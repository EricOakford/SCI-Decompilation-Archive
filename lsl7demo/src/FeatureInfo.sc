;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64900)
(include sci.sh)
(use System)


(class FeatureInfo of Obj
	(properties
		scratch 0
		hotspotVerbList 0
		forceCursor 0
	)
	
	(method (dispose)
		(if hotspotVerbList (hotspotVerbList release: dispose:))
		(super dispose:)
	)
)
