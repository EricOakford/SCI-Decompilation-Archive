;;; Sierra Script 1.0 - (do not remove this comment)
(script# 235)
(include game.sh) (include "230.shm")
(use Main)
(use Vendor)
(use GloryTalker)
(use Actor)

(public
	leatherVendor 0
	tLeatherTalker 1
)

(local
	theVendor
)
(instance leatherVendor of Vendor
	(properties
		noun N_LEATHER_VENDOR
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
				(ego get: iWaterskin howMany solvePuzzle: fBoughtWaterskin 3)
				(messager say: N_LEATHER_VENDOR V_DOIT C_DONE_DEAL 0 self)
			)
			(1
				(Buy what howMany theVendor)
				(ego get: iSkins howMany solvePuzzle: fBoughtZebraSkin 2)
				(messager say: N_LEATHER_VENDOR V_DOIT C_DONE_DEAL 0 self)
			)
		)
	)
	
	(method (doBargain result)
		(switch result
			(bargainSUCCESS
				(messager say: N_LEATHER_VENDOR V_DOIT C_BARGAIN_SUCCESS 0 self)
			)
			(bargainTRY1
				(messager say: N_LEATHER_VENDOR V_DOIT C_BARGAIN_TRY_1 0 self)
			)
			(bargainTRY2
				(messager say: N_LEATHER_VENDOR V_DOIT C_BARGAIN_TRY_2 0 self)
			)
			(bargainTRY3
				(messager say: N_LEATHER_VENDOR V_DOIT C_BARGAIN_TRY_3 0 self)
			)
			(bargainFAIL
				(messager say: N_LEATHER_VENDOR V_DOIT C_BARGAIN_FAIL 0 self)
			)
			(bargainNODEAL
				(messager say: N_LEATHER_VENDOR V_DOIT C_BARGAIN_NO_DEAL 0 self)
			)
			(else  (self cue:))
		)
	)
)

(instance tLeatherTalker of GloryTalker
	(properties
		x 200
		y 2
		view 237
		loop 1
		cel 1
		talkWidth 260
		back 57
		textX -175
		textY 150
		backColor 24
	)
	
	(method (init)
		(super init: leatherBust leatherEyes leatherMouth &rest)
	)
)

(instance leatherBust of Prop
	(properties
		nsTop 27
		nsLeft 30
		view 237
		loop 3
		cel 1
	)
)

(instance leatherEyes of Prop
	(properties
		nsTop 43
		nsLeft 34
		view 237
		loop 2
	)
)

(instance leatherMouth of Prop
	(properties
		nsTop 55
		nsLeft 32
		view 237
	)
)
