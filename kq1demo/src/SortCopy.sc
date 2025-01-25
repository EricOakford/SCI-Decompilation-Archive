;;; Sierra Script 1.0 - (do not remove this comment)
(script# SORTCOPY)
(include game.sh)
(use Main)
(use Sight)
(use System)

;;;(procedure
;;;	SortedAdd
;;;)

(public
	SortedAdd	0
)

(procedure (SortedAdd theOrigin theLists 
		&tmp	
		i toList fromColl
		node dist obj ang
		invisible
		
		objMinFront distMinFront frontList	;features ego can see on screen
		objMinOut distMinOut	outList			;   "      "   "   "  off   "
		objMinBack distMinBack	backList		;   "     behind ego
		
		
		
	)
	
	((= frontList (EventHandler new:)) add:, name:{fl})
	((= outList   (EventHandler new:)) add:, name:{ol})
	((= backList  (EventHandler new:)) add:, name:{bl})
	
	((= toList [theLists 0]) add: 
		frontList 
		outList 
		regions
		locales
		backList
	)
	
	(-- argc)					;to make up for new argument theOrigin
	(repeat
		
		;reset for each pass
		(= objMinFront		(= objMinOut	(= objMinBack	NULL)))
		(= distMinFront	(= distMinOut	(= distMinBack	INFINITY)))
		
		(for
			(	(= i 1)
			)
			(< i argc)
			(	(++ i)
			)
			
			(= fromColl [theLists i])
			
			(for	
				((= node (FirstNode (fromColl elements?))))
				(and
					node
					(IsObject (= obj (NodeValue node)))
				)
				((= node (NextNode node)))
				
				(if (toList firstTrue: #contains obj)				;already seen
					(continue)
				)
				
				(= dist (theOrigin distanceTo: obj))
				
				;;favor objects straight in front of ego
				(= ang
					(AngleDiff (theOrigin heading?)
						(GetAngle (theOrigin x?) (theOrigin y?) (obj x?) (obj y?))
					)
				)
				;fudge to avoid trig divide by zero
				(if (== (umod ang 90) 0)	(-- ang))
				
				(if (= invisible (CantBeSeen obj theOrigin))
					;straight aside is better when behind
					(= dist (SinDiv ang dist))
				else
					;straight ahead is better when in front
					(if (> (Abs ang) 90)
						(= ang 89)
						(*= dist 10)	;penalize stuff behind ego
					)
					(= dist (Abs (CosDiv ang dist)))
				)
				
				(if (< dist 0) (= dist INFINITY))	;overflow compensation
				
				(cond
					(invisible
						(if (<= dist distMinBack)
							(= distMinBack dist)
							(= objMinBack obj)
						)
					)
					((IsOffScreen obj)				;off screen but visible
						(if (<= dist distMinOut)
							(= distMinOut dist)
							(= objMinOut obj)
						)
					)
					(else									;visible and on screen
						(if (<= dist distMinFront)
							(= distMinFront dist)
							(= objMinFront obj)
						)
					)
				)
				
			);for loop over elements
			
		);for loop over lists
		
		(if objMinFront	(frontList addToEnd: objMinFront))
		(if objMinOut		(outList addToEnd: objMinOut))
		(if objMinBack		(backList addToEnd: objMinBack))
		(or 
			objMinFront
			objMinOut
			objMinBack
			(break) ;out of repeat
		)
		
	);repeat
	
);SortedAdd procedure
