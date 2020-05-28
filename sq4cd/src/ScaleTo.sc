;;; Sierra Script 1.0 - (do not remove this comment)
(script# SCALETO)
(include game.sh)
(use Scaler)


(class ScaleTo of Scaler
	(properties
		caller 0
		endScale 0
		step 6
		adjustValue 0
		repCount 0
	)
	
	(method (init who whereToScale theStep theTime &tmp theValue)
		(if argc
			(= client who)
			(if (>= argc 2)
				(= endScale whereToScale)
				(if (>= argc 3)
					(if (IsObject theStep)
						(= caller theStep)
					else
						(= step theStep)
					)
					(if (>= argc 4)
						(= caller theTime)
					)
				)
			)
		)
		(= adjustValue
			(*
				step
				(= theValue (- (* (> endScale (client maxScale?)) 2) 1))
			)
		)
		(= repCount
			(/ (Abs (- endScale (client maxScale?))) step)
		)
	)
	
	(method (doit &tmp theValue)
		(if repCount
			(if (!= (-- repCount) 0)
				(client maxScale: (+ (client maxScale?) adjustValue))
			else
				(client maxScale: endScale)
			)
		)
		(client
			scaleX: (client maxScale?)
			scaleY: (client maxScale?)
		)
		(if (not repCount)
			(self dispose:)
		)
	)
	
	(method (dispose &tmp whoToCue)
		(= endScale 0)
		(= step 1)
		(client scaler: 0)
		(if caller
			(= whoToCue caller)
			(= caller 0)
			(whoToCue cue:)
		)
		(super dispose:)
	)
)
