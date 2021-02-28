;;; Sierra Script 1.0 - (do not remove this comment)
(script# 248)
(include sci.sh)
(use Main)
(use Vendor)
(use GloryTalker)
(use Actor)

(public
	oilTalker 0
	oilVendor 1
)

(local
	theVendor
)
(instance oilTalker of GloryTalker
	(properties
		x 5
		y 25
		view 259
		loop 1
		talkWidth 260
		back 57
		textX 15
		textY 95
		backColor 29
	)
	
	(method (init)
		(super init: oilBust oilEyes oilMouth &rest)
	)
)

(instance oilBust of View
	(properties
		nsTop 27
		nsLeft 39
		view 259
		loop 3
	)
)

(instance oilMouth of Prop
	(properties
		nsTop 50
		nsLeft 37
		view 259
	)
)

(instance oilEyes of Prop
	(properties
		nsTop 39
		nsLeft 38
		view 259
		loop 2
	)
)

(instance oilVendor of Vendor
	(properties
		noun 5
	)
	
	(method (dispose)
		(= goods 0)
		(super dispose:)
	)
	
	(method (transact param1 param2)
		(= theVendor self)
		(switch param1
			(0
				(Buy param1 param2 theVendor)
				(ego solvePuzzle: 223 5 4 get: 25 param2)
				(messager say: 5 6 10 0 self)
			)
		)
	)
	
	(method (doBargain param1)
		(switch param1
			(1
				(messager say: 5 6 11 0 self)
			)
			(2
				(messager say: 5 6 14 0 self)
			)
			(3
				(messager say: 5 6 15 0 self)
			)
			(4
				(messager say: 5 6 16 0 self)
			)
			(5
				(messager say: 5 6 12 0 self)
			)
			(6
				(messager say: 5 6 13 0 self)
			)
			(else  (self cue:))
		)
	)
)
