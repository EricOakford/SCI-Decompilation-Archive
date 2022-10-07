;;; Sierra Script 1.0 - (do not remove this comment)
(script# 286)
(include sci.sh)
(use Main)
(use SmallItem)
(use Scaler)
(use Motion)
(use Actor)
(use System)

(public
	ownerGiveScr 0
	alexGiveScr 1
	alexShowScr 2
	alexTakeMapScr 3
	genericShowScr 4
	fullMsgShowScr 5
)

(local
	local0
)
(instance ownerGiveScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 286)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					((ScriptID 280 2) view: 286 loop: 1 cel: 0)
					(= cycles 2)
				else
					(= state (+ state 4))
					(self cue:)
				)
			)
			(1
				((ScriptID 280 2) setCycle: CycleTo 2 1 self)
			)
			(2 (= cycles 2))
			(3
				((ScriptID 280 2) setCycle: BegLoop self)
			)
			(4 (= cycles 2))
			(5
				((ScriptID 280 2) view: 284 loop: 2 cel: 0)
				(= cycles 2)
			)
			(6
				((ScriptID 280 2) setCycle: CycleTo 1 1 self)
			)
			(7 (= cycles 2))
			(8
				(ego
					normal: 0
					setSpeed: 6
					posn: 188 135
					view: 2811
					loop: 1
					setScale: 0
					cel: 0
				)
				(= cycles 2)
			)
			(9 (ego setCycle: CycleTo 2 1 self))
			(10 (= cycles 2))
			(11
				((ScriptID 280 2) setCycle: BegLoop self)
				(ego setCycle: EndLoop self)
			)
			(12 0)
			(13
				((ScriptID 280 2) view: 2841 loop: 0 cel: 0)
				(ego
					posn: ((ScriptID 280 2) approachX?) ((ScriptID 280 2) approachY?)
					view: 280
					loop: 7
					cel: 0
					setScale: Scaler 105 90 139 121
				)
				(= cycles 2)
			)
			(14 (self dispose:))
		)
	)
)

(instance arm of Prop
	(properties
		x 199
		y 102
		view 281
		priority 15
		signal $0010
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance alexShowScr of Script
	(properties)
	
	(method (dispose &tmp temp0)
		(if
		(not (OneOf client fullMsgShowScr genericShowScr))
			(= temp0 1)
		else
			(= temp0 0)
		)
		(super dispose:)
		(if temp0 (DisposeScript 286))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					normal: 0
					setSpeed: 6
					posn: 188 135
					view: 2811
					setScale: 0
					loop: 1
					cel: 0
				)
				(if register
					(arm
						loop:
						(switch register
							(3 5)
							(2 6)
							(0 7)
							(1 8)
						)
						cel: 0
						init:
					)
				)
				(= cycles 2)
			)
			(1
				(ego setCycle: CycleTo 2 1 self)
				(if register (arm setCycle: CycleTo 2 1))
			)
			(2 (= cycles 2))
			(3
				(if register (= local0 1) (= register 0))
				(client cue:)
			)
			(4
				(if local0 (arm dispose:))
				(if register
					(arm
						loop:
						(switch register
							(3 5)
							(2 6)
							(0 7)
							(1 8)
						)
						cel: 2
						init:
					)
				)
				(= cycles 2)
			)
			(5
				(ego setCycle: EndLoop self)
				(if register (arm setCycle: EndLoop arm))
			)
			(6 (= cycles 2))
			(7
				(ego
					posn: ((ScriptID 280 2) approachX?) ((ScriptID 280 2) approachY?)
					view: 280
					loop: 7
					cel: 0
					setScale: Scaler 105 90 139 121
				)
				(self dispose:)
			)
		)
	)
)

(instance alexGiveScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 286)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					normal: 0
					setSpeed: 6
					posn: 188 135
					view: 2811
					loop: 1
					cel: 0
					setScale: 0
				)
				(= cycles 2)
			)
			(1 (ego setCycle: CycleTo 2 1 self))
			(2 (= cycles 2))
			(3 (client cue:))
			(4
				((ScriptID 280 2) view: 284 loop: 2 cel: 0)
				(= cycles 2)
			)
			(5
				((ScriptID 280 2) setCycle: CycleTo 1 1 self)
			)
			(6 (= cycles 2))
			(7
				(ego setCycle: EndLoop self)
				(if (& register $4000)
					((ScriptID 280 2) setCycle: EndLoop self)
				else
					(= state (+ state 3))
					(self cue:)
				)
			)
			(8 0)
			(9 (= cycles 2))
			(10 (client cue:))
			(11
				((ScriptID 280 2) setCycle: BegLoop self)
			)
			(12
				(if (not (& register $4000)) 0 else (self cue:))
			)
			(13 (= cycles 2))
			(14
				(if (& register $8000)
					((ScriptID 280 2)
						view: 286
						loop: 1
						cel: 1
						setCycle: CycleTo 2 1 self
					)
				else
					(= state (+ state 2))
					(self cue:)
				)
			)
			(15 (= cycles 2))
			(16
				((ScriptID 280 2) setCycle: BegLoop self)
			)
			(17 (= cycles 2))
			(18
				(ego
					posn: ((ScriptID 280 2) approachX?) ((ScriptID 280 2) approachY?)
					view: 280
					loop: 7
					cel: 0
					setScale: Scaler 105 90 139 121
				)
				((ScriptID 280 2) view: 280 loop: 8 cel: 0)
				(= cycles 2)
			)
			(19 (self dispose:))
		)
	)
)

(instance alexTakeMapScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 286)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					normal: 0
					setSpeed: 6
					view: 2861
					posn: 199 143
					loop: 0
					setScale: 0
				)
				(if (not register) (ego cel: 0) else (ego cel: 4))
				(= cycles 2)
			)
			(1
				(ego setCycle: CycleTo 2 (if register -1 else 1) self)
			)
			(2 (= cycles 2))
			(3
				(if register
					((ScriptID 280 1) init:)
				else
					((ScriptID 280 1) dispose:)
				)
				(= cycles 2)
			)
			(4
				(if register
					(ego setCycle: BegLoop self put: 0)
				else
					(ego setCycle: EndLoop self get: 0)
				)
			)
			(5 (= cycles 2))
			(6
				(ego
					posn: ((ScriptID 280 2) approachX?) ((ScriptID 280 2) approachY?)
					view: 280
					loop: 7
					cel: 0
					setScale: Scaler 105 90 139 121
				)
				(= ticks 12)
			)
			(7 (= cycles 2))
			(8 (self dispose:))
		)
	)
)

(instance fullMsgShowScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 286)
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
				(self setScript: alexShowScr self)
			)
			(3
				(messager say: 4 register 0 1 alexShowScr)
			)
			(4
				(messager say: 4 register 0 2 self)
			)
			(5
				(ego reset: 0)
				((ScriptID 280 2) setScript: (ScriptID 280 9))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance genericShowScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 286)
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
				(self setScript: alexShowScr self)
			)
			(3
				(if (not register) (= register 0))
				(messager say: 4 0 0 1 alexShowScr)
			)
			(4
				(messager
					say: 4 register 0 (if (== register 0) 2 else 1) self
				)
			)
			(5
				((ScriptID 280 2) setScript: (ScriptID 280 9))
				(ego reset: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
