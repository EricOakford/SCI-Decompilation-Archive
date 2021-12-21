;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64889)
(include sci.sh)
(use Main)
(use TimePauser)
(use Cast)
(use NewGame)
(use Plane)
(use Feature)


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
		nScreenSizeX 0
	)
	
	(method (init)
		(proc64888_7)
		(proc64888_2)
		(TimePauser init:)
		(super init: &rest)
		(self setPri: (+ (GetHighPlanePri) 1))
		(self addCast: (Cast new:))
		(UpdatePlane self)
	)
	
	(method (dispose)
		(RestorePlane)
		(TimePauser dispose:)
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
		(self nScrollMaxX: -1)
		(self setSpeedFraction: -1)
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
		oUpArrow 0
		nScrollTime 0
		nThumbOffset 0
		nPhysRange 0
		invSlotsX 0
	)
	
	(method (init)
		(super init: &rest)
		(= invSlotsX 0)
		(self setSpeedFraction: (ScriptID 64006 0))
	)
	
	(method (handleEvent event &tmp temp0 temp1)
		(return
			(cond 
				((== invSlotsX 1)
					(event globalize:)
					(= temp0 (- (event x?) nThumbOffset))
					(= temp1 (- (event y?) nPhysRange))
					(plane moveTo: (+ oUpArrow temp0) (+ nScrollTime temp1))
					(UpdatePlane plane)
					(if (== (event type?) evMOUSERELEASE)
						(gOEventHandler screenLocX: self)
						(= invSlotsX 0)
					)
					(event claimed: 1)
					(return 1)
				)
				(
					(and
						(self onMe: event)
						(== (event type?) evMOUSEBUTTON)
					)
					(gOEventHandler screenLocY: self)
					(= invSlotsX 1)
					(event globalize:)
					(= nThumbOffset (event x?))
					(= nPhysRange (event y?))
					(= oUpArrow (plane left:))
					(= nScrollTime (plane top?))
					(event claimed: 1)
					(return 1)
				)
				(else (return (super handleEvent: event &rest)))
			)
		)
	)
	
	(method (nUserRange theNsBottom)
		(if (not plane) (MonoOut LOOKUP_ERROR) (return))
		(= nsTop (= nsLeft 0))
		(= nsRight (- (plane right:) (plane left:)))
		(= nsBottom theNsBottom)
	)
)
