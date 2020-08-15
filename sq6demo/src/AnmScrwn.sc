;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64906)
(include game.sh)
(use AnmCast)
(use Anm)
(use Plane)
(use System)


(local
	local0
)
(class ScrollerWindow of ScrollableWindow
	(properties
		scratch 0
		plane 0
		fore 255
		back 0
		font 0
		mode 0
		borderColor 255
		nsLeft 75
		nsTop 50
		nsRight 275
		nsBottom 150
		maxItems 20
		kWindow 0
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
			selector: 725
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
				((scroller upScroller:)
					x: (+ ((scroller upScroller:) x?) param1)
					yourself:
				)
			)
			(UpdateScreenItem
				((scroller downScroller:)
					x: (+ ((scroller downScroller:) x?) param1)
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
				((scroller upScroller:) x: nsRight yourself:)
			)
			(UpdateScreenItem
				((scroller downScroller:) x: nsRight yourself:)
			)
			(UpdateScreenItem
				((scroller thumb?) x: nsRight yourself:)
			)
		)
		(ScrollWindow 9 kWindow self)
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
	
	(method (handleEvent pEvent)
		(if isVisible (scroller handleEvent: pEvent))
	)
)

(class WindowScrollBar of ScrollBar
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle ftrDefault
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $4021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		type $0000
		thumbView -1
		thumbLoop 0
		thumbCel 0
		thumb 0
		object 0
		selector 0
		minPosn 0
		maxPosn 0
		curPosn 0
		ourCast 0
		pageSize 0
		lowIcon 0
		highIcon 0
		relVer 1
		freeTrack 1
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
