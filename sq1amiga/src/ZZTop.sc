;;; Sierra Script 1.0 - (do not remove this comment)
(script# 302)
(include sci.sh)
(use Intrface)
(use Motion)
(use Actor)
(use System)

(public
	forwardScript 0
	drum 1
	ZZTop 2
	ZZTop2 3
	ZZTop3 4
)

(instance forwardScript of Script
	(properties)
)

(class FROR of Cycle
	(properties
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		firstStaticCel 0
		lastStaticCel 0
		minCyclesToGo 100
		maxCyclesToGo 250
		cyclesToGo 1
	)
	
	(method (init param1 theMinCyclesToGo theMaxCyclesToGo theFirstStaticCel theLastStaticCel)
		(super init: param1)
		(if (> argc 1)
			(= minCyclesToGo theMinCyclesToGo)
			(if (> argc 2)
				(= maxCyclesToGo theMaxCyclesToGo)
				(if (> argc 3)
					(= firstStaticCel theFirstStaticCel)
					(if (> argc 4) (= lastStaticCel theLastStaticCel))
				)
			)
		)
	)
	
	(method (doit &tmp fRORNextCel)
		(if (not (-- cyclesToGo))
			(= cyclesToGo (Random minCyclesToGo maxCyclesToGo))
			(= cycleDir (- 1 (Random 0 2)))
			(if (not cycleDir)
				(client cel: (Random firstStaticCel lastStaticCel))
			)
		)
		(= fRORNextCel (self nextCel:))
		(if cycleDir
			(cond 
				((== cycleDir 1) (client cel: fRORNextCel))
				((< fRORNextCel 0) (client cel: (client lastCel:)))
				(else (client cel: fRORNextCel))
			)
		)
	)
)

(instance drum of View
	(properties
		x 112
		y 67
		description {drum}
		lookStr {On stage are two heavily-bearded gents and a relatively clean-shaven drummer. They're one of the hottest bands in the quadrant.}
		view 533
	)
)

(instance ZZTop of Prop
	(properties
		x 112
		y 49
		description {drummer}
		lookStr {On stage are two heavily-bearded gents and a relatively clean-shaven drummer. They're one of the hottest bands in the quadrant.}
		view 433
		cel 5
		cycleSpeed 4
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 302 0))
			(11 (Print 302 1))
			(12 (Print 302 2))
			(5 (Print 302 3))
			(4
				(switch theItem
					(10 (Print 302 4))
					(0 (Print 302 5))
					(15 (Print 302 6))
					(2 (Print 302 7))
					(4 (Print 302 8))
					(5 (Print 302 9))
					(11 (Print 302 10))
					(17 (Print 302 11))
					(18 (Print 302 11))
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ZZTop2 of Prop
	(properties
		x 133
		y 93
		description {guitar}
		lookStr {On stage are two heavily-bearded gents and a relatively clean-shaven drummer. They're one of the hottest bands in the quadrant.}
		view 433
		loop 1
		cel 5
		priority 6
		signal $0010
		cycleSpeed 4
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 302 0))
			(11 (Print 302 1))
			(12 (Print 302 2))
			(5 (Print 302 3))
			(4
				(switch theItem
					(10 (Print 302 4))
					(0 (Print 302 5))
					(15 (Print 302 6))
					(2 (Print 302 7))
					(4 (Print 302 8))
					(5 (Print 302 9))
					(11 (Print 302 10))
					(17 (Print 302 11))
					(18 (Print 302 11))
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ZZTop3 of Prop
	(properties
		x 161
		y 88
		description {guitar}
		lookStr {On stage are two heavily-bearded gents and a relatively clean-shaven drummer. They're one of the hottest bands in the quadrant.}
		view 433
		loop 1
		cel 3
		priority 6
		signal $0010
		cycleSpeed 4
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 302 0))
			(11 (Print 302 1))
			(12 (Print 302 2))
			(5 (Print 302 3))
			(4
				(switch theItem
					(10 (Print 302 4))
					(0 (Print 302 5))
					(15 (Print 302 6))
					(2 (Print 302 7))
					(4 (Print 302 8))
					(5 (Print 302 9))
					(11 (Print 302 10))
					(17 (Print 302 11))
					(18 (Print 302 11))
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
