;;; Sierra Script 1.0 - (do not remove this comment)
(script# 380)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use Array)
(use Scaler)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm380 0
)

(local
	local0
	newActor
	newActor_2
	newActor_3
	newActor_4
	local5
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
)
(instance rm380 of GloryRm
	(properties
		noun 20
		picture 380
		south 280
	)
	
	(method (init)
		(= local7
			(IntArray
				with:
					3
					3
					240
					137
					3
					3
					226
					86
					3
					4
					203
					137
					3
					0
					203
					137
					3
					1
					203
					137
					3
					2
					203
					137
					3
					3
					167
					44
					3
					3
					162
					178
					3
					4
					162
					178
					3
					0
					162
					178
					3
					1
					162
					178
					3
					2
					162
					178
					3
					3
					94
					67
					3
					3
					56
					177
					3
					4
					56
					177
					3
					0
					56
					177
					3
					1
					56
					177
					3
					2
					56
					177
					3
					3
					14
					75
					3
					3
					-5
					76
					-32768
			)
		)
		(= local8
			(IntArray
				with:
					3
					3
					245
					140
					3
					3
					226
					84
					3
					3
					200
					134
					3
					4
					200
					134
					3
					0
					200
					134
					3
					1
					200
					134
					3
					2
					200
					134
					3
					3
					232
					178
					3
					4
					232
					178
					3
					0
					232
					178
					3
					1
					232
					178
					3
					2
					232
					178
					3
					3
					265
					174
					3
					4
					265
					174
					3
					0
					265
					174
					3
					1
					265
					174
					3
					2
					265
					174
					3
					3
					293
					144
					3
					3
					325
					144
					-32768
			)
		)
		(= local9
			(IntArray
				with:
					3
					3
					250
					135
					3
					3
					228
					85
					3
					3
					210
					139
					3
					4
					210
					139
					3
					0
					210
					139
					3
					1
					210
					139
					3
					2
					210
					139
					3
					3
					167
					44
					3
					3
					162
					178
					3
					4
					162
					178
					3
					0
					162
					178
					3
					1
					162
					178
					3
					2
					162
					178
					3
					3
					94
					67
					3
					3
					96
					135
					3
					4
					96
					135
					3
					0
					96
					135
					3
					1
					96
					135
					3
					2
					96
					135
					3
					3
					88
					121
					3
					3
					-5
					122
					-32768
			)
		)
		(= local10
			(IntArray
				with:
					3
					3
					250
					138
					3
					3
					222
					83
					3
					3
					190
					133
					3
					4
					190
					133
					3
					0
					190
					133
					3
					1
					190
					133
					3
					2
					190
					133
					3
					3
					167
					44
					3
					3
					164
					141
					3
					4
					164
					141
					3
					0
					164
					141
					3
					1
					164
					141
					3
					2
					164
					141
					3
					3
					206
					49
					3
					3
					217
					84
					3
					3
					330
					85
					-32768
			)
		)
		(= local11
			(IntArray
				with:
					3
					3
					-5
					76
					3
					3
					14
					75
					3
					3
					85
					173
					3
					4
					85
					173
					3
					0
					85
					173
					3
					1
					85
					173
					3
					2
					85
					173
					3
					3
					160
					147
					3
					3
					220
					174
					3
					4
					220
					174
					3
					0
					220
					174
					3
					1
					220
					174
					3
					2
					220
					174
					3
					3
					294
					145
					3
					3
					330
					145
					-32768
			)
		)
		(= local12
			(IntArray
				with:
					3
					3
					294
					145
					3
					3
					220
					174
					3
					4
					220
					174
					3
					0
					220
					174
					3
					1
					220
					174
					3
					2
					220
					174
					3
					3
					160
					147
					3
					3
					85
					173
					3
					4
					85
					173
					3
					0
					85
					173
					3
					1
					85
					173
					3
					2
					85
					173
					3
					3
					14
					75
					3
					3
					-5
					76
					-32768
			)
		)
		(= local13
			(IntArray
				with:
					3
					3
					87
					121
					3
					3
					101
					135
					3
					4
					101
					135
					3
					0
					101
					135
					3
					1
					101
					135
					3
					2
					101
					135
					3
					3
					141
					119
					3
					3
					186
					137
					3
					4
					186
					137
					3
					0
					186
					137
					3
					1
					186
					137
					3
					2
					186
					137
					3
					3
					213
					85
					3
					3
					330
					85
					-32768
			)
		)
		(= local14
			(IntArray
				with:
					3
					3
					216
					85
					3
					3
					186
					137
					3
					4
					186
					137
					3
					0
					186
					137
					3
					1
					186
					137
					3
					2
					186
					137
					3
					3
					141
					119
					3
					3
					101
					135
					3
					4
					101
					135
					3
					0
					101
					135
					3
					1
					101
					135
					3
					2
					101
					135
					3
					3
					87
					121
					3
					3
					-5
					121
					-32768
			)
		)
		(= local15
			(IntArray
				with:
					3
					3
					276
					148
					3
					3
					226
					86
					3
					4
					203
					137
					3
					0
					203
					137
					3
					1
					203
					137
					3
					2
					203
					137
					3
					3
					163
					92
					3
					3
					144
					180
					3
					4
					144
					180
					3
					0
					144
					180
					3
					1
					144
					180
					3
					2
					144
					180
					3
					3
					121
					90
					3
					3
					96
					167
					3
					4
					96
					167
					3
					0
					96
					167
					3
					1
					96
					167
					3
					2
					96
					167
					-32768
			)
		)
		(theMusic number: 380 setLoop: -1 play:)
		(self
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
						294
						189
						200
						130
						185
						109
						179
						109
						170
						130
						133
						130
						130
						109
						123
						109
						107
						131
						11
						189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 88 148 151 148 151 176 81 176
					yourself:
				)
		)
		(Load rsSCRIPT -594)
		(trapMachine init: approachVerbs: 4 19)
		(hole1 init:)
		(hole2 init:)
		(hole3 init:)
		(hole4 init:)
		(mybust init:)
		(painting init:)
		(leftDoor approachVerbs: 4 29 init:)
		(rightDoor approachVerbs: 4 29 init:)
		(railing init:)
		(stairs1 init:)
		(stairs2 init:)
		(endDoor approachVerbs: 4 29 init:)
		(ball1 init:)
		(ball2 init:)
		(pillar1 init:)
		(pillar2 init:)
		(wheel init:)
		(lightBulb init:)
		(catcher
			cel: (if (Btst 109) (catcher lastCel:) else 0)
			init:
		)
		(leftDoorTeller init: leftDoor 380 5 121)
		(rightDoorTeller init: rightDoor 380 5 120)
		(endDoorTeller init: endDoor 380 5 122)
		(switch prevRoomNum
			(370
				(ego
					x: 130
					y: 109
					init:
					setScaler: Scaler 135 71 173 108
					normalize: 2
				)
				(self setScript: from370)
			)
			(280
				(ego
					x: 165
					y: 210
					setScaler: Scaler 135 71 173 108
					normalize: 3
					init:
				)
				(self setScript: enterRoom)
			)
			(else 
				(ego
					x: 165
					y: 170
					setScaler: Scaler 135 71 173 108
					normalize:
					init:
				)
				(theGame handsOn:)
			)
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(DisposeScript 80)
		(DisposeScript 81)
		(DisposeScript 83)
		(DisposeScript -580)
		(DisposeScript -594)
		(local7 dispose:)
		(local8 dispose:)
		(local9 dispose:)
		(local10 dispose:)
		(local11 dispose:)
		(local12 dispose:)
		(local13 dispose:)
		(local14 dispose:)
		(local15 dispose:)
		(= script 0)
		(LoadMany 0 80 81 83 -580 -594)
		(super dispose:)
	)
)

(instance from370 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(ego setMotion: MoveTo 130 109 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 5)
			)
			(1
				(ego solvePuzzle: 405 6 setMotion: PolyPath 165 170 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance openLeftDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bclr 51)
				(= cycles 5)
			)
			(1
				(ego view: 31 setLoop: 1 cel: 0 setCycle: End self)
			)
			(2 (ego setCycle: Beg self))
			(3
				(ego normalize: 7)
				(leftDoor setCycle: End self)
			)
			(4
				(if (and (Btst 109) (ego has: 58))
					(messager say: 3 4 28 0 self)
				else
					(= ticks 1)
				)
			)
			(5
				(if (Btst 109)
					((ScriptID 81 0) init: show: dispose:)
					(DisposeScript 81)
					(theGame handsOn:)
					(self cue:)
				else
					(messager say: 3 4 2 0 self)
				)
			)
			(6
				(leftDoor setCycle: Beg self)
			)
			(7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance openRightDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bclr 51)
				(if (rightDoor script?)
					(rightDoor setScript: 0)
					(newActor hide:)
					(newActor_2 hide:)
					(newActor_3 hide:)
					(newActor_4 hide:)
				)
				(= cycles 5)
			)
			(1
				(ego view: 31 setLoop: 0 cel: 0 setCycle: End self)
			)
			(2 (ego setCycle: Beg self))
			(3
				(ego normalize: 6)
				(rightDoor setCycle: End self)
			)
			(4
				(if local5
					(newActor posn: 240 137 show: setCycle: MCyc local7 self)
					(newActor_2 posn: 255 130 show: setCycle: MCyc local8)
					(newActor_3 posn: 254 140 show: setCycle: MCyc local9)
					(newActor_4 posn: 248 135 show: setCycle: MCyc local10)
				else
					((= newActor (Actor new:))
						view: 380
						setLoop: 3
						x: 255
						y: 130
						cycleSpeed: 8
						moveSpeed: 8
						noun: 16
						ignoreActors: 1
						init:
						setCycle: MCyc local7 self
					)
					((= newActor_2 (Actor new:))
						view: 380
						setLoop: 3
						x: 240
						y: 137
						cycleSpeed: 8
						moveSpeed: 8
						ignoreActors: 1
						init:
						noun: 16
						setCycle: MCyc local8
					)
					((= newActor_3 (Actor new:))
						view: 380
						setLoop: 3
						x: 248
						y: 135
						cycleSpeed: 8
						moveSpeed: 8
						noun: 16
						ignoreActors: 1
						init:
						setCycle: MCyc local9
					)
					((= newActor_4 (Actor new:))
						view: 380
						setLoop: 3
						x: 254
						y: 140
						cycleSpeed: 8
						moveSpeed: 8
						noun: 16
						ignoreActors: 1
						init:
						setCycle: MCyc local10
					)
					(= local5 1)
				)
				(= ticks 12)
			)
			(5
				(ego
					view: 6
					setLoop: 1
					x: (+ (ego x?) 7)
					y: (- (ego y?) 3)
					setCycle: End self
				)
			)
			(6)
			(7 (= seconds 4))
			(8
				(messager say: 6 6 25 0 self)
			)
			(9
				(ego
					setLoop: 3
					x: (- (ego x?) 2)
					y: (+ (ego y?) 1)
					cel: 0
					setCycle: End self
				)
			)
			(10
				(ego
					x: (- (ego x?) 21)
					y: (+ (ego y?) 5)
					normalize:
					loop: 5
				)
				(rightDoor setCycle: Beg self)
			)
			(11
				(theGame handsOn:)
				(newActor hide:)
				(newActor_2 hide:)
				(newActor_3 hide:)
				(newActor_4 hide:)
				(if (not (Btst 109))
					(rightDoor setScript: sDoAnAntwerp)
				)
				(self dispose:)
			)
		)
	)
)

(instance sDoAnAntwerp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 10)))
			(1
				(switch (= local6 (Random 1 4))
					(1
						(if
						(and (& local0 $0001) (not (Btst 109)) (Btst 27))
							(newActor show: setCycle: MCyc local15 self)
						else
							(newActor x: -5 y: 76 show: setCycle: MCyc local11 self)
						)
					)
					(2
						(if
						(and (& local0 $0001) (not (Btst 109)) (Btst 27))
							(newActor_2 show: setCycle: MCyc local15 self)
						else
							(newActor_2
								x: 294
								y: 145
								show:
								setCycle: MCyc local12 self
							)
						)
					)
					(3
						(if
						(and (& local0 $0001) (not (Btst 109)) (Btst 27))
							(newActor_3 show: setCycle: MCyc local15 self)
						else
							(newActor_3
								x: 87
								y: 121
								show:
								setCycle: MCyc local13 self
							)
						)
					)
					(4
						(if
						(and (& local0 $0001) (not (Btst 109)) (Btst 27))
							(newActor_4 show: setCycle: MCyc local15 self)
						else
							(newActor_4
								x: 216
								y: 85
								show:
								setCycle: MCyc local14 self
							)
						)
					)
				)
			)
			(2
				(if
				(and (& local0 $0001) (Btst 27) (not (Btst 109)))
					(Bset 109)
					(ego solvePuzzle: 408 2)
					(catcher setCycle: End self)
					(wheel setCycle: 0)
					(lightBulb setCycle: End)
				else
					(switch local6
						(1 (newActor hide:))
						(2 (newActor_2 hide:))
						(3 (newActor_3 hide:))
						(4 (newActor_4 hide:))
					)
					(= ticks 30)
				)
			)
			(3
				(if (not (Btst 109))
					(self changeState: 0)
				else
					(messager say: 4 4 1 0 self)
				)
			)
			(4 (self dispose:))
		)
	)
)

(instance bustAnim of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 20 45)))
			(1
				(mybust setCycle: ForwardCounter (Random 5 9) self)
			)
			(2
				(mybust
					cycleSpeed: 8
					setLoop: 4
					cel: 0
					setCycle: End self
				)
			)
			(3
				(mybust setLoop: 3 cycleSpeed: 16)
				(self changeState: 0)
			)
		)
	)
)

(instance openEndDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bclr 51)
				(endDoor setCycle: End self)
			)
			(1
				(if (not (Btst 410))
					(messager say: 19 6 20 1 self)
				else
					(= ticks 1)
				)
			)
			(2 (curRoom newRoom: 370))
		)
	)
)

(instance myCueObj of CueObj
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(ego
					setHeading:
						(GetAngle
							(ego x?)
							(- (ego y?) 77)
							(client x?)
							(client y?)
						)
						self
				)
				(theDoits add: self)
			)
			(2 (= cycles 3))
			(3
				(theDoits delete: self)
				(if (not ((client actions?) doVerb: theVerb))
					(client doVerb: theVerb)
				)
				(= state 0)
			)
		)
	)
)

(instance wheel of Prop
	(properties
		x 135
		y 135
		priority 170
		fixPriority 1
		view 386
		signal $4001
		detailLevel 3
	)
)

(instance lightBulb of Prop
	(properties
		x 106
		y 106
		priority 170
		fixPriority 1
		view 386
		loop 2
		signal $4001
		cycleSpeed 8
		detailLevel 3
	)
)

(instance catcher of Prop
	(properties
		x 89
		y 131
		priority 170
		fixPriority 1
		view 386
		loop 1
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(19
				(trapMachine doVerb: theVerb)
			)
			(4
				(if (Btst 109)
					(messager say: 4 4 1 0)
				else
					(super doVerb: theVerb)
				)
			)
			(1
				(if (Btst 109)
					(messager say: 4 4 1 0)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance mybust of Prop
	(properties
		noun 8
		x 290
		y 110
		view 386
		loop 3
		signal $4001
		cycleSpeed 10
	)
	
	(method (init)
		(super init:)
		(self setScript: bustAnim)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not (script state?)) (script changeState: 1))
				(super doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance leftDoor of Prop
	(properties
		noun 3
		approachX 75
		approachY 154
		x 42
		y 39
		view 380
		signal $4001
	)
	
	(method (handleEvent event)
		(if script (script handleEvent: event))
		(cond 
			((event claimed?) (return 1))
			(
				(and
					(& (event type?) evVERB)
					(self onMe: event)
					(self isNotHidden:)
				)
				(myCueObj
					state: 0
					cycles: 0
					client: self
					theVerb: (event message?)
				)
				(event claimed: 1)
				(if
					(and
						(user canControl:)
						(& (ego state?) $0002)
						(>
							(GetDistance (ego x?) (ego y?) approachX approachY)
							approachDist
						)
						approachCode
						(& _approachVerbs (approachCode doit: (event message?)))
					)
					(ego
						setMotion: PolyPath approachX (+ (ego z?) approachY) myCueObj
					)
				else
					(ego setMotion: 0)
					(if (self facingMe:) (myCueObj changeState: 3))
				)
			)
		)
		(return (event claimed?))
	)
)

(instance rightDoor of Prop
	(properties
		noun 2
		approachX 214
		approachY 145
		x 235
		y 39
		view 380
		loop 1
		signal $4001
	)
	
	(method (handleEvent event)
		(if script (script handleEvent: event))
		(cond 
			((event claimed?) (return 1))
			(
				(and
					(& (event type?) evVERB)
					(self onMe: event)
					(self isNotHidden:)
				)
				(myCueObj
					state: 0
					cycles: 0
					client: self
					theVerb: (event message?)
				)
				(event claimed: 1)
				(if
					(and
						(user canControl:)
						(& (ego state?) $0002)
						(>
							(GetDistance (ego x?) (ego y?) approachX approachY)
							approachDist
						)
						approachCode
						(& _approachVerbs (approachCode doit: (event message?)))
					)
					(ego
						setMotion: PolyPath approachX (+ (ego z?) approachY) myCueObj
					)
				else
					(ego setMotion: 0)
					(if (self facingMe:) (myCueObj changeState: 3))
				)
			)
		)
		(return (event claimed?))
	)
)

(instance endDoor of Prop
	(properties
		noun 1
		approachX 130
		approachY 107
		x 140
		y 64
		view 380
		loop 2
		signal $4001
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: 4 29)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 29)
			(cond 
				((and (Btst 52) (not (ego has: 58))) (messager say: 1 4 21))
				((and (not (Btst 52)) (ego has: 58))
					(theGame setCursor: normalCursor)
					((ScriptID 80 0) init: show: dispose:)
					(DisposeScript 80)
					(if (Btst 375)
						(curRoom setScript: openEndDoor)
					else
						(messager say: 1 4 22)
					)
				)
				((and (Btst 52) (ego has: 58)) (curRoom setScript: openEndDoor))
				(else (super doVerb: theVerb))
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance trapMachine of Feature
	(properties
		noun 4
		nsLeft 108
		nsTop 123
		nsRight 137
		nsBottom 168
		approachX 156
		approachY 177
		x 122
		y 145
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(4
				(= temp0 0)
				(if (Btst 33)
					(= temp0 1)
					(if (& local0 $0001)
						(if (Btst 109)
							(messager say: 4 4 1 0)
						else
							(messager say: 4 4 2)
						)
					else
						(messager say: 4 4 3)
					)
				else
					((ScriptID 83 0) init: show: dispose:)
					(DisposeScript 83)
				)
			)
			(19
				(if (Btst 27)
					(if (& local0 $0001)
						(messager say: 4 theVerb 5)
					else
						(= local0 (| local0 $0001))
						(ego drop: 4 1)
						(wheel setCycle: Fwd)
						(lightBulb setCycle: Fwd)
						(messager say: 4 theVerb 6)
					)
				else
					(messager say: noun theVerb 4)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance hole1 of Feature
	(properties
		noun 7
		nsLeft 4
		nsTop 59
		nsRight 25
		nsBottom 96
		x 14
		y 77
	)
)

(instance hole2 of Feature
	(properties
		noun 7
		nsLeft 284
		nsTop 126
		nsRight 305
		nsBottom 169
		x 294
		y 147
	)
)

(instance hole3 of Feature
	(properties
		noun 7
		nsLeft 84
		nsTop 115
		nsRight 91
		nsBottom 128
		x 87
		y 121
	)
)

(instance hole4 of Feature
	(properties
		noun 7
		nsLeft 212
		nsTop 77
		nsRight 220
		nsBottom 91
		x 216
		y 84
	)
)

(instance painting of Feature
	(properties
		noun 9
		nsLeft 79
		nsTop 50
		nsRight 99
		nsBottom 108
		x 89
		y 79
	)
)

(instance railing of Feature
	(properties
		noun 11
		nsLeft 133
		nsTop 98
		nsRight 175
		nsBottom 117
		x 154
		y 107
	)
)

(instance stairs1 of Feature
	(properties
		noun 10
		nsLeft 101
		nsTop 108
		nsRight 141
		nsBottom 128
		x 121
		y 118
	)
)

(instance stairs2 of Feature
	(properties
		noun 10
		nsLeft 166
		nsTop 109
		nsRight 206
		nsBottom 130
		x 186
		y 119
	)
)

(instance ball1 of Feature
	(properties
		noun 12
		nsLeft 104
		nsTop 38
		nsRight 121
		nsBottom 50
		x 112
		y 44
	)
)

(instance ball2 of Feature
	(properties
		noun 12
		nsLeft 184
		nsTop 37
		nsRight 200
		nsBottom 49
		x 192
		y 43
	)
)

(instance pillar1 of Feature
	(properties
		noun 13
		nsLeft 102
		nsTop 50
		nsRight 120
		nsBottom 109
		x 111
		y 79
	)
)

(instance pillar2 of Feature
	(properties
		noun 14
		nsLeft 182
		nsTop 49
		nsRight 204
		nsBottom 109
		x 193
		y 79
	)
)

(instance leftDoorTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(if (== iconValue 7)
			(curRoom setScript: openLeftDoor)
		else
			(super sayMessage:)
		)
	)
	
	(method (showCases)
		(super showCases: 10 (ego has: 13))
	)
)

(instance rightDoorTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(if (== iconValue 7)
			(curRoom setScript: openRightDoor)
		else
			(super sayMessage:)
		)
	)
	
	(method (showCases)
		(super showCases: 10 (ego has: 13))
	)
)

(instance endDoorTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(if (== iconValue 11)
			(self clean:)
			(if (and (Btst 52) (ego has: 58))
				(curRoom setScript: openEndDoor)
			else
				((ScriptID 80 0) init: show: dispose:)
				(DisposeScript 80)
				(if (Btst 375)
					(if (ego has: 58)
						(curRoom setScript: openEndDoor)
					else
						(messager say: 1 4 21)
					)
				else
					(messager say: 1 4 22)
				)
			)
		else
			(super sayMessage:)
		)
	)
	
	(method (showCases)
		(super showCases: 7 0 10 (ego has: 13))
	)
)
