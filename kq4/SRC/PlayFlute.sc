;;; Sierra Script 1.0 - (do not remove this comment)
(script# 304)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	playFlute 0
)

(local
	gEgoViewer
	musicNotes
)
(instance fluteMusic of Sound
	(properties)
)

(instance playFlute of Script
	(properties)
	
	(method (init)
		(= isHandsOff TRUE)
		(Load VIEW 55)
		(super init:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= musicNotes (Prop new:))
				(musicNotes
					view: 888
					ignoreActors:
					setPri: (+ (ego priority?) 1)
					cycleSpeed: 1
					setCycle: Forward
					posn: (+ (ego x?) 10) (- (ego y?) 27)
					init:
				)
				(= gEgoViewer (ego viewer?))
				(ego
					viewer: 0
					view: 55
					loop: (if (== (ego view?) 2) 0 else 1)
					setMotion: 0
					cycleSpeed: 2
					setCycle: Forward
				)
				(sounds eachElementDo: #stop 0)
				(fluteMusic number: 77 play: self)
			)
			(1
				(ego
					viewer: gEgoViewer
					view: (if (== (ego loop?) 0) 2 else 4)
				)
				(ego
					script: oldEgoScript
					cycleSpeed: 0
					loop: 2
					setCycle: Walk
				)
				(musicNotes dispose:)
				(HandsOn)
				(fluteMusic dispose:)
				(DisposeScript 304)
			)
		)
	)
)
