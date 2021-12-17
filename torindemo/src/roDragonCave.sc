;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20400)
(include sci.sh)
(use Main)
(use ScrollExit)
(use TPRoom)
(use Script)
(use ExitFeature)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roDragonCave 0
)

(instance soTorinLeaveRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if ((ScriptID 64017 0) test: 68)
					(if ((ScriptID 64017 0) test: 69)
						(self setScript: soBoogleWormOut self)
					else
						(self setScript: soBoogleWormOutNoPoo self)
					)
				else
					(self cue:)
				)
			)
			(1
				(curRoom newRoom: 20100)
				(self dispose:)
			)
		)
	)
)

(instance foToCliffExit of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 3))
		(= nsTop 0)
		(= nsBottom 314)
		(= nsLeft 920)
		(= nsRight 946)
	)
	
	(method (doVerb)
		(ego setScript: soTorinLeaveRoom)
	)
)

(instance foDarkness of Feature
	(properties)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						368
						148
						372
						175
						322
						208
						284
						268
						220
						312
						2
						312
						2
						4
						174
						4
						286
						115
						312
						156
						324
						132
						370
						148
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (not ((ScriptID 64017 0) test: 68))
					(ego setScript: soOpenPooDoor)
				)
			)
			(43
				(if (not ((ScriptID 64017 0) test: 68))
					(if ((ScriptID 64017 0) test: 69)
						(messager say: 1 43 2 0)
					else
						(ego setScript: soBoogleWormIn)
					)
				)
			)
			(45
				(if ((ScriptID 64017 0) test: 68)
					(if ((ScriptID 64017 0) test: 69)
						(voDarkness hide:)
						(poBoogle show:)
					else
						(ego setScript: soBoogleLamp)
					)
				)
			)
		)
	)
)

(instance voDarkness of View
	(properties
		priority 400
		fixPriority 1
		view 20403
	)
	
	(method (init)
		(super init: &rest)
		(foDarkness init: oDragonScrollPlane)
		(self show:)
	)
	
	(method (show)
		(super show:)
		(if (ego has: ((ScriptID 64001 0) get: 17))
			(if ((ScriptID 64017 0) test: 68)
				(foDarkness setVisibleRange: 45)
				(foDarkness setTotalWidth: 43)
				(foLight setVisibleRange: 43)
				(foLight setTotalWidth: 45 44)
			else
				(foDarkness setTotalWidth: 45)
				(foDarkness setVisibleRange: 43)
				(foLight setTotalWidth: 43)
				(foLight setVisibleRange: 45 44)
			)
		else
			(foDarkness setTotalWidth: 43 45)
		)
		(foPoo dispose:)
	)
	
	(method (hide)
		(super hide:)
		(foDarkness setTotalWidth: 45)
		(foPoo init: oDragonScrollPlane)
	)
)

(instance soBoogleWormOutNoPoo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 1 43 5 1 self)
			)
			(1
				(voDarkness show:)
				(oDragonScrollPlane sitNSpin: 316 0 self)
				(poBoogle
					setCel: 0
					setLoop: 3
					posn: 373 169
					show:
					setCycle: End self
				)
			)
			(2)
			(3
				(messager say: 1 43 5 2 self)
			)
			(4
				((ScriptID 64017 0) clear: 68)
				(voDarkness show:)
				(poBoogle dispose:)
				((ScriptID 64018 0) posn: 882 214 show:)
				(self dispose:)
			)
		)
	)
)

(instance soBoogleWormOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 3 43 6 1 self)
				(oDragonScrollPlane sitNSpin: 316 0 self)
			)
			(1)
			(2
				(ego setMotion: PolyPath 900 205 self)
			)
			(3 (ego setHeading: 270 self))
			(4
				(voDarkness show:)
				(poBoogle
					setCel: 0
					setLoop: 3
					posn: 373 169
					show:
					setCycle: End self
				)
			)
			(5
				(ego hide:)
				(poBoogle
					setCel: 0
					setLoop: 4
					posn: 900 205
					setCycle: End self
				)
				(messager say: 3 43 6 2 self)
			)
			(6)
			(7
				(poBoogle dispose:)
				(ego show:)
				((ScriptID 64017 0) set: 144)
				((ScriptID 64018 0) dispose:)
				(ego get: ((ScriptID 64001 1) get: 2))
				(ego get: ((ScriptID 64001 0) get: 26))
				((ScriptID 64017 0) set: 54)
				((ScriptID 64017 0) clear: 68)
				(voDarkness show:)
				(self dispose:)
			)
		)
	)
)

(instance poBoogle of Prop
	(properties
		x 650
		y 204
		view 20401
	)
)

(instance soBoogleLamp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 3 45 5 1 self)
			)
			(1
				(poBoogle
					setLoop: 1
					setCel: 0
					posn: 256 180
					setCycle: End
				)
				(sound1 lThumbLoop: 20403)
				(voDarkness hide:)
				((ScriptID 64017 0) set: 71)
				(messager say: 3 45 5 2 self)
			)
			(2
				(messager say: 3 45 5 3 self)
			)
			(3
				(ego get: ((ScriptID 64001 1) get: 5))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soBoogleWormIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(oDragonScrollPlane sitNSpin: 316 0 self)
				(messager say: 1 43 7 1 self)
			)
			(1)
			(2
				(if ((ScriptID 64017 0) test: 144)
					((ScriptID 64017 0) clear: 144)
					((ScriptID 64018 0)
						oPanner:
						posn: 940 216
						setHeading: 270
						init:
					)
				)
				((ScriptID 64018 0)
					bSwing: 0
					setMotion: MoveTo 650 204 self
				)
				(messager say: 1 43 7 2 self)
			)
			(3)
			(4
				((ScriptID 64018 0) setHeading: 270 self)
			)
			(5
				((ScriptID 64018 0) hide:)
				(poBoogle
					loop: 0
					cel: 0
					posn: 650 204
					init:
					setCycle: CT 9 1 self
				)
			)
			(6
				(sound1 lThumbLoop: 20404)
				(poBoogle setCycle: CT 24 1 self)
			)
			(7
				(sound1 stop:)
				(poBoogle setCycle: End self)
			)
			(8
				(messager say: 1 43 7 3 self)
				(oDragonScrollPlane sitNSpin: 0 0 self)
			)
			(9)
			(10
				(messager say: 1 43 7 4 self)
			)
			(11
				((ScriptID 64017 0) set: 70)
				((ScriptID 64017 0) set: 68)
				(voDarkness show:)
				(ego get: ((ScriptID 64001 1) get: 2))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soBoogleTakePoo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(oDragonScrollPlane sitNSpin: 0 0 self)
				(messager say: 3 44 4 1 self)
			)
			(1)
			(2
				(poBoogle setLoop: 2 setCel: 0 setCycle: End self)
			)
			(3
				(messager say: 3 44 4 2 self)
			)
			(4
				(voDarkness show:)
				(poBoogle setLoop: 1 setCel: 1 hide:)
				(sound1 lThumbLoop: 20403)
				(messager say: 3 44 4 3 self)
			)
			(5
				(sound1 lThumbLoop: 20402 self)
			)
			(6
				(ego get: ((ScriptID 64001 1) get: 4))
				((ScriptID 64017 0) set: 69)
				(voDarkness show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance foPoo of Feature
	(properties)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					init:
						308
						313
						269
						254
						216
						227
						215
						217
						185
						202
						171
						209
						160
						202
						157
						178
						140
						170
						115
						182
						101
						176
						85
						188
						69
						161
						37
						171
						31
						201
						45
						222
						58
						225
						78
						243
						97
						258
						96
						276
						145
						303
						164
						299
						165
						313
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 44)
	)
	
	(method (doVerb)
		(if ((ScriptID 64017 0) test: 68)
			(if ((ScriptID 64017 0) test: 69)
				(messager say: 3 44 2 0)
			else
				(ego setScript: soBoogleTakePoo)
			)
		)
	)
)

(instance poTorin of Prop
	(properties
		x 898
		y 214
		view 20400
	)
)

(instance soOpenPooDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(oDragonScrollPlane sitNSpin: 316 0 self)
			)
			(1
				(ego hide:)
				(poTorin init: setCycle: CT 30 1 self)
			)
			(2
				(messager say: 1 1 1 1 self)
				(poTorin setCycle: CT 48 1 self)
			)
			(3)
			(4
				(voPooDoor dispose:)
				(messager say: 1 1 1 2 self)
			)
			(5
				(poTorin setCycle: CT 56 1 self)
				(sound1 lThumbLoop: 20401)
			)
			(6
				(ego get: ((ScriptID 64001 0) get: 17))
				(poTorin setCycle: End self)
				(messager say: 1 1 1 3 self)
			)
			(7)
			(8
				(poTorin setCel: 0 setLoop: 1 setCycle: End self)
				(messager say: 1 1 1 4 self)
			)
			(9)
			(10
				(poTorin setLoop: 0 setCel: 46 setCycle: Beg self)
			)
			(11
				(voDarkness show:)
				(ego show:)
				(poTorin dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance voPooDoor of View
	(properties
		x 378
		y 158
		view 20402
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 43)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego setScript: soOpenPooDoor)
			)
			(43 (messager say: 1 43 1 0))
		)
	)
)

(instance foLight of Feature
	(properties)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						348
						136
						262
						96
						158
						0
						934
						0
						934
						316
						218
						316
						286
						259
						306
						252
						326
						199
						374
						168
						376
						148
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if ((ScriptID 64017 0) test: 69)
					(messager say: 1 1 2 0)
				else
					(messager say: 1 1 7 0)
				)
			)
			(43
				(if ((ScriptID 64017 0) test: 69)
					(theGame handsOff:)
					(ego setScript: soBoogleWormOut (ScriptID 64020 0))
				else
					(theGame handsOff:)
					(ego setScript: soBoogleWormOutNoPoo (ScriptID 64020 0))
				)
			)
			(45 (messager say: 1 45 0 0))
			(44 (messager say: 1 44 0 0))
		)
	)
)

(instance oDragonScrollPlane of TorScrollPlane
	(properties
		priority 20
	)
	
	(method (init)
		(= addBoogleFeature 0)
		(super init: &rest)
	)
	
	(method (nSeq)
		(AddPicAt self 20400 0 0)
		(AddPicAt self 20401 474 0)
	)
)

(instance roDragonCave of TPRoom
	(properties
		picture -2
	)
	
	(method (init)
		(super init: &rest)
		(= plane
			(oDragonScrollPlane
				init: 948 (thePlane doDouble:)
				yourself:
			)
		)
		(music1 pageSize: 20400)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 948 207 897 204 897 214 949 219
					yourself:
				)
		)
		((ScriptID 64017 0) clear: 68)
		(foLight init: oDragonScrollPlane)
		(voDarkness init: oDragonScrollPlane)
		(if (not (ego has: ((ScriptID 64001 0) get: 17)))
			(voPooDoor init: oDragonScrollPlane)
		)
		(foToCliffExit init:)
		(ego oPanner: setHeading: 225 posn: 915 216 init:)
		(if (!= prevRoomNum 20100)
			((ScriptID 64017 0) set: 144)
		)
		(theGame handsOn:)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 1) get: 2))
		(ego get: ((ScriptID 64001 1) get: 4))
		(ego get: ((ScriptID 64001 1) get: 5))
	)
)
