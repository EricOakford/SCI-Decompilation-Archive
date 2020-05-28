;;; Sierra Script 1.0 - (do not remove this comment)
(script# 120)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use Motion)
(use System)

(public
	rm120 0
)

(instance rm120 of SQRoom
	(properties
		picture 120
	)
	
	(method (init)
		(super init:)
		(self setScript: shipScript)
		(globalSound hold: 3)
	)
)

(instance shipScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(ship init: hide:)
				(= seconds 3)
			)
			(2
				(ship show: setCycle: EndLoop self)
				(= seconds 2)
			)
			(3 (curRoom newRoom: 535))
		)
	)
)

(instance ship of Sq4Actor
	(properties
		x 186
		y 87
		view 120
		loop 4
	)
)
