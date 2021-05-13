;;; Sierra Script 1.0 - (do not remove this comment)
(script# 510)
(include sci.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use ExitFeature)
(use Scaler)
(use PolyPath)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use StopWalk)
(use Timer)
(use Sound)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm510 0
	eastDoor 2
)

(local
	local0
	local1
)
(instance rm510 of LBRoom
	(properties
		noun 10
		picture 510
		north 530
		east 550
		south 520
		west 500
		vanishingX 0
	)
	
	(method (init)
		(LoadMany 128 510 511 831)
		(if (and (== currentAct 4) (== global111 11))
			(LoadMany 128 820 812 817)
			(Load rsSOUND 332)
		)
		(LoadMany 132 531 721)
		(ego init: normalize: 831 setScale: Scaler 120 0 190 0)
		(self setRegions: 90)
		(switch prevRoomNum
			(521
				(theGame handsOff:)
				(ego posn: 86 175)
				(WrapMusic pause: 0)
				(self setScript: sOlympiaEnters)
			)
			(north
				(ego x: 278 y: 147)
				(self setScript: sEnterNorth)
			)
			(east
				(ego edgeHit: 0 setHeading: 180)
				(if (and (== currentAct 4) (== global111 11))
					(WrapMusic pause: 1)
					(theMusic2 number: 332 loop: -1 flags: 1 play:)
					(olympia
						init:
						setCycle: StopWalk -1
						setScale: Scaler 140 20 190 0
					)
					(steve
						init:
						setCycle: StopWalk -1
						setScale: Scaler 140 20 190 0
					)
				else
					(WrapMusic pause: 0)
				)
				(self cue:)
			)
			(west
				(self setScript: sEnterWest)
			)
			(26
				(WrapMusic init: 1 90 91 92 93)
				(= global111 11)
				(self setScript: sEnterWest)
			)
			(south
				(self cue:)
				(WrapMusic pause: 0)
				(if (== ((Inv at: 14) owner?) 0)
					(self setScript: sFollowOlympia)
					((Inv at: 14) owner: 630)
				)
			)
			(else 
				(ego posn: 86 175)
				(WrapMusic pause: 0)
				(self cue:)
				(theGame handsOn:)
			)
		)
		(super init:)
		(if (Btst 31)
			(rodinDudeHead
				init:
				cel: (rodinDudeHead lastCel:)
				stopUpd:
			)
		else
			(rodinDudeHead init: stopUpd:)
		)
		(eastDoor init:)
		(statue1 init: setOnMeCheck: 1 32)
		(statue2 init: setOnMeCheck: 1 64)
		(statue3 init: setOnMeCheck: 1 128)
		(statue4 init: setOnMeCheck: 1 256)
		(arch init: setOnMeCheck: 1 16)
		(rodinSeam init: setOnMeCheck: 1 4096)
		(rodinBody init: setOnMeCheck: 1 16384)
		(transom init: setOnMeCheck: 1 512)
		(wall init: setOnMeCheck: 1 1024)
		(southExitFeature init:)
		(if (OneOf global111 0 6 10 14) (eastDoor locked: 1))
		(if
			(and
				(== currentAct 4)
				(not (== prevRoomNum 521))
				(not (Btst 62))
				(TriggerEvent 16648 1)
				(not (Btst 92))
			)
			(Bset 62)
			(self setScript: sMeanwhile)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego 2) (self setScript: sExitWest))
			((IsObjectOnControl ego 4) (self setScript: sExitNorth))
			((IsObjectOnControl ego 2048) (curRoom setScript: sExitSouth))
		)
	)
	
	(method (cue)
		(cond 
			((== global111 0)
				(if (== ((ScriptID 90 2) room?) curRoomNum)
					(theGame handsOff:)
					((ScriptID 90 2) goTo: 550 self)
				else
					((ScriptID 90 2) moveTo: -2)
					(waterPrompt setReal: waterPrompt 5)
				)
			)
			((OneOf global111 4 10 12) (waterPrompt setReal: waterPrompt 5))
		)
	)
	
	(method (newRoom n)
		(if (timers contains: waterPrompt)
			(waterPrompt dispose: delete:)
		)
		(cond 
			((OneOf n 500 530)
				(switch global111
					(3 (++ global111))
					(5
						(= global111 (+ global111 2))
					)
					(6 (++ global111))
					(13 (++ global111))
				)
			)
			((== n 520) (WrapMusic pause: 1))
		)
		(if (OneOf global111 1 11 15)
			((ScriptID 90 6) moveTo: -2)
		)
		(if
			(and
				(== global111 7)
				(== ((ScriptID 90 6) room?) -2)
				(not (TriggerEvent -15612))
			)
			((ScriptID 90 6) moveTo: 510 goTo: 430)
		)
		(if local1 (Bclr 31))
		(if (!= n east) (Bclr 97))
		(super newRoom: n)
	)
)

(instance olympia of Actor
	(properties
		x 256
		y 184
		view 820
		cel 5
		priority 13
		signal $4010
	)
)

(instance steve of Actor
	(properties
		x 285
		y 184
		view 812
		loop 1
		cel 4
		priority 13
		signal $0010
	)
)

(instance yvette of Actor
	(properties
		x 309
		y 159
		view 817
		loop 1
		priority 13
		signal $0010
	)
)

(instance rodinDudeHead of Prop
	(properties
		x 166
		y 127
		noun 6
		view 511
		loop 1
		priority 14
		signal $0010
		cycleSpeed 12
	)
	
	(method (init)
		(super init:)
		(self stopUpd:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (Btst 31)
					(Bclr 31)
				else
					(theGame points: 1 142)
					(Bset 31)
				)
				(rodinDudeHead setScript: sRodinFlipSwitch)
			)
			(8 (messager say: 7 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance eastDoor of LbDoor
	(properties
		x 319
		y 95
		noun 2
		modNum 1510
		sightAngle 40
		approachX 309
		approachY 163
		view 510
		priority 8
		signal $0010
		entranceTo 550
		listenVerb 38
		moveToX 310
		moveToY 157
		enterType 0
		exitType 0
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: 4 38)
	)
	
	(method (cue)
		(super cue:)
		(if (== doorState 0)
			(if (and (== currentAct 4) (== global111 11))
				(self setScript: sSteveYvetteMeeting)
			)
			(if
				(and
					(== currentAct 4)
					(not (== prevRoomNum 521))
					(not (Btst 62))
					(TriggerEvent 16648 1)
					(not (Btst 92))
				)
				(Bset 62)
				(curRoom newRoom: 521)
			)
		)
	)
	
	(method (listen)
		(if (timers contains: waterPrompt)
			(waterPrompt dispose: delete:)
		)
		(switch global111
			(0
				(messager say: 2 38 2 0 0 1510)
				(++ global111)
			)
			(4
				(messager say: 2 38 5 0 0 1510)
				(++ global111)
			)
			(10
				(messager say: 2 38 6 0 0 1510)
				(++ global111)
			)
			(12
				(curRoom setScript: sListenToYvetteAndSteve)
				(++ global111)
			)
			(else  (super listen:))
		)
	)
	
	(method (createPoly)
		(super createPoly: 297 152 319 156 319 164 297 160)
	)
)

(instance statue1 of Feature
	(properties
		y 1
		noun 1
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(messager say: 14 4)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance statue2 of Feature
	(properties
		x 100
		y 1
		noun 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(messager say: 14 4)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance statue3 of Feature
	(properties
		x 200
		y 1
		noun 3
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(messager say: 14 4)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance statue4 of Feature
	(properties
		x 300
		y 1
		noun 4
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(messager say: 14 4)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance arch of Feature
	(properties
		y 1
		noun 5
	)
)

(instance rodinSeam of Feature
	(properties
		x 160
		y 161
		noun 7
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (Btst 31)
					(Bclr 31)
				else
					(theGame points: 1 142)
					(Bset 31)
				)
				(rodinDudeHead setScript: sRodinFlipSwitch)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rodinBody of Feature
	(properties
		x 160
		y 159
		noun 8
		sightAngle 40
	)
)

(instance transom of Feature
	(properties
		x 360
		y 84
		noun 9
		sightAngle 40
	)
)

(instance wall of Feature
	(properties
		y 1
		noun 11
	)
)

(instance sExitSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 180 setMotion: MoveFwd 80 self)
			)
			(1
				(curRoom newRoom: (curRoom south?))
			)
		)
	)
)

(instance sExitWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 270 setMotion: MoveFwd 20 self)
			)
			(1
				(curRoom newRoom: (curRoom west?))
			)
		)
	)
)

(instance sEnterWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					posn: 18 150
					setHeading: 90
					setMotion: MoveFwd 30 self
				)
			)
			(1
				(curRoom cue:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveFwd 25 self)
			)
			(1
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance sEnterNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 225 setMotion: PolyPath 247 155 self)
			)
			(1
				(curRoom cue:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 45 setMotion: MoveFwd 20 self)
			)
			(1
				(curRoom newRoom: (curRoom east?))
			)
		)
	)
)

(instance sRodinFlipSwitch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 186 185 self)
			)
			(1 (ego setHeading: 270 self))
			(2
				(ego
					view: 511
					setLoop: 0
					cel: 0
					posn: 174 184
					cycleSpeed: 12
					setScale: Scaler 100 100 190 0
					setCycle: End self
				)
			)
			(3
				(sFX number: 531 flags: 1 play:)
				(if (Btst 31)
					(rodinDudeHead setCycle: End self)
				else
					(rodinDudeHead setCycle: Beg self)
				)
				(ego
					normalize: 831
					loop: 1
					posn: 186 185
					setScale: Scaler 120 0 190 0
				)
			)
			(4
				(rodinDudeHead stopUpd:)
				(sFX number: 721 flags: 1 setLoop: 1 play:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sSteveYvetteMeeting of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 60)
			)
			(1
				(ego setMotion: PolyPath 273 172 self)
			)
			(2 (= ticks 60))
			(3
				(Face ego steve)
				(= ticks 60)
			)
			(4
				(messager say: 1 0 1 1 self 1510)
			)
			(5
				(Face steve ego)
				(= ticks 60)
			)
			(6
				(messager say: 1 0 1 2 self 1510)
			)
			(7
				(Face olympia ego)
				(= ticks 60)
			)
			(8
				(Face olympia steve)
				(= ticks 60)
			)
			(9
				(messager say: 1 0 1 3 self 1510)
			)
			(10 (= ticks 60))
			(11
				(olympia
					setCycle: Walk
					setMotion: PolyPath (olympia x?) 250 self
				)
			)
			(12
				(olympia dispose:)
				(ego setMotion: PolyPath 256 184 self)
			)
			(13
				(Face ego steve)
				(Face steve ego)
				(= ticks 60)
			)
			(14
				(messager say: 1 0 3 0 0 1510)
				(= ticks 60)
			)
			(15 (= ticks 60))
			(16
				(eastDoor setCycle: End self)
				(doorSound number: 46 play:)
				(altPolyList delete: (eastDoor doorPoly?))
			)
			(17 (= ticks 60))
			(18
				(Face yvette steve)
				(= ticks 60)
			)
			(19
				(yvette
					init:
					setScale: Scaler 140 20 190 0
					setCycle: Walk
					setMotion: MoveTo 298 174 self
				)
			)
			(20
				(yvette setCycle: StopWalk -1)
				(= cycles 1)
			)
			(21
				(Face yvette steve)
				(= cycles 10)
			)
			(22
				(Face yvette ego)
				(= cycles 10)
			)
			(23
				(Face yvette steve)
				(= cycles 10)
			)
			(24
				(messager say: 1 0 1 17 self 1510)
			)
			(25 (Face steve yvette self))
			(26 (= cycles 1))
			(27
				(messager say: 1 0 1 18 self 1510)
			)
			(28
				(messager say: 1 0 1 19 self 1510)
			)
			(29
				(yvette setCycle: Walk setMotion: MoveTo 309 159 self)
			)
			(30
				(yvette hide: dispose:)
				(= cycles 1)
			)
			(31
				(Face steve eastDoor)
				(Face ego eastDoor)
				(= ticks 60)
			)
			(32
				(Face steve ego)
				(Face ego steve)
				(= ticks 60)
			)
			(33
				(messager say: 1 0 1 20 self 1510)
			)
			(34
				(messager say: 1 0 1 21 self 1510)
			)
			(35
				(messager say: 1 0 1 22 self 1510)
			)
			(36
				(steve setMotion: MoveTo 309 159 self)
			)
			(37
				(Face ego eastDoor)
				(steve hide:)
				(= cycles 1)
			)
			(38
				(eastDoor setCycle: Beg self)
				(doorSound number: 47 play:)
				(altPolyList add: (eastDoor doorPoly?))
				(= ticks 60)
			)
			(39
				(theMusic2 fade: 0 12 5 1)
				(= cycles 1)
			)
			(40
				(messager say: 1 0 1 23 self 1510)
			)
			(41
				(messager say: 1 0 1 24 self 1510)
			)
			(42
				((ScriptID 22 0) doit: 8961 self)
			)
			(43
				(++ global111)
				(waterPrompt setReal: waterPrompt 3)
				(WrapMusic pause: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOlympiaEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1
				(olympia
					posn: 100 210
					setCycle: Walk
					setScale: Scaler 140 20 190 0
					setPri: -1
					setMotion: PolyPath (- (ego x?) 20) (ego y?) self
					init:
				)
				(= seconds 2)
			)
			(2 (Face ego olympia))
			(3 (Face ego olympia self))
			(4 (Face olympia ego self))
			(5 (= cycles 1))
			(6
				(olympia setCycle: StopWalk -1)
				(= cycles 1)
			)
			(7 (= cycles 1))
			(8
				(messager say: 1 0 24 0 self 1892)
			)
			(9
				(olympia setMotion: PolyPath 13 146 self)
			)
			(10
				(theGame handsOn:)
				(olympia dispose:)
				(Bclr 62)
				(self dispose:)
			)
		)
	)
)

(instance sFollowOlympia of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 31)
				(= local1 1)
				(ego posn: (ego x?) (+ (ego y?) 20))
				(rodinDudeHead cel: (rodinDudeHead lastCel:))
				(olympia
					posn: 196 161
					setScale: Scaler 140 20 190 0
					init:
				)
				(= cycles 15)
			)
			(1
				(olympia
					setPri: -1
					setCycle: StopWalk -1
					setMotion: PolyPath 271 142 self
				)
			)
			(2
				(olympia dispose:)
				(ego init: setMotion: PolyPath 86 175 self)
			)
			(3
				(sFX number: 721 flags: 1 setLoop: 1 play:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)

(instance doorSound of Sound
	(properties
		flags $0001
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsBottom 189
		nsRight 319
		cursor 11
		exitDir 3
		noun 15
	)
)

(instance sMeanwhile of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch prevRoomNum
					((curRoom north?)
						(ego x: 278 y: 147)
						(self setScript: sEnterNorth self)
					)
					((curRoom west?)
						(self setScript: sEnterWest self)
					)
					(else  (= cycles 1))
				)
			)
			(1 (curRoom newRoom: 521))
		)
	)
)

(instance sListenToYvetteAndSteve of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 2 38 4 0 self 1510)
			)
			(1
				(Bset 75)
				(eastDoor doVerb: 4)
			)
		)
	)
)

(instance waterPrompt of Timer
	(properties)
	
	(method (cue)
		(messager say: 16)
	)
)
