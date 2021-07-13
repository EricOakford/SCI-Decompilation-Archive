;;; Sierra Script 1.0 - (do not remove this comment)
(script# 86)
(include game.sh)
(use Main)
(use Follow)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	compoundHallwayRm 0
)

(instance compoundHallwayRm of Room
	(properties
		picture 86
		north 87
		south 81
	)
	
	(method (init)
		(super init:)
		(self setRegions: 310)
		(HandsOff)
		(Load VIEW 86)
		(switch prevRoomNum
			(south
				(Load VIEW 85)
				(Load VIEW 787)
				(globalSound
					number: 80
					owner: theGame
					priority: 1
					loop: -1
					play:
				)
				(self setScript: withGuardScript)
			)
			(else 
				(Load VIEW 684)
				(Load VIEW 787)
				(self setScript: withAmbScript)
			)
		)
		(ego init:)
	)
)

(instance withGuardScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(door init:)
				(ego
					view: 85
					posn: 161 157
					setLoop: 3
					init:
					setMotion: MoveTo 161 109 self
				)
				(guard
					setCycle: Walk
					setLoop: 3
					init:
					setMotion: MoveTo 148 110
				)
			)
			(1
				(door setCycle: EndLoop self)
				(guard setMotion: 0)
			)
			(2
				(curRoom newRoom: 87)
			)
		)
	)
)

(instance withAmbScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 684 posn: 146 104 setLoop: 2 init: setPri: 4)
				(ambass
					setLoop: 2
					setCycle: Walk
					ignoreActors: TRUE
					init:
					illegalBits: 0
					setPri: 4
				)
				(door init: setPri: 5 setCycle: EndLoop self)
			)
			(1
				(ego setMotion: MoveTo 146 164 self)
				(ambass setMotion: Follow ego 20)
			)
			(2
				(curRoom newRoom: 81)
			)
		)
	)
)

(instance guard of Actor
	(properties
		y 168
		x 148
		view 787
	)
)

(instance ambass of Actor
	(properties
		y 101
		x 159
		view 787
	)
)

(instance door of Prop
	(properties
		y 103
		x 137
		view 86
	)
)
