;;; Sierra Script 1.0 - (do not remove this comment)
(script# 218)
(include game.sh)
(use Main)
(use Skilled)
(use Invent)

(public
	fighter 0
)

(class Fighter of Skilled
	(properties
		canFight 0
		fighterView vEgoFightWithSword
		endFight 0
		baseX 187
		baseY 150
	)
	
	(method (init)
	(= strength 	[egoStats STR])
		(= intell 		[egoStats INT])
		(= agil 		[egoStats AGIL])
		(= vit 			[egoStats VIT])
		(= luck 		[egoStats LUCK])
		(= weap 		[egoStats WEAPON])
		(= parry 		[egoStats PARRY])
		(= dodge 		[egoStats DODGE])
		(= magic 		[egoStats MAGIC])
		(= stamina 		[egoStats STAMINA])
		(= health 		[egoStats HEALTH])
		(= mana 		[egoStats MANA])
		(super init: &rest)
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
	
	(method (getTired amount whoCares)
		(if (<= (-= stamina amount) 0)
			(= stamina 0)
			(self endFight: TRUE)
			(self setScript: whoCares)
		)
		(= [egoStats STAMINA] stamina)
	)
	
	(method (drawWeapons)
		(= baseX (opponent warriorX?))
		(= baseY (opponent warriorY?))
		(if (ego has: iShield)
			(= shieldValue 10)
		)
		(= armorEnc (/ (WtCarried) 2))
		(= armorValue 0)
		(cond 
			((ego has: iChainmail)
				(= armorValue CHAIN_VALUE)
			)
			((ego has: iLeather)
				(= armorValue LEATHER_VALUE)
			)
		)
		(self weapValue: SWORD_VALUE)
		(self
			ignoreActors: TRUE
			view: fighterView
			posn: baseX baseY
			stopUpd:
		)
	)
	
	(method (getHit)
		(if fightLeft
			(self x: (+ (self x?) 3))
			((self opponent?) x: (+ ((self opponent?) x?) 3))
		else
			(self x: (- (self x?) 3))
			((self opponent?) x: (- ((self opponent?) x?) 3))
		)
	)
	
	(method (gotBeat whoCares)
		(self endFight: TRUE)
		(self setScript: whoCares)
	)
)

(instance fighter of Fighter)
