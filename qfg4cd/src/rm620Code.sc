;;; Sierra Script 1.0 - (do not remove this comment)
(script# 633)
(include sci.sh)
(use Main)
(use TellerIcon)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm620Code 0
	pBottomDoor 1
	pUpperDoor 2
	wasMusic3 3
)

(local
	local0
	local1
	local2
	local3
	local4
	[local5 2]
	local7
	local8
	local9
)
(instance rm620Code of Code
	(properties)
	
	(method (init)
		(if (< (ego y?) 64)
			(ego setScaler: Scaler 100 37 189 31)
			(= local3 1)
		else
			(ego setScaler: Scaler 100 37 189 31)
		)
		(if (!= (curRoom style?) 0)
			(= local4 1)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 3
						init:
							226
							32
							237
							38
							250
							51
							260
							66
							261
							81
							253
							108
							233
							136
							180
							163
							108
							184
							63
							202
							63
							157
							0
							168
							0
							189
							62
							204
							113
							187
							189
							164
							262
							128
							276
							93
							267
							58
							233
							32
						yourself:
					)
			)
			(curRoom
				addPoly:
					((Polygon new:)
						type: 1
						init: 222 30 232 32 271 54 250 58
						yourself:
					)
					30
					((Polygon new:)
						type: 1
						init: 250 58 272 55 284 106 250 104
						yourself:
					)
					40
					((Polygon new:)
						type: 1
						init: 249 104 284 106 260 138 235 128
						yourself:
					)
					50
					((Polygon new:)
						type: 1
						init: 75 180 236 128 260 139 121 189 67 189
						yourself:
					)
					90
					((Polygon new:)
						type: 1
						init: 28 185 60 166 51 158 73 155 69 189 5 189
						yourself:
					)
					50
					((Polygon new:)
						type: 1
						init: 0 163 20 157 51 158 59 166 28 186 0 189
						yourself:
					)
					90
			)
			(pBottomDoor
				setLoop: 1
				posn: 82 141
				setCel: (cond 
					(local3 0)
					((== prevRoomNum 810) 0)
					(else 7)
				)
				approachX: 48
				approachY: 158
				approachVerbs: 4 32
				init:
			)
			(pUpperDoor
				setLoop: 3
				setCel: (if local3 7 else 0)
				posn: 214 25
				approachX: 229
				approachY: 30
				ignoreActors:
				approachVerbs: 4 32
				init:
			)
			(pTorch1
				setLoop: 1
				posn: 130 142
				setPri: 0
				approachVerbs: 4
				setCycle: Fwd
				init:
			)
			(pTorch2
				setLoop: 5 1
				cel: 0
				posn: 230 54
				setPri: 0
				approachVerbs: 4
				init:
				setCycle: Fwd
			)
			(pTorch3
				setLoop: 7
				posn: 85 150
				setPri: 0
				approachVerbs: 4
				setCycle: Fwd
				init:
			)
			(pTorch4
				setLoop: 9
				posn: 179 117
				setPri: 0
				approachVerbs: 4
				setCycle: Fwd
				init:
			)
			(pTorch5
				setLoop: 11
				posn: 213 105
				setPri: 0
				approachVerbs: 4
				setCycle: Fwd
				init:
			)
			(pTorch6
				setLoop: 13
				posn: 22 121
				setPri: 0
				approachVerbs: 4
				setCycle: Fwd
				init:
			)
			(hSconce1 init: approachVerbs: 4)
			(hSconce2 init: approachVerbs: 4)
			(hSconce3 init: approachVerbs: 4)
			(hSconce4 init: approachVerbs: 4)
			(hSconce5 init: approachVerbs: 4)
			(hSconce6 init: approachVerbs: 4)
			(hStair1 init:)
			(hStair2 init:)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 3
						init:
							79
							38
							74
							39
							53
							54
							41
							100
							49
							118
							128
							166
							197
							189
							261
							204
							319
							189
							319
							175
							283
							163
							280
							158
							257
							165
							261
							202
							246
							187
							241
							186
							199
							184
							130
							161
							81
							130
							63
							106
							56
							75
							63
							55
							92
							32
							81
							32
						yourself:
					)
			)
			(curRoom
				addPoly:
					((Polygon new:)
						init: 43 107 71 103 85 130 134 156 92 149 51 128
						yourself:
					)
					80
					((Polygon new:)
						type: 0
						init: 39 108 33 76 62 73 71 102
						yourself:
					)
					50
					((Polygon new:)
						type: 0
						init: 32 76 47 49 72 52 63 74
						yourself:
					)
					40
					((Polygon new:)
						type: 0
						init: 46 48 83 28 103 29 72 52
						yourself:
					)
					30
					((Polygon new:)
						type: 0
						init: 319 163 319 188 307 187 264 171 258 166 270 158 296 158
						yourself:
					)
					90
					((Polygon new:)
						type: 0
						init:
							270
							158
							258
							166
							264
							171
							313
							189
							252
							189
							248
							154
							256
							150
							275
							149
							300
							158
						yourself:
					)
					60
			)
			(pBottomDoor
				setCel: (cond 
					(local3 0)
					((== prevRoomNum 810) 0)
					(else 7)
				)
				init:
				approachVerbs: 4 32
			)
			(pUpperDoor
				setCel: (if local3 7 else 0)
				init:
				approachVerbs: 4 32
			)
			(pTorch1 approachVerbs: 4 setCycle: Fwd init:)
			(pTorch2 approachVerbs: 4 setCycle: Fwd init:)
			(pTorch3 approachVerbs: 4 setCycle: Fwd init:)
			(pTorch4 approachVerbs: 4 setCycle: Fwd init:)
			(pTorch5 approachVerbs: 4 setCycle: Fwd init:)
			(pTorch6 approachVerbs: 4 setCycle: Fwd init:)
			(fSconce1 init: approachVerbs: 4)
			(fSconce2 init: approachVerbs: 4)
			(fSconce3 init: approachVerbs: 4)
			(fSconce4 init: approachVerbs: 4)
			(fSconce5 init: approachVerbs: 4)
			(fSconce6 init: approachVerbs: 4)
			(dStair1 init:)
			(dStair2 init:)
		)
		(if (== heroType 2)
			(if (== (= local0 (Random 1 4)) 3)
				(= local0 1)
			else
				(= local0 0)
			)
		)
		(if (< (ego y?) 65) (= local1 0) else (= local1 1))
		(doorBottomTeller init: pBottomDoor 620 8 155 2)
		(if (and (== curRoomNum 627) Night)
			(doorTopTeller init: pUpperDoor 620 8 155 5)
		else
			(doorTopTeller init: pUpperDoor 620 8 155 1)
		)
		(if (!= prevRoomNum 810) (curRoom setScript: sEnter))
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 24)
			)
			(1
				(if local3
					(pUpperDoor ignoreActors: 1)
				else
					(pBottomDoor ignoreActors: 1)
				)
				(= ticks 12)
			)
			(2
				(if local3
					(ego ignoreActors: 1)
					(if local4
						(ego setMotion: MoveTo 252 44 self)
					else
						(ego setMotion: MoveTo 83 38 self)
					)
				else
					(ego ignoreActors: 1)
					(if local4
						(ego setMotion: MoveTo 48 170 self)
					else
						(ego setMotion: MoveTo 278 172 self)
					)
				)
			)
			(3
				(wasMusic3 number: 973 loop: 1 play:)
				(if local3
					(pUpperDoor setCycle: Beg self)
				else
					(pBottomDoor setCycle: Beg self)
				)
			)
			(4
				(theGame handsOn:)
				(wasMusic3 stop: dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sPeepingTom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cond 
					((!= (curRoom style?) 0)
						(if (< (ego y?) 64)
							(ego setLoop: 0)
						else
							(ego setLoop: 1)
						)
					)
					((< (ego y?) 64) (ego setLoop: 1))
					(else (ego setLoop: 0))
				)
				(ego view: 4 setCel: 0 setCycle: End self)
			)
			(1
				(cond 
					((and (< (ego y?) 64) (== curRoomNum 620)) (messager say: 8 155 32 1 self 620))
					(
					(and (< (ego y?) 64) (== curRoomNum 627) Night) (messager say: 8 155 31 1 self 620))
					(else (messager say: 8 155 27 1 self 620))
				)
			)
			(2 (= seconds 3))
			(3 (ego setCycle: Beg self))
			(4
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sListened2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 8 155 10 1 self 620)
			)
			(1
				(wasMusic3 number: 972 setLoop: 1 play:)
				(pUpperDoor setCycle: CT 3 1 self)
			)
			(2
				(if (or (not (Btst 114)) (Btst 113))
					(wasMusic3 number: 143 setLoop: 1 play:)
					(messager say: 4 6 6 0 self 620)
				else
					(self cue:)
				)
			)
			(3
				(theMusic2 stop:)
				(wasMusic3 stop: dispose:)
				(= cycles 5)
			)
			(4
				(Palette palSET_FLAG 66 85 100)
				(curRoom newRoom: 630)
			)
		)
	)
)

(instance sListened of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 8 155 9 1 self 620)
			)
			(1
				(wasMusic3 number: 972 setLoop: 1 play:)
				(pUpperDoor setCycle: CT 3 1 self)
			)
			(2
				(if (or (not (Btst 114)) (Btst 113))
					(wasMusic3 number: 143 setLoop: 1 play:)
					(messager say: 4 6 6 0 self 620)
				else
					(self cue:)
				)
			)
			(3
				(theMusic2 stop:)
				(wasMusic3 stop: dispose:)
				(= cycles 5)
			)
			(4
				(Palette palSET_FLAG 66 85 100)
				(curRoom newRoom: 630)
			)
		)
	)
)

(instance sPickLock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1
				(cond 
					(
						(and
							(or
								(and (Btst 222) (< (ego y?) 64))
								(and (Btst 220) (not (< (ego y?) 64)))
							)
							(== curRoomNum 620)
						)
						(self cue:)
					)
					(
						(and
							(or
								(and (Btst 221) (< (ego y?) 64))
								(and (Btst 223) (not (< (ego y?) 64)))
							)
							(== curRoomNum 621)
						)
						(self cue:)
					)
					(
						(and
							(or
								(and (Btst 216) (< (ego y?) 64))
								(and (Btst 215) (not (< (ego y?) 64)))
							)
							(== curRoomNum 624)
						)
						(self cue:)
					)
					(
						(and
							(or
								(and (Btst 214) (< (ego y?) 64))
								(and (Btst 207) (not (< (ego y?) 64)))
							)
							(== curRoomNum 625)
						)
						(self cue:)
					)
					(
						(and
							(not (Btst 517))
							(not (Btst 114))
							(== curRoomNum 627)
							(< (ego y?) 64)
						)
						(wasMusic3 number: 143 setLoop: 1 play:)
						(messager say: 4 6 6 0 self 620)
					)
					(
						(and
							(or
								(and (Btst 114) (< (ego y?) 64))
								(and (Btst 208) (not (< (ego y?) 64)))
							)
							(== curRoomNum 627)
						)
						(self cue:)
					)
					((and (Btst 118) (== curRoomNum 629)) (self cue:))
					(
						(and
							(or
								(and (Btst 212) (< (ego y?) 64))
								(and (Btst 211) (not (< (ego y?) 64)))
							)
							(== curRoomNum 632)
						)
						(self cue:)
					)
					(else (wasMusic3 number: 143 setLoop: 1 play:) (self cue:))
				)
			)
			(2
				(if (== curRoomNum 629)
					(messager say: 6 6 23 0 self 620)
				else
					(= ticks 12)
				)
			)
			(3
				(wasMusic3 number: 972 setLoop: 1 play:)
				(if (< (ego y?) 45)
					(pUpperDoor setCycle: End self)
				else
					(pBottomDoor setCycle: End self)
				)
			)
			(4
				(if (>= castleDoorDifficulty 300)
					(= castleDoorDifficulty 300)
				else
					(++ castleDoorDifficulty)
				)
				(= ticks 24)
			)
			(5
				(theMusic2 stop:)
				(wasMusic3 stop: dispose:)
				(= cycles 5)
			)
			(6
				(Palette palSET_FLAG 66 85 100)
				(if (< (ego y?) 60)
					(curRoom newRoom: (curRoom north?))
				else
					(curRoom
						newRoom:
							(switch curRoomNum
								(620 640)
								(621 660)
								(624 643)
								(625 630)
								(627 662)
								(629 670)
								(632 644)
							)
					)
				)
			)
		)
	)
)

(instance sCastOpenDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego trySkill: 20)
				(= seconds 2)
			)
			(1
				(cond 
					(
						(and
							(or
								(and (Btst 222) (< (ego y?) 64))
								(and (Btst 220) (not (< (ego y?) 64)))
							)
							(== curRoomNum 620)
						)
						(self cue:)
					)
					(
						(and
							(or
								(and (Btst 221) (< (ego y?) 64))
								(and (Btst 223) (not (< (ego y?) 64)))
							)
							(== curRoomNum 621)
						)
						(self cue:)
					)
					(
						(and
							(or
								(and (Btst 216) (< (ego y?) 64))
								(and (Btst 215) (not (< (ego y?) 64)))
							)
							(== curRoomNum 624)
						)
						(self cue:)
					)
					(
						(and
							(or
								(and (Btst 214) (< (ego y?) 64))
								(and (Btst 207) (not (< (ego y?) 64)))
							)
							(== curRoomNum 625)
						)
						(self cue:)
					)
					(
						(and
							(not (Btst 517))
							(not (Btst 114))
							(== curRoomNum 627)
							(< (ego y?) 64)
						)
						(wasMusic3 number: 143 setLoop: 1 play:)
						(messager say: 4 6 6 0 self 620)
					)
					(
						(and
							(or
								(and (Btst 114) (< (ego y?) 64))
								(and (Btst 208) (not (< (ego y?) 64)))
							)
							(== curRoomNum 627)
						)
						(self cue:)
					)
					((and (Btst 118) (== curRoomNum 629)) (self cue:))
					(
						(and
							(or
								(and (Btst 212) (< (ego y?) 64))
								(and (Btst 211) (not (< (ego y?) 64)))
							)
							(== curRoomNum 632)
						)
						(self cue:)
					)
					(else (wasMusic3 number: 143 setLoop: 1 play:) (self cue:))
				)
			)
			(2
				(if (== curRoomNum 629)
					(messager say: 6 6 23 0 self 620)
				else
					(= ticks 12)
				)
			)
			(3
				(wasMusic3 number: 972 setLoop: 1 play:)
				(if (== local1 0)
					(pUpperDoor setCycle: End self)
				else
					(pBottomDoor setCycle: End self)
				)
			)
			(4
				(if (>= castleDoorDifficulty 300)
					(= castleDoorDifficulty 300)
				else
					(++ castleDoorDifficulty)
				)
				(= ticks 24)
			)
			(5
				(theMusic2 stop:)
				(wasMusic3 stop: dispose:)
				(= cycles 5)
			)
			(6
				(if (== local1 0)
					(ego
						setMotion: PolyPath (pUpperDoor approachX?) (pUpperDoor approachY?) self
					)
				else
					(ego
						setMotion:
							PolyPath
							(pBottomDoor approachX?)
							(pBottomDoor approachY?)
							self
					)
				)
			)
			(7
				(Palette palSET_FLAG 66 85 100)
				(if (== local1 0)
					(curRoom newRoom: (curRoom north?))
				else
					(curRoom
						newRoom:
							(switch curRoomNum
								(620 640)
								(621 660)
								(624 643)
								(625 630)
								(627 662)
								(629 670)
								(632 644)
							)
					)
				)
			)
		)
	)
)

(instance sDisplay of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and Night (== curRoomNum 627))
					(if (or local2 (Btst 112))
						(theGame handsOn:)
						(self dispose:)
					else
						(theGame handsOff:)
						(= local2 1)
						(messager say: 4 6 13 0 self 620)
					)
				else
					(self dispose:)
				)
			)
			(1
				(if (== heroType 3)
					(messager say: 4 6 14 0 self 620)
				else
					(self cue:)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance pBottomDoor of Prop
	(properties
		noun 2
		modNum 620
		sightAngle 180
		approachX 264
		approachY 155
		x 238
		y 143
		priority 34
		fixPriority 1
		view 620
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(42
				(ego trySkill: 9 castleDoorDifficulty)
				(ego trySkill: 9 castleDoorDifficulty)
				(if (== (ego trySkill: 9 castleDoorDifficulty) 1)
					(pBottomDoor setScript: sPickLock)
				else
					(messager say: 2 42 25 1 0 620)
				)
			)
			(32
				(switch curRoomNum
					(620 (Bset 220))
					(621 (Bset 223))
					(624 (Bset 215))
					(625 (Bset 207))
					(627 (Bset 208))
					(632 (Btst 211))
				)
				(super doVerb: theVerb)
			)
			(-80
				(= local1 1)
				(curRoom setScript: sCastOpenDoor)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pUpperDoor of Prop
	(properties
		noun 1
		modNum 620
		sightAngle 180
		approachX 92
		approachY 32
		x 107
		y 24
		view 620
		loop 2
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 97 31 87 42 69 39 79 31
						yourself:
					)
					3
					6
					7
					sDisplay
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
			(42
				(if (and Night (== curRoomNum 627))
					(Bset 113)
					(pUpperDoor setScript: sListened)
				else
					(ego trySkill: 9 castleDoorDifficulty)
					(ego trySkill: 9 castleDoorDifficulty)
					(if (== (ego trySkill: 9 castleDoorDifficulty) 1)
						(pUpperDoor setScript: sPickLock)
					else
						(messager say: 2 42 25 1 0 620)
					)
				)
			)
			(32
				(switch curRoomNum
					(620 (Bset 222))
					(621 (Bset 221))
					(624 (Bset 216))
					(625 (Bset 214))
					(627
						(Bset 114)
						(ego solvePuzzle: 517 2 4)
					)
					(629 (Bset 118))
					(632 (Btst 212))
				)
				(super doVerb: theVerb)
			)
			(-80
				(= local1 0)
				(curRoom setScript: sCastOpenDoor)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pTorch1 of Prop
	(properties
		noun 3
		modNum 620
		sightAngle 180
		x 140
		y 117
		view 621
		loop 8
		cel 3
		detailLevel 2
	)
	
	(method (doit)
		(super doit:)
		(if (> (ego y?) 140) (= local9 1) else (= local9 0))
		(= local7 ((User curEvent?) x?))
		(= local8 ((User curEvent?) y?))
		(if (> (theGame detailLevel:) 1)
			(cond 
				((OneOf cel 1 2)
					(if (< (GetDistance x y (ego x?) (ego y?) 0) 45)
						(Palette palSET_FLAG 66 85 88)
					)
				)
				((OneOf cel 0 5)
					(if (< (GetDistance x y (ego x?) (ego y?) 0) 45)
						(Palette palSET_FLAG 66 85 62)
					)
				)
				(else (Palette palSET_FLAG 66 85 75))
			)
		)
	)
)

(instance pTorch2 of Prop
	(properties
		noun 3
		modNum 620
		sightAngle 180
		x 89
		y 55
		view 621
		loop 4
		cel 5
		detailLevel 2
	)
	
	(method (doit)
		(super doit:)
		(if (> (theGame detailLevel:) 1)
			(cond 
				((OneOf cel 1 2)
					(if (< (GetDistance x y (ego x?) (ego y?) 0) 45)
						(Palette palSET_FLAG 66 85 88)
					)
				)
				((OneOf cel 0 5)
					(if (< (GetDistance x y (ego x?) (ego y?) 0) 45)
						(Palette palSET_FLAG 66 85 62)
					)
				)
				(else (Palette palSET_FLAG 66 85 75))
			)
		)
	)
)

(instance pTorch3 of Prop
	(properties
		noun 3
		modNum 620
		sightAngle 180
		x 234
		y 150
		view 621
		loop 6
		cel 3
		detailLevel 2
	)
	
	(method (doit)
		(super doit:)
		(if (> (theGame detailLevel:) 1)
			(cond 
				((OneOf cel 1 2)
					(if (< (GetDistance x y (ego x?) (ego y?) 0) 45)
						(Palette palSET_FLAG 66 85 88)
					)
				)
				((OneOf cel 0 5)
					(if (< (GetDistance x y (ego x?) (ego y?) 0) 45)
						(Palette palSET_FLAG 66 85 62)
					)
				)
				(else (Palette palSET_FLAG 66 85 75))
			)
		)
	)
)

(instance pTorch4 of Prop
	(properties
		noun 3
		modNum 620
		sightAngle 180
		x 189
		y 142
		view 621
		detailLevel 2
	)
	
	(method (doit)
		(super doit:)
		(if (> (theGame detailLevel:) 1)
			(cond 
				((OneOf cel 1 2)
					(if (< (GetDistance x y (ego x?) (ego y?) 0) 45)
						(Palette palSET_FLAG 66 85 88)
					)
				)
				((OneOf cel 0 5)
					(if (< (GetDistance x y (ego x?) (ego y?) 0) 45)
						(Palette palSET_FLAG 66 85 62)
					)
				)
				(else (Palette palSET_FLAG 66 85 75))
			)
		)
	)
)

(instance pTorch5 of Prop
	(properties
		noun 3
		modNum 620
		sightAngle 180
		x 106
		y 104
		view 621
		loop 10
		detailLevel 2
	)
	
	(method (doit)
		(super doit:)
		(if (> (theGame detailLevel:) 1)
			(cond 
				((OneOf cel 1 2)
					(if (< (GetDistance x y (ego x?) (ego y?) 0) 45)
						(Palette palSET_FLAG 66 85 88)
					)
				)
				((OneOf cel 0 5)
					(if (< (GetDistance x y (ego x?) (ego y?) 0) 45)
						(Palette palSET_FLAG 66 85 62)
					)
				)
				(else (Palette palSET_FLAG 66 85 75))
			)
		)
	)
)

(instance pTorch6 of Prop
	(properties
		noun 3
		modNum 620
		sightAngle 180
		x 298
		y 119
		view 621
		loop 12
		detailLevel 2
	)
	
	(method (doit)
		(super doit:)
		(if (> (theGame detailLevel:) 1)
			(cond 
				((OneOf cel 1 2)
					(if (< (GetDistance x y (ego x?) (ego y?) 0) 45)
						(Palette palSET_FLAG 66 85 88)
					)
				)
				((OneOf cel 0 5)
					(if (< (GetDistance x y (ego x?) (ego y?) 0) 45)
						(Palette palSET_FLAG 66 85 62)
					)
				)
				(else (Palette palSET_FLAG 66 85 75))
			)
		)
	)
)

(instance fSconce1 of Feature
	(properties
		noun 3
		modNum 620
		nsLeft 87
		nsTop 48
		nsRight 94
		nsBottom 59
		sightAngle 180
		x 90
		y 53
	)
)

(instance fSconce2 of Feature
	(properties
		noun 3
		modNum 620
		nsLeft 100
		nsTop 101
		nsRight 110
		nsBottom 126
		sightAngle 180
		x 105
		y 113
	)
)

(instance fSconce3 of Feature
	(properties
		noun 3
		modNum 620
		nsLeft 134
		nsTop 113
		nsRight 146
		nsBottom 137
		sightAngle 180
		x 140
		y 125
	)
)

(instance fSconce4 of Feature
	(properties
		noun 3
		modNum 620
		nsLeft 182
		nsTop 133
		nsRight 193
		nsBottom 158
		sightAngle 180
		x 187
		y 145
	)
)

(instance fSconce5 of Feature
	(properties
		noun 3
		modNum 620
		nsLeft 232
		nsTop 143
		nsRight 241
		nsBottom 169
		sightAngle 180
		x 236
		y 156
	)
)

(instance fSconce6 of Feature
	(properties
		noun 3
		modNum 620
		nsLeft 295
		nsTop 114
		nsRight 309
		nsBottom 135
		sightAngle 180
		x 302
		y 124
	)
)

(instance dStair1 of Feature
	(properties
		noun 3
		modNum 620
		nsLeft 243
		nsTop 184
		nsRight 262
		nsBottom 189
		sightAngle 180
		x 245
		y 184
	)
)

(instance dStair2 of Feature
	(properties
		modNum 620
		nsLeft 63
		nsTop 128
		nsRight 138
		nsBottom 93
		sightAngle 180
		x 65
		y 134
	)
)

(instance hStair1 of Feature
	(properties
		modNum 620
		nsLeft 57
		nsTop 182
		nsRight 80
		nsBottom 189
		sightAngle 180
		x 57
		y 184
	)
)

(instance hStair2 of Feature
	(properties
		modNum 620
		nsLeft 218
		nsTop 130
		nsRight 269
		nsBottom 141
		sightAngle 180
		x 130
		y 218
	)
)

(instance hSconce1 of Feature
	(properties
		noun 3
		modNum 620
		nsLeft 71
		nsTop 129
		nsRight 92
		nsBottom 175
		sightAngle 180
		x 81
		y 139
	)
)

(instance hSconce2 of Feature
	(properties
		noun 3
		modNum 620
		nsLeft 121
		nsTop 131
		nsRight 136
		nsBottom 173
		sightAngle 180
		x 128
		y 152
	)
)

(instance hSconce3 of Feature
	(properties
		noun 3
		modNum 620
		nsLeft 171
		nsTop 113
		nsRight 183
		nsBottom 138
		sightAngle 180
		x 177
		y 125
	)
)

(instance hSconce4 of Feature
	(properties
		noun 3
		modNum 620
		nsLeft 204
		nsTop 101
		nsRight 216
		nsBottom 128
		sightAngle 180
		x 210
		y 114
	)
)

(instance hSconce5 of Feature
	(properties
		noun 3
		modNum 620
		nsLeft 15
		nsTop 113
		nsRight 22
		nsBottom 132
		sightAngle 180
		x 18
		y 122
	)
)

(instance hSconce6 of Feature
	(properties
		noun 3
		modNum 620
		nsLeft 53
		nsTop 128
		nsRight 58
		nsBottom 140
		sightAngle 180
		x 55
		y 134
	)
)

(instance doorBottomTeller of Teller
	(properties
		modNum 620
		actionVerb 4
	)
	
	(method (sayMessage)
		(switch iconValue
			(4
				(ego trySkill: 9 castleDoorDifficulty)
				(ego trySkill: 9 castleDoorDifficulty)
				(if (== (ego trySkill: 9 castleDoorDifficulty) 1)
					(self clean:)
					(pBottomDoor setScript: sPickLock)
				else
					(messager say: 2 42 25 0 0 620)
				)
			)
			(1
				(self clean:)
				(pBottomDoor setScript: sPickLock)
			)
			(27
				(self clean:)
				(pBottomDoor setScript: sPeepingTom)
			)
			(else  (super sayMessage:))
		)
	)
	
	(method (showCases)
		(super
			showCases:
				4
				(if (and (ego has: 24) (> [egoStats 9] 0))
					local0
				else
					0
				)
				1
				(if (== local0 0) else (== local1 1))
				26
				(if (== local0 1) (== local1 0) else 0)
		)
	)
)

(instance doorTopTeller of Teller
	(properties
		modNum 620
		actionVerb 4
	)
	
	(method (sayMessage)
		(switch iconValue
			(9
				(self clean:)
				(pUpperDoor setScript: sListened)
			)
			(4
				(if (and Night (== curRoomNum 627))
					(self clean:)
					(Bset 113)
					(pUpperDoor setScript: sListened)
				else
					(ego trySkill: 9 castleDoorDifficulty)
					(ego trySkill: 9 castleDoorDifficulty)
					(if (== (ego trySkill: 9 castleDoorDifficulty) 1)
						(self clean:)
						(pUpperDoor setScript: sPickLock)
					else
						(messager say: 2 42 25 0 self 620)
					)
				)
			)
			(12
				(if (and Night (== curRoomNum 627))
					(Bset 113)
					(self clean:)
					(pUpperDoor setScript: sListened)
				else
					(ego trySkill: 9 castleDoorDifficulty)
					(ego trySkill: 9 castleDoorDifficulty)
					(if (== (ego trySkill: 9 castleDoorDifficulty) 1)
						(self clean:)
						(pUpperDoor setScript: sPickLock)
					else
						(messager say: 2 42 25 0 self 620)
					)
				)
			)
			(27
				(self clean:)
				(pUpperDoor setScript: sPeepingTom)
			)
			(1
				(if (and Night (== curRoomNum 627))
					(Bset 113)
					(self clean:)
					(pUpperDoor setScript: sListened)
				else
					(self clean:)
					(pUpperDoor setScript: sPickLock)
				)
			)
			(10
				(if (and Night (== curRoomNum 627))
					(Bset 113)
					(self clean:)
					(pUpperDoor setScript: sListened2)
				else
					(self clean:)
					(pUpperDoor setScript: sPickLock)
				)
			)
			(11
				(Bset 113)
				(self clean:)
				(pUpperDoor setScript: sListened)
			)
			(3
				(if (and (not (Btst 115)) (== curRoomNum 620))
					(messager say: 8 155 36 0 self 620)
				else
					(super sayMessage:)
				)
			)
			(else  (super sayMessage:))
		)
	)
	
	(method (showCases)
		(super
			showCases:
				4
				(if (and (ego has: 24) (> [egoStats 9] 0))
					local0
				else
					0
				)
				12
				(if (and (ego has: 24) (> [egoStats 9] 0))
					local0
				else
					0
				)
				1
				(if (== local0 0) else (== local1 0))
				26
				(if (== local0 1) (== local1 1) else 0)
		)
	)
)

(instance wasMusic3 of Sound
	(properties)
)
