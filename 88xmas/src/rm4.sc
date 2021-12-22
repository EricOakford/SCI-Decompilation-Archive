;;; Sierra Script 1.0 - (do not remove this comment)
(script# rDeerRiver)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm4 0
)

(local
	aDeer
	aMoon
	aRipple
	local3
)
(instance rm4 of Room
	(properties
		picture rDeerRiver
		style IRISOUT
	)
	
	(method (init)
		(Load VIEW rDeerRiver)
		((= aDeer (Actor new:))
			view: rDeerRiver
			loop: 0
			posn: 232 158
			xStep: 2
			init:
		)
		((= aMoon (Actor new:))
			view: rDeerRiver
			loop: 4
			posn: 37 64
			xStep: 1
			init:
			setScript: rm4Script
		)
		((= aRipple (Prop new:))
			view: rDeerRiver
			loop: 3
			posn: 126 178
		)
		(super init:)
		(self setScript: MusicScript)
	)
)

(instance rm4Script of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 10)
			)
			(1
				(aDeer
					setLoop: 0
					moveSpeed: 4
					cycleSpeed: 1
					setMotion: MoveTo 197 162 self
				)
				(aMoon
					setLoop: 4
					moveSpeed: 120
					setMotion: MoveTo 44 57
				)
			)
			(2
				(aDeer
					setLoop: 1
					moveSpeed: 4
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(= seconds 10)
			)
			(3
				(aDeer
					setLoop: 0
					moveSpeed: 3
					cycleSpeed: 1
					setStep: 3
					setCycle: Walk
					setMotion: MoveTo 156 168 self
				)
			)
			(4
				(aDeer
					setLoop: 1
					moveSpeed: 4
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(= seconds 10)
			)
			(5
				(aDeer setCycle: BegLoop self)
			)
			(6
				(aDeer
					setPri: 14
					setLoop: 2
					moveSpeed: 4
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(7
				(aDeer stopUpd:)
				(aRipple setPri: 10 init: cycleSpeed: 2 setCycle: Forward)
				(= seconds 5)
			)
			(8
				(aRipple hide:)
				(aDeer startUpd: setCycle: BegLoop self)
			)
			(9
				(aDeer
					setLoop: 1
					moveSpeed: 4
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(10
				(aDeer stopUpd:)
				(= seconds 3)
			)
			(11
				(aDeer startUpd: setCycle: BegLoop self)
			)
			(12
				(aDeer
					setPri: 14
					setLoop: 2
					moveSpeed: 4
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(13
				(aDeer stopUpd:)
				(aRipple show:)
				(= seconds 10)
			)
			(14
				(aRipple dispose:)
				(aDeer startUpd: setCycle: BegLoop self)
			)
			(15
				(aDeer
					setLoop: 1
					moveSpeed: 4
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(16
				(aDeer stopUpd:)
			)
		)
	)
)

(instance MusicScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				((Sound new:)
					number: 3
					loop: 1
					play: self
				)
			)
			(1
				(curRoom newRoom: 6)
			)
		)
	)
)
