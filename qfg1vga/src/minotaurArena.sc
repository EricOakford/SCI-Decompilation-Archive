;;; Sierra Script 1.0 - (do not remove this comment)
(script# vMinotaur)
(include game.sh)
(use Main)
(use Arena)
(use Monster)
(use TCyc)
(use Procs)
(use ForCount)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	minotaurArena 0
	minotaur 1
	minotaurScript 2
	ball 3
)

(local
	local0
	local1
	local2
	monsterCycle1 = [
		0 0
		0 1
		0 2
		0 1
		0 0
		PATHEND
		]
	monsterCycle2 = [
		0 0
		0 2
		0 0
		0 2
		0 0
		PATHEND
		]
)

(instance minotaurArena of Arena
	(properties
		picture 430
	)
	
	(method (init)
		(minoMusic init:)
		ForwardCounter
		TimedCycle
		(= monster minotaur)
		(monster ignoreActors: ignoreControl: cWHITE drawStatus:)
		(super init: &rest)
		(if Night
			(Animate (cast elements?) FALSE)
		)
		(ball init:)
		(addToPics add: legs doit:)
		(minoMusic number: (SoundFX 2) loop: -1 play:)
		(minotaur init: setScript: minotaurScript)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(minoMusic dispose:)
		(theMusic2 number: (SoundFX 38) loop: 1 play:)
		(DisposeScript FORCOUNT)
		(DisposeScript 419)
		(super dispose:)
	)
)

(instance minotaur of Monster
	(properties
		x 190
		y 100
		view 427
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
		warriorX 124
		warriorY 152
		flameX 174
		flameY 81
	)
	
	(method (init)
		(= nightPalette 1427)
		(PalVary PALVARYTARGET 1427)
		(AssertPalette 427)
		(super init:)
	)
	
	(method (die)
		(SolvePuzzle f425BeatMinotaur 5 FIGHTER)
		(self canFight: 0)
	)
)

(instance minotaurScript of Script
	(method (init)
		(super init: &rest)
		(= monsterNum vMinotaur)
		(client
			view: 427
			setLoop: 0
			cel: 0
			setPri: 4
			cycleSpeed: 20
		)
	)
	
	(method (doit)
		(cond 
			((and monsterDazzle (== state 0) (not script))
				(self changeState: 7)
				(Bclr fMonsterDazzled)
			)
			(local0
				(= local1
					(= cycles 0)
				)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(Bset fBattleStarted)
				(ball posn: 500 500 forceUpd: show:)
				(client
					action: 0
					setLoop: 0
					setCel: 0
					cycleSpeed: 20
					ateEgo: 0
				)
				(cond 
					((Btst fMonsterRecoils)
						(Bclr fMonsterRecoils)
						(= state 2)
						(self cue:)
					)
					((Random 0 1)
						(client setCycle: TimedCycle @monsterCycle1 self)
					)
					(else
						(client setCycle: TimedCycle @monsterCycle2 self)
					)
				)
			)
			(1
				(= ticks 10)
			)
			(2
				(client action: 3 setLoop: 4 setCel: 0)
				(if (< (= temp0 (Random 1 3)) 2) (= state -1))
				(ball
					cel: 0
					posn: 141 59
					setCycle: ForwardCounter temp0 self
				)
			)
			(3
				(Bclr fBattleStarted)
				(ball posn: 500 500)
				(client action: 1)
				(client setCel: 0 setLoop: 0 setCycle: CycleTo 3 1 self)
				(if (client tryAttack: (client opponent?))
					(client ateEgo: 1)
					(if (ego has: 2) (client setPri: 14))
				)
			)
			(4
				(if (client ateEgo?)
					(client ateEgo: 0 doDamage: (client opponent?))
					(ShakeScreen 2 shakeSDown)
				)
				(= ticks 8)
			)
			(5
				(client setCycle: EndLoop)
				(= ticks 30)
			)
			(6
				(client setPri: -1 ateEgo: 0)
				(= state -1)
				(= ticks 12)
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

(instance legs of View
	(properties
		x 190
		y 100
		view 427
		loop 2
		priority 2
	)
)

(instance ball of Prop
	(properties
		x 500
		y 500
		view 427
		loop 3
	)
)

(instance minoMusic of Sound
	(properties
		number 2
		priority 2
		loop -1
	)
)
