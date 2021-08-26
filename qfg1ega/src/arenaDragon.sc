;;; Sierra Script 1.0 - (do not remove this comment)
(script# vDragon) ;460
(include game.sh)
(use Main)
(use Arena)
(use Monster)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	dragonArena 0
	dragon 1
)

(local
	local0
	local1
)
(instance dragonArena of Arena
	(properties
		picture 460
	)
	
	(method (init)
		(Load VIEW vDragonFight)
		(= monster dragon)
		(= monsterNum vDragon)
		(super init: &rest)
		(dragonTail init:)
		(if (== howFast slow)
			(dragonTail addToPic:)
		else
			(dragonTail cycleSpeed: 6 setScript: drTailScript)
		)
		(dragMusic number: (SoundFX 2) loop: -1 play:)
		(monster setScript: dragonScript)
	)
	
	(method (dispose)
		(dragMusic stop:)
		(spareSound number: (SoundFX 38) loop: 1 play:)
		(super dispose:)
	)
)

(instance dragMusic of Sound
	(properties
		number 2
		priority 2
		loop -1
	)
)

(instance dragon of Monster
	(properties
		y 118
		x 170
		view vDragonFight
		priority 10
		strength 80
		intell 40
		agil 50
		vit 100
		luck 50
		weap 90
		dodge 40
		armorValue 5
		weapValue 8
		attackRange 65
		warriorX 185
		warriorY 210
		flameX 163
		flameY 112
	)
	
	(method (die)
		(SolvePuzzle f460BeatDragon 4 FIGHTER)
		(self canFight: FALSE)
	)
)

(instance dragonScript of Script
	(method (init)
		(super init: &rest)
		(client view: vDragonFight setLoop: 0 setPri: 10 cel: 8)
	)
	
	(method (doit)
		(if (== state 0)
			(cond 
				((and monsterDazzle (not script))
					(+= cycles monsterDazzle)
					(= monsterDazzle 0)
					(Bclr fMonsterDazzled)
				)
				((Btst fMonsterDazzled)
					(= cycles 0)
					(self setScript: dragonHurt)
				)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					action: 0
					cycleSpeed: 0
					setCycle: 0
					posn: 170 118
					stopUpd:
				)
				(cond 
					((>= cycles 7))
					(local1 (= cycles 1))
					(else (= cycles (Random 5 10)))
				)
			)
			(1
				(= local1 0)
				(client action: 1 setCycle: CycleTo 5 1 self)
				(if (client tryAttack: (client opponent?))
					(client ateEgo: 1)
				)
			)
			(2
				(if (client ateEgo?)
					(client doDamage: (client opponent?) ateEgo: 0)
				)
				(client cycleSpeed: 1 setCycle: CycleTo 1 1 self)
			)
			(3
				(if (= local0 (- 1 local0))
					(client setCycle: CycleTo 8 1 self)
				else
					(self cue:)
				)
			)
			(4 (self changeState: 0))
		)
	)
)

(instance dragonTail of Prop
	(properties
		y 121
		x 87
		view vDragonFight
		loop 1
		priority 8
	)
)

(instance drTailScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: EndLoop self))
			(1
				(= state -1)
				(client setCycle: BegLoop self)
			)
		)
	)
)

(instance dragonHurt of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr fMonsterDazzled)
				(dragonTail cycleSpeed: 0)
				(dragon setLoop: 2 setCel: 0)
				(= cycles 6)
			)
			(1
				(dragon setLoop: 0 setCel: -1)
				(dragonTail cycleSpeed: 6)
				(= cycles 2)
			)
			(2
				(= local1 1)
				(client changeState: 0)
				(self dispose:)
			)
		)
	)
)
