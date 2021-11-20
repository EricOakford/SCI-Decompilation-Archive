;;; Sierra Script 1.0 - (do not remove this comment)
(script# 545)
(include sci.sh)
(use Main)
(use GloryRm)
(use TargFeature)
(use SwampView)
(use EgoDead)
(use Scaler)
(use PolyPath)
(use Polygon)
(use StopWalk)
(use Timer)
(use Grooper)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm545 0
)

(local
	local0
	local1
	local2
	local3
	local4
)
(procedure (localproc_0248)
	(theGame handsOff:)
	(if (not (Btst 390))
		((Timer new:) setReal: curRoom 15)
		(= local3 1)
	)
	(if (not (& (lChernovy signal?) $0008))
		(lChernovy x: (- (ego x?) 25) y: (ego y?))
	)
	(if (not (& (rChernovy signal?) $0008))
		(rChernovy x: (+ (ego x?) 25) y: (ego y?))
	)
	(Bset 390)
	(theGame handsOn:)
)

(procedure (localproc_02e2)
	(curRoom
		topX: 162
		rightY: 125
		leftY: 131
		bottomX: 215
		addObstacle:
			((Polygon new:)
				type: 2
				init: 122 100 118 109 60 109 50 100 87 94
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 175 111 176 123 114 123 112 114 142 109
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 261 132 262 150 171 151 171 136 216 130
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 319 189 233 189 233 166 245 153 319 153
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 136 140 129 146 62 146 62 133 90 124
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 0 154 74 159 111 153 200 167 205 189 130 189 65 189 2 189
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 319 147 269 147 265 130 319 128
				yourself:
			)
	)
)

(instance rm545 of GloryRm
	(properties
		picture 550
		horizon 100
		north 3
		east 4
		south 2
		west 1
	)
	
	(method (init)
		(self setRegions: 10)
		(super init: &rest)
		(leftTree init:)
		(rightTree init:)
		(bush1 init:)
		(bush2 init:)
		(bush3 init:)
		(bush4 init:)
		(rock1 init:)
		(reflectRock1 init:)
		(bush5 init:)
		(bush6 init:)
		(bush7 init:)
		(bush8 init:)
		(skeleton init:)
		(reflectSkeleton init:)
		(tombStone init:)
		((ScriptID 10 2) init:)
		(if (!= prevRoomNum 810)
			(= global480 300)
			(= gGABad1Health 300)
			(Bclr 369)
			(Bclr 370)
		)
		(ego solvePuzzle: 436 6)
		(if (and (== prevRoomNum 810) (== battleResult 1))
			(EgoDead 3)
		else
			(if (not (Btst 361))
				(if
					(or
						(OneOf prevRoomNum 541 542 543)
						(and
							(== prevRoomNum 810)
							(not (Btst 369))
							(> global480 0)
						)
					)
					(++ local0)
					(lChernovy
						setStep: 3 2
						x: (if (== prevRoomNum 810)
							gRChernovyX
						else
							(lChernovy x?)
						)
						y: (if (== prevRoomNum 810)
							gRChernovyY
						else
							(lChernovy y?)
						)
						loop: (if (< (ego y?) (lChernovy y?)) 6 else 0)
						setCycle: Fwd
						cycleSpeed: 8
						setScaler: Scaler 100 60 125 65
						setScript: sTakeTurns
						init:
					)
					(if (== prevRoomNum 810) (rChernovy init: hide:))
				)
				(if
					(and
						(not (== heroType 2))
						(or
							(OneOf prevRoomNum 541 542 543)
							(and
								(== prevRoomNum 810)
								(> gGABad1Health 0)
								(not (Btst 370))
							)
						)
					)
					(++ local0)
					(rChernovy
						setStep: 3 2
						x: (if (== prevRoomNum 810)
							gRChernovyX
						else
							(rChernovy x?)
						)
						y: (if (== prevRoomNum 810)
							gRChernovyY
						else
							(rChernovy y?)
						)
						setScaler: Scaler 100 60 125 65
						setCycle: Fwd
						cycleSpeed: 8
						loop: (if (< (ego y?) (lChernovy y?)) 7 else 1)
						init:
					)
					(if (== prevRoomNum 810)
						(lChernovy init: hide: setScript: sTakeTurns)
					)
				else
					(rChernovy hide:)
				)
			)
			(walkHandler add: curRoom)
			(switch prevRoomNum
				(810
					(self setScript: sCombatEnter)
				)
				(543
					(cond 
						((and (< gGTheObj_2X 320) (< gGTheObj_2Y 200)) (self setScript: sFromWest))
						((< gGTheObj_2X 320) (self setScript: sFromNorth))
						(else (self setScript: sFromWest))
					)
				)
				(541
					(cond 
						((> gGTheObj_2Y 200) (self setScript: sFromNorth))
						((< gGTheObj_2X 0) (self setScript: sFromEast))
						(else (self setScript: sFromWest))
					)
				)
				(542
					(cond 
						((< gGTheObj_2X 0) (self setScript: sFromEast))
						((> gGTheObj_2Y 190) (self setScript: sFromNorth))
						((and (> gGTheObj_2X 320) (> gGTheObj_2Y 0)) (self setScript: sFromWest))
						(else (self setScript: sFromWest))
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
		)
		(if (Btst 149)
			(walkHandler
				addToFront: bush8 bush1 bush2 bush3 bush4 bush5 bush6 bush7
			)
		)
		(if (!= prevRoomNum 810) (theGame save: 1))
	)
	
	(method (dispose)
		(if (Btst 149)
			(walkHandler
				delete: bush8 bush1 bush2 bush3 bush4 bush5 bush6 bush7
			)
		)
		(= gGTheObj_2X (ego x?))
		(= gGTheObj_2Y (ego y?))
		(walkHandler delete: curRoom)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(3
					(if (>= (ego y?) 190) (ego setPri: -1))
				)
				(11
					(cond 
						((Btst 167) (messager say: 0 11 17))
						(local3 (messager say: 6 6 16))
						(else (self setScript: sGlideFromTuff))
					)
				)
				(91
					(if local3
						(Palette palSET_FLAG 0 255 100)
						(= local3 0)
						(if (curRoom timer?) (timer dispose:) (= timer 0))
					)
					(curRoom setScript: (ScriptID 62))
				)
				(83
					(if (and local3 (>= [egoStats 19] [spellCost 3]))
						(curRoom setScript: (ScriptID 12) 0 83)
						(Palette palSET_FLAG 0 255 100)
						(= local3 0)
						(if (curRoom timer?) (timer dispose:) (= timer 0))
					)
				)
				(86
					(curRoom setScript: (ScriptID 32 0) 0 theVerb)
				)
				(88
					(curRoom setScript: (ScriptID 32 0) 0 theVerb)
				)
				(93
					(curRoom setScript: (ScriptID 32 0) 0 theVerb)
				)
				(79
					(curRoom setScript: (ScriptID 32 0) 0 theVerb)
				)
				(95 (ego hide:))
				(21
					(cond 
						((Btst 149) (messager say: 0 11 18) (return 1))
						((Btst 167) (messager say: 6 6 18))
						(else (curRoom setScript: (ScriptID 32 0) 0 theVerb))
					)
				)
				(10
					(if local3
						(messager say: 6 6 16)
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
	
	(method (cue)
		(if local3 (= local3 0) (Palette palSET_FLAG 0 255 100))
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
					((== n 810) (Bset 378) 810)
					((== n 1) 542)
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
			(localproc_02e2)
		)
	)
	
	(method (addObstacle param1)
		(switch param1
			(poly6 (self rightY: 135))
			(poly7 (self topX: 100))
			(polyStone (self leftY: 168))
			(poly5 (self bottomX: 150))
		)
		(super addObstacle: param1 &rest)
	)
)

(instance sCombatEnter of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 3])
		(switch (= state newState)
			(0
				(if (or (Btst 167) (Btst 149))
					(localproc_02e2)
					(if (Btst 149)
						(ego
							init:
							setScaler: Scaler 100 60 125 65
							setLoop: -1
							setLoop: Grooper
							setCycle: Walk
							loop: 2
							view: 5
						)
					else
						(ego
							init:
							scaleSignal: 4
							setScaler: Scaler 100 60 125 65
							moveSpeed: 12
							cycleSpeed: 6
							changeGait: -1 0
							setCycle: StopWalk 538
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
					(curRoom addObstacle: (bush3 heading?))
					(ego
						x: (bush3 approachX?)
						y: (bush3 approachY?)
						init:
						setScaler: Scaler 100 60 125 65
						normalize: 2
					)
					(if (Btst 390) (localproc_0248))
				)
				(= cycles 3)
			)
			(1
				(theGame handsOn:)
				(if (Btst 149) (theIconBar disable: 5))
				(self dispose:)
			)
		)
	)
)

(instance sGlideFromTuff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= disabledActions (| disabledActions $0003))
				(if (and (< (ego x?) 80) (>= (ego y?) 170))
					(ego setMotion: PolyPath 80 170 self)
				else
					(= ticks 1)
				)
			)
			(1
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
				)
				(localproc_02e2)
				(Bset 149)
				(self setScript: (ScriptID 12) self)
			)
			(2
				(ego
					setLoop: -1
					setLoop: Grooper
					loop: 0
					setCycle: Walk
					view: 5
					setMotion: MoveTo (ego x?) (- (ego y?) 5) self
				)
			)
			(3
				(walkHandler
					addToFront: bush8 bush1 bush2 bush3 bush4 bush5 bush6 bush7
				)
				(theGame handsOn:)
				(theIconBar disable: 5)
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
				(theGame handsOff:)
				(if (or (Btst 167) (Btst 149))
					(localproc_02e2)
					(if (Btst 149)
						(ego
							x: 236
							y: 102
							init:
							normalize:
							setScaler: Scaler 100 60 125 65
							setLoop: -1
							setLoop: Grooper
							setCycle: Walk
							view: 5
							loop: 2
							setMotion: PolyPath 236 106 self
						)
					else
						(ego
							init:
							normalize:
							view: 536
							x: 236
							y: 102
							scaleSignal: 4
							setScaler: Scaler 100 60 125 65
							moveSpeed: 12
							cycleSpeed: 6
							changeGait: -1 0
							setCycle: StopWalk 538
							setMotion: PolyPath 236 106 self
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
					(curRoom addObstacle: (bush7 heading?))
					(ego
						x: (bush7 approachX?)
						y: (bush7 approachY?)
						init:
						setScaler: Scaler 100 60 125 65
						normalize: 2
					)
					(= cycles 2)
				)
			)
			(1
				(if (and (not (Btst 361)) (== heroType 3))
					(messager say: 6 6 7)
				)
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
				(theGame handsOff:)
				(if (or (Btst 167) (Btst 149))
					(localproc_02e2)
					(if (Btst 149)
						(ego
							x: 310
							y: 135
							init:
							normalize:
							setLoop: -1
							setLoop: Grooper
							setCycle: Walk
							view: 5
							loop: 1
							setMotion: PolyPath 300 135 self
						)
					else
						(ego
							init:
							normalize:
							view: 536
							x: 310
							y: 135
							scaleSignal: 4
							setScaler: Scaler 100 60 125 65
							moveSpeed: 12
							cycleSpeed: 6
							changeGait: -1 0
							setCycle: StopWalk 538
							setMotion: PolyPath 300 135 self
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
					(curRoom addObstacle: (bush6 heading?))
					(ego
						x: 310
						y: (bush6 approachY?)
						init:
						setScaler: Scaler 100 60 125 65
						normalize: 1
						setMotion: PolyPath 300 (bush6 approachY?) self
					)
				)
			)
			(1
				(if (and (not (Btst 361)) (== heroType 3))
					(messager say: 6 6 7)
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
				(if (or (Btst 167) (Btst 149))
					(localproc_02e2)
					(if (Btst 149)
						(ego
							x: -10
							y: 135
							init:
							normalize:
							setScaler: Scaler 100 60 125 65
							setLoop: -1
							setLoop: Grooper
							setCycle: Walk
							view: 5
							loop: 0
							setMotion: PolyPath 20 135 self
						)
					else
						(ego
							init:
							normalize:
							view: 536
							x: -10
							y: 135
							scaleSignal: 4
							setScaler: Scaler 100 60 125 65
							moveSpeed: 12
							cycleSpeed: 6
							changeGait: -1 0
							setCycle: StopWalk 538
							setMotion: PolyPath 20 135 self
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
					(curRoom addObstacle: (bush8 heading?))
					(ego
						x: 10
						y: (bush8 approachY?)
						init:
						normalize: 0
						setScaler: Scaler 100 60 125 65
						setMotion: PolyPath 20 (bush8 approachY?) self
					)
				)
			)
			(1
				(if (and (not (Btst 361)) (== heroType 3))
					(messager say: 6 6 7)
				)
				(theGame handsOn:)
				(if (Btst 149) (theIconBar disable: 5))
				(self dispose:)
			)
		)
	)
)

(instance sShowPuzzle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 4 43 5 0 self)
			)
			(1
				((ScriptID 85 0) init: show: dispose:)
				(DisposeScript 85)
				(= cycles 1)
			)
			(2
				(if (Btst 361)
					(messager say: 6 6 9 0 self)
					(ego solvePuzzle: 437 2 get: 52)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(3 (messager say: 6 6 8 0 self))
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTakeTurns of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 60))
			(1
				(if
					(and
						(not (== (lChernovy view?) 842))
						(not (& (lChernovy signal?) $0008))
					)
					(if (< (ego y?) (lChernovy y?))
						(lChernovy setCel: 0 setCycle: 0 setLoop: 12 1)
					else
						(lChernovy setCel: 0 setCycle: 0 setLoop: 8 1)
					)
					(= cycles 6)
				else
					(= cycles 1)
				)
			)
			(2
				(if
					(and
						(not (== (lChernovy view?) 842))
						(not (& (lChernovy signal?) $0008))
					)
					(ChernovySpell init: lChernovy)
					(FrameOut)
				else
					(= ticks 1)
				)
			)
			(3
				(if
					(and
						(not (== (lChernovy view?) 842))
						(not (& (lChernovy signal?) $0008))
					)
					(if (< (ego y?) (lChernovy y?))
						(lChernovy setLoop: 6 setCycle: Fwd)
					else
						(lChernovy setLoop: 0 setCycle: Fwd)
					)
				)
				(= cycles 60)
			)
			(4
				(if
					(and
						(not (== (rChernovy view?) 842))
						(not (& (rChernovy signal?) $0008))
					)
					(if (< (ego y?) (lChernovy y?))
						(rChernovy setCel: 0 setLoop: 13 1 setCycle: 0)
					else
						(rChernovy setCel: 0 setLoop: 9 1 setCycle: 0)
					)
					(= cycles 6)
				else
					(= cycles 1)
				)
			)
			(5
				(if
					(and
						(not (== (rChernovy view?) 842))
						(not (& (rChernovy signal?) $0008))
					)
					(ChernovySpell init: rChernovy)
					(FrameOut)
				else
					(= ticks 1)
				)
			)
			(6
				(if
					(and
						(not (== (rChernovy view?) 842))
						(not (& (rChernovy signal?) $0008))
					)
					(if (< (ego y?) (lChernovy y?))
						(rChernovy setLoop: 7 setCycle: Fwd)
					else
						(rChernovy setLoop: 1 setCycle: Fwd)
					)
				)
				(self changeState: 0)
			)
		)
	)
)

(instance sCheckLoop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setLoop: 14 setMotion: 0 setCycle: End self)
			)
			(1 (self dispose:))
		)
	)
)

(instance tombStone of View
	(properties
		noun 4
		x 6
		y 90
		priority 165
		fixPriority 1
		view 550
		cel 6
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(43
				(cond 
					((not (bush8 onMe: (ego x?) (ego y?))) (messager say: 4 4 2))
					(local0 (messager say: 4 4 3))
					((Btst 437) (messager say: 4 43 4))
					(else (curRoom setScript: sShowPuzzle))
				)
			)
			(1 (super doVerb: theVerb))
			(2 (super doVerb: theVerb))
			(3 (super doVerb: theVerb))
			(else 
				(cond 
					((not (bush8 onMe: (ego x?) (ego y?))) (messager say: 4 4 2))
					(local0 (messager say: 4 4 3))
					((Btst 361) (messager say: 4 43 4))
					(else (super doVerb: theVerb))
				)
			)
		)
	)
)

(instance bush8 of SwampView
	(properties
		approachX 80
		approachY 170
		x 38
		y 187
		view 550
		cel 5
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(= heading (polyStone init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x y
			priority: (bush8 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance polyStone of SwampPoly
	(properties
		type $0002
	)
	
	(method (init)
		(super
			init: 0 0 319 0 319 189 0 189 0 173 51 180 92 171 90 166 48 172 0 165
		)
		(return self)
	)
)

(instance bush1 of SwampView
	(properties
		approachX 283
		approachY 178
		x 279
		y 188
		view 550
		loop 2
		cel 3
		signal $4000
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
			priority: (bush1 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly1 of SwampPoly
	(properties)
	
	(method (init)
		(super init: 283 182 297 178 285 173 267 176)
		(return self)
	)
)

(instance bush2 of SwampView
	(properties
		approachX 230
		approachY 131
		x 215
		y 147
		view 550
		loop 3
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
			posn: x (+ y 11)
			priority: (bush2 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly2 of SwampPoly
	(properties)
	
	(method (init)
		(super init: 249 132 229 127 206 134 228 137)
		(return self)
	)
)

(instance bush3 of SwampView
	(properties
		approachX 147
		approachY 113
		x 146
		y 122
		view 550
		loop 3
		cel 5
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly3 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 8)
			priority: (bush3 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly3 of SwampPoly
	(properties)
	
	(method (init)
		(super init: 162 114 145 109 126 112 143 117)
		(return self)
	)
)

(instance bush4 of SwampView
	(properties
		approachX 92
		approachY 130
		x 87
		y 142
		view 550
		loop 3
		cel 2
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly4 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 16)
			priority: (bush4 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly4 of SwampPoly
	(properties)
	
	(method (init)
		(super init: 108 132 92 127 71 132 89 136)
		(return self)
	)
)

(instance bush5 of SwampView
	(properties
		approachX 168
		approachY 181
		x 153
		y 190
		view 550
		loop 3
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
			posn: x (+ y 11)
			priority: (bush5 priority?)
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
				138
				178
				138
				184
				144
				184
				144
				189
				0
				189
				0
				0
				319
				0
				319
				189
				154
				189
				154
				184
				186
				184
				186
				178
		)
		(return self)
	)
)

(instance bush6 of SwampView
	(properties
		approachX 300
		approachY 135
		x 319
		y 145
		view 550
		loop 3
		cel 6
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly6 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 11)
			priority: (bush6 priority?)
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
			init: 319 130 290 130 290 138 319 138 319 189 0 189 0 0 319 0
		)
		(return self)
	)
)

(instance bush7 of SwampView
	(properties
		approachX 93
		approachY 98
		x 90
		y 107
		view 550
		loop 3
		cel 5
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(= heading (poly7 init:))
		(= extra (View new:))
		(extra
			view: view
			loop: (+ loop 4)
			cel: cel
			posn: x (+ y 8)
			priority: (bush7 priority?)
			fixPriority: 1
			signal: 20480
			init:
		)
	)
)

(instance poly7 of SwampPoly
	(properties
		type $0002
	)
	
	(method (init)
		(super
			init:
				86
				95
				70
				95
				70
				100
				106
				100
				106
				95
				93
				95
				93
				85
				319
				85
				319
				189
				0
				189
				0
				85
				86
				85
		)
		(return self)
	)
)

(instance skeleton of View
	(properties
		x 308
		y 162
		view 550
		loop 1
		cel 3
		signal $5000
	)
)

(instance reflectSkeleton of View
	(properties
		x 308
		y 175
		view 550
		loop 5
		cel 3
		signal $5000
	)
)

(instance rock1 of View
	(properties
		x 118
		y 146
		view 550
		loop 2
		cel 4
		signal $5000
	)
	
	(method (doVerb theVerb)
		(bush4 doVerb: theVerb)
	)
)

(instance reflectRock1 of View
	(properties
		x 118
		y 146
		view 550
		loop 6
		cel 4
		signal $5000
	)
)

(instance leftTree of View
	(properties
		y 81
		view 550
		signal $5000
	)
)

(instance rightTree of View
	(properties
		x 319
		y 65
		view 550
		cel 1
		signal $5000
	)
)

(instance lChernovy of TargActor
	(properties
		noun 7
		x 60
		y 175
		z 10
		view 840
		signal $0001
	)
	
	(method (doit &tmp temp0)
		(if
			(and
				(not (& signal $0800))
				(not (== loop 6))
				(not (== loop 12))
				(< (ego y?) (self y?))
			)
			(self setLoop: 6)
		)
		(if
			(and
				(not (& signal $0800))
				(not (== loop 0))
				(not (== loop 8))
				(>= (ego y?) (self y?))
			)
			(self setLoop: 0)
		)
		(cond 
			((== (curRoom script?) (ScriptID 62)) 0)
			((== (curRoom script?) (ScriptID 12)) 0)
			(
				(and
					(not local4)
					(not local3)
					(> gGABad1Health 0)
					(not (Btst 369))
					(not (& signal $0008))
					(<= (ego distanceTo: self) 30)
				)
				(= local4 1)
				(= global365 840)
				(= monsterHealth gGABad1Health)
				(Bset 369)
				(= gRChernovyX (rChernovy x?))
				(= gRChernovyY (rChernovy y?))
				(curRoom newRoom: 810)
			)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 79)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (cue)
		(-- local0)
		(self hide:)
	)
	
	(method (getHurt param1 param2)
		(if (and (== param1 90) (> (++ local2) 1))
			(Palette palSET_FLAG 0 85 0)
			(Palette palSET_FLAG 112 255 0)
			(lChernovy setScript: 0)
			(localproc_0248)
		)
		(if (== param1 21)
			(= gGABad1Health (- gGABad1Health param2))
		else
			(= global480 (- global480 (/ (* param2 70) 100)))
		)
		(if
			(and
				(not (== view 842))
				(not (& signal $0008))
				(<= global480 0)
			)
			(self
				view: 842
				setLoop: 0 1
				setCel: 0
				z: -18
				setCycle: CT 6 1 self
			)
		)
	)
)

(instance rChernovy of TargActor
	(properties
		noun 7
		x 290
		y 175
		z 10
		view 840
		loop 1
		signal $0001
	)
	
	(method (doit)
		(if
			(and
				(not (& signal $0800))
				(not (== loop 7))
				(not (== loop 13))
				(< (ego y?) (self y?))
			)
			(self setLoop: 7)
		)
		(if
			(and
				(not (& signal $0800))
				(not (== loop 1))
				(not (== loop 9))
				(>= (ego y?) (self y?))
			)
			(self setLoop: 1)
		)
		(cond 
			((== (curRoom script?) (ScriptID 62)) 0)
			((== (curRoom script?) (ScriptID 12)) 0)
			(
				(and
					(not local4)
					(not local3)
					(> gGABad1Health 0)
					(not (Btst 370))
					(not (& signal $0008))
					(<= (ego distanceTo: self) 30)
				)
				(= local4 1)
				(= global365 840)
				(= monsterHealth gGABad1Health)
				(Bset 370)
				(= gRChernovyX (lChernovy x?))
				(= gRChernovyY (lChernovy y?))
				(curRoom newRoom: 810)
			)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 79)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (cue)
		(-- local0)
		(self hide:)
	)
	
	(method (getHurt param1 param2)
		(if (and (== param1 90) (> (++ local2) 1))
			(Palette palSET_FLAG 0 85 0)
			(Palette palSET_FLAG 112 255 0)
			(lChernovy setScript: 0)
			(localproc_0248)
		)
		(if (== param1 21)
			(= gGABad1Health (- gGABad1Health param2))
		else
			(= gGABad1Health
				(- gGABad1Health (/ (* param2 70) 100))
			)
		)
		(if
			(and
				(not (== view 842))
				(not (& signal $0008))
				(<= gGABad1Health 0)
			)
			(self
				view: 842
				setLoop: 1 1
				z: -18
				setCel: 0
				setCycle: CT 6 1 self
			)
		)
	)
)

(class ChernovySpell of Actor
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 190
		fixPriority 1
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view 21
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $6001
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		robot 0
		cuedOnce 0
		curDamage 23
	)
	
	(method (init param1 &tmp temp0)
		(if (< (Random 0 100) 26)
			(= temp0 (- 100 (Random 20 50)))
		else
			(= temp0 0)
		)
		(self
			cuedOnce: 0
			x:
				(+
					(param1 x?)
					(cond 
						((== param1 lChernovy) (if (== (param1 loop?) 12) 15 else 40))
						((== (param1 loop?) 13) -15)
						(else -40)
					)
				)
			y:
				(-
					(param1 y?)
					(if
					(or (== (param1 loop?) 12) (== (param1 loop?) 13))
						57
					else
						37
					)
				)
			setStep: 8 5
			setLoop: 4 1
			moveSpeed: 0
			setScaler: Scaler 100 60 125 65
			setCycle: Fwd
			setMotion: MoveTo (+ (ego x?) temp0) (- (ego y?) 25) self
		)
		(super init:)
		(= local1 param1)
	)
	
	(method (cue &tmp temp0 temp1 temp2)
		(= temp2
			(/
				(*
					(= temp0 (/ (* 100 (ego scaleY?)) (ego maxScale?)))
					(CelHigh (ego view?) (ego loop?) (ego cel?))
				)
				100
			)
		)
		(= temp1
			(/
				(* temp0 (CelWide (ego view?) (ego loop?) (ego cel?)))
				200
			)
		)
		(if (not cuedOnce)
			(sTakeTurns cue:)
			(if
				(and
					(<= (- (ego y?) temp2) (self y?))
					(<= (self y?) (ego y?))
					(<= (- (ego x?) temp1) (self x?))
					(<= (self x?) (+ (ego x?) temp1))
					(Btst 8)
				)
				(if (& (ego signal?) $0008)
					(ego show:)
					(messager say: 6 6 11)
				)
				(revSpell init: local1 self)
				(= cuedOnce 1)
				(self cue:)
			else
				(self setScript: (sCheckLoop new:) self)
				(= cuedOnce 1)
			)
		else
			(if
				(and
					(<= (- (ego y?) temp2) (self y?))
					(<= (self y?) (ego y?))
					(<= (- (ego x?) temp1) (self x?))
					(<= (self x?) (+ (ego x?) temp1))
				)
				(if (not (Btst 8)) (ego takeDamage: curDamage))
				(if (& (ego signal?) $0008)
					(ego show:)
					(messager say: 6 6 11)
				)
				(if (<= [egoStats 17] 0) (EgoDead 3))
			)
			(self dispose:)
		)
	)
)

(class revSpell of Actor
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 190
		fixPriority 1
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view 21
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $6001
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		robot 0
		cuedOnce 0
		curDamage 23
	)
	
	(method (init param1 param2)
		(self
			cuedOnce: 0
			x: (param2 x?)
			y: (param2 y?)
			setStep: 8 5
			setLoop: 4 1
			moveSpeed: 0
			setScaler: Scaler 100 60 125 65
			setCycle: Fwd
			setMotion: MoveTo (param1 x?) (- (param1 y?) 25) self
		)
		(super init:)
	)
	
	(method (cue)
		(if (not cuedOnce)
			(= cuedOnce 1)
			(local1 getHurt: 90 curDamage)
			(self setScript: (sCheckLoop new:) self)
		else
			(self dispose:)
		)
	)
)

(instance qReverseFX of Sound
	(properties)
)
