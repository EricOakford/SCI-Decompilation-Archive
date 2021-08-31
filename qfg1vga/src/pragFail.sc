;;; Sierra Script 1.0 - (do not remove this comment)
(script# PRAGFAIL)
(include game.sh) (include "121.shm")
(use Main)
(use CastDart)
(use ThrowKnife)
(use ThrowRock)
(use User)
(use System)

(public
	pragFail 0
)

(procedure (DumpPotion wPotion)
	(ego use: wPotion 1)
	(ego get: iFlask)
	(messager say: N_PRAGFAIL NULL NULL 1 0 PRAGFAIL)
)

(instance pragFail of Code
	(method (doit &tmp theVerb [temp1 52])
		(= theVerb ((user curEvent?) message?))
		(if (User input?)
			(if modelessDialog
				(modelessDialog dispose:)
			)
			(if (OneOf theVerb V_LOOK V_TALK V_DO V_MONEY V_SWORD V_LOCKPICK V_BRASSKEY V_CANDELABRA)
				(messager say: N_PRAGFAIL theVerb NULL 0 0 121)
				(return TRUE)
			else
				(switch theVerb
					(V_FLAME
						(CastDart 0)
					)
					(V_ROCK
						(ThrowRock 0)
						(return TRUE)
					)
					(V_DAGGER
						(ThrowKnife 0)
						(return TRUE)
					)
					(V_HEALING
						(DumpPotion iHealingPotion)
					)
					(V_MANA
						(DumpPotion iManaPotion)
					)
					(V_VIGOR
						(DumpPotion iStaminaPotion)
					)
					(V_DISENCHANT
						(DumpPotion iDisenchant)
					)
					(V_GHOSTOIL
						(DumpPotion iGhostOil)
					)
					(V_WATER
						(DumpPotion iFlyingWater)
					)
					(else 
						(messager say: N_PRAGFAIL NULL NULL 2 0 121)
						(return TRUE)
					)
				)
			)
		)
		(return
			(DisposeScript PRAGFAIL)
		)
	)
)
