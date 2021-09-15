;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51)
(include game.sh)
(use Main)
(use AirplaneActor)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm51 0
)

(local
	local0
	joinedHenchwoman
	henchwomanBeckons
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
		(addToPics add: aChair1 aChair2 aSign aNorthChair1 doit:)
		(aBarberPole
			setCycle: Forward
			isExtra: TRUE
			init:
		)
		(aPlane
			setPri: 1
			startX: -20
			startY: 18
			endX: 111
			endY: 11
			init:
		)
		(if (or (!= 1 (Random 1 3)) (!= currentEgoView vEgo))
			(addToPics add: aNorthChair2 doit:)
		else
			(self setRegions: HENCHWOMAN)
			(= henchwomanIsHere TRUE)
			(= henchView 523)
			(Load VIEW henchView)
			(Load VIEW 524)
			(aHench
				setPri: 5
				illegalBits: 0
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
		(self setRegions: AIRPORT setScript: rm51Script)
	)
)

(instance rm51Script of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cBLUE)
			(curRoom newRoom: 151)
		)
		(if (== EAST (ego edgeHit?))
			(if (== joinedHenchwoman FALSE)
				(curRoom newRoom: 52)
			else
				(Print 51 0 #at 15 -1 #width 280)
				(= currentStatus egoCAPTURED)
				(curRoom newRoom: 95)
			)
		)
		(if (and henchwomanIsHere henchwomanBeckons (> (ego x?) 300))
			(= henchwomanBeckons FALSE)
			(= joinedHenchwoman TRUE)
			(curRoom east: 95)
			(Print 51 1)
			(HandsOff)
			(ego setMotion: MoveTo 333 (ego y?) self)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/barstool,bimbo')
				(if (and henchwomanIsHere (< (henchScript state?) 7))
					(Print 51 2)
				else
					(Print 51 3)
				)
			)
			(if (Said '/art')
				(Print 51 4)
			)
			(if (Said '[/building,airport]')
				(Print 51 5)
				(if henchwomanIsHere
					(Print 51 6)
				)
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
		(if (Said 'bath[/down,barstool]')
			(Print 51 9)
		)
	)
)

(instance henchScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 5 10))
			)
			(1
				(aHench
					cycleSpeed: 1
					setLoop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(aHench cycleSpeed: 0 setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(3
				(aHench setLoop: 1 setCel: 0)
				(Print 51 10 #draw)
				(Print 51 11 #at -1 130)
				(= seconds 5)
			)
			(4
				(aHench setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(5
				(Print 51 12)
				(= seconds 3)
			)
			(6
				(Print 51 13)
				(aHench setLoop: 0 cel: 0 setCycle: Forward cycleSpeed: 2)
				(= seconds (Random 10 40))
				(= state 0)
			)
			(7
				(= cycles (= seconds 0))
				(addToPics add: aNewChair doit:)
				(aHench
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
				(aHench setMotion: MoveTo 333 118 self)
				(= henchwomanBeckons TRUE)
			)
			(9
				(= seconds 10)
			)
			(10
				(aHench dispose:)
				(= henchView 0)
				(= henchwomanIsHere FALSE)
				(= henchwomanBeckons FALSE)
			)
		)
	)
)

(instance aChair1 of PicView
	(properties
		y 156
		x 59
		view 506
		priority 10
		signal ignrAct
	)
)

(instance aChair2 of PicView
	(properties
		y 182
		x 19
		view 506
		priority 11
		signal ignrAct
	)
)

(instance aSign of PicView
	(properties
		y 74
		x 152
		view 506
		cel 1
		priority 4
		signal ignrAct
	)
)

(instance aNorthChair1 of PicView
	(properties
		y 103
		x 266
		view 506
		cel 2
		priority 5
		signal ignrAct
	)
)

(instance aNorthChair2 of PicView
	(properties
		y 103
		x 240
		view 506
		cel 2
		priority 5
		signal ignrAct
	)
)

(instance aNewChair of PicView
	(properties
		y 103
		x 240
		view 506
		cel 2
		priority 5
		signal ignrAct
	)
)

(instance aBarberPole of Prop
	(properties
		y 74
		x 236
		view 230
		priority 4
	)
)

(instance aPlane of Airplane)

(instance aHench of Actor
	(properties
		y 103
		x 240
		view 524
		signal ignrAct
	)
)
