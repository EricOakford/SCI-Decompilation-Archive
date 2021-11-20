;;; Sierra Script 1.0 - (do not remove this comment)
(script# 37)
(include sci.sh)
(use Main)
(use Array)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	castFetchScript 0
)

(local
	gTheObj_2Cel
	gTheObj_2Loop
	newActor
	local3
	local4
	local5
	local6
	local7
)
(instance castFetchScript of Script
	(properties)
	
	(method (init theClient &tmp temp0 temp1)
		(= local3 (IntArray with: 30 -30 20 -20))
		(= local4 (IntArray with: -35 -35 -40 -40))
		(= local5 (IntArray with: 0 1 0 2 0 1 2 3))
		(= local6 (IntArray with: 0 1 1 2 0 1 2 3))
		(= local7 (IntArray with: 2 3 6 7))
		(= temp1 0)
		(AutoTarget
			((User curEvent?) x?)
			((User curEvent?) y?)
		)
		(if (>= argc 2)
			(= register myCollection)
			(= temp0 1)
			(while (< temp0 argc)
				(if [theClient temp0]
					(= temp1 1)
					(register add: [theClient temp0])
				)
				(++ temp0)
			)
		)
		(if (not temp1)
			(= register 0)
			(super init: theClient &rest)
		else
			(= lastTicks gameTime)
			(if (>= argc 1)
				((= client [theClient 0]) script: self)
				(if (>= argc 2) (= caller [theClient 1]))
			)
			(= state (- start 1))
			(self cue:)
		)
	)
	
	(method (dispose)
		(local3 dispose:)
		(local4 dispose:)
		(local5 dispose:)
		(local6 dispose:)
		(local7 dispose:)
		(if
		(and register (!= curRoomNum 800) (not (Btst 353)))
			(register dispose:)
		)
		(super dispose:)
	)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: 0)
				(= cycles 5)
				(= gTheObj_2Cel (ego cel?))
			)
			(1
				(ego
					setHeading: (GetAngle (ego x?) (ego y?) projX projY) self
				)
			)
			(2
				(= gTheObj_2Loop (ego loop?))
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(ego
						view: 18
						loop: (local6 at: (ego loop?))
						cel: 0
						setCycle: CT 6 1 self
					)
				else
					(ego
						view: 14
						loop: (local5 at: (ego loop?))
						setCycle: End self
					)
				)
			)
			(3
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(ego setCycle: CT 0 1)
				)
				((= newActor (Actor new:))
					signal: 24577
					view: 21
					setLoop: 7 1
					x:
						(+
							(ego x?)
							(/ (* (ego maxScale?) (local3 at: (ego loop?))) 128)
						)
					y:
						(+
							(ego y?)
							(/ (* (ego maxScale?) (local4 at: (ego loop?))) 128)
						)
					setStep: 12 12
					illegalBits: 0
					init:
					setCycle: Fwd
					setMotion: MoveTo projX projY self
					setPri: 180
				)
				(if (ego scaler?) (newActor setScaler: ego))
				(soundFX number: 934 play:)
			)
			(4
				(if register
					(= temp0 0)
					(if (= temp0 (register firstTrue: #onMe newActor))
						(temp0 doVerb: -87)
						(= cycles 5)
					else
						(messager say: 1 6 1 0 self 37)
					)
				else
					(messager say: 1 6 1 0 self 37)
				)
			)
			(5
				(newActor
					setMotion:
						MoveTo
						(+
							(ego x?)
							(/ (* (ego maxScale?) (local3 at: (ego loop?))) 128)
						)
						(+
							(ego y?)
							(/ (* (ego maxScale?) (local4 at: (ego loop?))) 128)
						)
						self
				)
			)
			(6
				(newActor hide:)
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(self cue:)
				else
					(ego setCycle: Beg self)
				)
			)
			(7
				(newActor dispose:)
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(ego
						view: 20
						cel: gTheObj_2Cel
						loop: (local7 at: (ego loop?))
					)
				else
					(ego normalize: gTheObj_2Loop)
				)
				(= cycles 2)
			)
			(8
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(ego cel: (ego lastCel:))
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soundFX of Sound
	(properties)
)

(instance myCollection of Collect
	(properties)
	
	(method (dispose)
		(if elements (DisposeList elements))
		(= size (= elements 0))
		(DisposeClone self)
	)
)
