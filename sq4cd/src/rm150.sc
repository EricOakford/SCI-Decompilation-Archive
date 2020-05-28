;;; Sierra Script 1.0 - (do not remove this comment)
(script# 150)
(include game.sh)
(use Main)
(use Intrface)
(use brain)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use Osc)
(use PolyPath)
(use MoveFwd)
(use LoadMany)
(use StopWalk)
(use DPath)
(use Reverse)
(use Sound)
(use Motion)
(use Invent)
(use System)

(public
	rm150 0
	RogerJr 1
	diskDrive 2
)

(local
	local0
	local1
	lOrbZapTimer =  10
	rOrbZapTimer =  15
	local4
	[local5 8] = [161 189 186 154 120 91 87 118]
	[local13 45] = [119 107 80 67 61 79 107 126 2 2 122 99 2 3 122 99 2 2 122 99 2 3 122 99 2 2 122 99 2 3 122 99 2 2 122 99 2 3 122 99 2 2 122 99 -32768]
)
(procedure (OrbFires param1 param2 param3 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6)
	(laserSound play:)
	(= temp0 (param1 x?))
	(= temp1 (- (param1 y?) 10))
	(= temp2 (- (Min temp1 param3) 1))
	(= temp3 (+ (Max temp1 param3) 1))
	(= temp4 (- (Min temp0 param2) 1))
	(= temp5 (+ (Max temp0 param2) 1))
	(= temp6 (Graph GSaveBits temp2 temp4 temp3 temp5 1))
	(Graph
		GDrawLine
		temp1
		temp0
		param3
		param2
		myTextColor13
		-1
		-1
	)
	(Graph GReAnimate temp2 temp4 temp3 temp5 1)
	(Wait 1)
	(Wait 5)
	(Graph GRestoreBits temp6)
	(Graph GReAnimate temp2 temp4 temp3 temp5 1)
	(if
	(or (!= param2 (ego x?)) (!= param3 (- (ego y?) 15)))
		(blast x: param2 y: param3 init: setCycle: EndLoop blast)
	)
)

(instance rm150 of SQRoom
	(properties
		picture 150
		south 505
		lookStr 1
	)
	
	(method (init)
		(LoadMany VIEW 522)
		(LoadMany VIEW 150 550)
		(Load SCRIPT 151)
		(if (not (brain formatting?))
			(LoadMany SOUND 118 120 119 121)
		else
			(LoadMany SOUND 152)
		)
		(if (< (ego x?) 160)
			(ego x: 170 y: 250 init:)
		else
			(ego x: 325 y: 235 init:)
		)
		(self setRegions: BRAIN)
		(if (regions contains: BRAIN) (SetDebug))
		(super init:)
		(brain
			makePolygon: 194 189 199 181 210 176 221 174 233 174 249 178 272 189
			makePolygon: 116 189 138 159 155 159 151 189
			makePolygon: 319 165 220 145 216 140 231 136 319 153
		)
		(diskDrive init: stopUpd:)
		(ladder init:)
		(overhang init:)
		(pedestal init:)
		(lOrb init:)
		(rOrb init:)
		(if (brain formatting?)
			(lOrb addToPic:)
			(rOrb addToPic:)
		)
		(music fade:)
		(self setScript: rescueScript)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego cGREEN) (self setScript: fallScript))
			((IsObjectOnControl ego cBLUE) (self setScript: climbScript))
		)
	)
	
	(method (dispose)
		(DisposeScript 151)
		(DisposeScript 152)
		(super dispose:)
	)
	
	(method (notify param1)
		(switch param1
			(-1 (= local4 1))
			(else  (= local1 param1))
		)
	)
)

(instance rescueScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (cast contains: (ScriptID BRAIN 1))
					(((ScriptID BRAIN 1) body?) dispose:)
					((ScriptID BRAIN 1) dispose:)
				)
				(if (cast contains: (ScriptID BRAIN 2))
					(((ScriptID BRAIN 2) body?) dispose:)
					((ScriptID BRAIN 2) dispose:)
				)
				(globalSound number: 848 vol: 127 loop: 1 play:)
				(RogerJr init: setCycle: Forward)
				(ego setPri: -1 setLoop: -1)
				(if (== (ego x?) 170)
					(ego setMotion: PolyPath 170 170 self)
				else
					(ego setMotion: PolyPath 260 170 self)
				)
			)
			(1
				(if (not (brain formatting?))
					(lOrb setScript: laserScript)
					(self dispose:)
				else
					(SolvePuzzle fReachVohaulsChamber 5)
					(globalSound stop:)
					(RogerJr
						posn: 133 94
						setLoop: 1
						setCel: 0
						cycleSpeed: 12
						setCycle: EndLoop
					)
					(bridge init: setMotion: MoveTo 180 153 self)
					(bridgeSound play:)
				)
			)
			(2
				(bridgeSound stop:)
				(brain
					makePolygon: 123 159 123 127 166 127 180 150 155 159
					makePolygon: 218 145 195 148 175 124 202 109 228 135 216 140
				)
				(ego setMotion: PolyPath 156 118 self)
			)
			(3
				(bridge setMotion: MoveTo 193 176 self)
				(bridgeSound play:)
			)
			(4
				(bridge dispose:)
				(bridgeSound stop:)
				(RogerJr setLoop: 2 setCel: 0 setCycle: EndLoop self)
				(diskDrive setMotion: MoveTo 120 98 diskDrive)
			)
			(5)
			(6
				(RogerJr
					view: 550
					setLoop: 0
					setCel: 0
					setPri: 9
					posn: 125 94
					setCycle: CycleTo 4 1 self
				)
			)
			(7 (= seconds 2))
			(8
				(tVOHAULJR modNum: 558 say: 1 self)
			)
			(9
				(tVOHAULJR modNum: 558 say: 2 self)
			)
			(10
				(RogerJr setCycle: EndLoop self)
			)
			(11
				(disk
					init:
					setCycle: Forward
					setMotion: DPath 88 61 53 65 20 98 -5 133 self
				)
				(RogHead
					posn: (+ (RogerJr x?) 8) (- (RogerJr y?) 31)
					init:
					setCycle: Forward
				)
			)
			(12
				(disk dispose:)
				(RogerJr
					view: 525
					setLoop: 0
					setPri: -1
					cycleSpeed: 6
					setCycle: Reverse
					setMotion: MoveTo [local5 4] [local13 (RogHead dispose:)] self
				)
				(diskDrive setMotion: MoveTo 120 110 diskDrive)
			)
			(13)
			(14
				(tVOHAULJR modNum: 558 say: 3 self)
			)
			(15
				(tVOHAULJR modNum: 558 say: 4 self)
			)
			(16
				(RogerJr view: 527 setCycle: Walk)
				(ego view: 526 setCycle: Walk)
				(self setScript: (ScriptID 151 0) self)
			)
			(17
				(self setScript: (ScriptID 151 1) self)
			)
			(18 (self dispose:))
		)
	)
)

(instance laserSound of Sound
	(properties
		number 106
	)
)

(instance laserScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOn) (= seconds 5))
			(1
				(OrbFires lOrb (- (ego x?) 25) 159)
				(= cycles 20)
			)
			(2
				(OrbFires rOrb (+ (ego x?) 25) 143)
				(= cycles 20)
			)
			(3
				(OrbFires lOrb (- (ego x?) 15) 161)
				(= cycles 20)
			)
			(4
				(OrbFires rOrb (+ (ego x?) 15) 150)
				(= cycles 20)
			)
			(5
				(OrbFires lOrb (- (ego x?) 5) 160)
				(= cycles 20)
			)
			(6
				(OrbFires rOrb (+ (ego x?) 5) 158)
				(= cycles 20)
			)
			(7
				(OrbFires lOrb (ego x?) (- (ego y?) 15))
				(= cycles 1)
			)
			(8
				(ego view: 511 setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(9 (EgoDead iconLASER deathORBS))
		)
	)
)

(instance climbScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if
				(OneOf ((Inventory at: iDiskette) owner?) ego curRoomNum diskDrive)
					(narrator say: 2)
					(= register 1)
					(ego setMotion: MoveTo 100 115 self)
				else
					(ego setMotion: MoveTo 84 115 self)
				)
			)
			(1
				(if register
					(HandsOn)
					(self dispose:)
				else
					(ego setHeading: 270 self)
				)
			)
			(2
				(ego
					view: 551
					setLoop: 2
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(3
				(ego
					posn: 77 144
					setLoop: 0
					setCel: 0
					setCycle: Forward
					setMotion: MoveTo 98 245 self
				)
			)
			(4 (ego get: iDiskette) (= seconds 5))
			(5
				(ego setCycle: Reverse setMotion: MoveTo 77 144 self)
			)
			(6
				(ego posn: 84 115 setLoop: 2 setCel: 2 setCycle: BegLoop self)
			)
			(7
				(ego
					view: 3
					setLoop: -1
					cycleSpeed: 6
					setCycle: StopWalk 510
					setMotion: MoveTo 100 115 self
				)
			)
			(8
				(HandsOn)
				(SolvePuzzle fGetDiskette 5)
				(self dispose:)
			)
		)
	)
)

(instance fallScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveFwd 12 self)
			)
			(1
				(ego setHeading: 180 looper: 0)
				(= cycles 8)
			)
			(2
				(narrator say: 3 self)
				(cond 
					((< (ego y?) 95) (= temp0 1))
					((< (ego x?) 95) (= temp0 12))
					(else (= temp0 3))
				)
				(ego
					setStep: (ego xStep?) 19
					setPri: temp0
					setMotion: MoveTo (ego x?) 300 self
				)
			)
			(3 0)
			(4 (EgoDead))
		)
	)
)

(instance jrFreeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(brain formatting: 0)
				(RogerJr
					posn: 133 91
					setLoop: 1
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(1
				(Face ego RogerJr self)
				(= seconds 3)
			)
			(2 0)
			(3
				(RogerJr setLoop: 2 setCel: 0 setCycle: EndLoop self)
				(music stop:)
			)
			(4
				(if local4
					(EgoDead iconDEAD deathVOHAULWINS)
				else
					(music number: 870 vol: 127 loop: -1 flags: 1 play:)
					(= seconds 3)
				)
			)
			(5
				(tROGERJR modNum: 557 say: 1 self)
			)
			(6
				(music fade: 115 25 1 0)
				(= seconds 2)
			)
			(7 (curRoom newRoom: 556))
		)
	)
)

(instance driveScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 111 99 self)
			)
			(1
				((ScriptID 152 0) init:)
				(self dispose:)
			)
		)
	)
)

(instance RogerJr of Sq4Actor
	(properties
		x 127
		y 39
		view 522
		priority 4
		signal (| ignrAct fixPriOn)
		lookStr 4
	)
	
	(method (init)
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (and local1 (MousedOn self event))
			((ScriptID 151 0)
				handleEvent: (event type: 64 message: 1)
			)
		else
			(super handleEvent: event)
		)
	)
)

(instance RogHead of Sq4Prop
	(properties
		view 550
		loop 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance driveSound of Sound
	(properties)
)

(instance diskDrive of Sq4Actor
	(properties
		x 120
		y 110
		view 522
		loop 3
		priority 5
		signal (| ignrAct fixedLoop fixPriOn)
		illegalBits $0000
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DISKETTE
				(if (cast contains: (ScriptID 152 0))
					((ScriptID 152 0) doVerb: theVerb theItem) ;&rest (would not compile)
				else
					((inventory at: iDiskette) owner: self)
					(ego put: iDiskette curRoomNum)
					(SolvePuzzle fInsertDiskette 5)
					(self setScript: driveScript)
				)
			)
			(V_LOOK (narrator say: 6))
			((OneOf theVerb
				V_BUCKAZOID V_ROPE V_BOMB V_RABBIT V_BATTERY V_JAR V_GUM V_TANK
				V_HINTBOOK V_PEN V_ATMCARD V_PLUG V_CIGAR V_MATCHES V_LAPTOP
			 )
				0
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(driveSound number: 877 play:)
		(if (== y 98)
			(self setCycle: Forward)
		else
			(self stopUpd:)
		)
		(cond 
			((not (curRoom script?)) (curRoom setScript: jrFreeScript))
			((rescueScript script?) ((rescueScript script?) cue:))
			(else (rescueScript cue:))
		)
	)
	
	(method (setMotion)
		(driveSound number: 876 play:)
		(super setMotion: &rest)
	)
)

(instance ladder of Sq4Feature
	(properties
		x 83
		y 149
		nsTop 109
		nsLeft 66
		nsBottom 189
		nsRight 100
		sightAngle 90
		lookStr 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(ego setMotion: PolyPath 90 115)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance disk of Sq4Actor
	(properties
		x 88
		y 61
		yStep 7
		view 550
		loop 2
		priority 14
		signal (| fixedLoop fixPriOn)
		xStep 7
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance bridgeSound of Sound
	(properties
		number 152
	)
)

(instance bridge of Sq4Actor
	(properties
		x 193
		y 176
		view 522
		loop 4
		priority 2
		signal (| ignrAct fixedLoop fixPriOn)
		illegalBits $0000
	)
)

(instance blast of Sq4Prop
	(properties
		view 28
		signal (| ignrAct ignrHrz)
	)
	
	(method (cue)
		(super cue:)
		(self dispose:)
	)
)

(instance lOrbZap of Sound
	(properties)
)

(instance rOrbZap of Sound
	(properties)
)

(instance lOrb of Sq4Prop
	(properties
		x 47
		y 123
		sightAngle 90
		view 151
		priority 8
	)
	
	(method (doit)
		(super doit:)
		(if (and lOrbZapTimer (not (-- lOrbZapTimer)))
			(= loop (if (< (Random 1 10) 5) 0 else 2))
			(if (< (Random 1 10) 5)
				(lOrbZap number: (if loop 120 else 118) play:)
				(self setCycle: Oscillate 1 self)
			else
				(lOrbZap number: (if loop 120 else 118) play:)
				(self setCycle: EndLoop self)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if cycler (narrator say: 8) else (narrator say: 9))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(= lOrbZapTimer (Random 20 50))
		(lOrbZap stop:)
		(self stopUpd:)
	)
)

(instance rOrb of Sq4Prop
	(properties
		x 230
		y 87
		sightAngle 90
		view 151
		loop 1
		priority 8
	)
	
	(method (doit)
		(super doit:)
		(if (and rOrbZapTimer (not (-- rOrbZapTimer)))
			(= loop (if (< (Random 1 10) 5) 1 else 3))
			(if (< (Random 1 10) 5)
				(rOrbZap number: (if (== loop 3) 121 else 119) play:)
				(self setCycle: Oscillate 1 self)
			else
				(rOrbZap number: (if (== loop 3) 121 else 119) play:)
				(self setCycle: EndLoop self)
			)
		)
	)
	
	(method (doVerb theVerb)
		(lOrb doVerb: theVerb &rest)
	)
	
	(method (cue)
		(= rOrbZapTimer (Random 20 50))
		(rOrbZap stop:)
		(self stopUpd:)
	)
)

(instance overhang of Sq4Feature
	(properties
		lookStr 10
	)
	
	(method (onMe event)
		(return (== (OnControl cBLUE (event x?) (event y?)) userEvent))
	)
)

(instance pedestal of Sq4Feature
	(properties
		lookStr 11
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TASTE (narrator say: 12))
			(V_SMELL (narrator say: 13))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(return (== (OnControl cBLUE (event x?) (event y?)) keyDown))
	)
)

(instance tVOHAULJR of Sq4Talker
	(properties
		z 400
		noun VOHAULJR
		view 1523
		talkerNum VOHAULJR
		mouthOffsetX 21
		mouthOffsetY 32
		eyeOffsetX 25
		eyeOffsetY 20
	)
)

(instance tROGER of Sq4Talker
	(properties
		z 400
		noun ROGER
		modNum 150
		view 1008
		talkerNum ROGER
		mouthOffsetX 21
		mouthOffsetY 32
		eyeOffsetX 27
		eyeOffsetY 21
	)
)

(instance tROGERJR of Sq4Talker
	(properties
		z 400
		noun ROGERJR
		view 1524
		talkerNum ROGERJR
		mouthOffsetX 21
		mouthOffsetY 32
		eyeOffsetX 25
		eyeOffsetY 20
	)
)
