;;; Sierra Script 1.0 - (do not remove this comment)
(script# 103)
(include game.sh)
(use Main)
(use Grooper)

(public
	EgoGroop 0
)

(class EgoGroop of GradualLooper
	(properties
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
			bnt      code_011a
			pushi    #isHandsOn
			pushi    0
			lag      theGame
			send     4
			bnt      code_011a
			pushi    3
			dup     
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_00e0
			pprev   
			ldi      8
			lt?     
			bnt      code_00e0
			pushi    #setStep
			pushi    2
			pushi    3
			pushi    2
			lag      ego
			send     8
			pToa     speedState
			bnt      code_011a
			pushi    673
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
			jmp      code_011a
code_00e0:
			pushi    #setStep
			pushi    2
			pushi    6
			pushi    2
			lag      ego
			send     8
			pToa     speedState
			not     
			bnt      code_011a
			pushi    673
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
code_011a:
			ldi      0
			aTop     dontHead
			ret     
		)
	)
)
