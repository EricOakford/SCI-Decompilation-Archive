;;; Sierra Script 1.0 - (do not remove this comment)
(script# 24)
(include game.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQCursor)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm024 0
)

(local
	local0
	local1
	local2
	local3
	[local4 8] = [99 189 115 177 319 177 319 189]
	[local12 12] = [0 189 0 136 86 136 59 150 85 156 13 189]
	[local24 16] = [0 0 319 0 319 102 219 109 204 128 153 128 141 121 0 121]
	[local40 20] = [0 189 0 0 319 0 319 101 253 102 166 121 109 121 60 149 86 154 12 189]
	[local60 8] = [0 5091 0 5092 0 5093]
	[local68 9] = [1003 115 63 4 11 25 23 31 31]
	[local77 9] = [1011 125 55 4 11 28 26 24 34]
	[local86 14] = [0 5083 1 5084 0 5085 1 5086 0 5087 1 5088]
)
(procedure (localproc_000e)
	(return
		(switch (inventory indexOf: (theIconBar curInvIcon?))
			(17
				(SolvePuzzle 4)
				(ego put: 17 24)
				(HandsOff)
				(ego get: 18)
				(curRoom setScript: squeeze)
				(return 1)
			)
			(14
				(if (not (curRoom script?))
					(SolvePuzzle 2)
					(curRoom setScript: throwEmerald)
				)
				(return 1)
			)
			(28 (return 0))
			(else 
				(if
					(and
						(== ((inventory at: 6) owner?) 200)
						(not (Btst 56))
					)
					(SpeakAudio 379)
					(return 1)
				)
			)
		)
	)
)

(instance rm024 of KQ5Room
	(properties
		picture 24
		east 22
		south 25
		west 81
	)
	
	(method (init)
		(super init:)
		(switch prevRoomNum
			(east (ego posn: 315 108))
			(west (ego posn: 20 123))
			(208
				(ego posn: gGEgoX gGEgoY view: 0)
				(NormalEgo 0 0)
			)
			(else  (ego posn: 83 187))
		)
		(self setFeatures: path24 forest setRegions: 200 551 552)
		(if (== ((inventory at: 17) owner?) 24)
			(puddle posn: 138 126 init: stopUpd:)
		)
		(if (Btst 56)
			(jinx posn: 176 126 init: stopUpd:)
			(self addObstacle: poly1 poly2 poly3)
		else
			(jinx init: stopUpd:)
			(self addObstacle: poly1 poly4)
		)
		(ego view: 0 init:)
		((= local2 (Clone elf)) posn: 56 77 init:)
		((= local3 (Clone elf)) posn: 175 81 init:)
		(elf init: setScript: blink)
		(if
		(or (!= ((inventory at: 6) owner?) 200) (Btst 56))
			(elf hide:)
			(local2 hide:)
			(local3 hide:)
		)
		(poly1 points: @local4 size: 4)
		(poly2 points: @local12 size: 6)
		(poly3 points: @local24 size: 8)
		(poly4 points: @local40 size: 10)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(if local0 (Bset 13))
				((ScriptID 200 1) register: temp0)
				(self setScript: (ScriptID 200 1) 0 (ego edgeHit?))
			)
		)
	)
	
	(method (dispose)
		(DisposeScript 991)
		(DisposeScript 982)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
			(
				(and
					(== (event type?) 16384)
					(MousedOn ego event)
					(== (event message?) verbUse)
				)
				(event claimed: (localproc_000e))
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(theMusic2 fade:)
	)
)

(instance blink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(elf setCycle: EndLoop)
				(= seconds (Random 2 4))
			)
			(1
				(local2 setCycle: EndLoop)
				(= seconds (Random 2 4))
			)
			(2
				(local3 setCycle: EndLoop)
				(= seconds (Random 2 4))
			)
			(3 (self init:))
		)
	)
)

(instance squeeze of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego illegalBits: 0 setMotion: PolyPath 125 126 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 469
					loop: 0
					cel: 0
					x: (+ (ego x?) 5)
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(theAudio number: 8077 loop: 1 play:)
			)
			(2
				(puddle posn: (+ (ego x?) 13) (ego y?) init:)
				(= cycles 5)
			)
			(3
				(SpeakAudio 380)
				(ego
					normal: 1
					view: 0
					illegalBits: -32768
					loop: 0
					x: (- (ego x?) 5)
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance throwEmerald of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (> state 27) (== (ego x?) 81))
			(Face ego jinx 5)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 122 126 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 469
					cel: 0
					cycleSpeed: 1
					x: (+ (ego x?) 8)
				)
				(if (== numEmeralds 1)
					(ego loop: 2 setCycle: EndLoop self)
				else
					(ego loop: 1 setCycle: CycleTo 5 1 self)
				)
			)
			(2
				(if (== numEmeralds 1)
					(self cue:)
				else
					(ego setCycle: EndLoop)
					(gem x: 145 y: 107 init:)
					(if (== numEmeralds 2)
						(gem setMotion: JumpTo 164 120 self)
					else
						(gem setMotion: JumpTo 184 120 self)
					)
				)
			)
			(3
				(if (== numEmeralds 1)
					(self cue:)
				else
					(gem setMotion: JumpTo (+ (gem x?) 5) 120 self)
					(theAudio number: 8085 loop: 1 play:)
				)
			)
			(4
				(if (== (-- numEmeralds) 0)
					(gem
						init:
						x: 140
						y: 124
						setPri: (+ (puddle priority?) 1)
					)
					(ego put: 14 24)
					(theAudio number: 8105 loop: 1 play:)
				else
					((inventory at: 14) cel: (+ (- numEmeralds 1) 4))
					((inventory at: 14)
						cursor:
							(switch numEmeralds
								(3 threeEmeraldCursor)
								(2 twoEmeraldCursor)
								(1 oneEmeraldCursor)
							)
						yourself:
					)
					((theIconBar curIcon?)
						cursor: ((inventory at: 14) cursor?)
						yourself:
					)
				)
				(gem setScript: glintScript)
				(= cycles 30)
			)
			(5
				(elf
					loop: 2
					cel: 0
					setStep: 2 4
					setCycle: EndLoop
					cycleSpeed: 4
					setMotion: MoveTo 214 113 self
					setScript: 0
				)
				(theMusic2 number: 88 loop: 1 vol: 127 play:)
			)
			(6
				(elf
					setLoop: 10
					cel: 0
					setStep: 2 2
					setCycle: Walk
					cycleSpeed: 1
					ignoreActors: 1
					setMotion: MoveTo (+ (gem x?) 7) (+ (gem y?) 3) self
				)
				(if
					(and
						(== numEmeralds 0)
						(== ((inventory at: 17) owner?) 24)
					)
					(= state 12)
				)
			)
			(7
				(elf setLoop: 3 cel: 0 setCycle: CycleTo 5 1 self)
			)
			(8
				(gem dispose:)
				(ego loop: 3 cel: 0 setCycle: CycleTo 1 1)
				(elf setCycle: EndLoop self)
			)
			(9
				(if (== numEmeralds 0)
					(SpeakAudio 5094 self)
				else
					(= cycles 1)
				)
			)
			(10
				(cls)
				(ego setCycle: BegLoop)
				(elf
					setLoop: 4
					cel: 0
					moveSpeed: 0
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 214 113 self
				)
				(theMusic2 number: 89 loop: 1 vol: 127 play:)
			)
			(11
				(elf
					setLoop: 12
					cel: 0
					setCycle: EndLoop
					setMotion: MoveTo 222 76 self
				)
				(ego
					normal: 1
					view: 0
					cycleSpeed: 0
					loop: 0
					x: (- (ego x?) 8)
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
			)
			(12
				(elf view: 468 loop: 1 posn: 222 76 setScript: blink)
				(if numEmeralds
					(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
				else
					(theGame setCursor: arrowCursor)
				)
				(HandsOn)
				(client setScript: 0)
			)
			(13
				(elf loop: 14 cel: 0 setCycle: CycleTo 5 1 self)
				(theMusic3 number: 84 loop: -1 vol: 127 play:)
			)
			(14
				(gem dispose:)
				(elf setCycle: EndLoop self)
			)
			(15
				(elf cel: 0 setCycle: EndLoop self)
				(ego cycleSpeed: 0)
			)
			(16
				(theMusic3 stop:)
				(theMusic2 number: 86 loop: -1 vol: 127 play:)
				(ego loop: 3 cel: 0 setCycle: CycleTo 1 1 self)
			)
			(17
				(elf hide:)
				(ego setCycle: CycleTo 4 1 self)
			)
			(18
				(ego setCycle: EndLoop)
				(elf
					show:
					loop: 5
					posn: 140 110
					setMotion: 0
					setCycle: Forward
				)
				(local2 dispose:)
				(local3 dispose:)
				(= cycles 30)
			)
			(19
				(proc762_0 @local77 @local68 @local86 self)
			)
			(20
				(elf hide:)
				(ego setPri: -1 loop: 4 cel: 0 setCycle: CycleTo 3 1 self)
				(theMusic2 stop:)
			)
			(21
				(ego setPri: -1 cel: (ego lastCel:))
				(elf
					posn: (+ (ego x?) 14) (ego y?)
					setLoop: 5
					cel: 0
					show:
				)
				(= cycles 1)
			)
			(22
				(elf
					setLoop: 10
					setStep: 2 2
					setCycle: Walk
					setMotion: MoveTo 111 130 self
				)
				(ego
					normal: 1
					view: 0
					cycleSpeed: 0
					moveSpeed: 0
					loop: 0
					setStep: 3 2
					ignoreControl: 16384
				)
				((ego head?) show:)
			)
			(23
				(Face ego jinx)
				((ego head?) show:)
				(jinx setCycle: EndLoop)
				(SpeakAudio 5089 0 1)
				(= seconds 7)
			)
			(24
				(elf view: 469 setLoop: 6 cel: 0 setCycle: CycleTo 6 1 self)
			)
			(25
				(gem
					init:
					loop: 15
					x: 98
					y: 115
					setScript: 0
					setMotion: JumpTo 67 (gem y?)
				)
				(elf setCycle: EndLoop)
				(jinx loop: 13)
				(jinxHead
					setPri: (+ (jinx priority?) 1)
					init:
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(26
				(cls)
				(jinxHead dispose:)
				(SpeakAudio 5090 0 1)
				(gem dispose:)
				(jinx
					setLoop: 9
					cel: 0
					setCycle: Walk
					cycleSpeed: 2
					setMotion: MoveTo 176 126
				)
				(= cycles 10)
			)
			(27
				(ego
					setCycle: KQ5SyncWalk
					setLoop: -1
					moveSpeed: 0
					setMotion: MoveTo 81 144
				)
				((ego head?) show:)
				(= cycles 25)
			)
			(28
				(cls)
				(jinx
					setLoop: 9
					cel: 0
					setCycle: Walk
					setMotion: MoveTo 176 126 self
				)
			)
			(29
				(jinx setLoop: 10 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(30 (jinx setCycle: BegLoop self))
			(31 (jinx setCycle: EndLoop self))
			(32 (= cycles 10))
			(33
				(jinx setLoop: 7)
				(jinx cel: (jinx lastCel:) setCycle: BegLoop self)
			)
			(34
				(Bset 56)
				(jinx loop: 7 cel: 0)
				((curRoom obstacles?) delete: poly4)
				(curRoom addObstacle: poly2 poly3)
				(= cycles 1)
			)
			(35
				(elf
					view: 468
					setLoop: 10
					setCycle: Walk
					setMotion: MoveTo 58 120 self
				)
			)
			(36
				(HandsOn)
				(if numEmeralds
					(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
				else
					(theGame setCursor: arrowCursor)
				)
				(elf dispose:)
				(ego
					moveSpeed: (theGame egoMoveSpeed?)
					illegalBits: -32768
					setCycle: KQ5SyncWalk
					setLoop: -1
				)
				(client setScript: 0)
			)
		)
	)
)

(instance glintScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gem setCycle: EndLoop)
				(= cycles (Random 10 25))
			)
			(1 (self init:))
		)
	)
)

(instance path24 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0002))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 371)
					(event claimed: 1)
				)
				(verbUse
					(event claimed: (localproc_000e))
				)
			)
		)
	)
)

(instance forest of RFeature
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
				(verbLook
					(SpeakAudio 372)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance elf of Actor
	(properties
		x 222
		y 76
		view 468
		loop 1
		signal $4000
		cycleSpeed 4
		illegalBits $0000
	)
	
	(method (handleEvent event &tmp temp0)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
				(!= ((inventory at: 6) owner?) 200)
				(Btst 56)
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 373)
					(event claimed: 1)
				)
				(verbDo
					(if (not local1) (SpeakAudio 377))
					(event claimed: 1)
				)
				(verbTalk
					(cond 
						((not (Btst 13))
							(event claimed: 1)
							(if (not local0)
								(++ local0)
								(proc762_0 @local68 @local68 @local60)
							else
								(SpeakAudio 383)
							)
						)
						((not local0) (++ local0) (SpeakAudio 384))
						(else (SpeakAudio 383))
					)
					(event claimed: 1)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(14
							(if (!= ((inventory at: 6) owner?) 200)
								(SpeakAudio 381)
								(event claimed: 1)
							else
								(if (not (curRoom script?))
									(SolvePuzzle 2)
									(curRoom setScript: throwEmerald)
								)
								(event claimed: 1)
							)
						)
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 382)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance puddle of Prop
	(properties
		x 138
		y 126
		view 469
		loop 12
		priority 1
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
			(switch (event message?)
				(verbLook
					(SpeakAudio 374)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance jinx of Actor
	(properties
		x 71
		y 126
		view 469
		loop 7
		signal $4000
		cycleSpeed 2
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
				(verbLook
					(if (< (self x?) 100)
						(SpeakAudio 375)
					else
						(SpeakAudio 376)
					)
					(event claimed: 1)
				)
				(verbDo
					(if (> (self x?) 100)
						(SpeakAudio 378)
						(event claimed: 1)
					)
				)
				(verbTalk
					(if (> (self x?) 100)
						(SpeakAudio 385)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance jinxHead of Prop
	(properties
		x 75
		y 126
		z 8
		view 469
		loop 8
		signal $4001
		cycleSpeed 3
	)
)

(instance gem of Actor
	(properties
		view 469
		loop 14
		signal $4000
		illegalBits $0000
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

(instance oneEmeraldCursor of KQCursor
	(properties
		view 941
		loop 5
		cel 7
		x 4
		y 4
	)
	
	(method (init)
		(if global400 (self number: 27 yourself:))
		(super init: &rest)
	)
)

(instance twoEmeraldCursor of KQCursor
	(properties
		view 941
		loop 5
		cel 8
		x 4
		y 4
	)
	
	(method (init)
		(if global400 (self number: 27 yourself:))
		(super init: &rest)
	)
)

(instance threeEmeraldCursor of KQCursor
	(properties
		view 941
		loop 5
		cel 9
		x 4
		y 4
	)
	
	(method (init)
		(if global400 (self number: 27 yourself:))
		(super init: &rest)
	)
)

(instance arrowCursor of KQCursor
	(properties
		view 942
		loop 1
		cel 7
	)
	
	(method (init)
		(if global400 (self number: 999 yourself:))
		(super init: &rest)
	)
)
