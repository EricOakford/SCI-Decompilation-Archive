;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmSlots) ;260
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
	currentBet
	betBits
	moneyBits
	[moneyStr 80]
	dollarsInMachine
	[local84 7]
	local91
	hitHouseLimit
)
(procedure (ExitMachine)
	(= dollars dollarsInMachine)
	(globalSound number: 266 loop: 1 play:)
	(curRoom newRoom: rmInsideCasino)
)

(procedure (WinMoney winnings)
	(if (> winnings 0)
		(if (< (+ dollarsInMachine winnings) 0)
			(= dollarsInMachine 10000)
		else
			(= dollarsInMachine (+ dollarsInMachine winnings))
		)
	else
		(= dollarsInMachine 10000)
	)
	(if (< dollarsInMachine 0) (= dollarsInMachine 0))
	(if (> dollarsInMachine 10000)
		(= dollarsInMachine 10000)
		(= hitHouseLimit TRUE)
	)
	(Display 260 20 108 moneyBits)
	(= moneyBits
		(Display
			(Format @moneyStr 260 1 dollarsInMachine)
			p_at 165 34
			p_color myTextColor8
			p_save
		)
	)
)

(procedure (localproc_130d param1)
	(soundFx number: 260 play:)
	(param1 setCel: 1)
	(Animate (cast elements?) FALSE)
	(param1 setCel: 0)
	(Animate (cast elements?) FALSE)
	(param1 stopUpd:)
)

(procedure (localproc_134f &tmp temp0)
	(return
		(if
		(mod (= temp0 (/ (+ currentBet 100) 50)) 2)
			(return (- temp0 1))
		else
			(return temp0)
		)
	)
)

(instance rm260 of Room
	(properties
		picture rmSlots
		style HSHUTTER
	)
	
	(method (init &tmp temp0)
		(Load SCRIPT RANDCYC)
		(Load VIEW 260)
		(LoadMany SOUND 260 261 262 263 264 265 266)
		(globalSound loop: 1 vol: 127 flags: mNOPAUSE)
		(soundFx loop: 1 vol: 127 flags: mNOPAUSE)
		(ego init: z: 1000 hide:)
		(super init:)
		(playButton init: stopUpd:)
		(increaseButton init: stopUpd:)
		(decreaseButton init: stopUpd:)
		(oddsButton init: stopUpd:)
		(cashOutButton init: stopUpd:)
		(wheelLeft cycleSpeed: 0 init: stopUpd:)
		(wheelCenter cycleSpeed: 0 init: stopUpd:)
		(wheelRight cycleSpeed: 0 init: stopUpd:)
		(= currentBet 10)
		(= dollarsInMachine dollars)
		(= betBits
			(Display
				(Format @moneyStr 260 0 currentBet)
				p_at 105 36
				p_color myTextColor8
				p_save
			)
		)
		(= moneyBits
			(Display
				(Format @moneyStr 260 1 dollarsInMachine)
				p_at 165 34
				p_color myTextColor8
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
	)
	
	(method (doit)
		(if (GameIsRestarting)
			(= betBits
				(Display
					(Format @moneyStr 260 0 currentBet)
					p_at
					105
					36
					p_color
					myTextColor8
					p_save
				)
			)
			(= moneyBits
				(Display
					(Format @moneyStr 260 1 dollarsInMachine)
					p_at
					165
					34
					p_color
					myTextColor8
					p_save
				)
			)
		)
		(cond 
			(script)
			((>= dollarsInMachine 10000) (HandsOff) (curRoom setScript: sWin))
			((ego mover?) (ExitMachine))
		)
		(super doit:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2 (Print 260 2) (Print 260 3))
			(5 (Print 260 4))
			(3 (Print 260 5))
			(10 (Print 260 6))
			(11 (Print 260 7))
			(4
				(switch theItem
					(11
						(Print 260 8)
						(Print 260 9)
					)
					(14
						(Print 260 10)
						(Print 260 11 #at -1 140)
						(ShowDeathIcon 103 0 1)
						(Format @str1 260 12)
						(EgoDead 260 13)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance sWin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(soundFx number: 265 play: self)
				(Print 260 14 #dispose)
			)
			(1
				(if modelessDialog (modelessDialog dispose:))
				(if hitHouseLimit (Print 260 15))
				(ExitMachine)
			)
		)
	)
)

(instance playButton of Prop
	(properties
		x 164
		y 189
		description {the Play button}
		lookStr {Click the Hand here to begin play.}
		view 260
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(cond 
					((< dollarsInMachine currentBet) (Print 260 16))
					((== currentBet 0) (Print 260 17) (Print 260 18) (Print 260 19 #at -1 140))
					(else
						(localproc_130d playButton)
						(HandsOff)
						(curRoom setScript: spinEm)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance spinEm of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalSound setLoop: -1 number: 261 play:)
				(HandsOff)
				(Display 260 20 p_restore moneyBits)
				(= moneyBits
					(Display
						(Format @moneyStr 260 1 (= dollarsInMachine (- dollarsInMachine currentBet)))
						p_at 165 34
						p_color myTextColor8
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
				(globalSound number: 262 setLoop: 1 play:)
				(soundFx setLoop: -1)
				(cond 
					((== (wheelLeft cel?) 0)
						(if (== (wheelCenter cel?) 0)
							(if (== (wheelRight cel?) 0)
								(soundFx number: 264 play:)
								(if (> currentBet 8000)
									(WinMoney 10000)
								else
									(WinMoney (* 4 currentBet))
								)
								(= seconds 5)
							else
								(soundFx number: 263 play:)
								(WinMoney (* 2 currentBet))
								(= seconds 3)
							)
						else
							(soundFx number: 263 play:)
							(WinMoney currentBet)
							(= seconds 2)
						)
					)
					(
						(and
							(== (wheelLeft cel?) (wheelCenter cel?))
							(== (wheelCenter cel?) 2)
						)
						(if (== (wheelRight cel?) 2)
							(soundFx number: 264 play:)
							(if (> currentBet 8000)
								(WinMoney 10000)
							else
								(WinMoney (* 4 currentBet))
							)
							(= seconds 5)
						else
							(soundFx number: 263 play:)
							(WinMoney (* 2 currentBet))
							(= seconds 3)
						)
					)
					(
						(and
							(== (wheelLeft cel?) (wheelCenter cel?))
							(== (wheelCenter cel?) 4)
						)
						(if (== (wheelRight cel?) 4)
							(soundFx number: 264 play:)
							(if (> currentBet 6400)
								(WinMoney 10000)
							else
								(WinMoney (* 5 currentBet))
							)
							(= seconds 6)
						else
							(soundFx number: 263 play:)
							(WinMoney (* 3 currentBet))
							(= seconds 4)
						)
					)
					(
						(and
							(== (wheelLeft cel?) (wheelCenter cel?))
							(== (wheelCenter cel?) (wheelRight cel?))
							(== (wheelRight cel?) 6)
						)
						(soundFx number: 264 play: self)
						(jackpotSign cycleSpeed: howFast init: setCycle: Forward)
						(if (> currentBet 4500)
							(WinMoney 10000)
						else
							(WinMoney (* 7 currentBet))
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
				(soundFx setLoop: 1 stop:)
				(if (IsObject jackpotSign) (jackpotSign dispose:))
				(if (> currentBet dollarsInMachine)
					(if (> dollarsInMachine 9)
						(= currentBet dollarsInMachine)
					else
						(= currentBet 10)
					)
					(Display 260 20 108 betBits)
					(= betBits
						(Display
							(Format @moneyStr 260 0 currentBet)
							p_at 105 36
							p_color myTextColor8
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
		description {the Increase button}
		lookStr {Click the Hand here to increase the amount of your bet.}
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
	
	(method (handleEvent event &tmp newEvent temp1)
		(if
		(and (self onMe: event) (!= (curRoom script?) spinEm))
			(if
				(or
					(and
						(< 0 (event modifiers?))
						(< (event modifiers?) 5)
					)
					(!= ((theIconBar curIcon?) message?) 3)
				)
				(super handleEvent: event)
			else
				(switch (event type?)
					(mouseDown
						(while (!= ((= newEvent (Event new:)) type?) 2)
							(= temp1 (GetTime))
							(while (< (GetTime) (+ 10 temp1))
							)
							(newEvent localize:)
							(if (== currentBet 10000) (Print 260 21) (break))
							(if (< dollarsInMachine 10) (Print 260 22) (break))
							(if
							(< dollarsInMachine (+ (localproc_134f) currentBet 1))
								(if (mod dollarsInMachine 2)
									(= currentBet (- dollarsInMachine 1))
								else
									(= currentBet dollarsInMachine)
								)
								(Display 260 20 p_restore betBits)
								(= betBits
									(Display
										(Format @moneyStr 260 0 currentBet)
										p_at 105 36
										p_color myTextColor8
										p_save
									)
								)
								(Print 260 22)
								(break)
							)
							(soundFx number: 260 play:)
							(= currentBet (+ currentBet (localproc_134f)))
							(Display 260 20 108 betBits)
							(= betBits
								(Display
									(Format @moneyStr 260 0 currentBet)
									p_at 105 36
									p_color myTextColor8
									p_save
								)
							)
							(newEvent dispose:)
						)
						(newEvent dispose:)
						(event claimed: TRUE)
						(return)
					)
					(keyDown
						(if (== (event message?) ENTER)
							(cond 
								((== currentBet 10000) (Print 260 21))
								(
								(< dollarsInMachine (+ (localproc_134f) currentBet 1))
									(if (mod dollarsInMachine 2)
										(= currentBet (- dollarsInMachine 1))
									else
										(= currentBet dollarsInMachine)
									)
									(Display 260 20 108 betBits)
									(= betBits
										(Display
											(Format @moneyStr 260 0 currentBet)
											p_at 105 36
											p_color myTextColor8
											p_save
										)
									)
									(Print 260 22)
								)
								(else
									(= currentBet (+ currentBet (localproc_134f)))
									(soundFx number: 260 play:)
									(Display 260 20 108 betBits)
									(= betBits
										(Display
											(Format @moneyStr 260 0 currentBet)
											p_at 105 36
											p_color myTextColor8
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
)

(instance decreaseButton of Prop
	(properties
		x 183
		y 164
		description {the Decrease button}
		lookStr {Click the Hand here to decrease the amount of your bet.}
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
	
	(method (handleEvent event &tmp temp0 temp1)
		(asm
			pushi    #onMe
			pushi    1
			lsp      event
			self     6
			bnt      code_0f21
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			push    
			lofsa    spinEm
			ne?     
			bnt      code_0f21
			pushi    0
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			lt?     
			bnt      code_0daa
			pprev   
			ldi      4
			lt?     
			bt       code_0dbe
code_0daa:
			pushi    #message
			pushi    0
			pushi    #curIcon
			pushi    0
			lag      theIconBar
			send     4
			send     4
			push    
			ldi      3
			ne?     
			bnt      code_0dc9
code_0dbe:
			pushi    #handleEvent
			pushi    1
			lsp      event
			super    Prop,  6
			jmp      code_0f21
code_0dc9:
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_0e97
code_0dd8:
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
			bnt      code_0e84
			pushi    0
			callk    GetTime,  0
			sat      temp1
code_0df3:
			pushi    0
			callk    GetTime,  0
			push    
			pushi    10
			lat      temp1
			add     
			lt?     
			bnt      code_0e04
			jmp      code_0df3
code_0e04:
			pushi    #localize
			pushi    0
			lat      temp0
			send     4
			lsl      currentBet
			ldi      10
			gt?     
			bnt      code_0e6a
			pushi    #number
			pushi    1
			pushi    260
			pushi    42
			pushi    0
			lag      soundFx
			send     10
			lsl      currentBet
			pushi    0
			call     localproc_134f,  0
			sub     
			sal      currentBet
			push    
			ldi      10
			lt?     
			bnt      code_0e36
			ldi      10
			sal      currentBet
code_0e36:
			pushi    4
			pushi    260
			pushi    20
			pushi    108
			lsl      betBits
			callk    Display,  8
			pushi    7
			pushi    4
			lea      @moneyStr
			push    
			pushi    260
			pushi    0
			lsl      currentBet
			callk    Format,  8
			push    
			pushi    100
			pushi    105
			pushi    36
			pushi    102
			lsg      myTextColor8
			pushi    107
			callk    Display,  14
			sal      betBits
			jmp      code_0e7a
code_0e6a:
			pushi    3
			pushi    260
			pushi    23
			pushi    10
			calle    Printf,  6
			jmp      code_0e84
code_0e7a:
			pushi    #dispose
			pushi    0
			lat      temp0
			send     4
			jmp      code_0dd8
code_0e84:
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
			jmp      code_0f20
code_0e97:
			dup     
			ldi      4
			eq?     
			bnt      code_0f20
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			ldi      13
			eq?     
			bnt      code_0f20
			lsl      currentBet
			ldi      10
			gt?     
			bnt      code_0f0a
			lsl      currentBet
			pushi    0
			call     localproc_134f,  0
			sub     
			sal      currentBet
			push    
			ldi      10
			lt?     
			bnt      code_0ec9
			ldi      10
			sal      currentBet
code_0ec9:
			pushi    #number
			pushi    1
			pushi    260
			pushi    42
			pushi    0
			lag      soundFx
			send     10
			pushi    4
			pushi    260
			pushi    20
			pushi    108
			lsl      betBits
			callk    Display,  8
			pushi    7
			pushi    4
			lea      @moneyStr
			push    
			pushi    260
			pushi    0
			lsl      currentBet
			callk    Format,  8
			push    
			pushi    100
			pushi    105
			pushi    36
			pushi    102
			lsg      myTextColor8
			pushi    107
			callk    Display,  14
			sal      betBits
			jmp      code_0f17
code_0f0a:
			pushi    3
			pushi    260
			pushi    24
			pushi    10
			calle    Printf,  6
code_0f17:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			ret     
code_0f20:
			toss    
code_0f21:
			ret     
		)
	)
)

(instance oddsButton of Prop
	(properties
		x 193
		y 145
		description {the Odds button}
		lookStr {Click the Hand here to learn this machine's odds.}
		view 260
		loop 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(soundFx number: 260 play:)
				(self setCycle: BegLoop self)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(Print 260 25 #width 200 #at -1 30)
	)
)

(instance cashOutButton of Prop
	(properties
		x 135
		y 144
		description {the Cash Out button}
		lookStr {Click the Hand here to stop playing. All the money you've deposited in the machine will be returned to you.}
		view 260
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(self setCycle: BegLoop self)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(ExitMachine)
	)
)

(instance wheelLeft of Prop
	(properties
		x 115
		y 92
		description {the left tumbler}
		lookStr {You hope this will soon match the other two.}
		view 260
	)
	
	(method (cue)
		(super cue:)
		(soundFx number: 262 loop: 1 play:)
	)
)

(instance wheelCenter of Prop
	(properties
		x 159
		y 89
		description {the center tumbler}
		lookStr {You hope this will soon match the other two.}
		view 260
	)
	
	(method (cue)
		(super cue:)
		(soundFx number: 262 loop: 1 play:)
	)
)

(instance wheelRight of Prop
	(properties
		x 204
		y 88
		description {the right tumbler}
		lookStr {You hope this will soon match the other two.}
		view 260
	)
)

(instance circle of Actor
	(properties
		description {those cute little circles}
		lookStr {You must be bored to be staring at these things!}
		yStep 1
		view 260
		loop 7
		signal (| ignrAct fixedLoop fixedCel)
		illegalBits $0000
		xStep 1
	)
	
	(method (doit)
		(super doit: &rest)
		(if (self isBlocked:) (self setMotion: Wander))
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
		description {the light}
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
		description {the light}
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
		description {the light}
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
		description {the light}
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
		description {the light}
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
