;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6)
(include game.sh)
(use Main)
(use Osc)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoRoom6 0
)

(instance demoRoom6 of Room
	(properties
		picture 114
		style IRISIN
	)
	
	(method (init)
		(LoadMany PICTURE 114 535)
		(LoadMany VIEW 113 535)
		(super init:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 15])
		(switch (= state newState)
			(0
				(poster init:)
				(addToPics doit:)
				(DoDisplay
					{Larry's job:}
					#at 175 20
					#color myTextColor4
					#font 2510
					#mode teJustCenter
				)
				(= cycles 25)
			)
			(1
				(DoDisplay
					{to find the}
					#at 175 50
					#color myTextColor4
					#font 2510
					#mode teJustCenter
				)
				(DoDisplay
					{show's hostess, the}
					#at 175 70
					#color myTextColor4
					#font 2510
					#mode teJustCenter
				)
				(DoDisplay
					{"Sexiest Woman}
					#at 175 90
					#color myTextColor4
					#font 2510
					#mode teJustCenter
				)
				(DoDisplay
					{in America!"}
					#at 175 110
					#color myTextColor4
					#font 2510
					#mode teJustCenter
				)
				(= cycles 40)
			)
			(2
				(addToPics dispose:)
				(curRoom drawPic: 535 VSHUTTER)
				(Michelle init:)
				(wink init:)
				(tongue init:)
				(sundae init:)
				(cherry init:)
				(DoDisplay
					{Learn what Larry finds attractive in women...}
					#at -1 10
					#color myTextColor6
					#font 2510
					#mode teJustCenter
				)
				(= cycles 5)
			)
			(3
				(sparkle init: setCycle: EndLoop self)
			)
			(4
				(sparkle dispose:)
				(= cycles 20)
			)
			(5
				(tongue setCycle: CycleTo 4 1 self)
			)
			(6 (= cycles 2))
			(7
				(slurpSound play:)
				(cherry dispose:)
				(tongue setCycle: CycleTo 0 1 self)
			)
			(8 (= cycles 20))
			(9
				(wink setCycle: Oscillate 1)
				(= cycles 20)
			)
			(10
				(UnLoad PICTURE 114)
				(UnLoad PICTURE 535)
				(UnLoad VIEW 113)
				(UnLoad VIEW 535)
				(curRoom newRoom: 7)
			)
		)
	)
)

(instance Michelle of View
	(properties
		x 151
		y 115
		view 535
		priority 3
		signal fixPriOn
	)
)

(instance sundae of View
	(properties
		x 151
		y 140
		view 535
		loop 6
		priority 8
		signal fixPriOn
	)
)

(instance tongue of Prop
	(properties
		x 152
		y 75
		view 535
		loop 5
		priority 10
		signal fixPriOn
	)
)

(instance cherry of View
	(properties
		x 149
		y 113
		view 535
		loop 9
		priority 10
		signal fixPriOn
	)
)

(instance sparkle of Prop
	(properties
		x 148
		y 109
		view 535
		loop 7
		priority 11
		signal fixPriOn
	)
)

(instance wink of Prop
	(properties
		x 153
		y 72
		view 535
		loop 8
		priority 12
		signal fixPriOn
	)
)

(instance slurpSound of Sound
	(properties
		number 2
	)
)

(instance poster of PicView
	(properties
		x 100
		y 85
		view 113
	)
)
