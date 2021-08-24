;;; Sierra Script 1.0 - (do not remove this comment)
(script# 302)
(include game.sh)
(use Main)
(use Sleep)
(use Game)
(use System)

(public
	rm302 0
)

(local
	local0
)
(procedure (localproc_000c &tmp evt)
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
		(ego view: vInn loop: 5 cel: 6 posn: 160 75 init:)
		(self setScript: rm302Script)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance rm302Script of Script
	(properties)
	
	(method (changeState newState &tmp str textColor)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(EgoSleeps 6 0)
				(= str
					{Asleep at the Hero's Tale Inn.\0AThe sleep heals and refreshes you.}
				)
				(= textColor (if (< numColors 16) vWHITE else vLMAGENTA))
				(Display str
					p_font 300
					p_width 200
					p_at 80 133
					p_color textColor)
				(localproc_000c)
				(= cycles 2)
			)
			(2
				(curRoom newRoom: 301)
			)
		)
	)
)
