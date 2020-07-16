;;; Sierra Script 1.0 - (do not remove this comment)
(script# 921)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Demo 0
)

(local
	numVoices
	theMusic
	local2 =  1
	demoStage
	nextStage
	curRoomPicture
	underbits
	local7
	local8
	local9
	local10 =  1
	local11
	[egoX 3] = [271 219 199]
	[egoY 3] = [152 134 114]
	[boarX 3] = [48 72 57]
	[boarY 3] = [103 172 153]
	[local24 3] = [126 132 102]
	[local27 3] = [141 147 145]
	[boarLoop 3] = [0 3 6]
	[local33 3] = [1 4 7]
	[local36 3] = [2 5 8]
	[theCycles 3] = [1 1 1]
	[theSeconds 3] = [4 3 2]
	local45
	local46
	local47
	local48
	local49
	local50
	[local51 6] = [-2 -1 -3 -3 -3 -6]
	[local77_2 6] = [-3 -2 -2 -2 -3 -1]
	[local63 6] = [6 6 6 6 6 8]
	[local69 6] = [5 5 4 5 6 6]
	local75
	local76
	local77_2_2
	local78
	local79
	[local80 22] = [0 166 88 100 88 93 120 144 168 192 168 218 142 190 114 166 114 163 78 196 78 -32768]
)
(procedure (DoDisplay textRes textString theX theY theWidth &tmp shadowX shadowY)
	(if (and underbits (== curRoomPicture (curRoom picture?)))
		(Display 921 0 p_restore underbits)
		(UnLoad MEMORY underbits)
	)
	(= curRoomPicture (curRoom picture?))
	(= underbits
		(Display textRes textString
			p_at (+ theX 0) (+ theY -1)
			p_color vBLACK
			p_font USERFONT
			p_width theWidth
			p_save
		)
	)
	(= shadowX -1)
	(while (<= shadowX 1)
		(= shadowY -1)
		(while (<= shadowY 1)
			(Display textRes textString
				p_at (+ theX shadowX) (+ theY shadowY)
				p_color vBLACK
				p_font USERFONT
				p_width theWidth
			)
			(++ shadowY)
		)
		(++ shadowX)
	)
	(Display textRes textString
		100 theX theY
		102 15
		105 1
		106 theWidth
	)
	(= userFont SYSFONT)
)

(instance demoMusic of Sound
	(properties
		number 204
	)
)

(instance Demo of Room
	
	(method (init)
		(super init:)
		(Load FONT SYSFONT)
		(Load FONT USERFONT)
		(Load CURSOR HAND_CURSOR)
		(Load CURSOR ARROW_CURSOR)
		(Load SCRIPT DEBUG)
		(Load PICTURE 100)
		(Load VIEW 600)
		(Load VIEW 550)
		(Load SOUND 204)
		(Load SOUND 4)
		(Load PICTURE 101)
		(Load VIEW 601)
		(Load SOUND 2)
		(Load PICTURE 103)
		(Load VIEW 603)
		(Load VIEW 607)
		(Load PICTURE 1)
		(= demoStage 1)
	)
	
	(method (doit &tmp [temp0 30])
		(super doit:)
		(theGame setSpeed: 5)
		(SetCursor 999 0 320 200)
		(if (!= demoStage nextStage)
			(= nextStage demoStage)
			(cast eachElementDo: #dispose 84)
			(switch demoStage
				(1
					(curRoom setScript: titleScreen)
				)
				(2
					(curRoom setScript: camelotGood)
				)
				(3
					(curRoom setScript: roundTableGrail)
				)
				(4
					(curRoom setScript: merlinsRoom)
				)
				(5
					(curRoom setScript: merlinCloseUp)
				)
				(6
					(curRoom setScript: boarAttack)
				)
				(7
					(curRoom setScript: theJoust)
				)
				(8
					(curRoom setScript: monkActions)
				)
				(9 (= demoStage 1))
			)
		)
	)
)

(instance grail of Actor)

(instance leftSparkles of Actor)

(instance rightSparkles of Actor)

(instance Hand of Actor)

(instance titleScreen of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local2
					(curRoom drawPic: 100 IRISOUT)
					(= local2 0)
				else
					(curRoom drawPic: 100 (| IRISIN BLACKOUT))
				)
				(grail
					view: 600
					loop: 3
					cel: 0
					posn: 227 52
					setPri: 14
					cycleSpeed: 2
					init:
					hide:
				)
				(leftSparkles
					view: 600
					loop: 1
					cel: 0
					posn: 227 44
					setPri: 13
					cycleSpeed: 2
					ignoreActors:
					init:
					hide:
				)
				(rightSparkles
					view: 600
					loop: 2
					cel: 0
					posn: 227 44
					setPri: 13
					cycleSpeed: 2
					ignoreActors:
					init:
					hide:
				)
				(Hand
					view: 600
					loop: 0
					cel: 1
					posn: 106 83
					setPri: 4
					init:
				)
				(WindowCorners 258)
				(addToPics doit:)
				(if
					(or
						(<= (= numVoices (DoSound NumVoices)) 3)
						(== numVoices 12)
					)
					(= theMusic 204)
				else
					(= theMusic 4)
				)
				(demoMusic number: theMusic play:)
				(self cue:)
			)
			(1
				(Hand cycleSpeed: 4 setCycle: Forward)
				(= seconds 4)
			)
			(2
				(Hand stopUpd:)
				(grail show: setCycle: Forward)
				(leftSparkles show: setCycle: Forward)
				(rightSparkles show: setCycle: Forward)
				(demoMusic client: self)
			)
			(3
				(demoMusic dispose:)
				(++ demoStage)
				(= seconds 4)
			)
		)
	)
)

(instance boat of Actor)

(instance breaker of Prop)

(instance butterfly1 of Prop)

(instance butterfly2 of Prop)

(instance butterfly3 of Prop)

(instance leftFlag of Prop)

(instance middleFlag of Prop)

(instance rightFlag of Prop)

(instance camelotGood of Script
	
	(method (doit)
		(super doit:)
		(if (and (== state 2) (== (demoMusic prevSignal?) 20))
			(demoMusic prevSignal: 0)
			(= seconds (= cycles 0))
			(self changeState: 3)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(UnLoad MEMORY underbits)
				(curRoom drawPic: 101)
				(boat
					view: 601
					setLoop: 2
					posn: 10 156
					setPri: 7
					init:
					setCycle: Forward
					cycleSpeed: 2
					moveSpeed: 1
					setStep: 1 1
					setMotion: MoveTo 139 156
				)
				(breaker
					view: 601
					loop: 0
					cel: 1
					posn: 133 182
					setPri: 13
					cycleSpeed: 2
					setCycle: Forward
					init:
				)
				(butterfly1
					view: 601
					loop: 3
					cel: 0
					posn: 215 176
					priority: 8
					setCycle: Forward
					cycleSpeed: 3
					init:
				)
				(butterfly2
					view: 601
					loop: 3
					cel: 2
					posn: 244 170
					priority: 8
					setCycle: Forward
					cycleSpeed: 2
					init:
				)
				(butterfly3
					view: 601
					loop: 3
					cel: 1
					posn: 278 164
					priority: 8
					setCycle: Forward
					cycleSpeed: 4
					init:
				)
				(leftFlag
					view: 601
					loop: 4
					cel: 1
					posn: 163 27
					setCycle: Forward
					cycleSpeed: 6
					init:
				)
				(middleFlag
					view: 601
					loop: 4
					cel: 2
					posn: 191 26
					setCycle: Forward
					cycleSpeed: 5
					init:
				)
				(rightFlag
					view: 601
					loop: 4
					cel: 0
					posn: 207 37
					setCycle: Forward
					cycleSpeed: 6
					init:
				)
				(WindowCorners 32)
				(addToPics doit:)
				(= seconds 2)
			)
			(1
				(demoMusic number: 2 play:)
				(UnLoad MEMORY underbits)
				(DoDisplay 921 1 30 30 120)
				(= seconds 5)
			)
			(2
				(UnLoad 133 underbits)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 1)
				(WindowCorners 258)
				(addToPics doit:)
				(DoDisplay 921 2 60 30 140)
				(= seconds 5)
			)
			(3 (cls) (++ demoStage))
		)
	)
)

(instance knight1 of View)

(instance knight2 of Prop)

(instance knight3 of View)

(instance knight4 of View)

(instance knight5 of View)

(instance knight6 of View)

(instance knight7 of View)

(instance knight8 of View)

(instance knight9 of View)

(instance knight10 of Prop)

(instance knight11 of View)

(instance knight12 of View)

(instance knight13 of View)

(instance knight14 of View)

(instance knight15 of Prop)

(instance gawaine of Prop)

(instance lancelot of Prop)

(instance galahad of Prop)

(instance coveredGrail of Actor)

(instance roundTableGrail of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(UnLoad MEMORY underbits)
				(curRoom drawPic: 103)
				(knight1
					view: 603
					loop: 0
					cel: 0
					posn: 229 143
					setPri: 10
					init:
					addToPic:
				)
				(lancelot
					view: 603
					loop: 0
					cel: 1
					posn: 202 151
					setPri: 11
					init:
				)
				(knight3
					view: 603
					loop: 0
					cel: 2
					posn: 164 161
					setPri: 12
					init:
					addToPic:
				)
				(knight4
					view: 603
					loop: 0
					cel: 3
					posn: 120 156
					setPri: 11
					init:
					addToPic:
				)
				(knight5
					view: 603
					loop: 0
					cel: 4
					posn: 90 144
					setPri: 10
					init:
					addToPic:
				)
				(knight6
					view: 603
					loop: 0
					cel: 5
					posn: 68 128
					setPri: 9
					init:
					addToPic:
				)
				(knight7
					view: 603
					loop: 0
					cel: 6
					posn: 60 90
					setPri: 8
					init:
					addToPic:
				)
				(knight8
					view: 603
					loop: 1
					cel: 0
					posn: 67 72
					setPri: 3
					init:
					addToPic:
				)
				(galahad
					view: 603
					loop: 1
					cel: 1
					posn: 89 49
					setPri: 1
					init:
				)
				(knight10
					view: 603
					loop: 1
					cel: 2
					posn: 120 39
					setPri: 0
					init:
					addToPic:
				)
				(knight11
					view: 603
					loop: 1
					cel: 3
					posn: 159 33
					setPri: 9
					init:
					addToPic:
				)
				(knight12
					view: 603
					loop: 1
					cel: 4
					posn: 195 37
					setPri: 8
					init:
					addToPic:
				)
				(knight13
					view: 603
					loop: 1
					cel: 5
					posn: 228 48
					setPri: 8
					init:
					addToPic:
				)
				(knight14
					view: 603
					loop: 1
					cel: 6
					posn: 257 72
					setPri: 3
					init:
					addToPic:
				)
				(gawaine
					view: 603
					loop: 1
					cel: 7
					posn: 259 95
					setPri: 8
					init:
				)
				(coveredGrail
					view: 607
					loop: 0
					cel: 5
					posn: 159 6
					ignoreActors:
					illegalBits: 0
					setPri: 14
					setCycle: Forward
					cycleSpeed: 1
					moveSpeed: 1
					setMotion: MoveTo 159 98 self
					init:
				)
				;stops here; is an issue with Actor
				(WindowCorners 32)
				(addToPics doit:)
			)
			(1
				(coveredGrail dispose:)
				(DoDisplay 921 3 100 67 125)
				(= seconds 7)
			)
			(2
				(DoDisplay 921 4 100 72 125)
				(gawaine posn: 100 1000)
				(lancelot posn: 100 1000)
				(galahad posn: 100 1000)
				(= seconds 4)
				(demoMusic client: self)
			)
			(3
				(DoDisplay 921 0 120 72 115)
				(demoMusic client: self)
				(= seconds 3)
			)
			(4
				(DoDisplay 921 5 120 72 115)
				(= seconds 3)
			)
			(5
				(cls)
				(++ demoStage)
			)
		)
	)
)

(instance merlin of Actor)

(instance lid of View)

(instance candles of Prop)

(instance merlinsRoom of Script
	(properties)
	
	(method (init)
		(super init:)
		(Load PICTURE 2)
		(Load VIEW 320)
		(Load VIEW 2)
		(Load VIEW 102)
		(Load SOUND 5)
		(Load PICTURE 104)
		(Load VIEW 604)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(UnLoad MEMORY underbits)
				(curRoom drawPic: 2)
				(merlin
					view: 320
					setLoop: 0
					setCel: 6
					posn: 196 132
					init:
				)
				(lid
					view: 102
					setLoop: 0
					setCel: 0
					posn: 305 148
					init:
					addToPic:
				)
				(candles
					view: 102
					setLoop: 1
					cycleSpeed: 1
					setCycle: Forward
					posn: 233 74
					ignoreActors:
					init:
				)
				(WindowCorners 256)
				(addToPics doit:)
				(= seconds 2)
			)
			(1
				(demoMusic number: 5 play:)
				(ego
					view: 2
					posn: 172 186
					loop: 3
					setLoop: -1
					init:
					setCycle: Walk
					setMotion: MoveTo 175 150 self
				)
			)
			(2
				(DoDisplay 921 6 90 30 200)
				(merlin setCycle: BegLoop self)
			)
			(3
				(= seconds 7)
			)
			(4
				(++ demoStage)
			)
		)
	)
)

(instance mouth of Prop)

(instance eyes of Prop)

(instance gem of Prop)

(instance merlinCloseUp of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(UnLoad MEMORY underbits)
				(curRoom drawPic: 104)
				(mouth
					view: 604
					loop: 0
					cel: 0
					posn: 119 136
					cycleSpeed: 1
					init:
				)
				(eyes
					view: 604
					loop: 2
					cel: 0
					posn: 22 75
					init:
					cycleSpeed: 10
					setCycle: Forward
				)
				(gem
					view: 604
					loop: 1
					cel: 3
					posn: 136 69
					cycleSpeed: 2
					setCycle: Forward
					init:
				)
				(WindowCorners 256)
				(addToPics doit:)
				(= seconds 3)
			)
			(1
				(DoDisplay 921 7 170 30 130)
				(mouth setCycle: Forward)
				(= seconds 6)
			)
			(2
				(DoDisplay 921 8 170 30 130)
				(= seconds 6)
			)
			(3
				(mouth setCel: 0 stopUpd:)
				(DoDisplay 921 0 170 30 130)
				(= seconds 2)
			)
			(4
				(demoMusic fade:)
				(= seconds 3)
			)
			(5
				(++ demoStage)
			)
		)
	)
)

(instance boarAttack of Script

	(method (init)
		(super init:)
		(Load PICTURE 12)
		(Load VIEW 9)
		(Load VIEW 406)
		(Load VIEW 17)
		(Load SOUND 23)
		(Load PICTURE 89)
		(Load VIEW 302)
		(Load VIEW 301)
		(Load VIEW 306)
		(Load VIEW 305)
		(Load SOUND 14)
		(Load PICTURE 18)
		(Load VIEW 0)
		(Load VIEW 338)
	)
	
	(method (doit)
		(super doit:)
		(if local8
			(= local8 0)
			(ego setScript: Spearing)
			(boar setScript: BoarDead)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(UnLoad MEMORY underbits)
				(curRoom drawPic: 12)
				(WindowCorners 32)
				(addToPics doit:)
				(= seconds 3)
			)
			(1
				(demoMusic number: 23 play:)
				(ego
					view: 9
					posn: 303 174
					setScript: EgoMotion
					loop: 1
					init:
					setCycle: Walk
				)
				(self cue:)
			)
			(2
				(DoDisplay 921 9 50 20 200)
				(boar init: setScript: BoarMotion)
				(Spear
					view: 17
					setLoop: 4
					setCel: 1
					ignoreActors: 1
					posn: (+ (ego x?) 2) (- (ego y?) 17)
					init:
				)
			)
		)
	)
)

(instance EgoMotion of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local7 0)
				(ego
					view: 9
					loop: 1
					setCycle: Walk
					setMotion: MoveTo [egoX local10] [egoY local7] self
				)
			)
			(1
				(= local7 1)
				(ego setScript: 0)
			)
		)
	)
)

(instance Spearing of Script
	
	(method (doit)
		(super doit:)
		(if (and local9 (== local10 1) (== (ego cel?) 4))
			(ego setLoop: 6 setCel: 0 setCycle: EndLoop)
			(self changeState: 3)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 17 setLoop: 1 setCel: 0 setCycle: CycleTo 3 1 self)
				(= local7 0)
			)
			(1
				(= cycles [theCycles local10])
			)
			(2
				(ego setCel: 4 setCycle: EndLoop self)
			)
			(3
				(= local7 1)
				(ego setScript: 0)
			)
		)
	)
)

(instance BoarMotion of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(boar
					view: 406
					setLoop: [boarLoop local10]
					posn: [boarX local10] [boarY local10]
					cycleSpeed: 0
				)
				(= seconds 2)
			)
			(1
				(boar
					setMotion: MoveTo [local24 local10] [local27 local10] self
				)
			)
			(2
				(boar setLoop: [local33 local10] setCycle: Forward)
				(= seconds [theSeconds local10])
			)
			(3
				(boar
					setLoop: [boarLoop local10]
					setCycle: Walk
					setStep: 8 5
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo (- (ego x?) 8) (+ (ego y?) 4) self
				)
			)
			(4
				(if (not local9)
					(= local8 1)
					(boar
						setLoop: [local36 local10]
						cel: 0
						setCycle: EndLoop self
					)
				)
			)
			(5)
			(6 (= seconds 2))
		)
	)
)

(instance BoarDead of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(boar
					view: 17
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(if (== local10 1)
					(= local11 1)
					(Spear
						setLoop: 4
						setCel: 3
						posn: (+ (boar x?) 10) (boar y?)
						setPri: (boar priority?)
						ignoreActors: 1
					)
				)
				(boar loop: 2 cel: 0 setCycle: Forward cycleSpeed: 3)
				(= seconds 5)
			)
			(2
				(deadBoar
					view: 17
					loop: 2
					cel: (boar cel?)
					posn: (boar x?) (boar y?)
					setPri: (+ (ego priority?) 1)
					addToPic:
				)
				(if (== local10 1)
					(RedrawCast)
					(Spear
						setLoop: 4
						setCel: 3
						posn: (+ (deadBoar x?) 10) (deadBoar y?)
						setPri: (deadBoar priority?)
						ignoreActors: 1
						addToPic:
					)
					(boar dispose:)
				else
					(boar posn: 350 100)
				)
				(if (< (++ local10) 2)
					(ego setScript: EgoMotion)
					(boar setScript: BoarMotion)
					(= local9 0)
					(= local8 0)
				else
					(ego
						view: 9
						loop: 1
						setLoop: -1
						setCycle: Walk
						setScript: 0
					)
					(= local10 1)
					(++ demoStage)
				)
			)
		)
	)
)

(instance boar of Actor
	
	(method (init)
		(self
			view: 406
			loop: [boarLoop local10]
			cel: 0
			setCycle: Walk
			setStep: 5 5
			illegalBits: cWHITE
			ignoreActors: 1
		)
		(super init:)
	)
)

(instance deadBoar of View)

(instance Spear of Prop

	(method (init)
		(super init:)
	)
	
	(method (doit)
		(cond 
			((and (== (ego view?) 9) (not local11))
				(self
					setPri: (ego priority?)
					setCel: 2
					posn: (- (ego x?) 5) (- (ego y?) 28)
				)
			)
			((not local11) (self setCel: 1))
		)
		(super doit:)
	)
)

(instance cor1 of PicView
	(properties
		view 550
		cel 2
		priority 15
	)
)

(instance cor2 of PicView
	(properties
		y 170
		view 550
		loop 3
		cel 1
		priority 15
	)
)

(instance cor3 of PicView
	(properties
		x 295
		view 550
		loop 3
		cel 1
		priority 15
	)
)

(instance cor4 of PicView
	(properties
		y 170
		x 295
		view 550
		cel 3
		priority 15
	)
)

(instance horse of Actor)

(instance blackKnight of Actor)

(instance arthurIcon of PicView
	(properties
		y 176
		x 263
		view 305
		priority 15
		signal $4000
	)
)

(instance blackKnightIcon of PicView
	(properties
		y 176
		x 58
		view 305
		cel 1
		priority 15
		signal $4000
	)
)

(instance shieldIcon of PicView
	(properties
		y 44
		x 56
		view 302
		loop 1
		priority 15
	)
)

(instance lanceIcon of PicView
	(properties
		y 44
		x 264
		view 302
		loop 1
		cel 1
		priority 15
	)
)

(instance skull of Actor
	(properties
		view 302
		loop 1
		cel 4
		priority 14
		signal $0010
	)
)

(instance sPtangk of Sound
	(properties
		number 14
		priority 14
	)
)

(instance theJoust of Script
	
	(method (init)
		(super init:)
		(UnLoad MEMORY underbits)
		(curRoom drawPic: 89)
		(egoLance
			view: 302
			setLoop: 0
			setCel: 0
			posn: 180 175
			ignoreActors: TRUE
			setStep: 2 2
			setPri: 12
			init:
		)
		(egoShield
			view: 302
			setLoop: 1
			cel: 3
			posn: 170 180
			setPri: 13
			setStep: 4 4
			ignoreActors: TRUE
			illegalBits: 2
			init:
		)
		(blackLance init:)
		(blackShield init:)
		(blackKnight view: 301 setPri: 8 init: ignoreActors:)
		(skull posn: 1000 1000 init:)
		(addToPics
			add: cor1 cor2 cor3 cor4 shieldIcon lanceIcon arthurIcon blackKnightIcon
			doit:
		)
		(DoDisplay 921 10 115 10 200)
		(self changeState: 1)
	)
	
	(method (doit &tmp [temp0 4] temp4 temp5)
		(super doit:)
		(switch (++ local45)
			(1 (= local48 3))
			(4 (= local47 -3))
			(8 (= local48 -3))
			(12 (= local47 3))
			(16 (= local48 3))
			(20 (= local47 -3))
			(28
				(= temp4 1)
				(sPtangk play:)
				(blackKnight setScript: blackKnightHit)
				(skull
					setLoop: 1
					setCel: 4
					posn: (- (egoLance x?) 51) (- (egoLance y?) 72)
				)
			)
			(29
				(skull posn: (- (skull x?) 5) (+ (skull y?) 6))
			)
			(30 (skull dispose:))
		)
	)
	
	(method (changeState newState &tmp [temp0 20] newEvent)
		(switch (= state newState)
			(1
				(blackKnight
					view: 301
					setLoop: 2
					setCel: 4
					posn: 168 58
					setPri: 8
				)
				(blackLance
					setLoop: 4
					setCel: 2
					posn: (+ 168 [local51 0]) (+ 58 [local77_2 0])
					setPri: 11
				)
				(blackShield setLoop: 5 setCel: 4 doit: setPri: 9)
				(egoLance setCel: 0 setStep: 2 2 setPri: 12)
				(if (not (egoLance inRect: 100 100 210 200))
					(egoLance posn: 180 175)
				)
				(egoShield setStep: 4 4 setScript: 0 setMotion: 0)
				(if (not (egoShield inRect: 100 100 210 200))
					(egoShield posn: 170 180)
				)
				(= local49 (= local50 0))
				(= local79 (= local77_2_2 (= local78 0)))
				(= local46 1)
				(= local45 0)
				(= cycles 4)
			)
			(2
				(blackKnight
					setLoop: 2
					setCel: 4
					setStep: 1 1
					setCycle: CycleTo 0 -1
					setMotion: MoveTo 164 62 self
				)
				(blackLance
					setStep: 1 1
					setMotion: MoveTo (+ 164 [local51 0]) (+ 62 [local77_2 0])
				)
				(blackShield doit:)
			)
			(3
				(blackKnight
					setLoop: 1
					setCel: 255
					setStep: 2 2
					setCycle: CycleTo 5 -1
					posn: 164 63
					setMotion: MoveTo 156 71 self
				)
				(blackLance
					setStep: 2 2
					setLoop: 4
					setCel: 1
					posn: (+ 164 [local51 1]) (+ 63 [local51 1])
					setMotion: MoveTo (+ 156 [local51 1]) (+ 71 [local77_2 1])
				)
				(blackShield setCel: 3 doit:)
			)
			(4
				(blackKnight
					setStep: 2 3
					posn: 156 72
					setCycle: CycleTo 0 -1
					setMotion: MoveTo 148 85 self
				)
				(blackLance
					setStep: 2 3
					setLoop: 4
					setCel: 0
					posn: (+ 156 [local51 2]) (+ 72 [local51 2])
					setMotion: MoveTo (+ 148 [local51 2]) (+ 85 [local77_2 2])
				)
				(blackShield setCel: 2 doit:)
			)
			(5
				(blackKnight
					setLoop: 0
					setStep: 1 3
					setCel: 9
					posn: 147 87
					setCycle: CycleTo 5 -1
					setMotion: MoveTo 143 100 self
				)
				(blackLance
					setStep: 1 3
					setLoop: 3
					setCel: (Random 1 4)
					posn: (+ 147 [local51 3]) (+ 87 [local51 3])
					setMotion: MoveTo (+ 143 [local51 3]) (+ 100 [local77_2 3])
				)
				(if
				(or (== (blackLance cel?) 3) (== (blackLance cel?) 4))
					(blackLance setPri: 12)
					(egoLance setPri: 11)
				)
				(= local49
					(cond 
						((< (egoLance x?) 172) -8)
						((< (egoLance x?) 176) -6)
						((< (egoLance x?) 184) (- 0 (Random 2 6)))
						(else (- 2 (Random 0 6)))
					)
				)
				(= local50 (- 2 (Random 0 4)))
				(blackShield setCel: 1 doit:)
			)
			(6
				(if (< (egoLance x?) 175)
					(blackKnight view: 306)
					(= local49 -8)
					(= local50 0)
				)
				(blackKnight
					setLoop: 0
					setStep: 3 4
					posn: 143 101
					setCycle: CycleTo 0 -1
					setMotion: MoveTo 130 119 self
				)
				(blackLance
					setStep: 3 4
					setLoop: 2
					posn: (+ 143 [local51 4]) (+ 101 [local77_2 4])
					setMotion: MoveTo (+ 130 [local51 4]) (+ 119 [local77_2 4])
				)
				(blackShield setCel: 0 doit:)
			)
			(7
				(if (== (blackKnight view?) 301)
					(blackKnight setLoop: 3 setCycle: Forward)
				)
				(blackKnight
					setStep: 5 6
					posn: 130 119
					setMotion: MoveTo 85 175 self
				)
				(blackLance
					setStep: 5 6
					posn: (+ 128 [local51 5]) (+ 112 [local77_2 5])
					setMotion: MoveTo (+ 85 [local51 5]) (+ 175 [local77_2 5])
				)
			)
			(8
				(if (or local77_2_2 local79) (= state 7))
				(= cycles 4)
			)
			(9
				(if (== (blackKnight view?) 306)
					(blackKnight view: 301)
				)
				(while ((= newEvent (Event new:)) type?)
					(newEvent dispose:)
				)
				(newEvent dispose:)
				(blackKnight setMotion: 0)
				(blackLance setMotion: 0)
				(= state 0)
				(= seconds 3)
			)
		)
	)
)

(instance egoShield of Actor
	(properties
		view 302
	)
	
	(method (doit)
		(self setMotion: MoveTo (self x?) 186 self)
		(super doit:)
	)
	
	(method (cue)
		(= local46 1)
		(self stopUpd:)
	)
)

(instance egoLance of Actor
	(properties
		view 302
	)
	
	(method (doit)
		(cond 
			((and (& signal $0004) (not (& signal $0002))) (return))
			(local77_2_2 0)
			(else
				(if
					(and
						local47
						(> 190 (+ x local47))
						(> (+ x local47) 165)
					)
					(= x (+ x local47))
					(self forceUpd:)
				else
					(= local47 0)
				)
				(if
					(and
						local48
						(> 190 (+ y local48))
						(> (+ y local48) 174)
					)
					(= y (+ y local48))
					(self forceUpd:)
				else
					(= local48 0)
				)
			)
		)
		(= y (+ y local75))
		(super doit:)
	)
)

(instance blackLance of Actor
	(properties
		view 302
	)
	
	(method (init)
		(super init:)
		(self setLoop: 4 setPri: 11 ignoreActors:)
	)
)

(instance blackShield of Actor
	(properties
		view 302
	)
	
	(method (init)
		(super init:)
		(self setLoop: 5 setPri: 9 ignoreActors:)
	)
	
	(method (doit)
		(super doit:)
		(if (not local79)
			(self
				posn:
					(+ (blackKnight x?) [local63 0] local49)
					(+ (blackKnight y?) [local69 0] local50)
			)
		)
	)
)

(instance blackKnightHit of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local79 1)
				(horse
					view: 306
					loop: 1
					cel: 0
					ignoreActors:
					posn: (blackKnight x?) (blackKnight y?)
					setPri: 8
					init:
					setStep: 3 4
					setMotion: MoveTo 85 175
				)
				(blackKnight
					view: 306
					loop: 2
					cel: 0
					setCycle: EndLoop self
					setPri: 9
					setStep: 4 5
					setMotion: 0
					setMotion: MoveTo (- (blackKnight x?) 15) (- (blackKnight y?) 20)
				)
				(blackShield
					setStep: 4 5
					setPri: 10
					setMotion: MoveTo (- (blackShield x?) 10) (- (blackShield y?) 20)
				)
				(blackLance
					setStep: 8 12
					setMotion: MoveTo (- (blackLance x?) 30) (+ (blackLance y?) 48)
				)
			)
			(1
				(blackKnight
					setStep: 14 4
					setMotion: MoveTo (- (blackKnight x?) 10) (- (blackKnight y?) 10) self
				)
				(blackShield
					setStep: 14 5
					setMotion: MoveTo (+ (blackShield x?) 10) (- (blackShield y?) 15)
				)
			)
			(2
				(blackKnight
					setStep: 14 10
					setMotion: MoveTo (- (blackKnight x?) 10) (+ (blackKnight y?) 30)
				)
				(blackShield
					setStep: 14 10
					setMotion: MoveTo (- (blackShield x?) 10) (+ (blackShield y?) 25) self
					setCel: 5
					setCycle: EndLoop
				)
			)
			(3 (= seconds 4))
			(4
				(horse dispose:)
				(++ demoStage)
			)
		)
	)
)

(instance monk of Actor)

(instance monkActions of Script

	(method (init)
		(super init:)
		(UnLoad MEMORY underbits)
		(curRoom drawPic: 18)
		(ego
			view: 0
			illegalBits: 0
			posn: 0 80
			loop: 0
			setLoop: -1
			setStep: 4 2
			ignoreActors:
			setCycle: Walk
			init:
			setMotion: MoveTo 70 80 self
		)
		(WindowCorners 48)
		(addToPics doit:)
		(DoDisplay 921 11 100 20 300)
		(monk
			view: 338
			illegalBits: 0
			setStep: 8 4
			ignoreActors:
			init:
			posn: 320 80
		)
		(self changeState: 0)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(ego setMotion: MoveTo 105 95 self)
			)
			(2
				(ego setMotion: MoveTo 155 95 self)
			)
			(3
				(ego setMotion: MoveTo 200 80 self)
			)
			(4
				(DoDisplay 921 12 100 120 300)
				(ego setStep: 6 4 setMotion: MoveTo 155 95 self)
				(monk setCycle: Walk setMotion: MoveTo 200 80)
			)
			(5
				(ego setMotion: MoveTo 105 95 self)
				(monk setMotion: MoveTo 155 95)
			)
			(6
				(ego setMotion: MoveTo 70 80 self)
				(monk setMotion: MoveTo 105 95)
			)
			(7
				(ego setMotion: MoveTo -10 80)
				(monk setMotion: MoveTo -10 80)
				(= seconds 3)
			)
			(8
				(demoMusic fade:)
				(= seconds 2)
			)
			(9
				(UnLoad MEMORY underbits)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 55)
				(WindowCorners 65)
				(addToPics doit:)
				(self cue:)
			)
			(10
				(Load PICTURE 55)
				(Load SOUND 20)
				(demoMusic number: 20 play:)
				(DoDisplay 921 13 60 60 300)
				(= seconds 5)
			)
			(11
				(DoDisplay 921 14 60 80 300)
				(= seconds 4)
			)
			(12
				(demoMusic fade:)
				(= seconds 5)
			)
			(13
				(demoMusic stop:)
				(theGame restart:)
			)
		)
	)
)
