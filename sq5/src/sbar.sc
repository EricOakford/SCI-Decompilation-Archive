;;; Sierra Script 1.0 - (do not remove this comment)
(script# 505)
(include sci.sh)
(use Main)
(use Game)
(use System)

(public
	sbar 0
)

(class sbar of Rgn
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
		state $0000
	)
	
	(method (init)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and (Btst 54) (not (self script?)))
			(self setScript: sWarningTimer)
		)
	)
	
	(method (newRoom n)
		(= keep (OneOf n 500 510 520 530))
		(= initialized 0)
		(super newRoom: n)
	)
)

(instance sWarningTimer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 100))
			(1
				(messager say: 1 0 0 1 self 501)
			)
			(2 (= seconds 50))
			(3
				(messager say: 1 0 0 2 self 501)
			)
			(4 (= seconds 50))
			(5
				(messager say: 1 0 0 3 self 501)
			)
			(6 (= seconds 50))
			(7 (curRoom newRoom: 550))
		)
	)
)
