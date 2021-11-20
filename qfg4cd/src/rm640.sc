;;; Sierra Script 1.0 - (do not remove this comment)
(script# 640)
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
	rm640 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
)
(instance rm640 of GloryRm
	(properties
		modNum 640
		picture 640
		east 622
		west 620
	)
	
	(method (init)
		(theGame handsOff:)
		(switch prevRoomNum
			(620
				(= local0 38)
				(= local1 118)
				(ego posn: 0 108)
			)
			(622
				(= local0 225)
				(= local1 175)
				(ego setLoop: 1 1 posn: 293 152)
			)
			(621
				(= local0 80)
				(= local1 98)
				(ego setLoop: 2 1 setCel: 3 posn: 88 82)
			)
			(else 
				(= local0 225)
				(= local1 175)
				(ego posn: 225 175)
			)
		)
		(ego
			init:
			ignoreActors:
			setScaler: Scaler 100 100 180 0
			normalize:
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						53
						90
						31
						98
						31
						104
						6
						123
						56
						131
						103
						148
						90
						163
						7
						158
						9
						189
						0
						189
						0
						0
						319
						0
						319
						189
						303
						189
						270
						159
						242
						131
						116
						126
						112
						137
						48
						110
						62
						99
						104
						97
						106
						94
						75
						91
					yourself:
				)
		)
		(super init: &rest)
		(pLeftDoor init: ignoreActors: approachVerbs: 4 32)
		(leftDoorTeller init: pLeftDoor 640 2 155)
		(pBackDoor init: ignoreActors: approachVerbs: 4 32)
		(backDoorTeller init: pBackDoor 640 2 155)
		(pRightDoor init: ignoreActors: approachVerbs: 4 32)
		(rightDoorTeller init: pRightDoor 640 2 155)
		(pLtUpperDoor init: ignoreActors: approachVerbs: 4)
		(pRtUpperDoor init: ignoreActors: approachVerbs: 4)
		((pFlames new:)
			setCel: 0
			posn: 92 23
			setCycle: Fwd
			init:
		)
		((pFlames new:)
			setCel: 1
			posn: 99 23
			setCycle: Fwd
			init:
		)
		((pFlames new:)
			setCel: 2
			posn: 105 20
			setCycle: Fwd
			init:
		)
		((pFlames new:)
			setCel: 0
			posn: 112 22
			setCycle: Fwd
			init:
		)
		((pFlames new:)
			setCel: 1
			posn: 119 24
			setCycle: Fwd
			init:
		)
		(vLeftDoor ignoreActors: approachVerbs: 4 init:)
		(vBackDoor ignoreActors: approachVerbs: 4 init:)
		(vRightDoor ignoreActors: approachVerbs: 4 init:)
		(vCandle ignoreActors: init:)
		(vStoneSteps ignoreActors: approachVerbs: 4 init:)
		(vCupBoard ignoreActors: approachVerbs: 4 init:)
		(vChest ignoreActors: approachVerbs: 4 init:)
		(vRightCurtain ignoreActors: approachVerbs: 4 init:)
		(vFloorRug ignoreActors: init:)
		(pDoor1 init: ignoreActors: approachVerbs: 4)
		(pDoor2 init: ignoreActors: approachVerbs: 4)
		(pDoor3 init: ignoreActors: approachVerbs: 4)
		(curRoom setScript: sComeOnIn)
	)
)

(instance sComeOnIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOn:)
				(doorSound play:)
				(switch prevRoomNum
					(621
						(pBackDoor setCycle: End self)
					)
					(620
						(pLeftDoor setCycle: End self)
					)
					(else 
						(pRightDoor setCycle: End self)
					)
				)
			)
			(1
				(vBackDoor setPri: 0)
				(pBackDoor setPri: 86)
				(pLeftDoor setPri: 75)
				(vStoneSteps setPri: 86 init:)
				(pRightDoor setPri: 75)
				(vRightCurtain setPri: 141 init:)
				(ego setMotion: PolyPath local0 local1 self)
			)
			(2
				(doorCloseSound play:)
				(switch prevRoomNum
					(621
						(pBackDoor setCycle: Beg self)
					)
					(620
						(pLeftDoor setCycle: Beg self)
					)
					(else 
						(pRightDoor setCycle: Beg self)
					)
				)
			)
			(3
				(doorCloseSound stop:)
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
					(if (< (ego y?) 100)
						(= local2 1)
						(if (not (Btst 221))
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
						(pBackDoor setCycle: End self)
					else
						(= local3 1)
						(if (not (Btst 220))
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
						(pLeftDoor setCycle: End self)
					)
				else
					(= local3 0)
					(if (not (Btst 219))
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
					(local2 (ego setMotion: MoveTo 87 82 self))
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
					(local2 (curRoom newRoom: 621))
					(local3 (curRoom newRoom: (curRoom west?)))
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
						(if (not (Btst 220))
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
						(pLeftDoor setCycle: End self)
					)
					(local2
						(if (not (Btst 221))
							(messager say: 1 4 3 1 0 640)
							(squeakSound play:)
						)
						(pBackDoor setCycle: End self)
					)
					(else
						(if (not (Btst 219))
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
					(local2 (curRoom newRoom: 621))
					(local3 (curRoom newRoom: (curRoom west?)))
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
					((Btst 221) (self cue:))
					((Btst 220) (self cue:))
					((Btst 219) (self cue:))
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
					(if (< (ego y?) 100)
						(= local2 1)
						(pBackDoor setCycle: End self)
					else
						(= local3 1)
						(pLeftDoor setCycle: End self)
					)
				else
					(= local3 0)
					(pRightDoor setCycle: End self)
				)
			)
			(3
				(squeakSound dispose:)
				(doorSound dispose:)
				(= cycles 5)
			)
			(4
				(cond 
					(local2 (curRoom newRoom: 621))
					((< (ego x?) 160) (curRoom newRoom: (curRoom west?)))
					(else (curRoom newRoom: (curRoom east?)))
				)
			)
		)
	)
)

(instance sOpenCupboard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOn:)
				(switch local4
					(1
						(pLtUpperDoor setCycle: End self)
					)
					(else 
						(pRtUpperDoor setCycle: End self)
					)
				)
			)
			(1
				(messager say: 7 4 14 1 self 640)
			)
			(2
				(doorCloseSound play:)
				(switch local4
					(1
						(pLtUpperDoor setCycle: Beg self)
					)
					(else 
						(pRtUpperDoor setCycle: Beg self)
					)
				)
			)
			(3
				(= local4 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCastOpenCupboard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOn:)
				(ego trySkill: 20)
				(switch local4
					(1
						(pLtUpperDoor setCycle: End self)
					)
					(else 
						(pRtUpperDoor setCycle: End self)
					)
				)
			)
			(1
				(switch local4
					(1
						(ego
							setMotion:
								PolyPath
								(pLtUpperDoor approachX?)
								(pLtUpperDoor approachY?)
								self
						)
					)
					(else 
						(ego
							setMotion:
								PolyPath
								(pRtUpperDoor approachX?)
								(pRtUpperDoor approachY?)
								self
						)
					)
				)
			)
			(2
				(messager say: 7 4 14 1 self 640)
			)
			(3
				(doorCloseSound play:)
				(switch local4
					(1
						(pLtUpperDoor setCycle: Beg self)
					)
					(else 
						(pRtUpperDoor setCycle: Beg self)
					)
				)
			)
			(4
				(= local4 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(self cue:)
			)
			(1
				(switch local5
					(1 (pDoor1 setCycle: End self))
					(2 (pDoor2 setCycle: End self))
					(3 (pDoor3 setCycle: End self))
					(else  (self cue:))
				)
			)
			(2
				(if (== local5 1)
					(ego setLoop: 1 1)
				else
					(ego setLoop: 0 1)
				)
				(ego
					view: 4
					posn: (- (ego x?) 3) (ego y?)
					setCel: 0
					setCycle: End self
				)
			)
			(3
				(messager say: 7 4 14 1 self 640)
			)
			(4 (ego setCycle: Beg self))
			(5
				(doorCloseSound play:)
				(ego posn: (+ (ego x?) 3) (ego y?) normalize:)
				(switch local5
					(1 (pDoor1 setCycle: Beg self))
					(2 (pDoor2 setCycle: Beg self))
					(3 (pDoor3 setCycle: Beg self))
				)
			)
			(6
				(= local5 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCastOpenUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego trySkill: 20)
				(self cue:)
			)
			(1
				(switch local5
					(1
						(ego
							setMotion: PolyPath (pDoor1 approachX?) (pDoor1 approachY?) self
						)
					)
					(2
						(ego
							setMotion: PolyPath (pDoor2 approachX?) (pDoor2 approachY?) self
						)
					)
					(3
						(ego
							setMotion: PolyPath (pDoor3 approachX?) (pDoor3 approachY?) self
						)
					)
					(else  (self cue:))
				)
			)
			(2
				(switch local5
					(1 (pDoor1 setCycle: End self))
					(2 (pDoor2 setCycle: End self))
					(3 (pDoor3 setCycle: End self))
					(else  (self cue:))
				)
			)
			(3
				(ego
					view: 4
					posn: (- (ego x?) 3) (ego y?)
					setLoop: (if (== local5 1) 1 else 0) 1
					setCel: 0
					setCycle: End self
				)
			)
			(4
				(messager say: 7 4 14 1 self 640)
			)
			(5 (ego setCycle: Beg self))
			(6
				(doorCloseSound play:)
				(ego posn: (+ (ego x?) 3) (ego y?) normalize:)
				(switch local5
					(1 (pDoor1 setCycle: Beg self))
					(2 (pDoor2 setCycle: Beg self))
					(3 (pDoor3 setCycle: Beg self))
				)
			)
			(7
				(= local5 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance pLeftDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 16
		approachY 119
		x 1
		y 18
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
		approachX 85
		approachY 92
		x 53
		y 18
		z 6
		priority 97
		fixPriority 1
		view 687
		signal $4001
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
				(vBackDoor doVerb: theVerb)
			)
		)
	)
)

(instance pRightDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 268
		approachY 160
		x 265
		y 195
		z 125
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

(instance pLtUpperDoor of Prop
	(properties
		noun 10
		modNum 640
		approachX 160
		approachY 126
		x 137
		y 50
		view 694
		loop 2
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= local4 1)
				(curRoom setScript: sOpenCupboard)
			)
			(-80
				(= local4 1)
				(curRoom setScript: sCastOpenCupboard)
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

(instance pRtUpperDoor of Prop
	(properties
		noun 10
		modNum 640
		approachX 160
		approachY 126
		x 216
		y 51
		view 694
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= local4 2)
				(curRoom setScript: sOpenCupboard)
			)
			(-80
				(= local4 2)
				(curRoom setScript: sCastOpenCupboard)
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

(instance pFlames of Prop
	(properties
		noun 15
		modNum 640
		x 92
		y 23
		view 699
		loop 6
		detailLevel 2
	)
)

(instance pDoor1 of Prop
	(properties
		noun 7
		modNum 640
		approachX 168
		approachY 134
		x 134
		y 103
		view 643
		loop 3
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= local5 1)
				(curRoom setScript: sOpenUp)
			)
			(-80
				(= local5 1)
				(curRoom setScript: sCastOpenUp)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(else  (vChest doVerb: theVerb))
		)
	)
)

(instance pDoor2 of Prop
	(properties
		noun 7
		modNum 640
		approachX 154
		approachY 135
		x 193
		y 104
		view 643
		loop 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= local5 2)
				(curRoom setScript: sOpenUp)
			)
			(-80
				(= local5 2)
				(curRoom setScript: sCastOpenUp)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(else  (vChest doVerb: theVerb))
		)
	)
)

(instance pDoor3 of Prop
	(properties
		noun 7
		modNum 640
		approachX 180
		approachY 139
		x 217
		y 106
		view 643
		loop 2
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= local5 3)
				(curRoom setScript: sOpenUp)
			)
			(-80
				(= local5 3)
				(curRoom setScript: sCastOpenUp)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(else  (vChest doVerb: theVerb))
		)
	)
)

(instance vBackDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 85
		approachY 92
		x 55
		y 11
		priority 86
		fixPriority 1
		view 687
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(Bset 221)
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
		approachX 16
		approachY 119
		y 15
		priority 53
		fixPriority 1
		view 644
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(Bset 220)
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
		approachX 268
		approachY 160
		x 270
		y 67
		view 644
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(Bset 219)
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

(instance vCandle of View
	(properties
		noun 15
		modNum 640
		approachX 85
		approachY 92
		x 105
		y 20
		priority 97
		fixPriority 1
		view 699
		cel 1
	)
)

(instance vStoneSteps of View
	(properties
		noun 8
		modNum 640
		y 5
		z -80
		priority 207
		fixPriority 1
		view 693
	)
)

(instance vCupBoard of View
	(properties
		noun 10
		modNum 640
		approachX 160
		approachY 126
		x 126
		y 41
		view 694
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= local4 1)
				(curRoom setScript: sOpenCupboard)
			)
			(-80
				(= local5 1)
				(curRoom setScript: sCastOpenCupboard)
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

(instance vChest of View
	(properties
		noun 7
		modNum 640
		approachX 160
		approachY 126
		x 128
		y 95
		view 643
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(messager say: 7 4 14)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance vRightCurtain of View
	(properties
		noun 18
		modNum 640
		x 261
		y 46
		priority 141
		fixPriority 1
		view 696
		loop 2
	)
)

(instance vFloorRug of View
	(properties
		noun 33
		modNum 640
		x 65
		y 125
		view 692
		loop 2
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
