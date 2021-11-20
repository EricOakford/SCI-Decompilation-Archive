;;; Sierra Script 1.0 - (do not remove this comment)
(script# 770)
(include sci.sh)
(use Main)
(use GloryRm)
(use TargFeature)
(use TellerIcon)
(use EgoDead)
(use Array)
(use Scaler)
(use IconBar)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Cursor)
(use Sound)
(use Jump)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm770 0
)

(local
	[local0 3]
	local3
	local4 =  100
	local5
	local6
	newProp
	gTheObj_2CycleSpeed
	gTheObj_2Loop
	gTheObj_2Cel
	local11
	local12
	local13
	local14
	local15
	local16
	local17
	local18
	local19
	local20
)
(procedure (localproc_0126 &tmp temp0 temp1)
	(= temp0 0)
	(while (<= temp0 100)
		(Palette palSET_FLAG 0 255 temp0)
		(FrameOut)
		(= temp1 0)
		(while (< temp1 50)
			(++ temp1)
		)
		(++ temp0)
	)
)

(procedure (localproc_2c88 param1)
	(theGame handsOn:)
	(theIconBar disable: 0)
	(User canControl: 0)
	(param1 dispose:)
)

(procedure (localproc_2caf &tmp temp0)
	(Cursor loop: 8 cel: 10)
	(theGame
		setCursor: (IconBarCursor view: 905 loop: 8 cel: 10 yourself:)
	)
	((inventory at: 44) loop: 8 cel: 10 mainCel: 10 state: 1)
	((theIconBar at: 6) cursorCel: 10)
	((= temp0 (ScriptID 36 1)) cel: 10 show:)
	(UpdateScreenItem temp0)
)

(instance rm770 of GloryRm
	(properties
		noun 2
		picture 770
	)
	
	(method (init)
		(Bclr 6)
		(theGame handsOff:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						185
						96
						142
						106
						99
						105
						71
						89
						101
						77
						162
						68
						159
						65
						138
						56
						177
						56
						177
						53
						135
						53
						135
						57
						157
						67
						102
						74
						70
						87
						-300
						87
						-300
						-300
						619
						-300
						619
						489
						-300
						489
						-300
						108
						47
						118
						28
						137
						110
						170
						222
						170
						298
						124
						297
						104
						271
						96
						236
						90
						224
						97
						196
						93
						208
						80
						204
						75
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 287 118 211 160 116 160 56 137 87 118 150 118 202 102
					yourself:
				)
		)
		(= local3 50)
		(body1 init: approachVerbs: 4)
		(body2 init: approachVerbs: 4)
		(body3 init: approachVerbs: 4)
		(leftTHolder init: approachVerbs: 4 39 76)
		(if (== prevRoomNum 720)
			(rightTHolder init: approachVerbs: 4 39 76)
		else
			(rightTHolder nsTop: 50 init: approachVerbs: 4 39 76)
		)
		(leftEfx init: approachVerbs: 4 39 76)
		(rightEfx init: approachVerbs: 4 39 76)
		(altar init: approachVerbs: -1)
		(if (== prevRoomNum 720)
			(ego
				x: 153
				y: 55
				init:
				normalize:
				setScaler: Scaler 66 22 71 55
			)
			(torchEff init: setScaler: Scaler 66 22 71 55)
			(gate approachVerbs: 4 setLoop: 1)
			(gate
				setCel: (gate lastCel:)
				signal: (| (gate signal?) $0001)
				init:
			)
			(= local18 1)
			(= local19 0)
			(if ((inventory at: 44) state?) (self doTorch: 1))
			(self setScript: sFromNorth)
		else
			(theMusic number: 200 play:)
			(ego
				view: 40
				loop: 0
				cel: 5
				x: 51
				y: 140
				init:
				setScaler: Scaler 100 66 158 69
			)
			(torchEff init: setScaler: Scaler 100 66 158 69)
			(gate init: approachVerbs: 4)
			(= local18 0)
			(= local19 1)
			(self setScript: sEnterScr)
		)
		(super init: &rest)
	)
	
	(method (doit)
		(if (GameIsRestarting)
			(if (not (& (leftEfx signal?) $0008))
				(leftEfx signal: (| (leftEfx signal?) $0001) show:)
			)
			(if (not (& (rightEfx signal?) $0008))
				(rightEfx signal: (| (rightEfx signal?) $0001) show:)
			)
		)
		(super doit:)
		(if
		(and (not local18) (< (ego y?) 72) (< (ego x?) 180))
			(ego
				setPri: (ego priority?)
				setScaler: Scaler 66 22 71 55
			)
			(torchEff setScaler: Scaler 66 22 71 55)
			(= local18 1)
			(= local19 0)
		)
		(if
		(and (not local19) (>= (ego y?) 72) (< (ego x?) 180))
			(ego setPri: -1 setScaler: Scaler 100 66 158 71)
			(torchEff setScaler: Scaler 100 66 158 71)
			(= local18 0)
			(= local19 1)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(91
				(if local17
					(messager say: 0 0 42)
				else
					(self setScript: sDoJuggling)
				)
			)
			(83
				(if local17
					(messager say: 0 0 42)
				else
					(self setScript: sCastDazzle)
				)
			)
			(86
				(cond 
					(local17 (messager say: 0 0 42))
					(local5 (self setScript: sCastFlame))
					(else (curRoom setScript: (ScriptID 32) 0 86))
				)
			)
			(79
				(if local17
					(messager say: 0 0 42)
				else
					(curRoom setScript: sCastFrost)
				)
			)
			(81 (messager say: 2 81 4))
			(10
				(if local5
					(curRoom setScript: sJumpOut)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (doTorch param1)
		(if param1
			(torchEff signal: (| (torchEff signal?) $0001) show:)
			(Bset 373)
			(ego changeGait:)
		)
	)
)

(instance sJumpOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local17 0)
				(= gGTheObj_2CycleSpeed (ego cycleSpeed?))
				(cage signal: (| (cage signal?) $0001) setLoop: 0 1)
				(ego
					useSkill: 15
					view: 30
					setLoop: 3 1
					cel: 0
					code: 0
					cycleSpeed: 6
					moveSpeed: 6
					setScale:
					fixPriority: 1
					setCycle: CT 8 1
					setMotion: JumpTo 150 100 self
					show:
				)
			)
			(1 (ego setCycle: End self))
			(2
				(ego
					setSpeed: gGTheObj_2CycleSpeed
					solvePuzzle: 454 15
					normalize:
				)
				(altar init:)
				(leftTHolder init:)
				(egoTeller dispose:)
				(= local5 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sBreakBones of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(messager say: 8 36 17 0 self)
				else
					(messager say: 12 154 32 0 self)
				)
			)
			(1
				(cage signal: (| (cage signal?) $0001) dispose:)
				(ego view: 29 setLoop: 1 1 show:)
				(ego cel: (ego lastCel:) setCycle: Beg self)
			)
			(2
				(ego
					solvePuzzle: 454 15
					normalize:
					setMotion: PolyPath 200 100 self
				)
			)
			(3
				(= local5 0)
				(altar init:)
				(leftTHolder init:)
				(egoTeller dispose:)
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
			(0 (= cycles 1))
			(1
				(RemapColors 2 254 60)
				(RemapColors 1 253 112 175 62)
				(ego setMotion: PolyPath 153 57 self)
			)
			(2 (gate setCycle: Beg self))
			(3
				(gate setLoop: 0 setCel: 1)
				(= cycles 12)
			)
			(4
				(gate setCel: 0)
				(= cycles 6)
			)
			(5
				(gate signal: (& (gate signal?) $fffe))
				(= local11 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDoJuggling of Script
	(properties)
	
	(method (doit &tmp temp0)
		(switch state
			(3
				(= temp0 local4)
				(while (<= temp0 260)
					(= gameTime (+ tickOffset (GetTime)))
					(Palette palSET_FLAG 112 173 temp0)
					((newProp cycler?) doit:)
					(if
						(and
							(leftEfx cycler?)
							(not (& (leftEfx signal?) $0008))
						)
						((leftEfx cycler?) doit:)
						(UpdateScreenItem leftEfx)
					)
					(if
						(and
							(rightEfx cycler?)
							(not (& (rightEfx signal?) $0008))
						)
						((rightEfx cycler?) doit:)
						(UpdateScreenItem rightEfx)
					)
					(UpdateScreenItem newProp)
					(FrameOut)
					(= temp0 (+ temp0 5))
				)
				(self cue:)
			)
			(5
				(= temp0 260)
				(while (>= temp0 local4)
					(Palette palSET_FLAG 112 173 temp0)
					(FrameOut)
					(= temp0 (- temp0 8))
				)
				(self cue:)
			)
			(else  (super doit:))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (ego mover?) (ego setMotion: 0))
				(theMusic2 number: 934 setLoop: 1 1 play:)
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(ego view: 19 loop: 5 cel: 0 setCycle: End self)
				else
					(ego view: 15 loop: 0 setCycle: End self)
				)
			)
			(1
				((= newProp (Prop new:))
					view: 99
					loop: 0
					cel: 0
					x: (ego x?)
					y: (-
						(ego y?)
						(+ (/ (* 55 (ego scaleY?)) (ego maxScale?)) 20)
					)
					setPri: (+ (ego priority?) 1)
					init:
					setScale:
					scaleX: (ego scaleX?)
					scaleY: (ego scaleY?)
					setCycle: Fwd
				)
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(self cue:)
				else
					(ego setCycle: CT 4 -1 self)
				)
			)
			(2
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(self cue:)
				else
					(ego setCycle: CT 2 -1 self)
				)
			)
			(3 (Bclr 6))
			(4 (= seconds 2))
			(5 (newProp dispose:))
			(6 (ego setCycle: CT 0 -1 self))
			(7
				(theGame handsOn:)
				(ego normalize: 4)
				(self dispose:)
			)
		)
	)
)

(instance sExitNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 5 4 13 0 self)
			)
			(1
				(gate signal: (| (gate signal?) $0001) setCel: 1)
				(= cycles 12)
			)
			(2
				(gate setLoop: 1 setCel: 0 setCycle: End self)
			)
			(3
				(ego setMotion: MoveTo 153 51 self)
			)
			(4 (curRoom newRoom: 720))
		)
	)
)

(instance sGetTrapped of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (> ((User curEvent?) x?) (ego x?))
					(= local6 1)
				else
					(= local6 0)
				)
				(if (ego mover?) (ego setMotion: 0))
				(= gGTheObj_2CycleSpeed (ego cycleSpeed?))
				(if local6
					(ego
						view: 6
						setLoop: 4 1
						cel: 0
						x: (+ (ego x?) 20)
						cycleSpeed: 6
						setCycle: End self
					)
				else
					(ego
						view: 6
						setLoop: 5 1
						cel: 0
						x: (- (ego x?) 20)
						cycleSpeed: 6
						setCycle: End self
					)
				)
			)
			(1
				(messager say: 7 6 25 0 self)
			)
			(2
				(messager say: 9 6 26 0 self)
			)
			(3
				(if local6
					(ego setLoop: 6 1 cel: 0 setCycle: End self)
				else
					(ego setLoop: 7 1 cel: 0 setCycle: End self)
				)
			)
			(4
				(register heading: 1)
				(ego solvePuzzle: 402 2 get: 5)
				((inventory at: 0)
					amount: (+ 5 ((inventory at: 0) amount?))
				)
				(= kopeks (+ kopeks 13))
				(ego
					normalize: (if local6 4 else 5)
					cycleSpeed: gGTheObj_2CycleSpeed
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetItem of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gGTheObj_2CycleSpeed (ego cycleSpeed?))
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(ego
					setMotion: 0
					view: 4
					loop: (mod (ego loop?) 2)
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(1
				(switch register
					(body1
						(messager say: 9 4 26 0 self)
					)
					(body2
						(messager say: 10 4 30 0 self)
					)
					(body3
						(messager say: 11 4 31 0 self)
					)
				)
			)
			(2 (ego setCycle: Beg self))
			(3
				(ego cycleSpeed: gGTheObj_2CycleSpeed normalize:)
				(switch register
					(body1
						(ego solvePuzzle: 402 2 get: 5)
						((inventory at: 0)
							amount: (+ 5 ((inventory at: 0) amount?))
						)
						(= kopeks (+ kopeks 13))
					)
					(body2
						(ego get: 21)
						((inventory at: 0)
							amount: (+ 3 ((inventory at: 0) amount?))
						)
						(= kopeks (+ kopeks 13))
					)
					(body3
						((inventory at: 0)
							amount: (+ 5 ((inventory at: 0) amount?))
						)
						(= kopeks (+ kopeks 25))
					)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Palette palSET_FLAG 0 255 0)
				(= cycles 1)
			)
			(1
				(RemapColors 2 254 60)
				(RemapColors 1 253 112 175 62)
				(= gGTheObj_2CycleSpeed (ego cycleSpeed?))
				(localproc_0126)
				(= cycles 2)
			)
			(2
				(messager say: 7 6 37 0 self)
			)
			(3 (ego setCycle: Beg self))
			(4
				(ego cycleSpeed: gGTheObj_2CycleSpeed normalize: 4)
				(= cycles 2)
			)
			(5
				(messager say: 7 6 36 0 self)
			)
			(6
				(theGame handsOn:)
				(theIconBar curIcon: (theIconBar at: 1))
				(theGame
					setCursor: ((theIconBar getCursor:)
						view: 941
						loop: 0
						cel: 0
						yourself:
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance sDoCage of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 4 67 0 0 self)
			)
			(1
				(ego drop: 52 1 solvePuzzle: 453 6)
				(Bset 323)
				(= local5 1)
				(if (== heroType 2)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
					(curRoom
						addObstacle:
							((Polygon new:)
								type: 2
								init:
									142
									106
									99
									105
									70
									93
									93
									77
									161
									67
									159
									64
									138
									56
									177
									56
									177
									53
									135
									53
									135
									57
									156
									66
									93
									74
									67
									87
									-300
									87
									-300
									-300
									619
									-300
									619
									489
									-300
									489
									-300
									108
									47
									118
									28
									137
									110
									170
									222
									170
									298
									124
									297
									104
									271
									96
									236
									90
									224
									97
									195
									93
								yourself:
							)
							((Polygon new:)
								type: 2
								init: 287 118 211 160 116 160 56 137 87 118 150 118 202 102
								yourself:
							)
					)
					(cage
						cycleSpeed: 150
						setPri: (- (ego priority?) 5)
						setLoop: 4
						signal: (| (cage signal?) $0001)
						setCycle: End
						init:
					)
					(cage2
						signal: (| (cage signal?) $0001)
						setPri: (+ (ego priority?) 5)
						setCycle: End
						init:
					)
					(= local17 1)
				else
					(cage
						setPri: (+ (ego priority?) 5)
						signal: (| (cage signal?) $0001)
						setLoop: 3
						setCycle: CT 3 1
						init:
					)
					(ego changeGait: 0 setLooper: 0 setCycle: 0 hide:)
				)
				(egoTeller init: ego 770 12 154 8)
				(altar dispose:)
				(leftTHolder dispose:)
				(theGame handsOn:)
				(theIconBar disable: 0)
				(User canControl: 0)
				(self dispose:)
			)
		)
	)
)

(instance sLightTorch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(ego
					view: 31
					setLoop: 0
					cel: 0
					x: (+ (ego x?) 4)
					setCycle: End self
				)
			)
			(2
				(register
					signal: (| (register signal?) $0001)
					show:
					setCycle: End self
				)
			)
			(3
				(register cue:)
				(ego solvePuzzle: 401 6 setCycle: Beg self)
			)
			(4
				(switch register
					(leftEfx (Bset 303))
					(rightEfx (Bset 304))
				)
				(ego x: (- (ego x?) 4) normalize: 6)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFlameTorch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(register
					signal: (| (register signal?) $0001)
					show:
					setCycle: End self
				)
			)
			(2
				(register cue:)
				(ego solvePuzzle: 401 6)
				(switch register
					(leftEfx (Bset 303))
					(rightEfx (Bset 304))
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTakeTorch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 31 setLoop: 0 cel: 0 setCycle: End self)
			)
			(1
				(messager say: 1 4 5 0 self)
				(if (rightEfx cycler?)
					(register hide:)
					(= local6 1)
					((inventory at: 44) cel: 10 mainCel: 10 state: 1)
					(curRoom doTorch: 1)
				else
					(= local6 0)
				)
				(ego get: 44)
			)
			(2 (ego setCycle: Beg self))
			(3
				(Bset 302)
				(ego normalize: 6)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCastDazzle of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (ego mover?) (ego setMotion: 0))
				(= temp0 (IntArray with: 2 3 0 3 0 1 2 3))
				(= temp1 (IntArray with: 0 0 0 1 0 0 2 3))
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(= gTheObj_2Loop (ego loop?))
				(soundFX number: 940 play:)
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(= gTheObj_2Cel (ego cel?))
					(ego view: 19 loop: (temp1 at: gTheObj_2Loop))
				else
					(ego view: 15 loop: (temp0 at: gTheObj_2Loop))
				)
				(ego
					setMotion: 0
					cycleSpeed: defaultCycles
					setCycle: End self
				)
				(temp0 dispose:)
				(temp1 dispose:)
			)
			(1 (ego setCycle: Beg self))
			(2
				(ego cycleSpeed: gTheObj_2CycleSpeed)
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(ego view: 20 loop: gTheObj_2Loop cel: gTheObj_2Cel)
				else
					(ego normalize: gTheObj_2Loop)
				)
				(Palette palSET_FLAG 0 255 200)
				(FrameOut)
				(= cycles 6)
			)
			(3
				(Palette palSET_FLAG 0 255 local4)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCastForce of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (if (>= local14 1) (>= local13 1)))
					(messager say: 8 88 18)
					(localproc_2c88 self)
				else
					(messager say: 8 88 21 0 self)
					(altar init:)
					(leftTHolder init:)
					(egoTeller dispose:)
					(= local5 0)
					(cage dispose:)
					(ego view: 29 setLoop: 1 1)
					(ego setCel: (ego lastCel:) show:)
				)
			)
			(1 (ego setCycle: Beg self))
			(2
				(ego solvePuzzle: 454 15 normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCastFlame of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local15
					(messager say: 8 86 19)
					(= local15 1)
					(= local16 0)
					(localproc_2c88 self)
				else
					(= ticks 1)
				)
			)
			(1
				(cage
					setLoop: 1
					cel: 0
					signal: (| (cage signal?) $0001)
					setCycle: End self
				)
			)
			(2 (cage setCycle: Beg self))
			(3
				(cage
					setLoop: 3
					cel: 3
					signal: (& (cage signal?) $fffe)
				)
				(if local14
					(if local16
						(if (>= local13 2)
							(messager say: 8 86 23 0 self)
							(= local5 0)
							(altar init:)
							(leftTHolder init:)
							(egoTeller dispose:)
							(cage dispose:)
							(ego view: 29 setLoop: 1 1)
							(ego setCel: (ego lastCel:) show:)
						else
							(messager say: 8 86 21)
							(++ local14)
							(= local15 1)
							(= local16 0)
							(localproc_2c88 self)
						)
					)
				else
					(++ local14)
					(messager say: 8 86 18 0)
					(= local15 1)
					(= local16 0)
					(localproc_2c88 self)
				)
			)
			(4 (ego setCycle: Beg self))
			(5
				(ego solvePuzzle: 454 15 normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCastFrost of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local16
					(messager say: 8 79 22 0 self)
					(= local15 0)
					(= local16 1)
					(localproc_2c88 self)
				else
					(= ticks 1)
				)
			)
			(1
				(cage
					setLoop: 2
					cel: 0
					signal: (| (cage signal?) $0001)
					setCycle: End self
				)
			)
			(2 (cage setCycle: Beg self))
			(3
				(cage
					setLoop: 3
					cel: 3
					signal: (& (cage signal?) $fffe)
				)
				(if local13
					(if local15
						(if (>= local14 2)
							(messager say: 8 79 20 0 self)
							(altar init:)
							(leftTHolder init:)
							(egoTeller dispose:)
							(= local5 0)
							(cage dispose:)
							(ego view: 29 setLoop: 1 1)
							(ego setCel: (ego lastCel:) show:)
						else
							(messager say: 8 79 21)
							(++ local13)
							(= local15 0)
							(= local16 1)
							(localproc_2c88 self)
						)
					)
				else
					(++ local13)
					(messager say: 8 79 18)
					(= local15 0)
					(= local16 1)
					(localproc_2c88 self)
				)
			)
			(4 (ego setCycle: Beg self))
			(5
				(ego solvePuzzle: 454 15 normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sPlaceTorch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(ego
					view: 31
					setLoop: 0
					cel: 0
					x: (+ (ego x?) 4)
					setCycle: End self
				)
			)
			(2 (messager say: 7 6 4 0 self))
			(3
				(Bclr 302)
				(if (not (& (torchEff signal?) $0008))
					(torchEff signal: (& (torchEff signal?) $fffe) hide:)
					(Bclr 373)
					(Bset 304)
					(rightEfx show: cue:)
				)
				(= cycles 2)
			)
			(4
				(ego drop: 44 setCycle: Beg self)
			)
			(5
				(theGame handsOn:)
				(ego normalize: 6)
				(self dispose:)
			)
		)
	)
)

(instance torchEff of Prop
	(properties
		view 775
		signal $4001
	)
	
	(method (init)
		(super init:)
		(self setCycle: RandCycle)
		(if (ego has: 44)
			(self signal: (| (self signal?) $0001) show:)
			(Bset 373)
			(ego changeGait:)
		else
			(self signal: (& (self signal?) $fffe) hide:)
			(Bclr 373)
		)
	)
	
	(method (doit)
		(= x (ego x?))
		(= y (ego y?))
		(= z (+ (ego z?) 1))
		(super doit: &rest)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance cage of Prop
	(properties
		x 241
		y 62
		view 771
		loop 3
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if local5
					((ego actions?) doVerb: theVerb)
				else
					(super doVerb: theVerb)
				)
			)
			(86
				(if local17
					(messager say: 0 0 42)
				else
					(curRoom setScript: sCastFlame)
				)
			)
			(79
				(if local17
					(messager say: 0 0 42)
				else
					(curRoom setScript: sCastFrost)
				)
			)
			(88
				(if local17
					(messager say: 0 0 42)
				else
					(curRoom setScript: sCastForce)
				)
			)
			(36
				(if
					(and
						(== heroType 3)
						(== ((inventory at: 19) state?) 3)
					)
					(curRoom setScript: sBreakBones 0 1)
				else
					(super doVerb: theVerb)
				)
			)
			(10 (curRoom doVerb: theVerb))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance cage2 of Prop
	(properties
		x 241
		y 62
		view 771
		loop 5
		signal $4000
		cycleSpeed 150
	)
	
	(method (doit)
		(if (and local17 (== cel 3)) (EgoDead 24))
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(cage doVerb: theVerb)
	)
)

(instance gate of Prop
	(properties
		noun 5
		approachX 153
		approachY 50
		x 153
		y 46
		view 772
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (and (not (== theVerb 10)) local5)
			(messager say: 7 6 47)
		else
			(switch theVerb
				(4
					(cond 
						(
							(or
								(not (ego has: 44))
								(not ((inventory at: 44) state?))
							)
							(messager say: 5 4 12)
						)
						((and local12 (not (Btst 323))) (messager say: 5 4 48))
						(
							(and
								(ego has: 44)
								((inventory at: 44) state?)
								(ego has: 5)
							)
							(curRoom setScript: sExitNorth)
						)
						((not (ego has: 5)) (messager say: 5 4 26))
					)
				)
				(10 (curRoom doVerb: theVerb))
				(20 (self doVerb: 4))
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance leftEfx of Prop
	(properties
		approachX 197
		approachY 89
		x 214
		y 57
		priority 74
		fixPriority 1
		view 770
		signal $4000
		detailLevel 3
	)
	
	(method (init)
		(super init:)
		(if (Btst 303)
			(self cue: signal: (| (self signal?) $0001))
		else
			(self signal: (& (self signal?) $fffe) hide:)
		)
	)
	
	(method (doVerb)
		(leftTHolder doVerb: &rest)
	)
	
	(method (cue)
		(self setLoop: 1 1 setCycle: RandCycle)
	)
)

(instance rightEfx of Prop
	(properties
		approachX 230
		approachY 93
		x 250
		y 69
		priority 88
		fixPriority 1
		view 770
		loop 2
		signal $5000
		detailLevel 3
	)
	
	(method (init)
		(super init:)
		(cond 
			((Btst 302) (self hide:))
			((Btst 304) (self cue: signal: (| (self signal?) $0001)))
			(else (self signal: (& (self signal?) $fffe) hide:))
		)
	)
	
	(method (doVerb)
		(rightTHolder doVerb: &rest)
	)
	
	(method (cue)
		(self setLoop: 3 1 setCycle: RandCycle)
	)
)

(instance body1 of Feature
	(properties
		noun 9
		nsLeft 75
		nsTop 138
		nsRight 122
		nsBottom 158
		x 98
		y 148
	)
	
	(method (init)
		(super init:)
		(if (!= prevRoomNum 720) (theDoits add: self))
	)
	
	(method (doit)
		(if
			(and
				(not heading)
				(self onMe: (ego x?) (ego y?))
				(not (curRoom script?))
				(not local20)
			)
			(curRoom setScript: sGetTrapped 0 self)
		)
	)
	
	(method (dispose)
		(if (!= prevRoomNum 720) (theDoits delete: self))
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (== (event message?) JOY_DOWNRIGHT)
			(if (self onMe: (event x?) (event y?))
				(= local20 1)
			else
				(= local20 0)
			)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(if (and (not (== theVerb 10)) local5)
			(messager say: 7 6 47)
		else
			(switch theVerb
				(4
					(if (!= prevRoomNum 720)
						(if heading
							(messager say: 9 4 27)
						else
							(= heading 1)
							(curRoom setScript: sGetItem 0 self)
						)
					else
						(super doVerb: theVerb)
					)
				)
				(10 (curRoom doVerb: theVerb))
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance body2 of Feature
	(properties
		noun 10
		nsLeft 131
		nsTop 102
		nsRight 161
		nsBottom 116
		approachX 150
		approachY 109
		x 146
		y 109
	)
	
	(method (init)
		(super init:)
		(if (!= prevRoomNum 720) (theDoits add: self))
	)
	
	(method (dispose)
		(if (!= prevRoomNum 720) (theDoits delete: self))
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (and (not (== theVerb 10)) local5)
			(messager say: 7 6 47)
		else
			(switch theVerb
				(4
					(if (!= prevRoomNum 720)
						(if heading
							(messager say: 10 4 28)
						else
							(= heading 1)
							(curRoom setScript: sGetItem 0 self)
						)
					else
						(super doVerb: theVerb)
					)
				)
				(10 (curRoom doVerb: theVerb))
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance body3 of Feature
	(properties
		noun 11
		nsLeft 153
		nsTop 91
		nsRight 179
		nsBottom 106
		approachX 170
		approachY 98
		x 166
		y 98
	)
	
	(method (init)
		(super init:)
		(if (!= prevRoomNum 720) (theDoits add: self))
	)
	
	(method (dispose)
		(if (!= prevRoomNum 720) (theDoits delete: self))
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (and (not (== theVerb 10)) local5)
			(messager say: 7 6 47)
		else
			(switch theVerb
				(4
					(if (!= prevRoomNum 720)
						(if heading
							(messager say: 11 4 29)
						else
							(= heading 1)
							(curRoom setScript: sGetItem 0 self)
						)
					else
						(super doVerb: theVerb)
					)
				)
				(10 (curRoom doVerb: theVerb))
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance leftTHolder of TargFeature
	(properties
		noun 3
		nsLeft 207
		nsTop 51
		nsRight 225
		nsBottom 70
		approachX 201
		approachY 82
		x 213
		y 109
		z 50
	)
	
	(method (dispose)
		(if (and global392 (global392 contains: self))
			(global392 delete: self)
		)
		(if (and global392 (not (global392 size:)))
			(global392 dispose:)
			(= global392 0)
		)
		(features delete: self)
		(DisposeClone self)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (and (not (== theVerb 10)) local5)
			(messager say: 7 6 47)
		else
			(switch theVerb
				(1
					(if (Btst 303)
						(messager say: 1 1 2)
					else
						(messager say: 3 1 1)
					)
				)
				(76
					(cond 
						((and (== heroType 2) (Btst 323)) (messager say: 3 76 45))
						(((inventory at: 44) state?)
							(if (Btst 303)
								(messager say: 3 76 8)
							else
								(messager say: 3 76 9)
								(curRoom setScript: sLightTorch 0 leftEfx)
							)
						)
						((Btst 303)
							(localproc_2caf)
							(curRoom doTorch: 1)
							(messager say: 3 76 10)
						)
						(else (messager say: 3 76 11))
					)
				)
				(39
					(cond 
						((and (== heroType 2) (Btst 323)) (messager say: 3 76 45))
						((ego has: 5)
							(if (!= (leftEfx loop?) 1)
								(messager say: 1 39 1)
								(curRoom setScript: sLightTorch 0 leftEfx)
							else
								(messager say: 1 39 2)
							)
						)
						(else (messager say: 1 39 7))
					)
				)
				(86
					(cond 
						(local17 (messager say: 0 0 42))
						(local5 (curRoom setScript: sCastFlame))
						(else (curRoom setScript: (ScriptID 32) 0 86))
					)
				)
				(10 (curRoom doVerb: theVerb))
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (getHurt)
		(global392 delete: self)
		(leftEfx setScript: sFlameTorch 0 leftEfx)
	)
)

(instance rightTHolder of TargFeature
	(properties
		noun 1
		nsLeft 241
		nsTop 64
		nsRight 265
		nsBottom 90
		approachX 230
		approachY 93
		x 249
		y 108
		z 40
	)
	
	(method (dispose)
		(if (and global392 (global392 contains: self))
			(global392 delete: self)
		)
		(if (and global392 (not (global392 size:)))
			(global392 dispose:)
			(= global392 0)
		)
		(features delete: self)
		(DisposeClone self)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (and (not (== theVerb 10)) local5)
			(messager say: 7 6 47)
		else
			(switch theVerb
				(1
					(cond 
						((and (not (Btst 302)) (not (Btst 304))) (messager say: 1 1 1))
						((and (not (Btst 302)) (Btst 304)) (messager say: 1 1 2))
						(else (super doVerb: theVerb))
					)
				)
				(4
					(if (not (Btst 302))
						(if (ego has: 44)
							(messager say: 3 4 40)
						else
							(curRoom setScript: sTakeTorch 0 rightEfx)
						)
					else
						(messager say: 1 4 43)
					)
				)
				(76
					(cond 
						((Btst 302) (curRoom setScript: sPlaceTorch 0 rightEfx))
						(((inventory at: 44) state?)
							(if (Btst 304)
								(messager say: 3 76 8)
							else
								(messager say: 3 76 9)
								(curRoom setScript: sLightTorch 0 rightEfx)
							)
						)
						((Btst 304)
							(localproc_2caf)
							(curRoom doTorch: 1)
							(messager say: 3 76 10)
						)
						(else (messager say: 3 76 11))
					)
				)
				(39
					(if (not (Btst 302))
						(if (ego has: 5)
							(if (!= (rightEfx loop?) 3)
								(messager say: 1 39 1)
								(curRoom setScript: sLightTorch 0 rightEfx)
							else
								(messager say: 1 39 2)
							)
						else
							(messager say: 1 39 7)
						)
					else
						(messager say: 1 4 43)
					)
				)
				(10 (curRoom doVerb: theVerb))
				(86
					(cond 
						(local17 (messager say: 0 0 42))
						(local5 (curRoom setScript: sCastFlame))
						(else (curRoom setScript: (ScriptID 32) 0 86))
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (getHurt)
		(if (Btst 302)
			(messager say: 1 86 3)
		else
			(global392 delete: self)
			(rightEfx setScript: sFlameTorch 0 rightEfx)
		)
	)
)

(instance altar of Feature
	(properties
		noun 4
		nsLeft 223
		nsTop 5
		nsRight 260
		nsBottom 65
		sightAngle 40
		approachX 222
		approachY 77
		x 247
		y 65
	)
	
	(method (doVerb theVerb)
		(if (and (not (== theVerb 10)) local5)
			(messager say: 7 6 47)
		else
			(switch theVerb
				(43
					(if (Btst 302)
						(messager say: 7 6 44)
					else
						(= local12 1)
						(ego drop: 25)
						(messager say: 7 6 41)
					)
				)
				(67
					(cond 
						((or (not (Btst 303)) (not (Btst 304))) (messager say: 7 6 46))
						((Btst 302) (messager say: 7 6 44))
						((not local12) (messager say: 7 6 16))
						(else (curRoom setScript: sDoCage))
					)
				)
				(86 (messager say: 4 86 0))
				(88 (messager say: 4 88 0))
				(79 (messager say: 4 79 0))
				(93 (messager say: 4 93 0))
				(10 (curRoom doVerb: theVerb))
				(1
					(if (Btst 323)
						(messager say: 4 1 6)
					else
						(messager say: 4 1 5)
					)
				)
				(76
					(rightTHolder doVerb: theVerb)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance egoTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(switch iconValue
			(32
				(curRoom setScript: sBreakBones 0 0)
				(self clean:)
			)
			(35
				(curRoom setScript: sJumpOut)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				32
				(if (== heroType 0) else (== heroType 3))
				33
				(if (== heroType 1) else (== heroType 2))
				34
				[egoStats 11]
				35
				(== heroType 2)
		)
	)
)

(instance soundFX of Sound
	(properties)
)
