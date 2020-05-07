;;; Sierra Script 1.0 - (do not remove this comment)
(script# 270)
(include sci.sh)
(use Main)
(use KQ6Print)
(use NewRoomCue)
(use CartoonScript)
(use Kq6Procs)
(use Scaler)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm270 0
	bookStand 1
	shopOwner 2
	clownBook 3
	shopDoor 4
	clownChair 5
	spellBook 6
	proc270_7 7
)

(local
	[local0 32] = [268 160 -4095 4 151 168 -4095 6 141 164 -4095 4 -4094 133 160 43 152 -4095 6 -4094 37 148 34 116 -4095 4 -4094 29 110 -7 114 -4092]
	[local32 13] = [182 28 -4095 8 160 14 -4088 -4094 171 18 101 -4 -4092]
	[local45 15] = [16 36 -4095 7 14 72 -4095 5 48 69 -4095 4 -5 69 -4092]
	[local60 10] = [64 -4 -4095 9 96 17 128 -4 -4092]
	[local70 32] = [129 123 119 133 102 133 85 138 59 148 84 165 112 178 315 178 303 166 254 166 286 132 314 132 314 121 207 119 168 119 152 114]
	[local102 30] = [129 123 119 133 102 133 85 138 59 148 84 165 112 178 315 178 303 166 254 166 286 132 314 132 314 121 207 119 152 114]
	local132
	local133 =  -1
	local134
)
(procedure (proc270_7 param1)
	(if (shopOwner script?)
		(shopOwnerScr caller: param1)
		(if (== (shopOwnerScr state?) 0)
			(shopOwnerScr dispose:)
		)
	else
		(param1 cue:)
	)
)

(procedure (localproc_15b1 param1)
	(if param1
		(curRoom
			addObstacle: (roomPoly type: 3 points: @local70 size: 16 yourself:)
		)
	else
		(curRoom
			addObstacle: (roomPoly type: 3 points: @local102 size: 15 yourself:)
		)
	)
)

(instance rm270 of KQ6Room
	(properties
		noun 15
		picture 270
		autoLoad 0
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 231 124 247 129 197 136 180 132 180 123
					yourself:
				)
		)
		(super init: &rest)
		(ego
			init:
			posn: 92 144
			reset: 0
			setScale: Scaler 108 83 170 113
		)
		(features
			add:
				genericFeatures
				poemShelf
				clownChair
				readingTable
				bookStand
				shelfFeatures
				frontCounter
			eachElementDo: #init
		)
		(fire setCycle: Fwd init:)
		(shopDoor init: setCycle: Beg shopDoor)
		(if (== ((inventory at: 45) owner?) curRoomNum)
			(spellBook init:)
		)
		(if
		(not (OneOf ((inventory at: 1) owner?) ego -1))
			((ScriptID 273 0) init:)
		)
		(shopOwner init:)
		(if (not (Btst 27)) (Bclr 54) (Bset 53))
		(cond 
			((not (Btst 16))
				(Bset 27)
				((ScriptID 271 0) init:)
				(= local132 1)
				(clownBook init:)
			)
			(
				(and
					(Btst 16)
					(== currentAct 1)
					(not (Btst 26))
					(Btst 54)
				)
				(Bset 26)
				((ScriptID 274 0) init:)
				(Bclr 54)
				(Bset 53)
			)
			(
				(or
					(and
						(== currentAct 1)
						(Btst 26)
						(Btst 53)
						(not (Btst 54))
					)
					(and
						(== currentAct 2)
						(not (Btst 54))
						(not (Btst 10))
					)
					(and (not (Btst 54)) (OneOf currentAct 3 4))
				)
				((ScriptID 274 0) init:)
				(if (and (Btst 10) (OneOf currentAct 3 4))
					(curRoom setScript: (ScriptID 277 1))
				)
			)
			(else (clownBook init:))
		)
		(if (not (curRoom script?))
			(if (not ((ScriptID 10 0) isSet: 64))
				((ScriptID 10 0) setIt: 64)
				(curRoom setScript: ownerFromCounterScr)
			else
				(curRoom setScript: ownerNotAtCounterScr)
			)
		)
		(if (not (cast contains: clownBook))
			(theMusic number: 780 loop: -1 play:)
		)
		(Bset 27)
		(if (not (curRoom script?)) (theGame handsOn:))
		(localproc_15b1 local132)
	)
	
	(method (dispose)
		(if (== (theMusic number?) 240)
			((ScriptID 10 0) setIt: 512)
			(theMusic fade: 127 15 15 0)
		else
			(theMusic fade:)
		)
		(super dispose:)
		(DisposeScript 923)
		(DisposeScript 11)
		(DisposeScript 271)
		(DisposeScript 272)
		(DisposeScript 273)
		(DisposeScript 274)
		(DisposeScript 276)
	)
	
	(method (scriptCheck param1 &tmp temp0)
		(= temp0 1)
		(if ((ScriptID 10 0) isSet: 2)
			((ScriptID 10 0) clrIt: 2)
		)
		(switch param1
			(87
				(messager say: 0 0 14 0 0 899)
				(= temp0 0)
			)
			(190 (= temp0 1))
		)
		(return temp0)
	)
)

(instance spider of Actor
	(properties
		noun 22
		view 270
		loop 4
		priority 15
		signal $0810
	)
	
	(method (init)
		(super init:)
		(self setCycle: Walk)
	)
)

(instance spiderScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0 [temp1 40])
		(switch (= state newState)
			(0
				(= local133 -1)
				(= local134
					(switch (Random 0 3)
						(0 @local0)
						(1 @local32)
						(2 @local45)
						(3 @local60)
					)
				)
				(spider
					posn: (WordAt local134 (++ local133)) (WordAt local134 (++ local133))
				)
				(= cycles 2)
			)
			(1
				(switch (= temp0 (WordAt local134 (++ local133)))
					(-4088 (spider setScale: 0))
					(-4095
						(spider setLoop: (WordAt local134 (++ local133)))
						(-- state)
						(= cycles 1)
					)
					(-4094
						(spider
							posn: (WordAt local134 (++ local133)) (WordAt local134 (++ local133))
						)
						(-- state)
						(= cycles 1)
					)
					(-4092 (++ state) (= ticks 1))
					(else 
						(spider
							cycleSpeed: (Random 3 8)
							moveSpeed: (Random 3 8)
							setMotion: MoveTo temp0 (WordAt local134 (++ local133)) self
						)
					)
				)
			)
			(2
				(= state 0)
				(if (not (Random 0 1))
					(= seconds (Random 3 5))
				else
					(= cycles 1)
				)
			)
			(3
				(spider hide:)
				(= seconds (Random 5 20))
			)
			(4
				(spider show:)
				(= state -1)
				(= cycles 2)
			)
		)
	)
)

(instance exitShopScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(ego setHeading: 315 self)
				(proc270_7 self)
			)
			(2 0)
			(3
				(if (not (< (shopOwner y?) 145))
					(shopOwner view: 277 loop: 2 cel: 0 setCycle: End)
				)
				(soundFx2 number: 901 loop: 1 play:)
				(shopDoor setCycle: End self)
			)
			(4
				(shopDoor setPri: 15)
				(ego setMotion: MoveTo 70 142 self)
			)
			(5 (curRoom newRoom: 240))
		)
	)
)

(instance clownChairScr of CartoonScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setSpeed: 6
					normal: 0
					posn: 205 133
					view: 2711
					loop: 0
					cel: 0
					scaleX: 128
					scaleY: 128
					setScale: 0
					setCycle: End self
				)
			)
			(1
				(messager say: 14 5 20 1 self)
			)
			(2
				(KQ6Print
					font: userFont
					modeless: 1
					ticks: 20
					posn: -1 10
					say: 0 14 5 20 2
					init: self
				)
				(= cycles 1)
			)
			(3
				(ego loop: 3 cel: 0 setCycle: CT 1 1 self)
			)
			(4
				(clownBook dispose:)
				(= cycles 2)
			)
			(5 (ego setCycle: End self))
			(6
				(ego setSpeed: 6 loop: 1 cel: 0 setCycle: End self)
			)
			(7 (= seconds 4))
			(8
				(ego cel: 0 setCycle: End self)
			)
			(9 (= seconds 2))
			(10
				(if modelessDialog
					(KQ6Print caller: self)
				else
					(self cue:)
				)
			)
			(11
				(ego loop: 3 cel: 2 setCycle: CT 1 -1 self)
			)
			(12
				(clownBook init:)
				(= cycles 2)
			)
			(13 (ego setCycle: Beg self))
			(14
				(ego loop: 2 cel: 0 setCycle: End self)
			)
			(15
				(ego
					reset: 4
					posn: 207 133
					setScale: Scaler 108 83 170 113
				)
				(= cycles 2)
			)
			(16
				(messager say: 14 5 20 3 self)
			)
			(17
				(UnLoad 128 2711)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance randomConvScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc270_7 self)
			)
			(1
				(messager say: 18 2 23 1 self)
			)
			(2 (= cycles 2))
			(3
				(messager say: 18 2 24 (Random 1 5) self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance boringBookDoScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc270_7 self)
			)
			(1
				(messager say: 18 42 0 1 self)
			)
			(2
				(messager say: 18 register 0 0 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance ownerNotAtCounterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(if (shopDoor cycler?)
					((shopDoor cycler?) caller: self)
				else
					(= state (+ state 2))
					(self cue:)
				)
			)
			(2 (= cycles 2))
			(3
				(shopDoor stopUpd:)
				(soundFx2 number: 902 loop: 1 play: self)
			)
			(4
				(messager say: 19 0 36 0 self)
			)
			(5
				(shopOwner setScript: shopOwnerScr)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance ownerFromCounterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 270 2)
					view: 276
					loop: 0
					cel: 0
					setPri: 1
					posn: 290 134
				)
				(= cycles 2)
			)
			(1
				(if (shopDoor cycler?)
					((shopDoor cycler?) caller: self)
				else
					(self cue:)
				)
			)
			(2
				(soundFx2 number: 902 loop: 1 play:)
				((ScriptID 270 2) setCycle: End self)
			)
			(3 (= cycles 2))
			(4
				(messager say: 18 2 25 1 self)
			)
			(5 (= ticks 150))
			(6
				((ScriptID 270 2)
					posn: 290 138
					loop: 1
					cel: 0
					setPri: 8
					setCycle: End self
				)
			)
			(7
				((ScriptID 270 2)
					posn: 288 140
					loop: 2
					cel: 0
					setCycle: CT 2 1 self
				)
			)
			(8
				((ScriptID 270 2) setPri: 12 setCycle: End self)
			)
			(9 (= cycles 2))
			(10
				((ScriptID 270 2)
					loop: 3
					cel: 0
					posn: 303 151
					setCycle: End self
				)
			)
			(11 (= cycles 2))
			(12
				((ScriptID 270 2)
					view: 277
					loop: 2
					cel: 0
					setScript: shopOwnerScr
				)
				(= cycles 2)
			)
			(13
				(messager say: 18 2 25 2 self)
				(= cycles 1)
			)
			(14
				(UnLoad 128 276)
				((ScriptID 270 2) stopUpd:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance shopOwnerScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 10))
			(1
				(shopOwner
					view: 277
					loop: 0
					cel: 0
					posn: 297 159
					setCycle: End self
				)
			)
			(2 (= ticks 20))
			(3
				(shopOwner loop: 1 cel: 0 setCycle: End self)
			)
			(4 (= ticks 20))
			(5
				(shopOwner cel: 0 setCycle: End self)
			)
			(6 (= ticks 20))
			(7
				(shopOwner setCycle: Beg self)
			)
			(8
				(shopOwner
					loop: 1
					cel: (shopOwner lastCel:)
					setCycle: Beg self
				)
			)
			(9
				(shopOwner view: 277 loop: 2 cel: 0 posn: 303 151)
				(= cycles 2)
			)
			(10 (self dispose:))
		)
	)
)

(instance genericTalkScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc270_7 self)
			)
			(1
				(messager say: 18 2 register 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance shopOwner of Actor
	(properties
		x 303
		y 151
		noun 18
		sightAngle 40
		approachX 266
		approachY 151
		view 277
		loop 2
		priority 12
		signal $5011
	)
	
	(method (init param1)
		(super init: &rest)
		(self approachVerbs: 2 27)
	)
	
	(method (doVerb theVerb)
		(if ((ScriptID 10 0) isSet: 2)
			((ScriptID 10 0) clrIt: 2)
		)
		(switch theVerb
			(2
				(cond 
					((not (Btst 64))
						(Bset 64)
						(Bset 73)
						(curRoom setScript: genericTalkScr 0 26)
					)
					((and (not (Btst 16)) (Btst 73))
						(theGame givePoints: 1)
						(Bset 16)
						(Bclr 54)
						(Bset 53)
						(curRoom setScript: (ScriptID 276 3))
					)
					((not (Btst 16))
						(Bset 16)
						(theGame givePoints: 1)
						(curRoom setScript: genericTalkScr 0 22)
					)
					(else (curRoom setScript: randomConvScr))
				)
			)
			(27
				(curRoom setScript: (ScriptID 276 1))
			)
			(else 
				(if (OneOf theVerb 28 32)
					(curRoom setScript: boringBookDoScr 0 theVerb)
				else
					(if (== theVerb 67) (= theVerb 63))
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance shopDoor of Prop
	(properties
		x 69
		y 90
		noun 17
		approachX 80
		approachY 142
		view 270
		cel 4
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: exitShopScr)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(self stopUpd:)
		(soundFx2 number: 902 loop: 1 play:)
	)
)

(instance fire of Prop
	(properties
		x 225
		y 105
		noun 8
		view 270
		loop 1
		signal $4000
		detailLevel 1
	)
)

(instance bookStand of Feature
	(properties
		x 113
		y 131
		noun 2
		nsTop 116
		nsLeft 99
		nsBottom 132
		nsRight 123
		sightAngle 40
		approachX 112
		approachY 137
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 1 5)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (== ((inventory at: 1) owner?) curRoomNum)
					(messager say: 2 1 4)
				else
					(KQ6Print
						font: userFont
						posn: -1 10
						say: 0 noun theVerb 4 1
						init:
					)
					(KQ6Print
						font: userFont
						posn: -1 10
						say: 0 noun theVerb 3 1
						init:
					)
				)
			)
			(5
				(if (!= ((inventory at: 1) owner?) curRoomNum)
					(messager say: noun theVerb 3)
				else
					(curRoom setScript: (ScriptID 273 1))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event &tmp temp0)
		(if (= temp0 (super onMe: event &rest))
			(if (== (event message?) JOY_UP)
				(= approachX 134)
				(= approachY 129)
			else
				(= approachX 112)
				(= approachY 137)
			)
		)
		(return temp0)
	)
)

(instance poemShelf of Feature
	(properties
		x 302
		y 90
		noun 13
		onMeCheck $1000
		approachX 302
		approachY 124
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: (ScriptID 272 0))
			)
			(else 
				(if (or (== theVerb 2) (not (OneOf theVerb 5 1)))
					(if (!= theVerb 2) (= theVerb 0))
					(messager say: 4 theVerb)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance shelfFeatures of Feature
	(properties
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (or (== theVerb 2) (not (OneOf theVerb 5 1)))
			(= noun 4)
			(if (!= theVerb 2) (= theVerb 0))
		)
		(super doVerb: theVerb &rest)
	)
	
	(method (onMe event)
		(if _approachVerbs (= _approachVerbs 0))
		(= noun
			(switch (OnControl 4 (event x?) (event y?))
				(512
					(= x 162)
					(= y 113)
					(= approachX 159)
					(= approachY 119)
					(self approachVerbs: 5 1)
					4
				)
				(1024
					(= x 186)
					(= y 110)
					(= approachX 192)
					(= approachY 121)
					(self approachVerbs: 5 1)
					20
				)
				(2048
					(= x 265)
					(= y 116)
					(= approachX 266)
					(= approachY 121)
					(self approachVerbs: 5 1)
					16
				)
				(256
					(= x 134)
					(= y 118)
					(= approachX 142)
					(= approachY 121)
					(self approachVerbs: 5 1)
					11
				)
				(else  0)
			)
		)
	)
)

(instance genericFeatures of Feature
	(properties
		sightAngle 40
	)
	
	(method (onMe event)
		(= noun
			(switch (OnControl 4 (event x?) (event y?))
				(32 (= x 225) (= y 118) 3)
				(64 (= x 125) (= y 115) 21)
				(4 (= x 223) (= y 117) 7)
				(128 (= x 72) (= y 141) 12)
				(else  0)
			)
		)
	)
)

(instance frontCounter of Feature
	(properties
		x 281
		y 139
		z -10
		noun 5
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager
				say: 5 1 (if (== ((inventory at: 45) owner?) curRoomNum)
					6
				else
					5
				)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance clownChair of Feature
	(properties
		x 215
		y 131
		noun 14
		sightAngle 40
		onMeCheck $4008
		approachX 217
		approachY 134
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if
					(and
						(cast contains: (ScriptID 274 0))
						(not (cast contains: clownBook))
					)
					(messager say: noun theVerb 19)
				else
					(curRoom setScript: clownChairScr)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event &tmp temp0 temp1)
		(if (= temp0 (super onMe: event &rest))
			(= temp1 (OnControl 4 (event x?) (event y?)))
			(cond 
				(
				(and (== (event message?) JOY_DOWN) (== temp1 16384)) (= temp0 0))
				(
					(and
						(== (event message?) JOY_DOWN)
						(not (cast contains: (ScriptID 274 0)))
						(== temp1 8)
					)
					(self approachVerbs: 5)
				)
				(else (= _approachVerbs 0))
			)
		)
		(return temp0)
	)
)

(instance clownBook of View
	(properties
		x 227
		y 129
		z 10
		approachX 227
		approachY 132
		view 270
		loop 2
		cel 2
		priority 9
		signal $4011
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 1)
	)
	
	(method (doVerb)
		(readingTable doVerb: &rest)
	)
	
	(method (onMe event &tmp temp0)
		(if (= temp0 (super onMe: event &rest))
			(if
				(and
					(not (cast contains: (ScriptID 274 0)))
					(or
						(== (event message?) JOY_DOWN)
						(== (event message?) JOY_UP)
					)
				)
				(self approachVerbs: 5 1)
				(if (== (event message?) JOY_DOWN)
					(= approachX 217)
					(= approachY 134)
				else
					(= approachX 227)
					(= approachY 132)
				)
			else
				(= _approachVerbs 0)
			)
		)
		(return temp0)
	)
)

(instance readingTable of Feature
	(properties
		x 227
		y 129
		noun 23
		onMeCheck $2000
		approachX 227
		approachY 132
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (cast contains: (ScriptID 274 0))
					(messager say: noun theVerb 37)
				else
					(messager say: noun theVerb 38)
				)
			)
			(else 
				(clownChair doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event &tmp temp0)
		(if (= temp0 (super onMe: event &rest))
			(if
				(and
					(not (cast contains: (ScriptID 274 0)))
					(or
						(== (event message?) JOY_DOWN)
						(== (event message?) JOY_UP)
					)
				)
				(self approachVerbs: 5 1)
				(if (== (event message?) JOY_DOWN)
					(= approachX 217)
					(= approachY 134)
				else
					(= approachX 227)
					(= approachY 132)
				)
			else
				(= _approachVerbs 0)
			)
		)
		(return temp0)
	)
)

(instance spellBook of View
	(properties
		x 294
		y 150
		z 15
		noun 1
		view 270
		loop 3
		priority 13
		signal $4010
	)
	
	(method (init)
		(super init: &rest)
		(self
			approachX: (shopOwner approachX?)
			approachY: (shopOwner approachY?)
			approachVerbs: 5
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if (Btst 7)
					(messager say: noun theVerb 2)
				else
					(Bset 7)
					(theGame givePoints: 2)
					(messager say: noun theVerb 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance roomPoly of Polygon
	(properties)
)
