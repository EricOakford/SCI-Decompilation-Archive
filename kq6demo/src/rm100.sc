;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include game.sh)
(use Main)
(use KQ6Room)
(use Motion)
(use Actor)
(use System)

(public
	rm100 0
)

(local
	local0
	local1
	local2
	local3
)
(instance rm100 of KQ6Room
	(properties
		picture 99
	)
	
	(method (init)
		(Palette PALIntensity 0 255 0)
		(Load RES_PIC 100)
		(presents init:)
		(super init: &rest)
		(theGame setCursor: blankCursor)
		(theIconBar disable:)
		(curRoom setScript: introScript)
	)
	
	(method (doVerb)
	)
)

(instance introScript of Script

	(method (dispose)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(keyDownHandler add: self)
				(Cursor showCursor: FALSE)
				(theMusic1 number: 1 loop: 1)
				(if (== howFast 0)
					(theMusic1 play:)
					(= seconds 6)
				else
					(theMusic1 play: self)
				)
			)
			(1
				(if (== howFast 0)
					(FadeCode init: 100 1 self)
				else
					(FadeCode init: 100 1)
				)
			)
			(2
				(glint init: setCycle: EndLoop)
				(if (== howFast 0) (= cycles 1))
			)
			(3
				(glint posn: 148 143 setCycle: EndLoop)
				(if (== howFast 0) (= cycles 1))
			)
			(4
				(if (== howFast 0) (= seconds 2) else (= cycles 1))
			)
			(5 (FadeCode init: 0 -1 self))
			(6
				(presents dispose:)
				(glint dispose:)
				(curRoom drawPic: 100 PIXELDISSOLVE)
				(= cycles 1)
			)
			(7 (FadeCode init: 100 1 self))
			(8
				(six init:)
				(theMusic1 number: 2 loop: 1 play: self)
				(= seconds 5)
			)
			(9 (six setCycle: EndLoop))
			(10
				(theIconBar
					enable:
					disable:  ICON_WALK ICON_DO ICON_LOOK ICON_TALK ICON_ITEM ICON_INVENTORY
					height: -100
					activateHeight: -100
				)
				(Cursor showCursor: TRUE)
				(theGame setCursor: normalCursor)
				(six stopUpd:)
				(= seconds 3)
			)
			(11 (openingBut cue:))
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(& (event type?) keyDown)
				(== (event message?) ESC)
			)
			(event message: 114)
		)
		(return 0)
	)
)

(instance FadeCode of Code

	(method (init param1 param2 param3)
		(= local3 0)
		(if (>= argc 1)
			(= local2 param1)
			(if (>= argc 2)
				(= local1 param2)
				(if (>= argc 3) (= local3 param3))
			)
		)
		(theDoits add: self)
	)
	
	(method (doit &tmp i)
		(if (!= local0 local2)
			(= local0 (+ local0 (* 1 local1)))
			(Palette PALIntensity 0 255 local0)
			(= i 0)
			(while (< i 10)
				(++ i)
			)
		else
			(theDoits delete: self)
			(if (and local3 (IsObject local3))
				(local3 cue:)
				(= local3 0)
			)
		)
	)
)

(instance glint of Prop
	(properties
		x 161
		y 124
		view 100
	)
)

(instance presents of Prop
	(properties
		x 132
		y 182
		view 100
		loop 1
	)
)

(instance blankCursor of Cursor
	(properties
		view 998
		cel 1
	)
)

(class ButtonActor of Actor
	
	(method (init)
		(super init: &rest)
		(keyDownHandler add: self)
		(mouseDownHandler add: self)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (event modifiers?))
				(or
					(& (event type?) mouseDown)
					(and
						(& (event type?) keyDown)
						(== (event message?) ENTER)
					)
				)
				(<= nsLeft (event x?))
				(<= (event x?) nsRight)
				(<= nsTop (event y?))
				(<= (event y?) nsBottom)
			)
			((curRoom script?) seconds: 0)
			(if (self flash: (& (event type?) keyDown))
				(self cue:)
			)
			(event claimed: TRUE)
		)
		(event claimed?)
	)
	
	(method (flash param1 &tmp temp0 temp1)
		(asm
			ldi      0
			sat      temp1
			pushi    #startUpd
			pushi    0
			self     4
code_0468:
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp0
			send     4
			push    
			lap      param1
			bnt      code_047f
			ldi      8
			jmp      code_0481
code_047f:
			ldi      2
code_0481:
			ne?     
			bnt      code_04b1
			pushi    #localize
			pushi    0
			lat      temp0
			send     4
			pushi    #cel
			pushi    1
			pushi    #onMe
			pushi    1
			lst      temp0
			super    Actor,  6
			push    
			self     6
			pushi    2
			pushi    #elements
			pushi    0
			lag      cast
			send     4
			push    
			pushi    1
			callk    Animate,  4
			pushi    #dispose
			pushi    0
			lat      temp0
			send     4
			jmp      code_0468
code_04b1:
			pTos     cel
			ldi      1
			eq?     
			bnt      code_04bc
			ldi      1
			sat      temp1
code_04bc:
			pushi    #cel
			pushi    1
			pushi    0
			pushi    299
			pushi    0
			self     10
			pushi    2
			pushi    #elements
			pushi    0
			lag      cast
			send     4
			push    
			pushi    1
			callk    Animate,  4
			pushi    #dispose
			pushi    0
			lat      temp0
			send     4
			lat      temp1
			not     
			bnt      code_04ef
			pushi    #seconds
			pushi    1
			pushi    8
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			send     6
code_04ef:
			lat      temp1
			ret     
		)
	)
)

(instance openingBut of ButtonActor
	(properties
		x 110
		y 174
		view 100
		loop 3
	)
	
	(method (cue)
		(if
			(and
				(not (== howFast 0))
				(== (Graph GDetect) 256)
			)
			(curRoom newRoom: 105)
		else
			(curRoom newRoom: 106)
		)
	)
)

(instance playBut of ButtonActor
	(properties
		x 220
		y 173
		view 100
		loop 5
	)
	
	(method (cue)
		(curRoom newRoom: 200)
	)
)

(instance helpBut of ButtonActor
	(properties
		x 165
		y 174
		view 100
		loop 4
	)
	
	(method (cue)
		(curRoom newRoom: 205)
	)
)

(instance restoreBut of ButtonActor
	(properties
		x 55
		y 174
		view 100
		loop 2
	)
	
	(method (cue)
		(theGame restore:)
		((curRoom script?) seconds: 8)
	)
)

(instance six of Prop
	(properties
		x 113
		y 74
		view 101
	)
)
