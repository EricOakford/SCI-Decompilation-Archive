;;; Sierra Script 1.0 - (do not remove this comment)
(script# 24)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm24 0
)

(instance rm24 of Room
	(properties
		picture 24
		horizon 5
		north 20
		east 25
		west 23
	)
	
	(method (init)
		(Load VIEW 255)
		(super init:)
		(aPeople
			setLoop: 0
			setStep: 1 1
			setCycle: Forward
			illegalBits: 0
			ignoreHorizon:
			ignoreActors:
			init:
			setScript: detailScript
		)
		(cond 
			((== prevRoomNum 0)
				(ego posn: 155 179)
			)
			((== prevRoomNum 20)
				(ego posn: 99 110)
			)
		)
		(NormalEgo)
		(ego init:)
		(self setRegions: CITY setScript: rm24Script)
	)
)

(instance rm24Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said '/film')
			(Print 24 0)
			(Print 24 1 #at -1 130)
		)
		(if (Said 'look>')
			(if (Said '[/building,unreversal,krod,airport]')
				(Print 24 2)
			)
			(if (Said '/children,man,bimbo')
				(Print 24 3)
			)
		)
	)
)

(instance detailScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 12)
			)
			(1
				(aPeople
					setPri: -1
					setLoop: 0
					setMotion: MoveTo 190 64 self
				)
			)
			(2
				(aPeople hide:)
				(= seconds (Random 2 9))
			)
			(3
				(aPeople
					setLoop: 1
					posn: -5 63
					show:
					setMotion: MoveTo 190 64 self
				)
			)
			(4
				(aPeople hide:)
				(= seconds (Random 2 9))
			)
			(5
				(aPeople
					setLoop: 2
					posn: -5 63
					show:
					setMotion: MoveTo 210 64 self
				)
			)
			(6
				(aPeople hide:)
				(= seconds (Random 2 9))
			)
			(7
				(aPeople setLoop: 3 posn: 299 103 setPri: 11 show:)
				(= seconds (Random 5 15))
			)
			(8
				(aPeople hide:)
				(self changeState: 0)
			)
		)
	)
)

(instance aPeople of Actor
	(properties
		y 63
		x -5
		view 255
	)
)
