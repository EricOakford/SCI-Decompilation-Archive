;;; Sierra Script 1.0 - (do not remove this comment)
(script# 26)
(include game.sh)
(use Main)
(use Procs)
(use KQRoom)
(use debugHandler)
(use Print)
(use System)

(public
	rm26 0
)

(instance rm26 of KQRoom
	
	(method (init)
		(super init:)
		(theGame handsOn:)
		(self setScript: showOpening)
	)
)

(instance showOpening of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(if (= demoScripts TRUE)
					(theGame setCursor: normalCursor TRUE 150 100)
					(if
						(Print
							font: smallFont
							addText: {Include scrolling?}
							addButton: 1 {<Yes>} 0 10
							addButton: 0 {<No>} 0 24
							init:
						)
						(= scrollingIsOn TRUE)
						(Bset 21)
						(SetCursor 0)
						(curRoom newRoom: 1400)
					else
						(Bset 21)
						(if
							(Print
								font: smallFont
								addText: {Include DAC Music?}
								addButton: 1 {<Yes>} 0 10
								addButton: 0 {<No>} 0 24
								init:
							)
							(Bset 241)
						)
						(SetCursor 0)
						(= scrollingIsOn FALSE)
						(curRoom newRoom: 1250)
					)
				else
					(ego x: 400)
					(WhereTo)
				)
			)
		)
	)
)
