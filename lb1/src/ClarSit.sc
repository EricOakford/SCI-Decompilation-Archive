;;; Sierra Script 1.0 - (do not remove this comment)
(script# 378)
(include game.sh)
(use Main)
(use servent)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	ClarSit 0
)
(synonyms
	(attorney person fellow)
)

(local
	local0
	[local1 2]
	roomCycles
)
(instance Jeeves of servent)

(instance ClarSit of Region
	
	(method (init)
		(super init:)
		(LoadMany 143 243 218)
		(LoadMany 142 7)
		(= global208 64)
		(= [global377 6] 218)
		(ClarAss init: stopUpd:)
		(Clarence setPri: 6 init: setScript: clarActions)
		(Smoke setPri: 6 init: hide:)
		(if (not (& global194 $0002))
			(|= global194 $0002)
			(Jeeves
				view: 444
				posn: -15 98
				setCycle: Walk
				guest1: ClarAss
				exitX: -10
				exitY: 98
				itemX: 178
				itemY: 108
				setAvoider: ((Avoider new:) offScreenOK: TRUE)
				init:
			)
		)
	)
	
	(method (doit)
		(if (< roomCycles 50)
			(++ roomCycles)
		else
			(= global167 1)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 204)
		(DisposeScript CHASE)
		(DisposeScript AVOIDER)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '/attorney')
								(if (& global207 $0040)
									(Print 378 0)
								else
									(event claimed: FALSE)
								)
							)
							((Said '/cigar')
								(Bset fExaminedCigar)
								(Print 378 1)
							)
							((Said '/drink,glass')
								(Print 378 2)
							)
						)
					)
					((Said 'converse>')
						(if (Said '/attorney')
							(Print 378 3)
						)
					)
					((Said 'hear/attorney')
						(Print 378 4)
					)
					((Said 'get/butt')
						(Print 378 5)
					)
					((Said 'get/drink,glass')
						(Print 378 6)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance clarActions of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Clarence loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(1
				(Clarence loop: 4 cel: 0 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(2
				(Smoke cel: 0 setCycle: EndLoop self show:)
				(if (< local0 1)
					(++ local0)
					(= state 0)
				else
					(= local0 0)
				)
			)
			(3
				(Smoke cel: 0 hide:)
				(Clarence loop: 1 cel: 5 cycleSpeed: 2 setCycle: BegLoop)
				(= seconds (Random 6 12))
			)
			(4
				(Clarence loop: 2 cel: 0 setCycle: EndLoop)
				(= seconds (Random 6 12))
				(= state -1)
			)
		)
	)
)

(instance Clarence of Prop
	(properties
		y 74
		x 171
		view 401
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((and (MousedOn self event shiftDown) (not (& global207 $0040)))
				(event claimed: TRUE)
				(ParseName {clarence})
			)
			(
				(and
					(& global207 $0040)
					(or (MousedOn self event shiftDown) (Said 'examine/attorney'))
				)
				(event claimed: TRUE)
				(Print 378 0)
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
