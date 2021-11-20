;;; Sierra Script 1.0 - (do not remove this comment)
(script# 660)
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
	rm660 0
)

(local
	local0
)
(instance rm660 of GloryRm
	(properties
		modNum 640
		picture 640
	)
	
	(method (init)
		(if (== prevRoomNum 663)
			(pSecretDoor setCel: 5)
			(ego posn: 209 114)
			(= local0 1)
		else
			(ego posn: 297 112)
		)
		(ego init: setScaler: Scaler 117 100 168 126 normalize:)
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						85
						111
						77
						115
						176
						125
						142
						188
						-300
						173
						-300
						0
						619
						0
						619
						137
						619
						138
						295
						138
						297
						139
						289
						145
						258
						144
						241
						127
					yourself:
				)
		)
		(pDoor approachVerbs: 4 32 init:)
		(pSecretDoor init: ignoreActors: 1)
		(vTheBed ignoreActors: init:)
		(vTheRug ignoreActors: init:)
		(vTheDoor ignoreActors: approachVerbs: 4 32 init:)
		(doorTeller init: vTheDoor 640 2 155)
		(vThePicture ignoreActors: init:)
		(fSteps init: approachVerbs: 4)
		(fCurtains init: approachVerbs: 4)
		(curRoom setScript: sComeOnIn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 0 1 0 0 0 660)
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
				(= ticks 30)
			)
			(1
				(doorSound play:)
				(if local0
					(= seconds 2)
				else
					(if (not (Btst 223)) (squeakSound play:))
					(pDoor setCycle: End self)
				)
			)
			(2
				(pDoor setPri: 108)
				(vTheDoor setPri: 97)
				(if local0
					(ego setMotion: MoveTo 211 137 self)
				else
					(ego setMotion: MoveTo 296 174 self)
				)
			)
			(3
				(doorCloseSound play:)
				(if local0
					(pSecretDoor cycleSpeed: 12 setCycle: Beg self)
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
				(ego view: 4 setLoop: 1 1 setCel: 0 setCycle: End self)
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
				(if (not (Btst 223))
					(messager say: 1 4 3 1 0 640)
					(squeakSound play:)
				)
				(pDoor setCycle: End self)
			)
			(1
				(squeakSound dispose:)
				(doorSound dispose:)
				(ego setMotion: MoveTo (ego x?) (- (ego y?) 2) self)
			)
			(2 (curRoom newRoom: 621))
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
				(if (not (Btst 223))
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
			(2 (curRoom newRoom: 621))
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
				(if (Btst 223)
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
			(4 (curRoom newRoom: 621))
		)
	)
)

(instance sLeave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(doorSound play:)
				(pSecretDoor setCycle: End self)
			)
			(1
				(ego setMotion: MoveTo 209 114 self)
			)
			(2 (curRoom newRoom: 663))
		)
	)
)

(instance pDoor of Prop
	(properties
		modNum 640
		approachX 311
		approachY 117
		x 261
		y 51
		priority 207
		fixPriority 1
		view 695
		loop 2
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
				(vTheDoor doVerb: theVerb)
			)
		)
	)
)

(instance pSecretDoor of Prop
	(properties
		modNum 640
		approachX 191
		approachY 110
		x 181
		y 73
		priority 75
		fixPriority 1
		view 660
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if
				(or (< mouseX 160) (and (< 37 mouseY) (< mouseY 92)))
					(vThePicture doVerb: 1)
				else
					(curRoom doVerb: 1)
				)
			)
			(-80
				(curRoom setScript: sLeave)
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

(instance vTheBed of View
	(properties
		noun 19
		modNum 640
		y 5
		priority 152
		fixPriority 1
		view 688
		signal $5001
	)
)

(instance vTheRug of View
	(properties
		noun 33
		modNum 640
		x 145
		y 139
		view 688
		loop 2
	)
)

(instance vTheDoor of View
	(properties
		noun 1
		modNum 640
		approachX 298
		approachY 137
		x 262
		y 59
		z 10
		priority 152
		fixPriority 1
		view 695
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(Bset 223)
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

(instance vThePicture of View
	(properties
		noun 4
		modNum 640
		x 93
		y 23
		priority 86
		fixPriority 1
		view 698
	)
)

(instance fSteps of Feature
	(properties
		noun 8
		modNum 640
		nsLeft 285
		nsTop 114
		nsRight 319
		nsBottom 136
		sightAngle 180
		x 302
		y 125
	)
)

(instance fCurtains of Feature
	(properties
		noun 18
		modNum 640
		nsTop 7
		nsRight 63
		nsBottom 93
		sightAngle 180
		x 31
		y 50
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
