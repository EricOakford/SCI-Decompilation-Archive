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
	
	(method (tryAttack param1 &tmp temp0)
		(cond 
			(
				(>
					(= temp0
						(+ (- (self attackLevel:) (param1 defenseLevel:)) 50)
					)
					95
				)
				(= temp0 95)
			)
			((< temp0 5) (= temp0 5))
		)
		(return (if (>= temp0 (Rand100)) (return TRUE) else (return FALSE)))
	)
	
	(method (getTired param1 param2)
		(if (<= (= stamina (- stamina param1)) 0)
			(= stamina 0)
			(self endFight: TRUE)
			(self setScript: param2)
		)
		(= [egoStats STAMINA] stamina)
	)
	
	(method (drawWeapons)
		(= baseX (opponent warriorX?))
		(= baseY (opponent warriorY?))
		(if (ego has: iShield) (= shieldValue 10))
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
		(self weapValue: 8)
		(self
			ignoreActors: 1
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
	
	(method (gotBeat param1)
		(self endFight: TRUE)
		(self setScript: param1)
	)
)

(instance fighter of Fighter
	(properties)
)
