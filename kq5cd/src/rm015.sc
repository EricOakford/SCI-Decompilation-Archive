;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15)
(include sci.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use CDActor)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm015 0
)

(local
	local0
	[local1 4] = [15 16 210 211]
	gEgoEdgeHit
	local6
	[local7 7] = [2 0 3 1 2]
	theGPEventX
	gEgoY
	local16
	theGPolyList15
	local18
	local19
	local20
	[local21 8] = [208 208 220 207 109 117 148 143]
	[local29 8] = [172 130 174 100 178 120 154 107]
	[local37 8] = [236 164 219 173 181 173 179 164]
	[local45 8] = [172 152 180 155 144 160 122 154]
	[local53 8] = [168 142 202 142 201 148 173 148]
	[local61 8] = [185 154 292 150 304 158 183 158]
	[local69 10] = [222 156 165 154 160 145 279 145 279 148]
	[local79 8] = [182 163 226 164 217 169 179 169]
	[local87 8] = [140 137 193 137 188 141 134 141]
	[local95 8] = [226 132 279 139 244 142 203 138]
	[local103 16] = [162 152 162 166 148 174 106 175 75 169 43 163 50 159 80 152]
	[local119 10] = [65 171 74 175 74 178 55 179 49 176]
	[local129 10] = [65 143 86 139 122 143 118 147 96 151]
	[local139 10] = [162 139 198 135 233 140 232 150 162 150]
	[local149 12] = [111 122 137 122 166 124 162 128 132 131 111 131]
	[local161 8] = [165 127 181 124 200 129 174 131]
	[local169 8] = [199 116 215 120 200 122 185 117]
	[local177 10] = [76 118 85 112 137 114 167 119 119 119]
	[local187 8] = [1 1 2 1 2 2 1 2]
	[local195 12] = [2 146 2 140 24 123 86 142 108 160 67 164]
	local207
	theScript
	local209
)
(procedure (localproc_0bc4 &tmp temp0 temp1)
	(ego setMotion: 0 setCycle: 0 cel: (ego loop?) loop: 7)
	((ego head?)
		x: (ego x?)
		y: (ego y?)
		z: (CelHigh (ego view?) (ego loop?) (ego cel?))
	)
	(RedrawCast)
	(= local18 0)
	(if
		(or
			(localproc_113c 4 3)
			(localproc_113c 3 7)
			(localproc_113c 6 6)
			(localproc_113c 9 4)
		)
		(theMusic3 number: 6 loop: -1 vol: 127 play:)
		(theMusic2 stop:)
		(theMusic stop:)
		(= local20 1)
	else
		(= local20 0)
		(theMusic3 fade:)
		(theMusic number: 2 loop: -1 vol: 127 play:)
		(theMusic2 number: 3 loop: -1 vol: 127 play:)
	)
	(cond 
		((and (not global314) (> global315 3)) (curRoom newRoom: 211))
		((not global314) (curRoom newRoom: 14))
		((== global315 1)
			(= globalCedric (++ theGPolyList15))
			(curRoom newRoom: (if (== global314 7) 213 else 212))
		)
		((localproc_113c 10 7)
			(= globalCedric (++ theGPolyList15))
			(curRoom newRoom: 216)
		)
		(else
			(= temp0
				[local1 (= temp1 (mod (+ global314 global315) 4))]
			)
			(++ theGPolyList15)
			(curRoom drawPic: temp0)
			(oasis
				loop:
				(switch temp0
					(15 0)
					(16 1)
					(210 2)
					(else  3)
				)
				cel: local20
				setPri:
					(if local20
						(switch temp0
							(15 12)
							(16 11)
							(210 12)
							(else  11)
						)
					else
						-1
					)
				x: [local21 (+ (* temp1 2) local20)]
				y: [local29 (+ (* temp1 2) local20)]
			)
			(dummy
				x:
				(switch temp0
					(15 178)
					(16 222)
					(210 210)
					(else  140)
				)
				y:
				(switch temp0
					(15 156)
					(16 155)
					(210 164)
					(else  137)
				)
			)
			(if (localproc_113c 3 7)
				(rope init:)
			else
				(rope dispose:)
			)
			(if (localproc_113c 6 4)
				(skeleton init: stopUpd:)
				(if (not local207)
					(theMusic3 number: 64 loop: 1 vol: 127 play:)
					(++ local207)
				)
				(if (== ((inventory at: 8) owner?) 15)
					(bootInSand init: stopUpd:)
				)
			else
				(skeleton dispose:)
				(bootInSand dispose:)
			)
			(switch gEgoEdgeHit
				(1
					(ego
						view: 0
						setStep: 3 2
						setPri: 14
						y: 186
						looper: MyLooper
						posn: (ego x?) 186
					)
					(= local6 1)
				)
				(3
					(ego
						view: 2
						setStep: 2 1
						setPri: 4
						x: (ego x?)
						y:
						(switch temp0
							(15 115)
							(16 77)
							(210 103)
							(else  94)
						)
					)
					(= local6 3)
				)
				(2
					(ego
						view: 0
						setStep: 3 2
						setPri: 14
						looper: MyLooper
						posn: 5 170
					)
					(= local6 1)
				)
				(else 
					(ego
						view: 0
						setStep: 3 2
						setPri: 14
						looper: MyLooper
						posn: 315 170
					)
					(= local6 1)
				)
			)
			(if local20
				(if (== local6 1)
					(ego setPri: -1)
					(curRoom
						obstacles:
							(switch temp0
								(15 polyList15)
								(16 polyList16)
								(210 polyList210)
								(211 polyList211)
							)
					)
				)
				(oasisWater
					loop:
					(switch temp0
						(15 0)
						(16 1)
						(211 2)
					)
					x:
					(switch temp0
						(15 213)
						(16 225)
						(211 129)
					)
					y:
					(switch temp0
						(15 165)
						(16 167)
						(211 154)
					)
					setCycle: Fwd
					init:
				)
			else
				(oasisWater dispose:)
				(if (localproc_113c 6 4)
					(curRoom obstacles: polyList1)
				else
					(curRoom obstacles: 0)
				)
			)
			(ego forceUpd:)
			((ego head?)
				x: (ego x?)
				y: (ego y?)
				z: (CelHigh (ego view?) (ego loop?) (ego cel?))
			)
			(= gEgoEdgeHit 0)
			(ego edgeHit: 0)
			(= local0 0)
		)
	)
)

(procedure (localproc_113c param1 param2)
	(return
		(if
		(and (== param1 global314) (== param2 global315))
			(return 1)
		else
			(return 0)
		)
	)
)

(instance rm015 of KQ5Room
	(properties
		picture 15
	)
	
	(method (init &tmp temp0)
		(self
			setFeatures: desert
			picture: [local1 (= temp0 (mod (+ global314 global315) 4))]
		)
		(super init:)
		(theMusic number: 2 loop: -1 vol: 127 play:)
		(theMusic2 number: 3 loop: -1 vol: 127 play:)
		(if (== prevRoomNum 14) (++ theGPolyList15))
		(= local20 0)
		(oasis
			loop:
			(switch (self picture?)
				(15 0)
				(16 1)
				(210 2)
				(else  3)
			)
			cel: local20
			setPri: (if local20 10 else -1)
			x: [local21 (+ (* temp0 2) local20)]
			y: [local29 (+ (* temp0 2) local20)]
			init:
		)
		(dummy
			x:
			(switch (self picture?)
				(15 178)
				(16 224)
				(210 210)
				(else  140)
			)
			y:
			(switch (self picture?)
				(15 153)
				(16 157)
				(210 164)
				(else  153)
			)
			init:
		)
		(switch prevRoomNum
			(212
				(= theGPolyList15 globalCedric)
				(= local6 3)
				(ego
					view: 2
					x: 160
					y:
					(switch (curRoom picture?)
						(15 115)
						(16 77)
						(210 103)
						(else  94)
					)
					init:
					setPri: 4
				)
			)
			(213
				(= theGPolyList15 globalCedric)
				(= local6 3)
				(ego
					view: 2
					x: 160
					y:
					(switch (curRoom picture?)
						(15 115)
						(16 77)
						(210 103)
						(else  94)
					)
					init:
					setPri: 4
				)
			)
			(216
				(= theGPolyList15 globalCedric)
				(ego y: 170 init: setPri: 14)
				(= local6 1)
			)
			(else 
				(= theGPolyList15 globalCedric)
				(ego y: 170 init: setPri: 14)
				(= local6 1)
			)
		)
		(Load rsVIEW 2 26 28)
		(poly1 points: @local37 size: 4)
		5
		(poly2 points: @local45 size: 4)
		(poly3 points: @local53 size: 4)
		(poly4 points: @local61 size: 4)
		(poly5 points: @local69 size: 5)
		(poly6 points: @local79 size: 4)
		(poly7 points: @local87 size: 4)
		(poly8 points: @local95 size: 4)
		(poly9 points: @local103 size: 8)
		(poly10 points: @local119 size: 5)
		(poly11 points: @local129 size: 5)
		(poly12 points: @local139 size: 5)
		(poly13 points: @local149 size: 6)
		(poly14 points: @local161 size: 4)
		(poly15 points: @local169 size: 4)
		(poly16 points: @local177 size: 5)
		(poly17 points: @local187 size: 4)
		(poly18 points: @local195 size: 6)
		(polyList15 add: poly1 poly2 poly3 poly4)
		(polyList16 add: poly5 poly6 poly7 poly8)
		(polyList210 add: poly9 poly10)
		(polyList211
			add: poly11 poly12 poly13 poly14 poly15 poly16
		)
		(polyList0 add: poly17)
		(polyList1 add: poly18)
	)
	
	(method (doit &tmp temp0)
		(if local20
			(if (== curPic 211)
				(cond 
					((ego inRect: 136 127 156 157 136)
						(if (!= (oasis priority?) 9)
							(oasis setPri: 9 forceUpd:)
						)
					)
					((!= (oasis priority?) 11) (oasis setPri: 11 forceUpd:))
				)
			)
			(if (and (== curPic 16) (== local6 1))
				(if (< (ego y?) 100)
					(ego setPri: 5)
				else
					(ego setPri: -1)
				)
			)
		)
		(cond 
			(
				(and
					(& (= temp0 (ego onControl: 0)) $0002)
					(== local6 1)
				)
				(curRoom obstacles: polyList0)
				(= local6 2)
				(if (IsObject (ego mover?))
					(if gPEventY
						(= gEgoY 400)
						(= theGPEventX gPEventX)
					else
						(= gEgoY
							(+ (- (ego y?) ((ego mover?) y?)) (ego y?))
						)
						(= theGPEventX ((ego mover?) x?))
					)
				else
					(= gEgoY (ego y?))
					(= theGPEventX (ego x?))
				)
				(if (== gEgoY (ego y?)) (= gEgoY (+ gEgoY 5)))
				(ego y: (+ (ego y?) 7) looper: duneLooper setPri: 4)
				(if
				(not (& (OnControl 4 theGPEventX gEgoY) $0002))
					(ego setMotion: MoveTo theGPEventX gEgoY theScript)
				)
			)
			((and (& temp0 $0004) (== local6 2))
				(= local6 3)
				(if (IsObject (ego mover?))
					(if gPEventY
						(= gEgoY gPEventY)
						(= theGPEventX gPEventX)
					else
						(= gEgoY
							(+ (- (ego y?) ((ego mover?) y?)) (ego y?))
						)
						(= theGPEventX ((ego mover?) x?))
					)
				else
					(= gEgoY (ego y?))
					(= theGPEventX (ego x?))
				)
				(if (== gEgoY (ego y?)) (= gEgoY (- gEgoY 5)))
				(ego view: 2 posn: (ego x?) (- (ego y?) 5))
				(RedrawCast)
				(ego setStep: 2 1 looper: MyLooper setPri: 4)
				(if
				(not (& (OnControl 4 theGPEventX gEgoY) $0004))
					(ego setMotion: MoveTo theGPEventX gEgoY theScript)
				)
			)
			((and (& temp0 $0002) (== local6 2))
				(= local6 1)
				(if (IsObject (ego mover?))
					(if gPEventY
						(= gEgoY gPEventY)
						(= theGPEventX gPEventX)
					else
						(= gEgoY
							(+ (- (ego y?) ((ego mover?) y?)) (ego y?))
						)
						(= theGPEventX ((ego mover?) x?))
					)
				else
					(= gEgoY (ego y?))
					(= theGPEventX (ego x?))
				)
				(if (== gEgoY (ego y?)) (= gEgoY (+ gEgoY 5)))
				(if local20
					(curRoom
						obstacles:
							(switch (curRoom curPic?)
								(15 polyList15)
								(16 polyList16)
								(210 polyList210)
								(211 polyList211)
							)
					)
				)
				(if (cast contains: skeleton)
					(curRoom obstacles: polyList1)
				)
				(ego
					y: (+ (ego y?) 5)
					looper: MyLooper
					setPri: (if local20 -1 else 14)
					moveSpeed: 0
				)
				(if
				(not (& (OnControl 4 theGPEventX gEgoY) $0002))
					(ego setMotion: PolyPath theGPEventX gEgoY theScript)
				)
			)
			((and (& temp0 $0004) (== local6 3))
				(= local6 2)
				(if (IsObject (ego mover?))
					(if gPEventY
						(= gEgoY -400)
						(= theGPEventX gPEventX)
					else
						(= gEgoY
							(+ (- (ego y?) ((ego mover?) y?)) (ego y?))
						)
						(= theGPEventX ((ego mover?) x?))
					)
				else
					(= gEgoY (ego y?))
					(= theGPEventX (ego x?))
				)
				(if (== gEgoY (ego y?)) (= gEgoY (- gEgoY 5)))
				(ego view: 2 posn: (ego x?) (- (ego y?) 5))
				(RedrawCast)
				(ego
					view: 0
					looper: duneLooper
					xStep: 3
					yStep: 2
					setMotion: MoveTo theGPEventX gEgoY theScript
					setPri: 4
				)
				(if
				(not (& (OnControl 4 theGPEventX gEgoY) $0004))
					(ego setMotion: MoveTo theGPEventX gEgoY theScript)
				)
			)
		)
		(cond 
			(script (script doit:))
			((and (== theGPolyList15 5) (not local19)) (= local19 1) (SpeakAudio 315))
			((and (& temp0 $0008) (== local6 3))
				(-- global315)
				(= gEgoEdgeHit 1)
				(= local209 (theGame setCursor: waitCursor 1))
				(localproc_0bc4)
				(ego setCycle: KQ5SyncWalk)
				(theGame setCursor: local209)
			)
			(
				(and
					local20
					(& temp0 $0200)
					(!= curPic 210)
					(== local6 1)
				)
				(ego view: 26)
			)
			(
				(and
					local20
					(!= curPic 210)
					(== local6 1)
					(& temp0 $0400)
				)
				(ego view: 28)
			)
			(
				(and
					local20
					(== local6 1)
					(not (& temp0 $0200))
					(not (& temp0 $0400))
					(not (== (ego view?) 0))
				)
				(ego view: 0)
			)
			(
				(and
					(> theGPolyList15 6)
					(not local20)
					(== (ego view?) 0)
					(== local6 1)
				)
				(ego
					looper: MyLooper
					setPri: 14
					moveSpeed: (+ (ego moveSpeed?) 1)
				)
				(self setScript: dying)
			)
			(
			(and (not local0) (= gEgoEdgeHit (ego edgeHit?)))
				(= local209 (theGame setCursor: waitCursor 1))
				(= local0 1)
				(switch gEgoEdgeHit
					(3 (++ global315))
					(2 (-- global314))
					(4 (++ global314))
				)
				(localproc_0bc4)
				(theGame setCursor: local209)
				(ego setCycle: KQ5SyncWalk)
			)
		)
	)
	
	(method (dispose)
		(polyList0 dispose:)
		(polyList1 dispose:)
		(polyList15 dispose:)
		(polyList16 dispose:)
		(polyList210 dispose:)
		(polyList211 dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom n)
		(theMusic stop:)
		(theMusic2 stop:)
		(super newRoom: n)
	)
)

(instance duneLooper of Script
	(properties)
	
	(method (doit)
		(ego loop: [local7 (/ (+ (ego heading?) 13) 90)])
	)
)

(instance dying of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(theMusic2 number: 785 loop: 1 play:)
				(User canControl: 0 canInput: 0)
				(ego setPri: 14 setMotion: PolyPath 163 160 self)
			)
			(2
				(SpeakAudio 316 self)
				(ego
					view: 358
					cel: 0
					cycleSpeed: 2
					normal: 0
					setCycle: End self
				)
				((ego head?) hide:)
			)
			(3
				(switch (ego loop?)
					(0 (= temp0 25))
					(else  (= temp0 60))
				)
				(buzzard2 init: setScript: dying2)
				(buzzard
					init:
					setLoop: 6
					setCycle: Walk
					setMotion: MoveTo (- (ego x?) temp0) (ego y?) self
				)
			)
			(4
				(buzzard setLoop: 4 cel: 4 setCycle: Beg self)
			)
			(5
				(theMusic fade:)
				(theMusic2 fade:)
				(buzzard setLoop: 0 cel: 0)
				(= seconds 3)
			)
			(6)
			(7
				(cls)
				(= deathMessage 317)
				(EgoDead 264 0 End 20)
			)
		)
	)
)

(instance dying2 of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(switch (ego loop?)
					(0 (= temp1 60))
					(else  (= temp1 25))
				)
				(buzzard2
					setLoop: 7
					setCycle: Walk
					setMotion: MoveTo (+ (ego x?) temp1) (ego y?) self
				)
			)
			(1
				(buzzard2 setLoop: 5 cel: 4 setCycle: Beg self)
			)
			(2 (buzzard2 setLoop: 1 cel: 0))
		)
	)
)

(instance getDrink of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (== local6 1)
			(cond 
				((& (= temp0 (ego onControl: 1)) $0200) (ego view: 26))
				((& temp0 $0400) (ego view: 28))
				((not state) (ego view: 0))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= theScript self)
				(ego
					setMotion:
						PolyPath
						(switch (curRoom curPic?)
							(15 (= gPEventX 178))
							(16 (= gPEventX 158))
							(210 (= gPEventX 210))
							(else  (= gPEventX 150))
						)
						(switch (curRoom curPic?)
							(15 (= gPEventY 168))
							(16 (= gPEventY 158))
							(210 (= gPEventY 164))
							(else  (= gPEventY 159))
						)
						self
				)
			)
			(1
				((ego head?) hide:)
				(ego normal: 0 view: 40 cel: 0 cycleSpeed: 3)
				(= cycles 1)
			)
			(2
				(Face ego dummy 5)
				(ego setCycle: End self)
			)
			(3
				(ego loop: (+ (ego loop?) 4) cel: 0 setCycle: End self)
			)
			(4 (= cycles 20))
			(5
				(ego cel: 0 setCycle: End self)
			)
			(6
				(SpeakAudio 311)
				(ego loop: (- (ego loop?) 4) cel: 3 setCycle: Beg self)
			)
			(7
				(ego
					normal: 1
					view: 0
					setCycle: KQ5SyncWalk
					setLoop: -1
					cycleSpeed: 0
				)
				((ego head?) show:)
				(= cycles 2)
			)
			(8
				(if (== (curRoom curPic?) 16)
					(ego setMotion: PolyPath (- (ego x?) 10) (ego y?) self)
				else
					(= cycles 1)
				)
			)
			(9
				(Face ego dummy 5)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance useWell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= local6 1)
					(= theScript self)
					(= gPEventX 145)
					(= gPEventY 175)
				)
				(ego setMotion: PolyPath 145 175 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 357
					loop: 1
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(2
				(ego loop: 2 cel: 0 cycleSpeed: 2 setCycle: End self)
				(rope hide:)
			)
			(3
				(ego loop: 3 cel: 0)
				(rope loop: 4 setCycle: Fwd show:)
				(= seconds 4)
			)
			(4
				(theAudio number: 8068 loop: 1 play: self)
				(rope setCycle: 0)
			)
			(5
				(rope loop: 5 setCycle: 0)
				(ego loop: 6 setCycle: Fwd)
				(= seconds 4)
			)
			(6
				(ego loop: 7 cel: 0 setCycle: End)
				(= seconds 4)
			)
			(7
				(SpeakAudio 311)
				(ego loop: 8 cel: 0 setCycle: End self)
			)
			(8
				(rope loop: 0 cel: 1)
				(ego loop: 1 cel: 1 setCycle: Beg self)
			)
			(9
				(ego
					view: 0
					loop: 7
					cel: 1
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(= cycles 2)
			)
			(10
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance getBoot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= local6 1)
					(= theScript self)
					(= gPEventX 61)
					(= gPEventY 170)
					(ego setMotion: MoveTo 61 170 self)
				else
					(ego setMotion: PolyPath 61 170 self)
				)
			)
			(1
				(= theScript 0)
				((ego head?) hide:)
				(ego normal: 0 view: 56 loop: 3 cel: 0 setCycle: End self)
			)
			(2
				(ego get: 8)
				(SolvePuzzle 2)
				(bootInSand dispose:)
				(ego setCycle: Beg self)
			)
			(3
				(SpeakAudio 9073)
				(ego
					normal: 1
					view: 0
					setCycle: KQ5SyncWalk
					loop: 7
					cel: 1
				)
				((ego head?) show:)
				(= cycles 2)
			)
			(4
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance desert of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(if local20 (SpeakAudio 305) else (SpeakAudio 306))
					(event claimed: 1)
				)
			)
		)
	)
)

(instance skeleton of View
	(properties
		x 50
		y 160
		view 354
		loop 4
		priority 13
		signal $4001
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 307)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 312)
					(event claimed: 1)
				)
				(JOY_DOWN
					(SpeakAudio 314)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance bootInSand of View
	(properties
		x 50
		y 175
		view 354
		loop 4
		cel 1
		priority 14
		signal $4001
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 308)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: getBoot)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance buzzard of Actor
	(properties
		x -17
		y 48
		view 360
		priority 14
		signal $4010
		cycleSpeed 2
	)
)

(instance buzzard2 of Actor
	(properties
		x 337
		y 74
		view 360
		priority 14
		signal $4010
		cycleSpeed 2
	)
)

(instance rope of Prop
	(properties
		x 112
		y 173
		view 357
		signal $4000
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not local20)
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 309)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (not local18)
						(HandsOff)
						(++ local18)
						(= theGPolyList15 0)
						(if (not (Btst 42)) (Bset 42) (SolvePuzzle 2))
						(curRoom setScript: useWell)
					else
						(SpeakAudio 313)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance dummy of Prop
	(properties
		x 207
		y 100
		view 354
		loop 5
		priority 1
		signal $4011
	)
)

(instance oasisWater of Prop
	(properties
		view 353
		priority 5
		signal $4010
		cycleSpeed 4
		detailLevel 3
	)
)

(instance oasis of Prop
	(properties
		x 207
		y 100
		view 354
		priority 10
		signal $4011
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(and
					(not (& (OnControl 4 (event x?) (event y?)) $0200))
					(not (& (OnControl 4 (event x?) (event y?)) $0400))
				)
				(not local20)
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(if (== (curRoom curPic?) 210)
						(SpeakAudio 9072)
					else
						(SpeakAudio 310)
					)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (not local18)
						(HandsOff)
						(++ local18)
						(= theGPolyList15 0)
						(= local19 0)
						(if (not (Btst 42)) (Bset 42) (SolvePuzzle 2))
						(if (== (curRoom curPic?) 210)
							(curRoom setScript: useWell)
						else
							(curRoom setScript: getDrink)
						)
					else
						(SpeakAudio 313)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance polyList0 of List
	(properties)
)

(instance polyList1 of List
	(properties)
)

(instance polyList15 of List
	(properties)
)

(instance polyList16 of List
	(properties)
)

(instance polyList210 of List
	(properties)
)

(instance polyList211 of List
	(properties)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)

(instance poly2 of Polygon
	(properties
		type $0002
	)
)

(instance poly3 of Polygon
	(properties
		type $0002
	)
)

(instance poly4 of Polygon
	(properties
		type $0002
	)
)

(instance poly5 of Polygon
	(properties
		type $0002
	)
)

(instance poly6 of Polygon
	(properties
		type $0002
	)
)

(instance poly7 of Polygon
	(properties
		type $0002
	)
)

(instance poly8 of Polygon
	(properties
		type $0002
	)
)

(instance poly9 of Polygon
	(properties
		type $0002
	)
)

(instance poly10 of Polygon
	(properties
		type $0002
	)
)

(instance poly11 of Polygon
	(properties
		type $0002
	)
)

(instance poly12 of Polygon
	(properties
		type $0002
	)
)

(instance poly13 of Polygon
	(properties
		type $0002
	)
)

(instance poly14 of Polygon
	(properties
		type $0002
	)
)

(instance poly15 of Polygon
	(properties
		type $0002
	)
)

(instance poly16 of Polygon
	(properties
		type $0002
	)
)

(instance poly17 of Polygon
	(properties
		type $0002
	)
)

(instance poly18 of Polygon
	(properties
		type $0002
	)
)
