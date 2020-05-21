;;; Sierra Script 1.0 - (do not remove this comment)
(script# SWING) ;12
(include game.sh)
(use Osc)
(use System)


(class Swing of Oscillate
	(properties
		swingSpeed 1
		countSwings 0
		startFull 0
		startSwingCel 0
		stopSwingCel 0
	)
	
	(method (init param1 theSwingSpeed theStartFull &tmp theStopSwingCel)
		(= theStopSwingCel (param1 lastCel:))
		(if (>= argc 4)
			(= swingSpeed theSwingSpeed)
			(if (>= argc 5) (= startFull theStartFull))
		)
		(if startFull
			(= startSwingCel 0)
			(= stopSwingCel theStopSwingCel)
		else
			(= startSwingCel (- (/ theStopSwingCel 2) 1))
			(= stopSwingCel (/ theStopSwingCel 2))
		)
		(param1 cel: startSwingCel)
		(= countSwings swingSpeed)
		(super init: param1 &rest)
	)
	
	(method (doit &tmp swingNextCel)
		(= swingNextCel (self nextCel:))
		(if (!= (client cel?) swingNextCel)
			(cond 
				(
					(and
						startFull
						(or
							(== swingNextCel stopSwingCel)
							(== swingNextCel startSwingCel)
						)
					)
					(if (== countSwings 0)
						(= countSwings swingSpeed)
						(if (!= startSwingCel (/ (client lastCel:) 2))
							(++ startSwingCel)
						)
						(if (!= stopSwingCel (/ (client lastCel:) 2))
							(-- stopSwingCel)
						)
					else
						(-- countSwings)
					)
					(= cycleDir (- cycleDir))
					(client
						cel:
							(if
								(or
									(== swingNextCel (- startSwingCel 1))
									(== swingNextCel startSwingCel)
								)
								startSwingCel
							else
								stopSwingCel
							)
					)
				)
				(
					(or
						(> swingNextCel stopSwingCel)
						(< swingNextCel startSwingCel)
					)
					(if
					(OneOf startSwingCel stopSwingCel (- stopSwingCel 1))
						(= howManyCycles 0)
					)
					(if (== countSwings 0)
						(= countSwings swingSpeed)
						(if (> startSwingCel 0) (-- startSwingCel))
						(if (< stopSwingCel (client lastCel:))
							(++ stopSwingCel)
						)
					else
						(-- countSwings)
					)
					(= cycleDir (- cycleDir))
					(self cycleDone:)
				)
				(else (client cel: swingNextCel))
			)
		)
	)
)
