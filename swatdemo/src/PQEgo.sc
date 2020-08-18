;;; Sierra Script 1.0 - (do not remove this comment)
(script# PQEGO)
(include game.sh)
(use Ego)


(class PQEgo of Ego
	(properties
		medals 0
	)
	
	(method (awardMedal theMedal)
		(|= medals theMedal)
	)
)
