;;; Sierra Script 1.0 - (do not remove this comment)
(script# ENCOUNTER) ;210
(include game.sh)
(use Main)
(use CastDart)
(use ThrowKnife)
(use ThrowRock)
(use CastCalm)
(use CastDazz)
(use Target)
(use Chase)
(use Motion)
(use Game)
(use Invent)
(use User)
(use System)

(public
	Encounter 0
	smallMonster 1
)

(local
	gotClaws
	gotBeard
	searchedMonster
	egoDirection
	local4
	local5
	theSmallMonster
	viewDeadMonster
	local8
	local9
	local10
	local11
	SearchMonsterX
	SearchMonsterY
	[local36 12] = [500 40 40 40 40 50 30 35 40 50 30 40]
	[monsterHP 12] = [10000 133 186 53 86 113 60 140 93 186 60 100]
	[whichMonster 12] = ['/aardvark' '/bear' '/bull,bull' '/saurus' '/mantray' '/cheetaur' '/goblin' '/troll' '/ogre,giant' '/dragon,(rex[<saurus])' '/bandit,man' '/leader,female']
	local50
	monsterHurt
	local52
)


(procedure (GetMonsterIndex monster &tmp temp0)
	(return
		(if (and (<= vBear monster) (<= monster vBrigandLeader))
			;EO: According to the whichMonster array, 470 was intended for the Brigand Leader. Was she originally going to be a possible combatant?
			;CI: Likely, since she had a close-up view when transforming, it was simpler to group all "Arena" scenes together.
			(return (+ 1 (/ (- monster vBear) 5)))
		else
			(return 0)
		)
	)
)

(procedure (SetEgoDirection)
	(cond 
		((> egoX 310) (= egoDirection EAST))
		((< egoX 10) (= egoDirection WEST))
		((< egoY 100) (= egoDirection NORTH))
		((> egoY 180) (= egoDirection SOUTH))
	)
)

(procedure (localproc_0071 param1)
	(Bclr FLAG_351)
	(ChangeGait MOVE_WALK FALSE)
	(= local9 1)
	(= local10 1)
	(if
		(or
			(not theSmallMonster)
			(not (cast contains: theSmallMonster))
		)
		((= theSmallMonster smallMonster) init:)
	)
	(= viewDeadMonster (+ monsterNum 1))
	;luckPoints are increased by 1/12th of the monster's maximum HP.
	(SkillUsed LUCK (/ [monsterHP (GetMonsterIndex monsterNum)] tryStatRandomEncounter))
	(HandsOn)
	(NormalEgo)
	(if param1
		(ego posn: 160 160)
		(theSmallMonster posn: 115 150)
	)
	(ego loop: 1 illegalBits: (curRoom illBits?) init:)
	(theSmallMonster
		view: viewDeadMonster
		setLoop: 0
		cel: 0
		ignoreActors: 0
		setMotion: 0
		cycleSpeed: 1
	)
	(if (!= monsterNum vGoblin)
		(theSmallMonster setCycle: EndLoop)
	)
)

(procedure (SetMonsterChase param1)
	(param1
		illegalBits: 0
		setMotion: Chase ego [local36 (GetMonsterIndex monsterNum)] Encounter
	)
	(= local50 1)
	(param1 setScript: 0)
)

(procedure (SetMonsterStats defaultMonster &tmp tmpMonsterNum retMonster)
	(if (and argc defaultMonster)
		;if a monster is specified explicitly, we'll set it
		(= retMonster defaultMonster)
	else
		;otherwise, we'll pick a monster encounter at random
		(cond 
			((< [egoStats EXPER] 1000)
				;if experience is < 1000, then you only get Goblin, Saurus or Brigand at the daytime
				(= tmpMonsterNum (Random 0 3))
			)
			((< [egoStats EXPER] 2000)
				;if your experience is < 2000, you get anybody at the day (except trolls)
				(= tmpMonsterNum (Random 0 6))
			)
			(else 
				;if it's higher than 2000, you don't get the mini saurus anymore.
				(= tmpMonsterNum (Random 2 6))
			)
		)
		(if Night 
			;add 2 if it's nighttime.
			(= tmpMonsterNum (+ tmpMonsterNum 2))
		)
		(= retMonster
			(switch tmpMonsterNum
				(0 vGoblin)
				(1 vSaurus)
				(2 vGoblin)
				(3 vBrigand)
				(4 vMantray)
				(5 vCheetaur)
				(6 vDragon)
				(else  vTroll)
			)
		)
	)
	
	(if (OneOf curRoomNum 85 86 92)
		(if (or (<= tmpMonsterNum 4) (not Night))
			(= retMonster vBrigand)
		else
			(= retMonster vTroll)
		)
	)
	(= bucks 0)
	(cond 
		((== retMonster vGoblin) (= bucks (Random 1 10)))
		((== retMonster vBrigand) (= bucks (Random 5 25)))
		((== retMonster vTroll) (= bucks (Random 20 50)))
		((== retMonster vMinotaur) (= bucks 50))
	)
	(return retMonster)
)

(procedure (localproc_0d25 vMonster actMonster &tmp curRoomAmbushX curRoomAmbushY temp2 temp3 temp4)
	;CI: NOTE: Monster Views and Room numbers are very closely tied together.
	;EO: For this reason, I'll just be using the view defines/
	(= temp2 (& (curRoom entrances?) (| reEAST reWEST)))
	(= temp4 (& (curRoom entrances?) reWEST))
	(= temp3 (& (curRoom entrances?) reEAST))
	(actMonster view: vMonster)
	(if (!= vMonster vMantray)
		(actMonster xStep: 6 yStep: 4 cel: 0)
		(switch vMonster
			(vSaurus
				(actMonster xStep: 5 yStep: 3 setCycle: Forward)
			)
			(vTroll
				(actMonster xStep: 5 yStep: 3 setCycle: Walk)
			)
			(vCheetaur
				(actMonster xStep: 6 yStep: 3 setCycle: Forward)
			)
			(vGoblin
				(actMonster xStep: 4 yStep: 2 setCycle: Walk)
			)
			(vBrigand
				(actMonster xStep: 3 yStep: 2 setCycle: Walk)
			)
			(vDragon
				(actMonster xStep: 8 yStep: 5 setCycle: Forward)
			)
		)
		(if fastEgo
			(actMonster
				xStep: (* (actMonster xStep?) 2)
				yStep: (* (actMonster yStep?) 2)
			)
		)
	)
	(cond 
		(local4
			(if (== vMonster vMantray)
				(theSmallMonster
					setCycle: Walk
					z: 25
					xStep: (Random 4 8)
					yStep: (Random 3 5)
				)
			)
			(= local50 1)
			(switch egoDirection
				(NORTH
					(ego setMotion: MoveTo egoX (- egoY 2))
					(theSmallMonster
						posn:
							(+ (ego x?) (Random 20 40))
							(+
								(ego y?)
								[local36 (GetMonsterIndex vMonster)]
								(Random 20 30)
							)
						setCel: -1
						setMotion: Chase ego [local36 (GetMonsterIndex vMonster)] Encounter
					)
				)
				(SOUTH
					(ego setMotion: MoveTo egoX (+ egoY 2))
					(theSmallMonster
						posn:
							(- (ego x?) (Random 30 50))
							(-
								(ego y?)
								(+ [local36 (GetMonsterIndex vMonster)] (Random 20 30))
							)
						setCel: -1
						setMotion: Chase ego [local36 (GetMonsterIndex vMonster)] Encounter
					)
				)
				(EAST
					(ego setMotion: MoveTo 320 egoY)
					(theSmallMonster
						posn:
							(-
								(ego x?)
								(+ [local36 (GetMonsterIndex vMonster)] (Random 25 40))
							)
							(ego y?)
						setCel: -1
						setMotion: Chase ego [local36 (GetMonsterIndex vMonster)] Encounter
					)
				)
				(WEST
					(ego setMotion: MoveTo 0 egoY)
					(theSmallMonster
						posn:
							(+
								(ego x?)
								[local36 (GetMonsterIndex vMonster)]
								(Random 25 40)
							)
							(ego y?)
						setCel: -1
						setMotion: Chase ego [local36 (GetMonsterIndex vMonster)] Encounter
					)
				)
			)
			(User canControl: TRUE)
		)
		(local5
			(if (== vMonster vMantray)
				(theSmallMonster
					setCycle: Walk
					z: 25
					xStep: (Random 4 8)
					yStep: (Random 3 5)
				)
			)
			(= local50 1)
			(switch egoDirection
				(NORTH
					(= local50 0)
					(ego setMotion: MoveTo egoX 190)
					(if
						(or
							(== vMonster vGoblin)
							(== vMonster vMantray)
							(== vMonster vTroll)
							(== vMonster vBrigand)
						)
						(theSmallMonster setScript: northDelay)
					else
						(= vMonster (= monsterHealth 0))
						(theSmallMonster dispose:)
					)
				)
				(SOUTH
					(ego setMotion: MoveTo egoX 0)
					(if
						(or
							(== vMonster vGoblin)
							(== vMonster vMantray)
							(== vMonster vTroll)
							(== vMonster vBrigand)
						)
						(theSmallMonster
							posn:
								egoX
								(if (== vMonster vMantray) 235 else (- egoY monsterDistY))
							setCel: -1
							setLoop: -1
							setMotion: Chase ego [local36 (GetMonsterIndex vMonster)] Encounter
						)
					else
						(= monsterNum (= monsterHealth 0))
						(theSmallMonster dispose:)
						(= local50 0)
					)
				)
				(EAST
					(ego setMotion: MoveTo 0 egoY)
					(theSmallMonster
						posn: (- egoX monsterDistX) (+ egoY monsterDistY)
						loop: 1
						setCel: -1
						setLoop: -1
						setMotion: Chase ego [local36 (GetMonsterIndex vMonster)] Encounter
					)
				)
				(WEST
					(ego setMotion: MoveTo 320 egoY)
					(theSmallMonster
						posn: (- egoX monsterDistX) (+ egoY monsterDistY)
						loop: 0
						setCel: -1
						setLoop: -1
						setMotion: Chase ego [local36 (GetMonsterIndex vMonster)] Encounter
					)
				)
			)
		)
		((== vMonster vMantray) (theSmallMonster setScript: (ScriptID 436 2)))
		((== temp2 (| reWEST reEAST))
			(switch (= temp2 (if (< (Random 0 1000) 500) reWEST else reEAST))
				(reEAST (actMonster setScript: inEast))
				(reWEST (actMonster setScript: inWest))
			)
		)
		(temp3 (actMonster setScript: inEast))
		(temp4 (actMonster setScript: inWest))
		(else
			(= curRoomAmbushX (curRoom ambushX?))
			(= curRoomAmbushY (curRoom ambushY?))
			(actMonster
				posn: curRoomAmbushX curRoomAmbushY
				setMotion: Chase ego [local36 (GetMonsterIndex vMonster)] Encounter
			)
		)
	)
)

(procedure (SearchMonster &tmp [temp0 60])
	(switch bucks
		(0
			(HighPrint 210 36)
			;What a waste!  No treasure!
			)
		(1
			(HighPrint 210 37)
			;You find a single silver coin, carefully polish it, and place it in your pouch.  What a way to make a living!
			(ego get: iSilver 1)
		)
		(else 
			(HighPrint (Format @temp0 210 38 bucks)
				;You find %d silver coins, and carefully place them in your pouch.
				)
			(ego get: iSilver bucks)
		)
	)
	(if (== monsterNum vTroll)
		(HighPrint 210 39)
		;You thought that the Troll concealed some of its treasure in that thick beard, but you didn't find any there.
		)
	(= bucks 0)
	(if
		(and
			(== curRoomNum daggerRoom)
			(or missedDaggers hitDaggers [invDropped iDagger])
		)
		(ego
			get: iDagger (+ missedDaggers hitDaggers [invDropped iDagger])
		)
		(HighPrint 210 40)
		;You retrieve your daggers.
	)
	(= [invDropped iDagger]
		(= hitDaggers
			(= missedDaggers (= daggerRoom 0))
		)
	)
)

(class EncRoom of Room
	(properties
		;additional properties of EncRoom
		encChance 0
		entrances (| reNORTH reEAST reSOUTH reWEST)
		ambushX 160
		ambushY 100
		illBits cWHITE
	)
)

(instance smallMonster of TargActor
	(properties
		signal $6000
		illegalBits $0000
		targDeltaY -20
	)
	
	(method (getHurt param1)
		(= monsterHurt 1)
		(= local52 0)
		(= monsterHealth (- monsterHealth param1))
	)
)

(instance Encounter of Region
	(properties)
	
	(method (init &tmp curRoomEncChance)
		(Load SCRIPT CHASE)
		(super init: &rest)
		(= local5 (= local4 (= monsterHurt 0)))
		(= theSmallMonster 0)
		(SetEgoDirection)
		(cond 
			(
				(not
					(OneOf
						prevRoomNum
						vBear
						vMinotaur
						vSaurus
						vMantray
						vCheetaur
						vGoblin
						vOgre
						vTroll
						vDragon
						vBrigand
						vBrigandLeader
					)
				)
				(if monsterNum
					(if (and (== monsterNum vTroll) (not Night))
						(ego illegalBits: (curRoom illBits?) init:)
						(ChangeGait MOVE_RUN FALSE)
						(= monsterNum (= monsterHealth 0))
						(HighPrint 210 0)
						;As the sun begins to rise, the Troll runs off to some dark hole.
					else
						(= local5 1)
						(ego illegalBits: (curRoom illBits?) init:)
						(= local8 (= local9 0))
						(SetMonsterStats monsterNum)
					)
				else
					(= curRoomEncChance (curRoom encChance?))
					(if Night
						;chance encounters are doubled at nightTime
						(= curRoomEncChance (* curRoomEncChance 2))
					)
					(if (Btst fBeenIn97)
						;chance encounters are doubled after you've transformed the brigand leader.
						(= curRoomEncChance (* curRoomEncChance 2))
					)
					(cond 
						((> (Rand100) curRoomEncChance))
						(
							(and
								(== egoGait MOVE_SNEAK)
								(TrySkill STEALTH curRoomEncChance)
								(TrySkill LUCK 0 0)
							)
							(HighPrint 210 1)
							;A monster just wandered by.  You hid in the bushes until it passed.
						)
						(else
							(= monsterNum (SetMonsterStats 0))
							(= monsterHealth
								[monsterHP (GetMonsterIndex monsterNum)]
							)
							(= local8 (= local9 0))
						)
					)
					(ego illegalBits: (curRoom illBits?))
				)
			)
			((<= monsterHealth 0)
				(localproc_0071 1)
			)
			(else
				(= local8 (= local9 0))
				(ChangeGait MOVE_RUN FALSE)
				(= local4 1)
				(NormalEgo)
				(ego illegalBits: (curRoom illBits?) posn: 160 140 init:)
				(SetMonsterStats monsterNum)
			)
		)
	)
	
	(method (doit)
		(cond 
			(
				(and
					(not local8)
					(not local9)
					monsterNum
					(or local5 (== (ego onControl: origin) cLCYAN))
				)
				(= local8 1)
				((= theSmallMonster smallMonster) posn: 0 1000 init:)
				(localproc_0d25 monsterNum theSmallMonster)
			)
			(
				(and 
					(== monsterNum vMantray) 
					(Btst FLAG_351)
				)
				(localproc_0071 0)
			)
			(
				(and
					local8
					monsterNum
					(not local10)
					(<= monsterHealth 0)
					(not (Btst FLAG_351))
				)
				(localproc_0071 0)
			)
		)
		(super doit: &rest)
	)
	
	(method (handleEvent event &tmp spell theTheSmallMonster)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(if (Said 'nap,rest')
					(if (and local8 (> monsterHealth 0))
						(HighPrint 210 4)
						;No rest for the weary.  Something's after you!
					else
						(event claimed: FALSE)
					)
				)
				(cond 
					(local9
						(cond 
							((super handleEvent: event))
							((Said 'kill,fight,beat,beat') (AlreadyDone))
							((Said 'eat')
								(HighPrint 210 5)
								;All the excitement of the battle has caused you to lose your appetite.
								)
							((Said 'look>')
								(if
									(or
										(Said '/monster,creature')
										(Said [whichMonster (GetMonsterIndex monsterNum)])
									)
									(HighPrint 210 6)
									;It's dead, of course.
									(HighPrint 210 7)
									;It doesn't smell very good, either.
								else
									(event claimed: 0)
								)
							)
							((Said 'odor>')
								(if
									(or
										(Said '/monster,creature')
										(Said [whichMonster (GetMonsterIndex monsterNum)])
									)
									(HighPrint 210 8)
									;You can't help but smell it.
								else
									(HighPrint 210 9)
									;You only think you can smell that.
									(event claimed: TRUE)
								)
							)
							((Said 'get>')
								(cond 
									((Said '/claw[<cheetaur]')
										(if (== monsterNum vCheetaur)
											(if gotClaws
												(AlreadyDone)
											else
												(HighPrint 210 10)
												;Some of the Cheetaur's claws have been broken off or damaged, apparently in battle.
												;You remove the remaining claws and put them away in your pack.
												(= gotClaws TRUE)
												(ego get: iCheetaurClaw (Random 4 10))
											)
										else
											(HighPrint 210 11)
											;Cheetaur claws are very rare except on Cheetaurs.
										)
									)
									((Said '/beard[<troll]')
										(cond 
											(gotBeard
												(HighPrint 210 12)
												;You already have the Troll's beard.
												)
											((== monsterNum vTroll)
												(HighPrint 210 13)
												;You remove as much of the Troll's beard as you can and put it away in your pack.
												(= gotBeard TRUE) (ego get: iTrollBeard))
											(else
												(HighPrint 210 14)
												;That's not easy to do, especially since this is not a Troll.
												)
										)
									)
								)
							)
							((Said 'search>')
								(if
									(or
										(Said '/monster,creature,body,enemy,[!*]')
										(Said [whichMonster (GetMonsterIndex monsterNum)])
									)
									(if searchedMonster
										(AlreadyDone)
									else
										(= searchedMonster TRUE)
										(ego setScript: searchIt 0 theSmallMonster)
									)
								else
									(HighPrint 210 15)
									;You look all around, but you can't find any of those.
									(event claimed: TRUE)
								)
							)
						)
					)
					((super handleEvent: event))
					((Said 'look>')
						(if
							(or
								(Said '/monster,creature')
								(Said [whichMonster (GetMonsterIndex monsterNum)])
							)
							(switch monsterNum
								(vGoblin
									(HighPrint 210 16)
									;It's an ugly little Goblin.
								)
								(vBrigand
									(HighPrint 210 17)
									;Uh oh, this looks like one of the brigands that have been terrorizing the Valley.  Better hope his friends aren't around.
								)
								(vSaurus
									(HighPrint 210 18)
									;Don't worry, it's just a little purple Saurus.  Watch out for those teeth though.
								)
								(vDragon
									(HighPrint 210 19)
									;Oh, no!  It's a fierce (and hungry) looking Saurus Rex (smarter than your average Saurus).
								)
								(vCheetaur
									(HighPrint 210 20)
									;It's a Cheetaur, a Cheetah Centaur, one of the deadliest creatures in the land.  Hope you've saved your game recently.
								)
								(vOgre
									;EO: Were Ogres originally going to be encountered at random?
									(HighPrint 210 21)
									;It's an Ogre, big, mean, and ugly.  Ogres are said to eat human flesh.
								)
								(vTroll
									(HighPrint 210 22)
									;It's a Troll, hard as stone.
								)
								(vMantray
									(HighPrint 210 23)
									;It's a Mantray, a former sea creature that took flight many generations ago and moved to the mountains.  Don't get stung!
								)
								(else ;no monster present
									(HighPrint 210 24)
									;It's a Program Bug!
								)
							)
						)
					)
					((Said 'fight')
						(if (and local8 monsterNum)
							(curRoom newRoom: monsterNum)
						else
							(HighPrint 210 25)
							;There is nothing here to fight.
						)
					)
					((Said 'cast>')
						(if
							(not
								(OneOf
									curRoomNum
									vBear
									vMinotaur
									vSaurus
									vMantray
									vCheetaur
									vGoblin
									vOgre
									vTroll
									vDragon
									vBrigand
									vBrigandLeader
								)
							)
							(= spell (SaidSpell event))
							(if (CastSpell spell)
								(switch spell
									(0)
									(CALM
										(if (or local50 (== monsterNum vMantray))
											(theSmallMonster setScript: spellDelay 0 22)
										else
											(HighPrint 210 26)
											;Although that spell is not useful here, you at least had a chance to practice it.
										)
									)
									(OPEN
										(HighPrint 210 27)
										;You don't see anything closed around here.
										)
									(DETMAGIC
										(HighPrint 210 28)
										;You sense no magic here.
										)
									(DAZZLE
										(if (or local50 (== monsterNum vMantray))
											(theSmallMonster setScript: spellDelay 0 20)
										else
											(HighPrint 210 26)
											;Although that spell is not useful here, you at least had a chance to practice it.
										)
									)
									(ZAP
										(= zapPower (+ 5 (/ [egoStats ZAP] 10)))
										(if (or (ego has: iDagger) (ego has: iSword))
											(HighPrint 210 29)
											;Your weapon is now magically charged.
										else
											(HighPrint 210 30)
											;You don't seem to have a weapon to charge.
										)
									)
									(FLAMEDART
										(= theTheSmallMonster 0)
										(if local8
											(Face ego theSmallMonster)
											(= theTheSmallMonster theSmallMonster)
										)
										(CastDart theTheSmallMonster)
									)
									(FETCH
										(if local8
											(HighPrint 210 31)
											;Certainly you don't want to Fetch THAT!
											else
											(HighPrint 210 32)
											;Good practice, but there's nothing worth Fetching here.
											)
									)
									(else
										(HighPrint 210 33)
										;Your spell had no effect.
										)
								)
							)
						)
					)
					((Said 'throw/dagger,dagger')
						(= theTheSmallMonster 0)
						(if local8
							(Face ego theSmallMonster)
							(= theTheSmallMonster theSmallMonster)
						)
						(ThrowKnife theTheSmallMonster)
					)
					((Said 'throw/boulder')
						(= theTheSmallMonster 0)
						(if local8
							(Face ego theSmallMonster)
							(= theTheSmallMonster theSmallMonster)
						)
						(ThrowRock theTheSmallMonster)
					)
				)
			)
		)
	)
	
	(method (cue &tmp temp0)
		(= temp0 1)
		(if (and monsterNum (> monsterHealth 0))
			(ChangeGait MOVE_WALK 0)
			(if (and temp0 (or local4 local5))
				(= temp0 0)
				(HighPrint 210 2)
				;You could not escape.  The battle is on.
			else
				(HighPrint 210 3)
				;Hostile intent is evident.  You prepare for battle.
			)
			(curRoom newRoom: monsterNum)
		)
	)
	
	(method (newRoom newRoomNumber)
		(HandsOff)
		(if local8
			(= monsterDistX (- (ego x?) (theSmallMonster x?)))
			(= monsterDistY (- (ego y?) (theSmallMonster y?)))
		)
		(if
			(or
				local9
				(> monsterDistX 180)
				(> monsterDistY 80)
				(and
					(not
						(OneOf
							newRoomNumber
							vBear
							vMinotaur
							vSaurus
							vMantray
							vCheetaur
							vGoblin
							vOgre
							vTroll
							vDragon
							vBrigand
							vBrigandLeader
						)
					)
					(not
						(OneOf
							newRoomNumber
							11 12 17 18 19 23 24 25 26 27 33 34 35 36 42 43 44
							51 56 57 61 62 69 71 74 75 79 80 81 85 86 92
						)
					)
				)
			)
			(= monsterNum (= monsterHealth 0))
			(= brigandHead 0)
		)
		(Bclr FLAG_351)
		(super newRoom: newRoomNumber &rest)
	)
)

(instance inWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setLoop: 0
					posn: -100 140
					setMotion: MoveTo 0 140 self
				)
			)
			(1 (SetMonsterChase client))
		)
	)
)

(instance inEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setLoop: 1
					posn: 400 140
					setMotion: MoveTo 319 140 self
				)
			)
			(1 (SetMonsterChase client))
		)
	)
)

(instance northDelay of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 6))
			(1
				(theSmallMonster
					posn:
						egoX
						(cond 
							(
							(> (curRoom horizon?) (- egoY monsterDistY)) (- (curRoom horizon?) 20))
							((== monsterNum vMantray) -10)
							(else (- egoY monsterDistY))
						)
					setCel: -1
					setLoop: -1
					setMotion: Chase ego [local36 (GetMonsterIndex monsterNum)] Encounter
				)
				(= local50 1)
				(client setScript: 0)
			)
		)
	)
)

(instance spellDelay of Script
	(properties)
	
	(method (doit)
		(if (and seconds monsterHurt (not local52))
			(= seconds 0)
			(= cycles 1)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local50 0)
				(theSmallMonster setCycle: 0 setMotion: 0 ignoreActors: 0)
				(if (== register CALM)
					(if (not (CastCalm self self))
						(self changeState: 4)
					)
				else
					(self changeState: 2)
				)
			)
			(1
				(= state 3)
				(cond 
					((== monsterNum vMantray)
						(HighPrint 210 34)
						;Unfortunately, the Mantray appears not to have been affected by your spell.
						(= cycles 1))
					(monsterHurt
						(HighPrint 210 35)
						;The monster doesn't seem very calm.  Maybe it didn't like you hurting it.
						(= cycles 1))
					(else (= seconds (+ 5 (/ [egoStats CALM] 10))))
				)
			)
			(2
				(= local52 1)
				(if (not (CastDazz self self))
					(self changeState: 4)
				)
			)
			(3
				(if (== monsterNum vMantray)
					(HighPrint 210 34)
					;Unfortunately, the Mantray appears not to have been affected by your spell.
					(= cycles 1)
				else
					(= seconds (+ 3 (/ [egoStats CALM] 10)))
				)
			)
			(4
				(if (not local10)
					(if
						(or
							(== monsterNum vGoblin)
							(== monsterNum vTroll)
							(== monsterNum vBrigand)
							(and
								(< -15 (- (ego x?) (theSmallMonster x?)))
								(< (- (ego x?) (theSmallMonster x?)) 15)
							)
						)
						(theSmallMonster setLoop: -1 setCycle: Walk)
					else
						(theSmallMonster setCycle: Forward)
					)
					(theSmallMonster
						ignoreActors:
						setMotion: Chase ego [local36 (GetMonsterIndex monsterNum)] Encounter
					)
					(= local50 1)
				)
				(= local52 0)
				(self dispose:)
			)
		)
	)
)

(instance searchIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= theSmallMonster register) ignoreActors:)
				(= SearchMonsterX
					(switch monsterNum
						(vSaurus 10)
						(vCheetaur 12)
						(vDragon 18)
						(vGoblin 23)
						(vMantray 12)
						(vBrigand 0)
						(else  20)
					)
				)
				(= SearchMonsterY
					(switch monsterNum
						(vSaurus 4)
						(vCheetaur -3)
						(vDragon 4)
						(vGoblin 1)
						(vMantray 4)
						(vBrigand 2)
						(else  20)
					)
				)
				(HandsOff)
				(if (> (ego y?) (theSmallMonster y?))
					(ego
						illegalBits: 0
						ignoreActors:
						setPri: (+ (theSmallMonster priority?) 1)
						setMotion:
							MoveTo
							(+ (theSmallMonster x?) SearchMonsterX)
							(+ (theSmallMonster y?) SearchMonsterY)
							self
					)
				else
					(ego
						illegalBits: 0
						ignoreActors:
						setPri: (- (theSmallMonster priority?) 1)
						setMotion:
							MoveTo
							(-
								(theSmallMonster x?)
								(cond 
									((== monsterNum vMantray) 19)
									((== monsterNum vBrigand) 36)
									(else 11)
								)
							)
							(- (theSmallMonster y?) 5)
							self
					)
				)
			)
			(1
				(ego
					view: vEgoThrowing
					setLoop: (if (> (ego y?) (theSmallMonster y?)) 1 else 0)
					setCycle: EndLoop self
				)
			)
			(2
				(HighPrint 210 41)
				;You search your opponent.
				(ego setCycle: BegLoop self)
			)
			(3
				(SearchMonster)
				(ego
					view: vEgo
					setLoop: -1
					setCycle: Walk
					setMotion:
						MoveTo
						185
						(if (< (ego x?) (theSmallMonster x?)) 140 else 160)
						self
				)
			)
			(4
				(ego setLoop: loopW)
				(theSmallMonster ignoreActors: 0)
				(NormalEgo)
				(ego illegalBits: (curRoom illBits?))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
