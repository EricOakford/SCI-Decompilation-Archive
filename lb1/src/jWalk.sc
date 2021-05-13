;;; Sierra Script 1.0 - (do not remove this comment)
(script# 387)
(include game.sh)
(use Main)
(use Avoider)
(use Motion)
(use System)

(public
	jWalk 0
)

(instance jWalk of Script

	(method (init)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit: &rest)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript AVOIDER)
		(DisposeScript 387)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 7)
			)
			(1
				(HandsOff)
				(gDoor
					setCycle: Walk
					setMotion: MoveTo 305 55 self
					setAvoider: ((Avoider new:) offScreenOK: TRUE)
					init:
				)
				(if (ego inRect: 252 117 312 131)
					(ego setMotion: MoveTo (ego x?) (+ (ego y?) 20))
				)
				(if (ego inRect: 295 50 320 60)
					(ego setMotion: MoveTo (- (ego x?) 15) (ego y?))
				)
			)
			(2
				(HandsOff)
				(gDoor setMotion: MoveTo 302 124 self)
			)
			(3
				(gDoor setMotion: MoveTo 270 123 self)
			)
			(4
				(gMyMusic number: 43 loop: 1 priority: 5 play:)
				(gCellar setCycle: EndLoop self)
			)
			(5
				(gDoor
					illegalBits: 0
					setPri: 8
					setMotion: MoveTo 240 122 self
				)
			)
			(6
				(gDoor setMotion: MoveTo 219 129 self)
			)
			(7
				(gDoor hide:)
				(gCellar setCycle: BegLoop self)
				(gMyMusic number: 44 loop: 1 priority: 5 play:)
			)
			(8
				(gDoor dispose:)
				(gCellar stopUpd:)
				(HandsOn)
				(= global155 17)
				(client setScript: 0)
			)
		)
	)
)
