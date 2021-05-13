;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include sci.sh)
(use Main)
(use Inset)
(use Feature)
(use User)
(use Actor)
(use System)

(public
	inNotebook 0
)

(local
	local0
	local1
	local2
	local3
	theSubject_2
	theX
	theY_2
	local7
	theTheSubject_2
	local9
)
(procedure (localproc_03c0 &tmp temp0 temp1 temp2)
	(backPage hide:)
	(morePage hide:)
	(DrawPic 851 dpOPEN_CENTEREDGE)
	(notebookList eachElementDo: #subject 0)
	(Display {} dsRESTOREPIXELS local7)
	(= local7
		(Display
			@global136
			dsCOORD
			(- 160 (* 7 (StrLen @global136)))
			170
			dsFONT
			global119
			dsCOLOR
			myHighlightColor
			dsSAVEPIXELS
		)
	)
	(= local2 (= temp0 (* (- local0 1) 8)))
	(while
		(and
			(< local2 (+ 8 temp0))
			(= temp1 (Memory memPEEK (+ local1 (* 2 local2))))
		)
		(if
			((= temp2 (notebookList at: (mod local2 8)))
				respondsTo: #subject
			)
			(temp2 display: (mod local2 8) temp1)
		)
		(++ local2)
	)
	(if
		(and
			(== (mod local2 8) 0)
			(Memory memPEEK (+ local1 (* 2 local2)))
		)
		(morePage show:)
	)
)

(procedure (localproc_049e param1)
	(switch param1
		(1
			(peopleTab show: (if (> (peopleTab x?) 100) 1 else 0))
			(= local1 @global202)
		)
		(2
			(placesTab show: (if (> (placesTab x?) 100) 1 else 0))
			(= local1 @global220)
		)
		(3
			(thingsTab show: (if (> (thingsTab x?) 100) 1 else 0))
			(= local1 @global228)
		)
		(4
			(miscTab show: (if (> (miscTab x?) 100) 1 else 0))
			(= local1 @global263)
		)
	)
	(titlePage hide:)
	(= local0 1)
	(localproc_03c0)
)

(class NotebookItem of Obj
	(properties
		subject 0
		x 0
		y 0
		nsLeft 0
		nsRight 0
		nsTop 0
		nsBottom 0
	)
	
	(method (init)
		(notebookList add: self)
		(super init:)
	)
	
	(method (display theText theSubject &tmp temp0 temp1 [temp2 40] temp42 temp43 temp44 temp45)
		(if (> theSubject 1088)
			(= temp45 (- theSubject 1088))
			(= temp42 857)
			(= temp43 (/ (- temp45 1) 16))
			(= temp44 (mod (- temp45 1) 16))
			(= subject (- theSubject 1024))
			(self posn: theText temp42 temp43 temp44)
			(DrawCel temp42 temp43 temp44 x y)
		else
			(= subject theSubject)
			(= temp0 (/ subject 256))
			(= temp1 (mod subject 256))
			(self posn: theText)
			(Message msgGET 20 temp0 1 0 temp1 @temp2)
			(Display
				@temp2
				dsFONT
				10
				dsCOORD
				x
				y
				dsCOLOR
				(if (== theSubject_2 theSubject) global160 else 0)
				dsWIDTH
				100
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(cond 
			((event claimed?) (return 1))
			(
			(and (& (event type?) evVERB) (self onMe: event)) (event claimed: 1) (self doVerb: (event message?)))
		)
		(return (event claimed?))
	)
	
	(method (doVerb theVerb &tmp [temp0 100] temp100 temp101)
		(switch theVerb
			(13 (inNotebook doVerb: 13))
			(4
				(if (< subject 256)
					(= theSubject_2 0)
					(if (< (StrLen @global136) 16)
						(Format @global136 {%s%c} @global136 subject)
						(Display {} dsRESTOREPIXELS local7)
						(= local7
							(Display
								@global136
								dsCOORD
								(- 160 (* 7 (StrLen @global136)))
								170
								dsFONT
								global119
								dsCOLOR
								myHighlightColor
								dsSAVEPIXELS
							)
						)
					)
				else
					(Display {} dsRESTOREPIXELS local7)
					(= global136 0)
					(= temp100 (/ theSubject_2 256))
					(= temp101 (mod theSubject_2 256))
					(if
						(and
							theSubject_2
							(notebookList
								firstTrue: #perform oldOnePresent theSubject_2
							)
						)
						(Message msgGET 20 temp100 1 0 temp101 @temp0)
						(Display @temp0 dsFONT 10 dsCOORD theX theY_2 dsWIDTH 100)
					)
					(= theSubject_2 subject)
					(= theX x)
					(= theY_2 y)
					(= temp100 (/ theSubject_2 256))
					(= temp101 (mod theSubject_2 256))
					(Message msgGET 20 temp100 1 0 temp101 @temp0)
					(Display
						@temp0
						dsFONT
						10
						dsCOORD
						theX
						theY_2
						dsCOLOR
						global160
						dsWIDTH
						100
					)
				)
			)
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(if
				(and
					(<= nsLeft (theObjOrX x?))
					(<= (theObjOrX x?) nsRight)
					(<= nsTop (theObjOrX y?))
					(<= (theObjOrX y?) nsBottom)
				)
				1
			else
				0
			)
		)
	)
	
	(method (posn param1 param2 param3 param4 &tmp temp0)
		(if (< subject 256)
			(= temp0 (if (< param1 4) 65 else 172))
			(= nsTop (+ (* (mod param1 4) 21) 43))
			(= nsLeft (+ temp0 (if (mod param1 2) 40 else 0)))
			(= nsBottom (+ (CelHigh param2 param3 param4) nsTop))
			(= nsRight (+ (CelWide param2 param3 param4) nsLeft))
			(= x nsLeft)
			(= y nsTop)
		else
			(= nsTop (+ (* (mod param1 4) 24) 43))
			(= nsLeft (if (< param1 4) 65 else 179))
			(= nsBottom (+ 5 nsTop))
			(= nsRight (+ 75 nsLeft))
			(= x nsLeft)
			(= y nsTop)
		)
	)
)

(instance oldOnePresent of Code
	(properties)
	
	(method (doit param1 param2)
		(return (== (param1 subject?) param2))
	)
)

(instance notebookList of EventHandler
	(properties)
)

(instance titlePage of View
	(properties
		x 156
		y 151
		modNum 20
		view 854
	)
	
	(method (initialize)
	)
	
	(method (doVerb)
		(inNotebook doVerb: &rest)
	)
)

(instance morePage of View
	(properties
		x 237
		y 153
		modNum 20
		view 851
		loop 4
		cel 1
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(++ local0)
				(localproc_03c0)
				(backPage show:)
			)
			(else 
				(inNotebook doVerb: theVerb)
			)
		)
	)
)

(instance backPage of View
	(properties
		x 85
		y 153
		modNum 20
		view 851
		loop 4
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(-- local0)
				(localproc_03c0)
				(if (== local0 1) (self hide:) else (self show:))
			)
			(else 
				(inNotebook doVerb: theVerb)
			)
		)
	)
)

(instance peopleTab of View
	(properties
		x 262
		y 58
		modNum 20
		view 851
		loop 1
		priority 5
		signal $0010
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (localproc_049e 1))
			(else 
				(inNotebook doVerb: theVerb)
			)
		)
	)
	
	(method (show param1)
		(if (and argc param1)
			(self setLoop: 2 x: 59)
		else
			(placesTab setLoop: 1 x: 262)
			(thingsTab setLoop: 1 x: 262)
			(miscTab setLoop: 1 x: 262)
		)
	)
)

(instance placesTab of View
	(properties
		x 262
		y 92
		modNum 20
		view 851
		loop 1
		cel 1
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (localproc_049e 2))
			(else 
				(inNotebook doVerb: theVerb)
			)
		)
	)
	
	(method (show param1)
		(if (and argc param1)
			(self setLoop: 2 x: 59)
			(peopleTab show: 1)
		else
			(thingsTab setLoop: 1 x: 262)
			(miscTab setLoop: 1 x: 262)
		)
	)
)

(instance thingsTab of View
	(properties
		x 262
		y 128
		modNum 20
		view 851
		loop 1
		cel 2
		priority 4
		signal $0010
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (localproc_049e 3))
			(else 
				(inNotebook doVerb: theVerb)
			)
		)
	)
	
	(method (show param1)
		(if (and argc param1)
			(self setLoop: 2 x: 59)
			(placesTab show: 1)
		else
			(miscTab setLoop: 1 x: 262)
		)
	)
)

(instance miscTab of View
	(properties
		x 262
		y 153
		modNum 20
		view 851
		loop 1
		cel 3
		priority 3
		signal $0010
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (localproc_049e 4))
			(else 
				(inNotebook doVerb: theVerb)
			)
		)
	)
	
	(method (show param1)
		(if (and argc param1)
			(self setLoop: 2 x: 59)
			(thingsTab show: 1)
		)
	)
)

(instance backUp of Feature
	(properties
		x 1
		y 1
		nsTop 175
		nsLeft 41
		nsBottom 189
		nsRight 279
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(StrAt @global136 (- (StrLen @global136) 1) 0)
				(Display {} dsRESTOREPIXELS local7)
				(= local7
					(Display
						@global136
						dsCOORD
						(- 160 (* 7 (StrLen @global136)))
						170
						dsFONT
						global119
						dsCOLOR
						myHighlightColor
						dsSAVEPIXELS
					)
				)
			)
			(else 
				(inNotebook doVerb: theVerb)
			)
		)
	)
)

(instance inNotebook of Inset
	(properties
		picture 851
		style $000a
		modNum 20
	)
	
	(method (init)
		(if
		(= local9 (!= (((theIconBar at: 0) cursor?) view?) 6))
			(InFirstPerson 1)
		)
		(theGame handsOff:)
		(theIconBar disable: 7 enable: 2 0)
		(super init: &rest)
		(= theSubject_2 0)
		(= theTheSubject_2 0)
		(= global136 0)
		(= local2 0)
		(while (< local2 8)
			((NotebookItem new:) posn: local2 init:)
			(++ local2)
		)
		(backUp init:)
		(titlePage init: stopUpd:)
		(morePage init: hide:)
		(backPage init: hide:)
		(peopleTab init: stopUpd:)
		(placesTab init: stopUpd:)
		(thingsTab init: stopUpd:)
		(miscTab init: stopUpd:)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(directionHandler addToFront: self)
		(theIconBar select: (theIconBar at: 2))
		(theGame setCursor: 2)
		((user curEvent?) claimed: 1)
		(self doit:)
	)
	
	(method (doit &tmp temp0)
		(asm
			pushi    #joyInc
			pushi    1
			pushi    2
			lag      pMouse
			send     6
code_08b5:
			lal      theTheSubject_2
			not     
			bnt      code_0a0a
			pushi    2
			pushi    #elements
			pushi    0
			lag      cast
			send     4
			push    
			pushi    1
			callk    Animate,  4
			lag      doMotionCue
			bnt      code_08da
			ldi      0
			sag      doMotionCue
			pushi    #eachElementDo
			pushi    1
			pushi    253
			lag      cast
			send     6
code_08da:
			pToa     script
			bnt      code_08e3
			pushi    #doit
			pushi    0
			send     4
code_08e3:
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp0
			sag      lastEvent
			pushi    #localize
			pushi    0
			lat      temp0
			send     4
			lag      pMouse
			bnt      code_090b
			pushi    #contains
			pushi    1
			push    
			lag      theDoits
			send     6
			bnt      code_090b
			pushi    #doit
			pushi    0
			lag      pMouse
			send     4
code_090b:
			pushi    #canControl
			pushi    1
			pushi    1
			lag      user
			send     6
			pushi    1
			lst      temp0
			callk    MapKeyToDir,  2
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      256
			eq?     
			bnt      code_094c
			pushi    31
			pushi    1
			pushi    4
			pushi    37
			pushi    1
			pushi    #modifiers
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      3
			and     
			bnt      code_0941
			ldi      27
			jmp      code_0943
code_0941:
			ldi      13
code_0943:
			push    
			pushi    61
			pushi    1
			pushi    0
			lat      temp0
			send     18
code_094c:
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      64
			and     
			bnt      code_098e
			pushi    #message
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      0
			eq?     
			bnt      code_0981
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      4
			and     
			bnt      code_0981
			pushi    #handleEvent
			pushi    1
			lst      temp0
			lag      theIconBar
			send     6
			jmp      code_09f5
code_0981:
			pushi    #handleEvent
			pushi    1
			lst      temp0
			lag      pMouse
			send     6
			jmp      code_09f5
code_098e:
			pushi    #handleEvent
			pushi    1
			lst      temp0
			lag      theIconBar
			send     6
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      16384
			and     
			bnt      code_09f5
			pushi    #init
			pushi    0
			class    OnMeAndLowY
			send     4
			pushi    #eachElementDo
			pushi    3
			pushi    96
			class    OnMeAndLowY
			push    
			lst      temp0
			lag      cast
			send     10
			pushi    #eachElementDo
			pushi    3
			pushi    96
			class    OnMeAndLowY
			push    
			lst      temp0
			lag      features
			send     10
			pushi    #handleEvent
			pushi    1
			lst      temp0
			lofsa    notebookList
			send     6
			bnt      code_09db
			jmp      code_09f5
code_09db:
			pushi    #theObj
			pushi    0
			class    OnMeAndLowY
			send     4
			bnt      code_09f5
			pushi    #handleEvent
			pushi    1
			lst      temp0
			pushi    #theObj
			pushi    0
			class    OnMeAndLowY
			send     4
			send     6
code_09f5:
			lat      temp0
			bnt      code_08b5
			pushi    #claimed
			pushi    1
			pushi    1
			send     6
			pushi    #dispose
			pushi    0
			lat      temp0
			send     4
			jmp      code_08b5
code_0a0a:
			pushi    #joyInc
			pushi    1
			pushi    5
			lag      pMouse
			send     6
			lal      theTheSubject_2
			ret     
		)
	)
	
	(method (dispose)
		(notebookList dispose:)
		(super dispose: &rest)
		(Animate (cast elements?) 0)
		(= gameTime (+ tickOffset (GetTime)))
		(if local9 (InFirstPerson 0))
		(theGame handsOn: 1)
		(theIconBar enable: 7)
		(DisposeScript 20)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(theGame setCursor: waitCursor)
				(if (not theSubject_2) (= theSubject_2 -1))
				(= theTheSubject_2 theSubject_2)
				(inNotebook dispose:)
			)
		)
	)
)
