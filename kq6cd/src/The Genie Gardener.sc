;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1056)
(include sci.sh)
(use Kq6Talker)
(use Kq6Procs)
(use Actor)

(public
	The_Genie_Gardener 55
)

(instance The_Genie_Gardener of Kq6Talker
	(properties
		view 2002
		loop 1
		cel 1
		talkWidth 213
		textX -180
		textY 18
		name "The Genie Gardener"
	)
	
	(method (init)
		(if (Btst 163)
			(= x 204)
			(= y 74)
		else
			(= x 202)
			(= y 98)
		)
		(super init: 0 0 tMouth1 &rest)
	)
)

(instance tMouth1 of Prop
	(properties
		view 2002
		loop 1
	)
)
