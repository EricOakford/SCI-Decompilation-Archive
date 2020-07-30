;;; Sierra Script 1.0 - (do not remove this comment)
(script# ABOUT) ;811
(include game.sh)
(use Main)
(use Intrface)
(use System)

(public
	aboutCode 0
)

(instance aboutCode of Code
	(properties)
	
	(method (doit)
		(Printf 811 0 version)
		(Print 811 1)
		(Print 811 2)
	)
)
