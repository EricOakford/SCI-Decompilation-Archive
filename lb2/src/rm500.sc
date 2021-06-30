;;; Sierra Script 1.0 - (do not remove this comment)
(script# 500)
(include game.sh) (include "500.shm")
(use Main)
(use LBRoom)
(use ExitFeature)
(use MuseumRgn)
(use Inset)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm500 0
)

(local
	local0
	local1
	theGLb2DoVerbCode
	local3
)
(instance rm500 of LBRoom
	(properties
		noun N_ROOM
		picture 500
		north 420
		south 510
		vanishingX 0
	)
	
	(method (init)
		(LoadMany RES_VIEW 500 504 505 831)
		(Load RES_PIC 501)
		(Load RES_SOUND 501 19)
		(if (Btst 4)
			(Load RES_SOUND 502)
		)
		(if
			(or
				(> currentAct 4)
				(and (== currentAct 4) (TriggerEvent 12548 1))
			)
			(LoadMany RES_PIC 556 505)
			(LoadMany RES_SOUND 500 3 6 84)
		)
		(ego
			init:
			normalize: (if (== currentAct 5) 426 else 831)
			setScale: Scaler 100 0 190 0
		)
		(self setRegions: 90)
		(theGame handsOff:)
		(switch prevRoomNum
			(north
				(ego x: 103 y: 159 setHeading: 180)
				(cond 
					((== currentAct 5) (self setScript: sLauraDies))
					(
						(and
							(== currentAct 3)
							(TriggerEvent 8512)
							(not (Btst 85))
						)
						(if (== ((ScriptID 90 1) room?) curRoomNum)
							((ScriptID 90 1) moveTo: -2)
						)
						(if (== ((ScriptID 90 2) room?) curRoomNum)
							((ScriptID 90 2) moveTo: -2)
						)
						(self setScript: sMeeting)
					)
					(else (self setScript: sEnterNorth))
				)
			)
			(south
				(ego x: 260 y: 200 setHeading: 315)
				(self setScript: sEnterSouth)
			)
			(else 
				(ego posn: 78 176)
				(theGame handsOn:)
			)
		)
		(super init:)
		(if
			(and
				(== currentAct 3)
				(TriggerEvent 8512)
				(not (Btst 85))
			)
			(= local3 ((ScriptID 90 3) room?))
			((ScriptID 90 3)
				originalView: 818
				view: 818
				moveTo: curRoomNum
				posn: 227 179
				setLoop: 8
				setCel: 1
			)
			((ScriptID 32 0)
				originalView: 814
				room: curRoomNum
				init:
				posn: 206 179
				setLoop: 8
				setCel: 0
			)
			(WrapMusic pause:)
			(theMusic2 number: 350 loop: -1 flags: 1 play:)
		)
		(if
			(or
				(> currentAct 4)
				(and (== currentAct 4) (TriggerEvent 12548 1))
			)
			(yvetteStatue init: stopUpd:)
			(curRoom
				addObstacle:
					(= local1
						((Polygon new:)
							type: 2
							init: 135 183 180 183 191 189 142 189
							yourself:
						)
					)
			)
		)
		(if (not (ego has: 8))
			(keyGlint init: approachVerbs: 4 1 8 setScript: sKeyGlint)
		)
		(bobPortrait init:)
		(rickPortrait init:)
		(leftWall init: setOnMeCheck: 1 4)
		(dennisPortrait init: setOnMeCheck: 1 256)
		(suziPortrait init: setOnMeCheck: 1 8)
		(boschPortrait init: setOnMeCheck: 1 16)
		(erwinPortrait init: setOnMeCheck: 1 32)
		(johnPortrait init: setOnMeCheck: 1 64)
		(bench init: setOnMeCheck: 1 128)
		(sculpture1 init: setOnMeCheck: 1 1024)
		(sculpture2 init: setOnMeCheck: 1 512)
		(ceiling init: setOnMeCheck: 1 2048)
		(borderCeiling init: setOnMeCheck: 1 4096)
		(southExitFeature init:)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego 2) (ego edgeHit: 1) (self newRoom: north))
		)
	)
	
	(method (newRoom n)
		(cond 
			(
			(and (== currentAct 3) (Btst 85) (TriggerEvent 128)) (= n 26))
			((== currentAct 2) (theMusic2 stop:) (theMusic pause: 0))
			(
				(or
					(> currentAct 4)
					(and (== currentAct 4) (TriggerEvent 12548 1))
				)
				(local1 dispose:)
			)
		)
		(super newRoom: n)
	)
)

(instance sLauraDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 152 166 self)
			)
			(1
				(Face ego oRiley)
				(oRiley
					init:
					view: 423
					posn: 99 150
					setScale: 165
					setHeading: 180
					setCycle: StopWalk -1
					setMotion: PolyPath 124 161 self
				)
			)
			(2
				(oRiley
					view: 424
					posn: (+ (oRiley x?) 4) (oRiley y?)
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(ego setMotion: 0 view: 858 setLoop: 4 setCycle: EndLoop self)
				(sFX number: 80 flags: 1 play:)
			)
			(4 (= ticks 60))
			(5
				(= deathReason 0)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance oRiley of Actor
	(properties
		x 227
		y 179
		view 818
		loop 8
		cel 1
	)
)

(instance yvetteStatue of Prop
	(properties
		x 150
		y 141
		noun 8
		approachX 185
		approachY 187
		view 504
		priority 15
		signal $0010
	)
	
	(method (init)
		(if
			(or
				(> currentAct 4)
				(and (== currentAct 4) (TriggerEvent 12548))
			)
			(self cel: 8 stopUpd:)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(29
					(if
						(not
							(cond 
								((> currentAct 4))
								((== currentAct 4) (TriggerEvent 12548))
							)
						)
						(if (MuseumRgn nobodyAround:)
							(curRoom setScript: sSmashPlaster)
						else
							(return 1)
						)
					)
				)
				(1
					(if
					(== (yvetteStatue cel?) (yvetteStatue lastCel:))
						(curRoom setScript: sDeadYvette)
					else
						(messager say: 8 1)
					)
				)
				(8
					(if
					(== (yvetteStatue cel?) (yvetteStatue lastCel:))
						(curRoom setScript: sDeadYvette)
					else
						(messager say: 8 8)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance keyGlint of Prop
	(properties
		x 267
		y 116
		noun 20
		view 500
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(curRoom setScript: sBoschPainting)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance keyGlintInset of Prop
	(properties
		x 198
		y 91
		noun 21
		view 500
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(inBoschPainting setInset: inSkeletonKey)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lauraSwingingRt of Actor
	(properties
		x 185
		y 187
		view 504
		loop 1
		cel 8
	)
)

(instance lauraSwingingLt of Actor
	(properties
		x 136
		y 185
		view 504
		loop 2
		cel 9
	)
)

(instance bobPortrait of Feature
	(properties
		x 50
		y 128
		noun 2
		nsTop 106
		nsLeft 33
		nsBottom 151
		nsRight 67
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (messager say: 24 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rickPortrait of Feature
	(properties
		x 153
		y 113
		noun 4
		nsTop 102
		nsLeft 136
		nsBottom 124
		nsRight 170
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (messager say: 24 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance leftWall of Feature
	(properties
		y 1
		noun 7
		sightAngle 40
	)
)

(instance dennisPortrait of Feature
	(properties
		x 200
		y 1
		noun 3
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (messager say: 24 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance suziPortrait of Feature
	(properties
		x 240
		y 1
		noun 5
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (messager say: 24 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance boschPortrait of Feature
	(properties
		x 270
		y 100
		noun 9
		sightAngle 40
	)
)

(instance skeletonKey of Feature
	(properties
		x 270
		y 100
		noun 17
		sightAngle 40
		onMeCheck $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(curRoom setScript: sBoschPainting)
			)
			(8
				(curRoom setScript: sBoschPainting)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance erwinPortrait of Feature
	(properties
		x 380
		y 100
		noun 6
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (messager say: 24 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance johnPortrait of Feature
	(properties
		x 380
		y 160
		noun 1
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (messager say: 24 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bench of Feature
	(properties
		y 1
		noun 10
		sightAngle 40
	)
)

(instance sculpture1 of Feature
	(properties
		y 1
		noun 11
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (messager say: 25 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sculpture2 of Feature
	(properties
		y 1
		noun 12
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (messager say: 25 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ceiling of Feature
	(properties
		y 1
		noun 13
		sightAngle 40
	)
)

(instance borderCeiling of Feature
	(properties
		y 1
		noun 14
		sightAngle 40
	)
)

(instance inDeadYvette of Inset
	(properties
		picture 505
		priority 12
	)
	
	(method (init)
		(super init: &rest)
		(= theGLb2DoVerbCode doVerbCode)
		(= doVerbCode exitDoVerbCode)
		(InFirstPerson 1)
		(walkHandler addToFront: self)
		(theIconBar disable: 7)
		(if (not (ego has: 26)) (bifocals init:))
		(if (not (ego has: 27)) (redHair init:))
		(feHair init: setOnMeCheck: 1 16384)
		(feScarf init: setOnMeCheck: 1 8192)
		(feFace init: setOnMeCheck: 1 4096)
		(feDress init: setOnMeCheck: 1 2048)
		(feCleavage init: setOnMeCheck: 1 1024)
		(feBody init: setOnMeCheck: 1 512)
		(feLtHand init: setOnMeCheck: 1 256)
		(feRtHand init: setOnMeCheck: 1 128)
		(feTowel init: setOnMeCheck: 1 64)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(theIconBar enable: 7)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(= doVerbCode theGLb2DoVerbCode)
				(self dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance exitDoVerbCode of Code
	(properties)
	
	(method (doit param1 param2)
		(if (== param1 13)
			(= doVerbCode theGLb2DoVerbCode)
			(inDeadYvette dispose:)
		else
			(DftDoVerb param2 param1)
		)
	)
)

(instance bifocals of View
	(properties
		x 102
		y 156
		noun 22
		view 505
		priority 13
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if (not (ego has: 26))
					(inDeadYvette setInset: inBifocals)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inBifocals of Inset
	(properties
		view 505
		loop 1
		x 90
		y 135
		hideTheCast 1
		disposeNotOnMe 1
		noun 37
	)
	
	(method (init)
		(super init: &rest)
		(if (not (ego has: 26))
			(bifocals hide:)
			(viBifocals init:)
		)
	)
)

(instance viBifocals of View
	(properties
		x 103
		y 156
		noun 38
		view 505
		loop 1
		cel 1
		priority 15
		signal $4011
	)
	
	(method (dispose)
		(if (not (ego has: 26)) (bifocals show: stopUpd:))
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(theGame points: 1 162)
				((ScriptID 21 0) doit: 795)
				(ego get: 26)
				(bifocals dispose:)
				(inBifocals dispose:)
				(self dispose:)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance redHair of View
	(properties
		x 161
		y 152
		noun 23
		view 505
		loop 2
		priority 13
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if (not (ego has: 27))
					(inDeadYvette setInset: inRedHair)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inRedHair of Inset
	(properties
		view 505
		loop 3
		x 144
		y 146
		hideTheCast 1
		disposeNotOnMe 1
		noun 35
	)
	
	(method (init)
		(super init: &rest)
		(if (not (ego has: 27))
			(redHair hide:)
			(viRedHair init:)
		)
	)
)

(instance viRedHair of View
	(properties
		x 155
		y 155
		noun 39
		view 505
		loop 3
		cel 1
		priority 15
		signal $4011
	)
	
	(method (dispose)
		(if (not (ego has: 27)) (redHair show: stopUpd:))
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(theGame points: 1 160)
				(ego get: 27)
				((ScriptID 21 0) doit: 796)
				(inRedHair dispose:)
				(redHair dispose:)
				(self dispose:)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance feHair of Feature
	(properties
		y 1
		noun 29
		sightAngle 40
	)
)

(instance feScarf of Feature
	(properties
		y 1
		noun 30
		sightAngle 40
	)
)

(instance feFace of Feature
	(properties
		y 1
		noun 31
		sightAngle 40
	)
)

(instance feDress of Feature
	(properties
		y 1
		noun 32
		sightAngle 40
	)
)

(instance feCleavage of Feature
	(properties
		y 1
		noun 33
		sightAngle 40
	)
)

(instance feBody of Feature
	(properties
		y 1
		noun 34
		sightAngle 40
	)
)

(instance feLtHand of Feature
	(properties
		y 1
		noun 35
		sightAngle 40
	)
)

(instance feRtHand of Feature
	(properties
		y 1
		noun 36
		sightAngle 40
	)
)

(instance feTowel of Feature
	(properties
		y 1
		noun 37
		sightAngle 40
	)
)

(instance inBoschPainting of Inset
	(properties
		picture 501
		hideTheCast 1
		noun 19
	)
	
	(method (init)
		(super init: &rest)
		(InFirstPerson 1)
		(walkHandler addToFront: self)
		(if (not (ego has: 8))
			(keyGlintInset init: setScript: sKeyGlintInset)
		)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (self dispose:))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inSkeletonKey of Inset
	(properties
		view 500
		loop 1
		x 179
		y 93
		disposeNotOnMe 1
		noun 18
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(22
					(if (MuseumRgn nobodyAround:)
						(theGame points: 1 146)
						(ego get: -1 8)
						((ScriptID 21 0) doit: 777)
						(if (!= msgType 2) (sFX number: 501 play:))
						(keyGlintInset dispose:)
						(keyGlint setScript: 0)
						(messager say: 18 22)
						(self dispose:)
						(return 1)
					else
						(return 1)
					)
				)
				(21
					(if (MuseumRgn nobodyAround:)
						(theGame points: 1 146)
						(ego get: -1 8)
						((ScriptID 21 0) doit: 777)
						(if (!= msgType 2) (sFX number: 501 play:))
						(keyGlintInset dispose:)
						(keyGlint setScript: 0)
						(messager say: 18 21)
						(self dispose:)
						(return 1)
					else
						(return 1)
					)
				)
				(25 (messager say: 18 25))
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance sMeeting of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveFwd 15 self)
				(= cycles 5)
			)
			(1
				(messager say: 3 0 0 0 self 1500)
			)
			(2)
			(3
				(ego setMotion: PolyPath 166 179 self)
			)
			(4 (= cycles 3))
			(5
				(messager say: 2 0 0 0 self 1500)
			)
			(6
				((ScriptID 32 0) setHeading: 270 self)
			)
			(7
				(messager say: 1 0 0 0 self 1500)
			)
			(8 (ego setHeading: 180 self))
			(9
				(if (TriggerEvent 128)
					(theMusic2 number: 502 loop: 1 flags: 1 play: self)
				)
				(ego setMotion: MoveFwd 70 self)
			)
			(10
				(if (not (TriggerEvent 128))
					(theMusic2 fade:)
					(WrapMusic pause: 0)
					(= cycles 1)
				)
			)
			(11
				(Bset 85)
				((ScriptID 90 3) moveTo: local3)
				(curRoom newRoom: (curRoom south?))
				(self dispose:)
			)
		)
	)
)

(instance sEnterSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveFwd 20 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== currentAct 2)
					((ScriptID 32 0)
						originalView: 814
						room: curRoomNum
						init:
						posn: 130 180
						setLoop: 8
						setCel: 3
						noun: 1
					)
					(theMusic pause:)
					(theMusic2 number: 19 flags: 1 setLoop: 1 play:)
				)
				(ego setMotion: MoveFwd 15 self)
			)
			(1
				(if (cast contains: (ScriptID 32 0))
					(Face (ScriptID 32 0) ego self)
				else
					(= cycles 1)
				)
			)
			(2 (= cycles 5))
			(3
				(if (> currentAct 2)
					(if
						(not
							(if (and (== currentAct 3) (TriggerEvent 8512))
								(not (Btst 85))
							)
						)
						(theGame handsOn:)
					)
					(self dispose:)
				else
					(messager say: 3 0 84 0 self 1889)
				)
			)
			(4
				(ego setMotion: PolyPath (ego x?) (- (ego y?) 15) self)
			)
			(5
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance sSmashPlaster of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 185 187 self)
			)
			(1
				(ego
					view: 504
					loop: 1
					cel: 0
					setScale: Scaler 100 100 190 0
					setCycle: CycleTo 10 1 self
				)
			)
			(2
				(ego setCycle: EndLoop self)
				(sFX number: 500 play:)
				(yvetteStatue setCycle: EndLoop self)
			)
			(3 0)
			(4
				((ScriptID 22 0) doit: 12548)
				(theGame points: 1 161)
				(ego normalize: 831 setScale: Scaler 100 0 190 0)
				(yvetteStatue stopUpd:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDeadYvette of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable: 7)
				(cast eachElementDo: #hide)
				(ego stopUpd:)
				(WrapMusic pause: 1)
				(if (Btst 68)
					(fooSound number: 6 loop: -1 flags: 1 play:)
					(= cycles 1)
				else
					(Bset 68)
					(curRoom drawPic: 556)
					(wrapMusic init: -1 3 6)
					(= local0 1)
					(sFX number: 84 flags: 1 loop: 1 play:)
					(= ticks 180)
				)
			)
			(1
				(curRoom setInset: inDeadYvette self)
			)
			(2
				(ego startUpd:)
				(cast eachElementDo: #show)
				(curRoom drawPic: 500)
				(= cycles 1)
			)
			(3
				(fooSound fade: 0 12 30 1)
				(if local0 (wrapMusic dispose: 1))
				(WrapMusic pause: 0)
				(InFirstPerson 0)
				(theIconBar enable: 7)
				(self dispose:)
			)
		)
	)
)

(instance sBoschPainting of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom setInset: inBoschPainting self)
			)
			(1
				(if (ego has: 8) (keyGlint dispose:))
				(= cycles 1)
			)
			(2
				(InFirstPerson 0)
				(self dispose:)
			)
		)
	)
)

(instance sKeyGlint of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (= ticks 300))
			(2
				(if (and (> (ego y?) 168) (< (ego y?) 187))
					(keyGlint setCel: 0 setCycle: EndLoop)
					(= ticks (* 60 (Random 3 10)))
				else
					(= cycles 1)
				)
			)
			(3 (self changeState: 2))
		)
	)
)

(instance sKeyGlintInset of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(keyGlintInset setCel: 0 setCycle: EndLoop)
				(= ticks (* 60 (Random 2 5)))
			)
			(1 (self changeState: 0))
		)
	)
)

(instance wrapMusic of WrapMusic
	(properties)
	
	(method (init)
		(= wrapSound fooSound)
		(super init: &rest)
	)
)

(instance fooSound of Sound
	(properties)
)

(instance sFX of Sound
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
		noun 26
	)
)
