;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2)
(include sci.sh)
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
(procedure (localproc_005e param1 param2 param3 &tmp [temp0 303])
	(theGame setCursor: normalCursor 1)
	(switch
		(Print
			{Thank you for playing Space Quest I. As usual, you've been most entertaining.}
			#at
			26
			60
			106
			81
			{Restore}
			1
			106
			81
			{Restart}
			2
			106
			81
			{Quit}
			3
			82
			param1
			param2
			param3
		)
		(1
			(theGame restore:)
			(Sound pause: 0)
		)
		(2
			(theGame restart: 0)
			(Sound pause: 0)
		)
		(3 (= quit 1))
		(else  (Sound pause: 0))
	)
	(theIconBar disable: hide:)
	(theGame setCursor: waitCursor 0)
)

(instance babbleIcon of DCIcon
	(properties)
	
	(method (init)
		((= cycler (RandCycle new:)) init: self 20)
	)
)

(instance rm2 of Rm
	(properties
		picture 2
		style $8009
	)
	
	(method (init)
		(babbleIcon view: 946 cycleSpeed: (* (+ howFast 1) 4))
		(Palette 1 999)
		(theIconBar disable:)
		(theMusic flags: 0)
		(super init:)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(theGame setCursor: waitCursor 0)
		(self setScript: finalCredits)
	)
	
	(method (handleEvent event)
		(if (and (event type?) (== curRoomNum newRoomNum))
			(event claimed: 1)
			(Sound pause: 1)
			(theIconBar disable:)
			(theGame setCursor: normalCursor 1 160 100)
			(localproc_005e 0 2 0)
		)
	)
	
	(method (newRoom n)
		(theMusic flags: 1 fade:)
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
							dsALIGN
							1
							dsFONT
							4
							dsWIDTH
							200
							dsCOLOR
							colLED
							dsCOORD
							60
							20
							dsSAVEPIXELS
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
							dsALIGN
							1
							dsFONT
							4
							dsWIDTH
							200
							dsCOLOR
							colLED
							dsCOORD
							60
							20
							dsSAVEPIXELS
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
							{Programmers:\n\nJerry Shaw\nRandy MacNeill\nDave Jamriska\nHugh Diedrichs\n}
							dsALIGN
							1
							dsFONT
							4
							dsWIDTH
							200
							dsCOLOR
							colLED
							dsCOORD
							60
							20
							dsSAVEPIXELS
						)
				)
				(= seconds 15)
			)
			(5
				(self restore:)
				(= cycles 3)
			)
			(6
				(self
					save1:
						(Display
							{Amiga Implentation:\n\nSteve Coallier\nJerry Shaw\n}
							dsALIGN
							1
							dsFONT
							4
							dsWIDTH
							200
							dsCOLOR
							colLED
							dsCOORD
							60
							20
							dsSAVEPIXELS
						)
				)
				(= seconds 15)
			)
			(7
				(self restore:)
				(= cycles 3)
			)
			(8
				(self
					save1:
						(Display
							{Amiga Quality Assurance:\n\nSharon Simmons\nMax DearDorff\nJoe Perry\nRoger Clendenning\nBill Hilton\nMike Pickhinke\nMat Genesi\nBill Davis Jr.\n}
							dsALIGN
							1
							dsFONT
							4
							dsWIDTH
							200
							dsCOLOR
							colLED
							dsCOORD
							60
							20
							dsSAVEPIXELS
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
							{Music Director:\n\nMark Seibert\n}
							dsALIGN
							1
							dsFONT
							4
							dsWIDTH
							200
							dsCOLOR
							colLED
							dsCOORD
							60
							20
							dsSAVEPIXELS
						)
				)
				(= seconds 8)
			)
			(11
				(self restore:)
				(= cycles 3)
			)
			(12
				(self
					save1:
						(Display
							{System Development:\n\nChris Smith\nJeff Stephenson\nRobert E. Heitman\nPablo Ghenis\nDan Foy\nLarry Scott\nJ. Mark Hood\nMark Wilden\nEric Hart\nChad Bye\n}
							dsALIGN
							1
							dsFONT
							4
							dsWIDTH
							200
							dsCOLOR
							colLED
							dsCOORD
							60
							20
							dsSAVEPIXELS
						)
				)
				(= seconds 15)
			)
			(13
				(self restore:)
				(= cycles 3)
			)
			(14
				(self
					save1:
						(Display
							{Sound Effects:\n\nKen Allen\nMark Seibert\nOrpheus Hanley\n}
							dsALIGN
							1
							dsFONT
							4
							dsWIDTH
							200
							dsCOLOR
							colLED
							dsCOORD
							60
							20
							dsSAVEPIXELS
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
							{Additional Written Material:\n\nBridget McKenna\nGano Haine\nJerry Shaw\n}
							dsALIGN
							1
							dsFONT
							4
							dsWIDTH
							200
							dsCOLOR
							colLED
							dsCOORD
							60
							20
							dsSAVEPIXELS
						)
				)
				(= seconds 10)
			)
			(17
				(self restore:)
				(= cycles 3)
			)
			(18
				(self
					save1:
						(Display
							{Space Quest 1 Theme Based on the\nOriginal Composition by Mark Crowe_}
							dsALIGN
							1
							dsFONT
							4
							dsWIDTH
							200
							dsCOLOR
							colLED
							dsCOORD
							60
							20
							dsSAVEPIXELS
						)
				)
				(= seconds 10)
			)
			(19
				(self restore:)
				(= cycles 3)
			)
			(20
				(self
					save1:
						(Display
							{Quality Assurance:\n\nSharon Simmons_}
							dsALIGN
							1
							dsFONT
							4
							dsWIDTH
							200
							dsCOLOR
							colLED
							dsCOORD
							60
							20
							dsSAVEPIXELS
						)
				)
				(= seconds 10)
			)
			(21
				(self restore:)
				(= cycles 3)
			)
			(22
				(theGame setCursor: theCursor (HaveMouse))
				(localproc_005e 0 2 0)
			)
		)
	)
)
