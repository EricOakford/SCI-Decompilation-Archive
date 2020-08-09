;;; Sierra Script 1.0 - (do not remove this comment)
(script# SWING)
(include game.sh)
(use Main)
(use Osc)
(use System)


(class Swing of Oscillate
	(properties
		swingSpeed 1
		countSwings 0
		startFull 0
		startSwingCel 0
		stopSwingCel 0
		cycleSound 0
	)
	
	(method (init who theSwingSpeed theStartFull soundNum &tmp newCel)
		(= cycleSound soundNum)
		(= newCel (who lastCel:))
		(if (>= argc 4)
			(= swingSpeed theSwingSpeed)
			(if (>= argc 5)
				(= startFull theStartFull)
			)
		)
		(if startFull
			(= startSwingCel 0)
			(= stopSwingCel newCel)
		else
			(= startSwingCel (- (/ newCel 2) 1))
			(= stopSwingCel (/ newCel 2))
		)
		(who cel: startSwingCel)
		(= countSwings swingSpeed)
		(super init: who &rest)
	)
	
	(method (doit &tmp newCel)
		(= newCel (self nextCel:))
		(if (!= (client cel?) newCel)
			(cond 
				(
					(and
						startFull
						(or
							(== newCel stopSwingCel)
							(== newCel startSwingCel)
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
					(if (!= cycleSound 0)
						(swingSound number: cycleSound play:)
					)
					(client
						cel:
							(if
								(or
									(== newCel (- startSwingCel 1))
									(== newCel startSwingCel)
								)
								startSwingCel
							else
								stopSwingCel
							)
					)
				)
				(
					(or
						(> newCel stopSwingCel)
						(< newCel startSwingCel)
					)
					(if
						(and
							(OneOf startSwingCel stopSwingCel (- stopSwingCel 1))
							(> howManyCycles 0)
						)
						(-- howManyCycles)
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
				(else (client cel: newCel))
			)
		)
	)
	
	(method (dispose)
		(swingSound dispose:)
		(super dispose:)
	)
)

(instance swingSound of FPSound
	(properties
		flags mNOPAUSE
	)
)
