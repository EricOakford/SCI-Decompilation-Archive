;;; Sierra Script 1.0 - (do not remove this comment)
(script# FEATURE)
(include game.sh)
(use Main)
(use Intrface)
(use PolyPath)
(use System)
;; Features are the basic responsive object
;; PicView, View, Prop, Actor, and Ego all inherit from this class.
;;
;; Classes
;;	CueObj	
;;	Feature

(class CueObj of Script
	(properties 
		theVerb 		NULL
		theInvItem	NULL
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
							(IsObject (client actions?))
							((client actions?) 
								doVerb:
									theVerb 
									theInvItem
							)
						)
					)
					(client 
						doVerb: 
							theVerb 
							theInvItem
					)	
				)
				(= state 0)
			)
		)
	)
)
						
(class	Feature of Object
	(properties
		x					0
		y					0
		z					0
		heading			0
		noun				0 	; noun that this object will respond to from user input.
		nsTop				0	; rectangle defining this objects boundaries.
		nsLeft			0	; 
		nsBottom			0	;
		nsRight			0	;
		description		0	; noun used to describe this object in print messages
		sightAngle		ftrDefault	; angle used by facingMe
		actions			0	; instance of Action or EventHandler with Actions
		onMeCheck		ftrDefault	; if filled, the control color used by onMe:	or a Polygon
		approachX		0
		approachY		0
		approachDist	0
		_approachVerbs	ftrDefault
		lookStr			0
	)

	
;;;	(methods
;;;		init
;;;		handleEvent
;;;		doVerb				; check all proximity tests and respond.
;;;
;;;		notFacing			; if ego isn't facing me (TurnIfSaid hook)
;;;
;;;		;; boolean methods
;;;		facingMe				; is ego facing this object?
;;;		isNotHidden			; method defining what hidden means for this object
;;;		onMe					; method defining what constitutes being on  
;;;		approachVerbs		; method to set _approachVerbs
;;;	)


	(method (init theInitializer)
		(cond
			((and argc theInitializer)
				(self perform:theInitializer)
			)
			(ftrInitializer
				(self perform:ftrInitializer)
			)
;			(else		; Game didn't supply initializer -- use Sierra defaults
;				(self perform:dftFtrInit)
;			)
		)

		;; if object isKindof:View
		;; won't compile since actor.sc is compiled after
		(if (self respondsTo:#underBits)
			(cast add: self) ;;this is here so that Views etc can inherit this code
		else
			(features add: self)
		)
	)

	(method (approachVerbs args &tmp i)
		(= _approachVerbs 0)
		;; have to bit-pack verbs since verbs are enums, not bits.
		(for ((= i 0)) (< i argc) ((++ i))
			(if args
				(self _approachVerbs:(| (self _approachVerbs?) (<< 1 (- [args i] 1))))
			)
		)
	)

	(method (handleEvent event &tmp useMsg)	
		(cond
			((event claimed?) 
				(return TRUE)
			)
			((and 
					(== (event type?) userEvent) 
					(self onMe: event) 
					(self isNotHidden:)
				)
				(CueObj
					state:	0,
					cycles:	0,
					client: 	self,
					theVerb: (event message?),
					theInvItem:
					(if
						(and
							theIconBar
							(== (event message?) verbUse)
							inventory
						)
						(inventory indexOf:(theIconBar curInvIcon?))
					)
				)
				(event claimed: TRUE)
				(if (and 
						(user canControl?)
						(!= _approachVerbs ftrDefault)
						(& _approachVerbs (<< 1 (- (event message?) 1)))
					)
					(ego setMotion: PolyPath approachX (+ (ego z?) approachY) CueObj)
				else
					(if (user canControl?)
						(ego setMotion: 0)
					)
					(CueObj changeState:3)
				)
			)
		)
		(return (event claimed?))
	)

	(method (notFacing &tmp event)
		(ego setMotion:0) 																		 
		(CueObj 
			client:	self,
			state:	0,
			cycles:	0,
			cue:
		)
	)

	;; verb responses

	(method (doVerb theVerb)
	  	(return 
			(
				(if doVerbCode
					doVerbCode 
				else
					dftDoVerb
				)
				doit:	theVerb self &rest
			)
			
		)
	)
	
	;; Boolean Methods

	(method (facingMe act &tmp theActor theAngle)
		(= theActor (if argc act else ego))
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
			(self notFacing:)
			(return FALSE)
		)
	)

	(method (isNotHidden)
		(return TRUE)
;		(if (& signal actorHidden)
;			(self notInFar:)
;			(return FALSE)
;		else
;			(return TRUE)
;		)
	)

	(method (onMe theObjOrX theY &tmp oX oY)
		
		;; do we really want this much code?
		;; your comments welcome.

		(if (IsObject theObjOrX) 
			(= oX	(theObjOrX x?))
			(= oY	(theObjOrX y?))
		else
			(= oX	theObjOrX)
			(= oY	theY)
		)
		(return 
			(if (IsObject onMeCheck)
				;; a polygon
				(AvoidPath oX oY onMeCheck)
			else
				;; control color
				(and 
					(or
						;; ns not set
						(not (or	nsLeft nsRight nsTop nsBottom))
						;; check inside
						(and 
							(<= nsLeft oX nsRight)
							(<= nsTop  oY nsBottom)
						)
					)
					;; is onMeCheck 
					(if (!= onMeCheck ftrDefault) 
						(& onMeCheck (OnControl CMAP oX oY)) 
					else 
						TRUE
					)
				)
			)
		)
	)						 

	(method (dispose)
		(if actions
			(actions dispose:)
			(= actions NULL)
		)
		(if (IsObject onMeCheck)
			(onMeCheck dispose:)
			(= onMeCheck NULL)
		)
		(features delete: self)
		(super dispose:)
	)

)



;(instance dftFtrInit of Code		; sets up defaults
;	(method (doit theObj)
;		; angle used by facingMe
;		(if (== (theObj sightAngle?) ftrDefault)
;			(theObj sightAngle: 90)
;		)
;		; maximum distance to get an object (for example.)
;		(if (== (theObj closeRangeDist?) ftrDefault)
;			(theObj closeRangeDist: 50)
;		)
;		; maximum distance to see an object	(for example.)
;		(if (== (theObj longRangeDist?) ftrDefault)
;			(theObj longRangeDist: 100)
;		)
;		; verb message corresponding to a shift click.
;		(if (== (theObj shiftClick?) ftrDefault)
;			; don't do checks if mousedOn.
;			(theObj shiftClick: (| NOCHECKMOUSE verbLook))
;		)
;		; verb message corresponding to a control click.
;;		(if (== (theObj contClick?) ftrDefault)
;;			(theObj contClick: verbGet)
;;		)
;		; instance of Action or EventHandler with Actions
;		(if (== (theObj actions?) ftrDefault)
;			(theObj actions: 0)
;		)
;		; if filled, the control color used by onMe:
;		(if (== (theObj control?) ftrDefault)
;			(theObj control: 0)
;		)
;		; Bit-mapped verb checks (4 bits per verb)
;		(if (== (theObj verbChecks1?) ftrDefault)
;			(theObj verbChecks1: $bbb5)
;		)
;		; Bit-mapped verb checks (4 bits per verb)
;		(if (== (theObj verbChecks2?) ftrDefault)
;			(theObj verbChecks2: $bbbb)
;		)
;		; Bit-mapped verb checks (4 bits per verb)
;		(if (== (theObj verbChecks3?) ftrDefault)
;			(theObj verbChecks3: $bbbb)
;		)
;	)
;)


;(instance moveInCloseScript of Script
;	(define thisVerb register)
;	;; use caller property as a holder for the target feature
;	;; then disable the normal cueing of a script
;	(method (changeState newState)
;		(switchto (= state newState)
;			(
;;				(user canControl:FALSE, canInput:FALSE)
;				(if theIconBar
;					(theIconBar state:(| (theIconBar state?) DISABLED))
;				)
;				(ego setMotion:PolyPath 
;					(+ (caller x?) (SinMult  (caller heading?) (caller closeRangeDist?)))
;					(+ (caller y?)	(CosMult  (caller heading?) (caller closeRangeDist?)))
;					self
;				)
;			)
;			(
;				(caller doVerb:thisVerb)
;				(= caller 0)
;;				(user canControl:TRUE, canInput:TRUE)
;				(if theIconBar
;					(theIconBar state:(| (theIconBar state?) ENABLED))
;				)
;				(self dispose:)
;			)
;		)
;	); changeState
;
;); moveInCloseScript


(instance dftDoVerb of Code
	(method (doit theVerb theObj invNo &tmp objDesc iItem)
		(= objDesc (theObj description?))
		(switch theVerb
			(verbLook
				(if (theObj lookStr?)
					(Print (theObj lookStr?))
				else
					(Printf FEATURE 0 objDesc objDesc)
				)
			)
			(verbUse	
				(if (= iItem (inventory at: invNo))
					(Printf FEATURE 1 (iItem description?) objDesc)
				)
			)
			
;			(verbClose
;				(Printf "You cannot close the %s." objDesc)
;			)
;			(verbSmell
;				(Printf "The %s has no smell." objDesc)
;			)
;			(verbMove	
;				(Printf "You cannot move the %s." objDesc)
;			)
;			(verbEat 	
;				(Printf "You wouldn't want to eat the %s." objDesc)
;			)
;			(verbGet 	
;				(Printf "You cannot get the %s." objDesc)
;			)
;			(verbClimb
;				(Printf "You can't climb the %s." objDesc)
;			)
			(verbTalk
				(Printf FEATURE 2 objDesc)
			)
 		)
	)
)
