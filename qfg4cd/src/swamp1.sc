;;; Sierra Script 1.0 - (do not remove this comment)
(script# 541)
(include sci.sh)
(use Main)
(use GloryRm)
(use SwampView)
(use Scaler)
(use PolyPath)
(use Polygon)
(use StopWalk)
(use Grooper)
(use Motion)
(use Actor)
(use System)

(public
	swamp1 0
)

(procedure (localproc_00be)
	(curRoom
		topX: 124
		bottomX: 151
		rightY: 167
		leftY: 149
		addObstacle:
			((Polygon new:)
				type: 2
				init: 0 118 40 118 43 133 10 136 0 132
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 14 142 75 129 116 131 142 150 102 153 69 151 14 151
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 0 159 39 157 40 165 84 165 90 181 39 180 33 170 0 171
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 130 153 182 156 182 170 132 178 100 178 94 164
				yourself:
			)
			((Polygon new:)
				type: 2
				init:
					117
					127
					150
					118
					173
					121
					185
					113
					245
					113
					246
					134
					223
					137
					172
					138
					133
					141
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 183 174 209 169 242 175 243 189 183 189
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 191 153 233 148 261 148 311 151 311 166 274 167 234 168 193 165
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 237 137 254 136 280 142 244 142
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 319 94 319 144 291 144 262 124 261 105
				yourself:
			)
	)
)

(instance swamp1 of GloryRm
	(properties
		picture 550
		horizon 118
		north 3
		east 4
		south 2
		west 1
	)
	
	(method (init)
		(self setRegions: 10)
		(super init: &rest)
		(atp1 init:)
		(atp2 init:)
		(atp3 init:)
		(atp4 init:)
		(atp4Refect init:)
		(atp5 init:)
		(atp6 init:)
		(atp7 init:)
		(atp7Reflect init:)
		(atp8 init:)
		(atp8Reflect init:)
		(atp9 init:)
		(atp10 init:)
		(atp11 init:)
		((ScriptID 10 2) init:)
		(walkHandler add: curRoom)
		(switch prevRoomNum
			(545
				(cond 
					((and (< gGTheObj_2X 320) (< gGTheObj_2Y 200)) (self setScript: sFromSouth))
					((> gGTheObj_2X 320) (self setScript: sFromWest))
					(else (self setScript: sFromNorth))
				)
			)
			(530
				(self setScript: sFromNorth)
			)
			(535
				(self setScript: sFromEast)
			)
			(542
				(if (< gGTheObj_2X 0)
					(self setScript: sFromEast)
				else
					(self setScript: sFromSouth)
				)
			)
			(543
				(if (> gGTheObj_2X 320)
					(self setScript: sFromWest)
				else
					(self setScript: sFromNorth)
				)
			)
			(else 
				(ego
					x: 160
					y: 100
					init:
					normalize:
					setScaler: Scaler 100 60 125 65
				)
				(theGame handsOn:)
			)
		)
		(if (Btst 149)
			(walkHandler
				addToFront: atp1 atp2 atp3 atp5 atp6 atp9 atp10 atp11
			)
		)
	)
	
	(method (dispose)
		(if (Btst 149)
			(walkHandler
				delete: atp1 atp2 atp3 atp5 atp6 atp9 atp10 atp11
			)
		)
		(= gGTheObj_2X (ego x?))
		(= gGTheObj_2Y (ego y?))
		(walkHandler delete: curRoom)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (>= (ego y?) 175) (ego setPri: -1))
			)
			(11
				(if (Btst 167)
					(messager say: 0 11 7)
				else
					(self setScript: sGlideFromTuff)
				)
			)
			(91
				(if (Btst 167)
					(messager say: 0 11 7)
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
			(1
				(= global357 0)
				(++ global350)
			)
			(2
				(= global357 0)
				(++ global349)
			)
			(3
				(++ global357)
				(-- global349)
			)
			(4
				(= global357 0)
				(-- global350)
			)
		)
		(super
			newRoom:
				(cond 
					((>= (+ global349 global350) 4) 545)
					((and (== global349 1) (== global350 -1)) 535)
					((and (<= global349 0) (<= global350 0)) 530)
					((> global357 3) 530)
					(else (+ (mod (+ (- global349 global350) 2) 3) 541))
				)
		)
	)
	
	(method (notify param1)
		(if
		(and argc (not (curRoom script?)) (== param1 -2))
			(curRoom setScript: (ScriptID 10 5))
		else
			(localproc_00be)
		)
	)
	
	(method (addObstacle param1)
		(switch param1
			(poly6 (self leftY: 164))
			(poly9 (self leftY: 127))
			(poly2 (self topX: 208))
			(poly5 (self bottomX: 213))
			(poly10 (self rightY: 134))
		)
		(super addObstacle: param1 &rest)
	)
)

(instance sGlideFromTuff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
				)
				(= disabledActions (| disabledActions $0003))
				(localproc_00be)
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
					addToFront: atp1 atp2 atp3 atp5 atp6 atp9 atp10 atp11
				)
				(theGame handsOn:)
				(theIconBar disable: 5)
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
				(theGame handsOff:)
				(if (or (Btst 167) (Btst 149))
					(localproc_00be)
					(if (Btst 149)
						(ego
							x: 310
							y: 167
							init:
							normalize:
							setLoop: -1
							setLoop: Grooper
							setCycle: Walk
							view: 5
							loop: 1
							setMotion: PolyPath 300 167 self
						)
					else
						(ego
							init:
							normalize:
							view: 536
							x: 310
							y: 167
							scaleSignal: 4
							setScaler: Scaler 100 60 125 65
							moveSpeed: 12
							cycleSpeed: 6
							changeGait: -1 0
							setCycle: StopWalk 538
							setMotion: PolyPath 300 167 self
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
					(curRoom addObstacle: (atp10 heading?))
					(ego
						x: 320
						y: (atp10 approachY?)
						init:
						setScaler: Scaler 100 60 125 65
						normalize: 1
						setMotion: PolyPath 305 (atp10 approachY?) self
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

(instance sFromNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (or (Btst 167) (Btst 149))
					(localproc_00be)
					(if (Btst 149)
						(ego
							x: 124
							y: 108
							init:
							normalize:
							setScaler: Scaler 100 60 125 65
							setLoop: -1
							setLoop: Grooper
							setCycle: Walk
							view: 5
							loop: 2
							setMotion: PolyPath 124 115 self
						)
					else
						(ego
							init:
							normalize:
							view: 536
							x: 124
							y: 108
							scaleSignal: 4
							setScaler: Scaler 100 60 125 65
							moveSpeed: 12
							cycleSpeed: 12
							changeGait: -1 0
							setCycle: StopWalk 538
							setMotion: PolyPath 124 115 self
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
						x: (atp2 approachX?)
						y: (atp2 approachY?)
						init:
						normalize: 2
						setScaler: Scaler 100 60 125 65
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

(instance sFromWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (or (Btst 167) (Btst 149))
					(localproc_00be)
					(if (Btst 149)
						(ego
							x: -10
							y: 149
							init:
							normalize:
							setLoop: -1
							setLoop: Grooper
							setCycle: Walk
							view: 5
							loop: 0
							setMotion: PolyPath 20 149 self
						)
					else
						(ego
							init:
							normalize:
							view: 536
							x: -10
							y: 149
							scaleSignal: 4
							setScaler: Scaler 100 60 125 65
							moveSpeed: 12
							cycleSpeed: 12
							changeGait: -1 0
							setCycle: StopWalk 538
							setMotion: PolyPath 20 149 self
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
					(curRoom addObstacle: (atp9 heading?))
					(ego
						x: -5
						y: (atp9 approachY?)
						init:
						setScaler: Scaler 100 60 125 65
						normalize: 0
						setMotion: PolyPath 13 (atp9 approachY?) self
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
				(if (or (Btst 167) (Btst 149))
					(localproc_00be)
					(if (Btst 149)
						(ego
							x: 151
							y: 195
							init:
							normalize:
							setLoop: -1
							setLoop: Grooper
							loop: 3
							setScaler: Scaler 100 60 125 65
							setCycle: Walk
							view: 5
							loop: 3
							setMotion: PolyPath 151 187 self
						)
					else
						(ego
							init:
							normalize:
							view: 536
							x: 151
							y: 195
							scaleSignal: 4
							setScaler: Scaler 100 60 125 65
							moveSpeed: 12
							cycleSpeed: 12
							changeGait: -1 0
							setCycle: StopWalk 538
							loop: 3
							setMotion: PolyPath 151 187 self
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
					(curRoom addObstacle: (atp5 heading?))
					(ego
						x: (atp5 approachX?)
						y: (atp5 approachY?)
						init:
						normalize: 3
						setScaler: Scaler 100 60 125 65
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

(instance atp1 of SwampView
	(properties
		noun 4
		approachX 138
		approachY 160
		x 138
		y 171
		view 550
		loop 3
		cel 3
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly1 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 10)
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
		(super init: 125 159 125 164 161 164 161 159)
		(return self)
	)
)

(instance atp2 of SwampView
	(properties
		noun 4
		approachX 208
		approachY 121
		x 165
		y 135
		view 550
		loop 2
		cel 1
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly2 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 17)
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
			init:
				203
				82
				203
				119
				187
				119
				187
				125
				224
				125
				224
				119
				214
				119
				214
				82
				319
				82
				319
				189
				0
				189
				0
				82
		)
		(return self)
	)
)

(instance atp3 of SwampView
	(properties
		noun 4
		approachX 78
		approachY 141
		x 76
		y 151
		view 550
		loop 3
		cel 6
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly3 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 11)
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
		(super
			init: 40 145 66 145 78 141 117 141 117 137 79 134 62 140 40 141
		)
		(return self)
	)
)

(instance atp4 of View
	(properties
		x 251
		y 142
		view 550
		loop 3
		cel 4
		signal $5000
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 4)
	)
	
	(method (doVerb)
		(curRoom doVerb: &rest)
	)
)

(instance atp4Refect of View
	(properties
		x 251
		y 142
		view 550
		loop 7
		cel 4
		signal $5000
	)
)

(instance atp5 of SwampView
	(properties
		noun 4
		approachX 213
		approachY 179
		x 216
		y 190
		view 550
		loop 2
		cel 2
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly5 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 15)
			priority: (atp5 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly5 of SwampPoly
	(properties
		type $0002
	)
	
	(method (init)
		(super
			init:
				198
				184
				208
				184
				208
				189
				0
				189
				0
				0
				319
				0
				319
				189
				216
				189
				216
				184
				224
				184
				224
				176
				198
				176
		)
		(return self)
	)
)

(instance atp6 of SwampView
	(properties
		noun 4
		approachX 64
		approachY 169
		x 48
		y 178
		view 550
		loop 3
		cel 1
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly6 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 17)
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
				167
				34
				167
				42
				172
				74
				172
				74
				167
				46
				167
				35
				163
				0
				162
		)
		(return self)
	)
)

(instance atp7 of View
	(properties
		noun 9
		x 305
		y 120
		view 550
		cel 4
		signal $5000
	)
)

(instance atp7Reflect of View
	(properties
		x 305
		y 137
		priority 120
		fixPriority 1
		view 550
		loop 4
		cel 4
		signal $5000
	)
)

(instance atp8 of View
	(properties
		noun 8
		x 319
		y 65
		view 550
		cel 1
		signal $5000
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 4)
	)
)

(instance atp8Reflect of View
	(properties
		x 319
		y 65
		view 550
		loop 4
		cel 1
		signal $5000
	)
	
	(method (doVerb)
		(curRoom doVerb: &rest)
	)
)

(instance atp9 of SwampView
	(properties
		noun 4
		approachX 20
		approachY 124
		y 132
		view 550
		loop 3
		cel 1
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly9 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 17)
			priority: (atp9 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly9 of SwampPoly
	(properties
		type $0002
	)
	
	(method (init)
		(super
			init: 0 0 319 0 319 189 0 189 0 130 26 128 26 124 0 124
		)
		(return self)
	)
)

(instance atp10 of SwampView
	(properties
		noun 4
		approachX 303
		approachY 137
		x 309
		y 141
		view 550
		loop 3
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
			posn: x (+ y 15)
			priority: (atp10 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly10 of SwampPoly
	(properties
		type $0002
	)
	
	(method (init)
		(super
			init: 319 132 293 132 293 137 319 137 319 189 0 189 0 0 319 0
		)
		(return self)
	)
)

(instance atp11 of SwampView
	(properties
		noun 4
		approachX 250
		approachY 153
		x 250
		y 165
		view 550
		loop 3
		cel 6
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly11 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 11)
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
		(super
			init: 239 161 246 157 291 156 291 151 246 149 212 155
		)
		(return self)
	)
)
