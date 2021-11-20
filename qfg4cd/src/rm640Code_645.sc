;;; Sierra Script 1.0 - (do not remove this comment)
(script# 645)
(include sci.sh)
(use Main)
(use TellerIcon)
(use PolyPath)
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
	local2
	local3
)
(instance rm640Code of Code
	(properties)
	
	(method (init)
		(if (< (ego x?) 95) (= local0 1))
		(switch curRoomNum
			(622
				(if local0
					(= local2 42)
					(= local3 116)
				else
					(= local2 247)
					(= local3 151)
				)
				(pLeftDoor
					approachVerbs: 4 32
					ignoreActors:
					setPri: 64
					approachX: 46
					approachY: 115
					init:
				)
				(pRightDoor
					approachVerbs: 4 32
					ignoreActors:
					approachX: 251
					approachY: 153
					init:
				)
				(rightDoorTeller init: pRightDoor 640 2 155)
				(leftDoorTeller init: pLeftDoor 640 2 155)
				(vLeftDoor approachVerbs: 4 32 ignoreActors: init:)
				(vRightDoor approachVerbs: 4 32 ignoreActors: init:)
				(curRoom setScript: sEnter)
			)
			(623
				(if local0
					(= local2 85)
					(= local3 91)
				else
					(= local2 298)
					(= local3 140)
				)
				(pLeftDoor
					view: 687
					setLoop: 0 1
					setCel: 0
					posn: 57 10
					approachX: 88
					approachY: 96
					approachVerbs: 4 32
					ignoreActors:
					setPri: 97
					init:
				)
				(pRightDoor
					view: 695
					setLoop: 2 1
					setCel: 0
					posn: 261 52
					approachX: 310
					approachY: 140
					approachVerbs: 4 32
					ignoreActors:
					init:
				)
				(rightDoorTeller init: pRightDoor 640 2 155)
				(leftDoorTeller init: pLeftDoor 640 2 155)
				(vLeftDoor
					view: 695
					setLoop: 0 1
					setCel: 0
					posn: 261 50
					approachVerbs: 4 32
					ignoreActors:
					init:
				)
				(vRightDoor
					view: 687
					setLoop: 1 1
					setCel: 0
					posn: 57 7
					approachX: 310
					approachY: 123
					approachVerbs: 4 32
					ignoreActors:
					init:
				)
				(curRoom setScript: sEnter)
			)
		)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1
				(doorSound play:)
				(if local0
					(pLeftDoor setCycle: End self)
				else
					(pRightDoor setCycle: End self)
				)
			)
			(2
				(pLeftDoor setPri: 68)
				(doorSound dispose:)
				(ego
					setPri: 86
					show:
					setMotion: MoveTo local2 local3 self
				)
			)
			(3
				(doorCloseSound play:)
				(ego setPri: -1)
				(if local0
					(pLeftDoor setCycle: Beg self)
				else
					(pRightDoor setCycle: Beg self)
				)
			)
			(4
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
					(622
						(if (> (ego x?) 100)
							(ego setLoop: 0 1)
						else
							(ego setLoop: 1 1)
						)
					)
					(623 (ego setLoop: 1 1))
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
						(622
							(if (Btst 219) (self cue:))
						)
						(623
							(if (Btst 213) (self cue:))
						)
						(else 
							(squeakSound play:)
							(messager say: 1 4 3 1 0 640)
						)
					)
					(pLeftDoor setCycle: End self)
				else
					(= local0 0)
					(switch curRoomNum
						(622
							(if (Btst 217) (self cue:))
						)
						(623
							(if (Btst 225) (self cue:))
						)
						(else 
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
					)
					(pRightDoor setCycle: End self)
				)
			)
			(1
				(squeakSound stop: dispose:)
				(doorSound stop: dispose:)
				(doorCloseSound stop: dispose:)
				(cond 
					(local0
						(ego
							setMotion: MoveTo (- (ego x?) 2) (- (ego y?) 2) self
						)
					)
					((== curRoomNum 623) (ego setMotion: MoveTo (ego x?) (- (ego y?) 2) self))
					(else
						(ego
							setMotion: MoveTo (+ (ego x?) 2) (- (ego y?) 2) self
						)
					)
				)
			)
			(2
				(if local0
					(if (== curRoomNum 622)
						(curRoom newRoom: 640)
					else
						(curRoom newRoom: (curRoom west?))
					)
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
				(doorSound play:)
				(if local0
					(switch curRoomNum
						(622
							(if (Btst 219) (self cue:))
						)
						(623
							(if (Btst 213) (self cue:))
						)
						(else 
							(squeakSound play:)
							(messager say: 1 4 3 1 0 640)
						)
					)
					(pLeftDoor setCycle: End self)
				else
					(switch curRoomNum
						(622
							(if (Btst 217) (self cue:))
						)
						(623
							(if (Btst 225) (self cue:))
						)
						(else 
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
					)
					(pRightDoor setCycle: End self)
				)
			)
			(1
				(squeakSound stop: dispose:)
				(doorSound stop: dispose:)
				(doorCloseSound stop: dispose:)
				(if local0
					(ego
						setMotion: PolyPath (pLeftDoor approachX?) (pLeftDoor approachY?) self
					)
				else
					(ego
						setMotion: PolyPath (pRightDoor approachX?) (pRightDoor approachY?) self
					)
				)
			)
			(2
				(if local0
					(if (== curRoomNum 622)
						(curRoom newRoom: 640)
					else
						(curRoom newRoom: (curRoom west?))
					)
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
				(if (< (ego x?) 160) (= local0 1))
				(switch curRoomNum
					(622
						(cond 
							((and (Btst 217) local0) (self cue:))
							((Btst 219) (self cue:))
							(else
								(messager say: 1 4 3 1 0 640)
								(squeakSound play:)
								(self cue:)
							)
						)
					)
					(623
						(cond 
							((and (Btst 213) local0) (self cue:))
							((Btst 225) (self cue:))
							(else
								(messager say: 1 4 3 1 0 640)
								(squeakSound play:)
								(self cue:)
							)
						)
					)
					(else 
						(messager say: 1 4 3 1 0 640)
						(squeakSound play:)
						(self cue:)
					)
				)
			)
			(2
				(doorSound play:)
				(if (< (ego x?) 160)
					(= local0 1)
					(pLeftDoor setCycle: End self)
				else
					(= local0 0)
					(pRightDoor setCycle: End self)
				)
			)
			(3
				(squeakSound stop: dispose:)
				(doorSound stop: dispose:)
				(doorCloseSound stop: dispose:)
				(= cycles 5)
			)
			(4
				(if (< (ego x?) 160)
					(if (== curRoomNum 622)
						(curRoom newRoom: 640)
					else
						(curRoom newRoom: (curRoom west?))
					)
				else
					(curRoom newRoom: (curRoom east?))
				)
			)
		)
	)
)

(instance pLeftDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 31
		approachY 119
		y 29
		z 4
		priority 64
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
			(else 
				(vLeftDoor doVerb: theVerb)
			)
		)
	)
)

(instance pRightDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 269
		approachY 152
		x 264
		y 61
		priority 86
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
			(else 
				(vRightDoor doVerb: theVerb)
			)
		)
	)
)

(instance vRightDoor of View
	(properties
		noun 1
		modNum 640
		approachX 269
		approachY 152
		x 265
		y 59
		priority 64
		fixPriority 1
		view 644
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(switch curRoomNum
					(622 (Bset 217))
					(623 (Bset 225))
				)
				(messager say: 1 32 0 1 0 640)
			)
			(42
				(curRoom setScript: sPickLock)
			)
			(-80
				(= local0 0)
				(curRoom setScript: sCastOpenDoor)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vLeftDoor of View
	(properties
		noun 1
		modNum 640
		approachX 21
		approachY 119
		y 27
		priority 51
		fixPriority 1
		view 644
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(switch curRoomNum
					(622 (Bset 219))
					(623 (Bset 213))
				)
				(messager say: 1 32 0 1 0 640)
			)
			(42
				(curRoom setScript: sPickLock)
			)
			(-80
				(= local0 1)
				(curRoom setScript: sCastOpenDoor)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(else  (super doVerb: theVerb))
		)
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
