;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64889)
(include sci.sh)
(use Main)
(use TimePauser)
(use NewGame)
(use Plane)
(use CueObj)
(use System)


(class ModalPlane of Plane
	(properties
		scratch 0
		resX -1
		resY -1
		left 0
		top 0
		right 0
		bottom 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		vanishingX 0
		vanishingY 0
		coordType 0
		picture -1
		style $0000
		priority -1
		back 0
		bitmap 0
		casts 0
		mirrored 0
		oMyFeatures 0
	)
	
	(method (init)
		(AddPlane)
		(proc64888_2)
		(TimePauser init:)
		((ScriptID 64000 0) disable:)
		(super init: &rest)
		(self setPri: (+ (GetHighPlanePri) 1))
		(self addCast: (Cast new:))
		(UpdatePlane self)
	)
	
	(method (dispose)
		(RestorePlane)
		(TimePauser dispose:)
		((ScriptID 64000 0) enable:)
		(super dispose: &rest)
	)
)

(class OpaqueFeature of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
	)
	
	(method (init)
		(super init: &rest)
		(self myPriority: -1)
		(self forceCursor: -1)
	)
	
	(method (handleEvent event)
		(event claimed: 1)
		(return 1)
	)
	
	(method (doVerb)
	)
)

(class MoveFeature of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		nInitPlaneX 0
		nInitPlaneY 0
		nInitCursorX 0
		nInitCursorY 0
		bImAHog 0
	)
	
	(method (init)
		(super init: &rest)
		(= bImAHog 0)
		(self forceCursor: (ScriptID 64006 0))
	)
	
	(method (handleEvent event &tmp temp0 temp1)
		(return
			(cond 
				((== bImAHog 1)
					(event globalize:)
					(= temp0 (- (event x?) nInitCursorX))
					(= temp1 (- (event y?) nInitCursorY))
					(plane
						moveTo: (+ nInitPlaneX temp0) (+ nInitPlaneY temp1)
					)
					(UpdatePlane plane)
					(if (== (event type?) evMOUSERELEASE)
						(self stopHogging:)
					)
					(event claimed: 1)
					(return 1)
				)
				(
					(and
						(self onMe: event)
						(== (event type?) evMOUSEBUTTON)
					)
					(gOEventHandler registerEventHog: self)
					(= bImAHog 1)
					(event globalize:)
					(= nInitCursorX (event x?))
					(= nInitCursorY (event y?))
					(= nInitPlaneX (plane left:))
					(= nInitPlaneY (plane top?))
					(event claimed: 1)
					(return 1)
				)
				(else (return (super handleEvent: event &rest)))
			)
		)
	)
	
	(method (stopHogging)
		(gOEventHandler unregisterEventHog: self)
		(= bImAHog 0)
	)
	
	(method (makeTopBorder theNsBottom)
		(if (not plane)
			(MonoOut
				{attempt to call makeTopBorder on MoveFeature with uninited plane. DJM, modalpl.sc}
			)
			(return)
		)
		(= nsTop (= nsLeft 0))
		(= nsRight (- (plane right:) (plane left:)))
		(= nsBottom theNsBottom)
	)
)
