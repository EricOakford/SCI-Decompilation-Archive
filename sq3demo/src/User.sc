;;; Sierra Script 1.0 - (do not remove this comment)
(script# 996)
(include game.sh)
(use Main)
(use Intrface)
(use SortCopy)
(use Sound)
(use Motion)
(use Menu)
(use System)


(local
	[inputLine 23]
	inputLen
)
(class User of Object
	(properties
		alterEgo 0
		canInput 0
		controls 0
		echo 32
		prevDir 0
		prompt {Enter input}
		inputLineAddr 0
		x -1
		y -1
		blocks 1
		mapKeyToDir 1
	)
	
	(method (init param1 param2)
		(= inputLineAddr (if argc param1 else @inputLine))
		(= inputLen (if (== argc 2) param2 else 45))
	)
	
	(method (doit &tmp newEvent newEventType newEventMessage)
		(if ((= newEvent (Event new:)) type?)
			(= lastEvent newEvent)
			(= newEventType (newEvent type?))
			(if mapKeyToDir (MapKeyToDir newEvent))
			(if TheMenuBar (TheMenuBar handleEvent: newEvent))
			(GlobalToLocal newEvent)
			(theGame handleEvent: newEvent)
			(if (not (newEvent claimed?))
				(switch (newEvent type?)
					(1
						(if
							(and
								controls
								(IsObject alterEgo)
								(cast contains: alterEgo)
							)
							(alterEgo setMotion: MoveTo (newEvent x?) (newEvent y?))
							(= prevDir 0)
							(newEvent claimed: 1)
						)
					)
					(64
						(if
							(and
								controls
								(IsObject alterEgo)
								(cast contains: alterEgo)
							)
							(= newEventMessage (newEvent message?))
							(if
								(and
									(== newEventType 4)
									(== newEventMessage prevDir)
									(IsObject (alterEgo mover?))
								)
								(= newEventMessage 0)
							)
							(= prevDir
								(if (== newEventType 4) newEventMessage else 0)
							)
							(alterEgo setDirection: newEventMessage)
							(newEvent claimed: 1)
						)
					)
					(else 
						(cast handleEvent: newEvent)
					)
				)
			)
			(if
				(and
					(not (newEvent claimed?))
					(== (newEvent type?) 4)
					(or
						(== (newEvent message?) echo)
						(and
							(<= 32 (newEvent message?))
							(<= (newEvent message?) 127)
						)
					)
					canInput
					(self getInput: newEvent)
					(Parse @inputLine newEvent)
				)
				(newEvent type: 128)
				(self said: newEvent)
			)
		)
		(newEvent dispose:)
		(= lastEvent 0)
	)
	
	(method (canControl theControls)
		(if argc (= controls theControls) (= prevDir 0))
		(return controls)
	)
	
	(method (getInput param1 &tmp temp0 temp1)
		(if (!= (param1 type?) 4) (= inputLine 0))
		(if (!= (param1 message?) echo)
			(Format @inputLine 996 0 (param1 message?))
		)
		(= temp0 (Sound pause: blocks))
		(= temp1 (GetInput @inputLine inputLen prompt 67 x y))
		(Sound pause: temp0)
		(return temp1)
	)
	
	(method (said param1)
		(if TheMenuBar (sortedFeatures addToFront: TheMenuBar))
		(if useSortedFeatures
			(SortedAdd alterEgo sortedFeatures cast features)
		else
			(sortedFeatures add: cast features)
		)
		(sortedFeatures
			addToEnd: theGame
			handleEvent: param1
			release:
		)
		(if
		(and (== (param1 type?) 128) (not (param1 claimed?)))
			(theGame pragmaFail: @inputLine)
		)
	)
)
