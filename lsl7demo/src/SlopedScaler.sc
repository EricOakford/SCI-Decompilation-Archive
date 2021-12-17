;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64886)
(include sci.sh)
(use Main)
(use Print)
(use Scaler)


(class SlopedScaler of Scaler
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
		frontX 190
		backX 0
		mn 0
		md 0
		m_sm 0
		sb 0
	)
	
	(method (init theClient theFrontSize theFrontX theFrontY theBackSize theBackX theBackY &tmp theMd temp1)
		(if argc
			(= client theClient)
			(= frontSize theFrontSize)
			(= frontX theFrontX)
			(= frontY theFrontY)
			(= backSize theBackSize)
			(= backX theBackX)
			(= backY theBackY)
		)
		(if
			(or
				(== theFrontX theBackX)
				(== theFrontY theBackY)
				(== theFrontSize theBackSize)
			)
			(Printf
				{SlopedScaler, bad args %d %d %d___%d %d %d}
				theFrontSize
				theFrontX
				theFrontY
				theBackSize
				theBackX
				theBackY
			)
			(return 0)
		)
		(= theMd (- backY frontY))
		(= temp1 (- backX frontX))
		(= sb (- frontY (MulDiv theMd frontX temp1)))
		(= slopeNum (- theFrontSize theBackSize))
		(= slopeDen
			(GetDistance theFrontX theFrontY theBackX theBackY)
		)
		(= mn (- temp1))
		(= md theMd)
		(while (or (> (Abs mn) 128) (> (Abs md) 128))
			(= mn (/ mn 2))
			(= md (/ md 2))
		)
		(= m_sm (/ (+ (* mn mn) (* md md)) (* md mn)))
		(return (self doit:))
	)
	
	(method (doit &tmp temp0 clientScaleX clientScaleY temp3 temp4 temp5 temp6)
		(= clientScaleX (client scaleX?))
		(= clientScaleY (client scaleY?))
		(= temp5
			(/
				(- sb (= temp3 (- (ego y?) (MulDiv (ego x?) mn md))))
				m_sm
			)
		)
		(= temp6 (+ (MulDiv mn temp5 md) temp3))
		(= temp4 (GetDistance frontX frontY temp5 temp6))
		(= temp4 (GetDistance backX backY temp5 temp6))
		(= temp0 (+ backSize (MulDiv temp4 slopeNum slopeDen)))
		(MonoOut
			{Ego @ %d %d, scale = %d}
			(ego x?)
			(ego y?)
			temp0
		)
		(if
		(or (!= clientScaleX temp0) (!= clientScaleY temp0))
			(client scaleX: temp0 scaleY: temp0)
		)
	)
)
