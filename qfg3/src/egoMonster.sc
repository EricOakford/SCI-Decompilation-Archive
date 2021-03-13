;;; Sierra Script 1.0 - (do not remove this comment)
(script# vMirrorMonster)
(include game.sh)
(use Main)
(use Combat)
(use Motion)
(use Actor)
(use System)

(public
	egoMonster 0
)

(procedure (DamageCue)
	(cond 
		((>= [egoStats HEALTH] 55)
			(gWarriorObj getHurt: 30)
		)
		((not (cast contains: haramiMan))
			(haramiMan init:)
		)
	)
)

(instance egoMonster of Monster
	(properties
		x 174
		y 136
		roar 901
		secDamage 30
		armorValue 10
		spellHitX 164
		spellHitY 96
		mustFight 1
	)
	
	(method (init)
		(curRoom picture: 0)
		(self
			primDamage: (* (gWarriorObj weapValue?) 2)
			monHP: [egoStats HEALTH]
			view: (if (ego has: iSword) 844 else 843)
		)
		(super init: &rest)
		(self setScript: sFight)
		(cSound setLoop: -1 changeTo: 845)
	)
	
	(method (getHurt)
		(self setScript: sReact)
		(cond 
			((> (- monsterHealth [egoStats HEALTH]) 60)
				(= monsterHealth (- monsterHealth 5))
			)
			((> (- monsterHealth [egoStats HEALTH]) 30)
				(= monsterHealth (- monsterHealth 2))
			)
		)
		((ScriptID 550 3) update:)
	)
	
	(method (defenseLevel)
		(return 250)
	)
)

(instance sFight of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (/ (Random 45 180) arcadeDifficulty))
			)
			(1
				(self start: 1)
				(if (== gCalledBy 1)
					(client setScript: sCast)
				else
					(gMonster
						setLoop: (if (ego has: 1) (Random 0 2) else 0)
						setCycle: EndLoop self
					)
				)
			)
			(2
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
					(DamageCue (gMonster primDamage?))
				else
					(globalSound number: 940 play:)
				)
				(= cycles (+ (/ 3 arcadeDifficulty) 1))
			)
			(3
				(gMonster setLoop: 0 setCel: 0 stopUpd:)
				(= ticks (/ (Random 45 180) arcadeDifficulty))
			)
			(4 (self init:))
		)
	)
)

(instance sCast of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gMonster setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(1
				(monSpell
					setLoop:
						(switch ((ScriptID 550 2) approachDist?)
							(27 1)
							(32 2)
							(else  0)
						)
					init:
					x: 180
					y: 100
					setMotion: MoveTo (gWarriorObj x?) (- (gWarriorObj y?) 45) monSpell
				)
				(globalSound
					number:
						(switch ((ScriptID 550 2) approachDist?)
							(27 11)
							(32 11)
							(else  13)
						)
					play:
				)
				(= ticks (/ (Random 45 180) arcadeDifficulty))
			)
			(2
				(if (== gCalledBy 1)
					(self init:)
				else
					(client setScript: sFight)
				)
			)
		)
	)
)

(instance sReact of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gMonster setLoop: 0 setCel: 2)
				(= ticks 60)
			)
			(1
				(gMonster whimper:)
				(= ticks (- (/ (Random 45 180) arcadeDifficulty) 14))
			)
			(2
				(if (== gCalledBy 1)
					(gMonster setScript: sCast)
				else
					(gMonster setScript: sFight)
				)
			)
		)
	)
)

(instance monSpell of Actor
	(properties
		x 180
		y 100
		yStep 15
		view 22
		priority 11
		signal $4810
		cycleSpeed 5
		xStep 15
		moveSpeed 0
	)
	
	(method (cue &tmp temp0)
		(if (and (< loop 3) (> x 10))
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
				(= temp0 0)
				(switch ((ScriptID 550 2) approachDist?)
					(27
						(= temp0 (* (+ 10 (/ [egoStats 27] 20)) 2))
					)
					(32
						(= temp0 (* (+ 12 (/ [egoStats 32] 20)) 2))
					)
					(else 
						(= temp0 (* (+ 10 (/ [egoStats 25] 20)) 2))
					)
				)
				(DamageCue temp0)
				(globalSound number: 930 play:)
				(self setCel: 0 setLoop: (+ loop 3) setCycle: EndLoop self)
			else
				(self setMotion: MoveTo -30 y self)
			)
		else
			(self dispose:)
		)
	)
)

(instance haramiMan of Actor
	(properties
		x 245
		y 66
		view 847
		signal (| ignrAct fixPriOn)
		cycleSpeed 40
		xStep 1
		moveSpeed 4
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: CycleTo 1 1 self)
	)
	
	(method (cue)
		(if (not loop)
			(switch cel
				(1
					(self setMotion: MoveTo (- x 10) y setCycle: CycleTo 2 1 self)
				)
				(2
					(self setMotion: MoveTo (- x 10) y setCycle: CycleTo 3 1 self)
				)
				(3
					(self setMotion: MoveTo (- x 10) y setCycle: CycleTo 4 1 self)
				)
				(4
					(self
						setLoop: 1
						setCel: 0
						cycleSpeed: 6
						setCycle: EndLoop self
					)
				)
			)
		else
			(gMonster setCycle: 0 setScript: 0)
			(HandsOff)
			(gWarriorObj setCycle: 0 setScript: sMonsterDie)
		)
	)
)

(instance sMonsterDie of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 40))
			(1
				(= monsterHealth 0)
				(= battleResult -1)
				((ScriptID 550 3) cue:)
			)
		)
	)
)
