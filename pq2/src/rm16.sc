;;; Sierra Script 1.0 - (do not remove this comment)
(script# 16)
(include system.sh)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm16 0
)

(local
	keithCallingCaptain
	local1
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	goingToHouston
	goingToSteelton
	local11
	local12
	local13
	local14
	local15
	local16
)
(procedure (localproc_000c)
	(if (not local16) (= local16 1) (cSound fade:))
)

(procedure (LocPrint)
	(Print &rest #at -1 15)
)

(instance agent of Actor
	(properties)
)

(instance rm16 of Room
	(properties
		picture 16
		style $0001
		picAngle 70
	)
	
	(method (init)
		(Load VIEW 0)
		(Load VIEW 1)
		(Load VIEW 20)
		(Load VIEW 77)
		(if (>= gamePhase 6)
			(Load VIEW 667)
		)
		(super init:)
		(HandsOn)
		(= perspective 70)
		(if
			(and
				(>= gamePhase 6)
				(< gamePhase 8)
			)
			(Bset fStolenCarTowed)
		)
		(= gunFireState gunPROHIBITED)
		(= gunNotNeeded 1)
		(self setLocales: regFieldKit)
		(ego
			view: (if gunDrawn 7 else 1)
			init:
		)
		(agent
			view: 77
			loop: 2
			posn: 230 110
			setPri: 8
			init:
			ignoreActors:
			illegalBits: 0
			setCycle: Forward
			cycleSpeed:
			stopUpd:
		)
		(switch prevRoomNum
			(12
				(= local4 0)
				(agent
					setLoop: 3
					setCel: 0
					posn: 244 1119
				)
				(ego
					view: 667
					loop: 1
					illegalBits: -32768
					posn: 253 139
					cycleSpeed: 1
				)
				(HandsOff)
				(egoOnThePhoneScript changeState: 6)
			)
			(17
				(ego
					posn: 67 113
					view: (if gunDrawn 6 else 0)
					setMotion: MoveTo 310 113
					setPri: -1
				)
			)
			(18
				(ego
					posn: 285 (ego y?)
					setMotion: MoveTo 10 (ego y?)
				)
			)
			(19
				(ego
					posn: 20 132
					setMotion: MoveTo 100 132
				)
			)
			(else 
				(ego
					posn: 151 188
					setMotion: MoveTo 151 114
				)
				(if (>= gamePhase 6)
					(cSound
						number: 16
						loop: -1
						play:
					)
				)
			)
		)
		(if (Btst fKeithFollows)
			((= keith (Actor new:))
				view: 20
				init:
				setMotion: Follow ego 25
				setAvoider: (Avoider new:)
				setCycle: Walk
			)
			(switch prevRoomNum
				(18
					(keith posn: (+ (ego x?) 20) (ego y?))
				)
				(19
					(keith posn: 91 146)
				)
				(17
					(keith posn: 50 112)
				)
				(else 
					(keith posn: (+ (ego x?) 10) (+ (ego y?) 20))
				)
			)
		)
		(addToPics
			add:
				pictureOnWall
				sittingPerson
				counterPerson
				talking1
				talking2
				talking3
		)
		(addToPics
			add:
				standing1
				standing2
				standing3
				standing4
				standing5
				case
				SIGN
				poster1
				poster2
				agent2
		)
		(addToPics doit:)
		(= local5 1)
	)
	
	(method (doit)
		(cond 
			((ego inRect: 175 112 225 130)
				(if (!= local4 1)
					(= local4 1)
				)
			)
			((ego inRect: 195 130 225 139)
				(if (!= local4 2)
					(= local4 2)
				)
			)
			((ego inRect: 225 139 265 145)
				(if (!= local4 3)
					(= local4 3)
				)
			)
			((ego inRect: 30 114 83 143)
				(if (!= local4 4)
					(= local4 4)
				)
			)
			((ego inRect: 63 130 116 184)
				(if (!= local4 5)
					(= local4 5)
				)
			)
			(else
				(= local4 0)
			)
		)
		(if
			(and
				(>= gamePhase 6)
				(< gamePhase 8)
				local8
			)
			(= local8 0)
			(Bset fKeithFollows)
			((= keith (Actor new:))
				view: 20
				setCycle: Walk
				setAvoider: (Avoider new:)
				init:
				setScript: keithJoinsEgoScript
			)
		)
		(if
			(and
				(Btst fKeithFollows)
				(<= (keith distanceTo: ego) 25)
				(not (Btst fToldKeithAboutRevolver))
				(ego has: iJailerRevolver)
				(== prevRoomNum 19)
			)
			(Bset fToldKeithAboutRevolver)
			(LocPrint 16 0)
			(LocPrint 16 1)
		)
		(if
			(and
				local1
				(ego inRect: 225 139 265 145)
			)
			(egoOnThePhoneScript changeState: 0)
		)
		(if
			(and
				(>= gamePhase 6)
				(!= gamePhase 13)
				(Btst fTriedToGetTicketToHouston)
			)
			(= local13 1)
		)
		(if (== gamePhase 13)
			(= local14 1)
		)
		(if (> local2 1)
			(-- local2)
		)
		(if
			(and
				(== local2 1)
				(not local15)
			)
			(= local2 0)
			(moveScript cue:)
		)
		(if (> local3 0)
			(-- local3)
		)
		(cond 
			(
				(and
					(>= (ego y?) 189)
					(<= 124 (ego x?))
					(<= (ego x?) 178)
				)
				(cSound fade:)
				(= perspective 0)
				(curRoom newRoom: 15)
			)
			(
				(and
					(< (ego y?) 115)
					(< (ego x?) 65)
				)
				(if (== (cSound number?) 16)
					(cSound fade:)
				)
				(= perspective 0)
				(curRoom newRoom: 17)
			)
			((> (ego x?) 305)
				(if (== (cSound number?) 16)
					(cSound fade:)
				)
				(= perspective 0)
				(curRoom newRoom: 18)
			)
			(
				(and
					(< (ego x?) 17)
					(< 128 (ego y?))
					(< (ego y?) 145)
				)
				(if (Btst fKeithFollows)
					(LocPrint 16 2)
				)
				(if (== (cSound number?) 16)
					(cSound fade:)
				)
				(= perspective 0)
				(curRoom newRoom: 19)
			)
		)
		(if
			(and
				(== local4 2)
				(not local5)
			)
			(= local5 1)
			(moveScript changeState: 5)
		)
		(if
			(and (== local4 3)
				(not local5)
			)
			(= local5 1)
			(moveScript changeState: 7)
		)
		(if
			(and
				(!= local4 2)
				(!= local4 3)
				local5
			)
			(if (not local7)
				(moveScript changeState: 0)
				(= local5 0)
			else
				(moveScript changeState: 2)
				(= local5 0)
			)
		)
		(cond 
			((<= (ego y?) 120)
				(if (!= (mod (ego view?) 2) 0)
					(ego view: (- (ego view?) 1))
				)
			)
			((!= (mod (ego view?) 2) 1)
				(ego view: (+ (ego view?) 1))
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(moveScript dispose:)
		(agentScript dispose:)
		(egoOnThePhoneScript dispose:)
		(keithJoinsEgoScript dispose:)
		(getTheTicketScript dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(asm
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      128
			eq?     
			bnt      code_17b9
			pushi    1
			lofsa    'display/shot,mugshot,painting'
			push    
			callk    Said,  2
			bnt      code_0960
			pushi    0
			call     localproc_000c,  0
			pushi    #has
			pushi    1
			pushi    12
			lag      ego
			send     6
			not     
			bnt      code_07a2
			pushi    #has
			pushi    1
			pushi    23
			lag      ego
			send     6
			not     
			bnt      code_07a2
			pushi    2
			pushi    16
			pushi    3
			call     LocPrint,  4
			jmp      code_17b9
code_07a2:
			lsl      local4
			ldi      5
			eq?     
			bt       code_07de
			lsl      local4
			ldi      4
			eq?     
			bt       code_07de
			lsl      local4
			ldi      3
			eq?     
			bt       code_07c2
			lsl      local4
			ldi      2
			eq?     
			bnt      code_07cc
code_07c2:
			pushi    1
			pushi    39
			callb    Btst,  2
			not     
			bt       code_07de
code_07cc:
			lsl      local4
			ldi      1
			eq?     
			bnt      code_08bd
			pushi    1
			pushi    38
			callb    Btst,  2
			not     
			bnt      code_08bd
code_07de:
			pushi    #has
			pushi    1
			pushi    12
			lag      ego
			send     6
			bnt      code_0854
			pushi    2
			pushi    1
			pushi    4
			callk    Random,  4
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_080b
			pushi    4
			pushi    16
			pushi    4
			pushi    82
			pushi    112
			call     LocPrint,  8
			jmp      code_0850
code_080b:
			dup     
			ldi      2
			eq?     
			bnt      code_0823
			pushi    4
			pushi    16
			pushi    5
			pushi    82
			pushi    112
			call     LocPrint,  8
			jmp      code_0850
code_0823:
			dup     
			ldi      3
			eq?     
			bnt      code_083b
			pushi    4
			pushi    16
			pushi    6
			pushi    82
			pushi    112
			call     LocPrint,  8
			jmp      code_0850
code_083b:
			dup     
			ldi      4
			eq?     
			bnt      code_0850
			pushi    4
			pushi    16
			pushi    7
			pushi    82
			pushi    112
			call     LocPrint,  8
code_0850:
			toss    
			jmp      code_17b9
code_0854:
			pushi    2
			pushi    1
			pushi    4
			callk    Random,  4
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_0874
			pushi    4
			pushi    16
			pushi    4
			pushi    82
			pushi    123
			call     LocPrint,  8
			jmp      code_08b9
code_0874:
			dup     
			ldi      2
			eq?     
			bnt      code_088c
			pushi    4
			pushi    16
			pushi    5
			pushi    82
			pushi    123
			call     LocPrint,  8
			jmp      code_08b9
code_088c:
			dup     
			ldi      3
			eq?     
			bnt      code_08a4
			pushi    4
			pushi    16
			pushi    6
			pushi    82
			pushi    123
			call     LocPrint,  8
			jmp      code_08b9
code_08a4:
			dup     
			ldi      4
			eq?     
			bnt      code_08b9
			pushi    4
			pushi    16
			pushi    7
			pushi    82
			pushi    123
			call     LocPrint,  8
code_08b9:
			toss    
			jmp      code_17b9
code_08bd:
			lsl      local4
			ldi      1
			eq?     
			bnt      code_08fb
			pushi    #has
			pushi    1
			pushi    12
			lag      ego
			send     6
			bnt      code_08fb
			pushi    1
			pushi    38
			callb    Btst,  2
			bnt      code_08fb
			lsg      gamePhase
			ldi      6
			ge?     
			bnt      code_08fb
			pushi    4
			pushi    16
			pushi    8
			pushi    82
			pushi    112
			call     LocPrint,  8
			pushi    2
			pushi    1
			pushi    81
			callb    SolvePuzzle,  4
			jmp      code_17b9
code_08fb:
			lsl      local4
			ldi      2
			eq?     
			bt       code_0913
			lsl      local4
			ldi      3
			eq?     
			bt       code_0913
			lsl      local4
			ldi      1
			eq?     
			bnt      code_0954
code_0913:
			pushi    1
			pushi    39
			callb    Btst,  2
			bt       code_0925
			pushi    1
			pushi    38
			callb    Btst,  2
			bnt      code_0954
code_0925:
			pushi    #has
			pushi    1
			pushi    12
			lag      ego
			send     6
			bnt      code_0943
			pushi    4
			pushi    16
			pushi    9
			pushi    82
			pushi    112
			call     LocPrint,  8
			jmp      code_17b9
code_0943:
			pushi    4
			pushi    16
			pushi    9
			pushi    82
			pushi    123
			call     LocPrint,  8
			jmp      code_17b9
code_0954:
			pushi    2
			pushi    16
			pushi    10
			call     LocPrint,  4
			jmp      code_17b9
code_0960:
			pushi    1
			lofsa    '[look]/computer,computer,display,info[<flight]'
			push    
			callk    Said,  2
			bnt      code_0a20
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      114
			lt?     
			bnt      code_0988
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      160
			gt?     
			bt       code_09a5
code_0988:
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      114
			ge?     
			bnt      code_09b1
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      210
			gt?     
			bnt      code_09b1
code_09a5:
			pushi    2
			pushi    16
			pushi    11
			call     LocPrint,  4
			jmp      code_17b9
code_09b1:
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			ldi      0
			eq?     
			not     
			bnt      code_09cc
			pushi    2
			pushi    16
			pushi    12
			call     LocPrint,  4
			jmp      code_17b9
code_09cc:
			pushi    2
			pushi    1
			pushi    4
			callk    Random,  4
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_09e7
			pushi    2
			pushi    16
			pushi    13
			call     LocPrint,  4
			jmp      code_0a1c
code_09e7:
			dup     
			ldi      2
			eq?     
			bnt      code_09fa
			pushi    2
			pushi    16
			pushi    14
			call     LocPrint,  4
			jmp      code_0a1c
code_09fa:
			dup     
			ldi      3
			eq?     
			bnt      code_0a0d
			pushi    2
			pushi    16
			pushi    15
			call     LocPrint,  4
			jmp      code_0a1c
code_0a0d:
			dup     
			ldi      4
			eq?     
			bnt      code_0a1c
			pushi    2
			pushi    16
			dup     
			call     LocPrint,  4
code_0a1c:
			toss    
			jmp      code_17b9
code_0a20:
			pushi    1
			lofsa    'look>'
			push    
			callk    Said,  2
			bnt      code_0db4
			pushi    1
			lofsa    '/bathroom,(chamber<rest)'
			push    
			callk    Said,  2
			bnt      code_0a42
			pushi    2
			pushi    16
			pushi    17
			call     LocPrint,  4
			jmp      code_17b9
code_0a42:
			pushi    1
			lofsa    '[<at,around][/chamber,airport,terminal,building,lobby]'
			push    
			callk    Said,  2
			bnt      code_0a59
			pushi    2
			pushi    16
			pushi    18
			call     LocPrint,  4
			jmp      code_17b9
code_0a59:
			pushi    1
			lofsa    '[<at,up][/ceiling]'
			push    
			callk    Said,  2
			bnt      code_0a70
			pushi    2
			pushi    16
			pushi    19
			call     LocPrint,  4
			jmp      code_17b9
code_0a70:
			pushi    1
			lofsa    '[<at,down][/floor,dirt,tile]'
			push    
			callk    Said,  2
			bnt      code_0a87
			pushi    2
			pushi    16
			pushi    20
			call     LocPrint,  4
			jmp      code_17b9
code_0a87:
			pushi    1
			lofsa    '/pane'
			push    
			callk    Said,  2
			bnt      code_0ad2
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			ldi      2
			eq?     
			bnt      code_0aac
			pushi    2
			pushi    16
			pushi    21
			call     LocPrint,  4
			jmp      code_17b9
code_0aac:
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			ldi      3
			eq?     
			bnt      code_0ac6
			pushi    2
			pushi    16
			pushi    22
			call     LocPrint,  4
			jmp      code_17b9
code_0ac6:
			pushi    2
			pushi    16
			pushi    23
			call     LocPrint,  4
			jmp      code_17b9
code_0ad2:
			pushi    1
			lofsa    '/flyer,ad'
			push    
			callk    Said,  2
			bnt      code_0b58
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      114
			lt?     
			bnt      code_0afa
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      160
			gt?     
			bt       code_0b25
code_0afa:
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      114
			ge?     
			bnt      code_0b17
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      210
			gt?     
			bt       code_0b25
code_0b17:
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      110
			lt?     
			bnt      code_0b31
code_0b25:
			pushi    2
			pushi    16
			pushi    24
			call     LocPrint,  4
			jmp      code_17b9
code_0b31:
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			ldi      0
			eq?     
			not     
			bnt      code_0b4c
			pushi    2
			pushi    16
			pushi    25
			call     LocPrint,  4
			jmp      code_17b9
code_0b4c:
			pushi    2
			pushi    16
			pushi    26
			call     LocPrint,  4
			jmp      code_17b9
code_0b58:
			pushi    1
			lofsa    '/briefcase,bag,baggage'
			push    
			callk    Said,  2
			bnt      code_0b83
			lsl      local4
			ldi      4
			eq?     
			bnt      code_0b77
			pushi    2
			pushi    16
			pushi    27
			call     LocPrint,  4
			jmp      code_17b9
code_0b77:
			pushi    2
			pushi    16
			pushi    28
			call     LocPrint,  4
			jmp      code_17b9
code_0b83:
			pushi    1
			lofsa    '/counter,iguana'
			push    
			callk    Said,  2
			bnt      code_0b9a
			pushi    2
			pushi    16
			pushi    29
			call     LocPrint,  4
			jmp      code_17b9
code_0b9a:
			pushi    1
			lofsa    '/dude,broad,men,women,crowd,passenger'
			push    
			callk    Said,  2
			bnt      code_0c00
			lsl      local4
			dup     
			ldi      1
			eq?     
			bnt      code_0bba
			pushi    2
			pushi    16
			pushi    30
			call     LocPrint,  4
			jmp      code_0bfc
code_0bba:
			dup     
			ldi      3
			eq?     
			bnt      code_0bcd
			pushi    2
			pushi    16
			pushi    31
			call     LocPrint,  4
			jmp      code_0bfc
code_0bcd:
			dup     
			ldi      2
			eq?     
			bnt      code_0be0
			pushi    2
			pushi    16
			pushi    31
			call     LocPrint,  4
			jmp      code_0bfc
code_0be0:
			dup     
			ldi      4
			eq?     
			bnt      code_0bf3
			pushi    2
			pushi    16
			pushi    32
			call     LocPrint,  4
			jmp      code_0bfc
code_0bf3:
			pushi    2
			pushi    16
			pushi    33
			call     LocPrint,  4
code_0bfc:
			toss    
			jmp      code_17b9
code_0c00:
			pushi    1
			lofsa    '/agent'
			push    
			callk    Said,  2
			bnt      code_0c3b
			lsl      local4
			ldi      2
			eq?     
			bt       code_0c23
			lsl      local4
			ldi      3
			eq?     
			bt       code_0c23
			lsl      local4
			ldi      1
			eq?     
			bnt      code_0c2f
code_0c23:
			pushi    2
			pushi    16
			pushi    31
			call     LocPrint,  4
			jmp      code_17b9
code_0c2f:
			pushi    2
			pushi    16
			pushi    28
			call     LocPrint,  4
			jmp      code_17b9
code_0c3b:
			pushi    1
			lofsa    '<below/bench'
			push    
			callk    Said,  2
			bnt      code_0c66
			lsl      local4
			ldi      4
			eq?     
			bnt      code_0c5a
			pushi    2
			pushi    16
			pushi    34
			call     LocPrint,  4
			jmp      code_17b9
code_0c5a:
			pushi    2
			pushi    16
			pushi    28
			call     LocPrint,  4
			jmp      code_17b9
code_0c66:
			pushi    1
			lofsa    '/bench'
			push    
			callk    Said,  2
			bnt      code_0c91
			lsl      local4
			ldi      4
			eq?     
			bnt      code_0c85
			pushi    2
			pushi    16
			pushi    35
			call     LocPrint,  4
			jmp      code_17b9
code_0c85:
			pushi    2
			pushi    16
			pushi    28
			call     LocPrint,  4
			jmp      code_17b9
code_0c91:
			pushi    1
			lofsa    '/wall'
			push    
			callk    Said,  2
			bnt      code_0ca8
			pushi    2
			pushi    16
			pushi    36
			call     LocPrint,  4
			jmp      code_17b9
code_0ca8:
			pushi    1
			lofsa    '/counter'
			push    
			callk    Said,  2
			bnt      code_0cbf
			pushi    2
			pushi    16
			pushi    37
			call     LocPrint,  4
			jmp      code_17b9
code_0cbf:
			pushi    1
			lofsa    '/painting'
			push    
			callk    Said,  2
			bnt      code_0cd6
			pushi    2
			pushi    16
			pushi    38
			call     LocPrint,  4
			jmp      code_17b9
code_0cd6:
			pushi    1
			lofsa    '/ass,cigarette'
			push    
			callk    Said,  2
			bnt      code_0d01
			lsl      local4
			ldi      4
			eq?     
			bnt      code_0cf5
			pushi    2
			pushi    16
			pushi    39
			call     LocPrint,  4
			jmp      code_17b9
code_0cf5:
			pushi    2
			pushi    16
			pushi    28
			call     LocPrint,  4
			jmp      code_17b9
code_0d01:
			pushi    1
			lofsa    '/rope'
			push    
			callk    Said,  2
			bnt      code_0d18
			pushi    2
			pushi    16
			pushi    40
			call     LocPrint,  4
			jmp      code_17b9
code_0d18:
			pushi    1
			lofsa    '/ashtray,(tray<ash)'
			push    
			callk    Said,  2
			bnt      code_0d43
			lsl      local4
			ldi      4
			eq?     
			bnt      code_0d37
			pushi    2
			pushi    16
			pushi    41
			call     LocPrint,  4
			jmp      code_17b9
code_0d37:
			pushi    2
			pushi    16
			pushi    28
			call     LocPrint,  4
			jmp      code_17b9
code_0d43:
			pushi    1
			lofsa    '/list,flight'
			push    
			callk    Said,  2
			bnt      code_17b9
			lsl      local4
			ldi      0
			eq?     
			bt       code_0d66
			lsl      local4
			ldi      4
			eq?     
			bt       code_0d66
			lsl      local4
			ldi      5
			eq?     
			bnt      code_0d72
code_0d66:
			pushi    2
			pushi    16
			pushi    42
			call     LocPrint,  4
			jmp      code_17b9
code_0d72:
			lsl      local4
			ldi      1
			eq?     
			bnt      code_0d83
			pushi    1
			pushi    38
			callb    Btst,  2
			bt       code_0d9c
code_0d83:
			lsl      local4
			ldi      2
			eq?     
			bt       code_0d93
			lsl      local4
			ldi      3
			eq?     
			bnt      code_0da8
code_0d93:
			pushi    1
			pushi    39
			callb    Btst,  2
			bnt      code_0da8
code_0d9c:
			pushi    #changeState
			pushi    1
			pushi    1
			lofsa    agentScript
			send     6
			jmp      code_17b9
code_0da8:
			pushi    2
			pushi    16
			pushi    43
			call     LocPrint,  4
			jmp      code_17b9
code_0db4:
			pushi    1
			lofsa    'kiss'
			push    
			callk    Said,  2
			bnt      code_0dcb
			pushi    2
			pushi    16
			pushi    44
			call     LocPrint,  4
			jmp      code_17b9
code_0dcb:
			pushi    1
			lofsa    '/hello'
			push    
			callk    Said,  2
			bnt      code_0de2
			pushi    2
			pushi    16
			pushi    45
			call     LocPrint,  4
			jmp      code_17b9
code_0de2:
			pushi    1
			lofsa    '/bye'
			push    
			callk    Said,  2
			bnt      code_0df9
			pushi    2
			pushi    16
			pushi    46
			call     LocPrint,  4
			jmp      code_17b9
code_0df9:
			pushi    1
			lofsa    'affirmative'
			push    
			callk    Said,  2
			bnt      code_0e10
			pushi    2
			pushi    16
			pushi    47
			call     LocPrint,  4
			jmp      code_17b9
code_0e10:
			pushi    1
			lofsa    'n'
			push    
			callk    Said,  2
			bnt      code_0e27
			pushi    2
			pushi    16
			pushi    48
			call     LocPrint,  4
			jmp      code_17b9
code_0e27:
			pushi    1
			lofsa    'frisk,kill,beat,fire'
			push    
			callk    Said,  2
			bnt      code_0e3e
			pushi    2
			pushi    16
			pushi    49
			call     LocPrint,  4
			jmp      code_17b9
code_0e3e:
			pushi    1
			lofsa    'get,eat,pick[<up]/candy,bar'
			push    
			callk    Said,  2
			bnt      code_0e55
			pushi    2
			pushi    16
			pushi    50
			call     LocPrint,  4
			jmp      code_17b9
code_0e55:
			pushi    1
			lofsa    'gave,get/briefcase'
			push    
			callk    Said,  2
			bnt      code_0ea7
			pushi    #owner
			pushi    0
			pushi    #at
			pushi    1
			pushi    10
			lag      inventory
			send     6
			send     4
			push    
			ldi      16
			eq?     
			bnt      code_0e82
			pushi    2
			pushi    16
			pushi    51
			call     LocPrint,  4
			jmp      code_17b9
code_0e82:
			pushi    #has
			pushi    1
			pushi    10
			lag      ego
			send     6
			bnt      code_0e9b
			pushi    2
			pushi    16
			pushi    52
			call     LocPrint,  4
			jmp      code_17b9
code_0e9b:
			pushi    2
			pushi    16
			pushi    53
			call     LocPrint,  4
			jmp      code_17b9
code_0ea7:
			pushi    1
			lofsa    'get,pick[<up]/baggage,briefcase,bag'
			push    
			callk    Said,  2
			bnt      code_0edf
			pushi    #inRect
			pushi    4
			pushi    30
			pushi    114
			pushi    83
			pushi    143
			lag      ego
			send     12
			bnt      code_0ed3
			pushi    2
			pushi    16
			pushi    54
			call     LocPrint,  4
			jmp      code_17b9
code_0ed3:
			pushi    2
			pushi    16
			pushi    55
			call     LocPrint,  4
			jmp      code_17b9
code_0edf:
			pushi    1
			lofsa    'sat[<down]'
			push    
			callk    Said,  2
			bnt      code_0ef6
			pushi    2
			pushi    16
			pushi    56
			call     LocPrint,  4
			jmp      code_17b9
code_0ef6:
			pushi    1
			lofsa    'get/bench'
			push    
			callk    Said,  2
			bnt      code_0f21
			lsl      local4
			ldi      4
			eq?     
			bnt      code_0f15
			pushi    2
			pushi    16
			pushi    34
			call     LocPrint,  4
			jmp      code_17b9
code_0f15:
			pushi    2
			pushi    16
			pushi    57
			call     LocPrint,  4
			jmp      code_17b9
code_0f21:
			pushi    1
			lofsa    'hoist,move/bench'
			push    
			callk    Said,  2
			bnt      code_0f4c
			lsl      local4
			ldi      4
			eq?     
			bnt      code_0f40
			pushi    2
			pushi    16
			pushi    34
			call     LocPrint,  4
			jmp      code_17b9
code_0f40:
			pushi    2
			pushi    16
			pushi    57
			call     LocPrint,  4
			jmp      code_17b9
code_0f4c:
			pushi    1
			lofsa    'get/painting'
			push    
			callk    Said,  2
			bnt      code_0f63
			pushi    2
			pushi    16
			pushi    58
			call     LocPrint,  4
			jmp      code_17b9
code_0f63:
			pushi    1
			lofsa    'eat,cigarette,get/ass,cigarette'
			push    
			callk    Said,  2
			bnt      code_0f8e
			lsl      local4
			ldi      4
			eq?     
			bnt      code_0f82
			pushi    2
			pushi    16
			pushi    59
			call     LocPrint,  4
			jmp      code_17b9
code_0f82:
			pushi    2
			pushi    16
			pushi    60
			call     LocPrint,  4
			jmp      code_17b9
code_0f8e:
			pushi    1
			lofsa    'chat>'
			push    
			callk    Said,  2
			bnt      code_121e
			pushi    1
			lofsa    '/dude,person'
			push    
			callk    Said,  2
			bnt      code_1044
			lsl      local4
			dup     
			ldi      1
			eq?     
			bnt      code_0fbe
			pushi    0
			call     localproc_000c,  0
			pushi    2
			pushi    16
			pushi    61
			call     LocPrint,  4
			jmp      code_1040
code_0fbe:
			dup     
			ldi      2
			eq?     
			bnt      code_0fd6
			pushi    0
			call     localproc_000c,  0
			pushi    2
			pushi    16
			pushi    62
			call     LocPrint,  4
			jmp      code_1040
code_0fd6:
			dup     
			ldi      3
			eq?     
			bnt      code_0fee
			pushi    0
			call     localproc_000c,  0
			pushi    2
			pushi    16
			pushi    62
			call     LocPrint,  4
			jmp      code_1040
code_0fee:
			dup     
			ldi      0
			eq?     
			bnt      code_1001
			pushi    2
			pushi    16
			pushi    63
			call     LocPrint,  4
			jmp      code_1040
code_1001:
			pushi    2
			pushi    1
			pushi    3
			callk    Random,  4
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_101c
			pushi    2
			pushi    16
			pushi    64
			call     LocPrint,  4
			jmp      code_103f
code_101c:
			dup     
			ldi      2
			eq?     
			bnt      code_102f
			pushi    2
			pushi    16
			pushi    65
			call     LocPrint,  4
			jmp      code_103f
code_102f:
			dup     
			ldi      3
			eq?     
			bnt      code_103f
			pushi    2
			pushi    16
			pushi    66
			call     LocPrint,  4
code_103f:
			toss    
code_1040:
			toss    
			jmp      code_17b9
code_1044:
			pushi    1
			lofsa    '/customer,passenger'
			push    
			callk    Said,  2
			bnt      code_106f
			lsl      local4
			ldi      1
			eq?     
			bnt      code_1063
			pushi    2
			pushi    16
			pushi    67
			call     LocPrint,  4
			jmp      code_17b9
code_1063:
			pushi    2
			pushi    16
			pushi    68
			call     LocPrint,  4
			jmp      code_17b9
code_106f:
			pushi    1
			lofsa    '/men,crowd'
			push    
			callk    Said,  2
			bnt      code_10a8
			pushi    #inRect
			pushi    4
			pushi    63
			pushi    130
			pushi    116
			pushi    184
			lag      ego
			send     12
			bnt      code_109c
			pushi    2
			pushi    16
			pushi    69
			call     LocPrint,  4
			jmp      code_17b9
code_109c:
			pushi    2
			pushi    16
			pushi    70
			call     LocPrint,  4
			jmp      code_17b9
code_10a8:
			pushi    1
			lofsa    '/broad'
			push    
			callk    Said,  2
			bnt      code_117a
			lsl      local4
			ldi      0
			eq?     
			bnt      code_1135
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      225
			gt?     
			bnt      code_10d8
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      114
			lt?     
			bt       code_10e7
code_10d8:
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      141
			lt?     
			bnt      code_10f3
code_10e7:
			pushi    2
			pushi    16
			pushi    63
			call     LocPrint,  4
			jmp      code_17b9
code_10f3:
			pushi    2
			pushi    1
			pushi    3
			callk    Random,  4
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_110e
			pushi    2
			pushi    16
			pushi    64
			call     LocPrint,  4
			jmp      code_1131
code_110e:
			dup     
			ldi      2
			eq?     
			bnt      code_1121
			pushi    2
			pushi    16
			pushi    65
			call     LocPrint,  4
			jmp      code_1131
code_1121:
			dup     
			ldi      3
			eq?     
			bnt      code_1131
			pushi    2
			pushi    16
			pushi    66
			call     LocPrint,  4
code_1131:
			toss    
			jmp      code_17b9
code_1135:
			pushi    1
			pushi    38
			callb    Btst,  2
			not     
			bnt      code_1150
			pushi    0
			call     localproc_000c,  0
			pushi    2
			pushi    16
			pushi    71
			call     LocPrint,  4
			jmp      code_17b9
code_1150:
			lsl      local4
			ldi      1
			eq?     
			bnt      code_1169
			pushi    0
			call     localproc_000c,  0
			pushi    2
			pushi    16
			pushi    72
			call     LocPrint,  4
			jmp      code_17b9
code_1169:
			pushi    0
			call     localproc_000c,  0
			pushi    2
			pushi    16
			pushi    73
			call     LocPrint,  4
			jmp      code_17b9
code_117a:
			pushi    1
			lofsa    '/agent'
			push    
			callk    Said,  2
			bnt      code_17b9
			lsl      local4
			dup     
			ldi      1
			eq?     
			bnt      code_11b4
			pushi    1
			pushi    38
			callb    Btst,  2
			bnt      code_11a3
			pushi    2
			pushi    16
			pushi    72
			call     LocPrint,  4
			jmp      code_121a
code_11a3:
			pushi    0
			call     localproc_000c,  0
			pushi    2
			pushi    16
			pushi    74
			call     LocPrint,  4
			jmp      code_121a
code_11b4:
			dup     
			ldi      2
			eq?     
			bnt      code_11cc
			pushi    0
			call     localproc_000c,  0
			pushi    2
			pushi    16
			pushi    62
			call     LocPrint,  4
			jmp      code_121a
code_11cc:
			dup     
			ldi      3
			eq?     
			bnt      code_11e4
			pushi    0
			call     localproc_000c,  0
			pushi    2
			pushi    16
			pushi    62
			call     LocPrint,  4
			jmp      code_121a
code_11e4:
			dup     
			ldi      0
			eq?     
			bnt      code_11f7
			pushi    2
			pushi    16
			pushi    75
			call     LocPrint,  4
			jmp      code_121a
code_11f7:
			dup     
			ldi      5
			eq?     
			bnt      code_120a
			pushi    2
			pushi    16
			pushi    75
			call     LocPrint,  4
			jmp      code_121a
code_120a:
			dup     
			ldi      4
			eq?     
			bnt      code_121a
			pushi    2
			pushi    16
			pushi    75
			call     LocPrint,  4
code_121a:
			toss    
			jmp      code_17b9
code_121e:
			pushi    1
			lofsa    'flash,display/badge'
			push    
			callk    Said,  2
			bnt      code_12b1
			pushi    #has
			pushi    1
			pushi    7
			lag      ego
			send     6
			not     
			bnt      code_1243
			pushi    2
			pushi    16
			pushi    76
			call     LocPrint,  4
			jmp      code_17b9
code_1243:
			lsl      local4
			ldi      4
			eq?     
			bt       code_1253
			lsl      local4
			ldi      5
			eq?     
			bnt      code_125f
code_1253:
			pushi    2
			pushi    16
			pushi    77
			call     LocPrint,  4
			jmp      code_17b9
code_125f:
			lsl      local4
			ldi      1
			eq?     
			bnt      code_127e
			pushi    0
			call     localproc_000c,  0
			pushi    1
			pushi    38
			callb    Bset,  2
			pushi    #changeState
			pushi    1
			pushi    0
			lofsa    agentScript
			send     6
			jmp      code_17b9
code_127e:
			lsl      local4
			ldi      2
			eq?     
			bt       code_128e
			lsl      local4
			ldi      3
			eq?     
			bnt      code_12a5
code_128e:
			pushi    0
			call     localproc_000c,  0
			pushi    1
			pushi    39
			callb    Bset,  2
			pushi    #changeState
			pushi    1
			pushi    0
			lofsa    agentScript
			send     6
			jmp      code_17b9
code_12a5:
			pushi    2
			pushi    16
			pushi    78
			call     LocPrint,  4
			jmp      code_17b9
code_12b1:
			pushi    1
			lofsa    '[*]/bains'
			push    
			callk    Said,  2
			bnt      code_12c8
			pushi    2
			pushi    16
			pushi    79
			call     LocPrint,  4
			jmp      code_17b9
code_12c8:
			pushi    1
			lofsa    'ask/departure,flight'
			push    
			callk    Said,  2
			bt       code_12f4
			pushi    1
			lofsa    'is<when/flight<next/houston'
			push    
			callk    Said,  2
			bt       code_12f4
			pushi    1
			lofsa    'fly,go/houston'
			push    
			callk    Said,  2
			bt       code_12f4
			pushi    1
			lofsa    '/flight,departure<next'
			push    
			callk    Said,  2
			bnt      code_1376
code_12f4:
			pushi    1
			pushi    34
			callb    Btst,  2
			bnt      code_1322
			lsl      local4
			ldi      1
			eq?     
			bt       code_1315
			lsl      local4
			ldi      2
			eq?     
			bt       code_1315
			lsl      local4
			ldi      3
			eq?     
			bnt      code_1322
code_1315:
			pushi    #changeState
			pushi    1
			pushi    4
			lofsa    agentScript
			send     6
			jmp      code_17b9
code_1322:
			pushi    2
			pushi    1
			pushi    4
			callk    Random,  4
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_133d
			pushi    2
			pushi    16
			pushi    13
			call     LocPrint,  4
			jmp      code_1372
code_133d:
			dup     
			ldi      2
			eq?     
			bnt      code_1350
			pushi    2
			pushi    16
			pushi    14
			call     LocPrint,  4
			jmp      code_1372
code_1350:
			dup     
			ldi      3
			eq?     
			bnt      code_1363
			pushi    2
			pushi    16
			pushi    15
			call     LocPrint,  4
			jmp      code_1372
code_1363:
			dup     
			ldi      4
			eq?     
			bnt      code_1372
			pushi    2
			pushi    16
			dup     
			call     LocPrint,  4
code_1372:
			toss    
			jmp      code_17b9
code_1376:
			pushi    1
			lofsa    '[get,buy]/ticket'
			push    
			callk    Said,  2
			bt       code_138c
			pushi    1
			lofsa    'gave/i/ticket'
			push    
			callk    Said,  2
			bnt      code_150b
code_138c:
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
			ldi      100
			sal      local3
			lsl      local4
			ldi      0
			eq?     
			bt       code_13b0
			lsl      local4
			ldi      4
			eq?     
			bt       code_13b0
			lsl      local4
			ldi      5
			eq?     
			bnt      code_140c
code_13b0:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    2
			pushi    1
			pushi    4
			callk    Random,  4
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_13d3
			pushi    2
			pushi    16
			pushi    13
			call     LocPrint,  4
			jmp      code_1408
code_13d3:
			dup     
			ldi      2
			eq?     
			bnt      code_13e6
			pushi    2
			pushi    16
			pushi    14
			call     LocPrint,  4
			jmp      code_1408
code_13e6:
			dup     
			ldi      3
			eq?     
			bnt      code_13f9
			pushi    2
			pushi    16
			pushi    15
			call     LocPrint,  4
			jmp      code_1408
code_13f9:
			dup     
			ldi      4
			eq?     
			bnt      code_1408
			pushi    2
			pushi    16
			dup     
			call     LocPrint,  4
code_1408:
			toss    
			jmp      code_17b9
code_140c:
			lsl      local4
			ldi      1
			eq?     
			bnt      code_1437
			pushi    1
			pushi    38
			callb    Btst,  2
			not     
			bnt      code_1437
			pushi    0
			call     localproc_000c,  0
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    2
			pushi    16
			pushi    74
			call     LocPrint,  4
			jmp      code_17b9
code_1437:
			pushi    #inRect
			pushi    4
			pushi    225
			pushi    139
			pushi    265
			pushi    145
			lag      ego
			send     12
			bnt      code_1468
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    0
			call     localproc_000c,  0
			pushi    2
			pushi    16
			pushi    80
			call     LocPrint,  4
			jmp      code_17b9
code_1468:
			pushi    1
			lofsa    '//houston'
			push    
			callk    Said,  2
			bnt      code_1484
			ldi      1
			sal      goingToHouston
			pushi    #changeState
			pushi    1
			pushi    5
			lofsa    agentScript
			send     6
			jmp      code_17b9
code_1484:
			pushi    1
			lofsa    '//steelton'
			push    
			callk    Said,  2
			bnt      code_14a0
			ldi      1
			sal      goingToSteelton
			pushi    #changeState
			pushi    1
			pushi    6
			lofsa    agentScript
			send     6
			jmp      code_17b9
code_14a0:
			pushi    1
			lofsa    '//beirut'
			push    
			callk    Said,  2
			bnt      code_14bb
			ldi      1
			sal      local11
			pushi    2
			pushi    16
			pushi    81
			call     LocPrint,  4
			jmp      code_17b9
code_14bb:
			pushi    1
			lofsa    '//coarsegold'
			push    
			callk    Said,  2
			bnt      code_14e0
			ldi      1
			sal      local12
			pushi    2
			pushi    16
			pushi    82
			call     LocPrint,  4
			pushi    #changeState
			pushi    1
			pushi    7
			lofsa    agentScript
			send     6
			jmp      code_17b9
code_14e0:
			pushi    1
			lofsa    '//*'
			push    
			callk    Said,  2
			bnt      code_14f7
			pushi    2
			pushi    16
			pushi    83
			call     LocPrint,  4
			jmp      code_17b9
code_14f7:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    2
			pushi    16
			pushi    84
			call     LocPrint,  4
			jmp      code_17b9
code_150b:
			lsl      local3
			ldi      0
			gt?     
			bnt      code_15ec
			pushi    #inRect
			pushi    4
			pushi    175
			pushi    112
			pushi    225
			pushi    130
			lag      ego
			send     12
			bt       code_155a
			pushi    #inRect
			pushi    4
			pushi    195
			pushi    130
			pushi    225
			pushi    139
			lag      ego
			send     12
			bt       code_155a
			pushi    #inRect
			pushi    4
			pushi    225
			pushi    139
			pushi    265
			pushi    145
			lag      ego
			send     12
			bnt      code_15ec
code_155a:
			pushi    1
			lofsa    '/houston'
			push    
			callk    Said,  2
			bnt      code_1576
			ldi      1
			sal      goingToHouston
			pushi    #changeState
			pushi    1
			pushi    5
			lofsa    agentScript
			send     6
			jmp      code_15e6
code_1576:
			pushi    1
			lofsa    '/steelton'
			push    
			callk    Said,  2
			bnt      code_1592
			ldi      1
			sal      goingToSteelton
			pushi    #changeState
			pushi    1
			pushi    6
			lofsa    agentScript
			send     6
			jmp      code_15e6
code_1592:
			pushi    1
			lofsa    '/beirut'
			push    
			callk    Said,  2
			bnt      code_15ad
			ldi      1
			sal      local11
			pushi    2
			pushi    16
			pushi    81
			call     LocPrint,  4
			jmp      code_15e6
code_15ad:
			pushi    1
			lofsa    '/coarsegold'
			push    
			callk    Said,  2
			bnt      code_15d2
			ldi      1
			sal      local12
			pushi    2
			pushi    16
			pushi    82
			call     LocPrint,  4
			pushi    #changeState
			pushi    1
			pushi    7
			lofsa    agentScript
			send     6
			jmp      code_15e6
code_15d2:
			pushi    1
			lofsa    '/america'
			push    
			callk    Said,  2
			bnt      code_15ec
			pushi    2
			pushi    16
			pushi    83
			call     LocPrint,  4
code_15e6:
			bnt      code_15ec
			jmp      code_17b9
code_15ec:
			pushi    1
			lofsa    'ask/ticket'
			push    
			callk    Said,  2
			bt       code_1602
			pushi    1
			lofsa    'ask/[agent,dude,broad]/ticket'
			push    
			callk    Said,  2
			bnt      code_1613
code_1602:
			pushi    0
			call     localproc_000c,  0
			pushi    2
			pushi    16
			pushi    84
			call     LocPrint,  4
			jmp      code_17b9
code_1613:
			pushi    1
			lofsa    'pay[/agent,dude,broad,ticket,flight]'
			push    
			callk    Said,  2
			bnt      code_162a
			pushi    2
			pushi    16
			pushi    85
			call     LocPrint,  4
			jmp      code_17b9
code_162a:
			pushi    1
			lofsa    'ask,dial,use,get,pick[<up]/phone'
			push    
			callk    Said,  2
			bt       code_1640
			pushi    1
			lofsa    'ask,make/call,phone'
			push    
			callk    Said,  2
			bnt      code_1712
code_1640:
			pushi    #inRect
			pushi    4
			pushi    175
			pushi    112
			pushi    225
			pushi    130
			lag      ego
			send     12
			bnt      code_167d
			pushi    0
			call     localproc_000c,  0
			pushi    1
			pushi    38
			callb    Btst,  2
			bnt      code_1671
			pushi    2
			pushi    16
			pushi    86
			call     LocPrint,  4
			jmp      code_17b9
code_1671:
			pushi    2
			pushi    16
			pushi    87
			call     LocPrint,  4
			jmp      code_17b9
code_167d:
			pushi    #inRect
			pushi    4
			pushi    195
			pushi    130
			pushi    225
			pushi    139
			lag      ego
			send     12
			bnt      code_16bf
			pushi    0
			call     localproc_000c,  0
			pushi    1
			pushi    39
			callb    Btst,  2
			bnt      code_16b3
			ldi      1
			sal      local1
			pushi    2
			pushi    16
			pushi    88
			call     LocPrint,  4
			jmp      code_17b9
code_16b3:
			pushi    2
			pushi    16
			pushi    87
			call     LocPrint,  4
			jmp      code_17b9
code_16bf:
			pushi    #inRect
			pushi    4
			pushi    225
			pushi    139
			pushi    265
			pushi    145
			lag      ego
			send     12
			bnt      code_1706
			pushi    0
			call     localproc_000c,  0
			pushi    1
			pushi    39
			callb    Btst,  2
			bnt      code_16fa
			pushi    2
			pushi    16
			pushi    89
			call     LocPrint,  4
			pushi    #changeState
			pushi    1
			pushi    0
			lofsa    egoOnThePhoneScript
			send     6
			jmp      code_17b9
code_16fa:
			pushi    2
			pushi    16
			pushi    87
			call     LocPrint,  4
			jmp      code_17b9
code_1706:
			pushi    2
			pushi    16
			pushi    90
			call     LocPrint,  4
			jmp      code_17b9
code_1712:
			pushi    1
			lofsa    'ask,get,gave,look,see,display>'
			push    
			callk    Said,  2
			bt       code_1728
			pushi    1
			lofsa    'have<do>'
			push    
			callk    Said,  2
			bnt      code_17b9
code_1728:
			pushi    1
			lofsa    '/flight,list,passenger,info'
			push    
			callk    Said,  2
			bt       code_173e
			pushi    1
			lofsa    '//flight,list,passenger,info'
			push    
			callk    Said,  2
			bnt      code_17b9
code_173e:
			lsl      local4
			ldi      0
			eq?     
			bt       code_1756
			lsl      local4
			ldi      4
			eq?     
			bt       code_1756
			lsl      local4
			ldi      5
			eq?     
			bnt      code_176a
code_1756:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    2
			pushi    16
			pushi    42
			call     LocPrint,  4
			jmp      code_17b9
code_176a:
			lsl      local4
			ldi      1
			eq?     
			bnt      code_177b
			pushi    1
			pushi    38
			callb    Btst,  2
			bt       code_1794
code_177b:
			lsl      local4
			ldi      2
			eq?     
			bt       code_178b
			lsl      local4
			ldi      3
			eq?     
			bnt      code_17a8
code_178b:
			pushi    1
			pushi    39
			callb    Btst,  2
			bnt      code_17a8
code_1794:
			pushi    #changeState
			pushi    1
			pushi    1
			lofsa    agentScript
			send     6
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_17b9
code_17a8:
			pushi    2
			pushi    16
			pushi    43
			call     LocPrint,  4
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
code_17b9:
			toss    
			ret     
		)
	)
)

(instance moveScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(agent
					startUpd:
					setLoop: 3
					setCel: -1
					setMotion: MoveTo (Random 245 260) 110 self
				)
			)
			(1
				(agent setLoop: 4 setCel: 0 stopUpd:)
				(= local2 (Random 80 150))
			)
			(2
				(agent
					startUpd:
					setLoop: 2
					setCel: -1
					setMotion: MoveTo 230 110 self
				)
			)
			(3
				(agent stopUpd:)
				(= local2 (Random 80 150))
			)
			(4
				(self changeState: 0)
			)
			(5
				(agent
					startUpd:
					setLoop: 2
					setMotion: MoveTo 230 110 self
				)
			)
			(6
				(moveScript changeState: 9)
			)
			(7
				(agent
					startUpd:
					setLoop: (if (< (ego x?) (agent x?)) 2 else 3)
					setMotion: MoveTo (ego x?) 110 self
				)
			)
			(8
				(agent setLoop: 4 setCel: 0)
				(= local7 1)
				(self cue:)
			)
			(9
				(agent stopUpd:)
				(cond 
					((== prevRoomNum 12)
						(= local6 1)
					)
					((not local6)
						(LocPrint 16 73 83)
						(localproc_000c)
						(= local6 1)
					)
				)
			)
		)
	)
)

(instance agentScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LocPrint 16 72)
			)
			(1
				(LocPrint 16 91)
				(switch local4
					(2
						(LocPrint 16 92)
					)
					(3
						(LocPrint 16 92)
					)
					(1
						(if
							(or
								(< gamePhase 6)
								(== gamePhase 13)
								(Btst fTriedToGetTicketToHouston)
							)
							(LocPrint 16 92)
						else
							(SolvePuzzle 3 fLookedAtPassengerList)
							(LocPrint 16 93)
							(LocPrint 16 94)
							(LocPrint 16 95)
							(self cue:)
						)
					)
				)
			)
			(2
				(if (!= (cSound state?) 3)
					(cSound number: 29 loop: -1 play:)
				)
				(LocPrint 16 96 83)
				(Bset fTriedToGetTicketToHouston)
				(self cue:)
			)
			(3
				(if (== currentCar 13)
					(= local8 1)
				)
			)
			(4
				(LocPrint 16 97 25 4)
				(LocPrint 16 98)
			)
			(5
				(cond 
					(
						(and
							(ego has: iPlaneTicket)
							(== airplaneToSteelton 0)
						)
						(LocPrint 16 99)
					)
					((Btst fTriedToGoToHouston)
						(LocPrint 16 100)
					)
					((Btst fHoustonAuthorized)
						(if (not (ego has: iPlaneTicket))
							(LocPrint 16 101)
							(LocPrint 16 102)
						else
							(LocPrint 16 103)
						)
						(= goingToHouston 0)
						(ego get: iPlaneTicket)
						(= airplaneToSteelton 0)
						(Bclr fHoustonAuthorized)
						(HandsOn)
					)
					(else
						(LocPrint 16 104)
						(LocPrint 16 105)
						(HandsOn)
						(if
							(and
								(cast contains: keith)
								(not (Btst fHoustonAuthorized))
								local13
							)
							(keith setScript: getTheTicketScript)
						)
					)
				)
			)
			(6
				(cond 
					((== airplaneToSteelton 1)
						(LocPrint 16 106)
					)
					((Btst fSteeltonAuthorized)
						(if (not (ego has: iPlaneTicket))
							(LocPrint 16 107)
							(LocPrint 16 102)
							(if (ego has: iFieldKit)
								(LocPrint 16 108)
							)
						else
							(LocPrint 16 109)
						)
						(= goingToSteelton 0)
						(SolvePuzzle 3 fGotTicketToSteelton)
						(ego get: iPlaneTicket)
						(= airplaneToSteelton 1)
						(if (IsObject theFieldKit)
							(theFieldKit dispose:)
							(= fieldKitOpen 0)
						)
						(ego put: iFieldKit 16)
						(HandsOn)
					)
					(else
						(LocPrint 16 110)
						(LocPrint 16 111)
						(HandsOn)
						(if
							(and
								(cast contains: keith)
								(not (Btst fSteeltonAuthorized))
								local14
							)
							(keith setScript: getTheTicketScript)
						)
					)
				)
			)
			(7
				(LocPrint 16 112)
			)
		)
	)
)

(instance keithJoinsEgoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(keith
					posn: 165 209
					setMotion: MoveTo 168 180 self
				)
			)
			(1
				(LocPrint 16 113 83 25 3)
				(keith setMotion: MoveTo 168 151 self)
			)
			(2
				(keith
					loop: 0
					cel: 2
				)
				(if (< (ego y?) 115)
					(ego setMotion: MoveTo 155 114 self)
				else
					(self cue:)
				)
			)
			(3
				(ego setMotion: MoveTo (+ (keith x?) 20) (keith y?) self)
			)
			(4
				(ego loop: 1)
				(switch global169
					(1
						(= global169 3)
						(LocPrint 16 114 83)
						(LocPrint 16 115)
						(LocPrint 16 116)
					)
					(2
						(LocPrint 16 117 83)
						(LocPrint 16 116)
					)
					(3 (LocPrint 16 118 83))
				)
				(= global169 0)
				(self cue:)
			)
			(5
				(LocPrint 16 119 83)
				(LocPrint 16 120)
				(keith setMotion: Follow ego 25)
				(HandsOn)
			)
		)
	)
)

(instance getTheTicketScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(LocPrint 16 121)
				(= local15 1)
				(keith setMotion: 0)
				(self cue:)
			)
			(1
				(if (< (keith y?) (+ (ego y?) 5))
					(keith setMotion: MoveTo (- (ego x?) 20) (+ (ego y?) 5) self)
				else
					(self cue:)
				)
			)
			(2
				(keith setMotion: MoveTo 258 142 self)
			)
			(3
				(keith loop: 3)
				(agent
					setLoop: 3
					setCel: 0
					setMotion: MoveTo 244 119 self
					illegalBits: 0
				)
			)
			(4
				(LocPrint 16 122 25 7 83)
				(self cue:)
			)
			(5
				(keith hide:)
				(agent setCel: -1 hide:)
				((= keithCallingCaptain (Prop new:))
					view: 667
					loop: 0
					posn: 253 138
					init:
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(6
				(LocPrint 16 123 25 10 83)
				(self cue:)
			)
			(7
				(keithCallingCaptain setCycle: BegLoop self)
			)
			(8
				(keithCallingCaptain dispose:)
				(keith show:)
				(agent show:)
				(self cue:)
			)
			(9
				(agent setMotion: MoveTo 244 110)
				(keith setMotion: Follow ego 25)
				(if (or local14 local13)
					(LocPrint 16 124 83)
					(cond 
						(goingToHouston
							(Bset fHoustonAuthorized)
							(= goingToHouston 0)
						)
						(goingToSteelton
							(Bset fSteeltonAuthorized)
							(= goingToSteelton 0)
							(if
								(not (Btst fColbyPhoneTap))
								(LocPrint 16 125)
							)
						)
					)
				else
					(LocPrint 16 126)
					(LocPrint 16 127)
				)
				(HandsOn)
				(self cue:)
			)
			(10
				(= local15 0)
				(moveScript changeState: 2)
			)
		)
	)
)

(instance egoOnThePhoneScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst fKeithFollows)
					(if (keith inRect: 225 139 280 145)
						(HandsOff)
						(keith
							setMotion: MoveTo
								(if (> (ego x?) (keith x?))
									210
								else
									290
								) 
								(keith y?) self
						)
					else
						(self cue:)
					)
				else
					(self cue:)
				)
			)
			(1
				(HandsOn)
				(ego setMotion: MoveTo 258 139 self)
			)
			(2
				(= local1 0)
				(ego loop: 3)
				(agent
					setLoop: 3
					setCel: 0
					setMotion: MoveTo 244 119 self
					illegalBits: 0
				)
			)
			(3
				(LocPrint 16 128 25 3 83)
				(self cue:)
			)
			(4
				(HandsOff)
				(agent setCel: -1 hide:)
				(ego
					view: 667
					loop: 1
					posn: 253 139
					init:
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(5
				(= perspective 0)
				(curRoom newRoom: 12)
			)
			(6
				(ego setCycle: BegLoop self)
			)
			(7
				(HandsOn)
				(NormalEgo)
				(ego view: 1 loop: 1 posn: 260 139)
				(agent setLoop: 3 setCel: 0 posn: 244 119)
			)
		)
	)
)

(instance pictureOnWall of PicView
	(properties
		y 88
		x 44
		view 77
		loop 7
		cel 2
		priority 8
		signal $4000
	)
)

(instance sittingPerson of PicView
	(properties
		y 125
		x 55
		view 77
		loop 5
		priority 8
		signal $4000
	)
)

(instance counterPerson of PicView
	(properties
		y 124
		x 179
		view 77
		loop 5
		cel 1
		priority 8
	)
)

(instance talking1 of PicView
	(properties
		y 150
		x 78
		view 77
		loop 5
		cel 2
		priority 11
	)
)

(instance talking2 of PicView
	(properties
		y 151
		x 101
		view 77
		loop 5
		cel 3
		priority 11
	)
)

(instance talking3 of PicView
	(properties
		y 153
		x 91
		view 77
		loop 5
		cel 4
		priority 11
	)
)

(instance standing1 of PicView
	(properties
		y 98
		x 145
		view 77
		loop 6
		cel 4
		priority 5
	)
)

(instance standing2 of PicView
	(properties
		y 103
		x 240
		view 77
		loop 6
		cel 1
		priority 5
		signal $4000
	)
)

(instance standing3 of PicView
	(properties
		y 104
		x 171
		view 77
		loop 6
		cel 2
		priority 5
	)
)

(instance standing4 of PicView
	(properties
		y 106
		x 164
		view 77
		loop 6
		priority 5
	)
)

(instance standing5 of PicView
	(properties
		y 100
		x 154
		view 77
		loop 6
		cel 3
		priority 5
	)
)

(instance case of PicView
	(properties
		y 129
		x 61
		view 77
		loop 7
		cel 1
		priority 9
	)
)

(instance SIGN of PicView
	(properties
		y 70
		x 29
		view 77
		loop 7
		priority 9
	)
)

(instance poster1 of PicView
	(properties
		y 117
		x 190
		view 77
		loop 7
		cel 3
		priority 8
		signal $4000
	)
)

(instance poster2 of PicView
	(properties
		y 126
		x 212
		view 77
		loop 7
		cel 4
		priority 9
		signal $4000
	)
)

(instance agent2 of PicView
	(properties
		y 103
		x 211
		view 77
		loop 4
		cel 1
		priority 8
	)
)
