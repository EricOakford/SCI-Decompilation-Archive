;;; Sierra Script 1.0 - (do not remove this comment)
(script# 663)
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
	rm663 0
)

(local
	local0
	local1
)
(instance rm663 of GloryRm
	(properties
		modNum 640
		picture 640
		east 632
	)
	
	(method (init)
		(if (or (== prevRoomNum 660) (== prevRoomNum 661))
			(ego posn: 30 129)
			(= local0 1)
			(ego hide:)
		else
			(ego posn: 278 133)
		)
		(ego
			init:
			setScaler: Scaler 100 100 189 0
			normalize: (if (== prevRoomNum 660) 4 else 5)
		)
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 57 130 0 172 0 0 319 0 319 189 299 189 252 130 130 123 72 121
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 87 133 221 135 214 160 198 169 169 179 118 180 97 168 82 146
					yourself:
				)
		)
		(pDoor approachVerbs: 4 32 init:)
		(pFire ignoreActors: approachVerbs: 4 init: setCycle: Fwd)
		(pSecretDoor2 ignoreActors: init:)
		(pCrest init: ignoreActors: approachVerbs: 4)
		(vFirePlace ignoreActors: init:)
		(vBookCase ignoreActors: init:)
		(vRightDoor
			ignoreActors:
			approachVerbs: 4 32
			setPri: (if local0 97 else 207)
			init:
		)
		(doorTeller init: vRightDoor 640 2 155)
		(vRug ignoreActors: init:)
		(vTheTable ignoreActors: approachVerbs: 4 init:)
		(vCeiling ignoreActors: init:)
		(vRightCeiling ignoreActors: init:)
		(vSecretDoor ignoreActors: init:)
		(vBook ignoreActors: init:)
		(fChair1 approachVerbs: 4 init:)
		(fChair2 approachVerbs: 4 init:)
		(fChair3 approachVerbs: 4 init:)
		(curRoom setScript: sComeOnIn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 0 1 0 0 0 663)
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
				(ego setPri: 130)
				(= ticks 30)
			)
			(1
				(doorSound play:)
				(if local0
					(pSecretDoor2 setCycle: End self)
				else
					(if (not (Bset 212)) (squeakSound play:))
					(pDoor setCycle: End self)
				)
			)
			(2
				(ego normalize:)
				(pDoor setPri: 108)
				(vRightDoor setPri: 97)
				(if local0
					(ego setMotion: MoveTo 46 175 self)
				else
					(ego setMotion: MoveTo 248 143 self)
				)
			)
			(3
				(doorCloseSound play:)
				(if local0
					(pSecretDoor2 setPri: 130 setCycle: Beg self)
				else
					(pDoor setCycle: Beg self)
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
				(ego view: 4 setLoop: 0 1 setCel: 0 setCycle: End self)
			)
			(1
				(messager say: 2 155 29 1 self 640)
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
				(if (not (Btst 212))
					(messager say: 1 4 3 1 0 640)
					(squeakSound play:)
				)
				(pDoor setCycle: End self)
			)
			(1
				(squeakSound dispose:)
				(doorSound dispose:)
				(ego setMotion: PolyPath (+ (ego x?) 10) (ego y?) self)
			)
			(2
				(curRoom newRoom: (curRoom east?))
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
				(if (not (Btst 212))
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
			(2 (= seconds 2))
			(3
				(curRoom newRoom: (curRoom east?))
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
				(if (Btst 212)
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
				(ego setMotion: PolyPath (ego x?) (- (ego y?) 2) self)
				(squeakSound dispose:)
				(doorSound dispose:)
			)
			(4
				(curRoom newRoom: (curRoom east?))
			)
		)
	)
)

(instance sSecret of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(= seconds 1)
			)
			(1 (pCrest setCycle: End self))
			(2
				(doorSound play:)
				(pSecretDoor2 setPri: 150 setCycle: End self)
			)
			(3
				(messager say: 37 4 0 0 self 640)
			)
			(4
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 87 133 212 137 211 154 198 169 169 179 118 180 97 168 82 146
							yourself:
						)
						((Polygon new:)
							type: 2
							init:
								0
								0
								319
								0
								319
								189
								305
								189
								250
								131
								73
								121
								63
								128
								68
								149
								61
								156
								36
								154
								23
								144
								0
								173
							yourself:
						)
				)
				(theGame handsOn:)
				(self dispose:)
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
			(1 (pCrest setCycle: Beg self))
			(2
				(doorCloseSound play:)
				(pSecretDoor2 setPri: 130 setCycle: Beg self)
			)
			(3
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 57 130 0 172 0 0 319 0 319 189 299 189 252 130 130 123 72 121
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 87 133 212 137 211 154 198 169 169 179 118 180 97 168 82 146
							yourself:
						)
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
				(if local1
					(theGame handsOff:)
					(= seconds 1)
				else
					(self dispose:)
				)
			)
			(1
				(ego setMotion: MoveTo 29 151 self)
			)
			(2 (curRoom newRoom: 660))
		)
	)
)

(instance pFire of Prop
	(properties
		noun 31
		modNum 640
		x 166
		y 112
		priority 80
		fixPriority 1
		view 679
		loop 2
		cel 4
		detailLevel 2
	)
)

(instance pDoor of Prop
	(properties
		modNum 640
		approachX 248
		approachY 143
		x 252
		y 47
		priority 207
		fixPriority 1
		view 644
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
			(else 
				(vRightDoor doVerb: theVerb)
			)
		)
	)
)

(instance pSecretDoor2 of Prop
	(properties
		noun 38
		modNum 640
		x 49
		y 129
		priority 130
		fixPriority 1
		view 691
		loop 3
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 19 147 42 148 41 159 7 159
						yourself:
					)
					1
					7
					3
					sLeaveSecretly
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
)

(instance pCrest of Prop
	(properties
		noun 37
		modNum 640
		approachX 61
		approachY 129
		x 38
		y 138
		z 80
		view 691
		loop 5
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if local1
					(= local1 0)
					(curRoom setScript: sCloseSecretDoor)
				else
					(= local1 1)
					(curRoom setScript: sSecret)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vFirePlace of View
	(properties
		noun 31
		modNum 640
		x 82
		y 43
		priority 65
		fixPriority 1
		view 679
	)
)

(instance vBookCase of View
	(properties
		noun 38
		modNum 640
		y 31
		view 691
	)
)

(instance vRightDoor of View
	(properties
		noun 1
		modNum 640
		approachX 248
		approachY 143
		x 253
		y 49
		priority 207
		fixPriority 1
		view 644
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(Bset 212)
				(messager say: 1 32 0 1 0 640)
			)
			(42
				(curRoom setScript: sPickLock)
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

(instance vRug of View
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
		approachX 135
		approachY 133
		x 93
		y 128
		z 25
		priority 152
		fixPriority 1
		view 697
	)
)

(instance vCeiling of View
	(properties
		modNum 640
		view 690
	)
)

(instance vRightCeiling of View
	(properties
		modNum 640
		x 180
		view 690
		cel 1
	)
)

(instance vSecretDoor of View
	(properties
		noun 38
		modNum 640
		x 50
		y 129
		view 691
		loop 2
	)
)

(instance vBook of View
	(properties
		noun 38
		modNum 640
		x 5
		y 83
		priority 132
		fixPriority 1
		view 691
		loop 4
	)
)

(instance fChair1 of Feature
	(properties
		noun 27
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
		nsLeft 183
		nsTop 110
		nsRight 209
		nsBottom 157
		sightAngle 180
		x 196
		y 133
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
