;;; Sierra Script 1.0 - (do not remove this comment)
(script# 741)
(include sci.sh)
(use Main)
(use CastleRoom)
(use System)

(public
	earlyGuest 0
)

(instance earlyGuest of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 741)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 80 5)
					view: 724
					loop: 4
					cel: 0
					x: 129
					y: 155
					init:
				)
				((ScriptID 80 6)
					view: 726
					loop: 4
					cel: 1
					x: 151
					y: 153
					init:
				)
				((ScriptID 80 0) setupGuards:)
				(= cycles 2)
			)
			(1
				((ScriptID 80 6) setHeading: 180 self)
			)
			(2
				(messager say: 1 0 10 1 self)
			)
			(3
				(messager say: 1 0 10 2 self)
			)
			(4
				((ScriptID 80 5) setHeading: 180 self)
			)
			(5
				(messager say: 1 0 10 3 self)
			)
			(6 (= cycles 2))
			(7
				(curRoom moveOtherGuard: 1 spotEgo: (proc80_7))
				(self dispose:)
			)
		)
	)
)
