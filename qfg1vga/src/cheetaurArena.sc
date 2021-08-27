;;; Sierra Script 1.0 - (do not remove this comment)
(script# vCheetaur)
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
	cheetaurArena 0
	cheetaur 1
	aFightScript 2
)

(local
	local0
	local1 = [
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
		0 0
		2 0
		0 0
		3 0
		0 0
		PATHEND
		]
	monsterCycle = [
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
		number 2
		priority 2
		loop -1
	)
)

(instance cheetRoar of Sound
	(properties
		flags $ffff
		number 107
		priority 1
	)
)

(instance legs of View
	(properties
		x 164
		y 105
		view 442
		loop 4
		priority 5
	)
)

(instance cheetaur of Monster
	(properties
		x 165
		y 105
		view 442
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
		(= nightPalette 1442)
		(PalVary PALVARYTARGET 1442)
		(AssertPalette 442)
		(super init:)
	)
	
	(method (die)
		(SolvePuzzle f440BeatCheetaur 4 FIGHTER)
		(= local0 1)
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
		(Load VIEW 442)
		(cheetaur
			init:
			ignoreControl: cWHITE
			ignoreActors:
			stopUpd:
			setScript: aFightScript
		)
		(cheetMusic number: (SoundFX 2) init: play:)
		(cheetRoar init: play:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(cheetMusic stop:)
		(cheetRoar dispose:)
		(spareSound number: (SoundFX 38) loop: 1 play:)
		(DisposeScript 419)
		(super dispose:)
	)
)

(instance aFightScript of Script
	(method (doit)
		(cond 
			(local0
				(= local0
					(= cycles 0)
				)
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
				(Bset fFlag284)
				(cheetaur
					view: 442
					action: 0
					cycleSpeed: 25
					ateEgo: 0
					setLoop: 0
					setCel: 0
					setPri: -1
				)
				(legs setPri: 5 stopUpd:)
				(if (Btst fFlag285)
					(Bclr fFlag285)
					(self cue:)
				)
				(switch (Random 0 1)
					(0
						(client setCycle: TimedCycle @monsterCycle self)
					)
					(1
						(cheetRoar play:)
						(cheetaur setCycle: EndLoop self)
					)
				)
			)
			(1
				(client stopUpd:)
				(if (not (Random 0 3)) (= state -1))
				(= ticks 25)
			)
			(2
				(Bclr fFlag284)
				(if (cheetaur tryAttack: (cheetaur opponent?))
					(cheetaur ateEgo: 1 setPri: 14)
				)
				(client action: 1 setCel: 0 cycleSpeed: 25)
				(switch (Random 0 1)
					(0
						(= register 2)
						(client view: 443 setLoop: 0)
						(client setCycle: CycleTo 1 1 self)
					)
					(1
						(cheetRoar play:)
						(= register 3)
						(client view: 444 setLoop: 0)
						(client setCycle: CycleTo 2 1 self)
					)
				)
			)
			(3
				(if (cheetaur ateEgo?)
					(cheetaur
						ateEgo: FALSE
						doDamage: (cheetaur opponent?)
						action: 3
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
				(if (== register 3) (= ticks 18) else (= ticks 1))
			)
			(6
				(cheetaur ateEgo: FALSE)
				(self changeState: 0)
			)
			(7
				(client action: 0)
				(client setCycle: 0)
				(= state -1)
				(= ticks (* monsterDazzle 3))
				(= monsterDazzle 0)
			)
		)
	)
)
