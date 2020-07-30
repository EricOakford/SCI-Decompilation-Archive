;;; Sierra Script 1.0 - (do not remove this comment)
(script# 5)
(include game.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use Elevator)
(use Osc)
(use Polygon)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm5 0
)

(local
	[upperPts 36] = [0 0 319 0 319 189 0 189 0 74 32 67 257 67 263 48 188 48 184 50 169 50 169 28 154 28 154 50 139 50 135 45 31 45 0 52]
	[lowerPts 40] = [49 157 76 169 107 156 260 156 319 167 319 189 0 189 0 0 319 0 319 147 243 132 189 133 184 144 171 144 166 128 156 128 151 144 139 144 135 134 110 134]
)
(instance rm5 of SQRoom
	(properties
		lookStr {This is another hallway aboard the Arcada. There is an elevator door nearby.}
		picture 5
		east 6
		west 4
	)
	
	(method (init)
		(LoadMany VIEW 105 400 4)
		(upperPoly points: @upperPts size: 18)
		(lowerPoly points: @lowerPts size: 20)
		(body init: stopUpd:)
		(alertSign setCycle: Forward init:)
		(= style (if (== prevRoomNum west) 12 else 11))
		(ego init:)
		(super init:)
		(upperDoor
			exiting: (if (not (OneOf prevRoomNum west east)) 1 else 0)
			init:
			whereTo: lowerDoor
			polyCode: changeToUpper
		)
		(lowerDoor
			init:
			whereTo: upperDoor
			polyCode: changeToLower
		)
		(if (== currentFloor 1)
			(switch prevRoomNum
				(west
					(self addObstacle: upperPoly)
				)
				(east
					(if (< (ego y?) 56) (ego y: 56))
					(body approachVerbs: verbLook verbDo verbTalk)
					(upperDoor _approachVerbs: ftrDefault)
					(lowerDoor _approachVerbs: ftrDefault)
					(self
						addObstacle:
							((Polygon new:)
								type: PBarredAccess
								init: 0 0 319 0 319 51 289 58 283 68 319 68 319 189 0 189
								yourself:
							)
					)
				)
			)
		else
			(switch prevRoomNum
				(west
					(upperDoor _approachVerbs: ftrDefault)
					(lowerDoor _approachVerbs: ftrDefault)
					(self
						addObstacle:
							((Polygon new:)
								type: PBarredAccess
								init: 0 0 319 0 319 189 0 189 0 187 36 187 56 178 24 161 0 161
								yourself:
							)
					)
				)
				(east
					(self addObstacle: lowerPoly)
				)
			)
		)
		(self setRegions: ARCADA)
		(HandsOn)
	)
	
	(method (doit)
		(cond 
			((== currentFloor 2)
				(if (& (ego onControl:) cRED)
					(ego setPri: 1)
				else
					(ego setPri: -1)
				)
			)
			((& (ego onControl: origin) cCYAN) (ego setPri: 1))
			(else (ego setPri: -1))
		)
		(cond 
			(script 0)
			((lowerDoor inFront:) (lowerDoor open:))
			((upperDoor inFront:) (upperDoor open:))
		)
		(super doit:)
	)
)

(instance changeToUpper of Code
	(properties)
	
	(method (doit)
		(= currentFloor 1)
		((curRoom obstacles?) delete: lowerPoly add: upperPoly)
	)
)

(instance changeToLower of Code
	(properties)
	
	(method (doit)
		(= currentFloor 2)
		((curRoom obstacles?) delete: upperPoly add: lowerPoly)
	)
)

(instance searchBody of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 0 self)
			)
			(1
				(if register
					(Printf 5 0
						(if (not (ego has: iKeyCard)) {a keycard.} else {nothing.})
					)
					(if (not (ego has: iKeyCard))
						(SolvePuzzle 1 f5GetKeyCard)
						(ego get: iKeyCard)
					)
					(= state 2)
				)
				(ego
					view: 4
					loop: 4
					cycleSpeed: 5
					moveSpeed: 5
					cel: register
					setCycle: (if register BegLoop else EndLoop) self
				)
			)
			(2
				(ego
					loop: (+ (ego loop?) 2)
					cel: 0
					setCycle: Oscillate 2 self
				)
				(= state 0)
				(= register 3)
			)
			(3
				(HandsOn)
				(= state -1)
				(= register 0)
				(NormalEgo 7 0 60)
				(self dispose:)
			)
		)
	)
)

(instance body of View
	(properties
		x 301
		y 54
		description {Jerry}
		sightAngle 45
		approachX 317
		approachY 58
		lookStr {It's all that remains of Jerry, one of the few technodudes aboard who sometimes tolerated your company. Your low clearance excluded you from visiting him during the performance of his duties in the elegant lower level airlock of the Arcada.}
		view 400
		loop 1
		cel 5
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(if (and (== prevRoomNum 6) (== currentFloor 1))
			(switch theVerb
				(verbDo
					(if (!= _approachVerbs ftrDefault)
						(curRoom setScript: searchBody)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(verbTalk
					(Print 5 1)
				)
				(verbSmell
					(Print 5 2)
				)
				(verbTaste
					(Print 5 3)
				)
				(verbUse
					(switch theItem
						(iBuckazoid
							(Print 5 4)
						)
						(iCartridge
							(Print 5 5)
						)
						(iKeyCard
							(Print 5 6)
						)
						(iWidget
							(Print 5 7)
						)
						(iGadget
							(Print 5 8)
						)
						(else 
							(super doVerb: theVerb theItem)
						)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print 5 9)
		)
	)
)

(instance alertSign of Prop
	(properties
		x 225
		y 102
		description {alert sign}
		sightAngle 90
		lookStr {Gee! The sign seems to be flashing. Could it be a warning of some kind? Who knows? You're always slower than normal after a nap.}
		view 105
		loop 2
		cycleSpeed 8
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 2)
			(super doVerb: theVerb &rest)
		else
			(Print 5 10)
		)
	)
)

(instance upperDoor of Elevator
	(properties
		x 162
		y 46
		description {elevator door}
		sightAngle 45
		lookStr {These appear to be doors to an elevator.}
		view 105
		cycleSpeed 4
		level 1
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor level)
			(switch theVerb
				(verbLook
					(Print 5 11)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print 5 10)
		)
	)
)

(instance lowerDoor of Elevator
	(properties
		x 162
		y 138
		z 1
		description {elevator door}
		sightAngle 45
		lookStr {elevator}
		view 105
		cycleSpeed 4
		level 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor level)
			(switch theVerb
				(verbLook
					(Print 5 12)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print 5 10)
		)
	)
)

(instance upperPoly of Polygon
	(properties
		type PBarredAccess
	)
)

(instance lowerPoly of Polygon
	(properties
		type PBarredAccess
	)
)
