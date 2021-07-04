;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9998)
(include game.sh)
(use Main)
(use Procs)
(use KQRoom)
(use Motion)
(use Actor)
(use System)

(public
	rm9998 0
)

(local
	local0
	local1
	sparkXY = [
		152 98
		152 18
		162 35
		68 70
		213 43
		108 107
		124 21
		71 89
		83 82
		203 68
		189 93
		198 85
		111 109
		201 81
		250 80
		227 128
		115 128
		168 66
		292 117
		307 78
		304 101
		530 43
		404 55
		441 48
		394 80
		370 93
		457 20
		481 28
		614 63
		628 74
		341 56
		596 116
		436 78
		503 52
		541 52
		523 34
		545 62
		494 68
		]
)
(instance rm9998 of KQRoom
	(properties
		picture 2600
		style SHOW_FADE_IN
		exitStyle SHOW_FADE_OUT
	)
	
	(method (init)
		(super init:)
		(spark1 init:)
		(spark2 init:)
		(spark3 init:)
		(spark4 init:)
		(spark5 init:)
		(spark6 init:)
		(spark7 init:)
		(spark8 init:)
		(self setScript: scroll)
	)
)

(instance scroll of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(curRoom setRect: 0 0 639 136)
				(UpdatePlane thePlane)
				(= seconds 4)
			)
			(1
				(= local0 0)
				(= local1 639)
				(while (>= (thePlane left:) -315)
					(= gameTime (+ tickOffset (GetTime)))
					(cast doit:)
					(if doMotionCue
						(= doMotionCue FALSE)
						(cast eachElementDo: #motionCue)
					)
					(if (theGame script?)
						((theGame script?) doit:)
					)
					(if cuees
						(cuees eachElementDo: #doit)
						(if (cuees isEmpty:) (cuees dispose:) (= cuees 0))
					)
					(regions eachElementDo: #doit)
					(thePlane setRect: local0 0 local1 136)
					(-= local0 3)
					(-= local1 3)
					(UpdatePlane thePlane)
					(FrameOut)
				)
				(= seconds 8)
			)
			(2
				(Bclr 21)
				(curRoom newRoom: 30)
			)
		)
	)
)

(instance spark1 of Prop
	(properties
		view 2601
		cel 1
	)
	
	(method (init &tmp i)
		(super init: &rest)
		(= i (* (Random 0 36) 2))
		(self
			cel: 0
			posn: [sparkXY i] [sparkXY (+ i 1)]
			setCycle: EndLoop self
		)
	)
	
	(method (cue)
		(self init:)
	)
)

(instance spark2 of Prop
	(properties
		view 2601
		cel 1
	)
	
	(method (init &tmp i)
		(super init: &rest)
		(= i (* (Random 0 36) 2))
		(self
			cel: 0
			posn: [sparkXY i] [sparkXY (+ i 1)]
			setCycle: EndLoop self
		)
	)
	
	(method (cue)
		(self init:)
	)
)

(instance spark3 of Prop
	(properties
		view 2601
		cel 1
	)
	
	(method (init &tmp i)
		(super init: &rest)
		(= i (* (Random 0 36) 2))
		(self
			cel: 0
			posn: [sparkXY i] [sparkXY (+ i 1)]
			setCycle: EndLoop self
		)
	)
	
	(method (cue)
		(self init:)
	)
)

(instance spark4 of Prop
	(properties
		view 2601
		cel 1
	)
	
	(method (init &tmp i)
		(super init: &rest)
		(= i (* (Random 0 36) 2))
		(self
			cel: 0
			posn: [sparkXY i] [sparkXY (+ i 1)]
			setCycle: EndLoop self
		)
	)
	
	(method (cue)
		(self init:)
	)
)

(instance spark5 of Prop
	(properties
		view 2601
		cel 1
	)
	
	(method (init &tmp i)
		(super init: &rest)
		(= i (* (Random 0 36) 2))
		(self
			cel: 0
			posn: [sparkXY i] [sparkXY (+ i 1)]
			setCycle: EndLoop self
		)
	)
	
	(method (cue)
		(self init:)
	)
)

(instance spark6 of Prop
	(properties
		view 2601
		cel 1
	)
	
	(method (init &tmp i)
		(super init: &rest)
		(= i (* (Random 0 36) 2))
		(self
			cel: 0
			posn: [sparkXY i] [sparkXY (+ i 1)]
			setCycle: EndLoop self
		)
	)
	
	(method (cue)
		(self init:)
	)
)

(instance spark7 of Prop
	(properties
		view 2601
		cel 1
	)
	
	(method (init &tmp i)
		(super init: &rest)
		(= i (* (Random 0 36) 2))
		(self
			cel: 0
			posn: [sparkXY i] [sparkXY (+ i 1)]
			setCycle: EndLoop self
		)
	)
	
	(method (cue)
		(self init:)
	)
)

(instance spark8 of Prop
	(properties
		view 2601
		cel 1
	)
	
	(method (init &tmp i)
		(super init: &rest)
		(= i (* (Random 0 36) 2))
		(self
			cel: 0
			posn: [sparkXY i] [sparkXY (+ i 1)]
			setCycle: EndLoop self
		)
	)
	
	(method (cue)
		(self init:)
	)
)
