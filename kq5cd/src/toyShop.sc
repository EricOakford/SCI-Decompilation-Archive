;;; Sierra Script 1.0 - (do not remove this comment)
(script# 204)
(include game.sh)
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
	paidWhat
	local5
	local6
	local7
	local8
	local9
	local10 = [1019 140 48 3 11 26 20 24 27]
	local19 = [1003 140 70 4 11 25 23 31 31]
	local28 = [1003 200 70 4 11 25 23 31 31]
	local37 = [0 5007 1 5008 0 5009]
	local45 = [0 5010 1 5011 0 5012 1 5013 0 5014 1 5015]
	local59 = [0 5016 1 5017 0 5018 1 5019 0 5020 1 5021 0 5022]
	local75 = [0 5023 1 5024]
	local81 = [0 5025 1 5026 0 5027 1 5028]
	local91 = [0 5029 1 5030 0 5031 1 5032]
	local101 = [0 5036 1 5037]
)

(enum ;paid what
	paidPuppet
	paidNeedle
	paidHeart
	paidGold
)

(instance toyShop of Region
	
	(method (init)
		(super init:)
		(curRoom setFeatures: fishBowl shopRoom)
		(theMusic number: 61 loop: -1 play:)
		(fish cycleSpeed: 3 setCycle: Forward init:)
		(toyMaker init: stopUpd:)
		(door init:)
		(curRoom setScript: greet)
		(lArm init:)
		(rArm init:)
		(girl init:)
		(if (== (theGame detailLevel:) 3)
			(girl setScript: girlPlay)
		)
		(if (== ((inventory at: iSled) owner?) 204)
			(sled init: stopUpd:)
		)
		(= local8 100)
	)
	
	(method (doit &tmp [temp0 2])
		(if (< global363 7)
			(++ local5)
		else
			(= global363 0)
		)
		(if (< local6 1005)
			(++ local6)
		)
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
	
	(method (doit &tmp event)
		(super doit:)
		(if
			(or
				(== ((= event (Event new:)) type?) 1)
				(== (event type?) 4)
			)
			(= seconds 0)
			(= cycles 1)
		)
		(event dispose:)
	)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0 (door setCycle: EndLoop self))
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
				(door setCycle: BegLoop self)
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

	(method (changeState newState)
		(switch (= state newState)
			(0
				(lArm show: loop: 5 stopUpd:)
				(rArm show: loop: 6 x: 160 setCycle: Forward)
				(= seconds (Random 3 8))
			)
			(1
				(rArm setCycle: EndLoop)
				(= seconds (Random 3 8))
				(= state -1)
			)
			(2
				(toyMaker loop: 0 cycleSpeed: 3 setCycle: Forward)
			)
		)
	)
)

(instance girlPlay of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(girl loop: 0 cycleSpeed: 2 setCycle: EndLoop)
				(= seconds (Random 3 8))
			)
			(1 (girl setCycle: BegLoop self))
			(2
				(girl loop: 1 setCycle: EndLoop self)
			)
			(3
				(girl loop: 3 cycleSpeed: 4 setCycle: Forward)
				(= seconds (Random 5 10))
			)
			(4
				(girl loop: 1 cycleSpeed: 1 setCycle: BegLoop self)
			)
			(5
				(girl loop: 2 cycleSpeed: 4 setCycle: Forward)
				(= seconds (Random 5 10))
				(= state -1)
			)
		)
	)
)

(instance girlTalk of Script

	(method (doit &tmp event)
		(super doit:)
		(if
			(or
				(== ((= event (Event new:)) type?) mouseDown)
				(== (event type?) keyDown)
			)
			(= seconds 0)
			(= cycles 1)
		)
		(event dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(girl loop: 4 cel: 0 setScript: 0 setCycle: EndLoop self)
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
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch paidWhat
					(paidPuppet
						(ego put: iPuppet 204)
					)
					(paidNeedle
						(ego put: iNeedle 204)
					)
					(paidHeart
						(ego put: iHeart 204)
					)
					(paidGold
						(ego put: iGold 204)
					)
				)
				(HandsOff)
				(toyMaker setScript: 0)
				(ego setMotion: PolyPath 172 134 self)
			)
			(1
				(if (== paidWhat paidPuppet)
					((ego head?) hide:)
					(ego
						normal: FALSE
						view: 178
						loop: 12
						cel: 0
						cycleSpeed: 3
						setCycle: CycleTo 1 1 self
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
					setCycle: CycleTo 2 1 self
				)
			)
			(3
				(if (== paidWhat paidPuppet)
					(ego setCycle: EndLoop self)
					(toyMaker setCycle: EndLoop self)
				else
					(toyMaker setCycle: BegLoop self)
					(= cycles 1)
				)
			)
			(4)
			(5
				(ego
					normal: TRUE
					view: 0
					loop: 3
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(= cycles 3)
			)
			(6
				(switch paidWhat
					(paidPuppet
						(proc762_0 @local10 @local19 @local59)
					)
					(paidNeedle
						(proc762_0 @local19 @local10 @local75)
					)
					(paidHeart
						(proc762_0 @local19 @local10 @local81)
					)
					(paidGold
						(proc762_0 @local19 @local10 @local91)
					)
				)
				(ego setMotion: MoveTo 232 131 self)
				(if (== paidWhat paidPuppet)
					(toyMaker loop: 11 setCycle: EndLoop self)
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
					setCycle: CycleTo 2 1 self
				)
			)
			(9
				(ego setCycle: EndLoop self)
				(sled hide: dispose:)
				(ego get: iSled)
			)
			(10
				(proc762_1 @local28 5033 self)
			)
			(11
				(switch paidWhat
					(paidPuppet
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
		signal fixPriOn
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
		signal fixPriOn
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
		signal fixPriOn
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
		signal (| fixPriOn stopUpdOn)
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
		signal fixPriOn
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 838)
					(event claimed: TRUE)
				)
				(verbDo
					(SpeakAudio 9071)
					(event claimed: TRUE)
				)
				(verbTalk
					(if (not (Btst fTalkedToToyMaker))
						(Bset fTalkedToToyMaker)
						(self setScript: 0)
						(Face ego toyMaker 5)
						(HandsOff)
						(toyShop setScript: manTalk)
					else
						(SpeakAudio 847)
					)
					(event claimed: TRUE)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(iPuppet
							(if (not (ego has: iSled))
								(SolvePuzzle 4)
								(= paidWhat paidPuppet)
								(toyShop setScript: getSled)
							else
								(SpeakAudio 849)
							)
							(event claimed: TRUE)
						)
						(iWand
							(event claimed: FALSE)
						)
						(iNeedle
							(if (not (ego has: iSled))
								(= paidWhat paidNeedle)
								(toyShop setScript: getSled)
							else
								(SpeakAudio 849)
							)
							(event claimed: TRUE)
						)
						(iHeart
							(if (not (ego has: iSled))
								(= paidWhat paidHeart)
								(toyShop setScript: getSled)
							else
								(SpeakAudio 849)
							)
							(event claimed: TRUE)
						)
						(iCoin
							(if (not (ego has: iSled))
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
							(event claimed: TRUE)
						)
						(iGold
							(if (not (ego has: iSled))
								(= paidWhat paidGold)
								(toyShop setScript: getSled)
							else
								(SpeakAudio 849)
							)
							(event claimed: TRUE)
						)
						(else 
							(SpeakAudio 850)
							(event claimed: TRUE)
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
		signal (| fixPriOn stopUpdOn)
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 839)
					(event claimed: TRUE)
				)
				(verbDo
					(if (not local2)
						(++ local2)
						(toyShop setScript: lookSled)
					else
						(SpeakAudio 844)
					)
					(event claimed: TRUE)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(iPuppet
							(SolvePuzzle 4)
							(= paidWhat paidPuppet)
							(toyShop setScript: getSled)
							(event claimed: TRUE)
						)
						(iWand
							(event claimed: FALSE)
						)
						(iNeedle
							(= paidWhat paidNeedle)
							(toyShop setScript: getSled)
							(event claimed: TRUE)
						)
						(iHeart
							(= paidWhat paidHeart)
							(toyShop setScript: getSled)
							(event claimed: TRUE)
						)
						(iCoin
							(proc762_0 @local19 @local10 @local101)
							(event claimed: TRUE)
						)
						(else 
							(SpeakAudio 850)
							(event claimed: TRUE)
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
		signal (| fixPriOn stopUpdOn)
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
		signal (| fixPriOn stopUpdOn)
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 840)
					(event claimed: TRUE)
				)
				(verbDo
					(SpeakAudio 845)
					(event claimed: TRUE)
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
		signal fixPriOn
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
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 841)
					(event claimed: TRUE)
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
		signal (| fixPriOn stopUpdOn)
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
		signal fixPriOn
		detailLevel 3
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 842)
					(event claimed: TRUE)
				)
				(verbDo
					(SpeakAudio 846)
					(event claimed: TRUE)
				)
				(verbTalk
					(SpeakAudio 848)
					(event claimed: TRUE)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(iWand
							(event claimed: FALSE)
						)
						(else 
							(SpeakAudio 851)
							(event claimed: TRUE)
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
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 843)
					(event claimed: TRUE)
				)
			)
		)
	)
)
