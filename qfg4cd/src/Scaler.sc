;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64935)
(include sci.sh)
(use Print)
(use System)


(class Scaler of Code
	(properties
		scratch 0
		client 0
		frontY 190
		backY 0
		frontSize 100
		backSize 0
		slopeNum 0
		slopeDen 0
		const 0
		lastY -9999
	)
	
	(method (init theClient theFrontSize theBackSize theFrontY theBackY)
		(if argc
			(= client theClient)
			(= frontSize theFrontSize)
			(= backSize theBackSize)
			(= frontY theFrontY)
			(= backY theBackY)
		)
		(= slopeNum (- frontSize backSize))
		(if (not (= slopeDen (- frontY backY)))
			(Prints {<Scaler> frontY cannot be equal to backY})
			(return 0)
		)
		(= const (- backSize (/ (* slopeNum backY) slopeDen)))
		(return (self doit:))
	)
	
	(method (doit &tmp clientY theBackSize clientScaleX clientScaleY)
		(if (!= (= clientY (client y?)) lastY)
			(= lastY clientY)
			(= clientScaleX (client scaleX?))
			(= clientScaleY (client scaleY?))
			(cond 
				((< clientY backY) (= theBackSize backSize))
				((> clientY frontY) (= theBackSize frontSize))
				(else
					(= theBackSize
						(+ (/ (* slopeNum clientY) slopeDen) const)
					)
				)
			)
			(if
				(or
					(!=
						clientScaleX
						(= theBackSize (/ (* theBackSize 128) 100))
					)
					(!= clientScaleY theBackSize)
				)
				(client scaleX: theBackSize scaleY: theBackSize)
			)
		)
	)
)
