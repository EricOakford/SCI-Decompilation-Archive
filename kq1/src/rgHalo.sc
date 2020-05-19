;;; Sierra Script 1.0 - (do not remove this comment)
(script# HALO)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	rgHalo 0
)

(instance rgHalo of Region
	(properties)
	
	(method (init)
		(Load VIEW 702)
		(halo
			init:
			hide:
			ignoreHorizon:
			illegalBits: 0
			ignoreActors:
			setCycle: Forward
		)
		(super init:)
	)
	
	(method (doit &tmp temp0)
		(asm
			lag      haloTimer
			bnt      code_00f2
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      34
			eq?     
			bnt      code_006a
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			ldi      2
			eq?     
			bt       code_006a
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			ldi      3
			eq?     
code_006a:
			not     
			bnt      code_00f2
			pushi    #nsLeft
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #nsRight
			pushi    0
			lag      ego
			send     4
			add     
			push    
			ldi      2
			div     
			sat      temp0
			pushi    #mover
			pushi    0
			lag      ego
			send     4
			bnt      code_00c0
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_00ac
			lst      temp0
			pushi    #xStep
			pushi    0
			lag      ego
			send     4
			add     
			sat      temp0
			jmp      code_00bf
code_00ac:
			dup     
			ldi      1
			eq?     
			bnt      code_00bf
			lst      temp0
			pushi    #xStep
			pushi    0
			lag      ego
			send     4
			sub     
			sat      temp0
code_00bf:
			toss    
code_00c0:
			pushi    225
			pushi    2
			lst      temp0
			pushi    #nsTop
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #nsBottom
			pushi    0
			lag      ego
			send     4
			add     
			push    
			ldi      2
			div     
			push    
			pushi    66
			pushi    1
			pushi    #priority
			pushi    0
			lag      ego
			send     4
			push    
			pushi    232
			pushi    0
			lofsa    halo
			send     18
			jmp      code_0108
code_00f2:
			pushi    #contains
			pushi    1
			lofsa    halo
			push    
			lag      cast
			send     6
			bnt      code_0108
			pushi    #dispose
			pushi    0
			lofsa    halo
			send     4
code_0108:
			ret     
		)
	)
)

(instance halo of Actor
	(properties
		view 702
		signal $0000
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/halo,glow,cloud,spell')
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 616 0)
			)
		)
	)
)
