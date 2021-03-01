;;; Sierra Script 1.0 - (do not remove this comment)
(script# 248)
(include game.sh) (include "240.shm")
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
		noun N_OIL_VENDOR
	)
	
	(method (dispose)
		(= goods 0)
		(super dispose:)
	)
	
	(method (transact what howMany)
		(= theVendor self)
		(switch what
			(0
				(Buy what howMany theVendor)
				(ego solvePuzzle: fBoughtOil 5 puzzleTHIEF get: iOil howMany)
				(messager say: N_OIL_VENDOR V_DOIT C_DONE_DEAL 0 self)
			)
		)
	)
	
	(method (doBargain result)
		(switch result
			(bargainSUCCESS
				(messager say: N_OIL_VENDOR V_DOIT C_BARGAIN_SUCCESS 0 self)
			)
			(bargainTRY1
				(messager say: N_OIL_VENDOR V_DOIT C_BARGAIN_TRY_1 0 self)
			)
			(bargainTRY2
				(messager say: N_OIL_VENDOR V_DOIT C_BARGAIN_TRY_2 0 self)
			)
			(bargainTRY3
				(messager say: N_OIL_VENDOR V_DOIT C_BARGAIN_TRY_3 0 self)
			)
			(bargainFAIL
				(messager say: N_OIL_VENDOR V_DOIT C_BARGAIN_FAIL 0 self)
			)
			(bargainNODEAL
				(messager say: N_OIL_VENDOR V_DOIT C_BARGAIN_NO_DEAL 0 self)
			)
			(else
				(self cue:)
			)
		)
	)
)
