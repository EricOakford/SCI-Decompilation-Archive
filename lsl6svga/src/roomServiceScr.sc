;;; Sierra Script 1.0 - (do not remove this comment)
(script# 621)
(include sci.sh)
(use Main)
(use fileScr)
(use System)

(public
	roomServiceScr 0
)

(local
	local0
	local1
)
(instance roomServiceScr of Script
	(properties)
	
	(method (init)
		(= local0 1)
		(= local1 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst 97)
					(Bclr 97)
					(= register (= cycles (= state 2)))
					(= local0 -1)
				else
					(switch local0
						(1
							(= local0 0)
							(messager say: 1 4 66 1 self 610)
						)
						(0
							(messager say: 1 4 23 0 self 610)
						)
						(else 
							(= local0 0)
							(messager say: 1 4 22 0 self 610)
						)
					)
				)
			)
			(1
				(messager say: 1 4 66 2 self 610)
			)
			(2
				((ScriptID 610 0) init: self)
			)
			(3
				(switch register
					(0 (self init:))
					(1
						(= state 9)
						(messager say: 1 4 24 0 self 610)
					)
					(2
						(= state 39)
						(messager say: 1 4 37 0 self 610)
					)
					(3
						(= state 69)
						(messager say: 1 4 50 0 self 610)
					)
					(4
						(messager say: 1 4 63 0 self 610)
					)
					(5
						(messager say: 1 4 64 0 self 610)
					)
					(6
						(messager say: 1 4 65 0 self 610)
					)
					(-1 (self dispose:))
					(else  (self dispose:))
				)
			)
			(4 (self dispose:))
			(10
				((ScriptID 610 0) init: self)
			)
			(11
				(switch register
					(1
						(messager say: 1 4 25 0 self 610)
					)
					(2
						(= state 19)
						(messager say: 1 4 29 0 self 610)
					)
					(3
						(= state 29)
						(messager say: 1 4 33 0 self 610)
					)
					(-1 (self dispose:))
					(else  (self init:))
				)
			)
			(12
				((ScriptID 610 0) init: self)
			)
			(13
				(= state 995)
				(switch register
					(1
						(messager say: 1 4 26 0 self 610)
					)
					(2
						(messager say: 1 4 27 0 self 610)
					)
					(3
						(messager say: 1 4 28 0 self 610)
					)
					(-1 (self dispose:))
					(else  (self init:))
				)
			)
			(20
				((ScriptID 610 0) init: self)
			)
			(21
				(switch register
					(1
						(messager say: 1 4 30 0 self 610)
					)
					(2
						(messager say: 1 4 31 0 self 610)
					)
					(3
						(= local1 1)
						(messager say: 1 4 32 1 self 610)
					)
					(-1 (self dispose:))
					(else  (self init:))
				)
			)
			(22
				((ScriptID 610 0) init: self)
			)
			(23
				(switch register
					(-2
						(if local1
							(= local1 0)
							(messager say: 1 4 32 2 self 610)
						else
							(= state 996)
							(self cue:)
						)
					)
					(-1 (self dispose:))
					(else 
						(= local0 -1)
						(self init:)
					)
				)
			)
			(24 (self dispose:))
			(30
				((ScriptID 610 0) init: self)
			)
			(31
				(= state 995)
				(switch register
					(1
						(messager say: 1 4 34 0 self 610)
					)
					(2
						(messager say: 1 4 35 0 self 610)
					)
					(3
						(messager say: 1 4 36 0 self 610)
					)
					(-1 (self dispose:))
					(else  (self init:))
				)
			)
			(40
				((ScriptID 610 0) init: self)
			)
			(41
				(switch register
					(1
						(messager say: 1 4 38 0 self 610)
					)
					(2
						(= state 49)
						(messager say: 1 4 42 0 self 610)
					)
					(3
						(= state 59)
						(messager say: 1 4 46 0 self 610)
					)
					(-1 (self dispose:))
					(else  (self init:))
				)
			)
			(42
				((ScriptID 610 0) init: self)
			)
			(43
				(= state 995)
				(switch register
					(1
						(messager say: 1 4 39 0 self 610)
					)
					(2
						(messager say: 1 4 40 0 self 610)
					)
					(3
						(messager say: 1 4 41 0 self 610)
					)
					(-1 (self dispose:))
					(else  (self init:))
				)
			)
			(50
				((ScriptID 610 0) init: self)
			)
			(51
				(= state 995)
				(switch register
					(1
						(messager say: 1 4 43 0 self 610)
					)
					(2
						(messager say: 1 4 44 0 self 610)
					)
					(3
						(messager say: 1 4 45 0 self 610)
					)
					(-1 (self dispose:))
					(else  (self init:))
				)
			)
			(60
				((ScriptID 610 0) init: self)
			)
			(61
				(switch register
					(1
						(= state 995)
						(messager say: 1 4 47 0 self 610)
					)
					(2
						(= state 995)
						(messager say: 1 4 48 0 self 610)
					)
					(3
						(messager say: 1 4 49 0 self 610)
					)
					(-1 (self dispose:))
					(else  (self init:))
				)
			)
			(62 (self dispose:))
			(70
				((ScriptID 610 0) init: self)
			)
			(71
				(switch register
					(1
						(= state 79)
						(messager say: 1 4 51 0 self 610)
					)
					(2
						(messager say: 1 4 55 0 self 610)
					)
					(3
						(= state 89)
						(messager say: 1 4 59 0 self 610)
					)
					(-1 (self dispose:))
					(else  (self init:))
				)
			)
			(72
				(= state 995)
				(messager say: 1 4 58 0 self 610)
			)
			(80
				((ScriptID 610 0) init: self)
			)
			(81
				(= state 995)
				(switch register
					(1
						(messager say: 1 4 52 0 self 610)
					)
					(2
						(messager say: 1 4 53 0 self 610)
					)
					(3
						(messager say: 1 4 54 0 self 610)
					)
					(-1 (self dispose:))
					(else  (self init:))
				)
			)
			(90
				((ScriptID 610 0) init: self)
			)
			(91
				(= state 995)
				(switch register
					(1
						(messager say: 1 4 60 0 self 610)
					)
					(2
						(messager say: 1 4 61 0 self 610)
					)
					(3
						(messager say: 1 4 62 0 self 610)
					)
					(-1 (self dispose:))
					(else  (self init:))
				)
			)
			(996
				((ScriptID 610 0) init: self)
			)
			(997
				(switch register
					(-2
						(messager say: 1 4 21 1 self 610)
					)
					(-1 (self dispose:))
					(else 
						(= local0 -1)
						(self init:)
					)
				)
			)
			(998 (= seconds 8))
			(999
				(messager say: 1 4 21 2 self 610)
			)
			(1000 (self dispose:))
		)
	)
)
