;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11)
(include sci.sh)
(use Main)
(use Intrface)
(use Arcada)
(use Elevator)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm11 0
)

(local
	oldView
	oldStop
	local2
	local3
)
(instance rm11 of Rm
	(properties
		lookStr {This is the airlock of the Arcada.}
		picture 11
		east 12
	)
	
	(method (init)
		(LoadMany 128 9 111)
		(= currentFloor 2)
		(if (not (Btst 16))
			(= local2 1)
			(= local3 1)
			(helmet init: stopUpd:)
			(spaceSuit init: stopUpd:)
		)
		(drawerFeature init:)
		(drawerButton init:)
		(BayButton init:)
		(controlPanel init:)
		(control setCycle: Fwd init:)
		(if (Btst 10) (lights setCycle: Fwd))
		(lights init:)
		(if (Btst 74)
			(closetDoor init: ignoreActors: 1 cel: 6 stopUpd:)
			(helmet approachVerbs: 3 2)
			(spaceSuit approachVerbs: 3 2)
		else
			(closetDoor init: stopUpd:)
		)
		(drawer init: stopUpd:)
		(bigRedDoor
			init:
			setCel: (bigRedDoor lastCel:)
			approachVerbs: 3
		)
		(self
			setRegions: 700
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						4
						100
						22
						100
						22
						113
						4
						115
						4
						126
						14
						150
						58
						179
						93
						187
						172
						187
						294
						150
						289
						135
						255
						126
						218
						107
						194
						104
						195
						92
						218
						84
						173
						76
						127
						89
						121
						86
						78
						97
						98
						105
						72
						112
						56
						108
						48
						112
						38
						114
						35
						98
						6
						98
						6
						0
						319
						0
						319
						189
						0
						189
						0
						0
						4
						0
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 91 162 89 156 116 147 164 170 161 178 145 183 131 183
					yourself:
				)
		)
		(ego init: illegalBits: 0)
		(super init:)
		(if (== prevRoomNum 12)
			(HandsOff)
			(self setScript: enterFromHanger)
		)
		(elevatorDoor
			exiting: (if (!= prevRoomNum 12) 1 else 0)
			light: doorLight
			init:
			setPri: 7
			whereTo: 10
		)
		(if (!= (theMusic number?) 355)
			(theMusic number: 323 loop: -1 play:)
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((elevatorDoor inFront:) (elevatorDoor open:))
		)
		(super doit:)
	)
	
	(method (dispose)
		(if (!= (theMusic number?) 355)
			(theMusic loop: 0 fade:)
		)
		(super dispose:)
	)
	
	(method (notify)
		(if (== prevRoomNum 10) (HandsOff))
	)
)

(instance putOnSpacesuit of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== (ego loop?) 1) (== (ego cel?) 1) local2)
			(= local2 0)
			(spaceSuit _approachVerbs: 26505 dispose:)
		)
		(if
		(and (== (ego loop?) 2) (== (ego cel?) 3) local3)
			(= local3 0)
			(helmet _approachVerbs: 26505 dispose:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(SolvePuzzle 2 136)
				(ego
					view: 9
					setLoop: 1
					cycleSpeed: 8
					cel: 0
					setCycle: End self
				)
			)
			(1
				(ego setLoop: 2 cel: 0 setCycle: End self)
			)
			(2
				(closetDoor setCycle: Beg self)
				(Bclr 74)
			)
			(3
				(soundFx stop:)
				(closetDoor stopUpd:)
				(NormalEgo 3 1 61)
				(Bset 16)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance leaveToHanger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= oldView
					(cond 
						(
							(and
								(ego cycler?)
								((ego cycler?) respondsTo: #vWalking)
							)
							((ego cycler?) vWalking?)
						)
						(local2 0)
						(else 1)
					)
				)
				(= oldStop
					(cond 
						(
							(and
								(ego cycler?)
								((ego cycler?) respondsTo: #vStopped)
							)
							((ego cycler?) vStopped?)
						)
						(local2 60)
						(else 61)
					)
				)
				(ego
					view: 9
					setLoop: (if (== oldView 0) 6 else 7)
					cel: 0
					setCycle: End self
				)
			)
			(1
				(soundFx number: 311 loop: 1 play:)
				(theMusic2 number: 322 loop: 1 play:)
				(bigRedDoor setCycle: Beg self)
			)
			(2
				(ego setPri: (bigRedDoor priority?) setCycle: Beg self)
			)
			(3
				(NormalEgo)
				(bigRedDoor setPri: (- (ego priority?) 1))
				(ego
					ignoreActors: 1
					view: oldView
					setCycle: StopWalk oldStop
					setMotion: MoveTo 220 114 self
				)
			)
			(4
				(ego setMotion: MoveTo 272 114 self)
			)
			(5
				(soundFx number: 311 loop: 1 play:)
				(bigRedDoor setPri: -1 setCycle: End self)
				(if (Btst 10) (theMusic2 fade:))
				(ego setPri: -1 setMotion: MoveTo 306 114 self)
			)
			(6 0)
			(7
				(ego illegalBits: -32768 ignoreActors: 0)
				(curRoom newRoom: 12)
			)
		)
	)
)

(instance enterFromHanger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					posn: 306 114
					setPri: (- (bigRedDoor priority?) 1)
					setMotion: MoveTo 260 114 self
				)
			)
			(1
				(soundFx number: 311 loop: 1 play:)
				(bigRedDoor setCycle: Beg self)
			)
			(2
				(ego setPri: -1 setMotion: MoveTo 213 114 self)
			)
			(3
				(soundFx number: 311 loop: 1 play:)
				(bigRedDoor setCycle: End self)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance openClosetDoor of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== (ego loop?) 1) (== (ego cel?) 1) local2)
			(= local2 1)
			(spaceSuit init: stopUpd:)
		)
		(if
		(and (== (ego loop?) 2) (== (ego cel?) 3) local3)
			(= local3 1)
			(helmet init: stopUpd:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setHeading: 0 self))
			(1
				(ego
					view: 9
					setLoop: (if (Btst 16) 5 else 0)
					cel: 0
					setCycle: CT 2 1 self
				)
			)
			(2
				(soundFx number: 318 loop: 1 play:)
				(ego setCycle: Beg)
				(closetDoor setCycle: End self)
			)
			(3
				(soundFx stop:)
				(Bset 74)
				(closetDoor ignoreActors: 1)
				(if (Btst 16)
					(NormalEgo 0 1 61)
					(ego setMotion: PolyPath 159 80 self)
				else
					(if (not (ArcadaCheck 551 64))
						(Print 11 0)
						((ScriptID 700 0)
							rFlag1: (| ((ScriptID 700 0) rFlag1?) $0040)
						)
					)
					(helmet approachVerbs: 3 2)
					(spaceSuit approachVerbs: 3 2)
					(NormalEgo 3 0 60)
					(= cycles 3)
				)
			)
			(4
				(if (Btst 16)
					(ego
						view: 9
						setLoop: 2
						cel: 10
						cycleSpeed: 8
						setCycle: Beg self
					)
				else
					(HandsOn)
					(self dispose:)
				)
			)
			(5
				(ego setLoop: 1 cel: 15 setCycle: Beg self)
			)
			(6
				(closetDoor setCycle: Beg self)
				(Bclr 74)
				(= local2 1)
				(Bclr 16)
				(NormalEgo 3 0 60)
			)
			(7
				(soundFx stop:)
				(closetDoor stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance openDrawer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 9
					loop: (if (Btst 16) 4 else 3)
					cel: 0
					setCycle: CT 5 1 self
				)
			)
			(1
				(soundFx number: 330 loop: 1 play:)
				(drawer setCycle: (if (drawer cel?) Beg else End) self)
			)
			(2
				(cond 
					((not (drawer cel?)) (ego setCycle: Beg self))
					((not (ego has: 2))
						(Print 11 1)
						(ego get: 2 setCycle: End self)
						(SolvePuzzle 2 137)
						(= state -1)
					)
					(else (Print 11 2) (= cycles 3) (= state 0))
				)
			)
			(3
				(HandsOn)
				(if (Btst 16)
					(NormalEgo 3 1 61)
				else
					(NormalEgo 3 0 60)
				)
				(self dispose:)
			)
		)
	)
)

(instance spaceSuit of View
	(properties
		x 137
		y 76
		description {space suit}
		sightAngle 45
		approachX 159
		approachY 80
		lookStr {A space suit hangs in the closet. It won't be used by anyone else on the ship anymore.}
		view 111
		loop 7
		priority 2
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (closetDoor cel?)
					(curRoom setScript: putOnSpacesuit)
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

(instance helmet of View
	(properties
		x 135
		y 29
		description {helmet}
		sightAngle 45
		approachX 159
		approachY 80
		lookStr {It's a fish bowl - no, it's a space suit helmet!.}
		view 111
		loop 7
		cel 1
		priority 2
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(spaceSuit doVerb: theVerb &rest)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance doorLight of View
	(properties
		description {elevator light.}
		sightAngle 45
		lookStr {This elevator indicator points up.}
		view 111
		loop 1
	)
)

(instance bigRedDoor of Prop
	(properties
		x 229
		y 105
		description {big door.}
		sightAngle 45
		approachX 245
		approachY 121
		lookStr {These large red doors serve as the airlock entrance which empties out to the pod bay.}
		view 111
		loop 4
		priority 6
		signal $4011
		cycleSpeed 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(HandsOff)
				(curRoom setScript: leaveToHanger)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance drawer of Prop
	(properties
		x 91
		y 90
		z 22
		description {drawer}
		approachX 122
		approachY 96
		lookStr {A small drawer is located just beneath a button.}
		view 111
		loop 3
		cycleSpeed 8
	)
	
	(method (init)
		(self approachVerbs: 3 2)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(curRoom setScript: openDrawer)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance closetDoor of Prop
	(properties
		x 136
		y 81
		description {closet door}
		approachX 154
		approachY 86
		lookStr {The image on the closet door looks familiar, but you can't quite place it.}
		view 111
		loop 2
		priority 3
		signal $0010
		cycleSpeed 5
	)
	
	(method (init)
		(self approachVerbs: 3 2)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (not cel)
					(HandsOff)
					(curRoom setScript: openClosetDoor)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (setCycle)
		(super setCycle: &rest)
		(soundFx number: 329 loop: 1 play:)
	)
	
	(method (cue)
		(soundFx stop:)
	)
)

(instance lights of Prop
	(properties
		x 134
		y 167
		z 28
		description {lights}
		approachX 111
		approachY 173
		lookStr {Flashing lights adorn this strange yet familiar-looking console.}
		view 111
		loop 6
		priority 13
		signal $4010
		cycleSpeed 20
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 2)
	)
)

(instance control of Prop
	(properties
		x 123
		y 168
		z 19
		description {control}
		approachX 111
		approachY 173
		lookStr {It doesn't do anything but it sure looks cool.}
		view 111
		loop 5
		priority 12
		signal $4010
		cycleSpeed 24
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 2 3)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(controlPanel doVerb: theVerb &rest)
			)
			(3
				(controlPanel doVerb: theVerb &rest)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance elevatorDoor of Elevator
	(properties
		x 30
		y 109
		description {door}
		sightAngle 45
		lookStr {This is another elevator door. This one goes up.}
		view 111
		cycleSpeed 4
		level 2
	)
)

(instance controlPanel of Feature
	(properties
		x 160
		y 100
		nsBottom 190
		nsRight 320
		description {controlPanel}
		sightAngle 45
		onMeCheck $0004
		approachX 111
		approachY 173
		lookStr {The control panel has many confusing gauges on it. The only one you can read says "CAUTION: LAUNCH BAY DECOMPRESSED!"}
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 2 3)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 11 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance drawerButton of Feature
	(properties
		x 101
		y 89
		z 34
		nsTop 50
		nsLeft 96
		nsBottom 61
		nsRight 106
		description {drawer}
		sightAngle 45
		onMeCheck $0002
		approachX 122
		approachY 96
		lookStr {There is a button above the rectangular object below.}
	)
	
	(method (init)
		(self approachVerbs: 3 2)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(if (!= theVerb 2)
			(drawer doVerb: theVerb &rest)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance BayButton of Feature
	(properties
		x 265
		y 125
		z 31
		nsTop 88
		nsLeft 261
		nsBottom 101
		nsRight 269
		description {BayButton}
		sightAngle 45
		onMeCheck $0008
		approachX 245
		approachY 121
		lookStr {This button might have some function involving the adjacent doors.}
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 2 3)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(HandsOff)
				(curRoom setScript: leaveToHanger)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance drawerFeature of Feature
	(properties
		description {drawer}
		onMeCheck $0010
		lookStr {You notice a drawer and a button.}
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 2)
			(self
				x: ((User curEvent?) x?)
				y: ((User curEvent?) y?)
			)
		)
		(super doVerb: theVerb &rest)
	)
)
