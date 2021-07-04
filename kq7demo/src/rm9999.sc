;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9999)
(include game.sh)
(use Main)
(use KQRoom)
(use System)

(public
	rm9999 0
)

(local
	local0
	local1
)
(instance rm9999 of KQRoom
	(properties
		picture 3150
		style SHOW_FADE_IN
		exitStyle SHOW_FADE_OUT
	)
	
	(method (init)
		(super init:)
		(self setScript: scroll)
	)
)

(instance scroll of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(curRoom setRect: -319 0 319 136)
				(UpdatePlane thePlane)
				(= seconds 4)
			)
			(1
				(= local0 -319)
				(= local1 319)
				(while (<= (thePlane left:) -5)
					(thePlane setRect: local0 0 local1 136)
					(+= local0 3)
					(+= local1 3)
					(UpdatePlane thePlane)
					(FrameOut)
				)
				(= seconds 4)
			)
			(2
				(curRoom newRoom: 9998)
			)
		)
	)
)
