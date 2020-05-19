;;; Sierra Script 1.0 - (do not remove this comment)
(script# 82)
(include game.sh)
(use Main)
(use rgClouds)

(public
	rm82 0
)

(instance rm82 of cloudRoom
	(properties
		picture 82
		east 56
	)
	
	(method (init)
		(super init:)
		(ego posn: 317 168 init:)
		(NormalEgo)
	)
)
