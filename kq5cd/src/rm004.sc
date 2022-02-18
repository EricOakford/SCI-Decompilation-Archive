;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use Door)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm004 0
)

(local
	[local0 18] = [0 0 118 0 118 78 93 102 78 100 77 98 49 98 49 105 0 100]
	[local18 18] = [204 0 208 89 203 98 184 94 137 116 100 107 96 102 126 85 130]
	[local36 14] = [248 0 274 94 241 108 209 100 208 97 224 85 221]
	[local50 10] = [193 98 202 103 183 112 168 104 177 100]
	[local60 28] = [319 0 319 189 0 189 0 137 59 123 156 180 217 156 268 182 312 113 312 94 297 94 285 88 277 92 254]
	local88
	local89
	local90
	local91
	local92
	local93
	local94
	local95
	local96
	local97
	local98
	local99
	[local100 9] = [1003 58 92 4 11 25 23 31 31]
	[local109 9] = [1007 50 72 3 9 34 24 28 31]
	[local118 14] = [0 5600 1 5601 0 5602 1 5603 0 5604 1 5605]
)
(procedure (localproc_10fa param1 param2 &tmp newEvent)
	(cls)
	(signCel x: param2 cel: param1 init:)
	(RedrawCast)
	(while
		(not
			(OneOf ((= newEvent (Event new:)) type?) 1 4 256)
		)
		(newEvent dispose:)
	)
	(newEvent dispose:)
	(signCel dispose:)
)

(instance rm004 of KQ5Room
	(properties
		picture 4
		west 3
	)
	
	(method (init)
		(super init:)
		(theMusic number: 30 loop: -1 play:)
		(switch prevRoomNum
			(5
				(switch global313
					(1
						(door0 cel: (- (NumCels door0) 1))
						(ego posn: 99 97)
					)
					(2
						(ego posn: 222 95)
						(door1 cel: (- (NumCels door1) 1))
					)
					(3
						(ego posn: 267 90)
						(door2 cel: (- (NumCels door2) 1))
					)
				)
				(HandsOff)
				(self setScript: outTheDoor)
			)
			(else 
				(HandsOff)
				(ego posn: 6 121)
				(self setScript: walkIn4)
			)
		)
		(if (not (Btst 5))
			(Bset 5)
			(if (> (theGame detailLevel:) 0)
				(cart init: setScript: fixCart)
			else
				(cart init: stopUpd:)
			)
		else
			(self setFeatures: alley)
			(if (== ((inventory at: 4) owner?) 4)
				(glint init: setScript: glitter)
			)
		)
		(if (> (theGame detailLevel:) 0)
			(walker1
				setCycle: Walk
				setStep: 1 1
				setScript: walkThruW1
				init:
			)
		)
		(if (> (theGame detailLevel:) 0)
			(walker2
				setCycle: Walk
				setStep: 2 1
				setScript: walkThruBoy
				init:
			)
		)
		(= local93 1)
		(= local95 1)
		(if (> (theGame detailLevel:) 0)
			(walker3
				setCycle: Walk
				setStep: 1 1
				setScript: walkThruW3
				init:
			)
		)
		(if (> (theGame detailLevel:) 0)
			(walker4
				setCycle: Walk
				setStep: 1 1
				setScript: walkThruW2
				init:
			)
		)
		(door0 init: stopUpd:)
		(door1 init: stopUpd:)
		(door2 init: stopUpd:)
		(self
			setFeatures: barrel sign1 sign2 sign3 tailor toystore shoeshop townShip
		)
		(ego
			view: 5
			illegalBits: -32768
			ignoreActors: 1
			normal: 1
			setStep: 2 1
			init:
		)
		((ego head?) hide:)
		(poly1 points: @local0 size: 9)
		(poly2 points: @local18 size: 9)
		(poly3 points: @local36 size: 7)
		(poly4 points: @local50 size: 5)
		(poly5 points: @local60 size: 14)
		(self addObstacle: poly1 poly2 poly3 poly4 poly5)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((= temp0 (self edgeToRoom: (ego edgeHit?))) (curRoom newRoom: temp0))
			((& (ego onControl: 0) $4000) (HandsOff) (= global313 1) (self setScript: openDoor))
			((& (ego onControl: 0) $0010) (HandsOff) (= global313 2) (self setScript: openDoor))
			((& (ego onControl: 0) $0040) (HandsOff) (= global313 3) (self setScript: openDoor))
			(
				(and
					(cast contains: barrelClone)
					(not (ego inRect: 24 80 63 124))
				)
				(cls)
				(fish dispose:)
				(barrelClone dispose:)
			)
		)
	)
	
	(method (dispose)
		(DisposeScript 985)
		(DisposeScript 983)
		(DisposeScript 767)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom n)
		(ego setPri: -1)
		(theMusic fade:)
		(super newRoom: n)
	)
)

(instance walkIn4 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 6))
			(1
				(ego setMotion: MoveTo 35 (ego y?) self)
			)
			(2
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance walkThruBoy of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(-- local96)
				(= seconds (Random 1 5))
			)
			(1
				(if (>= local96 2)
					(-- state)
					(= cycles 10)
				else
					(++ local96)
					(switch local90
						(0 (= temp0 305) (++ local90))
						(1 (= temp0 0) (-- local90))
					)
					(client setMotion: MoveTo temp0 134 self)
					(= state -1)
				)
			)
		)
	)
)

(instance walkThruW1 of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(-- local96)
				(if (>= local91 1)
					(client loop: 1)
				else
					(client loop: 0)
				)
				(= seconds (Random 1 5))
				(= local91 (+ local91 local94))
			)
			(1
				(if (>= local96 2)
					(-- state)
					(= cycles 1)
				else
					(++ local96)
					(switch local91
						(0
							(= temp0 115)
							(= temp1 108)
							(= local94 1)
						)
						(1 (= temp0 165) (= temp1 101))
						(2 (= temp0 263) (= temp1 98))
						(3
							(= temp0 303)
							(= temp1 98)
							(= local94 -1)
						)
					)
					(client setMotion: PolyPath temp0 temp1 self)
					(= state -1)
				)
			)
		)
	)
)

(instance walkThruW2 of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(= seconds (Random 1 5))
				(-- local96)
			)
			(1
				(if (>= local96 2)
					(-- state)
					(= cycles 2)
				else
					(++ local96)
					(switch local92
						(0
							(= temp0 277)
							(= temp1 153)
							(= local92 1)
						)
						(1
							(= temp0 74)
							(= temp1 132)
							(= local92 0)
						)
					)
					(client setMotion: PolyPath temp0 temp1 self)
					(= state -1)
				)
			)
		)
	)
)

(instance walkThruW3 of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(-- local96)
				(if (and (!= local93 3) (not local97))
					(client loop: 3)
					(= seconds (Random 1 5))
				else
					(= cycles 1)
				)
			)
			(1
				(= local97 1)
				(if (>= local96 2)
					(= local97 1)
					(-- state)
					(= cycles 3)
				else
					(= local97 0)
					(++ local96)
					(switch local93
						(0
							(= temp0 300)
							(= temp1 101)
							(= local93 (+ local93 (= local95 1)))
						)
						(1
							(= temp0 187)
							(= temp1 114)
							(= local93 (+ local93 local95))
						)
						(2
							(= temp0 134)
							(= temp1 127)
							(= local93 (+ local93 local95))
							(if (== local95 1) (++ local93))
						)
						(4
							(= temp0 0)
							(= temp1 111)
							(= local93 (+ local93 (= local95 -1)))
							(-- local93)
						)
					)
					(client setMotion: PolyPath temp0 temp1 self)
					(= state -1)
				)
			)
		)
	)
)

(instance glitter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 4 7))
				(= state -1)
				(glint cycleSpeed: 2 setCycle: EndLoop)
			)
		)
	)
)

(instance outTheDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego illegalBits: 0)
				(= cycles 5)
			)
			(1
				(switch global313
					(1
						(ego setMotion: MoveTo 91 104 self)
					)
					(2
						(ego setMotion: MoveTo 205 100 self)
					)
					(else 
						(ego setMotion: MoveTo 284 90 self)
					)
				)
			)
			(2
				(if (== global313 3)
					(ego setMotion: MoveTo 284 95 self)
				else
					(self cue:)
				)
			)
			(3
				(switch global313
					(1
						(door0 cycleSpeed: 2 setCycle: BegLoop self)
					)
					(2
						(door1 cycleSpeed: 2 setCycle: BegLoop self)
					)
					(else 
						(door2 cycleSpeed: 2 setCycle: BegLoop self)
					)
				)
				(theAudio number: 8124 loop: 1 play:)
			)
			(4
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance fixCart of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cart loop: 0 cycleSpeed: 2 setCycle: Forward)
				(= cycles 8)
				(= state (- (Random 0 2) 1))
			)
			(1
				(cart loop: 1 setCycle: Forward)
				(= cycles 8)
				(= state (- (Random 0 2) 1))
			)
			(2
				(cart loop: 2 setCycle: EndLoop self)
				(= state (- (Random 0 2) 1))
			)
		)
	)
)

(instance openDoor of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (not (ego mover?))
			(cond 
				((== state 0) (ego y: (+ (ego y?) 2)) (self init:))
				((== state 2) (ego y: (+ (ego y?) 2)) (self start: 2 init:))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch global313
					(1
						(ego setMotion: PolyPath 100 98 self)
					)
					(2
						(ego setMotion: PolyPath 207 100 self)
					)
					(else 
						(ego setMotion: PolyPath 277 93 self)
					)
				)
			)
			(1
				(ego setMotion: 0 illegalBits: 0)
				(switch global313
					(1
						(door0 startUpd: setCycle: EndLoop self)
					)
					(2
						(door1 startUpd: setCycle: EndLoop self)
					)
					(else 
						(door2 startUpd: setCycle: EndLoop self)
					)
				)
				(theAudio number: 8122 loop: 1 play:)
			)
			(2
				(switch global313
					(1
						(ego setMotion: MoveTo 109 91 self)
					)
					(2
						(ego setMotion: MoveTo 222 95 self)
					)
					(else 
						(ego setPri: 2 setMotion: MoveTo 267 90 self)
					)
				)
			)
			(3
				(ego illegalBits: -32768)
				(curRoom newRoom: 5)
			)
		)
	)
)

(instance getFish of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== state 1) (not (ego mover?)))
			(self start: 1 init:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cls)
				(fish dispose:)
				(barrelClone dispose:)
				(= seconds 3)
			)
			(1
				(ego setMotion: PolyPath 51 106 self)
			)
			(2
				(ego
					normal: 0
					view: 154
					loop: 0
					cel: 0
					cycleSpeed: 2
					setAvoider: 0
					setCycle: EndLoop self
				)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(ego get: 5)
				(SolvePuzzle 2)
				(ego
					normal: 1
					view: 5
					cycleSpeed: 0
					loop: 1
					setCycle: KQ5SyncWalk
				)
				(RedrawCast)
				(SpeakAudio 203)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance getCoin of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== state 0) (not (ego mover?)))
			(ego y: (+ (ego y?) 2))
			(client setScript: getCoin)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 72 100 self)
			)
			(1
				(ego
					normal: 0
					view: 154
					setLoop: 1
					cel: 0
					cycleSpeed: 2
					setAvoider: 0
					setCycle: EndLoop self
				)
			)
			(2
				(glint dispose:)
				(ego setCycle: BegLoop self)
			)
			(3
				(ego get: 4)
				(SolvePuzzle 2)
				(ego
					normal: 1
					view: 5
					setLoop: -1
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				(RedrawCast)
				(SpeakAudio 9064)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance talkMan of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== state 0) (not (ego mover?)))
			(self init:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 68 107 self)
			)
			(1
				(Face ego cart 5)
				(ego setAvoider: 0)
				(= cycles 3)
			)
			(2
				(proc762_0 @local100 @local109 @local118 self)
			)
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance lookInBarrel of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== state 0) (not (ego mover?)))
			(ego y: (+ (ego y?) 2))
			(client setScript: lookInBarrel)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 51 106 self)
			)
			(1
				(ego loop: 7 cel: 1)
				(= cycles 10)
			)
			(2
				(if (== ((inventory at: 5) owner?) 4)
					(fish init:)
					(barrelClone init:)
					(RedrawCast)
					(SpeakAudio 192)
				else
					(SpeakAudio 193)
				)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance signCel of Prop
	(properties
		x 160
		y 100
		view 157
		priority 15
		signal $0010
	)
)

(instance townShip of RFeature
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
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 194)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance alley of RFeature
	(properties
		nsTop 61
		nsLeft 40
		nsBottom 96
		nsRight 78
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
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 195)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 205)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance sign1 of RFeature
	(properties
		nsTop 66
		nsLeft 86
		nsBottom 76
		nsRight 101
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
					(localproc_10fa 0 60)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance sign2 of RFeature
	(properties
		nsTop 65
		nsLeft 205
		nsBottom 73
		nsRight 217
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
					(localproc_10fa 2 200)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance sign3 of RFeature
	(properties
		nsTop 63
		nsLeft 286
		nsBottom 72
		nsRight 297
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
					(localproc_10fa 1 280)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance door0 of Door
	(properties
		x 101
		y 93
		view 152
		loop 3
		priority 2
		signal $4011
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
			(cls)
			(switch (event message?)
				(JOY_RIGHT
					(= global313 1)
					(HandsOff)
					(curRoom setScript: openDoor)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance door1 of Door
	(properties
		x 210
		y 96
		view 152
		loop 1
		priority 2
		signal $4011
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
			(cls)
			(switch (event message?)
				(JOY_RIGHT
					(= global313 2)
					(HandsOff)
					(curRoom setScript: openDoor)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance door2 of Door
	(properties
		x 275
		y 90
		view 152
		loop 2
		priority 2
		signal $4011
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
			(cls)
			(switch (event message?)
				(JOY_RIGHT
					(= global313 3)
					(HandsOff)
					(curRoom setScript: openDoor)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance tailor of RFeature
	(properties
		nsTop 46
		nsLeft 75
		nsBottom 102
		nsRight 181
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
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 196)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance toystore of RFeature
	(properties
		nsTop 49
		nsLeft 181
		nsBottom 102
		nsRight 252
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
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 197)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance shoeshop of RFeature
	(properties
		nsTop 49
		nsLeft 252
		nsBottom 97
		nsRight 288
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
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 198)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance cart of Prop
	(properties
		x 63
		y 97
		view 155
		priority 2
		signal $0010
		detailLevel 3
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
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 199)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 9065)
					(event claimed: 1)
				)
				(JOY_DOWN
					(cond 
						((not local88) (++ local88) (curRoom setScript: talkMan))
						(local99 (proc762_1 @local109 5607))
						(else (proc762_1 @local109 5606) (= local99 1))
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance barrel of RFeature
	(properties
		nsTop 95
		nsLeft 37
		nsBottom 105
		nsRight 50
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
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 200)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (not (cast contains: fish))
						(curRoom setScript: lookInBarrel)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance barrelClone of Prop
	(properties
		x 30
		y 81
		view 157
		loop 1
		signal $0001
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
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(if (== ((inventory at: 5) owner?) 4)
						(SpeakAudio 192)
					else
						(SpeakAudio 193)
					)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (== ((inventory at: 5) owner?) 4)
						(HandsOff)
						(curRoom setScript: getFish)
					else
						(SpeakAudio 204)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance fish of Prop
	(properties
		x 34
		y 61
		view 157
		loop 1
		cel 1
		priority 6
		signal $4011
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
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 192)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: getFish)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance walker1 of Actor
	(properties
		y 112
		view 156
		illegalBits $0800
		moveSpeed 1
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
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 201)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 9066)
					(event claimed: 1)
				)
				(JOY_DOWN
					(womanWords doit:)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance walker2 of Actor
	(properties
		x -20
		y 120
		view 158
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
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 201)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 9066)
					(event claimed: 1)
				)
				(JOY_DOWN
					(kidWords doit:)
					(event claimed: 1)
				)
			)
		)
	)
	
	(method (checkDetail param1)
		(super checkDetail: &rest)
		(cond 
			((not detailLevel))
			((& signal $0001) (= signal (| signal $0100)))
			((& signal $0002) (= signal (& signal $feff)))
		)
	)
)

(instance walker3 of Actor
	(properties
		x 300
		y 101
		view 159
		illegalBits $0800
		moveSpeed 1
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
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 201)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 9066)
					(event claimed: 1)
				)
				(JOY_DOWN
					(manWords doit:)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance walker4 of Actor
	(properties
		x 73
		y 132
		view 153
		illegalBits $0800
		moveSpeed 1
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
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 201)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 9066)
					(event claimed: 1)
				)
				(JOY_DOWN
					(womanWords doit:)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance glint of Prop
	(properties
		x 65
		y 98
		view 152
		loop 4
		priority 1
		signal $4010
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
			(cls)
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 202)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: getCoin)
					(event claimed: 1)
				)
			)
		)
	)
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

(instance manWords of Code
	(properties)
	
	(method (doit)
		(switch (Random 1 2)
			(1 (SpeakAudio 5701))
			(2 (SpeakAudio 5705))
		)
	)
)

(instance womanWords of Code
	(properties)
	
	(method (doit)
		(switch (Random 1 3)
			(1 (SpeakAudio 5702))
			(2 (SpeakAudio 5703))
			(3 (SpeakAudio 5704))
		)
	)
)

(instance kidWords of Code
	(properties)
	
	(method (doit)
		(switch (Random 1 3)
			(1 (SpeakAudio 4007))
			(2 (SpeakAudio 4008))
			(3 (SpeakAudio 5706))
		)
	)
)

(instance poly5 of Polygon
	(properties
		type $0002
	)
)
