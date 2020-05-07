;;; Sierra Script 1.0 - (do not remove this comment)
(script# OGRE) ;455
(include game.sh)
(use Main)
(use Arena)
(use Monster)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	ogreArena 0
	ogre 1
)

(local
	local0
	local1
	local2
	local3
)
(instance ogreArena of Arena
	(properties
		picture 400
	)
	
	(method (init)
		(= monster ogre)
		(= monsterNum OGRE)
		(Load VIEW vOgreFight)
		(super init: &rest)
		(ogre cycleSpeed: 1 setPri: 5)
		(ogreLegs init:)
		(addToPics add: ogreLegs doit:)
		(ogreMusic number: (SoundFX 2) loop: -1 play:)
		(monster setScript: ogreScript)
	)
	
	(method (dispose)
		(ogreMusic stop:)
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
		y 105
		x 150
		view vOgreFight
		priority 10
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
		warriorX 176
		warriorY 212
		flameX 166
		flameY 74
	)
	
	(method (die)
		(SolvePuzzle POINTS_KILLOGRE 2 FIGHTER)
		(self canFight: FALSE)
	)
)

(instance ogreScript of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(= local1 1)
		(client
			view: vOgreFight
			setLoop: 0
			setPri: 10
			illegalBits: 0
			cel: 8
		)
	)
	
	(method (doit)
		(if (and (== (ogre cel?) 0) (== (ogre loop?) 4))
			(Bclr fMonsterDazzled)
		)
		(if (and monsterDazzle (== state 0))
			(= cycles (+ cycles monsterDazzle))
			(= monsterDazzle 0)
			(Bclr fMonsterDazzled)
		)
		(if
			(and
				(Btst fMonsterDazzled)
				(or (== (ogre loop?) 0) (== (ogre loop?) 4))
			)
			(= cycles 0)
			(ogre setCycle: 0)
			(client setScript: ogreHurt)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client action: 0 stopUpd:)
				(if local2
					(= local2 0)
					(= cycles 1)
				else
					(= cycles (Random 8 14))
				)
			)
			(1
				(client
					action: 1
					startUpd:
					setLoop: (if (Random 0 1) 0 else 4)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(if (client tryAttack: (client opponent?))
					(client ateEgo: 1)
				)
				(= cycles 8)
			)
			(3
				(client
					setLoop: (if (Random 0 1) 1 else 5)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(client setLoop: (- (client loop?) 1) cel: 0)
				(RedrawCast)
				(if (and (client ateEgo?) (not local3))
					(client doDamage: (client opponent?) ateEgo: 0)
				)
				(self changeState: 0)
			)
		)
	)
)

(instance ogreLegs of PicView
	(properties
		y 184
		x 156
		view vOgreFight
		loop 2
		priority 2
	)
)

(instance ogreFace of Prop
	(properties
		view vOgreFight
		loop 3
	)
)

(instance ogreHurt of Script
	(properties)
	
	(method (dispose)
		(= local2 1)
		(ogreFace dispose:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr fMonsterDazzled)
				(ogre ignoreActors:)
				(switch (ogre loop?)
					(0
						(if (ogre cel?)
							(ogreFace posn: 136 83)
						else
							(ogreFace posn: 147 74)
						)
					)
					(4
						(if (ogre cel?)
							(ogreFace posn: 157 86)
						else
							(ogreFace posn: 142 79)
						)
					)
				)
				(ogreFace init: setLoop: 3 setPri: 14 setCycle: EndLoop self)
			)
			(1
				(client setScript: ogreScript)
			)
		)
	)
)
