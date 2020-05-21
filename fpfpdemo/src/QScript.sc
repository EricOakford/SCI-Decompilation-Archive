;;; Sierra Script 1.0 - (do not remove this comment)
(script# 962)
(include game.sh)

;NOTE: This entire module has been commented out,
; as it is obsolete as of SCI1.1.

;;;(public
;;;	QueueScript 0
;;;)
;;;
;;;(procedure (QueueScript param1 param2 param3 param4 &tmp temp0 temp1 temp2 temp3)
;;;	(asm
;;;		lap      param1
;;;		sat      temp0
;;;		ldi      0
;;;		sat      temp1
;;;		ldi      0
;;;		sat      temp2
;;;		lsp      argc
;;;		ldi      3
;;;		ge?     
;;;		bnt      code_0045
;;;		lap      param3
;;;		sat      temp1
;;;		lsp      argc
;;;		ldi      4
;;;		ge?     
;;;		bnt      code_002e
;;;		lap      param4
;;;		sat      temp2
;;;code_002e:
;;;		pushi    #caller
;;;		pushi    1
;;;		lst      temp1
;;;		pushi    141
;;;		pushi    1
;;;		lst      temp2
;;;		pushi    1
;;;		lsp      param2
;;;		call     localproc_0163,  2
;;;		sap      param2
;;;		send     12
;;;code_0045:
;;;		ldi      1
;;;		bnt      code_0162
;;;		lat      temp0
;;;		not     
;;;		bnt      code_0064
;;;		pushi    #setScript
;;;		pushi    1
;;;		pushi    1
;;;		lsp      param2
;;;		call     localproc_0163,  2
;;;		push    
;;;		lap      param1
;;;		send     6
;;;		jmp      code_0162
;;;		jmp      code_0045
;;;code_0064:
;;;		pushi    #respondsTo
;;;		pushi    1
;;;		pushi    65
;;;		lat      temp0
;;;		send     6
;;;		not     
;;;		bnt      code_009b
;;;		pushi    #script
;;;		pushi    0
;;;		lat      temp0
;;;		send     4
;;;		bnt      code_0086
;;;		pushi    #script
;;;		pushi    0
;;;		lat      temp0
;;;		send     4
;;;		sat      temp0
;;;		jmp      code_0045
;;;code_0086:
;;;		pushi    #setScript
;;;		pushi    1
;;;		pushi    1
;;;		lsp      param2
;;;		call     localproc_0163,  2
;;;		push    
;;;		lat      temp0
;;;		send     6
;;;		jmp      code_0162
;;;		jmp      code_0045
;;;code_009b:
;;;		lst      temp0
;;;		lap      param1
;;;		eq?     
;;;		bnt      code_00c1
;;;		pushi    #script
;;;		pushi    0
;;;		lap      param1
;;;		send     4
;;;		sat      temp0
;;;		push    
;;;		pushi    #script
;;;		pushi    0
;;;		send     4
;;;		eq?     
;;;		bnt      code_0045
;;;		pushi    #new
;;;		pushi    0
;;;		lat      temp0
;;;		send     4
;;;		sat      temp0
;;;		jmp      code_0045
;;;code_00c1:
;;;		pushi    #next
;;;		pushi    0
;;;		lat      temp0
;;;		send     4
;;;		bnt      code_012d
;;;		pushi    1
;;;		pushi    #next
;;;		pushi    0
;;;		lat      temp0
;;;		send     4
;;;		push    
;;;		call     localproc_0163,  2
;;;		sat      temp3
;;;		pushi    65
;;;		pushi    1
;;;		lst      temp0
;;;		pushi    #next
;;;		pushi    0
;;;		lat      temp0
;;;		send     4
;;;		eq?     
;;;		bnt      code_00fa
;;;		pushi    #next
;;;		pushi    1
;;;		pushi    0
;;;		pushi    117
;;;		pushi    0
;;;		pushi    #new
;;;		pushi    0
;;;		lat      temp3
;;;		send     4
;;;		send     10
;;;		jmp      code_011c
;;;code_00fa:
;;;		pushi    #-info-
;;;		pushi    0
;;;		lat      temp0
;;;		send     4
;;;		push    
;;;		ldi      1
;;;		and     
;;;		bnt      code_011a
;;;		pushi    #next
;;;		pushi    1
;;;		pushi    0
;;;		pushi    117
;;;		pushi    0
;;;		pushi    #new
;;;		pushi    0
;;;		lat      temp3
;;;		send     4
;;;		send     10
;;;		jmp      code_011c
;;;code_011a:
;;;		lat      temp3
;;;code_011c:
;;;		push    
;;;		lat      temp0
;;;		send     6
;;;		pushi    #next
;;;		pushi    0
;;;		lat      temp0
;;;		send     4
;;;		sat      temp0
;;;		jmp      code_0045
;;;code_012d:
;;;		pushi    65
;;;		pushi    1
;;;		lsp      param2
;;;		lat      temp0
;;;		eq?     
;;;		bt       code_014d
;;;		pushi    1
;;;		lsp      param2
;;;		callk    IsObject,  2
;;;		bnt      code_0156
;;;		pushi    #-info-
;;;		pushi    0
;;;		lap      param2
;;;		send     4
;;;		push    
;;;		ldi      1
;;;		and     
;;;		bnt      code_0156
;;;code_014d:
;;;		pushi    #new
;;;		pushi    0
;;;		lap      param2
;;;		send     4
;;;		jmp      code_0158
;;;code_0156:
;;;		lap      param2
;;;code_0158:
;;;		push    
;;;		lat      temp0
;;;		send     6
;;;		jmp      code_0162
;;;		jmp      code_0045
;;;code_0162:
;;;		ret     
;;;	)
;;;)
;;;
;;;(procedure (localproc_0163 param1)
;;;	(if (IsObject param1) param1 else (ScriptID param1))
;;;)
;;;