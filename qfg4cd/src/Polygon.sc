;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64946)
(include sci.sh)
(use Array)
(use System)


(class Polygon of Obj
	(properties
		scratch 0
		size 0
		points 0
		type $0001
		dynamic 0
	)
	
	(method (init param1 &tmp [temp0 2])
		(= size (/ argc 2))
		(= points (IntArray with: param1 &rest))
		(= dynamic 1)
	)
	
	(method (dispose)
		(if (and dynamic points) (points dispose:))
		(super dispose:)
	)
	
	(method (onMe theObjOrX theY)
		(InPolygon theObjOrX theY self)
	)
)
