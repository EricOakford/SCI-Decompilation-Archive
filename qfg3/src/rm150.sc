;;; Sierra Script 1.0 - (do not remove this comment)
(script# 150)
(include game.sh) (include "150.shm")
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
		noun N_ROOM
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
		(if (or (Btst fCanGoToSimbani) (== prevRoomNum 160))
			(= east 160)
		)
		(HandsOff)
		(self
			setRegions: PANORAMA
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						319 189
						0 189
						0 0
						319 0
						319 54
						139 49
						79 59
						65 74
						125 69
						156 78
						99 86
						121 95
						116 113
						129 134
						170 141
						238 173
						319 173
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						284 118
						284 126
						255 126
						244 126
						244 118
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						262 75
						264 85
						204 85
						194 75
						238 69
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						184 52
						225 56
						225 61
						184 61
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						142 101
						169 101
						226 115
						206 122
						220 128
						187 138
						170 128
						189 120
						169 112
						128 106
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						141 119
						159 118
						159 126
						141 126
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
						(= style SCROLLRIGHT)
						fromSimbaniPanorama
					)
					(else  fromTarna)
				)
		)
		(river_1 init: setCycle: Reverse)
		(river_2 init: setCycle: Reverse)
		(river_3 init: setCycle: Reverse)
		(river_4 init: setCycle: Reverse)
		(tarna init:)
		(rockPile init:)
		(smallRockPile init:)
		(mountains init:)
		(if (and (Btst fHoneyBirdHinted) (== honeyBirdRoom curRoomNum))
			(honeyTree x: honeyTreeX y: honeyTreeY init: stopUpd:)
		)
		(super init: &rest)
		(if (not (Btst fExitTarna))
			(theGame save: 1)
		)
		(ego solvePuzzle: fExitTarna 3)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			((& (ego onControl:) cRED)
				(ego setMotion: 0)
				(curRoom newRoom: 200)
			)
			((ego inRect: 244 118 292 128)
				(ego setMotion: 0)
				(self newRoom: 380)
			)
		)
	)
	
	(method (dispose)
		(DisposeScript 35)
		(super dispose:)
	)
	
	(method (cue)
		(cond 
			((& (ego onControl:) cRED) ;into Tarna
				(ego setMotion: 0)
				(self newRoom: 200)
			)
			((ego inRect: 244 118 292 128)	;to Meerbats
				(self newRoom: 380)
			)
			((and (> (ego x?) 315) (== prevRoomNum 330))
				(self setScript: toSimbani)
			)
			(
				(and
					(Btst fHoneyBirdHinted)
					(== honeyBirdRoom curRoomNum)
					(ego
						inRect: honeyTreeX (- honeyTreeY 20) (+ honeyTreeX 20) honeyTreeY
					)
				)
				(Bset fStartedEncounter)
				(= monsterNum 2)
				(self newRoom: 400)
			)
			((& (ego onControl:) cLGREEN)	;no enemy encounters near the city
				(Bset fStartedEncounter)
				(= monsterNum 999)
				(self newRoom: 400)
			)
			(else
				(self newRoom: 0)
			)
		)
	)
)

(instance noEnterTarna of Script
	;NOTE: This script is unused.
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_CUE V_DOIT C_NO_ENTRY 0 self)
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

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 319 (ego y?) self)
			)
			(1
				(curRoom newRoom: 160)
			)
		)
	)
)

(instance mustLeave of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego code: 0)
				(messager say: N_CUE V_DOIT C_MUST_LEAVE 0 self)
			)
			(1
				(curRoom newRoom: 310)
			)
		)
	)
)

(instance fromTarna of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst fTravelWithSomeone)
					(ego view: 153)
				)
				(ego posn: 117 87 setMotion: PolyPath 131 91 self)
			)
			(1
				(if (Btst fTravelWithSomeone)
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

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr fInMainGame)
				(ego setMotion: PolyPath 150 95 self)
			)
			(1
				(messager say: N_RAKEESH_WALK V_DOIT C_TALK7 0 self)
			)
			(2
				(Bclr fInMainGame)
				(ego setMotion: PolyPath 170 91 self)
			)
			(3
				(messager say: N_RAKEESH_WALK V_DOIT C_TALK1 0 self)
			)
			(4
				(Bclr fInMainGame)
				(ego setMotion: PolyPath 205 95 self)
			)
			(5
				(messager say: N_RAKEESH_WALK V_DOIT C_TALK2 0 self)
			)
			(6
				(Bclr fInMainGame)
				(ego setMotion: PolyPath 240 93 self)
			)
			(7
				(messager say: N_RAKEESH_WALK V_DOIT C_TALK3 0 self)
			)
			(8
				(Bclr fInMainGame)
				(ego setMotion: PolyPath 265 95 self)
			)
			(9
				(messager say: N_RAKEESH_WALK V_DOIT C_TALK4 0 self)
			)
			(10
				(Bclr fInMainGame)
				(ego setMotion: PolyPath 286 94 self)
			)
			(11
				(ego setMotion: PolyPath 290 94 self)
			)
			(12
				(ego setMotion: PolyPath 319 91 self)
			)
			(13
				(curRoom newRoom: 160)
			)
		)
	)
)

(instance fromVines of Script
	
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
		signal ignrAct
	)
)

(instance tarna of Feature
	(properties
		x 114
		y 75
		noun N_TARNA
		onMeCheck fixPriOn
	)
)

(instance rockPile of Feature
	(properties
		x 230
		y 76
		noun N_ROCK_PILE
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
		noun N_SMALL_ROCK_PILE
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
		noun N_MOUNTAINS
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
		noun N_RIVER1
		view 150
		loop 3
		cel 2
		priority 15
		signal fixPriOn
		cycleSpeed 7
		name "river#1"
	)
)

(instance river_2 of Prop
	(properties
		x 165
		y 167
		noun N_RIVER2
		view 150
		loop 4
		cel 3
		priority 15
		signal fixPriOn
		cycleSpeed 7
		name "river#2"
	)
)

(instance river_3 of Prop
	(properties
		x 94
		y 114
		noun N_RIVER3
		view 150
		loop 5
		cel 3
		priority 15
		signal fixPriOn
		cycleSpeed 10
		name "river#3"
	)
)

(instance river_4 of Prop
	(properties
		x 49
		y 75
		noun N_RIVER4
		view 150
		loop 6
		cel 2
		priority 15
		signal fixPriOn
		cycleSpeed 11
		name "river#4"
	)
)
