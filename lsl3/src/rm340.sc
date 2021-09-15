;;; Sierra Script 1.0 - (do not remove this comment)
(script# 340)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm340 0
)

(local
	[local0 45]
	jokeNum
	theALadyLR_Top
	egoSittingLoop
	comedianOnStage
	[wantToSayStr 30]
	[ethnic1Str 10]
	[ethnic2Str 10]
	[ethnic3Str 10]
	local109
	local110
	local111
)
(instance rm340 of Room
	(properties
		picture 340
		horizon 1
		south 240
	)
	
	(method (init)
		(Load TEXT 341)
		(Load VIEW 705)
		(Load VIEW 344)
		(LoadMany SOUND 21 22 23 24 25 26 27 10 340 341)
		(= currentStatus egoINCOMEDYCLUB)
		(super init:)
		(addToPics
			add: atpChair
			add: atpManUR
			add: atpManLR
			add: atpManUL_Bottom
			add: atpLadyLR_Bottom
			add: atpLadyUL_Bottom
			doit:
		)
		(aManUL_Top init:)
		(aLadyUL_Top init:)
		(aLadyLR_Top init:)
		(aDrummer init:)
		(aComic init:)
		(aSign init:)
		(if (and (InRoom iWineBottle) (ego has: iPenthouseKey))
			(aBottle setPri: 10 ignoreActors: init:)
		)
		(self setScript: RoomScript)
		(NormalEgo 3 (+ 705 larryBuffed))
		(ego posn: 159 188 init:)
		(if (Btst fAlAndBill)
			(aAl loop: 5 cel: 4 init: stopUpd:)
			(aBill loop: 4 cel: 4 init: stopUpd:)
		else
			(aAlTop init:)
			(aBillTop init:)
			(aAl init:)
			(aBill init:)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if comedianOnStage (Print 340 0))
		(= currentStatus egoNORMAL)
		(super newRoom: newRoomNumber)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(Ok)
				(HandsOff)
				(if (< (ego x?) 165)
					(ego illegalBits: 0 setMotion: MoveTo 153 145 self)
					(= egoSittingLoop 4)
				else
					(ego illegalBits: 0 setMotion: MoveTo 176 145 self)
					(= egoSittingLoop 5)
				)
			)
			(2
				(= cycles (= seconds 0))
				(ego
					view: 705
					loop: egoSittingLoop
					cel: 0
					illegalBits: 0
					setCycle: EndLoop self
				)
			)
			(3
				(if (== (ComicScript state?) 0)
					(ComicScript changeState: 1)
				)
				(User canInput: TRUE)
				(= currentStatus egoSITTING)
			)
			(4 (ego setCycle: BegLoop self))
			(5
				(= currentStatus 16)
				(NormalEgo 3 (+ 705 larryBuffed))
				(if comedianOnStage (Print 340 39))
			)
			(6
				(HandsOff)
				(= wantToSayStr 0)
				(while (> 5 (StrLen @wantToSayStr))
					(GetInput @wantToSayStr 50
						{Just say what you want to say:}
						#title {Author Interface}
					)
				)
				(aAlTop loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(7
				(aAlTop loop: 3 setCycle: Forward)
				(= seconds 3)
			)
			(8
				(Printf 340 40 @wantToSayStr)
				(aAlTop loop: 2 setCel: 255 setCycle: BegLoop self)
			)
			(9
				(aBillTop show:)
				(= seconds 3)
			)
			(10
				(Printf 340 41 @wantToSayStr)
				(= seconds 3)
			)
			(11
				(aBillTop hide:)
				(aAlTop setCycle: EndLoop self)
			)
			(12
				(aAlTop loop: 3 setCycle: Forward)
				(= seconds 2)
			)
			(13
				(Printf 340 42 @wantToSayStr)
				(aAl dispose:)
				(aBill dispose:)
				(aAlTop
					posn: 99 191
					loop: 5
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(aBillTop
					posn: 70 190
					loop: 4
					cel: 0
					cycleSpeed: 1
					show:
					setCycle: EndLoop self
				)
			)
			(14
				(HandsOn)
				(aAlTop stopUpd:)
				(aBillTop stopUpd:)
				(if (not (Btst fAlAndBill))
					(Bset fAlAndBill)
					(theGame changeScore: 5)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'boo')
				(if comedianOnStage
					(Print 340 1)
					(Printf 340 2 expletive)
					(Print 340 3 #at -1 144)
				else
					(Print 340 4)
				)
			)
			((Said 'applaud') (if comedianOnStage (Print 340 5) else (Print 340 4)))
			((and debugging (Said 'test/joke'))
				(= jokeNum
					(GetNumber {First joke (from 1 to LAST JOKE):})
				)
				(Printf 340 6 jokeNum)
			)
			((Said 'address/comedian') (if comedianOnStage (Print 340 7) else (Print 340 8)))
			((Said 'address/bob') (Print 340 9))
			(
				(or
					(Said 'get/microphone')
					(Said 'nightstand,get,jump,climb<on/backstage')
				)
				(Print 340 10)
			)
			((Said 'address/man,bill,babe,al')
				(if
					(or
						(& (ego onControl:) cCYAN)
						(& (ego onControl:) cGREEN)
					)
					(if (Btst fAlAndBill)
						(Print 340 11)
					else
						(self changeState: 6)
					)
				else
					(Printf 340 12 currentEgo)
				)
			)
			((Said 'get/bottle,beer')
				(cond 
					(
					(and (!= currentStatus 16) (!= currentStatus egoSITTING)) (GoodIdea))
					(
					(or (not (InRoom iWineBottle)) (not (ego has: iPenthouseKey))) (Print 340 11))
					((not (& (ego onControl:) cLGREY)) (NotClose))
					(else
						(Ok)
						(aBottle dispose:)
						(theGame changeScore: 15)
						(Print 340 13)
						(ego get: iWineBottle)
					)
				)
			)
			(
				(or
					(Said 'nightstand,(get<off),(get<up),(nightstand<up)')
					(Said 'exit/barstool')
				)
				(cond 
					((== currentStatus egoINCOMEDYCLUB) (YouAre))
					((!= currentStatus egoSITTING) (GoodIdea))
					(else (self changeState: 4))
				)
			)
			((Said 'lie')
				(cond 
					((not (& (ego onControl:) cBLUE)) (Print 340 14))
					((== currentStatus egoSITTING) (YouAre))
					((!= currentStatus egoINCOMEDYCLUB) (GoodIdea))
					(else (self changeState: 1))
				)
			)
			((Said 'look>')
				(cond 
					((Said '/barstool') (Print 340 15))
					((Said '/backstage') (Print 340 16))
					((Said '/mask') (Print 340 17))
					((Said '/awning') (Print 340 18))
					((Said '/cigarette,smoke') (Print 340 19))
					((Said '/burn,burn') (Print 340 20))
					(
						(or
							(Said 'buy/beer,drink,beer')
							(Said 'buy//beer,drink,beer')
							(Said '/attendant,attendant,attendant')
							(Said '//attendant,attendant,attendant')
						)
						(Print 340 21)
					)
					((Said '/door') (Print 340 22))
					((Said '/comedian') (if comedianOnStage (Print 340 23) else (Print 340 24)))
					((Said '/babe') (Print 340 25))
					((Said '/man,couple')
						(if
							(and
								(not (Btst fAlAndBill))
								(or
									(& (ego onControl:) cCYAN)
									(& (ego onControl:) cGREEN)
								)
							)
							(Print 340 26)
						else
							(Print 340 27)
						)
					)
					((Said '/bottle')
						(if (and (InRoom iWineBottle) (ego has: iPenthouseKey))
							(Print 340 28)
						else
							(Print 340 11)
						)
					)
					((Said '/al')
						(cond 
							((Btst fAlAndBill) (Print 340 29) (Print 340 30 #at -1 144))
							((& (ego onControl:) cCYAN) (Print 340 31))
							(else (Print 340 32))
						)
					)
					((Said '/bill')
						(cond 
							((Btst fAlAndBill) (event claimed: FALSE))
							((& (ego onControl:) cGREEN) (Print 340 33))
							(else (Print 340 34))
						)
					)
					((Said '/buffet')
						(if
							(and
								(& (ego onControl:) $0080)
								(InRoom iWineBottle)
								(ego has: iPenthouseKey)
							)
							(Print 340 28)
						else
							(Print 340 35)
						)
					)
					((Said '/bob') (if comedianOnStage (Print 340 36) else (Print 340 37)))
					((Said '[/area,couple]') (Print 340 38))
				)
			)
		)
	)
)

(instance ComicScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== -1 (music prevSignal?)) (== state 7))
			(self cue:)
		)
	)
	
	(method (changeState newState &tmp [temp0 200] [temp200 4] temp204 temp205 temp206 temp207 temp208)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0a5f
			jmp      code_1083
code_0a5f:
			dup     
			ldi      1
			eq?     
			bnt      code_0aa0
			lsg      machineSpeed
			ldi      39
			gt?     
			bnt      code_0a79
			pushi    #setCycle
			pushi    1
			class    Forward
			push    
			lofsa    aSign
			send     6
code_0a79:
			pushi    #number
			pushi    1
			pushi    10
			pushi    6
			pushi    1
			pushi    65535
			pushi    42
			pushi    0
			lag      music
			send     16
			pushi    #loop
			pushi    1
			pushi    2
			pushi    126
			pushi    1
			class    Forward
			push    
			lofsa    aDrummer
			send     12
			ldi      4
			aTop     seconds
			jmp      code_1083
code_0aa0:
			dup     
			ldi      2
			eq?     
			bnt      code_0aee
			pushi    2
			pushi    340
			pushi    43
			calle    Print,  4
			lsg      currentStatus
			ldi      1004
			ne?     
			bnt      code_0ac4
			pushi    2
			pushi    340
			pushi    44
			calle    Print,  4
code_0ac4:
			pushi    #stop
			pushi    0
			pushi    43
			pushi    1
			pushi    340
			pushi    6
			pushi    1
			pushi    1
			pushi    42
			pushi    0
			lag      music
			send     20
			pushi    #cycleSpeed
			pushi    1
			pushi    1
			pushi    6
			pushi    1
			pushi    1
			lofsa    aDrummer
			send     12
			ldi      3
			aTop     seconds
			jmp      code_1083
code_0aee:
			dup     
			ldi      3
			eq?     
			bnt      code_0b0b
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			pushi    160
			pushi    58
			pushSelf
			lofsa    aComic
			send     12
			jmp      code_1083
code_0b0b:
			dup     
			ldi      4
			eq?     
			bnt      code_0b2d
			pushi    #setPri
			pushi    1
			pushi    65535
			pushi    210
			pushi    4
			class    MoveTo
			push    
			pushi    160
			pushi    78
			pushSelf
			lofsa    aComic
			send     18
			jmp      code_1083
code_0b2d:
			dup     
			ldi      5
			eq?     
			bnt      code_0b55
			lsg      machineSpeed
			ldi      39
			gt?     
			bnt      code_0b4a
			pushi    #setCel
			pushi    1
			pushi    1
			pushi    197
			pushi    0
			lofsa    aSign
			send     10
code_0b4a:
			ldi      1
			sal      comedianOnStage
			ldi      3
			aTop     seconds
			jmp      code_1083
code_0b55:
			dup     
			ldi      6
			eq?     
			bnt      code_0b75
			pushi    #loop
			pushi    1
			pushi    4
			pushi    208
			pushi    1
			lofsa    talkCycler
			push    
			lofsa    aComic
			send     12
			ldi      3
			aTop     seconds
			jmp      code_1083
code_0b75:
			dup     
			ldi      7
			eq?     
			bnt      code_0b7f
			jmp      code_1083
code_0b7f:
			dup     
			ldi      8
			eq?     
			bnt      code_0baa
			pushi    #cycleSpeed
			pushi    1
			pushi    0
			pushi    6
			pushi    1
			pushi    0
			pushi    201
			pushi    1
			pushi    0
			lofsa    aDrummer
			send     18
			pushi    2
			pushi    340
			pushi    45
			calle    Print,  4
			ldi      3
			aTop     seconds
			jmp      code_1083
code_0baa:
			dup     
			ldi      9
			eq?     
			bnt      code_0c60
			pushi    #number
			pushi    1
			pushi    341
			pushi    6
			pushi    1
			pushi    65535
			pushi    42
			pushi    0
			lag      music
			send     16
			lsg      currentStatus
			ldi      1004
			ne?     
			bnt      code_0bd6
			pushi    2
			pushi    340
			pushi    46
			calle    Print,  4
code_0bd6:
			pushi    2
			pushi    340
			pushi    47
			calle    Print,  4
			ldi      0
			sal      ethnic1Str
			ldi      0
			sal      ethnic2Str
			ldi      0
			sal      ethnic3Str
code_0bec:
			pushi    3
			pushi    1
			lea      @ethnic1Str
			push    
			callk    StrLen,  2
			ugt?    
			bnt      code_0c0d
			pushi    3
			lea      @ethnic1Str
			push    
			pushi    15
			lofsa    {Ethnic group #1:}
			push    
			calle    GetInput,  6
			jmp      code_0bec
code_0c0d:
			pushi    3
			pushi    1
			lea      @ethnic2Str
			push    
			callk    StrLen,  2
			ugt?    
			bnt      code_0c2e
			pushi    3
			lea      @ethnic2Str
			push    
			pushi    15
			lofsa    {Ethnic group #2:}
			push    
			calle    GetInput,  6
			jmp      code_0c0d
code_0c2e:
			pushi    3
			pushi    1
			lea      @ethnic3Str
			push    
			callk    StrLen,  2
			ugt?    
			bnt      code_0c4f
			pushi    3
			lea      @ethnic3Str
			push    
			pushi    15
			lofsa    {Ethnic group #3:}
			push    
			calle    GetInput,  6
			jmp      code_0c2e
code_0c4f:
			pushi    2
			pushi    340
			pushi    48
			calle    Print,  4
			ldi      3
			aTop     seconds
			jmp      code_1083
code_0c60:
			dup     
			ldi      10
			eq?     
			bnt      code_0c80
			pushi    #loop
			pushi    1
			pushi    4
			pushi    208
			pushi    1
			lofsa    talkCycler
			push    
			lofsa    aComic
			send     12
			ldi      3
			aTop     seconds
			jmp      code_1083
code_0c80:
			dup     
			ldi      11
			eq?     
			bnt      code_0e5c
			pushi    #setCycle
			pushi    1
			class    Walk
			push    
			pushi    208
			pushi    1
			pushi    0
			lofsa    aComic
			send     12
			ldi      0
			sat      temp204
code_0c9b:
			+at      temp204
			pushi    2
			pushi    0
			pushi    42
			callk    Random,  4
			sat      temp206
			lal      jokeNum
			bnt      code_0cb2
			+al      jokeNum
			sat      temp206
			pushi    0
			sali     local0
code_0cb2:
			lat      temp206
			lsli     local0
			ldi      0
			eq?     
			bnt      code_0e42
			pushi    1
			lat      temp206
			sali     local0
			pushi    2
			pushi    49
			pushi    51
			callk    Random,  4
			sal      local109
			sal      local110
			lal      local109
			sal      local111
code_0cd1:
			lsl      local109
			lal      local110
			eq?     
			bnt      code_0ce6
			pushi    2
			pushi    49
			pushi    51
			callk    Random,  4
			sal      local110
			jmp      code_0cd1
code_0ce6:
			lsl      local111
			lal      local109
			eq?     
			bt       code_0cf6
			lsl      local111
			lal      local110
			eq?     
			bnt      code_0d03
code_0cf6:
			pushi    2
			pushi    49
			pushi    51
			callk    Random,  4
			sal      local111
			jmp      code_0ce6
code_0d03:
			ldi      0
			sat      temp207
code_0d07:
			lst      temp207
			ldi      5
			lt?     
			bnt      code_0de8
			ldi      0
			sat      temp208
			pushi    5
			lea      @temp0
			push    
			pushi    340
			pushi    49
			pushi    341
			lst      temp207
			lst      temp206
			ldi      5
			mul     
			add     
			push    
			callk    Format,  10
			pushi    32
			pushi    2
			lea      @temp0
			push    
			pushi    1
			callk    StrAt,  4
			ne?     
			bnt      code_0de3
			ldi      0
			sat      temp205
code_0d40:
			lst      temp205
			pushi    1
			lea      @temp0
			push    
			callk    StrLen,  2
			lt?     
			bnt      code_0dc5
			pushi    47
			pushi    2
			lea      @temp0
			push    
			lst      temp205
			callk    StrAt,  4
			eq?     
			bnt      code_0dc0
			pushi    3
			lea      @temp0
			push    
			lst      temp205
			pushi    37
			callk    StrAt,  6
			pushi    2
			lea      @temp0
			push    
			+at      temp205
			push    
			callk    StrAt,  4
			push    
			dup     
			lal      local109
			eq?     
			bnt      code_0d8b
			lea      @ethnic1Str
			push    
			lat      temp208
			sati     temp200
			+at      temp208
			jmp      code_0db0
code_0d8b:
			dup     
			lal      local110
			eq?     
			bnt      code_0d9f
			lea      @ethnic2Str
			push    
			lat      temp208
			sati     temp200
			+at      temp208
			jmp      code_0db0
code_0d9f:
			dup     
			lal      local111
			eq?     
			bnt      code_0db0
			lea      @ethnic3Str
			push    
			lat      temp208
			sati     temp200
			+at      temp208
code_0db0:
			toss    
			pushi    3
			lea      @temp0
			push    
			lst      temp205
			pushi    115
			callk    StrAt,  6
			+at      temp205
code_0dc0:
			+at      temp205
			jmp      code_0d40
code_0dc5:
			pushi    6
			lea      @temp0
			push    
			ldi      0
			lsti     temp200
			ldi      1
			lsti     temp200
			ldi      2
			lsti     temp200
			ldi      3
			lsti     temp200
			ldi      4
			lsti     temp200
			calle    Printf,  12
code_0de3:
			+at      temp207
			jmp      code_0d07
code_0de8:
			lsl      jokeNum
			ldi      42
			gt?     
			bnt      code_0e10
			lal      jokeNum
			bnt      code_0e06
			pushi    2
			pushi    340
			pushi    50
			calle    Print,  4
			ldi      0
			sal      jokeNum
			jmp      code_0e10
code_0e06:
			pushi    2
			pushi    340
			pushi    51
			calle    Print,  4
code_0e10:
			pushi    #setCycle
			pushi    1
			class    EndLoop
			push    
			lofsa    aDrummer
			send     6
			pushi    1
			pushi    11
			callk    DoSound,  2
			push    
			ldi      5
			gt?     
			bnt      code_0e55
			pushi    #number
			pushi    1
			pushi    2
			pushi    21
			pushi    27
			callk    Random,  4
			push    
			pushi    6
			pushi    1
			pushi    1
			pushi    42
			pushi    0
			lag      soundFX
			send     16
			jmp      code_0e55
code_0e42:
			lst      temp204
			ldi      1000
			ge?     
			bnt      code_0c9b
			ldi      12
			aTop     state
			jmp      code_0e55
			jmp      code_0c9b
code_0e55:
			ldi      2
			aTop     seconds
			jmp      code_1083
code_0e5c:
			dup     
			ldi      12
			eq?     
			bnt      code_0e8b
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			pushi    2
			pushi    125
			pushi    195
			callk    Random,  4
			push    
			pushi    2
			pushi    66
			pushi    80
			callk    Random,  4
			push    
			pushSelf
			lofsa    aComic
			send     12
			ldi      9
			aTop     state
			jmp      code_1083
code_0e8b:
			dup     
			ldi      13
			eq?     
			bnt      code_0eba
			pushi    #fade
			pushi    0
			lag      music
			send     4
			pushi    2
			pushi    340
			pushi    52
			calle    Print,  4
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			pushi    158
			pushi    75
			pushSelf
			lofsa    aComic
			send     12
			jmp      code_1083
code_0eba:
			dup     
			ldi      14
			eq?     
			bnt      code_0eec
			pushi    2
			pushi    340
			pushi    53
			calle    Print,  4
			pushi    1
			pushi    43
			callb    Btst,  2
			not     
			bnt      code_0ee5
			pushi    1
			pushi    43
			callb    Bset,  2
			pushi    #changeScore
			pushi    1
			pushi    100
			lag      theGame
			send     6
code_0ee5:
			ldi      2
			aTop     seconds
			jmp      code_1083
code_0eec:
			dup     
			ldi      15
			eq?     
			bnt      code_0f15
			pushi    #view
			pushi    1
			pushi    344
			pushi    6
			pushi    1
			pushi    2
			pushi    7
			pushi    1
			pushi    0
			pushi    126
			pushi    2
			class    EndLoop
			push    
			pushSelf
			pushi    210
			pushi    1
			pushi    0
			lofsa    aComic
			send     32
			jmp      code_1083
code_0f15:
			dup     
			ldi      16
			eq?     
			bnt      code_0f37
			pushi    #setCycle
			pushi    1
			class    Walk
			push    
			pushi    210
			pushi    4
			class    MoveTo
			push    
			pushi    125
			pushi    75
			pushSelf
			lofsa    aComic
			send     18
			jmp      code_1083
code_0f37:
			dup     
			ldi      17
			eq?     
			bnt      code_0f54
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			pushi    194
			pushi    75
			pushSelf
			lofsa    aComic
			send     12
			jmp      code_1083
code_0f54:
			dup     
			ldi      18
			eq?     
			bnt      code_0f71
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			pushi    161
			pushi    75
			pushSelf
			lofsa    aComic
			send     12
			jmp      code_1083
code_0f71:
			dup     
			ldi      19
			eq?     
			bnt      code_0f92
			pushi    #loop
			pushi    1
			pushi    2
			pushi    201
			pushi    1
			pushi    255
			pushi    126
			pushi    2
			class    BegLoop
			push    
			pushSelf
			lofsa    aComic
			send     20
			jmp      code_1083
code_0f92:
			dup     
			ldi      20
			eq?     
			bnt      code_0fb5
			pushi    #view
			pushi    1
			pushi    343
			pushi    6
			pushi    1
			pushi    2
			pushi    126
			pushi    1
			class    Walk
			push    
			lofsa    aComic
			send     18
			ldi      2
			aTop     seconds
			jmp      code_1083
code_0fb5:
			dup     
			ldi      21
			eq?     
			bnt      code_0ff2
			pushi    2
			pushi    340
			pushi    54
			calle    Print,  4
			pushi    #number
			pushi    1
			pushi    340
			pushi    6
			pushi    1
			pushi    65535
			pushi    42
			pushi    0
			lag      music
			send     16
			lsg      machineSpeed
			ldi      39
			gt?     
			bnt      code_0feb
			pushi    #setCycle
			pushi    1
			class    Forward
			push    
			lofsa    aSign
			send     6
code_0feb:
			ldi      2
			aTop     seconds
			jmp      code_1083
code_0ff2:
			dup     
			ldi      22
			eq?     
			bnt      code_100f
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			pushi    160
			pushi    57
			pushSelf
			lofsa    aComic
			send     12
			jmp      code_1083
code_100f:
			dup     
			ldi      23
			eq?     
			bnt      code_1042
			pushi    2
			pushi    340
			pushi    55
			calle    Print,  4
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			pushi    241
			pushi    58
			pushSelf
			lofsa    aComic
			send     12
			pushi    #fade
			pushi    0
			lag      music
			send     4
			ldi      0
			sal      comedianOnStage
			jmp      code_1083
code_1042:
			dup     
			ldi      24
			eq?     
			bnt      code_1083
			pushi    #hide
			pushi    0
			lofsa    aComic
			send     4
			lsg      machineSpeed
			ldi      39
			gt?     
			bnt      code_1068
			pushi    #setCel
			pushi    1
			pushi    0
			pushi    197
			pushi    0
			lofsa    aSign
			send     10
code_1068:
			pushi    #stopUpd
			pushi    0
			lofsa    aDrummer
			send     4
			pushi    #number
			pushi    1
			pushi    341
			pushi    6
			pushi    1
			lsg      musicLoop
			pushi    42
			pushi    0
			lag      music
			send     16
code_1083:
			toss    
			ret     
		)
	)
)

(instance drinkerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 10)))
			(1
				(switch (Random 1 3)
					(1
						(= theALadyLR_Top aLadyLR_Top)
					)
					(2
						(= theALadyLR_Top aLadyUL_Top)
					)
					(3
						(= theALadyLR_Top aManUL_Top)
					)
				)
				(theALadyLR_Top setCycle: EndLoop self)
			)
			(2
				(if (== theALadyLR_Top aLadyUL_Top) (= state -1))
				(= cycles (Random 5 22))
			)
			(3
				(theALadyLR_Top setCycle: BegLoop self)
				(= state -1)
			)
		)
	)
)

(instance atpManUR of PicView
	(properties
		y 186
		x 247
		view 340
		cel 9
	)
)

(instance atpManLR of PicView
	(properties
		y 128
		x 249
		view 340
		cel 8
	)
)

(instance atpChair of PicView
	(properties
		y 145
		x 165
		view 340
		priority 11
	)
)

(instance aDrummer of Prop
	(properties
		y 71
		x 79
		view 345
	)
	
	(method (init)
		(super init:)
		(self stopUpd:)
	)
)

(instance aBottle of View
	(properties
		y 119
		x 165
		view 340
		cel 5
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd:)
	)
)

(instance aBillTop of Prop
	(properties
		y 155
		x 67
		view 346
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward setPri: 14 ignoreActors: hide:)
	)
)

(instance aBill of View
	(properties
		y 190
		x 70
		view 346
		priority 14
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd:)
	)
)

(instance aAlTop of Prop
	(properties
		y 148
		x 102
		view 346
		loop 3
	)
	
	(method (init)
		(super init:)
		(self setPri: 14 ignoreActors: stopUpd:)
	)
)

(instance aAl of View
	(properties
		y 191
		x 99
		view 346
		cel 1
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd:)
	)
)

(instance aLadyUL_Top of Prop
	(properties
		y 104
		x 45
		view 340
		loop 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 9 stopUpd:)
	)
)

(instance atpLadyUL_Bottom of PicView
	(properties
		y 133
		x 52
		view 340
		cel 2
	)
)

(instance aLadyLR_Top of Prop
	(properties
		y 156
		x 291
		view 340
		loop 4
	)
	
	(method (init)
		(super init:)
		(self setPri: 14 setScript: drinkerScript stopUpd:)
	)
)

(instance atpLadyLR_Bottom of PicView
	(properties
		y 185
		x 283
		view 340
		cel 4
	)
)

(instance aManUL_Top of Prop
	(properties
		y 104
		x 281
		view 340
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 9 stopUpd:)
	)
)

(instance atpManUL_Bottom of PicView
	(properties
		y 132
		x 275
		view 340
		cel 1
	)
)

(instance talkCycler of Code
	(properties)
	
	(method (doit)
		(if (Random 0 3) (aComic cel: (Random 0 2)))
	)
)

(instance aComic of Actor
	(properties
		y 58
		x 241
		view 343
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setScript: ComicScript setCycle: Walk stopUpd:)
	)
)

(instance aSign of Prop
	(properties
		y 50
		x 223
		view 340
		loop 5
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 3 stopUpd:)
	)
)
