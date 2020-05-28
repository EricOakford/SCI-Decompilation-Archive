;;; Sierra Script 1.0 - (do not remove this comment)
(script# 400)
(include game.sh)
(use Main)
(use mall)
(use BeltWay)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use StopWalk)
(use Reverse)
(use Sound)
(use Motion)
(use System)

(public
	rm400 0
)

(local
	[skater0Cycle 93] = [0 0 -100 -100 0 0 241 5 0 0 227 12 0 0 212 16 0 0 194 22 0 0 174 27 0 0 148 32 0 1 123 36 0 2 98 35 0 3 83 38 0 4 71 40 0 5 60 45 0 6 50 46 0 7 42 50 0 8 38 50 0 9 46 49 0 10 57 43 0 10 69 38 0 10 80 32 0 10 93 25 0 10 108 15 0 10 115 6 0 0 -100 -100 -32768]
	[skater1Cycle 109] = [0 0 -100 -100 1 0 184 -6 1 0 179 4 1 0 172 16 1 0 160 24 1 1 149 29 1 1 141 36 1 2 133 43 1 2 129 50 1 3 125 59 1 4 128 66 1 4 139 70 1 4 152 72 1 5 161 73 1 6 176 74 1 6 191 73 1 7 203 71 1 7 216 65 1 7 228 58 1 7 239 53 1 7 250 47 1 7 262 40 1 7 275 32 1 7 288 23 1 7 302 13 1 7 314 7 0 0 -100 -100 -32768]
	[skater2Cycle 81] = [0 0 -100 -100 4 0 26 7 4 0 30 17 4 0 31 26 4 0 33 34 4 1 39 38 4 1 47 39 4 2 55 40 4 3 64 41 4 4 71 41 4 5 78 40 4 6 85 38 4 0 92 36 4 1 97 32 4 2 104 29 4 2 113 25 4 2 121 18 4 2 129 10 4 2 136 4 0 0 -100 -100 -32768]
	local283
	local284
	egoX_2
	egoX_3
	egoX
	local288
	theEgoX_3
	local290
	local291
	local292
	theSpeed
	beltwayControl
	local295
)
(instance rm400 of SQRoom
	(properties
		picture 400
		style FADEOUT
		east 395
		west 370
	)
	
	(method (init)
		(= useSortedFeatures FALSE)
		(HandsOff)
		(LoadMany VIEW 7 400 401)
		(LoadMany SOUND 400 401 405)
		(music number: 405 vol: 127 loop: -1 priority: 1 play:)
		(globalSound number: 0 vol: 0 stop:)
		(switch prevRoomNum
			(west
				(if (== ((ScriptID MALL 0) whichBelt?) 1)
					(= theEgoX_3 -12)
					(= local290 93)
				else
					(= theEgoX_3 -12)
					(= local290 151)
				)
				(ego
					x: theEgoX_3
					y: local290
					setLoop: theStopGroop
					observeControl: 64
				)
				(self setScript: (ScriptID MALL 1) 0 egoBwBlue)
			)
			(east
				(if (== ((ScriptID MALL 0) whichBelt?) 1)
					(= theEgoX_3 331)
					(= local290 93)
				else
					(= theEgoX_3 331)
					(= local290 151)
				)
				(ego
					x: theEgoX_3
					y: local290
					setLoop: theStopGroop
					observeControl: 64
				)
				(self setScript: (ScriptID MALL 1) 0 egoBwCyan)
			)
			(else 
				(music vol: 20 flags: 1 play:)
				(escSnd setVol: 127)
				(self setScript: fromCarScript)
			)
		)
		(if ((inventory at: iCigar) ownedBy: 400) (cigar init:))
		(if
			(and
				(proc700_3 (ScriptID MALL 0) 678 64)
				(not (ego has: 10))
			)
			(card init: setCel: 6)
		)
		(super init:)
		(if (!= prevRoomNum 365)
			(ego
				setPri: -1
				code: beltwayCode
				init:
				setCycle: SyncWalk
			)
		)
		(if
		(or (== prevRoomNum east) (== prevRoomNum west))
			(skater0 init: setScript: (Clone skaterScript))
			(if (> (theGame detailLevel:) 1)
				(skater1 init: setScript: (Clone skaterScript))
				(if (>= (theGame detailLevel:) 3)
					(skater2 init: setScript: (Clone skaterScript))
				)
			)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 209 189 209 176 219 176 219 171 318 171 319 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 131 237 134 232 130 220 130 213 114 219 112 222 108 319 98
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 81 224 88 205 93 126 93 88 87 0 78 0 0 319 0
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 0 98 92 107 93 112 97 115 91 130 81 130 75 137 0 134
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 0 170 95 170 95 176 101 176 101 189 0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 131 189 131 175 181 175 181 189
					yourself:
				)
		)
		((ScriptID MALL 6)
			view: 7
			loop: 3
			cel: 0
			x: 196
			y: 248
			init:
			stopUpd:
		)
		(cond 
			((or (Btst fPoliceInSkateORama) (Btst fPoliceAtMallEntrance))
				(Load SOUND 105)
				(LoadMany VIEW 28 13 26)
				(Load FONT 68 69)
				((ScriptID MALL 6) setScript: plugEgo)
			)
			((Btst fPoliceAtArcade)
				(Load SOUND 105)
				(LoadMany VIEW 28 13 26)
				(Load FONT 68 69)
				(Bset fPoliceAtMallEntrance)
				(Bset fSoftwareExcessClosed)
				(if (== prevRoomNum 370)
					((ScriptID MALL 6) setScript: twoSPright)
				else
					((ScriptID MALL 6) setScript: twoSPleft)
				)
			)
		)
		(lator1 init:)
		(lator2 init:)
		(if
		(or (== prevRoomNum east) (== prevRoomNum west))
			(lator1 setScript: escScript setCycle: Forward)
			(lator2 setCycle: Reverse)
			(escSnd init:)
			(escSnd setVol: 80)
		)
		(self setRegions: MALL)
		(= beltwayControl 30)
		(= local295 96)
		(bush1 init:)
		(bush2 init:)
		(belts init:)
		(skateorama init:)
		(island1 init:)
		(island2 init:)
	)
	
	(method (doit &tmp [temp0 3])
		(cond 
			(script 0)
			(
				(and
					(ego edgeHit?)
					(OneOf (ego edgeHit?) EAST WEST)
					(not ((ScriptID MALL 7) script?))
					(!= (ego view?) 26)
				)
				(HandsOff)
				((ScriptID MALL 0)
					enterBelt: (if (== (ego edgeHit?) 2) egoBwRed else egoBwGreen)
				)
				(self
					setScript:
						(ScriptID MALL 2)
						0
						(if (== (ego edgeHit?) 2) east else west)
				)
			)
		)
		(if
			(and
				(not ((ScriptID MALL 7) script?))
				(!= (ego view?) 26)
			)
			(super doit:)
		)
	)
	
	(method (dispose)
		(= useSortedFeatures TRUE)
		(DisposeScript 804)
		(super dispose:)
	)
)

(instance beltwayCode of Code
	(properties)
	
	(method (doit &tmp temp0 temp1 temp2)
		(cond 
			((curRoom script?) 0)
			((& (= temp1 (ego onControl: origin)) beltwayControl)
				(cond 
					((& temp1 $0004)
						(egoBwGreen who: ego doit:)
						((ScriptID MALL 0) whichBelt: 1)
					)
					((& temp1 $0002)
						(egoBwBlue who: ego doit:)
						((ScriptID MALL 0) whichBelt: 2)
					)
					((& temp1 $0008)
						(egoBwCyan who: ego doit:)
						((ScriptID MALL 0) whichBelt: 1)
					)
					((& temp1 $0010)
						(egoBwRed who: ego doit:)
						((ScriptID MALL 0) whichBelt: 2)
					)
				)
				(proc700_5 0)
			)
			(
				(|
					(egoBwGreen onCon?)
					(egoBwBlue onCon?)
					(egoBwCyan onCon?)
					(egoBwRed onCon?)
				)
				(egoBwGreen onCon: 0)
				(egoBwBlue onCon: 0)
				(egoBwCyan onCon: 0)
				(egoBwRed onCon: 0)
				(ego xStep: 3 yStep: 2 setPri: -1)
				(proc700_5 1)
			)
			((& temp1 local295)
				(curRoom
					setScript: (if (& temp1 $0020) downLatorScript else joyRiding)
				)
			)
		)
	)
)

(instance egoBwGreen of BeltWay
	(properties
		xStep 2
		xOff 2
		yOff -1
		xTweak 2
		key 90
		head 277
		xDir -1
		yDir 1
	)
)

(instance egoBwCyan of BeltWay
	(properties
		xStep 2
		xOff 2
		yOff 1
		xTweak 2
		key 90
		head 262
		xDir -1
		yDir 1
	)
)

(instance egoBwBlue of BeltWay
	(properties
		xStep 2
		xOff 2
		yOff -1
		xTweak 2
		key 270
		head 96
		xDir 1
		yDir -1
	)
)

(instance egoBwRed of BeltWay
	(properties
		xStep 2
		xOff 2
		yOff 1
		xTweak 2
		key 270
		head 86
		xDir 1
		yDir -1
		tixCnt 6
	)
)

(instance skater0 of Sq4Prop
	(properties
		x -100
		y -100
		view 400
		priority 2
		signal (| ignrAct ignrHrz fixPriOn)
		lookStr 1
	)
)

(instance skater1 of Sq4Prop
	(properties
		x -100
		y -100
		view 400
		loop 1
		priority 2
		signal (| ignrAct ignrHrz fixPriOn)
		lookStr 1
	)
)

(instance skater2 of Sq4Prop
	(properties
		x -100
		y -100
		view 400
		loop 2
		priority 2
		signal (| ignrAct ignrHrz fixPriOn)
		lookStr 1
	)
)

(instance lator1 of Sq4Prop
	(properties
		x 116
		y 189
		view 401
		priority 4
		signal (| ignrAct fixPriOn)
		lookStr 2
	)
)

(instance escScript of Script
	(properties)
	
	(method (doit &tmp temp0)
		(if (not (lator1 cel?)) (escSnd play:))
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
		)
	)
)

(instance escSnd of Sound
	(properties
		number 400
	)
)

(instance lator2 of Sq4Prop
	(properties
		x 196
		y 189
		view 401
		priority 4
		signal (| ignrAct fixPriOn)
		lookStr 3
	)
)

(instance blast of Sq4Prop
	(properties
		view 28
	)
)

(instance cigar of Sq4View
	(properties
		x 220
		y 114
		view 401
		loop 5
		signal (| ignrAct fixPriOn)
		lookStr 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(HandsOff)
				(proc700_5 1)
				(curRoom setScript: getSomethingScript 0 self)
			)
			(6 (narrator say: 5))
			(7 (narrator say: 6))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance card of Sq4Prop
	(properties
		x 167
		y 151
		sightAngle 90
		view 401
		loop 4
		priority 1
		signal (| ignrAct fixedLoop fixPriOn)
		lookStr 7
	)
	
	(method (init)
		(super init: &rest)
		(self doit:)
	)
	
	(method (doit)
		(super doit:)
		(if (!= (self cel?) 6)
			(self setPri: -1 x: (+ (self x?) 2))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(HandsOff)
				(proc700_5 1)
				(curRoom setScript: getSomethingScript 0 self)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance getSomethingScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego signal?) $0400)
			(narrator say: 8)
			(HandsOn)
			(self dispose:)
		)
	)
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0
				(Face ego register)
				(= temp0
					(-
						(GetDistance
							(ego x?)
							(ego y?)
							(register x?)
							(register y?)
						)
						10
					)
				)
				(= temp1 (+ (ego x?) (SinMult (ego heading?) temp0)))
				(= temp2 (- (ego y?) (CosMult (ego heading?) temp0)))
				(ego ignoreActors: 1 setMotion: PolyPath temp1 temp2 self)
			)
			(1
				(switch register
					(card
						(narrator say: 9)
						(ego get: IAtmCard)
						(SolvePuzzle fGotATMCard 2)
					)
					(cigar
						(narrator say: 10)
						(ego get: iCigar)
						(SolvePuzzle fGotCigar 5)
					)
				)
				(HandsOn)
				(ego ignoreActors: FALSE)
				(register dispose:)
				(self dispose:)
			)
		)
	)
)

(instance joyRiding of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 10))
			(1
				(= local292 ((ego cycler?) vStopped?))
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 3) self)
			)
			(2
				(ego setHeading: 180 setCycle: Forward)
				(= seconds 3)
			)
			(3
				(ego
					setHeading: 90
					setCycle: StopWalk local292
					setMotion: MoveTo (ego x?) (- (ego y?) 5) self
				)
			)
			(4
				(narrator say: 11)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance babe1 of Sq4Actor
	(properties
		x 195
		y 248
		view 401
		loop 1
		signal (| ignrAct ignrHrz fixedLoop)
		illegalBits $0000
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: 0 setMotion: MoveTo (self x?) 158)
	)
)

(instance babe2 of Sq4Actor
	(properties
		x 186
		y 255
		view 401
		loop 1
		cel 1
		signal (| ignrAct ignrHrz fixedLoop)
		illegalBits $0000
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: 0 setMotion: MoveTo (self x?) 166)
	)
)

(instance babe3 of Sq4Actor
	(properties
		x 204
		y 255
		view 401
		loop 1
		cel 2
		signal (| ignrAct ignrHrz fixedLoop)
		illegalBits $0000
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: 0 setMotion: MoveTo (self x?) 167)
	)
)

(instance fromCarScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= theSpeed speed)
				(ego
					x: 196
					y: 262
					setPri: -1
					code: beltwayCode
					init:
					setCycle: SyncWalk
				)
				(= cycles 2)
			)
			(1
				(music fade: 127 10 5 0)
				(NormalEgo 0 402)
				(ego setSpeed: 6)
				(EgoHeadMove 14)
				(ego x: -100 y: -100)
				(= cycles 10)
			)
			(2
				(lator1 setScript: escScript init: setCycle: Forward)
				(lator2 init: setCycle: Reverse)
				(escSnd init:)
				(escSnd setVol: 127)
				(babe1 init:)
				(babe2 init:)
				(babe3 init:)
				(ego
					x: 196
					y: 262
					init:
					setCycle: 0
					setMotion: MoveTo 196 172 self
				)
			)
			(3
				(NormalEgo 3 402)
				(ego setSpeed: speed)
				(ego observeControl: 64 setCycle: StopWalk 14)
				(proc700_5 1)
				(= cycles 3)
			)
			(4
				(skater0 init: setScript: (Clone skaterScript))
				(= seconds 2)
			)
			(5
				(babe1 setLoop: 2 setCycle: EndLoop self)
			)
			(6 (= seconds 1))
			(7
				(tBabes say: 1 self 2 64 190 100 27 1)
			)
			(8
				(babe1
					setLoop: 3
					setCel: -1
					setStep: 5 3
					setCycle: Walk
					setMotion: PolyPath 80 109 self
				)
				(babe2 dispose:)
				(babe3 dispose:)
				(= cycles 3)
			)
			(9
				(card
					init:
					setCycle: EndLoop
					x: (- (babe1 x?) 23)
					y: (babe1 y?)
				)
				(mall rFlag1: (| (mall rFlag1?) $0040))
			)
			(10
				(card setPri: 1)
				(babe1 setStep: 7 3 setMotion: MoveTo -40 111 self)
			)
			(11
				(ego setSpeed: theSpeed)
				(babe1 dispose:)
				(escSnd setVol: 80)
				(if (> (theGame detailLevel:) 1)
					(skater1 init: setScript: (Clone skaterScript))
					(if (>= (theGame detailLevel:) 3)
						(skater2 init: setScript: (Clone skaterScript))
					)
				)
				(HandsOn)
				(theGame setCursor: 850 TRUE)
				(theIconBar curIcon: (theIconBar at: ICON_WALK))
				(ego setSpeed: speed)
				(self dispose:)
			)
		)
	)
)

(instance plugEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID MALL 6) posn: 160 (ego y?) loop: 2 cel: 2)
				(= cycles 10)
			)
			(1
				(= register (< (ego x?) ((ScriptID MALL 6) x?)))
				((ScriptID MALL 6)
					view: 13
					loop: (if register 1 else 0)
					cel: 0
					setCycle: CycleTo 1 1 self
				)
			)
			(2
				(HandsOff)
				(ego setMotion: 0)
				(= local290 (- ((ScriptID MALL 6) y?) 28))
				(= theEgoX_3 (if register 144 else 176))
				(= local288 (- (ego y?) 31))
				(if (< (= egoX (ego x?)) theEgoX_3)
					(= egoX_2 egoX)
					(= egoX_3 theEgoX_3)
				else
					(= egoX_2 theEgoX_3)
					(= egoX_3 egoX)
				)
				(if (< local288 local290)
					(= local283 local288)
					(= local284 local290)
				else
					(= local283 local290)
					(= local284 local288)
				)
				(= local291
					(Graph
						GSaveBits
						(- local283 1)
						(- egoX_2 1)
						(+ local284 1)
						(+ egoX_3 1)
						1
					)
				)
				(Graph
					GDrawLine
					local290
					theEgoX_3
					local288
					egoX
					(VGAOrEGA myTextColor3 myTextColor13)
					(- (ego priority?) 1)
					-1
				)
				(Graph
					GReAnimate
					(- local283 1)
					(- egoX_2 1)
					(+ local284 1)
					(+ egoX_3 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(3
				(Graph GRestoreBits local291)
				(Graph
					GReAnimate
					(- local283 1)
					(- egoX_2 1)
					(+ local284 1)
					(+ egoX_3 1)
				)
				(blast
					init:
					posn: egoX local288
					cel: 0
					setPri: (+ (ego priority?) 1)
					setCycle: EndLoop self
				)
				(ego view: 26 cel: 0 cycleSpeed: 12 setCycle: EndLoop)
				((ScriptID MALL 6) cel: 0)
			)
			(4 (EgoDead iconLASER deathMALLSECURED))
		)
	)
)

(instance twoSPright of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			((and (not state) (> (ego x?) 90)) (self cue:))
			(
				(and
					(< state 10)
					(or
						(and (> (ego y?) 174) ((ScriptID MALL 6) loop: 2))
						(> (ego x?) 118)
					)
				)
				(= cycles 0)
				(self changeState: 10)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID MALL 7)
					posn: 229 163
					loop: 2
					cel: 2
					init:
					stopUpd:
				)
				((ScriptID MALL 6)
					posn: 213 106
					view: 13
					loop: 0
					cel: 0
					stopUpd:
				)
			)
			(1
				((ScriptID MALL 7) loop: 1 forceUpd:)
				((ScriptID MALL 6) loop: 3 forceUpd:)
				(= cycles 6)
			)
			(2
				(tSP1
					say:
						1
						self
						2
						64
						-1
						100
						25
						myTopBordColor
						26
						(VGAOrEGA myTextColor9 myTextColor19)
						27
						1
				)
				((ScriptID MALL 6) loop: 1 forceUpd:)
				((ScriptID MALL 7) setScript: uStayIGo)
				(= seconds 2)
			)
			(3 (= cycles 5))
			(4
				((ScriptID MALL 6) setCycle: CycleTo 1 1 self)
			)
			(5
				(= local283 74)
				(= local284 95)
				(= egoX_2 4)
				(= egoX_3 186)
				(= local291
					(Graph
						GSaveBits
						(- local283 1)
						(- egoX_2 1)
						(+ local284 1)
						(+ egoX_3 1)
						1
					)
				)
				(Graph
					GDrawLine
					local283
					egoX_3
					local284
					egoX_2
					(VGAOrEGA myTextColor3 myTextColor13)
					3
					-1
				)
				(Graph
					GReAnimate
					(- local283 1)
					(- egoX_2 1)
					(+ local284 1)
					(+ egoX_3 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(6
				((ScriptID MALL 6) setCycle: EndLoop)
				(blast init: posn: 4 95 setCycle: EndLoop)
				(Graph GRestoreBits local291)
				(Graph
					GReAnimate
					(- local283 1)
					(- egoX_2 1)
					(+ local284 1)
					(+ egoX_3 1)
				)
				(= local291 0)
				(= cycles 18)
			)
			(7
				((ScriptID MALL 6) cel: 0 setCycle: CycleTo 1 1 self)
			)
			(8
				(= local283 74)
				(= local284 78)
				(= egoX_2 74)
				(= egoX_3 186)
				(= local291
					(Graph
						GSaveBits
						(- local283 1)
						(- egoX_2 1)
						(+ local284 1)
						(+ egoX_3 1)
						1
					)
				)
				(Graph
					GDrawLine
					local284
					egoX_3
					local283
					egoX_2
					(VGAOrEGA myTextColor3 myTextColor13)
					3
					-1
				)
				(Graph
					GReAnimate
					(- local283 1)
					(- egoX_2 1)
					(+ local284 1)
					(+ egoX_3 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(9
				((ScriptID MALL 6) setCycle: EndLoop)
				(blast posn: 73 74 setPri: 6 setCycle: EndLoop)
				(Graph GRestoreBits local291)
				(Graph
					GReAnimate
					(- local283 1)
					(- egoX_2 1)
					(+ local284 1)
					(+ egoX_3 1)
				)
				(= local291 0)
				(= cycles 15)
			)
			(10
				((ScriptID MALL 6) cel: 0 setCycle: CycleTo 1 1 self)
			)
			(11
				(HandsOff)
				(ego setMotion: 0)
				(= local283 (Min (- (ego y?) 31) 75))
				(= local284 (Max (- (ego y?) 31) 75))
				(= egoX_2 (ego x?))
				(= egoX_3 186)
				(= local291
					(Graph
						GSaveBits
						(- local283 1)
						(- egoX_2 1)
						(+ local284 1)
						(+ egoX_3 1)
						1
					)
				)
				(if (== local283 75)
					(Graph
						GDrawLine
						local283
						egoX_3
						local284
						egoX_2
						(VGAOrEGA myTextColor3 myTextColor13)
						(- (ego priority?) 1)
						-1
					)
				else
					(Graph
						GDrawLine
						local284
						egoX_3
						local283
						egoX_2
						(VGAOrEGA myTextColor3 myTextColor13)
						(- (ego priority?) 1)
						-1
					)
				)
				(Graph
					GReAnimate
					(- local283 1)
					(- egoX_2 1)
					(+ local284 1)
					(+ egoX_3 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(12
				(Graph GRestoreBits local291)
				(Graph
					GReAnimate
					(- local283 1)
					(- egoX_2 1)
					(+ local284 1)
					(+ egoX_3 1)
				)
				(ego view: 26 cel: 0 cycleSpeed: 12 setCycle: EndLoop self)
			)
			(13 (EgoDead iconLASER deathDILLYDALLY))
		)
	)
)

(instance twoSPleft of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			((and (not state) (< (ego x?) 228)) (self cue:))
			((and (< (ego x?) 202) (< state 10)) (= cycles 0) (self changeState: 10))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID MALL 7)
					posn: 84 161
					loop: 2
					cel: 2
					init:
					stopUpd:
				)
				((ScriptID MALL 6)
					posn: 102 141
					view: 13
					loop: 1
					cel: 0
					stopUpd:
				)
			)
			(1
				((ScriptID MALL 7) loop: 0 forceUpd:)
				((ScriptID MALL 6) loop: 3 forceUpd:)
				(= cycles 4)
			)
			(2
				((ScriptID MALL 7) stopUpd:)
				((ScriptID MALL 6) loop: 0 forceUpd:)
				(tSP1
					say:
						1
						self
						2
						64
						-1
						100
						25
						myTopBordColor
						26
						(VGAOrEGA myTextColor9 myTextColor19)
						27
						1
				)
				((ScriptID MALL 7) setScript: uStayIGo)
			)
			(3 (= cycles 8))
			(4
				((ScriptID MALL 6) setCycle: CycleTo 1 1 self)
			)
			(5
				(= local283 109)
				(= local284 111)
				(= egoX_2 124)
				(= egoX_3 254)
				(= local291
					(Graph
						GSaveBits
						(- local283 1)
						(- egoX_2 1)
						(+ local284 1)
						(+ egoX_3 1)
						1
					)
				)
				(Graph
					GDrawLine
					local284
					egoX_2
					local283
					egoX_3
					(VGAOrEGA myTextColor3 myTextColor13)
					3
					-1
				)
				(Graph
					GReAnimate
					(- local283 1)
					(- egoX_2 1)
					(+ local284 1)
					(+ egoX_3 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(6
				((ScriptID MALL 6) setCycle: EndLoop)
				(blast init: posn: 254 109 setPri: 7 setCycle: EndLoop)
				(Graph GRestoreBits local291)
				(Graph
					GReAnimate
					(- local283 1)
					(- egoX_2 1)
					(+ local284 1)
					(+ egoX_3 1)
				)
				(= local291 0)
				(= cycles 24)
			)
			(7
				((ScriptID MALL 6) cel: 0 setCycle: CycleTo 1 1 self)
			)
			(8
				(= local283 107)
				(= local284 111)
				(= egoX_2 124)
				(= egoX_3 279)
				(= local291
					(Graph
						GSaveBits
						(- local283 1)
						(- egoX_2 1)
						(+ local284 1)
						(+ egoX_3 1)
						1
					)
				)
				(Graph
					GDrawLine
					local284
					egoX_2
					local283
					egoX_3
					(VGAOrEGA myTextColor3 myTextColor13)
					3
					-1
				)
				(Graph
					GReAnimate
					(- local283 1)
					(- egoX_2 1)
					(+ local284 1)
					(+ egoX_3 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(9
				((ScriptID MALL 6) setCycle: EndLoop)
				(blast posn: 279 107 setPri: 6 setCycle: EndLoop self)
				(Graph GRestoreBits local291)
				(Graph
					GReAnimate
					(- local283 1)
					(- egoX_2 1)
					(+ local284 1)
					(+ egoX_3 1)
				)
				(= local291 0)
				(= cycles 16)
			)
			(10
				((ScriptID MALL 6)
					loop: (if (> (ego x?) 161) 0 else 3)
					cel: 0
					setCycle: CycleTo 1 1 self
				)
			)
			(11
				(HandsOff)
				(ego setMotion: 0)
				(= local283 (Min (- (ego y?) 31) 108))
				(= local284 (Max (- (ego y?) 31) 108))
				(= egoX_3 (ego x?))
				(= egoX_2 119)
				(= local291
					(Graph
						GSaveBits
						(- local283 1)
						(- egoX_2 1)
						(+ local284 1)
						(+ egoX_3 1)
						1
					)
				)
				(if (== local283 108)
					(Graph
						GDrawLine
						local283
						egoX_2
						local284
						egoX_3
						(VGAOrEGA myTextColor3 myTextColor13)
						(- (ego priority?) 1)
						-1
					)
				else
					(Graph
						GDrawLine
						local284
						egoX_2
						local283
						egoX_3
						(VGAOrEGA myTextColor3 myTextColor13)
						(- (ego priority?) 1)
						-1
					)
				)
				(Graph
					GReAnimate
					(- local283 1)
					(- egoX_2 1)
					(+ local284 1)
					(+ egoX_3 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(12
				(Graph GRestoreBits local291)
				(Graph
					GReAnimate
					(- local283 1)
					(- egoX_2 1)
					(+ local284 1)
					(+ egoX_3 1)
				)
				(ego view: 26 cel: 0 cycleSpeed: 12 setCycle: EndLoop self)
			)
			(13 (EgoDead iconLASER deathDILLYDALLY))
		)
	)
)

(instance uStayIGo of Script
	(properties)
	
	(method (doit)
		(if
		(and (not state) (ego edgeHit?) (!= (ego view?) 26))
			(self cue:)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local291
					(Graph GRestoreBits local291)
					(Graph
						GReAnimate
						(- local283 1)
						(- egoX_2 1)
						(+ local284 1)
						(+ egoX_3 1)
					)
				)
				((ScriptID MALL 6) cel: 0)
			)
			(1
				(HandsOff)
				(ego hide:)
				((ScriptID MALL 6) view: 7 loop: 2 cel: 2 setScript: 0)
				((ScriptID MALL 7) forceUpd: loop: 3)
				(= seconds 3)
			)
			(2
				(tSP2
					say:
						1
						self
						2
						64
						-1
						40
						25
						myTopBordColor
						26
						(VGAOrEGA myTextColor9 myTextColor19)
						27
						1
				)
			)
			(3 (= cycles 2))
			(4
				(tSP1
					say:
						3
						self
						2
						64
						-1
						40
						25
						myTopBordColor
						26
						(VGAOrEGA myTextColor9 myTextColor19)
						27
						1
				)
			)
			(5
				((ScriptID MALL 6)
					setCycle: Walk
					setMotion: MoveTo 160 ((ScriptID MALL 6) y?) self
				)
			)
			(6
				(curRoom setScript: 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance downLatorScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(= temp0 (CelHigh (ego view?) (ego loop?) (ego cel?)))
				(ego setCycle: 0 looper: 0)
				(= cycles 1)
			)
			(1
				(ego setMotion: MoveTo (ego x?) 250 self)
			)
			(2 (= seconds 2))
			(3
				(if modelessDialog (modelessDialog dispose:))
				(ego
					setLoop: 2
					posn: 196 248
					ignoreControl: 64
					setMotion: MoveTo 196 177 self
				)
			)
			(4
				((ScriptID MALL 6)
					illegalBits: 0
					setCycle: 0
					setLoop: 3
					setMotion: MoveTo 196 172 self
				)
				(ego setCycle: Reverse setMotion: MoveTo 196 155 self)
			)
			(5 (ego setCycle: 0) 0)
			(6 (EgoDead iconLASER deathLEAVEMALL))
		)
	)
)

(instance skaterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 10)))
			(1
				(switch client
					(skater0
						(client setCycle: MoveCycle @skater0Cycle self)
					)
					(skater1
						(client setCycle: MoveCycle @skater1Cycle self)
					)
					(skater2
						(client setCycle: MoveCycle @skater2Cycle self)
					)
				)
			)
			(2 (self init:))
		)
	)
)

(instance skateorama of Sq4Feature
	(properties
		x 159
		y 64
		z 24
		lookStr 12
	)
	
	(method (onMe event)
		(return (== (OnControl cGREEN (event x?) (event y?)) 8192))
	)
)

(instance island1 of Sq4Feature
	(properties
		x 41
		y 123
		z 10
		nsTop 96
		nsBottom 130
		nsRight 82
		lookStr 14
	)
)

(instance island2 of Sq4Feature
	(properties
		x 278
		y 112
		nsTop 95
		nsLeft 238
		nsBottom 130
		nsRight 319
		lookStr 14
	)
)

(instance belts of Sq4Feature
	(properties
		sightAngle 180
		lookStr 15
	)
	
	(method (onMe event)
		(return (& (OnControl cGREEN (event x?) (event y?)) $001e))
	)
)

(instance bush1 of Sq4Feature
	(properties
		x 17
		y 100
		nsTop 96
		nsLeft 13
		nsBottom 108
		nsRight 26
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 370 say: 12)
			)
			(V_SMELL
				(narrator modNum: 370 say: 13)
			)
			(V_TASTE
				(narrator modNum: 370 say: 14)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bush2 of Sq4Feature
	(properties
		x 294
		y 102
		nsTop 94
		nsLeft 288
		nsBottom 108
		nsRight 304
	)
	
	(method (doVerb theVerb)
		(bush1 doVerb: theVerb &rest)
	)
)

(instance aSound of Sound
	(properties)
)

(instance tSP1 of Sq4Talker
	(properties
		z 400
		noun SP4
		view 1016
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
		mouthOffsetX 22
		mouthOffsetY 34
	)
)

(instance tBabes of Sq4Narrator
	(properties
		noun BABES
		talkerNum BABES
	)
)
