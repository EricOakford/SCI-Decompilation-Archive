;;; Sierra Script 1.0 - (do not remove this comment)
(script# 700)
(include game.sh)
(use Main)
(use Game)
(use User)
(use System)

(public
	Room700 0
)

(instance Room700 of Room
	
	(method (init)
		(curRoom drawPic: 700 IRISIN)
		(self setScript: wait)
		(User canInput: FALSE)
		(= userFont smallFont)
		(super init:)
	)
	
	(method (dispose)
		(User canInput: TRUE)
		(super dispose:)
	)
)

(instance wait of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Display 700 0
					p_at 70 170
					p_color vBLACK
					p_back vLGREY
				)
				(= seconds 3)
			)
			(1
				(curRoom newRoom: 699)
			)
		)
	)
)
