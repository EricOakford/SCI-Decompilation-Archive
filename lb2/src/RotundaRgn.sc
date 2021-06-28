;;; Sierra Script 1.0 - (do not remove this comment)
(script# 93)
(include game.sh)
(use Main)
(use n027)
(use Game)
(use Actor)
(use System)

(public
	RotundaRgn 0
	Countess 1
	proc93_2 2
	Heimlich 3
	Olympia 4
	O_Riley 5
	Pippin 6
	Rameses 7
	Steve 8
	Tut 9
	Watney 10
	Yvette 11
	Ziggy 12
)

;EO: this did not decompile correctly. Fortunately, it
; doesn't seem to be used.
(procedure (proc93_2 param1 &tmp temp0 temp1 temp2)
	(asm
		eq?     
		bnt      code_0437
		lst      temp2
		ldi      18
		add     
		jmp      code_0451
code_0437:
		dup     
		ldi      768
		eq?     
		bnt      code_0445
		lst      temp2
		ldi      26
		add     
		jmp      code_0451
code_0445:
		dup     
		ldi      1024
		eq?     
		bnt      code_0451
		lst      temp2
		ldi      61
		add     
code_0451:
		toss    
		sat      temp1
		pushi    6
		pushi    0
		pTos     28
		; WARNING: Can't determine property name for index 28
		pTos     26
		; WARNING: Can't determine property name for index 26
		pushi    6
		push    
		pushi    1
		callk    Message,  12
		not     
		bnt      code_047b
		pushi    #say
		pushi    6
		pTos     26
		; WARNING: Can't determine property name for index 26
		pushi    6
		pushi    81
		pushi    0
		pushi    0
		pTos     28
		; WARNING: Can't determine property name for index 28
		lag      messager
		send     16
		jmp      code_04ce
code_047b:
		pushi    2
		pushi    2
		lst      temp1
		ldi      2
		sub     
		lsgi     global296
		calle    proc27_0,  4
		bnt      code_049f
		pushi    #say
		pushi    6
		pTos     26
		; WARNING: Can't determine property name for index 26
		pushi    6
		pushi    1
		pushi    0
		pushi    0
		pTos     28
		; WARNING: Can't determine property name for index 28
		lag      messager
		send     16
		jmp      code_04ce
code_049f:
		pushi    #say
		pushi    6
		pTos     26
		; WARNING: Can't determine property name for index 26
		pushi    6
		lst      temp1
		pushi    0
		pushi    0
		pTos     28
		; WARNING: Can't determine property name for index 28
		lag      messager
		send     16
		pushi    2
		pushi    2
		lst      temp1
		ldi      2
		sub     
		leai     @global296
		push    
		calle    proc27_1,  4
		jmp      code_04ce
		pushi    #doVerb
		pushi    1
		lsp      param1
		super    Actor,  6
code_04ce:
		ret     
	)
)

(class RotundaRgn of Region
	(properties
		convRoom 0
		convReturn 0
	)
	
	(method (init)
		(super init:)
		(= convRoom
			(switch global128
				(0 350)
				(1 350)
				(2 360)
				(3 360)
				(4 350)
				(5 350)
				(6 370)
				(7 370)
				(8 370)
				(9 350)
				(10 360)
				(11 360)
				(12 360)
				(13 350)
			)
		)
		(Countess approachDist: 20 approachVerbs: V_TALK V_ASK)
		(Heimlich approachDist: 20 approachVerbs: V_TALK V_ASK)
		(Olympia approachDist: 20 approachVerbs: V_TALK V_ASK)
		(O_Riley approachDist: 20 approachVerbs: V_TALK V_ASK)
		(Pippin approachDist: 20 approachVerbs: V_TALK V_ASK)
		(Rameses approachDist: 20 approachVerbs: V_TALK V_ASK)
		(Steve approachDist: 20 approachVerbs: V_TALK V_ASK)
		(Tut approachDist: 20 approachVerbs: V_TALK V_ASK)
		(Watney approachDist: 20 approachVerbs: V_TALK V_ASK)
		(Yvette approachDist: 20 approachVerbs: V_TALK V_ASK)
		(Ziggy approachDist: 20 approachVerbs: V_TALK V_ASK)
	)
	
	(method (newRoom n)
		(= keep (OneOf n 335 340 350 355 360 370 400))
		(= initialized FALSE)
		(cond 
			((not (== currentAct 2)))
			((OneOf n 335 400 420)
				(theMusic fade: 50 5 5 0)
			)
			((== n 340)
				(theMusic fade: 100 5 5 0)
			)
		)
		(if
			(and
				(& $7204 triggeredEvents)
				(not (Btst 71))
				(== n 360)
			)
			(Bset 71)
		)
		(super newRoom: n)
	)
	
	(method (notify)
		(= convReturn curRoomNum)
		(curRoom newRoom: 340)
	)
)

(instance Countess of Actor
	(properties
		noun 1
		modNum 1884
		view 813
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem &tmp temp0 temp1 temp2)
		(cond 
			((== theVerb V_TALK)
				(if (not (Bset 112))
					(messager say: noun theVerb 80 0 0 modNum)
				else
					(super doVerb: theVerb)
				)
			)
			((OneOf theVerb V_ASK V_NOTEBOOK)
				(if
					(==
						(= temp0
							(if (== argc 2)
								theItem
							else
								(curRoom setInset: (ScriptID 20 0))
							)
						)
						-1
					)
					(return)
				)
				(= temp2 (& temp0 $00ff))
				(= temp1
					(switch (& temp0 $ff00)
						(256 (+ temp2 1))
						(512 (+ temp2 18))
						(768 (+ temp2 26))
						(1024 (+ temp2 61))
					)
				)
				(cond 
					((not (Message MsgGet modNum noun V_ASK temp1 1))
						(messager say: noun V_ASK 81 0 0 modNum)
					)
					((proc27_0 0 [global296 (- temp1 2)]) (messager say: noun V_ASK 1 0 0 modNum))
					(else
						(messager say: noun V_ASK temp1 0 0 modNum)
						(proc27_1 0 @[global296 (- temp1 2)])
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance Heimlich of Actor
	(properties
		noun 1
		modNum 1889
		view 814
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem &tmp temp0 temp1 temp2)
		(if (OneOf theVerb V_ASK V_NOTEBOOK)
			(if
				(==
					(= temp0
						(if (== argc 2)
							theItem
						else
							(curRoom setInset: (ScriptID 20 0))
						)
					)
					-1
				)
				(return)
			)
			(= temp2 (& temp0 $00ff))
			(= temp1
				(switch (& temp0 $ff00)
					(256 (+ temp2 1))
					(512 (+ temp2 18))
					(768 (+ temp2 26))
					(1024 (+ temp2 61))
				)
			)
			(cond 
				((not (Message MsgGet modNum noun V_ASK temp1 1))
					(messager say: noun V_ASK 81 0 0 modNum)
				)
				((proc27_0 2 [global296 (- temp1 2)])
					(messager say: noun V_ASK 1 0 0 modNum)
				)
				(else
					(messager say: noun V_ASK temp1 0 0 modNum)
					(proc27_1 2 @[global296 (- temp1 2)])
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance Olympia of Actor
	(properties
		noun 1
		modNum 1892
		view 820
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem &tmp temp0 theCase temp2)
		(if (OneOf theVerb 6 14)
			(if
				(==
					(= temp0
						(if (== argc 2)
							theItem
						else
							(curRoom setInset: (ScriptID 20 0))
						)
					)
					-1
				)
				(return)
			)
			(= temp2 (& temp0 $00ff))
			(= theCase
				(switch (& temp0 $ff00)
					(256 (+ temp2 1))
					(512 (+ temp2 18))
					(768 (+ temp2 26))
					(1024 (+ temp2 61))
				)
			)
			(cond 
				((not (Message MsgGet modNum noun V_ASK theCase 1))
					(messager say: noun V_ASK 81 0 0 modNum)
				)
				((proc27_0 3 [global296 (- theCase 2)])
					(messager say: noun V_ASK 1 0 0 modNum)
				)
				(else
					(messager say: noun V_ASK theCase 0 0 modNum)
					(proc27_1 3 @[global296 (- theCase 2)])
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance O_Riley of Actor
	(properties
		noun 1
		modNum 1888
		view 819
		signal ignrAct
		name "O'Riley"
	)
	
	(method (doVerb theVerb theItem &tmp temp0 theCase temp2)
		(cond 
			((== theVerb V_TALK)
				(if (not (Bset 114))
					(messager say: noun theVerb 80 0 0 modNum)
				else
					(super doVerb: theVerb)
				)
			)
			((OneOf theVerb V_ASK V_NOTEBOOK)
				(if
					(==
						(= temp0
							(if (== argc 2)
								theItem
							else
								(curRoom setInset: (ScriptID 20 0))
							)
						)
						-1
					)
					(return)
				)
				(= temp2 (& temp0 $00ff))
				(= theCase
					(switch (& temp0 $ff00)
						(256 (+ temp2 1))
						(512 (+ temp2 18))
						(768 (+ temp2 26))
						(1024 (+ temp2 61))
					)
				)
				(switch temp0
					(258
						(cond 
							((Btst 134)
								(if (proc27_0 4 global364)
									(messager say: noun V_ASK 1 0 0 modNum)
								else
									(messager say: noun V_ASK 72 0 0 modNum)
									(proc27_1 4 @global364)
								)
							)
							((proc27_0 4 global297)
								(messager say: noun V_ASK 1 0 0 modNum)
							)
							(else
								(messager say: noun V_ASK 3 0 0 modNum)
								(proc27_1 4 @global297)
							)
						)
					)
					(259
						(cond 
							((Btst 171)
								(if (proc27_0 4 global363)
									(messager say: noun V_ASK 1 0 0 modNum)
								else
									(messager say: noun V_ASK 69 0 0 modNum)
									(proc27_1 4 @global363)
								)
							)
							((proc27_0 4 global298) (messager say: noun V_ASK 1 0 0 modNum))
							(else
								(messager say: noun V_ASK 4 0 0 modNum)
								(proc27_1 4 @global298)
							)
						)
					)
					(264
						(cond 
							((Btst 143)
								(if (proc27_0 4 global366)
									(messager say: noun V_ASK 1 0 0 modNum)
								else
									(messager say: noun V_ASK 74 0 0 modNum)
									(proc27_1 4 @global366)
								)
							)
							((proc27_0 4 global303)
								(messager say: noun V_ASK 1 0 0 modNum)
							)
							(else
								(messager say: noun V_ASK 9 0 0 modNum)
								(proc27_1 4 @global303)
							)
						)
					)
					(266
						(cond 
							((Btst 161)
								(if (proc27_0 4 global367)
									(messager say: noun V_ASK 1 0 0 modNum)
								else
									(messager say: noun V_ASK 73 0 0 modNum)
									(proc27_1 4 @global367)
								)
							)
							((proc27_0 4 global305)
								(messager say: noun V_ASK 1 0 0 modNum)
							)
							(else
								(messager say: noun V_ASK 11 0 0 modNum)
								(proc27_1 4 @global305)
							)
						)
					)
					(267
						(cond 
							((Btst 158)
								(if (proc27_0 4 global365)
									(messager say: noun V_ASK 1 0 0 modNum)
								else
									(messager say: noun V_ASK 71 0 0 modNum)
									(proc27_1 4 @global365)
								)
							)
							((proc27_0 4 global306)
								(messager say: noun V_ASK 1 0 0 modNum)
							)
							(else
								(messager say: noun V_ASK 12 0 0 modNum)
								(proc27_1 4 @global306)
							)
						)
					)
					(780
						(cond 
							((Btst 155)
								(if (proc27_0 4 global368)
									(messager say: noun V_ASK 1 0 0 modNum)
								else
									(messager say: noun V_ASK 75 0 0 modNum)
									(proc27_1 4 @global368)
								)
							)
							((proc27_0 4 global332)
								(messager say: noun V_ASK 1 0 0 modNum)
							)
							(else
								(messager say: noun V_ASK 38 0 0 modNum)
								(proc27_1 4 @global332)
							)
						)
					)
					(else 
						(cond 
							((not (Message MsgGet modNum noun V_ASK theCase 1))
								(messager say: noun V_ASK 81 0 0 modNum)
							)
							((proc27_0 4 [global296 (- theCase 2)])
								(messager say: noun V_ASK 1 0 0 modNum)
							)
							(else
								(messager say: noun V_ASK theCase 0 0 modNum)
								(proc27_1 4 @[global296 (- theCase 2)])
							)
						)
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance Pippin of Actor
	(properties
		noun 1
		modNum 1882
		view 822
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem &tmp temp0 theCase temp2)
		(cond 
			((== theVerb V_TALK)
				(if (not (Bset 110))
					(messager say: noun theVerb 80 0 0 modNum)
				else
					(super doVerb: theVerb)
				)
			)
			((OneOf theVerb V_ASK V_NOTEBOOK)
				(if
					(==
						(= temp0
							(if (== argc 2)
								theItem
							else
								(curRoom setInset: (ScriptID 20 0))
							)
						)
						-1
					)
					(return)
				)
				(= temp2 (& temp0 $00ff))
				(= theCase
					(switch (& temp0 $ff00)
						(256 (+ temp2 1))
						(512 (+ temp2 18))
						(768 (+ temp2 26))
						(1024 (+ temp2 61))
					)
				)
				(cond 
					((not (Message MsgGet modNum noun V_ASK theCase 1))
						(messager say: noun V_ASK 81 0 0 modNum)
					)
					((proc27_0 5 [global296 (- theCase 2)])
						(messager say: noun V_ASK 1 0 0 modNum)
					)
					(else
						(messager say: noun V_ASK theCase 0 0 modNum)
						(proc27_1 5 @[global296 (- theCase 2)])
					)
				)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance Rameses of Actor
	(properties
		noun 1
		modNum 1891
		view 823
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem &tmp temp0 theCase temp2)
		(cond 
			((== theVerb V_TALK)
				(if (not (Bset 115))
					(messager say: noun theVerb 80 0 0 modNum)
				else
					(super doVerb: theVerb)
				)
			)
			((OneOf theVerb V_ASK V_NOTEBOOK)
				(if
					(==
						(= temp0
							(if (== argc 2)
								theItem
							else
								(curRoom setInset: (ScriptID 20 0))
							)
						)
						-1
					)
					(return)
				)
				(= temp2 (& temp0 $00ff))
				(= theCase
					(switch (& temp0 $ff00)
						(256 (+ temp2 1))
						(512 (+ temp2 18))
						(768 (+ temp2 26))
						(1024 (+ temp2 61))
					)
				)
				(cond 
					((not (Message MsgGet modNum noun V_ASK theCase 1))
						(messager say: noun V_ASK 81 0 0 modNum)
					)
					((proc27_0 6 [global296 (- theCase 2)])
						(messager say: noun V_ASK 1 0 0 modNum)
					)
					(else
						(messager say: noun V_ASK theCase 0 0 modNum)
						(proc27_1 6 @[global296 (- theCase 2)])
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance Steve of Actor
	(properties
		noun 1
		modNum 1887
		view 812
		signal ignrAct
		scaleSignal scalable
	)
	
	(method (doVerb theVerb theItem &tmp temp0 theCase temp2)
		(if (== theVerb V_ASK)
			(if
				(==
					(= temp0
						(if (== argc 2)
							theItem
						else
							(curRoom setInset: (ScriptID 20 0))
						)
					)
					-1
				)
				(return)
			)
			(= temp2 (& temp0 $00ff))
			(= theCase
				(switch (& temp0 $ff00)
					(256 (+ temp2 1))
					(512 (+ temp2 18))
					(768 (+ temp2 26))
					(1024 (+ temp2 61))
				)
			)
			(cond 
				((not (Message MsgGet modNum noun V_ASK theCase 1))
					(messager say: noun V_ASK 81 0 0 modNum)
				)
				((proc27_0 10 [global296 (- theCase 2)])
					(messager say: noun V_ASK 1 0 0 modNum)
				)
				(else
					(messager say: noun V_ASK theCase 0 0 modNum)
					(proc27_1 10 @[global296 (- theCase 2)])
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance Tut of Actor
	(properties
		noun 1
		modNum 1883
		view 821
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem &tmp temp0 theCase temp2)
		(cond 
			((== theVerb V_TALK)
				(if (not (Bset 111))
					(messager say: noun theVerb 80 0 0 modNum)
				else
					(super doVerb: theVerb)
				)
			)
			((OneOf theVerb V_ASK V_NOTEBOOK)
				(if
					(==
						(= temp0
							(if (== argc 2)
								theItem
							else
								(curRoom setInset: (ScriptID 20 0))
							)
						)
						-1
					)
					(return)
				)
				(= temp2 (& temp0 $00ff))
				(= theCase
					(switch (& temp0 $ff00)
						(256 (+ temp2 1))
						(512 (+ temp2 18))
						(768 (+ temp2 26))
						(1024 (+ temp2 61))
					)
				)
				(cond 
					((not (Message MsgGet modNum noun V_ASK theCase 1))
						(messager say: noun V_ASK 81 0 0 modNum)
					)
					((proc27_0 7 [global296 (- theCase 2)])
						(messager say: noun V_ASK 1 0 0 modNum)
					)
					(else
						(messager say: noun V_ASK theCase 0 0 modNum)
						(proc27_1 7 @[global296 (- theCase 2)])
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance Watney of Actor
	(properties
		noun 1
		modNum 1886
		view 815
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem &tmp temp0 theCase temp2)
		(if (== theVerb V_ASK)
			(if
				(==
					(= temp0
						(if (== argc 2)
							theItem
						else
							(curRoom setInset: (ScriptID 20 0))
						)
					)
					-1
				)
				(return)
			)
			(= temp2 (& temp0 $00ff))
			(= theCase
				(switch (& temp0 $ff00)
					(256 (+ temp2 1))
					(512 (+ temp2 18))
					(768 (+ temp2 26))
					(1024 (+ temp2 61))
				)
			)
			(cond 
				((not (Message MsgGet modNum noun V_ASK theCase 1))
					(messager say: noun V_ASK 81 0 0 modNum)
				)
				((proc27_0 8 [global296 (- theCase 2)])
					(messager say: noun V_ASK 1 0 0 modNum)
				)
				(else
					(messager say: noun V_ASK theCase 0 0 modNum)
					(proc27_1 8 @[global296 (- theCase 2)])
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance Yvette of Actor
	(properties
		noun 1
		modNum 1885
		view 817
		signal ignrAct
		scaleSignal scalable
	)
	
	(method (doVerb theVerb theItem &tmp temp0 theCase temp2)
		(cond 
			((== theVerb V_TALK)
				(if (not (Bset 113))
					(messager say: noun theVerb 80 0 0 modNum)
				else
					(messager say: noun theVerb 28 0 0 modNum)
				)
			)
			((OneOf theVerb V_ASK V_NOTEBOOK)
				(if
					(==
						(= temp0
							(if (== argc 2)
								theItem
							else
								(curRoom setInset: (ScriptID 20 0))
							)
						)
						-1
					)
					(return)
				)
				(= temp2 (& temp0 $00ff))
				(= theCase
					(switch (& temp0 $ff00)
						(256 (+ temp2 1))
						(512 (+ temp2 18))
						(768 (+ temp2 26))
						(1024 (+ temp2 61))
					)
				)
				(switch temp0
					(271
						(messager say: noun V_ASK 30 0 0 modNum)
					)
					(267
						(cond 
							((Btst 158)
								(if (proc27_0 9 global365)
									(messager say: noun V_ASK 1 0 0 modNum)
								else
									(messager say: noun V_ASK 71 0 0 modNum)
									(proc27_1 9 @global365)
								)
							)
							((proc27_0 9 global306)
								(messager say: noun V_ASK 1 0 0 modNum)
							)
							(else
								(messager say: noun V_ASK 12 0 0 modNum)
								(proc27_1 9 @global306)
							)
						)
					)
					(263
						(if (proc27_0 9 global302)
							(messager say: noun V_ASK 1 0 0 modNum)
						else
							(messager say: noun V_ASK 8 0 0 modNum)
							(proc27_1 9 @global302)
						)
					)
					(else 
						(cond 
							((not (Message MsgGet modNum noun V_ASK theCase 1))
								(messager say: noun V_ASK 81 0 0 modNum)
							)
							((proc27_0 9 [global296 (- theCase 2)])
								(messager say: noun V_ASK 1 0 0 modNum)
							)
							(else
								(messager say: noun V_ASK theCase 0 0 modNum)
								(proc27_1 9 @[global296 (- theCase 2)])
							)
						)
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance Ziggy of Actor
	(properties
		noun 1
		modNum 1890
		view 816
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem &tmp temp0 theCase temp2)
		(if (OneOf theVerb V_ASK V_NOTEBOOK)
			(if
				(==
					(= temp0
						(if (== argc 2)
							theItem
						else
							(curRoom setInset: (ScriptID 20 0))
						)
					)
					-1
				)
				(return)
			)
			(= temp2 (& temp0 $00ff))
			(= theCase
				(switch (& temp0 $ff00)
					(256 (+ temp2 1))
					(512 (+ temp2 18))
					(768 (+ temp2 26))
					(1024 (+ temp2 61))
				)
			)
			(cond 
				((not (Message MsgGet modNum noun V_ASK theCase 1))
					(messager say: noun V_ASK 81 0 0 modNum)
				)
				((proc27_0 11 [global296 (- theCase 2)])
					(messager say: noun V_ASK 1 0 0 modNum)
				)
				(else
					(messager say: noun V_ASK theCase 0 0 modNum)
					(proc27_1 11 @[global296 (- theCase 2)])
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
)
