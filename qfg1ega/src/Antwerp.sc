;;; Sierra Script 1.0 - (do not remove this comment)
(script# 84)
(include game.sh)
(use Main)
(use CastDart)
(use ThrowKnife)
(use ThrowRock)
(use CastDazz)
(use Target)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rm84 0
)

(local
	antwerpOnScreen
	minDistX =  90
	minDistY =  40
	local3 =  20
	antPushX
	antPushY
	antCageX =  114
	antCageY =  96
	local8 =  1
	local9 =  6
	loosenUpTimer
	fightWithSword
)
(instance rm84 of Room
	(properties
		picture 84
		style DISSOLVE
		north 78
		east 85
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler add: self)
		(StatusLine enable:)
		(cSound fade:)
		(self setLocales: FOREST)
		(if (or (== prevRoomNum 97) (== prevRoomNum 89))
			(Bset fTrollDoorOpen)
		)
		(if (not (Btst fBeenIn94))	;fixed a script error. This fix appeared in QFG1VGA.
			(Bset fBrigsUnaware)
		)
		(NormalEgo)
		(ego init:)
		(if (not (Btst fTrollDoorOpen))
			(ego illegalBits: (| cWHITE cBROWN))
			(rock init: stopUpd:)
		)
		(if (= antwerpOnScreen (not (if (Btst fAntwerpSplit) else (Btst fAntwerpInSky))))
			(antwerp init: loop: 3 setScript: bounceAndLook)
			(LoadMany VIEW
				vAntwerp
				vEgoDaggerDefeated
				vEgoBeginFight
				vEgoDefeated
				vEgoThrowing
				vSecretEntranceRock
			)
			(LoadMany SOUND
				(SoundFX 4)
				(SoundFX 5)
				(SoundFX 6)
				(SoundFX 9)
				54
			)
			(cSound stop:)
			(antSound number: (SoundFX 4) init:)
			(antHits init:)
			(cond 
				((= fightWithSword (ego has: iSword))
					(Load VIEW vEgoFightWithSword)
				)
				((ego has: iDagger)
					(Load VIEW vEgoFightDaggerNoCape)
				)
			)
		)
		(switch prevRoomNum
			(89
				(ego posn: 25 90 setMotion: MoveTo 36 90)
			)
			(97
				(ego posn: 25 90 setMotion: MoveTo 36 90)
			)
			(78
				(ego posn: 160 30 setMotion: MoveTo 198 75)
			)
			(85
				(ego posn: 319 100 setMotion: MoveTo 295 100)
			)
		)
	)
	
	(method (doit)
		(cond 
			((and (== (ego onControl: origin) cLRED) (== (ego loop?) 3))
				(curRoom newRoom: 78)
			)
			((<= (ego x?) 25)
				(curRoom newRoom: 89)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(Bset fBeenIn84)
		(if (!= newRoomNum 89)
			(Bclr fHidenGoseke)
			(if (not (if (Btst fBeatFred) else (Btst fBeatFred89)))
				(Bclr fTrollDoorOpen)
				(Bclr fTrollDoorUnlocked)
			)
		)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [str 30] spell targObj)
		(switch (event type?)
			(mouseDown
				(cond 
					((super handleEvent: event))
					((MouseClaimed ego event shiftDown)
						(HighPrint (Format @str 84 0 @userName)
							;By golly, It's %s!
						)
					)
				)
			)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look,search>')
						(cond 
							((Said '[<at,around][/place,area]')
								(cond 
									((Btst fAntwerpSplit)
										(HighPrint 84 1)
										;Now that the big Antwerp has split, this corner of the forest seems strangely quiet.
									)
									((Btst fAntwerpInSky)
										(HighPrint 84 2)
										;You look around and see rocks and grass, but no Antwerp.
									)
									(else
										(HighPrint 84 3)
										;You see rocks, grass and an Antwerp.
									)
								)
							)
							((Said '[<at,around][/antwerp,monster,creature,beast,animal,bouncer]')
								(cond 
									((Btst fAntwerpSplit)
										(HighPrint 84 4)
										;The only Antwerp known in these parts has split... into parts.
									)
									((Btst fAntwerpInSky)
										(HighPrint 84 5)
										;The Antwerp seems to have flown the coop.
									)
									(else
										(HighPrint 84 6)
										;Antwerps are on the endangered species list.  They are rarely seen.
									)
								)
							)
							((Said '[<at][/boulder,boulder]')
								(cond 
									((not (ego inRect: 0 72 66 102))
										(HighPrint 84 7)
										;The rocks were left here by some receding glacier.
									)
									((Btst fTrollDoorOpen)
										(HighPrint 84 8)
										;There is a narrow cave entrance among the rocks.
									)
									(else
										(HighPrint 84 9)
										;You find a keyhole concealed in a crack in the rock.
									)
								)
							)
							((or (Said '/cave,entrance') (Said '<in'))
								(if (Btst fTrollDoorOpen)
									(HighPrint 84 10)
									;There is a narrow cave entrance among the rocks.  Inside, you see a dark passage through the hillside.
								else
									(HighPrint 84 11)
									;You see nothing like that here.
								)
							)
							((Said '/door,keyhole,hasp')
								(if (ego inRect: 0 72 66 102)
									(HighPrint 84 9)
									;You find a keyhole concealed in a crack in the rock.
								else
									(HighPrint 84 12)
									;You do not find anything nearby.
								)
							)
							((or (Said '<up') (Said '/sky'))
								(HighPrint 84 13)
								;The sky is clear.
							)
							((or (Said '<down') (Said '/ground,grass'))
								(HighPrint 84 14)
								;The grass is luscious, just the thing for hungry herbivores.
							)
							((Said '/south,west')
								(HighPrint 84 15)
								;The way is impassable.  Sheer rock cliffs rise to serious heights.
							)
							((Said '/east,north')	
								(HighPrint 84 16)
								;The forest extends to the east and north.
							)
						)
					)
					((Said 'cast>')
						(switch (= spell (SaidSpell event))
							(DETMAGIC
								(if (CastSpell spell)
									(HighPrint 84 17)
									;You detect no magic here.
								)
							)
							(DAZZLE
								(if (CastSpell spell)
									(CastDazz)
									(if antwerpOnScreen
										(HighPrint 84 18)
										;Antwerps aren't dazzled easily.
									)
								)
							)
							(FLAMEDART
								(if (CastSpell spell)
									(if (cast contains: antwerp)
										(Face ego antwerp)
										(RedrawCast)
									)
									(if antwerpOnScreen
										(CastDart antwerp)
										(antwerp setScript: antwerpAway)
									else
										(CastDart 0)
									)
								)
							)
							(OPEN
								(cond 
									((not (ego inRect: 0 72 66 102))
										(HighPrint 84 19)
										;You aren't close enough to a lock.
									)
									((CastSpell spell)
										(if (Btst fTrollDoorUnlocked)
											(HighPrint 84 20)
											;It's already unlocked.
										else
											(self setScript: sMagicRock)
										)
									)
								)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					((Said 'throw/dagger,dagger')
						(= targObj (if (cast contains: antwerp) antwerp else 0))
						(if (ThrowKnife targObj)
							(if (cast contains: antwerp)
								(+= missedDaggers hitDaggers)
								(= hitDaggers 0)
								(Face ego antwerp)
								(RedrawCast)
							)
							(if antwerpOnScreen
								(antwerp setScript: antwerpAway)
							)
						)
					)
					((Said 'throw/boulder')
						(= targObj (if (cast contains: antwerp) antwerp else 0))
						(if (ThrowRock targObj)
							(if (cast contains: antwerp)
								(Face ego antwerp)
								(RedrawCast)
							)
							(if antwerpOnScreen
								(antwerp setScript: antwerpAway)
							)
						)
					)
					((Said 'fight,kill[/bouncer,antwerp,animal,beast,monster,creature]')
						(cond 
							((Btst fAntwerpSplit)
								(HighPrint 84 4)
								;The only Antwerp known in these parts has split... into parts.
								)
							((Btst fAntwerpInSky)
								(HighPrint 84 5)
								;The Antwerp seems to have flown the coop.
								)
							((or fightWithSword (ego has: iDagger))
								(antwerp setScript: fightAntwerp)
							)
							(else
								(HighPrint 84 21)
								;You have no weapon with which to fight the Antwerp.
								(HighPrint 84 22)
								;However, you bravely attack the bouncing beast with your bare hands.
								(ego setScript: bareHandAttack)
							)
						)
					)
					((Said 'feed[/antwerp,creature,monster,bouncer,animal,beast]')
						(cond 
							((Btst fAntwerpSplit)
								(HighPrint 84 23)
								;The only Antwerp known to these parts, split ...into parts.
							)
							((Btst fAntwerpInSky)
								(HighPrint 84 5)
								;The Antwerp seems to have flown the coop.
							)
							(else
								(HighPrint 84 24)
								;He's on a diet.
							)
						)
					)
					(
						(or
							(Said 'unlock,lockpick/door,boulder,hasp,keyhole')
							(Said 'use/key,lockpick')
							(Said 'open/hasp,keyhole')
							(Said 'put,fill<in/key/hasp')
						)
						(cond 
							((not (ego inRect: 0 72 66 102))
								(HighPrint 84 25)
								;You don't see any locks nearby.
							)
							((Btst fTrollDoorUnlocked)
								(HighPrint 84 20)
								;It's already unlocked.
							)
							((and (ego has: iBrassKey) (Btst fGotBrutusKey))
								(HighPrint 84 26)
								;The lock in the rock clicks open.
								(Bset fTrollDoorUnlocked)
							)
							((not (CanPickLocks))
								(HighPrint 84 27)
								;You'd have a much easier time of this if you had the key.
							)
							((TrySkill PICK 85 lockPickBonus)
								(HighPrint 84 28)
								;Ah, got it!  The lock in the rock clicks open.
								(Bset fTrollDoorUnlocked)
							)
							(else
								(HighPrint 84 29)
								;The lock is beyond your present skill.  It might help if you had the key.
								(if (not (ego has: iThiefKit))
									(HighPrint 84 30)
									;... Or at least a better set of tools.
								)
							)
						)
					)
					((Said 'shove,move,force,get,open/boulder,door')
						(if (not (Btst fTrollDoorOpen))
							(if (Btst fTrollDoorUnlocked)
								(if (TrySkill STR 40 0)
									(rock setScript: sMoveRock)
								else
									(HighPrint 84 31)
									;You are not strong enough yet to open the rock door.
								)
							else
								(HighPrint 84 32)
								;Despite your mightiest efforts, the rock does not move.
							)
						else
							(HighPrint 84 33)
							;The rock door has already been opened.
						)
					)
					(
						(or
							(Said 'say,holler<hiden/goseke')
							(Said 'hiden/goseke')
						)
						(if
							(and
								(Btst fSpiedOnThieves)
								(ego inRect: 0 72 66 102)
								(Btst fTrollDoorOpen)
								(not (Btst fHidenGoseke))
								(not (Btst fBeatFred))
							)
							(Bset fHidenGoseke)
							(SolvePuzzle f84HidenGoseke 5)
							(HighPrint 84 34)
							;You hear the sound of someone...or something...moving deeper into the cave to let you pass.
						else
							(HighPrint 84 35)
							;Ok, you say Hiden Goseke.
						)
					)
					((Said 'enter/cave,entrance')
						(HighPrint 84 36)
						;Go ahead. If you dare.
					)
				)
			)
		)
	)
)

(instance antwerp of TargActor
	(properties
		y 96
		x 114
		yStep 4
		view vAntwerp
		loop 2
		cycleSpeed 1
		xStep 4
		moveSpeed 1
	)
	
	(method (doit)
		(if
			(and
				(== (self loop?) 0)
				(== (antSound loop?) 0)
				(== (self cel?) 0)
				(!= (self script?) fightAntwerp)
			)
			(antSound loop: 1 play:)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(mouseDown
				(if (MouseClaimed antwerp event shiftDown)
					(HighPrint 84 38)
					;By golly, it's an Antwerp!
				)
			)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'play/[antwerp,monster,creature,bouncer,animal,beast]')
						(HighPrint 84 39)
						;The Antwerp plays rough.
					)
				)
			)
		)
	)
	
	(method (getHurt)
		(HighPrint 84 37)
		;That's funny.  It bounced right off.
	)
)

(instance bounceAndLook of Script
	(method (doit)
		(cond 
			(
				(and
					(< (- (antwerp x?) minDistX) (ego x?))
					(< (ego x?) (+ (antwerp x?) minDistX))
					(< (- (antwerp y?) minDistY) (ego y?))
					(< (ego y?) (+ (antwerp y?) minDistY))
				)
				(antwerp cycleSpeed: 0 moveSpeed: 0)
				(client setScript: antwerpFollow)
			)
			(
				(or
					(and
						(< (antwerp x?) (ego x?))
						(!= (antwerp loop?) 0)
					)
					(and
						(> (antwerp x?) (ego x?))
						(!= (antwerp loop?) 1)
					)
				)
				(self changeState: 0)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(antSound number: (SoundFX 4) play:)
				(if (< (antwerp x?) (ego x?))
					(antwerp
						loop: 0
						cycleSpeed: 1
						moveSpeed: 1
						setCycle: EndLoop self
					)
				else
					(antwerp
						loop: 1
						cycleSpeed: 1
						moveSpeed: 1
						setCycle: EndLoop self
					)
				)
			)
			(1
				(self changeState: 0)
			)
		)
	)
)

(instance antwerpFollow of Script
	;EO: This is sluicebox's decompilation of the doit method.
	(method (doit)
		(= antPushX (- (ego x:) (antwerp x:)))
		(= antPushY (- (ego y:) (antwerp y:)))
		(if (and (< -45 antPushX 45) (< -8 antPushY 8))
			(antwerp cycleSpeed: 1 moveSpeed: 1)
			(client setScript: antwerpPushes)
		)
		(cond
			((-- local8))
			(
				(or
					(not (< (- minDistX) antPushX minDistX))
					(not (< (- minDistY) antPushY minDistY))
				)
				(self changeState: 7)
			)
			(
				(and
					(< (- (* antPushX minDistY) (* antPushY local3)) 0)
					(< (+ (* antPushY local3) (* antPushX minDistY)) 0)
				)
				(self changeState: 1)
			)
			((and (> (- (* antPushX minDistY) (* antPushY local3)) 0) (< antPushX 0))
				(self changeState: 2)
			)
			((< (+ (* antPushY local3) (* antPushX minDistY)) 0)
				(self changeState: 3)
			)
			((> (- (* antPushX minDistY) (* antPushY local3)) 0)
				(self changeState: 4)
			)
			((and (< (- (* antPushX minDistY) (* antPushY local3)) 0) (> antPushX 0))
				(self changeState: 5)
			)
			(else
				(self changeState: 6)
			)
		)
		(if (not local8)
			(= local8 local9)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(if
					(and
						(< (- antCageX minDistX) (ego x?))
						(< (ego x?) (+ antCageX minDistX))
						(< (- antCageY minDistY) (ego y?))
						(< (ego y?) (+ antCageY minDistY))
					)
					(cond 
						((<= (antwerp x?) antCageX)
							(antwerp
								setLoop: 1
								setCycle: Forward
								setMotion: MoveTo antCageX (ego y?)
							)
						)
						((<= (/ (+ (antwerp x?) (ego x?)) 2) antCageX)
							(antwerp
								setLoop: 1
								setCycle: Forward
								setMotion: MoveTo antCageX (ego y?)
							)
						)
						(else
							(antwerp
								setLoop: 1
								setCycle: Forward
								setMotion: MoveTo (+ (antwerp x?) (ego x?)) (ego y?)
							)
						)
					)
				else
					(antwerp
						setLoop: 1
						setCycle: Forward
						setMotion: MoveTo antCageX antCageY
					)
				)
			)
			(2
				(antwerp
					setLoop: 1
					setCycle: Forward
					setMotion:
						MoveTo
						(+ (antwerp x?) (/ antPushY 2))
						(- (antwerp y?) (/ antPushY 2))
				)
			)
			(3
				(antwerp
					setLoop: 0
					setCycle: Forward
					setMotion:
						MoveTo
						(- (antwerp x?) (/ antPushY 2))
						(- (antwerp y?) (/ antPushY 2))
				)
			)
			(4
				(if
					(and
						(< (- antCageX minDistX) (ego x?))
						(< (ego x?) (+ antCageX minDistX))
						(< (- antCageY minDistY) (ego y?))
						(< (ego y?) (+ antCageY minDistY))
					)
					(cond 
						((>= (antwerp x?) antCageX)
							(antwerp
								setLoop: 0
								setCycle: Forward
								setMotion: MoveTo antCageX (ego y?)
							)
						)
						((>= (/ (+ (antwerp x?) (ego x?)) 2) antCageX)
							(antwerp
								setLoop: 0
								setCycle: Forward
								setMotion: MoveTo antCageX (ego y?)
							)
						)
						(else
							(antwerp
								setLoop: 0
								setCycle: Forward
								setMotion: MoveTo (+ (antwerp x?) (ego x?)) (ego y?)
							)
						)
					)
				else
					(antwerp
						setLoop: 0
						setCycle: Forward
						setMotion: MoveTo antCageX antCageY
					)
				)
			)
			(5
				(antwerp
					setLoop: 0
					setCycle: Forward
					setMotion:
						MoveTo
						(- (antwerp x?) (/ antPushY 2))
						(+ (antwerp y?) (/ antPushY 2))
				)
			)
			(6
				(antwerp
					setLoop: 1
					setCycle: Forward
					setMotion:
						MoveTo
						(+ (antwerp x?) (/ antPushY 2))
						(+ (antwerp y?) (/ antPushY 2))
				)
			)
			(7
				(antwerp
					setLoop: 0
					setCycle: Forward
					setMotion: MoveTo antCageX antCageY self
				)
			)
			(8
				(client setScript: bounceAndLook)
			)
		)
	)
)

(instance antwerpPushes of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (< (ego x?) (antwerp x?))
					(ego
						ignoreActors:
						setMotion: MoveTo (- (antwerp x?) 30) (antwerp y?) self
					)
				else
					(ego
						ignoreActors:
						setMotion: MoveTo (+ (antwerp x?) 30) (antwerp y?) self
					)
				)
			)
			(1
				(if (< (ego x?) (antwerp x?))
					(antwerp
						setLoop: 1
						cel: 0
						setCycle: Forward
						setMotion: MoveTo (+ (ego x?) 21) (antwerp y?) self
					)
					(ego
						view: vEgoDaggerDefeated
						setLoop: loopS
						cycleSpeed: 1
						setCycle: EndLoop
						setMotion: MoveTo (- (ego x?) 10) (ego y?)
					)
				else
					(antwerp
						setLoop: 0
						cel: 0
						setCycle: Forward
						setMotion: MoveTo (- (ego x?) 21) (antwerp y?) self
					)
					(ego
						view: vEgoDaggerDefeated
						setLoop: loopN
						cycleSpeed: 1
						setCycle: EndLoop
						setMotion: MoveTo (+ (ego x?) 10) (ego y?)
					)
				)
				(antHits play:)
			)
			(2
				(if (not (TakeDamage 10))
					(self changeState: 5)
				else
					(NormalEgo)
					(if (not (Btst fTrollDoorOpen))
						(ego illegalBits: (| cWHITE cBROWN))
					)
					(= antPushX 45)
					(if (< (ego x?) (antwerp x?))
						(if (> 226 (+ (antwerp x?) antPushX))
							(= antPushX (- 226 (antwerp x?)))
						)
						(antwerp
							setLoop: 1
							cel: 0
							setCycle: Forward
							setMotion: MoveTo (+ (antwerp x?) antPushX) (antwerp y?) self
						)
						(ego loop: loopE)
					else
						(if (> 82 (- (antwerp x?) antPushX))
							(= antPushX (- (antwerp x?) 82))
						)
						(antwerp
							setLoop: 0
							cel: 0
							setCycle: Forward
							setMotion: MoveTo (- (antwerp x?) antPushX) (antwerp y?) self
						)
						(ego loop: loopW)
					)
					(HandsOn)
				)
			)
			(3
				(= seconds 3)
			)
			(4
				(antwerp cycleSpeed: 0 moveSpeed: 0)
				(client setScript: antwerpFollow)
			)
			(5
				(= antPushX 25)
				(if (< (ego x?) (antwerp x?))
					(if (> 226 (+ (antwerp x?) antPushX))
						(= antPushX (- 226 (antwerp x?)))
					)
					(antwerp
						setLoop: 1
						cel: 0
						setCycle: Forward
						setMotion: MoveTo (+ (antwerp x?) antPushX) (antwerp y?)
					)
					(ego loop: loopS)
				else
					(if (> 82 (- (antwerp x?) antPushX))
						(= antPushX (- (antwerp x?) 82))
					)
					(antwerp
						setLoop: 0
						cel: 0
						setCycle: Forward
						setMotion: MoveTo (- (antwerp x?) antPushX) (antwerp y?)
					)
					(ego loop: loopW)
				)
				(ego
					view: vEgoDefeated
					setLoop: loopE
					cel: 0
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(6
				(EgoDead 84 40
					#icon vEgoClimbing 2 5
					#title {Your figure remains still and silent.}
					;The old ticker just couldn't keep going. 
					; Maybe you shouldn't have missed the annual visit with your local Healer.
				)
			)
		)
	)
)

(instance fightAntwerp of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(TimePrint 5 84 41)
				;Cockily, you loosen up to fight.
				(antwerp setMotion: MoveTo 114 96)
				(ego ignoreActors: setMotion: MoveTo 250 100 self)
			)
			(1
				(ego
					view: vEgoBeginFight
					setLoop: (if fightWithSword 0 else 2)
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2
				(if fightWithSword
					(ego view: vEgoFightWithSword setLoop: 2)
				else
					(ego view: vEgoFightDaggerNoCape setLoop: 5)
				)
				(ego cel: 0 setCycle: EndLoop self)
			)
			(3
				(ego
					cel: 0
					setLoop: (if fightWithSword 6 else 1)
					setCycle: EndLoop self
				)
			)
			(4
				(ego
					cel: 0
					setLoop: (if fightWithSword 3 else 0)
					setCycle: EndLoop self
				)
			)
			(5
				(ego
					cel: 0
					setLoop: (if fightWithSword 7 else 0)
					setCycle: EndLoop self
				)
			)
			(6
				(if loosenUpTimer
					(-- loosenUpTimer)
					(self changeState: 2)
				else
					(= loosenUpTimer 4)
					(= cycles 4)
				)
			)
			(7
				(ego
					cel: 0
					setLoop: (if fightWithSword 4 else 3)
					setCycle: EndLoop self
				)
			)
			(8
				(if loosenUpTimer
					(-- loosenUpTimer)
					(self changeState: 7)
				else
					(ego
						view: vEgoBeginFight
						setLoop: (if fightWithSword 0 else 3)
						cel: 5
						setCycle: BegLoop self
					)
				)
			)
			(9
				(TimePrint 4 84 42)
				;Confident and loose, you approach the Antwerp.
				(NormalEgo)
				(ego
					setLoop: 1
					moveSpeed: 1
					cycleSpeed: 1
					ignoreActors:
					setMotion: MoveTo 158 96 self
				)
			)
			(10
				(ego
					view: vEgoBeginFight
					setLoop: (if fightWithSword 0 else 2)
					cel: 0
					moveSpeed: 0
					cycleSpeed: 0
					setCycle: EndLoop self
				)
			)
			(11
				(antwerp
					setLoop: 0
					setCycle: CycleTo 2 1
					setStep: 1 5
					setMotion: MoveTo 114 0
				)
				(antSound number: (SoundFX 4) play:)
				(= cycles 13)
			)
			(12
				(antSound stop:)
				(if fightWithSword
					(ego view: vEgoFightWithSword setLoop: 3)
				else
					(ego view: vEgoFightDaggerNoCape setLoop: 5)
				)
				(ego cel: 0 setCycle: EndLoop self)
			)
			(13
				(ego loop: (if fightWithSword 1 else 8) cel: 0)
				(antwerp setCycle: EndLoop setMotion: MoveTo 114 96 self)
			)
			(14
				(antwerp setCycle: Forward)
				(= cycles 10)
			)
			(15
				(antwerp
					yStep: 7
					setCycle: CycleTo 2 1
					ignoreActors: 1
					ignoreHorizon:
					illegalBits: 0
					setMotion: MoveTo 114 0
				)
				(antSound number: (SoundFX 5) play:)
				(= cycles 10)
			)
			(16
				(antSound stop:)
				(if fightWithSword
					(ego view: vEgoFightWithSword setLoop: 3)
				else
					(ego view: vEgoFightDaggerNoCape setLoop: 5)
				)
				(ego cel: 0 setCycle: EndLoop self)
			)
			(17 (= cycles 16))
			(18
				(antwerp setMotion: MoveTo 114 96 self)
				(ego loop: (if fightWithSword 1 else 8) cel: 0)
			)
			;CI: NOTE: This case already exists, and will probably never be executed
;			(18
;				(antSound number: (SoundFX 9) play:)
;				(antwerp setCycle: CycleTo 2 1 setMotion: MoveTo 114 92 self)
;			)
			(19
				(antSound stop:)
				(antwerp setCycle: BegLoop self)
			)
			(20
				(antSound number: (SoundFX 6) play:)
				(antwerp yStep: 10 setMotion: MoveTo 114 -10)
				(= cycles 10)
			)
			(21
				(antSound stop:)
				(if fightWithSword
					(ego view: vEgoFightWithSword setLoop: 5 cel: 0)
				else
					(ego view: vEgoFightDaggerNoCape setLoop: 5)
				)
				(ego cel: 0 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(22
				(ego
					view: vEgoBeginFight
					setLoop: (if fightWithSword 0 else 3)
					cel: (if fightWithSword 5 else 2)
					setCycle: BegLoop self
				)
			)
			(23
				(NormalEgo)
				(if (not (Btst fTrollDoorOpen))
					(ego illegalBits: (| cWHITE cBROWN))
				)
				(Bset fAntwerpInSky)
				(HandsOn)
				(TimePrint 3 84 43)
				;"Holy Mackerel!"
			)
		)
	)
)

(instance antSound of Sound
	(properties
		priority 5
	)
)

(instance antHits of Sound
	(properties
		number 54
		priority 6
	)
)

(instance rock of Actor
	(properties
		y 89
		x 18
		yStep 1
		view vSecretEntranceRock
		xStep 1
	)
)

(instance sMoveRock of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: vEgoThrowing
					setLoop: 1
					cel: 0
					cycleSpeed: 2
					setCycle: CycleTo 2 1 self
				)
			)
			(1
				(rock
					setCycle: 0
					startUpd:
					illegalBits: 0
					xStep: 3
					setMotion: MoveTo -18 89 self
				)
				(ego setCycle: BegLoop)
			)
			(2
				(ChangeGait MOVE_WALK 0)
				(ego illegalBits: cWHITE)
				(HandsOn)
				(SolvePuzzle f84FindTrollCave 10)
				(Bset fTrollDoorOpen)
				(rock dispose:)
			)
		)
	)
)

(instance sMagicRock of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: vEgoMagicDetect
					illegalBits: 0
					setLoop: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(Bset fTrollDoorUnlocked)
				(ChangeGait MOVE_WALK FALSE)
				(if (< [egoStats OPEN] 50)
					(HighPrint 84 44)
					;Your spell has unlocked the lock on the rock,
					; but it is not yet powerful enough to open the rock door.
					(HandsOn)
					(NormalEgo)
					(ego illegalBits: (| cWHITE cBROWN))
					(client setScript: 0)
				else
					(rock
						setCycle: 0
						startUpd:
						illegalBits: 0
						setMotion: MoveTo -18 89 self
					)
				)
			)
			(2
				(SolvePuzzle f84FindTrollCave 10)
				(Bset fTrollDoorOpen)
				(rock dispose:)
				(NormalEgo)
				(HandsOn)
			)
		)
	)
)

(instance bareHandAttack of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (antwerp x?) (antwerp y?) self)
			)
			(1 (client setScript: 0))
		)
	)
)

(instance antwerpAway of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= antwerpOnScreen FALSE)
				(Bset fAntwerpInSky)
				(antwerp setCycle: EndLoop self)
			)
			(1
				(antwerp
					setLoop: 2
					cel: 0
					ignoreHorizon:
					illegalBits: 0
					setPri: (antwerp priority?)
					setStep: 4 18
					setCycle: BegLoop
					setMotion: MoveTo 200 -10 self
				)
			)
			(2
				(HighPrint 84 45)
				;You seem to have scared the Antwerp with your behavior.
			)
		)
	)
)
