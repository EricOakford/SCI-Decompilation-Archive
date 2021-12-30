;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12000)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use TPSound)
(use ExitFeature)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roLeaves 0
)

(local
	local0
	local1
)
(instance poWind of Prop
	(properties
		x 301
		y 100
		view 12000
	)
)

(instance poGrab of Prop
	(properties
		x 301
		y 315
		view 12000
	)
)

(instance poStomp of Prop
	(properties
		x 301
		y 315
		view 12000
		loop 4
	)
)

(instance poPeel of Prop
	(properties
		x 305
		y 315
		view 12001
	)
)

(instance soPeelInchie of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poGrab dispose:)
				(poStomp cel: 0 init: setCycle: CT 17 1 self)
			)
			(1
				(poStomp cel: 18)
				(UpdateScreenItem poStomp)
				(poPeel cel: 0 init:)
				(= ticks 1)
			)
			(2
				(messager say: 3 1 2 4 self)
				(poPeel cel: 1 setCycle: End self)
			)
			(3)
			(4
				(messager say: 3 1 2 5 self)
				(((ScriptID 64001 0) get: 3) moveTo: -3)
				(ego get: ((ScriptID 64001 0) get: 13))
			)
			(5
				(ego get: ((ScriptID 64001 1) get: 2))
				((ScriptID 64017 0) set: 11)
				(poPeel dispose:)
				(poStomp dispose:)
				(self dispose:)
			)
		)
	)
)

(instance oWindBlow of TPSound
	(properties)
)

(instance soWind of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(oWindBlow nHeight: 12001)
				(theGame handsOff:)
				(if (== local0 3)
					(messager say: 3 18 2 0 self)
				else
					(messager say: 3 18 2 1 self)
				)
			)
			(1
				(oWindBlow lThumbLoop: 12001)
				(poWind
					cel: 0
					loop: (+ 4 local0)
					init:
					setCycle: End self
				)
				(poGrab cel: 0 loop: local0 init: setCycle: End self)
				(if (== local0 3) (poGrab posn: 173 (poGrab y?)))
			)
			(2)
			(3
				(poWind dispose:)
				(cond 
					((== 1 local0)
						(foBigLeaf setPolygon: (foBigLeaf2 init: yourself:))
						(self cue:)
					)
					((== 2 local0)
						(foBigLeaf setPolygon: (foBigLeaf3 init: yourself:))
						(self cue:)
					)
					((== 3 local0) (foBigLeaf dispose:) (self setScript: soPeelInchie self))
				)
			)
			(4
				(if (< 3 local0)
					(messager say: 3 1 2 local0 self)
				else
					(self cue:)
				)
			)
			(5
				(theGame handsOn:)
				(poGrab dispose:)
				(self dispose:)
			)
		)
	)
)

(instance foBigLeaf1 of Polygon
	(properties)
	
	(method (init)
		(super
			init: 446 52 481 19 543 20 586 47 599 79 584 114 524 135 470 109
		)
	)
)

(instance foBigLeaf2 of Polygon
	(properties)
	
	(method (init)
		(super
			init:
				378
				89
				421
				93
				471
				114
				487
				165
				446
				202
				396
				217
				372
				192
				341
				163
				341
				116
		)
	)
)

(instance foBigLeaf3 of Polygon
	(properties)
	
	(method (init)
		(super
			init: 136 70 88 84 53 121 70 163 121 198 182 179 218 134 195 90
		)
	)
)

(instance foBigLeaf of Feature
	(properties)
	
	(method (init &tmp temp0)
		(self setPolygon: (foBigLeaf1 init: yourself:))
		(= local1 0)
		(= nsBottom (= nsTop (= nsRight (= nsLeft 0))))
		(super init: &rest)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(cond 
				((== theVerb 18)
					(= local1 1)
					(++ local0)
					(curRoom setScript: soWind)
					(return 1)
				)
				((== theVerb 1)
					(cond 
						(((ScriptID 64017 0) test: 11)
							(theGame handsOff:)
							(messager say: 3 1 2 1 (ScriptID 64020 0))
						)
						((not local1)
							(theGame handsOff:)
							(messager say: 3 1 5 1 (ScriptID 64020 0))
						)
						((not ((ScriptID 64017 0) test: 24))
							(theGame handsOff:)
							(messager say: 3 1 4 1 (ScriptID 64020 0))
						)
					)
					(return 1)
				)
			)
		)
	)
)

(instance foAllOthers of Feature
	(properties
		x 302
		y 151
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 16 21 22 283 590 278 586 28
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			(((ScriptID 64017 0) test: 11) (messager say: 3 1 3))
			((== theVerb 18) (messager say: 1 18 1 (Random 1 35)))
			((== theVerb 1)
				(cond 
					((not ((ScriptID 64017 0) test: 24)) (messager say: 1 1 4))
					(((ScriptID 64017 0) test: 11) (messager say: 1 1 1))
				)
			)
		)
	)
)

(instance foExit of CUExitFeature
	(properties
		unregisterDefaultHandler 10100
	)
)

(instance roLeaves of TPRoom
	(properties
		picture 12000
		style $0000
		exitStyle 0
		south 10100
	)
	
	(method (init)
		(super init: &rest)
		((ScriptID 64017 0) set: 12)
		(foExit init:)
		(theMusic pageSize: 12000)
		(foAllOthers
			init: plane
			nScrollMaxX: 1
			setVisibleRange: 1 18
		)
		(= local0 0)
		(if (not ((ScriptID 64017 0) test: 11))
			(foBigLeaf
				init: plane
				nScrollMaxX: 100
				setVisibleRange: 1 18
			)
		)
		(theGame handsOn:)
	)
	
	(method (intoPouch)
		(if
			(and
				(ego has: ((ScriptID 64001 0) get: 3))
				(not ((ScriptID 64017 0) test: 24))
			)
			(MonoOut {setting met snails flag})
			((ScriptID 64017 0) set: 24)
		else
			(ego get: ((ScriptID 64001 0) get: 3))
			(if (not ((ScriptID 64017 0) test: 24))
				(MonoOut {gimme again to set met snails flag})
			)
		)
	)
)
