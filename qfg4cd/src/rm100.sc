;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include sci.sh)
(use Main)
(use GloryRm)
(use TextIcon)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm100 0
)

(local
	theLoop
	local1
)
(procedure (localproc_057c)
	(switch theLoop
		(0 (curRoom newRoom: 140))
		(1 (curRoom newRoom: 54))
		(2
			(theGame restore:)
			(optionPanel init: show: dispose:)
			(localproc_057c)
		)
		(3 (curRoom newRoom: 160))
		(4 (= quit 1))
	)
)

(instance rm100 of GloryRm
	(properties
		picture 3
	)
	
	(method (init)
		(LoadMany 128 100 935)
		(Bset 50)
		(Bclr 6)
		(if (OneOf prevRoomNum 140 54 160)
			(= picture 130)
			(= style 0)
			(super init: &rest)
			(aShadows init:)
			(theBat init:)
			(aQuest init:)
			(Bset 51)
			(self setScript: doPanel)
		else
			(= picture 3)
			(Bset 51)
			(super init: &rest)
			(self setScript: doMovie)
		)
	)
	
	(method (dispose)
		(Bclr 50)
		(Bclr 51)
		(super dispose:)
	)
)

(instance doPanel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr 51)
				(optionPanel init: show: dispose:)
				(localproc_057c)
				(self dispose:)
			)
		)
	)
)

(instance doMovie of Script
	(properties)
	
	(method (init)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic number: 100 setLoop: 1 play:)
				(= seconds 13)
			)
			(1
				(UpdatePlane
					((curRoom plane?) back: 0 picture: -1 yourself:)
				)
				(= cycles 2)
			)
			(2 (= cycles 2))
			(3
				(cond 
					(local1
						(curRoom picture: 130)
						(curRoom drawPic: (curRoom picture?))
					)
					(
					(and (== (Platform) 1) (FileIO fiEXISTS {flythru.seq}))
						(theMusic number: 101 setLoop: -1 play:)
						(theMusic2 number: 102 setLoop: -1 play:)
						(SetCursor 0)
						(SetVideoMode 1)
						(ShowMovie {flythru.seq} 8)
						(SetVideoMode 0)
						(curRoom picture: 130)
						(curRoom drawPic: (curRoom picture?))
					)
					((!= (Platform) 1)
						(theMusic number: 101 setLoop: -1 play:)
						(theMusic2 number: 102 setLoop: -1 play:)
						(ShowMovie 1 0 {flyby.AVI})
						(ShowMovie 1 1 0 0 319 199)
						(ShowMovie 1 2)
						(ShowMovie 1 7)
						(ShowMovie 1 6)
						(curRoom picture: 130)
						(curRoom drawPic: (curRoom picture?))
					)
					(else
						(if (!= (theMusic number?) 130)
							(theMusic setLoop: -1 number: 130 play:)
						)
						(theMusic2 number: 131 setLoop: 1 play:)
						(++ state)
					)
				)
				(aQuest init:)
				(aShadows init:)
				(Bclr 51)
				(theGame handsOff:)
				(Bset 51)
				(= cycles 1)
			)
			(4
				(theMusic2 stop:)
				(theMusic prevSignal: 0)
				(= ticks 1)
			)
			(5 (= ticks 120))
			(6
				(theBat init: setPri: 13 posn: 164 156 setCycle: End)
				(= ticks 270)
			)
			(7 (= ticks 90))
			(8
				(Bclr 51)
				(SetCursor 2)
				(theGame setCursor: normalCursor)
				(optionPanel init: show: dispose:)
				(localproc_057c)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(return
			(if
			(and (OneOf (event type?) 4 32 1) (< state 1))
				(theMusic number: 101 setLoop: -1 play:)
				(self seconds: 0 changeState: 1)
				(= local1 1)
				(event claimed: 1)
				(return event)
			else
				(super handleEvent: event)
			)
		)
	)
)

(instance optionPanel of PuzzleBar
	(properties
		state $0000
	)
	
	(method (init)
		(super init: &rest)
		(self changeCursor: 999)
	)
	
	(method (resetPuzzle &tmp temp0)
		(= temp0 0)
		(while (< temp0 5)
			(self
				add:
					((choiceIcon new:)
						nsLeft: 11
						x: 11
						nsTop: (+ (* temp0 20) 10)
						y: (+ (* temp0 20) 10)
						loop: temp0
						value: temp0
						init: self
						yourself:
					)
			)
			(++ temp0)
		)
		(self eachElementDo: #show)
	)
	
	(method (setPlane)
		(= usePlane 1)
		(plane bitmap: 0)
		(plane
			priority: (+ (GetHighPlanePri) 1)
			init:
			setRect: 82 40 258 165
			setBitmap: 935 0 0 1
			addCast: puzzleCast
		)
	)
	
	(method (addIcons)
	)
)

(instance choiceIcon of TextIcon
	(properties
		view 100
	)
	
	(method (select)
		(if (super select: &rest)
			(= theLoop loop)
			(optionPanel state: (& (optionPanel state?) $ffdf))
		)
	)
	
	(method (highlight param1 param2)
		(if (> argc 1)
			(= cel (if param2 1 else 2))
		else
			(= cel (if param1 0 else 2))
		)
		(UpdateScreenItem self)
		(FrameOut)
	)
)

(instance theBat of Prop
	(properties
		x 162
		y 153
		view 130
		loop 1
	)
)

(instance aQuest of View
	(properties
		x 86
		y 37
		view 130
		loop 2
		signal $4000
	)
)

(instance aShadows of View
	(properties
		x 158
		y 155
		view 130
		signal $4000
	)
)
