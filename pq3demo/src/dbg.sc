;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG) ;899
(include game.sh)
(use Main)
(use Intrface)
(use Feature)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	dbg 0
)

(local
	outtaHereButton
)
(procedure (localproc_000e param1 param2 &tmp [str 40])
	(= str 0)
	(if (> argc 1) (Format @str DEBUG 0 param2))
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
		(curRoom drawPic: (curRoom picture?) PLAIN style: PLAIN)
	)
)

(instance dbg of Feature
	(properties)
	
	(method (handleEvent event &tmp [temp0 75] temp75 temp76 temp77 temp78 temp79 temp80 temp81 temp82 temp83 temp84 temp85 temp86)
		(asm
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      4
			eq?     
			bnt      code_06fa
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
			ldi      9472
			eq?     
			bnt      code_01c6
			pushi    0
			callk    GetPort,  0
			sat      temp75
			pushi    1
			pushi    0
			callk    SetPort,  2
			ldi      5
			sat      temp85
			ldi      16
			sat      temp86
			ldi      15
			sat      temp81
			ldi      80
			sat      temp82
			lst      temp81
			pushi    34
			lat      temp85
			mul     
			add     
			sat      temp84
			lst      temp82
			pushi    10
			lat      temp86
			mul     
			add     
			sat      temp83
			pushi    6
			pushi    7
			lst      temp81
			lst      temp82
			lst      temp84
			push    
			pushi    1
			callk    Graph,  12
			sat      temp79
			pushi    7
			pushi    11
			lst      temp81
			lst      temp82
			lst      temp84
			lst      temp83
			pushi    1
			pushi    255
			callk    Graph,  14
			ldi      0
			sat      temp80
code_00fe:
			lst      temp80
			ldi      256
			lt?     
			bnt      code_015e
			pushi    7
			pushi    11
			lst      temp81
			lat      temp85
			add     
			push    
			lst      temp85
			lst      temp80
			ldi      8
			div     
			mul     
			add     
			push    
			lst      temp82
			lat      temp86
			add     
			push    
			pushi    16
			lst      temp80
			ldi      8
			mod     
			mul     
			add     
			push    
			lst      temp81
			lat      temp85
			add     
			push    
			lat      temp85
			add     
			push    
			lst      temp85
			lst      temp80
			ldi      8
			div     
			mul     
			add     
			push    
			lst      temp82
			lat      temp86
			add     
			push    
			lat      temp86
			add     
			push    
			lst      temp86
			lst      temp80
			ldi      8
			mod     
			mul     
			add     
			push    
			pushi    1
			lst      temp80
			callk    Graph,  14
			+at      temp80
			jmp      code_00fe
code_015e:
			pushi    6
			pushi    12
			lst      temp81
			lst      temp82
			lst      temp84
			lst      temp83
			pushi    1
			callk    Graph,  12
code_016e:
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp76
			pushi    #type
			pushi    0
			send     4
			push    
			ldi      1
			eq?     
			bt       code_0191
			pushi    #type
			pushi    0
			lat      temp76
			send     4
			push    
			ldi      4
			eq?     
			bnt      code_0194
code_0191:
			jmp      code_019e
code_0194:
			pushi    #dispose
			pushi    0
			lat      temp76
			send     4
			jmp      code_016e
code_019e:
			pushi    #dispose
			pushi    0
			lat      temp76
			send     4
			pushi    2
			pushi    8
			lst      temp79
			callk    Graph,  4
			pushi    6
			pushi    12
			lst      temp81
			lst      temp82
			lst      temp84
			lst      temp83
			pushi    1
			callk    Graph,  12
			pushi    1
			lst      temp75
			callk    SetPort,  2
			jmp      code_06f6
code_01c6:
			dup     
			ldi      8448
			eq?     
			bnt      code_01d9
			pushi    #showMem
			pushi    0
			lag      theGame
			send     4
			jmp      code_06f6
code_01d9:
			dup     
			ldi      8704
			eq?     
			bnt      code_0227
			ldi      0
			sat      temp0
			pushi    3
			lea      @temp0
			push    
			pushi    4
			lofsa    {Variable No.}
			push    
			calle    GetInput,  6
			pushi    1
			lea      @temp0
			push    
			callk    ReadNumber,  2
			sat      temp77
			ldi      0
			sat      temp0
			pushi    3
			lea      @temp0
			push    
			pushi    4
			lofsa    {Value}
			push    
			calle    GetInput,  6
			pushi    1
			lea      @temp0
			push    
			callk    ReadNumber,  2
			push    
			lat      temp77
			sagi     ego
			ldi      0
			sat      temp0
			jmp      code_06f6
code_0227:
			dup     
			ldi      8960
			eq?     
			bnt      code_0286
			ldi      0
			sat      temp0
			pushi    3
			lea      @temp0
			push    
			pushi    4
			lofsa    {Variable No.}
			push    
			calle    GetInput,  6
			pushi    1
			lea      @temp0
			push    
			callk    ReadNumber,  2
			sat      temp77
			pushi    1
			lsgi     ego
			callk    IsObject,  2
			bnt      code_026f
			pushi    4
			pushi    899
			pushi    1
			lst      temp77
			pushi    #name
			pushi    0
			lat      temp77
			lagi     ego
			send     4
			push    
			calle    Printf,  8
			jmp      code_027f
code_026f:
			pushi    4
			pushi    899
			pushi    2
			lst      temp77
			lat      temp77
			lsgi     ego
			calle    Printf,  8
code_027f:
			ldi      0
			sat      temp0
			jmp      code_06f6
code_0286:
			dup     
			ldi      12032
			eq?     
			bnt      code_0296
			pushi    1
			pushi    1
			callk    Show,  2
			jmp      code_06f6
code_0296:
			dup     
			ldi      6400
			eq?     
			bnt      code_02ab
			pushi    0
			call     noScrolling,  0
			pushi    1
			pushi    2
			callk    Show,  2
			jmp      code_06f6
code_02ab:
			dup     
			ldi      11776
			eq?     
			bnt      code_02f9
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
code_02ca:
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
			bnt      code_02ea
			pushi    #dispose
			pushi    0
			lap      event
			send     4
			jmp      code_02ca
code_02ea:
			pushi    #dispose
			pushi    0
			lap      event
			send     4
			pushi    1
			pushi    1
			callk    Show,  2
			jmp      code_06f6
code_02f9:
			dup     
			ldi      4608
			eq?     
			bnt      code_03c2
			pushi    #contains
			pushi    1
			lsg      ego
			lag      cast
			send     6
			bnt      code_03b5
			pushi    7
			pushi    16
			lea      @temp0
			push    
			pushi    899
			pushi    3
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #cel
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #z
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #priority
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #signal
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #illegalBits
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #normal
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			push    
			callk    Format,  32
			push    
			pushi    80
			pushi    #name
			pushi    0
			lag      ego
			send     4
			push    
			pushi    82
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #cel
			pushi    0
			lag      ego
			send     4
			push    
			calle    Print,  14
			jmp      code_06f6
code_03b5:
			pushi    2
			pushi    899
			pushi    4
			calle    Print,  4
			jmp      code_06f6
code_03c2:
			dup     
			ldi      7680
			eq?     
			bnt      code_04a2
			pushi    #first
			pushi    0
			lag      cast
			send     4
			sat      temp77
code_03d3:
			lat      temp77
			bnt      code_06f6
			pushi    1
			push    
			callk    NodeValue,  2
			sat      temp78
			pushi    5
			pushi    15
			lea      @temp0
			push    
			pushi    899
			pushi    5
			pushi    #name
			pushi    0
			lat      temp78
			send     4
			push    
			pushi    #name
			pushi    0
			pushi    #superClass
			pushi    0
			lat      temp78
			send     4
			send     4
			push    
			pushi    #view
			pushi    0
			lat      temp78
			send     4
			push    
			pushi    #loop
			pushi    0
			lat      temp78
			send     4
			push    
			pushi    #cel
			pushi    0
			lat      temp78
			send     4
			push    
			pushi    #x
			pushi    0
			lat      temp78
			send     4
			push    
			pushi    #y
			pushi    0
			lat      temp78
			send     4
			push    
			pushi    #z
			pushi    0
			lat      temp78
			send     4
			push    
			pushi    #heading
			pushi    0
			lat      temp78
			send     4
			push    
			pushi    #priority
			pushi    0
			lat      temp78
			send     4
			push    
			pushi    #signal
			pushi    0
			lat      temp78
			send     4
			push    
			pushi    #superClass
			pushi    0
			lat      temp78
			send     4
			push    
			class    Actor
			eq?     
			bt       code_0462
			pushi    #superClass
			pushi    0
			lat      temp78
			send     4
			push    
			class    Ego
			eq?     
			bnt      code_046c
code_0462:
			pushi    #illegalBits
			pushi    0
			lat      temp78
			send     4
			jmp      code_046e
code_046c:
			ldi      65535
code_046e:
			push    
			callk    Format,  30
			push    
			pushi    82
			pushi    #view
			pushi    0
			lat      temp78
			send     4
			push    
			pushi    #loop
			pushi    0
			lat      temp78
			send     4
			push    
			pushi    #cel
			pushi    0
			lat      temp78
			send     4
			push    
			calle    Print,  10
			pushi    #next
			pushi    1
			lst      temp77
			lag      cast
			send     6
			sat      temp77
			jmp      code_03d3
			jmp      code_06f6
code_04a2:
			dup     
			ldi      5120
			eq?     
			bnt      code_04db
			lag      modelessDialog
			bnt      code_04b4
			pushi    #dispose
			pushi    0
			send     4
code_04b4:
			pushi    1
			lofsa    {Teleport to:}
			push    
			calle    GetNumber,  2
			sat      temp77
			push    
			ldi      0
			gt?     
			bnt      code_06f6
			pushi    #stop
			pushi    0
			lag      music
			send     4
			pushi    #newRoom
			pushi    1
			lst      temp77
			lag      curRoom
			send     6
			jmp      code_06f6
code_04db:
			dup     
			ldi      8192
			eq?     
			bnt      code_0505
			lag      debugOn
			not     
			sag      debugOn
			bnt      code_04f8
			pushi    2
			pushi    899
			pushi    6
			calle    Print,  4
			jmp      code_06f6
code_04f8:
			pushi    2
			pushi    899
			pushi    7
			calle    Print,  4
			jmp      code_06f6
code_0505:
			dup     
			ldi      4864
			eq?     
			bnt      code_05a2
			pushi    15
			pushi    899
			pushi    8
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
			bnt      code_053e
			pushi    #name
			pushi    0
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			send     4
			jmp      code_0541
code_053e:
			lofsa    {..none..}
code_0541:
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
			jmp      code_06f6
code_05a2:
			dup     
			ldi      5376
			eq?     
			bnt      code_062f
			pushi    4
			pushi    899
			pushi    9
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
			sat      temp77
			pushi    3
			push    
			pushi    65535
			pushi    0
			calle    OneOf,  6
			bt       code_05eb
			pushi    #vanishingX
			pushi    1
			lst      temp77
			lag      curRoom
			send     6
code_05eb:
			pushi    1
			lofsa    {vanishingY:}
			push    
			call     localproc_000e,  2
			sat      temp77
			pushi    3
			push    
			pushi    65535
			pushi    0
			calle    OneOf,  6
			bt       code_060f
			pushi    #vanishingY
			pushi    1
			lst      temp77
			lag      curRoom
			send     6
code_060f:
			pushi    4
			pushi    899
			pushi    9
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
			jmp      code_06f6
code_062f:
			dup     
			ldi      4352
			eq?     
			bnt      code_0641
			pushi    #doit
			pushi    0
			class    102
			send     4
			jmp      code_06f6
code_0641:
			dup     
			ldi      5632
			eq?     
			bnt      code_0670
			pushi    #canInput
			pushi    1
			pushi    1
			pushi    281
			pushi    1
			pushi    1
			class    User
			send     12
			pushi    #enable
			pushi    8
			pushi    0
			pushi    1
			pushi    2
			pushi    3
			pushi    9
			pushi    7
			pushi    4
			pushi    5
			lag      theIconBar
			send     20
			jmp      code_06f6
code_0670:
			dup     
			ldi      11264
			eq?     
			bnt      code_067f
			ldi      1
			sag      quit
			jmp      code_06f6
code_067f:
			dup     
			ldi      5888
			eq?     
			bnt      code_0692
			pushi    #doit
			pushi    0
			lofsa    dInvD
			send     4
			jmp      code_06f6
code_0692:
			dup     
			ldi      12288
			eq?     
			bnt      code_06a4
			pushi    #doit
			pushi    0
			class    100
			send     4
			jmp      code_06f6
code_06a4:
			dup     
			ldi      3
			eq?     
			bnt      code_06c9
code_06ab:
			pushi    1
			lofsa    {Clear Flag#:_}
			push    
			calle    GetNumber,  2
			sat      temp77
			push    
			ldi      65535
			ne?     
			bt       code_06c0
			jmp      code_06ab
code_06c0:
			pushi    1
			lst      temp77
			callb    Bclr,  2
			jmp      code_06f6
code_06c9:
			dup     
			ldi      19
			eq?     
			bnt      code_06ee
code_06d0:
			pushi    1
			lofsa    {Set Flag#:_}
			push    
			calle    GetNumber,  2
			sat      temp77
			push    
			ldi      65535
			ne?     
			bt       code_06e5
			jmp      code_06d0
code_06e5:
			pushi    1
			lst      temp77
			callb    Bset,  2
			jmp      code_06f6
code_06ee:
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
code_06f6:
			toss    
			jmp      code_084b
code_06fa:
			dup     
			ldi      1
			eq?     
			bnt      code_084b
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      8
			eq?     
			bnt      code_07dd
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    7
			pushi    5
			lea      @temp0
			push    
			pushi    899
			pushi    10
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
			ldi      23
			lt?     
			bnt      code_074d
			ldi      3
			jmp      code_076d
code_074d:
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			ldi      292
			gt?     
			bnt      code_0762
			ldi      273
			jmp      code_076d
code_0762:
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			ldi      20
			sub     
code_076d:
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			ldi      9
			lt?     
			bnt      code_0781
			ldi      3
			jmp      code_07a1
code_0781:
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			ldi      175
			gt?     
			bnt      code_0796
			ldi      170
			jmp      code_07a1
code_0796:
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			ldi      6
			sub     
code_07a1:
			push    
			pushi    33
			pushi    999
			pushi    107
			calle    Print,  14
			sat      temp75
code_07af:
			pushi    2
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp76
			send     4
			ne?     
			bnt      code_07cc
			pushi    #dispose
			pushi    0
			lat      temp76
			send     4
			jmp      code_07af
code_07cc:
			pushi    #dispose
			pushi    0
			lat      temp76
			send     4
			pushi    #dispose
			pushi    0
			lat      temp75
			send     4
			jmp      code_084b
code_07dd:
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      12
			eq?     
			bnt      code_084b
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
code_07f3:
			pushi    2
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp76
			send     4
			ne?     
			bnt      code_0844
			pushi    283
			pushi    #-info-
			pushi    #x
			pushi    0
			lat      temp76
			send     4
			push    
			pushi    #y
			pushi    0
			lat      temp76
			send     4
			push    
			ldi      10
			sub     
			push    
			pushi    282
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
			lat      temp76
			send     4
			jmp      code_07f3
code_0844:
			pushi    #dispose
			pushi    0
			lat      temp76
			send     4
code_084b:
			toss    
			ret     
		)
	)
)

(instance dInvD of Dialog
	(properties)
	
	(method (init &tmp left top temp2 i invName node obj)
		(= temp2 (= left (= top MARGIN)))
		(= i 0)
		(= node (inventory first:))
		(while node
			(= obj (NodeValue node))
			(++ i)
			(if (obj isKindOf: InvItem)
				(self
					add:
						((= invName (DText new:))
							value: obj
							text: (obj name?)
							nsLeft: left
							nsTop: top
							state: 3
							font: smallFont
							setSize:
							yourself:
						)
				)
			)
			(if
			(< temp2 (- (invName nsRight?) (invName nsLeft?)))
				(= temp2 (- (invName nsRight?) (invName nsLeft?)))
			)
			(if
				(>
					(= top
						(+ top (- (invName nsBottom?) (invName nsTop?)) 1)
					)
					140
				)
				(= top 4)
				(= left (+ left temp2 10))
				(= temp2 0)
			)
			(= node (inventory next: node))
		)
		(= window systemWindow)
		(self setSize:)
		(= outtaHereButton (DButton new:))
		(outtaHereButton
			text: {Outta here!}
			setSize:
			moveTo: (- nsRight (+ MARGIN (outtaHereButton nsRight?))) nsBottom
		)
		(outtaHereButton
			move: (- (outtaHereButton nsLeft?) (outtaHereButton nsRight?)) 0
		)
		(self add: outtaHereButton setSize: center:)
		(return i)
	)
	
	(method (doit &tmp theNewDButton)
		(self init:)
		(self open: 4 15)
		(= theNewDButton outtaHereButton)
		(repeat
			(if
				(or
					(not (= theNewDButton (super doit: theNewDButton)))
					(== theNewDButton -1)
					(== theNewDButton outtaHereButton)
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
					(UPARROW (= eMsg SHIFTTAB))
					(DOWNARROW (= eMsg TAB))
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
