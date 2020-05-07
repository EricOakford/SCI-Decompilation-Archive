;;; Sierra Script 1.0 - (do not remove this comment)
(script# 331)
(include game.sh)
(use Main)
(use barLook)
(use String)
(use LoadMany)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm331 0
	DB 1
	bartender 2
	smoke 3
	barSound 4
	head 5
	trap 6
	crusher 7
	bartenderScript 8
)

(local
	nearBarstool
	bartenderAttention
	pickedUpNote
	local3
	local4
	bartenderSuspicious
	crusherAnnoyed
	local7
	local8
	alePrice =  1
	trollSweatPrice =  5
	dragonBreathPrice =  25
)

(enum 1
	noteSUSPICIOUS
	noteSUSPICIOUS_READ
	noteARCHERY
	noteARCHERY_READ
	noteNOSY
	noteNOSY_READ
)

(procedure (BuyDrink price)
	(if (Purchase price)
		(HighPrint 331 68)
		;You thank the bartender and pay him for your beverage.
	else
		(NotEnoughMoney)
	)
)

(procedure (NotEnoughMoney)
	(HighPrint 331 69)
	;You inform the bartender that you seem to be temporarily strapped for funds.
	(HighPrint 331 70)
	;He says, "Hey Crusher! Get this deadbeat bum outta here!"
	(crusher setScript: (ScriptID 337 0))
)

(instance barSound of Sound
	(properties
		number 44
	)
)

(instance rm331 of Room
	(properties
		picture 331
		style WIPELEFT
	)
	
	(method (init)
		(Load PICTURE 400)
		(LoadMany VIEW vBarInside vCrusher vBartenderPouring vBartender vEgoInsideBar)
		(LoadMany SOUND (SoundFX 43) (SoundFX 44))
		(LoadMany SCRIPT 339 335 336 337 338 STRING)
		(Load TEXT 257)
		(super init:)
		(mouseDownHandler add: self)
		(self setLocales: TOWN)
		(StatusLine enable:)
		(bartender init:)
		(crusher init:)
		(Bclr OBTAINED_BAR_NOTE)
		(cond 
			(
				(or
					(>= barNote noteNOSY_READ)
					(and (> barNote 0) (not (Btst BEAR_GONE))) ;EO: By 1.200, another note was added into the beginning of the game.
				)
				(Bset OBTAINED_BAR_NOTE)
			)
			((Btst DEFEATED_BRUTUS) (= barNote noteNOSY_READ) (Bset OBTAINED_BAR_NOTE))
			((and (Btst SPIED_THIEVES) (== barNote noteARCHERY)) (= barNote noteARCHERY_READ))
			((and (Btst BEAR_GONE) (<= barNote noteSUSPICIOUS)) (= barNote noteSUSPICIOUS_READ))
			((== barNote noteARCHERY) (Bset OBTAINED_BAR_NOTE))
		)
		(if (not (Btst OBTAINED_BAR_NOTE)) (curRoom setFeatures: onPaper))
		(curRoom
			setFeatures:
				onButcher
				onBaker
				onGoon
				onTrapDoor
				onTableBottom
				onTableTop
				onStool1
				onStool2
				onStool3
				onKeg1
				onKeg2
				onKeg3
				onFloor
		)
		(Bset ORDERED_DRINK)
		(Bclr HERO_SITTING)
		(= drinkInHand drinkNothing)
		(= drinkOrdered drinkNothing)
		(= numberOfAlesDrunk 0)
		(head init: stopUpd: hide:)
		(NormalEgo)
		(ego init:)
		(addToPics
			add: drag lfTap rtTap ctrStool rtStool guck puddle barb players
			eachElementDo: #init
			doit:
		)
		(if (not (Btst OBTAINED_BAR_NOTE)) (paper init: stopUpd:))
		(smoke init: setPri: 4 setCycle: Forward startUpd:)
		(ooze init: setPri: 7 setScript: oozeScript)
		(trap init: setPri: 5 ignoreActors: stopUpd:)
		(DB init: setPri: 9 posn: 159 68 stopUpd:)
		(barSound number: (SoundFX 44) init:)
		(switch prevRoomNum
			(330
				(ego posn: 156 188 setMotion: MoveTo 156 175)
			)
			(332
				(ego posn: 120 150 loop: 0)
			)
			(else 
				(Bset VISITED_TAVERN_INSIDE)
				(ego posn: 156 188 setMotion: MoveTo 156 175)
			)
		)
		(if (not (Btst VISITED_TAVERN_INSIDE)) (self setScript: rm331Script))
	)
	
	(method (doit)
		(if (> (ego y?) 188) (curRoom newRoom: 330))
		(if (> bartenderSuspicious 0) (-- bartenderSuspicious))
		(cond 
			(
			(and (ego inRect: 171 140 291 183) (not local3)) (= local3 1) (ego setScript: cardScript))
			(
			(and (not (ego inRect: 171 140 291 183)) local3) (= local3 0) (ego setScript: 0))
		)
		(super doit:)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(Bset VISITED_TAVERN_INSIDE)
		(super dispose:)
		(LoadMany FALSE 335 336 337 338 339)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((!= (event type?) saidEvent))
			((Said 'get,lockpick[<up]/note,note')
				(if (ego inRect: 128 106 171 130)
					(if (Btst OBTAINED_BAR_NOTE)
						(HighPrint 331 0)
						;There's nothing there.
					else
						(paper hide:)
						(ego get: iPaper)
						(Bset OBTAINED_BAR_NOTE)
						(= pickedUpNote TRUE)
						(++ barNote)
						(SolvePuzzle POINTS_PICKUPNOTE 2)
						(HighPrint 331 1)
						;You pick up the note.
						(rm331Script changeState: 2)
					)
				else
					(HighPrint 331 2)
					;Where?
				)
			)
			((Said 'look,read/note,note')
				(if pickedUpNote
					(rm331Script changeState: 2)
				else
					(HighPrint 331 3)
					;You don't have anything like that to look at.
				)
			)
			((Said 'look>') (SaidTavern event))
			((Said 'spit')
				(HighPrint 331 4)
				;The bartender frowns on customers who spit in his establishment.
				)
			((Said 'sat')
				(cond 
					((ego inRect: 192 109 237 130) (TavernLookAtStool TRUE))
					((ego inRect: 128 106 171 130)
						(if (Btst HERO_SITTING)
							(HighPrint 331 5)
							;You're already sitting down.
						else
							(ego setScript: (ScriptID 338 0))
						)
					)
					(nearBarstool
						(HighPrint 331 6)
						;You're not close enough to a barstool.
						)
					(else (HighPrint 331 7))
				)
			)
			((Said 'run,walk,sneak')
				(if (Btst HERO_SITTING)
					(HighPrint 331 8)
					;Separate yourself from that stool first.
				else
					(event claimed: FALSE)
				)
			)
			(
				(or
					(Said 'get<up,down,off[/barstool,chair]')
					(Said 'stand[<up]')
				)
				(if (Btst HERO_SITTING)
					(cond 
						((and (== drinkInHand drinkNothing) (== drinkOrdered drinkNothing)) (ego setScript: (ScriptID 338 1)))
						((and (== drinkInHand drinkNothing) (> drinkOrdered drinkNothing))
							(HighPrint 331 9)
							;Wait for the drink you just ordered.
							)
						(else
							(HighPrint 331 10)
							;Go ahead and drink up, first.  You paid for it!
							)
					)
				else
					(HighPrint 331 11)
					;But you're not sitting down!
				)
			)
			((Said 'display,made/sign[<thief]')
				(if bartenderAttention
					(HighPrint 331 12)
					;"You'd better talk to Crusher."
					else
					(HighPrint 331 13)
					;You'll have to get the bartender's attention first.
					)
				)
			(
			(or (Said 'order,buy,get/drink') (Said 'ask//drink'))
				(if bartenderAttention
					(if (not (Btst HERO_SITTING))
						(HighPrint 331 14)
						;The bartender bellows, "Sit down first!"
					else
						(HighPrint 331 15)
						;"OK. What'll you have?"
					)
				else
					(HighPrint 331 13)
					;You'll have to get the bartender's attention first.
				)
			)
			(
			(or (Said '[order,buy,get]/ale') (Said 'ask//ale'))
				(if bartenderAttention
					(cond 
						((not (Btst HERO_SITTING))
							(HighPrint 331 14)
							;The bartender bellows, "Sit down first!"
							)
						((> drinkInHand drinkNothing)
							(HighPrint 331 16)
							;Drink up before you order anything else!
							)
						(else
							(= drinkOrdered drinkAle)
							(HighPrint 331 17)
							;The bartender says, "One well-aged house brew, coming up!"
							(bartenderScript changeState: 9)
						)
					)
				else
					(HighPrint 331 18)
					;You'll need the bartender's help on that one.
				)
			)
			(
				(or
					(Said '[order,buy,get]/sweat[<troll]')
					(Said 'ask//sweat[<troll]')
				)
				(if bartenderAttention
					(cond 
						((not (Btst HERO_SITTING))
							(HighPrint 331 14)
							;The bartender bellows, "Sit down first!"
							)
						((> drinkInHand 0)
							(HighPrint 331 16)
							;Drink up before you order anything else!
							)
						(else
							(= drinkOrdered drinkSweat)
							(HighPrint 331 19)
							;The bartender says, "One mug of my fine Troll's Sweat, coming up!"
							(bartenderScript changeState: 9)
						)
					)
				else
					(HighPrint 331 20)
					;Sounds potent, but you'd better go see the bartender if you want some.
				)
			)
			(
				(or
					(Said '[order,buy,get]/breath[<dragon]')
					(Said 'ask//breath[<dragon]')
				)
				(if bartenderAttention
					(cond 
						((not (Btst HERO_SITTING))
							(HighPrint 331 14)
							;The bartender bellows, "Sit down first!"
							)
						((> drinkInHand drinkNothing)
							(HighPrint 331 16)
							;Drink up before you order anything else!
							)
						(else
							(= drinkOrdered drinkBreath)
							(HighPrint 331 21)
							;The bartender is emphatic as he tells you, "If you want a mug of Dragon's Breath, house rules say it'll have to be cash up front!"
							(if (Purchase dragonBreathPrice)
								(HighPrint 331 22)
								;You cough up the cash.
								(HighPrint 331 23)
								;"Thanks, buddy!"
								(HighPrint 331 24)
								;"Hey, Crusher! Our friend here wants Dragon's Breath!"
								(bartenderScript changeState: 9)
							else
								(= drinkOrdered drinkNothing)
								(HighPrint 331 25)
								;You tell the bartender that you don't have enough working capital at this point in time.
								(HighPrint 331 26)
								;He says, "Too bad, buddy. You're missing a real treat, for sure!"
							)
						)
					)
				else
					(HighPrint 331 27)
					;Well then!  Belly up to the bar and get some Dragon's Breath!
				)
			)
			((Said 'pay') (if bartenderAttention
					(HighPrint 331 28)
					;For what?
					else
					(HighPrint 331 29)
					;The bartender can't reach your money. You're not close enough.
					)
				)
			((Said 'drink>')
				(cond 
					((Said '/ale')
						(switch drinkInHand
							(drinkNothing
								(HighPrint 331 30)
								;You'll need to buy one.
								)
							(drinkAle
								(++ numberOfAlesDrunk)
								(ego setScript: (ScriptID 336 0))
							)
							(else
								(HighPrint 331 31)
								;That ain't beer!
								)
						)
					)
					((Said '/sweat[<troll]')
						(switch drinkInHand
							(drinkNothing
								(HighPrint 331 30)
								;You'll need to buy one.
								)
							(drinkSweat
								(ego setScript: (ScriptID 336 0))
							)
							(else
								(HighPrint 331 32)
								;That isn't Troll's Sweat!
								)
						)
					)
					((Said '/breath[<dragon]')
						(switch drinkInHand
							(drinkNothing
								(HighPrint 331 30)
								;You'll need to buy one.
								)
							(drinkBreath
								(ego setScript: (ScriptID 336 0))
							)
							(else
								(HighPrint 331 33)
								;That isn't Dragon's Breath!
								)
						)
					)
					((Said '[/!*]')
						(switch drinkInHand
							(drinkNothing
								(HighPrint 331 30)
								;You'll need to buy one.
								)
							(drinkAle
								(++ numberOfAlesDrunk)
								(ego setScript: (ScriptID 336 0))
							)
							(else 
								(ego setScript: (ScriptID 336 0))
							)
						)
					)
				)
			)
		)
	)
)

(instance rm331Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 10))
			(1 (TavernLookAround 1 1))
			(2
				(HighPrint 331 34)
				;You smooth out the piece of paper and read:
				(cond 
					((== barNote noteSUSPICIOUS) ;EO: This note appears at the beginning of the game. It was added in by 1.200
						(HighPrint 331 35)
						;"B. - He's starting to act suspicious.  Better save this drop for emergencies. - B."
						)
					((== barNote noteARCHERY)	;EO: This note appears when the bear is no longer in the cave
						(HighPrint 331 36)
						;"B. - Meet me at the old archery range south of town at noon -- urgent! - B."
						)
					(else ;EO: This note appears if you spied on Bruno and Brutus, but Brutus is still alive
						(HighPrint 331 37)
						;"B. - That new adventurer is getting too nosy.  Deal with him. - B."
						(HighPrint 331 38)
						;Oh, isn't that nice!  Sounds like you're going to be invited to a card game.
						(= barNote noteNOSY_READ))
				)
			)
		)
	)
)

(instance oozeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ooze stopUpd:)
				(= cycles (Random 30 90))
			)
			(1
				(= state -1)
				(ooze setCycle: EndLoop self)
			)
		)
	)
)

(instance bartender of Actor
	(properties
		view vBartender
		loop 2
		illegalBits $0000
	)
	
	(method (init)
		(self posn: 162 80 stopUpd:)
		(self setScript: bartenderScript)
		(super init:)
	)
	
	(method (doit)
		(cond 
			(
			(and (< (ego y?) 113) (not (Btst HERO_SITTING)) (not nearBarstool)) (= nearBarstool TRUE) (bartenderScript changeState: 6))
			((and (> (ego y?) 113) nearBarstool) (= nearBarstool FALSE) (bartenderScript changeState: 0))
			((or nearBarstool (Btst HERO_SITTING)) (= bartenderAttention TRUE))
			((and (not nearBarstool) (not (Btst HERO_SITTING))) (= bartenderAttention FALSE))
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(mouseDown
				(if (MouseClaimed bartender event shiftDown)
					(HighPrint 331 39)
					;The bartender looks tough from his crewcut to his boots. The only thing soft about him is his tattoo, which says "MAMA".
				)
			)
			(saidEvent
				(if bartenderAttention
					(cond 
						((Said 'affirmative,please')
							(if (> bartenderSuspicious 0)
								(HighPrint 331 40)
								;"Crusher, escort this squealer off the premises!"
								(crusher setScript: (ScriptID 337 0))
							else
								(HighPrint 331 41)
								;"Yes...what?"
							)
						)
						((Said 'n')
							(if (> bartenderSuspicious 0)
								(HighPrint 331 42)
								;"OK."
							else
								(HighPrint 331 43)
								;"No...what?"
							)
						)
						((Said 'chat>')
							(cond 
								((Said '/man,bartender')
									(HighPrint 331 44)
									;The bartender shows little interest in small talk.
									)
								((Said '/bum,barber')
									(HighPrint 331 45)
									;The drunken barber at the end of the bar isn't in any shape to talk, at least not coherently.
									)
							)
						)
						((Said 'ask>')
							(cond 
								(
								(or (Said '//thief') (Said '//club<thief[<about]'))
								(HighPrint 331 46)
								;"Hey! This is an honest establishment!  You want me to ask Crusher?"
								(= bartenderSuspicious 40)
								)
								((Said '//drink,cost')
									(HighPrint 331 47)
									;"We've got some of the finest ale in the valley, only 1 silver."
									(HighPrint 331 48)
									;"Our world-famous Troll's Sweat is always fresh and it's only 5 silvers."
									(HighPrint 331 49)
									;"The Dragon's Breath is our specialty, and it's Crusher's personal favorite.
									;It's very hard to come by, so it'll cost you 25 silver, cash up front."
									)
								((Said '//sweat[<troll,about]')
									(HighPrint 331 48)
									;"Our world-famous Troll's Sweat is always fresh and it's only 5 silvers."
									)
								((Said '//ale')
									(HighPrint 331 47)
									;"We've got some of the finest ale in the valley, only 1 silver."
									)
								((Said '//breath[<dragon,about]')
									(HighPrint 331 49)
									;"The Dragon's Breath is our specialty, and it's Crusher's personal favorite.
									;It's very hard to come by, so it'll cost you 25 silver, cash up front."
									)
								((Said '//mama,tattoo')
									(HighPrint 331 50)
									;Look buddy...don't get personal!
									)
								((Said '//bouncer,goon,ogre')
									(HighPrint 331 51)
									;"He don't like his personal affairs discussed. My advice is not to do anything that'll get him upset."
									)
								((Said '//*')
									(HighPrint 331 52)
									;"This is a bar. I serve drinks. You want a drink, order one. You want answers to stupid questions, GET OUT!"
									)
							)
						)
					)
				)
			)
		)
	)
)

(instance cardScript of Script
	(properties)
	
	(method (changeState newState &tmp [number 10])
		(switch (= state newState)
			(0 (= cycles (Random 20 40)))
			(1
				(HighPrint (Format @number 331 58 (Random 1 1000)
						;"Got any %d's?"
						)
					)
				(= cycles (Random 10 20))
			)
			(2
				(HighPrint 331 59)
				;"Go FISH!"
				(self changeState: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'ask>')
						(cond 
							((Said '//fish,trout,south,lake,river,water')
								(if local4
									(HighPrint 331 53)
									;The baker says, "There's a lake to the south which has a huge fish, but its pretty dangerous."
									(= local4 0)
								else
									(HighPrint 331 54)
									;The butcher says, "There's a river to the south which has some good-sized trout.
									(= local4 1)
								)
							)
							((Said '//*')
								(HighPrint 331 55)
								;They don't seem to be paying any attention to you.
								)
						)
					)
					(
					(Said 'chat/man,man,butcher,baker,player,gambler')
					(HighPrint 331 56)
					;These guys are so intent on their game that they don't want to open their mouths on any other subject.
					)
					((Said 'look/card,deck,player,butcher,baker')
						(HighPrint 331 57)
						;It looks like these guys aren't playing with a full deck.
					)
				)
			)
		)
	)
)

(instance crusher of Actor
	(properties
		view vCrusher
	)
	
	(method (init)
		(super init:)
		(keyDownHandler add: self)
		(self posn: 92 153 stopUpd:)
	)
	
	(method (handleEvent event &tmp [str 10])
		(return
			(cond 
				((super handleEvent: event))
				((not (ego inRect: 0 125 140 170)))
				(
					(LookFor event
						(Format @str 257 thievesPassword)
					)
					(if (and (not [egoStats STEALTH]) (not [egoStats PICK])) ;this is to prevent those without thief skills from entering the guild
						(HighPrint 331 60)
						;The bartender calls to Crusher:  "Hey, he's not one of us!  Take care of him!"
						(self setScript: (ScriptID 337 0))
					else
						(self setScript: (ScriptID 337 1))
					)
				)
				((!= (event type?) saidEvent))
				((Said 'display,made/sign[<thief]')
					(HighPrint 331 61)
					;"Oh yeah?  What's the password?"
					)
				((Said 'ask//thief,club')
					(HighPrint 331 62)
					;Uh oh!  It looks like you got Crusher upset!
					(self setScript: (ScriptID 337 0)))
				((Said '[say,gave,use]/password')
					(HighPrint 331 63)
					;"Oh yeah?  So what is it?"
					)
				((Said '[say]/swordfish')
					(HighPrint 331 64)
					;Good idea, but you're in the wrong movie.
					(self setScript: (ScriptID 337 0)))
				(
				(or (Said 'chat/man,goon,bouncer') (Said 'ask//*'))
					(switch crusherAnnoyed
						(0
							(HighPrint 331 65)
							;The goon seems to be ignoring you.
							)
						(1
							(HighPrint 331 66)
							;As you ask him questions, the goon's eyes darken.
							)
						(2
							(HighPrint 331 67)
							;Uh oh! It looks like you got Crusher upset!
							(self setScript: (ScriptID 337 0))
						)
					)
					(++ crusherAnnoyed)
				)
			)
		)
	)
)

(instance bartenderScript of Script
	(properties)
	
	(method (doit)
		(cond 
			((> local7 1)
				(-- local7)
				(if local8
					((ScriptID 331 2)
						posn: ((ScriptID 331 2) x?) (- ((ScriptID 331 2) y?) 2)
					)
				else
					((ScriptID 331 2)
						posn: ((ScriptID 331 2) x?) (+ ((ScriptID 331 2) y?) 2)
					)
				)
			)
			((== local7 1)
				(= local7 0)
				(self cue:)
				(if local8 (= local8 0) else (= local8 1))
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 20 80)))
			(1
				(= cycles 0)
				((ScriptID 331 2)
					setCycle: Walk
					loop: 0
					setMotion: MoveTo 195 80 self
				)
			)
			(2
				((ScriptID 331 2) loop: (Random 2 3) stopUpd:)
				(= cycles (Random 50 100))
			)
			(3
				((ScriptID 331 2)
					loop: 1
					cel: -1
					setMotion: MoveTo 124 80 self
				)
			)
			(4
				((ScriptID 331 2) loop: (Random 2 3) stopUpd:)
				(= cycles (Random 50 100))
			)
			(5 (self changeState: 1))
			(6
				(= cycles 0)
				(HandsOff)
				(if (> ((ScriptID 331 2) distanceTo: ego) 15)
					((ScriptID 331 2)
						loop: (if (< (ego x?) ((ScriptID 331 2) x?)) 1 else 0)
						cel: -1
						setMotion:
							MoveTo
							(if (Btst HERO_SITTING) (+ (ego x?) 20) else (ego x?))
							80
							self
					)
				else
					(self cue:)
				)
			)
			(7
				((ScriptID 331 2) loop: 2 stopUpd:)
				(if (Btst HERO_SITTING) ((ScriptID 331 5) setCel: 2))
				(= cycles 2)
			)
			(8
				(HighPrint 331 71)
				;"Whaddaya want?"
				(if (Btst HERO_SITTING) (User canInput: TRUE) else (HandsOn))
			)
			(9
				(User canInput: FALSE)
				(if (== (ego loop?) 3)
					(ego loop: 2 cel: 0 stopUpd:)
					((ScriptID 331 5) show:)
				)
				(switch drinkOrdered
					(drinkSweat
						((ScriptID 331 2)
							setLoop: (if (Btst ORDERED_DRINK) 1 else 6)
							setCycle: Walk
							setMotion: MoveTo 124 80 self
						)
					)
					(else 
						((ScriptID 331 2)
							setLoop: (if (Btst ORDERED_DRINK) 0 else 5)
							setCycle: Walk
							setMotion: MoveTo 195 80 self
						)
					)
				)
			)
			(10
				(Bclr ORDERED_DRINK)
				((ScriptID 331 2) setLoop: 4 cel: 0 setPri: 5)
				(= local7 7)
			)
			(11
				((ScriptID 331 2) cel: 1)
				(= local7 8)
			)
			(12
				(switch drinkOrdered
					(drinkAle (self cue:))
					(drinkSweat (self cue:))
					(drinkBreath
						(User canInput: TRUE)
						((ScriptID 331 1) setScript: (ScriptID 335 0))
					)
				)
			)
			(13
				((ScriptID 331 2)
					posn: (if (== drinkOrdered drinkSweat) 124 else 195) 80
					setLoop: (if (== drinkOrdered drinkSweat) 6 else 5)
					cel: 0
				)
				(= cycles 2)
			)
			(14
				((ScriptID 331 2) setLoop: 3)
				(= cycles 20)
			)
			(15
				((ScriptID 331 2)
					setLoop: (if (== drinkOrdered drinkSweat) 5 else 6)
					setPri: -1
					setMotion:
						MoveTo
						(if (== drinkOrdered drinkSweat)
							(+ (ego x?) 10)
						else
							(+ (ego x?) 20)
						)
						80
						self
				)
			)
			(16
				((ScriptID 331 2)
					setLoop: (if (== drinkOrdered drinkSweat) 0 else 1)
					cel: 0
				)
				(HighPrint 331 72)
				;"There ya go!"
				(User canInput: TRUE)
				(if (< drinkOrdered drinkBreath)
					(switch drinkOrdered
						(drinkAle (BuyDrink alePrice))
						(drinkSweat (BuyDrink trollSweatPrice))
					)
				)
				(= drinkInHand drinkOrdered)
				(= drinkOrdered drinkNothing)
				(= cycles 5)
			)
			(17
				(if (Btst HERO_SITTING)
					(ego loop: 3 cel: 0)
					((ScriptID 331 5) hide:)
				)
				((ScriptID 331 2)
					setLoop: -1
					setMotion: MoveTo 180 80 self
				)
			)
			(18
				((ScriptID 331 2) loop: 2 stopUpd:)
			)
		)
	)
)

(instance drag of PicView
	(properties
		y 56
		x 160
		view vBarInside
	)
)

(instance lfTap of PicView
	(properties
		y 72
		x 125
		view vBarInside
		cel 1
	)
)

(instance rtTap of PicView
	(properties
		y 72
		x 195
		view vBarInside
		cel 1
	)
)

(instance ctrStool of PicView
	(properties
		y 111
		x 149
		view vBarInside
		loop 3
	)
)

(instance rtStool of PicView
	(properties
		y 113
		x 216
		view vBarInside
		loop 3
	)
)

(instance guck of PicView
	(properties
		y 89
		x 214
		view vBarInside
		loop 3
		cel 3
		priority 8
	)
)

(instance puddle of PicView
	(properties
		y 116
		x 222
		view vBarInside
		loop 3
		cel 2
	)
)

(instance barb of PicView
	(properties
		y 113
		x 108
		view vBarInside
		loop 3
		cel 1
	)
)

(instance players of PicView
	(properties
		y 172
		x 224
		view vBarInside
		loop 5
	)
)

(instance paper of View
	(properties
		y 111
		x 145
		view vBarInside
		cel 3
	)
)

(instance smoke of Prop
	(properties
		y 45
		x 160
		view vBarInside
		loop 1
		cycleSpeed 1
	)
)

(instance ooze of Prop
	(properties
		y 91
		x 222
		view vBarInside
		loop 4
		cycleSpeed 2
	)
)

(instance trap of Prop
	(properties
		y 155
		x 92
		view vBarInside
		loop 2
		cycleSpeed 2
	)
)

(instance DB of Prop
	(properties
		view vBartenderPouring
		loop 3
		cel 12
		cycleSpeed 1
	)
)

(instance head of View
	(properties
		y 73
		x 150
		view vEgoInsideBar
		loop 2
		cel 3
	)
)

(instance onPaper of RFeature
	(properties
		nsTop 108
		nsLeft 141
		nsBottom 113
		nsRight 148
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onPaper event shiftDown)
				(HighPrint 331 73)
				;You see a crumpled piece of paper under the stool.
				)
		)
	)
)

(instance onButcher of RFeature
	(properties
		nsTop 126
		nsLeft 197
		nsBottom 144
		nsRight 215
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onButcher event shiftDown) (if (Btst HERO_SITTING) (head setCel: 2))
				(HighPrint 331 74)
				;The man on the west side of the table is wearing a blood-stained apron, like a butcher.
				)
		)
	)
)

(instance onBaker of RFeature
	(properties
		nsTop 117
		nsLeft 238
		nsBottom 145
		nsRight 252
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onBaker event shiftDown) (if (Btst HERO_SITTING) (head setCel: 2))
				(HighPrint 331 75)
				;The man on the east has on a baker's hat.
				)
		)
	)
)

(instance onGoon of RFeature
	(properties
		nsTop 108
		nsLeft 75
		nsBottom 140
		nsRight 93
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onGoon event shiftDown)
				(HighPrint 331 76)
				;This goon looks really tough and mean. He's not someone to pick a fight with.
				)
		)
	)
)

(instance onTableTop of RFeature
	(properties
		nsTop 146
		nsLeft 196
		nsBottom 156
		nsRight 247
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onTableTop event shiftDown)
				(HighPrint 331 77)
				;Looks like some kind of card game.
				)
		)
	)
)

(instance onTrapDoor of RFeature
	(properties
		nsTop 139
		nsLeft 65
		nsBottom 155
		nsRight 96
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onTrapDoor event shiftDown)
				(HighPrint 331 78)
				;There appears to be a trapdoor beneath the goon, but he's blocking it very effectively.
				)
		)
	)
)

(instance onTableBottom of RFeature
	(properties
		nsTop 163
		nsLeft 209
		nsBottom 170
		nsRight 237
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onTableBottom event shiftDown)
				(HighPrint 331 79)
				;Someone has carved into the bottom of the table " G. MEISTER slept here".
				)
		)
	)
)

(instance onStool1 of RFeature
	(properties
		nsTop 70
		nsLeft 101
		nsBottom 106
		nsRight 113
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onStool1 event shiftDown) (if (Btst HERO_SITTING) (head setCel: 1))
				(HighPrint 331 80)
				;So this is where the barber goes to lunch! The guy's had so much liquid diet, he looks embalmed.
				)
		)
	)
)

(instance onStool2 of RFeature
	(properties
		nsTop 85
		nsLeft 142
		nsBottom 107
		nsRight 152
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onStool2 event shiftDown)
				(if (Btst HERO_SITTING)
					(HighPrint 331 81)
					;Yup, You're sitting on a stool.
				else
					(HighPrint 331 7)
					;Looks like the only place to sit is at the bar.
				)
			)
		)
	)
)

(instance onStool3 of RFeature
	(properties
		nsTop 87
		nsLeft 210
		nsBottom 116
		nsRight 226
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onStool3 event shiftDown)
				(HighPrint 331 82)
				;Not a pleasant sight!
				)
		)
	)
)

(instance onKeg1 of RFeature
	(properties
		nsTop 42
		nsLeft 114
		nsBottom 76
		nsRight 136
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onKeg1 event shiftDown)
				(HighPrint 331 83)
				;The world-famous Troll's Sweat is always fresh.
				)
		)
	)
)

(instance onKeg2 of RFeature
	(properties
		nsTop 12
		nsLeft 140
		nsBottom 76
		nsRight 175
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onKeg2 event shiftDown)
				(HighPrint 331 84)
				;Dragon's Breath is the house specialty, and it's Crusher's personal favorite.
				)
		)
	)
)

(instance onKeg3 of RFeature
	(properties
		nsTop 43
		nsLeft 180
		nsBottom 76
		nsRight 208
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onKeg3 event shiftDown)
				(HighPrint 331 85)
				;Some of the finest ale in the valley.
				)
		)
	)
)

(instance onFloor of RFeature
	(properties
		nsTop 116
		nsLeft 106
		nsBottom 172
		nsRight 189
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onFloor event shiftDown)
				(HighPrint 331 86)
				;Nothing but a dirty floor.
				)
		)
	)
)
