;;; Sierra Script 1.0 - (do not remove this comment)
(script# 560)
(include game.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use Inset)
(use Scaler)
(use PolyPath)
(use Feature)
(use LoadMany)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm560 0
	westDoor 4
)

(local
	local0
	local1
	local2
)
(instance rm560 of LBRoom
	(properties
		picture 560
		north 666
		west 550
		vanishingX 0
	)
	
	(method (init)
		(LoadMany RES_VIEW 560 561 562 563 564 814 831)
		(LoadMany RES_SOUND 560 561 558 562 564 566 565 44 45 721 567)
		(ego
			init:
			normalize: 831
			ignoreActors:
			setScale: Scaler 130 0 190 0
		)
		(self setRegions: 90)
		(switch prevRoomNum
			(north
				(curRoom setScript: sEnterTunnel)
			)
			(west
				(ego edgeHit: 0 setHeading: 270)
				(if (OneOf global111 1 7)
					(++ global111)
					(westDoor locked: 1)
					(waterPrompt setReal: waterPrompt 5)
				)
				(if
					(or
						(> currentAct 3)
						(and (== currentAct 3) (TriggerEvent -15612 1))
					)
					(Face ego deadWatney)
					(theGame points: 1 171)
				)
			)
			(565 0 (theGame handsOn:))
			(else 
				(ego posn: 20 180)
				(theGame handsOn:)
			)
		)
		(super init:)
		(if (== prevRoomNum north)
			(Palette PALIntensity 0 255 100)
		)
		(theMusic2 number: 565 loop: -1 flags: 1 play:)
		(if
			(or
				(> currentAct 3)
				(and (== currentAct 3) (TriggerEvent -15612 1))
			)
			(deskClock addToPic:)
			(deadWatney addToPic:)
		else
			(deskClock setCel: 4 addToPic:)
			(porcupine addToPic: approachVerbs: 4 1 8)
		)
		(calendar addToPic: approachVerbs: 4 1 8)
		(intercom addToPic: approachVerbs: 4 1 8)
		(phoneList addToPic: approachVerbs: 4 1 8)
		(phone addToPic: approachVerbs: 4 1 8)
		(safePic init: stopUpd: approachVerbs: 4 1 8)
		(westDoor init:)
		(if (!= prevRoomNum north) (secretDoor init: stopUpd:))
		(genericMask init:)
		(pillars init: setOnMeCheck: 1 8)
		(desk init: approachVerbs: 1 setOnMeCheck: 1 2048)
		(drawers init: setOnMeCheck: 1 8192)
		(deskLamp init: setOnMeCheck: 1 4096)
		(chair init: setOnMeCheck: 1 16384)
		(rug init: setOnMeCheck: 1 1024)
		(fireplaceOut init: setOnMeCheck: 1 512)
		(fireplaceIn
			init:
			approachVerbs: 4 1 8
			setOnMeCheck: 1 256
		)
		(bookcase init: setOnMeCheck: 1 16)
		(genericBookshelf init:)
		(book init: approachVerbs: 4 1 8 setOnMeCheck: 1 64)
		(bigPainting init:)
		(skylightBase init: setOnMeCheck: 1 16)
		(skylightSupport init: setOnMeCheck: 1 64)
		(nightSky init: setOnMeCheck: 1 32)
		(buildingBig init: setOnMeCheck: 1 256)
		(buildingPointed init: setOnMeCheck: 1 128)
		(if
			(and
				(== prevRoomNum 565)
				(== currentAct 3)
				(not (TriggerEvent -15612))
			)
			((ScriptID 22 0) doit: -15612)
			((ScriptID 90 1) wandering: 0 room: -1)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				local0
				(not ((ScriptID 561 0) cel?))
				(!= ((ScriptID 32 0) room?) curRoomNum)
			)
			(= local0 0)
			(self setScript: sDumpSafe)
		)
	)
	
	(method (dispose)
		(LoadMany 0 561 562)
		(theMusic2 fade:)
		(super dispose: &rest)
	)
)

(instance westDoor of LbDoor
	(properties
		x 17
		y 148
		noun 36
		sightAngle 40
		approachX 14
		approachY 160
		view 560
		loop 1
		priority 7
		signal $0010
		entranceTo 550
		listenVerb 38
		moveToX 4
		moveToY 145
		enterType 0
		exitType 0
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: 4 38)
	)
	
	(method (listen)
		(switch global111
			(2
				(messager say: 1 38 1 0 0 1560)
				(++ global111)
				(= locked 0)
			)
			(8
				(messager say: 1 38 2 0 0 1560)
				(++ global111)
				(= locked 0)
			)
			(else  (super listen: &rest))
		)
	)
	
	(method (createPoly)
		(super createPoly: 0 142 23 136 31 147 0 153)
	)
)

(instance secretDoor of Prop
	(properties
		x 294
		y 147
		view 560
		loop 2
		cycleSpeed 12
	)
)

(instance safePic of View
	(properties
		x 82
		y 100
		noun 77
		approachX 93
		approachY 149
		view 564
		loop 1
		priority 6
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(Face ego safePic)
				((ScriptID 561 0) init:)
				(= local0 1)
				(self hide:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance deadWatney of View
	(properties
		x 137
		y 114
		view 560
		priority 11
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(theGame handsOff:)
				(Face ego deadWatney)
				(curRoom newRoom: 565)
			)
			(8
				(theGame handsOff:)
				(Face ego deadWatney)
				(curRoom newRoom: 565)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance porcupine of View
	(properties
		x 131
		y 112
		noun 37
		approachX 162
		approachY 170
		view 560
		cel 1
		priority 12
		signal $4010
	)
)

(instance deskClock of View
	(properties
		x 128
		y 114
		noun 41
		approachX 89
		approachY 185
		view 563
		loop 4
		cel 5
		priority 11
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (curRoom setInset: inClock))
			(8 (curRoom setInset: inClock))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance intercom of View
	(properties
		x 132
		y 122
		noun 42
		approachX 93
		approachY 171
		view 563
		loop 4
		cel 3
		priority 12
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(curRoom setInset: (ScriptID 562 0))
			)
			(8
				(curRoom setInset: (ScriptID 562 0))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance phone of View
	(properties
		x 83
		y 118
		noun 58
		approachX 62
		approachY 181
		view 563
		loop 4
		cel 6
		priority 12
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (curRoom setInset: inPhone))
			(8 (curRoom setInset: inPhone))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance phoneList of View
	(properties
		x 109
		y 123
		noun 43
		approachX 77
		approachY 178
		view 563
		loop 4
		priority 12
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(curRoom setInset: inPhonelist)
			)
			(8
				(curRoom setInset: inPhonelist)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance calendar of View
	(properties
		x 120
		y 118
		noun 44
		approachX 99
		approachY 178
		view 563
		loop 4
		cel 1
		priority 12
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(++ local2)
				(curRoom setInset: inCalendar)
			)
			(8
				(++ local2)
				(curRoom setInset: inCalendar)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance genericMask of Feature
	(properties
		y 2
	)
	
	(method (onMe theObjOrX)
		(if (super onMe: theObjOrX)
			(cond 
				(
					(and
						(<= 34 mouseX)
						(<= mouseX 50)
						(<= 63 mouseY)
						(<= mouseY 87)
					)
					(= noun 4)
				)
				(
					(and
						(<= 34 mouseX)
						(<= mouseX 50)
						(<= 94 mouseY)
						(<= mouseY 126)
					)
					(= noun 5)
				)
				(
					(and
						(<= 265 mouseX)
						(<= mouseX 282)
						(<= 71 mouseY)
						(<= mouseY 97)
					)
					(= noun 6)
				)
				(
					(and
						(<= 265 mouseX)
						(<= mouseX 281)
						(<= 101 mouseY)
						(<= mouseY 127)
					)
					(= noun 7)
				)
			)
		)
	)
)

(instance pillars of Feature
	(properties
		y 1
		noun 8
	)
)

(instance desk of Feature
	(properties
		y 117
		noun 9
		approachX 114
		approachY 171
	)
)

(instance drawers of Feature
	(properties
		y 1
		noun 10
	)
)

(instance deskLamp of Feature
	(properties
		y 1
		noun 11
	)
)

(instance chair of Feature
	(properties
		y 1
		noun 12
	)
)

(instance rug of Feature
	(properties
		y 1
		noun 13
	)
)

(instance fireplaceOut of Feature
	(properties
		y 1
		noun 14
	)
)

(instance fireplaceIn of Feature
	(properties
		y 1
		noun 15
		approachX 78
		approachY 145
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (or (ego has: 33) (Btst 35))
					(messager say: 15 1)
				else
					(curRoom setInset: inCharcoal)
				)
			)
			(4
				(if (or (ego has: 33) (Btst 35))
					(messager say: 15 4 10)
				else
					(messager say: 15 4 11)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bookcase of Feature
	(properties
		y 1
		noun 16
	)
)

(instance genericBookshelf of Feature
	(properties
		y 2
		approachX 184
		approachY 169
	)
	
	(method (onMe theObjOrX)
		(if (super onMe: theObjOrX)
			(cond 
				(
					(and
						(<= 185 mouseX)
						(<= mouseX 201)
						(<= 80 mouseY)
						(<= mouseY 92)
					)
					(= noun 17)
				)
				(
					(and
						(<= 203 mouseX)
						(<= mouseX 241)
						(<= 75 mouseY)
						(<= mouseY 89)
					)
					(= noun 18)
				)
				(
					(and
						(<= 184 mouseX)
						(<= mouseX 201)
						(<= 94 mouseY)
						(<= mouseY 107)
					)
					(= noun 19)
				)
				(
					(and
						(<= 203 mouseX)
						(<= mouseX 240)
						(<= 90 mouseY)
						(<= mouseY 106)
					)
					(= noun 20)
				)
				(
					(and
						(<= 203 mouseX)
						(<= mouseX 239)
						(<= 108 mouseY)
						(<= mouseY 120)
					)
					(= noun 21)
				)
			)
		)
	)
)

(instance book of Feature
	(properties
		x 221
		y 115
		heading 90
		noun 22
		nsTop 90
		nsLeft 229
		nsBottom 105
		nsRight 240
		approachX 214
		approachY 155
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sGetBook)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bigPainting of Feature
	(properties
		x 154
		y 99
		noun 23
		nsTop 86
		nsLeft 135
		nsBottom 113
		nsRight 174
	)
)

(instance skylightBase of Feature
	(properties
		x 161
		y 36
		noun 31
		nsTop 1
		nsLeft 50
		nsBottom 72
		nsRight 272
	)
)

(instance skylightSupport of Feature
	(properties
		x 161
		y 36
		noun 32
		nsTop 1
		nsLeft 50
		nsBottom 72
		nsRight 272
	)
)

(instance nightSky of Feature
	(properties
		x 161
		y 36
		noun 33
		nsTop 1
		nsLeft 50
		nsBottom 72
		nsRight 272
	)
)

(instance buildingBig of Feature
	(properties
		x 161
		y 36
		noun 34
		nsTop 1
		nsLeft 50
		nsBottom 72
		nsRight 272
	)
)

(instance buildingPointed of Feature
	(properties
		x 161
		y 36
		noun 35
		nsTop 1
		nsLeft 50
		nsBottom 72
		nsRight 272
	)
)

(instance inBookClosed of Inset
	(properties
		view 562
		x 216
		y 69
		disposeNotOnMe 1
		noun 47
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setInset: inBookOpen)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inBookOpen of Inset
	(properties
		view 562
		loop 1
		x 216
		y 69
		priority 13
		disposeNotOnMe 1
		noun 48
	)
	
	(method (init)
		(super init: &rest)
		(if (not (ego has: 24)) (file init:))
		(theIconBar disable: 7)
	)
	
	(method (dispose)
		(if (IsObject file) (file dispose: delete:))
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (ego has: 24)
					(messager say: noun 1 9)
				else
					(messager say: noun 1 8)
				)
			)
			(8
				(if (ego has: 24)
					(messager say: noun 8 9)
				else
					(messager say: noun 8 8)
				)
			)
			(4
				(if (ego has: 24)
					(messager say: noun 4 9)
				else
					(ego get: 24)
					((ScriptID 21 0) doit: 793)
					((ScriptID 21 0) doit: 272)
					(theGame points: 1 172)
					(file dispose:)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance file of View
	(properties
		x 218
		y 71
		view 562
		loop 1
		cel 1
		priority 14
		signal $0010
	)
	
	(method (doVerb theVerb)
		(inBookOpen doVerb: theVerb &rest)
	)
)

(instance inCharcoal of Inset
	(properties
		view 563
		cel 3
		x 21
		y 124
		disposeNotOnMe 1
		noun 45
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sGetCoal)
				(self dispose:)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inClock of Inset
	(properties
		view 563
		x 101
		y 105
		disposeNotOnMe 1
		noun 52
	)
	
	(method (init)
		(if (Btst 3) (self cel: 1 noun: 53))
		(super init: &rest)
	)
)

(instance inPhone of Inset
	(properties
		view 563
		loop 3
		cel 3
		x 79
		y 99
		disposeNotOnMe 1
		noun 59
	)
)

(instance inPhonelist of Inset
	(properties
		view 563
		loop 2
		x 87
		y 93
		disposeNotOnMe 1
		noun 49
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setInset: inPhoneOpen)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inPhoneOpen of Inset
	(properties
		view 563
		loop 2
		cel 1
		x 87
		y 93
		disposeNotOnMe 1
		noun 50
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 50 1))
			(8
				(if local2
					(messager say: noun theVerb 6)
				else
					(messager say: noun theVerb 7)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inCalendar of Inset
	(properties
		view 563
		cel 4
		x 108
		y 99
		disposeNotOnMe 1
		noun 51
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(theGame points: 1 173)
				(messager say: 51 1)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sGetCoal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					normalize: 831
					setScale: Scaler 130 0 190 0
					setMotion: PolyPath 79 143 self
				)
			)
			(1
				(ego
					view: 561
					setLoop: 1
					cel: 0
					posn: 78 141
					setScale: Scaler 100 100 190 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(2
				(sFX number: 564 flags: 1 loop: 1 play:)
				(theGame points: 1 175)
				(= cycles 3)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(ego
					normalize: 831
					setScale: Scaler 130 0 190 0
					loop: 1
					posn: 79 143
				)
				(= cycles 1)
			)
			(5
				(ego get: 33)
				((ScriptID 21 0) doit: 802)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetBook of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					normalize: 831
					setScale: Scaler 130 0 190 0
					setMotion: PolyPath 214 155 self
				)
			)
			(1
				(ego setHeading: 90)
				(= cycles 1)
			)
			(2
				(ego
					view: 561
					setScale: 155
					setLoop: 0
					cel: 0
					posn: 218 153
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(3 (= ticks 30))
			(4
				(sFX number: 566 flags: 1 loop: 1 play:)
				(ego setCycle: BegLoop self)
			)
			(5 (= ticks 60))
			(6
				(curRoom setInset: inBookClosed)
				(ego
					normalize: 831
					setScale: Scaler 130 0 190 0
					loop: 0
					posn: 214 155
				)
				(= cycles 1)
			)
			(7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDumpSafe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(==
						((ScriptID 561 1) cel?)
						((ScriptID 561 1) lastCel:)
					)
					(theGame handsOff:)
					((ScriptID 561 1) setPri: 5 setCycle: BegLoop self)
					(= local1 1)
				else
					(= cycles 1)
				)
			)
			(1
				(if local1
					(sFX number: 561 flags: 1 loop: 1 play:)
					(= local1 0)
					(= ticks 60)
				else
					(= cycles 1)
				)
			)
			(2
				(if (!= ((ScriptID 561 0) cel?) 0)
					((ScriptID 561 0) setCycle: BegLoop self)
					(sFX number: 45 flags: 1 loop: 1 play:)
				else
					(= cycles 1)
				)
			)
			(3
				(safePic show:)
				((ScriptID 561 0) dispose:)
				(= cycles 2)
			)
			(4
				(DisposeScript 561)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterTunnel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(secretDoor init:)
				(= cycles 2)
			)
			(1 (= seconds 3))
			(2
				(ego x: 306 y: 147 setPri: 5 setHeading: 180)
				(sFX number: 721 flags: 1 loop: 1 play:)
				(secretDoor setCycle: EndLoop self)
			)
			(3
				(sFX stop:)
				(ego setPri: -1 setMotion: MoveTo 303 177 self)
			)
			(4
				(sFX number: 721 flags: 1 loop: 1 play:)
				(secretDoor setCycle: BegLoop self)
			)
			(5
				(secretDoor stopUpd:)
				(sFX stop:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance waterPrompt of Timer
	(properties)
	
	(method (cue)
		(messager say: 79)
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)
