;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use Inset)
(use Scaler)
(use PolyPath)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	lampTradeScr 2
	talkToSellerScr 3
)

(local
	local0
)
(instance lampTradeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 10 0) setIt: 16)
				(if (not ((ScriptID 10 0) isSet: 4))
					((ScriptID 10 0) setIt: 4)
					(= register 22)
				else
					(= register 23)
				)
				(messager say: 4 43 register 1 self 240)
			)
			(1
				((ScriptID 241 0)
					setPri: -1
					loop: 6
					cel: 0
					setCycle: End self
				)
			)
			(2 (= cycles 2))
			(3
				(ego hide:)
				((ScriptID 241 0)
					view: 245
					loop: 2
					cel: 0
					posn: 41 128
					scaleX: 102
					scaleY: 102
					setScale:
					setSpeed: 6
					setCycle: End self
				)
			)
			(4
				(messager say: 4 43 register 2 self 240)
			)
			(5 (= cycles 2))
			(6
				((ScriptID 241 0) hide:)
				(ego
					show:
					normal: 0
					view: 247
					loop: 0
					cel: 0
					setSpeed: 6
					setCycle: End self
				)
			)
			(7
				(ego loop: 1 cel: 0 setCycle: End self)
			)
			(8 (= ticks 60))
			(9
				(lampSellerInset init: self curRoom)
			)
			(10
				(theGame handsOff:)
				(= cycles 2)
			)
			(11
				(if (not local0)
					(messager say: 39 0 0 1 self 240)
				else
					(messager say: 34 5 0 1 self 240)
				)
			)
			(12
				(if local0
					(= cycles 1)
				else
					(ego reset: 1 loop: 9 cel: 1)
					((ScriptID 241 0)
						show:
						setScale: 0
						view: 2431
						loop: 6
						cel: 6
						setPri: 7
						posn: 19 128
						setCycle: Beg self
					)
					(= state (+ state 2))
				)
			)
			(13
				(ego hide:)
				((ScriptID 241 0)
					show:
					view: 245
					loop: 1
					cel: 0
					setCycle: End self
				)
			)
			(14 (= cycles 2))
			(15
				(if (not local0)
					(messager say: 39 0 0 2 self 240)
				else
					(++ state)
					(self cue:)
				)
			)
			(16
				((ScriptID 241 0)
					setScale: 0
					posn: 19 128
					view: 2431
					loop: 6
					cel: ((ScriptID 241 0) lastCel:)
					setCycle: Beg self
				)
				(ego reset: 1)
				(theGame handsOn:)
				(client setScript: (ScriptID 241 1))
			)
			(17
				((ScriptID 241 0)
					posn: 19 128
					view: 243
					loop: 0
					setCycle: Walk
					setScale: Scaler 90 72 188 95
				)
				(ego reset: 1)
				(client setScript: sealTheDealScr self)
			)
		)
	)
)

(instance lamp of Prop
	(properties
		view 245
	)
)

(instance sealTheDealScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 34 5 0 2 self 240)
			)
			(1
				(messager say: 34 5 0 3 self 240)
			)
			(2
				(curRoom notify:)
				(= cycles 2)
			)
			(3
				((ScriptID 241 0)
					moveSpeed: 3
					cycleSpeed: 3
					setMotion: PolyPath 274 187 self
				)
				(Bset 12)
			)
			(4
				((ScriptID 241 0) loop: 0)
				(= cycles 2)
			)
			(5
				((ScriptID 241 0)
					moveSpeed: 6
					cycleSpeed: 6
					view: 254
					loop: 0
					cel: 0
					setCycle: End self
				)
			)
			(6
				((ScriptID 241 0) loop: 1 cel: 0 setCycle: Fwd self)
				(= seconds 4)
			)
			(7
				(messager say: 34 5 0 4 self 240)
			)
			(8 (= cycles 3))
			(9
				((ScriptID 241 0)
					loop: 0
					cel: ((ScriptID 241 0) lastCel:)
				)
				(= cycles 2)
			)
			(10
				((ScriptID 241 0) setCycle: Beg self)
			)
			(11 (= cycles 2))
			(12
				((ScriptID 241 0)
					moveSpeed: 3
					cycleSpeed: 3
					view: 243
					setCycle: Walk
					setMotion: MoveTo 274 230 self
				)
			)
			(13
				(theGame handsOn:)
				(client dispose:)
			)
		)
	)
)

(instance talkToSellerScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 241 0) loop: 0 cel: 0 setCycle: 0)
				(if (== register -1)
					(ego
						view: 272
						loop: 1
						cel: 0
						setSpeed: 6
						setCycle: End self
					)
				else
					(messager say: 4 2 register 0 self 240)
					(= state (+ state 4))
				)
			)
			(1
				(messager say: 4 0 0 0 self 240)
			)
			(2 (ego setCycle: Beg self))
			(3 (= cycles 2))
			(4 (ego reset: 1) (= cycles 2))
			(5
				(theGame handsOn:)
				(client setScript: (ScriptID 241 1))
			)
		)
	)
)

(instance lampSellerInset of Inset
	(properties
		picture 245
		hideTheCast 1
		disposeNotOnMe 1
		noun 9
	)
	
	(method (init)
		(self drawInset:)
		(super init: &rest)
		(lamps init:)
		(theGame handsOn:)
		(theIconBar
			disable: 0 3 4 5 6
			curIcon: (theIconBar at: 1)
		)
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
	)
	
	(method (doit)
		(if script (script doit:))
	)
	
	(method (dispose)
		(theIconBar enable: 6)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(super doVerb: theVerb &rest)
	)
	
	(method (onMe event)
		(InRect 83 48 232 136 (event x?) (event y?))
	)
)

(instance lamps of Feature
	(properties
		y 1
		onMeCheck $0076
	)
	
	(method (init)
		(super init: &rest)
		(self sightAngle: 26505)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(theGame givePoints: 1)
				(ego put: 19)
				(theGame handsOff:)
				(cond 
					((== noun 7) (ego get: 25))
					((not (ego has: 25)) (ego get: 25))
				)
				(= local0 1)
				(lampSellerInset dispose:)
			)
			(else 
				(if (!= theVerb 1) (= noun 34))
				(messager say: noun theVerb 0 0 0 240)
			)
		)
	)
	
	(method (onMe event)
		(= noun
			(switch (OnControl 4 (event x?) (event y?))
				(2
					(lampTradeScr register: 0)
					((inventory at: 25)
						message: 57
						noun: 25
						cel: 11
						setCursor: 990 1 11
					)
					34
				)
				(4
					(lampTradeScr register: 3)
					((inventory at: 25)
						message: 56
						noun: 24
						loop: 2
						cel: 9
						setCursor: 990 2 9
					)
					7
				)
				(8
					(lampTradeScr register: 4)
					((inventory at: 25)
						message: 58
						noun: 26
						cel: 13
						setCursor: 990 1 13
					)
					35
				)
				(16
					(lampTradeScr register: 3)
					((inventory at: 25)
						message: 59
						noun: 27
						cel: 15
						setCursor: 990 1 15
					)
					36
				)
				(32
					(lampTradeScr register: 4)
					((inventory at: 25)
						message: 60
						noun: 28
						cel: 14
						setCursor: 990 1 14
					)
					37
				)
				(64
					((inventory at: 25)
						message: 96
						noun: 65
						cel: 12
						setCursor: 990 1 12
					)
					6
				)
				(else  0)
			)
		)
	)
)
