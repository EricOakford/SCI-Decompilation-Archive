;;; Sierra Script 1.0 - (do not remove this comment)
(script# FLIPPOLY)
(include game.sh)
(use Main)
(use System)

;; FlipPoly procedure for use (primarily) with picture mirroring.
;; 7/3/91 	J.M.H. 
;; FlipFeature procedure for use (primarily) with picture mirroring.
;; 7/24/91	J.M.H.

;;;(procedure
;;;	FlipPoly
;;;	FlipFeature
;;;)

(define SIZEOFPOINT 	4)
(define SIZEOFWORD 	2)
	
(public
	FlipPoly	0
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
		(= newPoints 
			(Memory MNeedPtr (* SIZEOFPOINT (= theSize (thePoly size?))))
		)
		;; walk through the points
		(for ((= i 0))	(< i theSize) ((++ i))
			;; set the new x
			(Memory MWriteWord	
				;; location of new x
				(+ newPoints (* SIZEOFPOINT i))
				;; translate horizontally
				(- 
					320 
					;; old x?
					(Memory MReadWord 
						(- 
							(+ 
								(thePoly points?) 
								(* SIZEOFPOINT theSize)
							)	
							(+ SIZEOFPOINT (* SIZEOFPOINT i))
						)
					)
				)
			)
			;; the y
			(Memory MWriteWord
				;; location of new y
				(+ newPoints (* SIZEOFPOINT i) SIZEOFWORD)
				;; get old y as is
				(Memory MReadWord 
					(- 
						(+ 
							(thePoly points?) 
							(* SIZEOFPOINT theSize)
						)	
						(+ SIZEOFWORD (* SIZEOFPOINT i))
					)
				)
			)
		)
		;; clear Polygons old points array
		(if (thePoly dynamic?)
			(Memory MDisposePtr (thePoly points?))
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
	(method (doit theFeature &tmp oldLeft)
		(if (IsObject (theFeature onMeCheck?))
			(FlipPoly (theFeature onMeCheck?))
		else
			(= oldLeft (theFeature nsLeft?))
			(theFeature
				nsLeft: 		(- 320 (theFeature nsRight?)),
				nsRight: 	(- 320 oldLeft)
			)
		)
	)
)