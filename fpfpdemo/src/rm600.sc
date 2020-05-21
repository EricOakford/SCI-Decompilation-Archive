;;; Sierra Script 1.0 - (do not remove this comment)
(script# 600)
(include game.sh) (include "600.shm")
(use Main)
(use FPRoom)
(use Scaler)
(use RangeOsc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm600 0
)

(local
	local0 =  1
	oldEgoMover
)
(instance rm600 of FPRoom
	(properties
		noun N_ROOM
		picture 600
		style FADEOUT
		horizon 40
		south 250
	)
	
	(method (init)
		(self setRegions: rgFreddy)
		(switch prevRoomNum
			(105
				(self setScript: sStartTheGame)
				(ego
					view: 124
					posn: 215 158
					init:
					setScale: Scaler 150 70 189 128
					setCycle: RangeOsc 20 6 8
				)
			)
			(else 
				(theGame handsOn:)
				(ego
					init:
					posn: 160 220
					setScale: Scaler 150 70 189 128
					normalize:
				)
			)
		)
		(LoadMany RES_VIEW 801)
		(theGame handsOff:)
		(super init:)
		(theMusic
			number: (if (> numVoices 11) 600 else 1600)
			loop: -1
			play:
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 0
						319 0
						319 189
						243 189
						282 136
						80 136
						99 127
						126 127
						126 123
						155 124
						155 120
						123 120
						123 125
						93 125
						83 129
						31 142
						42 161
						27 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						59 156
						106 139
						169 139
						115 163
						58 163
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						118 163
						174 139
						233 139
						233 149
						196 168
					yourself:
				)
		)
		(if
			(and
				(not (ego has: iSaltpeter))
				(not (ego has: iFullSaltpeter))
				(not (Btst fPutSaltpeterInCan))
			)
			(bottle init: approachVerbs: V_DO)
		)
		(if (not (ego has: iPrescription))
			(rx init: approachVerbs: V_DO)
		)
		(gate init: approachVerbs: V_DO)
		(if
			(and
				(not (ego has: iEmptyVial))
				(not (ego has: iFullVial))
				(not (Btst fPutSulfurInCan))
			)
			(vial init: ignoreActors: approachVerbs: V_DO)
		)
		(backDoor init:)
		(backCounter init:)
		(iceCream init:)
		(diploma init:)
		(backShelf1 init:)
		(backShelf2 init:)
		(bigPicture init:)
		(rearShelf1 init:)
		(rearShelf2 init:)
		(rearShelf3 init:)
		(desk
			init:
			setOnMeCheck: dynamicName cBROWN
		)
		(leftShelf1
			init:
			setOnMeCheck: dynamicName cBLUE
		)
		(leftShelf2
			init:
			setOnMeCheck: dynamicName cGREEN
		)
		(leftShelf3
			init:
			setOnMeCheck: dynamicName cCYAN
		)
		(leftShelf4
			init:
			setOnMeCheck: dynamicName cRED
		)
		(lFrontShelf1
			init:
			setOnMeCheck: dynamicName cMAGENTA
		)
		(lFrontShelf2
			init:
			setOnMeCheck: dynamicName cRED
		)
		(rFrontShelf1
			init:
			setOnMeCheck: dynamicName cGREEN
		)
		(rFrontShelf2
			init:
			setOnMeCheck: dynamicName cCYAN
		)
		(handSign
			init:
			setOnMeCheck: dynamicName cMAGENTA
		)
		(lamp
			init:
			setOnMeCheck: dynamicName cBROWN
		)
		(cabinet
			init:
			setOnMeCheck: dynamicName cLGREY
		)
		(bottles1
			init:
			setOnMeCheck: dynamicName cGREY
		)
		(bottles2
			init:
			setOnMeCheck: dynamicName cLBLUE
		)
		(lFrontShelf
			init:
			setOnMeCheck: dynamicName cLGREEN
		)
		(rFrontShelf
			init:
			setOnMeCheck: dynamicName cLCYAN
		)
		(photo
			init:
			setOnMeCheck: dynamicName cLRED
		)
		(statue
			init:
			setOnMeCheck: dynamicName cLMAGENTA
		)
		(jar
			init:
			setOnMeCheck: dynamicName cYELLOW
		)
		(column
			init:
			setOnMeCheck: dynamicName cWHITE
		)
	)
	
	(method (doit)
		(if (ego inRect: 86 128 110 135)
			(if local0
				(= local0 0)
				(ego setLoop: -1)
				(= oldEgoMover (ego mover?))
				(ego mover: 0)
				(if (< (ego y?) 130)
					(curRoom setScript: sOpenGate 0 0)
				else
					(curRoom setScript: sOpenGate 0 1)
				)
			)
		else
			script
		)
		(super doit:)
	)
	
	(method (dispose)
		(theMusic fade: 0 10 12 1)
		(super dispose:)
	)
)

(instance sOpenGate of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(gate setPri: 10)
				(gate setCycle: EndLoop self)
				(gateSound play:)
			)
			(1
				(if register
					(= temp0 95)
					(= temp1 124)
				else
					(= temp0 79)
					(= temp1 136)
				)
				(ego setMotion: PolyPath temp0 temp1 self)
			)
			(2
				(gate setCycle: BegLoop self)
				(gateSound play:)
				(ego setHeading: (if register 180 else 0))
				(theGame handsOn:)
				(if oldEgoMover
					(ego mover: oldEgoMover)
					(= oldEgoMover 0)
					(ego setCycle: StopWalk -1)
					(ego setLoop: theStopGroop)
				)
				(if register
					(ego setScript: sComeBackLittleFreddy)
				)
				(= local0 1)
				(self dispose:)
			)
		)
	)
)

(instance sComeBackLittleFreddy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 5)
			)
			(1
				(ego setMotion: PolyPath 140 116 self)
			)
			(2
				(= seconds 4)
			)
			(3
				(ego setMotion: PolyPath 64 146 self)
			)
			(4
				(messager say: N_CANT_GO NULL NULL 0 self)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sStartTheGame of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 2)
			)
			(1
				((ScriptID tlkWilly 1) disposeWhenDone: FALSE)
				(messager say: N_INTRO NULL NULL 1 4 self)
			)
			(2
				(srini init: setLoop: 1 setMotion: MoveTo 168 173 self)
			)
			(3
				(ego normalize: setHeading: 180)
				(= cycles 10)
			)
			(4
				((ScriptID tlkWilly 1) disposeWhenDone: TRUE)
				(messager say: N_INTRO NULL NULL 5 9 self)
			)
			(5
				(srini setLoop: 0 setMotion: MoveTo 168 320 self)
			)
			(6
				(theGame handsOn:)
				(srini dispose:)
				(self dispose:)
			)
		)
	)
)

(instance gate of Prop
	(properties
		x 110
		y 103
		noun N_GATE
		approachX 88
		approachY 130
		view 600
		loop 3
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
	)
)

(instance srini of Actor
	(properties
		x 168
		y 320
		view 801
	)
	
	(method (init)
		(super init:)
		(self setCycle: StopWalk -1 setScale: -1 ego)
	)
)

(instance bottle of View
	(properties
		x 187
		y 151
		noun N_BOTTLE
		approachX 189
		approachY 168
		view 600
		priority 12
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(ego get: iSaltpeter self)
				(self dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance vial of View
	(properties
		x 107
		y 151
		z 30
		noun N_VIAL
		approachX 108
		approachY 166
		view 600
		loop 1
		priority 12
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(ego get: iEmptyVial self)
				(self dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rx of View
	(properties
		x 234
		y 134
		z 28
		noun N_RX
		approachX 230
		approachY 137
		view 600
		loop 2
		priority 10
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(ego get: iPrescription self)
				(self dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rFrontShelf1 of Feature
	(properties
		x 201
		y 146
		noun N_RFRONTSHELF1
		nsTop 115
		nsLeft 183
		nsBottom 134
		nsRight 220
	)
)

(instance rFrontShelf2 of Feature
	(properties
		x 200
		y 146
		noun N_RFRONTSHELF2
		nsTop 131
		nsLeft 183
		nsBottom 156
		nsRight 218
	)
)

(instance backDoor of Feature
	(properties
		x 126
		y 91
		noun N_BACKDOOR
		nsTop 79
		nsLeft 117
		nsBottom 103
		nsRight 135
		sightAngle 40
	)
)

(instance backCounter of Feature
	(properties
		x 187
		y 133
		noun N_BACKCOUNTER
		nsTop 104
		nsLeft 110
		nsBottom 131
		nsRight 265
	)
)

(instance iceCream of Feature
	(properties
		x 48
		y 88
		noun N_ICECREAM
		nsTop 38
		nsBottom 138
		nsRight 97
	)
)

(instance diploma of Feature
	(properties
		x 298
		y 73
		noun N_DIPLOMA
		nsTop 68
		nsLeft 291
		nsBottom 79
		nsRight 306
	)
)

(instance backShelf1 of Feature
	(properties
		x 238
		y 88
		noun N_BACKSHELF1
		nsTop 84
		nsLeft 215
		nsBottom 92
		nsRight 261
	)
)

(instance backShelf2 of Feature
	(properties
		x 237
		y 98
		noun N_BACKSHELF2
		nsTop 94
		nsLeft 215
		nsBottom 102
		nsRight 260
	)
)

(instance bigPicture of Feature
	(properties
		x 215
		y 16
		noun N_BIGPICTURE
		nsLeft 166
		nsBottom 32
		nsRight 265
	)
)

(instance rearShelf1 of Feature
	(properties
		x 159
		y 80
		noun N_REARSHELF1
		nsTop 77
		nsLeft 143
		nsBottom 83
		nsRight 175
	)
)

(instance rearShelf2 of Feature
	(properties
		x 159
		y 89
		noun N_REARSHELF2
		nsTop 85
		nsLeft 143
		nsBottom 93
		nsRight 175
	)
)

(instance rearShelf3 of Feature
	(properties
		x 148
		y 99
		noun N_REARSHELF3
		nsTop 95
		nsLeft 143
		nsBottom 103
		nsRight 154
	)
)

(instance desk of Feature
	(properties
		x 305
		y 189
		noun N_DESK
		nsTop 135
		nsLeft 267
		nsBottom 189
		nsRight 311
	)
)

(instance lFrontShelf1 of Feature
	(properties
		x 126
		y 146
		noun N_LFRONTSHELF1
		nsTop 121
		nsLeft 109
		nsBottom 136
		nsRight 144
	)
)

(instance lFrontShelf2 of Feature
	(properties
		x 126
		y 146
		noun N_LFRONTSHELF2
		nsTop 133
		nsLeft 108
		nsBottom 154
		nsRight 144
	)
)

(instance leftShelf1 of Feature
	(properties
		x 80
		y 89
		noun N_LEFTSHELF1
	)
)

(instance leftShelf2 of Feature
	(properties
		x 92
		y 89
		noun N_LEFTSHELF2
	)
)

(instance leftShelf3 of Feature
	(properties
		x 103
		y 89
		noun N_LEFTSHELF3
	)
)

(instance leftShelf4 of Feature
	(properties
		x 112
		y 89
		noun N_LEFTSHELF4
	)
)

(instance handSign of Feature
	(properties
		x 55
		y 39
		noun N_HANDSIGN
	)
)

(instance lamp of Feature
	(properties
		x 91
		y 24
		noun N_LAMP
	)
)

(instance cabinet of Feature
	(properties
		x 29
		y 189
		noun N_CABINET
	)
)

(instance bottles1 of Feature
	(properties
		x 118
		y 146
		noun N_BOTTLES1
	)
)

(instance bottles2 of Feature
	(properties
		x 189
		y 146
		noun N_BOTTLES2
	)
)

(instance lFrontShelf of Feature
	(properties
		x 95
		y 146
		noun N_LFRONTSHELF
	)
)

(instance rFrontShelf of Feature
	(properties
		x 157
		y 146
		noun N_RFRONTSHELF
	)
)

(instance photo of Feature
	(properties
		x 165
		y 146
		noun N_PHOTO
	)
)

(instance statue of Feature
	(properties
		x 179
		y 133
		noun N_STATUE
	)
)

(instance jar of Feature
	(properties
		x 293
		y 189
		noun N_JAR
	)
)

(instance column of Feature
	(properties
		x 315
		y 189
		noun N_COLUMN
	)
)

(instance gateSound of Sound
	(properties
		flags mNOPAUSE
		number 146
	)
)
