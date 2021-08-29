;;; Sierra Script 1.0 - (do not remove this comment)
(script# SKILLED) ;212
(include game.sh)
(use Main)
(use Actor)


(class Skilled of Actor
	(properties
		;stats
		strength 0
		intell 0
		agil 0
		vit 0
		luck 0
		weap 0
		parry 0
		dodge 0
		magic 0
		
		;derived stats
		stamina 0
		health 0
		mana 0
		
		;other values
		armorValue 0
		armorEnc 0
		shieldValue 0
		weapValue 0
		canFight 0
		action ActNone
		opponent 0
		fightLeft 0
	)
	
	(method (init)
		;get ready to fight
		(= canFight (= action 0))
		(super init: &rest)
	)
	
	(method (attackLevel &tmp theLevel)
		(= theLevel
			(/
				(+ (* weap 8) (* agil 4) strength strength intell luck)
				16
			)
		)
		;"When Stamina points are low, the character is weak. He will
		; fight less effectively, and may be injured more easily." -Manual
		(if (<= stamina 30)
			(-= theLevel 10)
			(if (<= stamina 10)
				(-= theLevel 20)
			)
		)
		;if ego hasn't slept for a while, increase penalty
		(-= theLevel (* lostSleep 10))
		(if (== action ActThrust)
			(+= theLevel 10)
		)
		(return theLevel)
	)
	
	(method (defenseLevel &tmp theLevel parryBonus dodgeBonus)
		(= theLevel
			(- (/ (+ (* agil 5) intell intell luck) 8) armorEnc)
		)
		(if (<= stamina 30)
			(-= theLevel 5)
			(if (<= stamina 10)
				(-= theLevel 10)
			)
		)
		(-= theLevel (* lostSleep 10))
		(= parryBonus (= dodgeBonus 0))
		(if parry
			(= parryBonus (+ parry shieldValue (/ agil 10)))
		)
		(if dodge
			(= dodgeBonus (+ dodge (/ agil 5)))
		)
		(switch action
			(ActThrust
				(-= theLevel 5)
			)
			(ActParryUp
				(+= theLevel parryBonus)
			)
			(ActParryDown
				(+= theLevel parryBonus)
			)
			(ActDodgeLeft
				(+= theLevel dodgeBonus)
			)
			(ActDodgeRight
				(+= theLevel dodgeBonus)
			)
		)
		(return theLevel)
	)
	
	(method (tryAttack monster &tmp toHit)
		(cond 
			(
				(>
					(= toHit
						(+ (- (self attackLevel:) (monster defenseLevel:)) 50)
					)
					95
				)
				(= toHit 95)
			)
			((< toHit 5)
				(= toHit 5)
			)
		)
		(return
			(if (>= toHit (Rand100))
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
	
	(method (doDamage monster addIt &tmp damage)
		(= damage
			(+
				(- (+ weapValue (/ strength 10)) (monster armorValue?))
				(Random 1 6)
			)
		)
		(if (>= argc 2)
			(+= damage addIt)
		)
		(if (< damage 0)
			(= damage 0)
		else
			(monster getHurt: damage)
		)
		(return damage)
	)
	
	(method (getHurt damage)
		(if (< (-= health damage) 0)
			(= health 0)
		)
		(if (> health (self calcHealth:))
			(= health
				(self calcHealth:)
			)
		)
		(if (== health 0)
			(self die:)
		)
	)
	
	(method (getTired amount)
		(if (< (-= stamina amount) 0)
			(self getHurt: (/ (- 0 stamina) 2))
			(= stamina 0)
		)
	)
	
	(method (die)
		(opponent canFight: FALSE)
		(self setScript: 0)
		(= canFight FALSE)
		(= action ActDie)
	)
	
	(method (calcHealth &tmp tmpHealth)
		(return (+ (= tmpHealth (/ (+ strength vit vit) 3)) tmpHealth))
	)
	
	(method (calcStamina)
		(return (* (+ agil vit) 2))
	)
	
	(method (calcMana)
		(return
			(if magic
				(return (/ (+ intell magic magic) 3))
			else
				(return 0)
			)
		)
	)
)
