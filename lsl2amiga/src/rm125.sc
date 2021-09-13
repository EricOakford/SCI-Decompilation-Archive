;;; Sierra Script 1.0 - (do not remove this comment)
(script# 125)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm125 0
)

(local
	howFast
	thoughtBalloonView
	talkedToBarber
)
(instance rm125 of Room
	(properties
		picture 125
		horizon 5
		south 25
	)
	
	(method (init)
		(Load VIEW 233)
		(Load VIEW 232)
		(Load VIEW 244)
		(Load VIEW 246)
		(if (> filthLevel 4)
			(= thoughtBalloonView 245)
		else
			(= thoughtBalloonView 248) ;censored view
		)
		(Load VIEW thoughtBalloonView)
		(super init:)
		(cond 
			((> machineSpeed 60) (= howFast 3))
			((> machineSpeed 40) (= howFast 2))
			((> machineSpeed 20) (= howFast 1))
		)
		(aThoughtBalloon
			view: thoughtBalloonView
			init:
		)
		(aThought
			view: thoughtBalloonView
			init:
		)
		(aHandle
			init:
		)
		(aHeads
			init:
			hide:
		)
		(aChair
			stopUpd:
			init:
		)
		(aBarber
			setCycle: Walk
			illegalBits: cWHITE
			init:
		)
		(NormalEgo loopN)
		(ego posn: 160 159 init:)
		(self setRegions: BARBER CITY setScript: rm125Script)
	)
)

(instance rm125Script of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cBLUE)
			(curRoom newRoom: 25)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not gotHaircutInCity)
					(= seconds 10)
				)
			)
			(1
				(if (not talkedToBarber)
					(Print 125 9)
					(= seconds (Random 12 25))
					(= state 0)
				)
			)
			(2
				(= seconds (= cycles 0))
				(= currentStatus egoSITTING)
				(HandsOff)
				(Print 125 10)
				(Print 125 11)
				(Print 125 12)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 157 120 self
				)
				(aBarber setMotion: MoveTo 185 119 self)
			)
			(3
				(aChair hide:)
				(ego
					view: 232
					setLoop: 2
					cel: 0
					posn: 167 115
					setCycle: EndLoop
				)
			)
			(4
				(aBarber setMotion: MoveTo 179 111 self)
			)
			(5
				(aBarber setMotion: MoveTo 166 111 self illegalBits: 0)
			)
			(6
				(Print 125 13)
				(aThoughtBalloon
					view: 246
					setCel: 0
					posn: 160 127
					setPri: 14
					stopUpd:
				)
				(aHandle posn: 160 128 stopUpd:)
				(aThought
					view: 246
					setLoop: 1
					cel: 0
					posn: 161 119
					setPri: 15
					setCycle: Forward
					cycleSpeed: (* 2 howFast)
				)
				(= seconds 6)
			)
			(7
				(Print 125 14 #at -1 15 #width 280)
				(= seconds 3)
			)
			(8
				(aThought posn: 161 1119)
				(aHandle posn: 160 1128)
				(aThoughtBalloon posn: 160 1127)
				(Print 125 15 #at -1 20 #draw)
				(= seconds 3)
			)
			(9
				(ego hide:)
				(aBarber view: 244 setLoop: 0 posn: 167 115 setCel: 0)
				(= cycles 30)
			)
			(10
				(aBarber cycleSpeed: 1 setCycle: EndLoop self)
			)
			(11
				(Print 125 16 #draw)
				(aBarber
					setLoop: 1
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(12
				(aHeads
					posn: 166 100
					setLoop: 2
					cel: 0
					setPri: 9
					cycleSpeed: 2
					show:
					setCycle: Forward
				)
				(aBarber stopUpd:)
				(= seconds 5)
			)
			(13
				(Print 125 17)
				(Print 125 18)
				(Print 125 19)
				(= seconds 5)
			)
			(14
				(aThoughtBalloon
					view: thoughtBalloonView
					setLoop: 0
					cel: 0
					setPri: 14
					posn: 154 91
					cycleSpeed: howFast
					setCycle: EndLoop self
				)
			)
			(15
				(aThoughtBalloon stopUpd:)
				(aThought
					view: thoughtBalloonView
					setLoop: 2
					setPri: 15
					setCel: 0
					posn: 110 45
					cycleSpeed: howFast
					setCycle: EndLoop self
				)
			)
			(16
				(aThought setCycle: BegLoop self)
			)
			(17
				(aThought setCycle: EndLoop self)
			)
			(18
				(aThought setCycle: BegLoop self)
			)
			(19
				(aThought setCycle: EndLoop self)
			)
			(20
				(aThought setCycle: BegLoop self)
			)
			(21
				(aThought posn: 161 1119)
				(= seconds 5)
			)
			(22
				(aThought
					setLoop: 1
					posn: 112 40
					cel: 0
					setPri: 15
					setCycle: EndLoop self
				)
			)
			(23
				(aThought posn: 161 1120)
				(= seconds 3)
			)
			(24
				(Print 125 20)
				(= seconds 3)
			)
			(25
				(aThought
					setLoop: 3
					cel: 0
					posn: 103 41
					cycleSpeed: howFast
					setCycle: CycleTo 10 1 self
				)
			)
			(26
				(Print 125 21 #draw)
				(= cycles 8)
			)
			(27
				(aThought setCycle: EndLoop self)
			)
			(28
				(aThought posn: 161 1120)
				(aThoughtBalloon posn: 160 1128)
				(= seconds 6)
			)
			(29
				(aHeads hide:)
				(aBarber
					setLoop: 3
					cel: 0
					cycleSpeed: howFast
					setCycle: EndLoop self
				)
				(Print 125 22 #draw)
				(Print 125 23)
			)
			(30
				(aHeads
					posn: 166 94
					setLoop: 4
					cel: 0
					setPri: 14
					setCycle: Forward
					show:
				)
				(aBarber stopUpd:)
				(= seconds 11)
			)
			(31
				(aHeads dispose:)
				(Print 125 24 #draw)
				(= seconds 3)
			)
			(32
				(aThoughtBalloon
					view: 246
					setCel: 0
					posn: 160 127
					setPri: 14
					stopUpd:
				)
				(aHandle posn: 160 128 stopUpd:)
				(aThought
					view: 246
					setLoop: 1
					cel: 0
					setPri: 15
					posn: 161 119
					setCycle: Forward
					cycleSpeed: howFast
				)
				(= seconds 6)
			)
			(33
				(Print 125 25 #at -1 20 #draw)
				(= seconds 3)
			)
			(34
				(aThought dispose:)
				(aHandle dispose:)
				(aThoughtBalloon dispose:)
				(Print 125 26 #at -1 20 #draw)
				(= seconds 3)
			)
			(35
				(Print 125 27)
				(= seconds 3)
			)
			(36
				(Print 125 28)
				(= seconds 2)
			)
			(37
				(aBarber setLoop: 3 setCel: 255 setCycle: BegLoop self)
			)
			(38
				(aBarber setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(39
				(aBarber
					view: 233
					posn: 182 114
					loop: 2
					setCycle: Walk
					illegalBits: cWHITE
				)
				(ego
					view: 232
					setLoop: 2
					setCel: 255
					posn: 167 115
					setCycle: BegLoop self
					show:
				)
			)
			(40
				(NormalEgo loopS)
				(ego ignoreActors: FALSE)
				(aChair show:)
				(theGame changeScore: 3)
				(= seconds 2)
			)
			(41
				(Print 125 29)
				(Print 125 30)
				(Print (Format @str 125 31 tritePhrase))
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if
			(or
				(Said 'bath,fix,ok,cut')
				(Said '(get<in)/barstool')
				(Said 'get/haircut,(cut<hair)')
			)
			(= talkedToBarber TRUE)
			(cond 
				((not (ego inRect: 148 117 180 127))
					(Print 125 0)
				)
				((ego has: iMillionDollarBill)
					(Print 125 1)
					(Print 125 2)
				)
				((not (ego has: iWadODough))
					(Print 125 3)
				)
				(gotHaircutInCity
					(Print 125 4)
					(Print 125 5)
				)
				((== currentStatus egoSITTING)
					(YouAre)
				)
				(else
					(= gotHaircutInCity TRUE)
					(self changeState: 2)
				)
			)
		)
		(if (Said 'call/man')
			(= talkedToBarber TRUE)
			(if (== gotHaircutInCity TRUE)
				(Print 125 6)
			else
				(Print 125 7)
				(Print 125 8)
			)
		)
	)
)

(instance aHandle of View
	(properties
		y 1128
		x 160
		view 246
		cel 1
		priority 14
		signal ignrAct
	)
)

(instance aHeads of Prop
	(properties
		view 244
		signal ignrAct
	)
)

(instance aChair of View
	(properties
		y 118
		x 164
		view 232
		loop 1
		priority 8
		signal ignrAct
	)
)

(instance aBarber of Actor
	(properties
		y 153
		x 113
		view 233
		loop 2
	)
)

(instance aThoughtBalloon of Prop
	(properties
		y 1127
		x 160
		priority 14
		signal ignrAct
	)
)

(instance aThought of Prop
	(properties
		y 1119
		x 161
		priority 15
		signal ignrAct
	)
)
