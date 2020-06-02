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
	local1
	[local2 3]
	local5 =  2
	local6
	[local7 2]
	local9
	local10
	[local11 3]
	local14
	local15
	local16
	local17
	local18
	local19
)
(instance aSpell of Actor)

(instance monHurt of Sound
	(properties
		flags $ffff
		number 102
		priority 1
	)
)

(instance closeCombat of Script
	
	(method (init)
		(monHurt init:)
		(Bset fFlag284)
		(Bclr fNextMonster)
		(user canInput: FALSE)
		(pointBox init: setLoop: 2 stopUpd:)
		(= local10 0)
		(if (ego has: iSword)
			(Load VIEW 109)
			(= local18 1)
			(= local10 0)
			(blood view: 535)
		else
			(= local18 0)
			(dodgeLIcon init: setPri: 15 hide: stopUpd:)
			(dodgeRIcon init: setPri: 15 hide: stopUpd:)
			(if (!= heroType THIEF) (switchLoop doit:))
			(Load VIEW 102)
			(blood view: 535)
			(aSpell view: 535)
		)
		(= egoCanFight 1)
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
				(= local1 (ScriptID 213 0))
				(directionHandler addToFront: client)
				(mouseDownHandler addToFront: client)
				(= ticks 1)
			)
			(1
				(cond 
					((local1 noWeapon?)
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
					((<= (local1 stamina?) 0)
						(client canFight: 0 action: 10)
						(EgoDead C_DIE_EXHAUSTION C_DIE_EXHAUSTION_TITLE)
					)
					(((local1 opponent?) ateEgo?))
					(else
						(if local18 (client view: 109) else (client view: 102))
						(client
							canFight: 1
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
	
	(method (handleEvent event &tmp eX eY temp2 temp3)
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
			(while ((= temp3 (Event new: 71)) type?)
				(temp3 dispose:)
			)
			(temp3 dispose:)
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
									(= local5 2)
									(cond 
										((not local10)
											(self setScript: egoThrust self local5)
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
									(= local5 1)
									(cond 
										((not local10)
											(self setScript: egoThrust self local5)
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
									(= local5 0)
									(cond 
										((not local10) (self setScript: egoDodge self local5))
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
									(= local5 2)
									(cond 
										((== (pointBox loop?) 2) (self setScript: egoBlock self local5))
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
							(= local5 2)
							(cond 
								((not local10) (self setScript: egoThrust self local5))
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
								(else (messager say: N_ROOM NULL C_DONTKNOWSPELL 0 0 215))
							)
						)
						(dirSE
							(theGame setCursor: normalCursor TRUE 293 170)
							(= local5 0)
							(cond 
								((not local10)
									(self setScript: egoDodge self local5)
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
							(= local5 1)
							(cond 
								((not local10)
									(self setScript: egoThrust self local5)
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
							(= local5 2)
							(cond 
								((not local10)
									(self setScript: egoBlock self local5)
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
							(if (== (event type?) 68)
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
	(properties)
	
	(method (init)
		(= local1 (ScriptID 213 0))
		((= local6 (ScriptID 215 1))
			init:
			posn: 500 500
			stopUpd:
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(HandsOn)
		(if local6 (local6 dispose:))
		(user canInput: FALSE)
		(theGame setCursor: normalCursor TRUE)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(local1
					canFight: FALSE
					action: 11
					view: 107
					setCel: 0
					setPri: 15
				)
				(local1 setCycle: CycleTo 4 1 self)
				((ScriptID monsterNum 0) inTransit: 1)
			)
			(1
				(local6
					view: 535
					setLoop: 6
					setCel: 1
					cycleSpeed: 4
					moveSpeed: 0
					xStep: 50
					yStep: 30
					ignoreActors:
					setPri: (+ ((local1 opponent?) priority?) 1)
					posn: (+ (local1 x?) 43) (- (local1 y?) 56)
				)
				(local1 setCycle: EndLoop self)
			)
			(2 (= ticks 1))
			(3
				(local1 setLoop: 0 setCel: 0 setPri: -1)
				(local6 setPri: (+ ((local1 opponent?) priority?) 1))
				(cond 
					(
						(or
							(== monsterNum 425)
							(== monsterNum 430)
							(== monsterNum 440)
							(== monsterNum 450)
							(== monsterNum 460)
						)
						(= ticks 1)
					)
					((!= monsterNum 435)
						(local6
							setMotion:
								MoveTo
								((local1 opponent?) flameX?)
								((local1 opponent?) flameY?)
								self
						)
					)
					(else
						(local6
							setMotion:
								MoveTo
								(- ((local1 opponent?) x?) 37)
								(+ ((local1 opponent?) y?) 30)
								self
						)
					)
				)
			)
			(4
				(local6
					setPri: (+ ((local1 opponent?) priority?) 1)
					setCycle: EndLoop self
				)
			)
			(5
				(local6 priority: (+ ((local1 opponent?) priority?) 1))
				((local1 opponent?) setCycle: 0 setScript: 0)
				(if (== monsterNum 455)
					((local1 opponent?)
						x: 123
						y: 25
						loop: 3
						setCel: 0
						stopUpd:
					)
					((ScriptID 455 3) hide: forceUpd:)
				)
				(if (== monsterNum 450)
					((ScriptID 450 3) cel: 0 x: 182 y: 91 show: forceUpd:)
					((local1 opponent?)
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
				(local6 dispose:)
				((ScriptID 425 3) x: 500 y: 500 setCycle: 0 forceUpd:)
				(Bset fFlag285)
				(if (== monsterNum 455)
					((ScriptID 455 3)
						loop: 2
						cel: 0
						x: 166
						y: 88
						show:
						forceUpd:
					)
					((local1 opponent?)
						loop: 1
						x: 165
						y: 80
						setCel: 0
						stopUpd:
					)
				)
				(if (or (== monsterNum 445) (== monsterNum 465))
					((local1 opponent?) cycleSpeed: 6)
				else
					((local1 opponent?) cycleSpeed: 23)
				)
				(monHurt play:)
				((local1 opponent?)
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(7
				((local1 opponent?) getHurt: (+ 5 (/ [egoStats FLAMEDART] 3)))
				((local1 opponent?)
					setMonsterHP: ((local1 opponent?) health?)
				)
				(Animate (cast elements?) 0)
				(if (<= ((local1 opponent?) health?) 0)
					((local1 opponent?) die:)
				)
				(if
					(and
						(or (== monsterNum 445) (== monsterNum 465))
						(<= ((local1 opponent?) health?) 0)
					)
					((local1 opponent?) setScript: (ScriptID monsterNum 3))
				else
					((ScriptID monsterNum 0) inTransit: 0)
					((local1 opponent?) setScript: (ScriptID monsterNum 2))
				)
				(local1 canFight: TRUE view: 102 setCel: 0 setLoop: 0)
				(self dispose:)
			)
		)
	)
)

(instance egoZap of Script
	(properties)
	
	(method (init)
		(= local1 (ScriptID 213 0))
		(= local6 (ScriptID 215 1))
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= zapPower (+ 5 (/ [egoStats ZAP] 10)))
				(local1
					canFight: FALSE
					action: 11
					view: 108
					loop: 0
					cel: 0
					forceUpd:
					setPri: 15
				)
				(local1 setCycle: EndLoop self)
			)
			(1 (= ticks 1))
			(2
				(local1 canFight: TRUE view: 102 cel: 0 forceUpd: setPri: -1)
				(self dispose:)
			)
		)
	)
)

(instance egoDazzle of Script
	(properties)
	
	(method (init)
		(= local1 (ScriptID 213 0))
		(= local6 (ScriptID 215 1))
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(local1
					canFight: 0
					action: 11
					view: 107
					setCel: 0
					setPri: 15
				)
				(local1 setCycle: EndLoop self)
			)
			(1 (= ticks 15))
			(2
				(local1 setLoop: 0 setCel: 0 setPri: -1)
				(local6
					view: 535
					setLoop: 5
					cel: 0
					setPri: 15
					cycleSpeed: 6
					ignoreActors:
					posn: (+ (local1 x?) 35) (- (local1 y?) 60)
					init:
					setCycle: EndLoop self
				)
			)
			(3
				(local6 dispose:)
				(Bset 233)
				(= monsterDazzle [egoStats 20])
				(local1 canFight: 1 action: 0)
				(self dispose:)
			)
		)
	)
)

(instance egoThrust of Script
	(properties)
	
	(method (init)
		(= local1 (ScriptID 213 0))
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(local1 getTired: 4 setCel: 0 canFight: 0 action: 1)
				(if
					(and
						local18
						(or (== monsterNum 450) (== monsterNum 435))
						(== local5 2)
					)
					(local1 view: 114)
				else
					(if (ego has: iSword)
						(local1 view: (if (== local5 2) 110 else 109))
					else
						(local1 view: (if (== local5 2) 103 else 102))
					)
					(if (!= local5 2) (local1 setCel: 1))
				)
				(= register 0)
				((ScriptID monsterNum 0) inTransit: 1)
				(= egoCanFight 0)
				(TrySkill 5 0 0)
				(= local9 (local1 tryAttack: (local1 opponent?)))
				(if
					(and
						(or
							(== monsterNum 425)
							(== monsterNum 465)
							(== monsterNum 435)
							(== monsterNum 445)
							(== monsterNum 450)
							(== monsterNum 440)
							(== monsterNum 455)
							(== monsterNum 420)
						)
						(== ((local1 opponent?) action?) 3)
					)
					(= local9 0)
				)
				(if local9
					(blood init: posn: 500 500 stopUpd:)
					(zapCrap init: posn: 500 500 stopUpd:)
					((local1 opponent?) setCycle: 0 setScript: 0 stopUpd:)
					(local1 doDamage: (local1 opponent?) zapPower)
					(switch monsterNum
						(440
							((local1 opponent?)
								view: 442
								setLoop: 1
								setCel: 0
								stopUpd:
							)
						)
						(430
							(if (Random 0 1)
								((local1 opponent?) setLoop: 0 setCel: 1 stopUpd:)
							else
								((local1 opponent?) setLoop: 0 setCel: 7 stopUpd:)
							)
						)
						(445
							((local1 opponent?) setLoop: 1 setCel: 0 stopUpd:)
						)
						(450
							((ScriptID 450 3) x: 182 y: 91 show: setCel: 0 stopUpd:)
							((local1 opponent?)
								x: 182
								y: 91
								setLoop: 1
								setCel: 0
								stopUpd:
							)
						)
						(460
							(if (Random 0 1)
								((local1 opponent?) setLoop: 0 setCel: 2 stopUpd:)
							else
								((local1 opponent?) setLoop: 0 setCel: 7 stopUpd:)
							)
						)
						(420
							(cond 
								((== local5 1)
									(if local18
										((local1 opponent?) setLoop: 0 setCel: 2 stopUpd:)
									else
										((local1 opponent?) setLoop: 0 setCel: 3 stopUpd:)
									)
								)
								((not local18) ((local1 opponent?) setLoop: 0 setCel: 2 stopUpd:))
							)
						)
					)
				else
					(if (and (!= monsterNum 435) (Btst 284))
						(= local17 1)
						((local1 opponent?) setCycle: 0 setScript: 0 stopUpd:)
					)
					(if local17
						(switch monsterNum
							(425
								((ScriptID 425 3) x: 500 y: 500 setCycle: 0 forceUpd:)
								((local1 opponent?) setLoop: 0 setCel: 4 stopUpd:)
							)
							(440
								((local1 opponent?)
									view: 444
									setLoop: 0
									setCel: 1
									stopUpd:
								)
							)
							(430
								(if local17
									((local1 opponent?) setLoop: 0 setCel: 0 stopUpd:)
								)
							)
							(445
								((local1 opponent?) setLoop: 2 setCel: 4 stopUpd:)
							)
							(465
								((local1 opponent?) setLoop: 3 setCel: 2 stopUpd:)
							)
							(420
								(if local17
									(cond 
										((and (== local5 2) local18) ((local1 opponent?) setLoop: 0 setCel: 2 stopUpd:))
										((Random 0 1) ((local1 opponent?) setLoop: 0 setCel: 0 stopUpd:))
										(else ((local1 opponent?) setLoop: 0 setCel: 1 stopUpd:))
									)
								)
							)
						)
					)
					(Bset 357)
				)
				((ScriptID 425 3) x: 500 y: 500 setCycle: 0 forceUpd:)
				(if
					(and
						(== monsterNum 455)
						(== ((local1 opponent?) loop?) 0)
						(== local5 2)
						local18
					)
					(= register 1)
					(local1 setPri: 1 setCycle: CycleTo 1 1 self)
					((local1 opponent?) setCycle: 0 setScript: 0 setCel: 0)
				else
					(if local9
						(local1 setPri: 15)
					else
						(if (== monsterNum 450) (local1 setPri: 1))
						(if (== monsterNum 440) (local1 setPri: 15))
					)
					(if (and (== local5 2) (ego has: iSword))
						(local1 setCycle: CycleTo 2 1 self)
					else
						(local1 setCycle: CycleTo 3 1 self)
					)
				)
			)
			(1
				(cond 
					(register (= ticks 1))
					(local9
						(cond 
							((== monsterNum 455) (= ticks 45))
							((== monsterNum 420) (= ticks 25))
							(else (= ticks 1))
						)
						(blood setPri: 15 ignoreActors: setCel: 0)
						(zapCrap setPri: 15 ignoreActors: setCel: 0)
						(monHurt play:)
						(if (== local5 2)
							(if local18
								(if (or (== monsterNum 450) (== monsterNum 435))
									(blood x: (+ (local1 x?) 53) y: (- (local1 y?) 81))
								else
									(blood x: (+ (local1 x?) 64) y: (- (local1 y?) 64))
								)
							else
								(blood x: (+ (local1 x?) 43) y: (- (local1 y?) 70))
								(if zapPower
									(zapCrap
										x: (+ (local1 x?) 43)
										y: (- (local1 y?) 70)
										setCycle: EndLoop
									)
								)
							)
							(blood setLoop: 2 setCycle: EndLoop)
						else
							(if local18
								(blood x: (+ (local1 x?) 42) y: (- (local1 y?) 65))
							else
								(blood x: (+ (local1 x?) 32) y: (- (local1 y?) 56))
								(if zapPower
									(zapCrap
										x: (+ (local1 x?) 32)
										y: (- (local1 y?) 56)
										setCycle: EndLoop
									)
								)
							)
							(blood setLoop: 1 setCycle: EndLoop)
						)
						(= zapPower 0)
					)
					(else (= ticks 8))
				)
			)
			(2
				(local1 setCycle: EndLoop self)
				(if local9
					(Bset 285)
					(if (== monsterNum 455)
						((ScriptID 455 3)
							loop: 2
							cel: 0
							x: 166
							y: 88
							show:
							forceUpd:
						)
						((local1 opponent?)
							loop: 1
							cel: 0
							x: 165
							y: 80
							forceUpd:
						)
					)
					(if (or (== monsterNum 445) (== monsterNum 465))
						((local1 opponent?) cycleSpeed: 6)
					else
						((local1 opponent?) cycleSpeed: 23)
					)
					((local1 opponent?) loop: 1 cel: 0 setCycle: EndLoop self)
				else
					(if local17
						(switch monsterNum
							(425
								((local1 opponent?) setLoop: 0 setCel: 3 stopUpd:)
							)
							(440
								((local1 opponent?)
									view: 444
									setLoop: 0
									setCel: 2
									stopUpd:
								)
							)
							(430
								(if local17
									((local1 opponent?) setLoop: 0 setCel: 1 stopUpd:)
								)
							)
							(445
								((local1 opponent?) setLoop: 2 setCel: 5 stopUpd:)
							)
							(465
								((local1 opponent?) setLoop: 3 setCel: 3 stopUpd:)
							)
							(460
								(if local17
									((local1 opponent?) setLoop: 0 setCel: 0 stopUpd:)
								)
							)
						)
					)
					(self cue:)
				)
			)
			(3
				(if (not (local1 cycler?))
					(if local18
						(local1 view: 109 setCel: 0 stopUpd:)
					else
						(local1 view: 102 setCel: 0 stopUpd:)
					)
				)
			)
			(4
				(if local18
					(local1 view: 109 setCel: 0 stopUpd:)
				else
					(local1 view: 102 setCel: 0 stopUpd:)
				)
				(= ticks 15)
			)
			(5
				(zapCrap dispose:)
				(blood dispose:)
				(if local9
					((local1 opponent?)
						setMonsterHP: ((local1 opponent?) health?)
					)
					(if (<= ((local1 opponent?) health?) 0)
						((local1 opponent?) die:)
					)
				)
				(if local18 (local1 view: 109))
				(cond 
					((or (== monsterNum 450) (== monsterNum 455))
						(if (or local17 local9)
							((local1 opponent?) setScript: (ScriptID monsterNum 2))
							(= local17 0)
						)
						((ScriptID monsterNum 0) inTransit: 0)
					)
					(
						(and
							(or (== monsterNum 445) (== monsterNum 465))
							(<= ((local1 opponent?) health?) 0)
						)
						((local1 opponent?) setScript: (ScriptID monsterNum 3))
					)
					((and (== monsterNum 435) (not local9)) ((ScriptID monsterNum 0) inTransit: 0))
					(else
						(if (not ((local1 opponent?) script?))
							(= local17 0)
							((local1 opponent?) setScript: (ScriptID monsterNum 2))
						)
						((ScriptID monsterNum 0) inTransit: 0)
					)
				)
				(= local9 0)
				(Bclr 233)
				(Bclr 357)
				(local1 canFight: 1)
				(= egoCanFight 1)
				(self dispose:)
			)
		)
	)
)

(instance egoBlock of Script
	(properties)
	
	(method (init)
		(= local16 1)
		(= local1 (ScriptID 213 0))
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoCanFight 0)
				(if local18 (local1 view: 111) else (local1 view: 104))
				(local1 getTired: 1 canFight: 0 action: 3 setCel: 0)
				(TrySkill 6 0 20)
				(if ((local1 opponent?) ateEgo?) (self dispose:))
				(if
					(and
						(or (== monsterNum 455) (== monsterNum 450))
						local18
					)
					(local1 view: 115 loop: 0 forceUpd: setCycle: CycleTo 2 1)
					(= ticks 80)
				else
					(= ticks 1)
				)
			)
			(1 (local1 setCycle: EndLoop self))
			(2 (= ticks 12))
			(3
				(if
					(and
						(or (== monsterNum 455) (== monsterNum 450))
						local18
					)
					(local1 view: 109 forceUpd:)
				)
				(local1 setCel: 0 stopUpd:)
				(= egoCanFight 1)
				(self dispose:)
			)
		)
	)
)

(instance egoParry of Script
	(properties)
	
	(method (init)
		(= local16 1)
		(= local1 (ScriptID 213 0))
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoCanFight 0)
				(TrySkill 6 0 20)
				(local1
					getTired: 1
					canFight: 0
					action: 3
					setLoop: 4
					setCel: 0
					stopUpd:
				)
				(= cycles 1)
			)
			(1
				(local1 setCel: 1)
				(= cycles 7)
			)
			(2
				(local1 setCel: 0)
				(= cycles 1)
			)
			(3
				(local1 cel: 0 setLoop: 2 stopUpd:)
				(= egoCanFight 1)
				(self dispose:)
			)
		)
	)
)

(instance egoDodge of Script
	(properties)
	
	(method (init)
		(= local16 1)
		(= local1 (ScriptID 213 0))
		(super init: &rest)
	)
	
	(method (doit)
		(if ((local1 opponent?) ateEgo?)
			(local1 setCycle: 0 setCycle: CycleTo 0 -1)
			(self dispose:)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoCanFight 0)
				(TrySkill 7 0 20)
				(cond 
					(
						(and
							local18
							(or (== monsterNum 450) (== monsterNum 455))
						)
						(local1 view: 116 setLoop: 0)
					)
					(local18 (local1 view: 112 setLoop: 0))
					(else (local1 view: 105))
				)
				(if local18 (local1 setCel: 1))
				(local1
					getTired: 2
					canFight: 0
					action: (if (== register 0) 5 else 6)
					setCel: 0
				)
				(local1 setCycle: EndLoop self)
			)
			(1 (= ticks 12))
			(2
				(if (== (local1 view?) 110) (local1 view: 109))
				(local1 setCel: 0 stopUpd:)
				(= egoCanFight 1)
				(self dispose:)
			)
		)
	)
)

(instance painReaction of Script
	(properties)
	
	(method (init)
		(= local1 (ScriptID 213 0))
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoCanFight 0)
				(= ticks 1)
			)
			(1
				(if (Btst 248) (= ticks 1))
				(= egoCanFight 1)
			)
			(2 (self dispose:))
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
		first 1
		leftBor 260
		rightBor 303
		topBor 130
		botBor 175
		value 1000
	)
	
	(method (init)
		(theIconBar disable:)
		(theGame setCursor: normalCursor 1 280 155)
		(self setPri: 14 ignoreActors:)
		(super init: &rest)
	)
	
	(method (doit &tmp newEvent theRelX theRelY theOldX theOldY temp5 temp6 temp7)
		(= theOldX ((= newEvent (Event new:)) x?))
		(= theOldY (- (newEvent y?) 10))
		(if first
			(= oldX theOldX)
			(= oldY theOldY)
			(= first 0)
		)
		(if (or (!= theOldX oldX) (!= theOldY oldY))
			(if (!= theOldX oldX)
				(= temp5 (- theOldX oldX))
				(= oldX theOldX)
				(cond 
					((< (= theRelX (+ relX temp5)) leftBor) (= theRelX (= oldX leftBor)))
					((> theRelX rightBor) (= theRelX (= oldX rightBor)))
				)
			else
				(= theRelX relX)
			)
			(if (!= theOldY oldY)
				(= temp6 (- theOldY relY))
				(= oldY theOldY)
				(cond 
					((< (= theRelY (+ relY temp6)) topBor) (= theRelY (= oldY topBor)))
					((> theRelY botBor) (= theRelY (= oldY botBor)))
				)
			else
				(= theRelY relY)
			)
			(= relY theRelY)
			(theGame
				setCursor: normalCursor 1 (= relX theRelX) theRelY
			)
		)
		(newEvent dispose:)
		(super doit: &rest)
	)
	
	(method (dispose)
		(theGame setCursor: normalCursor 1)
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
	(properties)
	
	(method (doit &tmp temp0 temp1)
		(= temp0 (GetTime))
		(= local15
			(cond 
				((< (= temp1 (Abs (- temp0 local14))) 180) 50)
				((< temp1 240) 40)
				((< temp1 300) 30)
				((< temp1 360) 20)
				((< temp1 420) 10)
				(else 0)
			)
		)
		(if local16
			(= local15 0)
		)
		(= local16 0)
		(= local14 temp0)
	)
)

(instance switchLoop of Code
	(properties)
	
	(method (doit)
		(if local10
			(pointBox setLoop: 2)
			(if (not (ego has: iSword))
				(dodgeRIcon show: forceUpd: stopUpd:)
				(dodgeLIcon show: forceUpd: stopUpd:)
			)
			(= local10 0)
		else
			(pointBox setLoop: 3)
			(if (not (ego has: iSword))
				(dodgeRIcon hide: forceUpd: stopUpd:)
				(dodgeLIcon hide: forceUpd: stopUpd:)
			)
			(= local10 1)
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
