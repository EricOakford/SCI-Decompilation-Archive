;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmBlackjack) ;250
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use LoadMany)
(use Sound)
(use Actor)
(use System)

(public
	rm250 0
)

(local
	dealerCardNumber
	egoCardNumber
	local2
	local3
	[local4 6]
	[local10 7]
	[local17 6]
	local23
	local24
	local25
	local26
	local27
	local28
	local29
	local30
	local31
	currentBet
	betBits
	moneyBits
	[betStr 80]
	[moneyStr 80]
	local195
	local196
	local197
	local198
	local199
	local200
	local201
	local202
	local203
	dollarsInMachine
	local205
	hitHouseLimit
)
(procedure (ExitBlackjack)
	(globalSound number: 266 play:)
	(if modelessDialog (modelessDialog dispose:))
	(if hitHouseLimit (Print 250 57))
	(Printf 250 58 dollarsInMachine)
	(ego z: 0 hide:)
	(deck dispose:)
	(= dollars dollarsInMachine)
	(cast eachElementDo: #dispose)
	(if modelessDialog (modelessDialog dispose:))
	(curRoom newRoom: rmInsideCasino)
)

(procedure (localproc_20e8 &tmp ret)
	(return
		(if debugging
			(return (= ret (GetNumber {card #})))
		else
			(= ret (Random 0 51))
			(if (== (deck at: ret) -1)
				(= ret (localproc_20e8))
			else
				(deck delete: (deck at: ret))
				(deck addAfter: (- ret 1) -1)
				(return ret)
			)
		)
	)
)

(procedure (localproc_2148)
	([local4 0]
		loop: (/ local195 13)
		cel: (mod local195 13)
	)
	(Animate (cast elements?) 0)
)

(procedure (localproc_216e param1)
	(return
		(cond 
			((== (mod local3 13) 0)
				(switch param1
					(0 (++ local23))
					(1 (++ local24))
					(2 (++ local25))
				)
				11
			)
			((< (mod local3 13) 9) (+ (mod local3 13) 1))
			(else 10)
		)
	)
)

(procedure (localproc_21be param1 param2)
	(soundFx number: 250 play:)
	(switch param1
		(0
			((= [local4 local31] (Clone card))
				loop: (/ local3 13)
				cel: (mod local3 13)
				x: (+ 61 (* param2 33))
				y: 25
				init:
			)
		)
		(1
			((= [local10 local27] (Clone card))
				loop: (/ local3 13)
				cel: (mod local3 13)
				x: (+ 61 (* param2 33))
				y: 105
				init:
			)
		)
		(2
			((= [local17 local28] (Clone card))
				loop: (/ local3 13)
				cel: (mod local3 13)
				x: (+ 61 (* param2 33))
				y: 66
				init:
			)
		)
	)
)

(procedure (localproc_2289)
	(return
		(+
			(* currentBet local197)
			(if local201 (* currentBet local198) else 0)
		)
	)
)

(procedure (localproc_22a0)
	(HandsOn)
	(if
		(>=
			(- (+ (localproc_2289) (/ currentBet 2)) 1)
			(if (== (mod dollarsInMachine 2) 0)
				dollarsInMachine
			else
				(- dollarsInMachine 1)
			)
		)
		(return FALSE)
	else
		(if modelessDialog (modelessDialog dispose:))
		(theGame setCursor: ARROW_CURSOR TRUE)
		(Animate (cast elements?) FALSE)
		(return (Print 250 59 #button {Yes} 1 #button {No} 0))
		(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
	)
	(return (HandsOff))
)

(procedure (localproc_232e param1)
	(if (> param1 0)
		(if
			(or
				(and (== egoCardNumber 21) (== local27 1))
				(and (== local2 21) (== local28 1))
			)
			(soundFX number: 253 play:)
		else
			(soundFX number: 252 play:)
		)
	)
	(if (< param1 0) (soundFX number: 251 play:))
	(if (> param1 0)
		(if (< (+ dollarsInMachine param1) 0)
			(= dollarsInMachine 10000)
			(= hitHouseLimit TRUE)
		else
			(= dollarsInMachine (+ dollarsInMachine param1))
		)
	else
		(= dollarsInMachine (+ dollarsInMachine param1))
	)
	(if (< dollarsInMachine 0) (= dollarsInMachine 0))
	(if (> dollarsInMachine 10000)
		(= dollarsInMachine 10000)
		(= hitHouseLimit TRUE)
	)
	(Display 250 45 p_restore moneyBits)
	(= moneyBits
		(Display
			(Format @moneyStr 250 12 dollarsInMachine)
			p_at 165 11
			p_color
			myTextColor
			p_save
		)
	)
	(return
		(if (and (== egoCardNumber 21) (== local27 1))
			(return (+ param1 (/ param1 2)))
		else
			(return param1)
		)
	)
)

(procedure (localproc_242c param1)
	(soundFX number: 260 play:)
	(param1 setCel: 1)
	(Animate (cast elements?) FALSE)
	(param1 setCel: 0)
	(Animate (cast elements?) FALSE)
)

(procedure (localproc_2467 &tmp temp0)
	(return
		(if
		(mod (= temp0 (/ (+ currentBet 100) 50)) 2)
			(return (- temp0 1))
		else
			(return temp0)
		)
	)
)

(procedure (DIsplayBet)
	(= local29 1)
	(HandsOn)
	(if (>= currentBet dollarsInMachine)
		(if (mod currentBet 2)
			(= currentBet (- dollarsInMachine 1))
		else
			(= currentBet dollarsInMachine)
		)
		(if (< currentBet 10) (= currentBet 10))
		(Display 250 45 p_restore betBits)
		(= betBits
			(Display
				(Format @betStr 250 0 currentBet)
				p_at 120 11
				p_color myTextColor
				p_save
			)
		)
	)
)

(instance rm250 of LLRoom
	(properties
		picture rmBlackjack
		style HSHUTTER
	)
	
	(method (init)
		(LoadMany VIEW 250 251)
		(LoadMany SOUND 250 251 252 266 260)
		(soundFx loop: 1 vol: 127 flags: mNOPAUSE)
		(globalSound loop: 1 vol: 127 flags: mNOPAUSE)
		(ego init: z: 1000 hide:)
		(super init:)
		(theDouble init: stopUpd:)
		(theSplit init: stopUpd:)
		(theDeal init: stopUpd:)
		(hitMe init: stopUpd:)
		(stand init: stopUpd:)
		(changeBet init: stopUpd:)
		(surrender init: stopUpd:)
		(odds init: stopUpd:)
		(cashout init: stopUpd:)
		(increase init: stopUpd:)
		(decrease init: stopUpd:)
		(= currentBet 10)
		(= dollarsInMachine dollars)
		(= betBits
			(Display
				(Format @betStr 250 0 currentBet)
				p_at 120 11
				p_color myTextColor
				p_save
			)
		)
		(= moneyBits
			(Display
				(Format @moneyStr 250 0 dollarsInMachine)
				p_at 165 11
				p_color myTextColor
				p_save
			)
		)
		(= local29 1)
	)
	
	(method (doit)
		(if (GameIsRestarting)
			(= betBits
				(Display
					(Format @betStr 250 12 currentBet)
					p_at 120 11
					p_color myTextColor
					p_save
				)
			)
			(= moneyBits
				(Display
					(Format @moneyStr 250 12 dollarsInMachine)
					p_at 165 11
					p_color myTextColor
					p_save
				)
			)
		)
		(cond 
			(script)
			((and (>= dollarsInMachine 10000) (== local29 1))
				(if modelessDialog (modelessDialog dispose:))
				(Print 250 13)
				(ExitBlackjack)
			)
			((ego mover?) (ExitBlackjack))
		)
		(super doit:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if modelessDialog (modelessDialog dispose:))
				(Print 250 1)
				(Print 250 2)
			)
			(verbTalk
				(if modelessDialog (modelessDialog dispose:))
				(Print 250 3)
			)
			(verbDo
				(if modelessDialog (modelessDialog dispose:))
				(Print 250 4)
			)
			(verbZipper
				(if modelessDialog (modelessDialog dispose:))
				(Print 250 5)
			)
			(verbTaste
				(if modelessDialog (modelessDialog dispose:))
				(Print 250 6)
			)
			(verbUse
				(switch theItem
					(iPocketKnife
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 7)
					)
					(iHammer
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 8)
						(Print 250 9)
						(ShowDeathIcon 103 0 1)
						(Format @str1 250 10)
						(EgoDead 250 11)
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

(instance card of View
	(properties
		view 251
		loop 4
		cel 4
		priority 14
		signal fixPriOn
	)
)

(instance deck of List
	(properties)
)

(instance theDeal of View
	(properties
		x 29
		y 184
		description {the Deal button}
		lookStr {Click the Hand here to begin a new hand of blackjack.}
		view 250
		loop 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(curRoom setScript: dealEm)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance dealEm of Script
	(properties)
	
	(method (changeState newState &tmp i)
		(switch (= state newState)
			(0
				(if modelessDialog (modelessDialog dispose:))
				(cond 
					((== local29 0) (Print 250 14) (self dispose:))
					((== currentBet 0) (Print 250 15) (Print 250 16) (self dispose:))
					((> currentBet dollarsInMachine) (Print 250 17) (self dispose:))
					(else (localproc_242c theDeal) (self cue:))
				)
			)
			(1
				(HandsOff)
				(deck release:)
				(= i 0)
				(while (< i 52)
					(deck addToEnd: i)
					(++ i)
				)
				(= i 0)
				(while (< i 6)
					(if (IsObject [local4 i]) ([local4 i] dispose:))
					(if (IsObject [local10 i])
						([local10 i] dispose:)
					)
					(if (IsObject [local17 i])
						([local17 i] dispose:)
					)
					(++ i)
				)
				(= egoCardNumber 0)
				(= local24 0)
				(= local26 0)
				(= local2 0)
				(= local25 0)
				(= local203 0)
				(= dealerCardNumber 0)
				(= local23 0)
				(= local27 0)
				(= local28 1)
				(= local30 0)
				(= local31 0)
				(= local197 1)
				(= local198 1)
				(= local29 0)
				(= local202 0)
				(= local201 0)
				(= local199 (mod (= local3 (localproc_20e8)) 13))
				(= egoCardNumber (+ egoCardNumber (localproc_216e 1)))
				(localproc_21be 1 local27)
				(= cycles 10)
			)
			(2
				(= local195 (= local3 (localproc_20e8)))
				(= dealerCardNumber (+ dealerCardNumber (localproc_216e 0)))
				((= [local4 local31] (Clone card))
					view: 251
					x: 61
					y: 25
					init:
				)
				(soundFx number: 250 play:)
				(= cycles 10)
			)
			(3
				(++ local27)
				(= local200 (mod (= local3 (localproc_20e8)) 13))
				(= egoCardNumber (+ egoCardNumber (localproc_216e 1)))
				(localproc_21be 1 local27)
				(= cycles 10)
			)
			(4
				(++ local30)
				(++ local31)
				(= local196 (= local3 (localproc_20e8)))
				(= dealerCardNumber (+ dealerCardNumber (localproc_216e 0)))
				(localproc_21be 0 local31)
				(= cycles 1)
			)
			(5
				(if (== egoCardNumber 21)
					(if modelessDialog (modelessDialog dispose:))
					(Print 250 18)
					(localproc_2148)
					(if (== dealerCardNumber 21)
						(Print 250 19)
						(DIsplayBet)
						(self dispose:)
					else
						(= dollarsInMachine (+ dollarsInMachine (/ currentBet 2)))
						(DIsplayBet)
						(curRoom setScript: egoWins)
					)
				else
					(self cue:)
				)
			)
			(6
				(if (== (mod local196 13) 0)
					(cond 
						((localproc_22a0)
							(if (== dealerCardNumber 21)
								(localproc_2148)
								(localproc_232e 0)
								(if modelessDialog (modelessDialog dispose:))
								(Print 250 20)
								(DIsplayBet)
								(self dispose:)
							else
								(localproc_232e (- (/ currentBet 2)))
								(self cue:)
							)
						)
						((== dealerCardNumber 21)
							(localproc_2148)
							(if modelessDialog (modelessDialog dispose:))
							(Print 250 20)
							(localproc_232e (- currentBet))
							(DIsplayBet)
							(self dispose:)
						)
						(else (self cue:))
					)
				else
					(self cue:)
				)
			)
			(7
				(self start: 0)
				(if (== local200 local199)
					(if
						(and
							(<= (* 2 currentBet) dollarsInMachine)
							(not local201)
						)
						(HandsOn)
						(self dispose:)
					)
					(if (== local201 1)
						(if (== local199 0)
							(= local24 1)
							(= local25 1)
							(= egoCardNumber 11)
							(= local2 11)
						else
							(= local2 (= egoCardNumber (/ egoCardNumber 2)))
						)
						(= [local17 0] (Clone [local10 1]))
						([local10 1] dispose:)
						([local17 0] x: 61 y: 66 init:)
						(= local200 (mod (= local3 (localproc_20e8)) 13))
						(= egoCardNumber (+ egoCardNumber (localproc_216e 1)))
						(localproc_21be 1 local27)
						(self cue:)
					else
						(self cue:)
					)
				else
					(self cue:)
				)
			)
			(8
				(if (== egoCardNumber 21)
					(if modelessDialog (modelessDialog dispose:))
					(Print 250 21)
					(= local3 (localproc_20e8))
					(= local2 (+ local2 (localproc_216e 2)))
					(localproc_21be 2 local28)
					(Animate (cast elements?) FALSE)
					(if (== local2 21)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 22)
						(curRoom setScript: splitStands)
						(self dispose:)
					else
						(HandsOn)
						(= local202 1)
						(self dispose:)
					)
				else
					(self cue:)
				)
			)
			(9
				(if (== egoCardNumber 22) (= egoCardNumber 12) (-- local24))
				(if (== local2 21)
					(if modelessDialog (modelessDialog dispose:))
					(Print 250 22)
					(curRoom setScript: splitStands)
					(self dispose:)
				else
					(HandsOn)
					(self dispose:)
				)
			)
		)
	)
)

(instance hitMe of View
	(properties
		x 100
		y 186
		description {the Hit button}
		lookStr {Click the Hand here to get another card.}
		view 250
		loop 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(if (and local201 local202)
					(curRoom setScript: splitGetsHit)
				else
					(curRoom setScript: egoGetsHit)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance egoGetsHit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(++ local27)
				(cond 
					((== local29 1)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 23)
						(HandsOn)
						(self dispose:)
					)
					((and (not local201) (== local26 1))
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 24)
						(HandsOn)
						(self dispose:)
					)
					(else
						(localproc_242c hitMe)
						(= local3 (localproc_20e8))
						(= egoCardNumber (+ egoCardNumber (localproc_216e 1)))
						(localproc_21be 1 local27)
						(= cycles 1)
					)
				)
			)
			(1
				(if (> egoCardNumber 21)
					(cond 
						(local24 (-- local24) (= egoCardNumber (- egoCardNumber 10)))
						((not local201)
							(if modelessDialog (modelessDialog dispose:))
							(Print 250 25)
							(localproc_2148)
							(HandsOff)
							(self setScript: dealerWins)
							(self dispose:)
						)
						(else
							(= local202 1)
							(if modelessDialog (modelessDialog dispose:))
							(Print 250 26)
							(= local26 1)
							(= local3 (localproc_20e8))
							(= local2 (+ local2 (localproc_216e 2)))
							(localproc_21be 2 local28)
							(Animate (cast elements?) FALSE)
							(= local202 1)
							(HandsOn)
							(self dispose:)
						)
					)
				)
				(self cue:)
			)
			(2
				(if (and (== local26 0) (== local27 5))
					(if modelessDialog (modelessDialog dispose:))
					(Print 250 27)
					(if (not local201)
						(localproc_2148)
						(curRoom setScript: egoWins)
						(self dispose:)
					else
						(= local202 1)
						(= local3 (localproc_20e8))
						(= local2 (+ local2 (localproc_216e 2)))
						(localproc_21be 2 local28)
						(HandsOn)
						(self dispose:)
					)
				else
					(HandsOn)
					(self dispose:)
				)
			)
		)
	)
)

(instance splitGetsHit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(++ local28)
				(cond 
					((== local29 1)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 23)
						(HandsOn)
						(self dispose:)
					)
					((== local203 1)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 24)
						(HandsOn)
						(self dispose:)
					)
					(else
						(localproc_242c hitMe)
						(= local3 (localproc_20e8))
						(= local2 (+ local2 (localproc_216e 2)))
						(localproc_21be 2 local28)
						(= cycles 1)
					)
				)
			)
			(1
				(if (> local2 21)
					(if local25
						(-- local25)
						(= local2 (- local2 10))
					else
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 28)
						(= local203 1)
						(localproc_2148)
						(curRoom setScript: splitStands)
						(self dispose:)
					)
				)
				(self cue:)
			)
			(2
				(if (and (== local203 0) (== local28 5))
					(if modelessDialog (modelessDialog dispose:))
					(Print 250 29)
					(localproc_2148)
					(curRoom setScript: splitStands)
					(self dispose:)
				else
					(HandsOn)
					(self dispose:)
				)
			)
		)
	)
)

(instance stand of View
	(properties
		x 219
		y 184
		description {the Stand button}
		lookStr {Click the Hand here when you want no more cards.}
		view 250
		loop 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(if local201
					(if (not local202)
						(localproc_242c stand)
						(= local205 1)
						(= local202 1)
						(= local3 (localproc_20e8))
						(= local2 (+ local2 (localproc_216e 2)))
						(localproc_21be 2 local28)
						(Animate (cast elements?) FALSE)
						(if (and (== local2 21) (== local28 1))
							(if modelessDialog (modelessDialog dispose:))
							(Print 250 22)
							(localproc_2148)
							(curRoom setScript: splitStands)
						)
					else
						(curRoom setScript: splitStands)
					)
				else
					(curRoom setScript: egoStands)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance egoStands of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== local29 1)
					(if modelessDialog (modelessDialog dispose:))
					(Print 250 23)
					(HandsOn)
					(self dispose:)
				else
					(if (not local197) (localproc_242c stand))
					(localproc_2148)
					(if (and (> egoCardNumber 21) local24)
						(-- local24)
						(= egoCardNumber (- egoCardNumber 10))
					)
				)
				(self cue:)
			)
			(1
				(++ local30)
				(++ local31)
				(if
				(or (< dealerCardNumber 17) (and local23 (== dealerCardNumber 17)))
					(= local3 (localproc_20e8))
					(= dealerCardNumber (+ dealerCardNumber (localproc_216e 0)))
					(localproc_21be 0 local30)
					(self changeState: 1)
				else
					(= cycles 1)
				)
			)
			(2
				(if (> dealerCardNumber 21)
					(if local23
						(-- local23)
						(= dealerCardNumber (- dealerCardNumber 10))
						(-- local30)
						(self changeState: 1)
					else
						(= cycles 1)
					)
				)
				(= cycles 1)
			)
			(3
				(cond 
					((> egoCardNumber 21)
						(if modelessDialog (modelessDialog dispose:))
						(HandsOff)
						(Print 250 24)
						(curRoom setScript: dealerWins)
					)
					((> dealerCardNumber 21)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 30)
						(self setScript: egoWins)
						(self dispose:)
					)
					((== dealerCardNumber egoCardNumber)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 31)
					)
					((> dealerCardNumber egoCardNumber) (HandsOff) (self setScript: dealerWins) (self dispose:))
					(else (self setScript: egoWins) (self dispose:))
				)
				(DIsplayBet)
				(self dispose:)
			)
		)
	)
)

(instance splitStands of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== local29 1)
					(= local205 0)
					(if modelessDialog (modelessDialog dispose:))
					(Print 250 23)
					(HandsOn)
					(self dispose:)
				else
					(if (not local205)
						(localproc_242c stand)
						(= local205 0)
					)
					(localproc_2148)
					(self cue:)
				)
			)
			(1
				(++ local30)
				(++ local31)
				(if
				(or (< dealerCardNumber 17) (and local23 (== dealerCardNumber 17)))
					(= local3 (localproc_20e8))
					(= dealerCardNumber (+ dealerCardNumber (localproc_216e 0)))
					(localproc_21be 0 local30)
					(self changeState: 1)
				else
					(= cycles 1)
				)
			)
			(2
				(if (> dealerCardNumber 21)
					(if local23
						(-- local23)
						(= dealerCardNumber (- dealerCardNumber 10))
						(-- local30)
						(self changeState: 1)
					else
						(self cue:)
					)
				)
				(self cue:)
			)
			(3
				(cond 
					((> dealerCardNumber 21)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 30)
						(if (not local26)
							(if modelessDialog (modelessDialog dispose:))
							(Print 250 32)
							(localproc_232e (* currentBet local197))
						)
						(if (not local203)
							(if modelessDialog (modelessDialog dispose:))
							(Print 250 33)
							(localproc_232e (* currentBet local198))
						)
						(DIsplayBet)
						(self dispose:)
					)
					((and (== local27 5) (not local26))
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 32)
						(localproc_232e (* currentBet local197))
					)
					((== dealerCardNumber egoCardNumber)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 34)
					)
					((> dealerCardNumber egoCardNumber)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 35)
						(localproc_232e (- (* currentBet local197)))
					)
					((and (== egoCardNumber 21) (== local27 1))
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 32)
						(localproc_232e (+ currentBet (/ currentBet 2)))
					)
					((not local26)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 32)
						(localproc_232e (* currentBet local197))
					)
					(else
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 36)
						(localproc_232e (- (* currentBet local197)))
					)
				)
				(self cue:)
			)
			(4
				(cond 
					((and (== local28 5) (not local203))
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 33)
						(localproc_232e (* currentBet local198))
					)
					((== dealerCardNumber local2)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 37)
					)
					((> dealerCardNumber local2)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 38)
						(localproc_232e (- (* currentBet local198)))
						(DIsplayBet)
						(self dispose:)
					)
					((and (== local2 21) (== local28 1))
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 33)
						(localproc_232e (+ currentBet (/ currentBet 2)))
					)
					((not local203)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 33)
						(localproc_232e (* currentBet local198))
					)
					(else
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 39)
						(localproc_232e (- (* currentBet local198)))
					)
				)
				(self cue:)
			)
			(5
				(DIsplayBet)
				(self dispose:)
			)
		)
	)
)

(instance egoWins of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if modelessDialog (modelessDialog dispose:))
				(Printf 250 40
					(localproc_232e (* currentBet local197))
					25 3 105
				)
				(DIsplayBet)
				(self dispose:)
			)
		)
	)
)

(instance dealerWins of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if modelessDialog (modelessDialog dispose:))
				(localproc_232e (- (* currentBet local197)))
				(Print 250 41 #time 3 #dispose)
				(DIsplayBet)
				(self dispose:)
			)
		)
	)
)

(instance increase of View
	(properties
		x 158
		y 161
		description {the Increase button}
		lookStr {Click the Hand here to increase your bet. This button repeats automatically if you hold it down.}
		view 250
		loop 8
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
	
	(method (handleEvent event &tmp newEvent temp1 temp2)
		(if (self onMe: event)
			(cond 
				(
					(or
						(and
							(< 0 (event modifiers?))
							(< (event modifiers?) 5)
						)
						(!= ((theIconBar curIcon?) message?) 3)
					)
					(super handleEvent: event)
				)
				((== local29 0)
					(if modelessDialog (modelessDialog dispose:))
					(Print 250 42)
					(event claimed: TRUE)
					(return)
				)
				(else
					(switch (event type?)
						(mouseDown
							(while (!= ((= newEvent (Event new:)) type?) 2)
								(= temp1 (GetTime))
								(if temp2 (while (< (GetTime) (+ 7 temp1))
								))
								(= temp2 1)
								(newEvent localize:)
								(if (== currentBet 10000)
									(if modelessDialog (modelessDialog dispose:))
									(Print 250 43)
									(break)
								)
								(if (< dollarsInMachine 10) (Print 250 44) (break))
								(if
								(< dollarsInMachine (+ (localproc_2467) currentBet 1))
									(if (mod dollarsInMachine 2)
										(= currentBet (- dollarsInMachine 1))
									else
										(= currentBet dollarsInMachine)
									)
									(Display 250 45 108 betBits)
									(= betBits
										(Display
											(Format @betStr 250 0 currentBet)
											p_at
											120
											11
											p_color
											myTextColor
											p_save
										)
									)
									(if modelessDialog (modelessDialog dispose:))
									(Print 250 17)
									(break)
								)
								(cond 
									((< currentBet 0)
										(if modelessDialog (modelessDialog dispose:))
										(Print 250 46)
									)
									((mod currentBet 2)
										(if modelessDialog (modelessDialog dispose:))
										(Print 250 47)
										(-- currentBet)
										(Display 250 45 108 betBits)
										(= betBits
											(Display
												(Format @betStr 250 0 currentBet)
												p_at 120 11
												p_color myTextColor
												p_save
											)
										)
									)
									(else
										(= currentBet (+ currentBet (localproc_2467)))
										(Display 250 45 108 betBits)
										(= betBits
											(Display
												(Format @betStr 250 0 currentBet)
												p_at 120 11
												p_color myTextColor
												p_save
											)
										)
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
									((== currentBet 10000)
										(if modelessDialog (modelessDialog dispose:))
										(Print 250 43)
									)
									(
									(< dollarsInMachine (+ (localproc_2467) currentBet 1))
										(if (mod dollarsInMachine 2)
											(= currentBet (- dollarsInMachine 1))
										else
											(= currentBet dollarsInMachine)
										)
										(Display 250 45 108 betBits)
										(= betBits
											(Display
												(Format @betStr 250 0 currentBet)
												p_at 120 11
												p_color myTextColor
												p_save
											)
										)
										(if modelessDialog (modelessDialog dispose:))
										(Print 250 17)
									)
									((< currentBet 0)
										(if modelessDialog (modelessDialog dispose:))
										(Print 250 46)
									)
									((mod currentBet 2)
										(if modelessDialog (modelessDialog dispose:))
										(Print 250 47)
										(-- currentBet)
										(Display 250 45 108 betBits)
										(= betBits
											(Display
												(Format @betStr 250 0 currentBet)
												p_at 120 11
												p_color
												myTextColor
												p_save
											)
										)
									)
									(else
										(= currentBet (+ currentBet (localproc_2467)))
										(Display 250 45 108 betBits)
										(= betBits
											(Display
												(Format @betStr 250 0 currentBet)
												p_at 120 11
												p_color myTextColor
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
)

(instance decrease of View
	(properties
		x 157
		y 186
		description {the Decrease button}
		lookStr {Click the Hand here to decrease your bet. This button repeats automatically if you hold it down.}
		view 250
		loop 9
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
			bnt      code_1aeb
			pushi    0
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			lt?     
			bnt      code_1968
			pprev   
			ldi      4
			lt?     
			bt       code_197c
code_1968:
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
			bnt      code_1987
code_197c:
			pushi    #handleEvent
			pushi    1
			lsp      event
			super    View,  6
			jmp      code_1aeb
code_1987:
			lsl      local29
			ldi      0
			eq?     
			bnt      code_19af
			lag      modelessDialog
			bnt      code_1999
			pushi    #dispose
			pushi    0
			send     4
code_1999:
			pushi    2
			pushi    250
			pushi    48
			calle    Print,  4
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			ret     
			jmp      code_1aeb
code_19af:
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_1a6f
code_19be:
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
			bnt      code_1a5c
			pushi    0
			callk    GetTime,  0
			sat      temp1
code_19d9:
			pushi    0
			callk    GetTime,  0
			push    
			pushi    7
			lat      temp1
			add     
			lt?     
			bnt      code_19ea
			jmp      code_19d9
code_19ea:
			pushi    #localize
			pushi    0
			lat      temp0
			send     4
			lsl      currentBet
			ldi      11
			gt?     
			bnt      code_1a38
			lsl      currentBet
			pushi    0
			call     localproc_2467,  0
			sub     
			sal      currentBet
			pushi    4
			pushi    250
			pushi    45
			pushi    108
			lsl      betBits
			callk    Display,  8
			pushi    7
			pushi    4
			lea      @betStr
			push    
			pushi    250
			pushi    0
			lsl      currentBet
			callk    Format,  8
			push    
			pushi    100
			pushi    120
			pushi    11
			pushi    102
			lsg      myTextColor
			pushi    107
			callk    Display,  14
			sal      betBits
			jmp      code_1a52
code_1a38:
			lag      modelessDialog
			bnt      code_1a42
			pushi    #dispose
			pushi    0
			send     4
code_1a42:
			pushi    3
			pushi    250
			pushi    49
			pushi    10
			calle    Printf,  6
			jmp      code_1a5c
code_1a52:
			pushi    #dispose
			pushi    0
			lat      temp0
			send     4
			jmp      code_19be
code_1a5c:
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
			jmp      code_1aea
code_1a6f:
			dup     
			ldi      4
			eq?     
			bnt      code_1aea
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			ldi      13
			eq?     
			bnt      code_1aea
			lsl      currentBet
			ldi      11
			gt?     
			bnt      code_1aca
			lsl      currentBet
			pushi    0
			call     localproc_2467,  0
			sub     
			sal      currentBet
			pushi    4
			pushi    250
			pushi    45
			pushi    108
			lsl      betBits
			callk    Display,  8
			pushi    7
			pushi    4
			lea      @betStr
			push    
			pushi    250
			pushi    0
			lsl      currentBet
			callk    Format,  8
			push    
			pushi    100
			pushi    120
			pushi    11
			pushi    102
			lsg      myTextColor
			pushi    107
			callk    Display,  14
			sal      betBits
			jmp      code_1ae1
code_1aca:
			lag      modelessDialog
			bnt      code_1ad4
			pushi    #dispose
			pushi    0
			send     4
code_1ad4:
			pushi    3
			pushi    250
			pushi    49
			pushi    10
			calle    Printf,  6
code_1ae1:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			ret     
code_1aea:
			toss    
code_1aeb:
			ret     
		)
	)
)

(instance changeBet of View
	(properties
		x 157
		y 174
		description {the Change Bet button}
		lookStr {Click the two arrows to increase or decrease your bet.}
		view 250
		loop 10
	)
)

(instance surrender of View
	(properties
		x 27
		y 76
		description {the Surrender button}
		lookStr {Click the Hand here to surrender your cards and have half your bet refunded.}
		view 250
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(curRoom setScript: surrenderScript)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theDouble of View
	(properties
		x 291
		y 72
		description {the Double Down button}
		lookStr {Click the Hand only here when you have two cards totaling 10 or 11. You will automatically receive only one more card and double your bet.}
		view 250
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(cond 
					(
						(>
							(+ (localproc_2289) currentBet)
							(if (== (mod dollarsInMachine 2) 0)
								dollarsInMachine
							else
								(- dollarsInMachine 1)
							)
						)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 50)
					)
					((== local29 1)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 23)
					)
					(local201 (Print 250 51))
					(
						(and
							(< 9 (- egoCardNumber (* local24 10)))
							(< (- egoCardNumber (* local24 10)) 12)
							(!= local202 1)
						)
						(localproc_242c self)
						(= local197 2)
						(++ local27)
						(= local3 (localproc_20e8))
						(= egoCardNumber (+ egoCardNumber (localproc_216e 1)))
						(localproc_21be 1 local27)
						(curRoom setScript: egoStands)
					)
					(else
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 52)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theSplit of View
	(properties
		x 292
		y 104
		description {the Split button}
		lookStr {Click the Hand here when you have only two matching cards and you want to split them into two hands, doubling your bet.}
		view 250
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(if
					(and
						(== local200 local199)
						(not local29)
						(not local201)
					)
					(localproc_242c theSplit)
					(HandsOff)
					(= local201 1)
					(dealEm start: 7)
					(curRoom setScript: dealEm)
				else
					(self doVerb: verbLook)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance surrenderScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((== local29 1)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 23)
					)
					((and (== local27 1) (not local201))
						(localproc_242c surrender)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 53)
						(localproc_232e (- (/ currentBet 2)))
						(DIsplayBet)
					)
					(else
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 54)
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance odds of View
	(properties
		x 26
		y 106
		description {the Odds button}
		lookStr {Click the Hand here to learn this machine's current odds.}
		view 250
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(localproc_242c odds)
				(if modelessDialog (modelessDialog dispose:))
				(Print 250 55 #at 15 -1 #width 280 #title {HOUSE RULES})
				(Print 250 56 #at 15 -1 #width 280 #title {HOUSE RULES})
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cashout of View
	(properties
		x 284
		y 185
		description {the Cash Out button}
		lookStr {Click the Hand here to stop playing this machine. Your winnings will be given to you automatically.}
		view 250
		loop 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(if (== local29 0)
					(if modelessDialog (modelessDialog dispose:))
					(Print 250 48)
				else
					(localproc_242c cashout)
					(ExitBlackjack)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance soundFX of Sound
	(properties
		flags mNOPAUSE
	)
)
