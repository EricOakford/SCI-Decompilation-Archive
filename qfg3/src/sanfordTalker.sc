;;; Sierra Script 1.0 - (do not remove this comment)
(script# 246)
(include game.sh) (include "240.shm")
(use Main)
(use Vendor)
(use GloryTalker)
(use Actor)

(public
	sanfordTalker 0
	sonTalker 1
	sanfordVendor 2
)

(local
	theVendor
)
(instance sanfordTalker of GloryTalker
	(properties
		x 10
		y 15
		view 243
		loop 1
		talkWidth 260
		back 57
		textX 15
		textY 95
		backColor 10
	)
	
	(method (init)
		(super init: sanfordBust sanfordEyes sanfordMouth &rest)
	)
)

(instance sanfordBust of View
	(properties
		view 243
		loop 1
	)
)

(instance sanfordMouth of Prop
	(properties
		nsTop 52
		nsLeft 42
		view 243
	)
)

(instance sanfordEyes of Prop
	(properties
		nsTop 38
		nsLeft 43
		view 243
		loop 2
	)
)

(instance sonTalker of GloryTalker
	(properties
		x 10
		y 15
		view 245
		loop 1
		talkWidth 260
		back 57
		textX 15
		textY 95
		backColor 10
	)
	
	(method (init)
		(super init: sonBust sonEyes sonMouth &rest)
	)
)

(instance sonBust of View
	(properties
		view 245
		loop 1
	)
)

(instance sonMouth of Prop
	(properties
		nsTop 49
		nsLeft 42
		view 245
	)
)

(instance sonEyes of Prop
	(properties
		nsTop 35
		nsLeft 39
		view 245
		loop 2
	)
)

(instance sanfordVendor of Vendor
	(properties
		noun N_JUNK
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
				(ego solvePuzzle: fBoughtTinderbox 3 get: iTinderbox howMany)
				(messager say: N_JUNK V_DOIT C_DONE_DEAL 0 self)
			)
			(1
				(Buy what howMany theVendor)
				(ego solvePuzzle: fBoughtBlackBird 5 puzzleTHIEF get: iBird howMany)
				(Bset fGotBlackBird)
				(messager say: N_SON V_DOIT C_DONE_DEAL 0 self)
			)
		)
	)
	
	(method (doBargain result withSon)
		(if withSon
			(switch result
				(bargainSUCCESS
					(messager say: N_SON V_DOIT C_BARGAIN_SUCCESS 0 self)
				)
				(bargainTRY1
					(messager say: N_SON V_DOIT C_BARGAIN_TRY_1 0 self)
				)
				(bargainTRY2
					(messager say: N_SON V_DOIT C_BARGAIN_TRY_2 0 self)
				)
				(bargainTRY3
					(messager say: N_SON V_DOIT C_BARGAIN_TRY_3 0 self)
				)
				(bargainFAIL
					(messager say: N_SON V_DOIT C_BARGAIN_FAIL 0 self)
				)
				(bargainNODEAL
					(messager say: N_SON V_DOIT C_BARGAIN_NO_DEAL 0 self)
				)
				(else  (self cue:))
			)
		else
			(switch result
				(bargainSUCCESS
					(messager say: N_JUNK V_DOIT C_BARGAIN_SUCCESS 0 self)
				)
				(bargainTRY1
					(messager say: N_JUNK V_DOIT C_BARGAIN_TRY_1 0 self)
				)
				(bargainTRY2
					(messager say: N_JUNK V_DOIT C_BARGAIN_TRY_2 0 self)
				)
				(bargainTRY3
					(messager say: N_JUNK V_DOIT C_BARGAIN_TRY_3 0 self)
				)
				(bargainFAIL
					(messager say: N_JUNK V_DOIT C_BARGAIN_FAIL 0 self)
				)
				(bargainNODEAL
					(messager say: N_JUNK V_DOIT C_BARGAIN_NO_DEAL 0 self)
				)
				(else
					(self cue:)
				)
			)
		)
	)
)
