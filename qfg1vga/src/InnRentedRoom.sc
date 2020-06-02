;;; Sierra Script 1.0 - (do not remove this comment)
(script# 302)
(include game.sh) (include "302.shm")
(use Main)
(use Sleep)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm302 0
)

(local
	local0
)
(procedure (localproc_0026 &tmp evt)
	(while (not ((= evt (Event new: (| mouseDown keyDown))) type?))
		(evt dispose:)
	)
	(evt dispose:)
)

(instance rm302 of Room
	(properties
		picture 400
		style BLACKOUT
	)
	
	(method (init)
		(HandsOff)
		(super init:)
		(StatusLine enable:)
		(ego view: 350 loop: 0 cel: 0 posn: 86 42 init:)
		(candle init: setCycle: Forward)
		(lightoffshirt init: setCycle: Forward)
		(lightoffcape init: setCycle: Forward)
		(lightoffdonob init: setCycle: Forward)
		(lightoffcurtain init: setCycle: Forward)
		(lightoffbody init: setCycle: Forward)
		(self setScript: rm302Script)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance candle of Actor
	(properties
		x 141
		y 107
		view 350
		loop 1
	)
)

(instance lightoffshirt of Actor
	(properties
		x 161
		y 93
		view 350
		loop 2
		cel 1
	)
)

(instance lightoffcape of Actor
	(properties
		x 169
		y 92
		view 350
		loop 3
		cel 2
	)
)

(instance lightoffdonob of Actor
	(properties
		x 215
		y 113
		view 350
		loop 4
		cel 3
	)
)

(instance lightoffcurtain of Actor
	(properties
		x 98
		y 79
		view 350
		loop 5
	)
)

(instance lightoffbody of Actor
	(properties
		x 111
		y 105
		view 350
		loop 6
		cel 1
	)
)

(instance rm302Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(EgoSleeps 5 0)
				(messager say: N_ROOM)
				(localproc_0026)
				(= cycles 2)
			)
			(2
				(curRoom newRoom: 301)
			)
		)
	)
)
