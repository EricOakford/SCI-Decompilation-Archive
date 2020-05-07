;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50)
(include sci.sh)
(use Game)
(use System)

(public
	rBeast 0
)

(class rBeast of Rgn
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
	)
	
	(method (newRoom n)
		(= keep (OneOf n 500 510 520 530 540))
		(= initialized 0)
		(super newRoom: n &rest)
	)
)
