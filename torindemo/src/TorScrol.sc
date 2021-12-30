;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50900)
(include sci.sh)
(use Main)
(use ScrollExit)
(use TPRoom)
(use Script)
(use n64896)
(use ExitFeature)
(use Plane)
(use Print)
(use Talker)
(use Scaler)
(use Osc)
(use PolyPath)
(use Polygon)
(use Grooper)
(use Motion)
(use Actor)
(use System)

(public
	roBackstageAmp 0
	toRabbitCu 1
	toRabbit 2
	toCentipede 3
	toMagicianCu 4
)

(local
	local0 =  -1
	local1 =  -1
	theGSel_1206Sel_1 =  -1
	local3
	local4
	local5 =  1
	local6
	local7
)
(procedure (localproc_02da)
	(return
		(if
			(and
				(ego has: ((ScriptID 64001 0) get: 52))
				(ego has: ((ScriptID 64001 0) get: 53))
				(ego has: ((ScriptID 64001 0) get: 46))
				local4
			)
			(theDoits add: oCarpenterHammerCuer)
			(= local6 1)
			(return 1)
		else
			(return 0)
		)
	)
)

(procedure (localproc_0386)
	(return
		(if
			(and
				(ego has: ((ScriptID 64001 0) get: 56))
				(ego has: ((ScriptID 64001 0) get: 51))
			)
			(theDoits add: oAcrobatsVanishCuer)
			(= local7 1)
			(return 1)
		else
			(return 0)
		)
	)
)

(procedure (localproc_040a param1 param2 param3 &tmp temp0)
	(if (> argc 2) (= temp0 param3) else (= temp0 0))
	(if (<= (Abs (- param1 (ego x?))) 1580)
		(= local0 param1)
		(= local1 param2)
	else
		(if (< (ego x?) 1580)
			(= local0 (- param1 3160))
		else
			(= local0 (+ param1 3160))
		)
		(= local1 param2)
	)
	(ego setMotion: PolyPath local0 local1 temp0)
)

(procedure (localproc_04b1 &tmp egoMover egoMoverCaller egoMoverX egoMoverY)
	(if
		(and
			(not (= egoMover (ego mover?)))
			((ego looper?) isMemberOf: Grooper)
		)
		(= egoMover ((ego looper?) oldMover?))
	)
	(if
		(and
			(!= local0 -1)
			egoMover
			(>= (egoMover x?) (ego x?))
		)
		(= local0 (- local0 3160))
		(= egoMoverCaller (egoMover caller?))
		(ego posn: (- (ego x?) 3160) (ego y?))
		(ego setMotion: PolyPath local0 local1 egoMoverCaller)
	else
		(ego posn: (- (ego x?) 3160) (ego y?))
		(if egoMover
			(= egoMoverX (egoMover x?))
			(= egoMoverY (egoMover y?))
			(= egoMoverCaller (egoMover caller?))
			(ego
				setMotion: PolyPath egoMoverX egoMoverY egoMoverCaller
			)
		)
	)
)

(procedure (localproc_05ef &tmp egoMover egoMoverCaller egoMoverX egoMoverY)
	(if
		(and
			(not (= egoMover (ego mover?)))
			((ego looper?) isMemberOf: Grooper)
		)
		(= egoMover ((ego looper?) oldMover?))
	)
	(if
		(and
			(!= local0 -1)
			egoMover
			(<= (egoMover x?) (ego x?))
		)
		(= local0 (+ local0 3160))
		(= egoMoverCaller (egoMover caller?))
		(ego posn: (+ (ego x?) 3160) (ego y?))
		(ego setMotion: PolyPath local0 local1 egoMoverCaller)
	else
		(ego posn: (+ (ego x?) 3160) (ego y?))
		(if egoMover
			(= egoMoverX (egoMover x?))
			(= egoMoverY (egoMover y?))
			(= egoMoverCaller (egoMover caller?))
			(ego
				setMotion: PolyPath egoMoverX egoMoverY egoMoverCaller
			)
		)
	)
)

(instance foLeftCurtain of ExitFeature
	(properties
		nsLeft 2998
		nsTop 12
		nsRight 3067
		nsBottom 239
		approachX 3034
		approachY 226
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 5))
	)
	
	(method (doVerb)
		(localproc_040a approachX approachY self)
	)
	
	(method (cue)
		(curRoom newRoom: -14536)
	)
)

(instance foRightCurtain of ExitFeature
	(properties
		nsLeft 734
		nsTop 17
		nsRight 801
		nsBottom 227
		approachX 768
		approachY 233
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 6))
	)
	
	(method (doVerb)
		(localproc_040a approachX approachY self)
	)
	
	(method (cue)
		(curRoom newRoom: -14536)
	)
)

(instance poEgoEntrance of Prop
	(properties
		x 2069
		y 207
		view -14624
	)
)

(instance poDancingCentipede of Prop
	(properties
		x 1911
		y 270
		view -14624
		loop 2
	)
)

(instance voCentipedeBody of View
	(properties
		x 1911
		y 270
		view -14624
		loop 5
	)
)

(instance toCentipede of Talker
	(properties
		x 1911
		y 270
		view -14624
		loop 1
		priority 270
	)
	
	(method (init)
		(= frame voCentipedeBody)
		(super init: &rest)
	)
)

(instance soEgoEntrance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego bMouseDown: 0)
				(oBackstageScrollPlane fadeRel: 1756 0)
				(poEgoEntrance init:)
				(= global237 1)
				(messager sayRange: 0 0 0 1 2 self)
			)
			(1
				(poEgoEntrance setCycle: End)
				(poDancingCentipede init: setCycle: End self)
			)
			(2
				(voHat init:)
				(voCane init:)
				(poDancingCentipede
					setLoop: 3
					setCel: 0
					posn: 2102 208
					setCycle: End self
				)
			)
			(3
				(poDancingCentipede dispose:)
				(= global237 0)
				(messager say: 0 0 0 3 self)
			)
			(4
				(poEgoEntrance
					setLoop: 4
					setCel: 0
					posn: 2000 206
					setCycle: End self
				)
			)
			(5
				(ego
					posn: 2069 207
					setLoop: 2
					setScaler: Scaler 100 85 283 211
					init:
					oPanner: 1
					ignoreActors: 0
					code: oEgoCode
					lCheck: oScrollerWalkHandler
				)
				(theDoits add: oRabbitCuer)
				(theDoits add: oCarpenterCuer)
				(theDoits add: oAcrobatCuer)
				(poEgoDouble init:)
				(theGame handsOn:)
				(poEgoEntrance dispose:)
				(self dispose:)
			)
		)
	)
)

(instance oRabbitCuer of Code
	(properties)
	
	(method (doit)
		(if (and (< 1786 (ego x?)) (< (ego x?) 1960))
			(ego setMotion: 0)
			(theGame handsOff:)
			(curRoom setScript: soArchery)
			(theDoits delete: self)
			(self dispose:)
		)
	)
)

(instance oCarpenterCuer of Code
	(properties)
	
	(method (doit)
		(if
			(and
				(< 849 (oBackstageScrollPlane setMusic?))
				(< (oBackstageScrollPlane setMusic?) 1226)
			)
			(poCarpenter setScript: soCarpenterSaws)
			(theDoits delete: self)
			(self dispose:)
		)
	)
)

(instance oCarpenterHammerCuer of Code
	(properties)
	
	(method (doit)
		(if
			(or
				(< (oBackstageScrollPlane setMusic?) 577)
				(> (oBackstageScrollPlane setMusic?) 1687)
			)
			(poCarpenter
				posn: 1544 240
				setLoop: 2
				setCel: 0
				setCycle: Fwd
				setTotalWidth: 1
			)
			(voSaw init:)
			(theDoits delete: self)
			(self dispose:)
		)
	)
)

(instance oAcrobatCuer of Code
	(properties)
	
	(method (doit)
		(if
			(and
				(or
					(< (oBackstageScrollPlane setMusic?) 1157)
					(> (oBackstageScrollPlane setMusic?) 2593)
				)
				(or (< (ego x?) 1787) (> (ego x?) 2593))
			)
			(poManAcrobat init:)
			(poGirlAcrobat init:)
			(poAcrobats init: setScript: soTumbleAct)
			(theDoits delete: self)
			(self dispose:)
		)
	)
)

(instance oAcrobatsVanishCuer of Code
	(properties)
	
	(method (doit)
		(if
			(and
				(or
					(< (oBackstageScrollPlane setMusic?) 1157)
					(> (oBackstageScrollPlane setMusic?) 2593)
				)
				(or (< (ego x?) 1787) (> (ego x?) 2593))
			)
			(poAcrobats dispose:)
			(poManAcrobat dispose:)
			(poGirlAcrobat dispose:)
			(voRosin init:)
			(theDoits delete: self)
			(self dispose:)
		)
	)
)

(instance oStageManagerReadyCuer of Code
	(properties)
	
	(method (doit)
		(if
			(and
				local7
				local6
				(ego has: ((ScriptID 64001 0) get: 60))
			)
			(aoStageManager setScript: soStageManagerReady)
			(theDoits delete: self)
			(self dispose:)
		)
	)
)

(instance poRabbit of Prop
	(properties
		approachX 1859
		approachY 251
		x 1871
		y 195
		view -14623
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(localproc_040a approachX approachY)
	)
)

(instance poArcher of Prop
	(properties
		x 1741
		y 294
		view -14623
		loop 2
	)
)

(instance poRabbitQuits of Prop
	(properties
		case 1
		approachX 2031
		approachY 269
		x 1897
		y 199
		view -14621
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 57 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(curRoom setScript: soTalkToRabbit)
			)
			(57
				(curRoom setScript: soRabbitClimbsInHat)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poArcherLeaves of Prop
	(properties
		x 1725
		y 289
		view -14621
		loop 2
	)
)

(instance poArrowFlight of Prop
	(properties
		x 291
		y 269
		view -14622
	)
)

(instance toRabbit of Talker
	(properties
		x 1897
		y 199
		view -14621
		loop 1
		priority 199
	)
	
	(method (init)
		(poRabbitQuits hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(poRabbitQuits show:)
		(super dispose: &rest)
	)
)

(instance soArchery of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego bMouseDown: 0)
				(oBackstageScrollPlane sitNSpin: 1553 0 self)
			)
			(1
				(poArcher setCycle: End self)
			)
			(2
				(messager sayRange: 0 0 17 1 2 self)
			)
			(3
				(poArcher setLoop: 3 setCel: 0 setCycle: End self)
			)
			(4
				(poRabbit dispose:)
				(poArcher dispose:)
				(curRoom initThumb: oArcheryCU)
				(poArrowFlight init: setCycle: End self)
				(messager say: 0 0 17 3 self)
			)
			(5)
			(6
				(curRoom arrowDown: oArcheryCU)
				(poRabbitQuits init: setCycle: End self)
				(poArcherLeaves init:)
			)
			(7
				(= global233 2)
				(messager sayRange: 0 0 17 4 6 self)
			)
			(8
				(= global233 0)
				(poArcherLeaves setCycle: End self)
			)
			(9
				(messager say: 0 0 17 7 self)
			)
			(10
				(voBow init:)
				(poArcherLeaves
					posn: 1697 289
					setLoop: 3
					setCel: 0
					setCycle: End self
				)
			)
			(11
				(messager say: 0 0 17 8 self)
			)
			(12
				(poArcherLeaves dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soTalkToRabbit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(localproc_040a
					(poRabbitQuits approachX?)
					(poRabbitQuits approachY?)
					self
				)
			)
			(1 (ego setHeading: 315 self))
			(2
				(curRoom initThumb: oRabbitCU)
				(if (and (== (poRabbitQuits case?) 2) local3)
					(poRabbitQuits case: 4)
				)
				(messager say: 0 1 (poRabbitQuits case?) 0 self -12436)
				(switch (poRabbitQuits case?)
					(1 (poRabbitQuits case: 3))
					(3 (poRabbitQuits case: 5))
					(5 (poRabbitQuits case: 2))
				)
			)
			(3
				(curRoom arrowDown: oRabbitCU)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance voRabbitBody of View
	(properties
		x 285
		y 313
		view -14620
	)
)

(instance voRabbitMouth of View
	(properties
		x 285
		y 313
		view -14620
		loop 1
	)
)

(instance toRabbitCu of Talker
	(properties
		x 285
		y 313
		view -14620
		loop 1
		priority 400
	)
	
	(method (init)
		(voRabbitMouth hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(voRabbitMouth show:)
		(super dispose: &rest)
	)
)

(instance poTorinWithHat of Prop
	(properties
		x 2031
		y 269
		view -14620
		loop 2
	)
)

(instance poRabbitClimbsInHat of Prop
	(properties
		x 1880
		y 195
		view -14620
		loop 3
	)
)

(instance soRabbitClimbsInHat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(localproc_040a
					(poRabbitQuits approachX?)
					(poRabbitQuits approachY?)
					self
				)
			)
			(1 (ego setHeading: 315 self))
			(2
				(curRoom initThumb: oRabbitCU)
				(messager sayRange: 0 57 0 1 7 self -12436)
			)
			(3
				(curRoom arrowDown: oRabbitCU)
				(if
					(not
						(if (< 1483 (oBackstageScrollPlane setMusic?))
							(< (oBackstageScrollPlane setMusic?) 1755)
						)
					)
					(oBackstageScrollPlane sitNSpin: 1640 0 self)
				else
					(self cue:)
				)
			)
			(4
				(ego hide:)
				(poTorinWithHat init: setCycle: End self)
			)
			(5
				(poRabbitQuits dispose:)
				(poRabbitClimbsInHat init: setCycle: End self)
			)
			(6
				(poRabbitClimbsInHat dispose:)
				(poTorinWithHat
					posn: 2037 265
					setLoop: 4
					setCel: 0
					setCycle: End self
				)
			)
			(7
				(poTorinWithHat dispose:)
				(ego put: ((ScriptID 64001 0) get: 47))
				(ego get: ((ScriptID 64001 0) get: 48))
				(ego posn: 2037 265 setLoop: 7 scrollTo: show:)
				(messager say: 0 57 0 8 self -12436)
			)
			(8
				(if local3
					(messager sayRange: 0 57 0 9 10 self -12436)
				else
					(self cue:)
				)
			)
			(9
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance voSaw of View
	(properties
		approachX 1257
		approachY 235
		x 1257
		y 235
		view -14619
		loop 4
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (ego setScript: soTakeSaw))
		)
	)
)

(instance soTakeSaw of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_040a
					(voSaw approachX?)
					(voSaw approachY?)
					self
				)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theGame handsOff:)
				(voSaw dispose:)
				(Prints {Anim of Torin taking saw})
				(self cue:)
			)
			(3
				(ego get: ((ScriptID 64001 0) get: 55))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance voBoard of View
	(properties
		x 1349
		y 273
		view -14619
		loop 3
	)
)

(instance poCarpenter of Prop
	(properties
		case 18
		approachX 1196
		approachY 250
		x 1304
		y 260
		view -14619
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(ego setScript: soTalkToCarpenter)
	)
)

(instance soTalkToCarpenter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_040a
					(poCarpenter approachX?)
					(poCarpenter approachY?)
					self
				)
			)
			(1 (ego setHeading: 90 self))
			(2
				(theGame handsOff:)
				(messager say: 1 1 (poCarpenter case?) 0 self)
			)
			(3
				(if (< (poCarpenter case?) 21)
					(poCarpenter case: (+ 1 (poCarpenter case?)))
				else
					(= local4 1)
					(localproc_02da)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soCarpenterSaws of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poCarpenter setCycle: End self)
			)
			(1
				(voSaw dispose:)
				(voBoard dispose:)
				(poCarpenter
					posn: 1282 247
					setLoop: 1
					setCel: 0
					setCycle: Fwd
				)
				(self dispose:)
			)
		)
	)
)

(instance poGirlAcrobat of Prop
	(properties
		noun 8
		case 14
		x 2187
		y 298
		view -14618
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= signal (& signal $efff))
	)
	
	(method (doVerb)
		(ego setScript: soTalkToAcrobat 0 self)
	)
)

(instance poManAcrobat of Prop
	(properties
		noun 8
		case 14
		x 2233
		y 290
		view -14618
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= signal (& signal $efff))
	)
	
	(method (doVerb)
		(ego setScript: soTalkToAcrobat 0 self)
	)
)

(instance poAcrobats of Prop
	(properties
		noun 8
		case 15
		x 2293
		y 294
		view -14618
		loop 3
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= signal (& signal $efff))
	)
	
	(method (doVerb)
		(ego setScript: soTalkToAcrobat 0 self)
	)
)

(instance soTalkToAcrobat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_040a 2161 235 self)
			)
			(1
				(proc64896_0 ego register self)
			)
			(2
				(messager
					say: (register noun?) 1 (register case?) 0 self
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance soTumbleAct of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poAcrobats hide:)
				(poManAcrobat setCel: 0 show: setCycle: End self)
				(poGirlAcrobat
					posn: 2187 298
					setLoop: 0
					setCel: 0
					show:
					setCycle: End self
				)
			)
			(1)
			(2
				(poGirlAcrobat
					posn: 1965 309
					setLoop: 2
					setCel: 0
					setCycle: End self
				)
			)
			(3
				(poManAcrobat hide:)
				(poGirlAcrobat hide:)
				(poAcrobats
					posn: 2293 294
					setLoop: 3
					setCel: 0
					show:
					setCycle: End self
				)
			)
			(4
				(poAcrobats setLoop: 4 setCel: 0 show: setCycle: End self)
				(= state -1)
			)
		)
	)
)

(instance poArchivist of Prop
	(properties
		case 1
		approachX 2897
		approachY 261
		x 2916
		y 261
		view -14612
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(ego setScript: soTalkToArchivist 0 0)
	)
)

(instance voArchivistCU of View
	(properties
		x 568
		y 348
		view -14612
		loop 1
	)
	
	(method (init &tmp [temp0 3])
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(ego setScript: soTalkToArchivist 0 1)
	)
)

(instance toArchivistCU of Talker
	(properties
		x 568
		y 348
		view -14612
		loop 1
		priority 348
	)
	
	(method (init)
		(voArchivistCU hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(voArchivistCU show:)
		(super dispose: &rest)
	)
)

(instance voTorinWithArchivistMouth of View
	(properties
		x 125
		y 362
		view -14612
		loop 3
	)
)

(instance voTorinWithArchivistBody of View
	(properties
		x 125
		y 362
		view -14612
		loop 2
	)
)

(instance toTorinWithArchivist of Talker
	(properties
		x 125
		y 362
		view -14612
		loop 3
		priority 362
	)
	
	(method (init)
		(voTorinWithArchivistMouth hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(voTorinWithArchivistMouth show:)
		(super dispose: &rest)
	)
)

(instance soTalkToArchivist of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register (= state 4))
				(self cue:)
			)
			(1
				(localproc_040a
					(poArchivist approachX?)
					(poArchivist approachY?)
					self
				)
			)
			(2 (ego setHeading: 90 self))
			(3
				(theGame handsOff:)
				(if (== (poArchivist case?) 1)
					(messager sayRange: 0 1 1 1 4 self -12336)
				else
					(self cue:)
				)
			)
			(4
				(curRoom initThumb: oArchivistCU)
				(self cue:)
			)
			(5
				(theGame handsOff:)
				(switch (poArchivist case?)
					(1
						(messager say: 0 1 1 5 self -12336)
					)
					(5
						(self setScript: soRecordingSession self)
					)
					(else 
						(messager say: 0 1 (poArchivist case?) 0 self -12336)
					)
				)
			)
			(6
				(switch (poArchivist case?)
					(1 (poArchivist case: 6))
					(6 (poArchivist case: 2))
					(2 (poArchivist case: 3))
					(3 (poArchivist case: 4))
					(4 (poArchivist case: 5))
					(5 (poArchivist case: 7))
					(7
						(poArchivist case: 8)
						(curRoom arrowDown: oArchivistCU)
					)
					(else 
						(curRoom arrowDown: oArchivistCU)
					)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soRecordingSession of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager sayRange: 0 1 5 1 10 self -12336)
			)
			(1
				(curRoom arrowDown: oArchivistCU)
				(poArchivist hide:)
				(poEjectCrystal init: setCycle: End self)
			)
			(2
				(messager say: 0 1 5 11 self -12336)
			)
			(3
				(poEjectCrystal dispose:)
				(poTorinRecords init:)
				(ego hide:)
				(poArchivist show:)
				(messager say: 0 1 5 12 self -12336)
			)
			(4
				(poTorinRecords dispose:)
				(ego show:)
				(curRoom initThumb: oArchivistCU)
				(messager say: 0 1 5 13 self -12336)
			)
			(5
				(ego get: ((ScriptID 64001 0) get: 53))
				(messager say: 0 1 5 14 self -12336)
			)
			(6
				(curRoom arrowDown: oArchivistCU)
				(ego get: ((ScriptID 64001 0) get: 52))
				(localproc_02da)
				(poArchivist hide:)
				(poArchivistGivesCorder init: setCycle: End self)
			)
			(7
				(poArchivist show:)
				(ego hide:)
				(poArchivistGivesCorder dispose:)
				(poTorinTakesCorder init: setCycle: End self)
			)
			(8
				(poTorinTakesCorder dispose:)
				(ego show:)
				(self dispose:)
			)
		)
	)
)

(instance poEjectCrystal of Prop
	(properties
		x 2916
		y 261
		view -14611
	)
)

(instance poTorinRecords of Prop
	(properties
		x 2807
		y 261
		view -14611
		loop 1
	)
)

(instance poArchivistGivesCorder of Prop
	(properties
		x 2916
		y 261
		view -14611
		loop 2
	)
)

(instance poTorinTakesCorder of Prop
	(properties
		x 2807
		y 261
		view -14611
		loop 3
	)
)

(instance voMagicianBody of View
	(properties
		x 293
		y 245
		view -14617
		loop 9
	)
)

(instance voMagicianMouth of View
	(properties
		x 293
		y 245
		view -14617
		loop 10
	)
)

(instance toMagicianCu of Talker
	(properties
		x 293
		y 245
		view -14617
		loop 10
		priority 400
	)
	
	(method (init)
		(voMagicianMouth hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(voMagicianMouth show:)
		(super dispose: &rest)
	)
)

(instance poMagician of Prop
	(properties
		approachX 2528
		approachY 240
		x 2528
		y 200
		view -14616
		loop 4
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if local3
					(= case 7)
					(ego setScript: soTalkToZippy 0 theVerb)
				else
					(ego setScript: soMagicTrick)
					(= local3 1)
				)
			)
			(56
				(if ((ScriptID 64017 0) test: 134)
					(= case 5)
				else
					(= case 2)
				)
				(ego setScript: soGiveKerchiefToZippy)
			)
			(58
				(ego setScript: soGiveRabbitToZippy)
			)
			(57
				(= case 0)
				(ego setScript: soTalkToZippy 0 theVerb)
			)
			(60
				(if ((ScriptID 64017 0) test: 134)
					(ego setScript: soGiveWandToZippy)
				else
					(= case 2)
					(ego setScript: soTalkToZippy 0 theVerb)
				)
			)
		)
	)
)

(instance soTalkToZippy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_040a
					(poMagician approachX?)
					(poMagician approachY?)
					self
				)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theGame handsOff:)
				(curRoom initThumb: oMagicianCU)
				(messager
					say: 0 register (poMagician case?) 0 self -12536
				)
			)
			(3
				(curRoom arrowDown: oMagicianCU)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soMagicTrick of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_040a
					(poMagician approachX?)
					(poMagician approachY?)
					self
				)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theGame handsOff:)
				(curRoom initThumb: oMagicianCU)
				(messager sayRange: 0 1 1 1 4 self -12536)
			)
			(3
				(voMagicianBody hide:)
				(voMagicianMouth hide:)
				(poShufflesCards init: setCycle: Fwd)
				(messager say: 0 1 1 5 self -12536)
			)
			(4
				(poShufflesCards setCycle: End self)
			)
			(5
				(poShufflesCards dispose:)
				(poHandsCardsToTorin init: setCycle: End self)
			)
			(6
				(poHandsCardsToTorin dispose:)
				(voMagicianBody show:)
				(messager say: 0 1 1 6 self -12536)
			)
			(7
				(poTorinTakesCards init: setCycle: End self)
			)
			(8
				(messager sayRange: 0 1 1 9 10 self -12536)
			)
			(9
				(voMagicianBody hide:)
				(voMagicianMouth hide:)
				(poSleeveFidget setCel: 0 init: setCycle: End self)
			)
			(10
				(poSleeveFidget dispose:)
				(poPullsOutBouquet init: setCycle: CT 2 1 self)
			)
			(11
				(messager say: 0 1 1 11 self -12536)
			)
			(12
				(poPullsOutBouquet setCycle: End self)
			)
			(13
				(poPullsOutBouquet dispose:)
				(voMagicianBody show:)
				(messager sayRange: 0 1 1 12 16 self -12536)
			)
			(14
				(voMagicianBody hide:)
				(voMagicianMouth hide:)
				(poSleeveFidget setCel: 0 init: setCycle: End self)
			)
			(15
				(poSleeveFidget dispose:)
				(poPullsOutDove init: setCycle: CT 2 1 self)
			)
			(16
				(messager say: 0 1 1 17 self -12536)
			)
			(17
				(poPullsOutDove setCycle: End self)
			)
			(18
				(poPullsOutDove dispose:)
				(voMagicianBody show:)
				(messager sayRange: 0 1 1 18 19 self -12536)
			)
			(19
				(messager say: 0 1 1 21 self -12536)
			)
			(20
				(voMagicianBody hide:)
				(voMagicianMouth hide:)
				(poSleeveFidget setCel: 0 init: setCycle: End self)
			)
			(21
				(poSleeveFidget dispose:)
				(poPullsOutBook init: setCycle: CT 2 1 self)
			)
			(22
				(messager say: 0 1 1 22 self -12536)
			)
			(23
				(poPullsOutBook setCycle: End self)
			)
			(24
				(poPullsOutBook dispose:)
				(voMagicianBody show:)
				(messager sayRange: 0 1 1 23 27 self -12536)
			)
			(25
				(poTorinTakesCards dispose:)
				(poTorinReturnsCards init: setCycle: End self)
			)
			(26
				(curRoom arrowDown: oMagicianCU)
				(poMagician setVisibleRange: 56 58 57 60)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poShufflesCards of Prop
	(properties
		x 293
		y 245
		view -14617
	)
)

(instance poHandsCardsToTorin of Prop
	(properties
		x 293
		y 245
		view -14617
		loop 1
	)
)

(instance poTorinTakesCards of Prop
	(properties
		x 412
		y 313
		view -14617
		loop 6
	)
)

(instance poSleeveFidget of Prop
	(properties
		x 293
		y 245
		view -14617
		loop 2
	)
)

(instance poPullsOutBouquet of Prop
	(properties
		x 293
		y 245
		view -14617
		loop 3
	)
)

(instance poPullsOutDove of Prop
	(properties
		x 293
		y 245
		view -14617
		loop 4
	)
)

(instance poPullsOutBook of Prop
	(properties
		x 293
		y 245
		view -14617
		loop 5
	)
)

(instance poTorinReturnsCards of Prop
	(properties
		x 412
		y 313
		view -14617
		loop 7
	)
)

(instance soGiveRabbitToZippy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_040a
					(poMagician approachX?)
					(poMagician approachY?)
					self
				)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theGame handsOff:)
				(curRoom initThumb: oMagicianCU)
				(messager sayRange: 0 72 0 1 3 self -12536)
			)
			(3
				(poTorinGivesRabbit init: setCycle: End self)
			)
			(4
				(poTorinGivesRabbit dispose:)
				(ego put: ((ScriptID 64001 0) get: 48))
				(messager sayRange: 0 72 0 4 14 self -12536)
			)
			(5
				((ScriptID 64017 0) set: 134)
				(curRoom arrowDown: oMagicianCU)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soGiveWandToZippy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_040a
					(poMagician approachX?)
					(poMagician approachY?)
					self
				)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theGame handsOff:)
				(curRoom initThumb: oMagicianCU)
				(messager say: 0 60 0 1 self -12536)
			)
			(3
				(poTorinGivesWand init: setCycle: End self)
			)
			(4
				(poTorinGivesWand dispose:)
				(ego put: ((ScriptID 64001 0) get: 50))
				(messager sayRange: 0 60 0 2 7 self -12536)
			)
			(5
				((ScriptID 64017 0) set: 135)
				(if ((ScriptID 64017 0) test: 136)
					(ego setScript: soMagicianGivesBook)
				else
					(messager say: 0 60 3 0 self -12536)
				)
			)
			(6
				(curRoom arrowDown: oMagicianCU)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soGiveKerchiefToZippy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_040a
					(poMagician approachX?)
					(poMagician approachY?)
					self
				)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theGame handsOff:)
				(curRoom initThumb: oMagicianCU)
				(messager say: 0 56 (poMagician case?) 1 self -12536)
			)
			(3
				(poTorinGivesKerchief init: setCycle: End self)
			)
			(4
				(ego put: ((ScriptID 64001 0) get: 58))
				(poTorinGivesKerchief dispose:)
				((ScriptID 64017 0) set: 136)
				(if (== (poMagician case?) 5)
					(messager sayRange: 0 56 5 2 3 self -12536)
				else
					(messager sayRange: 0 56 2 2 6 self -12536)
				)
			)
			(5
				(if
					(and
						(== (poMagician case?) 5)
						((ScriptID 64017 0) test: 135)
					)
					(messager sayRange: 0 56 5 4 6 self -12536)
				else
					(self cue:)
				)
			)
			(6
				(if
					(and
						(== (poMagician case?) 5)
						((ScriptID 64017 0) test: 135)
					)
					(ego setScript: soMagicianGivesBook)
				else
					(curRoom arrowDown: oMagicianCU)
					(theGame handsOn:)
					(self dispose:)
				)
			)
		)
	)
)

(instance poTorinGivesRabbit of Prop
	(properties
		x 412
		y 313
		view -14616
	)
)

(instance poTorinGivesWand of Prop
	(properties
		x 412
		y 313
		view -14616
	)
)

(instance poTorinGivesKerchief of Prop
	(properties
		x 412
		y 313
		view -14616
	)
)

(instance soMagicianGivesBook of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 0 0 6 1 self -12536)
			)
			(1
				(voMagicianBody hide:)
				(voMagicianMouth hide:)
				(poMagicianGivesBook init: setCycle: End self)
			)
			(2
				(poMagicianGivesBook dispose:)
				(voMagicianBody show:)
				(ego get: ((ScriptID 64001 0) get: 51))
				(localproc_0386)
				(messager sayRange: 0 0 6 2 3 self -12536)
			)
			(3
				(curRoom arrowDown: oMagicianCU)
				(poMagician dispose:)
				(poHappyMagician init: setCycle: Fwd)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poMagicianGivesBook of Prop
	(properties
		x 293
		y 245
		view -14616
		loop 2
	)
)

(instance poHappyMagician of Prop
	(properties
		x 2528
		y 200
		view -14616
		loop 4
	)
)

(instance aoStageManager of Actor
	(properties
		case 9
		x 765
		y 268
		view -14615
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(self
			setStep: 5 -1
			ignoreActors: 0
			setVisibleRange: 1
			setScript: soStageManagerPaces
		)
	)
	
	(method (doVerb)
		(ego setScript: soTalkToStageManager)
		(self setScript: soStageManagerStopsToTalk)
	)
)

(instance soStageManagerPaces of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aoStageManager setLoop: local5 1 setCycle: Walk)
				(if local5
					(aoStageManager setMotion: PolyPath 1151 257 self)
				else
					(aoStageManager setMotion: PolyPath 765 268 self)
				)
			)
			(1
				(= local5 (- 1 local5))
				(aoStageManager setScript: self)
			)
		)
	)
)

(instance soStageManagerReady of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aoStageManager setTotalWidth: 1)
				(= seconds 5)
			)
			(1
				(foLeftCurtain init:)
				(foRightCurtain init:)
				(aoStageManager
					setMotion: 0
					setLoop: (+ 2 local5) 1
					setCycle: End self
				)
			)
			(2 (messager say: 0 0 1 0 self))
			(3 (= seconds 30))
			(4
				(= state (- state 2))
				(messager say: 0 0 8 0 self)
			)
		)
	)
)

(instance soStageManagerStopsToTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (<= (- (ego x?) (aoStageManager x?)) 1580)
					(= local5 1)
				else
					(= local5 0)
				)
				(aoStageManager
					setMotion: 0
					setLoop: (+ 2 local5) 1
					setCycle: End self
				)
			)
		)
	)
)

(instance soTalkToStageManager of Script
	(properties)
	
	(method (dispose)
		(aoStageManager setScript: soStageManagerPaces)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_040a
					(+ (aoStageManager x?) 100)
					(aoStageManager y?)
					self
				)
			)
			(1
				(if local5
					(ego setHeading: 270 self)
				else
					(ego setHeading: 90 self)
				)
			)
			(2
				(theGame handsOff:)
				(messager say: 3 1 (aoStageManager case?) 0 self)
			)
			(3
				(switch (aoStageManager case?)
					(9 (aoStageManager case: 10))
					(10 (aoStageManager case: 11))
					(11 (aoStageManager case: 12))
					(12 (aoStageManager case: 13))
					(13
						(aoStageManager case: 22)
						(theDoits add: oStageManagerReadyCuer)
					)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance voPoster of View
	(properties
		approachX 2488
		approachY 206
		x 2499
		y 94
		view -14636
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego setScript: soTakePoster)
			)
		)
	)
)

(instance soTakePoster of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_040a
					(voPoster approachX?)
					(voPoster approachY?)
					self
				)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theGame handsOff:)
				(voPoster dispose:)
				(ego hide:)
				(poTakePoster init: setCycle: End self)
			)
			(3
				(poTakePoster dispose:)
				(ego show:)
				(ego get: ((ScriptID 64001 0) get: 45))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poTakePoster of Prop
	(properties
		x 2488
		y 206
		view -14613
	)
)

(instance voBagpipes of View
	(properties
		approachX 2618
		approachY 183
		x 2618
		y 183
		view -14635
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego setScript: soTakeBagpipes)
			)
		)
	)
)

(instance soTakeBagpipes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_040a
					(voBagpipes approachX?)
					(voBagpipes approachY?)
					self
				)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theGame handsOff:)
				(voBagpipes dispose:)
				(self cue:)
			)
			(3
				(ego get: ((ScriptID 64001 0) get: 46))
				(localproc_02da)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance voHat of View
	(properties
		approachX 2177
		approachY 147
		x 2177
		y 147
		view -14634
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (ego setScript: soTakeHat))
		)
	)
)

(instance soTakeHat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_040a
					(voHat approachX?)
					(voHat approachY?)
					self
				)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theGame handsOff:)
				(voHat dispose:)
				(Prints {Anim of Torin taking hat})
				(self cue:)
			)
			(3
				(ego get: ((ScriptID 64001 0) get: 47))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance voCane of View
	(properties
		approachX 2138
		approachY 174
		x 2138
		y 174
		view -14632
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (ego setScript: soTakeCane))
		)
	)
)

(instance soTakeCane of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_040a
					(voCane approachX?)
					(voCane approachY?)
					self
				)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theGame handsOff:)
				(voCane dispose:)
				(Prints {Anim of Torin taking cane})
				(self cue:)
			)
			(3
				(ego get: ((ScriptID 64001 0) get: 49))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance voBow of View
	(properties
		approachX 1753
		approachY 248
		x 1753
		y 248
		view -14626
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (ego setScript: soTakeBow))
		)
	)
)

(instance soTakeBow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_040a
					(voBow approachX?)
					(voBow approachY?)
					self
				)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theGame handsOff:)
				(voBow dispose:)
				(Prints {Anim of Torin taking bow})
				(self cue:)
			)
			(3
				(ego get: ((ScriptID 64001 0) get: 56))
				(localproc_0386)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance voRosin of View
	(properties
		approachX 1927
		approachY 256
		x 1927
		y 256
		view -14625
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (ego setScript: soTakeRosin))
		)
	)
)

(instance soTakeRosin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_040a
					(voRosin approachX?)
					(voRosin approachY?)
					self
				)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theGame handsOff:)
				(voRosin dispose:)
				(Prints {Anim of Torin taking rosin})
				(self cue:)
			)
			(3
				(ego get: ((ScriptID 64001 0) get: 57))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance roBackstageAmp of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(theGame handsOff:)
		(= plane
			(oBackstageScrollPlane
				init: 3792 (thePlane doDouble:)
				yourself:
			)
		)
		(theMusic pageSize: -14636)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						257
						0
						0
						3792
						0
						3792
						257
						3050
						257
						3050
						220
						3020
						220
						3020
						257
						2960
						280
						2825
						280
						2810
						263
						2650
						250
						2660
						210
						2585
						220
						2563
						267
						2400
						268
						2375
						221
						2265
						220
						2265
						235
						2152
						235
						2129
						207
						2000
						207
						1960
						250
						1804
						259
						1750
						229
						1683
						264
						1525
						267
						1456
						266
						1367
						293
						1229
						290
						1220
						215
						962
						215
						821
						272
						768
						232
						740
						257
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 1442 316 1448 287 1502 289 1547 316 1509 335
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 1795 335 1795 294 1935 283 2184 275 2376 275 2382 335
					yourself:
				)
		)
		(voSaw init:)
		(voBoard init:)
		(voPoster init:)
		(voBagpipes init:)
		(poRabbit init: setCycle: Fwd)
		(poArcher init: setCycle: Fwd)
		(poCarpenter init: setCycle: Osc)
		(poArchivist init: setCycle: Fwd)
		(poMagician init:)
		(aoStageManager init:)
		(ego posn: 2069 207)
		(curRoom setScript: soEgoEntrance)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 0) get: 44))
	)
)

(instance oArcheryCU of Plane
	(properties
		picture 12000
	)
	
	(method (init)
		(super
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
	)
)

(instance oMagicianCU of Plane
	(properties
		picture -12536
	)
	
	(method (init)
		(super
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
		(voMagicianBody init: self)
		(voMagicianMouth init: self)
	)
)

(instance foArchivistCUExit of CUExitFeature
	(properties)
	
	(method (doVerb)
		(curRoom arrowDown: oArchivistCU)
	)
)

(instance oArchivistCU of Plane
	(properties
		picture -12336
	)
	
	(method (init)
		(super
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
		(foArchivistCUExit init:)
		(= gToArchivistCU toArchivistCU)
		(voArchivistCU init:)
		(voTorinWithArchivistBody init:)
	)
	
	(method (dispose)
		(= gToArchivistCU 0)
		(= gToTorinPullsOutMeat 0)
		(super dispose: &rest)
	)
)

(instance oRabbitCU of Plane
	(properties
		picture -12436
	)
	
	(method (init)
		(super
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
		(voRabbitBody init: self)
		(voRabbitMouth init: self)
		(= global233 1)
	)
	
	(method (dispose)
		(= global233 0)
		(super dispose: &rest)
	)
)

(instance oBackstageHandle of TorScrollHandle
	(properties
		view -5532
		loop 13
		currentX 20
		currentY 620
	)
	
	(method (init)
		(super init: &rest)
		(= currentX (+ (thePlane left:) 10))
		(= currentY
			(- (thePlane right:) (+ 10 (CelWide view loop cel)))
		)
	)
	
	(method (handleEvent event &tmp theCurrentY gimmeRelVolPercent temp2 temp3)
		(if (not gimme)
			(return
				(MonoOut
					{Attempt to handleEvent scroll handle with no scroll plane. MLE 50900.sc}
				)
			)
		)
		(if
			(and
				(self onMe: event)
				(== (event type?) evMOUSEBUTTON)
			)
			(= invSlotsX 1)
			(gOEventHandler screenLocY: self)
			(event globalize:)
			(ego bMouseDown: 0)
			(= lines (event x?))
			(= findClosestPoint (event y?))
			(= insertPoint (plane left:))
			(= deletePoint (plane top?))
		)
		(if invSlotsX
			(if (== (event type?) evMOUSERELEASE)
				(= invSlotsX 0)
				(gOEventHandler screenLocX: self)
			)
			(event globalize:)
			(if shortestDistance
				(if
					(<
						(= theCurrentY (+ (- (event x?) lines) insertPoint))
						currentX
					)
					(= theCurrentY currentY)
					(theCursor
						posn: (+ theCurrentY (- lines insertPoint)) deletePoint
					)
				)
				(if (> theCurrentY currentY)
					(= theCurrentY currentX)
					(theCursor
						posn: (+ theCurrentY (- lines insertPoint)) deletePoint
					)
				)
				(plane moveTo: theCurrentY deletePoint)
				(UpdatePlane plane)
				(= temp2 (- currentY currentX))
				(= theCurrentY (- theCurrentY currentX))
				(= gimmeRelVolPercent (gimme relVolPercent?))
				(= temp3
					(+
						(ego x?)
						-316
						(-
							(MulDiv theCurrentY gimmeRelVolPercent temp2)
							(/ gimmeRelVolPercent 2)
						)
					)
				)
				(gimme fadeRel: temp3 (gimme setAmbient?))
			)
			(event claimed: 1)
			(return 1)
		)
		(return (super handleEvent: event &rest))
	)
	
	(method (indexToCoor &tmp temp0 gimmeRelVolPercent temp2)
		(if (not gimme)
			(MonoOut
				{Attempt to resynch scroll handle with no scroll plane. DJM torscrol.sc}
			)
			(return)
		)
		(= temp2 (- currentY currentX))
		(if shortestDistance
			(= temp0
				(mod
					(+
						(/ (= gimmeRelVolPercent (gimme relVolPercent?)) 2)
						(- (gimme setMusic?) (- (ego x?) 316))
					)
					3160
				)
			)
			(plane
				moveTo:
					(+ (MulDiv temp0 temp2 gimmeRelVolPercent) currentX)
					(plane top?)
			)
			(UpdatePlane plane)
		)
	)
)

(instance oBackstageScrollPlane of TorScrollPlane
	(properties
		priority 20
	)
	
	(method (init)
		(= delBoogleFeature oBackstageHandle)
		(= oMainPlane foEBackstageScroll)
		(= addBoogleFeature foWBackstageScroll)
		(super init: &rest)
		(oBackstageHandle gimme: self init: yourself:)
		(oMainPlane init: (ScriptID 0 1))
		(addBoogleFeature init: (ScriptID 0 1))
	)
	
	(method (fadeRel theTheSetMusic theTheAudTicks &tmp theSetMusic theAudTicks bIsPausedFirst nTimeFrozenFirst temp4 temp5 torScrollPlaneFindData torScrollPlaneDoDouble)
		(if argc
			(= theSetMusic theTheSetMusic)
		else
			(= theSetMusic 0)
		)
		(if (> argc 1)
			(= theAudTicks theTheAudTicks)
		else
			(= theAudTicks 0)
		)
		(if
		(or (> theSetMusic relVolPercent) (< theSetMusic 0))
			(= theSetMusic (mod theSetMusic relVolPercent))
			(if (ego bMouseDown?)
				(cond 
					(
						(<=
							(ego x?)
							(+ 632 (CelWide (ego view?) (ego loop?) (ego cel:)))
						)
						(localproc_05ef)
					)
					(
						(>=
							(ego x?)
							(- 3160 (CelWide (ego view?) (ego loop?) (ego cel:)))
						)
						(localproc_04b1)
					)
				)
				(if (ego isNotHidden:) (UpdateScreenItem ego))
			)
		)
		(if (> theAudTicks audTicks) (= theAudTicks audTicks))
		(if (< theAudTicks 0) (= theAudTicks 0))
		(if
			(and
				(== setMusic theSetMusic)
				(== setAmbient theAudTicks)
			)
			(return)
		)
		(= setMusic theSetMusic)
		(= setAmbient theAudTicks)
		(= torScrollPlaneFindData (self findData:))
		(= torScrollPlaneDoDouble (self doDouble:))
		(= top (- reSyncVol setAmbient))
		(= left (- getCurVol setMusic))
		(self
			setRect:
				left
				top
				(- (+ left torScrollPlaneFindData) 1)
				(- (+ top torScrollPlaneDoDouble) 1)
		)
		(if newSound (newSound eachElementDo: #curSFXVolume))
		(UpdatePlane self)
		(if (or (not bIsPaused) (not nTimeFrozen)) (return))
		(= bIsPausedFirst (bIsPaused first:))
		(= nTimeFrozenFirst (nTimeFrozen first:))
		(while (and bIsPausedFirst nTimeFrozenFirst)
			(= temp4 (KList 8 bIsPausedFirst))
			(= temp5 (KList 8 nTimeFrozenFirst))
			(if (and temp4 temp5) (Eval temp4 temp5))
			(= bIsPausedFirst (bIsPaused next: bIsPausedFirst))
			(= nTimeFrozenFirst (bIsPaused next: nTimeFrozenFirst))
		)
	)
	
	(method (nSeq)
		(AddPicAt self -14632 0 0)
		(AddPicAt self -14636 632 0)
		(AddPicAt self -14635 1264 0)
		(AddPicAt self -14634 1896 0)
		(AddPicAt self -14633 2528 0)
		(AddPicAt self -14632 3160 0 0 0)
	)
)

(class InfiniteScrollExit of ScrollExit
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
		killToolHelp 1
		handlePlaneEvent 30
		addUnderMouse 40
		voMyHelp 0
		nGameSpeed 0
		autosave 0
		autorestore 0
	)
	
	(method (doVerb &tmp [temp0 2])
		(ego setMotion: PolyPath autosave (ego y?))
		(= local0 -1)
	)
)

(instance foEBackstageScroll of InfiniteScrollExit
	(properties
		killToolHelp 2
	)
)

(instance foWBackstageScroll of InfiniteScrollExit
	(properties
		killToolHelp 4
	)
)

(instance poEgoDouble of Prop
	(properties)
	
	(method (init &tmp egoReposition temp1 temp2)
		(= view (ego view?))
		(= loop (ego loop?))
		(= cel (ego cel:))
		(= y (ego y?))
		(self setScale:)
		(= scaleX (ego scaleX?))
		(= scaleY (ego scaleY?))
		(if (>= (ego x?) 1580)
			(= x (- (ego x?) 3160))
		else
			(= x (+ (ego x?) 3160))
		)
		(super init: &rest)
		(if (= egoReposition (ego reposition:))
			(= temp1 0)
			(while (= temp2 (egoReposition at: temp1))
				(self setVisibleRange: temp2)
				(++ temp1)
			)
		)
	)
	
	(method (doit &tmp egoX)
		(= view (ego view?))
		(= loop (ego loop?))
		(= cel (ego cel:))
		(= y (ego y?))
		(= scaleX (ego scaleX?))
		(= scaleY (ego scaleY?))
		(if (!= (= egoX (ego x?)) theGSel_1206Sel_1)
			(if (>= egoX 1580)
				(= x (- egoX 3160))
			else
				(= x (+ egoX 3160))
			)
			(= theGSel_1206Sel_1 egoX)
			(oBackstageHandle indexToCoor:)
		)
		(super doit: &rest)
	)
	
	(method (doVerb)
		(ego doVerb: &rest)
	)
)

(instance oEgoCode of Code
	(properties)
	
	(method (doit)
		(if (not (ego bMouseDown?))
			(cond 
				((< (ego x?) 316) (localproc_05ef))
				((> (ego x?) 3476) (localproc_04b1))
			)
		)
	)
)

(instance oScrollerWalkHandler of Code
	(properties)
	
	(method (doit param1 param2)
		(localproc_040a param1 param2)
	)
)
