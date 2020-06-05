;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13)
(include game.sh)
(use Main)
(use Rest)
(use ThrowFlameDart)
(use ThrowDagger1)
(use CastCalm)
(use CastOpen)
(use CastDazz)
(use Target)
(use LoadMany)
(use Chase)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use System)

(public
	rm13 0
)

(local
	ogreChest
)
	(enum 
		CHEST_NOTKNOWN
		CHEST_LOCKPICKED
		CHEST_FORCED
		CHEST_LOCKED
		CHEST_EMPTY
	)

(instance ogre of TargActor
	(properties
		y 94
		x 195
		yStep 3
		view vOgre
		loop 1
		cel 6
		cycleSpeed 2
		xStep 5
		moveSpeed 2
		targDeltaY -5
	)
	
	(method (getHurt ogreDamage)
		(if
		(<= (= monsterHealth (- monsterHealth ogreDamage)) 0)
			(Bset DEFEATED_OGRE)
			(self setScript: ogreDies)
		else
			(= ogreHealth monsterHealth)
		)
	)
)

(instance crash of Sound
	(properties
		priority 14
	)
)

(instance rm13 of Room
	(properties
		picture 13
		style DISSOLVE
		west 12
	)
	
	(method (init)
		(if (not (Btst VISITED_OGRE_ROOM)) (= ogreHealth MAX_HP_OGRE))
		(if (== prevRoomNum vOgre)
			(if (<= (= ogreHealth monsterHealth) 0) (Bset DEFEATED_OGRE))
		else
			(= monsterHealth ogreHealth)
		)
		(cond 
			((not (Btst DEFEATED_OGRE))
				(LoadMany VIEW vOgre vOgreDefeated vEgoThrowing)
				(Load SCRIPT CHASE)
				(= monsterNum vOgre)
			)
			((> Day ogreDeathDay) (Bset OGRE_GONE))
		)
		(cSound fade:)
		(StatusLine enable:)
		(self setLocales: FOREST)
		(super init:)
		(if (Btst OPENED_OGRE_CHEST) (Bset SEARCHED_OGRE_CHEST))
		(Bclr BEAR_FRIENDLY)
		(NormalEgo)
		(ego init:)
		(if (== prevRoomNum vOgre)
			(ego
				posn: 127 107
				loop: 1
				setMotion: MoveTo -20 130
				cel: 4
			)
			(if (<= monsterHealth 0)
				(ogre posn: 60 104)
				(self setScript: ogreDies)
				(= monsterNum FALSE)
			else
				(ChangeGait MOVE_RUN FALSE)
				(ogre init: setScript: ogreVSego)
			)
		else
			(if (Btst DEFEATED_OGRE)
				(if (not (Btst OGRE_GONE))
					(ogre
						init:
						loop: 0
						cel: 7
						posn: ogreX ogreY
						view: vOgreDefeated
						addToPic:
					)
				)
			else
				(cond 
					((== ogreDay Day)
						(if (== ogreTime timeODay)
							(ogre posn: 80 125)
						)
					)
					(
						(>
							(= ogreHealth
								(+ ogreHealth (* (- Day ogreDay) 25))
							)
							MAX_HP_OGRE
						)
						(= ogreHealth MAX_HP_OGRE)
					)
				)
				(= monsterHealth ogreHealth)
				(ogre init: setScript: ogreVSego)
			)
			(switch prevRoomNum
				(14
					(ego posn: 200 66 setMotion: MoveTo 200 78)
				)
				(171
					(ego posn: 200 66 setMotion: MoveTo 200 78)
				)
				(else 
					(ego posn: 2 140 setMotion: MoveTo 35 140)
				)
			)
		)
	)
	
	(method (doit)
		(if
		(and (== (ego onControl: origin) cMAGENTA) (== (ego loop?) 3))
			(curRoom newRoom: 14)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset VISITED_OGRE_ROOM)
		(DisposeScript CHASE)
		(if (!= newRoomNum vOgre) (= monsterNum FALSE))
		(super dispose:)
	)
	
	(method (handleEvent event &tmp spellCast)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'rest[/!*]') (if (Btst DEFEATED_OGRE) (EgoRests 10 TRUE) else
							(HighPrint 13 0)
							;You're kidding, right?
							)
						)
					((Said 'fight')
						(if (Btst DEFEATED_OGRE)
							(event claimed: FALSE)
						else
							(curRoom newRoom: vOgre)
						)
					)
					((Said 'throw/dagger')
						(= zapPower 0)
						(cond 
							((not (ego has: iDagger))
								(HighPrint 13 1)
								;You don't have a knife.
								)
							((not (Btst DEFEATED_OGRE)) (Face ego ogre) (KnifeCast ogre))
							(else (KnifeCast 0))
						)
					)
					((Said 'search,look/troll,bandit,man')
						(HighPrint 13 2)
						;There are none here to search.
						(cond 
							((Btst OGRE_GONE)
								(HighPrint 13 3)
								;The Ogre's body must have been carried away during the night by some marauding beasties.
								)
							((Btst DEFEATED_OGRE)
								(HighPrint 13 4)
								;However, there is a dead Ogre here.
								)
							(else
								(HighPrint 13 5)
								;However, there is a very large Ogre here.
								)
						)
					)
					((Said 'look>')
						(cond 
							((Said '/cave,hill,hill')
								(HighPrint 13 6)
								)
							((Said '/body,ogre,monster,creature')
								(cond 
									((Btst OGRE_GONE)
										(HighPrint 13 3)
										;The Ogre's body must have been carried away during the night by some marauding beasties.
										)
									((Btst DEFEATED_OGRE)
										(HighPrint 13 7)
										;There is a big, ugly, dead Ogre here.
										)
									(else
										(HighPrint 13 8)
										;This Ogre is in a bad mood.
										(HighPrint 13 9)
										;And big. Very BIG.
										)
								)
							)
							((Said '/chest,trunk,hasp')
								(cond 
									((Btst OPENED_OGRE_CHEST)
										(if (Btst SEARCHED_OGRE_CHEST)
											(HighPrint 13 10)
											;The dead Ogre's chest lies open and empty.
										else
											(HighPrint 13 11)
											;The dead Ogre's chest lies open, its contents glittering brightly.
										)
									)
									((not (Btst DEFEATED_OGRE))
										(HighPrint 13 12)
										;The Ogre is carrying a chest in his massive arms.
										)
									(else
										(HighPrint 13 13)
										;The chest has a heavy lid with a strong lock.
										)
								)
							)
						)
					)
					(
						(or
							(Said 'gave/food')
							(Said 'feed/body,ogre,monster,man,creature')
						)
						(cond 
							((Btst OGRE_GONE)
								(HighPrint 13 14)
								;There is nobody here.
								)
							((Btst DEFEATED_OGRE)
								(HighPrint 13 15)
								;This Ogre will never eat again.
								)
							(else (HighPrint 13 16)
								;This Ogre looks like it would rather eat you.
								)
						)
					)
					((Said 'search/body,ogre,monster,enemy,creature')
						(cond 
							((Btst OGRE_GONE)
								(HighPrint 13 14)
								;There is nobody here.
								)
							((Btst DEFEATED_OGRE) (= ogreChest 0) (ego setScript: egoSearch))
							(else
								(HighPrint 13 0)
								;You're kidding, right?
								)
						)
					)
					(
						(Said
							'search,look,get/trunk,box,chest,contents,alm,gold,silver,loot'
						)
						(cond 
							((Btst OGRE_GONE)
								(HighPrint 13 14)
								;There is nobody here.
								)
							((Btst DEFEATED_OGRE)
								(if (Btst OPENED_OGRE_CHEST) (= ogreChest 4) else (= ogreChest 3))
								(ego setScript: egoSearch)
							)
							(else (HighPrint 13 0)
								;You're kidding, right?
								)
						)
					)
					(
						(or
							(Said 'unlock,lockpick/trunk,box,chest,hasp,keyhole')
							(Said 'use/key,lockpick,(implement,kit<thief)')
							(Said
								'put,fill<in/key,lockpick,(implement,kit<thief)/hasp'
							)
							(Said 'open/hasp,keyhole,trunk,box,chest')
						)
						(cond 
							((Btst OGRE_GONE)
								(HighPrint 13 17)
								;There are no locks here.
								)
							((Btst OPENED_OGRE_CHEST)
								(HighPrint 13 18)
								;The chest is already open.
								)
							((not (Btst DEFEATED_OGRE))
								(HighPrint 13 0)
								;You're kidding, right?
								)
							((not [egoStats PICK])
								(HighPrint 13 19)
								;It's locked -- you'll have to use another skill to open this.
								)
							((not (CanPickLocks))
								(HighPrint 13 20)
								;You don't have the right tools -- you'll have to find another way to open it.
								)
							((TrySkill PICK 0 lockPickBonus) (= ogreChest CHEST_LOCKPICKED) (ego setScript: egoSearch))
							(else
								(HighPrint 13 21)
								;The lock is beyond your current skill.
								)
						)
					)
					((Said 'force,break,pry/box,chest,hasp,lid')
						(cond 
							((Btst OGRE_GONE)
								(HighPrint 13 22)
								;There's nothing like that here.
								)
							((Btst OPENED_OGRE_CHEST)
								(HighPrint 13 23)
								;It's already unlocked.
								)
							((not (Btst DEFEATED_OGRE))
								(HighPrint 13 0)
								;You're kidding, right?
								)
							(else (= ogreChest CHEST_FORCED) (ego setScript: egoSearch))
						)
					)
					((Said 'cast>')
						(= spellCast (SaidSpell event))
						(if (CastSpell spellCast)
							(switch spellCast
								(DETMAGIC
									(HighPrint 13 24)
									;A magical aura emanates from the cave's entrance.
									)
								(DAZZLE
									(if (CastDazz)
										(cond 
											((Btst OGRE_GONE)
												(HighPrint 13 25)
												;You've wasted a spell.
												)
											((Btst DEFEATED_OGRE)
												(HighPrint 13 26)
												;Ok, he'll be a dazzled cadaver.
												)
											(else (ogre setScript: ogreCalmed))
										)
									)
								)
								(FLAMEDART
									(if (Btst DEFEATED_OGRE)
										(FlameCast 0)
									else
										(FlameCast ogre)
									)
								)
								(CALM
									(if (CastCalm)
										(cond 
											((Btst OGRE_GONE)
												(HighPrint 13 25)
												;You've wasted a spell.
												)
											((Btst DEFEATED_OGRE)
												(HighPrint 13 27)
												;How much more calm can he get?
												)
											(else (ogre setScript: ogreCalmed))
										)
									)
								)
								(OPEN
									(CastOpen)
									(cond 
										((Btst OGRE_GONE)
											(HighPrint 13 28)
											;You're wasting a spell.
											(HighPrint 13 29)
											;There's nothing here to open.
											)
										((Btst DEFEATED_OGRE)
											(cond 
												((Btst OPENED_OGRE_CHEST)
													(HighPrint 13 28)
													;You're wasting a spell.
													(HighPrint 13 30)
													;The Ogre's chest is already unlocked.
													)
												((> [egoStats OPEN] 10)
													(HighPrint 13 31)
													;Your spell unlocks the Ogre's chest.
													(Bset OPENED_OGRE_CHEST))
												(else (HighPrint 13 32)
													;Your spell is too weak to open the Ogre's chest.
													)
											)
										)
										(else (HighPrint 13 33)
											;The chest is held shut by the Ogre's massive arms.
											)
									)
								)
								(ZAP
									(= zapPower (+ 5 (/ [egoStats ZAP] 10)))
									(if (or (ego has: iDagger) (ego has: iSword))
										(HighPrint 13 34)
										;Your weapon is now magically charged.
									else
										(HighPrint 13 35)
										;You don't seem to have a weapon to charge.
									)
								)
								(FETCH
									(HighPrint 13 36)
									;You want to fetch THAT?
									)
								(else  (event claimed: FALSE))
							)
						)
					)
					((Said 'get,grab/club,weapon')
						(cond 
							((Btst OGRE_GONE)
								(HighPrint 13 22)
								;There's nothing like that here.
								)
							((Btst DEFEATED_OGRE)
								(HighPrint 13 37)
								;The dead Ogre's huge club is much too heavy for you to lift.
								)
							(else
								(HighPrint 13 0)
								;You're kidding, right?
								)
						)
					)
				)
			)
		)
	)
)

(instance ogreVSego of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ogreDay Day)
				(= ogreTime timeODay)
				(= cycles 12)
			)
			(1
				(ogre setCycle: Walk setMotion: Chase ego 46 self)
			)
			(2
				(HandsOn)
				(if (not (Btst DEFEATED_OGRE))
					(HighPrint 13 38)
					;Hostile intent is evident.  You prepare for battle.
					(curRoom newRoom: vOgre)
				)
			)
		)
	)
)

(instance ogreDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ogreDeathDay Day)
				(ogre
					view: vOgreDefeated
					loop: 0
					cel: 0
					illegalBits: 0
					init:
					setCycle: EndLoop self
				)
			)
			(1
				(crash number: (SoundFX 66) init: play:)
				(ShakeScreen 3 shakeSRight)
				(HandsOn)
				(Bset DEFEATED_OGRE)
				(= ogreX (ogre x?))
				(= ogreY (ogre y?))
				(ogre addToPic:)
				(self dispose:)
			)
		)
	)
)

(instance egoSearch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego inRect: 151 19 222 79)
					(HighPrint 13 39)
					;You need to get closer to the dead Ogre.
					(self dispose:)
				else
					(HandsOff)
					(ego
						ignoreActors:
						illegalBits: 0
						setMotion:
							MoveTo
							(+ ogreX 1)
							(if (< (ego y?) ogreY)
								(- ogreY 8)
							else
								(+ ogreY 1)
							)
							self
					)
				)
			)
			(1
				(Face ego ogre)
				(ego
					loop: (mod (+ (ego loop?) 4) 2)
					view: vEgoThrowing
					setCycle: EndLoop self
				)
			)
			(2
				(switch ogreChest
					(CHEST_NOTKNOWN
						(if
							(and
								(== curRoomNum daggerRoom)
								(or missedDaggers hitDaggers [invDropped iDagger])
							)
							(ego
								get: iDagger (+ missedDaggers hitDaggers [invDropped iDagger])
							)
							(HighPrint 13 40)
							;You retrieve your daggers.
						)
						(= [invDropped iDagger]
							(= hitDaggers
								(= missedDaggers (= daggerRoom 0))
							)
						)
						(HighPrint 13 41)
						;A search of the dead Ogre's body produces no treasure.
						(HighPrint 13 42)
						;However, the treasure chest he was carrying lies beside his body.
					)
					(CHEST_LOCKPICKED
						(HighPrint 13 43)
						;The lock on the Ogre's chest clicks open.
						(Bset OPENED_OGRE_CHEST)
						(HighPrint 13 44)
						;The dead Ogre's chest contains 1 gold and 43 silver, which you take and put away.
						(Bset SEARCHED_OGRE_CHEST)
						(ego get: iGold 1)
						(ego get: iSilver 43)
					)
					(CHEST_FORCED
						(HighPrint 13 45)
						;You force the Ogre's chest open.
						(Bset OPENED_OGRE_CHEST)
						(HighPrint 13 44)
						;The dead Ogre's chest contains 1 gold and 43 silver, which you take and put away.
						(Bset SEARCHED_OGRE_CHEST)
						(ego get: iGold 1)
						(ego get: iSilver 43)
					)
					(CHEST_LOCKED
						(HighPrint 13 46)
						;The chest is locked.
						)
					(CHEST_EMPTY
						(if (Btst SEARCHED_OGRE_CHEST)
							(HighPrint 13 47)
							;The dead Ogre's chest is empty.
						else
							(HighPrint 13 44)
							;The dead Ogre's chest contains 1 gold and 43 silver, which you take and put away.
							(Bset SEARCHED_OGRE_CHEST)
							(ego get: iGold 1)
							(ego get: iSilver 43)
						)
					)
				)
				(ego setCycle: BegLoop self)
			)
			(3
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance ogreCalmed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ogre setMotion: 0 cel: 2)
				(RedrawCast)
				(= seconds 14)
			)
			(1
				(HighPrint 13 48)
				;The Ogre appears to have recovered from your spell.
				(client setScript: ogreVSego)
			)
		)
	)
)
