;;; Sierra Script 1.0 - (do not remove this comment)
(script# vBear)
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
	bearArena 0
	bear 1
	aFightScript 2
)

(local
	local0
	local1 = [0 0 0 0 0 0 0 0 0 0 5]
	bearCycle1 = [
		0 0
		0 1
		0 2
		0 1
		0 0
		PATHEND
		]
	bearCycle2 = [
		1 0
		1 1
		1 0
		PATHEND
		]
)

(instance bearLegs of Prop
	(properties
		x 149
		y 91
		view 422
		loop 3
	)
)

(instance bearMusic of Sound
	(properties
		number 2
		priority 2
		loop -1
	)
)

(instance bear of Monster
	(properties
		view 422
		strength 70
		intell 25
		agil 40
		vit 65
		luck 25
		weap 50
		dodge 60
		armorValue 3
		weapValue 5
		attackRange 65
		warriorX 100
		warriorY 133
		flameX 164
		flameY 67
	)
	
	(method (init)
		(= nightPalette 1422)
		(PalVary PALVARYTARGET 1422)
		(AssertPalette 422)
		(super init:)
	)
	
	(method (die)
		(opponent canFight: FALSE)
		(self setScript: 0)
		(= canFight FALSE)
		(= action 10)
		(= local0 1)
		(Bset fBearDying)
		(SolvePuzzle f420BeatBear -25)
		(curRoom newRoom: 171)
	)
)

(instance bearArena of Arena
	(properties
		picture 425
	)
	
	(method (init)
		TimedCycle
		(= monster bear)
		(= monsterNum vBear)
		(= prevRoomNum 171)
		(super init: &rest)
		(bear drawStatus:)
		(addToPics add: bearLegs doit:)
		(bear ignoreActors: ignoreControl: cWHITE)
		(Load VIEW 422)
		(bear
			view: 422
			setLoop: 0
			cel: 0
			posn: 206 88
			setPri: 10
			cycleSpeed: 15
			init:
			setScript: aFightScript
		)
		(bearMusic number: (SoundFX 2) init: play:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(bearMusic dispose:)
		(DisposeScript 419)
		(spareSound number: (SoundFX 38) loop: 1 play:)
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
			((and monsterDazzle (== state 0))
				(Bclr fMonsterDazzled)
				(self changeState: 7)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fFlag284)
				(bear
					setLoop: 0
					setCel: 0
					setCycle: 0
					ateEgo: 0
					setPri: 5
					stopUpd:
					action: 0
				)
				(if (Btst fFlag285)
					(Bclr fFlag285)
					(self cue:)
				)
				(if (not (Random 0 2)) (= state -1))
				(switch (Random 0 1)
					(0
						(client setCycle: TimedCycle @bearCycle1 self)
					)
					(1
						(client setCycle: TimedCycle @bearCycle2 self)
					)
				)
			)
			(1
				(bear setLoop: 1 setCel: 0)
				(= ticks 25)
				(if (bear tryAttack: (bear opponent?))
					(bear ateEgo: 1)
				)
			)
			(2
				(Bclr fFlag284)
				(if (bear ateEgo?)
					(bear setPri: 14 action: 3)
				else
					(bear action: 1)
				)
				(if (Random 0 1)
					(client setLoop: 2)
					(bear setCel: 0 setCycle: CycleTo 2 1 self)
				else
					(client setLoop: 0)
					(bear setCel: 0 setCycle: CycleTo 3 1 self)
				)
			)
			(3
				(if (bear ateEgo?)
					(bear setPri: 5)
					(bear doDamage: (bear opponent?) ateEgo: 0)
				)
				(= ticks 15)
			)
			(4 (bear setCycle: EndLoop self))
			(5 (= ticks 15))
			(6 (self changeState: 0))
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
