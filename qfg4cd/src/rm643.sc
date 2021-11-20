;;; Sierra Script 1.0 - (do not remove this comment)
(script# 643)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use EgoDead)
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
	rm643 0
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
(instance rm643 of GloryRm
	(properties
		modNum 640
		picture 640
		east 624
	)
	
	(method (init)
		(switch prevRoomNum
			(625
				(= local2 1)
				(= local0 55)
				(= local1 164)
				(ego posn: 9 130)
			)
			(else 
				(= local0 257)
				(= local1 166)
				(ego posn: 288 146)
			)
		)
		(ego
			init:
			setScaler: Scaler 100 100 189 0
			normalize: (if (== prevRoomNum 625) 0 else 1)
		)
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
						309
						189
						272
						171
						240
						130
						137
						133
						125
						145
						56
						149
						28
						137
						4
						154
						4
						189
						0
						189
					yourself:
				)
		)
		(pSafeDoor init: approachVerbs: 4)
		(pRightDoor init: approachVerbs: 4 32)
		(rightDoorTeller init: pRightDoor 640 2 155)
		(pLeftDoor init: approachVerbs: 4 32)
		(leftDoorTeller init: pLeftDoor 640 2 155)
		(vLeftCupDoor ignoreActors: approachVerbs: 4 init:)
		(vMidCupDoor ignoreActors: approachVerbs: 4 init:)
		(vRightCupDoor ignoreActors: approachVerbs: 4 init:)
		(vBackDoor ignoreActors: approachVerbs: 4 32 init:)
		(vLeftDoor ignoreActors: approachVerbs: 4 32 init:)
		(vTheRug ignoreActors: init:)
		(vCupBoard ignoreActors: approachVerbs: 4 init:)
		(vCeiling ignoreActors: init:)
		(vLeftCeiling ignoreActors: init:)
		(vSafe ignoreActors: approachVerbs: 4 init:)
		(vThePainting ignoreActors: init:)
		(fBowl init: approachVerbs: 4)
		(fChest init: approachVerbs: 4)
		(chestTeller init: fChest 640 2 167)
		(curRoom setScript: sComeOnIn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 0 1 0 0 0 643)
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
				(= seconds 2)
			)
			(1
				(doorSound play:)
				(if local2
					(pLeftDoor setCycle: End self)
				else
					(pRightDoor setCycle: End self)
				)
			)
			(2
				(pRightDoor setPri: 141)
				(pLeftDoor setPri: 141)
				(ego setMotion: MoveTo local0 local1 self)
			)
			(3
				(doorCloseSound play:)
				(if local2
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
				(ego
					view: 4
					setLoop: (if (> (ego x?) 60) 0 else 1) 1
					setCel: 0
					setCycle: CT (if (> (ego x?) 60) 2 else 1) 1 self
				)
			)
			(1
				(if (> (ego x?) 60)
					(messager say: 2 155 27 1 self 640)
				else
					(messager say: 2 155 28 1 self 640)
				)
			)
			(2 (= ticks 120))
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
					(= local2 1)
					(if (not (Btst 214))
						(messager say: 1 4 3 1 0 640)
						(squeakSound play:)
					)
					(pLeftDoor setCycle: End self)
				else
					(if (not (Btst 215))
						(= local2 0)
						(messager say: 1 4 3 1 0 640)
						(squeakSound play:)
					)
					(pRightDoor setCycle: End self)
				)
			)
			(1
				(squeakSound dispose:)
				(doorSound dispose:)
				(if local2
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
				(if local2
					(curRoom newRoom: 625)
				else
					(curRoom newRoom: 624)
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
				(if local2
					(if (not (Btst 214))
						(messager say: 1 4 3 1 0 640)
						(squeakSound play:)
					)
					(pLeftDoor setCycle: End self)
				else
					(if (not (Btst 215))
						(messager say: 1 4 3 1 0 640)
						(squeakSound play:)
					)
					(pRightDoor setCycle: End self)
				)
			)
			(1
				(squeakSound dispose:)
				(doorSound dispose:)
				(if local2
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
				(if local2
					(curRoom newRoom: 625)
				else
					(curRoom newRoom: 624)
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
					((Btst 215) (self cue:))
					((Btst 214) (self cue:))
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
					(= local2 1)
					(pLeftDoor setCycle: End self)
				else
					(= local2 0)
					(pRightDoor setCycle: End self)
				)
			)
			(3
				(squeakSound dispose:)
				(doorSound dispose:)
				(= cycles 5)
			)
			(4
				(if (< (ego x?) 160)
					(curRoom newRoom: 625)
				else
					(curRoom newRoom: 624)
				)
			)
		)
	)
)

(instance sDoTheChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if local4
					(self cue:)
				else
					(messager say: 6 4 9 1 self 640)
				)
			)
			(1 (chestTeller doVerb: 4))
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenTheChest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (Btst 191)
					(self setScript: sAlreadyDone)
				else
					(self cue:)
				)
			)
			(1
				(if (< (ego distanceTo: fChest) 100) (= local5 1))
				(if (not local3)
					(explosiveSound play:)
					(pExplosive init: setCycle: End self)
				else
					(self cue:)
				)
			)
			(2
				(pExplosive dispose:)
				(pSafeDoor setCycle: End self)
			)
			(3
				(if (not local3)
					(if local5 (ego takeDamage: 50))
					(messager say: 3 6 16 0 self 640)
				else
					(self cue:)
				)
			)
			(4
				(if (<= [egoStats 17] 0)
					(EgoDead 16 640)
				else
					(= local4 1)
					(ego get: 0 15)
					(ego setMotion: PolyPath 107 149 self)
				)
			)
			(5
				(ego normalize:)
				(messager say: 6 4 21 1 0 640)
				(self cue:)
			)
			(6
				(pSafeDoor setCycle: Beg self)
			)
			(7
				(Bset 191)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sAlreadyDone of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(pSafeDoor setCycle: End self)
			)
			(1
				(ego setMotion: PolyPath 107 149 self)
			)
			(2
				(ego normalize:)
				(messager say: 6 4 19 1 self 640)
			)
			(3
				(pSafeDoor setCycle: Beg self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenCupboard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch local6
					(1
						(vLeftCupDoor setCycle: End self)
					)
					(2
						(vMidCupDoor setCycle: End self)
					)
					(else 
						(vRightCupDoor setCycle: End self)
					)
				)
			)
			(1
				(ego
					view: 4
					posn: (- (ego x?) 3) (ego y?)
					setLoop: (if (== local6 3) 0 else 1) 1
					setCel: 0
					setCycle: CT 2 1 self
				)
			)
			(2
				(if (Btst 192)
					(messager say: 7 4 14 1 self 640)
				else
					(Bset 192)
					(ego get: 0 6)
					(messager say: 7 4 15 1 self 640)
				)
			)
			(3 (ego setCycle: Beg self))
			(4
				(doorCloseSound play:)
				(ego posn: (+ (ego x?) 3) (ego y?) normalize:)
				(switch local6
					(1
						(vLeftCupDoor setCycle: Beg self)
					)
					(2
						(vMidCupDoor setCycle: Beg self)
					)
					(else 
						(vRightCupDoor setCycle: Beg self)
					)
				)
			)
			(5
				(= local6 0)
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
				(theGame handsOff:)
				(ego trySkill: 20)
				(switch local6
					(1
						(vLeftCupDoor setCycle: End self)
					)
					(2
						(vMidCupDoor setCycle: End self)
					)
					(else 
						(vRightCupDoor setCycle: End self)
					)
				)
			)
			(1
				(switch local6
					(1
						(ego
							setMotion:
								PolyPath
								(vLeftCupDoor approachX?)
								(vLeftCupDoor approachY?)
								self
						)
					)
					(2
						(ego
							setMotion:
								PolyPath
								(vMidCupDoor approachX?)
								(vMidCupDoor approachY?)
								self
						)
					)
					(else 
						(ego
							setMotion:
								PolyPath
								(vRightCupDoor approachX?)
								(vRightCupDoor approachY?)
								self
						)
					)
				)
			)
			(2
				(ego
					view: 4
					posn: (- (ego x?) 3) (ego y?)
					setLoop: (if (== local6 3) 0 else 1) 1
					setCel: 0
					setCycle: CT 2 1 self
				)
			)
			(3
				(if (Btst 192)
					(messager say: 7 4 14 1 self 640)
				else
					(Bset 192)
					(ego get: 0 6)
					(messager say: 7 4 15 1 self 640)
				)
			)
			(4 (ego setCycle: Beg self))
			(5
				(doorCloseSound play:)
				(ego posn: (+ (ego x?) 3) (ego y?) normalize:)
				(switch local6
					(1
						(vLeftCupDoor setCycle: Beg self)
					)
					(2
						(vMidCupDoor setCycle: Beg self)
					)
					(else 
						(vRightCupDoor setCycle: Beg self)
					)
				)
			)
			(6
				(= local6 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance pExplosive of Prop
	(properties
		x 110
		y 118
		priority 174
		fixPriority 1
		view 21
		loop 9
	)
)

(instance pSafeDoor of Prop
	(properties
		noun 17
		modNum 640
		approachX 131
		approachY 148
		x 126
		y 95
		priority 142
		fixPriority 1
		view 698
		loop 8
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(-80
				(curRoom setScript: sOpenTheChest)
			)
			(82
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 11) 0 self)
			)
			(-82
				(curRoom setScript: sOpenTheChest)
			)
			(4
				(if (Btst 191)
					(curRoom setScript: sAlreadyDone)
				else
					(curRoom setScript: sDoTheChest)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pLeftDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 37
		approachY 141
		x -1
		y 141
		z 101
		priority 163
		fixPriority 1
		view 644
		loop 1
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
				(vLeftDoor doVerb: theVerb)
			)
		)
	)
)

(instance pRightDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 263
		approachY 163
		x 265
		y 163
		z 99
		priority 163
		fixPriority 1
		view 644
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local2 0)
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

(instance vLeftCupDoor of Prop
	(properties
		noun 7
		modNum 640
		approachX 164
		approachY 135
		x 127
		y 104
		view 643
		loop 3
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local6 1)
				(curRoom setScript: sCastOpenCupboard)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(4
				(= local6 1)
				(curRoom setScript: sOpenCupboard)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vMidCupDoor of Prop
	(properties
		noun 7
		modNum 640
		approachX 195
		approachY 131
		x 156
		y 103
		view 643
		loop 3
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local6 2)
				(curRoom setScript: sCastOpenCupboard)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(4
				(= local6 2)
				(curRoom setScript: sOpenCupboard)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vRightCupDoor of Prop
	(properties
		noun 7
		modNum 640
		approachX 173
		approachY 133
		x 213
		y 102
		view 643
		loop 4
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local6 3)
				(curRoom setScript: sCastOpenCupboard)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(4
				(= local6 3)
				(curRoom setScript: sOpenCupboard)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vBackDoor of View
	(properties
		noun 1
		modNum 640
		approachX 267
		approachY 163
		x 265
		y 62
		view 644
		loop 2
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local2 0)
				(curRoom setScript: sCastOpenDoor)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(32
				(Bset 214)
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
		approachX 37
		approachY 141
		y 36
		view 644
		loop 3
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
			(32
				(Bset 215)
				(messager say: 1 32 0 1 0 640)
			)
			(42
				(curRoom setScript: sPickLock)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vTheRug of View
	(properties
		noun 33
		modNum 640
		x 302
		y 136
		view 692
		loop 3
	)
)

(instance vCupBoard of View
	(properties
		noun 7
		modNum 640
		approachX 142
		approachY 130
		x 219
		y 94
		view 643
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local6 1)
				(curRoom setScript: sCastOpenCupboard)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(4
				(= local6 1)
				(curRoom setScript: sOpenCupboard)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vCeiling of View
	(properties
		modNum 640
		x 319
		priority 152
		fixPriority 1
		view 690
		loop 1
		signal $4001
	)
)

(instance vLeftCeiling of View
	(properties
		modNum 640
		x 139
		view 690
		loop 1
		cel 1
		signal $4001
	)
)

(instance vSafe of View
	(properties
		noun 17
		modNum 640
		approachX 131
		approachY 148
		x 125
		y 94
		priority 141
		fixPriority 1
		view 698
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(-80
				(curRoom setScript: sOpenTheChest)
			)
			(82
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 11) 0 self)
			)
			(-82
				(curRoom setScript: sOpenTheChest)
			)
			(4
				(if (Btst 191)
					(curRoom setScript: sAlreadyDone)
				else
					(curRoom setScript: sDoTheChest)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vThePainting of View
	(properties
		noun 4
		modNum 640
		x 213
		y 33
		view 698
		loop 1
	)
)

(instance fBowl of Feature
	(properties
		noun 11
		modNum 640
		nsLeft 188
		nsTop 93
		nsRight 208
		nsBottom 102
		sightAngle 180
		x 198
		y 97
	)
)

(instance fChest of Feature
	(properties
		noun 6
		modNum 640
		nsLeft 66
		nsTop 106
		nsRight 111
		nsBottom 74
		sightAngle 180
		approachX 131
		approachY 148
		x 80
		y 74
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(-80
				(curRoom setScript: sOpenTheChest)
			)
			(82
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 11) 0 self)
			)
			(-82
				(curRoom setScript: sOpenTheChest)
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

(instance chestTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (respond)
		(super respond: &rest)
		(if (or (not iconValue) (== iconValue -999))
			(sDoTheChest cue:)
		)
		(return 1)
	)
	
	(method (sayMessage)
		(switch iconValue
			(13
				(theGame handsOn:)
				(if local3
					(self clean:)
					(curRoom setScript: sOpenTheChest)
				else
					(cond 
						(((ScriptID 648 0) init: show: dispose:)
							(= local3 1)
							(self clean:)
							(DisposeScript 648)
							(curRoom setScript: sOpenTheChest)
						)
						((Btst 182) (self clean:) (curRoom setScript: sOpenTheChest))
					)
					(DisposeScript 648)
				)
			)
			(25
				(self clean:)
				(if (== (ego trySkill: 0 325) 1)
					(curRoom setScript: sOpenTheChest)
				else
					(theGame handsOn:)
					(messager say: 3 6 17 1 0 640)
				)
			)
			(26
				(self clean:)
				(curRoom setScript: sOpenTheChest)
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
				(if (ego has: 24) (Btst 242) else 0)
				4
				(not local4)
				26
				(if (and (ego has: 24) [egoStats 9])
					(not local4)
				else
					0
				)
				25
				(not local4)
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

(instance explosiveSound of Sound
	(properties
		number 971
	)
)

(instance doorCloseSound of Sound
	(properties
		number 973
	)
)
