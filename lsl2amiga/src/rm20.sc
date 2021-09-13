;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm20 0
)

(instance rm20 of Room
	(properties
		picture 20
		horizon 1
		east 21
		south 24
		west 19
	)
	
	(method (init)
		(Load VIEW 251)
		(super init:)
		(aJogger
			setLoop: 0
			setPri: 4
			moveSpeed: 3
			setStep: 1 1
			illegalBits: 0
			ignoreActors:
			init:
			hide:
			setCycle: Walk
		)
		(aPlane
			setLoop: 1
			setPri: 0
			illegalBits: 0
			ignoreActors:
			init:
			hide:
		)
		(if (or (== prevRoomNum 0) (== prevRoomNum 16))
			(ego posn: 309 132)
		)
		(NormalEgo)
		(ego init:)
		(self setRegions: CITY setScript: rm20Script)
	)
)

(instance rm20Script of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl: origin) cBLUE)
			(curRoom newRoom: 16)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 2 14))
			)
			(1
				(aPlane posn: 287 7 show: setMotion: MoveTo -30 8 self)
			)
			(2
				(aPlane hide:)
				(= seconds (Random 2 9))
			)
			(3
				(aJogger
					posn: 61 120
					show:
					setMotion: MoveTo 112 116 self
				)
			)
			(4
				(aJogger setMotion: MoveTo 150 125 self)
			)
			(5
				(aJogger hide:)
				(self changeState: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/airline')
				(Print 20 0)
			)
			(if (Said '/children,man,bimbo')
				(if (or (== state 3) (== state 4))
					(if (not lookedAtJogger)
						(= lookedAtJogger TRUE)
						(theGame changeScore: 1)
					)
					(Print 20 1)
					(Print 20 2 #at -1 130)
				else
					(Print 20 3)
				)
			)
			(if (Said '/hotel,building')
				(Print 20 4)
			)
			(if (Said '[/angeles,airport]')
				(Print 20 5)
			)
		)
	)
)

(instance aJogger of Actor
	(properties
		view 251
	)
)

(instance aPlane of Actor
	(properties
		y 7
		x 274
		view 251
	)
)
