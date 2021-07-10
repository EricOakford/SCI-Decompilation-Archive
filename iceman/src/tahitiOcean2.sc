;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15)
(include sci.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use System)

(public
	tahitiOcean2 0
)

(instance tahitiOcean2 of Rm
	(properties
		picture 15
		horizon 90
		picAngle 70
	)
	
	(method (init &tmp temp0)
		(self setRegions: 301 300 308 setScript: dieSoonerScript)
		(ego view: 217 init:)
		(= temp0 ((User alterEgo?) edgeHit?))
		(super init:)
		(self
			north: (if (== temp0 3) prevRoomNum else number)
			south: (if (== temp0 1) prevRoomNum else number)
			east: (if (== temp0 4) prevRoomNum else number)
			west: (if (== temp0 2) prevRoomNum else number)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/water,room]') (Print 15 0))
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (== newRoomNumber number) (= curRoomNum -1))
		(self drawPic: number)
		(super newRoom: newRoomNumber)
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
