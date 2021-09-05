;;; Sierra Script 1.0 - (do not remove this comment)
(script# 590)
(include game.sh)
(use Main)
(use Combat)
(use Motion)
(use System)

(public
	dewormMonster 0
)

(instance dewormMonster of Monster
	(properties
		x 166
		y 128
		view 586
		loop 1
		roar 908
		primDamage 30
		secDamage 20
		armorValue 10
		monHP 200
		spellHitX 166
		spellHitY 78
	)
	
	(method (init)
		(curRoom picture: 550)
		(super init: &rest)
		(self setScript: sFight)
		(armFront init:)
		(armBack init: hide:)
		(cSound setLoop: -1 changeTo: 154)
	)
	
	(method (getHurt)
		(self setScript: sReact)
		(super getHurt:)
	)
	
	(method (defenseLevel)
		(return 200)
	)
)

(instance armFront of MonsterProp
	(properties
		x 195
		y 130
		view 586
		priority 11
	)
	
	(method (cue)
		(gWarriorObj autoDodge:)
		(if
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
			(gWarriorObj getHurt: (gMonster secDamage?))
		else
			(globalSound number: 940 play:)
		)
		(self setCycle: BegLoop)
	)
)

(instance armBack of MonsterProp
	(properties
		x 143
		y 105
		view 586
		priority 4
	)
	
	(method (cue)
		(if (> cel 2)
			(gWarriorObj autoDodge:)
			(if
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
				(gWarriorObj getHurt: (gMonster secDamage?))
			else
				(globalSound number: 940 play:)
			)
			(self setCycle: CycleTo (Random 0 2) -1 self)
		else
			(self hide:)
		)
	)
)

(instance sFight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (/ (Random 45 180) arcadeDifficulty))
			)
			(1
				(gMonster setCel: 0 x: 166 y: 128 setCycle: EndLoop self)
			)
			(2
				(gWarriorObj autoDodge:)
				(if
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
				(dewormMonster setCycle: CycleTo (Random 0 3) -1 self)
			)
			(3
				(if (Random 0 1)
					(armFront setCycle: EndLoop armFront)
					(= ticks (/ 180 arcadeDifficulty))
				)
				(if (Random 0 1)
					(armBack show: setCycle: EndLoop armBack)
					(= ticks (/ 180 arcadeDifficulty))
				)
				(if (not ticks) (= ticks (/ 18 arcadeDifficulty)))
			)
			(4 (self init:))
		)
	)
)

(instance sReact of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gMonster setCel: 0 setCycle: CycleTo 2 1)
				(= ticks 60)
			)
			(1
				(gMonster whimper:)
				(= ticks (- (/ (Random 180 360) arcadeDifficulty) 59))
			)
			(2 (gMonster setScript: sFight))
		)
	)
)
