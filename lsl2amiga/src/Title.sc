;;; Sierra Script 1.0 - (do not remove this comment)
(script# 90)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm90 0
)

(local
	lightBall
	openingCredits
	i
	songEnded
)
(instance mThemeSong of Sound
	(properties
		number 101
		priority 20
	)
)

(instance rm90 of Room
	(properties
		picture 90
		style WIPEDOWN
	)
	
	(method (init)
		(Load VIEW 800)
		(Load VIEW 193)
		(Load VIEW 815)
		(Load VIEW 801)
		(Load VIEW 809)
		(Load SOUND 101)
		(Load PICTURE 91)
		(StatusLine disable:)
		(TheMenuBar hide:)
		(super init:)
		(mThemeSong play:)
		(addToPics add: aView1 doit:)
		(aGirl
			illegalBits: 0
			setPri: 13
			setCycle: Walk
			cycleSpeed: 1
			moveSpeed: 1
			init:
			stopUpd:
		)
		(ego
			view: 815
			ignoreActors:
			illegalBits: 0
			setPri: 15
			moveSpeed: 0
			cycleSpeed: 0
			posn: 123 1123
			init:
		)
		(self setScript: rm90Script)
		(HandsOff)
		(theGame setSpeed: 6)
	)
)

(instance rm90Script of Script
	(method (doit)
		(super doit:)
		(if (and (== -1 (mThemeSong prevSignal?)) (== state 40))
			(curRoom newRoom: 91)
		)
	)
	
	(method (changeState newState &tmp lightX lightY creditX creditY)
		(switch (= state newState)
			(0
				(= cycles 20)
			)
			(1
				(addToPics add: aView2 doit:)
				(= cycles 30)
			)
			(2
				(addToPics add: aView3 doit:)
				(= cycles 20)
				(theGame setSpeed: 3)
			)
			(3
				(switch (Random 0 16)
					(0
						(= lightX 149)
						(= lightY 39)
					)
					(1
						(= lightX 64)
						(= lightY 47)
					)
					(2
						(= lightX 161)
						(= lightY 77)
					)
					(3
						(= lightX 234)
						(= lightY 88)
					)
					(4
						(= lightX 114)
						(= lightY 116)
					)
					(5
						(= lightX 91)
						(= lightY 97)
					)
					(6
						(= lightX 179)
						(= lightY 29)
					)
					(7
						(= lightX 61)
						(= lightY 97)
					)
					(8
						(= lightX 255)
						(= lightY 64)
					)
					(9
						(= lightX 130)
						(= lightY 86)
					)
					(10
						(= lightX 143)
						(= lightY 80)
					)
					(11
						(= lightX 212)
						(= lightY 85)
					)
					(12
						(= lightX 48)
						(= lightY 107)
					)
					(13
						(= lightX 205)
						(= lightY 125)
					)
					(14
						(= lightX 275)
						(= lightY 121)
					)
					(15
						(= lightX 92)
						(= lightY 129)
					)
					(16
						(= lightX 182)
						(= lightY 132)
					)
				)
				(ego posn: lightX lightY cel: 0 setCycle: EndLoop self)
				(if (< (++ i) 10)
					(= state 2)
				)
			)
			(4
				(ego hide:)
				(theGame setSpeed: 6)
				(aGirl setMotion: MoveTo 109 100 self)
			)
			(5
				(aGirl setMotion: MoveTo 175 100 self)
				(ego
					view: 193
					loop: 0
					cel: 0
					posn: 86 79
					setPri: 15
					show:
					setCycle: EndLoop self
				)
			)
			(6
				(ego setCycle: BegLoop self)
			)
			(7
				(ego hide:)
			)
			(8
				(aGirl setMotion: MoveTo 220 100 self)
				(ego
					loop: 1
					cel: 0
					posn: 148 106
					show:
					setCycle: EndLoop self
				)
			)
			(9
				(ego setCycle: BegLoop self)
			)
			(10
				(ego hide:)
			)
			(11
				(aGirl setMotion: MoveTo 268 100 self)
				(ego loop: 2 cel: 0 posn: 199 57 show: setCycle: EndLoop self)
			)
			(12
				(ego setCel: setStep: 1 15 setMotion: MoveTo 199 234)
			)
			(13
				(ego hide:)
				(aGirl hide:)
				(= lightBall ego)
				(= openingCredits aGirl)
				(curRoom drawPic: 91 WIPEUP)
				(= cycles 10)
			)
			(14
				(theGame setSpeed: 3)
				(= lightX (- (Random 0 420) 50))
				(lightBall
					view: 801
					setLoop: 0
					setCycle: 0
					setCel: (Random 0 8)
					posn: lightX 233
					setStep: 15 15
					illegalBits: 0
					ignoreActors:
					show:
					setMotion: MoveTo 159 47 self
				)
			)
			(15
				(= lightX (- (Random 0 420) 50))
				(= creditX (Random 50 270))
				(= creditY (Random 70 180))
				(lightBall
					setCel: (Random 0 8)
					posn: lightX -33
					setMotion: MoveTo creditX creditY self
				)
				(openingCredits
					view: 801
					setLoop: 1
					setCycle: 0
					setCel: 0
					setPri: 1
					setMotion: 0
					ignoreActors:
					illegalBits: 0
					posn: 159 46
					show:
				)
			)
			(16
				(lightBall setLoop: 1 setCel: 1)
				(= cycles 35)
			)
			(17
				(= lightX (- (Random 0 420) 50))
				(= creditX (Random 50 270))
				(= creditY (Random 70 180))
				(lightBall
					setLoop: 0
					setCel: (Random 0 8)
					posn: lightX -33
					setMotion: MoveTo creditX creditY self
				)
			)
			(18
				(lightBall setLoop: 1 setCel: 2)
				(= cycles 35)
			)
			(19
				(= lightX (- (Random 0 420) 50))
				(= creditX (Random 50 270))
				(= creditY (Random 70 180))
				(lightBall
					setLoop: 0
					setCel: (Random 0 8)
					posn: lightX -33
					setMotion: MoveTo creditX creditY self
				)
			)
			(20
				(lightBall setLoop: 1 setCel: 3)
				(= cycles 35)
			)
			(21
				(openingCredits hide:)
				(= lightX (- (Random 0 420) 50))
				(lightBall
					setLoop: 0
					setCel: (Random 0 8)
					posn: lightX 233
					setMotion: MoveTo 159 47 self
				)
			)
			(22
				(= lightX (- (Random 0 420) 50))
				(= creditX (Random 50 270))
				(= creditY (Random 70 180))
				(lightBall
					setCel: (Random 0 8)
					posn: lightX -33
					setMotion: MoveTo creditX creditY self
				)
				(openingCredits setLoop: 4 setCel: 0 posn: 159 46 show:)
			)
			(23
				(lightBall setLoop: 4 setCel: 1)
				(= cycles 35)
			)
			(24
				(= lightX (- (Random 0 420) 50))
				(= creditX (Random 50 270))
				(= creditY (Random 70 180))
				(lightBall
					setLoop: 0
					setCel: (Random 0 8)
					posn: lightX -33
					setMotion: MoveTo creditX creditY self
				)
			)
			(25
				(lightBall setLoop: 4 setCel: 2)
				(= cycles 35)
			)
			(26
				(= lightX (- (Random 0 420) 50))
				(= creditX (Random 50 270))
				(= creditY (Random 70 180))
				(lightBall
					setLoop: 0
					setCel: (Random 0 8)
					posn: lightX -33
					setMotion: MoveTo creditX creditY self
				)
			)
			(27
				(lightBall setLoop: 4 setCel: 3)
				(= cycles 35)
			)
			(28
				(= lightX (- (Random 0 420) 50))
				(= creditX (Random 50 270))
				(= creditY (Random 70 180))
				(lightBall
					setLoop: 0
					setCel: (Random 0 8)
					posn: lightX -33
					setMotion: MoveTo creditX creditY self
				)
			)
			(29
				(lightBall setLoop: 4 setCel: 4)
				(= cycles 35)
			)
			(30
				(openingCredits hide:)
				(= lightX (- (Random 0 420) 50))
				(lightBall
					setLoop: 0
					setCel: (Random 0 8)
					posn: lightX 233
					setMotion: MoveTo 159 47 self
				)
			)
			(31
				;Amiga addition
				(= lightX (- (Random 0 420) 50))
				(= creditX (Random 50 270))
				(= creditY (Random 70 180))
				(lightBall
					setCel: (Random 0 8)
					posn: lightX -33
					setMotion: MoveTo creditX creditY self
				)
				(openingCredits setLoop: 5 setCel: 0 posn: 159 46 show:)
			)
			(32
				(lightBall setLoop: 5 setCel: 1)
				(= cycles 35)
			)
			(33
				(= lightX (- (Random 0 420) 50))
				(= creditX (Random 50 270))
				(= creditY (Random 70 180))
				(lightBall
					setLoop: 0
					setCel: (Random 0 8)
					posn: lightX -33
					setMotion: MoveTo creditX creditY self
				)
			)
			(34
				(lightBall setLoop: 5 setCel: 2)
				(= cycles 35)
			)
			(35
				(= lightX (- (Random 0 420) 50))
				(= creditX (Random 50 270))
				(= creditY (Random 70 180))
				(lightBall
					setLoop: 0
					setCel: (Random 0 8)
					posn: lightX -33
					setMotion: MoveTo creditX creditY self
				)
			)
			(36
				(lightBall setLoop: 5 setCel: 3)
				(= cycles 35)
			)
			(37
				(openingCredits hide:)
				(= lightX (- (Random 0 420) 50))
				(lightBall
					setLoop: 0
					setCel: (Random 0 8)
					posn: lightX 233
					setMotion: MoveTo 159 47 self
				)
			)
			(38
				(= lightX (- (Random 0 420) 50))
				(= creditX (Random 50 270))
				(= creditY (Random 70 180))
				(lightBall
					setLoop: 0
					cel: (Random 0 8)
					posn: lightX -33
					setMotion: MoveTo creditX creditY self
				)
				(openingCredits setLoop: 2 cel: 0 show:)
			)	;end Amiga addition
			(39
				(theGame setSpeed: 6)
				(lightBall setLoop: 3 setCel: 0)
				(openingCredits cycleSpeed: 2 setCycle: Forward)
				(= cycles 72)
			)
		)
	)
	
	(method (handleEvent event)
		(if (not (event claimed?))
			(theGame restart:)
		)
	)
)

(instance aView1 of PicView
	(properties
		y 47
		x 159
		view 800
		priority 8
		signal ignrAct
	)
)

(instance aGirl of Actor
	(properties
		y 99
		x 22
		view 809
		signal ignrAct
	)
)

(instance aView2 of PicView
	(properties
		y 98
		x 159
		view 800
		loop 1
		priority 10
		signal ignrAct
	)
)

(instance aView3 of PicView
	(properties
		y 134
		x 162
		view 800
		loop 2
		priority 12
		signal ignrAct
	)
)
