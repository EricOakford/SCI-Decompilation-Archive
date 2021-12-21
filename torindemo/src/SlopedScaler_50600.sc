;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50600)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use TiledBitmap)
(use CueMe)
(use Button)
(use ExitFeature)
(use Print)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roSlipperySlope 0
)

(local
	local0
	local1
)
(class SlopedScaler of Scaler
	(properties
		scratch 0
		client 0
		frontY 190
		backY 0
		frontSize 100
		backSize 0
		slopeNum 0
		slopeDen 0
		const 0
		id 190
		location 0
		row 0
		canReachList 0
		oShard 0
		bLit 0
	)
	
	(method (init theClient theFrontSize theId theFrontY theBackSize theLocation theBackY &tmp theCanReachList temp1)
		(if argc
			(= client theClient)
			(= frontSize theFrontSize)
			(= id theId)
			(= frontY theFrontY)
			(= backSize theBackSize)
			(= location theLocation)
			(= backY theBackY)
		)
		(if
			(or
				(== theId theLocation)
				(== theFrontY theBackY)
				(== theFrontSize theBackSize)
			)
			(Printf
				LOOKUP_ERROR
				theFrontSize
				theId
				theFrontY
				theBackSize
				theLocation
				theBackY
			)
			(return 0)
		)
		(= frontSize (MulDiv frontSize 128 100))
		(= backSize (MulDiv backSize 128 100))
		(= theCanReachList (- backY frontY))
		(= temp1 (- location id))
		(= bLit (- frontY (MulDiv theCanReachList id temp1)))
		(= slopeNum (- frontSize backSize))
		(= slopeDen
			(GetDistance theId theFrontY theLocation theBackY)
		)
		(= row (- temp1))
		(= canReachList theCanReachList)
		(while
		(or (> (Abs row) 128) (> (Abs canReachList) 128))
			(= row (/ row 2))
			(= canReachList (/ canReachList 2))
		)
		(= oShard
			(/
				(+ (* row row) (* canReachList canReachList))
				(* canReachList row)
			)
		)
		(return (self doit:))
	)
	
	(method (doit &tmp temp0 clientScaleX clientScaleY temp3 temp4 temp5 temp6)
		(= clientScaleX (client scaleX?))
		(= clientScaleY (client scaleY?))
		(= temp5
			(/
				(-
					bLit
					(= temp3
						(- (client y?) (MulDiv (client x?) row canReachList))
					)
				)
				oShard
			)
		)
		(= temp6 (+ (MulDiv row temp5 canReachList) temp3))
		(= temp4
			(GetDistance location backY (client x?) (client y?))
		)
		(if
			(or
				(!=
					clientScaleX
					(= temp0 (+ backSize (MulDiv temp4 slopeNum slopeDen)))
				)
				(!= clientScaleY temp0)
			)
			(client scaleX: temp0 scaleY: temp0)
		)
	)
)

(instance foPlanterExit of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 4))
		(self setRect: 0 0 15 316)
	)
	
	(method (doVerb)
		(ego setMotion: PolyPath 0 300 self)
	)
	
	(method (cue)
		(curRoom newRoom: -15136)
	)
)

(instance foTenebrousLSExit of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 3))
		(self setRect: 615 0 632 316)
	)
	
	(method (doVerb)
		(ego setMotion: PolyPath 632 300 self)
	)
	
	(method (cue)
		(curRoom newRoom: -14836)
	)
)

(instance foTree of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						1
						24
						4
						3
						120
						0
						118
						147
						186
						208
						85
						227
						63
						253
						1
						251
						1
						168
						34
						144
						27
						56
					yourself:
				)
		)
		(self setVisibleRange: 1 52)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego setScript: LOOKUP_ERROR)
			)
			(52
				(if (>= global232 4)
					(ego setScript: LOOKUP_ERROR)
				else
					(Prints LOOKUP_ERROR)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance soTreeTalk of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 122 238 self)
			)
			(1 (ego setHeading: 315 self))
			(2
				(switch global232
					(0
						(messager say: 1 1 5 0)
						(++ global232)
					)
					(1
						(messager say: 1 1 6 0)
						(++ global232)
					)
					(2
						(messager say: 1 1 7 0)
						(++ global232)
					)
					(3
						(messager say: 1 1 8 0)
						(++ global232)
					)
					(4 (messager say: 1 1 9 0))
				)
				(self dispose:)
			)
		)
	)
)

(instance soGetSappyDawburr of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 122 238 self)
			)
			(1 (ego setHeading: 315 self))
			(2
				(theGame handsOff:)
				(messager say: 1 52 0 0 self)
			)
			(3
				(ego put: ((ScriptID 64001 0) get: 61))
				(ego get: ((ScriptID 64001 0) get: 62))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poTorin of Prop
	(properties
		view -14934
	)
	
	(method (init)
		(super init: &rest)
		(self setScale: 0)
	)
)

(instance aoTorin of Actor
	(properties
		view -14934
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(self setStep: 5 4)
		(self setCycle: Walk)
		(self setScaler: SlopedScaler 100 190 229 55 530 172)
	)
	
	(method (cantBeHere)
		(return 0)
	)
	
	(method (setHeading h)
		(= heading h)
	)
)

(class YesFeature of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self nScrollMaxX: 100)
	)
	
	(method (handleEvent event &tmp temp0 [temp1 2])
		(if
			(and
				((ScriptID 64017 0) test: 109)
				(self onMe: event)
				(not local0)
			)
			(= local0 1)
			(= temp0 (Random 1 9))
			(theSound
				lDownArrowLoop: 0 0 2 temp0 LOOKUP_ERROR -14936 1
			)
		)
		(super handleEvent: event &rest)
	)
)

(instance foSlope of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 130 43 353 7 466 86 522 110 629 85 628 308 292 313 231 215 131 150
					yourself:
				)
		)
		(self nScrollMaxX: 0)
		(self setVisibleRange: 1)
	)
	
	(method (handleEvent event &tmp temp0 [temp1 2])
		(if
			(and
				((ScriptID 64017 0) test: 109)
				(self onMe: event)
				(not local0)
			)
			(= local0 1)
			(= temp0 (Random 1 17))
			(theSound
				lDownArrowLoop: 0 0 3 temp0 LOOKUP_ERROR -14936 1
			)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if local1
					(curRoom setScript: LOOKUP_ERROR)
				else
					(curRoom setScript: LOOKUP_ERROR)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance oStopTalking of CueMe
	(properties)
	
	(method (cue)
		(theGame setScript: LOOKUP_ERROR)
	)
)

(instance soPauseABit of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 5))
			(1
				(= local0 0)
				(self dispose:)
			)
		)
	)
)

(instance foPath1 of YesFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setRect: 343 178 396 221)
	)
	
	(method (doVerb)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance foPath2 of YesFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setRect: 429 128 480 179)
	)
	
	(method (doVerb)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance foPath3 of YesFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setRect: 507 149 556 188)
	)
	
	(method (doVerb)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance soGetOnSlope of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 190 229 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 45 self)
			)
			(2
				(ego hide:)
				(LOOKUP_ERROR
					loop: 0
					cel: 0
					posn: 190 229
					init:
					setCycle: End self
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance soSlipAndFall of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR
					posn: (LOOKUP_ERROR x?) (LOOKUP_ERROR y?)
					loop: 2
					cel: 0
					init:
					setScaler: SlopedScaler 100 190 229 55 530 172
					setCycle: End self
				)
				(LOOKUP_ERROR hide:)
			)
			(1
				(if (not ((ScriptID 64017 0) test: 109))
					(messager say: 0 0 4 1 self)
				else
					(self cue:)
				)
			)
			(2
				(LOOKUP_ERROR loop: 3 cel: 0 setCycle: End self)
			)
			(3
				(LOOKUP_ERROR loop: 4 cel: 0 setCycle: End self)
			)
			(4
				(LOOKUP_ERROR loop: 5 cel: 0 setCycle: End self)
				(messager say: 0 0 4 2 self)
			)
			(5)
			(6
				(if ((ScriptID 64019 0) show:)
					(LOOKUP_ERROR dispose:)
					(ego posn: 190 229 setLoop: 6 show:)
					(= local1 0)
					(LOOKUP_ERROR dispose:)
					(LOOKUP_ERROR dispose:)
					(LOOKUP_ERROR init:)
					(LOOKUP_ERROR init:)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soGetOnSlopeAndSlip of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: LOOKUP_ERROR self)
			)
			(1
				(LOOKUP_ERROR posn: 342 218 init: hide:)
				(self setScript: LOOKUP_ERROR self)
			)
			(2 (self dispose:))
		)
	)
)

(instance soGetOnSlopeAndStay of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: LOOKUP_ERROR self)
			)
			(1
				(theGame handsOff:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR
					posn: 342 218
					init:
					setMotion: MoveTo 370 200 self
				)
			)
			(2
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init:)
				(= local1 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soScuttleToPos2 of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR setMotion: MoveTo 455 155 self)
			)
			(1
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soScuttleToPos3 of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR setMotion: MoveTo 530 172 self)
			)
			(1
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR
					loop: 6
					cel: 0
					posn: 530 172
					init:
					setCycle: End self
				)
			)
			(2
				(curRoom newRoom: -14836)
				(self dispose:)
			)
		)
	)
)

(instance roSlipperySlope of TPRoom
	(properties
		picture -14936
	)
	
	(method (init)
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						2
						261
						2
						310
						28
						313
						83
						288
						112
						264
						139
						256
						162
						253
						222
						234
						227
						225
						152
						214
						99
						231
						77
						252
						66
						263
					yourself:
				)
		)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(ego init: oPanner:)
		(theGame handsOn:)
		(switch prevRoomNum
			(else  (ego posn: 30 300))
		)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
		((ScriptID 64017 0) set: 109)
		(ego get: ((ScriptID 64001 0) get: 61))
	)
)

(instance voTest of TiledView
	(properties
		x 100
		y 100
		view -5524
	)
	
	(method (init)
		(super init: 308 120 &rest)
	)
)

(instance oBtnTest of TextButton
	(properties
		x 130
		y 130
		text {Test}
		fore 234
		back 210
		font 2510
		findNearestOpen -5523
		updateItemSlot -5522
	)
)
