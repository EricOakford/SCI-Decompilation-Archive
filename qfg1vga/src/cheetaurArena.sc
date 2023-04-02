;;; Sierra Script 1.0 - (do not remove this comment)
(script# vCheetaur)
(include game.sh)
(use Main)
(use Arena)
(use Monster)
(use TimeCyc)
(use Procs)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	cheetaurArena 0
	cheetaur 1
	aFightScript 2
)

(local
	deathCued
	monsterCycle1 = [
		0 0
		0 0
		0 0
		0 0
		0 2
		0 0
		1 2
		2 0
		0 0
		1 0
		2 3
		0 0
		0
		PATHEND
		]
	monsterCycle2 =	[
		0 0
		2 0
		0 0
		3 0
		0 0
		PATHEND
		]
	monsterCycle3 = [
		0 0
		0 1
		0 2
		0 1
		0 0
		PATHEND
		]
)

(instance cheetMusic of Sound
	(properties
		number sHardBattle
		priority 2
		loop -1
	)
)

(instance cheetRoar of Sound
	(properties
		flags $ffff
		number sCheetaurRoar
		priority 1
	)
)

(instance legs of View
	(properties
		x 164
		y 105
		view vCheetaurFight
		loop 4
		priority 5
	)
)

(instance cheetaur of Monster
	(properties
		x 165
		y 105
		view vCheetaurFight
		strength 70
		intell 65
		agil 70
		vit 50
		luck 30
		weap 40
		dodge 60
		armorValue 3
		weapValue 5
		attackRange 65
		warriorX 115
		warriorY 145
		flameX 152
		flameY 80
	)
	
	(method (init)
		(= nightPalette (+ vCheetaurFight 1000))
		(PalVary PALVARYTARGET (+ vCheetaurFight 1000))
		(AssertPalette vCheetaurFight)
		(super init:)
	)
	
	(method (die)
		(SolvePuzzle f440BeatCheetaur 4 FIGHTER)
		(= deathCued TRUE)
	)
)

(instance cheetaurArena of Arena
	(properties
		picture 430
	)
	
	(method (init)
		TimedCycle
		(= monster cheetaur)
		(monster drawStatus:)
		(= monsterNum vCheetaur)
		(super init: &rest)
		(legs init: stopUpd:)
		(Load VIEW vCheetaurFight)
		(cheetaur
			init:
			ignoreControl: cWHITE
			ignoreActors:
			stopUpd:
			setScript: aFightScript
		)
		(cheetMusic
			number: (SoundFX sHardBattle)
			init:
			play:
		)
		(cheetRoar
			init:
			play:
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(cheetMusic stop:)
		(cheetRoar dispose:)
		(theMusic2
			number: (SoundFX sHardBattleEnd)
			loop: 1
			play:
		)
		(DisposeScript TIMECYC)
		(super dispose:)
	)
)

(instance aFightScript of Script
	(method (doit)
		(cond 
			(deathCued
				(= deathCued FALSE)
				(= cycles 0)
			)
			((and monsterDazzle (== state 0) (not script))
				(self changeState: 7)
				(Bclr fMonsterDazzled)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fBattleStarted)
				(cheetaur
					view: vCheetaurFight
					action: ActNone
					cycleSpeed: 25
					ateEgo: FALSE
					setLoop: 0
					setCel: 0
					setPri: -1
				)
				(legs setPri: 5 stopUpd:)
				(if (Btst fMonsterRecoils)
					(Bclr fMonsterRecoils)
					(self cue:)
				)
				(switch (Random 0 1)
					(0
						(client setCycle: TimedCycle @monsterCycle3 self)
					)
					(1
						(cheetRoar play:)
						(cheetaur setCycle: EndLoop self)
					)
				)
			)
			(1
				(client stopUpd:)
				(if (not (Random 0 3))
					(= state -1)
				)
				(= ticks 25)
			)
			(2
				(Bclr fBattleStarted)
				(if (cheetaur tryAttack: (cheetaur opponent?))
					(cheetaur
						ateEgo: TRUE
						setPri: 14
					)
				)
				(client
					action: ActThrust
					setCel: 0
					cycleSpeed: 25
				)
				(switch (Random 0 1)
					(0
						(= register 2)
						(client
							view: vCheetaurBite
							setLoop: 0
						)
						(client setCycle: CycleTo 1 1 self)
					)
					(1
						(cheetRoar play:)
						(= register 3)
						(client
							view: vCheetaurSlash
							setLoop: 0
						)
						(client setCycle: CycleTo 2 1 self)
					)
				)
			)
			(3
				(if (cheetaur ateEgo?)
					(cheetaur
						ateEgo: FALSE
						doDamage: (cheetaur opponent?)
						action: ActParryUp
					)
				)
				(= ticks 18)
			)
			(4
				(if (== register 2)
					(client setPri: -1)
					(legs setPri: 5 stopUpd:)
				)
				(client setCycle: EndLoop self)
			)
			(5
				(cheetaur stopUpd:)
				(if (== register 3)
					(= ticks 18)
				else
					(= ticks 1)
				)
			)
			(6
				(cheetaur ateEgo: FALSE)
				(self changeState: 0)
			)
			(7
				(client action: ActNone)
				(client setCycle: 0)
				(= state -1)
				(= ticks (* monsterDazzle 3))
				(= monsterDazzle 0)
			)
		)
	)
)
