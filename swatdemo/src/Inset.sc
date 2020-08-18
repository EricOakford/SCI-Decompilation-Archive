;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	INSET.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1994
;;;;
;;;;	Author: 	Warren Schwader & Robert W. Lindsley
;;;;	Updated:	Brian K. Hughes
;;;;	Extensively Modified:	Tom DeSalvo 3/31/94
;;;;	
;;;;	An Inset is a self-contained structure that appears over the current
;;;;	room.
;;;;
;;;;	Classes:
;;;;		Inset
;;;;  Usage:
;;;;  	See bottom of file

(script#	INSET)
(include game.sh)
(use Main)
(use Actor)
(use System)


(class Inset kindof Object
	(properties
		picture			-1		 ;Public. The PIC number which will be inset. 

		style				SHOW_PLAIN	 ;Public. The style in which to draw the picture.
		view				-1		 ;Public. If view is greater than 0 then draw a
									 ; view in addition to a pic if any.
		loop				NULL	 ;Public.
		cel				NULL	 ;Public.
		x					-1	 	 ;Public. Coordinates to draw plane at (upper/left)
		y					-1	 	 ;Public.
		priority			14		 ;Public.
		register			NULL	 ;Optional parameter passed to the inset 
		caller			NULL	 ;Semi-Private. Parameter. The object which should
									 ;	be notified (if any) when the inset is dispose.

		client			NULL	 ;Semi-Private. Parameter. The room or Inset that
									 ;	told this inset to appear.

		script			NULL	 ;Semi-Private. Use Method setScript.
									 ;	The script attached to an inset.

		oldFeatures		NULL	 ;Private. The features list in use before the inset
		oldMH				NULL	 ;Private. The mouseDownHandler list in use before the inset
		oldKH				NULL	 ;Private. The keyDownHandler list in use before the inset
		oldDH				NULL	 ;Private. The directionHandler list in use before the inset
		oldWH				NULL	 ;Private. The walkHandler list in use before the inset
		oldObstacles  	NULL	 ;Private. The obstacles list in use before the inset.
		inset				NULL	 ;Private. This is used in the restore method to see 
									 ;	if there is another pic that needs to be drawn.
		disposeNotOnMe FALSE	 ;Private.  If we're a view & the user clicked
									 ; not onMe, should we dispose or just return FALSE?
		modNum			-1		 ;Public.   The module from which the messages come
		noun				NULL	 ;Public.	The noun by which the Inset is identified
		plane				NULL
		oldTimers		NULL
		oldHotCast     NULL
	)
;;;	(methods
;;;		init						 ;Public. User defined. Include a super init at the proper time.
;;;		doit						 ;Public. User defined. Insets are added to regions list.
;;;		dispose					 ;Public. User defined. Include a super dispose at the proper time.
;;;									 ;			 insets dispose methods will be very similar to Rooms.
;;;  		setScript				 ;Public. Attach a script to an inset by means of this method. 
;;;		handleEvent				 ;Public. Can use an Inset's handleEvent the same as a Room's
;;;		doVerb					 ;Public. User defined.
;;;
;;;		drawInset				 ;Private. Draw the inset.
;;;		restore					 ;Private. Restore the inset after a restore game.
;;;		setInset					 ;Public. Set another inset to this inset
;;;		onMe						 ;Public. Determines if inset should claim event
;;;	)
	(method (setInset theInset who reg delay)
		(if inset
			(inset dispose:)
 			(= inset 0)
		)
		(if (and argc theInset)
			(theInset init: 
				(if (>= argc 2) who else 0) 
				self 
				(if (>= argc 3) reg else 0) 
				(if (>= argc 4) delay else 0))
		)
	)
	(method (init whoCares theRoomOrInset reg delayDraw &tmp delay i)
		(= client theRoomOrInset)
		(client inset: self)
		(= caller whoCares)
		(= register (if ( > argc 2) reg else 0))
		(= delay (and (> argc 3) delayDraw))
		; Save off the old handlers & create new ones
		(= oldFeatures features)
		(= oldMH mouseDownHandler)
		(= oldKH keyDownHandler)
		(= oldDH directionHandler)
		(= oldWH walkHandler)
		(= oldObstacles (curRoom obstacles?))
		(= oldTimers timers)
	  	((= timers (Set new:))
			name: {inTimers},
			add:
		)
		(curRoom obstacles: ((List new:) add:, yourself:))

      (for ((= i 0)) (< i (regions size?)) ((++ i))
            (oldRegions add: (regions at: i))
      )
      (regions release:)
		(regions add: self)
		((= features (EventHandler new:))
			name: {inFeatures}
			add:
		)
		((= mouseDownHandler (EventHandler new:))
			name: {inMH},
			add: self 			;so events can go to this inset's inset if present
		)
		((= keyDownHandler (EventHandler new:))
			name: {inKH},
			add: self
		)
		((= directionHandler (EventHandler new:))
			name: {inDH},
			add: self
		)
		((= walkHandler (EventHandler new:))
			name: {inWH},
			add:
		)
		; Create the plane
		(if (not plane)
			(= plane (thePlane new:))
		)
		(plane
			priority:	priority,
			picture: 	picture,
			style: 		style,
			init:			,
			addCast:		(= cast (Cast new:))
		)
		(= thePlane plane)
		(if (not delay)
			(self drawInset:)
		)
	)
	(method (drawInset)
		(if (!= picture -1)
			(plane drawPic: picture)
		)
		(plane 
			posn:			x y,
			priority: 	(+ (GetHighPlanePri) 1)
		)
		(if (not (== view -1))
			(inView
				view:				view,
				loop:				loop,
				cel:				cel,
				setPri:			0,
				ignoreActors:	TRUE
			)
			(plane setBitmap: inView 0)	;so onMe can handle skip check
		)
		(UpdatePlane plane)
		(FrameOut)
	)
	(method (restore)
		(self drawInset:)
		(if inset 
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

		(if (== argc 1)			;it's an object
			(theObjOrX localize: plane)
			(= oX (theObjOrX x?))
			(= oY	(theObjOrX y?))
		else
			(= oX theObjOrX)
			(= oY theY)
		)
		(if (not (== view -1))
			; Return onMe if within the nsRect
			(if (== argc 1)
				(return ((plane bitmap?) onMe: theObjOrX))
			else
				(return ((plane bitmap?) onMe: theObjOrX theY))
			)
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
		else
			(theGame pragmaFail:)
		)
	)
	(method (doit)
		(if script
			(script doit:)
		)
	)
	(method (dispose refreshOrNot &tmp whoCares i)
		(regions 			delete: self)
		(features 			delete: self)
		(mouseDownHandler delete: self)
		(keyDownHandler 	delete: self)
		(directionHandler delete: self)
		(walkHandler 		delete: self)
		(if inset
			(inset dispose: FALSE)
			(= inset 0)
		)

	   (mouseDownHandler dispose:) 	;be sure you have deleted any objects
		(keyDownHandler 	dispose:) 	;that you added to these lists.
		(directionHandler dispose:) 	;only dispose those objects if they are
		(walkHandler 		dispose:) 	;not in the cast or features lists.
	   (cast eachElementDo: #dispose:) ;or they will be re-disposed here

		(plane dispose:)
		(= plane 0)

		(timers dispose:)
		(cast									
 			plane: 0,
			dispose:
		)					
		(features dispose:)
      (for ((= i 0)) (< i (oldRegions size?)) ((++ i))
    	  	(regions add: (oldRegions at: i))
      )

      (oldRegions release:, dispose:)
		((curRoom obstacles?) 	dispose:)
		(client inset: 0)
		(curRoom obstacles: oldObstacles)
		(= cast (((curRoom plane?) casts?) at: 0))
		(= thePlane (curRoom plane?))
		(= features 			oldFeatures)
		(= mouseDownHandler 	oldMH)
		(= keyDownHandler 	oldKH)
		(= directionHandler 	oldDH)
		(= walkHandler 		oldWH)
		(= timers oldTimers)
		(= oldTimers FALSE)

		(if (or (not argc) refreshOrNot)
			(if caller
				(= whoCares caller)
				(= caller 0)
				(whoCares cue:)
			)
		)
	)
	(method (setScript newScript)
		(if script
			(script dispose:)
		)
		(= script (if argc newScript else 0))
		(if script
			(script init: self &rest)
		)
	)
)

(instance inView of View
	(method (handleEvent)
		(return FALSE)
	)
)

(instance oldRegions of List)




;;;******************** USAGE *********************************************
;;;
;;;
;;;Typical case:
;;;
;;;(curRoom setInset: insetInstance whoToCue <register>)
;;;
;;;(instance insetInstance of Inset
;;;	(properties
;;;		view	 			;for bitmap
;;;		loop
;;;		cel
;;;		x					;default will center
;;;		y
;;;	)
;;;)
;;;
;;;Note: we can also use a picture by just setting the picture property.
;;;
;;;
;;;Case where we have Views within inset:
;;;
;;;(curRoom setInset: insetInstance whoToCue register TRUE)
;;;
;;;The register parameter is a scratch property.
;;;The last parameter tells the init of the Inset class not to call the
;;;drawInset: method.  This is so the bitmap doesn't showup first and then
;;;the Views can be seen being drawn afterwards.  So if you have Views other 
;;;than the bitmap in your inset your code will look like:
;;;
;;;Note: if your inset doesn't have a picture, do not pass the fourth
;;;parameter, or you will get a black plane. The fourth parameter is 
;;;only passed if you have a picture and views that you want to appear
;;;simultaneously.
;;;
;;;(curRoom setInset: insetInstance whoToCue register TRUE)
;;;
;;;(instance insetInstance of Inset
;;;	(properties
;;;		view	 			;for bitmap
;;;		loop
;;;		cel
;;;		x					;default will center
;;;		y
;;;	)
;;;	(method (init)
;;;		(super init: &rest)
;;;		(addedView init:)
;;;		(self drawInset:)
;;;	)
;;;)
;;;
;;;Of course any Views that you want to show up after the inset is up
;;;can be init:'ed normally and will be disposed when the Inset is disposed
