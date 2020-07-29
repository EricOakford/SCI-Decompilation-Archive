;;; Sierra Script 1.0 - (do not remove this comment)
(script# 140)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm140 0
)

(local
	local0
)
(instance rm140 of LLRoom
	(properties
		picture 140
	)
	
	(method (init)
		(LoadMany VIEW 140 141 1142 1143 1140)
		(LoadMany SOUND 140)
		(HandsOff)
		(super init:)
		(fScruemall init: setPri: 3)
		(pornGeek1 init:)
		(pornGeek2 init:)
		(geek3 init:)
		(geek4 init:)
		(geek4a init:)
		(geek5 init:)
		(geek6 init:)
		(geek6a init:)
		(geek7 init:)
		(addToPics doit:)
		(SetFFRoom 155)
		(theMusic number: 140 flags: mNOPAUSE setLoop: -1 play:)
		(if (== prevRoomNum 130)
			(self setScript: sCartoon)
		else
			(= style SCROLLRIGHT)
			(self setScript: sLarryCartoon)
		)
	)
	
	(method (newRoom n)
		(if (== n 160)
			(theMusic fade: 0 15 12 1)
		)
		(super newRoom: n)
	)
)

(instance fScruemall of Prop
	(properties
		x 158
		y 55
		view 144
		cel 2
	)
)

(instance pornGeek1 of PicView
	(properties
		x 63
		y 81
		view 142
		priority 7
		signal fixPriOn
	)
)

(instance pornGeek2 of PicView
	(properties
		x 249
		y 75
		view 143
		priority 7
		signal fixPriOn
	)
)

(instance geek3 of PicView
	(properties
		x 53
		y 110
		view 140
		priority 8
	)
)

(instance geek5 of PicView
	(properties
		x 267
		y 114
		view 140
		loop 1
		priority 9
	)
)

(instance geek4 of PicView
	(properties
		x 221
		y 159
		view 140
		loop 2
		cel 1
	)
)

(instance geek4a of PicView
	(properties
		x 236
		y 128
		view 140
		loop 2
		priority 14
		signal fixPriOn
	)
)

(instance geek6 of PicView
	(properties
		x 103
		y 154
		view 140
		loop 3
		cel 1
		priority 13
		signal fixPriOn
	)
)

(instance geek6a of PicView
	(properties
		x 89
		y 126
		view 140
		loop 3
		priority 14
		signal fixPriOn
	)
)

(instance geek7 of PicView
	(properties
		x 157
		y 128
		view 140
		loop 4
	)
)

(instance Biffie of Talker
	(properties
		nsTop 113
		nsLeft 3
		view 1142
		loop 3
		viewInPrint 1
	)
	
	(method (init)
		(= bust geek1Bust)
		(= eyes geek1Eyes)
		(= mouth geek1Mouth)
		(super init: &rest)
	)
)

(instance geek1Bust of Prop
	(properties
		view 1142
		loop 1
	)
)

(instance geek1Eyes of Prop
	(properties
		nsTop 15
		nsLeft 10
		view 1142
		loop 2
		cycleSpeed 70
	)
)

(instance geek1Mouth of Prop
	(properties
		nsTop 36
		nsLeft 26
		view 1142
		cycleSpeed 5
	)
)

(instance Scooter of Talker
	(properties
		nsTop 113
		nsLeft 48
		view 1143
		loop 3
		viewInPrint 1
	)
	
	(method (init)
		(= bust geek2Bust)
		(= eyes geek2Eyes)
		(= mouth geek2Mouth)
		(super init: &rest)
	)
)

(instance geek2Bust of Prop
	(properties
		view 1143
		loop 1
	)
)

(instance geek2Eyes of Prop
	(properties
		nsTop 34
		nsLeft 23
		view 1143
		loop 2
		cycleSpeed 70
	)
)

(instance geek2Mouth of Prop
	(properties
		nsTop 40
		nsLeft 16
		view 1143
		cycleSpeed 5
	)
)

(instance Silas_Scruemall of Talker
	(properties
		nsTop 106
		nsLeft 40
		view 1140
		loop 3
		viewInPrint 1
		name "Silas Scruemall"
	)
	
	(method (init)
		(= bust ScruemallBust)
		(= eyes ScruemallEyes)
		(= mouth ScruemallMouth)
		(super init: &rest)
	)
)

(instance ScruemallBust of Prop
	(properties
		view 1140
		loop 1
	)
)

(instance ScruemallEyes of Prop
	(properties
		nsTop 31
		nsLeft 13
		view 1140
		loop 2
		cycleSpeed 70
	)
)

(instance ScruemallMouth of Prop
	(properties
		nsTop 53
		nsLeft 8
		view 1140
		cycleSpeed 5
	)
)

(instance sCartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Say Biffie 140 0 #dispose #caller self)
			)
			(1 (= ticks 60))
			(2
				(Say Biffie 140 1 #dispose #caller self)
			)
			(3 (= ticks 60))
			(4
				(Say Biffie 140 2 #dispose #caller self)
			)
			(5 (= ticks 60))
			(6
				(Say Biffie 140 3 #dispose #caller self)
			)
			(7 (= ticks 60))
			(8
				(Say Biffie 140 4 #dispose #caller self)
			)
			(9 (= ticks 60))
			(10
				(Say Biffie 140 5 #dispose #caller self)
			)
			(11 (= ticks 60))
			(12
				(Say Biffie 140 6 #dispose #caller self)
			)
			(13 (= ticks 60))
			(14
				(fScruemall setCel: 1)
				(= seconds 3)
			)
			(15
				(TimePrint 140 7)
				(= seconds 3)
			)
			(16
				(fScruemall setCel: 2)
				(= ticks 100)
			)
			(17
				(Say Silas_Scruemall 140 8 #dispose #caller self)
			)
			(18 (= ticks 60))
			(19
				(fScruemall setCel: 1)
				(TimePrint 140 9
					#at 68 91
					#title {A Yes Man}
				)
				(TimePrint 140 10
					#at 105 137
					#title {A Yes Man}
				)
				(TimePrint 140 11
					#at 180 113
					#title {A Yes Man}
				)
				(= seconds 3)
			)
			(20
				(Say Silas_Scruemall 140 12 #dispose #caller self)
			)
			(21 (= ticks 60))
			(22
				(TimePrint 140 9
					#at 180 147
					80 {A Yes Man})
				(TimePrint 140 10
					#at 0 147
					#title {A Yes Man})
				(TimePrint 140 11
					#at 68 91
					#title {A Yes Man})
				(= seconds 3)
			)
			(23
				(Say Silas_Scruemall 140 13 #dispose #caller self)
			)
			(24 (= ticks 60))
			(25
				(fScruemall setCel: 0)
				(TimePrint 140 9
					#at 105 137
					#title {A Yes Man})
				(TimePrint 140 10
					#at 180 113
					#title {A Yes Man})
				(TimePrint 140 11
					#at 180 147
					#title {A Yes Man})
				(= seconds 3)
			)
			(26
				(Say Scooter 140 14 #dispose #caller self)
			)
			(27
				(fScruemall setCel: 1)
				(= seconds 3)
			)
			(28
				(Say Silas_Scruemall 140 15 #dispose #caller self)
			)
			(29 (= ticks 60))
			(30
				(TimePrint 140 16
					#at 0 147
					#mode {A Yes Man})
				(TimePrint 140 17
					#at 68 91
					#mode {A Yes Man})
				(TimePrint 140 18
					#at 105 137
					#title {A Yes Man})
				(fScruemall setCel: 0)
				(= seconds 4)
			)
			(31
				(Say Scooter 140 19 #dispose #caller self)
			)
			(32
				(fScruemall setCel: 0)
				(= ticks 150)
			)
			(33
				(theIconBar disable:)
				(DrawPic 1 -32762)
				(= seconds 3)
			)
			(34
				(theIconBar enable:)
				(curRoom newRoom: 160)
			)
		)
	)
)

(instance sLarryCartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fScruemall setCel: 1)
				(ego
					init:
					x: 319
					y: 69
					view: 141
					setCycle: Walk
					loop: 1
					setMotion: MoveTo 217 56 self
				)
			)
			(1
				(Say ego 140 20)
				(= cycles 10)
			)
			(2
				(Say Silas_Scruemall 140 21 #dispose #caller self)
			)
			(3
				(TimePrint 140 22 #at -1 185)
				(ego setMotion: MoveTo 190 53 self)
			)
			(4
				(ego setMotion: MoveTo 125 53 self)
			)
			(5
				(fScruemall setCel: 2)
				(ego setCel: 0 y: 55 setLoop: 2 setCycle: EndLoop self)
			)
			(6
				(Say ego 140 23)
				(curRoom newRoom: 150)
			)
		)
	)
)
