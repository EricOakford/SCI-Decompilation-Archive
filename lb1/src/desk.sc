;;; Sierra Script 1.0 - (do not remove this comment)
(script# 276)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	desk 0
)
(synonyms
	(attorney person fellow)
)

(local
	local0
	local1
)
(instance Clarence of Prop
	(properties
		y 138
		x 267
		view 411
	)
	
	(method (handleEvent event)
		(cond 
			(
				(and
					(not (& global207 $0040))
					(or (MousedOn self event 3) (Said 'examine/attorney'))
				)
				(= global207 (| global207 $0040))
				(= theTalker 7)
				(event claimed: 1)
				(Say 0 276 0)
			)
			(
				(and
					(& global207 $0040)
					(or (MousedOn self event 3) (Said 'examine/attorney'))
				)
				(event claimed: 1)
				(Print 276 1)
			)
		)
	)
)

(instance cHead of Prop
	(properties
		y 99
		x 268
		view 411
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			(
				(and
					(not (& global207 $0040))
					(or (MousedOn self event 3) (Said 'examine/attorney'))
				)
				(= global207 (| global207 $0040))
				(= theTalker 7)
				(event claimed: 1)
				(Say 0 276 0)
			)
			(
				(and
					(& global207 $0040)
					(or (MousedOn self event 3) (Said 'examine/attorney'))
				)
				(event claimed: 1)
				(Print 276 1)
			)
		)
	)
)

(instance desk of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(Load rsFONT 4)
		(= global195 64)
		(cHead setPri: 10 init:)
		(Clarence init:)
		(self setScript: noteBook)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if (== (event type?) evSAID)
			(= theTalker 7)
			(cond 
				((Said 'examine>')
					(cond 
						((Said '/attorney') (Print 276 1))
						((Said '/desk') (Print 276 2))
						((Said '<in/notebook') (Print 276 3))
						((Said '/notebook') (Print 276 4))
					)
				)
				((Said 'ask//*<about')
					(switch local1
						(0 (Say 1 276 5) (++ local1))
						(1 (Print 276 6))
					)
				)
				((Said 'tell//*<about') (Print 276 7))
				((Said 'hold/*')
					(if (and theInvItem haveInvItem)
						(Print 276 8)
					else
						(DontHave)
					)
				)
				((Said 'deliver/*')
					(if (and theInvItem haveInvItem)
						(Print 276 9)
					else
						(DontHave)
					)
				)
				((Said '/attorney>')
					(cond 
						((Said 'converse')
							(switch local0
								(0 (Say 1 276 10))
								(1 (Say 1 276 11))
								(2 (Say 1 276 12))
								(else  (Print 276 13))
							)
							(++ local0)
						)
						((Said 'hear') (Print 276 14))
						((Said 'get') (Print 276 15))
						((Said 'kill') (Print 276 16))
						((Said 'kiss') (Print 276 17))
						((Said 'embrace') (Print 276 18))
						((Said 'flirt') (Print 276 19))
					)
				)
				((Said 'get,open/notebook') (Print 276 20))
				((Said 'read/notebook') (Print 276 3))
			)
		)
	)
)

(instance noteBook of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216) (= state -1))
					((not (& global118 $0008))
						(= global118 (| global118 $0008))
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
					((self script?) (= state -1))
				)
				(= cycles 1)
			)
			(1
				(cHead loop: 5 cel: 0 cycleSpeed: 3 setCycle: End)
				(Clarence cycleSpeed: 1 setCycle: Fwd)
				(= seconds (Random 5 12))
			)
			(2
				(Clarence setCycle: 0)
				(cHead setCycle: Beg)
				(= seconds (Random 3 5))
			)
			(3
				(cHead loop: 1 cel: 0 setCycle: End)
				(= seconds (Random 3 5))
			)
			(4
				(cHead setCycle: Beg)
				(= seconds (Random 3 5))
			)
			(5
				(switch (Random 1 2)
					(1 (= state 0))
					(2 (= state 2))
				)
				(= seconds (Random 3 5))
			)
		)
	)
)
