;;; Sierra Script 1.0 - (do not remove this comment)
(script# 530)
(include sci.sh)
(use Main)
(use GloryRm)
(use SwampView)
(use TellerIcon)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use StopWalk)
(use Grooper)
(use Motion)
(use Actor)
(use System)

(public
	rm530 0
)

(local
	local0
	theGGGTheObj_2CycleSpeed
	pEventX
	pEventY
	local4
	local5
)
(procedure (localproc_0106)
	(curRoom
		leftY: 146
		bottomX: 185
		addObstacle:
			((Polygon new:)
				type: 2
				init: 0 100 24 96 51 99 51 119 0 119
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 103 106 125 99 152 99 162 119 103 119
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 31 131 56 124 88 124 96 143 31 143
				yourself:
			)
			((Polygon new:)
				type: 2
				init:
					104
					154
					113
					143
					154
					143
					162
					141
					227
					141
					227
					156
					170
					156
					164
					164
					104
					165
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 7 156 38 150 83 164 83 179 62 184 34 184 34 168 7 168
				yourself:
			)
			((Polygon new:)
				type: 2
				init:
					0
					0
					319
					0
					319
					180
					234
					180
					229
					172
					261
					149
					246
					143
					244
					137
					231
					129
					206
					120
					143
					90
					0
					90
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 89 189 94 182 144 182 154 189
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 156 137 159 126 212 126 221 137
				yourself:
			)
	)
)

(instance rm530 of GloryRm
	(properties
		noun 1
		picture 530
		horizon 78
		north 556
		east 562
		south 541
		west 542
		topX 238
		rightY 120
	)
	
	(method (init)
		(self setRegions: 10)
		(super init: &rest)
		(atp1 init:)
		(atp2 init:)
		(atp3 init:)
		(atp4 init:)
		(atp5 init:)
		(atp6 init:)
		(atp7 init:)
		(atp8 init:)
		(if (not (Btst 363))
			(atp9 init:)
			(skeletonReflect init:)
		)
		(atp10 init:)
		((ScriptID 10 2) init:)
		(theWater init:)
		(theShore init:)
		(fShore init:)
		(aDoorMat init:)
		(walkHandler add: curRoom)
		(RemapColors 4 253 100 200)
		(if
		(and Night (not (Btst 459)) (not (ego has: 41)))
			(egoTell init: ego 530 10 128 9)
		)
		(switch prevRoomNum
			(556
				(self setScript: sFromNorth)
			)
			(562
				(self setScript: sFromEast)
			)
			(542
				(self setScript: sFromWest)
			)
			(541
				(self setScript: sFromSouth)
			)
			(543
				(self setScript: sFromWest)
			)
			(else 
				(theGame handsOn:)
				(self addObstacle: (fShore heading?))
				(ego
					x: 260
					y: 122
					init:
					setScaler: Scaler 100 60 125 65
					normalize:
				)
			)
		)
		(if (Btst 149)
			(walkHandler
				addToFront: atp3 atp4 atp5 atp6 atp7 atp8 atp10 fShore
			)
			(= gFShore fShore)
		)
		(theGame save: 1)
	)
	
	(method (doit)
		(super doit:)
		(if
		(and (aDoorMat onMe: (ego x?) (ego y?)) (not script))
			(if
			(and (OneOf (ego view?) 0 1 2 5) (not (Btst 149)))
				(self setScript: sDownWater)
			else
				(self setScript: sUpWater)
			)
		)
	)
	
	(method (dispose)
		(if (Btst 149)
			(walkHandler
				delete: atp3 atp4 atp5 atp6 atp7 atp8 atp10 fShore
			)
			(= gFShore 0)
		)
		(= gGTheObj_2X (ego x?))
		(= gGTheObj_2Y (ego y?))
		(theWater dispose: 1)
		(theShore dispose: 1)
		(aDoorMat dispose:)
		(egoTell dispose:)
		(walkHandler delete: curRoom)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(11
				(cond 
					((fShore onMe: ego) (self setScript: sGoGlide))
					((Btst 167) (messager say: 0 11 13))
					(else (self setScript: sGlideFromTuff))
				)
			)
			(23
				(cond 
					((Btst 167) (messager say: 0 23 13))
					((Btst 149) (messager say: 0 23 15))
					((not (theShore onMe: (ego x?) (ego y?))) (messager say: 0 23 16))
					(else (self setScript: (ScriptID 10 1)))
				)
			)
			(3
				(if (>= (ego y?) 180) (ego setPri: -1))
				(super doVerb: theVerb)
			)
			(91
				(if (Btst 167)
					(messager say: 0 11 13)
				else
					(curRoom setScript: (ScriptID 62))
				)
			)
			(else 
				((ScriptID 10) doVerb: theVerb)
			)
		)
	)
	
	(method (newRoom n)
		(switch n
			(542
				(= global349 0)
				(= global350 1)
			)
			(541
				(= global357 0)
				(= global349 1)
				(= global350 0)
			)
		)
		(super newRoom: n)
	)
	
	(method (notify param1)
		(if
		(and argc (not (curRoom script?)) (== param1 -2))
			(curRoom setScript: (ScriptID 10 5))
		else
			(localproc_0106)
		)
	)
	
	(method (addObstacle param1)
		(switch param1
			(poly8 (self bottomX: 113))
			(poly6 (self leftY: 109))
		)
		(super addObstacle: param1 &rest)
	)
)

(instance aDoorMat of Polygon
	(properties)
	
	(method (init)
		(super init: 251 127 274 136 262 155 238 140)
	)
)

(instance sGlideFromTuff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= disabledActions (| disabledActions $0003))
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
				)
				(localproc_0106)
				(Bset 149)
				(self setScript: (ScriptID 12) self)
			)
			(1
				(ego
					setLoop: -1
					setLoop: Grooper
					loop: 1
					setCycle: Walk
					view: 5
					setMotion: MoveTo (ego x?) (- (ego y?) 5) self
				)
			)
			(2
				(walkHandler
					addToFront: atp3 atp4 atp5 atp6 atp7 atp8 atp10 fShore
				)
				(= gFShore fShore)
				(theGame handsOn:)
				(theIconBar disable: 5)
				(self dispose:)
			)
		)
	)
)

(instance sGetBone of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 292 160 self)
			)
			(1
				(ego view: 4 setCel: 0 setLoop: 3 setCycle: End self)
			)
			(2
				(Bset 363)
				(messager say: 5 4 0 0 self)
			)
			(3
				(ego get: 29 1 setCycle: Beg self)
			)
			(4
				(ego normalize:)
				(ego loop: 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sUpWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 253 144 self)
			)
			(1
				(if (Btst 149)
					(walkHandler
						delete: atp3 atp4 atp5 atp6 atp7 atp8 atp10 fShore
					)
					(ego normalize: setMotion: MoveTo 267 118 self)
				else
					(ego
						view: 5354
						loop: 0
						cel: 0
						cycleSpeed: 6
						x: (+ (ego x?) 9)
						y: (+ (ego y?) 21)
						setCycle: End self
					)
					((ScriptID 10 2) hide:)
				)
				(= disabledActions (& disabledActions $fffc))
			)
			(2
				(curRoom north: 556)
				(Bclr 359)
				(Bclr 167)
				(Bclr 15)
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
					(curRoom addObstacle: (fShore heading?))
				)
				((ScriptID 10 2) hide:)
				(if (Btst 149)
					(ego
						moveSpeed: gGTheObj_2CycleSpeed
						setScaler: Scaler 100 60 125 65
						cycleSpeed: gGTheObj_2CycleSpeed
					)
					(Bclr 149)
				else
					(ego
						x: (+ (ego x?) 15)
						y: (- (ego y?) 28)
						view: 5
						changeGait: theGGGTheObj_2CycleSpeed
						moveSpeed: gGTheObj_2CycleSpeed
						cycleSpeed: gGTheObj_2CycleSpeed
						setScaler: Scaler 100 60 125 65
						normalize:
					)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGoGlide of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= disabledActions (| disabledActions $0003))
				(ego code: 0 setMotion: MoveTo 241 148 self)
			)
			(1
				(Bset 149)
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
				)
				(localproc_0106)
				(self setScript: (ScriptID 12) self)
			)
			(2
				(ego
					setLoop: -1
					setLoop: Grooper
					setCycle: Walk
					view: 5
					loop: 1
				)
				(messager say: 11 6 19 0 self)
			)
			(3
				(walkHandler
					addToFront: atp3 atp4 atp5 atp6 atp7 atp8 atp10 fShore
				)
				(= gFShore fShore)
				(theGame handsOn:)
				(theIconBar disable: 5)
				(self dispose:)
			)
		)
	)
)

(instance sUpShore of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gGTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					code: 0
					view: 5354
					loop: 0
					cel: 0
					cycleSpeed: 12
					x: (+ (ego x?) 9)
					y: (+ (ego y?) 21)
					setCycle: End self
				)
			)
			(1
				(curRoom north: 562)
				(Bclr 167)
				(Bclr 15)
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
					(curRoom
						addObstacle:
							((Polygon new:)
								type: 2
								init:
									215
									103
									231
									118
									279
									136
									319
									149
									319
									189
									0
									189
									0
									0
									220
									0
									220
									81
									179
									92
								yourself:
							)
					)
				)
				((ScriptID 10 2) hide:)
				(ego
					x: (+ (ego x?) 15)
					y: (- (ego y?) 28)
					view: 5
					moveSpeed: gGTheObj_2CycleSpeed
					cycleSpeed: gGTheObj_2CycleSpeed
					normalize:
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDownWater of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(walkHandler add: curRoom)
				(= gGTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego setMotion: MoveTo 256 147 self)
			)
			(1
				(= theGGGTheObj_2CycleSpeed egoGait)
				(= egoGait 0)
				(ego
					view: 5354
					loop: 3
					cel: 0
					cycleSpeed: 12
					x: (- (ego x?) 12)
					y: (+ (ego y?) 25)
					setCycle: End self
				)
			)
			(2
				(curRoom north: 0)
				(= disabledActions (| disabledActions $0003))
				(Bset 167)
				(Bset 15)
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
				)
				(localproc_0106)
				(ego
					view: 536
					x: (- (ego x?) 9)
					y: (- (ego y?) 21)
					setLoop: -1
					loop: 5
					moveSpeed: 12
					cycleSpeed: 12
					setCycle: StopWalk 538
				)
				((ScriptID 10 2)
					x: (ego x?)
					y: (ego y?)
					loop: (+ (ego loop?) 8)
					cel: (ego cel?)
					show:
				)
				(if (== (ego trySkill: 0 300) -1)
					(if (or (== heroType 0) (== heroType 3))
						(messager say: 11 6 6)
					else
						(messager say: 11 6 7)
					)
					(Bset 359)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFromNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom addObstacle: (fShore heading?))
				(ego
					x: 238
					y: 70
					init:
					normalize:
					setScaler: Scaler 100 60 125 65
					setMotion: PolyPath 238 80 self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFromEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom addObstacle: (fShore heading?))
				(ego
					x: 314
					y: 122
					init:
					normalize:
					setScaler: Scaler 100 60 125 65
					setMotion: PolyPath 300 122 self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFromWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (or (Btst 149) (Btst 167))
					(localproc_0106)
					(if (Btst 149)
						(ego
							x: -5
							y: 129
							init:
							normalize:
							setLoop: -1
							setLoop: Grooper
							setCycle: Walk
							view: 5
							setMotion: PolyPath 10 129 self
						)
					else
						(ego
							x: -5
							y: 129
							init:
							normalize:
							view: 536
							setLoop: -1
							loop: 5
							moveSpeed: 12
							cycleSpeed: 12
							setCycle: StopWalk 538
							setMotion: PolyPath 10 129 self
						)
						((ScriptID 10 2)
							x: (ego x?)
							y: (ego y?)
							loop: (+ (ego loop?) 8)
							cel: (ego cel?)
							show:
						)
					)
				else
					(curRoom addObstacle: (atp6 heading?))
					(ego
						x: -5
						y: (atp6 approachY?)
						init:
						normalize:
						setScaler: Scaler 100 60 125 65
						setMotion: PolyPath (atp6 approachX?) (atp6 approachY?) self
					)
				)
			)
			(1
				(theGame handsOn:)
				(if (Btst 149) (theIconBar disable: 5))
				(self dispose:)
			)
		)
	)
)

(instance sFromSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (or (Btst 149) (Btst 167))
					(localproc_0106)
					(if (Btst 149)
						(ego
							x: 175
							y: 200
							init:
							normalize:
							setLoop: -1
							setLoop: Grooper
							setCycle: Walk
							loop: 3
							view: 5
							setMotion: PolyPath 175 180 self
						)
					else
						(ego
							init:
							normalize:
							view: 536
							x: 175
							y: 200
							scaleSignal: 4
							setScaler: Scaler 100 60 125 65
							moveSpeed: 12
							cycleSpeed: 6
							changeGait: -1 0
							setCycle: StopWalk 538
							loop: 3
							setMotion: PolyPath 175 180 self
						)
						((ScriptID 10 2)
							x: (ego x?)
							y: (ego y?)
							loop: (+ (ego loop?) 8)
							cel: (ego cel?)
							show:
						)
					)
				else
					(curRoom addObstacle: (atp8 heading?))
					(ego
						x: (atp8 approachX?)
						y: (atp8 approachY?)
						init:
						normalize: 3
					)
					(= cycles 2)
				)
			)
			(1
				(theGame handsOn:)
				(if (Btst 149) (theIconBar disable: 5))
				(self dispose:)
			)
		)
	)
)

(instance atp1 of View
	(properties
		x 24
		y 48
		view 530
		signal $4000
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)

(instance atp2 of View
	(properties
		noun 2
		x 285
		y 149
		priority 1
		fixPriority 1
		view 530
		cel 1
		signal $5000
	)
)

(instance atp3 of SwampView
	(properties
		noun 7
		approachX 43
		approachY 157
		x 7
		y 151
		view 530
		loop 1
		cel 4
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly3 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x y
			priority: (atp3 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly3 of SwampPoly
	(properties)
	
	(method (init)
		(super init: 10 161 49 161 49 157 16 157)
		(return self)
	)
)

(instance atp4 of SwampView
	(properties
		noun 7
		approachX 144
		approachY 152
		x 163
		y 156
		view 530
		loop 1
		cel 1
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly4 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 7)
			priority: (atp4 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly4 of SwampPoly
	(properties)
	
	(method (init)
		(super
			init: 116 158 149 156 161 150 209 149 209 144 157 145 146 151 116 152
		)
		(return self)
	)
)

(instance atp5 of SwampView
	(properties
		noun 7
		approachX 73
		approachY 131
		x 72
		y 140
		view 530
		loop 1
		cel 2
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly5 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 16)
			priority: (atp5 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly5 of SwampPoly
	(properties)
	
	(method (init)
		(super init: 43 137 88 136 88 130 43 131)
		(return self)
	)
)

(instance atp6 of SwampView
	(properties
		noun 7
		approachX 23
		approachY 106
		x 16
		y 114
		view 530
		loop 2
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly6 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 13)
			priority: (atp6 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly6 of SwampPoly
	(properties
		type $0002
	)
	
	(method (init)
		(super
			init: 0 0 319 0 319 189 0 189 0 111 40 110 40 104 0 105
		)
		(return self)
	)
)

(instance atp7 of SwampView
	(properties
		noun 7
		approachX 139
		approachY 113
		x 140
		y 117
		view 530
		loop 1
		cel 2
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly7 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 16)
			priority: (atp7 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly7 of SwampPoly
	(properties)
	
	(method (init)
		(super init: 111 113 157 113 157 107 112 107)
		(return self)
	)
)

(instance atp8 of SwampView
	(properties
		noun 7
		approachX 115
		approachY 184
		x 120
		y 189
		view 530
		loop 1
		cel 3
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly8 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 16)
			priority: (atp8 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly8 of SwampPoly
	(properties
		type $0002
	)
	
	(method (init)
		(super
			init: 110 180 120 180 120 189 319 189 319 0 0 0 0 189 110 189
		)
		(return self)
	)
)

(instance atp9 of View
	(properties
		noun 5
		x 275
		y 175
		view 530
		loop 3
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(if (Btst 363)
				(messager say: 5 4 11)
				(return 1)
			else
				(if
				(== ((curRoom obstacles?) at: 0) (fShore heading?))
					(ego setScript: sGetBone)
				else
					(messager say: 5 4 12)
				)
				(return 1)
			)
		)
		(return (super doVerb: theVerb &rest))
	)
)

(instance skeletonReflect of View
	(properties
		x 275
		y 189
		view 530
		loop 7
		signal $5000
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb &rest)
	)
)

(instance atp10 of SwampView
	(properties
		noun 7
		approachX 180
		approachY 130
		x 180
		y 135
		view 530
		loop 2
		cel 2
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly10 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 16)
			priority: (atp10 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly10 of SwampPoly
	(properties)
	
	(method (init)
		(super init: 164 129 183 132 207 127 181 121)
		(return self)
	)
)

(instance theWater of Polygon
	(properties)
	
	(method (init)
		(super init: 0 99 166 99 236 116 319 147 319 189 0 189)
	)
	
	(method (dispose param1)
		(if (and argc param1) (super dispose:))
	)
)

(instance fShore of Feature
	(properties
		approachX 264
		approachY 122
		x 220
		y 119
	)
	
	(method (init)
		(= onMeCheck theShore)
		(= heading (roomPoly init:))
		(super init:)
	)
	
	(method (dispose)
		(if heading (heading dispose: 1))
		(super dispose:)
	)
	
	(method (handleEvent event)
		(= pEventX (event x?))
		(= pEventY (event y?))
		(super handleEvent: event)
	)
	
	(method (doVerb theVerb &tmp theEgoStats)
		(switch theVerb
			(3
				(curRoom setScript: sUpWater)
			)
			(4
				(if (not (self onMe: (ego x?) (ego y?)))
					(curRoom setScript: sUpWater)
				else
					(super doVerb: theVerb)
				)
			)
			(10
				(if (not (self onMe: (ego x?) (ego y?)))
					(if (or (Btst 149) (Btst 167))
						(messager say: 0 10 13)
					else
						(if (< [egoStats 15] 150)
							(= theEgoStats 150)
						else
							(= theEgoStats [egoStats 15])
						)
						(= local4 (/ theEgoStats 3))
						(if (<= local4 (= local5 (ego distanceTo: self)))
							(ego
								setHeading: (GetAngle (ego x?) (ego y?) pEventX pEventY)
							)
							((ScriptID 10) notify: local5 local4)
							(curRoom setScript: (ScriptID 10 4) 0)
						else
							(curRoom setScript: (ScriptID 10 3) 0 self)
						)
					)
				else
					(messager say: 0 10 14)
				)
			)
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance theShore of Polygon
	(properties)
	
	(method (init)
		(super
			init: 163 96 241 75 265 79 319 74 319 150 274 135 217 106
		)
	)
	
	(method (dispose param1)
		(if (and argc param1) (super dispose:))
	)
)

(instance roomPoly of Polygon
	(properties
		type $0002
	)
	
	(method (init)
		(super
			init:
				215
				103
				232
				124
				309
				143
				308
				156
				272
				156
				282
				162
				319
				162
				319
				189
				0
				189
				0
				0
				220
				0
				220
				81
				179
				92
		)
		(return self)
	)
	
	(method (dispose param1)
		(if (and argc param1) (super dispose:))
	)
)

(instance egoTell of Teller
	(properties)
)

(instance splash of Prop
	(properties
		signal $4000
	)
)
