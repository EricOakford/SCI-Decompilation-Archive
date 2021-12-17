;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51300)
(include sci.sh)
(use Main)
(use TPRoom)
(use Script)
(use Motion)
(use Actor)

(public
	roLycentiasHorrors 0
)

(instance poHorror of Prop
	(properties
		x 313
		y 245
		view -14236
	)
)

(instance soHorror of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poHorror init: setCycle: End self)
			)
			(1 (curRoom newRoom: -14136))
		)
	)
)

(instance roLycentiasHorrors of TPRoom
	(properties
		picture -14236
	)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: -14236)
		(theGame handsOff:)
		(self setScript: soHorror)
	)
	
	(method (setWander)
	)
)
