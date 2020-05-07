;;; Sierra Script 1.0 - (do not remove this comment)
(script# 996)
(include game.sh)
(use Main)
(use Intrface)
(use SortCopy)
(use Sound)
(use Motion)
(use Menu)
(use Actor)
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
	
	(method (doit)
		(if demoScripts else (self handleEvent: (Event new:)))
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
		(if useSortedFeatures
			(SortedAdd alterEgo sortedFeatures cast features)
		else
			(sortedFeatures add: cast features)
		)
		(if MenuBar (sortedFeatures addToFront: MenuBar))
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
	
	(method (handleEvent pEvent &tmp pEventType temp1)
		(if (pEvent type?)
			(= lastEvent pEvent)
			(= pEventType (pEvent type?))
			(if mapKeyToDir (MapKeyToDir pEvent))
			(if TheMenuBar (TheMenuBar handleEvent: pEvent pEventType))
			(GlobalToLocal pEvent)
			(if (not (pEvent claimed?))
				(theGame handleEvent: pEvent pEventType)
			)
			(if
				(and
					controls
					(not (pEvent claimed?))
					(cast contains: alterEgo)
				)
				(alterEgo handleEvent: pEvent)
			)
			(if
				(and
					canInput
					(not (pEvent claimed?))
					(== (pEvent type?) evKEYBOARD)
					(or
						(== (pEvent message?) echo)
						(and
							(<= KEY_SPACE (pEvent message?))
							(<= (pEvent message?) 255)
						)
					)
					(self getInput: pEvent)
					(Parse @inputLine pEvent)
				)
				(pEvent type: 128)
				(self said: pEvent)
			)
		)
		(pEvent dispose:)
		(= lastEvent 0)
	)
)

(class Ego of Actor
	(properties
		y 0
		x 0
		z 0
		heading 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $2000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		moveSpeed 0
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		edgeHit 0
	)
	
	(method (init)
		(super init:)
		(if (not cycler) (self setCycle: Walk))
	)
	
	(method (doit)
		(super doit:)
		(= edgeHit
			(cond 
				((<= x 0) 4)
				((<= y (curRoom horizon?)) 1)
				((>= x 319) 2)
				((>= y 189) 3)
				(else 0)
			)
		)
	)
	
	(method (handleEvent pEvent &tmp pEventMessage)
		(if (not (super handleEvent: pEvent))
			(switch (pEvent type?)
				(evMOUSEBUTTON
					(if
						(and
							(not (& (pEvent modifiers?) emSHIFT))
							(User controls?)
						)
						(self setMotion: MoveTo (pEvent x?) (pEvent y?))
						(User prevDir: 0)
						(pEvent claimed: 1)
					)
				)
				(evJOYSTICK
					(if
						(and
							(==
								(= pEventMessage (pEvent message?))
								(User prevDir?)
							)
							(IsObject mover)
						)
						(= pEventMessage 0)
					)
					(User prevDir: pEventMessage)
					(self setDirection: pEventMessage)
					(pEvent claimed: 1)
				)
			)
		)
		(pEvent claimed?)
	)
	
	(method (get param1 &tmp temp0)
		(= temp0 0)
		(while (< temp0 argc)
			((inventory at: [param1 temp0]) moveTo: self)
			(++ temp0)
		)
	)
	
	(method (put param1 param2)
		(if (self has: param1)
			((inventory at: param1)
				moveTo: (if (== argc 1) -1 else param2)
			)
		)
	)
	
	(method (has param1 &tmp temp0)
		(if (= temp0 (inventory at: param1))
			(temp0 ownedBy: self)
		)
	)
)
