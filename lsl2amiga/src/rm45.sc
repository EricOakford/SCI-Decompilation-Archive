;;; Sierra Script 1.0 - (do not remove this comment)
(script# 45)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm45 0
)

(local
	local0
	talkedToBarber
)
(instance rm45 of Room
	(properties
		picture 125
		south 40
	)
	
	(method (init)
		(Load VIEW currentEgoView)
		(Load VIEW 232)
		(Load VIEW 430)
		(Load VIEW 149)
		(super init:)
		(aChair
			posn: 167 115
			setPri: 8
			init:
		)
		(aBarber
			illegalBits: 0
			init:
		)
		(NormalEgo)
		(ego posn: 160 159 init:)
		(self setRegions: BARBER RESORT setScript: rm45Script)
	)
)

(instance rm45Script of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cBLUE)
			(curRoom newRoom: 40)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= currentEgoView 151)
					(= seconds 10)
				)
			)
			(1
				(if (not talkedToBarber)
					(Print 45 6)
					(= seconds (Random 12 25))
					(= state 0)
				)
			)
			(2
				(= seconds (= cycles 0))
				(= currentStatus egoSITTING)
				(HandsOff)
				(Print 45 7)
				(Print 45 8)
				(Print 45 9)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 157 120 self
				)
				(aBarber setCycle: EndLoop)
			)
			(3
				(aChair hide:)
				(ego
					view: 232
					setLoop: 2
					cel: 0
					posn: 167 115
					setCycle: EndLoop self
				)
			)
			(4
				(aBarber setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(5
				(ego hide:)
				(aBarber
					view: 430
					setLoop: 2
					cel: 0
					posn: 167 115
					setCycle: EndLoop self
				)
			)
			(6
				(Print 45 10 #at -1 20 #draw)
				(Print 45 11 #at -1 20)
				(Print 45 12 #at -1 20)
				(Print 45 13 #at -1 130)
				(= seconds 3)
			)
			(7
				(aBarber
					setLoop: 4
					cel: 0
					posn: 165 85
					cycleSpeed: 1
					setPri: 9
					setCycle: EndLoop self
				)
				(aChair view: 430 setLoop: 3 cel: 0 show:)
			)
			(8
				(aBarber
					setLoop: 5
					cel: 0
					cycleSpeed: 2
					setCycle: CycleTo 4 1 self
				)
			)
			(9
				(Print 45 14 #at -1 20 #draw)
				(Print 45 15 #at -1 20)
				(= seconds 3)
			)
			(10
				(aBarber setCycle: EndLoop self)
			)
			(11
				(Print 45 16 #at -1 20 #draw)
				(Print 45 17 #at -1 20)
				(Print 45 18 #at -1 20)
				(Print 45 19 #at -1 20)
				(= seconds 3)
			)
			(12
				(aBarber setLoop: 6 cel: 0 setCycle: EndLoop self)
			)
			(13
				(= seconds 3)
			)
			(14
				(Print 45 20 #at -1 20)
				(Print 45 21 #at -1 20)
				(= seconds 3)
			)
			(15
				(aBarber setLoop: 1 setCel: 0 setPri: -1 posn: 167 91)
				(aChair
					setLoop: 7
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(16
				(= hairDyedBlonde TRUE)
				(theGame changeScore: 3)
				(aChair view: 232 setLoop: 1 setCel: 0)
				(= currentEgoView 149)
				(NormalEgo loopN)
				(ego posn: 157 119 show:)
				(Print 45 22 #draw)
				(= cycles 5)
			)
			(17
				(Print (Format @str 45 23 tritePhrase))
				(aBarber stopUpd:)
				(aChair stopUpd:)
			)
			(18
				(= seconds (= cycles 0))
				(User canControl: FALSE canInput: FALSE)
				(Print 45 24)
				(Print 45 25)
				(Print 45 26)
				(= currentEgoView (ego view?))
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 157 120 self
				)
				(aBarber illegalBits: 0 setCycle: EndLoop)
			)
			(19
				(aChair hide:)
				(ego
					view: 431
					setLoop: 0
					cel: 7
					posn: 167 115
					setCycle: BegLoop self
				)
			)
			(20
				(aBarber setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(21
				(ego hide:)
				(aBarber
					view: 431
					setLoop: 1
					cel: 0
					posn: 167 115
					setCycle: EndLoop self
				)
			)
			(22
				(Print 45 27 #at -1 20 #draw)
				(Print 45 28 #at -1 20)
				(Print 45 29 #at -1 20)
				(Print 45 30 #at -1 20)
				(Print 45 31 #at -1 20)
				(Print 45 32 #at -1 130)
				(= seconds 3)
			)
			(23
				(aBarber
					setLoop: 2
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(24
				(= seconds 3)
			)
			(25
				(aBarber
					setLoop: 3
					cel: 0
					posn: 165 81
					setCycle: CycleTo 1 1 self
				)
				(aChair view: 431 setLoop: 7 cel: 0 posn: 167 115 show:)
			)
			(26
				(= seconds 3)
			)
			(27
				(aBarber setCycle: EndLoop self)
			)
			(28
				(= seconds 3)
			)
			(29
				(Print 45 33 #at -1 20)
				(Print 45 34 #at -1 20)
				(= seconds 3)
			)
			(30
				(aBarber setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(31
				(Print 45 35 #at -1 130 #draw)
				(aBarber setLoop: 5 cel: 0 setCycle: EndLoop)
				(ego
					view: 431
					posn: 157 94
					setLoop: 6
					setCycle: Forward
					setStep: 1 10
					setPri: 9
					setMotion: MoveTo 157 57 self
					show:
				)
			)
			(32
				(= seconds 4)
			)
			(33
				(ego setCel: 0)
				(= seconds 3)
			)
			(34
				(ego setMotion: MoveTo 157 94 self)
			)
			(35
				(ego hide:)
				(aChair hide:)
				(aBarber view: 431 setLoop: 2 setCel: 5 posn: 167 115)
				(= seconds 3)
			)
			(36
				(Print 45 36 #at -1 20)
				(Print 45 37 #at -1 20)
				(= seconds 3)
			)
			(37
				(aBarber setCycle: BegLoop self)
			)
			(38
				(aBarber
					view: 430
					setLoop: 1
					setCel: 0
					setPri: -1
					posn: 167 91
				)
				(aChair
					view: 431
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
					show:
				)
			)
			(39
				(= gotHaircutAtResort TRUE)
				(theGame changeScore: 3)
				(aChair view: 232 setLoop: 1 setCel: 0)
				(NormalEgo loopN)
				(ego posn: 157 119 show:)
				(Print 45 38 #draw)
				(= seconds 2)
			)
			(40
				(Print (Format @str 45 39 tritePhrase))
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
				(Said 'drain/hair')
				(Said '(get<in)/barstool')
				(Said 'get/haircut,(cut<hair)')
			)
			(= talkedToBarber TRUE)
			(cond 
				((not (ego inRect: 148 117 180 127))
					(Print 45 0)
				)
				((== currentStatus egoSITTING)
					(YouAre)
				)
				((and hairDyedBlonde gotHaircutAtResort)
					(Print 45 1)
				)
				((not hairDyedBlonde)
					(self changeState: 2)
				)
				((and (!= currentEgoView 150) (!= currentEgoView 151))
					(Print 45 2)
				)
				(else
					(self changeState: 18)
				)
			)
		)
		(if (Said 'look/man')
			(Print 45 3)
		)
		(if (Said 'call/man')
			(= talkedToBarber TRUE)
			(if (!= currentEgoView vEgo)
				(Print 45 4)
			else
				(Print 45 5)
			)
		)
	)
)

(instance aChair of Prop
	(properties
		view 232
		loop 1
		signal ignrAct
	)
)

(instance aBarber of Actor
	(properties
		y 91
		x 184
		view 430
		signal ignrAct
	)
)
