;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmElevatorBottom) ;330
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
	rm330 0
)

(class ElevatorNumbers of View
	(properties
		y 180
		description {the button}
		view 333
		loop 1
		cel 0
		priority 15
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(self setLoop: (+ (self loop?) 1))
				(Animate (cast elements?) FALSE)
				(if (== (= currentHotelFloor (self cel?)) 1)
					(HandsOff)
					(self setLoop: (- (self loop?) 1))
					(Animate (cast elements?) FALSE)
					(curRoom setScript: sNoRide)
				else
					(HandsOff)
					(curRoom setScript: sToElevator)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance rm330 of LLRoom
	(properties
		picture rmElevatorBottom
		north rmElevators
		east rmLounge
		south rmInsideCasino
		west 310
	)
	
	(method (init)
		(Load SOUND 310 340)
		(LoadMany VIEW 331 332 808 333)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0 319 0 319 112 150 112 135 102 117 99 82 97
						46 99 15 108 18 126 36 126 36 139 0 139
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 250 189 250 184 271 176 319 176 319 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						83 145 117 135 141 135 152 147 161 147 164 140 164 135
						189 135 222 143 222 152 187 162 175 162 175 169 150 169
						136 163 100 162 83 150
					yourself:
				)
		)
		(soundFx loop: 1 vol: 127 flags: mNOPAUSE)
		(switch prevRoomNum
			(rmInsideCasino 0)
			(rmLounge
				(theMusic number: 310 loop: -1 flags: mNOPAUSE play: 90)
				(HandsOff)
				(self setScript: sFromLounge)
			)
			(rmElevators
				(HandsOff)
				(curRoom setScript: sFromElevator)
			)
			(else  (ego posn: 160 170))
		)
		(ego init:)
		(super init:)
		(elevator
			cycleSpeed: (+ 1 howFast)
			moveSpeed: (+ 1 howFast)
			init:
			approachVerbs: verbDo verbUse verbZipper verbTaste
		)
		(if (!= prevRoomNum rmElevators) (elevator stopUpd:))
		(ashTray init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(thePhone init: approachVerbs: verbDo verbUse verbZipper verbTaste verbLook)
		(plants init:)
		(elevatorShaft init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		(theWindow init:)
		(cabaretSign init:)
		(if (== ((inventory at: iDiscoPass) owner?) 330)
			(discoPass init: approachVerbs: verbDo verbUse verbZipper verbTaste)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and (ego mover?) (cast contains: numberPad))
			(numberPad dispose:)
			(cast eachElementDo: #perform disposeNumbers)
			(if (== (curRoom script?) sElevatorScript)
				(curRoom setScript: 0)
			)
		)
		(cond 
			(script)
			((> (ego x?) 278) (HandsOff) (self setScript: sGoLounge))
			((IsObjectOnControl ego cCYAN) (theMusic fade: 127 5 5 0))
			((IsObjectOnControl ego cRED) (theMusic fade: 90 5 5 0))
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (IsObjectOnControl ego cCYAN)
					(Print 330 0)
				else
					(Print 330 1)
				)
			)
			(verbTalk
				(Print 330 2)
				(Print 330 3 #at -1 140)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance sGoLounge of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 288 (ego y?) self)
			)
			(1
				(theMusic fade:)
				(curRoom newRoom: (curRoom east?))
			)
		)
	)
)

(instance sFromLounge of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 288 150 setMotion: MoveTo 260 150 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance sFromElevator of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 16)
				(ego z: 1000 hide: normal: FALSE)
				(elevator
					view: 331
					y: -10
					setCycle: Reverse
					setMotion: MoveTo 156 137 self
				)
			)
			(1
				(elevator setCycle: EndLoop self)
			)
			(2
				(soundFx number: 340 play:)
				(elevator cel: 0 view: 332 stopUpd:)
				(ego x: 156 y: 137 z: 0 show:)
				(NormalEgo loopS)
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
				(ego z: 1000 normal: 0 hide:)
				(elevator
					view: 331
					cel: 0
					setCycle: Forward
					setMotion: MoveTo (elevator x?) -20 self
				)
			)
			(3 (curRoom newRoom: rmElevators))
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

(instance sGetPass of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					egoSpeed:
					view: 808
					loop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(SolvePuzzle fGetDiscoPass 1)
				(discoPass z: 1000 dispose:)
				(ego get: 10 setCycle: BegLoop self)
			)
			(2
				(NormalEgo loopW)
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
				(Print 330 4)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance aNumber of ElevatorNumbers
	(properties
		description {the button}
	)
)

(instance disposeNumbers of Code
	(properties)
	
	(method (doit obj)
		(if (obj isKindOf: ElevatorNumbers)
			(obj dispose:)
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

(instance elevator of Actor
	(properties
		x 156
		y 137
		description {the elevator}
		sightAngle 40
		approachY 137
		lookStr {That elevator must be made by the DNA Company.}
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
			(verbDo
				(curRoom setScript: sElevatorScript)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance discoPass of View
	(properties
		x 159
		y 168
		z 20
		description {the card}
		sightAngle 40
		approachX 175
		approachY 162
		lookStr {Why, it appears some sort of card is lying in the ashtray.}
		view 332
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(HandsOff)
				(curRoom setScript: sGetPass)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance ashTray of Feature
	(properties
		x 157
		y 153
		nsTop 143
		nsLeft 149
		nsBottom 164
		nsRight 165
		description {the ash tray}
		sightAngle 40
		approachX 175
		approachY 162
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (== ((inventory at: iDiscoPass) owner?) 330)
					(Print 330 5)
				else
					(Print 330 6)
				)
			)
			(verbDo
				(if (CheckItemOwner iDiscoPass)
					(HandsOff)
					(curRoom setScript: sGetPass)
				else
					(Print 330 7)
				)
			)
			(else 
				(if (== ((inventory at: iDiscoPass) owner?) 330)
					(Print 330 5)
				else
					(Print 330 8)
				)
			)
		)
	)
)

(instance thePhone of Feature
	(properties
		x 35
		y 103
		z 23
		nsTop 67
		nsLeft 27
		nsBottom 93
		nsRight 43
		description {the telephone}
		sightAngle 40
		approachX 54
		approachY 101
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook (Print 330 9))
			(verbTalk (Print 330 10))
			(verbDo (Print 330 11))
			(verbTaste (Print 330 12))
			(verbUse (self doVerb: verbDo))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance plants of Feature
	(properties
		x 151
		y 140
		nsTop 123
		nsLeft 103
		nsBottom 158
		nsRight 200
		description {the plants}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (IsObjectOnControl ego cCYAN)
					(Print 330 13)
					(Print 330 14)
				else
					(Print 330 15)
				)
			)
			(verbTaste (Print 330 16))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance elevatorShaft of Feature
	(properties
		x 154
		y 66
		nsLeft 121
		nsBottom 132
		nsRight 187
		description {the elevator shaft}
		sightAngle 40
		approachX 156
		approachY 137
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

(instance theWindow of Feature
	(properties
		x 93
		y 96
		z 47
		nsTop 21
		nsLeft 67
		nsBottom 78
		nsRight 120
		description {the window}
		sightAngle 40
		lookStr {Is that a window or an illusory painting? With art like this, who can tell?}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 330 17)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance cabaretSign of Feature
	(properties
		x 226
		y 110
		z 60
		nsTop 37
		nsLeft 188
		nsBottom 63
		nsRight 265
		description {the sign}
		sightAngle 40
		lookStr {Oh, no! Look! Someone has painted the word "Cabaret" right there on the wall.}
	)
)
