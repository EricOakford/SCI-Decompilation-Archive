;;; Sierra Script 1.0 - (do not remove this comment)
(script# 295)
(include game.sh)
(use Main)
(use Intrface)
(use Save)
(use Motion)
(use Actor)
(use System)

(public
	byebye 0
)

(procedure (FairySays seconds &tmp [str 400])
	(Print
		&rest
		#at -1 12
		#width 100
		#mode teJustCenter
		#dispose
		#time seconds
		#window aFaeryWindow
	)
)

(instance aFaeryWindow of SysWindow
	(properties
		color vRED
		back vLCYAN
	)
)

(instance faery of Actor
	(properties
		view vFaery
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
				(if (< numColors 8) (aFaeryWindow color: vBLACK back: vWHITE))
				(switch (Random 0 3)
					(0
						(FairySays 4 295 0)
						;Take care!
						)
					(1
						(FairySays 4 295 1)
						;Say "Hi!" to the Dryad for us!
						)
					(2
						(FairySays 4 295 2)
						;Bye bye!  See ya!
						)
					(3
						(FairySays 4 295 3)
						;Kiss a tree for me!
						)
				)
				(faery
					setMotion: MoveTo (Random 120 190) (Random 50 110)
				)
				(= seconds 4)
			)
			(2
				(faery
					setMotion:
						MoveTo
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
				(aFaeryWindow dispose:)
				(faery dispose:)
				(= cycles 2)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)
