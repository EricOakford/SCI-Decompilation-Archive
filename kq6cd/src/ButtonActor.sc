;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include sci.sh)
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
	(properties)
	
	(method (init)
		(super init: &rest)
		(if (or (== howFast 0) (not global169))
			(Palette palSET_INTENSITY 0 255 0)
			(Load rsPIC 100)
			(presents init:)
			(DrawPic 99)
		)
		(theGame setCursor: blankCursor)
		(theIconBar disable:)
		(curRoom setScript: introScript)
	)
	
	(method (doVerb)
	)
	
	(method (newRoom)
		(theIconBar height: 0 activateHeight: 0)
		(super newRoom: &rest)
	)
)

(instance introScript of Script
	(properties)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(keyDownHandler add: self)
				(if (and global169 (Platform 7) (> howFast 0))
					(self setScript: winLogo self)
				else
					(self setScript: dosLogo self)
				)
			)
			(1
				(curRoom drawPic: 100 9)
				(= cycles 1)
			)
			(2
				(if global169
					(= cycles 1)
				else
					(FadeCode init: 100 1 self)
				)
			)
			(3
				(six init:)
				(theMusic number: 2 loop: 1 play: self)
				(= seconds 5)
			)
			(4 (six setCycle: EndLoop))
			(5
				(theGame handsOn:)
				(= seconds 10)
				(theIconBar
					enable:
					disable: 0 1 2 3 4 5
					height: -100
					activateHeight: -100
				)
				(SetSynonyms 0 {ALEX})
				(Cursor showCursor: 1)
				(theGame setCursor: normalCursor)
				(six stopUpd:)
				(openingBut init: stopUpd:)
				(playBut init: stopUpd:)
				(helpBut init: stopUpd:)
				(restoreBut init: stopUpd:)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(& (event type?) evKEYBOARD)
				(== (event message?) KEY_ESCAPE)
			)
			(event claimed: 1)
		)
		(return 0)
	)
)

(instance FadeCode of Code
	(properties)
	
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
	
	(method (doit &tmp temp0)
		(if (!= local0 local2)
			(= local0 (+ local0 (* 1 local1)))
			(Palette palSET_INTENSITY 0 255 local0)
			(= temp0 0)
			(while (< temp0 10)
				(++ temp0)
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
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
	)
	
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
					(& (event type?) evMOUSEBUTTON)
					(and
						(& (event type?) evKEYBOARD)
						(== (event message?) KEY_RETURN)
					)
				)
				(<= nsLeft (event x?))
				(<= (event x?) nsRight)
				(<= nsTop (event y?))
				(<= (event y?) nsBottom)
			)
			((curRoom script?) seconds: 0)
			(if (self flash: (& (event type?) evKEYBOARD))
				(self cue:)
			)
			(event claimed: 1)
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
code_0421:
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
			bnt      code_0438
			ldi      8
			jmp      code_043a
code_0438:
			ldi      2
code_043a:
			ne?     
			bnt      code_046a
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
			jmp      code_0421
code_046a:
			pTos     cel
			ldi      1
			eq?     
			bnt      code_0475
			ldi      1
			sat      temp1
code_0475:
			pushi    #cel
			pushi    1
			pushi    0
			pushi    301
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
			bnt      code_04a8
			pushi    #seconds
			pushi    1
			pushi    8
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			send     6
code_04a8:
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
		(cond 
			(
				(and
					global169
					(Platform 7)
					(DoSound sndGET_AUDIO_CAPABILITY)
				)
				(curRoom newRoom: 108)
			)
			(
				(and
					(not global169)
					(not (== howFast 0))
					(== (Graph grGET_COLOURS) 256)
					(DoSound sndGET_AUDIO_CAPABILITY)
				)
				(curRoom newRoom: 105)
			)
			(else (curRoom newRoom: 106))
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
		(theGame setCursor: normalCursor)
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

(instance dosLogo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Cursor showCursor: 0)
				(theMusic number: 1 loop: 1)
				(theMusic play: self)
			)
			(1
				(if (not global169)
					(if (== howFast 0)
						(FadeCode init: 100 1 self)
					else
						(FadeCode init: 100 1)
					)
				)
			)
			(2 (glint init: setCycle: EndLoop))
			(3
				(glint posn: 148 143 setCycle: EndLoop)
			)
			(4 (= cycles 1))
			(5
				(if global169
					(= cycles 1)
				else
					(FadeCode init: 0 -1 self)
				)
			)
			(6
				(presents dispose:)
				(glint dispose:)
				(if global169 (curRoom drawPic: 98))
				(= cycles 1)
			)
			(7 (self dispose:))
		)
	)
)

(instance winLogo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom drawPic: 98)
				(= cycles 1)
			)
			(1
				(if (not (ShowMovie 0 {hdlogo.avi}))
					(ShowMovie 1 0 5)
					(ShowMovie 2 0 self)
				else
					(= cycles 1)
				)
			)
			(2
				(ShowMovie 6)
				(self dispose:)
			)
		)
	)
)
