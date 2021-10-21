;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	ACTOR.SC
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: Jeff Stephenson
;;;;
;;;;	The classes in this module are the visible/animated objects on the screen.
;;;;
;;;;	Classes:
;;;;		PicView
;;;;		View
;;;;		Prop
;;;;		Actor
;;;;		Ego
;;;;		Block
;;;;		Cage


(script# ACTOR)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use System)


(class Feature kindof Object
	;
	;This class is used to keep track of room features that may need to respond 
	;to said events, even though visually they are just part of the background.
	;They should respond roughly in order of proximity to ego
	;
	(properties
		y 0								;current y position
		x 0								;current x position
		z 0								;current z position
											;	add to y to get "effective y"
		
		heading 0						;which way are we facing?
	)
;;;	(methods
;;;		handleEvent						;handle user input
;;;	)
	
	
	(method (handleEvent event)
		;; Game programmer must redefine this method.
		(return (event claimed?))
	)
)



(class PicView kindof Feature
	;;; This class is used to keep track of addToPics in the current room.
	;;; Instances of it are put in the addToPics Set for use by RestoreGame.
	
	(properties
		name "PV"
		view 0				;view number
		loop 0				;loop number
		cel 0					;cel number
		priority -1			;visual priority
		signal 0
	)
	
	(method (showSelf)
		(Print name #icon: view loop cel)
	)
	(method (init)
		(addToPics	add:self)
		;(super init:)
	)
	;(method (dispose)
	;	(addToPics	delete:self)
	;	(super dispose)
	;)
		
)




(class View kindof Feature
	;;; The view class contains the minimum functionality to put a visible
	;;; object on the screen in a reversible manner (i.e. so that it can
	;;; be erased).
	
	(properties
		yStep 2				;here for BaseSetter use only
		view 0				;view number
		loop 0				;loop number
		cel 0					;cel number
		priority	0			;priority of visible-obj
		underBits 0
		signal (| stopUpdOn staticView)
		nsTop 0				;nowSeen - current rectangle
		nsLeft 0
		nsBottom 0
		nsRight 0
		lsTop 0				;lastSeen - previous rectangle
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop	0				;base rectangle
		brLeft 0
		brBottom 0
		brRight 0
	)
	
;;;	(methods
;;;		init					;add actor to cast, once prepared for animation
;;;		posn					;position the object at a particular point
;;;		stopUpd				;stop updating (or force update of stopped) actor
;;;		forceUpd				;force one update of stopped actor
;;;		startUpd				;resume updating stopped actor
;;;		setPri				;alter priority (see method)
;;;		setLoop				;alter loop (see method)
;;;		setCel				;alter cel (see method)
;;;		ignoreActors		;condition signal 'ignrAct' bit
;;;		hide					;remove View from screen, but leave in cast
;;;		show					;show a hidden view
;;;		delete
;;;		addToPic				;convert object to a PicView
;;;		lastCel				;return number of last cel for this view
;;;		isExtra				;signal that object may be converted to PicView
;;;								;	if animation performance is too poor
;;;		motionCue
;;;	)
	
	
	(method (init)
		;; Prepare View and add to cast.
		
		;Make sure that we're not going to be deleted on the next animation cycle.
		(&= signal (~ delObj))
		
		;Set lastSeen to null rectangle if View is not currently in the cast.
		(if (not (cast contains:self))
			;Reset lastSeen rectangle.
			(= lsTop 0)
			(= lsLeft 0)
			(= lsBottom 0)
			(= lsRight 0)
			
			;Ensure that HIDE and HIDDEN bits are not on.
			(&= signal (~ (| hideActor actorHidden)))
		)
		
		;Set baseRect of the View.
		(BaseSetter self)
		
		;Add to cast.
		(cast add: self)
	)
	
	
	(method (posn newX newY newZ)
		;; Position the View at (newX, newY, newZ).  It will appear there on
		;; next animation cycle.
		
		(if (>= argc 1)			(= x newX)
			(if (>= argc 2)		(= y newY)
				(if (>= argc 3)	(= z newZ)
				)
			)
		)
		
		;Reset base rectangle.
		(BaseSetter self)
		
		;Force the change to the screen.
		(self forceUpd:)
	)
	
	
	(method (dispose)
		;; Signal object's imminent demise to (Animate), so that it will
		;; be removed from the screen.  Signal that object is to be deleted
		;; later.
		
		(self startUpd:, hide:)
		(|= signal delObj)
	)
	
	
	(method (hide)
		;; Hide the object (remove from screen without removing from cast).
		
		(|= signal hideActor)
	)
	
	
	(method (show)
		;; Show a hidden object.
		
		(&= signal (~ hideActor))
	)
	
	
	(method (delete)
		;; This method is called during each animation cycle to purge the
		;; cast of dispose:d actors.
		
		;Are we slated for deletion?
		(if (& signal delObj)
			;If this is an addToPic, make sure we save the state before deleting.
			(if (& signal viewAdded)
				(addToPics add:
					((PicView new:)
						view: view,
						loop: loop,
						cel: cel,
						x: x,
						y: y,
						z: z,
						priority: priority,
						signal: signal,
						yourself:
					)
				)
			)
			
			;Ensure that this signal is not hanging around.
			(&= signal (~ delObj))
			
			;Remove the object from the cast.
			(cast delete:self)
			
			;Unload the underbits (saved background bitmap) in case
			;(Animate) didn't.
			(if underBits
				(UnLoad MEMORY underBits)
				(= underBits NULL)
			)
			
			(super dispose:)
		)
	)
	
	
	;;; stopUpd:, forceUpd:, and startUpd: rely upon the kernel (Animate)
	;;; to resolve the state of updating and hidden status.
	
	(method (stopUpd)
		;; Set signal bits to request a stop update.
		(|= signal stopUpdOn)
		(&= signal (~ startUpdOn))
	)
	
	
	(method (forceUpd)
		;; Set signal to request one forced update.
		(|= signal forceUpdOn)
	)
	
	
	(method (startUpd)
		;; Set signal bits to request a start update.
		(|= signal startUpdOn)
		(&= signal (~ stopUpdOn))
	)
	
	
	(method (setPri newPri)
		;; Action depends on the presence/value of newPri:
		;;		not present		fix object's priority at its current value
		;;		-1					let (Animate) determine object's priority
		;;							based on its x,y position
		;;		else				fix object's priority at the specified value
		
		(cond
			((== argc 0)
				(|= signal fixPriOn)
			)
			((== newPri -1)
				(&= signal (~ fixPriOn))
			)
			(else
				(= priority newPri)
				(|= signal fixPriOn)
			)
		)
		
		;Force the change to the screen.
		(self forceUpd:)
	)
	
	
	(method (setLoop newLoop)
		;; Action depends on the presence/value of newLoop:
		;;		not present		fix object's loop at its current value
		;;		-1					let a Motion class determine the object's loop
		;;							based on its current direction
		;;		else				fix object's loop at the specified value
		
		(cond
			((== argc 0)
				(|= signal fixedLoop)
			)
			((== newLoop -1)
				(&= signal (~ fixedLoop))
			)
			(else
				(= loop newLoop)
				(|= signal fixedLoop)
			)
		)
		
		;Force the change to the screen.
		(self forceUpd:)
	)
	
	
	(method (setCel newCel)
		;; Action depends on the presence/value of newCel:
		;;		not present		fix object's cel at its current value
		;;		-1					let a Cycle class determine the object's cel
		;;		else				fix object's cel at the specified value
		
		(cond
			((== argc 0)
				(|= signal fixedCel)
			)
			((== newCel -1)
				(&= signal (~ fixedCel))
			)
			(else
				(|= signal fixedCel)
				(= cel 
					(if (>= newCel (self lastCel:))
						(self lastCel:)
					else
						newCel
					)
				)	
			)
		)
		
		;Force the change to the screen.
		(self forceUpd:)
	)
	
	
	(method (ignoreActors arg)
		;; No or nonzero argument says that this object should not collide
		;; with other animated objects.  A FALSE argument says that it should
		;; collide.
		
		(if (or (== 0 argc) arg)
			(|= signal ignrAct)
		else
			(&= signal (~ ignrAct))
		)
	)  
	
	
	(method (addToPic)
		;; This signals that the object should be converted to a PicView.
		
		(if (not (cast contains: self))
			(self init:)
		)
		(self signal:(| signal ADDTOPIC))
	)
	
	
	(method (lastCel)
		;; Return the number of the last cel in the current loop of this object.
		
		(return (- (NumCels self) 1))
	)
	
	
	(method (showSelf)
		(Print name #icon: view loop cel)
	)
	
	
	(method (isExtra value &tmp ret)
		;; On slow machines, animation performance may be terrible.  This method
		;; allows one to designate the object as one whose animation is not
		;; critical and which may therefore be converted to a PicView if
		;; necessary.  Returns the previous value of the 'extra' bit.  See also
		;; class Extra.
		
		(= ret (& signal anExtra))
		(if argc
			(if value
				(|= signal anExtra)
			else
				(&= signal (~ anExtra))
			)
		)
		(return ret)
	)
	
	
	(method (motionCue)
		;Does nothing in a View.
	)
)





(class Prop kindof View
	;;; The Prop class adds cycling capability to the View class.
	;;; It also introduces the ability to have an attached script
	;;; which determines its behaviour.
	
	(properties
		cycleSpeed 0	;0 (fastest) to n (slowest)
		script 0			;object ID of script
		cycler 0			;object ID of cycle code
		timer 0			;object ID of an attached Timer
		signal 0
	)
	
;;;	(methods
;;;		setCycle			;attach an instance of a Cycle class
;;;		setScript		;attach a script
;;;		cue				;cue the object (or its script)
;;;	)
	
	
	(method (doit &tmp aState)
		;; This is the method called in every animation cycle
		;; via the "eachElementDo" method of the cast.
		
		;If we are scheduled for deletion we simply return
		(if (& signal delObj)
			(return)
		)
		
		(if script 
			;Give the script a shot.
			(script doit:)
		)
		
		;If we are not updating (or about to start) we return
		(if (and (& signal notUpd) (not (& signal startUpdOn)))
			(return)
		)
		
		(if cycler
			;Cycle the prop.
			(cycler doit:)
		)
	)
	
	
	(method (handleEvent event)
		;; Default event handling is to just pass the event along to
		;; an existing script.
		
		
		(if script
			(script handleEvent:event)
		)
		(return	(event claimed?))
	)
	
	
	(method (setCycle cType)
		;; Attach an instance of a Cycle class to actor.
		
		;Dispose of existing cycle code
		(if cycler
			(cycler dispose:)
		)
		
		(if cType
			;If a Cycling class was specified, attach an instance of the
			;class to this object and initialize it.
		
			;Release our cel and make sure it's updating on the screen.
			;(self setCel:-1)
			(self startUpd:)
			(= cycler (cType new:))
			(cycler init: self &rest)
		else
			;If cType is NULL, clear the cycler property.
			(= cycler 0)
		)
	)
	
	
	(method (delete)
		(if (& signal delObj)
			;Dispose of any attached objects.
			(self setScript:0, setCycle:0)
			(if timer
				(timer dispose:)
			)
			
			(super delete:)
		)
	)
	
	
	(method (cue)
		;; Cue this object's script.
		(if script
			(script cue:)
		)
	)
	
	(method (setScript newScript)
		;; Attach a new script to this object, removing any existing one.
		
		(if (IsObject script)		(script dispose:))
		(if (= script newScript)
			((= script newScript) init: self &rest)
		)
	)
	
	
	(method (motionCue)
		(if (and cycler (cycler completed?))
			(cycler motionCue:)
		)
	)
)




(class Actor kindof Prop
	;;; The Actor class introduces the ability to move to animated objects.
	
	(properties
		name "Act"
		illegalBits $8000
		
		xLast 0				;previous x position
		yLast 0				;previous y position
		xStep 3				;how far actor moves in x direction
		yStep 2				;how far actor moves in y direction
		;heading 0			;compass degrees of current motion 
								;	(inherited from Feature class)
		moveSpeed 0			;0 (fastest) to n (slowest)
		blocks 0				;any blocks relating to this actor
		baseSetter 0		;ID of Code which sets this objects base rectangle
		mover 0				;object ID of motion code
		looper 0				;set actor's loop based on direction or whatever
		viewer 0				;set actor's view based on some criterion
		avoider 0			;code for getting around obstructions
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
;;;		findPosn				;search for a legal position when baffled
;;;		inRect				;return TRUE if Actor is in a given rectangle
;;;		onControl			;return controls which an Actor is on
;;;		distanceTo			;return distance from actor to actor
;;;		canBeHere			;return TRUE if current position is legal
;;;		setStep				;set Actor's step size
;;;		setDirection		;set Actor's direction of motion
;;;	)
	
	
	(method (init)
		;; Prepare actor for animation and add to cast.
		
		(super init:)
		
		;Make previous X/Y == to current X/Y
		(= xLast x)
		(= yLast y)
	)
	
	
	(method (doit &tmp aState left right)
		;; This is the method called in every animation cycle
		;; via the "eachElementDo" method of the cast.
		
		;If we are scheduled for deletion we simply return.
		(if (& signal delObj)
			(return)
		)
		(&= signal (~ blocked))		;clear the blocked bit
		
		(if script 
			(script doit:)
		)
		
		;If we are not updating (or about to start) we return.
		(if (and (& signal notUpd) (not (& signal startUpdOn)))
			(return)
		)
		
		;Determine which view of the actor to display.
		(if viewer	
			(viewer doit: self)
		)
		
		;Move the actor.  An avoider (which will call the mover itself)
		;takes precedence over a mover.
		(cond
			(avoider
				(avoider doit:)
			)
			(mover
				(mover doit:)
			)
		)
		
		;Now cycle the object.
		(if cycler
			;Get the current edges of the baseRect.
			(= left brLeft)
			(= right brRight)
			
			(cycler doit:)
			
			;Set the new baseRect.
			(if baseSetter 
				(baseSetter doit: self)
			else
				(BaseSetter self)
			)
			
			;If the baseRect has been changed by cycling, make sure the
			;Actor can be here.
			(if
				(and
					(or
						(!= left brLeft)
						(!= right brRight)
					)
					(not (self canBeHere:))
				)
				
				(self findPosn:)
			)
		)
		
		;Update last position.
		(= xLast x)
		(= yLast y)
	)
	
	
	(method (posn newX newY)
		;; Attempt to place actor at x,y.  It will appear here on next
		;; animation cycle
		
		(super posn: newX newY &rest)	;we may have passed a z too.
		(= xLast newX)
		(= yLast newY)
		(if (not (self canBeHere:))
			(self findPosn:)
		)
	)
	
	
	(method (setMotion mType &tmp [str 40])
		;; Attach a motion class to the actor.
		
		;Get rid of any old mover.
		(if (and mover (!= mover -1))
			(mover dispose:)
		)
		
		;If a motion type was specified, attach and init it.  Otherwise,
		;clear clear the mover.
		(if mType
			(self startUpd:)
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
		;; Attach an avoider to the actor.
		
		;If there is a current avoider, toss it.
		(if avoider
			(avoider dispose:)
		)
		
		;If the avoider passed is a class, use an instance of it.  Otherwise,
		;use the instance passed (or 0 if we're clearing the avoider).
		(= avoider
			(if (and (IsObject aType) (& (aType -info-?) CLASS))
				(aType new:)
			else
				aType
			)
		)
		
		;Initialize the avoider.
		(if avoider
			(avoider init: self &rest)
		)
	)
	
	
	(method (isStopped)
		;; Return TRUE if actor did not move in the previous animation cycle.
		
		(cond 
			((not (IsObject mover)))
			((== x xLast) (if (== y yLast) (mover triedToMove:)))
		)
	)
	
	
	(method (isBlocked)
		;; Return TRUE if actor tried to move, but couldn't.
		
		(return (& signal blocked))
	)
	
	
	(method (delete)
		;; Actually delete the actor.  The dispose: method just marks the actor
		;; for deletion, but leaves it in the cast so that it can be removed
		;; from the screen.  This does the dirty work of real disposal.
		
		(if (& signal delObj)
			(if (!= mover -1)
				(self setMotion:0)
			)
			(self setAvoider:0)
			
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
			
			(super delete:)
		)
	)
	
	
	(method (ignoreHorizon arg)
		;; If arg is not present or nonzero, say that the actor can ignore
		;; the rooms horizon.  If arg is zero, require the actor to observe it.
		
		(if (or (not argc) arg)
			(|= signal ignrHrz)
		else
			;Clear the bit
			(&= signal (~ ignrHrz))
		)
	)  
	
	
	(method (observeControl ctrl &tmp i)
		;; Set the controls which the actor cannot be on.
		
		(for	((= i 0))
			(< i argc)
			((++ i))
			
			(|= illegalBits [ctrl i])
		)
	)
	
	
	(method (ignoreControl ctrl &tmp i)
		;; Set the controls which the actor can be on.
		
		(for	((= i 0))
			(< i argc)
			((++ i))
			
			(&= illegalBits (~ [ctrl i]))
		)
	)
	
	
	(method (observeBlocks)
		;; Set the blocks (class Block) which an actor cannot be inside of.
		
		;Make sure there is a set for the blocks.
		(if (not blocks)
			(= blocks (Set new:))
		)
		(blocks add: &rest)
	)
	
	
	(method (ignoreBlocks)
		;; Delete specified blocks from those which an actor must stay out of.
		
		(blocks delete:&rest)
		(if (blocks isEmpty:)
			(blocks dispose:)
			(= blocks 0)
		)
	)
	
	
	(method (distanceTo anObj)
		;; Return the distance from one actor to another.
		
		(return
			(GetDistance
				x				y
				(anObj x?)	(anObj y?) 
				perspective
			)
		)
	)
	
	
	(method (canBeHere)
		;; We have chosen a new point for the actor.  Check its validity.
		
		;Set the base rectangle which we check for collisions.
		(if baseSetter 
			(baseSetter doit: self)			;user-supplied code
		else
			(BaseSetter self)					;use the default kernel call
		)
		
		;We can be blocked by:
		(return 
			(and
				(CanBeHere self (cast elements?))
				
				;;Being above the horizon.
				(or
					(& signal ignrHrz)
					(not (IsObject curRoom))
					(>= y (curRoom horizon?))
				)
				
				;Being in conflict with a Block or Cage.
				(or	
					(== blocks NULL)
					(blocks allTrue: #doit: self)
				)
				
			)
		)
	)
	
	
	(method (findPosn &tmp legDir legLen xOrg yOrg goodPosn)
		;; The actor cannot be in its current position (findPosn: is FALSE).
		;; Search for a legal position for the object.  Bad positions are
		;; often caused by baseRect width changes, so x-axis is given
		;; biggest search range
		
		;Save actor's current position.
		(= xOrg x)
		(= yOrg y)
		(= goodPosn FALSE)
		
		;Search in a square pattern of increasing size.  The size of the square
		;legLen, the part being checked (the corners and middle of each side)
		;is given by legDir.
		(for	((= legLen 1))
			(not goodPosn)
			((++ legLen))
			
			(for	((= legDir 0))
				(and (not goodPosn) (< legDir 8))
				((++ legDir))
				
				;these two lines replace an 8-way switch, save 300+ bytes -Pablo
				(= x (+ xOrg (* legLen (sign (TimesCos (* legDir 45) 100)))))
				(= y (- yOrg (* legLen (sign (TimesSin (* legDir 45) 100)))))
				
				
				(= goodPosn
					(and
						(self canBeHere:)
						
						;;; Why is this here?
						;Must be on some control.
						;Needs a real fix later to avoid room exit.
						(self onControl:)
						
						;;; This should be handled by the canBeHere: check.
						;						;Ignoring or being below horizon.
						;						(or
						;							(& signal ignrHrz)
						;							(>= y (curRoom horizon?))
						;						)
					)
				)
			)
		)
		
		;Just to be legal.
		(self posn: x y)
	)	
	
	
	(method (inRect lx uy rx by)
		;; Return TRUE if actor's origin is within the rectangle bounded
		;; by lx (left x), uy (upper y), rx (right x), by (bottom y).
		
		(return (and (<= lx x) (< x rx) (<= uy y) (< y by)))
	)
	
	
	(method (onControl org)
		;; If org is present and nonzero, return the control under the actor's
		;; origin.  Otherwise, return a bit-mapped word indicating the controls
		;; which the actors baseRect is on.
		
		(return
			(if (and argc org)
				(OnControl CMAP x y)
			else
				(OnControl CMAP brLeft brTop brRight brBottom)
			)
		)
	)
	
	
	(method (setStep xs ys)
		;; Set an actor's stepsize to xs,ys.  If either xs or ys are -1,
		;; leave that stepsize as is.
		
		(if (and (>= argc 1) (!= xs -1))
			(= xStep xs)
		)
		(if (and (>= argc 2) (!= ys -1))
			(= yStep ys)
		)
		
		;If the actor is controlled by a MoveTo, recompute the move based
		;on the new stepsize.
		(if
			(and
				mover
				(!= -1 mover)
				(mover isMemberOf: MoveTo)
			)
			((self mover?) init:)	;re-init the mover
		)
	)
	
	(method (setDirection dir 
			&tmp vx vy xIncr yIncr ang
		)
		;; Set an actor's motion to be to a point off the screen in a given
		;; direction.
		
		(= vx (curRoom vanishingX?))
		(= vy (curRoom vanishingY?))
		
		(switch dir
			(dirStop
				(self setMotion:0)
				(return)
			)
			(dirN
				(= xIncr (- vx x))
				(= yIncr (- vy y))
			)
			(dirS							;opposite of dirN
				(= xIncr (- x vx))
				(= yIncr (- y vy))
			)
			(dirE
				(= xIncr 10000)
				(= yIncr 0)
			)
			(dirW
				(= xIncr -10000)
				(= yIncr 0)
			)
			(else	;WE HAVE A SEMI-QUADRANT
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
				(= xIncr	(TimesSin ang 100))
				(= yIncr (- (TimesCos ang 100)))		;y-axis is inverted
				
			)
		)
		(while (and (< (Abs yIncr) 1000) (< (Abs xIncr) 1000))
			(*= xIncr 10)
			(*= yIncr 10)
		)
		
		(self setMotion: MoveTo 
			(+ x xIncr)
			(+ y yIncr)
		)
	);setDirection
	
	
	
	(method (motionCue)
		(if (and mover (mover completed?))
			(mover motionCue:)
		)
		(super motionCue:)
	)
	
	(method (setLoop l &tmp newLooper)
		(if 
			(= newLooper
				(cond
					((== argc 0) 
						(super setLoop:)
						NULL
					)
					((not (IsObject l))
						(super setLoop: l &rest)
						NULL
					)
					((& (l -info-?) CLASS)
						(l new:)
					)
					(else	;we have an instance
						l
					)
				)
			)
			(if looper
				(looper dispose:)
			)
			((= looper newLooper) init: self &rest)
x		)
	);setLoop
	
);Actor	






(class Block kindof Object
	;;; A Block is a rectangle meant to exclude Actors.  'theActor' is required
	;;; to stay out of a 'theBlock' by
	;;;	(theActor observeBlocks: theBlock  ...)
	;;; Similarly,
	;;;	(theActor ignoreBlocks: theBlock ...)
	;;; will allow the Actor to be inside the block once more.
	
	(properties
		name "Blk"
		top 0				;coordinates of block edges
		left 0
		bottom 0
		right 0
	)
	
	
	(method (doit theObj)
		(return
			(or
				(<= (theObj brBottom?) top)
				(> (theObj brTop?) bottom)
				(< (theObj brRight?) left)
				(>= (theObj brLeft?) right)
			)
		)
	)
)
