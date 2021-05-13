;;; Sierra Script 1.0 - (do not remove this comment)
(script# 790)
(include sci.sh)
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
		style $000a
	)
	
	(method (init)
		(LoadMany 128 790)
		(super init:)
		(theMusic number: 110 loop: -1 flags: 1 play:)
		(theIconBar disable:)
		(theGame setCursor: 996)
		(sleeper init:)
		(badGuy init:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	(properties)
	
	(method (doit)
		(Palette palANIMATE 24 28 10)
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
				(badGuy loop: 1 cel: 0 setCycle: End self)
			)
			(3
				(narrator x: 120 y: 140)
				(messager say: 1 0 0 0 self)
			)
			(4
				(badGuy loop: 2 cel: 0 setCycle: Fwd)
				(sleeper setCycle: Fwd)
				(bullets init: setPri: 15 setCycle: End self)
				(theMusic number: 1 loop: 1 flags: 1 play:)
				(theMusic2 number: 653 loop: -1 flags: 1 play:)
			)
			(5
				(sleeper setCycle: 0)
				(badGuy setCycle: 0)
				(= seconds 4)
				(theMusic2 stop:)
			)
			(6
				(badGuy loop: 3 cel: 0 setCycle: End self)
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
		signal $4000
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
