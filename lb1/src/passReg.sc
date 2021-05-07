;;; Sierra Script 1.0 - (do not remove this comment)
(script# 212)
(include sci.sh)
(use Intrface)
(use Game)

(public
	passReg 0
)
(synonyms
	(room passage)
)

(instance passReg of Rgn
	(properties)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if
		(and (== (event type?) evSAID) (Said 'examine>'))
			(cond 
				((Said '/ceiling') (Print 212 0))
				((Said '/wall') (Print 212 1))
				((or (Said '/dirt') (Said '<down')) (Print 212 2))
				((Said '/stair') (Print 212 3))
				((Said '/platform') (Print 212 4))
				((Said '/panel') (Print 212 5))
			)
		)
	)
)
