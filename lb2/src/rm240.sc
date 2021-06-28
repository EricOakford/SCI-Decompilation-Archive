;;; Sierra Script 1.0 - (do not remove this comment)
(script# 240)
(include game.sh) (include "240.shm")
(use Main)
(use LBRoom)
(use Conv)
(use Talker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm240 0
	local_Steve 12
)

(local
	theConv = [
		-1 N_STEVE V_ASK C_MUSEUM 1 0 0 0
		-1 N_STEVE V_ASK C_MUSEUM 2 0 0 0
		-1 N_STEVE V_ASK C_MUSEUM 3 0 0 0
		-1 N_STEVE V_ASK C_MUSEUM 4 0 0 0
		-1 N_STEVE V_ASK C_MUSEUM 5 0 0 0
		-1 N_STEVE V_ASK C_MUSEUM 6 0 0 0
		-1 N_STEVE V_ASK C_MUSEUM 7
		]
	talkCount
	local58
	steveBlocks
)
(instance rm240 of LBRoom
	(properties
		picture 120
		style FADEOUT
		east 250
		vanishingX 187
		vanishingY 135
	)
	
	(method (init)
		(LoadMany RES_VIEW 852 284 1125 125 830 121)
		(LoadMany RES_SOUND 40 120 121)
		(ego init: setScale: Scaler 145 20 187 135 normalize: 830)
		(switch prevRoomNum
			(east
				(if (Btst 9)
					(curRoom setScript: sEnterEastN)
				else
					(steve init: setScale: Scaler 197 10 187 135)
					(self
						addObstacle:
							(= steveBlocks
								((Polygon new:)
									type: PBarredAccess
									init: 199 159 199 171 160 171 160 159
									yourself:
								)
							)
					)
					(curRoom setScript: sEnterEast1)
				)
			)
			(else 
				(ego posn: 160 160)
				(theGame handsOn:)
			)
		)
		(super init:)
		(self
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						84 154
						83 176
						183 176
						243 176
						243 164
						212 164
						212 154
						242 154
						242 142
						162 142
						91 154
					yourself:
				)
		)
		(theMusic number: 121 loop: -1 flags: mNOPAUSE play:)
		(theMusic2 fade:)
		(person1 init: setStep: 1 1)
		(person2 init: setStep: 1 1)
		(person3 init: setStep: 1 1)
		(taxiSign init:)
		(ship setOnMeCheck: ftrControl cYELLOW init:)
		(crate init:)
		(warehouses init:)
		(city init:)
		(city1 init:)
		(cityLeft init:)
		(sky init:)
		(skyleft init:)
		(water init:)
		(pilingRt init:)
		(pilingL init:)
		(docks setOnMeCheck: ftrControl cLMAGENTA init:)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego cGREY)
				(curRoom setScript: sHitEdgeScreen)
			)
		)
	)
	
	(method (dispose)
		(theMusic fade:)
		(noConvTimer dispose: delete:)
		(super dispose:)
	)
)

(instance sEnterEast1 of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 22 0) doit: 2)
				(theGame points: 1 130)
				((ScriptID 1881 2) x: 203 y: 17 textX: -180 textY: 0)
				(ego
					edgeHit: 0
					posn: 250 159
					setHeading: 270
					setMotion: MoveFwd 38 self
				)
				(steve
					setLoop: 5
					setCycle: Walk
					setMotion: MoveTo 180 166 self
				)
			)
			(1 0)
			(2
				(steve
					setLoop: 8
					cel: 0
					posn: 180 165
					cycleSpeed: 12
					setScript: sSteveAnimates
				)
				(theGame handsOn:)
				(Bset 9)
				(person1 setScript: moveItAround)
				(= seconds 1)
			)
			(3
				(messager say: 14 NULL NULL 1)
				(person3 setScript: (moveItAround new:))
				(= seconds 1)
			)
			(4
				(person2 setScript: (moveItAround new:))
				(= seconds 1)
			)
			(5
				(noConvTimer setReal: noConvTimer 15)
				(self dispose:)
			)
		)
	)
)

(instance sEnterEastN of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					edgeHit: 0
					posn: 250 150
					setHeading: 270
					setMotion: MoveFwd 35 self
				)
			)
			(1
				(theGame handsOn:)
				(person1 setScript: moveItAround)
				(= seconds 2)
			)
			(2
				(person3 setScript: (moveItAround new:))
				(= cycles 5)
			)
			(3
				(person2 setScript: (moveItAround new:))
				(= seconds 6)
			)
			(4 (self dispose:))
		)
	)
)

(instance sSteveAnimates of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= seconds (Random 4 8)))
			(1
				(switch (= temp0 (Random 0 3))
					(0
						(steve loop: 8 setCycle: EndLoop self)
					)
					(1
						(if (== (Random 0 3) 0)
							(steve loop: 9 setCycle: EndLoop self)
						else
							(= temp0 3)
							(= cycles 1)
						)
					)
					(2
						(steve loop: 10 setCycle: EndLoop self)
					)
					(3 (= seconds (Random 2 4)))
				)
			)
			(2 (= seconds (Random 4 8)))
			(3
				(if (!= temp0 3)
					(steve setCycle: BegLoop self)
				else
					(= cycles 1)
				)
			)
			(4 (= state -1) (= cycles 1))
		)
	)
)

(instance moveItAround of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: MoveTo 194 127 self)
			)
			(1
				(client posn: 234 137 setMotion: MoveTo 191 138 self)
			)
			(2
				(switch (Random 0 1)
					(0
						(client
							setMotion: MoveTo (Random 138 174) (Random 136 140) self
						)
					)
					(1 (= seconds 2))
				)
			)
			(3
				(client setMotion: MoveTo 191 138 self)
			)
			(4
				(client setMotion: MoveTo 234 137 self)
			)
			(5
				(client loop: (Random 0 4) posn: 169 121)
				(= seconds 2)
			)
			(6 (= state -1) (= cycles 1))
		)
	)
)

(instance sTalkSteve of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(noConvTimer setReal: noConvTimer 15)
				(= cycles 1)
			)
			(1
				((ScriptID 21 0) doit: 263)
				(if (< (ego x?) 181)
					(ego setMotion: PolyPath 162 165 self)
				else
					(ego setMotion: PolyPath 204 169 self)
				)
			)
			(2 (Face ego steve self))
			(3 (= cycles 4))
			(4
				(switch (++ talkCount)
					(1
						(messager say: N_STEVE V_TALK 1 0 self)
						((ScriptID 21 0) doit: 263)
					)
					(2
						(messager say: N_STEVE V_TALK 2 0 self)
					)
					(3
						(messager say: N_STEVE V_TALK 3 0 self)
					)
					(4
						(messager say: N_STEVE V_TALK 4 0 self)
					)
					(5
						(messager say: N_STEVE V_TALK 5 0 self)
					)
					(else 
						(messager say: N_STEVE V_TALK 6 0 self)
					)
				)
				(= seconds 1)
			)
			(5 (= cycles 4))
			(6
				(noConvTimer setReal: noConvTimer 15)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sAskSteve of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(noConvTimer setReal: noConvTimer 15)
				(= cycles 1)
			)
			(1
				((ScriptID 21 0) doit: 263)
				(if (< (ego x?) 181)
					(ego setMotion: PolyPath 162 165 self)
				else
					(ego setMotion: PolyPath 204 169 self)
				)
			)
			(2 (Face ego steve self))
			(3
				(theGame handsOff:)
				(= cycles 4)
			)
			(4
				(switch (curRoom setInset: (ScriptID 20 0))
					(262
						(messager say: N_STEVE V_ASK 7 0 self)
					)
					(261
						(messager say: N_STEVE V_ASK 8 0 self)
					)
					(264
						(messager say: N_STEVE V_ASK 9 0 self)
					)
					(260
						(messager say: N_STEVE V_ASK 10 0 self)
					)
					(259
						((ScriptID 21 0) doit: 269)
						(messager say: N_STEVE V_ASK 11 0 self)
					)
					(269
						(messager say: N_STEVE V_ASK 12 0 self)
					)
					(258
						(switch (++ local58)
							(1
								(messager say: N_STEVE V_ASK 13 0 self)
							)
							(else 
								(messager say: N_STEVE V_ASK 14 0 self)
							)
						)
					)
					(263
						(messager say: N_STEVE V_ASK 26 0 self)
					)
					(780
						((ScriptID 21 0) doit: 258)
						(messager say: N_STEVE V_ASK 15 0 self)
					)
					(518
						(messager say: N_STEVE V_ASK 16 0 self)
					)
					(516
						(messager say: N_STEVE V_ASK 17 0 self)
					)
					(514
						(messager say: N_STEVE V_ASK 18 0 self)
					)
					(519
						(messager say: N_STEVE V_ASK 27 0 self)
					)
					(517
						(self setScript: sAskMuseum self)
					)
					(1026
						(messager say: N_STEVE V_ASK 28 0 self)
					)
					(773
						(messager say: N_STEVE V_ASK 20 0 self)
					)
					(772
						(messager say: N_STEVE V_ASK 22 0 self)
					)
					(769
						(messager say: N_STEVE V_ASK 21 0 self)
					)
					(771
						(messager say: N_STEVE V_ASK 23 0 self)
					)
					(-1 (= cycles 1))
					(else 
						(messager say: N_STEVE V_ASK 25 0 self)
					)
				)
			)
			(5 (= cycles 4))
			(6
				(if (cast contains: steve)
					(noConvTimer setReal: noConvTimer 15)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sAskMuseum of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(noConvTimer dispose: delete:)
				((ScriptID 21 0) doit: 258)
				(myConv load: @theConv init: self)
				(steve setScript: 0)
			)
			(1
				(steve
					setLoop: 6
					posn: (- (steve x?) 2) (+ (steve y?) 3)
					ignoreActors: TRUE
					setCycle: EndLoop self
				)
			)
			(2
				(steve
					setLoop: 7
					posn: (+ (steve x?) 2) (- (steve y?) 1)
					setHeading: 0
					setStep: 1 1
					moveSpeed: 9
					setCycle: Walk
					setMotion: MoveTo (steve x?) 140 self
				)
				(= seconds 5)
			)
			(3
				(if seconds (= seconds 0))
				(theGame handsOn:)
				(steve dispose:)
				((curRoom obstacles?) delete: steveBlocks)
				(steveBlocks dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sHailCab of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(if (cast contains: steve) (steve setScript: 0))
				(ego setMotion: PolyPath 213 152 self)
			)
			(2
				(ego
					view: 852
					loop: 0
					cel: 0
					setScale: Scaler 170 20 187 135
					setCycle: EndLoop self
				)
				(if (cast contains: steve)
					(steve
						setLoop: 7
						setHeading: 0
						setStep: 1 1
						moveSpeed: 9
						setMotion: MoveTo (steve x?) 140
					)
				)
				(noise number: 97 flags: mNOPAUSE play:)
			)
			(3
				(taxi
					init:
					setPri: 11
					setScale: Scaler 187 70 187 135
					setLoop: 4
				)
				(= cycles 1)
			)
			(4
				(theMusic2 play:)
				(taxi setMotion: MoveTo 281 154 self)
			)
			(5
				(taxi setMotion: MoveTo 293 163 self)
			)
			(6
				(ego
					normalize: 830
					setHeading: 90
					setScale: Scaler 145 20 187 135
					setMotion: MoveFwd 32 self
				)
			)
			(7
				(noise number: 40 flags: mNOPAUSE play: self)
			)
			(8 (curRoom newRoom: 250))
		)
	)
)

(instance sHitEdgeScreen of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(messager say: N_WAREHOUSES V_WALK NULL 0 self 91)
			)
			(2
				(if (> (ego heading?) 180)
					(ego setHeading: 90)
				else
					(ego setHeading: 270)
				)
				(ego setMotion: MoveFwd 25 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance person1 of Actor
	(properties
		x 169
		y 121
		view 121
		priority 3
		signal (| ignrAct fixedLoop fixPriOn)
		scaleSignal scalable
		moveSpeed 25
	)
)

(instance person2 of Actor
	(properties
		x 169
		y 121
		view 121
		loop 1
		priority 3
		signal (| ignrAct fixedLoop fixPriOn)
		scaleSignal scalable
		moveSpeed 27
	)
)

(instance person3 of Actor
	(properties
		x 169
		y 121
		view 121
		loop 2
		priority 3
		signal (| ignrAct fixedLoop fixPriOn)
		scaleSignal scalable
		moveSpeed 24
	)
)

(instance steve of Actor
	(properties
		x 184
		y 140
		noun N_STEVEDORE
		view 121
		loop 5
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(curRoom setScript: sTalkSteve)
			)
			(V_ASK
				(curRoom setScript: sAskSteve)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance taxiSign of View
	(properties
		x 225
		y 160
		noun N_TAXI_SIGN
		view 284
		cel 2
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: sHailCab)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance taxi of Actor
	(properties
		x 290
		y 146
		view 852
		loop 4
	)
)

(instance ship of Feature
	(properties
		y 100
		noun N_SHIP
	)
)

(instance crate of Feature
	(properties
		x 113
		y 189
		noun N_CRATE
		nsTop 164
		nsLeft 88
		nsBottom 179
		nsRight 139
		sightAngle 40
	)
)

(instance warehouses of Feature
	(properties
		x 209
		y 128
		noun N_WAREHOUSES
		nsTop 121
		nsLeft 189
		nsBottom 136
		nsRight 230
		sightAngle 40
	)
)

(instance city of Feature
	(properties
		x 199
		y 82
		noun N_CITY
		nsTop 46
		nsLeft 188
		nsBottom 119
		nsRight 210
		sightAngle 40
	)
)

(instance city1 of Feature
	(properties
		x 204
		y 93
		noun N_CITY
		nsTop 67
		nsLeft 177
		nsBottom 120
		nsRight 231
		sightAngle 40
	)
)

(instance cityLeft of Feature
	(properties
		x 95
		y 100
		noun N_CITY
		nsTop 72
		nsLeft 88
		nsBottom 128
		nsRight 103
		sightAngle 40
	)
)

(instance sky of Feature
	(properties
		x 199
		y 26
		noun N_SKY
		nsTop 8
		nsLeft 168
		nsBottom 45
		nsRight 231
		sightAngle 40
	)
)

(instance skyleft of Feature
	(properties
		x 107
		y 20
		noun N_SKY
		nsTop 6
		nsLeft 89
		nsBottom 35
		nsRight 126
		sightAngle 40
	)
)

(instance water of Feature
	(properties
		x 99
		y 139
		noun N_WATER
		nsTop 135
		nsLeft 87
		nsBottom 144
		nsRight 112
		sightAngle 40
	)
)

(instance pilingRt of Feature
	(properties
		x 214
		y 168
		noun N_PILING
		nsTop 151
		nsLeft 198
		nsBottom 185
		nsRight 230
		sightAngle 40
	)
)

(instance pilingL of Feature
	(properties
		x 171
		y 176
		noun N_PILING
		nsTop 169
		nsLeft 150
		nsBottom 183
		nsRight 193
		sightAngle 40
	)
)

(instance docks of Feature
	(properties
		y 160
		noun N_DOCKS
	)
)

(instance myConv of Conversation)

(instance local_Steve of Talker
	(properties
		x 9
		y 85
		view 125
		loop 3
		priority 15
		signal fixPriOn
		disposeWhenDone 0
		talkWidth 130
		back 15
		textX 130
		textY -75
		name "local Steve"
	)
	
	(method (init)
		(= font userFont)
		(super init: 0 tSteveEyes tSteveMouth &rest)
	)
)

(instance tSteveMouth of Prop
	(properties
		nsTop 34
		nsLeft 57
		view 1125
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance tSteveEyes of Prop
	(properties
		nsTop 28
		nsLeft 58
		view 1125
		loop 2
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance noConvTimer of Timer
	
	(method (cue)
		(cond 
			((and (not (curRoom script?)) (cast contains: steve))
				(messager say: N_NO_CONV NULL NULL 2)
				(noConvTimer setReal: noConvTimer 15)
			)
			((not (cast contains: steve))
				(noConvTimer dispose: delete:)
			)
			(else (self setReal: self 5))
		)
	)
)

(instance noise of Sound
	(properties
		flags mNOPAUSE
	)
)
