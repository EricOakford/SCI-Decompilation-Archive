;;; Sierra Script 1.0 - (do not remove this comment)
(script# 260)
(include game.sh)
(use Main)
(use Intrface)
(use Block)
(use LoadMany)
(use Wander)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm260 0
)

(local
	theTheDollars
	saveBits
	saveBits2
	[str 80]
	theDollars
	[local84 7]
	local91
	local92
	dollarPlural
)
(procedure (localproc_0f53)
	(if saveBits (Display 260 7 p_restore saveBits))
	(if saveBits2 (Display 260 7 p_restore saveBits2))
	(= dollars theDollars)
	(theMusic2 number: 266 loop: 1 play:)
	(if (> theDollars 0)
		(Printf 260 26 theDollars)
	else
		(Print 260 6)
	)
	(curRoom newRoom: 100)
)

(procedure (localproc_0fb5 param1)
	(if (> param1 0)
		(if (< (+ theDollars param1) 0)
			(= theDollars 10000)
		else
			(+= theDollars param1)
		)
	else
		(= theDollars 10000)
	)
	(if (< theDollars 0)
		(= theDollars 0)
	)
	(if (> theDollars 10000)
		(= theDollars 10000)
		(= local92 1)
	)
	(Display 260 7 p_restore saveBits2)
	(= saveBits2
		(Display
			(Format @str 260 1 theDollars)
			p_at 165 34
			p_color global130
			p_save
		)
	)
)

(procedure (localproc_102c param1)
	(theMusic3 number: 260 play:)
	(param1 setCel: 1)
	(Animate (cast elements?) FALSE)
	(param1 setCel: 0)
	(Animate (cast elements?) FALSE)
	(param1 stopUpd:)
)

(procedure (localproc_106e &tmp temp0)
	(return
		(if (mod (= temp0 (/ (+ theTheDollars 100) 50)) 2)
			(return (- temp0 1))
		else
			(return temp0)
		)
	)
)

(instance rm260 of Room
	(properties
		picture 260
		style FADEOUT
	)
	
	(method (init &tmp temp0)
		(Load SCRIPT RANDCYC)
		(Load VIEW 260)
		(LoadMany SOUND 260 261 262 263 264 265 266)
		(theMusic2 loop: 1 vol: 127 flags: mNOPAUSE)
		(theMusic3 loop: 1 vol: 127 flags: mNOPAUSE)
		(ego init: z: 1000 hide:)
		(super init:)
		(playButton init: stopUpd:)
		(increaseButton init: stopUpd:)
		(decreaseButton init: stopUpd:)
		(oddsButton init: stopUpd:)
		(cashOutButton init: stopUpd:)
		(theIconBar enable: disable: 4 5)
		(wheelLeft cycleSpeed: 0 init: stopUpd:)
		(wheelCenter cycleSpeed: 0 init: stopUpd:)
		(wheelRight cycleSpeed: 0 init: stopUpd:)
		(= theTheDollars 10)
		(= theDollars dollars)
		(= saveBits
			(Display
				(Format @str 260 0 theTheDollars)
				p_at 105 36
				p_color global130
				p_save
			)
		)
		(= saveBits2
			(Display
				(Format @str 260 1 theDollars)
				p_at 165 34
				p_color global130
				p_save
			)
		)
		(leftTank init:)
		(rightTank init:)
		(= temp0 0)
		(while (< temp0 (+ 4 howFast))
			((circle new:)
				moveSpeed: howFast
				init:
				cel: temp0
				setPri: temp0
				x: (Random 1 36)
				y: (Random 71 249)
				observeBlocks: leftTank
				setMotion: Wander
			)
			(++ temp0)
		)
		(= temp0 12)
		(while (> temp0 (- 9 howFast))
			((circle new:)
				moveSpeed: howFast
				init:
				cel: temp0
				setPri: temp0
				x: (Random 302 318)
				y: (Random 66 249)
				observeBlocks: rightTank
				setMotion: Wander
			)
			(-- temp0)
		)
		(stripe0 cycleSpeed: howFast setPri: 14 init: stopUpd:)
		(stripe1 cycleSpeed: howFast setPri: 14 init: stopUpd:)
		(stripe2 cycleSpeed: howFast setPri: 14 init: stopUpd:)
		(stripe3 cycleSpeed: howFast setPri: 14 init: stopUpd:)
		(stripe4 cycleSpeed: howFast setPri: 14 init: stopUpd:)
		(self setScript: waitASecScript self)
	)
	
	(method (doit)
		(if (GameIsRestarting)
			(= saveBits
				(Display
					(Format @str 260 0 theTheDollars)
					p_at 105 36
					p_color global130
					p_at
				)
			)
			(= saveBits2
				(Display
					(Format @str 260 1 theDollars)
					p_at 165 34
					p_color global130
					p_save
				)
			)
		)
		(cond 
			(script 0)
			((>= theDollars 10000)
				(HandsOff)
				(curRoom setScript: sWin)
			)
			((ego mover?)
				(localproc_0f53)
			)
			(
				(and
					(< theDollars 10)
					(not (curRoom script?))
					(not (Btst 104))
				)
				(Bset 104)
				(if (== theDollars 1)
					(= dollarPlural {dollar})
				else
					(= dollarPlural {dollars})
				)
				(if (> theDollars 0)
					(Printf 260 5 theDollars dollarPlural)
				else
					(Print 260 6)
				)
				(if saveBits (Display 260 7 p_restore saveBits))
				(if saveBits2 (Display 260 7 p_restore saveBits2))
				(= dollars theDollars)
				(curRoom newRoom: 100)
				(Bclr 104)
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(Print 260 2)
				(Print 260 3)
			)
			(2
				(Print 260 4)
			)
			(else  0)
		)
	)
	
	(method (newRoom)
		(super newRoom: &rest)
		(DontMove waitCursor)
	)
)

(instance waitASecScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame setCursor: waitCursor)
				(= seconds 1)
			)
			(1
				(HandsOn)
				(proc0_31)
				(self dispose:)
			)
		)
	)
)

(instance sWin of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic3 number: 265 play: self)
				(Print 260 8 #dispose)
			)
			(1
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(if local92 (Print 260 9))
				(localproc_0f53)
			)
		)
	)
)

(instance playButton of Prop
	(properties
		x 164
		y 189
		view 260
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(Print 260 10)
			)
			(2
				(cond 
					((< theDollars theTheDollars)
						(Print 260 11)
					)
					((== theTheDollars 0)
						(Print 260 12)
						(Print 260 13)
						(Print 260 14 #at -1 140)
					)
					(else
						(localproc_102c playButton)
						(HandsOff)
						(curRoom setScript: spinEm)
					)
				)
			)
			(else
				(Print 260 15)
			)
		)
	)
)

(instance spinEm of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 setLoop: -1 number: 261 play:)
				(HandsOff)
				(Display 260 7 p_restore saveBits2)
				(= saveBits2
					(Display
						(Format @str 260 1
							(-= theDollars theTheDollars)
						)
						p_at 165 34
						p_color global130
						p_save
					)
				)
				(wheelLeft setCel: (Random 0 7) setCycle: Forward)
				(wheelCenter setCel: (Random 0 7) setCycle: Forward)
				(wheelRight setCel: (Random 0 7) setCycle: Forward)
				(= seconds 3)
			)
			(1
				(wheelLeft
					setCycle: CycleTo (if debugging 6 else (* 2 (Random 0 3))) 1 wheelLeft
				)
				(= seconds 2)
			)
			(2
				(wheelCenter
					setCycle: CycleTo (if debugging 6 else (* 2 (Random 0 3))) 1 wheelCenter
				)
				(= seconds 2)
			)
			(3
				(wheelRight
					setCycle: CycleTo (if debugging 6 else (* 2 (Random 0 3))) 1 self
				)
			)
			(4
				(= local91 0)
				(theMusic2 number: 262 setLoop: 1 play:)
				(theMusic3 setLoop: -1)
				(cond 
					((== (wheelLeft cel?) 0)
						(if (== (wheelCenter cel?) 0)
							(if (== (wheelRight cel?) 0)
								(theMusic3 number: 264 play:)
								(if (> theTheDollars 8000)
									(localproc_0fb5 10000)
								else
									(localproc_0fb5 (* 4 theTheDollars))
								)
								(= seconds 5)
							else
								(theMusic3 number: 263 play:)
								(localproc_0fb5 (* 2 theTheDollars))
								(= seconds 3)
							)
						else
							(theMusic3 number: 263 play:)
							(localproc_0fb5 theTheDollars)
							(= seconds 2)
						)
					)
					(
						(and
							(== (wheelLeft cel?) (wheelCenter cel?))
							(== (wheelCenter cel?) 2)
						)
						(if (== (wheelRight cel?) 2)
							(theMusic3 number: 264 play:)
							(if (> theTheDollars 8000)
								(localproc_0fb5 10000)
							else
								(localproc_0fb5 (* 4 theTheDollars))
							)
							(= seconds 5)
						else
							(theMusic3 number: 263 play:)
							(localproc_0fb5 (* 2 theTheDollars))
							(= seconds 3)
						)
					)
					(
						(and
							(== (wheelLeft cel?) (wheelCenter cel?))
							(== (wheelCenter cel?) 4)
						)
						(if (== (wheelRight cel?) 4)
							(theMusic3 number: 264 play:)
							(if (> theTheDollars 6400)
								(localproc_0fb5 10000)
							else
								(localproc_0fb5 (* 5 theTheDollars))
							)
							(= seconds 6)
						else
							(theMusic3 number: 263 play:)
							(localproc_0fb5 (* 3 theTheDollars))
							(= seconds 4)
						)
					)
					(
						(and
							(== (wheelLeft cel?) (wheelCenter cel?))
							(== (wheelCenter cel?) (wheelRight cel?))
							(== (wheelRight cel?) 6)
						)
						(theMusic3 number: 264 play: self)
						(jackpotSign cycleSpeed: howFast init: setCycle: Forward)
						(if (> theTheDollars 4500)
							(localproc_0fb5 10000)
						else
							(localproc_0fb5 (* 7 theTheDollars))
						)
						(= seconds 8)
					)
					(else (= local91 (= cycles 1)))
				)
				(if (not local91) (stripe0 setCycle: EndLoop stripe1))
			)
			(5
				(stripe0 setCel: 0 stopUpd:)
				(stripe1 setCel: 0 stopUpd:)
				(stripe2 setCel: 0 stopUpd:)
				(stripe3 setCel: 0 stopUpd:)
				(stripe4 setCel: 0 stopUpd:)
				(theMusic3 setLoop: 1 stop:)
				(if (IsObject jackpotSign) (jackpotSign dispose:))
				(if (> theTheDollars theDollars)
					(if (> theDollars 9)
						(= theTheDollars theDollars)
					else
						(= theTheDollars 10)
					)
					(Display 260 7 p_restore saveBits)
					(= saveBits
						(Display
							(Format @str 260 0 theTheDollars)
							p_at 105 36
							p_color global130
							p_save
						)
					)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance increaseButton of Prop
	(properties
		x 146
		y 164
		view 260
		loop 2
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp evt theTime)
		(if (and (self onMe: event) (!= (curRoom script?) spinEm))
			(if
				(or
					(and
						(< 0 (event modifiers?))
						(< (event modifiers?) 5)
					)
					(!= ((theIconBar curIcon?) message?) 2)
				)
				(super handleEvent: event)
			else
				(switch (event type?)
					(mouseDown
						(while (!= ((= evt (Event new:)) type?) mouseUp)
							(= theTime (GetTime))
							(while (< (GetTime) (+ 10 theTime)))
							(evt localize:)
							(if (== theTheDollars 10000)
								(Print 260 17)
								(break)
							)
							(if (< theDollars 10)
								(Print 260 18)
								(break)
							)
							(if (< theDollars (+ (localproc_106e) theTheDollars 1))
								(if (mod theDollars 2)
									(= theTheDollars (- theDollars 1))
								else
									(= theTheDollars theDollars)
								)
								(Display 260 7 p_restore saveBits)
								(= saveBits
									(Display
										(Format @str 260 0 theTheDollars)
										p_at 105 36
										p_color global130
										p_save
									)
								)
								(Print 260 18)
								(break)
							)
							(theMusic3 number: 260 play:)
							(+= theTheDollars (localproc_106e))
							(Display 260 7 108 saveBits)
							(= saveBits
								(Display
									(Format @str 260 0 theTheDollars)
									p_at 105 36
									p_color global130
									p_save
								)
							)
							(evt dispose:)
						)
						(evt dispose:)
						(event claimed: TRUE)
						(return)
					)
					(keyDown
						(if (== (event message?) ENTER)
							(cond 
								((== theTheDollars 10000)
									(Print 260 17)
								)
								(
								(< theDollars (+ (localproc_106e) theTheDollars 1))
									(if (mod theDollars 2)
										(= theTheDollars (- theDollars 1))
									else
										(= theTheDollars theDollars)
									)
									(Display 260 7 p_restore saveBits)
									(= saveBits
										(Display
											(Format @str 260 0 theTheDollars)
											p_at 105 36
											p_color global130
											p_save
										)
									)
									(Print 260 18)
								)
								(else
									(+= theTheDollars (localproc_106e))
									(theMusic3 number: 260 play:)
									(Display 260 7 108 saveBits)
									(= saveBits
										(Display
											(Format @str 260 0 theTheDollars)
											p_at 105 36
											p_color global130
											p_save
										)
									)
								)
							)
							(event claimed: TRUE)
							(return)
						)
					)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (Print 260 16))
			(else  0)
		)
	)
)

(instance decreaseButton of Prop
	(properties
		x 183
		y 164
		view 260
		loop 5
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0 theTime)
		(asm
			pushi    #onMe
			pushi    1
			lsp      event
			self     6
			bnt      code_0e4c
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			push    
			lofsa    spinEm
			ne?     
			bnt      code_0e4c
			pushi    0
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			lt?     
			bnt      code_0cdc
			pprev   
			ldi      4
			lt?     
			bt       code_0cef
code_0cdc:
			pushi    #message
			pushi    0
			pushi    #curIcon
			pushi    0
			lag      theIconBar
			send     4
			send     4
			push    
			ldi      2
			ne?     
			bnt      code_0cfb
code_0cef:
			pushi    #handleEvent
			pushi    1
			lsp      event
			super    Prop,  6
			jmp      code_0e4c
code_0cfb:
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_0dc4
code_0d0a:
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp0
			send     4
			push    
			ldi      2
			ne?     
			bnt      code_0db1
			pushi    0
			callk    GetTime,  0
			sat      theTime
code_0d25:
			pushi    0
			callk    GetTime,  0
			push    
			pushi    10
			lat      theTime
			add     
			lt?     
			bnt      code_0d34
			jmp      code_0d25
code_0d34:
			pushi    #localize
			pushi    0
			lat      temp0
			send     4
			lsl      theTheDollars
			ldi      10
			gt?     
			bnt      code_0d98
			pushi    #number
			pushi    1
			pushi    260
			pushi    39
			pushi    0
			lag      theMusic3
			send     10
			lsl      theTheDollars
			pushi    0
			call     localproc_106e,  0
			sub     
			sal      theTheDollars
			push    
			ldi      10
			lt?     
			bnt      code_0d65
			ldi      10
			sal      theTheDollars
code_0d65:
			pushi    4
			pushi    260
			pushi    7
			pushi    108
			lsl      saveBits
			callk    Display,  8
			pushi    7
			pushi    4
			lea      @str
			push    
			pushi    260
			pushi    0
			lsl      theTheDollars
			callk    Format,  8
			push    
			pushi    100
			pushi    105
			pushi    36
			pushi    102
			lsg      global130
			pushi    107
			callk    Display,  14
			sal      saveBits
			jmp      code_0da7
code_0d98:
			pushi    3
			pushi    260
			pushi    20
			pushi    10
			calle    Printf,  6
			jmp      code_0db1
code_0da7:
			pushi    #dispose
			pushi    0
			lat      temp0
			send     4
			jmp      code_0d0a
code_0db1:
			pushi    #dispose
			pushi    0
			lat      temp0
			send     4
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			ret     
			jmp      code_0e4b
code_0dc4:
			dup     
			ldi      4
			eq?     
			bnt      code_0e4b
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			ldi      13
			eq?     
			bnt      code_0e4b
			lsl      theTheDollars
			ldi      10
			gt?     
			bnt      code_0e35
			lsl      theTheDollars
			pushi    0
			call     localproc_106e,  0
			sub     
			sal      theTheDollars
			push    
			ldi      10
			lt?     
			bnt      code_0df5
			ldi      10
			sal      theTheDollars
code_0df5:
			pushi    #number
			pushi    1
			pushi    260
			pushi    39
			pushi    0
			lag      theMusic3
			send     10
			pushi    4
			pushi    260
			pushi    7
			pushi    108
			lsl      saveBits
			callk    Display,  8
			pushi    7
			pushi    4
			lea      @str
			push    
			pushi    260
			pushi    0
			lsl      theTheDollars
			callk    Format,  8
			push    
			pushi    100
			pushi    105
			pushi    36
			pushi    102
			lsg      global130
			pushi    107
			callk    Display,  14
			sal      saveBits
			jmp      code_0e42
code_0e35:
			pushi    3
			pushi    260
			pushi    21
			pushi    10
			calle    Printf,  6
code_0e42:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			ret     
code_0e4b:
			toss    
code_0e4c:
			ret     
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (Print 260 19))
			(else  0)
		)
	)
)

(instance oddsButton of Prop
	(properties
		x 193
		y 145
		view 260
		loop 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(theMusic3 number: 260 play:)
				(self setCycle: BegLoop self)
			)
			(1 (Print 260 23))
			(else  0)
		)
	)
	
	(method (cue)
		(super cue:)
		(Print 260 22 #width 200 #at -1 30)
	)
)

(instance cashOutButton of Prop
	(properties
		x 135
		y 144
		view 260
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2 (self setCycle: BegLoop self))
			(1 (Print 260 24))
			(else  0)
		)
	)
	
	(method (cue)
		(super cue:)
		(localproc_0f53)
	)
)

(instance wheelLeft of Prop
	(properties
		x 115
		y 92
		view 260
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (Print 260 25))
			(else  0)
		)
	)
	
	(method (cue)
		(super cue:)
		(theMusic3 number: 262 loop: 1 play:)
	)
)

(instance wheelCenter of Prop
	(properties
		x 159
		y 89
		view 260
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (Print 260 25))
			(else  0)
		)
	)
	
	(method (cue)
		(super cue:)
		(theMusic3 number: 262 loop: 1 play:)
	)
)

(instance wheelRight of Prop
	(properties
		x 204
		y 88
		view 260
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (Print 260 25))
			(else  0)
		)
	)
)

(instance circle of Actor
	(properties
		yStep 1
		view 260
		loop 7
		signal (| ignrAct fixedLoop fixedCel)
		illegalBits $0000
		xStep 1
	)
	
	(method (doit)
		(super doit: &rest)
		(if (self isBlocked:)
			(self setMotion: Wander)
		)
	)
)

(instance leftTank of Cage
	(properties
		top 70
		left -10
		bottom 210
		right 47
	)
)

(instance rightTank of Cage
	(properties
		top 65
		left 281
		bottom 230
		right 339
	)
)

(instance jackpotSign of Prop
	(properties
		x 159
		y 84
		view 260
		loop 6
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance stripe0 of Prop
	(properties
		x 129
		y 115
		view 260
		loop 8
		signal ignrAct
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(stripe4 setCel: 0)
		(self setCycle: EndLoop stripe1)
	)
)

(instance stripe1 of Prop
	(properties
		x 147
		y 134
		view 260
		loop 9
		signal ignrAct
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(stripe0 setCel: 0)
		(self setCycle: EndLoop stripe2)
	)
)

(instance stripe2 of Prop
	(properties
		x 163
		y 154
		view 260
		loop 10
		signal ignrAct
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(stripe1 setCel: 0)
		(self setCycle: EndLoop stripe3)
	)
)

(instance stripe3 of Prop
	(properties
		x 180
		y 133
		view 260
		loop 11
		signal ignrAct
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(stripe2 setCel: 0)
		(self setCycle: EndLoop stripe4)
	)
)

(instance stripe4 of Prop
	(properties
		x 195
		y 108
		view 260
		loop 12
		signal ignrAct
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(stripe3 setCel: 0)
		(self setCycle: EndLoop stripe0)
	)
)
