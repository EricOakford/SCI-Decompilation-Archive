;;; Sierra Script 1.0 - (do not remove this comment)
(script# 210)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm210 0
)

(instance rm210 of Room
	(properties
		picture 211
	)
	
	(method (init)
		(super init:)
		(loveSound vol: 90 init: play:)
		(kissers setPri: 14 init:)
		(curRoom setScript: mama)
	)
	
	(method (doit)
		(if (== (loveSound prevSignal?) -1)
			(curRoom newRoom: 320)
		)
		(super doit:)
	)
)

(instance voice of Sound
	(properties
		number 505
		priority 15
	)
)

(instance kissers of Prop
	(properties
		x 150
		y 150
		view 217
	)
)

(instance rHead of Prop
	(properties
		x 120
		y 73
		view 216
		signal ignrAct
	)
)

(instance mHead of Prop
	(properties
		x 161
		y 87
		view 216
		loop 1
		signal ignrAct
	)
)

(instance mama of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(kissers cycleSpeed: 1 setCycle: EndLoop self)
			)
			(1 (= cycles 8))
			(2
				(if cDAudio
					(voice init: play:)
				)
				(Display 210 0
					p_at 72 16
					p_time 7
					p_font SYSFONT
					p_color 0
				)
				(Display 210 1
					p_at 53 26
					p_time 7
					p_font SYSFONT
					p_color 0
				)
				(Display 210 0
					p_at 71 15
					p_time 7
					p_font SYSFONT
					p_color 30
				)
				(Display 210 1
					p_at 52 25
					p_time 7
					p_font SYSFONT
					p_color 30
				)
				(kissers cycleSpeed: 1 setCycle: BegLoop self)
			)
			(3 (= cycles 20))
			(4
				(kissers loop: 1 cycleSpeed: 1 setCycle: EndLoop)
				(= cycles 42)
			)
			(5
				(kissers loop: 2 cel: 0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(6
				(= global126 1)
				(curRoom drawPic: 211)
				(= cycles 2)
			)
			(7
				(= global126 0)
				(kissers dispose:)
				(mHead setPri: 14 init:)
				(rHead setPri: 15 init:)
				(curRoom overlay: 216)
				(= cycles 10)
			)
			(8 (= cycles 23))
			(9
				(mHead setPri: 14 cycleSpeed: 1 setCycle: EndLoop)
				(rHead setPri: 15 cycleSpeed: 1 setCycle: EndLoop)
				(= cycles 43)
			)
			(10 (self dispose:))
		)
	)
)
