;;; Sierra Script 1.0 - (do not remove this comment)
(script# 5)
(include game.sh)
(use Main)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoRoom5 0
)

(instance demoRoom5 of Room
	(properties
		picture 190
		style IRISIN
	)
	
	(method (init)
		(LoadMany PICTURE 190 100)
		(LoadMany VIEW 192)
		(super init:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fountain init:)
				(water init:)
				(limo init: setMotion: MoveTo 100 130 self)
			)
			(1 (= seconds 4))
			(2
				(UnLoad PICTURE 190)
				(UnLoad VIEW 192)
				(UnLoad PICTURE 100)
				(curRoom newRoom: 6)
			)
		)
	)
)

(instance limo of Actor
	(properties
		x -25
		y 130
		view 192
		loop 2
		signal fixedLoop
	)
)

(instance fountain of Prop
	(properties
		x 83
		y 182
		view 192
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward)
	)
)

(instance water of Prop
	(properties
		x 95
		y 189
		view 192
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward)
	)
)
