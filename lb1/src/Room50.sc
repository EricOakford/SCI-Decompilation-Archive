;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50)
(include sci.sh)
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
	Room50 0
)
(synonyms
	(armoire closet)
	(door panel)
)

(local
	[local0 12] = [50 0 50 1 50 2 50 3 50 4 50 5]
	local12
	local13
	[local14 5] = [105 100 121 99 -32768]
	[local19 5] = [112 99 91 109 -32768]
	[local24 5] = [156 106 133 99 -32768]
	[local29 5] = [144 100 169 109 -32768]
	local34
	local35
	local36
	local37
)
(procedure (localproc_011c &tmp temp0)
	(if (not local12)
		(= local12 1)
		(DrawPic 62 dpOPEN_CENTEREDGE)
		(cast eachElementDo: #hide)
		(myMusic number: 27 loop: -1 play:)
		(if local35 (= temp0 0) else (= temp0 4))
		(if (== (ego loop?) 3) (= temp0 (+ temp0 2)))
		(Print [local0 temp0] [local0 (++ temp0)] 67 65 67 88)
		(LookAround seconds: 4)
	)
)

(procedure (localproc_0192)
	(if local12
		(myMusic stop:)
		(= local12 0)
		(DrawPic 39 dpOPEN_EDGECENTER)
		(addToPics doit:)
		(cast eachElementDo: #show)
		(eyes hide:)
	)
)

(instance rightUpPath of Path
	(properties)
	
	(method (at param1)
		(return [local24 param1])
	)
)

(instance leftUpPath of Path
	(properties)
	
	(method (at param1)
		(return [local14 param1])
	)
)

(instance rightDwnPath of Path
	(properties)
	
	(method (at param1)
		(return [local29 param1])
	)
)

(instance leftDwnPath of Path
	(properties)
	
	(method (at param1)
		(return [local19 param1])
	)
)

(instance Room50 of Rm
	(properties
		picture 39
	)
	
	(method (init)
		(if
			(or
				(< prevRoomNum 40)
				(and (> prevRoomNum 299) (< prevRoomNum 320))
			)
			(= west 33)
			(= north 34)
			(= south 38)
			(= local35 1)
		else
			(= west 43)
			(= north 44)
			(= south 48)
		)
		(super init:)
		(Load rsPIC 62)
		(LoadMany 132 74 27 75 106 87)
		(if (== south 38)
			(protrait cel: 3)
		else
			(protrait cel: 2)
		)
		(addToPics add: protrait bprotrait doit:)
		(self setFeatures: Platform)
		(if
		(and (== currentAct 6) (== (curRoom west?) 43))
			(boot setPri: 3 ignoreActors: 1 init:)
			(if (and (& global118 $0002) (not (Btst 36)))
				(= [local0 6] [local0 10])
				(= [local0 7] [local0 11])
			)
		)
		(if
			(and
				(>= currentAct 2)
				(not (ego has: 21))
				(== global119 0)
				(< prevRoomNum 49)
			)
			(= global119 (+ (Random 49 50) (curRoom west?)))
		)
		(if
			(and
				(>= currentAct 2)
				(not (ego has: 21))
				(== global119 0)
				(< prevRoomNum 49)
			)
			(= global119 (+ (Random 49 50) (curRoom west?)))
		)
		(if
			(and
				(not (ego has: 21))
				(== curRoomNum (- global119 (curRoom west?)))
			)
			(cane init:)
		)
		(if
			(and
				(>= currentAct 3)
				(not (ego has: 8))
				(== global168 0)
				(< prevRoomNum 49)
				(==
					(= global168 (+ (Random 49 50) (curRoom west?)))
					global119
				)
			)
			(if (> global119 85)
				(= global168 (- global168 10))
			else
				(= global168 (+ global168 10))
			)
		)
		(if
			(and
				(not (ego has: 8))
				(== curRoomNum (- global168 (curRoom west?)))
			)
			(cigar ignoreActors: 1 init:)
		)
		(fpanel
			setLoop: 5
			setCel: 0
			illegalBits: 0
			ignoreActors: 1
			priority: 7
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
			(33 (ego posn: 16 110))
			(43 (ego posn: 16 110))
			(38 (ego posn: 193 115))
			(48 (ego posn: 193 115))
		)
		(if (or (== prevRoomNum 34) (== prevRoomNum 44))
			(ego
				loop: 2
				illegalBits: 0
				posn: 68 102
				setPri: 3
				setScript: Entering
				init:
			)
		else
			(ego view: 0 setPri: 6 illegalBits: -32768 init:)
		)
	)
	
	(method (doit)
		(if
			(and
				(ego inRect: 190 111 197 114)
				(not script)
				(< currentAct 7)
				(> currentAct 0)
				(!= currentAct 5)
				(< (Random 1 100) 35)
			)
			(self setScript: grabbed)
		)
		(if (and (== prevRoomNum 43) (== global144 0))
			(= global144 1)
			(mySound number: 106 loop: 1 play:)
			(Print 50 6)
		)
		(if (and (== prevRoomNum 33) (== global140 0))
			(= global140 1)
			(mySound number: 106 loop: 1 play:)
			(Print 50 6)
		)
		(if
			(and
				(== [global368 1] 1)
				(not global107)
				(== currentAct 4)
			)
			(= global107 1)
			(Print 50 7)
		)
		(if (< (ego x?) 50)
			(= vertAngle 0)
		else
			(= vertAngle 140)
		)
		(if (& (ego onControl: 1) $0002)
			(curRoom newRoom: west)
		)
		(if (not local13)
			(if (& (ego onControl:) $0020)
				(= local13 1)
				(= vertAngle 0)
				(ego illegalBits: 0)
				(HandsOff)
				(if (< (ego heading?) 180)
					(ego setLoop: 0 setMotion: leftUpPath self)
				else
					(ego setLoop: 1 setMotion: leftDwnPath self)
				)
			)
			(if (& (ego onControl:) $0040)
				(= local13 1)
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
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((and (not global107) (Said 'smell')) (Print 50 7))
					((Said 'examine>')
						(cond 
							((or (Said '/room<dining') (Said '/parlor')) (if (== west 33) (Print 50 8) else (event claimed: 0)))
							((or (Said '/room<guest') (Said '/bedroom')) (if (== west 43) (Print 50 8) else (event claimed: 0)))
							((Said '[<around,at][/room]') (Print 50 6))
							((Said '/painting') (Print 50 9))
							((Said '/mirror')
								(if (== (curRoom west?) 33)
									(Print 50 10)
								else
									(Print 50 11)
								)
							)
							((Said '/armoire')
								(if (== (curRoom west?) 43)
									(Print 50 12)
								else
									(Print 50 13)
								)
							)
							((Said '/parlor,room<dining')
								(if (== (curRoom west?) 33)
									(Print 50 14)
								else
									(event claimed: 0)
								)
							)
							((or (Said '/dirt') (Said '<down'))
								(if
								(and (== currentAct 6) (== (curRoom west?) 43))
									(= local36 1)
									(Print 50 15)
								)
								(if
									(and
										(not (ego has: 21))
										(== curRoomNum (- global119 prevRoomNum))
									)
									(= local36 1)
									(Print 50 16)
								)
								(if
									(and
										(not (ego has: 8))
										(== curRoomNum (- global168 prevRoomNum))
									)
									(= local36 1)
									(Print 50 17)
								)
								(if (not local36) (event claimed: 0))
								(= local36 0)
							)
							((Said '[<through,in]/eyehole,eye')
								(if (& (ego onControl: 1) $0010)
									(if (or (== (ego loop?) 2) (== (ego loop?) 3))
										(eyes setScript: LookAround)
									else
										(Print 50 18)
									)
								else
									(NotClose)
								)
							)
						)
					)
					((Said 'move,open/door')
						(cond 
							((& (ego onControl: 1) $0008)
								(switch (curRoom south?)
									(38
										(= global175 (| global175 $0008))
									)
									(48
										(= global175 (| global175 $0080))
									)
								)
								(HandsOff)
								(self setScript: Front)
							)
							((& (ego onControl: 1) $0004)
								(switch (curRoom north?)
									(34
										(= global175 (| global175 $0002))
									)
									(44
										(= global175 (| global175 $0020))
									)
								)
								(self setScript: Back)
							)
							(else (NotClose))
						)
					)
					((Said 'close/door') (Print 50 19))
					((Said 'get/painting') (Print 50 9))
					((Said 'close,drag,press,open,move/mirror')
						(if (== (curRoom west?) 33)
							(Print 50 20)
						else
							(Print 50 11)
						)
					)
					((Said 'close,drag,press,open,move/armoire')
						(if (== (curRoom west?) 43)
							(Print 50 21)
						else
							(Print 50 13)
						)
					)
				)
			else
				0
			)
		)
	)
	
	(method (cue)
		(HandsOn)
		(ego illegalBits: -32768 setLoop: -1)
		(= local13 0)
	)
	
	(method (newRoom n)
		(ego setPri: -1)
		(super newRoom: n)
	)
)

(instance Front of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local34 0)
				(ego illegalBits: 0)
				(= cycles 2)
			)
			(1
				(ego setMotion: MoveTo 209 115 self)
			)
			(2
				(mySound number: 74 loop: 1 play:)
				(ego loop: 2)
				(fpanel setMotion: MoveTo 187 119 self)
			)
			(3
				(= local34 0)
				(switch (curRoom south?)
					(48
						(switch currentAct
							(0
								(if (> global199 0) (= local34 1))
							)
							(1 (= local34 1))
							(5 (= local34 1))
							(2 (= local34 1))
						)
					)
					(38
						(switch currentAct
							(0 (= local34 1))
							(1 (= local34 1))
							(4 (= local34 1))
							(3
								(if (and (< gameMinutes 3) (< global192 2))
									(= local34 1)
								)
							)
						)
					)
				)
				(if (not local34)
					(curRoom newRoom: (curRoom south?))
				else
					(Print 50 22 #at 130 10)
					(= cycles 1)
				)
			)
			(4
				(fpanel setMotion: MoveTo 194 119 self)
				(mySound number: 75 loop: 1 play:)
			)
			(5
				(HandsOn)
				(ego illegalBits: -32768)
				(fpanel stopUpd:)
				(client setScript: 0)
			)
		)
	)
)

(instance Back of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local34 0)
				(bpanel setMotion: MoveTo 89 103 self)
				(mySound number: 74 loop: 1 play:)
			)
			(1
				(= local34 0)
				(switch (curRoom north?)
					(34
						(if
							(or
								(== currentAct 4)
								(and (== currentAct 1) (== global154 3))
								(and (== gameMinutes 3) (> [global368 2] 1))
							)
							(= local34 1)
						)
					)
					(44
						(if
							(or
								(== currentAct 5)
								(and
									(== currentAct 0)
									(or (< [global368 4] 20) (== global203 2) global125)
								)
								(and (== currentAct 3) (& global118 $0002))
								(and
									(== currentAct 6)
									(& global118 $0002)
									(not (Btst 36))
								)
							)
							(= local34 1)
						)
					)
				)
				(if local34
					(Print 50 22 #at 10 10 #mode 1)
				else
					(= state 3)
				)
				(= cycles 1)
			)
			(2
				(bpanel setMotion: MoveTo 68 103 self)
				(mySound number: 75 loop: 1 play:)
			)
			(3
				(bpanel stopUpd:)
				(HandsOn)
				(ego setLoop: -1 illegalBits: -32768)
				(client setScript: 0)
			)
			(4
				(ego
					illegalBits: 0
					setLoop: 3
					setMotion: MoveTo 64 105 self
				)
			)
			(5
				(bpanel setMotion: MoveTo 98 103 self)
			)
			(6
				(ego
					setLoop: 3
					setPri: 3
					illegalBits: 0
					setMotion: MoveTo 68 100 self
				)
			)
			(7
				(bpanel setMotion: MoveTo 68 103 self)
				(mySound number: 75 loop: 1 play:)
			)
			(8
				(ego setLoop: -1 illegalBits: -32768)
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance Entering of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(bpanel setMotion: MoveTo 98 103 self)
				(mySound number: 74 loop: 1 play:)
			)
			(1
				(ego setPri: 6 setMotion: MoveTo 68 105 self)
			)
			(2
				(bpanel setMotion: MoveTo 68 103 self)
				(mySound number: 75 loop: 1 play:)
			)
			(3
				(HandsOn)
				(ego illegalBits: -32768 setScript: 0)
			)
		)
	)
)

(instance LookAround of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego yStep: 1)
				(if (== (ego loop?) 2)
					(ego setMotion: MoveTo 131 104 self)
				else
					(ego setMotion: MoveTo 121 97 self)
				)
			)
			(1
				(ego yStep: 2 cel: 2)
				(if (> (ego y?) 100)
					(ego loop: 2)
					(eyes cycleSpeed: 3 setCycle: Fwd show:)
					(= seconds 3)
				else
					(ego loop: 3)
					(= cycles 3)
				)
			)
			(2
				(= temp0 0)
				(switch currentAct
					(0
						(cond 
							((== (ego loop?) 3)
								(if
									(and
										(!= (curRoom west?) 33)
										(or (== [global368 4] 1) (== global203 2) global125)
									)
									(= temp0 330)
								)
							)
							((== (curRoom west?) 33) (= temp0 301))
							(
								(and
									(== (curRoom west?) 43)
									(== (ego loop?) 2)
									(> global199 0)
								)
								(= temp0 332)
							)
						)
					)
					(1
						(cond 
							((== (ego loop?) 3)
								(if
								(and (== (curRoom west?) 33) (== global154 3))
									(= temp0 305)
								)
							)
							((== (curRoom west?) 33) (= temp0 302))
							(else (= temp0 332))
						)
					)
					(2
						(if
						(and (== (ego loop?) 2) (== (curRoom west?) 43))
							(= temp0 333)
						)
					)
					(3
						(if
							(and
								(== (ego loop?) 3)
								(== (curRoom west?) 33)
								(== gameMinutes 3)
							)
							(= temp0 303)
						)
						(if
							(and
								(== (ego loop?) 3)
								(== (curRoom west?) 43)
								(& global118 $0002)
							)
							(= temp0 335)
						)
						(if
							(and
								(== (ego loop?) 2)
								(== (curRoom west?) 33)
								(< gameMinutes 3)
								(< global192 2)
							)
							(= temp0 307)
						)
					)
					(4
						(if (== (curRoom west?) 33)
							(= temp0
								(switch (== (ego loop?) 3)
									(1 306)
									(0 304)
								))
						)
					)
					(else 
						(if
						(and (== 5 currentAct) (== (curRoom west?) 43))
							(= temp0
								(switch (== (ego loop?) 3)
									(1 335)
									(0 334)
								))
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
				(client setScript: 0)
				(cls)
				(localproc_0192)
				(HandsOn)
			)
		)
	)
)

(instance pickUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego client)
				(= cycles 2)
			)
			(1
				(ego view: 17 cel: 0 setMotion: 0 setCycle: End self)
			)
			(2
				(if (== local37 1)
					(Ok)
					(cane hide:)
					(ego get: 21)
				else
					(Print 50 23)
					(cigar hide:)
					(ego get: 8)
				)
				(= gotItem 1)
				(= cycles 2)
			)
			(3 (ego setCycle: Beg self))
			(4
				(HandsOn)
				(ego view: 0 setCycle: Walk)
				(client dispose: setScript: 0)
			)
		)
	)
)

(instance grabbed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 43 loop: 1 cel: 0 setCycle: End self)
				(mySound number: 87 loop: 1 play:)
			)
			(1 (= seconds 5))
			(2
				(= cIcon 43)
				(= deathLoop 2)
				(= deathCel 0)
				(EgoDead 50 24)
			)
		)
	)
)

(instance fpanel of Act
	(properties
		y 119
		x 194
		view 139
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/door'))
			(Print 50 25)
			(event claimed: 1)
		)
	)
)

(instance bpanel of Act
	(properties
		y 103
		x 68
		view 139
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(Print 50 25)
			(event claimed: 1)
		)
	)
)

(instance eyes of Prop
	(properties
		y 58
		x 132
		view 139
		loop 2
	)
)

(instance protrait of RPicView
	(properties
		y 84
		x 130
		view 139
		priority 9
	)
)

(instance bprotrait of RPicView
	(properties
		y 76
		x 121
		view 139
		loop 1
		priority 4
	)
)

(instance cigar of Prop
	(properties
		y 116
		x 56
		view 139
		loop 7
	)
	
	(method (handleEvent event)
		(cond 
			(
			(or (MousedOn self event 3) (Said 'examine/butt')) (event claimed: 1) (Print 50 26))
			((Said 'get/butt')
				(if (< (ego distanceTo: cigar) 10)
					(= local37 0)
					(self setScript: pickUp)
				else
					(NotClose)
				)
			)
		)
	)
)

(instance cane of Prop
	(properties
		y 116
		x 46
		view 139
		loop 6
	)
	
	(method (handleEvent event)
		(cond 
			(
			(or (MousedOn self event 3) (Said 'examine/cane')) (event claimed: 1) (Print 50 27))
			((Said 'get/cane')
				(if (< (ego distanceTo: cane) 25)
					(= local37 1)
					(self setScript: pickUp)
				else
					(NotClose)
				)
			)
		)
	)
)

(instance boot of Prop
	(properties
		y 113
		x 35
		view 139
		loop 8
	)
	
	(method (handleEvent)
		(cond 
			((Said 'get/bootprint') (Print 50 28))
			((Said 'examine<use<monocle/bootprint')
				(if (ego has: 1)
					(if (< (ego distanceTo: boot) 10)
						(Print 50 29)
					else
						(NotClose)
					)
				else
					(DontHave)
				)
			)
		)
	)
)

(instance Platform of RFeature
	(properties
		nsTop 95
		nsLeft 108
		nsBottom 115
		nsRight 146
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {platform})
		)
	)
)

(instance mySound of Sound
	(properties)
)

(instance myMusic of Sound
	(properties)
)
