;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	SCALER.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1990
;;;;
;;;;	Written by:
;;;;		Brian K. Hughes
;;;;		February 6, 1992
;;;;
;;;;	Classes:
;;;;		Scaler

(script# SCALER)
(include game.sh)
(use Print)
(use System)


(class Scaler kindof Code
	(properties
		client		0		; who we are attached to
		frontY		190	; y coordinate to stop scaling up
		backY			0		; y coordinate to stop scaling down
		frontSize	100	; % size at frontY
		backSize		0		; % size at backY
		slopeNum		0		; numerator of slope - difference in %
		slopeDen		0		; denominator of slope - difference in y
		const			0		; constant adjustement for result %

	)
	(method (init who fs bs fy by)
		(if argc
			(= client who)
			(= frontSize 	fs)
			(= backSize 	bs)
			(= frontY		fy)
			(= backY			by)
		)
		(= slopeNum (- frontSize backSize))
		(if (not (= slopeDen (- frontY backY)))
			(Prints {<Scaler> frontY cannot be equal to backY})
			(return FALSE)
		)
		(= const (- backSize (/ (* slopeNum backY) slopeDen) ))
		(self doit:)
	)
	(method (doit &tmp Y scale)
		(= Y (client y?))
		(cond
			((< Y backY)
				(= scale backSize) 
			)
			((> Y frontY)
				(= scale frontSize) 
			)
			(else
				(= scale (+ (/ (* slopeNum Y) slopeDen) const))			 
			)
		)
		(= scale (/ (* scale scaleBase) 100))
		(client
			scaleX: scale,
			scaleY: scale
		)
	)
)
