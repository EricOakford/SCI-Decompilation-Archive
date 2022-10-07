;;; Sierra Script 1.0 - (do not remove this comment)
(script# 275)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use Conv)
(use Motion)
(use System)

(public
	giveRingScr 0
)

(instance roomConv of Conversation
	(properties)
)

(instance interactionScr of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(= start (+ state 1))
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				((ScriptID 274 0)
					view: 2722
					loop: 3
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				((ScriptID 274 0) stopUpd:)
				(= cycles 2)
			)
			(2 (self dispose:))
			(3
				((ScriptID 274 0)
					view: 273
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				((ScriptID 274 0) stopUpd:)
				(= cycles 2)
			)
			(5 (self dispose:))
			(6
				((ScriptID 274 0) loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(7
				((ScriptID 274 0) stopUpd:)
				(= cycles 2)
			)
			(8 (self dispose:))
			(9
				((ScriptID 274 0)
					view: 2731
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(10
				((ScriptID 274 0) stopUpd:)
				(= cycles 2)
			)
			(11 (self dispose:))
			(12
				((ScriptID 274 0) loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(13
				((ScriptID 274 0) stopUpd:)
				(= cycles 2)
			)
			(14 (self dispose:))
		)
	)
)

(instance giveRingScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(= register 0)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 274 0) setScript: 0)
				(if register
					(messager say: 10 40 9 1 self)
				else
					(messager say: 10 70 11 1 self)
				)
			)
			(1
				(ego
					setSpeed: 6
					normal: 0
					view: 272
					loop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2 (ego stopUpd:) (= cycles 2))
			(3
				(UnLoad 128 900)
				(if register
					(messager say: 10 40 9 2 self)
				else
					(messager say: 10 70 11 2 self)
				)
			)
			(4
				((ScriptID 274 0)
					view: 2721
					loop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(5
				((ScriptID 274 0) stopUpd:)
				(= cycles 2)
			)
			(6
				(if register
					(messager say: 10 40 9 3 self)
				else
					(messager say: 10 70 11 3 self)
				)
			)
			(7 (ego setCycle: BegLoop self))
			(8 (ego stopUpd:) (= cycles 2))
			(9
				(roomConv
					add: -1 19 0 27 1
					add: interactionScr
					add: -1 19 0 27 2
					add: -1 19 0 27 3
					add: -1 19 0 27 4
					add: interactionScr
					add: -1 19 0 27 5
					add: -1 19 0 27 6
					add: -1 19 0 27 7
					add: -1 19 0 27 8
					add: -1 19 0 27 9
					add: -1 19 0 27 10
					add: interactionScr
					add: -1 19 0 27 11
					add: -1 19 0 27 12
					add: interactionScr
					add: -1 19 0 27 13
					add: -1 19 0 27 14
					add: interactionScr
					add: -1 19 0 27 15
					init: self
				)
			)
			(10 (= cycles 2))
			(11
				(cond 
					((OneOf currentAct 1 2) (messager say: 10 70 11 4 self oneOnly: 0))
					((== currentAct 3) (self setScript: giveRingAct3Scr self))
					((== currentAct 4) (self setScript: giveRingAct4Scr self))
				)
			)
			(12
				(DisposeScript 1020)
				(DisposeScript 1001)
				(= cycles 1)
			)
			(13
				((ScriptID 274 0)
					view: 274
					loop: 0
					cel: 0
					setCycle: CycleTo 3 1 self
				)
			)
			(14
				(UnLoad 128 2721)
				(UnLoad 128 2722)
				(UnLoad 128 273)
				(UnLoad 128 2731)
				((ScriptID 270 3) init:)
				(= cycles 2)
			)
			(15
				(ego startUpd:)
				(= cycles 2)
			)
			(16
				((ScriptID 274 0) setCycle: EndLoop self)
				(ego
					posn: ((ScriptID 274 0) approachX?) ((ScriptID 274 0) approachY?)
					reset: 1
				)
			)
			(17
				(UnLoad 128 272)
				(client setScript: (ScriptID 274 1))
			)
		)
	)
)

(instance giveRingAct3Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 10 70 12 1 self)
			)
			(1 (= cycles 2))
			(2
				(messager say: 19 0 28 0 self)
			)
			(3 (= cycles 2))
			(4
				(messager say: 10 70 12 2 self oneOnly: 0)
			)
			(5 (self dispose:))
		)
	)
)

(instance giveRingAct4Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 10 70 13 0 self)
			)
			(1 (= cycles 2))
			(2
				(Bset 52)
				(messager say: 19 0 29 0 self)
			)
			(3 (self dispose:))
		)
	)
)
