;;; Sierra Script 1.0 - (do not remove this comment)
(script# 140)
(include game.sh) (include "140.shm")
(use Main)
(use LBRoom)
(use Scaler)
(use PolyPath)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm140 0
)

(instance rm140 of LBRoom
	(properties
		picture 140
		style FADEOUT
		vanishingX 7
		vanishingY 54
	)
	
	(method (init)
		(LoadMany RES_VIEW 151 830 142)
		(LoadMany RES_SOUND 140)
		(self setRegions: 92)
		(ego
			init:
			view: 830
			loop: 3
			cel: 1
			posn: 167 158
			setScale: Scaler 125 0 190 24
			cycleSpeed: 6
		)
		(super init:)
		(dad init:)
		(theMusic number: 140 flags: mNOPAUSE loop: -1 play:)
		(self setScript: sCartoon)
	)
	
	(method (dispose)
		(theMusic fade: 0 30 12 1)
		(super dispose: &rest)
	)
)

(instance sCartoon of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 1880 1)
					disposeWhenDone: FALSE
					x: 5
					y: 5
					textX: 10
					textY: 140
					talkWidth: 280
				)
				((ScriptID 1881 2)
					disposeWhenDone: FALSE
					x: 211
					y: 5
					textX: -196
					textY: 140
					talkWidth: 280
				)
				(= cycles 1)
			)
			(1
				(narrator x: 60 y: 100 keepWindow: 0)
				(messager say: N_CARTOON NULL NULL 0 self)
			)
			(2 (= cycles 2))
			(3
				(ego setMotion: PolyPath 167 145 self)
			)
			(4
				(ego hide:)
				(dad loop: 0 setCycle: EndLoop self)
			)
			(5 (= seconds 2))
			(6 (dad setCycle: BegLoop self))
			(7
				(ego show: setLoop: 4 setMotion: MoveTo 299 340 self)
				(= cycles 1)
			)
			(8
				(dad
					loop: 1
					cel: 0
					posn: 168 143
					cycleSpeed: 10
					setCycle: CycleTo 6 1 self
				)
			)
			(9 (= ticks 20))
			(10
				(dad setCycle: CycleTo 4 -1 self)
			)
			(11 (= ticks 20))
			(12 (dad setCycle: CycleTo 6 1 self))
			(13 (dad setCycle: BegLoop self))
			(14 0)
			(15
				(dad addToPic:)
				(creditTitle
					init:
					setPri: 15
					setMotion: MoveTo 50 82 self
				)
				(creditName
					init:
					setPri: 15
					setMotion: MoveTo 107 125 self
				)
			)
			(16 0)
			(17 (= seconds 3))
			(18
				(creditTitle setMotion: MoveTo -200 82 self)
				(creditName setMotion: MoveTo 107 209 self)
			)
			(19 0)
			(20 (= seconds 3))
			(21
				(creditTitle
					loop: 10
					posn: 50 -100
					setMotion: MoveTo 50 82 self
				)
				(creditName
					loop: 10
					posn: 107 -57
					setMotion: MoveTo 107 125 self
				)
			)
			(22 0)
			(23 (= seconds 3))
			(24
				(creditTitle setMotion: MoveTo 320 82 self)
				(creditName setMotion: MoveTo -150 125 self)
			)
			(25 0)
			(26
				(curRoom newRoom: 150)
				(self dispose:)
			)
		)
	)
)

(instance dad of Actor
	(properties
		x 170
		y 143
		view 142
		loop 1
		signal ignrAct
		cycleSpeed 10
	)
)

(instance creditTitle of Actor
	(properties
		x 50
		y -2
		view 151
		loop 3
		signal (| ignrAct ignrHrz fixedLoop)
		moveSpeed 0
	)
)

(instance creditName of Actor
	(properties
		x 230
		y 125
		view 151
		loop 3
		cel 1
		signal (| ignrAct ignrHrz fixedLoop)
		moveSpeed 0
	)
)
