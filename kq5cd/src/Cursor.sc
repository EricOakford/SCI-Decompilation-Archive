;;; Sierra Script 1.0 - (do not remove this comment)
(script# 766)
(include sci.sh)
(use System)


(class Cursor of Obj
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
	)
	
	(method (init)
		(if view
			(SetCursor
				view
				loop
				cel
				x
				y
				skipOne
				skipTwo
				skipThree
				skipFour
			)
		)
		(if (or curPosnX curPosnY)
			(Intersections curPosnX curPosnY)
			(= curPosnX (= curPosnY 0))
		)
	)
	
	(method (posn theCurPosnX theCurPosnY &tmp [temp0 3])
		(= curPosnX theCurPosnX)
		(= curPosnY theCurPosnY)
		(self init:)
	)
	
	(method (posnOrigin theX theY)
		(= x theX)
		(= y theY)
		(self init:)
	)
	
	(method (setLoop theLoop)
		(= loop theLoop)
		(self init:)
	)
	
	(method (skipColor theSkipOne theSkipTwo theSkipThree theSkipFour)
		(= skipOne theSkipOne)
		(if (>= argc 1)
			(= skipTwo theSkipTwo)
			(if (>= argc 2)
				(= skipThree theSkipThree)
				(if (>= argc 3) (= skipFour theSkipFour))
			)
		)
		(self init:)
	)
	
	(method (setCel theCel)
		(= cel theCel)
		(self init:)
	)
)
