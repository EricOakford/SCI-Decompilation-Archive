;;; Sierra Script 1.0 - (do not remove this comment)
(script# 94)
(include sci.sh)
(use Main)
(use Timer)
(use Game)
(use System)

(public
	PursuitRgn 0
	pursuitTimer 1
)

(local
	local0
)
(class PursuitRgn of Rgn
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
	)
	
	(method (init)
		(super init:)
		(cond 
			((< howFast 5) (= local0 60))
			((< howFast 10) (= local0 40))
			((<= howFast 15) (= local0 20))
		)
		(if (not (HaveMouse)) (= local0 (* 2 local0)))
	)
	
	(method (newRoom n)
		(= initialized 0)
		(= keep
			(OneOf n 420 430 435 440 448 450 454 460 480 490 660)
		)
		(if (not keep) (pursuitTimer dispose: delete:))
	)
	
	(method (increaseTime)
		(pursuitTimer
			seconds: (+ (pursuitTimer seconds?) local0)
		)
	)
	
	(method (decreaseTime)
		(pursuitTimer
			seconds: (- (pursuitTimer seconds?) local0)
		)
	)
)

(instance pursuitTimer of Timer
	(properties)
	
	(method (cue)
		(curRoom notify:)
	)
)
