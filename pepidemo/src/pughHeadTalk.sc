;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkPugh)
(include game.sh)
(use Main)
(use TalkerWindow)
(use Talker)
(use Actor)

(public
	pughHeadTalk 0
)

(local
	saveWindow
)
(instance pughHeadTalk of Talker
	(properties
		x 23
		y 108
		talkWidth 110
		color 1
		back 5
		view 3000
		textX 130
	)
	
	(method (init)
		(= saveWindow systemWindow)
		(= systemWindow TalkerWindow)
		(super init: 0 0 pughEyes pughFrame &rest)
	)
	
	(method (dispose)
		(= systemWindow saveWindow)
		(super dispose: &rest)
	)
)

(instance pughFrame of View
	(properties
		x 9
		y 14
		view 3000
		loop 3
		priority 15
		signal (| ignrAct ignrHrz fixPriOn)
	)
)

(instance pughEyes of Prop
	(properties
		x 61
		y 66
		view 3000
		loop 2
		priority 15
		signal (| ignrAct ignrHrz fixPriOn)
	)
)
