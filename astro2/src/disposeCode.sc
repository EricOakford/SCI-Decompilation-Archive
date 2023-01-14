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
	(method (doit roomNum)
		(LoadMany FALSE
			OSC NAMEFIND REVERSE SORT COUNT DPATH FORCOUNT SIGHT MOVEFWD
			RANDCYC STOPWALK MOVECYC POLYPATH POLYGON SQROOM NOSEPICK HINTBOOK
			RMNSCRIPT PRINTD MOVETOCOORDS
		)
		(if
			(or
				(and
					(OneOf roomNum 25 30 35 40 45 50 55 60 65)
					(!= curReg STREET)
				)
				(and
					(OneOf roomNum
						370 371 375 376 380 381 385
						386 387 390 391 395 397
						398 399 400 405 406 410 411
					)
					(!= curReg MALL)
				)
				(and
					(OneOf roomNum 150 500 505 510 514 515 520 525 541 544 545)
					(!= curReg BRAIN)
				)
				(not
					(OneOf roomNum
						25 30 35 40 45 50 55 60 65 370 371 375
						376 380 381 385 386 387 390 391 395
						397 398 399 400 405 406 410 411 150
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
