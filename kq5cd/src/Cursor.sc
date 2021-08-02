;;; Sierra Script 1.0 - (do not remove this comment)
(script# 766)
(include game.sh)
(use System)


(class Cursor kindof Object
	;;; The Cursor class is an object used to control the user's cursor.
	;;;	(Written by Robert Lindsley, July, 1991)

	(properties
		view			NULL
		loop			NULL
		cel			NULL
		x				0
		y				0
		skipOne 0
		skipTwo 0
		skipThree 0
		skipFour 0
		curPosnX 0
		curPosnY 0
	)
	
	(method (init)
		(if view
			(SetCursor view loop cel x y skipOne skipTwo skipThree skipFour)
		)
		(if (or curPosnX curPosnY)
			(Intersections curPosnX curPosnY)
			(= curPosnX (= curPosnY 0))
		)
	)
	
	(method (posn theX theY &tmp [temp0 3])
		(= curPosnX theX)
		(= curPosnY theY)
		(self init:)
	)
	
	(method (posnOrigin theX theY)
		(= x theX)
		(= y theY)
		(self init:)
	)
	
	(method (setLoop whichLoop)
		(= loop whichLoop)
		(self init:)
	)
	
	(method (skipColor theSkipOne theSkipTwo theSkipThree theSkipFour)
		(= skipOne theSkipOne)
		(if (>= argc 1)
			(= skipTwo theSkipTwo)
			(if (>= argc 2)
				(= skipThree theSkipThree)
				(if (>= argc 3)
					(= skipFour theSkipFour)
				)
			)
		)
		(self init:)
	)
	
	(method (setCel whichCel)
		(= cel whichCel)
		(self init:)
	)
)
