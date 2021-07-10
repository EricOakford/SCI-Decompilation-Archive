;;; Sierra Script 1.0 - (do not remove this comment)
(script# 385)
(include game.sh)
(use Intrface)
(use subMarine)

(public
	proc385_0 0
)

(local
	[local0 12]
)
(procedure (proc385_0 param1 param2)
	(localproc_01bf)
	(return
		(if
			(and
				(<= param1 ((subMarine script?) state?))
				(not [local0 param2])
			)
			(switch (Random 0 3)
				(0 (Print 385 0))
				(1 (Print 385 1))
				(2 (Print 385 2))
				(3 (Print 385 3))
			)
			(Print 385 4)
			(localproc_02a4 param2)
			(localproc_02f4 (+ [local0 param2] 0))
			(localproc_02f4 (+ [local0 (+ param2 1)] 0))
			(switch (Random 0 3)
				(0 (Print 385 5))
				(1 (Print 385 6))
				(2 (Print 385 7))
				(3 (Print 385 8))
			)
			(Print 385 9)
			(localproc_02a4 (+ param2 2))
			(localproc_02f4 (+ [local0 (+ param2 2)] 16))
			(localproc_02f4 (+ [local0 (+ param2 3)] 16))
			(localproc_0146)
			(return 1)
		else
			(return 0)
		)
	)
)

(procedure (localproc_0146)
	(subMarine
		msg12:
			(^
				(^
					(^ (<< [local0 0] $000c) (<< [local0 1] $0008))
					(<< [local0 2] $0004)
				)
				[local0 3]
			)
	)
	(subMarine
		msg34:
			(^
				(^
					(^ (<< [local0 4] $000c) (<< [local0 5] $0008))
					(<< [local0 6] $0004)
				)
				[local0 7]
			)
	)
	(subMarine
		msg56:
			(^
				(^
					(^ (<< [local0 8] $000c) (<< [local0 9] $0008))
					(<< [local0 10] $0004)
				)
				[local0 11]
			)
	)
)

(procedure (localproc_01bf)
	(= [local0 0] (>> (subMarine msg12?) $000c))
	(= [local0 1] (& (>> (subMarine msg12?) $0008) $000f))
	(= [local0 2] (& (>> (subMarine msg12?) $0004) $000f))
	(= [local0 3] (& (subMarine msg12?) $000f))
	(= [local0 4] (>> (subMarine msg34?) $000c))
	(= [local0 5] (& (>> (subMarine msg34?) $0008) $000f))
	(= [local0 6] (& (>> (subMarine msg34?) $0004) $000f))
	(= [local0 7] (& (subMarine msg34?) $000f))
	(= [local0 8] (>> (subMarine msg56?) $000c))
	(= [local0 9] (& (>> (subMarine msg56?) $0008) $000f))
	(= [local0 10] (& (>> (subMarine msg56?) $0004) $000f))
	(= [local0 11] (& (subMarine msg56?) $000f))
)

(procedure (localproc_02a4 param1)
	(= [local0 param1] (localproc_02bc))
	(= [local0 (+ param1 1)] (localproc_02bc))
)

(procedure (localproc_02bc &tmp temp0 temp1)
	(asm
		pushi    2
		pushi    1
		pushi    15
		callk    Random,  4
		sat      temp0
code_02c7:
		ldi      0
		sat      temp1
code_02cb:
		lst      temp1
		ldi      12
		lt?     
		bnt      code_02f1
		lst      temp0
		lat      temp1
		lali     local0
		eq?     
		bt       code_02e5
		+at      temp1
		jmp      code_02cb
		bnt      code_02f1
code_02e5:
		pushi    2
		pushi    1
		pushi    15
		callk    Random,  4
		sat      temp0
		jmp      code_02c7
code_02f1:
		lat      temp0
		ret     
	)
)

(procedure (localproc_02f4 param1)
	(switch param1
		(1 (Print 385 10))
		(2 (Print 385 11))
		(3 (Print 385 12))
		(4 (Print 385 13))
		(5 (Print 385 14))
		(6 (Print 385 15))
		(7 (Print 385 16))
		(8 (Print 385 17))
		(9 (Print 385 18))
		(10 (Print 385 19))
		(11 (Print 385 20))
		(12 (Print 385 21))
		(13 (Print 385 22))
		(14 (Print 385 23))
		(15 (Print 385 24))
		(17 (Print 385 25))
		(18 (Print 385 26))
		(19 (Print 385 27))
		(20 (Print 385 28))
		(21 (Print 385 29))
		(22 (Print 385 30))
		(23 (Print 385 31))
		(24 (Print 385 32))
		(25 (Print 385 33))
		(26 (Print 385 34))
		(27 (Print 385 35))
		(28 (Print 385 36))
		(29 (Print 385 37))
		(30 (Print 385 38))
		(31 (Print 385 39))
	)
)
