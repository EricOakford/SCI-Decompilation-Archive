;;; Sierra Script 1.0 - (do not remove this comment)
(script# MONSTER) ;214
(include game.sh)
(use Main)
(use StatusBar)
(use Skilled)


(class Monster of Skilled
	(properties
		attackRange 50
		ateEgo 0
		monsterTitle 0
		monsterHP 0
		warriorX 160
		warriorY 190
		flameX 0
		flameY 0
	)
	
	(method (dispose)
		(if monsterTitle
			(monsterHP dispose:)
			(Display MONSTER 0 p_restore monsterTitle)
			;Enemy Status
			(= monsterTitle (= monsterHP 0))
		)
		(super dispose:)
	)
	
	(method (getHurt damage)
		(super getHurt: damage)
		(if (> damage 0)
			(Bset fMonsterDazzled)
		)
		(self setMonsterHP: health)
	)
	
	(method (drawStatus)
		(= opponent (ScriptID 213 0))
		(if (not (= health monsterHealth))
			(= health (self calcHealth:))
		)
		(= stamina (self calcStamina:))
		(= mana (self calcMana:))
		(= monsterTitle
			(Display 214 1
				p_width 120
				p_at 228 13
				p_mode teJustLeft
				p_font 300
				p_color combatColor
				p_save
			)
		)
		((= monsterHP (StatusBar new:))
			x: 240
			y: 33
			titleCel: 0
			priority: 1
			max: (self calcHealth:)
			value: health
			init:
		)
	)
	
	(method (setMonsterHP newValue)
		(monsterHP value: newValue draw:)
	)
)
