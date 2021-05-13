;;; Sierra Script 1.0 - (do not remove this comment)
(script# 456)
(include sci.sh)
(use Main)
(use Feature)
(use Game)

(public
	magRosetta 0
)

(instance magRosetta of Rm
	(properties
		picture 780
	)
	
	(method (init)
		(if (timers contains: (ScriptID 90 15))
			((ScriptID 90 15) client: self)
		)
		(self setRegions: 90)
		(super init:)
		(bigRosetta init:)
	)
	
	(method (cue)
		((ScriptID 90 15) setReal: self 10 0 0 global125)
	)
	
	(method (newRoom)
		(if (timers contains: (ScriptID 90 15))
			(= timer 0)
			((ScriptID 90 15) client: (ScriptID 90 15))
		)
		(theMusic2 fade: 127 20 20 0)
		(super newRoom: &rest)
	)
)

(instance bigRosetta of Feature
	(properties
		y 190
		nsTop 50
		nsLeft 50
		nsBottom 145
		nsRight 210
	)
	
	(method (init)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(theMusic2 fade: 80 20 20 0)
		(theIconBar disable: 7)
		(super init: &rest)
		(theIconBar curIcon: (theIconBar at: 1) disable:)
		(if (== prevRoomNum 454)
			(DrawPic 455 dpOPEN_NO_TRANSITION)
		else
			(DrawPic 521 dpOPEN_NO_TRANSITION)
		)
		(theGame setCursor: 996)
		(if (== prevRoomNum 454)
			(SetCursor 2 79 55 239 144 86 0 0 456 53)
		else
			(SetCursor 2 79 55 239 144 86 0 0 527 53)
		)
		(Animate (cast elements?) 0)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
		(theIconBar enable: 7)
		(curRoom newRoom: prevRoomNum)
	)
	
	(method (handleEvent event)
		(if
			(or
				(== (event type?) evMOUSEBUTTON)
				(== (event type?) evKEYBOARD)
			)
			(SetCursor -1)
			(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
			(event claimed: 1)
			(self dispose:)
			(theIconBar enable:)
		)
	)
)
