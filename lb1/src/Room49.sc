;;; Sierra Script 1.0 - (do not remove this comment)
(script# 49)
(include game.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Path)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room49 0
)
(synonyms
	(armoire closet)
	(door panel)
)

(local
	msgID = [49 0 49 1 49 2 49 3]
	local8
	local9
	leftUpPts = [163 106 186 99 PATHEND]
	leftDwnPts = [175 100 150 109 PATHEND]
	rightUpPts = [215 100 197 99 PATHEND]
	rightDwnPts = [206 99 227 109 PATHEND]
	local30
	local31
	local32
	local33
)
(procedure (localproc_011c &tmp i)
	(if (not local8)
		(= local8 1)
		(DrawPic 62 IRISOUT)
		(cast eachElementDo: #hide)
		(myMusic number: 27 loop: -1 play:)
		(if local31 (= i 0) else (= i 4))
		(if (== (ego loop?) 3) (= i (+ i 2)))
		(Print
			[msgID i] [msgID (++ i)]
			#at 65 67
			#dispose
		)
		(LookAround seconds: 4)
	)
)

(procedure (localproc_0192)
	(if local8
		(= local8 0)
		(DrawPic 40 IRISIN)
		(myMusic fade:)
		(addToPics doit:)
		(cast eachElementDo: #show)
		(eyes hide:)
	)
)

(instance rightUpPath of Path

	(method (at n)
		(return [rightUpPts n])
	)
)

(instance leftUpPath of Path

	(method (at n)
		(return [leftUpPts n])
	)
)

(instance rightDwnPath of Path
	
	(method (at n)
		(return [rightDwnPts n])
	)
)

(instance leftDwnPath of Path
	
	(method (at n)
		(return [leftDwnPts n])
	)
)

(instance Room49 of Room
	(properties
		picture 40
	)
	
	(method (init)
		(HandsOn)
		(if
			(or
				(< prevRoomNum 40)
				(and (> prevRoomNum 299) (< prevRoomNum 330))
			)
			(= local31 1)
			(= east 33)
			(= north 32)
			(= south 36)
		else
			(= east 43)
			(= north 42)
			(= south 46)
		)
		(super init:)
		(Load PICTURE 62)
		(LoadMany SOUND 27 74 75 106)
		(if (== south 46)
			(fprotrait cel: 1)
		)
		(addToPics add: fprotrait bprotrait doit:)
		(self setFeatures: Platform)
		(if
			(and
				(>= currentAct 2)
				(not (ego has: 21))
				(== global119 0)
				(< prevRoomNum 49)
			)
			(= global119 (+ (Random 49 50) (curRoom east?)))
		)
		(if
			(and
				(== curRoomNum (- global119 (curRoom east?)))
				(not (ego has: iCane))
			)
			(cane init:)
		)
		(if
			(and
				(>= currentAct 3)
				(not (ego has: iCigarButt))
				(== global168 0)
				(< prevRoomNum 49)
				(==
					(= global168 (+ (Random 49 50) (curRoom east?)))
					global119
				)
			)
			(if (> global119 85)
				(-= global168 10)
			else
				(-= global168 10)
			)
		)
		(if
			(and
				(== curRoomNum (- global168 (curRoom east?)))
				(not (ego has: iCigarButt))
			)
			(cigar ignoreActors: TRUE init:)
		)
		(fpanel
			setLoop: 5
			setCel: 1
			illegalBits: 0
			ignoreActors: TRUE
			setPri: 7
			init:
			stopUpd:
		)
		(bpanel
			setLoop: 3
			setCel: 0
			illegalBits: 0
			setPri: 4
			init:
			stopUpd:
		)
		(eyes setPri: 10 init:)
		(eyes hide:)
		(self setRegions: 212)
		(switch prevRoomNum
			(33 (ego posn: 301 110))
			(43 (ego posn: 301 110))
			(36 (ego posn: 124 115))
			(46 (ego posn: 124 115))
		)
		(if (or (== prevRoomNum 32) (== prevRoomNum 42))
			(ego
				loop: 2
				illegalBits: 0
				posn: 251 101
				setPri: 3
				setScript: Entering
				init:
			)
		else
			(ego view: 0 setPri: 6 illegalBits: cWHITE init:)
		)
	)
	
	(method (doit)
		(if
			(and
				(ego inRect: 120 115 129 118)
				(not script)
				(> currentAct 0)
				(< currentAct 7)
				(!= currentAct 4)
				(< (Random 1 100) 35)
			)
			(self setScript: grabbed)
		)
		(if (and (== prevRoomNum 43) (== global143 0))
			(= global143 1)
			(mySound number: 106 loop: 1 play:)
			(Print 49 4)
		)
		(if (and (== prevRoomNum 33) (== global139 0))
			(= global139 1)
			(mySound number: 106 loop: 1 play:)
			(Print 49 4)
		)
		(if
			(and
				(== [global368 1] 1)
				(not global107)
				(< prevRoomNum 47)
				(== currentAct 4)
			)
			(= global107 1)
			(Print 49 5)
		)
		(if (< (ego x?) 270)
			(= vertAngle 40)
		else
			(= vertAngle 0)
		)
		(if (& (ego onControl: origin) cBLUE)
			(if
				(and
					(== currentAct 0)
					(& global173 $0001)
					(== global199 0)
				)
				(= global199 1)
			)
			(curRoom newRoom: east)
		)
		(if (not local9)
			(if (& (ego onControl:) cBROWN)
				(= local9 1)
				(= vertAngle 0)
				(ego illegalBits: 0)
				(HandsOff)
				(if (< (ego heading?) 180)
					(ego setLoop: 0 setMotion: leftUpPath self)
				else
					(ego setLoop: 1 setMotion: leftDwnPath self)
				)
			)
			(if (& (ego onControl:) cMAGENTA)
				(= local9 1)
				(= vertAngle 0)
				(ego illegalBits: 0)
				(HandsOff)
				(if (< (ego heading?) 180)
					(ego setLoop: 0 setMotion: rightDwnPath self)
				else
					(ego setLoop: 1 setMotion: rightUpPath self)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 983)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((and (not global107) (Said 'smell'))
						(Print 49 5)
					)
					((Said 'examine>')
						(cond 
							((or (Said '/room<billiard') (Said '/library'))
								(if (== west 33)
									(Print 49 6)
								else
									(event claimed: FALSE)
								)
							)
							((or (Said '/room<guest') (Said '/bedroom'))
								(if (== west 43)
									(Print 49 6)
								else
									(event claimed: FALSE)
								)
							)
							((Said '[<around,at][/room]')
								(Print 49 4)
							)
							((Said '/painting')
								(Print 49 7)
							)
							((Said '/door')
								(Print 49 8)
							)
							((or (Said '/dirt') (Said '<down'))
								(if
									(and
										(not (ego has: iCane))
										(== curRoomNum (- global119 prevRoomNum))
									)
									(= local32 1)
									(Print 49 9)
								)
								(if
									(and
										(not (ego has: iCigarButt))
										(== curRoomNum (- global168 prevRoomNum))
									)
									(= local32 1)
									(Print 49 10)
								)
								(if (not local32)
									(event claimed: FALSE)
								)
								(= local32 0)
							)
							((Said '/clock')
								(if (== (curRoom east?) 33)
									(Print 49 11)
								else
									(Print 49 12)
								)
							)
							((Said '/armoire')
								(if (== (curRoom east?) 43)
									(Print 49 13)
								else
									(Print 49 14)
								)
							)
							((Said '[<through,in]/eyehole,eye')
								(if (& (ego onControl: origin) cRED)
									(if (or (== (ego loop?) 2) (== (ego loop?) 3))
										(eyes setScript: LookAround)
									else
										(Print 49 15)
									)
								else
									(NotClose)
								)
							)
						)
					)
					((Said 'move,open/door')
						(cond 
							((& (ego onControl: origin) cCYAN)
								(switch (curRoom south?)
									(36
										(|= global175 $0004)
									)
									(46
										(|= global175 $0040)
									)
								)
								(HandsOff)
								(self setScript: Front)
							)
							((& (ego onControl: origin) cGREEN)
								(switch (curRoom north?)
									(32
										(|= global175 $0001)
									)
									(42
										(|= global175 $0010)
									)
								)
								(self setScript: Back)
							)
							(else
								(NotClose)
							)
						)
					)
					((Said 'close/door')
						(Print 49 16)
					)
					((Said 'get/painting')
						(Print 49 7)
					)
					((Said 'close,drag,press,open/clock')
						(if (== (curRoom east?) 33)
							(Print 49 17)
						else
							(Print 49 12)
						)
					)
					((Said 'close,drag,press,open/armoire')
						(if (== (curRoom east?) 43)
							(Print 49 18)
						else
							(Print 49 14)
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (cue)
		(HandsOn)
		(ego illegalBits: cWHITE setLoop: -1)
		(= local9 0)
	)
	
	(method (newRoom n)
		(ego setPri: -1)
		(if (and (or (Btst 38) (Btst 37)) (!= n 42) (!= n 43))
			(Bclr 38)
			(Bclr 37)
		)
		(super newRoom: n)
	)
)

(instance Front of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local30 0)
				(ego illegalBits: 0)
				(= cycles 2)
			)
			(1
				(ego setMotion: MoveTo 109 115 self)
			)
			(2
				(ego loop: 2)
				(fpanel setMotion: MoveTo 135 119 self)
				(mySound number: 74 loop: 1 play:)
			)
			(3
				(switch (curRoom south?)
					(46
						(if (and (& global173 $0002) (== currentAct 0))
							(= local30 1)
						)
					)
					(36
						(switch currentAct
							(0
								(if
									(or
										(== global199 0)
										(== global199 2)
										(== [global368 2] 1)
									)
									(= local30 1)
								)
							)
							(1 (= local30 1))
							(2 (= local30 1))
						)
					)
				)
				(if (not local30)
					(curRoom newRoom: (curRoom south?))
				else
					(Print 49 19 #at 130 10)
					(= cycles 1)
				)
			)
			(4
				(fpanel setMotion: MoveTo 126 119 self)
				(mySound number: 75 loop: 1 play:)
			)
			(5
				(fpanel stopUpd:)
				(ego illegalBits: cWHITE)
				(HandsOn)
			)
		)
	)
)

(instance Back of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local30 0)
				(HandsOff)
				(bpanel setMotion: MoveTo 272 103 self)
				(mySound number: 74 loop: 1 play:)
			)
			(1
				(= local30 0)
				(switch (curRoom north?)
					(42
						(if
							(or
								(== currentAct 0)
								(== currentAct 1)
								(Btst 38)
								(and
									(< gameMinutes 3)
									(not (& deadGuests deadLILLIAN))
									(not (Btst 37))
								)
							)
							(= local30 1)
						)
					)
					(32
						(if
							(or
								(== currentAct 0)
								(and (== currentAct 1) (>= global154 4))
							)
							(= local30 1)
						)
					)
				)
				(if local30
					(Print 49 19
						#at 105 10
					)
				else
					(= state 3)
				)
				(= cycles 1)
			)
			(2
				(bpanel setMotion: MoveTo 250 103 self)
				(mySound number: 75 loop: 1 play:)
			)
			(3
				(bpanel stopUpd:)
				(HandsOn)
				(ego setLoop: -1 illegalBits: cWHITE)
				(client setScript: 0)
			)
			(4
				(ego
					illegalBits: 0
					setLoop: 3
					setMotion: MoveTo 251 105 self
				)
			)
			(5
				(bpanel setMotion: MoveTo 280 103 self)
			)
			(6
				(ego
					setLoop: 3
					setPri: 3
					illegalBits: 0
					setMotion: MoveTo 251 100 self
				)
			)
			(7
				(bpanel setMotion: MoveTo 250 103 self)
				(mySound number: 75 loop: 1 play:)
			)
			(8
				(ego setLoop: -1 illegalBits: cWHITE)
				(client setScript: 0)
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance Entering of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(bpanel setMotion: MoveTo 280 103 self)
				(mySound number: 74 loop: 1 play:)
			)
			(1
				(ego setPri: 6 setMotion: MoveTo 251 105 self)
			)
			(2
				(bpanel setMotion: MoveTo 250 103 self)
				(mySound number: 75 loop: 1 play:)
			)
			(3
				(HandsOn)
				(ego illegalBits: cWHITE setScript: 0)
			)
		)
	)
)

(instance LookAround of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego yStep: 1)
				(if (== (ego loop?) 2)
					(ego setMotion: MoveTo 190 104 self)
				else
					(ego setMotion: MoveTo 198 97 self)
				)
			)
			(1
				(ego yStep: 2)
				(if (== (ego y?) 104)
					(ego loop: 2)
					(eyes cycleSpeed: 3 setCycle: Forward show:)
					(= seconds 3)
				else
					(ego loop: 3)
					(= cycles 1)
				)
			)
			(2
				(= temp0 0)
				(switch currentAct
					(0
						(cond 
							((== (ego loop?) 3)
								(= temp0
									(switch (== (curRoom east?) 33)
										(1 320)
										(0 350)
									)
								)
							)
							((== (curRoom east?) 33)
								(= temp0 321)
							)
							((& global173 $0002)
								(= temp0 351)
							)
						)
					)
					(1
						(cond 
							((== (ego loop?) 3)
								(if (== (curRoom east?) 33)
									(if (>= global154 4) (= temp0 320))
								else
									(= temp0 352)
								)
							)
							((== (curRoom east?) 33)
								(= temp0 323)
							)
						)
					)
					(2
						(cond 
							((== (ego loop?) 3)
								(if
									(and
										(!= (curRoom east?) 33)
										(< gameMinutes 3)
										(not (Btst 37))
									)
									(= temp0
										(switch (& global173 $0040)
											(0 353)
											(64 350)
										)
									)
								)
							)
							((== (curRoom east?) 33) (= temp0 324))
						)
					)
					(3
						(if
							(and
								(== (ego loop?) 3)
								(== (curRoom east?) 43)
								(< gameMinutes 3)
								(not (Btst 37))
							)
							(= temp0 352)
						)
					)
					(4
						(if (and (== (ego loop?) 3) (== (curRoom east?) 43))
							(cond 
								(
									(or
										(not (& global118 $0002))
										(Btst 38)
										(and (< gameMinutes 3) (not (Btst 37)))
									)
									(= temp0 354)
								)
								((not (& global118 $0004)) (= temp0 355))
							)
						)
					)
					(7
						(if
							(and
								(== (ego loop?) 3)
								(== (curRoom east?) 43)
								(not (& deadGuests deadLILLIAN))
							)
							(= temp0 352)
						)
					)
					(else 
						(if
							(and
								(== (ego loop?) 3)
								(== (curRoom east?) 43)
								(< gameMinutes 3)
								(not (Btst 37))
								(< currentAct 7)
							)
							(= temp0 352)
						)
					)
				)
				(if temp0
					(curRoom newRoom: temp0)
				else
					(localproc_011c)
				)
			)
			(3
				(eyes setScript: 0)
				(cls)
				(localproc_0192)
				(HandsOn)
			)
		)
	)
)

(instance pickUp of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego client)
				(= cycles 2)
			)
			(1
				(ego view: 17 cel: 0 setMotion: 0 setCycle: EndLoop self)
			)
			(2
				(if (== local33 1)
					(Ok)
					(cane hide:)
					(ego get: iCane)
				else
					(Print 49 20)
					(cigar hide:)
					(ego get: iCigarButt)
				)
				(= gotItem TRUE)
				(= cycles 2)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(HandsOn)
				(ego view: 0 setCycle: Walk)
				(client dispose: setScript: 0)
			)
		)
	)
)

(instance grabbed of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 43 loop: 0 cel: 0 setCycle: EndLoop self)
				(mySound number: 87 loop: 1 play:)
			)
			(1 (= seconds 5))
			(2
				(= cIcon 43)
				(= deathLoop 2)
				(= deathCel 1)
				(EgoDead 49 21)
			)
		)
	)
)

(instance fprotrait of PicView
	(properties
		y 78
		x 189
		view 139
		priority 9
	)
)

(instance bprotrait of PicView
	(properties
		y 70
		x 198
		view 139
		loop 1
		priority 4
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/door'))
			(Print 49 8)
			(event claimed: TRUE)
		)
	)
)

(instance fpanel of Actor
	(properties
		y 119
		x 126
		view 139
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(Print 49 8)
			(event claimed: TRUE)
		)
	)
)

(instance bpanel of Actor
	(properties
		y 103
		x 250
		view 139
	)
)

(instance eyes of Prop
	(properties
		y 57
		x 190
		view 139
		loop 2
	)
)

(instance cane of Prop
	(properties
		y 115
		x 276
		view 139
		loop 6
	)
	
	(method (handleEvent event)
		(cond 
			((or (MousedOn self event shiftDown) (Said 'examine/cane'))
				(event claimed: TRUE)
				(Print 49 22)
			)
			((Said 'get/cane')
				(if (< (ego distanceTo: cane) 25)
					(= local33 1)
					(self setScript: pickUp)
				else
					(NotClose)
				)
			)
		)
	)
)

(instance cigar of Prop
	(properties
		y 115
		x 276
		view 139
		loop 7
	)
	
	(method (handleEvent event)
		(cond 
			((or (MousedOn self event shiftDown) (Said 'examine/butt'))
				(event claimed: TRUE)
				(Print 49 23)
			)
			((Said 'get/butt')
				(if (< (ego distanceTo: cigar) 10)
					(= local33 0)
					(self setScript: pickUp)
				else
					(NotClose)
				)
			)
		)
	)
)

(instance Platform of RFeature
	(properties
		nsTop 95
		nsLeft 174
		nsBottom 115
		nsRight 209
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {platform})
		)
	)
)

(instance mySound of Sound)

(instance myMusic of Sound)
