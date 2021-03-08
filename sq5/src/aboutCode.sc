;;; Sierra Script 1.0 - (do not remove this comment)
(script# ABOUT)
(include game.sh) (include "13.shm")
(use Main)
(use System)

(public
	aboutCode 0
)

(instance aboutCode of Code
	
	(method (doit)
		(messager say: N_ABOUT NULL NULL 0 0 ABOUT)
	)
)
