;;; Sierra Script 1.0 - (do not remove this comment)
(script# 320)
(include game.sh)
(use Main)
(use Door)
(use LoadMany)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm320 0
)

(local
	storeDoor
	sheriffDoor
	tail
	centaurCued
	local4
	sleepNearStall
	local6
	talkRet
)
(procedure (NoMoney)
	(HighPrint 320 0)
	;You reach for your money but come up empty-handed.  Unable to pay for your goods, you put the merchandise back.
)

(instance storeSign of View
	(properties
		y 59
		x 159
		view vTownDryGoodsOutside
		loop 2
	)
)

(instance martSign of View
	(properties
		y 146
		x 270
		view vTownDryGoodsOutside
		loop 3
		cel 4
	)
)

(instance rm320 of Room
	(properties
		picture 320
		style HSHUTTER
	)
	
	(method (init)
		(LoadMany VIEW vTownDryGoodsOutside vDryGoodsInside vEgoTired)
		(super init:)
		(mouseDownHandler add: self)
		(StatusLine enable:)
		(self setLocales: STREET TOWN)
		(NormalEgo)
		(ego init:)
		(if (and Night [egoStats PICK])
			(Load SOUND (SoundFX 35))
		)
		(if (< timeODay TIME_SUNSET)
			(Load VIEW vCentaurGirl)
			(centaur init:)
		)
		((= storeDoor (Door new:))
			view: vTownDryGoodsOutside
			loop: 0
			cel: 0
			posn: 141 91
			doorControl: cLMAGENTA
			locked: (if Night TRUE else FALSE)
			facingLoop: loopN
			entranceTo: 322
			init:
			setPri: 8
		)
		((= sheriffDoor (Door new:))
			view: vTownDryGoodsOutside
			loop: 1
			cel: 0
			posn: 32 72
			doorControl: cYELLOW
			locked: TRUE
			facingLoop: loopN
			entranceTo: 321
			init:
			setPri: 6
		)
		(storeSign init: stopUpd: addToPic:)
		(martSign setPri: 11 init: stopUpd: addToPic:)
		(switch prevRoomNum
			(0
				(ego posn: 275 188 setMotion: MoveTo 275 170)
			)
			(300
				(ego posn: 275 188 setMotion: MoveTo 275 175)
			)
			(330
				(ego posn: 1 175 setMotion: MoveTo 15 175)
			)
			(321
				(ego posn: 49 120)
				(sheriffDoor close:)
			)
			(322
				(ego posn: 158 141)
				(storeDoor close:)
			)
			(999
				(if (ego inRect: 216 0 320 153)
					(= sleepNearStall TRUE)
					(ego
						view: vEgoTired
						setLoop: 3
						cel: 5
						posn: 228 166
						init:
						setScript: egoWakes
					)
				else
					(ego
						view: vEgoTired
						setLoop: 3
						cel: 5
						init:
						setScript: egoWakes
					)
				)
			)
		)
	)
	
	(method (doit)
		(cond 
			((> (ego y?) 188)
				(= exploringTown FALSE)
				(curRoom newRoom: 300)
			)
			((< (ego x?) 1)
				(= exploringTown FALSE)
				(curRoom newRoom: 330)
			)
			((and (not (ego inRect: 210 130 319 185)) centaurCued)
				(= centaurCued 0)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(Bset fBeenIn320)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed onSheriffHouse event shiftDown)
					(Said 'look/house[<meisterson]')
				)
				(HighPrint 320 5)
				;This house is the same color as the Sheriff's office around the corner.
			)
			(
				(or
					(MouseClaimed onStore event shiftDown)
					(Said 'look/shop,drygoods')
				)
				(HighPrint 320 6)
				;A simple but prosperous-looking edifice it is.  It looks like it might provide some staples that would prove useful to a traveler.
			)
			(
				(or
					(MouseClaimed onStand event shiftDown)
					(Said 'look/market,stand')
				)
				(HighPrint 320 7)
				;It is but a small stall, used for the daily sale of whatever local produce has not been confiscated by the brigands.
			)
			((Said 'open/door')
				(cond 
					((== (ego onControl: origin) cLMAGENTA)
						(if Night
							(HighPrint 320 8)
							;The door appears to be locked.
						else
							(HighPrint 320 9)
							;Turn around.
						)
					)
					((== (ego onControl: origin) cYELLOW)
						(if (sheriffDoor locked?)
							(HighPrint 320 8)
							;The door appears to be locked.
						else
							(HighPrint 320 9)
							;Turn around.
						)
					)
					(else (event claimed: FALSE))
				)
			)
			((Said 'knock')
				(cond 
					((== (ego onControl: origin) cLMAGENTA)
						(if Night
							(HighPrint 320 10)
							;There is no response.  The store must be closed for the evening.
						else
							(HighPrint 320 11)
							;Turn around and go on in.
						)
					)
					((== (ego onControl: origin) cYELLOW)
						(HighPrint 320 12)
						;There is no response.
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			((Said 'look>')
				(cond 
					((>= timeODay TIME_SUNSET)
						(cond 
							((Said '/centaur,filly,shopkeeper,female,horse,creature,seller,daughter,girl')
								(HighPrint 320 13)
								;There is no one around.  The markets are closed for the night.
							)
							((Said '/produce,apple,apple,carrot,food')
								(HighPrint 320 14)
								;All of the produce has been taken in for the night.
							)
						)
					)
					((Said '/centaur,filly,shopkeeper,female,horse,creature,seller,daughter,girl')
						(HighPrint 320 15)
						;The creature in charge of the Farmer's Mart is a rather lovely and shy-looking young centaur.
					)
					((Said '/produce,apple,apple,carrot,food')
						(HighPrint 320 16)
						;The selection is meagre and the appearance is not of the best, but the aromas are strangely enticing.
						;A barrel contains some small apples, and there are several types of vegetables on the counter.
					)
				)
				(cond 
					((Said '[<at,around][/!*,street,hamlet]')
						(HighPrint 320 17)
						;This appears to be a Market street.  In the corner is a fruit and vegetable stand, or Farmer's Mart.
						;Next to it is a dry goods store, and next to that is a house.
						;Across the street you see the back of the barber's shop and the Sheriff's office.
					)
					((Said '<south')
						(HighPrint 320 18)
						;You see the backs of buildings.
					)
					((Said '/sign')
						(HighPrint 320 19)
						;Unlike an Ogre, YOU can read!
					)
					((Said '/hasp')
						(cond 
							((== (ego onControl: origin) cLMAGENTA)
								(if Night
									(HighPrint 320 20)
									;The lock appears to be an extra heavy-duty model.
								else
									(HighPrint 320 9)
									;Turn around.
								)
							)
							((== (ego onControl: origin) cYELLOW)
								(HighPrint 320 21)
								;The door appears to be locked, but the lock is of a simple type.
							)
							(else
								(NotClose)
							)
						)
					)
				)
			)
		)
	)
	
	(method (notify param1)
		(cond 
			((!= param1 1))
			((not (sheriffDoor locked?))
				(HighPrint 320 1)
				;The door isn't locked.  Just turn around.
				)
			((not (TrySkill PICK 50 lockPickBonus))
				(HighPrint 320 2)
				;Picking locks looked a lot easier in the instruction book.
				)
			((and (Btst fBeenIn321) (< daySheriffBreakIn Day))
				(HighPrint 320 3)
				;The owners seem to have been made nervous by the recent rash of house burglaries.
				;The door is barred from the inside.  But you do get some valuable lock-picking practice.
			)
			(else
				(lockSound number: (SoundFX 35) init: play:)
				(HighPrint 320 4)
				;You hear a "Snick!".  The lock is open!
				(sheriffDoor locked: FALSE)
			)
		)
	)
)

(instance tailScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles (Random 40 100))
			)
			(1
				(tail setCycle: EndLoop)
				(self changeState: 0)
			)
		)
	)
)

(instance centaur of Prop
	(properties
		view vCentaurGirl
		loop 2
	)
	
	(method (init)
		(self posn: 253 107 setPri: 10 cycleSpeed: 1 stopUpd:)
		((View new:)
			view: vCentaurGirl
			loop: 0
			cel: 0
			posn: 258 124
			init:
			stopUpd:
			addToPic:
		)
		((= tail (Prop new:))
			view: vCentaurGirl
			loop: 1
			cel: 0
			posn: 266 106
			init:
			cycleSpeed: 1
			stopUpd:
			setScript: tailScript
		)
		((View new:)
			view: vTownDryGoodsOutside
			loop: 4
			cel: 0
			posn: 268 133
			setPri: 11
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vDryGoodsInside
			loop: 3
			cel: 0
			posn: 308 158
			setPri: 11
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vTownDryGoodsOutside
			loop: 4
			cel: 1
			posn: 308 138
			setPri: 12
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vDryGoodsInside
			loop: 3
			cel: 0
			posn: 223 141
			init:
			stopUpd:
			addToPic:
		)
		(self setScript: centaurScript)
		(super init:)
	)
	
	(method (doit)
		(if (and (not (ego inRect: 210 130 319 185)) centaurCued)
			(= centaurCued FALSE)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'affirmative,please')
				(cond 
					(centaurCued
						(HighPrint 320 22)
						;"I will be happy to take your silver.  Please pick out what you want."
					)
					((ego inRect: 210 130 319 185)
						(HighPrint 320 23)
						;"Beg pardon?"
					)
				)
			)
			((Said 'n')
				(cond 
					(centaurCued
						(HighPrint 320 24)
						;"As you wish, sir."
					)
					((ego inRect: 210 130 319 185)
						(HighPrint 320 23)
						;"Beg pardon?"
					)
				)
			)
			((Said 'chat')
				(HighPrint 320 25)
				;The pretty young centaur looks at you and says, "Good day, and welcome to Spielburg!
				;Do you wish to buy some nice fresh fruits or vegetables?"
			)
			((Said 'ask>')
				(= talkRet TRUE)
				(cond 
					((Said '//name')
						(HighPrint 320 26)
						;"I am Hilde, daughter of Heinrich Pferdefedern, the farmer."
					)
					((Said '//centaur')
						(HighPrint 320 27)
						;"My father and I are the only centaurs in this valley."
					)
					((Said '//carrot,food')
						(HighPrint 320 28)
						;"We have many kinds of fresh vegetables for sale today.
						;There are lettuce, carrots, potatoes, and turnips right from the ground at five for a silver.  They are very good for you!"
					)
					((Said '//beet')
						(HighPrint 320 29)
						;"It is much too early in the season for those.  Sorry."
					)
					((Said '//cherry')
						;EO: This should refer specifically to cherries, but here, "cherry" is a synonym for the other out-of-season produce.
						(HighPrint 320 30)
						;"I'm sorry, but the cherry harvest is not yet in.  But we will have very nice cherries two months from now."
						)
					((Said '//apple,apple')
						(HighPrint 320 31)
						;"There are still some apples left from last season in the barrel.
						;You may buy ten for a silver, since they are so small.  We will have fresh cherries in a couple of months."
					)
					((Said '//bandit,robbery')
						(HighPrint 320 32)
						;"They have twice robbed my Father of his money, and they have tried to steal our food this winter.
						;That is because we farm outside of town.  They would not dare to try that in town.  The Sheriff would stop them fast!"
						(HighPrint 320 33)
						;"Many robbers have run up to Father as he trotted back to town.
						;My Father tried to fight, but they hurt him badly.  They always ran away to the west."
					)
					((Said '//farm,farmer')
						(HighPrint 320 34)
						;"We have some land to the north of town.  It isn't very big, but we grow many things.
						;You should be here during the harvest time.  Then you would see many fruits and vegetables."
					)
					((Said '//harvest')
						(HighPrint 320 35)
						;"You know...in the autumn!  But then, perhaps you have never been a farmer."
					)
					((Said '//healer')
						(HighPrint 320 36)
						;"The Healer lives in a nice little cottage on the North road, near my Father's fields.  She is a very nice woman."
					)
					((Said '//date,kiss')
						(HighPrint 320 37)
						;"Thank you, but my Father thinks I am too young.  He would not permit it.  But perhaps next year..."
					)
					((Said '//dad,heinrich')
						(HighPrint 320 38)
						;"My Father is Heinrich Pferdefedern, who works our farm just north of town."
					)
					((Said '//*')
						(= talkRet FALSE)
						(HighPrint 320 39)
						;"I am sorry.  I do not get about very much, and I do not really know about many things."
					)
				)
				(if talkRet
					(SolvePuzzle f320TalkToHilde 1))
			)
			((Said 'buy,get>')
				(cond 
					((not (ego inRect: 210 130 319 185))
						(event claimed: TRUE)
						(HighPrint 320 40)
						;If you want to buy something, go over to the vendor.
					)
					((Said '/carrot,food')
						(if (GiveMoney 1)
							(HighPrint 320 41)
							;You pick out the freshest-looking vegetables you can find, and pay the lady a silver piece.
							(ego get: iVegetables 5)
						else
							(NoMoney)
						)
					)
					((Said '/apple,apple')
						(if (GiveMoney 1)
							(HighPrint 320 42)
							;You carefully select ten of the best apples from the barrel, and pay the lady a silver piece.
							(ego get: iFruit 10)
							(SolvePuzzle f320BuyApples 3)
						else
							(NoMoney)
						)
					)
					((Said '/beet')
						(HighPrint 320 43)
						;"As I told you, sir, it is too early in the season.  You would not want to buy any."
					)
					((Said '/*')
						(HighPrint 320 44)
						;"I'm afraid I can't help you with that, sir."
					)
				)
			)
			((Said 'pay')
				(HighPrint 320 45)
				;"Just pick out what you would like to purchase, then you can pay me."
			)
			((MouseClaimed onCentaur event shiftDown)
				(HighPrint 320 46)
				;The creature in charge of the Farmer's Mart is a rather lovely and shy-looking young Centaur.
			)
			((MouseClaimed onBarrel1 event shiftDown)
				(HighPrint 320 47)
				;The barrel contains potent looking potatoes more suited to throwing than eating.
			)
			((MouseClaimed onBarrel2 event shiftDown)
				(HighPrint 320 48)
				;The small apples give off a sweet but pungent odor.
			)
			((MouseClaimed onCarrots event shiftDown)
				(HighPrint 320 49)
				;Carrots are good for night vision and lower one's cholesterol.
			)
			((MouseClaimed onTurnips event shiftDown)
				(HighPrint 320 50)
				;The turnips seem to blush with pride.  You wonder if the farmer has been using Alar.
			)
			(
				(or
					(MouseClaimed onGreens1 event shiftDown)
					(MouseClaimed onGreens2 event shiftDown)
				)
				(HighPrint 320 51)
				;Lettuce and other leafy plants.
			)
		)
	)
)

(instance centaurScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(if (not (Btst fBeenIn320))
					(User canControl: FALSE)
					(HighPrint 320 52)
					;You can smell apples as you approach this corner.
					(= cycles 12)
				)
			)
			(2
				(HighPrint 320 25)
				;The pretty young centaur looks at you and says, 
				;"Good day, and welcome to Spielburg!  
				;Do you wish to buy some nice fresh fruits or vegetables?"
				(User canControl: TRUE)
				(= centaurCued TRUE)
			)
		)
	)
)

(instance onCarrots of RFeature
	(properties
		nsTop 120
		nsLeft 276
		nsBottom 131
		nsRight 288
	)
)

(instance onTurnips of RFeature
	(properties
		nsTop 120
		nsLeft 259
		nsBottom 131
		nsRight 275
	)
)

(instance onGreens1 of RFeature
	(properties
		nsTop 120
		nsLeft 235
		nsBottom 131
		nsRight 258
	)
)

(instance onGreens2 of RFeature
	(properties
		nsTop 120
		nsLeft 289
		nsBottom 131
		nsRight 298
	)
)

(instance onBarrel1 of RFeature
	(properties
		nsTop 118
		nsLeft 214
		nsBottom 139
		nsRight 231
	)
)

(instance onBarrel2 of RFeature
	(properties
		nsTop 132
		nsLeft 300
		nsBottom 157
		nsRight 317
	)
)

(instance onStand of RFeature
	(properties
		nsTop 135
		nsLeft 238
		nsBottom 145
		nsRight 297
	)
)

(instance onStore of RFeature
	(properties
		y 135
		x 99
		nsTop 9
		nsLeft 99
		nsBottom 135
		nsRight 212
	)
)

(instance onSheriffHouse of RFeature
	(properties
		nsTop 3
		nsLeft 2
		nsBottom 114
		nsRight 95
	)
)

(instance onCentaur of RFeature
	(properties
		nsTop 84
		nsLeft 247
		nsBottom 112
		nsRight 269
	)
)

(instance lockSound of Sound
	(properties
		number 35
	)
)

(instance egoWakes of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: BegLoop self)
			)
			(1
				(NormalEgo)
				(HighPrint 320 53)
				;However, this was not a good place to sleep.  You're stiff and sore all over and your wallet feels very light.
				;You give the Hilde the centaur a suspicious look, but you are too sore to pursue the matter.
				(= [invNum iSilver] 0)
				(= [invNum iGold] 0)
				(HandsOn)
				(self cue:)
			)
			(2
				(if sleepNearStall
					(HighPrint 320 54)
					;"I'm sorry, but I had to move you out of my stall!
					; It is very dangerous to sleep in the street at night.  I fear you have been robbed."
					)
			)
		)
	)
)
