;;; Sierra Script 1.0 - (do not remove this comment)
(script# 800)
(include sci.sh)
(use Main)
(use GloryRm)
(use TargFeature)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Jump)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm800 0
	pSquid 1
	aBush 2
	proc800_3 3
	proc800_4 4
	pSquidStone 5
)

(local
	[local0 3]
	local3
	gTheObj_2CycleSpeed
	gTheObj_2MoveSpeed
	local6
	local7
	local8
	local9
	local10
	local11
	local12
	local13
	local14
	local15
	local16
	gTheObj_2Z
	newProp
	local19
	local20
	local21
	newProp_2
)
(procedure (proc800_3)
	(theMusic number: 550 setLoop: -1 play:)
	(switch prevRoomNum
		(790 (ego posn: 319 59))
		(else  (ego posn: 0 172))
	)
	(ego init: normalize: setScaler: Scaler 98 68 189 63)
	(curRoom
		addObstacle:
			((Polygon new:)
				type: 2
				init:
					-300
					0
					619
					0
					619
					53
					247
					57
					232
					53
					125
					53
					74
					63
					56
					75
					84
					84
					104
					95
					54
					114
					85
					158
					44
					165
					-300
					140
				yourself:
			)
			((Polygon new:)
				type: 2
				init:
					-300
					189
					26
					175
					88
					166
					94
					181
					143
					173
					224
					157
					228
					145
					221
					137
					202
					139
					212
					152
					193
					159
					158
					165
					139
					157
					126
					155
					66
					117
					116
					97
					116
					88
					70
					74
					80
					66
					119
					59
					169
					57
					210
					57
					253
					64
					619
					59
					619
					189
				yourself:
			)
	)
	(if (not (Btst 335))
		(cond 
			((Btst 347)
				(aBush
					view: 804
					setLoop: 9 1
					setCel: 0
					ignoreActors: 1
					posn: 249 149
					approachX: 148
					approachY: 165
					init:
				)
			)
			((Btst 353)
				(aBush
					view: 804
					setLoop: 9 1
					setCel: 0
					posn: 365 218 100
					approachX: 148
					approachY: 165
					init:
				)
				(vGoo setPri: 119 init:)
			)
			(else (aBush approachVerbs: 4 init:))
		)
	else
		(vGoo setPri: 119 init:)
	)
	(pSquidStone init:)
	(((ScriptID 809 1) new:)
		setLoop: 0 1
		setCel: 0
		posn: 276 152
		cycleSpeed: 30
		init:
		ignoreActors: 1
		setCycle: Fwd
	)
	(((ScriptID 809 1) new:)
		setLoop: 1 1
		setCel: 0
		posn: 214 167
		cycleSpeed: 36
		init:
		ignoreActors: 1
		setCycle: Fwd
	)
	(((ScriptID 809 1) new:)
		setLoop: 2 1
		setCel: 0
		posn: 279 92
		cycleSpeed: 30
		setPri: 79
		init:
		ignoreActors: 1
		setCycle: Fwd
	)
	(((ScriptID 809 1) new:)
		setLoop: 3 1
		setCel: 0
		posn: 91 117
		cycleSpeed: 24
		init:
		ignoreActors: 1
		setCycle: Fwd
	)
	(((ScriptID 809 1) new:)
		setLoop: 4 1
		setCel: 0
		posn: 43 162
		cycleSpeed: 26
		init:
		ignoreActors: 1
		setCycle: Fwd
	)
	(((ScriptID 809 1) new:)
		setLoop: 5 1
		setCel: 0
		posn: 178 178
		cycleSpeed: 30
		init:
		ignoreActors: 1
		setCycle: Fwd
	)
	(fPath init:)
	(fLedge init:)
	(fAboveLedge init:)
	(fSideLedge init:)
	(fIsland init: approachVerbs: 4)
	(fIslandTree init: approachVerbs: 4)
	(fTreeBranch init: approachVerbs: 4)
	(fFrontTree init:)
	(fStream init: approachVerbs: 4)
	(if (and (not (Btst 353)) (not (Btst 335)))
		(tBush init:)
		(fRocks init: approachVerbs: 4)
	)
	(super init: &rest)
	(curRoom setScript: (ScriptID 804 0))
)

(procedure (proc800_4)
	(theMusic number: 550 setLoop: -1 play:)
	(switch prevRoomNum
		(790 (ego posn: 319 59))
		(else  (ego posn: 0 172))
	)
	(ego init: normalize: setScaler: Scaler 98 68 189 63)
	(curRoom
		addObstacle:
			((Polygon new:)
				type: 2
				init:
					-300
					0
					619
					0
					619
					53
					247
					57
					232
					53
					125
					53
					74
					63
					56
					75
					84
					84
					104
					95
					54
					114
					85
					158
					44
					165
					-300
					140
				yourself:
			)
			((Polygon new:)
				type: 2
				init:
					-300
					189
					26
					175
					88
					166
					94
					181
					143
					173
					224
					157
					228
					145
					221
					137
					202
					139
					212
					152
					193
					159
					158
					165
					139
					157
					126
					155
					66
					117
					116
					97
					116
					88
					70
					74
					80
					66
					119
					59
					169
					57
					210
					57
					253
					64
					619
					59
					619
					189
				yourself:
			)
	)
	(if (not (Btst 335))
		(cond 
			((Btst 347)
				(aBush
					view: 804
					setLoop: 9 1
					setCel: 0
					ignoreActors: 1
					posn: 249 149
					approachX: 148
					approachY: 165
					init:
				)
			)
			((Btst 353)
				(aBush
					view: 804
					setLoop: 9 1
					setCel: 0
					posn: 365 218 100
					approachX: 148
					approachY: 165
					init:
				)
				(vGoo setPri: 119 init:)
			)
			(else (aBush approachVerbs: 4 init:))
		)
	else
		(vGoo setPri: 119 init:)
	)
	(pSquidStone init:)
	(((ScriptID 809 1) new:)
		setLoop: 0 1
		setCel: 0
		posn: 276 152
		cycleSpeed: 30
		init:
		ignoreActors: 1
		setCycle: Fwd
	)
	(((ScriptID 809 1) new:)
		setLoop: 1 1
		setCel: 0
		posn: 214 167
		cycleSpeed: 36
		init:
		ignoreActors: 1
		setCycle: Fwd
	)
	(((ScriptID 809 1) new:)
		setLoop: 2 1
		setCel: 0
		posn: 279 92
		cycleSpeed: 30
		setPri: 79
		init:
		ignoreActors: 1
		setCycle: Fwd
	)
	(((ScriptID 809 1) new:)
		setLoop: 3 1
		setCel: 0
		posn: 91 117
		cycleSpeed: 24
		init:
		ignoreActors: 1
		setCycle: Fwd
	)
	(((ScriptID 809 1) new:)
		setLoop: 4 1
		setCel: 0
		posn: 43 162
		cycleSpeed: 26
		init:
		ignoreActors: 1
		setCycle: Fwd
	)
	(((ScriptID 809 1) new:)
		setLoop: 5 1
		setCel: 0
		posn: 178 178
		cycleSpeed: 30
		init:
		ignoreActors: 1
		setCycle: Fwd
	)
	(fPath init:)
	(fLedge init:)
	(fAboveLedge init:)
	(fSideLedge init:)
	(fIsland init: approachVerbs: 4)
	(fIslandTree init: approachVerbs: 4)
	(fTreeBranch init: approachVerbs: 4)
	(fFrontTree init:)
	(fStream init: approachVerbs: 4)
	(if (and (not (Btst 353)) (not (Btst 335)))
		(tBush init:)
		(fRocks init: approachVerbs: 4)
	)
	(super init: &rest)
	(curRoom setScript: (ScriptID 804 0))
)

(instance rm800 of GloryRm
	(properties
		noun 1
		picture 800
		east 790
		west 535
		leftY 172
		rightY 60
	)
	
	(method (init)
		(theMusic number: 550 setLoop: -1 play:)
		(switch prevRoomNum
			(790 (ego posn: 319 59))
			(else  (ego posn: 0 172))
		)
		(ego init: normalize: setScaler: Scaler 98 68 189 63)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						-300
						0
						619
						0
						619
						53
						247
						57
						232
						53
						125
						53
						74
						63
						56
						75
						84
						84
						104
						95
						54
						114
						85
						158
						44
						165
						-300
						140
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						-300
						189
						26
						175
						88
						166
						94
						181
						143
						173
						224
						157
						228
						145
						221
						137
						202
						139
						212
						152
						193
						159
						158
						165
						139
						157
						126
						155
						66
						117
						116
						97
						116
						88
						70
						74
						80
						66
						119
						59
						169
						57
						210
						57
						253
						64
						619
						59
						619
						189
					yourself:
				)
		)
		(if (not (Btst 335))
			(cond 
				((Btst 347)
					(aBush
						view: 804
						setLoop: 9 1
						setCel: 0
						ignoreActors: 1
						posn: 249 149
						approachX: 148
						approachY: 165
						init:
					)
				)
				((Btst 353)
					(aBush
						view: 804
						setLoop: 9 1
						setCel: 0
						posn: 365 218 100
						approachX: 148
						approachY: 165
						init:
					)
					(vGoo setPri: 119 init:)
				)
				(else (aBush approachVerbs: 4 init:))
			)
		else
			(vGoo setPri: 119 init:)
		)
		(pSquidStone init:)
		(((ScriptID 809 1) new:)
			setLoop: 0 1
			setCel: 0
			posn: 276 152
			cycleSpeed: 30
			init:
			ignoreActors: 1
			setCycle: Fwd
		)
		(((ScriptID 809 1) new:)
			setLoop: 1 1
			setCel: 0
			posn: 214 167
			cycleSpeed: 36
			init:
			ignoreActors: 1
			setCycle: Fwd
		)
		(((ScriptID 809 1) new:)
			setLoop: 2 1
			setCel: 0
			posn: 279 92
			cycleSpeed: 30
			setPri: 79
			init:
			ignoreActors: 1
			setCycle: Fwd
		)
		(((ScriptID 809 1) new:)
			setLoop: 3 1
			setCel: 0
			posn: 91 117
			cycleSpeed: 24
			init:
			ignoreActors: 1
			setCycle: Fwd
		)
		(((ScriptID 809 1) new:)
			setLoop: 4 1
			setCel: 0
			posn: 43 162
			cycleSpeed: 26
			init:
			ignoreActors: 1
			setCycle: Fwd
		)
		(((ScriptID 809 1) new:)
			setLoop: 5 1
			setCel: 0
			posn: 178 178
			cycleSpeed: 30
			init:
			ignoreActors: 1
			setCycle: Fwd
		)
		(fPath init:)
		(fLedge init:)
		(fAboveLedge init:)
		(fSideLedge init:)
		(fIsland init: approachVerbs: 4)
		(fIslandTree init: approachVerbs: 4)
		(fTreeBranch init: approachVerbs: 4)
		(fFrontTree init:)
		(fStream init: approachVerbs: 4)
		(if (and (not (Btst 353)) (not (Btst 335)))
			(tBush init:)
			(fRocks init: approachVerbs: 4)
		)
		(super init: &rest)
		(curRoom setScript: (ScriptID 804 0))
	)
	
	(method (doit)
		(if
			(and
				(== (curRoom script?) (ScriptID 31 1))
				(>= (ego z?) 90)
			)
			(= gTheObj_2Z (ego z?))
			(curRoom setScript: sLevitating)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(Bclr 9)
		(if (> (pSquidStone cel?) 0)
			(walkHandler delete: pSquidStone)
		)
		(if script (script dispose:))
		(DisposeScript 802)
		(DisposeScript 803)
		(DisposeScript 804)
		(DisposeScript 805)
		(DisposeScript 806)
		(DisposeScript 807)
		(DisposeScript 809)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(= projX ((User curEvent?) x?))
		(= projY ((User curEvent?) y?))
		(return
			(switch theVerb
				(82
					(curRoom setScript: (ScriptID 11))
					(return 1)
				)
				(4
					(if local21
						(messager say: 15 6 44)
						(ego get: 5 local21)
						(= local21 0)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(-82
					(theGame handsOn:)
					(curRoom setScript: 0)
					(messager say: 15 6 34)
				)
				(91
					(curRoom setScript: (ScriptID 807 0))
				)
				(81
					(if (== (pSquidStone cel?) 0)
						(curRoom setScript: sDoSparkle)
					else
						(super doVerb: theVerb)
					)
				)
				(87
					(if (and (> (ego y?) 100) (not local15))
						(self setScript: (ScriptID 37) 0 aBush)
					else
						(messager say: 16 88 21)
					)
				)
				(-87
					(if (Btst 353)
						(self setScript: (ScriptID 804 4))
					else
						(messager say: 15 6 35 0 self)
					)
				)
				(10
					(if local15
						(messager say: 1 10 26)
					else
						(messager say: 1 10 0 0)
					)
					(return 1)
				)
				(89
					(if (> (ego y?) 100)
						((ScriptID 31 0) init: (ego x?) (ego y?) 100 0 2)
					else
						(messager say: 15 6 36)
					)
				)
				(86
					(= local20 1)
					(self setScript: (ScriptID 32) 0 86)
				)
				(93
					(= local20 1)
					(self setScript: (ScriptID 32) 0 93)
				)
				(79
					(= local20 1)
					(self setScript: (ScriptID 32) 0 79)
				)
				(else 
					(Bclr 9)
					(cond 
						(
							(and
								(not (Btst 353))
								(< 236 projX)
								(< projX 319)
								(< 113 projY)
								(< projY 180)
							)
							(fRocks doVerb: theVerb)
						)
						((== theVerb 21)
							(if (Btst 347)
								(messager say: 0 33 0)
								(return 1)
							else
								(curRoom setScript: (ScriptID 32) 0 21)
							)
						)
						((== theVerb 37)
							(if (== (ego has: 5) 1)
								(messager say: 15 6 42)
							else
								(++ local21)
								(curRoom setScript: (ScriptID 32) 0 37)
							)
						)
						(else (super doVerb: theVerb))
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(if (== n 790) (theMusic fade: 0))
		(super newRoom: n &rest)
	)
)

(instance sDoSparkle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (Btst 335)
					(messager say: 1 81 9 0 self)
				else
					(messager say: 1 81 8 0 self)
				)
			)
			(1
				(sparkle1 setCel: 0 setCycle: End self init:)
				(if (not (Btst 335))
					(sparkle2 setCel: 0 setCycle: End init:)
				)
			)
			(2
				(sparkle1 dispose:)
				(if (not (Btst 335)) (sparkle2 dispose:))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDoForce of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (> (ego x?) 176)
					(ego setMotion: PolyPath 176 (ego y?) self)
				else
					(= ticks 1)
				)
			)
			(1
				(theGame handsOn:)
				(Bset 9)
				(pSquidStone setPri: 0)
				(curRoom setScript: (ScriptID 32) 0 88)
			)
		)
	)
)

(instance sThrowDagger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (> (ego x?) 176)
					(ego setMotion: PolyPath 176 (ego y?) self)
				else
					(= ticks 1)
				)
			)
			(1
				(= local11 1)
				(= local7 0)
				(Bset 9)
				(cond 
					((== local10 3) (curRoom setScript: (ScriptID 32) 0 37 fRocks))
					((== (ego trySkill: 0 275) 1)
						(= local12 0)
						(curRoom setScript: (ScriptID 32) 0 37 fRocks)
					)
					(else
						(= local12 1)
						(= projX (Random 260 270))
						(= projY (Random 103 170))
						(curRoom setScript: (ScriptID 32) 0 37 fRocks)
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance sThrowRock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (> (ego x?) 176)
					(ego setMotion: PolyPath 176 (ego y?) self)
				else
					(= ticks 1)
				)
			)
			(1
				(= local11 0)
				(Bset 9)
				(if (== local10 3)
					(curRoom setScript: (ScriptID 32) 0 21 fRocks)
				else
					(= local7 0)
					(if (== (ego trySkill: 0 275) 1)
						(= local12 0)
						(curRoom setScript: (ScriptID 32) 0 21 fRocks)
					else
						(= local12 1)
						(= projX (Random 260 270))
						(= projY (Random 103 170))
						(curRoom setScript: (ScriptID 32) 0 21 fRocks)
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance sLevitating of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(if (== state 2)
			(if (== (ego z?) 0)
				(self cue:)
			else
				(ego z: (- (ego z?) 1))
				(newProp z: (ego z?))
				(if newProp_2
					(newProp_2
						scaleX: (- 128 (ego z?))
						scaleY: (- 128 (ego z?))
					)
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: (ego x?) (ego y?) gTheObj_2Z)
				(= newProp (Prop new:))
				(newProp
					view: 17
					loop: 4
					x: (ego x?)
					y: (+ (ego y?) 1)
					z: gTheObj_2Z
					setCycle: Fwd
					setScaler:
					scaleX: (ego scaleX?)
					scaleY: (ego scaleY?)
					priority: (ego priority?)
					signal: (| (newProp signal?) $4000)
					init:
				)
				((= newProp_2 (Prop new:))
					signal: 24577
					view: 17
					loop:
						(if
							(and
								(> (ego view?) 17)
								(< (ego view?) 21)
								(< (ego view?) 21)
							)
							(- (ego loop?) 8)
						else
							(+ (ego loop?) 6)
						)
					x: (ego x?)
					y: (+ (ego y?) 1)
					setCycle: Fwd
					setScale: ego
					priority: (ego priority?)
					fixPriority: 1
					init:
				)
				(= ticks 6)
			)
			(1
				(messager say: 15 6 28 0 self)
			)
			(2 0)
			(3
				(ego trySkill: 29)
				(newProp hide: dispose:)
				(= newProp 0)
				(newProp_2 dispose:)
				(= newProp_2 0)
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetGoo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 107 177 self)
			)
			(1
				(ego setMotion: MoveTo 106 184 self)
			)
			(2
				(ego
					view: 4
					setLoop: 0 1
					setCel: 0
					posn: 106 184
					setCycle: End self
				)
			)
			(3
				(switch local3
					(1
						(messager say: 11 76 0 0 self)
					)
					(else 
						(messager say: 11 24 0 0 self)
					)
				)
			)
			(4
				(ego view: 4 setCycle: Beg self)
			)
			(5
				(if (== local3 0)
					(ego
						solvePuzzle: 430 6
						posn: 109 184
						normalize:
						get: 11
						drop: 9 1
					)
				else
					(ego posn: 109 184 normalize:)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sThrowIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== local12 0)
					(if (!= (ego trySkill: 0 275) 1)
						(messager say: 15 6 14 0 self)
					else
						(switch local10
							(0
								(= local10 1)
								(messager say: 15 6 15 0 self)
							)
							(1
								(= local10 2)
								(messager say: 15 6 16 0 self)
							)
							(2
								(= local10 3)
								(messager say: 15 6 17 0 self)
							)
						)
					)
				else
					(messager say: 15 6 13 0 self)
				)
			)
			(1
				(if (!= local10 3)
					(theGame handsOn:)
					(self dispose:)
				else
					(aBush
						view: 800
						posn: 249 149
						setLoop: 2 1
						setCel: 0
						setCycle: End self
					)
				)
			)
			(2
				(aBush setLoop: 1 1 setCel: 0 setCycle: End self)
			)
			(3
				(Bset 347)
				(vGoo setCel: 0 init:)
				(aBush
					setLoop: 0 1
					setCel: 0
					approachX: 148
					approachY: 165
					setCycle: End self
				)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sThrowAtStones of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local9 1)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego setMotion: MoveTo 150 165 self)
			)
			(1
				(ego
					view: 8
					setLoop: 0 1
					setCel: 0
					posn: 160 165
					setPri: 196
					setCycle: End self
				)
			)
			(2
				(ego
					view: 806
					setLoop: 1 1
					setCel: 0
					posn: 160 165
					setPri: 196
					cycleSpeed: 13
					setCycle: CT 2 1 self
				)
			)
			(3
				(cond 
					((!= (ego trySkill: 10 275) 1) (= local13 1) (messager say: 15 6 18 0 self))
					((!= (ego trySkill: 0 250) 1) (= local13 1) (messager say: 15 6 19 0 self))
					(else (= local13 0) (self cue:))
				)
			)
			(4
				(ego setCel: 2 setCycle: CT 14 1 self)
			)
			(5
				(ego
					posn: 155 165
					normalize:
					loop: 0
					cycleSpeed: gTheObj_2CycleSpeed
				)
				(if local13
					(theGame handsOn:)
					(self dispose:)
				else
					(curRoom setScript: sStonesDislodge)
				)
			)
		)
	)
)

(instance sDislodgeRocks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aBush
					view: 800
					posn: 249 149
					setLoop: 2 1
					setCel: 0
					setCycle: End self
				)
			)
			(1
				(aBush setLoop: 1 1 setCel: 0 setCycle: End self)
			)
			(2
				(Bset 347)
				(vGoo setCel: 0 init:)
				(aBush
					setLoop: 0 1
					setCel: 0
					approachX: 148
					approachY: 165
					setCycle: End self
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sStonesDislodge of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 353)
				(aBush
					view: 800
					posn: 248 148
					setLoop: 2 1
					setPri: 199
					setCel: 0
					setCycle: End self
				)
			)
			(1
				(aBush setLoop: 1 1 setCel: 0 setCycle: End self)
			)
			(2
				(ego normalize: loop: 0)
				(tBush dispose:)
				(fRocks dispose:)
				(aBush
					view: 804
					setLoop: 9 1
					setCel: 0
					posn: 365 218 100
					approachX: 148
					approachY: 165
					setPri: 120
				)
				(vGoo setPri: 100 init:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance doForceBolt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local7 0)
				(pSquidStone setPri: 141)
				(ego normalize:)
				(if
				(or (== local14 1) (== (ego trySkill: 28 300) 1))
					(= local16 1)
					(if (== [egoStats 27] 0)
						(self setScript: sDislodgeRocks)
					else
						(self setScript: sStonesDislodge self)
					)
				else
					(= local14 1)
					(= local16 0)
					(messager say: 15 6 22 0 self)
				)
			)
			(1
				(if local16
					(messager say: 16 88 0 0 self)
					(= local16 0)
				else
					(ego normalize:)
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(2
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLiftBonsai of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 263 80 self)
			)
			(1
				(ego
					view: 8
					setLoop: 9 1
					setCel: 0
					posn: 271 82
					setCycle: 0
				)
				((ScriptID 809 3)
					posn: 275 62
					setPri: 174
					setCycle: End self
					init:
				)
			)
			(2
				(messager say: 15 6 25 0 self)
			)
			(3
				(aBush
					view: 804
					setLoop: 9 1
					setCycle: 0
					setPri: 196
					posn: 350 124 0
				)
				(= ticks 6)
			)
			(4
				(aBush posn: 347 108)
				((ScriptID 809 3) setCel: 3)
				(= ticks 6)
			)
			(5
				(aBush posn: 348 75)
				((ScriptID 809 3) setCel: 2)
				(= ticks 6)
			)
			(6
				(aBush posn: 349 69)
				((ScriptID 809 3) setCel: 1)
				(= ticks 6)
			)
			(7
				(aBush posn: 349 58)
				((ScriptID 809 3) setCel: 0)
				(= ticks 6)
			)
			(8
				((ScriptID 809 3) hide: dispose:)
				(aBush hide: dispose:)
				(ego
					normalize:
					loop: 2
					setPri: 152
					solvePuzzle: 415 15
					get: 48
				)
				(Bset 335)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sUseTheGrapnel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(= gTheObj_2MoveSpeed (ego moveSpeed?))
				(ego setMotion: PolyPath 150 165 self)
			)
			(1
				(ego
					view: 8
					setLoop: 0 1
					setCel: 0
					posn: 160 165
					setPri: 152
					setCycle: End self
				)
			)
			(2
				(ego
					setLoop: 7 1
					setCel: 0
					posn: 160 166
					setCycle: CT 1 1
				)
				((ScriptID 809 2) posn: 230 122 init: setCycle: End self)
			)
			(3
				(ego setLoop: 7 1 setCel: 1 setCycle: End self)
			)
			(4
				(ego
					normalize:
					setPri: 152
					cycleSpeed: gTheObj_2CycleSpeed
					moveSpeed: gTheObj_2MoveSpeed
					setMotion: MoveTo 242 145 self
				)
			)
			(5
				(ego
					view: 7
					setLoop: 0 1
					setCel: 0
					setScale:
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(6
				(if (== (ego trySkill: 11 250) 1)
					(ego
						view: 7
						posn: 242 141
						setLoop: 1 1
						setCel: 0
						setCycle: End
						setMotion: MoveTo 242 135 self
					)
				else
					(curRoom
						setScript: (ScriptID 805 0) 0 gTheObj_2CycleSpeed
					)
				)
			)
			(7
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(ego
					setLoop: 4 1
					setCel: 0
					posn: 242 81
					setCycle: End self
				)
			)
			(8
				(ego
					normalize:
					setPri: 152
					cycleSpeed: gTheObj_2CycleSpeed
					moveSpeed: gTheObj_2MoveSpeed
					setMotion: MoveTo 255 75 self
				)
			)
			(9 (Face ego 178 162 self))
			(10
				(ego
					view: 4
					setLoop: 1 1
					setCel: 0
					posn: 259 78
					setCycle: End self
				)
			)
			(11
				((ScriptID 809 2) hide: dispose:)
				(= seconds 1)
			)
			(12 (ego setCycle: Beg self))
			(13
				(ego normalize: setPri: 152 setMotion: MoveTo 290 80 self)
			)
			(14
				(curRoom addObstacle: (ledgePoly init: yourself:))
				(= local15 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local15 0)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego setPri: 152 setMotion: MoveTo 255 75 self)
			)
			(1
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(ego
					view: 4
					setLoop: 1 1
					setCel: 0
					posn: 259 78
					setCycle: End self
				)
			)
			(2
				((ScriptID 809 2)
					posn: 230 122
					setCel: 0
					init:
					setCycle: End self
				)
			)
			(3 (ego setCycle: Beg self))
			(4
				(ego normalize: setPri: 152 setMotion: MoveTo 242 81 self)
			)
			(5
				(ego view: 7 setLoop: 4 1)
				(ego
					setCel: (ego lastCel:)
					cycleSpeed: 8
					setCycle: Beg self
				)
			)
			(6
				(ego posn: 242 130 setLoop: 1 1)
				(ego
					setCel: (ego lastCel:)
					setCycle: Beg
					setMotion: MoveTo 242 145 self
				)
			)
			(7
				(ego setLoop: 0 1)
				(ego setCel: (ego lastCel:) setCycle: Beg self)
			)
			(8
				((ScriptID 809 2) hide: dispose:)
				(ego
					normalize:
					setPri: 152
					cycleSpeed: gTheObj_2CycleSpeed
					setMotion: MoveTo 142 172 self
				)
			)
			(9
				(ego normalize:)
				(curRoom addObstacle: (roomPoly init: yourself:))
				(curRoom addObstacle: (roomPoly2 init: yourself:))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sJumpThere of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego trySkill: 15)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(= gTheObj_2MoveSpeed (ego moveSpeed?))
				(if local15
					(ego setMotion: MoveTo 310 74 self)
				else
					(ego setMotion: MoveTo 281 59 self)
				)
			)
			(1
				(ego view: 30 setCel: 0 setSpeed: 6)
				(if local15
					(ego
						setLoop: 3 1
						setCel: 0
						setCycle: CT 8 1
						setMotion: JumpTo 281 59 self
					)
				else
					(ego
						setLoop: 4 1
						setCycle: CT 8 1
						setMotion: JumpTo 310 74 self
					)
				)
			)
			(2
				(ego normalize:)
				(if local15
					(= local15 0)
					(curRoom addObstacle: (roomPoly init: yourself:))
					(curRoom addObstacle: (roomPoly2 init: yourself:))
				else
					(= local15 1)
					(curRoom addObstacle: (ledgePoly init: yourself:))
				)
				(= ticks 6)
			)
			(3
				(ego
					normalize:
					moveSpeed: gTheObj_2MoveSpeed
					cycleSpeed: gTheObj_2CycleSpeed
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDoTheDarkSign of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 13 43 0 0 self)
			)
			(1
				(user canInput: 1)
				(Bset 147)
				(self setScript: (ScriptID 801 0) self)
			)
			(2
				(Bclr 147)
				(ego posn: 151 173 setLoop: 6 1 setPri: 185)
				((ScriptID 809 0) setPri: 160 init:)
				(= seconds 1)
			)
			(3
				(if (Btst 355)
					((ScriptID 809 0) setCycle: End self)
				else
					(messager say: 13 1 0 0 self)
				)
			)
			(4
				(ego normalize:)
				(if (Btst 355)
					(messager say: 15 6 2 0 self)
				else
					((ScriptID 809 0) hide: dispose:)
					(DisposeScript 801)
					(user canControl: 1)
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(5
				((ScriptID 809 0) setCycle: Beg self)
			)
			(6
				(ego get: 55)
				((ScriptID 809 0) hide: dispose:)
				(ego normalize:)
				(user canControl: 1)
				(DisposeScript 801)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sWait of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(curRoom setScript: sThrowIt)
			)
		)
	)
)

(instance tBush of TargActor
	(properties
		x 276
		y 164
		fixPriority 1
		view 804
		loop 11
		signal $4000
	)
	
	(method (cue)
		(Bclr 9)
		(if local7
			(curRoom setScript: doForceBolt)
		else
			(curRoom setScript: sWait)
		)
	)
)

(instance aBush of Actor
	(properties
		noun 2
		approachX 154
		approachY 163
		x 248
		y 149
		priority 250
		fixPriority 1
		view 804
		loop 8
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(33
				(cond 
					(
						(and
							(< 73 (ego y?))
							(< (ego y?) 81)
							(< 263 (ego x?))
							(< (ego x?) 312)
						)
						(if (Btst 353)
							(curRoom setScript: sLiftBonsai)
						else
							(messager say: 2 33 23)
						)
					)
					((Btst 353) (messager say: 2 33 24))
					(else (fRocks doVerb: 33))
				)
			)
			(-87
				(if (Btst 353)
					(self setScript: (ScriptID 804 4))
				else
					(messager say: 15 6 35 0 self)
				)
			)
			(87
				(if (and (> (ego y?) 100) (not local15))
					(= projX ((User curEvent?) x?))
					(= projY ((User curEvent?) y?))
					(curRoom setScript: (ScriptID 37) 0 aBush)
				else
					(messager say: 16 88 21)
				)
			)
			(4
				(if (Btst 347)
					(curRoom setScript: (ScriptID 804 3))
				else
					(messager say: 2 4 0 0)
				)
			)
			(88
				(if (and (> (ego y?) 100) (not local15))
					(= local7 1)
					(curRoom setScript: (ScriptID 32) 0 88)
				else
					(messager say: 16 88 21)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sparkle1 of Prop
	(properties
		x 160
		y 50
		priority 255
		fixPriority 1
		view 801
		loop 3
		signal $5001
	)
)

(instance sparkle2 of Prop
	(properties
		x 285
		y 110
		priority 255
		fixPriority 1
		view 801
		loop 4
		signal $5001
	)
)

(instance pSquidStone of Prop
	(properties
		noun 4
		x 178
		y 162
		priority 141
		fixPriority 1
		view 801
		signal $5001
	)
	
	(method (handleEvent event)
		(if
			(and
				(== ((theIconBar getCursor:) view?) 940)
				(> (pSquidStone cel?) 0)
				(self onMe: event)
			)
			(event claimed: 1)
			(if (< (ego y?) 100)
				(curRoom setScript: (ScriptID 802 1))
			else
				(curRoom setScript: (ScriptID 802 0))
			)
		else
			(event claimed: 0)
			(super handleEvent: event &rest)
		)
	)
	
	(method (doVerb theVerb)
		(= projX ((User curEvent?) x?))
		(= projY ((User curEvent?) y?))
		(return
			(switch theVerb
				(1
					(cond 
						((> (pSquidStone cel?) 0) (messager say: 4 1 41 0))
						((or (== heroType 3) (== heroType 0)) (messager say: 4 1 6))
						(else (messager say: 4 1 0 0))
					)
				)
				(4
					(if (or (== heroType 3) (== heroType 0))
						(cond 
							((> (pSquidStone cel?) 0)
								(if (< (ego y?) 100)
									(curRoom setScript: (ScriptID 802 1))
								else
									(curRoom setScript: (ScriptID 802 0))
								)
							)
							((> (ego y?) 100) (curRoom setScript: (ScriptID 806 1)))
							(else (messager say: 4 4 0 0))
						)
					else
						(messager say: 4 4 0 0)
					)
				)
				(82
					(curRoom setScript: (ScriptID 11) 0 self)
					(return 1)
				)
				(-82
					(if (== (pSquidStone cel?) 0)
						(pSquidStone setPri: 110)
						(curRoom setScript: (ScriptID 806 0))
					else
						(curRoom setScript: 0)
						(messager say: 4 1 41 0)
						(theGame handsOn:)
					)
				)
				(60
					(cond 
						((> (pSquidStone cel?) 0) (messager say: 4 1 41 0))
						((> (ego y?) 100)
							(if (== (pSquidStone cel?) 0)
								(if (== (pSquid cel?) 0)
									(curRoom setScript: (ScriptID 804 2))
								else
									(messager say: 4 60 31)
								)
							else
								(messager say: 15 6 34)
							)
						)
						(else (messager say: 15 6 34))
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance pSquid of Prop
	(properties
		noun 13
		approachX 146
		approachY 158
		x 173
		y 189
		z 100
		priority 152
		fixPriority 1
		view 803
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(43
				(if (> (ego y?) 100)
					(if (or (Btst 355) (ego has: 55))
						(messager say: 15 6 11)
					else
						(curRoom setScript: sDoTheDarkSign)
					)
				else
					(messager say: 15 6 34)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vGoo of View
	(properties
		noun 11
		x 249
		y 148
		view 804
		loop 7
		signal $4001
	)
	
	(method (doVerb theVerb)
		(if (not (Btst 335))
			(aBush doVerb: theVerb)
		else
			(fLedge doVerb: theVerb)
		)
	)
)

(instance fAboveLedge of Feature
	(properties
		noun 6
		nsLeft 220
		nsTop 67
		nsRight 319
		nsBottom 87
		sightAngle 180
		x 269
		y 77
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 70 145 129 145 129 150 70 150
						yourself:
					)
					4
					5
					2
					(ScriptID 803 3)
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(33
				(cond 
					(local15 (curRoom setScript: sGetDown))
					((> (ego y?) 100) (curRoom setScript: sUseTheGrapnel))
					(else (messager say: 16 33 21))
				)
			)
			(4
				(if local15
					(messager say: 5 4 27)
				else
					(messager say: 5 4 0)
				)
			)
			(10
				(cond 
					((< (ego y?) 65) (curRoom setScript: sJumpThere))
					(local15 (messager say: 6 159 27))
					(else (messager say: 5 159 0))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fPath of Feature
	(properties
		noun 6
		nsLeft 202
		nsTop 49
		nsRight 319
		nsBottom 63
		sightAngle 180
		x 202
		y 49
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(10
				(cond 
					((< (ego y?) 65) (messager say: 6 159 27))
					(local15 (curRoom setScript: sJumpThere))
					(else (messager say: 6 159 26))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fStream of Feature
	(properties
		noun 11
		nsLeft 129
		nsTop 149
		nsRight 283
		nsBottom 189
		sightAngle 180
		x 206
		y 169
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init:
						113
						186
						129
						180
						149
						176
						149
						175
						198
						166
						241
						155
						243
						149
						266
						146
						281
						152
						262
						162
						271
						174
						212
						183
						208
						189
						113
						189
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(24
				(if (> (ego y?) 100)
					(= local3 0)
					(curRoom setScript: sGetGoo)
				else
					(messager say: 11 24 29)
				)
			)
			(76
				(if (> (ego y?) 100)
					(= local3 1)
					(curRoom setScript: sGetGoo)
				else
					(messager say: 11 76 29)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fLedge of Feature
	(properties
		noun 5
		nsLeft 238
		nsTop 116
		nsRight 275
		nsBottom 134
		sightAngle 180
		x 256
		y 116
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 203 50 220 50 220 65 203 65
						yourself:
					)
					1
					7
					5
					(ScriptID 804 1)
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(33 (messager say: 15 6 20))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fSideLedge of Feature
	(properties
		noun 5
		nsLeft 238
		nsTop 116
		nsRight 275
		nsBottom 134
		sightAngle 180
		x 256
		y 125
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init:
						296
						138
						305
						137
						310
						134
						307
						132
						306
						127
						319
						127
						319
						174
						275
						176
						261
						162
						280
						155
						281
						138
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(33 (messager say: 15 6 20))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fIsland of Feature
	(properties
		noun 7
		nsLeft 128
		nsTop 94
		nsRight 182
		nsBottom 120
		sightAngle 180
		x 155
		y 107
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 108 51 125 51 125 65 108 65
						yourself:
					)
					1
					7
					5
					(ScriptID 803 0)
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
)

(instance fIslandTree of Feature
	(properties
		noun 8
		nsLeft 106
		nsRight 171
		nsBottom 94
		sightAngle 180
		x 138
		y 47
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init:
						107
						3
						163
						0
						171
						0
						165
						7
						164
						22
						147
						28
						148
						35
						141
						37
						135
						50
						141
						60
						150
						60
						157
						62
						172
						73
						170
						95
						157
						103
						129
						102
						130
						77
						129
						68
						120
						58
						112
						56
						112
						50
						117
						51
						117
						44
						128
						39
						129
						29
						117
						17
						100
						7
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance fTreeBranch of Feature
	(properties
		noun 9
		nsLeft 41
		nsTop -1
		nsRight 107
		nsBottom 35
		sightAngle 180
		x 74
		y 17
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 43 114 84 114 84 120 43 120
						yourself:
					)
					3
					7
					1
					(ScriptID 803 1)
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
)

(instance fFrontTree of Feature
	(properties
		noun 10
		nsTop -1
		nsRight 41
		nsBottom 189
		sightAngle 180
		x 20
		y 94
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 54 137 123 137 123 143 54 143
						yourself:
					)
					3
					6
					7
					(ScriptID 803 2)
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
)

(instance fRocks of TargFeature
	(properties
		noun 16
		nsLeft 236
		nsTop 86
		nsRight 319
		nsBottom 180
		sightAngle 180
		approachX 154
		approachY 163
		x 274
		y 144
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init: 268 146 262 146 254 131 267 131 279 130 282 137 283 148 281 152
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(= projX ((User curEvent?) x?))
		(= projY ((User curEvent?) y?))
		(return
			(switch theVerb
				(33
					(cond 
						((< (ego y?) 100) (messager say: 16 33 21))
						((Btst 347) (messager say: 0 33 0) (return 1))
						(else (curRoom setScript: sThrowAtStones))
					)
				)
				(4 (messager say: 2 4 0))
				(21
					(cond 
						((Btst 347) (messager say: 0 33 0) (return 1))
						((< (ego y?) 100) (messager say: 16 33 21))
						(else (curRoom setScript: sThrowRock))
					)
				)
				(86
					(curRoom setScript: (ScriptID 32) 0 86)
				)
				(93
					(curRoom setScript: (ScriptID 32) 0 93)
				)
				(79
					(curRoom setScript: (ScriptID 32) 0 79)
				)
				(88
					(= local7 1)
					(if (and (> (ego y?) 100) (not local15))
						(curRoom setScript: (ScriptID 32) 0 88)
					else
						(messager say: 16 88 21)
					)
				)
				(37
					(cond 
						((== (ego has: 5) 1) (messager say: 15 6 42))
						((or (< (ego y?) 100) local15) (messager say: 16 33 21))
						(else (++ local21) (curRoom setScript: sThrowDagger))
					)
				)
				(82
					(curRoom setScript: (ScriptID 11) 0 self)
					(return 1)
				)
				(-82
					(curRoom setScript: 0)
					(messager say: 15 6 34)
					(theGame handsOn:)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (getHurt)
		(Bclr 9)
		(theGame handsOn:)
		(cond 
			((== local20 1) (= local20 0) (messager say: 16 4 34))
			((== local7 0) (curRoom setScript: sThrowIt))
			(else (curRoom setScript: doForceBolt) (= local7 0))
		)
	)
)

(instance roomPoly of Polygon
	(properties
		type $0002
	)
	
	(method (init)
		(super
			init:
				-300
				0
				619
				0
				619
				53
				247
				57
				232
				53
				125
				53
				74
				63
				56
				75
				84
				84
				104
				95
				54
				114
				85
				158
				44
				165
				-300
				140
		)
	)
)

(instance roomPoly2 of Polygon
	(properties
		type $0002
	)
	
	(method (init)
		(super
			init:
				-300
				189
				26
				175
				88
				166
				94
				181
				143
				173
				224
				157
				228
				145
				221
				137
				202
				139
				212
				152
				193
				159
				158
				165
				139
				157
				126
				155
				66
				117
				116
				97
				116
				88
				70
				74
				80
				66
				119
				59
				169
				57
				210
				57
				253
				64
				619
				59
				619
				189
		)
	)
)

(instance ledgePoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super init: 312 73 263 73 263 81 312 81)
	)
)
