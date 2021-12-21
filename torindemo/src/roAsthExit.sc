;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40900)
(include sci.sh)
(use Main)
(use TorinEgo)
(use TPRoom)
(use TPScript)
(use CueMe)
(use Print)
(use PolyPath)
(use Polygon)
(use Feature)

(public
	roAsthExit 0
)

(instance oToConsole of CueMe
	(properties)
	
	(method (cue)
		(curRoom newRoom: -22536)
	)
)

(instance foConsole of Feature
	(properties
		approachX 491
		approachY 294
		x 500
		y 294
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 618 233 571 220 532 239 556 260 598 267 615 257
					yourself:
				)
		)
		(super init: &rest)
		(super setVisibleRange: 1)
	)
	
	(method (doVerb)
		(ego nSaveTime: self LOOKUP_ERROR)
	)
)

(instance soPortalOpens of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Prints LOOKUP_ERROR)
				(self cue:)
			)
			(1
				(theGame handsOn:)
				({oTransport} init:)
				(ego oFlagValues: LOOKUP_ERROR)
				(self dispose:)
			)
		)
	)
)

(instance foPhenocryst of Feature
	(properties
		nsLeft 262
		nsTop 28
		nsRight 374
		nsBottom 149
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 13)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(curRoom setScript: LOOKUP_ERROR)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance oRSDHandler of VerbHandler
	(properties)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 13)
				(curRoom setScript: 'LOOKUP_ERROR')
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance soTransport of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Prints LOOKUP_ERROR)
				(self cue:)
			)
			(1 (curRoom newRoom: -15536))
		)
	)
)

(instance roAsthExit of TPRoom
	(properties
		picture -24636
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: -24636)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						388
						205
						290
						198
						285
						222
						191
						238
						71
						236
						64
						249
						182
						267
						186
						294
						38
						309
						3
						359
						630
						355
						623
						307
						498
						309
						499
						282
						623
						284
						623
						264
						482
						264
						432
						237
						434
						216
					yourself:
				)
		)
		(theGame handsOn:)
		(switch prevRoomNum
			(-22536
				(ego
					posn: (LOOKUP_ERROR approachX?) (LOOKUP_ERROR approachY?)
					scrollTo:
					init:
					oPanner:
					setHeading: 90
				)
				((ScriptID 64018 0) posn: 147 317 init: oPanner:)
				(if ((ScriptID 64017 0) test: 108)
					(ego setScript: LOOKUP_ERROR)
				else
					(LOOKUP_ERROR init:)
				)
			)
			(else 
				(theGame handsOff:)
				(LOOKUP_ERROR init:)
				(ego
					posn: 313 456
					init:
					oPanner:
					setMotion: PolyPath 412 322 (ScriptID 64020 0)
				)
				((ScriptID 64018 0)
					posn: 147 317
					init:
					oPanner:
					setMotion: PolyPath 108 274
				)
			)
		)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
