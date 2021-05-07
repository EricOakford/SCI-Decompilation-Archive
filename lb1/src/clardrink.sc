;;; Sierra Script 1.0 - (do not remove this comment)
(script# 272)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	clardrink 0
)
(synonyms
	(attorney person fellow)
)

(local
	local0
	local1
	local2
)
(instance clardrink of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(Load rsFONT 41)
		(Load rsVIEW 642)
		(LoadMany 132 29 94 95 96)
		(LoadMany 143 243 297 406)
		(LoadMany 142 7 12)
		(= global208 64)
		(= [global377 6] 297)
		(ClarAss init: stopUpd:)
		(Clarence illegalBits: 0 setPri: 6 init:)
		(Smoke setPri: 6 init: hide:)
		(self setScript: clarActions)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
	)
)

(instance clarActions of Script
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
				(Clarence setLoop: 1 cel: 0 setCycle: End self)
				(= local1 (Random 1 3))
				(= local2 0)
			)
			(2 (= seconds 2))
			(3
				(Clarence setLoop: 4 cel: 1)
				(= cycles 1)
			)
			(4
				(Smoke cel: 0 setCycle: End self show:)
			)
			(5
				(if (< local2 local1)
					(++ local2)
					(Clarence cel: 0)
					(= cycles (= state 1))
				else
					(Smoke cel: 0 hide:)
					(Clarence setLoop: 5)
					(Clarence
						cel: (- (NumCels Clarence) 3)
						cycleSpeed: 2
						setCycle: Beg
					)
					(= seconds (Random 3 6))
				)
			)
			(6
				(Clarence setLoop: 2)
				(Clarence cel: (- (NumCels Clarence) 1))
				(= cycles 1)
			)
			(7
				(Clarence setCycle: Beg)
				(switch (Random 1 4)
					(1 (= state 0))
					(3 (= state 8))
				)
				(= seconds (Random 3 6))
			)
			(8
				(Clarence setCycle: End)
				(= seconds (Random 3 (= state 6)))
			)
			(9
				(Clarence setLoop: 9 cel: 0 setCycle: End)
				(= seconds (Random 1 2))
			)
			(10
				(Clarence setCycle: Beg)
				(= seconds (Random 3 6))
				(if (< seconds 5) (= state 0) else (= state 5))
			)
		)
	)
)

(instance Clarence of Act
	(properties
		y 74
		x 171
		view 401
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine/glass,drink') (Print 272 0))
			((Said 'get/drink,glass,alcohol') (Print 272 1))
			((Said 'examine/butt') (Print 272 2))
			((Said 'get/butt') (Print 272 3))
			((Said 'drink/alcohol') (Print 272 4))
			(
			(and (MousedOn self event 3) (not (& global207 $0040))) (event claimed: 1) (ParseName {clarence}))
			(
				(and
					(& global207 $0040)
					(or (MousedOn self event 3) (Said 'examine/attorney'))
				)
				(event claimed: 1)
				(Print 272 5)
			)
			(
			(and (== (event type?) evSAID) (Said '*/attorney>'))
				(cond 
					((Said 'converse')
						(= theTalker 7)
						(switch local0
							(0 (Say 1 272 6))
							(1
								(Say 1 272 7)
								(= theTalker 12)
								(Say 1 272 8)
							)
							(2 (Say 1 272 9))
							(3
								(Say 1 272 10)
								(= theTalker 12)
								(Say 1 272 11)
							)
							(4 (Say 1 272 12))
							(5
								(Say 1 272 13)
								(= theTalker 12)
								(Say 1 272 14)
							)
							(6 (Say 1 272 15))
							(else  (Print 272 16))
						)
						(++ local0)
					)
					((Said 'hear') (Print 272 17))
				)
			)
		)
	)
)

(instance ClarAss of Prop
	(properties
		y 102
		x 168
		view 401
		cel 2
	)
)

(instance Smoke of Prop
	(properties
		y 60
		x 167
		view 401
		loop 6
	)
)
