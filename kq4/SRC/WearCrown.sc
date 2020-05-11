;;; Sierra Script 1.0 - (do not remove this comment)
(script# 301)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	wearCrown 0
)

(local
	preFrogView
)
(instance poofSound of Sound
	(properties)
)

(instance wearCrown of Script
	(properties)
	
	(method (init)
		(= isHandsOff TRUE)
		(Load VIEW 370)
		(Load VIEW 687)
		(super init:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== noWearCrown TRUE)
					(Print 301 0)
					(ego script: oldEgoScript)
					(DisposeScript 301)
					(return)
				else
					(HandsOff)
					(self changeState: 1)
				)
			)
			(1
				(= preFrogView (ego viewer?))
				(sounds eachElementDo: #stop 0)
				(poofSound number: 59 play:)
				(User canControl: 0 canInput: 0)
				(= oldEgoView (ego view?))
				(= gNewProp (Prop new:))
				(gNewProp
					x: (ego x?)
					y: (ego y?)
					view: 687
					loop: 0
					ignoreActors:
					setPri: (+ (ego priority?) 1)
					cel: 0
					setCycle: CycleTo 3 1 self
					init:
				)
			)
			(2
				(gNewProp setCycle: EndLoop self)
				(ego viewer: 0 view: 370 cycleSpeed: 2 setCycle: Forward)
			)
			(3
				(Timer setReal: self 5)
				(gNewProp hide:)
				(Print 301 1)
			)
			(4
				(Print 301 2)
				(poofSound number: 59 play:)
				(gNewProp
					x: (ego x?)
					y: (ego y?)
					show:
					cel: 10
					setCycle: CycleTo 3 -1 self
				)
			)
			(5
				(ego view: oldEgoView cycleSpeed: 0 setCycle: Walk)
				(gNewProp setCycle: BegLoop self)
			)
			(6 (= cycles 2))
			(7
				(if (== (poofSound state?) 3)
					(self changeState: 6)
				else
					(poofSound dispose:)
					(gNewProp dispose:)
					(ego viewer: preFrogView script: oldEgoScript)
					(HandsOn)
					(DisposeScript 301)
				)
			)
		)
	)
)
