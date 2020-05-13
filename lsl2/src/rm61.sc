;;; Sierra Script 1.0 - (do not remove this comment)
(script# 61)
(include game.sh)
(use Main)
(use Intrface)
(use Extra)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm61 0
)

(local
	aStewardess
	aCockDoor
	aKnitting
	aNewspaper
	aHand
)
(instance rm61 of Room
	(properties
		picture 61
	)
	
	(method (init)
		(Load VIEW 100)
		(Load VIEW 661)
		(Load VIEW 603)
		(super init:)
		(NormalEgo)
		((View new:)
			view: 661
			loop: 0
			cel: 2
			posn: 254 75
			setPri: 4
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 661
			loop: 0
			cel: 3
			posn: 205 75
			setPri: 4
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 661
			loop: 0
			cel: 4
			posn: 151 76
			setPri: 4
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 661
			loop: 0
			cel: 1
			posn: 206 90
			setPri: 5
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 661
			loop: 0
			cel: 4
			posn: 261 91
			setPri: 5
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 661
			loop: 0
			cel: 0
			posn: 150 91
			setPri: 5
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 661
			loop: 1
			cel: 4
			posn: 157 119
			setPri: 8
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 661
			loop: 1
			cel: 5
			posn: 286 119
			setPri: 8
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 661
			loop: 1
			cel: 3
			posn: 223 120
			setPri: 8
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 661
			loop: 1
			cel: 0
			posn: 152 137
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 661
			loop: 1
			cel: 2
			posn: 288 137
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 661
			loop: 1
			cel: 1
			posn: 226 138
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 661
			loop: 2
			cel: 1
			posn: 189 77
			setPri: 4
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 661
			loop: 2
			cel: 2
			posn: 239 93
			setPri: 5
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 661
			loop: 2
			cel: 0
			posn: 188 110
			setPri: 7
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 661
			loop: 2
			cel: 1
			posn: 255 110
			setPri: 7
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 661
			loop: 2
			cel: 2
			posn: 279 129
			setPri: 9
			ignoreActors:
			addToPic:
		)
		((= aNewspaper (Extra new:))
			view: 661
			loop: 3
			posn: 146 74
			setPri: 6
			pauseCel: 255
			minPause: 3
			maxPause: 8
			init:
		)
		((= aHand (Extra new:))
			view: 661
			loop: 4
			posn: 279 108
			setPri: 9
			pauseCel: 255
			minPause: 3
			maxPause: 8
			minCycles: 5
			maxCycles: 11
			init:
		)
		((= aKnitting (Extra new:))
			view: 661
			loop: 5
			posn: 149 59
			setPri: 5
			pauseCel: 255
			minPause: 3
			maxPause: 11
			minCycles: 15
			maxCycles: 31
			init:
		)
		(self setRegions: AIRPLANE setScript: rm61Script)
		((= aCockDoor (Prop new:))
			view: 603
			ignoreActors:
			setLoop: 0
			posn: 41 103
			setPri: 5
			setCel: 0
			stopUpd:
			init:
		)
		(if (== prevRoomNum 62)
			(ego observeControl: cYELLOW loop: 1 posn: 300 101)
			((View new:)
				view: 603
				loop: 1
				cel: 0
				posn: 38 156
				setPri: 15
				ignoreActors:
				addToPic:
			)
		else
			(Load VIEW 23)
			((= aStewardess (Prop new:))
				view: 600
				setLoop: 4
				posn: 55 100
				stopUpd:
				init:
			)
			(ego loop: 3 posn: 42 143)
			(HandsOff)
			(= currentStatus egoBOARDPLANE)
			(rm61Script changeState: 1)
		)
		(ego init:)
	)
)

(instance rm61Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== state 17) (ShakeScreen 1 (Random 1 3)))
		(if (& (ego onControl:) $0004) (curRoom newRoom: 62))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (= seconds 2))
			(2
				(ego setMotion: MoveTo 53 117 self)
			)
			(3
				(aStewardess setCycle: Forward)
				(= seconds 3)
			)
			(4
				(aStewardess setCel: 0)
				(Print 61 7 #draw)
				(Print 61 8 #icon 23 0 0)
				(= seconds 3)
			)
			(5
				(aStewardess setCycle: Forward)
				(= seconds 3)
			)
			(6
				(aStewardess setCel: 0)
				(Print 61 9 #draw)
				(ego put: 23 -1)
				(Print 61 10)
				(Print 61 11)
				(= seconds 3)
			)
			(7
				(aStewardess setCycle: Forward)
				(= seconds 3)
			)
			(8
				(aStewardess setCel: 0 stopUpd:)
				(Print 61 12 #draw)
				(Print 61 13)
				(Print 61 14)
				(Print (Format @str 61 15 tritePhrase))
				(= seconds 2)
			)
			(9
				(ego setMotion: MoveTo 103 102 self)
			)
			(10 (= seconds 3))
			(11
				(Print 61 16)
				(ego setMotion: MoveTo 326 102)
			)
			(12
				(HandsOff)
				(= currentStatus egoSTOPPED)
				(Ok)
				(aCockDoor setCycle: EndLoop self)
			)
			(13
				(ego illegalBits: 0 setMotion: MoveTo 0 102 self)
			)
			(14
				(aCockDoor setCycle: BegLoop self)
			)
			(15
				(Print 61 17)
				(= seconds 3)
			)
			(16
				(Print 61 18)
				(Print 61 19)
				(= seconds 3)
			)
			(17 (= seconds 3))
			(18
				(Print 61 20)
				(= currentStatus egoDEAD)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/galley,curtain,bimbo')
				(if (ego inRect: 0 0 106 92)
					(Print 61 0)
				else
					(NotClose)
				)
			)
			(if (Said '/door') (Print 61 1))
			(if (Said '[/children,man,bimbo,airline,airport]')
				(Print 61 2)
				(Print 61 3)
			)
		)
		(if (Said 'call/man,bimbo,children')
			(Print 61 4)
			(Print 61 5)
		)
		(if (Said 'open/door')
			(cond 
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				((& (ego onControl:) $0010) (Print 61 6))
				((not (& (ego onControl:) $0008)) (NotClose))
				(else (self changeState: 12))
			)
		)
	)
)
