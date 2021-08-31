;;; Sierra Script 1.0 - (do not remove this comment)
(script# vOgre)
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
	ogreArena 0
	ogre 1
	ogreScript 2
	ogreLegs 3
)

(local
	[local0 3]
	[monsterCycle 7] = [4 0 4 5 4 0 -32768]
)
(instance ogreArena of Arena
	(properties
		picture 430
	)
	
	(method (init)
		(= monster ogre)
		(= monsterNum vOgre)
		(monster drawStatus:)
		TimedCycle
		(super init: &rest)
		(Load VIEW 457)
		(ogreLegs init: setPri: 2 stopUpd:)
		(ogreMusic number: (SoundFX 2) loop: -1 play:)
		(monster init: setScript: ogreScript)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(ogreMusic dispose:)
		(DisposeScript 419)
		(spareSound number: (SoundFX 38) loop: 1 play:)
		(super dispose:)
	)
)

(instance ogreMusic of Sound
	(properties
		number 2
		priority 2
		loop -1
	)
)

(instance ogre of Monster
	(properties
		x 165
		y 80
		view 457
		priority 10
		illegalBits $0000
		strength 70
		intell 15
		agil 50
		vit 50
		luck 40
		weap 50
		dodge 40
		armorValue 3
		weapValue 7
		attackRange 40
		warriorX 97
		warriorY 161
		flameX 159
		flameY 77
	)
	
	(method (init)
		(= nightPalette 1457)
		(PalVary PALVARYTARGET 1457)
		(AssertPalette 457)
		(super init:)
	)
	
	(method (die)
		(SolvePuzzle f455BeatOgre 2 FIGHTER)
		(self canFight: 0)
	)
)

(instance ogreScript of Script
	(properties)
	
	(method (doit)
		(if (and monsterDazzle (== state 0))
			(self changeState: 9)
			(Bclr fMonsterDazzled)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fBattleStarted)
				(client
					loop: 0
					cel: 0
					setPri: 10
					x: 165
					y: 80
					ateEgo: 0
					action: 3
					cycleSpeed: 20
				)
				(ogreLegs cel: 0 x: 166 y: 88 show: forceUpd:)
				(cond 
					((Btst fMonsterRecoils) (Bclr fMonsterRecoils) (self cue:))
					((Random 0 1) (client setCycle: EndLoop self))
					(else (client cel: 3 setCycle: BegLoop self))
				)
			)
			(1 (= ticks 20))
			(2
				(Bclr fBattleStarted)
				(client action: 1 loop: 3 cel: 0 x: 123 y: 25 forceUpd:)
				(ogreLegs hide: forceUpd:)
				(= ticks (Random 12 18))
			)
			(3
				(if (client tryAttack: (client opponent?))
					(client ateEgo: TRUE setPri: 14)
				)
				(ogreLegs cel: 1 x: 156 y: 94 show: forceUpd:)
				(client
					loop: 4
					cel: 0
					x: 164
					y: 79
					cycleSpeed: 18
					forceUpd:
					setCycle: CycleTo 4 1 self
				)
			)
			(4
				(if (client ateEgo?)
					(client ateEgo: FALSE doDamage: (client opponent?))
				)
				(= ticks 18)
			)
			(5
				(cond 
					((client ateEgo?) (ShakeScreen 2 shakeSDown))
					((Random 0 2) (client ateEgo: FALSE) (= state 2))
				)
				(client setCel: 5)
				(= ticks 20)
			)
			(6
				(client
					action: 0
					cycleSpeed: 24
					setCycle: TimedCycle @monsterCycle self
				)
			)
			(7
				(client action: 3 loop: 3 cel: 0 x: 123 y: 25 forceUpd:)
				(ogreLegs hide: forceUpd:)
				(client ateEgo: FALSE)
				(= ticks (Random 12 18))
			)
			(8 (self changeState: 0))
			(9
				(client action: 0)
				(client setCycle: 0)
				(= state -1)
				(= ticks (* monsterDazzle 3))
				(= monsterDazzle 0)
			)
		)
	)
)

(instance ogreLegs of View
	(properties
		x 166
		y 88
		view 457
		loop 2
	)
)
