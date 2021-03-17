;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include game.sh) (include "200.shm")
(use Main)
(use Array)
(use Print)
(use Game)
(use System)

(public
	rm200 0
)

(local
	printRet
)
(procedure (localproc_0143 &tmp temp0)
	(= temp0 (IntArray with: 0 0 0 0))
	(theGame setCursor: normalCursor TRUE)
	(TextSize (temp0 data?) {A} userFont 320)
	(= printRet
		(Print
			font: userFont
			addTitle: {Gabriel Knight II Demo}
			addText: {How do you want to see the Movie?}
			addButton: 0 {Windowed} 45 15
			addButton: 1 {Full Screen} 45 35
			addButton: 2 {Cancel} 45 55
			init:
		)
	)
	(temp0 dispose:)
)

(instance rm200 of Room
	
	(method (init)
		(super init: &rest)
		(theGame handsOn:)
		(self setScript: intro)
	)
)

(instance intro of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc0_5 0 3 self)
			)
			(1
				(if (== global122 0)
					(Bclr fSawIntro)
					(= printRet 0)
				else
					(localproc_0143)
				)
				(self cue:)
			)
			(2
				(= cycles 1)
			)
			(3
				(switch printRet
					(0
						(if isWindows
							(ShowMovie 1 0 {gk2a.avi})
							(ShowMovie 1 1 83 58)
							(Palette PalLoad 400 2)
							(ShowMovie 1 7)
							(ShowMovie 1 2)
							(ShowMovie 1 6)
						)
					)
					(1
						(if isWindows
							(ShowMovie 1 0 {gk2a.avi})
							(ShowMovie 1 1 0 12 319 158)
							(Palette PalLoad 400 2)
							(ShowMovie 1 7)
							(ShowMovie 1 2)
							(ShowMovie 1 6)
						)
					)
				)
				(= cycles 1)
			)
			(4
				(curRoom newRoom: 100)
				(self dispose:)
			)
		)
	)
)
