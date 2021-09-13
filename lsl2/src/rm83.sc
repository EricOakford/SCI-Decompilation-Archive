;;; Sierra Script 1.0 - (do not remove this comment)
(script# 83)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use System)

(public
	rm83 0
)

(instance theSound of Sound
	(properties
		number 11
	)
)

(instance rm83 of Room
	(properties
		picture 178
	)
	
	(method (init)
		(Load VIEW 185)
		(Load SOUND 11)
		(super init:)
		(theSound play:)
		(HandsOff)
		(ego
			view: 185
			loop: 0
			cel: 0
			posn: 159 87
			setCycle: Forward
			init:
		)
		(self setScript: rm83Script)
	)
)

(instance rm83Script of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 5)
			)
			(1
				(Print 83 0 #at -1 152 #time 5 #dispose)
				(= seconds 5)
			)
			(2
				(curRoom newRoom: 84)
			)
		)
	)
)
