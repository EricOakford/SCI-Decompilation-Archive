;;; Sierra Script 1.0 - (do not remove this comment)
(script# TARGET) ;812
(include game.sh)
(use Main)
(use Feature)
(use Actor)


(class TargActor of Actor
	(properties
		code 0
		status 0
		targDeltaX 0
		targDeltaY 0
	)
	
	(method (doit)
		(if
			(and
				(not (Btst fFlag281))
				(IsObject gClient)
				(not ((gClient script?) register?))
				(self onMe: (gClient x?) (- (gClient y?) 34))
			)
			(switch (gClient view?)
				(522
					(self getHurt: (+ 5 (/ [egoStats 23] 3)))
				)
				(524
					(self getHurt: (+ 5 (/ [egoStats 0] 10)))
				)
				(else  (self getHurt: 1))
			)
			(gClient setPri: 15)
			((gClient script?) changeState: 3)
			(= gClient 0)
		)
		(super doit:)
	)
	
	(method (getHurt)
	)
)

(class TargFeature of Feature
	(properties
		targDeltaX 0
		targDeltaY 0
	)
	
	(method (getHurt)
	)
)
