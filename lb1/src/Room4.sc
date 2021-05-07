;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4)
(include game.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room4 0
)
(synonyms
	(rose blossom)
)

(instance Room4 of Room
	(properties
		picture 4
	)
	
	(method (init)
		(= west 10)
		(= east 12)
		(= north 5)
		(= south 33)
		(= horizon 118)
		(super init:)
		(Load FONT 4)
		(Load SOUND 2)
		(addToPics add: lBench rBench eachElementDo: #init doit:)
		(self
			setRegions: 206
			setFeatures: Column1 Column2 rBench lBench
		)
		(if (== currentAct 1)
			(self setRegions: 381)
		)
		(statue setPri: 12 ignoreActors: TRUE init: stopUpd:)
		(if howFast
			(star1 cycleSpeed: 2 setCycle: Forward init:)
			(star2 init: setScript: twinkle)
			(if (== (Random 1 200) 95)
				(Plane loop: 1 play:)
				(airplane
					setCycle: Forward
					illegalBits: 0
					setLoop: 3
					ignoreHorizon: TRUE
					xStep: 10
					setMotion: MoveTo 380 68
					init:
				)
			)
		)
		(if (and (>= currentAct 2) (< currentAct 4))
			(self setRegions: 202)
		)
		(switch prevRoomNum
			(12 (ego posn: 318 158))
			(10 (ego posn: 1 158))
			(5 (ego posn: 158 120))
		)
		(ego view: 0 illegalBits: cWHITE init:)
	)
	
	(method (doit)
		(if (FirstEntry)
			(Print 4 0)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]')
								(Print 4 0)
							)
							((Said '/garden[<rose]')
								(Print 4 1)
							)
							((Said '/gallery')
								(Print 4 2)
							)
							((Said '/field')
								(Print 4 3)
							)
							((Said '/path')
								(Print 4 4)
							)
							((Said '/door')
								(Print 4 5)
							)
							((Said '/fence')
								(Print 4 6)
							)
							((Said '/archway')
								(Print 4 7)
							)
							((Said '/rose,bush,foliage,arbor')
								(Print 4 8)
							)
						)
					)
					((Said 'get/rose')
						(Print 4 9)
					)
					((Said 'climb/fence')
						(Print 4 10)
					)
					((Said 'smell/rose')
						(Print 4 11)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(if (and (& deadGuests $0008) (!= n 5))
			(= global200 101)
		)
		(if (== n 33)
			(cSound stop:)
		)
		(super newRoom: n)
	)
)

(instance twinkle of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(star2 setCycle: EndLoop self)
			)
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

(instance lBench of RPicView
	(properties
		y 133
		x 87
		view 104
		loop 1
		cel 1
		priority 9
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 4 12)
		)
	)
)

(instance rBench of RPicView
	(properties
		y 133
		x 233
		view 104
		loop 1
		priority 9
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<below/bench')
				(Print 4 13)
			)
			((Said 'sit/[<bench]')
				(Print 4 14)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/bench'))
				(event claimed: TRUE)
				(Print 4 12)
			)
		)
	)
)

(instance statue of Prop
	(properties
		y 148
		x 160
		view 104
	)
	
	(method (handleEvent event)
		(cond 
			((or (MousedOn self event shiftDown) (Said 'examine/monument'))
				(event claimed: TRUE)
				(Print 4 15)
			)
			((Said 'get/monument')
				(Print 4 16)
			)
			((Said 'press,drag,rotate,move/monument')
				(Print 4 17)
			)
		)
	)
)

(instance star1 of Prop
	(properties
		y 11
		x 212
		view 107
		loop 1
	)
)

(instance star2 of Prop
	(properties
		y 14
		x 110
		view 107
		loop 2
	)
)

(instance airplane of Actor
	(properties
		y 68
		x -120
		view 104
		loop 3
	)
)

(instance Column1 of RFeature
	(properties
		nsTop 23
		nsLeft 43
		nsBottom 182
		nsRight 75
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/column'))
			(event claimed: TRUE)
			(Print 4 18)
		)
	)
)

(instance Column2 of RFeature
	(properties
		nsTop 21
		nsLeft 247
		nsBottom 181
		nsRight 280
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/column'))
			(event claimed: TRUE)
			(Print 4 18)
		)
	)
)

(instance Plane of Sound
	(properties
		number 2
	)
)
