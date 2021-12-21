;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15600)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use TPSound)
(use CueMe)
(use ExitFeature)
(use Array)
(use Motion)
(use Actor)

(public
	roCrystalConsole 0
)

(local
	local0
	local1
)
(procedure (localproc_00f0 &tmp temp0)
	(= temp0 (local0 at: global201))
	((temp0 at: 0) posn: 209 265)
	((temp0 at: 1) posn: 273 247)
	((temp0 at: 2) posn: 342 267)
	((temp0 at: 3) posn: 409 249)
)

(procedure (localproc_028d param1 &tmp temp0)
	(= temp0 0)
	(while (< temp0 argc)
		(((local1 at: global201) at: (- [param1 temp0] 1))
			holdStart:
		)
		(++ temp0)
	)
)

(class StonePillar of Prop
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
		signal $0021
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
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		oSlot 0
	)
	
	(method (init)
		(super init: &rest)
		(if ((ScriptID 64017 0) test: oSlot)
			(self setCel: 0)
		else
			(self setCel: (self lastCel:))
		)
	)
	
	(method (holdStart)
		(if (not (theSound vDownArrowView:))
			(theSound lThumbLoop: 15201)
		)
		(if ((ScriptID 64017 0) test: oSlot)
			(self setCycle: End LOOKUP_ERROR)
			((ScriptID 64017 0) clear: oSlot)
		else
			(self setCycle: Beg LOOKUP_ERROR)
			((ScriptID 64017 0) set: oSlot)
		)
	)
)

(instance poStone1 of StonePillar
	(properties
		x 163
		y 127
		view 15202
		loop 4
		oSlot 36
	)
)

(instance poStone2 of StonePillar
	(properties
		x 164
		y 125
		view 15202
		loop 5
		oSlot 37
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 128)
	)
)

(instance poStone3 of StonePillar
	(properties
		x 184
		y 133
		view 15202
		loop 6
		oSlot 38
	)
)

(instance poStone4 of StonePillar
	(properties
		x 138
		y 144
		view 15202
		loop 7
		oSlot 39
	)
)

(instance poFont of Prop
	(properties
		x 72
		y 139
		view 15202
		loop 8
	)
)

(instance oTestForWin of CueMe
	(properties)
	
	(method (cue)
		(if
			(and
				(== 0 (LOOKUP_ERROR cel:))
				(== 0 (LOOKUP_ERROR cel:))
				(== 0 (LOOKUP_ERROR cel:))
				(== 0 (LOOKUP_ERROR cel:))
			)
			((ScriptID 64017 0) set: 40)
			(curRoom setScript: {StonePillar})
		)
	)
)

(instance soPuzzleSolved of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR init: setCycle: End self)
				(theSound lThumbLoop: 15201)
			)
			(1 (curRoom newRoom: 15200))
		)
	)
)

(instance oClick of TPSound
	(properties)
)

(instance voRed of View
	(properties
		view 15202
		loop 3
		cel 1
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 22)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(22
				(localproc_028d 1 2)
				(LOOKUP_ERROR init: x y loop)
				(LOOKUP_ERROR lThumbLoop: 15204)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance voGreen of View
	(properties
		view 15202
		loop 2
		cel 1
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 22)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(22
				(localproc_028d 1 3 4)
				(LOOKUP_ERROR init: x y loop)
				(LOOKUP_ERROR lThumbLoop: 15204)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance voBlue of View
	(properties
		view 15202
		loop 1
		cel 1
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 22)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(22
				(localproc_028d 2 3)
				(LOOKUP_ERROR init: x y loop)
				(LOOKUP_ERROR lThumbLoop: 15204)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance voWhite of View
	(properties
		view 15202
		cel 1
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 22)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(22
				(= global201 (mod (+ global201 1) 4))
				(LOOKUP_ERROR init: x y (LOOKUP_ERROR loop?))
				(localproc_00f0)
				(LOOKUP_ERROR lThumbLoop: 15204)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance voShard of View
	(properties
		view 15202
	)
	
	(method (init param1 param2 theLoop &tmp temp0)
		(= loop theLoop)
		(self posn: param1 param2)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self setPri: 500)
		(cond 
			((== param1 209) (= temp0 0))
			((== param1 273) (= temp0 1))
			((== param1 342) (= temp0 2))
			(else (= temp0 3))
		)
		(((ScriptID 64001 0) get: 12) moveTo: temp0)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(((ScriptID 64001 0) get: 12) moveTo: -2)
				(self dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance foExit of CUExitFeature
	(properties
		unregisterDefaultHandler 15200
	)
	
	(method (doVerb)
		((ScriptID 64017 0) clear: 35)
		(super doVerb: &rest)
	)
)

(instance roCrystalConsole of TPRoom
	(properties
		picture 15600
		east 15200
		west 15200
	)
	
	(method (init &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8)
		((ScriptID 64017 0) set: 35)
		(theMusic pageSize: 15600)
		(Load 141 15204 15201)
		(= temp0
			(IDArray
				with: LOOKUP_ERROR LOOKUP_ERROR LOOKUP_ERROR LOOKUP_ERROR
			)
		)
		(= temp1
			(IDArray
				with: LOOKUP_ERROR LOOKUP_ERROR LOOKUP_ERROR LOOKUP_ERROR
			)
		)
		(= temp2
			(IDArray
				with: LOOKUP_ERROR LOOKUP_ERROR LOOKUP_ERROR LOOKUP_ERROR
			)
		)
		(= temp3
			(IDArray
				with: LOOKUP_ERROR LOOKUP_ERROR LOOKUP_ERROR LOOKUP_ERROR
			)
		)
		(= local0 (IDArray with: temp0 temp1 temp2 temp3))
		(= temp4
			(IDArray
				with: LOOKUP_ERROR LOOKUP_ERROR LOOKUP_ERROR LOOKUP_ERROR
			)
		)
		(= temp5
			(IDArray
				with: LOOKUP_ERROR LOOKUP_ERROR LOOKUP_ERROR LOOKUP_ERROR
			)
		)
		(= temp6
			(IDArray
				with: LOOKUP_ERROR LOOKUP_ERROR LOOKUP_ERROR LOOKUP_ERROR
			)
		)
		(= temp7
			(IDArray
				with: LOOKUP_ERROR LOOKUP_ERROR LOOKUP_ERROR LOOKUP_ERROR
			)
		)
		(= local1 (IDArray with: temp4 temp5 temp6 temp7))
		(super init: &rest)
		(theGame handsOn:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(localproc_00f0)
		(if (>= (((ScriptID 64001 0) get: 12) owner?) 0)
			(= temp8 (local0 at: global201))
			(switch (((ScriptID 64001 0) get: 12) owner?)
				(0
					(LOOKUP_ERROR init: 209 265 ((temp8 at: 0) loop?))
				)
				(1
					(LOOKUP_ERROR init: 273 247 ((temp8 at: 1) loop?))
				)
				(2
					(LOOKUP_ERROR init: 342 267 ((temp8 at: 2) loop?))
				)
				(3
					(LOOKUP_ERROR init: 409 249 ((temp8 at: 3) loop?))
				)
			)
		)
	)
)
