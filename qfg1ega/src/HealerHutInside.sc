;;; Sierra Script 1.0 - (do not remove this comment)
(script# 55)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use RFeature)
(use Sound)
(use Save)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rm55 0
)

(local
	local0
	clawsSold
	healerFacingAway
	local3
	healerKiss
	theCycles
	chatHealer
)
(procedure (AskAboutDispel)
	(cond 
		((Btst OBTAINED_DISPEL_POTION)
			(HighPrint 55 0)
			;"I cannot make you another Dispel Potion unless you can convince the Dryad to give you another magic acorn.
			;I do not think that is likely.
			)
		((Btst DISPEL_HEALER_MAKING_POTION)
			(HighPrint 55 1)
			;Please leave me to make the potion and when you return I shall have it for you.
			)
		((Btst DISPEL_HEALER_KNOWS_RECIPE)
			(DispelIngredients)
			)
		(else
			(HighPrint 55 2)
			;"If you know the ingredients for a Dispel Potion and bring them here, I can make you such a potion."
			)
	)
)

(procedure (DispelIngredients &tmp [str 200])
	(if (and (not (Btst DISPEL_HEALER_MAKING_POTION))
			(not (Btst OBTAINED_DISPEL_POTION))
				)
		(cond 
			(
				(and
					(Btst DISPEL_GAVE_DUST)
					(Btst DISPEL_GAVE_FUR)
					(Btst DISPEL_GAVE_FLOWERS)
					(Btst DISPEL_GAVE_ACORN)
					(Btst DISPEL_GAVE_WATER)
				)
				(rm55 setScript: waitForHealer)
				(Bset DISPEL_HEALER_MAKING_POTION)
				(Bclr OBTAINED_DISPEL_POTION)
				(Bclr DISPEL_GAVE_DUST)
				(Bclr DISPEL_GAVE_FUR)
				(Bclr DISPEL_GAVE_FLOWERS)
				(Bclr DISPEL_GAVE_ACORN)
				(Bclr DISPEL_GAVE_WATER)
			)
			((Btst DISPEL_HEALER_KNOWS_RECIPE)
				(HighPrint
					(Format
						@str
						55
						3
						;Let's see, to make the Dispel Potion I still need: %s %s %s %s %s
						(if (Btst DISPEL_GAVE_DUST) {} else { \nFairy Dust})
						(if (Btst DISPEL_GAVE_FUR) {} else { \nGreen Fur})
						(if (Btst DISPEL_GAVE_FLOWERS) {} else { \nFlowers from Erana's Peace})
						(if (Btst DISPEL_GAVE_ACORN) {} else { \nMagic Acorn})
						(if (Btst DISPEL_GAVE_WATER) {} else { \nFlying Water})
					)
				)
			)
		)
	)
)

(procedure (HealerSays)
	(Print &rest #width 305 #mode teJustLeft #window healerWin)
)

(procedure (BuyPotion potion silvers)
	(return
		(if (Purchase silvers)
			(HighPrint 55 4)
			;You pay the Healer for the potion and put it away.
			(HighPrint 55 5)
			;"I hope this will help you."
			(ego get: potion 1)
			(return TRUE)
		else
			(HighPrint 55 6)
			;You find that you have less money than you thought.  You can't afford to buy the potion.
			(return FALSE)
		)
	)
)

(instance healerWin of SysWindow
	(properties
		color 1
	)
	
	(method (open &tmp temp0)
		(= top (+ top (= temp0 (- 188 bottom))))
		(= bottom (+ bottom temp0))
		(super open:)
	)
)

(instance rm55 of Room
	(properties
		picture 55
		style DISSOLVE
	)
	
	(method (init)
		(LoadMany VIEW vHealer vHealerInside vEgoFall vEgoBigGrin)
		(Load SOUND 42)
		(super init:)
		(mouseDownHandler add: self)
		(kettle init: setCycle: Forward)
		(addToPics
			add: rmTable rmStuff1 rmStuff2 rmStuff3 rmBin rmShelf1 rmShelf2 rmLamp
			eachElementDo: #init
			doit:
		)
		(self
			setFeatures:
				onKettle
				onBird
				onMaps
				onLadder
				onTable
				onLitterBox
				onLamp
				onWindow
				onThings
				onStores
				onBins
		)
		(StatusLine enable:)
		(NormalEgo)
		(if (< numColors 8) (healerWin color: 0 back: 15))
		(bird setScript: preening)
		(self setScript: egoEnters)
	)
	
	(method (doit)
		(if (& (ego onControl: origin) cLBLUE)
			(if (and (not (Btst MET_HEALER)) (not (Btst RETURNED_RING)))
				(HighPrint 55 7)
			)
			(Bset MET_HEALER)
			(curRoom newRoom: 54)
		)
		(super doit:)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(Bset VISITED_HEALERHUT_INSIDE)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (MouseClaimed ego event shiftDown)
			(HighPrint 55 8)
			;You can buy potions, get information, or sell ingredients here.
			)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look>')
						(cond 
							((Said '[<at,around][/room,hut,building]')
								(HighPrint 55 9) 
								;You see a clean, but very cluttered little hut filled with bottles and potions.
								;The Healer is busy puttering around with her potions.
								(HighPrint 55 10)
								;There is a small flying creature preening itself .  A ladder leads to the loft above.
								;A pot over the fire simmers with herbs.  It is pleasantly warm in here.
								)
							((Said '/healer,woman')
								(HighPrint 55 11)
								;You see a middle-aged woman with rosy cheeks and smile lines around her eyes.
								)
							((Said '/creature,animal,bird,lizard')
								(HighPrint 55 12)
								;You see a winged lizard much like the one you saw outside.
								)
							((Said '/potion')
								(HighPrint 55 13)
								;You see bottles and flasks of a wide variety of different potions.
								)
							((Said '/pan,caldron,fire')
								(HighPrint 55 14)
								;Whatever is boiling there is either soup or some sort of potion.
								)
							((Said '/soup')
								(HighPrint 55 15)
								;Soup is good for you.  Mmmm Mmmm good.
								)
							((Said '/ladder')
								(HighPrint 55 16)
								;It looks like a ladder.
								)
						)
					)
					((Said 'eat,get/soup')
						(HighPrint 55 17)
						;It's not your soup.
						)
					((or (Said 'nap') (Said 'go[<to]/nap'))
						(HighPrint 55 18)
						;"Come back in the morning, please."
						)
					((Said 'climb/ladder')
						(HighPrint 55 19)
						;Don't be nosy.
						)
					((Said '/bin,icicle,cheesecloth')
						(HighPrint 55 20)
						;You can look but don't touch.
						)
				)
			)
		)
	)
)

(instance healer of Actor
	(properties
		view vHealer
		illegalBits $0000
	)
	
	(method (doit)
		(if
			(and
				healerKiss
				(!= script healerKisses)
				(> (+ (* (ego y?) 3) (ego x?) -615) 0)
			)
			(healer setScript: healerKisses)
		)
		(super doit:)
	)
	
	(method (handleEvent event &tmp spell)
		(cond 
			((super handleEvent: event))
			((== mouseDown (event type?))
				(cond 
					((super handleEvent: event))
					((MouseClaimed healer event shiftDown)
						(HighPrint 55 21)
						;A rather motherly-looking woman with a look of vagueness that belies her shrewdness.
						;She wears an apron over her dress and a scarf wrapped around her head.
						)
				)
			)
			((== saidEvent (event type?))
				(cond 
					((super handleEvent: event))
					((Said 'chat,ask>')
						(= chatHealer TRUE)
						(cond 
							((Said '//ring<faerie,faerie')
								(HighPrint 55 22)
								;"Those fairies are cute, but they play rough!"
								)
							((Said '//ring')
								(if (Btst RETURNED_RING)
									(HighPrint 55 23)
									;"It is shaped in gold like a braid of the herb Althelas with entwined leaves."
								else
									(HighPrint 55 23)
									;"It is shaped in gold like a braid of the herb Althelas with entwined leaves."
									(HighPrint 55 24)
									;"I don't know how I lost it.  I hardly ever take it off."
								)
							)
							((Said '//prize')
								(if (Btst RETURNED_RING)
									(HighPrint 55 25)
									;"You already have your reward."
								else
									(HighPrint 55 26)
									;"I will give six golds to the person who returns my ring.
									;It was a gift when I graduated from the college of Healing."
								)
							)
							((Said '//component,material')
								(HighPrint 55 27)
								;"I will pay you for Cheetaur claws, Troll beard, magic mushrooms, and flowers from Erana's Peace."
								)
							((Said '//herb')
								(HighPrint 55 28)
								;"I use them to make my potions."
								)
							((Said '//potion<heal,healing')
								(HighPrint 55 29)
								;"This potion is used to heal damage and keep wounds from getting worse.
								;It has gotten pretty dangerous around here, and a Healing Potion can be quite useful in a serious situation."
								)
							((Said '//potion<vigor,stamina')
								(HighPrint 55 30)
								;"Vigor Potion is used to revitalize yourself after vigorous exercise.   It helps to restore stamina."
								)
							((Said '//potion<mana,magic,power')
								(HighPrint 55 31)
								;"Magic Potions restore the energy needed to cast spells."
								)
							((Said '//potion<disenchant')
								(AskAboutDispel)
								)
							((Said '//potion<heal,healing');EO: Already listed above for some reason, but has a different message.
								(HighPrint 55 32)
								;"I make up potions that heal injuries.  I will be happy to sell you a Healing Potion if you like."
								)
							((Said '//potion')
								(HighPrint 55 33)
								;"I make and sell Healing Potions, Magic Potions, Vigor Potions and Undead Unguent."
								(HealerSays 55 34)
								;"The Potions will cost you:
								;Healing Potion 40 silvers
								;Magic Potion 60 silvers
								;Vigor Potion 20 silvers
								;Undead Unguent 100 silvers."
								)
							((Said '//grease,grease,ghoul')
								(HighPrint 55 35)
								;"Undead Unguent is used to drive off the minor undead such as zombies or floating spirits.
								;It doesn't last long, so you should use it only when you are anticipating an encounter with such things."
								)
							((Said '//zombie')
								(HighPrint 55 36)
								;"There are not too many zombies in this land... too damp, I suppose.
								;There are some floating spirits, I have heard."
								)
							((Said '//ghost')
								(HighPrint 55 37)
								;"Floating Spirits are the ghosts which usually hang around graveyards at night.
								;They are dangerous, so don't go near them unless you use Undead Unguent first."
								)
							((Said '//stamina')
								(HighPrint 55 38)
								;"Stamina is a measure of the energy the body uses as you work or play."
								)
							((Said '//fur')
								(HighPrint 55 39)
								;"I've never heard of a monster around here with green fur.  Rare things are sometimes by their nature magical."
								)
							((Said '//faerie,dust')
								(HighPrint 55 40)
								;"Fairy Dust obviously comes from fairies.  They dance around mushroom rings when it is night.
								;Fairies are magical beings, so be careful around them."
								)
							((Said '//flower')
								(HighPrint 55 41)
								;"I use flowers from Erana's Peace to the north in nearly all my potions.
								;They have some of the magic of the place even when they are dried."
								(if (>= 1 numFlowers)
									(HighPrint 55 42)
									;"I will pay you five silvers for a flask full of flowers."
									)
							)
							((Said '//mushroom')
								(HighPrint 55 43)
								;"I use the mushrooms from a fairy ring to make Magic Potions."
								(if (>= 2 numMushrooms)
									(HighPrint 55 44)
									;"I will pay you one gold for some."
									)
							)
							((Said '//claw,cheetaur')
								(HighPrint 55 45)
								;"The Cheetaur looks like a cross between a panther and a man.  It is vicious and tough.
								;Unless you are a very tough fighter, you had best try to get away from it.
								;If you do manage to kill it, then I will pay you five silvers for each claw."
								)
							((Said '//beard,troll')
								(HighPrint 55 46)
								;"The Trolls around here are tough monsters that cannot stand the light of day and so are found at night or in caves.
								;They are very difficult to kill, but I will pay two healing potions for the beard of a Troll."
								)
							((Said '//dryad')
								(HighPrint 55 47)
								;"I have heard that the Dryad of the woods knows a Dispel Potion to disenchant people with spells upon them."
								)
							((Said '//water<fly')
								(HighPrint 55 48)
								;"I'm not sure what kind of water that is."
								)
							((Said '//acorn[<magic,about]')
								(HighPrint 55 49)
								;"The only place you can get a magic acorn from is a Dryad's oak tree."
								)
							((Said '//bottle')
								(HighPrint 55 50)
								;"I will pay you one silver for each empty flask you bring me so I can use it for more potions.
								;Waste not, want not, I always say."
								)
							((Said '//name,handle,woman,healer')
								(HighPrint 55 51)
								;"Well, my name is Amelia Appleberry, but mostly I am just known as the Healer around here."
								)
							((Said '//bird,creature,lizard')
								(HighPrint 55 52)
								;"Oh, that's my pet, Pterry, the pterosaur.  He has a girl friend, Pteresa, who has a nest in the oak outside my door.
								;Pterry keeps me company and listens to my chatter as I work."
								)
							((Said '//lizard')
								(HighPrint 55 53)
								;"They are a species of flying lizard.  I understand they can grow quite large in the south."
								)
							((Said '//baron')
								(HighPrint 55 54)
								;"Poor man, he hasn't been the same since his son and daughter were taken from him."
								)
							((Said '//barnard,barnard')
								(HighPrint 55 55)
								;"He was just a young dashing man when he last rode off and never returned."
								)
							((Said '//elsa,daughter')
								(HighPrint 55 56)
								;"The dear child, I can still picture her with her beautiful blond hair done up in braids.
								;She was so sweet.  It's hard to believe she's gone."
								)
							((Said '//bandit')
								(HighPrint 55 57)
								;"Those brutes!  I'm always having to heal someone they've beaten up and robbed.  I hate thieves and brigands."
								)
							((Said '//centaur,farmer,heinrich')
								(HighPrint 55 58)
								;"Would you believe the brigands almost killed him a while back?
								;Fortunately their leader made them bring Heinrich here where I could heal him."
								)
							((Said '//(peace<erana),erana')
								(HighPrint 55 59)
								;"Almost due north of here is the meadow called Erana's Peace.  It is a very magical area and it is a place of safety.
								;It is beautiful all year, for the flowers are always in bloom."
								)
							((Said '//mana,magic,power')
								(HighPrint 55 60)
								;"I have the skill to use my magic to create potions.
								;I am a bit proud about it.  Not everyone can do that, you know."
								)
							((Said '//mandrake,root')
								(HighPrint 55 61)
								;"Mandrake root is used in a variety of spells, mostly for evil purposes.
								;Mandrake must be pulled from a deadman's grave at midnight.  The root is particularly powerful."
								)
							(else (= chatHealer 0)
								(HighPrint 55 62)
								;"I'm sorry, I just don't know much about that.  Perhaps you should ask someone else."
								(event claimed: 1))
						)
						(if chatHealer
							(SolvePuzzle POINTS_TALKTOHEALER 2)
						)
					)
					((Said 'gave,offer,sell,display,replace>')
						(cond 
							((Said '/ring')
								(if (ego has: iRing)
									(if
									(> (+ (* (ego y?) 8) (* (ego x?) 3) -1822) 0)
										(PrintNotCloseEnough)
									else
										(Bset RETURNED_RING)
										(ego use: iRing)
										(SolvePuzzle POINTS_RETURNRING 10)
										(HighPrint 55 63)
										;"Oh, thank you for finding my ring.  How I've missed this.
										;Here are six golds and two Healing Potions for your reward!"
										(= [invNum iGold] (+ [invNum iGold] 6))
										(ego get: iHealingPotion)
										(ego get: iHealingPotion)
										(if (<= (+ (* (ego y?) 3) (ego x?) -615) 0)
											(= theCycles 20)
											(healer setScript: healerPleased)
										)
										(= healerKiss TRUE)
									)
								else
									(PrintDontHaveIt)
								)
							)
							((Said '/flower')
								(if (ego has: iFlowers)
									(if (> numFlowers 2)
										(HighPrint 55 64)
										;Thank you, but I have all the flowers I need.
									else
										(= theCycles 10)
										(healer setScript: healerPleased)
										(HighPrint 55 65)
										;"Thank you.  I often use flowers from Erana's Peace in potion making.  Here are your silvers."
										;You put them away.
										(Purchase -5)
										(Bset DISPEL_GAVE_FLOWERS)
										(DispelIngredients)
										(++ numFlowers)
										(ego use: iFlowers 5)
										(SolvePuzzle POINTS_SELLFLOWERS 1)
									)
								else
									(HighPrint 55 66)
									;You don't have any.
								)
							)
							((Said '/mushroom')
								(if (ego has: iMushroom)
									(cond 
										((< 2 numMushrooms)
											(HighPrint 55 67)
											;Thank you, but I have enough mushrooms.
											)
										((Btst fHaveToadstools)
											(HighPrint 55 68)
											;You have ruined the mushrooms with toadstools.  I can not buy your mushrooms.
											)
										(else
											(= theCycles 10)
											(healer setScript: healerPleased)
											(TimePrint 8 55 69)
											;"These are very nice.  I'll dry them and grind them into a powder.
											;Let me think...oh yes! I said these are worth a gold.  Here you are."
											;You put it away.
											(Purchase -10)
											(++ numMushrooms)
											(ego use: iMushroom 3)
											(SolvePuzzle POINTS_SELLMUSHROOM 1)
										)
									)
								else
									(HighPrint 55 66)
									;You don't have any.
								)
							)
							((Said '/claw,cheetaur')
								(if (= clawsSold (ego has: iCheetaurClaw))
									(= theCycles 10)
									(healer setScript: healerPleased)
									(TimePrint 8 55 70)
									;"Do you mean to tell me that you actually managed to kill a Cheetaur?  You're quite a hero, aren't you?  Here is your money.
									;You might think about purchasing a Healing Potion in case you have to fight such monsters again."
									(ego get: iGold (mod (* clawsSold 5) 10))
									(ego get: iSilver (/ (* clawsSold 5) 10))
									(ego use: iCheetaurClaw clawsSold)
									(SolvePuzzle POINTS_SELLCHEETAURCLAW 2)
								else
									(HighPrint 55 66)
								)
							)
							((Said '/beard,troll')
								(if (ego has: iTrollBeard)
									(= theCycles 10)
									(healer setScript: healerPleased)
									(TimePrint 8 55 71)
									;"Don't tell me you actually killed a Troll?  Why, you are really amazing.
									;I haven't had any Troll's Beard for a long time.  Here are two Healing Potions for the beard."
									;You put them away.
									(ego use: iTrollBeard)
									(ego get: iHealingPotion)
									(ego get: iHealingPotion)
									(SolvePuzzle POINTS_SELLTROLLBEARD 2)
								else
									(PrintDontHaveIt)
								)
							)
							((Said '/dust[<faerie]')
								(if (ego has: iFairyDust)
									(= theCycles 10)
									(healer setScript: healerPleased)
									(if (and (not (Btst DISPEL_HEALER_MAKING_POTION)) (Btst DISPEL_HEALER_KNOWS_RECIPE))
										(HighPrint 55 72)
										;"So, you say this is one of the ingredients for the Dispel Potion.
										;Well, I had better get started making it."
									else
										(HighPrint 55 73)
										;"Thank you.  I'm sure that I'll find a good use for Fairy Dust."
									)
									(ego use: iFairyDust)
									(Bset DISPEL_GAVE_DUST)
									(SolvePuzzle POINTS_GIVEFAIRYDUST 2)
									(DispelIngredients)
								else
									(PrintDontHaveIt)
								)
							)
							((Said '/water[<floating]')
								(cond 
									((not (ego has: iWater)) (PrintDontHaveIt))
									(numWater
										(HighPrint 55 74)
										;"I don't need any more Flying Water.
										;Thank you anyway."
										;
										)
									((not (Btst DISPEL_HEALER_KNOWS_RECIPE))
										(HighPrint 55 75)
										;"Thank you for the offer, but I really don't need any water."
										)
									((not (Btst fHaveFlyingWater))
										(HighPrint 55 76)
										;The Healer makes some sort of arcane gesture over the flask of water, then looks surprised.
										(HighPrint 55 77)
										;"I'm sorry, but this water does not seem to have any magical potential at all.
										;This can't be the `Flying Water' you described."
										)
									(else
										(if (> [invNum iWater] 1)
											(HighPrint 55 78)
											;The Healer makes some sort of arcane gesture over each flask of water, then smiles.
										else
											(HighPrint 55 79)
											;The Healer makes some sort of arcane gesture over the flask of water, then smiles.
										)
										(= theCycles 10)
										(healer setScript: healerPleased)
										(HighPrint 55 80)
										;"Flying Water.  How clever."
										(++ numWater)
										(ego use: iWater)
										(Bset DISPEL_GAVE_WATER)
										(SolvePuzzle POINTS_GIVEFLYINGWATER 2)
										(DispelIngredients)
									)
								)
							)
							((Said '/fur')
								(if (ego has: iGreenFur)
									(= theCycles 10)
									(healer setScript: healerPleased)
									(if (Btst DISPEL_HEALER_KNOWS_RECIPE)
										(HighPrint 55 81)
										;"Those Meeps sound so interesting.  I'd like to meet them sometime.
										;I'll get to work on that potion of yours."
									else
										(HighPrint 55 82)
										;"Thank you.  I'll save this for a later use."
									)
									(ego use: iGreenFur)
									(Bset DISPEL_GAVE_FUR)
									(SolvePuzzle POINTS_GIVEGREENFUR 2)
									(DispelIngredients)
								else
									(PrintDontHaveIt)
								)
							)
							((Said '/acorn')
								(if (ego has: iAcorn)
									(= theCycles 10)
									(healer setScript: healerPleased)
									(HighPrint 55 83)
									;"So you helped the Dryad, that's nice.  She does keep the forest around here healthy. 
									;So that's how to make a Dispel Potion, is it?  Thanks for letting me know."
									(ego use: iAcorn)
									(Bset DISPEL_GAVE_ACORN)
									(SolvePuzzle POINTS_GIVEMAGICACORN 5)
									(DispelIngredients)
								else
									(PrintDontHaveIt)
								)
							)
							((Said '/bottle')
								(if (ego has: iFlask)
									(= theCycles 6)
									(healer setScript: healerPleased)
									(TimePrint 8 55 84)
									;"Thank you, I always need flasks."
									(ego use: iFlask)
									(ego get: iSilver 1)
								else
									(PrintDontHaveIt)
								)
							)
							((Said '/root,plant[<mandrake]')
								(HighPrint 55 85)
								;I don't have any use for that.
								)
						)
					)
					((Said 'buy,buy,get,get>')
						(cond 
							((Said '/potion[<!*]')
								(HighPrint 55 86)
								;"You'll have to be more specific."
								)
							((Said '/potion<vigor,stamina')
								(BuyPotion iStaminaPotion 20)
								)
							((Said '/potion<heal,healing')
								(BuyPotion iHealingPotion 40)
								)
							((Said '/potion<mana,magic,power')
								(BuyPotion iManaPotion 60)
								)
							((Said '/grease[<ghost,ghoul]')
								(BuyPotion iGhostOil 100)
								)
							((Said '/potion<disenchant')
								(AskAboutDispel)
								)
							((Said '/bottle')
								(HighPrint 55 87)
								;I need all my flasks for potions.  You'll have to get one somewhere else.
								)
							((Said '/root,plant[<mandrake]')
								(HighPrint 55 88)
								;I don't have any.
								)
						)
					)
					((Said 'grab/potion,herb')
						(cond 
							((Btst STOLE_HEALER_POTIONS)
								(HighPrint 55 89)
								;You're getting greedy.  She'll notice if you take any more.
								)
							(healerFacingAway
								(HighPrint 55 90)
								;You carefully grab a couple of Healing Potions and conceal them under your cape.
								(ego get: iHealingPotion 2)
								(Bset STOLE_HEALER_POTIONS))
							(else 
								(HighPrint 55 91)
								;If you're going to steal, at least wait until the Healer is not looking.
								)
						)
					)
					(
					(or (Said 'kill,fight') (Said 'cast/flame,dart,spell'))
					(HighPrint 55 92)
					;You don't really think that is the correct way to actually win this game, do you?
					)
					((Said 'throw')
						(HighPrint 55 93)
						;Please, show some respect.
						)
					((Said 'cast>')
						(= spell (SaidSpell event))
						(if (CastSpell spell)
							(if (== spell DETMAGIC)
								(HighPrint 55 94)
								;There is an aura of magic throughout this small cabin.
							else
								(event claimed: FALSE)
							)
						)
					)
				)
			)
		)
	)
)

(instance kettle of Prop
	(properties
		y 114
		x 209
		view vHealerInside
		cycleSpeed 1
	)
)

(instance bird of Prop
	(properties
		y 82
		x 75
		view vHealer
		loop 8
		cycleSpeed 2
	)
)

(instance rmStuff1 of PicView
	(properties
		y 108
		x 244
		view vHealerInside
		loop 1
		signal ignrAct
	)
)

(instance rmStuff2 of PicView
	(properties
		y 121
		x 242
		view vHealerInside
		loop 1
		cel 1
		signal ignrAct
	)
)

(instance rmStuff3 of PicView
	(properties
		y 138
		x 242
		view vHealerInside
		loop 1
		cel 2
		priority 10
		signal ignrAct
	)
)

(instance rmBin of PicView
	(properties
		y 154
		x 209
		view vHealerInside
		loop 2
		priority 14
		signal ignrAct
	)
)

(instance rmShelf1 of PicView
	(properties
		y 118
		x 150
		view vHealerInside
		loop 3
		signal ignrAct
	)
)

(instance rmShelf2 of PicView
	(properties
		y 135
		x 85
		view vHealerInside
		loop 4
		signal ignrAct
	)
)

(instance rmTable of PicView
	(properties
		y 136
		x 152
		z 1
		view vHealerInside
		loop 5
		priority 11
		signal ignrAct
	)
)

(instance rmLamp of PicView
	(properties
		y 92
		x 222
		view vHealerInside
		loop 6
		signal ignrAct
	)
)

(instance onKettle of RFeature
	(properties
		nsTop 111
		nsLeft 190
		nsBottom 126
		nsRight 207
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onKettle event shiftDown)
				(HighPrint 55 14)
				;Whatever is boiling there is either soup or some sort of potion.
				)
		)
	)
)

(instance onBird of RFeature
	(properties
		nsTop 73
		nsLeft 65
		nsBottom 82
		nsRight 87
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onBird event shiftDown)
				(HighPrint 55 95)
				;Small on a prehistoric scale, but still a pterosaur.
				)
		)
	)
)

(instance onMaps of RFeature
	(properties
		nsTop 85
		nsLeft 72
		nsBottom 134
		nsRight 92
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onMaps event shiftDown)
				(HighPrint 55 96)
				;Cheesecloth.
				)
		)
	)
)

(instance onLadder of RFeature
	(properties
		nsTop 51
		nsLeft 168
		nsBottom 119
		nsRight 181
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onLadder event shiftDown)
				(HighPrint 55 97)
				;Ladder up to the loft where the Healer has her personal quarters.  No, you can't go up the ladder.
				)
		)
	)
)

(instance onTable of RFeature
	(properties
		nsTop 130
		nsLeft 133
		nsBottom 135
		nsRight 173
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onTable event shiftDown)
				(HighPrint 55 98)
				;The makings of various potions.
				)
		)
	)
)

(instance onLitterBox of RFeature
	(properties
		nsTop 109
		nsLeft 96
		nsBottom 124
		nsRight 129
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onLitterBox event shiftDown)
				(HighPrint 55 99)
				;The pterosaur's litter box.
				)
		)
	)
)

(instance onLamp of RFeature
	(properties
		nsTop 83
		nsLeft 219
		nsBottom 91
		nsRight 224
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onLamp event shiftDown)
				(HighPrint 55 100)
				;Iron holder fit for a lit torch.
				)
		)
	)
)

(instance onWindow of RFeature
	(properties
		nsTop 84
		nsLeft 94
		nsBottom 102
		nsRight 131
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onWindow event shiftDown)
				(HighPrint 55 101)
				;The view from the window has been obscured by soot from the fire.
				)
		)
	)
)

(instance onThings of RFeature
	(properties
		nsTop 94
		nsLeft 232
		nsBottom 136
		nsRight 253
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onThings event shiftDown)
				(HighPrint 55 102)
				;This and that.
				)
		)
	)
)

(instance onStores of RFeature
	(properties
		nsTop 86
		nsLeft 143
		nsBottom 118
		nsRight 156
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onStores event shiftDown)
				(HighPrint 55 103)
				;Ingredients.
				)
		)
	)
)

(instance onBins of RFeature
	(properties
		nsTop 144
		nsLeft 183
		nsBottom 155
		nsRight 231
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onBins event shiftDown)
				(HighPrint 55 104)
				;Petrified icicles and greens.
				)
		)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego init: posn: 124 160)
				(healer
					loop: 0
					init:
					posn: 110 145
					setCycle: Walk
					setMotion: MoveTo 107 141 self
				)
			)
			(1
				(if (not (Btst MET_HEALER))
					(HighPrint 55 105)
					;The fragrance of the herbs mingle with some other rather pungent odors as you step into the Healer's house.
					)
				(healer setMotion: MoveTo 139 139 self)
				(ego setMotion: MoveTo 155 160)
			)
			(2
				(if
					(and
						(Btst MET_HEALER)
						(not (Btst DISPEL_HEALER_MAKING_POTION))
						(or (not (Btst DISPEL_LEARNED_RECIPE)) (Btst DISPEL_HEALER_KNOWS_RECIPE))
					)
					(HighPrint 55 106)
					;"Well, what can I do for you this time?"
				)
				(if (not (Btst MET_HEALER))
					(HighPrint 55 107)
					;"Lovely day, isn't it?  My, you look very healthy for an adventurer.  You must be new."
					(HighPrint 55 108)
					;"What can I do for you?  I can sell you potions if you like.
					;I also buy spell components if you are interested in gathering some for me."
					(HighPrint 55 109)
					;"Don't mind me, I always have so much to do around here."
				)
				(if (and (Btst DISPEL_LEARNED_RECIPE) (not (Btst DISPEL_HEALER_KNOWS_RECIPE)))
					(HighPrint 55 110)
					;You say to the Healer "I have been to visit the Dryad of the Woods.
					;She gave me a Magic Acorn and told me the formula for a potion to dispel enchantments!"
					(Bset DISPEL_HEALER_KNOWS_RECIPE)
				)
				(if (Btst DISPEL_HEALER_MAKING_POTION)
					(HighPrint 55 111)
					;"Here is the Potion of Dispel!  Use it by splashing it on the victim of a magic spell.
					;Mind you, this won't work if the spell is caused by a magic item rather than a cast spell."
					(ego get: iDisenchant)
					(Bclr DISPEL_HEALER_MAKING_POTION)
					(Bset OBTAINED_DISPEL_POTION)
					(SolvePuzzle POINTS_GETDISPELPOTION 7)
				)
				(HandsOn)
				(healer setLoop: 5 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(3
				(healer setLoop: 5 setCycle: Forward)
				(healer setScript: healerPuttering)
			)
		)
	)
)

(instance preening of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bird init: cel: 2 setCycle: EndLoop)
				(= cycles (Random 9 27))
			)
			(1 (bird setCycle: EndLoop self))
			(2 (= cycles (Random 100 200)))
			(3 (self changeState: 0))
		)
	)
)

(instance healerPuttering of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 90 200)))
			(1
				(if (> (ego y?) 149)
					(healer
						setLoop: 4
						cel: 2
						cycleSpeed: 1
						moveSpeed: 1
						setCycle: BegLoop self
					)
				else
					(self changeState: 0)
				)
			)
			(2
				(= healerFacingAway 1)
				(switch (= local3 (Random 0 1))
					(0
						(healer
							loop: 1
							setCycle: Walk
							setMotion: MoveTo 87 138 self
						)
					)
					(1
						(healer
							loop: 3
							setCycle: Walk
							setMotion: MoveTo 155 123 self
						)
					)
				)
			)
			(3
				(switch local3
					(0 (healer loop: 3))
					(1 (healer loop: 1))
				)
				(= seconds 3)
			)
			(4
				(= healerFacingAway 0)
				(switch local3
					(0
						(healer
							setLoop: 0
							setCycle: Walk
							setMotion: MoveTo 139 139 self
						)
					)
					(1
						(healer
							setLoop: 2
							setCycle: Walk
							setMotion: MoveTo 139 139 self
						)
					)
				)
			)
			(5
				(healer
					setLoop: 4
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(6
				(healer setLoop: 5 cel: 0 setCycle: Forward)
				(self changeState: 0)
			)
		)
	)
)

(instance healerPleased of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(healer setMotion: 0 loop: 2)
				(= cycles 2)
			)
			(1
				(healer setLoop: 6 cel: 0 setCycle: EndLoop self)
			)
			(2 (= cycles theCycles))
			(3
				(switch local3
					(0
						(healer
							setLoop: -1
							setCycle: Walk
							setMotion: MoveTo 139 139 self
						)
					)
					(1
						(healer
							setLoop: 2
							setCycle: Walk
							setMotion: MoveTo 139 139 self
						)
					)
				)
			)
			(4
				(healer setLoop: 5 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(5
				(healer setLoop: 5 setCycle: Forward)
				(healer setScript: healerPuttering)
			)
		)
	)
)

(instance healerKisses of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if
					(not
						(if
							(and
								(<= 138 (healer x?))
								(<= (healer x?) 140)
								(<= 138 (healer y?))
							)
							(<= (healer y?) 140)
						)
					)
					(switch local3
						(0
							(healer
								setLoop: -1
								setCycle: Walk
								illegalBits: 0
								setMotion: MoveTo 139 139 self
							)
						)
						(1
							(healer
								setLoop: 2
								setCycle: Walk
								setMotion: MoveTo 139 139 self
							)
						)
					)
				else
					(= cycles 2)
				)
			)
			(1
				(healer setLoop: 2)
				(ego illegalBits: 0 setMotion: MoveTo 171 152 self)
			)
			(2
				(ego setLoop: 1)
				(= cycles 2)
			)
			(3
				(HighPrint 55 112)
				;"Thank you so much.  Oh, I'm so happy to have my ring back!"
				(healer loop: 7 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(4
				(healer setCycle: EndLoop)
				(ego
					view: vEgoFall
					loop: 0
					setPri: 11
					setCycle: EndLoop
					setMotion: MoveTo 166 144 self
				)
			)
			(5
				(kiss init: play:)
				(= seconds 3)
			)
			(6
				(healer loop: 2)
				(ego
					cycleSpeed: 1
					setCycle: BegLoop
					setMotion: MoveTo 166 155 self
				)
			)
			(7
				(ego view: vEgo setLoop: loopN setMotion: MoveTo 163 163)
				(healer
					setLoop: -1
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 153 126 self
				)
			)
			(8
				(healer
					cycleSpeed: 0
					moveSpeed: 0
					setMotion: MoveTo 197 141 self
				)
			)
			(9
				(healer loop: 1)
				(ego view: vEgoStanding loop: loopE)
				(= cycles 2)
			)
			(10
				(HighPrint 55 113)
				;You put the reward away and tell her she is welcome as you leave to avoid being kissed again.
				(= cycles 2)
			)
			(11
				(ego
					view: vEgoBigGrin
					setLoop: loopE
					cycleSpeed: 1
					moveSpeed: 1
					setCycle: EndLoop self
				)
			)
			(12
				(ego
					setLoop: loopW
					cycleSpeed: 1
					moveSpeed: 1
					setCycle: Forward
					setMotion: MoveTo 100 155 self
				)
			)
		)
	)
)

(instance waitForHealer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 10))
			(1
				(HighPrint 55 114)
				;"The Dispel Potion will take a while to make.  Why don't you come back later?"
				(self dispose:)
			)
		)
	)
)

(instance kiss of Sound
	(properties
		number 42
		priority 5
	)
)
