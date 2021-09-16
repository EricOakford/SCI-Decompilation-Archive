;;; Sierra Script 1.0 - (do not remove this comment)
(script# 333)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room333 0
)

(local
	h1
	h2
	h3
	mineDoor
)
(instance uniTheme of Sound
	(properties
		number 32
	)
)

(instance henchTheme of Sound
	(properties
		number 29
	)
)

(instance Room333 of Room
	(method (init)
		(User canControl: FALSE canInput: FALSE)
		(= picture prevRoomNum)
		(ego edgeHit: 0)
		(ego xStep: 4 init:)
		(super init:)
		(if isNightTime
			(curRoom overlay: (+ picture 100))
		)
		(Load PICTURE 30)
		(Load PICTURE 29)
		(Load PICTURE 28)
		(Load PICTURE 20)
		(Load PICTURE 26)
		(Load PICTURE 27)
		(Load VIEW 142)
		(Load VIEW 143)
		(Load VIEW 144)
		(Load VIEW 146)
		(Load VIEW 140)
		(Load VIEW 390)
		(Load VIEW 387)
		(Load VIEW 60)
		(Load VIEW 80)
		(ego baseSetter: (ScriptID 0 1))
		(= mineDoor (View new:))
		(self setScript: turnUnicorn)
	)
)

(instance from20 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 369 180 self)
			)
			(1
				(if isNightTime
					(curRoom drawPic: 27)
					(curRoom overlay: 127)
				else
					(curRoom drawPic: 27)
				)
				(ego posn: 30 97)
				(ego setMotion: MoveTo 369 100 self)
			)
			(2
				(if isNightTime
					(mineDoor
						view: 600
						loop: 0
						cel: 0
						setPri: 9
						posn: 289 137
						ignoreActors:
						init:
						stopUpd:
					)
					(curRoom drawPic: 28)
					(curRoom overlay: 128)
				else
					(curRoom drawPic: 28)
					(mineDoor
						view: 600
						loop: 1
						cel: 0
						setPri: 10
						posn: 284 131
						init:
						stopUpd:
					)
				)
				(ego posn: 30 187)
				(curRoom setScript: from28)
			)
		)
	)
)

(instance from26 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 369 162 self)
			)
			(1
				(if isNightTime
					(curRoom drawPic: 27)
					(curRoom overlay: 127)
				else
					(curRoom drawPic: 27)
				)
				(ego posn: 30 155)
				(ego setMotion: MoveTo 369 169 self)
			)
			(2
				(if isNightTime
					(mineDoor
						view: 600
						loop: 0
						cel: 0
						setPri: 10
						posn: 289 137
						init:
						stopUpd:
					)
					(curRoom drawPic: 28)
					(curRoom overlay: 128)
				else
					(curRoom drawPic: 28)
					(mineDoor
						view: 600
						loop: 1
						cel: 0
						setPri: 9
						posn: 284 131
						ignoreActors:
						init:
						stopUpd:
					)
				)
				(curRoom setScript: from28)
			)
		)
	)
)

(instance from27 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 369 162 self)
			)
			(1
				(if isNightTime
					(mineDoor
						view: 600
						loop: 0
						cel: 0
						setPri: 10
						posn: 289 137
						init:
						stopUpd:
					)
					(curRoom drawPic: 28)
					(curRoom overlay: 128)
				else
					(curRoom drawPic: 28)
					(mineDoor
						view: 600
						loop: 1
						cel: 0
						setPri: 9
						posn: 283 131
						ignoreActors:
						init:
						stopUpd:
					)
				)
				(curRoom setScript: from28)
			)
		)
	)
)

(instance from28 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 30 187 setMotion: MoveTo 369 187 self)
			)
			(1
				(if isNightTime
					(curRoom drawPic: 29)
					(curRoom overlay: 129)
				else
					(curRoom drawPic: 29)
				)
				(mineDoor dispose:)
				(ego posn: 30 187 setMotion: MoveTo 369 187 self)
			)
			(2
				(if isNightTime
					(curRoom drawPic: 30)
					(curRoom overlay: 130)
				else
					(curRoom drawPic: 30)
				)
				(ego posn: 30 124 setMotion: MoveTo 194 129 self)
			)
			(3
				(ego setMotion: MoveTo 369 98 self)
			)
			(4
				(if isNightTime
					(curRoom drawPic: 179)
				else
					(curRoom drawPic: 79)
				)
				(ego posn: 65 186)
				(in79 changeState: 1)
			)
		)
	)
)

(instance in79 of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= h1 (Actor new:))
				(h1Actions changeState: 1)
				(= underBits (Print 333 0 #at -1 10 #dispose))
			)
			(2
				(= unicorn (Actor new:))
				(unicorn
					posn: (- (ego x?) 7) (ego y?)
					view: 381
					loop: 0
					cel: 0
					setPri: (ego priority?)
					ignoreActors:
					init:
				)
				(ego
					view: 60
					setLoop: 0
					cel: 0
					posn: (- (unicorn x?) 4) (- (unicorn y?) 25)
					setPri: 14
					ignoreActors:
					setCycle: EndLoop self
				)
				(h2 hide:)
				(h3 hide:)
			)
			(3
				(ego
					view: 80
					setLoop: 5
					setCycle: Forward
					setMotion: MoveTo 24 60 self
				)
			)
			(4
				(ego setLoop: 3)
				(Timer setReal: self 2)
			)
			(5
				(ego setLoop: 2)
				(Timer setReal: self 2)
			)
			(6
				(ego setLoop: 1 setMotion: MoveTo 72 29)
				(Timer setReal: self 2)
			)
			(7
				(ego setLoop: 0 setMotion: MoveTo 72 29 self)
			)
			(8
				(ego setLoop: -1 setCel: -1)
				(User canControl: 1)
				(User canInput: 1)
				(curRoom newRoom: 92)
			)
		)
	)
)

(instance h1Actions of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(uniTheme dispose:)
				(henchTheme play:)
				(h1
					ignoreHorizon:
					posn: 57 7
					view: 142
					setCycle: Walk
					setMotion: MoveTo -10 22 self
					ignoreActors:
					illegalBits: 0
					setPri: 13
					init:
				)
			)
			(2
				(if modelessDialog (modelessDialog dispose:))
				(h1 xStep: 4 yStep: 3 setMotion: MoveTo -10 50 self)
				(= h2 (Actor new:))
				(h2 setScript: h2Actions)
			)
			(3
				(h1
					view: 143
					xStep: 6
					yStep: 4
					setMotion: MoveTo 252 66 self
				)
			)
			(4
				(h1
					view: 144
					xStep: 8
					yStep: 6
					setMotion: MoveTo 89 178 self
				)
				(h2Actions changeState: 1)
			)
			(5
				(h1 view: 146 loop: 1 cel: 0 setCycle: EndLoop self)
			)
		)
	)
)

(instance h2Actions of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(h2
					ignoreHorizon:
					posn: 57 7
					view: 142
					setCycle: Walk
					xStep: 4
					yStep: 3
					setMotion: MoveTo 26 65 self
					ignoreActors:
					illegalBits: 0
					setPri: 15
					init:
				)
			)
			(2
				(h2
					view: 143
					xStep: 6
					yStep: 4
					setMotion: MoveTo 42 120 self
				)
				(= h3 (Actor new:))
				(h3 setScript: h3Actions)
				(h3Actions changeState: 1)
			)
			(3
				(h2
					view: 144
					xStep: 8
					yStep: 6
					setMotion: MoveTo 10 185 self
				)
			)
			(4
				(h2
					xStep: 8
					yStep: 6
					setMotion: MoveTo (+ (ego x?) 21) (- (ego y?) 28) self
				)
			)
			(5
				(Face h2 ego)
				(h2 setCycle: Forward)
			)
		)
	)
)

(instance h3Actions of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(h3
					ignoreHorizon:
					posn: 57 7
					view: 142
					setCycle: Walk
					xStep: 4
					yStep: 3
					setMotion: MoveTo 26 65 self
					ignoreActors:
					illegalBits: 0
					setPri: 15
					init:
				)
			)
			(2
				(h3
					view: 143
					xStep: 6
					yStep: 4
					setMotion: MoveTo 42 120 self
				)
			)
			(3
				(h3
					view: 144
					xStep: 8
					yStep: 6
					setMotion: MoveTo 10 185 self
				)
			)
			(4
				(h3
					setMotion: MoveTo (- (ego x?) 15) (- (ego y?) 25) self
				)
			)
			(5 (in79 changeState: 2))
		)
	)
)

(instance turnUnicorn of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE canInput: FALSE)
				(ego setCycle: EndLoop self)
				(uniTheme play:)
			)
			(1
				(Print 333 1)
				(if (== (ego loop?) 1)
					(ego view: 390 cel: 0 loop: 0 setCycle: EndLoop self)
				else
					(self changeState: 2)
				)
			)
			(2
				(= unicornRoom 999)
				(ego view: 387 setCycle: Walk)
				(switch prevRoomNum
					(20 (curRoom setScript: from20))
					(26 (curRoom setScript: from26))
					(27 (curRoom setScript: from27))
				)
			)
		)
	)
)
