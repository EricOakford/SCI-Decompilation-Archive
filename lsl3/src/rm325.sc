;;; Sierra Script 1.0 - (do not remove this comment)
(script# 325)
(include game.sh)
(use Main)
(use Face)
(use Intrface)
(use Game)
(use System)

(public
	rm325 0
)

(instance rm325 of Room
	(properties
		picture 325
	)
	
	(method (init)
		(super init:)
		(self
			setRegions: FACE
			setLocales: GIRL
			setScript: RoomScript
		)
		(NotifyScript 71 1 103 63)
		(NotifyScript 71 2 184 60)
		(NotifyScript 71 3 106 65)
		(NotifyScript 71 4 181 65)
		(NotifyScript 71 5 143 107)
		(NotifyScript 71 6 141 152)
		(HandsOff)
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 11)
			)
			(1
				(AnimateFace 2 20)
				(= cycles 33)
			)
			(2
				(Print 325 0)
				(= cycles 11)
			)
			(3
				(curRoom newRoom: 323)
			)
		)
	)
)
