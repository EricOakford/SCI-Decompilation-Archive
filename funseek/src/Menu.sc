;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENU)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)


(class TheMenuBar of MenuBar
	(properties
		name "MenuBar"
	)
	
	(method (init)
		(AddMenu { \01_}
			{About Fun Seekers Guide`^a :About Boosters`^b :VaporCalc`^c}
		)
		(AddMenu { Quit_}
			{Quit`^q}
		)
	)
	
	(method (handleEvent event &tmp i oldPause)
		(switch (super handleEvent: event)
			(aboutI
				(= oldPause (DoSound PauseSound TRUE))
				(Print MENU 0
					#icon 0 3 0
					#font 3
				)
				(DoSound PauseSound oldPause)
			)
			(boostersI
				(= oldPause (DoSound PauseSound TRUE))
				(Print MENU 1
					#font 3
				)
				(DoSound PauseSound oldPause)
			)
			(vaporCalcI
				(= oldPause (DoSound PauseSound TRUE))
				(= vaporCalcCued TRUE)
				(DoSound PauseSound oldPause)
			)
			(quitI
				(= quit TRUE)
			)
		)
	)
)
