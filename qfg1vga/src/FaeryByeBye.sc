;;; Sierra Script 1.0 - (do not remove this comment)
(script# 295)
(include game.sh) (include "295.shm")
(use Main)
(use Motion)
(use Actor)
(use System)

(public
	byebye 0
)

(instance faery of Actor
	(properties
		view 70
	)
)

(instance byebye of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 295)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(faery
					ignoreActors:
					ignoreHorizon:
					illegalBits: 0
					setLoop: 4
					cel: 0
					setPri: 12
					posn:
						(switch curRoomNum
							(62 (Random 120 200))
							(69 330)
							(71 -10)
							(77 (Random 120 200))
						)
						(switch curRoomNum
							(62 200)
							(69 (Random 50 120))
							(71 (Random 50 120))
							(77 30)
						)
					setStep: 8 5
					init:
					setCycle: Forward
					setMotion: MoveTo (Random 140 180) (Random 55 105) self
				)
			)
			(1
				(switch (Random 0 3)
					(0
						(messager say: N_BYEBYE NULL NULL 1 self 295)
					)
					(1
						(messager say: N_BYEBYE NULL NULL 2 self 295)
					)
					(2
						(messager say: N_BYEBYE NULL NULL 3 self 295)
					)
					(3
						(messager say: N_BYEBYE NULL NULL 4 self 295)
					)
				)
				(faery
					setMotion: MoveTo (Random 120 190) (Random 50 110)
				)
			)
			(2
				(HandsOff)
				(faery
					setMotion: MoveTo
						(switch curRoomNum
							(62 (Random 120 200))
							(69 330)
							(71 -10)
							(77 (Random 120 200))
						)
						(switch curRoomNum
							(62 200)
							(69 (Random 50 120))
							(71 (Random 50 120))
							(77 30)
						)
						self
				)
			)
			(3
				(faery hide:)
				(= ticks 18)
			)
			(4
				(faery dispose: delete:)
				(= ticks 18)
			)
			(5
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
