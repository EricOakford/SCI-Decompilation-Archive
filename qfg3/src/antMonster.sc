;;; Sierra Script 1.0 - (do not remove this comment)
(script# 565)
(include game.sh)
(use Main)
(use Combat)
(use Motion)
(use Actor)
(use System)

(public
	antMonster 0
)

(instance antMonster of Monster
	(properties
		x 170
		y 142
		view 562
		roar 904
		primDamage 8
		secDamage 10
		armorValue 6
		monHP 150
		spellHitX 162
		spellHitY 98
	)
	
	(method (init)
		(curRoom picture: 560)
		(self setScript: sFight)
		(abdomen init:)
		(theHead init:)
		(splat init: hide:)
		(super init: &rest)
		(cSound setLoop: -1 changeTo: 151)
	)
	
	(method (getHurt)
		(self setScript: sReact)
		(super getHurt:)
	)
	
	(method (defenseLevel)
		(return 120)
	)
	
	(method (restart)
		(self setScript: sFight)
	)
)

(instance abdomen of MonsterProp
	(properties
		x 180
		y 124
		view 562
		loop 1
		signal $4000
	)
	
	(method (cue)
		(self setCycle: BegLoop)
		(splat
			x: 188
			y: 95
			setCel: 0
			show:
			setMotion: MoveTo 128 95 splat
		)
	)
)

(instance theHead of MonsterProp
	(properties
		x 152
		y 126
		view 562
		loop 3
		cel 2
		signal $4000
	)
)

(instance splat of Actor
	(properties
		x 188
		y 95
		yStep 15
		view 562
		loop 2
		priority 11
		signal $4810
		cycleSpeed 5
		xStep 15
		moveSpeed 0
	)
	
	(method (cue)
		(if (< cel 2)
			(theWarrior autoDodge:)
			(if
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
				(theWarrior getHurt: (theMonster secDamage?))
				(self setCycle: EndLoop self)
			else
				(self setMotion: MoveTo (- x 160) y)
			)
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
				(= ticks (/ 90 arcadeDifficulty))
			)
			(1
				(theHead x: 152 y: 126 setCycle: CycleTo 2 1 self)
			)
			(2
				(theWarrior autoDodge:)
				(if
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
				else
					(globalSound number: 940 play:)
				)
				(if (Random 0 1) (theHead setCel: 3))
				(= ticks 60)
			)
			(3
				(theHead y: 120 setCycle: CycleTo 2 1 self)
			)
			(4
				(theWarrior autoDodge:)
				(if
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
				else
					(globalSound number: 940 play:)
				)
				(if (Random 0 1) (theHead setCel: 3))
				(= ticks 60)
			)
			(5
				(theHead y: 126 setCycle: CycleTo 2 1 self)
			)
			(6
				(theWarrior autoDodge:)
				(if
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
				else
					(globalSound number: 940 play:)
				)
				(if (Random 0 1) (theHead setCel: 3))
				(= ticks (/ 180 arcadeDifficulty))
			)
			(7
				(abdomen setCycle: EndLoop abdomen)
				(= ticks (/ 90 arcadeDifficulty))
			)
			(8 (self init:))
		)
	)
)

(instance sReact of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theHead x: 174 y: 110 setCycle: CycleTo 0 1)
				(= ticks 60)
			)
			(1
				(theMonster whimper:)
				(= ticks (- (/ 180 arcadeDifficulty) 59))
			)
			(2 (theMonster setScript: sFight))
		)
	)
)
