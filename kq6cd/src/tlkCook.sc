;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1010)
(include sci.sh)
(use Main)
(use Kq6Window)
(use Talker)

(public
	tlkCook 57
)

(instance tlkCook of Narrator
	(properties
		y 10
	)
	
	(method (init)
		(= keepWindow 1)
		(= color (Kq6Window color?))
		(= back (Kq6Window back?))
		(= font userFont)
		(super init: &rest)
	)
)
