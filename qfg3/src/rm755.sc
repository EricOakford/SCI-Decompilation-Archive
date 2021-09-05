;;; Sierra Script 1.0 - (do not remove this comment)
(script# 755)
(include game.sh) (include "755.shm")
(use Main)
(use Polygon)
(use Motion)
(use Game)
(use System)

(public
	rm755 0
)

(instance rm755 of Room
	(properties
		noun N_ROOM
		picture 755
		vanishingY -60
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						0
						319
						0
						319
						164
						211
						164
						185
						152
						159
						128
						215
						116
						247
						88
						142
						54
						120
						56
						117
						47
						48
						47
						10
						43
						0
						37
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 0 44 40 55 50 60 106 64 144 57 243 89 215 110 143 126 32 91 0 86
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 0 96 34 94 141 133 206 168 196 174 124 183 103 189 0 189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 53 58 53 51 113 51 113 58
					yourself:
				)
		)
		(if (== prevRoomNum 750)
			(= style 12)
			(if (< (ego y?) 70)
				(self setScript: enterTop)
			else
				(self setScript: enterBottom)
			)
		else
			(ego setScale: 190 normalize: x: 300 y: 180 init:)
		)
		(super init:)
	)
	
	(method (doit)
		(if (GameIsRestarting)
			(cSound number: 750 setLoop: -1 play: 127)
			(globalSound number: 391 setLoop: -1 play: 90)
		)
		(cond 
			(script 0)
			((<= (ego x?) 5) (curRoom setScript: exitWest))
			((or (>= (ego x?) 315) (>= (ego y?) 183)) (curRoom setScript: exitEast))
		)
		(super doit:)
	)
)

(instance enterTop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setScale: 190 x: 0 normalize: init:)
				(= cycles 1)
			)
			(1
				(ego setMotion: MoveTo 45 55 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance enterBottom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setScale: 190 x: 0 normalize: init:)
				(= cycles 1)
			)
			(1
				(ego setMotion: MoveTo 10 93 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance exitWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 0 (ego y?) self)
			)
			(1
				(curRoom newRoom: 750)
				(self dispose:)
			)
		)
	)
)

(instance exitEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (>= (ego x?) 310)
					(ego setMotion: MoveTo 340 (ego y?) self)
				else
					(ego setMotion: MoveTo (ego x?) 200 self)
				)
			)
			(1 (curRoom newRoom: 170))
		)
	)
)
