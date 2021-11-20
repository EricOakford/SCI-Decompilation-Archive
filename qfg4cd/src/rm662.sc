;;; Sierra Script 1.0 - (do not remove this comment)
(script# 662)
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
	rm662 0
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
(instance rm662 of GloryRm
	(properties
		modNum 640
		picture 640
		east 631
	)
	
	(method (init)
		(switch prevRoomNum
			(631
				(= local0 258)
				(= local1 138)
				(ego posn: 283 135)
			)
			(510
				(theMusic doSongs: 630 631 632)
				(= local2 1)
				(= local0 27)
				(= local1 167)
				(ego setLoop: 0 1 posn: 27 137)
			)
			(else 
				(= local0 96)
				(= local1 117)
				(ego loop: 2 posn: 119 101)
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
						301
						189
						257
						139
						245
						128
						121
						118
						96
						116
						80
						116
						0
						171
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 99 138 230 142 231 162 182 171 149 182 102 178 87 146 86 138
					yourself:
				)
		)
		(pSecretDoor2 init: ignoreActors: 1)
		(pSideCandle init: approachVerbs: 4 ignoreActors: 1)
		((pFlame new:)
			posn: 162 58
			setCel: 2
			setCycle: Fwd
			init:
			approachVerbs: 4
		)
		((pFlame new:)
			posn: 169 57
			setCel: 1
			setCycle: Fwd
			init:
			approachVerbs: 4
		)
		((pFlame new:)
			posn: 175 54
			setCel: 0
			setCycle: Fwd
			init:
			approachVerbs: 4
		)
		((pFlame new:)
			posn: 182 56
			setCel: 2
			setCycle: Fwd
			init:
			approachVerbs: 4
		)
		(pBackDoor approachVerbs: 4 32 init:)
		(leftDoorTeller init: pBackDoor 640 2 155)
		(pRightDoor approachVerbs: 4 32 init:)
		(rightDoorTeller init: vRightDoor 640 2 155)
		(vSecretDoor init: ignoreActors:)
		(vBook init: ignoreActors:)
		(vRightDoor approachVerbs: 4 32 ignoreActors: init:)
		(vCase ignoreActors: init:)
		(vBackDoor approachVerbs: 4 32 ignoreActors: init:)
		(vTheRug ignoreActors: init:)
		(vTheTable ignoreActors: init:)
		(vTheCeiling ignoreActors: init:)
		(vRightCeiling ignoreActors: init:)
		(vTheCandles ignoreActors: init:)
		(fChair1 init: approachVerbs: 4)
		(fChair2 init: approachVerbs: 4)
		(fChair3 init: approachVerbs: 4)
		(fCrest init: approachVerbs: 4)
		(curRoom setScript: sComeOnIn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 0 1 0 0 0 662)
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
				(= ticks 60)
			)
			(1
				(doorSound play:)
				(cond 
					(local2 (pSecretDoor2 setCycle: End self))
					((< (ego x?) 130)
						(if (not (Btst 208))
							(squeakSound play:)
							(messager say: 1 4 3 1 0 640)
						)
						(pBackDoor setCycle: End self)
					)
					(else
						(if (not (Btst 209))
							(squeakSound play:)
							(messager say: 1 4 3 1 0 640)
						)
						(pRightDoor setCycle: End self)
					)
				)
			)
			(2
				(if local2
					(messager say: 3 6 31 0 self 640)
				else
					(= ticks 6)
				)
			)
			(3
				(pRightDoor setPri: 108)
				(vRightDoor setPri: 97)
				(pBackDoor setPri: 108)
				(vBackDoor setPri: 97)
				(pSecretDoor2 setPri: 130)
				(ego setMotion: MoveTo local0 local1 self)
			)
			(4
				(cond 
					(local2 (pSecretDoor2 setCycle: Beg self))
					((< (ego x?) 130) (pBackDoor setCycle: Beg self))
					(else (pRightDoor setCycle: Beg self))
				)
				(doorCloseSound play:)
			)
			(5
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
				(if (< (ego x?) 160)
					(ego setLoop: 1 1)
				else
					(ego setLoop: 0 1)
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
					(if (not (Btst 208))
						(messager say: 1 4 3 1 0 640)
						(squeakSound play:)
					)
					(pBackDoor setCycle: End self)
				else
					(if (not (Btst 209))
						(messager say: 1 4 3 1 0 640)
						(squeakSound play:)
					)
					(pRightDoor setCycle: End self)
				)
			)
			(1
				(squeakSound dispose:)
				(doorSound dispose:)
				(ego
					setMotion:
						MoveTo
						(ego x?)
						(if (> (ego x?) 160)
							(+ (ego y?) 2)
						else
							(- (ego y?) 2)
						)
						self
				)
			)
			(2
				(if (< (ego x?) 160)
					(curRoom newRoom: 627)
				else
					(curRoom newRoom: 631)
				)
			)
		)
	)
)

(instance sMoveToSpot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 81 175 self)
			)
			(1 (Face ego 49 129 self))
			(2 (self dispose:))
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
				(if local4
					(if (not (Btst 208))
						(messager say: 1 4 3 1 0 640)
						(squeakSound play:)
					)
					(pBackDoor setCycle: End self)
				else
					(if (not (Btst 209))
						(messager say: 1 4 3 1 0 640)
						(squeakSound play:)
					)
					(pRightDoor setCycle: End self)
				)
			)
			(1
				(squeakSound dispose:)
				(doorSound dispose:)
				(if local4
					(ego
						setMotion: PolyPath (pBackDoor approachX?) (pBackDoor approachY?) self
					)
				else
					(ego
						setMotion: PolyPath (pRightDoor approachX?) (pRightDoor approachY?) self
					)
				)
			)
			(2
				(if local4
					(curRoom newRoom: 627)
				else
					(curRoom newRoom: 631)
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
					((and (Btst 208) (< (ego x?) 160)) (self cue:))
					((Btst 209) (self cue:))
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
					(pBackDoor setCycle: End self)
				else
					(pRightDoor setCycle: End self)
				)
			)
			(3
				(ego setMotion: PolyPath (ego x?) (- (ego y?) 2) self)
				(squeakSound dispose:)
				(doorSound dispose:)
			)
			(4
				(if (< (ego x?) 160)
					(curRoom newRoom: 627)
				else
					(curRoom newRoom: 631)
				)
			)
		)
	)
)

(instance sCloseSecretDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(= ticks 30)
			)
			(1
				(messager say: 39 4 0 0 self 640)
			)
			(2
				(pSideCandle setCycle: End self)
			)
			(3
				(pSecretDoor2 setCycle: Beg self)
			)
			(4
				(doorCloseSound play:)
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
								301
								189
								257
								139
								245
								128
								121
								118
								96
								116
								80
								116
								0
								171
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 99 138 219 138 221 163 182 171 149 182 102 178 87 146 86 138
							yourself:
						)
				)
				(= local3 0)
				(if (pSecretDoor2 heading?)
					((pSecretDoor2 heading?) dispose:)
					(pSecretDoor2 heading: 0)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenSecretDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local3 1)
				(= ticks 30)
			)
			(1
				(if local6
					(= local6 0)
					(self cue:)
				else
					(messager say: 39 4 0 0 self 640)
				)
			)
			(2
				(pSideCandle setCycle: Beg self)
			)
			(3
				(doorSound play:)
				(pSecretDoor2 setCycle: End self)
			)
			(4
				(curRoom setScript: sLeaveSecretly)
			)
		)
	)
)

(instance sWaitItOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 10))
			(1
				(theGame handsOff:)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(pSideCandle setCycle: End self)
			)
			(2
				(pSecretDoor2 setCycle: Beg self)
			)
			(3
				(doorCloseSound play:)
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
								301
								189
								257
								139
								245
								128
								121
								118
								96
								116
								80
								116
								0
								171
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 99 138 219 138 221 163 182 171 149 182 102 178 87 146 86 138
							yourself:
						)
				)
				(= local3 0)
				(if (pSecretDoor2 heading?)
					((pSecretDoor2 heading?) dispose:)
					(pSecretDoor2 heading: 0)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLeaveSecretly of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 29 151 self)
			)
			(1
				(theMusic doSongs: fade:)
				(curRoom newRoom: 510)
			)
		)
	)
)

(instance pFlame of Prop
	(properties
		noun 39
		modNum 640
		approachX 190
		approachY 122
		x 162
		y 58
		view 699
		loop 6
		cel 2
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(pSideCandle doVerb: theVerb)
	)
)

(instance pSideCandle of Prop
	(properties
		noun 39
		modNum 640
		approachX 190
		approachY 122
		x 175
		y 55
		priority 75
		fixPriority 1
		view 699
		loop 9
		cel 3
		signal $4001
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(if local3
				(pSideCandle setScript: sCloseSecretDoor)
			else
				(pSideCandle setScript: sOpenSecretDoor)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance pSecretDoor2 of Prop
	(properties
		noun 36
		modNum 640
		x 49
		y 129
		priority 163
		fixPriority 1
		view 691
		loop 3
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(if local3
					(pSideCandle setScript: sCloseSecretDoor)
				else
					(pSideCandle setScript: sOpenSecretDoor)
				)
			)
			(80
				(= local6 1)
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: sMoveToSpot self)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(curRoom setScript: (ScriptID 13) 0 self)
	)
)

(instance pBackDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 110
		approachY 117
		x 81
		y 36
		priority 207
		fixPriority 1
		view 687
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local4 1)
				(curRoom setScript: sCastOpenDoor)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(else 
				(vBackDoor doVerb: theVerb)
			)
		)
	)
)

(instance pRightDoor of Prop
	(properties
		noun 1
		modNum 640
		x 250
		y 48
		priority 207
		fixPriority 1
		view 644
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local4 0)
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

(instance vCase of View
	(properties
		noun 36
		modNum 640
		y 31
		priority 64
		fixPriority 1
		view 691
	)
)

(instance vBackDoor of View
	(properties
		noun 1
		modNum 640
		approachX 110
		approachY 117
		x 80
		y 35
		priority 119
		fixPriority 1
		view 687
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(Bset 208)
				(messager say: 1 32 0 1 0 640)
			)
			(-80
				(= local4 1)
				(curRoom setScript: sCastOpenDoor)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
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
		approachX 245
		approachY 131
		x 253
		y 49
		priority 196
		fixPriority 1
		view 644
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(Bset 209)
				(messager say: 1 32 0 1 0 640)
			)
			(42
				(curRoom setScript: sPickLock)
			)
			(-80
				(= local4 0)
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

(instance vTheRug of View
	(properties
		noun 33
		modNum 640
		x 18
		y 123
		view 692
	)
)

(instance vTheTable of View
	(properties
		noun 28
		modNum 640
		x 93
		y 124
		z 24
		priority 163
		fixPriority 1
		view 697
	)
)

(instance vTheCeiling of View
	(properties
		noun 20
		modNum 640
		view 690
	)
)

(instance vRightCeiling of View
	(properties
		noun 39
		modNum 640
		x 180
		view 690
		cel 1
	)
)

(instance vTheCandles of View
	(properties
		noun 39
		modNum 640
		approachX 190
		approachY 122
		x 176
		y 55
		view 699
		loop 10
	)
	
	(method (doVerb theVerb)
		(pSideCandle doVerb: theVerb)
	)
)

(instance vSecretDoor of View
	(properties
		noun 36
		modNum 640
		x 50
		y 129
		priority 64
		fixPriority 1
		view 691
		loop 2
	)
)

(instance vBook of View
	(properties
		noun 36
		modNum 640
		x 5
		y 83
		priority 141
		fixPriority 1
		view 691
		loop 4
	)
)

(instance fChair1 of Feature
	(properties
		noun 27
		modNum 640
		nsLeft 123
		nsTop 125
		nsRight 146
		nsBottom 174
		sightAngle 180
		x 134
		y 149
	)
)

(instance fChair2 of Feature
	(properties
		noun 27
		modNum 640
		nsLeft 139
		nsTop 105
		nsRight 161
		nsBottom 128
		sightAngle 180
		x 150
		y 116
	)
)

(instance fChair3 of Feature
	(properties
		noun 27
		modNum 640
		nsLeft 183
		nsTop 110
		nsRight 209
		nsBottom 157
		sightAngle 180
		x 196
		y 133
	)
)

(instance fCrest of Feature
	(properties
		noun 36
		modNum 640
		nsLeft 30
		nsTop 42
		nsRight 43
		nsBottom 58
		sightAngle 180
		x 36
		y 50
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
