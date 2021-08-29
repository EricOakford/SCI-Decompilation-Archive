;;; Sierra Script 1.0 - (do not remove this comment)
(script# 334)
(include game.sh)
(use Main)
(use RFeature)
(use Extra)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm334 0
)

(local
	thievesAmbush
	thievesSatisfied
)
(procedure (LookAround)
	(HighPrint 334 0)
	;This place seems sinister at night. You see a strangely glowing object at the far end of the alley.
)

(instance frontBarrel of PicView
	(properties
		y 120
		x 119
		view vAlley
		loop 2
	)
)

(instance backBarrel of PicView
	(properties
		y 97
		x 162
		view vAlley
		loop 2
	)
)

(instance slink of Actor
	(properties
		y 97
		x 162
		view vAlley
		loop 3
		illegalBits $0000
	)
)

(instance sneak of Actor
	(properties
		y 120
		x 119
		view vAlley
		loop 4
		illegalBits $0000
	)
)

(instance knife of Actor
	(properties
		y 1000
		x 1000
		view vAlley
		priority 10
		illegalBits $0000
	)
)

(instance coin of Extra
	(properties
		y 103
		x 135
		view vAlley
		cycleType 1
		pauseCel 2
		minPause 12
		minCycles 1
		maxCycles 1
	)
)

(instance rm334 of Room
	(properties
		picture 333
		style HSHUTTER
		south 330
	)
	
	(method (init)
		(Load VIEW vAlley)
		(Load VIEW vEgoDefeated)
		(super init:)
		(mouseDownHandler add: self)
		(StatusLine enable:)
		(self setLocales: STREET TOWN)
		(NormalEgo)
		(switch prevRoomNum
			(53
				(ego posn: 160 130 init: setMotion: MoveTo 160 150)
			)
			(else 
				(ego posn: 120 187 init: setMotion: MoveTo 120 180)
			)
		)
		(addToPics
			add: frontBarrel backBarrel
			eachElementDo: #init
			doit:
		)
		(slink ignoreActors: TRUE setPri: 7 init: stopUpd:)
		(sneak ignoreActors: TRUE setPri: 10 init: stopUpd:)
		(knife
			ignoreActors: TRUE
			setLoop: 5
			setPri: 10
			init:
			stopUpd:
		)
		(coin ignoreActors: TRUE setLoop: 6 setPri: 6 init:)
		(if (not (Btst fBeenIn334))
			(RedrawCast)
			(LookAround)
		)
	)
	
	(method (doit)
		(if (and (not thievesAmbush) (<= (ego y?) 132))
			(= thievesAmbush TRUE)
			(self setScript: ambushScript)
		)
		(super doit:)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(Bset fBeenIn334)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(cond 
			((Said 'read,look/mark')
				(if (<= (ego y?) 150)
					(HighPrint 334 1)
					;Some sharp object appears to have been thrown at these walls several times.
				else
					(HighPrint 334 2)
					;You can't make them out from here.
				)
			)
			((Said 'look[<at,around][/!*,street,alley]')
				(LookAround)
			)
			((and (== thievesAmbush TRUE) (Said 'look/thief,thief,man,man'))
				(HighPrint 334 3)
				;These two men may be smiling at you, but they don't seem very friendly.
			)
			((Said 'look/wall')
				(HighPrint 334 4)
				;The walls seem pretty normal, but there are some curious marks at the north ends of the buildings.
			)
			((Said 'look/barrel')
				(HighPrint 334 5)
				;The barrels are empty, and smell of sour beer.
			)
			(
				(and
					(== thievesAmbush TRUE)
					(or
						(MouseClaimed onDagger event shiftDown)
						(Said 'look/dagger')
					)
				)
				(HighPrint 334 6)
				;The dagger looks sharp and very deadly.
			)
			(
				(or
					(MouseClaimed coin event shiftDown)
					(Said 'look/alm,item')
				)
				(if (<= (ego y?) 150)
					(HighPrint 334 7)
					;It's a glowing golden coin.
				else
					(HighPrint 334 8)
					;You just see a glint from here.
				)
			)
			((and (== thievesAmbush TRUE) (MouseClaimed sneak event shiftDown))
				(HighPrint 334 9)
				;He may be short, but the dagger makes him look 7 feet tall.
			)
			(
			(and (== thievesAmbush TRUE) (MouseClaimed slink event shiftDown))
				(HighPrint 334 10)
				;The florid face tells you that this thief is a regular at the "Aces and Eights" tavern.
			)
			((MouseClaimed onBricks event shiftDown)
				(HighPrint 334 11)
				;The whole place is in need of repair.
			)
			((MouseClaimed onFirstBarrel event shiftDown)
				(HighPrint 334 12)
				;Looks like this barrel once contained Dragon's Breath.  The bottom's eaten out.
			)
			((MouseClaimed onSecondBarrel event shiftDown)
				(HighPrint 334 13)
				;A whiff of Troll's Sweat indicates that this barrel is from the "Aces and Eights" tavern.
			)
			((Said 'get,move,lockpick<up,grab>') (if (Said '/barrel')
					(HighPrint 334 14)
					;The barrels are too heavy to move.
				)
			)
			(
				(and
					(not (if (not thievesAmbush) (<= (ego y?) 132)))
					(Said 'climb')
				)
				(HighPrint 334 15)
				;You're too far away from the back wall.
			)
		)
	)
)

(instance ambushScript of Script
	(method (doit)
		(if (not (-- register))
			(HighPrint 334 16)
			;"Aw, now look what ya done.  You made poor Slink run out of patience."
			(HandsOff)
			(self changeState: 7)
		)
		(if
			(and
				thievesAmbush
				(> (ego y?) 143)
				(not thievesSatisfied)
				(not (Btst fBeenIn334))
			)
			(= thievesAmbush 0)
			(= register 0)
			(TimePrint 4 334 17)
			;"Aw, ya forgot to pay.  What a shame."
			(HandsOff)
			(self changeState: 6)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(= state newState)
		(switch state
			(0
				(= register 300)
				(HandsOff)
				(slink startUpd: setCycle: EndLoop self)
			)
			(1
				(sneak startUpd: setCycle: EndLoop self)
			)
			(2
				(knife
					startUpd:
					posn: 150 108
					setCycle: Forward
					setMotion: MoveTo 175 95 self
				)
			)
			(3
				(knife setCel: 2 addToPic:)
				(slink ignoreActors: 0 stopUpd:)
				(= cycles 3)
			)
			(4
				(sneak setCel: 3 ignoreActors: 0 stopUpd:)
				(= cycles 5)
			)
			(5
				(cond 
					((not (Btst fAmbushedAlley))
						(Bset fAmbushedAlley)
						(HighPrint 334 32)
						;Slink says, "That coin light spell gets them every time.  See that dagger?  The next one is centered on your back.
						;Give Sneak there your cash and you walk out of here.  It's a simple trade, your life for your money."
						(HandsOn))
					((not (Btst fLearnedThiefPassword))
						(HighPrint 334 33)
						;Slink:  "Why, how nice to see you again.  I do hope (for your sake) you brought us some nice shiny new silver."
						(HandsOn)
					)
					((not (ego has: iThiefLicense))
						(HighPrint 334 34)
						;Slink:  "Hey, Sneak, this guy's hard of hearing or something. 
						; I told him to get a license last time he was here, and he didn't listen."
						(HighPrint 334 35)
						;"Let's teach him a little lesson about listening to his betters."
						(self changeState: 7)
					)
					((Btst fSlinkWarned)
						(HighPrint 334 36)
						;As Slink reaches for his dagger, you draw your Thieves' Guild license. . .
						(HighPrint 334 37)
						;You *almost* make it.
						(HighPrint 334 38)
						;Slink:  "Hey, Sneak, this guy's hard of hearing or something. 
						; I told him to stay out of our alley, and he didn't listen."
						(HighPrint 334 35)
						;"Let's teach him a little lesson about listening to his betters."
						(self changeState: 7)
					)
					(else
						(HighPrint 334 36)
						;As Slink reaches for his dagger, you draw your Thieves' Guild license. . .
						(HighPrint 334 39)
						;You win by a close margin.
						(HighPrint 334 40)
						;Slink:  "All right, so you got your license.  How nice.  Guess we don't get to kill you right now."
						(HighPrint 334 41)
						;"But stay out of our alley, 'cause we got real short memories, like."
						(Bset fSlinkWarned)
						(= thievesSatisfied TRUE)
						(= register 200)
						(HandsOn)
					)
				)
			)
			(6
				(sneak setLoop: 7 cel: 0)
				(self cue:)
			)
			(7
				(= register 0)
				(sneak cycleSpeed: 2 setCycle: EndLoop self)
			)
			(8
				(ego
					view: vEgoDefeated
					setLoop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(9
				(EgoDead 334 42
					#title { Erana missed this spot._}
					#icon vIcons 0 0)
					;Perhaps you shouldn't explore dark alleys at night unless you are looking for trouble.
					;Or perhaps you should look in your manual to learn how to deal with thieves.
			)
		)
	)
	
	(method (handleEvent event &tmp [str 30])
		(switch (event type?)
			(saidEvent
				(cond 
					((and (not thievesSatisfied) (Said 'climb'))
						(TimePrint 3 334 18)
						;That's a pretty tall wall.
						(HandsOff)
						(self changeState: 7)
					)
					((Said 'display,made,gave/sign[<thief]')
						(cond 
							(thievesSatisfied
								(HighPrint 334 19)
								;The thieves seem to be ignoring you.
							)
							((Btst fLearnedThiefPassword)
								(= register 0)
								(HighPrint 334 20)
								;"Yeah, yeah, we know, you're in the Guild too. 
								; How's a poor independent businessman supposed to make a living around here with all this competition?"
								(HighPrint 334 21)
								;"Now go before I forget you made the sign and I make you pay anyway."
								(= thievesSatisfied TRUE)
								(= register 200)
							)
							((not (if [egoStats STEALTH] else [egoStats PICK]))
								(HighPrint 334 22)
								;"What's that supposed to be?  You trying to pretend you're a thief or something?  Har, har."
							)
							(else
								(= register 0)
								(HighPrint 334 23)
								;"You mean to say that the first customer we've had in months is a fellow thief?
								;And here I thought we were going to make some money."
								(SolvePuzzle f334GiveSign 3 THIEF)
								(if (not (ego has: iThiefLicense))
									(HighPrint 334 24)
									;"You had better check in at the Thieves' Guild before you get in trouble for practicing without a license.
									;Find Crusher in the tavern, and give him the password."
									(HighPrint
										(Format @str 334 25 257 thievesPassword)
										;"The password is '%s'."
									)
								)
								(Bset fLearnedThiefPassword)
								(HighPrint 334 21)
								;"Now go before I forget you made the sign and I make you pay anyway."
								(= thievesSatisfied TRUE)
								(= register 200)
							)
						)
					)
					(
						(or
							(Said 'pay')
							(Said 'gave,put,deposit,throw[/alm,silver,gold]')
						)
						(= register 0)
						(cond 
							(thievesSatisfied
								(HighPrint 334 26)
								;"That's all right; we already have all your money.  But it was certainly nice of you to offer."
							)
							((not (GiveMoney 5))
								(HighPrint 334 27)
								;"Hey!  You don't have enough money to be worth our while. 
								; How do you expect us poor independent businessmen to make a living?"
								(HandsOff) (self changeState: 7))
							(else
								(= [invNum iSilver] 0)
								(= [invNum iGold] 0)
								(HighPrint 334 28)
								;Slink: "Thanks, it's been a pleasure doing business with you.  You can find your way out, I'm sure.
								;But better hurry before my dagger starts to slip."
								(SolvePuzzle f334Robbed -10 THIEF)
								(= thievesSatisfied TRUE)
								(= register 200)
							)
						)
					)
					((Said 'chat,ask')
						(HighPrint 334 29)
						;Slink:  "My finger is starting to get a bit twitchy, so forget about questions, just pay the man and get out alive."
					)
					(
						(or
							(Said 'fight,kill,attack,capture,chop,beat')
							(Said 'throw/dagger,boulder')
						)
						(HighPrint 334 30)
						;As you start to draw your weapon, the thieves jump you.
						(HandsOff)
						(self changeState: 7)
					)
					((Said 'cast')
						(HighPrint 334 31)
						;Before you can begin to use any magic, the thieves decide that you're up to no good.
						(HandsOff)
						(self changeState: 7)
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance onFirstBarrel of RFeature
	(properties
		nsTop 120
		nsLeft 111
		nsBottom 142
		nsRight 125
	)
)

(instance onSecondBarrel of RFeature
	(properties
		nsTop 97
		nsLeft 154
		nsBottom 121
		nsRight 171
	)
)
(instance onDagger of RFeature
	(properties
		nsTop 86
		nsLeft 171
		nsBottom 94
		nsRight 179
	)
)

(instance onBricks of RFeature
	(properties
		nsTop 66
		nsLeft 188
		nsBottom 93
		nsRight 201
	)
)
