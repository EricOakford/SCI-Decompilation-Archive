;;; Sierra Script 1.0 - (do not remove this comment)
(script# 160)
(include game.sh) (include "160.shm")
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
	rakeeshMsg
	local2
)
(procedure (ClassTalk)
	(return
		(switch heroType
			(FIGHTER
				(switch (++ rakeeshMsg)
					(1 (return C_FIGHTER1))
					(2 (return C_FIGHTER2))
					(3 (return C_FIGHTER3))
					(4 (return C_FIGHTER4))
					(else  (return C_END_TALK))
				)
			)
			(MAGIC_USER
				(switch (++ rakeeshMsg)
					(1 (return 11))
					(2 (return 12))
					(3 (return 13))
					(4 (return 14))
					(else  (return C_END_TALK))
				)
			)
			(THIEF
				(switch (++ rakeeshMsg)
					(1 (return C_THIEF1))
					(2 (return C_THIEF2))
					(3 (return C_THIEF3))
					(4 (return C_THIEF4))
					(else  (return C_THIEF5))
				)
			)
			(else 	;PALADIN
				(switch (++ rakeeshMsg)
					(1 (return C_PALADIN1))
					(2 (return C_PALADIN2))
					(3 (return C_PALADIN3))
					(4 (return C_PALADIN4))
					(else  (return C_PALADIN5))
				)
			)
		)
	)
)

(instance rm160 of PanoRoom
	(properties
		noun N_ROOM
		picture 160
		east 170
		west 150
	)
	
	(method (init)
		(self
			setRegions: 50
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						122 89
						124 99
						64 99
						64 89
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						177 91
						185 105
						179 117
						153 117
						168 101
						150 100
						141 91
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						176 177
						319 156
						319 189
						0 189
						0 159
						54 159
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						251 80
						251 87
						213 87
						205 80
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						319 0
						319 42
						262 42
						173 39
						152 46
						112 52
						0 53
						0 0
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						171 72
						171 76
						148 76
						148 72
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						102 127
						142 121
						148 130
						125 139
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						109 149
						124 145
						142 151
						159 143
						190 143
						189 154
						159 166
						124 162
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						194 101
						185 93
						198 81
						214 90
						214 100
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						214 140
						238 140
						238 145
						214 145
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						220 95
						223 91
						239 89
						248 90
						251 94
						231 100
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
						(= style SCROLLRIGHT)
						fromTreePanorama
					)
					(west
						(= style SCROLLLEFT)
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
		(ego solvePuzzle: fEnteredSimbaniPanorama 3)
		(if (and (Btst fFoundHoneyBird) (== honeyBirdRoom curRoomNum))
			(honeyTree x: honeyTreeX y: honeyTreeY init: stopUpd:)
		)
		(HandsOn 6 3)
		(super init: &rest)
		(if (not (Btst fTravelWithSomeone))
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
				(cond
					((Btst fAfterConference)
						;This should allow for entering the Simbani village one last time
						(Bset fCantEnterSimbani)
						(curRoom newRoom: 410)
					)
					((Btst fCantEnterSimbani)
						(self setScript: tellScript)
					)
					(else
						(curRoom newRoom: 410)
					)
				)
			)
			(
				(and
					(Btst fFoundHoneyBird)
					(== honeyBirdRoom curRoomNum)
					(ego
						inRect: honeyTreeX (- honeyTreeY 20) (+ honeyTreeX 20) honeyTreeY
					)
				)
				(Bset fStartedEncounter)
				(= monsterNum 2)
				(self newRoom: 400)
			)
			((ego inRect: 128 153 162 174)
				(curRoom newRoom: 390)
			)
			((> (ego x?) 315)
				(curRoom setScript: toJungle)
			)
			((< (ego x?) 5)
				(curRoom setScript: toTarna)
			)
			(else
				(curRoom newRoom: 0)
			)
		)
	)
)

(instance tellScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_TELL_SCRIPT V_DOIT C_NO_ENTRY 0 self)
			)
			(1
				(HandsOff)
				(curRoom newRoom: 400)
			)
		)
	)
)

(instance toTarna of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 1 (ego y?) self)
			)
			(1
				(curRoom newRoom: 150)
			)
		)
	)
)

(instance toJungle of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 319 (ego y?) self)
			)
			(1
				(curRoom newRoom: 170)
			)
		)
	)
)

(instance fromSimbani of Script
	
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
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst fTravelWithSomeone)
					(ego view: 153)
				)
				(ego x: 2 setMotion: PolyPath 6 (ego y?) self)
			)
			(1
				(if (Btst fTravelWithSomeone)
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

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bclr fInMainGame)
				(ego
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: PolyPath 29 100 self
				)
			)
			(1
				(messager say: N_RAKEESH_WALK V_DOIT (ClassTalk) 0 self)
			)
			(2
				(Bclr fInMainGame)
				(PalVary PALVARYSTART curRoomNum 3)
				(ego setMotion: PolyPath 30 103 self)
			)
			(3
				(self setScript: campOut self)
			)
			(4
				(ego setMotion: PolyPath 75 107 self)
			)
			(5
				(messager say: N_RAKEESH_WALK V_DOIT (ClassTalk) 0 self)
			)
			(6
				(Bclr fInMainGame)
				(ego setMotion: PolyPath 107 133 self)
			)
			(7
				(ego setMotion: PolyPath 110 136 self)
			)
			(8
				(ego setMotion: PolyPath 140 130 self)
			)
			(9
				(messager say: N_RAKEESH_WALK V_DOIT (ClassTalk) 0 self)
			)
			(10
				(Bclr fInMainGame)
				(ego setMotion: PolyPath 165 133 self)
			)
			(11
				(messager say: N_RAKEESH_WALK V_DOIT (ClassTalk) 0 self)
			)
			(12
				(Bclr fInMainGame)
				(ego setMotion: PolyPath 207 101 self)
			)
			(13
				(messager say: N_RAKEESH_WALK V_DOIT (ClassTalk) 0 self)
			)
			(14
				(Bclr fInMainGame)
				(ego setMotion: PolyPath 217 94 self)
			)
			(15
				(Bset fCanGoToSimbani)
				(Bclr fTravelWithSomeone)
				(Bset fInMainGame)
				(= Clock 2600)
				(= Day 3)
				(curRoom newRoom: 410)
			)
		)
	)
)

(instance campOut of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_RAKEESH_WALK V_DOIT C_CAMP 0 self)
			)
			(1
				(Bclr fInMainGame)
				(fire
					x: (+ (ego x?) 5)
					y: (ego y?)
					show:
					setCycle: Forward
				)
				(= cycles 5)
			)
			(2
				(= seconds 9)
			)
			(3
				(PalVary PALVARYREVERSE 5)
				(fire dispose:)
				(++ Day)
				(= [egoStats STAMINA] (ego maxStamina:))
				(ego takeDamage: -16 useMana: -16)
				(= cycles 5)
			)
			(4
				(= seconds 7)
			)
			(5
				(messager say: N_RAKEESH_WALK V_DOIT C_GOOD_MORNING 0 self)
			)
			(6
				(Bclr fInMainGame)
				(self dispose:)
			)
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
		signal ignrAct
	)
)

(instance poolOfPeace of Feature
	(properties
		x 142
		y 158
		noun N_POOL_OF_PEACE
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
		noun N_SIMBANI_VILLAGE
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
		noun N_BLUFFS
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
		noun N_HIGH_HILL
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
		noun N_LEAPFROG_ROCKS
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
		noun N_MISC_ROCKS
		nsTop 144
		nsLeft 156
		nsBottom 155
		nsRight 186
		sightAngle 180
	)
)

(instance whereIsHe of Code
	
	(method (doit)
		(cond 
			((curRoom script?) 0)
			((ego inRect: 123 154 164 164)
				(ego setMotion: 0)
				(curRoom newRoom: 390)
			)
			((> (ego x?) 315)
				(ego setMotion: 0)
				(curRoom setScript: toJungle)
			)
			((< (ego x?) 5)
				(curRoom setScript: toTarna)
			)
			((and (not (Btst fAfterConference)) (ego inRect: 208 82 252 92))
				(ego setMotion: 0)
				(curRoom newRoom: 410)
			)
		)
	)
)
