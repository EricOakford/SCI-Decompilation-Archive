;;; Sierra Script 1.0 - (do not remove this comment)
(script# 750)
(include sci.sh)
(use Main)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm750 0
)

(local
	local0
)
(instance rm750 of Rm
	(properties
		noun 3
		picture 750
		vanishingY -60
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						262
						189
						272
						175
						265
						159
						235
						159
						221
						156
						201
						161
						191
						172
						128
						169
						38
						170
						64
						160
						137
						86
						127
						77
						135
						70
						164
						67
						184
						66
						204
						69
						224
						78
						228
						76
						187
						56
						133
						66
						116
						80
						128
						84
						57
						156
						32
						164
						21
						154
						48
						141
						33
						131
						57
						126
						0
						120
						0
						189
					yourself:
				)
				((Polygon new:)
					type: 3
					init:
						319
						88
						306
						85
						310
						74
						292
						70
						270
						69
						246
						78
						298
						78
						304
						81
						291
						90
						319
						90
					yourself:
				)
				((Polygon new:)
					type: 3
					init: 319 32 282 26 204 27 183 19 135 1 130 9 202 30 284 30 319 43
					yourself:
				)
		)
		(cond 
			((== prevRoomNum 755) (= style 11) (curRoom setScript: enterEast))
			((== prevRoomNum 760) (curRoom setScript: enterNorth))
			(else (curRoom setScript: enterWest))
		)
		(super init:)
		(if (== prevRoomNum 170)
			(cSound number: 750 setLoop: -1 play: 127)
			(globalSound number: 391 setLoop: -1 play: 90)
		)
		(splash setCycle: Fwd init:)
		(splash1 setCycle: Fwd init:)
		(splash2 setCycle: Fwd init:)
		(splash3 setCycle: Fwd init:)
		(lilRok init:)
		(mainPath init:)
		(stream init:)
		(pool init:)
		(tree init:)
		(cave init:)
		(rightPath init:)
	)
	
	(method (doit)
		(if (GameIsRestarting)
			(cSound number: 750 setLoop: -1 play: 127)
			(globalSound number: 391 setLoop: -1 play: 90)
		)
		(if (== local0 2)
			(CyclePalette 226 230 -1)
			(= local0 0)
		else
			(++ local0)
		)
		(cond 
			(script 0)
			((>= (ego x?) 315) (curRoom setScript: exitEast))
			((and (<= (ego y?) 30) (<= (ego x?) 180)) (curRoom newRoom: 760))
			((or (<= (ego x?) 5) (>= (ego y?) 183)) (curRoom setScript: exitWest))
			((& (ego onControl: 1) $0004) (curRoom setScript: jumpRight))
			((& (ego onControl: 1) $0008) (curRoom setScript: jumpLeft))
		)
		(super doit:)
	)
)

(instance jumpRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setLoop: 0 setMotion: MoveTo 223 78 self)
			)
			(1
				(ego
					loop: 0
					x: 245
					y: 78
					cel: 0
					view: 47
					setPri: 14
					setScale: 0
					setCycle: End self
				)
			)
			(2
				(ego
					normalize:
					setPri: -1
					setScale: 190
					setMotion: MoveTo 260 75 self
				)
			)
			(3
				(ego setLoop: -1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance jumpLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 251 79 self)
			)
			(1
				(ego
					loop: 1
					x: 230
					y: 79
					cel: 0
					loop: 1
					view: 47
					setPri: 14
					setScale: 0
					setCycle: End self
				)
			)
			(2
				(ego
					normalize:
					setPri: -1
					setScale: 190
					setMotion: MoveTo 209 69 self
				)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance exitWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (>= (ego y?) 183)
					(ego setMotion: MoveTo (ego x?) 200 self)
				else
					(ego setMotion: MoveTo -10 (ego y?) self)
				)
			)
			(1 (curRoom newRoom: 170))
		)
	)
)

(instance enterWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setScale: 190
					normalize:
					x: -20
					y: 160
					init:
					setMotion: MoveTo 20 160 self
				)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance enterNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setScale: 190
					normalize:
					x: 175
					y: 23
					init:
					setMotion: PolyPath 191 26 self
				)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance exitEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 320 (ego y?) self)
			)
			(1
				(curRoom newRoom: 755)
				(self dispose:)
			)
		)
	)
)

(instance enterEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 320 setScale: 190 normalize: init:)
				(if (< (ego y?) 42) (ego x: 294 y: 29))
				(= cycles 1)
			)
			(1
				(if (< (ego y?) 42)
					(ego setMotion: MoveTo 282 29 self)
				else
					(ego
						setMotion: MoveTo (- (ego x?) 20) (- (ego y?) 4) self
					)
				)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance splash of Prop
	(properties
		x 215
		y 56
		view 750
		signal $4000
		detailLevel 3
	)
)

(instance splash1 of Prop
	(properties
		x 238
		y 80
		view 750
		loop 1
		signal $4000
		detailLevel 3
	)
)

(instance splash2 of Prop
	(properties
		x 213
		y 132
		view 750
		loop 2
		cel 1
		signal $4000
		detailLevel 3
	)
)

(instance splash3 of Prop
	(properties
		x 73
		y 126
		view 750
		loop 3
		signal $4000
		detailLevel 3
	)
)

(instance lilRok of Feature
	(properties
		x 233
		y 87
		noun 1
		nsTop 84
		nsLeft 227
		nsBottom 91
		nsRight 240
		sightAngle 40
	)
)

(instance mainPath of Feature
	(properties
		x 93
		y 126
		noun 2
		nsTop 100
		nsLeft 70
		nsBottom 152
		nsRight 117
		sightAngle 180
	)
)

(instance stream of Feature
	(properties
		x 114
		y 157
		noun 4
		nsTop 150
		nsLeft 36
		nsBottom 165
		nsRight 193
		sightAngle 180
	)
)

(instance pool of Feature
	(properties
		x 237
		y 58
		noun 5
		nsTop 46
		nsLeft 211
		nsBottom 71
		nsRight 263
		sightAngle 180
	)
)

(instance tree of Feature
	(properties
		x 63
		y 36
		noun 6
		nsTop 1
		nsLeft 39
		nsBottom 72
		nsRight 88
		sightAngle 180
	)
)

(instance cave of Feature
	(properties
		x 148
		y 46
		noun 7
		nsTop 27
		nsLeft 125
		nsBottom 65
		nsRight 171
		sightAngle 180
	)
)

(instance rightPath of Feature
	(properties
		x 300
		y 78
		noun 8
		nsTop 58
		nsLeft 282
		nsBottom 98
		nsRight 319
		sightAngle 180
	)
)
