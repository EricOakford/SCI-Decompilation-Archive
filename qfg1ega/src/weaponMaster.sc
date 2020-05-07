;;; Sierra Script 1.0 - (do not remove this comment)
(script# 221)
(include game.sh)
(use Main)
(use Skilled)
(use Motion)
(use System)

(public
	theMaster 0
)

(local
	facingWeaponMaster
	chatWithMaster
	askedFight
)
(procedure (DeclineTraining)
	(HighPrint 221 0)
	;"Pity.  I'm in the mood for some good combat practice."
)

(procedure (ParryCheck)
	(cond 
		((not [egoStats PARRY])
			(HighPrint 221 1)
			;"I am a skilled teacher as well as a skilled fighter."
			(HighPrint 221 2)
			;"However, the combat style I teach relies on the subtle alternation of Strike and Parry.
			;As I observe that you have no skill in the use of Parry, you would receive no benefit from my lessons."
			)
		((Btst OFFERED_TRAINING) (PrepareForBattle))
		(else
			(Bset OFFERED_TRAINING)
			(HighPrint 221 1)
			;"I am a skilled teacher as well as a skilled fighter."
			(HighPrint 221 3)
			;"For a mere gold coin, I will be glad to give you some practice and some pointers on how to better your skills."
			(HighPrint 221 4)
			;"Will you pay for a lesson?"
			(= yesNoTimer 100)
			(= askedFight TRUE)
		)
	)
)

(procedure (PrepareForBattle)
	(= askedFight TRUE)
	(if (Purchase 10)
		(HighPrint 221 5)
		;You pay the Weapon Master's price and then...
		(= masterDay Day)
		(SolvePuzzle POINTS_FIGHTWEAPONMASTER 3 FIGHTER)
		((ScriptID 39 0) setScript: (ScriptID 222 2))
	else
		(HighPrint 221 6)
		;That's easy to say, but you don't HAVE a gold coin.  You make your apologies to the Weapon Master.
		(DeclineTraining)
	)
)

(class WeaponMaster of Skilled
	(properties
		y 0
		x 0
		z 0
		heading 0
		yStep 2
		view NULL
		loop NULL
		cel NULL
		priority 0
		underBits 0
		signal $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		illegalBits cWHITE
		xLast 0
		yLast 0
		xStep 3
		moveSpeed 0
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		strength 65
		intell 60
		agil 75
		vit 50
		luck 60
		weap 80
		parry 80
		dodge 70
		magic 0
		stamina 0
		health 0
		mana 0
		armorValue 3
		armorEnc 0
		shieldValue 10
		weapValue 5
		canFight 0
		action 0
		opponent 0
		fightLeft 0
		warriorX 187
		warriorY 150
		endFight 0
	)
	
	(method (getHit)
		(if fightLeft
			(self x: (+ (self x?) 3))
			((self opponent?) x: (+ ((self opponent?) x?) 3))
		else
			(self x: (- (self x?) 3))
			((self opponent?) x: (- ((self opponent?) x?) 3))
		)
	)
	
	(method (gotBeat param1)
		(self endFight: TRUE)
		(self setScript: param1)
	)
)

(instance theMaster of WeaponMaster
	(properties
		view vWeaponMasterTalk
	)
	
	(method (doit)
		(cond 
			((> yesNoTimer 1) (-- yesNoTimer))
			((== yesNoTimer 1)
				(= yesNoTimer 0)
				(= askedFight FALSE)
				(DeclineTraining)
			)
		)
		(cond 
			(
				(and
					(< (ego distanceTo: (ScriptID 221 0)) 40)
					(not facingWeaponMaster)
					(not (Btst STOP_FIGHTING_WEAPONMASTER))
				)
				(= facingWeaponMaster TRUE)
			)
			(
				(and
					(not (< (ego distanceTo: (ScriptID 221 0)) 40))
					facingWeaponMaster
					(not (Btst STOP_FIGHTING_WEAPONMASTER))
				)
				(= facingWeaponMaster FALSE)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					(script (event claimed: FALSE))
					((super handleEvent: event))
					((> yesNoTimer 1)
						(cond 
							((Said 'affirmative,please')
								(= yesNoTimer 0)
								(if askedFight (PrepareForBattle) else (ParryCheck))
							)
							((Said 'pay')
								(= yesNoTimer 0)
								(if askedFight (PrepareForBattle) else (ParryCheck))
							)
							((Said 'n')
								(= yesNoTimer 0)
								(if askedFight (= askedFight FALSE))
								(DeclineTraining)
							)
							(else
								(event claimed: TRUE)
								(HighPrint 221 7)
								;"Do not ramble, my friend"
								(if askedFight
									(HighPrint 221 8)
									;"Will you pay?"
									(= yesNoTimer 80)
								else
									(HighPrint 221 9)
									;"Will you fight?"
									(= yesNoTimer 80)
								)
							)
						)
					)
					((Said 'pay')
						(= yesNoTimer 0)
						(= askedFight FALSE)
						(if [egoStats PARRY] (PrepareForBattle)
							else
							(HighPrint 221 10)
							;"I'm sorry.  Since you have no skill in parrying blows, you would receive no benefit from my instruction."
							)
					)
					((Said 'look>')
						(cond 
							((Said '/man,master[<weapon]')
								(HighPrint 221 11)
								;The man has the muscles of a trained athlete and is apparently quite skilled with the sword.
								)
							((Said '/blade')
								(HighPrint 221 12)
								;The weapon master carries a blunted practice sword, in the belief that students retain
								;more of their learning if they survive the practice sessions.
								)
						)
					)
					((Said 'chat/man,swordsman,master')
						(if (not facingWeaponMaster)
							(ego setScript: comeBackLittleEgo)
						else
							(HighPrint 221 13)
							;"Please don't bore me, young adventurer.  Ask me about something interesting to me."
						)
					)
					((Said 'chat,ask>')
						(if (not facingWeaponMaster)
							(ego setScript: comeBackLittleEgo)
							(event claimed: TRUE)
						else
							(= chatWithMaster TRUE)
						)
						(cond 
							(
							(Said '//fight,art,skill,strength,strength,agility')
								(HighPrint 221 14)
								;"Fighting is both an art and a skill."
								(HighPrint 221 15)
								;"It is a skill in the use of the muscle strength and agility's control."
								(HighPrint 221 16)
								;"It is an art in the out-witting and out-maneuvering of the opponent."
								(HighPrint 221 17)
								;"I am, of course, a most skilled artist."
								(if [egoStats PARRY]
									(HighPrint 221 18)
									;"Do you fight?"
									(= yesNoTimer 100)
								else
									(HighPrint 221 19)
									;"If you were a swordsman skilled in both attack and parry, we could have had a practice bout."
								)
							)
							((Said '//blade,weapon')
								(HighPrint 221 20)
								;"The sword is the finest of all weapons.  It requires an equal measure of skill and strength."
								)
							((Said '//name,man,master')
								(HighPrint 221 21)
								;"I am the Weapon Master."
								)
							((Said '//guard')
								(HighPrint 221 22)
								;"You will find them very skilled at fighting.  After all, I am the one who trains all of the guards at this castle."
								)
							((Said '//baron')
								(HighPrint 221 23)
								;"The Baron had the wisdom to recognize greatness when I presented myself.  Thus he made me Weapon Master."
								)
							((Said '//barnard')
								(HighPrint 221 24)
								;"The Baronet had some minor talent with weapons, but he was too impatient.
								;If he had but listened and learned what I teach, he would never have been lost."
								)
							((Said '//shield,armor')
								(HighPrint 221 25)
								;"I never use a shield or armor under most circumstances.
								;It only gets in the way of my agility and dodging ability.
								;It is best used by those who are strong, but not swift or agile."
								)
							((Said '//teach,learn,lesson,practice')
								(HighPrint 221 26)
								;"Practice is the way to refine and improve one's skills."
								(ParryCheck))
							(else
								(= chatWithMaster 0)
								(event claimed: TRUE)
								(if (not (ego script?))
									(HighPrint 221 13)
									;"Please don't bore me, young adventurer.  Ask me about something interesting to me."
									)
							)
						)
						(if (and chatWithMaster (event claimed?)) (SolvePuzzle POINTS_TALKTOWEAPONMASTER 1))
					)
					(
						(or
							(Said 'practice,fight,learn')
							(Said 'buy,get/lesson')
						)
						(ParryCheck)
					)
				)
			)
		)
	)
)

(instance comeBackLittleEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(HighPrint 221 27)
				;"If you are addressing me, sir, you must respect me enough to address me to my face!"
				(if (< (ego x?) ((ScriptID 221 0) x?))
					(theMaster loop: 5)
				else
					(theMaster loop: 4)
				)
				(ego
					ignoreActors:
					setMotion:
						MoveTo
						(if (< (ego x?) ((ScriptID 221 0) x?))
							(- (theMaster x?) 35)
						else
							(+ (theMaster x?) 35)
						)
						(theMaster y?)
						self
				)
			)
			(1
				(Face ego theMaster)
				(= facingWeaponMaster TRUE)
				(ego ignoreActors: 0)
				(client setScript: 0)
				(HandsOn)
			)
		)
	)
)
