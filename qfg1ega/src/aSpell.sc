;;; Sierra Script 1.0 - (do not remove this comment)
(script# CLOSECOMBAT) ;215
(include system.sh) (include sci2.sh) (include game.sh)
(use Main)
(use LoadMany)
(use Invent)
(use Actor)
(use System)

(public
	closeCombat 0
	aSpell 1
)

(local
	spellCast
	haveWeapon
	local2
	haveShield
	local4
	local5 =  2
)
(instance aSpell of Prop
	(properties)
)

(instance closeCombat of Script
	(properties)
	
	(method (init)
		(LoadMany SCRIPT ARENA_THRUST ARENA_BLOCK ARENA_PARRY ARENA_DODGE ARENA_PAIN)
		(if (ego has: iShield)
			(Load VIEW vEgoFightArmSword)
		else
			(LoadMany SCRIPT ARENA_MAGIC ARENA_FLAME ARENA_ZAP ARENA_DAZZLE ARENA_CALM)
			(Load VIEW vEgoFightArmDagger)
			(aSpell view: vEgoFightArmDagger)
		)
		(= egoCanFight TRUE)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client drawWeapons:)
				(= haveShield ((= haveWeapon (ScriptID WARRIOR 0)) egoShield?))
				(= local2 (haveWeapon egoHand?))
				(= local4 (haveWeapon egosBack?))
				(directionHandler addToFront: client)
				(mouseDownHandler addToFront: client)
				(if (and (haveWeapon noWeapon?) (not haveShield))
					(local2 setLoop: 1 setCel: 2)
				)
				(= cycles 1)
			)
			(1
				(if (haveWeapon noWeapon?)
					(self changeState: 3)
				else
					(client
						canFight: 1
						action: 0
						cycleSpeed: 0
						moveSpeed: 0
						view: (haveWeapon weaponView?)
						setLoop: 2
						cel: 0
						stopUpd:
					)
				)
			)
			(2
				(HandsOn)
				(self changeState: 1)
			)
			(3
				(EgoDead 215 8
					#title {Bare Hands vs. Teeth and Claws, etc.}
					#icon vEgoDefeatedMagic 0 9
					;Caught in combat with neither sword nor dagger, you are unable to resist the monster's fierce onslaught.
					;Try to be better equipped in your next life.
				)
			)
		)
	)
	
	(method (handleEvent event &tmp theX theY temp2 temp3)
		(if
		(or (not (client canFight?)) (not egoCanFight))
			(event claimed: 1)
			(while ((= temp3 (Event new: 71)) type?)
				(temp3 dispose:)
			)
			(temp3 dispose:)
			(return 1)
		)
		(if script
			(if
				(and
					(== (event type?) direction)
					(!= (event message?) dirN)
				)
				(script cue:)
			else
				(return (event claimed: TRUE))
			)
		)
		(return
			(switch (event type?)
				(saidEvent
					(cls)
					(cond 
						((super handleEvent: event))
						((Said 'look')
							(HighPrint 215 0)
							;Quit sightseeing and watch what you're doing!
							)
						((Said 'escape,escape,run')
							(if
								(or
									(== prevRoomNum 14)
									(== prevRoomNum 171)
									(== prevRoomNum 88)
									(== prevRoomNum 89)
									(== prevRoomNum 93)
									(== prevRoomNum 73)
									(== prevRoomNum 91)
								)
								(HighPrint 215 1)
								;You cannot escape this encounter.
							else
								((ScriptID curRoomNum) escaped: 1)
							)
						)
						((Said 'cast>')
							(cond 
								((not (= spellCast (SaidSpell event)))
									(HighPrint 215 2)
									;That isn't a known spell.
									)
								(haveShield
									(HighPrint 215 3)
									;You cannot make the arcane gestures to cast spells while carrying your shield.
									)
								(
									(and
										(!= spellCast FLAMEDART)
										(!= spellCast ZAP)
										(!= spellCast DAZZLE)
										(!= spellCast CALM)
									)
									(HighPrint 215 4)
									;This might not be the best time for practicing non-combat spells.
								)
								((CastSpell spellCast)
									(client setEgoMP: [egoStats MANA])
									(HandsOff)
									(self setScript: (ScriptID 146 0) self spellCast)
								)
							)
						)
						(
							(or
								(Said 'fight,kill,beat,chop')
								(Said 'use/blade,dagger,weapon,shield')
							)
							(HighPrint 215 5)
							;Go ahead!
						)
						((Said 'throw')
							(HighPrint 215 6)
							;You're too close.
							)
						(else
							(HighPrint 215 7)
							;You don't have time for that.
							(event claimed: TRUE))
					)
				)
				(mouseDown
					(= theX (event x?))
					(= theY (event y?))
					(event claimed: TRUE)
				)
				(direction
					(HandsOff)
					(switch (event message?)
						(dirN
							(= local5 2)
							(self setScript: (ScriptID 151 0) self local5)
						)
						(dirW
							(= local5 0)
							(self setScript: (ScriptID 154 0) self local5)
						)
						(dirE
							(= local5 1)
							(self setScript: (ScriptID 154 0) self local5)
						)
						(dirS
							(= local5 2)
							(cond 
								(haveShield (self setScript: (ScriptID 152 0) self local5))
								((not (ego has: 6)) (self setScript: (ScriptID 153 0) self local5))
								(else
									(= local5 (Random 0 1))
									(self setScript: (ScriptID 154 0) self local5)
								)
							)
						)
					)
					(event claimed: TRUE)
				)
			)
		)
	)
)
