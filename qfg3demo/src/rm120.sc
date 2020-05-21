;;; Sierra Script 1.0 - (do not remove this comment)
(script# 120)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm120 0
)

(local
	cuedIt
)
(instance rm120 of Room
	(properties
		picture 120
		picAngle 20
		vanishingY -50
	)
	
	(method (init)
		(ego
			view: 32
			init:
			loop: 1
			cel: 0
			x: 260
			y: 158
			setScale: 180
		)
		(super init:)
		(squatter init:)
		(stander init: addToPic:)
		(self setScript: seeMeGo)
	)
	
	(method (cue)
		(if (not cuedIt)
			(= cuedIt TRUE)
		else
			(self newRoom: 330)
		)
	)
)

(instance seeMeGo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DrawPic 120 FADEOUT)
				(= cycles 1)
			)
			(1
				(music number: 4 play: curRoom)
				(= seconds 3)
			)
			(2
				(ego cycleSpeed: 12 setCycle: EndLoop)
				(= seconds 2)
			)
			(3
				(Display {FROM THE PALACE OF}
					p_font 2510
					p_at 71 149
					p_color 25
				)
				(Display {THE SULTAN OF SHAPEIR}
					p_font 2510
					p_at 61 163
					p_color 25
				)
				(Display {FROM THE PALACE OF}
					p_font 2510
					p_at 70 148
					p_color 30
				)
				(Display {THE SULTAN OF SHAPEIR}
					p_font 2510
					p_at 60 162
					p_color 30
				)
				(= seconds 2)
			)
			(4
				(squatter cycleSpeed: 14 setCycle: EndLoop self)
			)
			(5
				(squatter loop: 2 cel: 0 x: 160 y: 81 setCycle: EndLoop self)
			)
			(6
				(= seconds 1)
			)
			(7
				(squatter setCycle: BegLoop)
			)
			(8 0)
		)
	)
)

(instance squatter of Prop
	(properties
		x 156
		y 76
		view 121
	)
)

(instance stander of View
	(properties
		x 230
		y 96
		view 120
	)
)
