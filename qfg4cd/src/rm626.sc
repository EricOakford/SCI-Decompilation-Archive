;;; Sierra Script 1.0 - (do not remove this comment)
(script# 626)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm626 0
)

(local
	local0
)
(instance rm626 of GloryRm
	(properties
		modNum 640
		picture 640
		horizon 66
		north 661
	)
	
	(method (init)
		(switch prevRoomNum
			(642 (ego posn: -10 155))
			(661 (ego posn: 151 94))
			(else  (ego posn: 151 94))
		)
		(ego init: setScaler: Scaler 100 42 115 58 normalize:)
		(if (< (ego x?) 10) (= local0 1))
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						59
						116
						4
						150
						4
						189
						-300
						189
						-300
						0
						619
						0
						619
						189
						285
						189
						237
						140
						215
						138
						185
						118
						158
						59
						143
						59
						97
						121
					yourself:
				)
		)
		(pDoor init: approachVerbs: 4 32)
		(doorTeller init: pDoor 640 2 155)
		((pFire new:) posn: 254 76 setCel: 6 setCycle: Fwd init:)
		((pFire new:) posn: 280 84 setCel: 0 setCycle: Fwd init:)
		(vRightDoor ignoreActors: approachVerbs: 4 32 init:)
		(vEndLessHall ignoreActors: init:)
		(vColumn1 ignoreActors: init:)
		(vColumn2 ignoreActors: init:)
		(vBench ignoreActors: init:)
		(curRoom setScript: sEnter)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 0 1 0 0 0 626)
		else
			(super doVerb: theVerb)
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
				(if local0
					(doorSound play:)
					(pDoor setCycle: End self)
				else
					(self cue:)
				)
			)
			(2
				(if local0
					(ego setMotion: MoveTo 49 155 self)
				else
					(ego setMotion: PolyPath 146 141 self)
				)
			)
			(3
				(if local0
					(doorCloseSound play:)
					(pDoor setCycle: Beg self)
				else
					(self cue:)
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
				(ego view: 4 setCel: 0 setLoop: 0 1 setCycle: End self)
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
				(if (not (Btst 218))
					(messager say: 1 4 3 1 0 640)
					(squeakSound play:)
				)
				(pDoor setCycle: End self)
			)
			(1
				(squeakSound dispose:)
				(doorSound dispose:)
				(ego
					setMotion: MoveTo (- (ego x?) 10) (- (ego y?) 5) self
				)
			)
			(2 (curRoom newRoom: 642))
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
				(if (not (Btst 218))
					(messager say: 1 4 3 1 0 640)
					(squeakSound play:)
				)
				(pDoor setCycle: End self)
			)
			(1
				(squeakSound dispose:)
				(doorSound dispose:)
				(ego
					setMotion: PolyPath (pDoor approachX?) (pDoor approachY?) self
				)
			)
			(2 (curRoom newRoom: 642))
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
				(if (Btst 218)
					(self cue:)
				else
					(squeakSound play:)
					(messager say: 1 4 3 1 0 640)
					(self cue:)
				)
			)
			(2
				(doorSound play:)
				(pDoor setCycle: End self)
			)
			(3
				(squeakSound dispose:)
				(doorSound dispose:)
				(= cycles 5)
			)
			(4 (curRoom newRoom: 642))
		)
	)
)

(instance sSitAndRest of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 261 163 self)
			)
			(1 (Face ego 10 170 self))
			(2
				(ego
					view: 54
					setLoop: 4 1
					setCel: 0
					posn: 267 162
					setCycle: End self
				)
			)
			(3
				(= register Clock)
				((ScriptID 7 2) init:)
				(= register (> Clock register))
				(DisposeScript 7)
				(DisposeScript 34)
				(if register (= seconds 3) else (= cycles 1))
			)
			(4 (ego setCycle: Beg self))
			(5
				(ego posn: 261 162 normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance pDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 10
		approachY 170
		x -8
		y 37
		priority 75
		fixPriority 1
		view 644
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(curRoom setScript: sCastOpenDoor)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(32
				(Bset 224)
				(messager say: 1 32 0 0 0 640)
			)
			(else 
				(vRightDoor doVerb: theVerb)
			)
		)
	)
)

(instance pFire of Prop
	(properties
		noun 21
		modNum 640
		priority 75
		fixPriority 1
		view 699
		loop 8
		signal $4001
		detailLevel 2
	)
)

(instance vEndLessHall of View
	(properties
		modNum 640
		x 77
		y 3
		view 678
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)

(instance vRightDoor of View
	(properties
		noun 1
		modNum 640
		approachX 10
		approachY 170
		x -10
		y 35
		view 644
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(Bset 218)
				(messager say: 1 32 0 0 0 640)
			)
			(-80
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

(instance vColumn1 of View
	(properties
		modNum 640
		x 203
		priority 64
		fixPriority 1
		view 689
		loop 4
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)

(instance vColumn2 of View
	(properties
		modNum 640
		x 319
		view 689
		loop 1
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)

(instance vBench of View
	(properties
		noun 14
		modNum 640
		x 250
		y 129
		view 699
		loop 4
		cel 1
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: sSitAndRest)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance doorTeller of Teller
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

(instance doorCloseSound of Sound
	(properties
		number 973
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
