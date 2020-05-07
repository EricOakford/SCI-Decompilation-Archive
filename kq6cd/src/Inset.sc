;;; Sierra Script 1.0 - (do not remove this comment)
(script# 923)
(include sci.sh)
(use Main)
(use Actor)
(use System)


(class Inset of Code
	(properties
		picture 0
		anOverlay 0
		style $0064
		view 0
		loop 0
		cel 0
		x 0
		y 0
		priority 14
		register 0
		hideTheCast 0
		caller 0
		owner 0
		script 0
		oldCast 0
		oldFeatures 0
		oldATPs 0
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
		insetView 0
	)
	
	(method (init theCaller theOwner theRegister)
		(= owner theOwner)
		(owner inset: self)
		(= register theRegister)
		(= caller theCaller)
		(if hideTheCast (self hideCast: 1))
		(= oldCast cast)
		(= oldFeatures features)
		(= oldATPs addToPics)
		(= oldMH mouseDownHandler)
		(= oldKH keyDownHandler)
		(= oldDH directionHandler)
		(= oldWH walkHandler)
		(= oldObstacles (curRoom obstacles?))
		(curRoom obstacles: ((List new:) add: yourself:))
		((= cast (EventHandler new:)) name: {newCast} add:)
		((= features (EventHandler new:))
			name: {newFeatures}
			add: self
		)
		((= addToPics (EventHandler new:)) name: {newATPs} add:)
		((= mouseDownHandler (EventHandler new:))
			name: {newMH}
			add: self
		)
		((= keyDownHandler (EventHandler new:))
			name: {newKH}
			add: self
		)
		((= directionHandler (EventHandler new:))
			name: {newDH}
			add: self
		)
		((= walkHandler (EventHandler new:)) name: {newWH} add:)
		(theDoits add: self)
		(self drawInset:)
	)
	
	(method (doit)
		(if script (script doit:))
		(if (not hideTheCast) (Animate (oldCast elements?) 0))
	)
	
	(method (dispose param1 &tmp theCaller)
		(features delete: self)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(directionHandler delete: self)
		(walkHandler delete: self)
		(theDoits delete: self)
		(if inset (inset dispose: 0))
		(cast
			eachElementDo: #dispose
			eachElementDo: #delete
			release:
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
		(if (or (not argc) param1) (self refresh:))
		(if (or (not argc) param1)
			((= addToPics oldATPs) doit:)
		)
		(curRoom obstacles: oldObstacles)
		(= cast oldCast)
		(= features oldFeatures)
		(= mouseDownHandler oldMH)
		(= keyDownHandler oldKH)
		(= directionHandler oldDH)
		(= walkHandler oldWH)
		(if hideTheCast (self hideCast: 0))
		(if (and (or (not argc) param1) caller)
			(= theCaller caller)
			(= caller 0)
			(theCaller cue:)
		)
	)
	
	(method (setScript newScript)
		(if (IsObject script) (script dispose:))
		(= script (if argc newScript else 0))
		(if script (script init: self &rest))
	)
	
	(method (handleEvent event &tmp [temp0 2])
		(return
			(cond 
				((and inset (inset handleEvent: event)) 0)
				((& (event type?) evVERB)
					(cond 
						((self onMe: event) (event claimed: 1) (self doVerb: (event message?)))
						(disposeNotOnMe (event claimed: 1) (self dispose:))
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
	
	(method (hideCast param1 &tmp temp0 temp1)
		(= temp0 0)
		(= temp1 (if param1 1000 else -1000))
		(while (< temp0 (cast size?))
			((cast at: temp0) z: (+ ((cast at: temp0) z?) temp1))
			(++ temp0)
		)
		(Animate (cast elements?) 0)
	)
	
	(method (drawInset)
		(if (> picture 0)
			(if global169
				(DrawPic picture 15 (if anOverlay 0 else 1))
			else
				(DrawPic
					picture
					(if anOverlay 100 else style)
					(if anOverlay 0 else 1)
				)
			)
		)
		(if view
			(= insetView
				((inView new:)
					view: view
					loop: loop
					cel: cel
					x: x
					y: y
					setPri: priority
					ignoreActors: 1
					init:
					yourself:
				)
			)
		)
	)
	
	(method (restore)
		(self drawInset:)
		(if inset ((inset oldATPs?) doit:) (inset restore:))
	)
	
	(method (refresh)
		(cond 
			(view
				(if global169
					(DrawPic (curRoom picture?) 15)
				else
					(DrawPic (curRoom picture?) dpOPEN_NO_TRANSITION)
				)
			)
			(global169 (DrawPic (curRoom picture?) 15))
			(else (DrawPic (curRoom picture?) style))
		)
		(curRoom style: oldStyle)
		(if (!= overlays -1)
			(if global169
				(DrawPic overlays 15 FALSE)
			else
				(DrawPic overlays dpOPEN_NO_TRANSITION FALSE)
			)
		)
		(if (curRoom inset:) ((curRoom inset:) restore:))
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
	
	(method (onMe event theEventY &tmp eventX eventY)
		(if (IsObject event)
			(= eventX (event x?))
			(= eventY (event y?))
		else
			(= eventX event)
			(= eventY theEventY)
		)
		(return
			(if view
				(return (insetView onMe: event theEventY))
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
