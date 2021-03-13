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
				(gMonster
					setLoop: (if (== (gMonster loop?) 1) 2 else 1)
					setCycle: EndLoop self
				)
			)
			(2
				(if (== (gMonster loop?) 1)
					(= ticks 60)
				else
					(= ticks 12)
				)
				(gWarriorObj autoDodge:)
				(cond 
					(
						(not
							(if
								(or
									(== (gWarriorObj view?) 26)
									(== (gWarriorObj view?) 27)
								)
							else
								(== (gWarriorObj view?) 555)
							)
						)
						(gWarriorObj getHurt: (gMonster primDamage?))
					)
					(
						(and
							(or
								(== (gWarriorObj view?) 26)
								(== (gWarriorObj view?) 555)
							)
							(== (gWarriorObj loop?) 0)
						)
						(gWarriorObj getHurt: (gMonster primDamage?) 2)
						(gMonster getHurt: (gWarriorObj weapValue?) 2)
					)
					(else (globalSound number: 940 play:))
				)
				(if (== (gMonster loop?) 1) (gMonster whimper:))
			)
			(3
				(gMonster setCycle: BegLoop self)
			)
			(4 (self init:))
		)
	)
)

(instance sReact of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gMonster setCel: 0 setLoop: 1 setCycle: CycleTo 2 1)
				(= ticks 60)
			)
			(1
				(gMonster setCycle: BegLoop self)
			)
			(2
				(gMonster loop: 2)
				(gMonster setScript: sFight)
			)
		)
	)
)
