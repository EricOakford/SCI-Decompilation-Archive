;;; Sierra Script 1.0 - (do not remove this comment)
(script# 780)
(include sci.sh)
(use Main)
(use GloryRm)
(use TargFeature)
(use TellerIcon)
(use EgoDead)
(use Array)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use DPath)
(use Motion)
(use Actor)
(use System)

(public
	rm780 0
)

(local
	gTheObj_2CycleSpeed
	local1
	local2
	local3
	local4
	gTheObj_2XStep
	theGGGTheObj_2CycleSpeed
)
(instance rm780 of GloryRm
	(properties
		picture 780
	)
	
	(method (init)
		(ego init: setScaler: Scaler 132 85 186 54)
		(RemapColors 2 253 50)
		(= local1 (IntArray with: 5 1 7 3 6 0 4 2))
		(if (Btst 316)
			(Palette palSET_FLAG 0 65 0)
			(Palette palSET_FLAG 86 255 0)
			(Palette palSET_FLAG 66 85 0)
			(ego view: 6 loop: 3 cel: 0 posn: 235 109)
		else
			(Palette palSET_FLAG 66 85 75)
			(ego posn: 319 54 normalize:)
		)
		(theMusic number: 780 setLoop: -1 play:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						52
						149
						52
						163
						75
						186
						132
						186
						132
						176
						194
						141
						264
						141
						264
						123
						278
						123
						270
						115
						264
						115
						264
						107
						227
						107
						197
						117
						126
						99
						24
						123
					yourself:
				)
		)
		(desk init: approachVerbs: 4 80 42)
		(rollTop init: setPri: 185 approachVerbs: 4 80 42)
		(if (and (not (Btst 315)) (> [egoStats 9] 0))
			(statue init: approachVerbs: 4 13)
		)
		(drip init: setCycle: Fwd)
		(genie init: approachVerbs: 4)
		(torch1 init: setPri: 75 setCycle: Fwd)
		(torch2 init: setPri: 75 setCycle: Fwd)
		(book init: approachVerbs: 4 39 76)
		(bottle init: approachVerbs: 4)
		(barrel init: approachVerbs: 4)
		(gargoyle1 init: approachVerbs: 4)
		(gargoyle2 init: approachVerbs: 4)
		(cask init: approachVerbs: 4)
		(stairs init: approachVerbs: 4)
		(alcove init: approachVerbs: 4)
		(sconce1 init: approachVerbs: 4)
		(sconce2 init: approachVerbs: 4)
		(genie1 init: approachVerbs: 4)
		(genie2 init: approachVerbs: 4)
		(pillar init: approachVerbs: 4)
		(avoozl init: approachVerbs: 4)
		(barrels init: approachVerbs: 4)
		(serpentCrack init: approachVerbs: 4)
		(super init: &rest)
		(if (Btst 316)
			(self setScript: sWakeUp)
		else
			(self setScript: sDownStairs)
		)
		(theGame save: 1)
	)
	
	(method (dispose)
		(local1 dispose:)
		(super dispose:)
	)
	
	(method (newRoom n)
		(theMusic fade: 0)
		(super newRoom: n &rest)
	)
)

(instance sUpStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: DPath 309 95 294 72 319 54 self)
			)
			(1
				(theGame handsOn:)
				(curRoom newRoom: 360)
			)
		)
	)
)

(instance sDownStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: DPath 294 72 309 95 271 124 250 124 self)
			)
			(1
				(if (not (Btst 319))
					(Bset 319)
					(messager say: 17 6 2 0 self)
				else
					(= ticks 2)
				)
			)
			(2
				(if (== heroType 3)
					(messager say: 17 6 3 0 self)
				else
					(= ticks 2)
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetDrunk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 243 99 self)
			)
			(1 (ego setHeading: 90 self))
			(2
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 4
					loop: 0
					cel: 0
					x: 237
					y: 99
					cycleSpeed: 8
					setCycle: CT 2 1 self
				)
			)
			(3 (messager say: 6 4 0 0 self))
			(4 (ego setCycle: Beg self))
			(5
				(ego
					normalize: 0
					cycleSpeed: gTheObj_2CycleSpeed
					setMotion: MoveTo 245 115 self
				)
			)
			(6
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					setCycle: 0
					view: 5
					cel: 0
					loop: 5
					cycleSpeed: 5
					setCycle: (spin new:) 7 1 self
				)
			)
			(7
				(ego setCycle: (spin new:) 7 1 self)
			)
			(8
				(ego setCycle: (spin new:) 7 1 self)
			)
			(9
				(ego
					view: 43
					setLoop: 1
					cel: 0
					posn: 222 120
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(10
				(Bset 50)
				(Bclr 6)
				(= local4 100)
				(while (>= local4 0)
					(= gameTime (+ tickOffset (GetTime)))
					(sounds eachElementDo: #check)
					(timers eachElementDo: #doit)
					(cast doit:)
					(Palette palSET_FLAG 0 65 local4)
					(Palette palSET_FLAG 86 255 local4)
					(if (< local4 75) (Palette palSET_FLAG 66 85 local4))
					(FrameOut)
					(= local4 (- local4 10))
				)
				(= cycles 1)
			)
			(11
				(Bset 316)
				(ego cycleSpeed: gTheObj_2CycleSpeed)
				(theGame handsOn:)
				(curRoom newRoom: 180)
			)
		)
	)
)

(instance sWakeUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bclr 316)
				(= seconds 2)
			)
			(1
				(= local4 0)
				(while (< local4 100)
					(theGame doit:)
					(Palette palSET_FLAG 0 65 local4)
					(Palette palSET_FLAG 86 255 local4)
					(if (not (> local4 75))
						(Palette palSET_FLAG 66 85 local4)
					)
					(FrameOut)
					(= local4 (+ local4 10))
				)
				(= cycles 1)
			)
			(2
				(Bset 6)
				(Bclr 50)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego cycleSpeed: 10 setCycle: End self)
			)
			(3
				(ego
					posn: 214 114
					cycleSpeed: gTheObj_2CycleSpeed
					normalize: 5
				)
				(messager say: 17 6 1 0 self)
			)
			(4
				(drip loop: 3 setCycle: End self)
			)
			(5 (= seconds 2))
			(6
				(messager say: 17 6 14 0 self)
			)
			(7
				(ego setMotion: MoveTo 235 105 self)
			)
			(8
				(ego view: 4 loop: 0 cel: 0 setCycle: End self)
			)
			(9
				(drip hide:)
				(ego get: 53 setCycle: Beg self)
			)
			(10
				(ego normalize: 0)
				(= seconds 2)
			)
			(11
				(messager say: 17 6 15 0 self)
			)
			(12
				(ego setMotion: MoveTo 235 114 self)
			)
			(13
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenDesk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(switch register
					(1
						(messager say: 19 166 12 0 self)
					)
					(2
						(messager say: 19 166 11 0 self)
					)
					(3 (= cycles 1))
					(else 
						(theGame handsOn:)
						(if ((ScriptID 648 0) init: show: dispose:)
							(DisposeScript 648)
							(= cycles 1)
						else
							(DisposeScript 648)
							(if (Btst 182)
								(Bclr 182)
								(= local3 1)
								(= cycles 1)
							else
								(self changeState: 5)
							)
						)
					)
				)
			)
			(1
				1
				(theGame handsOff:)
				(Bset 317)
				(rollTop setCycle: Beg self)
			)
			(2
				2
				(Bset 318)
				(if (or (OneOf register 2 1 3) (== local3 1))
					(gasCloud init: setPri: 196 setCycle: End self)
				else
					(self changeState: 5)
				)
			)
			(3
				3
				(gasCloud cel: 0)
				(if (== register 3)
					(= cycles 1)
				else
					(messager say: 17 6 8 0 self)
				)
			)
			(4
				4
				(cond 
					((== register 3) (messager say: 2 80 0 0 self))
					((ego takeDamage: 25)
						(Bset 14)
						(= poisonLevel (+ poisonLevel 30))
						(self changeState: 5)
					)
					(else (EgoDead 8 0 977 1 912))
				)
			)
			(5
				5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDoDesk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(Bset 317)
					(rollTop setCycle: Beg self)
				else
					(Bclr 317)
					(rollTop setCycle: End self)
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sSetFire of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 41)
				(ego addHonor: 500)
				(= cycles 1)
			)
			(1
				(theGame handsOff:)
				(= seconds 2)
			)
			(2
				(messager say: 12 76 6 0 self)
			)
			(3
				(book
					view: 783
					loop: 0
					cel: 0
					posn: 73 81
					setCycle: End self
				)
			)
			(4
				(= theGGGTheObj_2CycleSpeed egoGait)
				(book loop: 1 cel: 0 setCycle: Fwd)
				(ego
					normalize:
					changeGait: 1
					setMotion: PolyPath 230 120 self
				)
			)
			(5
				(fire1 init: setPri: 196 setCycle: Fwd)
				(fire2 init: setPri: 196 setCycle: Fwd)
				(ego setMotion: PolyPath 270 120 self)
			)
			(6
				(= egoGait theGGGTheObj_2CycleSpeed)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sBookDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 12 4 0 0 self)
			)
			(1
				(book hide:)
				(ego
					setScale: 0
					view: 781
					loop: 0
					cel: 0
					x: 82
					y: 118
					cycleSpeed: 10
					setCycle: End self
				)
			)
			(2
				(book show:)
				(ego loop: 1 setCycle: End self)
			)
			(3 (= seconds 2))
			(4 (EgoDead 4 0 0 0 912))
		)
	)
)

(instance sSlugDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(genie
					view: 782
					loop: 1
					cel: 0
					posn: 148 99
					setCycle: Fwd
				)
				(= seconds 5)
			)
			(1
				(messager say: 16 4 0 0 self)
			)
			(2 (= seconds 2))
			(3
				(= gTheObj_2XStep (ego xStep?))
				(ego
					setScale: 0
					setCycle: 0
					view: 5
					xStep: 1
					setLoop: 6
					setMotion: MoveTo 124 113 self
				)
			)
			(4
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(genie setCycle: 0 view: 782 loop: 0 cel: 0 posn: 146 99)
				(ego
					view: 782
					loop: 2
					cel: 0
					posn: 121 116
					cycleSpeed: 10
					setCycle: End self
				)
			)
			(5 (= seconds 2))
			(6 (EgoDead 7 0 975 1 912))
		)
	)
)

(instance sBagStatue of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 31 loop: 0 cel: 0 setCycle: CT 1 1 self)
			)
			(1
				(statue hide:)
				(ego get: 43)
				(Bset 315)
				(= cycles 2)
			)
			(2
				(messager say: 16 13 0 0 self)
			)
			(3 (ego setCycle: Beg self))
			(4
				(ego normalize: 6)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance deskTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(cond 
						(
						(and (== (CueObj client?) rollTop) (Btst 318))
							(if (Btst 317)
								(curRoom setScript: sDoDesk 0 0)
							else
								(curRoom setScript: sDoDesk 0 1)
							)
						)
						((Btst 318)
							(if (Btst 317)
								(if (not (Btst 233)) (Bset 233))
								(messager say: 2 4 9)
							else
								(messager say: 2 4 13)
							)
						)
						(else (super doVerb: theVerb &rest))
					)
					(return 1)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
	
	(method (sayMessage)
		(switch iconValue
			(12
				(self clean:)
				(curRoom setScript: sOpenDesk 0 1)
			)
			(11
				(self clean:)
				(curRoom setScript: sOpenDesk 0 2)
			)
			(10
				(self clean:)
				(curRoom setScript: sOpenDesk 0 0)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				10
				(if (ego has: 24) (Btst 242) else 0)
				12
				(>= [egoStats 0] 300)
				11
				(ego has: 24)
		)
	)
)

(instance rollTop of Prop
	(properties
		noun 2
		sightAngle 180
		x 162
		y 162
		priority 186
		fixPriority 1
		view 780
		signal $5001
	)
	
	(method (init)
		(super init: &rest)
		(if (not (Btst 317))
			(self setCel: (rollTop lastCel:))
		)
		(= actions deskTeller)
	)
	
	(method (handleEvent event)
		(if (== (event message?) KEY_P)
			(= approachX 73)
			(= approachY 156)
		else
			(= approachX 123)
			(= approachY 186)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(desk doVerb: theVerb &rest)
	)
)

(instance book of TargProp
	(properties
		noun 12
		sightAngle 180
		approachX 87
		approachY 108
		x 49
		y 69
		view 781
		loop 2
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sBookDeath)
			)
			(39
				(if
					(and
						(Btst 138)
						(Btst 233)
						(or (ego has: 53) ((inventory at: 53) chestAmout?))
					)
					(self setScript: sSetFire)
				else
					(messager say: 12 76 5)
				)
			)
			(76
				(if
					(and
						(Btst 138)
						(Btst 233)
						(or (ego has: 53) ((inventory at: 53) chestAmout?))
					)
					(self setScript: sSetFire)
				else
					(messager say: 12 76 5)
				)
			)
			(86
				(if
					(and
						(Btst 138)
						(Btst 233)
						(or (ego has: 53) ((inventory at: 53) chestAmout?))
					)
					(= projX mouseX)
					(= projY mouseY)
					(curRoom setScript: sSetFire 0 1)
				else
					(messager say: 12 76 5)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (getHurt param1)
		(if (== param1 86) (self doVerb: param1))
	)
)

(instance genie of Prop
	(properties
		noun 10
		sightAngle 180
		approachX 176
		approachY 119
		x 146
		y 99
		priority 60
		fixPriority 1
		view 782
		signal $4001
	)
)

(instance gasCloud of Prop
	(properties
		x 162
		y 142
		view 780
		loop 1
		signal $4001
	)
)

(instance drip of Prop
	(properties
		noun 6
		sightAngle 180
		approachX 237
		approachY 107
		x 254
		y 81
		view 781
		loop 4
		signal $4001
		cycleSpeed 10
	)
	
	(method (doVerb theVerb)
		(cask doVerb: theVerb &rest)
	)
)

(instance fire1 of Prop
	(properties
		x 53
		y 138
		view 783
		loop 2
		signal $4001
	)
)

(instance fire2 of Prop
	(properties
		x 27
		y 162
		view 783
		loop 5
		signal $4001
	)
)

(instance torch1 of Prop
	(properties
		x 101
		y 17
		view 780
		loop 3
		signal $4001
		detailLevel 3
	)
)

(instance torch2 of Prop
	(properties
		x 200
		y 16
		view 780
		loop 2
		signal $4001
		detailLevel 3
	)
)

(instance statue of View
	(properties
		noun 16
		sightAngle 90
		approachX 168
		approachY 124
		x 183
		y 87
		view 781
		loop 5
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sSlugDeath)
			)
			(13
				(curRoom setScript: sBagStatue)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance desk of Feature
	(properties
		noun 2
		nsLeft 140
		nsTop 127
		nsRight 226
		nsBottom 189
		sightAngle 180
		x 183
		y 158
	)
	
	(method (init)
		(super init: &rest)
		(deskTeller init: self 780 19 166)
	)
	
	(method (handleEvent event)
		(if (== (event message?) KEY_P)
			(= approachX 73)
			(= approachY 156)
		else
			(= approachX 123)
			(= approachY 186)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 317)
					(if (not (Btst 233)) (Bset 233))
					(messager say: 2 1 9)
				else
					(messager say: 2 1 13)
				)
			)
			(42
				(if (Btst 242)
					(curRoom setScript: sOpenDesk 0 0)
				else
					(curRoom setScript: sOpenDesk 0 2)
				)
			)
			(80
				(if (ego castSpell: 20)
					(= projX 168)
					(= projY 159)
					(curRoom setScript: (ScriptID 13 0) 0 self)
				)
			)
			(-80
				(curRoom setScript: sOpenDesk 0 3)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bottle of Feature
	(properties
		noun 3
		nsLeft 233
		nsTop 129
		nsRight 240
		nsBottom 143
		sightAngle 180
		x 236
		y 136
	)
)

(instance barrel of Feature
	(properties
		noun 4
		nsLeft 241
		nsTop 121
		nsRight 267
		nsBottom 150
		sightAngle 180
		x 254
		y 135
	)
)

(instance gargoyle1 of Feature
	(properties
		noun 5
		nsTop 76
		nsRight 27
		nsBottom 127
		sightAngle 180
		x 13
		y 101
	)
)

(instance gargoyle2 of Feature
	(properties
		noun 5
		nsLeft 205
		nsTop 52
		nsRight 225
		nsBottom 70
		sightAngle 180
		x 215
		y 61
	)
)

(instance cask of Feature
	(properties
		noun 6
		nsLeft 222
		nsTop 18
		nsRight 269
		nsBottom 91
		sightAngle 180
		approachX 237
		approachY 107
		x 245
		y 54
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if
				(or ((inventory at: 53) chestAmout?) (ego has: 53))
					(messager say: 6 4 18)
				else
					(curRoom setScript: sGetDrunk)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance stairs of Feature
	(properties
		noun 7
		nsLeft 270
		nsTop 52
		nsRight 313
		nsBottom 126
		sightAngle 180
		x 291
		y 89
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 281 112 281 127 266 127 266 112
						yourself:
					)
					6
					0
					4
					sUpStairs
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
)

(instance alcove of Feature
	(properties
		noun 8
		nsLeft 177
		nsTop 69
		nsRight 195
		nsBottom 94
		sightAngle 180
		x 186
		y 81
	)
)

(instance sconce1 of Feature
	(properties
		noun 9
		nsLeft 93
		nsTop 2
		nsRight 108
		nsBottom 46
		sightAngle 180
		x 100
		y 24
	)
)

(instance sconce2 of Feature
	(properties
		noun 9
		nsLeft 193
		nsTop 1
		nsRight 208
		nsBottom 48
		sightAngle 180
		x 200
		y 24
	)
)

(instance genie1 of Feature
	(properties
		noun 10
		nsLeft 136
		nsTop 32
		nsRight 160
		nsBottom 66
		sightAngle 180
		x 148
		y 49
	)
)

(instance genie2 of Feature
	(properties
		noun 10
		nsLeft 128
		nsTop 66
		nsRight 167
		nsBottom 85
		sightAngle 180
		x 147
		y 75
	)
)

(instance pillar of Feature
	(properties
		noun 11
		nsLeft 137
		nsTop 82
		nsRight 163
		nsBottom 102
		sightAngle 180
		x 150
		y 92
	)
)

(instance avoozl of Feature
	(properties
		noun 13
		nsLeft 30
		nsTop 20
		nsRight 88
		nsBottom 87
		sightAngle 180
		approachX 87
		approachY 108
		x 59
		y 53
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sBookDeath)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance barrels of Feature
	(properties
		noun 14
		nsTop 127
		nsRight 56
		nsBottom 189
		sightAngle 180
		x 28
		y 158
	)
)

(instance serpentCrack of Feature
	(properties
		noun 15
		nsLeft 264
		nsTop 159
		nsRight 280
		nsBottom 179
		sightAngle 180
		x 272
		y 169
	)
)

(instance spin of CT
	(properties)
	
	(method (init param1 param2 param3 param4)
		(super init: param1 param2 param3 param4)
		(if (== param3 1)
			(= local2 0)
			(= endCel 7)
		else
			(= local2 7)
			(= endCel 0)
		)
	)
	
	(method (doit &tmp clientLoop temp1)
		(if
		(< (Abs (- gameTime cycleCnt)) (client cycleSpeed?))
			(= clientLoop (client loop?))
		else
			(= cycleCnt gameTime)
			(= clientLoop (local1 at: local2))
			(if (and (>= local2 0) (<= local2 endCel))
				(= local2 (+ local2 cycleDir))
			)
		)
		(client loop: clientLoop)
		(if (== (- local2 cycleDir) endCel)
			(FrameOut)
			(= local2 0)
			(self cycleDone:)
		)
	)
)
