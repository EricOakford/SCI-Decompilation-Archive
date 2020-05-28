;;; Sierra Script 1.0 - (do not remove this comment)
(script# 376)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use PolyPath)
(use Polygon)
(use Sound)
(use Motion)
(use System)

(public
	rm376 0
)

(local
	local0
	underBits
	blastY
	egoX
	egoY
	local5
	local6
	[theKid 7]
	local14
	oldDetail
)
(instance rm376 of SQRoom
	(properties
		picture 376
		style FADEOUT
		south 375
	)
	
	(method (init)
		(= oldDetail (theGame detailLevel:))
		(HandsOn)
		(if (not (Btst fTimeTravel))
			(self setRegions: MALL)
		else
			(DisposeScript MALL)
		)
		(if (and local0 (Btst 29)) (Load SOUND 105))
		(Load VIEW 376)
		(if (>= msAstroChickenPlays 5) (screen1 setCel: 2))
		(screen1 init:)
		(screen2 init: setCycle: Forward)
		(screen3 init: setCycle: Forward)
		(screen4 init: setCycle: Forward)
		(screen5 init: setPri: 12 setCycle: Forward)
		(screen6 init:)
		(screen7 init:)
		(if (> (theGame detailLevel:) 2)
			(screen6 setCycle: Forward)
			(screen7 setCycle: Forward)
		)
		(screen10 init: setCycle: Forward)
		(= [theKid 0] kid)
		((= [theKid 1] (kid new:))
			loop: 8
			x: 197
			y: 175
			cycleSpeed: 12
			init:
		)
		((= [theKid 2] (kid new:))
			loop: 9
			x: 240
			y: 134
			cycleSpeed: 18
			init:
		)
		((= [theKid 3] (kid new:))
			loop: 10
			x: 97
			y: 114
			cycleSpeed: 24
			init:
		)
		((= [theKid 4] (kid new:))
			loop: 11
			x: 80
			y: 128
			cycleSpeed: 6
			priority: 7
			signal: (| ignrAct fixPriOn)
			init:
		)
		((= [theKid 5] (kid new:))
			loop: 12
			x: 118
			y: 172
			cycleSpeed: 12
			init:
		)
		((= [theKid 6] (kid new:))
			loop: 13
			x: 285
			y: 137
			cycleSpeed: 24
			init:
		)
		(kid init:)
		(basket init: addToPic:)
		(music number: 0 stop:)
		(globalSound vol: 127 changeState:)
		(switch (theGame detailLevel:)
			(0 (kid setScript: aniKids 0 1))
			(1 (kid setScript: aniKids 0 2))
			(2 (kid setScript: aniKids 0 3))
			(3 (kid setScript: aniKids 0 4))
		)
		(if (Btst fTimepodInArcade)
			(pod cel: 7 init: stopUpd:)
			(hatch init: stopUpd:)
		else
			(= local0 1)
			(blast init: stopUpd:)
		)
		(features
			add: changeMachine sushiBar arcadeRoom
			eachElementDo: #init
			doit:
		)
		(super init:)
		(tCanTalker init: 0 0 basket)
		(switch prevRoomNum
			(531
				(globalSound number: 19 vol: 127 loop: -1 flags: 1)
				(self
					setScript:
						(if (Btst fTimeTravel)
							egoArrives
						else
							(ego
								view: 377
								loop: 3
								cel: 0
								posn: 27 156
								setPri: 12
								ignoreActors:
								illegalBits: 0
								normal: 0
								moveHead: 0
								init:
							)
							egoExitsPod
						)
				)
			)
			(375
				(ego posn: (if (> (ego y?) 57) 100 else 220) 188 init:)
				(if
				(or (Btst fSpOnBelt) (and (Btst fPoliceAtArcade) (not (Btst fPoliceInSkateORama))))
					(Load SOUND 105)
					(sp2 init: setPri: -1 posn: (ego x?) 143)
					(blast init: stopUpd:)
					(HandsOff)
					(self setScript: shootDown)
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 189 0 0 319 0 319 188 257 189 239 181 233 175
								234 168 236 161 256 151 289 149 267 132 257 132
								255 138 226 138 219 126 238 123 213 108 132 107
								130 109 118 109 110 114 96 117 86 127 73 132 61 132
								41 145 43 148 59 156 80 158 69 172 59 172 49 177 10 189
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								206 185 175 185 175 189 143 189 143 185 115 185 119 176
								91 170 96 162 128 162 140 123 118 116 118 112 202 111
								202 116 175 123 194 162 219 162 225 180 202 180
							yourself:
						)
				)
			)
			(290
				(globalSound
					number: 19
					vol: 127
					loop: -1
					flags: mNOPAUSE
					playBed:
				)
				(ego posn: 58 143 init:)
				(if (Btst fPoliceInSkateORama)
					(Load SOUND 105)
					(bogusSP init:)
					(sp2 init: setPri: -1 posn: (+ (ego x?) 20) (ego y?))
					(blast init: stopUpd:)
					(HandsOff)
					(self setScript: shootDown)
				)
				(if (and (== (++ msAstroChickenPlays) 5) (not (Btst fPoliceInSkateORama)))
					(curRoom setScript: zapMsChickenS)
				)
				(HandsOn)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 189 0 0 319 0 319 188 257 189 239 181 233 175 234 168 236 161 256 151
								289 149 267 132 257 132 255 138 226 138 219 126 238 123 213 108 132 107
								130 109 118 109 110 114 96 117 86 127 73 132 61 132 41 145 43 148 59 156
								80 158 69 172 59 172 49 177 10 189
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								206 185 175 185 175 189 143 189 143 185 115 185 119 176 91 170 96 162
								128 162 140 123 118 116 118 112 202 111 202 116 175 123 194 162 219 162
								225 180 202 180
							yourself:
						)
				)
			)
			(else 
				(NormalEgo)
				(ego posn: 107 184 init:)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 189 0 0 319 0 319 188 257 189 239 181 233 175 234 168 236 161 256 151
								289 149 267 132 257 132 255 138 226 138 219 126 238 123 213 108 132 107
								130 109 118 109 110 114 96 117 86 127 73 132 61 132 41 145 43 148 59 156
								80 158 69 172 59 172 49 177 10 189
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								206 185 175 185 175 189 143 189 143 185 115 185 119 176
								91 170 96 162 128 162 140 123 118 116 118 112 202 111
								202 116 175 123 194 162 219 162 225 180 202 180
							yourself:
						)
				)
			)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (!= oldDetail (theGame detailLevel:))
			(if
			(> (= oldDetail (theGame detailLevel:)) 1)
				(screen6 setCycle: Forward)
				(screen7 setCycle: Forward)
			else
				(screen6 stopUpd:)
				(screen7 stopUpd:)
			)
		)
		(if
			(and
				local0
				(== (ego onControl: origin) cBLUE)
				(== (ego view?) 0)	;won't appear if Roger is in drag
				(cast contains: ego)
			)
			(ego setScript: spArrive)
		)
	)
)

(instance spArrive of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (== state 4) (== (music prevSignal?) -1))
			(self cue:)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(music number: 838 vol: 127 loop: 1 play:)
				(HandsOff)
				(Bset fTimepodInArcade)
				(Bset fPoliceAtArcade)
				(Bset fSocksClosed)
				(kid setScript: 0)
				(= temp0 0)
				(while (<= temp0 6)
					([theKid temp0] addToPic:)
					(++ temp0)
				)
				(= local0 0)
				(sparks init: setCycle: Forward)
				(= cycles 10)
			)
			(1
				(music fade:)
				(pod init: setCycle: CycleTo 6 1 self)
			)
			(2
				(ego setHeading: 230)
				(hatch init:)
				(pod cel: 7)
				(= cycles 1)
			)
			(3
				(pod stopUpd:)
				(sp1 init:)
				(= cycles 6)
			)
			(4
				(music number: 124 loop: 1 play:)
				(sparks dispose:)
			)
			(5
				(music number: 142 play:)
				(hatch setCycle: EndLoop self)
			)
			(6
				(music number: 0 stop:)
				(sp1 setCycle: EndLoop self)
			)
			(7
				(sp1
					view: 7
					setCycle: Walk
					setLoop: -1
					setCel: -1
					setPri: -1
					setStep: 2 3
				)
				(sp2 init: posn: 3 159 setMotion: MoveTo 27 156 self)
			)
			(8
				(sp1 setMotion: MoveTo 68 155)
				(sp2 posn: 27 156 setCycle: EndLoop self)
			)
			(9
				(sp2
					view: 7
					setCycle: Walk
					setLoop: -1
					setCel: -1
					setPri: -1
					setStep: 2 3
					stopUpd:
				)
				(hatch setCycle: BegLoop self)
			)
			(10
				(hatch stopUpd:)
				(sp1 setMotion: MoveTo 89 160 self)
			)
			(11
				(sp1 setMotion: MoveTo 69 237 self)
			)
			(12
				(sp1 dispose:)
				(HandsOn)
				(sp2 setMotion: MoveTo 60 145 self)
			)
			(13
				(sp2 setMotion: MoveTo 112 119 self)
			)
			(14
				(sp2 setPri: 7 setMotion: MoveTo 203 119 self)
			)
			(15
				(sp2 setPri: -1 setMotion: MoveTo 242 188 self)
			)
			(16
				(sp2 setMotion: MoveTo 242 220 self)
			)
			(17 (HandsOn) (self dispose:))
		)
	)
)

(instance shootDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setScript: 0)
				(sp2 setMotion: 0 view: 13 setLoop: 2 cel: 0)
				(= cycles 6)
			)
			(1
				(tSP2 say: 1)
				(ego setMotion: 0)
				(sp2 setPri: -1 stopUpd:)
				(= cycles 7)
			)
			(2
				(DoDisplay local14)
				(sp2 setCycle: CycleTo 2 1 self)
			)
			(3
				(if (< (ego distanceTo: sp2) 50)
					(self changeState: 8)
				else
					(= blastY (- (sp2 y?) 31))
					(= egoY 155)
					(= local5 (- (sp2 x?) 4))
					(= egoX 248)
					(= underBits
						(Graph
							GSaveBits
							blastY
							(- local5 1)
							egoY
							(+ egoX 1)
							1
						)
					)
					(Graph
						GDrawLine
						blastY
						local5
						egoY
						egoX
						(VGAOrEGA myTextColor3 myTextColor2)
						(- (ego priority?) 1)
						-1
					)
					(Graph
						GReAnimate
						blastY
						(- local5 1)
						egoY
						(+ egoX 1)
					)
					(aSound number: 105 loop: 1 vol: 127 play:)
					(= cycles 1)
				)
			)
			(4
				(sp2 cel: 0)
				(blast setCycle: EndLoop)
				(= cycles 2)
			)
			(5
				(Graph GRestoreBits underBits)
				(Graph
					GReAnimate
					blastY
					(- local5 1)
					egoY
					(+ egoX 1)
				)
				(= cycles 12)
			)
			(6 (sp2 setCycle: CycleTo 2 1 self))
			(7 (sp2 stopUpd:) (= cycles 1))
			(8
				(HandsOff)
				(ego setMotion: 0)
				(= blastY (- (sp2 y?) 31))
				(= egoY (- (ego y?) 28))
				(= local5 (- (sp2 x?) 4))
				(= egoX (ego x?))
				(= underBits
					(Graph
						GSaveBits
						blastY
						(- local5 1)
						egoY
						(+ egoX 1)
						1
					)
				)
				(Graph
					GDrawLine
					blastY
					local5
					egoY
					egoX
					(VGAOrEGA myTextColor3 myTextColor2)
					(- (ego priority?) 1)
					-1
				)
				(Graph
					GReAnimate
					blastY
					(- local5 1)
					egoY
					(+ egoX 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(9
				(blast
					cel: 0
					posn: egoX egoY
					setPri: 15
					setCycle: EndLoop self
				)
			)
			(10 (EgoDead iconLASER deathSMOKING))
		)
	)
)

(instance shootOther of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setScript: 0)
				(sp2 setMotion: 0 view: 13 setLoop: register cel: 0)
				(= cycles 2)
			)
			(1
				(tSP2 say: 1)
				(ego setMotion: 0)
				(sp2 stopUpd:)
				(= cycles 7)
			)
			(2
				(DoDisplay local14)
				(sp2 setCycle: CycleTo 1 1 self)
			)
			(3
				(if (== register 3)
					(= local5 (+ (sp2 x?) 6))
					(= egoY (- (sp2 y?) 33))
				else
					(= local5 (+ (sp2 x?) 27))
					(= egoY (- (sp2 y?) 29))
				)
				(= egoX (+ (ego x?) 2))
				(= blastY (- (ego y?) 34))
				(= underBits
					(Graph
						GSaveBits
						blastY
						(- local5 1)
						egoY
						(+ egoX 1)
						1
					)
				)
				(Graph
					GDrawLine
					egoY
					local5
					blastY
					egoX
					(VGAOrEGA myTextColor3 myTextColor2)
					(- (ego priority?) 1)
					-1
				)
				(Graph
					GReAnimate
					blastY
					(- local5 1)
					egoY
					(+ egoX 1)
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(4
				(blast
					cel: 0
					posn: egoX blastY
					setPri: 15
					setCycle: EndLoop self
				)
			)
			(5 (EgoDead iconLASER deathSMOKING))
		)
	)
)

(instance egoArrives of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 8))
			(1
				(pod init: setCycle: CycleTo 2 1 self)
			)
			(2
				(music number: 838 loop: 1 vol: 127 play:)
				(sparks init: setCycle: Forward)
				(pod setCycle: CycleTo 6 1 self)
			)
			(3
				(pod setCel: 7 setPri: -1 stopUpd:)
				(hatch init: stopUpd:)
				(= cycles 12)
			)
			(4
				(music number: 0 stop:)
				(sparks dispose:)
				(= cycles 1)
			)
			(5
				(HandsOn)
				(globalSound fade: 80 15 10 0)
				(curRoom newRoom: 531)
			)
		)
	)
)

(instance egoExitsPod of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (== state 0) (== (music prevSignal?) -1))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(music number: 124 vol: 127 loop: 1 play:)
				(Bset fTimepodInArcade)
				(= local0 0)
				(pod setCel: 7 init: stopUpd:)
				(hatch init:)
			)
			(1
				(music number: 142 play:)
				(hatch setCycle: EndLoop self)
			)
			(2 (music stop:) (= cycles 4))
			(3 (ego setCycle: EndLoop self))
			(4
				(music play:)
				(hatch setCycle: BegLoop self)
			)
			(5
				(music number: 125 play:)
				(hatch stopUpd:)
				(ego loop: 0 normal: TRUE moveHead: TRUE view: 0)
				(NormalEgo)
				(ego setMotion: PolyPath 51 146 self)
			)
			(6
				(music number: 0)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 189 0 0 319 0 319 188 257 189 239 181 233 175 234 168
								236 161 256 151 289 149 267 132 257 132 255 138 226 138
								219 126 238 123 213 108 132 107 130 109 118 109 110 114
								96 117 86 127 73 132 61 132 41 145 43 148 59 156 80 158
								69 172 59 172 49 177 10 189
							yourself:
						)
						((Polygon new:)
							type: PBarredAccess
							init:
								206 185 175 185 175 189 143 189 143 185 115 185 119 176
								91 170 96 162 128 162 140 123 118 116 118 112 202 111
								202 116 175 123 194 162 219 162 225 180 202 180
							yourself:
						)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoEntersPod of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 27 156 self)
			)
			(1
				(music number: 142 loop: 1 vol: 127 play:)
				(hatch setCycle: EndLoop self)
			)
			(2
				(music stop:)
				(ego
					view: 377
					loop: 3
					cel: 5
					posn: 27 156
					setPri: 13
					ignoreActors:
					illegalBits: 0
					normal: 0
					moveHead: 0
					setCycle: BegLoop self
				)
			)
			(3
				(if (Btst fPoliceInSkateORama)
					(SolvePuzzle fStealTimepod 15)
					(= cycles 12)
				else
					(= cycles 4)
				)
			)
			(4
				(music play:)
				(hatch setCycle: BegLoop self)
			)
			(5
				(music number: 125 play:)
				(= cycles 2)
			)
			(6
				(music number: 0 stop:)
				(Bclr fTimepodInArcade)
				(globalSound fade: 80 15 10 0)
				(curRoom newRoom: 531)
			)
		)
	)
)

(instance playChicken of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 58 143 self)
			)
			(1 (ego setHeading: 270 self))
			(2
				(globalSound fade:)
				(curRoom newRoom: 290)
			)
		)
	)
)

(instance changeMachineDeathS of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 26
					cel: 0
					setMotion: 0
					cycleSpeed: 12
					setCycle: CycleTo 1 1 self
				)
			)
			(1 (ego setCycle: BegLoop self))
			(2 (EgoDead iconLASER deathCHANGEMACHINE))
		)
	)
)

(instance zapMsChickenS of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(blast
					view: 28
					init:
					setPri: 2
					posn: 35 113
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(blast posn: 25 84 cel: 0 setCycle: EndLoop self)
			)
			(2
				(screen1 setCel: 2)
				(blast posn: 0 121 cel: 0 setCycle: EndLoop self)
			)
			(3
				(blast posn: 22 103 cel: 0 setCycle: EndLoop self)
			)
			(4
				(blast posn: 47 109 cel: 0 setCycle: EndLoop self)
			)
			(5
				(narrator say: 13)
				(blast view: 499 dispose:)
				(self dispose:)
			)
		)
	)
)

(instance pod of Sq4Prop
	(properties
		x 26
		y 161
		view 377
		priority 12
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 14))
			(V_DO
				(HandsOff)
				(if (not (shootDown client?))
					(curRoom setScript: egoEntersPod)
				)
			)
			(V_SMELL (narrator say: 15))
			(V_TASTE (narrator say: 16))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe event)
		(return
			(if (super onMe: event)
				(& (OnControl cBLUE (event x?) (event y?)) $1000)
			else
				0
			)
		)
	)
)

(instance sparks of Sq4Prop
	(properties
		x 32
		y 140
		view 377
		loop 1
		priority 13
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(super init: &rest)
		(aSound number: 838 loop: 1 play:)
	)
	
	(method (dispose)
		(aSound stop:)
		(super dispose:)
	)
)

(instance hatch of Sq4Prop
	(properties
		x 2
		y 132
		view 377
		loop 2
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (motionCue)
		(hatchSound stop: loop: 0)
		(super motionCue: &rest)
	)
	
	(method (setCycle)
		(super setCycle: &rest)
		(hatchSound init: play:)
	)
)

(instance sp1 of Sq4Actor
	(properties
		x 27
		y 156
		sightAngle 90
		view 377
		loop 4
		priority 13
		signal (| ignrAct fixPriOn fixedLoop)
		illegalBits $0000
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 17))
			(V_TALK (narrator say: 1))
			(V_SMELL (narrator say: 2))
			(V_TASTE (narrator say: 3))
			(V_DO (narrator say: 2))
			(V_RABBIT
				(narrator modNum: 700 say: 4)
			)
			(V_ROPE (narrator say: 6))
			(V_GUM (narrator say: 7))
			(V_PEN (narrator say: 8))
			(V_BATTERY (narrator say: 8))
			((OneOf theVerb V_BUCKAZOID V_BOMB V_JAR V_TANK V_HINTBOOK V_PLUG V_ATMCARD)
				(narrator say: 5)
			)
			((OneOf theVerb V_CIGAR V_MATCHES V_DISKETTE V_LAPTOP)
				(narrator say: 4)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sp2 of Sq4Actor
	(properties
		sightAngle 90
		view 377
		loop 4
		priority 13
		signal (| ignrAct fixedLoop fixPriOn)
		illegalBits $0000
		xStep 2
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script)
			(
				(and
					(> x 203)
					(< y (ego y?))
					(& (ego onControl: origin) cGREEN)
				)
				(self setScript: shootDown)
			)
			(
				(and
					(== y 119)
					(or
						(& (ego onControl: origin) cBLUE)
						(ego inRect: 146 104 247 125)
					)
				)
				(self setScript: shootOther 0 0)
			)
			((and (> y (ego y?)) (< (ego x?) 148)) (self setScript: shootOther 0 3))
			((and (> y (+ (ego y?) 4)) (> x 170)) (self setScript: shootOther 0 3))
		)
	)
	
	(method (doVerb)
		(sp1 doVerb: &rest)
	)
)

(instance blast of Sq4Prop
	(properties
		x 248
		y 155
		view 499
		loop 1
		priority 12
		signal (| ignrAct fixPriOn)
	)
)

(instance bogusSP of Sq4View
	(properties
		x 63
		y 103
		view 376
		loop 15
		priority 12
		signal (| ignrAct fixPriOn)
	)
)

(instance screen1 of Sq4Prop
	(properties
		x 25
		y 108
		view 376
		priority 2
		signal fixPriOn
		cycleSpeed 2
	)
	
	(method (doVerb theVerb theItem)
		(if (or (== theVerb V_DO) (== theVerb V_BUCKAZOID))
			(cond 
				((>= msAstroChickenPlays 5) (narrator say: 19))
				(buckazoids
					(if (not (-- buckazoids)) (ego put: iBuckazoid))
					(HandsOff)
					(curRoom setScript: playChicken)
				)
				(else (narrator say: 20))
			)
		else
			(switch theVerb
				(V_LOOK (narrator say: 12))
				(V_SMELL (narrator say: 9))
				(V_TASTE (narrator say: 10))
				(else 
					(super doVerb: theVerb theItem)
				)
			)
		)
	)
)

(instance screen2 of Sq4Prop
	(properties
		x 60
		y 99
		view 376
		loop 1
		signal ignrAct
		cycleSpeed 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 21))
			(V_DO (narrator say: 11))
			(V_SMELL (narrator say: 9))
			(V_TASTE (narrator say: 10))
			(
				(OneOf theVerb
					V_BUCKAZOID V_ROPE V_BOMB V_RABBIT V_BATTERY V_JAR V_GUM
					V_TANK V_HINTBOOK V_PEN V_ATMCARD V_PLUG V_CIGAR V_MATCHES
					V_DISKETTE V_LAPTOP
				)
				(narrator say: 11)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance screen3 of Sq4Prop
	(properties
		x 82
		y 91
		sightAngle 90
		view 376
		loop 2
		cel 1
		signal ignrAct
		cycleSpeed 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 22))
			(V_DO (narrator say: 11))
			(V_SMELL (narrator say: 9))
			(V_TASTE (narrator say: 10))
			(
				(OneOf theVerb
					V_BUCKAZOID V_ROPE V_BOMB V_RABBIT V_BATTERY V_JAR V_GUM
					V_TANK V_HINTBOOK V_PEN V_ATMCARD V_PLUG V_CIGAR V_MATCHES
					V_DISKETTE V_LAPTOP
				)
				(narrator say: 11)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance screen4 of Sq4Prop
	(properties
		x 261
		y 84
		sightAngle 90
		view 376
		loop 3
		cel 1
		signal ignrAct
		cycleSpeed 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 23))
			(V_SMELL (narrator say: 9))
			(V_TASTE (narrator say: 10))
			(
				(OneOf theVerb
					V_BUCKAZOID V_ROPE V_BOMB V_RABBIT V_BATTERY V_JAR V_GUM
					V_TANK V_HINTBOOK V_PEN V_ATMCARD V_PLUG V_CIGAR V_MATCHES
					V_DISKETTE V_LAPTOP
				)
				(narrator say: 11)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance screen5 of Sq4Prop
	(properties
		x 294
		y 151
		view 376
		loop 4
		cel 1
		signal $4000
		cycleSpeed 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 24))
			(V_SMELL (narrator say: 9))
			(V_TASTE (narrator say: 10))
			(
				(OneOf theVerb
					V_BUCKAZOID V_ROPE V_BOMB V_RABBIT V_BATTERY V_JAR V_GUM
					V_TANK V_HINTBOOK V_PEN V_ATMCARD V_PLUG V_CIGAR V_MATCHES
					V_DISKETTE V_LAPTOP
				)
				(narrator say: 11)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance screen6 of Sq4Prop
	(properties
		x 82
		y 29
		view 376
		loop 5
		cel 1
		signal ignrAct
		cycleSpeed 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 25))
			(V_DO (narrator say: 26))
			(V_SMELL (narrator say: 27))
			(V_TASTE (narrator say: 28))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance screen7 of Sq4Prop
	(properties
		x 240
		y 28
		view 376
		loop 6
		signal ignrAct
		cycleSpeed 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 29))
			(V_DO (narrator say: 26))
			(V_SMELL (narrator say: 27))
			(V_TASTE (narrator say: 28))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance screen10 of Sq4Prop
	(properties
		x 310
		y 114
		view 376
		loop 14
		signal ignrAct
		cycleSpeed 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 30))
			(V_SMELL (narrator say: 9))
			(V_TASTE (narrator say: 10))
			(
				(OneOf theVerb
					V_DO V_BUCKAZOID V_ROPE V_BOMB V_RABBIT V_BATTERY V_JAR V_GUM
					V_TANK V_HINTBOOK V_PEN V_ATMCARD V_PLUG V_CIGAR V_MATCHES
					V_DISKETTE V_LAPTOP
				)
				(narrator say: 11)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance basket of Sq4Prop
	(properties
		x 160
		y 187
		view 1376
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 31))
			(V_DO (narrator say: 32))
			(V_TALK (self setScript: sBasket))
			(V_SMELL (narrator say: 34))
			(
				(OneOf theVerb
					V_DO V_BUCKAZOID V_ROPE V_BOMB V_RABBIT V_BATTERY V_JAR V_GUM
					V_TANK V_HINTBOOK V_PEN V_ATMCARD V_PLUG V_CIGAR V_MATCHES
					V_DISKETTE V_LAPTOP
				)
				(narrator say: 35)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sBasket of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(narrator say: 33 self)
			)
			(1 (tCanTalker say: 1 self))
			(2
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance kid of Sq4Prop
	(properties
		x 266
		y 172
		view 376
		loop 7
		signal (| ignrAct stopUpdOn)
		cycleSpeed 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 36))
			(V_DO (narrator say: 37))
			(V_TALK (narrator say: 38))
			(V_SMELL (narrator say: 39))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (isNotHidden)
		(return TRUE)
	)
)

(instance aniKids of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= temp0 0)
				(while (<= temp0 register)
					([theKid (Random 0 6)] setCycle: Forward)
					(++ temp0)
				)
				(= seconds (Random 4 7))
			)
			(1
				(= temp0 0)
				(while (<= temp0 6)
					(if (not (& ([theKid temp0] signal?) $0004))
						([theKid temp0] stopUpd:)
					)
					(++ temp0)
				)
				(= seconds (Random 3 5))
			)
			(2
				(self register: (theGame detailLevel:) changeState: 0)
			)
		)
	)
)

(instance changeMachine of Sq4Feature
	(properties
		x 159
		y 117
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 40))
			(V_DO
				(cond 
					((not (ego inRect: 116 102 206 124)) (narrator say: 41))
					((OneOf (ego view?) 373 374) (narrator say: 42))
					((and local6 (not (curRoom script?))) (curRoom setScript: changeMachineDeathS))
					(else (narrator say: 43) (= local6 1))
				)
			)
			(V_BUCKAZOID (narrator say: 44))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe param1)
		(return (& (OnControl 4 (param1 x?) (param1 y?)) $2000))
	)
)

(instance sushiBar of Sq4Feature
	(properties)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_LOOK (narrator say: 45))
			(V_DO (narrator say: 46))
			(V_BUCKAZOID (narrator say: 47))
			(V_SMELL (narrator say: 48))
			(else 
				(super doVerb: theVerb param2)
			)
		)
	)
	
	(method (onMe event)
		(return (& (OnControl cGREEN (event x?) (event y?)) $0200))
	)
)

(instance arcadeRoom of Sq4Feature
	(properties
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 49))
			(V_DO (narrator say: 50))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe)
		(return TRUE)
	)
)

(instance aSound of Sound
	(properties)
)

(instance hatchSound of Sound
	(properties
		number 142
	)
)

(instance tCanTalker of FaceTalker
	(properties
		modNum 376
		talkerNum CAN
	)
)

(instance tRog of Sq4Talker
	(properties
		z 400
		noun ROGER
		view 1008
		talkerNum 14
		mouthOffsetX 26
		mouthOffsetY 32
		eyeOffsetX 25
		eyeOffsetY 21
		tStyle 2
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
	)
)
