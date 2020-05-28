;;; Sierra Script 1.0 - (do not remove this comment)
(script# 410)
(include game.sh)
(use Main)
(use mall)
(use MoveToCoords)
(use Inertia)
(use SQRoom)
(use Sq4Feature)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use MoveFwd)
(use LoadMany)
(use Sound)
(use Motion)
(use System)

(public
	rm410 0
)

(local
	[skater1Cycle 149] = [0 0 -100 -100 1 0 117 -4 1 0 107 9 1 0 99 20 1 0 91 29 1 0 86 39 1 1 79 47 1 2 71 54 1 2 66 63 1 3 62 72 1 4 63 78 1 4 70 84 1 5 75 87 1 5 84 91 1 5 93 95 1 6 105 97 1 7 117 98 1 7 129 98 1 7 143 95 1 8 157 91 1 8 166 82 1 8 168 73 1 9 167 67 1 10 161 62 1 10 151 59 1 11 143 56 1 11 132 55 1 12 120 53 1 12 108 51 1 12 96 48 1 12 87 44 1 12 76 39 1 12 65 34 1 12 43 23 1 12 32 15 1 12 23 0 0 0 -100 -100 -32768]
	[skater2Cycle 133] = [0 0 -100 -100 2 0 169 -3 2 0 169 9 2 0 170 21 2 0 170 33 2 0 172 45 2 0 173 56 2 0 175 67 2 0 177 78 2 1 179 86 2 1 182 93 2 2 185 100 2 3 190 106 2 4 196 111 2 5 201 113 2 6 206 117 2 7 210 123 2 8 220 129 2 9 228 127 2 10 237 125 2 11 243 121 2 12 249 119 2 13 256 116 2 14 263 108 2 14 267 94 2 14 270 80 2 14 271 65 2 14 271 48 2 14 273 32 2 14 274 14 2 14 277 0 2 14 280 -14 0 0 -100 -100 -32768]
	local282
	underbits
	local284
	local285
	local286
	local287
	local288
	local289
	local290
	local291
	local292
	local293
	local294
)
(instance rm410 of SQRoom
	(properties
		picture 410
		horizon 8
		north 411
		east 390
		west 405
	)
	
	(method (init)
		(HandsOff)
		(LoadMany VIEW 400 403)
		(switch (ego view?)
			(0 (LoadMany VIEW 406 407))
			(402 (LoadMany VIEW 398 411))
		)
		(LoadMany SOUND 0 105 401 406)
		(skate1Snd init:)
		(skate2Snd init:)
		(switch prevRoomNum
			(north
				(self setScript: enterScript 0 north style: 10)
			)
			(west
				(self setScript: enterScript 0 west style: 12)
			)
			(else 
				(music number: 406 loop: -1 vol: 127 flags: 1 playBed:)
				(globalSound number: 0 stop:)
				(self setScript: enterScript 0 east style: 10)
			)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 319 167 319 189 259 189 302 165
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 113 272 126 250 126 250 0 319 0
					yourself:
				)
		)
		(ego init:)
		(super init:)
		(skater1 init: setScript: (Clone skaterScript))
		(skater2 init: setScript: (Clone skaterScript))
		(cond 
			(
			(or (Btst fPoliceAtCeiling) (and (== prevRoomNum 390) (Btst fPoliceInSkateORama)))
				(LoadMany VIEW 408 409 28)
				((ScriptID MALL 6) init: hide: setScript: swimAfterEgo)
			)
			((not (Btst fPoliceAtArcade)))
			((== prevRoomNum 390)
				(LoadMany VIEW 7 13 28 409)
				((ScriptID MALL 7)
					init:
					hide:
					setScript: enterAndShootEgo
				)
			)
			(else
				(LoadMany VIEW 13 28 409)
				((ScriptID MALL 7) init: setScript: shootEgo)
			)
		)
		(self setRegions: MALL)
		(features
			add: skateorama light steps wall restOfMall
			eachElementDo: #init
			doit:
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			(
				(or
					(== (ego view?) 409)
					(>= local282 8)
					(and
						(== ((ScriptID MALL 6) script?) swimAfterEgo)
						(swimAfterEgo state?)
					)
				)
				(ego edgeHit: 0)
			)
			((IsObjectOnControl ego cBLUE)
				(HandsOff)
				(cond 
					((OneOf (ego view?) 373 374)
						(ego x: (+ (ego x?) 4) setMotion: 0)
						(narrator modNum: 405 say: 1)
						(HandsOn)
					)
					((OneOf (ego view?) 406 398) (self setScript: endSwimScript))
					(else (self setScript: startSwimScript))
				)
			)
			((IsObjectOnControl ego cGREEN) (HandsOff) (self setScript: landScript))
			((== (ego edgeHit?) NORTH) (HandsOff) (self setScript: exitScript 0 north))
			((== (ego edgeHit?) WEST) (HandsOff) (self setScript: exitScript 0 west))
			((== (ego edgeHit?) SOUTH) (HandsOff) (self setScript: stayInScript))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(if (sounds contains: skate1Snd) (skate1Snd dispose:))
		(if (sounds contains: skate2Snd) (skate2Snd dispose:))
		(super dispose: &rest)
	)
	
	(method (newRoom newRoomNumber)
		(if
		(and (!= newRoomNumber west) (!= newRoomNumber north))
			(music fade:)
		)
		(if (and (Btst fPoliceAtCeiling) (not (Btst fPoliceInSkateORama)))
			(Bset fPoliceInSkateORama)
			(Bset fSocksClosed)
			(Bset fBigTallClosed)
			(Bset fMonolithBurgerClosed)
			(Bset fSoftwareExcessClosed)
			(Bset fRadioShockClosed)
			(Bclr fArcadeClosed)
		)
		(super newRoom: newRoomNumber)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(switch register
					((curRoom east?)
						(ego x: 331 y: 124 setMotion: MoveTo 297 142 self)
					)
					((curRoom north?)
						(= temp0 (CelHigh (ego view?) (ego loop?) (ego cel?)))
						((ScriptID MALL 4) init: ego)
						(ego
							setCycle: Swim
							setHeading: 180
							x: (if (> (ego x?) 240) 240 else (ego x?))
							y: (- (curRoom horizon?) temp0)
						)
						(Animate (cast elements?) 0)
						(ego setMotion: MoveTo (ego x?) 10 self)
					)
					((curRoom west?)
						(= temp0 (CelWide (ego view?) (ego loop?) (ego cel?)))
						((ScriptID MALL 4)
							init: ego
							inertizing: 1
							inertia: 5
							oldDir: 90
							xOff: 1
						)
						(ego
							normal: 0
							setHeading: 90
							setCycle: Swim
							x: (+ 0 (/ temp0 2) 1)
							y: (ego y?)
						)
						(= cycles 1)
					)
				)
			)
			(1
				(cond 
					((OneOf (ego view?) 373 374) (= local294 0))
					((OneOf (ego view?) 402 14 398) (= local294 1))
					((OneOf (ego view?) 0 4 406) (= local294 2))
				)
				(switch register
					((curRoom east?)
						(switch local294
							(0 (NormalEgo 1 373 374))
							(1 (NormalEgo 1 402 14))
							(2 (NormalEgo 1 0 4))
						)
						(proc700_5 1)
					)
					(else  (proc700_5 0))
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance exitScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				((ScriptID MALL 4) dispose:)
				(if (== register (curRoom north?))
					(= temp0 0)
				else
					(= temp0 270)
				)
				(ego setHeading: temp0 self cel: 0)
			)
			(1
				(switch register
					((curRoom north?)
						(ego setMotion: MoveToY -30 self)
					)
					((curRoom west?)
						(ego setMotion: MoveToX -30 self)
					)
				)
			)
			(2 (curRoom newRoom: register))
		)
	)
)

(instance startSwimScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(if (== local294 1) (= temp1 3) else (= temp1 1))
				(ego
					normal: 0
					view: 403
					setLoop: temp1
					cycleSpeed: 24
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(if (== local294 2) (= temp0 406) else (= temp0 398))
				((ScriptID MALL 4) init: ego)
				(ego
					normal: 0
					loop: 1
					setLoop: -1
					view: temp0
					posn: (- (ego x?) 40) (- (ego y?) 6) 0
					setStep: 3 2
					setCycle: Swim
					setSpeed: speed
					setHeading: 270
					setMotion: MoveFwd 20 self
				)
			)
			(2
				(proc700_5 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance endSwimScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(if (== local294 2) (= register 0) else (= register 2))
				(ego
					view: 403
					setLoop: register
					setMotion: 0
					setCycle: 0
					cel: 1
					posn: (- (ego x?) 25) (+ (ego y?) 6)
				)
				((ScriptID MALL 4) dispose:)
				(= cycles 3)
			)
			(1
				(ego
					setLoop: register
					cel: 0
					posn: (+ (ego x?) 20) (ego y?)
				)
				(= cycles 3)
			)
			(2
				(if (== local294 2)
					(= temp0 0)
					(= temp1 4)
				else
					(= temp0 402)
					(= temp1 14)
				)
				(NormalEgo 0 temp0 temp1)
				(proc700_5 1)
				(ego
					posn: (+ (ego x?) 13) (+ (ego y?) 3)
					setMotion: PolyPath 298 135 self
				)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance landScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(if (== local294 2) (= register 0) else (= register 2))
				(ego
					view: 403
					setMotion: 0
					setCycle: 0
					setLoop: register
					cel: 1
					posn: (- (ego x?) 25) (+ (ego y?) 6)
				)
				((ScriptID MALL 4) dispose:)
				(= cycles 2)
			)
			(1
				(ego
					setLoop: register
					cel: 0
					posn: (+ (ego x?) 20) (ego y?)
				)
				(= cycles 2)
			)
			(2
				(if (== local294 2) (= temp0 0) else (= temp0 402))
				(NormalEgo 0 temp0)
				(ego
					setLoop: 0
					setStep: 6 4
					setCycle: 0
					setMotion: MoveTo 298 135 self
				)
			)
			(3
				(if (== local294 2) (= temp1 4) else (= temp1 14))
				(NormalEgo 0 (ego view?) temp1)
				(proc700_5 1)
				(HandsOn)
				(self dispose:)
			)
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
					(skater1
						(skate1Snd play:)
						(client setCycle: MoveCycle @skater1Cycle self)
					)
					(skater2
						(skate2Snd play:)
						(client setCycle: MoveCycle @skater2Cycle self)
					)
				)
			)
			(2
				(switch client
					(skater1 (skate1Snd stop:))
					(skater2 (skate2Snd stop:))
				)
				(self init:)
			)
		)
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
	)
	
	(method (doit)
		(if
			(and
				(not (curRoom script?))
				(OneOf (ego view?) 406 398)
				(self onMe: ego)
			)
			(curRoom setScript: spinEgo self)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 405 say: 2)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance skater2 of Sq4Actor
	(properties
		x -100
		y -100
		view 400
		loop 2
		priority 2
		signal (| ignrAct ignrHrz fixPriOn)
	)
	
	(method (doit)
		(if
			(and
				(not (curRoom script?))
				(OneOf (ego view?) 323 398)
				(self onMe: ego)
			)
			(curRoom setScript: spinEgo self)
		)
		(if (> x 204) (self setPri: 10) else (self setPri: 2))
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(narrator modNum: 405 say: 2)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance spinEgo of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(or (IsObjectOnControl ego cGREEN) (IsObjectOnControl ego cBLUE))
			(ego setMotion: 0)
			((ScriptID MALL 4) inertia: 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= register (if (> (caller x?) (ego x?)) 1 else -1))
				(= caller 0)
				(ego
					view:
					(switch (ego view?)
						(406 407)
						(398 411)
						(else  407)
					)
					loop: 0
					cel:
						(switch (ego loop?)
							(0 0)
							(1 2)
							(2 1)
							(3 4)
							(4 0)
							(5 2)
							(6 5)
							(7 3)
						)
					cycleSpeed: 6
					setCycle: CycleTo (ego cel?) register self
				)
			)
			(1
				(ego
					setCycle: CycleTo (ego cel?) register self
					cycleSpeed: 12
				)
			)
			(2
				(ego
					setCycle: CycleTo (ego cel?) register self
					cycleSpeed: 18
				)
			)
			(3
				(ego
					view:
					(switch (ego view?)
						(407 406)
						(411 398)
					)
					loop:
						(switch (ego cel?)
							(0 0)
							(2 1)
							(1 2)
							(4 3)
							;(0 4)	;EO: These cases already exist, and will probably never be executed
							;(2 5)
							(5 6)
							(3 7)
						)
					setSpeed: speed
					setCycle: Swim
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterAndShootEgo of Script
	(properties)
	
	(method (doit)
		(cond 
			((curRoom script?))
			((and (>= (ego x?) 298) (OneOf state 0 3 6)) (self changeState: 7))
			(
			(and (OneOf (ego view?) 406 407) (OneOf state 0 3 6)) (self changeState: 9))
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(= local284 259)
				(= local286 319)
				(= local285 125)
				(= local287 110)
				(= underbits
					(Graph
						GSaveBits
						(- local287 1)
						(- local284 1)
						(+ local285 1)
						local286
						1
					)
				)
				(Graph
					GDrawLine
					local285
					local286
					local287
					local284
					(VGAOrEGA myTextColor3 myTextColor13)
					1
					-1
				)
				(Graph
					GReAnimate
					(- local287 1)
					(- local284 1)
					(+ local285 1)
					local286
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(2
				(blast init: posn: local284 local287 setCycle: EndLoop)
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- local287 1)
					(- local284 1)
					(+ local285 1)
					local286
				)
				(= cycles 1)
			)
			(3 (= cycles 16))
			(4
				(= local284 273)
				(= local285 137)
				(= local286 319)
				(= local287 110)
				(= underbits
					(Graph
						GSaveBits
						(- local287 1)
						(- local284 1)
						(+ local285 1)
						local286
						1
					)
				)
				(Graph
					GDrawLine
					local285
					local286
					local287
					local284
					(VGAOrEGA myTextColor3 myTextColor13)
					1
					-1
				)
				(Graph
					GReAnimate
					(- local287 1)
					(- local284 1)
					(+ local285 1)
					local286
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(5
				(blast cel: 0 posn: local284 local287 setCycle: EndLoop)
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- local287 1)
					(- local284 1)
					(+ local285 1)
					local286
				)
				(= cycles 1)
			)
			(6 (= cycles 14))
			(7
				(if (OneOf (ego view?) 406 407 403)
					(self changeState: (+ state 2))
				else
					(HandsOff)
					(ego setMotion: 0)
					(= local286 319)
					(= local287 100)
					(= local284 (+ (ego x?) 3))
					(= local285 (- (ego y?) 30))
					(Graph
						GDrawLine
						local285
						local284
						local287
						local286
						(VGAOrEGA myTextColor3 myTextColor13)
						1
						-1
					)
					(Graph
						GReAnimate
						(- local287 1)
						(- local284 1)
						(+ local285 1)
						local286
					)
					(aSound number: 105 loop: 1 vol: 127 play:)
					(= cycles 4)
				)
			)
			(8 (EgoDead 8 12))
			(9 (= cycles 20))
			(10
				((ScriptID MALL 7)
					show:
					setLoop: -1
					setCel: -1
					view: 7
					setCycle: Walk
					posn: 326 118
					setMotion: MoveTo 299 127 self
				)
			)
			(11
				(client setScript: shootEgo)
			)
		)
	)
)

(instance shootEgo of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(not state)
				(> (ego x?) 154)
				(> (ego y?) 6)
				(< local282 8)
			)
			(= local282 8)
			(ego setMotion: 0)
			((ScriptID MALL 4) inertia: 0)
			(self cue:)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= register (<= (ego y?) 111))
				((ScriptID MALL 7)
					view: 13
					setMotion: 0
					loop: (if register 1 else 5)
					cel: 0
					posn: 299 127
					init:
				)
				(= seconds 3)
			)
			(1
				(= register (<= (ego y?) 111))
				((ScriptID MALL 7)
					loop: (if register 1 else 5)
					cel: 0
					cycleSpeed: 12
					setCycle: CycleTo 1 1 self
				)
			)
			(2
				(++ local282)
				(if register
					(= local288 283)
					(= local289
						(if (and (<= 95 (ego y?)) (<= (ego y?) 111))
							102
						else
							98
						)
					)
					(= local290 (Max (+ (ego x?) 6) 1))
					(= local291 (Max (- (ego y?) 12) 1))
				else
					(= local288 292)
					(= local289 105)
					(= local290 (Max (+ (ego x?) 6) 2))
					(= local291 (Min (- (ego y?) 10) 188))
				)
				(cond 
					((>= local282 8) (= local292 local290) (= local293 local291))
					(
						(= temp0
							(/ (* (- local291 local289) 100) (- local290 local288))
						)
						(if (< temp0 0) (= local293 188) else (= local293 2))
						(= local292
							(Max
								2
								(+ (/ (* (- local293 local289) 100) temp0) local288)
							)
						)
					)
					(else (= local292 2) (= local293 local289))
				)
				(if (and (< local292 0) temp0)
					(= local292 2)
					(= local293
						(Max
							(+ (/ (* temp0 (- local292 local288)) 100) local289)
						)
					)
				)
				(if (< local293 local289)
					(= local287 local293)
					(= local285 local289)
				else
					(= local287 local289)
					(= local285 local293)
				)
				(= underbits
					(Graph
						GSaveBits
						(- local287 1)
						(- local292 1)
						(+ local285 1)
						(+ local288 1)
						1
					)
				)
				(Graph
					GDrawLine
					local289
					local288
					local293
					local292
					(VGAOrEGA myTextColor3 myTextColor13)
					0
					-1
				)
				(Graph
					GReAnimate
					(- local287 1)
					(- local292 1)
					(+ local285 1)
					(+ local288 1)
				)
				((ScriptID MALL 7) setCycle: EndLoop)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(3
				((ScriptID MALL 7) cel: 0 stopUpd:)
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- local287 1)
					(- local292 1)
					(+ local285 1)
					(+ local288 1)
				)
				(if (>= local282 8)
					(blast init: cel: 0 posn: local290 local291 setCycle: EndLoop)
					(ego
						view: 409
						setLoop: 4
						cel: 0
						setCycle: EndLoop self
						setMotion: 0
					)
				else
					(= state 0)
					(= cycles (Random 16 24))
				)
			)
			(4 (EgoDead 8 18))
		)
	)
)

(instance swimAfterEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles
					(if (and (== prevRoomNum 390) (Btst 22)) 16 else 120)
				)
			)
			(1
				(if
					(or
						(and
							(not (curRoom script?))
							(> (ego x?) 85)
							(> (ego y?) 70)
						)
						(and (== prevRoomNum 390) (Btst 22))
					)
					(ego setMotion: 0)
					((ScriptID MALL 4) inertia: 0)
					((ScriptID MALL 6)
						show:
						view: 408
						setLoop: 0
						cel: 0
						setCycle: EndLoop
						setStep: 2 2
						posn:
							(if (and (== prevRoomNum 390) (Btst 22)) 0 else -38)
							(- (ego y?) 18)
						setMotion: MoveTo 10 (- (ego y?) 10) self
					)
				else
					(self changeState: 0)
				)
			)
			(2
				(HandsOff)
				((ScriptID MALL 6)
					view: 409
					setLoop: 0
					cel: 0
					setCycle: CycleTo 1 1 self
				)
			)
			(3
				(= local289 (- ((ScriptID MALL 6) y?) 4))
				(= local288 (+ ((ScriptID MALL 6) x?) 46))
				(= local291 (Max 2 (Min 188 (- (ego y?) 10))))
				(= local290 (Max 2 (ego x?)))
				(if (< local291 local289)
					(= local287 local291)
					(= local285 local289)
				else
					(= local287 local289)
					(= local285 local291)
				)
				(= underbits
					(Graph
						GSaveBits
						(- local287 1)
						(- local288 1)
						(+ local285 1)
						(+ local290 1)
						1
					)
				)
				(Graph
					GDrawLine
					local289
					local288
					local291
					local290
					(VGAOrEGA myTextColor3 myTextColor13)
					1
					-1
				)
				(Graph
					GReAnimate
					(- local287 1)
					(- local288 1)
					(+ local285 1)
					(+ local290 1)
					1
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(4
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- local287 1)
					(- local288 1)
					(+ local285 1)
					(+ local290 1)
					1
				)
				(blast init: cel: 0 posn: local290 local285 setCycle: EndLoop)
				((ScriptID MALL 6) cel: 0)
				(if
					(or
						(ego script?)
						(OneOf (curRoom script?) landScript endSwimScript)
					)
					(ego view: 409 setLoop: 5)
				else
					(ego view: 26)
				)
				(ego cel: 0 setCycle: EndLoop self setMotion: 0)
			)
			(5 (EgoDead 8 18))
		)
	)
)

(instance stayInScript of Script
	(properties)
	
	(method (changeState newState &tmp egoX temp1)
		(switch (= state newState)
			(0
				((ego code?) xOff: 0 yOff: 0 inertia: 0)
				(= egoX (ego x?))
				(= temp1 220)
				(ego illegalBits: 0 setMotion: MoveTo egoX temp1 self)
			)
			(1
				((ego code?) xOff: 0 yOff: 0 inertia: 0)
				(if (> (ego x?) 238) (ego x: 238))
				(if (< (ego x?) 20) (ego x: 20))
				(Animate (cast elements?) 0)
				(= egoX (ego x?))
				(= temp1 185)
				(ego setMotion: MoveTo egoX temp1 self)
			)
			(2
				(ego illegalBits: -32768)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance blast of Sq4Prop
	(properties
		view 28
	)
)

(instance skateorama of Sq4Feature
	(properties
		x 275
		y 100
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(narrator modNum: 405 say: 3)
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

(instance light of Sq4Feature
	(properties
		x 319
		y 189
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 405 say: 4)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe param1)
		(return (== (OnControl 4 (param1 x?) (param1 y?)) 1024))
	)
)

(instance steps of Sq4Feature
	(properties
		x 40
		y 90
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(narrator modNum: 405 say: 5)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe param1)
		(return (& (OnControl 4 (param1 x?) (param1 y?)) $1002))
	)
)

(instance wall of Sq4Feature
	(properties
		x 110
		y 80
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(narrator modNum: 405 say: 6)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe param1)
		(return
			(cond 
				(
				(& (OnControl 4 (param1 x?) (param1 y?)) $e000))
				(
				(& (OnControl 4 (param1 x?) (param1 y?)) $0004) (> (param1 y?) 71))
			)
		)
	)
)

(instance restOfMall of Sq4Feature
	(properties
		x 110
		y 80
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 405 say: 7)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe)
		(return TRUE)
	)
)

(instance skate1Snd of Sound
	(properties
		number 401
		priority 15
		loop -1
	)
)

(instance skate2Snd of Sound
	(properties
		number 401
		priority 15
		loop -1
	)
)

(instance aSound of Sound
	(properties)
)
