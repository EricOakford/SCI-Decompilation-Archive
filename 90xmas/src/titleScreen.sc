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
		picture pTitle
		style FADEOUT
	)
	
	(method (init)
		(LoadMany VIEW vBagPipe vDirector)
		(LoadMany PICTURE pBlack pStage pCredits1)
		(Load SOUND sBagPipes)
		(super init:)
		(self setScript: displayTitle)
	)
)

(instance displayTitle of Script	
	(method (doit)
		(super doit:)
		(if (< state 2)
			(Palette PALCycle 1 9 -1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 1)
			)
			(1
				((ScriptID MAIN 5)
					number: sJingleBells
					loop: -1
					playBed:
				)
				(= seconds 5)
			)
			(2
				(curRoom drawPic: pBlack FADEOUT)
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
