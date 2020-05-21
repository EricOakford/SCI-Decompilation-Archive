;;; Sierra Script 1.0 - (do not remove this comment)
(script# 600)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm600 0
)

(instance rm600 of Room
	(properties
		picture 600
	)
	
	(method (init)
		(super init:)
		(gate init: addToPic:)
		(egoAndJohari setPri: 15 init:)
		(self setScript: seeMeGo)
	)
)

(instance seeMeGo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(Display {THE ROMANCE}
					p_font 2510
					p_at 96 44
					p_color 0
				)
				(Display {THE ROMANCE}
					p_font 2510
					p_at 95 43
					p_color 30
				)
				(egoAndJohari setCycle: EndLoop self)
			)
			(2
				(egoAndJohari loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3 (= seconds 4))
			(4 (curRoom newRoom: 550))
		)
	)
)

(instance egoAndJohari of Prop
	(properties
		x 110
		y 189
		view 601
	)
)

(instance gate of View
	(properties
		x 147
		y 126
		view 600
	)
)
