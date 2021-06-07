;;; Sierra Script 1.0 - (do not remove this comment)
(script# 32)
(include game.sh)
(use Main)
(use ElevGate)
(use Intrface)
(use RFeature)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room32 0
)
(synonyms
	(painting beauregard)
	(bookcase bookcase shelf)
	(room library)
)

(local
	local0
	local1
	newElevGate
	local3
	local4
	local5
	local6
	local7
	msgID = [32 0 32 1]
)
(instance Room32 of Room
	(properties
		picture 32
	)
	
	(method (init)
		(= east 33)
		(super init:)
		(if (== (& global111 $7fff) curRoomNum)
			(= [msgID 0] [msgID 2])
			(= [msgID 1] [msgID 3])
		)
		(addToPics
			add:
				mantle
				books
				chair2
				chair3
				chair4
				chair5
				chair6
				sofa1
				sofa2
				table
				tools
		)
		(lamp1 setPri: 5 setCycle: Forward init:)
		(lamp2 setPri: 5 setCycle: Forward init:)
		(fire loop: (/ currentAct 2) setCycle: Forward init:)
		(if (not howFast)
			(lamp1 addToPic:)
			(lamp2 addToPic:)
			(fire addToPic:)
		)
		(if (!= prevRoomNum 66)
			(if (!= prevRoomNum 49)
				(if (== prevRoomNum 33)
					(ego posn: 310 150)
				else
					(ego posn: 55 120)
				)
				(ego view: 0 illegalBits: cWHITE setPri: -1)
			else
				(ego view: 0 illegalBits: cWHITE setPri: -1 posn: 252 167)
				(= local6 1)
			)
		)
		(if (>= currentAct 2)
			(Mag ignoreActors: TRUE init: stopUpd:)
			(if (< currentAct 3)
				(Feather setPri: 8 ignoreActors: TRUE init:)
				(chair1 view: 132 loop: 2 cel: 3 y: 135)
				(tools cel: 1)
			)
			(if (and ((inventory at: iPoker) ownedBy: 32) (< currentAct 3))
				(Load VIEW 17)
				(poker setPri: 9 stopUpd: init:)
			)
			(addToPics add: chair1 tools)
		)
		(self
			setRegions: 213
			setFeatures:
				chair2
				chair3
				chair4
				chair5
				chair6
				chair1
				sofa1
				sofa2
				table
				Shaft
				books
				Case1
				Case2
				mantle
				Mirror
		)
		(if (not howFast)
			(self setFeatures: lamp1 lamp2 fire)
		)
		(if
			(and
				(<= (Random 1 100) 35)
				(> currentAct 0)
				(< currentAct 3)
			)
			(Shadow illegalBits: 0 posn: 13 82 setPri: 1 init:)
			(Shadow setScript: shadowWalk)
		)
		(switch currentAct
			(0
				(= local0 1)
				(= local7 1)
				(self setRegions: 220)
			)
			(1
				(if
					(and
						(not (& global109 $0020))
						(or (== global154 2) (>= global154 4))
					)
					(if (== global154 2) (User canInput: 0))
					(= local0 1)
					(= local7 1)
					(self setRegions: 402)
				)
				(if (<= global154 3)
					(addToPics add: chair1)
				)
			)
			(2
				(if (< global198 100)
					(= global198 100)
				)
			)
			(3
				(if
					(and
						(!= prevRoomNum 42)
						(< gameMinutes 3)
						(not (& global141 $0002))
					)
					(self setRegions: 269)
					(= global111 42)
				)
			)
		)
		(if (not local0)
			(= newElevGate (ElevGate new:))
			(newElevGate
				chainID: chain
				elevatorID: elevator
				downID: down
				upID: up
				init:
			)
		else
			(elevGate
				cel:
					(if (& global109 $0001)
						(- (NumCels elevGate) 1)
					else
						(&= global109 $fffe)
					)
				setPri: 9
				init:
				stopUpd:
			)
			(ego init:)
		)
		(addToPics eachElementDo: #init doit:)
	)
	
	(method (doit)
		(if (and (== currentAct 3) (== gDoor 1))
			(= local7 1)
		)
		(if (and (== currentAct 3) (== gDoor 0))
			(= local7 0)
		)
		(if (and (not (& global109 $0010)) (FirstEntry))
			(Print [msgID 0] [msgID 1])
		)
		(if local6
			(= local6 0)
			(Print 32 2)
		)
		(if (== global198 100)
			(= global198 101)
			(Print 32 3)
		)
		(if (& (ego onControl: origin) cGREEN)
			(curRoom newRoom: 31)
		)
		(cond 
			((< (ego x?) 140) (= vertAngle 44))
			((< (ego x?) 260) (= vertAngle 27))
			(else (= vertAngle 18))
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 204)
		(DisposeScript 201)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if (== (event type?) saidEvent)
			(DisposeScript SAVE)
			(if
				(and
					global208
					(Said
						'ask,tell,hold,deliver,examine,get,kill,kiss,embrace,flirt>'
					)
				)
				(self setScript: (ScriptID 243 0))
				((self script?) handleEvent: event)
				(if (event claimed?) (return))
			)
			(if (== local0 1)
				(= local4 -1)
				(cond 
					((or (Said '*/elevator,lift') (Said 'lift[<open][/*]'))
						(Print 32 4)
					)
					((Said 'examine>')
						(cond 
							((Said '/archway')
								(Print 32 5)
							)
							((Said '/shaft')
								(cond 
									((< curRoomNum global111) (Print 32 6))
									((> curRoomNum global111) (Print 32 7))
									((not (& (ego onControl: origin) (| cMAGENTA cCYAN))) (NotClose))
								)
							)
						)
					)
					((Said 'open/archway')
						(= local3 1)
						(cond 
							((& global109 $0001) (AlreadyOpen))
							((& (ego onControl: origin) cCYAN) (= local4 3))
							((& (ego onControl: origin) cMAGENTA) (= local4 2))
							(else (NotClose))
						)
					)
					((Said 'close/archway')
						(= local3 0)
						(cond 
							((not (& global109 $0001))
								(AlreadyClosed)
							)
							((& (ego onControl: origin) cRED)
								(if (== (ego y?) (elevGate y?)) (Print 32 8) (return))
								(= local4 3)
							)
							((& (ego onControl: origin) cMAGENTA)
								(= local4 2)
							)
							(else (NotClose))
						)
					)
				)
				(if (!= local4 -1)
					(elevGate setScript: GateFunc)
				)
			)
			(if (event claimed?) (return))
			(cond 
				((Said '/panel,(door<hidden)>')
					(cond 
						((Said 'examine')
							(if (& global175 $0001)
								(Print 32 9)
							else
								(Print 32 10)
							)
						)
						((and (& global175 $0001) (Said 'open,move'))
							(if (not local7)
								(if (& (ego onControl: 1) $0080)
									(curRoom newRoom: 49)
								else
									(NotClose)
								)
							else
								(Print 32 11)
							)
						)
					)
				)
				((Said 'examine>')
					(cond 
						((Said '[<around,at][/room]')
							(cond 
								((cast contains: poker)
									(&= global166 (~ (<< $0001 currentAct)))
									(Print 32 3)
								)
								((& (ego onControl: 0) cMAGENTA) (Print 32 12))
								(else (Print [msgID 0] [msgID 1]))
							)
						)
						((or (Said '<up') (Said '/elevator'))
							(if (& (ego onControl: 0) cMAGENTA)
								(Print 32 6)
							else
								(event claimed: FALSE)
							)
						)
						((Said '<behind,below/mirror') (Print 32 13))
						((or (Said '<in/mirror') (Said '/reflection'))
							(if (< (ego distanceTo: fire) 70)
								(= theTalker talkLAURA)
								(Say 0 32 14)
							else
								(NotClose)
							)
						)
						((Said '/mirror')
							(Print 32 15)
						)
						((>= currentAct 2)
							(cond 
								((Said '[<down][/dirt]')
									(cond 
										((== currentAct 2)
											(if (not (ego has: 6))
												(Print 32 16)
											)
											(Print 32 17)
										)
										((and (== currentAct 3) (not (& global141 $0002)))
											(Print 32 17)
										)
										(else
											(event claimed: FALSE)
											(return)
										)
									)
								)
								((Said '/chair')
									(if (< currentAct 3)
										(Print 32 18)
									else
										(event claimed: FALSE)
									)
								)
								((Said '/nightstand')
									(Print 32 19)
								)
								((Said '/magazine')
									(if (& (ego onControl:) cBROWN)
										(Print 32 20)
									else
										(NotClose)
									)
								)
								((Said '/racehorse')
									(if (& (ego onControl:) cBROWN)
										(Print 32 21)
									else
										(Print 32 22)
									)
								)
							)
						)
						((Said '/eye,(painting<eye)')
							(Print 32 23)
						)
						((Said '<behind,below/painting')
							(Print 32 24)
						)
						((or (Said '/painting') (Said '/painting/painting'))
							(Print 32 25)
						)
					)
				)
				((Said 'move,get/painting')
					(Print 32 26)
				)
				((Said 'get>')
					(cond 
						((Said '/fire,log') (Print 32 27))
						((Said '/book/nightstand') (Print 32 28))
						((Said '/book') (Print 32 29))
						((Said '/mirror') (Print 32 30))
						((and (>= currentAct 2) (< currentAct 3))
							(cond 
								((Said '/poker')
									(if (not (ego has: iPoker))
										(if (& (ego onControl:) cLBLUE)
											(self setScript: pickUp)
										else
											(NotClose)
										)
									else
										(AlreadyTook)
									)
								)
								((Said '/magazine')
									(Print 32 31)
								)
							)
						)
					)
				)
				((Said 'read>')
					(cond 
						((Said '/book/nightstand') (Print 32 28))
						((Said '/book') (Print 32 29))
						((and (>= currentAct 2) (Said '/magazine'))
							(if (& (ego onControl:) cBROWN)
								(Print 32 20)
							else
								(NotClose)
							)
						)
					)
				)
			)
		)
		(super handleEvent: event)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance GateFunc of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(elevGate startUpd:)
				(if (== local3 1)
					(cSound number: 81)
					(elevGate setCycle: EndLoop)
					(|= global109 $0001)
				else
					(cSound number: 79)
					(elevGate setCycle: BegLoop)
					(&= global109 $fffe)
				)
				(cSound loop: 1 play: self)
				(if (!= local4 -1)
					(ego loop: local4)
				)
			)
			(1
				(elevGate ignoreActors: (elevGate cel?) stopUpd:)
				(client setScript: 0)
			)
		)
	)
)

(instance pickUp of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego poker)
				(= cycles 2)
			)
			(1
				(ego view: 17 cel: 0 setMotion: 0 setCycle: EndLoop self)
			)
			(2
				(Print 32 32)
				(poker hide:)
				(= gotItem TRUE)
				(ego get: iPoker)
				(= cycles 2)
			)
			(3
				(ego setCycle: BegLoop self)
			)
			(4
				(HandsOn)
				(ego loop: 0 view: 0 setCycle: Walk)
				(client setScript: 0)
			)
		)
	)
)

(instance shadowWalk of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 8))
			(1
				(Shadow setMotion: MoveTo 295 82 self)
			)
			(2
				(Shadow dispose:)
				(client setScript: 0)
			)
		)
	)
)

(instance mantle of RPicView
	(properties
		y 54
		x 192
		view 132
		priority 5
		signal ignrAct
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {mantel})
		)
	)
)

(instance books of RPicView
	(properties
		y 120
		x 145
		view 132
		loop 1
		priority 10
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine/book/nightstand')
				(Print 32 33)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/book'))
				(Print 32 33)
				(event claimed: TRUE)
			)
		)
	)
)

(instance chair1 of RPicView
	(properties
		y 135
		x 200
		view 132
		loop 1
		cel 2
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get,(get<up),straighten/chair')
				(if (== currentAct 2)
					(Print 32 34)
				else
					(event claimed: FALSE)
				)
			)
			((MousedOn self event shiftDown)
				(event claimed: TRUE)
				(ParseName {chair})
			)
		)
	)
)

(instance chair2 of RPicView
	(properties
		y 136
		x 108
		view 132
		loop 1
		cel 4
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {chair})
		)
	)
)

(instance chair3 of RPicView
	(properties
		y 152
		x 118
		view 132
		loop 1
		cel 3
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {chair})
		)
	)
)

(instance chair4 of RPicView
	(properties
		y 152
		x 153
		view 132
		loop 1
		cel 3
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {chair})
		)
	)
)

(instance chair5 of RPicView
	(properties
		y 94
		x 265
		view 132
		loop 2
		cel 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {chair})
		)
	)
)

(instance chair6 of RPicView
	(properties
		y 94
		x 118
		view 132
		loop 2
		cel 2
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {chair})
		)
	)
)

(instance sofa1 of RPicView
	(properties
		y 113
		x 131
		view 132
		loop 2
		signal ignrAct
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {couch})
		)
	)
)

(instance sofa2 of RPicView
	(properties
		y 113
		x 212
		view 132
		loop 2
		signal ignrAct
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {couch})
		)
	)
)

(instance table of RPicView
	(properties
		y 114
		x 71
		view 132
		loop 1
		cel 1
		signal ignrAct
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 32 35)
		)
	)
)

(instance tools of PicView
	(properties
		y 91
		x 166
		view 132
		loop 3
	)
)

(instance fire of Prop
	(properties
		y 85
		x 191
		view 232
		priority 9
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/log'))
			(event claimed: TRUE)
			(ParseName {fire})
		)
	)
)

(instance poker of Prop
	(properties
		y 149
		x 189
		view 132
		loop 3
		cel 2
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 32 16)
		)
	)
)

(instance lamp1 of Prop
	(properties
		y 45
		x 119
		view 132
		loop 6
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {lamp})
		)
	)
)

(instance lamp2 of Prop
	(properties
		y 45
		x 268
		view 132
		loop 6
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {lamp})
		)
	)
)

(instance Feather of Prop
	(properties
		y 151
		x 227
		view 132
		loop 5
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/feather')
				(Print 32 36)
			)
			((or (Said 'examine/feather') (MousedOn self event shiftDown))
				(Print 32 37)
				(event claimed: TRUE)
			)
		)
	)
)

(instance Mag of Prop
	(properties
		y 116
		x 176
		view 132
		loop 1
		cel 5
		priority 10
		signal fixPriOn
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'open/magazine')
				(AlreadyOpen)
			)
			((or (Said 'rotate/page') (Said 'read<more/magazine'))
				(Print 32 38)
			)
			((or (Said 'examine/magazine') (MousedOn self event shiftDown))
				(event claimed: TRUE)
				(if (& (ego onControl:) cBROWN)
					(Print 32 20)
				else
					(NotClose)
				)
			)
		)
	)
)

(instance Shaft of RFeature
	(properties
		nsTop 65
		nsLeft 281
		nsBottom 125
		nsRight 310
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {elevator})
		)
	)
)

(instance Mirror of RFeature
	(properties
		nsTop 13
		nsLeft 177
		nsBottom 41
		nsRight 211
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {mirror})
		)
	)
)

(instance Case1 of RFeature
	(properties
		nsTop 58
		nsBottom 139
		nsRight 38
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/bookcase'))
			(event claimed: TRUE)
			(Print 32 39)
		)
	)
)

(instance Case2 of RFeature
	(properties
		nsTop 24
		nsLeft 84
		nsBottom 76
		nsRight 107
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/bookcase'))
			(event claimed: TRUE)
			(Print 32 39)
		)
	)
)

(instance Shadow of Actor
	(properties
		view 110
	)
)

(instance chain of Actor)

(instance elevator of Actor)

(instance down of View)

(instance up of View)

(instance elevGate of Prop
	(properties
		y 125
		x 283
		view 242
		loop 2
	)
)
