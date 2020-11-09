;;; Sierra Script 1.0 - (do not remove this comment)
(script# rManger)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm7 0
)

(local
	aGlint1
	aGlint2
	aStar
	aWindow
	aWindow2
	aWindow3
	aDonkey
)
(instance rm7 of Room
	(properties
		picture rManger
		style IRISOUT
	)
	
	(method (init)
		(Load VIEW rManger)
		((= aStar (Prop new:))
			view: rManger
			loop: 1
			cel: 0
			posn: 158 17
		)
		((= aGlint1 (Prop new:))
			view: rManger
			setLoop: 5
			setScript: glint1
		)
		((= aGlint2 (Prop new:))
			view: rManger
			setLoop: 5
			setScript: glint2
		)
		((= aWindow (Prop new:))
			view: rManger
			loop: 4
			cel: 0
			posn: 129 153
			setPri: 11
			init:
			cycleSpeed: 4
			setCycle: Forward
		)
		((= aWindow2 (Prop new:))
			view: rManger
			loop: 3
			cel: 1
			posn: 44 125
			setPri: 9
			init:
			cycleSpeed: 4
			setCycle: Forward
		)
		((= aWindow3 (Prop new:))
			view: rManger
			loop: 2
			cel: 0
			posn: 177 124
			setPri: 9
			init:
			cycleSpeed: 3
			setCycle: Forward
		)
		((= aDonkey (Actor new:))
			view: rManger
			setLoop: 6
			posn: 140 199
			ignoreActors:
			xStep: 1
			setCycle: Forward
			moveSpeed: 12
			cycleSpeed: 6
			setPri: 15
			init:
			setScript: walkDonkey
		)
		(super init:)
		(rm7 setScript: MusicScript)
	)
)

(instance glint1 of Script
	
	(method (init c)
		(super init: c)
		(client init:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= temp0 (/ (Random 10 79) 10))
				(client
					posn:
						(switch temp0
							(1 191)
							(2 117)
							(3 44)
							(4 255)
							(5 77)
							(6 142)
							(7 172)
						)
						(switch temp0
							(1 31)
							(2 64)
							(3 9)
							(4 46)
							(5 52)
							(6 108)
							(7 58)
						)
					show:
					cycleSpeed: (Random 2 4)
					setCycle: EndLoop self
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

(instance glint2 of Script

	(method (init c)
		(super init: c)
		(client init:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= temp0 (/ (Random 10 119) 10))
				(client
					posn:
						(switch temp0
							(1 191)
							(2 117)
							(3 44)
							(4 255)
							(5 77)
							(6 142)
							(7 172)
						)
						(switch temp0
							(1 31)
							(2 64)
							(3 9)
							(4 46)
							(5 52)
							(6 108)
							(7 58)
						)
					show:
					cycleSpeed: (Random 1 3)
					setCycle: EndLoop self
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

(instance walkDonkey of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(aDonkey setMotion: MoveTo 140 174 self)
			)
			(1
				(aDonkey setLoop: 7 setMotion: MoveTo 140 164 self)
			)
			(2
				(aDonkey setLoop: 8 setMotion: MoveTo 135 156 self)
			)
			(3
				(aDonkey setLoop: 9 setMotion: MoveTo 132 152 self)
			)
			(4 (aDonkey stopUpd:))
		)
	)
)

(instance MusicScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				((Sound new:) number: 7 loop: 1 play: self)
			)
			(1
				(aStar init: cycleSpeed: 3 setCycle: EndLoop self)
			)
			(2
				(aStar setLoop: 0 setCycle: Forward)
			)
			(3
				(curRoom newRoom: rOpening)
			)
		)
	)
)
