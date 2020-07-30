;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11)
(include game.sh)
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
)
(instance rm11 of Room
	(properties
		lookStr {This is the airlock of the Arcada.}
		picture 11
		east 12
	)
	
	(method (init)
		(LoadMany VIEW 9 111)
		(= currentFloor 2)
		(if (not (Btst fWearingSpacesuit))
			(helmet init: stopUpd:)
			(spaceSuit init: stopUpd:)
		)
		(drawerFeature init:)
		(drawerButton init:)
		(BayButton init:)
		(controlPanel init:)
		(control setCycle: Forward init:)
		(if (Btst fBayDoorsOpen) (lights setCycle: Forward))
		(lights init:)
		(if (ArcadaCheck 553 128)
			(closetDoor init: ignoreActors: TRUE cel: 6 stopUpd:)
			(helmet approachVerbs: verbDo verbLook)
			(spaceSuit approachVerbs: verbDo verbLook)
		else
			(closetDoor init: stopUpd:)
		)
		(drawer init: stopUpd:)
		(bigRedDoor
			init:
			setCel: (bigRedDoor lastCel:)
			approachVerbs: verbDo
		)
		(self
			setRegions: ARCADA
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						4 100
						22 100
						22 113
						4 115
						4 126
						14 150
						58 179
						93 187
						172 187
						294 150
						289 135
						255 126
						218 107
						194 104
						195 92
						218 84
						173 76
						127 89
						121 86
						78 97
						98 105
						72 112
						56 108
						48 112
						38 114
						35 98
						6 98
						6 0
						319 0
						319 189
						0 189
						0 0
						4 0
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
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
			exiting: (if (!= prevRoomNum 12) TRUE else FALSE)
			light: doorLight
			init:
			setPri: 7
			whereTo: 10
		)
		(if (!= (music number?) 355)
			(music number: 323 loop: -1 play:)
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
		(if (!= (music number?) 355) (music loop: 0 fade:))
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
			(and
				(== (ego loop?) 1)
				(== (ego cel?) 2)
				(cast contains: spaceSuit)
			)
			(spaceSuit _approachVerbs: ftrDefault dispose:)
		)
		(if
			(and
				(== (ego loop?) 2)
				(== (ego cel?) 3)
				(cast contains: helmet)
			)
			(helmet _approachVerbs: ftrDefault dispose:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(SolvePuzzle 2 f11PutOnSpacesuit)
				(ego
					view: 9
					setLoop: 1
					cycleSpeed: 8
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(ego setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(closetDoor setCycle: BegLoop self)
				((ScriptID ARCADA 0)
					rFlag1: (& ((ScriptID 700 0) rFlag1?) $ff7f)
				)
			)
			(3
				(soundFx stop:)
				(closetDoor stopUpd:)
				(NormalEgo 3 1 61)
				(Bset fWearingSpacesuit)
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
				(= oldView ((ego cycler?) vWalking?))
				(= oldStop ((ego cycler?) vStopped?))
				(ego
					view: 9
					setLoop: (if (== oldView 0) 6 else 7)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(soundFx number: 311 loop: 1 play:)
				(globalSound number: 322 loop: 1 play:)
				(bigRedDoor setCycle: BegLoop self)
			)
			(2
				(ego setPri: (bigRedDoor priority?) setCycle: BegLoop self)
			)
			(3
				(NormalEgo)
				(bigRedDoor setPri: (- (ego priority?) 1))
				(ego
					ignoreActors: TRUE
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
				(bigRedDoor setPri: -1 setCycle: EndLoop self)
				(if (Btst fBayDoorsOpen) (globalSound fade:))
				(ego setPri: -1 setMotion: MoveTo 306 114 self)
			)
			(6 0)
			(7
				(ego illegalBits: cWHITE ignoreActors: FALSE)
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
				(bigRedDoor setCycle: BegLoop self)
			)
			(2
				(ego setPri: -1 setMotion: MoveTo 213 114 self)
			)
			(3
				(soundFx number: 311 loop: 1 play:)
				(bigRedDoor setCycle: EndLoop self)
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
			(and
				(== (ego loop?) 1)
				(== (ego cel?) 1)
				(not (cast contains: spaceSuit))
			)
			(spaceSuit init: stopUpd:)
		)
		(if
			(and
				(== (ego loop?) 2)
				(== (ego cel?) 3)
				(not (cast contains: helmet))
			)
			(helmet init: stopUpd:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setHeading: 0 self))
			(1
				(ego
					view: 9
					setLoop: (if (Btst fWearingSpacesuit) 5 else 0)
					cel: 0
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(soundFx number: 318 loop: 1 play:)
				(ego setCycle: BegLoop)
				(closetDoor setCycle: EndLoop self)
			)
			(3
				(soundFx stop:)
				((ScriptID ARCADA 0)
					rFlag1: (| ((ScriptID ARCADA 0) rFlag1?) $0080)
				)
				(closetDoor ignoreActors: TRUE)
				(if (Btst fWearingSpacesuit)
					(NormalEgo 0 1 61)
					(ego setMotion: PolyPath 159 80 self)
				else
					(if (not (ArcadaCheck 553 64))
						(Print 11 0)
						((ScriptID ARCADA 0)
							rFlag1: (| ((ScriptID 700 0) rFlag1?) $0040)
						)
					)
					(helmet approachVerbs: verbDo verbLook)
					(spaceSuit approachVerbs: verbDo verbLook)
					(NormalEgo 3 0 60)
					(= cycles 3)
				)
			)
			(4
				(if (Btst fWearingSpacesuit)
					(ego
						view: 9
						setLoop: 2
						cel: 10
						cycleSpeed: 8
						setCycle: BegLoop self
					)
				else
					(HandsOn)
					(self dispose:)
				)
			)
			(5
				(ego setLoop: 1 cel: 15 setCycle: BegLoop self)
			)
			(6
				(closetDoor setCycle: BegLoop self)
				((ScriptID ARCADA 0)
					rFlag1: (& ((ScriptID ARCADA 0) rFlag1?) $ff7f)
				)
				(Bclr fWearingSpacesuit)
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
					loop: (if (Btst fWearingSpacesuit) 4 else 3)
					cel: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(1
				(soundFx number: 330 loop: 1 play:)
				(drawer setCycle: (if (drawer cel?) BegLoop else EndLoop) self)
			)
			(2
				(cond 
					((not (drawer cel?)) (ego setCycle: BegLoop self))
					((not (ego has: iGadget))
						(Print 11 1)
						(ego get: iGadget setCycle: EndLoop self)
						(SolvePuzzle 2 f11GetGadget)
						(= state -1)
					)
					(else (Print 11 2) (= cycles 3) (= state 0))
				)
			)
			(3
				(HandsOn)
				(if (Btst fWearingSpacesuit)
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
		signal (| stopUpdOn fixPriOn ignrAct)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
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
		signal (| stopUpdOn fixPriOn ignrAct)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
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
		signal (| stopUpdOn fixPriOn ignrAct)
		cycleSpeed 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
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
		(self approachVerbs: verbDo verbLook)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
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
		signal fixPriOn
		cycleSpeed 5
	)
	
	(method (init)
		(self approachVerbs: verbDo verbLook)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
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
		signal (| fixPriOn ignrAct)
		cycleSpeed 20
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbLook)
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
		signal (| fixPriOn ignrAct)
		cycleSpeed 24
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbLook verbDo)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(controlPanel doVerb: theVerb &rest)
			)
			(verbDo
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
		onMeCheck FARCHECK
		approachX 111
		approachY 173
		lookStr {The control panel has many confusing gauges on it. The only one you can read says "CAUTION: LAUNCH BAY DECOMPRESSED!"}
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbLook verbDo)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 11 3)
			)
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
		onMeCheck NEARCHECK
		approachX 122
		approachY 96
		lookStr {There is a button above the rectangular object below.}
	)
	
	(method (init)
		(self approachVerbs: verbDo verbLook)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(if (!= theVerb verbLook)
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
		onMeCheck ISNOTHIDDEN
		approachX 245
		approachY 121
		lookStr {This button might have some function involving the adjacent doors.}
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbLook verbDo)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
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
		(if (== theVerb verbLook)
			(self
				x: ((User curEvent?) x?)
				y: ((User curEvent?) y?)
			)
		)
		(super doVerb: theVerb &rest)
	)
)
