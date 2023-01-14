;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	SORTCOPY.SC
;;;;	(c) Sierra On-Line, Inc, 1990
;;;;
;;;;	Author: J.Mark Hood
;;;;							
;;;;	This module is a replacement of the old sortcopy by Pablo Ghenis.
;;;;	It was rewritten to utilize the Sort kernel call for an overall
;;;; performance gain of approximately 1000%


(script# SORTCOPY)
(include game.sh)
(use Main)
(use Sight)
(use System)

(public
	SortedAdd	0
)
(local
	frontList
	backList
	outList
)
(procedure (SortedAdd &tmp frontList2 outList2 backList2) 
	((= frontList 	(EventHandler new:)) add:, name:{fl})
	((= outList   	(EventHandler new:)) add:, name:{ol})
	((= backList  	(EventHandler new:)) add:, name:{bl})

	;; don't want initial elements KList since Sort will do that
	((= frontList2 (EventHandler new:)) name:{fl2})
	((= outList2   (EventHandler new:)) name:{ol2})
	((= backList2  (EventHandler new:)) name:{bl2})
	(cast 		eachElementDo: #perform:preSortCode) ; put in appropiate list
	(features	eachElementDo: #perform:preSortCode) ; put in appropiate list

 	(Sort frontList 	frontList2 	frontSortCode)
	(sortedFeatures add: frontList2)

	(Sort outList 		outList2 	frontSortCode)
	(sortedFeatures add: outList2)

	(sortedFeatures add: regions)
	(sortedFeatures add: locales)

	(Sort backList 	backList2 	backSortCode)
	(sortedFeatures add: backList2)

	(frontList 	release:, dispose:)
	(outList 	release:, dispose:)
	(backList 	release:, dispose:)
)


(instance preSortCode of Code
	(method (doit obj)
		(cond 
			((CantBeSeen obj ego)	
				(backList add: obj)
			)
			((IsOffScreen obj)
				(outList add: obj)
			)
			(else
				(frontList add: obj)
			)
		)
	)
)


(instance frontSortCode of Code
	(method (doit obj &tmp dist ang)
		(= dist (ego distanceTo: obj))
		;;favor objects straight in front of ego
		(= ang
			(AngleDiff (ego heading?)
				(GetAngle (ego x?) (ego y?) (obj x?) (obj y?))
			)
		)
		;fudge to avoid trig divide by zero
		(if (== (umod ang 90) 0)	(-- ang))
		
 		;straight ahead is better when in front
 		(if (> (Abs ang) 90)
 			(= ang 89)
 			(*= dist 10)	;penalize stuff behind ego
 		)
 		(= dist (Abs (CosDiv ang dist)))
		(if (< dist 0) (= dist INFINITY))	;overflow compensation
		(return dist)
	)
)


(instance backSortCode of Code
	(method (doit obj &tmp dist ang)
		(= dist (ego distanceTo: obj))
		;;favor objects straight in front of ego
		(= ang
			(AngleDiff (ego heading?)
				(GetAngle (ego x?) (ego y?) (obj x?) (obj y?))
			)
		)
		;fudge to avoid trig divide by zero
		(if (== (umod ang 90) 0)	(-- ang))
		(= dist (SinDiv ang dist))
		(if (< dist 0) (= dist INFINITY))	;overflow compensation
	)
)