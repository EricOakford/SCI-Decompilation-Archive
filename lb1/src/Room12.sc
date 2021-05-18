;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
(include game.sh)
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
	firstTime
)
(instance Room12 of Room
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
		(= firstTime (FirstEntry))
		(if howFast
			(Lamp setPri: 14 setCycle: Forward init:)
			(Splash1 ignoreActors: TRUE init: hide:)
			(Splash2 ignoreActors: TRUE init: hide:)
		else
			(Lamp setPri: 14 init: stopUpd:)
		)
		(addToPics add: dHouse eachElementDo: #init doit:)
		(Load SCRIPT AVOIDER)
		(LoadMany SOUND 43 44 48)
		(Load VIEW 56)
		(self setRegions: 206 setFeatures: dHouse Window1)
		(Door
			cel: (if (== prevRoomNum 35) 3 else 0)
			setPri: 6
			ignoreActors: TRUE
			init:
			stopUpd:
		)
		(= gDoor_2 Door)
		(Cellar
			cel: (if (== prevRoomNum 53) 3 else 0)
			ignoreActors: TRUE
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
		(dHouse2 ignoreActors: TRUE setPri: 10 init:)
		(switch prevRoomNum
			(6 (ego posn: 318 60))
			(4 (ego posn: 235 55))
			(17 (ego posn: 1 178))
			(35
				(HandsOff)
				(ego posn: 164 122)
				(if (not firstTime)
					(ego setMotion: MoveTo 196 145)
					(Door setCycle: BegLoop)
					(myMusic number: 44 loop: 1 priority: 5 play:)
				)
			)
			(53
				(ego posn: 272 123)
				(Cellar setCycle: BegLoop)
				(myMusic number: 44 loop: 1 priority: 5 play:)
			)
			(23 (ego posn: 295 180))
		)
		(ego view: 0 illegalBits: -32750 init:)
		(= gMyMusic myMusic)
	)
	
	(method (doit)
		(if firstTime
			(= firstTime FALSE)
			(Print 12 0)
			(if (== prevRoomNum 35)
				(ego setMotion: MoveTo 193 145)
				(Door setCycle: BegLoop)
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
		(if (and (& (ego onControl: origin) cBLACK) (not local5))
			(= local5 1)
			(HandsOn)
		)
		(if (& (ego onControl: FALSE) cCYAN)
			(curRoom newRoom: 4)
		)
		(if
			(and
				(& (ego onControl: FALSE) cMAGENTA)
				(not local4)
				(or (== (ego loop?) 1) (== (ego loop?) 3))
			)
			(HandsOff)
			(= local4 1)
			(ego setScript: myDoor2)
		)
		(if (& (ego onControl: FALSE) cBLUE)
			(curRoom newRoom: 35)
		)
		(if (== (ego edgeHit?) EAST)
			(if (< (ego y?) 80)
				(curRoom newRoom: 6)
			else
				(curRoom newRoom: 18)
			)
		)
		(if
			(and
				(& (ego onControl: origin) cYELLOW)
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
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 5)
						(Splash2
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
				(3
					(if (== (ego cel?) 2)
						(Splash1
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 5)
						(Splash2
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
				(else 
					(if (== (ego cel?) 0)
						(Splash1
							posn: (- (ego x?) 2) (+ (ego y?) 1)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 4)
						(Splash2
							posn: (- (ego x?) 2) (+ (ego y?) 1)
							cel: 0
							show:
							setCycle: EndLoop
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
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]')
								(Print 12 0)
							)
							((Said '/fence')
								(Print 12 1)
							)
							((Said '/path')
								(Print 12 2)
							)
							((Said '/stair')
								(Print 12 3)
							)
							((Said '/up')
								(Print 12 4)
							)
						)
					)
					((Said 'climb/fence')
						(Print 12 5)
					)
					((Said 'bang/door<basement')
						(if (& (ego onControl: FALSE) cLGREY)
							(ego setScript: knockDoor)
						else
							(NotClose)
						)
					)
					((or (Said 'bang/[<door,!*,*]') (Said 'bang'))
						(if (& (ego onControl: FALSE) cLGREY)
							(ego setScript: knockDoor)
						else
							(Print 12 6)
						)
					)
				)
			else
				FALSE
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

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego ignoreActors: TRUE setMotion: MoveTo 186 158 self)
			)
			(1
				(ego view: 22 loop: 0 setCycle: EndLoop self)
			)
			(2
				(ego loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(3
				(if global162
					(Print 12 7)
				else
					(if
						(and
							local1
							(not (ego has: iNecklace))
							(== ((inventory at: iNecklace) owner?) 99)
						)
						(Print 12 8)
						(= gotItem TRUE)
						(ego get: iNecklace)
					else
						(Print 12 9)
					)
					(= local1 0)
				)
				(= cycles 1)
			)
			(4
				(ego setCycle: BegLoop self)
			)
			(5
				(ego
					loop: 0
					cel: (- (NumCels ego) 1)
					setCycle: BegLoop self
				)
			)
			(6
				(ego view: 0 loop: 1 ignoreActors: FALSE setCycle: Walk)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance myDoor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 2)
			)
			(1
				(Cellar setCycle: EndLoop self)
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
			(4
				(curRoom newRoom: 53)
			)
		)
	)
)

(instance myDoor2 of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 2)
			)
			(1
				(ego illegalBits: 0 setMotion: MoveTo 175 136 self)
			)
			(2
				(ego setLoop: 1 setMotion: MoveTo 165 122 self)
			)
			(3
				(ego setMotion: 0 illegalBits: cWHITE)
				(Door cycleSpeed: 1 ignoreActors: TRUE setCycle: EndLoop self)
				(myMusic number: 43 loop: 1 priority: 5 play:)
			)
			(4
				(ego setMotion: MoveTo (- (ego x?) 50) (ego y?))
			)
		)
	)
)

(instance knockDoor of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 267 125 self)
			)
			(1
				(ego view: 56 loop: 4 setMotion: 0 setCycle: EndLoop self)
			)
			(2
				(myMusic number: 48 loop: 1 play:)
				(ego loop: 5 setCycle: Forward)
				(= cycles 6)
			)
			(3
				(ego loop: 4 cel: 3 setCycle: BegLoop self)
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
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
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
	
	(method (handleEvent event &tmp locked)
		(= locked FALSE)
		(if
			(or
				(and (== currentAct 1) (< global155 17))
				(== currentAct 3)
				(== currentAct 0)
				(> currentAct 4)
			)
			(= locked TRUE)
		)
		(cond 
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine/(door<basement),basement')
				)
				(event claimed: TRUE)
				(Print 12 12)
			)
			(
				(or
					(Said '/(door<basement),basement>')
					(and (& (ego onControl: FALSE) cLGREY) (Said '/door>'))
				)
				(cond 
					((Said '(examine<in),unbar')
						(if locked
							(Print 12 13)
						else
							(Print 12 14)
						)
					)
					((Said 'open')
						(if (& (ego onControl: FALSE) cLGREY)
							(cond 
								((not locked)
									(HandsOff)
									(ego setScript: myDoor)
								)
								((!= (Cellar cel?) (Cellar lastCel:))
									(Print 12 15)
								)
								(else
									(AlreadyOpen)
								)
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
			((Said 'get/ignite')
				(Print 12 16)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/ignite'))
				(event claimed: TRUE)
				(Print 12 17)
			)
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
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
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
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<(below,behind)/doghouse')
				(Print 12 19)
			)
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
				(event claimed: TRUE)
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
					(MousedOn self event shiftDown)
					(Said 'examine/doghouse')
					(Said 'examine/cabin<beauregard')
				)
				(Print 12 18)
				(event claimed: TRUE)
			)
		)
	)
)

(instance myMusic of Sound)

(instance Window1 of RFeature
	(properties
		nsTop 59
		nsLeft 19
		nsBottom 141
		nsRight 46
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
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
			((or (MousedOn self event shiftDown) (Said 'examine/dish'))
				(event claimed: TRUE)
				(Print 12 21)
			)
			((Said 'get/dish')
				(Print 12 22)
			)
		)
	)
)
