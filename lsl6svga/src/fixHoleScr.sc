;;; Sierra Script 1.0 - (do not remove this comment)
(script# 333)
(include sci.sh)
(use Main)
(use fileScr)
(use Sound)
(use Motion)
(use System)

(public
	fixHoleScr 0
	wrenchTankScr 1
	operateTankLidScr 2
	takeOrPutFilterScr 3
	greasePistonScr 4
)

(local
	theRegister
)
(instance fixHoleScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(messager say: 6 11 0 0 self)
			)
			(2
				(theGame changeScore: 7 182)
				(ego
					view: 331
					loop: 3
					cel: 0
					setSpeed: 10
					posn: 214 98
					setCycle: CT 9 1 self
				)
			)
			(3 (= cycles 2))
			(4
				((ScriptID 330 9) cue:)
				(ego put: 4 normalize: 900 8 cel: 6)
				(= cycles 2)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance wrenchTankScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(ego view: 3311 loop: 2 cel: 0 setSpeed: 8 setCycle: 0)
				(= register 6)
				(= cycles 2)
			)
			(2
				(theMusic2 number: 404 loop: 1 play:)
				(ego cel: 0 setCycle: End self)
			)
			(3
				(if (-- register) (= state (- state 2)))
				(= ticks 10)
			)
			(4
				(theMusic2 stop:)
				(ego normalize: 900 8 cel: 7)
				(= cycles 2)
			)
			(5
				(if (not (Btst 29))
					(Bset 29)
					(= register 1)
					(theGame changeScore: 5 178)
				else
					(Bclr 29)
					(= register 0)
					(if (Btst 180) (theGame changeScore: 3 181))
				)
				(messager say: 8 64 (if register 7 else 17) 0 self)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance operateTankLidScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(theMusic2 loop: 1 number: 366 play:)
				(ego view: 3311 loop: 1 setSpeed: 13)
				((ScriptID 330 11) view: 3311 loop: 0)
				(if (= register (Btst 30))
					(ego cel: 3 setCycle: Beg self)
					((ScriptID 330 11)
						cel: 2
						setCycle: Beg (ScriptID 330 11)
					)
					(Bclr 30)
				else
					(if (== ((inventory at: 13) owner?) curRoomNum)
						((ScriptID 330 10) init:)
					)
					(ego cel: 0 setCycle: End self)
					((ScriptID 330 11) cel: 0 setCycle: End self)
					(Bset 30)
				)
			)
			(2 0)
			(3
				(if
					(and
						register
						(== ((inventory at: 13) owner?) curRoomNum)
					)
					((ScriptID 330 10) dispose:)
				)
				(ego normalize: 900 8 cel: 7)
				(self dispose:)
				(theGame handsOn:)
			)
		)
	)
)

(instance greasePistonScr of Script
	(properties)
	
	(method (dispose)
		(sounds delete: (sfx dispose: yourself:))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setSpeed: 6 setMotion: MoveTo 45 121 self)
				(= theRegister register)
				(= register 3)
			)
			(1
				(ego
					view: 331
					loop: 5
					cel: 0
					cycleSpeed: 10
					setCycle: End self
				)
				(= ticks 20)
			)
			(2 (sfx play:))
			(3
				(if (-- register) (= state (- state 3)))
				(= ticks 1)
			)
			(4
				((ScriptID 330 8) view: 336 loop: 2)
				(if (== theRegister 30)
					(ego put: 24)
				else
					((inventory at: 23) cue: 1)
					(inventory show:)
				)
				(= register 3)
				(= ticks 1)
			)
			(5
				(ego
					view: 331
					loop: 5
					cel: 0
					cycleSpeed: 10
					setCycle: End self
				)
				(= ticks 20)
			)
			(6 (sfx play:))
			(7
				(if (-- register) (= state (- state 3)))
				(= ticks 1)
			)
			(8
				(ego normalize: 900 8 cel: 3)
				(= cycles 2)
			)
			(9
				(theGame changeScore: 6 177)
				(= cycles 2)
			)
			(10
				(ego
					setSpeed: 6
					setMotion:
						MoveTo
						((ScriptID 330 8) approachX?)
						((ScriptID 330 8) approachY?)
						self
				)
			)
			(11 (= cycles 2))
			(12
				(messager say: 13 theRegister 0 0 self)
			)
			(13
				(ego setSpeed: gGEgoCycleSpeed_2)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance takeOrPutFilterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(theGame changeScore: 4 179)
				else
					(theGame changeScore: 4 180)
				)
				(= cycles 2)
			)
			(1
				(ego view: 3311 loop: 1 cel: 0 setCycle: 0)
				(= ticks 20)
			)
			(2
				(if register
					(ego get: 13)
					((ScriptID 330 10) dispose:)
				else
					(ego put: 13 curRoomNum)
					((ScriptID 330 10) init:)
				)
				(= ticks 20)
			)
			(3
				(ego normalize: 900 8 cel: 7)
				(= cycles 2)
			)
			(4
				(if register
					(messager say: 7 5 0 0 self)
				else
					(= cycles 2)
				)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sfx of Sound
	(properties
		flags $0001
		number 632
	)
)
