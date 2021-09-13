;;; Sierra Script 1.0 - (do not remove this comment)
(script# 71)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm71 0
)

(local
	stickOnGround
)
(instance theSound of Sound
	(properties)
)

(instance rm71 of Rm
	(properties
		picture 71
		horizon 5
		south 72
	)
	
	(method (init)
		(Load rsVIEW 105)
		(Load rsVIEW 173)
		(Load rsVIEW 702)
		(super init:)
		(aSwarm setPri: 14 setCycle: Walk illegalBits: 0 init:)
		(self setRegions: 700 setScript: rm71Script)
		(if ((inventory at: 28) ownedBy: curRoomNum)
			(= stickOnGround 1)
			(Load rsVIEW 721)
			(aStick init: stopUpd:)
		)
		(if (== prevRoomNum 72)
			(theSound number: 3 init:)
			(NormalEgo)
			(ego posn: 142 185 init:)
		else
			(Load rsVIEW 171)
			(Load rsVIEW 110)
			(Load rsVIEW 114)
			(Load rsSOUND 1)
			(Load rsSOUND 2)
			(theSound number: 1 loop: 1 play:)
			(aBigEgo setPri: 14 init:)
			(aBigEgoFace setPri: 15 setCycle: Fwd init:)
			((inventory at: 18) moveTo: -1)
			(ego
				put: 10 -1
				put: 17 -1
				put: 7 -1
				put: 24 -1
				put: 6 -1
				ignoreHorizon:
				illegalBits: 0
				view: 171
				setLoop: 0
				setCycle: Fwd
				setStep: 1 12
				setPri: 10
				posn: 193 -129
				init:
			)
			(HandsOff)
			(rm71Script changeState: 1)
			(= currentStatus 12)
		)
	)
)

(instance rm71Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== state 2) (ShakeScreen 1 (Random 1 3)))
		(if
		(and (& (ego onControl:) $4000) (== currentStatus 0))
			(self changeState: 7)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(ego setMotion: MoveTo 193 80 self)
			)
			(2
				(ego
					setMotion: 0
					setStep: 3 2
					setLoop: 1
					cel: 0
					setCycle: End self
				)
			)
			(3
				(Print 71 18 #draw)
				(= seconds 3)
			)
			(4
				(aBigEgo posn: 143 80 stopUpd:)
				(aBigEgoFace posn: 143 80)
				(theSound dispose:)
				(theSound number: 2 play:)
				(= seconds 5)
			)
			(5
				(aBigEgoFace dispose:)
				(aBigEgo dispose:)
				(theSound dispose:)
				(ego setLoop: 2 cel: 0 setCycle: End self cycleSpeed: 2)
			)
			(6
				(Print 71 19 #draw)
				(NormalEgo 2)
				(theSound number: 3 loop: -1)
			)
			(7
				(= currentStatus 1000)
				(HandsOff)
				(ego illegalBits: 0)
				(Print 71 20)
				(aSwarm show: setCycle: End self)
				(theSound play:)
			)
			(8
				(aSwarm
					setLoop: 1
					cel: 0
					posn: 106 106
					setCycle: CT 2 1 self
				)
			)
			(9
				(aSwarm setCycle: End self)
				(ego
					view: 173
					setLoop: 0
					cel: 0
					posn: 98 120
					setPri: 11
					setCycle: Fwd
				)
			)
			(10
				(aSwarm dispose:)
				(ego setLoop: 1 cel: 0 setCycle: End self)
			)
			(11
				(ego setLoop: 2 setCycle: Fwd)
				(= seconds 3)
			)
			(12
				(ego setMotion: MoveTo 195 39 self)
			)
			(13
				(Print 71 21)
				(ego setPri: 9 setMotion: MoveTo 333 3 self)
			)
			(14
				(Print 71 22)
				(= seconds 3)
			)
			(15
				(Print 71 23)
				(theSound dispose:)
				(= currentStatus 1001)
			)
			(16
				(Print 71 24)
				(= currentStatus 1012)
				(HandsOff)
				(ego
					illegalBits: 0
					view: 105
					setLoop: 2
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(17
				(ego
					moveSpeed: 1
					setLoop: 0
					setCycle: Walk
					setStep: 1 1
					setMotion: MoveTo 92 157 self
				)
			)
			(18
				(ego setLoop: 2 setCel: 255 setCycle: Beg self)
			)
			(19
				(NormalEgo 2)
				(if (> (++ avoidedBees) 1)
					(Print 71 25 #draw)
				else
					(Print 71 26 #draw)
					(theGame changeScore: 6)
				)
			)
			(20
				(Print 71 27 #draw)
				(= currentStatus 1012)
				(HandsOff)
				(ego
					illegalBits: 0
					view: 105
					setLoop: 3
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(21
				(ego
					moveSpeed: 1
					setLoop: 1
					setCycle: Walk
					setStep: 1 1
					setMotion: MoveTo 94 145 self
				)
			)
			(22
				(ego setLoop: 3 setCel: 255 setCycle: Beg self)
			)
			(23
				(NormalEgo 3)
				(Print 71 28 #draw)
				(Print 71 29)
				(Print 71 30)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'look<down')
			(Print 71 0)
			(if stickOnGround (Print 71 1))
		)
		(if (Said 'look>')
			(if (Said '/carpet,dirt')
				(Print 71 0)
				(if stickOnGround (Print 71 1))
			)
			(if (Said '/path') (Print 71 2))
			(if (and stickOnGround (Said '/stick')) (Print 71 1))
			(if (Said '/ear,art,lip') (Print 71 3))
			(if (Said '/bush') (Print 71 4))
			(if (Said '/bee') (Print 71 5))
			(if (Said '[/forest,palm,landscape,airport]')
				(Print 71 6)
				(Print 71 7)
				(Print 71 8)
			)
		)
		(if (Said '(get<up),get/stick')
			(cond 
				((!= currentStatus 0) (NotNow))
				((not stickOnGround) (AlreadyTook))
				((not (ego inRect: 205 6 226 82)) (NotClose))
				(else
					(Ok)
					(= stickOnGround 0)
					(ego get: 28)
					(aStick hide:)
					(theGame changeScore: 4)
					(Print 71 9 #draw)
				)
			)
		)
		(if
			(or
				(Said 'attack,caress,apply/bush,branch,bee/stick')
				(Said 'lagoon,burn,attack,get/bee,branch,bush,honey')
			)
			(Print 71 10)
		)
		(if
			(or
				(Said 'drain,burn,conceal,apply/rejuvenator')
				(Said 'caress,lagoon,dig/carpet,hole,dirt,stick')
				(Said 'burn/bush')
			)
			(Print 71 11)
		)
		(if (Said 'hop,(stair<over)') (Print 71 12))
		(if (Said 'climb,(board<below)')
			(Print 71 13)
			(cond 
				((& (ego onControl:) $0002) (self changeState: 16))
				((& (ego onControl:) $0004) (self changeState: 20))
				(else (Print 71 14))
			)
		)
		(if (Said 'crawl,(board<below)')
			(Print 71 13)
			(cond 
				((& (ego onControl:) $0002) (self changeState: 16))
				((& (ego onControl:) $0004) (self changeState: 20))
				(else (Print 71 14))
			)
		)
		(if (Said '/branch') (Print 71 15))
		(if (Said 'climb/palm') (Print 71 16))
		(if (Said 'climb') (Print 71 17))
	)
)

(instance aStick of View
	(properties
		y 71
		x 216
		view 721
		signal $4000
	)
)

(instance aBigEgo of View
	(properties
		y 1080
		x 243
		view 110
		signal $4000
	)
)

(instance aSwarm of Act
	(properties
		y 109
		x 148
		view 702
		signal $4000
	)
)

(instance aBigEgoFace of Prop
	(properties
		y 1080
		x 243
		view 114
		signal $4000
	)
)
