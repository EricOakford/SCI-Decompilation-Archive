;;; Sierra Script 1.0 - (do not remove this comment)
(script# 615)
(include game.sh)
(use Avoider)


(class mwAvoider of Avoider
	(properties)
	
	(method (doit)
		(super doit:)
		(DirLoop client (client heading?))
	)
)
