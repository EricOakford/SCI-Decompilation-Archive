;;; Sierra Script 1.0 - (do not remove this comment)
(script# vTroll) ;450
(include game.sh)
(use Main)
(use Arena)
(use Monster)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	trollArena 0
	troll 1
)

(local
	local0
	theCycles
	[local2 4]
	local6
	local7
)
(instance trollArena of Arena
	(properties
		picture 400
	)
	
	(method (init)
		(= monster troll)
		(= monsterNum vTroll)
		(Load VIEW vTrollFight)
		(super init: &rest)
		(trollLegs init:)
		(addToPics add: trollLegs doit:)
		(trollMusic number: (SoundFX 2) loop: -1 play:)
		(monster setScript: trollScript)
	)
	
	(method (dispose)
		(trollMusic stop:)
		(spareSound number: (SoundFX 38) loop: 1 play:)
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
		y 93
		x 157
		view vTrollFight
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
		warriorX 185
		warriorY 200
		flameX 166
		flameY 74
	)
	
	(method (init)
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
		(SolvePuzzle POINTS_KILLTROLL 4 FIGHTER)
		(self canFight: FALSE)
	)
)

(instance trollScript of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(= theCycles 1)
		(client
			view: vTrollFight
			setLoop: 0
			setPri: 10
			illegalBits: 0
			cel: 8
		)
	)
	
	(method (doit)
		(if (and monsterDazzle (== state 0))
			(= cycles (+ cycles monsterDazzle))
			(= monsterDazzle 0)
			(Bclr fMonsterDazzled)
		)
		(if (Btst fMonsterDazzled)
			(= cycles 0)
			(client setScript: trollHurt)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client action: 0 stopUpd:)
				(if local6
					(= local6 0)
					(= cycles 2)
				else
					(= cycles (Random 8 14))
				)
			)
			(1
				(switch (Random 1 8)
					(1
						(= [local2 0] 0)
						(= [local2 1] 1)
						(= [local2 2] 2)
						(= [local2 3] 1)
						(= [local2 4] 0)
					)
					(2
						(= [local2 0] 4)
						(= [local2 1] 3)
						(= [local2 2] 2)
						(= [local2 3] 3)
						(= [local2 4] 4)
					)
					(3
						(= [local2 0] 4)
						(= [local2 1] 3)
						(= [local2 2] 2)
						(= [local2 3] 1)
						(= [local2 4] 0)
					)
					(4
						(= [local2 0] 0)
						(= [local2 1] 1)
						(= [local2 2] 2)
						(= [local2 3] 3)
						(= [local2 4] 4)
					)
					(5
						(= [local2 0] 0)
						(= [local2 1] 4)
						(= [local2 2] 3)
						(= [local2 3] 4)
						(= [local2 4] 4)
					)
					(6
						(= [local2 0] 4)
						(= [local2 1] 0)
						(= [local2 2] 1)
						(= [local2 3] 0)
						(= [local2 4] 0)
					)
					(7
						(= [local2 0] 0)
						(= [local2 1] 1)
						(= [local2 2] 2)
						(= [local2 3] 1)
						(= [local2 4] 0)
					)
					(8
						(= [local2 0] 0)
						(= [local2 1] 1)
						(= [local2 2] 2)
						(= [local2 3] 3)
						(= [local2 4] 4)
					)
				)
				(if (client tryAttack: (client opponent?))
					(client ateEgo: TRUE)
				)
				(client action: 1 startUpd: setCel: [local2 0])
				(= cycles theCycles)
			)
			(2
				(client setCel: [local2 1])
				(= cycles theCycles)
			)
			(3
				(client setCel: [local2 2])
				(= cycles 2)
			)
			(4
				(client setCel: [local2 3])
				(if
				(and (not local7) (== [local2 2] 2) (client ateEgo?))
					(client doDamage: (client opponent?) ateEgo: 0)
				)
				(= cycles theCycles)
			)
			(5
				(client setCel: [local2 4])
				(= cycles theCycles)
			)
			(6 (self changeState: 0))
		)
	)
)

(instance trollLegs of PicView
	(properties
		y 110
		x 158
		view vTrollFight
		loop 1
		priority 4
	)
)

(instance trollFace of Prop
	(properties
		y 63
		x 158
		view vTrollFight
	)
)

(instance trollHurt of Script
	(properties)
	
	(method (dispose)
		(trollFace dispose:)
		(= local6 1)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr fMonsterDazzled)
				(trollFace init: setLoop: 2 setPri: 14 setCycle: EndLoop self)
			)
			(1
				(client setScript: trollScript 1)
			)
		)
	)
)
