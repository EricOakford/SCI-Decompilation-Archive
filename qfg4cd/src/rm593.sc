;;; Sierra Script 1.0 - (do not remove this comment)
(script# 593)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use ForestView)
(use Scaler)
(use Polygon)
(use Jump)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm593 0
)

(local
	local0
	gTheObj_2CycleSpeed
)
(instance rm593 of GloryRm
	(properties
		picture 420
		horizon 66
		north 592
		west 480
		topX 158
	)
	
	(method (init)
		(Bset 35)
		(self setRegions: 50)
		(super init: &rest)
		(if (or (!= prevRoomNum 480) global467)
			(if global467 (self setScript: sJumpFrom480))
			(= global467 0)
			(barrier init:)
			(heroTeller init: ego 593 3 128 2)
			(= west 0)
			(self
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							173
							105
							173
							-300
							619
							-300
							619
							489
							-300
							489
							-300
							155
							-300
							122
							-300
							-300
							141
							-300
							141
							68
							164
							84
							136
							101
							102
							101
							94
							96
							64
							96
							37
							69
							8
							69
							8
							75
							49
							92
							49
							99
							16
							99
							16
							105
							70
							117
							70
							148
							25
							148
							25
							174
							192
							174
							192
							162
							262
							162
							300
							138
							261
							127
							261
							118
							216
							118
							216
							113
							140
							113
							140
							105
						yourself:
					)
			)
		else
			(barrier loop: 2 cel: 9 init:)
			(= local0 1)
			(self
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							173
							105
							173
							-300
							619
							-300
							619
							489
							-300
							489
							-300
							155
							25
							155
							25
							174
							192
							174
							192
							162
							262
							162
							300
							138
							261
							127
							261
							118
							216
							118
							216
							113
							140
							113
							140
							105
						yourself:
					)
					((Polygon new:)
						type: 2
						init:
							141
							-300
							141
							68
							164
							84
							136
							101
							102
							101
							94
							96
							64
							96
							37
							69
							8
							69
							8
							75
							49
							92
							49
							99
							16
							99
							16
							122
							-300
							122
							-300
							-300
						yourself:
					)
			)
		)
		(atp1 init: setPri: 180)
		(atp2 init: setPri: 95)
		(atp3 init: setPri: 109)
		(atp4 init: setPri: 180)
		(extra1 init:)
		(extra2 init: setPri: 180)
		(stream1 setPri: 74 setCycle: Fwd init:)
		(stream2 setPri: 74 setCycle: Fwd init:)
		(stream3 setPri: 74 setCycle: Fwd init:)
		(streamMat init:)
		(if (Btst 380) (theGame save: 1))
	)
	
	(method (dispose)
		(Bclr 35)
		(streamMat dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(81
					(if local0
						(messager say: 2 81 2)
						(return 1)
					else
						(self setScript: sDetectBush)
					)
				)
				(82
					(AutoTarget 32 122)
					(self setScript: (ScriptID 11) 0 barrier)
				)
				(80
					(messager say: 1 80)
					(return 1)
				)
				(11
					(if (streamMat onMe: (ego x?) (ego y?))
						(messager say: 5 6 7)
					else
						(messager say: 4 0 8)
					)
				)
				(else 
					(if (Message msgSIZE 593 0 theVerb 0 1)
						(messager say: 0 theVerb 0 0 0 593)
					else
						((ScriptID 50) doVerb: theVerb)
						(return 1)
					)
				)
			)
		)
	)
)

(instance heroTeller of Teller
	(properties
		loopMenu 0
	)
	
	(method (respond)
		(super respond: &rest)
		(if (== iconValue -999) ((User curEvent?) claimed: 1))
		(return 1)
	)
	
	(method (sayMessage)
		(Bclr 51)
		(if (== iconValue 5)
			(if local0
				(messager say: 2 81 2)
			else
				(curRoom setScript: sSpellBush 0 1)
			)
		else
			(super sayMessage: &rest)
		)
	)
	
	(method (showCases)
		(super showCases: 5 (Btst 186))
	)
)

(instance sSpellBush of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 10)
			)
			(1
				(ego
					setHeading:
						(GetAngle (ego x?) (ego y?) (barrier x?) (barrier y?))
						self
				)
			)
			(2
				(barrier
					signal: (| (barrier signal?) $0001)
					setCycle: End self
				)
			)
			(3 (barrier setCycle: Beg self))
			(4
				(barrier setLoop: 1 setCel: 0 setCycle: End self)
			)
			(5 (barrier setCycle: Beg self))
			(6
				(barrier setLoop: 2 setCel: 0 setCycle: End self)
			)
			(7
				(barrier signal: (& (barrier signal?) $fffe))
				(if (== register 1)
					(messager say: 3 128 5 0 self)
				else
					(self cue:)
				)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								173
								105
								173
								-300
								619
								-300
								619
								489
								-300
								489
								-300
								155
								25
								155
								25
								174
								192
								174
								192
								162
								262
								162
								300
								138
								261
								127
								261
								118
								216
								118
								216
								113
								140
								113
								140
								105
							yourself:
						)
						((Polygon new:)
							type: 2
							init:
								141
								-300
								141
								68
								164
								84
								136
								101
								102
								101
								94
								96
								64
								96
								37
								69
								8
								69
								8
								75
								49
								92
								49
								99
								16
								99
								16
								122
								-300
								122
								-300
								-300
							yourself:
						)
				)
				(= local0 1)
				(heroTeller dispose:)
				(curRoom west: 480)
				(theGame handsOn:)
				(= register 0)
				(self dispose:)
			)
		)
	)
)

(instance sJumpBush of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 200 (barrier approachY?) self)
			)
			(1
				(messager say: 2 159 0 0 self)
			)
			(2
				(= register egoGait)
				(ego
					changeGait: 1
					setMotion: MoveTo (barrier approachX?) (barrier approachY?) self
				)
			)
			(3
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(= global467 1)
				(ego
					view: 30
					setLoop: 3
					setCel: 0
					setSpeed: defaultCycles
					setScale: 0
					setScaler: 0
					setCycle: CT 8 1
					setMotion: JumpTo -30 (ego y?) self
				)
			)
			(4
				(ego
					changeGait: register
					normalize:
					setSpeed: gTheObj_2CycleSpeed
				)
				(curRoom newRoom: 480)
			)
		)
	)
)

(instance sJumpFrom480 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 30
					x: -30
					y: 140
					setLoop: 2
					setCel: 0
					setSpeed: defaultCycles
					setScale: 0
					setCycle: CT 8 1
					setMotion: JumpTo 84 140 self
				)
			)
			(1
				(ego
					normalize: 0
					setScaler: Scaler 100 40 147 61
					setSpeed: gTheObj_2CycleSpeed
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDetectBush of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setHeading:
						(GetAngle (ego x?) (ego y?) (barrier x?) (barrier y?))
						self
				)
			)
			(1
				(barrier
					signal: (| (barrier signal?) $0001)
					setCycle: End self
				)
			)
			(2 (barrier setCycle: Beg self))
			(3
				(barrier setLoop: 1 setCel: 0 setCycle: End self)
			)
			(4 (barrier setCycle: Beg self))
			(5 (messager say: 1 6 1 0 self))
			(6
				(barrier setLoop: 0 signal: (& (barrier signal?) $fffe))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance atp1 of ForestView
	(properties
		x 146
		y 152
		view 422
		cel 1
	)
)

(instance atp2 of ForestView
	(properties
		x 224
		y 34
		view 421
		cel 1
	)
)

(instance atp3 of ForestView
	(properties
		x 146
		y 5
		view 424
		cel 2
	)
)

(instance atp4 of ForestView
	(properties
		x 4
		y 169
		view 422
	)
)

(instance stream1 of ForestView
	(properties
		x 69
		y 73
		view 420
		loop 2
		signal $4001
		detailLevel 2
	)
)

(instance stream2 of ForestView
	(properties
		x 133
		y 102
		view 420
		loop 4
		signal $4001
		detailLevel 2
	)
)

(instance stream3 of ForestView
	(properties
		x 290
		y 126
		view 420
		loop 6
		signal $4001
		detailLevel 2
	)
)

(instance extra1 of ForestView
	(properties
		x 84
		y -35
		view 421
		loop 1
		cel 1
		signal $4000
	)
)

(instance extra2 of ForestView
	(properties
		x 80
		y 143
		view 422
		loop 1
		cel 1
		signal $4000
	)
)

(instance barrier of Prop
	(properties
		noun 2
		approachX 84
		approachY 140
		y 146
		view 593
		signal $4000
	)
	
	(method (init)
		(self approachVerbs: 4 10 setPri: 1)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(80
					(if local0
						(messager say: 2 80 2)
					else
						(AutoTarget mouseX (- mouseY 10))
						(curRoom setScript: (ScriptID 13) 0 self)
					)
					(return 1)
				)
				(-80
					(curRoom setScript: sSpellBush)
					(return 1)
				)
				(10
					(if (not loop)
						(curRoom setScript: sJumpBush)
					else
						((ScriptID 50) doVerb: theVerb)
					)
				)
				(82 (curRoom doVerb: 82))
				(-82
					(if local0
						(messager say: 2 80 2)
						(curRoom setScript: 0)
						(theGame handsOn:)
						(return 1)
					else
						(self setScript: sSpellBush)
					)
				)
				(else 
					(if (Message msgSIZE 593 noun theVerb 0 1)
						(messager say: noun theVerb 0 0 0 593)
					else
						((ScriptID 50) doVerb: theVerb)
					)
				)
			)
		)
	)
)

(instance streamMat of Polygon
	(properties)
	
	(method (init)
		(super init: 134 101 175 101 176 91 141 92)
	)
)
