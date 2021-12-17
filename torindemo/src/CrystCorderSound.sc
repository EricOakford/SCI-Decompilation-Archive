;;; Sierra Script 1.0 - (do not remove this comment)
(script# 56000)
(include sci.sh)
(use Main)
(use TPSound)
(use Set)
(use PushButton)
(use Plane)
(use Polygon)
(use Feature)
(use Actor)

(public
	proc56000_0 0
	proc56000_1 1
	proc56000_2 2
	proc56000_3 3
)

(local
	theShard
	local1
	theObjOrXX
	theObjOrXY
)
(procedure (proc56000_0)
)

(procedure (proc56000_1 param1)
	(asm
		INVALID 
		sat      temp0
		jmp      code_0695
		_line_   257
		_line_   258
		pToa     74
		; WARNING: Can't determine property name for index 74
		sat      temp0
code_0695:
		_line_   260
		_line_   261
		pushi    #addToEnd
		pushi    8
		_line_   262
		lofsa    voShard0
		push    
		_line_   263
		lofsa    voShard1
		push    
		_line_   264
		lofsa    voShard2
		push    
		_line_   265
		lofsa    voShard3
		push    
		_line_   266
		lofsa    voShard4
		push    
		_line_   267
		lofsa    voShard5
		push    
		_line_   268
		lofsa    voShard6
		push    
		_line_   269
		lofsa    voShard7
		push    
		lat      temp0
		send     20
		_line_   271
		pushi    #BAD SELECTOR
		pushi    1
		lsp      param1
		self     6
		_line_   272
		ret     
	)
)

(procedure (proc56000_2 &tmp oCrystCorderBAD SELECTOR temp1 temp2 temp3 temp4)
	(= oCrystCorderBAD_SELECTOR (oCrystCorder BAD SELECTOR?))
	(if (== 4 (oCrystCorderBAD_SELECTOR size:))
		(= temp1 (oCrystCorderBAD_SELECTOR at: 0))
		(= temp2 (oCrystCorderBAD_SELECTOR at: 1))
		(= temp3 (oCrystCorderBAD_SELECTOR at: 2))
		(= temp4 (oCrystCorderBAD_SELECTOR at: 3))
		(cond 
			(
				(and
					(== (temp1 BAD SELECTOR?) -9530)
					(== (temp2 BAD SELECTOR?) -9528)
					(== (temp3 BAD SELECTOR?) -9533)
					(== (temp4 BAD SELECTOR?) -9532)
				)
				(return -9526)
			)
			(
				(and
					(== (temp1 BAD SELECTOR?) -9533)
					(== (temp2 BAD SELECTOR?) -9532)
					(== (temp3 BAD SELECTOR?) -9530)
					(== (temp4 BAD SELECTOR?) -9528)
				)
				(return -9525)
			)
		)
	)
	(return 0)
)

(procedure (proc56000_3)
	(asm
		sub     
		bnot    
		push    
		ldi      56003
		eq?     
		bnt      code_0276
		_line_   79
		pushi    #BAD SELECTOR
		pushi    0
		lat      temp2
		send     4
		push    
		ldi      56004
		eq?     
		bnt      code_0276
		_line_   80
		pushi    #BAD SELECTOR
		pushi    0
		lat      temp3
		send     4
		push    
		ldi      56006
		eq?     
		bnt      code_0276
		_line_   81
		pushi    #BAD SELECTOR
		pushi    0
		lat      temp4
		send     4
		push    
		ldi      56008
		eq?     
		bnt      code_0276
		_line_   82
		_line_   83
		ldi      56011
		ret     
code_0276:
		_line_   86
		ldi      0
		ret     
	)
)

(procedure (localproc_0fda &tmp temp0)
	(super
		init:
			(thePlane left:)
			(thePlane top?)
			(thePlane right:)
			(thePlane bottom?)
	)
	(= local1 1)
	(oCrystCorder init:)
	(foFullPlay init:)
	(foFullHolder init:)
	(oPlayButton init:)
	(= theShard 0)
)

(class CrystCorderSound of TPSound
	(properties
		scratch 0
		nodePtr 0
		handle 0
		flags $0000
		number 0
		vol 127
		priority 0
		loop 1
		signal $0000
		prevSignal 0
		dataInc 0
		min 0
		sec 0
		frame 0
		client 0
		owner 0
		type $0000
		minPosn 100
		audModNum 0
		audNoun 0
		audVerb 0
		audCase 0
		audSequence 0
		maxPosn -1
		curPosn 0
		incSize 0
		BAD_SELECTOR -1
		BAD_SELECTOR 0
		BAD_SELECTOR 0
		BAD_SELECTOR 0
		BAD_SELECTOR 0
	)
	
	(method (init &tmp bAD SELECTORFirst temp1 voUnbrokenShardBAD SELECTOR)
		(if (not BAD_SELECTOR)
			(super init: &rest)
			(= BAD_SELECTOR -1)
			(= BAD_SELECTOR (Set new:))
			(= BAD_SELECTOR (Set new:))
			(= BAD_SELECTOR 1)
		)
		(if ((ScriptID 64017 0) test: 137)
			(if
				(and
					(not (BAD_SELECTOR size:))
					(not (BAD_SELECTOR size:))
				)
				(if ((ScriptID 64017 0) test: 143)
					(self BAD_SELECTOR: 0)
				else
					(voUnbrokenShard init:)
					(self BAD_SELECTOR: 0 voUnbrokenShard 126 210)
				)
			else
				(= bAD_SELECTORFirst (BAD_SELECTOR first:))
				(while bAD_SELECTORFirst
					((KList 8 bAD_SELECTORFirst) init:)
					(= bAD_SELECTORFirst
						(BAD_SELECTOR next: bAD_SELECTORFirst)
					)
				)
				(= bAD_SELECTORFirst (BAD_SELECTOR first:))
				(while bAD_SELECTORFirst
					((KList 8 bAD_SELECTORFirst) init:)
					(= bAD_SELECTORFirst
						(BAD_SELECTOR next: bAD_SELECTORFirst)
					)
				)
				(= bAD_SELECTORFirst
					(+ (BAD_SELECTOR size:) (BAD_SELECTOR size:))
				)
				(if
					(and
						((ScriptID 64017 0) test: 143)
						(== bAD_SELECTORFirst 1)
					)
					(= voUnbrokenShardBAD_SELECTOR
						(voUnbrokenShard BAD SELECTOR?)
					)
					(oCrystCorder
						BAD_SELECTOR: voUnbrokenShardBAD_SELECTOR voUnbrokenShard
					)
					(self BAD_SELECTOR: voUnbrokenShardBAD_SELECTOR)
					(voUnbrokenShard dispose:)
				)
			)
		)
	)
	
	(method (dispose)
	)
	
	(method (BAD SELECTOR)
		(if (not (BAD_SELECTOR size:)) (self BAD_SELECTOR:))
		(= BAD_SELECTOR 8)
		(if ((ScriptID 64017 0) test: 137) (self cue:))
		((ScriptID 64017 0) set: 139)
	)
	
	(method (BAD SELECTOR)
		(= BAD_SELECTOR -1)
		((ScriptID 64017 0) clear: 139)
		(oPlayButton slotIncY: 0)
	)
	
	(method (BAD SELECTOR)
		(self BAD_SELECTOR:)
		(BAD_SELECTOR dispose:)
		(BAD_SELECTOR dispose:)
		(super dispose:)
		(DisposeScript -9536)
	)
	
	(method (BAD SELECTOR)
		(if (= BAD_SELECTOR (proc56000_2))
			((ScriptID 64017 0) set: 140)
		else
			((ScriptID 64017 0) clear: 140)
		)
	)
	
	(method (BAD SELECTOR param1 param2 &tmp theBAD SELECTOR)
		(if (== param1 1)
			(= theBAD_SELECTOR BAD_SELECTOR)
		else
			(= theBAD_SELECTOR BAD_SELECTOR)
		)
		(if (and (!= BAD_SELECTOR -1) (< BAD_SELECTOR 8))
			(self
				stop: ((BAD_SELECTOR at: BAD_SELECTOR) BAD SELECTOR?)
			)
		)
		(theBAD_SELECTOR delete: param2)
		(param2 BAD_SELECTOR: 0)
		(if (not (BAD_SELECTOR size:)) (self BAD_SELECTOR:))
		(self BAD_SELECTOR:)
	)
	
	(method (BAD SELECTOR param1 &tmp theBAD SELECTOR)
		(voShard0 init:)
		(voShard1 init:)
		(voShard2 init:)
		(voShard3 init:)
		(voShard4 init:)
		(voShard5 init:)
		(voShard6 init:)
		(voShard7 init:)
		(if (== param1 1)
			(= theBAD_SELECTOR BAD_SELECTOR)
		else
			(= theBAD_SELECTOR BAD_SELECTOR)
		)
		(theBAD_SELECTOR
			addToEnd:
				voShard0
				voShard1
				voShard2
				voShard3
				voShard4
				voShard5
				voShard6
				voShard7
		)
		(self BAD_SELECTOR: param1)
	)
	
	(method (BAD SELECTOR param1 &tmp theBAD SELECTOR temp1 temp2 temp3 temp4 temp5)
		(self BAD_SELECTOR: param1)
		(if (== param1 1)
			(= theBAD_SELECTOR BAD_SELECTOR)
		else
			(= theBAD_SELECTOR BAD_SELECTOR)
		)
		(= temp1 0)
		(while (< temp1 10)
			(= temp2 (Random 0 7))
			(if (!= temp2 (= temp3 (Random 0 7)))
				(= temp4 (theBAD_SELECTOR at: temp2))
				(= temp5 (theBAD_SELECTOR at: temp3))
				(theBAD_SELECTOR delete: temp5)
				(theBAD_SELECTOR addAfter: temp4 temp5)
			)
			(++ temp1)
		)
		(self BAD_SELECTOR: param1)
	)
	
	(method (BAD SELECTOR param1 param2 param3 &tmp theBAD SELECTOR theBAD SELECTORFirst temp2)
		(if (== param1 1)
			(= theBAD_SELECTOR BAD_SELECTOR)
		else
			(= theBAD_SELECTOR BAD_SELECTOR)
		)
		(= theBAD_SELECTORFirst (theBAD_SELECTOR first:))
		(if
			(or
				(== 0 (theBAD_SELECTOR size:))
				(< param3 ((KList 8 theBAD_SELECTORFirst) x?))
			)
			(theBAD_SELECTOR addToFront: param2)
		else
			(= theBAD_SELECTORFirst
				(theBAD_SELECTOR next: theBAD_SELECTORFirst)
			)
			(while theBAD_SELECTORFirst
				(if (< param3 ((KList 8 theBAD_SELECTORFirst) x?))
					(= theBAD_SELECTORFirst
						(theBAD_SELECTOR prev: theBAD_SELECTORFirst)
					)
					(theBAD_SELECTOR
						addAfter: (KList 8 theBAD_SELECTORFirst) param2
					)
					(break)
				)
				(= theBAD_SELECTORFirst
					(theBAD_SELECTOR next: theBAD_SELECTORFirst)
				)
			)
			(if (not theBAD_SELECTORFirst)
				(theBAD_SELECTOR addToEnd: param2)
			)
		)
		(param2 BAD_SELECTOR: param1)
		(self BAD_SELECTOR: param1)
		(self BAD_SELECTOR:)
	)
	
	(method (BAD SELECTOR param1 &tmp theBAD SELECTOR theBAD SELECTORFirst temp2 temp3 temp4)
		(if (== param1 1)
			(= theBAD_SELECTOR BAD_SELECTOR)
			(= temp3 148)
			(= temp4 127)
		else
			(= theBAD_SELECTOR BAD_SELECTOR)
			(= temp3 126)
			(= temp4 210)
		)
		(= temp3 (+ temp3 5))
		(= theBAD_SELECTORFirst (theBAD_SELECTOR first:))
		(while theBAD_SELECTORFirst
			((= temp2 (KList 8 theBAD_SELECTORFirst))
				posn: temp3 temp4
			)
			(= temp3
				(+
					(= temp3
						(+
							temp3
							(CelWide (temp2 view?) (temp2 loop?) (temp2 cel:))
						)
					)
					5
				)
			)
			(= theBAD_SELECTORFirst
				(theBAD_SELECTOR next: theBAD_SELECTORFirst)
			)
		)
	)
	
	(method (cue &tmp temp0 temp1)
		(asm
			_line_   206
			_file_   {filename}
			_line_   208
			pTos     BAD_SELECTOR
			ldi      65535
			eq?     
			bnt      code_055a
			ret     
code_055a:
			_line_   210
			pushi    #size
			pushi    0
			pToa     BAD_SELECTOR
			send     4
			not     
			bnt      code_0569
			ret     
code_0569:
			_line_   218
			pToa     BAD_SELECTOR
			sat      temp0
			_line_   220
code_0573:
			ldi      1
			bnt      code_05b5
			_line_   221
			ipToa    BAD_SELECTOR
			_line_   224
			pTos     BAD_SELECTOR
			ldi      8
			ge?     
			bnt      code_058d
			_line_   225
			ldi      0
			aTop     BAD_SELECTOR
code_058d:
			_line_   228
			pushi    0
			pushi    #at
			pushi    1
			pTos     BAD_SELECTOR
			pToa     BAD_SELECTOR
			send     6
			sat      temp1
			ne?     
			bnt      code_05a5
			_line_   229
			jmp      code_05b5
code_05a5:
			_line_   234
			pTos     BAD_SELECTOR
			lat      temp0
			eq?     
			bnt      code_0573
			_line_   235
			ret     
			jmp      code_0573
code_05b5:
			_line_   238
			lal      local1
			bnt      code_05e5
			_line_   239
			pushi    #cDownArrowCel
			pushi    7
			pushi    #BAD SELECTOR
			pushi    0
			lat      temp1
			send     4
			push    
			pushi    1
			pushi    1
			pushi    0
			pushi    #seq
			pushi    0
			lat      temp1
			send     4
			push    
			pushSelf
			pushi    56000
			self     18
			jmp      code_05fd
code_05e5:
			_line_   240
			_line_   241
			pushi    #lThumbLoop
			pushi    2
			pushi    #BAD SELECTOR
			pushi    0
			lat      temp1
			send     4
			push    
			pushSelf
			self     8
code_05fd:
			_line_   243
			ret     
		)
	)
)

(instance oCrystCorder of CrystCorderSound
	(properties)
)

(instance foFullPlay of Feature
	(properties
		x 156
		y 128
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 144 103 442 103 451 129 140 132
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 65 66)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 65)
			((ScriptID 64017 0) set: 137)
			(((ScriptID 64001 0) get: 53) moveTo: -1)
			(oCrystCorder BAD_SELECTOR: 1 voUnbrokenShard 148 127)
			(voUnbrokenShard init:)
			(if (oPlayButton slotIncX?)
				(oCrystCorder BAD_SELECTOR:)
			)
			(return)
		)
		(if (== theVerb 66)
			(if (not ((ScriptID 64017 0) test: 137))
				(oCrystCorder BAD_SELECTOR: 1)
				((ScriptID 64017 0) set: 143)
				((ScriptID 64017 0) set: 137)
				(((ScriptID 64001 0) get: 54) moveTo: -1)
			else
				(oCrystCorder
					BAD_SELECTOR: 1 theShard theObjOrXX theObjOrXY
				)
				(((ScriptID 64001 0) get: 54) moveTo: -1)
				(theShard init:)
			)
		)
	)
	
	(method (onMe theObjOrX &tmp temp0)
		(if (= temp0 (super onMe: theObjOrX))
			(= theObjOrXX (theObjOrX x?))
			(= theObjOrXY (theObjOrX y?))
		)
	)
)

(instance foFullHolder of Feature
	(properties
		x 156
		y 212
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 125 183 459 182 465 211 117 212
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 65 66)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 65)
			((ScriptID 64017 0) set: 137)
			(((ScriptID 64001 0) get: 53) moveTo: -1)
			(oCrystCorder BAD_SELECTOR: 0 voUnbrokenShard 126 210)
			(voUnbrokenShard init:)
		)
		(if (== theVerb 66)
			(if (not ((ScriptID 64017 0) test: 137))
				(oCrystCorder BAD_SELECTOR: 0)
				((ScriptID 64017 0) set: 143)
				((ScriptID 64017 0) set: 137)
				(((ScriptID 64001 0) get: 54) moveTo: -1)
			else
				(oCrystCorder
					BAD_SELECTOR: 0 theShard theObjOrXX theObjOrXY
				)
				(((ScriptID 64001 0) get: 54) moveTo: -1)
				(theShard init:)
			)
		)
	)
	
	(method (onMe theObjOrX &tmp temp0)
		(if (= temp0 (super onMe: theObjOrX))
			(= theObjOrXX (theObjOrX x?))
			(= theObjOrXY (theObjOrX y?))
		)
	)
)

(class Shard of View
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
		view -9536
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
		seq -1
		BAD_SELECTOR -1
		BAD_SELECTOR 0
		BAD_SELECTOR 2
	)
	
	(method (init)
		(if
		(or (< BAD_SELECTOR -9535) (> BAD_SELECTOR -9527))
			(MonoOut {Illegal init of shard})
			(SetDebug)
		)
		(if (== BAD_SELECTOR 2)
			(= loop 1)
			(= cel (- BAD_SELECTOR -9535))
			(= seq (+ cel 2))
		else
			(= cel (= loop 0))
			(= seq 1)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(= theShard self)
		(if (== BAD_SELECTOR 2)
			(((ScriptID 64001 0) get: 54) moveTo: -2)
		else
			(((ScriptID 64001 0) get: 53) moveTo: -2)
			((ScriptID 64017 0) clear: 137)
		)
		(oCrystCorder BAD_SELECTOR: BAD_SELECTOR self)
		(self dispose:)
	)
)

(instance oPlayButton of ToggleButton
	(properties
		x 294
		y 156
		view -9535
	)
	
	(method (oVerbs)
		(if slotIncX
			(oCrystCorder BAD_SELECTOR:)
		else
			(oCrystCorder BAD_SELECTOR:)
		)
	)
)

(instance voUnbrokenShard of Shard
	(properties
		BAD_SELECTOR -9527
		BAD_SELECTOR 1
	)
	
	(method (doVerb &tmp temp0)
		(localproc_0fda)
		(-- -size-)
		(super
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
		(= local1 1)
		(oCrystCorder init:)
		(foFullPlay init:)
		(foFullHolder init:)
		(oPlayButton init:)
		(= theShard 0)
	)
)

(instance voShard0 of Shard
	(properties
		BAD_SELECTOR -9535
	)
)

(instance voShard1 of Shard
	(properties
		BAD_SELECTOR -9534
	)
)

(instance voShard2 of Shard
	(properties
		BAD_SELECTOR -9533
	)
)

(instance voShard3 of Shard
	(properties
		BAD_SELECTOR -9532
	)
)

(instance voShard4 of Shard
	(properties
		BAD_SELECTOR -9531
	)
)

(instance voShard5 of Shard
	(properties
		BAD_SELECTOR -9530
	)
)

(instance voShard6 of Shard
	(properties
		BAD_SELECTOR -9529
	)
)

(instance voShard7 of Shard
	(properties
		BAD_SELECTOR -9528
	)
)

(instance poCrystCorderCu of Plane
	(properties
		picture -9536
		priority 20
	)
	
	(method (init &tmp temp0)
		(-- -size-)
		(super
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
		(= local1 1)
		(oCrystCorder init:)
		(foFullPlay init:)
		(foFullHolder init:)
		(oPlayButton init:)
		(= theShard 0)
	)
	
	(method (dispose)
		theObjOrXX
		(= local1 0)
		(super dispose:)
	)
)
