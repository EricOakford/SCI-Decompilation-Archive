;;; Sierra Script 1.0 - (do not remove this comment)
(script# CLOSECOMBAT) ;215
(include game.sh)
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
	handView
	haveShield
	backView
	attackAngle =  AttStraight
)
(instance aSpell of Prop)

(instance closeCombat of Script
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
				(= handView (haveWeapon egoHand?))
				(= backView (haveWeapon egosBack?))
				(directionHandler addToFront: client)
				(mouseDownHandler addToFront: client)
				(if (and (haveWeapon noWeapon?) (not haveShield))
					(handView setLoop: 1 setCel: 2)
				)
				(= cycles 1)
			)
			(1
				(if (haveWeapon noWeapon?)
					(self changeState: 3)
				else
					(client
						canFight: TRUE
						action: ActNone
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
	
	(method (handleEvent event &tmp mouseX mouseY temp2 evt)
		(if (or (not (client canFight?)) (not egoCanFight))
			(event claimed: TRUE)
			(while ((= evt (Event new: 71)) type?)
				(evt dispose:)
			)
			(evt dispose:)
			(return TRUE)
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
							(event claimed: TRUE)
						)
					)
				)
				(mouseDown
					(= mouseX (event x?))
					(= mouseY (event y?))
					(event claimed: TRUE)
				)
				(direction
					(HandsOff)
					(switch (event message?)
						(dirN
							(= attackAngle AttStraight)
							(self setScript: (ScriptID ARENA_THRUST 0) self attackAngle)
						)
						(dirW
							(= attackAngle AttLeft)
							(self setScript: (ScriptID ARENA_DODGE 0) self attackAngle)
						)
						(dirE
							(= attackAngle AttRight)
							(self setScript: (ScriptID ARENA_DODGE 0) self attackAngle)
						)
						(dirS
							(= attackAngle AttStraight)
							(cond 
								(haveShield
									(self setScript: (ScriptID ARENA_BLOCK 0) self attackAngle)
								)
								((not (ego has: iSword))
									(self setScript: (ScriptID ARENA_PARRY 0) self attackAngle)
								)
								(else
									(= attackAngle (Random 0 1))
									(self setScript: (ScriptID ARENA_DODGE 0) self attackAngle)
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
