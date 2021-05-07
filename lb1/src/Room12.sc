;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
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
	Room12 0
)
(synonyms
	(ignite lamp)
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
)
(instance Room12 of Rm
	(properties
		picture 12
	)
	
	(method (init)
		(= south 23)
		(= west 17)
		(= north 6)
		(= local0 0)
		(= local2 0)
		(super init:)
		(= local6 (FirstEntry))
		(if howFast
			(Lamp setPri: 14 setCycle: Fwd init:)
			(Splash1 ignoreActors: 1 init: hide:)
			(Splash2 ignoreActors: 1 init: hide:)
		else
			(Lamp setPri: 14 init: stopUpd:)
		)
		(addToPics add: dHouse eachElementDo: #init doit:)
		(Load rsSCRIPT 985)
		(LoadMany 132 43 44 48)
		(Load rsVIEW 56)
		(self setRegions: 206 setFeatures: dHouse Window1)
		(Door
			cel: (if (== prevRoomNum 35) 3 else 0)
			setPri: 6
			ignoreActors: 1
			init:
			stopUpd:
		)
		(= gDoor_2 Door)
		(Cellar
			cel: (if (== prevRoomNum 53) 3 else 0)
			ignoreActors: 1
			init:
			stopUpd:
		)
		(= gCellar Cellar)
		(if (== [global368 2] 1) (= global155 17))
		(if
			(or
				(and (>= currentAct 1) (< currentAct 5))
				(== currentAct 6)
			)
			(= local1 1)
			(self setRegions: 239)
		else
			(= global162 0)
		)
		(if (or (== currentAct 5) (> currentAct 6))
			(Dish init: stopUpd:)
		)
		(myMusic number: 44 loop: 1 init:)
		(dHouse2 ignoreActors: 1 setPri: 10 init:)
		(switch prevRoomNum
			(6 (ego posn: 318 60))
			(4 (ego posn: 235 55))
			(17 (ego posn: 1 178))
			(35
				(HandsOff)
				(ego posn: 164 122)
				(if (not local6)
					(ego setMotion: MoveTo 196 145)
					(Door setCycle: Beg)
					(myMusic number: 44 loop: 1 priority: 5 play:)
				)
			)
			(53
				(ego posn: 272 123)
				(Cellar setCycle: Beg)
				(myMusic number: 44 loop: 1 priority: 5 play:)
			)
			(23 (ego posn: 295 180))
		)
		(ego view: 0 illegalBits: -32750 init:)
		(= gMyMusic myMusic)
	)
	
	(method (doit)
		(if local6
			(= local6 0)
			(Print 12 0)
			(if (== prevRoomNum 35)
				(ego setMotion: MoveTo 193 145)
				(Door setCycle: Beg)
				(myMusic number: 44 loop: 1 priority: 5 play:)
			)
		)
		(if
			(and
				(not local3)
				(== prevRoomNum 35)
				(== (Door cel?) 0)
			)
			(= local3 1)
			(Door stopUpd:)
		)
		(if
		(and (& (ego onControl: 1) $0001) (not local5))
			(= local5 1)
			(HandsOn)
		)
		(if (& (ego onControl: 0) $0008) (curRoom newRoom: 4))
		(if
			(and
				(& (ego onControl: 0) $0020)
				(not local4)
				(or (== (ego loop?) 1) (== (ego loop?) 3))
			)
			(HandsOff)
			(= local4 1)
			(ego setScript: myDoor2)
		)
		(if (& (ego onControl: 0) $0002) (curRoom newRoom: 35))
		(if (== (ego edgeHit?) 2)
			(if (< (ego y?) 80)
				(curRoom newRoom: 6)
			else
				(curRoom newRoom: 18)
			)
		)
		(if
			(and
				(& (ego onControl: 1) $4000)
				(!= (ego mover?) 0)
				howFast
			)
			(switch (ego loop?)
				(2
					(if (== (ego cel?) 2)
						(Splash1
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: End
						)
					)
					(if (== (ego cel?) 5)
						(Splash2
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: End
						)
					)
				)
				(3
					(if (== (ego cel?) 2)
						(Splash1
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: End
						)
					)
					(if (== (ego cel?) 5)
						(Splash2
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: End
						)
					)
				)
				(else 
					(if (== (ego cel?) 0)
						(Splash1
							posn: (- (ego x?) 2) (+ (ego y?) 1)
							cel: 0
							show:
							setCycle: End
						)
					)
					(if (== (ego cel?) 4)
						(Splash2
							posn: (- (ego x?) 2) (+ (ego y?) 1)
							cel: 0
							show:
							setCycle: End
						)
					)
				)
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
							((Said '[<around,at][/room]') (Print 12 0))
							((Said '/fence') (Print 12 1))
							((Said '/path') (Print 12 2))
							((Said '/stair') (Print 12 3))
							((Said '/up') (Print 12 4))
						)
					)
					((Said 'climb/fence') (Print 12 5))
					((Said 'bang/door<basement')
						(if (& (ego onControl: 0) $0080)
							(ego setScript: knockDoor)
						else
							(NotClose)
						)
					)
					((or (Said 'bang/[<door,!*,*]') (Said 'bang'))
						(if (& (ego onControl: 0) $0080)
							(ego setScript: knockDoor)
						else
							(Print 12 6)
						)
					)
				)
			else
				0
			)
		)
	)
	
	(method (newRoom n)
		(if (or (== n 35) (== n 53))
			(cSound stop:)
			(ego setLoop: -1)
		)
		(super newRoom: n)
	)
)

(instance LookDog of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego ignoreActors: 1 setMotion: MoveTo 186 158 self)
			)
			(1
				(ego view: 22 loop: 0 setCycle: End self)
			)
			(2
				(ego loop: 4 cel: 0 setCycle: End self)
			)
			(3
				(if global162
					(Print 12 7)
				else
					(if
						(and
							local1
							(not (ego has: 0))
							(== ((inventory at: 0) owner?) 99)
						)
						(Print 12 8)
						(= gotItem 1)
						(ego get: 0)
					else
						(Print 12 9)
					)
					(= local1 0)
				)
				(= cycles 1)
			)
			(4 (ego setCycle: Beg self))
			(5
				(ego
					loop: 0
					cel: (- (NumCels ego) 1)
					setCycle: Beg self
				)
			)
			(6
				(ego view: 0 loop: 1 ignoreActors: 0 setCycle: Walk)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance myDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(Cellar setCycle: End self)
				(myMusic number: 43 loop: 1 priority: 5 play:)
			)
			(2
				(ego
					illegalBits: 0
					setCycle: Walk
					setMotion: MoveTo 251 120 self
				)
			)
			(3
				(ego setMotion: MoveTo 227 125 self)
			)
			(4 (curRoom newRoom: 53))
		)
	)
)

(instance myDoor2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(ego illegalBits: 0 setMotion: MoveTo 175 136 self)
			)
			(2
				(ego setLoop: 1 setMotion: MoveTo 165 122 self)
			)
			(3
				(ego setMotion: 0 illegalBits: -32768)
				(Door cycleSpeed: 1 ignoreActors: 1 setCycle: End self)
				(myMusic number: 43 loop: 1 priority: 5 play:)
			)
			(4
				(ego setMotion: MoveTo (- (ego x?) 50) (ego y?))
			)
		)
	)
)

(instance knockDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 267 125 self)
			)
			(1
				(ego view: 56 loop: 4 setMotion: 0 setCycle: End self)
			)
			(2
				(myMusic number: 48 loop: 1 play:)
				(ego loop: 5 setCycle: Fwd)
				(= cycles 6)
			)
			(3
				(ego loop: 4 cel: 3 setCycle: Beg self)
			)
			(4
				(HandsOn)
				(ego view: 0 loop: 1 illegalBits: -32752 setCycle: Walk)
				(if
					(or
						(and (== currentAct 1) (< global155 17))
						(== currentAct 3)
						(== currentAct 0)
						(> currentAct 4)
					)
					(Print 12 10)
				else
					(Print 12 11)
				)
				(client setScript: 0)
			)
		)
	)
)

(instance Door of Prop
	(properties
		y 122
		x 150
		view 117
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {door})
		)
	)
)

(instance Cellar of Prop
	(properties
		y 117
		x 264
		view 112
		priority 8
	)
	
	(method (handleEvent event &tmp temp0)
		(= temp0 0)
		(if
			(or
				(and (== currentAct 1) (< global155 17))
				(== currentAct 3)
				(== currentAct 0)
				(> currentAct 4)
			)
			(= temp0 1)
		)
		(cond 
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/(door<basement),basement')
				)
				(event claimed: 1)
				(Print 12 12)
			)
			(
				(or
					(Said '/(door<basement),basement>')
					(and (& (ego onControl: 0) $0080) (Said '/door>'))
				)
				(cond 
					((Said '(examine<in),unbar') (if temp0 (Print 12 13) else (Print 12 14)))
					((Said 'open')
						(if (& (ego onControl: 0) $0080)
							(cond 
								((not temp0) (HandsOff) (ego setScript: myDoor))
								((!= (Cellar cel?) (Cellar lastCel:)) (Print 12 15))
								(else (AlreadyOpen))
							)
						else
							(NotClose)
						)
					)
				)
			)
		)
	)
)

(instance Lamp of Prop
	(properties
		y 83
		x 130
		view 112
		loop 1
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/ignite') (Print 12 16))
			(
			(or (MousedOn self event 3) (Said 'examine/ignite')) (event claimed: 1) (Print 12 17))
		)
	)
)

(instance Splash1 of Prop
	(properties
		view 1
		loop 6
	)
)

(instance Splash2 of Prop
	(properties
		view 1
		loop 6
	)
)

(instance dHouse2 of Prop
	(properties
		y 159
		x 168
		view 112
		loop 2
		cel 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(Print 12 18)
		)
	)
)

(instance dHouse of RPicView
	(properties
		y 159
		x 151
		view 112
		loop 2
		priority 12
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<(below,behind)/doghouse') (Print 12 19))
			(
				(or
					(Said 'search,(examine<in)/doghouse')
					(Said 'search,(examine<in)/cabin<beauregard')
				)
				(if (ego inRect: 163 149 187 163)
					(HandsOff)
					(ego setScript: LookDog)
				else
					(NotClose)
				)
				(event claimed: 1)
			)
			(
				(or
					(Said 'enter,(get,go<in)/doghouse')
					(Said 'enter,(get,go<in)/cabin<beauregard')
				)
				(Print 12 20)
			)
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/doghouse')
					(Said 'examine/cabin<beauregard')
				)
				(Print 12 18)
				(event claimed: 1)
			)
		)
	)
)

(instance myMusic of Sound
	(properties)
)

(instance Window1 of RFeature
	(properties
		nsTop 59
		nsLeft 19
		nsBottom 141
		nsRight 46
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {window})
		)
	)
)

(instance Dish of Prop
	(properties
		y 159
		x 206
		view 112
		loop 2
		cel 2
	)
	
	(method (handleEvent event)
		(cond 
			(
			(or (MousedOn self event 3) (Said 'examine/dish')) (event claimed: 1) (Print 12 21))
			((Said 'get/dish') (Print 12 22))
		)
	)
)
