;;; Sierra Script 1.0 - (do not remove this comment)
(script# 152)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Game)
(use System)

(public
	rm152 0
)

(instance theSound of Sound
	(properties
		number 6
	)
)

(instance rm152 of Room
	(properties
		picture 152
		style WIPEUP
		horizon 1
	)
	
	(method (init)
		(super init:)
		(self setScript: rm152Script)
		(HandsOff)
		(ego posn: 99 1099)
		(if (== prevRoomNum 52)
			(Load SOUND 6)
			(theSound play:)
		else
			(Load SOUND 4)
			(theSound number: 4 play:)
		)
	)
)

(instance rm152Script of Script
	(method (doit)
		(super doit:)
		(if (== state 0)
			(ShakeScreen 1 (Random 1 3))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 5)
			)
			(1
				(cond 
					((== currentStatus egoNOWICK)
						(= currentStatus egoDYING)
						(Print 152 0)
					)
					((== currentStatus egoNOTDROPBOMB)
						(= currentStatus egoDYING)
						(Print 152 1 #at -1 152)
					)
					((== currentStatus egoKILLEDBYERUPTION)
						(= currentStatus egoDYING)
						(Print 152 2)
					)
					((== currentStatus egoSUITCASEBOMB)
						(if (== (theSound state?) 3)
							(-- state)
							(= cycles 3)
						else
							(= suitcaseBombState bombEXPLODING)
							(curRoom newRoom: 52)
						)
					)
					((== currentStatus egoBLOWNUP)
						(= currentStatus egoDYING)
						(Print 152 3)
					)
					(else
						(= currentStatus egoDYING)
						(Print 152 4)
					)
				)
			)
		)
	)
)
