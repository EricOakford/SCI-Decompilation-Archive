;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	ACTOR.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Jeff Stephenson
;;;;	Updated: Brian K. Hughes
;;;;
;;;;	The classes in this module are the visible/animated objects on the
;;;;	screen.
;;;;
;;;;	Classes:
;;;;		View
;;;;		Prop
;;;;		Actor


(script# ACTOR)
(include game.sh)
(use Main)
(use Array)
(use Print)
(use Scaler)
(use PolyPath)
(use Feature)
(use Motion)
(use System)


(class View kindof Feature
	;;; The view class contains the minimum functionality to put a visible
	;;; object on the screen in a reversible manner (i.e. so that it can
	;;; be erased).
	
	(properties
		scaleX			scaleBase
		scaleY			scaleBase
		maxScale			scaleBase
		scaleType		0

		priority			0			;priority of visible-obj
		fixPriority		FALSE

		inLeft			0
		inTop				0			;Inset rect
		inRight			0
		inBottom			0
		useInsetRect	FALSE

	 	view				-1 	  	;view number - default to illegal view
		loop				0 			;loop number
		cel				0 			;cel number
		bitmap			0			;handle to special bitmap, if any

		; Critical properties only above this point
		;	Adding crit properties below this requires changing the xxxx define

		yStep				2 			;here for BaseSetter use only
		signal			(| setBaseRect canUpdate ignrAct)
		
		lsLeft			0
		lsTop				0 			;lastSeen - previous rectangle
		lsRight			0
		lsBottom			0

		brLeft			0
		brTop				0 			;base rectangle
		brRight			0
		brBottom			0

		scaleSignal		0
		magnifier		0			;magnifier object
		oldScaleX		scaleBase
										;saved for speeding up actor doit
	)
;;;	(methods
;;;		init					;add actor to cast, once prepared for animation
;;;		posn					;position the object at a particular point
;;;		setPri				;alter priority (see method)
;;;		setLoop				;alter loop (see method)
;;;		setCel				;alter cel (see method)
;;;		setInsetRect		;set the inset rect
;;;		ignoreActors		;condition signal 'ignrAct' bit
;;;		hide					;remove View from screen, but leave in cast
;;;		show					;show a hidden view
;;;		delete
;;;		lastLoop				;return number of last loop for this view
;;;		lastCel				;return number of last cel for this view
;;;		motionCue
;;;		checkDetail			;does nothing in a view by default
;;;		setScale				;sets or unsets automatic scaling
;;;		setMagnifier		;set (or release) a magnifier object
;;;	)
	
	(method (init theCast theInitializer &tmp theList)
		;
		; Prepare View and add to the appropriate list.

		(= theList
			(if argc
				theCast
			else
				cast
			)
		)

		; Make sure we're not hidden
		(&= signal (~ viewHidden))

		; Add to the list, and feature initialization
		(theList add: self)
		(= plane (theList plane?))
		(AddScreenItem self)
		;DEBUG!  Move this to the AddScreenItem call
		(|= -info- IN_SILIST)

		; Set lastSeen to null rectangle if View is not currently in the list
		(if (not (theList contains: self))
			; Reset lastSeen rectangle
			(= lsLeft 	0)
			(= lsTop 	0)
			(= lsRight 	0)
			(= lsBottom 0)
		
			; Ensure that HIDE and HIDDEN bits are not on
			(&= signal (~ viewHidden))
		)
		
		(= useInsetRect FALSE) ;Avoid setting critical properties needlessly

		
		; Set baseRect and nowSeenRect of the View
		(if (and (== view -1) bitmap) (&= signal (~ setBaseRect)) )
		(if (& signal setBaseRect)
			(BaseSetter self)
		)

		; Set the nsRect if using a normal view (DItems sometimes don't)
		(if (!= view -1)
			(SetNowSeen self)
		)

		(self
			initialize:	,
			checkDetail:
		)
	)

	(method (doit)
		;
		; Check the -info- property to see if we need to update

		(if (and
			 	(& -info- GRAPH_UPD)
				(self isNotHidden:)
			)
			(UpdateScreenItem self)
		)
	)

	(method (posn newX newY newZ)
		;
		; Position the View at newX, newY, newZ.  It will appear there on
		; next animation cycle.
		
		(if (>= argc 1)			(= x newX)
			(if (>= argc 2)		(= y newY)
				(if (>= argc 3)	(= z newZ)
				)
			)
		)
		
		; Reset base rectangle
		(if (& signal setBaseRect)
			(BaseSetter self)
		)
	)

	(method (hide)
		;
		; Hide the object (remove from screen without removing from cast)
		
		(if (self isNotHidden:)
			(|= signal viewHidden)
			(DeleteScreenItem self)
			(&= -info- (~ IN_SILIST))
		)
	)

	(method (show)
		;
		; Show a hidden object
		
		(if (not (self isNotHidden:))
			(&= signal (~ viewHidden))
			(AddScreenItem self)
			;DEBUG!  Move this to the AddScreenItem call
			(|= -info- IN_SILIST)
		)
	)

	(method (dispose &tmp theSet)
		(cast delete: self)
		(if (self isNotHidden:)
			(DeleteScreenItem self)
			;DEBUG!  Move this to the DeleteScreenItem call
			(&= -info- (~ IN_SILIST))
		)
		(if plane
			(= theSet (plane casts?))
			(theSet eachElementDo: #delete: self)
			(= plane 0)
		)

		(super dispose:)
	)
	
	(method (setPri newPri)
		;
		; Action depends on the presence/value of newPri:
		;		not present		fix object's priority at its current value
		;		RELEASE, -1		let (Animate) determine object's priority
		;							based on its x,y position
		;		else				fix object's priority at the specified value
		
		(cond
			((== argc 0)
				(= fixPriority TRUE)
			)
			((== newPri -1)
				(= fixPriority FALSE)
			)
			(else
				(= priority newPri)
				(= fixPriority TRUE)
			)
		)
	)

	(method (setLoop newLoop fixIt)
		;
		; Action depends on the presence/value of newLoop:
		;		not present		fix object's loop at its current value
		;		RELEASE, -1		let a Motion class determine the object's loop
		;							based on its current direction
		;		else				set object's loop to the specified value
		;
		; In addition, if 'fixIt' is passed the fixedLoop bit will be set
		;	or cleared, depending on the value of 'fixIt'.
		;

		
		(cond
			((== argc 0)
				(|= signal fixedLoop)
			)
			((== newLoop -1)
				(&= signal (~ fixedLoop))
			)
			(else
				(= loop newLoop)
				(if (> argc 1)
					(if fixIt
						(|= signal fixedLoop)
					else
						(&= signal (~ fixedLoop))
					)
				)
			)
		)
	)

	(method (setCel newCel)
		;
		; Sets the View's cel to the specified value, checking for invalid values
		
		(= cel newCel)
	)

	(method (setInsetRect l t r b)
		;
		; Set the inset rect to that described by the parameters or
		;	to the same as the nsRect if 'l' is 0.

		(if (< argc 4)
			(= useInsetRect FALSE)
			(= inLeft nsLeft)
			(= inTop nsTop)
			(= inRight nsRight)
			(= inBottom nsBottom)
		else
			(= useInsetRect TRUE)
			(= inLeft l)
			(= inTop t)
			(= inRight r)
			(= inBottom b)
		)
	)

	(method (ignoreActors arg)
		;
		; No or nonzero argument says that this object should not collide with
		;	other animated objects.  A FALSE argument says that it should collide
		
		(if (or (== 0 argc) arg)
			(|= signal ignrAct)
		else
			(&= signal (~ ignrAct))
		)
	)  

	(method (lastLoop)
		;
		; Return the number of the last loop in the current view of this object

		(return (- (NumLoops self) 1))
	)

	(method (lastCel)
		;
		; Return the number of the last cel in the current loop of this object
		
		(return (- (NumCels self) 1))
	)

	(method (motionCue)
		; Does nothing in a View.
	)

	(method (checkDetail)
		; Does nothing in a View.
	)

	(method (isNotHidden)
		(return (not (& signal viewHidden)))
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
				((& signal viewHidden)
					FALSE
				)
				((not onMeCheck)
				  	(IsOnMe oX oY self (& signal skipCheck))	;EO: Was "OnMe"
				)
				(else
					(super onMe: oX oY)
				)
			)
		)
	)
	
	(method (setScale theYorW theH &tmp deltaY1 deltaY2 pctInc w h)
		;
		; Set either:
		;	a) (no arg)  - scalable, no auto scaling
		;	b) 0         - no scaling
		;	c) y         - automatic scaling, based on the vanishing point in the
		;						room and the y coordinate at which the client is 100%
		;	d) w h       - scale to fit dimensions of 'w' x 'h'

		(cond
			; Set scalable but not autoscaling
			((not argc)
				(|= scaleSignal scalable)
				(&= scaleSignal (~ autoScale))
			)
			
			; Scale to dimensions
			((== argc 2)
				(= w (CelWide view loop cel))
				(= h (CelHigh view loop cel))
				(if (== theYorW -1)
					(= theYorW w)
				)
				(if (== theH -1)
					(= theH h)
				)
				(= scaleX (/ (* theYorW scaleBase) w))
				(= scaleY (/ (* theH scaleBase) h))
				(|= scaleSignal scalable)
				(UpdateScreenItem self)
			)

			; Turn off scaling
			((not theYorW)
				(&= scaleSignal (~ (| scalable autoScale)))
			)

			; Set autoscaling
			(else
				(if (< theYorW (curRoom vanishingY?))
					(Printf {<%s setScale:> y value less than vanishingY} name)
				else
					(= deltaY1 (- theYorW (curRoom vanishingY?)))
					(= deltaY2 (- 190 theYorW))
					(= pctInc (+ (/ (* deltaY2 100) deltaY1) 100))
					(|= scaleSignal (| scalable autoScale))
					(= maxScale (/ (* pctInc scaleBase) 100))
				)
			)
		)
	)

	(method (setMagnifier theMag)
		;
		; Attach (or release) a magnifier object to the view.
		; Parameters are:
		;		view loop cel power skip

		(if (not (& signal viewHidden))
			(if magnifier
				(magnifier dispose:)
			)
			(if (and argc theMag)
				((= magnifier theMag) init: self &rest)
			)
		)
	)
)



(class Prop kindof View
	;;; The Prop class adds cycling capability to the View class.
	;;; It also introduces the ability to have an attached script
	;;; which determines its behaviour.
	
	(properties
		cycleSpeed	6					;0 (fastest) to n (slowest)
		script 		0 					;object ID of script
		cycler 		0 					;object ID of cycle code
		timer 		0 					;object ID of an attached Timer
		detailLevel 0					;level to start animating
		scaler		0					;object ID of scaler object
		signal		(| setBaseRect canUpdate)
	)
;;;	(methods
;;;		setCycle			;attach an instance of a Cycle class
;;;		setScript		;attach a script
;;;		cue				;cue the object (or its script)
;;;		setScaler		;set a scaler object
;;;		checkDetail		;check detailLevel against theGames and act accordingly
;;;	)

	(method (setLoop &tmp oLoop)
		(= oLoop loop)
		(super setLoop: &rest)
		(if (and cycler (!= oLoop loop))
			(cycler clientLastCel: (self lastCel:) )
 		)
	)

	(method (doit &tmp aState)
		;
		; This is the method called in every animation cycle

		(if script
			(script doit:)
		)

		(if (& signal canUpdate)
			(if cycler
				(cycler doit:)
			)

			; If we have a dynamic scaler attached, give its doit a shot
			(if scaler
				(scaler doit:)
			)
		)

		(super doit:)
	)
	
	(method (handleEvent event)
		(if script
			(script handleEvent: event)
		)
		(super handleEvent: event)

	)
	
	(method (setCycle cType)
		;
		; Perform cycler actions, depending on cType:
		;	a) a class		- attach a dynamic instance of that class
		;	b) an instance - attach the instance
		;	c) 0				- remove current cycler
		
		; Dispose of existing cycle code
		(if cycler
			(cycler dispose:)
		)
		
		(if cType
			; If a Cycling class was specified, attach an instance of the class to
			;	this object and initialize it, else if an instance was specified,
			;	just use it as is
			
			(= cycler
				(if (& (cType -info-?) CLASS)
					(cType new:)
				else
					cType
				)
			)
			(cycler init: self &rest)
		else
			; If cType is NULL, clear the cycler property
			(= cycler 0)
		)
	)
	
	(method (dispose)
		; Dispose of any attached objects
		(self
			setScript: 	0,
			setCycle: 	0
		)
		(if timer
			(timer dispose:)
		)

		(if scaler
			(scaler dispose:)
			(= scaler 0)
		)
		
		(super dispose:)
	)
	
	(method (cue)
		(if script
			(script cue:)
		)
	)
	
	(method (setScript newScript)
		;
		; Attach a new script to this object, removing any existing one
		
		(if script
			(script dispose:)
		)
		(if newScript
			(newScript init: self &rest)
		)
	)
	
	(method (motionCue)
		(if (and cycler (cycler completed?))
			(cycler motionCue:)
		)
	)

	(method (checkDetail theLevel)
		(cond
			((not detailLevel)
			)
			((< (if argc theLevel else (theGame detailLevel?)) detailLevel)
				(&= signal (~ canUpdate))
			)
			(else
				(|= signal canUpdate)
			)
		)
	)

	(method (setScaler theScaler)
		;
		; Set a scaler object on us

		(if scaler
			(scaler dispose:)
			(= scaler 0)
		)

		(cond
			((or (not argc) (not theScaler))
				(return)
			)

			((theScaler isKindOf: Scaler)
				(= scaler
					(if (& (theScaler -info-?) CLASS)
						(theScaler new:)
					else
						theScaler
					)
				)
				(|= scaleSignal scalable)
				(&= scaleSignal (~ autoScale))
				(scaler init: self &rest)
			)
			(else
				; Match the scaler of 'theScaler'.  Note that we don't want
				;	to reinit the scaler.
				(= scaler ((theScaler scaler?) new:))
				(= scaleSignal (theScaler scaleSignal?))
				(= maxScale 	(theScaler maxScale?))
				(scaler
					client:	self,
					doit:
				)
			)
		)
	)

	(method (setScale theObjOrY)
		;
		; Set either:
		;	a) (no arg)  - scalable, no scaler
		;	b) 0         - no scaling (passed to View)
		;	c) y         - scaling based on y-coordinate (passed to View)

		(if scaler
			(scaler dispose:)
			(= scaler 0)
		)
		(cond
			; Set scalable but not autoscaling
			((not argc)
				(super setScale:)
			)

			; Either set scaling to scalable or set autoscaling
			(else
				(super setScale: theObjOrY)
			)
		)
	)
)



(class Actor kindof Prop
	;;; The Actor class introduces the ability to move to animated objects.
	
	(properties
		illegalBits $8000
		xLast 		0		;previous x position
		yLast 		0		;previous y position
		xStep 		3		;how far actor moves in x direction
		yStep 		2		;how far actor moves in y direction
		origStep		$0302	;used as a base xStep & yStep for scaling (packed)
		moveSpeed 	6		;0 (fastest) to n (slowest)
		blocks 		0		;any blocks relating to this actor
		baseSetter	0		;ID of Code which sets this objects base rectangle
		mover 		0		;object ID of motion code
		looper 		0		;set actor's loop based on direction or whatever
		viewer 		0		;set actor's view based on some criterion
		avoider 		0		;code for getting around obstructions
		code			0		;any additional code to get done in a doit
		robot			0		;viewstream object (WalkieTalkie)
	)
;;;	(methods
;;;		setMotion			;attach an instance of Motion
;;;		setAvoider			;attach an Avoider
;;;		ignoreHorizon		;determine whether Actor can ignore Room's horizon
;;;		observeControl		;set controls which an Actor cannot be on
;;;		ignoreControl		;set controls which an Actor can ignore
;;;		observeBlocks		;set blocks which will stop Actor
;;;		ignoreBlocks		;allow Actor to ignore blocks
;;;		isStopped			;return TRUE if actor didn't move
;;;		isBlocked			;return TRUE if actor tried to move but couldn't
;;;		inRect				;return TRUE if Actor is in a given rectangle
;;;		distanceTo			;return distance from actor to actor
;;;		cantBeHere			;return TRUE if current position is illegal
;;;		setStep				;set Actor's step size
;;;		setDirection		;set Actor's direction of motion
;;;		setHeading			;set Actor heading and invoke looper
;;;		setSpeed				;sets moveSpeed & cycleSpeed
;;;		setLooper			;set an object to Actor loop
;;;		setRobot				;sets a robot and initializes
;;;	)
	
	(method (init)
		;
		; Prepare actor for animation and add to cast

		(super init: &rest)
		
		; Make previous X/Y == to current X/Y
		(= xLast x)
		(= yLast y)
	)
	
	(method (doit &tmp left right txStep tyStep stepVal )
		;
		; This is the method called in every animation cycle
		
		(if robot
			(robot doit:)
		)

		(if script 
			(script doit:)
		)

		; If any code is attached, doit
		(if code
			(code doit: self)
		)

		(if (& signal canUpdate)
			; If there's a viewer, let it set the view
			(if viewer	
				(viewer doit: self)
			)
		
			; If there's an avoider, let it do its thing
			(if avoider
				(avoider doit:)
			)

			; If there's a mover, give it the reins
			(if mover
				(mover doit:)
			)

			; If there's a cycler, let it have it's moment in the sun
			(if cycler
				(cycler doit:)
			)


			; Are we not now what we were?
			(if (& -info- GRAPH_UPD)  ;"non sum qualis eram"--Horace
				
				; It's scaler time
				(if scaler 
					(scaler doit:)
				)

				; Update last position
				(= xLast x)
				(= yLast y)

				; Update screen item
				(if (self isNotHidden:)
					(UpdateScreenItem self)
				)

				; If scale has changed, modify step size
				(if (and	
							(& scaleSignal scalable)
							(not (& scaleSignal noStepScale))
							(!= scaleX oldScaleX)
						)

					(= oldScaleX scaleX)
					(= txStep 
						(if 
								(= stepVal 
									(>> 
										(+ (* (>> origStep 8) scaleX)	halfScale) 
										scaleShift
									)
								)
							stepVal 
						else 
							1
						)
					)
					(= tyStep 
						(if 
								(= stepVal 
									(>> 
										(+ (* (& origStep $00ff) scaleY)	halfScale) 
										scaleShift
									)
								)
							stepVal 
						else 
							1
						)
					)
					(if
						(or
							(!= txStep xStep)
							(!= tyStep yStep)
						)
						(self setStep: txStep tyStep TRUE)
					)
				)
			
				; Set the new baseRect.
				(cond
					((not (& signal setBaseRect))
					)
					(baseSetter
						(baseSetter doit: self)
					)
					(else
						(BaseSetter self)
					)
				)
			)
		)
	)
	
	(method (posn newX newY)
		;
		; Attempt to place actor at x,y.  It will appear here on next
		; animation cycle
		
		(super posn: newX newY &rest)	;we may have passed a z too.

		(= xLast newX)
		(= yLast newY)
	)
	
	(method (setMotion mType)
		;
		; Perform mover actions, depending on mType:
		;	a) a class		- attach a dynamic instance of that class
		;	b) an instance - attach the instance
		;	c) 0				- remove current mover
		
		; Get rid of any old mover
		(if (and mover (!= mover -1))
			(mover dispose:)
		)
		
		; If a motion type was specified, attach and init it.  Otherwise,
		;	clear clear the mover.
		(if mType
			(= mover
				(if (& (mType -info-?) CLASS)
					(mType new:)
				else
					mType
				)
			)
			(mover init: self &rest)
		else
			(= mover 0)
		)
	)
	
	(method (setAvoider aType)
		;
		; Perform avoider actions, depending on aType:
		;	a) a class		- attach a dynamic instance of that class
		;	b) an instance - attach the instance
		;	c) 0				- remove current mover
		
		; If there is a current avoider, toss it
		(if avoider
			(avoider dispose:)
		)
		
		; If the avoider passed is a class, use an instance of it.  Otherwise,
		;	use the instance passed (or 0 if we're clearing the avoider)
		(= avoider
			(if (and aType (& (aType -info-?) CLASS))
				(aType new:)
			else
				aType
			)
		)
		
		; Initialize the avoider.
		(if avoider
			(avoider init: self &rest)
		)
	)
	
	(method (isStopped)
		;
		; Return TRUE if actor did not move in the previous animation cycle
		
		(return
			(or
				(not mover)
				(and
					(== x (mover xLast?))
					(== y (mover yLast?))
				)
			)
		)
	)
	
	(method (isBlocked)
		;
		; Return TRUE if actor tried to move, but couldn't
		
		(return (& signal blocked))
	)
	
	(method (dispose)

		(if robot
			(self setRobot:)
		)

		(if (!= mover -1)
			(self setMotion: 0)
		)

		(self setAvoider: 0)
		
		(if baseSetter
			(baseSetter dispose:)
			(= baseSetter 0)
		)
		(if looper
			(looper dispose:)
			(= looper 0)
		)
		(if viewer
			(viewer dispose:)
			(= viewer 0)
		)
		(if blocks
			(blocks dispose:)
			(= blocks 0)
		)
		(if code
			(code dispose:)
			(= code 0)
		)

		(super dispose:)
	)
	
	(method (ignoreHorizon arg)
		;
		; If arg is not present or nonzero, say that the actor can ignore the
		;	room's horizon.  If arg is zero, require the actor to observe it
		
		(if (or (not argc) arg)
			(|= signal ignrHrz)
		else
			(&= signal (~ ignrHrz))
		)
	)  
	
	(method (observeControl ctrl &tmp i)
		;
		; Set the controls which the actor cannot be on
		
		(for	((= i 0))
				(< i argc)
				((++ i))
			(|= illegalBits [ctrl i])
		)
	)
	
	(method (ignoreControl ctrl &tmp i)
		;
		; Set the controls which the actor can be on
		
		(for	((= i 0))
				(< i argc)
				((++ i))
			(&= illegalBits (~ [ctrl i]))
		)
	)
	
	(method (observeBlocks)
		;
		; Set the blocks (class Block) which an actor cannot be inside of
		
		; Make sure there is a set for the blocks
		(if (not blocks)
			(= blocks (Set new:))
		)
		(blocks add: &rest)
	)
	
	(method (ignoreBlocks)
		;
		; Delete specified blocks from those which an actor must stay out of
		
		(if blocks
			(blocks delete:&rest)
			(if (blocks isEmpty:)
				(blocks dispose:)
				(= blocks 0)
			)
		)
	)
	
	(method (distanceTo anObj)
		;
		; Return the distance from one actor to another
		
		(return
			(GetDistance x y (anObj x?) (anObj y?) perspective)
		)
	)
	
	(method (cantBeHere &tmp ret)
		;
		; Check the validity of a new point for the actor and returns:
		;	a)	obj ID or control color if blocked
		;	b) -1 if above horizon
		;	c) -2 if blocked by cage or block
		
		; Set the base rectangle which we check for collisions
		(cond
			((not (& signal setBaseRect))
			)
			(baseSetter 
				(baseSetter doit: self)
			)
			(else
				(BaseSetter self)
			)
		)
		
		; We can be blocked by:
		(= ret
			(cond
				; Returns obj id or control color of reason for blockage
				((CantBeHere self (cast elements?)))

				; Being above the horizon
				((and	(not (& signal ignrHrz))
						curRoom
						(< y (curRoom horizon?))
					)
					-1
				)
			
				; Being in conflict with a Block or Cage
				((and	blocks
						(not (blocks allTrue: #doit: self))
					)
					-2
				)
			)
		)
		(return ret)
	)
	
	(method (inRect lx uy rx by)
		;
		; Return TRUE if actor's origin is within the rectangle specified
		
		(return (and (<= lx x) (<= x rx) (<= uy y) (<= y by)))
	)
	
	(method (setStep xs ys leaveOriginal &tmp theX theY)
		;
		; Set an actor's stepsize to xs, ys.  If either xs or ys are -1, leave
		;	that stepsize as is.  If leaveOriginal is TRUE, the origStep will
		;	remain unchanged.
		;
		; 'origStep' is a packed word, wherein the high byte represents the
		;	original xStep and the low byte represents the original yStep
		
		(= theX (>> origStep 8))
		(= theY (& origStep $00ff))
		(if (and (>= argc 1) (!= xs -1))
			(= theX xs)
		)
		(if (and (>= argc 2) (!= ys -1))
			(= theY ys)
		)
		
		; If leaveOriginal is declared FALSE, change the origStep property
		(if (or 	(< argc 3)
					(not leaveOriginal)
			)
			(= origStep (+ (<< theX 8) theY))
		)

		(= xStep theX)
		(= yStep theY)

		; If the actor is controlled by a MoveTo or PolyPath, recompute the move
		;	based on the new stepsize
		(if (and	mover
					(or	(mover isMemberOf: MoveTo)
							(mover isMemberOf: PolyPath)
					)
			)
			(mover init:)
		)
	)
	
	(method (setDirection dir
					&tmp 	vx vy xIncr yIncr ang maxCoord pathPts obs xNode yNode
							planeW planeH)
		;
		; Set an actor to move off screen in a specified direction
		
		(= vy (curRoom vanishingY?))
		(= vx
			(if (== vy -30000)
				x
			else
				(curRoom vanishingX?)
			)
		)

		; Check for the pathological case of no step size for the Actor
		(if (and (== xStep 0) (== yStep 0))
			(return)
		)
		
		(= maxCoord (/ 32000 (Max xStep yStep)))
		
		(switch dir
			(dirStop
				(self setMotion: 0)
				(return)
			)
			(dirN
				(= xIncr (- vx x))
				(= yIncr (- vy y))
			)
			(dirS
				(= xIncr (- x vx))
				(= yIncr (- y vy))
			)
			(dirE
				(= xIncr maxCoord)
				(= yIncr 0)
			)
			(dirW
				(= xIncr (- maxCoord))
				(= yIncr 0)
			)
			(else	;we have a semi-quadrant
				;;as of 4/14/89 GetAngle is too inaccurate to use for
				;;anything but semi-quadrants, where accuracy is NOT
				;;critical. -- Pablo Ghenis
				;;dir is 2, 4, 6 or 8
				;;for 45, 135, 225, 315 if default (distant) vanishing point
				
				(if (< 180 (= ang (GetAngle x y vx vy)))
					(-= ang 360)
				)
				(= ang
					(+ (/ (+ ang 90) 2)	;1st quadrant bisection 
						(* 45 (- dir 2))	;add 90 degrees per extra quadrant
					)
				)
				(= xIncr	(SinMult ang 100))
				(= yIncr (- (CosMult ang 100)))		;y-axis is inverted
				
			)
		)

		; Scale up with caution to avoid overflow!
		(/= maxCoord 5)
		(while (and (< (Abs yIncr) maxCoord) (< (Abs xIncr) maxCoord))
			(*= xIncr 5)
			(*= yIncr 5)
		)

		;; go to first point of the unoptimized path returned by AvoidPath 
		;; which will be the first edge of any near of barred polygon in the 
		;; desired path, or the usual off screen point, then dispose of 
		;; the memory allocated by AvoidPath.

		(if (and (= obs (curRoom obstacles?)) useObstacles)
			(=	pathPts (IntArray new:))
			(= planeW (+ (- (plane right?) (plane left?)) 1))
			(= planeH (+ (- (plane bottom?) (plane top?)) 1))
			(pathPts copy:
					(AvoidPath x y (+ x xIncr) (+ y yIncr) obs planeW planeH FALSE)
			)
			(= xNode (pathPts at: 2))
			(= yNode (pathPts at: 3))
			(= xIncr (- xNode x)) 
			(= yIncr (- yNode y)) 

			(if altPolyList
				; Recalculate path unoptimized using alt polys and first node from
				;	previous path as destination
				(pathPts copy:
					(AvoidPath x y xNode yNode altPolyList planeW planeH FALSE)
				)
				; If the first node is different from our destination, then
				;	altPolys are involved
				(if (or	(!= (pathPts at: 2) (+ x xIncr))
							(!= (pathPts at: 3) (+ y yIncr))
					)
					(= xIncr (- (pathPts at: 2) x))
					(= yIncr (- (pathPts at: 3) y))
				)
			)
			(pathPts dispose:)
		)
	)
	
	(method (motionCue)
		(if (and mover (mover completed?))
			(mover motionCue:)
		)
		(super motionCue:)
	)
	
	(method (setLooper theLooper &tmp newLooper)
		;
		; Perform loop actions, depending on theLoop:
		;	a) class			- set the looper to a dynamic instance of that class
		;	b) instance		- set the looper to that instance

		(if looper
			(looper dispose:)
		)

		(if (and argc theLooper)
			(= newLooper
				(if (& (theLooper -info-?) CLASS)
					(theLooper new:)
				else
					theLooper
				)
			)
			((= looper newLooper) init: self &rest)
		)
	)

	(method (checkDetail theLevel)
		;
		; Check our detailLevel vs. the game's detailLevel to see if we should
		;	shut down/resume our animation

		(cond
			((not detailLevel)
			)
			((< (if argc theLevel else (theGame detailLevel?)) detailLevel)
				(&= signal (~ canUpdate))
			)
			(else
				(|= signal canUpdate)
			)
		)
	)

	(method (setHeading h whoCares)
		;
		; Turn the actor to a specified heading

		(if argc
			(= heading h)
		)
		(if looper
			(looper doit: self heading (if (>= argc 2) whoCares))
		else
			(DirLoop self heading)

			;updatecycler
			(if cycler (cycler clientLastCel: (self lastCel:)) )
 
			(if (and (>= argc 2) whoCares)
				(whoCares cue: &rest)
			)
		)
		(return heading)
	)

	(method (setSpeed theSpeed)
		(if argc
			(= moveSpeed (= cycleSpeed theSpeed))
		)
		(return moveSpeed)
	)

	(method (setRobot walkie passSelf &tmp ps) 
		;
		;Ex:
		;setRobot: [no params]	--dispose object w/o terminating robot
		;setRobot: 0 				--dispose object and terminate robot
		;setRobot: wt				--WalkieTalkie class or instance
		;setRobot: wt TRUE		--passes self to WalkieTalkie client
		;setRobot: wt t/f r#		--robot number passed to WalkieTalkie
		;setRobot: wt t/f r# who--who to cue when done
		;

		(if (and argc walkie)					    
			(= robot										 
				(if (& (walkie -info-?) CLASS)	 
					(walkie new:)						 
				else										 
					walkie								 
				)											 
			)												 
			(= ps 										 
				(if (and 								 
						(> argc 1) 
						passSelf
					) 
					self 
				else 
					NULL
				) 
			)
			(robot init: ps &rest)
		else
			(if robot
				(robot dispose: argc )
				(= robot 0)
			)
		)
	)
)