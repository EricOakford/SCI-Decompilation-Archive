;;; Sierra Script 1.0 - (do not remove this comment)
(script# GLORY_OBSTACLES)
(include game.sh)
(use Main)
(use Polygon)
(use System)

(public
	AddMovingObstacle 0
	DeleteMovingObstacle 1
)

(procedure (AddMovingObstacle thePoly theType &tmp pts obj temp2)
	(if (< argc 2) (= theType PBarredAccess))
	(= obj (curRoom obstacles?))
	(= pts
		(MergePoly
			thePoly
			(obj elements?)
			(obj size?)
		)
	)
	(= temp2
		((Polygon new:)
			points: pts
			size: (MeasurePoly pts)
			type: theType
			dynamic: TRUE
			yourself:
		)
	)
	(obj add: temp2)
	(return temp2)
)

(procedure (DeleteMovingObstacle thePoly &tmp obj i temp2)
	((= obj (curRoom obstacles?))
		delete: thePoly
	)
	(for ((= i 0)) (< i (obj size?)) ((++ i))
		(if
		(>= (= temp2 ((obj at: i) type?)) 16)
			((obj at: i) type: (- temp2 16))
		)
	)
)

(procedure (MeasurePoly param1 &tmp i [temp1 2] temp3 temp4)
	(= temp3 -100)
	(for ((= i 0)) (!= temp3 30583) ((++ i))
		(= temp3 (WordAt param1 (* 2 i)))
	)
	(return (-- i))
)
