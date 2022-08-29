;;; Sierra Script 1.0 - (do not remove this comment)
(script# GKABOUT)
(include game.sh) (include "0.shm")
(use Main)
(use Print)
(use System)

(public
	xaboutStuff 2
)

(local
	printRet
	oldCur
)
(instance xaboutStuff of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= oldCur (theGame setCursor:))
				(theGame setCursor: ARROW_CURSOR TRUE)
				(switch
					(= printRet
						(Print
							addText: {About:} 60 0
							addButton: 1 {__Demo Info__} 0 20
							addButton: 2 { How To Play_} 100 20
							addButton: 3 {__Days 2-10__} 0 40
							addButton: 4 {__GK Team__} 100 40
							init:
						)
					)
					(1 (self setScript: demoInfo))
					(2 (self setScript: howToPlay))
					(3 (self setScript: otherDays))
					(4 (self setScript: credits))
				)
			)
		)
	)
)

(instance demoInfo of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print addText: N_SHOWABOUT NULL C_ABOUT_DEMO 1 0 0 0 init:)
				(self cue:)
			)
			(1
				(Print addText: N_SHOWABOUT NULL C_ABOUT_DEMO 2 0 0 0 init:)
				(self cue:)
			)
			(2
				(Print addText: N_SHOWABOUT NULL C_ABOUT_DEMO 3 0 0 0 init:)
				(self cue:)
			)
			(3
				(Print addText: N_SHOWABOUT NULL C_ABOUT_DEMO 4 0 0 0 init:)
				(self cue:)
			)
			(4
				(theGame setCursor: oldCur TRUE)
				(self dispose:)
			)
		)
	)
)

(instance howToPlay of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print addText: N_SHOWABOUT NULL C_HOW_TO_PLAY 1 0 0 0 init:)
				(self cue:)
			)
			(1
				(Print addText: N_SHOWABOUT NULL C_HOW_TO_PLAY 2 0 0 0 init:)
				(self cue:)
			)
			(2
				(Print addText: N_SHOWABOUT NULL C_HOW_TO_PLAY 3 0 0 0 init:)
				(self cue:)
			)
			(3
				(Print addText: N_SHOWABOUT NULL C_HOW_TO_PLAY 4 0 0 0 init:)
				(self cue:)
			)
			(4
				(theGame setCursor: oldCur 1)
				(self dispose:)
			)
		)
	)
)

(instance otherDays of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print addText: N_SHOWABOUT NULL C_OTHER_DAYS 1 0 0 0 init:)
				(self cue:)
			)
			(1
				(Print addText: N_SHOWABOUT NULL C_OTHER_DAYS 2 0 0 0 init:)
				(self cue:)
			)
			(2
				(Print addText: N_SHOWABOUT NULL C_OTHER_DAYS 3 0 0 0 init:)
				(self cue:)
			)
			(3
				(Print addText: N_SHOWABOUT NULL C_OTHER_DAYS 4 0 0 0 init:)
				(self cue:)
			)
			(4
				(Print addText: N_SHOWABOUT NULL C_OTHER_DAYS 5 0 0 0 init:)
				(self cue:)
			)
			(5
				(theGame setCursor: oldCur 1)
				(self dispose:)
			)
		)
	)
)

(instance credits of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print addText: N_SHOWABOUT NULL C_CREDITS 1 0 0 0 init:)
				(self cue:)
			)
			(1
				(theGame setCursor: oldCur TRUE)
				(self dispose:)
			)
		)
	)
)
