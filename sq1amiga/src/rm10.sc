;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include sci.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use Elevator)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm10 0
)

(local
	local0
	buttonPadUp
)
(instance rm10 of SQRoom
	(properties
		lookStr {You're at the east end of one of the development labs. Another elevator is available here.}
		picture 10
		west 9
	)
	
	(method (init)
		(LoadMany 128 8 59 110)
		(= currentFloor 2)
		(= style (if (== prevRoomNum 9) 12 else 10))
		(self
			setRegions: 700
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						0
						319
						0
						319
						189
						0
						189
						0
						186
						273
						186
						293
						160
						273
						132
						244
						146
						221
						150
						197
						142
						201
						123
						190
						119
						139
						119
						118
						103
						94
						103
						54
						121
						27
						111
						51
						96
						0
						80
					yourself:
				)
		)
		(buttonPushed init: cel: 1 hide:)
		(ego init:)
		(ctrlPanel init:)
		(super init:)
		(door
			exitScript: (if (Btst 16) hearConverse else 0)
			exiting: (if (!= prevRoomNum 9) 1 else 0)
			light: doorLight
			init:
			whereTo: 11
			locked: 1
			unlockScript: insertKeyCard
			ignoreActors: 0
		)
		(features
			add:
				controlPanel
				correctButton
				wrongButtons
				vacHandle
				globe
				pipes
				keyCardSlot
			eachElementDo: #init
		)
		(bayDoor init: cel: (if (Btst 10) 0 else 5))
		(tank setCycle: Fwd init: cycleSpeed: 28)
		(HandsOn)
		(if (door exiting?) (HandsOff))
	)
	
	(method (doit)
		(if (and buttonPadUp (ego mover?))
			(= buttonPadUp 0)
			(buttonPad dispose:)
		)
		(cond 
			(script 0)
			((door inFront:) (door open:))
		)
		(super doit:)
	)
	
	(method (notify)
		(if (== prevRoomNum 11) (HandsOff))
	)
)

(instance insertKeyCard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 155 119 self)
			)
			(1 (ego setHeading: 0 self))
			(2
				(ego
					view: (if (Btst 16) 59 else 8)
					loop: 1
					cel: 0
					setCycle: End self
				)
			)
			(3
				(soundFx number: 304 loop: 1 play: self)
			)
			(4
				(Print 10 0)
				(SolvePuzzle 2 135)
				(ego setCycle: Beg self)
			)
			(5
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

(instance pressDownButton of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(soundFx number: 318 loop: 1 play:)
				(if register
					(buttonPushed show: cel: 1 x: 33)
				else
					(buttonPushed show: cel: 2 x: 69)
				)
				(= seconds 2)
			)
			(1
				(buttonPushed hide:)
				(buttonPad dispose:)
				(= cycles 3)
			)
			(2 (ego setHeading: 45 self))
			(3
				(ego
					view: (if (Btst 16) 59 else 8)
					loop: 0
					cel: 0
					cycleSpeed: 5
				)
				(if register
					(ego setCycle: CT 2 1 self)
				else
					(ego setCycle: End self)
				)
			)
			(4 (= cycles 30))
			(5
				(cond 
					(register
						(= register 0)
						(if (bayDoor cel?)
							(soundFx number: 316 loop: 1 play:)
							(Bset 10)
							(bayDoor startUpd: setCycle: Beg bayDoor)
						else
							(Print 10 1)
						)
					)
					((not (bayDoor cel?))
						(soundFx number: 316 loop: 1 play:)
						(theGame changeScore: -2)
						(Bclr 134)
						(Bclr 10)
						(bayDoor startUpd: setCycle: End)
					)
					(else (Print 10 2))
				)
				(= cycles 3)
			)
			(6 (ego setCycle: Beg self))
			(7
				(if (Btst 16)
					(NormalEgo 6 1 61)
				else
					(NormalEgo 6 0 60)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance hearConverse of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (Print 10 3) (= cycles 3))
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance door of Elevator
	(properties
		x 160
		y 115
		description {elevator}
		sightAngle 0
		lookStr {This is yet another in a long line of elevators in this game.. er.. ship.}
		view 110
		level 2
	)
)

(instance doorLight of View
	(properties
		x 156
		y 61
		description {light}
		sightAngle 90
		lookStr {This light indicates that the elevator goes down and, when lit, its presence at this floor.}
		view 110
		loop 1
		priority 15
		signal $4010
	)
)

(instance bayDoor of Prop
	(properties
		x 74
		y 58
		description {bay door}
		view 110
		loop 2
		cel 5
		signal $4001
		cycleSpeed 8
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
				(Printf
					10
					4
					(if (not (bayDoor cel?))
						{open exposing the vast reaches of space}
					else
						{closed}
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(SolvePuzzle 2 134)
		(self stopUpd:)
	)
)

(instance buttonPad of View
	(properties
		x 50
		y 120
		z -30
		description {button pad}
		sightAngle 45
		lookStr {This is the button pad for the bay doors below. The controls are located up here to minimize the chance of someone doing something stupid, such as pressing 'OPEN' while in the pod bay when wearing no pressure suit. YOU should be glad.}
		view 110
		loop 3
		priority 14
		signal $4010
	)
	
	(method (init)
		(features
			add: openButton closeButton
			eachElementDo: #init
			doit:
		)
		(= buttonPadUp 1)
		(super init: &rest)
	)
	
	(method (dispose)
		(features delete: openButton closeButton doit:)
		(= buttonPadUp 0)
		(super dispose:)
	)
)

(instance buttonPushed of View
	(properties
		y 139
		description {button}
		lookStr {The button is depressed. Aren't we all?}
		view 110
		loop 3
		priority 15
		signal $4010
	)
)

(instance tank of Prop
	(properties
		x 214
		y 94
		description {tank}
		lookStr {You always meant to ask one of the technicians what this thing is. However, you procrastinated a bit too long in this case. It's either an ocean-in-a-bottle or a model of your stomach as the deadline draws near.}
		view 110
		loop 4
		cel 2
		priority 1
		signal $0010
		cycleSpeed 8
		detailLevel 2
	)
)

(instance keyCardSlot of Feature
	(properties
		description {keyCardSlot}
		onMeCheck $0800
		lookStr {This is a keycard slot. Your bottom-of-the-foodchain-type clearance has always prevented you from attaining a keycard for this area, thus precluding your access to it without an escort. Good luck finding one now!}
	)
	
	(method (init)
		(super init:)
		(= approachX (door approachX?))
		(= approachY (door approachY?))
		(self approachVerbs: 3 4)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(4
				(if (== theItem 1)
					(door doVerb: theVerb theItem)
				else
					(super doVerb: theVerb theItem)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance openButton of Feature
	(properties
		x 46
		y 150
		nsTop 125
		nsLeft 16
		nsBottom 139
		nsRight 49
		description {openButton}
		sightAngle 360
		lookStr {This button opens the bay doors below.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(curRoom setScript: pressDownButton 0 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance closeButton of Feature
	(properties
		x 83
		y 149
		nsTop 125
		nsLeft 52
		nsBottom 139
		nsRight 85
		description {closeButton}
		sightAngle 360
		lookStr {This button closes the bay doors below.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(curRoom setScript: pressDownButton)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance controlPanel of Feature
	(properties
		description {controlPanel}
		sightAngle 45
		onMeCheck $0004
		approachX 24
		approachY 91
		lookStr {This is a remote monitoring panel for some of the ship's systems. Attempts to understand their purpose always made your brain hurt, but you do know that glowing red isn't good. Especially since some of the stuff is connected to the reactor cooling system.}
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 3 2)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (not buttonPadUp) (Print 10 5) else (Print 10 6))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance correctButton of Feature
	(properties
		description {controlPanel}
		sightAngle 45
		onMeCheck $0002
		approachX 24
		approachY 91
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 3 2)
	)
	
	(method (doVerb theVerb)
		(if (or (== theVerb 2) (== theVerb 3))
			(if (not buttonPadUp)
				(buttonPad init:)
			else
				(Print 10 6)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance wrongButtons of Feature
	(properties
		description {controlPanel}
		sightAngle 45
		onMeCheck $0008
		approachX 25
		approachY 92
		lookStr {These buttons are locked in position. It's probably just as well. You might live longer due to having no clue as to their purpose.}
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 3 2)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (not buttonPadUp) (Print 10 7) else (Print 10 6))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance vacHandle of Feature
	(properties
		x 106
		y 189
		z 26
		nsTop 137
		nsLeft 30
		nsBottom 189
		nsRight 183
		description {handle}
		onMeCheck $0010
		lookStr {This looks like something out of a Boris Karloid movie.}
	)
)

(instance globe of Feature
	(properties
		description {globe}
		onMeCheck $0040
		lookStr {This is the steel stand for the odd globe.}
	)
)

(instance pipes of Feature
	(properties
		description {pipes}
		onMeCheck $0080
		lookStr {More pipes run around the upper perimeter of this area.}
	)
)

(instance ctrlPanel of Feature
	(properties
		x 304
		y 136
		description {controlPanel}
		onMeCheck $0400
		approachX 266
		approachY 138
		lookStr {This is a remote monitoring panel for some of the ship's systems. Attempts to understand their purpose always made your brain hurt, but you do know that glowing red isn't good. Especially since some of the stuff is connected to the reactor cooling system.}
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 3 2)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 10 8))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
