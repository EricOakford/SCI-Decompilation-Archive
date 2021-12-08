;;; Sierra Script 1.0 - (do not remove this comment)
(script# 550)
(include game.sh) (include "550.shm") (include combat.sh)
(use Main)
(use GloryControls)
(use Print)
(use IconBar)
(use LoadMany)
(use Timer)
(use Window)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	combatRm 0
	WarriorObj 1
	combatSpell 2
	monsterHPStat 3
)

(local
	wasInMainGame
	theGameTime
)
(procedure (StopCast &tmp node nextNode obj)
	(= node (FirstNode (cast elements?)))
	(while node
		(= nextNode (NextNode node))
		(if (not (IsObject (= obj (NodeValue node))))
			(return)
		)
		(if (obj respondsTo: #setCycle)
			(obj setCycle: 0)
			(if (obj respondsTo: #setMotion) (obj setMotion: 0))
		)
		(= node nextNode)
	)
)

(procedure (EndBattle)
	(HandsOff)
	(if (<= [egoStats HEALTH] 0)
		(= battleResult battleEGOLOST)
	)
	(StopCast)
	(if (spellTimer seconds?)
		(spellTimer dispose:)
	)
	(if (stamTimer seconds?)
		(stamTimer dispose:)
	)
	(if (duckTimer seconds?)
		(duckTimer dispose:)
	)
	(SetCursor -2)
	(combatControls
		state: (& (combatControls state?) (~ IB_ACTIVE))
	)
	(theIconBar enable:)
	(if (== prevRoomNum 549)
		(curRoom newRoom: 840)
	else
		(if wasInMainGame
			(Bset fInMainGame)
		)
		(curRoom newRoom: prevRoomNum)
	)
)

(class CombatIcon of IconItem
	(properties
		ducking 0
		calledBy 0
		autoDodging 0
	)
	
	(method (highlight tOrF)
		(if (or (not (& signal IB_ACTIVE)) (& signal DISABLED))
			(return)
		)
		(if tOrF
			(DrawCel view loop 2 nsLeft nsTop)
		else
			(DrawCel view loop 0 nsLeft nsTop)
		)
	)
	
	(method (onMe obj)
		(return
			(if (not (& signal DISABLED))
				(if
					(and
						(>= (obj x?) nsLeft)
						(>= (obj y?) nsTop)
						(<= (obj x?) nsRight)
						(<= (obj y?) nsBottom)
						(not (IsItSkip view loop cel (- (obj y?) nsTop) (- (obj x?) nsLeft)))
					)
					(if (not cursor)
						(= cursor 1)
						(self highlight: 1)
					)
					(return TRUE)
				else
					(if cursor
						(= cursor 0)
						(self highlight: 0)
					)
					(return FALSE)
				)
			else
				(return FALSE)
			)
		)
	)
	
	(method (cue)
		(if
			(and
				(or
					(== (theWarrior view?) 26)
					(== (theWarrior view?) 27)
					(== (theWarrior view?) 555)
				)
				(not ducking)
			)
			(= ducking TRUE)
			(duckTimer setTicks: 60 self)
		else
			(if
				(and
					(theWarrior cel?)
					(== (theWarrior cel?) (theWarrior lastCel:))
					(not ducking)
				)
				(egoNoise number: 938 play:)
				(theMonster getHurt:)
				(splotch show: setCycle: EndLoop self)
			)
			(= ducking 0)
			(theWarrior normalize:)
		)
	)
	
	(method (warriorCast spellNum whoCares &tmp toCall)
		(cond 
			((< [egoStats MANA] [spellCost (- spellNum OPEN)]) (return))
			((ego castSpell: spellNum)
				(= toCall whoCares)
				(egoMPStat update:)
				(theWarrior cel: 0 setCycle: EndLoop toCall)
			)
		)
	)
	
	(method (tryAttack whoCares bonus &tmp toCall toHit theLevel theLoop)
		(ego trySkill: WEAPON 200)
		(egoSPStat update:)
		(egoHPStat update:)
		(= toCall 0)
		(if argc
			(= toCall whoCares)
		)
		(cond 
			(
				(>
					(= toHit
						(+
							(-
								(self attackLevel: bonus)
								(theMonster defenseLevel: 0)
							)
							50
						)
					)
					80
				)
				(= toHit 80)
			)
			((< toHit 20)
				(= toHit 20)
			)
		)
		(= theLevel (- toHit (Random 1 100)))
		(theWarrior cel: 0)
		(if (or (== calledBy 2) (== calledBy 0))
			(= toCall CombatIcon)
		)
		(if (> theLevel (= calledBy 0))
			(theWarrior setCycle: EndLoop toCall)
		else
			(if (< (= theLoop (- (theWarrior lastCel:) 1)) 1)
				(= theLoop 1)
			)
			(theWarrior setCycle: CycleTo theLoop 1 toCall)
		)
	)
	
	(method (attackLevel &tmp theLevel)
		(= theLevel
			(/
				(+
					(* [egoStats WEAPON] 8)
					(* [egoStats AGIL] 4)
					(* [egoStats STR] 2)
					[egoStats INT]
					[egoStats LUCK]
				)
				16
			)
		)
		(if (< [egoStats STAMINA] 100)
			(-= theLevel (/ (- 100 [egoStats STAMINA]) 5))
		)
		(return theLevel)
	)
	
	(method (defend theTheCombatIcon &tmp theCombatIcon)
		(= theCombatIcon 0)
		(if argc
			(if
				(or
					(== (= theCombatIcon theTheCombatIcon) iconC)
					(== combatView 24)
				)
				(ego trySkill: DODGE 200)
			else
				(ego trySkill: PARRY 200)
			)
		)
		(egoSPStat update:)
		(egoHPStat update:)
		(if (or (== calledBy 2) (== calledBy 0) autoDodging)
			(= autoDodging 0)
			(= theCombatIcon CombatIcon)
		)
		(= calledBy 0)
		(theWarrior cel: 0 setCycle: CycleTo 3 1 theCombatIcon)
	)
)

(class WarriorObj of Prop
	(properties
		x 100
		y 149
		z 0
		priority 12
		signal (| ignrAct fixPriOn)
		cycleSpeed 0
		status 0
		weapValue 0
	)
	
	(method (cue)
		(switch status
			(0
				(if (cast contains: flames)
					(flames hide:)
				)
				(= status 1)
				(self
					view: defendView
					loop: 1
					cel: 0
					setCycle: CycleTo 1 1 self
				)
				(egoNoise number: 901 play:)
			)
			(1
				(= status 0)
				(self normalize:)
			)
		)
	)
	
	(method (getHurt damage &tmp totDamage)
		(theWarrior status: 0 cue:)
		(= totDamage (/ (+ [egoStats PARRY] [egoStats DODGE]) 6))
		(= totDamage (- damage (/ (* damage totDamage) 100)))
		(if (ego has: iShield)
			(-= totDamage (- 6 arcadeDifficulty))
		)
		(if (> (+= totDamage (Random 0 10)) 0)
			(-= [egoStats HEALTH] totDamage)
			(if (< [egoStats HEALTH] 1)
				(= [egoStats HEALTH] 0)
			)
			(egoHPStat update:)
		)
	)
	
	(method (normalize)
		(theWarrior view: combatView loop: 0 cel: 0 stopUpd:)
		(if (cast contains: flames)
			(flames show: setCycle: Forward)
		)
	)
	
	(method (autoDodge &tmp theCycles)
		(if (== arcadeDifficulty 3) (return))	;don't autododge in highest difficulty
		(if (== arcadeDifficulty 1)
			(= theCycles 5)
		else
			(= theCycles 10)
		)
		(if (< (Random 0 100) (/ [egoStats DODGE] theCycles))
			(iconC
				autoDodging: (if (== gCalledBy 0) 0 else 2)
				select:
			)
			(return)
		)
		(if (< (Random 0 100) (/ [egoStats PARRY] theCycles))
			(iconD
				autoDodging: (if (== gCalledBy 0) 0 else 2)
				select:
			)
		)
	)
)

(class MonsterProp of Prop
	(properties
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(= cycleSpeed (/ 24 arcadeDifficulty))
		(super init: &rest)
	)
	
	(method (cue)
		(self setCel: 0 stopUpd:)
	)
)

(class Monster of MonsterProp
	(properties
		roar 0
		primDamage 0
		secDamage 0
		tertDamage 0
		armorValue 0
		monHP 0
		spellHitX 0
		spellHitY 0
		zapped 0
		picture 0
		mustFight 0
	)
	
	(method (init)
		(= theMonster self)
		(super init: &rest)
	)
	
	(method (getHurt damage param2 param3 &tmp totDamage)
		(if (< argc 3)
			(if
				(<
					(= totDamage
						(-
							(+ (theWarrior weapValue?) (/ [egoStats STR] 10))
							(theMonster armorValue?)
						)
					)
					0
				)
				(= totDamage (/ (theWarrior weapValue?) 5))
			)
			(+= totDamage (Random 1 10))
			(if (> argc 1)
				(if (and (< 0 param2) (< param2 4))
					(/= totDamage param2)
				else
					(+= totDamage param2)
				)
			)
			(+= totDamage zapPower)
			(= zapPower 0)
		else
			(= totDamage param3)
		)
		(if (spellTimer seconds?)
			(spellTimer seconds: 0 client: 0)
			(theMonster restart:)
		)
		(if (< (-= monsterHealth totDamage) 0)
			(= monsterHealth 0)
		)
		(monsterHPStat update:)
	)
	
	(method (whimper)
		(if roar
			(globalSound number: roar play:)
		)
	)
	
	(method (defenseLevel)
		(return 280)
	)
	
	(method (spellHurt spellNum &tmp damage)
		(= damage 0)
		(switch spellNum
			(FLAMEDART
				(= damage (+ 10 (/ [egoStats FLAMEDART] 30)))
				(theMonster getHurt: 0 0 damage)
			)
			(FORCEBOLT
				(= damage (+ 8 (/ [egoStats FORCEBOLT] 30)))
				(theMonster getHurt: 0 0 damage)
			)
			(LIGHTNING
				(= damage (+ 12 (/ [egoStats LIGHTNING] 30)))
				(theMonster getHurt: 0 0 damage)
			)
			(DAZZLE
				(Palette PALIntensity 0 255 500)
				(Palette PALIntensity 0 255 100)
				(self setCycle: 0 setScript: 0)
				(spellTimer setReal: combatRm (/ [egoStats DAZZLE] 10))
			)
		)
	)
	
	(method (restart)
	)
)

(class StatusBar of Actor
	(properties
		view vStatusBar
		loop 1
		cel 0
		priority 1
		signal (| ignrAct fixedLoop fixPriOn)
		xStep 1
		statMax 0
		xMax 0
	)
	
	(method (init)
		(= xMax (self x?))
		(self x: (self calcVal:))
		(super init: &rest)
	)
	
	(method (calcVal scaledValue &tmp xVal)
		(= xVal (/ (* scaledValue 100) statMax))
		(= xVal (- 47 (/ (* xVal 47) 100)))
		(if (and (> scaledValue 1) (>= xVal 47))
			(= xVal 46)
		)
		(return (- xMax xVal))
	)
	
	(method (update statNum &tmp temp0)
		(self setMotion: MoveTo (self calcVal: statNum) (self y?) self)
	)
)

(instance combatRm of Room
	(method (init)
		(if (== prevRoomNum 100) (= prevRoomNum 400))
		(= wasInMainGame (Btst fInMainGame))
		(Bclr fInMainGame)
		(globalSound setLoop: 1)
		(soundFx setLoop: 1)
		(= battleResult 2)
		((= theWarrior WarriorObj) view: 999 init:)
		(if
			(not
				(OneOf prevRoomNum
					400 650 700 800 810
					820 830 549 851 852
					853
				)
			)
			((ScriptID
				(Print
					addText: N_MONSTER NULL C_TITLE 1 0 12
					addButton: 560 N_MONSTER NULL NULL 2 0 26
					addButton: 565 N_MONSTER NULL NULL 3 140 26
					;addButton: 570 N_MONSTER NULL NULL 4
					addButton: 575 N_MONSTER NULL NULL 5 0 40
					addButton: 580 N_MONSTER NULL NULL 6 140 40
					addButton: 585 N_MONSTER NULL NULL 7 0 54
					addButton: 590 N_MONSTER NULL NULL 8 140 54
					addButton: 595 N_MONSTER NULL NULL 9 0 68
					addButton: 845 N_MONSTER NULL NULL 10 140 68
					addButton: 855 N_MONSTER NULL NULL 11 0 82
					addButton: 860 N_MONSTER NULL NULL 12 140 82
					init:
				)
			)
				init:
			)
		else
			((ScriptID monsterNum) init:)
		)
		(Load RES_VIEW 553)
		(cond 
			((and (ego has: iSword) ((inventory at: iSword) state?))
				(theWarrior weapValue: FLAMING_SWORD_VALUE)
				(= gCalledBy 0)
				(= combatView 25)
				(= defendView 555)
				(LoadMany RES_VIEW 25 554 555)
				(flames init: setCycle: Forward)
			)
			((ego has: iSword)
				(theWarrior weapValue: FINE_SWORD_VALUE)
				(= gCalledBy 0)
				(= combatView 23)
				(= defendView 26)
				(LoadMany RES_VIEW 23 26)
			)
			(else
				(theWarrior weapValue: DAGGER_VALUE)
				(= gCalledBy 2)
				(= combatView 24)
				(= defendView 27)
				(LoadMany RES_VIEW 24 27 28 22 552)
				(LoadMany RES_SCRIPT 21)
				(combatSpell init: hide:)
			)
		)
		(theWarrior view: combatView)
		(theIconBar disable:)
		(splotch init: hide:)
		(HandsOn)
		(super init: &rest)
		(if (not monsterHealth)
			(= monsterHealth (theMonster monHP?))
		)
		(egoStatus init:)
		(monStatus init:)
		(egoHPStat
			statMax: (if (ego maxHealth:) (ego maxHealth:) else 100)
			init:
		)
		(egoSPStat
			statMax: (if (ego maxStamina:) (ego maxStamina:) else 100)
			init:
		)
		(egoMPStat
			statMax: (if (ego maxMana:) (ego maxMana:) else 100)
			init:
		)
		(monsterHPStat statMax: (theMonster monHP?) init:)
		(stamTimer setReal: stamTimer 5)
		(combatControls init: show: dispose:)
	)
	
	(method (dispose)
		(LoadMany FALSE 565 575 580 585 845 590 560 595 855 860)
		(UnLoad RES_VIEW 24)
		(UnLoad RES_VIEW 27)
		(UnLoad RES_VIEW 28)
		(UnLoad RES_VIEW 22)
		(UnLoad RES_VIEW 552)
		(UnLoad RES_VIEW 23)
		(UnLoad RES_VIEW 26)
		(UnLoad RES_VIEW 25)
		(UnLoad RES_VIEW 554)
		(UnLoad RES_VIEW 555)
		(UnLoad RES_VIEW 553)
		(super dispose: &rest)
	)
	
	(method (cue)
		(theMonster restart:)
	)
)

(instance combatControls of GloryControls
	(method (init)
		(super init: &rest)
		(= icon1 iconA)
		(= icon2 iconB)
		(= icon3 iconC)
		(= icon4 iconD)
		(= icon5 iconE)
		(= icon6 iconF)
		(= icon7 iconMiddle)
		(self
			add: iconA iconB iconC iconD iconE iconF iconMiddle
			eachElementDo: #cel 0
			eachElementDo: #cursor 999
			eachElementDo: #signal 129
		)
		(cond 
			((== gCalledBy 2)
				(if (== heroType MAGIC_USER)
					(= gCalledBy 1)
					(if (theMonster mustFight?)
						(combatControls disable: iconF)
					)
					(if (not [egoStats ZAP])
						(combatControls disable: iconE)
					)
					(if (not [egoStats LIGHTNING])
						(combatControls disable: iconA)
					)
					(if (not [egoStats DAZZLE])	;NOTE: This was originally CALM, but that's incorrect
						(combatControls disable: iconB)
					)
					(if (not [egoStats FLAMEDART])
						(combatControls disable: iconC)
					)
					(if (not [egoStats FORCEBOLT])
						(combatControls disable: iconD)
					)
					(combatControls
						eachElementDo: #view 552
						eachElementDo: #cel 0
					)
				else
					(self eachElementDo: #view 551)
					(if (not [egoStats MAGIC])
						(iconMiddle view: 550)
						(if (theMonster mustFight?)
							(self disable: iconMiddle)
						)
					)
				)
			)
			((theMonster mustFight?)
				(self disable: iconMiddle)
			)
		)
		(SetCursor 137 242 196 318)
		(theGame setCursor: theCursor TRUE 290 170)
	)
	
	(method (show)
		(= window combatWin)
		(User input: TRUE)
		(theGame setCursor: ARROW_CURSOR)
		(super show: &rest)
	)
	
	(method (dispatchEvent event &tmp [temp0 3])
		(if
			(and
				(!= (= gameTime (GetTime)) theGameTime)
				(> reversalTimer 0)
			)
			(-- reversalTimer)
			(= theGameTime gameTime)
		)
		(super dispatchEvent: event &rest)
		(sounds eachElementDo: #check)
		(timers eachElementDo: #doit)
		(Animate (cast elements?) TRUE)
		(if doMotionCue
			(= doMotionCue FALSE)
			(cast eachElementDo: #motionCue)
		)
	)
)

(instance combatWin of Window
	(properties
		top 125
		left 239
		bottom 189
		right 320
		type (| wCustom wNoSave wNoBorder)
	)
	
	(method (open)
		(super open: &rest)
		(DrawCel 550 0 0 0 0 -1)
	)
)

(instance iconA of CombatIcon
	(properties
		view 550
		loop 1
		nsLeft 2
		nsTop 2
		maskView 552
		maskLoop 8
		maskCel 3
	)
	
	(method (select)
		(= calledBy gCalledBy)
		(if (cast contains: flames)
			(flames hide:)
		)
		(theWarrior view: combatView)
		(switch calledBy
			(0
				(theWarrior loop: 1)
				(self tryAttack: self)
			)
			(2
				(theWarrior loop: 1)
				(self tryAttack: self)
			)
			(1
				(theWarrior view: 28 loop: 0)
				(self warriorCast: 32 self)
			)
		)
		(return TRUE)
	)
	
	(method (cue)
		(switch gCalledBy
			(1
				(egoNoise number: 11 play:)
				(combatSpell
					x: 123
					y: 87
					setLoop: 2
					show:
					setCycle: Forward
					approachDist: 32
					setStep: 6 6
					setMotion:
						MoveTo
						(theMonster spellHitX?)
						(theMonster spellHitY?)
						combatSpell
				)
			)
			(else  (super cue:))
		)
		(theWarrior normalize:)
	)
)

(instance iconB of CombatIcon
	(properties
		view 550
		loop 2
		nsLeft 50
		nsTop 2
		signal ignrAct
		maskView 552
		maskLoop 8
		maskCel 4
	)
	
	(method (select)
		(= calledBy gCalledBy)
		(theWarrior view: combatView)
		(if (cast contains: flames)
			(flames hide:)
			(theWarrior view: 554)
		)
		(switch calledBy
			(0
				(theWarrior loop: 2)
				(self tryAttack: self)
			)
			(2
				(theWarrior loop: 2)
				(self tryAttack: self)
			)
			(1
				(theWarrior view: 28 loop: 0)
				(self warriorCast: 22 self)
			)
		)
		(return TRUE)
	)
	
	(method (cue)
		(switch gCalledBy
			(1
				(egoNoise number: 942 play:)
				(combatSpell
					x: 123
					y: 87
					show:
					setLoop: 3
					approachDist: 22
					setStep: 6 6
					setCycle: EndLoop combatSpell
				)
			)
			(else  (super cue:))
		)
		(theWarrior normalize:)
	)
)

(instance iconC of CombatIcon
	(properties
		view 550
		loop 3
		nsLeft 2
		nsTop 34
		maskView 552
		maskLoop 8
		maskCel 5
	)
	
	(method (select &tmp theAutoDodging)
		(= calledBy gCalledBy)
		(if autoDodging
			(= theAutoDodging autoDodging)
		else
			(= theAutoDodging calledBy)
		)
		(if (cast contains: flames) (flames hide:))
		(theWarrior view: defendView)
		(switch theAutoDodging
			(0
				(theWarrior loop: 1)
				(self defend: self)
			)
			(2
				(theWarrior loop: 0)
				(self defend: self)
			)
			(1
				(theWarrior view: 28 loop: 0)
				(self warriorCast: 25 self)
			)
		)
		(return TRUE)
	)
	
	(method (cue)
		(if (== gCalledBy 1)
			(egoNoise number: 13 play:)
			(combatSpell
				x: 123
				y: 87
				setLoop: 0
				show:
				setCycle: Forward
				approachDist: 25
				setStep: 6 6
				setMotion:
					MoveTo
					(theMonster spellHitX?)
					(theMonster spellHitY?)
					combatSpell
			)
			(theWarrior normalize:)
		else
			(super cue:)
		)
	)
)

(instance iconD of CombatIcon
	(properties
		view 550
		loop 4
		nsLeft 52
		nsTop 34
		maskView 552
		maskLoop 8
		maskCel 6
	)
	
	(method (select &tmp theAutoDodging)
		(= calledBy gCalledBy)
		(if autoDodging
			(= theAutoDodging autoDodging)
		else
			(= theAutoDodging calledBy)
		)
		(if (cast contains: flames) (flames hide:))
		(theWarrior view: defendView)
		(switch theAutoDodging
			(0
				(theWarrior loop: 0)
				(self defend: self)
			)
			(2
				(theWarrior loop: 1)
				(self defend: self)
			)
			(1
				(theWarrior view: 28 loop: 0)
				(self warriorCast: FORCEBOLT self)
			)
		)
		(return 1)
	)
	
	(method (cue)
		(if (== gCalledBy 1)
			(egoNoise number: 943 play:)
			(combatSpell
				x: 123
				y: 87
				setLoop: 1
				show:
				setCycle: Forward
				approachDist: 27
				setStep: 6 6
				setMotion:
					MoveTo
					(theMonster spellHitX?)
					(theMonster spellHitY?)
					combatSpell
			)
			(theWarrior normalize:)
		else
			(super cue:)
		)
	)
)

(instance iconE of CombatIcon
	(properties
		view 550
		loop 5
		nsLeft 3
		nsTop 12
		maskView 552
		maskLoop 8
		maskCel 1
	)
	
	(method (select)
		(= calledBy gCalledBy)
		(theWarrior view: defendView)
		(switch calledBy
			(0 0)
			(2 0)
			(1
				(if (cast contains: flames) (flames hide:))
				(theWarrior view: 28 loop: 0)
				(self warriorCast: ZAP self)
			)
		)
		(return 1)
	)
	
	(method (cue)
		(switch gCalledBy
			(1
				(egoNoise number: 900 play:)
				(= zapPower (+ 5 (/ [egoStats 23] 10)))
			)
			(else  (super cue:))
		)
		(theWarrior normalize:)
	)
)

(instance iconF of CombatIcon
	(properties
		view 550
		loop 6
		nsLeft 58
		nsTop 11
		maskView 552
		maskLoop 8
		maskCel 2
	)
	
	(method (select)
		(if (& signal DISABLED) (return 0))
		(= calledBy gCalledBy)
		(theWarrior view: defendView)
		(switch calledBy
			(0 0)
			(2 0)
			(1 (EndBattle))
		)
		(return TRUE)
	)
)

(instance iconMiddle of CombatIcon
	(properties
		view 550
		loop 7
		nsLeft 23
		nsTop 2
		maskView 550
		maskLoop 8
	)
	
	(method (select)
		(if (& signal DISABLED) (return FALSE))
		(= calledBy gCalledBy)
		(switch calledBy
			(0 (EndBattle))
			(2
				(if (not [egoStats MAGIC])
					(EndBattle)
				else
					(= gCalledBy 1)
					(if (theMonster mustFight?)
						(combatControls disable: iconF)
					)
					(if (not [egoStats ZAP])
						(combatControls disable: iconE)
					)
					(if (not [egoStats LIGHTNING])
						(combatControls disable: iconA)
					)
					(if (not [egoStats DAZZLE])
						(combatControls disable: iconB)
					)
					(if (not [egoStats FLAMEDART])
						(combatControls disable: iconC)
					)
					(if (not [egoStats FORCEBOLT])
						(combatControls disable: iconD)
					)
					(combatControls
						eachElementDo: #view 552
						eachElementDo: #cel 0
						eachElementDo: #show
					)
				)
			)
			(1
				(= gCalledBy 2)
				(combatControls
					enable: iconA iconB iconC iconD iconE iconF
					eachElementDo: #view 551
					eachElementDo: #cel 0
					eachElementDo: #show
				)
			)
		)
		(Animate (cast elements?) TRUE)
		(return TRUE)
	)
)

(instance combatSpell of Actor
	(properties
		x 123
		y 87
		view 22
		priority 14
		signal (| ignrAct fixPriOn)
		moveSpeed 0
	)
	
	(method (cue)
		(if (< loop 3)
			(self loop: (+ loop 3) setCel: 0 setCycle: EndLoop self)
			(egoNoise number: 930 play:)
			(theMonster spellHurt: approachDist)
		else
			(if (== approachDist 22)
				(theMonster spellHurt: approachDist)
			)
			(self hide: setCycle: 0)
		)
	)
)

(instance flames of Prop
	(properties
		x 100
		y 149
		view 554
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance egoStatus of View
	(properties
		x 30
		y 15
		view 553
		cel 1
		priority 3
		signal (| ignrAct fixPriOn)
	)
)

(instance monStatus of View
	(properties
		x 260
		y 15
		view 553
		priority 3
		signal (| ignrAct fixPriOn)
	)
)

(instance egoHPStat of StatusBar
	(properties
		x 30
		y 15
	)
	
	(method (cue)
		(if (< [egoStats HEALTH] 1)
			(= battleResult battleEGOLOST)
			(EndBattle)
		)
	)
	
	(method (calcVal)
		(super calcVal: [egoStats HEALTH])
	)
	
	(method (update)
		(super update: [egoStats HEALTH])
	)
)

(instance egoSPStat of StatusBar
	(properties
		x 30
		y 26
	)
	
	(method (calcVal)
		(super calcVal: [egoStats STAMINA])
	)
	
	(method (update)
		(super update: [egoStats STAMINA])
	)
)

(instance egoMPStat of StatusBar
	(properties
		x 30
		y 37
	)
	
	(method (calcVal)
		(super calcVal: [egoStats MANA])
	)
	
	(method (update)
		(super update: [egoStats MANA])
	)
)

(instance monsterHPStat of StatusBar
	(properties
		x 260
		y 15
	)
	
	(method (cue)
		(cond 
			((== battleResult -1)
				(theWarrior setScript: sDemonEgoDies)
			)
			((< monsterHealth 1)
				(= battleResult battleEGOWON)
				(EndBattle)
			)
		)
	)
	
	(method (calcVal)
		(super calcVal: monsterHealth)
	)
	
	(method (update)
		(super update: monsterHealth)
	)
)

(instance sDemonEgoDies of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(egoStatus hide:)
				(monStatus hide:)
				(egoHPStat hide:)
				(egoSPStat hide:)
				(egoMPStat hide:)
				(monsterHPStat hide:)
				(combatControls hide:)
				(= cycles 3)
			)
			(1
				(messager say: 1 6 1 0 0 840)
				(= cycles 3)
			)
			(2
				(= battleResult battleEGOWON)
				(DisposeScript 40)
				(EndBattle)
			)
		)
	)
)

(instance splotch of Prop
	(properties
		view 553
		loop 2
		signal ignrAct
	)
	
	(method (show)
		(self
			setPri: (+ (theWarrior priority?) 1)
			x: (+
				(theWarrior x?)
				(if (== (theWarrior loop?) 1) 48 else 67)
			)
			y: (-
				(theWarrior y?)
				(if (== (theWarrior loop?) 1) 66 else 46)
			)
		)
		(super show: &rest)
	)
	
	(method (cue)
		(self hide: x: -50)
	)
)

(instance duckTimer of Timer)

(instance spellTimer of Timer)

(instance stamTimer of Timer
	(method (cue)
		(ego useStamina: -5)
		(egoSPStat update:)
		(self setReal: self 2)
	)
)

(instance egoNoise of Sound)
