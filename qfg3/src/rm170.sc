;;; Sierra Script 1.0 - (do not remove this comment)
(script# 170)
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
	rm170 0
)

(local
	local0
	local1
	theGGOwnerX_2
	theGGOwnerY_2
	local4
)
(procedure (localproc_03d1)
	(switch local1
		(0 (messager say: 2 6 2))
		(1 (messager say: 2 6 3))
		(2 (messager say: 2 6 4))
		(3 (messager say: 2 6 5))
		(4 (messager say: 2 6 6))
	)
	(++ local1)
	(= local4 0)
)

(procedure (localproc_0445)
	(switch local1
		(0 (messager say: 3 6 8))
		(1 (messager say: 3 6 9))
		(2 (messager say: 3 6 10))
	)
	(++ local1)
	(= local4 0)
)

(instance rm170 of PanoRoom
	(properties
		noun 1
		picture 170
		east 180
		west 160
	)
	
	(method (init)
		(HandsOff)
		(globalSound stop:)
		(self
			setRegions: 50
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 139 66 139 114 153 207 156 219 168 290 179 319 158 319 189 0 189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 212 67 212 73 196 73 196 67
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 49 278 47 248 42 59 42 0 42 0 0 319 0
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 124 133 111 142 77 134 75 127 93 123
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 292 138 292 131 314 132 311 143
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 261 108 289 108 289 121 266 121
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 267 142 281 138 293 141 296 149 267 149
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 203 97 225 97 225 104 203 104
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 261 89 276 89 276 94 261 94
					yourself:
				)
			setScript:
				(switch prevRoomNum
					(400
						(HandsOn 6 3)
						(ego posn: panoEgoX panoEgoY)
						0
					)
					(600 (Bset 94) fromLeopards)
					(630 (Bset 94) fromLeopards)
					(700 fromJungle)
					(east
						(= style 11)
						fromLostCityPanorama
					)
					(west
						(= style 12)
						fromSimbaniPanorama
					)
					(else  fromTree)
				)
		)
		(bigTree init:)
		(giantAntHill init:)
		(gorge init:)
		(mountains init:)
		(sky init:)
		(ego solvePuzzle: 210 3)
		(if (Btst 94) (leopardVillage init:))
		(super init: &rest)
		(cSound number: 170 setLoop: -1 play:)
	)
	
	(method (dispose)
		(DisposeScript 41)
		(DisposeScript 36)
		(super dispose:)
	)
	
	(method (cue)
		(cond 
			((ego inRect: 191 35 214 75) (curRoom newRoom: 750))
			((ego inRect: 246 104 294 131)
				(if (Btst 94)
					(if (== heroType 2)
						(curRoom newRoom: 630)
					else
						(curRoom newRoom: 600)
					)
				else
					(curRoom newRoom: 0)
				)
			)
			((> (ego x?) 315) (self setScript: toLostCity))
			((< (ego x?) 5) (self setScript: toSimbani))
			(else (curRoom newRoom: 0))
		)
	)
)

(instance fromJungle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= theGGOwnerX_2 panoEgoX)
				(= theGGOwnerY_2 panoEgoY)
				(cond 
					((Btst 93)
						(ego view: 154)
						(ego x: panoEgoX y: panoEgoY)
						(Bclr 93)
						(Bset 88)
						(if
							(<
								(= local0
									(/ (GetDistance (ego x?) (ego y?) 319 (ego y?)) 4)
								)
								1
							)
							(messager say: 3 6 8)
							(messager say: 3 6 9)
							(messager say: 3 6 10)
						)
						(curRoom setScript: walkManu)
					)
					((Btst 92)
						(Bset 88)
						(ego view: 152)
						(ego x: panoEgoX y: panoEgoY)
						(if
							(<
								(= local0
									(/ (GetDistance (ego x?) (ego y?) 260 115) 6)
								)
								1
							)
							(messager say: 2 6 2)
							(messager say: 2 6 3)
							(messager say: 2 6 4)
							(messager say: 2 6 5)
							(messager say: 2 6 6)
						)
						(curRoom setScript: walkJohari)
					)
					(else
						(HandsOn 6 3)
						(ego x: panoEgoX y: panoEgoY)
						(self dispose:)
					)
				)
			)
		)
	)
)

(instance walkManu of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(not
						(mod
							(GetDistance
								(ego x?)
								(ego y?)
								theGGOwnerX_2
								theGGOwnerY_2
							)
							local0
						)
					)
					(not local4)
				)
				(= local4 1)
				(localproc_0445)
			)
			((and (> Clock 2750) (Btst 81))
				(= local4 1)
				(ego setMotion: 0)
				(curRoom setScript: campOutManu)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					moveSpeed: 6
					cycleSpeed: 6
					setMotion: PolyPath 319 (ego y?) self
				)
			)
			(1 (curRoom newRoom: 180))
		)
	)
)

(instance campOutManu of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 3 6 14 0 self)
			)
			(1
				(PalVary pvCHANGE_TICKS 3)
				(fire
					x: (+ (ego x?) 5)
					y: (ego y?)
					init:
					setCycle: Fwd
				)
				(= cycles 5)
			)
			(2 (= seconds 9))
			(3
				(PalVary pvREVERSE 5)
				(Bclr 81)
				(fire dispose:)
				(= Clock 800)
				(++ Day)
				(= [egoStats 17] (ego maxStamina:))
				(ego takeDamage: -16 useMana: -16)
				(= cycles 5)
			)
			(4 (= seconds 7))
			(5
				(messager say: 3 6 13 0 self)
			)
			(6
				(= local4 0)
				(curRoom setScript: walkManu)
			)
		)
	)
)

(instance walkJohari of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(not
						(mod
							(GetDistance
								(ego x?)
								(ego y?)
								theGGOwnerX_2
								theGGOwnerY_2
							)
							local0
						)
					)
					(not local4)
				)
				(localproc_03d1)
				(= local4 0)
			)
			((and (> Clock 2750) (Btst 81) (not local4))
				(= local4 1)
				(ego setMotion: 0)
				(curRoom setScript: campOutJohari)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 5))
			(1
				(ego
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: PolyPath 260 115 self
				)
			)
			(2 (= cycles 10))
			(3 (messager say: 2 6 7 0 self))
			(4 (curRoom newRoom: 600))
		)
	)
)

(instance campOutJohari of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 2 6 14 0 self)
			)
			(1
				(PalVary pvCHANGE_TICKS 3)
				(fire
					x: (+ (ego x?) 5)
					y: (ego y?)
					init:
					setCycle: Fwd
				)
				(= cycles 5)
			)
			(2 (= seconds 9))
			(3
				(PalVary pvREVERSE 5)
				(Bclr 81)
				(fire dispose:)
				(= Clock 800)
				(++ Day)
				(= [egoStats 17] (ego maxStamina:))
				(ego takeDamage: -16 useMana: -16)
				(= cycles 5)
			)
			(4 (= seconds 7))
			(5
				(messager say: 2 6 13 0 self)
			)
			(6
				(= local4 0)
				(curRoom setScript: walkJohari)
			)
		)
	)
)

(instance toLostCity of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 319 (ego y?) self)
			)
			(1 (curRoom newRoom: 180))
		)
	)
)

(instance toSimbani of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 1 (ego y?) self)
			)
			(1 (curRoom newRoom: 160))
		)
	)
)

(instance fromSimbaniPanorama of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					x: 2
					setMotion: MoveTo (if (Btst 43) 15 else 10) (ego y?) self
				)
			)
			(1
				(if (Btst 43)
					(Bset 143)
					(= monsterNum 9)
					(curRoom newRoom: 700)
				else
					(HandsOn 6 3)
					(self dispose:)
				)
			)
		)
	)
)

(instance fromLostCityPanorama of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: 318 setMotion: PolyPath 310 (ego y?) self)
			)
			(1
				(HandsOn 6 3)
				(self dispose:)
			)
		)
	)
)

(instance fromLeopards of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr 92)
				(ego x: 269 y: 132 setMotion: PolyPath 254 142 self)
			)
			(1
				(HandsOn 6 3)
				(self dispose:)
			)
		)
	)
)

(instance fromTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: 205 y: 76 setMotion: PolyPath 200 82 self)
			)
			(1
				(HandsOn 6 3)
				(self dispose:)
			)
		)
	)
)

(instance leopardVillage of Prop
	(properties
		x 262
		y 129
		noun 10
		view 150
		loop 1
		cel 1
		signal $4000
	)
	
	(method (init)
		(super init:)
		(Bset 94)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(ego setMotion: PolyPath 260 115 self)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(curRoom newRoom: 600)
	)
)

(instance fire of Prop
	(properties
		x 115
		y 113
		view 150
	)
)

(instance bigTree of Feature
	(properties
		x 202
		y 53
		noun 5
		nsTop 35
		nsLeft 184
		nsBottom 72
		nsRight 220
	)
)

(instance giantAntHill of Feature
	(properties
		x 98
		y 132
		noun 6
		nsTop 126
		nsLeft 77
		nsBottom 139
		nsRight 119
		sightAngle 180
	)
)

(instance gorge of Feature
	(properties
		x 159
		y 37
		noun 7
		nsTop 32
		nsBottom 42
		nsRight 319
	)
)

(instance mountains of Feature
	(properties
		x 159
		y 13
		noun 8
		nsTop 6
		nsBottom 20
		nsRight 319
	)
)

(instance sky of Feature
	(properties
		x 159
		y 3
		noun 9
		nsBottom 7
		nsRight 319
	)
)
