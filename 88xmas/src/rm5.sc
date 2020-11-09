;;; Sierra Script 1.0 - (do not remove this comment)
(script# rWiseMen)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm5 0
)

(local
	aFire
	aAngel
	aExtraAngels
)
(instance rm5 of Room
	(properties
		picture rWiseMen
		style IRISOUT
	)
	
	(method (init)
		(Load VIEW rWiseMen)
		((= aFire (Prop new:))
			view: rWiseMen
			loop: 4
			posn: 136 194
			init:
			setCycle: Forward
			cycleSpeed: 1
		)
		((= aAngel (Actor new:))
			view: rWiseMen
			posn: 163 0
			yStep: 3
			ignoreActors:
			init:
			setPri: 15
			setCycle: Forward
			cycleSpeed: 1
			hide:
		)
		((= aExtraAngels (Actor new:))
			view: vAngels
			posn: 73 0
			yStep: 3
		)
		(super init:)
		(self setScript: MusicScript)
	)
)

(instance angels of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(aAngel setLoop: 3 show: setMotion: MoveTo 163 53 self)
			)
			(1
				(aAngel yStep: 1 setLoop: 2 setMotion: MoveTo 163 54 self)
			)
			(2
				(aAngel setLoop: 1 setMotion: MoveTo 163 55 self)
			)
			(3 (aAngel setLoop: 0))
			(10
				(aExtraAngels
					ignoreActors:
					init:
					setLoop: 5
					setCycle: Forward
					cycleSpeed: 1
					setMotion: MoveTo 73 37 self
				)
			)
			(11
				(aExtraAngels setLoop: 4 setMotion: MoveTo 73 38 self)
			)
			(12
				(aExtraAngels setLoop: 3 setCycle: EndLoop self)
			)
			(13
				((View new:)
					view: vAngels
					posn: 73 38
					loop: 3
					cel: 2
					setPri: 13
					ignoreActors:
					init:
					stopUpd:
					addToPic:
				)
				(aExtraAngels
					posn: 253 0
					setLoop: 5
					setCycle: Forward
					cycleSpeed: 1
					setMotion: MoveTo 253 36 self
				)
			)
			(14
				(aExtraAngels setLoop: 4 setMotion: MoveTo 253 37 self)
			)
			(15
				(aExtraAngels setLoop: 3 setCycle: EndLoop self)
			)
			(16
				((View new:)
					view: vAngels
					posn: 253 37
					loop: 3
					cel: 2
					setPri: 13
					ignoreActors:
					init:
					stopUpd:
					addToPic:
				)
				(aExtraAngels
					posn: 28 0
					view: rWiseMen
					setLoop: 3
					setCycle: Forward
					cycleSpeed: 1
					setMotion: MoveTo 28 51 self
				)
			)
			(17
				(aExtraAngels setLoop: 2 setCycle: EndLoop self)
			)
			(18
				((View new:)
					view: rWiseMen
					posn: 28 51
					loop: 2
					cel: 2
					setPri: 10
					ignoreActors:
					init:
					stopUpd:
					addToPic:
				)
				(aExtraAngels
					posn: 298 0
					setLoop: 3
					setCycle: Forward
					cycleSpeed: 1
					setMotion: MoveTo 298 49 self
				)
			)
			(19
				(aExtraAngels setLoop: 2 setCycle: EndLoop self)
			)
			(20
				((View new:)
					view: rWiseMen
					posn: 298 49
					loop: 2
					cel: 2
					setPri: 10
					ignoreActors:
					init:
					stopUpd:
					addToPic:
				)
				(aExtraAngels
					posn: 81 0
					view: rWiseMen
					setLoop: 3
					setCycle: Forward
					cycleSpeed: 1
					setMotion: MoveTo 81 9 self
				)
			)
			(21
				(aExtraAngels setLoop: 2 setCycle: EndLoop self)
			)
			(22
				((View new:)
					view: rWiseMen
					posn: 81 9
					loop: 2
					cel: 2
					setPri: 10
					ignoreActors:
					init:
					stopUpd:
					addToPic:
				)
				(aExtraAngels
					posn: 246 0
					setLoop: 3
					setCycle: Forward
					cycleSpeed: 1
					setMotion: MoveTo 246 8 self
				)
			)
			(23
				(aExtraAngels setLoop: 2 setCycle: EndLoop self)
			)
			(24
				((View new:)
					view: rWiseMen
					posn: 246 8
					loop: 2
					cel: 2
					setPri: 10
					ignoreActors:
					init:
					stopUpd:
					addToPic:
				)
				(aExtraAngels
					posn: 112 0
					view: vAngels
					setLoop: 2
					setCycle: Forward
					cycleSpeed: 1
					setMotion: MoveTo 112 49 self
				)
			)
			(25
				(aExtraAngels setLoop: 1 setCycle: EndLoop self)
			)
			(26
				((View new:)
					view: vAngels
					posn: 112 49
					loop: 1
					cel: 2
					setPri: 10
					ignoreActors:
					init:
					stopUpd:
					addToPic:
				)
				(aExtraAngels
					posn: 217 0
					setLoop: 2
					setCycle: Forward
					cycleSpeed: 1
					setMotion: MoveTo 217 49 self
				)
			)
			(27
				(aExtraAngels setLoop: 1 setCycle: EndLoop self)
			)
			(28
				((View new:)
					view: vAngels
					posn: 217 49
					loop: 1
					cel: 2
					setPri: 10
					ignoreActors:
					init:
					stopUpd:
					addToPic:
				)
				(aExtraAngels
					posn: 50 0
					setLoop: 5
					setCycle: Forward
					cycleSpeed: 1
					setMotion: MoveTo 50 64 self
				)
			)
			(29
				(aExtraAngels setCycle: EndLoop self)
			)
			(30
				((View new:)
					view: vAngels
					posn: 50 64
					loop: 5
					cel: 2
					ignoreActors:
					init:
					stopUpd:
					addToPic:
				)
				(aExtraAngels
					posn: 275 0
					setLoop: 5
					setCycle: Forward
					cycleSpeed: 1
					setMotion: MoveTo 275 64 self
				)
			)
			(31
				(aExtraAngels setCycle: EndLoop self)
			)
			(32
				((View new:)
					view: vAngels
					posn: 275 64
					loop: 5
					cel: 2
					ignoreActors:
					init:
					stopUpd:
					addToPic:
				)
				(aExtraAngels
					posn: 97 0
					view: rWiseMen
					setLoop: 3
					setCycle: Forward
					cycleSpeed: 1
					setMotion: MoveTo 97 63 self
				)
			)
			(33
				(aExtraAngels setCycle: EndLoop self)
			)
			(34
				((View new:)
					view: rWiseMen
					posn: 97 63
					loop: 3
					cel: 2
					ignoreActors:
					init:
					stopUpd:
					addToPic:
				)
				(aExtraAngels
					posn: 235 0
					setLoop: 3
					setCycle: Forward
					cycleSpeed: 1
					setMotion: MoveTo 235 63 self
				)
			)
			(35
				(aExtraAngels setCycle: EndLoop self)
			)
			(36
				((View new:)
					view: rWiseMen
					posn: 235 63
					loop: 3
					cel: 2
					ignoreActors:
					init:
					stopUpd:
					addToPic:
				)
			)
		)
	)
)

(instance MusicScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				((Sound new:) number: 2 loop: 1 play: self)
			)
			(1
				(aAngel setScript: angels)
			)
			(2
				(aAngel setMotion: MoveTo 163 44)
			)
			(3
				(angels changeState: 10)
			)
			(4
				(curRoom newRoom: 3)
			)
		)
	)
)
