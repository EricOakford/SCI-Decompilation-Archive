;;; Sierra Script 1.0 - (do not remove this comment)
(script# 320)
(include sci.sh)
(use Main)
(use rCliffs)
(use Kq6Procs)
(use Feature)
(use LoadMany)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm320 0
	dieHard 1
)

(local
	local0
	[local1 28] = [54 54 54 54 54 54 54 54 54 68 68 68 68 68 68 82 82 82 82 82 96 96 96 96 96 96 96 96]
	[local29 28] = [100 114 126 143 155 164 176 188 202 117 131 143 161 173 187 126 140 155 167 179 109 121 136 150 162 175 187 199]
	[local57 28] = [68 68 68 68 68 68 68 68 68 82 82 82 82 82 82 96 96 96 96 96 110 110 110 110 110 110 110 110]
	[local85 28] = [114 126 138 155 164 176 188 202 215 131 143 157 173 187 199 140 155 167 179 191 121 136 150 162 175 187 199 212]
	[local113 28] = [103 117 129 146 158 167 179 191 205 120 134 146 164 176 190 129 143 158 170 182 112 124 139 153 165 178 190 202]
	[local141 28] = [61 61 61 61 61 61 61 61 61 75 75 75 75 75 75 89 89 89 89 89 103 103 103 103 103 103 103 103]
	[local169 28] = [1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 3 3 3 3 3 4 4 4 4 4 4 4 4]
	[local197 28] = [0 1 2 3 4 5 6 7 8 0 1 2 3 4 5 0 1 2 3 4 0 1 2 3 4 5 6 7]
	[local225 28] = [1 0 0 2 0 0 0 0 0 3 0 0 0 0 4 5 0 0 0 0 0 0 0 0 6]
	[local253 26] = [54 67 67 67 67 62 80 80 80 80 94 94 94 94 94 94 94 108 108 109 109 109 122 119 135 144]
	[local279 26] = [65 78 78 78 78 73 91 91 91 91 105 105 105 105 105 105 105 119 119 120 120 120 133 130 146 155]
	[local305 26] = [168 117 133 150 168 188 101 117 133 150 109 126 143 160 177 194 211 109 126 143 161 227 161 191 161 180]
	[local331 26] = [181 130 146 163 181 201 114 130 146 163 122 139 156 173 190 207 224 122 139 156 174 240 174 204 174 193]
	[local357 26] = [1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2]
	[local383 26] = [0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0 1 2 3 4 5 6 7 8 9]
	[local409 26] = [0 0 0 0 0 0 0 0 0 -1 0 0 0 0 -1 -1 0 0 0 0 0 0 0 0 -1]
	[local435 26] = [0 0 0 0 0 0 0 0 1 2 0 0 0 0 0 0 0 0 0 3 0 4]
	[local461 4] = [76 77 76 76]
	[local465 4] = [160 97 190 129]
	[local469 4] = [97 97 97 97]
	[local473 4] = [187 124 217 156]
	[local477 4] = [1 1 1 1]
	[local481 4] = [2 0 3 1]
	[local485 4] = [169 106 199 138]
	[local489 4] = [76 76 76 76]
	[local493 4] = [0 2 1 3]
	[local497 4] = [1]
	[local501 29]
)
(instance rm320 of CliffRoom
	(properties
		picture 320
		horizon 0
		walkOffEdge 1
	)
	
	(method (init)
		(theGame handsOff:)
		(if (== prevRoomNum 300)
			(self style: 14)
		else
			(self style: -32758)
			(if (!= (theMusic number?) 915)
				(theMusic number: 915 setLoop: -1 play:)
			)
		)
		(LoadMany 128 322 325)
		(super init: &rest)
		(rCliffs stepDirection: 3)
		(if (Btst 123)
			(curRoom allRocksOut: 0)
		else
			(curRoom constantRocks:)
		)
		(writ x: 230 init: stopUpd:)
		(theRoom init:)
		(ego
			view: 301
			normal: 0
			cycleSpeed: 14
			setLoop: 2
			posn: 101 182
			setPri: 10
			init:
			actions: egoStepVerb
		)
		((ScriptID 21 0) notify:)
		(directionHandler add: self)
		(theGame handsOn:)
	)
	
	(method (cue param1)
		(switch param1
			(1
				(ego signal: 8192)
				(rCliffs cheatCount: 1)
				(curRoom setScript: nextCliffUp)
			)
			(0
				(rCliffs cheatCount: 15)
				(curRoom setScript: nextCliffDown)
			)
			(-1
				(curRoom setScript: downToBeach)
			)
		)
	)
	
	(method (notify)
		(curRoom setScript: insetDispose)
	)
)

(class PuzzleBackup of View
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0101
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		lookMsg 0
	)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(directionHandler addToFront: self)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(directionHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(User canInput:)
				(or
					(and
						(== (event message?) KEY_RETURN)
						(== (event type?) evKEYBOARD)
					)
					(== (event type?) evMOUSEBUTTON)
				)
				(!= (event type?) evVERB)
				(not (event modifiers?))
			)
			(cond 
				(
					(and
						(or
							(& (event type?) evMOUSEBUTTON)
							(and
								(& (event type?) evKEYBOARD)
								(== (event message?) KEY_RETURN)
							)
						)
						(self onMe: event)
						(== (theIconBar curIcon?) (theIconBar at: 2))
					)
					(event claimed: 1)
					(cond 
						((cast contains: puzzle3) (messager say: 2 1 0 1 0 21))
						((cast contains: puzzle1) (messager say: 1 1 0 1 0 21))
						(else (messager say: lookMsg 1 0 1 0 21))
					)
				)
				(
					(and
						(or
							(& (event type?) evMOUSEBUTTON)
							(and
								(& (event type?) evKEYBOARD)
								(== (event message?) KEY_RETURN)
							)
						)
						(self onMe: event)
						(== (theIconBar curIcon?) (theIconBar at: 1))
					)
					(event claimed: 1)
					0
				)
				(else (super handleEvent: event))
			)
		else
			(super handleEvent: event)
		)
		(event claimed?)
	)
)

(instance puzzle1 of PuzzleInset
	(properties
		x 157
		y 39
		z -45
		view 322
		maxButtons 4
		buttNumber 26
		buttView 322
		lookMsg 1
		puzzNumber 2
	)
	
	(method (init)
		(self
			buttTop: @local253
			buttLeft: @local305
			buttRight: @local331
			buttBottom: @local279
			buttLoop: @local357
			buttCel: @local383
			buttX: @local305
			buttY: @local253
			buttVal: @local435
			buttKill: @local501
		)
		(super init:)
		(headStone init:)
		(headStoneWords loop: 1 init:)
	)
	
	(method (dispose)
		(headStone dispose:)
		(headStoneWords dispose:)
		(super dispose:)
		(ego view: 301 setLoop: 6 cel: 0)
		(UnLoad 128 3012)
	)
)

(instance puzzle2 of PuzzleInset
	(properties
		x 163
		y 59
		z -28
		view 320
		maxButtons 3
		buttNumber 4
		buttView 323
		lookMsg 3
		puzzNumber 3
	)
	
	(method (init)
		(self
			buttTop: @local461
			buttLeft: @local465
			buttRight: @local473
			buttBottom: @local469
			buttLoop: @local477
			buttCel: @local481
			buttX: @local485
			buttY: @local489
			buttVal: @local493
			buttKill: @local497
		)
		(super init:)
		(rollos init: stopUpd:)
	)
	
	(method (dispose)
		(rollos dispose:)
		(super dispose:)
		(ego view: 301 setLoop: 1)
		(UnLoad 128 3012)
	)
)

(instance puzzle3 of PuzzleInset
	(properties
		x 157
		y 39
		z -45
		view 322
		maxButtons 4
		buttNumber 26
		buttView 322
		lookMsg 2
		puzzNumber 4
	)
	
	(method (init)
		(self
			buttTop: @local253
			buttLeft: @local305
			buttRight: @local331
			buttBottom: @local279
			buttLoop: @local357
			buttCel: @local383
			buttX: @local305
			buttY: @local253
			buttVal: @local409
			buttKill: @local501
		)
		(super init:)
		(headStone init:)
		(headStoneWords loop: 2 init:)
	)
	
	(method (dispose)
		(headStone dispose:)
		(headStoneWords dispose:)
		(super dispose:)
		(ego view: 301 setLoop: 6 cel: 0)
		(UnLoad 128 3012)
	)
)

(instance puzzle4 of PuzzleInset
	(properties
		x 157
		y 39
		z -45
		view 320
		maxButtons 6
		buttNumber 28
		buttView 324
		lookMsg 5
		puzzNumber 5
	)
	
	(method (init)
		(self
			buttTop: @local1
			buttLeft: @local29
			buttRight: @local85
			buttBottom: @local57
			buttLoop: @local169
			buttCel: @local197
			buttX: @local113
			buttY: @local141
			buttVal: @local225
			buttKill: @local501
		)
		(super init:)
		(words init: stopUpd:)
	)
	
	(method (dispose)
		(words dispose:)
		(super dispose:)
		(ego view: 301 setLoop: 1)
		(UnLoad 128 3012)
	)
)

(instance words of View
	(properties
		x 211
		y 62
		z -45
		view 324
		priority 13
		signal $4010
	)
	
	(method (doVerb theVerb)
		(puzzle4 doVerb: theVerb &rest)
	)
)

(instance rollos of View
	(properties
		x 98
		y 66
		z -12
		view 323
		priority 13
		signal $4010
	)
)

(instance headStone of PuzzleBackup
	(properties
		x 150
		y 7
		view 325
		priority 3
		signal $4010
	)
	
	(method (doVerb theVerb)
		(if (cast contains: puzzle1)
			(puzzle1 doVerb: theVerb &rest)
		else
			(puzzle3 doVerb: theVerb &rest)
		)
	)
)

(instance headStoneWords of PuzzleBackup
	(properties
		x 148
		y 17
		view 325
		priority 4
		signal $4010
	)
	
	(method (doVerb theVerb)
		(if (cast contains: puzzle1)
			(puzzle1 doVerb: theVerb &rest)
		else
			(puzzle3 doVerb: theVerb &rest)
		)
	)
)

(instance theRoom of Feature
	(properties
		noun 3
		nsBottom 190
		nsRight 320
	)
	
	(method (init)
		(super init: &rest)
		(walkHandler add: self)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(Bset 59)
				(curRoom setScript: dieHard)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance writ of View
	(properties
		y 189
		z 135
		noun 4
		view 326
		priority 9
		signal $4010
	)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler add: self)
		(mouseDownHandler add: self)
		(directionHandler add: self)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(directionHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not ((ScriptID 21 0) puzzleIsUp?))
				(or
					(== (theIconBar curIcon?) (theIconBar at: 1))
					(== (theIconBar curIcon?) (theIconBar at: 2))
				)
				(or
					(and
						(== (event message?) KEY_RETURN)
						(== (event type?) evKEYBOARD)
					)
					(== (event type?) evMOUSEBUTTON)
				)
				(User canInput:)
				(!= (event type?) evVERB)
				(self onMe: event)
				(not (event modifiers?))
			)
			(event claimed: 1)
			(if (or (< (ego y?) 110) (> (ego y?) 120))
				(messager say: 4 1 13 1)
			else
				(if (== (ego loop?) 6)
					(ego view: 3012 setLoop: 0 cel: 1)
				else
					(ego view: 3012 setLoop: 0 cel: 0)
				)
				(UnLoad 128 301)
				(switch ((ScriptID 21 0) cliffFace?)
					(0
						(if (Btst 123)
							(puzzle1 puzzSolved:)
						else
							(curRoom setScript: insetInit 0 puzzle1)
						)
					)
					(1
						(if (Btst 124)
							(puzzle2 puzzSolved:)
						else
							(curRoom setScript: insetInit 0 puzzle2)
						)
					)
					(2
						(if (Btst 125)
							(puzzle3 puzzSolved:)
						else
							(curRoom setScript: insetInit 0 puzzle3)
						)
					)
					(3
						(if (Btst 126)
							(puzzle4 puzzSolved:)
						else
							(curRoom setScript: insetInit 0 puzzle4)
						)
					)
				)
			)
		else
			(super handleEvent: event)
		)
	)
)

(instance insetDispose of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 6)
			)
			(1
				(if (mod ((ScriptID 21 0) cliffFace?) 2)
					(curRoom flipRocks: 1 callForRocks:)
				else
					(curRoom flipRocks: 0 callForRocks:)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance nextCliffUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== ((ScriptID 21 0) cliffFace?) 3)
					(theMusic fade: 0 10 10)
					(soundFx2 stop:)
					(curRoom newRoom: 340)
				else
					(curRoom dumpRocks:)
					(self cue:)
				)
			)
			(1
				(rCliffs cliffFace: (+ (rCliffs cliffFace?) 1))
				(if (> (rCliffs cliffFace?) 3) (rCliffs cliffFace: 3))
				(switch ((ScriptID 21 0) cliffFace?)
					(1
						(UnLoad 128 322)
						(UnLoad 128 325)
						(if (Btst 124)
							(curRoom allRocksOut: 1)
						else
							(curRoom constantRocks: 1)
						)
						(LoadMany 128 320 323)
					)
					(2
						(UnLoad 128 320)
						(UnLoad 128 323)
						(if (Btst 125)
							(curRoom allRocksOut: 0)
						else
							(curRoom constantRocks:)
						)
						(LoadMany 128 322 325)
					)
					(3
						(UnLoad 128 322)
						(UnLoad 128 325)
						(if (Btst 126)
							(curRoom allRocksOut: 1)
						else
							(curRoom constantRocks: 1)
						)
						(LoadMany 128 320 324)
					)
				)
				(curRoom drawPic: 320 14)
				(if (mod ((ScriptID 21 0) cliffFace?) 2)
					(writ
						loop: (if (== ((ScriptID 21 0) cliffFace?) 1) 1 else 0)
						x: 90
						stopUpd:
					)
					(ego posn: 238 182 setLoop: 6 cel: 0 setPri: 10 show:)
				else
					(writ loop: 0 x: 230 stopUpd:)
					(ego posn: 101 182 setLoop: 2 cel: 0 setPri: 10 show:)
				)
				(theGame handsOn:)
				((ScriptID 21 0) notify:)
				(self dispose:)
			)
		)
	)
)

(instance downToBeach of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc21_1)
				(ego
					view: 301
					setLoop: 7
					cel: 0
					posn: (+ (ego x?) 2) (+ (ego y?) 2)
					cycleSpeed: 16
				)
				(= cycles 8)
			)
			(1
				(ego cel: 1 posn: (ego x?) (ego y?))
				(= cycles 8)
			)
			(2
				(ego cel: 2 posn: (ego x?) (ego y?))
				(= cycles 8)
			)
			(3
				(ego cel: 3 posn: (ego x?) (- (ego y?) 1))
				(= cycles 8)
			)
			(4
				(ego cel: 4 posn: (- (ego x?) 4) (- (ego y?) 4))
				(= cycles 8)
			)
			(5
				(ego cel: 5 posn: (ego x?) (ego y?))
				(= cycles 8)
			)
			(6
				(ego cel: 6 posn: (ego x?) (ego y?))
				(= cycles 8)
			)
			(7
				(ego
					setLoop: 5
					cel: 0
					cycleSpeed: 12
					posn: (- (ego x?) 7) (+ (ego y?) 19)
					setCycle: End self
				)
				(rCliffs stepDirection: 1)
			)
			(8
				(curRoom dumpRocks:)
				(curRoom newRoom: 300)
			)
		)
	)
)

(instance nextCliffDown of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 21 0) cue:)
				(if (== (rCliffs stepDirection?) 2)
					(= temp0 5)
					(= temp1 18)
					(= temp2 6)
					(rCliffs stepDirection: 1)
				else
					(= temp0 4)
					(= temp1 -19)
					(= temp2 7)
					(rCliffs stepDirection: 2)
				)
				(ego
					setLoop: temp0
					cel: 0
					cycleSpeed: 12
					posn: (+ (ego x?) temp1) (+ (ego y?) temp2)
					setCycle: End self
				)
			)
			(1
				(proc21_1)
				(if (== (rCliffs stepDirection?) 2)
					(= temp0 7)
					(= temp1 -14)
					(= temp2 -6)
				else
					(= temp0 8)
					(= temp1 14)
					(= temp2 -5)
				)
				(ego
					view: 301
					setLoop: temp0
					cel: 0
					posn: (+ (ego x?) temp1) (+ (ego y?) temp2)
					cycleSpeed: 16
				)
				(= cycles 8)
			)
			(2
				(ego cel: 1 posn: (ego x?) (ego y?))
				(= cycles 8)
			)
			(3
				(ego cel: 2 posn: (ego x?) (ego y?))
				(= cycles 8)
			)
			(4
				(if (== (rCliffs stepDirection?) 2)
					(= temp2 0)
				else
					(= temp2 -3)
				)
				(ego cel: 3 posn: (ego x?) (+ (ego y?) temp2))
				(= cycles 8)
			)
			(5
				(if (== (rCliffs stepDirection?) 2)
					(= temp1 -4)
					(= temp2 0)
				else
					(= temp1 4)
					(= temp2 -1)
				)
				(ego
					cel: 4
					posn: (+ (ego x?) temp1) (+ (ego y?) temp2)
				)
				(= cycles 8)
			)
			(6
				(ego cel: 5 posn: (ego x?) (ego y?))
				(= cycles 8)
			)
			(7
				(if (== (rCliffs stepDirection?) 2)
					(= temp2 -2)
				else
					(= temp2 0)
				)
				(ego cel: 6 posn: (ego x?) (+ (ego y?) temp2))
				(= cycles 8)
			)
			(8
				(if (== (rCliffs stepDirection?) 2)
					(= temp0 6)
					(= temp1 -25)
					(= temp2 11)
				else
					(= temp0 1)
					(= temp1 27)
					(= temp2 12)
				)
				(ego
					view: 301
					setLoop: temp0
					cel: 0
					posn: (+ (ego x?) temp1) (+ (ego y?) temp2)
				)
				(= cycles 8)
			)
			(9
				((ScriptID 21 0)
					cliffFace: (- ((ScriptID 21 0) cliffFace?) 1)
				)
				(curRoom dumpRocks:)
				(curRoom drawPic: 320 13)
				(switch ((ScriptID 21 0) cliffFace?)
					(0
						(UnLoad 128 320)
						(UnLoad 128 323)
						(LoadMany 128 322 325)
						(curRoom allRocksOut: 0)
						(writ loop: 0 x: 230 stopUpd:)
						(ego view: 301 setLoop: 6 cel: 0 posn: 210 4)
					)
					(1
						(UnLoad 128 322)
						(UnLoad 128 325)
						(LoadMany 128 320 323)
						(curRoom allRocksOut: 1)
						(writ
							loop: (if (== ((ScriptID 21 0) cliffFace?) 1) 1 else 0)
							x: 90
							stopUpd:
						)
						(ego view: 301 setLoop: 1 cel: 0 posn: 127 4)
					)
					(2
						(UnLoad 128 320)
						(UnLoad 128 324)
						(LoadMany 128 322 325)
						(curRoom allRocksOut: 0)
						(writ loop: 0 x: 230 stopUpd:)
						(ego view: 301 setLoop: 6 cel: 0 posn: 210 4)
					)
				)
				(= cycles 8)
			)
			(10
				(proc21_1)
				(if (== (rCliffs stepDirection?) 2)
					(= temp0 7)
					(= temp1 1)
					(= temp2 1)
				else
					(= temp0 8)
					(= temp1 1)
					(= temp2 0)
				)
				(ego
					view: 301
					setLoop: temp0
					cel: 0
					cycleSpeed: 16
					posn: (+ (ego x?) temp1) (+ (ego y?) temp2)
				)
				(= cycles 8)
			)
			(11
				(ego cel: 1 posn: (ego x?) (ego y?))
				(= cycles 8)
			)
			(12
				(ego cel: 2 posn: (ego x?) (ego y?))
				(= cycles 8)
			)
			(13
				(if (== (rCliffs stepDirection?) 2)
					(= temp1 1)
					(= temp2 -2)
				else
					(= temp1 0)
					(= temp2 0)
				)
				(ego
					cel: 3
					posn: (+ (ego x?) temp1) (+ (ego y?) temp2)
				)
				(= cycles 8)
			)
			(14
				(if (== (rCliffs stepDirection?) 2)
					(= temp1 -4)
				else
					(= temp1 1)
				)
				(ego cel: 4 posn: (+ (ego x?) temp1) (ego y?))
				(= cycles 8)
			)
			(15
				(if (== (rCliffs stepDirection?) 2)
					(= temp1 0)
				else
					(= temp1 -1)
				)
				(ego cel: 5 posn: (+ (ego x?) temp1) (ego y?))
				(= cycles 8)
			)
			(16
				(if (== (rCliffs stepDirection?) 2)
					(= temp1 0)
				else
					(= temp1 -1)
				)
				(ego cel: 6 posn: (+ (ego x?) temp1) (ego y?))
				(= cycles 8)
			)
			(17
				(if (== (rCliffs stepDirection?) 2)
					(= temp0 6)
					(= temp1 -27)
					(= temp2 13)
				else
					(= temp0 1)
					(= temp1 29)
					(= temp2 12)
				)
				(ego
					view: 301
					setLoop: temp0
					cel: 0
					posn: (+ (ego x?) temp1) (+ (ego y?) temp2)
				)
				(if (== local0 3) (self cue:) else (= seconds 2))
			)
			(18
				(if (< local0 3)
					(++ local0)
					(= state (- state 9))
				else
					(= local0 0)
				)
				(self cue:)
			)
			(19
				(if (== (rCliffs stepDirection?) 2)
					(= temp0 5)
					(= temp1 18)
					(= temp2 7)
					(rCliffs stepDirection: 3)
				else
					(= temp0 4)
					(= temp1 -19)
					(= temp2 7)
					(rCliffs stepDirection: 4)
				)
				(ego
					setLoop: temp0
					cel: 0
					cycleSpeed: 12
					posn: (+ (ego x?) temp1) (+ (ego y?) temp2)
					setCycle: End self
				)
			)
			(20
				(if (== (ego loop?) 4)
					(= temp0 6)
					(= temp1 -15)
					(= temp2 -7)
				else
					(= temp0 1)
					(= temp1 17)
					(= temp2 -7)
				)
				(ego
					setLoop: temp0
					cel: 0
					posn: (+ (ego x?) temp1) (+ (ego y?) temp2)
				)
				(= ticks 4)
			)
			(21
				(theGame handsOn:)
				((ScriptID 21 0) cheatCount: 10)
				((ScriptID 21 0) notify:)
				(self dispose:)
			)
		)
	)
)

(instance insetInit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 21 0) cue:)
				(messager say: 4 1 12 1 self)
			)
			(1
				(register init:)
				(self dispose:)
			)
		)
	)
)

(instance dieHard of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 3])
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theIconBar disable: 6)
				((ScriptID 21 0) cue:)
				(if (< (ego loop?) 6)
					(ego
						posn: (- (ego x?) 8) (- (ego y?) 3)
						view: 900
						setLoop: 0
						cel: 0
					)
				else
					(ego
						posn: (+ (ego x?) 6) (- (ego y?) 3)
						view: 900
						setLoop: 1
						cel: 0
					)
				)
				(= cycles 6)
			)
			(1
				(Bclr 59)
				(ego
					x: (if (== (ego loop?) 1)
						(- (ego x?) 18)
					else
						(+ (ego x?) 18)
					)
					y: (if (== (ego loop?) 1)
						(+ (ego y?) 2)
					else
						(+ (ego y?) 2)
					)
					view: 4011
					normal: 0
					cycleSpeed: 6
					setLoop: (if (== (ego loop?) 1) 1 else 0)
					setCycle: CT 10 1 self
				)
			)
			(2
				(soundFx number: 306 setLoop: 1 play: self)
				(ego setCycle: End)
			)
			(3 (ego y: 280) (= seconds 2))
			(4
				(soundFx number: 307 setLoop: 1 play:)
				(ShakeScreen 2 2)
				(= ticks 4)
			)
			(5
				(messager say: 9 3 38 3 self)
			)
			(6
				(soundFx2 fade: 0 5 5)
				(EgoDead 8)
			)
		)
	)
)

(instance egoStepVerb of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1 (return 0))
				(5 (return 0))
				(2 (return 0))
				(else 
					(messager say: 0 0 64 1 0 899)
					(return 1)
				)
			)
		)
	)
)
