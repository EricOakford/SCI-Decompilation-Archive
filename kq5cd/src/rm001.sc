;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use Door)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use RFeature)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm001 0
)

(local
	[local0 18] = [0 155 0 145 61 138 116 138 116 144 98 150 89 159 78 161 50 153]
	[local18 16] = [259 0 258 115 216 112 208 106 192 106 166 115 165 104 250 27]
	[local34 16] = [253 0 156 107 139 112 112 121 55 129 60 120 0 118]
	[local50 18] = [319 0 319 189 80 189 104 172 304 175 310 140 305 122 284 121 285]
	[local68 16] = [182 125 183 111 187 108 209 109 215 113 209 126 214 131 178 131]
	[local84 8] = [97 126 133 126 133 134 97 134]
	[local92 8] = [29 185 30 189 0 189 0 183]
	[local100 18] = [0 163 32 163 92 167 97 172 98 175 76 176 62 183 30 181 0 174]
	[local118 10] = [301 128 303 141 286 138 283 125 292 127]
	[local128 8] = [174 148 173 156 155 158 156 148]
	[local136 12] = [152 145 144 158 131 164 101 164 106 151 138 140]
	[local148 8] = [261 0 282 0 281 123 262 118]
	theScript
	local157
	local158
	gEgoMoverX
	gEgoMoverY
	gEgoView
	gEgoCycleSpeed
	local163
	[local164 9] = [1000 93 46 4 11 24 19 23 30]
	[local173 10] = [1 9106 0 7040 1 9107 0 7041]
	[local183 9] = [1003 140 100 4 11 25 23 31 31]
)
(instance rm001 of KQ5Room
	(properties
		picture 1
		south 2
		west 8
	)
	
	(method (init &tmp temp0 egoY temp2)
		(super init:)
		(= local163
			(if
				(or
					(== prevRoomNum 109)
					(== prevRoomNum 659)
					(== prevRoomNum 611)
					(== prevRoomNum 700)
				)
				(not (GameIsRestarting))
			else
				0
			)
		)
		(NormalEgo)
		(ego cycleSpeed: (ego moveSpeed?))
		(= global103 0)
		(HandsOn)
		(Bclr 22 33)
		(= global320 107)
		(= global321 94)
		(self setRegions: 202)
		(= global325 3020)
		(if (not (ego has: 28)) (ego get: 28))
		(theMusic loop: -1 number: 24 play:)
		(if local163
			(= temp0 160)
			(= egoY 108)
		else
			(switch prevRoomNum
				(south
					(= temp0 60)
					(= egoY 187)
				)
				(east
					(= temp0 74)
					(= egoY 159)
				)
				(west
					(= egoY (ego y?))
					(= temp0 0)
					(if (< egoY 168)
						(HandsOff)
						(self setScript: enterFromTree)
					else
						(ego setMotion: MoveTo (+ (ego x?) 3) (ego y?))
					)
				)
				(76 (= temp0 169) (= egoY 98))
				(else 
					(= temp0 140)
					(= egoY 136)
				)
			)
		)
		(LoadMany 128 27 9 0 2)
		(ego
			view: (if (== prevRoomNum south) 0 else 2)
			posn: temp0 egoY
			setStep: 2 1
			setLoop: -1
			illegalBits: -16384
			init:
		)
		(door
			setPri: 1
			cel: (if (== prevRoomNum 76) (- (NumCels door) 1) else 0)
			init:
			stopUpd:
		)
		(rail1 init: stopUpd:)
		(rail2 init: stopUpd:)
		(self
			setFeatures: theWindows ornament well pond bridge smallDoor house
		)
		(if (== prevRoomNum 76)
			(HandsOff)
			(self setScript: leaveCrispins)
		)
		(if local163
			(HandsOff)
			(self setScript: introRoomScript)
		)
		(poly2 points: @local0 size: 9)
		(poly3 points: @local18 size: 8)
		(poly4 points: @local34 size: 8)
		(poly5 points: @local50 size: 9)
		(poly6 points: @local68 size: 8)
		(poly7 points: @local84 size: 4)
		(poly8 points: @local92 size: 4)
		(poly9 points: @local100 size: 9)
		(poly11 points: @local118 size: 5)
		(poly12 points: @local128 size: 4)
		(poly13 points: @local136 size: 6)
		(poly14 points: @local148 size: 4)
		(self
			addObstacle:
				poly2
				poly3
				poly4
				poly5
				poly6
				poly7
				poly8
				poly9
				poly11
				poly12
				poly13
				poly14
		)
		(if (== (theGame detailLevel:) 3)
			(water setCycle: Fwd init:)
			(rippling setCycle: Fwd init:)
			(rippling2 setCycle: Fwd init:)
		)
	)
	
	(method (doit &tmp temp0 temp1 temp2)
		(cond 
			((& (= temp2 (ego onControl: 1)) $0008) (ego view: 27))
			((== (ego view?) 27) (ego view: 2))
		)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				((ScriptID 202 2) register: (ego edgeHit?))
				(self setScript: (ScriptID 202 2))
			)
			((or (& temp2 $0010) (& temp2 $0002)) (curRoom setScript: changeSize))
			((& temp2 $01c0) (HandsOff) (self setScript: falling))
			((ego inRect: 80 135 108 141) (ego setPri: 9))
			((and (& temp2 $0008) (< (ego x?) 99)) (ego setPri: 9))
			(
				(and
					(& temp2 $0008)
					(< 99 (ego x?))
					(< (ego x?) 150)
				)
				(ego setPri: 12)
			)
			((== (ego view?) 0) (ego setPri: 14))
			((and (& temp2 $0008) (> (ego x?) 149)) (ego setPri: -1))
			((& temp2 $0001) (ego setPri: -1))
		)
	)
	
	(method (dispose)
		(ego illegalBits: -32768)
		(DisposeScript 991)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (event claimed?) (not (== (event type?) 16384)))
			(return)
		)
	)
	
	(method (newRoom n)
		(ego setPri: -1)
		(super newRoom: n)
	)
)

(instance enterFromTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 47 143 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance leaveCrispins of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 160 109 self)
			)
			(1 (door setCycle: Beg self))
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance changeSize of Script
	(properties)
	
	(method (changeState newState &tmp egoMover)
		(switch (= state newState)
			(0
				(= gEgoView (ego view?))
				(= egoMover (ego mover?))
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(if (== gEgoView 2) (ego setPri: 1 illegalBits: 0))
				(if (IsObject egoMover)
					(= gEgoMoverX (egoMover x?))
					(= gEgoMoverY (egoMover y?))
				)
				(HandsOff)
				(rail1 setPri: 1)
				(if (== gEgoView 2)
					(ego
						signal: (| (ego signal?) $4810)
						setMotion: PolyPath 95 181 self
					)
				else
					(= cycles 1)
				)
			)
			(1
				(ego
					view: 9
					normal: 0
					cycleSpeed: (ego moveSpeed?)
					setPri: -1
					setLoop: (if (== gEgoView 2) 0 else 1)
					setStep: 4 3
					illegalBits: 0
					cel: 0
					setCycle: End self
				)
				((ego head?) hide:)
				(if (== gEgoView 2)
					(ego setMotion: MoveTo 63 186)
				else
					(ego setMotion: MoveTo 92 174)
				)
			)
			(2
				(if (== gEgoView 0)
					(ego
						view: 2
						setLoop: -1
						setCycle: KQ5SyncWalk
						setPri: 1
						cycleSpeed: gEgoCycleSpeed
						setMotion: PolyPath 100 170 self
					)
					((ego head?) show:)
				else
					(= cycles 1)
				)
			)
			(3
				(ego
					view: (if (== gEgoView 0) 2 else 0)
					cycleSpeed: gEgoCycleSpeed
					cel: 0
					normal: 1
					setCycle: KQ5SyncWalk
					setLoop: -1
					setPri: 12
					illegalBits: -16384
					signal: (& (ego signal?) $f7ff)
				)
				((ego head?) show:)
				(if (== gEgoView 0)
					(ego loop: 7 cel: 3 setStep: 2 1)
				else
					(ego loop: 7 cel: 2 setStep: 3 2)
				)
				(if theScript
					(ego setMotion: PolyPath local157 local158 theScript)
				else
					(if
						(and
							(not (& (OnControl 4 gPEventX gPEventY) $0002))
							(not (& (OnControl 4 gPEventX gPEventY) $0010))
							(not (& (OnControl 4 gPEventX gPEventY) $0020))
							(not (& (OnControl 4 gPEventX gPEventY) $0800))
						)
						(ego setMotion: PolyPath gPEventX gPEventY)
					)
					(HandsOn)
				)
				(rail1 setPri: 10)
				(client setScript: 0)
			)
		)
	)
)

(instance falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ego head?) hide:)
				(ego
					normal: 0
					view: 90
					ignoreControl: -32768
					cel: 0
					setCycle: End self
					setMotion: JumpTo 196 133 self
				)
			)
			(1)
			(2
				(ego
					normal: 1
					view: 2
					setCycle: KQ5SyncWalk
					observeControl: -32768
				)
				((ego head?) show:)
				(= cycles 3)
			)
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance openDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local157 160)
				(= local158 109)
				(= theScript self)
				(ego illegalBits: 0 setMotion: PolyPath 160 109 self)
			)
			(1
				(= local157 0)
				(= local158 0)
				(= theScript 0)
				(door setCycle: End self)
			)
			(2
				(ego setMotion: MoveTo 169 98 self)
			)
			(3
				(Bset 37)
				(curRoom newRoom: 76)
			)
		)
	)
)

(instance lookWell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local157 253)
				(= local158 118)
				(= theScript self)
				(ego setMotion: PolyPath 253 118 self)
			)
			(1
				(= local157 0)
				(= local158 0)
				(= theScript 0)
				(ego loop: 7 cel: 3)
				(= cycles 2)
			)
			(2 (proc0_29 162) (= cycles 1))
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance getDrink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local157 188)
				(= local158 147)
				(= theScript self)
				(ego setMotion: PolyPath 188 147 self)
			)
			(1
				(= local157 0)
				(= local158 0)
				(= theScript 0)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 45
					loop: 2
					cel: 0
					cycleSpeed: 3
					setCycle: End self
				)
			)
			(2
				(ego loop: (+ (ego loop?) 4) cel: 0 setCycle: End self)
			)
			(3 (= cycles 20))
			(4
				(ego cel: 0 setCycle: End self)
			)
			(5
				(ego loop: (- (ego loop?) 4) cel: 3 setCycle: Beg self)
			)
			(6
				(proc0_29 9070)
				(ego
					normal: 1
					view: 2
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
				)
				((ego head?) show:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance rail1 of Prop
	(properties
		x 94
		y 156
		view 770
		loop 4
		priority 10
		signal $4010
	)
)

(instance rail2 of Prop
	(properties
		x 123
		y 161
		view 770
		loop 4
		cel 1
		priority 12
		signal $4010
	)
)

(instance door of Door
	(properties
		x 170
		y 96
		view 770
		loop 5
	)
	
	(method (handleEvent event &tmp temp0)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 163)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (Btst 37)
						(proc0_29 171)
					else
						(HandsOff)
						(ego setScript: openDoor)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance theWindows of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0200))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 164)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance house of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0004))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 165)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance well of RFeature
	(properties
		nsTop 89
		nsLeft 246
		nsBottom 109
		nsRight 264
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
					(proc0_29 166)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(ego setScript: lookWell)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance ornament of RFeature
	(properties
		nsTop 95
		nsLeft 105
		nsBottom 128
		nsRight 125
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
					(proc0_29 167)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(proc762_1 @local164 3021)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance pond of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0008))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 168)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(ego setScript: getDrink)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance smallDoor of RFeature
	(properties
		nsTop 111
		nsLeft 186
		nsBottom 129
		nsRight 209
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
					(proc0_29 169)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(proc762_1 @local164 3022)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance bridge of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $1000))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 170)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance water of Prop
	(properties
		x 256
		y 154
		view 770
		priority 1
		signal $0010
		cycleSpeed 4
	)
)

(instance rippling of Prop
	(properties
		x 158
		y 168
		view 770
		loop 1
		priority 1
		signal $4010
		cycleSpeed 9
	)
)

(instance rippling2 of Prop
	(properties
		x 79
		y 154
		view 770
		loop 2
		priority 1
		signal $4010
		cycleSpeed 2
	)
)

(instance poly2 of Polygon
	(properties)
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
	(properties)
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

(instance introRoomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (- (ego x?) 1) (+ (ego y?) 1))
				(= cycles 15)
			)
			(1
				(ego
					cycleSpeed: 0
					moveSpeed: 0
					setLoop: -1
					setMotion: MoveTo 140 136 self
				)
			)
			(2
				(proc762_0 @local183 @local164 @local173 self)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)
