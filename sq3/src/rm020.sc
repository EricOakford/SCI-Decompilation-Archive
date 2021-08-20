;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm020 0
)

(instance rm020 of Room
	(properties
		picture 20
	)
	
	(method (init &tmp [temp0 50])
		(HandsOff)
		(= inCartoon 1)
		(Load VIEW 759)
		(= global207 1)
		(super init:)
		(self setScript: rmScript)
	)
	
	(method (doit)
		(if (and (== global220 1) (== script 0))
			(Print 20 0)
			(= inCartoon 0)
			(curRoom newRoom: 17)
		)
		(super doit:)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 20 1 #at -1 130 #width 280)
				(hole init: addToPic:)
				(blowUp play:)
				(if (== shieldActivated 0)
					(blast init: setCycle: CycleTo 1 1 self)
				else
					(blast init: setCycle: EndLoop self)
				)
			)
			(1
				(if (== shieldActivated 0)
					(ship init: setCycle: EndLoop self)
					(blast setCycle: EndLoop)
				else
					(= cycles 2)
				)
			)
			(2
				(if (== shieldActivated shieldOFF)
					(ship setLoop: 4 cel: 0 setMotion: 0 setCycle: EndLoop self)
				else
					(ship
						init:
						setLoop: 2
						setCycle: Forward
						setMotion: MoveTo 340 83 self
					)
				)
			)
			(3
				(blast dispose:)
				(if (!= shieldActivated shieldOFF)
					(Print 20 2 #at -1 130 #width 280)
					(theGame changeScore: 25)
					(= shipLocation shipSPACE)
					(= global206 3)
					(= inCartoon 0)
					(curRoom newRoom: 14)
				else
					(Print 20 3 #at -1 130 #width 280)
					(EgoDead 0 0 4 5)
				)
			)
		)
	)
)

(instance blast of Prop
	(properties
		view 759
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			cel: 0
			cycleSpeed: 1
			setPri: 5
			posn: 155 77
			ignoreActors: TRUE
		)
	)
)

(instance hole of View
	(properties
		view 759
	)
	
	(method (init)
		(super init:)
		(self setLoop: 1 setPri: 4 posn: 170 95 ignoreActors: 1)
	)
)

(instance ship of Actor
	(properties
		view 759
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 3
			cel: 0
			posn: 167 83
			cycleSpeed: 2
			ignoreActors: TRUE
			setStep: 10 1
		)
	)
)

(instance blowUp of Sound
	(properties
		number 33
		priority 1
	)
)
