;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11)
(include game.sh)
(use Main)
(use Door)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm11 0
)

(local
	aDoor
	aShowBizType
	showBizVisible
)
(instance rm11 of Room
	(properties
		picture 11
		horizon 2
		east 12
		south 15
		west 15
	)
	
	(method (init)
		(Load VIEW 200)
		(Load VIEW 204)
		(Load VIEW 205)
		(super init:)
		((View new:)
			view: 200
			loop: 1
			cel: 0
			posn: 160 30
			setPri: 1
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 200
			loop: 1
			cel: 1
			posn: 283 146
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((= aShowBizType (Actor new:))
			view: 204
			setLoop: 0
			posn: -20 132
			illegalBits: 0
			setPri: 3
			setCycle: Walk
			ignoreActors:
			init:
		)
		(cond 
			((== prevRoomNum 101)
				(ego posn: 161 60)
			)
			((== prevRoomNum 12)
				(ego posn: 316 168)
			)
			(else
				(ego posn: 21 187)
			)
		)
		(NormalEgo)
		(ego init:)
		((= aDoor (AutoDoor new:))
			view: 200
			setLoop: 0
			posn: 161 62
			setPri: 2
			entranceTo: 101
			msgLook: {Inside, a woman sits behind a desk.}
			init:
		)
		(self setRegions: CITY setScript: rm11Script)
	)
)

(instance rm11Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 3 13))
			)
			(1
				(= showBizVisible TRUE)
				(aShowBizType
					view: 204
					setLoop: 0
					setMotion: MoveTo 340 133 self
				)
			)
			(2
				(= showBizVisible FALSE)
				(= seconds (Random 1 11))
			)
			(3
				(= showBizVisible TRUE)
				(aShowBizType
					view: 205
					setLoop: 1
					setMotion: MoveTo -20 133 self
				)
			)
			(4
				(self changeState: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/sign')
				(Print 11 0)
			)
			(if (Said '/antenna,bowl')
				(Print 11 1)
			)
			(if (Said '/man,bimbo,children')
				(if showBizVisible
					(Print 11 2)
				else
					(Print 11 3)
				)
			)
			(if (Said '[/krod,building,krod,krod,(krod<krod),airport]')
				(Print 11 4)
			)
		)
	)
)
