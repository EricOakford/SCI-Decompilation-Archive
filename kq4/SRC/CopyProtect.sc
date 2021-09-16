;;; Sierra Script 1.0 - (do not remove this comment)
(script# 701)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use Menu)

(public
	copyProtect 0
)

(local
	randomPick
	requestText
	requestSum
	[userInput 30]
	i
	ch
	inputSum
)
(instance copyProtect of Room
	(properties
		picture 991
	)
	
	(method (init)
		(super init:)
		(switch (= randomPick (Random 1 79))
			(1 (= requestSum 431))
			(2 (= requestSum 521))
			(3 (= requestSum 535))
			(4 (= requestSum 323))
			(5 (= requestSum 621))
			(6 (= requestSum 480))
			(7 (= requestSum 303))
			(8 (= requestSum 671))
			(9 (= requestSum 393))
			(10 (= requestSum 380))
			(11 (= requestSum 470))
			(12 (= requestSum 287))
			(13 (= requestSum 352))
			(14 (= requestSum 316))
			(15 (= requestSum 317))
			(16 (= requestSum 355))
			(17 (= requestSum 616))
			(18 (= requestSum 475))
			(19 (= requestSum 383))
			(20 (= requestSum 425))
			(21 (= requestSum 516))
			(22 (= requestSum 673))
			(23 (= requestSum 550))
			(24 (= requestSum 666))
			(25 (= requestSum 465))
			(26 (= requestSum 617))
			(27 (= requestSum 628))
			(28 (= requestSum 395))
			(29 (= requestSum 505))
			(30 (= requestSum 492))
			(31 (= requestSum 478))
			(32 (= requestSum 455))
			(33 (= requestSum 455))
			(34 (= requestSum 599))
			(35 (= requestSum 431))
			(36 (= requestSum 565))
			(37 (= requestSum 455))
			(38 (= requestSum 467))
			(39 (= requestSum 691))
			(40 (= requestSum 535))
			(41 (= requestSum 432))
			(42 (= requestSum 621))
			(43 (= requestSum 372))
			(44 (= requestSum 536))
			(45 (= requestSum 395))
			(46 (= requestSum 496))
			(47 (= requestSum 372))
			(48 (= requestSum 389))
			(49 (= requestSum 374))
			(50 (= requestSum 523))
			(51 (= requestSum 286))
			(52 (= requestSum 454))
			(53 (= requestSum 273))
			(54 (= requestSum 579))
			(55 (= requestSum 282))
			(56 (= requestSum 354))
			(57 (= requestSum 602))
			(58 (= requestSum 463))
			(59 (= requestSum 653))
			(60 (= requestSum 691))
			(61 (= requestSum 309))
			(62 (= requestSum 519))
			(63 (= requestSum 380))
			(64 (= requestSum 446))
			(65 (= requestSum 302))
			(66 (= requestSum 599))
			(67 (= requestSum 357))
			(68 (= requestSum 399))
			(69 (= requestSum 381))
			(70 (= requestSum 771))
			(71 (= requestSum 378))
			(72 (= requestSum 378))
			(73 (= requestSum 434))
			(74 (= requestSum 614))
			(75 (= requestSum 395))
			(76 (= requestSum 381))
			(77 (= requestSum 456))
			(78 (= requestSum 384))
			(79 (= requestSum 686))
		)
		(switch randomPick
			(1
				(= requestText
					{On page 2, what is the fourth word of the first sentence?}
				)
			)
			(2
				(= requestText
					{On page 2, what is the fourth word of the second paragraph?}
				)
			)
			(3
				(= requestText
					{On page 3, what is the fourth word in the first paragraph?}
				)
			)
			(4
				(= requestText
					{On page 3, what is the sixth word in the first paragraph?}
				)
			)
			(5
				(= requestText
					{On page 3, what is the fourth word in the third paragraph?}
				)
			)
			(6
				(= requestText
					{On page 3, what is the third word in the second paragraph?}
				)
			)
			(7
				(= requestText
					{On page 8, what is the first word of the first paragraph?}
				)
			)
			(8
				(= requestText
					{On page 3, what is the seventh word in the third paragraph?}
				)
			)
			(9
				(= requestText
					{On page 3, what is the last word of the third paragraph?}
				)
			)
			(10
				(= requestText
					{On page 4, what is the third word in the first paragraph?}
				)
			)
			(11
				(= requestText
					{On page 4, what is the last word in the third paragraph?}
				)
			)
			(12
				(= requestText
					{On page 4, what is the sixth word of the first paragraph?}
				)
			)
			(13
				(= requestText
					{On page 3, what is the eighth word in the third paragraph?}
				)
			)
			(14
				(= requestText
					{On page 4, what is the seventh word in the first paragraph?}
				)
			)
			(15
				(= requestText
					{On page 4, what is the third word in the third paragraph?}
				)
			)
			(16
				(= requestText
					{On page 4, what is the eighth word in the third paragraph?}
				)
			)
			(17
				(= requestText
					{On page 5, what is the fifth word in the second paragraph?}
				)
			)
			(18
				(= requestText
					{On page 5, what is the second word in the third paragraph?}
				)
			)
			(19
				(= requestText
					{On page 5, what is the fourth word in the first paragraph?}
				)
			)
			(20
				(= requestText
					{On page 5, what is the ninth word in the fifth paragraph?}
				)
			)
			(21
				(= requestText
					{On page 6, what is the third word in the first paragraph?}
				)
			)
			(22
				(= requestText
					{On page 6, what is the fifth word in the first paragraph?}
				)
			)
			(23
				(= requestText
					{On page 6, what is the sixth word in the second paragraph?}
				)
			)
			(24
				(= requestText
					{On page 7, what is the first word in the first paragraph?}
				)
			)
			(25
				(= requestText
					{On page 7, what is the tenth word in the first paragraph?}
				)
			)
			(26
				(= requestText
					{On page 7, what is the third word in the second paragraph?}
				)
			)
			(27
				(= requestText
					{On page 7, what is the fifth word in the second paragraph?}
				)
			)
			(28
				(= requestText
					{On page 7, what is the second word in the third paragraph?}
				)
			)
			(29
				(= requestText
					{On page 8, what is the fifth word in the first paragraph?}
				)
			)
			(30
				(= requestText
					{On page 8, what is the last word of the first sentence in the first paragraph?}
				)
			)
			(31
				(= requestText
					{On page 6, what is the last word in the second paragraph?}
				)
			)
			(32
				(= requestText
					{On page 8, what is the second word in the third paragraph?}
				)
			)
			(33
				(= requestText
					{On page 8, what is the sixth word in the third paragraph?}
				)
			)
			(34
				(= requestText
					{On page 8, what is the ninth word in the third paragraph?}
				)
			)
			(35
				(= requestText
					{On page 9, what is the third word in the first paragraph?}
				)
			)
			(36
				(= requestText
					{On page 9, what is the fourth word in the first paragraph?}
				)
			)
			(37
				(= requestText
					{On page 9, what is the seventh word in the first paragraph?}
				)
			)
			(38
				(= requestText
					{On page 6, what is the fourth word in the second paragraph?}
				)
			)
			(39
				(= requestText
					{On page 6, what is the eighth word in the second paragraph?}
				)
			)
			(40
				(= requestText
					{On page 9, what is the seventh word in the second paragraph?}
				)
			)
			(41
				(= requestText
					{On page 5, what is the seventh word in the fifth paragraph?}
				)
			)
			(42
				(= requestText
					{On page 2, what is the sixth word in the second paragraph?}
				)
			)
			(43
				(= requestText
					{On page 2, what is the eighth word in the second paragraph?}
				)
			)
			(44
				(= requestText
					{On page 3, what is the tenth word in the first paragraph?}
				)
			)
			(45
				(= requestText
					{On page 4, what is the second word in the second paragraph?}
				)
			)
			(46
				(= requestText
					{On page 4, what is the fourth word in the third paragraph?}
				)
			)
			(47
				(= requestText
					{On page 5, what is the third word in the second paragraph?}
				)
			)
			(48
				(= requestText
					{On page 5, what is the eighth word in the third paragraph?}
				)
			)
			(49
				(= requestText
					{On page 5, what is the eighth word in the fourth paragraph?}
				)
			)
			(50
				(= requestText
					{On page 6, what is the ninth word in the first paragraph?}
				)
			)
			(51
				(= requestText
					{On page 7, what is the ninth word of the third paragraph?}
				)
			)
			(52
				(= requestText
					{What is the second word of the first paragraph in the OVERVIEW?}
				)
			)
			(53
				(= requestText
					{What is the first word of the second paragraph in the OVERVIEW?}
				)
			)
			(54
				(= requestText
					{What is the third word of the second paragraph in the OVERVIEW?}
				)
			)
			(55
				(= requestText
					{What is the sixth word of the first paragraph in the OVERVIEW?}
				)
			)
			(56
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the first word of tip #1 (HOW TO MOVE AROUND)?}
				)
			)
			(57
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the sixth word of tip #1 (HOW TO MOVE AROUND)?}
				)
			)
			(58
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the fifth word in the first paragraph of tip #2 (STAY OUT OF DANGER)}
				)
			)
			(59
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the eighth word in the second paragraph of tip #2 (STAY OUT OF DANGER)}
				)
			)
			(60
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the fourth word in the second paragraph of tip #2 (STAY OUT OF DANGER)}
				)
			)
			(61
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the first word of tip #3 (BE OBSERVANT)?}
				)
			)
			(62
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the fourth word of tip #3 (BE OBSERVANT)?}
				)
			)
			(63
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the last word of tip #3 (BE OBSERVANT)?}
				)
			)
			(64
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the last word of tip #1 (HOW TO MOVE AROUND)?}
				)
			)
			(65
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the first word of tip #4 (MAP YOUR PROGRESS)?}
				)
			)
			(66
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the fifth word of tip #4 (MAP YOUR PROGRESS)?}
				)
			)
			(67
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the seventh word of tip #4 (MAP YOUR PROGRESS)?}
				)
			)
			(68
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the ninth word of tip #4 (MAP YOUR PROGRESS)?}
				)
			)
			(69
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the fourth word of tip #5 (BRING ALONG SOME HELP)?}
				)
			)
			(70
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the seventh word of tip #5 (BRING ALONG SOME HELP)?}
				)
			)
			(71
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the ninth word of tip #5 (BRING ALONG SOME HELP)?}
				)
			)
			(72
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the last word of tip #5 (BRING ALONG SOME HELP)?}
				)
			)
			(73
				(= requestText
					{What is the last word starting with "b" in the verb list?}
				)
			)
			(74
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the last word of tip #7 (LEAVE NO STONE UNTURNED)?}
				)
			)
			(75
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the fourth word in the first paragraph of tip #8 (AT THE END OF YOUR ROPE)?}
				)
			)
			(76
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the sixth word in the first paragraph of tip #8 (AT THE END OF YOUR ROPE)?}
				)
			)
			(77
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the third word in the second paragraph of tip #8 (AT THE END OF YOUR ROPE)?}
				)
			)
			(78
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the last word in the first paragraph of tip #8 (AT THE END OF YOUR ROPE)?}
				)
			)
			(79
				(= requestText
					{In the section TIPS FOR NEW ADVENTURE PLAYERS, what is the eighth word in the first paragraph of tip #2 (STAY OUT OF DANGER)?}
				)
			)
		)
	)
	
	(method (doit)
		(= userInput 0)
		(= inputSum NULL)
		(HandsOn)
		(= inCutscene FALSE)
		(Print
			(Format @str 701 0 requestText)
			#at 7 5
			#font smallFont
			#width 290
			#edit @userInput 30
		)
		(if (and debugging (= i (ReadNumber @userInput)))
			(TheMenuBar draw:)
			(StatusLine enable:)
			(self newRoom: i)
			(return)
		)
		(= userFont smallFont)
		(for ((= i 0)) (< i (StrLen @userInput)) ((++ i))
			(= ch (& (= ch (StrAt @userInput i)) $005f)) ;ch = userInput[i] & 0x5F -- get uppercased ch
			(StrAt @userInput i ch) ;put that uppercase character back so we can detect BOBALU later on
			(+= inputSum ch)
		)
		;A whole cond block was missing here for some reason. I rewrote it. -- Kawa
		(cond
			;Bobalu was only in 1988 release -- enable if you want it. -- Kawa
			;((not (StrCmp @userInput {BOBALU})) (curRoom newRoom: 700))
			((== inputSum requestSum)
				(curRoom newRoom: 700)
			)
			(else
				(Print 701 1)
				(= quit TRUE)
			)
		)
	)
)
