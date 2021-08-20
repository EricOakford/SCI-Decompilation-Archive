;;; Sierra Script 1.0 - (do not remove this comment)
(script# 203)
(include game.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use RandCyc)
(use PolyPath)
(use Follow)
(use RFeature)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	tailorShop 0
)

(local
	local0
	[local1 2]
	local3
	tailorTalkCount
	helpCounterCount
	paidWhat
	saveIllegalBits
	[local8 2]
	local10 = [1015 130 90 4 8 32 26 28 29]
	local19 = [1003 138 100 4 11 25 23 31 31]
	local28 = [0 1190 1 1191 0 1192 1 1193 1 1194 0 1197]
	local42 = [0 1196 1 1198]
	local48 = [0 1195 1 1199]
	local54 = [0 1200 1 1201]
	local60 = [1 1202 1 1203 1 1204]
	local68 = [0 1205 1 1206]
	local74 = [0 1207 0 1208 0 1209 1 1210 0 1211]
	local86 = [0 1212 1 1213]
)

(enum ;paid what
	paidNeedle
	paidGold
	paidHeart
)

(instance tailorShop of Region
	
	(method (init)
		(super init:)
		(curRoom setFeatures: shopRoom)
		(theMusic number: 61 loop: -1 play:)
		(= saveIllegalBits (ego illegalBits?))
		(ego
			view: 0
			loop: 3
			setStep: 3 2
			posn: 152 166
			ignoreActors: 0
			illegalBits: cWHITE
		)
		(if (== (theGame detailLevel:) 3)
			(employee
				moveSpeed: 0
				ignoreActors: 0
				init:
				setScript: helpCounter
			)
			(empHand init:)
		)
		(if (== (theGame detailLevel:) 3)
			(theMouth init: posn: (curtain x?) (curtain y?) 1000)
		)
		(tailor
			setCycle: Walk
			ignoreActors: 0
			observeControl: cBLUE
			init:
		)
		(curRoom setScript: greet)
		(if (Btst 58)
			(++ tailorTalkCount)
		)
		(if (== ((inventory at: iCloak) owner?) 203)
			(cloak init:)
		)
		(if (== (theGame detailLevel:) 3)
			(curtain init:)
		else
			(curtain cel: 2 init:)
		)
	)
	
	(method (doit)
		(if script
			(script doit:)
		)
	)
	
	(method (dispose)
		(ego illegalBits: saveIllegalBits)
		(DisposeScript FOLLOW)
		(DisposeScript AVOIDER)
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

(instance greet of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 5)
			)
			(1
				(ego setMotion: MoveTo 152 152 self)
			)
			(2
				(proc762_1 @local10 1189 self)
				(tailor setMotion: Follow ego 40)
			)
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance soldCloak of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego has: iCloak)
					(HandsOff)
					(SpeakAudio 833)
					(client setScript: 0)
					(HandsOn)
				else
					(switch paidWhat
						(paidNeedle
							(ego put: iNeedle 203)
							(SolvePuzzle 4)
						)
						(paidGold
							(ego put: iGold 203)
						)
						(paidHeart
							(ego put: iHeart 203)
						)
					)
					(HandsOff)
					(if (& (ego onControl: 0) cRED)
						(ego setMotion: MoveTo 197 128 self)
					else
						(= cycles 1)
					)
				)
			)
			(1
				(ego
					setAvoider: (Avoider new:)
					setMotion: MoveTo (- (tailor x?) 8) (+ (tailor y?) 8) self
				)
			)
			(2
				((ego head?) hide:)
				(ego
					normal: 0
					view: 24
					loop: 0
					cel: 0
					setAvoider: 0
					ignoreControl: cLCYAN
					setCycle: EndLoop self
				)
			)
			(3
				(tailor
					setMotion: 0
					view: 164
					loop: 2
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(ego setCycle: BegLoop self)
				(tailor setCycle: BegLoop self)
			)
			(5)
			(6
				(ego view: 0 normal: TRUE loop: 3 setCycle: KQ5SyncWalk)
				((ego head?) show:)
				(= cycles 2)
			)
			(7
				(RedrawCast)
				(switch paidWhat
					(paidNeedle
						(ego get: iCloak)
						(proc762_0 @local10 @local19 @local28)
					)
					(paidGold
						(ego get: iCloak)
						(proc762_0 @local19 @local10 @local42)
					)
					(paidHeart
						(ego get: iCloak)
						(proc762_0 @local19 @local10 @local48)
					)
				)
				(tailor
					view: 162
					setCycle: Walk
					ignoreControl: cBLUE
					setAvoider: (Avoider new:)
					setMotion: MoveTo 216 127 self
				)
				(ego
					setAvoider: (Avoider new:)
					setMotion: MoveTo 205 133 self
				)
			)
			(8)
			(9
				(tailor
					view: 164
					loop: 3
					cel: 0
					setPri: 7
					setAvoider: 0
					setCycle: EndLoop self
				)
				(ego loop: 7 cel: 3 setAvoider: 0)
				(cloak dispose:)
			)
			(10
				(tailor loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(11
				(proc762_0 @local19 @local10 @local54)
				(tailor
					setScript: 0
					view: 162
					cycleSpeed: 0
					setLoop: -1
					setCycle: Walk
					setPri: -1
					illegalBits: cLCYAN
					setAvoider: (Avoider new:)
					setMotion: MoveTo 202 116 self
				)
			)
			(12
				(tailor setPri: 7 setMotion: MoveTo 152 116 self)
			)
			(13
				(tailor loop: 2 setAvoider: 0 stopUpd:)
				(ego view: 0)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance helpWithCloak of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(tailor
					ignoreControl: cBLUE
					setAvoider: (Avoider new:)
					setMotion: MoveTo 216 127 self
				)
				(ego setMotion: PolyPath 205 133 self)
			)
			(1)
			(2
				(ego loop: 11 cel: 4 ignoreActors: 0)
				((ego head?) cel: 1)
				(cloak hide:)
				(tailor
					view: 164
					loop: 3
					setPri: 7
					cel: 0
					setAvoider: 0
					setCycle: EndLoop self
				)
			)
			(3
				(tailor loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(4
				(ego view: 10)
				(tailor view: 162 loop: 2)
				((ego head?) cel: -1)
				(proc762_0 @local19 @local10 @local60)
				(ego view: 0)
				(tailor view: 164 loop: 4 setCycle: BegLoop self)
			)
			(5
				(tailor loop: 3 cel: 7 setCycle: BegLoop self)
			)
			(6
				(cloak show:)
				(tailor
					view: 162
					cycleSpeed: 0
					setLoop: -1
					setCycle: Walk
					setPri: -1
					setMotion: MoveTo 183 129 self
				)
			)
			(7
				(tailor observeControl: 2 setMotion: Follow ego 40)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance helpCounter of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(employee
					setLoop: 4
					setCycle: Walk
					setMotion: MoveTo 125 112 self
				)
			)
			(1
				(employee setLoop: 0 cel: 0 setCycle: EndLoop)
				(empHand cel: (Random 0 3))
				(= seconds 5)
			)
			(2
				(employee setCycle: BegLoop self)
			)
			(3
				(employee
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo 210 112 self
				)
			)
			(4
				(employee setLoop: 1 setCycle: EndLoop)
				(= seconds 5)
			)
			(5
				(curtain setCycle: EndLoop)
				(if (not (curRoom script?))
					(theAudio number: 8859 loop: 1 play:)
				)
				(= seconds 10)
			)
			(6
				(curtain setCycle: BegLoop self)
				(if (not (curRoom script?))
					(theAudio number: 8859 loop: 1 play:)
				)
			)
			(7
				(if (not (curRoom script?))
					(theMouth z: 34 setCycle: RandCycle)
					(switch helpCounterCount
						(0 (SpeakAudio 1219 self))
						(1 (SpeakAudio 1220 self))
						(2 (SpeakAudio 1221 self))
						(3 (SpeakAudio 1222 self))
						(4 (SpeakAudio 1223 self))
						(5 (SpeakAudio 1224 self))
						(6 (SpeakAudio 1225 self))
						(7 (SpeakAudio 1226 self))
						(8 (SpeakAudio 1227 self))
						(9 (SpeakAudio 1228 self))
						(10 (SpeakAudio 1229 self))
					)
				else
					(= cycles 1)
				)
				(if (< helpCounterCount 10)
					(++ helpCounterCount)
				else
					(= helpCounterCount 0)
				)
			)
			(8
				(theMouth z: 1000 setCycle: 0)
				(self init:)
			)
		)
	)
)

(instance tailorTalk of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Face ego tailor 5)
				(Face tailor ego 5)
				(= cycles 2)
			)
			(1
				(proc762_0 @local19 @local10 @local68)
				(tailor
					setScript: 0
					view: 162
					cycleSpeed: 0
					setLoop: -1
					setCycle: Walk
					illegalBits: cLCYAN
					setAvoider: (Avoider new:)
					setMotion: MoveTo 202 116 self
				)
			)
			(2
				(tailor setPri: 7 setMotion: MoveTo 152 116 self)
				(if (> (ego y?) 116)
					(ego setMotion: PolyPath 142 126 self)
				)
			)
			(3
				(if (and (not (ego mover?)) (< (ego y?) 126))
					(ego setMotion: PolyPath 142 126 self)
				)
			)
			(4
				(Face tailor ego)
				(ego loop: 7 cel: 3)
				(RedrawCast)
				(proc762_0 @local10 @local19 @local74)
				(tailor
					view: 162
					cycleSpeed: 0
					setLoop: -1
					setCycle: Walk
					illegalBits: cLCYAN
					setMotion: MoveTo 195 116 self
				)
			)
			(5
				(tailor setPri: -1 setMotion: MoveTo 195 132 self)
			)
			(6
				(tailor
					setCycle: Walk
					ignoreActors: 0
					setAvoider: 0
					observeControl: 2
					setMotion: Follow ego 40
					init:
				)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance tailor of Actor
	(properties
		x 149
		y 142
		view 162
		loop 2
		signal ignrAct
		illegalBits cLCYAN
	)
	
	(method (doit &tmp tailorLoop)
		(super doit:)
		(if
			(and
				(not (curRoom script?))
				(== ((inventory at: iCloak) owner?) 203)
			)
			(if (> (ego distanceTo: tailor) 40)
				(if (not mover)
					(self
						moveSpeed: (ego moveSpeed?)
						setMotion: Follow ego 40
					)
				)
			else
				(Face tailor ego 5)
				(= tailorLoop (tailor loop?))
				(self cel: tailorLoop loop: 7 setMotion: 0)
			)
		)
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
					(if (ego has: iCloak)
						(SpeakAudio 824)
					else
						(SpeakAudio 825)
					)
					(event claimed: TRUE)
				)
				(verbDo
					(cond 
						((== ((inventory at: iNeedle) owner?) 203)
							(SpeakAudio 830)
							(event claimed: TRUE)
						)
						((not local0)
							(++ local0)
							(SpeakAudio 9076)
							(event claimed: TRUE)
						)
					)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(iNeedle
							(= paidWhat paidNeedle)
							(curRoom setScript: soldCloak)
							(event claimed: TRUE)
						)
						(iCoin
							(if (not (ego has: iCloak))
								(Face ego tailor 5)
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
								(proc762_0 @local19 @local10 @local86)
							else
								(Face ego tailor 5)
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
								(proc762_1 @local10 1214)
							)
							(event claimed: TRUE)
						)
						(iGold
							(= paidWhat paidGold)
							(curRoom setScript: soldCloak)
							(event claimed: TRUE)
						)
						(iHeart
							(= paidWhat paidHeart)
							(curRoom setScript: soldCloak)
							(event claimed: TRUE)
						)
						(iWand
							(event claimed: FALSE)
						)
						(else 
							(if (ego has: iCloak)
								(SpeakAudio 833)
							else
								(SpeakAudio 834)
							)
							(event claimed: TRUE)
						)
					)
				)
				(verbTalk
					(switch tailorTalkCount
						(0
							(if (ego has: iCloak)
								(SpeakAudio 833)
							else
								(Bset fTalkedToTailor)
								(++ tailorTalkCount)
								(HandsOff)
								(curRoom setScript: tailorTalk)
							)
							(event claimed: TRUE)
						)
						(1
							(SpeakAudio 835)
							(event claimed: TRUE)
						)
					)
				)
			)
		)
	)
)

(instance cloak of Prop
	(properties
		x 233
		y 123
		view 176
		loop 9
		signal stopUpdOn
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
					(SpeakAudio 826)
					(event claimed: TRUE)
				)
				(verbDo
					(if (not local3)
						(++ local3)
						(HandsOff)
						(proc762_1 @local10 1215)
						(curRoom setScript: helpWithCloak)
					else
						(proc762_1 @local10 1216)
					)
					(event claimed: TRUE)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(iNeedle
							(= paidWhat paidNeedle)
							(curRoom setScript: soldCloak)
							(event claimed: TRUE)
						)
						(iCoin
							(Face ego tailor 5)
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
							(proc762_0 @local19 @local10 @local86)
							(event claimed: TRUE)
						)
						(iGold
							(= paidWhat paidGold)
							(curRoom setScript: soldCloak)
							(event claimed: TRUE)
						)
						(iHeart
							(= paidWhat paidHeart)
							(curRoom setScript: soldCloak)
							(event claimed: TRUE)
						)
						(iWand
							(event claimed: FALSE)
						)
						(iCloak
							(proc762_1 @local10 1217)
							(event claimed: TRUE)
						)
						(else 
							(proc762_1 @local10 1218)
							(event claimed: TRUE)
						)
					)
				)
			)
		)
	)
)

(instance curtain of Prop
	(properties
		x 204
		y 108
		view 176
		loop 5
		cycleSpeed 2
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
					(SpeakAudio 827)
					(event claimed: TRUE)
				)
				(verbDo
					(SpeakAudio 831)
					(event claimed: TRUE)
				)
				(verbTalk
					(SpeakAudio 836)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance theMouth of Prop
	(properties
		view 176
		loop 10
		cycleSpeed 4
	)
)

(instance empHand of Prop
	(properties
		view 176
		loop 6
	)
	
	(method (doit)
		(super doit:)
		(self y: (employee y?) z: 18)
		(switch (employee loop?)
			(2
				(self x: (employee x?) loop: 6 show:)
			)
			(3
				(self x: (+ (employee x?) 7) loop: 7 show:)
			)
			(4
				(self x: (- (employee x?) 6) loop: 8 show:)
			)
			(else  (self hide:))
		)
	)
)

(instance employee of Actor
	(properties
		x 218
		y 112
		view 176
		loop 1
		detailLevel 3
		illegalBits cLCYAN
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
					(SpeakAudio 828)
					(event claimed: TRUE)
				)
				(verbDo
					(SpeakAudio 832)
					(event claimed: TRUE)
				)
				(verbTalk
					(SpeakAudio 832)
					(event claimed: TRUE)
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
					(SpeakAudio 829)
					(event claimed: TRUE)
				)
			)
		)
	)
)
