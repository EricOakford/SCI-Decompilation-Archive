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
		picture 170
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
			(0
				(= cycles 10)
			)
			(1
				(curRoom style: 42 drawPic: 180)
				(= seconds 3)
			)
			(2
				(DoDisplay3 {Available at:} 150 29 colBlack)
				(DoDisplay3 {swinging software stores everywhere!}
					164 29 colBlack
				)
				(UnLoad PICTURE 160)
				(UnLoad PICTURE 160)
				(UnLoad PICTURE 170)
				(LoadMany PICTURE 100 110 120)
				(LoadMany VIEW 110 100)
				(Load FONT 1)
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
