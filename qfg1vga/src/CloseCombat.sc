;;; Sierra Script 1.0 - (do not remove this comment)
(script# CLOSECOMBAT)
(include game.sh) (include "215.shm")
(use Main)
(use Procs)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	closeCombat 0
	aSpell 1
	pointBox 2
	blood 3
	zapCrap 4
)

(local
	local0
	theWarrior
	[local2 3]
	attackAngle =  2
	theSpell
	[local7 2]
	hitOpponent
	magicIcons
	[local11 3]
	local14
	local15
	egoDefends
	local17
	haveSword
	local19
)
(instance aSpell of Actor)

(instance monHurt of Sound
	(properties
		flags -1
		number 102
		priority 1
	)
)

(instance closeCombat of Script
	(method (init)
		(monHurt init:)
		(Bset fBattleStarted)
		(Bclr fNextMonster)
		(user canInput: FALSE)
		(pointBox init: setLoop: 2 stopUpd:)
		(= magicIcons FALSE)
		(if (ego has: iSword)
			(Load VIEW 109)
			(= haveSword TRUE)
			(= magicIcons FALSE)
			(blood view: 535)
		else
			(= haveSword FALSE)
			(dodgeLIcon init: setPri: 15 hide: stopUpd:)
			(dodgeRIcon init: setPri: 15 hide: stopUpd:)
			(if (!= heroType THIEF)
				(switchLoop doit:)
			)
			(Load VIEW 102)
			(blood view: 535)
			(aSpell view: 535)
		)
		(= egoCanFight TRUE)
		(super init: &rest)
	)
	
	(method (doit)
		(pointBox doit:)
		(super doit: &rest)
	)
	
	(method (dispose &tmp temp0)
		(monHurt dispose:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client drawWeapons:)
				(= theWarrior (ScriptID WARRIOR 0))
				(directionHandler addToFront: client)
				(mouseDownHandler addToFront: client)
				(= ticks 1)
			)
			(1
				(cond 
					((theWarrior noWeapon?)
						(= state 2)
						(client
							view: 117
							setLoop: 0
							setCel: 0
							canFight: 0
							cycleSpeed: 12
							setCycle: EndLoop self
						)
					)
					;This code kills ego when stamina runs out.
					; It can be commented out for consistency with the other games.
					((<= (theWarrior stamina?) 0)
						(client canFight: FALSE action: ActDie)
						(EgoDead C_DIE_EXHAUSTION C_DIE_EXHAUSTION_TITLE)
					)
					(((theWarrior opponent?) ateEgo?))
					(else
						(if haveSword
							(client view: 109)
						else
							(client view: 102)
						)
						(client
							canFight: TRUE
							action: 0
							cycleSpeed: 8
							setLoop: 0
							setCel: 0
							setPri: -1
							stopUpd:
						)
					)
				)
			)
			(2
				(HandsOn)
				(user canInput: FALSE)
				(theGame setCursor: normalCursor TRUE)
				(self changeState: 1)
			)
			(3
				(EgoDead C_DIE_NO_WEAPON C_DIE_NO_WEAPON_TITLE)
			)
		)
	)
	
	(method (handleEvent event &tmp eX eY temp2 evt)
		(if (== (event type?) mouseUp)
			(event claimed: TRUE)
			(return TRUE)
		)
		(if
			(or
				(not (client canFight?))
				(not egoCanFight)
				(Btst fNextMonster)
			)
			(event claimed: TRUE)
			(while ((= evt (Event new: 71)) type?)
				(evt dispose:)
			)
			(evt dispose:)
			(return TRUE)
		)
		(if script
			(if
				(or
					(and
						(<= dirN (event message?))
						(<= (event message?) dirNW)
						(!= (event message?) dirN)
					)
					(== (event type?) mouseDown)
				)
				(script cue:)
			else
				(return (event claimed: TRUE))
			)
		)
		(return
			(cond 
				((== (event type?) mouseDown)
					(= eX (event x?))
					(cond 
						((< (= eY (event y?)) 144)
							(cond 
								((> eX 285)
									(= attackAngle AttStraight)
									(cond 
										((not magicIcons)
											(self setScript: egoThrust self attackAngle)
										)
										((ego has: iSword)
											(messager say: N_ROOM NULL C_CANTWITHSHIELD 0 0 215)
										)
										((ego knows: FLAMEDART)
											(if (CastSpell FLAMEDART)
												(client setEgoMP: [egoStats MANA])
												(HandsOff)
												(self setScript: egoFlame self)
											)
										)
										(else
											(messager say: N_ROOM NULL C_DONTKNOWSPELL 0 0 215)
										)
									)
								)
								((< eX 265)
									(= attackAngle AttRight)
									(cond 
										((not magicIcons)
											(self setScript: egoThrust self attackAngle)
										)
										((ego has: iSword)
											(messager say: N_ROOM NULL C_CANTWITHSHIELD 0 0 215)
										)
										((ego knows: ZAP)
											(if (CastSpell ZAP)
												(client setEgoMP: [egoStats MANA])
												(HandsOff)
												(self setScript: egoZap self)
											)
										)
										(else
											(messager say: N_ROOM NULL C_DONTKNOWSPELL 0 0 215)
										)
									)
								)
							)
						)
						((> eY 159)
							(cond 
								((> eX 285)
									(= attackAngle AttLeft)
									(cond 
										((not magicIcons)
											(self setScript: egoDodge self attackAngle)
										)
										((ego has: iSword)
											(messager say: N_ROOM NULL C_CANTWITHSHIELD 0 0 215)
										)
										((ego knows: DAZZLE)
											(if (CastSpell DAZZLE)
												(client setEgoMP: [egoStats MANA])
												(HandsOff)
												(self setScript: egoDazzle self)
											)
										)
										(else
											(messager say: N_ROOM NULL C_DONTKNOWSPELL 0 0 215)
										)
									)
								)
								((< eX 265)
									(= attackAngle AttStraight)
									(cond 
										((== (pointBox loop?) 2)
											(self setScript: egoBlock self attackAngle)
										)
										(
											(or
												(== prevRoomNum 14)
												(== prevRoomNum 171)
												(== prevRoomNum 88)
												(== prevRoomNum 89)
												(== prevRoomNum 93)
												(== prevRoomNum 73)
												(== prevRoomNum 91)
											)
											(messager say: N_ROOM NULL C_CANTESCAPE 0 0 215)
										)
										(else
											((ScriptID curRoomNum) escaped: TRUE)
										)
									)
								)
							)
						)
						((and (> 285 eX) (> eX 265))
							(if (ego has: iSword)
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
									(messager say: N_ROOM NULL C_CANTESCAPE 0 0 215)
								else
									((ScriptID curRoomNum) escaped: TRUE)
								)
							else
								(switchLoop doit:)
							)
						)
					)
					(event claimed: TRUE)
				)
				(
					(and
						(<= dirS (event message?))
						(<= (event message?) dirNW)
					)
					(HandsOff)
					(switch (event message?)
						(dirNE
							(theGame setCursor: normalCursor TRUE 293 135)
							(= attackAngle AttStraight)
							(cond 
								((not magicIcons)
									(self setScript: egoThrust self attackAngle)
								)
								((ego has: iSword)
									(messager say: N_ROOM NULL C_CANTWITHSHIELD 0 0 215)
								)
								((ego knows: FLAMEDART)
									(if (CastSpell FLAMEDART)
										(client setEgoMP: [egoStats MANA])
										(HandsOff)
										(self setScript: egoFlame self)
									)
								)
								(else
									(messager say: N_ROOM NULL C_DONTKNOWSPELL 0 0 215)
								)
							)
						)
						(dirSE
							(theGame setCursor: normalCursor TRUE 293 170)
							(= attackAngle AttLeft)
							(cond 
								((not magicIcons)
									(self setScript: egoDodge self attackAngle)
								)
								((ego has: iSword)
									(messager say: N_ROOM NULL C_CANTWITHSHIELD 0 0 215)
								)
								((ego knows: DAZZLE)
									(if (CastSpell DAZZLE)
										(client setEgoMP: [egoStats MANA])
										(HandsOff)
										(self setScript: egoDazzle self)
									)
								)
								(else
									(messager say: N_ROOM NULL C_DONTKNOWSPELL 0 0 215)
								)
							)
						)
						(dirNW
							(theGame setCursor: normalCursor TRUE 255 135)
							(= attackAngle AttRight)
							(cond 
								((not magicIcons)
									(self setScript: egoThrust self attackAngle)
								)
								((ego has: iSword)
									(messager say: N_ROOM NULL C_CANTWITHSHIELD 0 0 215)
								)
								((ego knows: ZAP)
									(if (CastSpell ZAP)
										(client setEgoMP: [egoStats MANA])
										(HandsOff)
										(self setScript: egoZap self)
									)
								)
								(else
									(messager say: N_ROOM NULL C_DONTKNOWSPELL 0 0 215)
								)
							)
						)
						(dirSW
							(theGame setCursor: normalCursor TRUE 255 170)
							(= attackAngle AttStraight)
							(cond 
								((not magicIcons)
									(self setScript: egoBlock self attackAngle)
								)
								(
									(or
										(== prevRoomNum 14)
										(== prevRoomNum 171)
										(== prevRoomNum 88)
										(== prevRoomNum 89)
										(== prevRoomNum 93)
										(== prevRoomNum 73)
										(== prevRoomNum 91)
									)
									(messager say: N_ROOM NULL C_CANTESCAPE 0 0 215)
								)
								(else
									((ScriptID curRoomNum) escaped: TRUE)
								)
							)
						)
						(dirS
							(if (== (event type?) (| keyDown direction))
								(theGame setCursor: normalCursor TRUE 275 152)
								(if (ego has: iSword)
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
										(messager say: N_ROOM NULL C_CANTESCAPE 0 0 215)
									else
										((ScriptID curRoomNum) escaped: TRUE)
									)
								else
									(switchLoop doit:)
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

(instance egoFlame of Script
	(method (init)
		(= theWarrior (ScriptID 213 0))
		((= theSpell (ScriptID 215 1))
			init:
			posn: 500 500
			stopUpd:
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(HandsOn)
		(if theSpell
			(theSpell dispose:)
		)
		(user canInput: FALSE)
		(theGame setCursor: normalCursor TRUE)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theWarrior
					canFight: FALSE
					action: 11
					view: 107
					setCel: 0
					setPri: 15
				)
				(theWarrior setCycle: CycleTo 4 1 self)
				((ScriptID monsterNum 0) inTransit: TRUE)
			)
			(1
				(theSpell
					view: 535
					setLoop: 6
					setCel: 1
					cycleSpeed: 4
					moveSpeed: 0
					xStep: 50
					yStep: 30
					ignoreActors:
					setPri: (+ ((theWarrior opponent?) priority?) 1)
					posn: (+ (theWarrior x?) 43) (- (theWarrior y?) 56)
				)
				(theWarrior setCycle: EndLoop self)
			)
			(2 (= ticks 1))
			(3
				(theWarrior setLoop: 0 setCel: 0 setPri: -1)
				(theSpell setPri: (+ ((theWarrior opponent?) priority?) 1))
				(cond 
					(
						(or
							(== monsterNum vMinotaur)
							(== monsterNum vSaurus)
							(== monsterNum vCheetaur)
							(== monsterNum vTroll)
							(== monsterNum vDragon)
						)
						(= ticks 1)
					)
					((!= monsterNum vMantray)
						(theSpell
							setMotion: MoveTo
								((theWarrior opponent?) flameX?)
								((theWarrior opponent?) flameY?)
								self
						)
					)
					(else
						(theSpell
							setMotion: MoveTo
								(- ((theWarrior opponent?) x?) 37)
								(+ ((theWarrior opponent?) y?) 30)
								self
						)
					)
				)
			)
			(4
				(theSpell
					setPri: (+ ((theWarrior opponent?) priority?) 1)
					setCycle: EndLoop self
				)
			)
			(5
				(theSpell priority: (+ ((theWarrior opponent?) priority?) 1))
				((theWarrior opponent?) setCycle: 0 setScript: 0)
				(if (== monsterNum vOgre)
					((theWarrior opponent?)
						x: 123
						y: 25
						loop: 3
						setCel: 0
						stopUpd:
					)
					((ScriptID vOgre 3) hide: forceUpd:)
				)
				(if (== monsterNum vTroll)
					((ScriptID vTroll 3) cel: 0 x: 182 y: 91 show: forceUpd:)
					((theWarrior opponent?)
						x: 182
						y: 91
						setLoop: 1
						setCel: 0
						stopUpd:
					)
				)
				(= ticks 1)
			)
			(6
				(theSpell dispose:)
				((ScriptID vMinotaur 3) x: 500 y: 500 setCycle: 0 forceUpd:)
				(Bset fMonsterRecoils)
				(if (== monsterNum vOgre)
					((ScriptID vOgre 3)
						loop: 2
						cel: 0
						x: 166
						y: 88
						show:
						forceUpd:
					)
					((theWarrior opponent?)
						loop: 1
						x: 165
						y: 80
						setCel: 0
						stopUpd:
					)
				)
				(if (or (== monsterNum vGoblin) (== monsterNum vBrigand))
					((theWarrior opponent?) cycleSpeed: 6)
				else
					((theWarrior opponent?) cycleSpeed: 23)
				)
				(monHurt play:)
				((theWarrior opponent?)
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(7
				((theWarrior opponent?) getHurt: (+ 5 (/ [egoStats FLAMEDART] 3)))
				((theWarrior opponent?)
					setMonsterHP: ((theWarrior opponent?) health?)
				)
				(Animate (cast elements?) FALSE)
				(if (<= ((theWarrior opponent?) health?) 0)
					((theWarrior opponent?) die:)
				)
				(if
					(and
						(or (== monsterNum vGoblin) (== monsterNum vBrigand))
						(<= ((theWarrior opponent?) health?) 0)
					)
					((theWarrior opponent?) setScript: (ScriptID monsterNum 3))
				else
					((ScriptID monsterNum 0) inTransit: FALSE)
					((theWarrior opponent?) setScript: (ScriptID monsterNum 2))
				)
				(theWarrior canFight: TRUE view: 102 setCel: 0 setLoop: 0)
				(self dispose:)
			)
		)
	)
)

(instance egoZap of Script
	(method (init)
		(= theWarrior (ScriptID 213 0))
		(= theSpell (ScriptID 215 1))
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= zapPower (+ 5 (/ [egoStats ZAP] 10)))
				(theWarrior
					canFight: FALSE
					action: ActCast
					view: 108
					loop: 0
					cel: 0
					forceUpd:
					setPri: 15
				)
				(theWarrior setCycle: EndLoop self)
			)
			(1
				(= ticks 1)
			)
			(2
				(theWarrior canFight: TRUE view: 102 cel: 0 forceUpd: setPri: -1)
				(self dispose:)
			)
		)
	)
)

(instance egoDazzle of Script
	(method (init)
		(= theWarrior (ScriptID 213 0))
		(= theSpell (ScriptID 215 1))
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theWarrior
					canFight: FALSE
					action: ActCast
					view: 107
					setCel: 0
					setPri: 15
				)
				(theWarrior setCycle: EndLoop self)
			)
			(1
				(= ticks 15)
			)
			(2
				(theWarrior setLoop: 0 setCel: 0 setPri: -1)
				(theSpell
					view: 535
					setLoop: 5
					cel: 0
					setPri: 15
					cycleSpeed: 6
					ignoreActors:
					posn: (+ (theWarrior x?) 35) (- (theWarrior y?) 60)
					init:
					setCycle: EndLoop self
				)
			)
			(3
				(theSpell dispose:)
				(Bset fMonsterDazzled)
				(= monsterDazzle [egoStats DAZZLE])
				(theWarrior canFight: TRUE action: ActNone)
				(self dispose:)
			)
		)
	)
)

(instance egoThrust of Script
	(method (init)
		(= theWarrior (ScriptID 213 0))
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theWarrior
					getTired: 4
					setCel: 0
					canFight: FALSE
					action: ActThrust
				)
				(if
					(and
						haveSword
						(or (== monsterNum vTroll) (== monsterNum vMantray))
						(== attackAngle AttStraight)
					)
					(theWarrior view: 114)
				else
					(if (ego has: iSword)
						(theWarrior view: (if (== attackAngle AttStraight) 110 else 109))
					else
						(theWarrior view: (if (== attackAngle AttStraight) 103 else 102))
					)
					(if (!= attackAngle AttStraight) (theWarrior setCel: 1))
				)
				(= register 0)
				((ScriptID monsterNum 0) inTransit: TRUE)
				(= egoCanFight FALSE)
				(TrySkill WEAPON 0 0)
				(= hitOpponent (theWarrior tryAttack: (theWarrior opponent?)))
				(if
					(and
						(or
							(== monsterNum vMinotaur)
							(== monsterNum vBrigand)
							(== monsterNum vMantray)
							(== monsterNum vGoblin)
							(== monsterNum vTroll)
							(== monsterNum vCheetaur)
							(== monsterNum vOgre)
							(== monsterNum vBear)
						)
						(== ((theWarrior opponent?) action?) ActParryUp)
					)
					(= hitOpponent FALSE)
				)
				(if hitOpponent
					(blood init: posn: 500 500 stopUpd:)
					(zapCrap init: posn: 500 500 stopUpd:)
					((theWarrior opponent?) setCycle: 0 setScript: 0 stopUpd:)
					(theWarrior doDamage: (theWarrior opponent?) zapPower)
					(switch monsterNum
						(vCheetaur
							((theWarrior opponent?)
								view: 442
								setLoop: 1
								setCel: 0
								stopUpd:
							)
						)
						(vSaurus
							(if (Random 0 1)
								((theWarrior opponent?) setLoop: 0 setCel: 1 stopUpd:)
							else
								((theWarrior opponent?) setLoop: 0 setCel: 7 stopUpd:)
							)
						)
						(vGoblin
							((theWarrior opponent?) setLoop: 1 setCel: 0 stopUpd:)
						)
						(vTroll
							((ScriptID vTroll 3) x: 182 y: 91 show: setCel: 0 stopUpd:)
							((theWarrior opponent?)
								x: 182
								y: 91
								setLoop: 1
								setCel: 0
								stopUpd:
							)
						)
						(vDragon
							(if (Random 0 1)
								((theWarrior opponent?) setLoop: 0 setCel: 2 stopUpd:)
							else
								((theWarrior opponent?) setLoop: 0 setCel: 7 stopUpd:)
							)
						)
						(vBear
							(cond 
								((== attackAngle AttRight)
									(if haveSword
										((theWarrior opponent?) setLoop: 0 setCel: 2 stopUpd:)
									else
										((theWarrior opponent?) setLoop: 0 setCel: 3 stopUpd:)
									)
								)
								((not haveSword)
									((theWarrior opponent?) setLoop: 0 setCel: 2 stopUpd:)
								)
							)
						)
					)
				else
					(if (and (!= monsterNum vMantray) (Btst fBattleStarted))
						(= local17 1)
						((theWarrior opponent?) setCycle: 0 setScript: 0 stopUpd:)
					)
					(if local17
						(switch monsterNum
							(vMinotaur
								((ScriptID vMinotaur 3) x: 500 y: 500 setCycle: 0 forceUpd:)
								((theWarrior opponent?) setLoop: 0 setCel: 4 stopUpd:)
							)
							(vCheetaur
								((theWarrior opponent?)
									view: 444
									setLoop: 0
									setCel: 1
									stopUpd:
								)
							)
							(vSaurus
								(if local17
									((theWarrior opponent?) setLoop: 0 setCel: 0 stopUpd:)
								)
							)
							(vGoblin
								((theWarrior opponent?) setLoop: 2 setCel: 4 stopUpd:)
							)
							(vBrigand
								((theWarrior opponent?) setLoop: 3 setCel: 2 stopUpd:)
							)
							(vBear
								(if local17
									(cond 
										((and (== attackAngle AttStraight) haveSword)
											((theWarrior opponent?) setLoop: 0 setCel: 2 stopUpd:)
										)
										((Random 0 1)
											((theWarrior opponent?) setLoop: 0 setCel: 0 stopUpd:)
										)
										(else
											((theWarrior opponent?) setLoop: 0 setCel: 1 stopUpd:)
										)
									)
								)
							)
						)
					)
					(Bset fThrustHit)
				)
				((ScriptID vMinotaur 3) x: 500 y: 500 setCycle: 0 forceUpd:)
				(if
					(and
						(== monsterNum vOgre)
						(== ((theWarrior opponent?) loop?) 0)
						(== attackAngle AttStraight)
						haveSword
					)
					(= register 1)
					(theWarrior setPri: 1 setCycle: CycleTo 1 1 self)
					((theWarrior opponent?) setCycle: 0 setScript: 0 setCel: 0)
				else
					(if hitOpponent
						(theWarrior setPri: 15)
					else
						(if (== monsterNum vTroll)
							(theWarrior setPri: 1)
						)
						(if (== monsterNum vCheetaur)
							(theWarrior setPri: 15)
						)
					)
					(if (and (== attackAngle AttStraight) (ego has: iSword))
						(theWarrior setCycle: CycleTo 2 1 self)
					else
						(theWarrior setCycle: CycleTo 3 1 self)
					)
				)
			)
			(1
				(cond 
					(register
						(= ticks 1)
					)
					(hitOpponent
						(cond 
							((== monsterNum vOgre)
								(= ticks 45)
							)
							((== monsterNum vBear)
								(= ticks 25)
							)
							(else
								(= ticks 1)
							)
						)
						(blood setPri: 15 ignoreActors: setCel: 0)
						(zapCrap setPri: 15 ignoreActors: setCel: 0)
						(monHurt play:)
						(if (== attackAngle AttStraight)
							(if haveSword
								(if (or (== monsterNum vTroll) (== monsterNum vMantray))
									(blood x: (+ (theWarrior x?) 53) y: (- (theWarrior y?) 81))
								else
									(blood x: (+ (theWarrior x?) 64) y: (- (theWarrior y?) 64))
								)
							else
								(blood x: (+ (theWarrior x?) 43) y: (- (theWarrior y?) 70))
								(if zapPower
									(zapCrap
										x: (+ (theWarrior x?) 43)
										y: (- (theWarrior y?) 70)
										setCycle: EndLoop
									)
								)
							)
							(blood setLoop: 2 setCycle: EndLoop)
						else
							(if haveSword
								(blood x: (+ (theWarrior x?) 42) y: (- (theWarrior y?) 65))
							else
								(blood x: (+ (theWarrior x?) 32) y: (- (theWarrior y?) 56))
								(if zapPower
									(zapCrap
										x: (+ (theWarrior x?) 32)
										y: (- (theWarrior y?) 56)
										setCycle: EndLoop
									)
								)
							)
							(blood setLoop: 1 setCycle: EndLoop)
						)
						(= zapPower 0)
					)
					(else
						(= ticks 8)
					)
				)
			)
			(2
				(theWarrior setCycle: EndLoop self)
				(if hitOpponent
					(Bset fMonsterRecoils)
					(if (== monsterNum vOgre)
						((ScriptID vOgre 3)
							loop: 2
							cel: 0
							x: 166
							y: 88
							show:
							forceUpd:
						)
						((theWarrior opponent?)
							loop: 1
							cel: 0
							x: 165
							y: 80
							forceUpd:
						)
					)
					(if (or (== monsterNum vGoblin) (== monsterNum vBrigand))
						((theWarrior opponent?) cycleSpeed: 6)
					else
						((theWarrior opponent?) cycleSpeed: 23)
					)
					((theWarrior opponent?) loop: 1 cel: 0 setCycle: EndLoop self)
				else
					(if local17
						(switch monsterNum
							(vMinotaur
								((theWarrior opponent?) setLoop: 0 setCel: 3 stopUpd:)
							)
							(vCheetaur
								((theWarrior opponent?)
									view: 444
									setLoop: 0
									setCel: 2
									stopUpd:
								)
							)
							(vSaurus
								(if local17
									((theWarrior opponent?) setLoop: 0 setCel: 1 stopUpd:)
								)
							)
							(vGoblin
								((theWarrior opponent?) setLoop: 2 setCel: 5 stopUpd:)
							)
							(vBrigand
								((theWarrior opponent?) setLoop: 3 setCel: 3 stopUpd:)
							)
							(vDragon
								(if local17
									((theWarrior opponent?) setLoop: 0 setCel: 0 stopUpd:)
								)
							)
						)
					)
					(self cue:)
				)
			)
			(3
				(if (not (theWarrior cycler?))
					(if haveSword
						(theWarrior view: 109 setCel: 0 stopUpd:)
					else
						(theWarrior view: 102 setCel: 0 stopUpd:)
					)
				)
			)
			(4
				(if haveSword
					(theWarrior view: 109 setCel: 0 stopUpd:)
				else
					(theWarrior view: 102 setCel: 0 stopUpd:)
				)
				(= ticks 15)
			)
			(5
				(zapCrap dispose:)
				(blood dispose:)
				(if hitOpponent
					((theWarrior opponent?)
						setMonsterHP: ((theWarrior opponent?) health?)
					)
					(if (<= ((theWarrior opponent?) health?) 0)
						((theWarrior opponent?) die:)
					)
				)
				(if haveSword
					(theWarrior view: 109)
				)
				(cond 
					((or (== monsterNum vTroll) (== monsterNum vOgre))
						(if (or local17 hitOpponent)
							((theWarrior opponent?) setScript: (ScriptID monsterNum 2))
							(= local17 0)
						)
						((ScriptID monsterNum 0) inTransit: 0)
					)
					(
						(and
							(or (== monsterNum vGoblin) (== monsterNum vBrigand))
							(<= ((theWarrior opponent?) health?) 0)
						)
						((theWarrior opponent?) setScript: (ScriptID monsterNum 3))
					)
					((and (== monsterNum vMantray) (not hitOpponent))
						((ScriptID monsterNum 0) inTransit: FALSE)
					)
					(else
						(if (not ((theWarrior opponent?) script?))
							(= local17 0)
							((theWarrior opponent?) setScript: (ScriptID monsterNum 2))
						)
						((ScriptID monsterNum 0) inTransit: FALSE)
					)
				)
				(= hitOpponent FALSE)
				(Bclr fMonsterDazzled)
				(Bclr fThrustHit)
				(theWarrior canFight: TRUE)
				(= egoCanFight TRUE)
				(self dispose:)
			)
		)
	)
)

(instance egoBlock of Script
	(method (init)
		(= egoDefends TRUE)
		(= theWarrior (ScriptID 213 0))
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoCanFight FALSE)
				(if haveSword
					(theWarrior view: 111)
				else
					(theWarrior view: 104)
				)
				(theWarrior
					getTired: 1
					canFight: FALSE
					action: ActParryUp
					setCel: 0
				)
				(TrySkill PARRY 0 20)
				(if ((theWarrior opponent?) ateEgo?)
					(self dispose:)
				)
				(if
					(and
						(or (== monsterNum vOgre) (== monsterNum vTroll))
						haveSword
					)
					(theWarrior view: 115 loop: 0 forceUpd: setCycle: CycleTo 2 1)
					(= ticks 80)
				else
					(= ticks 1)
				)
			)
			(1
				(theWarrior setCycle: EndLoop self)
			)
			(2
				(= ticks 12)
			)
			(3
				(if
					(and
						(or (== monsterNum vOgre) (== monsterNum vTroll))
						haveSword
					)
					(theWarrior view: 109 forceUpd:)
				)
				(theWarrior setCel: 0 stopUpd:)
				(= egoCanFight 1)
				(self dispose:)
			)
		)
	)
)

(instance egoParry of Script
	(method (init)
		(= egoDefends TRUE)
		(= theWarrior (ScriptID 213 0))
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoCanFight FALSE)
				(TrySkill PARRY 0 20)
				(theWarrior
					getTired: 1
					canFight: FALSE
					action: ActParryUp
					setLoop: 4
					setCel: 0
					stopUpd:
				)
				(= cycles 1)
			)
			(1
				(theWarrior setCel: 1)
				(= cycles 7)
			)
			(2
				(theWarrior setCel: 0)
				(= cycles 1)
			)
			(3
				(theWarrior cel: 0 setLoop: 2 stopUpd:)
				(= egoCanFight 1)
				(self dispose:)
			)
		)
	)
)

(instance egoDodge of Script
	(method (init)
		(= egoDefends TRUE)
		(= theWarrior (ScriptID 213 0))
		(super init: &rest)
	)
	
	(method (doit)
		(if ((theWarrior opponent?) ateEgo?)
			(theWarrior setCycle: 0 setCycle: CycleTo 0 -1)
			(self dispose:)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoCanFight FALSE)
				(TrySkill DODGE 0 20)
				(cond 
					(
						(and
							haveSword
							(or (== monsterNum vTroll) (== monsterNum vOgre))
						)
						(theWarrior view: 116 setLoop: 0)
					)
					(haveSword (theWarrior view: 112 setLoop: 0))
					(else (theWarrior view: 105))
				)
				(if haveSword
					(theWarrior setCel: 1)
				)
				(theWarrior
					getTired: 2
					canFight: FALSE
					action: (if (== register 0) ActDodgeLeft else ActDodgeRight)
					setCel: 0
				)
				(theWarrior setCycle: EndLoop self)
			)
			(1
				(= ticks 12)
			)
			(2
				(if (== (theWarrior view?) 110)
					(theWarrior view: 109)
				)
				(theWarrior setCel: 0 stopUpd:)
				(= egoCanFight TRUE)
				(self dispose:)
			)
		)
	)
)

(instance painReaction of Script
	(method (init)
		(= theWarrior (ScriptID 213 0))
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoCanFight FALSE)
				(= ticks 1)
			)
			(1
				(if (Btst fDiedInBattle)
					(= ticks 1)
				)
				(= egoCanFight TRUE)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance dodgeLIcon of View
	(properties
		x 246
		y 175
		view 945
		cel 4
	)
)

(instance dodgeRIcon of View
	(properties
		x 282
		y 175
		view 945
		cel 5
	)
)

(class inputBox215 of View
	(properties
		name "inputBox"
		oldX 0
		oldY 0
		relX 280
		relY 155
		first TRUE
		leftBor 260
		rightBor 303
		topBor 130
		botBor 175
		value 1000
	)
	
	(method (init)
		(theIconBar disable:)
		(theGame setCursor: normalCursor TRUE 280 155)
		(self setPri: 14 ignoreActors:)
		(super init: &rest)
	)
	
	(method (doit &tmp event theRelX theRelY evtX evtY temp5 temp6 temp7)
		(= evtX ((= event (Event new:)) x?))
		(= evtY (- (event y?) 10))
		(if first
			(= oldX evtX)
			(= oldY evtY)
			(= first 0)
		)
		(if (or (!= evtX oldX) (!= evtY oldY))
			(if (!= evtX oldX)
				(= temp5 (- evtX oldX))
				(= oldX evtX)
				(cond 
					((< (= theRelX (+ relX temp5)) leftBor) (= theRelX (= oldX leftBor)))
					((> theRelX rightBor) (= theRelX (= oldX rightBor)))
				)
			else
				(= theRelX relX)
			)
			(if (!= evtY oldY)
				(= temp6 (- evtY relY))
				(= oldY evtY)
				(cond 
					((< (= theRelY (+ relY temp6)) topBor) (= theRelY (= oldY topBor)))
					((> theRelY botBor) (= theRelY (= oldY botBor)))
				)
			else
				(= theRelY relY)
			)
			(= relY theRelY)
			(theGame
				setCursor: normalCursor TRUE (= relX theRelX) theRelY
			)
		)
		(event dispose:)
		(super doit: &rest)
	)
	
	(method (dispose)
		(theGame setCursor: normalCursor TRUE)
		(super dispose:)
	)
	
	;This didn't decompile properly, and has been commented out.
	;My guess is that this is meant to show button presses (there are
	;graphics for that in view.945)
;;;	(method (draw &tmp temp0 temp1)
;;;		(if (< value 1)
;;;			(= value 1)
;;;		)
;;;		(if
;;;			(>
;;;				(= temp1 --UNKNOWN-PROP-NAME--)	;which property is this?
;;;				value
;;;			)
;;;			(= temp1 value)
;;;		)
;;;		(if
;;;			(>
;;;				(= temp0
;;;					(/
;;;						(+
;;;							(= temp1
;;;								(/ (+ (* temp1 100) value -1) value)
;;;							)
;;;							9
;;;						)
;;;						10
;;;					)
;;;				)
;;;				9
;;;			)
;;;			(= temp0 9)
;;;		)
;;;		(self cel: temp0 setPri: 14 stopUpd:)
;;;	)
)

(instance pointBox of inputBox215
	(properties
		x 303
		y 175
		view 945
		loop 2
		value 1000
	)
)

(instance findPenalty of Code
	(method (doit &tmp thisTime temp1)
		(= thisTime (GetTime))
		(= local15
			(cond 
				((< (= temp1 (Abs (- thisTime local14))) 180) 50)
				((< temp1 240) 40)
				((< temp1 300) 30)
				((< temp1 360) 20)
				((< temp1 420) 10)
				(else 0)
			)
		)
		(if egoDefends
			(= local15 0)
		)
		(= egoDefends 0)
		(= local14 thisTime)
	)
)

(instance switchLoop of Code
	(method (doit)
		(if magicIcons
			(pointBox setLoop: 2)
			(if (not (ego has: iSword))
				(dodgeRIcon show: forceUpd: stopUpd:)
				(dodgeLIcon show: forceUpd: stopUpd:)
			)
			(= magicIcons FALSE)
		else
			(pointBox setLoop: 3)
			(if (not (ego has: iSword))
				(dodgeRIcon hide: forceUpd: stopUpd:)
				(dodgeLIcon hide: forceUpd: stopUpd:)
			)
			(= magicIcons TRUE)
		)
	)
)

(instance blood of Prop
	(properties
		cycleSpeed 3
	)
)

(instance zapCrap of Prop
	(properties
		view 535
		loop 7
		cycleSpeed 3
	)
)
