;;; Sierra Script 1.0 - (do not remove this comment)
(script# 786)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	SlthOMtr 0
)

(local
	[rankText 20] = [
		786 0 29 4
		786 1 55 12
		786 2 81 14
		786 3 107 11
		786 4 133 9
	]
	local20
	local21
	local22
	local23 =  -1
	[local24 5]
	[local29 3]
	local32
	local33 = [
		0 1
		786 5 1 3
		786 6 4 3
		786 7 7 8
		786 8 15 12
		786 9 27 5
		786 10 32 1
		786 11 33 1
		786 12 34 1
		786 13 35 10
		786 14 45 3
		786 15 48 2
		786 16 50 4
		786 17 54 1
		786 18 55 5
		786 19 60 1
		786 20 61 1
		786 21 62 4
		786 22 66 1
		786 23 67 4
		786 24 71 1
		786 25 72 2
		786 26 74 1
		786 27 75 1
		786 28]
	local129 = [
		786 29
		786 30
		786 31
		786 32
		786 33
		786 34
		786 35
		786 33
		786 36
		786 37
		786 38
		786 34
		786 35
		786 39
		786 40
		786 41
		786 42
		786 43
		786 44
		786 45
		786 46
		786 47
		786 48
		786 49
		786 50
		786 51
		786 52
		786 53
		786 54
		786 55
		786 56
		786 57
		786 33
		786 58
		786 59
		786 60
		786 61
		786 62
		786 63
		786 64
		786 65
		786 66
		786 67
		786 55
		786 68
		786 69
		786 34
		786 35
		786 34
		786 35
		786 70
		786 33
		786 34
		786 40
		786 36
		786 70
		786 38
		786 34
		786 39
		786 40
		786 38
		786 38
		786 71
		786 72
		786 67
		786 73
		786 46
		786 70
		786 69
		786 34
		786 40
		786 70
		786 70
		786 33
		786 33
		]
	[local279 84] = [786 74 786 75 786 76 786 77 786 78 786 79 786 80 786 81 786 82 786 83 786 84 786 85 786 86 786 87 786 88 786 89 786 90 786 91 786 92 786 93 786 94 786 95 786 96 786 97 786 98 786 99 786 100 786 101 786 102 786 103 786 104 786 105 786 106 786 107 786 108 786 109 786 110 786 111 786 112 786 113 786 114 786 115]
)
(procedure (localproc_000c flagEnum &tmp i temp1)
	(= i (/ flagEnum 16))
	(= temp1 (<< $0001 (mod flagEnum 16)))
	(= [local24 i] (| [local24 i] temp1))
)

(procedure (localproc_002b param1)
	(localproc_000c (+ param1 80))
)

(procedure (localproc_0037 &tmp temp0 temp1 [temp2 2])
	(if (& global135 $0001)
		(localproc_000c 0)
	else
		(localproc_002b 0)
		(localproc_002b 19)
	)
	(if (!= ((inventory at: 12) owner?) 35)
		(localproc_000c 1)
	else
		(localproc_002b 9)
	)
	(if (!= ((inventory at: 17) owner?) 59)
		(localproc_000c 2)
	else
		(localproc_002b 20)
	)
	(if (!= ((inventory at: 11) owner?) 53)
		(localproc_000c 3)
	else
		(localproc_002b 14)
	)
	(if (ego has: 5)
		(localproc_000c 4)
	else
		(localproc_002b 1)
	)
	(if (ego has: 16)
		(localproc_000c 5)
	else
		(localproc_002b 35)
	)
	(if (ego has: 1)
		(localproc_000c 6)
	else
		(localproc_002b 21)
	)
	(if (!= deadGuests 127)
		(localproc_002b 2)
	)
	(if (& deadGuests $0040)
		(localproc_000c 7)
	)
	(if (& deadGuests $0008)
		(localproc_000c 8)
	)
	(if (& deadGuests $0001)
		(localproc_000c 9)
	)
	(if (& deadGuests $0004)
		(localproc_000c 10)
	)
	(if (& deadGuests $0020)
		(localproc_000c 11)
	)
	(if (& deadGuests $0002)
		(localproc_000c 12)
	)
	(if (& deadGuests $0010)
		(localproc_000c 14)
		(localproc_000c 13)
	)
	(if global139
		(localproc_000c 16)
		(localproc_000c 21)
		(localproc_000c 18)
	)
	(if global140
		(localproc_000c 16)
		(localproc_000c 15)
		(localproc_000c 22)
	)
	(if global143
		(localproc_000c 17)
		(localproc_000c 26)
		(localproc_000c 24)
	)
	(if global144
		(localproc_000c 17)
		(localproc_000c 25)
		(localproc_000c 23)
	)
	(if
		(or
			(not global139)
			(not global140)
			(not global143)
			(not global144)
		)
		(localproc_002b 4)
	)
	(if (& [global148 3] $0008)
		(localproc_000c 19)
	else
		(localproc_002b 5)
	)
	(if (& global205 $0001)
		(localproc_000c 20)
	else
		(localproc_002b 22)
	)
	(if (< numCrackers 6)
		(localproc_000c 27)
	else
		(localproc_002b 14)
		(localproc_002b 15)
	)
	(if (or (not (ego has: 7)) (not (ego has: 3)))
		(localproc_002b 10)
	)
	(if (and (Btst 1) (Btst 2))
		(localproc_000c 28)
	else
		(localproc_002b 37)
	)
	(if (and (Btst 3) (Btst 12))
		(localproc_000c 29)
	else
		(localproc_002b 36)
	)
	(if (and oiledBell (& global142 $0002))
		(localproc_000c 30)
	else
		(localproc_002b 23)
	)
	(= temp0 4)
	(= temp1 0)
	(while (<= temp0 12)
		(if (Btst temp0) (++ temp1))
		(++ temp0)
	)
	(if (> temp1 1)
		(localproc_000c 31)
	else
		(localproc_002b 6)
	)
	(if (or (Btst 3) (Btst 12))
		(localproc_000c 32)
	else
		(localproc_002b 36)
	)
	(if (!= (>> global388 $0008) 9) (localproc_002b 7))
	(if (ego has: 22)
		(localproc_000c 33)
	else
		(localproc_002b 37)
	)
	(if (Btst 14)
		(localproc_000c 34)
	else
		(localproc_002b 11)
	)
	(if (Btst 4)
		(localproc_000c 35)
	else
		(localproc_002b 29)
	)
	(if (Btst 5)
		(localproc_000c 36)
	else
		(localproc_002b 31)
	)
	(= temp0 6)
	(= temp1 37)
	(while (<= temp0 12)
		(if (Btst temp0)
			(localproc_000c temp1)
		else
			(localproc_002b 6)
		)
		(++ temp0)
		(++ temp1)
	)
	(if (Btst 11)
		(localproc_000c 42)
	else
		(localproc_002b 32)
	)
	(if (Btst 12)
		(localproc_000c 43)
	else
		(localproc_002b 38)
		(localproc_002b 33)
	)
	(if (and (ego has: 8) (Btst 13))
		(localproc_000c 44)
	else
		(localproc_002b 28)
	)
	(if (Btst 15)
		(localproc_000c 45)
	else
		(localproc_002b 8)
	)
	(if (Btst 16) (localproc_000c 46))
	(if (Btst 17) (localproc_000c 47))
	(if (Btst 18) (localproc_000c 48))
	(if (Btst 19) (localproc_000c 49))
	(if
		(or
			(not (Btst 16))
			(not (Btst 17))
			(not (Btst 18))
			(not (Btst 19))
		)
		(localproc_002b 4)
		(localproc_002b 16)
	)
	(if global177
		(localproc_000c 50)
		(localproc_000c 52)
	else
		(localproc_002b 17)
	)
	(if (and global125 global107)
		(localproc_000c 51)
	else
		(localproc_002b 12)
	)
	(if (and (Btst 20) global107)
		(localproc_000c 53)
	else
		(localproc_002b 12)
		(localproc_002b 30)
	)
	(if (Btst 21)
		(localproc_000c 54)
	else
		(localproc_002b 4)
		(localproc_002b 13)
	)
	(localproc_000c 55)
	(localproc_000c 59)
	(if (Btst 23)
		(localproc_000c 57)
		(localproc_000c 56)
		(localproc_000c 60)
	else
		(localproc_002b 4)
		(localproc_002b 16)
	)
	(if (Btst 22)
		(localproc_000c 58)
	else
		(localproc_002b 4)
		(localproc_002b 27)
	)
	(if (Btst 24)
		(localproc_000c 61)
	else
		(localproc_002b 4)
		(localproc_002b 13)
	)
	(if (and (Btst 25) (Btst 26))
		(localproc_000c 62)
	else
		(localproc_002b 39)
	)
	(if
	(and (Btst 27) (or (& deadGuests $0020) (Btst 28)))
		(localproc_000c 63)
	else
		(localproc_002b 34)
	)
	(if (and (Btst 29) (Btst 30))
		(localproc_000c 64)
	else
		(localproc_002b 25)
	)
	(if (and (Btst 31) (Btst 32))
		(localproc_000c 65)
	else
		(localproc_002b 26)
	)
	(if (& global205 $0002)
		(localproc_000c 66)
	else
		(localproc_002b 40)
	)
	(if (Btst 45)
		(localproc_000c 68)
		(localproc_000c 69)
	else
		(localproc_002b 18)
	)
	(if global126
		(localproc_000c 68)
		(localproc_000c 70)
	else
		(localproc_002b 18)
	)
	(if (not (Btst 33))
		(localproc_000c 67)
		(localproc_000c 68)
	else
		(localproc_002b 7)
	)
	(if (!= prevRoomNum 785)
		(localproc_000c 74)
	else
		(localproc_002b 7)
		(localproc_002b 41)
	)
	(if (Btst 34)
		(localproc_000c 71)
		(if (or (ego has: 21) (ego has: 8))
			(localproc_000c 72)
		else
			(localproc_002b 17)
			(localproc_002b 24)
		)
	else
		(localproc_002b 24)
	)
	(if (and global107 (not (Btst 33)))
		(localproc_000c 73)
	else
		(localproc_002b 12)
	)
)

(procedure (localproc_0798 &tmp temp0 temp1 temp2 temp3 [temp4 25])
	(= temp0 0)
	(= temp2 0)
	(= temp1 0)
	(= temp3 1)
	(while (< temp0 75)
		(if (& [local24 temp2] temp3) (++ temp1))
		(if (== (= temp3 (<< temp3 $0001)) 0)
			(++ temp2)
			(= temp3 1)
		)
		(++ temp0)
	)
	(cond 
		((== temp1 75) (= local20 0) (= local21 144))
		(
			(<=
				(= local21
					(-
						248
						(= temp1
							(/
								(= temp1
									(* (= temp1 (/ (= temp1 (* temp1 100)) 75)) 105)
								)
								100
							)
						)
					)
				)
				170
			)
			(= local20 1)
		)
		((<= local21 196) (= local20 2))
		((<= local21 222) (= local20 3))
		(else (= local20 4))
	)
)

(procedure (ShowRank i)
	(*= i 4)
	(Display [rankText i] [rankText (++ i)]
		p_at 35 [rankText (++ i)]
		p_width 85
		p_color (& [rankText (++ i)] local23)
		p_back -1
		p_font 4
	)
)

(procedure (localproc_086f &tmp temp0 temp1 temp2 [temp3 160])
	(asm
		ldi      0
		sat      temp0
code_0875:
		lst      temp0
		ldi      42
		lt?     
		bnt      code_091e
		lst      temp0
		ldi      16
		div     
		sat      temp1
		pushi    1
		lst      temp0
		ldi      16
		mod     
		shl     
		sat      temp2
		lat      temp1
		lsli     local29
		lat      temp2
		and     
		bnt      code_0919
		pushi    17
		pushi    5
		lea      @temp3
		push    
		pushi    786
		pushi    116
		lst      temp0
		ldi      2
		mul     
		lsli     local279
		lst      temp0
		ldi      2
		mul     
		push    
		ldi      1
		add     
		lsli     local279
		callk    Format,  10
		push    
		pushi    30
		pushi    1
		pushi    70
		pushi    280
		pushi    81
		lofsa    {__MORE__}
		push    
		pushi    1
		pushi    81
		lofsa    { RESTORE_}
		push    
		pushi    2
		pushi    81
		lofsa    { RESTART_}
		push    
		pushi    3
		pushi    81
		lofsa    {__QUIT__}
		push    
		pushi    4
		calle    Print,  34
		push    
		dup     
		ldi      2
		eq?     
		bnt      code_08f8
		pushi    #restore
		pushi    0
		lag      theGame
		send     4
		jmp      code_0918
code_08f8:
		dup     
		ldi      3
		eq?     
		bnt      code_090a
		pushi    #restart
		pushi    0
		lag      theGame
		send     4
		jmp      code_0918
code_090a:
		dup     
		ldi      4
		eq?     
		bnt      code_0918
		ldi      1
		sag      quit
		jmp      code_091e
code_0918:
		toss    
code_0919:
		+at      temp0
		jmp      code_0875
code_091e:
		ret     
	)
)

(procedure (localproc_091f &tmp theY temp1 temp2 temp3 temp4 temp5 temp6 [str 10])
	(= currentPalette 1)
	(DrawPic 86 WIPEUP TRUE currentPalette)
	(Display [local33 (+ local32 2)] [local33 (+ local32 3)]
		p_at 18 7
		p_width 163
		p_color vGREY
		p_back -1
		p_font 4
	)
	(= theY 25)
	(= temp6 0)
	(= temp2 [local33 local32])
	(= temp1 [local33 (+ local32 1)])
	(if (< local32 92)
		(= temp5 0)
		(while (< temp5 temp1)
			(= temp3 (/ temp2 16))
			(= temp4 (<< $0001 (mod temp2 16)))
			(if (& [local24 temp3] temp4)
				(Display [local129 (* temp2 2)] [local129 (+ (* temp2 2) 1)]
					p_at 23 theY
					p_width 158
					p_color vGREY
					p_back -1
					p_font 4
				)
				(+= theY 9)
			else
				(= temp6 1)
			)
			(++ temp5)
			(++ temp2)
		)
	else
		(if (& global388 $00ff)
			(Format @str
				{%u:%u}
				(>> global388 $0008)
				(& global388 $00ff)
			)
		else
			(Format @str {%u:00} (>> global388 $0008))
		)
		(Display @str
			p_at 23 theY
			p_width 50
			p_color vGREY
			p_back -1
			p_font 4
		)
	)
	(if temp6
		(Display 786 117
			p_at 23 theY
			p_width 50
			p_color vRED
			p_back -1
			p_font 4
		)
	)
)

(procedure (localproc_0a67)
	(= currentPalette 0)
	(nBWindow hide:)
	(DrawPic 86 WIPEDOWN TRUE currentPalette)
	(addToPics doit:)
	(liquid setScript: Flash)
)

(procedure (localproc_0a94)
	(if (< local32 92)
		(+= local32 4)
		(localproc_091f)
	)
)

(procedure (localproc_0aa9)
	(if local32
		(-= local32 4)
		(localproc_091f)
	)
)

(instance SlthOMtr of Room
	(properties
		picture 87
	)
	
	(method (init &tmp i)
		(super init:)
		(TheMenuBar state: FALSE)
		(User canControl: TRUE canInput: FALSE)
		(Load FONT 4)
		(LoadMany SOUND 17 27 119)
		(localproc_0037)
		(localproc_0798)
		(liquid
			setPri: 3
			setMotion: MoveTo 133 local21 self
			init:
		)
		(soundFX number: 119 loop: 1 play:)
		(Display 786 118
			p_at 75 1
			p_width 140
			p_color vGREY
			p_back -1
			p_font SYSFONT
		)
		(Display 786 118
			p_at 76 0
			p_width 140
			p_color vWHITE
			p_back -1
			p_font SYSFONT
		)
		(for ((= i 0)) (< i 5) ((++ i))
			(ShowRank i)
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (cue)
		(soundFX stop:)
		(cSound number: 27 loop: -1 play:)
		(= local22 11)
		(self setScript: Notes)
	)
)

(instance Notes of Script

	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0c33
			-al      local22
			bnt      code_0c2c
			lsl      local23
			ldi      65535
			xor     
			sal      local23
			pushi    1
			lsl      local20
			call     ShowRank,  2
			ldi      65535
			aTop     state
			ldi      5
			aTop     cycles
			jmp      code_0eac
code_0c2c:
			ldi      1
			aTop     cycles
			jmp      code_0eac
code_0c33:
			dup     
			ldi      1
			eq?     
			bnt      code_0c7f
			pushi    15
			pushi    786
			pushi    119
			pushi    67
			pushi    185
			pushi    30
			pushi    70
			pushi    100
			pushi    30
			pushi    1
			pushi    81
			lofsa    {___Yes__}
			push    
			pushi    1
			pushi    81
			lofsa    {__Quit__}
			push    
			pushi    2
			calle    Print,  30
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_0c70
			ldi      1
			aTop     cycles
			jmp      code_0c7b
code_0c70:
			dup     
			ldi      2
			eq?     
			bnt      code_0c7b
			ldi      1
			sag      quit
code_0c7b:
			toss    
			jmp      code_0eac
code_0c7f:
			dup     
			ldi      2
			eq?     
			bnt      code_0ce0
			pushi    2
			pushi    86
			pushi    4
			callk    DrawPic,  4
			pushi    #hide
			pushi    0
			lofsa    liquid
			send     4
			pushi    #add
			pushi    2
			lofsa    Tulane
			push    
			lofsa    LauraBow
			push    
			pushi    60
			pushi    0
			lag      addToPics
			send     12
			pushi    #init
			pushi    0
			pushi    198
			pushi    0
			lofsa    silhouette
			send     8
			pushi    #init
			pushi    0
			pushi    198
			pushi    0
			lofsa    lightning
			send     8
			pushi    #init
			pushi    0
			pushi    137
			pushi    0
			lofsa    nBWindow
			send     8
			pushi    #setScript
			pushi    1
			lofsa    Flash
			push    
			lofsa    liquid
			send     6
			ldi      1
			aTop     cycles
			jmp      code_0eac
code_0ce0:
			dup     
			ldi      3
			eq?     
			bnt      code_0e9d
			pushi    #script
			pushi    0
			lofsa    liquid
			send     4
			bnt      code_0cf9
			ldi      2
			aTop     state
			jmp      code_0e96
code_0cf9:
			pushi    13
			pushi    786
			pushi    120
			pushi    67
			pushi    195
			pushi    8
			pushi    30
			pushi    1
			pushi    81
			lofsa    { Open_}
			push    
			pushi    1
			pushi    81
			lofsa    { Exit_}
			push    
			pushi    2
			calle    Print,  26
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_0d9d
			ldi      0
			sal      local32
			pushi    0
			call     localproc_091f,  0
			pushi    #show
			pushi    0
			lofsa    nBWindow
			send     4
code_0d36:
			pushi    16
			pushi    786
			pushi    120
			pushi    67
			pushi    195
			pushi    8
			pushi    30
			pushi    1
			pushi    81
			lofsa    {\19}
			push    
			pushi    1
			pushi    81
			lofsa    {\18}
			push    
			pushi    2
			pushi    81
			lofsa    {CLOSE}
			push    
			pushi    3
			calle    Print,  32
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_0d71
			pushi    0
			call     localproc_0a94,  0
			jmp      code_0d93
code_0d71:
			dup     
			ldi      2
			eq?     
			bnt      code_0d80
			pushi    0
			call     localproc_0aa9,  0
			jmp      code_0d93
code_0d80:
			dup     
			ldi      3
			eq?     
			bnt      code_0d93
			pushi    0
			call     localproc_0a67,  0
			ldi      2
			aTop     state
			jmp      code_0e96
code_0d93:
			toss    
			jmp      code_0d36
			jmp      code_0e96
			jmp      code_0e92
code_0d9d:
			dup     
			ldi      2
			eq?     
			bnt      code_0e92
			lsl      local20
			ldi      0
			eq?     
			bnt      code_0e0e
code_0dac:
			pushi    15
			pushi    786
			pushi    121
			pushi    70
			pushi    235
			pushi    30
			pushi    1
			pushi    81
			lofsa    {__RESTORE__}
			push    
			pushi    1
			pushi    81
			lofsa    {__RESTART__}
			push    
			pushi    2
			pushi    81
			lofsa    {___QUIT___}
			push    
			pushi    3
			calle    Print,  30
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_0de7
			pushi    #restore
			pushi    0
			lag      theGame
			send     4
			jmp      code_0e07
code_0de7:
			dup     
			ldi      2
			eq?     
			bnt      code_0df9
			pushi    #restart
			pushi    0
			lag      theGame
			send     4
			jmp      code_0e07
code_0df9:
			dup     
			ldi      3
			eq?     
			bnt      code_0e07
			ldi      1
			sag      quit
			jmp      code_0e96
code_0e07:
			toss    
			jmp      code_0dac
			jmp      code_0e96
code_0e0e:
			pushi    18
			pushi    786
			pushi    122
			pushi    70
			pushi    290
			pushi    30
			pushi    1
			pushi    81
			lofsa    {__HINTS__}
			push    
			pushi    1
			pushi    81
			lofsa    { RESTORE_}
			push    
			pushi    2
			pushi    81
			lofsa    { RESTART_}
			push    
			pushi    3
			pushi    81
			lofsa    {__QUIT__}
			push    
			pushi    4
			calle    Print,  36
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_0e5a
			pushi    0
			call     localproc_086f,  0
			lsg      quit
			ldi      1
			eq?     
			bnt      code_0e8b
			jmp      code_0e96
			jmp      code_0e8b
code_0e5a:
			dup     
			ldi      2
			eq?     
			bnt      code_0e6b
			pushi    #restore
			pushi    0
			lag      theGame
			send     4
			jmp      code_0e8b
code_0e6b:
			dup     
			ldi      3
			eq?     
			bnt      code_0e7d
			pushi    #restart
			pushi    0
			lag      theGame
			send     4
			jmp      code_0e8b
code_0e7d:
			dup     
			ldi      4
			eq?     
			bnt      code_0e8b
			ldi      1
			sag      quit
			jmp      code_0e96
code_0e8b:
			toss    
			jmp      code_0e0e
			jmp      code_0e96
code_0e92:
			toss    
			jmp      code_0cf9
code_0e96:
			ldi      1
			aTop     cycles
			jmp      code_0eac
code_0e9d:
			dup     
			ldi      4
			eq?     
			bnt      code_0eac
			pushi    #setScript
			pushi    1
			pushi    0
			pToa     client
			send     6
code_0eac:
			toss    
			ret     
		)
	)
)

(instance Flash of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 6)
			)
			(1
				(silhouette cel: (^ (silhouette cel?) 1) forceUpd:)
				(lightning cel: (^ (lightning cel?) 1) forceUpd:)
				(if (silhouette cel?)
					(soundFX number: 17 loop: 1 priority: 15 play:)
					(= state 0)
				)
				(= cycles 6)
			)
			(2
				(client setScript: 0)
			)
		)
	)
)

(instance Tulane of PicView
	(properties
		y 66
		x 98
		view 186
		loop 2
	)
)

(instance LauraBow of PicView
	(properties
		y 176
		x 100
		view 186
		loop 3
	)
)

(instance silhouette of Prop
	(properties
		y 188
		x 256
		view 186
	)
)

(instance lightning of Prop
	(properties
		y 143
		x 256
		view 186
		loop 1
	)
)

(instance nBWindow of Prop
	(properties
		x 187
		view 186
		loop 4
	)
)

(instance liquid of Actor
	(properties
		y 255
		x 133
		yStep 1
		view 187
		xStep 0
	)
)

(instance soundFX of Sound)
