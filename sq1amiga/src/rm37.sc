;;; Sierra Script 1.0 - (do not remove this comment)
(script# 37)
(include sci.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm37 0
)

(instance rm37 of SQRoom
	(properties
		picture 37
		style $0008
		horizon 93
		north 38
		east 19
		south 338
		west 138
	)
	
	(method (init)
		(if (not (Btst 68)) (LoadMany 132 810 409))
		(shipHull init:)
		(doorway init:)
		(windshield init:)
		(engines init:)
		(rocks init:)
		(ridge init:)
		(ridgeEdge init:)
		(mountains init:)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						319
						91
						303
						91
						286
						102
						250
						108
						223
						114
						227
						117
						220
						120
						194
						121
						191
						128
						181
						132
						169
						130
						163
						129
						154
						133
						148
						143
						138
						147
						154
						146
						164
						148
						160
						159
						149
						165
						140
						161
						115
						163
						101
						158
						58
						160
						43
						155
						54
						151
						28
						145
						20
						141
						72
						122
						78
						116
						0
						96
						0
						0
						319
						0
					yourself:
				)
		)
		(Load rsVIEW 10)
		(switch prevRoomNum
			(14
				(= style -32761)
				(ego
					ignoreActors:
					normal: 0
					view: 12
					init:
					hide:
					loop: 0
					cel: 0
					posn: 161 130
					cycleSpeed: 15
					illegalBits: 0
					signal: 16384
				)
				(self setScript: exitShip)
			)
			(north
				(door cel: 3 init: stopUpd:)
				(ego x: 307 init:)
			)
			(else 
				(door cel: 3 init: stopUpd:)
				(ego init:)
			)
		)
		(self setRegions: 704)
		(super init:)
		(if (not (Btst 7))
			(Bset 7)
			(smoke init: setCycle: Fwd)
		)
		(if (not (ego has: 6)) (glass init:))
		(if (not (ego has: 3)) (kit init:))
		(= currentFloor 2)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((& (ego onControl: 1) $0002) (self setScript: walkAwayFromEdge))
		)
		(super doit: &rest)
	)
	
	(method (newRoom n)
		(if
		(or (cast contains: grell) (== n north) (Btst 68))
			(super newRoom: n)
		else
			(Bset 68)
			(self setScript: grellYells 0 n)
		)
	)
)

(instance smoke of Prop
	(properties
		x 195
		y 57
		description {exhaust fumes}
		lookStr {The spent drives of the pod vent fumes after the long escape from the Arcada.}
		view 137
		cycleSpeed 15
		detailLevel 2
	)
)

(instance door of Prop
	(properties
		x 126
		y 98
		description {ship's hatch}
		lookStr {This is the door of the escape pod. Due to its special design, the door retained a reserve cache of power sufficient to open one more time. Now, however, it will no longer function.}
		view 137
		loop 1
		priority 12
		signal $4010
		cycleSpeed 26
	)
	
	(method (cue)
		(soundFx stop:)
		(self stopUpd:)
		(exitShip cue:)
	)
)

(instance glass of Prop
	(properties
		x 85
		y 152
		description {piece of glass}
		sightAngle 90
		approachX 73
		approachY 162
		lookStr {A chunk of the highly reflective windscreen rests on the sand at the front of the pod. It, along with your teeth, was jarred loose as a result of the the landing's impact.}
		view 137
		loop 2
		cycleSpeed 8
	)
	
	(method (init)
		(self approachVerbs: 3)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(curRoom setScript: getGlass)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance grell of Prop
	(properties
		x 248
		y 167
		view 420
		cycleSpeed 8
	)
)

(instance kit of View
	(properties
		x 142
		y 118
		description {emergency survival kit}
		sightAngle 90
		approachX 172
		approachY 125
		lookStr {The rugged landing liberated the survival kit from its mounting fixture.}
		view 137
		loop 3
	)
	
	(method (init)
		(self approachVerbs: 3)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (curRoom setScript: getKit))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance shipHull of Feature
	(properties
		description {pod's hull}
		onMeCheck $4000
		lookStr {The hull of the pod has an exquisite sheen. Too bad it is destined to remain a monument to your visit.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 37 0))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance doorway of Feature
	(properties
		description {hatchway}
		onMeCheck $2000
		lookStr {This is the entry way to the pod.}
	)
	
	(method (doVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(super doVerb: &rest)
	)
)

(instance ridgeEdge of Feature
	(properties
		description {ridge's edge}
		onMeCheck $0002
		lookStr {Your highly-honed preservation instincts tell you that going near the edge would be disasterous.}
	)
	
	(method (doVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(super doVerb: &rest)
	)
)

(instance windshield of Feature
	(properties
		description {pod's windshield}
		onMeCheck $1000
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 37 1))
			(2 (Print 37 2))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance engines of Feature
	(properties
		y 60
		description {pod's engines}
		onMeCheck $0800
		lookStr {The engines seem to have performed their jobs well. They will now stand silent for eternity.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 37 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rocks of Feature
	(properties
		description {rocks}
		onMeCheck $0400
		lookStr {The rocks lying on the ground here are made of crumbling sandstone.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 37 4))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ridge of Feature
	(properties
		description {ridge}
		onMeCheck $0200
		lookStr {This ridge of sand helped stop the pod.}
	)
	
	(method (doVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(super doVerb: &rest)
	)
)

(instance mountains of Feature
	(properties
		description {mountains}
		onMeCheck $0080
		lookStr {In the distance rises a unique formation of mountains. They look to be hundreds of kilometers away.}
	)
	
	(method (doVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(super doVerb: &rest)
	)
)

(instance exitShip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(door init:)
				(= cycles 3)
			)
			(1
				(soundFx number: 324 loop: 1 play:)
				(door setCycle: CT 2 1 self)
			)
			(2
				(door setCycle: End door)
				(ego show: setCycle: End self)
			)
			(3 0)
			(4
				(NormalEgo 0 1 61)
				(ego normal: 1 loop: 2)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getGlass of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 0 self)
			)
			(1
				(ego
					view: 10
					setLoop: 2
					cel: 0
					cycleSpeed: 20
					setCycle: CT 2 1 self
				)
			)
			(2
				(glass dispose:)
				(SolvePuzzle 3 141)
				(ego get: 6)
				(ego setCycle: End self)
			)
			(3
				(HandsOn)
				(NormalEgo 0 1 61)
				(ego loop: 3)
				(self dispose:)
			)
		)
	)
)

(instance getKit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 270 self)
			)
			(1
				(ego
					view: 10
					setLoop: 5
					cel: 0
					cycleSpeed: 20
					setCycle: CT 1 1 self
				)
			)
			(2
				(kit dispose:)
				(SolvePuzzle 2 140)
				(ego get: 3)
				(ego cel: 3 setCycle: End self)
			)
			(3
				(HandsOn)
				(NormalEgo 0 1 61)
				(ego loop: 1)
				(self dispose:)
			)
		)
	)
)

(instance walkAwayFromEdge of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 37 5)
				(ego setMotion: PolyPath (ego x?) (+ (ego y?) 10) self)
			)
			(1
				(Print 37 6)
				(self dispose:)
				(HandsOn)
			)
		)
	)
)

(instance grellYells of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(soundFx number: 810 loop: 1 play:)
				(grell init: setCycle: End self)
			)
			(1
				(soundFx number: 409 loop: 1 play:)
				(grell cel: 7)
				(= cycles (if howFast 7 else 2))
			)
			(2 (grell setCycle: End self))
			(3
				(grell setCycle: CT 6 -1 self)
			)
			(4 (grell setCycle: End self))
			(5
				(grell cel: 7)
				(= cycles (if howFast 9 else 2))
			)
			(6
				(grell cel: 8)
				(= cycles (if howFast 5 else 2))
			)
			(7
				(grell cel: 7)
				(= cycles (if howFast 12 else 2))
			)
			(8 (curRoom newRoom: register))
		)
	)
)
