;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmSecurityDesk) ;350
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Motion)
(use Actor)
(use System)

(public
	rm350 0
)

(local
	[local0 3]
	[closePts 28] = [319 0 319 189 314 189 314 159 296 154 253 154 234 148 234 139 213 133 81 133 3 153 3 189 0 189]
	[openPts 34] = [262 0 262 151 237 151 237 139 213 133 81 133 3 153 3 184 315 184 314 158 293 152 273 151 273 0 319 0 319 189 0 189]
	[middlePts 28] = [85 144 108 137 149 137 149 146 161 146 161 137 205 137 222 145 213 155 229 157 224 172 181 172 179 160 85 160]
)
(class Rm350ElevatorNumbers of View
	(properties
		name "ElevatorNumbers"
		y 180
		description {the button}
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
				(Animate (cast elements?) FALSE)
				(if (== (= currentHotelFloor (self cel?)) 8)
					(HandsOff)
					(self setLoop: (- (self loop?) 1))
					(Animate (cast elements?) FALSE)
					(curRoom setScript: sNoRide)
				else
					(HandsOff)
					(curRoom setScript: sToElevator)
				)
			)
			(verbTalk (curRoom doVerb: verbTalk))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance rm350 of LLRoom
	(properties
		picture 350
	)
	
	(method (init)
		(if debugging (Bset fFaithGone))
		(LoadMany VIEW 350 808 333 331 332)
		(LoadMany SOUND 350 340 351 353)
		(soundFx loop: 1 vol: 127 flags: mNOPAUSE)
		(closeObstacle points: @closePts size: 14)
		(openObstacle points: @openPts size: 19)
		(middleObstacle points: @middlePts size: 14)
		(closeObstacles add: middleObstacle closeObstacle)
		(openObstacles add: middleObstacle openObstacle)
		(curRoom obstacles: closeObstacles)
		(ego init:)
		(super init:)
		(if (!= prevRoomNum rmFaithCloseup)
			(theMusic number: 351 loop: -1 vol: 127 flags: 1 play:)
		)
		(doors
			cycleSpeed: (+ howFast 1)
			init:
			approachVerbs: verbDo verbUse verbZipper verbTaste
		)
		(elevator
			cycleSpeed: (+ howFast 1)
			moveSpeed: (+ howFast 1)
			init:
			approachVerbs: verbDo verbUse verbZipper verbTaste
		)
		(deskF init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(if (not (Btst fFaithGone))
			(LoadMany VIEW 352)
			(faith
				cycleSpeed: howFast
				moveSpeed: howFast
				init:
				stopUpd:
				approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbLook
			)
		else
			(deskF
				lookStr:
					{Now that you're no longer staring at Faith's ample weapons, you notice an unlabeled button on her desk.}
			)
		)
		(switch prevRoomNum
			(rmLivingRoom
				(elevator stopUpd:)
				(ego x: 400)
				(HandsOff)
				(curRoom setScript: sFromPenthouse)
			)
			(rmElevators
				(doors stopUpd:)
				(HandsOff)
				(curRoom setScript: sFromElevator)
			)
			(rmFaithCloseup
				(elevator stopUpd:)
				(ego loop: 1 show: normal: TRUE setMotion: 0)
				(if (CheckItemOwner iPills)
					(LoadMany SOUND 353)
					(curRoom setScript: sFaithLeaves)
				)
			)
			(else  (ego posn: 160 170))
		)
		(plants init:)
		(door1 init:)
		(door2 init:)
		(door3 init:)
		(door4 init:)
		(sculpture init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and (ego mover?) (cast contains: numberPad))
			(numberPad dispose:)
			(ego illegalBits: cWHITE)
			(cast eachElementDo: #perform disposeNumbers)
			(if (== (curRoom script?) sElevatorScript)
				(curRoom setScript: 0)
			)
		)
		(cond 
			(script)
			((IsObjectOnControl ego cBLUE) (HandsOff) (curRoom setScript: sToPenthouse))
		)
	)
	
	(method (dispose)
		(closeObstacles dispose:)
		(openObstacles dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
				(if (Btst fFaithGone)
					(Print 350 0)
					(Print 350 1 #at -1 140)
				else
					(Print 350 2)
				)
			)
			(5
				(Print 350 3)
				(Print 350 4 #at -1 140)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance sFromElevator of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(elevator
					view: 331
					y: 190
					setCycle: Forward
					setMotion: MoveTo (elevator x?) 137 self
				)
			)
			(1
				(elevator setCycle: EndLoop self)
			)
			(2
				(soundFx number: 340 play:)
				(ego x: 156 y: 137 z: 0 show:)
				(NormalEgo 2)
				(elevator view: 332 stopUpd:)
				(if (not (Btst fFirstElevatorRide)) (Bset fFirstElevatorRide) (Print 350 5))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sToElevator of Script
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
					setCycle: Reverse
					setMotion: MoveTo (elevator x?) 190 self
				)
			)
			(3
				(theMusic fade:)
				(curRoom newRoom: 340)
			)
		)
	)
)

(instance sElevatorScript of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (ego mover?)
			(numberPad dispose:)
			(cast eachElementDo: #perform disposeNumbers)
			(self dispose:)
		)
	)
	
	(method (changeState newState &tmp i n)
		(switch (= state newState)
			(0
				(numberPad init:)
				(= i 0)
				(while (< i 4)
					(= n 0)
					(while (< n 2)
						((aNumber new:)
							init:
							z: (+ 84 (* i 18))
							x: (+ 240 (* n 43))
							cel: (+ 1 (* i 2) n)
						)
						(++ n)
					)
					(++ i)
				)
			)
		)
	)
)

(instance sFaithLeaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 10))
			(1
				(theMusic number: 353 loop: 1 play:)
				(employeeExit init:)
				(Print 350 6)
				(faith
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 5 185 self
				)
			)
			(2
				(Print 350 7)
				(Print 350 8)
				(faith setMotion: MoveTo -40 185 self)
			)
			(3
				(theMusic number: 351 loop: -1 play:)
				(Print 350 9)
				(Bset fFaithGone)
				(deskF
					lookStr:
						{Now that you're no longer staring at Faith's ample weapons, you notice an unlabeled button on her desk.}
				)
				(HandsOn)
				(self dispose:)
				(faith dispose:)
			)
		)
	)
)

(instance sToPenthouse of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego illegalBits: cWHITE setMotion: MoveTo 267 149 self)
			)
			(1 (ego setHeading: 270 self))
			(2
				(soundFx number: 350 play:)
				(ego illegalBits: 0 normal: 0 hide:)
				(doors setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(3
				(if modelessDialog (modelessDialog dispose:))
				(ego illegalBits: cWHITE)
				(Print 350 10)
				(SolvePuzzle fBeenInPenthouse 5)
				(curRoom newRoom: rmLivingRoom)
			)
		)
	)
)

(instance sFromPenthouse of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(soundFx number: 350 play:)
				(doors setLoop: 1 setCel: 5 setCycle: BegLoop self)
			)
			(2
				(ego x: 267 y: 149 illegalBits: 0)
				(= cycles 5)
			)
			(3
				(doors setLoop: 0 setCel: 5)
				(ego
					egoSpeed:
					show:
					normal: TRUE
					setMotion: MoveTo (- (ego x?) 10) (+ (ego y?) 5)
				)
				(= cycles 5)
			)
			(4
				(soundFx number: 350 play:)
				(doors setCycle: BegLoop doors)
				(ego
					setMotion: MoveTo (- (ego x?) 10) (+ (ego y?) 5) self
				)
			)
			(5
				(NormalEgo 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sPushButton of Script
	(properties)
	
	(method (changeState newState &tmp [str 100])
		(switch (= state newState)
			(0
				(ego
					egoSpeed:
					view: 808
					loop: 1
					cel: 0
					setPri: (+ (ego priority?) 1)
					setCycle: EndLoop self
				)
			)
			(1
				(soundFx number: 350 play:)
				(if modelessDialog (modelessDialog dispose:))
				(Print
					(Format @str 350 11 (if register {open} else {close}))
					#dispose
					#time 5
					#at -1 20
				)
				(if register
					(curRoom obstacles: openObstacles)
				else
					(curRoom obstacles: closeObstacles)
				)
				(doors setCycle: (if (doors cel?) BegLoop else EndLoop) doors)
				(ego setCycle: BegLoop self)
			)
			(2
				(NormalEgo 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sNoRide of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(Print 350 12)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance aNumber of Rm350ElevatorNumbers
	(properties)
)

(instance disposeNumbers of Code
	(properties)
	
	(method (doit param1)
		(if (param1 isKindOf: Rm350ElevatorNumbers)
			(param1 dispose:)
		)
	)
)

(instance faith of Person
	(properties
		x 198
		y 166
		description {the security guard}
		sightAngle 40
		approachX 226
		approachY 167
		view 352
		loop 3
		signal ignrAct
		xStep 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook (curRoom newRoom: rmFaithCloseup))
			(else  (Print 350 13))
		)
	)
)

(instance elevator of Actor
	(properties
		x 156
		y 137
		description {the elevator}
		sightAngle 40
		approachY 137
		yStep 12
		view 332
		priority 10
		signal (| ignrAct ignrHrz fixPriOn)
	)
	
	(method (init)
		(self approachX: 155)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(ego
					setHeading: (GetAngle (ego x?) (ego y?) (self x?) (self y?)) self
				)
			)
			(verbDo
				(curRoom setScript: sElevatorScript)
			)
			(verbTalk (curRoom doVerb: verbTalk))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(if (and (== (ego x?) 156) (== (ego y?) 137))
			(Print 350 14)
		else
			(Print 350 15)
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
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook (curRoom doVerb: verbLook))
			(verbTalk (curRoom doVerb: verbTalk))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance doors of Prop
	(properties
		x 267
		y 154
		description {the doors}
		sightAngle 40
		approachX 267
		approachY 154
		view 350
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (not (== (doors cel?) 5))
					(Print 350 16)
				else
					(Print 350 17)
				)
			)
			(verbDo
				(if (not (== (doors cel?) 5))
					(Print 350 16)
				else
					(HandsOff)
					(curRoom setScript: sToPenthouse)
				)
			)
			(verbTalk (curRoom doVerb: verbTalk))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(self stopUpd:)
	)
)

(instance deskF of Feature
	(properties
		x 203
		y 168
		nsTop 138
		nsLeft 193
		nsBottom 167
		nsRight 214
		description {the security desk}
		sightAngle 40
		approachX 226
		approachY 167
		lookStr {A beautiful security guard sits at the desk. A pair of large pistols are in a holster around her slim waist. She looks friendly, but quick on the trigger.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(cond 
					((not (Btst fFaithGone)) (Print 350 18))
					((not (doors cel?)) (HandsOff) (curRoom setScript: sPushButton 0 1))
					(else (HandsOff) (curRoom setScript: sPushButton 0 0))
				)
			)
			(verbTalk (curRoom doVerb: verbTalk))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance door1 of Feature
	(properties
		x 15
		y 116
		nsTop 97
		nsLeft 3
		nsBottom 138
		nsRight 27
		description {the door}
		lookStr {It's a door just like all the others.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk (Print 350 19))
			(verbDo (Print 350 20))
			(verbUse (Print 350 21))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance door2 of Feature
	(properties
		x 50
		y 116
		nsTop 98
		nsLeft 40
		nsBottom 134
		nsRight 60
		description {the door}
	)
	
	(method (doVerb theVerb theItem)
		(door1 doVerb: theVerb theItem)
	)
)

(instance door3 of Feature
	(properties
		x 102
		y 112
		nsTop 94
		nsLeft 90
		nsBottom 130
		nsRight 115
		description {the door}
	)
	
	(method (doVerb theVerb theItem)
		(door1 doVerb: theVerb theItem)
	)
)

(instance door4 of Feature
	(properties
		x 189
		y 120
		nsTop 95
		nsLeft 175
		nsBottom 126
		nsRight 203
		description {the door}
	)
	
	(method (doVerb theVerb theItem)
		(door1 doVerb: theVerb theItem)
	)
)

(instance plants of Feature
	(properties
		x 159
		y 143
		nsTop 130
		nsLeft 111
		nsBottom 156
		nsRight 207
		description {the plants}
		sightAngle 40
		lookStr {They almost look real, don't they?}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk
				(curRoom doVerb: theVerb theItem)
			)
			(verbTaste (Print 350 22))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance sculpture of Feature
	(properties
		x 155
		y 155
		z 120
		nsTop 4
		nsLeft 110
		nsBottom 67
		nsRight 201
		description {the sculpture}
		sightAngle 40
		lookStr {Atop the elevator shaft, a modern woman runs to meet her alien lover.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk
				(curRoom doVerb: theVerb theItem)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance employeeExit of Feature
	(properties
		x 10
		y 166
		nsTop 144
		nsBottom 189
		nsRight 21
		description {the employee exit}
		sightAngle 40
		lookStr {Evidently, this exit is for employees only. You'd better use the elevator.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (self doVerb: 2))
			(verbTalk
				(curRoom doVerb: theVerb theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance closeObstacles of List
	(properties)
)

(instance openObstacles of List
	(properties)
)

(instance middleObstacle of Polygon
	(properties
		type PBarredAccess
	)
)

(instance closeObstacle of Polygon
	(properties
		type PBarredAccess
	)
)

(instance openObstacle of Polygon
	(properties
		type PBarredAccess
	)
)
