;;; Sierra Script 1.0 - (do not remove this comment)
(script# TOWN) ;801
(include game.sh) (include "811.shm")
(use Main)
(use Game)
(use System)

(public
	Town 0
)

(instance Town of Region
	(properties)
	
	(method (init)
		(if
		(not (OneOf curRoomNum 300 310 311 320 330 333 334))
			(cSound stop:)
		)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp theVerb)
		(if
			(or
				(and
					(== (event type?) keyDown)
					(== (event message?) ENTER)
				)
				(and
					(== (event type?) mouseDown)
					(not (event modifiers?))
				)
			)
			(if (== (theIconBar curIcon?) (theIconBar at: ICON_USEIT))
				(= theVerb ((theIconBar curInvIcon?) message?))
			else
				(= theVerb ((theIconBar curIcon?) message?))
			)
			(event claimed: (self doVerb: theVerb))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAGGER
				(messager say: N_STREET V_ROCK NULL 1 0 STREET)
			)
			(V_SWORD
				(messager say: N_STREET V_ROCK NULL 1 0 STREET)
			)
			(V_ROCK
				(messager say: N_STREET V_ROCK NULL 1 0 STREET)
			)
		)
	)
)
