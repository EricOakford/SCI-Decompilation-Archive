;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64973)
(include sci.sh)
(use Main)
(use System)


(class Timer of Obj
	(properties
		scratch 0
		cycleCnt -1
		seconds -1
		ticks -1
		lastTime -1
		client 0
	)
	
	(procedure (localproc_006c &tmp theClient)
		(= theClient client)
		(= client 0)
		(if theClient
			(if (theClient respondsTo: #timer) (theClient timer: 0))
			(if (theClient respondsTo: #cue) (theClient cue:))
		)
	)
	
	
	(method (new)
		(return (if (== self Timer) (super new:) else self))
	)
	
	(method (init theClient)
		(= client theClient)
		(timers add: self)
		(if (theClient respondsTo: #timer)
			(if (theClient timer?) ((theClient timer?) dispose:))
			(theClient timer: self)
		)
	)
	
	(method (doit &tmp theLastTime)
		(cond 
			((!= cycleCnt -1) (if (not (-- cycleCnt)) (localproc_006c)))
			((!= seconds -1)
				(if (!= lastTime (= theLastTime (GetTime 1)))
					(= lastTime theLastTime)
					(if (not (-- seconds)) (localproc_006c))
				)
			)
			((> (- gameTime ticks) 0) (localproc_006c))
		)
	)
	
	(method (dispose)
		(if (and client (client respondsTo: #timer))
			(client timer: 0)
		)
		(= client 0)
	)
	
	(method (set param1 param2 param3 param4 &tmp temp0 temp1 temp2)
		(if (== (= temp2 6) 0) (= temp2 1))
		(= temp1 (/ (* param2 60) temp2))
		(if (> argc 2)
			(= temp1 (+ temp1 (/ (* param3 3600) temp2)))
		)
		(if (> argc 3)
			(= temp1 (+ temp1 (* (/ (* param4 3600) temp2) 60)))
		)
		((= temp0 (if (& -info- $8000) (self new:) else self))
			init: param1
			cycleCnt: temp1
		)
		(return temp0)
	)
	
	(method (setCycle cType sendParams &tmp temp0)
		((= temp0 (if (& -info- $8000) (self new:) else self))
			init: cType
			cycleCnt: sendParams
		)
		(return temp0)
	)
	
	(method (setReal param1 param2 param3 param4 &tmp temp0 temp1)
		(= temp1 param2)
		(if (> argc 2) (= temp1 (+ temp1 (* param3 60))))
		(if (> argc 3) (= temp1 (+ temp1 (* param4 3600))))
		((= temp0 (if (& -info- $8000) (self new:) else self))
			init: param1
			seconds: temp1
		)
		(return temp0)
	)
	
	(method (delete)
		(if (== client 0)
			(timers delete: self)
			(super dispose:)
		)
	)
	
	(method (setTicks param1 param2 &tmp temp0)
		(= temp0 (if (& -info- $8000) (self new:) else self))
		(temp0 ticks: (+ gameTime param1) init: param2)
		(return temp0)
	)
)

(class TO of Obj
	(properties
		scratch 0
		timeLeft 0
	)
	
	(method (doit)
		(if timeLeft (-- timeLeft))
	)
	
	(method (set theTimeLeft)
		(= timeLeft theTimeLeft)
	)
)
