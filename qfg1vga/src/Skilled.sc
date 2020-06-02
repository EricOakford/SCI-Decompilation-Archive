;;; Sierra Script 1.0 - (do not remove this comment)
(script# SKILLED)
(include game.sh)
(use Main)
(use Procs)
(use Actor)


(class SkilledActor of Actor
	(properties	
		strength 0
		intell 0
		agil 0
		vit 0
		luck 0
		weap 0
		parry 0
		dodge 0
		magic 0
		stamina 0
		health 0
		mana 0
		armorValue 0
		armorEnc 0
		shieldValue 0
		weapValue 0
		canFight 0
		action 0
		opponent 0
		fightLeft 0
	)
	
	(method (init)
		(= canFight (= action 0))
		(super init: &rest)
	)
	
	(method (attackLevel amount &tmp theLevel)
		(= theLevel
			(/
				(+ (* weap 8) (* agil 4) strength strength intell luck)
				16
			)
		)
		(if (<= stamina 30)
			(= theLevel (- theLevel 10))
			(if (<= stamina 10) (= theLevel (- theLevel 20)))
		)
		(= theLevel (- theLevel (* lostSleep 10)))
		(if (== action 1) (= theLevel (+ theLevel 10)))
		(= theLevel (- theLevel amount))
	)
	
	(method (defenseLevel &tmp theLevel parryBonus dodgeBonus)
		(= theLevel
			(- (/ (+ (* agil 5) intell intell luck) 8) armorEnc)
		)
		(if (<= stamina 30)
			(= theLevel (- theLevel 5))
			(if (<= stamina 10) (= theLevel (- theLevel 10)))
		)
		(= theLevel (- theLevel (* lostSleep 10)))
		(= parryBonus (= dodgeBonus 0))
		(if parry (= parryBonus (+ parry shieldValue (/ agil 10))))
		(if dodge (= dodgeBonus (+ dodge (/ agil 5))))
		(switch action
			(1 (= theLevel (- theLevel 5)))
			(3 (= theLevel (+ theLevel parryBonus)))
			(4 (= theLevel (+ theLevel parryBonus)))
			(5 (= theLevel (+ theLevel dodgeBonus)))
			(6 (= theLevel (+ theLevel dodgeBonus)))
		)
		(return theLevel)
	)
	
	(method (tryAttack monster addIt &tmp toHit)
		(if (< argc 2) (= addIt 0))
		(cond 
			(
				(>
					(= toHit
						(+
							(- (self attackLevel: addIt) (monster defenseLevel:))
							50
						)
					)
					95
				)
				(= toHit 95)
			)
			((< toHit 5) (= toHit 5))
		)
		(return
			(if (>= toHit (Rand100))
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
	
	(method (doDamage monster addIt &tmp theLevel)
		(= theLevel
			(+
				(- (+ weapValue (/ strength 10)) (monster armorValue?))
				(Random 1 6)
			)
		)
		(if (>= argc 2) (= theLevel (+ theLevel addIt)))
		(if (< theLevel 0)
			(= theLevel 0)
		else
			(monster getHurt: theLevel)
		)
		(return theLevel)
	)
	
	(method (getHurt damage)
		(if (< (= health (- health damage)) 0) (= health 0))
		(if (> health (self calcHealth:))
			(= health (self calcHealth:))
		)
	)
	
	(method (getTired amount)
		(if (< (= stamina (- stamina amount)) 0)
			(self getHurt: (/ (- 0 stamina) 2))
			(= stamina 0)
		)
	)
	
	(method (die)
		(opponent canFight: FALSE)
		(self setScript: 0)
		(= canFight FALSE)
		(= action aaDIE)
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
