;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64016)
(include sci.sh)
(use Main)
(use Set)
(use System)


(class TimePauser of Obj
	(properties
		scratch 0
		oThumb 0
		oldTimers 0
		oDownArrow 0
	)
	
	(method (init)
		(if (not oThumb)
			(= oThumb 1)
			(= oDownArrow gameTime)
			(= oldTimers timers)
			((= timers (Set new:)) name: {pausedTimers} add:)
		)
	)
	
	(method (dispose)
		(if oThumb
			(= oThumb 0)
			(= tickOffset (- (= gameTime oDownArrow) (GetTime)))
			(timers dispose:)
			(= timers oldTimers)
		)
	)
)
