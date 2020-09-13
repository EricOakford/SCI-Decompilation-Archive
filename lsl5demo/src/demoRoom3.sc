;;; Sierra Script 1.0 - (do not remove this comment)
(script# 3)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoRoom3 0
)

(instance demoRoom3 of Room
	(properties
		picture 345
		style DISSOLVE
	)
	
	(method (init)
		(Load PICTURE 345)
		(Load VIEW 345 346)
		(super init:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(larry init:)
				(arms init:)
				(patti init:)
				(pattiDress init:)
				(DoDisplay
					{Teamed up again, Passionate Patti and Larry}
					#at -1 5
					#color myTextColor4
					#font 2510
					#mode teJustCenter
				)
				(DoDisplay
					{bring new meanings to the term}
					#at -1 20
					#color myTextColor4
					#font 2510
					#mode teJustCenter
				)
				(DoDisplay
					{"INTERACTIVE SOFTWARE!"}
					#at -1 160
					#color myTextColor3
					#font 2510
					#mode teJustCenter
				)
				(= cycles 20)
			)
			(1
				(larry setCycle: EndLoop)
				(arms setLoop: 2 cycleSpeed: 0 setCycle: EndLoop)
				(pattiDress setPri: 12 setCycle: EndLoop self)
				(zipperSound play:)
			)
			(2
				(arms setLoop: 3 setCycle: EndLoop)
				(patti setCycle: EndLoop)
				(pattiDress dispose:)
				(pattiArm init: setPri: 12 setCycle: EndLoop)
				(= cycles 10)
			)
			(3
				(pattiBelly init: setCycle: Forward)
				(= seconds 3)
			)
			(4
				(UnLoad PICTURE 345)
				(UnLoad VIEW 345)
				(UnLoad VIEW 346)
				(curRoom newRoom: 4)
			)
		)
	)
)

(instance larry of Prop
	(properties
		x 123
		y 118
		view 345
	)
)

(instance arms of Prop
	(properties
		x 123
		y 118
		view 345
		loop 1
		priority 11
		signal (| ignrAct fixPriOn)
		cycleSpeed 2
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward)
	)
)

(instance patti of Prop
	(properties
		x 164
		y 140
		view 346
		cycleSpeed 1
	)
)

(instance pattiDress of Prop
	(properties
		x 164
		y 140
		view 346
		loop 2
	)
)

(instance pattiBelly of Prop
	(properties
		x 164
		y 140
		view 346
		loop 4
	)
)

(instance pattiArm of Prop
	(properties
		x 161
		y 106
		view 346
		loop 1
		cycleSpeed 1
	)
)

(instance zipperSound of Sound
	(properties
		number 3
	)
)
