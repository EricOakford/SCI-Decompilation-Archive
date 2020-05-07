;;; Sierra Script 1.0 - (do not remove this comment)
(script# 339)
(include game.sh)
(use Main)

(public
	SaidTavern 0
	TavernLookAround 1
	TavernLookAtStool 2
)

(procedure (SaidTavern)
	(cond 
		(
			(Said
				'[<at,around][/!*,room,building,tavern,establishment]'
			)
			(TavernLookAround 0 0)
			
		)
		((Said '/barrel,barrel')
			(if (Btst HERO_SITTING) ((ScriptID 331 5) setCel: 2))
			(HighPrint 339 0 83)
			;From the size of the ancient oak kegs, you surmise that the drinking of large quantities of beverages is the main pastime in this establishment.
		)
		((Said '/dirt')
			(HighPrint 339 1)
			;As dirt goes, it's pretty dirty.
			)
		((Said '/ale,sweat,breath,drink,beverage')
			(switch drinkInHand
				(drinkNothing
					(HighPrint 339 2)
					;It's in the kegs.
					)
				(drinkAle
					(HighPrint 339 3)
					;Mighty frothy. Looks OK.
					)
				(drinkSweat
					(HighPrint 339 4)
					;Looks lean, mean, and green.
					)
				(drinkBreath
					(HighPrint 339 5)
					;The fumes hurt your eyes.
					)
			)
		)
		((Said '/bar,spill,cup')
			(if (Btst HERO_SITTING) ((ScriptID 331 5) setCel: 1))
			(HighPrint 339 6)
			;It doesn't look like the bartender ever wipes up spills, and the glasses are truly filthy.
		)
		((Said '/bartender,boot,chop,crewcut,tattoo')
			(if (Btst HERO_SITTING)
				(if (> ((ScriptID 331 2) x?) (ego x?))
					((ScriptID 331 5) setCel: 2)
				else
					((ScriptID 331 5) setCel: 1)
				)
			)
			(HighPrint 339 7)
			;The bartender looks tough from his crewcut to his boots. The only thing soft about him is his tattoo, which says "MAMA".
		)
		(
		(or (Said '/bum,barber') (Said '/man/barstool'))
			(if (Btst HERO_SITTING) ((ScriptID 331 5) setCel: 1))
			(HighPrint 339 8)
			;So this is where the barber goes to lunch! The guy's had so much liquid diet, he looks embalmed.
		)
		((Said '/gambler,table,player,butcher,baker')
			(if (Btst HERO_SITTING) ((ScriptID 331 5) setCel: 2))
			(HighPrint 339 9)
			;The man on the west side of the table is wearing a blood-stained apron, like a butcher.
			;The man on the east has on a baker's hat. They seem to be deeply involved in their game.
		)
		((Said '/card,game,cardgame')
			(if (not (ego inRect: 171 140 291 183))
				(HighPrint 339 10)
				;You can't see the game very well from where you are.
			else
				(HighPrint 339 11)
				;They seem to be using an "Old Maid" deck.
			)
		)
		((Said '/man')
			(HighPrint 339 12)
			;There are several.
			)
		((Said '/bouncer,goon')
			(if (Btst HERO_SITTING) ((ScriptID 331 5) setCel: 1))
			(HighPrint 339 13)
			;This goon looks really tough and mean. He's not someone to pick a fight with.
		)
		((Said '/smoke,dragon')
			(HighPrint 339 14)
			;That's a mighty unusual keg, there.
			)
		((Said '<below/barstool')
			(cond 
				((ego inRect: 128 106 171 130)
					(if (Btst OBTAINED_BAR_NOTE)
						(HighPrint 339 15)
						;Nothing but a dirty floor.
					else
						(HighPrint 339 16)
						;You see a crumpled piece of paper under the stool.
					)
				)
				((ego inRect: 192 109 237 130)
					(HighPrint 339 17)
					;That looks mighty nasty!
					)
				(else
					(HighPrint 339 15)
					;Nothing but a dirty floor.
					)
			)
		)
		((Said '/barstool')
			(if (ego inRect: 192 109 237 130)
				(TavernLookAtStool 0)
			else
				(HighPrint 339 18)
				;There are three bar stools at the bar.
			)
		)
		((Said '/goo')
			(if (ego inRect: 192 109 237 130)
				(HighPrint 339 19)
				;Not a pleasant sight!
			else
				(PrintNotCloseEnough)
			)
		)
		(
		(or (Said '<up') (Said '/wall,ceiling,beam,forest'))
		(HighPrint 339 20)
		;It looks like something you'd expect to see in a tavern, but a lot dirtier.
		)
		((or (Said '<down') (Said '/floor'))
			(if
			(and (ego inRect: 128 106 171 130) (not (Btst OBTAINED_BAR_NOTE)))
				(HighPrint 339 16)
				;You see a crumpled piece of paper under the stool.
			else
				(HighPrint 339 20)
				;It looks like something you'd expect to see in a tavern, but a lot dirtier.
			)
		)
		((Said '/chasm,door,trap')
			(HighPrint 339 21)
			;There appears to be a trapdoor beneath the goon, but he's blocking it very effectively.
			)
	)
	(DisposeScript 339)
)

(procedure (TavernLookAround firstTime param2)
	(HighPrint 339 22)
	;The grimy window lets little light into this tavern.  It smells like stale ale and other more unpleasant things.
	;The floor is covered with dirt, and the bar with sticky beer.
	(HighPrint 339 23)
	;Smoke appears to be rising from the center cask behind the bar.  To your right, two gamblers are playing cards.
	(if firstTime
		(HighPrint 339 24)
		;The bartender glares at you as you enter, and so does the ugly goon on the left. You get the impression that you are not welcome.
		)
	(if param2 (DisposeScript 339))
)

(procedure (TavernLookAtStool param1)
	(HighPrint 339 25)
	;There seems to be something slimy spilled on top of the stool, and it is eating into the wood.  You think perhaps you should sit on the next stool over.
	(if param1 (DisposeScript 339))
)
