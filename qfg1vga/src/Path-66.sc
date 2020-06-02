;;; Sierra Script 1.0 - (do not remove this comment)
(script# 66)
(include game.sh)
(use Main)
(use Procs)
(use Game)

(public
	rm66 0
)

(instance rm66 of Room
	(properties
		picture 66
		style DISSOLVE
		horizon 54
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(StatusLine enable:)
		(NormalEgo)
	)
	
	(method (dispose)
		(Bset fBeenIn66)
		(super dispose:)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)
