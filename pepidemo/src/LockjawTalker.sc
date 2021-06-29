;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkLockjaw)
(include game.sh)
(use Main)
(use BalloonTalker)

(public
	LockjawTalker 0
)

(local
	local0
)
(instance LockjawTalker of BalloonTalker
	(properties
		talkWidth 0
		font 61
	)
	
	(method (init &tmp t l b r)
		(= x ((ScriptID 895 1) x?))
		(= y ((ScriptID 895 1) y?))
		(= t ((ScriptID 895 1) nsTop?))
		(= l ((ScriptID 895 1) nsLeft?))
		(= b ((ScriptID 895 1) nsBottom?))
		(= r ((ScriptID 895 1) nsRight?))
		(cond 
			((+ winX winY))
			((<= x 150)
				(if (<= y 100)
					(= x (- r 5))
					(= y (+ t 30))
					(= tailPosn 3)
				else
					(= x (- r 5))
					(= y t)
					(= tailPosn 0)
				)
				(= x (+ x offX))
				(= y (+ y offY))
			)
			(else
				(if (<= y 100)
					(= x (- l 100))
					(= y (+ t 30))
					(= tailPosn 4)
				else
					(= x (- l 100))
					(= y t)
					(= tailPosn 1)
				)
				(= x (+ x offX))
				(= y (+ y offY))
			)
		)
		(if (not talkWidth)
			(= talkWidth 90)
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(if ((ScriptID 895 1) cycler?)
			((ScriptID 895 1) startUpd:)
		)
		(Animate (cast elements?) FALSE)
	)
	
	(method (say)
		(if dontUpd
			((ScriptID 895 1) stopUpd:)
		)
		(super say: &rest)
	)
)
