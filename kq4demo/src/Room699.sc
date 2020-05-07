;;; Sierra Script 1.0 - (do not remove this comment)
(script# 699)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Game)
(use Actor)
(use System)

(public
	Room699 0
)

(local
	local0
	oldDeadState
	[local2 2]
	local4
	[local5 3]
)
(instance mySound of Sound
	(properties
		number 1
	)
)

(instance Room699 of Room
	(properties
		picture 96
		style IRISIN
	)
	
	(method (init)
		(Load VIEW 879)
		(super init:)
		(playMusic cue:)
		((View new:)
			view: 879
			loop: 0
			cel: 0
			posn: 124 192
			addToPic:
		)
		((View new:)
			view: 879
			loop: 0
			cel: 1
			posn: 165 192
			addToPic:
		)
		((View new:)
			view: 879
			loop: 0
			cel: 2
			posn: 206 192
			addToPic:
		)
		(= oldDeadState dead)
		(= dead FALSE)
	)
	
	(method (doit)
		(if (== (mySound prevSignal?) -1) (playMusic cue:))
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(event claimed: TRUE)
		(= local4 0)
		(if oldDeadState (= oldDeadState FALSE) (theGame restart:))
		(return
			(if
				(and
					(== (event type?) keyDown)
					(== (event message?) `#2)
				)
				(event claimed: TRUE)
				(Print 699 0 #at -1 10 #dispose)
			else
				(cast eachElementDo: #dispose)
				(mySound fade:)
				(cls)
				(curRoom newRoom: 120)
			)
		)
	)
)

(instance bannerSound of Sound
	(properties
		number 1
	)
)

(instance playMusic of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (bannerSound play: self))
			(1
				(cls)
				(curRoom newRoom: 120)
			)
		)
	)
)
