;;; Sierra Script 1.0 - (do not remove this comment)
(script# 24)
(include game.sh)
(use Main)
(use Inset)
(use Feature)
(use Actor)
(use System)

(public
	insetCode 0
	invInset 1
)

(instance insetCode of Code
	
	(method (doit param1 &tmp temp0)
		(= temp0 0)
		(invInset
			view: 24
			loop: 0
			cel: 0
			x: (+ (Abs (thePlane left:)) 100)
			y: (+ (Abs (thePlane top?)) 30)
			disposeNotOnMe: 0
		)
		(invInset init: temp0 curRoom 0 1 param1)
	)
)

(instance invInset of Inset

	(method (init whoCares theRoomOrInset reg delayDraw obj)
		(super init: whoCares theRoomOrInset 0 delayDraw)
		(closeBox init:)
		(itemView
			view: (obj view?)
			loop: (obj loop?)
			cel: (obj cel?)
			x: 49
			y: 36
			init:
		)
		(self drawInset:)
	)
)

(instance closeBox of Feature
	(properties
		nsRight 7
		nsBottom 7
	)
	
	(method (init)
		(super init:)
		(self setHotspot: 0 (user message?))
	)
	
	(method (doVerb)
		(invInset dispose:)
	)
)

(instance itemView of View)
