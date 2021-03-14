;;; Sierra Script 1.0 - (do not remove this comment)
(script# 310)
(include game.sh) (include "310.shm")
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
	nearBed
	sleeping
	toX =  150
	toY =  150
	local5
	[local6 3]
)
(instance rm310 of Room
	(properties
		noun N_ROOM
		picture 310
		picAngle 290
		vanishingY 10
	)
	
	(method (init)
		(LoadMany RES_VIEW 310 35 40)
		(chestLid init: setPri: 10 approachVerbs: V_DO V_ROYALS stopUpd:)
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
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						0 189
						16 186
						29 187
						43 186
						37 179
						67 171
						90 174
						97 185
						135 186
						147 175
						167 173
						183 178
						198 186
						317 186
						316 169
						302 157
						267 143
						232 152
						175 133
						195 129
						183 124
						150 130
						133 126
						111 129
						101 130
						94 132
						67 110
						12 115
						23 138
						0 141
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
		(if (Btst fWillHaveMeeting)
			(self setScript: meeting)
		)
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
			(flame init: cycleSpeed: 10 setCycle: Forward setPri: 15)
		)
		(bed init: approachVerbs: V_DO)
		(chest init: approachVerbs: V_DO V_ROYALS)
		(northCushion init: approachVerbs: V_DO)
		(eastCushion init: approachVerbs: V_DO)
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
		(if (and (!= prevRoomNum 360) (not (Btst fWillHaveMeeting)))
			(HandsOn)
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((<= (ego x?) 2)
				(self setScript: exit310)
			)
			((and (not sleeping) (< (ego y?) 131) (< (ego x?) 88))
				(= sleeping TRUE)
				((ScriptID TIME 2) init:)
			)
			(sleeping
				(self setScript: getOffBed)
			)
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
		(DisposeScript CHEST)
		(UnLoad RES_VIEW 310)
		(UnLoad RES_VIEW 35)
		(UnLoad RES_VIEW 40)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_SLEEP
					(if (and (== (ego x?) 20) (== (ego y?) 140))
						(= nearBed TRUE)
					else
						(= nearBed FALSE)
					)
					(self setScript: goToBed)
				)
				(V_LEVITATE
					(ego addHonor: -5)
					(if (ego castSpell: LEVITATE)
						((ScriptID LEVITATION 0) init: (ego x?) (+ (ego y?) 1) 80)
					)
				)
				(V_FETCH
					(ego addHonor: -5)
					(if (ego castSpell: FETCH)
						(self setScript: (ScriptID CASTFETCH 0))
						(return TRUE)
					)
				)
				(V_FLAME
					(ego addHonor: -5)
					(if (ego castSpell: FLAMEDART)
						(self setScript: (ScriptID PROJECTILE 0) self V_FLAME)
					)
				)
				(V_FORCEBOLT
					(ego addHonor: -5)
					(if (ego castSpell: FORCEBOLT)
						(self setScript: (ScriptID PROJECTILE 0) self V_FORCEBOLT)
					)
				)
				(V_OPEN
					(ego addHonor: -5)
					(if (ego castSpell: OPEN)
						(AutoTarget
							((User curEvent?) x?)
							((User curEvent?) y?)
						)
						(ego setScript: (ScriptID CASTOPEN) 0 chest)
					)
				)
				(V_LIGHTNING
					(ego addHonor: -5)
					(if (ego castSpell: LIGHTNING)
						(self setScript: (ScriptID PROJECTILE 0) self V_LIGHTNING)
					)
				)
				(V_CALM
					(ego addHonor: -5)
					(if (ego castSpell: CALM)
						(ego setScript: (ScriptID CASTAREA 0) 0 V_CALM)
						(super doVerb: V_CALM)
					)
				)
				(V_JUGGLE
					(ego addHonor: -5)
					(if (ego castSpell: JUGGLE)
						(ego setScript: (ScriptID CASTJUGGLE 0))
					)
				)
				(V_DAZZLE
					(ego addHonor: -5)
					(if (ego castSpell: DAZZLE)
						(ego setScript: (ScriptID CASTAREA 0) 0 V_DAZZLE)
					)
				)
				(V_DETECT
					(ego addHonor: -5)
					(if (ego castSpell: DETMAGIC)
						(ego setScript: (ScriptID CASTAREA 0) 0 V_DETECT)
					)
				)
				(V_TRIGGER
					(ego addHonor: -5)
					(if (ego castSpell: TRIGGER)
						(ego setScript: (ScriptID CASTAREA 0) 0 V_TRIGGER)
					)
				)
				(V_REVERSAL
					(ego addHonor: -5)
					(if (ego castSpell: REVERSAL)
						(sFx number: 943 play:)
						(self setScript: (ScriptID CASTAREA 0) 0 V_REVERSAL)
					)
				)
				(V_STAFF
					(ego addHonor: -5)
					(if (ego castSpell: STAFF)
						(ego setScript: (ScriptID STAFF_SCRIPT 0))
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
				(V_WALK
					(egoActions doVerb: V_WALK)
				)
				(else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance meeting of Script

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
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 5)
			)
			(1
				(messager say: N_AWAKEN V_DOIT C_DREAMING 0 self)
			)
			(2
				(ego setCycle: BegLoop self)
			)
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
				(if (Btst fGaveDrum)
					(ego setMotion: PolyPath -10 140 self)
				else
					(HandsOn)
					(self dispose:)
				)
			)
			(5
				(messager say: N_AWAKEN V_DOIT C_PEACE 0 self)
			)
			(6
				(curRoom newRoom: 340)
			)
		)
	)
)

(instance exit310 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath -30 (ego y?) self)
			)
			(1
				(if (Btst fWillHaveMeeting)
					(messager say: NULL V_DOIT C_MEETING 0 self)
				else
					(self cue:)
				)
			)
			(2
				(ego x: 302 y: 33)
				(if (Btst fWillHaveMeeting)
					(Bclr fWillHaveMeeting)
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
					setCycle: EndLoop self
				)
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sitEast of Script
	
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
					setCycle: EndLoop self
				)
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance standNorth of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(if (!= (theIconBar curIcon?) (theIconBar at: ICON_WALK))
					(if (CueObj client?)
						(cond 
							((== (CueObj client?) northCushion)
								(self dispose:)
							)
							(((CueObj client?) approachX?)
								(= local5 1)
							)
						)
					)
				else
					(= local5 0)
				)
				(self cue:)
			)
			(1
				(if (not client)
					(HandsOff)
				)
				(northCushion approachVerbs: V_DO)
				(ego setMotion: 0 setCycle: BegLoop self)
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
					(ego setPri: -1 setMotion: PolyPath toX toY self)
				)
				(if (not client)
					(HandsOn)
				)
				(self dispose:)
			)
		)
	)
)

(instance standEast of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(if (!= (theIconBar curIcon?) (theIconBar at: ICON_WALK))
					(if (CueObj client?)
						(cond 
							((== (CueObj client?) eastCushion)
								(self dispose:)
							)
							(((CueObj client?) approachX?)
								(= local5 1)
							)
						)
					)
				else
					(= local5 0)
				)
				(self cue:)
			)
			(1
				(if (not client)
					(HandsOff)
				)
				(eastCushion approachVerbs: V_DO)
				(ego setMotion: 0 setCycle: BegLoop self)
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
					(ego setPri: -1 setMotion: PolyPath toX toY self)
				)
				(if (not client)
					(HandsOn)
				)
				(self dispose:)
			)
		)
	)
)

(instance useChest of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(sFx number: 311 play:)
				(chestLid setCycle: EndLoop self)
			)
			(1
				((ScriptID CHEST 0) init:)
				(= cycles 1)
			)
			(2
				(sFx number: 312 play:)
				(chestLid setCycle: BegLoop self)
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

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(sFx number: 311 play:)
				(chestLid setCycle: EndLoop self)
			)
			(1
				((ScriptID CHEST 1) init: local0)
				(= cycles 1)
			)
			(2
				(sFx number: 312 play:)
				(chestLid setCycle: BegLoop self)
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
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (not (Btst fWillHaveMeeting))
					(HandsOff)
				)
				(cond 
					(nearBed (self cue:))
					((== (ego view?) 40)
						(= toX 28)
						(= toY 128)
						(CueObj client: 0)
						(if (ego loop?)
							(self setScript: standEast self)
						else
							(self setScript: standNorth self)
						)
					)
					(else
						(ego setMotion: PolyPath 28 128 self)
					)
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
					setCycle: EndLoop self
				)
			)
			(3
				(if (= temp0 (PalVary PALVARYINFO))
					(if (and (< 0 temp0) (< temp0 64))
						(PalVary PALVARYNEWTIME 3)
						(if (< temp0 30)
							(= seconds 6)
						else
							(= seconds 3)
						)
					else
						(self cue:)
					)
				else
					(PalVary PALVARYSTART 310 3)
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
				(if (< (PalVary PALVARYINFO) 0)
					(PalVary PALVARYNEWTIME 3)
				else
					(PalVary PALVARYREVERSE 3)
				)
				(Bclr fEgoIsAsleep)
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
				(if (cast contains: moon)
					(moon dispose:)
				)
				((ScriptID TIME 7) init: 5 40)
				(ego setCycle: BegLoop self)
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
				(if (not (Btst fEgoIsAsleep))
					(HandsOn)
				else
					(ego changeGait: MOVE_WALK)
				)
				(= sleeping FALSE)
				(self dispose:)
			)
		)
	)
)

(instance getOffBed of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath (ego x?) (+ (ego y?) 15) self)
			)
			(1
				(HandsOn)
				(= sleeping FALSE)
				(self dispose:)
			)
		)
	)
)

(instance chestLid of Prop
	(properties
		x 265
		y 123
		noun N_CHEST
		sightAngle 40
		approachX 204
		approachY 143
		view 310
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(chest doVerb: theVerb &rest)
	)
)

(instance flame of Prop
	(properties
		x 101
		y 170
		noun N_FLAME
		view 310
		loop 1
		signal ignrAct
	)
)

(instance sun of Actor
	(properties
		noun N_SUN
		yStep 1
		view 938
		loop 2
		signal (| ignrAct ignrHrz)
		xStep 1
	)
)

(instance sky of View
	(properties
		x 296
		z -62
		noun N_SKY
		view 310
		loop 2
		signal (| ignrAct ignrHrz fixPriOn stopUpdOn)
	)
	
	(method (doit &tmp temp0 temp1)
		(super doit:)
		(if (Btst fInMainGame)
			(if
				(!=
					(= temp1
						(cond 
							(
								(and
									(<= 0 (= temp0 (Abs (PalVary PALVARYINFO))))
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
		noun N_MOON
		yStep 1
		view 938
		loop 5
		signal (| ignrAct ignrHrz)
		xStep 1
	)
)

(instance table of Feature
	(properties
		x 108
		y 184
		noun N_TABLE
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
		noun N_POT
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
		noun N_URN
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
		noun N_PLANT
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
		noun N_MOUNTAINS
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
		noun N_WINDOW
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
		noun N_PILLAR
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
		noun N_CURTAINRIGHT
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
		noun N_CURTAINLEFT
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
		noun N_ALCOVE
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
		noun N_RUG
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
		noun N_CHEST
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
			((== theVerb V_DO)
				(chestLid setScript: (useChest new:))
			)
			((== theVerb -75)
				(chestLid setScript: (useChest new:))
			)
			((and (< V_HELP theVerb) (< theVerb 62))
				(if (>= theVerb V_PIN)
					(= local0 (- theVerb V_SWORD))
				else
					(= local0 (- theVerb V_ROYALS))
				)
				(chestLid setScript: (fillChest new:))
			)
			(else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bed of Feature
	(properties
		x 50
		y 98
		noun N_BED
		nsTop 73
		nsLeft 12
		nsBottom 124
		nsRight 88
		sightAngle 40
		approachX 28
		approachY 135
	)
	
	(method (doVerb theVerb)
		(if (or (== theVerb V_WALK) (== theVerb V_DO))
			((ScriptID TIME 2) init:)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance eastCushion of Feature
	(properties
		x 173
		y 182
		noun N_EAST_CUSHION
		nsTop 175
		nsLeft 145
		nsBottom 190
		nsRight 201
		sightAngle 40
		approachX 188
		approachY 176
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
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
		noun N_NORTH_CUSHION
		nsTop 171
		nsLeft 42
		nsBottom 180
		nsRight 95
		sightAngle 40
		approachX 58
		approachY 170
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
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
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_WALK
					(cond 
						((curRoom script?) 0)
						((and (== (ego x?) 67) (== (ego y?) 179))
							(= toX ((User curEvent?) x?))
							(= toY ((User curEvent?) y?))
							(curRoom setScript: standNorth)
							(return TRUE)
						)
						((and (== (ego x?) 170) (== (ego y?) 183))
							(= toX ((User curEvent?) x?))
							(= toY ((User curEvent?) y?))
							(curRoom setScript: standEast)
							(return TRUE)
						)
					)
				)
				(else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance stopGroop of GradualLooper
	
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

(instance sFx of Sound)
