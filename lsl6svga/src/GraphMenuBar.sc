;;; Sierra Script 1.0 - (do not remove this comment)
(script# 913)
(include sci.sh)
(use Main)
(use Actor)
(use System)

(public
	GraphMenuBar 0
)

(local
	local0
)
(class GraphMenuBar of Set
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
		view -1
		loop 0
		cel 0
		x 0
		y 0
		curTitle 0
		state $0000
		nextTitle 0
		plane 0
	)
	
	(method (init)
		(= gGraphMenuBar self)
		(if (not plane) (= plane (systemPlane new:)))
		(plane
			picture: -2
			back: 0
			priority: -1
			init:
			addCast: menuBarCast
		)
		(= state 4)
		(super init: &rest)
		(self eachElementDo: #init)
	)
	
	(method (doit param1 &tmp temp0)
		(= temp0 param1)
		(while (!= (temp0 type?) 2)
			(self handleEvent: temp0)
			(= temp0 ((user curEvent?) new:))
		)
	)
	
	(method (dispose)
		(if (not (& state $0004)) (self hide:))
		(super dispose: &rest)
		(if plane (plane dispose:) (= plane 0))
	)
	
	(method (show)
		(= state (& state $fffb))
		(self eachElementDo: #show)
		(plane priority: (+ (GetHighPlanePri) 1) setSize:)
		(UpdatePlane
			(plane
				setRect:
					x
					y
					(+ x (- (plane right:) (plane left:)))
					(+ y (- (plane bottom?) (plane top?)))
				yourself:
			)
		)
	)
	
	(method (hide)
		(= state 4)
		(self eachElementDo: #hide)
		(UpdatePlane
			(plane priority: -1 setRect: 0 0 1 1 yourself:)
		)
	)
	
	(method (handleEvent event &tmp temp0 curTitleCurItem temp2)
		(if (& state $0004) (return))
		(cond 
			((& state $0020)
				(if
					(and
						(= nextTitle (self firstTrue: #handleEvent event))
						(!= nextTitle curTitle)
					)
					(nextTitle select:)
					(FrameOut)
				)
			)
			(
				(and
					(& (event type?) evMOUSEBUTTON)
					(not (event modifiers?))
					(= temp0 (self firstTrue: #handleEvent event))
				)
				(= state (| state $0020))
				(temp0 select:)
				(self doit: event)
				(= curTitleCurItem (curTitle curItem?))
				(if curTitle (curTitle hide: 0))
				(= state (& state $ffdf))
				(event claimed: 1)
				(if curTitleCurItem (curTitleCurItem cel: 0 select:))
			)
			(else
				(event localize: (menuBarCast plane?))
				(cond 
					((= temp2 (self firstTrue: #onMe event))
						(if (or (not local0) (!= theCursor normalCursor))
							(theGame setCursor: normalCursor)
							(= local0 1)
						)
					)
					(local0
						(if (== theCursor normalCursor)
							(theGame setCursor: (theIconBar getCursor:))
						)
						(= local0 0)
					)
				)
			)
		)
	)
)

(instance menuBarCast of Cast
	(properties)
)

(instance menuTitleCast of Cast
	(properties)
)

(class MenuTitle of Set
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
		mainView -1
		mainLoop 0
		mainCel 0
		x 0
		y 0
		message 0
		curItem 0
		winX 0
		winY 0
		winView 0
		winLoop 0
		winCel 0
		titleView 0
		windowView 0
	)
	
	(method (init)
		(super init:)
		(self eachElementDo: #client self)
	)
	
	(method (dispose)
		(if windowView (self hide: 0) else (self release:))
		(super dispose: &rest)
	)
	
	(method (show param1 &tmp newSystemPlane)
		(if (or (not argc) param1)
			(if (not titleView)
				((= titleView (View new:))
					view: mainView
					loop: mainLoop
					cel: mainCel
					posn: x y
					init: menuBarCast
				)
			)
		else
			(self curItem: 0)
			(if (not (menuTitleCast plane?))
				((= newSystemPlane (systemPlane new:))
					init:
					addCast: menuTitleCast
				)
			else
				(= newSystemPlane (menuTitleCast plane?))
			)
			(self eachElementDo: #init menuTitleCast)
			((= windowView (View new:))
				view: winView
				loop: winLoop
				cel: winCel
				init: menuTitleCast
			)
			(newSystemPlane
				priority: (+ (GetHighPlanePri) 1)
				setSize:
			)
			(UpdatePlane
				(newSystemPlane
					setRect:
						winX
						winY
						(+
							winX
							(- (newSystemPlane right:) (newSystemPlane left:))
						)
						(+
							winY
							(- (newSystemPlane bottom?) (newSystemPlane top?))
						)
					yourself:
				)
			)
		)
	)
	
	(method (handleEvent event)
		(event localize: (menuBarCast plane?))
		(cond 
			((self onMe: event) (return self))
			((== (gGraphMenuBar curTitle?) self)
				(event localize: (menuTitleCast plane?))
				(self eachElementDo: #handleEvent event)
			)
		)
		(return 0)
	)
	
	(method (hide param1)
		(if (or (not argc) param1)
			(if titleView (titleView dispose:) (= titleView 0))
		else
			(gGraphMenuBar curTitle: 0)
			(UpdateScreenItem (titleView cel: 0 yourself:))
			(self eachElementDo: #dispose)
			(windowView dispose:)
			(= windowView 0)
			(UpdatePlane
				((menuTitleCast plane?)
					priority: -1
					setRect: 0 0 1 1
					yourself:
				)
			)
		)
	)
	
	(method (onMe theObjOrX)
		(if titleView (titleView onMe: theObjOrX))
	)
	
	(method (select)
		(if (gGraphMenuBar curTitle?)
			(UpdateScreenItem
				(((gGraphMenuBar curTitle?) titleView?)
					cel: 0
					yourself:
				)
			)
			((gGraphMenuBar curTitle?) hide: 0)
		)
		(gGraphMenuBar curTitle: self)
		(UpdateScreenItem (titleView cel: 1))
		(self show: 0)
		(FrameOut)
	)
)

(class MenuItem of View
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
		sightAngle 26505
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
		client 0
	)
	
	(method (handleEvent event)
		(cond 
			((self onMe: event)
				(if (!= (client curItem?) self)
					(client curItem: self)
					(= cel 1)
					(UpdateScreenItem self)
					(FrameOut)
				)
			)
			((== cel 1)
				(if (== (client curItem?) self) (client curItem: 0))
				(= cel 0)
				(UpdateScreenItem self)
				(FrameOut)
			)
		)
	)
	
	(method (select &tmp temp0)
		(return 0)
	)
)
