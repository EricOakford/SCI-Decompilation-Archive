;;; Sierra Script 1.0 - (do not remove this comment)
(script# 71)
(include game.sh)
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
(instance theSound of Sound)

(instance rm71 of Room
	(properties
		picture 71
		horizon 5
		south 72
	)
	
	(method (init)
		(Load VIEW 105)
		(Load VIEW 173)
		(Load VIEW 702)
		(super init:)
		(aSwarm
			setPri: 14
			setCycle: Walk
			illegalBits: 0
			init:
		)
		(self setRegions: ISLAND setScript: rm71Script)
		(if ((inventory at: iStoutStick) ownedBy: curRoomNum)
			(= stickOnGround TRUE)
			(Load VIEW 721)
			(aStick
				init:
				stopUpd:
			)
		)
		(if (== prevRoomNum 72)
			(theSound number: 3 init:)
			(NormalEgo)
			(ego posn: 142 185 init:)
		else
			(Load VIEW 171)
			(Load VIEW 110)
			(Load VIEW 114)
			(Load SOUND 1)
			(Load SOUND 2)
			(theSound number: 1 loop: 1 play:)
			(aBigEgo
				setPri: 14
				init:
			)
			(aBigEgoFace
				setPri: 15
				setCycle: Forward
				init:
			)
			((inventory at: iSoap) moveTo: -1)
			(ego
				;lose any no-longer-useful items
				put: iOnklunk -1
				put: iKnife -1
				put: iPassport -1
				put: iParachute -1
				put: iWadODough -1
				ignoreHorizon:
				illegalBits: 0
				view: 171
				setLoop: 0
				setCycle: Forward
				setStep: 1 12
				setPri: 10
				posn: 193 -129
				init:
			)
			(HandsOff)
			(rm71Script changeState: 1)
			(= currentStatus egoFALLING)
		)
	)
)

(instance rm71Script of Script
	(method (doit)
		(super doit:)
		(if (== state 2)
			(ShakeScreen 1 (Random 1 3))
		)
		(if (and (& (ego onControl:) cYELLOW) (== currentStatus egoNORMAL))
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
					setCycle: EndLoop self
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
				(ego setLoop: 2 cel: 0 setCycle: EndLoop self cycleSpeed: 2)
			)
			(6
				(Print 71 19 #draw)
				(NormalEgo loopS)
				(theSound number: 3 loop: -1)
			)
			(7
				(= currentStatus egoSTOPPED)
				(HandsOff)
				(ego illegalBits: 0)
				(Print 71 20)
				(aSwarm show: setCycle: EndLoop self)
				(theSound play:)
			)
			(8
				(aSwarm
					setLoop: 1
					cel: 0
					posn: 106 106
					setCycle: CycleTo 2 1 self
				)
			)
			(9
				(aSwarm setCycle: EndLoop self)
				(ego
					view: 173
					setLoop: 0
					cel: 0
					posn: 98 120
					setPri: 11
					setCycle: Forward
				)
			)
			(10
				(aSwarm dispose:)
				(ego setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(11
				(ego setLoop: 2 setCycle: Forward)
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
				(= currentStatus egoDYING)
			)
			(16
				(Print 71 24)
				(= currentStatus egoCROUCHING)
				(HandsOff)
				(ego
					illegalBits: 0
					view: 105
					setLoop: 2
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
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
				(ego setLoop: 2 setCel: 255 setCycle: BegLoop self)
			)
			(19
				(NormalEgo loopS)
				(if (> (++ avoidedBees) 1)
					(Print 71 25 #draw)
				else
					(Print 71 26 #draw)
					(theGame changeScore: 6)
				)
			)
			(20
				(Print 71 27 #draw)
				(= currentStatus egoCROUCHING)
				(HandsOff)
				(ego
					illegalBits: 0
					view: 105
					setLoop: 3
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
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
				(ego setLoop: 3 setCel: 255 setCycle: BegLoop self)
			)
			(23
				(NormalEgo loopN)
				(Print 71 28 #draw)
				(Print 71 29)
				(Print 71 30)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look<down')
			(Print 71 0)
			(if stickOnGround
				(Print 71 1)
			)
		)
		(if (Said 'look>')
			(if (Said '/carpet,dirt')
				(Print 71 0)
				(if stickOnGround
					(Print 71 1)
				)
			)
			(if (Said '/path')
				(Print 71 2)
			)
			(if (and stickOnGround (Said '/stick'))
				(Print 71 1)
			)
			(if (Said '/ear,art,lip')
				(Print 71 3)
			)
			(if (Said '/bush')
				(Print 71 4)
			)
			(if (Said '/bee')
				(Print 71 5)
			)
			(if (Said '[/forest,palm,landscape,airport]')
				(Print 71 6)
				(Print 71 7)
				(Print 71 8)
			)
		)
		(if (Said '(get<up),get/stick')
			(cond 
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				((not stickOnGround)
					(AlreadyTook)
				)
				((not (ego inRect: 205 6 226 82))
					(NotClose)
				)
				(else
					(Ok)
					(= stickOnGround FALSE)
					(ego get: iStoutStick)
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
		(if (Said 'hop,(stair<over)')
			(Print 71 12)
		)
		;Amiga addiion. Why the redundant code? The 'climb' spec
		; could have just been added to the existing code.
		(if (Said 'climb,(board<below)')
			(Print 71 13)
			(cond 
				((& (ego onControl:) cBLUE)
					(self changeState: 16)
				)
				((& (ego onControl:) cGREEN)
					(self changeState: 20)
				)
				(else
					(Print 71 14)
				)
			)
		)
		(if (Said 'crawl,(board<below)')
			(Print 71 13)
			(cond 
				((& (ego onControl:) cBLUE)
					(self changeState: 16)
				)
				((& (ego onControl:) cGREEN)
					(self changeState: 20)
				)
				(else (Print 71 14))
			)
		)
		(if (Said '/branch')
			(Print 71 15)
		)
		(if (Said 'climb/palm')
			(Print 71 16)
		)
		(if (Said 'climb')
			(Print 71 17)
		)
	)
)

(instance aStick of View
	(properties
		y 71
		x 216
		view 721
		signal ignrAct
	)
)

(instance aBigEgo of View
	(properties
		y 1080
		x 243
		view 110
		signal ignrAct
	)
)

(instance aSwarm of Actor
	(properties
		y 109
		x 148
		view 702
		signal ignrAct
	)
)

(instance aBigEgoFace of Prop
	(properties
		y 1080
		x 243
		view 114
		signal ignrAct
	)
)
