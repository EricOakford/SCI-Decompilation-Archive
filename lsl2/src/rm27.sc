;;; Sierra Script 1.0 - (do not remove this comment)
(script# 27)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm27 0
)

(local
	confettiFalling
	numConfetti
)
(instance rm27 of Room
	(properties
		picture 27
		east 28
	)
	
	(method (init)
		(Load VIEW 238)
		(super init:)
		(NormalEgo 0)
		(ego posn: 8 134 setPri: 13 init:)
		(self setScript: rm27Script)
	)
)

(instance rm27Script of Script
	(properties)
	
	(method (doit &tmp i)
		(super doit:)
		(if (and (not confettiFalling) (> (ego x?) 160))
			(Print 27 0)
			(= confettiFalling TRUE)
			(cond 
				((> machineSpeed 80) (= numConfetti 5))
				((> machineSpeed 60) (= numConfetti 4))
				((> machineSpeed 40) (= numConfetti 3))
				((> machineSpeed 20) (= numConfetti 2))
				(else (= numConfetti 1))
			)
			(= numConfetti 4)
			(= i 0)
			(while (< i numConfetti)
				((Actor new:)
					view: 238
					setPri: 14
					illegalBits: 0
					setStep: 1 5
					ignoreActors:
					ignoreHorizon:
					setCycle: Forward
					init:
					setScript: (confettiScript new:)
				)
				(++ i)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (and confettiFalling (Said '/confetti')) (Print 27 1))
			(if (Said '/hull,craft,boat,up') (Print 27 2))
			(if (Said '/cup,hole') (Print 27 3))
			(if (Said '[/airport,carpet]')
				(Print 27 4)
				(if confettiFalling (Print 27 5))
			)
		)
	)
)

(instance confettiScript of Script
	(properties)
	
	(method (changeState newState &tmp theX theLoop theCel)
		(switch (= state newState)
			(0
				(= theX (Random 2 300))
				(= theLoop (Random 0 7))
				(= theCel (Random 0 1))
				(client
					setPri: 14
					setLoop: theLoop
					cel: theCel
					setStep: -1 (Random 5 12)
					posn: theX (- 0 (CelHigh 238 theLoop theCel))
					show:
					setMotion: MoveTo theX 180 self
				)
			)
			(1
				(client hide:)
				(self changeState: 0)
			)
		)
	)
)
