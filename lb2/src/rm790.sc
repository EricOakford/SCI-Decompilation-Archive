;;; Sierra Script 1.0 - (do not remove this comment)
(script# 790)
(include game.sh) (include "790.shm")
(use Main)
(use LBRoom)
(use PolyPath)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm790 0
)

(instance rm790 of LBRoom
	(properties
		picture 790
		style FADEOUT
	)
	
	(method (init)
		(LoadMany RES_VIEW 790)
		(super init:)
		(theMusic number: 110 loop: -1 flags: 1 play:)
		(theIconBar disable:)
		(theGame setCursor: INVIS_CURSOR)
		(sleeper init:)
		(badGuy init:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	
	(method (doit)
		(Palette PALCycle 24 28 10)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(badGuy
					setLoop: 0
					setCycle: Walk
					setMotion: PolyPath 142 102 self
				)
			)
			(2
				(badGuy loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3
				(narrator x: 120 y: 140)
				(messager say: N_ROOM NULL NULL 0 self)
			)
			(4
				(badGuy loop: 2 cel: 0 setCycle: Forward)
				(sleeper setCycle: Forward)
				(bullets init: setPri: 15 setCycle: EndLoop self)
				(theMusic number: 1 loop: 1 flags: mNOPAUSE play:)
				(theMusic2 number: 653 loop: -1 flags: mNOPAUSE play:)
			)
			(5
				(sleeper setCycle: 0)
				(badGuy setCycle: 0)
				(= seconds 4)
				(theMusic2 stop:)
			)
			(6
				(badGuy loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(7
				(badGuy
					setLoop: 4
					setCycle: Walk
					setMotion: PolyPath 0 257 self
				)
			)
			(8
				(curRoom newRoom: 780)
				(self dispose:)
			)
		)
	)
)

(instance badGuy of Actor
	(properties
		y 257
		view 790
		signal ignrAct
	)
)

(instance sleeper of Prop
	(properties
		x 213
		y 93
		view 790
		loop 6
	)
)

(instance bullets of Prop
	(properties
		x 184
		y 29
		view 790
		loop 5
	)
)
