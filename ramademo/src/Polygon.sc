;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	POLYGON.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1990
;;;;
;;;;	Author: 	J. Mark Hood
;;;;	Updated:
;;;;
;;;;	A Polygon is an obstacle used by the polygon avoider.  They can also
;;;;	be used as onMe checks for Features.
;;;;
;;;;	Classes:
;;;;		Polygon


(script# POLYGON)
(include game.sh)
(use Array)
(use System)


(class Polygon kindof Object
	(properties
		size 		0
		points 	0
		type 		PNearestAccess
		dynamic 	FALSE
	)
;;;	(methods
;;;		onMe
;;;	)

	(method (init thePoints &tmp i arg)
		(= size (/ argc 2))
		(= points (IntArray with:	thePoints &rest))
		(= dynamic TRUE)
	)

	(method (dispose)
		(if (and dynamic points)
			(points dispose:)
		)
		(super dispose:)
	)
	(method (onMe oX oY)
		(return (InPolygon oX oY self))
	)
)

