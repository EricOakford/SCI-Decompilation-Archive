;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64950)
(include game.sh)
(use Main)
(use L7Talk)
(use GenDialog)
(use FeatureInfo)
(use Plane)
(use Print)
(use PolyPath)
(use Polygon)
(use Actor)
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

(class Feature of Object
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
	
	(method (init thePlane_2 param2)
		(self initialize: (if (> argc 1) param2 else 0))
		(cond 
			(argc
				(if (not (thePlane_2 isKindOf: Plane))
					(Prints
						{attempt to init feature with non-plane. Feature.sc DJM}
					)
				)
				(= plane thePlane_2)
			)
			((and curRoom (curRoom plane?)) (= plane (curRoom plane?)))
			(else (= plane thePlane))
		)
		((plane oMyFeatures?) add: self)
	)
	
	(method (dispose)
		(self setPolygon: 0)
		(if actions (actions dispose:) (= actions 0))
		(if onMeCheck (onMeCheck dispose:) (= onMeCheck 0))
		(features delete: self)
		(if (and plane (plane oMyFeatures?))
			((plane oMyFeatures?) delete: self)
		)
		(if gOEventHandler (gOEventHandler notifyDispose: self))
		(if scratch (scratch dispose:) (= scratch 0))
		(super dispose:)
	)
	
	(method (initialize param1)
		(if (not scratch) (= scratch (FeatureInfo new:)))
		(cond 
			((and argc param1) (self perform: param1))
			(ftrInitializer (self perform: ftrInitializer))
		)
	)
	
	(method (handleEvent event &tmp temp0 temp1)
		(if (!= (event plane?) plane) (event localize: plane))
		(cond 
			((event claimed?) (return 1))
			(actions
				(event claimed: 1)
				(if (& (event type?) mouseUp)
					(gOEventHandler unregisterEventHog: self)
					(= actions 0)
					(if (not (self onMe: event)) (return 1))
					(= temp1 0)
					(if
						(and
							(not (self forceCursor:))
							(==
								(= temp1 ((ScriptID 64038 0) handleFeature: self))
								-1
							)
						)
						(return
							(==
								(= temp1 ((ScriptID 64038 0) handleFeature: self))
								-1
							)
						)
					)
					(CueObj state: 0 cycles: 0 client: self theVerb: temp1)
					(if
						(and
							(user canControl:)
							(& (ego state?) $0002)
							(>
								(GetDistance (ego x?) (ego y?) approachX approachY)
								approachDist
							)
							approachCode
							(& _approachVerbs (approachCode doit: gVerb))
						)
						(ego
							setMotion: PolyPath approachX (+ (ego z?) approachY) CueObj
						)
					else
						(ego setMotion: 0)
						(if (self facingMe:) (CueObj changeState: 3))
					)
				)
				(return (event claimed: 1))
			)
			(
				(and
					(self onMe: event)
					(self isNotHidden:)
					(or (self forceCursor:) (self getHotspotVerbList:))
				)
				(event claimed: 1)
				(if
					(and
						(& (event type?) mouseDown)
						(not (event modifiers?))
					)
					(gOEventHandler registerEventHog: self)
					(= actions 1)
				)
				(return 1)
			)
		)
		(return 0)
	)
	
	(method (doVerb theVerb &tmp [temp0 2] temp2 temp3)
		(if (== modNum -1) (= modNum curRoomNum))
		(cond 
			(
				(and
					(!= theVerb 12)
					(!= theVerb 112)
					(!= theVerb 70)
					msgType
					(Message 0 modNum noun theVerb case 1)
				)
				(messager say: noun theVerb case 0 0 modNum)
			)
			((proc64040_1 theVerb)
				(if (= temp2 (GetNumMessages modNum noun 70 case))
					(= temp3 (proc64896_11 1 temp2))
					(messager say: noun 70 case temp3 0 modNum)
				else
					(MonoOut {No default topics for object %s (%d)} name self)
				)
			)
			((= temp2 (GetNumMessages 16 0 theVerb 0))
				(= temp3 (proc64896_11 1 temp2))
				(messager say: 0 theVerb 0 temp3 0 16)
			)
			((= temp2 (GetNumMessages 16 0 69 0))
				(= temp3 (proc64896_11 1 temp2))
				(messager say: 0 69 0 temp3 0 16)
			)
			(else (MonoOut {No default verb handler messages}))
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
	
	(method (addHotspotVerb &tmp scratchHotspotVerbList)
		(if (not scratch)
			(MonoOut {Tried to addHotspotVerb to non-inited feature})
			(return 0)
		)
		(if
			(not
				(= scratchHotspotVerbList (scratch hotspotVerbList?))
			)
			(scratch
				hotspotVerbList: (= scratchHotspotVerbList (Set new:))
			)
		)
		(return (scratchHotspotVerbList add: &rest))
	)
	
	(method (deleteHotspotVerb &tmp scratchHotspotVerbList)
		(if (not scratch)
			(MonoOut
				{Tried to deleteHotspotVerb of non-inited feature}
			)
			(return 0)
		)
		(return
			(if
			(= scratchHotspotVerbList (scratch hotspotVerbList?))
				(scratchHotspotVerbList delete: &rest)
			else
				0
			)
		)
	)
	
	(method (testHotspotVerb param1 &tmp scratchHotspotVerbList)
		(if (not scratch)
			(MonoOut {Tried to testHotspotVerb of non-inited feature})
			(return 0)
		)
		(return
			(if
			(= scratchHotspotVerbList (scratch hotspotVerbList?))
				(return (scratchHotspotVerbList contains: param1))
			else
				(return 0)
			)
		)
	)
	
	(method (getHotspotVerbList)
		(return
			(if scratch
				(return (scratch hotspotVerbList?))
			else
				(return 0)
			)
		)
	)
	
	(method (forceCursor param1)
		(if (not scratch) (return 0))
		(return
			(if (== argc 0)
				(return (scratch forceCursor:))
			else
				(return (scratch forceCursor: param1))
			)
		)
	)
	
	(method (myPriority theY &tmp theFeature)
		(return
			(cond 
				((self isKindOf: View)
					(= theFeature self)
					(if argc
						(return (theFeature setPri: theY))
					else
						(return (theFeature priority?))
					)
				)
				(argc (return (= y theY)))
				(else (return y))
			)
		)
	)
	
	(method (setRect theNsLeft theNsTop theNsRight theNsBottom)
		(= nsTop theNsTop)
		(= nsLeft theNsLeft)
		(= nsRight theNsRight)
		(= nsBottom theNsBottom)
	)
	
	(method (normalizeRect &tmp theOnMeCheck theOnMeCheckPoints temp2)
		(if
			(and
				onMeCheck
				(== 0 nsLeft)
				(== nsLeft nsRight)
				(== nsRight nsTop)
				(== nsTop nsBottom)
			)
			(if
			(not ((= theOnMeCheck onMeCheck) respondsTo: #points))
				(= theOnMeCheck (theOnMeCheck at: 0))
			)
			(= nsLeft
				((= theOnMeCheckPoints (theOnMeCheck points?)) at: 0)
			)
			(= nsRight (theOnMeCheckPoints at: 0))
			(= nsTop (theOnMeCheckPoints at: 1))
			(= nsBottom (theOnMeCheckPoints at: 1))
			(= temp2 2)
			(while (< temp2 (* 2 (theOnMeCheck size:)))
				(= nsLeft (Min nsLeft (theOnMeCheckPoints at: temp2)))
				(= nsRight (Max nsRight (theOnMeCheckPoints at: temp2)))
				(= nsTop
					(Min nsTop (theOnMeCheckPoints at: (+ temp2 1)))
				)
				(= nsBottom
					(Max nsBottom (theOnMeCheckPoints at: (+ temp2 1)))
				)
				(= temp2 (+ temp2 2))
			)
		)
	)
	
	(method (getName &tmp temp0)
		(if
			(not
				(= temp0 (MakeMessageText noun 68 case 1 curRoomNum))
			)
			(= temp0 (MakeMessageText noun 68 0 1 curRoomNum))
		)
		(return temp0)
	)
	
	(method (handleTopic)
		(return 0)
	)
	
	(method (stopHogging)
		(gOEventHandler unregisterEventHog: self)
		(= actions 0)
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
