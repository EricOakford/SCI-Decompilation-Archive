;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64969)
(include sci.sh)
(use Motion)


(class Rev of Cycle
	(properties
		scratch 0
		client 0
		caller 0
		cycleDir -1
		cycleCnt 0
		completed 0
		clientLastCel 0
	)
	
	(method (doit &tmp revNextCel)
		(if
		(!= (client cel?) (= revNextCel (self nextCel:)))
			(if (< revNextCel 0)
				(self cycleDone:)
			else
				(client cel: revNextCel)
			)
		)
	)
	
	(method (cycleDone)
		(client cel: (client lastCel:))
	)
)
