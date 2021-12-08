;;; Sierra Script 1.0 - (do not remove this comment)
(script# 580)
(include game.sh)
(use Main)
(use Combat)
(use Motion)
(use Actor)
(use System)

(public
	cobraMonster 0
)

(instance cobraMonster of Monster
	(properties
		x 185
		y 125
		view 576
		roar 906
		primDamage 8
		secDamage 15
		armorValue 3
		monHP 150
		spellHitX 185
		spellHitY 97
	)
	
	(method (init)
		(curRoom picture: 560)
		(super init: &rest)
		(self setScript: sFight)
		(spit init: hide:)
		(cSound setLoop: -1 changeTo: 151)
	)
	
	(method (cue)
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
			(spit setCycle: EndLoop spit)
			(Bset 115)
		else
			(spit setMotion: MoveTo -58 119 spit)
		)
	)
	
	(method (getHurt)
		(self setScript: sReact)
		(super getHurt:)
	)
	
	(method (defenseLevel)
		(return 220)
	)
)

(instance spit of Actor
	(properties
		x 162
		y 119
		yStep 15
		view 576
		loop 4
		priority 11
		signal $4810
		cycleSpeed 5
		xStep 15
		moveSpeed 0
	)
	
	(method (cue)
		(self hide:)
	)
)

(instance sFight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (/ (Random 90 360) arcadeDifficulty))
			)
			(1
				(theMonster
					setCel: 0
					setLoop: (if (Random 0 1) 1 else 2)
					setCycle: EndLoop self
				)
			)
			(2
				(if (== (theMonster loop?) 1)
					(theMonster setCycle: BegLoop self)
				else
					(= ticks (/ 180 arcadeDifficulty))
				)
			)
			(3
				(theMonster setLoop: 3 setCel: 0 setCycle: CycleTo 1 1 self)
			)
			(4
				(if (Random 0 1)
					(spit
						show:
						x: 162
						y: 119
						setCel: 0
						setMotion: MoveTo 102 119 theMonster
					)
				else
					(theMonster x: 150)
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
					)
				)
				(theMonster setCycle: EndLoop self)
			)
			(5
				(theMonster x: 185 y: 125)
				(self init:)
			)
		)
	)
)

(instance sReact of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMonster setLoop: 0 setCel: 0 setCycle: EndLoop self)
				(= ticks 60)
			)
			(1
				(theMonster whimper:)
				(= ticks (- (/ (Random 45 180) arcadeDifficulty) 14))
			)
			(2 (theMonster setScript: sFight))
		)
	)
)
