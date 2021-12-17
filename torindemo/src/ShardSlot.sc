;;; Sierra Script 1.0 - (do not remove this comment)
(script# 43000)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use TPSound)
(use Set)
(use Script)
(use CueMe)
(use NewUser)
(use ExitFeature)
(use Array)
(use Print)
(use Feature)
(use Actor)
(use System)

(public
	roAsthExitPuzzle 0
)

(local
	local0
	local1 =  632
	local2
	local3 =  316
	theViewShard
	local5
	local6
)
(procedure (localproc_01b0 param1 param2)
	(return (+ 1 (mod (+ param1 (- param2 1)) 8)))
)

(procedure (localproc_01d3 param1 param2)
	(oLines
		add:
			(AddLine
				(curRoom plane?)
				(param1 x?)
				(param1 y?)
				(param2 x?)
				(param2 y?)
				1000
				62
				0
				0
				1
			)
	)
	(return
		(if
			(and
				(param2 BAD SELECTOR?)
				(not (param2 BAD SELECTOR?))
			)
			(param1 BAD_SELECTOR: 1)
			(param2 BAD_SELECTOR: 1)
			(return 1)
		else
			(return 0)
		)
	)
)

(procedure (localproc_1634 param1 &tmp temp0)
	(= temp0 1)
	(while (<= temp0 8)
		(localproc_01d3 param1 (param1 BAD_SELECTOR: temp0))
		(FrameOut)
		(++ temp0)
	)
)

(instance oLines of Set
	(properties)
	
	(method (dispose &tmp temp0)
		(oSlot1 BAD_SELECTOR: 0)
		(oSlot2 BAD_SELECTOR: 0)
		(oSlot3 BAD_SELECTOR: 0)
		(oSlot4 BAD_SELECTOR: 0)
		(oSlot5 BAD_SELECTOR: 0)
		(oSlot6 BAD_SELECTOR: 0)
		(oSlot7 BAD_SELECTOR: 0)
		(while size
			(DeleteLine (= temp0 (oLines at: 0)) (curRoom plane?))
			(self delete: temp0)
		)
		(super dispose: &rest)
	)
)

(instance soDrawLight of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sound1 lThumbLoop: -24633)
				(= ticks 30)
			)
			(1 (oDrawLight cue:))
		)
	)
)

(instance oDrawLight of CueMe
	(properties)
	
	(method (cue &tmp theOSlot0 theOSlot1 temp2)
		(= theOSlot0 oSlot0)
		(= theOSlot1 oSlot1)
		(= temp2 3)
		(while (localproc_01d3 theOSlot0 theOSlot1)
			(= theOSlot0 theOSlot1)
			(= temp2
				(localproc_01b0
					((theOSlot1 BAD SELECTOR?) BAD SELECTOR?)
					temp2
				)
			)
			(= theOSlot1 (theOSlot1 BAD_SELECTOR: temp2))
		)
		(theGame handsOn:)
		(cond 
			(
				(and
					(== theOSlot1 oSlot8)
					(oSlot7 BAD SELECTOR?)
					(oSlot6 BAD SELECTOR?)
					(oSlot5 BAD SELECTOR?)
					(oSlot4 BAD SELECTOR?)
					(oSlot3 BAD SELECTOR?)
					(oSlot2 BAD SELECTOR?)
					(oSlot1 BAD SELECTOR?)
				)
				(curRoom setScript: soPuzzleSolved)
			)
			(local6 (curRoom newRoom: -24636))
		)
	)
)

(instance soPuzzleSolved of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 64017 0) set: 108)
				(sound1 lThumbLoop: -24630 self)
			)
			(1 (curRoom newRoom: -24636))
		)
	)
)

(instance foExit of CUExitFeature
	(properties
		unregisterDefaultHandler -24636
	)
	
	(method (doVerb &tmp theFoSlot1)
		(if (== gVerb 75)
			(cond 
				((not (oSlot1 BAD SELECTOR?)) (= theFoSlot1 foSlot1))
				((not (oSlot2 BAD SELECTOR?)) (= theFoSlot1 foSlot2))
				((not (oSlot3 BAD SELECTOR?)) (= theFoSlot1 foSlot3))
				((not (oSlot4 BAD SELECTOR?)) (= theFoSlot1 foSlot4))
				((not (oSlot5 BAD SELECTOR?)) (= theFoSlot1 foSlot5))
				((not (oSlot6 BAD SELECTOR?)) (= theFoSlot1 foSlot6))
				((not (oSlot7 BAD SELECTOR?)) (= theFoSlot1 foSlot7))
				(else
					(MonoOut {Alert: Have shard but no empty slot.})
					(= gVerb 1)
					(= theFoSlot1 0)
				)
			)
			(if theFoSlot1 (= local6 1) (theFoSlot1 doVerb: 75))
		else
			(super doVerb: &rest)
		)
	)
)

(class ShardSlot of Obj
	(properties
		scratch 0
		BAD_SELECTOR 0
		x 0
		y 0
		BAD_SELECTOR 0
	)
	
	(method (posn theX theY)
		(= x theX)
		(= y theY)
	)
)

(instance oSlot0 of ShardSlot
	(properties
		x 51
		y 95
	)
)

(instance oSlot1 of ShardSlot
	(properties
		x 134
		y 95
	)
	
	(method (BAD SELECTOR param1)
		(return
			(switch param1
				(1 (return oSlot13))
				(2 (return oSlot2))
				(3 (return oSlot3))
				(4 (return oSlot4))
				(5 (return oSlot18))
				(6 (return oSlot32))
				(7 (return oSlot0))
				(8 (return oSlot23))
			)
		)
	)
)

(instance oSlot2 of ShardSlot
	(properties
		x 229
		y 40
	)
	
	(method (BAD SELECTOR param1)
		(return
			(switch param1
				(1 (return oSlot14))
				(2 (return oSlot29))
				(3 (return oSlot6))
				(4 (return oSlot3))
				(5 (return oSlot4))
				(6 (return oSlot1))
				(7 (return oSlot9))
				(8 (return oSlot24))
			)
		)
	)
)

(instance oSlot3 of ShardSlot
	(properties
		x 312
		y 95
	)
	
	(method (BAD SELECTOR param1)
		(return
			(switch param1
				(1 (return oSlot15))
				(2 (return oSlot6))
				(3 (return oSlot7))
				(4 (return oSlot5))
				(5 (return oSlot20))
				(6 (return oSlot4))
				(7 (return oSlot1))
				(8 (return oSlot2))
			)
		)
	)
)

(instance oSlot4 of ShardSlot
	(properties
		x 206
		y 185
	)
	
	(method (BAD SELECTOR param1)
		(return
			(switch param1
				(1 (return oSlot2))
				(2 (return oSlot3))
				(3 (return oSlot5))
				(4 (return oSlot26))
				(5 (return oSlot19))
				(6 (return oSlot33))
				(7 (return oSlot11))
				(8 (return oSlot1))
			)
		)
	)
)

(instance oSlot5 of ShardSlot
	(properties
		x 438
		y 185
	)
	
	(method (BAD SELECTOR param1)
		(return
			(switch param1
				(1 (return oSlot6))
				(2 (return oSlot7))
				(3 (return oSlot12))
				(4 (return oSlot27))
				(5 (return oSlot21))
				(6 (return oSlot34))
				(7 (return oSlot4))
				(8 (return oSlot3))
			)
		)
	)
)

(instance oSlot6 of ShardSlot
	(properties
		x 416
		y 40
	)
	
	(method (BAD SELECTOR param1)
		(return
			(switch param1
				(1 (return oSlot16))
				(2 (return oSlot30))
				(3 (return oSlot10))
				(4 (return oSlot7))
				(5 (return oSlot5))
				(6 (return oSlot3))
				(7 (return oSlot2))
				(8 (return oSlot25))
			)
		)
	)
)

(instance oSlot7 of ShardSlot
	(properties
		x 502
		y 95
	)
	
	(method (BAD SELECTOR param1)
		(return
			(switch param1
				(1 (return oSlot17))
				(2 (return oSlot31))
				(3 (return oSlot8))
				(4 (return oSlot28))
				(5 (return oSlot22))
				(6 (return oSlot5))
				(7 (return oSlot3))
				(8 (return oSlot6))
			)
		)
	)
)

(instance oSlot8 of ShardSlot
	(properties
		x 620
		y 97
	)
)

(instance oSlot9 of ShardSlot
	(properties)
)

(instance oSlot10 of ShardSlot
	(properties)
)

(instance oSlot11 of ShardSlot
	(properties)
)

(instance oSlot12 of ShardSlot
	(properties)
)

(instance oSlot13 of ShardSlot
	(properties)
)

(instance oSlot14 of ShardSlot
	(properties)
)

(instance oSlot15 of ShardSlot
	(properties)
)

(instance oSlot16 of ShardSlot
	(properties)
)

(instance oSlot17 of ShardSlot
	(properties)
)

(instance oSlot18 of ShardSlot
	(properties)
)

(instance oSlot19 of ShardSlot
	(properties)
)

(instance oSlot20 of ShardSlot
	(properties)
)

(instance oSlot21 of ShardSlot
	(properties)
)

(instance oSlot22 of ShardSlot
	(properties)
)

(instance oSlot23 of ShardSlot
	(properties)
)

(instance oSlot24 of ShardSlot
	(properties)
)

(instance oSlot25 of ShardSlot
	(properties)
)

(instance oSlot26 of ShardSlot
	(properties)
)

(instance oSlot27 of ShardSlot
	(properties)
)

(instance oSlot28 of ShardSlot
	(properties)
)

(instance oSlot29 of ShardSlot
	(properties)
)

(instance oSlot30 of ShardSlot
	(properties)
)

(instance oSlot31 of ShardSlot
	(properties)
)

(instance oSlot32 of ShardSlot
	(properties)
)

(instance oSlot33 of ShardSlot
	(properties)
)

(instance oSlot34 of ShardSlot
	(properties)
)

(instance oClick of TPSound
	(properties)
)

(class ShardSlotFeature of Feature
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
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 75)
	)
	
	(method (doVerb &tmp bAD SELECTORBAD SELECTOR)
		(= bAD_SELECTORBAD_SELECTOR (BAD_SELECTOR BAD SELECTOR?))
		(BAD_SELECTOR BAD_SELECTOR: theViewShard)
		(theViewShard posn: x y init:)
		(if bAD_SELECTORBAD_SELECTOR
			(oClick lThumbLoop: -24635)
			(= theViewShard bAD_SELECTORBAD_SELECTOR)
			(bAD_SELECTORBAD_SELECTOR dispose:)
		else
			(= gVerb 1)
			(curRoom setScript: soDrawLight)
		)
	)
)

(instance foSlot1 of ShardSlotFeature
	(properties
		nsLeft 101
		nsTop 142
		nsRight 169
		nsBottom 177
		x 134
		y 170
	)
)

(instance foSlot2 of ShardSlotFeature
	(properties
		nsLeft 202
		nsTop 89
		nsRight 257
		nsBottom 114
		x 229
		y 109
	)
)

(instance foSlot3 of ShardSlotFeature
	(properties
		nsLeft 285
		nsTop 137
		nsRight 350
		nsBottom 175
		x 312
		y 163
	)
)

(instance foSlot4 of ShardSlotFeature
	(properties
		nsLeft 172
		nsTop 231
		nsRight 240
		nsBottom 266
		x 206
		y 259
	)
)

(instance foSlot5 of ShardSlotFeature
	(properties
		nsLeft 400
		nsTop 226
		nsRight 476
		nsBottom 268
		x 438
		y 257
	)
)

(instance foSlot6 of ShardSlotFeature
	(properties
		nsLeft 390
		nsTop 92
		nsRight 435
		nsBottom 108
		x 416
		y 110
	)
)

(instance foSlot7 of ShardSlotFeature
	(properties
		nsLeft 481
		nsTop 151
		nsRight 528
		nsBottom 169
		x 502
		y 168
	)
)

(class ViewShard of View
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
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $4021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 75)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(sound1 lThumbLoop: -24632)
				(oLines dispose:)
				(= theViewShard self)
				(= gVerb 75)
				(switch self
					((oSlot1 BAD SELECTOR?)
						(oSlot1 BAD_SELECTOR: 0)
					)
					((oSlot2 BAD SELECTOR?)
						(oSlot2 BAD_SELECTOR: 0)
					)
					((oSlot3 BAD SELECTOR?)
						(oSlot3 BAD_SELECTOR: 0)
					)
					((oSlot4 BAD SELECTOR?)
						(oSlot4 BAD_SELECTOR: 0)
					)
					((oSlot5 BAD SELECTOR?)
						(oSlot5 BAD_SELECTOR: 0)
					)
					((oSlot6 BAD SELECTOR?)
						(oSlot6 BAD_SELECTOR: 0)
					)
					((oSlot7 BAD SELECTOR?)
						(oSlot7 BAD_SELECTOR: 0)
					)
				)
				(self dispose:)
			)
			(75
				(switch self
					((oSlot1 BAD SELECTOR?)
						(foSlot1 doVerb: theVerb)
					)
					((oSlot2 BAD SELECTOR?)
						(foSlot2 doVerb: theVerb)
					)
					((oSlot3 BAD SELECTOR?)
						(foSlot3 doVerb: theVerb)
					)
					((oSlot4 BAD SELECTOR?)
						(foSlot4 doVerb: theVerb)
					)
					((oSlot5 BAD SELECTOR?)
						(foSlot5 doVerb: theVerb)
					)
					((oSlot6 BAD SELECTOR?)
						(foSlot6 doVerb: theVerb)
					)
					((oSlot7 BAD SELECTOR?)
						(foSlot7 doVerb: theVerb)
					)
				)
			)
			(else 
				(MonoOut
					{Couldn't figure out which slot holds shard that was clicked upon}
				)
			)
		)
	)
)

(instance voR90One of ViewShard
	(properties
		view -22536
		BAD_SELECTOR 2
	)
)

(instance voR90Two of ViewShard
	(properties
		view -22536
		loop 2
		BAD_SELECTOR 2
	)
)

(instance voL90 of ViewShard
	(properties
		view -22536
		loop 1
		BAD_SELECTOR 6
	)
)

(instance voL45Two of ViewShard
	(properties
		view -22536
		loop 6
		BAD_SELECTOR 7
	)
)

(instance voL45 of ViewShard
	(properties
		view -22536
		loop 4
		BAD_SELECTOR 7
	)
)

(instance voR135 of ViewShard
	(properties
		view -22536
		loop 5
		BAD_SELECTOR 3
	)
)

(instance voL135 of ViewShard
	(properties
		view -22536
		loop 3
		BAD_SELECTOR 5
	)
)

(instance roAsthExitPuzzle of TPRoom
	(properties
		picture -22536
	)
	
	(method (init &tmp temp0)
		(super init: &rest)
		(oSlot9 posn: local0 (oSlot2 y?))
		(oSlot10 posn: local1 (oSlot2 y?))
		(oSlot11 posn: local0 (oSlot4 y?))
		(oSlot12 posn: local1 (oSlot4 y?))
		(oSlot13 posn: (+ (oSlot1 x?) 20) local2)
		(oSlot14 posn: (+ (oSlot2 x?) 8) local2)
		(oSlot15 posn: (oSlot3 x?) local2)
		(oSlot16 posn: (- (oSlot6 x?) 10) local2)
		(oSlot17 posn: (- (oSlot7 x?) 20) local2)
		(oSlot18 posn: (- (oSlot1 x?) 40) local3)
		(oSlot19 posn: (- (oSlot4 x?) 15) local3)
		(oSlot20 posn: (oSlot3 x?) local3)
		(oSlot21 posn: (+ (oSlot5 x?) 10) local3)
		(oSlot22 posn: (+ (oSlot7 x?) 40) local3)
		(oSlot23 posn: 0 (- (oSlot1 y?) (oSlot1 x?)))
		(oSlot24 posn: 0 (- (oSlot2 y?) (oSlot2 x?)))
		(oSlot25 posn: 0 (- (oSlot6 y?) (oSlot6 x?)))
		(oSlot26
			posn: (+ local3 (- (oSlot4 x?) (oSlot4 y?))) local3
		)
		(oSlot27
			posn: (+ local3 (- (oSlot5 x?) (oSlot5 y?))) local3
		)
		(oSlot28
			posn: (+ local3 (- (oSlot7 x?) (oSlot7 y?))) local3
		)
		(oSlot29 posn: (+ (oSlot2 x?) (oSlot2 y?)) 0)
		(oSlot30 posn: (+ (oSlot6 x?) (oSlot6 y?)) 0)
		(oSlot31 posn: (+ (oSlot7 x?) (oSlot7 y?)) 0)
		(oSlot32 posn: 0 (+ (oSlot1 x?) (oSlot1 y?)))
		(oSlot33 posn: 0 (+ (oSlot4 x?) (oSlot4 y?)))
		(oSlot34 posn: 0 (+ (oSlot5 x?) (oSlot5 y?)))
		(music1 pageSize: -25236)
		(Load rsAUDIO -24633 -24632 -24630 -24635)
		(foSlot1 init: BAD_SELECTOR: oSlot1)
		(foSlot2 init: BAD_SELECTOR: oSlot2)
		(foSlot3 init: BAD_SELECTOR: oSlot3)
		(foSlot4 init: BAD_SELECTOR: oSlot4)
		(foSlot5 init: BAD_SELECTOR: oSlot5)
		(foSlot6 init: BAD_SELECTOR: oSlot6)
		(foSlot7 init: BAD_SELECTOR: oSlot7)
		(= temp0
			((= local5
				(IDArray
					with: voR90One voR90Two voL90 voL45Two voL45 voR135 voL135
				)
			)
				at: global252
			)
		)
		(temp0 init: posn: (foSlot1 x?) (foSlot1 y?))
		(oSlot1 BAD_SELECTOR: temp0)
		(= temp0 (local5 at: global253))
		(temp0 init: posn: (foSlot2 x?) (foSlot2 y?))
		(oSlot2 BAD_SELECTOR: temp0)
		(= temp0 (local5 at: global254))
		(temp0 init: posn: (foSlot3 x?) (foSlot3 y?))
		(oSlot3 BAD_SELECTOR: temp0)
		(= temp0 (local5 at: global255))
		(temp0 init: posn: (foSlot4 x?) (foSlot4 y?))
		(oSlot4 BAD_SELECTOR: temp0)
		(= temp0 (local5 at: global256))
		(temp0 init: posn: (foSlot5 x?) (foSlot5 y?))
		(oSlot5 BAD_SELECTOR: temp0)
		(= temp0 (local5 at: global257))
		(temp0 init: posn: (foSlot6 x?) (foSlot6 y?))
		(oSlot6 BAD_SELECTOR: temp0)
		(= temp0 (local5 at: global258))
		(temp0 init: posn: (foSlot7 x?) (foSlot7 y?))
		(oSlot7 BAD_SELECTOR: temp0)
		(foExit init:)
		(theGame handsOn:)
		(oDrawLight cue:)
	)
	
	(method (dispose)
		(oLines dispose:)
		(= global252 (local5 indexOf: (oSlot1 BAD SELECTOR?)))
		(= global253 (local5 indexOf: (oSlot2 BAD SELECTOR?)))
		(= global254 (local5 indexOf: (oSlot3 BAD SELECTOR?)))
		(= global255 (local5 indexOf: (oSlot4 BAD SELECTOR?)))
		(= global256 (local5 indexOf: (oSlot5 BAD SELECTOR?)))
		(= global257 (local5 indexOf: (oSlot6 BAD SELECTOR?)))
		(= global258 (local5 indexOf: (oSlot7 BAD SELECTOR?)))
		(super dispose: &rest)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)

(instance oTempKeyHandler of EventCode
	(properties)
	
	(method (handleEvent event &tmp eventMessage)
		(if (not (& (event type?) $000c))
			(Prints
				{Attempt to execute key handler with non-key event. DJM keys.sc}
			)
			(return 0)
		)
		(if (not (& (event type?) evKEYBOARD)) (return 0))
		(if (== (= eventMessage (event message?)) 48)
			(soDebug cue:)
			(event claimed: 1)
		)
		(return (event claimed?))
	)
)

(instance soDebug of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0)
			(1
				(oLines dispose:)
				(localproc_1634 oSlot1)
			)
			(2
				(oLines dispose:)
				(localproc_1634 oSlot2)
			)
			(3
				(oLines dispose:)
				(localproc_1634 oSlot3)
			)
			(4
				(oLines dispose:)
				(localproc_1634 oSlot4)
			)
			(5
				(oLines dispose:)
				(localproc_1634 oSlot5)
			)
			(6
				(oLines dispose:)
				(localproc_1634 oSlot6)
			)
			(7
				(oLines dispose:)
				(localproc_1634 oSlot7)
				(= state 0)
			)
		)
	)
)
