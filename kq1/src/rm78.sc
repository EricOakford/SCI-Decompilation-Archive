;;; Sierra Script 1.0 - (do not remove this comment)
(script# 78)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Game)
(use User)

(public
	rm78 0
)

(instance rm78 of Room
	(properties
		picture 78
		east 77
		west 36
	)
	
	(method (init)
		(self style:
		(switch prevRoomNum
			(west WIPERIGHT)
			(east WIPELEFT)
		))
		(super init:)
		(switch prevRoomNum
			(east
				(ego posn: 315 (proc0_17 158 (ego y?) 142))
			)
			(west
				(ego posn: 49 88)
			)
			(else
				(ego posn: 150 180)
			)
		)
		(ego init:)
		(NormalEgo)
		(ego illegalBits: cWHITE)
		(light init:)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			((& (ego onControl: origin) cBROWN)
				(self newRoom: 36)
			)
			((== (ego onControl: origin) cBLUE) 0)
			(script
				(script doit:)
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(self newRoom: nRoom)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			(
				(or
					(Said 'look,check/hole')
					(Said 'look,check<in/hole')
					(MouseClaimed event 32 70 46 84)
				)
				(Print 78 0)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,cave]')
						(Print 78 1)
					)
					((Said '/path')
						(Print 78 2)
					)
				)
			)
			((Said 'go,enter,walk,crawl/hole')
				(if (== (ego script?) 779)
					(Print 78 3)
				else
					(Print 78 4)
				)
			)
			(
				(and
					(== (event type?) mouseDown)
					(& (event modifiers?) shiftDown)
					(== (OnControl PMAP (event x?) (event y?)) cWHITE)
				)
				(Print 78 5)
			)
			((Said '/stalactite,stalactite>')
				(cond 
					((Said 'take,bend')
						(Print 78 6)
					)
					((Said 'look,check')
						(Print 78 5)
					)
				)
			)
		)
	)
)

(instance light of NewFeature
	(properties
		x 47
		y 86
		noun '/light,sunlight'
		nsTop 82
		nsLeft 35
		nsBottom 90
		nsRight 60
		description {light}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The light shines through a small hole.}
	)
)
