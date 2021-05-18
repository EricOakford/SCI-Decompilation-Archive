;;; Sierra Script 1.0 - (do not remove this comment)
(script# 283)
(include game.sh)
(use Main)
(use atsgl)
(use Intrface)
(use System)

(public
	CeliAct3 0
)

(local
	local0
	local1 = [283 0 283 1 283 2 283 3 283 4 283 5 283 6 283 7 283 8 283 9 283 10 283 11 283 12 283 13 283 14 283 15 283 16 283 17 283 18 283 19 283 20 283 21 283 22 283 23 283 24 283 25 283 26 283 27 283 28 283 29 283 30 283 31 283 32 283 33 283 34 283 35 283 36 283 37 283 38 283 39 283 40 283 41 283 42 283 43 283 44 283 45 283 46 283 47 283 48 283 49 283 50 283 51 283 52 283 53 283 54 283 55 283 56 283 57 283 58 283 59 283 60 283 61 283 62 283 63 283 64 283 65 283 66 283 67 283 68 283 69 283 70 283 71 283 72 283 73 283 74 283 75 283 76 283 77 283 78 283 79 283 80 283 81 283 82 283 83 283 84 283 85 283 86 283 87]
	local177 = [8256 10240 8320 8196 64 65 68 320 192 512 528 8 16 1 4 1024 32 40 12288 256 260 272 16385 8208 16384 128 8200 16388 16392 8194 16400 16416 16448 16512 9216 8256 10240 8320 8196 64 65 68 320 192 512 528 8 16 1 4 1024 32 40 12288 256 272 260 16385 8208 16384 128 8200 16388 16392 8194 16400 16416 16448 16512 9216 8196 8208 16392 8194 9216 8196 8208 16392 8194 9216 2 2 2 2 2 -1]
	local263 = [0 35 70 75 80]
	local268 =  81
	local269 = [82 83 84 85]
)
(instance CeliAct3 of Script
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 283)
	)
	
	(method (handleEvent event &tmp temp0 temp1 i temp3)
		(cond 
			((event claimed?))
			((and (== (event type?) saidEvent) global212)
				(= local0 -1)
				(= i [local263 (- global212 1)])
				(while (< i [local263 global212])
					(if (== global211 [local177 i])
						(= local0 i)
						(break)
					)
					(++ i)
				)
				(if
					(and
						(!= local0 -1)
						(or (!= global211 9216) (> currentAct 2))
					)
					(= temp1 (<< local0 $0001))
					(cond 
						((and (== local0 48) (& deadGuests $0001)) (= temp3 85))
						((and (== local0 60) (& deadGuests $0002)) (= temp3 86))
						((and (== local0 49) (& deadGuests $0004)) (= temp3 87))
						(else (= temp3 0))
					)
					(if temp3
						(= [local1 temp1] [local1 (= temp3 (* temp3 2))])
						(= [local1 (+ temp1 1)] [local1 (++ temp3)])
					)
					(cond 
						((>= local0 local268)
							(global209 claimed: 1)
							(Print [local1 temp1] [local1 (++ temp1)])
						)
						(
							(and
								(not
									(proc243_1 local0 [local1 temp1] [local1 (++ temp1)])
								)
								(== [local177 (++ i)] 0)
							)
							(DaddySays [local1 (++ temp1)] [local1 (++ temp1)])
						)
					)
					(if (== global212 1)
						(switch global211
							(64 (Bset 18))
							(1024 (Bset 22))
							(256 (Bset 15))
						)
					)
				else
					(event claimed: FALSE)
				)
			)
		)
		(client setScript: 0)
	)
)
