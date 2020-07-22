;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG)
(include game.sh)
(use Main)
(use Procs)
(use HandsOffScript)
(use Intrface)
(use Feature)
(use Window)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	debugHandler 0
)

(local
	newDButton
)
(procedure (localproc_000e param1 param2 &tmp [str 40])
	(= str 0)
	(if (> argc 1)
		(Format @str 800 0 param2)
	)
	(return
		(if (GetInput @str 10 param1)
			(ReadNumber @str)
		else
			-1
		)
	)
)

(procedure (noScrolling)
	(if (OneOf (curRoom style?) SCROLLRIGHT SCROLLLEFT SCROLLUP SCROLLDOWN)
		(curRoom drawPic: (curRoom picture?) 100 style: PLAIN)
	)
)

(instance debugHandler of Feature
	(properties)
	
	(method (handleEvent event &tmp [str 75] temp75 temp76 temp77 temp78 temp79 temp80 temp81 temp82 temp83 temp84)
		(asm
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      4
			eq?     
			bnt      code_057a
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      8448
			eq?     
			bnt      code_00b2
			pushi    #showMem
			pushi    0
			lag      theGame
			send     4
			jmp      code_0576
code_00b2:
			dup     
			ldi      8704
			eq?     
			bnt      code_0103
			ldi      0
			sat      str
			pushi    3
			lea      @str
			push    
			pushi    4
			lofsa    {Variable No.}
			push    
			calle    GetInput,  6
			pushi    1
			lea      @str
			push    
			callk    ReadNumber,  2
			sat      temp79
			ldi      0
			sat      str
			pushi    3
			lea      @str
			push    
			pushi    4
			lofsa    {Value}
			push    
			calle    GetInput,  6
			pushi    1
			lea      @str
			push    
			callk    ReadNumber,  2
			push    
			lst      temp79
			ldi      100
			sub     
			sati     temp75
			ldi      0
			sat      str
			jmp      code_0576
code_0103:
			dup     
			ldi      12032
			eq?     
			bnt      code_0113
			pushi    1
			pushi    1
			callk    Show,  2
			jmp      code_0576
code_0113:
			dup     
			ldi      6144
			eq?     
			bnt      code_023a
			pushi    #new
			pushi    0
			class    List
			send     4
			sat      temp81
			pushi    1
			pushi    #elements
			pushi    0
			lag      cast
			send     4
			push    
			callk    FirstNode,  2
			sat      temp82
code_0132:
			lat      temp82
			bnt      code_020a
			pushi    1
			push    
			callk    NodeValue,  2
			sat      temp83
			pushi    1
			push    
			callk    IsObject,  2
			bnt      code_01ff
			pushi    #signal
			pushi    0
			lat      temp83
			send     4
			push    
			ldi      128
			and     
			not     
			bnt      code_01ff
			pushi    #add
			pushi    3
			pushi    #drawBox
			pushi    6
			pushi    #nsTop
			pushi    0
			lat      temp83
			send     4
			push    
			pushi    #nsLeft
			pushi    0
			lat      temp83
			send     4
			push    
			pushi    #nsBottom
			pushi    0
			lat      temp83
			send     4
			push    
			pushi    #nsRight
			pushi    0
			lat      temp83
			send     4
			push    
			pushi    0
			pushi    7
			pushi    114
			pushi    0
			pushi    #new
			pushi    0
			class    LineBox
			send     4
			send     20
			push    
			pushi    #drawBox
			pushi    6
			pushi    #brTop
			pushi    0
			lat      temp83
			send     4
			push    
			pushi    #brLeft
			pushi    0
			lat      temp83
			send     4
			push    
			pushi    #brBottom
			pushi    0
			lat      temp83
			send     4
			push    
			pushi    #brRight
			pushi    0
			lat      temp83
			send     4
			push    
			pushi    12
			pushi    31
			pushi    114
			pushi    0
			pushi    #new
			pushi    0
			class    LineBox
			send     4
			send     20
			push    
			pushi    #drawBox
			pushi    6
			pushi    #y
			pushi    0
			lat      temp83
			send     4
			push    
			pushi    #x
			pushi    0
			lat      temp83
			send     4
			push    
			pushi    #y
			pushi    0
			lat      temp83
			send     4
			push    
			pushi    #x
			pushi    0
			lat      temp83
			send     4
			push    
			pushi    32
			pushi    63
			pushi    114
			pushi    0
			pushi    #new
			pushi    0
			class    LineBox
			send     4
			send     20
			push    
			lat      temp81
			send     10
code_01ff:
			pushi    1
			lst      temp82
			callk    NextNode,  2
			sat      temp82
			jmp      code_0132
code_020a:
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp84
			send     4
			push    
			ldi      0
			eq?     
			bnt      code_0229
			pushi    #dispose
			pushi    0
			lat      temp84
			send     4
			jmp      code_020a
code_0229:
			pushi    #dispose
			pushi    0
			lat      temp84
			send     4
			pushi    #dispose
			pushi    0
			lat      temp81
			send     4
			jmp      code_0576
code_023a:
			dup     
			ldi      6400
			eq?     
			bnt      code_024f
			pushi    0
			call     noScrolling,  0
			pushi    1
			pushi    2
			callk    Show,  2
			jmp      code_0576
code_024f:
			dup     
			ldi      11776
			eq?     
			bnt      code_029d
			pushi    0
			call     noScrolling,  0
			pushi    1
			pushi    4
			callk    Show,  2
			pushi    1
			pushi    #elements
			pushi    0
			lag      cast
			send     4
			push    
			callk    Animate,  2
code_026e:
			pushi    0
			pushi    #type
			pushi    0
			pushi    #new
			pushi    1
			pushi    32765
			class    Event
			send     6
			sap      event
			send     4
			eq?     
			bnt      code_028e
			pushi    #dispose
			pushi    0
			lap      event
			send     4
			jmp      code_026e
code_028e:
			pushi    #dispose
			pushi    0
			lap      event
			send     4
			pushi    1
			pushi    1
			callk    Show,  2
			jmp      code_0576
code_029d:
			dup     
			ldi      7680
			eq?     
			bnt      code_0383
			pushi    #first
			pushi    0
			lag      cast
			send     4
			sat      temp79
code_02ae:
			lat      temp79
			bnt      code_0576
			pushi    1
			push    
			callk    NodeValue,  2
			sat      temp80
			pushi    9
			pushi    14
			lea      @str
			push    
			pushi    800
			pushi    1
			pushi    #name
			pushi    0
			pushi    #superClass
			pushi    0
			lat      temp80
			send     4
			send     4
			push    
			pushi    #view
			pushi    0
			lat      temp80
			send     4
			push    
			pushi    #loop
			pushi    0
			lat      temp80
			send     4
			push    
			pushi    #cel
			pushi    0
			lat      temp80
			send     4
			push    
			pushi    #x
			pushi    0
			lat      temp80
			send     4
			push    
			pushi    #y
			pushi    0
			lat      temp80
			send     4
			push    
			pushi    #z
			pushi    0
			lat      temp80
			send     4
			push    
			pushi    #heading
			pushi    0
			lat      temp80
			send     4
			push    
			pushi    #priority
			pushi    0
			lat      temp80
			send     4
			push    
			pushi    #signal
			pushi    0
			lat      temp80
			send     4
			push    
			pushi    #superClass
			pushi    0
			lat      temp80
			send     4
			push    
			class    Actor
			eq?     
			bt       code_0334
			pushi    #superClass
			pushi    0
			lat      temp80
			send     4
			push    
			class    Ego
			eq?     
			bnt      code_033e
code_0334:
			pushi    #illegalBits
			pushi    0
			lat      temp80
			send     4
			jmp      code_0340
code_033e:
			ldi      65535
code_0340:
			push    
			callk    Format,  28
			push    
			pushi    35
			class    SysWindow
			push    
			pushi    80
			pushi    #name
			pushi    0
			lat      temp80
			send     4
			push    
			pushi    82
			pushi    #view
			pushi    0
			lat      temp80
			send     4
			push    
			pushi    #loop
			pushi    0
			lat      temp80
			send     4
			push    
			pushi    #cel
			pushi    0
			lat      temp80
			send     4
			push    
			calle    HighPrint,  18
			pushi    #next
			pushi    1
			lst      temp79
			lag      cast
			send     6
			sat      temp79
			jmp      code_02ae
			jmp      code_0576
code_0383:
			dup     
			ldi      5120
			eq?     
			bnt      code_03bc
			lag      modelessDialog
			bnt      code_0395
			pushi    #dispose
			pushi    0
			send     4
code_0395:
			pushi    1
			lofsa    {Teleport to:}
			push    
			calle    GetNumber,  2
			sat      temp79
			push    
			ldi      0
			gt?     
			bnt      code_0576
			pushi    #stop
			pushi    0
			lag      cSound
			send     4
			pushi    #newRoom
			pushi    1
			lst      temp79
			lag      curRoom
			send     6
			jmp      code_0576
code_03bc:
			dup     
			ldi      8192
			eq?     
			bnt      code_03e5
			lag      debugOn
			not     
			sag      debugOn
			bnt      code_03d8
			pushi    2
			pushi    800
			pushi    2
			calle    HighPrint,  4
			jmp      code_0576
code_03d8:
			pushi    2
			pushi    800
			pushi    3
			calle    HighPrint,  4
			jmp      code_0576
code_03e5:
			dup     
			ldi      4864
			eq?     
			bnt      code_0482
			pushi    15
			pushi    800
			pushi    4
			lsg      curRoomNum
			pushi    #name
			pushi    0
			lag      curRoom
			send     4
			push    
			pushi    1
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			push    
			callk    IsObject,  2
			bnt      code_041e
			pushi    #name
			pushi    0
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			send     4
			jmp      code_0421
code_041e:
			lofsa    {..none..}
code_0421:
			push    
			pushi    #horizon
			pushi    0
			lag      curRoom
			send     4
			push    
			pushi    #vanishingX
			pushi    0
			lag      curRoom
			send     4
			push    
			pushi    #vanishingY
			pushi    0
			lag      curRoom
			send     4
			push    
			pushi    #picAngle
			pushi    0
			lag      curRoom
			send     4
			push    
			pushi    #north
			pushi    0
			lag      curRoom
			send     4
			push    
			pushi    #south
			pushi    0
			lag      curRoom
			send     4
			push    
			pushi    #east
			pushi    0
			lag      curRoom
			send     4
			push    
			pushi    #west
			pushi    0
			lag      curRoom
			send     4
			push    
			pushi    #style
			pushi    0
			lag      curRoom
			send     4
			push    
			pushi    #curPic
			pushi    0
			lag      curRoom
			send     4
			push    
			calle    Printf,  30
			jmp      code_0576
code_0482:
			dup     
			ldi      5376
			eq?     
			bnt      code_050f
			pushi    4
			pushi    800
			pushi    5
			pushi    #vanishingX
			pushi    0
			lag      curRoom
			send     4
			push    
			pushi    #vanishingY
			pushi    0
			lag      curRoom
			send     4
			push    
			calle    Printf,  8
			pushi    1
			lofsa    {vanishingX:}
			push    
			call     localproc_000e,  2
			sat      temp79
			pushi    3
			push    
			pushi    65535
			pushi    0
			calle    OneOf,  6
			bt       code_04cb
			pushi    #vanishingX
			pushi    1
			lst      temp79
			lag      curRoom
			send     6
code_04cb:
			pushi    1
			lofsa    {vanishingY:}
			push    
			call     localproc_000e,  2
			sat      temp79
			pushi    3
			push    
			pushi    65535
			pushi    0
			calle    OneOf,  6
			bt       code_04ef
			pushi    #vanishingY
			pushi    1
			lst      temp79
			lag      curRoom
			send     6
code_04ef:
			pushi    4
			pushi    800
			pushi    5
			pushi    #vanishingX
			pushi    0
			lag      curRoom
			send     4
			push    
			pushi    #vanishingY
			pushi    0
			lag      curRoom
			send     4
			push    
			calle    Printf,  8
			jmp      code_0576
code_050f:
			dup     
			ldi      4352
			eq?     
			bnt      code_0521
			pushi    #doit
			pushi    0
			class    102
			send     4
			jmp      code_0576
code_0521:
			dup     
			ldi      5632
			eq?     
			bnt      code_053a
			pushi    #canInput
			pushi    1
			pushi    1
			pushi    282
			pushi    1
			pushi    1
			class    User
			send     12
			jmp      code_0576
code_053a:
			dup     
			ldi      11264
			eq?     
			bnt      code_0549
			ldi      1
			sag      quit
			jmp      code_0576
code_0549:
			dup     
			ldi      5888
			eq?     
			bnt      code_055c
			pushi    #doit
			pushi    0
			lofsa    dInvD
			send     4
			jmp      code_0576
code_055c:
			dup     
			ldi      12288
			eq?     
			bnt      code_056e
			pushi    #doit
			pushi    0
			class    100
			send     4
			jmp      code_0576
code_056e:
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
code_0576:
			toss    
			jmp      code_06c7
code_057a:
			dup     
			ldi      1
			eq?     
			bnt      code_06c7
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      4
			eq?     
			bnt      code_05f2
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
code_0597:
			pushi    2
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp77
			send     4
			ne?     
			bnt      code_05e8
			pushi    284
			pushi    #-info-
			pushi    #x
			pushi    0
			lat      temp77
			send     4
			push    
			pushi    #y
			pushi    0
			lat      temp77
			send     4
			push    
			ldi      10
			sub     
			push    
			pushi    283
			pushi    1
			pushi    0
			pushi    #alterEgo
			pushi    0
			class    User
			send     4
			send     14
			pushi    2
			pushi    #elements
			pushi    0
			lag      cast
			send     4
			push    
			pushi    0
			callk    Animate,  4
			pushi    #dispose
			pushi    0
			lat      temp77
			send     4
			jmp      code_0597
code_05e8:
			pushi    #dispose
			pushi    0
			lat      temp77
			send     4
			jmp      code_06c7
code_05f2:
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      8
			and     
			bnt      code_06c7
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    7
			pushi    5
			lea      @str
			push    
			pushi    800
			pushi    6
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callk    Format,  10
			push    
			pushi    67
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			ldi      20
			lt?     
			bnt      code_0643
			pushi    #x
			pushi    0
			lap      event
			send     4
			jmp      code_066a
code_0643:
			pushi    300
			pushi    #x
			pushi    0
			lap      event
			send     4
			lt?     
			bnt      code_065f
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			ldi      40
			sub     
			jmp      code_066a
code_065f:
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			ldi      20
			sub     
code_066a:
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			ldi      16
			lt?     
			bnt      code_0683
			pushi    #y
			pushi    0
			lap      event
			send     4
			jmp      code_068e
code_0683:
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			ldi      6
			sub     
code_068e:
			push    
			pushi    33
			pushi    999
			pushi    108
			calle    HighPrint,  14
			sat      temp76
code_069c:
			pushi    2
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp77
			send     4
			ne?     
			bnt      code_06b9
			pushi    #dispose
			pushi    0
			lat      temp77
			send     4
			jmp      code_069c
code_06b9:
			pushi    #dispose
			pushi    0
			lat      temp77
			send     4
			pushi    #dispose
			pushi    0
			lat      temp76
			send     4
code_06c7:
			toss    
			ret     
		)
	)
)

(instance dInvD of Dialog
	(properties)
	
	(method (init &tmp theX theY temp2 temp3 theText inventoryFirst obj)
		(= temp2 (= theX (= theY 4)))
		(= temp3 0)
		(= inventoryFirst (inventory first:))
		(while inventoryFirst
			(= obj (NodeValue inventoryFirst))
			(++ temp3)
			(if (obj isKindOf: InvItem)
				(self
					add:
						((= theText (DText new:))
							value: obj
							text: (obj name?)
							nsLeft: theX
							nsTop: theY
							state: 3
							font: smallFont
							setSize:
							yourself:
						)
				)
			)
			(if
			(< temp2 (- (theText nsRight?) (theText nsLeft?)))
				(= temp2 (- (theText nsRight?) (theText nsLeft?)))
			)
			(if
				(>
					(= theY
						(+ theY (- (theText nsBottom?) (theText nsTop?)) 1)
					)
					140
				)
				(= theY 4)
				(= theX (+ theX temp2 10))
				(= temp2 0)
			)
			(= inventoryFirst (inventory next: inventoryFirst))
		)
		(= window systemWindow)
		(self setSize:)
		(= newDButton (DButton new:))
		(newDButton
			text: {Outta here!}
			setSize:
			moveTo: (- nsRight (+ 4 (newDButton nsRight?))) nsBottom
		)
		(newDButton
			move: (- (newDButton nsLeft?) (newDButton nsRight?)) 0
		)
		(self add: newDButton setSize: center:)
		(return temp3)
	)
	
	(method (doit &tmp theNewDButton)
		(self init:)
		(self open: wTitled 15)
		(= theNewDButton newDButton)
		(repeat
			(if
				(or
					(not (= theNewDButton (super doit: theNewDButton)))
					(== theNewDButton -1)
					(== theNewDButton newDButton)
				)
				(break)
			)
			((theNewDButton value?) owner: ego)
		)
		(self dispose:)
	)
	
	(method (handleEvent event &tmp eMsg eType)
		(= eMsg (event message?))
		(switch (= eType (event type?))
			(keyDown
				(switch eMsg
					(UPARROW
						(= eMsg SHIFTTAB)
					)
					(DOWNARROW
						(= eMsg TAB)
					)
				)
			)
			(direction
				(switch eMsg
					(dirN
						(= eMsg SHIFTTAB)
						(= eType keyDown)
					)
					(dirS
						(= eMsg TAB)
						(= eType keyDown)
					)
				)
			)
		)
		(event type: eType message: eMsg)
		(super handleEvent: event)
	)
)
