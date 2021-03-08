;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1045)
(include game.sh)
(use Main)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm1045 0
)

(instance rm1045 of Room
	(properties
		picture 127
	)
	
	(method (init)
		(LoadMany RES_VIEW 695 688)
		(LoadMany RES_SOUND 160 656)
		(NormalEgo 0)
		(wd40 init:)
		(wd40Parts init:)
		(super init:)
		(curRoom setScript: sWD40Freezes)
	)
)

(instance sWD40Freezes of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(wd40Parts setCycle: EndLoop self)
				(theMusic2 number: 160 setLoop: 1 play:)
			)
			(2
				(wd40Parts
					setLoop: 2
					cel: 0
					x: 132
					y: 126
					setCycle: EndLoop self
				)
			)
			(3
				(wd40Parts setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(4
				(wd40 view: 695 loop: 0 cel: 0 x: 111 y: 163)
				(wd40Parts dispose:)
				(leftShot init:)
				(rightShot init:)
				(= ticks 20)
			)
			(5
				(wd40 setCel: 1)
				(leftShot setLoop: 1 x: 137 y: 108)
				(rightShot setLoop: 1 x: 137 y: 108)
				(theMusic2 number: 656 setLoop: 1 play:)
				(= ticks 20)
			)
			(6
				(wd40 setCel: 2)
				(leftShot setLoop: 2 x: 161 y: 109)
				(rightShot setLoop: 2 x: 161 y: 109)
				(theMusic2 number: 656 setLoop: 1 play:)
				(= ticks 20)
			)
			(7
				(wd40 setCel: 3)
				(leftShot setLoop: 3 x: 176 y: 116)
				(rightShot setLoop: 3 x: 176 y: 116)
				(theMusic2 number: 656 setLoop: 1 play:)
				(= ticks 20)
			)
			(8
				(wd40 setCel: 4)
				(leftShot setLoop: 4 x: 191 y: 123)
				(rightShot setLoop: 4 x: 191 y: 123)
				(theMusic2 number: 656 setLoop: 1 play:)
				(= ticks 20)
			)
			(9
				(theMusic2 stop:)
				(curRoom newRoom: 1040)
				(self dispose:)
			)
		)
	)
)

(instance wd40 of Actor
	(properties
		x 111
		y 163
		view 695
		loop 4
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance wd40Parts of Prop
	(properties
		x 132
		y 126
		view 695
		loop 1
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 10
	)
)

(instance leftShot of View
	(properties
		x 112
		y 116
		view 6951
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance rightShot of View
	(properties
		x 112
		y 116
		view 6951
		cel 1
		priority 15
		signal (| ignrAct fixPriOn)
	)
)
