;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40500)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use TPSound)
(use CueMe)
(use Scaler)
(use Polygon)
(use Motion)
(use Actor)

(public
	roLavaCrossing 0
)

(instance voWaterCannon of View
	(properties
		approachX 384
		approachY 316
		x 378
		y 310
		view -25036
		loop 5
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 74)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego setScript: soTorinInspectsCannon)
			)
			(74
				(ego setScript: soTorinShootsCannon)
			)
		)
	)
)

(instance soTorinInspectsCannon of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego nSaveTime: voWaterCannon self)
			)
			(1 (curRoom newRoom: -24936))
		)
	)
)

(instance soTorinShootsCannon of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego nSaveTime: voWaterCannon self)
			)
			(1
				(voWaterCannon dispose:)
				(poBoogleJumps init: setCycle: End self)
			)
			(2
				(poBoogleJumps dispose:)
				(ego hide:)
				(poTorinKneels init: setCycle: End self)
			)
			(3
				(poTorinKneels dispose:)
				(poTorinOperatesCannon init: setCycle: End self)
				(oWaterGun lThumbLoop: -25035)
			)
			(4
				(oHiss lThumbLoop: -25034)
				(poPathForms init: setCycle: End self)
			)
			(5
				(poTorinOperatesCannon dispose:)
				(poTorinRuns init: setCycle: End self)
				(poBoogleRuns init: setCycle: End self)
			)
			(6)
			(7
				(poBoogleRuns dispose:)
				(poPathForms dispose:)
				(poPathMelts init: setCycle: End self)
			)
			(8
				(poPathMelts dispose:)
				(curRoom newRoom: -24836)
			)
		)
	)
)

(instance poTorinKneels of Prop
	(properties
		x 378
		y 310
		view -25036
	)
)

(instance poBoogleJumps of Prop
	(properties
		x 378
		y 310
		view -25036
		loop 1
	)
)

(instance poTorinOperatesCannon of Prop
	(properties
		x 378
		y 310
		view -25036
		loop 2
	)
)

(instance poPathForms of Prop
	(properties
		x 378
		y 310
		view -25036
		loop 3
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 200)
	)
)

(instance poTorinRuns of Prop
	(properties
		x 378
		y 310
		view -25035
	)
)

(instance poBoogleRuns of Prop
	(properties
		x 288
		y 259
		view -25035
		loop 1
	)
)

(instance poPathMelts of Prop
	(properties
		x 378
		y 310
		view -25035
		loop 2
	)
)

(instance oHiss of TPSound
	(properties)
)

(instance oWaterGun of TPSound
	(properties)
)

(instance oMoodMusic of TPSound
	(properties)
)

(instance oPlayMusic of CueMe
	(properties)
	
	(method (cue)
		(music1 pageSize: -25036)
	)
)

(instance roLavaCrossing of TPRoom
	(properties
		picture -25036
		south -25236
	)
	
	(method (init)
		(super init: &rest)
		(oMoodMusic lThumbLoop: -24937 oPlayMusic)
		(music1 pageSize: 0)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						596
						320
						558
						297
						533
						302
						466
						297
						435
						277
						340
						292
						293
						290
						271
						270
						234
						289
						193
						292
						158
						297
						68
						300
						85
						323
					yourself:
				)
		)
		((ScriptID 64899 3) init:)
		(ego init: oPanner: setScaler: Scaler 55 40 306 142)
		(voWaterCannon init:)
		(switch prevRoomNum
			(-24936
				(ego
					posn: (voWaterCannon approachX?) (voWaterCannon approachY?)
					setLoop: 7
				)
				(if ((ScriptID 64017 0) test: 107)
					(ego setScript: soTorinShootsCannon)
				)
			)
			(else 
				(ego posn: 161 311 setLoop: 3)
			)
		)
		(theGame handsOn:)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 0) get: 37))
	)
)
