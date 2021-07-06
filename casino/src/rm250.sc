;;; Sierra Script 1.0 - (do not remove this comment)
(script# 250)
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
	dollarPlural
)
(procedure (ExitBlackjack)
	(if betBits (Display 250 8 p_restore betBits))
	(if moneyBits (Display 250 8 p_restore moneyBits))
	(theMusic2 number: 266 play:)
	(if modelessDialog
		(modelessDialog dispose:)
	)
	(if hitHouseLimit (Print 250 61))
	(if (> dollarsInMachine 0)
		(Printf 250 62 dollarsInMachine)
	else
		(Print 250 7)
	)
	(ego z: 0 hide:)
	(deck dispose:)
	(= dollars dollarsInMachine)
	(if modelessDialog
		(modelessDialog dispose:)
	)
	(curRoom newRoom: 100)
)

(procedure (localproc_1a52 &tmp temp0)
	(return
		(if debugging
			(return (= temp0 (GetNumber {card #})))
		else
			(= temp0 (Random 0 51))
			(if (== (deck at: temp0) -1)
				(= temp0 (localproc_1a52))
			else
				(deck delete: (deck at: temp0))
				(deck addAfter: (- temp0 1) -1)
				(return temp0)
			)
		)
	)
)

(procedure (localproc_1ab2)
	([local4 0]
		loop: (/ local195 13)
		cel: (mod local195 13)
	)
	(Animate (cast elements?) FALSE)
)

(procedure (localproc_1ad8 param1)
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

(procedure (localproc_1b28 param1 param2)
	(theMusic3 number: 250 play:)
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

(procedure (localproc_1bed)
	(return
		(+
			(* currentBet local197)
			(if local201 (* currentBet local198) else 0)
		)
	)
)

(procedure (localproc_1c04)
	(HandsOn)
	(if
		(>=
			(- (+ (localproc_1bed) (/ currentBet 2)) 1)
			(if (== (mod dollarsInMachine 2) 0)
				dollarsInMachine
			else
				(- dollarsInMachine 1)
			)
		)
		(return 0)
	else
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(Animate (cast elements?) FALSE)
		(return (Print 250 63 #button {Yes} 1 #button {No} 0))
		(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
	)
	(return (HandsOff))
)

(procedure (localproc_1c91 param1)
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
			(= hitHouseLimit 1)
		else
			(= dollarsInMachine (+ dollarsInMachine param1))
		)
	else
		(= dollarsInMachine (+ dollarsInMachine param1))
	)
	(if (< dollarsInMachine 0) (= dollarsInMachine 0))
	(if (> dollarsInMachine 10000)
		(= dollarsInMachine 10000)
		(= hitHouseLimit 1)
	)
	(localproc_1df7 2)
	(return
		(if (and (== egoCardNumber 21) (== local27 1))
			(return (+ param1 (/ param1 2)))
		else
			(return param1)
		)
	)
)

(procedure (localproc_1d62 param1)
	(soundFX number: 260 play:)
	(param1 setCel: 1)
	(Animate (cast elements?) 0)
	(param1 setCel: 0)
	(Animate (cast elements?) 0)
)

(procedure (localproc_1d9d &tmp temp0)
	(return
		(if
		(mod (= temp0 (/ (+ currentBet 100) 50)) 2)
			(return (- temp0 1))
		else
			(return temp0)
		)
	)
)

(procedure (localproc_1dbe)
	(= local29 1)
	(HandsOn)
	(if (>= currentBet dollarsInMachine)
		(if (mod currentBet 2)
			(= currentBet (- dollarsInMachine 1))
		else
			(= currentBet dollarsInMachine)
		)
		(if (< currentBet 10) (= currentBet 10))
		(localproc_1df7 1)
	)
)

(procedure (localproc_1df7 param1)
	(if (OneOf param1 0 1)
		(if betBits (Display 250 8 p_restore betBits))
		(= betBits
			(Display
				(Format @betStr 250 64 currentBet)
				p_at 120 11
				p_color global123
				p_save
			)
		)
	)
	(if (OneOf param1 0 2)
		(if moneyBits (Display 250 8 p_restore moneyBits))
		(= moneyBits
			(Display
				(Format @moneyStr 250 65 dollarsInMachine)
				p_at 165 11
				p_color global123
				p_save
			)
		)
	)
)

(instance rm250 of LLRoom
	(properties
		picture 250
		style FADEOUT
	)
	
	(method (init)
		(LoadMany VIEW 250 251)
		(LoadMany SOUND 250 251 252 266 260)
		(theMusic3 loop: 1 vol: 127 flags: mNOPAUSE)
		(theMusic2 loop: 1 vol: 127 flags: mNOPAUSE)
		(ego init: z: 1000 hide:)
		(super init:)
		(theIconBar
			disable: 3 6 7 4 0 5
			curIcon: (theIconBar at: 2)
		)
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
		(self setScript: startScript)
		(proc0_31)
	)
	
	(method (doit)
		(if (GameIsRestarting) (localproc_1df7 0))
		(cond 
			(script 0)
			((and (>= dollarsInMachine 10000) (== local29 1))
				(if modelessDialog (modelessDialog dispose:))
				(Print 250 5)
				(ExitBlackjack)
			)
			((ego mover?) (ExitBlackjack))
			((mod currentBet 2)
				(if (> currentBet 9) (-- currentBet))
				(localproc_1df7 1)
			)
			(
				(and
					(< dollarsInMachine 10)
					(not (curRoom script?))
					(not (Btst 104))
				)
				(Bset 104)
				(if (== dollarsInMachine 1)
					(= dollarPlural {dollar})
				else
					(= dollarPlural {dollars})
				)
				(if (> dollarsInMachine 0)
					(Printf 250 6 dollarsInMachine dollarPlural)
				else
					(Print 250 7)
				)
				(if betBits (Display 250 8 108 betBits))
				(if moneyBits (Display 250 8 108 moneyBits))
				(= dollars dollarsInMachine)
				(curRoom newRoom: 100)
				(Bclr 104)
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(1
				(if modelessDialog (modelessDialog dispose:))
				(Print 250 0)
				(Print 250 1)
			)
			(4
				(if modelessDialog (modelessDialog dispose:))
				(Print 250 2)
			)
			(2
				(if modelessDialog (modelessDialog dispose:))
				(Print 250 3)
			)
			(6
				(if modelessDialog (modelessDialog dispose:))
				(Print 250 4)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (newRoom)
		(super newRoom: &rest)
		(DontMove waitCursor)
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

(instance deck of List)

(instance theDeal of View
	(properties
		x 29
		y 184
		view 250
		loop 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2 (curRoom setScript: dealEm))
			(1 (Print 250 9))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance startScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 4))
			(1
				(localproc_1df7 0)
				(= local29 1)
				(= cycles 1)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance dealEm of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(cond 
					((== local29 0) (Print 250 10) (self dispose:))
					((== currentBet 0) (Print 250 11) (Print 250 12) (self dispose:))
					((> currentBet dollarsInMachine) (Print 250 13) (self dispose:))
					(else (localproc_1d62 theDeal) (self cue:))
				)
			)
			(1
				(HandsOff)
				(deck release:)
				(= temp0 0)
				(while (< temp0 52)
					(deck addToEnd: temp0)
					(++ temp0)
				)
				(= temp0 0)
				(while (< temp0 6)
					(if (IsObject [local4 temp0]) ([local4 temp0] dispose:))
					(if (IsObject [local10 temp0])
						([local10 temp0] dispose:)
					)
					(if (IsObject [local17 temp0])
						([local17 temp0] dispose:)
					)
					(++ temp0)
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
				(= local199 (mod (= local3 (localproc_1a52)) 13))
				(= egoCardNumber (+ egoCardNumber (localproc_1ad8 1)))
				(localproc_1b28 1 local27)
				(= cycles 10)
			)
			(2
				(= local195 (= local3 (localproc_1a52)))
				(= dealerCardNumber (+ dealerCardNumber (localproc_1ad8 0)))
				((= [local4 local31] (Clone card))
					view: 251
					x: 61
					y: 25
					init:
				)
				(theMusic3 number: 250 play:)
				(= cycles 10)
			)
			(3
				(++ local27)
				(= local200 (mod (= local3 (localproc_1a52)) 13))
				(= egoCardNumber (+ egoCardNumber (localproc_1ad8 1)))
				(localproc_1b28 1 local27)
				(= cycles 10)
			)
			(4
				(++ local30)
				(++ local31)
				(= local196 (= local3 (localproc_1a52)))
				(= dealerCardNumber (+ dealerCardNumber (localproc_1ad8 0)))
				(localproc_1b28 0 local31)
				(= cycles 1)
			)
			(5
				(if (== egoCardNumber 21)
					(if modelessDialog (modelessDialog dispose:))
					(localproc_1ab2)
					(Print 250 14)
					(if (== dealerCardNumber 21)
						(Print 250 15)
						(localproc_1dbe)
						(self dispose:)
					else
						(= dollarsInMachine (+ dollarsInMachine (/ currentBet 2)))
						(localproc_1dbe)
						(curRoom setScript: egoWins)
					)
				else
					(self cue:)
				)
			)
			(6
				(if (== (mod local196 13) 0)
					(cond 
						((localproc_1c04)
							(if (== dealerCardNumber 21)
								(localproc_1ab2)
								(localproc_1c91 0)
								(if modelessDialog (modelessDialog dispose:))
								(Print 250 16)
								(localproc_1dbe)
								(self dispose:)
							else
								(localproc_1c91 (- (/ currentBet 2)))
								(self cue:)
							)
						)
						((== dealerCardNumber 21)
							(localproc_1ab2)
							(if modelessDialog (modelessDialog dispose:))
							(Print 250 16)
							(localproc_1c91 (- currentBet))
							(localproc_1dbe)
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
						(= local200 (mod (= local3 (localproc_1a52)) 13))
						(= egoCardNumber (+ egoCardNumber (localproc_1ad8 1)))
						(localproc_1b28 1 local27)
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
					(= local3 (localproc_1a52))
					(= local2 (+ local2 (localproc_1ad8 2)))
					(localproc_1b28 2 local28)
					(Animate (cast elements?) 0)
					(if modelessDialog (modelessDialog dispose:))
					(Print 250 17)
					(Animate (cast elements?) 0)
					(if (== local2 21)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 18)
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
					(Print 250 18)
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
		view 250
		loop 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if (and local201 local202)
					(curRoom setScript: splitGetsHit)
				else
					(curRoom setScript: egoGetsHit)
				)
			)
			(1 (Print 250 19))
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
						(Print 250 20)
						(HandsOn)
						(self dispose:)
					)
					((and (not local201) (== local26 1))
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 21)
						(HandsOn)
						(self dispose:)
					)
					(else
						(localproc_1d62 hitMe)
						(= local3 (localproc_1a52))
						(= egoCardNumber (+ egoCardNumber (localproc_1ad8 1)))
						(localproc_1b28 1 local27)
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
							(localproc_1ab2)
							(Print 250 22)
							(HandsOff)
							(self setScript: dealerWins)
							(self dispose:)
						)
						(else
							(= local202 1)
							(if modelessDialog (modelessDialog dispose:))
							(= local26 1)
							(= local3 (localproc_1a52))
							(= local2 (+ local2 (localproc_1ad8 2)))
							(localproc_1b28 2 local28)
							(Animate (cast elements?) 0)
							(if modelessDialog (modelessDialog dispose:))
							(Print 250 23)
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
					(Print 250 24)
					(if (not local201)
						(localproc_1ab2)
						(curRoom setScript: egoWins)
						(self dispose:)
					else
						(= local202 1)
						(= local3 (localproc_1a52))
						(= local2 (+ local2 (localproc_1ad8 2)))
						(localproc_1b28 2 local28)
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
						(Print 250 20)
						(HandsOn)
						(self dispose:)
					)
					((== local203 1)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 21)
						(HandsOn)
						(self dispose:)
					)
					(else
						(localproc_1d62 hitMe)
						(= local3 (localproc_1a52))
						(= local2 (+ local2 (localproc_1ad8 2)))
						(localproc_1b28 2 local28)
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
						(= local203 1)
						(localproc_1ab2)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 25)
						(curRoom setScript: splitStands)
						(self dispose:)
					)
				)
				(self cue:)
			)
			(2
				(if (and (== local203 0) (== local28 5))
					(if modelessDialog (modelessDialog dispose:))
					(localproc_1ab2)
					(Print 250 26)
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
		view 250
		loop 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if local201
					(if (not local202)
						(localproc_1d62 stand)
						(= local205 1)
						(= local202 1)
						(= local3 (localproc_1a52))
						(= local2 (+ local2 (localproc_1ad8 2)))
						(localproc_1b28 2 local28)
						(Animate (cast elements?) 0)
						(if (and (== local2 21) (== local28 1))
							(if modelessDialog (modelessDialog dispose:))
							(localproc_1ab2)
							(Print 250 18)
							(curRoom setScript: splitStands)
						)
					else
						(curRoom setScript: splitStands)
					)
				else
					(curRoom setScript: egoStands)
				)
			)
			(1 (Print 250 27))
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
					(Print 250 20)
					(HandsOn)
					(self dispose:)
				else
					(if (not local197) (localproc_1d62 stand))
					(localproc_1ab2)
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
					(= local3 (localproc_1a52))
					(= dealerCardNumber (+ dealerCardNumber (localproc_1ad8 0)))
					(localproc_1b28 0 local30)
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
						(Print 250 21)
						(curRoom setScript: dealerWins)
					)
					((> dealerCardNumber 21)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 28)
						(self setScript: egoWins)
						(self dispose:)
					)
					((== dealerCardNumber egoCardNumber)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 29)
					)
					((> dealerCardNumber egoCardNumber) (HandsOff) (self setScript: dealerWins) (self dispose:))
					(else (self setScript: egoWins) (self dispose:))
				)
				(localproc_1dbe)
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
					(Print 250 20)
					(HandsOn)
					(self dispose:)
				else
					(if (not local205)
						(localproc_1d62 stand)
						(= local205 0)
					)
					(localproc_1ab2)
					(self cue:)
				)
			)
			(1
				(++ local30)
				(++ local31)
				(if
				(or (< dealerCardNumber 17) (and local23 (== dealerCardNumber 17)))
					(= local3 (localproc_1a52))
					(= dealerCardNumber (+ dealerCardNumber (localproc_1ad8 0)))
					(localproc_1b28 0 local30)
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
						(Print 250 28)
						(if (not local26)
							(if modelessDialog (modelessDialog dispose:))
							(Print 250 30)
							(localproc_1c91 (* currentBet local197))
						)
						(if (not local203)
							(if modelessDialog (modelessDialog dispose:))
							(Print 250 31)
							(localproc_1c91 (* currentBet local198))
						)
						(localproc_1dbe)
						(self dispose:)
					)
					((and (== local27 5) (not local26))
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 30)
						(localproc_1c91 (* currentBet local197))
					)
					((== dealerCardNumber egoCardNumber)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 32)
					)
					((> dealerCardNumber egoCardNumber)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 33)
						(localproc_1c91 (- (* currentBet local197)))
					)
					((and (== egoCardNumber 21) (== local27 1))
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 30)
						(localproc_1c91 (+ currentBet (/ currentBet 2)))
					)
					((not local26)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 30)
						(localproc_1c91 (* currentBet local197))
					)
					(else
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 34)
						(localproc_1c91 (- (* currentBet local197)))
					)
				)
				(self cue:)
			)
			(4
				(cond 
					((and (== local28 5) (not local203))
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 31)
						(localproc_1c91 (* currentBet local198))
					)
					((== dealerCardNumber local2)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 35)
					)
					((> dealerCardNumber local2)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 36)
						(localproc_1c91 (- (* currentBet local198)))
						(localproc_1dbe)
						(self dispose:)
					)
					((and (== local2 21) (== local28 1))
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 31)
						(localproc_1c91 (+ currentBet (/ currentBet 2)))
					)
					((not local203)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 31)
						(localproc_1c91 (* currentBet local198))
					)
					(else
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 37)
						(localproc_1c91 (- (* currentBet local198)))
					)
				)
				(self cue:)
			)
			(5
				(localproc_1dbe)
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
				(if (> dollarsInMachine 10)
					(Printf
						250
						38
						(localproc_1c91 (* currentBet local197))
						22
						3
						111
					)
				else
					(Printf
						250
						39
						(localproc_1c91 (* currentBet local197))
					)
				)
				(localproc_1dbe)
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
				(localproc_1c91 (- (* currentBet local197)))
				(if (> dollarsInMachine 10)
					(Print 250 40 #time 3 #dispose)
				else
					(Print 250 40)
				)
				(localproc_1dbe)
				(self dispose:)
			)
		)
	)
)

(instance increase of View
	(properties
		x 158
		y 161
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
						(!= ((theIconBar curIcon?) message?) 2)
					)
					(super handleEvent: event)
				)
				((== local29 0)
					(if modelessDialog (modelessDialog dispose:))
					(Print 250 42)
					(event claimed: 1)
					(return)
				)
				(else
					(switch (event type?)
						(mouseDown
							(while (!= ((= newEvent (Event new:)) type?) mouseUp)
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
								(if
								(< dollarsInMachine (+ (localproc_1d9d) currentBet 1))
									(if (mod dollarsInMachine 2)
										(= currentBet (- dollarsInMachine 1))
									else
										(= currentBet dollarsInMachine)
									)
									(localproc_1df7 1)
									(if modelessDialog (modelessDialog dispose:))
									(Print 250 13)
									(break)
								)
								(cond 
									((< currentBet 0)
										(if modelessDialog (modelessDialog dispose:))
										(Print 250 44)
									)
									((mod currentBet 2)
										(if modelessDialog (modelessDialog dispose:))
										(localproc_1df7 1)
									)
									(else
										(= currentBet (+ currentBet (localproc_1d9d)))
										(localproc_1df7 1)
									)
								)
								(newEvent dispose:)
							)
							(newEvent dispose:)
							(event claimed: 1)
							(return)
						)
						(keyDown
							(if (== (event message?) ENTER)
								(cond 
									((== currentBet 10000)
										(if modelessDialog (modelessDialog dispose:))
										(Print 250 43)
									)
									((< dollarsInMachine (+ (localproc_1d9d) currentBet 1))
										(if (mod dollarsInMachine 2)
											(= currentBet (- dollarsInMachine 1))
										else
											(= currentBet dollarsInMachine)
										)
										(localproc_1df7 1)
										(if modelessDialog (modelessDialog dispose:))
										(Print 250 13)
									)
									((< currentBet 0)
										(if modelessDialog (modelessDialog dispose:))
										(Print 250 44)
									)
									((mod currentBet 2)
										(if modelessDialog (modelessDialog dispose:))
										(-- currentBet)
										(localproc_1df7 1)
									)
									(else
										(= currentBet (+ currentBet (localproc_1d9d)))
										(localproc_1df7 1)
									)
								)
								(event claimed: 1)
								(return)
							)
						)
					)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (Print 250 41))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance decrease of View
	(properties
		x 157
		y 186
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
			bnt      code_1680
			pushi    0
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			lt?     
			bnt      code_1555
			pprev   
			ldi      4
			lt?     
			bt       code_1569
code_1555:
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
			bnt      code_1575
code_1569:
			pushi    #handleEvent
			pushi    1
			lsp      event
			super    View,  6
			jmp      code_1680
code_1575:
			lsl      local29
			ldi      0
			eq?     
			bnt      code_159d
			lag      modelessDialog
			bnt      code_1587
			pushi    #dispose
			pushi    0
			send     4
code_1587:
			pushi    2
			pushi    250
			pushi    46
			calle    Print,  4
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			ret     
			jmp      code_1680
code_159d:
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_162f
code_15ac:
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
			bnt      code_161c
			pushi    0
			callk    GetTime,  0
			sat      temp1
code_15c7:
			pushi    0
			callk    GetTime,  0
			push    
			pushi    12
			lat      temp1
			add     
			lt?     
			bnt      code_15d6
			jmp      code_15c7
code_15d6:
			pushi    #localize
			pushi    0
			lat      temp0
			send     4
			lsl      currentBet
			ldi      11
			gt?     
			bnt      code_15f9
			lsl      currentBet
			pushi    0
			call     localproc_1d9d,  0
			sub     
			sal      currentBet
			pushi    1
			pushi    1
			call     localproc_1df7,  2
			jmp      code_1613
code_15f9:
			lag      modelessDialog
			bnt      code_1603
			pushi    #dispose
			pushi    0
			send     4
code_1603:
			pushi    3
			pushi    250
			pushi    47
			pushi    10
			calle    Printf,  6
			jmp      code_161c
code_1613:
			pushi    #dispose
			pushi    0
			lat      temp0
			send     4
			jmp      code_15ac
code_161c:
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
			jmp      code_167f
code_162f:
			dup     
			ldi      4
			eq?     
			bnt      code_167f
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			ldi      13
			eq?     
			bnt      code_167f
			lsl      currentBet
			ldi      11
			gt?     
			bnt      code_165f
			lsl      currentBet
			pushi    0
			call     localproc_1d9d,  0
			sub     
			sal      currentBet
			pushi    1
			pushi    1
			call     localproc_1df7,  2
			jmp      code_1676
code_165f:
			lag      modelessDialog
			bnt      code_1669
			pushi    #dispose
			pushi    0
			send     4
code_1669:
			pushi    3
			pushi    250
			pushi    47
			pushi    10
			calle    Printf,  6
code_1676:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			ret     
code_167f:
			toss    
code_1680:
			ret     
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (Print 250 45))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance changeBet of View
	(properties
		x 157
		y 174
		view 250
		loop 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (Print 250 48))
			(2 (Print 250 48))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance surrender of View
	(properties
		x 27
		y 76
		view 250
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(curRoom setScript: surrenderScript)
			)
			(1 (Print 250 49))
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
		view 250
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (Print 250 50))
			(2
				(cond 
					(
						(>
							(+ (localproc_1bed) currentBet)
							(if (== (mod dollarsInMachine 2) 0)
								dollarsInMachine
							else
								(- dollarsInMachine 1)
							)
						)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 51)
					)
					((== local29 1)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 20)
					)
					(local201 (Print 250 52))
					(
						(and
							(< 9 (- egoCardNumber (* local24 10)))
							(< (- egoCardNumber (* local24 10)) 12)
							(!= local202 1)
						)
						(localproc_1d62 self)
						(= local197 2)
						(++ local27)
						(= local3 (localproc_1a52))
						(= egoCardNumber (+ egoCardNumber (localproc_1ad8 1)))
						(localproc_1b28 1 local27)
						(curRoom setScript: egoStands)
					)
					(else
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 53)
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
		view 250
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (Print 250 54))
			(2
				(if
					(and
						(== local200 local199)
						(not local29)
						(not local201)
					)
					(localproc_1d62 theSplit)
					(HandsOff)
					(= local201 1)
					(dealEm start: 7)
					(curRoom setScript: dealEm)
				else
					(self doVerb: 1)
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
						(Print 250 20)
					)
					((and (== local27 1) (not local201))
						(localproc_1d62 surrender)
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 55)
						(localproc_1c91 (- (/ currentBet 2)))
						(localproc_1dbe)
					)
					(else
						(if modelessDialog (modelessDialog dispose:))
						(Print 250 56)
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
		view 250
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (Print 250 57))
			(2
				(localproc_1d62 odds)
				(if modelessDialog (modelessDialog dispose:))
				(Print 250 58 #at 15 -1 #width 280 #title {HOUSE RULES})
				(Print 250 59 #at 15 -1 #width 280 #title {HOUSE RULES})
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
		view 250
		loop 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (Print 250 60))
			(2
				(if (== local29 0)
					(if modelessDialog (modelessDialog dispose:))
					(Print 250 46)
				else
					(localproc_1d62 cashout)
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
		flags $0001
	)
)
