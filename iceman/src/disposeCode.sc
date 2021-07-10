;;; Sierra Script 1.0 - (do not remove this comment)
(script# 818)
(include game.sh)
(use LoadMany)
(use System)

(public
	disposeCode 0
)

(instance disposeCode of Code

	(method (doit roomNum)
		(LoadMany FALSE
			AVOIDER JUMP ORBIT PATH EXTRA TEXTRA RFEATURE
			GROOPER DEMO NAMEFIND CHASE FOLLOW WANDER REVERSE TIMER
			SORT COUNT DPATH QSCRIPT FORCOUNT CAT 802 800
			TRACK 804 807 811 812 GOTOSAID LASTLINK 309
			TIMEDCUE 361 APPROACH SORTCOPY SIGHT 820 821 823
			824 316 822 315
		)
		(if (not (OneOf roomNum 100 102))
			(DisposeScript QSOUND)
		)
		(if
			(not
				(OneOf roomNum
					53 54 55 56 57 58 61 45 46 47
					59 60 63 64 65 66 67 68 62 43
				)
			)
			(DisposeScript SMOOPER)
		)
		(if
			(not
				(OneOf roomNum
					53 54 55 56 57 58 61 45 46 47
					1 2 3 4 5 7 12 13 9 24 8 16
				)
			)
			(DisposeScript 809)
		)
		(if
			(not
				(OneOf roomNum
					25 26 27 28 29 30 31 32 33 34
					35 36 37 38 39 40 41 42 51 50
				)
			)
			(DisposeScript 806)
			(DisposeScript 817)
			(DisposeScript 827)
		)
		(DisposeScript 818)
	)
)
