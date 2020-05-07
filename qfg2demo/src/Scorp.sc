;;; Sierra Script 1.0 - (do not remove this comment)
(script# SCORP) ;8
(include game.sh)
(use Main)
(use Full)
(use Intrface)
(use ForCount)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoCombat 0
)

(instance demoCombat of Room
	(properties
		picture 681
		style IRISIN
	)
	
	(method (init)
		(LoadMany SCRIPT SCORP FULL FORCOUNT)
		(Load PICTURE 681)
		(LoadMany VIEW 0 4 673 670 675)
		(super init:)
		(ego
			view: 673
			loop: 0
			cel: 0
			posn: 151 113
			setPri: -1
			init:
		)
		(scorpion init:)
		(leftPincer init:)
		(rightPincer init:)
		(globalSound number: 54 loop: -1 playBed:)
		(self setScript: rmScript)
	)
)

(instance scorpion of Prop
	(properties
		x 173
		y 86
		view 670
	)
)

(instance leftPincer of Prop
	(properties
		view 670
		loop 1
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self posn: (+ (scorpion x?) 13) (+ (scorpion y?) 3))
	)
)

(instance rightPincer of Prop
	(properties
		view 670
		loop 2
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self posn: (- (scorpion x?) 9) (+ (scorpion y?) 3))
	)
)

(instance rmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print SCORP 0 #at -1 12 #dispose)
				(= cycles 10)
			)
			(1
				(ego loop: 1 cycleSpeed: 1 setCycle: Full)
				(rightPincer cycleSpeed: 1 setCycle: Full self)
				(= cycles 15)
			)
			(2
				(ego loop: 3 setCycle: Full)
				(rightPincer setCycle: Full self)
			)
			(3
				(leftPincer cycleSpeed: 1 setCycle: Full self)
			)
			(4
				(ego loop: 2 setCycle: Full self)
			)
			(5
				(rightPincer setCycle: Full self)
			)
			(6
				(ego loop: 1 setCycle: Full self)
			)
			(7
				(rightPincer setCycle: EndLoop)
				(leftPincer setCycle: EndLoop)
				(ego setCycle: Full)
				(= cycles 10)
			)
			(8
				(ego
					ignoreActors:
					posn: (leftPincer x?) (leftPincer y?)
					view: 675
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
				(leftPincer dispose:)
				(rightPincer dispose:)
			)
			(9
				(ego loop: 1 cel: 0 setCycle: ForwardCounter 4 self)
			)
			(10
				(ego setCycle: Forward)
				(scorpion cycleSpeed: 1 setCycle: EndLoop self)
			)
			(11
				(ego loop: 2 cel: 0 setCycle: CycleTo 1 1)
				(= cycles 15)
			)
			(12
				(ego setCycle: EndLoop)
				(scorpion setCycle: BegLoop)
				(= seconds 4)
			)
			(13
				(cls)
				(curRoom newRoom: ROPE)
			)
		)
	)
)
