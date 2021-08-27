;;; Sierra Script 1.0 - (do not remove this comment)
(script# vMantray)
(include game.sh)
(use Main)
(use Arena)
(use Monster)
(use Procs)
(use DPath)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	mantrayArena 0
	mantray 1
	recoilScript 2
)

(local
	local0 = [0 150 123]
	local5
	local6
)
(instance tail of Prop
	(properties
		x 197
		y 51
		view 437
		loop 2
	)
	
	(method (doit)
		(if (mantray mover?)
			(self
				x: (+ (mantray x?) 3)
				y: (+ (mantray y?) 3)
				forceUpd:
			)
		)
		(super doit:)
	)
)

(instance mantrayArena of Arena
	(properties
		picture 430
	)
	
	(method (init)
		DPath
		(= local5 (Random 0 1))
		(= local6 (Random 0 1))
		(= monster mantray)
		(monster drawStatus:)
		(= monsterNum vMantray)
		(super init: &rest)
		(if Night (Animate (cast elements?) 0))
		(stingerMusic number: (SoundFX 2) loop: -1 play:)
		(Load VIEW 437)
		(mantray init: setScript: mantrayScript)
		(tail init: setPri: 3 ignoreActors:)
		(stinger init: ignoreActors: setPri: 8 setLoop: 3)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(stingerMusic dispose:)
		(spareSound number: (SoundFX 38) play:)
		(DisposeScript DPATH)
		(super dispose:)
	)
)

(instance stingerMusic of Sound
	(properties
		number 2
		priority 2
		loop -1
	)
)

(instance mantray of Monster
	(properties
		x 194
		y 48
		view 437
		loop 3
		strength 50
		intell 20
		agil 70
		vit 40
		luck 40
		weap 50
		dodge 50
		armorValue 3
		weapValue 6
		attackRange 85
		warriorX 87
		warriorY 168
		flameX 169
		flameY 92
	)
	
	(method (init)
		(= nightPalette 1437)
		(PalVary PALVARYTARGET 1437)
		(AssertPalette 437)
		(super init:)
	)
	
	(method (die)
		(SolvePuzzle f435BeatMantray 2 FIGHTER)
		(self canFight: 0)
	)
)

(instance mantrayScript of Script
	(method (init)
		(super init: &rest)
		(client view: 437 setPri: 5 ignoreActors:)
	)
	
	(method (doit)
		(if (and monsterDazzle (== state 0) (not script))
			(+= cycles monsterDazzle)
			(= monsterDazzle 0)
			(Bclr fMonsterDazzled)
		)
		(if (and (< (client x?) 194) (not register))
			(= register 1)
			(tail setScript: attackScript)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 0)
				(if (not (tail script?))
					(tail
						setLoop: 4
						setCel: 0
						cycleSpeed: 15
						setPri: 1
						setCycle: Forward
					)
				)
				(client
					setLoop: 0
					moveSpeed: 5
					cycleSpeed: 10
					ateEgo: 0
					setCel: 0
					setCycle: Forward
					action: 3
				)
				(client setMotion: DPath 219 50 223 62 240 68 self)
			)
			(1
				(client setMotion: MoveTo 169 64 self)
			)
			(2
				(if (== (client x?) 169)
					(client action: 1)
				)
				(if (not (tail script?))
					(= register 1)
					(tail setScript: attackScript)
				)
				(client
					setMotion: MoveTo (+ (client x?) 10) (client y?)
				)
				(if (== (client action?) 1)
					(= ticks 200)
				else
					(= ticks 45)
				)
			)
			(3
				(= state -1)
				(client action: 3 setMotion: MoveTo 194 48 self)
			)
		)
	)
)

(instance recoilScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(tail setLoop: 4 setCycle: 0)
				(client
					setLoop: 0
					cycleSpeed: 7
					moveSpeed: 3
					setCycle: Forward
					setMotion: MoveTo 194 48 self
				)
				(if (and (not (Random 0 3)) (not (tail script?)))
					(tail setScript: attackScript)
				)
			)
			(1
				(client setScript: mantrayScript)
			)
		)
	)
)

(instance attackScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(tail setCycle: 0 setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(1
				(stinger
					setCel: 0
					posn: (- (tail x?) 57) (- (tail y?) 15)
					setCycle: CycleTo 2 1 self
				)
				(if (mantray tryAttack: (mantray opponent?))
					(mantray ateEgo: TRUE)
				)
			)
			(2
				(if (mantray ateEgo?)
					(stinger setMotion: MoveTo 95 80 self)
				else
					(stinger setMotion: MoveTo 0 103 self)
				)
			)
			(3
				(stinger posn: 500 500 stopUpd:)
				(if (mantray ateEgo?)
					(mantray doDamage: (mantray opponent?) ateEgo: FALSE)
				)
				(tail setLoop: 4 setCel: 0 setCycle: Forward)
				(self dispose:)
			)
		)
	)
)

(instance stinger of Actor
	(properties
		x 500
		y 500
		yStep 40
		view 437
		loop 3
		cycleSpeed 12
		illegalBits $0000
		xStep 60
		moveSpeed 0
	)
)
