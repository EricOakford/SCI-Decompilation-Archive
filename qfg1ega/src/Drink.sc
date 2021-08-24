;;; Sierra Script 1.0 - (do not remove this comment)
(script# DRINK)
(include game.sh)
(use Main)
(use Invent)

(public
	DrinkPotion 0
)

(procedure (DrinkPotion event index &tmp wPotion)
	(if index
		(= wPotion index)
	else
		(= wPotion (SaidInv event))
	)
	(cond 
		((not wPotion)
			(event claimed: FALSE)
			(cond 
				((Said '/water') ;Bugged for some reason; claims "That isn't drinkable."
					(if (ego has: iWater)
						(ego use: iWater 1)
						(ego get: iFlask)
						(cond 
							((not (ego has: iWater))
								(Bclr fHaveLakeWater)
								(Bclr fHaveFlyingWater)
							)
							((Btst fHaveLakeWater)
								(Bclr fHaveLakeWater)
							)
							((Btst fHaveFlyingWater)
								(Bclr fHaveFlyingWater)
							)
						)
						(HighPrint DRINK 0)
						;You drink a flask of water.  It tastes great.
						(HighPrint DRINK 1)
						;(Less filling, too!)
					)
				)
				((Said '/potion')
					(= wPotion 0)
					(for ((= index iHealingPotion)) (<= index iGhostOil) ((++ index))
						(cond 
							((and [invNum index] (not wPotion))
								(= wPotion index)
							)
							((and wPotion [invNum index])
								(= wPotion -1)
								(break)
							)
						)
					)
					(switch wPotion
						(-1 
							(HighPrint DRINK 2)
							;Perhaps you could tell me which potion you want to drink.							
						)
						(0
							(HighPrint DRINK 3)
							;You have no potions.	
						)
						(else
							(DrinkPotion event wPotion)
						)
					)
				)
				(else
					(event claimed: TRUE)
					(HighPrint DRINK 4)
					;You don't see any here.
				)
			)
		)
		((or (< wPotion iHealingPotion) (> wPotion iGhostOil))
			(event claimed: FALSE)
			(if (Said 'drink')
				(HighPrint DRINK 5)
				;That isn't drinkable.
			else
				(HighPrint DRINK 6)
				;It's not clear how you want to use that.
			)
			(event claimed: TRUE)
		)
		((not [invNum wPotion])
			(HighPrint DRINK 7)
		)
		((== wPotion iHealingPotion)
			(HighPrint DRINK 8)
			;The drink soothes as it goes down.
			(TakeDamage (- (/ (MaxHealth) 2)))
			(ego use: iHealingPotion)
			(ego get: iFlask)
		)
		((== wPotion iManaPotion)
			(HighPrint DRINK 9)
			;The drink burns as it goes down.
			(UseMana (- (/ (MaxMana) 2)))
			(ego use: iManaPotion)
			(ego get: iFlask)
		)
		((== wPotion iStaminaPotion)
			(HighPrint DRINK 10)
			;The drink is invigorating.
			(UseStamina (- (MaxStamina)))
			(ego use: iStaminaPotion)
			(ego get: iFlask)
		)
		((== wPotion iDisenchant)
			(HighPrint DRINK 11)
			;You don't feel anything.  Perhaps this was not the correct way to use this potion.
			(ego use: iDisenchant)
			(ego get: iFlask)
			(event claimed: TRUE)
		)
		((== wPotion iGhostOil)
			(if (ego has: iGhostOil)
				(HighPrint DRINK 12)
				;You feel a tingling sensation as you rub the unguent all over your body.
				(Bset fGhostOil)
				(Bclr fGhostsAttack)
				(= ghostOilTimer GAMEHOUR)
				(ego use: iGhostOil)
				(ego get: iFlask)
			else
				(HighPrint DRINK 13)
				;You can't.  You don't have any.
			)
		)
	)
	(DisposeScript DRINK)
)
