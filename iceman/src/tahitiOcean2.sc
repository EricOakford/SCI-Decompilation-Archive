;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use System)

(public
	tahitiOcean2 0
)

(instance tahitiOcean2 of Room
	(properties
		picture 15
		horizon 90
		picAngle 70
	)
	
	(method (init &tmp dir)
		(self setRegions: 301 300 308 setScript: dieSoonerScript)
		(ego view: 217 init:)
		(= dir ((User alterEgo?) edgeHit?))
		(super init:)
		(self
			north: (if (== dir SOUTH) prevRoomNum else number)
			south: (if (== dir NORTH) prevRoomNum else number)
			east: (if (== dir WEST) prevRoomNum else number)
			west: (if (== dir EAST) prevRoomNum else number)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/water,room]')
				(Print 15 0)
			)
		)
	)
	
	(method (newRoom nRoom)
		(if (== nRoom number)
			(= curRoomNum -1)
		)
		(self drawPic: number)
		(super newRoom: nRoom)
	)
)

(instance dieSoonerScript of Script
	(properties
		cycles 16
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(((ScriptID 308) script?) cycles: 30)
			)
		)
	)
)
