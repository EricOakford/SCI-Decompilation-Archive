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
		id 190
		location 0
		row 0
		canReachList 0
		oShard 0
		bLit 0
	)
	
	(method (init theClient theFrontSize theId theFrontY theBackSize theLocation theBackY &tmp theCanReachList temp1)
		(if argc
			(= client theClient)
			(= frontSize theFrontSize)
			(= id theId)
			(= frontY theFrontY)
			(= backSize theBackSize)
			(= location theLocation)
			(= backY theBackY)
		)
		(if
			(or
				(== theId theLocation)
				(== theFrontY theBackY)
				(== theFrontSize theBackSize)
			)
			(Printf
				LOOKUP_ERROR
				theFrontSize
				theId
				theFrontY
				theBackSize
				theLocation
				theBackY
			)
			(return 0)
		)
		(= theCanReachList (- backY frontY))
		(= temp1 (- location id))
		(= bLit (- frontY (MulDiv theCanReachList id temp1)))
		(= slopeNum (- theFrontSize theBackSize))
		(= slopeDen
			(GetDistance theId theFrontY theLocation theBackY)
		)
		(= row (- temp1))
		(= canReachList theCanReachList)
		(while
		(or (> (Abs row) 128) (> (Abs canReachList) 128))
			(= row (/ row 2))
			(= canReachList (/ canReachList 2))
		)
		(= oShard
			(/
				(+ (* row row) (* canReachList canReachList))
				(* canReachList row)
			)
		)
		(return (self doit:))
	)
	
	(method (doit &tmp temp0 clientScaleX clientScaleY temp3 temp4 temp5 temp6)
		(= clientScaleX (client scaleX?))
		(= clientScaleY (client scaleY?))
		(= temp5
			(/
				(-
					bLit
					(= temp3
						(- (ego y?) (MulDiv (ego x?) row canReachList))
					)
				)
				oShard
			)
		)
		(= temp6 (+ (MulDiv row temp5 canReachList) temp3))
		(= temp4 (GetDistance id frontY temp5 temp6))
		(= temp4 (GetDistance location backY temp5 temp6))
		(= temp0 (+ backSize (MulDiv temp4 slopeNum slopeDen)))
		(MonoOut LOOKUP_ERROR (ego x?) (ego y?) temp0)
		(if
		(or (!= clientScaleX temp0) (!= clientScaleY temp0))
			(client scaleX: temp0 scaleY: temp0)
		)
	)
)
