;;; Sierra Script 1.0 - (do not remove this comment)
(script# 752)
(include sci.sh)
(use Main)
(use Motion)
(use Actor)
(use System)

(public
	throwDazzle 0
	geniePoofIn 1
	geniePoofOut 2
)

(local
	[local0 8] = [9 10 11 -1 11 12 13 14]
	[local8 8] = [85 85 85 85 85 85 85 88]
	[local16 8] = [1 0 -1 0 1 0 1]
)
(instance throwDazzle of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2 [temp3 100])
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_006e
			lag      gEgo
			not     
			bnt      code_0044
			lag      ego
			sag      gEgo
code_0044:
			lsg      gEgo
			lag      ego
			eq?     
			bnt      code_0053
			pushi    #handsOff
			pushi    0
			lag      theGame
			send     4
code_0053:
			pushi    2
			pushi    132
			pushi    708
			callk    Load,  4
			pushi    2
			pushi    132
			pushi    707
			callk    Load,  4
			ldi      4
			aTop     cycles
			jmp      code_0391
code_006e:
			dup     
			ldi      1
			eq?     
			bnt      code_0218
			pushi    5
			pushi    #x
			pushi    0
			pToa     register
			send     4
			push    
			pushi    #y
			pushi    0
			pToa     register
			send     4
			push    
			pushi    #x
			pushi    0
			lag      gEgo
			send     4
			push    
			pushi    #y
			pushi    0
			lag      gEgo
			send     4
			push    
			pushi    60
			callk    GetAngle,  10
			sat      temp2
			pushi    3
			pushi    1
			pushi    338
			le?     
			bnt      code_00ae
			pprev   
			ldi      23
			le?     
			bnt      code_00ae
			ldi      3
			jmp      code_011d
code_00ae:
			pushi    23
			lat      temp2
			le?     
			bnt      code_00bf
			pprev   
			ldi      75
			le?     
			bnt      code_00bf
			ldi      6
			jmp      code_011d
code_00bf:
			pushi    75
			lat      temp2
			le?     
			bnt      code_00d0
			pprev   
			ldi      105
			le?     
			bnt      code_00d0
			ldi      0
			jmp      code_011d
code_00d0:
			pushi    105
			lat      temp2
			le?     
			bnt      code_00e2
			pprev   
			ldi      158
			le?     
			bnt      code_00e2
			ldi      4
			jmp      code_011d
code_00e2:
			pushi    158
			lat      temp2
			le?     
			bnt      code_00f5
			pprev   
			ldi      203
			le?     
			bnt      code_00f5
			ldi      2
			jmp      code_011d
code_00f5:
			pushi    203
			lat      temp2
			le?     
			bnt      code_0108
			pprev   
			ldi      255
			le?     
			bnt      code_0108
			ldi      5
			jmp      code_011d
code_0108:
			pushi    255
			lat      temp2
			le?     
			bnt      code_011b
			pprev   
			ldi      285
			le?     
			bnt      code_011b
			ldi      1
			jmp      code_011d
code_011b:
			ldi      7
code_011d:
			push    
			pToa     register
			send     6
			pushi    2
			pushi    128
			pushi    #view
			pushi    0
			pToa     register
			send     4
			push    
			callk    UnLoad,  4
			pushi    1
			pTos     register
			callk    NumCels,  2
			push    
			ldi      1
			sub     
			sat      temp0
			pushi    #cel
			pushi    1
			push    
			pToa     register
			send     6
			pushi    1
			pTos     register
			callk    SetNowSeen,  2
			pushi    #loop
			pushi    0
			pToa     register
			send     4
			push    
			ldi      3
			ne?     
			bnt      code_01d8
			pushi    60
			pushi    1
			pushi    #priority
			pushi    0
			pToa     register
			send     4
			push    
			pushi    0
			pushi    #x
			pushi    #nsBottom
			pushi    0
			pToa     register
			send     4
			push    
			pushi    #loop
			pushi    0
			pToa     register
			send     4
			lsli     local8
			pushi    #nsBottom
			pushi    0
			pToa     register
			send     4
			push    
			pushi    #nsTop
			pushi    0
			pToa     register
			send     4
			sub     
			mul     
			push    
			ldi      100
			div     
			sub     
			push    
			pushi    156
			pushi    1
			pushi    #loop
			pushi    0
			pToa     register
			send     4
			lsli     local0
			lofsa    projectile
			send     18
			pushi    1
			pushi    1
			pushi    #loop
			pushi    0
			pToa     register
			send     4
			lsli     local16
			ldi      0
			ge?     
			bnt      code_01cc
			pushi    #loop
			pushi    0
			pToa     register
			send     4
			lali     local16
			bnt      code_01c3
			pushi    #nsRight
			pushi    0
			pToa     register
			send     4
			jmp      code_01d2
code_01c3:
			pushi    #nsLeft
			pushi    0
			pToa     register
			send     4
			jmp      code_01d2
code_01cc:
			pushi    #x
			pushi    0
			pToa     register
			send     4
code_01d2:
			push    
			lofsa    projectile
			send     6
code_01d8:
			pushi    #cel
			pushi    1
			pushi    0
			pushi    162
			pushi    2
			class    EndLoop
			push    
			pushSelf
			pToa     register
			send     14
			pushi    #number
			pushi    0
			lag      theGlobalSound
			send     4
			push    
			ldi      707
			ne?     
			bnt      code_0391
			pushi    #number
			pushi    1
			pushi    0
			pushi    174
			pushi    0
			lag      theGlobalSound
			send     10
			pushi    #number
			pushi    1
			pushi    707
			pushi    156
			pushi    1
			pushi    1
			pushi    39
			pushi    0
			lag      theGlobalSound
			send     16
			jmp      code_0391
code_0218:
			dup     
			ldi      2
			eq?     
			bnt      code_02a5
			pushi    #setMotion
			pushi    1
			pushi    0
			pushi    301
			pushi    0
			lag      gEgo
			send     10
			pushi    #number
			pushi    1
			pushi    0
			pushi    174
			pushi    0
			lag      theGlobalSound
			send     10
			pushi    #number
			pushi    1
			pushi    708
			pushi    156
			pushi    1
			pushi    1
			pushi    39
			pushi    0
			lag      theGlobalSound
			send     16
			pushi    #loop
			pushi    0
			pToa     register
			send     4
			push    
			ldi      3
			ne?     
			bnt      code_029e
			pushi    #y
			pushi    0
			lag      gEgo
			send     4
			push    
			pushi    75
			pushi    #y
			pushi    0
			lag      gEgo
			send     4
			push    
			pushi    #nsTop
			pushi    0
			lag      gEgo
			send     4
			sub     
			mul     
			push    
			ldi      100
			div     
			sub     
			sat      temp1
			pushi    #x
			pushi    0
			lag      gEgo
			send     4
			sat      temp0
			pushi    #init
			pushi    0
			pushi    162
			pushi    1
			class    Forward
			push    
			pushi    300
			pushi    4
			class    MoveTo
			push    
			lst      temp0
			lst      temp1
			pushSelf
			lofsa    projectile
			send     22
			jmp      code_0391
code_029e:
			ldi      1
			aTop     cycles
			jmp      code_0391
code_02a5:
			dup     
			ldi      3
			eq?     
			bnt      code_02f6
			pushi    #loop
			pushi    0
			pToa     register
			send     4
			push    
			ldi      3
			ne?     
			bnt      code_02ef
			pushi    #number
			pushi    1
			pushi    0
			pushi    174
			pushi    0
			lag      theGlobalSound
			send     10
			pushi    #number
			pushi    1
			pushi    709
			pushi    156
			pushi    1
			pushi    1
			pushi    39
			pushi    0
			lag      theGlobalSound
			send     16
			pushi    #loop
			pushi    1
			pushi    15
			pushi    4
			pushi    1
			pushi    0
			pushi    162
			pushi    2
			class    EndLoop
			push    
			pushSelf
			lofsa    projectile
			send     20
			jmp      code_0391
code_02ef:
			ldi      1
			aTop     cycles
			jmp      code_0391
code_02f6:
			dup     
			ldi      4
			eq?     
			bnt      code_0366
			lsg      gEgo
			lag      ego
			eq?     
			bnt      code_0322
			pushi    #number
			pushi    1
			pushi    0
			pushi    174
			pushi    0
			lag      theGlobalSound
			send     10
			pushi    #number
			pushi    1
			pushi    705
			pushi    156
			pushi    1
			pushi    1
			pushi    39
			pushi    0
			lag      theMusic
			send     16
code_0322:
			pushi    #dispose
			pushi    0
			lofsa    projectile
			send     4
			pushi    1
			lsg      gKillDog
			callk    IsObject,  2
			bnt      code_0341
			pushi    #setScript
			pushi    2
			lsg      gKillDog
			pushSelf
			self     8
			ldi      0
			sag      gKillDog
			jmp      code_0391
code_0341:
			lsg      gEgo
			lag      ego
			eq?     
			bnt      code_0360
			pushi    #client
			pushi    1
			pushSelf
			lag      theMusic
			send     6
			pushi    #setScript
			pushi    3
			lofsa    getEgo
			push    
			pushi    0
			pTos     caller
			self     10
			jmp      code_0391
code_0360:
			ldi      1
			aTop     cycles
			jmp      code_0391
code_0366:
			dup     
			ldi      5
			eq?     
			bnt      code_037a
			pushi    #setCycle
			pushi    2
			class    BegLoop
			push    
			pushSelf
			pToa     register
			send     8
			jmp      code_0391
code_037a:
			dup     
			ldi      6
			eq?     
			bnt      code_0386
			ldi      3
			aTop     cycles
			jmp      code_0391
code_0386:
			dup     
			ldi      7
			eq?     
			bnt      code_0391
			pushi    #dispose
			pushi    0
			self     4
code_0391:
			toss    
			ret     
		)
	)
)

(instance getEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 749
					normal: 0
					cel: 0
					loop: (mod (ego cel?) 4)
					cycleSpeed: 8
					setCycle: CycleTo 2 1 self
				)
			)
			(1
				(ego
					loop:
					(switch (ego loop?)
						(0 3)
						(1 2)
						(2 1)
						(3 0)
					)
				)
				(ego setCycle: EndLoop self)
			)
			(2
				(if (> (ego loop?) 2)
					(ego
						loop: (+ 4 (== (ego loop?) 3))
						cycleSpeed: 10
						setCycle: EndLoop self
					)
				else
					(= cycles 1)
				)
			)
			(3
				(if register (self dispose:) else (EgoDead 18))
			)
		)
	)
)

(instance geniePoofIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsSOUND 943)
				(= cycles 2)
			)
			(1
				(theGlobalSound number: 0 stop:)
				(theGlobalSound number: 943 setLoop: 1 play:)
				(smoke
					scaleSignal: 1
					scaleX: (register scaleX?)
					scaleY: (register scaleY?)
					priority: (register priority?)
					posn: (register x?) (+ (register y?) 1) 1
					init:
					cycleSpeed: 8
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(register init: show:)
				(smoke cycleSpeed: 10 setCycle: EndLoop self)
			)
			(3
				(smoke dispose:)
				(= cycles 2)
			)
			(4 (self dispose:))
		)
	)
)

(instance geniePoofOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(theGlobalSound number: 0 stop:)
				(theGlobalSound number: 943 setLoop: 1 play:)
				(smoke
					scaleSignal: 1
					scaleX: (register scaleX?)
					scaleY: (register scaleY?)
					priority: (register priority?)
					posn: (register x?) (+ (register y?) 1) 1
					init:
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(register hide:)
				(smoke setCycle: EndLoop self)
			)
			(3
				(smoke dispose:)
				(= cycles 2)
			)
			(4 (self dispose:))
		)
	)
)

(instance smoke of Prop
	(properties
		view 7501
		signal $4010
		cycleSpeed 8
	)
)

(instance projectile of Actor
	(properties
		yStep 7
		loop 9
		signal $6010
		cycleSpeed 3
		xStep 7
		moveSpeed 0
	)
	
	(method (init)
		(if (== curRoomNum 740)
			(= view 7021)
		else
			(= view 702)
		)
		(super init: &rest)
	)
)
