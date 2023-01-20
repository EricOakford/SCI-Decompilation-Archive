;;; Sierra Script 1.0 - (do not remove this comment)
(script# 62)
(include sci.sh)
(use Main)
(use Intrface)
(use Deltaur)
(use SQRoom)
(use Elevator)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm62 0
)

(local
	[upperPts 64] = [0 0 319 0 319 189 0 189 0 90 67 90 108 83 165 83 149 92 133 92 79 123 126 123 168 100 181 94 174 83 216 83 221 90 296 90 311 76 279 76 259 81 203 81 186 71 140 71 133 75 108 75 121 71 69 71 61 75 48 75 49 72 0 72]
	[lowerPts 30] = [0 189 0 0 319 0 319 189 71 189 78 186 292 186 309 166 284 166 266 175 113 175 59 175 46 178 63 183 47 189]
)
(instance rm62 of SQRoom
	(properties
		lookStr {This seems to be just another visually confusing hallway between someplace and someplace else aboard the Deltaur.}
		picture 62
		west 66
	)
	
	(method (init)
		(self setRegions: 703)
		(LoadMany 128 62 162)
		(features
			add: lights portWindow
			eachElementDo: #init
			doit:
		)
		(switch prevRoomNum
			(west
				(ego ignoreActors: 0 posn: 10 75)
				(= currentFloor 1)
				(= style 3)
			)
			(61
				(ego ignoreActors: 0 posn: 20 243)
				(= style 4)
				(= currentFloor 2)
			)
			(else 
				(ego ignoreActors: 0 posn: 98 122)
				(= style 7)
				(= currentFloor 1)
			)
		)
		(upperPoly1 points: @upperPts size: 32)
		(lowerPoly1 points: @lowerPts size: 15)
		(if (== currentFloor 1)
			(self addObstacle: upperPoly1)
		else
			(self addObstacle: lowerPoly1)
		)
		(powerUnit setCycle: Fwd init:)
		(super init:)
		(DeltaurRegion eDoor1: e1Door)
		(DeltaurRegion eDoor2: e2Door)
		(e1Door
			view: 162
			locked: 0
			loop: 0
			whereTo: e2Door
			posn: 236 79
			level: 1
			setPri: 4
			exiting: 0
			polyCode: goUp
			lookStr: {An elevator door to the lower level}
			init:
		)
		(e2Door
			view: 162
			loop: 0
			locked: 0
			posn: 235 172
			setPri: 13
			whereTo: e1Door
			level: 2
			polyCode: goDown
			exiting: 0
			lookStr: {An elevator door to the upper level}
			init:
		)
		(switch prevRoomNum
			(63
				(self setScript: fromHallwayE)
			)
			(61
				(self setScript: fromHallwayC)
			)
			(else  (HandsOn) (ego show:))
		)
		(ego init:)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script 0)
			((e1Door inFront:) (e1Door open:))
			((e2Door inFront:) (e2Door open:))
			((& (= temp0 (ego onControl: 1)) $0004) (self setScript: toHallwayC))
			((and (& temp0 $0002) (== currentFloor 1)) (self setScript: toHallwayE))
		)
		(super doit:)
	)
)

(instance fromHallwayE of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: 1 setMotion: PolyPath 130 103 self)
			)
			(1
				(ego ignoreActors: 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance fromHallwayC of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreActors: 1
					loop: 6
					setMotion: MoveTo 78 179 self
				)
			)
			(1
				(ego ignoreActors: 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance toHallwayC of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: 1 setMotion: PolyPath -6 237 self)
			)
			(1 (curRoom newRoom: 61))
		)
	)
)

(instance toHallwayE of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: 1)
				(curRoom newRoom: 63)
			)
		)
	)
)

(instance goDown of Code
	(properties)
	
	(method (doit)
		((curRoom obstacles?) delete: upperPoly1)
		((curRoom obstacles?) add: lowerPoly1)
		(= currentFloor 2)
	)
)

(instance goUp of Code
	(properties)
	
	(method (doit)
		((curRoom obstacles?) delete: lowerPoly1)
		((curRoom obstacles?) add: upperPoly1)
		(= currentFloor 1)
	)
)

(instance e1Door of Elevator
	(properties
		description {elevator door}
		sightAngle 90
		priority 2
		signal $4010
	)
	
	(method (doVerb theVerb)
		(if (!= currentFloor level)
			(Print 62 0)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance e2Door of Elevator
	(properties
		description {elevator door}
		sightAngle 90
		priority 2
		signal $4010
	)
	
	(method (doVerb theVerb)
		(if (!= currentFloor level)
			(Print 62 0)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance powerUnit of Prop
	(properties
		x 166
		y 156
		description {lights}
		lookStr {These egg-shaped lights - if that's what they are - are just another example of the rather whimsical designs you've observed on this ship. For ugly, smelly, green, planet- destroying murderers, the Sariens appear to have a great sense of aesthetics.}
		view 162
		loop 1
		signal $0010
		cycleSpeed 6
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 2)
			(super doVerb: theVerb &rest)
		else
			(Print lookStr)
		)
	)
)

(instance upperPoly1 of Polygon
	(properties
		type $0002
	)
)

(instance upperPoly2 of Polygon
	(properties
		type $0002
	)
)

(instance lowerPoly1 of Polygon
	(properties
		type $0002
	)
)

(instance lowerPoly2 of Polygon
	(properties
		type $0002
	)
)

(instance portWindow of RegionFeature
	(properties
		x 26
		y 141
		description {window}
		onMeCheck $2000
		lookStr {It's a port of some kind. You've seen more empty space than you care to ever again.}
		level 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(12 (Print 62 1))
			(11
				(switch (Random 1 1000)
					(999 (Print 62 2))
					(else  (Print 62 3))
				)
			)
			(3 (Print 62 4))
			(4 (Print 62 5))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lights of RegionFeature
	(properties
		x 167
		y 137
		description {lights}
		onMeCheck $4000
		lookStr {Those are some weird globes. They aren't operating at this time.}
		level 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(12 (Print 62 6))
			(11 (Print 62 7))
			(3 (Print 62 8))
			(4 (Print 62 5))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
