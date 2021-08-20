;;; Sierra Script 1.0 - (do not remove this comment)
(script# 206)
(include game.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use RFeature)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	bakeShop 0
)

(local
	local0
	[local1 2]
	local3
	bakerTalkCount
	womanTalkCount
	boyTalkCount
	paidWhat
	pts1 = [
		0 0
		319 0
		319 189
		176 189
		176 170
		226 170
		207 141
		261 141
		244 119
		144 119
		144 112
		107 112
		95 126
		127 126
		102 170
		146 170
		146 189
		0 189
		]
	local44 = [1003 140 74 3 9 24 21 30 29]
	local53 = [1003 150 60 3 9 24 21 30 29]
	local62 = [1018 170 44 4 9 31 22 27 26]
	local71 = [1020 200 67 4 9 26 28 27 33]
	local80 = [1021 178 74 4 11 27 29 22 33]
	local89 = [0 1179 0 1180]
	local95 = [1 1154 0 1155 1 1156 0 1157 1 1158]
	local107 = [0 1159 1 1160 0 1161 1 1162 0 1163 1 1164]
	local121 = [0 1171 1 1188]
	local127 = [0 1043 1 1044 0 1045]
	local135 = [0 1046 1 1047]
)

(enum ;what's exchanged for pie
	paySilver
	payNeedle
	payHeart
	payGoldCoin
)

(procedure (localproc_245a)
	(baker setScript: 0 loop: 3 cel: 0)
)

(instance bakeShop of KQ5Room
	(properties
		picture 924
	)
	
	(method (init)
		(super init:)
		(LoadMany VIEW 226 224 1120)
		(HandsOff)
		(theMusic vol: 0 stop:)
		(self
			setFeatures: kitchenDoor counterTop shop
			addObstacle: poly1
			setScript: walkInScript
		)
		(if (Btst 25)
			(++ bakerTalkCount)
		)
		(if (ego has: iPie)
			(++ bakerTalkCount)
		)
		(if (and (== ((inventory at: iPie) owner?) 206) (Btst fBeenInBakery))
			(pie init:)
		)
		(if (not (Btst fBeenInBakery))
			(woman init:)
			(boy init:)
			(arm init:)
			(pie init:)
			(baker cel: 7)
		)
		(smoke init:)
		(cedric init:)
		(baker init:)
		(brother init:)
		(if (> (theGame detailLevel:) 1)
			(cat setScript: catScript init:)
			(tail init: cycleSpeed: 2 setCycle: Forward)
		else
			(cat signal: 1 init:)
			(tail signal: 1 init:)
		)
		(ego view: 0 setStep: 3 2 posn: 168 200 setPri: -1 init:)
		(poly1 points: @pts1 size: 18)
	)
	
	(method (doit &tmp temp0)
		(if (and (!= bakerTalkCount 3) (== ((inventory at: iPie) owner?) 1))
			(= bakerTalkCount 3)
		)
		(cond 
			(script
				(script doit:)
			)
			((& (ego onControl: 0) cYELLOW)
				(HandsOff)
				(curRoom setScript: walkOutScript)
			)
		)
	)
	
	(method (dispose)
		(DisposeScript 941)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom n)
		(Bset fBeenInBakery)
		(theMusic fade:)
		(super newRoom: n)
	)
)

(instance doWinners of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 3)
			)
			(1
				(SpeakAudio 1048 self)
			)
			(2
				(cls)
				(bakerMouth init: setCycle: RandCycle)
				(SpeakAudio 1181 self)
			)
			(3
				(bakerMouth z: 1000)
				(cls)
				(arm cycleSpeed: 7 setCycle: EndLoop self)
			)
			(4
				(woman
					view: 550
					setLoop: 4
					cycleSpeed: 2
					setCycle: CycleTo 1 1 self
				)
				(RedrawCast)
			)
			(5
				(SpeakAudio 1049 self)
			)
			(6
				(cls)
				(arm cel: 0)
				(= cycles 1)
			)
			(7
				(arm dispose:)
				(boy loop: 5 cycleSpeed: 2 setCycle: EndLoop self)
				(woman setCycle: BegLoop)
			)
			(8
				(= seconds 1)
			)
			(9
				(SpeakAudio 1050 self)
			)
			(10
				(bakerMouth z: 0)
				(SpeakAudio 1182 self)
			)
			(11
				(bakerMouth dispose:)
				(cls)
				(pie hide:)
				(baker loop: 3 cel: 6)
				(= cycles 15)
			)
			(12
				(woman view: 1120 loop: 1 cel: 1)
				(= cycles 15)
			)
			(13
				(baker view: 226 loop: 3 cel: 7)
				(SpeakAudio 1051 self)
			)
			(14
				(cls)
				(woman setCycle: EndLoop self)
			)
			(15
				(SpeakAudio 1052 self)
			)
			(16
				(cls)
				(woman view: 550 loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(17
				(theIconBar enable:)
				(theIconBar disable: ICON_WALK)
				(User canInput: TRUE)
				(theGame setCursor: crownCursor)
				(= cycles 1)
			)
			(18
				(boy
					setLoop: 0
					cycleSpeed: (if (== (theGame detailLevel:) 3) 4 else 1)
					moveSpeed: (if (== (theGame detailLevel:) 3) 4 else 1)
					setCycle: Forward
					setMotion: PolyPath 168 200
				)
				(= seconds 1)
			)
			(19
				(woman
					view: 1120
					setLoop: 0
					cycleSpeed: (if (== (theGame detailLevel:) 3) 4 else 1)
					moveSpeed: (if (== (theGame detailLevel:) 3) 4 else 1)
					setCycle: Forward
					setMotion: PolyPath 224 128 self
				)
			)
			(20
				(woman setMotion: PolyPath 168 200 self)
			)
			(21
				(boy dispose:)
				(woman dispose:)
				(curRoom setScript: greet)
			)
		)
	)
)

(instance walkInScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 15)
			)
			(1
				(theMusic number: 61 loop: -1 playBed:)
				(ego setMotion: MoveTo 168 148 self)
			)
			(2
				(if (not (Btst fBeenInBakery))
					(curRoom setScript: doWinners)
				else
					(if (> (theGame detailLevel:) 1)
						(brother setScript: brotherScript)
					else
						(brother stopUpd:)
					)
					(curRoom setScript: greetAgain)
				)
			)
		)
	)
)

(instance walkOutScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 168 200 self)
			)
			(1
				(curRoom newRoom: 6)
			)
		)
	)
)

(instance greet of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 10)
			)
			(1
				(brother cycleSpeed: 2 setCycle: BegLoop self)
			)
			(2
				(brother loop: 12 setCycle: CycleTo 2 1 self)
			)
			(3
				(baker view: 226 loop: 3 cel: 0)
				(theMouth init: setCycle: RandCycle)
				(SpeakAudio 1178 self)
			)
			(4
				(cls)
				(brother setCycle: EndLoop)
				(baker setCycle: EndLoop self)
				(theMouth dispose:)
			)
			(5
				(pie show:)
				(= cycles 20)
			)
			(6
				(= local0 1)
				(proc762_0 @local62 @local62 @local89 self)
			)
			(7
				(if (> (theGame detailLevel:) 1)
					(brother setScript: brotherScript)
				else
					(brother stopUpd:)
				)
				(if local0
					(= local0 0)
					(= cycles 1)
				else
					(proc762_1 @local62 1180 self)
				)
			)
			(8
				(theIconBar enable: ICON_WALK)
				(baker setScript: bakerScript)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance greetAgain of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(baker view: 225 loop: 0 setCycle: EndLoop self)
			)
			(1
				(cond 
					((== ((inventory at: iPie) owner?) 206)
						(proc762_1 @local62 1175 self)
					)
					((== ((inventory at: iPie) owner?) ego)
						(proc762_1 @local62 1176 self)
					)
					(else
						(proc762_1 @local62 1177 self)
					)
				)
			)
			(2
				(if (== ((inventory at: iPie) owner?) 206)
					(greet start: 7)
					(curRoom setScript: greet)
				else
					(baker setScript: bakerScript)
					(HandsOn)
					(client setScript: 0)
				)
			)
		)
	)
)

(instance doPie of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 198 120 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: FALSE
					view: 226
					loop: 4
					cel: 0
					setCycle: CycleTo 3 1
				)
				(= seconds 2)
			)
			(2
				(localproc_245a)
				(= cycles 1)
			)
			(3
				(proc762_0 @local53 @local62 @local95 self)
			)
			(4
				(ego setCycle: EndLoop)
				(= cycles 1)
			)
			(5
				(ego
					view: 0
					setLoop: 0
					setMotion: 0
					setCycle: KQ5SyncWalk
					normal: TRUE
				)
				((ego head?) show:)
				(ego setLoop: -1)
				(= cycles 1)
			)
			(6
				(baker setScript: bakerScript)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance talkBakerScript of Script
	
	(method (changeState newState &tmp egoAngle)
		(switch (= state newState)
			(0
				(= egoAngle
					(GetAngle (ego x?) (ego y?) (baker x?) (baker y?))
				)
				(ego
					cel:
						(cond 
							((< egoAngle 45) 3)
							((< egoAngle 135) 0)
							((< egoAngle 225) 2)
							((< egoAngle 315) 1)
							(else 3)
						)
				)
				(localproc_245a)
				(proc762_0 @local53 @local62 @local107 self)
			)
			(1
				(baker setScript: bakerScript)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance getPie of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 198 120 self)
			)
			(1
				(Face ego baker)
				(= cycles 2)
			)
			(2
				(proc762_1 @local53 1165 self)
				(localproc_245a)
			)
			(3
				(proc762_1 @local62 1166 self)
			)
			(4
				(switch paidWhat
					(paySilver
						(proc762_1 @local53 1167 self)
					)
					(payGoldCoin
						(proc762_1 @local53 1168 self)
					)
					(payNeedle
						(proc762_1 @local53 1169 self)
					)
					(payHeart
						(proc762_1 @local53 1170 self)
					)
				)
			)
			(5
				((ego head?) hide:)
				(ego
					normal: 0
					view: 226
					loop: 4
					cel: 0
					setCycle: CycleTo 3 1 self
				)
				(ego get: iPie)
				(= bakerTalkCount 2)
				(Bset fGotPie)
				(SolvePuzzle 2)
				(switch paidWhat
					(paySilver
						(ego put: iCoin 206)
					)
					(payGoldCoin
						(ego put: iGold 206)
					)
					(payNeedle
						(ego put: iNeedle 206)
					)
					(payHeart
						(ego put: iHeart 206)
					)
				)
			)
			(6 (= cycles 1))
			(7
				(switch paidWhat
					(paySilver
						(proc762_1 @local62 1171 self)
					)
					(else 
						(proc762_1 @local62 1172 self)
					)
				)
			)
			(8
				(ego setCycle: EndLoop self)
				(switch paidWhat
					(paySilver
						(proc762_1 @local53 1173 self)
					)
					(else 
						(proc762_1 @local53 1174 self)
					)
				)
				(pie dispose:)
			)
			(9 0)
			(10
				(ego
					view: 0
					setLoop: 0
					setMotion: 0
					setCycle: KQ5SyncWalk
					normal: TRUE
				)
				((ego head?) show:)
				(ego setLoop: -1)
				(baker setScript: bakerScript)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance bakerScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(baker view: 225 loop: 3 cel: 0 setCycle: EndLoop)
				(= cycles (Random 80 140))
			)
			(1
				(baker setCycle: CycleTo 1 -1)
				(= cycles 6)
			)
			(2
				(baker setCycle: EndLoop self)
				(= cycles (Random 50 100))
			)
			(3
				(baker setCycle: BegLoop)
				(= cycles (Random 60 120))
			)
			(4
				(baker loop: 4 cel: 0 setCycle: CycleTo 6 1)
				(= cycles 12)
			)
			(5
				(if (Random 0 1)
					(baker setCycle: CycleTo 3 -1 self)
				else
					(baker setCycle: EndLoop self)
				)
			)
			(6
				(baker setCycle: EndLoop)
				(= cycles (Random 60 120))
			)
			(7
				(baker loop: 1 setCycle: Forward)
				(= cycles (Random 40 60))
			)
			(8
				(if (curRoom script?)
					(= cycles 1)
				else
					(baker loop: 0 cel: 7 setCycle: BegLoop self)
				)
			)
			(9 (baker setCycle: EndLoop self))
			(10
				(baker view: 225 loop: 3 cel: 0 setCycle: EndLoop)
				(= cycles (Random 80 140))
			)
			(11
				(baker setCycle: CycleTo 1 -1)
				(= cycles (Random 50 100))
			)
			(12
				(baker setCycle: EndLoop)
				(= cycles (Random 80 140))
			)
			(13
				(baker setCycle: BegLoop)
				(= cycles (Random 80 140))
			)
			(14
				(if (curRoom script?)
					(self init:)
				else
					(baker loop: 2 setCycle: CycleTo 3 1)
					(= cycles 50)
				)
			)
			(15 (baker setCycle: EndLoop self))
			(16 (self init:))
		)
	)
)

(instance catScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< (Random 1 1000) 100)
					(= state 2)
				)
				(= cycles (Random 5 12))
			)
			(1
				(cat loop: 6 setCycle: EndLoop self)
			)
			(2
				(cat loop: 6 setCycle: BegLoop self)
				(= state -1)
			)
			(3
				(tail hide:)
				(cat loop: (+ 2 register) cel: 0 setCycle: CycleTo 4 1 self)
			)
			(4
				(cat
					cycleSpeed: 1
					setCycle: Walk
					xStep: 1
					setMotion:
						MoveTo
						(if register (- (cat x?) 20) else (+ (cat x?) 20))
						(cat y?)
						self
				)
			)
			(5
				(cat
					loop: (+ 4 register)
					cel: 0
					cycleSpeed: 2
					setCycle: CycleTo 5 1 self
				)
			)
			(6
				(if (Random 0 1) (cat cel: 1))
				(cat setCycle: EndLoop self)
			)
			(7
				(tail show: setCycle: Forward)
				(= cycles (Random 200 400))
			)
			(8
				(= register (not register))
				(self init:)
			)
		)
	)
)

(instance brotherScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(brother
					setLoop: (brother loop?)
					setCel: (brother cel?)
					setMotion: MoveTo 221 80 self
				)
			)
			(1
				(brother
					setCel: -1
					cel: 0
					setLoop: -1
					loop: 7
					setCycle: EndLoop self
				)
			)
			(2
				(brother loop: 9 cel: 0 cycleSpeed: 3 setCycle: EndLoop)
				(= seconds (Random 1 3))
				(if (< (Random 1 100) 50) (-- state))
			)
			(3
				(brother loop: 7 cel: 6 cycleSpeed: 2 setCycle: BegLoop self)
			)
			(4
				(brother loop: 8 cel: 2 cycleSpeed: 3 setCycle: BegLoop self)
			)
			(5
				(brother loop: 11 cycleSpeed: 2 setCycle: Forward)
				(= cycles (Random 80 200))
			)
			(6
				(brother loop: 8 cel: 0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(7
				(if (Random 0 1)
					(brother
						setLoop: 14
						setCycle: Forward
						setMotion:
							MoveTo
							(if (Random 0 1) (- (brother x?) 140) else 167)
							(brother y?)
							self
					)
				else
					(self init:)
				)
			)
			(8 (= cycles (Random 10 40)))
			(9
				(brother
					setLoop: 13
					setMotion: MoveTo 221 (brother y?) self
				)
			)
			(10
				(brother setLoop: -1)
				(if (Random 0 1) (self start: 4) else (self start: 0))
				(self init:)
			)
		)
	)
)

(instance boyTalk of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(cls)
				(proc762_0 @local44 @local80 @local127 self)
			)
			(1
				(User canInput: TRUE)
				(client setScript: 0)
			)
		)
	)
)

(instance womanTalk of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cls)
				(proc762_0 @local44 @local71 @local135 self)
			)
			(1
				(User canInput: TRUE)
				(client setScript: 0)
			)
		)
	)
)

(instance baker of Actor
	(properties
		x 206
		y 88
		view 226
		loop 3
		priority 7
		signal fixPriOn
		cycleSpeed 2
		illegalBits $0000
	)
	
	(method (handleEvent event &tmp temp0)
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
					(SpeakAudio 1)
					(event claimed: TRUE)
				)
				(verbDo
					(SpeakAudio 9056)
					(event claimed: TRUE)
				)
				(verbTalk
					(if (not (curRoom script?))
						(switch bakerTalkCount
							(0
								(++ bakerTalkCount)
								(HandsOff)
								(curRoom setScript: talkBakerScript)
								(event claimed: TRUE)
							)
							(1
								(Bset fGotPie)
								(SpeakAudio 9057)
								(event claimed: TRUE)
							)
							(2
								(proc762_0 @local62 @local53 @local121)
								(event claimed: TRUE)
							)
							(3
								(Bset fGotPie)
								(SpeakAudio 13)
								(event claimed: TRUE)
							)
						)
					)
				)
				(verbUse
					(cond 
						((== (inventory indexOf: (theIconBar curInvIcon?)) iWand)
							(event claimed: FALSE)
						)
						((not (curRoom script?))
							(cond 
								((== ((inventory at: iPie) owner?) 206)
									(switch (inventory indexOf: (theIconBar curInvIcon?))
										(iCoin
											(event claimed: TRUE)
											(= paidWhat paySilver)
											(curRoom setScript: getPie)
										)
										(iGold
											(event claimed: TRUE)
											(= paidWhat payGoldCoin)
											(curRoom setScript: getPie)
										)
										(iNeedle
											(event claimed: TRUE)
											(= paidWhat payNeedle)
											(curRoom setScript: getPie)
										)
										(iHeart
											(event claimed: TRUE)
											(= paidWhat payHeart)
											(curRoom setScript: getPie)
										)
										(else 
											(SpeakAudio 19)
											(event claimed: TRUE)
										)
									)
								)
								((== ((inventory at: iPie) owner?) ego)
									(SpeakAudio 20)
									(event claimed: TRUE)
								)
								(else
									(SpeakAudio 13)
									(event claimed: TRUE)
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance theMouth of Prop
	(properties
		x 220
		y 61
		view 225
		loop 5
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 2
	)
)

(instance bakerMouth of Prop
	(properties
		x 207
		y 70
		view 225
		loop 6
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 2
	)
)

(instance brother of Actor
	(properties
		x 221
		y 80
		view 224
		loop 8
		cel 2
		signal ignrAct
		cycleSpeed 3
		illegalBits $0000
		xStep 2
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
				(& (OnControl CMAP (event x?) (event y?)) cGREEN)
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 2)
					(event claimed: TRUE)
				)
				(verbDo
					(SpeakAudio 9)
					(event claimed: TRUE)
				)
				(verbTalk
					(SpeakAudio 14)
					(event claimed: TRUE)
				)
				(verbUse
					(if (== (inventory indexOf: (theIconBar curInvIcon?)) iWand)
						(event claimed: FALSE)
					else
						(SpeakAudio 21)
						(event claimed: TRUE)
					)
				)
			)
		)
	)
)

(instance tail of Prop
	(properties
		x 126
		y 107
		view 224
		loop 15
		signal ignrAct
		cycleSpeed 2
	)
	
	(method (doit)
		(super doit:)
		(switch (cat loop?)
			(4 (self x: (- (cat x?) 7)))
			(5 (self x: (+ (cat x?) 6)))
			(6 (self x: (cat x?)))
		)
	)
)

(instance cat of Actor
	(properties
		x 126
		y 107
		view 224
		loop 6
		cycleSpeed 2
		detailLevel 3
		xStep 1
		moveSpeed 1
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
					(SpeakAudio 3)
					(event claimed: TRUE)
				)
				(verbTalk
					(SpeakAudio 15)
					(event claimed: TRUE)
				)
				(verbDo
					(SpeakAudio 10)
					(event claimed: TRUE)
				)
				(verbUse
					(if (== (inventory indexOf: (theIconBar curInvIcon?)) iWand)
						(event claimed: FALSE)
					else
						(SpeakAudio 22)
						(event claimed: TRUE)
					)
				)
			)
		)
	)
)

(instance pie of Prop
	(properties
		x 206
		y 93
		view 224
		loop 10
		cel 1
		priority 8
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
					(SpeakAudio 4)
					(event claimed: TRUE)
				)
				(verbDo
					(if (not (curRoom script?))
						(cond 
							((and (not local3) (== ((inventory at: iPie) owner?) 206))
								(++ local3)
								(HandsOff)
								(curRoom setScript: doPie)
								(event claimed: TRUE)
							)
							((ego has: iPie)
								(SpeakAudio 11)
								(event claimed: TRUE)
							)
							(else
								(SpeakAudio 12)
								(event claimed: TRUE)
							)
						)
					)
				)
			)
		)
	)
)

(instance kitchenDoor of RFeature
	(properties
		nsTop 53
		nsLeft 122
		nsBottom 104
		nsRight 155
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
					(SpeakAudio 5)
					(event claimed: TRUE)
				)
				(verbDo
					(SpeakAudio 16)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance shop of RFeature
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
					(SpeakAudio 6)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance counterTop of RFeature

	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (& (OnControl CMAP (event x?) (event y?)) cBLUE))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 4)
					(event claimed: TRUE)
				)
				(verbDo
					(if (not (curRoom script?))
						(cond 
							((and (not local3) (== ((inventory at: iPie) owner?) 206))
								(++ local3)
								(HandsOff)
								(curRoom setScript: doPie)
								(event claimed: TRUE)
							)
							((ego has: iPie)
								(SpeakAudio 11)
								(event claimed: TRUE)
							)
							(else
								(SpeakAudio 12)
								(event claimed: TRUE)
							)
						)
					)
				)
			)
		)
	)
)

(instance smoke of Prop
	(properties
		x 146
		y 5
		view 221
		loop 7
		cycleSpeed 5
	)
)

(instance boy of Actor
	(properties
		x 215
		y 120
		view 550
		loop 6
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
					(SpeakAudio 7)
					(event claimed: TRUE)
				)
				(verbTalk
					(event claimed: TRUE)
					(if (or boyTalkCount (> (boy y?) (+ (ego y?) 25)))
						(SpeakAudio 17)
					else
						(++ boyTalkCount)
						(User canInput: FALSE)
						(boy setScript: boyTalk)
					)
				)
			)
		)
	)
)

(instance woman of Actor
	(properties
		x 214
		y 118
		view 550
		loop 4
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
					(SpeakAudio 8)
					(event claimed: TRUE)
				)
				(verbTalk
					(event claimed: TRUE)
					(if (or womanTalkCount (> (woman y?) (+ (ego y?) 25)))
						(SpeakAudio 18)
					else
						(++ womanTalkCount)
						(User canInput: FALSE)
						(woman setScript: womanTalk)
					)
				)
			)
		)
	)
)

(instance arm of Actor
	(properties
		x 226
		y 99
		view 550
		loop 7
		priority 8
		signal fixPriOn
	)
)

(instance cedric of View
	(properties
		x 46
		y 121
		view 225
		loop 7
	)
)

(instance poly1 of Polygon
	(properties
		type PBarredAccess
	)
)
