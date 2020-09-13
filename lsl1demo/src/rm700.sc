;;; Sierra Script 1.0 - (do not remove this comment)
(script# 700)
(include game.sh)
(use Main)
(use LoadMany)
(use Game)
(use System)

(public
	rm700 0
)

(local
	local0
)
(instance rm700 of Room
	(properties
		picture pTitle1
		style IRISIN
	)
	
	(method (init)
		(super init:)
		(self setScript: sRoomScript)
	)
)

(instance sRoomScript of Script
	
	(method (doit)
		(super doit: &rest)
		(if (and (== state 2) (== (theMusic prevSignal?) -1))
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 10))
			(1
				(curRoom style: SCROLLUP drawPic: 180)
				(= seconds 3)
			)
			(2
				(SCIDisplay {Available at:} 150
					#back myTextColor
				)
				(SCIDisplay {swinging software stores everywhere!} 164
					#back myTextColor
				)
				(UnLoad VIEW pHoneymoonSuite)
				(UnLoad PICTURE pHoneymoonSuite)
				(UnLoad PICTURE pTitle1)
				(LoadMany PICTURE pOutsideBarAGI pOutsideBarSCI pBlack)
				(LoadMany VIEW pOutsideBarSCI pOutsideBarAGI)
				(Load FONT USERFONT)
				(Load FONT 312)
				(LoadMany SOUND 906 900 903 905 904 102)
			)
			(3
				(theMusic fade:)
				(curRoom newRoom: 100)
			)
		)
	)
)
