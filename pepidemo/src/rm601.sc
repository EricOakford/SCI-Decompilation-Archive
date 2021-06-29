;;; Sierra Script 1.0 - (do not remove this comment)
(script# 601)
(include game.sh) (include "601.shm")
(use Main)
(use TWRoom)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm601 0
)

(local
	local0 =  1
)
(instance rm601 of ADRoom
	(properties
		horizon 75
		vanishingY -60
	)
	
	(method (init)
		(switch gameAct
			(1 (self picture: 200))
			(2 (self picture: 200))
			(3 (self picture: 345))
			(4 (self picture: 350))
			(5 (self picture: 530))
			(6 (self picture: 380))
		)
		(super init: &rest)
		(theIconBar disable:)
		(if (== prevRoomNum 600) (= local0 0))
		(curRoom setScript: sPickAct1)
	)
	
	(method (newRoom n)
		(narrator keepWindow: 0)
		(Bclr fCantSave)
		(Bclr 77)
		(theIconBar enable:)
		(= normalCursor ((theIconBar at: 0) cursor?))
		(super newRoom: n)
	)
)

(instance sWhat2Look4 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom drawPic: 99 10)
				(cast eachElementDo: #dispose)
				(= cycles 1)
			)
			(1
				(narrator keepWindow: 1)
				(messager say: 7 0 gameAct 2 self 110)
			)
			(2
				(if local0
					(switch gameAct
						(2
							(messager say: 7 0 gameAct 3 5 self 110)
						)
						(3
							(messager say: 7 0 gameAct 3 6 self 110)
						)
						(4
							(messager say: 7 0 gameAct 3 7 self 110)
						)
						(5
							(messager say: 7 0 gameAct 3 8 self 110)
						)
						(6
							(messager say: 7 0 gameAct 3 11 self 110)
						)
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance sPickAct1 of Script

	(method (doit &tmp sFXPrevSignal)
		(cond 
			(
				(and
					(>= (= sFXPrevSignal (sFX prevSignal?)) 10)
					(== state 1)
				)
				(self cue:)
			)
			((and (>= sFXPrevSignal 20) (== state 2)) (self cue:))
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp [temp0 601])
		(switch (= state newState)
			(0
				(curRoom drawPic: 200)
				(fallView init:)
				(crash init: hide:)
				(Load RES_VIEW 835 837)
				(theGame setCursor: INVIS_CURSOR TRUE 100 100)
				(= cycles 1)
			)
			(1
				(sFX number: 942 play:)
				(actLetters init: addToPic:)
				(actNum init: addToPic:)
			)
			(2 (shortMsg init: stopUpd:))
			(3
				(littleOr init: stopUpd:)
				(= seconds 3)
			)
			(4
				(sFX number: 943 play:)
				(fallView
					setLoop: 0
					setStep: 1 15
					setMotion: MoveTo 150 132 self
				)
			)
			(5
				(crash addToPic:)
				(sFX stop:)
				(= cycles 1)
			)
			(6
				(fallView setMotion: MoveTo 150 132 self)
			)
			(7
				(sFX number: 1209 play: self)
				(ShakeScreen 5 2)
			)
			(8
				(sFX number: 945 loop: 1 play: self)
			)
			(9 (= seconds 4))
			(10
				(self setScript: sWhat2Look4 self)
			)
			(11 (= cycles 4))
			(12 (curRoom newRoom: 120))
		)
	)
)

(instance actLetters of View
	(properties
		x 72
		y 10
		view 836
		signal ignrAct
	)
)

(instance actNum of View
	(properties
		x 204
		y 9
		view 836
		cel 1
		signal ignrAct
	)
)

(instance shortMsg of Prop
	(properties
		x 59
		y 50
		view 836
		loop 2
		priority 10
		signal (| ignrAct fixPriOn stopUpdOn)
	)
)

(instance littleOr of View
	(properties
		x 128
		y 78
		view 836
		loop 1
		priority 10
		signal (| ignrAct fixPriOn stopUpdOn)
	)
)

(instance fallView of Actor
	(properties
		x 150
		y -35
		view 835
		priority 15
		signal (| ignrAct ignrHrz fixPriOn)
		illegalBits $0000
	)
)

(instance crash of View
	(properties
		x 150
		y 130
		view 837
		signal scalable
	)
)

(instance kooky of Actor
	(properties
		x -84
		y 124
		view 835
		loop 1
		priority 15
		signal fixPriOn
	)
)

(instance zap of Prop
	(properties
		x 92
		y 135
		view 850
	)
)

(instance lightning of Prop
	(properties
		x 190
		y 15
		view 346
		scaleSignal scalable
	)
)

(instance banner of Prop
	(properties
		x 52
		y 108
		view 851
		priority 15
		signal fixPriOn
	)
)

(instance act5View of View
	(properties
		x 142
		y 113
		view 852
		priority 15
		signal fixPriOn
	)
)

(instance act5Txt of Prop
	(properties
		x 142
		y 113
		view 852
		loop 1
		priority 15
		signal fixPriOn
		cycleSpeed 1
	)
)

(instance frontDoors of View
	(properties
		x 17
		y 147
		view 396
		signal stopUpdOn
	)
)

(instance act6L1 of View
	(properties
		x 147
		y 125
		view 835
		loop 2
		priority 15
		signal fixPriOn
	)
)

(instance act6L2 of View
	(properties
		x 147
		y 125
		view 835
		loop 3
		priority 15
		signal fixPriOn
	)
)

(instance sFX of Sound
	(properties
		flags mNOPAUSE
	)
)
