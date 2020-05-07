;;; Sierra Script 1.0 - (do not remove this comment)
(script# 150)
(include sci.sh)
(use Main)
(use NewRoomCue)
(use Conv)
(use Motion)
(use Actor)
(use System)

(public
	rm150 0
	genie 1
)

(local
	local0
	local1
	local2
)
(instance rm150 of KQ6Room
	(properties
		picture 150
		style $0006
	)
	
	(method (init)
		(theIconBar disable: 6)
		(super init: &rest)
		(theGame givePoints: 2)
		(self setScript: roomScr)
		(swordArm init:)
		(theMusic number: 150 loop: -1 play: self)
	)
	
	(method (dispose)
		(theIconBar enable: 6)
		(super dispose:)
	)
	
	(method (cue)
		(curRoom newRoom: 220)
	)
)

(instance roomConv of Conversation
	(properties)
)

(instance genieHeadScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (genie init:) (= cycles 2))
			(1
				(= start (+ state 1))
				(self dispose:)
			)
			(2
				(genie hide:)
				(if (and (- (Random 0 3) 1) (not local1))
					(= local1 1)
					(= local2 1)
					(if (Random 0 1)
						(eye1 init: cel: 0 setCycle: End self)
						(= local0 1)
					else
						(eye2 init: cel: 0 setCycle: End self)
						(= local0 0)
					)
				else
					(= local1 0)
					(= state (+ state 2))
					(= cycles 2)
				)
			)
			(3 (= cycles 2))
			(4
				(if local0 (eye1 dispose:) else (eye2 dispose:))
				(= cycles 2)
			)
			(5
				(= start (+ state 1))
				(self dispose:)
			)
			(6 (genie show:) (= cycles 2))
			(7
				(= start (- state 5))
				(self dispose:)
			)
		)
	)
)

(instance genie of Prop
	(properties
		x 161
		y 82
		view 150
	)
)

(instance swordArm of Prop
	(properties
		x 291
		y 120
		view 150
		loop 7
		signal $0001
	)
)

(instance eye1 of Prop
	(properties
		x 166
		y 71
		view 902
	)
)

(instance eye2 of Prop
	(properties
		x 159
		y 71
		view 902
	)
)

(instance saladineyes of Prop
	(properties
		x 293
		y 4
		view 150
		loop 10
		cel 2
	)
)

(instance roomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(roomConv
					add: -1 1 0 1 1
					add: genieHeadScr
					add: -1 1 0 1 2
					add: genieHeadScr
					add: -1 1 0 1 3
					add: genieHeadScr
					add: -1 1 0 1 4
					add: genieHeadScr
					add: -1 1 0 1 5
					add: genieHeadScr
					add: -1 1 0 1 6
					add: genieHeadScr
					add: -1 1 0 1 7
					init: self
				)
			)
			(2
				(roomConv
					add: genieHeadScr
					add: -1 1 0 1 8
					add: -1 1 0 1 9
					add: -1 1 0 1 10
					add: -1 1 0 1 11
					add: genieHeadScr
					add: -1 1 0 1 12
					add: genieHeadScr
					add: -1 1 0 1 13
					add: genieHeadScr
					add: -1 1 0 1 14
					add: genieHeadScr
					add: -1 1 0 1 15
					add: -1 1 0 1 16
					add: genieHeadScr
					init: self
				)
			)
			(3
				(if (not local2)
					(if (cast contains: genie) (genie dispose:))
					(eye1 init: cel: 0 setCycle: End self)
				else
					(++ state)
					(= cycles 1)
				)
			)
			(4
				(eye1 dispose:)
				(= cycles 2)
			)
			(5
				(soundFx2 number: 756 loop: 1 play:)
				(swordArm setCycle: End)
				(theMusic fade:)
			)
		)
	)
)
