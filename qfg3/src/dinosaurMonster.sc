;;; Sierra Script 1.0 - (do not remove this comment)
(script# 560)
(include game.sh)
(use Main)
(use Combat)
(use Motion)
(use System)

(public
	dinosaurMonster 0
)

(instance dinosaurMonster of Monster
	(properties
		x 152
		y 44
		view 559
		loop 3
		priority 4
		roar 903
		primDamage 15
		secDamage 8
		tertDamage 15
		armorValue 8
		monHP 320
		spellHitX 152
		spellHitY 69
	)
	
	(method (init)
		(curRoom picture: 555)
		(armLeft init:)
		(armRight init:)
		(theTail init: setPri: 0 stopUpd:)
		(super init: &rest)
		(self restart:)
		(cSound setLoop: -1 changeTo: 157)
	)
	
	(method (getHurt)
		(self setScript: sReact)
		(super getHurt:)
	)
	
	(method (defenseLevel)
		(return 200)
	)
	
	(method (restart)
		(self setScript: sFight)
	)
)

(instance armLeft of MonsterProp
	(properties
		x 164
		y 55
		view 559
		loop 2
		priority 6
	)
	
	(method (cue)
		(self setCel: 0 stopUpd:)
	)
)

(instance armRight of MonsterProp
	(properties
		x 158
		y 63
		view 559
		priority 2
	)
	
	(method (cue)
		(self setCel: 0 stopUpd:)
	)
)

(instance theTail of MonsterProp
	(properties
		x 218
		y 86
		view 559
		loop 5
	)
	
	(method (cue)
		(if cel
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
				(theWarrior getHurt: (theMonster tertDamage?))
			else
				(globalSound number: 940 play:)
			)
			(self setCycle: BegLoop self)
		else
			(self setPri: 0)
			(super cue:)
		)
	)
)

(instance sFight of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (/ 180 arcadeDifficulty))
				(theMonster x: 152 y: 44)
			)
			(1
				(theMonster setCel: 0 setCycle: CycleTo 2 1 self)
			)
			(2
				(theMonster setCycle: EndLoop theMonster)
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
				(= ticks (/ (Random 180 270) arcadeDifficulty))
			)
			(3
				(armLeft setCycle: CycleTo 3 1 self)
				(armRight setCycle: CycleTo 3 1 self)
			)
			(4
				(armLeft setCycle: EndLoop armLeft)
				(armRight setCycle: EndLoop armRight)
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
				else
					(globalSound number: 940 play:)
				)
				(= ticks (/ (Random 270 360) arcadeDifficulty))
			)
			(5
				(theTail setPri: 6 setCycle: EndLoop theTail)
				(theMonster setCycle: EndLoop self)
			)
			(6
				(theMonster cue:)
				(= ticks (/ (Random 180 270) arcadeDifficulty))
			)
			(7
				(armRight setCycle: CycleTo 3 1 self)
				(armLeft setCycle: CycleTo 3 1 self)
			)
			(8
				(armLeft setCycle: EndLoop self)
				(armRight setCycle: EndLoop self)
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
				else
					(globalSound number: 940 play:)
				)
			)
			(9
				(armLeft cue:)
				(armRight cue:)
				(= ticks (/ (Random 180 270) arcadeDifficulty))
			)
			(10 (self init:))
		)
	)
)

(instance sReact of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMonster whimper: setCel: 1 x: 172 y: 39)
				(armLeft setCel: 4)
				(armRight setCel: 4)
				(= ticks 30)
			)
			(1
				(armLeft setCel: 1)
				(armRight setCel: 1)
				(= ticks 30)
			)
			(2
				(theMonster whimper:)
				(armLeft setCel: 5)
				(armRight setCel: 5)
				(= ticks (/ 30 arcadeDifficulty))
			)
			(3
				(armLeft setCel: 0)
				(armRight setCel: 0)
				(theMonster x: 152 y: 44 setCel: 0 setScript: sFight)
			)
		)
	)
)
