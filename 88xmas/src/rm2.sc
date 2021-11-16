;;; Sierra Script 1.0 - (do not remove this comment)
(script# rFireplace)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm2 0
)

(local
	aYuleLog
	aFire
	aCandle
	local3
	aPendulum
	aChestnuts
	aGlint1
	aGlint2
	aGlint3
	local9
	logCel
	local11
	fireCel
)
(instance rm2 of Room
	(properties
		picture rFireplace
		style IRISOUT
	)
	
	(method (init)
		(Load VIEW rFireplace)
		((View new:)
			view: rFireplace
			loop: 5
			cel: 0
			posn: 158 49
			ignoreActors:
			setPri: 1
			addToPic:
		)
		((View new:)
			view: rFireplace
			loop: 5
			cel: 1
			posn: 126 91
			ignoreActors:
			setPri: 5
			addToPic:
		)
		((View new:)
			view: rFireplace
			loop: 5
			cel: 2
			posn: 160 90
			ignoreActors:
			setPri: 5
			addToPic:
		)
		((View new:)
			view: rFireplace
			loop: 5
			cel: 3
			posn: 188 90
			ignoreActors:
			setPri: 5
			addToPic:
		)
		(= logCel (Random 0 2))
		((= aYuleLog (Prop new:))
			view: rFireplace
			loop: 0
			cel: logCel
			posn: 161 157
			init:
			setCycle: Forward
			cycleSpeed: 1
		)
		(= fireCel (Random 0 5))
		((= aFire (Prop new:))
			view: rFireplace
			loop: 1
			cel: fireCel
			posn: 160 136
			setPri: 15
			init:
			setCycle: Forward
			setScript: RandomFlicker
		)
		((= aCandle (Prop new:))
			view: rFireplace
			loop: 2
			posn: 101 24
			init:
			setCycle: Forward
		)
		((= aCandle (Prop new:))
			view: rFireplace
			loop: 3
			posn: 216 27
			init:
			setCycle: Forward
		)
		((= aPendulum (Prop new:))
			view: rFireplace
			loop: 6
			posn: 158 38
			setPri: 15
			init:
			setCycle: Forward
		)
		((= aChestnuts (Prop new:))
			view: rFireplace
			loop: 4
			cel: logCel
			posn: 216 177
			init:
			setCycle: Forward
			cycleSpeed: 1
		)
		((= aGlint1 (Prop new:))
			view: rFireplace
			loop: 7
			setScript: glint1
		)
		((= aGlint2 (Prop new:))
			view: rFireplace
			loop: 8
			setScript: glint2
		)
		((= aGlint3 (Prop new:))
			view: rFireplace
			loop: 9
			setScript: glint3
		)
		(super init:)
	)
)

(instance RandomFlicker of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				((Sound new:) number: sChestnuts loop: 1 play: self)
			)
			(1
				(curRoom newRoom: 5)
			)
		)
	)
)

(instance glint1 of Script
	(method (init c)
		(super init: c)
		(client init:)
	)
	
	(method (changeState newState &tmp n)
		(switch (= state newState)
			(0
				(= n (/ (Random 10 39) 10))
				(client
					posn:
						(switch n
							(1 25)
							(2 61)
							(3 52)
						)
						(switch n
							(1 27)
							(2 113)
							(3 182)
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

(instance glint2 of Script
	(method (init c)
		(super init: c)
		(client init:)
	)
	
	(method (changeState newState &tmp n)
		(switch (= state newState)
			(0
				(= n (/ (Random 10 39) 10))
				(client
					posn:
						(switch n
							(1 5)
							(2 37)
							(3 66)
						)
						(switch n
							(1 48)
							(2 73)
							(3 155)
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

(instance glint3 of Script
	(method (init c)
		(super init: c)
		(client init:)
	)
	
	(method (changeState newState &tmp n)
		(switch (= state newState)
			(0
				(= n (/ (Random 10 39) 10))
				(client
					posn:
						(switch n
							(1 12)
							(2 40)
							(3 24)
						)
						(switch n
							(1 83)
							(2 116)
							(3 153)
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
