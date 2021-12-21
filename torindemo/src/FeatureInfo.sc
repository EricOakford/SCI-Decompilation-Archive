;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64900)
(include sci.sh)
(use System)


(class FeatureInfo of Obj
	(properties
		scratch 0
		setScreenSpeed 0
		setSpeedFraction 0
	)
	
	(method (dispose)
		(if setScreenSpeed (setScreenSpeed release: dispose:))
		(super dispose:)
	)
)
