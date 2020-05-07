;;; Sierra Script 1.0 - (do not remove this comment)
(script# 194)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	openGate 0
	closeGate 1
)

(instance openGate of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 194)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 94 17) cycleSpeed: 2 setCycle: EndLoop self)
			)
			(1
				((ScriptID 94 0) notify: 4)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance closeGate of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 194)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 94 17) cycleSpeed: 2 setCycle: BegLoop self)
			)
			(1
				((ScriptID 94 0) notify: 5)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
