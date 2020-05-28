;;; Sierra Script 1.0 - (do not remove this comment)
(script# 380)
(include game.sh)
(use Main)
(use mall)
(use BeltWay)
(use SQRoom)
(use Sq4Feature)
(use Polygon)
(use Sound)
(use Motion)
(use System)

(public
	rm380 0
)

(local
	local0
	[local1 24] = [0 0 0 0 138 40 1 151 43 2 155 51 3 157 57 4 158 59 5 159 63 -1 -1 -1]
	local25 =  151
	local26 =  1
)
(instance rm380 of SQRoom
	(properties
		picture 380
		style FADEOUT
		east 385
		west 375
	)
	
	(method (init &tmp theX theY)
		(Load VIEW 380)
		(Load SOUND 145)
		(HandsOff)
		(switch prevRoomNum
			(west
				(if (== ((ScriptID STREET 0) whichBelt?) 1)
					(= theX -40)
					(= theY 135)
				else
					(= theX -12)
					(= theY 105)
				)
				(ego x: theX y: theY setLoop: theStopGroop)
				(if (or (Btst fTallGuyBrokeSign) (Btst fBigTallClosed))
					(self setScript: (ScriptID MALL 1) 0 egoBwGreen)
				else
					(tallGuy
						x: -1000
						y: -1000
						setSpeed: speed
						init:
						setScript: tallGuyScript 0 egoBwGreen
					)
				)
			)
			(east
				(if (== ((ScriptID MALL 0) whichBelt?) 1)
					(= theX 340)
					(= theY 123)
				else
					(= theX 339)
					(= theY 84)
				)
				(ego x: theX y: theY setLoop: theStopGroop)
				(if (or (Btst fTallGuyBrokeSign) (Btst fBigTallClosed))
					(self setScript: (ScriptID MALL 1) 0 egoBwBlue)
				else
					(tallGuy
						x: -1000
						y: -1000
						setSpeed: speed
						init:
						setScript: tallGuyScript 0 egoBwBlue
					)
				)
			)
			(else 
				(music number: 405 loop: -1 play: 95)
				(ego posn: -10 -10)
				(self
					setScript:
						(if (proc700_3 (ScriptID MALL 0) 678 128)
							tossScript
						else
							fromStoreScript
						)
				)
			)
		)
		(tallSign cel: (if (Btst fTallGuyBrokeSign) 4 else 0) init: stopUpd:)
		(ego
			setPri: -1
			code: beltwayCode
			init:
			setCycle: SyncWalk
		)
		(super init:)
		(if (Btst 10)
			(addToPics add: door eachElementDo: #init doit:)
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 319 78 209 83 193 64 114 69 105 88 0 92 0 0 319 0
						yourself:
					)
			)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 151 0 110 89 0 94 0 0
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 319 0 319 78 203 84 178 41 175 0
						yourself:
					)
			)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 319 138 319 189 0 189 0 152
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 0 111 133 103 131 123 120 127 0 133
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 95 319 116 187 123 179 101
					yourself:
				)
		)
		(self setRegions: MALL)
		(bush init:)
		(bush1 init:)
		(BigTallSign init:)
		(store init:)
		(OutsideTheShop init:)
		(cond 
			((and (!= prevRoomNum 381) (not (Btst fBigTallClosed))) (globalSound number: 381 loop: -1 play: 65))
			((Btst fBigTallClosed) (globalSound number: 0 vol: 0 stop:))
		)
	)
	
	(method (doit &tmp temp0)
		(if (not (-- local26))
			(cond 
				(
					(<
						(= temp0 (GetDistance (ego x?) (ego y?) local25 0))
						0
					)
					(= temp0 0)
				)
				((> temp0 300) (= temp0 300))
			)
			(globalSound setVol: (- 127 (/ temp0 3)))
			(= local26 60)
		)
		(cond 
			(script 0)
			((== (ego edgeHit?) EAST)
				(HandsOff)
				((ScriptID MALL 0) enterBelt: egoBwGreen)
				(self setScript: (ScriptID MALL 2) 0 east)
			)
			((== (ego edgeHit?) WEST)
				(HandsOff)
				((ScriptID MALL 0) enterBelt: egoBwBlue)
				(globalSound stop:)
				(self setScript: (ScriptID MALL 2) 0 west)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 804)
		(super dispose:)
	)
)

(instance fromStoreScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(ego
					x: 151
					y: 56
					illegalBits: 0
					heading: 180
					setMotion: MoveTo 151 80 self
				)
			)
			(2
				(proc700_5 1)
				(HandsOn)
				(ego illegalBits: cWHITE)
				(self dispose:)
			)
		)
	)
)

(instance tossScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= local0 0) (= seconds 5))
			(1
				(if (!= [local1 (= local0 (+ local0 3))] -1)
					(ego
						view: 392
						illegalBits: 0
						setLoop: 1
						cel: [local1 local0]
						posn: [local1 (+ (-- state) 1)] [local1 (+ local0 2)]
					)
					(= cycles 1)
				else
					(self cue:)
				)
			)
			(2
				(HandsOn)
				(NormalEgo 2 402 14)
				(proc700_5 1)
				((ScriptID MALL 0)
					rFlag1: (& ((ScriptID MALL 0) rFlag1?) $ff7f)
				)
				(self dispose:)
			)
		)
	)
)

(instance beltwayCode of Code
	(properties)
	
	(method (doit)
		(cond 
			((curRoom script?) 0)
			((IsObjectOnControl ego cRED) (ego setPri: 1))
			((IsObjectOnControl ego cGREEN)
				(egoBwGreen who: ego doit:)
				((ScriptID MALL 0) whichBelt: 1)
				(ego setPri: -1)
				(proc700_5 0)
				(music fade: 127 10 5 0)
			)
			((IsObjectOnControl ego cBLUE)
				(egoBwBlue who: ego doit:)
				((ScriptID MALL 0) whichBelt: 2)
				(proc700_5 0)
				(music fade: 95 10 5 0)
			)
			((or (egoBwGreen onCon?) (egoBwBlue onCon?))
				(egoBwGreen onCon: 0)
				(egoBwBlue onCon: 0)
				(ego xStep: 3 yStep: 2)
				(proc700_5 1)
			)
			((IsObjectOnControl ego cCYAN) (curRoom newRoom: 381))
		)
	)
)

(instance sDS of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 10))
			(1
				(if
					(and
						(< 130 ((ScriptID MALL 9) x?))
						(< ((ScriptID MALL 9) x?) 240)
					)
					(self init:)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance tallGuyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(and
						(< 130 ((ScriptID MALL 9) x?))
						(< ((ScriptID MALL 9) x?) 240)
					)
					(self setScript: sDS self)
				else
					(= cycles 1)
				)
			)
			(1
				(tallGuy
					setCycle: Walk
					setPri: 1
					setSpeed: speed
					setStep: 3 2
					posn: 148 -58
					setMotion: MoveTo 148 -6 self
				)
			)
			(2
				(soundFX number: 145 loop: 1 play:)
				(tallSign setCycle: CycleTo 2 1 self)
			)
			(3
				(tallSign setCycle: EndLoop)
				(tallGuy setMotion: MoveTo 148 14 self)
			)
			(4
				(Bset fTallGuyBrokeSign)
				(curRoom setScript: (ScriptID MALL 1) 0 register)
				(tallGuy
					setLoop: 1
					setPri: 6
					setMotion: MoveTo (- (tallGuy x?) 40) 20 self
				)
			)
			(5
				(tallGuy setSpeed: speed setMotion: MoveTo -18 20 self)
			)
			(6
				(proc700_5 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoBwGreen of BeltWay
	(properties
		xStep 2
		xOff 8
		yOff 1
		xTweak 2
		key 270
		head 86
		xDir 1
		yDir -1
	)
)

(instance egoBwBlue of BeltWay
	(properties
		xStep 2
		xOff 8
		yOff 1
		xTweak 2
		key 90
		head 265
		xDir -1
		yDir 1
	)
)

(instance door of Sq4View
	(properties
		x 110
		y 27
		view 380
		loop 3
		priority 1
		signal (| ignrAct fixPriOn)
		lookStr 13
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance tallGuy of Sq4Actor
	(properties
		x 148
		y -58
		view 380
		priority 3
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		illegalBits $0000
		lookStr 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SMELL (narrator say: 5))
			(V_TASTE (narrator say: 6))
			(V_TALK (narrator say: 7))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance tallSign of Sq4Prop
	(properties
		x 149
		y 27
		view 380
		loop 2
		priority 5
		signal fixPriOn
		cycleSpeed 12
	)
)

(instance bush of Sq4Feature
	(properties
		x 51
		y 121
		z 32
		nsTop 81
		nsLeft 40
		nsBottom 106
		nsRight 63
		sightAngle 90
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 370 say: 12)
			)
			(V_DO (narrator say: 1))
			(V_SMELL (narrator say: 8))
			(V_TASTE (narrator say: 7))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bush1 of Sq4Feature
	(properties
		x 264
		y 109
		z 31
		nsTop 71
		nsLeft 251
		nsBottom 95
		nsRight 275
		sightAngle 90
		lookStr 13
	)
	
	(method (doVerb theVerb)
		(bush doVerb: theVerb)
	)
)

(instance BigTallSign of Sq4Feature
	(properties
		x 152
		y 87
		z 73
		nsTop 1
		nsLeft 60
		nsBottom 27
		nsRight 245
		sightAngle 90
		lookStr 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (narrator say: 9))
			(V_SMELL (narrator say: 2))
			(V_TASTE (narrator say: 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance OutsideTheShop of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		sightAngle 500
		lookStr 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SMELL (narrator say: 11))
			(V_TASTE (narrator say: 12))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance soundFX of Sound
	(properties)
)

(instance store of Sq4Feature
	(properties
		x 145
		lookStr 13
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SMELL (narrator say: 2))
			(V_TASTE (narrator say: 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(return
			(<=
				5
				(/ (* 100 (- 90 (event y?))) (Max 1 (event x?)))
			)
		)
	)
)
