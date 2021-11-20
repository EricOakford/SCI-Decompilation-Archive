;;; Sierra Script 1.0 - (do not remove this comment)
(script# 642)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm642 0
)

(local
	local0
	local1
	local2
	local3
)
(instance rm642 of GloryRm
	(properties
		modNum 640
		picture 640
		north 624
		east 626
	)
	
	(method (init)
		(switch prevRoomNum
			(622
				(= local0 55)
				(= local1 135)
				(ego posn: 4 106)
			)
			(624
				(= local0 195)
				(= local1 133)
				(ego posn: 160 105)
			)
			(else 
				(= local0 225)
				(= local1 175)
				(ego hide: posn: 296 159)
			)
		)
		(ego init: setScaler: Scaler 100 100 189 0 normalize:)
		(super init: &rest)
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
						189
						303
						189
						258
						139
						230
						135
						220
						126
						171
						123
						143
						122
						88
						119
						68
						125
						42
						122
						27
						132
						13
						131
						5
						155
						6
						189
						0
						189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 52 142 196 142 190 165 136 183 98 186 52 184
					yourself:
				)
		)
		(pLeftDoor init: approachVerbs: 4 32)
		(leftDoorTeller init: pLeftDoor 640 2 155)
		(pBackDoor init: approachVerbs: 4 32)
		(backDoorTeller init: pBackDoor 640 2 155)
		(pRightDoor init: approachVerbs: 4 32)
		(rightDoorTeller init: pRightDoor 640 2 155)
		(pLeftSconce ignoreActors: setCycle: Fwd init:)
		(pRightSconce ignoreActors: setCycle: Fwd init:)
		(vLeftColumn ignoreActors: init:)
		(vRightColumn ignoreActors: init:)
		(vTheRug ignoreActors: init:)
		(vTheTable ignoreActors: approachVerbs: 4 init:)
		(vTheDoor ignoreActors: approachVerbs: 4 32 init:)
		(vLeftDoor ignoreActors: approachVerbs: 4 32 init:)
		(vRightDoor ignoreActors: approachVerbs: 4 32 init:)
		(fChair1 init: approachVerbs: 4)
		(fChair2 init: approachVerbs: 4)
		(fChair3 init: approachVerbs: 4)
		(curRoom setScript: sComeOnIn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 0 1 0 0 0 642)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance sComeOnIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(doorSound play:)
				(switch prevRoomNum
					(624
						(pBackDoor setCycle: End self)
					)
					(622
						(pLeftDoor setCycle: End self)
					)
					(else 
						(pRightDoor setCycle: End self)
					)
				)
			)
			(1
				(vTheDoor setPri: 75)
				(pBackDoor setPri: 86)
				(pLeftDoor setPri: 75)
				(pRightDoor setPri: 75)
				(ego setMotion: PolyPath local0 local1 self)
			)
			(2
				(doorCloseSound play:)
				(switch prevRoomNum
					(624
						(pBackDoor setCycle: Beg self)
					)
					(622
						(pLeftDoor setCycle: Beg self)
					)
					(else 
						(pRightDoor setCycle: Beg self)
					)
				)
			)
			(3
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
				(if (< (ego x?) 160)
					(if (< (ego y?) 124)
						(= local2 1)
						(if (not (Btst 216))
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
						(pBackDoor setCycle: End self)
					else
						(= local3 1)
						(if (not (Btst 217))
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
						(pLeftDoor setCycle: End self)
					)
				else
					(= local3 0)
					(if (not (Btst 218))
						(messager say: 1 4 3 1 0 640)
						(squeakSound play:)
					)
					(pRightDoor setCycle: End self)
				)
			)
			(1
				(squeakSound dispose:)
				(doorSound dispose:)
				(cond 
					(local2 (ego setMotion: MoveTo (ego x?) (- (ego y?) 2) self))
					(local3
						(ego
							setMotion: MoveTo (- (ego x?) 2) (- (ego y?) 2) self
						)
					)
					(else
						(ego
							setMotion: MoveTo (+ (ego x?) 2) (- (ego y?) 2) self
						)
					)
				)
			)
			(2
				(cond 
					(local2 (curRoom newRoom: 624))
					(local3 (curRoom newRoom: 622))
					(else (curRoom newRoom: (curRoom east?)))
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
				(cond 
					(local3
						(if (not (Btst 217))
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
						(pLeftDoor setCycle: End self)
					)
					(local2
						(if (not (Btst 216))
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
						(pBackDoor setCycle: End self)
					)
					(else
						(if (not (Btst 218))
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
						(pRightDoor setCycle: End self)
					)
				)
			)
			(1
				(squeakSound dispose:)
				(doorSound dispose:)
				(cond 
					(local2
						(ego
							setMotion: PolyPath (pBackDoor approachX?) (pBackDoor approachY?) self
						)
					)
					(local3
						(ego
							setMotion: PolyPath (pLeftDoor approachX?) (pLeftDoor approachY?) self
						)
					)
					(else
						(ego
							setMotion: PolyPath (pRightDoor approachX?) (pRightDoor approachY?) self
						)
					)
				)
			)
			(2
				(cond 
					(local2 (curRoom newRoom: 624))
					(local3 (curRoom newRoom: 622))
					(else (curRoom newRoom: (curRoom east?)))
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
					((Btst 216) (self cue:))
					((Btst 217) (self cue:))
					((Btst 218) (self cue:))
					(else
						(squeakSound play:)
						(messager say: 1 4 3 1 0 640)
						(self cue:)
					)
				)
			)
			(2
				(doorSound play:)
				(cond 
					((< (ego x?) 100) (= local3 1) (pLeftDoor setCycle: End self))
					((< (ego x?) 190) (= local2 1) (pBackDoor setCycle: End self))
					(else (= local3 0) (pRightDoor setCycle: End self))
				)
			)
			(3
				(squeakSound dispose:)
				(doorSound dispose:)
				(= cycles 5)
			)
			(4
				(cond 
					((< (ego x?) 100) (curRoom newRoom: 622))
					((< (ego x?) 190) (curRoom newRoom: 624))
					(else (curRoom newRoom: (curRoom east?)))
				)
			)
		)
	)
)

(instance pLeftDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 10
		approachY 148
		x -1
		y 31
		priority 141
		fixPriority 1
		view 644
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local3 1)
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

(instance pBackDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 131
		approachY 122
		x 128
		y 40
		priority 163
		fixPriority 1
		view 687
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local2 1)
				(curRoom setScript: sCastOpenDoor)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(else 
				(vTheDoor doVerb: theVerb)
			)
		)
	)
)

(instance pRightDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 272
		approachY 165
		x 270
		y 70
		priority 196
		fixPriority 1
		view 644
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local3 0)
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

(instance pLeftSconce of Prop
	(properties
		noun 21
		modNum 640
		x 51
		y 58
		priority 97
		fixPriority 1
		view 699
		loop 7
		cel 4
		detailLevel 2
	)
)

(instance pRightSconce of Prop
	(properties
		noun 21
		modNum 640
		x 243
		y 64
		view 699
		loop 7
		detailLevel 2
	)
)

(instance vLeftColumn of View
	(properties
		modNum 640
		x 41
		y -1
		priority 86
		fixPriority 1
		view 685
	)
)

(instance vRightColumn of View
	(properties
		modNum 640
		x 228
		y -1
		view 685
		loop 2
	)
)

(instance vTheRug of View
	(properties
		noun 33
		modNum 640
		x 8
		y 123
		view 692
	)
)

(instance vTheTable of View
	(properties
		noun 28
		modNum 640
		approachX 56
		approachY 113
		x 56
		y 109
		priority 163
		fixPriority 1
		view 697
	)
)

(instance vTheDoor of View
	(properties
		noun 1
		modNum 640
		approachX 131
		approachY 122
		x 126
		y 38
		priority 152
		fixPriority 1
		view 687
		loop 1
		signal $1001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(Bset 216)
				(messager say: 1 32 0 1 0 640)
			)
			(42
				(curRoom setScript: sPickLock)
			)
			(-80
				(= local2 1)
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
		approachX 10
		approachY 148
		y 29
		view 644
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(Bset 217)
				(messager say: 1 32 0 1 0 640)
			)
			(42
				(curRoom setScript: sPickLock)
			)
			(-80
				(= local3 1)
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

(instance vRightDoor of View
	(properties
		noun 1
		modNum 640
		approachX 272
		approachY 165
		x 271
		y 65
		view 644
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(Bset 218)
				(messager say: 1 32 0 1 0 640)
			)
			(42
				(curRoom setScript: sPickLock)
			)
			(-80
				(= local3 0)
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

(instance fChair1 of Feature
	(properties
		noun 27
		modNum 640
		nsLeft 100
		nsTop 99
		nsRight 125
		nsBottom 133
		sightAngle 180
		x 100
		y 99
	)
)

(instance fChair2 of Feature
	(properties
		noun 27
		modNum 640
		nsLeft 137
		nsTop 114
		nsRight 174
		nsBottom 173
		sightAngle 180
		x 137
		y 173
	)
)

(instance fChair3 of Feature
	(properties
		noun 27
		modNum 640
		nsLeft 59
		nsTop 130
		nsRight 154
		nsBottom 172
		sightAngle 180
		x 63
		y 172
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

(instance backDoorTeller of Teller
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
			(27
				(self clean:)
				(curRoom setScript: sPeepingTom)
			)
			(7
				(self clean:)
				(curRoom setScript: sPickLock)
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
