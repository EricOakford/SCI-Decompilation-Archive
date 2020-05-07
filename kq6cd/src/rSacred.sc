;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include sci.sh)
(use Game)
(use System)

(public
	rSacred 0
)

(class rSacred of Rgn
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
		geniePresent 0
		marePresent 0
	)
	
	(method (newRoom n)
		(= keep (OneOf n 300 310 320 330 340 350 370 380 390))
		(= initialized 0)
		(super newRoom: n &rest)
	)
)
