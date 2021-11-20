;;; Sierra Script 1.0 - (do not remove this comment)
(script# 250)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use EgoDead)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm250 0
)

(local
	local0
	local1
	local2
	local3
	gTheObj_2CycleSpeed
	theGGGTheObj_2CycleSpeed
	local6
)
(instance rm250 of GloryRm
	(properties
		picture 250
		east 280
		south 260
	)
	
	(method (init param1)
		(asm
			pushi    1
			pushi    41
			callb    Btst,  2
			bnt      code_0126
			lsg      prevRoomNum
			ldi      360
			eq?     
			bnt      code_0126
			ldi      0
			jmp      code_01fe
code_0126:
			pushi    1
			pushi    48
			callb    Btst,  2
			not     
			bnt      code_0147
			pushi    1
			pushi    37
			callb    Btst,  2
			bt       code_0142
			pushi    1
			pushi    38
			callb    Btst,  2
			bnt      code_0147
code_0142:
			ldi      4
			jmp      code_01fe
code_0147:
			pushi    1
			pushi    76
			callb    Btst,  2
			not     
			bnt      code_015f
			pushi    1
			pushi    48
			callb    Btst,  2
			bnt      code_015f
			ldi      5
			jmp      code_01fe
code_015f:
			pushi    1
			pushi    76
			callb    Btst,  2
			bnt      code_0178
			lsg      Day
			lsg      gCurrentDay_2
			ldi      1
			add     
			ge?     
			bnt      code_0178
			ldi      6
			jmp      code_01fe
code_0178:
			pushi    1
			pushi    74
			callb    Btst,  2
			bnt      code_0199
			pushi    1
			pushi    75
			callb    Btst,  2
			bnt      code_0199
			lsg      Day
			lsg      gCurrentDay_2
			ldi      1
			add     
			ge?     
			bnt      code_0199
			ldi      3
			jmp      code_01fe
code_0199:
			lsg      Day
			lsg      gCurrentDay_2
			ldi      1
			add     
			ge?     
			bnt      code_01b7
			pushi    1
			pushi    74
			callb    Btst,  2
			bnt      code_01b7
			pushi    1
			pushi    75
			callb    Btst,  2
			not     
			bt       code_01d1
code_01b7:
			pushi    1
			pushi    74
			callb    Btst,  2
			bnt      code_01d5
			pushi    1
			pushi    75
			callb    Btst,  2
			bnt      code_01d5
			lsg      gCurrentDay_2
			lag      Day
			eq?     
			bnt      code_01d5
code_01d1:
			ldi      2
			jmp      code_01fe
code_01d5:
			lsg      gCurrentDay_2
			ldi      0
			eq?     
			bt       code_01f8
			pushi    1
			pushi    74
			callb    Btst,  2
			bnt      code_01fc
			pushi    1
			pushi    75
			callb    Btst,  2
			not     
			bnt      code_01fc
			lsg      gCurrentDay_2
			lag      Day
			eq?     
			bnt      code_01fc
code_01f8:
			ldi      1
			jmp      code_01fe
code_01fc:
			ldi      7
code_01fe:
			sal      local2
			pushi    3
			lsg      prevRoomNum
			pushi    280
			pushi    260
			calle    OneOf,  6
			not     
			bnt      code_0228
			pushi    #number
			pushi    1
			pushi    250
			pushi    254
			pushi    1
			pushi    65535
			pushi    51
			pushi    0
			lag      theMusic
			send     16
code_0228:
			pushi    4
			pushi    2
			pushi    66
			pushi    85
			pushi    100
			callk    Palette,  8
			pushi    #init
			pushi    0
			pushi    790
			pushi    0
			pushi    332
			pushi    2
			pushi    335
			pushi    240
			pushi    342
			pushi    5
			class    Scaler
			push    
			pushi    100
			pushi    79
			pushi    176
			pushi    132
			lag      ego
			send     30
			pushi    #nsLeft
			pushi    1
			pushi    289
			pushi    19
			pushi    1
			pushi    30
			pushi    21
			pushi    1
			pushi    173
			pushi    20
			pushi    1
			pushi    319
			pushi    309
			pushi    1
			pushi    319
			pushi    310
			pushi    1
			pushi    155
			pushi    147
			pushi    0
			pushi    #new
			pushi    0
			pushi    2
			pushi    6
			pushi    0
			callk    ScriptID,  4
			send     4
			send     40
			pushi    #init
			pushi    0
			pushi    74
			pushi    1
			pushi    0
			pushi    317
			pushi    4
			dup     
			pushi    42
			pushi    29
			pushi    80
			lofsa    guildDoor
			send     22
			pushi    #init
			pushi    0
			pushi    317
			pushi    1
			pushi    4
			lofsa    guildWin
			send     10
			pushi    #init
			pushi    0
			pushi    317
			pushi    1
			pushi    4
			lofsa    guildTopWin
			send     10
			pushi    #init
			pushi    0
			pushi    317
			pushi    1
			pushi    4
			lofsa    guildWall
			send     10
			pushi    #init
			pushi    0
			pushi    317
			pushi    1
			pushi    4
			lofsa    tombStone
			send     10
			pushi    #init
			pushi    0
			pushi    317
			pushi    1
			pushi    4
			lofsa    archway
			send     10
			lsg      timeODay
			ldi      3
			le?     
			bnt      code_031d
			pushi    3
			lsl      local2
			pushi    0
			pushi    4
			calle    OneOf,  6
			not     
			bt       code_034b
code_031d:
			lsl      local2
			ldi      0
			eq?     
			bnt      code_03ea
			lsg      timeODay
			ldi      3
			le?     
			bnt      code_03ea
			pushi    1
			pushi    48
			callb    Btst,  2
			not     
			bnt      code_0347
			pushi    1
			pushi    37
			callb    Btst,  2
			bt       code_0347
			pushi    1
			pushi    38
			callb    Btst,  2
code_0347:
			not     
			bnt      code_03ea
code_034b:
			pushi    #init
			pushi    0
			pushi    317
			pushi    2
			pushi    4
			pushi    2
			pushi    184
			pushi    1
			lofsa    sIgorCarve
			push    
			lofsa    igor
			send     18
			pushi    #init
			pushi    0
			pushi    317
			pushi    1
			pushi    4
			lofsa    stone
			send     10
			pushi    #init
			pushi    0
			pushi    317
			pushi    1
			pushi    4
			lofsa    shovel
			send     10
			pushi    #addObstacle
			pushi    1
			pushi    #type
			pushi    1
			pushi    2
			pushi    147
			pushi    26
			pushi    0
			pushi    0
			pushi    319
			pushi    0
			pushi    319
			pushi    144
			pushi    212
			pushi    182
			pushi    78
			pushi    133
			pushi    50
			pushi    136
			pushi    36
			pushi    150
			pushi    4
			pushi    150
			pushi    4
			pushi    171
			pushi    156
			pushi    171
			pushi    174
			pushi    189
			pushi    10
			pushi    189
			pushi    0
			pushi    189
			pushi    153
			pushi    0
			pushi    #new
			pushi    0
			class    Polygon
			send     4
			send     66
			push    
			lag      curRoom
			send     6
			jmp      code_0449
code_03ea:
			pushi    #addObstacle
			pushi    1
			pushi    #type
			pushi    1
			pushi    2
			pushi    147
			pushi    24
			pushi    0
			pushi    0
			pushi    319
			pushi    0
			pushi    319
			pushi    144
			pushi    212
			pushi    182
			pushi    78
			pushi    133
			pushi    50
			pushi    136
			pushi    4
			pushi    150
			pushi    4
			pushi    171
			pushi    156
			pushi    171
			pushi    174
			pushi    189
			pushi    10
			pushi    189
			pushi    0
			pushi    189
			pushi    153
			pushi    0
			pushi    #new
			pushi    0
			class    Polygon
			send     4
			send     62
			push    
			lag      curRoom
			send     6
code_0449:
			lsg      prevRoomNum
			dup     
			ldi      260
			eq?     
			bnt      code_046e
			pushi    #posn
			pushi    2
			pushi    265
			pushi    240
			lag      ego
			send     8
			ldi      216
			sal      local0
			ldi      187
			sal      local1
			jmp      code_050f
code_046e:
			dup     
			ldi      280
			eq?     
			bnt      code_0491
			pushi    #posn
			pushi    2
			pushi    335
			pushi    140
			lag      ego
			send     8
			ldi      293
			sal      local0
			ldi      162
			sal      local1
			jmp      code_050f
code_0491:
			dup     
			ldi      360
			eq?     
			bnt      code_0500
			pushi    1
			pushi    328
			callb    Btst,  2
			bnt      code_04d5
			pushi    #view
			pushi    1
			pushi    7
			pushi    254
			pushi    2
			pushi    3
			pushi    1
			pushi    16
			pushi    1
			pushi    0
			pushi    332
			pushi    2
			pushi    135
			pushi    126
			pushi    342
			pushi    5
			class    Scaler
			push    
			pushi    89
			pushi    60
			pushi    150
			pushi    120
			lag      ego
			send     42
			jmp      code_050f
code_04d5:
			pushi    #posn
			pushi    2
			pushi    203
			pushi    187
			pushi    74
			pushi    1
			pushi    51
			lag      ego
			send     14
			pushi    #setCel
			pushi    1
			pushi    #lastCel
			pushi    0
			lofsa    monkDoor
			send     4
			push    
			lofsa    monkDoor
			send     6
			jmp      code_050f
code_0500:
			pushi    #posn
			pushi    2
			pushi    172
			pushi    178
			lag      ego
			send     8
code_050f:
			toss    
			pushi    1
			pushi    41
			callb    Btst,  2
			bnt      code_05d3
			pushi    #init
			pushi    0
			pushi    317
			pushi    2
			pushi    4
			pushi    33
			lofsa    burnFeature
			send     12
			lsg      prevRoomNum
			ldi      360
			eq?     
			bnt      code_0578
			pushi    #init
			pushi    0
			pushi    74
			pushi    1
			pushi    17
			pushi    317
			pushi    5
			pushi    4
			pushi    28
			pushi    42
			pushi    29
			pushi    43
			lofsa    monkDoor
			send     24
			pushi    #init
			pushi    0
			pushi    236
			pushi    1
			class    Fwd
			push    
			lofsa    fire1
			send     10
			pushi    #init
			pushi    0
			pushi    236
			pushi    1
			class    Fwd
			push    
			lofsa    fire2
			send     10
			jmp      code_069a
code_0578:
			pushi    #init
			pushi    0
			pushi    74
			pushi    1
			pushi    0
			pushi    317
			pushi    2
			pushi    4
			pushi    33
			lofsa    burntRemains
			send     18
			pushi    #init
			pushi    0
			pushi    74
			pushi    1
			pushi    0
			pushi    317
			pushi    2
			pushi    4
			pushi    33
			lofsa    burntRemains1
			send     18
			pushi    #init
			pushi    0
			pushi    74
			pushi    1
			pushi    0
			pushi    317
			pushi    2
			pushi    4
			pushi    33
			lofsa    burntRemains2
			send     18
			pushi    #init
			pushi    0
			pushi    74
			pushi    1
			pushi    0
			pushi    317
			pushi    2
			pushi    4
			pushi    33
			lofsa    burntRemains3
			send     18
			jmp      code_069a
code_05d3:
			pushi    #init
			pushi    0
			pushi    74
			pushi    1
			pushi    17
			pushi    317
			pushi    5
			pushi    4
			pushi    28
			pushi    42
			pushi    29
			pushi    43
			lofsa    monkDoor
			send     24
			pushi    #init
			pushi    0
			pushi    317
			pushi    2
			pushi    4
			pushi    33
			lofsa    monkWall1
			send     12
			pushi    #init
			pushi    0
			pushi    317
			pushi    2
			pushi    4
			pushi    33
			lofsa    monkWall2
			send     12
			pushi    #init
			pushi    0
			pushi    317
			pushi    1
			pushi    4
			lofsa    monkHighWin
			send     10
			pushi    #init
			pushi    0
			pushi    317
			pushi    1
			pushi    4
			lofsa    hectMidLtArm
			send     10
			pushi    #init
			pushi    0
			pushi    317
			pushi    1
			pushi    4
			lofsa    hectMidRtArm
			send     10
			pushi    #init
			pushi    0
			pushi    317
			pushi    1
			pushi    4
			lofsa    hectLtArm
			send     10
			pushi    #init
			pushi    0
			pushi    317
			pushi    1
			pushi    4
			lofsa    hectRtArm
			send     10
			pushi    #init
			pushi    0
			pushi    317
			pushi    2
			pushi    4
			pushi    33
			lofsa    monkWin1
			send     12
			pushi    #init
			pushi    0
			pushi    317
			pushi    2
			pushi    4
			pushi    33
			lofsa    monkWin2
			send     12
			pushi    #init
			pushi    0
			pushi    317
			pushi    1
			pushi    4
			lofsa    hectapus
			send     10
code_069a:
			pushi    #init
			pushi    0
			&rest    param1
			super    GloryRm,  4
			pushi    #contains
			pushi    1
			lofsa    igor
			push    
			lag      cast
			send     6
			bnt      code_0708
			pushi    147
			pushi    5
			lsg      ego
			pushi    250
			pushi    16
			pushi    128
			lsl      local2
			dup     
			ldi      0
			eq?     
			bnt      code_06d0
			ldi      19
			jmp      code_0700
code_06d0:
			dup     
			ldi      1
			eq?     
			bnt      code_06da
			ldi      12
			jmp      code_0700
code_06da:
			dup     
			ldi      2
			eq?     
			bnt      code_06e4
			ldi      11
			jmp      code_0700
code_06e4:
			dup     
			ldi      3
			eq?     
			bnt      code_06ee
			ldi      14
			jmp      code_0700
code_06ee:
			dup     
			ldi      5
			eq?     
			bnt      code_06f8
			ldi      15
			jmp      code_0700
code_06f8:
			dup     
			ldi      6
			eq?     
			bnt      code_0700
			ldi      13
code_0700:
			toss    
			push    
			lofsa    heroTeller
			send     14
code_0708:
			lsg      prevRoomNum
			dup     
			ldi      260
			eq?     
			bnt      code_071f
			pushi    #setScript
			pushi    1
			lofsa    sEnter
			push    
			self     6
			jmp      code_077b
code_071f:
			dup     
			ldi      280
			eq?     
			bnt      code_0733
			pushi    #setScript
			pushi    1
			lofsa    sEnter
			push    
			self     6
			jmp      code_077b
code_0733:
			dup     
			ldi      350
			eq?     
			bnt      code_0747
			pushi    #setScript
			pushi    1
			lofsa    sOutGuildDoor
			push    
			self     6
			jmp      code_077b
code_0747:
			dup     
			ldi      360
			eq?     
			bnt      code_0772
			pushi    1
			pushi    328
			callb    Btst,  2
			bnt      code_0765
			pushi    #setScript
			pushi    1
			lofsa    sClimbOutMonks
			push    
			self     6
			jmp      code_077b
code_0765:
			pushi    #setScript
			pushi    1
			lofsa    sExitMonks
			push    
			self     6
			jmp      code_077b
code_0772:
			pushi    #handsOn
			pushi    0
			lag      theGame
			send     4
code_077b:
			toss    
			pushi    1
			pushi    41
			callb    Btst,  2
			not     
			bnt      code_078f
			pushi    #save
			pushi    1
			pushi    1
			lag      theGame
			send     6
code_078f:
			ret     
		)
	)
	
	(method (doit)
		(if
			(and
				(== (curRoom script?) (ScriptID 31 1))
				(>= (ego z?) 25)
				(not (Btst 41))
			)
			(curRoom setScript: sLevitateInMonks)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(89
				(if Night
					((ScriptID 31 0) init: 139 156 35 0 3)
				else
					(messager say: 18 6 34)
				)
			)
			(181 (messager say: 0 181 0))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (newRoom n)
		(if (OneOf n 360 350) (theMusic fade: 0))
		(super newRoom: n &rest)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath local0 local1 self)
			)
			(1
				(cond 
					((and (== local2 5) (cast contains: igor)) (Bset 76) (messager say: 17 6 18 0 self))
					(
						(and
							(cast contains: igor)
							(not (Btst 77))
							(not Night)
						)
						(Bset 77)
						(= local6 1)
						(messager say: 18 6 16 0 self)
					)
					((and (not (Btst 78)) Night) (Bset 78) (= local6 1) (messager say: 18 6 17 0 self))
					(else (= cycles 1))
				)
			)
			(2
				(if (and local6 (== heroType 3))
					(= local6 0)
					(messager say: 18 6 83 0 self)
				else
					(= cycles 1)
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitMonks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= theGGGTheObj_2CycleSpeed egoGait)
				(ego changeGait: 0 setMotion: MoveTo 187 183 self)
			)
			(1
				(ego setMotion: MoveTo 211 175 self)
			)
			(2 (ego setHeading: 180 self))
			(3
				(messager say: 18 6 82 0 self)
			)
			(4
				(ego get: 25)
				(monkDoor setCycle: Beg self)
			)
			(5
				(theMusic2 number: 961 loop: 1 play:)
				(ego
					setPri: -1
					changeGait: theGGGTheObj_2CycleSpeed
					setMotion: MoveTo 210 180 self
				)
			)
			(6
				(if
					(and
						(Btst 41)
						(== prevRoomNum 360)
						(cast contains: igor)
					)
					(messager say: 17 6 9 0 self)
				else
					(= cycles 1)
				)
			)
			(7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sInMonks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= theGGGTheObj_2CycleSpeed egoGait)
				(ego changeGait: 0 setMotion: MoveTo 211 175 self)
			)
			(1
				(ego setPri: 51 setMotion: MoveTo 187 183 self)
			)
			(2
				(ego setMotion: MoveTo 203 187 self)
			)
			(3
				(ego
					changeGait: theGGGTheObj_2CycleSpeed
					setHeading: 0 self
				)
			)
			(4
				(ego drop: 25)
				(messager say: 6 43 0 0 self)
			)
			(5
				(theMusic2 number: 960 loop: 1 play:)
				(monkDoor setCycle: End self)
			)
			(6 (curRoom newRoom: 360))
		)
	)
)

(instance sIgorWarn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 0)
				(igor loop: 1 cel: 1 setScript: 0)
				(= seconds 2)
			)
			(1
				(igor loop: 2 setCycle: End self)
			)
			(2
				(igor loop: 3 setCycle: Fwd)
				(= seconds 2)
			)
			(3
				(= local3 1)
				(Face ego (igor x?) (igor y?) self)
			)
			(4
				(messager say: 17 6 7 0 self)
			)
			(5
				(igor cycleSpeed: 10)
				(= seconds 1)
			)
			(6 (igor setCycle: End self))
			(7
				(igor loop: 2 cel: 5)
				(= seconds 2)
			)
			(8 (igor setCycle: Beg self))
			(9
				(igor setScript: sIgorCarve)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sIgorCarve of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(igor loop: 1 cel: 1)
				(= seconds 4)
			)
			(1
				(igor loop: 0 setCycle: Fwd)
				(= seconds (Random 3 15))
			)
			(2
				(igor setCycle: 0 loop: 1 cel: 1)
				(= seconds (Random 1 4))
			)
			(3 (= state 0) (= cycles 1))
		)
	)
)

(instance sInGuildDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 108)
				(theMusic2 number: 960 loop: 1 play:)
				(guildDoor setCycle: End self)
			)
			(1
				(ego setPri: 17 setMotion: MoveTo 35 128 self)
			)
			(2
				(guildDoor setCycle: Beg self)
			)
			(3
				(theMusic2 number: 961 loop: 1 play:)
				(= seconds 2)
			)
			(4
				(ego setPri: -1)
				(theGame handsOn:)
				(curRoom newRoom: 350)
			)
		)
	)
)

(instance sOutGuildDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego loop: 0 posn: 35 128 setPri: 17)
				(= cycles 1)
			)
			(1
				(theMusic2 number: 960 loop: 1 play:)
				(guildDoor setCycle: End self)
			)
			(2
				(ego setMotion: MoveTo 81 138 self)
			)
			(3
				(ego setPri: -1)
				(guildDoor setCycle: Beg self)
			)
			(4
				(theMusic2 number: 961 loop: 1 play:)
				(= seconds 2)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sHectapusDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= theGGGTheObj_2CycleSpeed egoGait)
				(ego changeGait: 0 setMotion: MoveTo 211 175 self)
			)
			(1
				(ego setPri: 55 setMotion: MoveTo 187 183 self)
				(hectIncidental loop: 0 cel: 0 init: setCycle: End self)
			)
			(2 0)
			(3
				(ego setLoop: 3 1)
				(hectIncidental loop: 1 posn: 180 96 setCycle: Fwd)
				(= seconds 1)
			)
			(4
				(if
				(and (cast contains: igor) (< (igor loop?) 2))
					(igor setLoop: 2 1 setCycle: End self)
				else
					(= cycles 1)
				)
			)
			(5
				(hectMidLtArm setCycle: Fwd)
				(hectMidRtArm setCycle: Fwd)
				(hectLtArm setCycle: End)
				(hectRtArm setCycle: End self)
			)
			(6
				(if (cast contains: igor)
					(messager say: 17 6 8 0 self)
				else
					(= cycles 1)
				)
			)
			(7 (EgoDead 33 250 978 1 912))
		)
	)
)

(instance sClimbIntoMonks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 328)
				(messager say: 18 6 13 0 self)
			)
			(1
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 7
					setLoop: 3 1
					cel: 0
					posn: 135 150
					setSpeed: 15
					setCycle: Walk
					setScaler: Scaler 89 60 150 120
					setMotion: MoveTo 135 120 self
				)
			)
			(2
				(ego cycleSpeed: gTheObj_2CycleSpeed)
				(theGame handsOn:)
				(curRoom newRoom: 360)
			)
		)
	)
)

(instance sClimbOutMonks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bclr 328)
				(= cycles 2)
			)
			(1
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					setSpeed: 15
					setCycle: Walk
					setMotion: MoveTo 135 150 self
				)
			)
			(2
				(ego
					setSpeed: gTheObj_2CycleSpeed
					normalize: 3
					setMotion: MoveTo 135 155 self
				)
			)
			(3
				(if
					(and
						(Btst 41)
						(== prevRoomNum 360)
						(cast contains: igor)
					)
					(messager say: 17 6 9 0 self)
				else
					(= cycles 1)
				)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGrapnelIntoMonks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 328)
				(ego
					view: 8
					loop: 0
					cel: 0
					x: 107
					y: 149
					setCycle: End self
				)
			)
			(1
				(messager say: 9 33 0 0 self)
			)
			(2
				(theGame handsOn:)
				(curRoom newRoom: 360)
			)
		)
	)
)

(instance sLevitateInMonks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 328)
				(= cycles 1)
			)
			(1
				(theGame handsOn:)
				(curRoom newRoom: 360)
			)
		)
	)
)

(instance heroTeller of Teller
	(properties)
	
	(method (respond)
		(super respond: &rest)
		(if (or (not iconValue) (== iconValue -999))
			(switch local2
				(1 (Bset 74))
				(2 (Bset 75))
			)
			(= gCurrentDay_2 Day)
		)
		(return 1)
	)
	
	(method (sayMessage)
		(if (== iconValue 49) (Bset 267))
		(super sayMessage: &rest)
	)
	
	(method (showCases)
		(super showCases: 49 (not (Btst 267)))
	)
)

(instance igorTeller of Teller
	(properties
		title 1
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 73 0))
	)
	
	(method (respond)
		(super respond: &rest)
		(if (or (not iconValue) (== iconValue -999))
			(switch local2
				(1 (Bset 74))
				(2 (Bset 75))
			)
			(= gCurrentDay_2 Day)
		)
		(return 1)
	)
	
	(method (sayMessage)
		(switch iconValue
			(2 (Bset 267))
			(3 (ego get: 60) (Bset 268))
		)
		(super sayMessage: &rest)
	)
	
	(method (showCases)
		(super
			showCases:
				2
				(not (Btst 267))
				32
				gCurrentDay_8
				5
				(if (OneOf local2 2 3) (Btst 205) else 0)
				3
				(if (>= local2 5) (not (Btst 268)) else 0)
		)
	)
)

(instance guildDoorTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(switch iconValue
			(26
				(if (Btst 108)
					(self clean:)
					(curRoom setScript: sInGuildDoor)
				else
					(super sayMessage: &rest)
				)
			)
			(27
				(if (== (ego trySkill: 0 275) 1)
					(self clean:)
					(ego addHonor: -20)
					(Bset 108)
					(messager say: 18 6 79)
					(curRoom setScript: sInGuildDoor)
				else
					(super sayMessage: &rest)
				)
			)
			(29
				(if Night
					(if (== (ego trySkill: 9 250) 1)
						(self clean:)
						(ego addHonor: -20)
						(Bset 108)
						(messager say: 18 6 37)
						(curRoom setScript: sInGuildDoor)
					else
						(messager say: 18 6 10)
					)
				else
					(messager say: 18 6 35)
				)
			)
			(28
				(self clean:)
				(ego addHonor: 50)
				(Bset 108)
				(messager say: 18 6 36)
				(curRoom setScript: sInGuildDoor)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				29
				(if (not (Btst 108)) (ego has: 24) else 0)
				28
				(if (and (not (Btst 108)) (ego has: 14))
					(ego has: 61)
				else
					0
				)
				27
				(not (Btst 108))
		)
	)
)

(instance monkDoorTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((and (Btst 41) (== prevRoomNum 360)) (messager say: 18 6 81))
					(
					(and (not local3) (<= timeODay 3) (not (== local2 4))) (curRoom setScript: sIgorWarn))
					(else (super doVerb: theVerb &rest))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (sayMessage)
		(if (OneOf iconValue 26 27 31 29)
			(self clean:)
			(curRoom setScript: sHectapusDeath)
		else
			(super sayMessage: &rest)
		)
	)
)

(instance igor of Actor
	(properties
		noun 17
		approachX 44
		approachY 160
		x 21
		y 152
		priority 70
		fixPriority 1
		view 257
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(igorTeller
			init:
				self
				250
				16
				123
				(switch local2
					(0 19)
					(1 12)
					(2 11)
					(3 14)
					(5 15)
					(6 13)
				)
		)
	)
	
	(method (doit)
		(if
			(and
				(self cycler?)
				((self cycler?) isKindOf: Fwd)
				(== (self view?) 257)
				(== (self loop?) 0)
				(== (self cel?) 2)
				(not (& (self signal?) $0008))
			)
			(theMusic3 number: 13 play:)
		)
		(super doit: &rest)
	)
)

(instance monkDoor of Prop
	(properties
		noun 6
		approachX 210
		approachY 175
		x 229
		y 174
		fixPriority 1
		view 250
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(monkDoorTeller init: self 250 16 145)
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 28 42 29 43)
				(cond 
					((and (Btst 41) (== prevRoomNum 360)) (messager say: 18 6 81))
					(
					(and (not local3) (<= timeODay 3) (not (== local2 4))) (curRoom setScript: sIgorWarn))
					(else
						(switch theVerb
							(28
								(curRoom setScript: sHectapusDeath)
							)
							(42
								(curRoom setScript: sHectapusDeath)
							)
							(29
								(curRoom setScript: sHectapusDeath)
							)
							(else 
								(curRoom setScript: sInMonks)
							)
						)
					)
				)
			)
			((== theVerb 80)
				(if Night
					(messager say: 18 6 12)
				else
					(messager say: 18 6 34)
				)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance guildDoor of Prop
	(properties
		noun 1
		sightAngle 90
		approachX 65
		approachY 135
		x 63
		y 108
		view 250
		loop 3
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(if (Btst 108)
			(= heading
				(((ScriptID 49 0) new:)
					init:
						((Polygon new:)
							type: 1
							init: 68 128 78 137 53 141 43 130
							yourself:
						)
						1
						7
						3
						sInGuildDoor
					yourself:
				)
			)
		else
			(guildDoorTeller init: self 250 16 144)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(switch (event message?)
			(KEY_P
				(= approachX 120)
				(= approachY 149)
			)
			(JOY_DOWNRIGHT
				(= approachX 65)
				(= approachY 135)
			)
			(else 
				(= approachX 79)
				(= approachY 138)
			)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4)
			(42
				(cond 
					((Btst 108) (messager say: 18 6 80) (curRoom setScript: sInGuildDoor))
					(Night
						(if (== (ego trySkill: 9 250) 1)
							(ego addHonor: -20)
							(Bset 108)
							(messager say: 18 6 37)
							(curRoom setScript: sInGuildDoor)
						else
							(messager say: 18 6 10)
						)
					)
					(else (messager say: 18 6 35))
				)
			)
			(29
				(cond 
					((Btst 108) (messager say: 18 6 80) (curRoom setScript: sInGuildDoor))
					((ego has: 61)
						(ego addHonor: 50)
						(Btst 108)
						(messager say: 18 6 36)
						(curRoom setScript: sInGuildDoor)
					)
					(else (messager say: 18 6 38))
				)
			)
			(80
				(if Night
					(cond 
						((Btst 108) (messager say: 18 6 80) (curRoom setScript: sInGuildDoor))
						((ego castSpell: 20)
							(ego addHonor: -20)
							(= projX 50)
							(= projY 110)
							(curRoom setScript: (ScriptID 13 0) 0 self)
						)
					)
				else
					(messager say: 18 6 35)
				)
			)
			(-80
				(curRoom setScript: sInGuildDoor)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fire1 of Prop
	(properties
		x 140
		y 105
		view 250
		loop 1
	)
)

(instance fire2 of Prop
	(properties
		x 181
		y 69
		view 250
		loop 4
	)
)

(instance hectIncidental of Prop
	(properties
		noun 7
		approachX 210
		approachY 175
		x 178
		y 78
		priority 221
		view 251
	)
)

(instance hectLtArm of Prop
	(properties
		noun 7
		approachX 210
		approachY 175
		x 184
		y 106
		priority 119
		view 251
		loop 2
	)
)

(instance hectRtArm of Prop
	(properties
		noun 7
		approachX 210
		approachY 175
		x 184
		y 106
		priority 119
		view 251
		loop 4
	)
)

(instance hectMidLtArm of Prop
	(properties
		noun 7
		approachX 210
		approachY 175
		x 184
		y 106
		priority 11
		view 251
		loop 6
	)
)

(instance hectMidRtArm of Prop
	(properties
		noun 7
		approachX 210
		approachY 175
		x 184
		y 106
		priority 11
		view 251
		loop 8
	)
)

(instance shovel of View
	(properties
		noun 20
		sightAngle 180
		x 69
		y 152
		z 50
		fixPriority 1
		view 250
		loop 5
		signal $4000
	)
)

(instance stone of View
	(properties
		noun 21
		sightAngle 180
		approachX 43
		approachY 146
		x 27
		y 175
		z 30
		fixPriority 1
		view 257
		loop 4
		signal $4000
	)
)

(instance burntRemains of View
	(properties
		noun 23
		sightAngle 180
		approachX 189
		approachY 175
		x 52
		y 60
		view 252
		signal $4000
	)
)

(instance burntRemains1 of View
	(properties
		noun 23
		sightAngle 180
		approachX 189
		approachY 175
		x 113
		y 25
		view 252
		cel 1
		signal $4000
	)
)

(instance burntRemains2 of View
	(properties
		noun 23
		sightAngle 180
		approachX 189
		approachY 175
		x 151
		y 116
		view 252
		cel 2
		signal $4000
	)
)

(instance burntRemains3 of View
	(properties
		noun 23
		sightAngle 180
		approachX 189
		approachY 175
		x 141
		y 175
		view 252
		cel 3
		signal $4000
	)
)

(instance burnFeature of Feature
	(properties
		noun 23
		nsLeft 106
		nsTop 23
		nsRight 319
		nsBottom 163
		sightAngle 180
		approachX 189
		approachY 175
		x 169
		y 95
	)
)

(instance guildWin of Feature
	(properties
		noun 2
		nsLeft 2
		nsTop 71
		nsRight 38
		nsBottom 119
		sightAngle 180
		x 20
		y 95
	)
)

(instance guildTopWin of Feature
	(properties
		noun 3
		nsLeft 31
		nsTop 1
		nsRight 87
		nsBottom 67
		sightAngle 180
		x 59
		y 34
	)
)

(instance guildWall of Feature
	(properties
		nsLeft 69
		nsTop 95
		nsRight 117
		nsBottom 138
		sightAngle 180
		x 93
		y 116
	)
)

(instance tombStone of Feature
	(properties
		noun 4
		nsLeft 54
		nsTop 137
		nsRight 151
		nsBottom 189
		sightAngle 180
		x 102
		y 163
	)
)

(instance monkWin1 of Feature
	(properties
		noun 5
		nsLeft 131
		nsTop 82
		nsRight 150
		nsBottom 102
		sightAngle 180
		approachX 135
		approachY 150
		x 140
		y 92
	)
	
	(method (handleEvent event)
		(monkWall1 handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== (ego trySkill: 11 250) 1)
					(curRoom setScript: sClimbIntoMonks)
				else
					(messager say: 18 6 14)
				)
			)
			(33
				(if Night
					(curRoom setScript: sGrapnelIntoMonks)
				else
					(messager say: 18 6 34)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance monkWin2 of Feature
	(properties
		noun 5
		nsLeft 230
		nsTop 52
		nsRight 252
		nsBottom 76
		sightAngle 180
		x 241
		y 64
	)
	
	(method (handleEvent event)
		(monkWall1 handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(33
				(if Night
					(curRoom setScript: sGrapnelIntoMonks)
				else
					(messager say: 18 6 34)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance hectapus of Feature
	(properties
		noun 7
		nsLeft 149
		nsTop 74
		nsRight 223
		nsBottom 111
		sightAngle 180
		approachX 210
		approachY 175
		x 186
		y 92
	)
)

(instance archway of Feature
	(properties
		noun 8
		nsLeft 236
		nsTop 67
		nsRight 319
		nsBottom 186
		sightAngle 180
		x 277
		y 126
	)
)

(instance monkWall1 of Feature
	(properties
		noun 9
		nsLeft 94
		nsTop 50
		nsRight 172
		nsBottom 101
		sightAngle 180
		approachX 135
		approachY 150
		x 133
		y 75
	)
	
	(method (handleEvent event)
		(if (== (event message?) KEY_PRIOR)
			(= approachX 107)
			(= approachY 149)
		else
			(= approachX 135)
			(= approachY 150)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== (ego trySkill: 11 250) 1)
					(curRoom setScript: sClimbIntoMonks)
				else
					(messager say: 18 6 14)
				)
			)
			(33
				(if Night
					(curRoom setScript: sGrapnelIntoMonks)
				else
					(messager say: 18 6 34)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance monkWall2 of Feature
	(properties
		noun 9
		nsLeft 208
		nsTop 21
		nsRight 310
		nsBottom 69
		sightAngle 180
		approachX 135
		approachY 150
		x 259
		y 45
	)
	
	(method (handleEvent event)
		(monkWall1 handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(monkWall1 doVerb: theVerb &rest)
	)
)

(instance monkHighWin of Feature
	(properties
		noun 10
		nsLeft 128
		nsTop 24
		nsRight 143
		nsBottom 35
		sightAngle 180
		approachX 135
		approachY 150
		x 135
		y 29
	)
)
