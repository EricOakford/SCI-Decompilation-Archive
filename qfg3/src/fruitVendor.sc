;;; Sierra Script 1.0 - (do not remove this comment)
(script# 232)
(include game.sh) (include "230.shm")
(use Main)
(use Vendor)
(use GloryTalker)
(use Actor)

(public
	fruitVendor 0
	tFruitTalker 1
)

(local
	theVendor
)
(instance fruitVendor of Vendor
	(properties
		noun N_FRUIT_VENDOR
	)
	
	(method (dispose)
		(= goods 0)
		(super dispose:)
	)
	
	(method (transact what howMany)
		(switch what
			(0
				(= theVendor self)
				(Buy what howMany theVendor)
				(ego get: iFruit howMany)
				(messager say: N_FRUIT_VENDOR V_DOIT C_DONE_DEAL 0 self)
			)
		)
	)
	
	(method (doBargain result)
		(switch result
			(bargainSUCCESS
				(messager say: N_FRUIT_VENDOR V_DOIT C_BARGAIN_SUCCESS 0 self)
			)
			(bargainTRY1
				(messager say: N_FRUIT_VENDOR V_DOIT C_BARGAIN_TRY_1 0 self)
			)
			(bargainTRY2
				(messager say: N_FRUIT_VENDOR V_DOIT C_BARGAIN_TRY_2 0 self)
			)
			(bargainTRY3
				(messager say: N_FRUIT_VENDOR V_DOIT C_BARGAIN_TRY_3 0 self)
			)
			(bargainFAIL
				(messager say: N_FRUIT_VENDOR V_DOIT C_BARGAIN_FAIL 0 self)
			)
			(bargainNODEAL
				(messager say: N_FRUIT_VENDOR V_DOIT C_BARGAIN_NO_DEAL 0 self)
			)
			(else
				(self cue:)
			)
		)
	)
)

(instance tFruitTalker of GloryTalker
	(properties
		x 200
		y 2
		view 235
		loop 1
		talkWidth 260
		back 57
		textX -175
		textY 150
		backColor 28
	)
	
	(method (init)
		(super init: fruitBust fruitEyes fruitMouth &rest)
	)
)

(instance fruitBust of Prop
	(properties
		nsTop 38
		nsLeft 23
		view 235
		loop 3
	)
)

(instance fruitEyes of Prop
	(properties
		nsTop 47
		nsLeft 27
		view 235
		loop 2
	)
)

(instance fruitMouth of Prop
	(properties
		nsTop 62
		nsLeft 23
		view 235
	)
)
