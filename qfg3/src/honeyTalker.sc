;;; Sierra Script 1.0 - (do not remove this comment)
(script# 247)
(include game.sh) (include "240.shm")
(use Main)
(use Vendor)
(use GloryTalker)
(use Actor)

(public
	honeyTalker 0
	honeyVendor 1
)

(local
	theVendor
)
(instance honeyTalker of GloryTalker
	(properties
		x 10
		y 15
		view 255
		loop 1
		talkWidth 260
		back 57
		textX 15
		textY 95
		backColor 12
	)
	
	(method (init)
		(super init: honeyBust honeyEyes honeyMouth &rest)
	)
)

(instance honeyBust of View
	(properties
		nsTop 21
		nsLeft 28
		view 255
		loop 3
	)
)

(instance honeyMouth of Prop
	(properties
		nsTop 52
		nsLeft 31
		view 255
	)
)

(instance honeyEyes of Prop
	(properties
		nsTop 40
		nsLeft 35
		view 255
		loop 2
	)
)

(instance honeyVendor of Vendor
	(properties
		noun N_HONEY_VENDOR
	)
	
	(method (dispose)
		(= goods 0)
		(super dispose: &rest)
	)
	
	(method (transact what howMany)
		(= theVendor self)
		(switch what
			(0
				(Buy what howMany theVendor)
				(ego solvePuzzle: fBoughtHoney 2 get: iHoney howMany)
				(messager say: N_HONEY_VENDOR V_DOIT C_DONE_DEAL 0 self)
			)
		)
	)
	
	(method (doBargain result)
		(switch result
			(bargainSUCCESS
				(messager say: N_HONEY_VENDOR V_DOIT C_BARGAIN_SUCCESS 0 self)
			)
			(bargainTRY1
				(messager say: N_HONEY_VENDOR V_DOIT C_BARGAIN_TRY_1 0 self)
			)
			(bargainTRY2
				(messager say: N_HONEY_VENDOR V_DOIT C_BARGAIN_TRY_2 0 self)
			)
			(bargainTRY3
				(messager say: N_HONEY_VENDOR V_DOIT C_BARGAIN_TRY_3 0 self)
			)
			(bargainFAIL
				(messager say: N_HONEY_VENDOR V_DOIT C_BARGAIN_FAIL 0 self)
			)
			(bargainNODEAL
				(messager say: N_HONEY_VENDOR V_DOIT C_BARGAIN_NO_DEAL 0 self)
			)
			(else
				(self cue:)
			)
		)
	)
)
