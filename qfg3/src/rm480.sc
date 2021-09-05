;;; Sierra Script 1.0 - (do not remove this comment)
(script# 480)
(include game.sh)
(use Main)
(use TellerIcon)
(use OccasionalCycle)
(use PAvoid)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm480 0
)

(local
	local0
	local1
	local2
	local3 = [0 -30 -72 68 -41 28 999]
	[local10 2]
	local12 = [0 2 -3 -4 9 999]
	local18 = [0 5 6 -7 999]
	local23 = [0 8 999]
	local26 = [0 -4 -7 999]
	local30 = [0 -75 -11 14 15 999]
	local36 = [0 12 999]
	local39 = [0 -11 999]
	local42 = [0 -75 19 18 17 999]
	local48 = [0 23 24 22 21 999]
	local54 = [0 26 -27 29 999]
	local59 = [0 28 999]
	local62 = [0 -27 999]
	[local65 4]
	local69 = [0 -31 -33 -35 39 40 999]
	local76 = [0 32 999]
	local79 = [0 34 999]
	local82 = [0 -73 999]
	local85 = [0 -31 -33 -35 999]
	[local90 5]
	local95 = [0 -40 -76 -77 999]
	[local100 2]
	local102 = [0 -23 -24 -78 999]
	[local107 2]
	local109 = [0 45 46 47 28 999]
	local115 = [0 57 58 -59 60 999]
	[local121 2]
)
(procedure (localproc_1cd0)
	(curRoom
		addObstacle:
			((Polygon new:)
				type: 2
				init: 57 141 4 141 14 118 69 118
				yourself:
			)
	)
	(ego
		normalize:
		x: 173
		y: 144
		setScale: 200
		setHeading: 0
		init:
	)
	(switch global401
		(1
			(= [local65 0] @local12)
			(= [local65 1] @local18)
			(= [local65 2] @local23)
			(yesufuTeller
				init: (ScriptID 39 1) @local12 @local65 @local26
			)
		)
		(3
			(= [local65 0] @local30)
			(= [local65 1] @local36)
			(yesufuTeller
				init: (ScriptID 39 1) @local30 @local65 @local39
			)
		)
		(4
			(= [local65 0] @local42)
			(yesufuTeller init: (ScriptID 39 1) @local42 @local65)
		)
	)
	((ScriptID 39 1)
		view: 982
		loop: 0
		cel: 0
		x: 137
		y: 121
		noun: 6
		setScale: 200
		actions: yesufuTeller
		approachVerbs: 2
		ignoreActors: 1
		approachX: 109
		approachY: 137
		init:
	)
	(yesufuArm setPri: 8 setScale: 200 ignoreActors: 1 init:)
	(HandsOn)
)

(instance rm480 of Room
	(properties
		noun 1
		picture 480
		vanishingY -300
	)
	
	(method (init)
		(HandsOff)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						319
						160
						293
						154
						264
						157
						235
						157
						190
						150
						167
						143
						110
						143
						110
						110
						198
						110
						230
						116
						266
						109
						235
						104
						209
						106
						151
						102
						159
						96
						206
						96
						208
						91
						137
						88
						85
						87
						0
						87
						0
						0
						319
						0
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 247 189 266 185 311 184 319 189
					yourself:
				)
		)
		(awari approachVerbs: 4 init:)
		(cageDoor approachVerbs: 4 stopUpd: init:)
		(theCage approachVerbs: 4 init:)
		(fence init:)
		(tree1 init:)
		(tree2 init:)
		(rock1 init:)
		(rock2 init:)
		(mountain init:)
		(littleRock1 init:)
		(littleRock2 init:)
		(switch heroType
			(0
				(if (and (not (Btst 29)) (Btst 64)) (Bset 29))
			)
			(1
				(if
				(and (not (Btst 29)) (or (ego has: 44) [egoStats STAFF]))
					(Bset 29)
				)
			)
			(2
				(if (and (not (Btst 29)) (Btst 63)) (Bset 29))
			)
			(3
				(if (and (not (Btst 29)) (Btst 64)) (Bset 29))
			)
		)
		(if (and (Btst 29) (not (Btst 65)))
			(cSound number: 480 setLoop: -1 play: 127)
			(if (not (Btst 62))
				(Bset 62)
				(= gCurrentDay_10 (- Day 1))
			)
			(= [local100 0] @local95)
			(johariTeller init: (ScriptID 36 1) @local95 @local100)
			(if (Btst 38)
				((ScriptID 36 1)
					view: 483
					setLoop: 0
					x: 33
					y: 127
					noun: 10
					actions: johariTeller
					approachVerbs: 1 30 12 43 26
					approachX: 24
					approachY: 146
					approachDist: 0
					setCycle: OccasionalCycle self 1 65 150
					init:
				)
				((ScriptID 36 0) x: 195 textY: 0 talkWidth: 130)
			else
				((ScriptID 36 1)
					view: 481
					loop: 1
					x: 33
					y: 132
					noun: 11
					approachVerbs: 1 30 12 43 26
					approachX: 24
					approachY: 146
					approachDist: 0
					actions: johariTeller
					setCycle: OccasionalCycle self 1 65 150
					init:
				)
				(johariHands init:)
			)
			(if
				(and
					(< global401 11)
					(Btst 29)
					(not (Btst 65))
					(or Night (== gCurrentDay_10 Day))
					(not (Btst 129))
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 68 117 52 142 25 142 25 146 34 146 34 154 0 154 0 117
							yourself:
						)
				)
				(= [local107 0] @local102)
				(guardTeller init: guard @local102 @local107)
				(guard setScale: 200 init:)
				(if
					(and
						(< global401 5)
						(== gCurrentDay_10 Day)
						(not (Btst 38))
					)
					(= global401 5)
				)
			)
		)
		(cond 
			((== prevRoomNum 490) (localproc_1cd0))
			((== prevRoomNum 485)
				(ego x: 24 y: 146 setHeading: 0 setMotion: 0)
				(if (Btst 129)
					(curRoom
						addObstacle:
							((Polygon new:)
								type: 2
								init: 0 122 71 122 53 140 20 140 15 144 0 144
								yourself:
							)
					)
					(= [local121 0] @local109)
					(uhuraTeller init: (ScriptID 34 1) @local109 @local121)
					((ScriptID 34 1)
						view: 969
						loop: 0
						x: 7
						y: 141
						noun: 9
						setScale: 200
						stopUpd:
						init:
					)
				)
				(curRoom setScript: from485)
			)
			(
				(and
					(== brideState 4)
					(== gCurrentDay_10 Day)
					(not (Btst 65))
				)
				(= global401 10)
			)
			(
				(and
					(not (Btst 65))
					(== brideState 4)
					(!= gCurrentDay_10 Day)
					(not Night)
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 0 122 71 122 53 140 20 140 15 144 0 144
							yourself:
						)
				)
				(= gCurrentDay_10 Day)
				(= global401 9)
				(= [local121 0] @local115)
				(uhuraTeller init: (ScriptID 34 1) @local115 @local121)
				((ScriptID 34 1)
					view: 969
					loop: 0
					x: 7
					y: 141
					setScale: 200
					noun: 9
					stopUpd:
					init:
				)
				(ego setScript: uhuraGreet)
			)
			(
				(and
					(or (== global401 7) (== global401 8))
					(!= gCurrentDay_10 Day)
					(not Night)
					(not (Btst 65))
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 95 133 59 133 53 141 4 141 14 118 95 118
							yourself:
						)
				)
				(= gCurrentDay_10 Day)
				(= global401 8)
				(= [local65 0] @local54)
				(= [local65 1] @local59)
				(yesufuTeller
					init: (ScriptID 39 1) @local54 @local65 @local62
				)
				((ScriptID 39 1)
					view: 989
					x: 80
					y: 130
					loop: 2
					noun: 6
					setScale: 200
					stopUpd:
					init:
				)
				(ego setScript: yesufuGreet)
			)
			(
				(and
					(== global401 5)
					(!= gCurrentDay_10 Day)
					(not Night)
					(not (Btst 65))
					(not (Btst 38))
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 0 122 71 122 53 140 20 140 15 144 0 144
							yourself:
						)
				)
				(= gCurrentDay_10 Day)
				(= global401 6)
				(= [local121 0] @local109)
				(uhuraTeller init: (ScriptID 34 1) @local109 @local121)
				((ScriptID 34 1)
					view: 969
					x: 7
					y: 141
					noun: 9
					setScale: 200
					stopUpd:
					init:
				)
				(ego setScript: uhuraGreet)
			)
			(
				(and
					(Btst 29)
					(!= gCurrentDay_10 Day)
					(not Night)
					(not (Btst 65))
					(not (Btst 38))
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 95 133 59 133 53 141 4 141 14 118 95 118
							yourself:
						)
				)
				(= gCurrentDay_10 Day)
				(= global401 5)
				(= [local65 0] @local48)
				(yesufuTeller init: (ScriptID 39 1) @local48 @local65)
				((ScriptID 39 1)
					x: 80
					y: 130
					noun: 6
					view: 989
					loop: 2
					setScale: 200
					stopUpd:
					init:
				)
				(ego setScript: yesufuGreet)
			)
			(
				(and
					(== global401 2)
					(!= gCurrentDay_10 Day)
					(not Night)
					(not (Btst 29))
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 57 141 4 141 14 118 69 118
							yourself:
						)
				)
				(= gCurrentDay_10 Day)
				(= global401 3)
				(= [local65 0] @local30)
				(= [local65 1] @local36)
				(yesufuTeller
					init: (ScriptID 39 1) @local30 @local65 @local39
				)
				((ScriptID 39 1)
					view: 982
					loop: 0
					cel: 0
					x: 137
					y: 121
					setScale: 200
					noun: 6
					actions: yesufuTeller
					approachVerbs: 2
					ignoreActors: 1
					approachX: 109
					approachY: 137
					stopUpd:
					init:
				)
				(yesufuArm
					setPri: 8
					setScale: 200
					ignoreActors: 1
					stopUpd:
					init:
				)
				(ego setScript: yesufuGreet)
			)
			(
				(and
					(== global401 1)
					(!= gCurrentDay_10 Day)
					(not Night)
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 57 141 4 141 14 118 69 118
							yourself:
						)
				)
				(= gCurrentDay_10 Day)
				(= global401 2)
				(= [local90 0] @local69)
				(= [local90 1] @local76)
				(= [local90 2] @local79)
				(= [local90 3] @local82)
				(storyActions
					init: (ScriptID 53 1) @local69 @local90 @local85
				)
				((ScriptID 53 1)
					loop: 3
					cel: 0
					x: 160
					y: 142
					noun: 7
					actions: storyActions
					approachVerbs: 2
					approachX: 109
					approachY: 150
					setScale: 200
					stopUpd:
					init:
				)
				(ego setScript: storyTellGreet)
			)
			((and (not (Btst 62)) (not Night))
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 57 141 4 141 14 118 69 118
							yourself:
						)
				)
				(= gCurrentDay_10 Day)
				(= [local65 0] @local12)
				(= [local65 1] @local18)
				(= [local65 2] @local23)
				(yesufuTeller
					init: (ScriptID 39 1) @local12 @local65 @local26
				)
				((ScriptID 39 1)
					view: 982
					loop: 0
					cel: 0
					x: 137
					y: 121
					noun: 6
					actions: yesufuTeller
					setScale: 200
					approachVerbs: 2
					approachX: 109
					approachY: 137
					ignoreActors: 1
					stopUpd:
					init:
				)
				(yesufuArm
					setPri: 8
					setScale: 200
					ignoreActors: 1
					stopUpd:
					init:
				)
				(ego setScript: yesufuGreet)
				(Bset 62)
				(= global401 1)
			)
			(
				(and
					(!= gCurrentDay_10 Day)
					(not Night)
					(not (Btst 29))
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 57 141 4 141 14 118 69 118
							yourself:
						)
				)
				(= gCurrentDay_10 Day)
				(= global401 4)
				(= [local65 0] @local42)
				(yesufuTeller init: (ScriptID 39 1) @local42 @local65)
				((ScriptID 39 1)
					view: 982
					loop: 0
					cel: 0
					x: 137
					y: 121
					setScale: 200
					noun: 6
					actions: yesufuTeller
					approachVerbs: 2
					approachX: 109
					approachY: 137
					ignoreActors: 1
					stopUpd:
					init:
				)
				(yesufuArm
					ignoreActors: 1
					setScale: 200
					init:
					stopUpd:
					setPri: 8
				)
				(ego setScript: yesufuGreet)
			)
			((and (Btst 65) (== global401 10))
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 57 141 4 141 14 118 69 118
							yourself:
						)
				)
				(= global401 11)
			)
			(else
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 57 141 4 141 14 118 69 118
							yourself:
						)
				)
			)
		)
		(= [local10 0] @local3)
		(egoTeller init: ego @local3 @local10)
		(ego
			setScale: 200
			normalize:
			actions: egoTeller
			setAvoider: PAvoider
			noun: 8
			init:
		)
		(cond 
			((== prevRoomNum 420) (curRoom setScript: enterFrSouth))
			((== prevRoomNum 470) (curRoom setScript: enterFrWrest))
			((== prevRoomNum 460) (curRoom setScript: enterFrSpear))
			((OneOf prevRoomNum 485 490) 0)
			(else (curRoom setScript: enterFrSouth))
		)
		(if (!= (cSound number?) 160)
			(if (not (if (Btst 29) (not (Btst 65))))
				(cSound number: 160 setLoop: -1 play:)
			)
		)
		(super init:)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((>= (ego y?) 183) (curRoom setScript: exitSouth))
			((>= (ego x?) 315) (curRoom setScript: exitEast))
			((<= (ego x?) 5) (curRoom setScript: exitWest))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(LoadMany 0 39 36 34 53)
		(super dispose:)
	)
	
	(method (cue)
		(if (== (cSound prevSignal?) -1)
			(cSound setLoop: -1 client: 0 changeTo: 160)
		)
	)
)

(instance uhuraGreet of Script
	(properties)
	
	(method (doit)
		(if (and (== state 1) (not (ego mover?)))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1)
			(2
				(HandsOff)
				(switch global401
					(6
						(messager say: 9 6 44 0 self)
					)
					(9
						(messager say: 9 6 53 0 self)
					)
				)
			)
			(3
				(if (and (not (ego has: 16)) (== global401 6))
					(messager say: 9 6 51 0 self)
				else
					(= cycles 1)
				)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance yesufuGreet of Script
	(properties)
	
	(method (doit)
		(if (and (== state 1) (not (ego mover?)))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1)
			(2
				(HandsOff)
				(switch global401
					(1 (messager say: 6 6 1 0 self))
					(3
						(messager say: 6 6 74 0 self)
					)
					(4
						(messager say: 6 6 30 0 self)
					)
					(5
						(messager say: 6 5 20 0 self)
					)
					(8
						(messager say: 6 6 79 0 self)
					)
				)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance storyTellGreet of Script
	(properties)
	
	(method (doit)
		(if (and (== state 1) (not (ego mover?)))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1)
			(2
				(HandsOff)
				(messager say: 7 6 30 0 self)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance goToGame of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 127)
				(messager say: 6 5 3 0 self)
			)
			(1 (curRoom newRoom: 490))
		)
	)
)

(instance firstTimeEnter of Script
	(properties)
	
	(method (doit)
		(if (and (== state 1) (not (ego mover?)))
			(self cue:)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1)
			(2 (HandsOff))
			(3
				(yesufuArm setCycle: EndLoop self)
			)
			(4 (messager say: 6 6 1 0 self))
			(5
				(yesufuArm setCycle: BegLoop self)
			)
			(6
				(yesufuArm stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance from485 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(cond 
					((cast contains: guard) (messager say: 2 6 50 0 self))
					((cast contains: (ScriptID 34 1)) (Bclr 129) (messager say: 9 6 50 0 self))
				)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance releaseJohari of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset 65)
				(ego view: 31 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(1
				(ego
					normalize:
					setCycle: Reverse
					setLoop: 3
					setMotion: PolyPath (+ (ego x?) 10) (+ (ego y?) 10) self
				)
				(globalSound number: 481 setLoop: 1 play: 127)
				(cageDoor setCycle: EndLoop)
			)
			(2
				(cSound setLoop: 1 changeTo: 485 curRoom)
				(ego setCycle: 0)
				((ScriptID 36 1)
					view: 484
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				((ScriptID 36 1)
					setLoop: 1
					cel: 0
					x: 47
					setCycle: EndLoop self
				)
			)
			(4
				((ScriptID 36 1)
					setLoop: 2
					cel: 0
					x: 62
					y: 124
					setCycle: Forward
					setPri: 8
					origStep: 808
					setMotion: MoveTo 112 96 self
				)
			)
			(5
				((ScriptID 36 1) setPri: 5 setMotion: MoveTo 118 93 self)
			)
			(6
				((ScriptID 36 1)
					setLoop: 3
					cel: 0
					x: 134
					y: 57
					setPri: -1
					setCycle: EndLoop self
				)
			)
			(7
				((ScriptID 36 1) dispose:)
				(= cycles 5)
			)
			(8
				(messager say: 2 6 65 0 self)
			)
			(9
				(ego normalize:)
				(= global401 10)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance exitSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath (ego x?) 210 self)
			)
			(1 (curRoom newRoom: 420))
		)
	)
)

(instance exitEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 325 (ego y?) self)
			)
			(1 (curRoom newRoom: 470))
		)
	)
)

(instance exitWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath -5 (ego y?) self)
			)
			(1 (curRoom newRoom: 460))
		)
	)
)

(instance enterFrSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: 160 y: 210 setMotion: PolyPath 160 180 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance enterFrWrest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: 320 y: 170 setMotion: PolyPath 300 170 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance enterFrSpear of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: -15 y: 170 setMotion: PolyPath 20 170 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance johariHands of Prop
	(properties
		x 33
		y 132
		view 481
		loop 3
		priority 10
		signal $4010
	)
	
	(method (doit)
		(self cel: ((ScriptID 36 1) cel?))
		(super doit: &rest)
	)
)

(instance tree1 of Feature
	(properties
		x 189
		y 22
		noun 12
		nsTop 1
		nsLeft 92
		nsBottom 43
		nsRight 287
		sightAngle 180
	)
)

(instance tree2 of Feature
	(properties
		x 205
		y 62
		noun 13
		nsTop 44
		nsLeft 118
		nsBottom 77
		nsRight 292
		sightAngle 180
	)
)

(instance rock1 of Feature
	(properties
		x 166
		y 125
		noun 14
		nsTop 114
		nsLeft 121
		nsBottom 142
		nsRight 212
		sightAngle 180
	)
)

(instance rock2 of Feature
	(properties
		x 261
		y 124
		noun 15
		nsTop 93
		nsLeft 204
		nsBottom 155
		nsRight 319
	)
)

(instance mountain of Feature
	(properties
		x 159
		y 32
		noun 16
		nsTop 22
		nsBottom 42
		nsRight 319
		sightAngle 180
	)
)

(instance littleRock1 of Feature
	(properties
		x 69
		y 145
		noun 17
		nsTop 142
		nsLeft 62
		nsBottom 149
		nsRight 76
		sightAngle 180
	)
)

(instance littleRock2 of Feature
	(properties
		x 227
		y 182
		noun 18
		nsTop 179
		nsLeft 219
		nsBottom 186
		nsRight 235
		sightAngle 180
	)
)

(instance theCage of Feature
	(properties
		x 48
		y 121
		noun 3
		nsTop 85
		nsLeft 12
		nsBottom 128
		nsRight 56
		sightAngle 40
		approachX 60
		approachY 150
	)
	
	(method (doVerb)
		(cageDoor doVerb: &rest)
	)
)

(instance cageDoor of Prop
	(properties
		x 48
		y 121
		noun 3
		sightAngle 40
		approachX 60
		approachY 150
		view 480
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((curRoom script?) 0)
					(
						(and
							(== global401 5)
							(not (Btst 65))
							(cast contains: (ScriptID 39 1))
						)
						(messager say: 6 6 25)
					)
					(
						(and
							(== global401 9)
							(not (Btst 65))
							(cast contains: (ScriptID 34 1))
						)
						(if (== global453 3)
							(messager say: 3 4 55)
						else
							(messager say: 3 4 56)
						)
					)
					(
						(and
							(Btst 38)
							(== brideState 4)
							(> global401 5)
							(not (Btst 65))
						)
						(Bset 65)
						(curRoom setScript: releaseJohari)
					)
					(
						(and
							(cast contains: guard)
							(cast contains: (ScriptID 36 1))
						)
						(messager say: 3 4 83)
					)
					((not (cast contains: (ScriptID 36 1))) (messager say: 3 4 84))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance awari of Feature
	(properties
		x 173
		y 128
		noun 4
		nsTop 117
		nsLeft 142
		nsBottom 133
		nsRight 204
		sightAngle 40
		approachX 173
		approachY 134
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if
					(and
						(cast contains: (ScriptID 39 1))
						(OneOf global401 1 3 4)
					)
					(curRoom newRoom: 490)
				else
					(messager say: 4 4 82)
				)
			)
			(24
				(Bset 161)
				(super doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fence of Feature
	(properties
		x 144
		y 60
		noun 5
		nsTop 38
		nsBottom 85
		nsRight 224
		sightAngle 40
		approachX 144
		approachY 68
	)
)

(instance guard of View
	(properties
		x 14
		y 150
		noun 2
		view 482
	)
)

(instance yesufuArm of Prop
	(properties
		x 139
		y 107
		view 982
		loop 1
	)
)

(instance johariTeller of Teller
	(properties)
	
	(method (doChild)
		(if (Btst 38)
			(messager say: 10 5 63)
		else
			(messager say: 11 5 63)
		)
		(return 0)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(30
				(if (and (== brideState 4) (Btst 38))
					(if (not (Btst 283))
						(ego solvePuzzle: 283 3 addHonor: 20)
						(++ global453)
					)
					(ego drop: 20 1)
					(messager say: 10 30 0)
				else
					(messager say: 11 30 0)
				)
			)
			(12
				(if (and (== brideState 4) (Btst 38))
					(if (not (Btst 284))
						(ego solvePuzzle: 284 3 addHonor: 20)
						(++ global453)
					)
					(ego drop: 2 1)
					(messager say: 10 12 0)
				else
					(messager say: 11 12 0)
				)
			)
			(43
				(if (and (== brideState 4) (Btst 38))
					(if (not (Btst 282))
						(ego solvePuzzle: 282 3 addHonor: 20)
						(++ global453)
					)
					(ego drop: 32)
					(messager say: 10 43 0)
				else
					(messager say: 11 43 0)
				)
			)
			(V_DISPEL
				(cond 
					(
					(and (cast contains: (ScriptID 39 1)) (== global401 5)) (messager say: 6 6 25))
					(
						(and
							(or
								(and
									(== global401 5)
									(not (cast contains: (ScriptID 39 1)))
								)
								(== global401 6)
							)
							(not (Btst 38))
							(not (curRoom script?))
						)
						(Bset 38)
						(= global401 7)
						(if (cast contains: (ScriptID 34 1)) (Bset 129))
						(ego drop: 16 1 solvePuzzle: 281 15 addHonor: 40)
						(curRoom newRoom: 485)
					)
					(else (super doVerb: theVerb))
				)
			)
			(else 
				(cond 
					((and (> theVerb 10) (== global401 9)) (messager say: 19 6 80))
					((and (> theVerb 10) (== global401 10)) (messager say: 10 0 64))
					(else (super doVerb: theVerb))
				)
			)
		)
	)
)

(instance yesufuTeller of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				14
				(== origHeroType 0)
				18
				(== origHeroType 0)
				8
				(== origHeroType 0)
				-3
				(== global401 1)
				-75
				(if (== global401 3) else (== global401 4))
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-75
					(if (Btst 127)
						(= query 16)
					else
						(= query 3)
						(curRoom setScript: goToGame)
						(return 0)
					)
				)
				(-3
					(if (Btst 127)
						(= query 16)
					else
						(curRoom setScript: goToGame)
						(return 0)
					)
				)
				(-7
					(if (== origHeroType 0)
						(super doChild: &rest)
					else
						(return 1)
					)
				)
				(else  (super doChild: &rest))
			)
		)
	)
)

(instance egoTeller of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-30
				(if (and (not local2) (< global401 11))
					(cond 
						((cast contains: (ScriptID 34 1)))
						((cast contains: (ScriptID 39 1)))
						((cast contains: guard))
						(else (cast contains: (ScriptID 53 1)))
					)
				else
					0
				)
				-72
				(if (< global401 11)
					(cond 
						((cast contains: (ScriptID 34 1)))
						((cast contains: (ScriptID 39 1)))
						((cast contains: guard))
						(else (cast contains: (ScriptID 53 1)))
					)
				else
					0
				)
				68
				(if (cast contains: (ScriptID 39 1))
					(if (== global401 1) else (== global401 3))
				else
					0
				)
				-41
				(if (cast contains: (ScriptID 53 1))
					(if (not local0) (== global401 2))
				else
					0
				)
				28
				(if (== global401 9)
					(cast contains: (ScriptID 34 1))
				else
					0
				)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-30
					(cond 
						((cast contains: guard) (= query 69))
						((cast contains: (ScriptID 39 1)) (ego addHonor: 3) (= query 66))
						((cast contains: (ScriptID 53 1)) (ego addHonor: 3) (= query 42))
						((cast contains: (ScriptID 34 1)) (= query 62))
					)
					(= local2 1)
				)
				(-72
					(cond 
						((cast contains: guard) (= query 70))
						((cast contains: (ScriptID 39 1)) (= query 67))
						((cast contains: (ScriptID 53 1)) (= query 43))
						((cast contains: (ScriptID 34 1)) (= query 61))
					)
				)
				(-41
					(= local0 1)
					(ego addHonor: 10)
					(return 1)
				)
			)
		)
	)
)

(instance storyActions of Teller
	(properties)
	
	(method (showDialog)
		(super showDialog: 39 (Btst 9) 40 (Btst 9))
	)
	
	(method (doChild)
		(if (== query -73)
			(switch heroType
				(0 (= query 36))
				(3 (= query 36))
				(1 (= query 37))
				(2 (= query 38))
			)
		else
			(super doChild: &rest)
		)
	)
)

(instance guardTeller of Teller
	(properties)
	
	(method (doChild)
		(messager say: 2 5 63)
		(return 0)
	)
)

(instance uhuraTeller of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog: -59 (< global453 3) 28 (== prevRoomNum 485)
		)
	)
	
	(method (doChild)
		(return
			(if (== query -59)
				(ego solvePuzzle: 268 2)
				(return 1)
			else
				0
			)
		)
	)
)
