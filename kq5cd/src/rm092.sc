;;; Sierra Script 1.0 - (do not remove this comment)
(script# 92)
(include game.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use Sync)
(use RandCyc)
(use Follow)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm092 0
)

(local
	[local0 8] = [90 60 160 20 93 29 141 11]
	local8
)
(instance rm092 of KQ5Room
	(properties
		picture 92
		east 35
		south 34
	)
	
	(method (init)
		(super init:)
		(switch prevRoomNum
			(39
				(ego setPri: -1 posn: 135 157)
				(theMusic number: 118 loop: -1 play:)
				(wolf2 cel: 4 init:)
				(wolf1 setLoop: 5 cel: 4)
				(Bset 57)
				(self setScript: cartoon2)
			)
			(else 
				(ego loop: 3 cel: 3 posn: 135 157)
				(HandsOff)
				(self setScript: cartoon1)
			)
		)
		(iceQueen init:)
		(wolf1 init:)
		(wolf2 init:)
		(cage setPri: 14 init: stopUpd:)
		(cedric setScript: (ScriptID 202 3) init:)
		(ego
			normal: 0
			view: 856
			loop: 13
			cel: 0
			moveSpeed: 0
			xStep: 3
			yStep: 2
			init:
		)
		((ego head?) hide:)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((= temp0 (self edgeToRoom: (ego edgeHit?))) (curRoom newRoom: temp0))
		)
		(wolf1
			cycleSpeed: (theGame egoMoveSpeed?)
			moveSpeed: (theGame egoMoveSpeed?)
		)
		(wolf2
			cycleSpeed: (theGame egoMoveSpeed?)
			moveSpeed: (theGame egoMoveSpeed?)
		)
	)
	
	(method (dispose)
		(DisposeScript 941)
		(DisposeScript 971)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance cartoon1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(theMusic2 stop:)
				(theMusic number: 92 loop: -1 play:)
				(= cycles 30)
				(wolf1 cycleSpeed: 2 setCycle: EndLoop init:)
				(wolf2 cycleSpeed: 2 setCycle: EndLoop init:)
			)
			(2
				(iceQueen cycleSpeed: 3 setCycle: EndLoop self)
			)
			(3
				(= local8 1)
				(iceQueen loop: 5 cycleSpeed: 8 setCycle: RandCycle show:)
				(smallHead setCycle: MouthSync 935 init:)
				(SpeakAudio 935 self)
			)
			(4
				(= local8 0)
				(smallHead hide:)
				(iceQueen setCycle: 0)
				(cls)
				(ego cycleSpeed: 3 setCycle: EndLoop self)
			)
			(5
				(smallHead show:)
				(iceQueen loop: 6 cel: 0 setCycle: EndLoop)
				(ego loop: 12 cel: 2)
				(smallHead setCycle: MouthSync 936)
				(SpeakAudio 936 self)
			)
			(6
				(= local8 1)
				(cls)
				(iceQueen loop: 7 setCel: 1 posn: 248 80)
				(cage hide:)
				(cedric hide:)
				(wolf1 hide:)
				(wolf2 hide:)
				(ego loop: 12 cel: 1 posn: 10 171)
				(smallHead
					loop: 10
					x: (iceQueen x?)
					y: (- (iceQueen y?) 35)
				)
				(curRoom drawPic: 95)
				(qArm cycleSpeed: 8 setCycle: RandCycle init:)
				(smallHead setCycle: MouthSync 937)
				(SpeakAudio 937 self)
			)
			(7
				(= local8 0)
				(cls)
				(curRoom drawPic: 92)
				(theIconBar disable: 0 1 2 3)
				(wolf1 show:)
				(wolf2 show:)
				(cedric show:)
				(smallHead setCycle: 0 hide:)
				(qArm dispose:)
				(iceQueen
					loop: 6
					posn: 129 62
					cel: (- (NumCels iceQueen) 1)
				)
				(cage view: 846 loop: 4 x: 264 y: 109 show:)
				(ego loop: 13 posn: 135 157)
				(ego cel: (- (NumCels ego) 1))
				(= cycles 5)
			)
			(8
				(theIconBar enable:)
				(theGame setCursor: crownCursor)
				(User canInput: 1)
				(theMusic number: 93 loop: -1 play:)
				(if (HaveMouse) (= cycles 1) else (= seconds 12))
			)
			(9
				(wolf1 setCycle: BegLoop self)
				(wolf2 setCycle: BegLoop self)
			)
			(10)
			(11
				(wolf1
					setLoop: 1
					setCycle: Walk
					cycleSpeed: 0
					setMotion: MoveTo 95 (wolf1 y?) self
				)
				(wolf2
					setLoop: 0
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 179 (wolf2 y?) self
				)
			)
			(12)
			(13
				(theIconBar disable:)
				(HandsOff)
				(theMusic number: 94 loop: 1 play:)
				(ego loop: 10 cel: 0 setCycle: EndLoop self)
			)
			(14
				(wolf1 view: 917 loop: 1 cel: 0 setCycle: EndLoop self)
				(wolf2 view: 917 loop: 0 cel: 0 setCycle: EndLoop)
			)
			(15 (= cycles 1))
			(16
				(cls)
				(= deathMessage 757)
				(EgoDead 543 0 Forward)
			)
			(17
				(HandsOff)
				(smallHead loop: 4 x: 138 y: 25 cel: 0 show:)
				(ego loop: 2 setCycle: Forward cycleSpeed: 4)
				(note init: setScript: noteScript)
				(theMusic number: 817 loop: 1 play: self)
				(wolf1 setMotion: 0 setCycle: 0)
				(wolf2 setMotion: 0 setCycle: 0)
				(= seconds 3)
			)
			(18
				(smallHead setCycle: MouthSync 938)
				(SpeakAudio 938 0 1)
				(wolf1 cycleSpeed: 2 setLoop: 5 setCycle: EndLoop)
				(wolf2
					cycleSpeed: 2
					setPri: 1
					setLoop: 4
					ignoreActors: 1
					setCycle: EndLoop
				)
			)
			(19
				(= local8 1)
				(smallHead setCycle: 0)
				(= seconds 4)
				(note setScript: 0 dispose:)
				(ego loop: 13 cel: 3 setCycle: 0)
			)
			(20
				((ScriptID 763) doit:)
				(= cycles 1)
			)
			(21
				(theMusic number: 118 loop: -1 play:)
				(cls)
				(curRoom drawPic: 93)
				(cage hide:)
				(cedric hide:)
				(wolf1 hide:)
				(wolf2 hide:)
				(ego hide:)
				(iceQueen hide:)
				(smallHead hide:)
				(theMouth cycleSpeed: 4 init:)
				(theEyes setScript: moveScript init:)
				(glint setScript: glintScript init:)
				(= seconds 2)
			)
			(22
				(theMouth setCycle: MouthSync 939)
				(SpeakAudio 939 self)
			)
			(23
				(theMouth setCycle: MouthSync 940)
				(SpeakAudio 940 self)
			)
			(24
				(= local8 0)
				(theMouth setCycle: 0 dispose:)
				(RedrawCast)
				(cls)
				(curRoom drawPic: 92)
				(theEyes dispose:)
				(smallHead loop: 4 x: 138 y: 25 cel: 0 show:)
				(glint dispose:)
				(cage show:)
				(cedric show:)
				(wolf1 show:)
				(wolf2 show:)
				(ego show:)
				(iceQueen loop: 5 cycleSpeed: 8 setCycle: RandCycle show:)
				(= seconds 2)
			)
			(25
				(smallHead setCycle: MouthSync 941)
				(SpeakAudio 941 self)
			)
			(26
				(= local8 1)
				(ego setCycle: BegLoop)
				(smallHead setCycle: MouthSync 942)
				(SpeakAudio 942 self)
			)
			(27
				(smallHead setCycle: MouthSync 943)
				(SpeakAudio 943 self)
			)
			(28
				(smallHead setCycle: MouthSync 944)
				(SpeakAudio 944 self)
			)
			(29
				(= local8 0)
				(smallHead setCycle: 0 dispose:)
				(iceQueen cel: 0 setCycle: 0)
				(wolf1 setCycle: BegLoop self)
			)
			(30
				(cls)
				(wolf1
					setLoop: 1
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo (ego x?) (+ (ego y?) 5) self
				)
			)
			(31
				(wolf2 setPri: 1)
				(wolf1
					moveSpeed: (ego moveSpeed?)
					setMotion: MoveTo 340 175 self
				)
				(ego
					normal: 1
					view: 10
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					setLoop: -1
					setPri: -1
					ignoreActors: 1
					setMotion: MoveTo 340 175
				)
				((ego head?) show:)
			)
			(32
				(theMusic fade:)
				(curRoom newRoom: 39)
			)
		)
	)
)

(instance glintScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= temp0 (Random 0 3))
				(client
					x: [local0 (* temp0 2)]
					y: [local0 (+ (* temp0 2) 1)]
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(= seconds (Random 3 9))
				(= state -1)
			)
		)
	)
)

(instance moveScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: EndLoop self))
			(1 (= cycles 10))
			(2 (client setCycle: BegLoop self))
			(3
				(= state -1)
				(= seconds (Random 3 10))
			)
		)
	)
)

(instance cartoon2 of Script
	(properties)
	
	(method (doit &tmp newEvent temp1)
		(super doit:)
		(if local8
			(= temp1 ((= newEvent (Event new:)) type?))
			(if (OneOf temp1 1 4) (= seconds 0) (= cycles 1))
			(newEvent dispose:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable:)
				(User canControl: 0 canInput: 0)
				(= local8 1)
				(= cycles 5)
			)
			(1
				(= local8 0)
				((ego head?) hide:)
				(ego normal: 0 cycleSpeed: 3 setCycle: EndLoop self)
			)
			(2
				(iceQueen cycleSpeed: 3 setCycle: EndLoop self)
			)
			(3
				(= local8 1)
				(iceQueen loop: 5 cycleSpeed: 8 setCycle: RandCycle)
				(smallHead setCycle: MouthSync 945 init:)
				(SpeakAudio 945 self)
			)
			(4
				(iceQueen setCycle: 0)
				(smallHead hide:)
				(SpeakAudio 946 self)
				(ego loop: 8 cycleSpeed: 6 setScript: (moveScript new:))
			)
			(5
				(ego loop: 13 cel: 3 setCycle: 0 setScript: 0)
				(iceQueen loop: 5 cycleSpeed: 8 setCycle: RandCycle)
				(smallHead show: setCycle: MouthSync 947)
				(SpeakAudio 947 self)
			)
			(6
				(= local8 0)
				(cls)
				(ego cycleSpeed: 2 setCycle: BegLoop self)
			)
			(7
				(= local8 1)
				(ego
					normal: 1
					view: 10
					cycleSpeed: 0
					setLoop: 3
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(smallHead setCycle: MouthSync 948)
				(SpeakAudio 948 self)
			)
			(8
				(= local8 0)
				(smallHead hide:)
				(iceQueen cycleSpeed: 5 setCycle: EndLoop self)
			)
			(9 (= cycles 10))
			(10
				(iceQueen loop: 8 cel: 0 setCycle: EndLoop self)
			)
			(11 (= cycles 10))
			(12
				(cls)
				(cage loop: 3 cel: 0 cycleSpeed: 8 setCycle: EndLoop self)
			)
			(13 (= cycles 10))
			(14
				(= local8 1)
				(iceQueen loop: 5 cycleSpeed: 8 setCycle: RandCycle)
				(cage dispose:)
				(smallHead setCycle: MouthSync 949 show:)
				(SpeakAudio 949 self)
			)
			(15
				(= local8 0)
				(wolf1 cycleSpeed: 3 setCycle: BegLoop self)
			)
			(16
				(smallHead setCycle: 0 hide:)
				(iceQueen setCycle: 0)
				(cls)
				(wolf1
					setLoop: 1
					cycleSpeed: 0
					setCycle: Walk
					cycleSpeed: 0
					setMotion: MoveTo (ego x?) (+ (ego y?) 5) self
				)
			)
			(17
				(ego setLoop: -1 setMotion: Follow wolf1 40)
				(wolf1
					moveSpeed: (ego moveSpeed?)
					setMotion: MoveTo 340 169 self
				)
			)
			(18
				(cedric view: 138 loop: 5 cel: 0 setScript: cedricLeave)
			)
		)
	)
)

(instance cedricLeave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cedric cycleSpeed: 2 setCycle: CycleTo 4 1 self)
			)
			(1
				(cedric setLoop: 6 setCycle: EndLoop self)
			)
			(2
				(theMusic fade:)
				(theIconBar enable:)
				(curRoom newRoom: 39)
			)
		)
	)
)

(instance noteScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(note
					x: (- (ego x?) 30)
					y: (- (ego y?) 30)
					setCel: (Random 0 3)
					setMotion: MoveTo (- (ego x?) 50) (- (ego y?) 50) self
				)
				(= state -1)
			)
		)
	)
)

(instance cedric of Prop
	(properties
		x 264
		y 108
		view 138
		loop 5
	)
)

(instance cage of Prop
	(properties
		x 264
		y 109
		view 846
		loop 3
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn ego event))
			)
			(return)
		else
			(switch (event message?)
				(verbUse
					(if (MousedOn ego event)
						(switch (inventory indexOf: (theIconBar curInvIcon?))
							(10
								(SolvePuzzle 4)
								(cartoon1 changeState: 17)
								(event claimed: 1)
							)
						)
					)
				)
			)
		)
	)
)

(instance iceQueen of Prop
	(properties
		x 129
		y 62
		view 848
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
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(10
							(SolvePuzzle 4)
							(cartoon1 changeState: 17)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else  (SpeakAudio 756))
					)
				)
			)
		)
	)
)

(instance wolf1 of Actor
	(properties
		x 24
		y 159
		view 856
		loop 5
	)
)

(instance wolf2 of Actor
	(properties
		x 304
		y 159
		view 856
		loop 4
	)
)

(instance theEyes of Prop
	(properties
		x 164
		y 58
		view 852
	)
)

(instance theMouth of Prop
	(properties
		x 139
		y 96
		view 1030
		loop 2
	)
)

(instance glint of Prop
	(properties
		x 90
		y 60
		view 852
		loop 2
	)
)

(instance qArm of Prop
	(properties
		x 247
		y 44
		view 848
		loop 1
		priority 14
		signal $0010
	)
)

(instance note of Actor
	(properties
		view 856
		loop 3
		signal $0800
	)
)

(instance smallHead of Prop
	(properties
		x 138
		y 25
		view 848
		loop 4
		priority 14
		signal $0010
		cycleSpeed 4
	)
)
