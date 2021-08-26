;;; Sierra Script 1.0 - (do not remove this comment)
(script# vMinotaur) ;425
(include game.sh)
(use Main)
(use Arena)
(use Monster)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	minotaurArena 0
	minotaur 1
)

(local
	local0
	local1
	local2
)
(instance minotaurArena of Arena
	(properties
		picture 400
	)
	
	(method (init)
		(Load VIEW vMinotaurFight)
		(= monster minotaur)
		(super init: &rest)
		(legs init:)
		(body init:)
		(addToPics add: legs body doit:)
		(leftArm ignoreActors: init: setPri: 4 stopUpd:)
		(rightArm ignoreActors: init: setPri: 4 stopUpd:)
		(ball
			ignoreActors:
			setPri: 4
			cycleSpeed: (if (== howFast 0) 0 else 1)
			init:
		)
		(minoMusic number: (SoundFX 2) loop: -1 play:)
		(monster setScript: minotaurScript)
	)
	
	(method (doit)
		(if
			(and
				(Btst fMonsterDazzled)
				(== (minotaurScript state?) 0)
				(not local0)
			)
			(minotaurScript setScript: minotaurHurt)
		)
		(super doit:)
	)
	
	(method (dispose)
		(minoMusic stop:)
		(spareSound number: (SoundFX 38) loop: 1 play:)
		(super dispose:)
	)
)

(instance minoMusic of Sound
	(properties
		number 2
		priority 2
		loop -1
	)
)

(instance minotaur of Monster
	(properties
		y 67
		x 156
		view vMinotaurFight
		loop 2
		strength 80
		intell 40
		agil 60
		vit 100
		luck 50
		weap 70
		dodge 40
		armorValue 5
		weapValue 10
		attackRange 65
		warriorX 190
		flameX 165
		flameY 74
	)
	
	(method (die)
		(SolvePuzzle f425BeatMinotaur 5 FIGHTER)
		(self canFight: FALSE)
	)
)

(instance minotaurScript of Script
	(method (init)
		(super init: &rest)
		(= monsterNum vMinotaur)
		(client
			view: vMinotaurFight
			setLoop: 2
			cel: 0
			setPri: 4
			cycleSpeed:
			(switch howFast
				(slow 0)
				(medium 1)
				(fast 1)
				(fastest 2)
			)
			setCycle: Forward
		)
	)
	
	(method (doit)
		(cond 
			((and monsterDazzle (== state 0) (not script))
				(+= cycles monsterDazzle)
				(= monsterDazzle 0)
				(Bclr fMonsterDazzled)
			)
			(local0 (= local1 (= cycles 0)))
			((not local1)
				(= local1 1)
				(minotaur action: 3)
				(self setScript: minotaurBlock)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client action: 0 setLoop: 2 cel: 0 setCycle: Forward)
				(rightArm setLoop: 1 cel: 1 stopUpd:)
				(ball setLoop: 3 cel: 0 setCycle: Forward)
				(leftArm setCel: 1)
				(cond 
					((>= cycles 8))
					(local2 (= cycles 1) (= local2 0))
					(else (= cycles (Random 6 13)))
				)
			)
			(1
				(client action: 1)
				(rightArm hide:)
				(ball posn: 121 53 setLoop: 4 setCel: 0)
				(= cycles 2)
			)
			(2
				(ball setCel: 1)
				(= cycles 1)
				(if (client tryAttack: (client opponent?))
					(client ateEgo: 1)
				)
			)
			(3
				(if (client ateEgo?)
					(client doDamage: (client opponent?) ateEgo: 0)
				)
				(ball setCycle: EndLoop self)
			)
			(4
				(ball posn: 93 47 setLoop: 3 cel: 0 setCycle: Forward)
				(rightArm show:)
				(self cue:)
			)
			(5 (self changeState: 0))
		)
	)
)

(instance legs of PicView
	(properties
		y 189
		x 153
		view vMinotaurFight
		loop 1
		priority 2
	)
)

(instance body of PicView
	(properties
		y 116
		x 156
		view vMinotaurFight
		priority 3
	)
)

(instance leftArm of Prop
	(properties
		y 57
		x 193
		view vMinotaurFight
		loop 5
		cel 1
	)
)

(instance rightArm of View
	(properties
		y 77
		x 104
		view vMinotaurFight
		loop 1
		cel 1
	)
)

(instance ball of Prop
	(properties
		y 47
		x 93
		view vMinotaurFight
		loop 3
	)
)

(instance minotaurHurt of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 1)
				(Bclr fMonsterDazzled)
				(minotaur y: (- (minotaur y?) 3) setLoop: 6 setCel: 1)
				(= cycles 3)
			)
			(1
				(minotaur setCel: 0)
				(= cycles (Random 5 10))
			)
			(2
				(minotaur y: (+ (minotaur y?) 3) setLoop: 2 setCel: -1)
				(= cycles 2)
			)
			(3
				(= local0 0)
				(client changeState: 0)
				(= local2 1)
				(self dispose:)
			)
		)
	)
)

(instance minotaurBlock of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(leftArm setCel: 0)
				(= cycles 8)
			)
			(2
				(leftArm setCel: 1)
				(= cycles 1)
			)
			(3
				(= local1 0)
				(self dispose:)
			)
		)
	)
)
