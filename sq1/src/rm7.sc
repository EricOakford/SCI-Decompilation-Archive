;;; Sierra Script 1.0 - (do not remove this comment)
(script# 7)
(include game.sh)
(use Main)
(use Intrface)
(use Osc)
(use Polygon)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm7 0
)

(instance rm7 of Room
	(properties
		lookStr {This is the Star Generator Development Laboratory. Due to your incredibly low security clearance, you have never had access to this room. What a mess! Wreckage clutters the middle of the floor. A pair of lab workers have been blasted from the roster of the living. You're glad somebody else has to clean this up!}
		picture 7
		east 6
		south 6
	)
	
	(method (init)
		(LoadMany VIEW 53 4 400)
		(self setRegions: ARCADA)
		(deadMan1 init:)
		(deadMan2 init:)
		(= currentFloor 2)
		(if (not (ego has: iWidget))
			(Load VIEW 57)
			(widget init:)
		)
		(super init:)
		(if (!= (music number?) 355)
			(music
				number: 319
				loop: 1
				play: 40
				fade: 127 25 10 0
				hold: 1
			)
		)
		(features
			add:
				miscEquip
				baseSupport
				upperSupport
				floor
				starChart
				glassGlobe
				rightConsole
			eachElementDo: #init
		)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 0
						319 0
						319 155
						298 146
						287 152
						270 157
						243 145
						241 140
						244 135
						241 132
						226 129
						224 126
						204 123
						177 121
						134 121
						100 121
						72 124
						80 129
						81 133
						70 136
						70 148
						42 158
						26 160
						43 167
						72 177
						114 186
						301 186
						303 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						128 131
						179 131
						196 155
						194 162
						181 167
						161 167
						140 164
						127 161
						122 155
						115 155
						103 156
						96 153
						97 149
						104 146
						102 140
						103 132
					yourself:
				)
		)
		(ego init:)
		(self setScript: enterScript)
	)
	
	(method (doit &tmp edge)
		(cond 
			(script (script doit:))
			(
				(= edge
					(switch ((User alterEgo?) edgeHit?)
						(EAST east)
						(SOUTH south)
					)
				)
				(self setScript: ExitRoom)
			)
		)
	)
	
	(method (newRoom n)
		(if (!= (music number?) 355) (music hold: 0 fade:))
		(super newRoom: n)
	)
)

(instance ExitRoom of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== state 0) (> (ego nsLeft?) 320))
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 135 setMotion: MoveFwd 100)
			)
			(1 (ego setHeading: 180 self))
			(2 (curRoom newRoom: 6))
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(if (Btst fBeenInArcadaLab) (= state 3))
				(ego
					posn: 330 239
					setLoop: 7
					setPri: -1
					setMotion: MoveTo 280 185 self
				)
			)
			(1
				(soundFx number: 816 loop: 1 play:)
				(ego view: 53 cycleSpeed: 5 setLoop: 0 setCycle: EndLoop self)
			)
			(2
				(ego setLoop: 1 setCycle: EndLoop self)
			)
			(3
				(ego cycleSpeed: 2)
				(= seconds 1)
			)
			(4
				(HandsOn)
				(Bset fBeenInArcadaLab)
				(NormalEgo 1 0 60)
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
				(ego setHeading: 0 self)
			)
			(1
				(if register (Print 7 0) (= state 2))
				(ego
					view: 4
					loop: (if (< (ego x?) ((CueObj client?) x?)) 5 else 4)
					cycleSpeed: 5
					cel: register
					setCycle: (if register BegLoop else EndLoop) self
				)
			)
			(2
				(ego
					loop: (+ (ego loop?) 2)
					cel: 0
					cycleSpeed: 2
					setCycle: Oscillate 2 self
				)
				(= state 0)
				(= register 3)
			)
			(3
				(HandsOn)
				(= state -1)
				(= register 0)
				(NormalEgo 3 0 60)
				(self dispose:)
			)
		)
	)
)

(instance getWidget of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 57 loop: 0 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(1
				(widget dispose:)
				(SolvePuzzle 1 f7GetWidget)
				(ego get: iWidget)
				(ego setCycle: EndLoop self)
			)
			(2
				(HandsOn)
				(NormalEgo 6 0 60)
				(self dispose:)
			)
		)
	)
)

(instance deadMan1 of View
	(properties
		x 42
		y 157
		description {Randy}
		sightAngle 45
		approachX 67
		approachY 156
		lookStr {The lifeless body of Randy, one of the lab technicians, lies sprawled at your feet. Those laser blasts are nasty. Why, you can't distinguish one exposed organ from another.}
		view 400
		cel 7
		priority 4
		signal (| fixPriOn ignrAct)
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbLook verbTalk verbDo)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(curRoom setScript: searchBody)
			)
			(verbTalk
				(Print 7 1)
			)
			(verbSmell
				(Print 7 2)
			)
			(verbTaste
				(Print 7 3)
			)
			(verbUse
				(switch theItem
					(iBuckazoid
						(Print 7 4)
					)
					(iCartridge
						(Print 7 5)
					)
					(iKeyCard
						(Print 7 6)
					)
					(iWidget
						(Print 7 7)
					)
					(iGadget
						(Print 7 8)
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
	)
)

(instance deadMan2 of View
	(properties
		x 264
		y 152
		description {Hugh}
		sightAngle 45
		approachX 241
		approachY 154
		lookStr {Yet another crewman's motionless body reduces the shine of the floor wax. Hugh doesn't look too neat and clean with his lungs hanging out like that.}
		view 400
		cel 2
		priority 9
		signal (| fixPriOn ignrAct)
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbLook verbTalk verbDo)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(curRoom setScript: searchBody)
			)
			(verbTalk
				(Print 7 9)
			)
			(verbTaste
				(Print 7 10)
			)
			(verbSmell
				(Print 7 11)
			)
			(verbUse
				(switch theItem
					(iBuckazoid
						(Print 7 12)
					)
					(iCartridge
						(Print 7 13)
					)
					(iKeyCard
						(Print 7 14)
					)
					(iWidget
						(Print 7 15)
					)
					(iGadget
						(Print 7 16)
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
	)
)

(instance widget of Prop
	(properties
		x 132
		y 141
		description {widget}
		approachX 121
		approachY 162
		lookStr {You find a small but heavy device affixed to the base of the Star Generator platform. It appears to be magnetic. That must be how the aliens upset the force field protecting the unit!}
		view 57
		loop 1
		priority 12
		signal (| fixPriOn ignrAct)
		cycleSpeed 2
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Forward approachVerbs: verbLook verbDo)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(curRoom setScript: getWidget)
			)
			(verbTalk
				(Print 7 17)
			)
			(verbSmell
				(Print 7 18)
			)
			(verbTaste
				(Print 7 19)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance upperSupport of Feature
	(properties
		description {upper support}
		sightAngle 45
		onMeCheck NEARCHECK
		lookStr {This is the upper support for the Star Generator when it is affixed to the pedestal.}
	)
	
	(method (doVerb theVerb theItem)
		(self
			x: ((User curEvent?) x?)
			y: ((User curEvent?) y?)
		)
		(super doVerb: theVerb theItem &rest)
	)
)

(instance baseSupport of Feature
	(properties
		description {baseSupport}
		sightAngle 45
		onMeCheck FARCHECK
		lookStr {The base of the Star Generator research pedestal has apparently been trashed by the vandals who stole the Generator.}
	)
	
	(method (doVerb theVerb theItem)
		(self
			x: ((User curEvent?) x?)
			y: ((User curEvent?) y?)
		)
		(super doVerb: theVerb theItem &rest)
	)
)

(instance miscEquip of Feature
	(properties
		description {miscEquip}
		sightAngle 45
		onMeCheck $0010
		lookStr {Now you know why you didn't have clearance for this place. You don't understand what most of this crap does. This thing looks like a giant speaker for the cosmos.}
	)
	
	(method (doVerb theVerb theItem)
		(self
			x: ((User curEvent?) x?)
			y: ((User curEvent?) y?)
		)
		(super doVerb: theVerb theItem &rest)
	)
)

(instance rightConsole of Feature
	(properties
		description {rightConsole}
		sightAngle 45
		onMeCheck $4000
		lookStr {This thing looks like it came off the front end of an old Studebaker (or maybe the Batmobile). Anyway, it appears non-functional at this time. It was probably damaged in the heist.}
	)
	
	(method (doVerb theVerb theItem)
		(self
			x: ((User curEvent?) x?)
			y: ((User curEvent?) y?)
		)
		(super doVerb: theVerb theItem &rest)
	)
)

(instance glassGlobe of Feature
	(properties
		description {glassGlobe}
		sightAngle 45
		onMeCheck $0020
		lookStr {The glass globe atop the console is inactive at this time. The mess of wiring inside reminds you of how your hair looks after a nap.}
	)
	
	(method (doVerb theVerb theItem)
		(self
			x: ((User curEvent?) x?)
			y: ((User curEvent?) y?)
		)
		(super doVerb: theVerb theItem &rest)
	)
)

(instance floor of Feature
	(properties
		description {floor}
		onMeCheck $0040
		lookStr {You are standing on the research platform where the Star Generator is... was mounted for testing.}
	)
)

(instance starChart of Feature
	(properties
		description {starChart}
		sightAngle 45
		onMeCheck $0008
		lookStr {This is a star chart. It was used for locating candidates to test the Star Generator on once the bulk of the development process was completed.}
	)
	
	(method (doVerb theVerb theItem)
		(self
			x: ((User curEvent?) x?)
			y: ((User curEvent?) y?)
		)
		(super doVerb: theVerb theItem &rest)
	)
)
