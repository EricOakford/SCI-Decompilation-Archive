;;; Sierra Script 1.0 - (do not remove this comment)
(script# 92)
(include sci.sh)
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
(instance intro of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(theIconBar disable:)
		(theIconBar disable: 7)
		(theGame setCursor: 996 1 304 172)
		(user canInput: 1)
		(keyDownHandler addToFront: self)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) evKEYBOARD)
				(== (event message?) KEY_ESCAPE)
			)
			(event claimed: 1)
			(curRoom setScript: sFadeToBlack)
		)
	)
	
	(method (newRoom n)
		(keyDownHandler delete: self)
		(= initialized 0)
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
	(properties)
	
	(method (doit)
		(if (and local0 local1)
			(Palette palSET_INTENSITY 0 255 (-- local1))
			(if (not local1) (self cue:))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= local0 1))
			(1 (curRoom newRoom: 26))
		)
	)
)
