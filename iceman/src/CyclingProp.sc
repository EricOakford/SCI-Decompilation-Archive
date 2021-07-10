;;; Sierra Script 1.0 - (do not remove this comment)
(script# 812)
(include game.sh)
(use Motion)
(use Actor)


(class CyclingProp of Prop
	(properties
		signal ignrAct
	)
	
	(method (init)
		(self setPri: (if priority else -1) setCycle: Forward)
		(super init:)
	)
)
