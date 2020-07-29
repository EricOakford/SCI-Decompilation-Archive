;;; Sierra Script 1.0 - (do not remove this comment)
(script# 480)
(include game.sh)
(use Main)
(use LLRoom)
(use PrintD)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm480 0
)

(local
	printRet
	[local1 29] = [0 273 274 275 297 467 387 147 161 291 861 911 146 892 752 753 754 755 756 751 526 851 482 483 484]
	creditColor
)
(procedure (CreditDisplay theString param2 &tmp temp0 theColor)
	(curRoom drawPic: 1 13)
	(= theColor
		(switch (Random 0 5)
			(0 global131)
			(1 gLowlightColor_5)
			(2 global138)
			(3 global142)
			(4 myLowlightColor)
			(else  global146)
		)
	)
	(Display theString
		p_color theColor
		p_at 1 (- 89 (/ (* 12 param2) 2))
		p_width 318
		p_font bigFont
		p_mode teJustCenter
	)
)

(instance rm480 of LLRoom
	(properties
		picture 480
		style IRISOUT
	)
	
	(method (init)
		(LoadMany PICTURE 1)
		(LoadMany FONT giantFont)
		(LoadMany SOUND 100)
		(copter init: setCycle: Forward)
		(super init:)
		(if (Btst fSkippedEnding)
			(theMusic
				number: 462
				flags: 1
				setLoop: -1
				setVol: 127
				play:
			)
		)
		(theMusic2
			number: 468
			flags: 1
			setLoop: -1
			setVol: 127
			play:
		)
		(self setScript: sCartoon)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(theMusic fade: 0 15 12 1)
	)
)

(instance sCartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 3)
			)
			(1
				(Say ego 480 0 #dispose self #at -1 20)
			)
			(2
				(TimePrint 480 1 #title {Larry} #dispose self #at -1 20)
			)
			(3
				(Say ego 480 2 #dispose self #at -1 20)
			)
			(4
				(TimePrint 480 3
					#title {The Vice President}
					#dispose
					self
					#at -1 20
				)
			)
			(5
				(TimePrint 480 4 #title {Larry} #dispose self #at -1 20)
			)
			(6
				(Say ego 480 5 #dispose self #at -1 20)
			)
			(7
				(copter setLoop: 0 setCycle: EndLoop self)
			)
			(8
				(copter
					cycleSpeed: 8
					setLoop: 1
					setCel: 0
					cycleSpeed: 8
					moveSpeed: 8
					setCycle: EndLoop self
					setMotion: MoveTo 165 128
				)
			)
			(9
				(copter
					x: 285
					y: 79
					xStep: 7
					yStep: 7
					setLoop: 2
					setCel: 0
					cycleSpeed: 6
					moveSpeed: 6
					setCycle: Forward
					setMotion: MoveTo 350 61 self
				)
				(theMusic2 fade:)
			)
			(10 (= ticks 120))
			(11
				(theMusic fade: 0 15 12 1 self)
			)
			(12
				(theMusic
					number: 100
					flags: 1
					setLoop: -1
					setVol: 127
					play:
				)
				(= ticks 120)
			)
			(13
				(TimePrint 480 6 #at -1 20)
				(TimePrint 480 7)
				(= seconds 3)
			)
			(14
				(TimePrint 480 8 #at -1 15 #width 280)
				(TimePrint 480 9)
				(= seconds 3)
			)
			(15
				(TimePrint 480 10 #at -1 15 #width 280)
				(TimePrint 480 11)
				(= seconds 3)
			)
			(16
				(TimePrint 480 12 #at -1 15 #width 280)
				(TimePrint 480 13)
				(= seconds 3)
			)
			(17
				(TimePrint 480 14 #at -1 15 #width 280)
				(TimePrint 480 15)
				(= seconds 3)
			)
			(18
				(TimePrint 480 16 #at -1 15 #width 280)
				(TimePrint 480 17)
				(= seconds 3)
			)
			(19
				(TimePrint 480 18 #at -1 15 #width 280)
				(TimePrint 480 19)
				(= seconds 3)
			)
			(20
				(TimePrint 480 20 #at -1 15 #width 280)
				(= seconds 3)
			)
			(21
				(TimePrint 480 21)
				(= seconds 3)
			)
			(22
				(TimePrint 480 22 #at -1 185)
				(= seconds 3)
			)
			(23
				(CreditDisplay {Art Team Manager\n\nCheryl Loyd} 3)
				(= seconds 3)
			)
			(24
				(curRoom drawPic: 1 13)
				(= creditColor
					(switch (Random 0 5)
						(0 global131)
						(1 gLowlightColor_5)
						(2 global138)
						(3 global142)
						(4 myLowlightColor)
						(else  global146)
					)
				)
				(Display
					{Animators\n\n}
					p_color creditColor
					p_at 1 35
					p_width 318
					p_font bigFont
					p_mode teJustCenter
				)
				(Display
					{\n\nCheryl Loyd\nBarry T. Smith\nRoger Hardy Jr.\nDana M. Dean\nKarin Young\nMarc Hudgins\nEric Kasner}
					p_color creditColor
					p_at 1 35
					p_width 159
					p_font bigFont
					p_mode teJustCenter
				)
				(Display
					{\n\nRichard Powell\nPhyllis Cucchiara\nTerry Falls\nMichael Hutchison\nDesie Hartman\nWillis Wong}
					p_color creditColor
					p_at 161 35
					p_width 159
					p_font bigFont
					p_mode teJustCenter
				)
				(= seconds 8)
			)
			(25
				(CreditDisplay
					{Background Artists\n\nJane Cardinal\nMaurice Morgan\nAndy Hoyos\nCheryl Loyd\nJay Friedmann\nJennifer Shontz\nGloria Garland}
					9
				)
				(= seconds 5)
			)
			(26
				(CreditDisplay
					{Programmers\n\nBrian K. Hughes\nCarlos Escobar\nSteve Conrad\nJohn Hartin\nOliver Brelsford\nAl Lowe}
					8
				)
				(= seconds 5)
			)
			(27
				(CreditDisplay {Music Director\n\nMark Seibert} 3)
				(= seconds 3)
			)
			(28
				(curRoom drawPic: 1 13)
				(= creditColor
					(switch (Random 0 5)
						(0 global131)
						(1 gLowlightColor_5)
						(2 global138)
						(3 global142)
						(4 myLowlightColor)
						(else  global146)
					)
				)
				(Display
					{Sierra's Creative Interpreter\nDevelopment System\n\n}
					p_color creditColor
					p_at 1 23
					p_width 318
					p_font bigFont
					p_mode teJustCenter
				)
				(Display
					{\n\n\nJeff Stephenson\nRobert E. Heitman\nDan Foy\nLarry Scott\nJohn Rettig\nJ. Mark Hood\nChris Smith\nTerry McHenry}
					p_color creditColor
					p_at 1 23
					p_width 159
					p_font bigFont
					p_mode teJustCenter
				)
				(Display
					{\n\n\nEric Hart\nChad Bye\nMark Wilden\nKen Koch\nJohn Crane\nSteve Coallier\nRandy Moss}
					p_color creditColor
					p_at 161 23
					p_width 159
					p_font bigFont
					p_mode teJustCenter
				)
				(= seconds 8)
			)
			(29
				(CreditDisplay {Additional Music\n\nChris Braymen} 3)
				(= seconds 3)
			)
			(30
				(CreditDisplay
					{Additional Written Material\n\nJosh Mandel}
					3
				)
				(= seconds 3)
			)
			(31
				(CreditDisplay
					{Quality Assurance Team Leader\n\nRobin Bradley}
					3
				)
				(= seconds 3)
			)
			(32
				(CreditDisplay {Coffee Grinder\n\nC. Escobar} 3)
				(= ticks 100)
			)
			(33
				(if (DoSound SoundOn)
					(theMusic2 number: 161 setLoop: 1 play:)
				)
				(= seconds 3)
			)
			(34
				(curRoom drawPic: 1 13)
				(if (DoSound SoundOn)
					(TimePrint 480 23 #title {AL says} #at -1 20)
				)
				(= seconds 5)
			)
			(35 (theMusic fade: self))
			(36
				(if (not (DoSound SoundOn))
					(= state (+ state 1))
				else
					(theGame setCursor: ARROW_CURSOR TRUE)
					(TimePrint 480 24
						#title {Congratulations! You Win!!}
						#at -1 15
						#width 280
					)
				)
				(= cycles 2)
			)
			(37
				(if
					(= printRet
						(PrintD {Aren't you glad you bought a DAC?}
							#x 50
							#new
							#button {Airport PA #1} 1
							#button {Airport PA #2} 2
							#button {Airport PA #3} 3
							#new
							#button {Airport PA #4} 4
							#button {__Atmosphere__} 5
							#button {____Screams____} 6
							#new
							#button {______Yeow______} 7
							#button {_____Coffee_____} 8
							#button {Folding Chair} 9
							#new
							#button { Wolf Whistle_} 10
							#button {___Knocking___} 11
							#button {___Glass #1___} 12
							#new
							#button {___Glass #2___} 13
							#button {_______Ah_______} 14
							#button {_______Oh_______} 15
							#new
							#button {_______AH!_______} 16
							#button {______OH!______} 17
							#button {_____Oooh!_____} 18
							#new
							#button { Crowd Gasp_} 19
							#button { Slob Eating_} 20
							#button {_____Flush_____} 21
							#new
							#button {_____Belch_____} 22
							#button {_____Barf_____} 23
							#button {Do Not Touch} 24
							#new
							#button {< Done >} 0
							#x 115
							#title {Thanks for buying "Larry 5!"}
							#first printRet
						)
					)
					(theMusic number: [local1 printRet] setLoop: 1 play:)
					(self changeState: state)
				else
					(= cycles 1)
				)
			)
			(38
				(curRoom drawPic: 1 13)
				(Display
					{Remember Al Lowe's Slogan:}
					p_color gLowlightColor_6
					p_at 1 30
					p_width 318
					p_font giantFont2
					p_mode teJustCenter
				)
				(= cycles 2)
			)
			(39
				(if (DoSound SoundOn)
					(LoadMany SOUND 481)
				)
				(= seconds 3)
			)
			(40
				(if (DoSound SoundOn)
					(theMusic number: 481 setVol: 127 setLoop: 1 play:)
				)
				(Display
					{Better Babes Through\nTechnology!}
					p_color global131
					p_at 1 90
					p_width 318
					p_font giantFont
					p_mode teJustCenter
				)
				(= seconds 5)
			)
			(41
				(= quit TRUE)
			)
		)
	)
)

(instance copter of Actor
	(properties
		x 160
		y 144
		view 480
		loop 3
	)
)
