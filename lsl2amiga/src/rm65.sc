;;; Sierra Script 1.0 - (do not remove this comment)
(script# 65)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm65 0
)

(local
	local0
	numClouds
)
(instance rm65 of Room
	(properties
		picture 65
		horizon 5
	)
	
	(method (init &tmp i temp1)
		(Load VIEW 607)
		(Load VIEW 620)
		(super init:)
		(addToPics add: aIsland doit:)
		(= numClouds (Random 2 6))
		(for ((= i 0)) (< i numClouds) ((++ i))
			(aClouds
				cel: (Random 0 10)
				posn: (Random -10 330) (Random 5 120)
				addToPic:
			)
		)
		(ego init: ignoreHorizon: posn: 144 -21)
		(self setScript: rm65Script)
		(if (== currentStatus egoFALLING)
			(ego loop: 0 setStep: 3 4)
			(rm65Script changeState: 0)
		else
			(= currentStatus egoLAUNCHPARACHUTE)
			(ego loop: 1 setStep: 1 1 cycleSpeed: 4)
			(rm65Script changeState: 6)
		)
		(User canInput: TRUE canControl: FALSE)
	)
)

(instance rm65Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 144 177 self)
			)
			(1
				(ego
					view: 607
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2
				(ego hide:)
				(= seconds 3)
			)
			(3
				(Print 65 12)
				(if (Random 0 1)
					(Print 65 13)
				else
					(Print 65 14)
				)
				(= currentStatus egoDYING)
			)
			(4
				(= currentStatus egoLAUNCHPARACHUTE)
				(= seconds (= cycles 0))
				(User canInput: FALSE)
				(Print 65 15)
				(ego
					loop: 1
					setStep: 1 1
					cycleSpeed: 4
					setMotion: MoveTo (- (ego x?) 20) 147 self
				)
			)
			(5
				(Print 65 16)
				(ego setMotion: MoveTo (- (ego x?) 10) 177 self)
				(= state 0)
			)
			(6
				(= seconds (= cycles 0))
				(= currentStatus egoLAUNCHPARACHUTE)
				(User canInput: FALSE)
				(ego
					loop: 1
					setStep: 1 1
					setMotion: MoveTo 17 178 self
					setPri: 2
				)
			)
			(7
				(ego hide:)
				(= seconds 3)
			)
			(8
				(curRoom newRoom: 70)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/island,mountain')	;EO: fixed said spec
				(if (== currentStatus egoFALLING)
					(Print 65 0)
				else
					(Print 65 1)
				)
			)
			(if (Said '/fluid,lagoon,lagoon')
				(Print 65 2)
			)
			(if (Said '/cloud')
				(Print 65 3)
			)
			(if (Said '[/airport,around,cloud]')
				(if (== currentStatus egoFALLING)
					(Print 65 4)
				else
					(Print 65 5)
				)
			)
		)
		(if (Said 'apply,open,jerk/cord,parachute')
			(if (!= currentStatus egoFALLING)
				(Print 65 6)
			else
				(Print 65 7)
				(if (== wearingParachute TRUE)
					(rm65Script changeState: 4)
				else
					(Print 65 8)
				)
			)
		)
		(if (Said '(conceal<on),wear,afix,buckle,afix/parachute')
			(cond 
				((== wearingParachute TRUE)
					(Print 65 9)
				)
				((ego has: iParachute)
					(Print 65 10)
				)
				(else
					(Print 65 11)
				)
			)
		)
	)
)

(instance aIsland of PicView
	(properties
		y 179
		view 607
		loop 1
		priority 15
		signal ignrAct
	)
)

(instance aClouds of View
	(properties
		view 620
		signal ignrAct
	)
)
