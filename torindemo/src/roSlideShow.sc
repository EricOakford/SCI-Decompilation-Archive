;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64034)
(include sci.sh)
(use Main)
(use TPRoom)
(use GenDialg)
(use Script)
(use ModalPlane)
(use NewUser)
(use Plane)

(public
	roSlideShow 0
)

(instance oBackPlane of ModalPlane
	(properties
		priority 1100
		back 234
	)
)

(instance oShowPlane of Plane
	(properties
		priority 1200
	)
)

(instance soShow of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOn:)
				(oBackPlane init: 0 0 lastScreenX lastScreenY)
				(oShowPlane init: 4 82 635 397)
				(= ticks 360)
				(oShowPlane picture: -14136)
				(oShowPlane drawPic: -14136)
				(if (= temp0 (MakeMessageText 0 0 7 1 300))
					(TextDialog temp0 global288)
					(temp0 dispose:)
					(= temp0 0)
				)
				(SetCursor 316 470)
				(gOEventHandler scriptId: oOurQuitHandler)
			)
			(1
				(= ticks 360)
				(oShowPlane moveTo: 4 (Random 10 153))
				(oShowPlane picture: 13002)
				(oShowPlane drawPic: 13002)
			)
			(2
				(= ticks 360)
				(oShowPlane moveTo: 4 (Random 10 153))
				(oShowPlane picture: -24936)
				(oShowPlane drawPic: -24936)
			)
			(3
				(= ticks 360)
				(oShowPlane moveTo: 4 (Random 10 153))
				(oShowPlane picture: -14836)
				(oShowPlane drawPic: -14836)
			)
			(4
				(= ticks 360)
				(oShowPlane moveTo: 4 (Random 10 153))
				(oShowPlane picture: 30300)
				(oShowPlane drawPic: 30300)
			)
			(5
				(= ticks 360)
				(oShowPlane moveTo: 4 (Random 10 153))
				(oShowPlane picture: -23336)
				(oShowPlane drawPic: -23336)
			)
			(6
				(= ticks 360)
				(oShowPlane moveTo: 4 (Random 10 153))
				(oShowPlane picture: -15035)
				(oShowPlane drawPic: -15035)
			)
			(7
				(= ticks 360)
				(oShowPlane moveTo: 4 (Random 10 153))
				(oShowPlane picture: 15000)
				(oShowPlane drawPic: 15000)
			)
			(8
				(= ticks 360)
				(oShowPlane moveTo: 4 (Random 10 153))
				(oShowPlane picture: -24836)
				(oShowPlane drawPic: -24836)
			)
			(9
				(= ticks 360)
				(oShowPlane moveTo: 4 (Random 10 153))
				(oShowPlane picture: 16000)
				(oShowPlane drawPic: 16000)
			)
			(10
				(gOEventHandler bHasFF: oOurQuitHandler)
				(if (= temp0 (MakeMessageText 0 0 8 1 300))
					(TextDialog temp0 global288)
					(temp0 dispose:)
					(= temp0 0)
				)
				(= quit 1)
			)
		)
	)
)

(instance roSlideShow of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: -5520)
		(curRoom setScript: soShow)
	)
)

(instance oOurQuitHandler of EventCode
	(properties)
	
	(method (handleEvent event &tmp temp0 temp1)
		(if
			(and
				(& (event type?) $000c)
				(== (event message?) KEY_ESCAPE)
			)
			(gOEventHandler bHasFF: oOurQuitHandler)
			(= temp0 (MakeMessageText 0 0 8 1 300))
			(= temp1 (MakeMessageText 0 0 8 1 0))
			(if temp0
				(TextDialog temp0 temp1)
				(temp0 dispose:)
				(temp1 dispose:)
				(= temp0 0)
			)
			(= quit 1)
			(return 1)
		)
		(if (== (event type?) evMOUSEBUTTON)
			(soShow ticks: 0 cue:)
			(return 1)
		)
		(return 0)
	)
)
