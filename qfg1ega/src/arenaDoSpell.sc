;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_MAGIC) ;146
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	doSpell 0
)

(local
	local0
	local1
	local2
	local3
)
(instance doSpell of Script
	(properties)
	
	(method (init)
		(= local0 (ScriptID WARRIOR 0))
		(= local1 ((ScriptID WARRIOR 0) egoHand?))
		(= local2 ((ScriptID WARRIOR 0) egosBack?))
		(= local3 ((ScriptID WARRIOR 0) egoShield?))
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript ARENA_MAGIC)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoCanFight FALSE)
				(local1 setCycle: EndLoop self)
			)
			(1
				(switch register
					(FLAMEDART
						(self setScript: (ScriptID 147 0) self)
					)
					(ZAP
						(self setScript: (ScriptID 148 0) self)
					)
					(DAZZLE
						(self setScript: (ScriptID 149 0) self)
					)
					(CALM
						(self setScript: (ScriptID 150 0) self)
					)
				)
			)
			(2
				(= register 0)
				(local1 setCycle: BegLoop self)
			)
			(3
				(local1 stopUpd:)
				(= egoCanFight TRUE)
				(self dispose:)
			)
		)
	)
)
