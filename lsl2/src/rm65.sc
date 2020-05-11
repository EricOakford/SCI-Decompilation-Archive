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
	local1
)
(instance rm65 of Room
	(properties
		picture 65
		horizon 5
	)
	
	(method (init &tmp temp0 temp1)
		(Load VIEW 607)
		(Load VIEW 620)
		(super init:)
		((View new:)
			view: 607
			loop: 1
			cel: 0
			posn: 0 179
			setPri: 15
			ignoreActors:
			addToPic:
		)
		(= local1 (Random 2 6))
		(= temp0 0)
		(while (< temp0 local1)
			((View new:)
				view: 620
				cel: (Random 0 10)
				ignoreActors:
				posn: (Random -10 330) (Random 5 120)
				addToPic:
			)
			(++ temp0)
		)
		(self setScript: rm65Script)
		(ego ignoreHorizon: posn: 144 -21)
		(if (== currentStatus egoFalling)
			(ego loop: 0 setStep: 3 4 init:)
			(rm65Script changeState: 0)
		else
			(= currentStatus egoLaunchedParachute)
			(ego loop: 1 setStep: 1 1 cycleSpeed: 4 init:)
			(rm65Script changeState: 6)
		)
		(User canInput: TRUE canControl: FALSE)
	)
)

(instance rm65Script of Script
	(properties)
	
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
			(2 (ego hide:) (= seconds 3))
			(3
				(Print 65 12)
				(if (Random 0 1) (Print 65 13) else (Print 65 14))
				(= currentStatus egoDead)
			)
			(4
				(= currentStatus egoLaunchedParachute)
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
				(= currentStatus egoLaunchedParachute)
				(User canInput: FALSE)
				(ego
					loop: 1
					setStep: 1 1
					setMotion: MoveTo 17 178 self
					setPri: 2
				)
			)
			(7 (ego hide:) (= seconds 3))
			(8 (curRoom newRoom: 70))
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/island')
				(if (== currentStatus egoFalling)
					(Print 65 0)
				else
					(Print 65 1)
				)
			)
			(if (Said '/fluid,lagoon,lagoon') (Print 65 2))
			(if (Said '/cloud') (Print 65 3))
			(if (Said '[/airport,around,cloud]')
				(if (== currentStatus egoFalling)
					(Print 65 4)
				else
					(Print 65 5)
				)
			)
		)
		(if (Said 'apply,open,jerk/cord,parachute')
			(if (!= currentStatus egoFalling)
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
		(if
		(Said '(conceal<on),wear,afix,buckle,afix/parachute')
			(cond 
				((== wearingParachute TRUE) (Print 65 9))
				((ego has: iParachute) (Print 65 10))
				(else (Print 65 11))
			)
		)
	)
)
