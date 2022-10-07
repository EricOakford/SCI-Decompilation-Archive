;;; Sierra Script 1.0 - (do not remove this comment)
(script# 276)
(include sci.sh)
(use Main)
(use rm270)
(use Kq6Procs)
(use Motion)
(use System)

(public
	giveRareBookScr 1
	talkOwnerScr 2
	talkAfterLLoc 3
)

(instance giveRareBookScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 276)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 77)
				(proc270_7 self)
			)
			(1
				(messager say: 18 27 0 1 self)
			)
			(2
				(ego
					setSpeed: 6
					normal: 0
					put: 36
					get: 45
					view: 2771
					loop: 4
					cel: 0
					cycleSpeed: 5
					posn: 264 151
					setCycle: EndLoop
				)
				((ScriptID 270 2)
					view: 2771
					loop: 5
					cel: 0
					posn: 307 152
					setCycle: EndLoop self
				)
			)
			(3 (= cycles 2))
			(4
				(messager say: 18 27 0 2 self)
			)
			(5
				((ScriptID 270 2)
					loop: 0
					cel: 0
					posn: 300 161
					setCycle: CycleTo 3 1 self
				)
			)
			(6
				((ScriptID 270 6) dispose:)
				(= cycles 2)
			)
			(7
				((ScriptID 270 2) setCycle: CycleTo 7 1 self)
			)
			(8
				(ego loop: 3 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(9
				((ScriptID 270 2) setCycle: EndLoop self)
				(ego setCycle: EndLoop self)
			)
			(10 0)
			(11 (= cycles 2))
			(12
				(ego
					reset: 0
					posn: ((ScriptID 270 2) approachX?) ((ScriptID 270 2) approachY?)
				)
				((ScriptID 270 2)
					view: 277
					loop: 2
					cel: 0
					posn: 303 151
					stopUpd:
				)
				(= cycles 2)
			)
			(13
				(messager say: 18 27 0 3 self)
			)
			(14
				(messager say: 18 27 0 4 self)
			)
			(15
				(theGame givePoints: 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance talkOwnerScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 276)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc270_7 self)
			)
			(1
				(if
					(and
						(cast contains: (ScriptID 271 0))
						((ScriptID 10 0) isSet: -32768)
					)
					(self setScript: (ScriptID 278 1) self)
				else
					(= cycles 1)
				)
			)
			(2
				(if (not (Btst 16))
					(Bset 16)
					(theGame givePoints: 1)
					(messager say: 18 2 22 0 self)
				else
					(messager say: 18 2 23 1 self)
				)
			)
			(3
				(if
					(and
						(cast contains: (ScriptID 271 0))
						((ScriptID 10 0) isSet: -32768)
					)
					((ScriptID 10 0) setIt: -32768)
					(self setScript: (ScriptID 278 1) self)
				else
					(= cycles 1)
				)
			)
			(4
				(self dispose:)
				(theGame handsOn:)
			)
		)
	)
)

(instance talkAfterLLoc of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 276)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc270_7 self)
			)
			(1
				(if
					(and
						(cast contains: (ScriptID 271 0))
						((ScriptID 10 0) isSet: -32768)
					)
					(self setScript: (ScriptID 278 1) self)
				else
					(= cycles 1)
				)
			)
			(2
				(messager say: 18 2 21 0 self)
			)
			(3
				(messager say: 18 2 22 5 self oneOnly: 0)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
