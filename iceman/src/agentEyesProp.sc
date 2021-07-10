;;; Sierra Script 1.0 - (do not remove this comment)
(script# 360)
(include game.sh)
(use Motion)
(use Actor)
(use System)

(public
	agentEyesProp 0
	agentLipsProp 1
	sparkleProp 2
)

(instance agentEyesProp of Prop
	(properties
		y 77
		x 145
		view 302
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: agentsFeaturesPropScript)
	)
)

(instance agentsFeaturesPropScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(agentLipsProp setCycle: EndLoop self)
			)
			(2
				(sparkleProp setCycle: EndLoop self)
			)
			(3
				(sparkleProp setCycle: BegLoop self)
			)
			(4 (= seconds 2))
			(5
				(agentLipsProp setCycle: BegLoop self)
			)
			(6 (= seconds 5))
			(7 (self init:))
		)
	)
)

(instance agentLipsProp of Prop
	(properties
		y 104
		x 141
		view 302
		loop 1
	)
)

(instance sparkleProp of Prop
	(properties
		y 97
		x 153
		view 302
		loop 2
		priority 7
		signal fixPriOn
	)
	
	(method (delete)
		(super delete:)
		(DisposeScript 360)
	)
)
