;;; Sierra Script 1.0 - (do not remove this comment)
(script# 245)
(include sci.sh)
(use Main)
(use eureka)
(use rm240)
(use Scaler)
(use PolyPath)
(use StopWalk)
(use Grooper)
(use Motion)
(use System)

(public
	specialEntry 0
	beaClimbsOut 1
	spikeComesWith 2
)

(instance beaClimbsOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (proc240_1)
					(self setScript: (ScriptID 240 3) self 2)
				else
					(= cycles 1)
				)
			)
			(1
				(ego setMotion: PolyPath 188 136 self)
			)
			(2 (ego setHeading: 0 self))
			(3
				(SolvePuzzle 204 10)
				((ScriptID 240 5)
					view: 280
					loop: 1
					cel: 5
					setCycle: Beg self
				)
			)
			(4
				((ScriptID 240 5) view: 259 loop: 2 cel: 2)
				((ScriptID 240 2)
					init:
					view: 24
					loop: 0
					cel: 0
					posn: 172 109 0
					setPri: 11
					setCycle: End self
				)
			)
			(5
				((ScriptID 240 5)
					view: 280
					loop: 1
					cel: 0
					setCycle: End self
				)
			)
			(6
				((ScriptID 240 5) view: 259 loop: 2 cel: 1)
				((ScriptID 240 2)
					view: 8
					loop: 2
					posn: 140 129
					setCycle: StopWalk -1
					setLoop: Grooper
					setScale: Scaler 116 71 149 123
				)
				(if (Btst 84)
					(messager say: 2 2 3 0 self)
				else
					(messager say: 2 2 56 0 self)
				)
			)
			(7
				((ScriptID 240 2) setMotion: MoveTo 119 127 self)
			)
			(8
				((ScriptID 240 2) setMotion: MoveTo 128 143 self)
			)
			(9
				((ScriptID 240 2) setHeading: 180 self)
			)
			(10
				(((ScriptID 240 2) looper?) dispose:)
				((ScriptID 240 2) looper: 0 setCycle: 0)
				(= cycles 3)
			)
			(11
				((ScriptID 240 2)
					view: 25
					setLoop: 0
					setScale: 0
					cel: 15
				)
				(Bclr 45)
				(= global164 9)
				(if (== (eureka puke?) 7)
					(= next beaGoesFirst)
				else
					(theGame handsOn:)
				)
				(self dispose:)
			)
		)
	)
)

(instance specialEntry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 239
					loop: 3
					cel: 0
					setPri: 1
					posn: 42 135
					setScale:
					scaleX: 110
					scaleY: 110
					setScript: shakeShip
					setCycle: End self
				)
			)
			(1
				(NormalEgo 0 2)
				(ego posn: 48 134 setScale: Scaler 116 71 149 123)
				((ScriptID 240 22) setCycle: Beg self)
			)
			(2
				(messager say: 20 0 28 0 self)
			)
			(3
				(if (cast contains: (ScriptID 240 2))
					(= next beaGoesFirst)
				else
					(theGame handsOn:)
				)
				((ScriptID 240 22) stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance beaGoesFirst of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Face ego (ScriptID 240 2) self)
			)
			(1
				(messager say: 2 2 4 1 2 self)
			)
			(2
				((ScriptID 240 2) setCycle: Beg self)
				(theMusic2 number: 260 loop: 1 play:)
			)
			(3
				(theMusic2 stop:)
				((ScriptID 240 2) dispose:)
				(= seconds 2)
			)
			(4 (messager say: 2 2 4 3 self))
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance shakeShip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 10))
			(1
				(ShakeScreen 5 2)
				(= state -1)
				(= cycles 1)
			)
		)
	)
)

(instance spikeComesWith of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 240 24) init: 1 setCycle: End self)
				((ScriptID 240 20) init: cel: 0 setCycle: End self)
			)
			(1)
			(2
				((ScriptID 240 24) stopUpd:)
				((ScriptID 240 20) dispose:)
				((ScriptID 240 8) setCycle: Beg self)
			)
			(3
				(theMusic1 number: 23 loop: -1 play:)
				((ScriptID 240 8)
					view: 244
					loop: 5
					cel: 0
					posn: 251 100
					setCycle: End self
				)
			)
			(4
				(proc240_29 1)
				((ScriptID 240 8)
					posn: 181 144
					setMotion: MoveTo (+ (ego x?) 30) (+ (ego y?) 5) self
				)
			)
			(5
				(ego setHeading: 135 self)
				((ScriptID 240 8) setHeading: 270)
			)
			(6
				(proc240_30)
				((ScriptID 240 8) dispose:)
				(= register 3)
				(= start 0)
				(ego
					view: 192
					loop: 0
					cel: 0
					setScale: Scaler 116 71 149 123
					setCycle: End self
				)
			)
			(7 (ego setCycle: CT 5 -1 self))
			(8
				(ego setCycle: End self)
				(if (-- register) (= state (- state 2)))
			)
			(9
				(ego y: 1 cel: 0)
				(= cycles 1)
			)
			(10 (ego setCycle: CT 4 1 self))
			(11
				(ego get: 8 setCycle: CT 1 -1 self)
				(if (-- register) (= state (- state 2)))
			)
			(12 (ego setCycle: End self))
			(13
				(messager say: 22 0 31 0 self)
			)
			(14
				(NormalEgo 0 4)
				(self dispose:)
			)
		)
	)
)
