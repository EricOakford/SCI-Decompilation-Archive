;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6)
(include game.sh)
(use Main)
(use Intrface)
(use Arcada)
(use SQRoom)
(use Elevator)
(use Osc)
(use Polygon)
(use Feature)
(use LoadMany)
(use DPath)
(use Motion)
(use Actor)
(use System)

(public
	rm6 0
)

(local
	[upperPts1 24] = [0 0 319 0 319 78 219 78 133 54 74 53 68 33 56 33 54 53 42 53 38 47 0 47]
	[upperPts2 12] = [0 66 112 66 215 94 319 94 319 189 0 189]
	[lowerPts1 40] = [0 147 0 0 319 0 319 131 269 151 211 151 192 139 194 126 177 126 181 138 160 151 96 151 85 158 74 158 69 138 58 138 55 158 42 158 32 151 21 151]
	[lowerPts2 20] = [0 167 49 176 159 176 165 173 211 173 219 176 286 176 319 159 319 189 0 189]
	[lowerPts3 8] = [95 160 148 160 148 165 95 165]
	local104
	local105
)
(instance rm6 of SQRoom
	(properties
		lookStr {More hallway extends through the Arcada. There is an elevator door nearby.}
		picture 6
		style FADEOUT
		east 3
		west 5
	)
	
	(method (init)
		(LoadMany 128 400 106 4)
		(upper1 points: @upperPts1 size: 12)
		(upper2 points: @upperPts2 size: 6)
		(lower1 points: @lowerPts1 size: 20)
		(lower2 points: @lowerPts2 size: 10)
		(lower3 points: @lowerPts3 size: 4)
		(deadMan init:)
		(deadMan2 init:)
		(features add: labDoor eachElementDo: #init)
		(if (== currentFloor 1)
			(self addObstacle: upper1 upper2)
			((ScriptID ARCADA 0)
				sarienEntryDir: 2
				s1startY: 81
				s2startY: 93
				s1gotoX: 237
				s1gotoY: 82
				s2gotoX: 231
				s2gotoY: 94
			)
		else
			(self addObstacle: lower1 lower2 lower3)
			(deadMan startUpd: approachVerbs: verbLook verbDo)
			(deadMan2 startUpd: approachVerbs: verbLook verbDo)
			(if (== prevRoomNum 3) (ego setPri: 3))
			((ScriptID ARCADA 0)
				sarienEntryDir: 1
				s1startY: 149
				s2startY: 163
				s1gotoX: 21
				s1gotoY: 158
				s2gotoX: 27
				s2gotoY: 171
			)
		)
		(self setRegions: ARCADA)
		((ScriptID ARCADA 0) safeCode: egoSafe)
		(ego init:)
		(if (OneOf prevRoomNum west east)
			(= style (if (== prevRoomNum west) 12 else 11))
		else
			(ego setPri: 3 posn: 183 140)
			(self setScript: enterFrom7)
		)
		(super init:)
		(upperDoor
			init:
			whereTo: lowerDoor
			polyCode: changeToUpper
		)
		(lowerDoor
			light: elevatorLight
			init:
			whereTo: upperDoor
			polyCode: changeToLower
		)
		(alertLight setCycle: Forward init:)
		(if (OneOf prevRoomNum west east) (HandsOn))
	)
	
	(method (doit)
		(if (== currentFloor 2)
			(if (> (ego x?) 167)
				(ego setPri: 3)
			else
				(ego setPri: -1)
			)
		)
		(cond 
			(script 0)
			((& (ego onControl: origin) cCYAN) (HandsOff) (curRoom newRoom: 7))
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
		(deadMan stopUpd: _approachVerbs: ftrDefault)
		(deadMan2 stopUpd: _approachVerbs: ftrDefault)
		((curRoom obstacles?) delete: lower1 lower2 lower3)
		((curRoom obstacles?) add: upper1 upper2)
	)
)

(instance changeToLower of Code
	(properties)
	
	(method (doit)
		(= currentFloor 2)
		(deadMan startUpd: approachVerbs: verbLook verbDo verbTalk)
		(deadMan2 startUpd: approachVerbs: verbLook verbDo verbTalk)
		((curRoom obstacles?) delete: upper1 upper2)
		((curRoom obstacles?) add: lower1 lower2 lower3)
	)
)

(instance egoSafe of Code
	(properties)
	
	(method (doit &tmp ret)
		(= ret 0)
		(if
			(or
				(lowerDoor busy?)
				(upperDoor busy?)
				(!= currentFloor sarienFloor)
				(& (ego onControl: origin) (| cGREEN cCYAN))
			)
			(= ret 1)
		)
		(return ret)
	)
)

(instance enterFrom7 of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if local104
			(if (ego moveHead?) (ego moveHead: 0))
			(cond 
				(
					(and
						(>=
							220
							(= temp0
								(GetAngle
									(ego x?)
									(ego y?)
									(panicDroid x?)
									(panicDroid y?)
								)
							)
						)
						(>= temp0 135)
					)
					((ego _head?) cel: 2)
				)
				((< temp0 135) ((ego _head?) cel: 4))
				(else ((ego _head?) cel: 5))
			)
		)
		(if (and (not local104) (not (ego moveHead?)))
			(ego moveHead: 1)
		)
	)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 0 (= cycles 3))
			(1
				(HandsOff)
				(ego setLoop: 2)
				(if
				(and (>= howFast 1) (not (ArcadaCheck 553 2)))
					(= local104 1)
					(panicDroid init: setCycle: Forward)
					((ScriptID ARCADA 0)
						rFlag1: (| ((ScriptID ARCADA 0) rFlag1?) $0002)
					)
					(ego setMotion: MoveTo 183 155)
				else
					(ego setMotion: MoveTo 183 155 self)
				)
			)
			(2
				(= local104 0)
				(HandsOn)
				((ScriptID ARCADA 0)
					sarienState: 2
					setCountDown: (Random 2 4)
				)
				(NormalEgo)
				(ego ignoreActors: TRUE)
				(self dispose:)
			)
		)
	)
)

(instance searchBody of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 180 self)
			)
			(1
				(if register
					(Printf 6 0 ((CueObj client?) description?))
					(= state 2)
				)
				(ego
					view: 4
					loop: (if (< (ego x?) ((CueObj client?) x?)) 1 else 0)
					cycleSpeed: 5
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
				(NormalEgo 2 0 60)
				(self dispose:)
			)
		)
	)
)

(instance deadMan of View
	(properties
		x 121
		y 164
		description {David}
		sightAngle 45
		approachX 137
		approachY 162
		lookStr {A cursory glance indicates that Dave, a lab technician, is dead. Normally you wouldn't be able to tell, except that his intestines are hanging out of the scorched opening where his abdominal wall used to be. You remember that he was forced to serve in the Xenon National Guard (but he wasn't bitter).}
		view 400
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(if (== currentFloor 2)
			(switch theVerb
				(verbDo
					(if (== currentFloor 1)
						(Print 6 1)
					else
						(curRoom setScript: searchBody)
					)
				)
				(verbTalk
					(Print 6 2)
				)
				(verbSmell
					(Print 6 3)
				)
				(verbTaste
					(Print 6 4)
				)
				(verbUse
					(switch theItem
						(iBuckazoid
							(Print 6 5)
						)
						(iCartridge
							(Print 6 6)
						)
						(iKeyCard
							(Print 6 7)
						)
						(iWidget
							(Print 6 8)
						)
						(iGadget
							(Print 6 9)
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
			(Print 6 10)
		)
	)
)

(instance deadMan2 of View
	(properties
		x 188
		y 179
		description {Blanche}
		sightAngle 45
		approachX 178
		approachY 176
		lookStr {This is one of the head research scientists, Blanche. You wanted to get to know her a little better. However, seeing her ruptured chest wall reveals more about her than you were hoping to learn.}
		view 400
		cel 1
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(if (== currentFloor 2)
			(switch theVerb
				(verbDo
					(if (== currentFloor 1)
						(Print 6 11)
					else
						(curRoom setScript: searchBody)
					)
				)
				(verbTalk
					(Print 6 2)
				)
				(verbTaste
					(Print 6 12)
				)
				(verbSmell
					(Print 6 13)
				)
				(verbUse
					(switch theItem
						(iBuckazoid
							(Print 6 14)
						)
						(iCartridge
							(Print 6 15)
						)
						(iKeyCard
							(Print 6 16)
						)
						(iWidget
							(Print 6 17)
						)
						(iGadget
							(Print 6 18)
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
			(Print 6 10)
		)
	)
)

(instance elevatorLight of View
	(properties
		description {light}
		sightAngle 45
		lookStr {An arrow in the elevator light assembly points up.}
		view 106
		loop 2
		signal stopUpdOn
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 2)
			(super doVerb: theVerb &rest)
		else
			(Print 6 10)
		)
	)
)

(instance alertLight of Prop
	(properties
		x 280
		y 45
		description {alert light}
		sightAngle 90
		lookStr {In case you're trying to play this as a text adventure, the sign says 'RED ALERT' and it's flashing.}
		view 106
		loop 1
		signal (| ignrAct fixPriOn)
		cycleSpeed 8
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(super doVerb: theVerb &rest)
		else
			(Print 6 10)
		)
	)
)

(instance upperDoor of Elevator
	(properties
		x 64
		y 48
		description {elevator door}
		sightAngle 45
		lookStr {This is an elevator to one of the other levels of the Arcada.}
		view 106
		cycleSpeed 4
		level 1
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor level)
			(switch theVerb
				(verbLook
					(Print 6 19)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print 6 11)
		)
	)
)

(instance lowerDoor of Elevator
	(properties
		x 65
		y 152
		description {elevator door}
		sightAngle 45
		lookStr {This is an elevator to one of the other levels of the Arcada.}
		view 106
		cycleSpeed 4
		level 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor level)
			(switch theVerb
				(verbLook
					(Print 6 20)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print 6 10)
		)
	)
)

(instance panicDroid of Actor
	(properties
		x 345
		y 143
		description {panicking droid}
		lookStr {A fairly spazzed-out droid is on the loose in your immediate proprioceptic area.}
		yStep 6
		view 414
		cycleSpeed 4
		xStep 10
		moveSpeed 4
	)
	
	(method (init)
		(super init: &rest)
		(theMusic2 number: 357 loop: -1 play: 0)
		(self
			setLoop: 0
			ignoreActors: TRUE
			setMotion: DPath 276 165 45 167 -34 147 self
		)
	)
	
	(method (doit &tmp temp0 temp1 temp2)
		(super doit:)
		(if (> x 167) (self setPri: 3) else (self setPri: -1))
		(if
		(< (= temp0 (Abs (- (panicDroid x?) (ego x?)))) 0)
			(= temp0 0)
		)
		(if (> temp0 90) (= temp0 90))
		(if
			(>
				(= temp1
					(Abs (- (+ (- (ego x?) (panicDroid x?)) 63) 127))
				)
				127
			)
			(= temp1 127)
		)
		(if (< temp1 0) (= temp1 0))
		(theMusic2
			send: 7 10 temp1
			send: 8 10 temp1
			send: 9 10 temp1
			setVol: (- 127 temp0)
		)
		(if (< (- x (ego x?)) 0)
			(if (< (= local105 (- local105 6)) -512)
				(= local105 -512)
			)
			(theMusic2
				send: 7 224 local105
				send: 8 224 local105
				send: 9 224 local105
				send: 11 224 local105
			)
		)
	)
	
	(method (dispose)
		(theMusic2 loop: 0 stop:)
		(super dispose:)
	)
	
	(method (cue)
		(enterFrom7 cue:)
		(self dispose:)
	)
)

(instance labDoor of Feature
	(properties
		x 188
		y 136
		heading 180
		nsTop 96
		nsLeft 172
		nsBottom 138
		nsRight 202
		description {labDoor}
		sightAngle 45
		onMeCheck $0048
		approachX 188
		approachY 149
		lookStr {This is the door to the top secret Star Generator Lab. It's been forcibly ventilated!}
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 2)
			(super doVerb: theVerb &rest)
		else
			(Print 6 10)
		)
	)
)

(instance upper1 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance upper2 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance lower1 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance lower2 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance lower3 of Polygon
	(properties
		type PBarredAccess
	)
)
