;;; Sierra Script 1.0 - (do not remove this comment)
(script# 575)
(include game.sh)
(use Main)
(use Combat)
(use Motion)
(use System)

(public
	apemanMonster 0
)

(instance apemanMonster of Monster
	(properties
		x 165
		y 135
		view 571
		roar 905
		primDamage 12
		secDamage 6
		armorValue 4
		monHP 180
		spellHitX 155
		spellHitY 85
	)
	
	(method (init)
		(if (or (== prevRoomNum 800) (== prevRoomNum 810))
			(= mustFight TRUE)
		)
		(curRoom picture: 555)
		(super init: &rest)
		(self setScript: sFight)
		(cSound setLoop: -1 changeTo: 151)
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

(instance sFight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (/ (Random 90 360) arcadeDifficulty))
			)
			(1
				(apemanMonster
					setCel: 0
					setLoop: (Random 0 1)
					setCycle: EndLoop self
				)
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
					(theWarrior
						getHurt:
							(if (theWarrior loop?)
								(theMonster secDamage?)
							else
								(theMonster primDamage?)
							)
					)
				else
					(globalSound number: 940 play:)
				)
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
				(theMonster setCel: 0 setLoop: 2 setCycle: EndLoop self)
				(= ticks 60)
			)
			(1
				(theMonster whimper:)
				(= ticks (- (/ 120 arcadeDifficulty) 39))
			)
			(2 (theMonster setScript: sFight))
		)
	)
)
