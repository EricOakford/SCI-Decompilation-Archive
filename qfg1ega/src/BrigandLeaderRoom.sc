;;; Sierra Script 1.0 - (do not remove this comment)
(script# 97)
(include game.sh)
(use Main)
(use CastDart)
(use CastCalm)
(use CastOpen)
(use CastDazz)
(use Intrface)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm97 0
)

(local
	local0
	tookPotions
	leaderInRoom
	elsaInRoom
	yorickInRoom
)
(procedure (OfficeSay name &tmp [printRect 4] [str 400])
	(cls)
	(Format @str &rest)
	(TextSize @[printRect 0] @str userFont 0)
	(Print
		@str
		#at -1 12
		#width (if (> [printRect 2] 24) 300 else 0)
		#mode teJustCenter
		#title name
	)
)

(instance rm97 of Room
	(properties
		picture 97
		style WIPELEFT
	)
	
	(method (init)
		(LoadMany VIEW vBrigandLeaderRoom vBrigand vEgoDefeated)
		(LoadMany SOUND 88 66)
		(cSound fade:)
		(super init:)
		(SolvePuzzle POINTS_ENTERBRIGANDLEADERROOM 12)
		(StatusLine enable:)
		(ChangeGait MOVE_WALK 0)
		(NormalEgo)
		(dragon init: setPri: 0 addToPic:)
		(mula init: ignoreActors: setPri: 11 addToPic:)
		(carpet init: addToPic:)
		(falcon init: setPri: 8 addToPic:)
		(vase init: addToPic:)
		(oscar init: setPri: 8 addToPic:)
		(book init: ignoreActors: setPri: 9 addToPic:)
		(mirror setPri: 9 ignoreActors: init: stopUpd:)
		(door init: stopUpd:)
		(eyes init: setPri: 1)
		(torchL init: setCycle: Forward)
		(torchR init: setCycle: Forward)
		(eyes init: setScript: blink)
		(switch prevRoomNum
			(172
				(HandsOff)
				(if (Btst SAVED_ELSA)
					(= elsaInRoom TRUE)
					(leader
						init:
						setLoop: 5
						setCel: 0
						setPri: 11
						posn: 129 142
						setScript: elsaIsBack
					)
					(ego init: loop: 1 posn: 237 159)
					(yorick init: illegalBits: 0 posn: 316 142)
				else
					(= leaderInRoom TRUE)
					(leader
						setLoop: 3
						setCel: 0
						init:
						setPri: 9
						posn: 129 142
						setScript: elsaKillsEgo
					)
					(ego init: loop: 1 setPri: 10 posn: 178 141)
					(yorick init: posn: 316 142)
				)
			)
			(else 
				(= leaderInRoom TRUE)
				(leader
					init:
					setPri: 11
					illegalBits: 0
					setScript: leaderVaults
				)
				(ego init: setScript: egoEnters)
				(elsaSong init:)
			)
		)
	)
	
	(method (doit)
		(if (== (ego edgeHit?) EAST)
			(if (ego has: iMagicMirror)
				(curRoom newRoom: 84) ;if player has the mirror, return to the Antwerp area
			else
				(curRoom newRoom: ENDGAME) ;otherwise, go to the endgame sequence
			)
		)
	)
	
	(method (dispose)
		(Bset fBeenIn97)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp spell)
		(switch (event type?)
			(mouseDown
				(cond 
					((super handleEvent: event))
					((MouseClaimed ego event shiftDown)
						(HighPrint 97 0)
						;You finally made it through Yorick's room.
						)
				)
			)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'rest,nap')
						(EgoDead 97 1
							#title {Z-Z-Z-Z-Z-Z-Z-Z-Z-Z}
							#icon vIcons 0 0)
							;The Brigands oblige you by making your pleasant rest permanent!
						)
					((Said 'look>')
						(cond 
							((Said '[<at,around][/place,area]')
								(HighPrint 97 2)
								;The Leader's room is filled with treasures.
							)
							((Said '/falcon,statue')
								(HighPrint 97 3)
								;Used to belong to Bogey.
							)
							((Said '/west,east,south')
								(HighPrint 97 4)
								;You see loot from brigand raids stacked against the walls.
							)
							((Said '/north,desk')
								(HighPrint 97 5)
								;You see a desk.  On the desk is a book and a mirror.
							)
							((Said '/yorick')
								(if yorickInRoom
									(HighPrint 97 6)
									;What a funny guy!
									else
									(HighPrint 97 7)
									;He's not here.
								)
							)
							((Said '/carpet')
								(HighPrint 97 8)
								;This looks like a Persian carpet.  It has a license plate that says " Abdulla Doo 222".
							)
							((Said '/female,man')
								(cond 
									(leaderInRoom
										(HighPrint 97 9)
										;The brigand leader wears a sword, and it looks well used.
										)
									(elsaInRoom
										(HighPrint 97 10)
										;Elsa looks much better in a dress.
										(HighPrint 97 11)
										;It depends on your taste, though, I suppose.
										)
									(else
										(HighPrint 97 12)
										;Everybody else is gone.
										)
								)
							)
							(else (HighPrint 97 2)
								;'The Leader's room is filled with treasures.
								(event claimed: TRUE)
							)
						)
					)
					(
						(or
							(Said
								'cast,use,throw,splash/disenchant,potion[<disenchant]'
							)
							(Said 'disenchant')
						)
						(if (ego has: iDisenchant)
							(Bset SAVED_ELSA)
							(ego use: iDisenchant)
							(HighPrint 97 13)
							;You throw the Dispel Potion on the brigand leader.
						else
							(HighPrint 97 14)
							;Good idea, but you don't have that potion.
						)
					)
					((Said 'cast>')
						(= spell (SaidSpell event))
						(if (CastSpell spell)
							(switch spell
								(DETMAGIC
									(if leaderInRoom
										(HighPrint 97 15)
										;You sense a strong magical aura on the brigand leader.
										)
									(if (not (ego has: iMagicMirror))
										(HighPrint 97 16)
										;The hand mirror on the desk radiates a powerful magical aura.
										)
									(HighPrint 97 17)
									;You sense magical auras from the large carpet and several other objects in the room, but you don't have time to investigate.
								)
								(DAZZLE (CastDazz))
								(FLAMEDART
									(CastDart 0)
								)
								(CALM (CastCalm))
								(OPEN (CastOpen))
								(else
									(HighPrint 97 18)
									;That spell is useless here.
									)
							)
						)
					)
					((Said 'get/boulder')
						(HighPrint 97 19)
						;There are no rocks here.
						)
					((Said 'open[/gate,door]')
						(HighPrint 97 20)
						;That would be foolhardy.  You would let the brigands in.
						)
					((Said 'get,get/mirror')
						(cond 
							(
							(and (ego inRect: 100 108 233 135) (not (ego has: iMagicMirror)))
								(HighPrint 97 21)
								;You pick up the mirror and store it away.
								(SolvePuzzle POINTS_TAKEMAGICMIRROR 10)
								(ego get: iMagicMirror)
								(mirror dispose:)
							)
							((ego has: iMagicMirror)
								(HighPrint 97 22)
								;You already have the mirror.
								)
							(else
								(HighPrint 97 23)
								;You're not near the mirror.
								)
						)
					)
					((Said 'get,get/potion')
						(cond 
							(
							(and (ego inRect: 100 108 233 135) (not tookPotions))
								(HighPrint 97 24)
								;You place the two Healing Potions into your pack.
								(ego get: iHealingPotion)
								(ego get: iHealingPotion)
								(= tookPotions TRUE)
							)
							((ego inRect: 100 108 233 135)
								(HighPrint 97 25)
								;There are no more potions here.
								)
							(else
								(NotClose)
							)
						)
					)
					((Said 'search/desk')
						(if (ego inRect: 100 108 233 135)
							(cond 
								((and (ego has: iMagicMirror) tookPotions)
									(HighPrint 97 26)
									;You find nothing more.
									)
								(tookPotions
									(HighPrint 97 27)
									;You see a mirror.
									)
								((ego has: iMagicMirror)
									(HighPrint 97 28)
									;You find two Healing Potions.
									)
								(else
									(HighPrint 97 29)
									;A quick but thorough search of the desk discloses two Healing Potions and a mirror.
									)
							)
						else
							(NotClose)
						)
					)
					((Said 'get')
						(= local0 1)
						(if (and (ego has: iHealingPotion) (ego has: iMagicMirror))
							(HighPrint 97 30)
							;You don't have time to take anything else.
						else
							(HighPrint 97 31)
							;Take only those things that will serve your quest.  Your time is limited.
						)
					)
				)
			)
		)
	)
)

(instance dragon of View
	(properties
		y 92
		x 159
		view vBrigandLeaderRoom
	)
)

(instance mula of View
	(properties
		y 144
		x 285
		view vBrigandLeaderRoom
		cel 1
		priority 11
	)
)

(instance carpet of View
	(properties
		y 164
		x 273
		view vBrigandLeaderRoom
		cel 2
		priority 12
	)
)

(instance falcon of View
	(properties
		y 92
		x 256
		view vBrigandLeaderRoom
		cel 3
		priority 8
	)
)

(instance vase of View
	(properties
		y 151
		x 298
		view vBrigandLeaderRoom
		cel 4
		priority 11
	)
)

(instance oscar of View
	(properties
		y 81
		x 65
		view vBrigandLeaderRoom
		cel 5
		priority 10
	)
)

(instance book of View
	(properties
		y 108
		x 160
		view vBrigandLeaderRoom
		cel 6
		priority 9
	)
)

(instance mirror of View
	(properties
		y 109
		x 125
		view vBrigandLeaderRoom
		cel 7
		priority 9
	)
)

(instance eyes of Prop
	(properties
		y 75
		x 159
		view vBrigandLeaderRoom
		loop 1
		cel 1
		priority 1
	)
)

(instance torchL of Prop
	(properties
		y 76
		x 91
		view vBrigandLeaderRoom
		loop 2
		cel 4
	)
)

(instance door of Prop
	(properties
		y 190
		x 232
		view vBrigandLeaderRoom
		loop 8
	)
)

(instance torchR of Prop
	(properties
		y 76
		x 225
		view vBrigandLeaderRoom
		loop 2
		cel 2
	)
)

(instance leader of Actor
	(properties
		y 123
		x 164
		view vBrigandLeaderRoom
		loop 4
	)
)

(instance yorick of Actor
	(properties
		y 142
		x 316
		view vBrigandLeaderRoom
		loop 6
	)
)

(instance brig1 of Actor
	(properties
		view vBrigand
	)
)

(instance brig2 of Actor
	(properties
		view vBrigand
	)
)

(instance brig3 of Actor
	(properties
		view vBrigand
	)
)

(instance elsaSong of Sound
	(properties
		number 88
		priority 1
	)
)

(instance tromp of Sound
	(properties
		number 66
		priority 3
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					posn: 230 195
					illegalBits: 0
					setMotion: MoveTo 197 141 self
				)
			)
			(1
				(NormalEgo)
				(ego loop: 1 illegalBits: (| cWHITE cGREEN))
				(self dispose:)
			)
		)
	)
)

(instance blink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 10)))
			(1
				(eyes setCycle: Forward)
				(= cycles (Random 2 9))
			)
			(2
				(eyes setCycle: BegLoop)
				(self changeState: 0)
			)
		)
	)
)

(instance leaderVaults of Script
	(properties)
	
	(method (doit)
		(if
		(and (== state 1) (== (elsaSong prevSignal?) 10))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(leader setLoop: 4 setCel: 1 posn: 164 122)
				(= seconds 5)
			)
			(1 (elsaSong play:))
			(2
				(User canInput: FALSE)
				(theGame setCursor: normalCursor (HaveMouse))
				(leader setCel: 2 posn: 164 120)
				(= cycles 2)
			)
			(3
				(leader setCel: 3 posn: 158 123)
				(= cycles 2)
			)
			(4
				(leader setCel: 4 posn: 152 126)
				(= cycles 2)
			)
			(5
				(leader setCel: 5 posn: 146 129)
				(= cycles 2)
			)
			(6
				(leader setCel: 6 posn: 140 132)
				(= cycles 2)
			)
			(7
				(leader setCel: 7 posn: 134 135)
				(= cycles 2)
			)
			(8
				(leader setCel: 8 illegalBits: cWHITE posn: 128 138)
				(= cycles 2)
			)
			(9
				(leader setCel: 9 setPri: -1 posn: 122 141)
				(User canInput: TRUE)
				(= seconds 5)
			)
			(10
				(curRoom newRoom: 172)
				(self dispose:)
			)
		)
	)
)

(instance elsaIsBack of Script
	(properties)
	
	(method (doit)
		(if (and (<= 8 state) (<= state 9) local0)
			(self changeState: 10)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 10))
			(1
				(OfficeSay {Elsa} 97 32)
				;"You can't imagine how good it feels to know who I am again.  After all these years, to remember my name is Elsa von Spielburg!"
				(OfficeSay {Elsa} 97 33)
				;"I used to wish I was not a brigand's child but actually an enchanted princess.
				;Now I know that I really was enchanted, and I don't have to be a brigand any more!"
				(OfficeSay {Elsa} 97 34)
				;"Thank you so much for freeing me."
				(OfficeSay {Elsa} 97 35)
				;"I've got to hurry home to father and get his guards to capture the rest of the brigands before they manage to escape with the treasure.
				;It must be returned to the rightful owners."
				(OfficeSay {Elsa} 97 36)
				;"Yorick!  Yorick!  I'm me again!"
				(= cycles 1)
			)
			(2
				(yorick
					setLoop: 6
					setCel: 0
					setCycle: Walk
					cycleSpeed: 2
					setMotion: MoveTo 258 142 self
				)
				(= yorickInRoom TRUE)
			)
			(3
				(OfficeSay {Yorick} 97 37)
				;"Sorry I took so long.  I got lost."
				(OfficeSay {Yorick} 97 38)
				;"Will your Dad be glad that your spell's been repelled!  We need to go before the brigands know or they'll spoil our show."
				(OfficeSay {Yorick} 97 39)
				;"C'mon!  We'd better make our getaway before they get in our way."
				(= cycles 2)
			)
			(4
				(OfficeSay {Elsa} 97 40)
				;"Yorick and I can return to the castle with the amulet I wear, but I'm afraid you'll have to get there on your own."
				(OfficeSay {Elsa} 97 41)
				;"There are two Healing Potions in my desk that you may take with you."
				(OfficeSay {Elsa} 97 42)
				;"You should use this secret passage and escape while they are arguing over the treasure.
				;If they find you in here, you'll be overwhelmed and killed."
				(OfficeSay {Elsa} 97 43)
				;"Thank you again, and good luck!  I'll make sure you are richly rewarded for your bravery."
				(= cycles 2)
			)
			(5
				(tromp number: (SoundFX 66) init: loop: 4 play:)
				(OfficeSay {Yorick} 97 44)
				;"If you decide to counter the curser, then mind the mirror over mere minds.
				;So tip the canoe and toodleloo!  Elsa, if you do the honors, I'll honor your due."
				(= cycles 2)
			)
			(6
				(leader setCycle: EndLoop self)
				(= elsaInRoom FALSE)
			)
			(7
				(leader ignoreActors:)
				(yorick setLoop: 7 setCel: 0 setCycle: EndLoop self)
				(HandsOn)
				(tromp loop: 4 play:)
				(= yorickInRoom FALSE)
			)
			(8
				(HighPrint 97 45)
				;The brigands are trying to break into the room.
				(yorick dispose:)
				(tromp loop: 6 play:)
				(= seconds 20)
			)
			(9
				(tromp loop: 6 play:)
				(= seconds 15)
			)
			(10
				(HandsOff)
				(door setCel: 1)
				(brig1
					init:
					setCycle: Walk
					posn: 259 230
					setMotion: MoveTo 259 202
				)
				(brig2
					init:
					setCycle: Walk
					posn: 242 247
					setMotion: MoveTo 242 214
				)
				(brig3
					init:
					setCycle: Walk
					posn: 278 250
					setMotion: MoveTo 278 214 self
				)
			)
			(11
				(EgoDead 97 46
					#icon vEgoDefeatedMagic 0 9
					#title {Wearing out your welcome.})
					;Thinking about all that treasure cost you time and more.  Some folks just don't know when to leave.
			)
		)
	)
)

(instance elsaKillsEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(leader cycleSpeed: 1 setCycle: EndLoop self)
			)
			(1 (= cycles 6))
			(2
				(leader cycleSpeed: 1 setCycle: BegLoop)
				(= cycles 16)
			)
			(3
				(ego
					view: vEgoDefeated
					setLoop: 0
					setCel: 0
					cycleSpeed: 4
					setCycle: EndLoop self
				)
			)
			(4
				(yorick
					setLoop: 6
					setCel: 0
					setCycle: Walk
					cycleSpeed: 2
					setMotion: MoveTo 258 142
				)
				(= cycles 32)
			)
			(5
				(OfficeSay {Yorick} 97 47)
				;"Elsa, my dear.  There's a problem I fear.  To our dismay, that spell's here to stay, for alas you did slay your hero today."
				(= cycles 1)
			)
			(6
				(EgoDead 97 48
					#icon vEgoDefeated 0 7
					#title {One thrust...and that's it???})
					;The brigand leader is deadly with a sword.  The magic spell that possesses her makes her an invincible warrior.
			)
		)
	)
)
