;;; Sierra Script 1.0 - (do not remove this comment)
(script# 180)
(include game.sh) (include "180.shm")
(use Main)
(use PanoRoom)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm180 0
)

(local
	talkCued
	talkCount
	local2
	local3
	camping
	saveTime
	theState
	companionXY
)
(procedure (ManuSpeaks)
	(switch talkCount
		(0 (messager say: N_MANU V_DOIT C_TALK_MONKEYVILLE1))
		(1 (messager say: N_MANU V_DOIT C_TALK_MONKEYVILLE2))
	)
	(++ talkCount)
	(= talkCued 0)
)

(instance rm180 of PanoRoom
	(properties
		noun N_ROOM
		picture 180
		west 170
	)
	
	(method (init)
		(Bset fBeenInLostCityPanorama)
		(self setRegions: PANORAMA)
		(ego solvePuzzle: fEnteredLostCityPanorama 8)
		(switch prevRoomNum
			(400
				(ego posn: panoEgoX panoEgoY)
				(HandsOn 6 3)
			)
			(800
				(curRoom setScript: fromLostCity)
			)
			(700
				(ego x: panoEgoX y: panoEgoY)
				(cond 
					((Btst fGoWithManu)
						(Bset fTravelWithSomeone)
						(ego view: 154)
						(= companionXY
							(/ (GetDistance (ego x?) (ego y?) 162 72) 3)
						)
						(HandsOff)
						(curRoom setScript: goVillage)
					)
					((Btst fAfterWaterfall)
						(Bclr fAfterWaterfall)
						(HandsOff)
						(Bset fTravelWithSomeone)
						(ego view: 154)
						(curRoom setScript: leadEgo)
					)
					(else (HandsOn 6 3))
				)
			)
			(740
				(ego x: 175 y: 112)
				(ego view: 154)
				(curRoom setScript: afterWater)
			)
			(west
				(= style SCROLLLEFT)
				(curRoom setScript: fromTreePanorama)
			)
			(else 
				(ego x: 158 y: 75)
				(ego view: 154)
				(curRoom setScript: fromMonkeys)
			)
		)
		(wtrfll_1 init: setCycle: Forward)
		(wtrfll_2 init: setCycle: Forward)
		(wtrfll_3 init: setCycle: Forward)
		(wtrfll_4 init: setCycle: Forward)
		(upperRiver init:) ;EO: Was unused; now it's init'd properly
		(lostCity init:)
		(upperWaterfall init:)
		(pool init:)
		(mountains init:)
		(lowerCut init:)
		(eastCut init:)
		(lowerWaterfall init:)
		(lowerPool init:)
		(upperWaterfall init:)
		(upperCut init:)
		(if (Btst fTravelWithSomeone)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							0 157
							92 136
							142 121
							153 113
							162 114
							168 109
							141 111
							84 115
							77 105
							122 99
							115 88
							162 76
							161 71
							105 88
							0 69
							0 0
							319 0
							319 189
							0 189
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							56 121
							80 114
							87 126
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							47 103
							72 104
							73 110
							52 114
							41 118
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							0 157
							92 136
							142 121
							153 113
							162 114
							168 109
							141 111
							84 115
							77 105
							142 96
							103 89
							0 69
							0 0
							319 0
							319 189
							0 189
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							56 121
							80 114
							87 126
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							47 103
							72 104
							73 110
							52 114
							41 118
						yourself:
					)
			)
		)
		(super init: &rest)
		(cSound number: 180 setLoop: -1 play:)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (> (Abs (- gameTime saveTime)) 10)
			(= saveTime gameTime)
			(Palette PALCycle 232 235 -1)
		)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript MONkEY_TALKER)
	)
	
	(method (cue)
		(cond 
			((ego inRect: 146 69 181 93)
				(self newRoom: 720)
			)
			((ego inRect: 246 99 284 118)
				(self newRoom: 800)
			)
			((< (ego x?) 5)
				(self setScript: toJungle)
			)
			(else
				(self newRoom: 0)
			)
		)
	)
)

(instance toJungle of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 1 (ego y?) self)
			)
			(1
				(curRoom newRoom: 170)
			)
		)
	)
)

(instance fromLostCity of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: 251 y: 111 setMotion: PolyPath 239 110 self)
			)
			(1
				(HandsOn 6 3)
				(self dispose:)
			)
		)
	)
)

(instance fromMonkeys of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (Btst fWillGoToWaterfall)
					(Bclr fWillGoToWaterfall)
					(Bset fTravelWithSomeone)
					(HandsOff)
					(curRoom setScript: toWaterFall)
				else
					(ego x: 164 y: 75 setMotion: PolyPath 138 82 self)
				)
			)
			(1
				(HandsOn 6 3)
				(self dispose:)
			)
		)
	)
)

(instance toWaterFall of Script
	
	(method (doit)
		(super doit:)
		(if (and (> Clock 2750) (Btst fEgoIsAsleep) (not talkCued))
			(= talkCued TRUE)
			(ego setMotion: 0)
			(= camping TRUE)
			(curRoom setScript: campOutManu 0 state)
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
					setMotion: PolyPath 110 98 self
				)
			)
			(2
				(messager say: N_MANU V_DOIT C_TALK_LOSTCITY1 0 self)
			)
			(3
				(ego setMotion: PolyPath 77 110 self)
			)
			(4
				(messager say: N_MANU V_DOIT C_TALK_LOSTCITY2 0 self)
			)
			(5
				(ego setMotion: PolyPath 120 117 self)
			)
			(6
				(messager say: N_MANU V_DOIT C_TALK_LOSTCITY3 0 self)
			)
			(7
				(ego setMotion: PolyPath 165 112 self)
			)
			(8
				(= talkCued TRUE)
				(messager say: N_MANU V_DOIT C_TALK_LOSTCITY6 0 self)
			)
			(9
				(curRoom newRoom: 740)
			)
		)
	)
)

(instance afterWater of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fTravelWithSomeone)
				(ego
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo (+ (ego x?) 7) (ego y?) self
				)
			)
			(1
				(messager say: N_MANU V_DOIT C_TALK_LOSTCITY7 0 self)
			)
			(2
				(ego setMotion: MoveTo (+ (ego x?) 5) (ego y?) self)
			)
			(3
				(= monsterNum 590)
				(Bset fStartedEncounter)
				(Bset fMonkeysFindDeWorm)
				(Bset fAfterWaterfall)
				(curRoom newRoom: 700)
			)
		)
	)
)

(instance leadEgo of Script

	(method (doit)
		(super doit: &rest)
		(if (and (> Clock 2750) (Btst fEgoIsAsleep))
			(= talkCued TRUE)
			(ego setMotion: 0)
			(= camping FALSE)
			(curRoom setScript: campOutManu 0 state)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fTravelWithSomeone)
				(ego
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 193 113 self
				)
			)
			(1
				(messager say: N_MANU V_DOIT C_TALK_LOSTCITY4 0 self)
			)
			(2
				(ego setMotion: MoveTo 232 108 self)
			)
			(3
				(messager say: N_MANU V_DOIT C_TALK_LOSTCITY5 0 self)
			)
			(4
				(ego setMotion: MoveTo 260 112 self)
			)
			(5
				(messager say: N_MANU V_DOIT C_TALK_LOSTCITY8 0 self)
			)
			(6
				(Bclr fTravelWithSomeone)
				(curRoom newRoom: 800)
			)
		)
	)
)

(instance campOutManu of Script
	
	(method (changeState newState &tmp temp0)
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
			(4
				(= seconds 7)
			)
			(5
				(messager say: N_MANU V_DOIT C_GOOD_MORNING 0 self)
			)
			(6
				(= talkCued FALSE)
				(if (> register 0)
					(= temp0 (- register 1))
				else
					(= temp0 0)
				)
				(if camping
					(toWaterFall start: temp0)
					(curRoom setScript: toWaterFall)
				else
					(leadEgo start: temp0)
					(curRoom setScript: leadEgo)
				)
			)
		)
	)
)

(instance fromTreePanorama of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 2 setMotion: PolyPath 10 (ego y?) self)
			)
			(1
				(if (Btst fTravelWithSomeone)
					(ego view: 154)
					(curRoom setScript: manuWalk)
				else
					(HandsOn 6 3)
					(self dispose:)
				)
			)
		)
	)
)

(instance manuWalk of Script
	
	(method (doit)
		(super doit: &rest)
		(if (== (PalVary PALVARYINFO) 20)
			(= theState state)
			(curRoom setScript: campOut 0 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: PolyPath 50 92 self
				)
			)
			(1
				(messager say: N_MANU V_DOIT C_TALK_MONKEYVILLE1 0 self)
			)
			(2
				(ego setMotion: PolyPath 105 86 self)
			)
			(3
				(messager say: N_MANU V_DOIT C_TALK_MONKEYVILLE2 0 self)
			)
			(4
				(ego setMotion: PolyPath 160 72 self)
			)
			(5
				(messager say: N_MANU V_DOIT C_TALK_MONKEYVILLE3 0 self)
			)
			(6
				(curRoom newRoom: 720)
			)
		)
	)
)

(instance campOut of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_MANU V_DOIT C_GOOD_NIGHT 0 self)
			)
			(1
				(PalVary PALVARYNEWTIME 2)
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
				(messager say: N_MANU V_DOIT C_GOOD_MORNING 0 self)
			)
			(6
				(if register
					(curRoom setScript: goVillage)
				else
					(curRoom setScript: manuWalk)
					(manuWalk changeState: theState)
				)
			)
		)
	)
)

(instance goVillage of Script
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(not
						(mod
							(GetDistance (ego x?) (ego y?) local2 local3)
							companionXY
						)
					)
					(not talkCued)
				)
				(= talkCued TRUE)
				(ManuSpeaks)
			)
			((== (PalVary PALVARYINFO) 20)
				(ego setMotion: 0)
				(curRoom setScript: campOut 0 1)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 160 72 self)
			)
			(1
				(messager say: N_MANU V_DOIT C_TALK_MONKEYVILLE3 0 self)
			)
			(2
				(curRoom newRoom: 720)
			)
		)
	)
)

(instance fire of Prop
	(properties
		x 115
		y 113
		view 986
		loop 4
	)
)

(instance wtrfll_1 of Prop
	(properties
		x 181
		y 95
		view 160
		cel 6
		name "wtrfll#1"
	)
	
	(method (doVerb theVerb)
		(upperWaterfall doVerb: theVerb)
	)
)

(instance wtrfll_2 of Prop
	(properties
		x 171
		y 110
		view 160
		loop 1
		cel 1
		name "wtrfll#2"
	)
	
	(method (doVerb theVerb)
		(upperWaterfall doVerb: theVerb)
	)
)

(instance wtrfll_3 of Prop
	(properties
		x 166
		y 121
		view 160
		loop 2
		cel 2
		name "wtrfll#3"
	)
	
	(method (doVerb theVerb)
		(lowerWaterfall doVerb: theVerb)
	)
)

(instance wtrfll_4 of Prop
	(properties
		x 163
		y 157
		view 160
		loop 3
		cel 7
		name "wtrfll#4"
	)
	
	(method (doVerb theVerb)
		(lowerWaterfall doVerb: theVerb)
	)
)

(instance lostCity of Feature
	(properties
		x 259
		y 109
		noun N_LOST_CITY
		nsTop 97
		nsLeft 241
		nsBottom 121
		nsRight 278
	)
)

(instance upperWaterfall of Feature
	(properties
		x 184
		y 88
		noun N_UPPER_WATERFALL
		nsTop 80
		nsLeft 178
		nsBottom 97
		nsRight 190
	)
)

(instance pool of Feature
	(properties
		x 174
		y 98
		noun N_POOL
		nsTop 93
		nsLeft 161
		nsBottom 104
		nsRight 188
	)
)

(instance lowerWaterfall of Feature
	(properties
		x 163
		y 132
		noun N_LOWER_WATERFALL
		nsTop 104
		nsLeft 159
		nsBottom 160
		nsRight 168
	)
)

(instance lowerPool of Feature
	(properties
		x 147
		y 165
		noun N_LOWER_POOL
		nsTop 155
		nsLeft 116
		nsBottom 176
		nsRight 179
	)
)

(instance upperRiver of Feature
	(properties
		x 207
		y 67
		noun N_UPPER_RIVER
		nsTop 59
		nsLeft 180
		nsBottom 76
		nsRight 234
	)
)

(instance mountains of Feature
	(properties
		x 179
		y 8
		noun N_MOUNTAINS
		nsTop 3
		nsLeft 104
		nsBottom 14
		nsRight 254
	)
)

(instance lowerCut of Feature
	(properties
		x 96
		y 78
		noun N_LOWER_CUT
		nsTop 71
		nsLeft 37
		nsBottom 85
		nsRight 155
	)
)

(instance upperCut of Feature
	(properties
		x 76
		y 40
		noun N_UPPER_CUT
		nsTop 36
		nsBottom 44
		nsRight 153
	)
)

(instance eastCut of Feature
	(properties
		x 218
		y 93
		noun N_EAST_CUT
		nsTop 86
		nsLeft 189
		nsBottom 100
		nsRight 248
	)
)
