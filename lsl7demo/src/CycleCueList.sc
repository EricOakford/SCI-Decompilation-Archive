;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64041)
(include sci.sh)
(use Array)
(use Motion)
(use System)


(class CycleCueList of Cycle
	(properties
		scratch 0
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		clientLastCel 0
		endCel 0
		oCueArray 0
		nCurCue 0
		nCues 0
	)
	
	(method (init param1 param2 theCycleDir theCaller theTheClientLastCel &tmp temp0 theClientLastCel temp2)
		(super init: param1)
		(= cycleDir theCycleDir)
		(if (>= argc 4) (= caller theCaller))
		(= nCues (Max 0 (- argc 4)))
		(= nCurCue 0)
		(= oCueArray (IntArray new:))
		(= temp2 0)
		(while (< temp2 nCues)
			(if
				(>
					(= theClientLastCel [theTheClientLastCel temp2])
					clientLastCel
				)
				(= theClientLastCel clientLastCel)
			)
			(if (< theClientLastCel 0) (= theClientLastCel 0))
			(oCueArray at: temp2 theClientLastCel)
			(++ temp2)
		)
		(= endCel (Max 0 (Min param2 clientLastCel)))
	)
	
	(method (doit &tmp cycleCueListNextCel clientCel)
		(if
			(!=
				(= clientCel (client cel?))
				(= cycleCueListNextCel (self nextCel:))
			)
			(if (== clientCel endCel)
				(self cycleDone:)
			else
				(client cel: cycleCueListNextCel)
				(if
					(and
						(< nCurCue nCues)
						(== cycleCueListNextCel (oCueArray at: nCurCue))
					)
					(++ nCurCue)
					(if caller (caller cue:))
				)
			)
		)
	)
	
	(method (dispose)
		(if oCueArray (oCueArray dispose:) (= oCueArray 0))
		(super dispose: &rest)
	)
	
	(method (cycleDone)
		(= completed 1)
		(= caller 0)
		(self motionCue:)
	)
)

(class End of CycleCueList
	(properties
		scratch 0
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		clientLastCel 0
		endCel 0
		oCueArray 0
		nCurCue 0
		nCues 0
	)
	
	(method (init param1)
		(super init: param1 (param1 lastCel:) 1 &rest)
	)
)

(class Beg of CycleCueList
	(properties
		scratch 0
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		clientLastCel 0
		endCel 0
		oCueArray 0
		nCurCue 0
		nCues 0
	)
	
	(method (init param1)
		(super init: param1 0 -1 &rest)
	)
)
