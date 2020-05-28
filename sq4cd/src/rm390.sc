;;; Sierra Script 1.0 - (do not remove this comment)
(script# 390)
(include game.sh)
(use Main)
(use mall)
(use BeltWay)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use MoveCyc)
(use Polygon)
(use MoveFwd)
(use Sound)
(use Motion)
(use System)

(public
	rm390 0
)

(local
	[local0 18] = [181 5 187 8 197 14 211 16 225 20 235 31 250 36 263 41 274 45]
	[zapCycle 37] = [0 0 181 5 0 1 187 8 0 2 197 14 0 3 211 16 0 4 225 20 0 5 235 31 0 6 250 36 0 7 263 41 0 8 274 45 -32768]
	local55
	local56
	egoX_2
	egoX
	underbits
	local60
	local61
)
(instance rm390 of SQRoom
	(properties
		picture 390
		style FADEOUT
		north 385
		east 395
		south 395
		west 385
	)
	
	(method (init &tmp temp0 temp1)
		(HandsOff)
		(globalSound number: 999 loop: -1 play: 127)
		(switch prevRoomNum
			(west
				(if (== ((ScriptID MALL 0) whichBelt?) 1)
					(= temp0 -12)
					(= temp1 81)
				else
					(= temp0 -9)
					(= temp1 -6)
				)
				(ego x: temp0 y: temp1 setLoop: theStopGroop)
				(self setScript: (ScriptID MALL 1) 0 egoBwGreen)
			)
			(east
				(if (== ((ScriptID MALL 0) whichBelt?) 1)
					(= temp0 256)
					(= temp1 249)
				else
					(= temp0 331)
					(= temp1 173)
				)
				(ego x: temp0 y: temp1 setLoop: theStopGroop)
				(self setScript: (ScriptID MALL 1) 0 egoBwBlue)
			)
			(410
				(music number: 405 loop: -1 play:)
				(self setScript: fromElsewhereScript 0 0)
			)
			(else 
				(theIconBar enable:)
				(music number: 405 play:)
				(self setScript: fromElsewhereScript 0 1)
			)
		)
		(music setVol: 127 setPri: 7)
		(super init:)
		(ego
			setPri: -1
			code: beltwayCode
			init:
			setCycle: SyncWalk
		)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 0 113 65 145 64 157 44 170 40 179 13 189 0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 0 23 147 99 110 123 0 65
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 235 189 149 144 189 120 319 187 319 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 150 189 95 189 117 171
					yourself:
				)
		)
		(if (and (Btst fPoliceAtMallEntrance) (not (Btst fPoliceInSkateORama)))
			(Load SOUND 105)
			((ScriptID MALL 7)
				illegalBits: 0
				ignoreActors:
				init:
				posn: -14 79
				setScript: sp2Squeeze
			)
			((ScriptID MALL 6) illegalBits: 0 ignoreActors:)
		)
		(self setRegions: MALL)
		(store init:)
		(bush1 init:)
		(bush2 init:)
		(steps init:)
		(if (Btst fRadioShockClosed)
			(addToPics add: door eachElementDo: #init doit:)
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 21 0 319 0 319 150 224 101 235 82 181 53 156 69
						yourself:
					)
			)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 316 0 163 73 21 0
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 319 152 226 105 319 51
						yourself:
					)
			)
		)
		(= local60 30)
	)
	
	(method (doit &tmp [temp0 2])
		(cond 
			(script 0)
			((ego edgeHit?)
				(HandsOff)
				(if (OneOf (ego edgeHit?) 3 2)
					((ScriptID MALL 0) enterBelt: egoBwGreen)
					(self setScript: (ScriptID MALL 2) 0 east)
				else
					((ScriptID MALL 0) enterBelt: egoBwBlue)
					(self setScript: (ScriptID MALL 2) 0 west)
				)
			)
		)
		(Palette PALCycle 225 236 6)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 804)
		(super dispose:)
	)
	
	(method (newRoom newRoomNumber)
		(if (== newRoomNumber 391) (music fade: 40 10 10 0))
		(cond 
			((OneOf newRoomNumber 391 410 900) (super newRoom: newRoomNumber))
			((!= script (ScriptID MALL 2)) (HandsOff) (self setScript: (ScriptID MALL 2) 0 1))
			((and script (not (script register?))) (super newRoom: newRoomNumber))
			(else (super newRoom: newRoomNumber))
		)
	)
)

(instance beltwayCode of Code
	(properties)
	
	(method (doit &tmp temp0 temp1)
		(cond 
			((curRoom script?) 0)
			((& (= temp1 (ego onControl: 1)) local60)
				(cond 
					((& temp1 $0010) (HandsOff) (curRoom setScript: goToRamaScript))
					((& temp1 $0008) (curRoom newRoom: 391))
					((& temp1 $0004)
						(egoBwGreen who: ego doit:)
						((ScriptID MALL 0) whichBelt: 1)
						(proc700_5 0)
						(music fade: 127 10 5 0)
					)
					((& temp1 $0002)
						(egoBwBlue who: ego doit:)
						((ScriptID MALL 0) whichBelt: 2)
						(proc700_5 0)
						(music fade: 95 10 5 0)
					)
				)
			)
			((| (egoBwGreen onCon?) (egoBwBlue onCon?))
				(egoBwGreen onCon: 0)
				(egoBwBlue onCon: 0)
				(ego xStep: 3 yStep: 2 setPri: -1)
				(proc700_5 1)
			)
		)
	)
)

(instance fromElsewhereScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2 temp3)
		(switch (= state newState)
			(0
				(if register
					(= temp0 224)
					(= temp1 54)
					(= temp2 196)
					(= temp3 82)
				else
					(= temp0 -3)
					(= temp1 244)
					(= temp2 71)
					(= temp3 170)
				)
				(ego x: temp0 y: temp1 setMotion: MoveTo temp2 temp3 self)
			)
			(1
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
				(ego setHeading: 225 setMotion: MoveFwd 80 self)
			)
			(1 (curRoom newRoom: 410))
		)
	)
)

(instance zapScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					x: [local0 register]
					y: [local0 (+ register 1)]
					cel: (/ register 2)
				)
				(if (and (< register 3) (> numVoices 1))
					(signSound play:)
				)
				(= cycles 1)
			)
			(1
				(if (> (= register (+ register 2)) 16) (= register 0))
				(= cycles 3)
			)
			(2 (self init:))
		)
	)
)

(instance sZap of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(client setCycle: MoveCycle @zapCycle self)
			)
			(1 1 (signSound play: self))
			(2 2 (self changeState: 0))
		)
	)
)

(instance egoBwGreen of BeltWay
	(properties
		xStep 2
		yStep -1
		xOff 1
		yOff -1
		xTweak 1
		yTweak -1
		key 315
		head 119
		xDir 1
		yDir -1
		tixCnt 6
	)
)

(instance egoBwBlue of BeltWay
	(properties
		xStep 2
		yStep -1
		xOff 1
		yOff -1
		xTweak 1
		yTweak -1
		key 135
		head 301
		xDir -1
		yDir 1
		tixCnt 6
	)
)

(instance door of Sq4View
	(properties
		x 181
		y 28
		view 390
		loop 1
		priority 3
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(narrator modNum: 375 say: 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sp2Squeeze of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(cond 
			(
				(and
					(not state)
					(or (< (ego x?) 219) (IsObjectOnControl ego 8192))
				)
				(self cue:)
			)
			((curRoom script?))
			(
				(and
					(< (ego y?) (- ((ScriptID MALL 7) y?) 12))
					(not (IsObjectOnControl ego 8192))
				)
				(client setScript: sp2ShootEgo)
			)
			((& (= temp0 (ego onControl: 1)) $0001) (client setScript: sp2ShootDown))
			((& temp0 $2000) (curRoom setScript: hideEgoInStore))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				((ScriptID MALL 7)
					view: 7
					setLoop: 2
					setCel: 5
					setStep: 2 2
					setMotion: MoveTo 89 133 self
				)
			)
			(2
				((ScriptID MALL 6) init: setScript: sp1Squeeze)
				((ScriptID MALL 7)
					setStep: 3 2
					setCycle: Walk
					setLoop: -1
					setCel: -1
					setMotion: MoveTo 134 122 self
				)
			)
			(3
				((ScriptID MALL 7) view: 13 loop: 3 cel: 0 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance sp1Squeeze of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			((curRoom script?))
			(
				(and
					(< ((ScriptID MALL 6) x?) 310)
					(not (& (ego onControl: 1) $2000))
				)
				(client setScript: sp1ShootEgo)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID MALL 6)
					setStep: 2 2
					posn: 330 182
					view: 13
					setLoop: 1
					setCel: 0
					init:
					setMotion: MoveTo 199 112 self
				)
			)
			(1
				((ScriptID MALL 6)
					setStep: 3 2
					view: 7
					setCycle: Walk
					setLoop: -1
					setCel: -1
					setMotion: MoveTo 159 116 self
				)
			)
			(2
				((ScriptID MALL 6) view: 13 loop: 1 cel: 0 stopUpd:)
				(= seconds 3)
			)
			(3 (tSP1 say: 1))
			(4
				(tSP2 say: 1 self 2 64 175 70 67 140)
			)
			(5
				(tSP1 say: 2 self 2 64 175 80 67 140)
			)
			(6
				(tSP2 say: 2 self 2 64 175 70 67 140)
			)
			(7
				(tSP1 say: 3 self 2 64 175 80 67 140)
			)
			(8
				(tSP2 say: 3 self 2 64 175 70 67 140)
			)
			(9
				(tSP2 say: 4 self 2 64 175 70 67 140)
			)
			(10
				(tSP1 say: 4 self 2 64 175 80 67 140)
			)
			(11
				((ScriptID MALL 6) view: 7 setMotion: MoveTo 148 85 self)
			)
			(12
				((ScriptID MALL 6)
					view: 13
					setLoop: 1
					setCel: 0
					setStep: 2 2
					setMotion: MoveTo 1 1
				)
				(= cycles 10)
			)
			(13
				((ScriptID MALL 7) view: 7 setMotion: MoveTo 143 156 self)
			)
			(14
				((ScriptID MALL 7)
					view: 13
					setLoop: 0
					setCel: 0
					setStep: 2 2
					setMotion: MoveTo 267 236 self
				)
			)
			(15
				(hideEgoInStore cue:)
				(self dispose:)
			)
		)
	)
)

(instance sp1ShootEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID MALL 6) setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(1
				(= local55 (- (ego y?) 33))
				(= local56 (- ((ScriptID MALL 6) y?) 34))
				(= egoX (ego x?))
				(= egoX_2 (- ((ScriptID MALL 6) x?) 8))
				(= underbits
					(Graph
						GSaveBits
						(- local55 1)
						(- egoX 1)
						(+ local56 1)
						(+ egoX_2 1)
						1
					)
				)
				(Graph
					GDrawLine
					local56
					egoX_2
					local55
					egoX
					(VGAOrEGA myTextColor3 myTextColor13)
					(- (ego priority?) 1)
					-1
				)
				(Graph
					GReAnimate
					(- local55 1)
					(- egoX 1)
					(+ local56 1)
					(+ egoX_2 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(2
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- local55 1)
					(- egoX 1)
					(+ local56 1)
					(+ egoX_2 1)
				)
				(blast
					init:
					cel: 0
					posn: egoX local55
					setPri: (+ (ego priority?) 1)
					setCycle: EndLoop
				)
				(ego
					view: 26
					cel: 0
					cycleSpeed: 12
					ignoreActors:
					setCycle: EndLoop self
				)
			)
			(3 (EgoDead 8 3))
		)
	)
)

(instance sp2ShootDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID MALL 7)
					view: 13
					setLoop: 4
					setCel: 0
					setPri: (ego priority?)
				)
				(= cycles 4)
			)
			(1
				((ScriptID MALL 7) setCycle: CycleTo 1 1 self)
			)
			(2
				(= local56 (- (ego y?) 33))
				(= local55 (- ((ScriptID MALL 7) y?) 20))
				(= egoX_2 (ego x?))
				(= egoX (+ ((ScriptID MALL 7) x?) 11))
				(= underbits
					(Graph
						GSaveBits
						(- local55 1)
						(- egoX 1)
						(+ local56 1)
						(+ egoX_2 1)
						1
					)
				)
				(Graph
					GDrawLine
					local55
					egoX
					local56
					egoX_2
					(VGAOrEGA myTextColor3 myTextColor13)
					(- (ego priority?) 1)
					-1
				)
				(Graph
					GReAnimate
					(- local55 1)
					(- egoX 1)
					(+ local56 1)
					(+ egoX_2 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(3
				((ScriptID MALL 7) setCel: 0)
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- local55 1)
					(- egoX 1)
					(+ local56 1)
					(+ egoX_2 1)
				)
				(blast
					init:
					cel: 0
					posn: egoX_2 local56
					setPri: (+ (ego priority?) 1)
					setCycle: EndLoop
				)
				(ego
					view: 26
					cel: 0
					cycleSpeed: 12
					ignoreActors:
					setCycle: EndLoop self
				)
			)
			(4 (EgoDead iconLASER deathDILLYDALLY))
		)
	)
)

(instance sp2ShootEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID MALL 7) view: 13 setLoop: 0 setCel: 0)
				(= cycles 2)
			)
			(1
				((ScriptID MALL 7) setCycle: CycleTo 1 1 self)
			)
			(2
				(= local55 (- (ego y?) 33))
				(= local56 (- ((ScriptID MALL 7) y?) 28))
				(= egoX_2 (ego x?))
				(= egoX (+ ((ScriptID MALL 7) x?) 16))
				(= underbits
					(Graph
						GSaveBits
						(- local55 1)
						(- egoX 1)
						(+ local56 1)
						(+ egoX_2 1)
						1
					)
				)
				(Graph
					GDrawLine
					local56
					egoX
					local55
					egoX_2
					(VGAOrEGA myTextColor3 myTextColor13)
					(- (ego priority?) 1)
					-1
				)
				(Graph
					GReAnimate
					(- local55 1)
					(- egoX 1)
					(+ local56 1)
					(+ egoX_2 1)
				)
				((ScriptID MALL 7) setCycle: EndLoop)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(3
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- local55 1)
					(- egoX 1)
					(+ local56 1)
					(+ egoX_2 1)
				)
				(blast
					init:
					cel: 0
					posn: egoX_2 local55
					setPri: (+ (ego priority?) 1)
					setCycle: EndLoop
				)
				(ego
					view: 26
					cel: 0
					cycleSpeed: 12
					ignoreActors:
					setCycle: EndLoop self
				)
			)
			(4 (EgoDead 8 3))
		)
	)
)

(instance hideEgoInStore of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 192 49 self)
			)
			(1
				(ego setPri: 1 setMotion: MoveTo 171 47)
			)
			(2
				(ego setMotion: MoveTo 192 49 self)
			)
			(3
				(ego setPri: -1 setMotion: MoveTo 199 86 self)
			)
			(4 (= cycles 4))
			(5
				(narrator say: 1)
				(HandsOn)
				(ego illegalBits: cWHITE)
				(self dispose:)
			)
		)
	)
)

(instance blast of Sq4Prop
	(properties
		view 28
		signal ignrAct
	)
)

(instance signSound of Sound
	(properties
		number 391
		priority 1
	)
)

(instance store of Sq4Feature
	(properties
		x 280
		y 30
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 3))
			(V_TASTE (narrator say: 5))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(return
			(>=
				-51
				(/ (* 100 (- 151 (event y?))) (- (event x?) 320))
			)
		)
	)
)

(instance steps of Sq4Feature
	(properties
		y 185
		lookStr 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 1))
			(V_SMELL (narrator say: 6))
			(V_TASTE
				(if
				(or (== (ego view?) 374) (== (ego view?) 373))
					(tRogette say: 1)
				else
					(tRog say: 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe param1)
		(return (== (OnControl 4 (param1 x?) (param1 y?)) 2048))
	)
)

(instance bush1 of Sq4Feature
	(properties
		x 60
		y 45
		nsTop 32
		nsLeft 49
		nsBottom 62
		nsRight 70
		lookStr 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SMELL (narrator say: 8))
			(V_TASTE (narrator say: 9))
			(V_DO (narrator say: 10))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bush2 of Sq4Feature
	(properties
		x 245
		y 140
		nsTop 129
		nsLeft 239
		nsBottom 157
		nsRight 261
		lookStr 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SMELL (narrator say: 8))
			(V_TASTE (narrator say: 9))
			(V_DO (narrator say: 10))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance tRogette of Sq4Talker
	(properties
		z 400
		noun ROGER
		view 1009
		talkerNum ROGER
		mouthOffsetX 21
		mouthOffsetY 32
		eyeOffsetX 26
		eyeOffsetY 21
	)
)

(instance tRog of Sq4Talker
	(properties
		z 400
		noun ROGER
		view 1008
		talkerNum ROGER
		mouthOffsetX 21
		mouthOffsetY 32
		eyeOffsetX 27
		eyeOffsetY 21
	)
)

(instance tSP1 of Sq4Talker
	(properties
		z 400
		noun SP4
		view 1015
		talkerNum SP4
		mouthOffsetX 22
		mouthOffsetY 35
	)
)

(instance tSP2 of Sq4Talker
	(properties
		z 400
		noun SP3
		view 1016
		talkerNum SP3
		mouthOffsetX 21
		mouthOffsetY 34
		eyeOffsetX 1
		eyeOffsetY 1
	)
)

(instance aSound of Sound
	(properties)
)
