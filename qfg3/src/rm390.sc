;;; Sierra Script 1.0 - (do not remove this comment)
(script# 390)
(include sci.sh)
(use Main)
(use OccasionalCycle)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm390 0
)

(local
	local0
)
(instance rm390 of Rm
	(properties
		noun 3
		picture 390
		horizon 65
	)
	
	(method (init)
		(HandsOff)
		(theIconBar disable: 9)
		(ego
			x: 190
			y: 250
			setScale: Scaler 100 65 128 50
			actions: egoActions
			init:
			normalize:
		)
		(super init:)
		(cSound number: 390 setLoop: -1 play: 127)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 189 0 155 140 179 143 189
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						319
						147
						290
						149
						229
						133
						148
						138
						122
						127
						81
						115
						78
						104
						58
						92
						100
						89
						214
						86
						283
						87
						277
						91
						248
						97
						286
						107
						319
						104
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 62 319 88 306 86 289 81 280 72 229 69 232 62
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 164 76 148 81 100 85 64 83 52 89 0 99 0 67 162 67
					yourself:
				)
		)
		(soundFx number: 391 setLoop: -1 play: 127)
		(peacePool init:)
		(mountains init:)
		(trees init:)
		(rocks init:)
		(fountain setCycle: Fwd init:)
		(leftRip setCycle: Fwd init:)
		(rightRip setCycle: Fwd init:)
		(theMoon init: setPri: 1)
		(if (not Night)
			(cheetahBody init: approachVerbs: 4 addToPic:)
			(cheetahHead init: approachVerbs: 4)
			(cheetahTail
				setCycle: OccasionalCycle self 1 20 200
				init:
				approachVerbs: 4
			)
		)
		(ego setScript: egoEnters)
		(if (and (not Night) (not (Btst 98)))
			(curRoom setScript: impalaDrinks)
		)
	)
	
	(method (dispose)
		(soundFx stop:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(81 (messager say: 4 6 1))
			(88 (messager say: 4 6 1))
			(83 (messager say: 4 6 1))
			(20 (messager say: 4 6 1))
			(11 (messager say: 4 6 1))
			(12 (messager say: 4 6 1))
			(56 (messager say: 4 6 1))
			(76
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID 12 0) self 76)
				else
					(messager say: 4 6 9)
				)
			)
			(74
				(if (not (curRoom script?))
					(ego code: 0)
					(self setScript: goToBed)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(self setScript: runeAppears)
	)
)

(instance runeAppears of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: 3 76 0 0 self)
			)
			(1
				(rune init: setPri: 14 cycleSpeed: 4 setCycle: Fwd)
				(HandsOn)
				(soundFx number: 391 setLoop: -1 play:)
				(self dispose:)
			)
		)
	)
)

(instance goToBed of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 179 168 self)
			)
			(1
				(ego view: 35 loop: 0 cel: 0 x: 198 setCycle: End self)
			)
			(2
				(if (= temp0 (PalVary pvGET_CURRENT_STEP))
					(if (< temp0 64)
						(PalVary pvCHANGE_TICKS 3)
						(= seconds 5)
					else
						(self cue:)
					)
				else
					(PalVary pvINIT 310 3)
					(Bset 81)
					(= seconds 5)
				)
			)
			(3
				(theMoon moveSpeed: 4 setMotion: MoveTo 335 15 self)
			)
			(4
				(PalVary pvREVERSE 3)
				(Bclr 81)
				(= seconds 4)
			)
			(5
				((ScriptID 7 7) init: 5 40)
				(ego setCycle: Beg self)
			)
			(6
				(ego
					normalize: 6
					cel: 6
					x: 179
					changeGait: 0
					code: outCheck
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 92 119 self)
			)
			(1
				(ego view: 4 loop: 0 cel: 0 setCycle: End)
				(= seconds 3)
			)
			(2
				(switch local0
					(0
						(messager say: 1 4)
						(= [egoStats 17] (ego maxStamina:))
					)
					(1
						(messager say: 1 25)
						(ego get: 37 drop: 15 1 solvePuzzle: 258 3)
					)
					(2
						(if Night
							(if (not (Btst 259))
								(messager say: 1 50 5)
								((inventory at: 39) state: 1 cel: 7)
								(Cursor cel: 7)
							)
							(ego solvePuzzle: 259 6 2)
						else
							(messager say: 1 50 6)
						)
					)
				)
				(ego setCycle: Beg self)
			)
			(3
				(ego normalize:)
				(soundFx number: 391 setLoop: -1 play:)
				(= local0 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: 190 y: 250 init: setMotion: MoveTo 190 180 self)
			)
			(1 (messager say: 4 6 4 0 self))
			(2
				(if Night (messager say: 4 6 8 0 self) else (self cue:))
			)
			(3
				(ego code: outCheck)
				(theIconBar enable: 9)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance impalaDrinks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 98)
				(impala init:)
				(= seconds 6)
			)
			(1
				(impala loop: 1 setCycle: End)
				(= seconds 7)
			)
			(2
				(impala
					setLoop: 3
					setCycle: Fwd
					setMotion: MoveTo 330 90 self
				)
			)
			(3
				(impala dispose:)
				(self dispose:)
			)
		)
	)
)

(instance theMoon of Actor
	(properties
		x -10
		y 15
		noun 10
		view 393
		signal $6000
	)
)

(instance impala of Actor
	(properties
		x 255
		y 92
		noun 8
		view 392
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(super doVerb: theVerb)
		else
			(curRoom doVerb: theVerb)
		)
	)
)

(instance cheetahBody of View
	(properties
		x 116
		y 62
		noun 7
		approachX 111
		approachY 85
		view 391
		loop 1
		priority 4
		signal $4015
	)
)

(instance cheetahHead of Prop
	(properties
		x 114
		y 62
		noun 7
		approachX 111
		approachY 85
		view 391
		priority 4
		signal $0010
	)
	
	(method (doit)
		(cond 
			((< (ego x?) 106) (self cel: 0))
			((< (ego x?) 212) (self cel: 1))
			(else (self cel: 2))
		)
		(super doit: &rest)
	)
)

(instance cheetahTail of Prop
	(properties
		x 104
		y 58
		noun 7
		approachX 111
		approachY 85
		view 391
		loop 2
		priority 4
		signal $0010
		detailLevel 3
	)
)

(instance fountain of Prop
	(properties
		x 163
		y 113
		noun 1
		view 390
		loop 2
		signal $4000
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(peacePool doVerb: theVerb)
	)
)

(instance rune of Prop
	(properties
		x 81
		y 49
		noun 5
		view 393
		loop 3
		signal $4000
	)
)

(instance leftRip of Prop
	(properties
		x 129
		y 106
		noun 1
		view 390
		signal $4000
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(peacePool doVerb: theVerb)
	)
)

(instance rightRip of Prop
	(properties
		x 193
		y 105
		noun 1
		view 390
		loop 1
		signal $4000
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(peacePool doVerb: theVerb)
	)
)

(instance mountains of Feature
	(properties
		x 164
		y 30
		noun 2
		nsTop 24
		nsLeft 126
		nsBottom 37
		nsRight 203
		sightAngle 180
	)
)

(instance rocks of Feature
	(properties
		x 84
		y 58
		noun 9
		nsTop 41
		nsLeft 60
		nsBottom 76
		nsRight 109
		sightAngle 180
	)
)

(instance trees of Feature
	(properties
		x 32
		y 88
		noun 6
		nsBottom 177
		nsRight 65
		sightAngle 180
	)
)

(instance peacePool of Feature
	(properties
		x 180
		y 18
		noun 1
		sightAngle 40
		onMeCheck $0100
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(25
				(= local0 1)
				(ego setScript: getWater)
			)
			(50
				(= local0 2)
				(ego setScript: getWater)
			)
			(4
				(= local0 0)
				(ego setScript: getWater)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance outCheck of Code
	(properties)
	
	(method (doit &tmp theDay temp1 theClock)
		(if (GameIsRestarting)
			(soundFx number: 391 setLoop: -1 play:)
		)
		(= theDay Day)
		(if (or (!= timeODay 6) (> Clock 500)) (++ theDay))
		(if
			(or
				(and (< (= theClock Clock) 601) (> theClock -1))
				(and (> theClock 2999) (< theClock 3601))
			)
			(if (and (< theClock 601) (> theClock -1))
				(theMoon x: (+ 180 (/ theClock 5)))
			else
				(theMoon x: (+ 60 (/ (- theClock 3000) 5)))
			)
		else
			(theMoon x: -50)
		)
		(if (ego edgeHit?) (curRoom newRoom: 160))
	)
)

(instance egoActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)
