;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64923)
(include sci.sh)
(use Main)
(use Plane)
(use Actor)
(use System)


(class Inset of Obj
	(properties
		scratch 0
		picture -1
		anOverlay 0
		style $0000
		view -1
		loop 0
		cel 0
		x -1
		y -1
		priority 14
		register 0
		caller 0
		client 0
		script 0
		oldFeatures 0
		oldMH 0
		oldKH 0
		oldDH 0
		oldWH 0
		oldObstacles 0
		oldStyle 0
		inset 0
		disposeNotOnMe 0
		modNum -1
		noun 0
		plane 0
		insetView 0
	)
	
	(method (init theCaller theClient theRegister)
		(= client theClient)
		(client inset: self)
		(= register theRegister)
		(= caller theCaller)
		(= oldFeatures features)
		(= oldMH mouseDownHandler)
		(= oldKH keyDownHandler)
		(= oldDH directionHandler)
		(= oldWH walkHandler)
		(= oldObstacles (curRoom obstacles?))
		(curRoom obstacles: ((List new:) add: yourself:))
		((= features (EventHandler new:))
			name: {inFeatures}
			add: self
		)
		((= mouseDownHandler (EventHandler new:))
			name: {inMH}
			add: self
		)
		((= keyDownHandler (EventHandler new:))
			name: {inKH}
			add: self
		)
		((= directionHandler (EventHandler new:))
			name: {inDH}
			add: self
		)
		((= walkHandler (EventHandler new:)) name: {inWH} add:)
		(= plane
			(if (not plane) (thePlane new:) else (Plane new:))
		)
		(plane
			priority: priority
			picture: picture
			style: style
			init:
			addCast: (= cast (Cast new:))
		)
		(theDoits add: self)
		(self drawInset:)
	)
	
	(method (doit)
		(if script (script doit:))
	)
	
	(method (dispose param1 &tmp theCaller)
		(features delete: self)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(directionHandler delete: self)
		(walkHandler delete: self)
		(theDoits delete: self)
		(if inset (inset dispose: 0))
		(plane dispose:)
		(cast release: plane: 0 dispose:)
		(features dispose:)
		(mouseDownHandler dispose:)
		(keyDownHandler dispose:)
		(directionHandler dispose:)
		(walkHandler dispose:)
		((curRoom obstacles?) dispose:)
		(client inset: 0)
		(curRoom obstacles: oldObstacles)
		(= cast (((curRoom plane?) casts?) at: 0))
		(= features oldFeatures)
		(= mouseDownHandler oldMH)
		(= keyDownHandler oldKH)
		(= directionHandler oldDH)
		(= walkHandler oldWH)
		(if (and (or (not argc) param1) caller)
			(= theCaller caller)
			(= caller 0)
			(theCaller cue:)
		)
	)
	
	(method (setScript newScript)
		(if script (script dispose:))
		(= script (if argc newScript else 0))
		(if script (script init: self &rest))
	)
	
	(method (handleEvent event &tmp [temp0 2])
		(return
			(cond 
				((and inset (inset handleEvent: event)) 0)
				((& (event type?) evVERB)
					(cond 
						((self onMe: event)
							(event claimed: 1)
							(self doVerb: (event message?))
							(return 1)
						)
						(disposeNotOnMe (event claimed: 1) (self dispose:) (return 1))
						(else (return 0))
					)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== modNum -1) (= modNum curRoomNum))
		(if
		(and msgType (Message msgGET modNum noun theVerb 0 1))
			(messager say: noun theVerb 0 0 0 modNum)
		)
	)
	
	(method (drawInset)
		(if (!= picture -1) (plane drawPic: picture))
		(if (not (== view -1))
			(inView
				view: view
				loop: loop
				cel: cel
				x: 0
				y: 0
				setPri: priority
				ignoreActors: 1
			)
			(plane setBitmap: inView setSize:)
		)
		(plane posn: x y priority: (+ (GetHighPlanePri) 1))
		(UpdatePlane plane)
	)
	
	(method (restore)
		(self drawInset:)
		(if inset (inset restore:))
	)
	
	(method (setInset param1 param2 param3)
		(if inset (inset dispose:))
		(if (and argc param1)
			(param1
				init:
					(if (>= argc 2) param2 else 0)
					self
					(if (>= argc 3) param3 else 0)
			)
		)
	)
	
	(method (onMe theObjOrX theY &tmp theObjOrXX theObjOrXY)
		(if (== argc 1)
			(theObjOrX localize: plane)
			(= theObjOrXX (theObjOrX x?))
			(= theObjOrXY (theObjOrX y?))
		else
			(= theObjOrXX theObjOrX)
			(= theObjOrXY theY)
		)
		(return
			(if (not (== view -1))
				(if (== argc 1)
					(return ((plane bitmap?) onMe: theObjOrX))
				else
					(return ((plane bitmap?) onMe: theObjOrX theY))
				)
			else
				(return 1)
			)
		)
	)
)

(instance inView of View
	(properties)
	
	(method (handleEvent)
		(return 0)
	)
)
