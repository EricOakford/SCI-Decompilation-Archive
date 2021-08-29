;;; Sierra Script 1.0 - (do not remove this comment)
(script# rKoboldCave) ;15
(include game.sh)
(use Main)
(use ThrowKnife)
(use ThrowRock)
(use Intrface)
(use Target)
(use LoadMany)
(use Sound)
(use Save)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm15 0
	KoboldFight 1
	chest 2
	kobKey 3
	kobDazzle 4
	chestBlows 5
	AwakenKobold 6
	KoboldHurtEgo 7
	egoFighting 8
)

(local
	local0 = [79 113 172 226 306]
	local5 = [204 189 193 191 203]
	newView
	local11
	theCycles
	local13
	local14
	local15
	local16
	local17
	newSound
	ballX = [17 44 17 251 101 183 283 316 102 244 169]
	ballY = [47 155 134 186 47 145 124 212 161 51 55]
	local41 = [-24 24 -17 17 -5]
	local46 = [6 6 10 10 13]
)
(procedure (KoboldFight param1)
	(if (and param1 (not (Btst fFlag283)))
		(HandsOn)
	)
	(if fightingKobold
		(User canControl: FALSE)
		(if (ego has: iSword)
			(ego view: vEgoFightWithSword setLoop: egoKoboldBattleLoop)
		else
			(ego view: vEgoFightDaggerNoCape setLoop: (* egoKoboldBattleLoop 5))
		)
		(ego setCel: 0 illegalBits: 0 cycleSpeed: 0)
	else
		(NormalEgo)
		(ego illegalBits: koboldIllBits)
	)
)

(procedure (AwakenKobold)
	(return
		(if
			(and
				(not (Btst fKoboldAwake))
				(not (Btst fKoboldDead))
				(!= (kobold script?) kobWakeUp)
			)
			(kobold setScript: kobWakeUp)
			(return TRUE)
		else
			(return FALSE)
		)
	)
)

(procedure (KoboldHurtEgo damage)
	(if (not (TakeDamage damage)) 
		(ego setScript: egoDies)
	)
)

(procedure (SendKoboldSaidEvent event param2)
	(event type: allEvents claimed: FALSE)
	(Parse param2 event)
	(User said: event)
)

(procedure (localproc_003c param1)
	(Print
		&rest
		#at -1 150
		#width 0
		#mode teJustCenter
		#dispose
		#time param1
	)
)

(procedure (KoboldPrint seconds)
	(Print
		&rest
		#at 150 2
		#width 0
		#mode teJustCenter
		#window kobWin
		#dispose
		#time seconds
	)
)

(procedure (localproc_0079 param1)
	(Bset fKoboldChestSearched)
	(SolvePuzzle f15GetTreasure 5)
	(CenterPrint rKoboldCave 0)
	(if param1 (param1 dispose:))
	(&= koboldIllBits (~ cLMAGENTA))
	(ego illegalBits: koboldIllBits get: iGold 10 get: iSilver 60)
)

(procedure (localproc_0138 &tmp temp0 temp1)
	(if
		(!=
			(= temp1
				(cond 
					(
						(<
							(= temp0
								(GetAngle (kobold x?) (kobold y?) (ego x?) (ego y?))
							)
							120
						)
						1
					)
					((< temp0 150) 3)
					((> temp0 240) 0)
					((> temp0 210) 2)
					(else 4)
				)
			)
			(kobold loop?)
		)
		(kobold setLoop: temp1)
	)
)

(procedure (localproc_01b5 param1 param2 param3)
	(if (CastSpell param1)
		(Bset fFightingKobold)
		(AwakenKobold)
		(ego setScript: (ScriptID param2 param3))
	)
)

(procedure (SaidKoboldRoom param1 param2 param3)
	(cond 
		((Btst fKoboldAwake) (CenterPrint rKoboldCave 1))
		((CastSpell param1) (ego setScript: (ScriptID param2 param3)))
	)
)

(class KScript of Script
	(method (cue)
		(if client (super cue:))
	)
)

(class ballScript of KScript
	(method (doit)
		(cond 
			((Btst fKoboldDead)
				(client dispose:)
			)
			((and (< state 2) (not (client inRect: 10 30 310 200)))
				(= register 0)
				(self changeState: 2)
			)
			(else
				(super doit:)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ballSound play:)
				(if
					(>=
						(-
							(- 100 (/ [egoStats AGIL] 2))
							(/ (ego distanceTo: kobold) 5)
						)
						(Rand100)
					)
					(client setMotion: MoveTo (ego x?) (ego y?) self)
				else
					(client
						setMotion:
							MoveTo
							[ballX register]
							[ballY (= register (Random 0 2))]
							self
					)
				)
			)
			(1
				(cond 
					((< (ego distanceTo: client) 20) (= register 1) (self cue:))
					(
					(>= (= register (+ register (Random 1 4))) 11) (= register 0) (self cue:))
					(else
						(client
							setMotion: MoveTo [ballX register] [ballY (= state 0)] self
						)
					)
				)
			)
			(2
				(ballHits play:)
				(client
					setLoop: (if register 7 else 6)
					cel: 0
					setMotion: 0
					setCycle: EndLoop self
				)
				(if register
					(KoboldHurtEgo (if (ego has: 9) 15 else 20))
				)
			)
			(3 (client dispose:))
		)
	)
)

(instance rm15 of Room
	(properties
		picture 15
		style DISSOLVE
		west 14
	)
	
	(method (init)
		(= fightingKobold (= fightingKoboldStart FALSE))
		(if [egoStats MAGIC]
			(LoadMany SCRIPT 110 113 114 115)
			(Load SOUND (SoundFX 33))
			(LoadMany VIEW vEgoMagicFetch vEgoMagicDetect vEgoMagicFlameDart vExplosion)
		)
		(if (not (Btst fKoboldDead))
			(Load SCRIPT 111)
			(Load SCRIPT 117)
			(Load SOUND (SoundFX 45))
			(Load SOUND (SoundFX 34))
			(LoadMany VIEW vEgoDaggerDefeated vKoboldSitting vKoboldFighting vKoboldDead vEgoBeginFight)
			(if (ego has: iSword)
				(Load VIEW vEgoFightWithSword)
			else
				(Load VIEW vEgoFightDaggerNoCape)
				(Load VIEW vEgoThrowDagger)
			)
		)
		(if (not (Btst fKoboldChestUnlocked))
			(Load SCRIPT 116)
			(Load SOUND (SoundFX 36))
			(Load SOUND (SoundFX 62))
		)
		(Load VIEW vEgoThrowing)
		(SolvePuzzle f15EnterCave 2)
		(StatusLine enable:)
		(super init:)
		(keyDownHandler add: self)
		(mouseDownHandler add: self)
		(ChangeGait MOVE_WALK FALSE)
		(NormalEgo)
		(ego posn: 1 63 init:)
		(drip init: setScript: dripper)
		(dripper cycles: (Random 5 25))
		(if (not (Btst fTookToadstools)) (toadstools init: setPri: 9))
		(if (not (Btst fKoboldChestUnlocked))
			(chest init: stopUpd:)
			(|= koboldIllBits cLMAGENTA)
		)
		(if (not (Btst fGotKoboldKey))
			(if egoKoboldBattleLoop
				(kobKey posn: 52 84)
			)
			(kobKey
				ignoreActors:
				setPri: (if egoKoboldBattleLoop 5 else 1)
				init:
				stopUpd:
			)
			(if (not (Btst fKoboldDead))
				(kobKey hide:)
			)
		)
		(if (not (Btst fKoboldDead))
			(|= koboldIllBits cYELLOW)
			(kobold init:)
			(= theKobold kobold)
		)
		(ego illegalBits: koboldIllBits)
		(self setScript: kobEnter)
	)
	
	(method (dispose)
		(Bset fBeenIn15)
		(Bclr fKoboldChestKnown)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp eventType temp1 spell)
		(cls)
		(= eventType (event type?))
		(cond 
			((super handleEvent: event))
			((== eventType mouseDown)
				(cond 
					(
						(and
							(cast contains: kobKey)
							(MouseClaimed kobKey event shiftDown)
						)
						(SendKoboldSaidEvent event {look key})
					)
					(
						(and
							(cast contains: treasure)
							(MouseClaimed treasure event shiftDown)
						)
						(SendKoboldSaidEvent event {look coins})
					)
					(
						(and
							(cast contains: chest)
							(MouseClaimed chest event shiftDown)
						)
						(SendKoboldSaidEvent event {look chest})
					)
					(
						(and
							(cast contains: toadstools)
							(MouseClaimed toadstools event shiftDown)
						)
						(SendKoboldSaidEvent event {look mushroom})
					)
					(
						(and
							(not (Btst fKoboldDead))
							(MouseClaimed kobold event shiftDown)
						)
						(SendKoboldSaidEvent event {look kobold})
					)
				)
			)
			((!= eventType saidEvent))
			((and (not (Btst fKoboldDead)) (Said 'chat,ask,awaken'))
				(CenterPrint rKoboldCave 2)
			)
			((Said 'nap,rest')
				(CenterPrint rKoboldCave 3)
			)
			((Said 'search')
				(if (Btst fKoboldAwake)
					(CenterPrint rKoboldCave 4)
				else
					(ego setScript: (ScriptID 112 0))
				)
			)
			((Said 'get,grab,get/key[<kobold,brass,big,magic,glowing]')
				(cond 
					((cast contains: kobKey)
						(= temp1 0)
						(cond 
							((> (ego distanceTo: kobKey) 40) (CenterPrint rKoboldCave 5))
							((Btst fKoboldDead) (CenterPrint rKoboldCave 6) (= temp1 1))
							((Btst fKoboldAwake) (CenterPrint rKoboldCave 7))
							((TrySkill STEALTH trySneakKobold 0) (CenterPrint rKoboldCave 8) (= temp1 1))
							(else (CenterPrint rKoboldCave 9) (AwakenKobold))
						)
						(if temp1
							(ego get: iBrassKey)
							(Bset fGotKoboldKey)
							(SolvePuzzle f15GetKey 7)
							(kobKey dispose:)
						)
					)
					((ego has: iBrassKey) (CenterPrint rKoboldCave 10))
					((ego pickUp: iBrassKey 1) (CenterPrint rKoboldCave 11))
					(else (CenterPrint rKoboldCave 12))
				)
			)
			(
				(and
					(Btst fKoboldChestKnown)
					(Said 'get,grab,get,move/chest,box,trunk')
				)
				(CenterPrint rKoboldCave 13)
			)
			((Said 'get/loot,alm,gold,silver')
				(cond 
					(
						(and
							(< (ego distanceTo: chest) 25)
							(cast contains: treasure)
						)
						(localproc_0079 treasure)
					)
					((Btst fKoboldChestSearched) (CenterPrint rKoboldCave 14))
					((not (< (ego distanceTo: chest) 25)) (CenterPrint rKoboldCave 15))
					((not (Btst fKoboldChestUnlocked)) (CenterPrint rKoboldCave 16))
					(else (localproc_0079 0))
				)
			)
			(
				(or
					(Said 'use,turn,open,unlock/key,chest,box,trunk[/key]')
					(Said 'put,fill<in/key/hasp')
				)
				(if (and (Btst fKoboldChestKnown) (cast contains: chest))
					(CenterPrint rKoboldCave 17)
				else
					(CenterPrint rKoboldCave 18)
				)
			)
			(
				(or
					(Said 'lockpick/hasp,keyhole,chest,box,trunk')
					(Said 'use,fill/lockpick,(implement,kit<thief)')
				)
				(cond 
					((or (not (Btst fKoboldChestKnown)) (not (cast contains: chest)))
						(CenterPrint rKoboldCave 18)
					)
					((Btst fKoboldChestUnlocked) (CenterPrint rKoboldCave 19))
					((not (< (ego distanceTo: chest) 25)) (NotClose))
					((not [egoStats PICK]) (CenterPrint rKoboldCave 20))
					(else (ego setScript: (ScriptID 116 0) 0 0))
				)
			)
			((Said 'force,break,pry/chest,box,trunk,hasp,lid')
				(cond 
					((or (not (Btst fKoboldChestKnown)) (not (cast contains: chest)))
						(CenterPrint rKoboldCave 21)
					)
					((Btst fKoboldChestUnlocked) (CenterPrint rKoboldCave 19))
					((not (< (ego distanceTo: chest) 25)) (NotClose))
					(else (ego setScript: (ScriptID 116 0) 0 1))
				)
			)
			(
				(and
					(cast contains: toadstools)
					(Said 'get/toadstool,mushroom,food')
				)
				(if fightingKobold
					(CenterPrint rKoboldCave 22)
				else
					(CenterPrint rKoboldCave 23)
					(Bset fTookToadstools)
					(Bset fHaveToadstools)
					(ego get: iMushroom 6)
					(toadstools dispose:)
				)
			)
			((Said 'get/fungus') (CenterPrint rKoboldCave 24))
			((Said 'feel/chest,box,trunk') (SendKoboldSaidEvent event {look chest}))
			((Said 'look>')
				(cond 
					((Said '/west,open,entrance') (CenterPrint rKoboldCave 25))
					(
						(or
							(Said
								'/stalactite,stalagmite,boulder,ceiling,roof,floor,ground,formation'
							)
							(Said '<up,down')
						)
						(CenterPrint rKoboldCave 26)
					)
					((Said '[<at,around][/!*,cave,room,area,wall,fungus]')
						(CenterPrint rKoboldCave 27)
						(if (not (Btst fKoboldDead)) (CenterPrint rKoboldCave 28))
					)
					((and (cast contains: kobKey) (Said '/key')) (CenterPrint rKoboldCave 29))
					((Said '/table,mushroom,toadstool,food')
						(if (cast contains: toadstools)
							(HighPrint 15 30)
						else
							(CenterPrint rKoboldCave 31)
						)
					)
					((Said '/kobold,creature,man,wizard')
						(if (not (Btst fKoboldDead))
							(CenterPrint rKoboldCave 32)
							(if (not (Btst fGotKoboldKey)) (CenterPrint rKoboldCave 33))
						else
							(CenterPrint rKoboldCave 34)
						)
					)
					(
						(or
							(Said '<in/chest,box,trunk')
							(Said '/loot,alm,gold,silver')
						)
						(cond 
							((cast contains: treasure) (CenterPrint rKoboldCave 35))
							((not (cast contains: chest)) (CenterPrint rKoboldCave 36))
							((not (Btst fKoboldChestKnown)) (CenterPrint rKoboldCave 37))
							((Btst fKoboldChestSearched) (CenterPrint rKoboldCave 38))
							((Btst fKoboldChestUnlocked) (CenterPrint rKoboldCave 39))
							(else (CenterPrint rKoboldCave 40))
						)
					)
					((Said '/chest,box,trunk')
						(cond 
							((not (cast contains: chest)) (CenterPrint rKoboldCave 41))
							((Btst fKoboldChestKnown) (CenterPrint rKoboldCave 42))
							(else (CenterPrint rKoboldCave 43))
						)
					)
				)
			)
			((Said 'run,escape')
				(CenterPrint rKoboldCave 44)
				(if (not (Btst fKoboldDead)) (CenterPrint rKoboldCave 45))
				(if fightingKobold (self setScript: (ScriptID 117 1)))
			)
			(
				(Said
					'fight,attack,kill,beat[/kobold,creature,man,wizard]'
				)
				(cond 
					(fightingKobold (CenterPrint rKoboldCave 46))
					((Btst fKoboldDead) (CenterPrint rKoboldCave 47))
					((not (if (ego has: iSword) else (ego has: iDagger))) (CenterPrint rKoboldCave 48))
					(else (self setScript: (ScriptID 117 0)))
				)
			)
			((Said 'throw>')
				(cond 
					(fightingKobold (event claimed: 1) (CenterPrint rKoboldCave 49))
					((Btst fKoboldDead))
					((Said '/dagger') (ThrowKnife kobold))
					((Said '/boulder') (ThrowRock kobold))
				)
			)
			((Said 'cast>')
				(if
					(and
						(= spell (SaidSpell event))
						fightingKobold
						(ego has: iShield)
					)
					(CenterPrint rKoboldCave 50)
					(event claimed: TRUE)
					(= spell -1)
				)
				(switch spell
					(-1)
					(0 (event claimed: FALSE))
					(DAZZLE
						(if (not (Btst fKoboldDead))
							(localproc_01b5 20 113 0)
						else
							(event claimed: FALSE)
						)
					)
					(FLAMEDART
						(if (not (Btst fKoboldDead))
							(localproc_01b5 23 110 0)
						else
							(event claimed: FALSE)
						)
					)
					(CALM (localproc_01b5 22 114 0))
					(ZAP (event claimed: FALSE))
					(fightingKobold
						(CenterPrint rKoboldCave 1)
					)
					(DETMAGIC (SaidKoboldRoom 18 113 1))
					(OPEN
						(if
						(and (not (Btst fKoboldChestUnlocked)) (cast contains: chest))
							(SaidKoboldRoom 17 115 0)
						else
							(CenterPrint rKoboldCave 51)
						)
					)
					(TRIGGER
						(if
						(and (not (Btst fKoboldChestUnlocked)) (cast contains: chest))
							(SaidKoboldRoom 19 115 1)
						else
							(CenterPrint rKoboldCave 52)
						)
					)
					(FETCH
						(if
						(and (not (Btst fKoboldDead)) (cast contains: kobKey))
							(SaidKoboldRoom 24 114 1)
						else
							(CenterPrint rKoboldCave 53)
						)
					)
					(else  (event claimed: FALSE))
				)
			)
		)
	)
)

(instance kobKey of Actor
	(properties
		y 85
		x 229
		view vKoboldDead
		loop 6
		cel 12
		illegalBits $0000
	)
)

(instance chest of Prop
	(properties
		y 173
		x 132
		view rKoboldCave
		loop 1
	)
	
	(method (doit)
		(if (and (< (ego distanceTo: chest) 25) (not (Btst fKoboldChestKnown)))
			(CenterPrint rKoboldCave 54)
			(Bset fKoboldChestKnown)
		)
		(super doit:)
	)
)

(instance chestBlows of KScript
	(properties)
	
	(method (dispose)
		(if (>= state 1) (newSound dispose:))
		(= local17 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setLoop: 2
					cel: 0
					cycleSpeed: 0
					setCycle: CycleTo 4 1 self
				)
			)
			(1
				(Bset fKoboldChestUnlocked)
				((= newSound (Sound new:))
					number: (SoundFX 62)
					priority: 15
					init:
					play:
				)
				(client setCycle: EndLoop)
				(AwakenKobold)
				(if (< (ego distanceTo: chest) 25)
					(= local17 1)
					(KoboldHurtEgo 20)
				)
				(= cycles 10)
			)
			(2
				(= cycles 1)
				(if (< (ego distanceTo: chest) 25)
					(CenterPrint rKoboldCave 55)
					(= cycles 5)
				)
			)
			(3
				(treasure init:)
				(KoboldFight 1)
				(Face ego treasure)
				(client dispose:)
			)
		)
	)
)

(instance treasure of View
	(properties
		y 173
		x 132
		view rKoboldCave
		loop 2
		cel 8
	)
)

(instance toadstools of View
	(properties
		y 117
		x 213
		view rKoboldCave
		loop 3
	)
)

(instance drip of Prop
	(properties
		y 204
		x 79
		view rKoboldCave
	)
)

(instance dripEndLoop of EndLoop
	(properties)
)

(instance dripper of KScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(1
				(= temp0 (Random 0 4))
				(drip
					posn: [local0 temp0] [local5 temp0]
					setCycle: dripEndLoop
				)
				(= state 0)
				(= cycles (Random 20 40))
			)
		)
	)
)

(instance kobWin of SysWindow
	(properties
		color 6
	)
)

(instance ballSound of Sound
	(properties
		number 34
		priority 2
	)
)

(instance ballHits of Sound
	(properties
		number 45
		priority 3
	)
)

(instance kobold of TargActor
	(properties
		y 85
		x 229
		view vKoboldSitting
		loop 6
		cycleSpeed 3
		illegalBits $0000
		targDeltaY -15
	)
	
	(method (init)
		(Bclr fFlag280)
		(Bclr fFlag281)
		(ballSound number: (SoundFX 34) init:)
		(ballHits number: (SoundFX 45) init:)
		(if (ego knows: FLAMEDART)
			(= damageToKoboldFlame (+ 5 (/ [egoStats FLAMEDART] 3)))
		)
		(= egoKoboldBattleLoop 0)
		(super ignoreActors: posn: 229 85 setPri: 6 init:)
		(if (< numColors 8) (kobWin color: vBLACK back: vWHITE))
		(if
			(or
				(not (Btst fBeenIn15))
				(and
					(not Night)
					(!= Day dayKoboldAwakened)
				)
			)
			(self setScript: kobAsleep)
		else
			(self setScript: kobAwake)
		)
		(= monsterNum rKoboldCave)
		(= monsterHealth koboldHealth)
	)
	
	(method (dispose)
		(Bclr fKoboldAwake)
		(ballHits dispose:)
		(ballSound dispose:)
		(if newView (newView dispose:))
		(super dispose:)
	)
	
	(method (getHurt param1)
		(= koboldHealth (- koboldHealth param1))
		(self setScript: kobHurt)
	)
)

(instance kobDazzle of KScript
	(properties)
	
	(method (dispose)
		(= local11 (* register 5))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SkillUsed DAZZLE tryStatKoboldDazzle)
				(= register (/ (+ 5 [egoStats DAZZLE]) 10))
				(if (> register 6) (= register 6))
				(client view: vKoboldReflect setCel: 0)
				(= cycles 10)
			)
			(1
				(kobold setCel: -1 cycleSpeed: 2 setCycle: CycleTo 2 1)
				(= cycles 10)
			)
			(2
				(kobold setCel: 3)
				(= cycles 4)
			)
			(3
				(kobold setCel: 4)
				(= cycles 4)
			)
			(4
				(if (> (-- register) 0)
					(self changeState: 2)
				else
					(= cycles 5)
				)
			)
			(5
				(kobold setCel: 5)
				(= cycles 20)
			)
			(6 (client setScript: kobAwake))
		)
	)
)

(instance kobWakeUp of KScript
	(properties)
	
	(method (dispose)
		(if newView
			(newView dispose:)
			(= newView 0)
			(client setCycle: 0)
		)
		(if egoKoboldBattleLoop
			(client posn: 52 84)
		else
			(client posn: 229 85)
		)
		(cSound stop:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fKoboldAwake)
				(client setCycle: EndLoop)
				(= cycles 12)
			)
			(1
				(client setLoop: 7 setCycle: Forward)
				(KoboldPrint 5 15 56)
				(= seconds 3)
			)
			(2 (client setScript: kobAwake))
		)
	)
)

(instance kobAwake of KScript
	(properties)
	
	(method (doit)
		(localproc_0138)
		(if (and (Btst fFlag280) (== state 0))
			(Bclr fFlag280)
			(self changeState: 3)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp clientLoop)
		(switch (= state newState)
			(0
				(Bset fKoboldAwake)
				(= dayKoboldAwakened Day)
				(client view: vKoboldSitting cel: 0 setCycle: 0)
				(localproc_0138)
				(if egoKoboldBattleLoop
					(client posn: 52 84)
				else
					(client posn: 229 85)
				)
				(cond 
					(local11
						(client view: vKoboldReflect setCel: 1)
						(= cycles (Random 25 50))
						(= local11 0)
					)
					((and (Btst fFightingKobold) (not (Btst fFlag281))) (= cycles (Random 5 15)))
					(koboldCycles 
						(= cycles koboldCycles)
						(= koboldCycles 0)
					)
					(theCycles (= cycles theCycles))
					(else (= cycles (Random 25 50)))
				)
			)
			(1
				(client view: vKoboldSitting)
				(cond 
					((and (Btst fFightingKobold) (not (Btst fFlag281))) (client setScript: castRev))
					(theCycles (= theCycles 0) (client setScript: castTele))
					(else (client view: vKoboldMagic cycleSpeed: 0 setCycle: EndLoop self))
				)
			)
			(2
				(= clientLoop (client loop?))
				((Actor new:)
					ignoreActors:
					illegalBits: 0
					view: vKoboldMagic
					setLoop: 5
					setStep: 24 16
					posn:
						(+ (client x?) [local41 clientLoop])
						(+ (client y?) [local46 clientLoop])
						20
					init:
					setCycle: Forward
					setScript: (ballScript new:)
				)
				(= state -1)
				(client view: vKoboldSitting cycleSpeed: 3 setCycle: EndLoop self)
			)
			(3
				(client view: vKoboldDead setCel: 0)
				(= cycles 2)
			)
			(4
				(client setCel: 1)
				(= cycles 2)
			)
			(5
				(client view: vKoboldSitting cel: 0 setCel: -1 forceUpd:)
				(= state 0)
				(= cycles 5)
			)
		)
	)
)

(instance kobAsleep of KScript
	(properties)
	
	(method (doit)
		(if
			(and
				(< (ego distanceTo: client) 100)
				(or (!= egoGait MOVE_SNEAK) (< [egoStats STEALTH] 20))
			)
			(localproc_003c 7 15 57)
			(ChangeGait MOVE_WALK FALSE)
			(client setScript: kobWakeUp)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= koboldHealth MAX_HP_KOBOLD)
				((= newView (View new:))
					view: vKoboldSitting
					loop: 5
					cel: 0
					ignoreActors:
					posn: 229 85
					setPri: 6
					init:
				)
				(client
					posn: (newView x?) (- (newView y?) 17)
					setLoop: 6
					cycleSpeed: 1
				)
			)
		)
	)
)

(instance castRev of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst fKoboldCastingReversal))
					(Bset fKoboldCastingReversal)
					(localproc_003c 7 15 58)
				)
				(client
					view: vKoboldFighting
					setLoop: 1
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(= cycles 10)
			)
			(1
				(Bset fFlag281)
				(client setScript: kobAwake)
			)
		)
	)
)

(instance castTele of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					view: vKoboldFighting
					setLoop: 0
					cel: 0
					cycleSpeed: 0
					setCycle: EndLoop self
				)
				(if fightingKobold
					(curRoom setScript: (ScriptID 117 1))
				)
			)
			(1
				(= egoKoboldBattleLoop (- 1 egoKoboldBattleLoop))
				(if fightingKoboldStart
					(NormalEgo)
					(ego illegalBits: 0)
					((ScriptID 117 0) changeState: 1)
				)
				(if egoKoboldBattleLoop
					(client posn: 52 84)
				else
					(client posn: 229 85)
				)
				(client setCycle: BegLoop self)
			)
			(2 (client setScript: kobAwake))
		)
	)
)

(instance egoDies of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(client
					view: vEgoDaggerDefeated
					setLoop: egoKoboldBattleLoop
					cel: 0
					illegalBits: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(cond 
					((Btst fKoboldChestExploded)
						(EgoDead 15 59
							#title {Your plan seems to have backfired}
							#icon rKoboldCave 2 6
						)
					)
					(local17 
						(EgoDead 15 60 
							#title {Blast!} 
							#icon rKoboldCave 2 6
						)
					)
					(else 
						(EgoDead 15 61 
							#title {You've been deep-fried} 
							#icon vExplosion 0 1
						)
					)
				)
			)
		)
	)
)

(instance kobHurt of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst fKoboldDead)
					(self dispose:)
				else
					(if newView
						(newView dispose:)
						(= newView 0)
						(if egoKoboldBattleLoop
							(client posn: 52 84)
						else
							(client posn: 229 85)
						)
					)
					(client view: vKoboldDead setCycle: 0 setMotion: 0)
					(localproc_0138)
					(client setCel: 2)
					(= cycles 5)
				)
			)
			(1
				(client setCel: 3)
				(= cycles 3)
			)
			(2
				(client setCel: -1)
				(if (> koboldHealth 0)
					(= theCycles 3)
					(client setScript: kobAwake)
				else
					(client setScript: kobDies)
				)
			)
		)
	)
)

(instance kobDies of KScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fKoboldDead)
				(Bclr fKoboldAwake)
				(ego setScript: 0)
				(curRoom setScript: 0)
				(= koboldIllBits (& koboldIllBits (~ cYELLOW)))
				(client
					view: vKoboldDead
					setLoop: 5
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(if (not (Btst fGotKoboldKey))
					(kobKey
						show:
						illegalBits: 0
						ignoreActors:
						view: vKoboldDead
						posn: (kobold x?) (kobold y?)
						setLoop: 6
						cel: 0
						setPri: (if egoKoboldBattleLoop 5 else 1)
						cycleSpeed: 1
						setCycle: EndLoop
					)
				)
			)
			(1
				(= monsterNum (= monsterHealth 0))
				(if (or (== heroType FIGHTER) (== heroType MAGIC_USER))
					(SolvePuzzle f15BeatKobold 10)
				)
				(cSound number: 20 loop: -1 play:)
				(if fightingKobold
					(curRoom setScript: (ScriptID 117 1))
				else
					(HandsOn)
					(curRoom setScript: 0)
				)
				(client dispose:)
			)
		)
	)
)

(instance kobEnter of KScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (Btst fBeenIn15)) (localproc_003c 7 15 62))
				(ego setMotion: MoveTo 65 63 self)
			)
			(1
				(ego setMotion: MoveTo 100 77 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance egoFighting of KScript
	(properties)
	
	(method (dispose)
		(directionHandler delete: self)
		(ego setScript: 0 illegalBits: koboldIllBits)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego has: iSword)
					(= local13 0)
					(= local14 (= local15 1))
					(= local16 2)
				else
					(= local13 3)
					(= local14 (= local15 (= local16 4)))
				)
				(= fightingKobold 1)
				(directionHandler addToFront: self)
				(self cue:)
			)
			(1 (KoboldFight 1) (= state 0))
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(cond 
			((super handleEvent: event))
			(
				(and
					(== (event type?) direction)
					(not (ego script?))
				)
				(HandsOff)
				(switch (event message?)
					(dirN
						(ego setScript: (ScriptID 111 local13) self 0)
					)
					(dirW
						(ego setScript: (ScriptID 111 local14) self 1)
					)
					(dirE
						(ego setScript: (ScriptID 111 local15) self 2)
					)
					(dirS
						(ego setScript: (ScriptID 111 local16) self 3)
					)
					(else 
						(HandsOn)
						(User canControl: FALSE)
					)
				)
			)
		)
	)
)
