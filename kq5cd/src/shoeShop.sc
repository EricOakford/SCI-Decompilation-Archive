;;; Sierra Script 1.0 - (do not remove this comment)
(script# 205)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use Chase)
(use RFeature)
(use Avoid)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	shoeShop 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	[local6 9] = [1017 170 70 3 7 25 23 25 32]
	[local15 9] = [1025 70 63 5 7 29 24 29 27]
	[local24 9] = [1025 200 70 5 7 29 24 29 27]
	[local33 9] = [1003 120 79 3 9 24 21 30 29]
	[local42 10] = [0 1017 1 1018 0 1019 1 1020]
	[local52 12] = [1 1024 1 1025 0 1026 1 1027 0 1028]
	[local64 12] = [1 1030 1 1031 0 1032 1 1033 0 1034]
	[local76 8] = [1 1035 1 1036 0 1037]
	[local84 8] = [0 1038 1 1039 1 1040]
)
(instance shoeShop of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(if (Btst 24) (++ local5))
		(curRoom setFeatures: shopRoom)
		(theMusic number: 61 loop: -1 play:)
		(if (!= ((inventory at: 33) owner?) 205)
			(woman init:)
			(if (> (theGame detailLevel:) 0)
				(shoeArm init: setScript: makeShoe)
			else
				(shoeArm init: stopUpd:)
			)
			(shoeMaker init: stopUpd:)
			(dog init: stopUpd:)
			(if (> (theGame detailLevel:) 0)
				(tail setCycle: Fwd init:)
			else
				(tail init: stopUpd:)
			)
			(curRoom setScript: greet)
		else
			(curRoom setScript: enterIn)
		)
	)
	
	(method (doit &tmp temp0)
		(if script (script doit:))
	)
	
	(method (dispose)
		(DisposeScript 985)
		(DisposeScript 972)
		(theMusic fade:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance enterIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 5))
			(1
				(ego illegalBits: 0 setMotion: MoveTo 138 154 self)
			)
			(2
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance greet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 5))
			(1
				(ego illegalBits: 0 setMotion: MoveTo 138 154 self)
			)
			(2
				(ego illegalBits: -32768)
				(if (Btst 23)
					(proc762_1 @local15 1023 self)
				else
					(proc762_0 @local33 @local15 @local52 self)
				)
			)
			(3
				(if (Btst 23)
					(if (== (theGame detailLevel:) 3)
						(wHands cycleSpeed: 3 setCycle: Fwd init:)
					)
					(HandsOn)
					(client setScript: 0)
				else
					(Bset 23)
					(if (== (theGame detailLevel:) 3)
						(woman setCycle: Beg self)
					else
						(self cue:)
					)
				)
			)
			(4
				(RedrawCast)
				(if (== (theGame detailLevel:) 3)
					(wHands cycleSpeed: 3 setCycle: Fwd init:)
				)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance giveShoes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(shoeArm dispose:)
				(shoeMaker
					setScript: 0
					loop: 7
					cel: 4
					cycleSpeed: 2
					setCycle: Beg self
				)
			)
			(1
				(proc762_1 @local6 1029)
				(wHands dispose:)
				(woman setCycle: End self)
			)
			(2
				(ego setMotion: MoveTo 138 148 self)
			)
			(3
				(woman
					view: 210
					setLoop: -1
					posn: 96 140
					setPri: -1
					setCycle: Walk
					setAvoider: (Avoid new:)
					cycleSpeed: 0
					moveSpeed: 0
					setMotion: MoveTo 127 146 self
				)
			)
			(4
				(woman loop: 2)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 200
					loop: 11
					cel: 0
					setCycle: End self
				)
			)
			(5
				(ego setCycle: Beg)
				(woman setLoop: 8 setMotion: Chase shoeMaker 10 self)
				(shoeMaker
					view: 198
					observeControl: 2
					cycleSpeed: 0
					setLoop: -1
					loop: 2
					setCycle: Walk
					moveSpeed: 0
					setMotion: MoveTo 208 127
				)
			)
			(6
				(ego
					normal: 1
					view: 0
					loop: 7
					cel: 0
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(Face ego shoeMaker 5)
				(shoeMaker view: 200 loop: 10 cel: 0 setCycle: CT 3 1)
				(woman setLoop: -1 setMotion: MoveTo 225 133 self)
			)
			(7
				(woman setLoop: 1)
				(= cycles 5)
			)
			(8
				(proc762_0 @local33 @local6 @local64 self)
			)
			(9
				(shoeMaker loop: 10 cycleSpeed: 3 setCycle: End self)
			)
			(10
				(shoeMaker loop: 8 cel: 0 setCycle: End self)
			)
			(11
				(proc762_0 @local33 @local6 @local76)
				(ego
					normal: 1
					view: 0
					setAvoider: (Avoid new:)
					setCycle: KQ5SyncWalk
					setMotion: MoveTo 195 134 self
				)
				((ego head?) show:)
			)
			(12
				(ego setAvoider: 0 normal: 1)
				(Face ego shoeMaker 5)
				(= cycles 5)
			)
			(13
				(shoeMaker loop: 9 cel: 0 setCycle: End self)
			)
			(14
				(shoeMaker
					view: 198
					observeControl: 2
					cycleSpeed: 0
					setLoop: -1
					setCycle: Walk
					loop: 2
				)
				(proc762_0 @local24 @local6 @local84)
				(ego setMotion: MoveTo (- (ego x?) 10) (ego y?) self)
				(tail dispose:)
				(dog loop: 3 cel: 0 setCycle: End self)
			)
			(15)
			(16
				(dog
					setLoop: 4
					setCycle: Walk
					setMotion: MoveTo (dog x?) 237 self
				)
				(ego loop: 7 cel: 2)
				(shoeMaker
					view: 198
					observeControl: 2
					cycleSpeed: 0
					setLoop: -1
					setCycle: Walk
					moveSpeed: 0
					setMotion: MoveTo 180 237 self
				)
				(woman
					cycleSpeed: 0
					moveSpeed: 0
					setAvoider: 0
					setMotion: MoveTo 160 240
				)
			)
			(17)
			(18
				(curRoom obstacles: gPolyList15)
				(dog dispose:)
				(shoeMaker setAvoider: 0 dispose:)
				(woman setAvoider: 0 dispose:)
				(ego setAvoider: 0)
				(HandsOn)
				(cls)
				(client setScript: 0)
			)
		)
	)
)

(instance makeShoe of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not state)
				(== (shoeArm cel?) 2)
				(== (DoAudio 6) -1)
			)
			(theAudio number: 8885 loop: 1 play:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(shoeArm cycleSpeed: 3 loop: 4 setCycle: Fwd)
				(= seconds (Random 3 8))
			)
			(1
				(shoeArm loop: 6 setCycle: Fwd)
				(= seconds (Random 3 6))
				(= state -1)
			)
		)
	)
)

(instance womanScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego woman 5)
				(wHands hide:)
				(woman setCycle: End self)
			)
			(1
				(proc762_0 @local33 @local15 @local42 self)
			)
			(2 (woman setCycle: Beg self))
			(3
				(wHands show:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance shoeArm of Prop
	(properties
		x 197
		y 102
		view 200
		loop 4
		priority 9
		signal $0010
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
				(JOY_UPRIGHT
					(if (cast contains: woman)
						(proc0_29 808)
					else
						(proc0_29 809)
					)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (not local1)
						(++ local1)
						(proc0_29 814)
						(event claimed: 1)
					)
				)
				(JOY_DOWN
					(proc0_29 814)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(33
							(event claimed: 1)
							(ego put: 33 205)
							(SolvePuzzle 4)
							(HandsOff)
							(ego get: 22)
							(curRoom setScript: giveShoes)
						)
						(28 (event claimed: 0))
						(else 
							(proc0_29 816)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance shoeMaker of Actor
	(properties
		x 198
		y 123
		view 200
		cycleSpeed 2
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
			(switch (event message?)
				(JOY_UPRIGHT
					(if (cast contains: woman)
						(proc0_29 808)
					else
						(proc0_29 809)
					)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (not local1)
						(++ local1)
						(proc0_29 814)
						(event claimed: 1)
					)
				)
				(JOY_DOWN
					(proc0_29 814)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(33
							(event claimed: 1)
							(ego put: 33 205)
							(SolvePuzzle 4)
							(HandsOff)
							(ego get: 22)
							(curRoom setScript: giveShoes)
						)
						(28 (event claimed: 0))
						(else 
							(proc762_1 @local15 1021)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance wHands of Prop
	(properties
		x 95
		y 122
		view 210
		loop 4
		priority 11
		signal $4010
		cycleSpeed 2
	)
)

(instance woman of Actor
	(properties
		x 94
		y 126
		view 210
		loop 5
		cel 3
		priority 10
		signal $0010
		cycleSpeed 2
		detailLevel 3
		illegalBits $0800
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
					(proc0_29 810)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (not local2)
						(++ local2)
						(proc0_29 9067)
						(event claimed: 1)
					)
				)
				(JOY_DOWN
					(switch local5
						(0
							(Bset 24)
							(++ local5)
							(curRoom setScript: womanScript)
							(event claimed: 1)
						)
						(1
							(proc0_29 820)
							(event claimed: 1)
						)
					)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(33
							(ego get: 22)
							(ego put: 33 205)
							(SolvePuzzle 4)
							(HandsOff)
							(curRoom setScript: giveShoes)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(proc0_29 817)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance tail of Prop
	(properties
		x 124
		y 124
		view 196
		loop 1
		priority 9
		signal $0010
		cycleSpeed 3
		detailLevel 3
	)
)

(instance dog of Actor
	(properties
		x 130
		y 134
		view 196
		signal $4000
		detailLevel 3
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (> (theGame detailLevel:) 0)
			(cond 
				((>= loop 3) (self cycleSpeed: 0))
				(
					(and
						(< (= temp0 (ego distanceTo: self)) 20)
						(!= local4 0)
					)
					(= local4 0)
					(self loop: 0 setCycle: End)
					(tail hide:)
				)
				((and (> temp0 21) (!= local4 1)) (= local4 1) (self setCycle: Beg))
				((and (not cel) (== local4 1)) (self loop: 2) (tail show:))
			)
		)
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
					(proc0_29 811)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(proc0_29 815)
					(event claimed: 1)
				)
				(JOY_DOWN
					(proc0_29 821)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(28 (event claimed: 0))
						(else 
							(proc0_29 818)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance shopRoom of RFeature
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
					(if (cast contains: woman)
						(proc0_29 812)
					else
						(proc0_29 813)
					)
					(event claimed: 1)
				)
			)
		)
	)
)
