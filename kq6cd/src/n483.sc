;;; Sierra Script 1.0 - (do not remove this comment)
(script# 483)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use Conv)
(use PolyPath)
(use Motion)
(use System)

(public
	proc483_0 0
	proc483_1 1
	proc483_2 2
	proc483_3 3
	proc483_4 4
)

(procedure (proc483_0 param1)
	((ScriptID 480 5) register: 1)
	(ego setScript: getBottle 0 param1)
)

(procedure (proc483_1)
	((ScriptID 480 5) register: 1)
	(ego setScript: getGrapes)
)

(procedure (proc483_2 param1)
	((ScriptID 480 5) register: 1)
	(ego setScript: getTomato 0 param1)
)

(procedure (proc483_3 param1)
	((ScriptID 480 5) register: 1)
	(ego setScript: pickLettuce 0 param1)
)

(procedure (proc483_4 param1)
	((ScriptID 480 5) register: 1)
	(ego setScript: chokeDie 0 param1)
)

(instance myConv of Conversation
	(properties)
)

(instance getGrapes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 12 5 0 1 self 480)
			)
			(1
				(ego setMotion: PolyPath 16 95 self)
			)
			(2
				(ego setHeading: 0)
				(= ticks 6)
			)
			(3
				(myConv add: 480 12 5 0 2 add: 480 12 5 0 3 init: self)
			)
			(4
				(theGame handsOn:)
				(ego setHeading: 180)
				(self dispose:)
				(DisposeScript 483)
			)
		)
	)
)

(instance getTomato of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 170 157 self)
			)
			(1
				(ego setPri: 12 setMotion: PolyPath 154 157 self)
			)
			(2
				(theGame givePoints: 1)
				(ego
					view: 481
					setLoop: 3
					cel: 0
					posn: 151 157
					normal: 0
					cycleSpeed: 12
					setCycle: CT 2 1 self
				)
			)
			(3
				(messager say: 6 5 0 1 self 480)
			)
			(4
				(register dispose:)
				(if global169 (DrawPic 480 15) else (DrawPic 480))
				((ScriptID 480 7) cel: 0 init: addToPic:)
				(ego setCycle: End self)
			)
			(5
				(messager say: 6 5 0 2 self 480)
			)
			(6
				(theGame handsOn:)
				(ego posn: 154 157 reset: 7)
				(self dispose:)
				(DisposeScript 483)
			)
		)
	)
)

(instance pickLettuce of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not (Btst 119))
					(Bset 119)
					(theGame givePoints: 1)
				)
				(if (ego has: 21)
					(messager say: 10 5 31 1 self 480)
				else
					(messager say: 10 5 0 1 self 480)
				)
			)
			(1
				(ego setMotion: PolyPath 180 133 self)
			)
			(2
				(ego setHeading: 335)
				(= ticks 6)
			)
			(3
				(ego
					view: 481
					posn: 170 136
					setLoop: 2
					cel: 0
					normal: 0
					cycleSpeed: 12
					get: 21
					setCycle: End self
				)
			)
			(4
				(messager say: 10 5 0 2 self 480)
			)
			(5
				(theGame handsOn:)
				(ego posn: 180 133 reset: 7)
				((inventory at: 21)
					state: 0
					noun: 20
					message: 52
					cel: 4
					setCursor: 990 1 4
				)
				((ScriptID 0 7) setReal: (inventory at: 21) 30 2)
				(self dispose:)
				(DisposeScript 483)
			)
		)
	)
)

(instance chokeDie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== register 5)
					(messager say: 13 5 0 1 self 480)
				else
					(messager say: 13 3 0 1 self 480)
				)
			)
			(1
				(if (== register 5)
					(ego setMotion: PolyPath 60 98 self)
				else
					(self cue:)
				)
			)
			(2
				(ego setHeading: 0)
				(= cycles 8)
			)
			(3
				(ego normal: 0 view: 486 setLoop: 0 cel: 0)
				(theMusic number: 487 setLoop: 1 play:)
				(= cycles 6)
			)
			(4
				(messager say: 13 5 0 2 self 480)
			)
			(5
				(ego cycleSpeed: 12 setCycle: CT 3 1 self)
			)
			(6
				(myConv
					add: 480 13 5 0 3
					add: 480 13 5 0 4
					add: 480 13 5 0 5
					add: 480 13 5 0 6
					init: self
				)
			)
			(7 (ego cel: 4) (= ticks 6))
			(8
				(messager say: 13 5 0 7 self 480)
			)
			(9 (ego setCycle: End self))
			(10
				(messager say: 13 5 0 8 self 480)
			)
			(11
				(messager say: 13 5 0 9 self 480)
			)
			(12 (EgoDead 22))
		)
	)
)

(instance getBottle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== register 0)
					(ego
						ignoreActors: 1
						illegalBits: 0
						setMotion: PolyPath 215 155 self
					)
				else
					(ego
						ignoreActors: 1
						illegalBits: 0
						setMotion: PolyPath 286 79 self
					)
				)
			)
			(1
				(if (== register 1)
					(ego
						normal: 0
						view: 488
						setLoop: 0
						cel: 0
						cycleSpeed: 12
						setCycle: CT 2 1 self
						posn: 292 78
					)
				else
					(ego
						normal: 0
						view: 4811
						setLoop: 2
						cel: 0
						cycleSpeed: 12
						setCycle: CT 1 1 self
						posn: 227 161
					)
				)
			)
			(2
				(if (not register)
					((ScriptID 480 3) dispose:)
				else
					((ScriptID 480 4) dispose:)
				)
				(ego setCycle: End self)
			)
			(3
				(if (== register 1)
					(messager say: 22 5 0 1 self 480)
				else
					(messager say: 8 5 0 1 self 480)
				)
			)
			(4
				(if (== register 1)
					(ego reset: 0 setMotion: PolyPath 197 116 self get: 46)
				else
					(ego
						posn: (- (ego x?) 15) (- (ego y?) 6)
						get: 33
						reset: 0
					)
					(= cycles 2)
				)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
				(DisposeScript 483)
			)
		)
	)
)
