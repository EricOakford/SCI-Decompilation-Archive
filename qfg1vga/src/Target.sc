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
				(not (Btst fKoboldProtected))
				(IsObject projObj)
				(not ((projObj script?) register?))
				(self onMe: (projObj x?) (- (projObj y?) 34))
			)
			(switch (projObj view?)
				(522
					(self getHurt: (+ 5 (/ [egoStats FLAMEDART] 3)))
				)
				(524
					(self getHurt: (+ 5 (/ [egoStats STR] 10)))
				)
				(else
					(self getHurt: 1)
				)
			)
			(projObj setPri: 15)
			((projObj script?) changeState: 3)
			(= projObj 0)
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
