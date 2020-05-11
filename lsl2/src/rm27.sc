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
	madeItMessage
	confetti
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
	
	(method (doit &tmp numConfetti)
		(super doit:)
		(if (and (not madeItMessage) (> (ego x?) 160))
			(Print 27 0)
			(= madeItMessage TRUE)
			(cond 
				((> howFast 80) (= confetti 5))
				((> howFast 60) (= confetti 4))
				((> howFast 40) (= confetti 3))
				((> howFast 20) (= confetti 2))
				(else (= confetti 1))
			)
			(= confetti 4)
			(= numConfetti 0)
			(while (< numConfetti confetti)
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
				(++ numConfetti)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (and madeItMessage (Said '/confetti')) (Print 27 1))
			(if (Said '/hull,craft,boat,up') (Print 27 2))
			(if (Said '/cup,hole') (Print 27 3))
			(if (Said '[/airport,carpet]')
				(Print 27 4)
				(if madeItMessage (Print 27 5))
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
