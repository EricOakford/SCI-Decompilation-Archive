;;; Sierra Script 1.0 - (do not remove this comment)
(script# 282)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	lillRead 0
)
(synonyms
	(lil person girl)
)

(local
	local0
	local1
	local2
)
(instance lillRead of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(Load rsFONT 4)
		(= global195 32)
		(if (not (& global118 $0002))
			(Load rsFONT 41)
			(LoadMany 132 29 94 95 96)
			(Load rsSCRIPT 406)
			(Load rsVIEW 642)
		)
		(Lillian init: stopUpd:)
		(self setScript: reading)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said 'play[/doll]') (Print 282 0))
					((Said 'examine/blackboard') (Print 282 1))
					((Said 'get/blackboard') (Print 282 2))
				)
			else
				0
			)
		)
	)
)

(instance reading of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216) (= state -1))
					((not (& global118 $0002))
						(= global118 (| global118 $0002))
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
					((self script?) (= state -1))
				)
				(= cycles 1)
			)
			(1
				(Lillian loop: 8 cycleSpeed: 1 setCycle: Fwd)
				(if (not local1) (++ local1) (Print 282 3 #dispose))
				(if (< (Random 1 100) 51) (= state 4))
				(= seconds (Random 3 12))
			)
			(2
				(cls)
				(Lillian loop: 1 setCycle: End)
				(if (< (Random 1 100) 51) (= state 5))
				(= seconds (Random 3 12))
			)
			(3
				(Lillian loop: 0 setCycle: Fwd)
				(Print 282 4 #dispose)
				(= seconds (Random 3 12))
			)
			(4
				(cls)
				(Lillian loop: 1 cel: 2 setCycle: Beg)
				(cond 
					((< (= local2 (Random 1 100)) 31) (= state 0))
					((> local2 70) (= state 1))
				)
				(= seconds (Random 3 12))
			)
			(5
				(cls)
				(Lillian loop: 7 setCycle: Fwd)
				(if (< (Random 1 100) 51)
					(= state 0)
				else
					(= state 1)
				)
				(= seconds (Random 3 12))
			)
			(6
				(Lillian loop: 4 setCycle: Fwd)
				(= seconds (Random (= state 3) 12))
			)
		)
	)
)

(instance Lillian of Prop
	(properties
		y 141
		x 187
		view 506
		loop 8
	)
	
	(method (handleEvent event)
		(cond 
			(
				(and
					(not (& global207 $0020))
					(or (MousedOn self event 3) (Said 'examine/lil'))
				)
				(= global207 (| global207 $0020))
				(= theTalker 6)
				(event claimed: 1)
				(Say 0 282 5)
			)
			(
				(and
					(& global207 $0020)
					(or (MousedOn self event 3) (Said 'examine/lil'))
				)
				(event claimed: 1)
				(Print 282 6)
			)
			((Said 'tell,ask//*<about') (Print 282 7))
			((Said 'deliver,hold/*')
				(if (and theInvItem haveInvItem)
					(Print 282 8)
				else
					(DontHave)
				)
			)
			((Said '/lil>')
				(cond 
					((Said 'converse')
						(= theTalker 6)
						(switch local0
							(0 (Say 1 282 9))
							(1 (Say 1 282 10))
							(2 (Say 1 282 11))
							(3 (Say 1 282 12))
							(4 (Say 1 282 13))
							(else  (Print 282 14))
						)
						(++ local0)
					)
					((Said 'get') (Print 282 15))
					((Said 'kill') (Print 282 16))
					((Said 'kiss') (Print 282 17))
					((Said 'embrace') (Print 282 18))
				)
			)
		)
	)
)
