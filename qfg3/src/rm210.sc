;;; Sierra Script 1.0 - (do not remove this comment)
(script# 210)
(include game.sh) (include "210.shm")
(use Main)
(use TellerIcon)
(use GloryTalker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm210 0
	guard1Talker 1
	guard2Talker 2
)

(local
	local0 = [0 35 36 33 34 999]
	local6 = [0 -45 42 -40 43 999]
	local12 = [0 -41 999]
	local15 = [0 44 999]
	local18 = [0 -46 37 999]
	local22 = [0 -39 999]
	local25 = [0 38 999]
	local28 = [0 -45 42 -40 43 999]
	local34 = [0 -37 41 -44 999]
	local39 = [0 49 999]
	local42 = [0 57 -48 54 999]
	local47 = [0 -38 -50 999]
	local51 = [0 56 58 55 999]
	local56 = [0 51 999]
	local59 = [0 -37 52 53 999]
	local64 = [0 45 -42 40 -43 999]
	local70 = [0 60 999]
	local73 = [0 59 999]
	local76 = [0 45 42 -40 43 999]
	local82 = [0 -62 999]
	local85 = [0 -46 999]
	local88 = [0 -38 999]
	local91 = [0 56 999]
	[local94 4]
	[local98 8]
	[local106 11]
	[local117 5]
	[local122 7]
	local129 = [0 -45 -40 -41 -46 -39 999]
	local136 = [0 -45 -40 -44 -48 -50 -38 -37 999]
	local145 = [0 -42 -43 999]
	local149 = [0 -40 -62 -46 -38 999]
	local155 =  118
	[local156 20]
)
(instance rm210 of Room
	(properties
		noun N_ROOM
		picture 210
		picAngle 75
	)
	
	(method (init)
		(walkHandler addToFront: self)
		(HandsOff)
		(= [local94 0] @local0)
		(= [local98 0] @local6)
		(= [local98 1] @local12)
		(= [local98 2] @local18)
		(= [local98 3] @local15)
		(= [local98 4] @local22)
		(= [local98 5] @local25)
		(= [local106 0] @local28)
		(= [local106 1] @local34)
		(= [local106 2] @local59)
		(= [local106 3] @local42)
		(= [local106 4] @local47)
		(= [local106 5] @local56)
		(= [local106 6] @local51)
		(= [local106 7] @local39)
		(= [local117 0] @local64)
		(= [local117 1] @local70)
		(= [local117 2] @local73)
		(= [local122 0] @local76)
		(= [local122 1] @local82)
		(= [local122 2] @local85)
		(= [local122 3] @local88)
		(= [local122 4] @local91)
		(cond 
			((== prevRoomNum 200)
				(soundFx number: 925 setLoop: -1 play: 100)
				(self setScript: cominFromBoonies)
			)
			((== prevRoomNum 340)
				(self setScript: getOutAndStayOut)
				(cSound setLoop: -1 changeTo: 925)
			)
			(else
				(if (== prevRoomNum 320)
					(soundFx number: 925 setLoop: -1 play: 100)
				)
				(self setScript: cominFromTown)
			)
		)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						299 189
						267 173
						220 173
						207 157
						250 157
						175 143
						159 63
						155 63
						140 144
						67 158
						116 158
						110 173
						60 173
						18 189
						0 189
					yourself:
				)
		)
		(pot1 init:)
		(pot2 init:)
		(leftPotPalm init:)
		(leftFrond init:)
		(leftDistantPalm init:)
		(rightPotPalm init:)
		(rightFrond init:)
		(rightDistantPalm init:)
		(leftStatue init:)
		(rightStatue init:)
		(lionHead init:)
		(door init:)
		(stairs init:)
		(pillar1 init:)
		(pillar2 init:)
		(pillar3 init:)
		(pillar4 init:)
		(if (Btst fTarnaClosed)
			(closedCityGates init: addToPic:)
		else
			(egoTell init: ego @local0 @local94)
			(if (not Night)
				((Teller new:) init: guard2 @local28 @local106 @local136)
				((Teller new:) init: guard1 @local6 @local98 @local129)
				(guard1 init: addToPic:)
				(guard2 init: addToPic:)
			else
				((Teller new:) init: guard1 @local64 @local117 @local145)
				((Teller new:) init: guard2 @local76 @local122 @local149)
				(guard1 noun: N_GUARD3 init: addToPic:)
				(guard2 noun: N_GUARD4 init: addToPic:)
			)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script 0)
			((< (ego y?) 145)
				(self setScript: goinIntoTown)
			)
			((> (ego y?) 186)
				(self setScript: goinToBoonies)
			)
		)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_WALK)
			(if (< ((User curEvent?) y?) 145)
				(if (< ((User curEvent?) y?) 96)
					(if (< ((User curEvent?) y?) 61)
						(= local155 61)
					else
						(= local155 96)
					)
				else
					(= local155 118)
				)
			else
				(= local155 118)
			)
		)
		(super doVerb: theVerb &rest)
	)
	
	(method (newRoom n)
		(if (OneOf n 150 350 320)
			(soundFx fade:)
		)
		(super newRoom: n)
	)
)

(instance cominFromTown of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					init:
					x: (if (== prevRoomNum 270) 130 else 160)
					y: (if (== prevRoomNum 270) 118 else 96)
					normalize:
					edgeHit: 0
					setScale: Scaler 30 14 142 73
					noun: N_EGO_TELL
				)
				(if (== prevRoomNum 270)
					(ego setMotion: PolyPath 160 (ego y?) self)
				else
					(ego setMotion: PolyPath 160 150 self)
				)
			)
			(1
				(if (== prevRoomNum 270)
					(ego setMotion: PolyPath 160 150 self)
				else
					(self cue:)
				)
			)
			(2
				(ego setScale: Scaler 90 30 189 142)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getOutAndStayOut of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					init:
					x: 160
					y: (if (== prevRoomNum 270) 118 else 96)
					normalize:
					edgeHit: 0
					setScale: Scaler 30 14 142 73
					changeGait: 1
					setMotion: PolyPath 160 150 self
					noun: 988
				)
			)
			(1
				(ego
					setScale: Scaler 90 30 189 142
					setMotion: MoveTo 160 165 self
				)
			)
			(2
				(messager say: N_GET_OUT_STAY_OUT V_DOIT C_ESCAPE 0 self)
			)
			(3
				(ego setMotion: MoveTo 160 250 self)
				(Bset fTarnaClosed)
			)
			(4
				(curRoom newRoom: 150)
			)
		)
	)
)

(instance goinToBoonies of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) 240 self)
			)
			(1 (curRoom newRoom: 150))
		)
	)
)

(instance cominFromBoonies of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					init:
					x: 160
					y: 220
					noun: 5
					normalize:
					setScale: Scaler 90 30 189 142
					setMotion: PolyPath 160 180 self
				)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance goinIntoTown of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setScale: Scaler 30 14 142 73
					setMotion: PolyPath 160 local155 self
				)
			)
			(1
				(if (== local155 118)
					(ego setMotion: MoveTo 130 118 self)
				else
					(self cue:)
				)
			)
			(2
				(switch local155
					(118
						(curRoom newRoom: 270)
					)
					(96
						(curRoom newRoom: 320)
					)
					(61
						(curRoom newRoom: 350)
					)
				)
			)
		)
	)
)

(instance egoTell of Teller
	
	(method (showDialog)
		(super
			showDialog: 35 (not Night) 33 (not Night) 36 Night 34 Night
		)
	)
)

(instance guard1 of View
	(properties
		x 73
		y 165
		noun N_GUARD1
		approachX 125
		approachY 167
		view 210
		loop 3
		cel 1
		signal ignrAct
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: V_LOOK V_TALK V_DO)
	)
)

(instance guard2 of View
	(properties
		x 231
		y 169
		noun N_GUARD2
		approachX 214
		approachY 167
		view 210
		loop 2
		signal ignrAct
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: V_LOOK V_TALK V_DO)
	)
)

(instance guard1Talker of GloryTalker
	(properties
		x 1
		y 1
		view 191
		loop 1
		talkWidth 150
		back 57
		textX 139
		textY 5
	)
	
	(method (init)
		(super init: guard1Brow guard1Eyes guard1Mouth &rest)
	)
)

(instance guard1Mouth of Prop
	(properties
		nsTop 46
		nsLeft 46
		view 191
	)
)

(instance guard1Eyes of Prop
	(properties
		nsTop 35
		nsLeft 44
		view 191
		loop 2
	)
)

(instance guard1Brow of View
	(properties
		nsTop 24
		nsLeft 40
		view 191
		loop 3
	)
)

(instance guard2Talker of GloryTalker
	(properties
		x 203
		y 1
		view 196
		loop 1
		talkWidth 150
		back 57
		textX -180
		textY 5
	)
	
	(method (init)
		(super init: guard2Bust guard2Eyes guard2Mouth &rest)
	)
)

(instance guard2Mouth of Prop
	(properties
		nsTop 46
		nsLeft 26
		view 196
	)
)

(instance guard2Eyes of Prop
	(properties
		nsTop 35
		nsLeft 34
		view 196
		loop 2
	)
)

(instance guard2Bust of View
	(properties
		view 196
		loop 1
	)
)

(instance closedCityGates of View
	(properties
		x 161
		y 150
		noun N_GATES
		view 211
	)
)

(instance pot1 of Feature
	(properties
		x 29
		y 151
		noun N_POT1
		nsTop 135
		nsLeft 5
		nsBottom 168
		nsRight 53
		sightAngle 180
	)
)

(instance pot2 of Feature
	(properties
		x 288
		y 152
		noun N_POT2
		nsTop 135
		nsLeft 264
		nsBottom 170
		nsRight 313
		sightAngle 180
	)
)

(instance leftPotPalm of Feature
	(properties
		x 24
		y 68
		noun N_LEFT_POT_PALM
		nsTop 3
		nsLeft 1
		nsBottom 133
		nsRight 47
		sightAngle 180
	)
)

(instance leftFrond of Feature
	(properties
		x 59
		y 46
		noun N_LEFT_FROND
		nsTop 21
		nsLeft 47
		nsBottom 71
		nsRight 71
		sightAngle 180
	)
)

(instance leftDistantPalm of Feature
	(properties
		x 85
		y 38
		noun N_LEFT_DISTANT_PALM
		nsTop 28
		nsLeft 74
		nsBottom 48
		nsRight 96
		sightAngle 180
	)
)

(instance rightPotPalm of Feature
	(properties
		x 293
		y 68
		noun N_RIGHT_POT_PALM
		nsTop 2
		nsLeft 268
		nsBottom 135
		nsRight 319
		sightAngle 180
	)
)

(instance rightFrond of Feature
	(properties
		x 257
		y 69
		noun N_RIGHT_FROND
		nsTop 49
		nsLeft 247
		nsBottom 90
		nsRight 268
		sightAngle 180
	)
)

(instance rightDistantPalm of Feature
	(properties
		x 231
		y 39
		noun N_RIGHT_DISTANT_PALM
		nsTop 29
		nsLeft 222
		nsBottom 50
		nsRight 241
		sightAngle 180
	)
)

(instance leftStatue of Feature
	(properties
		x 93
		y 65
		noun N_LEFT_STATUE
		nsTop 49
		nsLeft 85
		nsBottom 81
		nsRight 102
		sightAngle 180
	)
)

(instance rightStatue of Feature
	(properties
		x 220
		y 63
		noun N_RIGHT_STATUE
		nsTop 47
		nsLeft 213
		nsBottom 80
		nsRight 228
		sightAngle 180
	)
)

(instance lionHead of Feature
	(properties
		x 158
		y 25
		noun N_LION_HEAD
		nsTop 10
		nsLeft 139
		nsBottom 40
		nsRight 178
		sightAngle 180
	)
)

(instance door of Feature
	(properties
		x 157
		y 57
		noun N_DOOR
		nsTop 53
		nsLeft 154
		nsBottom 61
		nsRight 161
		sightAngle 180
	)
)

(instance stairs of Feature
	(properties
		x 159
		y 89
		noun N_STAIRS
		nsTop 61
		nsLeft 138
		nsBottom 117
		nsRight 180
		sightAngle 180
	)
)

(instance pillar1 of Feature
	(properties
		x 82
		y 110
		noun N_PILLAR1
		nsTop 85
		nsLeft 74
		nsBottom 135
		nsRight 90
		sightAngle 180
	)
)

(instance pillar2 of Feature
	(properties
		x 98
		y 117
		noun N_PILLAR2
		nsTop 98
		nsLeft 92
		nsBottom 136
		nsRight 105
		sightAngle 180
	)
)

(instance pillar3 of Feature
	(properties
		x 216
		y 121
		noun N_PILLAR3
		nsTop 99
		nsLeft 211
		nsBottom 143
		nsRight 222
		sightAngle 180
	)
)

(instance pillar4 of Feature
	(properties
		x 231
		y 115
		noun N_PILLAR4
		nsTop 87
		nsLeft 225
		nsBottom 143
		nsRight 238
		sightAngle 180
	)
)
