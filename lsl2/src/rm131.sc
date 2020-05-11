;;; Sierra Script 1.0 - (do not remove this comment)
(script# 131)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm131 0
)

(local
	lifeboat1
	lifeboat2
	lifeboat3
	lifeboat4
	lifeboat5
	lifeboat6
	egoLifeboat
)
(instance rm131 of Room
	(properties
		picture 31
		horizon 5
	)
	
	(method (init)
		(Load VIEW 300)
		(Load VIEW 301)
		(super init:)
		((View new:)
			view: 300
			loop: 0
			priority: 15
			posn: 216 44
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 300
			loop: 4
			priority: 15
			posn: 99 22
			ignoreActors:
			addToPic:
		)
		((= lifeboat1 (Prop new:))
			view: 301
			setLoop: 0
			setPri: 14
			posn: 159 75
			cycleSpeed: 5
			init:
		)
		((= lifeboat2 (Prop new:))
			view: 301
			setLoop: 0
			setPri: 14
			posn: 199 75
			cycleSpeed: 5
			init:
		)
		((= lifeboat3 (Prop new:))
			view: 301
			setLoop: 0
			setPri: 14
			posn: 217 75
			cycleSpeed: 5
			init:
		)
		((= lifeboat4 (Prop new:))
			view: 301
			setLoop: 0
			setPri: 14
			posn: 234 75
			cycleSpeed: 5
			init:
		)
		((= lifeboat5 (Prop new:))
			view: 301
			setLoop: 0
			setPri: 14
			posn: 251 75
			cycleSpeed: 5
			init:
		)
		((= lifeboat6 (Prop new:))
			view: 301
			setLoop: 0
			setPri: 14
			posn: 268 75
			cycleSpeed: 5
			init:
		)
		((= egoLifeboat (Actor new:))
			view: 301
			setLoop: 2
			setPri: 14
			setCycle: Forward
			illegalBits: 0
			posn: 160 1132
			setStep: 1 1
			cycleSpeed: 1
			moveSpeed: 1
			init:
		)
		(ego posn: 123 999)
		(HandsOff)
		(= currentStatus egoAtSea)
		(self setRegions: 300 setScript: rm131Script)
	)
)

(instance rm131Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1 (Print 131 6) (= seconds 5))
			(2
				(Print 131 7)
				(theGame changeScore: 5)
				(= seconds 3)
			)
			(3
				(lifeboat1 setCycle: EndLoop self)
				(lifeboat2 setCycle: EndLoop)
				(lifeboat3 setCycle: EndLoop)
				(lifeboat4 setCycle: EndLoop)
				(lifeboat5 setCycle: EndLoop)
				(lifeboat6 setCycle: EndLoop)
			)
			(4
				(lifeboat2 stopUpd:)
				(lifeboat3 stopUpd:)
				(lifeboat4 stopUpd:)
				(lifeboat5 stopUpd:)
				(lifeboat6 stopUpd:)
				(lifeboat1 setLoop: 1 stopUpd:)
				(User canControl: FALSE canInput: TRUE)
				(egoLifeboat posn: 160 132 setMotion: MoveTo 123 146 self)
			)
			(5
				(Print 131 8)
				(egoLifeboat setMotion: MoveTo -12 201 self)
				(User canInput: TRUE)
			)
			(6 (curRoom newRoom: 138))
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if
		(and (ego has: iWig) (Said '(conceal<on),apply,wear/wig'))
			(Print 131 0)
			(if (not woreWigAtSea)
				(theGame changeScore: 5)
				(= woreWigAtSea TRUE)
			)
		)
		(if
			(and
				(ego has: iSpinachDip)
				(or
					(Said '/bread<(overboard,(board<over))')
					(Said 'conceal,conceal,throw,crap/bread')
				)
			)
			(theGame changeScore: 2)
			(Print 131 1)
			(Print 131 2)
			(Print 131 3 #at -1 152)
			(ego put: iSpinachDip -1)
		)
		(if (Said 'look>')
			(if (Said '/craft') (Print 131 4))
			(if (Said '[/boat,airport]') (Print 131 5))
		)
	)
)
