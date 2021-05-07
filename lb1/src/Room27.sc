;;; Sierra Script 1.0 - (do not remove this comment)
(script# 27)
(include sci.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room27 0
)

(instance Room27 of Rm
	(properties
		picture 27
	)
	
	(method (init)
		(= horizon 120)
		(= east 28)
		(= west 26)
		(= north 21)
		(super init:)
		(if howFast
			(star1 cycleSpeed: 2 setCycle: Fwd init:)
			(star2 init: setScript: twinkle)
		)
		(self setRegions: 207 405 setFeatures: House)
		(if (and (>= currentAct 2) (< currentAct 4))
			(self setRegions: 202)
		)
		(if
			(or
				(and (== currentAct 3) (!= global114 10))
				(and (== currentAct 6) (not (& global118 $0002)))
			)
			(self setRegions: 281)
		)
		(switch prevRoomNum
			(21 (ego posn: 162 122))
		)
		(ego view: 0 init:)
		(HandsOn)
	)
	
	(method (doit)
		(if (FirstEntry) (Print 27 0))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(return
			(if
			(and (== (event type?) evSAID) (Said 'examine>'))
				(cond 
					((Said '[<around,at][/room]') (Print 27 0))
					((Said '/cabin,mansion') (Print 27 1))
				)
			else
				0
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance twinkle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (star2 setCycle: End self))
			(1
				(if (< (Random 1 100) 35)
					(= state -1)
				else
					(= state 0)
				)
				(= seconds (Random 7 15))
			)
		)
	)
)

(instance star1 of Prop
	(properties
		y 3
		x 116
		view 107
		loop 1
	)
)

(instance star2 of Prop
	(properties
		y 43
		x 228
		view 107
		loop 2
	)
)

(instance House of RFeature
	(properties
		nsTop 69
		nsLeft 74
		nsBottom 82
		nsRight 90
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/playhouse'))
			(event claimed: 1)
			(Print 27 2)
		)
	)
)
