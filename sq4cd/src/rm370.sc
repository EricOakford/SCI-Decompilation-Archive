;;; Sierra Script 1.0 - (do not remove this comment)
(script# 370)
(include game.sh)
(use Main)
(use mall)
(use BeltWay)
(use SQRoom)
(use Sq4Feature)
(use Osc)
(use RandCyc)
(use Polygon)
(use MoveFwd)
(use Sound)
(use Motion)
(use System)

(public
	rm370 0
)

(local
	local0
	local1
	egoX
	local3
	underbits
	local5
	[local6 24] = [0 0 0 0 101 45 1 102 51 2 103 62 3 104 65 4 106 71 6 106 74 -1 -1 -1]
	local30 =  59
	oldDetail
	dummyTimer =  2
)
(instance rm370 of SQRoom
	(properties
		picture 370
		style FADEOUT
		east 375
		south 400
		west 400
	)
	
	(method (init &tmp temp0 temp1)
		(HandsOff)
		(Load VIEW 370)
		(= oldDetail (theGame detailLevel:))
		(switch prevRoomNum
			(west
				(if (== ((ScriptID MALL 0) whichBelt?) 1)
					(= temp0 37)
					(= temp1 239)
				else
					(= temp0 -18)
					(= temp1 178)
				)
				(ego x: temp0 y: temp1 setLoop: theStopGroop)
				(self setScript: (ScriptID MALL 1) 0 egoBwGreen)
			)
			(east
				(if (== ((ScriptID MALL 0) whichBelt?) 1)
					(= temp0 339)
					(= temp1 66)
				else
					(= temp0 329)
					(= temp1 3)
				)
				(ego x: temp0 y: temp1 setLoop: theStopGroop)
				(self setScript: (ScriptID MALL 1) 0 egoBwBlue)
			)
			(405
				(music number: 405 loop: -1 vol: 127 flags: 1 play:)
				(self setScript: fromElsewhereScript 0 0)
			)
			(else 
				(music number: 405 loop: -1 vol: 95 flags: 1 play:)
				(ego posn: -10 -10)
				(if
					(and
						(proc700_3 (ScriptID MALL 0) 678 128)
						(not (proc700_3 (ScriptID MALL 0) 680 4))
						(not (proc700_3 (ScriptID MALL 0) 680 16))
					)
					(self setScript: tossScript)
				else
					(mall rFlag3: (& (mall rFlag3?) $ffef))
					(self setScript: fromElsewhereScript 0 1)
				)
			)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 0 189 0 185 122 123 136 118 174 141 79 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 23 319 69 214 122 175 98 182 92
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 180 189 207 176 224 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 189 304 189 291 183 291 180 279 173 279 166 252 152 319 119
					yourself:
				)
		)
		(ego
			setPri: -1
			code: beltwayCode
			init:
			setCycle: SyncWalk
		)
		(super init:)
		(if (and (!= prevRoomNum 371) (Btst fSocksClosed))
			(addToPics add: door eachElementDo: #init doit:)
			(door addToPic: dispose:)
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							296 0 178 61 161 62 142 60 125 51 72 73
							74 75 85 86 88 93 87 101 82 111 0 154 0 0
						yourself:
					)
			)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 0 153 0 19 75 76 81 81 87 88 88 92 90 101 84 109
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 296 0 179 60 161 61 143 60 50 0
						yourself:
					)
			)
		)
		(dummy1 init:)
		(dummy2 init:)
		(if (> (theGame detailLevel:) 2)
			(dummy1 setCycle: Oscillate)
			(dummy2 setCycle: Oscillate)
			(light2 init: setCycle: RandCycle)
			(light3 init: setCycle: RandCycle)
			(light4 init: setCycle: RandCycle)
			(light5 init: setCycle: RandCycle)
			(light6 init: setCycle: RandCycle)
			(light7 init: setCycle: RandCycle)
		)
		(if (Btst fPoliceAtMallEntrance)
			(Bclr fPoliceAtMallEntrance)
			((ScriptID MALL 6)
				init:
				illegalBits: 0
				ignoreActors:
				setScript: sp1Squeeze
			)
			(Load SOUND 105)
		)
		(self setRegions: MALL)
		(steps init:)
		(bush1 init:)
		(bush2 init:)
		(store init:)
		(cond 
			((and (!= prevRoomNum 371) (not (Btst fSocksClosed))) (globalSound number: 371 setLoop: -1 flags: mNOPAUSE play: 65))
			((Btst fSocksClosed) (globalSound number: 0 vol: 0 stop:))
		)
	)
	
	(method (doit &tmp temp0 [temp1 5])
		(if (not (-- dummyTimer))
			(cond 
				(
					(<
						(= temp0 (GetDistance (ego x?) (ego y?) local30 0))
						0
					)
					(= temp0 0)
				)
				((> temp0 300) (= temp0 300))
			)
			(globalSound setVol: (- 127 (/ temp0 3)))
			(= dummyTimer 60)
		)
		(if (!= oldDetail (theGame detailLevel:))
			(if
			(> (= oldDetail (theGame detailLevel:)) 1)
				(dummy1 setCycle: Oscillate)
				(dummy2 setCycle: Oscillate)
			else
				(dummy1 stopUpd:)
				(dummy2 stopUpd:)
			)
		)
		(cond 
			(script 0)
			((== (ego edgeHit?) EAST)
				(HandsOff)
				((ScriptID MALL 0) enterBelt: egoBwGreen)
				(self setScript: (ScriptID MALL 2) 0 east)
			)
			(
			(or (== (ego edgeHit?) WEST) (== (ego edgeHit?) SOUTH))
				(if (IsObjectOnControl ego cGREEN)
					(ego
						edgeHit: 0
						setMotion: 0
						posn: (+ (ego x?) 3) (- (ego y?) 4)
					)
				else
					(HandsOff)
					((ScriptID MALL 0) enterBelt: egoBwBlue)
					(self setScript: (ScriptID MALL 2) 0 west)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript BELTWAY)
		(super dispose:)
	)
)

(instance beltwayCode of Code
	(properties)
	
	(method (doit)
		(cond 
			((curRoom script?) 0)
			((IsObjectOnControl ego cCYAN) (curRoom newRoom: 371))
			((IsObjectOnControl ego cRED) (HandsOff) (curRoom setScript: goToRamaScript))
			((IsObjectOnControl ego cGREEN)
				(egoBwGreen who: ego doit:)
				((ScriptID MALL 0) whichBelt: 1)
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
				(ego setPri: -1)
				(proc700_5 1)
			)
		)
	)
)

(instance tossScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setLoop: (if (proc700_3 (ScriptID MALL 0) 680 8) 1 else 0)
					setCycle: 0
					setMotion: 0
				)
				(= local5 0)
				(= seconds 5)
			)
			(1
				(if (!= [local6 (= local5 (+ local5 3))] -1)
					(ego
						view: 392
						illegalBits: 0
						cel: [local6 local5]
						x: [local6 (+ (-- state) 1)]
						y: [local6 (+ local5 2)]
						setCycle: 0
						setMotion: 0
					)
					(= cycles 1)
				else
					(self cue:)
				)
			)
			(2 (= seconds 2))
			(3
				(narrator say: 5)
				(ego setCycle: EndLoop self)
			)
			(4
				(HandsOn)
				(if (proc700_3 (ScriptID MALL 0) 680 8)
					(NormalEgo 2 402 14)
					(EgoHeadMove 14)
				else
					(NormalEgo 2 0 4)
					(EgoHeadMove 4)
				)
				(proc700_5 1)
				((ScriptID MALL 0)
					rFlag1: (& ((ScriptID MALL 0) rFlag1?) $ff7f)
				)
				(self dispose:)
			)
		)
	)
)

(instance fromElsewhereScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2 temp3)
		(switch (= state newState)
			(0
				(if (== (ego view?) 373) (Bclr 8))
				(if register
					(= temp0 75)
					(= temp1 40)
					(= temp2 110)
					(= temp3 74)
				else
					(= temp0 329)
					(= temp1 244)
					(= temp2 252)
					(= temp3 168)
				)
				(ego x: temp0 y: temp1 setMotion: MoveTo temp2 temp3 self)
			)
			(1
				(if (and (== (ego view?) 0) (Btst fFlag29)) (Bset fSocksClosed))
				(proc700_5 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance goToRamaScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setHeading: 135 setMotion: MoveFwd 80 self)
			)
			(1 (curRoom newRoom: 405))
		)
	)
)

(instance egoBwGreen of BeltWay
	(properties
		xStep 2
		yStep 1
		xOff 3
		yOff 1
		xTweak 1
		yTweak 1
		key 225
		head 63
		xDir 1
		yDir -1
	)
)

(instance egoBwBlue of BeltWay
	(properties
		xStep 2
		yStep 1
		xOff 10
		yOff 1
		xTweak 1
		yTweak 1
		key 45
		head 242
		xDir -1
		yDir 1
	)
)

(instance door of Sq4View
	(properties
		x 86
		y 69
		view 370
		loop 8
		priority 1
		signal (| ignrAct fixPriOn)
		lookStr 6
	)
)

(instance light2 of Sq4Prop
	(properties
		x 19
		y 108
		sightAngle 90
		view 370
		loop 2
		cel 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 4))
			(V_SMELL (narrator say: 1))
			(V_TASTE (narrator say: 2))
			(V_TALK (narrator say: 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance light3 of Sq4Prop
	(properties
		x 194
		y 16
		sightAngle 90
		view 370
		loop 3
		cel 2
		priority 1
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 4))
			(V_SMELL (narrator say: 1))
			(V_TASTE (narrator say: 2))
			(V_TALK (narrator say: 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance light4 of Sq4Prop
	(properties
		x 50
		y 75
		sightAngle 90
		view 370
		loop 4
		cel 1
		priority 15
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 4))
			(V_SMELL (narrator say: 1))
			(V_TASTE (narrator say: 2))
			(V_TALK (narrator say: 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance light5 of Sq4Prop
	(properties
		x 140
		y 25
		sightAngle 90
		view 370
		loop 5
		cel 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 4))
			(V_SMELL (narrator say: 1))
			(V_TASTE (narrator say: 2))
			(V_TALK (narrator say: 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance light6 of Sq4Prop
	(properties
		x 164
		y 28
		sightAngle 90
		view 370
		loop 6
		cel 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 4))
			(V_SMELL (narrator say: 1))
			(V_TASTE (narrator say: 2))
			(2 (narrator say: 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance light7 of Sq4Prop
	(properties
		x 48
		y 91
		sightAngle 90
		view 370
		loop 7
		cel 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 4))
			(V_SMELL (narrator say: 1))
			(V_TASTE (narrator say: 2))
			(V_TALK (narrator say: 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance dummy1 of Sq4Prop
	(properties
		x 26
		y 101
		sightAngle 90
		view 370
		cycleSpeed 18
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 7))
			(V_SMELL (narrator say: 1))
			(V_TASTE (narrator say: 2))
			(V_TALK (narrator say: 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance dummy2 of Sq4Prop
	(properties
		x 160
		y 20
		sightAngle 90
		view 370
		loop 1
		cycleSpeed 18
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 7))
			(V_SMELL (narrator say: 1))
			(V_TASTE (narrator say: 2))
			(V_TALK (narrator say: 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance blast of Sq4Prop
	(properties
		view 28
	)
)

(instance sp1Squeeze of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			((and (not state) (> (ego x?) 90)) (self cue:))
			(
			(and (not register) (IsObjectOnControl client 1024)) (if (> (ego x?) 259) (self changeState: 9)))
			((and (not register) (< (ego y?) 138)) (self changeState: 9))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setLoop: 1 setCel: 0 posn: 320 2)
			)
			(1
				(client
					setLoop: 1
					setCel: 0
					setStep: 2 2
					setMotion: MoveTo 244 46 self
				)
			)
			(2
				(client setLoop: 2 setMotion: MoveTo 231 49 self)
			)
			(3
				(client
					setMotion: MoveTo 0 172
					view: 13
					setCycle: CycleTo 1 1 self
				)
			)
			(4
				(= local0 21)
				(= local1 172)
				(= local3 210)
				(= egoX 226)
				(= underbits
					(Graph
						GSaveBits
						(- local0 1)
						(- local3 1)
						(+ local1 1)
						(+ egoX 1)
						1
					)
				)
				(Graph
					GDrawLine
					local0
					egoX
					local1
					local3
					(VGAOrEGA myTextColor3 myTextColor2)
					4
					-1
				)
				(Graph
					GReAnimate
					(- local0 1)
					(- local3 1)
					(+ local1 1)
					(+ egoX 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(5
				(client setCycle: EndLoop)
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- local0 1)
					(- local3 1)
					(+ local1 1)
					(+ egoX 1)
				)
				(client cel: 0)
				(blast init: posn: 210 170 setCycle: EndLoop self)
				((ScriptID MALL 7)
					init:
					illegalBits: 0
					ignoreActors:
					setScript: sp2Squeeze
				)
			)
			(6
				(client setCycle: CycleTo 1 1 self)
			)
			(7
				(= local3 (- (client x?) 4))
				(= local0 (- (client y?) 27))
				(= egoX 275)
				(= local1 132)
				(= underbits
					(Graph
						GSaveBits
						(- local0 1)
						(- local3 1)
						(+ local1 1)
						(+ egoX 1)
						1
					)
				)
				(Graph
					GDrawLine
					local0
					local3
					local1
					egoX
					(VGAOrEGA myTextColor3 myTextColor2)
					4
					-1
				)
				(Graph
					GReAnimate
					(- local0 1)
					(- local3 1)
					(+ local1 1)
					(+ egoX 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(8
				(client setCycle: EndLoop)
				(blast init: posn: 275 132 setCycle: EndLoop)
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- local0 1)
					(- local3 1)
					(+ local1 1)
					(+ egoX 1)
				)
				(client cel: 0)
			)
			(9
				(= register 1)
				(client setCycle: CycleTo 1 1 self)
			)
			(10
				(= local3 (- (client x?) 4))
				(= local0 (- (client y?) 27))
				(= egoX (ego x?))
				(= local1 (- (ego y?) 30))
				(= underbits
					(Graph
						GSaveBits
						(- local0 1)
						(- local3 1)
						(+ local1 1)
						(+ egoX 1)
						1
					)
				)
				(Graph
					GDrawLine
					local0
					local3
					local1
					egoX
					(VGAOrEGA myTextColor3 myTextColor2)
					(- (ego priority?) 1)
					-1
				)
				(Graph
					GReAnimate
					(- local0 1)
					(- local3 1)
					(+ local1 1)
					(+ egoX 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(11
				(client setCycle: EndLoop)
				(blast init: posn: egoX local1 setCycle: EndLoop self)
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- local0 1)
					(- local3 1)
					(+ local1 1)
					(+ egoX 1)
				)
				(client cel: 0)
			)
			(12 (EgoDead iconLASER deathSTANDTHERE))
		)
	)
)

(instance sp2Squeeze of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(== state 1)
				(or
					(< (ego x?) 130)
					(> (+ (ego y?) 4) (client y?))
					(curRoom script?)
				)
			)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client posn: 33 229 view: 13 setLoop: 0 setCel: 0)
				(= cycles 8)
			)
			(1
				(client setMotion: MoveTo 86 184 self)
			)
			(2
				(client setCycle: CycleTo 1 1 self setMotion: MoveTo 319 85)
			)
			(3
				(= register (not (curRoom script?)))
				(= local3 (+ (client x?) 6))
				(= local1 (- (client y?) 29))
				(if register
					(= egoX (ego x?))
					(= local0 (- (ego y?) 32))
					(ego setMotion: 0)
				else
					(= egoX 295)
					(= local0 151)
				)
				(= underbits
					(Graph
						GSaveBits
						(- local0 1)
						(- local3 1)
						(+ local1 1)
						(+ egoX 1)
						1
					)
				)
				(Graph
					GDrawLine
					local1
					local3
					local0
					egoX
					(VGAOrEGA myTextColor3 myTextColor2)
					(- (ego priority?) 1)
					-1
				)
				(Graph
					GReAnimate
					(- local0 1)
					(- local3 1)
					(+ local1 1)
					(+ egoX 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(4
				(client setCycle: EndLoop)
				(blast
					cel: 0
					posn: egoX local0
					setPri: 12
					setCycle: EndLoop self
				)
				(if register (ego view: 26 cel: 0 setCycle: EndLoop))
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- local0 1)
					(- local3 1)
					(+ local1 1)
					(+ egoX 1)
				)
				(client cel: 0)
			)
			(5
				(if register (EgoDead iconLASER deathKNEWCOMING))
			)
		)
	)
)

(instance store of Sq4Feature
	(properties
		x 20
		y 20
		lookStr 8
	)
	
	(method (onMe param1)
		(return
			(<=
				51
				(/ (* 100 (- 145 (param1 y?))) (Max 1 (param1 x?)))
			)
		)
	)
)

(instance steps of Sq4Feature
	(properties
		x 310
		y 185
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 9))
			(V_SMELL (narrator say: 10))
			(V_TASTE (narrator say: 11))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(return (== (OnControl 4 (event x?) (event y?)) 2048))
	)
)

(instance bush1 of Sq4Feature
	(properties
		x 65
		y 145
		nsTop 130
		nsLeft 57
		nsBottom 161
		nsRight 80
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 12))
			(V_SMELL (narrator say: 13))
			(V_TASTE (narrator say: 14))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bush2 of Sq4Feature
	(properties
		x 260
		y 50
		nsTop 36
		nsLeft 252
		nsBottom 61
		nsRight 275
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 12))
			(V_SMELL (narrator say: 15))
			(V_TASTE (narrator say: 14))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance aSound of Sound
	(properties)
)
