;;; Sierra Script 1.0 - (do not remove this comment)
(script# 310)
(include sci.sh)
(use Main)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Grooper)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm310 0
	meeting 1
)

(local
	local0
	local1
	local2
	local3 =  150
	local4 =  150
	local5
	[local6 3]
)
(instance rm310 of Rm
	(properties
		noun 21
		picture 310
		picAngle 290
		vanishingY 10
	)
	
	(method (init)
		(LoadMany 128 310 35 40)
		(chestLid init: setPri: 10 approachVerbs: 4 10 stopUpd:)
		(sky init:)
		(super init:)
		(if (!= prevRoomNum 300)
			(cSound number: 300 setLoop: -1 play: 64)
		else
			(cSound fade: 64 5 5 0)
		)
		(if (== prevRoomNum 360)
			(ego
				view: 35
				loop: 0
				cel: 8
				x: 56
				y: 124
				init:
				setScale: Scaler 122 95 160 130
			)
			(self setScript: dreaming)
		else
			(ego
				init:
				normalize:
				x: 3
				y: 160
				setScale: Scaler 122 95 160 130
				setMotion: PolyPath 20 160
			)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						0
						319
						0
						319
						189
						0
						189
						16
						186
						29
						187
						43
						186
						37
						179
						67
						171
						90
						174
						97
						185
						135
						186
						147
						175
						167
						173
						183
						178
						198
						186
						317
						186
						316
						169
						302
						157
						267
						143
						232
						152
						175
						133
						195
						129
						183
						124
						150
						130
						133
						126
						111
						129
						101
						130
						94
						132
						67
						110
						12
						115
						23
						138
						0
						141
					yourself:
				)
		)
		(if (and (<= 750 Clock) (<= Clock 1200))
			(sun
				init:
				x: (+ 256 (/ (- Clock 750) 6))
				y: (- 71 (/ (- Clock 750) 6))
				setLoop: 2
				setStep: 1 1
				moveSpeed: 1500
				setPri: 0
				setMotion: MoveTo 331 -4
			)
		)
		(if (Btst 130) (self setScript: meeting))
		(if (and (<= 3000 Clock) (<= Clock 3450))
			(moon
				init:
				x: (+ 256 (/ (- Clock 3000) 6))
				y: (- 71 (/ (- Clock 3000) 6))
				setLoop: 5
				setStep: 1 1
				moveSpeed: 1500
				setPri: 0
				setMotion: MoveTo 331 -4
			)
		)
		(if Night
			(flame init: cycleSpeed: 10 setCycle: Fwd setPri: 15)
		)
		(bed init: approachVerbs: 4)
		(chest init: approachVerbs: 4 10)
		(northCushion init: approachVerbs: 4)
		(eastCushion init: approachVerbs: 4)
		(table init:)
		(pot init:)
		(urn init:)
		(plant init:)
		(mountains init:)
		(outsideWindow init:)
		(pillar init:)
		(curtainright init:)
		(leftcurtain init:)
		(alcove init:)
		(rug init:)
		(walkHandler addToFront: curRoom)
		(if (and (!= prevRoomNum 360) (not (Btst 130)))
			(HandsOn)
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((<= (ego x?) 2) (self setScript: exit310))
			(
			(and (not local2) (< (ego y?) 131) (< (ego x?) 88)) (= local2 1) ((ScriptID 7 2) init:))
			(local2 (self setScript: getOffBed))
			((and (ego mover?) (== (ego view?) 40))
				(if (ego loop?)
					(self setScript: standEast)
				else
					(self setScript: standNorth)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(walkHandler delete: curRoom)
		(DisposeScript 29)
		(UnLoad 128 310)
		(UnLoad 128 35)
		(UnLoad 128 40)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(74
					(if (and (== (ego x?) 20) (== (ego y?) 140))
						(= local1 1)
					else
						(= local1 0)
					)
					(self setScript: goToBed)
				)
				(84
					(ego addHonor: -5)
					(if (ego castSpell: 28)
						((ScriptID 31 0) init: (ego x?) (+ (ego y?) 1) 80)
					)
				)
				(82
					(ego addHonor: -5)
					(if (ego castSpell: 26)
						(self setScript: (ScriptID 37 0))
						(return 1)
					)
				)
				(81
					(ego addHonor: -5)
					(if (ego castSpell: 25)
						(self setScript: (ScriptID 32 0) self 81)
					)
				)
				(83
					(ego addHonor: -5)
					(if (ego castSpell: 27)
						(self setScript: (ScriptID 32 0) self 83)
					)
				)
				(75
					(ego addHonor: -5)
					(if (ego castSpell: 19)
						(AutoTarget
							((User curEvent?) x?)
							((User curEvent?) y?)
						)
						(ego setScript: (ScriptID 13) 0 chest)
					)
				)
				(88
					(ego addHonor: -5)
					(if (ego castSpell: 32)
						(self setScript: (ScriptID 32 0) self 88)
					)
				)
				(80
					(ego addHonor: -5)
					(if (ego castSpell: 24)
						(ego setScript: (ScriptID 12 0) 0 80)
						(super doVerb: 80)
					)
				)
				(86
					(ego addHonor: -5)
					(if (ego castSpell: 30)
						(ego setScript: (ScriptID 62 0))
					)
				)
				(78
					(ego addHonor: -5)
					(if (ego castSpell: 22)
						(ego setScript: (ScriptID 12 0) 0 78)
					)
				)
				(76
					(ego addHonor: -5)
					(if (ego castSpell: 20)
						(ego setScript: (ScriptID 12 0) 0 76)
					)
				)
				(77
					(ego addHonor: -5)
					(if (ego castSpell: 21)
						(ego setScript: (ScriptID 12 0) 0 77)
					)
				)
				(85
					(ego addHonor: -5)
					(if (ego castSpell: 29)
						(sFx number: 943 play:)
						(self setScript: (ScriptID 12 0) 0 85)
					)
				)
				(87
					(ego addHonor: -5)
					(if (ego castSpell: 31)
						(ego setScript: (ScriptID 46 0))
					)
				)
				(-77
					(messager say: 0 0 2 1 0 12)
				)
				(-76
					(messager say: 0 0 1 1 0 12)
				)
				(-80
					(messager say: 0 0 4 1 0 12)
				)
				(3 (egoActions doVerb: 3))
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance meeting of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(self setScript: goToBed self)
			)
			(1
				(curRoom setScript: exit310)
				(self dispose:)
			)
		)
	)
)

(instance dreaming of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 5))
			(1
				(messager say: 19 6 2 0 self)
			)
			(2 (ego setCycle: Beg self))
			(3
				(ego
					normalize: 6
					cel: 6
					x: 31
					y: 123
					setMotion: PolyPath 28 135 self
				)
			)
			(4
				(if (Btst 13)
					(ego setMotion: PolyPath -10 140 self)
				else
					(HandsOn)
					(self dispose:)
				)
			)
			(5
				(messager say: 19 6 3 0 self)
			)
			(6 (curRoom newRoom: 340))
		)
	)
)

(instance exit310 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath -30 (ego y?) self)
			)
			(1
				(if (Btst 130)
					(messager say: 0 6 1 0 self)
				else
					(self cue:)
				)
			)
			(2
				(ego x: 302 y: 33)
				(if (Btst 130)
					(Bclr 130)
					(cSound fade:)
					(curRoom newRoom: 340)
				else
					(cSound fade: 127 5 5 0)
					(curRoom newRoom: 300)
				)
			)
		)
	)
)

(instance sitNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(CueObj client: 0)
				(northCushion approachVerbs:)
				(HandsOff)
				(ego setPri: 14 setMotion: MoveTo 66 179 self)
			)
			(1
				(ego
					view: 40
					setLoop: 0
					cel: 0
					x: 67
					y: 179
					setCycle: End self
				)
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance sitEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(CueObj client: 0)
				(eastCushion approachVerbs:)
				(HandsOff)
				(ego setPri: 14 setMotion: MoveTo 172 183 self)
			)
			(1
				(ego
					view: 40
					setLoop: 3
					cel: 0
					x: 170
					y: 183
					setCycle: End self
				)
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance standNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(if (!= (theIconBar curIcon?) (theIconBar at: 1))
					(if (CueObj client?)
						(cond 
							((== (CueObj client?) northCushion) (self dispose:))
							(((CueObj client?) approachX?) (= local5 1))
						)
					)
				else
					(= local5 0)
				)
				(self cue:)
			)
			(1
				(if (not client) (HandsOff))
				(northCushion approachVerbs: 4)
				(ego setMotion: 0 setCycle: Beg self)
			)
			(2
				(ego
					normalize: 5
					setPri: 14
					cel: 0
					x: 66
					y: 179
					setMotion: MoveTo 58 167 self
				)
			)
			(3
				(if local5
					(ego
						setPri: -1
						setMotion:
							PolyPath
							((CueObj client?) approachX?)
							((CueObj client?) approachY?)
							CueObj
					)
				else
					(ego setPri: -1 setMotion: PolyPath local3 local4 self)
				)
				(if (not client) (HandsOn))
				(self dispose:)
			)
		)
	)
)

(instance standEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(if (!= (theIconBar curIcon?) (theIconBar at: 1))
					(if (CueObj client?)
						(cond 
							((== (CueObj client?) eastCushion) (self dispose:))
							(((CueObj client?) approachX?) (= local5 1))
						)
					)
				else
					(= local5 0)
				)
				(self cue:)
			)
			(1
				(if (not client) (HandsOff))
				(eastCushion approachVerbs: 4)
				(ego setMotion: 0 setCycle: Beg self)
			)
			(2
				(ego
					normalize: 5
					setPri: 14
					cel: 0
					x: 180
					y: 183
					setMotion: MoveTo 191 176 self
				)
			)
			(3
				(if local5
					(ego
						setPri: -1
						setMotion:
							PolyPath
							((CueObj client?) approachX?)
							((CueObj client?) approachY?)
							CueObj
					)
				else
					(ego setPri: -1 setMotion: PolyPath local3 local4 self)
				)
				(if (not client) (HandsOn))
				(self dispose:)
			)
		)
	)
)

(instance useChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(sFx number: 311 play:)
				(chestLid setCycle: End self)
			)
			(1
				((ScriptID 29 0) init:)
				(= cycles 1)
			)
			(2
				(sFx number: 312 play:)
				(chestLid setCycle: Beg self)
			)
			(3
				(chestLid stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance fillChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(sFx number: 311 play:)
				(chestLid setCycle: End self)
			)
			(1
				((ScriptID 29 1) init: local0)
				(= cycles 1)
			)
			(2
				(sFx number: 312 play:)
				(chestLid setCycle: Beg self)
			)
			(3
				(chestLid stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance goToBed of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (not (Btst 130)) (HandsOff))
				(cond 
					(local1 (self cue:))
					((== (ego view?) 40)
						(= local3 28)
						(= local4 128)
						(CueObj client: 0)
						(if (ego loop?)
							(self setScript: standEast self)
						else
							(self setScript: standNorth self)
						)
					)
					(else (ego setMotion: PolyPath 28 128 self))
				)
			)
			(1
				(ego setMotion: PolyPath 31 123 self)
			)
			(2
				(ego
					view: 35
					loop: 0
					cel: 0
					x: 56
					y: 124
					setCycle: End self
				)
			)
			(3
				(if (= temp0 (PalVary pvGET_CURRENT_STEP))
					(if (and (< 0 temp0) (< temp0 64))
						(PalVary pvCHANGE_TICKS 3)
						(if (< temp0 30) (= seconds 6) else (= seconds 3))
					else
						(self cue:)
					)
				else
					(PalVary pvINIT 310 3)
					(= seconds 6)
				)
			)
			(4
				(if (or (< Clock 500) (> Clock 2199))
					(if (not (cast contains: moon))
						(moon
							init:
							x: 256
							y: 71
							setLoop: 5
							setStep: 1 1
							moveSpeed: 6
							setPri: 0
							setMotion: MoveTo 331 -4 self
						)
					else
						(moon moveSpeed: 6 setMotion: MoveTo 331 -4 self)
					)
				else
					(= seconds 2)
				)
			)
			(5
				(if (< (PalVary pvGET_CURRENT_STEP) 0)
					(PalVary pvCHANGE_TICKS 3)
				else
					(PalVary pvREVERSE 3)
				)
				(Bclr 81)
				(= seconds 3)
			)
			(6
				(sun
					init:
					x: 256
					y: 74
					setLoop: 2
					setStep: 1 1
					moveSpeed: 10
					setPri: 0
					setMotion: MoveTo 260 70 self
				)
			)
			(7
				(sun moveSpeed: 1500 setMotion: MoveTo 331 -4)
				(if (cast contains: moon) (moon dispose:))
				((ScriptID 7 7) init: 5 40)
				(ego setCycle: Beg self)
			)
			(8
				(ego
					normalize: 6
					cel: 6
					x: 31
					y: 123
					setMotion: PolyPath 28 135 self
				)
			)
			(9
				(if (not (Btst 130))
					(HandsOn)
				else
					(ego changeGait: 0)
				)
				(= local2 0)
				(self dispose:)
			)
		)
	)
)

(instance getOffBed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath (ego x?) (+ (ego y?) 15) self)
			)
			(1
				(HandsOn)
				(= local2 0)
				(self dispose:)
			)
		)
	)
)

(instance chestLid of Prop
	(properties
		x 265
		y 123
		noun 12
		sightAngle 40
		approachX 204
		approachY 143
		view 310
		signal $4000
	)
	
	(method (doVerb theVerb)
		(chest doVerb: theVerb &rest)
	)
)

(instance flame of Prop
	(properties
		x 101
		y 170
		noun 18
		view 310
		loop 1
		signal $4000
	)
)

(instance sun of Actor
	(properties
		noun 16
		yStep 1
		view 938
		loop 2
		signal $6000
		xStep 1
	)
)

(instance sky of View
	(properties
		x 296
		z -62
		noun 20
		view 310
		loop 2
		signal $6011
	)
	
	(method (doit &tmp temp0 temp1)
		(super doit:)
		(if (Btst 6)
			(if
				(!=
					(= temp1
						(cond 
							(
								(and
									(<= 0 (= temp0 (Abs (PalVary pvGET_CURRENT_STEP))))
									(<= temp0 8)
								)
								0
							)
							((and (<= 9 temp0) (<= temp0 16)) 1)
							((and (<= 17 temp0) (<= temp0 24)) 2)
							((and (<= 25 temp0) (<= temp0 32)) 3)
							((and (<= 33 temp0) (<= temp0 40)) 4)
							((and (<= 41 temp0) (<= temp0 48)) 5)
							((and (<= 49 temp0) (<= temp0 56)) 6)
							((and (<= 57 temp0) (<= temp0 64)) 7)
						)
					)
					cel
				)
				(self setCel: temp1 stopUpd:)
			)
		)
	)
)

(instance moon of Actor
	(properties
		noun 17
		yStep 1
		view 938
		loop 5
		signal $6000
		xStep 1
	)
)

(instance table of Feature
	(properties
		x 108
		y 184
		noun 1
		nsTop 179
		nsLeft 51
		nsBottom 189
		nsRight 166
		sightAngle 180
	)
)

(instance pot of Feature
	(properties
		x 240
		y 182
		noun 2
		nsTop 175
		nsLeft 219
		nsBottom 189
		nsRight 262
		sightAngle 180
	)
)

(instance urn of Feature
	(properties
		x 110
		y 109
		noun 3
		nsTop 95
		nsLeft 104
		nsBottom 124
		nsRight 116
		sightAngle 180
	)
)

(instance plant of Feature
	(properties
		x 232
		y 118
		z 50
		noun 4
		nsTop 59
		nsLeft 223
		nsBottom 77
		nsRight 241
		sightAngle 180
	)
)

(instance mountains of Feature
	(properties
		x 261
		y 91
		z 20
		noun 5
		nsTop 66
		nsLeft 241
		nsBottom 77
		nsRight 281
		sightAngle 180
	)
)

(instance outsideWindow of Feature
	(properties
		x 257
		y 72
		noun 6
		nsTop 42
		nsLeft 207
		nsBottom 102
		nsRight 307
		sightAngle 180
	)
)

(instance pillar of Feature
	(properties
		x 161
		y 73
		noun 7
		nsTop 32
		nsLeft 147
		nsBottom 114
		nsRight 175
		sightAngle 180
	)
)

(instance curtainright of Feature
	(properties
		x 93
		y 99
		noun 8
		nsTop 74
		nsLeft 83
		nsBottom 125
		nsRight 104
		sightAngle 180
	)
)

(instance leftcurtain of Feature
	(properties
		x 10
		y 104
		noun 9
		nsTop 72
		nsLeft 1
		nsBottom 136
		nsRight 20
		sightAngle 180
	)
)

(instance alcove of Feature
	(properties
		x 54
		y 89
		noun 10
		nsTop 73
		nsLeft 19
		nsBottom 106
		nsRight 89
		sightAngle 180
	)
)

(instance rug of Feature
	(properties
		x 69
		y 140
		noun 11
		nsTop 129
		nsLeft 26
		nsBottom 151
		nsRight 113
		sightAngle 180
	)
)

(instance chest of Feature
	(properties
		x 226
		y 129
		noun 12
		nsTop 114
		nsLeft 187
		nsBottom 144
		nsRight 265
		sightAngle 40
		approachX 204
		approachY 143
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 4) (chestLid setScript: (useChest new:)))
			((== theVerb -75) (chestLid setScript: (useChest new:)))
			((and (< 9 theVerb) (< theVerb 62))
				(if (>= theVerb 39)
					(= local0 (- theVerb 11))
				else
					(= local0 (- theVerb 10))
				)
				(chestLid setScript: (fillChest new:))
			)
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance bed of Feature
	(properties
		x 50
		y 98
		noun 13
		nsTop 73
		nsLeft 12
		nsBottom 124
		nsRight 88
		sightAngle 40
		approachX 28
		approachY 135
	)
	
	(method (doVerb theVerb)
		(if (or (== theVerb 3) (== theVerb 4))
			((ScriptID 7 2) init:)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance eastCushion of Feature
	(properties
		x 173
		y 182
		noun 14
		nsTop 175
		nsLeft 145
		nsBottom 190
		nsRight 201
		sightAngle 40
		approachX 188
		approachY 176
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(if (!= (ego view?) 40)
				(rm310 setScript: sitEast)
			else
				(super doVerb: &rest)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance northCushion of Feature
	(properties
		x 68
		y 177
		noun 15
		nsTop 171
		nsLeft 42
		nsBottom 180
		nsRight 95
		sightAngle 40
		approachX 58
		approachY 170
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(if (!= (ego view?) 40)
				(rm310 setScript: sitNorth)
			else
				(super doVerb: &rest)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance egoActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(3
					(cond 
						((curRoom script?) 0)
						((and (== (ego x?) 67) (== (ego y?) 179))
							(= local3 ((User curEvent?) x?))
							(= local4 ((User curEvent?) y?))
							(curRoom setScript: standNorth)
							(return 1)
						)
						((and (== (ego x?) 170) (== (ego y?) 183))
							(= local3 ((User curEvent?) x?))
							(= local4 ((User curEvent?) y?))
							(curRoom setScript: standEast)
							(return 1)
						)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance stopGroop of Grooper
	(properties)
	
	(method (doit)
		(if
			(and
				(IsObject (ego cycler?))
				((ego cycler?) isKindOf: StopWalk)
			)
			(ego view: ((ego cycler?) vWalking?))
		)
		(super doit: &rest)
	)
)

(instance sFx of Sound
	(properties)
)
