;;; Sierra Script 1.0 - (do not remove this comment)
(script# 661)
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
	rm661 0
)

(local
	local0
	local1
	local2
	local3
	local4
)
(instance rm661 of GloryRm
	(properties
		modNum 640
		picture 640
		style $0400
		west 626
	)
	
	(method (init)
		(switch prevRoomNum
			(626
				(= local0 68)
				(= local1 187)
				(ego setLoop: 0 1 posn: 0 173)
			)
			(663
				(= local2 1)
				(= local0 221)
				(= local1 125)
				(ego setLoop: 2 1 setCel: 3 posn: 190 115)
			)
			(else 
				(= local0 308)
				(= local1 146)
				(ego posn: 333 129)
			)
		)
		(ego
			init:
			setScaler: Scaler 100 100 189 0
			normalize:
			setPri: 0
		)
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						-300
						0
						619
						0
						619
						189
						312
						189
						314
						148
						283
						127
						272
						127
						255
						119
						143
						122
						98
						124
						91
						131
						68
						131
						24
						189
						13
						189
						-300
						189
					yourself:
				)
		)
		(pRightDoor approachVerbs: 4 32 init:)
		(rightDoorTeller init: pRightDoor 640 2 155)
		(pLeftDoor approachVerbs: 4 32 init:)
		(leftDoorTeller init: pLeftDoor 640 2 155)
		(pHiddenDoor init: ignoreActors: setPri: 152)
		(pLeftSconce ignoreActors: setCycle: Fwd init:)
		(pRightSconce ignoreActors: setCycle: Fwd init:)
		(vTheShelf ignoreActors: approachVerbs: 4 init:)
		(vTheWindow ignoreActors: init:)
		(vLeftDoor ignoreActors: approachVerbs: 4 32 init:)
		(vRightDoor ignoreActors: approachVerbs: 4 32 init:)
		(vTheBook init: ignoreActors: approachVerbs: 4)
		(fWheel init: approachVerbs: 4)
		(fCresset1 init: approachVerbs: 4)
		(fCresset2 init: approachVerbs: 4)
		(ego setScript: sComeOnIn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 0 1 0 0 0 661)
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
					(local2 (pHiddenDoor setCycle: End self))
					((< (ego x?) 60)
						(if (not (Btst 224))
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
						(pLeftDoor setCycle: End self)
					)
					(else
						(if (not (Btst 118))
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
						(pRightDoor setCycle: End self)
					)
				)
			)
			(2
				(pRightDoor setPri: 108)
				(pLeftDoor setPri: 108)
				(ego normalize: setMotion: MoveTo local0 local1 self)
			)
			(3
				(doorCloseSound play:)
				(pHiddenDoor setPri: 53)
				(cond 
					(local2 (pHiddenDoor setCycle: Beg self))
					((< (ego x?) 160) (pLeftDoor setCycle: Beg self))
					(else (pRightDoor setCycle: Beg self))
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
				(if (< (ego x?) 60)
					(ego setLoop: 1 1)
				else
					(ego setLoop: 0 1)
				)
				(ego view: 4 setCel: 0 setCycle: End self)
			)
			(1
				(if (> (ego x?) 60)
					(messager say: 2 155 30 1 self 640)
				else
					(messager say: 2 155 27 1 self 640)
				)
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
				(if (< (ego x?) 60)
					(if (not (Btst 224))
						(messager say: 1 4 3 1 0 640)
						(squeakSound play:)
					)
					(pLeftDoor setCycle: End self)
				else
					(if (not (Btst 118))
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
						(if (< (ego x?) 60)
							(- (ego x?) 2)
						else
							(+ (ego x?) 2)
						)
						(- (ego y?) 2)
						self
				)
			)
			(2
				(if (< (ego x?) 100)
					(curRoom newRoom: (curRoom west?))
				else
					(curRoom newRoom: 629)
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
				(if local4
					(if (not (Btst 224))
						(messager say: 1 4 3 1 0 640)
						(squeakSound play:)
					)
					(pLeftDoor setCycle: End self)
				else
					(if (not (Btst 118))
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
						setMotion: PolyPath (pLeftDoor approachX?) (pLeftDoor approachY?) self
					)
				else
					(ego
						setMotion: PolyPath (pRightDoor approachX?) (pRightDoor approachY?) self
					)
				)
			)
			(2
				(if local4
					(curRoom newRoom: (curRoom west?))
				else
					(curRoom newRoom: 629)
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
					((Btst 224) (self cue:))
					((Btst 118) (self cue:))
					(else
						(squeakSound play:)
						(messager say: 1 4 3 1 0 640)
						(self cue:)
					)
				)
			)
			(2
				(doorSound play:)
				(if (< (ego x?) 60)
					(pLeftDoor setCycle: End self)
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
				(if (< (ego x?) 100)
					(curRoom newRoom: 626)
				else
					(curRoom newRoom: 629)
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
				(= ticks 24)
			)
			(1
				(doorSound play:)
				(pHiddenDoor setPri: 207 setCycle: End self)
			)
			(2
				(ego setMotion: MoveTo 198 115 self)
			)
			(3 (curRoom newRoom: 663))
		)
	)
)

(instance sSayMessage of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 221 125 self)
			)
			(1
				(ego normalize:)
				(messager say: 3 6 22 1 self 640)
			)
			(2
				(vTheBook setCycle: End self)
			)
			(3
				(pHiddenDoor cue:)
				(self dispose:)
			)
		)
	)
)

(instance pLeftDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 38
		approachY 186
		x -10
		y 89
		priority 207
		fixPriority 1
		view 644
		loop 1
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
				(vLeftDoor doVerb: theVerb)
			)
		)
	)
)

(instance pRightDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 296
		approachY 137
		x 299
		y 61
		priority 207
		fixPriority 1
		view 687
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

(instance pHiddenDoor of Prop
	(properties
		noun 36
		modNum 640
		approachX 221
		approachY 125
		x 150
		y 61
		view 684
		loop 2
		signal $4001
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: sSayMessage)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (cue)
		(if ((ScriptID 664 0) init: show: dispose:)
			(DisposeScript 664)
			(curRoom setScript: sLeave)
		else
			(vTheBook setCycle: Beg)
			(DisposeScript 664)
		)
	)
)

(instance pLeftSconce of Prop
	(properties
		noun 21
		modNum 640
		x 71
		y 78
		view 699
		loop 12
		cel 5
		detailLevel 2
	)
)

(instance pRightSconce of Prop
	(properties
		noun 21
		modNum 640
		x 279
		y 69
		view 699
		loop 11
		detailLevel 2
	)
)

(instance vTheBook of Prop
	(properties
		noun 35
		modNum 640
		approachX 220
		approachY 86
		x 150
		y 71
		z 10
		view 684
		loop 3
		signal $4001
	)
)

(instance vTheShelf of View
	(properties
		noun 36
		modNum 640
		approachX 220
		approachY 125
		x 291
		y 10
		z 10
		view 684
		loop 1
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(messager say: 35 4 0 1 0 640)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance vTheWindow of View
	(properties
		noun 16
		modNum 640
		x 206
		y -20
		view 698
		loop 5
		signal $4001
	)
)

(instance vLeftDoor of View
	(properties
		noun 1
		modNum 640
		approachX 38
		approachY 186
		x -10
		y 81
		view 644
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(Bset 224)
				(messager say: 1 32 0 1 0 640)
			)
			(42
				(curRoom setScript: sPickLock)
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
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vRightDoor of View
	(properties
		noun 1
		modNum 640
		approachX 296
		approachY 137
		x 299
		y 59
		view 687
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(Bset 118)
				(messager say: 1 32 0 1 0 640)
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
			(42
				(curRoom setScript: sPickLock)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fWheel of Feature
	(properties
		noun 34
		modNum 640
		nsLeft 133
		nsRight 200
		nsBottom 39
		sightAngle 180
		x 166
		y 19
	)
)

(instance fCresset1 of Feature
	(properties
		noun 22
		modNum 640
		nsLeft 53
		nsTop 75
		nsRight 71
		nsBottom 100
		sightAngle 180
		x 62
		y 87
	)
)

(instance fCresset2 of Feature
	(properties
		noun 22
		modNum 640
		nsLeft 278
		nsTop 65
		nsRight 296
		nsBottom 92
		sightAngle 180
		x 287
		y 78
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
