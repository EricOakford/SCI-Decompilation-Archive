;;; Sierra Script 1.0 - (do not remove this comment)
(script# GOBLIN) ;445
(include game.sh)
(use Main)
(use Arena)
(use Monster)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	goblinArena 0
	goblin 1
)

(local
	local0
	goblinsInArena
	local2
)
(instance gobMusic of Sound
	(properties
		number 3
		priority 2
		loop -1
	)
)

(instance gob3Body of View
	(properties
		y 127
		x 226
		view vGoblinFight
		loop 4
		priority 5
	)
)

(instance gob3Arm of Prop
	(properties
		view vGoblinFight
		loop 5
		priority 6
		cycleSpeed 3
	)
)

(instance gob2Body of View
	(properties
		y 145
		x 99
		view vGoblinFight
		loop 2
		priority 7
	)
)

(instance gob2Arm of Prop
	(properties
		view vGoblinFight
		loop 3
		priority 8
		cycleSpeed 3
	)
)

(instance goblinBody of View
	(properties
		y 177
		x 173
		view vGoblinFight
		loop 1
		priority 9
	)
)

(instance goblinHead of Prop
	(properties
		view vGoblinFight
		loop 6
		priority 14
	)
)

(instance goblin of Monster
	(properties
		y 119
		x 173
		view vGoblinFight
		priority 10
		cycleSpeed 1
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
		warriorX 190
		warriorY 218
		flameX 173
		flameY 93
	)
	
	(method (die)
		(SolvePuzzle POINTS_KILLGOBLIN 1 FIGHTER)
		(super die:)
	)
)

(instance goblinArena of Arena
	(properties
		picture 400
	)
	
	(method (init)
		(Load VIEW vGoblinFight)
		(= monster goblin)
		(= monsterNum GOBLIN)
		(super init: &rest)
		(if (== prevRoomNum 45)
			(= goblinsInArena (+ numGoblins 1))
		else
			(= goblinsInArena 1)
		)
		(goblinBody init: setPri: 9 stopUpd:)
		(if (> goblinsInArena 1)
			(if (> goblinsInArena 2)
				(gob3Body setPri: 5 init: stopUpd:)
				(gob3Arm
					setPri: 6
					posn: (- (gob3Body x?) 14) (- (gob3Body y?) 40)
					init:
					stopUpd:
				)
				(if (== howFast 0)
					(gob3Arm setCycle: EndLoop)
				else
					(gob3Arm setCycle: Forward)
				)
			)
			(gob2Body setPri: 7 init: stopUpd:)
			(gob2Arm
				setPri: 8
				posn: (- (gob2Body x?) 22) (- (gob2Body y?) 55)
				init:
				stopUpd:
			)
			(if (== howFast 0)
				(gob2Arm setCycle: EndLoop)
			else
				(gob2Arm setCycle: Forward)
			)
		)
		(gobMusic number: (SoundFX 3) init: play:)
		(goblin
			view: vGoblinFight
			setLoop: 0
			cel: 0
			setPri: 10
			init:
			stopUpd:
			setScript: fightScript
		)
	)
	
	(method (doit)
		(if
			(and
				(Btst fMonsterDazzled)
				(monster script?)
				(== (fightScript state?) 0)
			)
			(fightScript setScript: goblinHurt)
		)
		(if (or inTransit (> (monster health?) 0))
			(super doit:)
		else
			(= inTransit 1)
			(monster setScript: 0)
			(self setScript: killGoblin)
		)
	)
	
	(method (dispose)
		(gobMusic stop:)
		(spareSound number: (SoundFX 7) loop: 1 play:)
		(super dispose:)
	)
)

(instance fightScript of Script
	(properties)
	
	(method (doit)
		(if (and monsterDazzle (== state 0))
			(= cycles (+ cycles monsterDazzle))
			(= monsterDazzle 0)
			(Bclr fMonsterDazzled)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(goblin cel: 0 setCycle: 0 stopUpd: action: 0)
				(= cycles (Random 7 18))
			)
			(1
				(goblin action: 2 setCycle: CycleTo 2 1 self)
			)
			(2
				(if (goblin tryAttack: (goblin opponent?))
					(goblin doDamage: (goblin opponent?))
				)
				(goblin setCycle: EndLoop self)
			)
			(3 (self changeState: 0))
		)
	)
)

(instance goblinHurt of Script
	(properties)
	
	(method (dispose)
		(goblinHead dispose:)
		(goblin setCel: -1 startUpd:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr fMonsterDazzled)
				(goblin setCel: 1 stopUpd:)
				(goblinHead
					setLoop: 6
					cel: 0
					posn: (+ (goblinBody x?) 1) (- (goblinBody y?) 74)
					setPri: 14
					cycleSpeed: 2
					init:
					setCycle: EndLoop self
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance killGoblin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 213 0) canFight: FALSE)
				(if (> goblinsInArena 1)
					(if (> goblinsInArena 2) (gob3Arm setCycle: 0 stopUpd:))
					(gob2Arm setCycle: 0 stopUpd:)
				)
				(goblin setCel: 1 stopUpd:)
				(goblinHead
					setLoop: 6
					cel: 0
					posn: (+ (goblinBody x?) 1) (- (goblinBody y?) 74)
					setPri: 14
					cycleSpeed: 2
					init:
					setCycle: EndLoop self
				)
			)
			(1
				(if (> goblinsInArena 1)
					(TimePrint 2 445 0)
					;You killed a goblin.
					(goblinBody hide:)
					(goblin hide:)
					(if (cast contains: goblinHead) (goblinHead hide:))
					(= cycles 10)
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
						(goblin dispose:)
						((ScriptID 213 0) dispose:)
						(curRoom newRoom: prevRoomNum)
					)
					(1
						(gob2Body dispose:)
						(gob2Arm dispose:)
						(goblin show:)
						(goblinBody show:)
					)
					(2
						(gob3Body dispose:)
						(gob3Arm dispose:)
						(goblin show:)
						(goblinBody show:)
						(if local2
							(gob2Body posn: 226 (gob2Body y?))
							(gob2Arm
								posn: (- (gob2Body x?) 22) (- (gob2Body y?) 55)
							)
						else
							(gob2Body posn: 99 (gob2Body y?))
							(gob2Arm
								posn: (- (gob2Body x?) 22) (- (gob2Body y?) 55)
							)
						)
						(if (== howFast 0)
							(gob2Arm setCycle: EndLoop)
						else
							(gob2Arm setCycle: Forward)
						)
					)
					(else 
						(if local2
							(gob2Body posn: 226 (gob2Body y?))
							(gob2Arm
								posn: (- (gob2Body x?) 22) (- (gob2Body y?) 55)
							)
							(gob3Body posn: 99 (gob3Body y?))
							(gob3Arm
								posn: (- (gob3Body x?) 14) (- (gob3Body y?) 40)
							)
						else
							(gob2Body posn: 99 (gob2Body y?))
							(gob2Arm
								posn: (- (gob2Body x?) 22) (- (gob2Body y?) 55)
							)
							(gob3Body posn: 226 (gob3Body y?))
							(gob3Arm
								posn: (- (gob3Body x?) 14) (- (gob3Body y?) 40)
							)
						)
						(goblin show:)
						(goblinBody show:)
						(if (== howFast slow)
							(gob2Arm setCycle: EndLoop)
							(gob3Arm setCycle: EndLoop)
						else
							(gob2Arm setCycle: Forward)
							(gob3Arm setCycle: Forward)
						)
					)
				)
				(if goblinsInArena
					(goblin
						setLoop: 0
						cel: 0
						cycleSpeed: 1
						health: (goblin calcHealth:)
						stamina: (goblin calcStamina:)
					)
					(goblin
						setMonsterHP: (goblin health?)
						setScript: fightScript
					)
					((ScriptID 213 0) canFight: 1)
					(HandsOn)
				)
				(goblinArena inTransit: 0)
				(self dispose:)
			)
		)
	)
)
