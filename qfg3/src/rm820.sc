;;; Sierra Script 1.0 - (do not remove this comment)
(script# 820)
(include sci.sh)
(use Main)
(use Target)
(use EgoDead)
(use GloryTalker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm820 0
	demonaTalker 1
)

(local
	local0 =  1
	local1 =  1
	local2
	local3
	local4
)
(instance rm820 of Rm
	(properties
		noun 3
		picture 820
		vanishingY -20
	)
	
	(method (init)
		(LoadMany 128 820 821 822)
		(ego x: 122 y: 41 setScale: 150 normalize:)
		(HandsOn)
		(super init:)
		(cSound number: 820 setLoop: -1 play: 127)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						225
						36
						218
						43
						148
						36
						37
						70
						32
						81
						9
						82
						3
						92
						3
						186
						115
						186
						83
						173
						57
						152
						51
						134
						56
						115
						68
						95
						52
						87
						73
						74
						88
						79
						140
						56
						187
						62
						236
						66
						282
						71
						316
						58
						316
						50
						231
						44
						237
						38
					yourself:
				)
		)
		(brazier init:)
		(brazier2 init:)
		(brazier3 init:)
		(brazier4 init:)
		(doorWay init:)
		(stairs init:)
		(floor init:)
		(visage init:)
		(flame1 setCycle: Fwd init:)
		(flame2 setPri: 6 setCycle: Fwd init:)
		(flame3 setPri: 3 setCycle: Fwd init:)
		(flame4 setPri: 5 setCycle: Fwd init:)
		(door ignoreActors: 0 init:)
		(cond 
			((not (== prevRoomNum 550))
				(frac init: stopUpd:)
				(fric init: stopUpd:)
				(if (== heroType 2)
					(ego code: thiefGaitChek)
				else
					(ego code: demonTurnChek)
				)
				(curRoom setScript: egoEnters)
			)
			((and (== prevRoomNum 550) (== battleResult 0)) (fric init: stopUpd:) (curRoom setScript: knockEmDead))
			(
			(and (== prevRoomNum 550) (not (== battleResult 0)))
				(fric x: 156 y: 67 view: 824 signal: 1 init:)
				(ego x: 212 y: 61 init: solvePuzzle: 335 7 9)
				(curRoom setScript: afterFight)
			)
			(else (curRoom setScript: egoEnters))
		)
		(if
		(and (== heroType 3) (> (ego trySkill: 4 100) 0))
			(messager say: 1 6 1)
		)
	)
	
	(method (dispose)
		(ego code: 0)
		(if gNewList (gNewList eachElementDo: #dispose))
		(UnLoad 128 820)
		(UnLoad 128 821)
		(UnLoad 128 822)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(65 (messager say: 1 6 9))
			(80 (self setScript: castCalm))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance closeCombat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 2))
			(1
				(= monsterHealth 320)
				(= monsterNum 845)
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance lubeLock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 230 40 self)
			)
			(1
				(ego solvePuzzle: 332 5 4)
				(messager say: 4 35 0 0 self)
			)
			(2
				(= local0 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance afterFight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(if (== battleResult 0) (EgoDead) else (self cue:))
			)
			(2
				(globalSound number: 931 setLoop: 1 play: 127)
				(= local1 0)
				(= local0 0)
				(= local3 1)
			)
		)
	)
)

(instance demonsDissolve of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 1))
			(1
				(fric dispose:)
				(DrawPic (curRoom picture?) dpOPEN_PIXELATION)
				(= cycles 4)
			)
			(2
				(= local1 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance castCalm of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(self setScript: (ScriptID 12 0) self)
			)
			(1
				(messager say: 3 80 0 0 self)
			)
			(2
				(= local1 0)
				(ego solvePuzzle: 333 6 2 code: 0)
				(self dispose:)
			)
		)
	)
)

(instance demonsTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (fric setCycle: End self))
			(1 (messager say: 2 6 3 0 self))
			(2 (fric setCycle: Beg self))
			(3 (frac setCycle: End self))
			(4
				(frac setCycle: Beg)
				(self dispose:)
			)
		)
	)
)

(instance demonsTurn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: 2 6 5 0 self)
			)
			(1
				(fric loop: 2 setCycle: End)
				(frac loop: 3 setCycle: End self)
			)
			(2 (messager say: 2 6 6 0 self))
			(3
				(= monsterNum 845)
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance tryToPickLock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 230 40 self)
			)
			(1
				(if local3
					(messager say: 4 17 12 0 self)
				else
					(ego useSkill: 9 200)
					(= local3 1)
					(messager say: 4 17 11)
					(HandsOn)
					(self dispose:)
				)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance castOpenOnDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(AutoTarget 235 20)
				(self setScript: (ScriptID 13 0) self)
			)
			(1
				(ego solvePuzzle: 334 4 2)
				(messager say: 4 75 0 0 self)
			)
			(2
				(= local3 1)
				(= local0 0)
				(HandsOn)
				(ego normalize:)
				(self dispose:)
			)
		)
	)
)

(instance hearNoise of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local2
					(messager say: 2 6 8 0 self)
				else
					(messager say: 2 6 7 0 self)
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance messWithDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 230 40 self)
			)
			(1
				(cond 
					((== local1 1) (fric setScript: demonsTurn))
					((== local3 0) (messager say: 4 4 10) (HandsOn) (self dispose:))
					((== local0 1) (fric setScript: demonsTurn))
					(else
						(if (cast contains: frac)
							(= local2 0)
							(self setScript: hearNoise self)
						)
						(= cycles 1)
					)
				)
			)
			(2
				(door setCycle: End self ignoreActors: 1)
				(globalSound number: 821 setLoop: 1 play:)
			)
			(3
				(ego setMotion: MoveTo 230 30 self)
			)
			(4
				(if (not local4) (ego solvePuzzle: 331 5 4))
				(curRoom newRoom: 830)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(secretDoor init: setCycle: End self)
				(globalSound number: 8 setLoop: 1 play: 127)
			)
			(1
				(ego init: setMotion: MoveTo 129 51 self)
			)
			(2
				(fric setScript: demonsTalk self)
			)
			(3
				(secretDoor setCycle: Beg self)
			)
			(4
				(if (Btst 150)
					(messager say: 1 6 2 0 self)
				else
					(self cue:)
				)
			)
			(5
				(secretDoor dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance theyAttack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: 2 6 4 0 self)
			)
			(1
				(= monsterNum 845)
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance knockEmDead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 1))
			(1 (EgoDead))
		)
	)
)

(instance flame1 of Prop
	(properties
		x 20
		y 43
		view 820
		loop 2
		signal $4000
		detailLevel 3
	)
)

(instance flame2 of Prop
	(properties
		x 74
		y 53
		view 820
		loop 2
		cel 2
		signal $4000
		detailLevel 3
	)
)

(instance flame3 of Prop
	(properties
		x 157
		y 33
		view 820
		loop 2
		cel 1
		signal $4000
		detailLevel 3
	)
)

(instance flame4 of Prop
	(properties
		x 258
		y 44
		view 820
		loop 2
		cel 4
		signal $4000
		detailLevel 3
	)
)

(instance secretDoor of Prop
	(properties
		x 122
		y 42
		noun 12
		view 820
		signal $4000
	)
)

(instance door of Prop
	(properties
		x 222
		y 35
		noun 4
		view 820
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(ego setScript: messWithDoor)
			)
			(75
				(ego setScript: castOpenOnDoor)
			)
			(35 (ego setScript: lubeLock))
			(17
				(ego setScript: tryToPickLock)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fric of TargProp
	(properties
		x 154
		y 103
		noun 2
		view 821
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not (== prevRoomNum 550))
					(curRoom setScript: theyAttack)
				else
					(messager say: 1 6 13)
				)
			)
			(20
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID 32 0) 0 theVerb)
				)
			)
			(81
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID 32 0) 0 theVerb)
				)
			)
			(88
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID 32 0) 0 theVerb)
				)
			)
			(83
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID 32 0) 0 theVerb)
				)
			)
			(33
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID 32 0) 0 theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (getHurt)
		(curRoom setScript: closeCombat)
	)
)

(instance frac of TargProp
	(properties
		x 193
		y 110
		noun 13
		view 821
		loop 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not (== prevRoomNum 550))
					(curRoom setScript: theyAttack)
				else
					(messager say: 1 6 13)
				)
			)
			(20
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID 32 0) 0 theVerb)
				)
			)
			(81
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID 32 0) 0 theVerb)
				)
			)
			(88
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID 32 0) 0 theVerb)
				)
			)
			(83
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID 32 0) 0 theVerb)
				)
			)
			(33
				(if (not (curRoom script?))
					(curRoom setScript: (ScriptID 32 0) 0 theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (getHurt)
		(curRoom setScript: closeCombat)
	)
)

(instance demonaTalker of GloryTalker
	(properties
		x 10
		y 10
		view 822
		loop 1
		talkWidth 260
		back 57
		textX 15
		textY 100
		backColor 42
	)
	
	(method (init)
		(super init: demonaBust demonaEyes demonaMouth &rest)
	)
)

(instance demonaMouth of Prop
	(properties
		nsTop 31
		nsLeft 60
		view 822
	)
)

(instance demonaEyes of Prop
	(properties
		nsTop 24
		nsLeft 49
		view 822
		loop 2
	)
)

(instance demonaBust of View
	(properties
		nsTop 14
		nsLeft 53
		view 822
		loop 3
	)
)

(instance brazier of Feature
	(properties
		x 23
		y 58
		noun 5
		nsTop 45
		nsLeft 14
		nsBottom 71
		nsRight 33
		sightAngle 180
	)
)

(instance brazier2 of Feature
	(properties
		x 76
		y 64
		noun 6
		nsTop 48
		nsLeft 67
		nsBottom 81
		nsRight 86
		sightAngle 180
	)
)

(instance brazier3 of Feature
	(properties
		x 158
		y 42
		noun 7
		nsTop 34
		nsLeft 149
		nsBottom 51
		nsRight 168
		sightAngle 180
	)
)

(instance brazier4 of Feature
	(properties
		x 260
		y 56
		noun 8
		nsTop 47
		nsLeft 251
		nsBottom 66
		nsRight 269
		sightAngle 180
	)
)

(instance doorWay of Feature
	(properties
		x 229
		y 21
		noun 9
		nsTop 1
		nsLeft 205
		nsBottom 41
		nsRight 254
		sightAngle 180
	)
)

(instance floor of Feature
	(properties
		x 189
		y 155
		noun 10
		nsTop 134
		nsLeft 148
		nsBottom 177
		nsRight 230
		sightAngle 180
	)
)

(instance stairs of Feature
	(properties
		x 44
		y 136
		noun 11
		nsTop 84
		nsBottom 189
		nsRight 88
		sightAngle 180
	)
)

(instance visage of Feature
	(properties
		x 291
		y 145
		noun 14
		nsTop 114
		nsLeft 270
		nsBottom 177
		nsRight 313
		sightAngle 180
	)
)

(instance thiefGaitChek of Code
	(properties)
	
	(method (doit)
		(if (== egoGait 2) (= local1 0) else (= local1 1))
		(if
			(and
				(not (curRoom script?))
				(== local1 1)
				(ego inRect: 180 20 200 80)
			)
			(= local4 1)
			(curRoom setScript: demonsTurn)
		)
	)
)

(instance demonTurnChek of Code
	(properties)
	
	(method (doit)
		(if
			(and
				(not (curRoom script?))
				(== local1 1)
				(or
					(ego inRect: 180 20 200 80)
					(ego inRect: 0 80 135 189)
				)
			)
			(curRoom setScript: demonsTurn)
		)
	)
)
