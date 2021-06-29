;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkRichard)
(include game.sh)
(use Main)
(use TalkerWindow)
(use Talker)
(use Actor)

(public
	richardHeadTalk 0
)

(local
	saveWindow
)
(instance richardHeadTalk of Talker
	(properties
		x 37
		y 77
		talkWidth 110
		color 1
		back 5
		view 3004
		textX 130
	)
	
	(method (init)
		(= saveWindow systemWindow)
		(= systemWindow TalkerWindow)
		(super init: 0 0 richardEyes richardFrame &rest)
	)
	
	(method (dispose)
		(= systemWindow saveWindow)
		(super dispose: &rest)
	)
)

(instance richardFrame of View
	(properties
		x 9
		y 14
		view 3004
		loop 3
		priority 15
		signal (| ignrAct ignrHrz fixPriOn)
	)
)

(instance richardEyes of Prop
	(properties
		x 56
		y 61
		view 3004
		loop 2
		priority 15
		signal (| ignrAct ignrHrz fixPriOn)
	)
)
