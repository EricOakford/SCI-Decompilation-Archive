;;; Sierra Script 1.0 - (do not remove this comment)
(script# BOSSKEY)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use Menu)

(public
	rm90 0
)

(instance rm90 of Room
	(properties
		picture 90
	)
	
	(method (init)
		(cls)
		(super init:)
		(TheMenuBar hide:)
		(StatusLine disable:)
		(ego hide:)
		(HandsOff)
		(User canInput: TRUE)
		(Animate 0)
	)
	
	(method (handleEvent event)
		(if
			(or
				(== (event type?) mouseUp)
				(event claimed?)
			)
			(return)
		)
		(Print 90 0)
		(Print 90 1 #at -1 144)
		(event claimed: TRUE)
	)
)
