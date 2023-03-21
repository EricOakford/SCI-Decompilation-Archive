;;; Sierra Script 1.0 - (do not remove this comment)
(script# vGoblin)
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
	goblinArena 0
	goblin 1
	fightScript 2
	killGoblin 3
	goblin2 4
)

(local
	local0
	goblinsInArena
	local2
	[monsterCycle 13] = [0 1 0 3 0 1 0 0 0 1 0 3 -32768]
)
(instance gobMusic of Sound
	(properties
		number 3
		priority 2
		loop -1
	)
)

(instance goblin of Monster
	(properties
		x 166
		y 106
		view 447
		priority 10
		cycleSpeed 15
		illegalBits $0000
		strength 30
		intell 20
		agil 25
		vit 30
		luck 20
		weap 30
		dodge 30
		armorValue 3
		armorEnc 1400
		weapValue 5
		attackRange 40
		warriorX 119
		warriorY 145
		flameX 162
		flameY 77
		lowBlow 1
	)
	
	(method (init)
		(= nightPalette 1447)
		(PalVary PALVARYTARGET 1447)
		(AssertPalette 447)
		(super init:)
	)
	
	(method (die)
		(SolvePuzzle f445BeatGoblin 1 FIGHTER)
		(super die:)
	)
)

(instance goblinArena of Arena
	(properties
		picture 430
	)
	
	(method (init)
		TimedCycle
		(= monster goblin)
		(= monsterNum vGoblin)
		(monster drawStatus:)
		(super init: &rest)
		(if (== prevRoomNum 45)
			(= goblinsInArena (+ numGoblins 1))
		else
			(= goblinsInArena 1)
		)
		(Load VIEW 447)
		(if (> goblinsInArena 1)
			(goblin2 init: stopUpd:)
			(if (> goblinsInArena 2) (goblin3 init: stopUpd:))
		)
		(gobMusic number: (SoundFX 3) init: play:)
		(goblin
			view: 447
			setLoop: 0
			cel: 0
			setPri: 7
			init:
			stopUpd:
			setScript: fightScript
		)
	)
	
	(method (doit)
		(if (or inTransit (> (monster health?) 0))
			(super doit:)
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(gobMusic stop:)
		(DisposeScript 419)
		(theMusic2 number: (SoundFX 7) loop: 1 play:)
		(super dispose:)
	)
)

(instance fightScript of Script
	(properties)
	
	(method (doit)
		(if (and monsterDazzle (== state 0))
			(self changeState: 6)
			(Bclr fMonsterDazzled)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setCel: 0
					ateEgo: 0
					setCycle: 0
					cycleSpeed: (Random 15 18)
				)
				(if (Btst fMonsterRecoils)
					(Bclr fMonsterRecoils)
					(self cue:)
				else
					(switch (Random 0 3)
						(0
							(client action: 0 setLoop: 3 setCycle: EndLoop self)
						)
						(1
							(client setLoop: 2 action: 3 setCycle: EndLoop self)
						)
						(2
							(client action: 0 setCycle: TimedCycle @monsterCycle self)
						)
						(3
							(client setLoop: 2 action: 3 setCycle: EndLoop self)
						)
					)
				)
			)
			(1
				(if (not (Random 0 3)) (= state -1))
				(= ticks (Random 15 18))
			)
			(2
				(if (client tryAttack: (client opponent?))
					(client ateEgo: TRUE)
				)
				(client setLoop: 0 setCel: 0 action: 1 setCycle: CycleTo 2 1)
				(= ticks 60)
			)
			(3
				(if (client ateEgo?)
					(client doDamage: (client opponent?))
					(client ateEgo: FALSE)
				)
				(client setCycle: EndLoop self)
			)
			(4
				(client stopUpd:)
				(= ticks 15)
			)
			(5 (self changeState: 0))
			(6
				(client action: 0)
				(client setCycle: 0)
				(= state -1)
				(= ticks (* monsterDazzle 3))
				(= monsterDazzle 0)
			)
		)
	)
)

(instance killGoblin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fNextMonster)
				(self cue:)
			)
			(1
				(if (> goblinsInArena 1)
					(goblin hide: forceUpd:)
					(= ticks 10)
				else
					(self cue:)
				)
			)
			(2
				(= local2 (not local2))
				(switch (-- goblinsInArena)
					(0
						(if (== prevRoomNum 45) (++ numGoblins))
						(= monsterHealth 0)
						(goblinArena inTransit: 0)
						(curRoom newRoom: prevRoomNum)
					)
					(1
						(goblin2 dispose:)
						(goblin show: posn: 166 106)
					)
					(2
						(goblin3 dispose:)
						(goblin show: posn: 166 106)
					)
					(else 
						(goblin show: posn: 166 106)
					)
				)
				(self cue:)
			)
			(3
				(if goblinsInArena
					(goblin
						setLoop: 0
						cel: 0
						cycleSpeed: 12
						health: (goblin calcHealth:)
						stamina: (goblin calcStamina:)
					)
					(goblin
						setMonsterHP: (goblin health?)
						setScript: fightScript
					)
					(HandsOn)
				)
				(Bclr fNextMonster)
				(goblinArena inTransit: 0)
				(self dispose:)
			)
		)
	)
)

(instance goblin2 of View
	(properties
		x 216
		y 88
		view 447
	)
)

(instance goblin3 of View
	(properties
		x 246
		y 88
		view 447
	)
)
