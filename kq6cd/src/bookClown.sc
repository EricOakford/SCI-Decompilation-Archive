;;; Sierra Script 1.0 - (do not remove this comment)
(script# 277)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use PolyPath)
(use Motion)
(use System)

(public
	talkClownNotFriendScr 0
	talkClownFriendScr 1
	showClownScr 2
	showItemScr 3
)

(local
	theRegister
)
(instance talkClownNotFriendScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 277)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst 78)
					(= register 16)
				else
					(Bset 78)
					(= register 8)
				)
				(theGame handsOff:)
				(messager say: 10 2 register 1 self)
			)
			(1
				((ScriptID 274 0)
					setScript: 0
					view: 2721
					loop: 1
					cel: 0
					setCycle: End self
				)
			)
			(2
				(messager say: 10 2 register 2 self)
			)
			(3
				((ScriptID 274 0) setCycle: Beg self)
			)
			(4
				(theGame handsOn:)
				((ScriptID 274 0) setScript: (ScriptID 274 2))
				(self dispose:)
			)
		)
	)
)

(instance talkClownFriendScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 277)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion:
						PolyPath
						((ScriptID 274 0) approachX?)
						((ScriptID 274 0) approachY?)
						self
				)
			)
			(1 (ego setHeading: 270 self))
			(2 (= cycles 2))
			(3
				(= register (if (== currentAct 3) 14 else 15))
				(messager say: 10 2 register 1 self)
			)
			(4
				((ScriptID 274 0)
					setScript: 0
					view: 274
					loop: 0
					cel: 0
					setCycle: CT 3 1 self
				)
			)
			(5
				(UnLoad 128 2721)
				((ScriptID 270 3) init:)
				(= cycles 2)
			)
			(6
				((ScriptID 274 0) setCycle: CT 5 1 self)
			)
			(7
				(messager say: 10 2 register 2 self)
			)
			(8
				(messager say: 10 2 register 3 self)
			)
			(9
				(messager say: 10 2 register 4 self)
			)
			(10 (= cycles 2))
			(11
				(messager
					say: 19 0 (if (== currentAct 3) 28 else (Bset 52) 29) 0 self
				)
			)
			(12 (= cycles 2))
			(13
				(if (== currentAct 3)
					(messager say: 10 2 register 5 self)
				else
					(= cycles 2)
				)
			)
			(14
				((ScriptID 274 0)
					view: 274
					loop: 0
					cel: 5
					setCycle: End self
				)
			)
			(15
				(client setScript: (ScriptID 274 1))
			)
		)
	)
)

(instance showClownScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(= register (= theRegister 0))
		(DisposeScript 277)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 274 0) setScript: 0)
				(if register (= theRegister register))
				(= register (if (Btst 10) 7 else 8))
				(self setScript: showItemScr self)
			)
			(1
				(messager say: 10 0 register 1 self)
			)
			(2
				((ScriptID 274 0)
					view: 2721
					loop: 1
					cel: 0
					setCycle: End self
				)
			)
			(3
				(if theRegister
					(messager say: 10 theRegister 0 0 self)
				else
					(messager say: 10 0 register 2 self)
				)
			)
			(4
				(script cue:)
				((ScriptID 274 0) setCycle: Beg)
			)
			(5 (= cycles 2))
			(6
				(theGame handsOn:)
				((ScriptID 274 0) setScript: (ScriptID 274 2))
				(self dispose:)
			)
		)
	)
)

(instance showItemScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setSpeed: 6
					normal: 0
					view: 272
					loop: 1
					cel: 0
					setCycle: End self
				)
			)
			(1 (client cue:))
			(2 (ego setCycle: Beg self))
			(3
				(if register (client cue:) else (= cycles 2))
			)
			(4
				(ego
					posn: ((ScriptID 274 0) approachX?) ((ScriptID 274 0) approachY?)
					reset: 7
				)
				(= cycles 2)
			)
			(5
				(UnLoad 128 272)
				(= register 0)
				(self dispose:)
			)
		)
	)
)
