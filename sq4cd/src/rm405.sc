;;; Sierra Script 1.0 - (do not remove this comment)
(script# 405)
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
	rm405 0
)

(local
	[skater0Cycle 101] = [0 0 -100 -100 0 0 250 4 0 0 236 16 0 0 223 26 0 0 207 35 0 0 192 45 0 0 178 60 0 1 168 70 0 2 158 74 0 3 153 78 0 4 153 84 0 5 156 93 0 6 166 97 0 7 176 102 0 8 192 104 0 9 206 107 0 9 220 108 0 10 235 113 0 10 253 120 0 10 253 120 0 10 270 130 0 10 283 140 0 10 297 149 0 10 313 161 0 0 -100 -100 -32768]
	[skater3Cycle 165] = [0 0 -100 -100 3 0 220 23 3 0 231 39 3 0 241 50 3 0 248 63 3 0 251 78 3 0 254 94 3 1 254 112 3 2 250 127 3 2 244 142 3 2 236 154 3 2 225 163 3 3 213 169 3 3 199 173 3 3 186 179 3 3 173 181 3 4 158 183 3 4 145 181 3 5 131 177 3 5 116 167 3 6 111 149 3 6 123 129 3 7 141 121 3 7 161 116 3 8 168 118 3 9 156 119 3 10 143 120 3 10 130 116 3 10 116 110 3 11 105 105 3 11 99 95 3 11 100 81 3 11 108 66 3 12 118 55 3 12 132 45 3 13 148 40 3 13 165 27 3 13 180 13 3 13 192 7 3 0 210 10 0 0 -100 -100 -32768]
	underbits
	local267
	local268
	blastX
	blastY
	local271
	local272
	local273
	local274
	local275
	local276
	local277
	local278
)
(instance rm405 of SQRoom
	(properties
		picture 405
		horizon 8
		north 406
		east 410
		west 370
	)
	
	(method (init)
		(HandsOff)
		(LoadMany VIEW 400 403)
		(switch (ego view?)
			(0 (LoadMany VIEW 406 407))
			(402 (LoadMany VIEW 398 411))
		)
		(LoadMany SOUND 0 105 401 406)
		(switch prevRoomNum
			(north
				(self setScript: enterScript 0 north style: FADEOUT)
			)
			(east
				(self setScript: enterScript 0 east style: SCROLLRIGHT)
			)
			(else 
				(music number: 406 loop: -1 vol: 127 flags: mNOPAUSE playBed:)
				(globalSound number: 0 stop:)
				(self setScript: enterScript 0 west style: FADEOUT)
			)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 0 168 16 168 50 189 0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 69 125 41 125 0 111 0 0 44 0 44 112
					yourself:
				)
		)
		(ego init:)
		(super init:)
		(skate0Snd init:)
		(skate3Snd init:)
		(skater0 init: setScript: (Clone skaterScript))
		(skater3 init: setScript: (Clone skaterScript))
		(cond 
			(
			(or (Btst fPoliceAtCeiling) (and (== prevRoomNum 370) (Btst fPoliceInSkateORama)))
				(LoadMany VIEW 408 409 28)
				((ScriptID MALL 6) init: hide: setScript: swimAfterEgo)
			)
			((not (Btst fPoliceAtArcade)) 0)
			((== prevRoomNum 370)
				(LoadMany VIEW 7 13 28 409)
				((ScriptID MALL 6)
					init:
					hide:
					setScript: enterAndShootEgo
				)
			)
			(else
				(LoadMany VIEW 13 28 409)
				((ScriptID MALL 6) init: setScript: shootEgo)
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
					(>= local277 8)
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
						(ego x: (- (ego x?) 4) setMotion: 0)
						(narrator say: 1)
						(HandsOn)
					)
					((OneOf (ego view?) 406 398) (self setScript: endSwimScript))
					(else (self setScript: startSwimScript))
				)
			)
			((IsObjectOnControl ego WEST) (HandsOff) (self setScript: landScript))
			((== (ego edgeHit?) NORTH) (HandsOff) (self setScript: exitScript 0 north))
			((== (ego edgeHit?) EAST) (HandsOff) (self setScript: exitScript 0 east))
			((== (ego edgeHit?) SOUTH) (HandsOff) (self setScript: stayInScript))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(if (sounds contains: skate0Snd) (skate0Snd dispose:))
		(if (sounds contains: skate3Snd) (skate3Snd dispose:))
		(super dispose: &rest)
	)
	
	(method (newRoom newRoomNumber)
		(if
		(and (!= newRoomNumber east) (!= newRoomNumber north))
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
					((curRoom west?)
						(ego x: -20 y: 106 setMotion: MoveTo 22 142 self)
					)
					((curRoom north?)
						(= temp0 (CelHigh (ego view?) (ego loop?) (ego cel?)))
						((ScriptID MALL 4) init: ego)
						(ego
							setCycle: Swim
							setHeading: 180
							x: (if (< (ego x?) 90) 90 else (ego x?))
							y: (- (curRoom horizon?) temp0)
						)
						(Animate (cast elements?) 0)
						(ego setMotion: MoveTo (ego x?) 10 self)
					)
					((curRoom east?)
						(= temp0 (CelWide (ego view?) (ego loop?) (ego cel?)))
						((ScriptID MALL 4)
							init: ego
							inertizing: TRUE
							inertia: 5
							oldDir: 270
							xOff: -1
						)
						(ego
							normal: 0
							setHeading: 270
							setCycle: Swim
							x: (- 319 (+ (/ temp0 2) 1))
							y: (ego y?)
						)
						(= cycles 1)
					)
				)
			)
			(1
				(cond 
					((OneOf (ego view?) 373 374) (= local278 0))
					((OneOf (ego view?) 402 14 398) (= local278 1))
					((OneOf (ego view?) 0 4 406) (= local278 2))
				)
				(switch register
					((curRoom west?)
						(switch local278
							(0 (NormalEgo 0 373 374))
							(1 (NormalEgo 0 402 14))
							(2 (NormalEgo 0 0 4))
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

(instance skater0 of Sq4Prop
	(properties
		x -100
		y -100
		view 400
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
			(V_LOOK (narrator say: 2))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance skater3 of Sq4Prop
	(properties
		x -100
		y -100
		view 400
		loop 3
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
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 2))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance skaterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 10)))
			(1
				(client
					setCycle:
						MoveCycle
						(switch client
							(skater0 @skater0Cycle)
							(skater3 @skater3Cycle)
						)
						self
				)
				(switch client
					(skater0 (skate0Snd play:))
					(skater3 (skate3Snd play:))
				)
			)
			(2
				(switch client
					(skater0 (skate0Snd stop:))
					(skater3 (skate3Snd stop:))
				)
				(self init:)
			)
		)
	)
)

(instance enterAndShootEgo of Script
	(properties)
	
	(method (doit)
		(cond 
			((curRoom script?))
			((and (<= (ego x?) 18) (OneOf state 0 3 6)) (self changeState: 7))
			(
			(and (OneOf (ego view?) 406 407) (OneOf state 0 3 6)) (self changeState: 9))
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(= local267 1)
				(= local268 121)
				(= blastX 54)
				(= blastY 107)
				(= underbits
					(Graph
						GSaveBits
						(- blastY 1)
						local267
						(+ local268 1)
						(+ blastX 1)
						1
					)
				)
				(Graph
					GDrawLine
					local268
					local267
					blastY
					blastX
					(VGAOrEGA myTextColor3 myTextColor13)
					2
					-1
				)
				(Graph
					GReAnimate
					(- blastY 1)
					local267
					(+ local268 1)
					(+ blastX 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(2
				(blast init: posn: blastX blastY setCycle: EndLoop)
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- blastY 1)
					local267
					(+ local268 1)
					(+ blastX 1)
				)
				(= cycles 1)
			)
			(3 (= cycles 16))
			(4
				(= local267 1)
				(= local268 181)
				(= blastX 58)
				(= blastY 108)
				(= underbits
					(Graph
						GSaveBits
						(- blastY 1)
						local267
						(+ local268 1)
						(+ blastX 1)
						1
					)
				)
				(Graph
					GDrawLine
					blastY
					local267
					local268
					blastX
					(VGAOrEGA myTextColor3 myTextColor13)
					2
					-1
				)
				(Graph
					GReAnimate
					(- blastY 1)
					local267
					(+ local268 1)
					(+ blastX 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(5
				(blast cel: 0 posn: blastX local268 setCycle: EndLoop)
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- blastY 1)
					local267
					(+ local268 1)
					(+ blastX 1)
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
					(= local267 1)
					(= blastY (- (ego y?) 36))
					(= blastX (- (ego x?) 3))
					(= local268 (- (ego y?) 32))
					(Graph
						GDrawLine
						local268
						local267
						blastY
						blastX
						(VGAOrEGA myTextColor3 myTextColor13)
						(- (ego priority?) 1)
						-1
					)
					(Graph
						GReAnimate
						(- blastY 1)
						local267
						(+ local268 1)
						(+ blastX 1)
					)
					(aSound number: 105 loop: 1 vol: 127 play:)
					(= cycles 4)
				)
			)
			(8 (EgoDead 8 12))
			(9 (= cycles 20))
			(10
				((ScriptID MALL 6)
					show:
					view: 7
					setCycle: Walk
					setLoop: -1
					setCel: -1
					posn: -7 122
					setMotion: MoveTo 34 135 self
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
				(< (ego x?) 155)
				(> (ego y?) 6)
				(< local277 8)
			)
			(= local277 8)
			(ego setMotion: 0)
			((ScriptID MALL 4) inertia: 0)
			(self cue:)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				((ScriptID MALL 6)
					view: 13
					setMotion: 0
					loop: (if register 0 else 4)
					cel: 0
					posn: 34 135
					init:
				)
				(= seconds 3)
			)
			(1
				(= register (<= (ego y?) 120))
				(++ local277)
				((ScriptID MALL 6)
					loop: (if register 0 else 4)
					cel: 0
					cycleSpeed: 12
					setCycle: CycleTo 1 1 self
				)
			)
			(2
				(if register
					(= local271 54)
					(= local272
						(if (and (<= 105 (ego y?)) (<= (ego y?) 120))
							110
						else
							104
						)
					)
					(= local273 (Min (+ (ego x?) 6) 318))
					(= local274 (Max (- (ego y?) 10) 5))
				else
					(= local271 42)
					(= local272 113)
					(= local273 (Min (+ (ego x?) 6) 318))
					(= local274 (Min (- (ego y?) 8) 188))
				)
				(cond 
					((>= local277 8) (= local275 local273) (= local276 local274))
					(
						(= temp0
							(/ (* (- local272 local274) 100) (- local273 local271))
						)
						(if (< temp0 0) (= local276 188) else (= local276 2))
						(= local275
							(Min
								318
								(+ (/ (* (- local272 local276) 100) temp0) local271)
							)
						)
					)
					(else (= local275 318) (= local276 local272))
				)
				(if (and (> local275 318) temp0)
					(= local275 318)
					(= local276
						(Max
							(-
								188
								(+ (/ (* temp0 (- local275 local271)) 100) local272)
							)
						)
					)
				)
				(if (< local272 local276)
					(= blastY local272)
					(= local268 local276)
				else
					(= blastY local276)
					(= local268 local272)
				)
				(= underbits
					(Graph
						GSaveBits
						(- blastY 1)
						(- local271 1)
						(+ local268 1)
						(+ local275 1)
						1
					)
				)
				(Graph
					GDrawLine
					local272
					local271
					local276
					local275
					(VGAOrEGA myTextColor3 myTextColor13)
					0
					-1
				)
				(Graph
					GReAnimate
					(- blastY 1)
					(- local271 1)
					(+ local268 1)
					(+ local275 1)
				)
				((ScriptID MALL 6) setCycle: EndLoop)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(3
				(if (>= local277 8)
					(HandsOff)
					(ego
						view: 409
						setLoop: 5
						cel: 0
						cycleSpeed: 12
						setCycle: Forward
					)
				)
				((ScriptID MALL 6) cel: 0 stopUpd:)
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- blastY 1)
					(- local271 1)
					(+ local268 1)
					(+ local275 1)
				)
				(if (>= local277 8)
					(blast init: cel: 0 posn: local273 local274 setCycle: EndLoop)
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
			(4 (EgoDead iconLASER deathZAPZAP))
		)
	)
)

(instance swimAfterEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles
					(if (and (== prevRoomNum 370) (Btst 22)) 16 else 120)
				)
			)
			(1
				(if
					(or
						(and
							(not (curRoom script?))
							(< (ego x?) 236)
							(> (ego y?) 30)
						)
						(and (== prevRoomNum 370) (Btst 22))
					)
					(ego setMotion: 0)
					((ScriptID MALL 4) inertia: 0)
					((ScriptID MALL 6)
						show:
						view: 408
						setLoop: 1
						cel: 0
						setCycle: EndLoop
						setStep: 2 2
						posn:
							(if (and (== prevRoomNum 370) (Btst 22)) 318 else 358)
							(- (ego y?) 18)
						setMotion: MoveTo 310 (- (ego y?) 10) self
					)
				else
					(self init:)
				)
			)
			(2
				(HandsOff)
				((ScriptID MALL 6)
					view: 409
					setLoop: 1
					cel: 0
					setCycle: CycleTo 1 1 self
				)
			)
			(3
				(= local272 (- ((ScriptID MALL 6) y?) 4))
				(= local271 (- ((ScriptID MALL 6) x?) 57))
				(= local274 (Max 2 (Min 188 (- (ego y?) 10))))
				(= local273 (Min 318 (ego x?)))
				(if (< local274 local272)
					(= blastY local274)
					(= local268 local272)
				else
					(= blastY local272)
					(= local268 local274)
				)
				(= underbits
					(Graph
						GSaveBits
						(- blastY 1)
						(- local273 1)
						(+ local268 1)
						(+ local271 1)
						1
					)
				)
				(Graph
					GDrawLine
					local272
					local271
					local274
					local273
					(VGAOrEGA myTextColor3 myTextColor13)
					1
					-1
				)
				(Graph
					GReAnimate
					(- blastY 1)
					(- local273 1)
					(+ local268 1)
					(+ local271 1)
					1
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(4
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- blastY 1)
					(- local273 1)
					(+ local268 1)
					(+ local271 1)
					1
				)
				(blast init: cel: 0 posn: local273 local268 setCycle: EndLoop)
				((ScriptID MALL 6) cel: 0)
				(if
					(or
						(ego script?)
						(OneOf (curRoom script?) landScript endSwimScript)
					)
					(ego view: 409 setLoop: 4)
				else
					(ego view: 26)
				)
				(ego cel: 0 setCycle: EndLoop self setMotion: 0)
			)
			(5 (EgoDead iconLASER deathZAPZAP))
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
					(= temp0 90)
				)
				(ego setHeading: temp0 self cel: 0)
			)
			(1
				(switch register
					((curRoom north?)
						(ego setMotion: MoveToY -30 self)
					)
					((curRoom east?)
						(ego setMotion: MoveToX 350 self)
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
				(if (== local278 1) (= temp1 2) else (= temp1 0))
				(ego
					normal: 0
					view: 403
					setLoop: temp1
					cycleSpeed: 18
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(if (== local278 2) (= temp0 406) else (= temp0 398))
				((ScriptID MALL 4) init: ego)
				(ego
					normal: 0
					loop: 0
					setLoop: -1
					view: temp0
					posn: (+ (ego x?) 37) (- (ego y?) 3) 0
					setStep: 3 2
					setSpeed: speed
					setCycle: Swim
					setHeading: 90
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
				(if (== local278 2) (= register 1) else (= register 3))
				(ego
					view: 403
					setMotion: 0
					setCycle: 0
					setLoop: register
					cel: 1
					posn: (+ (ego x?) 12) (+ (ego y?) 5)
				)
				((ScriptID MALL 4) dispose:)
				(= cycles 3)
			)
			(1
				(ego
					setLoop: register
					cel: 0
					posn: (- (ego x?) 20) (+ (ego y?) 4)
				)
				(= cycles 3)
			)
			(2
				(if (== local278 2)
					(= temp0 0)
					(= temp1 4)
				else
					(= temp0 402)
					(= temp1 14)
				)
				(NormalEgo 1 temp0 temp1)
				(proc700_5 1)
				(ego
					posn: (- (ego x?) 17) (+ (ego y?) 4)
					setMotion: PolyPath 24 136 self
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
				(if (== local278 2) (= register 1) else (= register 3))
				(ego
					view: 403
					setMotion: 0
					setCycle: 0
					setLoop: register
					cel: 1
					posn: (+ (ego x?) 12) (+ (ego y?) 5)
				)
				((ScriptID MALL 4) dispose:)
				(= cycles 2)
			)
			(1
				(ego
					setLoop: register
					cel: 0
					posn: (- (ego x?) 20) (+ (ego y?) 4)
				)
				(= cycles 2)
			)
			(2
				(if (== local278 2) (= temp0 0) else (= temp0 402))
				(NormalEgo 1 temp0)
				(ego
					setLoop: 1
					setStep: 6 4
					setCycle: 0
					setMotion: MoveTo 21 132 self
				)
			)
			(3
				(if (== local278 2) (= temp1 4) else (= temp1 14))
				(proc700_5 1)
				(NormalEgo 1 (ego view?) temp1)
				(HandsOn)
				(self dispose:)
			)
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
				(if (> (ego x?) 300) (ego x: 300))
				(if (< (ego x?) 78) (ego x: 78))
				(Animate (cast elements?) FALSE)
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

(instance spinEgo of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(or (IsObjectOnControl ego 4) (IsObjectOnControl ego 2))
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
						;	(0 4)	;EO: these cases already exist, and will probably never be executed
						;	(2 5)
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

(instance blast of Sq4Prop
	(properties
		view 28
	)
)

(instance skate0Snd of Sound
	(properties
		number 401
		priority 15
		loop -1
	)
)

(instance skate3Snd of Sound
	(properties
		number 401
		priority 15
		loop -1
	)
)

(instance skateorama of Sq4Feature
	(properties
		x 75
		y 100
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(return (== (OnControl 4 (event x?) (event y?)) 2048))
	)
)

(instance light of Sq4Feature
	(properties
		x 1
		y 189
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 4))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(return (== (OnControl 4 (event x?) (event y?)) 1024))
	)
)

(instance steps of Sq4Feature
	(properties
		x 280
		y 90
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 5))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(return (& (OnControl 4 (event x?) (event y?)) $1002))
	)
)

(instance wall of Sq4Feature
	(properties
		x 190
		y 80
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 6))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(return
			(cond 
				(
				(& (OnControl 4 (event x?) (event y?)) $e000))
				(
				(& (OnControl 4 (event x?) (event y?)) $0004) (> (event y?) 71))
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
			(1 (narrator say: 7))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe)
		(return TRUE)
	)
)

(instance aSound of Sound
	(properties)
)
