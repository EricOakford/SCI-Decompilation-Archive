;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_DAZZLE) ;149
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	egoDazzle 0
)

(local
	local0
	local1
	local2
)
(instance egoDazzle of Script
	(properties)
	
	(method (init)
		(= local0 (ScriptID WARRIOR 0))
		(= local2 (ScriptID CLOSECOMBAT 1))
		(= local1 (local0 egoHand?))
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript ARENA_DAZZLE)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(local0 canFight: FALSE action: 11)
				(self cue:)
			)
			(1
				(local2
					setLoop: 5
					cel: 0
					setPri: (- (local1 priority?) 1)
					cycleSpeed: 1
					ignoreActors:
					posn: (- (local0 x?) 78) (- (local0 y?) 81)
					init:
					setCycle: EndLoop self
				)
			)
			(2
				(local2 dispose:)
				(Bset fMonsterDazzled)
				(= monsterDazzle [egoStats DAZZLE])
				(local0 canFight: FALSE action: 0)
				(self dispose:)
			)
		)
	)
)
