;;; Sierra Script 1.0 - (do not remove this comment)
(script# 644)
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
	rm644 0
)

(local
	local0
	local1
	local2
	local3
)
(instance rm644 of GloryRm
	(properties
		modNum 640
		picture 640
		east 632
	)
	
	(method (init)
		(switch prevRoomNum
			(631
				(= local2 1)
				(= local0 25)
				(= local1 134)
				(ego posn: 10 127)
			)
			(else 
				(= local0 265)
				(= local1 175)
				(ego posn: 300 152)
			)
		)
		(ego
			init:
			setScaler: Scaler 100 100 189 0
			normalize: (if (== prevRoomNum 632) 1 else 0)
		)
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
						304
						189
						266
						153
						254
						135
						227
						135
						210
						126
						198
						128
						98
						124
						88
						116
						74
						123
						46
						122
						31
						133
						5
						143
						5
						189
						0
						189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 52 130 78 127 233 140 233 156 158 180 140 180 108 180 50 169 42 158
					yourself:
				)
		)
		(super init: &rest)
		((pCandle new:)
			approachX: 72
			approachY: 156
			setLoop: 6 1
			setCel: 2
			posn: 104 107
			setPri: 174
			init:
			setCycle: Fwd
		)
		((pCandle new:)
			approachX: 176
			approachY: 175
			setLoop: 6 1
			setCel: 0
			posn: 149 111
			setPri: 174
			init:
			setCycle: Fwd
		)
		((pCandle new:)
			setLoop: 6 1
			setCel: 1
			posn: 99 9
			setPri: 207
			init:
			setCycle: Fwd
		)
		((pCandle new:)
			setLoop: 6 1
			setCel: 0
			posn: 102 9
			setPri: 207
			init:
			setCycle: Fwd
		)
		((pCandle new:)
			setLoop: 6 1
			setCel: 1
			posn: 108 9
			init:
			setCycle: Fwd
		)
		((pCandle new:)
			setLoop: 6 1
			setCel: 2
			posn: 118 6
			setPri: 207
			init:
			setCycle: Fwd
		)
		((pCandle new:)
			setLoop: 6 1
			setCel: 0
			posn: 133 5
			setPri: 207
			init:
			setCycle: Fwd
		)
		((pCandle new:)
			setLoop: 6 1
			setCel: 1
			posn: 133 9
			setPri: 207
			init:
			setCycle: Fwd
		)
		((pCandle new:)
			setLoop: 6 1
			setCel: 2
			posn: 134 15
			setPri: 207
			init:
			setCycle: Fwd
		)
		((pCandle new:)
			setLoop: 6 1
			setCel: 0
			posn: 151 6
			setPri: 207
			init:
			setCycle: Fwd
		)
		((pCandle new:)
			setLoop: 6 1
			setCel: 1
			posn: 150 14
			setPri: 207
			init:
			setCycle: Fwd
		)
		((pCandle new:)
			setLoop: 6 1
			setCel: 2
			posn: 161 7
			setPri: 207
			init:
			setCycle: Fwd
		)
		((pCandle new:)
			setLoop: 6 1
			setCel: 0
			posn: 117 14
			setPri: 207
			init:
			setCycle: Fwd
		)
		(pLeftSconce
			ignoreActors:
			approachVerbs: 4
			init:
			setCycle: Fwd
		)
		(pRightSconce
			ignoreActors:
			approachVerbs: 4
			init:
			setCycle: Fwd
		)
		(pRightDoor init: approachVerbs: 4 32)
		(rightDoorTeller init: pRightDoor 640 2 155)
		(pLeftDoor init: setPri: 152 approachVerbs: 4 32)
		(leftDoorTeller init: pLeftDoor 640 2 155)
		(vLeftChest ignoreActors: approachVerbs: 4 init:)
		(vRightChest ignoreActors: approachVerbs: 4 init:)
		(vMidChest ignoreActors: approachVerbs: 4 init:)
		(vTheTable ignoreActors: approachVerbs: 4 init:)
		(vRightColumn ignoreActors: init:)
		(vLeftColumn ignoreActors: init:)
		(vChandelier ignoreActors: init:)
		(vLeftDoor ignoreActors: approachVerbs: 4 32 init:)
		(vRightDoor ignoreActors: approachVerbs: 4 32 init:)
		(vRightCandle ignoreActors: approachVerbs: 4 init:)
		(vLeftCandle ignoreActors: approachVerbs: 4 init:)
		(vGryCup ignoreActors: approachVerbs: 4 init:)
		(vBlueCup ignoreActors: approachVerbs: 4 init:)
		(vGryVace ignoreActors: approachVerbs: 4 init:)
		(vTheChest ignoreActors: approachVerbs: 4 init:)
		(vTheRug ignoreActors: init:)
		(fCresset1 init: approachVerbs: 4)
		(fCresset2 init: approachVerbs: 4)
		(fHorn init: approachVerbs: 4)
		(fChair1 init: approachVerbs: 4)
		(fChair2 init: approachVerbs: 4)
		(fChair3 init: approachVerbs: 4)
		(curRoom setScript: sComeOnIn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 0 1 0 0 0 644)
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
				(= ticks 24)
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
				(pLeftDoor setPri: 75)
				(pRightDoor setPri: 75)
				(ego setMotion: PolyPath local0 local1 self)
			)
			(3
				(if local2
					(pLeftDoor setCycle: Beg self)
				else
					(pRightDoor setCycle: Beg self)
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
				(if (> (ego x?) 60)
					(ego setLoop: 0 1)
				else
					(ego setLoop: 1 1)
				)
				(ego view: 4 setCel: 0 setCycle: CT 2 1 self)
			)
			(1
				(if (< (ego x?) 60)
					(messager say: 2 155 27 1 self 640)
				else
					(messager say: 2 155 29 1 self 640)
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
				(if (< (ego x?) 160)
					(= local2 1)
					(if (not (Btst 210))
						(messager say: 1 4 3 1 0 640)
						(squeakSound play:)
					)
					(pLeftDoor setCycle: End self)
				else
					(= local2 0)
					(if (not (Btst 211))
						(messager say: 1 4 3 1 0 640)
						(squeakSound play:)
					)
					(pRightDoor setCycle: End self)
				)
			)
			(1
				(squeakSound dispose:)
				(doorSound dispose:)
				(doorCloseSound dispose:)
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
					(curRoom newRoom: 631)
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
				(if local2
					(if (not (Btst 210))
						(messager say: 1 4 3 1 0 640)
						(squeakSound play:)
					)
					(pLeftDoor setCycle: End self)
				else
					(if (not (Btst 211))
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
					(curRoom newRoom: 631)
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
					((Btst 210) (self cue:))
					((Btst 211) (self cue:))
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
					(curRoom newRoom: 631)
				else
					(curRoom newRoom: (curRoom east?))
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
				(theGame handsOff:)
				(switch local3
					(1
						(vLeftChest setCycle: End self)
					)
					(2
						(vMidChest setCycle: End self)
					)
					(else 
						(vRightChest setCycle: End self)
					)
				)
			)
			(1
				(if (== local3 1)
					(ego setLoop: 1 1)
				else
					(ego setLoop: 0 1)
				)
				(ego
					view: 4
					posn: (- (ego x?) 3) (ego y?)
					setCel: 0
					setCycle: CT 2 1 self
				)
			)
			(2
				(messager say: 7 4 14 1 self 640)
			)
			(3 (ego setCycle: Beg self))
			(4
				(doorCloseSound setLoop: 1 1 play:)
				(ego posn: (+ (ego x?) 3) (ego y?) normalize:)
				(switch local3
					(1
						(vLeftChest setCycle: Beg self)
					)
					(2
						(vMidChest setCycle: Beg self)
					)
					(else 
						(vRightChest setCycle: Beg self)
					)
				)
			)
			(5
				(= local3 0)
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
				(switch local3
					(1
						(vLeftChest setCycle: End self)
					)
					(2
						(vMidChest setCycle: End self)
					)
					(else 
						(vRightChest setCycle: End self)
					)
				)
			)
			(1
				(switch local3
					(1
						(ego
							setMotion: PolyPath (vLeftChest approachX?) (vLeftChest approachY?) self
						)
					)
					(2
						(ego
							setMotion: PolyPath (vMidChest approachX?) (vMidChest approachY?) self
						)
					)
					(else 
						(ego
							setMotion:
								PolyPath
								(vRightChest approachX?)
								(vRightChest approachY?)
								self
						)
					)
				)
			)
			(2
				(if (== local3 1)
					(ego setLoop: 1 1)
				else
					(ego setLoop: 0 1)
				)
				(ego
					view: 4
					posn: (- (ego x?) 3) (ego y?)
					setCel: 0
					setCycle: CT 2 1 self
				)
			)
			(3
				(messager say: 7 4 14 1 self 640)
			)
			(4 (ego setCycle: Beg self))
			(5
				(doorCloseSound setLoop: 1 1 play:)
				(ego posn: (+ (ego x?) 3) (ego y?) normalize:)
				(switch local3
					(1
						(vLeftChest setCycle: Beg self)
					)
					(2
						(vMidChest setCycle: Beg self)
					)
					(else 
						(vRightChest setCycle: Beg self)
					)
				)
			)
			(6
				(= local3 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance vLeftChest of Prop
	(properties
		noun 7
		modNum 640
		approachX 149
		approachY 127
		x 109
		y 96
		view 643
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local3 1)
				(curRoom setScript: sCastOpenCupboard)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(4
				(= local3 1)
				(curRoom setScript: sOpenCupboard)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vRightChest of Prop
	(properties
		noun 7
		modNum 640
		approachX 157
		approachY 127
		x 195
		y 98
		view 643
		loop 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local3 3)
				(curRoom setScript: sCastOpenCupboard)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(4
				(= local3 3)
				(curRoom setScript: sOpenCupboard)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vMidChest of Prop
	(properties
		noun 7
		modNum 640
		approachX 135
		approachY 126
		x 163
		y 98
		view 643
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local3 2)
				(curRoom setScript: sCastOpenCupboard)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(4
				(= local3 2)
				(curRoom setScript: sOpenCupboard)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pRightSconce of Prop
	(properties
		noun 21
		modNum 640
		approachX 189
		approachY 128
		x 191
		y 63
		priority 130
		fixPriority 1
		view 699
		loop 7
		cel 2
		detailLevel 2
	)
)

(instance pLeftSconce of Prop
	(properties
		noun 21
		modNum 640
		approachX 88
		approachY 116
		x 99
		y 63
		view 699
		loop 7
		cel 5
		detailLevel 2
	)
)

(instance pRightDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 269
		approachY 165
		x 271
		y 165
		z 94
		priority 196
		fixPriority 1
		view 644
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(32
				(Bset 211)
				(messager say: 1 32 0 1 0 640)
			)
			(42
				(curRoom setScript: sPickLock)
			)
			(-80
				(= local2 0)
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

(instance pLeftDoor of Prop
	(properties
		noun 1
		modNum 640
		approachX 40
		approachY 128
		y 128
		z 97
		priority 31
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
			(32
				(Bset 210)
				(messager say: 1 32 0 1 0 640)
			)
			(42
				(curRoom setScript: sPickLock)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pCandle of Prop
	(properties
		view 699
		signal $4001
		detailLevel 2
	)
)

(instance vTheTable of View
	(properties
		noun 29
		modNum 640
		approachX 72
		approachY 156
		x 61
		y 126
		z 29
		priority 163
		fixPriority 1
		view 681
		loop 2
		signal $1001
	)
)

(instance vRightColumn of View
	(properties
		modNum 640
		x 216
		y -6
		priority 75
		fixPriority 1
		view 685
		loop 2
	)
)

(instance vLeftColumn of View
	(properties
		modNum 640
		x 43
		y -1
		priority 86
		fixPriority 1
		view 685
	)
)

(instance vChandelier of View
	(properties
		noun 20
		modNum 640
		x 182
		priority 152
		fixPriority 1
		view 681
		loop 1
	)
)

(instance vLeftDoor of View
	(properties
		noun 1
		modNum 640
		approachX 40
		approachY 128
		y 128
		z 99
		priority 29
		fixPriority 1
		view 644
		loop 3
		signal $4001
	)
	
	(method (doVerb theVerb)
		(pLeftDoor doVerb: theVerb)
	)
)

(instance vRightCandle of View
	(properties
		noun 26
		modNum 640
		approachX 176
		approachY 175
		x 161
		y 127
		z 16
		priority 174
		fixPriority 1
		view 699
		cel 2
	)
)

(instance vLeftCandle of View
	(properties
		noun 26
		modNum 640
		approachX 72
		approachY 156
		x 116
		y 128
		z 20
		priority 174
		fixPriority 1
		view 699
		cel 2
	)
)

(instance vGryCup of View
	(properties
		noun 23
		modNum 640
		approachX 72
		approachY 156
		x 106
		y 127
		z 7
		priority 174
		fixPriority 1
		view 699
		loop 2
	)
)

(instance vBlueCup of View
	(properties
		noun 24
		modNum 640
		approachX 176
		approachY 175
		x 177
		y 128
		z 3
		priority 174
		fixPriority 1
		view 699
		loop 3
		cel 3
	)
)

(instance vGryVace of View
	(properties
		noun 11
		modNum 640
		approachX 176
		approachY 175
		x 151
		y 129
		z 7
		priority 174
		fixPriority 1
		view 699
		loop 2
		cel 2
	)
)

(instance vTheChest of View
	(properties
		noun 7
		modNum 640
		approachX 189
		approachY 128
		x 102
		y 88
		view 643
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(= local3 3)
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

(instance vRightDoor of View
	(properties
		noun 1
		modNum 640
		approachX 269
		approachY 165
		x 270
		y 165
		z 97
		priority 68
		fixPriority 1
		view 644
		loop 2
		signal $4001
	)
	
	(method (doVerb theVerb)
		(pRightDoor doVerb: theVerb)
	)
)

(instance vTheRug of View
	(properties
		noun 33
		modNum 640
		x 11
		y 125
		fixPriority 1
		view 692
	)
)

(instance fCresset1 of Feature
	(properties
		noun 22
		modNum 640
		nsLeft 92
		nsTop 57
		nsRight 114
		nsBottom 90
		sightAngle 180
		approachX 88
		approachY 166
		x 103
		y 73
	)
)

(instance fCresset2 of Feature
	(properties
		noun 22
		modNum 640
		nsLeft 188
		nsTop 60
		nsRight 202
		nsBottom 86
		sightAngle 180
		approachX 189
		approachY 128
		x 195
		y 73
	)
)

(instance fHorn of Feature
	(properties
		noun 25
		modNum 640
		nsLeft 165
		nsTop 125
		nsRight 176
		nsBottom 135
		sightAngle 180
		approachX 176
		approachY 175
		x 170
		y 130
	)
)

(instance fChair1 of Feature
	(properties
		noun 27
		modNum 640
		nsLeft 68
		nsTop 97
		nsRight 92
		nsBottom 154
		sightAngle 180
		approachX 72
		approachY 156
		x 80
		y 125
	)
)

(instance fChair2 of Feature
	(properties
		noun 27
		modNum 640
		nsLeft 189
		nsTop 103
		nsRight 215
		nsBottom 158
		sightAngle 180
		approachX 204
		approachY 165
		x 202
		y 130
	)
)

(instance fChair3 of Feature
	(properties
		noun 27
		modNum 640
		nsLeft 123
		nsTop 122
		nsRight 155
		nsBottom 175
		sightAngle 180
		approachX 176
		approachY 175
		x 139
		y 148
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
					(messager say: 3 6 9 1 0 640)
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
			(else  (super sayMessage:))
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
					(messager say: 3 6 9 1 0 640)
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
			(else  (super sayMessage:))
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
