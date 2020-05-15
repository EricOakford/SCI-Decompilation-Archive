;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmTitle) ;710
(include game.sh)
(use Main)
(use LLRoom)
(use System)

(public
	rm710 0
)

(instance rm710 of LLRoom
	(properties
		picture 700
	)
	
	(method (init)
		(theGame setCursor: 900 TRUE 350 185)
		(Load PICTURE 710)
		(theIconBar disable:)
		(super init:)
		(theMusic number: 700 loop: -1 vol: 127 flags: 1 play:)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(curRoom style: SCROLLUP setScript: sRoom 0 710)
	)
	
	(method (handleEvent event)
		(if (event type?)
			(event claimed: TRUE)
			(curRoom newRoom: 720)
		)
	)
	
	(method (newRoom n)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super newRoom: n)
	)
)

(instance sRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(curRoom drawPic: register)
				(if (== register 710)
					(= register 700)
					(curRoom style: SCROLLDOWN)
				else
					(= register 710)
					(curRoom style: SCROLLUP)
				)
				(-- state)
				(= seconds 10)
			)
		)
	)
)
