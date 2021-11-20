;;; Sierra Script 1.0 - (do not remove this comment)
(script# 535)
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
(use Reverse)
(use Grooper)
(use Motion)
(use Actor)
(use System)

(public
	rm535 0
)

(local
	local0
	theGGGTheObj_2CycleSpeed
	pEventX
	pEventY
	local4
	local5
)
(procedure (localproc_00f2)
	(curRoom
		addObstacle:
			((Polygon new:)
				type: 2
				init: 0 99 33 100 42 112 41 119 0 124
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 9 138 57 125 88 135 87 147 55 153 13 158
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 99 146 128 139 174 150 174 163 125 163 100 156
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 99 123 155 123 155 133 132 135 115 139 94 138
				yourself:
			)
			((Polygon new:)
				type: 2
				init:
					151
					101
					164
					86
					0
					86
					0
					0
					319
					0
					319
					173
					292
					167
					253
					165
					256
					171
					226
					176
					192
					166
					205
					150
					249
					154
					279
					139
					249
					133
					234
					146
					203
					144
					169
					136
					168
					126
					188
					119
					187
					116
					172
					118
					165
					123
					151
					120
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 0 189 0 177 120 177 122 189
				yourself:
			)
	)
)

(instance rm535 of GloryRm
	(properties
		picture 530
		horizon 78
		north 562
		east 800
		south 543
		west 541
		topX 238
		rightY 120
	)
	
	(method (init)
		(self setRegions: 10)
		(super init: &rest)
		(tree approachVerbs: 4 init:)
		(atp1 init:)
		(atp2 init:)
		(atp3 init:)
		(atp4 init:)
		(atp6 init:)
		(atp8 init:)
		(atp9 init:)
		(atp10 init:)
		(atp11 init:)
		(atp12 init:)
		(walkHandler add: self)
		(theWater init:)
		(theShore init:)
		(fWater init:)
		(fShore init:)
		(aDoorMat init:)
		(RemapColors 4 253 100 200)
		(if
		(and Night (not (Btst 459)) (not (ego has: 41)))
			(egoTell init: ego 535 11 128 10)
		)
		((ScriptID 10 2) init:)
		(switch prevRoomNum
			(800
				(self setScript: sFromEast)
			)
			(562
				(self setScript: sFromNorth)
			)
			(541
				(self setScript: sFromWest)
			)
			(543
				(self setScript: sFromSouth)
			)
			(else 
				(curRoom addObstacle: (fShore heading?))
				(ego
					x: 260
					y: 122
					init:
					normalize:
					setScaler: Scaler 100 60 125 65
				)
				(theGame handsOn:)
			)
		)
		(if (Btst 149)
			(walkHandler
				addToFront: atp1 atp2 atp6 atp10 atp11 atp12 fShore
			)
			(= gFShore fShore)
		)
		(theGame save: 1)
	)
	
	(method (doit)
		(super doit:)
		(if
		(and (aDoorMat onMe: (ego x?) (ego y?)) (not script))
			(cond 
				(
				(and (OneOf (ego view?) 0 1 2 5) (not (Btst 149))) (self setScript: sDownWater))
				((Btst 167) (self setScript: sUpWater))
			)
		)
	)
	
	(method (dispose)
		(if (Btst 149)
			(walkHandler
				delete: atp1 atp2 atp6 atp10 atp11 atp12 fShore
			)
			(= gFShore 0)
		)
		(= gGTheObj_2X (ego x?))
		(= gGTheObj_2Y (ego y?))
		(theWater dispose: 1)
		(theShore dispose: 1)
		(egoTell dispose:)
		(aDoorMat dispose:)
		(walkHandler delete: curRoom)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(21
				(if (not (Btst 167))
					(curRoom setScript: (ScriptID 32) 0 21)
				)
			)
			(11
				(cond 
					((fShore onMe: ego) (self setScript: sGoGlide))
					((Btst 167) (messager say: 0 11 14))
					(else (self setScript: sGlideFromTuff))
				)
			)
			(23
				(cond 
					((Btst 167) (messager say: 0 23 14))
					((Btst 149) (messager say: 0 23 21))
					((not (theShore onMe: (ego x?) (ego y?))) (messager say: 0 23 20))
					(else (self setScript: (ScriptID 10 1)))
				)
			)
			(3
				(if (>= (ego y?) 181)
					(ego setPri: -1)
					(super doVerb: theVerb)
				else
					(super doVerb: theVerb)
				)
			)
			(1 (messager say: 1 1 0))
			(91
				(if (Btst 167)
					(messager say: 0 11 14)
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
		(= global357 0)
		(switch n
			(541
				(= global349 1)
				(= global350 0)
			)
			(543
				(= global349 2)
				(= global350 -1)
			)
		)
		(super newRoom: n)
	)
	
	(method (notify param1)
		(if
		(and argc (not (curRoom script?)) (== param1 -2))
			(curRoom setScript: (ScriptID 10 5))
		else
			(localproc_00f2)
		)
	)
	
	(method (addObstacle param1)
		(switch param1
			(poly12 (self bottomX: 83))
			(poly2 (self leftY: 114))
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
				(localproc_00f2)
				(Bset 149)
				(self setScript: (ScriptID 12) self)
			)
			(1
				(ego
					setLoop: -1
					setLoop: Grooper
					loop: 0
					setCycle: Walk
					view: 5
					setMotion: MoveTo (ego x?) (- (ego y?) 5) self
				)
			)
			(2
				(walkHandler
					addToFront: atp1 atp2 atp6 atp10 atp11 atp12 fShore
				)
				(= gFShore fShore)
				(theGame handsOn:)
				(theIconBar disable: 5)
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
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
				)
				(ego setMotion: MoveTo 241 148 self)
			)
			(1
				(Bset 149)
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
				)
				(localproc_00f2)
				(self setScript: (ScriptID 12) self)
			)
			(2
				(ego
					setLoop: -1
					setLoop: Grooper
					loop: 1
					setCycle: Walk
					view: 5
				)
				(messager say: 8 6 29 0 self)
			)
			(3
				(walkHandler
					addToFront: atp1 atp2 atp6 atp10 atp11 atp12 fShore
				)
				(= gFShore fShore)
				(theGame handsOn:)
				(theIconBar disable: 5)
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
				(= disabledActions (| disabledActions $0003))
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
				(curRoom east: 0)
				(Bset 167)
				(Bset 15)
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 0 99 33 100 42 112 41 119 0 124
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 9 138 57 125 88 135 87 147 55 153 13 158
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 99 146 128 139 174 150 174 163 125 163 100 156
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 101 130 112 122 128 124 155 123 155 133 132 135 115 139 97 136
							yourself:
						)
						((Polygon new:)
							type: 2
							init:
								151
								101
								164
								86
								0
								86
								0
								0
								319
								0
								319
								173
								292
								167
								253
								165
								256
								171
								226
								176
								192
								166
								205
								150
								249
								154
								279
								139
								249
								133
								234
								146
								203
								144
								169
								136
								168
								126
								188
								119
								187
								116
								172
								118
								165
								123
								151
								120
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 0 189 0 177 120 177 122 189
							yourself:
						)
				)
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
						(messager say: 8 6 3)
					else
						(messager say: 8 6 4)
					)
					(Bset 359)
				)
				(theGame handsOn:)
				(if (Btst 149) (theIconBar disable: 5))
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
					(localproc_00f2)
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
					(curRoom addObstacle: (atp2 heading?))
					(ego
						x: -5
						y: (atp2 approachY?)
						init:
						normalize:
						setScaler: Scaler 100 60 125 65
						setMotion: PolyPath (atp2 approachX?) (atp2 approachY?) self
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
					(localproc_00f2)
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
					(curRoom addObstacle: (atp1 heading?))
					(ego
						x: (atp1 approachX?)
						y: (atp1 approachY?)
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
				(if (and (== heroType 3) (not (Btst 393)))
					(Bset 393)
					(messager say: 8 6 27 0 self)
				else
					(= ticks 1)
				)
			)
			(2
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

(instance sClimbTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setPri: (+ (ego priority?) 1)
					setScale:
					setMotion: MoveTo 294 153 self
				)
			)
			(1
				(ego
					view: 7
					setCel: 0
					setLooper: 0
					setLoop: 3 1
					setCycle: Fwd
					setMotion: MoveTo (ego x?) (- (ego y?) 70) self
				)
			)
			(2 (messager say: 2 4 9 0 self))
			(3
				(ego
					setCycle: Rev
					setMotion: MoveTo (ego x?) (+ (ego y?) 70) self
				)
			)
			(4
				(ego
					normalize:
					setScaler: Scaler 100 60 125 65
					setMotion: MoveTo 311 150 self
				)
			)
			(5
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
				(Bclr 167)
				(walkHandler delete: curRoom)
				(ego setMotion: PolyPath 253 144 self)
			)
			(1
				(if (Btst 149)
					(walkHandler
						delete: atp1 atp2 atp6 atp10 atp11 atp12 fShore
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
				(curRoom north: 562)
				(curRoom east: 800)
				(Bclr 359)
				(Bclr 15)
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
					(curRoom addObstacle: (fShore heading?))
				)
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

(instance atp1 of SwampView
	(properties
		noun 12
		approachX 227
		approachY 162
		x 257
		y 166
		view 530
		loop 1
		cel 1
	)
	
	(method (init)
		(super init: &rest)
		(if (not heading) (= heading (poly1 init:)))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 7)
			priority: (atp1 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly1 of SwampPoly
	(properties)
	
	(method (init)
		(super
			init: 210 168 241 168 246 161 283 161 283 158 245 154 209 164
		)
		(return self)
	)
)

(instance atp2 of SwampView
	(properties
		noun 12
		approachX 28
		approachY 114
		x 10
		y 120
		view 530
		loop 2
		cel 2
	)
	
	(method (init)
		(super init: &rest)
		(if (not heading) (= heading (poly2 init:)))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 16)
			priority: (atp2 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly2 of SwampPoly
	(properties
		type $0002
	)
	
	(method (init)
		(super
			init: 0 0 319 0 319 189 0 189 0 116 31 116 31 111 0 111
		)
		(return self)
	)
)

(instance atp6 of SwampView
	(properties
		noun 12
		approachX 202
		approachY 132
		x 205
		y 139
		view 530
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(if (not heading) (= heading (poly6 init:)))
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
	(properties)
	
	(method (init)
		(super init: 185 133 225 140 225 135 187 129)
		(return self)
	)
)

(instance atp10 of SwampView
	(properties
		noun 12
		approachX 133
		approachY 152
		x 135
		y 158
		view 530
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(if (not heading) (= heading (poly10 init:)))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 13)
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
		(super init: 116 149 162 155 162 159 114 153)
		(return self)
	)
)

(instance atp11 of SwampView
	(properties
		noun 12
		approachX 49
		approachY 141
		x 53
		y 148
		view 530
		loop 2
	)
	
	(method (init)
		(super init: &rest)
		(if (not heading) (= heading (poly11 init:)))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 13)
			priority: (atp11 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly11 of SwampPoly
	(properties)
	
	(method (init)
		(super init: 25 149 74 142 74 138 60 136 24 145)
		(return self)
	)
)

(instance atp12 of SwampView
	(properties
		noun 12
		approachX 86
		approachY 181
		x 86
		y 190
		view 530
		loop 2
		cel 2
	)
	
	(method (init)
		(super init: &rest)
		(if (not heading) (= heading (poly12 init:)))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 13)
			priority: (atp12 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly12 of SwampPoly
	(properties
		type $0002
	)
	
	(method (init)
		(super
			init: 0 0 319 0 319 192 93 192 93 181 74 181 74 192 0 192
		)
		(return self)
	)
)

(instance atp3 of View
	(properties
		noun 12
		x 305
		y 189
		view 530
		loop 2
		cel 3
		signal $4000
	)
)

(instance atp4 of View
	(properties
		noun 12
		x 8
		y 189
		view 530
		loop 2
		cel 3
		signal $4000
	)
)

(instance atp8 of View
	(properties
		noun 7
		approachX 118
		approachY 137
		x 120
		y 134
		view 530
		loop 2
		cel 5
		signal $4000
	)
)

(instance atp9 of View
	(properties
		noun 12
		x 219
		y 90
		view 530
		loop 2
		cel 4
		signal $4000
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

(instance fWater of Feature
	(properties
		y 10
	)
	
	(method (init)
		(= onMeCheck theWater)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not (self onMe: (ego x?) (ego y?)))
					(curRoom setScript: sDownWater)
				else
					((ScriptID 10) doVerb: theVerb)
				)
			)
			(else 
				((ScriptID 10) doVerb: theVerb)
			)
		)
	)
)

(instance tree of Feature
	(properties
		noun 2
		nsLeft 287
		nsTop 65
		nsRight 299
		nsBottom 146
		sightAngle 40
		approachX 311
		approachY 150
		x 290
		y 153
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(if
			(== ((curRoom obstacles?) at: 0) (fShore heading?))
				(if (== (ego trySkill: 11 100) 1)
					(ego setScript: sClimbTree)
				else
					(messager say: 2 4 10)
				)
			else
				(messager say: 2 4 13)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance fShore of Feature
	(properties
		approachX 269
		approachY 122
		x 269
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
						(messager say: 0 10 14)
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
							(curRoom setScript: (ScriptID 10 4) 0 self)
						else
							(curRoom setScript: (ScriptID 10 3) 0 self)
						)
					)
				else
					(messager say: 0 10 16)
				)
			)
			(24 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance roomPoly of SwampPoly
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
				284
				144
				300
				144
				319
				156
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
)

(instance theShore of Polygon
	(properties)
	
	(method (init)
		(super
			init: 163 96 241 75 265 79 319 74 319 162 274 135 220 120
		)
	)
	
	(method (dispose param1)
		(if (and argc param1) (super dispose:))
	)
)

(instance egoTell of Teller
	(properties)
)
