;;; Sierra Script 1.0 - (do not remove this comment)
(script# 54)
(include sci.sh)
(use Main)
(use Door)
(use AirplaneActor)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm54 0
)

(local
	theBag
)
(instance theSound of Sound
	(properties
		number 5
		loop -1
	)
)

(instance rm54 of Rm
	(properties
		picture 54
		horizon 5
		east 55
		west 53
	)
	
	(method (init)
		(Load rsVIEW 515)
		(Load rsVIEW 516)
		(super init:)
		(addToPics add: aAgent doit:)
		(if ((inventory at: 22) ownedBy: curRoomNum)
			(Load rsVIEW 155)
			(Load rsSOUND 5)
			(Load rsFONT 7)
			(theSound init:)
			(aBag
				view: 515
				setLoop: 0
				setPri: 5
				setStep: 2 1
				posn: 90 123
				illegalBits: 0
				init:
				hide:
				setScript: bagScript
			)
		)
		(aPlane startX: 306 startY: 22 endX: 222 endY: 22 init:)
		(aTraveler
			setPri: 2
			setStep: 1 1
			setCycle: Walk
			illegalBits: 0
			init:
			hide:
		)
		(cond 
			((== prevRoomNum 53) (ego posn: 1 154))
			((== prevRoomNum 55) (ego posn: 316 154))
			(else (ego posn: 316 154))
		)
		(NormalEgo)
		(ego init:)
		(aDoor
			setPri: 11
			doorCtrl: 2
			doorBlock: 16384
			roomCtrl: 0
			msgLook: {The low blue gate leads back to the Customs Inspection area.}
			msgFunny: {Knock. Knock. (No one's there!)}
			init:
		)
		(self setRegions: 500 setScript: rm54Script)
	)
	
	(method (dispose)
		(DisposeScript 992)
		(DisposeScript travelerScript)
		(super dispose:)
	)
)

(instance rm54Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(theGame changeScore: 5)
				(aBag dispose:)
				(bagScript dispose:)
				(Print 54 25 #at -1 20 #width 222 #font 7)
				(theSound play:)
				(ego get: 22)
				(= suitcaseBombState 1)
				(HandsOff)
				(ego view: 155 setLoop: 1)
				(Print 54 26 #draw)
				(= seconds 3)
			)
			(2
				(Print 54 27)
				(ego setMotion: MoveTo (ego x?) 154 self)
			)
			(3
				(ego
					setStep: 4 3
					setCycle: Walk
					setMotion: MoveTo 0 154 self
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if
		(and (not (ego has: 22)) (Said 'look/bag,baggage'))
			(if ((inventory at: 22) ownedBy: curRoomNum)
				(Print 54 0)
			else
				(Print 54 1)
			)
		)
		(if (Said 'look>')
			(if (Said '/belt,baggage,bag,belt') (Print 54 2))
			(if (Said '/man,cop') (Print 54 3))
			(if (Said '/art')
				(Print 54 4)
				(if (> filthLevel 10) (Print 54 5 #at -1 130))
			)
			(if (Said '[/airport]') (Print 54 6))
		)
		(if
			(or
				(Said 'awaken<up')
				(Said 'awaken,lagoon,attack,call/man,cop')
			)
			(Print 54 7)
		)
		(if (Said 'carry,(get<up),get/bag,baggage')
			(cond 
				((not ((inventory at: 22) ownedBy: curRoomNum)) (Print 54 8))
				((not (& (ego onControl:) $0008)) (NotClose))
				((> (ego distanceTo: aBag) 23) (Print 54 9))
				(else
					(aBag hide:)
					(if (and (!= theBag 0) (!= theBag 8))
						(Print 54 10 #at -1 20 #draw)
					)
					(switch theBag
						(0 (Print 54 11))
						(1 (Print 54 12))
						(2 (Print 54 13))
						(3 (Print 54 14) (Print 54 15))
						(4 (Print 54 16))
						(5 (Print 54 17))
						(6 (Print 54 18))
						(7 (Print 54 19) (Print 54 20))
						(8 (Print 54 21))
						(9 (Print 54 18))
						(10 (Print 54 22))
						(11 (Print 54 23))
						(12
							(self changeState: 1)
							(return)
						)
					)
					(Print 54 24 #at -1 20)
					(aBag show:)
				)
			)
		)
	)
)

(instance travelerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(aTraveler
					posn: 230 36
					show:
					setMotion: MoveTo 291 37 self
				)
			)
			(2
				(aTraveler
					setLoop: (if (== (aTraveler loop?) 3) 4 else 3)
				)
				(self changeState: 0)
			)
		)
	)
)

(instance bagScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aBag
					posn: 90 123
					setCel: theBag
					setLoop: 0
					show:
					setMotion: MoveTo 152 123 self
				)
			)
			(1
				(aBag setLoop: 1 setMotion: MoveTo 303 123 self)
			)
			(2
				(if (> (++ theBag) 12) (= theBag 0))
				(aBag hide:)
				(self changeState: 0)
			)
		)
	)
)

(instance aAgent of PV
	(properties
		y 157
		x 109
		view 516
		priority 12
	)
)

(instance aBag of Act
	(properties
		signal $4000
	)
)

(instance aPlane of Airplane
	(properties)
)

(instance aTraveler of Act
	(properties
		y 36
		x 230
		view 515
		loop 3
		signal $4000
	)
)

(instance aDoor of AutoDoor
	(properties
		y 156
		x 15
		view 515
		loop 2
	)
)
