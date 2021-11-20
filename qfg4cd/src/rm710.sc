;;; Sierra Script 1.0 - (do not remove this comment)
(script# 710)
(include sci.sh)
(use Main)
(use GloryRm)
(use TargFeature)
(use TellerIcon)
(use EgoDead)
(use Scaler)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm710 0
	sLevitateOverPit 1
	torchEff 2
	sCombatOrNot 3
)

(local
	local0
	local1
	gTheObj_2CycleSpeed
	local3
	local4 =  1
	local5
	local6 =  1
	theTheVerb
)
(instance rm710 of GloryRm
	(properties
		picture 710
	)
	
	(method (init)
		(if debugging (Bset 101) (= prevRoomNum 790))
		(switch prevRoomNum
			(810
				(theMusic number: 203 setLoop: -1)
				(cond 
					((Btst 462) (theMusic play:))
					((Btst 459) (theMusic mute: 1 9 play:))
					((Btst 457) (theMusic mute: 1 7 mute: 1 9 play:))
					((Btst 455) (theMusic mute: 1 6 mute: 1 7 mute: 1 8 mute: 1 9 play:))
					(else
						(theMusic
							mute: 1 5
							mute: 1 6
							mute: 1 7
							mute: 1 8
							mute: 1 9
							play:
						)
					)
				)
				(ego
					init:
					x: 177
					y: 171
					normalize: 0
					setPri: 152
					setScaler: Scaler 71 39 95 29
				)
				(torchEff setPri: 152)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								0
								189
								0
								0
								319
								0
								319
								189
								150
								189
								150
								184
								266
								185
								284
								161
								258
								161
								221
								161
								210
								154
								210
								140
								173
								140
								167
								163
								146
								163
								127
								189
							yourself:
						)
				)
			)
			(790
				(theMusic
					number: 203
					setLoop: -1
					mute: 1 5
					mute: 1 6
					mute: 1 7
					mute: 1 8
					mute: 1 9
					play:
				)
				(ego init: normalize: setScaler: Scaler 71 39 95 29)
			)
			(else 
				(ego init: normalize: setScaler: Scaler 71 39 95 29)
			)
		)
		(walkHandler addToFront: self)
		(RemapColors 1 253 112 173 62)
		(torchEff init: setScaler: ego setCycle: RandCycle)
		(blowBit1 init: setCycle: Fwd)
		(blowBit2 init: setCycle: Fwd)
		(blowBit3 init: setCycle: Fwd)
		(blowBit4 init: setCycle: Fwd)
		(leftExitF init: approachVerbs: 4)
		(rightExitF init: approachVerbs: 4)
		(bridge init:)
		(pillar init:)
		(upperLedge init:)
		(lowerLedge init:)
		(largeCave init:)
		(smallCave init:)
		(backLedge init:)
		(if (== prevRoomNum 790) (= monsterHealth 400))
		(if (Btst 101)
			(Bset 373)
			(ego changeGait:)
			(if (not (Btst 461)) (book init: approachVerbs: 4))
			(priestHorror init:)
			(cond 
				((<= monsterHealth 0) (priestHorror loop: 1 cel: 0))
				((== heroType 2) (priestHorror setScript: sHorrorSleeps))
				(else (= local4 0) (priestHorror loop: 1 cel: 2))
			)
		else
			(ego code: warnCode)
			(tentacle init: hide:)
		)
		(super init: &rest)
		(cond 
			((== prevRoomNum 790)
				(Bset 373)
				(self
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								8
								105
								60
								94
								110
								94
								106
								88
								138
								88
								164
								74
								131
								74
								113
								65
								59
								65
								28
								47
								94
								0
								319
								0
								319
								189
								0
								189
								0
								0
								20
								0
								46
								24
								14
								48
								32
								62
								8
								69
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 102 68 112 75 109 86 27 88 17 78 29 68
							yourself:
						)
				)
				(ego x: 50 y: 28 changeGait:)
				(self setScript: sEnter 0 1)
			)
			((== prevRoomNum 810)
				(if (== battleResult 1)
					(self setScript: sHorrorKillsEgo)
				else
					(self setScript: sBackFromCombat)
				)
			)
			(else
				(self
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								271
								47
								293
								58
								271
								64
								286
								68
								304
								63
								315
								45
								293
								36
								619
								11
								319
								189
								0
								189
								0
								0
								267
								0
								302
								19
								284
								35
								241
								37
								248
								43
								202
								43
								202
								47
							yourself:
						)
				)
				(ego x: 308 y: 21)
				(self setScript: sEnter)
			)
		)
		(theGame save: 1)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script)
			(
				(ego
					inRect:
						(leftExitF nsLeft?)
						(leftExitF nsTop?)
						(leftExitF nsRight?)
						(leftExitF nsBottom?)
				)
				(if (Btst 101)
					(ego setMotion: 0)
					(self setScript: backOff)
				else
					(leftExitF doVerb: 4)
				)
			)
			(
				(ego
					inRect:
						(rightExitF nsLeft?)
						(rightExitF nsTop?)
						(rightExitF nsRight?)
						(rightExitF nsBottom?)
				)
				(rightExitF doVerb: 4)
			)
			(
				(and
					(not (< (ego y?) 125))
					(> monsterHealth 0)
					(== heroType 2)
					(!= egoGait 2)
					(or
						(== ((theIconBar getCursor:) view?) 940)
						(== ((theIconBar getCursor:) view?) 942)
					)
				)
				(curRoom setScript: sHorrorWakes)
			)
		)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(89
				(cond 
					((Btst 101)
						(cond 
							((and (> (ego x?) 190) (< (ego y?) 125)) (curRoom setScript: (ScriptID 711 4)))
							((and (< (ego x?) 190) (< (ego y?) 125)) (curRoom setScript: (ScriptID 711 2)))
							(
							(and (not (< (ego y?) 125)) (> (ego x?) 160)) (curRoom setScript: (ScriptID 711 5)))
							(else (curRoom setScript: (ScriptID 711 3)))
						)
					)
					((and (> (ego x?) 190) (< (ego y?) 125)) ((ScriptID 31 0) init: 222 45 30 0 2))
					(else ((ScriptID 31 0) init: 119 79 35 0 2))
				)
			)
			(86 (messager say: 0 86 0))
			(85
				(if
					(and
						(Btst 101)
						(or
							(and (!= heroType 2) (not local3))
							(and (== heroType 2) (not local4))
						)
						(> monsterHealth 0)
					)
					(curRoom setScript: sCalmDazzlePH 0 0)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(83
				(if
					(and
						(Btst 101)
						(or
							(and (!= heroType 2) (not local3))
							(and (== heroType 2) (not local4))
						)
						(> monsterHealth 0)
					)
					(curRoom setScript: sCalmDazzlePH 0 1)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(10 (messager say: 0 159 0))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (setScript newScript)
		(if (== newScript (ScriptID 31 1))
			(newScript next: sLevitateDone)
		)
		(super setScript: newScript &rest)
	)
)

(instance warnCode of Code
	(properties)
	
	(method (doit)
		(if (< (ego x?) 215)
			(ego code: 0 setMotion: 0)
			(curRoom setScript: warnBack)
		)
	)
)

(instance fallCode of Code
	(properties)
	
	(method (doit)
		(if (< (ego x?) 203)
			(ego code: 0 setMotion: 0)
			(curRoom setScript: sTentacleDeath 0 0)
		)
	)
)

(instance warnBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 10 6 1 0 self)
			)
			(1
				(ego code: fallCode)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance backOff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 10 6 34 0 self)
			)
			(1
				(ego setMotion: PolyPath 28 55 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance crossByHand of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (== state 2) (ego useStamina: 1))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(ego code: 0 setMotion: MoveTo 206 46 self)
			)
			(1
				(= gGTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					cycleSpeed: defaultCycles
					moveSpeed: defaultCycles
					view: 7
					setLoop: 4 1
					cel: 11
					setCycle: Beg self
				)
			)
			(2
				(if (not (Btst 403)) (ego solvePuzzle: 403 15))
				(= local6 2)
				(ego
					x: 205
					y: 58
					z: 25
					view: 39
					setLoop: 1 1
					setCycle: Walk
					setMotion: MoveTo 162 63 self
				)
				(if (not (Btst 101))
					(tentacle setScript: sTentacleDeath 0 1)
				)
			)
			(3
				(if (not (Btst 392))
					(Bset 392)
					(messager say: 10 6 32 0 self)
				else
					(self cue:)
				)
			)
			(4
				(if (not (Btst 101)) ((tentacle script?) cue:))
				(= local6 1)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								8
								105
								60
								94
								110
								94
								106
								88
								138
								88
								164
								74
								131
								74
								113
								65
								59
								65
								28
								47
								94
								0
								319
								0
								319
								189
								0
								189
								0
								0
								20
								0
								46
								24
								14
								48
								32
								62
								8
								69
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 102 68 112 75 109 86 27 88 17 78 29 68
							yourself:
						)
				)
				(ego
					y: 73
					z: 0
					normalize: 1
					cycleSpeed: gGTheObj_2CycleSpeed
					moveSpeed: gGTheObj_2CycleSpeed
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance crossByHandLeft of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (== state 1) (ego useStamina: 1))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(ego setMotion: MoveTo 162 73 self)
			)
			(1
				(= gGTheObj_2CycleSpeed (ego cycleSpeed?))
				(if (not (Btst 101))
					(tentacle setScript: sTentacleDeath 0 1)
				)
				(= local6 2)
				(ego
					cycleSpeed: defaultCycles
					moveSpeed: defaultCycles
					y: 63
					z: 25
					view: 39
					setLoop: 0 1
					setCycle: Walk
					setMotion: MoveTo 197 60 self
				)
			)
			(2
				(= local6 1)
				(ego
					z: 0
					y: 46
					view: 7
					setLoop: 5 1
					cel: 0
					setCycle: End self
				)
			)
			(3
				(ego
					normalize: 0
					cycleSpeed: gGTheObj_2CycleSpeed
					moveSpeed: gGTheObj_2CycleSpeed
					setMotion: PolyPath 230 46 self
				)
			)
			(4
				(if (not (Btst 101)) ((tentacle script?) cue:))
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								271
								47
								293
								58
								271
								64
								286
								68
								304
								63
								315
								45
								293
								36
								619
								11
								319
								189
								0
								189
								0
								0
								267
								0
								302
								19
								284
								35
								241
								37
								248
								43
								202
								43
								202
								47
							yourself:
						)
				)
				(ego code: warnCode)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance tightRope of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (or (== state 4) (== state 5))
			(ego useStamina: 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(ego code: 0 setMotion: MoveTo 226 44 self)
			)
			(1
				(ego normalize: 3)
				(= ticks 15)
			)
			(2
				(= gGTheObj_2CycleSpeed (ego cycleSpeed?))
				(if (not (Btst 101))
					(tentacle setScript: sTentacleDeath 0 1)
				)
				(ego
					cycleSpeed: defaultCycles
					moveSpeed: defaultCycles
					x: 227
					y: 54
					z: 29
					view: 7
					setLoop: 4 1
					cel: 0
					setCycle: End self
				)
			)
			(3
				(if (not (Btst 403)) (ego solvePuzzle: 403 15))
				(ego
					x: 224
					y: 39
					z: 10
					view: 12
					setLoop: 1 1
					setCycle: Walk
					setMotion: MoveTo 213 43 self
				)
			)
			(4
				(ego setMotion: MoveTo 196 47 self)
			)
			(5
				(ego setMotion: MoveTo 177 51 self)
			)
			(6
				(ego setMotion: MoveTo 152 51 self)
			)
			(7
				(ego
					y: 65
					z: 25
					view: 7
					setLoop: 4 1
					cel: 11
					setCycle: Beg self
				)
			)
			(8
				(if (not (Btst 392))
					(Bset 392)
					(messager say: 10 6 32 0 self)
				else
					(self cue:)
				)
			)
			(9
				(if (not (Btst 101)) ((tentacle script?) cue:))
				(ego
					view: 7
					setLoop: 3 1
					cel: 0
					y: 61
					z: 0
					setCycle: Walk
					setMotion: MoveTo 152 74 self
				)
			)
			(10
				(ego
					normalize: 3
					cycleSpeed: gGTheObj_2CycleSpeed
					moveSpeed: gGTheObj_2CycleSpeed
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								8
								105
								60
								94
								110
								94
								106
								88
								138
								88
								164
								74
								131
								74
								113
								65
								59
								65
								28
								47
								94
								0
								319
								0
								319
								189
								0
								189
								0
								0
								20
								0
								46
								24
								14
								48
								32
								62
								8
								69
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 102 68 112 75 109 86 27 88 17 78 29 68
							yourself:
						)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance tightRopeLeft of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (or (== state 6) (== state 7))
			(ego useStamina: 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(ego setMotion: MoveTo 152 74 self)
			)
			(1
				(ego normalize: 3)
				(= ticks 15)
			)
			(2
				(= gGTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 7
					setLoop: 3 1
					cel: 0
					setCycle: Walk
					setSpeed: 15
					setMotion: MoveTo 152 61 self
				)
			)
			(3
				(ego
					setSpeed: defaultCycles
					y: 65
					z: 25
					view: 7
					setLoop: 5 1
					cel: 0
					setCycle: End self
				)
			)
			(4
				(if (not (Btst 101))
					(tentacle setScript: sTentacleDeath 0 1)
				)
				(ego
					x: 152
					y: 51
					z: 10
					view: 12
					setLoop: 0 1
					setCycle: Walk
					setMotion: MoveTo 152 51 self
				)
			)
			(5
				(ego setMotion: MoveTo 177 51 self)
			)
			(6
				(ego setMotion: MoveTo 196 47 self)
			)
			(7
				(ego setMotion: MoveTo 213 43 self)
			)
			(8
				(ego setMotion: MoveTo 224 39 self)
			)
			(9
				(ego
					view: 7
					x: 227
					y: 50
					z: 25
					setLoop: 4 1
					cel: 11
					setCycle: Beg self
				)
			)
			(10
				(if (not (Btst 101)) ((tentacle script?) cue:))
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								271
								47
								293
								58
								271
								64
								286
								68
								304
								63
								315
								45
								293
								36
								619
								11
								319
								189
								0
								189
								0
								0
								267
								0
								302
								19
								284
								35
								241
								37
								248
								43
								202
								43
								202
								47
							yourself:
						)
				)
				(ego
					x: 226
					y: 44
					z: 0
					setSpeed: gGTheObj_2CycleSpeed
					code: warnCode
					normalize: 0
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLevitateOverPit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(= register (if (> (ego x?) 190) 1 else 0))
				(if register
					(ego setMotion: PolyPath 218 48 self)
				else
					(ego setMotion: PolyPath 143 83 self)
				)
			)
			(1
				1
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 711
					loop: 0
					cel: 0
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(2
				2
				(messager say: 10 6 7 0 self)
			)
			(3
				3
				(ego
					cycleSpeed: gTheObj_2CycleSpeed
					setLoop: 1 1
					setCycle: Fwd
				)
				(keyDownHandler addToFront: self)
				(mouseDownHandler addToFront: self)
				(theGame handsOn:)
			)
			(4
				4
				(theGame handsOff:)
				(keyDownHandler delete: self)
				(mouseDownHandler delete: self)
				(if register (= cycles 1) else (self changeState: 8))
			)
			(5
				5
				(if (not (Btst 101))
					(tentacle setScript: sTentacleDeath 0 1)
				)
				(if (not (Btst 403)) (ego solvePuzzle: 403 15))
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(ego code: 0)
				(theMusic2 number: 941 play:)
				(sparklies init: setCycle: Fwd)
				(if register
					(ego setMotion: MoveTo 143 83 self)
				else
					(ego setMotion: MoveTo 218 48 self)
				)
			)
			(6
				6
				(if (not (Btst 101)) ((tentacle script?) cue:))
				(if register
					(curRoom
						addObstacle:
							((Polygon new:)
								type: 2
								init:
									8
									105
									60
									94
									110
									94
									106
									88
									138
									88
									164
									74
									131
									74
									113
									65
									59
									65
									28
									47
									94
									0
									319
									0
									319
									189
									0
									189
									0
									0
									20
									0
									46
									24
									14
									48
									32
									62
									8
									69
								yourself:
							)
							((Polygon new:)
								type: 2
								init: 102 68 112 75 109 86 27 88 17 78 29 68
								yourself:
							)
					)
				else
					(curRoom
						addObstacle:
							((Polygon new:)
								type: 2
								init:
									271
									47
									293
									58
									271
									64
									286
									68
									304
									63
									315
									45
									293
									36
									619
									11
									319
									189
									0
									189
									0
									0
									267
									0
									302
									19
									284
									35
									241
									37
									248
									43
									202
									43
									202
									47
								yourself:
							)
					)
				)
				(sparklies dispose:)
				(theMusic2 fade:)
				(= cycles 2)
			)
			(7
				7
				(= state 8)
				(if (not (Btst 392))
					(Bset 392)
					(messager say: 10 6 32 0 self)
				else
					(self cue:)
				)
			)
			(8
				(messager say: 10 6 8 0 self)
			)
			(9
				9
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 711
					setLoop: 0 1
					setCel: 2
					cycleSpeed: 8
					setCycle: Beg self
				)
			)
			(10
				10
				(ego cycleSpeed: gTheObj_2CycleSpeed normalize: 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(and (== state 3) (OneOf (event type?) 4 32 1))
			(if (== (event message?) KEY_Y)
				(= register 1)
			else
				(= register 0)
			)
			(self cue:)
			(event claimed: 1)
			(return)
		else
			(super handleEvent: event)
		)
	)
)

(instance sLevitateDone of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 10 6 9 0 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTentacleDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(if (not register) (theGame handsOff:))
				(tentacle setLoop: 0 1 setCel: 0 show: setCycle: End self)
			)
			(1
				1
				(tentacle setLoop: 1 1 setCel: 0 setCycle: End self)
			)
			(2 2 (= cycles 6))
			(3
				3
				(if register
					(= state 5)
					(tentacle setCycle: RandCycle tentacle)
				else
					(tentacle setLoop: 4 1 setCel: 0 setCycle: CT 3 1 self)
				)
			)
			(4
				4
				(ego hide:)
				(torchEff hide:)
				(tentacle cycleSpeed: 10 setCycle: End self)
			)
			(5 5 (EgoDead 1 710 6))
			(6
				6
				(tentacle setLoop: 0 1 setCel: 10 setCycle: Beg self)
			)
			(7
				7
				(tentacle hide:)
				(= register 0)
				(self dispose:)
			)
		)
	)
)

(instance sCombatOrNot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
					(and
						(Btst 101)
						(or
							(and (!= heroType 2) (not local3))
							(and (== heroType 2) (not local4))
						)
						(> monsterHealth 0)
					)
					(self setScript: sGoToCombat)
				else
					(= cycles 1)
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sHorrorSleeps of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 6))
			(1
				(priestHorror setCel: 0)
				(= seconds 2)
			)
			(2
				(= state -1)
				(priestHorror setCycle: End self)
			)
		)
	)
)

(instance sGetBook of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(= cycles 1)
				else
					(ego
						view: 4
						loop: 0
						cel: 0
						x: 182
						y: 165
						setCycle: End self
					)
				)
			)
			(1
				(book hide:)
				(ego solvePuzzle: 461 15)
				(if register (= cycles 2) else (ego setCycle: Beg self))
			)
			(2
				(if register
					(messager say: 14 87 16 0 self)
				else
					(ego x: 185 y: 165 normalize: 0 setPri: 185)
					(= cycles 1)
				)
			)
			(3
				(messager say: 10 6 22 0 self)
			)
			(4
				(ego setPri: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sHorrorFetch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 14 87 10 0 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sHorrorWakes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(priestHorror setScript: 0 setCycle: End self)
			)
			(1
				(if local5
					(priestHorror loop: 1 cel: 0 setCycle: End self)
				else
					(priestHorror loop: 1 cel: 0 setCycle: CT 1 1 self)
				)
			)
			(2
				(if local5
					(messager say: 10 6 20 0 self)
				else
					(= seconds 3)
				)
			)
			(3
				(if local5
					(self setScript: sGoToCombat)
				else
					(messager say: 10 6 19 0 self)
				)
			)
			(4 (= local5 1) (= seconds 2))
			(5
				(priestHorror setCycle: Beg self)
			)
			(6
				(priestHorror loop: 0 setScript: sHorrorSleeps)
				(= cycles 2)
			)
			(7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sKillHorror of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(priestHorror setCycle: End self)
			)
			(1
				(priestHorror loop: 1 cel: 0 setCycle: End self)
			)
			(2
				(if register
					(messager say: 10 6 15 0 self)
				else
					(= local4 0)
					(= local3 0)
					(messager say: 14 theTheVerb 0 0 self)
				)
			)
			(3
				(if register (priestHorror setCel: 0))
				(if (not (curRoom script?))
					(theGame handsOn:)
				else
					(= disabledIcons 0)
				)
				(self dispose:)
			)
		)
	)
)

(instance sCalmDazzlePH of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local3 1)
				(if register
					(messager say: 0 83 10 0 self)
				else
					(messager say: 0 85 10 0 self)
				)
			)
			(1
				(if register
					(priestHorror loop: 1 setCel: 1)
				else
					(priestHorror loop: 0 cel: 0 setScript: sHorrorSleeps)
				)
				(= cycles 2)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGoToCombat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 378)
				(= cycles 2)
			)
			(1
				(theGame handsOn:)
				(curRoom newRoom: 810)
			)
		)
	)
)

(instance sBackFromCombat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (OneOf heroType 0 3) (ego solvePuzzle: 489 4 1))
				(= cycles 1)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sHorrorKillsEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(switch heroType
					(1 (EgoDead 23 0 937 1 912))
					(2 (EgoDead 21 0 937 1 912))
					(else  (EgoDead 12 0 937 1 912))
				)
			)
		)
	)
)

(instance torchEff of Prop
	(properties
		view 775
	)
	
	(method (doit)
		(super doit: &rest)
		(= x (ego x?))
		(= z (+ (ego z?) 1))
		(switch local6
			(1 (= y (+ (ego y?) 1)))
			(2 (= y (+ (ego y?) 32)))
		)
	)
	
	(method (doVerb theVerb)
		(ego doVerb: theVerb)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance tentacle of Prop
	(properties
		x 222
		y 123
		view 710
		signal $4001
		cycleSpeed 8
	)
)

(instance priestHorror of TargProp
	(properties
		x 239
		y 140
		view 855
		signal $4001
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 37 86 88 79 93)
				(cond 
					((<= monsterHealth 0) (messager say: 10 6 26))
					(
						(and
							(== theVerb 37)
							(== ((inventory at: 5) amount?) 1)
						)
						(messager say: 10 6 35)
					)
					(else (ego setScript: (ScriptID 32) 0 (= theTheVerb theVerb)))
				)
			)
			((== theVerb 1)
				(cond 
					((<= monsterHealth 0) (messager say: 14 1 14))
					((== local4 1) (messager say: 14 1 11))
					((== local3 1) (messager say: 14 1 28))
					(else (messager say: 14 1 10))
				)
			)
			((== theVerb 4)
				(cond 
					((< (ego y?) 125) (messager say: 14 4 27))
					((<= monsterHealth 0) (messager say: 14 4 14))
					(else (messager say: 14 4 11))
				)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
	
	(method (getHurt param1 param2)
		(if
		(<= (= monsterHealth (- monsterHealth param2)) 0)
			(self setScript: sKillHorror 0 1)
		else
			(self setScript: sKillHorror 0 0)
		)
	)
)

(instance blowBit1 of Prop
	(properties
		noun 16
		sightAngle 180
		x 1
		y 98
		priority 1
		fixPriority 1
		view 713
		signal $4001
		cycleSpeed 14
	)
)

(instance blowBit2 of Prop
	(properties
		noun 16
		sightAngle 180
		x 114
		y 67
		priority 1
		fixPriority 1
		view 713
		loop 1
		signal $4001
		cycleSpeed 8
	)
)

(instance blowBit3 of Prop
	(properties
		noun 16
		sightAngle 180
		x 121
		y 161
		priority 170
		fixPriority 1
		view 713
		loop 2
		signal $4001
		cycleSpeed 14
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 33)
			(lowerLedge doVerb: theVerb &rest)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance blowBit4 of Prop
	(properties
		noun 16
		sightAngle 180
		x 195
		y 35
		priority 1
		fixPriority 1
		view 713
		loop 3
		signal $4001
		cycleSpeed 8
	)
)

(instance book of View
	(properties
		noun 15
		sightAngle 180
		approachX 185
		approachY 165
		x 195
		y 158
		priority 130
		fixPriority 1
		view 855
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (< (ego y?) 125)
					(messager say: 15 4 24)
				else
					(curRoom setScript: sGetBook)
				)
			)
			(87
				(if (< (ego y?) 125)
					(theGame handsOff:)
					(ego setScript: (ScriptID 37) 0 self)
				else
					(messager say: 15 87 25)
				)
			)
			(-87
				(if (or (<= monsterHealth 0) local3 local4)
					(curRoom setScript: sGetBook 0 1)
				else
					(curRoom setScript: sHorrorFetch)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ropeTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (doVerb theVerb)
		(if (and (== theVerb 4) (Btst 388))
			(theGame pragmaFail:)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (sayMessage)
		(switch iconValue
			(4
				(self clean:)
				(if (< (ego x?) 170)
					(curRoom setScript: crossByHandLeft)
				else
					(curRoom setScript: crossByHand)
				)
			)
			(5
				(self clean:)
				(if (< (ego x?) 170)
					(curRoom setScript: tightRopeLeft)
				else
					(curRoom setScript: tightRope)
				)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				4
				(cond 
					(
					(or (OneOf heroType 0 3) (>= [egoStats 0] 320)))
					((< [egoStats 0] 320) (if (Btst 101) (== heroType 1)))
				)
				6
				(if (OneOf heroType 1 3) else (>= [egoStats 19] 310))
				5
				(if (== heroType 2) else (>= [egoStats 15] 110))
		)
	)
)

(instance bridge of Feature
	(properties
		noun 3
		nsLeft 128
		nsTop 22
		nsRight 239
		nsBottom 71
		approachX 239
		approachY 45
		x 183
		y 46
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 2
					init:
						125
						68
						142
						34
						153
						36
						186
						33
						230
						20
						239
						37
						229
						42
						218
						41
						224
						30
						186
						41
						151
						44
						162
						67
						138
						72
					yourself:
				)
		)
		(super init: &rest)
		(self approachVerbs: 4 1 4)
		(ropeTeller init: self 710 12 4 11)
	)
)

(instance pillar of Feature
	(properties
		noun 4
		nsLeft 23
		nsRight 107
		nsBottom 83
		x 65
		y 41
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 2
					init:
						25
						78
						42
						68
						48
						39
						39
						20
						19
						0
						110
						1
						88
						21
						82
						42
						94
						70
						105
						77
						83
						86
						45
						88
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance upperLedge of Feature
	(properties
		noun 5
		nsTop 66
		nsRight 173
		nsBottom 116
		x 86
		y 91
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init:
						0
						70
						12
						66
						28
						61
						13
						48
						20
						45
						33
						51
						43
						55
						42
						67
						23
						78
						44
						89
						84
						87
						107
						77
						95
						68
						93
						65
						124
						65
						123
						69
						137
						73
						156
						72
						164
						68
						164
						65
						173
						67
						172
						70
						160
						81
						139
						89
						129
						89
						109
						89
						122
						93
						118
						96
						110
						98
						77
						95
						26
						103
						15
						107
						55
						110
						59
						114
						23
						118
						0
						117
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (handleEvent event)
		(if (< (ego y?) 125)
			(= approachY (= approachX 0))
			(self approachVerbs: 0)
		else
			(= approachX 145)
			(= approachY 240)
			(self approachVerbs: 4 33)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(33
				(if (< (ego y?) 125)
					(if (> (ego x?) 190)
						(messager say: 10 6 31)
					else
						(curRoom setScript: (ScriptID 711 0))
					)
				else
					(curRoom setScript: (ScriptID 711 1))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lowerLedge of Feature
	(properties
		noun 6
		nsTop 122
		nsRight 158
		nsBottom 189
		sightAngle 180
		x 79
		y 155
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init:
						0
						185
						33
						161
						53
						135
						62
						125
						72
						126
						74
						137
						53
						158
						76
						161
						105
						157
						118
						148
						137
						152
						130
						159
						141
						162
						134
						165
						89
						174
						100
						179
						97
						180
						79
						183
						34
						189
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(33
				(if (< (ego y?) 125)
					(if (> (ego x?) 190)
						(messager say: 10 6 31)
					else
						(curRoom setScript: (ScriptID 711 0))
					)
				else
					(curRoom setScript: (ScriptID 711 1))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance largeCave of Feature
	(properties
		noun 7
		nsLeft 175
		nsTop 101
		nsRight 228
		nsBottom 133
		sightAngle 180
		x 201
		y 117
	)
)

(instance smallCave of Feature
	(properties
		noun 8
		nsLeft 251
		nsTop 117
		nsRight 283
		nsBottom 140
		sightAngle 180
		x 267
		y 128
	)
)

(instance backLedge of Feature
	(properties
		noun 9
		nsLeft 195
		nsTop 29
		nsRight 318
		nsBottom 80
		x 256
		y 54
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init:
						215
						39
						218
						42
						230
						43
						240
						38
						238
						32
						246
						33
						253
						36
						273
						33
						311
						38
						307
						57
						302
						66
						317
						69
						317
						79
						294
						75
						263
						65
						269
						61
						288
						60
						269
						48
						236
						50
						200
						50
						190
						47
						197
						40
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance leftExitF of Feature
	(properties
		noun 2
		nsRight 51
		nsBottom 52
		sightAngle 40
		approachX 28
		approachY 55
		x 25
		y 26
	)
	
	(method (handleEvent event)
		(if (and (< (ego x?) 190) (< (ego y?) 125))
			(self approachVerbs: 4)
		else
			(self approachVerbs: 0)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((not (< (ego y?) 125)) (messager say: 10 6 30))
					((> (ego x?) 190) (messager say: 10 6 29))
					(else
						(= local0 50)
						(= local1 28)
						(curRoom setScript: sExit 0 790)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rightExitF of Feature
	(properties
		noun 1
		nsLeft 271
		nsRight 319
		nsBottom 37
		approachX 292
		approachY 40
		x 295
		y 18
	)
	
	(method (handleEvent event)
		(if (and (> (ego x?) 190) (< (ego y?) 125))
			(self approachVerbs: 4)
		else
			(self approachVerbs: 0)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((not (< (ego y?) 125)) (messager say: 10 6 30))
					((< (ego x?) 190) (messager say: 10 6 29))
					(else
						(= local0 308)
						(= local1 21)
						(curRoom setScript: sExit 0 720)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sparklies of Prop
	(properties
		priority 182
		fixPriority 1
		view 17
		loop 4
		signal $4001
	)
	
	(method (init)
		(self setScaler: ego)
		(super init: &rest)
	)
	
	(method (doit)
		(= x (ego x?))
		(= y (ego y?))
		(super doit: &rest)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState &tmp leftExitFApproachX leftExitFApproachY)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(if (and (!= heroType 1) (< [egoStats 11] 100))
						(= [egoStats 11] 100)
					)
					(if (and (!= heroType 1) (not (ego has: 16)))
						(ego get: 16)
					)
					(= leftExitFApproachX (leftExitF approachX?))
					(= leftExitFApproachY (leftExitF approachY?))
				else
					(= leftExitFApproachX (rightExitF approachX?))
					(= leftExitFApproachY (rightExitF approachY?))
				)
				(ego
					setMotion: PolyPath leftExitFApproachX leftExitFApproachY self
				)
			)
			(1
				(if (and (== heroType 3) (not (Btst 391)))
					(Bset 391)
					(messager say: 10 6 33 0 self)
				else
					(= cycles 1)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath local0 local1 self)
			)
			(1 (curRoom newRoom: register))
		)
	)
)
