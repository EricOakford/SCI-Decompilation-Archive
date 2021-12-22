;;; Sierra Script 1.0 - (do not remove this comment)
(script# rJingleBells)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm3 0
)

(local
	aHorse
	aSleigh
	aBackgroundTree
	aForegroundTree
	backTreeCel
	local5
)
(instance rm3 of Room
	(properties
		picture rJingleBells
		style IRISOUT
	)
	
	(method (init)
		(Load VIEW rJingleBells)
		((= aHorse (Prop new:))
			view: rJingleBells
			loop: 0
			posn: 188 112
			init:
			cycleSpeed: 1
			setCycle: Forward
		)
		((= aSleigh (Prop new:))
			view: rJingleBells
			loop: 1
			posn: 118 112
			init:
			cycleSpeed: 1
			setCycle: Forward
		)
		((= aBackgroundTree (Actor new:))
			view: rJingleBells
			posn: 286 82
			setLoop: 3
			xStep: 1
			init:
			hide:
			setScript: backgroundTree1
		)
		((= aForegroundTree (Actor new:))
			view: rJingleBells
			posn: 286 150
			setLoop: 2
			init:
			hide:
			setScript: foregroundTree1
		)
		(= backTreeCel 0)
		(super init:)
		(self setScript: MusicScript)
	)
)

(instance backgroundTree1 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setCel: backTreeCel
					posn: 286 81
					show:
					setMotion: MoveTo 31 81 self
				)
			)
			(1
				(+= backTreeCel 1)
				(client hide:)
				(= state -1)
				(self cue:)
			)
		)
	)
)

(instance foregroundTree1 of Script
	(method (changeState newState &tmp theCel)
		(switch (= state newState)
			(0
				(= theCel (/ (= theCel (/ (Random 0 299) 10)) 10))
				(client
					cel: theCel
					posn: 286
					(switch theCel
						(0 150)
						(1 153)
						(2 156)
					)
					show:
					setMotion: MoveTo 31
					(switch theCel
						(0 150)
						(1 153)
						(2 156)
					) self
				)
			)
			(1
				(client hide:)
				(= state -1)
				(self cue:)
			)
		)
	)
)

(instance MusicScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				((Sound new:)
					number: sJingleBells
					loop: 1
					play: self
				)
			)
			(1
				(aHorse cycleSpeed: 0)
				(aSleigh cycleSpeed: 0)
				(aBackgroundTree xStep: 2)
				(aForegroundTree xStep: 4)
			)
			(2
				(curRoom newRoom: 4)
			)
		)
	)
)
