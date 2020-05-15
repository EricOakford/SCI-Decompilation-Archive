;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmElevators) ;340
(include game.sh)
(use Main)
(use Intrface)
(use rmSecurityDesk)
(use LLRoom)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Motion)
(use Actor)
(use System)

(public
	rm340 0
)

(local
	elevatorNumbersCel
)
(procedure (SetUpTheRoom)
	(theIconBar disable:)
	(if (not (Btst fIsVGA))
		(curRoom drawPic: 720)
		(cast eachElementDo: #hide)
	)
	(floorNumber cel: currentHotelFloor)
	(if (== currentHotelFloor 4)
		(suiteDoor init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(if (features contains: door1) (door1 dispose:))
		(theHeart z: 0)
	else
		(theHeart z: 1000)
		(door1 init:)
		(if (features contains: suiteDoor) (suiteDoor dispose:))
	)
	(if (Btst fIsVGA)
		(curRoom drawPic: (curRoom picture?))
		(Animate (cast elements?) FALSE)
	)
	(if (not (Btst fIsVGA))
		(Animate (cast elements?) FALSE)
		(curRoom drawPic: (curRoom picture?))
		(Animate (cast elements?) FALSE)
		(cast eachElementDo: #show)
	)
	(theIconBar enable:)
)

(class Rm340ElevatorNumbers of View ;EO: not in the class table, but it is used as a subclass
	(properties
		name "ElevatorNumbers"
		x 0
		y 180
		z 0
		description {a button}
		view 333
		loop 1
		cel 0
		priority 15
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(self setLoop: (+ (self loop?) 1))
				(Animate (cast elements?) 0)
				(if
					(==
						currentHotelFloor
						(= elevatorNumbersCel (self cel?))
					)
					(HandsOff)
					(self setLoop: (- (self loop?) 1))
					(Animate (cast elements?) FALSE)
					(curRoom setScript: sNoRide)
				else
					(HandsOff)
					(ego illegalBits: 0)
					(if (> elevatorNumbersCel currentHotelFloor)
						(HandsOff)
						(curRoom setScript: sUpElevator)
					else
						(HandsOff)
						(curRoom setScript: sDownElevator)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance rm340 of LLRoom
	(properties
		picture rmElevators
		style 30
	)
	
	(method (init)
		(if debugging (Bset fFawnInRoom) (ego get: iPocketKnife))
		(if (> debugging 1) (Bset fOrderedWine))
		(LoadMany VIEW 340 332 333 331 808)
		(LoadMany SOUND 340 310 341)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 0 0 319 0 319 189 316 189 316 154 222 130 90 128 3 144 3 189 0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						150 135 150 146 164 146 164 135 211 135 231 140
						206 161 109 161 95 145 95 138 109 136
					yourself:
				)
		)
		(soundFx loop: 1 vol: 127 flags: 1)
		(switch prevRoomNum
			(rmElevatorBottom
				(floorNumber cel: 2)
				(if (== currentHotelFloor 2)
					(HandsOff)
					(self setScript: sArriveUp)
				else
					(= elevatorNumbersCel currentHotelFloor)
					(= currentHotelFloor 2)
					(HandsOff)
					(self setScript: sGoUp)
				)
			)
			(rmSecurityDesk
				(theMusic number: 310 loop: -1 flags: 1 play:)
				(floorNumber cel: 7)
				(if (== currentHotelFloor 7)
					(HandsOff)
					(self setScript: sArriveDown)
				else
					(= elevatorNumbersCel currentHotelFloor)
					(= currentHotelFloor 7)
					(HandsOff)
					(self setScript: sGoDown)
				)
			)
			(390
				(theMusic number: 310 loop: -1 flags: 1 play: 90)
				(floorNumber cel: 4)
				(ego x: 40 y: 138 loop: 0 normal: 0)
				(HandsOff)
				(elevator stopUpd:)
				(curRoom setScript: sFrom390)
			)
			(else 
				(ego posn: 160 175)
				(= currentHotelFloor 4)
				(suiteDoor init: approachVerbs: verbDo verbUse verbZipper verbTaste)
				(theHeart z: 0)
			)
		)
		(ego init:)
		(super init:)
		(if (== prevRoomNum 390)
			(suiteDoor init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		else
			(door1 init: approachVerbs: verbDo verbUse verbZipper verbTaste)
			(theHeart z: 1000)
		)
		(elevator
			cycleSpeed: (+ howFast 1)
			moveSpeed: (+ howFast 1)
			init:
			approachVerbs: verbDo verbUse verbZipper verbTaste
		)
		(floorNumber init:)
		(theHeart init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(door2 init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(door3 init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(door4 init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(door5 init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(door6 init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(plants init:)
		(elevatorShaft init: approachVerbs: verbDo verbUse verbZipper verbTaste)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (ego mover?)
			(ego illegalBits: cWHITE)
			(if (cast contains: numberPad)
				(numberPad dispose:)
				(cast eachElementDo: #perform disposeNumbers)
				(if (== (curRoom script?) sElevatorScript)
					(curRoom setScript: 0)
				)
			)
		)
		(cond 
			((IsObjectOnControl ego cCYAN) (theMusic fade: 127 5 5 0))
			((IsObjectOnControl ego cRED) (theMusic fade: 90 5 5 0))
		)
	)
	
	(method (doVerb theVerb theItem)
		(return
			(switch theVerb
				(verbLook
					(Print 340 0)
					(cond 
						((== currentHotelFloor 4) (Print 340 1))
						((and (== (ego x?) 156) (== (ego y?) 133)) (Print 340 2))
						(else (return 1))
					)
				)
				(verbTalk
					(Print 340 3)
					(Print 340 4 #at -1 140)
				)
				(else 
					(super doVerb: theVerb theItem)
				)
			)
		)
	)
	
	(method (newRoom n)
		(if (== n 350) (theMusic fade:))
		(super newRoom: n)
	)
)

(instance sUpElevator of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1
				(numberPad dispose:)
				(cast eachElementDo: #perform disposeNumbers)
				(= cycles 30)
			)
			(2
				(ego z: 1000 hide: normal: 0)
				(elevator
					view: 331
					cel: 0
					setCycle: Forward
					setMotion: MoveTo (elevator x?) -20 self
				)
			)
			(3
				(if
				(== (++ currentHotelFloor) elevatorNumbersCel)
					(if (== currentHotelFloor 8)
						(curRoom newRoom: rmSecurityDesk)
					else
						(SetUpTheRoom)
						(curRoom setScript: sArriveUp)
					)
				else
					(SetUpTheRoom)
					(curRoom setScript: sGoUp)
				)
			)
		)
	)
)

(instance sGoUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(elevator
					view: 331
					y: 190
					setCycle: Forward
					setMotion: MoveTo (elevator x?) -20 self
				)
			)
			(1
				(if
				(== (++ currentHotelFloor) elevatorNumbersCel)
					(if (== currentHotelFloor 8)
						(curRoom newRoom: rmSecurityDesk)
					else
						(SetUpTheRoom)
						(curRoom setScript: sArriveUp)
					)
				else
					(SetUpTheRoom)
					(self init:)
				)
			)
		)
	)
)

(instance sDownElevator of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1
				(numberPad dispose:)
				(cast eachElementDo: #perform disposeNumbers)
				(= cycles 30)
			)
			(2
				(= register 16)
				(ego z: 1000 hide: normal: 0)
				(elevator
					view: 331
					cel: 0
					setCycle: Reverse
					setMotion: MoveTo (elevator x?) 190 self
				)
			)
			(3
				(if
				(== (-- currentHotelFloor) elevatorNumbersCel)
					(if (== currentHotelFloor 1)
						(curRoom newRoom: rmElevatorBottom)
					else
						(SetUpTheRoom)
						(curRoom setScript: sArriveDown)
					)
				else
					(SetUpTheRoom)
					(curRoom setScript: sGoDown)
				)
			)
		)
	)
)

(instance sGoDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(elevator
					view: 331
					y: -20
					setCycle: Reverse
					setMotion: MoveTo (elevator x?) 190 self
				)
			)
			(1
				(if
				(== (-- currentHotelFloor) elevatorNumbersCel)
					(if (== currentHotelFloor 1)
						(curRoom newRoom: rmElevatorBottom)
					else
						(SetUpTheRoom)
						(curRoom setScript: sArriveDown)
					)
				else
					(SetUpTheRoom)
					(self init:)
				)
			)
		)
	)
)

(instance sArriveUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 8)
				(elevator
					view: 331
					y: 190
					setCycle: Forward
					setMotion: MoveTo (elevator x?) 133 self
				)
			)
			(1
				(elevator setCycle: EndLoop self)
			)
			(2
				(soundFx number: 340 play:)
				(ego x: 156 y: 133 z: 0 show:)
				(NormalEgo 2)
				(elevator view: 332 stopUpd:)
				(= cycles 1)
			)
			(3
				(if (not (Btst fFirstElevatorRide)) (Bset fFirstElevatorRide) (Print 340 5))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sArriveDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 16)
				(elevator
					view: 331
					y: -20
					setCycle: Reverse
					setMotion: MoveTo (elevator x?) 133 self
				)
			)
			(1
				(elevator setCycle: EndLoop self)
			)
			(2
				(soundFx number: 340 play:)
				(ego x: 156 y: 133 z: 0 show:)
				(NormalEgo 2)
				(elevator view: 332 stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sElevatorScript of Script
	(properties)
	
	(method (init &tmp temp0 temp1)
		(numberPad init:)
		(= temp0 0)
		(while (< temp0 4)
			(= temp1 0)
			(while (< temp1 2)
				((aNumber new:)
					init:
					z: (+ 84 (* temp0 18))
					x: (+ 240 (* temp1 43))
					cel: (+ 1 (* temp0 2) temp1)
				)
				(++ temp1)
			)
			(++ temp0)
		)
	)
)

(instance sFrom390 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					egoSpeed:
					view: 808
					loop: 3
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(Print 340 6)
				(ego setCycle: BegLoop self)
			)
			(2
				(NormalEgo 1)
				(ego setHeading: 90 self)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance sNoRide of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(Print 340 7)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sDoorMessage of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 340 8 #dispose)
				(ego
					egoSpeed:
					view: 808
					setLoop: (if (< (ego x?) 160) 1 else 0)
					setCel: 0
					setCycle: EndLoop self
				)
				(soundFx number: 341 loop: 1 play: self)
			)
			(1 (ego setCycle: BegLoop self))
			(2 (= seconds 2))
			(3
				(if modelessDialog (modelessDialog dispose:))
				(switch (Random 34 50)
					(34 (Print 340 9))
					(35 (Print 340 10))
					(36 (Print 340 11))
					(37 (Print 340 12))
					(38
						(Print 340 13)
						(Print 340 14)
					)
					(39
						(Print 340 15)
						(Print 340 16)
					)
					(40
						(Print 340 17)
						(Print 340 18)
						(Print 340 19)
						(Print 340 20)
					)
					(41 (Print 340 21))
					(42 (Print 340 22))
					(43 (Print 340 23))
					(44 (Print 340 24))
					(45 (Print 340 25))
					(46
						(Print 340 26)
						(Print 340 27)
					)
					(47 (Print 340 28))
					(48 (Print 340 29))
					(49 (Print 340 30))
					(50 (Print 340 31))
				)
				(= seconds 2)
			)
			(4
				(Print 340 32)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sSuiteDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					egoSpeed:
					view: 808
					setLoop: (if (< (ego x?) 160) 1 else 0)
					setCel: 0
					setCycle: EndLoop self
				)
				(soundFx number: 341 loop: 1 play: self)
			)
			(1
				(Print 340 33)
				(ego setCycle: BegLoop self)
			)
			(2 (= seconds 3))
			(3
				(if (not (Btst 12))
					(Print 340 34)
					(NormalEgo)
					(HandsOn)
					(self dispose:)
				else
					(if (and (Btst fOrderedWine) (not (Btst fSeenDeliveryBoyGag)))
						(Print 340 35)
						(Print 340 36)
						(Print 340 37)
					else
						(Print 340 38)
						(Print 340 39)
					)
					(Print 340 40)
					(theMusic fade:)
					(NormalEgo 1)
					(curRoom newRoom: rmHoneymoonSuite)
				)
			)
		)
	)
)

(instance aNumber of Rm340ElevatorNumbers
	(properties
		description {a button}
	)
)

(instance disposeNumbers of Code
	(properties)
	
	(method (doit param1)
		(if (param1 isKindOf: Rm340ElevatorNumbers)
			(param1 dispose:)
		)
	)
)

(instance elevator of Actor
	(properties
		x 156
		y 133
		description {the elevator}
		sightAngle 40
		approachY 133
		yStep 12
		view 332
		priority 10
		signal (| ignrAct ignrHrz fixPriOn)
		illegalBits $0000
	)
	
	(method (init)
		(self approachX: 155)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (and (== (ego x?) 156) (== (ego y?) 133))
					(Print 340 2)
				else
					(Print 340 41)
				)
			)
			(verbDo
				(curRoom setScript: sElevatorScript)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance floorNumber of View
	(properties
		x 160
		y 86
		view 333
		loop 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance theHeart of Prop
	(properties
		x 20
		y 113
		description {a heart}
		approachX 40
		approachY 138
		lookStr {The hotel has stuck a cheap plastic heart to the door with a thumbtack. That's all it takes to create a Honeymoon Suite!}
		view 340
		loop 1
	)
	
	(method (doVerb)
		(Print 340 42)
	)
)

(instance suiteDoor of Feature
	(properties
		x 19
		y 128
		z 15
		nsTop 93
		nsLeft 7
		nsBottom 134
		nsRight 32
		description {the door with the heart on it}
		sightAngle 40
		approachX 40
		approachY 138
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook (Print 340 43))
			(verbDo
				(HandsOff)
				(curRoom setScript: sSuiteDoor)
			)
			(verbUse (Print 340 44))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance numberPad of View
	(properties
		x 262
		y 170
		z 48
		description {the key pad}
		view 333
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance door1 of Feature
	(properties
		x 11
		y 113
		nsTop 93
		nsLeft 7
		nsBottom 134
		nsRight 32
		description {the door}
		sightAngle 40
		approachX 40
		approachY 138
	)
	
	(method (doVerb theVerb theItem)
		(door2 doVerb: theVerb theItem)
	)
)

(instance door2 of Feature
	(properties
		x 45
		y 112
		nsTop 94
		nsLeft 45
		nsBottom 130
		nsRight 62
		description {the door}
		sightAngle 40
		approachX 71
		approachY 132
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook (Print 340 45))
			(verbDo
				(HandsOff)
				(curRoom setScript: sDoorMessage)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance door3 of Feature
	(properties
		x 100
		y 108
		nsTop 91
		nsLeft 95
		nsBottom 125
		nsRight 121
		description {the door}
		sightAngle 40
		approachX 118
		approachY 129
	)
	
	(method (doVerb theVerb theItem)
		(door2 doVerb: theVerb theItem)
	)
)

(instance door4 of Feature
	(properties
		x 208
		y 108
		nsTop 94
		nsLeft 181
		nsBottom 123
		nsRight 203
		description {the door}
		sightAngle 40
		approachX 187
		approachY 130
	)
	
	(method (doVerb theVerb theItem)
		(door2 doVerb: theVerb theItem)
	)
)

(instance door5 of Feature
	(properties
		x 265
		y 114
		nsTop 93
		nsLeft 239
		nsBottom 135
		nsRight 262
		description {the door}
		sightAngle 40
		approachX 240
		approachY 135
	)
	
	(method (doVerb theVerb theItem)
		(door2 doVerb: theVerb theItem)
	)
)

(instance door6 of Feature
	(properties
		x 308
		y 122
		nsTop 99
		nsLeft 285
		nsBottom 145
		nsRight 305
		description {the door}
		sightAngle 40
		approachX 279
		approachY 145
	)
	
	(method (doVerb theVerb theItem)
		(door2 doVerb: theVerb theItem)
	)
)

(instance plants of Feature
	(properties
		x 160
		y 140
		nsTop 125
		nsLeft 114
		nsBottom 155
		nsRight 206
		description {the plants}
		sightAngle 40
		lookStr {They almost look real, don't they?}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTaste (Print 340 46))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance elevatorShaft of Feature
	(properties
		x 159
		y 74
		nsTop -1
		nsLeft 128
		nsBottom 150
		nsRight 190
		description {the elevator shaft}
		sightAngle 40
		approachX 156
		approachY 133
		lookStr {That elevator must be made by the DNA Company.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(elevator doVerb: theVerb theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
