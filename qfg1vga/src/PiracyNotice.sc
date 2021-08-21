;;; Sierra Script 1.0 - (do not remove this comment)
(script# NOTICE)
(include game.sh) (include "2.shm")
(use Main)
(use Window)
(use Game)
(use System)

(public
	noticeRoom 0
)

(instance noticeRoom of Room
	(properties
		noun N_ROOM
		picture pBlueSkyForCarpet
		style VSHUTTER
	)
	
	(method (init)
		(super init: &rest)
		(theGame setCursor: ARROW_CURSOR FALSE)
		(Bset fHideCursor)
		(self setScript: sayRights)
	)
)

(instance rightsWin of SysWindow)

(instance sayRights of Script
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(= cycles 2)
			)
			(1
				;EGA remnant
				(if (< numColors 8)
					(rightsWin color: vBLACK back: vWHITE)
				)
				(messager say: N_ROOM NULL C_SAYRIGHTS 0 self)
			)
			(2
				(curRoom newRoom: LOGOROOM)
			)
		)
	)
)
