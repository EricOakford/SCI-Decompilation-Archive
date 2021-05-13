;;; Sierra Script 1.0 - (do not remove this comment)
(script# 376)
(include game.sh)
(use Main)
(use atsgl)
(use Intrface)
(use System)

(public
	ColoAct8 0
)

(local
	local0
	colMsg = [
		376 0
		376 1
		376 2
		376 3
		376 4
		376 5
		376 6
		376 7
		376 8
		376 9
		376 10
		376 11
		376 12
		376 13
		376 14
		376 15
		376 16
		376 17
		376 18
		376 19
		376 20
		376 21
		376 22
		376 23
		376 24
		376 25
		376 26
		376 27
		376 28
		376 29
		376 30
		376 31
		376 32
		376 33
		376 34
		376 35
		376 36
		376 37
		376 38
		376 39
		376 40
		376 41
		376 42
		376 43
		376 44
		376 45
		376 46
		376 47
		376 48
		376 49
		376 50
		376 51
		376 52
		376 53
		376 54
		376 55
		376 56
		376 57
		376 58
		376 59
		376 60
		376 61
		376 62
		376 63
		376 64
		376 65
		376 66
		376 67
		376 68
		376 69
		376 70
		376 71
		376 72
		376 73
		376 74
		376 75
		376 76
		376 77
		376 78
		376 79
		376 80
		376 81
		376 82
		376 83
		376 84
		376 85
		376 86
		376 87
		376 88
		376 89
		376 90
		376 91
		376 92
		376 93
		376 94
		376 95
		376 96
		376 97
		376 98
		376 99
		]
	local201 = [8256 10240 8320 16386 8196 2 9216 64 65 68 192 320 8 16 1 4 1024 1040 32 34 40 12288 256 260 272 16385 8208 128 8200 16388 8194 16392 16400 16416 16448 16512 8256 10240 8320 16386 8196 2 9216 64 65 68 192 320 8 16 1 4 1024 1040 32 34 40 12288 256 260 272 16385 8208 128 8200 16388 8194 16392 16400 16416 16448 16512 16386 8196 9216 12288 8208 8194 16392 16386 8196 9216 12288 8208 8194 16392 512 512 512 512 512 512 -1]
	local294 = [0 36 72 79 86]
	local299 =  87
	local300 = [88 89 90 91 92]
)
(instance ColoAct8 of Script
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 376)
	)
	
	(method (handleEvent event &tmp temp0 temp1 i temp3)
		(cond 
			((event claimed?))
			((and (== (event type?) saidEvent) global212)
				(= local0 -1)
				(= i [local294 (- global212 1)])
				(while (< i [local294 global212])
					(if (== global211 [local201 i])
						(= local0 i)
						(break)
					)
					(++ i)
				)
				(if (!= local0 -1)
					(= temp1 (<< local0 $0001))
					(cond 
						((and (== local0 50) (& deadGuests $0001)) (= temp3 96))
						((and (== local0 63) (& deadGuests $0002)) (= temp3 99))
						((and (== local0 51) (& deadGuests $0004)) (= temp3 94))
						((and (== local0 48) (& deadGuests $0008)) (= temp3 93))
						(
							(and
								(or (== local0 49) (== local0 52) (== local0 53))
								(& deadGuests $0010)
							)
							(switch local0
								(49 (= temp3 95))
								(52 (= temp3 97))
								(53 (= temp3 98))
							)
						)
						((and (== local0 43) (& deadGuests $0020)) (= temp3 92))
						(else (= temp3 0))
					)
					(if temp3
						(= [colMsg temp1] [colMsg (= temp3 (* temp3 2))])
						(= [colMsg (+ temp1 1)] [colMsg (+ temp3 1)])
					)
					(cond 
						((>= local0 local299)
							(global209 claimed: 1)
							(Print [colMsg temp1] [colMsg (++ temp1)])
						)
						(
							(and
								(not
									(proc243_1 local0 [colMsg temp1] [colMsg (++ temp1)])
								)
								(== [local201 (++ i)] 0)
							)
							(DaddySays [colMsg (++ temp1)] [colMsg (++ temp1)])
						)
					)
				)
			)
		)
		(client setScript: 0)
	)
)
