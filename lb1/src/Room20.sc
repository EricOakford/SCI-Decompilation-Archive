;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include sci.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room20 0
)

(local
	local0
	local1
	local2
)
(instance Room20 of Rm
	(properties
		picture 20
	)
	
	(method (init)
		(= west 13)
		(= east 21)
		(= north 14)
		(= horizon 96)
		(super init:)
		(LoadMany 132 43 44)
		(if howFast (Squirel init: setScript: runAway))
		(Door
			cel: (if (== prevRoomNum 61) 3 else 0)
			init:
			stopUpd:
		)
		(self setFeatures: Window1 Window2 Window3 Window4 Box)
		(if
		(and (>= currentAct 2) (== global113 curRoomNum))
			(self setRegions: 202)
		)
		(if
			(and
				(== global114 curRoomNum)
				(or
					(== currentAct 3)
					(and (== currentAct 6) (not (& global118 $0002)))
				)
			)
			(self setRegions: 281)
		)
		(switch prevRoomNum
			(25 (ego posn: 84 188))
			(26 (ego posn: 297 188))
			(14 (ego posn: 305 100))
			(61
				(= local1 1)
				(ego loop: 2 posn: 82 166)
				(self setScript: enterFrom61)
			)
			(13 (ego posn: 3 177))
		)
		(ego view: 0 illegalBits: -32768 init:)
	)
	
	(method (doit)
		(if (FirstEntry) (Print 20 0))
		(if
			(and
				(& (ego onControl: 1) $0008)
				(== (ego loop?) 3)
				(not local0)
			)
			(= local0 1)
			(self setScript: myDoor)
		)
		(if (& (ego onControl: 1) $0002) (curRoom newRoom: 61))
		(if (== (ego edgeHit?) 3)
			(if (< (ego x?) 188)
				(curRoom newRoom: 25)
			else
				(curRoom newRoom: 26)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said 'examine>')
						(cond 
							(
								(or
									(Said '[<around,at][/room]')
									(Said '/cabin[<buggy]')
								)
								(Print 20 0)
							)
							((Said '/drive,path') (Print 20 1))
						)
					)
					((Said 'open/box,box') (Print 20 2))
					((Said 'move,press,get/box,box') (Print 20 3))
				)
			else
				0
			)
		)
	)
	
	(method (newRoom n)
		(if (and (& deadGuests $0008) (!= n 61))
			(= global200 101)
		)
		(if (== n 61) (cSound stop:))
		(super newRoom: n)
	)
)

(instance myDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0)
				(myMusic number: 43 loop: 1 play:)
				(Door cycleSpeed: 3 setCycle: End self)
			)
			(1
				(Door ignoreActors: 1 stopUpd:)
				(if (ego inRect: 80 0 84 200)
					(= cycles 1)
				else
					(ego setMotion: MoveTo 82 (ego y?) self)
				)
			)
			(2
				(ego setMotion: MoveTo 82 158 self)
			)
			(3 (client setScript: 0))
		)
	)
)

(instance enterFrom61 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 82 175 self)
			)
			(1
				(Door cycleSpeed: 1 setCycle: Beg self)
				(myMusic number: 44 loop: 1 play:)
			)
			(2
				(Door stopUpd:)
				(HandsOn)
				(= local1 0)
				(client setScript: 0)
			)
		)
	)
)

(instance runAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 8)))
			(1 (Squirel setCycle: End self))
			(2
				(Squirel posn: 309 (Squirel y?) setCycle: End self)
			)
			(3
				(Squirel dispose:)
				(client setScript: 0)
			)
		)
	)
)

(instance Squirel of Prop
	(properties
		y 133
		x 286
		view 206
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get,capture/squirrel') (Print 20 4))
			(
			(or (MousedOn self event 3) (Said 'examine/squirrel')) (event claimed: 1) (Print 20 5))
		)
	)
)

(instance Door of Prop
	(properties
		y 166
		x 73
		view 120
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/door'))
			(event claimed: 1)
			(Print 20 6)
		)
	)
)

(instance Window1 of RFeature
	(properties
		nsTop 99
		nsBottom 131
		nsRight 31
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'open/window') (Print 20 7))
			((Said 'break/window') (Print 20 8))
			(
			(Said 'examine<(in,through)/window,(cabin<buggy)')
				(if (& (ego onControl: 0) $0040)
					(Print 20 9)
				else
					(NotClose)
				)
			)
			(
			(or (MousedOn self event 3) (Said 'examine/window')) (event claimed: 1) (Print 20 10))
		)
	)
)

(instance Window2 of RFeature
	(properties
		nsTop 90
		nsLeft 143
		nsBottom 116
		nsRight 209
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 20 10)
		)
	)
)

(instance Window3 of RFeature
	(properties
		nsTop 51
		nsLeft 73
		nsBottom 72
		nsRight 114
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 20 10)
		)
	)
)

(instance Window4 of RFeature
	(properties
		nsTop 30
		nsLeft 238
		nsBottom 50
		nsRight 273
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 20 10)
		)
	)
)

(instance Box of RFeature
	(properties
		nsTop 93
		nsLeft 252
		nsBottom 143
		nsRight 289
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<in/box') (Print 20 2))
			(
			(or (MousedOn self event 3) (Said 'examine/box')) (event claimed: 1) (Print 20 11))
		)
	)
)

(instance myMusic of Sound
	(properties)
)
