;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkPercy)
(include game.sh)
(use Main)
(use TalkerWindow)
(use Talker)
(use Actor)

(public
	percyHeadTalk 0
)

(local
	saveWindow
)
(instance percyHeadTalk of Talker
	(properties
		x 67
		y 97
		talkWidth 110
		color 1
		back 5
		view 3010
		textX 130
	)
	
	(method (init)
		(= saveWindow systemWindow)
		(= systemWindow TalkerWindow)
		(if (== global194 51)
			(percyFrame view: (= view 3011))
			(percyEyes view: 3011)
		)
		(super init: 0 0 percyEyes percyFrame &rest)
	)
	
	(method (dispose)
		(= systemWindow saveWindow)
		(super dispose: &rest)
	)
)

(instance percyFrame of View
	(properties
		x 9
		y 14
		view 3010
		loop 3
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
	)
)

(instance percyEyes of Prop
	(properties
		x 71
		y 60
		view 3010
		loop 2
		priority 15
		signal (| ignrAct ignrHrz fixPriOn)
	)
)
