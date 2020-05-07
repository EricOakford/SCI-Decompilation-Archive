;;; Sierra Script 1.0 - (do not remove this comment)
(script# WARRIOR) ;213
(include game.sh)
(use Main)
(use StatusBar)
(use Skilled)
(use Invent)
(use Actor)

(public
	warrior 0
)

(class Warrior of Skilled
	(properties
		xStep 16
		heroTitle 0
		egoHP 0
		egoSP 0
		egoMP 0
		weaponView 0
		egosBack 0
		egoShield 0
		egoHand 0
		usingSword 0
		noWeapon 0
		baseX 190
		baseY 190
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
		(= stamina [egoStats STAMINA])
		(= health [egoStats HEALTH])
		(= mana [egoStats MANA])
		(super init: &rest)
	)
	
	(method (dispose)
		(Bclr HERO_KILLED_IN_BATTLE)
		(if egoMP (egoMP dispose:) (= egoMP 0))
		(if heroTitle
			(egoSP dispose:)
			(egoHP dispose:)
			(Display 213 0 p_restore heroTitle)
			;Hero Status
			(= heroTitle (= egoHP (= egoSP 0)))
		)
		(super dispose:)
	)
	
	(method (getHurt damage)
		(TakeDamage damage)
		(super getHurt: damage)
		(self setEgoHP: health)
		(cond 
			((or (Btst HERO_KILLED_IN_BATTLE) (<= health 0))
				(Animate (cast elements?) FALSE)
				(EgoDead 213 2
					#title {What a monster!}
					#icon vEgoDefeatedMagic 0 9)
					;It was a tough battle, and you lost. Never fear!
					;All you have to do is restore your game, and...\nWhat do you mean, "Restore WHAT game?"
			)
			((not (script script?)) (script setScript: (ScriptID 155 0)))
		)
	)
	
	(method (getTired amount)
		(UseStamina amount)
		(super getTired: amount)
		(self setEgoSP: stamina)
	)
	
	(method (die)
		(Bset HERO_KILLED_IN_BATTLE)
	)
	
	(method (startCombat param1)
		(self setScript: (ScriptID param1 0))
	)
	
	(method (drawStatus)
		(= heroTitle
			(Display
				213 1
				p_width 80
				p_at 13 13
				p_mode teJustLeft
				p_font 300
				p_color combatColor
				p_save
			)
		)
		((= egoHP (StatusBar new:))
			x: 25
			y: 33
			titleCel: 0
			priority: 1
			max: (MaxHealth)
			value: health
			init:
		)
		((= egoSP (StatusBar new:))
			x: 25
			y: 47
			titleCel: 1
			priority: 1
			max: (MaxStamina)
			value: [egoStats STAMINA]
			init:
		)
		(if [egoStats MAGIC]
			((= egoMP (StatusBar new:))
				x: 25
				y: 61
				titleCel: 2
				priority: 1
				max: (MaxMana)
				value: [egoStats MANA]
				init:
			)
		)
	)
	
	(method (drawWeapons &tmp warLoad)
		(= baseX (opponent warriorX?))
		(= baseY (opponent warriorY?))
		(= shieldValue (= egoShield 0))
		(if (ego has: iShield)
			(= shieldValue 10)
			((= egoShield aShield)
				view: vEgoFightArmSword
				setLoop: 1
				setCel: 0
				setPri: 14
				x: (- baseX 74)
				y: baseY
				ignoreActors: TRUE
				init:
				stopUpd:
			)
		else
			(= shieldValue 0)
			((= egoHand aHand)
				view: vEgoFightArmDagger
				setLoop: 1
				setCel: 0
				setPri: 14
				x: (- baseX 73)
				y: baseY
				ignoreActors: TRUE
				init:
				stopUpd:
			)
		)
		(= armorEnc (/ (= warLoad (WtCarried)) 2))
		(if (> warLoad (MaxLoad))
			(= armorEnc warLoad)
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
				(= weaponView vEgoFightArmSword)
				(self weapValue: SWORD_VALUE)
			)
			((ego has: iDagger)
				(= weaponView vEgoFightArmDagger)
				(self weapValue: DAGGER_VALUE)
			)
			(else
				(= weaponView vEgoFightArmEmpty)
				(= noWeapon TRUE)
			)
		)
		((= egosBack closeupEgo)
			view: vEgoFightHead
			setLoop: 0
			setCel: 0
			setPri: 15
			x: (- baseX 41)
			y: baseY
			ignoreActors: TRUE
			init:
			stopUpd:
		)
		(self
			illegalBits: 0
			ignoreActors: TRUE
			view: weaponView
			setLoop: (if noWeapon 0 else 2)
			cel: 0
			setPri: 11
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

(instance warrior of Warrior
	(properties)
)

(instance aShield of View
	(properties)
)

(instance aHand of Prop
	(properties)
)

(instance closeupEgo of View
	(properties)
)
