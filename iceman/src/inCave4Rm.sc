;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64)
(include sci.sh)
(use Main)
(use Motion)
(use Game)
(use System)

(public
	inCave4Rm 0
)

(instance inCave4Rm of Rm
	(properties
		picture 64
	)
	
	(method (init)
		(super init:)
		(self setRegions: 304)
		(ego init:)
		(if (== south 65)
			(ego
				setScript: containEgo
				setMotion: MoveTo (ego x?) 170
			)
		)
	)
)

(instance containEgo of Script
	(properties)
	
	(method (doit)
		(cond 
			((< (ego x?) 10) (ego setMotion: MoveTo (+ (ego x?) 5) (ego y?)))
			((> (ego x?) 309) (ego setMotion: MoveTo (- (ego x?) 5) (ego y?)))
		)
	)
)
