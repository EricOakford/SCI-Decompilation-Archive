;;; Sierra Script 1.0 - (do not remove this comment)
(script# vSaurus)
(include game.sh)
(use Main)
(use Arena)
(use Monster)
(use TimeCyc)
(use Procs)
(use RandCyc)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	saurusArena 0
	saurus 1
	saurusScript 2
)

(local
	local0	;unused
	deathCued
	monsterCycle1 = [
		0 0
		0 1
		0 2
		0 3
		0 1
		PATHEND
		]
	monsterCycle2 = [
		0 0
		0 1
		0 2
		0 1
		0 0
		PATHEND
		]
	monsterCycle3 = [
		0 0
		0 7
		0 6
		0 7
		0 6
		PATHEND
		]
	monsterCel = [0 1 7]
)

(instance saurusArena of Arena
	(properties
		picture pForestArena
	)
	
	(method (init)
		TimedCycle
		(= monster saurus)
		(= monsterNum vSaurus)
		(monster drawStatus:)
		(super init: &rest)
		(saurusTail
			init:
			ignoreActors:
			setCycle: RandCycle
		)
		(saurMusic
			number: (SoundFX sEasyBattle)
			init:
			play:
		)
		(Load VIEW vSaurusFight)
		(monster init: ignoreActors: setScript: saurusScript)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(saurMusic dispose:)
		(theMusic2
			number: (SoundFX sEasyBattleEnd)
			loop: 1
			play:
		)
		(DisposeScript TIMECYC)
		(super dispose:)
	)
)

(instance saurMusic of Sound
	(properties
		number sEasyBattle
		priority 2
		loop -1
	)
)

(instance saurus of Monster
	(properties
		x 186
		y 135
		view vSaurusFight
		strength 30
		intell 5
		agil 20
		vit 25
		luck 10
		weap 30
		dodge 10
		armorValue 3
		weapValue 5
		attackRange 55
		warriorX 122
		warriorY 142
		flameX 176
		flameY 91
		lowBlow TRUE
	)
	
	(method (init)
		(= nightPalette (+ vSaurusFight 1000))
		(PalVary PALVARYTARGET (+ vSaurusFight 1000))
		(AssertPalette vSaurusFight)
		(super init:)
	)
	
	(method (die)
		(SolvePuzzle f430BeatSaurus 1 FIGHTER)
		(= deathCued TRUE)
	)
)

(instance saurusScript of Script
	(method (init)
		(= register 0)
		(super init: &rest)
	)
	
	(method (doit)
		(cond 
			(deathCued
				(= deathCued
					(= ticks 0)
				)
			)
			((and monsterDazzle (== state 0))
				(self changeState: 6)
				(Bclr fMonsterDazzled)
			)
		)
		(if (== state -1)
			(client cycleSpeed: (Random 15 20))
			(saurusTail cycleSpeed: (Random 20 80))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fBattleStarted)
				(client
					action: ActNone
					ateEgo: FALSE
					setLoop: 0
				)
				(if (Btst fMonsterRecoils)
					(Bclr fMonsterRecoils)
					(= state 2)
					(self cue:)
				else
					(if (not (Random 0 3)) (= state -1))
					(if (not register)
						(switch (Random 0 3)
							(0
								(client setCycle: TimedCycle @monsterCycle2 self)
							)
							(1
								(client setCycle: TimedCycle @monsterCycle1 self)
							)
							(2
								(client setCycle: TimedCycle @monsterCycle3 self)
							)
							(3
								(= state -1)
								(++ register)
								(self cue:)
							)
						)
					else
						(++ register)
						(client setCel: [monsterCel (Random 0 2)])
						(if (> register (Random 2 4))
							(= state (= register 0))
						else
							(= state -1)
						)
						(= ticks (Random 12 16))
					)
				)
			)
			(1
				(client setCel: 0)
				(= ticks 10)
			)
			(2
				(client setLoop: 1)
				(= ticks 30)
			)
			(3
				(Bclr fBattleStarted)
				(if (client tryAttack: (client opponent?))
					(client ateEgo: TRUE)
				)
				(client
					action: ActThrust
					setLoop: 0
					setCel: 0
					cycleSpeed: 12
					setCycle: CycleTo 4 1
				)
				(= ticks 62)
			)
			(4
				(if (client ateEgo?)
					(client
						ateEgo: FALSE
						doDamage: (client opponent?)
					)
				)
				(client setCycle: EndLoop self)
			)
			(5
				(self changeState: 0)
			)
			(6
				(client action: ActNone)
				(client setCycle: 0)
				(= state -1)
				(= ticks (* monsterDazzle 3))
				(= monsterDazzle 0)
			)
		)
	)
)

(instance saurusTail of Prop
	(properties
		x 189
		y 102
		view vSaurusFight
		loop 2
		cycleSpeed 26
	)
)
