;;; Sierra Script 1.0 - (do not remove this comment)
(script# 240)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	coloroom 0
)
(synonyms
	(butt cigarette)
	(colonel person fellow)
)

(local
	local0
	local1
)
(instance Colonel of Prop
	(properties)
	
	(method (handleEvent event)
		(cond 
			(
			(and (MousedOn self event 3) (not (& global207 $0200))) (event claimed: 1) (ParseName {colonel}))
			(
				(and
					(& global207 $0200)
					(or (MousedOn self event 3) (Said 'examine/colonel'))
				)
				(event claimed: 1)
				(Print 240 0)
			)
		)
	)
)

(instance smoke1 of Prop
	(properties)
)

(instance smoke2 of Prop
	(properties)
)

(instance coloroom of Rgn
	(properties)
	
	(method (init &tmp temp0 temp1 temp2)
		(super init:)
		(= temp1 175)
		(= temp2 140)
		(switch currentAct
			(1
				(= temp0 253)
				(= temp1 133)
				(= temp2 98)
			)
			(2 (= temp0 285))
			(3
				(= temp0 292)
				(= temp1 229)
				(= temp2 95)
			)
			(5 (= temp0 374))
			(6 (= temp0 375))
			(else  (= temp0 376))
		)
		(Load rsFONT 4)
		(LoadMany 143 243 temp0)
		(Load rsVIEW 909)
		(= global208 512)
		(= [global377 9] temp0)
		(Colonel
			view: 304
			x: temp1
			y: temp2
			init:
			stopUpd:
			setScript: colonelActions
		)
		(smoke1
			view: 304
			loop: 2
			cel: 0
			posn: (+ (Colonel x?) 6) (- (Colonel y?) 30)
			setPri: (CoordPri (Colonel y?))
			init:
			hide:
		)
		(smoke2
			view: 304
			loop: 3
			cel: 0
			posn: (+ (Colonel x?) 11) (- (Colonel y?) 24)
			setPri: (CoordPri (Colonel y?))
			init:
			hide:
		)
		(Glow
			posn: (+ (Colonel x?) 10) (Colonel y?)
			z: 29
			init:
			hide:
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said 'get,move,press/wheelchair') (Print 240 1))
					((Said 'examine/butt') (Bset 13) (Print 240 2))
					((Said 'hear/colonel') (Print 240 3))
					((Said 'get/butt') (Print 240 4))
					((and (Said 'converse>') (Said '/colonel'))
						(= theTalker 10)
						(switch currentAct
							(7
								(switch local1
									(0 (Say 1 240 5))
									(1 (Say 1 240 6))
									(else  (Print 240 7))
								)
							)
							(else 
								(switch local1
									(0 (Say 1 240 8))
									(1 (Say 1 240 9))
									(2 (Say 1 240 10))
									(else  (Print 240 11))
								)
							)
						)
						(++ local1)
					)
				)
			else
				0
			)
		)
	)
)

(instance colonelActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(smoke2 cel: 0 hide:)
				(Colonel loop: 0 cel: 0 setCycle: End self)
			)
			(1
				(Glow show: loop: 1 cel: 0 setCycle: Fwd)
				(= cycles 18)
			)
			(2
				(Glow hide:)
				(Colonel
					loop: 0
					cel: (- (NumCels Colonel) 1)
					setCycle: Beg self
				)
			)
			(3
				(smoke2 setCycle: Fwd show:)
				(smoke1 show: setCycle: End self)
			)
			(4
				(smoke1 cel: 0 hide:)
				(= cycles 1)
			)
			(5
				(if (< (Random 1 100) 30)
					(= state -1)
				else
					(= state 4)
				)
				(= seconds 5)
			)
		)
	)
)

(instance Glow of Prop
	(properties
		view 304
		loop 1
	)
)
