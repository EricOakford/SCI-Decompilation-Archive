;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	INSET.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Warren Schwader & Robert Lindsley
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	Classes:
;;;;		Inset


;****************************************************************************
;**
;** Inset.sc - Written by: Warren Schwader and Robert Lindsley
;**
;** An Inset (in concept) is a combination of a Room and a Dialog. 
;**
;** It is very similar to a Room in that you can do almost everything that
;**	you can do in a Room in the Inset.  It is unlike a room in that running
;**	the inset does not replace the current room but rather is literally
;**	stacked on top of the room.
;**
;** It is like a Dialog in that the whole Inset takes place & then the previous
;** 	condition of the room (or previous inset) is restored and picks up
;**	where it left off.
;**
;** Unlike a Dialog, the code following the inset init: will still take 
;**	place right after the inset is init'd.  To make things work correctly,
;**	the inset init: should be in it's own state in the rooms script.  When
;**	the inset is finished, it will cue the script (or any other object
;**	wanted) and then the room (or previous inset) will continue from where
;**	it left off.
;**
;** Insets may be nested.  Be alert that heap and hunk space should be
;**	considered.  Insets are erased automatically upon disposal of the Inset
;**	by restoring the screen from the Room up.
;**
;** Insets may encompass anywhere from a small portion of the screen up to the
;** 	full screen.  Insets that are full screen AND that are not an overlay
;** 	will be drawn to the screen in a FADEOUT style.  Other styles will
;**	require a change to the Kernel to prevent palette problems from being
;**	seen or requires that the new pic uses the same palette of the prev. pic.
;**   (in this case we could use a style property) Pictures that are overlays
;**	will be drawn PLAIN.
;**
;** Insets may be a picture or a view (or even both).
;**
;** Insets may dispose of any portion of the inset nested chain by using the
;**	setInset method.  Thus a deeply nested Inset may close all Insets by
;**	saying (curRoom setInset: 0).  You may parallel Insets by saying
;**	something like (curRoom setInset: newInset caller register)
;**
;** January 3, 1992 - BKH
;**	Added onMe and doVerb methods to Inset.  If picture or overlay, onMe will
;**		return TRUE, otherwise nsRect will be checked (which is set in the
;**		drawInset method).  Any keyDown or mouseDown outside of an Inset view
;**		will cause the Inset to dispose.
;**	
;******************************************************************************


(script#	INSET)
(include game.sh)
(use Main)
(use Actor)
(use System)


(class Inset kindof Code													  
	(properties
		picture			NULL	 ;Public. The PIC number which will be inset. 
									 ;Every inset may have a corresponding picture.
									 ; The picture can be overlayed if desired.

		anOverlay 		FALSE	 ;Public. Boolean. Set to TRUE if the picture should
									 ; be an overlay.
		style				PLAIN	 ;Public. The style in which to draw the picture.

		view				NULL	 ;Public. If view is greater than 0 then draw a
									 ; view in addition to a pic if any.
		loop				NULL	 ;Public.
		cel				NULL	 ;Public.
		x					NULL	 ;Public. Coordinates to DrawCel at (upper/left)
		y					NULL	 ;Public.
		priority			14		 ;Public.
		register			NULL	 ;Optional parameter passed to the inset (3rd) to
									 ; indicate the state of the inset (or other) can
									 ; set up the same inset conditionally based upon
									 ; register.

		hideTheCast		FALSE	 ;Public. Boolean. Set to TRUE if the cast at the
									 ; time of the inset init: should be hidden before
									 ; bringing up the inset.

		caller			NULL	 ;Semi-Private. Parameter. The object which should
									 ;	be notified (if any) when the inset is dispose.

		owner				NULL	 ;Semi-Private. Parameter. The room or Inset that
									 ;	told this inset to appear.

		script			NULL	 ;Semi-Private. Use Method setScript.
									 ;	The script attached to an inset.

		oldCast			NULL	 ;Private. The cast list in use before the inset
		oldFeatures		NULL	 ;Private. The features list in use before the inset
		oldATPs			NULL	 ;Private. The addToPics list in use before the inset
		oldMH				NULL	 ;Private. The mouseDownHandler list in use before the inset
		oldKH				NULL	 ;Private. The keyDownHandler list in use before the inset
		oldDH				NULL	 ;Private. The directionHandler list in use before the inset
		oldWH				NULL	 ;Private. The walkHandler list in use before the inset
		oldObstacles  	NULL	 ;Private. The obstacles list in use before the inset.
		oldStyle			NULL	 ;Private. The original style of the room's picture
		inset				NULL	 ;Private. This is used in the restore method to see 
									 ;	if there is another pic that needs to be drawn.
		disposeNotOnMe FALSE	 ;Private.  If we're a view & the user clicked
									 ; not onMe, should we dispose or just return FALSE?
		modNum			-1		 ;Public.   The module from which the messages come
		noun				NULL	 ;Public.	The noun by which the Inset is identified
		insetView		NULL	 ;Private.  The view used for an inset
	)
;;;	(methods
;;;		init						 ;Public. User defined. Include a super init at the proper time.
;;;		doit						 ;Public. User defined. Insets are added to theDoits list.
;;;		dispose					 ;Public. User defined. Include a super dispose at the proper time.
;;;									 ;			 insets dispose methods will be very similar to Rooms.
;;;  		setScript				 ;Public. Attach a script to an inset by means of this method. 
;;;		handleEvent				 ;Public. Can use an Inset's handleEvent the same as a Room's
;;;		doVerb					 ;Public. User defined.
;;;
;;;		hideCast					 ;Private. Hide or show the cast that was active at the time of the inset init:
;;;									 ;	Requires one parameter - If TRUE	do hide else show
;;;		drawInset				 ;Private. Draw the inset.
;;;		restore					 ;Private. Restore the inset after a restore game.
;;;		refresh					 ;Private. Redraw the previous Inset or Room.
;;;		setInset					 ;Public. Set another inset to this inset
;;;		onMe						 ;Public. Determines if inset should claim event
;;;	)

	;must write a setInset method for Room also.	May be the same code.
	(method (setInset theInset who reg)
		(if inset
			(inset dispose:)
		)
		(if (and argc theInset)
			(theInset init: (if (>= argc 2) who else 0) self (if (>= argc 3) reg else 0))
		)
	)
	(method (init whoCares theRoomOrInset reg)
		(= owner theRoomOrInset)
		(owner inset: self)
		(= register reg)
		(= caller whoCares)
		(if hideTheCast (self hideCast: TRUE))
		(= oldCast cast)
		(= oldFeatures features)
		(= oldATPs addToPics)
		(= oldMH mouseDownHandler)
		(= oldKH keyDownHandler)
		(= oldDH directionHandler)
		(= oldWH walkHandler)
		(= oldObstacles (curRoom obstacles?))
		(curRoom obstacles: ((List new:) add:, yourself:))
		((= cast (EventHandler new:))
			name: {newCast},
			add:
		)
		((= features (EventHandler new:))
			name: {newFeatures},
			add:	self
		)
		((= addToPics (EventHandler new:))
			name: {newATPs},
			add:
		)
		((= mouseDownHandler (EventHandler new:))
			name: {newMH},
			add: self
		)
		((= keyDownHandler (EventHandler new:))
			name: {newKH},
			add: self
		)
		((= directionHandler (EventHandler new:))
			name: {newDH},
			add: self
		)
		((= walkHandler (EventHandler new:))
			name: {newWH},
			add:
		)
		(theDoits add:	self)
		(self drawInset:)
	)
	(method (drawInset)
		(if (> picture 0)
			(DrawPic picture (if anOverlay PLAIN else style) (if anOverlay FALSE else TRUE))
		)
		(if view
			(= insetView
				((inView new:)
					view:				view,
					loop:				loop,
					cel:				cel,
					x:					x,
					y:					y,
					setPri:			priority,
					ignoreActors:	TRUE,
					init:				,
					yourself:
				)
			)
		)
	)
	(method (restore)
		(self drawInset:)
		(if inset 
			((inset oldATPs?) doit:)
			(inset restore:)
	  	)
	)
	(method (handleEvent event &tmp eType theItem)
		(cond
			((and	inset 
					(inset handleEvent: event)
				)
				NULL
			)
			((& (event type?) userEvent)
				(cond
					((self onMe: event)
						(event claimed: TRUE)
						(self doVerb: (event message?))
						(return TRUE)
					)
					(disposeNotOnMe
						(event claimed: TRUE)
						(self dispose:)
						(return TRUE)
					)
					(else
						(return FALSE)
					)
				)
			)
		)
	)
	(method (onMe theObjOrX theY &tmp oX oY)
		(if (IsObject theObjOrX)
			(= oX (theObjOrX x?))
			(= oY	(theObjOrX y?))
		else
			(= oX theObjOrX)
			(= oY theY)
		)
		(if view
			; Return onMe if within the nsRect
			(return (insetView onMe: theObjOrX theY))
		else
			; Always onMe if a picture or overlay
			(return TRUE)
		)
	)

	(method (doVerb theVerb)
		(if (== modNum -1)
			(= modNum curRoomNum)
		)
		(if (and	msgType
					(Message MsgGet modNum noun theVerb NULL 1)
				)
			(messager say: noun theVerb NULL NULL NULL modNum)
		)
	)

	(method (doit)
		(if script
			(script doit:)
		)
		(if (not hideTheCast)
			(Animate (oldCast elements?) FALSE)
		)
	)

	(method (refresh) ;refresh the screen from the Room up.
		(if view
			(DrawPic (curRoom picture?) PLAIN)
		else
	  		(DrawPic (curRoom picture?) style)
		)
		(curRoom style: oldStyle)
	  	(if (!= overlays -1)
	  		(DrawPic overlays PLAIN FALSE)
	  	)
		(if (curRoom inset?)
			((curRoom inset?) restore:)
	  	)
	)
	(method (dispose refreshOrNot &tmp whoCares)
		(features delete: self)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(directionHandler delete: self)
		(walkHandler delete: self)
		(theDoits delete: self)

		(if inset (inset dispose: FALSE))
		(cast 		
			eachElementDo:#dispose,
			eachElementDo:#delete,
			release:,
			dispose:
		)
		(addToPics dispose:)
		(features dispose:)
		(mouseDownHandler dispose:)
		(keyDownHandler dispose:)
		(directionHandler dispose:)
		(walkHandler dispose:)
		((curRoom obstacles?) dispose:)

		(owner inset: 0)
		(if (or (not argc) refreshOrNot)
			(self refresh:)
		)
		(if (or (not argc) refreshOrNot)
			((= addToPics oldATPs) doit:)
		)
		(curRoom obstacles: oldObstacles)
		(= cast oldCast)
		(= features oldFeatures)
		
		(= mouseDownHandler 	oldMH)
		(= keyDownHandler 	oldKH)
		(= directionHandler 	oldDH)
		(= walkHandler 		oldWH)
		(if hideTheCast (self hideCast: FALSE))
		(if (or (not argc) refreshOrNot)
			(if caller
				(= whoCares caller)
				(= caller 0)
				(whoCares cue:)
			)
		)
	)
	(method (setScript newScript)
		(if (IsObject script) (script dispose:))
		(= script (if argc newScript else 0))
		(if script (script init: self &rest))	
	)
	(method (hideCast hideOrShow &tmp i addValue)
		(for ((= i 0) (= addValue (if hideOrShow 1000 else -1000)))	(< i (cast size?)) ((++ i))
			((cast at: i) z: (+ ((cast at: i) z?) addValue))
		)
		(Animate (cast elements?) FALSE)
	)
)

(instance inView of View
	(method (handleEvent)
		(return FALSE)
	)
)


;WGS	NOTES about properties:
;WGS
;WGS	Public means that a property can be set by instances & other objects freely.
;WGS 	(Note: it is a good idea to try to have the instances do their own setting  
;WGS	 	of these properties whenever possible.)
;WGS		Usually these properties are of the type that can be set initially in a
;WGS		properties statement of an instance.
;WGS
;WGS	Semi-private means that a property can be set by means of a method or passed as a parameter.
;WGS		Also, these properties could be changed carefully for special needs.
;WGS
;WGS	Private means that a property is used internally and should not be changed 
;WGS		except inside the class itself.
