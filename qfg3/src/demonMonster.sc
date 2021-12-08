;;; Sierra Script 1.0 - (do not remove this comment)
(script# 845)
(include game.sh)
(use Main)
(use Combat)
(use Motion)
(use System)

(public
	demonMonster 0
)

(instance demonMonster of Monster
	(properties
		x 172
		y 148
		view 823
		loop 1
		roar 910
		primDamage 25
		armorValue 15
		monHP 320
		spellHitX 152
		spellHitY 70
		mustFight 1
	)
	
	(method (init)
		(curRoom picture: 550)
		(super init: &rest)
		(self restart:)
		(cSound setLoop: -1 number: 157 play: 127)
	)
	
	(method (getHurt)
		(self setScript: sReact)
		(super getHurt:)
	)
	
	(method (defenseLevel)
		(return 225)
	)
	
	(method (restart)
		(self setScript: sFight)
	)
)

(instance sFight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (/ 180 arcadeDifficulty))
			)
			(1
				(theMonster
					setLoop: (if (== (theMonster loop?) 1) 2 else 1)
					setCycle: EndLoop self
				)
			)
			(2
				(if (== (theMonster loop?) 1)
					(= ticks 60)
				else
					(= ticks 12)
				)
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
						(theWarrior getHurt: (theMonster primDamage?))
					)
					(
						(and
							(or
								(== (theWarrior view?) 26)
								(== (theWarrior view?) 555)
							)
							(== (theWarrior loop?) 0)
						)
						(theWarrior getHurt: (theMonster primDamage?) 2)
						(theMonster getHurt: (theWarrior weapValue?) 2)
					)
					(else (globalSound number: 940 play:))
				)
				(if (== (theMonster loop?) 1) (theMonster whimper:))
			)
			(3
				(theMonster setCycle: BegLoop self)
			)
			(4 (self init:))
		)
	)
)

(instance sReact of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMonster setCel: 0 setLoop: 1 setCycle: CycleTo 2 1)
				(= ticks 60)
			)
			(1
				(theMonster setCycle: BegLoop self)
			)
			(2
				(theMonster loop: 2)
				(theMonster setScript: sFight)
			)
		)
	)
)
