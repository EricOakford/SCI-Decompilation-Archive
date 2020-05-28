;;; Sierra Script 1.0 - (do not remove this comment)
(script# DISPOSE) ;801
(include game.sh)
(use Main)
(use LoadMany)
(use System)

(public
	disposeCode 0
)

(instance disposeCode of Code
	(properties)
	
	(method (doit roomNum)
		(LoadMany FALSE
			OSC NAMEFIND REVERSE SORT COUNT DPATH FORCOUNT SIGHT MOVEFWD
			STOPWALK MOVECYC POLYPATH POLYGON SQROOM NOSEPICK HINTBOOK
			ROBOT_MANAGER MOVETOCOORDS ZOID SYNC AUDSCRIPT PRINT TALKICON
			SQGCONTROL SLIDEICON ABOUT GCONTROL
		)
		(if
			(or
				(and
					(OneOf roomNum 25 30 35 40 45 50 55 60 65)
					(!= curRegionPath STREET)
				)
				(and
					(OneOf roomNum
						370 371 375 376 380 381 385
						386 387 390 391 395 396 397
						398 399 400 405 406 410 411
						290
					)
					(!= curRegionPath MALL)
				)
				(and
					(OneOf roomNum 150 500 505 510 514 515 520 525 541 544 545)
					(!= curRegionPath BRAIN)
				)
				(not
					(OneOf roomNum
						25 30 35 40 45 50 55 60 65 370 371 375
						376 380 381 385 386 387 390 391 395 396
						397 398 399 400 405 406 410 411 290 150
						500 505 510 514 515 520 525 541 544 545
					)
				)
			)
			(DisposeScript REGPATH)
		)
		(if (not (OneOf roomNum 405 406 410 411))
			(DisposeScript INERTIA)
		)
		(DisposeScript DEBUG)
		(DisposeScript DISPOSE)
	)
)
