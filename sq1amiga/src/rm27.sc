;;; Sierra Script 1.0 - (do not remove this comment)
(script# 27)
(include sci.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use Polygon)
(use Feature)
(use Motion)
(use System)

(public
	rm27 0
)

(local
	[bottomPts1 42] = [121 -1 119 44 148 96 168 113 192 140 170 140 134 92 96 12 60 27 150 147 137 150 110 149 68 133 58 123 60 108 58 105 40 96 37 80 21 58 0 53]
	[bottomPts2 28] = [259 45 291 82 294 98 287 107 275 110 256 107 235 110 216 105 233 95 205 92 208 76 224 72 204 61 199 45]
	[topPts 62] = [54 0 64 11 67 23 62 30 70 34 77 48 79 58 87 61 90 72 87 75 100 81 99 96 110 99 116 113 131 128 157 121 153 111 143 105 133 85 135 77 129 69 121 56 120 47 110 40 104 26 105 13 99 0 319 0 319 189 0 189]
)
(instance rm27 of SQRoom
	(properties
		lookStr {The cervical portion of the spinal column takes a dive into the sand right about here.}
		picture 27
		horizon 20
		north 24
		east 38
		south 18
		west 26
		walkOffTop 1
	)
	
	(method (init)
		(polyBottom1 points: @bottomPts1 size: 21)
		(polyBottom2 points: @bottomPts2 size: 14)
		(polyTop points: @topPts size: 31)
		(if (== currentFloor 2)
			(self addObstacle: polyBottom1 polyBottom2)
		else
			(self addObstacle: polyTop)
		)
		(ramp init:)
		(claw init:)
		(switch prevRoomNum
			(west (= style 12) (HandsOn))
			(north
				(= style 13)
				(if (and (<= 209 (ego x?)) (<= (ego x?) 277))
					(if (< (ego x?) 246) (ego x: 209) else (ego x: 277))
				)
				(if (and (< 108 (ego x?)) (< (ego x?) 135))
					(ego x: 130)
				)
				(HandsOn)
			)
			(south
				(ego x: 160)
				(= style 10)
			)
			(else  (= style 10))
		)
		(ego init:)
		(self setRegions: 704)
		(super init:)
		(if
		(and (== currentFloor 1) (== prevRoomNum north))
			(ego x: 106)
		)
	)
	
	(method (doit &tmp temp0)
		(= temp0 (ego onControl: 1))
		(cond 
			(script 0)
			((& temp0 $4000)
				(if (== currentFloor 2)
					(self setScript: goUp)
				else
					(self setScript: goDown)
				)
			)
		)
		(if (& temp0 $4002) (ego setPri: 14))
		(super doit:)
	)
)

(instance goUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setPri: 14 setMotion: MoveTo 125 98 self)
			)
			(1
				(= currentFloor 1)
				((client obstacles?) delete: polyBottom1 polyBottom2)
				((client obstacles?) add: polyTop)
				(= cycles 6)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance goDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 175 150 self)
			)
			(1
				(ego setPri: -1)
				(= currentFloor 2)
				((client obstacles?) delete: polyTop)
				((client obstacles?) add: polyBottom1 polyBottom2)
				(= cycles 6)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance ramp of Feature
	(properties
		description {neck bone}
		onMeCheck $4002
		lookStr {Cervical vertebrae taper off and appear to vanish gradually into the sand.}
	)
)

(instance claw of Feature
	(properties
		description {claw}
		onMeCheck $2000
		lookStr {These foreclaws were once razor-sharp, but the forces of the elements have reduced them to over-sized blunt toe nails. It's a good thing you didn't come here in an earlier time. These things would have torn you a new one.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 27 0))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance polyBottom1 of Polygon
	(properties
		type $0002
	)
)

(instance polyBottom2 of Polygon
	(properties
		type $0002
	)
)

(instance polyTop of Polygon
	(properties
		type $0002
	)
)
