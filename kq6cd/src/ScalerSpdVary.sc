;;; Sierra Script 1.0 - (do not remove this comment)
(script# 211)
(include sci.sh)
(use Print)
(use System)

(public
	ScalerSpdVary 0
)

(class ScalerSpdVary of Code
	(properties
		client 0
		frontY 190
		backY 0
		frontSize 100
		backSize 0
		slopeNum 0
		slopeNumSpd 0
		slopeNumStp 0
		slopeDen 0
		const 0
		constSpd 0
		constStp 0
		frontSpeed 6
		backSpeed 5
		frontStep 3
		backStep 2
		whichSel 1
	)
	
	(method (init theClient theFrontSize theBackSize theFrontY theBackY theWhichSel theFrontSpeed theBackSpeed theFrontStep theBackStep)
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
		(if (> argc 5)
			(= whichSel theWhichSel)
			(if (> argc 6)
				(= frontSpeed theFrontSpeed)
				(= backSpeed theBackSpeed)
				(= slopeNumSpd (- frontSpeed backSpeed))
				(= constSpd
					(- backSpeed (/ (* slopeNumSpd backY) slopeDen))
				)
				(if (> argc 8)
					(= frontStep theFrontStep)
					(= backStep theBackStep)
					(= slopeNumStp (- frontStep backStep))
					(= constStp
						(- backStep (/ (* slopeNumStp backY) slopeDen))
					)
				)
			)
		)
		(= const (- backSize (/ (* slopeNum backY) slopeDen)))
		(return (self doit:))
	)
	
	(method (doit &tmp temp0 theBackSize theBackSpeed theBackStep)
		(cond 
			(
				(<
					(= temp0
						(if (not whichSel) (client y?) else (client x?))
					)
					backY
				)
				(= theBackSize backSize)
				(= theBackSpeed backSpeed)
				(= theBackStep backStep)
			)
			((> temp0 frontY)
				(= theBackSize frontSize)
				(= theBackSpeed frontSpeed)
				(= theBackStep frontStep)
			)
			(else
				(= theBackSize
					(+ (/ (* slopeNum temp0) slopeDen) const)
				)
				(if constSpd
					(= theBackSpeed
						(+ (/ (* slopeNumSpd temp0) slopeDen) constSpd)
					)
					(if constStp
						(= theBackStep
							(+ (/ (* slopeNumStp temp0) slopeDen) constStp)
						)
					)
				)
			)
		)
		(if constSpd
			(client moveSpeed: theBackSpeed)
			(if constStp
				(if
					(not
						(if (== (client xStep?) theBackStep)
							(== (client yStep?) theBackStep)
						)
					)
					(client setStep: theBackStep theBackStep 1)
				)
			)
		)
		(= theBackSize (/ (* theBackSize 128) 100))
		(client scaleX: theBackSize scaleY: theBackSize)
	)
)
