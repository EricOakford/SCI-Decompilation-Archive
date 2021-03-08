;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkFlo)
(include game.sh)
(use Main)
(use Talker)
(use Actor)
(use System)

(public
	Flo 22
)

(instance Flo of Talker
	(properties
		x 175
		y 5
		view 1008
		talkWidth 85
		back 5
		textX -110
	)
	
	(method (init)
		(= font userFont)
		(= systemWindow gSq5Win_2)
		(if (OneOf curRoomNum 200)
			(self
				view: 1005
				setLoop: 3
				x: 215
				y: 20
				textX: -110
				talkWidth: 110
			)
			(floBust view: 1005 loop: 0)
			(floEyes view: 1005 nsLeft: 19 nsTop: 31)
			(floMouth view: 1005 nsLeft: 19 nsTop: 45)
		)
		(super init: floBust floEyes floMouth &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance floBust of Prop
	(properties
		view 1008
	)
)

(instance floEyes of Prop
	(properties
		nsTop 33
		nsLeft 39
		view 1008
		loop 2
		signal ignrAct
	)
)

(instance floMouth of Prop
	(properties
		nsTop 43
		nsLeft 45
		view 1008
		loop 1
		signal ignrAct
	)
)
