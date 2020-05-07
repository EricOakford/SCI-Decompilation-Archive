;;; Sierra Script 1.0 - (do not remove this comment)
(script# 278)
(include sci.sh)
(use Main)
(use Motion)
(use System)

(public
	giveMintScr 0
	genieSpyScr 1
	offerItemScr 2
)

(instance genieLookScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 271 0) setScript: 0)
				(if
					(and
						((ScriptID 271 0) loop?)
						(> ((ScriptID 271 0) cel?) 3)
					)
					(++ state)
					((ScriptID 271 0) setCycle: End self)
				else
					((ScriptID 271 0) loop: 1 cel: 0)
					(= ticks 10)
				)
			)
			(1
				((ScriptID 271 0) cel: 4 setCycle: End self)
			)
			(2 (client cue:))
			(3
				((ScriptID 271 0) setCycle: Beg self)
			)
			(4
				((ScriptID 271 0) setScript: (ScriptID 271 1))
				(if caller (caller cycles: 2) (= caller 0))
				(self dispose:)
			)
		)
	)
)

(instance genieSpyScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(if (== start 0) (DisposeScript 278))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 271 0)
					setScript: 0
					setMotion:
						MoveTo
						(+ ((ScriptID 271 0) x?) 10)
						((ScriptID 271 0) y?)
						self
				)
			)
			(1
				((ScriptID 271 0) loop: 1 cel: 0)
				(= start 2)
				(self dispose:)
			)
			(2
				((ScriptID 271 0)
					setMotion:
						MoveTo
						(- ((ScriptID 271 0) x?) 10)
						((ScriptID 271 0) y?)
						self
				)
			)
			(3
				((ScriptID 271 0)
					loop: 1
					cel: 0
					setScript: (ScriptID 271 1)
				)
				(= start 0)
				(self dispose:)
			)
		)
	)
)

(instance giveMintScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 278)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 23 curRoomNum)
				(theGame handsOff:)
				(self setScript: genieLookScr self)
			)
			(1
				(messager say: 9 63 0 1 self 270)
			)
			(2
				(messager say: 9 63 0 2 self 270)
			)
			(3
				(ego
					normal: 0
					setSpeed: 6
					view: 2832
					loop: 0
					setCycle: End self
				)
			)
			(4 (= ticks 15))
			(5
				((ScriptID 271 0)
					view: 2834
					loop: 0
					cel: 0
					posn: 185 118
					setCycle: End self
				)
			)
			(6 (= ticks 30))
			(7
				(messager say: 9 63 0 3 self 270)
			)
			(8 (ego setCycle: Beg self))
			(9
				(ego reset: 7)
				((ScriptID 271 0)
					loop: 1
					cel: 0
					posn: 185 118
					cycleSpeed: 14
					setCycle: End self
				)
			)
			(10 (= ticks 15))
			(11
				((ScriptID 271 0) cel: 0 setCycle: End self)
			)
			(12 (= ticks 15))
			(13
				((ScriptID 271 0) setCycle: Beg self)
			)
			(14 (= ticks 15))
			(15
				((ScriptID 271 0) setCycle: Beg self)
			)
			(16 (= ticks 45))
			(17
				(messager say: 9 63 0 4 self 270)
			)
			(18
				((ScriptID 271 0) view: 275 loop: 1 cel: 0 posn: 179 117)
				(script cue:)
			)
			(19
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance offerItemScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 278)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(self setScript: genieLookScr self)
			)
			(1 (messager say: 9 0 0 0 self))
			(2 (script cue:))
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
