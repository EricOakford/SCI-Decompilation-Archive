;;; Sierra Script 1.0 - (do not remove this comment)
(script# 94)
(include game.sh)
(use Main)
(use Timer)
(use Game)
(use System)

(public
	PursuitRgn 0
	pursuitTimer 1
)

(local
	pursuitSpeed
)
(class PursuitRgn of Region
	
	(method (init)
		(super init:)
		(cond 
			((< howFast 5)
				(= pursuitSpeed 60)
			)
			((< howFast 10)
				(= pursuitSpeed 40)
			)
			((<= howFast 15)
				(= pursuitSpeed 20)
			)
		)
		(if (not (HaveMouse))
			(= pursuitSpeed (* 2 pursuitSpeed))
		)
	)
	
	(method (newRoom n)
		(= initialized FALSE)
		(= keep
			(OneOf n 420 430 435 440 448 450 454 460 480 490 660)
		)
		(if (not keep)
			(pursuitTimer dispose: delete:)
		)
	)
	
	(method (increaseTime)
		(pursuitTimer
			seconds: (+ (pursuitTimer seconds?) pursuitSpeed)
		)
	)
	
	(method (decreaseTime)
		(pursuitTimer
			seconds: (- (pursuitTimer seconds?) pursuitSpeed)
		)
	)
)

(instance pursuitTimer of Timer

	(method (cue)
		(curRoom notify:)
	)
)
