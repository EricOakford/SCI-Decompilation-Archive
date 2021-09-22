;;; Sierra Script 1.0 - (do not remove this comment)
(script# 370)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm370 0
)

(local
	inputNum1
	inputNum2
	inputNum3
	leaveMsg
	local4
	[str 200]
)
(instance rm370 of Room
	(properties
		picture 370
		horizon 54
	)
	
	(method (init)
		(Load VIEW (+ 706 larryBuffed))
		(Load VIEW (+ 702 larryBuffed))
		(Load VIEW (+ 704 larryBuffed))
		(Load VIEW (+ 700 larryBuffed))
		(if (ego has: 8)
			(Load VIEW (+ 703 larryBuffed))
			(Load VIEW 8)
		)
		(super init:)
		(if (> machineSpeed 16)
			(aMan1 init:)
			(aMan2 init:)
			(aMan3 init:)
		)
		(aLocker init:)
		(self setScript: RoomScript)
		(NormalEgo)
		(cond 
			((== prevRoomNum 375)
				(ego loop: 2 posn: 221 58)
			)
			((== prevRoomNum 380)
				(ego loop: 2 posn: 313 62)
			)
			(else
				(= currentStatus egoLEISURESUIT)
				(ego posn: 307 179)
			)
		)
		(ego
			view:
				(switch currentStatus
					(egoNAKED_CENSORED (+ 706 larryBuffed))
					(egoNAKED (+ 702 larryBuffed))
					(egoTOWEL (+ 703 larryBuffed))
					(egoSWEATSUIT (+ 704 larryBuffed))
					(else  (+ 700 larryBuffed))
				)
			init:
		)
	)
	
	(method (newRoom n)
		(if (< (aLocker y?) 999)
			(Bset fLockerRobbed)
		)
		(if
			(and
				(== n 375)
				(== currentStatus egoTOWEL)
				(ego has: iTowel)
			)
			(Print 370 0
				#at 10 -1
				#width 290
			)
			(PutInRoom iTowel 375)
		)
		(super newRoom: n)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(switch currentStatus
			(egoNAKED
				(ego
					observeControl: cLRED cLMAGENTA
					ignoreControl: cYELLOW
				)
			)
			(egoNAKED_CENSORED
				(ego
					observeControl: cLRED cLMAGENTA
					ignoreControl: cYELLOW
				)
			)
			(egoSWEATSUIT
				(ego
					observeControl: cYELLOW cLRED
					ignoreControl: cLMAGENTA
				)
			)
			(egoTOWEL
				(ego
					observeControl: cLMAGENTA cLRED
					ignoreControl: cYELLOW
				)
			)
			(else 
				(ego
					observeControl: cLMAGENTA cYELLOW
					ignoreControl: cLRED
				)
			)
		)
		(if (& (ego onControl:) cLCYAN)
			(ego setPri: 3)
		)
		(if (& (ego onControl:) cLGREEN)
			(ego setPri: -1)
		)
		(cond 
			((& (ego onControl:) cGREEN)
				(curRoom newRoom: 375)
			)
			((& (ego onControl:) cBLUE)
				(if (not leaveMsg)
					(cond 
						((== currentStatus egoLEISURESUIT)
							(= leaveMsg TRUE)
							(Print 370 1)
						)
						((== currentStatus egoSWEATSUIT)
							(= leaveMsg TRUE)
							(Print 370 2)
						)
					)
				)
			)
			((& (ego onControl:) cRED)
				(curRoom newRoom: 380)
			)
			((& (ego onControl:) cCYAN)
				(if (not leaveMsg)
					(cond 
						((or (== currentStatus egoNAKED) (== currentStatus egoNAKED_CENSORED))
							(= leaveMsg TRUE)
							(Print 370 3)
						)
						((== currentStatus egoLEISURESUIT)
							(= leaveMsg TRUE)
							(Print 370 4)
							(if (not larryBuffed)
								(Print 370 5 #at -1 144)
							)
						)
						((== currentStatus egoTOWEL)
							(= leaveMsg TRUE)
							(Print 370 6)
						)
					)
				)
			)
			((== EAST (ego edgeHit?))
				(= currentStatus 0)
				(= currentEgoView (+ 700 larryBuffed))
				(curRoom newRoom: 360)
			)
			((& (ego onControl:) cMAGENTA)
				(if (not leaveMsg)
					(cond 
						((or (== currentStatus egoNAKED) (== currentStatus egoNAKED_CENSORED))
							(= leaveMsg TRUE)
							(Print 370 7)
						)
						((== currentStatus egoSWEATSUIT)
							(= leaveMsg TRUE)
							(Print 370 8)
						)
						((== currentStatus egoTOWEL)
							(= leaveMsg TRUE)
							(Print 370 9)
						)
					)
				)
			)
			(else (= leaveMsg FALSE))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(ego
					illegalBits: 0
					setPri:
					setLoop: 0
					setMotion: MoveTo (ego x?) (+ (ego y?) 20) self
				)
			)
			(2
				(= seconds 2)
			)
			(3
				(ego
					setMotion: MoveTo (ego x?) (- (ego y?) 20)
					view:
						(switch currentStatus
							(egoNAKED_CENSORED (+ 706 larryBuffed))
							(egoNAKED (+ 702 larryBuffed))
							(egoTOWEL (+ 703 larryBuffed))
							(egoSWEATSUIT (+ 704 larryBuffed))
							(else  (+ 700 larryBuffed))
						)
				)
				(= cycles 22)
			)
			(4
				(Print @str)
				(NormalEgo loopE (ego view?))
			)
		)
	)
	
	(method (handleEvent event)
		(asm
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      128
			ne?     
			bt       code_045e
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bnt      code_045f
code_045e:
			ret     
code_045f:
			pushi    1
			lofsa    '/combination'
			push    
			callk    Said,  2
			bt       code_0480
			pushi    1
			lofsa    'affirmative'
			push    
			callk    Said,  2
			bt       code_0480
			pushi    1
			lofsa    'unbolt,use,open/locker,door'
			push    
			callk    Said,  2
			bnt      code_0584
code_0480:
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			bnt      code_0497
			pushi    0
			callb    ItIs,  0
			jmp      code_0e7c
code_0497:
			pushi    2
			pushi    370
			pushi    10
			calle    Print,  4
code_04a1:
			lsl      inputNum1
			ldi      0
			le?     
			bnt      code_04b7
			pushi    1
			lofsa    {First number:}
			push    
			calle    GetNumber,  2
			sal      inputNum1
			jmp      code_04a1
code_04b7:
			lsl      inputNum2
			ldi      0
			le?     
			bnt      code_04cd
			pushi    1
			lofsa    {Second number:}
			push    
			calle    GetNumber,  2
			sal      inputNum2
			jmp      code_04b7
code_04cd:
			lsl      inputNum3
			ldi      0
			le?     
			bnt      code_04e3
			pushi    1
			lofsa    {Third number:}
			push    
			calle    GetNumber,  2
			sal      inputNum3
			jmp      code_04cd
code_04e3:
			pushi    6
			pushi    370
			pushi    11
			lsl      inputNum1
			dup     
			lsl      inputNum2
			lsl      inputNum3
			calle    Printf,  12
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			not     
			bnt      code_0512
			pushi    2
			pushi    370
			pushi    12
			calle    Print,  4
			jmp      code_0575
code_0512:
			lsl      inputNum1
			lag      lockerNum1
			ne?     
			bt       code_052a
			lsl      inputNum2
			lag      lockerNum2
			ne?     
			bt       code_052a
			lsl      inputNum3
			lag      lockerNum3
			ne?     
			bnt      code_0537
code_052a:
			pushi    2
			pushi    370
			pushi    13
			calle    Print,  4
			jmp      code_0575
code_0537:
			pushi    1
			pushi    40
			callb    Btst,  2
			not     
			bnt      code_055e
			pushi    1
			pushi    40
			callb    Bset,  2
			pushi    #changeScore
			pushi    1
			pushi    100
			lag      theGame
			send     6
			pushi    2
			pushi    370
			pushi    14
			calle    Print,  4
			jmp      code_0568
code_055e:
			pushi    2
			pushi    370
			pushi    15
			calle    Print,  4
code_0568:
			pushi    #posn
			pushi    2
			pushi    88
			pushi    65
			lofsa    aLocker
			send     8
code_0575:
			ldi      0
			sal      inputNum1
			ldi      0
			sal      inputNum2
			ldi      0
			sal      inputNum3
			jmp      code_0e7c
code_0584:
			pushi    1
			lofsa    'unknownnumber/'
			push    
			callk    Said,  2
			bt       code_05a5
			pushi    1
			lofsa    '/unknownnumber'
			push    
			callk    Said,  2
			bt       code_05a5
			pushi    1
			lofsa    '//unknownnumber'
			push    
			callk    Said,  2
			bnt      code_05b2
code_05a5:
			pushi    2
			pushi    370
			pushi    16
			calle    Print,  4
			jmp      code_0e7c
code_05b2:
			pushi    1
			lofsa    'bolt,close/locker,door'
			push    
			callk    Said,  2
			bnt      code_0600
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			not     
			bnt      code_05d5
			pushi    0
			callb    ItIs,  0
			jmp      code_0e7c
code_05d5:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			not     
			bnt      code_05ec
			pushi    0
			callb    NotClose,  0
			jmp      code_0e7c
code_05ec:
			pushi    0
			callb    Ok,  0
			pushi    #posn
			pushi    2
			pushi    1111
			dup     
			lofsa    aLocker
			send     8
			jmp      code_0e7c
code_0600:
			pushi    1
			lofsa    'drain,get/art'
			push    
			callk    Said,  2
			bnt      code_062a
			pushi    2
			pushi    370
			pushi    17
			calle    Print,  4
			pushi    5
			pushi    370
			pushi    18
			pushi    67
			pushi    65535
			pushi    144
			calle    Print,  10
			jmp      code_0e7c
code_062a:
			pushi    1
			lofsa    'naked'
			push    
			callk    Said,  2
			bt       code_0661
			pushi    1
			lofsa    'wear/nothing'
			push    
			callk    Said,  2
			bt       code_0661
			pushi    1
			lofsa    'get/naked'
			push    
			callk    Said,  2
			bt       code_0661
			pushi    1
			lofsa    'naked'
			push    
			callk    Said,  2
			bt       code_0661
			pushi    1
			lofsa    '(alter<(out<of),from),(get<off),drain/cloth,towel,sweatpants,cloth'
			push    
			callk    Said,  2
			bnt      code_06e3
code_0661:
			lsg      currentStatus
			ldi      5
			eq?     
			bt       code_0671
			lsg      currentStatus
			ldi      6
			eq?     
			bnt      code_067e
code_0671:
			pushi    2
			pushi    370
			pushi    19
			calle    Print,  4
			jmp      code_0e7c
code_067e:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			not     
			bnt      code_069b
			pushi    2
			pushi    370
			pushi    20
			calle    Print,  4
			jmp      code_0e7c
code_069b:
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			not     
			bnt      code_06b9
			pushi    2
			pushi    370
			pushi    21
			calle    Print,  4
			jmp      code_0e7c
code_06b9:
			pushi    3
			lea      @str
			push    
			pushi    370
			pushi    22
			callk    Format,  6
			lsg      filthLevel
			ldi      3
			lt?     
			bnt      code_06d6
			ldi      6
			sag      currentStatus
			jmp      code_06da
code_06d6:
			ldi      5
			sag      currentStatus
code_06da:
			pushi    #changeState
			pushi    1
			pushi    1
			self     6
			jmp      code_0e7c
code_06e3:
			pushi    1
			lofsa    'dress<get'
			push    
			callk    Said,  2
			bt       code_0704
			pushi    1
			lofsa    'get/dress'
			push    
			callk    Said,  2
			bt       code_0704
			pushi    1
			lofsa    'wear,get,(alter<in,to),(backdrop<on)/cloth[<leisure]'
			push    
			callk    Said,  2
			bnt      code_079b
code_0704:
			lsg      currentStatus
			ldi      7
			eq?     
			bnt      code_0719
			pushi    2
			pushi    370
			pushi    23
			calle    Print,  4
			jmp      code_0e7c
code_0719:
			pushi    1
			pushi    50
			callb    Btst,  2
			bnt      code_072f
			pushi    2
			pushi    370
			pushi    24
			calle    Print,  4
			jmp      code_0e7c
code_072f:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			not     
			bnt      code_074c
			pushi    2
			pushi    370
			pushi    25
			calle    Print,  4
			jmp      code_0e7c
code_074c:
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			not     
			bnt      code_076a
			pushi    2
			pushi    370
			pushi    21
			calle    Print,  4
			jmp      code_0e7c
code_076a:
			pushi    1
			pushi    51
			callb    Btst,  2
			bnt      code_0780
			pushi    2
			pushi    370
			pushi    26
			calle    Print,  4
			jmp      code_0e7c
code_0780:
			pushi    3
			lea      @str
			push    
			pushi    370
			pushi    27
			callk    Format,  6
			ldi      7
			sag      currentStatus
			pushi    #changeState
			pushi    1
			pushi    1
			self     6
			jmp      code_0e7c
code_079b:
			pushi    1
			lofsa    'wear,get,(alter<in),(backdrop<on)/towel'
			push    
			callk    Said,  2
			bnt      code_083c
			lsg      currentStatus
			ldi      8
			eq?     
			bnt      code_07bb
			pushi    2
			pushi    370
			pushi    28
			calle    Print,  4
			jmp      code_0e7c
code_07bb:
			pushi    #has
			pushi    1
			pushi    8
			lag      ego
			send     6
			not     
			bnt      code_07d0
			pushi    0
			callb    DontHave,  0
			jmp      code_0e7c
code_07d0:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			not     
			bnt      code_07ed
			pushi    2
			pushi    370
			pushi    29
			calle    Print,  4
			jmp      code_0e7c
code_07ed:
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			not     
			bnt      code_080b
			pushi    2
			pushi    370
			pushi    21
			calle    Print,  4
			jmp      code_0e7c
code_080b:
			pushi    1
			pushi    51
			callb    Btst,  2
			bnt      code_0821
			pushi    2
			pushi    370
			pushi    26
			calle    Print,  4
			jmp      code_0e7c
code_0821:
			pushi    3
			lea      @str
			push    
			pushi    370
			pushi    30
			callk    Format,  6
			ldi      8
			sag      currentStatus
			pushi    #changeState
			pushi    1
			pushi    1
			self     6
			jmp      code_0e7c
code_083c:
			pushi    1
			lofsa    'wear,get,(alter<in),(backdrop<on)/sweatpants,(cloth<perspiration)'
			push    
			callk    Said,  2
			bnt      code_08f8
			lsg      currentStatus
			ldi      9
			eq?     
			bnt      code_085c
			pushi    2
			pushi    370
			pushi    31
			calle    Print,  4
			jmp      code_0e7c
code_085c:
			pushi    1
			pushi    50
			callb    Btst,  2
			bnt      code_0872
			pushi    2
			pushi    370
			pushi    32
			calle    Print,  4
			jmp      code_0e7c
code_0872:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			not     
			bnt      code_088f
			pushi    2
			pushi    370
			pushi    25
			calle    Print,  4
			jmp      code_0e7c
code_088f:
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			not     
			bnt      code_08ad
			pushi    2
			pushi    370
			pushi    21
			calle    Print,  4
			jmp      code_0e7c
code_08ad:
			pushi    1
			pushi    51
			callb    Btst,  2
			bnt      code_08c3
			pushi    2
			pushi    370
			pushi    26
			calle    Print,  4
			jmp      code_0e7c
code_08c3:
			pushi    3
			lea      @str
			push    
			pushi    370
			pushi    33
			callk    Format,  6
			ldi      9
			sag      currentStatus
			pushi    1
			pushi    41
			callb    Btst,  2
			not     
			bnt      code_08ef
			pushi    1
			pushi    41
			callb    Bset,  2
			pushi    #changeScore
			pushi    1
			pushi    4
			lag      theGame
			send     6
code_08ef:
			pushi    #changeState
			pushi    1
			pushi    1
			self     6
			jmp      code_0e7c
code_08f8:
			pushi    1
			lofsa    '(look<for),find/locker,69'
			push    
			callk    Said,  2
			bt       code_090e
			pushi    1
			lofsa    '(look<for),find//locker,69'
			push    
			callk    Said,  2
			bnt      code_09d2
code_090e:
			pushi    4
			dup     
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			pushi    88
			pushi    65
			callk    GetDistance,  8
			sal      local4
			push    
			ldi      150
			gt?     
			bnt      code_093d
			pushi    2
			pushi    370
			pushi    34
			calle    Print,  4
			jmp      code_0e7c
code_093d:
			lsl      local4
			ldi      80
			gt?     
			bnt      code_0952
			pushi    2
			pushi    370
			pushi    35
			calle    Print,  4
			jmp      code_0e7c
code_0952:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      512
			and     
			bnt      code_096f
			pushi    2
			pushi    370
			pushi    36
			calle    Print,  4
			jmp      code_0e7c
code_096f:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      256
			and     
			bnt      code_098c
			pushi    2
			pushi    370
			pushi    37
			calle    Print,  4
			jmp      code_0e7c
code_098c:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      128
			and     
			bnt      code_09a9
			pushi    2
			pushi    370
			pushi    38
			calle    Print,  4
			jmp      code_0e7c
code_09a9:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			bnt      code_09c5
			pushi    2
			pushi    370
			pushi    39
			calle    Print,  4
			jmp      code_0e7c
code_09c5:
			pushi    2
			pushi    370
			pushi    40
			calle    Print,  4
			jmp      code_0e7c
code_09d2:
			pushi    1
			lofsa    'pick/bolt,locker,69'
			push    
			callk    Said,  2
			bnt      code_09ea
			pushi    2
			pushi    370
			pushi    41
			calle    Print,  4
			jmp      code_0e7c
code_09ea:
			pushi    1
			lofsa    'caress/locker,top,bay'
			push    
			callk    Said,  2
			bnt      code_0a02
			pushi    2
			pushi    370
			pushi    42
			calle    Print,  4
			jmp      code_0e7c
code_0a02:
			pushi    1
			lofsa    '(look<in),explore,(look<in)/locker,(door<locker)'
			push    
			callk    Said,  2
			bnt      code_0ace
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			not     
			bnt      code_0a2a
			pushi    2
			pushi    370
			pushi    43
			calle    Print,  4
			jmp      code_0e7c
code_0a2a:
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			not     
			bnt      code_0a48
			pushi    2
			pushi    370
			pushi    44
			calle    Print,  4
			jmp      code_0e7c
code_0a48:
			pushi    1
			pushi    51
			callb    Btst,  2
			bnt      code_0a72
			pushi    2
			pushi    370
			pushi    45
			calle    Print,  4
			pushi    2
			pushi    370
			pushi    46
			calle    Print,  4
			pushi    2
			pushi    370
			pushi    47
			calle    Print,  4
			jmp      code_0e7c
code_0a72:
			lsg      currentStatus
			ldi      7
			eq?     
			bnt      code_0a87
			pushi    2
			pushi    370
			pushi    48
			calle    Print,  4
			jmp      code_0e7c
code_0a87:
			lsg      currentStatus
			ldi      8
			eq?     
			bnt      code_0a9c
			pushi    2
			pushi    370
			pushi    49
			calle    Print,  4
			jmp      code_0e7c
code_0a9c:
			lsg      currentStatus
			ldi      9
			eq?     
			bnt      code_0ab1
			pushi    2
			pushi    370
			pushi    50
			calle    Print,  4
			jmp      code_0e7c
code_0ab1:
			lsg      currentStatus
			ldi      5
			eq?     
			bt       code_0ac1
			lsg      currentStatus
			ldi      6
			eq?     
			bnt      code_0e7c
code_0ac1:
			pushi    2
			pushi    370
			pushi    49
			calle    Print,  4
			jmp      code_0e7c
code_0ace:
			pushi    1
			lofsa    'get,spray,wear,use/can,spray,deodorant'
			push    
			callk    Said,  2
			bnt      code_0b8c
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			not     
			bnt      code_0af6
			pushi    2
			pushi    370
			pushi    51
			calle    Print,  4
			jmp      code_0e7c
code_0af6:
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			not     
			bnt      code_0b14
			pushi    2
			pushi    370
			pushi    21
			calle    Print,  4
			jmp      code_0e7c
code_0b14:
			lsg      currentStatus
			ldi      5
			eq?     
			bt       code_0b2c
			lsg      currentStatus
			ldi      6
			eq?     
			bt       code_0b2c
			lsg      currentStatus
			ldi      8
			eq?     
			bnt      code_0b6d
code_0b2c:
			pushi    2
			pushi    370
			pushi    52
			calle    Print,  4
			pushi    2
			pushi    370
			pushi    52
			calle    Print,  4
			pushi    1
			pushi    62
			callb    Bclr,  2
			pushi    1
			pushi    60
			callb    Btst,  2
			not     
			bnt      code_0b60
			pushi    1
			pushi    60
			callb    Bset,  2
			pushi    #changeScore
			pushi    1
			pushi    27
			lag      theGame
			send     6
code_0b60:
			pushi    2
			pushi    370
			pushi    53
			calle    Print,  4
			jmp      code_0e7c
code_0b6d:
			pushi    5
			pushi    370
			pushi    54
			pushi    67
			pushi    65535
			pushi    144
			calle    Print,  10
			pushi    #changeScore
			pushi    1
			pushi    65535
			lag      theGame
			send     6
			jmp      code_0e7c
code_0b8c:
			pushi    1
			lofsa    'dry,dry[/body,i]'
			push    
			callk    Said,  2
			bt       code_0bb8
			pushi    1
			lofsa    'caress/self,i'
			push    
			callk    Said,  2
			bt       code_0bb8
			pushi    1
			lofsa    'dry'
			push    
			callk    Said,  2
			bt       code_0bb8
			pushi    1
			lofsa    'use,(dry<with),(dry<off)/towel'
			push    
			callk    Said,  2
			bnt      code_0c69
code_0bb8:
			pushi    #has
			pushi    1
			pushi    8
			lag      ego
			send     6
			not     
			bnt      code_0bd3
			pushi    2
			pushi    370
			pushi    55
			calle    Print,  4
			jmp      code_0e7c
code_0bd3:
			lsg      currentStatus
			ldi      8
			ne?     
			bnt      code_0bf8
			lsg      currentStatus
			ldi      6
			ne?     
			bnt      code_0bf8
			lsg      currentStatus
			ldi      5
			ne?     
			bnt      code_0bf8
			pushi    2
			pushi    370
			pushi    56
			calle    Print,  4
			jmp      code_0e7c
code_0bf8:
			pushi    1
			pushi    50
			callb    Btst,  2
			not     
			bnt      code_0c0f
			pushi    2
			pushi    370
			pushi    57
			calle    Print,  4
			jmp      code_0e7c
code_0c0f:
			lsg      currentStatus
			ldi      8
			ne?     
			bnt      code_0c35
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			not     
			bnt      code_0c35
			pushi    2
			pushi    370
			pushi    58
			calle    Print,  4
			jmp      code_0e7c
code_0c35:
			pushi    1
			pushi    50
			callb    Bclr,  2
			pushi    1
			pushi    39
			callb    Btst,  2
			not     
			bnt      code_0c55
			pushi    1
			pushi    39
			callb    Bset,  2
			pushi    #changeScore
			pushi    1
			pushi    22
			lag      theGame
			send     6
code_0c55:
			pushi    6
			pushi    370
			pushi    59
			pushi    82
			pushi    8
			pushi    0
			pushi    0
			calle    Print,  12
			jmp      code_0e7c
code_0c69:
			pushi    1
			lofsa    'address'
			push    
			callk    Said,  2
			bnt      code_0c81
			pushi    2
			pushi    370
			pushi    60
			calle    Print,  4
			jmp      code_0e7c
code_0c81:
			pushi    1
			lofsa    '/combination'
			push    
			callk    Said,  2
			bt       code_0c97
			pushi    1
			lofsa    'are<where,what/combination,locker'
			push    
			callk    Said,  2
			bnt      code_0ca4
code_0c97:
			pushi    2
			pushi    370
			pushi    61
			calle    Print,  4
			jmp      code_0e7c
code_0ca4:
			pushi    1
			lofsa    'look>'
			push    
			callk    Said,  2
			bnt      code_0e5c
			pushi    1
			lofsa    '/man'
			push    
			callk    Said,  2
			bnt      code_0cc7
			pushi    2
			pushi    370
			pushi    62
			calle    Print,  4
			jmp      code_0e7c
code_0cc7:
			pushi    1
			lofsa    '/door,door'
			push    
			callk    Said,  2
			bnt      code_0d35
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      2
			and     
			bnt      code_0cee
			pushi    2
			pushi    370
			pushi    63
			calle    Print,  4
			jmp      code_0e7c
code_0cee:
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      8
			and     
			bnt      code_0d1e
			pushi    2
			pushi    370
			pushi    64
			calle    Print,  4
			pushi    2
			pushi    370
			pushi    65
			calle    Print,  4
			pushi    2
			pushi    370
			pushi    66
			calle    Print,  4
			jmp      code_0e7c
code_0d1e:
			pushi    2
			pushi    370
			pushi    67
			calle    Print,  4
			pushi    2
			pushi    370
			pushi    68
			calle    Print,  4
			jmp      code_0e7c
code_0d35:
			pushi    #y
			pushi    0
			lofsa    aLocker
			send     4
			push    
			ldi      999
			lt?     
			bnt      code_0d5d
			pushi    1
			lofsa    '/art'
			push    
			callk    Said,  2
			bnt      code_0d5d
			pushi    2
			pushi    370
			pushi    69
			calle    Print,  4
			jmp      code_0e7c
code_0d5d:
			pushi    1
			lofsa    '/number'
			push    
			callk    Said,  2
			bnt      code_0dbd
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      64
			and     
			bt       code_0d87
			pushi    #onControl
			pushi    0
			lag      ego
			send     4
			push    
			ldi      128
			and     
			bnt      code_0d94
code_0d87:
			pushi    2
			pushi    370
			pushi    70
			calle    Print,  4
			jmp      code_0e7c
code_0d94:
			pushi    3
			pushi    370
			pushi    71
			pushi    2
			pushi    1
			pushi    999
			callk    Random,  4
			push    
			calle    Printf,  6
			pushi    5
			pushi    370
			pushi    72
			pushi    67
			pushi    65535
			pushi    144
			calle    Print,  10
			jmp      code_0e7c
code_0dbd:
			pushi    1
			lofsa    '/69,(locker<69)'
			push    
			callk    Said,  2
			bnt      code_0dd5
			pushi    2
			pushi    370
			pushi    73
			calle    Print,  4
			jmp      code_0e7c
code_0dd5:
			pushi    1
			lofsa    '/locker<my'
			push    
			callk    Said,  2
			bnt      code_0ded
			pushi    2
			pushi    370
			pushi    61
			calle    Print,  4
			jmp      code_0e7c
code_0ded:
			pushi    1
			lofsa    '/locker,bay'
			push    
			callk    Said,  2
			bnt      code_0e05
			pushi    2
			pushi    370
			pushi    74
			calle    Print,  4
			jmp      code_0e7c
code_0e05:
			pushi    1
			lofsa    '/sweatpants'
			push    
			callk    Said,  2
			bnt      code_0e2c
			lsg      currentStatus
			ldi      9
			ne?     
			bnt      code_0e1f
			pushi    0
			callb    DontHave,  0
			jmp      code_0e7c
code_0e1f:
			pushi    2
			pushi    370
			pushi    75
			calle    Print,  4
			jmp      code_0e7c
code_0e2c:
			pushi    1
			lofsa    '/deodorant,can,spray'
			push    
			callk    Said,  2
			bnt      code_0e44
			pushi    2
			pushi    370
			pushi    76
			calle    Print,  4
			jmp      code_0e7c
code_0e44:
			pushi    1
			lofsa    '[/area]'
			push    
			callk    Said,  2
			bnt      code_0e7c
			pushi    2
			pushi    370
			pushi    77
			calle    Print,  4
			jmp      code_0e7c
code_0e5c:
			pushi    1
			lofsa    '/69'
			push    
			callk    Said,  2
			bt       code_0e72
			pushi    1
			lofsa    '//69'
			push    
			callk    Said,  2
			bnt      code_0e7c
code_0e72:
			pushi    2
			pushi    370
			pushi    78
			calle    Print,  4
code_0e7c:
			ret     
		)
	)
)

(instance aLocker of View
	(properties
		y 1111
		x 1111
		view 370
		loop 2
	)
)

(instance aMan1 of Actor
	(properties
		view 370
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			illegalBits: 0
			posn: (Random 80 200) 14
			setStep: 1 1
			setCycle: Walk
			setScript: Man1Script
		)
	)
)

(instance Man1Script of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 3 6))
			)
			(1
				(aMan1 setMotion: MoveTo (Random 81 200) 14 self)
			)
			(2
				(= seconds (Random 6 12))
			)
			(3
				(aMan1 setMotion: MoveTo (Random 80 100) 14 self)
				(= state -1)
			)
		)
	)
)

(instance aMan2 of Actor
	(properties
		view 370
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			illegalBits: 0
			posn: (Random -60 1) 8
			setStep: 1 1
			setCycle: Walk
			setScript: Man2Script
		)
	)
)

(instance Man2Script of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 3 6))
			)
			(1
				(aMan2 setMotion: MoveTo (Random 2 40) 8 self)
			)
			(2
				(= seconds (Random 6 12))
			)
			(3
				(aMan2 setMotion: MoveTo (Random -60 1) 8 self)
				(= state -1)
			)
		)
	)
)

(instance aMan3 of Actor
	(properties
		view 370
	)
	
	(method (init)
		(super init:)
		(self
			ignoreHorizon:
			ignoreActors:
			illegalBits: 0
			posn: (Random -60 0) 20
			setStep: 1 1
			setCycle: Walk
			setScript: Man3Script
		)
	)
)

(instance Man3Script of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 3 6))
			)
			(1
				(aMan3 setMotion: MoveTo (Random 2 22) 20 self)
			)
			(2
				(= seconds (Random 6 12))
			)
			(3
				(aMan3 setMotion: MoveTo (Random -60 1) 20 self)
				(= state -1)
			)
		)
	)
)
