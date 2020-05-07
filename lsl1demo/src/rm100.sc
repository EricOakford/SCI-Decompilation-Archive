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
	local0
	local1
	local2
	local3
	local4
	local5
	fakeParseDlg
	local7
	scriptTimer
	local9
	local10
	local11
)
(procedure (localproc_0d22)
	(= fakeParseDlg
		(Display 100 1
			p_at (+ local7 8) 179
			p_color myTextColor7
			p_save
		)
	)
	(click number: 903 play:)
)

(procedure (localproc_0d4c)
	(= fakeParseDlg
		(Display 100 1
			p_at (+ local7 8) 179
			p_color myTextColor7
			p_save
		)
	)
	(click number: 905 play:)
)

(procedure (localproc_0d76)
	(Display 100 7 p_restore fakeParseDlg)
)

(instance rm100 of Room
	(properties
		picture 100
		style DISSOLVE
	)
	
	(method (init)
		(LoadMany VIEW 110 100)
		(LoadMany PICTURE 110 120)
		(Load FONT USERFONT)
		(Load FONT 312)
		(LoadMany SOUND 906 900 903 905 904 102)
		(fakeEgo init:)
		(fakeEye init: z: 1000)
		(super init:)
		(music number: 906 loop: -1 vol: 127 priority: 10 play:)
		(globalSound
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local11
					(Display 100 0 p_at 1 179 p_color myTextColor7)
				)
				(= fakeParseDlg
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
				(localproc_0d76)
				(= cycles 1)
			)
			(5
				(= local0
					(Display
						100
						2
						p_at
						(= local7 (+ local7 8))
						179
						p_color
						myTextColor7
					)
				)
				(localproc_0d22)
				(= cycles 3)
			)
			(6
				(localproc_0d76)
				(= cycles 1)
			)
			(7
				(= local1
					(Display
						100
						3
						p_at
						(= local7 (+ local7 8))
						179
						p_color
						myTextColor7
					)
				)
				(localproc_0d22)
				(= cycles 1)
			)
			(8
				(localproc_0d76)
				(= cycles 1)
			)
			(9
				(= local2
					(Display
						100
						4
						p_at
						(= local7 (+ local7 8))
						179
						p_color
						myTextColor7
						p_save
					)
				)
				(localproc_0d22)
				(= cycles 3)
			)
			(10
				(localproc_0d76)
				(= cycles 1)
			)
			(11
				(= local7 (+ local7 8))
				(= local3
					(Display
						100
						5
						p_at
						(= local7 (+ local7 8))
						179
						p_color
						myTextColor7
						p_save
					)
				)
				(localproc_0d22)
				(= cycles 1)
			)
			(12
				(localproc_0d76)
				(= cycles 1)
			)
			(13
				(= local4
					(Display
						100
						3
						p_at
						(= local7 (+ local7 8))
						179
						p_color
						myTextColor7
						p_save
					)
				)
				(localproc_0d22)
				(= cycles 3)
			)
			(14
				(localproc_0d76)
				(= cycles 1)
			)
			(15
				(= local5
					(Display
						100
						6
						p_at
						(= local7 (+ local7 8))
						179
						p_color
						myTextColor7
						p_save
					)
				)
				(localproc_0d22)
				(= cycles 3)
			)
			(16
				(localproc_0d76)
				(= cycles 1)
			)
			(17
				(Display 100 7 108 local5)
				(= local7 (- local7 8))
				(localproc_0d4c)
				(= cycles 3)
			)
			(18
				(localproc_0d76)
				(= cycles 1)
			)
			(19
				(Display 100 7 108 local4)
				(= local7 (- local7 8))
				(localproc_0d4c)
				(= cycles 2)
			)
			(20
				(localproc_0d76)
				(= cycles 1)
			)
			(21
				(Display 100 7 108 local3)
				(= local7 (- local7 8))
				(= cycles 2)
				(localproc_0d4c)
			)
			(22
				(localproc_0d76)
				(= cycles 1)
			)
			(23
				(= local7 (- local7 8))
				(localproc_0d4c)
				(= cycles 1)
			)
			(24
				(localproc_0d76)
				(= cycles 1)
			)
			(25
				(Display 100 7 108 local2)
				(= local7 (- local7 8))
				(localproc_0d4c)
				(= cycles 2)
			)
			(26
				(localproc_0d76)
				(= cycles 1)
			)
			(27
				(= local2
					(Display
						100
						3
						p_at
						(= local7 (+ local7 8))
						179
						p_color
						myTextColor7
					)
				)
				(localproc_0d22)
				(= cycles 3)
			)
			(28
				(localproc_0d76)
				(= cycles 1)
			)
			(29
				(= local3
					(Display
						100
						4
						p_at
						(= local7 (+ local7 8))
						179
						p_color
						myTextColor7
					)
				)
				(localproc_0d22)
				(= local7 (+ local7 8))
				(= cycles 3)
			)
			(30
				(localproc_0d76)
				(= cycles 1)
			)
			(31
				(= local10
					(Display
						100
						5
						p_at
						(= local7 (+ local7 8))
						179
						p_color
						myTextColor7
					)
				)
				(localproc_0d22)
				(= cycles 5)
			)
			(32
				(localproc_0d76)
				(= cycles 1)
			)
			(33
				(= local9
					(Display
						100
						3
						p_at
						(= local7 (+ local7 8))
						179
						p_color
						myTextColor7
					)
				)
				(click number: 903 play:)
				(Display
					100
					1
					p_at
					(+ local7 8)
					179
					p_color
					myTextColor7
				)
				(= cycles 1)
			)
			(34
				(music fade:)
				(globalSound fade: 127 5 5 0)
				(curRoom style: 8 drawPic: 110)
				(AGIDisplay1
					{Leisure Suit Larry enters the 90's}
					166
					29
					myTextColor11
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
				(AGIDisplay1 1)
				(AGIDisplay1
					{With all the sophisticated}
					160
					29
					myTextColor11
				)
				(AGIDisplay2
					{humor of the original...}
					174
					29
					myTextColor11
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
				(AGIDisplay1
					{featuring our new, "no-typing,"}
					160
					29
					myTextColor11
				)
				(AGIDisplay2
					{point-and-grope interface...}
					174
					29
					myTextColor11
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
				(if modelessDialog (modelessDialog dispose:))
				(AGIDisplay1 1)
				(AGIDisplay2 1)
				(SCIDisplay
					{...perfect for those times when}
					160
					29
					myTextColor11
				)
				(SCIDisplay
					{two hands are just one too many!}
					174
					29
					myTextColor11
				)
				(= seconds 3)
			)
			(44
				(cast eachElementDo: #z 1000)
				(fakeEgo hide:)
				(curRoom style: 6 drawPic: 120)
				(= cycles 1)
			)
			(45
				(SCIDisplay
					{Great new graphics and animation,}
					30
					28
					myTextColor4
					29
					myTextColor10
				)
				(= seconds 4)
			)
			(46
				(SCIDisplay
					{advanced VGA and music card support,}
					50
					28
					myTextColor4
					29
					myTextColor10
				)
				(= seconds 4)
			)
			(47
				(SCIDisplay
					{but with the same wacky, risque humor}
					70
					28
					myTextColor4
					29
					myTextColor10
				)
				(SCIDisplay
					{that made it Rolling Stone Magazine's}
					90
					28
					myTextColor4
					29
					myTextColor10
				)
				(SCIDisplay
					{Hot Game of the Year}
					110
					28
					myTextColor4
					29
					myTextColor10
				)
				(= scriptTimer (+ (GetTime 1) 6))
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
				(LoadMany SOUND 902 113 114 115 382 SOUND 133 130)
				(while (< (GetTime 1) scriptTimer)
				)
				(= cycles 1)
			)
			(48
				(globalSound fade:)
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
	(properties)
	
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
