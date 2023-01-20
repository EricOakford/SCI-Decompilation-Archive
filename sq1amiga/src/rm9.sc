;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9)
(include sci.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm9 0
)

(instance rm9 of SQRoom
	(properties
		lookStr {This place looks like a monument to Soviet computer hardware miniaturization. At one time, it served as a development facility for the Star Generator. But that was in the early phase of the project.}
		picture 9
		east 10
		west 8
	)
	
	(method (init)
		(Load rsVIEW 6 109 411 410)
		(= currentFloor 2)
		(= style (if (== prevRoomNum west) 12 else 11))
		(star setCycle: Fwd init:)
		(if (> selfDestructTimer 30) (tv1 init:) (tv2 init:))
		(if (>= (theGame detailLevel:) (tv1 detailLevel:))
			(tv1 setScript: tv1Script)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						0
						319
						0
						319
						72
						298
						67
						268
						65
						207
						50
						170
						46
						133
						46
						133
						62
						58
						77
						113
						101
						114
						109
						106
						117
						73
						128
						44
						129
						0
						120
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 0 186 319 186 319 189 0 189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 277 153 205 177 135 149 206 125
					yourself:
				)
		)
		((ScriptID 700 0)
			sarienEntryDir: 1
			s1startY: 140
			s2startY: 158
			s1gotoX: 24
			s1gotoY: 136
			s2gotoX: 33
			s2gotoY: 159
			safeCode: egoSafe
			checkFootCode: timeToHearGuards
			checkEntryCode: callTheGuards
		)
		(self setRegions: 700)
		(ego init:)
		(super init:)
		(features
			add: joystick mouse screen wires keyboard cords
			eachElementDo: #init
		)
		(if (and (== prevRoomNum 10) (Btst 16))
			(sarien1 init: setScript: fireUponEgo)
			(sarien2 init:)
			(if (>= (ego y?) 140)
				(sarien2 posn: 142 166)
				(sarien1 posn: 103 181)
			)
		else
			(HandsOn)
		)
	)
)

(instance fireUponEgo of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(or
				(and
					(== (sarien1 view?) 411)
					(or
						(and (<= (sarien1 loop?) 3) (== (sarien1 cel?) 5))
						(and (> (sarien1 loop?) 3) (== (sarien1 cel?) 6))
					)
				)
				(and
					(== (sarien1 view?) 412)
					(or
						(and (<= (sarien1 loop?) 1) (== (sarien1 cel?) 4))
						(and (> (sarien1 loop?) 1) (== (sarien1 cel?) 5))
					)
				)
				(and (== (sarien1 view?) 413) (== (sarien1 cel?) 5))
			)
			(soundFx number: 312 loop: 1 play:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 288 (ego y?) self)
			)
			(1 (sarien1 setCycle: End self))
			(2
				(sarien1 setCycle: Beg)
				(if (Btst 16)
					(ego view: 48 setLoop: 0 cel: 0 setCycle: End self)
				else
					(ego
						view: 6
						setLoop: (if (>= (ego y?) 140) 3 else 2)
						cel: 0
						setCycle: End self
					)
				)
			)
			(3
				(EgoDead 940 (if (Btst 16) 1 else 0) 0 9 0)
				(self dispose:)
			)
		)
	)
)

(instance tv1Script of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (tv1 setCycle: Beg self))
			(1 (= seconds (Random 2 5)))
			(2
				(tv1 setCycle: Fwd)
				(= seconds (Random 1 4))
			)
			(3 (self changeState: 0))
		)
	)
)

(instance egoSafe of Code
	(properties)
	
	(method (doit)
		(return (& (ego onControl:) $0006))
	)
)

(instance timeToHearGuards of Code
	(properties)
	
	(method (doit &tmp temp0)
		(= temp0 0)
		(if (and (!= prevRoomNum 10) (> (ego x?) 80))
			(= temp0 1)
		)
		(return temp0)
	)
)

(instance callTheGuards of Code
	(properties)
	
	(method (doit &tmp temp0)
		(= temp0 0)
		(if (and (!= prevRoomNum 10) (> (ego x?) 160))
			(= temp0 1)
		)
		(return temp0)
	)
)

(instance tv2 of View
	(properties
		x 248
		y 103
		sightAngle 45
		view 109
		loop 2
		priority 14
		signal $6010
	)
	
	(method (doit)
		(self cel: (tv1 cel?) forceUpd:)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(tv1 doVerb: theVerb &rest)
	)
)

(instance star of Prop
	(properties
		x 10
		y 20
		description {star}
		sightAngle 45
		lookStr {A relatively nearby star blazes.}
		view 109
		loop 3
		signal $6010
		cycleSpeed 12
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 9 1))
			(5 (Print 9 2))
			(12 (Print 9 3))
			(11 (Print 9 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance tv1 of Prop
	(properties
		x 157
		y 95
		description {tv1}
		sightAngle 45
		lookStr {On the screen is some green dude you've never seen the likes of. He seems to be talking, but the audio is out in this area of the ship.}
		view 109
		loop 1
		priority 14
		signal $6010
		cycleSpeed 8
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 9 4))
			(5 (Print 9 5))
			(12 (Print 9 3))
			(11 (Print 9 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sarien1 of Prop
	(properties
		x 99
		y 124
		description {sarien}
		lookStr {These guys look scary. But then, don't most other beings packing weapons?}
		view 411
		cycleSpeed 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 9 6))
			(5 (Print 9 7))
			(12 (Print 9 8))
			(11 (Print 9 8))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sarien2 of Prop
	(properties
		x 135
		y 113
		description {sarien}
		lookStr {These guys look scary. But then, don't most other beings packing weapons?}
		view 410
		loop 1
		cel 2
		cycleSpeed 8
	)
	
	(method (doVerb theVerb)
		(sarien1 doVerb: theVerb &rest)
	)
)

(instance joystick of Feature
	(properties
		description {joystick}
		onMeCheck $000a
		lookStr {The architects must have been low on oxygen when they came up with the design scheme for this compartment. It's a giant tribute to an ancient, but still functional, control device.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 9 9))
			(5 (Print 9 10))
			(12 (Print 9 11))
			(11 (Print 9 12))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mouse of Feature
	(properties
		description {mouse}
		onMeCheck $0014
		lookStr {Here, we have a nice chunk of floor space dedicated to a cursor control device named after an annoying rodent. What were they thinking?}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 9 13))
			(5 (Print 9 14))
			(12 (Print 9 15))
			(11 (Print 9 16))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance screen of Feature
	(properties
		description {screen}
		onMeCheck $0040
		lookStr {The view screens offer a glimpse of the surroundings from outboard cameras. They came in handy during the testing phase of the Star Generator.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 9 17))
			(5 (Print 9 18))
			(12 (Print 9 19))
			(11 (Print 9 20))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance wires of Feature
	(properties
		description {wires}
		onMeCheck $0020
		lookStr {Big, phony wiring from the giant control devices attempts to complete the decorative effect.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 9 21))
			(5 (Print 9 22))
			(12 (Print 9 23))
			(11 (Print 9 24))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance keyboard of Feature
	(properties
		description {keyboard}
		onMeCheck $0200
		lookStr {This keyboard was used in the development of the Star Generator. It has since been placed here to be out of the way. It has no function.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 9 25))
			(5 (Print 9 26))
			(12 (Print 9 27))
			(11 (Print 9 28))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cords of Feature
	(properties
		description {cords}
		onMeCheck $0400
		lookStr {Big, phony wiring from the giant control devices attempts to complete the decorative effect.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 9 29))
			(5 (Print 9 30))
			(12 (Print 9 31))
			(11 (Print 9 32))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
