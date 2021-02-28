;;; Sierra Script 1.0 - (do not remove this comment)
(script# 880)
(include game.sh) (include "830.shm")
(use Main)
(use RandCyc)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm880 0
)

(local
	saveBits
	saveBits2
)
(instance rm880 of Room
	(properties
		picture 880
	)
	
	(method (init)
		(super init:)
		(cSound number: 880 setLoop: -1 play:)
		(avisFangs init:)
		(avisEyes init:)
		(self setScript: theVeryLastScript)
	)
)

(instance theVeryLastScript of Script
	
	(method (changeState newState &tmp underBits [str 30])
		(switch (= state newState)
			(0
				(= underBits 0)
				(= seconds 2)
			)
			(1
				(Message MsgGet 830 N_ENDING V_DOIT C_TO_BE_CONTINUED 1 @str)
				(= saveBits
					(Display @str
						p_font 2510
						p_at 60 9
						p_color 25
						p_save
					)
				)
				(= saveBits2
					(Display @str
						p_font 2510
						p_at 59 8
						p_color 30
						p_save
					)
				)
				(= seconds 5)
			)
			(2
				(Display @underBits p_restore saveBits2)
				(Display @underBits p_restore saveBits)
				(= seconds 2)
			)
			(3
				(glorySign init: setCycle: EndLoop self)
			)
			(4 (= seconds 3))
			(5
				(titleSign init:)
				(ballFx init: setCycle: Forward)
				(egoDude init: setCycle: RandCycle)
				(DrawPic 880 PIXELDISSOLVE)
				(= seconds 2)
			)
			(6
				(avisFangs setCycle: EndLoop)
				(avisEyes setCycle: EndLoop self)
			)
			(7 (= seconds 10))
			(8 (cSound fade: 0 6 3 1 self))
			(9 (curRoom newRoom: 52))
		)
	)
)

(instance avisFangs of Prop
	(properties
		x 58
		y 123
		view 880
	)
)

(instance avisEyes of Prop
	(properties
		x 57
		y 108
		view 880
		loop 1
	)
)

(instance ballFx of Prop
	(properties
		x 154
		y 185
		view 883
	)
)

(instance egoDude of Prop
	(properties
		x 161
		y 168
		view 883
		loop 1
	)
)

(instance glorySign of Prop
	(properties
		x 55
		y 9
		view 884
	)
)

(instance titleSign of View
	(properties
		x 31
		y 42
		view 884
		loop 1
		signal ignrAct
	)
)
