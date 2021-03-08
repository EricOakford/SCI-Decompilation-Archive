;;; Sierra Script 1.0 - (do not remove this comment)
(script# 202)
(include sci.sh)
(use Main)
(use sStopEggTimer)
(use eureka)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	proc202_1 1
	panel 2
	sPushButtons 6
	monitor1 13
	monitor2 14
	monitor3 15
)

(local
	[local0 3] = [3 4 5]
)
(procedure (proc202_1)
	(panel cel: (panel lastCel:) setCycle: Beg panel)
	(panelNoise number: 217 loop: 1 play:)
)

(instance buttonNoise of Sound
	(properties)
)

(instance panelNoise of Sound
	(properties)
)

(instance sPushButtons of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(rogArm init:)
				(rogHand
					init:
					x: (if (== register 2) 202 else 200)
					y: (if (== register 2) 142 else 144)
					cel: 0
					setLoop: [local0 register]
					cycleSpeed: 4
				)
				(= cycles 1)
			)
			(1 (= ticks 30))
			(2 (rogHand setCycle: End self))
			(3
				(buttonNoise number: 124 loop: 1 play: self)
			)
			(4
				(rogArm dispose:)
				(rogHand dispose:)
				(switch register
					(0
						(if
							(and
								(< (eureka puke?) 1)
								(not (Btst 86))
								(> global130 0)
							)
							(= next sPushGreen)
						)
						(= cycles 1)
					)
					(1
						(cond 
							((< wd40State 2) (messager say: 6 1 0 4 self 202))
							((and (< (eureka puke?) 1) (not (Btst 86))) (= next sPushOrange) (= cycles 1))
							(else (= cycles 1))
						)
					)
					(2
						(= cycles 1)
						(= next sPushRed)
					)
				)
			)
			(5
				(theGame handsOn:)
				(buttonNoise dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sPushGreen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 1)
				(monitor1 init:)
			)
			(1
				(if (Btst 60)
					(messager say: 6 0 8 1 self)
				else
					(messager say: 6 0 8 2 self)
				)
			)
			(2
				(self setScript: (ScriptID 218 0) self)
			)
			(3
				(monitor1 dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sPushOrange of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 1)
				(monitor2 init:)
			)
			(1
				(messager say: 40 0 0 1 self)
			)
			(2
				(self setScript: (ScriptID 219 0) self)
			)
			(3
				(monitor2 dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sPushRed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(panel init: cel: 0 setCycle: CT 5 1 self)
				(panelNoise number: 217 loop: 1 play:)
			)
			(1
				(cast eachElementDo: #stopUpd)
				(= cycles 1)
			)
			(2
				(panel setCycle: CT 9 1 self)
				(panelNoise number: 218 loop: 1 play:)
			)
			(3
				(proc204_1)
				(panel stopUpd:)
				(= cycles 1)
			)
			(4
				(theGame handsOn:)
				(panelNoise dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sCycle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client cel: 1 setCycle: Beg self)
			)
			(1 (= seconds 1))
			(2 (client setCycle: End self))
			(3 (self changeState: 0))
		)
	)
)

(instance sBlink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(monitor3 setCel: 1)
				(monitor1 setCel: 1)
				(if (cast contains: monitor2) (monitor2 setCel: 1))
				(= ticks 20)
			)
			(2
				(monitor1 setCel: 0)
				(if (cast contains: monitor2) (monitor2 setCel: 0))
				(monitor3 setCel: 0)
				(= state (- state 2))
				(= ticks 45)
			)
		)
	)
)

(instance monitor1 of Prop
	(properties
		x 88
		y 46
		z 20
		view 201
		loop 6
		priority 10
		signal $4011
	)
	
	(method (init)
		(if
			(or
				(>= (eureka puke?) 4)
				(Btst 84)
				(== (curRoom script?) (ScriptID 208 0))
			)
			(self loop: 0 cel: 0 setScript: sBlink noun: 36)
			(if
			(and (not (Btst 84)) (!= (theMusic2 number?) 105))
				(theMusic2 number: 105 loop: -1 play:)
			)
		else
			(self loop: 6 cel: 0 noun: 6 setCycle: 0)
		)
		(super init: &rest)
	)
)

(instance monitor2 of Prop
	(properties
		x 208
		y 46
		z 20
		noun 37
		view 201
		loop 6
		cel 1
		priority 9
		signal $4011
	)
	
	(method (init)
		(cond 
			((or (>= (eureka puke?) 4) (Btst 61)) (self loop: 2 cel: 0 noun: 37) (super init: &rest))
			((not (Btst 84)) (self loop: 6 cel: 1 noun: 40) (super init: &rest))
		)
	)
)

(instance monitor3 of View
	(properties
		x 145
		y 45
		z 20
		noun 38
		view 201
		loop 1
		priority 9
		signal $4011
	)
)

(instance rogArm of View
	(properties
		x 194
		y 142
		view 200
		loop 2
		priority 9
		signal $0010
	)
)

(instance rogHand of Prop
	(properties
		x 200
		y 144
		view 200
		loop 3
		signal $0001
	)
)

(instance panel of Prop
	(properties
		x 195
		y 144
		view 201
		loop 5
		priority 9
		signal $0010
	)
	
	(method (init)
		(if (Btst 84)
			(self setLoop: 5 cel: (panel lastCel:) stopUpd:)
		else
			(self setLoop: 5 cel: 0 stopUpd:)
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(panelNoise dispose:)
		(super dispose: &rest)
	)
	
	(method (cue)
		(self dispose:)
	)
)
