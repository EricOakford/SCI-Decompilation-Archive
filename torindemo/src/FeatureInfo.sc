;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64900)
(include game.sh)
(use System)


(class FeatureInfo of Object
	(properties
		setScreenSpeed 0
		setSpeedFraction 0
	)
	
	(method (dispose)
		(if setScreenSpeed
			(setScreenSpeed
				release:
				dispose:
			)
		)
		(super dispose:)
	)
)
