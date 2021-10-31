;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6)
(include system.sh)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm6 0
)
(synonyms
	(cop detective)
)

(local
	local0
	numPeopleInRoom
)
(instance James of Feature
	(properties)
	
	(method (handleEvent event)
		(asm
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bt       code_0025
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      128
			ne?     
			bnt      code_0029
code_0025:
			ret     
			jmp      code_01e9
code_0029:
			pushi    #inRect
			pushi    4
			pushi    124
			pushi    128
			pushi    192
			pushi    165
			lag      ego
			send     12
			bt       code_0063
			pushi    #inRect
			pushi    4
			pushi    193
			pushi    145
			pushi    240
			pushi    154
			lag      ego
			send     12
			bnt      code_0063
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			ldi      1
			eq?     
code_0063:
			not     
			bnt      code_007e
			pushi    1
			lofsa    '/james'
			push    
			callk    Said,  2
			not     
			bnt      code_0077
			ret     
			jmp      code_01e9
code_0077:
			pushi    0
			callb    NotClose,  0
			jmp      code_01e9
code_007e:
			pushi    1
			lofsa    'look/desk'
			push    
			callk    Said,  2
			bnt      code_0094
			pushi    2
			pushi    6
			pushi    0
			calle    Print,  4
			jmp      code_01e9
code_0094:
			pushi    1
			lofsa    'look/james,simpson,dude,cop'
			push    
			callk    Said,  2
			bnt      code_00aa
			pushi    2
			pushi    6
			pushi    1
			calle    Print,  4
			jmp      code_01e9
code_00aa:
			pushi    1
			lofsa    'chat/james,simpson,dude,cop'
			push    
			callk    Said,  2
			bt       code_00c0
			pushi    1
			lofsa    'hello'
			push    
			callk    Said,  2
			bnt      code_011c
code_00c0:
			lsg      global172
			ldi      1
			eq?     
			bnt      code_00d3
			pushi    2
			pushi    6
			pushi    2
			calle    Print,  4
			jmp      code_01e9
code_00d3:
			pushi    2
			pushi    0
			pushi    2
			callk    Random,  4
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_00ed
			pushi    2
			pushi    6
			pushi    3
			calle    Print,  4
			jmp      code_0118
code_00ed:
			dup     
			ldi      1
			eq?     
			bnt      code_0100
			pushi    2
			pushi    6
			pushi    4
			calle    Print,  4
			jmp      code_0118
code_0100:
			dup     
			ldi      2
			eq?     
			bnt      code_0118
			pushi    2
			pushi    6
			pushi    5
			calle    Print,  4
			pushi    2
			pushi    6
			dup     
			calle    Print,  4
code_0118:
			toss    
			jmp      code_01e9
code_011c:
			pushi    1
			lofsa    'chat,ask>'
			push    
			callk    Said,  2
			bnt      code_01e9
			lag      global172
			not     
			bnt      code_0141
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    2
			pushi    6
			pushi    7
			calle    Print,  4
			jmp      code_01e9
code_0141:
			pushi    1
			lofsa    '/browning,burglary'
			push    
			callk    Said,  2
			bt       code_0157
			pushi    1
			lofsa    '//browning,burglary'
			push    
			callk    Said,  2
			bnt      code_0175
code_0157:
			pushi    2
			pushi    6
			pushi    8
			calle    Print,  4
			pushi    2
			pushi    6
			pushi    9
			calle    Print,  4
			pushi    2
			pushi    2
			pushi    92
			callb    SolvePuzzle,  4
			+al      local0
			jmp      code_01e9
code_0175:
			pushi    1
			lofsa    '/print,finger,fingerprint,thumb'
			push    
			callk    Said,  2
			bt       code_018b
			pushi    1
			lofsa    '//print,finger,fingerprint,thumb'
			push    
			callk    Said,  2
			bnt      code_01a0
code_018b:
			pushi    2
			pushi    6
			pushi    10
			calle    Print,  4
			pushi    2
			pushi    2
			pushi    93
			callb    SolvePuzzle,  4
			+al      local0
			jmp      code_01e9
code_01a0:
			pushi    1
			lofsa    '/number<serial'
			push    
			callk    Said,  2
			bt       code_01b6
			pushi    1
			lofsa    '//number<serial'
			push    
			callk    Said,  2
			bnt      code_01c2
code_01b6:
			pushi    2
			pushi    6
			pushi    11
			calle    Print,  4
			jmp      code_01e9
code_01c2:
			pushi    1
			lofsa    '/*'
			push    
			callk    Said,  2
			bnt      code_01e9
			lsl      local0
			ldi      6
			ge?     
			bnt      code_01e1
			pushi    2
			pushi    6
			pushi    12
			calle    Print,  4
			jmp      code_01e9
code_01e1:
			pushi    2
			pushi    6
			pushi    2
			calle    Print,  4
code_01e9:
			ret     
		)
	)
)

(instance William of Feature
	(properties)
	
	(method (handleEvent event)
		(asm
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bt       code_022b
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      128
			ne?     
			bnt      code_022f
code_022b:
			ret     
			jmp      code_03aa
code_022f:
			pushi    #inRect
			pushi    4
			pushi    190
			pushi    128
			pushi    239
			pushi    146
			lag      ego
			send     12
			bt       code_026a
			pushi    #inRect
			pushi    4
			pushi    193
			pushi    145
			pushi    240
			pushi    154
			lag      ego
			send     12
			bnt      code_026a
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			ldi      3
			eq?     
code_026a:
			not     
			bnt      code_0299
			pushi    1
			lofsa    '/cole,willie,jerome'
			push    
			callk    Said,  2
			not     
			bnt      code_027e
			ret     
			jmp      code_03aa
code_027e:
			lsl      numPeopleInRoom
			ldi      0
			eq?     
			bnt      code_0292
			pushi    2
			pushi    6
			pushi    13
			calle    Print,  4
			jmp      code_03aa
code_0292:
			pushi    0
			callb    NotClose,  0
			jmp      code_03aa
code_0299:
			pushi    1
			lofsa    'look/desk'
			push    
			callk    Said,  2
			bnt      code_02b0
			pushi    2
			pushi    6
			pushi    14
			calle    Print,  4
			jmp      code_03aa
code_02b0:
			pushi    1
			lofsa    'ask'
			push    
			callk    Said,  2
			bnt      code_02db
			lsl      numPeopleInRoom
			ldi      0
			eq?     
			bnt      code_02cf
			pushi    2
			pushi    6
			pushi    13
			calle    Print,  4
			jmp      code_03aa
code_02cf:
			pushi    2
			pushi    6
			pushi    15
			calle    Print,  4
			jmp      code_03aa
code_02db:
			pushi    1
			lofsa    '/cole,willie,jerome,dude,cop>'
			push    
			callk    Said,  2
			bt       code_02f1
			pushi    1
			lofsa    'hello>'
			push    
			callk    Said,  2
			bnt      code_03aa
code_02f1:
			lsl      numPeopleInRoom
			ldi      0
			eq?     
			bnt      code_030d
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    2
			pushi    6
			pushi    13
			calle    Print,  4
			jmp      code_03aa
code_030d:
			pushi    1
			lofsa    'look'
			push    
			callk    Said,  2
			bnt      code_0324
			pushi    2
			pushi    6
			pushi    16
			calle    Print,  4
			jmp      code_03aa
code_0324:
			pushi    1
			lofsa    'chat'
			push    
			callk    Said,  2
			bt       code_033a
			pushi    1
			lofsa    'hello'
			push    
			callk    Said,  2
			bnt      code_03a2
code_033a:
			pushi    2
			pushi    0
			pushi    4
			callk    Random,  4
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0355
			pushi    2
			pushi    6
			pushi    17
			calle    Print,  4
			jmp      code_039e
code_0355:
			dup     
			ldi      1
			eq?     
			bnt      code_0368
			pushi    2
			pushi    6
			pushi    18
			calle    Print,  4
			jmp      code_039e
code_0368:
			dup     
			ldi      2
			eq?     
			bnt      code_037b
			pushi    2
			pushi    6
			pushi    19
			calle    Print,  4
			jmp      code_039e
code_037b:
			dup     
			ldi      3
			eq?     
			bnt      code_038e
			pushi    2
			pushi    6
			pushi    20
			calle    Print,  4
			jmp      code_039e
code_038e:
			dup     
			ldi      4
			eq?     
			bnt      code_039e
			pushi    2
			pushi    6
			pushi    21
			calle    Print,  4
code_039e:
			toss    
			jmp      code_03aa
code_03a2:
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
code_03aa:
			ret     
		)
	)
)

(instance Robert of Feature
	(properties)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(event claimed?)
					(!= (event type?) saidEvent)
				)
				(return)
			)
			((not (ego inRect: 56 120 120 146))
				(cond 
					((not (Said '/lieutenant,bob,adams'))
						(return)
					)
					((<= numPeopleInRoom 1)
						(Print 6 22)
					)
					(else
						(NotClose)
					)
				)
			)
			((Said 'look/desk')
				(Print 6 23)
			)
			((Said 'ask')
				(if (<= numPeopleInRoom 1)
					(Print 6 22)
				else
					(Print 6 24)
				)
			)
			(
				(or
					(Said '/lieutenant,bob,adams,dude,cop>')
					(Said 'hello>')
				)
				(cond 
					((<= numPeopleInRoom 1)
						(event claimed: 1)
						(Print 6 22)
					)
					((Said 'look')
						(Print 6 25)
					)
					(
						(or
							(Said 'chat')
							(Said 'hello')
						)
						(switch (Random 0 2)
							(0
								(Print 6 26)
							)
							(1
								(Print 6 27)
							)
							(2
								(Print 6 28)
							)
						)
					)
					(else
						(event claimed: 0)
					)
				)
			)
			((Said '/affirmative')
				(Print 6 29)
			)
		)
	)
)

(instance Laura of Feature
	(properties)
	
	(method (handleEvent event)
		(asm
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bt       code_0575
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      128
			ne?     
			bnt      code_0579
code_0575:
			ret     
			jmp      code_0710
code_0579:
			pushi    #inRect
			pushi    4
			pushi    137
			pushi    116
			pushi    300
			pushi    129
			lag      ego
			send     12
			bt       code_05b2
			pushi    #inRect
			pushi    4
			pushi    137
			pushi    116
			pushi    300
			pushi    135
			lag      ego
			send     12
			bnt      code_05b2
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			ldi      3
			eq?     
code_05b2:
			not     
			bnt      code_05e1
			pushi    1
			lofsa    '/holt,broad'
			push    
			callk    Said,  2
			not     
			bnt      code_05c6
			ret     
			jmp      code_0710
code_05c6:
			lsl      numPeopleInRoom
			ldi      2
			gt?     
			bnt      code_05da
			pushi    2
			pushi    6
			pushi    30
			calle    Print,  4
			jmp      code_0710
code_05da:
			pushi    0
			callb    NotClose,  0
			jmp      code_0710
code_05e1:
			pushi    1
			lofsa    'look/desk'
			push    
			callk    Said,  2
			bnt      code_05f8
			pushi    2
			pushi    6
			pushi    31
			calle    Print,  4
			jmp      code_0710
code_05f8:
			pushi    1
			lofsa    'kiss'
			push    
			callk    Said,  2
			bnt      code_060f
			pushi    2
			pushi    6
			pushi    32
			calle    Print,  4
			jmp      code_0710
code_060f:
			pushi    1
			lofsa    'ask'
			push    
			callk    Said,  2
			bnt      code_063a
			lsl      numPeopleInRoom
			ldi      2
			gt?     
			bnt      code_062e
			pushi    2
			pushi    6
			pushi    30
			calle    Print,  4
			jmp      code_0710
code_062e:
			pushi    2
			pushi    6
			pushi    33
			calle    Print,  4
			jmp      code_0710
code_063a:
			pushi    1
			lofsa    '/holt,broad,cop>'
			push    
			callk    Said,  2
			bt       code_0650
			pushi    1
			lofsa    'hello>'
			push    
			callk    Said,  2
			bnt      code_0710
code_0650:
			lsl      numPeopleInRoom
			ldi      2
			gt?     
			bnt      code_066c
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    2
			pushi    6
			pushi    30
			calle    Print,  4
			jmp      code_0710
code_066c:
			pushi    1
			lofsa    'look'
			push    
			callk    Said,  2
			bnt      code_0683
			pushi    2
			pushi    6
			pushi    34
			calle    Print,  4
			jmp      code_0710
code_0683:
			pushi    1
			lofsa    'chat'
			push    
			callk    Said,  2
			bt       code_0699
			pushi    1
			lofsa    'hello'
			push    
			callk    Said,  2
			bnt      code_06da
code_0699:
			pushi    2
			pushi    0
			pushi    2
			callk    Random,  4
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_06b3
			pushi    2
			pushi    6
			pushi    35
			calle    Print,  4
			jmp      code_06d6
code_06b3:
			dup     
			ldi      1
			eq?     
			bnt      code_06c6
			pushi    2
			pushi    6
			pushi    36
			calle    Print,  4
			jmp      code_06d6
code_06c6:
			dup     
			ldi      2
			eq?     
			bnt      code_06d6
			pushi    2
			pushi    6
			pushi    37
			calle    Print,  4
code_06d6:
			toss    
			jmp      code_0710
code_06da:
			pushi    1
			lofsa    'kiss,pinch,fuck'
			push    
			callk    Said,  2
			bnt      code_0710
			pushi    2
			pushi    0
			pushi    1
			callk    Random,  4
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_06ff
			pushi    2
			pushi    6
			pushi    38
			calle    Print,  4
			jmp      code_070f
code_06ff:
			dup     
			ldi      1
			eq?     
			bnt      code_070f
			pushi    2
			pushi    6
			pushi    39
			calle    Print,  4
code_070f:
			toss    
code_0710:
			ret     
		)
	)
)

(instance Computer of Feature
	(properties)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(event claimed?)
					(!= (event type?) saidEvent)
				)
				(return)
			)
			((not (ego inRect: 193 145 240 154))
				(if (Said '/computer')
					(NotClose)
				else
					(return)
				)
			)
			((Said 'look/desk')
				(Print 6 40)
			)
			((Said 'turn<on/computer')
				(Print 6 41)
			)
			((Said 'look,use/computer')
				(curRoom newRoom: 8)
			)
		)
	)
)

(instance rm6 of Room
	(properties
		picture 6
		style WIPEDOWN
	)
	
	(method (init)
		(Load VIEW 1)
		(Load VIEW 68)
		(super init:)
		(self setFeatures: Laura James William Robert Computer)
		(self setLocales: regFieldKit regOffice)
		(HandsOn)
		(= local0 0)
		(= numPeopleInRoom (Random 0 4))
		(= gunFireState gunPROHIBITED)
		(if (!= prevRoomNum 8)
			(User prevDir: 1)
			(ego
				posn: 87 158
				setMotion: MoveTo 87 10
			)
		)
		(ego
			view: 1
			setCycle: Walk
			illegalBits: cWHITE ;-32768
			init:
		)
		(if (<= numPeopleInRoom 2)
			((View new:) ;laura
				view: 68
				posn: 185 125
				loop: 0
				cel: 0
				init:
				addToPic:
			)
		)
		(if (!= numPeopleInRoom 0)
			((View new:) ;William 
				view: 68
				posn: 206 142
				loop: 0
				cel: 1
				init:
				addToPic:
			)
		)
		((View new:) ;James
			view: 68
			posn: 182 148
			loop: 0
			cel: 3
			init:
			addToPic:
		)
		(if (> numPeopleInRoom 1)
			((View new:) ;Adams
				view: 68
				posn: 92 112
				loop: 0
				cel: 2
				init:
				addToPic:
			)
		)
		(self setScript: rm6Script)
	)
	
	(method (dispose)
		(features eachElementDo: #dispose #delete)
		(super dispose:)
	)
)

(instance rm6Script of Script
	(properties)
	
	(method (doit)
		(if (> (ego y?) 160)
			(curRoom newRoom: 2)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							((Said '/flyer,painting')
								(Print 6 42)
							)
							((Said '/wastebasket,garbage')
								(Print 6 43)
							)
							((Said '[<at,around][/(!*,chamber,office)]')
								(Print 6 44)
							)
						)
					)
					((Said 'empty,clean[/newspaper,garbage,basket]')
						(Print 6 45)
					)
				)
			)
		)
	)
)
