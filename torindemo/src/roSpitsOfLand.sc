;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40300)
(include sci.sh)
(use Main)
(use ScrollExit)
(use TPRoom)
(use TPScript)
(use Scaler)
(use PolyPath)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	roSpitsOfLand 0
)

(local
	local0
)
(instance oSpitsScrollPlane of TorScrollPlane
	(properties
		priority 20
	)
	
	(method (nSeq)
		(AddPicAt self -25236 0 0)
		(AddPicAt self -25235 632 0)
		(AddPicAt self -25234 1264 0)
	)
)

(instance voWrench of Prop
	(properties
		x 1446
		y 128
		view -25236
	)
	
	(method (init)
		(super init: &rest)
		(foWrench init:)
		(self setCycle: Fwd)
	)
	
	(method (dispose)
		(foWrench dispose:)
		(super dispose: &rest)
	)
)

(instance foWrench of Feature
	(properties
		nsLeft 1436
		nsTop 120
		nsRight 1456
		nsBottom 135
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(ego setScript: soTakeWrench)
	)
)

(instance soTakeWrench of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 1456 135 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 270 self)
			)
			(2
				(ego get: ((ScriptID 64001 0) get: 37))
				(voWrench dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance foWaterCannon of Feature
	(properties
		nsLeft 1757
		nsTop 52
		nsRight 1893
		nsBottom 68
	)
	
	(method (init)
		(super init: &rest)
		(theDoits add: oTestNearCannon)
	)
)

(instance oTestNearCannon of Code
	(properties)
	
	(method (doit)
		(if (foWaterCannon onMe: ego) (curRoom newRoom: -25036))
	)
)

(instance oLavaDetector of Code
	(properties)
	
	(method (doit)
		(return
			(if (Bitmap 12 local0 (ego x?) (ego y?))
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance roSpitsOfLand of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(gOEventHandler bHasFF: (ScriptID 64010 1))
		(= plane
			(oSpitsScrollPlane
				oMainPlane: 0
				addBoogleFeature: 0
				init: 1896 (thePlane doDouble:)
				yourself:
			)
		)
		(theMusic pageSize: -25236)
		(voWrench init:)
		(ego
			init:
			oPanner:
			setScaler: Scaler 35 20 297 45
			sayNoSave: oLavaDetector
		)
		(theGame handsOn:)
		(switch prevRoomNum
			(-25036
				(ego posn: 1843 72)
				(plane fadeRel: 1264 0)
			)
			(else 
				(ego posn: 1023 299)
				(plane fadeRel: 632 0)
			)
		)
		(foWaterCannon init:)
		(= local0 (Bitmap 9 -25236 1 0 0 0))
	)
	
	(method (dispose)
		(gOEventHandler scriptId: (ScriptID 64010 1))
		(super dispose: &rest)
	)
	
	(method (intoPouch)
	)
)
