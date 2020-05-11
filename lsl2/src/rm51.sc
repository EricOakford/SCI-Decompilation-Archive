;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51)
(include game.sh)
(use Main)
(use AirplaneActor)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm51 0
)

(local
	local0
	followingHenchwoman
	henchwomanBeckons
	barberPole
	airplane
	henchwoman
	talkedToHenchwoman
)
(instance rm51 of Room
	(properties
		picture 51
		horizon 1
		north 151
	)
	
	(method (init)
		(Load VIEW 506)
		(Load VIEW 230)
		(Load VIEW 511)
		(super init:)
		((View new:)
			view: 506
			cel: 0
			posn: 59 156
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 506
			cel: 0
			posn: 19 182
			setPri: 11
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 506
			cel: 1
			posn: 152 74
			setPri: 4
			ignoreActors:
			addToPic:
		)
		((= barberPole (Prop new:))
			view: 230
			setPri: 4
			setCycle: Forward
			posn: 236 74
			isExtra: 1
			init:
		)
		((= airplane (Airplane new:))
			view: 511
			setPri: 1
			startX: -20
			startY: 18
			endX: 111
			endY: 11
			init:
		)
		((View new:)
			view: 506
			cel: 2
			posn: 266 103
			setPri: 5
			ignoreActors:
			addToPic:
		)
		(if
		(or (!= 1 (Random 1 3)) (!= currentEgoView 100))
			((View new:)
				view: 506
				cel: 2
				posn: 240 103
				setPri: 5
				ignoreActors:
				addToPic:
			)
		else
			(self setRegions: HENCHWOMAN)
			(= henchwomanIsHere TRUE)
			(= henchView 523)
			(Load VIEW henchView)
			(Load VIEW 524)
			((= henchwoman (Actor new:))
				view: 524
				ignoreActors:
				illegalBits: 0
				setLoop: 0
				posn: 240 103
				setPri: 5
				init:
				cycleSpeed: 2
				setCycle: Forward
				setScript: henchScript
			)
			(NotifyScript HENCHWOMAN 1)
		)
		(if (or (== prevRoomNum 151) (== prevRoomNum 0))
			(ego posn: 210 100)
		)
		(NormalEgo)
		(ego init:)
		(self setRegions: 500 setScript: rm51Script)
	)
)

(instance rm51Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0002) (curRoom newRoom: 151))
		(if (== 2 (ego edgeHit?))
			(if (== followingHenchwoman 0)
				(curRoom newRoom: 52)
			else
				(Print 51 0 #at 15 -1 #width 280)
				(= currentStatus egoCaptured)
				(curRoom newRoom: 95)
			)
		)
		(if (and henchwomanIsHere henchwomanBeckons (> (ego x?) 300))
			(= henchwomanBeckons FALSE)
			(= followingHenchwoman TRUE)
			(curRoom east: 95)
			(Print 51 1)
			(HandsOff)
			(ego setMotion: MoveTo 333 (ego y?) self)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/barstool,bimbo')
				(if
				(and henchwomanIsHere (< (henchScript state?) 7))
					(Print 51 2)
				else
					(Print 51 3)
				)
			)
			(if (Said '/art') (Print 51 4))
			(if (Said '[/building,airport]')
				(Print 51 5)
				(if henchwomanIsHere (Print 51 6))
			)
		)
		(if
			(and
				henchwomanIsHere
				(< (henchScript state?) 7)
				(not talkedToHenchwoman)
				(or
					(Said 'copulate')
					(Said 'call/bimbo')
					(Said 'ok')
					(Said 'bath[/down,barstool]')
					(Said 'embrace')
				)
			)
			(= talkedToHenchwoman TRUE)
			(Print 51 7)
			(Print 51 8)
			(henchScript changeState: 7)
		)
		(if (Said 'bath[/down,barstool]') (Print 51 9))
	)
)

(instance henchScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 10)))
			(1
				(henchwoman
					cycleSpeed: 1
					setLoop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(henchwoman cycleSpeed: 0 setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(3
				(henchwoman setLoop: 1 setCel: 0)
				(Print 51 10 #draw)
				(Print 51 11 #at -1 152)
				(= seconds 5)
			)
			(4
				(henchwoman setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(5 (Print 51 12) (= seconds 3))
			(6
				(Print 51 13)
				(henchwoman setLoop: 0 cel: 0 setCycle: Forward cycleSpeed: 2)
				(= seconds (Random 10 40))
				(= state 0)
			)
			(7
				(= cycles (= seconds 0))
				((View new:)
					view: 506
					cel: 2
					posn: 240 103
					setPri: 5
					ignoreActors:
					addToPic:
				)
				(henchwoman
					view: henchView
					ignoreActors: 0
					loop: 0
					cycleSpeed: 0
					posn: 237 108
					setStep: 3 2
					setLoop: -1
					setAvoider: (Avoider new:)
					setCycle: Walk
					setMotion: MoveTo 305 118 self
				)
			)
			(8
				(Print 51 14)
				(henchwoman setMotion: MoveTo 333 118 self)
				(= henchwomanBeckons TRUE)
			)
			(9 (= seconds 10))
			(10
				(henchwoman dispose:)
				(= henchView 0)
				(= henchwomanIsHere FALSE)
				(= henchwomanBeckons FALSE)
			)
		)
	)
)
