;;; Sierra Script 1.0 - (do not remove this comment)
(script# 103)
(include sci.sh)
(use Main)
(use Grooper)

(public
	EgoGroop 0
)

(class EgoGroop of GradualLooper
	(properties
		client 0
		oldCycler 0
		oldMover 0
		caller 0
		dontHead 0
		speedState 0
	)
	
	(method (doit)
		(if
			(and
				(== (ego loop?) (- (NumLoops ego) 1))
				(not (& (ego signal?) $0800))
			)
			(ego loop: (ego cel?))
		)
		(super doit: &rest)
	)
	
	(method (cue &tmp [temp0 10])
		(asm
			pushi    #cue
			pushi    0
			super    GradualLooper,  4
			ldi      1
			aTop     dontHead
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      900
			eq?     
			bnt      code_0119
			pushi    #isHandsOn
			pushi    0
			lag      theGame
			send     4
			bnt      code_0119
			pushi    3
			dup     
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_00df
			pprev   
			ldi      8
			lt?     
			bnt      code_00df
			pushi    #setStep
			pushi    2
			pushi    3
			pushi    2
			lag      ego
			send     8
			pToa     speedState
			bnt      code_0119
			pushi    685
			pushi    #x
			pushi    #currentSpeed
			pushi    0
			lag      ego
			send     4
			push    
			ldi      2
			sub     
			push    
			lag      ego
			send     6
			pushi    #setSpeed
			pushi    1
			pushi    #currentSpeed
			pushi    0
			lag      ego
			send     4
			push    
			lag      ego
			send     6
			ldi      0
			aTop     speedState
			jmp      code_0119
code_00df:
			pushi    #setStep
			pushi    2
			pushi    6
			pushi    2
			lag      ego
			send     8
			pToa     speedState
			not     
			bnt      code_0119
			pushi    685
			pushi    #x
			pushi    #currentSpeed
			pushi    0
			lag      ego
			send     4
			push    
			ldi      2
			add     
			push    
			lag      ego
			send     6
			pushi    #setSpeed
			pushi    1
			pushi    #currentSpeed
			pushi    0
			lag      ego
			send     4
			push    
			lag      ego
			send     6
			ldi      2
			aTop     speedState
code_0119:
			ldi      0
			aTop     dontHead
			ret     
		)
	)
)
