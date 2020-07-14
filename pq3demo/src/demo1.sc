;;; Sierra Script 1.0 - (do not remove this comment)
(script# 120)
(include game.sh)
(use Main)
(use RandCyc)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demo1 0
)

(local
	local0
)
(instance demo1 of Room
	(properties
		picture 30
		style FADEOUT
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(theIconBar disable:)
		(storeWindow init: stopUpd:)
		(music number: 300 loop: -1 play:)
		(self setScript: demoScript)
	)
	
	(method (doit &tmp temp0)
		(if script (script doit:))
	)
	
	(method (dispose)
		(DisposeScript RANDCYC)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
			(else (event claimed: TRUE))
		)
	)
)

(instance demoScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (or (== state 6) (== state 9))
			(cond 
				((and (== (marie cel?) 0) (== local0 0))
					(sfx3
						number: (Random 314 315)
						loop: 1
						vol: (+ 80 (* (/ (marie y?) 30) 10))
						play:
					)
					(= local0 1)
				)
				((and (== (marie cel?) 4) (== local0 0))
					(sfx3
						number: (Random 314 315)
						loop: 1
						vol: (+ 80 (* (/ (marie y?) 30) 10))
						play:
					)
					(= local0 1)
				)
				(
				(and (!= (marie cel?) 4) (!= (marie cel?) 0)) (= local0 0))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 50))
			(1
				(sfx1 number: 303 loop: 1 play:)
				(storeWindow
					posn: 156 7
					loop: 4
					cel: 0
					cycleSpeed: 9
					setCycle: EndLoop self
				)
			)
			(2
				(marie
					setCycle: Walk
					setLoop: 0
					cel: 1
					setMotion: MoveTo 181 27 self
					init:
				)
				(storeWindow loop: 5 cel: 0)
			)
			(3
				(sfx1 number: 303 loop: 1 play:)
				(storeWindow setCycle: EndLoop self)
			)
			(4
				(marie loop: 6 cel: 0 setCycle: CycleTo 9 1)
				(= seconds 3)
			)
			(5 (marie setCycle: EndLoop self))
			(6
				(storeWindow dispose:)
				(marie
					setLoop: 0
					x: (+ (marie x?) 1)
					y: (+ (marie y?) 2)
					setCycle: Walk
					setMotion: MoveTo 190 47 self
				)
			)
			(7
				(insetW init:)
				(marieHead cycleSpeed: 15 setCycle: EndLoop init:)
				(sfx1 number: 305 loop: 1 play:)
				(= seconds 4)
			)
			(8
				(marieHead loop: 2 cycleSpeed: 20 setCycle: EndLoop)
				(marie posn: 222 80 setStep: 3 2 setLoop: 1)
				(= seconds 4)
			)
			(9
				(marieHead dispose:)
				(insetW dispose:)
				(marie setMotion: MoveTo 202 144 self)
			)
			(10
				(marie
					setLoop: 2
					cel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(11 (= seconds 2))
			(12
				(curRoom drawPic: 66)
				(marieHead view: 368 loop: 0 cel: 0 posn: 174 82 init:)
				(marie hide:)
				(mArm cycleSpeed: 16 setCycle: RandCycle init:)
				(= seconds 3)
			)
			(13
				(curRoom style: 7 drawPic: 65)
				(mArm dispose:)
				(= cycles 5)
				(sfx3 number: 315 vol: 127 loop: 1 play:)
			)
			(14
				(sfx1 number: 306 loop: 1 vol: 127 play:)
				(marieHead
					loop: 1
					cel: 0
					posn: 149 84
					cycleSpeed: 20
					setCycle: RandCycle
				)
				(= seconds 2)
			)
			(15
				(marieHead setCycle: 0 cel: 1)
				(cigarette
					moveSpeed: 4
					setMotion: MoveTo 68 108 self
					init:
				)
			)
			(16 (= seconds 2))
			(17
				(cigarette setMotion: MoveTo 33 96 self)
			)
			(18
				(tip setCycle: EndLoop self init:)
			)
			(19
				(tip dispose:)
				(cigarette setMotion: MoveTo 33 126 self)
			)
			(20
				(marieHead dispose:)
				(insetW
					view: 370
					loop: 0
					cel: 0
					posn: 138 117
					setPri: 2
					init:
				)
				(stripe init:)
				(= cycles 3)
			)
			(21
				(smoke setCycle: EndLoop self init:)
				(sfx1 number: 313 loop: 1 play:)
			)
			(22
				(knife setMotion: MoveTo 88 112 self init:)
			)
			(23
				(sfx1 number: 310 loop: 1 play:)
				(knife setCycle: EndLoop self)
			)
			(24
				(knife loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(25
				(curRoom style: 10 drawPic: 30)
				(cigarette dispose:)
				(insetW dispose:)
				(marieHead dispose:)
				(knife dispose:)
				(bandit1 init:)
				(bandit2 init:)
				(stripe dispose:)
				(= cycles 30)
			)
			(26
				(bandit1 cycleSpeed: 8 setCycle: EndLoop self)
				(sfx3 number: 301 vol: 127 loop: 1 play:)
				(music stop:)
			)
			(27
				(bandit2
					loop: 1
					cycleSpeed: 8
					posn: 207 147
					setCycle: EndLoop self
				)
				(bandit1 dispose:)
			)
			(28
				(sfx1 number: 307 loop: 1 play:)
				(insetW
					view: 372
					loop: 0
					cel: 0
					posn: 160 120
					setPri: 13
					cycleSpeed: 16
					init:
				)
				(= seconds 2)
			)
			(29
				(curRoom style: IRISIN drawPic: 98)
				(bandit1 dispose:)
				(bandit2 dispose:)
				(Animate (cast elements?) FALSE)
				(insetW setCycle: EndLoop self)
			)
			(30
				(bandit1
					view: 372
					loop: 1
					cel: 0
					posn: 164 111
					setPri: 15
					cycleSpeed: 6
					setPri: 15
					setCycle: Forward
					init:
				)
				(= seconds 3)
			)
			(31
				(curRoom style: FADEOUT drawPic: 98)
				(bandit1 dispose:)
				(insetW dispose:)
				(= seconds 3)
			)
			(32
				(sfx3 fade:)
				(sfx1 fade:)
				(= seconds 2)
			)
			(33 (curRoom newRoom: 121))
		)
	)
)

(instance storeWindow of Prop
	(properties
		x 140
		y 7
		view 366
		loop 3
		signal ignrAct
	)
)

(instance marieHead of Prop
	(properties
		x 195
		y 79
		view 367
		loop 1
		priority 14
		signal fixPriOn
	)
)

(instance insetW of Prop
	(properties
		x 195
		y 110
		view 367
		priority 13
		signal (| ignrAct fixPriOn)
	)
)

(instance knife of Actor
	(properties
		x 88
		y 130
		view 370
		loop 1
		priority 3
		signal $0810
	)
)

(instance marie of Actor
	(properties
		x 180
		y 23
		yStep 1
		view 366
		cycleSpeed 8
		xStep 2
		moveSpeed 8
	)
)

(instance bandit1 of Actor
	(properties
		x 214
		y 145
		view 371
		signal ignrAct
	)
)

(instance bandit2 of Actor
	(properties
		x 210
		y 145
		view 371
		loop 2
		signal ignrAct
	)
)

(instance mArm of Prop
	(properties
		x 176
		y 126
		view 368
		loop 2
		priority 3
		signal (| ignrAct fixPriOn)
	)
)

(instance cigarette of Actor
	(properties
		x 45
		y 134
		view 368
		loop 3
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance smoke of Prop
	(properties
		x 166
		y 57
		view 370
		loop 3
		priority 6
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance stripe of Prop
	(properties
		x 93
		y 117
		view 370
		cel 1
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance tip of Prop
	(properties
		x 53
		y 64
		view 368
		loop 4
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 10
	)
)

(instance sfx1 of Sound
	(properties
		priority 18
	)
)

(instance sfx2 of Sound
	(properties
		priority 17
	)
)

(instance sfx3 of Sound
	(properties
		priority 16
	)
)
