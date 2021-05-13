;;; Sierra Script 1.0 - (do not remove this comment)
(script# 270)
(include sci.sh)
(use Main)
(use LBRoom)
(use ExitFeature)
(use Inset)
(use Scaler)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm270 0
)

(local
	gNarratorX
	gNarratorY
	gNarratorTalkWidth
	local3 =  1
	local4 =  1
	local5 =  1
	local6 =  1
	local7 =  1
	local8 =  1
	local9 =  1
	local10 =  1
	local11 =  1
	local12 =  1
	local13 =  1
	local14 =  1
)
(instance rm270 of LBRoom
	(properties
		noun 10
		picture 270
		south 260
		vanishingX 3
		vanishingY 39
	)
	
	(method (init)
		(LoadMany 128 272 271)
		(LoadMany 132 270)
		(ego init: setScale: Scaler 125 0 190 29 normalize: 830)
		(switch prevRoomNum
			(south
				(ego posn: 160 230)
				(curRoom setScript: sEnter)
			)
			(else 
				(ego posn: 160 160)
				(theGame handsOn:)
			)
		)
		(super init:)
		((ScriptID 1881 2) x: 215 y: 95 textX: -170 textY: 5)
		(theMusic number: 270 flags: 1 loop: -1 play:)
		(self
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
						311
						189
						286
						145
						216
						145
						216
						137
						153
						137
						128
						137
						70
						137
						54
						189
						0
						189
						0
						137
					yourself:
				)
		)
		(lofat
			init:
			approachVerbs: 1 6 2 4 7 8
			setCycle: RandCycle
		)
		(if (not (ego has: 32)) (dress init:))
		(dress2 init: addToPic:)
		(dress3 init: addToPic:)
		(dress4 init: addToPic:)
		(dress5 init: addToPic:)
		(dress6 init: addToPic:)
		(southExitFeature init:)
		(dClothes init:)
		(cClothes init:)
		(lamp init:)
		(symbols init:)
		(jar1 init:)
		(jar2 init:)
		(counter1 init:)
		(counter2 init:)
	)
	
	(method (doit)
		(cond 
			(script)
			((IsObjectOnControl ego 16) (self setScript: sEgoLeaveSouth))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(theMusic fade: 0 30 12 1)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(return
			(if (& (event type?) evJOYSTICK)
				(sGetDress cue:)
				(return 0)
			else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(3 (sGetDress cue:) (return 0))
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
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
			(2 (curRoom newRoom: 260))
		)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setMotion: MoveTo 160 160 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sShowDress of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= gNarratorX (narrator x?))
				(= gNarratorY (narrator y?))
				(= gNarratorTalkWidth (narrator talkWidth?))
				(narrator x: 160 y: 100 talkWidth: 120)
				(= cycles 2)
			)
			(1
				(messager say: 12 1 0 0 self)
			)
			(2
				(narrator
					x: gNarratorX
					y: gNarratorY
					talkWidth: gNarratorTalkWidth
				)
				(gownInset dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sGetDress of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(lofat setCycle: End self)
			)
			(1
				(ego setMotion: PolyPath 142 138 self)
			)
			(2
				(ego
					view: 271
					setScale: Scaler 100 100 190 29
					loop: 0
					setCycle: CT 3 1 self
				)
			)
			(3
				(lofat loop: 1 cel: 0 setCycle: CT 3 1 self)
			)
			(4 (messager say: 1 5 0 1 self))
			(5
				(lofat setCycle: End self)
				(ego setCycle: End self)
				(smoke init: setCycle: Fwd)
			)
			(6 0)
			(7 (messager say: 1 5 0 2 self))
			(8
				(lofat loop: 2 setCycle: End self)
			)
			(9 (messager say: 1 5 0 3 self))
			(10
				(messager say: 1 5 0 4 self)
				(dress dispose:)
			)
			(11
				(lofat loop: 3 setCycle: End self)
			)
			(12
				(messager say: 1 5 0 5)
				(dress init: posn: 136 94)
				(ego get: -1 32)
				(ego put: 1)
				((ScriptID 21 0) doit: 801)
				((ScriptID 21 1) doit: 770)
				(lofat loop: 4 cel: 0 setCycle: End self)
			)
			(13
				(messager say: 1 5 0 6 self)
			)
			(14
				(messager say: 1 5 0 7 self)
			)
			(15
				(ego
					normalize: 830
					setScale: Scaler 125 0 190 29
					setMotion: MoveTo 138 133 self
				)
			)
			(16
				(ego
					view: 271
					loop: 1
					cel: 0
					setScale: Scaler 100 100 190 29
					setCycle: CT 3 1 self
				)
			)
			(17
				(dress dispose:)
				(ego setCycle: End self)
			)
			(18
				(ego
					normalize: 830
					setScale: Scaler 125 0 190 29
					setMotion: MoveTo 142 138 self
				)
			)
			(19
				(theGame handsOn:)
				(walkHandler addToFront: curRoom)
				(directionHandler addToFront: curRoom)
				(southExitFeature dispose:)
			)
			(20
				(lofat loop: 5 cel: 0 setCycle: End self)
			)
			(21
				(smoke dispose:)
				(lofat loop: 0 setCycle: RandCycle)
				(walkHandler delete: curRoom)
				(directionHandler delete: curRoom)
				(southExitFeature init:)
				(self dispose:)
			)
		)
	)
)

(instance lofat of Actor
	(properties
		x 113
		y 102
		noun 1
		approachX 142
		approachY 138
		view 272
		priority 8
		signal $0010
		cycleSpeed 10
	)
	
	(method (doVerb theVerb theItem &tmp temp0 temp1 temp2)
		(switch theVerb
			(5
				(curRoom setScript: sGetDress)
			)
			(4
				(if local5
					(messager say: 1 4 98)
					(= local5 0)
				else
					(messager say: 1 4 2)
				)
			)
			(2
				(cond 
					(local6 (= temp1 16) (= local6 0))
					((or (ego has: 32) (ego wearingGown?)) (= temp1 67))
					(else (= temp1 13))
				)
				(messager say: 1 2 temp1)
			)
			(6
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
					(775
						(if local11
							(messager say: 1 6 33)
							(= local11 0)
						else
							(messager say: 1 6 91)
						)
					)
					(514
						(if local12
							(messager say: 1 6 20)
							(= local12 0)
						else
							(messager say: 1 6 93)
						)
					)
					(263
						(if local13
							(messager say: 1 6 8)
							(= local13 0)
						else
							(messager say: 1 6 95)
						)
					)
					(266
						(if local14
							(messager say: 1 6 11)
							(= local14 0)
						else
							(messager say: 1 6 96)
						)
					)
					(770
						(if local8
							(messager say: 1 6 28)
							(= local8 0)
						else
							(messager say: 1 6 83)
						)
					)
					(517
						(if local9
							(messager say: 1 6 23)
							(= local9 0)
							((ScriptID 21 0) doit: 270)
							((ScriptID 21 0) doit: 266)
							((ScriptID 21 0) doit: 265)
						else
							(messager say: 1 6 85)
						)
					)
					(519
						(if local10
							(messager say: 1 6 25)
							(= local10 0)
						else
							(messager say: 1 6 86)
						)
					)
					(780
						(if local7
							(messager say: 1 6 38)
							(= local7 0)
						else
							(messager say: 1 6 78)
						)
					)
					(else 
						(if (Message msgGET curRoomNum noun 6 temp1 1)
							(messager say: noun 6 temp1)
						else
							(messager say: noun 6 1)
						)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance smoke of Prop
	(properties
		x 103
		y 100
		view 272
		loop 7
		signal $4000
	)
)

(instance dress2 of View
	(properties
		x 86
		y 94
		view 272
		loop 6
		cel 1
		signal $4000
	)
)

(instance dress3 of View
	(properties
		x 80
		y 94
		view 272
		loop 6
		cel 2
		signal $4000
	)
)

(instance dress4 of View
	(properties
		x 98
		y 94
		view 272
		loop 6
		cel 3
		signal $4000
	)
)

(instance dress5 of View
	(properties
		x 104
		y 94
		view 272
		loop 6
		cel 4
		signal $4000
	)
)

(instance dress6 of View
	(properties
		x 74
		y 94
		view 272
		loop 6
		cel 5
		signal $4000
	)
)

(instance dress of View
	(properties
		x 92
		y 94
		noun 12
		view 272
		loop 6
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(8
				(curRoom setInset: gownInset)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance dClothes of Feature
	(properties
		x 250
		y 115
		noun 3
		nsTop 95
		nsLeft 224
		nsBottom 136
		nsRight 277
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(1
				(if local3
					(messager say: 3 1 98)
					(= local3 0)
				else
					(messager say: 3 1 2)
				)
			)
			(4
				(if local4
					(messager say: 3 4 98)
					(= local4 0)
				else
					(messager say: 3 4 2)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance lamp of Feature
	(properties
		x 38
		y 75
		noun 4
		nsTop 29
		nsBottom 122
		nsRight 76
		sightAngle 40
	)
)

(instance cClothes of Feature
	(properties
		x 214
		y 76
		noun 5
		nsTop 52
		nsLeft 177
		nsBottom 100
		nsRight 251
		sightAngle 40
	)
)

(instance symbols of Feature
	(properties
		y 2
		noun 6
		nsLeft 63
		nsBottom 106
		nsRight 104
	)
)

(instance jar1 of Feature
	(properties
		y 2
		noun 7
		nsTop 38
		nsLeft 185
		nsBottom 49
		nsRight 201
	)
)

(instance jar2 of Feature
	(properties
		y 2
		noun 8
		nsTop 34
		nsLeft 229
		nsBottom 48
		nsRight 242
	)
)

(instance counter1 of Feature
	(properties
		y 2
		noun 9
		nsTop 110
		nsLeft 50
		nsBottom 133
		nsRight 142
	)
)

(instance counter2 of Feature
	(properties
		y 2
		noun 11
		nsTop 111
		nsLeft 161
		nsBottom 132
		nsRight 227
	)
)

(instance gownInset of Inset
	(properties
		view 271
		loop 2
		x 62
		y 76
		priority 15
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: sShowDress)
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsLeft 90
		nsBottom 189
		nsRight 320
		cursor 11
		exitDir 3
		noun 13
	)
)
