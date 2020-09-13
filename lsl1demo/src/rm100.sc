;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm100 0
)

(local
	typeBits1
	typeBits2
	typeBits3
	typeBits4
	typeBits5
	typeBits6
	typeBits7
	letterX
	scriptTimer
	underbits8
	underbits9
	promptBits
)
(procedure (TypeLetter)
	(= typeBits7
		(Display 100 1
			p_at (+ letterX 8) 179
			p_color myTextColor7
			p_save
		)
	)
	(click number: 903 play:)
)

(procedure (TypeBackspace)
	(= typeBits7
		(Display 100 1
			p_at (+ letterX 8) 179
			p_color myTextColor7
			p_save
		)
	)
	(click number: 905 play:)
)

(procedure (TypeClear)
	(Display 100 7
		p_restore typeBits7
	)
)

(instance rm100 of Room
	(properties
		picture pOutsideBarAGI
		style DISSOLVE
	)
	
	(method (init)
		(LoadMany VIEW pOutsideBarSCI pOutsideBarAGI)
		(LoadMany PICTURE pOutsideBarSCI pBlack)
		(Load FONT USERFONT)
		(Load FONT 312)
		(LoadMany SOUND 906 900 903 905 904 102)
		(fakeEgo init:)
		(fakeEye init: z: 1000)
		(super init:)
		(theMusic
			number: 906
			loop: -1
			vol: 127
			priority: 10
			play:
		)
		(theMusic2
			number: 900
			loop: -1
			vol: 0
			priority: 50
			playBed:
		)
		(leftySign init: setCycle: Forward)
		(hotelSign init: setCycle: Forward)
		(glass init: setCycle: Forward)
		(self setScript: sRoomScript)
	)
)

(instance sRoomScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= promptBits
					(Display 100 0
						p_at 1 179
						p_color myTextColor7
					)
				)
				(= typeBits7
					(Display 100 1
						p_at 8 179
						p_color myTextColor7
						p_save
					)
				)
				(= seconds 4)
			)
			(1
				(AGIDisplay1 {One of the best-selling} 160)
				(AGIDisplay2 {computer games of all time...} 172)
				(= seconds 4)
			)
			(2
				(AGIDisplay2 1)
				(AGIDisplay1 1)
				(SCIDisplay {Over a quarter-million copies sold...} 160)
				(= seconds 4)
			)
			(3
				(dog
					init:
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 135 150
				)
				(= cycles 20)
			)
			(4
				(TypeClear)
				(= cycles 1)
			)
			(5
				(= typeBits1
					(Display 100 2
						p_at (+= letterX 8) 179
						p_color myTextColor7
					)
				)
				(TypeLetter)
				(= cycles 3)
			)
			(6
				(TypeClear)
				(= cycles 1)
			)
			(7
				(= typeBits2
					(Display 100 3
						p_at (+= letterX 8) 179
						p_color myTextColor7
					)
				)
				(TypeLetter)
				(= cycles 1)
			)
			(8
				(TypeClear)
				(= cycles 1)
			)
			(9
				(= typeBits3
					(Display 100 4
						p_at (+= letterX 8) 179
						p_color myTextColor7
						p_save
					)
				)
				(TypeLetter)
				(= cycles 3)
			)
			(10
				(TypeClear)
				(= cycles 1)
			)
			(11
				(+= letterX 8)
				(= typeBits4
					(Display 100 5
						p_at (+= letterX 8) 179
						p_color myTextColor7
						p_save
					)
				)
				(TypeLetter)
				(= cycles 1)
			)
			(12
				(TypeClear)
				(= cycles 1)
			)
			(13
				(= typeBits5
					(Display 100 3
						p_at (+= letterX 8) 179
						p_color myTextColor7
						p_save
					)
				)
				(TypeLetter)
				(= cycles 3)
			)
			(14
				(TypeClear)
				(= cycles 1)
			)
			(15
				(= typeBits6
					(Display 100 6
						p_at (+= letterX 8) 179
						p_color myTextColor7
						p_save
					)
				)
				(TypeLetter)
				(= cycles 3)
			)
			(16
				(TypeClear)
				(= cycles 1)
			)
			(17
				(Display 100 7
					p_restore typeBits6
				)
				(-= letterX 8)
				(TypeBackspace)
				(= cycles 3)
			)
			(18
				(TypeClear)
				(= cycles 1)
			)
			(19
				(Display 100 7
					p_restore typeBits5
				)
				(-= letterX 8)
				(TypeBackspace)
				(= cycles 2)
			)
			(20
				(TypeClear)
				(= cycles 1)
			)
			(21
				(Display 100 7
					p_restore typeBits4
				)
				(-= letterX 8)
				(= cycles 2)
				(TypeBackspace)
			)
			(22
				(TypeClear)
				(= cycles 1)
			)
			(23
				(-= letterX 8)
				(TypeBackspace)
				(= cycles 1)
			)
			(24
				(TypeClear)
				(= cycles 1)
			)
			(25
				(Display 100 7
					p_restore typeBits3
				)
				(-= letterX 8)
				(TypeBackspace)
				(= cycles 2)
			)
			(26
				(TypeClear)
				(= cycles 1)
			)
			(27
				(= typeBits3
					(Display 100 3
						p_at (+= letterX 8) 179
						p_color myTextColor7
					)
				)
				(TypeLetter)
				(= cycles 3)
			)
			(28
				(TypeClear)
				(= cycles 1)
			)
			(29
				(= typeBits4
					(Display 100 4
						p_at (+= letterX 8) 179
						p_color myTextColor7
					)
				)
				(TypeLetter)
				(+= letterX 8)
				(= cycles 3)
			)
			(30
				(TypeClear)
				(= cycles 1)
			)
			(31
				(= underbits9
					(Display 100 5
						p_at (+= letterX 8) 179
						p_color myTextColor7
					)
				)
				(TypeLetter)
				(= cycles 5)
			)
			(32
				(TypeClear)
				(= cycles 1)
			)
			(33
				(= underbits8
					(Display 100 3
						p_at (+= letterX 8) 179
						p_color myTextColor7
					)
				)
				(click number: 903 play:)
				(Display 100 1
					p_at (+ letterX 8) 179
					p_color myTextColor7
				)
				(= cycles 1)
			)
			(34
				(theMusic fade:)
				(theMusic2 fade: 127 5 5 0)
				(curRoom style: DISSOLVE drawPic: pOutsideBarSCI)
				(AGIDisplay1 {Leisure Suit Larry enters the 90's} 166
					#back myTextColor11
				)
				(fakeEgo
					x: 115
					y: 150
					view: pOutsideBarSCI
					loop: loopS
					setScript: sLarryLookAround
				)
				(dog
					view: pOutsideBarSCI
					loop: 6
				)
				(leftySign
					view: pOutsideBarSCI
					loop: 3
					x: 112
					y: 50
				)
				(hotelSign
					view: pOutsideBarSCI
					loop: 5
					x: 283
					y: 0
				)
				(glass
					view: pOutsideBarSCI
					loop: 4
					x: 181
					y: 38
				)
				(= cycles 27)
			)
			(35
				(dog
					setLoop: 8
					setCel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(36
				(dog
					setLoop: 8
					setCel: 0
					cycleSpeed: 0
					setCycle: EndLoop self
				)
			)
			(37
				(fakeEgo loop: 0 cel: 0 cycleSpeed: 1 setCycle: EndLoop)
				(dog setLoop: 10 setCel: 0 setCycle: Forward)
				(dogPiss play:)
				(= cycles 25)
			)
			(38
				(dogPiss stop:)
				(dog
					setLoop: 9
					setCel: 2
					cycleSpeed: 2
					setCycle: BegLoop self
				)
			)
			(39
				(AGIDisplay1 1)
				(AGIDisplay1 {With all the sophisticated} 160
					#back myTextColor11
				)
				(AGIDisplay2 {humor of the original...} 174
					#back myTextColor11
				)
				(dog
					setLoop: 7
					setCycle: Walk
					cycleSpeed: 0
					setMotion: MoveTo 335 150
				)
				(fakeEgo loop: 1 cel: 0 setCycle: EndLoop)
				(= cycles 50)
			)
			(40
				(fakeEgo loop: 2 setScript: sLarryLookAround)
				(AGIDisplay2 1)
				(AGIDisplay1 1)
				(AGIDisplay1 {featuring our new, "no-typing,"} 160
					#back myTextColor11
				)
				(AGIDisplay2 {point-and-grope interface...} 174
					#back myTextColor11
				)
				(fakeEye
					x: (fakeEgo x?)
					y: (- (fakeEgo y?) 25)
					z: 0
					setMotion: MoveTo (+ (dog x?) 30) (- (dog y?) 3) self
				)
			)
			(41
				(fakeEye setCycle: BegLoop self)
				(click number: 904 play:)
			)
			(42
				(Print 100 8
					#dispose
					#at 60 65
				)
				(= seconds 6)
			)
			(43
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(AGIDisplay1 1)
				(AGIDisplay2 1)
				(SCIDisplay {...perfect for those times when} 160
					#back myTextColor11
				)
				(SCIDisplay {two hands are just one too many!} 174
					#back myTextColor11
				)
				(= seconds 3)
			)
			(44
				(cast eachElementDo: #z 1000)
				(fakeEgo hide:)
				(curRoom style: IRISIN drawPic: pBlack)
				(= cycles 1)
			)
			(45
				(SCIDisplay {Great new graphics and animation,} 30
					#color myTextColor4
					#back myTextColor10
				)
				(= seconds 4)
			)
			(46
				(SCIDisplay {advanced VGA and music card support,} 50
					#color myTextColor4
					#back myTextColor10
				)
				(= seconds 4)
			)
			(47
				(SCIDisplay {but with the same wacky, risque humor} 70
					#color myTextColor4
					#back myTextColor10
				)
				(SCIDisplay {that made it Rolling Stone Magazine's} 90
					#color myTextColor4
					#back myTextColor10
				)
				(SCIDisplay {Hot Game of the Year} 110
					#color myTextColor4
					#back myTextColor10
				)
				(= scriptTimer (+ (GetTime SYSTIME1) 6))
				(UnLoad PICTURE pOutsideBarSCI)
				(UnLoad PICTURE pOutsideBarAGI)
				(UnLoad VIEW pOutsideBarSCI)
				(UnLoad VIEW pOutsideBarAGI)
				(UnLoad SOUND 906)
				(UnLoad SOUND 901)
				(UnLoad SOUND 903)
				(UnLoad SOUND 905)
				(UnLoad SOUND 904)
				(UnLoad SOUND 102)
				(LoadMany PICTURE pToilet pFawn pToilet)
				(LoadMany VIEW pToilet pFawn pToilet)
				(LoadMany SOUND 902 113 114 115 382 132 133 130)
				(while (< (GetTime SYSTIME1) scriptTimer))
				(= cycles 1)
			)
			(48
				(theMusic2 fade:)
				(curRoom newRoom: 110)
			)
		)
	)
)

(instance leftySign of Prop
	(properties
		x 122
		y 81
		view pOutsideBarAGI
		loop 2
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 6
	)
)

(instance hotelSign of Prop
	(properties
		x 269
		view pOutsideBarAGI
		loop 4
		priority 14
		signal (| ignrAct fixPriOn)
		cycleSpeed 5
	)
)

(instance glass of Prop
	(properties
		x 189
		y 81
		view pOutsideBarAGI
		loop 3
		priority 14
		signal (| ignrAct fixPriOn)
		cycleSpeed 4
	)
)

(instance dog of Actor
	(properties
		x 330
		y 145
		yStep 1
		view pOutsideBarAGI
		priority 12
		signal (| ignrAct fixedLoop fixPriOn)
		xStep 2
	)
)

(instance fakeEye of Actor
	(properties
		yStep 14
		view pOutsideBarAGI
		loop 5
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
		xStep 14
	)
)

(instance fakeEgo of Prop
	(properties
		x 111
		y 144
		view pOutsideBarAGI
		loop 6
		signal ignrAct
	)
)

(instance sLarryLookAround of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(fakeEgo setCel: 1)
				(= seconds 2)
			)
			(1
				(fakeEgo setCel: 2)
				(= seconds 1)
			)
			(2
				(fakeEgo setCel: 1)
				(= seconds 2)
			)
			(3
				(fakeEgo setCel: 0)
				(= seconds 1)
			)
			(4 (fakeEgo setCel: 1))
		)
	)
)

(instance click of Sound
	(properties
		priority 10
	)
)

(instance dogPiss of Sound
	(properties
		number 102
		priority 10
		loop -1
	)
)
