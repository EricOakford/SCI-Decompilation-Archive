;;; Sierra Script 1.0 - (do not remove this comment)
(script# TITLE)
(include game.sh)
(use Main)
(use LoadMany)
(use Game)
(use System)

(public
	titleScreen 0
)

(instance titleScreen of Room
	(properties
		picture 4
		style FADEOUT
	)
	
	(method (init)
		(LoadMany VIEW vBagPipe vDirector)
		(LoadMany PICTURE 3 1 5)
		(Load SOUND 6)
		(super init:)
		(self setScript: displayTitle)
	)
)

(instance displayTitle of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (< state 2)
			(Palette PALCycle 1 9 -1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				((ScriptID 0 5) number: 7 loop: -1 playBed:)
				(= seconds 5)
			)
			(2
				(curRoom drawPic: 3 FADEOUT)
				(Palette PALLoad 999 2)
				(= cycles 4)
			)
			(3
				(curRoom newRoom: STAGE)
				(self dispose:)
			)
		)
	)
)
