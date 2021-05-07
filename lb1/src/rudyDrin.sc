;;; Sierra Script 1.0 - (do not remove this comment)
(script# 379)
(include sci.sh)
(use Main)
(use servent)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rudyDrin 0
)
(synonyms
	(drink alcohol)
	(rudolph person fellow)
)

(local
	local0
)
(instance Rudy of Act
	(properties
		y 126
		x 169
		view 381
		loop 3
		cel 2
	)
	
	(method (handleEvent event)
		(cond 
			(
			(and (MousedOn self event 3) (not (& global207 $0100))) (event claimed: 1) (ParseName {rudy}))
			(
				(and
					(& global207 $0100)
					(or (MousedOn self event 3) (Said 'examine/rudolph'))
				)
				(event claimed: 1)
				(Print 379 0)
			)
		)
	)
)

(instance Jeeves of servent
	(properties)
)

(instance rudyDrin of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(LoadMany 143 243 222)
		(LoadMany 128 381 908)
		(= global208 256)
		(= [global377 8] 222)
		(glass setPri: 13 ignoreActors: 1 init: stopUpd:)
		(Rudy init: setScript: drink)
		(if (not (& global194 $0001))
			(= global194 (| global194 $0001))
			(Jeeves
				view: 444
				posn: -10 101
				setCycle: Walk
				guest1: glass
				exitX: -10
				exitY: 101
				setAvoider: ((Avoid new:) offScreenOK: 1)
				init:
			)
			(= global167 1)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (> (ego y?) 158)
			(ego setPri: 14)
		else
			(ego setPri: -1)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return (event claimed?)))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said 'examine/glass,drink') (Print 379 1))
					((Said 'hear/rudolph') (Print 379 2))
					((Said 'get/drink,glass') (Print 379 3))
					((Said 'drink/drink') (Print 379 4))
					((Said 'converse/rudolph')
						(= theTalker 9)
						(switch local0
							(0 (Say 1 379 5))
							(1 (Say 1 379 6))
							(2 (Say 1 379 7))
							(else  (Print 379 8))
						)
						(++ local0)
					)
				)
			else
				0
			)
		)
	)
)

(instance drink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Rudy
					cycleSpeed: 2
					setPri: 14
					illegalBits: 0
					ignoreActors: 1
					setCycle: Beg self
				)
			)
			(1
				(glass hide:)
				(Rudy loop: 2 cel: 3 setCycle: Beg self)
			)
			(2
				(Rudy loop: 1 cel: 0 setCycle: End)
				(= seconds 3)
			)
			(3 (Rudy setCycle: Beg self))
			(4
				(Rudy loop: 2 cel: 0 setCycle: End self)
			)
			(5
				(glass show:)
				(Rudy loop: 3 cel: 0 setCycle: End)
				(if (> (Random 1 100) 30) (= state -1))
				(= seconds (Random 6 12))
			)
			(6
				(Rudy loop: 5 cel: 0 setCycle: End)
				(if (< (Random 1 100) 50) (= state 8))
				(= seconds (Random 3 6))
			)
			(7
				(Rudy loop: 6 cel: 0 setCycle: End)
				(= seconds (Random 3 6))
			)
			(8
				(Rudy loop: 6 setCycle: Beg self)
			)
			(9
				(Rudy cel: 3 loop: 5 setCycle: Beg self)
			)
			(10
				(if (< (Random 1 100) 70)
					(= state -1)
				else
					(= state 5)
				)
				(= seconds (Random 3 9))
			)
		)
	)
)

(instance glass of Prop
	(properties
		y 138
		x 151
		view 381
		cel 2
	)
)
