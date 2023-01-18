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
	typeBits8
	typeBits9
	promptBits
)
(procedure (TypeLetter)
	(= typeBits7
		(Display 100 1
			p_at (+ letterX 8) 179
			p_color colWhite
			p_save
		)
	)
	(click number: 903 play:)
)

(procedure (TypeBackspace)
	(= typeBits7
		(Display 100 1
			p_at (+ letterX 8) 179
			p_color colWhite
			p_save
		)
	)
	(click number: 905 play:)
)

(procedure (TypeClear)
	(Display 100 7 p_restore typeBits7)
)

(instance rm100 of Room
	(properties
		picture 100
		style DISSOLVE
	)
	
	(method (init)
		(LoadMany VIEW 110 100)
		(LoadMany PICTURE 110 120)
		(Load FONT 1)
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
						p_color colWhite
					)
				)
				(= typeBits7
					(Display 100 1
						p_at 8 179
						p_color colWhite
						p_save
					)
				)
				(= seconds 4)
			)
			(1
				(DoDisplay1 {One of the best-selling} 160)
				(DoDisplay2 {computer games of all time...} 172)
				(= seconds 4)
			)
			(2
				(DoDisplay2 1)
				(DoDisplay1 1)
				(DoDisplay3 {Over a quarter-million copies sold...} 160)
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
						p_color colWhite
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
						p_color colWhite
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
						p_color colWhite
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
						p_color colWhite
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
						p_color colWhite
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
						p_color colWhite
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
				(Display 100 7 p_restore typeBits6)
				(-= letterX 8)
				(TypeBackspace)
				(= cycles 3)
			)
			(18
				(TypeClear)
				(= cycles 1)
			)
			(19
				(Display 100 7 108 typeBits5)
				(-= letterX 8)
				(TypeBackspace)
				(= cycles 2)
			)
			(20
				(TypeClear)
				(= cycles 1)
			)
			(21
				(Display 100 7 p_restore typeBits4)
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
				(Display 100 7 p_restore typeBits3)
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
						p_color colWhite
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
						p_color colWhite
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
				(= typeBits9
					(Display 100 5
						p_at (+= letterX 8) 179
						p_color colWhite
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
				(= typeBits8
					(Display 100 3
						p_at (+= letterX 8) 179
						p_color colWhite
					)
				)
				(click number: 903 play:)
				(Display 100 1
					p_at (+ letterX 8) 179
					p_color colWhite
				)
				(= cycles 1)
			)
			(34
				(theMusic fade:)
				(theMusic2 fade: 127 5 5 0)
				(curRoom style: 8 drawPic: 110)
				(DoDisplay1 {Leisure Suit Larry enters the 90's}
					166 29 colDBlue
				)
				(fakeEgo
					x: 115
					y: 150
					view: 110
					loop: 2
					setScript: sLarryLookAround
				)
				(dog view: 110 loop: 6)
				(leftySign view: 110 loop: 3 x: 112 y: 50)
				(hotelSign view: 110 loop: 5 x: 283 y: 0)
				(glass view: 110 loop: 4 x: 181 y: 38)
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
				(DoDisplay1 1)
				(DoDisplay1 {With all the sophisticated} 160 29 colDBlue)
				(DoDisplay2 {humor of the original...} 174 29 colDBlue)
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
				(DoDisplay2 1)
				(DoDisplay1 1)
				(DoDisplay1 {featuring our new, "no-typing,"}
					160 29 colDBlue
				)
				(DoDisplay2 {point-and-grope interface...}
					174 29 colDBlue
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
				(Print 100 8 #dispose #at 60 65)
				(= seconds 6)
			)
			(43
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(DoDisplay1 1)
				(DoDisplay2 1)
				(DoDisplay3 {...perfect for those times when}
					160 29 colDBlue
				)
				(DoDisplay3 {two hands are just one too many!}
					174 29 colDBlue
				)
				(= seconds 3)
			)
			(44
				(cast eachElementDo: #z 1000)
				(fakeEgo hide:)
				(curRoom style: IRISIN drawPic: 120)
				(= cycles 1)
			)
			(45
				(DoDisplay3 {Great new graphics and animation,}
					30 28 colRed 29 colDRed
				)
				(= seconds 4)
			)
			(46
				(DoDisplay3  {advanced VGA and music card support,}
					50 28 colRed 29 colDRed
				)
				(= seconds 4)
			)
			(47
				(DoDisplay3 {but with the same wacky, risque humor}
					70 28 colRed 29 colDRed
				)
				(DoDisplay3 {that made it Rolling Stone Magazine's}
					90 28 colRed
					29 colDRed
				) 
				(DoDisplay3 {Hot Game of the Year}
					110 28 colRed 29 colDRed
				)
				(= scriptTimer (+ (GetTime SYSTIME1) 6))
				(UnLoad PICTURE 110)
				(UnLoad PICTURE 100)
				(UnLoad VIEW 110)
				(UnLoad VIEW 100)
				(UnLoad SOUND 906)
				(UnLoad SOUND 901)
				(UnLoad SOUND 903)
				(UnLoad SOUND 905)
				(UnLoad SOUND 904)
				(UnLoad SOUND 102)
				(LoadMany PICTURE 150 140 130)
				(LoadMany VIEW 150 140 130)
				(LoadMany SOUND 902 113 114 115 382 132 133 130)
				(while (< (GetTime SYSTIME1) scriptTimer)
				)
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
		view 100
		loop 2
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 6
	)
)

(instance hotelSign of Prop
	(properties
		x 269
		view 100
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
		view 100
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
		view 100
		priority 12
		signal (| ignrAct fixedLoop fixPriOn)
		xStep 2
	)
)

(instance fakeEye of Actor
	(properties
		yStep 14
		view 100
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
		view 100
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
