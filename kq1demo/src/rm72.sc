;;; Sierra Script 1.0 - (do not remove this comment)
(script# rBeanStalk3)
(include game.sh)
(use Main)
(use NewWalk)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm72 0
)

(local
	local0
)
(instance rm72 of Room
	(properties
		picture pBeanStalk3AGI
	)
	
	(method (init)
		(LoadMany VIEW
			vGiant
			vGiantGrabEgo
			vGiantStompEgo
			vEgoSmall
			vEgoClimbSmall
		)
		(Load PICTURE rBeanStalk2)
		(LoadMany SOUND 59 17)
		(self style: WIPELEFT)
		(super init:)
		(HandsOff)
		(self setScript: chaseEgo)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
	)
)

(instance giant of Actor
	(properties
		view vGiant
		loop 1
	)
)

(instance chaseEgo of Script
	
	(method (doit)
		(super doit:)
		(if (and (== state 6) (not local0) (> (ego y?) 6))
			(= local0 1)
			(DisplayNewGraphics)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DisplayOldGraphics)
				(ego
					view: vEgoSmall
					init:
					posn: 320 148
					setMotion: MoveTo 200 148
				)
				(giant
					init:
					setPri: 11
					cycleSpeed: 0
					moveSpeed: 0
					posn: 440 145
					setStep: 4 3
					setCycle: NewWalk
					setMotion: MoveTo 213 145 self
				)
			)
			(1
				(giant loop: loopS)
				(self cue:)
			)
			(2
				((ScriptID 0 6) fade:)
				(ego hide:)
				(giant
					view: vGiantGrabEgo
					loop: loopW
					cel: 0
					setCycle: EndLoop
					self
				)
				(if (== (giant loop?) 3) (- (giant y?) 7))
			)
			(3
				(ego hide:)
				(giant
					view: vGiantStompEgo
					cel: 3
					cycleSpeed: 1
					y: (+ (giant y?) 6)
					loop: 0
					setMotion: 0
					setAvoider: 0
					setCycle: CycleTo 10 1 self
				)
			)
			(4
				((ScriptID 0 5) number: 59 loop: 1 play:)
				((ScriptID 0 6) loop: 1 stop:)
				(ego
					show:
					view: vEgoClimbSmall
					setLoop: 5
					setCycle: Forward
					cycleSpeed: 1
					cel: 0
					ignoreActors:
					illegalBits: 0
					moveSpeed: 1
					setStep: 4 8
					setPri: 9
					setMotion: MoveTo (ego x?) 220 self
				)
				(= cycles 2)
			)
			(5
				(ShakeScreen 6)
				(giant setCycle: EndLoop)
			)
			(6
				(giant dispose: delete:)
				(curRoom drawPic: rBeanStalk2)
				((ScriptID 0 5) stop:)
				((ScriptID 0 6) number: 17 loop: -1 play:)
				(ego
					y: -10
					setPri: 15
					setStep: 4 8
					setMotion: MoveTo (ego x?) 220 self
				)
			)
			(7
				(curRoom newRoom: dmLeps)
			)
		)
	)
)
