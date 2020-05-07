;;; Sierra Script 1.0 - (do not remove this comment)
(script# 285)
(include sci.sh)
(use Main)
(use SmallItem)
(use System)

(public
	pearlForRingScr 0
	pearlForMapScr 1
	mapForPearlOrRingScr 2
)

(instance pearlForRingScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 285)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 30 curRoomNum get: 39)
				(theGame handsOff:)
				(proc280_10 self)
				(ego normal: 0 view: 280 loop: 7 cel: 0)
				(= cycles 2)
			)
			(1 (UnLoad 128 900))
			(2
				(if register
					(= register 27)
					(self setScript: (ScriptID 286 1) self)
				else
					(= register 26)
					(theGame givePoints: 2)
					(self setScript: (ScriptID 286 1) self 16384)
				)
			)
			(3
				(messager say: 4 66 register 1 (ScriptID 286 1))
			)
			(4
				(messager
					say: 4 66 register 2 (if (== register 26) (ScriptID 286 1) else self)
				)
			)
			(5 (= cycles 2))
			(6
				(self setScript: (ScriptID 286 0) self 1)
			)
			(7
				(messager say: 4 66 register 3 self)
			)
			(8
				(if (== register 27) (++ state))
				(= ticks 1)
			)
			(9
				(messager say: 4 66 register 4 self)
			)
			(10
				((ScriptID 280 2) setScript: (ScriptID 280 9))
				(ego reset: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance pearlForMapScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 285)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 30 curRoomNum get: 0)
				(theGame handsOff:)
				(proc280_10 self)
				(ego normal: 0 view: 280 loop: 7 cel: 0)
				(= cycles 2)
			)
			(1 (UnLoad 128 900))
			(2
				(self
					setScript:
						(ScriptID 286 1)
						self
						(if register
							(= register 29)
							-32768
						else
							(= register 28)
							-16384
						)
				)
			)
			(3
				(messager say: 4 66 register 1 script)
			)
			(4
				(messager
					say: 4 66 register 2 (if (== register 28) script else (++ state) self)
				)
			)
			(5
				(messager say: 4 66 register 3 self)
			)
			(6
				(self setScript: (ScriptID 286 3) self)
			)
			(7
				((ScriptID 280 2) setScript: (ScriptID 280 9))
				(ego reset: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance mapForPearlOrRingScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 285)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 0 curRoomNum)
				(theGame handsOff:)
				(proc280_10 self)
				(ego normal: 0 view: 280 loop: 7 cel: 0)
				(= cycles 2)
			)
			(1 (UnLoad 128 900))
			(2
				(if (== ((inventory at: 30) owner?) curRoomNum)
					(ego get: (= register 30))
				else
					(= register 31)
					(ego get: 39)
				)
				(messager say: 4 12 register 1 self 280)
			)
			(3
				(messager say: 4 12 register 2 self 280)
			)
			(4
				(self setScript: (ScriptID 286 3) self 1)
			)
			(5
				(messager say: 4 12 register 3 self 280)
			)
			(6
				(self setScript: (ScriptID 286 0) self 1)
			)
			(7
				((ScriptID 280 2) setScript: (ScriptID 280 9))
				(ego reset: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
