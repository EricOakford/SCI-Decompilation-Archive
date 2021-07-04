;;; Sierra Script 1.0 - (do not remove this comment)
(script# 19)
(include game.sh)
(use Main)
(use PolyPath)
(use Feature)

(public
	eastFeat 0
	westFeat 1
	northFeat 2
	southFeat 3
)

(class ExitFeature of Feature
	(properties
		plane 0
		x 0
		y 0
		z 0
		exitDir 0
	)
	
	(method (init)
		(super init: &rest)
		(walkHandler addToFront: self)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((and (& (event type?) walkEvent) (self onMe: event))
					(switch exitDir
						(NORTH
							(ego setMotion: PolyPath (event x?) 0)
						)
						(SOUTH
							(ego setMotion: PolyPath (event x?) 150)
						)
						(EAST
							(ego setMotion: PolyPath (curRoom eastSide?) (event y?))
						)
						(WEST
							(ego setMotion: PolyPath -10 (event y?))
						)
					)
					(return (event claimed: TRUE))
				)
				((and scratch (not (event type?)) (self onMe: event))
					(= theExitFeature self)
					((self scratch?) doit:)
					(return (event claimed: TRUE))
				)
			)
		)
	)
)

(instance eastFeat of ExitFeature
	(properties
		nsLeft 300
		nsRight 319
		nsBottom 140
		exitDir EAST
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 10 10)
	)
)

(instance westFeat of ExitFeature
	(properties
		nsRight 20
		nsBottom 140
		exitDir WEST
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 10 10)
	)
)

(instance northFeat of ExitFeature
	(properties
		nsLeft 21
		nsRight 289
		nsBottom 20
		exitDir NORTH
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 10 10)
	)
)

(instance southFeat of ExitFeature
	(properties
		nsLeft 21
		nsTop 130
		nsRight 289
		nsBottom 135
		exitDir SOUTH
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 10 10)
	)
)
