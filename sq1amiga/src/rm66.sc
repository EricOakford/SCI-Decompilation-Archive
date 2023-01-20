;;; Sierra Script 1.0 - (do not remove this comment)
(script# 66)
(include sci.sh)
(use Main)
(use Intrface)
(use Deltaur)
(use SQRoom)
(use SQEgo)
(use Elevator)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm66 0
)

(local
	blastX
	blastY
	[withGPts 38] = [0 189 0 0 319 0 319 104 0 104 0 110 13 110 2 119 2 134 128 134 101 147 91 147 33 176 91 176 147 134 169 134 186 142 319 142 319 189]
	[withoutGPts 48] = [0 189 0 0 319 0 319 102 308 105 304 110 286 110 280 104 0 104 0 110 13 110 2 119 2 134 128 134 104 149 88 149 39 175 91 175 112 158 144 134 169 134 186 142 319 142 319 189]
)
(instance rm66 of SQRoom
	(properties
		lookStr {You're in a brightly-colored hallway aboard the Deltaur. Two elevators dominate the upper level.}
		picture 66
		east 62
		west 57
	)
	
	(method (init)
		(self setRegions: 703)
		(HandsOff)
		(Load rsSOUND 312)
		(LoadMany 128 415 66 166 479 419)
		(withG points: @withoutGPts size: 24)
		(withoutG points: @withGPts size: 19)
		(if (not (Btst 58))
			(self addObstacle: withG)
			(standingSarienHead init: standingSarien setLoop: 8)
			(standingSarien
				init:
				level: 1
				shootEgo: shootTheEgo1
				blastID: blast1
				regionPathID: 0
				dead: 0
				posn: 292 106
				loop: 2
				ignoreActors: 0
			)
			(if (== (DeltaurRegion egoStatus?) 1)
				(standingSarien approachVerbs: 5 12 11)
			)
		else
			(self addObstacle: withoutG)
		)
		(= currentFloor 1)
		(if (== prevRoomNum east)
			(ego posn: 310 116 loop: 1)
			(HandsOn)
			(= style 2)
		)
		(features add: bimActivate eachElementDo: #init)
		(switch prevRoomNum
			(57
				(ego loop: 0 posn: 9 109)
				(if (== (DeltaurRegion egoStatus?) 0)
					(standingSarien shotsFired: 3)
				)
			)
			(61 (ego posn: 60 175))
		)
		(super init: &rest)
		(DeltaurRegion eDoor2: e2Door)
		(DeltaurRegion eDoor1: e1Door)
		(e1Door
			view: 166
			loop: 0
			posn: 218 100
			description: {left elevator door}
			lookStr:
				{It's an elevator, much like the ones you used to waste time in aboard the Arcada.}
			whereTo: 60
			level: 1
			setPri: 7
			exiting: (if (== prevRoomNum 60) else (< prevRoomNum 10))
			polyCode: 0
			init:
		)
		(e2Door
			view: 166
			loop: 0
			posn: 290 101
			locked: (not (Btst 58))
			lockStr:
				{The right elevator is identical to the left except for the ugly green guy standing in front of it.}
			setPri: 7
			lookStr: {This is an elevator.}
			description: {right elevator door}
			whereTo: 67
			level: 1
			exiting: (== prevRoomNum 67)
			polyCode: 0
			init:
			approachVerbs: 2 3
		)
		(switch prevRoomNum
			(57
				(self setScript: fromLaundry)
			)
			(61
				(self setScript: fromHallwayC)
			)
			(else  (ego ignoreActors: 0))
		)
		(ego init:)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script 0)
			((ego script?) 0)
			(
				(and
					(cast contains: standingSarien)
					(not (& (ego illegalBits?) $0002))
				)
				(ego observeControl: 2)
			)
			(
				(and
					(not (cast contains: standingSarien))
					(& (ego illegalBits?) $0002)
				)
				(ego ignoreControl: 2)
			)
			((e1Door inFront:) (e1Door open:))
			((e2Door inFront:) (e2Door open:))
			((& (= temp0 (ego onControl: 1)) $0008) (self setScript: toLaundry))
			((& temp0 $0010) (curRoom newRoom: 61))
		)
		(super doit:)
	)
	
	(method (dispose)
		(ego ignoreControl: 2)
		(if (standingSarien dead?) (Bset 58))
		(super dispose:)
	)
	
	(method (notify)
		(if (or (== prevRoomNum 60) (== prevRoomNum 67))
			(HandsOff)
		)
	)
)

(instance withG of Polygon
	(properties
		type $0002
	)
)

(instance withoutG of Polygon
	(properties
		type $0002
	)
)

(instance standingSarien of sarienGuard
	(properties
		description 0
		approachX 269
		approachY 114
		lookStr 0
	)
	
	(method (doit)
		(if
			(and
				(not guardLocked)
				(not script)
				(== level currentFloor)
				(not (ego script?))
				(not (e1Door busy?))
				(not (e2Door busy?))
				(not dead)
				(!= (DeltaurRegion egoStatus?) 1)
				(not (curRoom script?))
			)
			(self setScript: shootEgo)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb theItem)
		(if (and (== theVerb 4) (== theItem 12))
			(e2Door locked: 0)
			((curRoom obstacles?) delete: withG)
			(curRoom addObstacle: withoutG)
		)
		(super doVerb: theVerb theItem &rest)
	)
)

(instance standingSarienHead of Head
	(properties
		view 66
		loop 8
		cycleSpeed 216
	)
)

(instance blast1 of Prop
	(properties
		view 479
		loop 15
		priority 15
		signal $4010
		cycleSpeed 6
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
			(Print 66 0)
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
			(Print 66 0)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance shootTheEgo1 of Script
	(properties)
	
	(method (changeState newState &tmp egoX temp1)
		(switch (= state newState)
			(0
				(client cel: 0 setMotion: 0 view: 415)
				((client _head?) hide:)
				(client setLoop: (proc703_2 client ego))
				(= ticks 18)
			)
			(1
				(if (< (+ (client shotsFired?) 1) 5)
					(client shotsFired: (+ (client shotsFired?) 1))
					(= register 0)
				else
					(= register 1)
					(HandsOff)
				)
				(if (== (client view?) 415)
					(sarienShot play:)
					(client cel: (- (client lastCel:) 2) setCycle: End self)
				else
					(client view: 415 setMotion: 0 cel: 0 setCycle: End self)
				)
			)
			(2
				(if register
					(= egoX (ego x?))
					(= temp1 (- (ego y?) 35))
				else
					(switch (Random 1 2)
						(1
							(= egoX (- (ego nsLeft?) (Random 1 5)))
						)
						(2
							(= egoX (+ (ego nsRight?) (Random 1 5)))
						)
					)
					(switch (Random 1 2)
						(1
							(= temp1 (- (ego nsTop?) (Random 1 5)))
						)
						(2
							(= temp1 (+ (ego nsBottom?) (Random 1 5)))
						)
					)
				)
				((client blastID?) init:)
				(if (OneOf (client loop?) 0 2 4)
					((client blastID?) setLoop: 1)
				else
					((client blastID?) setLoop: 2)
				)
				((client blastID?)
					ignoreActors: 1
					view: 479
					posn: egoX temp1
					cel: 0
					setCycle: End self
				)
			)
			(3
				((client blastID?) dispose:)
				(= blastX (client x?))
				(= blastY (client y?))
				(if (and register (not (ego script?)))
					(curRoom setScript: (ScriptID 707 1))
				)
				(= seconds 2)
			)
			(4 (self dispose:))
		)
	)
)

(instance fromLaundry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: 1 setMotion: PolyPath 48 112 self)
			)
			(1
				(if (Btst 65) (Bclr 65) (Print 66 1))
				(ego ignoreActors: 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance toLaundry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: 1 setMotion: MoveTo 0 109 self)
			)
			(1 (curRoom newRoom: 57))
		)
	)
)

(instance fromHallwayC of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: 1 setMotion: MoveTo 90 161 self)
			)
			(1
				(ego ignoreActors: 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance bimWalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bim
					posn: -10 189
					setLoop: 1
					setStep: 5 2
					setCycle: Fwd
					setMotion: MoveTo 335 189 self
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance bimActivate of RegionFeature
	(properties
		onMeCheck $0400
		level 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(11
				(if (not (bim script?))
					(bim init: setScript: bimWalk)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bim of Actor
	(properties
		view 166
		loop 1
		priority 15
		signal $4010
	)
)

(instance sarienShot of Sound
	(properties
		number 312
	)
)
