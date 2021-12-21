;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30200)
(include sci.sh)
(use Main)
(use ScrollExit)
(use TPRoom)
(use TPScript)
(use TPSound)
(use n64896)
(use Plane)
(use Talker)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roPergolaCity 0
)

(instance poTorinBreaksFree of Prop
	(properties
		x 139
		y 244
		view 30200
		loop 1
	)
)

(instance poPergolansRun of Prop
	(properties
		x 140
		y 208
		view 30200
		loop 3
	)
)

(instance poSmetana of Prop
	(properties
		x 220
		y 123
		view 30200
		loop 4
	)
)

(instance oGasp of TPSound
	(properties)
)

(instance oScream of TPSound
	(properties)
)

(instance soTorinBreaksFree of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(LOOKUP_ERROR lThumbLoop: 30205)
				(messager say: 1 1 1 1 self)
			)
			(2
				(messager say: 1 1 1 2 self)
				(theSound lThumbLoop: 30201)
				(LOOKUP_ERROR setCycle: End self)
				(LOOKUP_ERROR setCycle: End)
			)
			(3
				(LOOKUP_ERROR lThumbLoop: 30202)
			)
			(4 (messager say: 1 1 1 3 self))
			(5
				(messager say: 1 1 1 4 self)
				(LOOKUP_ERROR
					posn: 192 227
					setLoop: 2
					setCel: 0
					setCycle: End self
				)
			)
			(6 (theSound lThumbLoop: 30204))
			(7
				(LOOKUP_ERROR lThumbLoop: 30203)
				(LOOKUP_ERROR dispose:)
				(ego init: setLoop: 2 posn: 181 219 oPanner: scrollTo:)
				(LOOKUP_ERROR init:)
				(messager say: 1 1 1 5 self)
			)
			(8
				(LOOKUP_ERROR setCycle: End self)
				(messager say: 1 1 1 6 self)
			)
			(9)
			(10
				(ego hide:)
				(LOOKUP_ERROR
					posn: 181 219
					setLoop: 5
					setCel: 0
					init:
					setCycle: End self
				)
			)
			(11
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(curRoom initThumb: LOOKUP_ERROR)
				(LOOKUP_ERROR init:)
				(messager sayRange: 1 1 1 7 17 self)
			)
			(12
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init: setCycle: End self)
			)
			(13
				(curRoom arrowDown: LOOKUP_ERROR)
				(LOOKUP_ERROR
					init:
					posn: 142 221
					setLoop: 3
					setCel: 0
					setCycle: End self
				)
			)
			(14
				(LOOKUP_ERROR dispose:)
				(ego show:)
				(theGame handsOn:)
				(LOOKUP_ERROR init: setCycle: Fwd)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR sitNSpin: 632 0 0 2)
				(theMusic pageSize: 30399)
				(self dispose:)
			)
		)
	)
)

(instance voSmetanaBody of View
	(properties
		x 336
		y 141
		view 30201
	)
)

(instance poTorinPicksUpSmetana of Prop
	(properties
		x 336
		y 141
		view 30201
		loop 2
	)
)

(instance toSmetana of Talker
	(properties
		x 336
		y 141
		view 30201
	)
	
	(method (init)
		(LOOKUP_ERROR hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(LOOKUP_ERROR show:)
		(super dispose: &rest)
	)
)

(instance oSmetanaCU of Plane
	(properties
		picture 30203
	)
	
	(method (init)
		(= gToSmetana LOOKUP_ERROR)
		(super
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
	)
	
	(method (dispose)
		(= gToSmetana 0)
		(super dispose: &rest)
	)
)

(instance poLeenahInBondage of Prop
	(properties
		noun 3
		case 3
		approachX 1096
		approachY 290
		x 1149
		y 264
		view 30202
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 41 73)
		(self approachVerbs: 1 2)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (== case 3)
					(ego setScript: LOOKUP_ERROR)
				else
					(messager say: noun theVerb case 0)
				)
				(switch case
					(3 (= case 4))
					(4 (= case 5))
					(5 (= case 6))
					(6 (= case 7))
					(7 (self setTotalWidth: 1))
				)
			)
			(41
				(ego setScript: LOOKUP_ERROR)
			)
			(73
				(ego setScript: LOOKUP_ERROR)
			)
		)
	)
)

(instance soTorinMeetsLeenah of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LOOKUP_ERROR hide:)
				(ego hide:)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
				(LOOKUP_ERROR setCel: 0 init: setCycle: Fwd)
				(LOOKUP_ERROR setCycle: End)
				(messager say: 0 0 3 1 self)
			)
			(1)
			(2
				(messager sayRange: 0 0 3 2 3 self)
			)
			(3
				(LOOKUP_ERROR setCycle: Beg self)
				(LOOKUP_ERROR setCycle: End self)
			)
			(4)
			(5
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(ego show:)
				(LOOKUP_ERROR show:)
				(self dispose:)
			)
		)
	)
)

(instance poTorinKneels of Prop
	(properties
		x 1096
		y 290
		view 30202
		loop 3
	)
)

(instance poLeenahScreams of Prop
	(properties
		x 1149
		y 264
		view 30202
		loop 1
	)
)

(instance poPergolansHide of Prop
	(properties
		x 1132
		y 235
		view 30202
		loop 2
	)
)

(instance poTorinShowsLocket of Prop
	(properties
		x 1096
		y 290
		view 30203
	)
)

(instance poTorinPutsAwayLocket of Prop
	(properties
		x 1096
		y 290
		view 30203
		loop 3
	)
)

(instance toLeenahReactsToLocket of Talker
	(properties
		x 1149
		y 264
		view 30203
		loop 1
	)
	
	(method (init)
		(LOOKUP_ERROR hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(LOOKUP_ERROR show:)
		(super dispose: &rest)
	)
)

(instance toTorinShowingLocket of Talker
	(properties
		x 1096
		y 290
		view 30203
		loop 2
	)
	
	(method (init)
		(LOOKUP_ERROR hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(LOOKUP_ERROR show:)
		(super dispose: &rest)
	)
)

(instance soTorinShowsClosedLocket of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager sayRange: 3 41 0 1 5 self)
			)
			(1
				(ego hide:)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
			)
			(2
				(= gToTorinPullsOutMeat LOOKUP_ERROR)
				(= gToLeenahReactsToLocket LOOKUP_ERROR)
				(messager sayRange: 3 41 0 6 8 self)
			)
			(3
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
			)
			(4
				(= gToTorinPullsOutMeat 0)
				(= gToLeenahReactsToLocket 0)
				(LOOKUP_ERROR dispose:)
				(ego show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soTorinShowsOpenLocket of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego hide:)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
			)
			(1
				(= gToTorinPullsOutMeat LOOKUP_ERROR)
				(= gToLeenahReactsToLocket LOOKUP_ERROR)
				(messager sayRange: 3 73 0 1 2 self)
			)
			(2
				(messager say: 3 73 0 3 self)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
			)
			(3)
			(4
				(LOOKUP_ERROR dispose:)
				(ego show:)
				(= gToTorinPullsOutMeat 0)
				(messager sayRange: 3 73 0 4 11 self)
			)
			(5
				(= gToLeenahReactsToLocket 0)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR setTotalWidth: 1 41)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance foKnife of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self approachVerbs: 1)
		(self nScrollMaxX: (+ 1 ({poTorinAndLeenah} priority?)))
		(= nsTop 240)
		(= nsBottom 265)
		(= nsLeft 1073)
		(= nsRight 1112)
		(= approachX (LOOKUP_ERROR approachX?))
		(= approachY (LOOKUP_ERROR approachY?))
		(= x (+ approachX 10))
		(= y (- approachY 10))
	)
	
	(method (doVerb)
		(curRoom setScript: LOOKUP_ERROR)
		(self dispose:)
	)
)

(instance poTorinCuts of Prop
	(properties
		x 1096
		y 290
		view 30204
	)
)

(instance poLeenahSitsUp of Prop
	(properties
		x 1149
		y 264
		view 30204
		loop 1
	)
)

(instance soTorinKnifesLeenah of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 4 1 0 1 self)
			)
			(1
				(ego hide:)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
				(ego get: ((ScriptID 64001 0) get: 34))
			)
			(2
				(theSound lThumbLoop: 30301)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
			)
			(3
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(curRoom initThumb: LOOKUP_ERROR)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
			)
			(4 (messager say: 4 1 0 2 self))
			(5
				(messager say: 4 1 0 3 self)
				(LOOKUP_ERROR setCycle: End self)
				(LOOKUP_ERROR setLoop: 2 setCel: 0 setCycle: End self)
			)
			(6)
			(7)
			(8
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
				(ego put: ((ScriptID 64001 0) get: 31))
				(ego put: ((ScriptID 64001 0) get: 32))
			)
			(9
				(LOOKUP_ERROR setLoop: 5 setCel: 0 setCycle: End self)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
				(messager say: 4 1 0 4 self)
			)
			(10)
			(11)
			(12
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR setLoop: 6 setCel: 0 setCycle: Fwd)
				(LOOKUP_ERROR
					view: 30206
					setLoop: 0
					setCel: 0
					init:
					setCycle: End self
				)
				(LOOKUP_ERROR
					view: 30206
					setLoop: 1
					setCel: 0
					init:
					setCycle: End self
				)
			)
			(13)
			(14
				(messager say: 4 1 0 5 self)
				(LOOKUP_ERROR
					view: 30206
					setLoop: 2
					setCel: 0
					setCycle: End self
				)
			)
			(15)
			(16
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR setLoop: 3 setCel: 0 setCycle: End self)
				(LOOKUP_ERROR setLoop: 4 setCel: 0 setCycle: End self)
			)
			(17)
			(18
				(messager sayRange: 4 1 0 6 7 self)
			)
			(19
				(curRoom arrowDown: LOOKUP_ERROR)
				(LOOKUP_ERROR setCel: 0 init: setCycle: End self)
			)
			(20
				(LOOKUP_ERROR setLoop: 6 setCel: 0 setCycle: End self)
				(messager say: 4 1 0 8 self)
			)
			(21)
			(22 (curRoom newRoom: 30300))
		)
	)
)

(instance poTorinOffers of Prop
	(properties
		x 143
		y 308
		view 30205
		loop 1
	)
)

(instance poLeenahSmiles of Prop
	(properties
		x 494
		y 309
		view 30205
	)
)

(instance poBoogleInterrupts of Prop
	(properties
		x 309
		y 313
		view 30205
		loop 4
	)
)

(instance poPlacingOfLocket of Prop
	(properties
		x 143
		y 308
		view 30205
		loop 3
	)
)

(instance poTorinAndLeenah of Prop
	(properties
		x 1126
		y 273
		view 30206
		loop 5
	)
)

(instance oPergolaScrollPlane of TorScrollPlane
	(properties
		priority 20
	)
	
	(method (nSeq)
		(AddPicAt self 30200 0 0)
		(AddPicAt self 30201 632 0)
	)
)

(instance oBLT of Plane
	(properties
		picture 30202
		priority 20
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

(instance oJungleAmbience of TPSound
	(properties)
)

(instance roPergolaCity of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(= plane
			(LOOKUP_ERROR init: 1264 (thePlane doDouble:) yourself:)
		)
		(theMusic pageSize: 30100)
		(LOOKUP_ERROR vThumbView: 30299)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 1263 291 1045 289 1029 228 644 211 426 196 0 186 0 316
					yourself:
				)
		)
		(theGame handsOff:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(curRoom setScript: LOOKUP_ERROR)
		(proc64896_1 0 10 LOOKUP_ERROR)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
