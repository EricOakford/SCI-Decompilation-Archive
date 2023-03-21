;;; Sierra Script 1.0 - (do not remove this comment)
(script# vDragon)
(include game.sh)
(use Main)
(use Arena)
(use Monster)
(use Procs)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	dragonArena 0
	dragon 1
	dragonScript 2
)

(local
	local0
	local1
	[monsterCel 5] = [0 1 5 6 7]
)
(instance dragonArena of Arena
	(properties
		picture 430
	)
	
	(method (init &tmp [temp0 9])
		(= monster dragon)
		(= monsterNum vDragon)
		(super init: &rest)
		(monster drawStatus:)
		(Load VIEW 462)
		(dragonTail init:)
		(if (== howFast 0)
			(dragonTail addToPic:)
		else
			(dragonTail setScript: drTailScript)
		)
		(dragMusic number: (SoundFX 2) loop: -1 play:)
		(monster init: setScript: dragonScript)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(dragMusic stop:)
		(theMusic2 number: (SoundFX 38) loop: 1 play:)
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
		x 168
		y 141
		view 462
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
		warriorX 100
		warriorY 158
		flameX 159
		flameY 88
		lowBlow 1
	)
	
	(method (init)
		(= nightPalette 1462)
		(PalVary PALVARYTARGET 1462)
		(AssertPalette 462)
		(self ignoreActors:)
		(super init: &rest)
	)
	
	(method (die)
		(SolvePuzzle f460BeatDragon 4 FIGHTER)
		(self canFight: FALSE)
	)
)

(instance dragonScript of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(client view: 462 setLoop: 0 setPri: 10 setCel: 0)
	)
	
	(method (doit)
		(if (and (== state 0) monsterDazzle (== state 0))
			(self changeState: 7)
			(Bclr fMonsterDazzled)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fBattleStarted)
				(client
					action: 0
					ateEgo: 0
					setCel: [monsterCel (Random 0 4)]
					cycleSpeed: 16
					stopUpd:
				)
				(= ticks (Random 12 15))
				(if (Btst fMonsterRecoils)
					(Bclr fMonsterRecoils)
					(= state 1)
				)
			)
			(1
				(if (Random 0 3)
					(= state -1)
					(client setCel: [monsterCel (Random 0 4)] stopUpd:)
					(= ticks (Random 12 15))
				else
					(client setCel: 1 stopUpd:)
					(= ticks 40)
				)
			)
			(2
				(Bclr fBattleStarted)
				(= local1 0)
				(client action: 1 setCel: 0 setCycle: CycleTo 4 1 self)
				(if (client tryAttack: (client opponent?))
					(client ateEgo: 1)
				)
			)
			(3
				(if (client ateEgo?) (= ticks 1) else (= ticks 12))
			)
			(4
				(if (client ateEgo?)
					(client
						doDamage: (client opponent?)
						ateEgo: 0
						setCycle: CycleTo 1 1 self
					)
				else
					(client setCycle: EndLoop self)
				)
			)
			(5
				(client stopUpd:)
				(Bset fBattleStarted)
				(= ticks 20)
			)
			(6 (self changeState: 0))
			(7
				(client action: 0)
				(client setCycle: 0 stopUpd:)
				(= state -1)
				(= ticks (* monsterDazzle 3))
				(= monsterDazzle 0)
			)
		)
	)
)

(instance dragonTail of Prop
	(properties
		x 183
		y 95
		view 462
		loop 2
		priority 8
	)
)

(instance drTailScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register (client cel?))
				(if (< register 2)
					(client setCel: (++ register) stopUpd:)
				else
					(client setCel: 0 stopUpd:)
				)
				(= state -1)
				(= ticks 42)
			)
		)
	)
)
