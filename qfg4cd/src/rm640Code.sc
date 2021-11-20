;;; Sierra Script 1.0 - (do not remove this comment)
(script# 634)
(include sci.sh)
(use Main)
(use TellerIcon)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm640Code 0
)

(local
	local0
	local1
)
(instance rm640Code of Code
	(properties)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						0
						319
						0
						319
						69
						319
						189
						303
						189
						266
						144
						259
						136
						46
						122
						5
						146
						5
						189
						0
						189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 85 131 224 139 220 162 135 173 89 164
					yourself:
				)
		)
		(if (< (ego x?) 10) (= local0 1))
		(pRtDoor approachVerbs: 4 32 init:)
		(rightDoorTeller init: pRtDoor 640 2 155)
		(pLtDoor approachVerbs: 4 32 init:)
		(leftDoorTeller init: pLtDoor 640 2 155)
		(vRightDoor approachVerbs: 4 32 ignoreActors: init:)
		(vLeftDoor approachVerbs: 4 32 ignoreActors: init:)
		(if (not (== curRoomNum 631))
			(fSteps init: approachVerbs: 4)
		)
		(curRoom setScript: sEnter)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 24)
			)
			(1
				(doorSound play:)
				(if local0
					(pLtDoor setCycle: End self)
				else
					(pRtDoor setCycle: End self)
				)
			)
			(2
				(pLtDoor setPri: 86)
				(pRtDoor setPri: 86)
				(if local0
					(ego setMotion: MoveTo 80 155 self)
				else
					(ego setMotion: PolyPath 282 168 self)
				)
			)
			(3
				(if local0
					(pLtDoor setPri: 86 setCycle: Beg self)
				else
					(pRtDoor setPri: 86 setCycle: Beg self)
				)
			)
			(4
				(doorCloseSound play:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sPeepingTom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch curRoomNum
					(631
						(ego setLoop: (if (< (ego x?) 60) 1 else 0) 1)
					)
					(else  (ego setLoop: 0 1))
				)
				(ego view: 4 setCel: 0 setCycle: End self)
			)
			(1
				(messager say: 2 155 27 1 self 640)
			)
			(2 (= seconds 3))
			(3 (ego setCycle: Beg self))
			(4
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenTheDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(doorSound play:)
				(if (< (ego x?) 160)
					(= local0 1)
					(switch curRoomNum
						(631
							(if (Btst 209)
								(self cue:)
							else
								(messager say: 1 4 3 1 0 640)
								(squeakSound play:)
							)
						)
						(else 
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
					)
					(pLtDoor setCycle: End self)
				else
					(= local0 0)
					(switch curRoomNum
						(631
							(if (Btst 210)
								(self cue:)
							else
								(messager say: 1 4 3 1 0 640)
								(squeakSound play:)
							)
						)
						(else 
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
					)
					(pRtDoor setCycle: End self)
				)
			)
			(1
				(doorCloseSound stop: dispose:)
				(squeakSound stop: dispose:)
				(doorSound stop: dispose:)
				(if local0
					(ego
						setMotion: MoveTo (- (ego x?) 2) (- (ego y?) 2) self
					)
				else
					(ego
						setMotion: MoveTo (+ (ego x?) 2) (- (ego y?) 2) self
					)
				)
			)
			(2
				(if local0
					(curRoom newRoom: 662)
				else
					(curRoom newRoom: (curRoom east?))
				)
			)
		)
	)
)

(instance sCastOpenDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego trySkill: 20)
				(doorSound play:)
				(if local0
					(switch curRoomNum
						(631
							(if (Btst 209)
								(self cue:)
							else
								(messager say: 1 4 3 1 0 640)
								(squeakSound play:)
							)
						)
						(else 
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
					)
					(pLtDoor setCycle: End self)
				else
					(switch curRoomNum
						(631
							(if (Btst 210)
								(self cue:)
							else
								(messager say: 1 4 3 1 0 640)
								(squeakSound play:)
							)
						)
						(else 
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
					)
					(pRtDoor setCycle: End self)
				)
			)
			(1
				(doorCloseSound stop: dispose:)
				(squeakSound stop: dispose:)
				(doorSound stop: dispose:)
				(if local0
					(ego
						setMotion: PolyPath (pLtDoor approachX?) (pLtDoor approachY?) self
					)
				else
					(ego
						setMotion: PolyPath (pRtDoor approachX?) (pRtDoor approachY?) self
					)
				)
			)
			(2
				(if local0
					(curRoom newRoom: 662)
				else
					(curRoom newRoom: (curRoom east?))
				)
			)
		)
	)
)

(instance sPickLock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego trySkill: 9 castleDoorDifficulty)
				(ego trySkill: 9 castleDoorDifficulty)
				(if (== (ego trySkill: 9 castleDoorDifficulty) 1)
					(theGame handsOff:)
					(messager say: 1 42 1 1 self 640)
				else
					(messager say: 1 42 2 1 0 640)
					(self dispose:)
				)
			)
			(1
				(cond 
					((and (== curRoomNum 631) (Btst 209)) (self cue:))
					((and (== curRoomNum 631) (Btst 210)) (self cue:))
					(else
						(squeakSound play:)
						(messager say: 1 4 3 1 0 640)
						(self cue:)
					)
				)
			)
			(2
				(doorSound play:)
				(if (< (ego x?) 160)
					(= local0 1)
					(pLtDoor setCycle: End self)
				else
					(= local0 0)
					(pRtDoor setCycle: End self)
				)
			)
			(3
				(doorCloseSound stop: dispose:)
				(squeakSound stop: dispose:)
				(doorSound stop: dispose:)
				(= cycles 5)
			)
			(4
				(if (< (ego x?) 160)
					(curRoom newRoom: 662)
				else
					(curRoom newRoom: (curRoom east?))
				)
			)
		)
	)
)

(instance sLeave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(doorCloseSound stop: dispose:)
				(squeakSound stop: dispose:)
				(doorSound stop: dispose:)
				(ego setMotion: PolyPath (ego x?) (- (ego y?) 10) self)
			)
			(1
				(curRoom newRoom: (curRoom east?))
			)
		)
	)
)

(instance pLtDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 39
		approachY 134
		y 32
		priority 207
		fixPriority 1
		view 644
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local0 1)
				(curRoom setScript: sCastOpenDoor)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(32
				(if (== curRoomNum 631) (Bset 209))
				(messager say: 1 32 0 1 0 640)
			)
			(42
				(curRoom setScript: sPickLock)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pRtDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 262
		approachY 151
		x 270
		y 73
		z 10
		priority 207
		fixPriority 1
		view 644
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local0 0)
				(curRoom setScript: sCastOpenDoor)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(32
				(if (== curRoomNum 631) (Bset 210))
				(messager say: 1 32 0 1 0 640)
			)
			(42
				(curRoom setScript: sPickLock)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vRightDoor of View
	(properties
		noun 1
		modNum 640
		approachX 262
		approachY 151
		x 271
		y 65
		view 644
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local0 0)
				(curRoom setScript: sCastOpenDoor)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(32
				(if (== curRoomNum 631) (Bset 210))
				(messager say: 1 32 0 1 0 640)
			)
			(42
				(curRoom setScript: sPickLock)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vLeftDoor of View
	(properties
		noun 1
		modNum 640
		approachX 39
		approachY 134
		y 29
		view 644
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local0 1)
				(curRoom setScript: sCastOpenDoor)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(32
				(if (== curRoomNum 631) (Bset 209))
				(messager say: 1 32 0 1 0 640)
			)
			(42
				(curRoom setScript: sPickLock)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fSteps of Feature
	(properties
		noun 8
		modNum 640
		nsLeft 284
		nsTop 113
		nsRight 319
		nsBottom 134
		sightAngle 180
		x 301
		y 123
	)
)

(instance rightDoorTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(switch iconValue
			(4
				(if (== heroType 2)
					(super sayMessage: 3 6 9 &rest)
				else
					(self clean:)
					(curRoom setScript: sOpenTheDoor)
				)
			)
			(7
				(self clean:)
				(curRoom setScript: sPickLock)
			)
			(27
				(self clean:)
				(curRoom setScript: sPeepingTom)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				13
				(if (== heroType 2) (Btst 242) else 0)
				7
				(if [egoStats 9] (ego has: 24) else 0)
		)
	)
)

(instance leftDoorTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(switch iconValue
			(4
				(if (== heroType 2)
					(super sayMessage: 3 6 9 &rest)
				else
					(self clean:)
					(curRoom setScript: sOpenTheDoor)
				)
			)
			(7
				(self clean:)
				(curRoom setScript: sPickLock)
			)
			(27
				(self clean:)
				(curRoom setScript: sPeepingTom)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				13
				(if (== heroType 2) (Btst 242) else 0)
				7
				(if [egoStats 9] (ego has: 24) else 0)
		)
	)
)

(instance squeakSound of Sound
	(properties
		number 143
	)
)

(instance doorSound of Sound
	(properties
		number 972
	)
)

(instance doorCloseSound of Sound
	(properties
		number 973
	)
)
