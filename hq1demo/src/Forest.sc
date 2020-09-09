;;; Sierra Script 1.0 - (do not remove this comment)
(script# FOREST)
(include game.sh)
(use Main)
(use Intrface)
(use Chase)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	forest 0
)

(instance forest of Room
	(properties
		picture rGenericForest
		style DISSOLVE
		horizon 90
	)
	
	(method (init)
		(HandsOff)
		(Load SOUND sForest)
		(Load VIEW vCheetaur)
		(super init:)
		(ego posn: 318 140 init:)
		(music number: sForest play:)
		(self setScript: mosey)
	)
	
	(method (dispose)
		(DisposeScript CHASE)
		(super dispose:)
	)
)

(instance cheetaur of Actor
	(properties
		view vCheetaur
	)
)

(instance mosey of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 240 140 self)
			)
			(1
				(Print 10 0
					#at -1 20
					#mode teJustCenter
					#dispose
				)
				(ego setMotion: MoveTo 140 140 self)
			)
			(2
				(ego view: vEgoStanding loop: loopS)
				(= cycles 10)
			)
			(3
				(ego loop: loopE)
				(= cycles 10)
			)
			(4
				(ego loop: loopN)
				(= cycles 10)
			)
			(5
				(cls)
				(cheetaur
					posn: -20 140
					init:
					xStep: 6
					yStep: 3
					ignoreActors: TRUE
					setCycle: Forward
					setMotion: Chase ego 35 self
				)
				(ego view: vEgo setMotion: MoveTo 310 140)
				(= cycles 20)
			)
			(6
				(Print 10 1
					#at -1 20
					#width 210
					#dispose
				)
			)
			(7
				(cls)
				(cheetaur setMotion: 0)
				(curRoom newRoom: ARENA)
			)
		)
	)
)
