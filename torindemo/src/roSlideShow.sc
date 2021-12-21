;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64034)
(include sci.sh)
(use Main)
(use TPRoom)
(use GenDialog)
(use Script)
(use ModalPlane)
(use Events)
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
				(LOOKUP_ERROR init: 0 0 lastScreenX lastScreenY)
				(LOOKUP_ERROR init: 4 82 635 397)
				(= ticks 360)
				(LOOKUP_ERROR picture: -14136)
				(LOOKUP_ERROR drawPic: -14136)
				(if (= temp0 (MakeMessageText 0 0 7 1 300))
					(TextDialog temp0 continueText)
					(temp0 dispose:)
					(= temp0 0)
				)
				(SetCursor 316 470)
				(gOEventHandler scriptId: LOOKUP_ERROR)
			)
			(1
				(= ticks 360)
				(LOOKUP_ERROR moveTo: 4 (Random 10 153))
				(LOOKUP_ERROR picture: 13002)
				(LOOKUP_ERROR drawPic: 13002)
			)
			(2
				(= ticks 360)
				(LOOKUP_ERROR moveTo: 4 (Random 10 153))
				(LOOKUP_ERROR picture: -24936)
				(LOOKUP_ERROR drawPic: -24936)
			)
			(3
				(= ticks 360)
				(LOOKUP_ERROR moveTo: 4 (Random 10 153))
				(LOOKUP_ERROR picture: -14836)
				(LOOKUP_ERROR drawPic: -14836)
			)
			(4
				(= ticks 360)
				(LOOKUP_ERROR moveTo: 4 (Random 10 153))
				(LOOKUP_ERROR picture: 30300)
				(LOOKUP_ERROR drawPic: 30300)
			)
			(5
				(= ticks 360)
				(LOOKUP_ERROR moveTo: 4 (Random 10 153))
				(LOOKUP_ERROR picture: -23336)
				(LOOKUP_ERROR drawPic: -23336)
			)
			(6
				(= ticks 360)
				(LOOKUP_ERROR moveTo: 4 (Random 10 153))
				(LOOKUP_ERROR picture: -15035)
				(LOOKUP_ERROR drawPic: -15035)
			)
			(7
				(= ticks 360)
				(LOOKUP_ERROR moveTo: 4 (Random 10 153))
				(LOOKUP_ERROR picture: 15000)
				(LOOKUP_ERROR drawPic: 15000)
			)
			(8
				(= ticks 360)
				(LOOKUP_ERROR moveTo: 4 (Random 10 153))
				(LOOKUP_ERROR picture: -24836)
				(LOOKUP_ERROR drawPic: -24836)
			)
			(9
				(= ticks 360)
				(LOOKUP_ERROR moveTo: 4 (Random 10 153))
				(LOOKUP_ERROR picture: 16000)
				(LOOKUP_ERROR drawPic: 16000)
			)
			(10
				(gOEventHandler bHasFF: LOOKUP_ERROR)
				(if (= temp0 (MakeMessageText 0 0 8 1 300))
					(TextDialog temp0 continueText)
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
		(theMusic pageSize: -5520)
		(curRoom setScript: LOOKUP_ERROR)
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
			(gOEventHandler bHasFF: LOOKUP_ERROR)
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
			(LOOKUP_ERROR ticks: 0 cue:)
			(return 1)
		)
		(return 0)
	)
)
