;;; Sierra Script 1.0 - (do not remove this comment)
(script# 170)
(include game.sh) (include "170.shm")
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
	companionXY
	talkCount
	enterX
	enterY
	talkCued
)
(procedure (JohariSpeaks)
	(switch talkCount
		(0 (messager say: N_JOHARI V_DOIT 2))
		(1 (messager say: N_JOHARI V_DOIT 3))
		(2 (messager say: N_JOHARI V_DOIT 4))
		(3 (messager say: N_JOHARI V_DOIT 5))
		(4 (messager say: N_JOHARI V_DOIT 6))
	)
	(++ talkCount)
	(= talkCued 0)
)

(procedure (ManuSpeaks)
	(switch talkCount
		(0 (messager say: N_MANU V_DOIT 8))
		(1 (messager say: N_MANU V_DOIT 9))
		(2 (messager say: N_MANU V_DOIT 10))
	)
	(++ talkCount)
	(= talkCued 0)
)

(instance rm170 of PanoRoom
	(properties
		noun N_ROOM
		picture 170
		east 180
		west 160
	)
	
	(method (init)
		(HandsOff)
		(globalSound stop:)
		(self
			setRegions: PANORAMA
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 139
						66 139
						114 153
						207 156
						219 168
						290 179
						319 158
						319 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						212 67
						212 73
						196 73
						196 67
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						319 49
						278 47
						248 42
						59 42
						0 42
						0 0
						319 0
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						124 133
						111 142
						77 134
						75 127
						93 123
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						292 138
						292 131
						314 132
						311 143
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						261 108
						289 108
						289 121
						266 121
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						267 142
						281 138
						293 141
						296 149
						267 149
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						203 97
						225 97
						225 104
						203 104
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						261 89
						276 89
						276 94
						261 94
					yourself:
				)
			setScript:
				(switch prevRoomNum
					(400
						(HandsOn 6 3)
						(ego posn: panoEgoX panoEgoY)
						0
					)
					(600
						(Bset fCanGoToLeopardmanVillage)
						fromLeopards
					)
					(630
						(Bset fCanGoToLeopardmanVillage)
						fromLeopards
					)
					(700 fromJungle)
					(east
						(= style SCROLLRIGHT)
						fromLostCityPanorama
					)
					(west
						(= style SCROLLLEFT)
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
		(ego solvePuzzle: fEnteredGiantTreePanorama 3)
		(if (Btst fCanGoToLeopardmanVillage)
			(leopardVillage init:)
		)
		(super init: &rest)
		(cSound number: 170 setLoop: -1 play:)
	)
	
	(method (dispose)
		(DisposeScript MONkEY_TALKER)
		(DisposeScript JOHARI_TALKER)
		(super dispose:)
	)
	
	(method (cue)
		(cond 
			((ego inRect: 191 35 214 75)
				(curRoom newRoom: 750)
			)
			((ego inRect: 246 104 294 131)
				(if (Btst fCanGoToLeopardmanVillage)
					(if (== heroType THIEF)
						(curRoom newRoom: 630)
					else
						(curRoom newRoom: 600)
					)
				else
					(curRoom newRoom: 0)
				)
			)
			((> (ego x?) 315)
				(self setScript: toLostCity)
			)
			((< (ego x?) 5)
				(self setScript: toSimbani)
			)
			(else
				(curRoom newRoom: 0)
			)
		)
	)
)

(instance fromJungle of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= enterX panoEgoX)
				(= enterY panoEgoY)
				(cond 
					((Btst fGoWithManu)
						(ego view: 154)
						(ego x: panoEgoX y: panoEgoY)
						(Bclr fGoWithManu)
						(Bset fTravelWithSomeone)
						(if
							(<
								(= companionXY
									(/ (GetDistance (ego x?) (ego y?) 319 (ego y?)) 4)
								)
								1
							)
							(messager say: N_MANU V_DOIT C_MANU_TALK1)
							(messager say: N_MANU V_DOIT C_MANU_TALK2)
							(messager say: N_MANU V_DOIT C_MANU_TALK3)
						)
						(curRoom setScript: walkManu)
					)
					((Btst fGoWithJohari)
						(Bset fTravelWithSomeone)
						(ego view: 152)
						(ego x: panoEgoX y: panoEgoY)
						(if
							(<
								(= companionXY
									(/ (GetDistance (ego x?) (ego y?) 260 115) 6)
								)
								1
							)
							(messager say: N_JOHARI V_DOIT C_JOHARI_TALK1)
							(messager say: N_JOHARI V_DOIT C_JOHARI_TALK2)
							(messager say: N_JOHARI V_DOIT C_JOHARI_TALK3)
							(messager say: N_JOHARI V_DOIT C_JOHARI_TALK4)
							(messager say: N_JOHARI V_DOIT C_JOHARI_TALK5)
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
								enterX
								enterY
							)
							companionXY
						)
					)
					(not talkCued)
				)
				(= talkCued TRUE)
				(ManuSpeaks)
			)
			((and (> Clock 2750) (Btst fEgoIsAsleep))
				(= talkCued TRUE)
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
			(1
				(curRoom newRoom: 180)
			)
		)
	)
)

(instance campOutManu of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_MANU V_DOIT C_GOOD_NIGHT 0 self)
			)
			(1
				(PalVary PALVARYNEWTIME 3)
				(fire
					x: (+ (ego x?) 5)
					y: (ego y?)
					init:
					setCycle: Forward
				)
				(= cycles 5)
			)
			(2
				(= seconds 9)
			)
			(3
				(PalVary PALVARYREVERSE 5)
				(Bclr fEgoIsAsleep)
				(fire dispose:)
				(= Clock 800)
				(++ Day)
				(= [egoStats STAMINA] (ego maxStamina:))
				(ego takeDamage: -16 useMana: -16)
				(= cycles 5)
			)
			(4 (= seconds 7))
			(5
				(messager say: N_MANU V_DOIT C_GOOD_MORNING 0 self)
			)
			(6
				(= talkCued 0)
				(curRoom setScript: walkManu)
			)
		)
	)
)

(instance walkJohari of Script
	
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
								enterX
								enterY
							)
							companionXY
						)
					)
					(not talkCued)
				)
				(JohariSpeaks)
				(= talkCued FALSE)
			)
			((and (> Clock 2750) (Btst fEgoIsAsleep) (not talkCued))
				(= talkCued TRUE)
				(ego setMotion: 0)
				(curRoom setScript: campOutJohari)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 5)
			)
			(1
				(ego
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: PolyPath 260 115 self
				)
			)
			(2
				(= cycles 10)
			)
			(3
				(messager say: N_JOHARI V_DOIT C_JOHARI_TALK6 0 self)
			)
			(4
				(curRoom newRoom: 600)
			)
		)
	)
)

(instance campOutJohari of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_JOHARI V_DOIT C_GOOD_NIGHT 0 self)
			)
			(1
				(PalVary PALVARYNEWTIME 3)
				(fire
					x: (+ (ego x?) 5)
					y: (ego y?)
					init:
					setCycle: Forward
				)
				(= cycles 5)
			)
			(2
				(= seconds 9)
			)
			(3
				(PalVary PALVARYREVERSE 5)
				(Bclr fEgoIsAsleep)
				(fire dispose:)
				(= Clock 800)
				(++ Day)
				(= [egoStats STAMINA] (ego maxStamina:))
				(ego takeDamage: -16 useMana: -16)
				(= cycles 5)
			)
			(4
				(= seconds 7)
			)
			(5
				(messager say: N_JOHARI V_DOIT C_GOOD_MORNING 0 self)
			)
			(6
				(= talkCued FALSE)
				(curRoom setScript: walkJohari)
			)
		)
	)
)

(instance toLostCity of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 319 (ego y?) self)
			)
			(1
				(curRoom newRoom: 180)
			)
		)
	)
)

(instance toSimbani of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 1 (ego y?) self)
			)
			(1
				(curRoom newRoom: 160)
			)
		)
	)
)

(instance fromSimbaniPanorama of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					x: 2
					setMotion: MoveTo (if (Btst fAfterConference) 15 else 10) (ego y?) self
				)
			)
			(1
				(if (Btst fAfterConference)
					(Bset fStartedEncounter)
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
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr fGoWithJohari)
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
		noun N_LEOPARDMAN_VILLAGE
		view 150
		loop 1
		cel 1
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(Bset fCanGoToLeopardmanVillage)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(ego setMotion: PolyPath 260 115 self)
			)
			(else
				(super doVerb: theVerb)
			)
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
		noun N_BIG_TREE
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
		noun N_GIANT_ANT_HILL
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
		noun N_GORGE
		nsTop 32
		nsBottom 42
		nsRight 319
	)
)

(instance mountains of Feature
	(properties
		x 159
		y 13
		noun N_MOUNTAIN
		nsTop 6
		nsBottom 20
		nsRight 319
	)
)

(instance sky of Feature
	(properties
		x 159
		y 3
		noun N_SKY
		nsBottom 7
		nsRight 319
	)
)
