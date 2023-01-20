;;; Sierra Script 1.0 - (do not remove this comment)
(script# DISPOSE) ;801
(include game.sh)
(use LoadMany)
(use System)

(public
	disposeCode 0
)

(instance disposeCode of Code
	(method (doit roomNum)
		(LoadMany FALSE
			OSC NAMEFIND REVERSE SORT COUNT DPATH FORCOUNT MOVEFWD MOVECYC FOLLOW
			PRINTD MOVETOCOORDS DSCRIPT PAVOID PCHASE MADONNA ZZTOP BLUESBROS RANGEOSC
		)
		(if
			(and
				(not
					(OneOf roomNum 54 55 57 58 59 60 61 62 63 64 65 66 67)
				)
				(not (OneOf roomNum 3 4 5 6 7 8 9 10 11 12 13 103))
			)
			(DisposeScript ELEVATOR)
		)
		(if
			(not
				(OneOf roomNum 54 55 57 58 59 60 61 62 63 64 65 66 67)
			)
			(DisposeScript TALKER)
		)
		(DisposeScript DEBUG)
		(DisposeScript DISPOSE)
	)
)
