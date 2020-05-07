;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1)
(include game.sh)
(use Main)
(use Game)
(use Actor)
(use System)

(public
	demoRoom1 0
)

(instance demoRoom1 of Room
	(properties
		picture 112
	)
	
	(method (init)
		(Load PICTURE 111 112)
		(super init:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (< 0 state) (< state 4))
			(Palette PALCycle 8 15 -1)
			(Palette PALCycle 16 23 -1)
			(Palette PALCycle 32 39 -1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(music number: 4 playBed: self)
				(DoDisplay
					{He's back and...}
					#at 20 10
					#color myTextColor6
					#width 150
					#font 2510
				)
			)
			(1
				(curRoom drawPic: 111 100)
				(leftNitemare init:)
				(rightNitemare init:)
				(DoDisplay
					{He's back and...}
					#at 20 10
					#color myTextColor6
					#width 150
					#font 2510
				)
			)
			(2
				(DoDisplay
					{Sylvester Stallone?}
					#at 175 140
					#width 143
					#color myTextColor6
					#font 2510
				)
			)
			(3
				(DoDisplay
					{Freddie Krueger?}
					#at 185 155
					#width 133
					#color myTextColor6
					#font 2510
				)
			)
			(4
				(DoDisplay
					{Dan Quayle?}
					#at 219 170
					#width 99
					#color myTextColor6
					#font
					2510
				)
			)
			(5
				(UnLoad PICTURE 111)
				(UnLoad VIEW 111)
				(curRoom newRoom: 2)
			)
		)
	)
)

(instance leftNitemare of View
	(properties
		x 131
		y 53
		view 111
	)
)

(instance rightNitemare of View
	(properties
		x 164
		y 41
		view 111
		cel 1
	)
)
