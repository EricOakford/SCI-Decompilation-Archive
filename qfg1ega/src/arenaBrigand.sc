;;; Sierra Script 1.0 - (do not remove this comment)
(script# vBrigand) ;465
(include game.sh)
(use Main)
(use Arena)
(use Monster)
(use Sound)
(use Actor)
(use System)

(public
	brigandArena 0
	brigand 1
)

(local
	local0
	local1
)
(instance brigandArena of Arena
	(properties
		picture 400
	)
	
	(method (init)
		(Load VIEW vBrigandFight)
		(= monster brigand)
		(super init: &rest)
		(body init:)
		(addToPics add: body doit:)
		(leftArm ignoreActors: init: setPri: 3 stopUpd:)
		(rightArm ignoreActors: init: setPri: 2 stopUpd:)
		(brigMusic number: (SoundFX 3) loop: -1 play:)
		(monster setScript: brigandScript)
	)
	
	(method (doit)
		(if
		(and (Btst fMonsterDazzled) (== (brigandScript state?) 0))
			(brigandScript setScript: brigandHurt)
		)
		(if
			(and
				(<= (monster health?) 0)
				(== prevRoomNum 91)
				(> (-- numBrigands) 0)
			)
			(HighPrint 465 0)
			;You've taken out the brigand, but... oh, no!  Here comes another one!
			(= prevRoomNum curRoomNum)
			(= curRoomNum 91)
		)
		(super doit:)
	)
	
	(method (dispose)
		(brigMusic stop:)
		(spareSound number: (SoundFX 7) loop: 1 play:)
		(super dispose:)
	)
)

(instance brigMusic of Sound
	(properties
		number 3
		priority 2
		loop -1
	)
)

(instance brigand of Monster
	(properties
		y 77
		x 152
		view vBrigandFight
		loop 3
		strength 30
		intell 30
		agil 40
		vit 30
		luck 30
		weap 30
		dodge 30
		armorValue 4
		weapValue 6
		attackRange 65
		warriorX 180
		warriorY 189
		flameX 162
		flameY 84
	)
	
	(method (init)
		(if (or (== prevRoomNum 91) (== prevRoomNum 73))
			(= strength
				(= agil (= vit (= luck (= weap (= dodge 50)))))
			)
			(= armorValue 5)
			(= weapValue 8)
		)
		(super init: &rest)
	)
	
	(method (die)
		(SolvePuzzle POINTS_KILLBRIGAND 1 FIGHTER)
		(self canFight: 0)
	)
)

(instance brigandScript of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(= monsterNum vBrigand)
		(= brigandHead (if brigandHead else (Random 3 5)))
		(client
			view: vBrigandFight
			setLoop: brigandHead
			cel: 0
			setPri: 2
			stopUpd:
		)
	)
	
	(method (doit)
		(cond 
			((and monsterDazzle (== state 0) (not script))
				(= cycles (+ cycles monsterDazzle))
				(= monsterDazzle 0)
				(= local1 1)
				(= local0 0)
				(Bclr fMonsterDazzled)
			)
			(local0 (= local1 (= cycles 0)))
			((and (not local1) (== cycles 10))
				(= local1 1)
				(brigand action: 3)
				(self setScript: brigandBlock)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client action: 0 setCel: 0)
				(rightArm setCel: 0)
				(leftArm setCel: 0)
				(if (< cycles 13) (= cycles (Random 8 18)))
			)
			(1
				(client action: 1)
				(rightArm setCel: 2)
				(if (client tryAttack: (client opponent?))
					(client ateEgo: 1)
				)
				(= cycles 4)
			)
			(2
				(if (client ateEgo?)
					(client doDamage: (client opponent?) ateEgo: 0)
				)
				(rightArm setCel: 1)
				(= cycles 2)
			)
			(3
				(rightArm setCel: 0)
				(self changeState: 0)
			)
		)
	)
)

(instance body of PicView
	(properties
		y 174
		x 149
		view vBrigandFight
		priority 1
	)
)

(instance leftArm of Prop
	(properties
		y 70
		x 180
		view vBrigandFight
		loop 2
	)
)

(instance rightArm of View
	(properties
		y 71
		x 126
		view vBrigandFight
		loop 1
	)
)

(instance brigandHurt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 1)
				(Bclr fMonsterDazzled)
				(brigand setCel: 1)
				(= cycles 7)
			)
			(1
				(brigand setCel: 2)
				(= cycles 3)
			)
			(2
				(= local0 0)
				(client changeState: 0)
				(= cycles 1)
			)
			(3 (self dispose:))
		)
	)
)

(instance brigandBlock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(leftArm setCel: 1)
				(= cycles 8)
			)
			(1
				(= local1 0)
				(leftArm setCel: 0)
				(= cycles 1)
			)
			(2 (self dispose:))
		)
	)
)
