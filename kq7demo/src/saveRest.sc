;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include game.sh)
(use Main)
(use Procs)
(use n021)
(use Styler)
(use Array)
(use Print)
(use Game)
(use User)
(use System)

(public
	saveRest 0
)

(instance saveRest of Room
	
	(method (init)
		(thePlane setRect: 0 0 319 199)
		(User canInput: TRUE)
		(super init:)
		(self setScript: doDialog)
	)
)

(instance doDialog of Script
	
	(method (changeState newState &tmp str temp1 printRet)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(= str (IntArray with: 0 0 0 0))
				(theGame setCursor: normalCursor)
				(TextSize (str data?) {A} userFont 320)
				(= temp1 (+ (str at: 3) 6))
				(str dispose:)
				(= printRet 0)
				(switch
					(= printRet
						(Print
							font: userFont
							addText: {What?} 1 (* 0 temp1)
							addButton: 1 {New Game.} 1 (* 1 temp1)
							addButton: 2 {Old game.} 1 (* 2 temp1)
							addButton: 3 {Intro.} 1 1 (* 3 temp1)
							addButton: 4 {Quit} 1 (* 4 temp1)
							init:
						)
					)
					(1
						(if (== (proc21_0) 10)
							(Bset 3)
							(curRoom newRoom: 23)
						else
							(Bclr 3)
							(curRoom newRoom: 20)
						)
					)
					(2
						(if (proc21_0)
							(Bclr 3)
							(curRoom newRoom: 23)
						else
							(Prints {There are currently no saved games.})
							(self init:)
						)
					)
					(3
						(= cycles 1)
					)
					(4
						(if
							(not
								(= quit
									(Print
										posn: 71 82
										font: 0
										addText: {Are you sure you want to quit?} 0 0
										addButton: 1 {Yes} 127 15
										addButton: 0 {No} 168 15
										init:
									)
								)
							)
							(self init:)
						)
					)
				)
			)
			(2
				(Styler doit: (curRoom plane?) 13)
				(= seconds 5)
			)
			(3 (curRoom newRoom: 100))
		)
	)
)
