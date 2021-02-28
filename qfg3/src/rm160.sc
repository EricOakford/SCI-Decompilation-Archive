;;; Sierra Script 1.0 - (do not remove this comment)
(script# 160)
(include sci.sh)
(use Main)
(use PanoRoom)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm160 0
)

(local
	local0
	local1
	local2
)
(procedure (localproc_0482)
	(return
		(switch heroType
			(0
				(switch (++ local1)
					(1 (return 20))
					(2 (return 17))
					(3 (return 18))
					(4 (return 19))
					(else  (return 16))
				)
			)
			(1
				(switch (++ local1)
					(1 (return 11))
					(2 (return 12))
					(3 (return 13))
					(4 (return 14))
					(else  (return 15))
				)
			)
			(2
				(switch (++ local1)
					(1 (return 6))
					(2 (return 7))
					(3 (return 8))
					(4 (return 9))
					(else  (return 10))
				)
			)
			(else 
				(switch (++ local1)
					(1 (return 1))
					(2 (return 2))
					(3 (return 3))
					(4 (return 4))
					(else  (return 5))
				)
			)
		)
	)
)

(instance rm160 of PanoRoom
	(properties
		noun 9
		picture 160
		east 170
		west 150
	)
	
	(method (init)
		(self
			setRegions: 50
			addObstacle:
				((Polygon new:)
					type: 2
					init: 122 89 124 99 64 99 64 89
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 177 91 185 105 179 117 153 117 168 101 150 100 141 91
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 176 177 319 156 319 189 0 189 0 159 54 159
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 251 80 251 87 213 87 205 80
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 0 319 42 262 42 173 39 152 46 112 52 0 53 0 0
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 171 72 171 76 148 76 148 72
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 102 127 142 121 148 130 125 139
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 109 149 124 145 142 151 159 143 190 143 189 154 159 166 124 162
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 194 101 185 93 198 81 214 90 214 100
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 214 140 238 140 238 145 214 145
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 220 95 223 91 239 89 248 90 251 94 231 100
					yourself:
				)
			setScript:
				(switch prevRoomNum
					(400
						(ego posn: panoEgoX panoEgoY)
						0
					)
					(700
						(ego posn: panoEgoX panoEgoY)
						0
					)
					(410 fromSimbani)
					(east
						(= style 11)
						fromTreePanorama
					)
					(west
						(= style 12)
						fromTarnaPanorama
					)
					(else  fromSprings)
				)
		)
		(poolOfPeace init:)
		(simbaniVillage init:)
		(bluffs init:)
		(highHill init:)
		(leapFrogRocks init:)
		(miscRocks init:)
		(ego solvePuzzle: 209 3)
		(if (and (Btst 99) (== gGClientModNum curRoomNum))
			(honeyTree x: gGOwnerX y: gGOwnerY init: stopUpd:)
		)
		(HandsOn 6 3)
		(super init: &rest)
		(if (not (Btst 88))
			(ego code: whereIsHe)
			(if (!= (cSound number?) 160)
				(cSound number: 160 setLoop: -1 play:)
			)
		)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 35)
	)
	
	(method (cue)
		(cond 
			((ego inRect: 208 82 250 97)
				(if (Btst 43)
					(self setScript: tellScript)
				else
					(curRoom newRoom: 410)
				)
			)
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
			((ego inRect: 128 153 162 174) (curRoom newRoom: 390))
			((> (ego x?) 315) (curRoom setScript: toJungle))
			((< (ego x?) 5) (curRoom setScript: toTarna))
			(else (curRoom newRoom: 0))
		)
	)
)

(instance tellScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: 3 6 21 0 self)
			)
			(1
				(HandsOff)
				(curRoom newRoom: 400)
			)
		)
	)
)

(instance toTarna of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 1 (ego y?) self)
			)
			(1 (curRoom newRoom: 150))
		)
	)
)

(instance toJungle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 319 (ego y?) self)
			)
			(1 (curRoom newRoom: 170))
		)
	)
)

(instance fromSimbani of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 233 87 setMotion: PolyPath 214 95 self)
			)
			(1
				(HandsOn 6 3)
				(self dispose:)
			)
		)
	)
)

(instance fromSprings of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 140 165 setMotion: PolyPath 152 170 self)
			)
			(1
				(HandsOn 6 3)
				(self dispose:)
			)
		)
	)
)

(instance fromTreePanorama of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: 318 setMotion: PolyPath 314 (ego y?) self)
			)
			(1
				(HandsOn 6 3)
				(self dispose:)
			)
		)
	)
)

(instance fromTarnaPanorama of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst 88) (ego view: 153))
				(ego x: 2 setMotion: PolyPath 6 (ego y?) self)
			)
			(1
				(if (Btst 88)
					(fire init: hide:)
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
				(HandsOff)
				(Bclr 6)
				(ego
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: PolyPath 29 100 self
				)
			)
			(1
				(messager say: 1 6 (localproc_0482) 0 self)
			)
			(2
				(Bclr 6)
				(PalVary pvINIT curRoomNum 3)
				(ego setMotion: PolyPath 30 103 self)
			)
			(3
				(self setScript: campOut self)
			)
			(4
				(ego setMotion: PolyPath 75 107 self)
			)
			(5
				(messager say: 1 6 (localproc_0482) 0 self)
			)
			(6
				(Bclr 6)
				(ego setMotion: PolyPath 107 133 self)
			)
			(7
				(ego setMotion: PolyPath 110 136 self)
			)
			(8
				(ego setMotion: PolyPath 140 130 self)
			)
			(9
				(messager say: 1 6 (localproc_0482) 0 self)
			)
			(10
				(Bclr 6)
				(ego setMotion: PolyPath 165 133 self)
			)
			(11
				(messager say: 1 6 (localproc_0482) 0 self)
			)
			(12
				(Bclr 6)
				(ego setMotion: PolyPath 207 101 self)
			)
			(13
				(messager say: 1 6 (localproc_0482) 0 self)
			)
			(14
				(Bclr 6)
				(ego setMotion: PolyPath 217 94 self)
			)
			(15
				(Bset 85)
				(Bclr 88)
				(Bset 6)
				(= Clock 2600)
				(= Day 3)
				(curRoom newRoom: 410)
			)
		)
	)
)

(instance campOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 1 6 22 0 self)
			)
			(1
				(Bclr 6)
				(fire
					x: (+ (ego x?) 5)
					y: (ego y?)
					show:
					setCycle: Fwd
				)
				(= cycles 5)
			)
			(2 (= seconds 9))
			(3
				(PalVary pvREVERSE 5)
				(fire dispose:)
				(++ Day)
				(= [egoStats 17] (ego maxStamina:))
				(ego takeDamage: -16 useMana: -16)
				(= cycles 5)
			)
			(4 (= seconds 7))
			(5
				(messager say: 1 6 23 0 self)
			)
			(6 (Bclr 6) (self dispose:))
		)
	)
)

(instance fire of Prop
	(properties
		x 115
		y 113
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

(instance poolOfPeace of Feature
	(properties
		x 142
		y 158
		noun 2
		nsTop 152
		nsLeft 121
		nsBottom 164
		nsRight 163
		sightAngle 180
	)
)

(instance simbaniVillage of Feature
	(properties
		x 228
		y 83
		noun 4
		nsTop 79
		nsLeft 208
		nsBottom 87
		nsRight 248
		sightAngle 180
	)
)

(instance bluffs of Feature
	(properties
		x 219
		y 91
		noun 5
		nsTop 82
		nsLeft 190
		nsBottom 100
		nsRight 249
		sightAngle 180
	)
)

(instance highHill of Feature
	(properties
		x 162
		y 90
		noun 6
		nsTop 82
		nsLeft 148
		nsBottom 99
		nsRight 176
		sightAngle 180
	)
)

(instance leapFrogRocks of Feature
	(properties
		x 95
		y 93
		noun 7
		nsTop 89
		nsLeft 68
		nsBottom 98
		nsRight 122
		sightAngle 180
	)
)

(instance miscRocks of Feature
	(properties
		x 171
		y 149
		noun 8
		nsTop 144
		nsLeft 156
		nsBottom 155
		nsRight 186
		sightAngle 180
	)
)

(instance whereIsHe of Code
	(properties)
	
	(method (doit)
		(cond 
			((curRoom script?) 0)
			((ego inRect: 123 154 164 164) (ego setMotion: 0) (curRoom newRoom: 390))
			((> (ego x?) 315) (ego setMotion: 0) (curRoom setScript: toJungle))
			((< (ego x?) 5) (curRoom setScript: toTarna))
			(
			(and (not (Btst 43)) (ego inRect: 208 82 252 92)) (ego setMotion: 0) (curRoom newRoom: 410))
		)
	)
)
