;;; Sierra Script 1.0 - (do not remove this comment)
(script# POLYGON)
(include game.sh)
(use System)

;; Polygon class, originally for use by the polygon based avoider.
;; Other uses are possible however, such as a features onMe check.
;; 7/23/90 J.M.H. Avoider librarian.

(class Polygon kindof Object
	(properties
		size 		0
		points 	0
		type 		PNearestAccess
		dynamic 	FALSE
	)

	(method (init thePoints &tmp i arg)
		(= size (/ argc 2))
		(= points (Memory MNeedPtr	(* 2 argc)))
		(StrCpy points @thePoints (- (* argc 2)))
		(= dynamic TRUE)
	)

	(method (dispose)
		(if (and dynamic points)
			(Memory MDisposePtr points)
		)
		(super dispose:)
	)
)