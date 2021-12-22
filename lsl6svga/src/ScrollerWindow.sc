;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64906)
(include game.sh)
(use Main)
(use ScrollBar)
(use ScrollableWindow)
(use Plane)
(use System)


(local
	local0
)
(class ScrollerWindow of ScrollableWindow
	(properties
		scroller 0
		scrollCast 0
		range 0
		isVisible 0
	)
	
	(method (init theScroller param2 param3 &tmp temp0)
		((= scrollCast (Cast new:)) init:)
		((= plane (Plane new:))
			priority: -1
			init:
			addCast: scrollCast
		)
		(= temp0 (+ (- nsRight nsLeft) 1))
		((= scroller theScroller)
			x: (+ (theScroller x?) temp0)
			object: self
			selector: 783
		)
		(if (> argc 1)
			(param2 x: (+ (param2 x?) temp0))
			(param3 x: (+ (param3 x?) temp0))
			(scroller init: scrollCast param2 param3)
		else
			(scroller init: scrollCast)
		)
		(plane
			setRect:
				nsLeft
				nsTop
				(+
					nsRight
					(CelWide
						(scroller view?)
						(scroller loop?)
						(scroller cel?)
					)
				)
				nsBottom
		)
		(UpdatePlane plane)
		(= range (- (scroller maxPosn?) (scroller minPosn?)))
		(= nsRight (- nsRight nsLeft))
		(= nsBottom (- nsBottom (- nsTop 1)))
		(= nsTop (= nsLeft 0))
		(super init: &rest)
	)
	
	(method (dispose)
		(if scroller (scroller dispose:))
		(if plane (plane dispose:))
		(super dispose: &rest)
	)
	
	(method (show)
		(UpdatePlane
			(plane priority: (+ (GetHighPlanePri) 1) yourself:)
		)
		(= isVisible 1)
		(super show: &rest)
	)
	
	(method (hide)
		(UpdatePlane (plane priority: -1 yourself:))
		(= isVisible 0)
		(super hide: &rest)
	)
	
	(method (addString &tmp temp0)
		(= temp0 (super addString: &rest))
		(self updateThumb:)
		(return temp0)
	)
	
	(method (resize param1 param2 param3)
		(if (and (> argc 2) param3)
			(= nsRight (+ nsRight param1))
			(= nsBottom (+ nsBottom param2))
			(UpdateScreenItem
				((scroller upScroller?)
					x: (+ ((scroller upScroller?) x?) param1)
					yourself:
				)
			)
			(UpdateScreenItem
				((scroller downScroller?)
					x: (+ ((scroller downScroller?) x?) param1)
					yourself:
				)
			)
			(UpdateScreenItem
				((scroller thumb?)
					x: (+ ((scroller thumb?) x?) param1)
					yourself:
				)
			)
		else
			(= nsRight (+ nsLeft param1 -1))
			(= nsBottom (+ nsTop param2 -1))
			(UpdateScreenItem
				((scroller upScroller?) x: nsRight yourself:)
			)
			(UpdateScreenItem
				((scroller downScroller?) x: nsRight yourself:)
			)
			(UpdateScreenItem
				((scroller thumb?) x: nsRight yourself:)
			)
		)
		(ScrollWindow ScrollResize kWindow self)
	)
	
	(method (scrollerMove)
		(self
			scrollTo: 3 (- (scroller curPosn?) (scroller minPosn?)) range
		)
	)
	
	(method (updateThumb &tmp temp0 temp1)
		(= temp0 (self where: range))
		(= temp1 (+ (scroller minPosn?) temp0))
		(scroller rePosn: temp1 1)
	)
	
	(method (handleEvent event)
		(if isVisible (scroller handleEvent: event))
	)
)

(class WindowScrollBar of ScrollBar
	(properties
		freeTrack 1
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(event localize: ((object scrollCast?) plane?))
		(cond 
			((self onMe: event)
				(if (or (not local0) (!= theCursor normalCursor))
					(theGame setCursor: normalCursor)
					(= local0 1)
				)
			)
			(local0
				(theGame setCursor: (theIconBar getCursor:))
				(= local0 0)
			)
		)
	)
	
	(method (pageUp)
		(object scrollTo: 1 updateThumb:)
	)
	
	(method (pageDown)
		(object scrollTo: 5 updateThumb:)
	)
	
	(method (arrowUp)
		(object scrollTo: 2 updateThumb:)
	)
	
	(method (arrowDown)
		(object scrollTo: 4 updateThumb:)
	)
)
