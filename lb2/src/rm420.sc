;;; Sierra Script 1.0 - (do not remove this comment)
(script# 420)
(include game.sh)
(use Main)
(use LBRoom)
(use ExitFeature)
(use Inset)
(use PChase)
(use Scaler)
(use PolyPath)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm420 0
)

(local
	local0
	theGLb2DoVerbCode
)
(instance rm420 of LBRoom
	(properties
		picture 420
		north 350
		east 430
		west 500
		vanishingX 0
	)
	
	(method (init)
		(theGame handsOff:)
		(LoadMany RES_VIEW 420 423 424 426 831)
		(if
			(or
				(> currentAct 3)
				(and (== currentAct 3) (TriggerEvent 8512 1))
			)
			(LoadMany RES_PIC 556 429)
			(LoadMany RES_SOUND 3 80 6 84)
		)
		(Load RES_SOUND 19)
		(ego
			init:
			normalize: (if (== currentAct 5) 426 else 831)
			setScale: Scaler 100 0 190 0
		)
		(if (== currentAct 5)
			(theGame handsOn:)
			(theMusic number: 16 flags: 1 setLoop: -1 play:)
			(self setRegions: 94)
			(curRoom obstacles: (List new:))
			((ScriptID 2420 0) doit: (curRoom obstacles?))
			(oriley init: setScale: 165 setCycle: Walk)
		else
			(self setRegions: 90)
		)
		(switch prevRoomNum
			(north
				(self setScript: sEnterNorth)
			)
			(east
				(if
					(and
						(or
							(> currentAct 3)
							(and (== currentAct 3) (TriggerEvent 8512 1))
						)
						(not (Btst 67))
					)
					(self setScript: sLookitDeadErnie 0 1)
				else
					(theGame handsOn:)
				)
				(ego y: 187)
			)
			(west
				(self setScript: sEnterWest)
			)
			(26
				(ego posn: 264 164)
				(ego setHeading: 180)
			)
			(else 
				(ego posn: 185 160)
				(theGame handsOn:)
			)
		)
		(super init:)
		(= local0
			(cond 
				((< howFast 5) 85)
				((< howFast 10) 60)
				((<= howFast 15) 35)
			)
		)
		(if (not (HaveMouse)) (= local0 (* 2 local0)))
		(if (and (== currentAct 5) (!= script sCopyFail))
			(oRileyTimer setTicks: 300 oRileyTimer)
		)
		(if
			(or
				(> currentAct 3)
				(and (== currentAct 3) (TriggerEvent 8512 1))
			)
			(ernie addToPic: approachVerbs: 4 1 8)
		)
		(southExitFeature init:)
		(wall init: setOnMeCheck: 1 512)
		(floor init: setOnMeCheck: 1 1024)
		(tuskSupport init: setOnMeCheck: 1 128)
		(dinoSkeleton init: setOnMeCheck: 1 64)
		(skull init: setOnMeCheck: 1 8)
		(leftPic init: setOnMeCheck: 1 32)
		(rightPic init: setOnMeCheck: 1 16)
		(mastodon init: setOnMeCheck: 1 256 4)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego 2) (self setScript: sExitNorth))
			((IsObjectOnControl ego 4)
				(theGame handsOff:)
				(ego edgeHit: 4)
				(curRoom newRoom: (curRoom west?))
			)
		)
	)
	
	(method (newRoom n)
		(switch currentAct
			(2
				(if (== n 350)
					(if (theMusic2 handle?)
						(theMusic2 stop:)
						(theMusic pause: 0)
					)
					(theMusic fade: 127 5 5 0)
				)
			)
			(3
				(if
					(and
						(== n 430)
						(TriggerEvent -20222 1)
						(not (Btst 72))
					)
					(= n 435)
				)
			)
			(5
				((ScriptID 94 1) setReal: (ScriptID 94 1) local0)
				(DisposeScript 2420)
			)
		)
		(super newRoom: n)
	)
	
	(method (notify)
		(if (== currentAct 5)
			(if
				(and
					(curRoom script?)
					(not (== (curRoom script?) sDie))
				)
				((curRoom script?) next: sDie)
			else
				(curRoom setScript: sDie)
			)
		)
	)
)

(instance oriley of Actor
	(properties
		y 155
		view 423
	)
	
	(method (cue)
		(if (not (curRoom script?))
			(theGame handsOff:)
			(curRoom setScript: sDie)
		)
	)
)

(instance ernie of View
	(properties
		x 175
		y 189
		z 71
		noun 9
		approachX 199
		approachY 180
		view 420
		priority 15
		signal $4010
	)
	
	(method (doVerb theVerb)
		(theGame points: 1 158)
		(switch theVerb
			(1
				(if (== currentAct 5)
					(messager say: 27)
				else
					(theIconBar disable: 7)
					(curRoom setScript: sDeadErnie)
				)
			)
			(8 (self doVerb: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance wall of Feature
	(properties
		y 1
		noun 1
		sightAngle 40
	)
)

(instance floor of Feature
	(properties
		y 1
		noun 2
		sightAngle 40
	)
)

(instance tuskSupport of Feature
	(properties
		y 1
		noun 3
		sightAngle 40
	)
)

(instance dinoSkeleton of Feature
	(properties
		y 1
		noun 4
		sightAngle 40
	)
)

(instance skull of Feature
	(properties
		y 1
		noun 5
		sightAngle 40
	)
)

(instance leftPic of Feature
	(properties
		y 1
		noun 6
		sightAngle 40
	)
)

(instance rightPic of Feature
	(properties
		y 1
		noun 7
		sightAngle 40
	)
)

(instance mastodon of Feature
	(properties
		y 1
		noun 8
		sightAngle 40
	)
)

(instance inDeadErnie of Inset
	(properties
		picture 429
		hideTheCast 1
	)
	
	(method (init)
		(super init: &rest)
		(= theGLb2DoVerbCode doVerbCode)
		(= doVerbCode exitDoVerbCode)
		(InFirstPerson 1)
		(walkHandler addToFront: self)
		(feSuspenders init: setOnMeCheck: 1 16384)
		(feShirt init: setOnMeCheck: 1 8192)
		(feTusks init: setOnMeCheck: 1 4096)
		(feRtArm init: setOnMeCheck: 1 2048)
		(feLtArm init: setOnMeCheck: 1 1024)
		(feSupport init: setOnMeCheck: 1 512)
		(feShoe init: setOnMeCheck: 1 256)
		(fePants init: setOnMeCheck: 1 128)
		(feEye init: setOnMeCheck: 1 64)
		(feSkull init: setOnMeCheck: 1 32)
		(feEar init: setOnMeCheck: 1 16)
		(feNeck init: setOnMeCheck: 1 8)
		(feMouth init: setOnMeCheck: 1 4)
		(feHead init: setOnMeCheck: 1 2)
	)
	
	(method (dispose)
		(= doVerbCode theGLb2DoVerbCode)
		(walkHandler delete: self)
		(theIconBar enable: 7)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13 (inDeadErnie dispose:))
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
			(inDeadErnie dispose:)
		else
			(DftDoVerb param2 param1)
		)
	)
)

(instance feSuspenders of Feature
	(properties
		y 1
		noun 11
		sightAngle 40
	)
)

(instance feShirt of Feature
	(properties
		y 1
		noun 12
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if (not (ego has: 25))
					(inDeadErnie setInset: inWartHairs)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance feTusks of Feature
	(properties
		y 1
		noun 13
		sightAngle 40
	)
)

(instance feRtArm of Feature
	(properties
		y 1
		noun 14
		sightAngle 40
	)
)

(instance feLtArm of Feature
	(properties
		y 1
		noun 15
		sightAngle 40
	)
)

(instance feSupport of Feature
	(properties
		y 1
		noun 16
		sightAngle 40
	)
)

(instance feShoe of Feature
	(properties
		y 1
		noun 17
		sightAngle 40
	)
)

(instance fePants of Feature
	(properties
		y 1
		noun 18
		sightAngle 40
	)
)

(instance feEye of Feature
	(properties
		y 1
		noun 19
		sightAngle 40
	)
)

(instance feSkull of Feature
	(properties
		y 1
		noun 20
		sightAngle 40
	)
)

(instance feEar of Feature
	(properties
		y 1
		noun 21
		sightAngle 40
	)
)

(instance feNeck of Feature
	(properties
		y 1
		noun 22
		sightAngle 40
	)
)

(instance feMouth of Feature
	(properties
		y 1
		noun 23
		sightAngle 40
	)
)

(instance feHead of Feature
	(properties
		y 1
		noun 24
		sightAngle 40
	)
)

(instance inWartHairs of Inset
	(properties
		view 420
		loop 1
		x 126
		y 57
		disposeNotOnMe 1
		noun 10
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(theGame points: 1 159)
					(ego get: 25)
					((ScriptID 21 0) doit: 794)
					(self dispose:)
					(return 1)
				)
				(else  (super doVerb: theVerb))
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
				(ego setHeading: 0 setMotion: MoveFwd 20 self)
			)
			(1
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance sLookitDeadErnie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					(ego setMotion: PolyPath 280 185 self)
				else
					(= cycles 1)
				)
			)
			(1
				(Face ego ernie)
				(= ticks 120)
			)
			(2
				(ernie doVerb: 1)
				(if register (theGame handsOn:))
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
				(ego
					posn: 300 140
					setHeading: 180
					setMotion: MoveFwd 20 self
				)
				(if
				(and (== currentAct 2) (not (TriggerEvent -32480 1)))
					((ScriptID 32 0)
						init:
						view: 814
						room: curRoomNum
						posn: 154 179
						setLoop: 8
						setCel: 6
						noun: 1
					)
					(theMusic pause:)
					(theMusic2 number: 19 flags: 1 setLoop: 1 play:)
				)
			)
			(1
				(cond 
					(
					(and (== currentAct 2) (not (TriggerEvent -32480 1))) (curRoom setScript: sHeimlichShoos))
					(
						(and
							(or
								(> currentAct 3)
								(and (== currentAct 3) (TriggerEvent 8512 1))
							)
							(not (Btst 67))
						)
						(self setScript: sLookitDeadErnie self)
					)
					(else (= cycles 1))
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					posn: 2 150
					setHeading: 135
					setMotion: MoveFwd 20 self
				)
			)
			(1
				(if
					(and
						(or
							(> currentAct 3)
							(and (== currentAct 3) (TriggerEvent 8512 1))
						)
						(not (Btst 67))
					)
					(self setScript: sLookitDeadErnie self)
				else
					(= cycles 1)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDeadErnie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(WrapMusic pause:)
				(if (Btst 67)
					(fooSound number: 6 loop: -1 flags: 1 play:)
					(= cycles 1)
				else
					(Bset 67)
					(cast eachElementDo: #hide)
					(DrawPic 556)
					(addToPics eachElementDo: #dispose release:)
					(wrapMusic init: -1 3 6)
					(sFX number: 84 flags: 1 loop: 1 play:)
					(= ticks 180)
				)
			)
			(1
				(curRoom setInset: inDeadErnie self)
			)
			(2
				(cast eachElementDo: #show)
				(ernie addToPic:)
				(= cycles 2)
			)
			(3
				(fooSound fade: 0 12 30 1)
				(if (sounds contains: (wrapMusic wrapSound?))
					(wrapMusic dispose: 1)
				)
				(WrapMusic pause: 0)
				(if
				(and (== currentAct 3) (not (TriggerEvent 8512)))
					((ScriptID 22 0) doit: 8512)
				)
				(InFirstPerson 0)
				(self dispose:)
			)
		)
	)
)

(instance sHeimlichShoos of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 60)
			)
			(1
				(messager say: 3 0 83 0 self 1889)
			)
			(2
				(ego setMotion: PolyPath 300 142 self)
			)
			(3
				(client setScript: sExitNorth)
			)
		)
	)
)

(instance sDie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(oriley view: 424)
				(Face ego oriley)
				(= cycles 1)
			)
			(1
				(theMusic number: 3 flags: 1 loop: 1 play:)
				(= cycles 4)
			)
			(2
				(oriley cel: 0 setCycle: EndLoop self)
			)
			(3
				(sFX number: 80 flags: 1 loop: 1 play:)
				(ego
					view: 858
					setScale: Scaler 100 100 190 0
					posn: (+ (ego x?) 13) (ego y?)
					setCycle: EndLoop self
				)
			)
			(4 (= seconds 4))
			(5
				(= deathReason 0)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sCopyFail of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(messager say: 26 0 0 0 self)
			)
			(2 (= seconds 5))
			(3
				(= deathReason 3)
				(curRoom newRoom: 99)
			)
		)
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 166
		nsLeft 314
		nsBottom 189
		nsRight 319
		cursor 14
		exitDir 2
		noun 25
	)
)

(instance oRileyTimer of Timer
	(properties)
	
	(method (cue)
		(oriley setMotion: PChase ego 20 oriley)
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
