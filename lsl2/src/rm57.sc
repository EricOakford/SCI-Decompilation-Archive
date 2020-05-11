;;; Sierra Script 1.0 - (do not remove this comment)
(script# 57)
(include game.sh)
(use Main)
(use Door)
(use AirplaneActor)
(use Intrface)
(use Extra)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm57 0
)

(local
	triedToEnterWrongSidewalk
	sidewalkNorth
	sidewalkSouth
	airplane
	child1
	child2
	child3
	gate
)
(instance rm57 of Room
	(properties
		picture 57
		horizon 1
	)
	
	(method (init)
		(Load VIEW 525)
		(Load VIEW 511)
		(super init:)
		((View new:)
			view: 525
			loop: 0
			cel: 0
			posn: 112 140
			setPri: 10
			addToPic:
		)
		((View new:)
			view: 525
			loop: 0
			cel: 1
			posn: 234 160
			setPri: 11
			addToPic:
		)
		((View new:)
			view: 525
			loop: 0
			cel: 3
			posn: 196 130
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 525
			loop: 0
			cel: 2
			posn: 197 111
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((= airplane (Airplane new:))
			view: 511
			setLoop: 2
			setCel: 0
			startX: -100
			startY: 14
			endX: 555
			endY: 14
			init:
		)
		((= gate (Door new:))
			view: 525
			setLoop: 6
			posn: 163 137
			setPri: 9
			entranceTo: 0
			doorCtrl: 0
			roomCtrl: 0
			init:
			locked: 1
			doorState: 0
			msgLook:
				{This door is controlled by the gentleman behind the counter. He'll unlock it for you if you'll show him a confirmed ticket for the next flight.}
			msgLookLock: {Right now, it's locked up tight!}
			msgLocked:
				{This door is controlled by the gentleman behind the counter. He'll unlock it for you if you'll show him a confirmed ticket for the next flight.}
			msgExcept: {Have the man at the desk open it for you!}
		)
		((= child1 (Extra new:))
			view: 525
			loop: 2
			posn: 92 130
			setPri: 10
			cycleSpeed: 1
			minPause: 20
			maxPause: 30
			minCycles: 11
			maxCycles: 33
			init:
		)
		((= child2 (Extra new:))
			view: 525
			loop: 1
			posn: 127 139
			setPri: 10
			cycleSpeed: 2
			minPause: 29
			maxPause: 100
			minCycles: 20
			maxCycles: 30
			init:
		)
		((= child3 (Prop new:))
			view: 525
			setLoop: 4
			posn: 100 157
			setPri: 12
			cycleSpeed: 0
			init:
			setScript: tumbleScript
		)
		((= sidewalkNorth (Actor new:))
			view: 525
			setLoop: 3
			setCel: 3
			setPri: 0
			setStep: 1 1
			posn: 192 180
			illegalBits: 0
			ignoreActors:
			init:
			setScript: sidewalkNorthScript
		)
		((= sidewalkSouth (Actor new:))
			view: 525
			setLoop: 3
			setCel: 3
			setPri: 0
			setStep: 1 1
			posn: 135 178
			illegalBits: 0
			ignoreActors:
			init:
			setScript: sidewalkSouthScript
		)
		(= currentStatus egoInTerminal)
		(HandsOff)
		(ego
			illegalBits: 0
			setPri: -1
			setLoop: 3
			setCycle: 0
			setStep: 1 1
			posn: 196 233
			setCel: 0
			init:
		)
		(self setRegions: AIRPORT setScript: rm57Script)
	)
)

(instance rm57Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			((& (ego onControl:) $0010) (self changeState: 2))
			((& (ego onControl:) $0008)
				(if (and (== currentStatus egoNormal) (== triedToEnterWrongSidewalk FALSE))
					(= triedToEnterWrongSidewalk TRUE)
					(Print 57 0)
				)
			)
			(else (= triedToEnterWrongSidewalk FALSE))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setCel: 0 setMotion: MoveTo 186 175 self)
			)
			(1
				(NormalEgo 3)
				(ego observeControl: 4 16384)
			)
			(2
				(= currentStatus egoInTerminal)
				(HandsOff)
				(ego
					illegalBits: 0
					setLoop: 2
					setCel: 4
					setStep: 1 1
					setMotion: MoveTo 129 234 self
				)
			)
			(3 (curRoom newRoom: 55))
			(4
				(HandsOff)
				(theGame changeScore: 3)
				(Print 57 20)
				(Print (Format @str 57 21 tritePhrase))
				(gate locked: FALSE force: TRUE open:)
				(IncrementGamePhase NULL 0 0)
				(ego setMotion: MoveTo 151 145 self)
			)
			(5
				(ego setMotion: MoveTo 151 133 self)
			)
			(6
				(gate setCycle: BegLoop)
				(ego
					illegalBits: 0
					setPri: 5
					setMotion: MoveTo 199 133 self
				)
			)
			(7 (curRoom newRoom: 58))
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if
			(or
				(Said 'give,finger,look/ticket/man,agent')
				(Said 'give,finger,look/agent,man/ticket')
				(Said 'give,finger,look,apply/ticket')
			)
			(cond 
				((not (ego has: iAirlineTicket)) (PrintDontHaveIt))
				((!= currentStatus egoNormal) (PrintNotNow))
				((not (ego inRect: 185 140 244 152)) (PrintNotCloseEnough))
				(else
					(Print 57 1)
					(if (== missedFlight TRUE)
						(Print 57 2)
						(Print (Format @str 57 3 tritePhrase))
					else
						(self changeState: 4)
					)
				)
			)
		)
		(if
			(and
				(ego inRect: 185 140 244 152)
				(Said 'call/man,agent')
			)
			(Print (Format @str 57 4 introductoryPhrase))
			(if (not (ego has: iAirlineTicket))
				(Print 57 5)
				(Print 57 6)
			else
				(Print 57 7)
			)
		)
		(if (Said 'get/pamphlet')
			(cond 
				((!= currentStatus egoNormal) (PrintNotNow))
				((not ((inventory at: iPamphlet) ownedBy: curRoomNum)) (Print 57 8))
				((not (ego inRect: 159 140 195 152)) (PrintNotCloseEnough))
				(else (ego get: iPamphlet) (theGame changeScore: 11) (Print 57 9))
			)
		)
		(if (Said 'look>')
			(if (Said '/man,agent') (Print 57 10) (Print 57 11))
			(if (Said '/buffet')
				(if ((inventory at: iPamphlet) ownedBy: curRoomNum)
					(Print 57 12)
				else
					(Print 57 13)
				)
			)
			(if (Said '/pamphlet') (Print 57 14))
			(if (Said '/children,bimbo')
				(if (> filthLevel 4) (Print 57 15) else (Print 57 16))
			)
			(if (Said '/computer')
				(Print 57 17)
				(Print 57 18 #at -1 152)
			)
			(if (Said '[/airport]') (Print 57 19))
		)
	)
)

(instance sidewalkNorthScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sidewalkNorth setMotion: MoveTo 192 179 self)
			)
			(1
				(sidewalkNorth posn: 192 221)
				(self changeState: 0)
			)
		)
	)
)

(instance sidewalkSouthScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sidewalkSouth setMotion: MoveTo 135 202 self)
			)
			(1
				(sidewalkSouth posn: 135 178)
				(self changeState: 0)
			)
		)
	)
)

(instance tumbleScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(child3 setLoop: 4 cel: 0)
				(= seconds (Random 2 5))
			)
			(1
				(child3 cycleSpeed: (Random 0 2) setCycle: EndLoop self)
			)
			(2
				(child3 setLoop: 5 cel: 0)
				(= seconds (Random 2 5))
			)
			(3
				(child3 cycleSpeed: (Random 0 2) setCycle: EndLoop self)
			)
			(4 (self changeState: 0))
		)
	)
)
