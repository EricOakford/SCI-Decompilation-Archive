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
(use Events)
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
	(LOOKUP_ERROR
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
		(LOOKUP_ERROR BAD_SELECTOR: 0)
		(LOOKUP_ERROR BAD_SELECTOR: 0)
		(LOOKUP_ERROR BAD_SELECTOR: 0)
		(LOOKUP_ERROR BAD_SELECTOR: 0)
		(LOOKUP_ERROR BAD_SELECTOR: 0)
		(LOOKUP_ERROR BAD_SELECTOR: 0)
		(LOOKUP_ERROR BAD_SELECTOR: 0)
		(while size
			(DeleteLine
				(= temp0 (LOOKUP_ERROR at: 0))
				(curRoom plane?)
			)
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
				(theSound lThumbLoop: -24633)
				(= ticks 30)
			)
			(1 (LOOKUP_ERROR cue:))
		)
	)
)

(instance oDrawLight of CueMe
	(properties)
	
	(method (cue &tmp theLOOKUP_ERROR theLOOKUP_ERROR_2 temp2)
		(= theLOOKUP_ERROR LOOKUP_ERROR)
		(= theLOOKUP_ERROR_2 LOOKUP_ERROR)
		(= temp2 3)
		(while
		(localproc_01d3 theLOOKUP_ERROR theLOOKUP_ERROR_2)
			(= theLOOKUP_ERROR theLOOKUP_ERROR_2)
			(= temp2
				(localproc_01b0
					((theLOOKUP_ERROR_2 BAD SELECTOR?) BAD SELECTOR?)
					temp2
				)
			)
			(= theLOOKUP_ERROR_2
				(theLOOKUP_ERROR_2 BAD_SELECTOR: temp2)
			)
		)
		(theGame handsOn:)
		(cond 
			(
				(and
					(== theLOOKUP_ERROR_2 LOOKUP_ERROR)
					(LOOKUP_ERROR BAD SELECTOR?)
					(LOOKUP_ERROR BAD SELECTOR?)
					(LOOKUP_ERROR BAD SELECTOR?)
					(LOOKUP_ERROR BAD SELECTOR?)
					(LOOKUP_ERROR BAD SELECTOR?)
					(LOOKUP_ERROR BAD SELECTOR?)
					(LOOKUP_ERROR BAD SELECTOR?)
				)
				(curRoom setScript: 'LOOKUP_ERROR')
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
				(theSound lThumbLoop: -24630 self)
			)
			(1 (curRoom newRoom: -24636))
		)
	)
)

(instance foExit of CUExitFeature
	(properties
		unregisterDefaultHandler -24636
	)
	
	(method (doVerb &tmp theLOOKUP_ERROR)
		(if (== gVerb 75)
			(cond 
				((not (LOOKUP_ERROR BAD SELECTOR?)) (= theLOOKUP_ERROR LOOKUP_ERROR))
				((not (LOOKUP_ERROR BAD SELECTOR?)) (= theLOOKUP_ERROR LOOKUP_ERROR))
				((not (LOOKUP_ERROR BAD SELECTOR?)) (= theLOOKUP_ERROR LOOKUP_ERROR))
				((not (LOOKUP_ERROR BAD SELECTOR?)) (= theLOOKUP_ERROR {oSlot2}))
				((not (LOOKUP_ERROR BAD SELECTOR?)) (= theLOOKUP_ERROR {Slot14}))
				((not (LOOKUP_ERROR BAD SELECTOR?)) (= theLOOKUP_ERROR {Slot25}))
				((not (LOOKUP_ERROR BAD SELECTOR?)) (= theLOOKUP_ERROR LOOKUP_ERROR))
				(else (MonoOut LOOKUP_ERROR) (= gVerb 1) (= theLOOKUP_ERROR 0))
			)
			(if theLOOKUP_ERROR
				(= local6 1)
				(theLOOKUP_ERROR doVerb: 75)
			)
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
				(1 (return LOOKUP_ERROR))
				(2 (return LOOKUP_ERROR))
				(3 (return LOOKUP_ERROR))
				(4 (return LOOKUP_ERROR))
				(5 (return LOOKUP_ERROR))
				(6 (return {soPuzzleSolved}))
				(7 (return LOOKUP_ERROR))
				(8 (return LOOKUP_ERROR))
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
				(1 (return LOOKUP_ERROR))
				(2 (return LOOKUP_ERROR))
				(3 (return LOOKUP_ERROR))
				(4 (return LOOKUP_ERROR))
				(5 (return LOOKUP_ERROR))
				(6 (return LOOKUP_ERROR))
				(7 (return LOOKUP_ERROR))
				(8 (return LOOKUP_ERROR))
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
				(1 (return LOOKUP_ERROR))
				(2 (return LOOKUP_ERROR))
				(3 (return LOOKUP_ERROR))
				(4 (return LOOKUP_ERROR))
				(5 (return LOOKUP_ERROR))
				(6 (return LOOKUP_ERROR))
				(7 (return LOOKUP_ERROR))
				(8 (return LOOKUP_ERROR))
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
				(1 (return LOOKUP_ERROR))
				(2 (return LOOKUP_ERROR))
				(3 (return LOOKUP_ERROR))
				(4 (return LOOKUP_ERROR))
				(5 (return LOOKUP_ERROR))
				(6 (return LOOKUP_ERROR))
				(7 (return LOOKUP_ERROR))
				(8 (return LOOKUP_ERROR))
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
				(1 (return LOOKUP_ERROR))
				(2 (return LOOKUP_ERROR))
				(3 (return {hardSlot}))
				(4 (return LOOKUP_ERROR))
				(5 (return LOOKUP_ERROR))
				(6 (return LOOKUP_ERROR))
				(7 (return LOOKUP_ERROR))
				(8 (return LOOKUP_ERROR))
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
				(1 (return LOOKUP_ERROR))
				(2 (return LOOKUP_ERROR))
				(3 (return LOOKUP_ERROR))
				(4 (return LOOKUP_ERROR))
				(5 (return LOOKUP_ERROR))
				(6 (return LOOKUP_ERROR))
				(7 (return LOOKUP_ERROR))
				(8 (return LOOKUP_ERROR))
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
				(1 (return {voR90Two}))
				(2 (return LOOKUP_ERROR))
				(3 (return LOOKUP_ERROR))
				(4 (return LOOKUP_ERROR))
				(5 (return LOOKUP_ERROR))
				(6 (return LOOKUP_ERROR))
				(7 (return LOOKUP_ERROR))
				(8 (return LOOKUP_ERROR))
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
			(LOOKUP_ERROR lThumbLoop: -24635)
			(= theViewShard bAD_SELECTORBAD_SELECTOR)
			(bAD_SELECTORBAD_SELECTOR dispose:)
		else
			(= gVerb 1)
			(curRoom setScript: LOOKUP_ERROR)
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
				(theSound lThumbLoop: -24632)
				(LOOKUP_ERROR dispose:)
				(= theViewShard self)
				(= gVerb 75)
				(switch self
					((LOOKUP_ERROR BAD SELECTOR?)
						(LOOKUP_ERROR BAD_SELECTOR: 0)
					)
					((LOOKUP_ERROR BAD SELECTOR?)
						(LOOKUP_ERROR BAD_SELECTOR: 0)
					)
					((LOOKUP_ERROR BAD SELECTOR?)
						(LOOKUP_ERROR BAD_SELECTOR: 0)
					)
					((LOOKUP_ERROR BAD SELECTOR?)
						(LOOKUP_ERROR BAD_SELECTOR: 0)
					)
					((LOOKUP_ERROR BAD SELECTOR?)
						(LOOKUP_ERROR BAD_SELECTOR: 0)
					)
					((LOOKUP_ERROR BAD SELECTOR?)
						(LOOKUP_ERROR BAD_SELECTOR: 0)
					)
					((LOOKUP_ERROR BAD SELECTOR?)
						(LOOKUP_ERROR BAD_SELECTOR: 0)
					)
				)
				(self dispose:)
			)
			(75
				(switch self
					((LOOKUP_ERROR BAD SELECTOR?)
						(LOOKUP_ERROR doVerb: theVerb)
					)
					((LOOKUP_ERROR BAD SELECTOR?)
						(LOOKUP_ERROR doVerb: theVerb)
					)
					((LOOKUP_ERROR BAD SELECTOR?)
						(LOOKUP_ERROR doVerb: theVerb)
					)
					((LOOKUP_ERROR BAD SELECTOR?)
						(LOOKUP_ERROR doVerb: theVerb)
					)
					((LOOKUP_ERROR BAD SELECTOR?)
						(LOOKUP_ERROR doVerb: theVerb)
					)
					((LOOKUP_ERROR BAD SELECTOR?)
						(LOOKUP_ERROR doVerb: theVerb)
					)
					((LOOKUP_ERROR BAD SELECTOR?)
						(LOOKUP_ERROR doVerb: theVerb)
					)
				)
			)
			(else  (MonoOut LOOKUP_ERROR))
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
		(LOOKUP_ERROR posn: local0 (LOOKUP_ERROR y?))
		(LOOKUP_ERROR posn: local1 (LOOKUP_ERROR y?))
		(LOOKUP_ERROR posn: local0 (LOOKUP_ERROR y?))
		(LOOKUP_ERROR posn: local1 (LOOKUP_ERROR y?))
		(LOOKUP_ERROR posn: (+ (LOOKUP_ERROR x?) 20) local2)
		(LOOKUP_ERROR posn: (+ (LOOKUP_ERROR x?) 8) local2)
		(LOOKUP_ERROR posn: (LOOKUP_ERROR x?) local2)
		(LOOKUP_ERROR posn: (- (LOOKUP_ERROR x?) 10) local2)
		(LOOKUP_ERROR posn: (- (LOOKUP_ERROR x?) 20) local2)
		(LOOKUP_ERROR posn: (- (LOOKUP_ERROR x?) 40) local3)
		(LOOKUP_ERROR posn: (- (LOOKUP_ERROR x?) 15) local3)
		(LOOKUP_ERROR posn: (LOOKUP_ERROR x?) local3)
		(LOOKUP_ERROR posn: (+ (LOOKUP_ERROR x?) 10) local3)
		(LOOKUP_ERROR posn: (+ (LOOKUP_ERROR x?) 40) local3)
		(LOOKUP_ERROR
			posn: 0 (- (LOOKUP_ERROR y?) (LOOKUP_ERROR x?))
		)
		(LOOKUP_ERROR
			posn: 0 (- (LOOKUP_ERROR y?) (LOOKUP_ERROR x?))
		)
		(LOOKUP_ERROR
			posn: 0 (- (LOOKUP_ERROR y?) (LOOKUP_ERROR x?))
		)
		(LOOKUP_ERROR
			posn: (+ local3 (- (LOOKUP_ERROR x?) (LOOKUP_ERROR y?))) local3
		)
		(LOOKUP_ERROR
			posn: (+ local3 (- (LOOKUP_ERROR x?) (LOOKUP_ERROR y?))) local3
		)
		(LOOKUP_ERROR
			posn: (+ local3 (- (LOOKUP_ERROR x?) (LOOKUP_ERROR y?))) local3
		)
		(LOOKUP_ERROR
			posn: (+ (LOOKUP_ERROR x?) (LOOKUP_ERROR y?)) 0
		)
		(LOOKUP_ERROR
			posn: (+ (LOOKUP_ERROR x?) (LOOKUP_ERROR y?)) 0
		)
		(LOOKUP_ERROR
			posn: (+ (LOOKUP_ERROR x?) (LOOKUP_ERROR y?)) 0
		)
		(LOOKUP_ERROR
			posn: 0 (+ (LOOKUP_ERROR x?) (LOOKUP_ERROR y?))
		)
		(LOOKUP_ERROR
			posn: 0 (+ (LOOKUP_ERROR x?) (LOOKUP_ERROR y?))
		)
		(LOOKUP_ERROR
			posn: 0 (+ (LOOKUP_ERROR x?) (LOOKUP_ERROR y?))
		)
		(theMusic pageSize: -25236)
		(Load 141 -24633 -24632 -24630 -24635)
		(LOOKUP_ERROR init: BAD_SELECTOR: LOOKUP_ERROR)
		(LOOKUP_ERROR init: BAD_SELECTOR: LOOKUP_ERROR)
		(LOOKUP_ERROR init: BAD_SELECTOR: LOOKUP_ERROR)
		(LOOKUP_ERROR init: BAD_SELECTOR: LOOKUP_ERROR)
		(LOOKUP_ERROR init: BAD_SELECTOR: LOOKUP_ERROR)
		(LOOKUP_ERROR init: BAD_SELECTOR: LOOKUP_ERROR)
		(LOOKUP_ERROR init: BAD_SELECTOR: LOOKUP_ERROR)
		(= temp0
			((= local5
				(IDArray
					with:
						LOOKUP_ERROR
						LOOKUP_ERROR
						LOOKUP_ERROR
						LOOKUP_ERROR
						LOOKUP_ERROR
						LOOKUP_ERROR
						LOOKUP_ERROR
				)
			)
				at: global252
			)
		)
		(temp0 init: posn: (LOOKUP_ERROR x?) (LOOKUP_ERROR y?))
		(LOOKUP_ERROR BAD_SELECTOR: temp0)
		(= temp0 (local5 at: global253))
		(temp0 init: posn: (LOOKUP_ERROR x?) (LOOKUP_ERROR y?))
		(LOOKUP_ERROR BAD_SELECTOR: temp0)
		(= temp0 (local5 at: global254))
		(temp0 init: posn: (LOOKUP_ERROR x?) (LOOKUP_ERROR y?))
		(LOOKUP_ERROR BAD_SELECTOR: temp0)
		(= temp0 (local5 at: global255))
		(temp0 init: posn: (LOOKUP_ERROR x?) (LOOKUP_ERROR y?))
		(LOOKUP_ERROR BAD_SELECTOR: temp0)
		(= temp0 (local5 at: global256))
		(temp0 init: posn: (LOOKUP_ERROR x?) (LOOKUP_ERROR y?))
		(LOOKUP_ERROR BAD_SELECTOR: temp0)
		(= temp0 (local5 at: global257))
		(temp0 init: posn: (LOOKUP_ERROR x?) (LOOKUP_ERROR y?))
		(LOOKUP_ERROR BAD_SELECTOR: temp0)
		(= temp0 (local5 at: global258))
		(temp0 init: posn: (LOOKUP_ERROR x?) (LOOKUP_ERROR y?))
		(LOOKUP_ERROR BAD_SELECTOR: temp0)
		(LOOKUP_ERROR init:)
		(theGame handsOn:)
		(LOOKUP_ERROR cue:)
	)
	
	(method (dispose)
		(LOOKUP_ERROR dispose:)
		(= global252
			(local5 indexOf: (LOOKUP_ERROR BAD SELECTOR?))
		)
		(= global253
			(local5 indexOf: (LOOKUP_ERROR BAD SELECTOR?))
		)
		(= global254
			(local5 indexOf: (LOOKUP_ERROR BAD SELECTOR?))
		)
		(= global255
			(local5 indexOf: (LOOKUP_ERROR BAD SELECTOR?))
		)
		(= global256
			(local5 indexOf: (LOOKUP_ERROR BAD SELECTOR?))
		)
		(= global257
			(local5 indexOf: (LOOKUP_ERROR BAD SELECTOR?))
		)
		(= global258
			(local5 indexOf: (LOOKUP_ERROR BAD SELECTOR?))
		)
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
			(Prints LOOKUP_ERROR)
			(return 0)
		)
		(if (not (& (event type?) evKEYBOARD)) (return 0))
		(if (== (= eventMessage (event message?)) 48)
			(LOOKUP_ERROR cue:)
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
				(LOOKUP_ERROR dispose:)
				(localproc_1634 'LOOKUP_ERROR')
			)
			(2
				(LOOKUP_ERROR dispose:)
				(localproc_1634 'LOOKUP_ERROR')
			)
			(3
				(LOOKUP_ERROR dispose:)
				(localproc_1634 'LOOKUP_ERROR')
			)
			(4
				(LOOKUP_ERROR dispose:)
				(localproc_1634 'LOOKUP_ERROR')
			)
			(5
				(LOOKUP_ERROR dispose:)
				(localproc_1634 'LOOKUP_ERROR')
			)
			(6
				(LOOKUP_ERROR dispose:)
				(localproc_1634 'LOOKUP_ERROR')
			)
			(7
				(LOOKUP_ERROR dispose:)
				(localproc_1634 'LOOKUP_ERROR')
				(= state 0)
			)
		)
	)
)
