;;; Sierra Script 1.0 - (do not remove this comment)
(script# LOGGER)
(include game.sh)
(use System)

(public
	sysLogger 0
)

(instance sysLogger of Code
	(method (doit)
		(super doit: &rest)
		(DisposeScript LOGGER)
	)
)
