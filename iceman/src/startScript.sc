;;; Sierra Script 1.0 - (do not remove this comment)
(script# 376)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use System)

(public
	startScript 0
	johnnyLost 1
)

(instance startScript of Script
	(properties
		seconds 2
	)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (dispose)
		(if modelessDialog (modelessDialog dispose:))
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose: &rest)
		(DisposeScript 376)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(Print 376 0 #at 35 10 #width 250 #dispose)
				((ScriptID 39 9) loop: 0 cycleSpeed: 2 setCycle: Forward)
			)
			(2
				(Print 376 1 #at 35 10 #width 250 #dispose)
			)
			(3
				(cls)
				((ScriptID 39 9) loop: 1 cel: 1 setCycle: 0)
				((ScriptID 39 0) notify: 10)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(OneOf state 1 2)
				(or
					(== (event type?) keyDown)
					(== (event type?) mouseDown)
				)
			)
			(event claimed: TRUE)
			(self cue:)
		)
	)
)

(instance johnnyLost of Script
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 376)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 39 8) show: cycleSpeed: 1 setCycle: Forward)
				(= seconds 3)
			)
			(1
				(Print 376 2)
				(= cycles 2)
			)
			(2
				((ScriptID 39 8) hide:)
				(cls)
				((ScriptID 39 9) loop: 0 cycleSpeed: 1 setCycle: Forward)
				(= seconds 3)
			)
			(3
				(Print 376 3)
				(= cycles 2)
			)
			(4
				((ScriptID 39 9) loop: 1 cel: 1 setCycle: 0)
				(SetCursor theCursor TRUE 310 180)
				(curRoom newRoom: 32)
				(self dispose:)
			)
		)
	)
)
