;;; Sierra Script 1.0 - (do not remove this comment)
(script# 204)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use Door)
(use Sync)
(use PolyPath)
(use RFeature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	toyShop 0
)

(local
	[local0 2]
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	local9
	[local10 9] = [1019 140 48 3 11 26 20 24 27]
	[local19 9] = [1003 140 70 4 11 25 23 31 31]
	[local28 9] = [1003 200 70 4 11 25 23 31 31]
	[local37 8] = [0 5007 1 5008 0 5009]
	[local45 14] = [0 5010 1 5011 0 5012 1 5013 0 5014 1 5015]
	[local59 16] = [0 5016 1 5017 0 5018 1 5019 0 5020 1 5021 0 5022]
	[local75 6] = [0 5023 1 5024]
	[local81 10] = [0 5025 1 5026 0 5027 1 5028]
	[local91 10] = [0 5029 1 5030 0 5031 1 5032]
	[local101 6] = [0 5036 1 5037]
)
(instance toyShop of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(curRoom setFeatures: fishBowl shopRoom)
		(theMusic number: 61 loop: -1 play:)
		(fish cycleSpeed: 3 setCycle: Fwd init:)
		(toyMaker init: stopUpd:)
		(door init:)
		(curRoom setScript: greet)
		(lArm init:)
		(rArm init:)
		(girl init:)
		(if (== (theGame detailLevel:) 3)
			(girl setScript: girlPlay)
		)
		(if (== ((inventory at: 29) owner?) 204)
			(sled init: stopUpd:)
		)
		(= local8 100)
	)
	
	(method (doit &tmp [temp0 2])
		(if (< global363 7) (++ local5) else (= global363 0))
		(if (< local6 1005) (++ local6))
		(cond 
			(script (script doit:))
			(
				(and
					(>= local5 local8)
					(not (curRoom script?))
					(== (theGame detailLevel:) 3)
				)
				(= local8 1000)
				(theIconBar disable:)
				(HandsOff)
				(self setScript: sonScript)
			)
			(
				(and
					(>= local6 200)
					(not local7)
					(not (curRoom script?))
					(== (girl loop?) 2)
				)
				(theIconBar disable:)
				(HandsOff)
				(= local5 0)
				(++ local7)
				(self setScript: girlTalk)
			)
		)
	)
	
	(method (dispose)
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

(instance sonScript of Script
	(properties)
	
	(method (doit &tmp newEvent)
		(super doit:)
		(if
			(or
				(== ((= newEvent (Event new:)) type?) 1)
				(== (newEvent type?) 4)
			)
			(= seconds 0)
			(= cycles 1)
		)
		(newEvent dispose:)
	)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0 (door setCycle: End self))
			(1
				(toyMaker setScript: 0 setCycle: 0)
				(toyHead loop: 14 x: 159 show: init:)
				(boyHead init:)
				(= temp0 8)
				(switch global363
					(0
						(boyHead setCycle: MouthSync 5038)
						(SpeakAudio 5038 self)
					)
					(1
						(boyHead setCycle: MouthSync 5039)
						(SpeakAudio 5039 self)
					)
					(2
						(boyHead setCycle: MouthSync 5040)
						(SpeakAudio 5040 self)
					)
					(3
						(boyHead setCycle: MouthSync 5041)
						(SpeakAudio 5041 self)
					)
					(4
						(boyHead setCycle: MouthSync 5042)
						(SpeakAudio 5042 self)
					)
					(5
						(boyHead setCycle: MouthSync 5043)
						(SpeakAudio 5043 self)
					)
					(else 
						(boyHead setCycle: MouthSync 5044)
						(SpeakAudio 5044 self)
					)
				)
			)
			(2
				(boyHead setCycle: 0 hide:)
				(theMouth x: 163 init:)
				(= temp0 8)
				(switch global363
					(0
						(theMouth setCycle: MouthSync 5049)
						(SpeakAudio 5049 self)
					)
					(1
						(theMouth setCycle: MouthSync 5050)
						(SpeakAudio 5050 self)
					)
					(2
						(theMouth setCycle: MouthSync 5051)
						(SpeakAudio 5051 self)
					)
					(3
						(theMouth setCycle: MouthSync 5052)
						(SpeakAudio 5052 self)
					)
					(4
						(theMouth setCycle: MouthSync 5053)
						(SpeakAudio 5053 self)
					)
					(5
						(theMouth setCycle: MouthSync 5054)
						(SpeakAudio 5054 self)
					)
					(else 
						(theMouth setCycle: MouthSync 5055)
						(SpeakAudio 5055 self)
					)
				)
			)
			(3
				(theMouth setCycle: 0 setCel: 0)
				(boyHead show:)
				(= temp0 3)
				(switch global363
					(0
						(boyHead setCycle: MouthSync 5045)
						(SpeakAudio 5045 self)
					)
					(1
						(boyHead setCycle: MouthSync 5046)
						(SpeakAudio 5046 self)
					)
					(4
						(boyHead setCycle: MouthSync 5047)
						(SpeakAudio 5047 self)
					)
					(5
						(boyHead setCycle: MouthSync 5048)
						(SpeakAudio 5048 self)
					)
					(else  (= seconds 1))
				)
			)
			(4
				(boyHead setCycle: 0 dispose:)
				(cls)
				(door setCycle: Beg self)
			)
			(5
				(theIconBar enable:)
				(theMouth dispose:)
				(toyHead dispose:)
				(door stopUpd:)
				(boyHead stopUpd:)
				(= local5 0)
				(++ global363)
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
			(0
				(HandsOff)
				(toyMaker setScript: buildToy)
				(= cycles 5)
			)
			(1
				(ego setMotion: MoveTo 215 140 self)
			)
			(2
				(proc762_1 @local10 5006 self)
			)
			(3
				(HandsOn)
				(cls)
				(client setScript: 0)
			)
		)
	)
)

(instance lookSled of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 225 139 self)
			)
			(1
				(Face ego toyMaker 5)
				(= cycles 3)
			)
			(2
				(proc762_0 @local10 @local19 @local37 self)
			)
			(3
				(= local5 0)
				(= local6 0)
				(theMouth dispose:)
				(HandsOn)
				(cls)
				(toyMaker setScript: buildToy)
				(client setScript: 0)
			)
		)
	)
)

(instance buildToy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(lArm show: loop: 5 stopUpd:)
				(rArm show: loop: 6 x: 160 setCycle: Fwd)
				(= seconds (Random 3 8))
			)
			(1
				(rArm setCycle: End)
				(= seconds (Random 3 8))
				(= state -1)
			)
			(2
				(toyMaker loop: 0 cycleSpeed: 3 setCycle: Fwd)
			)
		)
	)
)

(instance girlPlay of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(girl loop: 0 cycleSpeed: 2 setCycle: End)
				(= seconds (Random 3 8))
			)
			(1 (girl setCycle: Beg self))
			(2
				(girl loop: 1 setCycle: End self)
			)
			(3
				(girl loop: 3 cycleSpeed: 4 setCycle: Fwd)
				(= seconds (Random 5 10))
			)
			(4
				(girl loop: 1 cycleSpeed: 1 setCycle: Beg self)
			)
			(5
				(girl loop: 2 cycleSpeed: 4 setCycle: Fwd)
				(= seconds (Random 5 10))
				(= state -1)
			)
		)
	)
)

(instance girlTalk of Script
	(properties)
	
	(method (doit &tmp newEvent)
		(super doit:)
		(if
			(or
				(== ((= newEvent (Event new:)) type?) 1)
				(== (newEvent type?) 4)
			)
			(= seconds 0)
			(= cycles 1)
		)
		(newEvent dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(girl loop: 4 cel: 0 setScript: 0 setCycle: End self)
			)
			(1
				(girlHead init: setCycle: MouthSync 5001)
				(SpeakAudio 5001 self)
			)
			(2
				(toyHead loop: 15 x: 158 init:)
				(= cycles 1)
			)
			(3
				(girlHead setCycle: 0 hide:)
				(theMouth x: 159 init: setCycle: MouthSync 5002)
				(SpeakAudio 5002 self)
			)
			(4
				(theMouth setCycle: 0)
				(girlHead show: setCycle: MouthSync 5003)
				(SpeakAudio 5003 self)
			)
			(5
				(girlHead setCycle: 0 hide:)
				(theMouth setCycle: MouthSync 5004)
				(SpeakAudio 5004 self)
			)
			(6
				(theMouth setCycle: 0)
				(girlHead show: setCycle: MouthSync 5005)
				(SpeakAudio 5005 self)
			)
			(7
				(girlHead setCycle: 0 dispose:)
				(theMouth dispose:)
				(toyHead hide:)
				(cls)
				(HandsOn)
				(theIconBar enable:)
				(toyMaker setScript: buildToy)
				(girl setScript: girlPlay)
				(client setScript: 0)
			)
		)
	)
)

(instance manTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(toyMaker setScript: 0)
				(rArm setCycle: 0 loop: 7)
				(= cycles 1)
			)
			(2
				(proc762_0 @local19 @local10 @local45 self)
			)
			(3
				(= local5 0)
				(= local6 0)
				(HandsOn)
				(theMouth dispose:)
				(cls)
				(toyMaker setScript: buildToy)
				(client setScript: 0)
			)
		)
	)
)

(instance getSled of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch local4
					(0 (ego put: 12 204))
					(1 (ego put: 3 204))
					(2 (ego put: 9 204))
					(3 (ego put: 11 204))
				)
				(HandsOff)
				(toyMaker setScript: 0)
				(ego setMotion: PolyPath 172 134 self)
			)
			(1
				(if (== local4 0)
					((ego head?) hide:)
					(ego
						normal: 0
						view: 178
						loop: 12
						cel: 0
						cycleSpeed: 3
						setCycle: CT 1 1 self
					)
				else
					(= cycles 1)
				)
			)
			(2
				(lArm hide:)
				(rArm hide:)
				(toyMaker
					view: 178
					loop: 6
					cel: 0
					cycleSpeed: 3
					setCycle: CT 2 1 self
				)
			)
			(3
				(if (== local4 0)
					(ego setCycle: End self)
					(toyMaker setCycle: End self)
				else
					(toyMaker setCycle: Beg self)
					(= cycles 1)
				)
			)
			(4)
			(5
				(ego
					normal: 1
					view: 0
					loop: 3
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(= cycles 3)
			)
			(6
				(switch local4
					(0
						(proc762_0 @local10 @local19 @local59)
					)
					(1
						(proc762_0 @local19 @local10 @local75)
					)
					(2
						(proc762_0 @local19 @local10 @local81)
					)
					(3
						(proc762_0 @local19 @local10 @local91)
					)
				)
				(ego setMotion: MoveTo 232 131 self)
				(if (== local4 0)
					(toyMaker loop: 11 setCycle: End self)
				else
					(= cycles 1)
				)
			)
			(7)
			(8
				((ego head?) hide:)
				(ego
					normal: 0
					view: 178
					loop: 7
					cel: 0
					setCycle: CT 2 1 self
				)
			)
			(9
				(ego setCycle: End self)
				(sled hide: dispose:)
				(ego get: 29)
			)
			(10
				(proc762_1 @local28 5033 self)
			)
			(11
				(switch local4
					(0
						(proc762_1 @local10 5035 self)
					)
					(else 
						(proc762_1 @local10 5034 self)
					)
				)
			)
			(12
				(= local5 0)
				(= local6 0)
				(theMouth dispose:)
				(toyMaker view: 182 loop: 1 setScript: buildToy)
				(ego
					normal: 1
					view: 0
					loop: 7
					cel: 0
					setCycle: KQ5SyncWalk
					setMotion: MoveTo 229 139 self
				)
				((ego head?) show:)
			)
			(13
				(toyMaker stopUpd:)
				(lArm stopUpd:)
				(rArm stopUpd:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance rArm of Prop
	(properties
		x 175
		y 87
		view 182
		priority 5
		signal $0010
		cycleSpeed 3
		detailLevel 3
	)
	
	(method (handleEvent event)
		(toyMaker handleEvent: event)
	)
)

(instance theMouth of Prop
	(properties
		y 85
		view 178
		loop 9
		priority 6
		signal $0010
		cycleSpeed 2
	)
	
	(method (handleEvent event)
		(toyMaker handleEvent: event)
	)
)

(instance lArm of Prop
	(properties
		x 143
		y 85
		view 182
		loop 3
		priority 5
		signal $0010
		detailLevel 3
	)
	
	(method (handleEvent event)
		(toyMaker handleEvent: event)
	)
)

(instance toyHead of Prop
	(properties
		y 85
		view 178
		loop 14
		priority 5
		signal $0011
		cycleSpeed 2
	)
	
	(method (handleEvent event)
		(toyMaker handleEvent: event)
	)
)

(instance toyMaker of Prop
	(properties
		x 156
		y 97
		view 182
		loop 1
		priority 4
		signal $0010
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
					(SpeakAudio 838)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 9071)
					(event claimed: 1)
				)
				(JOY_DOWN
					(if (not (Btst 108))
						(Bset 108)
						(self setScript: 0)
						(Face ego toyMaker 5)
						(HandsOff)
						(toyShop setScript: manTalk)
					else
						(SpeakAudio 847)
					)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(12
							(if (not (ego has: 29))
								(SolvePuzzle 4)
								(= local4 0)
								(toyShop setScript: getSled)
							else
								(SpeakAudio 849)
							)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(3
							(if (not (ego has: 29))
								(= local4 1)
								(toyShop setScript: getSled)
							else
								(SpeakAudio 849)
							)
							(event claimed: 1)
						)
						(9
							(if (not (ego has: 29))
								(= local4 2)
								(toyShop setScript: getSled)
							else
								(SpeakAudio 849)
							)
							(event claimed: 1)
						)
						(4
							(if (not (ego has: 29))
								(Face ego toyMaker 5)
								(RedrawCast)
								((ego head?)
									cel:
									(switch (ego loop?)
										(0 1)
										(1 5)
										(2 3)
										(3 7)
									)
									forceUpd:
								)
								(RedrawCast)
								(proc762_0 @local19 @local10 @local101)
							else
								(SpeakAudio 849)
							)
							(event claimed: 1)
						)
						(11
							(if (not (ego has: 29))
								(= local4 3)
								(toyShop setScript: getSled)
							else
								(SpeakAudio 849)
							)
							(event claimed: 1)
						)
						(else 
							(SpeakAudio 850)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance sled of Prop
	(properties
		x 243
		y 125
		view 178
		loop 1
		priority 9
		signal $0011
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
					(SpeakAudio 839)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (not local2)
						(++ local2)
						(toyShop setScript: lookSled)
					else
						(SpeakAudio 844)
					)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(12
							(SolvePuzzle 4)
							(= local4 0)
							(toyShop setScript: getSled)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(3
							(= local4 1)
							(toyShop setScript: getSled)
							(event claimed: 1)
						)
						(9
							(= local4 2)
							(toyShop setScript: getSled)
							(event claimed: 1)
						)
						(4
							(proc762_0 @local19 @local10 @local101)
							(event claimed: 1)
						)
						(else 
							(SpeakAudio 850)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance boyHead of Prop
	(properties
		x 184
		y 77
		view 178
		loop 13
		priority 2
		signal $0011
		cycleSpeed 2
	)
)

(instance door of Door
	(properties
		x 191
		y 98
		view 178
		loop 2
		priority 1
		signal $0011
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
					(SpeakAudio 840)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 845)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance fish of Prop
	(properties
		x 213
		y 100
		view 178
		priority 9
		signal $0010
		detailLevel 3
	)
	
	(method (handleEvent)
	)
)

(instance fishBowl of RFeature
	(properties
		nsTop 90
		nsLeft 205
		nsBottom 103
		nsRight 225
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
					(SpeakAudio 841)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance girlHead of Prop
	(properties
		x 99
		y 119
		view 190
		loop 5
		priority 5
		signal $0011
		cycleSpeed 2
		detailLevel 3
	)
)

(instance girl of Actor
	(properties
		x 99
		y 132
		view 190
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
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 842)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 846)
					(event claimed: 1)
				)
				(JOY_DOWN
					(SpeakAudio 848)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 851)
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
					(SpeakAudio 843)
					(event claimed: 1)
				)
			)
		)
	)
)
