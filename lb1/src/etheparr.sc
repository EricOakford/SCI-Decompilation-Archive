;;; Sierra Script 1.0 - (do not remove this comment)
(script# 238)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	etheparr 0
)
(synonyms
	(ethel person girl)
)

(local
	local0
	local1
)
(procedure (localproc_000c)
	(Ethel loop: 1 setCycle: Fwd)
	(Print
		&rest
		#at
		80
		145
		#font
		4
		#width
		125
		#mode
		1
		#draw
		#dispose
	)
)

(procedure (localproc_003a)
	(Print
		&rest
		#at
		171
		145
		#font
		4
		#width
		125
		#mode
		1
		#draw
		#dispose
	)
)

(instance etheparr of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(Load rsFONT 4)
		(= global195 8)
		(Ethel init: setScript: ethelParrot)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return 1))
		(if
		(and (== (event type?) evSAID) (Said '/drink,glass>'))
			(cond 
				((Said 'examine') (Print 238 0))
				((Said 'get') (Print 238 1))
			)
		)
		(return (super handleEvent: event))
	)
)

(instance ethelParrot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Ethel loop: 0 setCycle: End self)
			)
			(1
				(User canInput: 0)
				(if local1
					(switch local1
						(1 (localproc_000c 238 2))
						(2 (localproc_000c 238 3))
						(3 (localproc_003a 238 4))
						(4 (localproc_000c 238 5))
						(else  (localproc_000c 238 6))
					)
				else
					(localproc_000c 238 7)
				)
				(= seconds 5)
			)
			(2
				(Ethel setCycle: 0)
				(if local1
					(switch local1
						(1 (localproc_003a 238 8))
						(2 (localproc_003a 238 9))
						(3 (localproc_000c 238 10))
						(4 (localproc_003a 238 11))
						(else  (localproc_003a 238 12))
					)
				else
					(localproc_003a 238 13)
				)
				(++ local1)
				(= seconds 3)
			)
			(3
				(User canInput: 1)
				(cls)
				(Ethel loop: 0 cel: 3 setCycle: Beg)
				(if (< (Random 1 100) 20) (= state 6))
				(= seconds 10)
			)
			(4
				(Ethel loop: 2 cel: 0 cycleSpeed: 2 setCycle: End)
				(= seconds 3)
			)
			(5 (Ethel setCycle: Beg self))
			(6
				(if (< (Random 1 100) 20) (= state -1))
				(= seconds 10)
			)
			(7
				(Ethel loop: 3 setCycle: End)
				(if (< (Random 1 100) 20) (= state -1))
				(= seconds (Random 6 12))
			)
			(8
				(Ethel loop: 4 cel: 0 setCycle: End)
				(= seconds (Random 3 6))
			)
			(9
				(Ethel loop: 5 cel: 0 setCycle: End)
				(if (< (Random 1 100) 20)
					(= state -1)
				else
					(= state 3)
				)
				(= seconds (Random 6 12))
			)
		)
	)
)

(instance Ethel of Act
	(properties
		y 102
		x 168
		view 329
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(= theTalker 4)
		(cond 
			(
				(or
					(Said 'ask,tell/parrot>')
					(Said 'hold,deliver/*/parrot>')
					(Said 'hold,deliver/*<parrot>')
				)
			)
			(
			(or (MousedOn self event 3) (Said 'examine/ethel'))
				(if (not (& global207 $0008))
					(= global207 (| global207 $0008))
					(Say 0 238 14)
				else
					(Print 238 15)
				)
				(event claimed: 1)
			)
			((Said 'ask,tell//*<about')
				(switch (Random 1 8)
					(1 (Say 1 238 16))
					(2 (Say 1 238 17))
					(3 (Say 1 238 18))
					(4 (Print 238 19))
					(5 (Say 1 238 20))
					(6 (Say 1 238 21))
					(7 (Say 1 238 22))
					(else  (Print 238 23))
				)
			)
			(
			(or (Said 'deliver/*<ethel') (Said 'deliver/*[/ethel]'))
				(if (and theInvItem haveInvItem)
					(Print 238 24)
				else
					(DontHave)
				)
			)
			(
			(or (Said 'hold/*<ethel') (Said 'hold/*[/ethel]'))
				(if (and theInvItem haveInvItem)
					(Print 238 25)
				else
					(DontHave)
				)
			)
			((Said '/ethel>')
				(cond 
					((Said 'converse')
						(switch local0
							(0 (Print 238 26))
							(1
								(Say 1 238 27)
								(Say 1 238 28)
							)
							(2
								(Say 1 238 29)
								(= theTalker 12)
								(Say 1 238 30)
								(= theTalker 4)
								(Say 1 238 31)
							)
							(3
								(Say 1 238 32)
								(Say 1 238 33)
								(Print 238 34)
							)
							(4
								(Say 1 238 35)
								(= theTalker 12)
								(Say 1 238 36)
							)
							(else  (Print 238 37))
						)
						(++ local0)
					)
					((Said 'hear') (Print 238 38))
					((Said 'get') (Print 238 39))
					((Said 'kill') (Print 238 40))
					((Said 'kiss') (Print 238 41))
					((Said 'embrace') (Print 238 42))
				)
			)
		)
	)
)
