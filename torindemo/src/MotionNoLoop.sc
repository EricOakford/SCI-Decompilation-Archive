;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20200)
(include sci.sh)
(use Main)
(use soBooglePouch)
(use TPRoom)
(use TPScript)
(use Set)
(use Script)
(use Cue)
(use Talker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use ForCount)
(use Motion)
(use Actor)
(use System)

(public
	roBitternuts 0
)

(local
	local0
	local1 =  10
	local2 =  1
	local3 =  128
)
(procedure (localproc_2316)
	((curRoom obstacles?) dispose:)
	(curRoom obstacles: 0)
	(curRoom
		addObstacle:
			((Polygon new:)
				type: 3
				init:
					473
					251
					461
					230
					407
					219
					303
					227
					279
					236
					254
					237
					227
					227
					-1
					224
					-3
					316
					361
					320
					359
					303
					537
					280
					633
					315
					632
					271
					598
					272
					560
					251
					512
					264
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 341 231 383 240 382 262 329 282 280 274 270 242
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 253 241 253 269 196 294 134 264 133 236 181 233
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 43 207 64 226 69 245 47 256 -2 233 -2 204
				yourself:
			)
	)
)

(instance voTrivetTile of View
	(properties
		approachX 487
		approachY 337
		x 485
		y 281
		priority 400
		fixPriority 1
		view 20200
		loop 7
	)
	
	(method (doVerb)
		(if ((ScriptID 64017 0) test: 63)
			(ego setScript: LOOKUP_ERROR)
		else
			(ego setScript: LOOKUP_ERROR)
		)
	)
)

(instance soAttemptPickUpTrivet of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 487 282 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 180 self)
			)
			(2
				(ego
					setPri: (ego priority?)
					setMotion:
						MoveTo
						(LOOKUP_ERROR approachX?)
						(LOOKUP_ERROR approachY?)
						self
				)
			)
			(3
				(ego hide:)
				(LOOKUP_ERROR
					setCel: 0
					setLoop: 5
					setPri: (+ LOOKUP_ERROR 1)
					posn: 484 281
					init:
					setCycle: CT 8 1 self
				)
			)
			(4
				(messager say: 4 1 11 1 self)
				(LOOKUP_ERROR setCycle: Beg self)
			)
			(5)
			(6
				({oBoogleYoYo} dispose:)
				(ego show: setMotion: LOOKUP_ERROR 487 282 self)
			)
			(7
				(ego setPri: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soAwayFromTable of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 480 287 self)
			)
			(1
				(ego setMotion: PolyPath 370 267 self)
			)
			(2 (self dispose:))
		)
	)
)

(instance soPickUpTrivet of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego oPlane: LOOKUP_ERROR self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 180 self)
			)
			(2
				(ego
					setPri: (ego priority?)
					setMotion:
						MoveTo
						(LOOKUP_ERROR approachX?)
						(LOOKUP_ERROR approachY?)
						self
				)
			)
			(3
				(ego hide:)
				(LOOKUP_ERROR
					setCel: 0
					setLoop: 5
					setPri: (+ LOOKUP_ERROR 1)
					posn: 484 281
					init:
					setCycle: CT 5 1 self
				)
			)
			(4
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR setCycle: End self)
				(messager say: 4 1 5 1 self)
			)
			(5)
			(6
				(LOOKUP_ERROR dispose:)
				(ego show: get: ((ScriptID 64001 0) get: 16))
				(self setScript: LOOKUP_ERROR self)
				((ScriptID 64017 0) set: 44)
				(messager sayRange: 4 1 5 2 9 self)
			)
			(7)
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poBoogleYoYo of Prop
	(properties
		x 94
		y 301
		view 20204
	)
)

(instance soBoogleToYoYo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(((ScriptID 64001 1) get: 3) moveTo: -3)
				(curRoom newTarget: 'LOOKUP_ERROR')
				(theSound nHeight: -5525)
				(LOOKUP_ERROR setScript: 0 setCycle: Fwd)
				(LOOKUP_ERROR nCurPosY: 139 init: setCycle: CT 24 1 self)
				((ScriptID 64018 0) hide:)
			)
			(1
				(theSound lThumbLoop: -5525)
				(LOOKUP_ERROR setCycle: End self)
			)
			(2
				(LOOKUP_ERROR dispose:)
				((ScriptID 64018 0) show:)
				(LOOKUP_ERROR setScript: LOOKUP_ERROR)
				(self dispose:)
			)
		)
	)
)

(instance foYoYo of BoogleLearnMeClass
	(properties
		approachX 94
		approachY 301
	)
)

(instance soBoogleTV of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom newTarget: LOOKUP_ERROR)
				(self dispose:)
			)
		)
	)
)

(instance foTV of BoogleLearnMeClass
	(properties
		approachX 284
		approachY 309
	)
)

(instance poBoogleShovel of Prop
	(properties
		x 255
		y 255
		view 20204
		loop 1
	)
)

(instance soBoogleToShovel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 64018 0) hide:)
				(curRoom newTarget: LOOKUP_ERROR)
				(((ScriptID 64001 1) get: 4) moveTo: -3)
				(LOOKUP_ERROR setCel: 0 init: setCycle: CT 18 1 self)
				(theSound nHeight: -5525)
			)
			(1
				(theSound lThumbLoop: -5525)
				(LOOKUP_ERROR setCycle: End self)
			)
			(2
				(LOOKUP_ERROR dispose:)
				((ScriptID 64018 0) show: bSwing: 1)
				(self dispose:)
			)
		)
	)
)

(instance foShovel of BoogleLearnMeClass
	(properties
		approachX 262
		approachY 242
	)
)

(instance soTalkToMrBitter of TPScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego nSaveTime: LOOKUP_ERROR self)
			)
			(1 (messager say: 2 1 0 0 self))
			(2 (self dispose:))
		)
	)
)

(instance poMrBitter of Prop
	(properties
		approachX 457
		approachY 243
		x 510
		y 246
		view 20203
		loop 2
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance soBobbyFidget of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (< (= temp0 (Random 1 100)) 20)
					(LOOKUP_ERROR
						setCel: 0
						setLoop: 6
						setCycle: ForwardCounter (Random 2 7) self
					)
				else
					(= ticks (Random 40 180))
				)
			)
			(1 (self changeState: 0))
		)
	)
)

(instance soBobbyRunAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LOOKUP_ERROR
					setCel: 0
					setLoop: 3
					posn: 84 249
					setPri: 301
					setCycle: End self
				)
			)
			(1
				(LOOKUP_ERROR init: setCycle: End self)
				(LOOKUP_ERROR hide:)
			)
			(2
				(LOOKUP_ERROR setScript: LOOKUP_ERROR)
				(LOOKUP_ERROR dispose:)
			)
		)
	)
)

(instance soMrBitterFidget of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(cond 
					((< (= temp0 (Random 1 100)) 15) (LOOKUP_ERROR setCel: 0 setLoop: 1 setCycle: End self))
					((< temp0 30) (LOOKUP_ERROR setCel: 0 setLoop: 2 setCycle: End self))
					(else (= ticks (Random 40 180)))
				)
			)
			(1 (self changeState: 0))
		)
	)
)

(instance soApproachMrsBitternut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> (ego x?) 208)
					(ego setMotion: PolyPath 269 289 self)
				else
					(ego setMotion: PolyPath 158 296 self)
				)
			)
			(1
				(if (> (ego x?) 208)
					(ego setHeading: 315 self)
				else
					(ego setHeading: 45 self)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance soTalkMrsBitternut of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: LOOKUP_ERROR self)
			)
			(1
				(messager say: 1 1 global289 0 self)
				(if (< global289 9) (++ global289))
			)
			(2 (self dispose:))
		)
	)
)

(instance soPointAtBag of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LOOKUP_ERROR setCel: 0 setLoop: 3 setCycle: CT 3 1 self)
			)
			(1 (= ticks 70))
			(2
				(LOOKUP_ERROR setCycle: CT 0 -1 self)
			)
			(3 (self dispose:))
		)
	)
)

(instance soFirstTalk of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 1 1 3 1 self)
			)
			(1
				(messager say: 1 1 3 2 self)
				(= ticks 70)
			)
			(2
				(LOOKUP_ERROR setCel: 0 setLoop: 3 setCycle: CT 5 1 self)
			)
			(3)
			(4
				(messager say: 1 1 3 3 self)
				(LOOKUP_ERROR setCycle: End self)
			)
			(5)
			(6
				(messager sayRange: 1 1 3 4 5 self)
				(LOOKUP_ERROR setCel: 0 setLoop: 6 setCycle: CT 4 1 self)
			)
			(7 (= ticks 60))
			(8
				(LOOKUP_ERROR setCycle: End self)
			)
			(9)
			(10
				(messager sayRange: 1 1 3 6 7 self)
				(self setScript: LOOKUP_ERROR self)
			)
			(11)
			(12
				(messager sayRange: 1 1 4 1 2 self)
			)
			(13
				(localproc_2316)
				(messager say: 1 1 4 3 self)
				(self setScript: (ScriptID 64018 2) self)
				(LOOKUP_ERROR setScript: LOOKUP_ERROR self)
			)
			(14)
			(15)
			(16
				(messager sayRange: 1 1 4 4 6 self)
			)
			(17
				(messager sayRange: 1 1 4 7 8 self)
				(LOOKUP_ERROR
					setCel: 8
					setLoop: 4
					setPri: 320
					setCycle: End self
				)
				(LOOKUP_ERROR setLoop: 8 show:)
			)
			(18)
			(19
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init:)
				((ScriptID 64018 0) bSwing: 1)
				(theGame handsOn:)
				(= global289 6)
				(self dispose:)
			)
		)
	)
)

(instance soGiveInvite of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 158 296 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 45 self)
			)
			(2
				(messager say: 1 34 0 1 self)
			)
			(3
				(ego hide:)
				(LOOKUP_ERROR
					loop: 4
					cel: 0
					setPri: -1
					posn: (ego x?) (ego y?)
					setScale:
					scaleX: (ego scaleX?)
					scaleY: (ego scaleY?)
					init:
					setCycle: CT 20 1 self
				)
			)
			(4
				(ego put: ((ScriptID 64001 0) get: 24))
				((ScriptID 64017 0) set: 63)
				(LOOKUP_ERROR setCycle: End self)
				(messager sayRange: 1 34 0 2 5 self)
			)
			(5)
			(6
				(theGame handsOn:)
				(LOOKUP_ERROR dispose:)
				(ego show:)
				(self dispose:)
			)
		)
	)
)

(instance poMrsBitter of Prop
	(properties
		approachX 69
		approachY 235
		x 202
		y 258
		view 20201
	)
	
	(method (init)
		(= signal (| signal $1000))
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(if (== global289 3)
			(ego setScript: LOOKUP_ERROR)
		else
			(ego setScript: LOOKUP_ERROR)
		)
	)
)

(instance poMrsBitterChair of Prop
	(properties
		x 202
		y 258
		view 20201
	)
	
	(method (init)
		(= signal (| signal $1000))
		(super init: &rest)
		(self setVisibleRange: 1 34)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego setScript: LOOKUP_ERROR)
			)
			(34
				(ego setScript: LOOKUP_ERROR)
			)
		)
	)
)

(instance soTouchBobby of TPScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(self setScript: (ScriptID 64007 1) self self)
			)
			(1 (messager say: 3 1 0 1 self))
			(2)
			(3
				(messager sayRange: 3 1 0 2 6 self)
				(LOOKUP_ERROR setTotalWidth: 1)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poBobby of Prop
	(properties
		approachX 69
		approachY 235
		x 276
		y 240
		view 20202
	)
	
	(method (init)
		(= signal (| signal $1000))
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance soTouchBobbyFidget of TPScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 122 305 self)
			)
			(1 (ego setHeading: 270 self))
			(2
				(self setScript: (ScriptID 64007 1) self self)
			)
			(3 (messager say: 3 1 0 1 self))
			(4)
			(5
				(messager sayRange: 3 1 0 2 6 self)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poBobbyFidget of Prop
	(properties
		approachX 41
		approachY 267
		x 24
		y 301
		view 20202
		loop 6
	)
	
	(method (init)
		(= signal (| signal $1000))
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance soExitTorin of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(SetDebug)
		(switch (= state newState)
			(0
				(self setScript: (ScriptID 64018 1) self)
			)
			(1
				(ego setMotion: PolyPath 61 231 self)
			)
			(2 (ego setHeading: 315 self))
			(3
				(ego hide:)
				(LOOKUP_ERROR
					loop: 6
					cel: 0
					posn: (ego x?) (ego y?)
					init:
					setCycle: CT 7 1 self
				)
			)
			(4
				(LOOKUP_ERROR setPri: 200 setCycle: End self)
			)
			(5
				(ego
					posn: (- (LOOKUP_ERROR x?) 19) (- (LOOKUP_ERROR y?) 10)
					setPri: (LOOKUP_ERROR priority?)
					setHeading: 270 self
				)
				(LOOKUP_ERROR dispose:)
			)
			(6
				(ego setMotion: MoveTo (- (ego x?) 20) (ego y?))
				(self dispose:)
			)
		)
	)
)

(instance soExitMessage of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (messager say: 0 0 2 1 self))
			(1
				(messager say: 0 0 2 (Random 2 6) self)
			)
			(2 (messager say: 0 0 2 7 self))
			(3 (self dispose:))
		)
	)
)

(instance soExit of TPScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(curRoom setScript: LOOKUP_ERROR self)
				(self setScript: LOOKUP_ERROR self)
			)
			(1)
			(2
				(curRoom newRoom: 20100)
				(self dispose:)
			)
		)
	)
)

(instance poDoor of Prop
	(properties
		x 8
		y 140
		priority 235
		fixPriority 1
		view 20200
	)
	
	(method (init)
		(= signal (| signal $1000))
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 7 218 7 137 62 137 61 218
					yourself:
				)
		)
		(self setSpeedFraction: (ScriptID 64006 1))
	)
	
	(method (doVerb)
		(ego setScript: 'LOOKUP_ERROR')
	)
)

(instance poTorin of Prop
	(properties
		x 47
		y 214
		view 20200
		loop 1
	)
)

(instance soWhoIsIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LOOKUP_ERROR setCycle: CT 15 1 self)
			)
			(1
				(messager say: 0 0 1 6 self)
				(= ticks 80)
			)
			(2
				(LOOKUP_ERROR cycleSpeed: 9 setCycle: CT 58 1 self)
			)
			(3)
			(4
				(LOOKUP_ERROR hide:)
				(self dispose:)
			)
		)
	)
)

(instance soKnockDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theSound nHeight: 20210)
				(LOOKUP_ERROR setCel: 0 setCycle: CT 1 1 self)
			)
			(1
				(theSound lThumbLoop: 20210)
				(LOOKUP_ERROR setCycle: CT 4 1 self)
			)
			(2
				(theSound lThumbLoop: 20210)
				(LOOKUP_ERROR setCycle: CT 7 1 self)
			)
			(3
				(theSound lThumbLoop: 20210)
				(LOOKUP_ERROR setCycle: End self)
			)
			(4 (self dispose:))
		)
	)
)

(instance soTorinEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 0 0 1 17 self)
			)
			(1
				(LOOKUP_ERROR setCycle: CT 5 1 self)
			)
			(2
				(LOOKUP_ERROR setPri: 217)
				(messager say: 0 0 1 18 self)
			)
			(3
				(LOOKUP_ERROR setCycle: End self)
			)
			(4
				(LOOKUP_ERROR dispose:)
				(ego
					posn: 50 234
					loop: 4
					show:
					setMotion: MoveTo 69 235 self
				)
			)
			(5 (ego setHeading: 180 self))
			(6 (self dispose:))
		)
	)
)

(instance voLogo of View
	(properties
		x 302
		y 144
		priority 500
		fixPriority 1
		view 20205
	)
)

(instance soZoomLogo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local2 (+ local2 local1))
				(if
					(or
						(and (> local1 0) (>= local2 local3))
						(and (< local1 0) (<= local2 local3))
					)
					(self cue:)
					(return)
				)
				(LOOKUP_ERROR scaleX: local2 scaleY: local2)
				(= ticks 8)
				(= state -1)
			)
			(1 (self dispose:))
		)
	)
)

(instance soTheBitternutsIntro of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LOOKUP_ERROR setScale: maxScale: 128 init:)
				(self setScript: LOOKUP_ERROR)
				(theSound lThumbLoop: 20299 self)
				(= ticks 1)
			)
			(1
				(if (& msgType $0001)
					(= local0 (MakeMessageSubtitle curRoomNum 0 0 1 1))
				else
					(= local0 0)
				)
				((messager nPointLeft?) unselect: curRoomNum 0 0 1 1)
				(= ticks 395)
			)
			(2
				(if local0
					(local0 dispose:)
					(= local0 (MakeMessageSubtitle curRoomNum 0 0 1 2))
				)
				((messager nPointLeft?) unselect: curRoomNum 0 0 1 2)
				(= ticks 189)
			)
			(3
				(if local0
					(local0 dispose:)
					(= local0 (MakeMessageSubtitle curRoomNum 0 0 1 3))
				)
				((messager nPointLeft?) unselect: curRoomNum 0 0 1 3)
				(= ticks 262)
			)
			(4
				(if local0
					(local0 dispose:)
					(= local0 (MakeMessageSubtitle curRoomNum 0 0 1 4))
				)
				((messager nPointLeft?) unselect: curRoomNum 0 0 1 4)
				(= ticks 225)
			)
			(5
				(if local0
					(local0 dispose:)
					(= local0 (MakeMessageSubtitle curRoomNum 0 0 1 5))
				)
				((messager nPointLeft?) unselect: curRoomNum 0 0 1 5)
			)
			(6
				(if local0 (local0 dispose:))
				(LOOKUP_ERROR dispose:)
				(self dispose:)
			)
		)
	)
)

(instance soTheBitternuts of TPScript
	(properties
		oMessageList 1
		nTextWidth 1
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self sayMessage:)
				(self setScript: LOOKUP_ERROR self)
			)
			(1
				(LOOKUP_ERROR show:)
				(self setScript: LOOKUP_ERROR self)
				(LOOKUP_ERROR setScript: LOOKUP_ERROR self)
			)
			(2)
			(3
				(LOOKUP_ERROR setCycle: End self)
			)
			(4
				(LOOKUP_ERROR posn: 29 247 setCel: 0 setLoop: 2)
				(messager sayRange: 0 0 1 7 16 self)
			)
			(5
				(self setScript: LOOKUP_ERROR self)
			)
			(6
				(LOOKUP_ERROR show: setCycle: CT 3 1 self)
				(messager say: 0 0 1 19 self)
			)
			(7)
			(8
				(messager say: 0 0 1 20 self)
			)
			(9
				(LOOKUP_ERROR setCycle: End self)
				(messager sayRange: 0 0 1 21 22 self)
			)
			(10)
			(11
				(LOOKUP_ERROR
					setCel: 0
					setLoop: 1
					posn: 85 242
					setCycle: End self
				)
				(LOOKUP_ERROR setCycle: End self)
				(messager sayRange: 0 0 1 23 26 self)
			)
			(12)
			(13)
			(14
				(self rememberMessage:)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(theSound stop:)
		(LOOKUP_ERROR dispose:)
		((ScriptID 64017 0) set: 61)
		(theGame handsOn:)
		(ego posn: 69 235 setHeading: 180 setPri: -1 show:)
		(LOOKUP_ERROR hide:)
		(LOOKUP_ERROR dispose:)
		(LOOKUP_ERROR
			setLoop: 2
			setCel: (CelHigh (LOOKUP_ERROR view?) 2 0)
			posn: 29 247
		)
		(LOOKUP_ERROR
			setLoop: 1
			setCel: (CelHigh (LOOKUP_ERROR view?) 1 0)
			posn: 85 242
			show:
		)
	)
	
	(method (sayMessage)
		(theGame handsOff:)
		(ego hide:)
		(LOOKUP_ERROR show:)
		(LOOKUP_ERROR
			setCel: 0
			setLoop: 1
			setPri: 215
			init:
			hide:
		)
		(LOOKUP_ERROR
			setCel: 0
			setLoop: 0
			posn: 202 258
			cycleSpeed: 9
		)
		(LOOKUP_ERROR setCel: 0 setLoop: 0 init: hide:)
	)
)

(instance soTheBitternuts2 of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance roBitternuts of TPRoom
	(properties
		picture 20200
	)
	
	(method (init)
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 68 234 68 236 70 236 70 234
					yourself:
				)
		)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init: setScript: LOOKUP_ERROR)
		(if (not ((ScriptID 64017 0) test: 44))
			(LOOKUP_ERROR init: setVisibleRange: 1)
		)
		(if (not (ego has: ((ScriptID 64001 1) get: 3)))
			(LOOKUP_ERROR init: LOOKUP_ERROR)
			(self setDefault: LOOKUP_ERROR)
		)
		(LOOKUP_ERROR init: LOOKUP_ERROR)
		(self setDefault: LOOKUP_ERROR)
		(if (not (ego has: ((ScriptID 64001 1) get: 4)))
			(LOOKUP_ERROR init: LOOKUP_ERROR)
			(self setDefault: LOOKUP_ERROR)
		)
		(ego
			oPanner:
			setScaler: Scaler 140 85 301 236
			posn: 50 234
			setHeading: 135
			init:
			hide:
		)
		(theGame handsOff:)
		(if (not ((ScriptID 64017 0) test: 61))
			(= global289 3)
			(curRoom setScript: LOOKUP_ERROR)
		else
			(curRoom setScript: LOOKUP_ERROR)
		)
	)
	
	(method (dispose)
		(theSound lThumbLoop: 11102)
		(super dispose:)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 0) get: 24))
		((ScriptID 64017 0) set: 61)
	)
)

(class MotionNoLoop of Obj
	(properties
		scratch 0
		client 0
		caller 0
		x 0
		y 0
		dx 0
		dy 0
		b-moveCnt 0
		b-i1 0
		b-i2 0
		b-di 0
		b-xAxis 0
		b-incr 0
		completed 0
		xLast 0
		yLast 0
	)
	
	(method (init theClient theX theY theCaller &tmp temp0 clientCycler)
		(if (>= argc 1)
			(= client theClient)
			(if (>= argc 2)
				(= x theX)
				(if (>= argc 3)
					(= y theY)
					(if (>= argc 4) (= caller theCaller))
				)
			)
		)
		(= completed 0)
		(= xLast (client x?))
		(= yLast (client y?))
		(= b-moveCnt (+ 1 (client moveSpeed?) gameTime))
		(if (= clientCycler (client cycler?))
			(clientCycler cycleCnt: b-moveCnt)
		)
		(InitBresen self)
	)
	
	(method (doit &tmp [temp0 6])
		(if
		(>= (Abs (- gameTime b-moveCnt)) (client moveSpeed?))
			(= b-moveCnt gameTime)
			(DoBresen self)
		)
	)
	
	(method (moveDone)
		(= completed 1)
		(self motionCue:)
	)
	
	(method (setTarget theX theY)
		(if argc (= x theX) (= y theY))
	)
	
	(method (onTarget)
		(return (if (== (client x?) x) (== (client y?) y) else 0))
	)
	
	(method (motionCue)
		(client mover: 0)
		(if (and completed caller)
			(if (not cuees) (= cuees (Set new:)))
			(cuees add: ((Cue new:) cuee: caller yourself:))
		)
		(self dispose:)
	)
)

(class MoveToNoLoop of MotionNoLoop
	(properties
		scratch 0
		client 0
		caller 0
		x 0
		y 0
		dx 0
		dy 0
		b-moveCnt 0
		b-i1 0
		b-i2 0
		b-di 0
		b-xAxis 0
		b-incr 0
		completed 0
		xLast 0
		yLast 0
	)
	
	(method (onTarget)
		(return
			(if (<= (Abs (- (client x?) x)) (client xStep?))
				(<= (Abs (- (client y?) y)) (client yStep?))
			else
				0
			)
		)
	)
)
