;;; Sierra Script 1.0 - (do not remove this comment)
(script# WARRIOR)
(include game.sh)
(use Main)
(use StatusBar)
(use Skilled)
(use Procs)

(public
	warrior 0
)

(class Warrior of SkilledActor
	(properties
		yStep 10
		view 109
		heroTitle 0
		egoHP 0
		egoSP 0
		egoMP 0
		usingSword 0
		noWeapon 0
		baseX 0
		baseY 0
	)
	
	(method (init)
		(= strength [egoStats STR])
		(= intell [egoStats INT])
		(= agil [egoStats AGIL])
		(= vit [egoStats VIT])
		(= luck [egoStats LUCK])
		(= weap [egoStats WEAPON])
		(= parry [egoStats PARRY])
		(= dodge [egoStats DODGE])
		(= magic [egoStats MAGIC])
		(self ignoreActors:)
		(= stamina [egoStats STAMINA])
		(= health [egoStats HEALTH])
		(= mana [egoStats MANA])
		(super init: &rest)
	)
	
	(method (dispose)
		(Bclr fDiedInBattle)
		(theGame setCursor: normalCursor TRUE)
		(Bclr fFlag284)
		(if egoMP
			(egoMP dispose:) 
			(= egoMP 0)
		)
		(egoSP dispose:)
		(egoHP dispose:)
		(= heroTitle (= egoHP (= egoSP 0)))
		(super dispose:)
	)
	
	(method (getHurt damage)
		(TakeDamage damage)
		(super getHurt: damage)
		(if (> stamina 0)
			(if (script script?) ((script script?) dispose:))
			((ScriptID 215 1) dispose:)
			((ScriptID 215 3) dispose:)
			((ScriptID 215 4) dispose:)
			(script setScript: (ScriptID 155 0))
		else
			(self setEgoHP: health)
		)
	)
	
	(method (getTired amount)
		(UseStamina amount)
		(super getTired: amount)
		(self setEgoSP: stamina)
	)
	
	(method (die)
		(Bset fDiedInBattle)
	)
	
	(method (startCombat param1)
		(self setScript: (ScriptID param1 0))
	)
	
	(method (drawStatus)
		((= egoHP (StatusBar new:))
			x: 34
			y: 28
			titleCel: 0
			priority: 1
			max: (MaxHealth)
			value: health
			init:
		)
		((= egoSP (StatusBar new:))
			x: 34
			y: 39
			titleCel: 1
			priority: 1
			max: (MaxStamina)
			value: [egoStats STAMINA]
			init:
		)
		(if [egoStats MAGIC]
			((= egoMP (StatusBar new:))
				x: 34
				y: 50
				titleCel: 2
				priority: 1
				max: (MaxMana)
				value: [egoStats MANA]
				init:
			)
		)
	)
	
	(method (drawWeapons &tmp theArmorEnc)
		(= baseX (opponent warriorX?))
		(= baseY (opponent warriorY?))
		(if (ego has: iShield)
			(= shieldValue 10)
		else
			(= shieldValue 0)
		)
		(= armorEnc (/ (= theArmorEnc (WtCarried)) 2))
		(if (> theArmorEnc (MaxLoad))
			(= armorEnc theArmorEnc)
		)
		(= armorValue 0)
		(cond 
			((ego has: iChainmail)
				(= armorValue CHAIN_VALUE)
			)
			((ego has: iLeather)
				(= armorValue LEATHER_VALUE)
			)
		)
		(cond 
			((ego has: iSword)
				(self view: 109 weapValue: SWORD_VALUE)
			)
			((ego has: iDagger)
				(switch monsterNum
					(vCheetaur
						1
					)
					(vMantray
						1
					)
					(vBear
						(= baseY (+ baseY 8))
					)
					(vSaurus
						(= baseX (+ baseX 8))
						(= baseY (+ baseY 5))
					)
					(vTroll
						(= baseX (+ baseX 12))
					)
					(vOgre
						(= baseY (+ baseY 4))
						(= baseX (+ baseX 8))
					)
					(vMinotaur
						(= baseX (+ baseX 7))
					)
					(vGoblin
						(= baseX (+ baseX 6))
						(= baseY (- baseY 7))
					)
					(vBrigand
						(= baseX (+ baseX 12))
						(= baseY (+ baseY 2))
					)
					(vDragon
						(= baseX (+ baseX 8))
						(= baseY (+ baseY 10))
					)
					(else 
						(= baseX (+ baseX 20))
						(= baseY (+ baseY 10))
					)
				)
				(self view: 102 weapValue: DAGGER_VALUE)
			)
			(else
				(= noWeapon TRUE)
				(self view: 117 loop: 0 cel: 0 forceUpd:)
			)
		)
		(self
			ignoreActors: TRUE
			cel: 0
			setPri: 13
			posn: baseX baseY
			stopUpd:
		)
	)
	
	(method (setEgoHP newValue)
		(= [egoStats HEALTH] newValue)
		(if egoHP (egoHP value: newValue draw:))
	)
	
	(method (setEgoMP newValue)
		(= mana newValue)
		(if egoMP (egoMP value: newValue draw:))
	)
	
	(method (setEgoSP newValue)
		(= [egoStats STAMINA] newValue)
		(if egoSP (egoSP value: newValue draw:))
	)
)

(instance warrior of Warrior)
