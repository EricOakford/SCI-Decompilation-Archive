;;; Sierra Script 1.0 - (do not remove this comment)
(script# 417)
(include game.sh)
(use Main)
(use Actor)
(use System)


(class Button of Prop
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(& (event type?) keyDown)
				(& (event message?) ENTER)
				(<= nsLeft (event x?))
				(<= (event x?) nsRight)
				(<= nsTop (event y?))
				(<= (event y?) nsBottom)
			)
			(if (self onMe: event)
				(self doVerb: ((theIconBar curIcon?) message?))
			)
			(event claimed: TRUE)
			(return)
		else
			(super handleEvent: event)
		)
	)
	
	(method (onMe event)
		(if (super onMe: event)
			(self flash: (& (event type?) keyDown))
			(return)
		)
	)
	
	(method (flash param1 &tmp temp0 temp1)
		(asm
			ldi      0
			sat      temp1
			pushi    #startUpd
			pushi    0
			self     4
code_0141:
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
			bnt      code_0158
			ldi      8
			jmp      code_015a
code_0158:
			ldi      2
code_015a:
			ne?     
			bnt      code_018a
			pushi    #localize
			pushi    0
			lat      temp0
			send     4
			pushi    #cel
			pushi    1
			pushi    #onMe
			pushi    1
			lst      temp0
			super    Prop,  6
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
			jmp      code_0141
code_018a:
			pushi    #cel
			pushi    1
			pushi    0
			pushi    304
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
			pushi    #localize
			pushi    0
			lat      temp0
			send     4
			pushi    #onMe
			pushi    1
			lst      temp0
			super    Prop,  6
			bnt      code_01b8
			ldi      1
			sat      temp1
code_01b8:
			pushi    #dispose
			pushi    0
			lat      temp0
			send     4
			lat      temp1
			ret     
		)
	)
)
