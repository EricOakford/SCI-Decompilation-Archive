;;; Sierra Script 1.0 - (do not remove this comment)
(script# FEATURE)
(include game.sh)
(use Main)
(use Intrface)
(use PolyPath)
(use System)


(class CueObj of Script
	(properties
		theVerb NULL
		theInvItem NULL
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
						(if (and client (IsObject (client actions?)))
							((client actions?) doVerb: theVerb theInvItem)
						)
					)
					(client doVerb: theVerb theInvItem)
				)
				(= state 0)
			)
		)
	)
)

(class Feature of Object
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description 0
		sightAngle ftrDefault
		actions 0
		onMeCheck ftrDefault
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs ftrDefault
		lookStr 0
	)
	
	(method (init theInitializer)
		(cond 
			((and argc theInitializer) (self perform: theInitializer))
			(ftrInitializer (self perform: ftrInitializer))
		)
		(if (self respondsTo: #underBits)
			(cast add: self)
		else
			(features add: self)
		)
	)
	
	(method (dispose)
		(if actions (actions dispose:) (= actions NULL))
		(if (IsObject onMeCheck)
			(onMeCheck dispose:)
			(= onMeCheck NULL)
		)
		(features delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(cond 
			((event claimed?) (return TRUE))
			(
				(and
					(== (event type?) userEvent)
					(self onMe: event)
					(self isNotHidden:)
				)
				(CueObj
					state: 0
					cycles: 0
					client: self
					theVerb: (event message?)
					theInvItem:
						(if
							(and
								theIconBar
								(== (event message?) verbUse)
								inventory
							)
							(inventory indexOf: (theIconBar curInvIcon?))
						else
							FALSE
						)
				)
				(event claimed: TRUE)
				(if
					(and
						(user canControl:)
						(!= _approachVerbs ftrDefault)
						(&
							_approachVerbs
							(<< $0001 (- (event message?) verbWalk))
						)
					)
					(ego
						setMotion: PolyPath approachX (+ (ego z?) approachY) CueObj
					)
				else
					(if (user canControl:)
						(ego setMotion: 0)
					)
					(CueObj changeState: 3)
				)
			)
		)
		(return (event claimed?))
	)
	
	(method (doVerb theVerb)
		((if doVerbCode else dftDoVerb) doit: theVerb self &rest)
	)
	
	(method (notFacing &tmp event)
		(ego setMotion: 0)
		(CueObj client: self state: 0 cycles: 0 cue:)
	)
	
	(method (facingMe act &tmp theActor theAngle)
		(= theActor (if argc act else ego))
		(if
			(>
				(= theAngle
					(Abs
						(-
							(GetAngle (theActor x?) (theActor y?) x y)
							(theActor heading?)
						)
					)
				)
				180
			)
			(= theAngle (- 360 theAngle))
		)
		(return
			(if (<= theAngle sightAngle)
				(return TRUE)
			else
				(self notFacing:)
				(return FALSE)
			)
		)
	)
	
	(method (isNotHidden)
		(return TRUE)
	)
	
	(method (onMe theObjOrX theY &tmp oX oY)
		(if (IsObject theObjOrX)
			(= oX (theObjOrX x?))
			(= oY (theObjOrX y?))
		else
			(= oX theObjOrX)
			(= oY theY)
		)
		(return
			(cond 
				((IsObject onMeCheck) (AvoidPath oX oY onMeCheck))
				(
					(or
						(not (if (or nsLeft nsRight nsTop) else nsBottom))
						(and
							(<= nsLeft oX)
							(<= oX nsRight)
							(<= nsTop oY)
							(<= oY nsBottom)
						)
					)
					(if (!= onMeCheck ftrDefault)
						(& onMeCheck (OnControl CMAP oX oY))
					else
						TRUE
					)
				)
			)
		)
	)
	
	(method (approachVerbs args &tmp i)
		(= i (= _approachVerbs 0))
		(while (< i argc)
			(if args
				(self
					_approachVerbs:
						(|
							(self _approachVerbs?)
							(<< $0001 (- [args i] 1))
						)
				)
			)
			(++ i)
		)
	)
)

(instance dftDoVerb of Code
	(properties)
	
	(method (doit theVerb theObj theItem &tmp objDesc invDesc)
		(= objDesc (theObj description?))
		(switch theVerb
			(verbLook
				(if (theObj lookStr?)
					(Print (theObj lookStr?))
				else
					(Printf 950 0 objDesc objDesc)
				)
			)
			(verbUse
				(if (= invDesc (inventory at: theItem))
					(Printf 950 1 (invDesc description?) objDesc)
				)
			)
			(verbTalk (Printf 950 2 objDesc))
		)
	)
)
