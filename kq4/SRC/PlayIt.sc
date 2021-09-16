;;; Sierra Script 1.0 - (do not remove this comment)
(script# 303)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	playIt 0
)

(local
	musicNotes
	saveViewer
)
(instance musicLute of Sound
	(properties
		number 54
	)
)

(instance playIt of Script
	(method (init)
		(Load VIEW 54)
		(= isHandsOff TRUE)
		(super init:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= musicNotes (Prop new:))
				(if (and (!= (ego view?) 2) (!= (ego view?) 4))
					(HandsOn)
					(musicLute dispose:)
					(DisposeScript 303)
					(return)
				)
				(if (== (ego view?) 2)
					(musicNotes
						view: 888
						ignoreActors:
						setPri: (ego priority?)
						cycleSpeed: 1
						setCycle: Forward
						posn: (+ (ego x?) 13) (- (ego y?) 27)
						init:
					)
				else
					(musicNotes
						view: 888
						ignoreActors:
						setPri: (ego priority?)
						cycleSpeed: 1
						setCycle: Forward
						posn: (+ (ego x?) 15) (- (ego y?) 37)
						init:
					)
				)
				(sounds eachElementDo: #stop 0)
				(musicLute play: self)
				(= saveViewer (ego viewer?))
				(ego
					viewer: 0
					view: 54
					setMotion: 0
					loop: (if (== (ego view?) 2) 0 else 1)
					cycleSpeed: 2
					setCycle: Forward
				)
			)
			(1
				(ego
					viewer: saveViewer
					view: (if (== (ego loop?) 0) 2 else 4)
				)
				(musicNotes dispose:)
				(ego
					viewer: saveViewer
					script: oldEgoScript
					cycleSpeed: 0
					loop: 2
					setCycle: Walk
				)
				(HandsOn)
				(musicLute dispose:)
				(DisposeScript 303)
			)
		)
	)
)
