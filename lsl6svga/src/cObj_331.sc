;;; Sierra Script 1.0 - (do not remove this comment)
(script# 331)
(include sci.sh)
(use Main)
(use fileScr)
(use rm330)
(use rm740)
(use PolyPath)
(use Motion)
(use System)

(public
	leaveWithMsgScr 0
	exitScr 1
	notWhileRunningScr 2
	enterRoomScr 3
)

(class cObj of Obj
	(properties
		scratch 0
	)
)

(instance leaveWithMsgScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(if (or (== (proc330_2) 11) (Btst 112))
					(messager say: 0 0 (proc330_2) 0 self)
				else
					(++ state)
					(= cycles 2)
				)
			)
			(2
				(cond 
					((< (ego x?) 157) (ego setHeading: 45 self))
					((> (ego x?) 210) (ego setHeading: 310 self))
					(else (ego setHeading: 360 self))
				)
			)
			(3 (= cycles 2))
			(4
				(cond 
					((and (== (proc330_2) 11) (< (ego x?) 157)) (ego setMotion: PolyPath 114 136 self))
					((and (== (proc330_2) 11) (>= (ego x?) 157)) (ego setMotion: PolyPath 217 138 self))
					(else (self setScript: exitScr))
				)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance exitScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				((ScriptID 330 12) setPri: 145)
				(ego
					setMotion: MoveTo (- (ego x?) 28) (+ (ego y?) 40) self
				)
			)
			(2 (curRoom newRoom: 310))
		)
	)
)

(instance notWhileRunningScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(messager say: 0 0 34 0 self)
			)
			(2
				(ego setMotion: PolyPath (ego x?) (- (ego y?) 4) self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterRoomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (OneOf global171 6 7)
					((ScriptID 330 1)
						view: 203
						loop: 3
						cel: 0
						posn: 192 145 0
						setCycle: Walk
						setMotion: MoveTo 194 131 self
						init:
					)
					(= register 1)
				else
					(= register 0)
				)
				(ego setMotion: MoveTo 83 135 egoCue)
			)
			(1
				((ScriptID 330 1) setHeading: 225 self)
			)
			(2
				((ScriptID 330 1) loop: 4 cel: 1)
				(= ticks 30)
			)
			(3
				(if
					(and
						(!= register 3)
						(!= (ego x?) 83)
						(!= (ego y?) 135)
					)
					(-- state)
					(= ticks 60)
				else
					(messager say: 11 0 4 1 self)
				)
			)
			(4
				(messager say: 11 0 4 2 self)
			)
			(5
				((ScriptID 330 1) setHeading: 360 self)
			)
			(6
				((ScriptID 330 1)
					view: 333
					loop: 0
					cel: 0
					cycleSpeed: 10
					setCycle: CT 12 1 self
				)
			)
			(7
				(UpdateScreenItem ((ScriptID 92 3) view: 1906))
				((ScriptID 330 1) setCycle: End self)
			)
			(8
				(theMusic2 number: 520 loop: 1 play:)
				(ShakeScreen 5 1)
				(= cycles 2)
			)
			(9
				((ScriptID 330 1)
					view: 333
					loop: 1
					cel: 0
					posn: 175 122 27
				)
				(= ticks 30)
			)
			(10
				(UpdateScreenItem ((ScriptID 92 3) view: 1900 loop: 1))
				(= global171 8)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance egoCue of cObj
	(properties)
	
	(method (cue &tmp enterRoomScrRegister)
		(if
		(not (= enterRoomScrRegister (enterRoomScr register?)))
			(theGame handsOn:)
			(enterRoomScr register: 0 dispose:)
		else
			(enterRoomScr register: (++ enterRoomScrRegister))
			(switch enterRoomScrRegister
				(2 (ego setHeading: 45 self))
				(3 (ego loop: 8 cel: 0))
			)
		)
	)
)
