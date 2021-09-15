;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm64 0
)

(local
	numClouds
	aPlane
)
(instance rm64 of Room
	(properties
		picture 64
		horizon 5
	)
	
	(method (init &tmp i temp1)
		(Load VIEW 162)
		(Load VIEW 511)
		(Load VIEW 620)
		(super init:)
		((= aPlane (Actor new:))
			view: 511
			setLoop: 5
			setCel: 0
			posn: 155 7
			illegalBits: 0
			setStep: 3
			setMotion: MoveTo -20 8
			init:
		)
		(= numClouds (Random 2 6))
		(for ((= i 0)) (< i numClouds) ((++ i))
			((View new:)
				view: 620
				cel: (Random 0 10)
				ignoreActors:
				posn: (Random -10 330) (Random 5 188)
				addToPic:
			)
		)
		(ego
			view: 162
			posn: 160 8
			setCycle: Forward
			loop: 0
			setStep: -1 3
			init:
		)
		(= currentStatus egoFALLING)
		(self setScript: rm64Script)
		(User canInput: TRUE canControl: FALSE)
	)
)

(instance rm64Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 144 208 self)
			)
			(1
				(curRoom newRoom: 65)
			)
			(2
				(ego
					loop: 1
					setStep: -1 1
					cycleSpeed: 4
					setMotion: MoveTo 144 208 self
				)
				(= currentStatus egoLAUNCHPARACHUTE)
				(Print 64 8 #draw)
			)
			(3
				(curRoom newRoom: 65)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look')
			(if (== currentStatus egoFALLING)
				(Print 64 0)
			else
				(Print 64 1)
			)
		)
		(if (Said 'apply,open,jerk/cord,parachute')
			(if (!= currentStatus egoFALLING)
				(Print 64 2)
			else
				(Print 64 3)
				(if (== wearingParachute TRUE)
					(self changeState: 2)
				else
					(Print 64 4)
				)
			)
		)
		(if (Said '(conceal<on),wear,afix,buckle,afix/parachute')
			(cond 
				((== wearingParachute TRUE)
					(Print 64 5)
				)
				((ego has: iParachute)
					(Print 64 6)
				)
				(else
					(Print 64 7)
				)
			)
		)
	)
)
