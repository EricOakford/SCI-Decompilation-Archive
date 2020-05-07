;;; Sierra Script 1.0 - (do not remove this comment)
(script# NOTICE) ;2
(include game.sh)
(use Main)
(use Intrface)
(use Save)
(use Game)
(use System)

(public
	noticeRoom 0
)

(instance noticeRoom of Room
	(properties
		picture pBlueSkyForCarpet
		style VSHUTTER
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: sayRights)
	)
)

(instance rightsWin of SysWindow
	(properties
		back vLCYAN
	)
)

(instance sayRights of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(if (< numColors 8) (rightsWin color: vBLACK back: vWHITE))
				
				(Print 2 0 #mode teJustCenter #width 300 #window rightsWin)
				(Print 2 1 #mode teJustCenter #width 300 #window rightsWin)
				(= cycles 1)
				; Oh, by the way . . .  You will need the information contained in the printed documentation to successfully complete this game.
;
; In other words, it's not just the law . . .
; It's a good idea.
			)
			(2 (curRoom newRoom: NOTICE2))	;and proceed to the game select screen
		)
	)
)
