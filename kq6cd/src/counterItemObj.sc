;;; Sierra Script 1.0 - (do not remove this comment)
(script# 283)
(include sci.sh)
(use Main)
(use SmallItem)
(use Kq6Procs)
(use Inset)
(use Conv)
(use Actor)
(use System)

(public
	counterInset 0
	buyItemWithCoinScr 1
	genericExchangeScr 2
	offerCoinForMapScr 3
	lookAtCounterScr 4
)

(local
	local0
	[theInvItemNum 21] = [48 3 14 27 -1 0 5 38 1 0 0 -1 -1 10 5 38 2 0 0 -1]
	theState
	local23
)
(instance counterInset of Inset
	(properties
		view 287
		x 84
		y 48
		priority 15
		disposeNotOnMe 1
		noun 35
	)
	
	(method (init)
		(super init: &rest)
		(if (== ((inventory at: 48) owner?) curRoomNum)
			(tinderBox init:)
		)
		(if (== ((inventory at: 3) owner?) curRoomNum)
			(paintBrush init:)
		)
		(if (== ((inventory at: 27) owner?) curRoomNum)
			(windupBird init:)
		)
		(if (== ((inventory at: 14) owner?) curRoomNum)
			(flute init:)
		)
		(if
		(and (== (curRoom script?) lookAtCounterScr) 1)
			(messager say: 9 1)
		)
		(if (and (Btst 29) (Btst 30) (not (ego has: 0)))
			(counterMap init:)
		)
		(self setScript: initDoneScr)
	)
	
	(method (doit)
		(if script (script doit:))
	)
	
	(method (dispose)
		(theIconBar enable: 6)
		(theGame handsOff:)
		(DisposeClone insetView)
		(if (not local0) (Bclr 51) (Bclr 50))
		(super dispose: &rest)
		(curRoom drawPic: curRoomNum 100)
	)
	
	(method (drawInset)
		(super drawInset:)
		(insetView ignoreActors: addToPic:)
	)
)

(class counterItemObj of View
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view 287
		loop 1
		cel 0
		priority 15
		underBits 0
		signal $4011
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		baseFlag 32
		lookFlagNum 0
		invItemNum 0
		item 0
	)
	
	(method (init theItem)
		(super init: &rest)
		(if (> argc 0) (= item theItem))
		(= invItemNum [theInvItemNum item])
		(= lookFlagNum (+ baseFlag item))
		(= sightAngle 26505)
		(self addToPic:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(1
				(if (not (Btst lookFlagNum))
					(Bset lookFlagNum)
					(messager say: noun 1 42 0 0 280)
				else
					(messager say: noun 1 43)
				)
			)
			(5
				(= local0 1)
				(if (curRoom script?)
					((curRoom script?)
						setScript: itemTradeScr (counterInset caller?) noun
					)
				else
					(curRoom
						setScript: itemTradeScr (counterInset caller?) noun
					)
				)
				(counterInset caller: 0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance roomTalk of Conversation
	(properties)
)

(instance tinderBox of counterItemObj
	(properties
		x 197
		y 89
		noun 10
	)
)

(instance paintBrush of counterItemObj
	(properties
		x 191
		y 67
		noun 13
		cel 2
		item 1
	)
)

(instance windupBird of counterItemObj
	(properties
		x 92
		y 76
		noun 12
		cel 1
		item 3
	)
)

(instance flute of counterItemObj
	(properties
		x 143
		y 62
		noun 11
		cel 3
		item 2
	)
)

(instance counterMap of View
	(properties
		x 145
		y 106
		noun 37
		view 287
		loop 1
		cel 4
		priority 15
		signal $5010
	)
	
	(method (init)
		(super init: &rest)
		(= sightAngle 26505)
		(self addToPic:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(messager
					say: noun theVerb (cond 
						((Btst 50) 16)
						((Btst 51) 39)
						(else 0)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance itemTradeScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(counterInset dispose:)
				(= cycles 2)
			)
			(2 (= cycles 2))
			(3
				(= temp1 0)
				(= temp0 0)
				(while (< temp0 4)
					(if (ego has: [theInvItemNum temp0])
						(= temp1 1)
						(break)
					)
					(++ temp0)
				)
				(cond 
					(temp1 (messager say: register: nodePtr: self))
					((or (Btst 50) (Btst 51))
						(= state 3)
						(= theState (if (Btst 50) 4 else 6))
						(messager say: register 5 16 1 self)
					)
					(else (= state 7) (messager say: register 5 38 1 self))
				)
			)
			(4
				(= state theState)
				(self
					setScript:
						(ScriptID 287 1)
						self
						(switch register
							(11 2)
							(10 0)
							(12 3)
							(13 1)
						)
				)
			)
			(5
				(= state 8)
				(messager say: 10 5 16 2 self oneOnly: 0)
			)
			(6
				(= state 8)
				(messager say: 10 5 41 2 self oneOnly: 0)
			)
			(7
				(= state 8)
				(messager
					say: register 5 (if (== currentAct 1) 39 else 40) 0 self
				)
			)
			(8
				(= state 8)
				(messager say: 10 5 38 2 self)
			)
			(9
				(Bclr 50)
				(Bclr 51)
				(if (== client curRoom) (theGame handsOn:))
				(self dispose:)
			)
		)
	)
)

(instance lookAtCounterScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 283)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc280_10 self)
			)
			(1
				(ego normal: 0 view: 280 loop: 7 cel: 0)
				(= cycles 2)
			)
			(2
				(UnLoad 128 900)
				(= cycles 2)
			)
			(3
				(counterInset init: self curRoom)
			)
			(4 (ego reset: 0) (= cycles 2))
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance buyItemWithCoinScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 283)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc280_10 self)
			)
			(1
				(self
					setScript:
						(ScriptID 286 1)
						self
						(if (Bset 109)
							(= register 69)
							-32768
						else
							(= register 23)
							-16384
						)
				)
			)
			(2
				(messager say: 4 40 register 1 script)
			)
			(3
				(messager
					say: 4 40 register 2 (if (!= register 23) (++ state) self else script)
				)
			)
			(4
				(messager say: 4 40 23 3 self oneOnly: 0)
			)
			(5 (= cycles 2))
			(6
				(Bset 50)
				(counterInset init: self curRoom)
			)
			(7 (= cycles 2))
			(8
				(if (not local0)
					(messager say: 1 5 63 0 self)
				else
					(++ state)
					(theGame givePoints: 2)
					(ego put: 9 curRoomNum)
					(= cycles 1)
				)
			)
			(9
				(self setScript: (ScriptID 286 0) self 1)
			)
			(10
				(ego reset: 0)
				(= cycles 2)
			)
			(11
				((ScriptID 280 2) setScript: (ScriptID 280 9))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance genericExchangeScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 283)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc280_10 self)
			)
			(1
				(= register
					(switch register
						(20 0)
						(37 3)
						(29 1)
						(31 2)
					)
				)
				(self setScript: (ScriptID 287 0) self register)
			)
			(2
				(messager say: 4 20 24 1 script)
			)
			(3
				(if (== currentAct 1)
					(messager say: 4 20 24 2 self)
				else
					(messager say: 4 20 25 1 self)
				)
			)
			(4
				(messager say: 4 20 24 3 self)
			)
			(5 (= cycles 2))
			(6
				(Bset 51)
				(counterInset init: self curRoom)
			)
			(7 (= cycles 10))
			(8
				(if (not local0)
					(messager say: 1 5 63 1 self)
				else
					(= state (+ state 2))
					(= cycles 1)
				)
			)
			(9
				(messager say: 1 5 64 2 self)
			)
			(10
				(self setScript: (ScriptID 287 1) self register)
			)
			(11 (= cycles 2))
			(12
				(ego reset: 0)
				(= cycles 2)
			)
			(13
				((ScriptID 280 2) setScript: (ScriptID 280 9))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance offerCoinForMapScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 283)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc280_10 self)
			)
			(1
				(self
					setScript:
						(ScriptID 286 1)
						self
						(if (Bset 109)
							(= register 70)
							-32768
						else
							(= register 22)
							-16384
						)
				)
			)
			(2
				(messager say: 4 40 register 1 script)
			)
			(3
				(messager
					say: 4 40 register 2 (if (!= register 22) (++ state) self else script)
				)
			)
			(4
				(messager say: 4 40 22 3 self oneOnly: 0)
			)
			(5 (= cycles 2))
			(6
				(Bset 50)
				(counterInset init: self curRoom)
			)
			(7 (= cycles 2))
			(8
				(if (not local0)
					(messager say: 1 5 63 0 self)
				else
					(++ state)
					(theGame givePoints: 2)
					(ego put: 9 curRoomNum)
					(= cycles 1)
				)
			)
			(9
				(self setScript: (ScriptID 286 0) self)
			)
			(10
				(ego reset: 0)
				(= cycles 2)
			)
			(11
				((ScriptID 280 2) setScript: (ScriptID 280 9))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance initDoneScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(theGame handsOn:)
				(theIconBar curIcon: (theIconBar at: 1))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
				(theIconBar disable: 0 3 4 5 6)
				(= local0 0)
				(self dispose:)
			)
		)
	)
)
