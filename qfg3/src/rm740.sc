;;; Sierra Script 1.0 - (do not remove this comment)
(script# 740)
(include sci.sh)
(use Main)
(use TellerIcon)
(use EgoDead)
(use JumpX)
(use Scaler)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm740 0
)

(local
	[local0 21] = [4 0 151 41 4 1 150 41 4 2 150 56 4 3 150 66 4 4 150 79 -32768]
	[newJumpScript 10]
	[newMonkey 10]
	local41
	local42
	local43
	local44
	local45
	local46
	local47
	local48
	local49
	[local50 9] = [0 1 -4 -10 -7 -6 -2 8 999]
	[local59 2]
	[local61 5] = [0 15 2 16 999]
	[local66 2]
)
(instance rm740 of Rm
	(properties
		noun 13
		picture 740
	)
	
	(method (init)
		(HandsOff)
		(theIconBar disable:)
		(= [local59 0] @local50)
		(= [local66 0] @local61)
		(walkHandler addToFront: target)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						67
						50
						71
						101
						93
						139
						91
						167
						72
						146
						72
						138
						82
						116
						81
						103
						66
						112
						54
						132
						48
						132
						42
						108
						42
						106
						51
						86
						65
						69
						61
						0
						60
						0
						0
						319
						0
						319
						62
						296
						70
						239
						67
						248
						94
						319
						91
						319
						189
						0
						189
					yourself:
				)
		)
		(super init:)
		(target init:)
		(globalSound number: 742 setLoop: -1 play: 127)
		(waterFall init:)
		(rightVines init:)
		(lowPath init:)
		(upperPath init:)
		(mist setCycle: Fwd init:)
		(vine init: stopUpd:)
		(vine2 init: stopUpd:)
		(egoTell init: ego @local50 @local59)
		(manuTell init: (ScriptID 41 1) @local61 @local66)
		(ego
			x: -15
			y: 60
			noun: 1
			init:
			setScale: Scaler 100 60 189 60
			normalize:
		)
		((ScriptID 41 1)
			ignoreActors: 1
			x: -15
			y: 50
			noun: 3
			setScale: 0
			init:
			stopUpd:
		)
		(jungle init:)
		(curRoom setScript: enterRoom)
		(theGame save: 1)
	)
	
	(method (doit)
		(CyclePalette 227 234 -1)
		(if (and (GameIsRestarting) (== local47 1))
			(Graph grDRAW_LINE 36 124 36 283 0 -1 -1)
			(Graph grUPDATE_BOX 35 123 37 284 1)
		)
		(if
			(and
				(not (== (globalSound number?) 742))
				(== (globalSound prevSignal?) -1)
			)
			(globalSound number: 742 setLoop: -1 play:)
		)
		(cond 
			(script)
			((<= (ego x?) 10) (curRoom setScript: manuWarn))
			((>= (ego x?) 310) (curRoom setScript: exitRoom))
		)
		(super doit:)
	)
	
	(method (dispose)
		(mist setCycle: 0)
		(LoadMany 0 964 41 942 57)
		(walkHandler delete: target)
		(walkHandler delete: bridge)
		(cSound stop:)
		(globalSound stop:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(84
				(cond 
					((== ((ScriptID 41 1) script?) manuPulls) (manuPulls cue:))
					((and local43 local45 local46) (curRoom setScript: leviWVine))
					((== heroType 1) (curRoom setScript: leviNotMonks))
					(else (super doVerb: theVerb))
				)
			)
			(81 (messager say: 2 6 35))
			(83 (messager say: 2 6 35))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (notify param1)
		(if (and (== param1 -15) local47)
			(Graph grDRAW_LINE 36 124 36 283 0 -1 -1)
			(Graph grUPDATE_BOX 35 123 37 284 1)
		)
	)
)

(instance thiefCrossBridge of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 5))
			(1
				(ego setMotion: PolyPath 132 46 self)
			)
			(2
				(ego
					view: 12
					setCycle: Fwd
					setLoop: 0
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 132 36 self
				)
			)
			(3
				(ego setMotion: MoveTo 264 36 self)
			)
			(4
				(ego view: 0 setLoop: -1 normalize:)
				(bridge dispose:)
				(self setScript: exitRoom)
			)
		)
	)
)

(instance manuPulls of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (or (== state 6) (== state 7))
			(egoVine x: (ego x?))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego normalize:)
				(if (ego has: 26)
					(self cue:)
				else
					(messager say: 1 34 4 0 self)
				)
			)
			(1
				(ego setMotion: PolyPath 164 77 ignoreActors: 1)
				((ScriptID 41 1) setMotion: PolyPath 150 78 self)
			)
			(2
				((ScriptID 41 1) setMotion: JumpTo 256 82 self)
				(messager say: 2 6 26)
			)
			(3
				(egoVine ignoreActors: 1 setLoop: 1 setPri: 1 init:)
				((ScriptID 41 1) view: 741 loop: 4 setCycle: End self)
				(= local49 1)
				(messager say: 2 6 28)
				(ego drop: 24 -1)
			)
			(4 (DontMove))
			(5
				(HandsOff)
				(ego
					view: 17
					setLoop: 0
					setCycle: End self
					setMotion: MoveTo 164 (ego y?)
				)
			)
			(6
				((ScriptID 41 1) setCycle: Fwd)
				(ego
					xStep: 3
					moveSpeed: 3
					setMotion: MoveTo 240 (ego y?) self
				)
			)
			(7
				((ScriptID 41 1)
					view: 985
					setCycle: 0
					setLoop: -1
					loop: 1
				)
				(walkHandler delete: target)
				(target dispose:)
				(egoVine dispose:)
				(ego setCycle: Beg setMotion: MoveTo 240 77 self)
			)
			(8
				(globalSound number: 742 setLoop: -1 play: 60)
				(ego view: 0 normalize:)
				(self setScript: exitRoom)
				(self dispose:)
			)
		)
	)
)

(instance leviVine of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local46 1)
				(ego drop: 24)
				(ego setMotion: PolyPath 164 77)
				((ScriptID 41 1) setMotion: PolyPath 169 77 self)
			)
			(1
				((ScriptID 41 1) setMotion: JumpTo 259 78 self)
			)
			(2
				(messager say: 2 6 26)
				(egoVine ignoreActors: 1 setLoop: 1 setPri: 1 init:)
				((ScriptID 41 1) view: 741 loop: 4 setScale: cel: 0)
				(ego drop: 24 -1)
				(= cycles 1)
			)
			(3
				(globalSound number: 742 setLoop: -1 play: 60)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance leviWVine of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theIconBar disable:)
				(ego
					view: 17
					setLoop: 0
					setCycle: End self
					setMotion: MoveTo 164 70
				)
				(egoVine setMotion: MoveTo 164 70)
			)
			(1
				((ScriptID 41 1) setLoop: 4 setCycle: Fwd)
				(ego xStep: 3 moveSpeed: 3 setMotion: MoveTo 240 70 self)
				(egoVine xStep: 3 moveSpeed: 3 setMotion: MoveTo 240 70)
			)
			(2
				((ScriptID 41 1)
					view: 985
					setCycle: 0
					setLoop: -1
					loop: 1
				)
				(egoVine dispose:)
				(ego setCycle: Beg self setMotion: MoveTo 240 77)
			)
			(3
				(globalSound number: 742 setLoop: -1 play: 60)
				(ego view: 0 normalize:)
				(ego drop: 24 2)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance leviNotMonks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 31 0) init:)
				(= seconds 5)
			)
			(1
				(messager say: 2 6 25 0 self)
			)
			(2
				(globalSound number: 742 setLoop: -1 play: 60)
				(theIconBar enable:)
				(self dispose:)
			)
		)
	)
)

(instance setBridge of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 41 1) setMotion: PolyPath 150 78 self)
			)
			(1
				((ScriptID 41 1)
					view: 741
					loop: 0
					cel: 0
					setCycle: End
					setMotion: JumpTo 256 82 self
				)
			)
			(2 (= seconds 2))
			(3
				((ScriptID 41 1)
					view: 985
					setCycle: Walk
					setMotion: MoveTo 247 62 self
				)
			)
			(4
				((ScriptID 41 1)
					view: 741
					loop: 3
					cel: 0
					setCycle: End self
				)
			)
			(5
				((ScriptID 41 1) loop: 2 cel: 0 setCycle: End self)
			)
			(6
				((ScriptID 41 1) loop: 5 cel: 0 setCycle: End self)
			)
			(7
				((ScriptID 41 1) setCycle: Beg self)
			)
			(8
				(Graph grDRAW_LINE 36 124 36 283 0 -1 -1)
				(Graph grUPDATE_BOX 35 123 37 284 1)
				(ego drop: 41)
				(theIconBar advanceCurIcon:)
				(bridge approachVerbs: 4 init:)
				(walkHandler add: bridge)
				(= cycles 1)
			)
			(9 (messager say: 2 6 2 0 self))
			(10
				((ScriptID 41 1) setPri: 1)
				(= local47 1)
				(self dispose:)
			)
		)
	)
)

(instance setVine of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 41 1) setMotion: PolyPath 150 78 self)
			)
			(1
				((ScriptID 41 1)
					view: 741
					loop: 0
					cel: 0
					setCycle: End
					setMotion: JumpTo 256 82 self
				)
			)
			(2 (= seconds 2))
			(3
				((ScriptID 41 1)
					view: 985
					setCycle: Walk
					setMotion: MoveTo 271 52 self
				)
			)
			(4
				((ScriptID 41 1)
					view: 741
					loop: 3
					cel: 0
					setCycle: End self
				)
			)
			(5
				((ScriptID 41 1) loop: 2 cel: 0 setCycle: End self)
			)
			(6
				((ScriptID 41 1) loop: 5 cel: 0 setCycle: End self)
			)
			(7
				((ScriptID 41 1) setCycle: Beg self)
			)
			(8
				(Graph grDRAW_LINE 36 124 36 283 0 -1 -1)
				(Graph grUPDATE_BOX 35 123 37 284 1)
				(ego drop: 24 2)
				(bridge approachVerbs: 4 init:)
				(ego solvePuzzle: 324 3 9)
				(walkHandler add: bridge)
				(= cycles 1)
			)
			(9
				((ScriptID 41 1)
					view: 985
					loop: 0
					setPri: 1
					setCycle: Walk
				)
				(= local47 1)
				(self dispose:)
			)
		)
	)
)

(instance jumpScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: PolyPath 150 78 self)
			)
			(1
				(client
					view: 741
					loop: 0
					cel: 0
					setCycle: End
					setMotion: JumpX 256 (Random 15 25) self
				)
			)
			(2
				(client
					view: 985
					setCycle: Walk
					setMotion: MoveTo 350 66 self
				)
			)
			(3 (client dispose:))
		)
	)
)

(instance enterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local41 0)
				(while (< local41 9)
					(= [newJumpScript local41] (jumpScript new:))
					((= [newMonkey local41] (monkey new:))
						x: (- (monkey x?) (* 25 local41))
						setLoop: 0
						setCycle: Walk
						cycleSpeed: 3
						moveSpeed: 3
						xStep: 5
						init:
						setScript: [newJumpScript local41]
					)
					(++ local41)
				)
				(= seconds 7)
			)
			(1
				(ego setStep: 3 2 setMotion: PolyPath 38 66 self)
				((ScriptID 41 1)
					setCycle: Walk
					setMotion: PolyPath 42 60
				)
			)
			(2
				(messager say: 3 6 12 0 self)
			)
			(3
				(theIconBar enable:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance manuWarn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath (+ (ego x?) 10) (ego y?) self)
			)
			(1
				(messager say: 3 6 13)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance throwGrap of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 163 75 self)
			)
			(1
				(ego view: 8 loop: 0 cel: 0 setCycle: CT 6 1 self)
			)
			(2
				(ego setCycle: End)
				(rope init: setCycle: End self)
				(sFx number: 721 play:)
			)
			(3
				(ego view: 4 setCycle: End self)
				(ego drop: 6)
			)
			(4
				(ego view: 0 setCycle: Walk)
				(= local47 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance exitRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego has: 24) (ego drop: 24))
				(ego
					solvePuzzle: 325 10
					setMotion: PolyPath 330 (ego y?) self
				)
			)
			(1 (= seconds 1))
			(2 (curRoom newRoom: 180))
		)
	)
)

(instance attemptBridge of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 5))
			(1
				(ego setMotion: PolyPath 132 46 self)
			)
			(2
				(ego
					view: 12
					setCycle: Fwd
					setLoop: 0
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 132 36 self
				)
			)
			(3
				(ego view: 6 setLoop: 4 setCycle: MCyc @local0 self)
			)
			(4 (ego setCycle: End self))
			(5
				(switch local42
					(0 (messager say: 2 6 20))
					(1 (messager say: 2 6 21))
				)
				(++ local42)
				(= cycles 1)
			)
			(6
				(ego setLoop: 10 cel: 0 setCycle: End self)
			)
			(7
				(ego
					cycleSpeed: 3
					moveSpeed: 3
					setLoop: -1
					changeGait: 0
					normalize:
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoDeathFall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 5))
			(1
				(ego setMotion: PolyPath 120 36 self)
			)
			(2
				(ego
					view: 12
					setCycle: Fwd
					setLoop: 0
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 209 36 self
				)
			)
			(3 (self setScript: egoFalls))
		)
	)
)

(instance egoFalls of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 6 setLoop: 4 setCel: 0 setCycle: End self)
			)
			(1 (= seconds 2))
			(2
				(sFx number: 912 play:)
				(ego
					moveSpeed: 0
					setStep: 4 4
					setScale: Scaler 25 75 189 60
					setCycle: 0
					setMotion: MoveTo 209 200 self
				)
			)
			(3
				(EgoDead 22 0 744 Fwd)
				(self dispose:)
			)
		)
	)
)

(instance thiefCrossRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 169 79 self)
			)
			(1
				(ego
					view: 11
					y: 79
					setLoop: 2
					setCycle: Fwd
					setMotion: MoveTo 264 79 self
				)
			)
			(2
				(ego view: 0 setLoop: -1 normalize:)
				(messager say: 2 6 38 0 self)
			)
			(3
				(walkHandler delete: target)
				(bridge dispose:)
				(ego get: 6)
				(self setScript: exitRoom)
			)
		)
	)
)

(instance getVine of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 44 70 self)
			)
			(1
				(ego view: 31 cel: 0 setCycle: CT 3 1 self)
			)
			(2
				(if (== ((inventory at: 24) state?) 0)
					(ego get: 24)
					((inventory at: 24) state: 1)
					(messager say: 2 6 23)
					(vine dispose:)
				else
					(ego get: 24)
					(messager say: 2 6 24)
					((inventory at: 24) state: 2)
					(vine2 dispose:)
				)
				(= cycles 1)
			)
			(3
				(if (== ((inventory at: 24) state?) 2)
					(messager say: 2 6 9 0 self)
				else
					(self cue:)
				)
			)
			(4 (ego setCycle: End self))
			(5
				(ego view: 0 normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance crossBridge of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 166 71 self)
			)
			(1
				(ego
					view: 39
					y: 35
					setCycle: Fwd
					setMotion: MoveTo 265 35 self
				)
			)
			(2
				(ego view: 0 normalize: y: 75)
				(= seconds 2)
			)
			(3
				(messager say: 3 6 14)
				(walkHandler delete: target)
				(bridge dispose:)
				(self setScript: exitRoom)
			)
		)
	)
)

(instance attemptVine of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 166 71 self)
			)
			(1
				(ego view: 39 loop: 0 cel: 0 y: 35)
				(= ticks 120)
			)
			(2
				(ego view: 0 y: 71)
				(messager say: 2 6 33)
				(= cycles 1)
			)
			(3 (self dispose:))
		)
	)
)

(instance jumpToDoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego moveSpeed: 2 setMotion: PolyPath 166 78 self)
			)
			(1
				(ego setMotion: JumpTo 185 66 self)
			)
			(2
				(sFx number: 912 play:)
				(ego
					view: 6
					setLoop: 4
					moveSpeed: 0
					setCycle: 0
					setScale: Scaler 0 75 189 60
					setMotion: MoveTo 185 162 self
				)
			)
			(3
				(EgoDead 17 0 744 Fwd)
				(self dispose:)
			)
		)
	)
)

(instance monkey of Actor
	(properties
		x -10
		y 60
		view 985
		signal $4000
	)
)

(instance rope of Prop
	(properties
		x 170
		y 79
		view 51
		signal $4000
	)
	
	(method (init)
		(super init:)
		(walkHandler addToFront: self)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (< (ego x?) 250)
					(curRoom setScript: thiefCrossRope)
				else
					(ego get: 6)
					(self dispose:)
				)
			)
			(3
				(if (< (ego x?) 250)
					(curRoom setScript: thiefCrossRope)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance mist of Prop
	(properties
		x 202
		y 172
		view 740
		loop 1
		signal $4000
		detailLevel 3
	)
)

(instance vine of View
	(properties
		noun 7
		view 740
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(ego setScript: getVine 0 vine)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vine2 of View
	(properties
		x 29
		noun 7
		view 740
		cel 1
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(ego setScript: getVine 0 vine2)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance egoVine of Actor
	(properties
		x 161
		y 74
		view 51
		signal $4000
	)
)

(instance target of Feature
	(properties
		x 252
		y 76
		noun 10
		nsLeft 233
		nsBottom 200
		nsRight 320
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(16
				(if (not local47) (curRoom setScript: throwGrap))
			)
			(52 (messager say: 2 6 19))
			(3
				(if (== egoGait 1)
					(ego setScript: jumpToDoom)
				else
					(messager say: 2 6 18)
					(ego setMotion: 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bridge of Feature
	(properties
		x 200
		y 38
		nsTop 31
		nsLeft 133
		nsBottom 43
		nsRight 285
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((curRoom script?) 0)
					((or (== heroType 2) (== heroType 1))
						(if (> (ego trySkill: 0 250) 0)
							(curRoom setScript: crossBridge)
						else
							(curRoom setScript: attemptVine)
						)
					)
					(else (curRoom setScript: crossBridge))
				)
			)
			(3
				(cond 
					((curRoom script?) 0)
					((== heroType 2) (curRoom setScript: thiefCrossBridge))
					((< local42 2) (curRoom setScript: attemptBridge))
					(else (curRoom setScript: egoDeathFall))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance waterFall of Feature
	(properties
		x 180
		y 1
		noun 5
		sightAngle 40
		onMeCheck $4000
	)
)

(instance jungle of Feature
	(properties
		x 28
		y 168
		noun 6
		nsTop 148
		nsBottom 189
		nsRight 56
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rightVines of Feature
	(properties
		x 221
		y 26
		noun 9
		nsTop -1
		nsLeft 196
		nsBottom 54
		nsRight 247
		sightAngle 180
	)
)

(instance lowPath of Feature
	(properties
		x 138
		y 80
		noun 11
		nsTop 68
		nsLeft 106
		nsBottom 93
		nsRight 171
		sightAngle 180
	)
)

(instance upperPath of Feature
	(properties
		x 122
		y 48
		noun 12
		nsTop 35
		nsLeft 100
		nsBottom 62
		nsRight 144
		sightAngle 180
	)
)

(instance sFx of Sound
	(properties)
)

(instance manuTell of Teller
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2 (super doVerb: theVerb))
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance egoTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-4
				(if (== heroType 1) (< (ego x?) 240) else 0)
				-10
				(== local48 1)
				-6
				(if
					(and
						(< (ego x?) 240)
						(ego has: 24)
						(> ((inventory at: 24) state?) 0)
						(not local47)
					)
					(not (== heroType 2))
				else
					0
				)
				-2
				(if
					(and
						(or (ego has: 41) (ego has: 26))
						(not local47)
						(not local46)
					)
					(not (== heroType 2))
				else
					0
				)
				-7
				(== local49 1)
		)
	)
	
	(method (doChild)
		(switch query
			(-2
				(cond 
					((== heroType 1) (= local47 1) ((ScriptID 41 1) setScript: manuPulls))
					((and (not local47) (not local46)) (curRoom setScript: setBridge))
				)
			)
			(-4 (= local48 1))
			(-10 (= local43 1))
			(-6
				(cond 
					((== heroType 1) (= local47 1) ((ScriptID 41 1) setScript: manuPulls))
					((not local47) (= local47 1) (curRoom setScript: setVine) (= query -5))
				)
			)
			(-7
				(= local49 0)
				(theIconBar enable: 6)
				((ScriptID 41 1) setCycle: Beg)
			)
		)
		(return 1)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(2 (super doVerb: theVerb))
			(34
				(if (== heroType 1)
					(messager say: 2 6 37)
					(= local46 1)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (ego doVerb: theVerb))
		)
	)
)
