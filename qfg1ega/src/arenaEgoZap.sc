;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_ZAP) ;148
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	egoZap 0
)

(local
	local0
	local1
)
(instance egoZap of Script
	(properties)
	
	(method (init)
		(= local0 (ScriptID WARRIOR 0))
		(= local1 (ScriptID CLOSECOMBAT 1))
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript ARENA_ZAP)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= zapPower (+ 5 (/ [egoStats ZAP] 10)))
				(local0 canFight: FALSE action: 11 setCel: 2)
				(= cycles 1)
			)
			(1
				(local1
					setLoop: 7
					cel: 0
					cycleSpeed: 1
					ignoreActors:
					posn: (- (local0 x?) 7) (- (local0 y?) 70)
					setPri: (+ (local0 priority?) 1)
					init:
					setCycle: EndLoop self
				)
			)
			(2
				(local1 dispose:)
				(local0 canFight: TRUE setCel: 0)
				(self dispose:)
			)
		)
	)
)
