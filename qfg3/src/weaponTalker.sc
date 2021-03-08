;;; Sierra Script 1.0 - (do not remove this comment)
(script# 245)
(include game.sh) (include "240.shm")
(use Main)
(use Vendor)
(use GloryTalker)
(use Actor)

(public
	weaponTalker 0
	weaponVendor 1
)

(local
	theVendor
)
(instance weaponTalker of GloryTalker
	(properties
		x 200
		y 10
		view 326
		loop 1
		talkWidth 260
		back 57
		textX -175
		textY 165
		backColor 50
	)
	
	(method (init)
		(super init: weaponBust weaponEyes weaponMouth &rest)
	)
)

(instance weaponBust of View
	(properties
		nsTop 22
		nsLeft 30
		view 326
		loop 3
	)
)

(instance weaponMouth of Prop
	(properties
		nsTop 47
		nsLeft 38
		view 326
	)
)

(instance weaponEyes of Prop
	(properties
		nsTop 34
		nsLeft 37
		view 326
		loop 2
	)
)

(instance weaponVendor of Vendor
	(properties
		noun N_WEAPON
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
				(ego solvePuzzle: fBoughtFineDagger 2 get: iFineDagger howMany)
				(Bset fGotFineDagger)
				(messager say: N_WEAPON V_DOIT C_DONE_DEAL 0 self)
			)
			(1
				(Buy what howMany theVendor)
				(ego get: iDaggers howMany)
				(messager say: N_WEAPON V_DOIT C_DONE_DEAL 0 self)
			)
			(2
				(Buy what howMany theVendor)
				(ego solvePuzzle: fBoughtFineSpear 2 get: iFineSpear howMany)
				(Bset fGotFineSpear)
				(messager say: N_WEAPON V_DOIT C_DONE_DEAL 0 self)
			)
			;EO: Seems like it was going to be possible to buy a sword from
			; this guy
			(3
				(Buy what howMany theVendor)
				(ego get: iSword howMany)
				(messager say: N_WEAPON V_DOIT C_DONE_DEAL 0 self)
			)
		)
	)
	
	(method (doBargain result)
		(switch result
			(bargainSUCCESS
				(messager say: N_WEAPON V_DOIT C_BARGAIN_SUCCESS 0 self)
			)
			(bargainTRY1
				(messager say: N_WEAPON V_DOIT C_BARGAIN_TRY_1 0 self)
			)
			(bargainTRY2
				(messager say: N_WEAPON V_DOIT C_BARGAIN_TRY_2 0 self)
			)
			(bargainTRY3
				(messager say: N_WEAPON V_DOIT C_BARGAIN_TRY_3 0 self)
			)
			(bargainFAIL
				(messager say: N_WEAPON V_DOIT C_BARGAIN_FAIL 0 self)
			)
			(bargainNODEAL
				(messager say: N_WEAPON V_DOIT C_BARGAIN_NO_DEAL 0 self)
			)
			(else
				(self cue:)
			)
		)
	)
)
