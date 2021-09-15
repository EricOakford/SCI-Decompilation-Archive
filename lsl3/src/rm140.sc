;;; Sierra Script 1.0 - (do not remove this comment)
(script# 140)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm140 0
)

(local
	local0
	theY
	filthNum
	local3
	local4
	printRet
	local6
	textRes
	local8
	[local9 99]
	[buffer 300]
)
(procedure (DoDisplay param1 theColor &tmp [str 200])
	(= theY (+ 42 (* param1 30)))
	(Format @buffer 140 8 (+ param1 96))
	(Display @buffer
		p_at 150 theY
		p_color theColor
		p_font 4
		p_width 10
	)
	(GetFarText textRes (+ (* local4 5) param1) @str)
	(Display @str
		p_at 165 theY
		p_color theColor
		p_font 4
		p_width 135
	)
)

(procedure (ButtonPressed event t l b r &tmp x y)
	(= x (event x?))
	(= y (event y?))
	(return
		(if
			(and
				(> x t)
				(> y l)
				(< x b)
				(< y r)
			)
			1
		else
			0
		)
	)
)

(procedure (localproc_07ff param1)
	(= [local9 (/ param1 16)]
		(| [local9 (/ param1 16)] (>> $8000 (mod param1 16)))
	)
)

(procedure (localproc_0819 param1)
	(return
		(if (& [local9 (/ param1 16)] (>> $8000 (mod param1 16)))
			1
		else
			0
		)
	)
)

(procedure (localproc_0835 &tmp temp0 temp1 [fileBuf 30])
	(if (!= (= temp0 (FOpen {LARRY3.DRV} 1)) -1)
		(= [local9 0] (ReadNumber (FGets @fileBuf 8 temp0)))
		(= [local9 1] (ReadNumber (FGets @fileBuf 8 temp0)))
		(= [local9 2] (ReadNumber (FGets @fileBuf 8 temp0)))
	)
	(FClose temp0)
)

(procedure (localproc_0895 &tmp [fileBuf 40] temp40)
	(if (!= (= temp40 (FOpen {LARRY3.DRV} 2)) -1)
		(Format
			@fileBuf
			140
			13
			[local9 0]
			[local9 1]
			[local9 2]
			140
			14
		)
		(FPuts temp40 @fileBuf)
	)
	(FClose temp40)
)

(procedure (UpdateRect)
	(Graph GFillRect 32 150 189 302 1 15)
	(Graph GShowBits 32 150 189 302 1)
)

(instance rm140 of Room
	(properties
		picture 99
	)
	
	(method (init)
		(Load PICTURE curRoomNum)
		(Load SOUND 140)
		(Load SOUND 141)
		(Load FONT 4)
		(localproc_0835)
		(while
			(and
				(< (++ local0) 1000)
				(localproc_0819 (- (= textRes (Random 141 161)) 141))
			)
		)
		(if (>= local0 1000)
			(= [local9 0] 0)
			(= [local9 1] 0)
			(= [local9 2] 0)
			(= textRes (Random 141 161))
		)
		(localproc_07ff (- textRes 141))
		(localproc_0895)
		(Load TEXT textRes)
		(super init:)
		(= programControl FALSE)
		(HandsOn)
		(User canInput: FALSE)
		(ego init: hide:)
		(self setScript: RoomScript)
	)
)

(instance RoomScript of Script
	(method (changeState newState &tmp temp0 temp1 temp2 [temp3 200])
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_041d
			pushi    9
			pushi    140
			pushi    1
			pushi    80
			lofsa    {Hello!___My name is Larry; Larry Laffer!}
			push    
			pushi    67
			pushi    10
			pushi    65535
			pushi    70
			pushi    290
			calle    Print,  18
			pushi    11
			pushi    140
			pushi    2
			pushi    100
			pushi    99
			pushi    166
			pushi    102
			pushi    4
			pushi    106
			pushi    130
			pushi    105
			pushi    4
			callk    Display,  22
			pushi    0
			pushi    10
			pushi    140
			pushi    3
			pushi    80
			lofsa    {Blush!}
			push    
			pushi    81
			lofsa    {Bail\nOut}
			push    
			pushi    0
			pushi    81
			lofsa    {Oh, Go Ahead!\nTry to Offend Me!}
			push    
			pushi    1
			calle    Print,  20
			eq?     
			bnt      code_0356
			ldi      1
			sag      quit
			ret     
code_0356:
			pushi    16
			pushi    140
			pushi    4
			pushi    80
			lofsa    {Reality Check}
			push    
			pushi    81
			lofsa    {Under 12}
			push    
			pushi    65535
			pushi    81
			lofsa    {13 to 17}
			push    
			pushi    0
			pushi    81
			lofsa    {18 to 25}
			push    
			pushi    18
			pushi    81
			lofsa    {over 25}
			push    
			pushi    25
			calle    Print,  32
			sat      temp0
			pushi    #fade
			pushi    0
			lag      music
			send     4
			pushi    #drawPic
			pushi    2
			pushi    99
			pushi    7
			lag      curRoom
			send     8
			lst      temp0
			dup     
			ldi      65535
			eq?     
			bnt      code_03b6
			pushi    2
			pushi    140
			pushi    5
			calle    Print,  4
			ldi      1
			sag      quit
			jmp      code_0419
code_03b6:
			dup     
			ldi      0
			eq?     
			bnt      code_03d1
			pushi    2
			pushi    140
			pushi    6
			calle    Print,  4
			pushi    #changeState
			pushi    1
			pushi    5
			self     6
			jmp      code_0419
code_03d1:
			pushi    #drawPic
			pushi    2
			lsg      curRoomNum
			pushi    7
			lag      curRoom
			send     8
			pushi    #init
			pushi    0
			lofsa    aSuit
			send     4
			pushi    #add
			pushi    1
			lofsa    atpFace
			push    
			pushi    60
			pushi    0
			lag      addToPics
			send     10
			pushi    4
			lea      @buffer
			push    
			pushi    140
			pushi    7
			lst      temp0
			callk    Format,  8
			pushi    4
			lea      @buffer
			push    
			pushi    67
			pushi    65535
			pushi    144
			calle    Print,  8
			pushi    #cue
			pushi    0
			self     4
code_0419:
			toss    
			jmp      code_06a0
code_041d:
			dup     
			ldi      1
			eq?     
			bnt      code_0514
			ldi      42
			sal      theY
			pushi    3
			lsl      textRes
			lsl      local4
			ldi      5
			mul     
			push    
			lea      @buffer
			push    
			callk    GetFarText,  6
			pushi    2
			lea      @buffer
			push    
			pushi    0
			callk    StrAt,  4
			push    
			ldi      48
			sub     
			sal      local6
			ldi      0
			sat      temp1
code_044c:
			lst      temp1
			pushi    1
			lea      @buffer
			push    
			callk    StrLen,  2
			le?     
			bnt      code_0479
			pushi    3
			lea      @temp3
			push    
			lst      temp1
			pushi    2
			lea      @buffer
			push    
			lst      temp1
			ldi      1
			add     
			push    
			callk    StrAt,  4
			push    
			callk    StrAt,  6
			+at      temp1
			jmp      code_044c
code_0479:
			pushi    10
			lea      @temp3
			push    
			pushi    100
			pushi    150
			lsl      theY
			pushi    102
			pushi    1
			pushi    105
			pushi    4
			pushi    106
			pushi    150
			callk    Display,  20
			ldi      72
			sal      theY
			ldi      1
			sat      temp1
code_049d:
			lst      temp1
			ldi      5
			lt?     
			bnt      code_06a0
			pushi    4
			lea      @buffer
			push    
			pushi    140
			pushi    8
			lst      temp1
			ldi      96
			add     
			push    
			callk    Format,  8
			pushi    10
			lea      @buffer
			push    
			pushi    100
			pushi    150
			lsl      theY
			pushi    102
			pushi    1
			pushi    105
			pushi    4
			pushi    106
			pushi    10
			callk    Display,  20
			pushi    3
			lsl      textRes
			lsl      local4
			ldi      5
			mul     
			push    
			lat      temp1
			add     
			push    
			lea      @temp3
			push    
			callk    GetFarText,  6
			pushi    10
			lea      @temp3
			push    
			pushi    100
			pushi    165
			lsl      theY
			pushi    102
			pushi    1
			pushi    105
			pushi    4
			pushi    106
			pushi    135
			callk    Display,  20
			+at      temp1
			lsl      theY
			ldi      30
			add     
			sal      theY
			jmp      code_049d
			jmp      code_06a0
code_0514:
			dup     
			ldi      2
			eq?     
			bnt      code_05aa
			lsl      printRet
			lal      local6
			eq?     
			bnt      code_055b
			pushi    2
			lsl      printRet
			pushi    2
			call     DoDisplay,  4
			pushi    #number
			pushi    1
			pushi    140
			pushi    6
			pushi    1
			pushi    1
			pushi    42
			pushi    0
			lag      music
			send     16
			pushi    8
			pushi    140
			pushi    9
			pushi    67
			pushi    190
			pushi    8
			pushi    25
			pushi    3
			pushi    88
			calle    Print,  16
			+al      filthNum
			+al      local3
			jmp      code_0594
code_055b:
			pushi    2
			lsl      printRet
			pushi    4
			call     DoDisplay,  4
			pushi    #number
			pushi    1
			pushi    141
			pushi    6
			pushi    1
			pushi    1
			pushi    42
			pushi    0
			lag      music
			send     16
			pushi    8
			pushi    140
			pushi    10
			pushi    67
			pushi    190
			pushi    8
			pushi    25
			pushi    3
			pushi    88
			calle    Print,  16
			lal      local3
			bnt      code_0594
			-al      local3
code_0594:
			pushi    #setCel
			pushi    1
			lsl      local3
			pushi    198
			pushi    0
			lofsa    aSuit
			send     10
			ldi      3
			aTop     seconds
			jmp      code_06a0
code_05aa:
			dup     
			ldi      3
			eq?     
			bnt      code_05d2
			pushi    0
			callb    cls,  0
			pushi    0
			call     UpdateRect,  0
			ldi      0
			sal      printRet
			+al      local4
			push    
			ldi      5
			lt?     
			bnt      code_05cb
			ldi      0
			aTop     state
code_05cb:
			ldi      11
			aTop     cycles
			jmp      code_06a0
code_05d2:
			dup     
			ldi      4
			eq?     
			bnt      code_064b
			pushi    5
			lea      @buffer
			push    
			pushi    140
			pushi    11
			lsl      filthNum
			dup     
			dup     
			ldi      5
			eq?     
			bnt      code_05f4
			lofsa    {Totally Raunchiest}
			jmp      code_061e
code_05f4:
			dup     
			ldi      4
			eq?     
			bnt      code_0601
			lofsa    {Really Filthy}
			jmp      code_061e
code_0601:
			dup     
			ldi      3
			eq?     
			bnt      code_060e
			lofsa    {Pretty Dirty}
			jmp      code_061e
code_060e:
			dup     
			ldi      2
			eq?     
			bnt      code_061b
			lofsa    {Rather Risque}
			jmp      code_061e
code_061b:
			lofsa    {Mother Goose}
code_061e:
			toss    
			push    
			callk    Format,  10
			pushi    0
			call     UpdateRect,  0
			pushi    10
			lea      @buffer
			push    
			pushi    100
			pushi    160
			pushi    60
			pushi    102
			pushi    1
			pushi    105
			lsg      userFont
			pushi    106
			pushi    130
			callk    Display,  20
			ldi      9
			aTop     seconds
			jmp      code_06a0
code_064b:
			dup     
			ldi      5
			eq?     
			bnt      code_06a0
			lsl      filthNum
			ldi      0
			eq?     
			bnt      code_065c
			+al      filthNum
code_065c:
			lsl      filthNum
			ldi      1
			sub     
			sag      filthLevel
			pushi    2
			lofsa    {RESOURCE.LL3}
			push    
			pushi    2
			callk    FOpen,  4
			sat      temp2
			push    
			ldi      65535
			ne?     
			bnt      code_068f
			pushi    4
			lea      @temp3
			push    
			pushi    140
			pushi    12
			lsg      filthLevel
			callk    Format,  8
			pushi    2
			lst      temp2
			lea      @temp3
			push    
			callk    FPuts,  4
code_068f:
			pushi    1
			lst      temp2
			callk    FClose,  2
			pushi    #newRoom
			pushi    1
			pushi    290
			lag      curRoom
			send     6
code_06a0:
			toss    
			ret     
		)
	)
	
	(method (handleEvent event &tmp temp0 [temp1 33])
		(if (and (not (event claimed?)) (== state 4))
			(self cue:)
		)
		(if
			(or
				(event claimed?)
				(!= state 1)
				(super handleEvent: event)
			)
			(return)
		)
		(switch (event type?)
			(mouseDown
				(cond 
					((ButtonPressed event 141 71 300 91)
						(= printRet 1)
						(self cue:)
					)
					((ButtonPressed event 141 101 300 121)
						(= printRet 2)
						(self cue:)
					)
					((ButtonPressed event 141 132 300 152)
						(= printRet 3)
						(self cue:)
					)
					((ButtonPressed event 141 161 300 186)
						(= printRet 4)
						(self cue:)
					)
				)
			)
			(keyDown
				(= temp0 (event modifiers?))
				(switch (event message?)
					(`#2
						(event claimed?)
						(ToggleSound)
					)
					(`a
						(= printRet 1)
						(self cue:)
					)
					(`b
						(= printRet 2)
						(self cue:)
					)
					(`c
						(= printRet 3)
						(self cue:)
					)
					(`d
						(= printRet 4)
						(self cue:)
					)
					(`A
						(= printRet 1)
						(self cue:)
					)
					(`B
						(= printRet 2)
						(self cue:)
					)
					(`C
						(= printRet 3)
						(self cue:)
					)
					(`D
						(= printRet 4)
						(self cue:)
					)
					(`@x
						(if (& temp0 $0004)
							(event claimed: TRUE)
							(Print 140 0)
							(= filthNum 6)
							(while (u> filthNum 5)
								(= filthNum (GetNumber {from 1-5 ONLY!}))
							)
							(self changeState: 4)
						)
					)
				)
			)
		)
	)
)

(instance atpFace of PicView
	(properties
		y 52
		x 50
		view 140
		priority 15
	)
)

(instance aSuit of Prop
	(properties
		y 77
		x 83
		view 140
		loop 1
	)
)
