;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2)
(include game.sh)
(use Main)
(use Intrface)
(use DScript)
(use RandCyc)
(use DCIcon)
(use Sound)
(use Game)

(public
	rm2 0
)

(local
	[local0 3]
)
(procedure (EndPrint view loop cel &tmp [str 303])
	(theGame setCursor: normalCursor TRUE)
	(switch
		(Print
			{Thank you for playing Space Quest I. As usual, you've been most entertaining.}
			#at 100 60
			#new
			#button {Restore} 1
			#new
			#button {Restart} 2
			#new
			#button {Quit} 3
			#icon view loop cel
		)
		(1
			(theGame restore:)
			(Sound pause: FALSE)
		)
		(2
			(theGame restart:)
			(Sound pause: FALSE)
		)
		(3
			(= quit TRUE)
		)
		(else
			(Sound pause: FALSE)
		)
	)
	(theIconBar disable: hide:)
	(theGame setCursor: waitCursor FALSE)
)

(instance babbleIcon of DCIcon
	(properties)
	
	(method (init)
		((= cycler (RandCycle new:)) init: self 20)
	)
)

(instance rm2 of Room
	(properties
		picture 2
		style (| BLACKOUT PIXELDISSOLVE)
	)
	
	(method (init)
		(babbleIcon view: 946 cycleSpeed: (* (+ howFast 1) 4))
		(Palette PALLoad 999)
		(theIconBar disable:)
		(theMusic flags: 0)
		(super init:)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(theGame setCursor: waitCursor FALSE)
		(self setScript: finalCredits)
	)
	
	(method (handleEvent event)
		(if (and (event type?) (== curRoomNum newRoomNum))
			(event claimed: TRUE)
			(Sound pause: TRUE)
			(theIconBar disable:)
			(theGame setCursor: normalCursor TRUE 160 100)
			(EndPrint 0 2 0)
		)
	)
	
	(method (newRoom n)
		(theMusic flags: mNOPAUSE fade:)
		(super newRoom: n)
	)
)

(instance finalCredits of DScript
	(properties)
	
	(method (init)
		(super init: &rest)
		(= clrByKey 0)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self
					save1:
						(Display
							{Animators:\n\nDouglas Herring\nVasken Nokhoudian\nNathan Larsen\nArturo Sinclair\nDeena Krutak\nDesie Hartman\nJerry Jessurun\nRussell Truelove\nDiana R. Wilson\n}
							p_mode teJustCenter
							p_font 4
							p_width 200
							p_color colLED
							p_at 60 20
							p_save
						)
				)
				(= seconds 15)
			)
			(1
				(self restore:)
				(= cycles 3)
			)
			(2
				(self
					save1:
						(Display
							{Background Artists:\n\nDouglas Herring\nNathan Larsen\nArturo Sinclair\nEric Kasner\nWillis Wong\nJay Allan Friedmann\nJennifer Shontz\nAndy Hoyos\nJane Cardinal\nMaurice F. Morgan\n}
							p_mode teJustCenter
							p_font 4
							p_width 200
							p_color colLED
							p_at 60 20
							p_save
						)
				)
				(= seconds 15)
			)
			(3
				(self restore:)
				(= cycles 3)
			)
			(4
				(self
					save1:
						(Display
							{Programmers:\n\nJerry Shaw\nRandy MacNeill\nDave Jamriska\nHugh Diedrichs\nChristopher Carr\n}
							p_mode teJustCenter
							p_font 4
							p_width 200
							p_color colLED
							p_at 60 20
							p_save
						)
				)
				(= seconds 12)
			)
			(5
				(self restore:)
				(= cycles 3)
			)
			(6
				(self
					save1:
						(Display
							{Music Director:\n\nMark Seibert\n}
							p_mode teJustCenter
							p_font 4
							p_width 200
							p_color colLED
							p_at 60 20
							p_save
						)
				)
				(= seconds 8)
			)
			(7
				(self restore:)
				(= cycles 3)
			)
			(8
				(self
					save1:
						(Display
							{System Development:\n\nChris Smith\nJeff Stephenson\nRobert E. Heitman\nPablo Ghenis\nDan Foy\nLarry Scott\nJ. Mark Hood\nMark Wilden\nEric Hart\nChad Bye\n}
							p_mode teJustCenter
							p_font 4
							p_width 200
							p_color colLED
							p_at 60 20
							p_save
						)
				)
				(= seconds 15)
			)
			(9
				(self restore:)
				(= cycles 3)
			)
			(10
				(self
					save1:
						(Display
							{Sound Effects:\n\nKen Allen\nMark Seibert\nOrpheus Hanley\n}
							p_mode teJustCenter
							p_font 4
							p_width 200
							p_color colLED
							p_at 60 20
							p_save
						)
				)
				(= seconds 10)
			)
			(11
				(self restore:)
				(= cycles 3)
			)
			(12
				(self
					save1:
						(Display
							{Additional Written Material:\n\nBridget McKenna\nGano Haine\n}
							p_mode teJustCenter
							p_font 4
							p_width 200
							p_color colLED
							p_at 60 20
							p_save
						)
				)
				(= seconds 10)
			)
			(13
				(self restore:)
				(= cycles 3)
			)
			(14
				(self
					save1:
						(Display
							{Space Quest 1 Theme Based on the\nOriginal Composition by Mark Crowe_}
							p_mode teJustCenter
							p_font 4
							p_width 200
							p_color colLED
							p_at 60 20
							p_save
						)
				)
				(= seconds 10)
			)
			(15
				(self restore:)
				(= cycles 3)
			)
			(16
				(self
					save1:
						(Display
							{Quality Assurance:\n\nSharon Simmons_}
							p_mode teJustCenter
							p_font 4
							p_width 200
							p_color colLED
							p_at 60 20
							p_save
						)
				)
				(= seconds 10)
			)
			(17
				(self restore:)
				(= cycles 3)
			)
			(18
				(theGame setCursor: theCursor (HaveMouse))
				(EndPrint 0 2 0)
			)
		)
	)
)
