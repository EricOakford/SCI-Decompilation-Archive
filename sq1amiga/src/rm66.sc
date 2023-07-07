;;; Sierra Script 1.0 - (do not remove this comment)
(script# 66)
(include game.sh)
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
	withoutGPts = [0 189 0 0 319 0 319 105 292 105 292 94 307 94 307 45 278 45 278 94 289 94 289 105 221 105 221 96 235 96 235 44 204 44 204 96 217 96 217 105 0 104 0 110 13 110 2 119 2 134 128 134 101 147 91 147 33 176 91 176 147 134 169 134 186 142 319 142 319 189]
	withGPts = [0 189 0 0 319 0 319 102 308 105 304 110 286 110 278 105 221 105 221 96 235 96 235 44 204 44 204 96 217 96 217 105 0 104 0 110 13 110 2 119 2 134 128 134 104 149 88 149 39 175 91 175 112 158 144 134 169 134 186 142 319 142 319 189]
)
(instance rm66 of SQRoom
	(properties
		lookStr {You're in a brightly-colored hallway aboard the Deltaur. Two elevators dominate the upper level.}
		picture 66
		east 62
		west 57
	)
	
	(method (init)
		(self setRegions: DELTAUR)
		(HandsOff)
		(Load SOUND 312)
		(LoadMany VIEW 415 66 166 479 419)
		(withG points: @withGPts size: 32)
		(withoutG points: @withoutGPts size: 35)
		(if (not (Btst fElevatorGuardDead))
			(self addObstacle: withG)
			(standingSarienHead init: standingSarien setLoop: 8)
			(standingSarien
				init:
				level: 1
				shootEgo: shootTheEgo1
				blastID: blast1
				regionPathID: 0
				dead: FALSE
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
				(if (== (DeltaurRegion egoStatus?) egoSpacesuit)
					(standingSarien shotsFired: 3)
				)
			)
			(61
				(ego posn: 60 175)
			)
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
			locked: (not (Btst fElevatorGuardDead))
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
			(else
				(ego ignoreActors: FALSE)
			)
		)
		(ego init:)
	)
	
	(method (doit &tmp theControl)
		(cond 
			(script 0)
			((ego script?) 0)
			(
				(and
					(cast contains: standingSarien)
					(not (& (ego illegalBits?) cBLUE))
				)
				(ego observeControl: cBLUE)
			)
			(
				(and
					(not (cast contains: standingSarien))
					(& (ego illegalBits?) cBLUE)
				)
				(ego ignoreControl: 2)
			)
			((e1Door inFront:)
				(e1Door open:)
			)
			((e2Door inFront:)
				(e2Door open:)
			)
			((& (= theControl (ego onControl: origin)) cCYAN)
				(self setScript: toLaundry)
			)
			((& theControl cRED)
				(curRoom newRoom: 61)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(ego ignoreControl: cBLUE)
		(if (standingSarien dead?)
			(Bset fElevatorGuardDead)
		)
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
		type PBarredAccess
	)
)

(instance withoutG of Polygon
	(properties
		type PBarredAccess
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
				(!= (DeltaurRegion egoStatus?) egoWithHelmet)
				(not (curRoom script?))
			)
			(self setScript: shootEgo)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb theItem)
		(if (and (== theVerb verbUse) (== theItem iPulseray))
			(e2Door locked: FALSE)
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
		signal (| ignrAct fixPriOn)
		cycleSpeed 6
	)
)

(instance e1Door of Elevator
	(properties
		description {elevator door}
		sightAngle 90
		priority 2
		signal (| ignrAct fixPriOn)
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
		signal (| ignrAct fixPriOn)
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
	(method (changeState newState &tmp egoX egoY)
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
					(client cel: (- (client lastCel:) 2) setCycle: EndLoop self)
				else
					(client view: 415 setMotion: 0 cel: 0 setCycle: EndLoop self)
				)
			)
			(2
				(if register
					(= egoX (ego x?))
					(= egoY (- (ego y?) 35))
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
							(= egoY (- (ego nsTop?) (Random 1 5)))
						)
						(2
							(= egoY (+ (ego nsBottom?) (Random 1 5)))
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
					ignoreActors: TRUE
					view: 479
					posn: egoX egoY
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				((client blastID?) dispose:)
				(= blastX (client x?))
				(= blastY (client y?))
				(if (and register (not (ego script?)))
					(curRoom setScript: (ScriptID DELTAUR_DEATH 1))
				)
				(= seconds 2)
			)
			(4
				(self dispose:)
			)
		)
	)
)

(instance fromLaundry of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreActors: TRUE
					setMotion: PolyPath 48 112 self
				)
			)
			(1
				(if (Btst fDisguiseMessage)
					(Bclr fDisguiseMessage)
					(Print 66 1)
				)
				(ego ignoreActors: FALSE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance toLaundry of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreActors: TRUE
					setMotion: MoveTo 0 109 self
				)
			)
			(1
				(curRoom newRoom: 57)
			)
		)
	)
)

(instance fromHallwayC of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreActors: TRUE
					setMotion: MoveTo 90 161 self
				)
			)
			(1
				(ego ignoreActors: FALSE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance bimWalk of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bim
					posn: -10 189
					setLoop: 1
					setStep: 5 2
					setCycle: Forward
					setMotion: MoveTo 335 189 self
				)
			)
			(1
				(self dispose:)
			)
		)
	)
)

(instance bimActivate of RegionFeature
	(properties
		onMeCheck cLGREEN
		level 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbTaste
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
		signal (| ignrAct fixPriOn)
	)
)

(instance sarienShot of Sound
	(properties
		number 312
	)
)
