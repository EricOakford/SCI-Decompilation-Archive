;;; Sierra Script 1.0 - (do not remove this comment)
(script# 63)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use System)

(public
	inCave3Rm 0
)

(instance inCave3Rm of Room
	(properties
		picture 63
	)
	
	(method (init)
		(super init:)
		(self setRegions: 304)
		(ego init:)
		(if (== south 67)
			(ego setScript: containEgo)
		)
	)
)

(instance containEgo of Script

	(method (doit)
		(cond 
			((< (ego x?) 10)
				(ego setMotion: MoveTo (+ (ego x?) 5) (ego y?))
			)
			((< (ego y?) 10)
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 5))
			)
			((< 185 (ego y?))
				(ego setMotion: MoveTo (ego x?) (- (ego y?) 5))
			)
		)
	)
)
