;;; Sierra Script 1.0 - (do not remove this comment)
(script# 768)
(include sci.sh)
(use Cursor)


(class KQCursor of Cursor
	(properties
		view 0
		loop 0
		cel 0
		x 0
		y 0
		skipOne 0
		skipTwo 0
		skipThree 0
		skipFour 0
		curPosnX 0
		curPosnY 0
		number 0
	)
	
	(method (init)
		(cond 
			(number (SetCursor number &rest))
			(view
				(SetCursor
					view
					loop
					cel
					x
					y
					(if (and skipOne (!= skipOne 0)) skipOne else 900)
					(if (and skipTwo (!= skipTwo 0)) skipTwo else 900)
					(if (and skipThree (!= skipThree 0)) skipThree else 900)
					(if (and skipFour (!= skipFour 0)) skipFour else 900)
				)
				(if (or curPosnX curPosnY)
					(Intersections curPosnX curPosnY)
					(= curPosnX (= curPosnY 0))
				)
			)
		)
	)
)
