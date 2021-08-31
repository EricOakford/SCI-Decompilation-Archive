;;; Sierra Script 1.0 - (do not remove this comment)
(script# vTroll)
(include game.sh)
(use Main)
(use Arena)
(use Monster)
(use TCyc)
(use Procs)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	trollArena 0
	troll 1
	trollScript 2
	trollLegs 3
)

(local
	local0
	local1
	[local2 6]
	[monsterCycle1 11] = [0 0 0 1 0 0 0 1 0 0 -32768]
	[monsterCycle2 15] = [0 0 0 1 0 0 0 4 0 3 0 4 0 0 -32768]
)
(instance trollArena of Arena
	(properties)
	
	(method (init)
		(if
			(OneOf prevRoomNum
				11 12 17 18 19 23 24 25 26 27 33 34
				35 36 42 43 44 51 52 56 57 61 62 63
				69 71 72 74 75 79 80 81 85 86 92
			)
			(self picture: 430)
		else
			(self picture: 465)
		)
		(= monster troll)
		(= monsterNum vTroll)
		TimedCycle
		(super init: &rest)
		(Load VIEW 452)
		(troll drawStatus: init: setScript: trollScript)
		(trollLegs init: stopUpd: ignoreActors:)
		(trollMusic number: (SoundFX 2) loop: -1 play:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(trollMusic stop:)
		(spareSound number: (SoundFX 38) loop: 1 play:)
		(DisposeScript 419)
		(super dispose:)
	)
)

(instance trollMusic of Sound
	(properties
		number 2
		priority 2
		loop -1
	)
)

(instance troll of Monster
	(properties
		x 182
		y 91
		view 452
		priority 10
		strength 80
		intell 30
		agil 40
		vit 60
		luck 30
		weap 65
		dodge 50
		armorValue 5
		weapValue 8
		attackRange 65
		warriorX 112
		warriorY 152
		flameX 166
		flameY 74
	)
	
	(method (init)
		(= nightPalette 1452)
		(PalVary PALVARYTARGET 1452)
		(AssertPalette 452)
		(self ignoreActors: ignoreControl: cWHITE)
		(if (or (== prevRoomNum 88) (== prevRoomNum 89))
			(= strength
				(= agil (= vit (= luck (= weap (= dodge 80)))))
			)
			(= intell 60)
			(= weapValue 9)
			(= armorValue 6)
		)
		(super init: &rest)
	)
	
	(method (die)
		(SolvePuzzle f450BeatTroll 4 FIGHTER)
		(self canFight: FALSE)
	)
)

(instance trollScript of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(= local1 1)
		(= register 0)
		(client view: 452 setPri: 10 ateEgo: 0 cycleSpeed: 18)
	)
	
	(method (doit)
		(if (and monsterDazzle (== state 0))
			(self changeState: 8)
			(Bclr fMonsterDazzled)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr fBattleStarted)
				(client
					action: 0
					cel: 0
					loop: 0
					x: 182
					y: 91
					ateEgo: FALSE
					setPri: -1
				)
				(trollLegs cel: 0 x: 182 y: 91 show: forceUpd:)
				(if (Btst fMonsterRecoils)
					(Bclr fMonsterRecoils)
					(self cue:)
				else
					(switch (Random 0 2)
						(0
							(client action: 3 setCycle: TimedCycle @monsterCycle2 self)
						)
						(1
							(client action: 0 setCycle: TimedCycle @monsterCycle1 self)
						)
						(2
							(client action: 3 setCycle: EndLoop self)
						)
					)
				)
			)
			(1
				(if (not (Random 0 4)) (= state -1))
				(= ticks 18)
			)
			(2
				(Bclr fBattleStarted)
				(client action: 3 loop: 3 cel: 0 x: 180 y: 93 forceUpd:)
				(trollLegs hide: forceUpd:)
				(= ticks (Random 12 18))
			)
			(3
				(if (client tryAttack: (client opponent?))
					(client ateEgo: 1)
				)
				(trollLegs cel: 1 x: 157 y: 155 show: forceUpd:)
				(client
					action: 1
					loop: 4
					cel: 0
					x: 181
					y: 101
					forceUpd:
					setCycle: EndLoop self
				)
			)
			(4
				(if (client ateEgo?)
					(client ateEgo: FALSE doDamage: (client opponent?))
					(ShakeScreen 2 shakeSDown)
					(= ticks 40)
				else
					(= ticks 10)
				)
			)
			(5
				(client action: 0)
				(client loop: 3 cel: 0 x: 180 y: 93 forceUpd:)
				(trollLegs hide: forceUpd:)
				(= ticks (Random 18 30))
				(client ateEgo: FALSE)
			)
			(6 (= ticks 1))
			(7 (self changeState: 0))
			(8
				(client action: 0)
				(client setCycle: 0)
				(= state -1)
				(= ticks (* monsterDazzle 3))
				(= monsterDazzle 0)
			)
		)
	)
)

(instance trollLegs of View
	(properties
		x 182
		y 91
		view 452
		loop 2
		priority 4
	)
)
