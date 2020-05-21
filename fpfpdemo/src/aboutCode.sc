;;; Sierra Script 1.0 - (do not remove this comment)
(script# FPABOUT) ;13
(include game.sh) (include "13.shm")
(use Main)
(use System)

(public
	aboutCode 0
)

(instance aboutCode of Code
	(properties)
	
	(method (doit &tmp [str 300])
		(= normalCursor ((theIconBar curIcon?) cursor?))
		(theGame setCursor: ARROW_CURSOR)
		(messager say: N_ABOUT NULL NULL NULL NULL FPABOUT)
		(self dispose:)
	)
	
	(method (dispose)
		(theGame setCursor: normalCursor)
		(DisposeScript DCICON)
		(DisposeScript FPABOUT)
	)
)
