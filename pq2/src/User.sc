;;; Sierra Script 1.0 - (do not remove this comment)
(script# 996)
(include sci.sh)
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
(class User of Obj
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
	
	(method (doit &tmp newEvent newEventType temp2)
		(if ((= newEvent (Event new:)) type?)
			(= lastEvent newEvent)
			(= newEventType (newEvent type?))
			(if mapKeyToDir (MapKeyToDir newEvent))
			(if MenuBar (MenuBar handleEvent: newEvent newEventType))
			(GlobalToLocal newEvent)
			(if (not (newEvent claimed?))
				(theGame handleEvent: newEvent newEventType)
			)
			(if
				(and
					controls
					(not (newEvent claimed?))
					(cast contains: alterEgo)
				)
				(alterEgo handleEvent: newEvent newEventType)
			)
			(if
				(and
					canInput
					(not (newEvent claimed?))
					(== (newEvent type?) 4)
					(or
						(== (newEvent message?) echo)
						(and
							(<= 32 (newEvent message?))
							(<= (newEvent message?) 127)
						)
					)
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
	
	(method (canControl value)
		(if argc (= controls value) (= prevDir 0))
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
)

(class Ego of Act
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
	
	(method (handleEvent event param2 &tmp temp0 eventMessage)
		(= temp0 (if (>= argc 2) param2 else (event type?)))
		(if (not (super handleEvent: event))
			(switch (event type?)
				(evMOUSEBUTTON
					(if (not (& (event modifiers?) emSHIFT))
						(self setMotion: MoveTo (event x?) (event y?))
						(User prevDir: 0)
						(event claimed: 1)
					)
				)
				(evJOYSTICK
					(= eventMessage (event message?))
					(if
						(and
							(== temp0 4)
							(== eventMessage (User prevDir?))
							(IsObject mover)
						)
						(= eventMessage 0)
					)
					(User prevDir: (if (== temp0 4) eventMessage else 0))
					(self setDirection: eventMessage)
					(event claimed: 1)
				)
			)
		)
		(event claimed?)
	)
	
	(method (get what &tmp temp0)
		(= temp0 0)
		(while (< temp0 argc)
			((inventory at: [what temp0]) moveTo: self)
			(++ temp0)
		)
	)
	
	(method (put what recipient)
		(if (self has: what)
			((inventory at: what)
				moveTo: (if (== argc 1) -1 else recipient)
			)
		)
	)
	
	(method (has what &tmp temp0)
		(if (= temp0 (inventory at: what))
			(temp0 ownedBy: self)
		)
	)
)
