;;; Sierra Script 1.0 - (do not remove this comment)
(script# 610)
(include sci.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use ExitFeature)
(use MuseumRgn)
(use Scaler)
(use PolyPath)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use Reverse)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm610 0
	northDoor 1
	eastDoor 2
)

(local
	[local0 15] = [-1 198 202 209 216 234 169 165 160 148 129 79 64 46 23]
	[local15 15] = [-1 132 136 145 151 150 127 132 136 144 154 138 139 143 150]
	[local30 15] = [-1 82 90 112 118 130 82 90 97 112 130 94 98 111 125]
	[local45 15] = [-1 10 13 22 32 41 10 13 20 29 41 17 20 22 30]
	local60
	gEgoCel_2 =  -1
	gEgoCel =  -1
	local63
)
(instance rm610 of LBRoom
	(properties
		noun 29
		picture 610
		north 630
		east 640
		south 600
		vanishingX 165
		vanishingY 88
	)
	
	(method (init)
		(ego init: normalize: 831 setScale: Scaler 123 0 190 88)
		(self setRegions: 90)
		(switch prevRoomNum
			(north
				(ego edgeHit: 0 setHeading: 180)
				(northDoor caller: self)
			)
			(east
				(ego edgeHit: 0 setHeading: 270)
				(eastDoor caller: self)
			)
			(south
				(curRoom setScript: sEnterSouth self)
			)
			(620
				(self setScript: sClimbDownVat)
				(theIconBar enable: 7)
			)
			(666
				(Palette palSET_INTENSITY 0 255 100)
				(ego posn: 84 132)
				(self setScript: sEnterFromTunnel)
			)
			(26
				(self setScript: sClimbDownVat)
			)
			(else 
				(ego wearingGown: 1 posn: 160 160)
				(theGame handsOn:)
			)
		)
		(theMusic2 number: 610 loop: -1 flags: 1 play:)
		(super init:)
		(LoadMany 128 611 612)
		(northDoor init:)
		(if
			(or
				(and (== currentAct 3) (TriggerEvent 4104))
				(>= currentAct 4)
				(not (Btst 50))
			)
			(northDoor locked: 0)
		else
			(northDoor locked: 1)
		)
		(eastDoor init:)
		(if (and (Btst 18) (not (Btst 4)))
			(eastDoor locked: 1)
		)
		(oilJar
			setCel:
				(cond 
					((Btst 105) 3)
					((Btst 106) 2)
					((Btst 107) 1)
					(else 0)
				)
			approachVerbs: 1 4 8 25
			init:
		)
		(sink approachVerbs: 25 8 init: setOnMeCheck: 1 2)
		(desk approachVerbs: 25 8 init: setOnMeCheck: 1 4)
		(drain approachVerbs: 25 8 init:)
		(light init: setOnMeCheck: 1 8)
		(funnel init:)
		(longPipe init:)
		(shortPipe init:)
		(southExit init:)
		(if
			(and
				(Btst 20)
				(not (== prevRoomNum 620))
				(not (== prevRoomNum 26))
			)
			(sHeimlichMusic play:)
			(MuseumRgn loadPolyList:)
			((ScriptID 32 0)
				init:
				view: 814
				room: 610
				posn: 228 153
				setScale: 156
				loop: 1
			)
			(= local60 1)
		else
			(vat14
				approachVerbs: 4
				approachX: [local0 14]
				approachY:
					[local15 (vat13
						approachVerbs: 4
						approachX: [local0 13]
						approachY:
							[local15 (vat12
								approachVerbs: 4
								approachX: [local0 12]
								approachY:
									[local15 (vat11
										approachVerbs: 4
										approachX: [local0 11]
										approachY:
											[local15 (vat10
												approachX: [local0 10]
												approachY:
													[local15 (vat9
														approachVerbs: 4
														approachX: [local0 9]
														approachY:
															[local15 (vat8
																approachVerbs: 4
																approachX: [local0 8]
																approachY:
																	[local15 (vat7
																		approachVerbs: 4
																		approachX: [local0 7]
																		approachY:
																			[local15 (vat6
																				approachVerbs: 4
																				approachX: [local0 6]
																				approachY:
																					[local15 (vat5
																						approachVerbs: 4
																						approachX: [local0 5]
																						approachY:
																							[local15 (vat4
																								approachVerbs: 4
																								approachX: [local0 4]
																								approachY:
																									[local15 (vat3
																										approachVerbs: 4
																										approachX: [local0 3]
																										approachY:
																											[local15 (vat2
																												approachVerbs: 4
																												approachX: [local0 2]
																												approachY:
																													[local15 (vat1
																														approachVerbs: 4
																														approachX: [local0 1]
																														approachY: [local15 (Load rsSOUND 40)]
																														init:
																													)]
																												init:
																											)]
																										init:
																									)]
																								init:
																							)]
																						init:
																					)]
																				init:
																			)]
																		init:
																	)]
																init:
															)]
														init:
													)]
												approachVerbs: 4
												init:
											)]
										init:
									)]
								init:
							)]
						init:
					)]
				init:
			)
		)
	)
	
	(method (doit)
		(if
			(and
				(== (ego edgeHit?) 3)
				(& ((theIconBar at: 7) signal?) $0004)
			)
			(theIconBar enable: 7)
		)
		(if
			(and
				(not (curRoom script?))
				(IsObjectOnControl ego 64)
			)
			(curRoom setScript: sExitSouth)
		)
		(super doit:)
	)
	
	(method (dispose)
		(if (and (not (== newRoomNum 620)) (Btst 20))
			(Bclr 21)
			((ScriptID 90 13) setReal: (ScriptID 90 13) 30)
		)
		(if (!= newRoomNum 620) (theMusic2 fade:))
		(kickTimer dispose: delete:)
		(sHeimlichMusic fade:)
		(super dispose:)
	)
	
	(method (cue)
		(if (not (ego has: 11)) (theIconBar disable: 7))
		(cond 
			(local60 (curRoom setScript: sKickOut))
			((and (not (Btst 154)) (not (Btst 4)))
				(if
					(not
						(if (and (== currentAct 3) (TriggerEvent -20222))
							(not (TriggerEvent 4880))
						)
					)
					(eastDoor setScript: sWaterPrompt)
				)
			)
		)
		(if
			(and
				(== currentAct 4)
				(TriggerEvent 12290 1)
				(not (TriggerEvent 12290))
			)
			((ScriptID 90 15) seconds: 2)
			(= global111 15)
		)
	)
)

(instance sExitSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar enable: 7)
				(theGame handsOff:)
				(ego setHeading: 180 setMotion: MoveFwd 80 self)
			)
			(1
				(curRoom newRoom: (curRoom south?))
			)
		)
	)
)

(instance sEnterSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego x: 120 y: 270)
				(= cycles 1)
			)
			(1
				(ego setHeading: 0 setMotion: MoveFwd 100 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sWaterPrompt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(messager say: 32 0 0 0 self)
			)
			(2 (self dispose:))
		)
	)
)

(instance sEnterFromTunnel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 58 144 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sClimbUpVat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gEgoCel_2 -1)
				(= gEgoCel -1)
				(ego
					view: 612
					setLoop: register
					setCel: 0
					code: footstepCode
					scaleX: [local30 global129]
					scaleY:
						[local30 (if (> (ego x?) 180)
							(= register 1)
						else
							(= register 0)
						)]
					setScale:
					setCycle: End self
				)
			)
			(1
				(ego
					setLoop: (+ register 2)
					setCel: 0
					setCycle: Fwd
					setMotion: MoveTo (ego x?) (- (ego y?) [local45 global129]) self
				)
			)
			(2
				(ego setMotion: 0 code: 0)
				(curRoom newRoom: 620)
				(self dispose:)
			)
		)
	)
)

(instance footstepCode of Code
	(properties)
	
	(method (doit)
		(cond 
			((> (ego loop?) 1)
				(if
					(and
						(or (== (ego cel?) 1) (== (ego cel?) 4))
						(!= gEgoCel (ego cel?))
					)
					(sFX play:)
					(= gEgoCel (ego cel?))
				)
			)
			(
				(and
					(or
						(== (ego cel?) 4)
						(== (ego cel?) 7)
						(== (ego cel?) 11)
					)
					(!= gEgoCel_2 (ego cel?))
				)
				(sFX play:)
				(= gEgoCel_2 (ego cel?))
			)
		)
	)
)

(instance sClimbDownVat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 612
					setLoop: (+ register 2)
					setCel: (ego lastCel:)
					posn:
						[local0 global129]
						(-
							[local15 (if (> [local0 global129] 180)
								(= register 1)
							else
								(= register 0)
							)]
							[local45 (theGame handsOff:)]
						)
					code: footstepCode
					scaleX: [local30 global129]
					scaleY: [local30 global129]
					setScale:
					setCycle: Rev
					setMotion: MoveTo [local0 global129] [local15 global129] self
				)
			)
			(1
				(ego setLoop: register setCel: 13 setCycle: Beg self)
			)
			(2
				(ego
					normalize: 831
					code: 0
					loop: (if register 0 else 1)
					setScale: Scaler 123 0 190 88
				)
				(= cycles 1)
			)
			(3
				(if (Btst 21)
					(messager say: 27 0 2)
				else
					(messager say: 27 0 1)
				)
				(theGame handsOn:)
				(if (not (ego has: 11)) (theIconBar disable: 7))
				(self dispose:)
			)
		)
	)
)

(instance sKickOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 28 0 5)
				(= cycles 1)
			)
			(1
				(theGame handsOn:)
				(if (not (ego has: 11)) (theIconBar disable: 7))
				(kickTimer setReal: kickTimer 7)
				(self dispose:)
			)
		)
	)
)

(instance sKickOut2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar enable: 7)
				(theGame handsOff:)
				(messager say: 28 0 6)
				(= cycles 1)
			)
			(1
				(ego setMotion: PolyPath 108 252 self)
			)
			(2
				(curRoom newRoom: (curRoom south?))
				(self dispose:)
			)
		)
	)
)

(instance sDumpIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 611
					setLoop: 3
					setCel: 0
					setScale: Scaler 100 100 190 88
					setCycle: End self
				)
			)
			(1 (= ticks 20))
			(2 (ego setCycle: Beg self))
			(3
				(ego normalize: 831 loop: 0 setScale: Scaler 123 0 190 88)
				(= cycles 1)
			)
			(4
				(messager say: 3 25 14 0 self)
			)
			(5
				(theGame handsOn:)
				(= numSnakeOil 0)
				(= local63 1)
				(self dispose:)
			)
		)
	)
)

(instance northDoor of LbDoor
	(properties
		x 187
		y 120
		noun 1
		approachX 189
		approachY 125
		view 611
		priority 8
		signal $0010
		entranceTo 630
		moveToX 184
		moveToY 118
		enterType 0
		exitType 0
	)
	
	(method (createPoly)
		(super
			createPoly: 173 122 173 117 189 117 189 122 172 122
		)
	)
)

(instance eastDoor of LbDoor
	(properties
		x 306
		y 149
		noun 2
		approachX 311
		approachY 155
		view 611
		loop 1
		priority 10
		signal $0010
		entranceTo 640
		listenVerb 38
		moveToX 315
		moveToY 147
		enterType 0
		exitType 0
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: 4 38)
	)
	
	(method (listen)
		(if
			(or
				(Btst 154)
				(Btst 4)
				(and
					(== currentAct 3)
					(TriggerEvent -20222)
					(not (TriggerEvent 4880))
				)
			)
			(super listen:)
		else
			(messager say: 2 38 4)
			(theGame points: 1 154)
		)
	)
	
	(method (createPoly)
		(super createPoly: 311 150 318 152 318 156 310 153)
	)
)

(instance kickTimer of Timer
	(properties)
	
	(method (cue)
		(if (not (curRoom script?))
			(curRoom setScript: sKickOut2)
		)
	)
)

(instance oilJar of View
	(properties
		x 25
		y 165
		z 11
		noun 31
		approachX 68
		approachY 181
		view 611
		loop 2
		priority 15
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cond 
					((Btst 105) (messager say: 31 1 10))
					((Btst 106) (messager say: 31 1 9))
					((Btst 107) (messager say: 31 1 8))
					(else (messager say: 31 1 7))
				)
			)
			(8
				(if (ego has: 14)
					(if (Btst 105)
						(messager say: 31 8 10)
					else
						(messager say: 31 8 11)
					)
				else
					(messager say: 31 8 12)
				)
			)
			(4
				(if (Btst 105)
					(messager say: 31 4 10)
				else
					(messager say: 31 4 17)
				)
			)
			(25
				(if (< cel 3)
					(++ cel)
					(= numSnakeOil 4)
					(cond 
						((Btst 106) (Bclr 106) (Bset 105))
						((Btst 107) (Bclr 107) (Bset 106))
						(else (Bset 107))
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sink of Feature
	(properties
		x 180
		y 167
		noun 3
		nsTop 157
		nsLeft 162
		nsBottom 189
		nsRight 319
		sightAngle 40
		approachX 161
		approachY 184
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if local63
					(messager say: 3 1 13)
				else
					(messager say: 3 1)
				)
			)
			(8
				(if local63
					(messager say: 3 8 13)
				else
					(messager say: 3 8)
				)
			)
			(25
				(if numSnakeOil
					(curRoom setScript: sDumpIt)
				else
					(messager say: 3 25 15)
				)
			)
			(4
				(if local63
					(messager say: 3 4 13)
				else
					(messager say: 3 4)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vat1 of Feature
	(properties
		x 209
		y 102
		noun 4
		nsTop 75
		nsLeft 204
		nsBottom 130
		nsRight 208
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= global129 1)
				(curRoom setScript: sClimbUpVat)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vat2 of Feature
	(properties
		x 214
		y 101
		noun 5
		nsTop 71
		nsLeft 210
		nsBottom 132
		nsRight 216
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= global129 2)
				(curRoom setScript: sClimbUpVat)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vat3 of Feature
	(properties
		x 222
		y 102
		noun 6
		nsTop 66
		nsLeft 217
		nsBottom 138
		nsRight 225
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= global129 3)
				(curRoom setScript: sClimbUpVat)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vat4 of Feature
	(properties
		x 230
		y 100
		noun 7
		nsTop 56
		nsLeft 225
		nsBottom 145
		nsRight 236
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= global129 4)
				(curRoom setScript: sClimbUpVat)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vat5 of Feature
	(properties
		x 266
		y 100
		noun 8
		nsTop 44
		nsLeft 237
		nsBottom 156
		nsRight 295
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= global129 5)
				(curRoom setScript: sClimbUpVat)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vat6 of Feature
	(properties
		x 158
		y 102
		noun 9
		nsTop 78
		nsLeft 159
		nsBottom 126
		nsRight 163
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= global129 6)
				(curRoom setScript: sClimbUpVat)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vat7 of Feature
	(properties
		x 153
		y 102
		noun 10
		nsTop 74
		nsLeft 152
		nsBottom 130
		nsRight 159
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= global129 7)
				(curRoom setScript: sClimbUpVat)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vat8 of Feature
	(properties
		x 145
		y 100
		noun 11
		nsTop 66
		nsLeft 141
		nsBottom 135
		nsRight 152
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= global129 8)
				(curRoom setScript: sClimbUpVat)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vat9 of Feature
	(properties
		x 134
		y 99
		noun 12
		nsTop 57
		nsLeft 128
		nsBottom 142
		nsRight 140
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= global129 9)
				(curRoom setScript: sClimbUpVat)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vat10 of Feature
	(properties
		x 98
		y 99
		noun 13
		nsTop 44
		nsLeft 72
		nsBottom 154
		nsRight 125
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= global129 10)
				(curRoom setScript: sClimbUpVat)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vat11 of Feature
	(properties
		x 65
		y 100
		noun 14
		nsTop 67
		nsLeft 59
		nsBottom 133
		nsRight 71
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= global129 11)
				(curRoom setScript: sClimbUpVat)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vat12 of Feature
	(properties
		x 50
		y 100
		noun 15
		nsTop 64
		nsLeft 42
		nsBottom 136
		nsRight 58
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= global129 12)
				(curRoom setScript: sClimbUpVat)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vat13 of Feature
	(properties
		x 31
		y 100
		noun 16
		nsTop 59
		nsLeft 20
		nsBottom 141
		nsRight 42
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= global129 13)
				(curRoom setScript: sClimbUpVat)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vat14 of Feature
	(properties
		x 9
		y 92
		noun 17
		nsTop 51
		nsBottom 133
		nsRight 19
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= global129 14)
				(curRoom setScript: sClimbUpVat)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance desk of Feature
	(properties
		x 27
		y 161
		noun 18
		nsTop 134
		nsBottom 189
		nsRight 54
		sightAngle 40
		approachX 78
		approachY 183
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(25
				(if numSnakeOil
					(messager say: 18 25 14)
					(-- numSnakeOil)
				else
					(messager say: 18 25 15)
				)
			)
			(6 (messager say: 18 6 3))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance drain of Feature
	(properties
		x 110
		y 172
		noun 19
		nsTop 169
		nsLeft 98
		nsBottom 176
		nsRight 122
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(25
				(if numSnakeOil
					(messager say: 19 25 14)
					(-- numSnakeOil)
				else
					(messager say: 19 25 15)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance light of Feature
	(properties
		x 38
		y 195
		z 100
		noun 20
		nsTop 90
		nsLeft 29
		nsBottom 100
		nsRight 48
		sightAngle 40
	)
)

(instance funnel of Feature
	(properties
		x 208
		y 150
		noun 21
		nsTop 145
		nsLeft 201
		nsBottom 155
		nsRight 216
		sightAngle 40
	)
)

(instance longPipe of Feature
	(properties
		x 159
		y 31
		noun 22
		nsTop 29
		nsBottom 33
		nsRight 319
		sightAngle 40
	)
)

(instance shortPipe of Feature
	(properties
		x 263
		y 17
		noun 23
		nsTop 15
		nsLeft 208
		nsBottom 20
		nsRight 319
		sightAngle 40
	)
)

(instance southExit of ExitFeature
	(properties
		nsTop 185
		nsLeft 52
		nsBottom 189
		nsRight 159
		cursor 11
		exitDir 3
		noun 30
	)
)

(instance sFX of Sound
	(properties
		flags $0001
		number 613
	)
)

(instance sHeimlichMusic of Sound
	(properties
		flags $0001
		number 19
		loop -1
	)
)
