;;; Sierra Script 1.0 - (do not remove this comment)
(script# STARTAROOM)
(include game.sh)
(use Main)
(use LoadMany)
(use System)

(public
	StartARoom 0
)

(instance StartARoom of Code
	
	(method (init &tmp [temp0 2])
		(LoadMany FALSE
			CASTAREA ARRAY_PATH CHASE DPATH OPEN_GIFT
			JUMP LEVITATION OCC_CYCLE CASTOPEN
			PRINTD PAVOID PRINT PROJECTILE REVERSE
			SCALER SIGHT TIME SLIDEICON SMOOPER TARGET
			TELLER VENDOR GCONTROL 401 402
			GLORY_CONTROL 701 702
			INIT_ADDTOS 703 704
			MONkEY_TALKER CASTFETCH JOHARI_TALKER PCHASE
			GLORY_ABOUT RANDCYC GLORY_TALKER EGODEAD CASTJUGGLE 705
			MCHASE STAFF_SCRIPT
		)
		(mouseDownHandler release:)
		(keyDownHandler release:)
		(directionHandler release:)
	)
)
