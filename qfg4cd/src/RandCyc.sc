;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64941)
(include sci.sh)
(use Motion)


(class RandCycle of Cycle
	(properties
		scratch 0
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		clientLastCel 0
		count -1
		reset 0
	)
	
	(method (init param1 param2 theCaller theReset)
		(super init: param1)
		(if (>= argc 4) (= reset theReset))
		(if reset (client cel: 0))
		(= cycleCnt (GetTime))
		(if (>= argc 2)
			(if (!= param2 -1)
				(= count (+ (GetTime) param2))
			else
				(= count -1)
			)
			(if (>= argc 3) (= caller theCaller))
		else
			(= count -1)
		)
	)
	
	(method (doit &tmp temp0)
		(if
		(or (> count (= temp0 (GetTime))) (== count -1))
			(if (> (- temp0 cycleCnt) (client cycleSpeed?))
				(client cel: (self nextCel:))
				(= cycleCnt (GetTime))
			)
		else
			(if reset (client cel: 0))
			(self cycleDone:)
		)
	)
	
	(method (nextCel &tmp temp0)
		(return
			(if (!= (NumCels client) 1)
				(while
					(==
						(= temp0 (Random 0 (client lastCel:)))
						(client cel?)
					)
				)
				temp0
			else
				0
			)
		)
	)
	
	(method (cycleDone)
		(= completed 1)
		(self motionCue:)
	)
)

(class RTRandCycle of RandCycle
	(properties
		scratch 0
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		clientLastCel 0
		count -1
		reset 0
	)
)
