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
	barber
	chair
	mirror
	egoBigFace
	egoOnChair
	mirrorHandle
	detailLevel
	daydreamView
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
			(= daydreamView 245)
		else
			(= daydreamView 248) ;censored view
		)
		(Load VIEW daydreamView)
		(super init:)
		(cond 
			((> howFast 60) (= detailLevel 3))
			((> howFast 40) (= detailLevel 2))
			((> howFast 20) (= detailLevel 1))
		)
		((= mirror (Prop new:))
			view: daydreamView
			ignoreActors:
			posn: 160 1127
			setPri: 14
			init:
		)
		((= egoBigFace (Prop new:))
			view: daydreamView
			ignoreActors:
			posn: 161 1119
			setPri: 15
			init:
		)
		((= mirrorHandle (View new:))
			view: 246
			setCel: 1
			setPri: 14
			ignoreActors:
			posn: 160 1128
			init:
		)
		((= egoOnChair (Prop new:))
			view: 244
			ignoreActors:
			init:
			hide:
		)
		((= chair (View new:))
			view: 232
			loop: 1
			cel: 0
			posn: 164 118
			setPri: 8
			ignoreActors:
			stopUpd:
			init:
		)
		((= barber (Actor new:))
			view: 233
			loop: 2
			posn: 113 153
			setCycle: Walk
			illegalBits: -32768
			init:
		)
		(NormalEgo 3)
		(ego posn: 160 159 init:)
		(self setRegions: 7 200 setScript: rm125Script)
	)
)

(instance rm125Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0002) (curRoom newRoom: 25))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not gotHaircutInCity) (= seconds 10))
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
				(= currentStatus egoSitting)
				(HandsOff)
				(Print 125 10)
				(Print 125 11)
				(Print 125 12)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 157 120 self
				)
				(barber setMotion: MoveTo 185 119 self)
			)
			(3
				(chair hide:)
				(ego
					view: 232
					setLoop: 2
					cel: 0
					posn: 167 115
					setCycle: EndLoop
				)
			)
			(4
				(barber setMotion: MoveTo 179 111 self)
			)
			(5
				(barber setMotion: MoveTo 166 111 self illegalBits: 0)
			)
			(6
				(Print 125 13)
				(mirror
					view: 246
					setCel: 0
					posn: 160 127
					setPri: 14
					stopUpd:
				)
				(mirrorHandle posn: 160 128 stopUpd:)
				(egoBigFace
					view: 246
					setLoop: 1
					cel: 0
					posn: 161 119
					setPri: 15
					setCycle: Forward
					cycleSpeed: (* 2 detailLevel)
				)
				(= seconds 6)
			)
			(7
				(Print 125 14 #at -1 15 #width 280)
				(= seconds 3)
			)
			(8
				(egoBigFace posn: 161 1119)
				(mirrorHandle posn: 160 1128)
				(mirror posn: 160 1127)
				(Print 125 15 #at -1 20 #draw)
				(= seconds 3)
			)
			(9
				(ego hide:)
				(barber view: 244 setLoop: 0 posn: 167 115 setCel: 0)
				(= cycles 30)
			)
			(10
				(barber cycleSpeed: 1 setCycle: EndLoop self)
			)
			(11
				(Print 125 16 #draw)
				(barber
					setLoop: 1
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(12
				(egoOnChair
					posn: 166 100
					setLoop: 2
					cel: 0
					setPri: 9
					cycleSpeed: 2
					show:
					setCycle: Forward
				)
				(barber stopUpd:)
				(= seconds 5)
			)
			(13
				(Print 125 17)
				(Print 125 18)
				(Print 125 19)
				(= seconds 5)
			)
			(14
				(mirror
					view: daydreamView
					setLoop: 0
					cel: 0
					setPri: 14
					posn: 154 91
					cycleSpeed: detailLevel
					setCycle: EndLoop self
				)
			)
			(15
				(mirror stopUpd:)
				(egoBigFace
					view: daydreamView
					setLoop: 2
					setPri: 15
					setCel: 0
					posn: 110 45
					cycleSpeed: detailLevel
					setCycle: EndLoop self
				)
			)
			(16
				(egoBigFace setCycle: BegLoop self)
			)
			(17
				(egoBigFace setCycle: EndLoop self)
			)
			(18
				(egoBigFace setCycle: BegLoop self)
			)
			(19
				(egoBigFace setCycle: EndLoop self)
			)
			(20
				(egoBigFace setCycle: BegLoop self)
			)
			(21
				(egoBigFace posn: 161 1119)
				(= seconds 5)
			)
			(22
				(egoBigFace
					setLoop: 1
					posn: 112 40
					cel: 0
					setPri: 15
					setCycle: EndLoop self
				)
			)
			(23
				(egoBigFace posn: 161 1120)
				(= seconds 3)
			)
			(24
				(Print 125 20)
				(= seconds 3)
			)
			(25
				(egoBigFace
					setLoop: 3
					cel: 0
					posn: 103 41
					cycleSpeed: detailLevel
					setCycle: CycleTo 10 1 self
				)
			)
			(26
				(Print 125 21 #draw)
				(= cycles 8)
			)
			(27
				(egoBigFace setCycle: EndLoop self)
			)
			(28
				(egoBigFace posn: 161 1120)
				(mirror posn: 160 1128)
				(= seconds 6)
			)
			(29
				(egoOnChair hide:)
				(barber
					setLoop: 3
					cel: 0
					cycleSpeed: detailLevel
					setCycle: EndLoop self
				)
				(Print 125 22 #draw)
				(Print 125 23)
			)
			(30
				(egoOnChair
					posn: 166 94
					setLoop: 4
					cel: 0
					setPri: 14
					setCycle: Forward
					show:
				)
				(barber stopUpd:)
				(= seconds 11)
			)
			(31
				(egoOnChair dispose:)
				(Print 125 24 #draw)
				(= seconds 3)
			)
			(32
				(mirror
					view: 246
					setCel: 0
					posn: 160 127
					setPri: 14
					stopUpd:
				)
				(mirrorHandle posn: 160 128 stopUpd:)
				(egoBigFace
					view: 246
					setLoop: 1
					cel: 0
					setPri: 15
					posn: 161 119
					setCycle: Forward
					cycleSpeed: detailLevel
				)
				(= seconds 6)
			)
			(33
				(Print 125 25 #at -1 20 #draw)
				(= seconds 3)
			)
			(34
				(egoBigFace dispose:)
				(mirrorHandle dispose:)
				(mirror dispose:)
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
				(barber setLoop: 3 setCel: 255 setCycle: BegLoop self)
			)
			(38
				(barber setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(39
				(barber
					view: 233
					posn: 182 114
					loop: 2
					setCycle: Walk
					illegalBits: -32768
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
				(NormalEgo 2)
				(ego ignoreActors: 0)
				(chair show:)
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
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
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
				((not (ego inRect: 148 117 180 127)) (Print 125 0))
				((ego has: iMillionDollarBill) (Print 125 1) (Print 125 2))
				((not (ego has: iWadODough)) (Print 125 3))
				(gotHaircutInCity (Print 125 4) (Print 125 5))
				((== currentStatus egoSitting) (PrintYouAre))
				(else (= gotHaircutInCity TRUE) (self changeState: 2))
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
