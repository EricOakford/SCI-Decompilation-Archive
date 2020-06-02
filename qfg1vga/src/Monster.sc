;;; Sierra Script 1.0 - (do not remove this comment)
(script# MONSTER)
(include game.sh)
(use Main)
(use StatusBar)
(use Skilled)


(class Monster of SkilledActor
	(properties
		attackRange 50
		ateEgo 0
		monsterTitle 0
		monsterHP 0
		warriorX 160
		warriorY 190
		flameX 0
		flameY 0
		lowBlow 0
	)
	
	(method (dispose)
		(monsterHP dispose:)
		(= monsterTitle (= monsterHP 0))
		(super dispose:)
	)
	
	(method (getHurt damage)
		(super getHurt: damage)
		(if (> damage 0) (Bset 233))
	)
	
	(method (drawStatus)
		(= opponent (ScriptID 213 0))
		(if (not (= health monsterHealth))
			(= health (self calcHealth:))
		)
		(= stamina (self calcStamina:))
		(= mana (self calcMana:))
		((= monsterHP (StatusBar new:))
			x: 255
			y: 28
			titleCel: 0
			priority: 1
			max: (self calcHealth:)
			value: health
			init:
		)
	)
	
	(method (setMonsterHP param1)
		(monsterHP value: param1 draw:)
		(return 1)
	)
)
