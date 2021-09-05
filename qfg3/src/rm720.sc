;;; Sierra Script 1.0 - (do not remove this comment)
(script# 720)
(include game.sh)
(use Main)
(use TellerIcon)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm720 0
)

(local
	local0
	local1
	[local2 5] = [0 9 -8 10 999]
	[local7 2]
	[local9 7] = [0 4 1 2 3 -5 999]
	[local16 3] = [0 -6 999]
	[local19 3] = [0 -7 999]
	[local22 3] = [0 -8 999]
	[local25 5] = [0 -5 -6 -7 999]
	[local30 5]
)
(instance rm720 of Room
	(properties
		picture 720
	)
	
	(method (init)
		(super init:)
		(cSound number: 720 setLoop: -1 play: 127)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						0
						319
						0
						319
						128
						237
						127
						211
						111
						171
						111
						156
						127
						192
						147
						187
						157
						91
						160
						112
						135
						85
						133
						79
						150
						58
						150
						46
						140
						0
						143
					yourself:
				)
		)
		(= [local7 0] @local2)
		(= [local30 0] @local9)
		(= [local30 1] @local16)
		(= [local30 2] @local19)
		(= [local30 3] @local22)
		(manuTell init: funkyMonkey @local9 @local30 @local25)
		(egoTell init: ego @local2 @local7)
		(ego x: -10 y: 160 setScale: 189 normalize: noun: 2 init:)
		((ScriptID 41 1)
			x: -10
			y: 170
			setScale: 189
			noun: 12
			init:
		)
		(tree init:)
		(treeTop init: approachVerbs: 4)
		(tree init:)
		(leaves init:)
		(otherTree init:)
		(notherTree init:)
		(threeTree init:)
		(fourTree init:)
		(fern init:)
		(roots init:)
		(rocks init:)
		(funkyMonkey init:)
		(curRoom setScript: enterRoom)
	)
	
	(method (doit)
		(if
		(and (> (ego z?) 80) (== script (ScriptID 31 1)))
			(= useObstacles 1)
			(ego solvePuzzle: 321 8)
			(curRoom newRoom: 730)
		)
		(cond 
			(script 0)
			((> (ego x?) 10) 0)
			((Btst 14) (curRoom setScript: blockEgo))
			((not (Btst 14)) (curRoom newRoom: 180))
		)
		(super doit:)
	)
	
	(method (dispose)
		(funkyMonkey dispose:)
		(LoadMany 0 41 31)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(84
				(if local1
					(curRoom setScript: byeByeLance)
				else
					((ScriptID 31 0) init: 187 131)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance byeByeLance of Script
	(properties)
	
	(method (doit)
		(if (< (ego y?) 5)
			(ego priority: -1)
			(ego solvePuzzle: 321 8)
			(curRoom newRoom: 730)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 160 137 self)
			)
			(1
				(messager say: 3 6 13 0 self)
			)
			(2
				(sFx number: 945 play:)
				(ego
					view: 731
					setPri: 10
					setCycle: Forward
					setMotion: MoveTo 160 0 self
				)
			)
		)
	)
)

(instance enterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setScale: 0 setMotion: PolyPath 50 160 self)
			)
			(1
				((ScriptID 41 1) stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoClimb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 95 137 self)
			)
			(1
				(ego setPri: 10 view: 7 loop: 2 cel: 1)
				(= cycles 1)
			)
			(2
				(sFx number: 928 play:)
				(ego
					loop: 3
					cel: 0
					yStep: 3
					setCycle: Forward
					setMotion: MoveTo 95 49 self
				)
			)
			(3
				(ego yStep: 2 setPri: -1 normalize: 111)
				(= cycles 1)
			)
			(4
				(ego solvePuzzle: 321 8)
				(curRoom newRoom: 730)
			)
		)
	)
)

(instance egoTryClimb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 95 137 self)
			)
			(1
				(ego setPri: 10 view: 7 loop: 2 cel: 1)
				(= cycles 1)
			)
			(2
				(sFx number: 928 play:)
				(ego loop: 3 cel: 0 setCycle: Forward cycleSpeed: 3)
				(= ticks 120)
			)
			(3
				(ego view: 0 y: 137 setPri: -1 normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance dropRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ladder init: setCycle: EndLoop self)
			)
			(1
				(sFx number: 920 play:)
				(ego setMotion: PolyPath 187 131 self)
			)
			(2
				(ego view: 7 loop: 2 cel: 1 y: 86)
				(= cycles 1)
			)
			(3
				(ego solvePuzzle: 321 8)
				(curRoom newRoom: 730)
			)
		)
	)
)

(instance climbRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 215 155 self ignoreActors: 1)
			)
			(1
				(ego view: 8 loop: 1 cel: 0 setCycle: CycleTo 6 1 self)
			)
			(2
				(ego setCycle: EndLoop)
				(rope setPri: 0 setCycle: EndLoop self init:)
				(sFx number: 721 play:)
			)
			(3
				(ego
					view: 0
					setCycle: Walk
					setMotion: PolyPath 190 145 self
				)
			)
			(4
				(ego view: 7 setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(5
				(sFx number: 928 play:)
				(ego
					cel: 1
					setPri: 2
					y: 100
					yStep: 3
					setCycle: Forward
					setMotion: MoveTo 193 0 self
				)
			)
			(6
				(ego normalize: dispose:)
				(= cycles 1)
			)
			(7
				(ego solvePuzzle: 321 8)
				(curRoom newRoom: 730)
			)
		)
	)
)

(instance blockEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: 1 6 10)
				(ego setMotion: PolyPath 50 160 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance funkyMonkey of Prop
	(properties
		x 40
		y 69
		noun 1
		view 988
		loop 3
		signal $4000
		cycleSpeed 10
		detailLevel 3
	)
	
	(method (doit)
		(if
		(and (not (self cycler?)) (== (Random 1 120) 5))
			(self loop: (Random 0 3) cel: 0 setCycle: EndLoop)
			(sFx number: 929 play:)
		)
		(super doit:)
	)
)

(instance rope of Prop
	(properties
		x 190
		y 103
		view 50
		signal $4000
	)
)

(instance ladder of Prop
	(properties
		x 188
		y 27
		view 720
		signal $4000
	)
)

(instance treeTop of Feature
	(properties
		x 144
		y 16
		nsTop 5
		nsLeft 56
		nsBottom 35
		nsRight 233
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(16
				(if (not (curRoom script?))
					(curRoom setScript: climbRope)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance leaves of Feature
	(properties
		x 137
		y 13
		noun 9
		nsTop 1
		nsLeft 33
		nsBottom 26
		nsRight 241
		sightAngle 180
	)
)

(instance otherTree of Feature
	(properties
		x 274
		y 40
		noun 5
		nsTop 9
		nsLeft 254
		nsBottom 72
		nsRight 295
		sightAngle 180
	)
)

(instance notherTree of Feature
	(properties
		x 179
		y 53
		noun 6
		nsTop 27
		nsLeft 168
		nsBottom 80
		nsRight 191
		sightAngle 180
	)
)

(instance threeTree of Feature
	(properties
		x 139
		y 55
		noun 7
		nsTop 27
		nsLeft 130
		nsBottom 83
		nsRight 149
		sightAngle 180
	)
)

(instance fourTree of Feature
	(properties
		x 24
		y 48
		noun 8
		nsLeft 4
		nsBottom 97
		nsRight 44
		sightAngle 180
	)
)

(instance fern of Feature
	(properties
		x 64
		y 172
		noun 10
		nsTop 159
		nsLeft 31
		nsBottom 186
		nsRight 98
		sightAngle 180
	)
)

(instance roots of Feature
	(properties
		x 140
		y 139
		noun 11
		nsTop 126
		nsLeft 108
		nsBottom 153
		nsRight 173
		sightAngle 180
	)
)

(instance rocks of Feature
	(properties
		x 203
		y 95
		noun 13
		nsTop 88
		nsLeft 176
		nsBottom 103
		nsRight 231
		sightAngle 180
	)
)

(instance tree of Feature
	(properties
		x 95
		y 67
		noun 4
		nsTop 11
		nsLeft 56
		nsBottom 123
		nsRight 134
		sightAngle 40
		approachX 95
		approachY 137
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not (curRoom script?))
					(if [egoStats 11]
						(curRoom setScript: egoClimb)
					else
						(curRoom setScript: egoTryClimb)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sFx of Sound
	(properties)
)

(instance egoTell of Teller
	(properties)
	
	(method (showDialog)
		(super showDialog: -8 (== local0 1))
	)
	
	(method (doChild)
		(return
			(switch query
				(-8
					(curRoom setScript: dropRope)
					(return query)
				)
				(else  (return query))
			)
		)
	)
)

(instance manuTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-5
				(if (not (> [egoStats 28] 0))
					(not (> [egoStats 11] 0))
				else
					0
				)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-8 (= local0 1) (return query))
				(-5 (super doChild: query))
				(-6 (super doChild: query))
				(-7 (super doChild: query))
				(else  (return query))
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(41 (= local1 1))
			(else  (super doVerb: theVerb))
		)
	)
)
