;;; Sierra Script 1.0 - (do not remove this comment)
(script# 385)
(include game.sh)
(use Main)
(use mall)
(use BeltWay)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use MoveCyc)
(use Polygon)
(use LoadMany)
(use Sound)
(use Motion)
(use System)

(public
	rm385 0
)

(local
	[egoLandCycle 53] = [0 0 164 69 0 0 165 75 0 1 167 82 0 1 168 90 0 2 169 99 0 2 170 108 0 3 171 115 0 3 173 125 0 4 174 129 0 4 176 128 0 5 177 122 0 5 178 127 0 6 178 129 -32768]
	[egoTossedCycle 53] = [1 0 164 69 1 0 165 75 1 0 167 82 1 1 168 90 1 1 169 99 1 2 170 108 1 2 171 115 1 3 173 125 1 3 174 129 1 4 176 128 1 4 177 122 1 5 178 127 1 5 178 129 -32768]
	[cigarCycle 29] = [5 3 180 93 5 4 177 103 5 5 171 99 5 6 165 94 5 0 161 105 5 1 156 123 5 2 156 140 -32768]
	blastY
	local136
	local137
	blastX
	local139
	local140
	local141 =  175
	policeTimer =  1
	[burgerPolyPts 30] = [262 -1 272 27 231 91 193 97 188 87 198 50 168 50 137 46 138 84 131 94 123 94 92 81 84 60 74 33 71]
)
(instance rm385 of SQRoom
	(properties
		picture 385
		style FADEOUT
		east 390
		west 380
	)
	
	(method (init &tmp temp0 temp1)
		(HandsOff)
		(cond 
			((and (!= prevRoomNum 386) (not (Btst fMonolithBurgerClosed)))
				(groundCigarSFX init:)
				(tossedSFX init:)
				(egoLandsSFX init:)
				(globalSound number: 4 loop: -1 flags: mNOPAUSE play: 65)
			)
			((Btst fMonolithBurgerClosed) (globalSound number: 0 vol: 0 stop:))
		)
		(switch prevRoomNum
			(west
				(if (== ((ScriptID MALL 0) whichBelt?) 1)
					(= temp0 -12)
					(= temp1 139)
				else
					(= temp0 -12)
					(= temp1 99)
				)
				(ego x: temp0 y: temp1 setLoop: theStopGroop)
				(self setScript: (ScriptID MALL 1) 0 egoBwGreen)
			)
			(east
				(if (== ((ScriptID MALL 0) whichBelt?) 1)
					(= temp0 334)
					(= temp1 149)
				else
					(= temp0 334)
					(= temp1 119)
				)
				(ego x: temp0 y: temp1 setLoop: theStopGroop)
				(self setScript: (ScriptID MALL 1) 0 egoBwBlue)
			)
			(387
				(music number: 405 loop: -1 flags: 1 play:)
				(ego x: -100 y: -100)
				(self setScript: egoTossedOut 0 0)
			)
			(else 
				(music number: 405 loop: -1 flags: 1 play: 95)
				(ego x: -100 y: -100)
				(if (proc700_3 (ScriptID MALL 0) 681 1)
					(self setScript: egoTossedOut 0)
					(mall rFlag4: (& (mall rFlag4?) $fffe))
				else
					(self setScript: fromStoreScript)
				)
			)
		)
		(burgerPoly points: @burgerPolyPts size: 15)
		(super init:)
		(if (Btst fMonolithBurgerClosed)
			(addToPics add: door eachElementDo: #init doit:)
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 319 108 191 101 185 93 136 92 134 98 0 91 0 0 319 0
						yourself:
					)
			)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 153 65 152 75 138 97 0 91 0 55
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 170 65 319 66 319 108 183 100
						yourself:
					)
			)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 319 165 319 189 0 189 0 149
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 0 105 124 110 139 114 130 133 0 128
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 122 319 144 189 136 181 116 190 114
					yourself:
				)
		)
		(if (and (Btst fPoliceAtMallEntrance) (not (Btst fPoliceInSkateORama)))
			(Load SOUND 105)
			((ScriptID MALL 7)
				posn: 168 118
				view: 13
				setLoop: 0
				setCel: 0
				init:
				setScript: shootEgo
			)
		)
		(ego
			setPri: -1
			code: beltwayCode
			init:
			setCycle: SyncWalk
		)
		(self setRegions: MALL)
		(store init:)
		(bush1 init:)
		(bush2 init:)
	)
	
	(method (doit &tmp temp0)
		(if (not (-- policeTimer))
			(cond 
				(
					(<
						(= temp0 (GetDistance (ego x?) (ego y?) local141 0))
						0
					)
					(= temp0 0)
				)
				((> temp0 300) (= temp0 300))
			)
			(globalSound setVol: (- 127 (/ temp0 3)))
			(= policeTimer 60)
		)
		(cond 
			((curRoom script?) 0)
			((== (ego edgeHit?) 2)
				(HandsOff)
				((ScriptID MALL 0) enterBelt: egoBwGreen)
				(curRoom setScript: (ScriptID MALL 2) 0 east)
			)
			((== (ego edgeHit?) 4)
				(HandsOff)
				((ScriptID MALL 0) enterBelt: egoBwBlue)
				(curRoom setScript: (ScriptID MALL 2) 0 west)
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
			(0
				(ego x: 167 y: 74 setMotion: MoveTo 167 96 self)
			)
			(1
				(proc700_5 1)
				(HandsOn)
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
			((IsObjectOnControl ego 8)
				(if (== (ego view?) 402)
					(curRoom setScript: egoTossedOut 0 1)
				else
					(curRoom newRoom: 386)
				)
			)
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
				(ego xStep: 3 yStep: 2)
				(proc700_5 1)
			)
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
						(< ((ScriptID MALL 9) x?) 220)
					)
					(self init:)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance egoTossedOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LoadMany VIEW 392 385)
				(LoadMany SOUND 836 873 874 134)
				(HandsOff)
				(if (== register 1)
					(ego illegalBits: 0 setMotion: MoveTo 160 42 self)
				else
					(= cycles 1)
				)
			)
			(1
				(if
					(and
						(< 130 ((ScriptID MALL 9) x?))
						(< ((ScriptID MALL 9) x?) 220)
					)
					(self setScript: sDS self)
				else
					(= cycles 1)
				)
			)
			(2
				(ego view: 392 setLoop: register)
				(if (== register 1)
					(tossedSFX play:)
					(ego setCycle: MoveCycle @egoTossedCycle)
					(= cycles 10)
				else
					(tossedSFX play:)
					(ego setCycle: MoveCycle @egoLandCycle)
					(= cycles 10)
				)
			)
			(3
				(egoLandsSFX play:)
				(boss
					init:
					setLoop: 0
					illegalBits: 0
					setCycle: Walk
					setMotion: MoveTo 155 95 self
				)
			)
			(4
				(boss view: 385 loop: 4)
				(pighead
					ignoreActors: TRUE
					setPri: 7
					x: (+ (boss x?) 2)
					y: (- (boss y?) 48)
					z: 0
					init:
				)
				(if (== register 1)
					(tPighead init: 0 0 pighead say: 1 self 2)
				else
					(tPighead
						init: 0 0 pighead
						say: 2 self 2 64 5 5 67 310 27 1
					)
					(Bset fMonolithBurgerFired)
				)
			)
			(5
				(pighead dispose:)
				(if
					(and
						(== register 0)
						(not ((inventory at: iCigar) ownedBy: 400))
						(not (ego has: iCigar))
					)
					(boss view: 385 loop: 2 cel: 0 setCycle: CycleTo 3 1 self)
					((inventory at: iCigar) owner: 400)
					(= register 2)
				else
					(= cycles 1)
				)
				(pighead dispose:)
			)
			(6
				(if (== register 2)
					(boss cel: 4)
					(cigar
						z: 0
						illegalBits: 0
						ignoreActors: 1
						init:
						setCycle: MoveCycle @cigarCycle self
					)
					(headCigarSFX init: play:)
				else
					(= cycles 1)
				)
			)
			(7
				(if (== register 2)
					(groundCigarSFX play:)
					(cigar
						setLoop: 5
						setSpeed: speed
						setMotion: MoveTo 321 148 self
					)
				else
					(= cycles 1)
				)
				(boss
					loop: 1
					cel: 0
					setCycle: Walk
					setMotion: MoveTo 160 42 self
				)
			)
			(8 0)
			(9
				(boss dispose:)
				(if (== register 2) (cigar dispose:))
				(= cycles 1)
			)
			(10 (ego setCycle: EndLoop self))
			(11
				(NormalEgo
					2
					(if (== (ego loop?) 1) 402 else 0)
					(if (== (ego loop?) 1) 14 else 4)
				)
				(= cycles 1)
			)
			(12
				(HandsOn)
				(proc700_5 1)
				(self dispose:)
			)
		)
	)
)

(instance shootEgo of Script
	(properties)
	
	(method (doit)
		(if (and (not state) (not (curRoom script?)))
			(self cue:)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				(HandsOff)
				((ScriptID MALL 7) setCycle: CycleTo 1 1 self)
			)
			(2
				(= blastY (= local136 89))
				(= blastX (- (ego x?) 3))
				(= local137 194)
				(= local139
					(Graph
						GSaveBits
						(- blastY 1)
						(- local137 1)
						(+ local136 1)
						(+ blastX 1)
						1
					)
				)
				(Graph
					GDrawLine
					local136
					local137
					blastY
					blastX
					(VGAOrEGA myTextColor3 myTextColor13)
					(- (ego priority?) 1)
					-1
				)
				(Graph
					GReAnimate
					(- blastY 1)
					(- local137 1)
					(+ local136 1)
					(+ blastX 1)
				)
				(aSound number: 105 loop: 1 play:)
				(= cycles 1)
			)
			(3
				(blast
					init:
					cel: 0
					posn: blastX blastY
					setPri: (+ (ego priority?) 1)
					setCycle: EndLoop
				)
				(ego view: 26 cel: 0 cycleSpeed: 12 setCycle: EndLoop self)
				(Graph GRestoreBits local139)
				(Graph
					GReAnimate
					(- blastY 1)
					(- local137 1)
					(+ local136 1)
					(+ blastX 1)
				)
			)
			(4 (EgoDead iconLASER deathALLOVER))
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
		head 90
		xDir 1
		yDir 1
	)
)

(instance egoBwBlue of BeltWay
	(properties
		xStep 2
		xOff 8
		yOff -1
		xTweak 2
		key 90
		head 270
		xDir -1
		yDir 1
	)
)

(instance tossedSFX of Sound
	(properties
		number 836
	)
)

(instance headCigarSFX of Sound
	(properties
		number 873
	)
)

(instance groundCigarSFX of Sound
	(properties
		number 874
	)
)

(instance egoLandsSFX of Sound
	(properties
		number 134
	)
)

(instance door of Sq4View
	(properties
		x 168
		y 91
		sightAngle 90
		view 385
		loop 6
		priority 1
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 375 say: 1)
			)
			(V_DO (narrator say: 2))
			(V_SMELL (narrator say: 1))
			(V_TASTE (narrator say: 11))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance boss of Sq4Actor
	(properties
		x 160
		y 42
		sightAngle 90
		view 385
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 2))
			(V_SMELL (narrator say: 3))
			(V_TASTE (narrator say: 4))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pighead of Sq4Prop
	(properties
		z 1000
		view 1385
		loop 3
	)
)

(instance cigar of Sq4Actor
	(properties
		z 1000
		sightAngle 90
		yStep 1
		view 385
		loop 5
		xStep 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 5))
			(V_SMELL (narrator say: 6))
			(V_TASTE (narrator say: 6))
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

(instance store of Sq4Feature
	(properties
		x 131
		y 94
		sightAngle 90
	)
	
	(method (init)
		(super init:)
		(self onMeCheck: burgerPoly)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 7))
			(V_SMELL (narrator say: 8))
			(V_TASTE (narrator say: 4))
			(V_DO (narrator say: 9))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bush1 of Sq4Feature
	(properties
		x 55
		y 90
		nsTop 81
		nsLeft 44
		nsBottom 106
		nsRight 63
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 370 say: 12)
			)
			(V_SMELL (narrator say: 10))
			(V_TASTE (narrator say: 11))
			(V_DO
				(narrator modNum: 380 say: 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bush2 of Sq4Feature
	(properties
		x 262
		y 110
		nsTop 93
		nsLeft 256
		nsBottom 118
		nsRight 274
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 370 say: 12)
			)
			(V_SMELL (narrator say: 10))
			(V_TASTE (narrator say: 11))
			(V_DO
				(narrator modNum: 380 say: 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance burgerPoly of Polygon
	(properties)
)

(instance aSound of Sound
	(properties)
)

(instance tPighead of FaceTalker
	(properties
		noun PIGHEAD
		talkerNum PIGHEAD
	)
)

(instance tROG of Sq4Talker
	(properties
		z 400
		noun ROGER
		view 1008
		talkerNum ROGER
		mouthOffsetX 22
		mouthOffsetY 31
		eyeOffsetX 27
		eyeOffsetY 21
	)
)
