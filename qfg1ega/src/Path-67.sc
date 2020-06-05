;;; Sierra Script 1.0 - (do not remove this comment)
(script# 67)
(include game.sh)
(use Main)
(use ThrowDagger1)
(use ThrowRock)
(use CastOpen)
(use Target)
(use LoadMany)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rm67 0
)

(local
	[eyesPosn 10] = [205 180 243 73 103 67 74 72 54 25]
	local10
	local11
	foxOnScreen
	openSpellSuccess
	openSpellFail
	trapSprung
	talkedToFox
	flameDartAttempted
	foxDistance
)
(instance rm67 of Room
	(properties
		picture 67
		style DISSOLVE
		horizon 90
		north 57
		east 68
		west 66
	)
	
	(method (init)
		(LoadMany VIEW vBushes vBushEyes vEgoThrowing)
		(super init:)
		(self setLocales: FOREST)
		(StatusLine enable:)
		(southBush setPri: 15 init: addToPic:)
		(if (and (Btst ENTERED_FOX_ROAD_1) (Btst ENTERED_FOX_ROAD_2) (not (Btst MET_FOX)))
			(= foxOnScreen TRUE)
			(Bset MET_FOX)
			(Load VIEW vFox)
			(Load VIEW vEgoDrawWeapon)
			(fox init: setScript: foxCallForHelp)
			(Load VIEW vEgoMagicFlameDart)
			(Load VIEW vEgoSwordSpirea)
			(HighPrint 67 0)
			;There seems to be a fox north of the road.
		)
		(if (not foxOnScreen)
			(eyes1
				setPri: 5
				init:
				stopUpd:
				setScript: peekABooScript1
			)
			(eyes2
				setPri: 5
				init:
				stopUpd:
				setScript: peekABooScript2
			)
		)
		(if (Btst ENTERED_FOX_ROAD_1) (Bset ENTERED_FOX_ROAD_2))
		(Bset ENTERED_FOX_ROAD_1)
		(NormalEgo)
		(ego init:)
		(switch prevRoomNum
			(57
				(ego posn: 140 92 setMotion: MoveTo 140 190)
			)
			(66
				(ego posn: 1 140 setMotion: MoveTo 320 140)
			)
			(68
				(ego posn: 318 140 setMotion: MoveTo 0 140)
			)
		)
	)
	
	(method (doit)
		(if
			(and
				(< (= foxDistance (ego distanceTo: fox)) 75)
				(not talkedToFox)
				(not (fox script?))
				(not (ego script?))
				foxOnScreen
			)
			(= talkedToFox TRUE)
			(fox setScript: foxExplains)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset VISITED_PATH_67)
		(= egoY (ego y?))
		(super dispose:)
	)
	
	(method (handleEvent event &tmp spell)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look>')
						(cond 
							((Said '[<at,around][/!*,road]')
								(HighPrint 67 1)
								;The forest deepens north and south of you.  To the east, the road twists it's way up the mountain.
								;To the west, the road is hidden by trees.
								)
							((Said '/eye')
								(HighPrint 67 2)
								;For a moment there, you thought you saw something. You can't see anything now.
								)
							(
								(or
									(Said '/trap,hasp,chain')
									(and (Said '<down') (ego inRect: 55 111 120 120))
								)
								(cond 
									(foxOnScreen
										(HighPrint 67 3)
										;It seems to be a simple spring trap and you can easily open it.
									)
									(trapSprung
										(HighPrint 67 4)
										;The trap seems to be caught on something.
									)
									((not (ego inRect: 55 111 120 120))
										(event claimed: FALSE)
									)
									(else (HighPrint 67 5)
										;You can see no sign of a trap here.
									)
								)
							)
							((Said '/fox') (if foxOnScreen (HighPrint 67 6) else (HighPrint 67 7)))
						)
					)
					((Said 'get>')
						(if (Said '/trap,hasp,chain')
							(cond 
								(foxOnScreen
									(HighPrint 67 8)
									;You must free the fox first.
									)
								(trapSprung
									(if (ego inRect: 55 111 120 120)
										(ego setScript: getTrap)
									else
										(HighPrint 67 9)
										;Walk over to the trap.
									)
								)
								(else (HighPrint 67 10)
									;You don't see the trap now.  It seems to have vanished.
									)
							)
						)
					)
					((Said 'cast>')
						(switch (= spell (SaidSpell event))
							(DETMAGIC
								(if (CastSpell spell)
									(if foxOnScreen
										(HighPrint 67 11)
										;There is an aura of magic around the fox.
										else
										(HighPrint 67 12)
										;There is no magic present.
										)
								)
							)
							(DAZZLE
								(if foxOnScreen
									(HighPrint 67 13)
									;The fox is in such pain that it's already dazzled.
									else
									(HighPrint 67 14)
									;There is no point to that.
									)
							)
							(FLAMEDART
								(if (CastSpell spell)
									(if foxOnScreen
										(if flameDartAttempted
											(dart init: setScript: flameDartFox)
										else
											(HighPrint 67 15)
											;"You don't have to put me out of my misery, you just have to get me out of this trap."
											(= flameDartAttempted TRUE)
										)
									else
										(HighPrint 67 14)
										;There is no point to that.
									)
								)
							)
							(CALM
								(if foxOnScreen
									(HighPrint 67 16)
									;"You realize, of course, I really find it hard to relax with this thing on my leg."
									else
									(HighPrint 67 14)
									;There is no point to that.
									)
							)
							(OPEN
								(if foxOnScreen
									(if (CastSpell spell)
										(if (TrySkill OPEN tryCastOpenFox)
											(= openSpellSuccess TRUE)
											(SolvePuzzle POINTS_FREEFOX 10)
										else
											(= openSpellFail TRUE)
										)
										(ego setScript: foxFreed)
									)
								else
									(HighPrint 67 14)
									;There is no point to that.
								)
							)
							(else  (event claimed: FALSE))
						)
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance foxCallForHelp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(HandsOff)
				(fox setCycle: EndLoop self)
			)
			(2
				(cls)
				(TimePrint 6 67 17)
				;"Help me, Brave and Kind Hero."
				(fox loop: 1 cel: 0 setCycle: Forward)
				(= seconds 2)
			)
			(3
				(fox setCycle: EndLoop)
				(HandsOn)
				(= seconds 8)
			)
			(4
				(fox loop: 1 cel: 0 setCycle: Forward)
				(cls)
				(TimePrint 6 67 18)
				;"Please help me, Brave and Kind Hero."
				(= seconds 2)
			)
			(5 (fox setCycle: EndLoop self))
			(6
				(fox setScript: thrashAndWait)
			)
		)
	)
)

(instance foxWillNotFight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cls)
				(TimePrint 7 67 19)
				;"Please have mercy on a creature who is in great pain."
				(fox loop: 1 cel: 0 setCycle: Forward)
				(= seconds 5)
			)
			(1
				(fox setCycle: EndLoop)
				(= cycles 5)
			)
			(2 (HighPrint 67 20)
				;The fox does not seem to want to fight.
				)
		)
	)
)

(instance foxExplains of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cls)
				(TimePrint 9 67 21)
				;"My foot is caught in this cruel trap and I am in great pain.
				;Surely you could take a minute and set me free."
				(fox loop: 1 cel: 0 setCycle: Forward)
				(= seconds 3)
			)
			(1
				(fox setCycle: EndLoop)
				(= cycles 5)
			)
			(2
				(HandsOn)
				(client setScript: thrashAndWait)
			)
		)
	)
)

(instance distantFoxDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= trapSprung TRUE)
				(= foxOnScreen FALSE)
				(fox loop: 2 cel: 0 cycleSpeed: 2 setCycle: EndLoop)
				(= seconds 7)
			)
			(1
				(SolvePuzzle POINTS_KILLFOX -10)
				(= hitDaggers 0)
				(HandsOn)
				(HighPrint 67 22)
				;That was strange, no sooner did you kill the fox than it vanishes.
				(client dispose:)
			)
		)
	)
)

(instance foxDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= trapSprung TRUE)
				(= foxOnScreen FALSE)
				(if (ego inRect: 41 109 111 138)
					(self cue:)
				else
					(ego
						ignoreActors:
						illegalBits: 0
						setMotion: MoveTo 120 130 self
					)
				)
			)
			(1
				(ego
					ignoreActors:
					setMotion: MoveTo (+ (fox x?) 20) (+ (fox y?) 2) self
				)
			)
			(2
				(ego view: vEgoSwordSpirea setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(3
				(ego setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(4 (ego setCycle: BegLoop self))
			(5
				(NormalEgo)
				(fox loop: 2 cel: 0 cycleSpeed: 2 setCycle: EndLoop)
				(= seconds 7)
			)
			(6
				(SolvePuzzle POINTS_KILLFOX -10)
				(HandsOn)
				(= hitDaggers 0)
				(HighPrint 67 22)
				;That was strange, no sooner did you kill the fox than it vanishes.
				(client dispose:)
			)
		)
	)
)

(instance foxFreed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (or openSpellSuccess openSpellFail)
					(CastOpen rm67 self)
				else
					(self cue:)
				)
			)
			(1
				(HandsOff)
				(fox setScript: thrashAndWait)
				(ego ignoreActors: illegalBits: 0)
				(if (ego inRect: 41 109 111 138)
					(self cue:)
				else
					(ego setMotion: MoveTo 112 130 self)
				)
			)
			(2
				(ego
					setMotion: MoveTo (- (fox x?) 29) (+ (fox y?) 3) self
				)
			)
			(3
				(ego view: vEgoThrowing loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(4
				(cond 
					(openSpellFail
						(HighPrint 67 23)
						;Hmmm.  Perhaps it needed a stronger spell.
						)
					(openSpellSuccess
						(HighPrint 67 24)
						;Your powerful Open spell springs the trap.
						)
					(else
						(HighPrint 67 25)
						;You spring the trap.
						)
				)
				(self cue:)
			)
			(5 (ego setCycle: BegLoop self))
			(6
				(if openSpellFail
					(= openSpellFail FALSE)
					(NormalEgo)
					(HandsOn)
					(fox setScript: foxExplains)
				else
					(= trapSprung TRUE)
					(= foxOnScreen FALSE)
					(= cycles 8)
				)
			)
			(7
				(fox setScript: 0 setCycle: Forward)
				(= seconds 2)
			)
			(8
				(CenterPrint 67 26)
				;"In exchange for your kindness, I will give you some advice and a bit of information.
				;First of all, it sometimes pays off to be polite, even to rude people."
				(self cue:)
			)
			(9
				(CenterPrint 67 27)
				;"As for the amusing tidbit - Baba Yaga put an enchantment on the Baron's Daughter some years back.
				;To break the spell you need to talk to the Dryad.  Au Revoir, Ta-ta, see you sometime."
				(self cue:)
			)
			(10
				(fox
					setLoop: 3
					setCel: 0
					cycleSpeed: 1
					setStep: 6 6
					moveSpeed: 1
					setCycle: CycleTo 4 1 self
				)
			)
			(11
				(trap init: stopUpd:)
				(fox
					setMotion: MoveTo (+ (fox x?) 18) (+ (fox y?) 0)
					setCycle: EndLoop self
				)
			)
			(12
				(fox
					setLoop: 4
					setCel: 0
					cycleSpeed: 0
					setStep: 6 6
					moveSpeed: 0
					setCycle: Forward
					setMotion: MoveTo (+ (fox x?) 250) (+ (fox y?) 5)
				)
				(ego view: vEgoDrawWeapon loop: 0 cel: 0 setCycle: EndLoop)
				(= cycles 35)
			)
			(13
				(NormalEgo)
				(HandsOn)
				(fox dispose:)
			)
		)
	)
)

(instance flameDartFox of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= trapSprung TRUE)
				(= foxOnScreen FALSE)
				(fox setScript: 0)
				(ego
					view: vEgoMagicFlameDart
					setLoop: (if (< (ego x?) (fox x?)) 0 else 1)
					cel: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(1
				(ego setCycle: EndLoop)
				(dart
					setLoop: 2
					setStep: 18 12
					setPri: 12
					ignoreActors: 1
					posn: (ego x?) (ego y?)
					show:
					setCycle: Forward
					startUpd:
				)
				(dart setMotion: MoveTo (+ (fox x?) 3) (fox y?) self)
			)
			(2
				(dart setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(3
				(fox loop: 2 cel: 0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(4
				(HandsOn)
				(NormalEgo)
				(HighPrint 67 22)
				;That was strange, no sooner did you kill the fox than it vanishes.
				(= seconds 2)
			)
		)
	)
)

(instance getTrap of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: illegalBits: 0)
				(if (ego inRect: 41 109 111 138)
					(self cue:)
				else
					(ego setMotion: MoveTo 112 130 self)
				)
			)
			(1
				(ego setMotion: MoveTo 58 113 self)
			)
			(2
				(ego view: vEgoThrowing loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(3
				(HighPrint 67 28)
				;The trap seems to be caught on something.  You can't pull it loose.
				(self cue:)
			)
			(4 (= cycles 8))
			(5 (ego setCycle: BegLoop self))
			(6 (NormalEgo) (HandsOn))
		)
	)
)

(instance thrashAndWait of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fox setLoop: 0 setCycle: EndLoop self)
			)
			(1
				(fox cel: 0)
				(= seconds (Random 3 8))
			)
			(2 (self changeState: 0))
		)
	)
)

(instance peekABooScript1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 12 30)))
			(1
				(while (== (= local10 (Random 0 4)) local11)
				)
				(self cue:)
			)
			(2
				(client
					x: [eyesPosn local10]
					y: [eyesPosn (+ local10 5)]
					setCycle: EndLoop self
				)
			)
			(3 (self changeState: 0))
		)
	)
)

(instance peekABooScript2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 12 30)))
			(1
				(while (== (= local10 (Random 0 4)) local11)
				)
				(self cue:)
			)
			(2
				(client
					x: [eyesPosn local11]
					y: [eyesPosn (+ local11 5)]
					setCycle: EndLoop self
				)
			)
			(3 (self changeState: 0))
		)
	)
)

(instance eyes1 of Prop
	(properties
		y 67
		x 205
		view vBushEyes
		cycleSpeed 1
	)
)

(instance eyes2 of Prop
	(properties
		y 74
		x 180
		view vBushEyes
		loop 1
		cycleSpeed 1
	)
)

(instance southBush of View
	(properties
		y 207
		x 162
		view vBushes
		loop 2
		cel 1
	)
)

(instance fox of TargActor
	(properties
		y 110
		x 90
		view vFox
		cycleSpeed 3
		targDeltaY 10
	)
	
	(method (handleEvent event &tmp spell)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'chat')
						(HighPrint 67 29)
						;"I am in such pain and agony I cannot think.   Help me, good adventurer, please."
						)
					((Said 'pat/fox')
						(HighPrint 67 30)
						;That's not worth any game points, and will not make the fox feel any better.
						)
					((Said 'throw/dagger,dagger') (KnifeCast self))
					((Said 'throw/boulder') (RockCast self))
					(
					(or (Said 'look/feet,feet') (Said 'ask//feet,feet'))
					(HighPrint 67 31)
					;"My foot is caught in this trap.  If I had hands like you, instead of paws, I could free myself."
					)
					((Said 'ask>')
						(if (Said '//help,trap')
							(if (not talkedToFox)
								(HighPrint 67 21)
								;"My foot is caught in this cruel trap and I am in great pain.  Surely you could take a minute and set me free."
								(= talkedToFox TRUE)
							else
								(HighPrint 67 29)
								;"I am in such pain and agony I cannot think.   Help me, good adventurer, please."
							)
						else
							(HighPrint 67 29)
							;"I am in such pain and agony I cannot think.   Help me, good adventurer, please."
							(event claimed: TRUE)
						)
					)
					(
						(or
							(Said 'free,free/fox,animal')
							(Said 'open,get/trap')
						)
						(if foxOnScreen
							(if
								(and
									(< (ego y?) 112)
									(< 60 (ego x?))
									(< (ego x?) 112)
								)
								(HighPrint 67 32)
								;Walk around to the other side of the fox, where the trap is.
							else
								(SolvePuzzle POINTS_FREEFOX 10)
								(ego setScript: foxFreed)
							)
						else
							(HighPrint 67 7)
							;The fox is gone.
						)
					)
					((Said 'fight/fox,animal')
						(if foxOnScreen
							(fox setScript: foxWillNotFight)
						else
							(HighPrint 67 33)
							;There's no one here to fight.
						)
					)
					((Said 'kill,attack,fight/fox,animal')
						(if foxOnScreen
							(cond 
								((not (ego has: iSword))
									(HighPrint 67 34)
									;You would need a sword.
								)
								((ego inRect: 55 111 120 120)
									(fox setScript: foxDies)
								)
								(else
									(NotClose)
								)
							)
						else
							(HighPrint 67 33)
							;There's no one here to fight.
						)
					)
				)
			)
		)
	)
	
	(method (getHurt)
		(self setScript: distantFoxDies)
	)
)

(instance dart of Actor
	(properties
		y 500
		x 1000
		z 12
		view vEgoMagicFlameDart
		illegalBits $0000
	)
)

(instance trap of View
	(properties
		y 110
		x 92
		view vFox
		loop 2
		cel 14
		priority 7
	)
)
