;;; Sierra Script 1.0 - (do not remove this comment)
(script# 92)
(include game.sh)
(use Main)
(use Game)
(use System)

(public
	intro 0
)

(local
	local0
	local1 =  100
)
(instance intro of Region
	
	(method (init)
		(super init:)
		(theIconBar disable:)
		(theIconBar disable: ICON_CONTROL)
		(theGame setCursor: INVIS_CURSOR TRUE 304 172)
		(user canInput: TRUE)
		(keyDownHandler addToFront: self)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) keyDown)
				(== (event message?) ESC)
			)
			(event claimed: TRUE)
			(curRoom setScript: sFadeToBlack)
		)
	)
	
	(method (newRoom n)
		(keyDownHandler delete: self)
		(= initialized FALSE)
		(if
			(not
				(= keep
					(OneOf n 100 105 110 120 140 150 155 160 180 190 220)
				)
			)
			(theIconBar enable:)
		)
	)
)

(instance sFadeToBlack of Script
	
	(method (doit)
		(if (and local0 local1)
			(Palette PALIntensity 0 255 (-- local1))
			(if (not local1)
				(self cue:)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 1)
			)
			(1
				(curRoom newRoom: 26)
			)
		)
	)
)
