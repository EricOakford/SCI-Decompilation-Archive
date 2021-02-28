;;; Sierra Script 1.0 - (do not remove this comment)
(script# 180)
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
	rm180 0
)

(local
	local0
	local1
	local2
	local3
	local4
	theGameTime
	theState
	local7
)
(procedure (localproc_04a4)
	(switch local1
		(0 (messager say: 1 6 10))
		(1 (messager say: 1 6 11))
	)
	(++ local1)
	(= local0 0)
)

(instance rm180 of PanoRoom
	(properties
		noun 2
		picture 180
		west 170
	)
	
	(method (init)
		(Bset 63)
		(self setRegions: 50)
		(ego solvePuzzle: 211 8)
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
					((Btst 93)
						(Bset 88)
						(ego view: 154)
						(= local7
							(/ (GetDistance (ego x?) (ego y?) 162 72) 3)
						)
						(HandsOff)
						(curRoom setScript: goVillage)
					)
					((Btst 134)
						(Bclr 134)
						(HandsOff)
						(Bset 88)
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
				(= style 12)
				(curRoom setScript: fromTreePanorama)
			)
			(else 
				(ego x: 158 y: 75)
				(ego view: 154)
				(curRoom setScript: fromMonkeys)
			)
		)
		(wtrfll_1 init: setCycle: Fwd)
		(wtrfll_2 init: setCycle: Fwd)
		(wtrfll_3 init: setCycle: Fwd)
		(wtrfll_4 init: setCycle: Fwd)
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
		(if (Btst 88)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							0
							157
							92
							136
							142
							121
							153
							113
							162
							114
							168
							109
							141
							111
							84
							115
							77
							105
							122
							99
							115
							88
							162
							76
							161
							71
							105
							88
							0
							69
							0
							0
							319
							0
							319
							189
							0
							189
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 56 121 80 114 87 126
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 47 103 72 104 73 110 52 114 41 118
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							0
							157
							92
							136
							142
							121
							153
							113
							162
							114
							168
							109
							141
							111
							84
							115
							77
							105
							142
							96
							103
							89
							0
							69
							0
							0
							319
							0
							319
							189
							0
							189
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 56 121 80 114 87 126
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 47 103 72 104 73 110 52 114 41 118
						yourself:
					)
			)
		)
		(super init: &rest)
		(cSound number: 180 setLoop: -1 play:)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (> (Abs (- gameTime theGameTime)) 10)
			(= theGameTime gameTime)
			(Palette palANIMATE 232 235 -1)
		)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 41)
	)
	
	(method (cue)
		(cond 
			((ego inRect: 146 69 181 93) (self newRoom: 720))
			((ego inRect: 246 99 284 118) (self newRoom: 800))
			((< (ego x?) 5) (self setScript: toJungle))
			(else (self newRoom: 0))
		)
	)
)

(instance toJungle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 1 (ego y?) self)
			)
			(1 (curRoom newRoom: 170))
		)
	)
)

(instance fromLostCity of Script
	(properties)
	
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (Btst 95)
					(Bclr 95)
					(Bset 88)
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
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (> Clock 2750) (Btst 81) (not local0))
			(= local0 1)
			(ego setMotion: 0)
			(= local4 1)
			(curRoom setScript: campOutManu 0 state)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 5))
			(1
				(ego
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: PolyPath 110 98 self
				)
			)
			(2 (messager say: 1 6 1 0 self))
			(3
				(ego setMotion: PolyPath 77 110 self)
			)
			(4 (messager say: 1 6 2 0 self))
			(5
				(ego setMotion: PolyPath 120 117 self)
			)
			(6 (messager say: 1 6 3 0 self))
			(7
				(ego setMotion: PolyPath 165 112 self)
			)
			(8
				(= local0 1)
				(messager say: 1 6 6 0 self)
			)
			(9 (curRoom newRoom: 740))
		)
	)
)

(instance afterWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset 88)
				(ego
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo (+ (ego x?) 7) (ego y?) self
				)
			)
			(1 (messager say: 1 6 7 0 self))
			(2
				(ego setMotion: MoveTo (+ (ego x?) 5) (ego y?) self)
			)
			(3
				(= monsterNum 590)
				(Bset 143)
				(Bset 96)
				(Bset 134)
				(curRoom newRoom: 700)
			)
		)
	)
)

(instance leadEgo of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (and (> Clock 2750) (Btst 81))
			(= local0 1)
			(ego setMotion: 0)
			(= local4 0)
			(curRoom setScript: campOutManu 0 state)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 88)
				(ego
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 193 113 self
				)
			)
			(1 (messager say: 1 6 4 0 self))
			(2
				(ego setMotion: MoveTo 232 108 self)
			)
			(3 (messager say: 1 6 5 0 self))
			(4
				(ego setMotion: MoveTo 260 112 self)
			)
			(5 (messager say: 1 6 8 0 self))
			(6
				(Bclr 88)
				(curRoom newRoom: 800)
			)
		)
	)
)

(instance campOutManu of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(messager say: 1 6 14 0 self)
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
				(messager say: 1 6 13 0 self)
			)
			(6
				(= local0 0)
				(if (> register 0)
					(= temp0 (- register 1))
				else
					(= temp0 0)
				)
				(if local4
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 2 setMotion: PolyPath 10 (ego y?) self)
			)
			(1
				(if (Btst 88)
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
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (== (PalVary pvGET_CURRENT_STEP) 20)
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
				(messager say: 1 6 10 0 self)
			)
			(2
				(ego setMotion: PolyPath 105 86 self)
			)
			(3
				(messager say: 1 6 11 0 self)
			)
			(4
				(ego setMotion: PolyPath 160 72 self)
			)
			(5
				(messager say: 1 6 12 0 self)
			)
			(6 (curRoom newRoom: 720))
		)
	)
)

(instance campOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 1 6 14 0 self)
			)
			(1
				(PalVary pvCHANGE_TICKS 2)
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
				(messager say: 1 6 13 0 self)
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
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(not
						(mod
							(GetDistance (ego x?) (ego y?) local2 local3)
							local7
						)
					)
					(not local0)
				)
				(= local0 1)
				(localproc_04a4)
			)
			((== (PalVary pvGET_CURRENT_STEP) 20) (ego setMotion: 0) (curRoom setScript: campOut 0 1))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 160 72 self)
			)
			(1
				(messager say: 1 6 12 0 self)
			)
			(2 (curRoom newRoom: 720))
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
		noun 4
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
		noun 5
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
		noun 7
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
		noun 6
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
		noun 8
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
		noun 9
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
		noun 10
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
		noun 11
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
		noun 12
		nsTop 36
		nsBottom 44
		nsRight 153
	)
)

(instance eastCut of Feature
	(properties
		x 218
		y 93
		noun 13
		nsTop 86
		nsLeft 189
		nsBottom 100
		nsRight 248
	)
)
