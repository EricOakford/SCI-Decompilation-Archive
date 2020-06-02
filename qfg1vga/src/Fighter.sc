;;; Sierra Script 1.0 - (do not remove this comment)
(script# 218)
(include game.sh)
(use Main)
(use Skilled)
(use Procs)

(public
	fighter 0
)

(class Fighter of SkilledActor
	(properties
		opponent 0
		fightLeft 0
		fighterView 501
		endFight 0
		baseX 187
		baseY 135
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
	
	(method (attackLevel &tmp temp0 [temp1 20])
		(= temp0
			(/
				(+ (* weap 8) (* agil 4) strength strength intell luck)
				16
			)
		)
		(if (<= stamina 30)
			(= temp0 (- temp0 10))
			(if (<= stamina 10) (= temp0 (- temp0 20)))
		)
		(= temp0 (- temp0 (* lostSleep 10)))
		(if (== action 1) (= temp0 (+ temp0 10)))
		(return temp0)
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
		(return (if (>= temp0 (Rand100)) (return 1) else (return 0)))
	)
	
	(method (getTired param1 param2)
		(if (<= (= stamina (- stamina param1)) 0)
			(= stamina 0)
			(self endFight: 1)
			(self setScript: param2)
		)
		(= [egoStats 15] stamina)
	)
	
	(method (drawWeapons)
		(= baseX (opponent warriorX?))
		(= baseY 135)
		(if (ego has: 5) (= shieldValue 10))
		(= armorEnc (/ (WtCarried) 2))
		(= armorValue 0)
		(cond 
			((ego has: 3) (= armorValue 5))
			((ego has: 4) (= armorValue 3))
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
			(self x: (+ (self x?) 3) forceUpd:)
			((self opponent?)
				posn: (+ ((self opponent?) x?) 3) ((self opponent?) y?)
			)
		else
			(self x: (- (self x?) 3) forceUpd:)
			((self opponent?)
				posn: (- ((self opponent?) x?) 3) ((self opponent?) y?)
			)
		)
	)
	
	(method (gotBeat param1)
		(self endFight: 1)
		(self setScript: param1)
	)
)

(instance fighter of Fighter)
