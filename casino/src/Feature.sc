;;; Sierra Script 1.0 - (do not remove this comment)
(script# FEATURE)
(include game.sh)
(use Main)
(use PolyPath)
(use System)


(class CueObj of Script
	(properties
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
						(if (and client (IsObject (client actions?)))
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
		x					0
		y					0
		z					0
		heading			0
		noun				0 	; noun that this object will respond to from user input.
		modNum		   0	; module number for our messages
		nsTop				0	; rectangle defining this objects boundaries.
		nsLeft			0	; 
		nsBottom			0	;
		nsRight			0	;
		sightAngle		ftrDefault	; angle used by facingMe
		actions			0	; instance of Action or EventHandler with Actions
		onMeCheck		ftrDefault	; if filled, the control color used by onMe:	or a Polygon
		approachX		0
		approachY		0
		approachDist	0
		_approachVerbs	0
	)
	
	(method (init theInitializer)
		(cond 
			((and argc theInitializer)
				(self perform: theInitializer)
			)
			(ftrInitializer
				(self perform: ftrInitializer)
			)
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
	
	(method (handleEvent event &tmp theMods)
		(cond 
			((event claimed?) (return TRUE))
			(
				(and
					(& (event type?) userEvent)
					(self onMe: event)
					(self isNotHidden:)
				)
				(CueObj
					state: 0
					cycles: 0
					client: self
					theVerb: (event message?)
				)
				(event claimed: TRUE)
				(if
					(and
						(user canControl:)
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
					(CueObj changeState: 3)
				)
			)
		)
		(return (event claimed?))
	)
	
	(method (doVerb theVerb &tmp defaultCode who)
		(= defaultCode (if doVerbCode else dftDoVerb))
		(if (not modNum )
			(= modNum curRoomNum)
		)
		(if (and msgType (Message MsgGet modNum noun theVerb 0 1))
			(messager say: noun theVerb 0 0 0 modNum)
		else
			(defaultCode doit: theVerb self)
		)
	)
	
	(method (notFacing &tmp event)
		(ego setMotion: 0)
		(CueObj
			client: self
			state: 0
			cycles: 0
			cue:
		)
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
						1
					)
				)
			)
		)
	)
	
	(method (approachVerbs args &tmp i theBits)
		(if (and argc approachCode)
			(= _approachVerbs 0)
			(= i 0)
			(while (< i argc)
				(= theBits (approachCode doit: [args i]))
				(self _approachVerbs: (| (self _approachVerbs?) theBits))
				(++ i)
			)
		)
	)
)

(instance dftDoVerb of Code
	(properties)
	
	(method (doit)
		(return TRUE)
	)
)