;;; Sierra Script 1.0 - (do not remove this comment)
(script# 150)
(include sci.sh)
(use Main)
(use PanoRoom)
(use PolyPath)
(use Polygon)
(use Feature)
(use Reverse)
(use Motion)
(use Actor)
(use System)

(public
	rm150 0
)

(local
	local0
)
(instance rm150 of PanoRoom
	(properties
		noun 7
		picture 150
	)
	
	(method (init)
		(if (== prevRoomNum 330)
			(fire init: hide:)
			(Bset 88)
			(cSound number: 285 setLoop: -1 play: 127)
		else
			(cSound number: 150 setLoop: -1 play: 127)
		)
		(if (or (Btst 85) (== prevRoomNum 160)) (= east 160))
		(HandsOff)
		(self
			setRegions: 50
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						319
						189
						0
						189
						0
						0
						319
						0
						319
						54
						139
						49
						79
						59
						65
						74
						125
						69
						156
						78
						99
						86
						121
						95
						116
						113
						129
						134
						170
						141
						238
						173
						319
						173
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 284 118 284 126 255 126 244 126 244 118
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 262 75 264 85 204 85 194 75 238 69
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 184 52 225 56 225 61 184 61
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						142
						101
						169
						101
						226
						115
						206
						122
						220
						128
						187
						138
						170
						128
						189
						120
						169
						112
						128
						106
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 141 119 159 118 159 126 141 126
					yourself:
				)
			setScript:
				(switch prevRoomNum
					(400
						(ego posn: panoEgoX panoEgoY)
						fromSavanna
					)
					(380 fromVines)
					(east
						(= style 11)
						fromSimbaniPanorama
					)
					(else  fromTarna)
				)
		)
		(river_1 init: setCycle: Rev)
		(river_2 init: setCycle: Rev)
		(river_3 init: setCycle: Rev)
		(river_4 init: setCycle: Rev)
		(tarna init:)
		(rockPile init:)
		(smallRockPile init:)
		(mountains init:)
		(if (and (Btst 99) (== gGClientModNum curRoomNum))
			(honeyTree x: gGOwnerX y: gGOwnerY init: stopUpd:)
		)
		(super init: &rest)
		(if (not (Btst 208)) (theGame save: 1))
		(ego solvePuzzle: 208 3)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			((& (ego onControl:) $0010) (ego setMotion: 0) (curRoom newRoom: 200))
			((ego inRect: 244 118 292 128) (ego setMotion: 0) (self newRoom: 380))
		)
	)
	
	(method (dispose)
		(DisposeScript 35)
		(super dispose:)
	)
	
	(method (cue)
		(cond 
			((& (ego onControl:) $0010) (ego setMotion: 0) (self newRoom: 200))
			((ego inRect: 244 118 292 128) (self newRoom: 380))
			((and (> (ego x?) 315) (== prevRoomNum 330)) (self setScript: toSimbani))
			(
				(and
					(Btst 99)
					(== gGClientModNum curRoomNum)
					(ego
						inRect: gGOwnerX (- gGOwnerY 20) (+ gGOwnerX 20) gGOwnerY
					)
				)
				(Bset 143)
				(= monsterNum 2)
				(self newRoom: 400)
			)
			((& (ego onControl:) $0400) (Bset 143) (= monsterNum 999) (self newRoom: 400))
			(else (self newRoom: 0))
		)
	)
)

(instance noEnterTarna of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: 3 6 10 0 self)
			)
			(1
				(if (> (ego y?) (ego yLast?))
					(ego
						setMotion: PolyPath (+ (ego x?) 5) (- (ego y?) 5) self
					)
				else
					(ego
						setMotion: PolyPath (+ (ego x?) 5) (+ (ego y?) 5) self
					)
				)
			)
			(2
				(HandsOn 6 3)
				(self dispose:)
			)
		)
	)
)

(instance toSimbani of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 319 (ego y?) self)
			)
			(1 (curRoom newRoom: 160))
		)
	)
)

(instance mustLeave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego code: 0)
				(messager say: 3 6 1 0 self)
			)
			(1 (curRoom newRoom: 310))
		)
	)
)

(instance fromTarna of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst 88) (ego view: 153))
				(ego posn: 117 87 setMotion: PolyPath 131 91 self)
			)
			(1
				(if (Btst 88)
					(curRoom setScript: rakeeshWalk)
				else
					(self cue:)
				)
			)
			(2
				(HandsOn 6 3)
				(self dispose:)
			)
		)
	)
)

(instance rakeeshWalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr 6)
				(ego setMotion: PolyPath 150 95 self)
			)
			(1 (messager say: 2 6 9 0 self))
			(2
				(Bclr 6)
				(ego setMotion: PolyPath 170 91 self)
			)
			(3 (messager say: 2 6 3 0 self))
			(4
				(Bclr 6)
				(ego setMotion: PolyPath 205 95 self)
			)
			(5 (messager say: 2 6 4 0 self))
			(6
				(Bclr 6)
				(ego setMotion: PolyPath 240 93 self)
			)
			(7 (messager say: 2 6 5 0 self))
			(8
				(Bclr 6)
				(ego setMotion: PolyPath 265 95 self)
			)
			(9 (messager say: 2 6 6 0 self))
			(10
				(Bclr 6)
				(ego setMotion: PolyPath 286 94 self)
			)
			(11
				(ego setMotion: PolyPath 290 94 self)
			)
			(12
				(ego setMotion: PolyPath 319 91 self)
			)
			(13 (curRoom newRoom: 160))
		)
	)
)

(instance fromVines of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 262 129 setMotion: PolyPath 258 140 self)
			)
			(1
				(HandsOn 6 3)
				(self dispose:)
			)
		)
	)
)

(instance fromSimbaniPanorama of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn 6 3)
				(ego x: 318 setMotion: MoveTo 314 (ego y?))
				(self dispose:)
			)
		)
	)
)

(instance fromSavanna of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(= cycles 10)
			)
			(1
				(HandsOn 6 3)
				(self dispose:)
			)
		)
	)
)

(instance fire of Prop
	(properties
		x 295
		y 94
		view 150
	)
)

(instance honeyTree of Prop
	(properties
		view 150
		loop 1
		cel 2
		signal $4000
	)
)

(instance tarna of Feature
	(properties
		x 114
		y 75
		noun 1
		onMeCheck $0010
	)
)

(instance rockPile of Feature
	(properties
		x 230
		y 76
		noun 4
		nsTop 67
		nsLeft 201
		nsBottom 85
		nsRight 260
	)
)

(instance smallRockPile of Feature
	(properties
		x 264
		y 121
		noun 5
		nsTop 116
		nsLeft 245
		nsBottom 127
		nsRight 283
	)
)

(instance mountains of Feature
	(properties
		x 218
		y 16
		noun 6
		nsTop 6
		nsLeft 118
		nsBottom 26
		nsRight 319
	)
)

(instance river_1 of Prop
	(properties
		x 190
		y 189
		noun 8
		view 150
		loop 3
		cel 2
		priority 15
		signal $0010
		cycleSpeed 7
		name "river#1"
	)
)

(instance river_2 of Prop
	(properties
		x 165
		y 167
		noun 9
		view 150
		loop 4
		cel 3
		priority 15
		signal $0010
		cycleSpeed 7
		name "river#2"
	)
)

(instance river_3 of Prop
	(properties
		x 94
		y 114
		noun 10
		view 150
		loop 5
		cel 3
		priority 15
		signal $0010
		cycleSpeed 10
		name "river#3"
	)
)

(instance river_4 of Prop
	(properties
		x 49
		y 75
		noun 11
		view 150
		loop 6
		cel 2
		priority 15
		signal $0010
		cycleSpeed 11
		name "river#4"
	)
)
