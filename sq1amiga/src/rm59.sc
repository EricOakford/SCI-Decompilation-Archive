;;; Sierra Script 1.0 - (do not remove this comment)
(script# 59)
(include sci.sh)
(use Main)
(use Intrface)
(use Deltaur)
(use SQRoom)
(use Elevator)
(use Polygon)
(use LoadMany)
(use Sound)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm59 0
)

(local
	[upperPts 16] = [319 88 319 189 0 189 0 0 319 0 319 77 66 77 43 88]
	[lowerPts 17] = [319 189 0 189 0 0 319 0 319 175 57 175 31 185 319 185]
)
(instance rm59 of SQRoom
	(properties
		lookStr {You're in a hallway on the upper level of the Deltaur.}
		picture 59
		style $000b
		east 60
	)
	
	(method (init)
		(HandsOff)
		(self setRegions: 703)
		(LoadMany 128 159 464 47)
		(features
			add:
				farRightLowerYellowMach
				farRightUpperBlueMach
				popcorn
				microwave
				generator
				variousMachines
				variousLowerMachines
				rowOfLights
				otherGadgets
			eachElementDo: #init
			doit:
		)
		(LoadMany 132 361 403 622)
		(upperPoly points: @upperPts size: 8)
		(lowerPoly points: @lowerPts size: 8)
		(if (== currentFloor 1)
			(self addObstacle: upperPoly)
		else
			(self addObstacle: lowerPoly)
		)
		(if
			(and
				(== currentFloor 1)
				(== (DeltaurRegion egoStatus?) 1)
				(== prevRoomNum east)
			)
			(if (or (ego has: 13) (Btst 47)) else (ego has: 12))
			((ScriptID 703 17) dispose:)
		)
		(electBall setCycle: Fwd init:)
		(lights setCycle: Fwd setScript: pulse init:)
		(ego init:)
		(super init:)
		(DeltaurRegion eDoor1: e1Door)
		(DeltaurRegion eDoor2: e2Door)
		(e1Door
			view: 159
			locked: 0
			loop: 0
			posn: 91 76
			lookStr: {This is an elevator to one of the lower levels.}
			whereTo: e2Door
			level: 1
			setPri: 4
			exiting: (< prevRoomNum 10)
			polyCode: goUp
			init:
		)
		(e2Door
			view: 159
			loop: 0
			locked: 0
			posn: 91 172
			setPri: 13
			lookStr: {This is an elevator to one of the upper levels.}
			whereTo: e1Door
			level: 2
			polyCode: goDown
			init:
		)
		(HandsOn)
	)
	
	(method (doit)
		(cond 
			((> (theGame detailLevel:) 1) (if (not (lights script?)) (lights setScript: pulse)))
			((lights script?) (lights show:) (electBall show:) (lights setScript: 0))
		)
		(cond 
			(script 0)
			((e1Door inFront:) (e1Door open:))
			((e2Door inFront:) (e2Door open:))
			(
				(and
					(== currentFloor 1)
					(== (DeltaurRegion egoStatus?) 1)
					(<= (ego x?) 256)
					(or (ego has: 13) (Btst 47) (ego has: 12))
				)
				(self setScript: egoLosesHelmet)
			)
		)
		(super doit:)
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
			(Print 59 0)
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
			(Print 59 0)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance helmet of Actor
	(properties
		x 175
		y 76
		view 159
		loop 3
		cel 1
		cycleSpeed 6
		moveSpeed 6
	)
)

(instance robot of Actor
	(properties
		x 330
		y 88
		view 464
		loop 1
		priority 6
		signal $4010
		cycleSpeed 6
		xStep 5
		moveSpeed 6
	)
)

(instance goDown of Code
	(properties)
	
	(method (doit)
		((curRoom obstacles?) delete: upperPoly)
		((curRoom obstacles?) add: lowerPoly)
		(= currentFloor 2)
	)
)

(instance goUp of Code
	(properties)
	
	(method (doit)
		((curRoom obstacles?) delete: lowerPoly)
		((curRoom obstacles?) add: upperPoly)
		(= currentFloor 1)
	)
)

(instance electBall of Prop
	(properties
		x 236
		y 51
		lookStr {A neat gizmo.}
		view 159
		loop 2
		cel 1
		priority 1
		signal $4010
		cycleSpeed 6
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(super doVerb: theVerb &rest)
		else
			(Print 59 0)
		)
	)
)

(instance lights of Prop
	(properties
		x 235
		y 115
		lookStr {A panel of flashing lights.}
		view 159
		loop 1
		cel 3
		priority 15
		signal $4010
		cycleSpeed 6
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 2)
			(super doVerb: theVerb &rest)
		else
			(Print 59 0)
		)
	)
)

(instance upperPoly of Polygon
	(properties
		type $0002
	)
)

(instance lowerPoly of Polygon
	(properties
		type $0002
	)
)

(instance helmetFlysAcrossRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(helmet setPri: 15 setMotion: JumpTo 39 29 self)
			)
			(1
				(soundFx number: 361 loop: 1 play:)
				(helmet setLoop: 4 setMotion: JumpTo 86 78 self)
			)
			(2
				(soundFx play:)
				(helmet setMotion: MoveTo 106 78 self)
			)
			(3
				(helmet
					cycleSpeed: 18
					yStep: (- (helmet yStep?) 1)
					setMotion: MoveTo 147 77 self
				)
			)
			(4
				(helmet setCycle: 0 setPri: -1 ignoreActors: 1 setCel: 0)
				(self dispose:)
			)
		)
	)
)

(instance egoLosesHelmet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 47 setLoop: 0 cel: 0 setCycle: CT 1 1 self)
			)
			(1
				(ego setCel: 1 setMotion: JumpTo 237 84 self)
			)
			(2
				(soundFx number: 403 loop: 1 play:)
				(ego setCycle: CT 2 1 self)
			)
			(3
				(helmet
					init:
					ignoreActors: 1
					setCycle: Fwd
					setScript: helmetFlysAcrossRoom self
				)
				(ego setCycle: End)
			)
			(4
				(robotSound number: 622 loop: -1 play:)
				(robot
					init:
					ignoreActors: 1
					setCycle: Walk
					setLoop: 1
					setMotion: MoveTo 94 85 self
				)
			)
			(5
				(robot loop: 7 cel: 4 setCycle: Beg self)
			)
			(6
				(ego ignoreActors: 1)
				(robot
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 128 77 self
				)
			)
			(7
				(robotSound stop:)
				(helmet dispose:)
				(robot setLoop: 5 posn: 128 77 cel: 0 setCycle: End self)
			)
			(8
				(ego setLoop: 2 cel: 0 setCycle: CT 3 1)
				(robotSound number: 622 loop: -1 play:)
				(robot
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo (- (ego x?) 66) (- (ego y?) 2) self
				)
			)
			(9
				(robot hide:)
				(ego setCycle: CT 4 1 self)
			)
			(10
				(= register (ego cycleSpeed?))
				(ego
					cycleSpeed: (robot cycleSpeed?)
					setCycle: CT 9 1 self
				)
			)
			(11
				(ego
					cel: (ego lastCel:)
					ignoreActors: 0
					cycleSpeed: register
				)
				(robot
					show:
					setPri: (- (ego priority?) 1)
					posn: (- (ego x?) 30) (- (ego y?) 2)
					cel: 1
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 353 (robot y?) self
				)
			)
			(12
				(robotSound stop:)
				(robot dispose:)
				(ego setLoop: 1 cel: 0 setCycle: End self)
			)
			(13
				(DeltaurRegion egoStatus: 2)
				(EgoStatusCheck)
				(ego loop: 1)
				(= ticks 18)
			)
			(14
				(Print 59 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance pulse of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(lights cycleSpeed: (* (Random 0 10) 3))
				(electBall startUpd: show:)
				(= seconds (Random 1 5))
			)
			(1
				(= state (- state 2))
				(electBall hide:)
				(= seconds (Random 1 5))
			)
		)
	)
)

(instance farRightLowerYellowMach of RegionFeature
	(properties
		description {gold machine}
		onMeCheck $0400
		lookStr {It's an expensive-looking machine that goes `ping.'}
		level 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 59 2))
			(12 (Print 59 3))
			(11 (Print 59 4))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance farRightUpperBlueMach of RegionFeature
	(properties
		description {blue speaker}
		onMeCheck $0040
		lookStr {It does sort of look like a speaker, doesn't it. But it isn't.}
		level 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5 (Print 59 5))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance popcorn of RegionFeature
	(properties
		description {purple egg thing}
		onMeCheck $4000
		lookStr {It seems obvious to you that Wally Wood did some time designing Sarien hardware.}
		level 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 59 6))
			(12 (Print 59 7))
			(11 (Print 59 8))
			(5 (Print 59 9))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance microwave of RegionFeature
	(properties
		description {that radio thing}
		onMeCheck $2000
		lookStr {It's either a mega-frequency wide band spectrofonic analyzer module or one of those really old table model radios.}
		level 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 59 10))
			(11 (Print 59 11))
			(5 (Print 59 12) 5)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance generator of RegionFeature
	(properties
		description {generator}
		onMeCheck $1000
		lookStr {Printed near the bottom in tiny precise letters are the words: Oda Generator - model EC-54 - Deltaur backup unit #1.}
		level 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 59 13))
			(5 (Print 59 14))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance variousMachines of RegionFeature
	(properties
		description {various machines}
		onMeCheck $0800
		level 1
	)
)

(instance variousLowerMachines of RegionFeature
	(properties
		description {various machines}
		onMeCheck $0002
		level 2
	)
)

(instance rowOfLights of RegionFeature
	(properties
		description {row-o-lights}
		onMeCheck $0008
		lookStr {This bank of lights looks extremely important, or it would if you were a Sarien.}
		level 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5 (Print 59 15))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance otherGadgets of RegionFeature
	(properties
		description {gadgets}
		onMeCheck $0004
		level 1
	)
)

(instance robotSound of Sound
	(properties
		prevSignal -1
	)
)
