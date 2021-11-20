;;; Sierra Script 1.0 - (do not remove this comment)
(script# 89)
(include sci.sh)
(use Main)
(use Array)
(use Feature)
(use Actor)
(use System)

(public
	safePuz 0
)

(local
	theGGX
	local1
	local2
	local3
	local4 =  1
	local5
	local6
	theIconBarGetCursor
	local8
	local9 =  1
)
(procedure (localproc_0044 param1 param2 &tmp temp0 temp1 temp2 temp3)
	(= temp1 999)
	(= temp0 0)
	(while (< temp0 12)
		(if
			(<
				(= temp2
					(GetDistance
						param1
						param2
						(local2 at: temp0)
						(local3 at: temp0)
					)
				)
				temp1
			)
			(= temp1 temp2)
			(= temp3 temp0)
		)
		(++ temp0)
	)
	(return temp3)
)

(instance safePuz of Script
	(properties)
	
	(method (init)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(myList init:)
		(= local2
			(IntArray
				with: 152 169 180 183 179 170 154 141 126 121 124 130
			)
		)
		(= local3
			(IntArray with: 68 73 81 97 110 120 124 123 114 97 84 74)
		)
		(super init: &rest)
	)
	
	(method (doit)
		(if (and (== state 3) (or (Btst 248) local8))
			(self dispose:)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(myList dispose:)
		(Bclr 50)
		(Bclr 51)
		(local2 dispose:)
		(local3 dispose:)
		(if (Btst 240) (dial dispose:) (comboLetter dispose:))
		(safe dispose:)
		(bogusFtr dispose:)
		(theGame handsOn: setCursor: theIconBarGetCursor)
		(curRoom drawPic: curRoomNum)
		(Bclr 147)
		((ScriptID 0 21) doit: curRoomNum)
		(cast eachElementDo: #perform (ScriptID 90 0) 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(UpdatePlane
					((curRoom plane?) back: 0 picture: -1 yourself:)
				)
				(Bset 147)
				((ScriptID 0 21) doit: 100)
				(cast eachElementDo: #perform (ScriptID 90 0) 1)
				(= cycles 2)
			)
			(1
				1
				(safe init:)
				(if (Btst 240) (dial init:) (comboLetter init:))
				(bogusFtr init:)
				(= cycles 2)
			)
			(2
				2
				(if (Btst 240) (myList add: dial comboLetter))
				(myList add: safe bogusFtr)
				(theGame handsOn:)
				(= theIconBarGetCursor (theIconBar getCursor:))
				(theGame
					setCursor: ((theIconBar getCursor:)
						view: 999
						loop: 0
						cel: 0
						yourself:
					)
				)
				(Bset 50)
				(Bset 51)
				(= local8 0)
				(if (Btst 240)
					(messager say: 25 6 14 0 self)
				else
					(= cycles 1)
				)
			)
			(3 3 0)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(cond 
			((OneOf (event type?) 1 32)
				(= theGGX mouseX)
				(= local1 (- mouseY 10))
				(if (not (Btst 240)) (= local8 1))
				(if (= temp0 (myList firstTrue: #onMe event))
					(temp0 doVerb:)
				)
				(event claimed: 1)
				(return)
			)
			((== (event type?) evKEYBOARD) (event claimed: 1) (return))
			(else (super handleEvent: event))
		)
	)
)

(instance sSpinIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(= local6 (localproc_0044 theGGX local1))
				(= cycles 1)
			)
			(1
				1
				(cond 
					(local4
						(if (== (dial cel?) (dial lastCel:))
							(dial cel: 0)
						else
							(dial cel: (+ (dial cel?) 1))
						)
					)
					((== (dial cel?) 0) (dial cel: (dial lastCel:)))
					(else (dial cel: (- (dial cel?) 1)))
				)
				(= cycles 1)
			)
			(2
				2
				(if (== (dial cel?) local6)
					(= local6 0)
					(if
						(or
							(and (== local5 0) (== local4 1) (== (dial cel?) 5))
							(and (== local5 1) (== local4 0) (== (dial cel?) 8))
							(and (== local5 2) (== local4 1) (== (dial cel?) 11))
							(and (== local5 3) (== local4 0) (== (dial cel?) 2))
							(and (== local5 4) (== local4 1) (== (dial cel?) 7))
						)
						(if (>= (++ local5) 5)
							(Bset 248)
							(ego solvePuzzle: 510 2 4)
						)
					else
						(= local5 0)
					)
					(= local4 (if local4 0 else 1))
					(theGame handsOn:)
					(self dispose:)
				else
					(self changeState: 1)
				)
			)
		)
	)
)

(instance myList of List
	(properties)
	
	(method (dispose)
		(if elements (DisposeList elements))
		(= size (= elements 0))
		(DisposeClone self)
	)
)

(instance safe of View
	(properties
		x 42
		y 31
		priority 250
		fixPriority 1
		view 342
		signal $4000
	)
	
	(method (doVerb)
		(if (Btst 240)
			(switch local9
				(1
					(++ local9)
					(messager say: 25 9 0 1)
				)
				(2
					(++ local9)
					(messager say: 25 9 0 2)
				)
				(3
					(++ local9)
					(messager say: 25 9 0 3)
				)
				(4
					(++ local9)
					(messager say: 25 9 0 4)
				)
				(else  (messager say: 25 9 0 5))
			)
		else
			(= local8 1)
		)
	)
)

(instance dial of Prop
	(properties
		x 154
		y 296
		z 200
		priority 254
		fixPriority 1
		view 342
		loop 1
		signal $4001
	)
	
	(method (doVerb)
		(messager say: 32 6 112)
	)
)

(instance comboLetter of Feature
	(properties
		nsLeft 104
		nsTop 46
		nsRight 207
		nsBottom 148
		x 154
		y 203
		z 50
	)
	
	(method (doVerb)
		(dial setScript: sSpinIt)
	)
)

(instance bogusFtr of Feature
	(properties
		nsRight 320
		nsBottom 200
		x 160
		y 100
	)
	
	(method (doVerb)
		(= local8 1)
	)
)
