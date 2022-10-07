;;; Sierra Script 1.0 - (do not remove this comment)
(script# 284)
(include sci.sh)
(use Main)
(use SmallItem)
(use Kq6Procs)
(use Reverse)
(use Motion)
(use System)

(public
	ownerTalkScr 0
	ringMapNotOutScr 1
	ringForMapActIScr 2
	ringForPearlScr 3
	ringForMapNotActIScr 4
	talkAfterActI 5
)

(instance ownerTalkScr of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 284)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc280_10 self)
				(ego normal: 0 view: 280 loop: 7 cel: 0)
				(= cycles 2)
			)
			(1 (UnLoad 128 900))
			(2
				(messager say: 4 2 13 1 self)
			)
			(3
				(messager say: 4 2 13 2 self)
			)
			(4
				((ScriptID 280 2)
					view: 286
					posn: 236 127
					loop: 1
					cel: 0
					setPri: 11
					setCycle: EndLoop self
				)
				((inventory at: 0) owner: -1)
			)
			(5 (= cycles 2))
			(6
				(messager say: 4 2 13 3 self)
			)
			(7
				(messager say: 4 2 13 4 self)
			)
			(8
				(messager say: 4 2 13 5 self)
			)
			(9
				(messager say: 4 2 13 6 self)
			)
			(10
				((ScriptID 280 2) loop: 2 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(11
				((ScriptID 280 1) init:)
				((ScriptID 280 2) setCycle: EndLoop self)
			)
			(12
				((ScriptID 280 2) setPri: -1 view: 280 loop: 8 cel: 0)
				(= cycles 2)
			)
			(13
				(UnLoad 128 286)
				(messager say: 4 2 13 7 self oneOnly: 0)
			)
			(14
				((ScriptID 280 2) setScript: (ScriptID 280 9))
				(ego reset: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance ringMapNotOutScr of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 284)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc280_10 self)
				(ego normal: 0 view: 280 loop: 7 cel: 0)
				(= cycles 2)
			)
			(1 (UnLoad 128 900))
			(2
				(= register
					(if ((ScriptID 10 0) isSet: 1)
						(self setScript: (ScriptID 286 2) self)
						36
					else
						((ScriptID 10 0) setIt: 1)
						(self setScript: (ScriptID 286 1) self 16384)
						18
					)
				)
			)
			(3
				(messager
					say: 4 70 register 1 (if (== register 36) self else script)
				)
			)
			(4
				(messager say: 4 70 register 2 script)
			)
			(5
				(if (== register 36)
					(= state (+ state 2))
					(= cycles 2)
				else
					(messager say: 4 70 register 3 self)
				)
			)
			(6
				(self setScript: (ScriptID 286 0) self)
			)
			(7
				(messager say: 4 70 register 4 self)
			)
			(8
				((ScriptID 280 2) setScript: (ScriptID 280 9))
				(ego reset: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance ringForMapActIScr of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 284)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 39 curRoomNum)
				(theGame handsOff:)
				(proc280_10 self)
				(ego normal: 0 view: 280 loop: 7 cel: 0)
				(= cycles 2)
			)
			(1 (UnLoad 128 900))
			(2
				(theGame givePoints: 5)
				(self setScript: (ScriptID 286 1) self 16384)
			)
			(3
				(UnLoad 128 2811)
				(UnLoad 128 284)
				(messager say: 4 70 19 1 (ScriptID 286 1))
			)
			(4
				(messager say: 4 70 19 2 (ScriptID 286 1))
			)
			(5
				(messager say: 4 70 19 3 self)
			)
			(6
				(messager say: 4 70 19 4 self)
			)
			(7
				(messager say: 4 70 19 5 self)
			)
			(8
				(messager say: 4 70 19 6 self)
			)
			(9
				(messager say: 4 70 19 7 self)
			)
			(10
				(messager say: 4 70 19 8 self)
			)
			(11
				(messager say: 4 70 19 9 self)
			)
			(12
				(NextAct)
				(self setScript: (ScriptID 286 3) self)
			)
			(13
				(UnLoad 128 286)
				(self setScript: (ScriptID 282 2) self)
				(= cycles 1)
			)
			(14
				(ego
					reset:
					setSpeed: 6
					setLoop: 0
					setCycle: Reverse
					setMotion: MoveTo (- (ego x?) 10) (ego y?) self
				)
			)
			(15 (ego setCycle: Walk))
			(16 (curRoom newRoom: 145))
		)
	)
)

(instance ringForMapNotActIScr of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 284)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego get: 0 put: 39 curRoomNum)
				(theGame handsOff:)
				(proc280_10 self)
				(ego normal: 0 view: 280 loop: 7 cel: 0)
				(= cycles 2)
			)
			(1 (UnLoad 128 900))
			(2
				(messager say: 4 70 21 1 self)
			)
			(3
				(messager say: 4 70 21 2 self)
			)
			(4
				(self setScript: (ScriptID 286 1) self -32768)
			)
			(5 (script cue:))
			(6 (= ticks 15))
			(7
				(self setScript: (ScriptID 286 3) self)
			)
			(8
				((ScriptID 280 2) setScript: (ScriptID 280 9))
				(ego reset: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance ringForPearlScr of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 284)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 39 get: 30)
				(theGame handsOff:)
				(proc280_10 self)
				(ego normal: 0 view: 280 loop: 7 cel: 0)
				(= cycles 2)
			)
			(1 (UnLoad 128 900))
			(2
				(messager say: 4 70 20 1 self)
			)
			(3
				(messager say: 4 70 20 2 self)
			)
			(4
				(self setScript: (ScriptID 286 1) self)
			)
			(5 (script cue:))
			(6 (= ticks 120))
			(7
				(self setScript: (ScriptID 286 0) self)
			)
			(8
				(messager say: 4 70 20 3 self)
			)
			(9
				(ego reset: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance talkAfterActI of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 284)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc280_10 self)
				(ego normal: 0 view: 280 loop: 7 cel: 0)
				(= cycles 2)
			)
			(1 (UnLoad 128 900))
			(2
				(messager say: 4 2 47 1 self)
			)
			(3
				(messager
					say:
						4
						2
						(switch (Random 0 4)
							(0 15)
							(1 37)
							(2 44)
							(3 45)
							(4 46)
						)
						1
						self
				)
			)
			(4
				(ego reset: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
