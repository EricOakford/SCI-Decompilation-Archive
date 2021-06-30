;;; Sierra Script 1.0 - (do not remove this comment)
(script# 290)
(include game.sh) (include "290.shm")
(use Main)
(use LbDoor)
(use LBRoom)
(use ExitFeature)
(use Talker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm290 0
	theWanderer 38
)

(local
	local0
	local1 =  1
	local2
)
(instance rm290 of LBRoom
	(properties
		noun N_ROOM
		picture 290
		north 295
		south 280
	)
	
	(method (init)
		(LoadMany RES_VIEW 290 291 293 292)
		(LoadMany RES_SOUND 292 280)
		(ego init: normalize: 830 setScale: Scaler 137 0 190 0)
		(switch prevRoomNum
			(north
				(ego edgeHit: 0 setHeading: 180)
			)
			(south
				(ego posn: 160 230)
				(curRoom setScript: sComeInSouth)
			)
			(else 
				(ego posn: 140 173)
				(theGame handsOn:)
			)
		)
		(super init:)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						308 144
						281 145
						273 146
						261 152
						236 157
						199 157
						197 164
						175 169
						27 137
						27 129
						96 124
						97 117
						44 118
						28 81
						27 120
						11 123
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						64 189
						85 171
						107 189
					yourself:
				)
		)
		(officeDoor init: locked: (Btst 43))
		(happyWanderer init: setScript: sWander)
		(sergeant
			init:
			approachVerbs: 1 6 2 4 7 8
			setScript: sMoveSergeant
		)
		(southExitFeature init:)
		(southExitFeature2 init:)
		(files init:)
		(desk init:)
		(poster1 init:)
		(poster2 init:)
		(poster3 init:)
		(poster4 init:)
		(poster5 init:)
		(poster6 init:)
		(pole init:)
	)
	
	(method (doit)
		(cond 
			(script)
			((IsObjectOnControl ego cRED)
				(self setScript: sEgoLeaveSouth)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(super dispose: &rest)
	)
)

(instance sEgoLeaveSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego heading: 180)
				(= cycles 1)
			)
			(1
				(ego setMotion: MoveFwd 80 self)
			)
			(2 (curRoom newRoom: 280))
		)
	)
)

(instance sComeInSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setMotion: MoveTo 160 175 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sKnock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(knockSound play: self)
			)
			(1 (messager say: 3 4 0 0 self))
			(2
				(= local2 1)
				(officeDoor doVerb: 4)
				(self dispose:)
			)
		)
	)
)

(instance sMoveSergeant of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(not local0)
				(sergeant cycler?)
				(== (sergeant cel?) 3)
			)
			(shuffleSound play: sergeant)
			(= local0 1)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sergeant setCycle: Forward)
				(= seconds (Random 1 6))
			)
			(1
				(sergeant setCycle: 0)
				(= seconds (Random 2 4))
			)
			(2 (self init:))
		)
	)
)

(instance sGiveSandwich of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 252 152 self)
			)
			(1 (Face ego sergeant self))
			(2
				(sergeant setScript: 0)
				(sergeant
					loop: 1
					cel: 0
					cycleSpeed: 10
					setCycle: CycleTo 8 1 self
				)
				(ego
					view: 292
					posn: 240 152
					loop: 1
					cel: 0
					setScale: Scaler 100 100 190 0
					cycleSpeed: 10
					setCycle: CycleTo 4 1 self
				)
			)
			(3 (messager say: 2 15))
			(4
				(ego setCycle: EndLoop self)
				(sergeant setCycle: EndLoop self)
			)
			(5 0)
			(6
				(sergeant loop: 0 setScript: sMoveSergeant)
				(Bset 7)
				((ScriptID 21 1) doit: 772)
				(ego put: 3)
				(ego normalize: 830)
				(ego
					loop: 7
					posn: 254 152
					setScale: Scaler 137 0 190 0
					setHeading: 315
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sWander of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 20)))
			(1
				(client setCycle: Walk setMotion: MoveTo 154 122 self)
			)
			(2 (= ticks (Random 30 240)))
			(3
				(client setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(4
				(client
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo -20 141 self
				)
			)
			(5
				(client dispose:)
				(self dispose:)
			)
		)
	)
)

(instance happyWanderer of Actor
	(properties
		x -20
		y 141
		noun 1
		view 293
		signal $1000
	)
)

(instance sergeant of Prop
	(properties
		x 220
		y 116
		noun 2
		approachX 254
		approachY 152
		view 291
	)
	
	(method (doVerb theVerb theItem &tmp temp0 temp1 temp2)
		(switch theVerb
			(15
				(curRoom setScript: sGiveSandwich)
			)
			(2
				(cond 
					((Btst 7) (= temp1 73))
					(local1 (= temp1 78))
					(else (= temp1 79))
				)
				(messager say: 2 2 temp1)
				(= local1 0)
			)
			(6
				(if (not (Btst 7))
					(messager say: 2 6 70)
				else
					(if
						(==
							(= temp0
								(if (== argc 2)
									theItem
								else
									(curRoom setInset: (ScriptID 20 0))
								)
							)
							-1
						)
						(return)
					)
					(= temp2 (& temp0 $00ff))
					(= temp1
						(switch (& temp0 $ff00)
							(256 (+ temp2 1))
							(512 (+ temp2 18))
							(768 (+ temp2 26))
							(1024 (+ temp2 61))
						)
					)
					(switch temp0
						(260
							(if (officeDoor locked?)
								(messager say: 2 6 71)
							else
								(messager say: 2 6 5)
							)
						)
						(520
							(messager say: 2 6 26)
							((ScriptID 21 0) doit: 1029)
						)
						(264
							(messager say: 2 6 9)
							((ScriptID 21 1) doit: 518)
							((ScriptID 21 0) doit: 520)
						)
						(else 
							(if (Message MsgGet curRoomNum noun 6 temp1 1)
								(messager say: noun 6 temp1)
							else
								(messager say: noun 6 43)
							)
						)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(= local0 0)
		(super cue:)
	)
)

(instance officeDoor of LbDoor
	(properties
		x 18
		y 57
		noun 3
		approachX 58
		approachY 125
		view 290
		entranceTo 295
		moveToX 37
		moveToY 110
		enterType 0
		exitType 0
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					(local2 (super doVerb: theVerb &rest))
					((not (self locked?)) (ego setScript: sKnock))
					(else (Face ego officeDoor) (super doVerb: theVerb &rest))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (createPoly)
		(super createPoly: 12 112 55 112 54 121 15 122)
	)
)

(instance theWanderer of Narrator
	(properties
		back 15
	)
	
	(method (init)
		(= font userFont)
		(super init: &rest)
	)
)

(instance files of Feature
	(properties
		y 2
		noun 4
		nsTop 76
		nsLeft 150
		nsBottom 98
		nsRight 204
	)
)

(instance desk of Feature
	(properties
		y 1
		noun 5
		nsTop 98
		nsLeft 58
		nsBottom 151
		nsRight 261
	)
)

(instance poster1 of Feature
	(properties
		y 189
		noun 6
		nsTop 34
		nsLeft 75
		nsBottom 55
		nsRight 100
	)
)

(instance poster2 of Feature
	(properties
		y 189
		noun 7
		nsTop 64
		nsLeft 84
		nsBottom 79
		nsRight 101
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(4 (poster1 doVerb: 4))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance poster3 of Feature
	(properties
		y 189
		noun 8
		nsTop 66
		nsLeft 69
		nsBottom 85
		nsRight 79
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(4 (poster1 doVerb: 4))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance poster4 of Feature
	(properties
		y 189
		noun 9
		nsTop 85
		nsLeft 74
		nsBottom 106
		nsRight 81
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(4 (poster1 doVerb: 4))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance poster5 of Feature
	(properties
		y 189
		noun 10
		nsTop 100
		nsLeft 84
		nsBottom 114
		nsRight 94
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(4 (poster1 doVerb: 4))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance poster6 of Feature
	(properties
		y 189
		noun 11
		nsTop 117
		nsLeft 69
		nsBottom 137
		nsRight 95
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(4 (poster1 doVerb: 4))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance pole of Feature
	(properties
		y 188
		noun 12
		nsLeft 71
		nsBottom 189
		nsRight 100
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsLeft 111
		nsBottom 189
		nsRight 320
		cursor 11
		exitDir 3
		noun 14
	)
)

(instance southExitFeature2 of ExitFeature
	(properties
		nsTop 184
		nsBottom 189
		nsRight 63
		cursor 11
		exitDir 3
		noun 14
	)
)

(instance shuffleSound of Sound
	(properties
		flags $0001
		number 292
	)
)

(instance knockSound of Sound
	(properties
		flags $0001
		number 297
	)
)
