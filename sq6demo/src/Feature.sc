;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	FEATURE.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1990
;;;;
;;;;	Features are the basic responsive object from which View,
;;;;		Prop, Actor, and Ego all descend.
;;;;
;;;;	Author:	Mark Hood
;;;;	Updated: Robert W. Lindsley
;;;;
;;;;	Classes:
;;;;		CueObj
;;;;		Feature
;;;;		Actions


(script# FEATURE)
(include game.sh)
(use Main)
(use PolyPath)
(use Polygon)
(use System)


(class CueObj of Script
	(properties 
		theVerb 		NULL
	)
	(method (changeState newState)
		(switch (= state newState)
			(1
				(ego setHeading: (GetAngle (ego x?) (ego y?) (client x?) (client y?)) self)
				(theDoits add:self)
			)
			(2
				(= cycles 3)	
			)
			(3
				(theDoits delete: self)
				(if (not
						(and
							client
							(client actions?)
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
						
(class Feature kindof Object
	(properties
		heading			0
		noun				0 	; noun that this object will respond to from user input.
		case				0	; case that this object will respond with
		modNum		   -1	; module number for our messages

		nsLeft			0	; 
		nsTop				0	; rectangle defining this objects boundaries.
		nsRight			0	;
		nsBottom			0	;

		sightAngle		ftrDefault	; angle used by facingMe
		actions			0	; instance of Action or EventHandler with Actions
		onMeCheck		0	; a list of polys or a poly
		state				0
		approachX		0
		approachY		0
		approachDist	0
		_approachVerbs	0

		; Critical properties only below this point
		;	Adding properties above this requires changing the xxxx define

		plane				0	;pointer to a plane (if any)
		x					0
		y					0
		z					0
	)

	
;;;	(methods
;;;		init
;;;		initialize			; do the feature initializer code
;;;		handleEvent
;;;		doVerb				; check all proximity tests and respond.
;;;		notFacing			; if ego isn't facing me (TurnIfSaid hook)
;;;		facingMe				; is ego facing this object?
;;;		isNotHidden			; method defining what hidden means for this object
;;;		onMe					; method defining what constitutes being on  
;;;		approachVerbs		; method to set _approachVerbs
;;;		setName				; dynamically allocate space for new name
;;;		setPolygon			; add a list of points for onMe
;;;		createPoly			; pass points for onMe
;;;	)


	(method (init theInitializer)
		(self initialize: (if argc theInitializer else 0))
		;; if object isKindof: View
		;; won't compile since actor.sc is compiled after
		(if (self respondsTo: #view)
			(cast add: self) ;;this is here so that Views etc can inherit this code
		else
			(features add: self)
		)
	)

	(method (initialize theInitializer)
		(cond
			((and argc theInitializer)
				(self perform: theInitializer)
			)
			(ftrInitializer
				(self perform: ftrInitializer)
			)
		)
	)

	(method (approachVerbs args &tmp i theBits)
		;
		; Load the _approachVerbs property with the bit pattern that matches
		;	the verb sent to us... the approachCode global points to a game-
		;	specific piece of code that translates the verb into that bit pattern
		;
		; If no args are passed or 0 is passed the _approachVerbs property will
		;	be reset to 0.

		(= _approachVerbs 0)
		(if (and argc
					approachCode
					[args 0]
				)
			(for 	((= i 0))
					(< i argc)
					((++ i))
				(= theBits (approachCode doit: [args i]))
				(self _approachVerbs: (| (self _approachVerbs?) theBits))
			)
		)
	)

	(method (handleEvent event &tmp ret)
		(cond
			((event claimed?) 
				(return TRUE)
			)
			((and 
					(& (event type?) userEvent) 
					(self onMe: event) 
					(self isNotHidden:)
				)
				(CueObj
					state:		0,
					cycles:		0,
					client: 		self,
					theVerb: 	(event message?)
				)
				(= ret (event claimed: TRUE))

				; If we can control ego AND ego's outside the approachDist AND
				;	we're using one of the approachVerbs AND his signal bit is
				;	set to approach, bring ego to us

				(if (and	(user canControl?)
							(& (ego state?) approachObj)
							(> (GetDistance (ego x?) (ego y?) approachX approachY) approachDist)
							(and	approachCode
									(& _approachVerbs (approachCode doit: (event message?)))
							)
					)
					(ego setMotion: PolyPath approachX (+ (ego z?) approachY) CueObj)
				else
					(ego setMotion: 0)
					(if (self facingMe:)
						(CueObj changeState: 3)
					)
				)
			)
		)
		(return ret)
	)

	(method (notFacing &tmp event)
		(ego setMotion: 0) 																		 
		(CueObj 
			client:	self,
			state:	0,
			cycles:	0,
			cue:
		)
	)

	(method (doVerb theVerb &tmp defaultCode who)
		(= defaultCode
			(if doVerbCode
				doVerbCode
			else
				dftDoVerb
			)
		)

		(if (== modNum -1)
			(= modNum curRoomNum)
		)

		; If we're not a Print-only game and if there is a message, tell
		;	the messager to go for it

		(if (and	msgType
					(Message MsgGet modNum noun theVerb case 1)
				)
			(messager say: noun theVerb case NULL NULL modNum)
		else
			(defaultCode doit: theVerb self)
		)
	)
	
	;; Boolean Methods

	(method (facingMe act &tmp theActor theAngle)
		(cond
			(argc
				(= theActor act)
			)
			((cast contains: ego)
				(= theActor ego)
			)
			(else
				(return TRUE)
			)
		)
		(= theAngle	
			(Abs
				(-	
					(GetAngle (theActor x?) (theActor y?) x y)
					(theActor heading?)
				)
			)
		)
		(if (> theAngle 180) 
			(= theAngle (- 360 theAngle))
		)
		(if (<= 	theAngle	sightAngle)
			(return TRUE)
		else
			(if (!= sightAngle ftrDefault)
				(if (& (theActor signal?) fixedLoop)
					(CueObj 
						client:	self,
						state:	2,
						cycles:	0,
						cue:
					)
				else
					(self notFacing:)
				)
			)
			(return FALSE)
		)
	)

	(method (isNotHidden)
		(return TRUE)
	)

	(method (onMe theObjOrX theY &tmp oX oY)
		(if (== argc 1)
			(= oX	(theObjOrX x?))
			(= oY	(theObjOrX y?))
		else
			(= oX	theObjOrX)
			(= oY	theY)
		)
		(return
			(cond
				((not onMeCheck)
					(if (or	(not 	(or nsLeft nsRight nsTop nsBottom))
								(and	(<= nsLeft oX nsRight)
										(<= nsTop  oY nsBottom)
								)
						)
						TRUE
					else
						FALSE
					)
				)
				((onMeCheck isKindOf: List)
					(onMeCheck firstTrue: #onMe oX oY)
				)
				(else
					(onMeCheck onMe: oX oY)
				)
			)
		)
	)

	(method (setName theName strObj &tmp locStrObj)
		(if (== theName FROM)
			(= locStrObj (strObj new:))
			(= name (locStrObj data?))
			(locStrObj data:	0)		;this is so when we dispose the string, the data will remain intact.
			(locStrObj dispose:)
		else
			(= name theName)
		)
	)

	(method (setPolygon args)
		;
		; Set the polygon (or list thereof) for onMe checks.  To set a single
		;	polygon by passing points, use the createPoly method.

		(if onMeCheck
			(onMeCheck dispose:)
			(= onMeCheck 0)
		)
		(cond
			; No args, or 0
			((or (not argc) (== args 0))
				(return)
			)

			; Single arg is list or polygon
			((== argc 1)
				(= onMeCheck [args 0])
			)

			; Multiple polygons
			(else
				(= onMeCheck (List new:))
				(onMeCheck add: args &rest)
			)
		)
	)

	(method (createPoly args)
		(if onMeCheck
			(onMeCheck dispose:)
		)
		(= onMeCheck (Polygon new:))
		(onMeCheck
			init:		args &rest,
			type:		PTotalAccess
		)
	)

	(method (dispose)
		(self setPolygon:		0)		;dispose of polygon list
		(if actions
			(actions dispose:)
			(= actions NULL)
		)
		(if onMeCheck
			(onMeCheck dispose:)
			(= onMeCheck NULL)
		)
		(features delete: self)
		(super dispose:)
	)

)


; The following code ONLY happens if the feature doesn't have handling
;	for the event AND the game is a Print Only game (i.e. msgType is 0)

(instance dftDoVerb of Code
	(method (doit)
		(return TRUE)
	)
)



(class Actions kindof Code
;;;	(methods
;;;		doVerb
;;;	)
	(method (doVerb)
		(return FALSE)
	)
)
