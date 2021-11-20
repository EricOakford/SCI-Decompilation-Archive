;;; Sierra Script 1.0 - (do not remove this comment)
(script# 622)
(include sci.sh)
(use Main)
(use GloryRm)
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
	rm622 0
)

(local
	local0
	local1
)
(instance rm622 of GloryRm
	(properties
		modNum 640
		picture 640
		east 642
	)
	
	(method (init)
		(ego init: setScaler: Scaler 137 83 189 95 normalize:)
		(switch prevRoomNum
			(642
				(ego setPri: 51 posn: 300 149 hide:)
			)
			(640
				(ego setPri: 51 posn: 12 121)
			)
			(else 
				(ego setPri: 51 posn: 293 149)
			)
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
						306
						189
						247
						130
						124
						123
						111
						137
						41
						108
						45
						97
						101
						97
						110
						91
						53
						91
						33
						99
						34
						119
						16
						132
						107
						151
						88
						169
						0
						166
					yourself:
				)
		)
		(pDoor1 init: approachVerbs: 4)
		(pDoor2 init: approachVerbs: 4)
		(pDoor3 init: approachVerbs: 4)
		(vLeftSteps approachVerbs: 4 ignoreActors: init:)
		(vCupboard approachVerbs: 4 ignoreActors: init:)
		(vCeiling1 approachVerbs: 4 ignoreActors: init:)
		(vCeiling2 approachVerbs: 4 ignoreActors: init:)
		(vThePainting approachVerbs: 4 ignoreActors: init:)
		(fBowl init: approachVerbs: 4)
		(fSteps init: approachVerbs: 4)
		((ScriptID 645 0) init:)
	)
	
	(method (dispose)
		(if script (script dispose:))
		(DisposeScript 645)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 0 1 0 0 0 622)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance sOpenUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== local0 1)
					(ego setMotion: PolyPath 195 127 self)
				else
					(self cue:)
				)
			)
			(1
				(myDoorSound play:)
				(switch local0
					(1 (pDoor1 setCycle: End self))
					(2 (pDoor2 setCycle: End self))
					(3 (pDoor3 setCycle: End self))
					(else  (self cue:))
				)
			)
			(2
				(ego
					view: 4
					posn: (- (ego x?) 3) (ego y?)
					setLoop: (if (== local0 1) 1 else 0) 1
					setCel: 0
					setCycle: End self
				)
			)
			(3
				(messager say: 7 4 14 0 self 640)
			)
			(4 (ego setCycle: Beg self))
			(5
				(ego posn: (+ (ego x?) 3) (ego y?) normalize:)
				(switch local0
					(1 (pDoor1 setCycle: Beg self))
					(2 (pDoor2 setCycle: Beg self))
					(3 (pDoor3 setCycle: Beg self))
				)
			)
			(6
				(shutSound play:)
				(= local0 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCastOpen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego trySkill: 20)
				(myDoorSound play:)
				(switch local0
					(1 (pDoor1 setCycle: End self))
					(2 (pDoor2 setCycle: End self))
					(3 (pDoor3 setCycle: End self))
					(else  (self cue:))
				)
			)
			(1
				(switch local0
					(1
						(ego setMotion: PolyPath 195 127 self)
					)
					(2
						(ego setMotion: PolyPath 204 128 self)
					)
					(3
						(ego setMotion: PolyPath 172 126 self)
					)
					(else  (self cue:))
				)
			)
			(2
				(ego
					view: 4
					posn: (- (ego x?) 3) (ego y?)
					setLoop: (if (== local0 1) 1 else 0) 1
					setCel: 0
					setCycle: End self
				)
			)
			(3
				(messager say: 7 4 14 0 self 640)
			)
			(4 (ego setCycle: Beg self))
			(5
				(ego posn: (+ (ego x?) 3) (ego y?) normalize:)
				(switch local0
					(1 (pDoor1 setCycle: Beg self))
					(2 (pDoor2 setCycle: Beg self))
					(3 (pDoor3 setCycle: Beg self))
				)
			)
			(6
				(shutSound play:)
				(= local0 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance pDoor1 of Prop
	(properties
		noun 7
		modNum 640
		approachX 195
		approachY 127
		x 153
		y 99
		view 643
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= local0 1)
				(curRoom setScript: sOpenUp)
			)
			(80
				(= local0 1)
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(-80
				(curRoom setScript: sCastOpen)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pDoor2 of Prop
	(properties
		noun 7
		modNum 640
		approachX 204
		approachY 128
		x 237
		y 102
		view 643
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= local0 2)
				(curRoom setScript: sOpenUp)
			)
			(80
				(= local0 2)
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(-80
				(curRoom setScript: sCastOpen)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pDoor3 of Prop
	(properties
		noun 7
		modNum 640
		approachX 172
		approachY 126
		x 211
		y 100
		view 643
		loop 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= local0 3)
				(curRoom setScript: sOpenUp)
			)
			(80
				(= local0 3)
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(-80
				(curRoom setScript: sCastOpen)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vLeftSteps of View
	(properties
		noun 9
		modNum 640
		y 9
		z -80
		priority 75
		fixPriority 1
		view 693
	)
)

(instance vThePainting of View
	(properties
		noun 4
		modNum 640
		approachX 139
		approachY 125
		x 150
		y 34
		view 698
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(cond 
				((Btst 178) (messager say: 3 6 11 1 0 640))
				((== heroType 2) (Bset 178) (ego get: 0 6) (messager say: 3 6 10 1 0 640))
				(else (messager say: 3 6 12 1 0 640))
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance vCupboard of View
	(properties
		noun 7
		modNum 640
		approachX 163
		approachY 126
		x 147
		y 91
		view 643
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= local0 1)
				(curRoom setScript: sOpenUp)
			)
			(80
				(= local0 1)
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(-80
				(curRoom setScript: sCastOpen)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vCeiling1 of View
	(properties
		view 690
	)
)

(instance vCeiling2 of View
	(properties
		x 180
		view 690
		cel 1
	)
)

(instance fBowl of Feature
	(properties
		noun 11
		modNum 640
		nsLeft 157
		nsTop 89
		nsRight 179
		nsBottom 98
		sightAngle 180
		approachX 167
		approachY 127
		x 168
		y 93
	)
)

(instance fSteps of Feature
	(properties
		noun 8
		modNum 640
		nsTop 107
		nsRight 110
		nsBottom 160
		sightAngle 180
		x 40
		y 160
	)
)

(instance shutSound of Sound
	(properties
		number 960
	)
)

(instance myDoorSound of Sound
	(properties
		number 972
	)
)
