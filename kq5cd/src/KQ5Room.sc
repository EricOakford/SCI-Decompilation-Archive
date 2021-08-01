;;; Sierra Script 1.0 - (do not remove this comment)
(script# 888)
(include sci.sh)
(use Main)
(use Game)
(use User)


(class KQ5Room of Rm
	(properties
		script 0
		number 0
		timer 0
		keep 0
		initialized 0
		lookStr 0
		picture 0
		style $ffff
		horizon 0
		controls 0
		north 0
		east 0
		south 0
		west 0
		curPic 0
		picAngle 0
		vanishingX 160
		vanishingY -30000
		obstacles 0
	)
	
	(method (init)
		(super init: &rest)
		(if (and (User canControl:) (User canInput:))
			(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
		)
	)
	
	(method (newRoom)
		(theGame setCursor: waitCursor 1)
		(super newRoom: &rest)
	)
)
