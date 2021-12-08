;;; Sierra Script 1.0 - (do not remove this comment)
(script# vGargoyle)
(include game.sh)
(use Main)
(use Combat)
(use Motion)
(use System)

(public
	gargoyleMonster 0
)

(instance gargoyleMonster of Monster
	(properties
		x 174
		y 136
		view 851
		roar 933
		primDamage 25
		secDamage 15
		armorValue 17
		monHP 280
		spellHitX 164
		spellHitY 61
		mustFight TRUE
	)
	
	(method (init)
		(= monsterHealth 280)
		(curRoom picture: 550)
		(super init: &rest)
		(self setScript: sFight)
	)
	
	(method (getHurt)
		(self setScript: sReact)
		(super getHurt:)
	)
	
	(method (defenseLevel)
		(return 150)
	)
)

(instance sFight of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (/ (Random 45 180) arcadeDifficulty))
			)
			(1
				(if (== (= register (Random 2 3)) 2)
					(theMonster x: 154 setLoop: 2 setCycle: CycleTo 1 1 self)
				else
					(theMonster setLoop: 3 setCycle: EndLoop self)
				)
			)
			(2
				(theWarrior autoDodge:)
				(cond 
					(
						(not
							(if
								(or
									(== (theWarrior view?) 26)
									(== (theWarrior view?) 27)
								)
							else
								(== (theWarrior view?) 555)
							)
						)
						(if (== register 2)
							(theWarrior getHurt: (theMonster primDamage?))
						else
							(theWarrior getHurt: (theMonster secDamage?))
						)
					)
					((== register 3) (globalSound number: 940 play:))
				)
				(if (== register 2)
					(theMonster x: 174 y: 136 whimper: setCycle: EndLoop self)
				else
					(= cycles (+ (/ 3 arcadeDifficulty) 1))
				)
			)
			(3
				(theMonster x: 174 y: 136 setLoop: 0 setCel: 0 stopUpd:)
				(self init:)
			)
		)
	)
)

(instance sReact of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMonster setLoop: 1 setCel: 0 setCycle: EndLoop)
				(= ticks 35)
			)
			(1
				(theMonster whimper:)
				(= ticks (- (/ (Random 45 180) arcadeDifficulty) 14))
			)
			(2 (theMonster setScript: sFight))
		)
	)
)
