;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	SCALETO.SC
;;;;
;;;;	(c) Sierra On-Line, Inc., 1992
;;;;
;;;;	Author:	Robert W. Lindsley & Randy MacNeill
;;;;	Updated:	Robert W. Lindsley -- 8/17/93 Parameter list is no longer variable
;;;;
;;;;	A scaler class that scales an object from its current size
;;;;	to a target size.
;;;;
;;;;	Usage:
;;;;		(object SetScale:	ScaleTo targetSize stepSize ticks caller)
;;;;
;;;;	Classes:
;;;;		ScaleTo


(script# SCALETO)
(include game.sh)
(use Main)
(use Scaler)


(class ScaleTo kindof Scaler
	(properties
		caller			0		;who to cue
		endScale			0		;where to scale to
		step				6		;step size, parameter passed to init
		waitCount		1		;for real time
		scaleDir			NULL	;which way to scale
		saveWaitCount	NULL	;save for wait count
	)
	(method (init who whereToScale theStep theTime whoCares)
		(if argc
			(= client who)
			(if (>= argc 2)
				(= endScale whereToScale)
				(if (>= argc 3)
					(= step theStep)
					(if (>= argc 4)
						(= waitCount theTime)
						(if (>= argc 5)
							(= caller whoCares)
						)
					)
				)
			)
		)
		(= saveWaitCount waitCount)
		(= scaleDir (if (<= (client maxScale?) endScale) 1))	  ;0 - backward / 1 - forward
	)
	(method (doit &tmp theValue)
		(if (> (- gameTime waitCount) 0)
			(= theValue (if scaleDir (+ (client maxScale?) step) else (- (client maxScale?) step)))
			(client
				maxScale:  	theValue,
				scaleX:		theValue,
				scaleY:		theValue
			)
			(= waitCount (+ saveWaitCount gameTime))
			(if scaleDir
				(if (>= (client maxScale?) endScale) (self dispose:))	  ;if it's done kill it
			else
				(if (<= (client maxScale?) endScale) (self dispose:))
			)
		)
	)
	(method (dispose &tmp whoToCue)
		(= endScale 0)
		(= step 6)
		(= waitCount 1)
		(client scaler: 0)
		(if caller
			(= whoToCue caller)
			(= caller 0)
			(whoToCue cue:)
		)
		(super dispose:)
	)
)