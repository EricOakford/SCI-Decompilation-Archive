;;; Sierra Script 1.0 - (do not remove this comment)
(script# 78)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	Room78 0
)
(synonyms
	(kiss kiss embrace)
	(cobra cobra)
)

(local
	local0
	local1
	[jumpArea 18]
	gEgoViewer
	snake
	snakeState
	fruit
	board
	ripple1
	ripple2
	newProp_4
)
(instance gotFruit of Sound
	(properties)
)

(instance theme of Sound
	(properties)
)

(instance charm of Sound
	(properties
		number 55
	)
)

(instance Room78 of Room
	(properties
		picture 78
	)
	
	(method (init)
		(= east 78)
		(= west 77)
		(= horizon 40)
		(= isIndoors FALSE)
		(if isNightTime (= picture 178))
		(super init:)
		(self setRegions: SWAMP)
		(Load VIEW 330)
		(Load VIEW 69)
		(Load VIEW 59)
		(if (ego has: iSilverFlute)
			(Load VIEW 888)
			(Load VIEW 55)
			(Load SOUND 55)
		)
		(if (ego has: iBoard) (Load VIEW 21) (Load VIEW 515))
		(Load SOUND 50)
		(Load SOUND 39)
		(Load SOUND 40)
		(Load VIEW 508)
		(= ripple1 (Prop new:))
		(= ripple2 (Prop new:))
		(ripple1
			isExtra: TRUE
			view: 650
			loop: 3
			cel: 1
			posn: 126 87
			setPri: 0
			setCycle: Forward
			init:
		)
		(ripple2
			isExtra: 1
			view: 650
			loop: 5
			cel: 0
			posn: 74 142
			setPri: 0
			setCycle: Forward
			init:
		)
		(= currentStatus egoOnSwampGrass)
		(switch prevRoomNum
			(west
				(ego
					view: 2
					loop: 0
					cel: 0
					posn: 3 166
					setCycle: Walk
					setScript: boardActions
				)
			)
			(0
				(ego
					view: 2
					loop: 0
					cel: 0
					posn: 3 166
					setCycle: Walk
					setScript: boardActions
				)
			)
		)
		(ego view: 2 xStep: 3 yStep: 2 init:)
		(ego edgeHit: 0)
		(= [jumpArea 0] 5)
		(= [jumpArea 1] 167)
		(= [jumpArea 2] 27)
		(= [jumpArea 3] 170)
		(= [jumpArea 4] 42)
		(= [jumpArea 5] 167)
		(= [jumpArea 6] 61)
		(= [jumpArea 7] 169)
		(= [jumpArea 8] 79)
		(= [jumpArea 9] 166)
		(= [jumpArea 10] 98)
		(= [jumpArea 11] 169)
		(= [jumpArea 12] 117)
		(= [jumpArea 13] 170)
		(= [jumpArea 14] 124)
		(= [jumpArea 15] 162)
		(if ((Inventory at: iMagicFruit) ownedBy: 78)
			(= fruit (Prop new:))
			(fruit
				view: 508
				posn: 187 146
				setLoop: 0
				cycleSpeed: 1
				setPri: 12
				setScript: fruitActions
				init:
			)
		)
		(= snake (Actor new:))
		(snake
			view: 330
			posn: 196 163
			cel: 0
			setLoop: 0
			setScript: snakeActions
			init:
		)
		(if ((Inventory at: iBoard) ownedBy: 78)
			((= board (View new:))
				view: 515
				cel: 0
				loop: 0
				ignoreActors:
				setPri: 12
				posn: 121 168
				init:
			)
			(boardActions state: 3)
		)
		(curRoom setScript: jump)
		(= inCutscene TRUE)
		(User canInput: TRUE)
	)
	
	(method (dispose)
		(DisposeScript JUMP)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(asm
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bnt      code_038e
			ldi      1
			ret     
code_038e:
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      128
			eq?     
			bnt      code_08e2
			pushi    1
			lofsa    'hop,hop'
			push    
			callk    Said,  2
			bnt      code_0437
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      109
			gt?     
			bnt      code_03c1
			pushi    2
			pushi    78
			pushi    0
			calle    Print,  4
			jmp      code_08e2
code_03c1:
			lsl      local0
			ldi      5
			eq?     
			bnt      code_0418
			pushi    #ownedBy
			pushi    1
			pushi    78
			pushi    #at
			pushi    1
			pushi    22
			class    Inventory
			send     6
			send     6
			bnt      code_0418
			pushi    #inRect
			pushi    4
			pushi    92
			pushi    164
			pushi    109
			pushi    172
			lag      ego
			send     12
			bnt      code_040c
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			ldi      1
			ne?     
			bnt      code_040c
			pushi    2
			pushi    78
			pushi    1
			calle    Print,  4
			jmp      code_08e2
code_040c:
			pushi    #changeState
			pushi    1
			pushi    1
			lofsa    jump
			send     6
			jmp      code_08e2
code_0418:
			lsg      currentStatus
			ldi      13
			eq?     
			bnt      code_042b
			pushi    2
			pushi    78
			pushi    2
			calle    Print,  4
			jmp      code_08e2
code_042b:
			pushi    #changeState
			pushi    1
			pushi    1
			lofsa    jump
			send     6
			jmp      code_08e2
code_0437:
			pushi    1
			lofsa    'play/flute'
			push    
			callk    Said,  2
			bnt      code_04c4
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      2
			eq?     
			bnt      code_04b7
			pushi    #has
			pushi    1
			pushi    0
			lag      ego
			send     6
			bnt      code_04ab
			pushi    #state
			pushi    0
			lofsa    snakeActions
			send     4
			push    
			ldi      21
			eq?     
			bnt      code_048a
			pushi    #seconds
			pushi    1
			pushi    0
			lofsa    snakeActions
			send     6
			pushi    2
			pushi    78
			pushi    3
			calle    Print,  4
			pushi    #seconds
			pushi    1
			pushi    15
			lofsa    snakeActions
			send     6
			jmp      code_0494
code_048a:
			pushi    #changeState
			pushi    1
			pushi    20
			lofsa    snakeActions
			send     6
code_0494:
			lag      global155
			not     
			bnt      code_08e2
			pushi    #changeScore
			pushi    1
			pushi    4
			lag      theGame
			send     6
			ldi      1
			sag      global155
			jmp      code_08e2
code_04ab:
			pushi    2
			pushi    800
			pushi    2
			calle    Print,  4
			jmp      code_08e2
code_04b7:
			pushi    2
			pushi    800
			pushi    3
			calle    Print,  4
			jmp      code_08e2
code_04c4:
			pushi    1
			lofsa    'get/fruit'
			push    
			callk    Said,  2
			bnt      code_056a
			pushi    #has
			pushi    1
			pushi    25
			lag      ego
			send     6
			bnt      code_04e8
			pushi    2
			pushi    78
			pushi    4
			calle    Print,  4
			jmp      code_08e2
code_04e8:
			lsl      snakeState
			ldi      2
			eq?     
			bnt      code_0563
			pushi    #inRect
			pushi    4
			pushi    171
			pushi    157
			pushi    195
			pushi    167
			lag      ego
			send     12
			bnt      code_0563
			pushi    #ownedBy
			pushi    1
			pushi    78
			pushi    #at
			pushi    1
			pushi    25
			class    Inventory
			send     6
			send     6
			bnt      code_0557
			pushi    2
			pushi    78
			pushi    5
			calle    Print,  4
			pushi    #number
			pushi    1
			pushi    50
			pushi    42
			pushi    0
			lofsa    gotFruit
			send     10
			pushi    #dispose
			pushi    0
			lal      fruit
			send     4
			pushi    #moveTo
			pushi    1
			lsg      ego
			pushi    #at
			pushi    1
			pushi    25
			class    Inventory
			send     6
			send     6
			pushi    #changeScore
			pushi    1
			pushi    10
			lag      theGame
			send     6
			jmp      code_08e2
code_0557:
			pushi    2
			pushi    78
			pushi    6
			calle    Print,  4
			jmp      code_08e2
code_0563:
			pushi    0
			callb    NotClose,  0
			jmp      code_08e2
code_056a:
			pushi    1
			lofsa    'get/board'
			push    
			callk    Said,  2
			bnt      code_05e1
			pushi    #ownedBy
			pushi    1
			pushi    78
			pushi    #at
			pushi    1
			pushi    22
			class    Inventory
			send     6
			send     6
			not     
			bnt      code_0596
			pushi    2
			pushi    78
			pushi    7
			calle    Print,  4
			jmp      code_08e2
code_0596:
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      109
			gt?     
			bnt      code_05af
			pushi    2
			pushi    78
			pushi    0
			calle    Print,  4
			jmp      code_08e2
code_05af:
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			push    
			ldi      1024
			ne?     
			bt       code_05c8
			lsl      local0
			ldi      5
			ne?     
			bnt      code_05d4
code_05c8:
			pushi    2
			pushi    78
			pushi    8
			calle    Print,  4
			jmp      code_08e2
code_05d4:
			pushi    #changeState
			pushi    1
			pushi    10
			lofsa    boardActions
			send     6
			jmp      code_08e2
code_05e1:
			pushi    1
			lofsa    'lay,place/board'
			push    
			callk    Said,  2
			bnt      code_064b
			pushi    #has
			pushi    1
			pushi    22
			lag      ego
			send     6
			not     
			bnt      code_0601
			pushi    0
			callb    DontHave,  0
			jmp      code_08e2
code_0601:
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      109
			gt?     
			bnt      code_061a
			pushi    2
			pushi    78
			pushi    0
			calle    Print,  4
			jmp      code_08e2
code_061a:
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			push    
			ldi      1024
			ne?     
			bt       code_0633
			lsl      local0
			ldi      5
			ne?     
			bnt      code_063f
code_0633:
			pushi    2
			pushi    78
			pushi    8
			calle    Print,  4
			jmp      code_08e2
code_063f:
			pushi    #changeState
			pushi    1
			pushi    1
			lofsa    boardActions
			send     6
			jmp      code_08e2
code_064b:
			pushi    1
			lofsa    'deliver/*[/cobra]>'
			push    
			callk    Said,  2
			bnt      code_069e
			pushi    #saidMe
			pushi    0
			lag      inventory
			send     4
			sat      temp0
			bnt      code_068a
			pushi    #has
			pushi    1
			pushi    #indexOf
			pushi    1
			push    
			lag      inventory
			send     6
			push    
			lag      ego
			send     6
			bnt      code_0683
			pushi    2
			pushi    78
			pushi    9
			calle    Print,  4
			jmp      code_08e2
code_0683:
			pushi    0
			callb    DontHave,  0
			jmp      code_08e2
code_068a:
			pushi    2
			pushi    78
			pushi    10
			calle    Print,  4
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_08e2
code_069e:
			pushi    1
			lofsa    'throw/*[/cobra]>'
			push    
			callk    Said,  2
			bnt      code_06e2
			pushi    #saidMe
			pushi    0
			lag      inventory
			send     4
			sat      temp0
			bnt      code_06e2
			pushi    #has
			pushi    1
			pushi    #indexOf
			pushi    1
			lst      temp0
			lag      inventory
			send     6
			push    
			lag      ego
			send     6
			bnt      code_06d7
			pushi    2
			pushi    78
			pushi    11
			calle    Print,  4
			jmp      code_08e2
code_06d7:
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
			jmp      code_08e2
code_06e2:
			pushi    1
			lofsa    'look>'
			push    
			callk    Said,  2
			bnt      code_0858
			pushi    1
			lofsa    '/cobra'
			push    
			callk    Said,  2
			bnt      code_073f
			pushi    #state
			pushi    0
			lofsa    snakeActions
			send     4
			push    
			ldi      21
			eq?     
			bnt      code_0713
			pushi    2
			pushi    78
			pushi    12
			calle    Print,  4
			jmp      code_08e2
code_0713:
			pushi    #ownedBy
			pushi    1
			pushi    78
			pushi    #at
			pushi    1
			pushi    25
			class    Inventory
			send     6
			send     6
			bnt      code_0733
			pushi    2
			pushi    78
			pushi    13
			calle    Print,  4
			jmp      code_08e2
code_0733:
			pushi    2
			pushi    78
			pushi    14
			calle    Print,  4
			jmp      code_08e2
code_073f:
			pushi    1
			lofsa    '/forest'
			push    
			callk    Said,  2
			bnt      code_0776
			pushi    #ownedBy
			pushi    1
			pushi    78
			pushi    #at
			pushi    1
			pushi    25
			class    Inventory
			send     6
			send     6
			bnt      code_076a
			pushi    2
			pushi    78
			pushi    15
			calle    Print,  4
			jmp      code_08e2
code_076a:
			pushi    2
			pushi    78
			pushi    16
			calle    Print,  4
			jmp      code_08e2
code_0776:
			pushi    1
			lofsa    '/fruit'
			push    
			callk    Said,  2
			bnt      code_07cb
			pushi    #ownedBy
			pushi    1
			pushi    78
			pushi    #at
			pushi    1
			pushi    25
			class    Inventory
			send     6
			send     6
			bnt      code_07a1
			pushi    2
			pushi    78
			pushi    17
			calle    Print,  4
			jmp      code_08e2
code_07a1:
			pushi    #has
			pushi    1
			pushi    25
			lag      ego
			send     6
			bnt      code_07bf
			pushi    #showSelf
			pushi    0
			pushi    #at
			pushi    1
			pushi    25
			class    Inventory
			send     6
			send     4
			jmp      code_08e2
code_07bf:
			pushi    2
			pushi    78
			pushi    18
			calle    Print,  4
			jmp      code_08e2
code_07cb:
			pushi    1
			lofsa    '/island'
			push    
			callk    Said,  2
			bnt      code_0802
			pushi    #ownedBy
			pushi    1
			pushi    78
			pushi    #at
			pushi    1
			pushi    25
			class    Inventory
			send     6
			send     6
			bnt      code_07f6
			pushi    2
			pushi    78
			pushi    19
			calle    Print,  4
			jmp      code_08e2
code_07f6:
			pushi    2
			pushi    78
			pushi    20
			calle    Print,  4
			jmp      code_08e2
code_0802:
			pushi    1
			lofsa    '/grass,tuft'
			push    
			callk    Said,  2
			bnt      code_0819
			pushi    2
			pushi    78
			pushi    21
			calle    Print,  4
			jmp      code_08e2
code_0819:
			pushi    1
			lofsa    '[<around][/marsh,room]'
			push    
			callk    Said,  2
			bnt      code_08e2
			pushi    1
			pushi    4
			lea      @str
			push    
			pushi    78
			pushi    22
			pushi    #ownedBy
			pushi    1
			pushi    78
			pushi    #at
			pushi    1
			pushi    25
			class    Inventory
			send     6
			send     6
			bnt      code_0849
			lofsa    {A large, glistening fruit hangs from a small branch.}
			jmp      code_084c
code_0849:
			lofsa    {LOOKUP\_ERROR}
code_084c:
			push    
			callk    Format,  8
			push    
			calle    Print,  2
			jmp      code_08e2
code_0858:
			pushi    1
			lofsa    '*[/cobra]>'
			push    
			callk    Said,  2
			bnt      code_08e2
			pushi    1
			lofsa    'converse'
			push    
			callk    Said,  2
			bnt      code_087c
			pushi    2
			pushi    78
			pushi    23
			calle    Print,  4
			ldi      1
			jmp      code_08dd
code_087c:
			pushi    1
			lofsa    'kill'
			push    
			callk    Said,  2
			bnt      code_0895
			pushi    2
			pushi    78
			pushi    24
			calle    Print,  4
			ldi      1
			jmp      code_08dd
code_0895:
			pushi    1
			lofsa    'get,capture/*'
			push    
			callk    Said,  2
			bnt      code_08ae
			pushi    2
			pushi    78
			pushi    25
			calle    Print,  4
			ldi      1
			jmp      code_08dd
code_08ae:
			pushi    1
			lofsa    'kiss'
			push    
			callk    Said,  2
			bnt      code_08c7
			pushi    2
			pushi    78
			pushi    26
			calle    Print,  4
			ldi      1
			jmp      code_08dd
code_08c7:
			pushi    1
			lofsa    'hit/*'
			push    
			callk    Said,  2
			bnt      code_08e2
			pushi    2
			pushi    78
			pushi    27
			calle    Print,  4
			ldi      1
code_08dd:
			bnt      code_08e2
			ldi      1
code_08e2:
			ret     
		)
	)
)

(instance jump of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(if (== (ego loop?) 0)
					(++ local0)
					(= local1 (+ local1 2))
				else
					(-- local0)
					(= local1 (- local1 2))
				)
				(if (== local0 -1) (curRoom newRoom: 77) (return))
				(HandsOff)
				(= gEgoViewer (ego viewer?))
				(ego viewer: 0)
				(ego view: 69 cel: 255 setCycle: EndLoop self)
			)
			(2 (ego setCycle: CycleTo 1 -1 self))
			(3
				(ego xStep: 6 yStep: 4)
				(cond 
					((== (ego loop?) 0)
						(ego
							setMotion:
								JumpTo
								[jumpArea local1]
								[jumpArea (+ (ego setLoop: 2 cel: 255 setCycle: EndLoop) 1)]
								self
						)
					)
					((== (ego loop?) 1)
						(ego
							setMotion:
								JumpTo
								[jumpArea local1]
								[jumpArea (+ (ego setLoop: 3 cel: 255 setCycle: EndLoop) 1)]
								self
						)
					)
					((== (ego loop?) 2)
						(ego viewer: gEgoViewer)
						(ego setMotion: JumpTo (ego x?) (+ (ego y?) 6))
					)
					((== (ego loop?) 3)
						(ego viewer: gEgoViewer)
						(ego setMotion: JumpTo (ego x?) (- (ego y?) 6))
					)
				)
			)
			(4
				(if (== local0 6) (ego viewer: gEgoViewer))
				(if (== (ego loop?) 2)
					(ego cel: 255 setLoop: -1 loop: 4 setCycle: EndLoop self)
				else
					(ego cel: 255 setLoop: -1 loop: 5 setCycle: EndLoop self)
				)
			)
			(5
				(if (== (ego loop?) 4)
					(ego view: 2 loop: 0 cel: 0 xStep: 3 yStep: 2)
				else
					(ego view: 2 loop: 1 cel: 0 xStep: 3 yStep: 2)
				)
				(HandsOn)
				(ego viewer: gEgoViewer)
				(ego view: 2 setCycle: Walk)
				(if (and (== local0 5) (== snakeState 0))
					(snakeActions changeState: 1)
				)
			)
			(10 (Print 78 0))
		)
	)
)

(instance snakeActions of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== snakeState 1) (ego inRect: 170 157 217 170))
			(= snakeState 3)
			(snakeActions changeState: 10)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(theme number: 39 play:)
				(snake setCycle: EndLoop self)
			)
			(2
				(snake setLoop: 1 setCycle: Forward)
				(= snakeState 1)
			)
			(10
				(HandsOff)
				(sounds eachElementDo: #stop 0)
				(theme number: 40 play:)
				(ego viewer: 0)
				(if (< (ego x?) 194)
					(snake cel: 255 setLoop: 2 setCycle: EndLoop self)
				else
					(snake cel: 255 setLoop: 3 setCycle: EndLoop self)
				)
			)
			(11
				(ego
					view: 59
					illegalBits: 0
					ignoreActors:
					cel: 255
					setCycle: EndLoop self
				)
			)
			(12
				(Print 78 28)
				(snake setLoop: 1 setCycle: Forward)
				(= seconds 5)
			)
			(13 (= dead TRUE))
			(20
				(= seconds 0)
				(HandsOff)
				(= currentStatus egoNormal)
				(ego view: 55 setMotion: 0 loop: 0 setCycle: Forward)
				((= newProp_4 (Prop new:))
					view: 888
					setPri: (+ (ego priority?) 1)
					cycleSpeed: 1
					setCycle: Forward
					posn: (+ (ego x?) 10) (- (ego y?) 27)
					init:
				)
				(sounds eachElementDo: #stop 0)
				(charm play: self)
				(snake setLoop: 4 setCycle: Forward)
				(= snakeState 2)
			)
			(21
				(Print 78 29 #at -1 10)
				(ego view: 2 setCycle: Walk)
				(newProp_4 dispose:)
				(= currentStatus egoOnSwampGrass)
				(= seconds 15)
				(HandsOn)
			)
			(22 (self changeState: 2))
		)
	)
)

(instance boardActions of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== state 3)
			(if
				(ego
					inRect: 101 (- (board y?) 3) 150 (+ (board y?) 3)
				)
				(= laidDownBoard TRUE)
			else
				(= laidDownBoard FALSE)
			)
		else
			(= laidDownBoard FALSE)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(ego view: 21 loop: 0 cel: 255 setCycle: EndLoop self)
				(if (not putBoardOverSwamp)
					(= putBoardOverSwamp TRUE)
					(theGame changeScore: 2)
				)
			)
			(2
				(= board (View new:))
				(board
					view: 515
					cel: 0
					loop: 0
					ignoreActors:
					setPri: 12
					posn: 121 (ego y?)
					init:
				)
				((Inventory at: iBoard) moveTo: 78)
				(ego setCycle: BegLoop self)
			)
			(3
				(ego view: 2 setCycle: Walk)
				(HandsOn)
			)
			(10
				(HandsOff)
				(ego view: 21 loop: 0 cel: 255 setCycle: EndLoop self)
			)
			(11
				(board dispose:)
				((Inventory at: iBoard) moveTo: ego)
				(ego setCycle: BegLoop self)
			)
			(12
				(ego view: 2 loop: 1 setCycle: Walk)
				(HandsOn)
			)
		)
	)
)

(instance fruitActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 10)))
			(1
				(fruit cel: 255 setCycle: EndLoop self)
			)
			(2
				(fruit cel: 0)
				(self changeState: 0)
			)
		)
	)
)
