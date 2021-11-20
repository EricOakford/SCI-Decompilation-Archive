;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64950)
(include sci.sh)
(use Main)
(use PolyPath)
(use Polygon)
(use System)


(class CueObj of Script
	(properties
		scratch 0
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		theVerb 0
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(ego
					setHeading: (GetAngle (ego x?) (ego y?) (client x?) (client y?)) self
				)
				(theDoits add: self)
			)
			(2 (= cycles 3))
			(3
				(theDoits delete: self)
				(if
					(not
						(if (and client (client actions?))
							((client actions?) doVerb: theVerb)
						)
					)
					(client doVerb: theVerb)
				)
				(= state 0)
			)
		)
	)
)

(class Feature of Obj
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
	
	(method (init param1)
		(self initialize: (if argc param1 else 0))
		(if (self respondsTo: #view)
			(cast add: self)
		else
			(features add: self)
		)
	)
	
	(method (dispose)
		(self setPolygon: 0)
		(if actions (actions dispose:) (= actions 0))
		(if onMeCheck (onMeCheck dispose:) (= onMeCheck 0))
		(features delete: self)
		(super dispose:)
	)
	
	(method (initialize param1)
		(cond 
			((and argc param1) (self perform: param1))
			(ftrInitializer (self perform: ftrInitializer))
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(cond 
			((event claimed?) (return 1))
			(
				(and
					(& (event type?) evVERB)
					(self onMe: event)
					(self isNotHidden:)
				)
				(CueObj
					state: 0
					cycles: 0
					client: self
					theVerb: (event message?)
				)
				(= temp0 (event claimed: 1))
				(if
					(and
						(user canControl:)
						(& (ego state?) $0002)
						(>
							(GetDistance (ego x?) (ego y?) approachX approachY)
							approachDist
						)
						approachCode
						(& _approachVerbs (approachCode doit: (event message?)))
					)
					(ego
						setMotion: PolyPath approachX (+ (ego z?) approachY) CueObj
					)
				else
					(ego setMotion: 0)
					(if (self facingMe:) (CueObj changeState: 3))
				)
			)
		)
		(return temp0)
	)
	
	(method (doVerb theVerb &tmp temp0 temp1)
		(= temp0 (if doVerbCode else dftDoVerb))
		(if (== modNum -1) (= modNum curRoomNum))
		(if
		(and msgType (Message msgGET modNum noun theVerb case 1))
			(messager say: noun theVerb case 0 0 modNum)
		else
			(temp0 doit: theVerb self)
		)
	)
	
	(method (notFacing &tmp temp0)
		(ego setMotion: 0)
		(CueObj client: self state: 0 cycles: 0 cue:)
	)
	
	(method (facingMe theTheEgo &tmp theEgo temp1)
		(cond 
			(argc (= theEgo theTheEgo))
			((cast contains: ego) (= theEgo ego))
			(else (return 1))
		)
		(if
			(>
				(= temp1
					(Abs
						(-
							(GetAngle (theEgo x?) (theEgo y?) x y)
							(theEgo heading?)
						)
					)
				)
				180
			)
			(= temp1 (- 360 temp1))
		)
		(return
			(if (<= temp1 sightAngle)
				(return 1)
			else
				(if (!= sightAngle 26505)
					(if (& (theEgo signal?) $0800)
						(CueObj client: self state: 2 cycles: 0 cue:)
					else
						(self notFacing:)
					)
				)
				(return 0)
			)
		)
	)
	
	(method (isNotHidden)
		(return 1)
	)
	
	(method (onMe theObjOrX theY &tmp theObjOrXX theObjOrXY)
		(if (== argc 1)
			(= theObjOrXX (theObjOrX x?))
			(= theObjOrXY (theObjOrX y?))
		else
			(= theObjOrXX theObjOrX)
			(= theObjOrXY theY)
		)
		(cond 
			((not onMeCheck)
				(if
					(or
						(not (if (or nsLeft nsRight nsTop) else nsBottom))
						(and
							(<= nsLeft theObjOrXX)
							(<= theObjOrXX nsRight)
							(<= nsTop theObjOrXY)
							(<= theObjOrXY nsBottom)
						)
					)
					1
				else
					0
				)
			)
			((onMeCheck isKindOf: List) (onMeCheck firstTrue: #onMe theObjOrXX theObjOrXY))
			(else (onMeCheck onMe: theObjOrXX theObjOrXY))
		)
	)
	
	(method (approachVerbs param1 &tmp temp0 temp1)
		(= _approachVerbs 0)
		(if (and argc approachCode [param1 0])
			(= temp0 0)
			(while (< temp0 argc)
				(= temp1 (approachCode doit: [param1 temp0]))
				(self _approachVerbs: (| (self _approachVerbs?) temp1))
				(++ temp0)
			)
		)
	)
	
	(method (setName theName param2 &tmp temp0)
		(if (== theName 2)
			(= name ((= temp0 (param2 new:)) data?))
			(temp0 data: 0)
			(temp0 dispose:)
		else
			(= name theName)
		)
	)
	
	(method (setPolygon theOnMeCheck)
		(if onMeCheck (onMeCheck dispose:) (= onMeCheck 0))
		(cond 
			((or (not argc) (== theOnMeCheck 0)) (return))
			((== argc 1) (= onMeCheck [theOnMeCheck 0]))
			(else
				(= onMeCheck (List new:))
				(onMeCheck add: theOnMeCheck &rest)
			)
		)
	)
	
	(method (createPoly param1)
		(if onMeCheck (onMeCheck dispose:))
		(= onMeCheck (Polygon new:))
		(onMeCheck init: param1 &rest type: 0)
	)
)

(instance dftDoVerb of Code
	(properties)
	
	(method (doit)
		(return 1)
	)
)

(class Actions of Code
	(properties
		scratch 0
	)
	
	(method (doVerb)
		(return 0)
	)
)
