;;; Sierra Script 1.0 - (do not remove this comment)
(script# 214)
(include sci.sh)
(use Main)
(use Motion)
(use Actor)


(class HighLite of Prop
	(properties
		y 0
		x 0
		z 0
		heading 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
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
			ignoreActors: 1
			setCycle: Fwd
		)
		(self
			leadingEdge: (/ (+ (- (self brRight?) (self brLeft?)) 1) 2)
		)
	)
	
	(method (doit &tmp castFirst theCastFirst temp2 temp3 temp4 temp5)
		(self HighLitePosn:)
		(self posn: highLiteX highLiteY)
		(if (not ignoreCast)
			(= castFirst (cast first:))
			(while castFirst
				(= theCastFirst (cast next: castFirst))
				(if
					(and
						(IsObject (= temp2 (NodeValue castFirst)))
						(!= temp2 ego)
						(!= temp2 self)
						(!= temp2 eyesID)
					)
					(= temp3 (- (temp2 x?) (self x?)))
					(if (>= leadingEdge (Abs temp3))
						(= temp5 (NumCels temp2))
						(if
							(<
								(= temp4
									(+
										(/
											(* (+ (/ (* temp3 1000) leadingEdge) 5) (/ temp5 2))
											1000
										)
										(/ temp5 2)
									)
								)
								temp5
							)
							(temp2 show: cel: temp4 forceUpd:)
						)
					else
						(temp2 hide:)
					)
				)
				(= castFirst theCastFirst)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
	)
	
	(method (HighLitePosn &tmp egoLoop)
		(if
		(or (== (= egoLoop (ego loop?)) 2) (== egoLoop 1))
			(= highLiteX (- (ego x?) deltaX))
		else
			(= highLiteX (+ (ego x?) deltaX))
		)
		(= highLiteY (- (ego y?) deltaY))
	)
)
