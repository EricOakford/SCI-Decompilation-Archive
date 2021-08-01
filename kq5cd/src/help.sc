;;; Sierra Script 1.0 - (do not remove this comment)
(script# 753)
(include sci.sh)
(use Intrface)
(use System)

(public
	help 0
)

(local
	[local0 10] = [9237 9238 9239 9240 9241 9242 9243 9244 9245 9246]
)
(instance help of Code
	(properties)
	
	(method (doit &tmp temp0 temp1)
		(asm
			ldi      0
			sat      temp0
code_0014:
			pushi    2
			pushi    2
			pushi    2
			lea      @local0
			push    
			lst      temp0
			calle    WordAt,  4
			push    
			callk    DoAudio,  4
			lst      temp0
			ldi      0
			eq?     
			bnt      code_0056
			pushi    13
			pushi    601
			lst      temp0
			pushi    82
			pushi    942
			pushi    0
			lst      temp0
			pushi    0
			pushi    81
			lofsa    {Next}
			push    
			pushi    1
			pushi    81
			lofsa    {Exit}
			push    
			pushi    0
			calle    Print,  26
			sat      temp1
			jmp      code_00b0
code_0056:
			lst      temp0
			ldi      9
			lt?     
			bnt      code_008c
			pushi    16
			pushi    601
			lst      temp0
			pushi    82
			pushi    942
			pushi    0
			lst      temp0
			pushi    0
			pushi    81
			lofsa    {Next}
			push    
			pushi    1
			pushi    81
			lofsa    {Previous}
			push    
			pushi    2
			pushi    81
			lofsa    {Exit}
			push    
			pushi    0
			calle    Print,  32
			sat      temp1
			jmp      code_00b0
code_008c:
			pushi    13
			pushi    601
			lst      temp0
			pushi    82
			pushi    942
			pushi    0
			lst      temp0
			pushi    0
			pushi    81
			lofsa    {Exit}
			push    
			pushi    0
			pushi    81
			lofsa    {Previous}
			push    
			pushi    2
			calle    Print,  26
			sat      temp1
code_00b0:
			lst      temp1
			dup     
			ldi      0
			eq?     
			bnt      code_00c5
			pushi    1
			pushi    3
			callk    DoAudio,  2
			jmp      code_00de
			jmp      code_00da
code_00c5:
			dup     
			ldi      1
			eq?     
			bnt      code_00d1
			+at      temp0
			jmp      code_00da
code_00d1:
			dup     
			ldi      2
			eq?     
			bnt      code_00da
			-at      temp0
code_00da:
			toss    
			jmp      code_0014
code_00de:
			pushi    1
			pushi    753
			callk    DisposeScript,  2
			ret     
		)
	)
)
