;;; Sierra Script 1.0 - (do not remove this comment)
(script# 306)
(include game.sh)
(use Main)
(use Intrface)
(use System)

(public
	readBook 0
	;readBook 1	;decompiler goof
	readNote 1	;was 2
)

(instance readBook of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(Print 306 0
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(2
				(Print 306 1
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(3
				(Print 306 2
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(4
				(Print 306 3
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(5
				(Print 306 4
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(6
				(Print 306 5
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(7
				(Print 306 6
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(8
				(Print 306 7
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(9
				(Print 306 8
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(10
				(Print 306 9
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(11
				(Print 306 10
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(12
				(Print 306 11
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(13
				(Print 306 12
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(14
				(Print 306 13
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(15
				(Print 306 14
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(16
				(Print 306 15
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(17
				(Print 306 16
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(18
				(Print 306 17
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(19
				(Print 306 18
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(20
				(Print 306 19
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(21
				(Print 306 20
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(22
				(Print 306 21
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(23
				(Print 306 22
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(24
				(Print 306 23
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(25
				(Print 306 24
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(26
				(Print 306 25
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(27
				(Print 306 26
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(28
				(Print 306 27
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(29
				(Print 306 28
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
			(30
				(Print 306 29
					#font smallFont
					#mode teJustCenter
					#at -1 15
					#width 290
				)
			)
		)
		(DisposeScript 306)
	)
)

(instance readNote of Script
	(method (cue)
		(switch (Random 1 9)
			(1 (Print 306 30))
			(2 (Print 306 31))
			(3 (Print 306 32))
			(4 (Print 306 33))
			(5 (Print 306 34))
			(6 (Print 306 35))
			(7 (Print 306 36))
			(8 (Print 306 37))
			(9 (Print 306 38))
			(10 (Print 306 39))
		)
		(DisposeScript 306)
	)
)
