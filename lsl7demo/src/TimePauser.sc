;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64016)
(include sci.sh)
(use Main)
(use System)


(class TimePauser of Obj
	(properties
		scratch 0
		bIsPaused 0
		oldTimers 0
		nTimeFrozen 0
	)
	
	(method (init)
		(if (not bIsPaused)
			(= bIsPaused 1)
			(= nTimeFrozen gameTime)
			(= oldTimers timers)
			((= timers (Set new:)) name: {pausedTimers} add:)
		)
	)
	
	(method (dispose)
		(if bIsPaused
			(= bIsPaused 0)
			(= tickOffset (- (= gameTime nTimeFrozen) (GetTime)))
			(timers dispose:)
			(= timers oldTimers)
		)
	)
)
