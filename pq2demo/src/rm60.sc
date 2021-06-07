;;; Sierra Script 1.0 - (do not remove this comment)
(script# 60)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm60 0
)

(local
	bush1
	bush2
	bush3
	bainsCar
	carDust
)
(instance bains of Actor)

(instance rm60 of Room
	(properties
		picture 60
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(Load VIEW 90)
		(Load VIEW 4)
		(Load VIEW 6)
		(Load VIEW 94)
		(cSound number: 9 priority: 6 loop: -1 play:)
		(Load VIEW 53)
		(Load VIEW 14)
		(Load VIEW 15)
		((= bush1 (Prop new:))
			view: 90
			posn: 74 97
			cel: 0
			ignoreActors:
			setPri: -1
			init:
		)
		((= bush2 (Prop new:))
			view: 90
			posn: 60 131
			cel: 0
			ignoreActors:
			setPri: -1
			init:
		)
		((= bush3 (Prop new:))
			view: 90
			posn: 10 165
			cel: 0
			ignoreActors:
			setPri: -1
			init:
		)
		((= keith (Actor new:))
			view: 53
			posn: 340 90
			setCycle: Walk
			setAvoider: Avoider
			init:
			setMotion: Follow ego 60
		)
		(ego
			view: 6
			posn: 320 100
			setMotion: MoveTo 200 100
			init:
		)
	)
	
	(method (doit)
		(super doit:)
		(if (and (< (ego x?) 220) (== script 0))
			(self setScript: bainsScript)
		)
	)
	
	(method (dispose)
		(bainsScript dispose:)
		(super dispose:)
	)
)

(instance bainsScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bains stopUpd:)
				(self cue:)
			)
			(1
				(bains
					view: 14
					posn: -10 140
					setStep: 12 2
					setCycle: Walk
					init:
					setMotion: MoveTo 82 140 self
				)
			)
			(2
				(ego view: 4 loop: 5 setCycle: EndLoop)
				(bains
					view: 15
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(3
				(ego view: 4 loop: 5 setCycle: EndLoop)
				(bains
					view: 15
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(4
				(ego view: 4 loop: 5 setCycle: EndLoop)
				(bains
					view: 15
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(5
				(bains
					view: 14
					loop: 1
					cel: 2
					setCycle: Walk
					setMotion: MoveTo -25 (bains y?)
				)
				(bush2 setCycle: EndLoop self startUpd:)
			)
			(6
				(bush1 addToPic:)
				(bush2 addToPic:)
				(bush3 addToPic:)
				(= seconds 5)
			)
			(7
				((= bainsCar (Actor new:))
					view: 94
					setStep: 18 10
					setLoop: 1
					setCel: 0
					posn: -66 160
					init:
					illegalBits: 0
					ignoreActors:
					stopUpd:
				)
				((= carDust (Prop new:))
					view: 94
					loop: 2
					cel: 0
					init:
					ignoreActors:
					stopUpd:
				)
				(self cue:)
			)
			(8
				(keith setMotion: Follow bainsCar 500)
				(ego view: 6 setMotion: Follow bainsCar 500)
				(bainsCar
					setMotion: MoveTo
						(ego x?)
						(if (> (ego y?) 140) (ego y?) else 140)
						self
					startUpd:
				)
				(carDust setCycle: Forward startUpd:)
			)
			(9
				(bainsCar
					setMotion: MoveTo
						430
						(if (> (ego y?) 120) (+ (ego y?) 35) else 140)
						self
				)
			)
			(10
				(Print 60 0 #dispose)
				(keith setMotion: MoveTo 187 118 self)
			)
			(11
				(cls)
				(curRoom newRoom: 25)
			)
		)
	)
)
