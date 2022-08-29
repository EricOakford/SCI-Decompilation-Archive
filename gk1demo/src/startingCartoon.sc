;;; Sierra Script 1.0 - (do not remove this comment)
(script# 211)
(include game.sh) (include "210.shm")
(use Main)
(use Procs)
(use GKEgo)
(use Print)
(use Osc)
(use RandCyc)
(use PolyPath)
(use ForCount)
(use StopWalk)
(use Motion)
(use Actor)
(use System)

(public
	proc211_0 0
	startingCartoon 1
	startOfDay2 2
	startOfDay3 3
	startOfDay4 4
	startOfDay5 5
	startOfDay6 6
	hangUpCoat 7
)

(local
	local0
)
(procedure (proc211_0)
	;this did not decompile correctly, but doesn't seem to used in the demo anyway
	(asm
		;+ali     local30720
		callk    ScriptID,  4
		send     18
		jmp      code_086c
		dup     
		ldi      19
		eq?     
		bnt      code_0452
		pushi    #cel
		pushi    1
		pushi    0
		pushi    161
		pushi    1
		pushi    0
		pushi    2
		pushi    210
		pushi    1
		callk    ScriptID,  4
		send     12
		pushi    #say
		pushi    4
		pushi    1
		pushi    0
		pushi    1
		pushi    6
		lag      messager
		send     12
		pushi    #cel
		pushi    1
		pushi    0
		pushi    161
		pushi    3
		class    Oscillate
		push    
		pushi    1
		pushSelf
		lag      ego
		send     16
		jmp      code_086c
code_0452:
		dup     
		ldi      20
		eq?     
		bnt      code_0483
		pushi    #say
		pushi    5
		pushi    1
		pushi    0
		pushi    1
		pushi    7
		pushSelf
		lag      messager
		send     14
		pushi    #loop
		pushi    1
		pushi    4
		dup     
		pushi    1
		pushi    0
		pushi    161
		pushi    1
		class    RandCycle
		push    
		pushi    2
		pushi    210
		pushi    1
		callk    ScriptID,  4
		send     18
		jmp      code_086c
code_0483:
		dup     
		ldi      21
		eq?     
		bnt      code_04bf
		pushi    #cel
		pushi    1
		pushi    0
		pushi    161
		pushi    1
		pushi    0
		pushi    2
		pushi    210
		pushi    1
		callk    ScriptID,  4
		send     12
		pushi    #say
		pushi    4
		pushi    1
		pushi    0
		pushi    1
		pushi    8
		lag      messager
		send     12
		pushi    #cel
		pushi    1
		pushi    0
		pushi    161
		pushi    3
		class    Oscillate
		push    
		pushi    1
		pushSelf
		lag      ego
		send     16
		jmp      code_086c
code_04bf:
		dup     
		ldi      22
		eq?     
		bnt      code_04f0
		pushi    #say
		pushi    5
		pushi    1
		pushi    0
		pushi    1
		pushi    9
		pushSelf
		lag      messager
		send     14
		pushi    #loop
		pushi    1
		pushi    4
		dup     
		pushi    1
		pushi    0
		pushi    161
		pushi    1
		class    RandCycle
		push    
		pushi    2
		pushi    210
		pushi    1
		callk    ScriptID,  4
		send     18
		jmp      code_086c
code_04f0:
		dup     
		ldi      23
		eq?     
		bnt      code_0534
		pushi    #cel
		pushi    1
		pushi    0
		pushi    161
		pushi    1
		pushi    0
		pushi    2
		pushi    210
		pushi    1
		callk    ScriptID,  4
		send     12
		pushi    #say
		pushi    5
		pushi    1
		pushi    0
		pushi    1
		pushi    10
		pushSelf
		lag      messager
		send     14
		pushi    #view
		pushi    1
		pushi    215
		pushi    3
		pushi    1
		pushi    6
		pushi    4
		pushi    1
		pushi    0
		pushi    161
		pushi    1
		class    Forward
		push    
		lag      ego
		send     24
		jmp      code_086c
code_0534:
		dup     
		ldi      24
		eq?     
		bnt      code_056f
		pushi    #setCycle
		pushi    1
		pushi    0
		lag      ego
		send     6
		pushi    #say
		pushi    5
		pushi    1
		pushi    0
		pushi    1
		pushi    11
		pushSelf
		lag      messager
		send     14
		pushi    #loop
		pushi    1
		pushi    3
		pushi    4
		pushi    1
		pushi    0
		pushi    161
		pushi    1
		class    RandCycle
		push    
		pushi    2
		pushi    210
		pushi    1
		callk    ScriptID,  4
		send     18
		jmp      code_086c
code_056f:
		dup     
		ldi      25
		eq?     
		bnt      code_05b2
		pushi    #cel
		pushi    1
		pushi    0
		pushi    161
		pushi    1
		pushi    0
		pushi    2
		pushi    210
		pushi    1
		callk    ScriptID,  4
		send     12
		pushi    #say
		pushi    4
		pushi    1
		pushi    0
		pushi    35
		pushi    1
		lag      messager
		send     12
		pushi    #view
		pushi    1
		pushi    2151
		pushi    3
		pushi    1
		pushi    1
		pushi    4
		pushi    1
		pushi    0
		pushi    161
		pushi    2
		class    EndLoop
		push    
		pushSelf
		lag      ego
		send     26
		jmp      code_086c
code_05b2:
		dup     
		ldi      26
		eq?     
		bnt      code_05dd
		pushi    #loop
		pushi    1
		pushi    2
		pushi    4
		pushi    1
		pushi    0
		pushi    63
		pushi    1
		pushi    11
		lag      ego
		send     18
		pushi    #init
		pushi    0
		pushi    2
		pushi    210
		pushi    2
		callk    ScriptID,  4
		send     4
		ldi      1
		aTop     26
		; WARNING: Can't determine property name for index 26
		jmp      code_086c
code_05dd:
		dup     
		ldi      27
		eq?     
		bnt      code_0603
		pushi    #loop
		pushi    1
		pushi    2
		pushi    4
		pushi    1
		pushi    1
		pushi    63
		pushi    1
		pushi    65535
		pushi    161
		pushi    4
		class    CycleTo
		push    
		pushi    3
		pushi    1
		pushSelf
		lag      ego
		send     30
		jmp      code_086c
code_0603:
		dup     
		ldi      28
		eq?     
		bnt      code_0669
		pushi    #view
		pushi    1
		pushi    901
		pushi    153
		pushi    2
		pushi    123
		pushi    139
		pushi    63
		pushi    1
		pushi    65535
		pushi    324
		pushi    1
		pushi    6
		pushi    161
		pushi    2
		class    StopWalk
		push    
		pushi    65535
		pushi    155
		pushi    1
		pushi    0
		pushi    298
		pushi    4
		class    MoveTo
		push    
		pushi    187
		pushi    125
		pushSelf
		lag      ego
		send     52
		pushi    #view
		pushi    1
		pushi    213
		pushi    3
		pushi    1
		pushi    0
		pushi    4
		pushi    1
		pushi    0
		pushi    254
		pushi    1
		pushi    10
		pushi    161
		pushi    1
		class    EndLoop
		push    
		pushi    2
		pushi    210
		pushi    1
		callk    ScriptID,  4
		send     30
		jmp      code_086c
code_0669:
		dup     
		ldi      29
		eq?     
		bnt      code_067d
		pushi    #hide
		pushi    0
		lag      ego
		send     4
		ldi      1
		aTop     26
		; WARNING: Can't determine property name for index 26
		jmp      code_086c
code_067d:
		dup     
		ldi      30
		eq?     
		bnt      code_06a5
		pushi    #posn
		pushi    2
		pushi    222
		pushi    118
		pushi    3
		pushi    1
		pushi    1
		pushi    161
		pushi    2
		class    EndLoop
		push    
		pushSelf
		pushi    2
		pushi    210
		pushi    1
		callk    ScriptID,  4
		send     22
		jmp      code_086c
code_06a5:
		dup     
		ldi      31
		eq?     
		bnt      code_06d9
		pushi    #posn
		pushi    2
		pushi    251
		pushi    115
		pushi    4
		pushi    1
		pushi    0
		pushi    3
		pushi    1
		pushi    2
		pushi    161
		pushi    2
		class    EndLoop
		push    
		pushSelf
		pushi    2
		pushi    210
		pushi    1
		callk    ScriptID,  4
		send     28
		pushi    #show
		pushi    0
		lag      ego
		send     4
		jmp      code_086c
code_06d9:
		dup     
		ldi      32
		eq?     
		bnt      code_070f
		pushi    #say
		pushi    5
		pushi    1
		pushi    0
		pushi    35
		pushi    2
		pushSelf
		lag      messager
		send     14
		pushi    #view
		pushi    1
		pushi    213
		pushi    3
		pushi    1
		pushi    2
		pushi    4
		pushi    1
		pushi    0
		pushi    161
		pushi    1
		class    EndLoop
		push    
		pushi    2
		pushi    210
		pushi    1
		callk    ScriptID,  4
		send     24
		jmp      code_086c
code_070f:
		dup     
		ldi      33
		eq?     
		bnt      code_0728
		pushi    #say
		pushi    5
		pushi    1
		pushi    0
		pushi    35
		pushi    3
		pushSelf
		lag      messager
		send     14
		jmp      code_086c
code_0728:
		dup     
		ldi      34
		eq?     
		bnt      code_075f
		pushi    #say
		pushi    5
		pushi    1
		pushi    0
		pushi    35
		pushi    4
		pushSelf
		lag      messager
		send     14
		pushi    #view
		pushi    1
		pushi    213
		pushi    3
		pushi    1
		pushi    2
		pushi    4
		pushi    1
		pushi    0
		pushi    161
		pushi    1
		class    RandCycle
		push    
		pushi    2
		pushi    210
		pushi    1
		callk    ScriptID,  4
		send     24
		jmp      code_086c
code_075f:
		dup     
		ldi      35
		eq?     
		bnt      code_078b
		pushi    #cel
		pushi    1
		pushi    0
		pushi    161
		pushi    1
		pushi    0
		pushi    2
		pushi    210
		pushi    1
		callk    ScriptID,  4
		send     12
		pushi    #say
		pushi    5
		pushi    1
		pushi    0
		pushi    35
		pushi    5
		pushSelf
		lag      messager
		send     14
		jmp      code_086c
code_078b:
		dup     
		ldi      36
		eq?     
		bnt      code_07c6
		pushi    #say
		pushi    5
		pushi    1
		pushi    0
		pushi    35
		pushi    6
		pushSelf
		lag      messager
		send     14
		pushi    #view
		pushi    1
		pushi    213
		pushi    3
		pushi    1
		pushi    3
		pushi    4
		pushi    1
		pushi    0
		pushi    161
		pushi    3
		class    Oscillate
		push    
		pushi    1
		pushSelf
		pushi    2
		pushi    210
		pushi    1
		callk    ScriptID,  4
		send     28
		jmp      code_086c
code_07c6:
		dup     
		ldi      37
		eq?     
		bnt      code_07cf
		jmp      code_086c
code_07cf:
		dup     
		ldi      38
		eq?     
		bnt      code_07e8
		pushi    #say
		pushi    5
		pushi    1
		pushi    0
		pushi    35
		pushi    7
		pushSelf
		lag      messager
		send     14
		jmp      code_086c
code_07e8:
		dup     
		ldi      39
		eq?     
		bnt      code_081e
		pushi    #say
		pushi    5
		pushi    1
		pushi    0
		pushi    35
		pushi    8
		pushSelf
		lag      messager
		send     14
		pushi    #view
		pushi    1
		pushi    213
		pushi    3
		pushi    1
		pushi    2
		pushi    4
		pushi    1
		pushi    0
		pushi    161
		pushi    1
		class    RandCycle
		push    
		pushi    2
		pushi    210
		pushi    1
		callk    ScriptID,  4
		send     24
		jmp      code_086c
code_081e:
		dup     
		ldi      40
		eq?     
		bnt      code_086c
		pushi    #handsOn
		pushi    0
		lag      theGame
		send     4
		pushi    #normalize
		pushi    2
		pushi    0
		pushi    901
		class    GKEgo
		send     8
		pushi    #ignoreActors
		pushi    1
		pushi    1
		pushi    317
		pushi    1
		pushi    32768
		lag      ego
		send     12
		pushi    #setScript
		pushi    1
		pushi    2
		pushi    210
		pushi    5
		callk    ScriptID,  4
		push    
		pushi    2
		pushi    210
		pushi    1
		callk    ScriptID,  4
		send     6
		pushi    #dispose
		pushi    0
		self     4
		pushi    1
		pushi    211
		callk    DisposeScript,  2
code_086c:
		toss    
		ret     
	)
)

(instance startingCartoon of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset fGraceAtBookstore)
				(ego
					posn: 291 123
					view: 212
					loop: 0
					setSpeed: 11
					setCycle: CycleTo 2 1 self
				)
				((ScriptID 210 1)
					init:
					loop: 0
					cel: 0
					cycleSpeed: 10
					setCycle: ForwardCounter 5
				)
				((ScriptID 210 4) init: (ScriptID 210 1))
			)
			(1
				(ego view: 212 loop: 0 cel: 3 setCycle: EndLoop self)
				(theSound1 number: 215 play:)
			)
			(2
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_1 1)
				(ego posn: 271 151 loop: 1 setCycle: EndLoop self)
			)
			(3
				((ScriptID 210 1) loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(4
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_1 2)
				(ego loop: 2 setSpeed: 11 setCycle: EndLoop self)
			)
			(5
				(ego loop: 3 setSpeed: 10 setCycle: ForwardCounter 2 self)
			)
			(6
				((ScriptID 210 1) loop: 1 cel: 4 setCycle: BegLoop self)
			)
			(7
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_1 3)
				(ego
					posn: 254 153
					loop: 4
					cel: 0
					setSpeed: 10
					setCycle: EndLoop self
				)
				((ScriptID 210 1)
					loop: 0
					cel: 0
					setCycle: ForwardCounter 6
				)
			)
			(8
				(ego
					view: 901
					posn: 257 152
					setPri: 9
					setSpeed: 6
					setCycle: StopWalk -1
					setMotion: MoveTo 137 138 self
				)
			)
			(9
				(ego
					view: 215
					posn: 81 139
					loop: 0
					cel: 0
					setSpeed: 10
					setCycle: CycleTo 2 1 self
				)
			)
			(10
				((ScriptID 210 2) dispose:)
				(= cycles 1)
			)
			(11
				(ego view: 215 loop: 0 cel: 3 setCycle: EndLoop self)
			)
			(12
				(ego view: 215 loop: 1 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(13
				((ScriptID 210 3) hide:)
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_1 4)
				(ego view: 215 loop: 1 cel: 5 setCycle: EndLoop self)
				(theSound1 number: 214 play:)
			)
			(14
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(15
				((ScriptID 210 3) show:)
				(ego
					posn: 81 141
					setPri: 7
					loop: 5
					cel: 0
					setCycle: EndLoop self
				)
			)
			(16
				(ego
					view: 2151
					posn: 150 123
					loop: 0
					cel: 0
					setCycle: Oscillate 1 self
				)
			)
			(17
				(if modelessDialog
					(modelessDialog dispose:)
				)
				((ScriptID 210 1) loop: 2 cel: 4 setCycle: EndLoop self)
			)
			(18
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_1 5 self)
				((ScriptID 210 1) loop: 3 cel: 0 setCycle: RandCycle)
			)
			(19
				((ScriptID 210 1) cel: 0 setCycle: 0)
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_1 6)
				(ego cel: 0 setCycle: Oscillate 1 self)
			)
			(20
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_1 7 self)
				((ScriptID 210 1) loop: 4 cel: 0 setCycle: RandCycle)
			)
			(21
				((ScriptID 210 1) cel: 0 setCycle: 0)
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_1 8)
				(ego cel: 0 setCycle: Oscillate 1 self)
			)
			(22
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_1 9 self)
				((ScriptID 210 1) loop: 4 cel: 0 setCycle: RandCycle)
			)
			(23
				((ScriptID 210 1) cel: 0 setCycle: 0)
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_1 10 self)
				(ego view: 215 loop: 6 cel: 0 setCycle: Forward)
			)
			(24
				(ego setCycle: 0)
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_1 11 self)
				((ScriptID 210 1) loop: 3 cel: 0 setCycle: RandCycle)
			)
			(25
				((ScriptID 210 1) cel: 0 setCycle: 0)
				(messager say: N_ROOM_EVENTS NULL C_GET_RECORDER 1)
				(ego view: 2151 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(26
				(ego loop: 2 cel: 0 setPri: 11)
				((ScriptID 210 2) init:)
				(= cycles 1)
			)
			(27
				(ego loop: 2 cel: 1 setPri: -1 setCycle: CycleTo 3 1 self)
			)
			(28
				(ego
					view: 901
					posn: 123 139
					setPri: -1
					setSpeed: 6
					setCycle: StopWalk -1
					setLoop: 0
					setMotion: MoveTo 187 125 self
				)
				((ScriptID 210 1)
					view: 213
					loop: 0
					cel: 0
					cycleSpeed: 10
					setCycle: EndLoop
				)
			)
			(29
				(ego hide:)
				(= cycles 1)
			)
			(30
				((ScriptID 210 1)
					posn: 222 118
					loop: 1
					setCycle: EndLoop self
				)
			)
			(31
				((ScriptID 210 1)
					posn: 251 115
					cel: 0
					loop: 2
					setCycle: EndLoop self
				)
				(ego show:)
			)
			(32
				(messager say: N_ROOM_EVENTS NULL C_GET_RECORDER 2 self)
				((ScriptID 210 1) view: 213 loop: 2 cel: 0 setCycle: EndLoop)
			)
			(33
				(messager say: N_ROOM_EVENTS NULL C_GET_RECORDER 3 self)
			)
			(34
				(messager say: N_ROOM_EVENTS NULL C_GET_RECORDER 4 self)
				((ScriptID 210 1)
					view: 213
					loop: 2
					cel: 0
					setCycle: RandCycle
				)
			)
			(35
				((ScriptID 210 1) cel: 0 setCycle: 0)
				(messager say: N_ROOM_EVENTS NULL C_GET_RECORDER 5 self)
			)
			(36
				(messager say: N_ROOM_EVENTS NULL C_GET_RECORDER 6 self)
				((ScriptID 210 1)
					view: 213
					loop: 3
					cel: 0
					setCycle: Oscillate 1 self
				)
			)
			(37)
			(38
				(messager say: N_ROOM_EVENTS NULL C_GET_RECORDER 7 self)
			)
			(39
				(messager say: N_ROOM_EVENTS NULL C_GET_RECORDER 8 self)
				((ScriptID 210 1)
					view: 213
					loop: 2
					cel: 0
					setCycle: RandCycle
				)
			)
			(40
				(theGame handsOn:)
				(GKEgo normalize: 0 901)
				(ego ignoreActors: TRUE ignoreControl: cWHITE)
				((ScriptID 210 1) setScript: (ScriptID 210 5))
				(self dispose:)
				(DisposeScript 211)
			)
		)
	)
)

(instance startOfDay2 of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fGraceAtBookstore)
				(theGame handsOff:)
				((ScriptID 210 1)
					init:
					view: 208
					loop: 1
					cel: 0
					cycleSpeed: 8
					signal: skipCheck
					setCycle: EndLoop
				)
				(ego
					posn: 291 123
					view: 212
					loop: 0
					setSpeed: 11
					setCycle: EndLoop self
				)
				((ScriptID 210 4) init: (ScriptID 210 1))
			)
			(1
				((ScriptID 210 1) loop: 0 cel: 4 setCycle: BegLoop self)
			)
			(2
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_2 1 self)
				((ScriptID 210 1)
					view: 211
					loop: 3
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3)
			(4
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_2 2)
				(ego posn: 271 151 loop: 1 setCycle: EndLoop self)
			)
			(5
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_2 3)
				(ego
					view: 901
					setPri: 9
					setSpeed: 6
					setCycle: StopWalk -1
					setMotion: MoveTo 137 138 self
				)
			)
			(6
				(ego
					view: 215
					posn: 81 139
					loop: 0
					cel: 0
					setSpeed: 10
					setCycle: CycleTo 2 1 self
				)
			)
			(7
				((ScriptID 210 2) dispose:)
				(= cycles 1)
			)
			(8
				(ego view: 215 loop: 0 cel: 3 setCycle: EndLoop self)
			)
			(9
				(ego view: 215 loop: 1 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(10
				((ScriptID 210 3) hide:)
				(ego loop: 1 cel: 5 setCycle: EndLoop self)
			)
			(11
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(12
				((ScriptID 210 3) show:)
				(ego posn: 81 141 loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(13
				(ego
					view: 2151
					posn: 150 123
					loop: 0
					cel: 0
					setCycle: Oscillate 1 self
				)
				((ScriptID 210 1) view: 211 loop: 3 cel: 0 setCycle: EndLoop)
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_2 4 self)
			)
			(14)
			(15
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_2 5 7 self)
			)
			(16
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_2 8)
				(ego
					view: 2151
					posn: 150 123
					loop: 0
					cel: 0
					setCycle: Oscillate 1 self
				)
			)
			(17
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_2 9 12 self)
			)
			(18
				(ego loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(19
				(ego loop: 2 cel: 0 setPri: 11)
				((ScriptID 210 2) init:)
				(= cycles 1)
			)
			(20
				((ScriptID 210 1) setScript: (ScriptID 210 5))
				(ego loop: 2 cel: 1 setPri: -1 setCycle: CycleTo 3 1 self)
			)
			(21
				(Bset 24)
				(GKEgo normalize: 0 901)
				(ego
					posn: 127 137
					ignoreActors: TRUE
					ignoreControl: cWHITE
					setMotion: MoveTo 131 137
				)
				(theGame handsOn:)
				(self dispose:)
				(DisposeScript 211)
			)
		)
	)
)

(instance startOfDay3 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fGraceAtBookstore)
				(theGame handsOff:)
				((ScriptID 210 1)
					init:
					view: 208
					loop: 1
					cel: 0
					cycleSpeed: 8
					signal: skipCheck
					setCycle: EndLoop
				)
				(ego
					posn: 291 123
					view: 212
					loop: 0
					setSpeed: 11
					setCycle: EndLoop self
				)
				((ScriptID 210 4) init: (ScriptID 210 1))
			)
			(1
				((ScriptID 210 1) loop: 0 cel: 4 setCycle: BegLoop self)
			)
			(2
				((ScriptID 210 1)
					view: 211
					loop: 3
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_3 1 self)
			)
			(4
				(ego posn: 271 151 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(5
				(GKEgo normalize: 0 901)
				(self cue:)
			)
			(6
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_3 2 3 self)
			)
			(7
				(ego
					view: 901
					setCycle: StopWalk -1
					setMotion: MoveTo 187 125 self
				)
			)
			(8
				(Face ego (ScriptID 210 1) self)
			)
			(9
				((ScriptID 210 1)
					view: 2131
					loop: 4
					cel: 0
					setCycle: CycleTo 3 1 self
				)
				(ego view: 2131 loop: 1 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(10
				((ScriptID 210 1)
					view: 2131
					loop: 4
					cel: 4
					setCycle: EndLoop
				)
				(ego view: 2131 loop: 1 cel: 4 setCycle: EndLoop self)
			)
			(11
				(GKEgo normalize: 0 901)
				(= cycles 1)
			)
			(12
				(ego get: iPhonePage)
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_3 4 self)
			)
			(13
				(ego
					setPri: 9
					setSpeed: 6
					setLoop: egoLooper
					setCycle: StopWalk -1
					setMotion: MoveTo 137 138 self
				)
			)
			(14
				(ego
					view: 215
					posn: 81 139
					loop: 0
					cel: 0
					setSpeed: 10
					setCycle: EndLoop self
				)
			)
			(15
				(ego view: 215 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(16
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(17
				(ego posn: 81 141 loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(18
				(ego
					view: 2151
					posn: 150 123
					loop: 0
					cel: 0
					setCycle: Oscillate 1 self
				)
				((ScriptID 210 1) view: 211 loop: 3 cel: 0 setCycle: EndLoop)
			)
			(19
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_3 5 8 self)
			)
			(20
				(ego
					view: 2151
					posn: 150 123
					loop: 0
					cel: 0
					setCycle: Oscillate 1 self
				)
			)
			(21
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_3 9 12 self)
			)
			(22
				((ScriptID 210 1)
					view: 211
					loop: 3
					cel: 0
					setCycle: EndLoop self
				)
			)
			(23
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_3 13 16 self)
			)
			(24
				(ego loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(25
				((ScriptID 210 1) setScript: (ScriptID 210 5))
				(ego loop: 2 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(26
				(ego posn: 127 137)
				(GKEgo normalize: 0 901)
				(ego ignoreActors: TRUE ignoreControl: cWHITE)
				(theGame handsOn:)
				(self dispose:)
				(DisposeScript 211)
			)
		)
	)
)

(instance startOfDay4 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fGraceAtBookstore)
				(theGame handsOff:)
				((ScriptID 210 1) init: setScript: (ScriptID 210 5))
				(ego
					posn: 291 123
					view: 212
					loop: 0
					setSpeed: 11
					setCycle: EndLoop self
				)
				((ScriptID 210 4) init: (ScriptID 210 1))
			)
			(1
				(ego posn: 271 151 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(2 (= seconds 3))
			(3
				(ego
					view: 901
					setPri: 9
					setSpeed: 6
					setCycle: StopWalk -1
					setMotion: MoveTo 137 138 self
				)
			)
			(4
				(ego
					view: 215
					posn: 81 139
					loop: 0
					cel: 0
					setSpeed: 10
					setCycle: CycleTo 2 1 self
				)
			)
			(5
				((ScriptID 210 2) dispose:)
				(ego view: 215 loop: 0 cel: 3 setCycle: EndLoop self)
			)
			(6
				(ego view: 215 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(7
				((ScriptID 210 3) hide:)
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(8
				((ScriptID 210 3) show:)
				(ego loop: 3 cel: 0 setCycle: Oscillate 1 self)
			)
			(9 (= seconds 3))
			(10
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_4 0 self)
			)
			(11
				(if (Btst fGaveGracePattern)
					(messager say: N_ROOM_EVENTS NULL C_GAVE_PATTERN 1 2 self)
				else
					(messager say: N_ROOM_EVENTS NULL C_GRACE_DONE_DAY_4 0 self)
				)
			)
			(12
				(if (Btst fGaveGracePattern)
					((ScriptID 210 5) dispose:)
					((ScriptID 210 1)
						view: 213
						loop: 0
						cel: 3
						setCycle: EndLoop self
					)
				else
					(theGame handsOn:)
					(GKEgo normalize: 0 901)
					(ego ignoreActors: TRUE ignoreControl: cWHITE)
					((ScriptID 210 1) setScript: (ScriptID 210 5))
					(DisposeScript 211)
					(self dispose:)
				)
			)
			(13
				(ego
					view: 901
					setSpeed: 6
					setCycle: StopWalk -1
					setMotion: MoveTo 187 125 self
				)
			)
			(14
				(ego hide:)
				((ScriptID 210 1)
					posn: 222 118
					loop: 1
					setCycle: EndLoop self
				)
			)
			(15
				((ScriptID 210 1)
					posn: 251 115
					cel: 0
					loop: 2
					setCycle: EndLoop self
				)
				(ego show: get: iNews1810)
			)
			(16
				(messager say: N_ROOM_EVENTS NULL C_GAVE_PATTERN 3 5 self)
			)
			(17
				(messager say: N_ROOM_EVENTS NULL C_GRACE_DONE_DAY_4 0 self)
			)
			(18
				((ScriptID 210 1) setScript: (ScriptID 210 5))
				(GKEgo normalize: 0 901)
				(ego ignoreActors: TRUE ignoreControl: cWHITE)
				(theGame handsOn:)
				(self dispose:)
				(DisposeScript 211)
			)
		)
	)
)

(instance startOfDay5 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fGraceAtBookstore)
				(theGame handsOff:)
				(ego setScript: hangUpCoat self)
				((ScriptID 210 1) init: setScript: (ScriptID 210 5))
				((ScriptID 210 4) init: (ScriptID 210 1))
				(package init:)
			)
			(1
				((ScriptID 210 6) init:)
				((ScriptID 210 5) dispose:)
				((ScriptID 210 1) loop: 0 cel: 4 setCycle: BegLoop self)
			)
			(2
				(ego
					view: 901
					setPri: 9
					setSpeed: 6
					setCycle: StopWalk -1
					setMotion: MoveTo 137 138 self
				)
			)
			(3
				(ego
					view: 215
					posn: 81 139
					loop: 0
					cel: 0
					setSpeed: 10
					setCycle: CycleTo 2 1 self
				)
			)
			(4
				((ScriptID 210 2) dispose:)
				(ego view: 215 loop: 0 cel: 3 setCycle: EndLoop self)
			)
			(5
				(ego view: 215 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(6
				((ScriptID 210 3) hide:)
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(7 (= seconds 3))
			(8
				(ego view: 215 loop: 3 setCycle: Oscillate 1 self)
			)
			(9
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_5 1 3 self)
			)
			(10
				(ego view: 215 loop: 3 setCycle: Oscillate 1 self)
			)
			(11
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_5 4 self)
			)
			(12
				(if (Btst fGaveGracePattern)
					(messager say: N_ROOM_EVENTS NULL C_GAVE_PATTERN 1 2 self)
				else
					(self changeState: 19)
				)
			)
			(13
				((ScriptID 210 1)
					view: 213
					loop: 0
					cel: 3
					setCycle: EndLoop self
				)
			)
			(14
				(ego
					view: 901
					setSpeed: 6
					setCycle: StopWalk -1
					setMotion: MoveTo 187 125 self
				)
			)
			(15
				(ego hide:)
				((ScriptID 210 1)
					posn: 222 118
					loop: 1
					setCycle: EndLoop self
				)
			)
			(16
				((ScriptID 210 1)
					posn: 251 115
					cel: 0
					loop: 2
					setCycle: EndLoop self
				)
				(ego show: get: iNews1810)
			)
			(17
				(messager say: N_ROOM_EVENTS NULL C_GAVE_PATTERN 3 5 self)
			)
			(18
				(if (Btst fGaveGracePattern)
					(self cue:)
				else
					(ego view: 215 loop: 3 setCycle: Oscillate 1 self)
				)
			)
			(19
				(messager say: N_ROOM_EVENTS NULL C_GET_PACKAGE 1 self)
			)
			(20
				(if (Btst fGaveGracePattern)
					(self changeState: 23)
				else
					(self cue:)
				)
			)
			(21
				(ego
					view: 901
					setSpeed: 6
					setCycle: StopWalk -1
					setMotion: MoveTo 109 127 self
				)
			)
			(22
				(ego
					view: 901
					setCycle: StopWalk -1
					setMotion: MoveTo 187 125 self
				)
			)
			(23
				(messager say: N_ROOM_EVENTS NULL C_GET_PACKAGE 2 self)
			)
			(24
				((ScriptID 210 1)
					view: 213
					loop: 0
					cel: 0
					setCycle: CycleTo 2 1 self
				)
			)
			(25
				(package dispose:)
				((ScriptID 210 1)
					view: 213
					loop: 0
					cel: 3
					setCycle: EndLoop self
				)
			)
			(26
				(ego hide:)
				(= cycles 1)
			)
			(27
				((ScriptID 210 1)
					posn: 222 118
					loop: 1
					setCycle: EndLoop self
				)
			)
			(28
				((ScriptID 210 1)
					posn: 251 115
					cel: 0
					loop: 2
					setCycle: EndLoop self
				)
				(ego show:)
			)
			(29
				(messager say: N_ROOM_EVENTS NULL C_GET_PACKAGE 3 self)
			)
			(30
				((ScriptID 210 1)
					view: 2131
					loop: 4
					cel: 0
					setCycle: CycleTo 3 1 self
				)
				(ego view: 2131 loop: 1 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(31
				((ScriptID 210 1)
					view: 2131
					loop: 4
					cel: 4
					setCycle: EndLoop
				)
				(ego view: 2131 loop: 1 cel: 4 setCycle: EndLoop self)
			)
			(32
				(ego get: iGuntJournal iRitLetter)
				(GKEgo normalize: 0 901)
				(self cue:)
			)
			(33
				(messager say: N_ROOM_EVENTS NULL C_GET_PACKAGE 4 7 self)
			)
			(34
				(Prints {Ring})
				(self cue:)
			)
			(35
				(messager say: N_ROOM_EVENTS NULL C_GET_PACKAGE 8 self)
			)
			(36
				(ego
					ignoreActors: TRUE
					ignoreControl: cWHITE
					setMotion: PolyPath 277 106 self
				)
			)
			(37
				(ego view: 2191 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(38
				(messager say: N_ROOM_EVENTS NULL C_GET_PACKAGE 9 self)
			)
			(39
				(ego loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(40
				(messager say: N_ROOM_EVENTS NULL C_GET_PACKAGE 10 self)
			)
			(41
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(42
				(messager say: N_ROOM_EVENTS NULL C_GET_PACKAGE 11 self)
			)
			(43
				(messager say: N_ROOM_EVENTS NULL C_GET_PACKAGE 12 self)
			)
			(44
				(messager say: N_ROOM_EVENTS NULL C_GET_PACKAGE 13 self)
			)
			(45
				(messager say: N_ROOM_EVENTS NULL C_GET_PACKAGE 14 self)
			)
			(46
				(messager say: N_ROOM_EVENTS NULL C_GET_PACKAGE 15 16 self)
			)
			(47
				(ego loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(48
				(GKEgo normalize: 1 901)
				(= seconds 2)
			)
			(49
				(messager say: N_ROOM_EVENTS NULL C_GET_PACKAGE 17 20 self)
			)
			(50
				(theGame handsOn:)
				(ego ignoreActors: TRUE ignoreControl: cWHITE)
				((ScriptID 210 1) setScript: (ScriptID 210 5))
				(self dispose:)
				(DisposeScript 211)
			)
		)
	)
)

(instance hangUpCoat of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 210 8) dispose:)
				((ScriptID 210 6) dispose:)
				(ego
					posn: 101 93
					view: 219
					setLoop: 2
					cel: 0
					setCycle: EndLoop self
				)
				(theSound1 number: 373 play:)
			)
			(1
				((ScriptID 210 8) init:)
				(theSound1 number: 374 play:)
				(ego
					posn: 88 97
					loop: 3
					setPri: 8
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				((ScriptID 210 6) init:)
				(GKEgo normalize: 0 901)
				(ego setMotion: MoveTo 98 97 self)
			)
			(3
				(if (or (== prevRoomNum 370) (== prevRoomNum 200))
					(messager say: N_ROOM_EVENTS NULL C_GABRIEL_BACK (Random 1 5) self)
				else
					(self cue:)
				)
			)
			(4
				(if (or (== prevRoomNum 370) (== prevRoomNum 200))
					(messager say: N_ROOM_EVENTS NULL C_GRACE_BACK (Random 1 5) self)
				else
					(self cue:)
				)
			)
			(5
				(GKEgo normalize: 0 901)
				(ego ignoreActors: TRUE ignoreControl: cWHITE)
				(theGame handsOn:)
				(self dispose:)
				(DisposeScript 211)
			)
		)
	)
)

(instance startOfDay6 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fGraceAtBookstore)
				((ScriptID 210 7)
					init:
					view: 2101
					posn: 101 99
					loop: 1
					cel: 0
					setCycle: EndLoop self
				)
				(chicken init:)
			)
			(1
				(= seconds 2)
			)
			(2
				(Prints {SCREAM})
				(self cue:)
			)
			(3
				(ego
					view: 2101
					posn: 292 109
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_6 1 self)
				(GKEgo normalize: 0 901)
			)
			(5
				(ego setMotion: PolyPath 175 124 self)
			)
			(6
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_6 2 3 self)
			)
			(7
				(Prints {Gab is looking around})
				(self cue:)
			)
			(8
				(messager say: N_ROOM_EVENTS NULL C_START_DAY_6 4 12 self)
			)
			(9
				(Prints {Fade to black and then come back})
				(GKEgo normalize: 0 901)
				(ego ignoreActors: TRUE ignoreControl: cWHITE)
				((ScriptID 210 7) dispose:)
				(chicken dispose:)
				((ScriptID 210 1) init: setScript: (ScriptID 210 5))
				(self dispose:)
			)
		)
	)
)

(instance package of View
	(properties
		x 233
		y 117
		view 211
		loop 6
	)
)

(instance chicken of View
	(properties
		x 152
		y 99
		view 2101
		loop 2
	)
)
