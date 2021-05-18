;;; Sierra Script 1.0 - (do not remove this comment)
(script# 214)
(include game.sh)
(use Main)
(use Motion)
(use Actor)


(class HighLite of Prop
	(properties
		highLiteX 0
		highLiteY 0
		deltaX 0
		deltaY 0
		leadingEdge 0
		ignoreCast 0
		eyesID 0
	)
	
	(method (init)
		(super init:)
		(self HighLitePosn:)
		(self
			view: 151
			loop: 1
			cel: 0
			setPri: 2
			posn: highLiteX highLiteY
			ignoreActors: TRUE
			setCycle: Forward
		)
		(self
			leadingEdge: (/ (+ (- (self brRight?) (self brLeft?)) 1) 2)
		)
	)
	
	(method (doit &tmp node nextNode obj theX theCel numCels)
		(self HighLitePosn:)
		(self posn: highLiteX highLiteY)
		(if (not ignoreCast)
			(= node (cast first:))
			(while node
				(= nextNode (cast next: node))
				(if
					(and
						(IsObject (= obj (NodeValue node)))
						(!= obj ego)
						(!= obj self)
						(!= obj eyesID)
					)
					(= theX (- (obj x?) (self x?)))
					(if (>= leadingEdge (Abs theX))
						(= numCels (NumCels obj))
						(if
							(<
								(= theCel
									(+
										(/
											(* (+ (/ (* theX 1000) leadingEdge) 5) (/ numCels 2))
											1000
										)
										(/ numCels 2)
									)
								)
								numCels
							)
							(obj show: cel: theCel forceUpd:)
						)
					else
						(obj hide:)
					)
				)
				(= node nextNode)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
	)
	
	(method (HighLitePosn &tmp egoLoop)
		(if (or (== (= egoLoop (ego loop?)) 2) (== egoLoop 1))
			(= highLiteX (- (ego x?) deltaX))
		else
			(= highLiteX (+ (ego x?) deltaX))
		)
		(= highLiteY (- (ego y?) deltaY))
	)
)
