;;; Sierra Script 1.0 - (do not remove this comment)
(script# 280)
(include sci.sh)
(use Main)
(use NewRoomCue)
(use Kq6Procs)
(use Scaler)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm280 0
	map 1
	shopOwner 2
	shopDoor 3
	smBird 4
	smTinderBox 5
	smFlute 6
	smPBrush 7
	proc280_8 8
	shopOwnerScr 9
	proc280_10 10
)

(local
	local0
	local1 =  -1
	local2 =  -1
	local3
	[local4 32] = [22 129 22 157 217 157 250 157 241 148 214 148 184 127 210 127 210 121 188 121 160 121 139 133 88 133 61 133 57 120 40 120]
	[local36 30] = [22 129 22 157 217 157 250 157 241 148 214 148 184 127 210 127 210 121 188 121 160 121 141 131 86 131 78 122 40 120]
	local66
	[local67 13] = [51 152 135 150 255 133 311 165 202 157 235 154 -1]
	local80
)
(procedure (proc280_8 param1)
	(if param1
		(curRoom
			addObstacle: (roomPoly type: 3 points: @local4 size: 16 yourself:)
		)
	else
		(curRoom
			addObstacle: (roomPoly type: 3 points: @local36 size: 15 yourself:)
		)
	)
)

(procedure (proc280_10 param1)
	(cond 
		((shopOwner cycler?)
			(removeOwnerScr client: removeOwnerScr register: param1)
			((shopOwner cycler?) caller: removeOwnerScr)
		)
		((== (shopOwnerScr state?) 5)
			(removeOwnerScr client: removeOwnerScr register: param1)
			(shopOwnerScr
				register: removeOwnerScr
				seconds: 0
				ticks: 1
			)
		)
		(else (shopOwner setScript: 0) (param1 cue:))
	)
)

(instance rm280 of KQ6Room
	(properties
		noun 3
		picture 280
		autoLoad 0
	)
	
	(method (init)
		(super init: &rest)
		(if (== prevRoomNum 240)
			(shopDoor cel: 5 init: setScript: doorScr)
		else
			(shopDoor init:)
		)
		(if (and (== prevRoomNum 145) (Btst 41))
			(ego posn: 190 138)
		else
			(ego posn: 51 121)
		)
		(ego
			init:
			reset:
			loop: 9
			cel: 0
			setScale: Scaler 105 90 139 121
		)
		(shopOwner init:)
		(mints init:)
		(if (== ((inventory at: 48) owner?) curRoomNum)
			(smTinderBox init:)
		)
		(if (== ((inventory at: 3) owner?) curRoomNum)
			(smPBrush init:)
		)
		(if (== ((inventory at: 27) owner?) curRoomNum)
			(smBird init:)
		)
		(if (== ((inventory at: 14) owner?) curRoomNum)
			(smFlute init:)
		)
		(if (and (Btst 29) (Btst 30) (not (ego has: 0)))
			(map init:)
		)
		(cond 
			((and (== prevRoomNum 145) (Btst 41))
				(theMusic number: 240 loop: -1 play: 70)
				(theGame handsOn:)
			)
			((== prevRoomNum 145) (curRoom setScript: (ScriptID 282 0)))
			(
				(or
					(and (Btst 17) (not (ego has: 0)))
					(and
						(not (Btst 82))
						(not (Btst 28))
						(OneOf currentAct 4 5)
					)
					(and
						(not (Btst 82))
						(== currentAct 5)
						(Btst 15)
						(not (Bset 158))
					)
				)
				(if
				(and (not (Btst 158)) (== currentAct 5) (Btst 15))
					(Bset 158)
				)
				(Bset 28)
				((ScriptID 281 0) init: 1)
				(= local66 1)
			)
			((and (not (Btst 28)) (== currentAct 3)) (Bset 28) ((ScriptID 281 0) init: 0) (= local66 1))
		)
		(features
			add: genericFeatures eastShelf northShelf
			eachElementDo: #init
		)
		(if (not (curRoom script?))
			(= local80 1)
			(shopOwner
				setScript: shopOwnerScr 0 (if (== prevRoomNum 145) 0 else 1)
			)
		)
		(proc280_8 local66)
	)
	
	(method (dispose)
		(if (== newRoomNum 240)
			(theMusic fade: 127 15 15 0)
			((ScriptID 10 0) setIt: 512)
		else
			(theMusic fade:)
		)
		((ScriptID 281 0) dispose:)
		(DisposeScript 969)
		(DisposeScript 923)
		(DisposeScript 283)
		(DisposeScript 282)
		(Bclr 49)
		(super dispose:)
	)
	
	(method (scriptCheck param1 &tmp temp0)
		(= temp0 1)
		(if (== param1 87)
			(if (cast contains: (ScriptID 281 0))
				(curRoom setScript: (ScriptID 282 1))
			else
				(curRoom setScript: (ScriptID 282 5))
			)
			(= temp0 0)
		)
		(return temp0)
	)
)

(instance exitScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1 (ego setHeading: 270 self))
			(2 (= cycles 2))
			(3
				(ego
					setSpeed: 6
					normal: 0
					setPri: 1
					view: 2812
					loop: 0
					cel: 0
					posn: 47 125
					setScale: 0
					scaleX: 117
					scaleY: 117
					scaleSignal: 1
					setCycle: CT 2 1 self
				)
			)
			(4
				(soundFx2 number: 901 loop: 1 play:)
				(shopDoor setCycle: End self)
				(ego setCycle: End self)
			)
			(5 0)
			(6 (= cycles 1))
			(7 (curRoom newRoom: 240))
		)
	)
)

(instance doorScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 45))
			(1
				(client cycleSpeed: 8 setCycle: Beg self)
			)
			(2 (= cycles 2))
			(3
				(client stopUpd:)
				(soundFx2 number: 902 loop: 1 play: self)
			)
			(4 (= cycles 2))
			(5 (UnLoad 132 902))
			(6 (self dispose:))
		)
	)
)

(instance removeOwnerScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theDoits add: self)
				(= cycles 2)
			)
			(1
				(= client 0)
				(theDoits delete: self)
				(proc280_10 register)
				(= state -1)
			)
		)
	)
)

(instance shopOwnerScr of Script
	(properties)
	
	(method (dispose)
		(= register 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register (client view: 2841 loop: 1 cel: 0))
				(= seconds 2)
			)
			(1
				(if register
					(client setCycle: End self)
				else
					(= ticks 1)
				)
			)
			(2
				(client view: 280 loop: 8 posn: 236 127 cel: 0)
				(if (and local80 register)
					(= local80 0)
					(theGame handsOn:)
					(messager say: 1 0 (if (> currentAct 1) 67 else 66))
				)
				(client stopUpd:)
				(= seconds (Random 10 20))
			)
			(3
				(= state (- (Random 4 5) 1))
				(= cycles 1)
			)
			(4
				(client view: 285 loop: 0 cel: 0 setCycle: End self)
				(= state 1)
			)
			(5
				(client view: 2852 loop: 0 cel: 0 setCycle: End)
				(= seconds 10)
			)
			(6
				(client
					view: 2853
					loop: 0
					cel: 0
					setCycle: End (if register else removeOwnerScr)
				)
				(= state 1)
			)
		)
	)
)

(instance genericTalkScr of Script
	(properties)
	
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
				(messager say: 4 2 register 0 self)
			)
			(3
				(ego reset: 0)
				((ScriptID 280 2) setScript: (ScriptID 280 9))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance shopOwner of Actor
	(properties
		x 236
		y 127
		noun 4
		approachX 192
		approachY 133
		view 2841
		signal $5001
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 2 70)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 2)
				(cond 
					((> currentAct 1) (curRoom setScript: (ScriptID 284 5)))
					((not (Bset 64)) (curRoom setScript: genericTalkScr 0 61))
					((and (not (Btst 29)) (not (Bset 30))) (curRoom setScript: genericTalkScr 0 11))
					((not (Btst 29)) (curRoom setScript: genericTalkScr 0 12))
					((not (Bset 30)) (curRoom setScript: (ScriptID 284 0)))
					(else (curRoom setScript: genericTalkScr 0 14))
				)
			)
			((== theVerb 70)
				(cond 
					(
					(and (== currentAct 1) (not (cast contains: map))) (curRoom setScript: (ScriptID 284 1) self))
					((== currentAct 1) (curRoom setScript: (ScriptID 284 2)))
					((and (!= currentAct 1) (ego has: 0)) (curRoom setScript: (ScriptID 284 3)))
					(else (curRoom setScript: (ScriptID 284 4)))
				)
			)
			((== theVerb 40)
				(if (cast contains: map)
					(curRoom setScript: (ScriptID 283 3))
				else
					(curRoom setScript: (ScriptID 283 1))
				)
			)
			((OneOf theVerb 37 20 29 31) (curRoom setScript: (ScriptID 283 2) 0 theVerb))
			((== theVerb 66)
				(if (ego has: 0)
					(curRoom setScript: (ScriptID 285 0) 0 (Btst 36))
				else
					(curRoom setScript: (ScriptID 285 1) 0 (Btst 36))
				)
				(Bset 36)
			)
			((== theVerb 12) (curRoom setScript: (ScriptID 285 2)))
			((OneOf theVerb 13 42 27 28 39 45 7 83)
				(if (OneOf theVerb 27 28) (= theVerb 42))
				(curRoom setScript: (ScriptID 286 5) 0 theVerb)
			)
			(
				(or
					(OneOf theVerb 8 30 47 48 52 68 38 16)
					(OneOf theVerb 85 17 35 51 32)
					(and
						(OneOf theVerb 92 43 57 58 59 60 96 56)
						(= theVerb 92)
					)
				)
				(if (== theVerb 17) (= theVerb 48))
				(curRoom setScript: (ScriptID 286 4) 0 theVerb)
			)
			(
				(or
					(OneOf theVerb 5 1 49 15 18 50 54)
					(OneOf theVerb 63 65 33 69 25 94 34 24)
				)
				(if (== theVerb 50) (= theVerb 49))
				(if (== theVerb 18) (= theVerb 15))
				(super doVerb: theVerb)
			)
			(else (curRoom setScript: (ScriptID 286 4)))
		)
	)
)

(class SmallItem of View
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 9
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 190
		approachY 138
		approachDist 0
		_approachVerbs 0
		yStep 2
		view 280
		loop 6
		cel 0
		priority 0
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
	)
	
	(method (init &tmp temp0)
		(super init: &rest)
		(self approachVerbs: 5 1)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 5 1)
			(curRoom setScript: (ScriptID 283 4))
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance smPBrush of SmallItem
	(properties
		x 229
		y 117
		cel 5
		priority 12
	)
)

(instance smFlute of SmallItem
	(properties
		x 220
		y 111
		cel 2
		priority 10
	)
)

(instance smTinderBox of SmallItem
	(properties
		x 216
		y 115
		cel 4
		priority 12
	)
)

(instance smBird of SmallItem
	(properties
		x 213
		y 109
		cel 3
		priority 10
	)
)

(instance map of View
	(properties
		x 236
		y 127
		noun 6
		view 280
		loop 6
		priority 9
		signal $1011
	)
)

(instance shopDoor of Prop
	(properties
		x 39
		y 126
		noun 14
		approachX 56
		approachY 125
		view 280
		loop 2
		signal $5001
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(self setScript: exitScr)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance takeMintScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not (Bset 90)) (theGame givePoints: 1))
				(ego
					setSpeed: 7
					normal: 0
					view: 2813
					loop: 0
					posn: 176 133
					get: 23
					setCycle: End self
				)
			)
			(1 (= cycles 2))
			(2
				(ego
					reset: 0
					posn: (mints approachX?) (mints approachY?)
				)
				(= cycles 2)
			)
			(3
				(UnLoad 128 2813)
				(messager say: 7 5 34 1 self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance mints of View
	(properties
		x 201
		y 110
		noun 7
		approachX 180
		approachY 131
		view 280
		loop 3
		priority 9
		signal $4011
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if (< currentAct 3)
					(if (not (ego has: 23))
						(curRoom setScript: takeMintScr)
					else
						(messager say: noun theVerb 35)
					)
				else
					(messager say: 7 5 32)
				)
			)
			(1
				(if (< currentAct 3)
					(messager say: noun theVerb 33)
				else
					(messager say: noun theVerb 32)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance firstWallLookScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager
					say: register 1 (if (== register 17) 58 else 49) 0 self
				)
			)
			(1
				(messager say: register 1 50 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance northShelf of Feature
	(properties
		x 205
		y 85
		sightAngle 45
		onMeCheck $0080
	)
	
	(method (init)
		(super init: &rest)
		(= state (| state $0004))
	)
	
	(method (doVerb theVerb)
		(= noun 17)
		(switch theVerb
			(1
				(if (== (+ local1 1) 0)
					(curRoom setScript: firstWallLookScr 0 noun)
					(= local1 (+ local1 2))
				else
					(messager
						say:
							noun
							theVerb
							(switch (++ local1)
								(0 0)
								(1 50)
								(2 51)
								(3 52)
								(4 53)
								(5 54)
								(6 55)
								(7 56)
								(8 57)
								(9 59)
								(10 (= local1 0) 60)
							)
					)
				)
			)
			(else 
				(cond 
					(
						(or
							(and
								(== (approachCode doit: theVerb) -32768)
								(or (= theVerb 0) 1)
							)
							(== theVerb 5)
						)
						(= noun 27)
					)
					((== theVerb 2) (= noun 16))
				)
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance eastShelf of Feature
	(properties
		x 280
		y 90
		sightAngle 45
		onMeCheck $0100
	)
	
	(method (init)
		(super init: &rest)
		(= state (| state $0004))
	)
	
	(method (doVerb theVerb)
		(= noun 16)
		(switch theVerb
			(1
				(if (== (+ local2 1) 0)
					(curRoom setScript: firstWallLookScr 0 noun)
					(= local2 (+ local2 2))
				else
					(messager
						say:
							noun
							theVerb
							(switch (++ local2)
								(0 50)
								(1 51)
								(2 52)
								(3 53)
								(4 54)
								(5 55)
								(6 56)
								(7 (= local2 0) 57)
							)
					)
				)
			)
			(else 
				(if
					(or
						(and
							(== (approachCode doit: theVerb) -32768)
							(or (= theVerb 0) 1)
						)
						(== theVerb 5)
					)
					(= noun 27)
				)
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(return
			(if (super onMe: event &rest)
				(> (event x?) 119)
			else
				0
			)
		)
	)
)

(instance roomPoly of Polygon
	(properties)
)

(instance genericFeatures of Feature
	(properties
		sightAngle 45
	)
	
	(method (init)
		(super init: &rest)
		(= state (| state $0004))
	)
	
	(method (doVerb theVerb &tmp [temp0 40])
		(if
			(or
				(and
					(== (approachCode doit: theVerb) -32768)
					(& local3 $0002)
					(or (= theVerb 0) 1)
				)
				(and (== theVerb 5) (& local3 $0001))
			)
			(messager say: 27 theVerb)
		else
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (onMe event &tmp temp0)
		(= temp0 (OnControl 4 (event x?) (event y?)))
		(self x: (event x?) y: (event y?))
		(= local3 0)
		(= noun
			(switch temp0
				(2
					(if (> (event y?) 85) 8 else 15)
				)
				(4
					(if (> (event y?) 136)
						(= local3 2)
						23
					else
						(= local3 3)
						18
					)
				)
				(8
					(if (> (event y?) 146) (= local3 3) 28 else 22)
				)
				(16 36)
				(32 (= local3 3) 20)
				(256
					(if (< (event x?) 120) 32 else 0)
				)
				(64
					(if (> (event y?) 131) 29 else (= local3 2) 21)
				)
				(128
					(if (> (event y?) 131) 27 else 0)
				)
				(512
					(if (> (event x?) 176)
						(= local3 2)
						31
					else
						(= local3 2)
						24
					)
				)
				(1024
					(if (> (event x?) 98) (= local3 3) 30 else 19)
				)
				(8192 19)
				(else  0)
			)
		)
		(return noun)
	)
)
