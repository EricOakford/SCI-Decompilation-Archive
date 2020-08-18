;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	FLIPPOLY.SC
;;;;
;;;;	(c) Sierra On-Line, Inc., 1992
;;;;
;;;;	Author:	J. Mark Hood
;;;;	Updated:
;;;;
;;;;	The FlipPoly and FlipFeature procedures mirror polygons and features,
;;;;	primarily for use with picture mirroring.
;;;;
;;;;	Classes:
;;;;
;;;;	Procedures:
;;;;		FlipPoly
;;;;		FlipFeature


(script# FLIPPOLY)
(include game.sh)
(use Main)
(use Array)
(use System)

;;;(procedure
;;;	FlipPoly
;;;	FlipFeature
;;;)

(define SIZEOFPOINT 	4)
(define SIZEOFWORD 	2)

(public
	FlipPoly		0
	FlipFeature	1
)

;; pass a Polygon, a list of polygons or no args to use (curRoom obstacles?)
(procedure (FlipPoly arg &tmp theList)
	(cond
		((not argc)
			(= theList (curRoom obstacles?))
		)
		((arg isKindOf: Collection)
			(= theList arg)
		)
		(else
			(arg perform:flipPoly)
			(return)
		)
	)
	(theList eachElementDo: #perform flipPoly)
	(DisposeScript FLIPPOLY)
)


(instance flipPoly of Code
;;;	(define SIZEOFPOINT 	4)
;;;	(define SIZEOFWORD 	2)
	(method (doit thePoly &tmp i newPoints theSize)
		;; get a new array
		(= newPoints (IntArray with: (* SIZEOFPOINT (= theSize (thePoly size?))) {}))
		;; walk through the points
		(for ((= i 0))	(< i theSize) ((++ i))
			;; set the new x
			(newPoints at:
				(* SIZEOFPOINT i)
				(- 
					screenWidth 
					((thePoly points?) at:
						(- 
							(* SIZEOFPOINT theSize)
							(+ SIZEOFPOINT (* SIZEOFPOINT i))
						)
					)
				)
			)
			;; the y
			(newPoints at:
				(+ (* SIZEOFPOINT i) SIZEOFWORD)
				((thePoly points?) at:
					(- 
						(* SIZEOFPOINT theSize)
						(+ SIZEOFWORD (* SIZEOFPOINT i))
					)
				)
			)
		)
		;; clear Polygons old points array
		(if (thePoly dynamic?)
			((thePoly points?) dispose:)
		)
		;; set it to the newly created array
		(thePoly 
			points: 	newPoints, 
			dynamic:	TRUE
		)
	)
)

;; flip a feature, many features, a list of features 
;; or the feature list (no args)
(procedure (FlipFeature args &tmp i)
	(if (not argc)
		(features eachElementDo: #perform flipFeature)
	else
		(for ((= i 0)) (< i argc) ((++ i))
			(if ([args i] isKindOf: Collection)
				([args i] eachElementDo: #perform flipFeature)
			else
				([args i] perform: flipFeature)
			)
		)
	)
	(DisposeScript FLIPPOLY)
)

(instance flipFeature of Code
	(method (doit theFeature &tmp node obj theList)
		(if ((theFeature onMeCheck?) isKindOf:	List)
			(= theList (theFeature onMeCheck?))
			(for	((= node (List LFirstNode (theList elements?))))
					node
					((= node (theList nextNode?)))
				(theList nextNode: (List LNextNode node))
				(= obj (List LNodeValue node))
				(FlipPoly obj)		;so all list elements will flip polygons
			)
		else
			(FlipPoly (theFeature onMeCheck?))
		)
	)
)
