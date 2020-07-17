;;; Sierra Script 1.0 - (do not remove this comment)
(script# ABOUT)
(include game.sh)
(use Main)
(use Intrface)
(use System)

(public
	aboutCode 0
)

(instance aboutCode of Code
	
	(method (doit)
		(Printf 811 0 version)
		(Print 811 1)
		(Print 811 2)
		(Print 811 3)
	)
)
