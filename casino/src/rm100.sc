;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include game.sh)
(use Main)
(use Intrface)
(use Button)
(use PrintD)
(use LoadMany)
(use Window)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm100 0
)

(instance rm100 of Room
	(properties
		picture 900
		style FADEOUT
	)
	
	(method (init)
		(super init: &rest)
		(theGame masterVolume: 15)
		(LoadMany VIEW 417)
		(if (or prevRoomNum (GameIsRestarting))
			(= picture 417)
		)
		(HandsOff)
		(theIconBar
			enable:
			disable: ICON_LOOK ICON_DO
			height: -100
			activateHeight: -100
		)
		(= useSortedFeatures FALSE)
		(self setScript: startScript)
	)
	
	(method (doit)
		(super doit:)
		(if (== (curRoom curPic?) 900)
			(Palette PALCycle 95 218 -1)
		)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 417)
	)
	
	(method (doVerb)
		(switch (curRoom script?)
			(startScript
				(if (< (startScript state?) 1)
					(startScript changeState: 1)
				)
			)
			(else  0)
		)
	)
	
	(method (newRoom)
		(= useSortedFeatures TRUE)
		(if (< numVoices 2)
			(theMusic stop:)
		else
			(theMusic fade:)
		)
		(HandsOff)
		(theGame setCursor: waitCursor)
		(super newRoom: &rest)
	)
)

(instance startScript of Script
	
	(method (init)
		(HandsOff)
		(DontMove)
		(if prevRoomNum
			(proc0_29)
			(= start TRUE)
		)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(theMusic setLoop: -1)
				(sounds eachElementDo: #stop)
				(curRoom drawPic: 417 FADEOUT)
				(= cycles 5)
			)
			(2
				(aboutGames init:)
				(quitGames init:)
				(cond 
					((< dollars 1) (loan init: posn: 76 109))
					((< dollars 10)
						(slotMachine init: posn: 77 103)
						(blackJack init: posn: 77 120)
						(poker init: posn: 77 137)
						(loan init: posn: 77 154)
					)
					(else
						(blackJack init: posn: 77 120)
						(poker init: posn: 77 137)
						(slotMachine init: posn: 77 103)
					)
				)
				(= cycles 20)
			)
			(3
				(theMusic number: 1 loop: -1 play: 40)
				(DontMove 721)
				(slotMachine stopUpd:)
				(blackJack stopUpd:)
				(poker stopUpd:)
				(aboutGames stopUpd:)
				(quitGames stopUpd:)
				(if (loan isNotHidden:) (loan stopUpd:))
				(= cycles 1)
			)
			(4 (self dispose:))
		)
	)
)

(instance aboutGames of Button
	(properties
		x 197
		y 170
		view 417
	)
	
	(method (doVerb)
		(requestHelp init: &rest)
	)
)

(instance quitGames of Button
	(properties
		x 274
		y 170
		view 417
		loop 1
	)
	
	(method (doVerb)
		(= quit TRUE)
	)
)

(instance slotMachine of Button
	(properties
		x 77
		y 103
		view 417
		loop 2
	)
	
	(method (doVerb)
		(curRoom newRoom: 260)
	)
)

(instance blackJack of Button
	(properties
		x 77
		y 120
		view 417
		loop 3
	)
	
	(method (doVerb)
		(curRoom newRoom: 250)
	)
)

(instance poker of Button
	(properties
		x 77
		y 137
		view 417
		loop 4
	)
	
	(method (doVerb)
		(curRoom newRoom: 720)
	)
)

(instance loan of Button
	(properties
		x 77
		y 154
		view 417
		loop 5
	)
	
	(method (doVerb)
		(loanOffice doit:)
	)
)

(instance loanOffice of Code
	
	(method (doit &tmp temp0 [temp1 200])
		(asm
			pushi    #doit
			pushi    0
			super    Code,  4
			lsg      dollars
			ldi      1
			lt?     
			bnt      code_0335
			ldi      1212
			sat      temp0
			jmp      code_0341
code_0335:
			lsg      dollars
			ldi      10
			lt?     
			bnt      code_0341
			ldi      1211
			sat      temp0
code_0341:
			lst      temp0
			dup     
			ldi      1211
			eq?     
			bnt      code_0433
code_034b:
			pushi    14
			pushi    100
			pushi    0
			pushi    27
			pushi    1
			pushi    32
			class    SysWindow
			push    
			pushi    77
			lofsa    {Loan}
			push    
			pushi    78
			lofsa    {Sure!}
			push    
			pushi    1
			pushi    78
			lofsa    {Naw}
			push    
			pushi    2
			calle    Print,  28
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_041a
			lsg      outstandingLoans
			ldi      10
			lt?     
			bnt      code_03db
			+ag      outstandingLoans
			pushi    6
			lea      @temp1
			push    
			pushi    100
			pushi    1
			lsg      outstandingLoans
			dup     
			ldi      100
			mul     
			push    
			pushi    10
			lag      outstandingLoans
			sub     
			push    
			callk    Format,  12
			pushi    9
			lea      @temp1
			push    
			pushi    27
			pushi    1
			pushi    77
			lofsa    {Loan Shark's Office}
			push    
			pushi    32
			class    SysWindow
			push    
			pushi    78
			lofsa    { Done_}
			push    
			calle    Print,  18
			pushi    3
			pushi    100
			pushi    2
			lsg      dollars
			ldi      100
			add     
			sag      dollars
			push    
			calle    Printf,  6
			pushi    #play
			pushi    0
			lofsa    winSound
			send     4
			pushi    #dispose
			pushi    0
			lofsa    loan
			send     4
			jmp      code_040d
code_03db:
			pushi    6
			lea      @temp1
			push    
			pushi    100
			pushi    1
			lsg      outstandingLoans
			dup     
			ldi      100
			mul     
			push    
			pushi    0
			callk    Format,  12
			pushi    9
			lea      @temp1
			push    
			pushi    27
			pushi    1
			pushi    77
			lofsa    {Loan Shark's Office}
			push    
			pushi    32
			class    SysWindow
			push    
			pushi    78
			lofsa    { Done_}
			push    
			calle    Print,  18
code_040d:
			pushi    #play
			pushi    0
			lofsa    winSound
			send     4
			jmp      code_0518
			jmp      code_042c
code_041a:
			dup     
			ldi      2
			eq?     
			bnt      code_042c
			pushi    2
			pushi    100
			pushi    3
			calle    Print,  4
			jmp      code_0518
code_042c:
			toss    
			jmp      code_034b
			jmp      code_0518
code_0433:
			dup     
			ldi      1212
			eq?     
			bnt      code_0518
			lsg      outstandingLoans
			ldi      10
			lt?     
			bnt      code_049e
			+ag      outstandingLoans
			pushi    #play
			pushi    0
			lofsa    winSound
			send     4
			pushi    6
			lea      @temp1
			push    
			pushi    100
			pushi    1
			lsg      outstandingLoans
			dup     
			ldi      100
			mul     
			push    
			pushi    10
			lag      outstandingLoans
			sub     
			push    
			callk    Format,  12
			pushi    #play
			pushi    0
			lofsa    winSound
			send     4
			pushi    9
			lea      @temp1
			push    
			pushi    27
			pushi    1
			pushi    77
			lofsa    {Loan Shark's Office}
			push    
			pushi    32
			class    SysWindow
			push    
			pushi    78
			lofsa    { Done_}
			push    
			calle    Print,  18
			pushi    3
			pushi    100
			pushi    2
			lsg      dollars
			ldi      100
			add     
			sag      dollars
			push    
			calle    Printf,  6
			jmp      code_04e4
code_049e:
			pushi    6
			lea      @temp1
			push    
			pushi    100
			pushi    1
			lsg      outstandingLoans
			dup     
			ldi      100
			mul     
			push    
			pushi    0
			callk    Format,  12
			pushi    9
			lea      @temp1
			push    
			pushi    27
			pushi    1
			pushi    77
			lofsa    {Loan Shark's Office}
			push    
			pushi    32
			class    SysWindow
			push    
			pushi    78
			lofsa    {Done}
			push    
			calle    Print,  18
			pushi    4
			pushi    100
			pushi    4
			pushi    27
			pushi    1
			calle    Print,  8
			pushi    #restart
			pushi    0
			lag      theGame
			send     4
code_04e4:
			pushi    #play
			pushi    0
			lofsa    winSound
			send     4
			pushi    #init
			pushi    0
			pushi    304
			pushi    0
			lofsa    slotMachine
			send     8
			pushi    #init
			pushi    0
			pushi    304
			pushi    0
			lofsa    blackJack
			send     8
			pushi    #init
			pushi    0
			pushi    304
			pushi    0
			lofsa    poker
			send     8
			pushi    #dispose
			pushi    0
			lofsa    loan
			send     4
code_0518:
			toss    
			ret     
		)
	)
)

(instance displayCredits of Code

	(method (doit)
		(theGame masterVolume: 0)
		(systemWindow color: 50 back: 42)
		(Print 100 5
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 100 6
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 100 7
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 100 8
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 100 9
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 100 10
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 100 11
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 100 12
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 100 13
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 100 14
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 100 15
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 100 16
			#title {Credits}
			#mode teJustCenter
			#button { Done_}
			#window cluckWindow
		)
		(theGame masterVolume: 12)
	)
)

(instance winSound of Sound
	(properties
		number 724
	)
)

(instance sparkle of Prop
	(properties
		view 950
	)
	
	(method (init)
		(self
			setLoop: 2
			cel: 0
			ignoreActors:
			setPri: 14
			setCycle: EndLoop
		)
		(super init:)
	)
)

(instance cluckWindow of SysWindow
	(properties
		back 42
	)
)

(instance requestHelp of Object

	(method (init param1 param2 saveColor saveBack)
		(= saveColor (systemWindow color?))
		(= saveBack (systemWindow back?))
		(theGame masterVolume: 0)
		(super init:)
		(self
			doit: param1 param2 saveColor saveBack
		)
	)
	
	(method (doit param1 param2)
		(asm
code_0702:
			pushi    #color
			pushi    1
			pushi    0
			pushi    26
			pushi    1
			pushi    42
			lag      systemWindow
			send     12
			pushi    17
			lofsa    {Select...}
			push    
			pushi    109
			pushi    78
			lofsa    {Game Help}
			push    
			pushi    1
			pushi    109
			pushi    78
			lofsa    {Credits}
			push    
			pushi    2
			pushi    109
			pushi    78
			lofsa    {Sierra}
			push    
			pushi    4
			pushi    109
			pushi    78
			lofsa    {Quit About}
			push    
			pushi    3
			calle    PrintD,  34
			sap      param2
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_07b7
			pushi    #color
			pushi    1
			pushi    0
			pushi    26
			pushi    1
			pushi    42
			lag      systemWindow
			send     12
			pushi    10
			pushi    100
			pushi    17
			pushi    32
			lofsa    cluckWindow
			push    
			pushi    27
			pushi    1
			pushi    77
			lofsa    {Game Help}
			push    
			pushi    78
			lofsa    { More_}
			push    
			calle    Print,  20
			pushi    10
			pushi    100
			pushi    18
			pushi    32
			lofsa    cluckWindow
			push    
			pushi    27
			pushi    1
			pushi    77
			lofsa    {Game Help (Con't)}
			push    
			pushi    78
			lofsa    { More_}
			push    
			calle    Print,  20
			pushi    10
			pushi    100
			pushi    19
			pushi    32
			lofsa    cluckWindow
			push    
			pushi    27
			pushi    1
			pushi    77
			lofsa    {Game Help (Con't)}
			push    
			pushi    78
			lofsa    { More_}
			push    
			calle    Print,  20
			jmp      code_0817
code_07b7:
			dup     
			ldi      2
			eq?     
			bnt      code_07c9
			pushi    #doit
			pushi    0
			lofsa    displayCredits
			send     4
			jmp      code_081b
			jmp      code_0817
code_07c9:
			dup     
			ldi      3
			eq?     
			bnt      code_07d3
			jmp      code_081b
			jmp      code_0817
code_07d3:
			dup     
			ldi      4
			eq?     
			bnt      code_0817
			pushi    10
			pushi    100
			pushi    20
			pushi    32
			lofsa    cluckWindow
			push    
			pushi    27
			pushi    1
			pushi    77
			lofsa    {Other great products...}
			push    
			pushi    78
			lofsa    { More_}
			push    
			calle    Print,  20
			pushi    10
			pushi    100
			pushi    21
			pushi    32
			lofsa    cluckWindow
			push    
			pushi    27
			pushi    1
			pushi    77
			lofsa    {Other great products...}
			push    
			pushi    78
			lofsa    { Done_}
			push    
			calle    Print,  20
code_0817:
			toss    
			jmp      code_0702
code_081b:
			pushi    #color
			pushi    1
			pushi    0
			pushi    26
			pushi    1
			pushi    42
			lag      systemWindow
			send     12
			pushi    #masterVolume
			pushi    1
			pushi    13
			lag      theGame
			send     6
			ret     
		)
	)
)
