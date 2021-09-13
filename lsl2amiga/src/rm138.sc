;;; Sierra Script 1.0 - (do not remove this comment)
(script# 138)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm138 0
)

(local
	currentDay
)
(instance rm138 of Room
	(properties
		picture 138
		horizon 5
	)
	
	(method (init)
		(Load VIEW 120)
		(Load VIEW 326)
		(Load VIEW 121)
		(Load VIEW 122)
		(Load VIEW 123)
		(Load VIEW 124)
		(super init:)
		(aBigEgo
			setPri: 3
			stopUpd:
			init:
		)
		(if (== woreWigAtSea TRUE)
			(aWig
				setPri: 5
				stopUpd:
				init:
			)
		)
		(aCalendar
			setLoop: 1
			setPri: 7
			posn: 220 39
			setCycle: Forward
			init:
			hide:
		)
		(aDate
			setLoop: 2
			setPri: 9
			posn: 228 71
			init:
			hide:
		)
		(aPage
			setLoop: 3
			setPri: 11
			posn: 220 39
			ignoreHorizon:
			cycleSpeed: 0
			setStep: 10 10
			init:
			hide:
		)
		(aShip
			setLoop: 0
			setPri: 3
			setStep: 1 1
			moveSpeed: 8
			cycleSpeed: 8
			ignoreHorizon:
			init:
		)
		(aWave
			setLoop: 4
			setPri: 11
			cycleSpeed: 2
			setCycle: Forward
			isExtra: 1
			init:
		)
		(aFlame
			setLoop: 2
			setPri: 5
			setCycle: Forward
			init:
			hide:
		)
		(HandsOff)
		(= currentStatus egoATSEA)
		(self setScript: rm138Script)
	)
)

(instance rm138Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aShip setCycle: EndLoop setMotion: MoveTo 233 50 self)
			)
			(1
				(aShip
					setPri: 1
					setCel:
					moveSpeed: 13
					setMotion: MoveTo 233 65 self
				)
				(Print 138 0)
			)
			(2
				(Print 138 1)
				(aShip dispose:)
				(= seconds 3)
			)
			(3
				(aCalendar show:)
				(aDate show:)
				(= seconds 3)
			)
			(4
				(if (>= currentDay 9)
					(Print 138 2)
					(curRoom newRoom: 42)
				else
					(++ currentDay)
					(calendarScript changeState: 1)
				)
			)
			(5
				(= seconds 3)
			)
			(6
				(cond 
					((== currentDay 3)
						(Print 138 3 #at -1 20)
						(if (or (== sunscreenState sunscreenAPPLIED) (== sunscreenState sunscreenINLIFEBOAT))
							(Print 138 4 #at 15 -1 #width 280)
							(theGame changeScore: 5)
							(= seconds 1)
						else
							(self changeState: 8)
						)
					)
					((== currentDay 4)
						(Print 138 5 #at -1 20)
						(if woreWigAtSea
							(Print 138 6 #at -1 20)
							(theGame changeScore: 5)
							(= seconds 1)
						else
							(self changeState: 11)
						)
					)
					((== currentDay 5)
						(Print 138 7 #at 15 -1 #width 280)
						(if (not (ego has: iGrotesqueGulp))
							(self changeState: 18)
						else
							(Print 138 8 #at 15 -1 #width 280)
							(theGame changeScore: 5)
							(ego put: iGrotesqueGulp -1)
							(= seconds 1)
						)
					)
					((== currentDay 6)
						(Print 138 9 #at 15 -1 #width 280)
						(cond 
							((ego has: iSpinachDip)
								(Print 138 10 #at -1 20)
								(ego put: iSpinachDip -1)
								(self changeState: 15)
							)
							((ego has: iSewingKit)
								(Print 138 11 #at 15 -1 #width 280)
								(Print 138 12)
								(theGame changeScore: 10)
								(= seconds 1)
							)
							((ego has: iFruit)
								(Print 138 13 #at -1 20)
								(ego put: iFruit -1)
								(theGame changeScore: 5)
								(= seconds 1)
							)
							(else
								(self changeState: 21)
							)
						)
					)
					(else
						(= seconds 1)
					)
				)
			)
			(7
				(= state 3)
				(= seconds 1)
			)
			(8
				(aBigEgo
					view: 123
					cel: 0
					cycleSpeed: 5
					setCycle: EndLoop self
				)
			)
			(9
				(= seconds 3)
			)
			(10
				(Print 138 14 #at -1 20)
				(if (== sunscreenState sunscreenAFTERSWIM)
					(Print 138 15 #at -1 130)
				)
				(= state 24)
				(= seconds 3)
			)
			(11
				(aFlame show:)
				(= seconds 5)
			)
			(12
				(aBigEgo
					view: 123
					cel: 0
					cycleSpeed: 5
					setCycle: EndLoop self
				)
			)
			(13
				(= seconds 3)
			)
			(14
				(Print 138 16 #at -1 20)
				(= state 24)
				(= seconds 3)
			)
			(15
				(aBigEgo
					view: 121
					cel: 0
					cycleSpeed: 5
					setCycle: EndLoop self
				)
			)
			(16
				(= seconds 3)
			)
			(17
				(Print 138 17 #at -1 20)
				(= state 24)
				(= seconds 3)
			)
			(18
				(aBigEgo
					view: 122
					cel: 0
					cycleSpeed: 5
					setCycle: EndLoop self
				)
			)
			(19
				(= seconds 3)
			)
			(20
				(Print 138 18 #at -1 20)
				(= state 24)
				(= seconds 3)
			)
			(21
				(aBigEgo
					view: 124
					cel: 0
					cycleSpeed: 5
					setCycle: EndLoop self
				)
			)
			(22
				(= seconds 3)
			)
			(23
				(Print 138 19 #at -1 20)
				(= state 24)
				(= seconds 3)
			)
			(24 (= seconds 3))
			(25
				(calendarScript changeState: 6)
				(= seconds 3)
			)
			(26
				(Print 138 20 #at -1 20)
				(= currentStatus egoDYING)
			)
		)
	)
)

(instance calendarScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(aPage posn: 220 39 cel: 0 setCycle: CycleTo 2 1 self show:)
			)
			(2
				(aDate setCel: (+ 1 (aDate cel?)) forceUpd:)
				(aPage setCycle: EndLoop self)
			)
			(3
				(aPage setMotion: MoveTo 360 -31 self)
			)
			(4
				(aPage hide:)
				(rm138Script cue:)
			)
			(6
				(aDate dispose:)
				(aPage dispose:)
				(aCalendar dispose:)
			)
		)
	)
)

(instance aWig of View
	(properties
		y 67
		x 116
		view 120
		loop 1
		signal ignrAct
	)
)

(instance aBigEgo of Prop
	(properties
		y 67
		x 116
		view 120
	)
)

(instance aCalendar of Prop
	(properties
		view 326
	)
)

(instance aDate of Prop
	(properties
		view 326
	)
)

(instance aPage of Actor
	(properties
		view 326
		signal ignrAct
	)
)

(instance aShip of Actor
	(properties
		y 59
		x 233
		view 326
		signal ignrAct
	)
)

(instance aWave of Prop
	(properties
		y 155
		x 115
		view 326
	)
)

(instance aFlame of Prop
	(properties
		y 66
		x 115
		view 120
	)
)
