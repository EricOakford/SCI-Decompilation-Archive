;;; Sierra Script 1.0 - (do not remove this comment)
(script# EAT)
(include game.sh)
(use Main)

(public
	Eat 0
)

(procedure (Eat event &tmp haveEaten)
	(= haveEaten 0)
	(cond 
		((Said '/mushroom,toadstool')
			(cond 
				((and (not (ego has: iMushroom)) (!= curRoomNum 70))
					(HighPrint EAT 0)
					;You don't have any.
				)
				((Btst fHaveToadstools)
					(ego use: iMushroom 3)
					(if (Btst fHaveFaeryShrooms) (HighPrint EAT 1))
					;The mushrooms from the Fairy Ring are pretty good, so you hear. 
					;However, when they're mixed with Kobold toadstools...that's a horse of a different color.
					(HighPrint EAT 2)
					;You eat some of the Kobold's fungus.
					;Within seconds your insides feel like they're on fire.
					;Guess you're not a Kobold, huh?
					(if (<= [egoStats HEALTH] 20)
						(EgoDead
							{Didn't anyone warn you about eating other people's food?__Death by Toadstools doesn't leave mushroom for improvement.}
							#icon vDeathScenes 1 1
						)
					else
						(TakeDamage 20)
						(HighPrint EAT 3)
						;You think that you'd better take it easy for a while until you recover.
					)
					(if (not (ego has: iMushroom))
						(Bclr fHaveToadstools)
					)
				)
				(else
					(ego use: iMushroom 3)
					(if (Btst fStarving) (Bclr fStarving) else (Bclr fHungry))
					(HighPrint EAT 4)
					;You ingest a few of the mushrooms from the Faery Ring.  They taste delicious.
					(HighPrint EAT 5)
					;Wow!  Look at all the nice paisley horses!
					(HighPrint EAT 6)
					;Not to mention the beautiful neon sky.
					(if (Btst fAteShrooms)
						(EgoDead EAT 7
							#title {Where did all the pretty horses go?}
							#icon vDeathScenes 1 1
							;That's funny.  I could have sworn I warned you about eating too many Magic Mushrooms.
							;Your mind permanently warped, you die a garishly polka-dotted death.
						)
					else
						(HighPrint EAT 8)
						;Hmm, that was interesting.  But it would probably not be a good idea to repeat the experience.
					)
					(Bset fAteShrooms)
				)
			)
		)
		((Said '/acorn,nut')
			(if (not (ego has: iAcorn))
				(HighPrint EAT 0)
				;You don't have any.
			else
				(ego use: iAcorn 1)
				(HighPrint EAT 9)
				;You eat the Magic Acorn. It tastes terrible. Better stick to real food next time.
			)
		)
		(freeMeals
			(event claimed: TRUE)
			(HighPrint EAT 10)
		)
		((Said '/apple')
			(if (ego has: iFruit)
				(ego use: iFruit 3)
				(HighPrint EAT 11)
				;You eat some of the apples.  They actually taste quite good.
				(= haveEaten TRUE)
			else
				(HighPrint EAT 12)
				;You don't have any fruit.
			)
		)
		((Said '/carrot')
			(if (ego has: iVegetables)
				(ego use: iVegetables 2)
				(HighPrint EAT 13)
				;You eat some vegetables. You think they would have been better saved for cows or horses.
				;Real Heroes eat preserved dry rations.
				(= haveEaten TRUE)
			else
				(HighPrint EAT 14)
				;You don't have any vegetables.
			)
		)
		((or (Said '/ration,food') (Said 'eat[/!*]'))
			(if (ego has: iRations)
				(ego use: iRations 1)
				(HighPrint EAT 15)
				;The rations are tasteless but filling.
				(= haveEaten TRUE)
			else
				(HighPrint EAT 16)
				;You aren't carrying any rations.
			)
		)
		(else
			(event claimed: TRUE)
			(HighPrint EAT 17)
		)
		;Ugh. You don't want to eat *that*.
	)
	(if haveEaten
		(if (Btst fHungry)
			(Bclr fHungry)
			(Bclr fStarving)
		else
			(= freeMeals 1)
		)
	)
	(DisposeScript EAT)
)
