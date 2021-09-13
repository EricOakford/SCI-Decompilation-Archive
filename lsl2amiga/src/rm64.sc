;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64)
(include sci.sh)
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
)
(instance rm64 of Rm
	(properties
		picture 64
		horizon 5
	)
	
	(method (init &tmp temp0 temp1)
		(Load rsVIEW 162)
		(Load rsVIEW 511)
		(Load rsVIEW 620)
		(super init:)
		(aPlane
			illegalBits: 0
			setStep: 3
			setMotion: MoveTo -20 8
			init:
		)
		(= numClouds (Random 2 6))
		(= temp0 0)
		(while (< temp0 numClouds)
			(aClouds
				cel: (Random 0 10)
				posn: (Random -10 330) (Random 5 188)
				addToPic:
			)
			(++ temp0)
		)
		(ego
			view: 162
			posn: 160 8
			setCycle: Fwd
			loop: 0
			setStep: -1 3
			init:
		)
		(= currentStatus 12)
		(self setScript: rm64Script)
		(User canInput: 1 canControl: 0)
	)
)

(instance rm64Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 144 208 self)
			)
			(1 (curRoom newRoom: 65))
			(2
				(ego
					loop: 1
					setStep: -1 1
					cycleSpeed: 4
					setMotion: MoveTo 144 208 self
				)
				(= currentStatus 10)
				(Print 64 8 #draw)
			)
			(3 (curRoom newRoom: 65))
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'look')
			(if (== currentStatus 12)
				(Print 64 0)
			else
				(Print 64 1)
			)
		)
		(if (Said 'apply,open,jerk/cord,parachute')
			(if (!= currentStatus 12)
				(Print 64 2)
			else
				(Print 64 3)
				(if (== wearingParachute 1)
					(self changeState: 2)
				else
					(Print 64 4)
				)
			)
		)
		(if
		(Said '(conceal<on),wear,afix,buckle,afix/parachute')
			(cond 
				((== wearingParachute 1) (Print 64 5))
				((ego has: 24) (Print 64 6))
				(else (Print 64 7))
			)
		)
	)
)

(instance aPlane of Act
	(properties
		y 7
		x 155
		view 511
		loop 5
	)
)

(instance aClouds of View
	(properties
		view 620
		signal $4000
	)
)
