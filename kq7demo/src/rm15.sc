;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15)
(include game.sh)
(use Main)
(use Cursor)
(use Game)
(use Actor)
(use System)

(public
	rm15 0
)

(local
	local0
)
(instance rm15 of Room
	(properties
		style SHOW_FADE_IN
		exitStyle SHOW_FADE_OUT
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler add: curRoom)
		(keyDownHandler add: curRoom)
		(directionHandler add: curRoom)
		(self setScript: doTheLogo)
	)
	
	(method (dispose)
		(mouseDownHandler delete: curRoom)
		(keyDownHandler delete: curRoom)
		(directionHandler delete: curRoom)
		(super dispose:)
	)
	
	(method (doVerb)
		(if (== (doTheLogo state?) 2) (doTheLogo cue:))
	)
)

(instance doTheLogo of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 1)
			)
			(1
				(logo init:)
				(= seconds 1)
			)
			(2
				(= seconds 12)
			)
			(3
				(curRoom newRoom: 30)
			)
		)
	)
)

(instance logo of View
	(properties
		view 1050
	)
)

(instance myInvisCursor of Cursor
	(properties
		view INVIS_CURSOR
	)
)
