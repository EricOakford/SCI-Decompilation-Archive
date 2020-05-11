;;; Sierra Script 1.0 - (do not remove this comment)
(script# 697)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Game)
(use User)
(use System)

(public
	Room697 0
)

(instance Room697 of Room
	(properties
		picture 697
		style DISSOLVE
	)
	
	(method (init)
		(super init:)
		(= isHandsOff TRUE)
		(self setScript: showMessage)
	)
)

(instance showMessage of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE canInput: FALSE)
				(= timedMessage (Print 697 0 #at -1 118 #dispose))
				((Sound new:) number: 67 play: self)
			)
			(1
				(cls)
				(= curRoomNum prevRoomNum)
				(= isHandsOff FALSE)
				(User canControl: TRUE canInput: TRUE)
				(curRoom newRoom: nightRoom)
			)
		)
	)
)
