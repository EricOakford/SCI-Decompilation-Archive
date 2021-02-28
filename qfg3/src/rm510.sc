;;; Sierra Script 1.0 - (do not remove this comment)
(script# 510)
(include sci.sh)
(use Main)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Grooper)
(use Path)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm510 0
)

(local
	local0
	local1
	[newSpear 5]
	[local7 10] = [120 137 126 137 132 138 139 137 146 138]
	[local17 5] = [214 81 360 100 -32768]
	[local22 5] = [214 86 360 100 -32768]
	[local27 5] = [216 86 214 180 -32768]
	[local32 5] = [216 81 214 180 -32768]
	[local37 45] = [3 0 201 62 3 1 201 62 3 2 201 62 3 3 201 62 3 4 197 64 3 5 180 66 3 6 167 79 3 7 165 93 3 8 162 117 3 9 169 148 3 10 169 148 -32768]
	[local82 25] = [4 0 220 58 4 1 219 65 4 2 220 89 4 4 224 112 4 3 227 140 4 3 227 180 -32768]
	[local107 29] = [0 0 188 134 0 1 188 134 0 2 188 134 0 3 207 121 0 4 228 130 0 5 237 147 0 5 237 180 -32768]
	local136
	local137
	local138
	local139
	local140
	local141
	local142
	local143
	local144
)
(instance rm510 of Rm
	(properties
		noun 7
		picture 510
		vanishingY -200
	)
	
	(method (init &tmp temp0)
		(HandsOff)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 154 0 66 32 80 63 101 41 115 48 149
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 271 189 271 181 285 181 286 189
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						319
						145
						255
						156
						253
						143
						190
						143
						188
						132
						167
						132
						139
						138
						93
						131
						87
						110
						115
						107
						84
						0
						319
						0
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 0 177 107 177 156 189 0 189
					yourself:
				)
		)
		(LoadMany 128 511 515 516 1 989 980 0 984 7 12)
		(rock init:)
		(marker init:)
		(mountains init:)
		(rack ignoreActors: 1 approachVerbs: 4 34 stopUpd: init:)
		(= temp0 0)
		(while (< temp0 5)
			((= [newSpear temp0] (spear new:))
				loop: 3
				cel: 0
				approachVerbs: 4 34
				x: [local7 (* temp0 2)]
				y: [local7 (+ (* temp0 2) 1)]
				init:
			)
			(++ temp0)
		)
		(ego changeGait: 1 setScale: 200 x: -30 y: 30 init:)
		((ScriptID 39 1)
			view: 983
			x: 0
			y: 60
			origStep: 2053
			setCycle: StopWalk -1
			setLoop: yesufuStopGroop
			setScale: 200
			ignoreActors: 1
			init:
		)
		((ScriptID 58 1)
			setScale: 200
			view: 414
			loop: 0
			cel: 7
			x: 109
			y: 118
			setPri: 8
			ignoreActors: 1
			noun: 4
			illegalBits: 0
			init:
		)
		(hole init:)
		(string init:)
		(ring init:)
		(vine approachVerbs: 4 init:)
		(tree approachVerbs: 4 init:)
		(curRoom setScript: enterRoom)
		(super init:)
		(theGame save: 1)
	)
	
	(method (dispose)
		(UnLoad 128 511)
		(UnLoad 128 515)
		(UnLoad 128 516)
		(UnLoad 128 1)
		(UnLoad 128 989)
		(UnLoad 128 980)
		(UnLoad 128 0)
		(UnLoad 128 984)
		(UnLoad 128 7)
		(UnLoad 128 12)
		(LoadMany 0 39 983 942 58)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (ego setScript: fallOff))
			(65 (messager say: 2 6 22))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance egoWin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego solvePuzzle: 288 5 9)
				(ego setMotion: PolyPath 109 125 self)
			)
			(1
				(ego setMotion: PolyPath 65 160 self)
				((ScriptID 39 1) setMotion: PolyPath 65 180)
			)
			(2 (self dispose:))
		)
	)
)

(instance yesufuWin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 39 1) setMotion: PolyPath 109 125 self)
			)
			(1
				(messager say: 1 6 18 0 self)
			)
			(2
				((ScriptID 39 1) setMotion: PolyPath 65 180 self)
			)
			(3
				(ego setMotion: PolyPath 65 160 self)
			)
			(4 (self dispose:))
		)
	)
)

(instance awardPrize of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(cond 
					((and (< local138 3) local143) (messager say: 1 6 19 0 self) (= temp0 1))
					(
					(and (< local138 3) (< local137 3) local142 local141) (messager say: 1 6 21 0 self) (= temp0 1))
					((and (< local138 3) (< local137 2) local141) (messager say: 1 6 16 0 self) (= temp0 1))
					((and (<= local138 3) (== local137 3) local141) (messager say: 1 6 14 0 self) (= temp0 0))
					(
						(and
							(<= local138 3)
							(< 0 local137)
							(< local137 3)
							(not local141)
							local143
						)
						(= temp0 1)
						(= local144 1)
						(messager say: 1 6 13 0 self)
					)
					((and (<= local138 3) local142 local143) (= temp0 1) (= local144 1) (messager say: 1 6 13 0 self))
					((> local138 3) (messager say: 1 6 12 0 self) (= temp0 0))
					(local139 (= temp0 0) (= cycles 1))
				)
			)
			(1
				(if local144
					(messager say: 1 6 15 0 self)
				else
					(= cycles 1)
				)
			)
			(2
				(if temp0
					(self setScript: egoWin self)
				else
					(self setScript: yesufuWin self)
				)
			)
			(3
				(messager say: 1 6 20)
				(ego setMotion: PolyPath -20 160 self)
				((ScriptID 39 1) setMotion: PolyPath -20 180)
			)
			(4 (curRoom newRoom: 500))
		)
	)
)

(instance dropRing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(string dispose:)
				(ring setMotion: MoveTo (ring x?) 180 self)
			)
			(2
				(ring setCycle: 0)
				(ring dispose:)
			)
		)
	)
)

(instance throwSpVine of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local138 (+ local138 2))
				(= local143 1)
				(ring setScript: spinRing)
				(messager say: 2 6 10 0 self)
			)
			(1
				(ego view: 511 setLoop: 1 cel: 0 setCycle: End self)
			)
			(2
				(= local136 2)
				(ego view: 10 setLoop: 0 cel: 0 setCycle: CT 4 1 self)
				([newSpear local136] dispose:)
			)
			(3
				((= [newSpear local136] (spear new:))
					view: 46
					setLoop: 6
					cel: 4
					x: 152
					y: 90
					setStep: 18 8
					moveSpeed: 0
					setPri: 0
					init:
					setMotion: spear3Path self spinRing
				)
				(ego setCycle: End)
			)
			(4
				(messager say: 2 6 11 0 self)
			)
			(5
				(= local0 (Graph grSAVE_BOX 112 143 131 192 1))
				(Graph grDRAW_LINE 113 144 130 191 87 -1 -1)
				(Graph grUPDATE_BOX 113 144 130 191 1)
				(ego view: 511 setLoop: 0 cel: 0 setCycle: End self)
			)
			(6
				(Graph grRESTORE_BOX local0)
				(Graph grUPDATE_BOX 113 144 130 191 1)
				(++ local136)
				(ego view: 10 setLoop: 0 cel: 0 setCycle: CT 4 1 self)
				([newSpear local136] dispose:)
			)
			(7
				((= [newSpear local136] (spear new:))
					view: 46
					setLoop: 6
					cel: 4
					x: 152
					y: 90
					moveSpeed: 0
					setStep: 18 8
					setPri: 0
					init:
					setMotion: spear4Path self spinRing
				)
				(ego setCycle: End)
			)
			(8
				(= local0 (Graph grSAVE_BOX 113 144 130 191 1))
				(Graph grDRAW_LINE 113 144 130 191 87 -1 -1)
				(Graph grUPDATE_BOX 113 144 130 191 1)
				(ego view: 511 setLoop: 0 cel: 0 setCycle: End self)
			)
			(9
				(Graph grRESTORE_BOX local0)
				(Graph grUPDATE_BOX 113 144 130 191 1)
				(ego normalize:)
				(client setScript: awardPrize)
			)
		)
	)
)

(instance getVine of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ego view: 31 setLoop: 0 cel: 0 setCycle: End self)
			)
			(1
				(vine dispose:)
				(ego get: 24 normalize:)
				((inventory at: 24) owner: ego)
				(theIconBar
					curInvIcon: (inventory at: 24)
					curIcon: (theIconBar at: 7)
					enable: 7
				)
				((theIconBar at: 7) cursor: Cursor)
				(((theIconBar at: 7) cursor?) view: 905 loop: 10 cel: 8)
				(theGame setCursor: ((theIconBar at: 7) cursor?))
				(self dispose:)
			)
		)
	)
)

(instance jumpForRing of Script
	(properties)
	
	(method (doit)
		(cond 
			((and (== state 1) (== (ego cel?) 3)) (ring setCycle: Fwd))
			((== state 3) (ring setCycle: 0 cel: 0))
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local142 1)
				(HandsOff)
				(++ local138)
				(ego changeGait: 1 setMotion: PolyPath 187 135 self)
			)
			(1
				(ego
					view: 516
					setLoop: 0
					cel: 0
					setPri: 0
					setCycle: MCyc @local107 self
				)
			)
			(2 (= ticks 240))
			(3
				(ego setLoop: 1 x: 191 y: 132 setCycle: End self)
			)
			(4
				(ego
					x: 184
					y: 134
					setPri: -1
					loop: 2
					cel: 0
					setCycle: End self
				)
			)
			(5
				(ego normalize:)
				(messager say: 2 6 8 0 self)
			)
			(6 (HandsOn) (self dispose:))
		)
	)
)

(instance throwSpears of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local139 1)
				(= local138 (+ local138 4))
				(ego view: 10)
				(= local136 2)
				(= cycles 1)
			)
			(1
				(ego setLoop: 0 cel: 0 setCycle: CT 4 1 self)
				([newSpear local136] dispose:)
			)
			(2
				((= [newSpear local136] (spear new:))
					view: 46
					setLoop: 6
					cel: 4
					x: 152
					y: 90
					setStep: 18 8
					moveSpeed: 0
					setPri: 9
					init:
					setMotion: spearPath self
				)
				(ego setCycle: End)
			)
			(3
				(if (== (++ local136) 4)
					(= cycles 1)
				else
					(switch local136
						(2 (messager say: 2 6 6))
						(3 (messager say: 2 6 7))
					)
					(self changeState: (= state (- state 2)))
				)
			)
			(4
				(ego setLoop: 0 cel: 0 setCycle: CT 4 1 self)
				(ring setScript: dropRing)
				([newSpear local136] dispose:)
			)
			(5
				((= [newSpear local136] (spear new:))
					view: 46
					setLoop: 6
					cel: 4
					x: 152
					y: 90
					setStep: 18 8
					moveSpeed: 0
					setPri: 9
					init:
					setMotion: spear2Path self dropRing
				)
				(ego setCycle: End)
			)
			(6
				(ego
					view: 1
					setCycle: Walk
					setMotion: MoveTo 187 135 self
				)
			)
			(7
				(ego
					view: 516
					setLoop: 0
					cel: 0
					setPri: 0
					setCycle: MCyc @local107 self
				)
			)
			(8 (= ticks 240))
			(9
				(ego setLoop: 1 x: 191 y: 132 setCycle: End self)
			)
			(10
				(ego
					x: 184
					y: 134
					setPri: -1
					loop: 2
					cel: 0
					setCycle: End self
				)
			)
			(11
				(ego normalize:)
				(client setScript: awardPrize)
			)
		)
	)
)

(instance fallOff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(walkHandler delete: curRoom)
				(User canControl: 1)
				(HandsOff)
				(ego view: 6 setPri: 0 setCycle: MCyc @local82 self)
			)
			(1
				(ego view: 516 loop: 1 x: 191 y: 132 setCycle: End self)
			)
			(2
				(ego
					x: 184
					y: 134
					setPri: -1
					loop: 2
					cel: 0
					setCycle: End self
				)
			)
			(3
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getRing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local141 1)
				(walkHandler delete: curRoom)
				(HandsOff)
				(ego view: 4 x: 191 y: 58 cel: 0 setCycle: End self)
			)
			(1
				(string setMotion: MoveTo 210 63 self)
				(ring setMotion: MoveTo 210 68)
			)
			(2
				(string dispose:)
				(ring dispose:)
				(ego setCycle: Beg self)
			)
			(3
				(ego view: 30 setCycle: MCyc @local37 self)
			)
			(4
				(ego normalize:)
				(client setScript: awardPrize)
			)
		)
	)
)

(instance egoClimbTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(++ local138)
				(walkHandler addToFront: curRoom)
				(User canControl: 0)
				(ego setMotion: MoveTo 130 125 self)
			)
			(1
				(ego
					view: 7
					setLoop: 3
					setScale:
					setCycle: Fwd
					setMotion: MoveTo 124 96 self
				)
			)
			(2
				(ego setLoop: 5 cel: 0 x: 120 y: 64 setCycle: End self)
			)
			(3
				(ego
					view: 12
					setLoop: 0
					x: 125
					y: 65
					setCycle: Walk
					setMotion: MoveTo 146 58 self
				)
			)
			(4
				(ego setMotion: MoveTo 178 54 self)
			)
			(5
				(ego setMotion: MoveTo 199 60 self)
			)
			(6
				(HandsOn)
				(User canControl: 0)
				(self dispose:)
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
				(ego
					view: 7
					setLoop: 3
					setScale:
					setCycle: Fwd
					setMotion: MoveTo 130 102 self
				)
			)
			(1
				(ego setCycle: 0 setMotion: MoveTo 130 120 self)
			)
			(2
				(++ local137)
				(++ local138)
				(ego normalize:)
				(messager say: 2 6 5 0 self)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance egoCantClimb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local140 1)
				(HandsOff)
				(ego view: 7 setLoop: 3 cycleSpeed: 2 setCycle: Fwd)
				(= ticks 240)
			)
			(1
				(++ local138)
				(ego cycleSpeed: 6 normalize:)
				(messager say: 2 6 3 0 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance spinRing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1 (ring setCycle: Fwd))
			(2
				(string dispose:)
				(ring setMotion: MoveTo (ring x?) 180 self)
			)
			(3
				(ring setCycle: 0)
				(ring dispose:)
			)
		)
	)
)

(instance runIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 36 72 self)
			)
			(1
				(ego setMotion: PolyPath 66 161 self)
			)
			(2
				(ego
					view: 0
					normalize:
					setSpeed: gGOwnerMoveSpeed
					setHeading: 90
				)
				(= cycles 18)
			)
			(3 (self dispose:))
		)
	)
)

(instance enterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 39 1)
					xStep: 5
					yStep: 3
					setMotion: PolyPath 36 72 self
				)
			)
			(1
				(ego setScript: runIn self)
				(HandsOff)
				((ScriptID 39 1) setMotion: PolyPath 76 135 self)
			)
			(2
				((ScriptID 39 1) setMotion: PolyPath 109 125 self)
			)
			(3
				((ScriptID 39 1) setHeading: 0)
				(= cycles 18)
			)
			(4)
			(5 (messager say: 1 6 1 0 self))
			(6 (messager say: 1 6 2 0 self))
			(7
				((ScriptID 39 1) setMotion: PolyPath 109 142 self)
			)
			(8
				((ScriptID 39 1)
					view: 984
					loop: 0
					cel: 0
					setCycle: CT 4 1 self
				)
			)
			(9
				((ScriptID 39 1) setCycle: End self)
				([newSpear local136] dispose:)
			)
			(10
				((ScriptID 39 1)
					x: 120
					y: 140
					loop: 1
					cel: 0
					setCycle: CT 3 1 self
				)
			)
			(11
				((= [newSpear local136] (spear new:))
					view: 46
					setLoop: 6
					cel: 4
					x: 152
					y: 90
					setStep: 18 8
					moveSpeed: 0
					setPri: 9
					init:
					setMotion: spearPath self spinRing
				)
				((ScriptID 39 1) setCycle: End)
			)
			(12
				([newSpear local136] dispose:)
				(if (!= local136 1)
					(++ local136)
					(= state (- state 5))
					(= ticks 120)
				else
					(= ticks 1)
				)
			)
			(13
				([newSpear local136] dispose:)
				((ScriptID 39 1)
					x: 109
					y: 138
					view: 983
					setCycle: StopWalk -1
					setLoop: yesufuStopGroop
					setMotion: MoveTo 165 140 self
				)
			)
			(14
				((ScriptID 39 1)
					view: 515
					setLoop: 0
					cel: 0
					x: 191
					y: 140
					setCycle: End self
				)
			)
			(15
				((ScriptID 39 1)
					setPri: 0
					moveSpeed: 0
					setScale:
					origStep: 1290
					setMotion: MoveTo 240 220 self
				)
			)
			(16 (= ticks 240))
			(17
				((ScriptID 39 1)
					setLoop: 1
					setPri: -1
					x: 222
					y: 140
					origStep: 2053
					cel: 0
					moveSpeed: 6
					setCycle: End self
				)
			)
			(18 (= ticks 120))
			(19
				((ScriptID 39 1)
					view: 983
					setPri: -1
					setCycle: StopWalk -1
					setLoop: yesufuStopGroop
					setMotion: PolyPath 96 185 self
				)
			)
			(20
				((ScriptID 39 1) setHeading: 90)
				((ScriptID 58 1) setCycle: End self)
				(string y: 65 setMotion: MoveTo 210 73 init:)
				(ring y: 70 cel: 0 setMotion: MoveTo 210 78 init:)
			)
			(21 (HandsOn) (self dispose:))
		)
	)
)

(instance hole of Feature
	(properties
		x 210
		y 65
		noun 3
		sightAngle 40
		onMeCheck $0008
	)
)

(instance tree of Feature
	(properties
		x 115
		y 90
		noun 10
		sightAngle 40
		onMeCheck $0004
		approachX 119
		approachY 130
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(4
				(cond 
					((curRoom script?) 0)
					((< (ego y?) 60) 0)
					(
					(and (== (= temp0 (ego trySkill: 11 150)) 0) local140) (messager say: 2 6 4))
					((== temp0 0) (curRoom setScript: egoCantClimb))
					((and (== temp0 -1) (< local137 3)) (curRoom setScript: egoTryClimb))
					(else (curRoom setScript: egoClimbTree))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rock of Feature
	(properties
		x 74
		y 169
		noun 11
		nsTop 149
		nsBottom 189
		nsRight 149
		sightAngle 180
	)
)

(instance marker of Feature
	(properties
		x 282
		y 161
		noun 12
		nsTop 133
		nsLeft 278
		nsBottom 189
		nsRight 287
		sightAngle 180
	)
)

(instance mountains of Feature
	(properties
		x 159
		y 31
		noun 13
		nsTop 22
		nsBottom 41
		nsRight 319
		sightAngle 180
	)
)

(instance vine of View
	(properties
		x 77
		y 43
		noun 6
		approachX 80
		approachY 111
		view 510
		loop 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (curRoom script?)
					0
				else
					(curRoom setScript: getVine)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance spear of Actor
	(properties
		noun 8
		view 510
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(super doVerb: 1 &rest)
		else
			(rack doVerb: theVerb)
		)
	)
)

(instance ring of Actor
	(properties
		x 210
		y 78
		noun 5
		yStep 10
		view 510
		loop 1
		signal $4810
		illegalBits $0000
	)
	
	(method (init)
		(self setScript: spinRing)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((ego script?) 0)
					((== (ego view?) 12) (ego setScript: getRing))
					((and (!= (ego view?) 12) (not local142)) (ego setScript: jumpForRing))
					((and (!= (ego view?) 12) local142) (messager say: 2 6 9))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance string of Actor
	(properties
		x 210
		y 73
		noun 9
		view 510
		loop 1
		cel 1
		signal $4800
	)
)

(instance rack of Actor
	(properties
		x 144
		y 135
		approachX 132
		approachY 150
		view 510
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (curRoom script?)
					0
				else
					(curRoom setScript: throwSpears)
				)
			)
			(34
				(if (curRoom script?)
					0
				else
					(curRoom setScript: throwSpVine)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance yesufuStopGroop of Grooper
	(properties)
	
	(method (doit)
		(if
			(and
				(IsObject ((ScriptID 39 1) cycler?))
				(((ScriptID 39 1) cycler?) isKindOf: StopWalk)
			)
			((ScriptID 39 1)
				view: (((ScriptID 39 1) cycler?) vWalking?)
			)
		)
		(super doit: &rest)
	)
)

(instance spearPath of Path
	(properties)
	
	(method (at param1)
		(return [local17 param1])
	)
)

(instance spear2Path of Path
	(properties)
	
	(method (at param1)
		(return [local22 param1])
	)
)

(instance spear3Path of Path
	(properties)
	
	(method (at param1)
		(return [local27 param1])
	)
)

(instance spear4Path of Path
	(properties)
	
	(method (at param1)
		(return [local32 param1])
	)
)
