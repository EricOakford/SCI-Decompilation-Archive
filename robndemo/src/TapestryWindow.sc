;;; Sierra Script 1.0 - (do not remove this comment)
(script# 850)
(include game.sh)
(use Window)


(class TapestryWindow of SysWindow
	(properties
		brBottom 190
		brRight 320
		underBits 0
		pUnderBits 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
	)
	
	(method (dispose)
		(SetPort 0)
		(Graph GRestoreBits underBits)
		(Graph GRestoreBits pUnderBits)
		(Graph GReAnimate lsTop lsLeft lsBottom lsRight)
		(super dispose:)
	)
	
	(method (open &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9)
		(asm
			pushi    1
			pushi    0
			callk    SetPort,  2
			ldi      1
			sat      temp2
			push    
			ldi      2
			or      
			sat      temp2
			pToa     top
			sat      temp3
			pToa     left
			sat      temp4
			pToa     bottom
			sat      temp5
			pToa     right
			sat      temp6
			pTos     top
			ldi      11
			sub     
			aTop     lsTop
			pTos     left
			ldi      32
			sub     
			aTop     lsLeft
			pTos     right
			ldi      32
			add     
			aTop     lsRight
			pTos     bottom
			ldi      13
			add     
			aTop     lsBottom
			pushi    6
			pushi    7
			pTos     lsTop
			pTos     lsLeft
			pTos     lsBottom
			pTos     lsRight
			pushi    1
			callk    Graph,  12
			aTop     underBits
			pushi    6
			pushi    7
			pTos     lsTop
			pTos     lsLeft
			pTos     lsBottom
			pTos     lsRight
			pushi    2
			callk    Graph,  12
			aTop     pUnderBits
			pushi    #open
			pushi    0
			super    SysWindow,  4
			pushi    0
			callk    GetPort,  0
			sat      temp1
			pushi    1
			pushi    0
			callk    SetPort,  2
			lst      temp4
			ldi      1
			sub     
			sat      temp8
			lst      temp6
			ldi      13
			sub     
			sat      temp7
			lat      temp4
			sat      temp9
			pushi    6
			pushi    785
			pushi    7
			pushi    0
			lst      temp4
			ldi      18
			sub     
			push    
			lst      temp3
			ldi      6
			sub     
			push    
			pushi    15
			callk    DrawCel,  12
			pushi    6
			pushi    785
			pushi    7
			pushi    0
			lst      temp6
			lst      temp3
			ldi      6
			sub     
			push    
			pushi    15
			callk    DrawCel,  12
			pushi    6
			pushi    785
			pushi    6
			pushi    0
			lst      temp6
			lst      temp5
			pushi    15
			callk    DrawCel,  12
			pushi    6
			pushi    785
			pushi    6
			pushi    0
			lst      temp4
			ldi      1
			sub     
			push    
			lst      temp5
			pushi    15
			callk    DrawCel,  12
			pushi    6
			pushi    785
			pushi    5
			pushi    0
			lst      temp6
			ldi      13
			sub     
			push    
			lst      temp5
			ldi      1
			add     
			push    
			pushi    15
			callk    DrawCel,  12
code_00f6:
			lst      temp9
			lst      temp6
			ldi      14
			sub     
			lt?     
			bnt      code_0120
			pushi    6
			pushi    785
			pushi    5
			pushi    0
			lst      temp9
			lst      temp5
			ldi      1
			add     
			push    
			pushi    15
			callk    DrawCel,  12
			lst      temp9
			ldi      13
			add     
			sat      temp9
			jmp      code_00f6
code_0120:
			lst      temp7
			lat      temp8
			sub     
			push    
			ldi      14
			gt?     
			bnt      code_0167
			pushi    6
			pushi    785
			pushi    4
			pushi    0
			lst      temp8
			lst      temp5
			ldi      1
			add     
			push    
			pushi    15
			callk    DrawCel,  12
			pushi    6
			pushi    785
			pushi    4
			pushi    0
			lst      temp7
			pTos     bottom
			ldi      1
			add     
			push    
			pushi    15
			callk    DrawCel,  12
			lst      temp8
			ldi      26
			add     
			sat      temp8
			lst      temp7
			ldi      26
			sub     
			sat      temp7
			jmp      code_0120
code_0167:
			pushi    1
			lst      temp1
			callk    SetPort,  2
			pushi    6
			pushi    12
			pTos     lsTop
			pTos     lsLeft
			pTos     lsBottom
			pTos     lsRight
			pushi    1
			callk    Graph,  12
			ldi      129
			aTop     type
			ret     
		)
	)
)
