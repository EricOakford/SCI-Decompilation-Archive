;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64900)
(include game.sh)
(use System)


(class FeatureInfo of Object
	(properties
		hotspotVerbList 0
		forceCursor 0
	)
	
	(method (dispose)
		(if hotspotVerbList
			(hotspotVerbList
				release:
				dispose:
			)
		)
		(super dispose:)
	)
)
