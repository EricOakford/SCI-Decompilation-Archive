;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40800)
(include sci.sh)
(use Main)
(use ScrollExit)
(use TPRoom)
(use TPSound)
(use List)
(use Script)
(use ExitFeature)
(use Print)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roSteppingStones 0
)

(local
	theLavaStone
	local1
	[local2 40]
	[local42 20]
	[lavaStoneSel_141 37]
)
(procedure (localproc_0316)
	((curRoom obstacles?) delete: oFarShoreWinnerPoly)
	((curRoom obstacles?) delete: oFarShorePoly)
	(if (>= local1 34)
		(oFarShoreWinnerPoly init:)
		((curRoom obstacles?) add: oFarShoreWinnerPoly)
		(foExit init:)
	else
		(oFarShorePoly init:)
		((curRoom obstacles?) add: oFarShorePoly)
		(foExit dispose:)
	)
)

(procedure (localproc_03c4 param1 param2 param3 &tmp temp0 temp1)
	(= temp0 (- param3 12))
	(= temp0 (MulDiv temp0 150 4))
	(= temp0
		(+ (= temp0 (MulDiv temp0 [local2 param2] 100)) 316)
	)
	(= temp1 (- 570 (= temp1 [local42 param2])))
	(param1 posn: temp0 temp1)
)

(procedure (localproc_0432 &tmp temp0)
	(= temp0 0)
	(while (< temp0 33)
		((= [lavaStoneSel_141 temp0] (LavaStone new:))
			getBitmap: temp0
		)
		(++ temp0)
	)
	(= local1 34)
	(poBridge cel: local1)
	([lavaStoneSel_141 0] BAD_SELECTOR: 2 6 init: add: 1 4)
	([lavaStoneSel_141 1]
		BAD_SELECTOR: 2 10
		init:
		add: 0 2 5 35
	)
	([lavaStoneSel_141 2]
		BAD_SELECTOR: 2 14
		init:
		add: 1 3 6 36
	)
	([lavaStoneSel_141 3] BAD_SELECTOR: 2 18 init: add: 2 7)
	([lavaStoneSel_141 4] BAD_SELECTOR: 4 6 init: add: 0 5 8)
	([lavaStoneSel_141 5]
		BAD_SELECTOR: 4 10
		init:
		add: 1 4 6 9
	)
	([lavaStoneSel_141 6]
		BAD_SELECTOR: 4 14
		init:
		add: 2 5 7 10
	)
	([lavaStoneSel_141 7]
		BAD_SELECTOR: 4 18
		init:
		add: 3 6 11
	)
	([lavaStoneSel_141 8] BAD_SELECTOR: 6 6 init: add: 4 9 12)
	([lavaStoneSel_141 9]
		BAD_SELECTOR: 6 10
		init:
		add: 5 8 10 13
	)
	([lavaStoneSel_141 10]
		BAD_SELECTOR: 6 14
		init:
		add: 6 9 11 14
	)
	([lavaStoneSel_141 11]
		BAD_SELECTOR: 6 18
		init:
		add: 7 10 15
	)
	([lavaStoneSel_141 12]
		BAD_SELECTOR: 8 6
		init:
		add: 8 13 16
	)
	([lavaStoneSel_141 13]
		BAD_SELECTOR: 8 10
		init:
		add: 9 12 14 17
	)
	([lavaStoneSel_141 14]
		BAD_SELECTOR: 8 14
		init:
		add: 10 13 15 17
	)
	([lavaStoneSel_141 15]
		BAD_SELECTOR: 8 18
		init:
		add: 11 14 18
	)
	([lavaStoneSel_141 16]
		BAD_SELECTOR: 10 6
		init:
		add: 12 19 20
	)
	([lavaStoneSel_141 17]
		BAD_SELECTOR: 10 12
		init:
		add: 13 14 20 21 24
	)
	([lavaStoneSel_141 18]
		BAD_SELECTOR: 10 18
		init:
		add: 15 21 22
	)
	([lavaStoneSel_141 19]
		BAD_SELECTOR: 11 3
		init:
		add: 16 23 26
	)
	([lavaStoneSel_141 20]
		BAD_SELECTOR: 11 9
		init:
		add: 16 17 24 27
	)
	([lavaStoneSel_141 21]
		BAD_SELECTOR: 11 15
		init:
		add: 17 18 24 28
	)
	([lavaStoneSel_141 22]
		BAD_SELECTOR: 11 21
		init:
		add: 18 25 29
	)
	([lavaStoneSel_141 23]
		BAD_SELECTOR: 12 0
		init:
		add: 19 26
	)
	([lavaStoneSel_141 24]
		BAD_SELECTOR: 12 12
		init:
		add: 17 20 21 27 28 31
	)
	([lavaStoneSel_141 25]
		BAD_SELECTOR: 12 24
		init:
		add: 22 29
	)
	([lavaStoneSel_141 26]
		BAD_SELECTOR: 13 3
		init:
		add: 19 23 30
	)
	([lavaStoneSel_141 27]
		BAD_SELECTOR: 13 9
		init:
		add: 20 24 30 31
	)
	([lavaStoneSel_141 28]
		BAD_SELECTOR: 13 15
		init:
		add: 21 24 31 32
	)
	([lavaStoneSel_141 29]
		BAD_SELECTOR: 13 21
		init:
		add: 22 25 32
	)
	([lavaStoneSel_141 30]
		BAD_SELECTOR: 14 6
		init:
		add: 26 27
	)
	([lavaStoneSel_141 31]
		BAD_SELECTOR: 14 12
		init:
		add: 24 27 28 33
	)
	([lavaStoneSel_141 32]
		BAD_SELECTOR: 14 18
		init:
		add: 28 29
	)
	((= [lavaStoneSel_141 33] (AlmostFarLavaStone new:))
		BAD_SELECTOR: 16 12
		getBitmap: 33
		init:
		add: 31 34
	)
	((= [lavaStoneSel_141 34] (FarLavaStone new:))
		BAD_SELECTOR: 18 12
		getBitmap: 34
		init:
		add: 33
	)
	((= [lavaStoneSel_141 35] (NearLavaStone new:))
		BAD_SELECTOR: 0 10
		getBitmap: 35
		init:
		add: 1 36
	)
	((= [lavaStoneSel_141 36] (NearLavaStone new:))
		BAD_SELECTOR: 0 14
		getBitmap: 36
		init:
		add: 2 35
	)
)

(procedure (localproc_0af9 param1 &tmp temp0 lavaStoneSel_141Cel poBridgeCel thePoBridge)
	(= local1 0)
	(localproc_0316)
	(= poBridgeCel (poBridge cel:))
	(= thePoBridge poBridge)
	(poBridge setCycle: Beg)
	(= temp0 0)
	(while (< temp0 37)
		(if
			(>
				(= lavaStoneSel_141Cel ([lavaStoneSel_141 temp0] cel:))
				poBridgeCel
			)
			(= thePoBridge [lavaStoneSel_141 temp0])
			(= poBridgeCel lavaStoneSel_141Cel)
		)
		(if (> lavaStoneSel_141Cel 0)
			([lavaStoneSel_141 temp0]
				setVisibleRange: 1
				setScript: 0
				cycleSpeed: (poBridge cycleSpeed?)
				setCycle: Beg
			)
		)
		(++ temp0)
	)
	(theGame handsOff:)
	(thePoBridge setCycle: Beg param1)
)

(class LavaStone of Prop
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
		view -24734
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
		cycleSpeed 30
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		getBitmap -1
		BAD_SELECTOR -1
		BAD_SELECTOR 0
	)
	
	(method (init)
		(super init: &rest)
		(= BAD_SELECTOR (List new:))
		(self setVisibleRange: 1)
	)
	
	(method (dispose)
		(if BAD_SELECTOR
			(BAD_SELECTOR release: dispose:)
			(= BAD_SELECTOR 0)
		)
		(super dispose:)
	)
	
	(method (doVerb &tmp temp0 temp1 temp2)
		(= temp0 (- x (theLavaStone x?)))
		(= temp1 (- y (theLavaStone y?)))
		(= temp2 -1)
		(cond 
			((< temp0 -30)
				(cond 
					((< temp1 0) (= temp2 7))
					((> temp1 0) (= temp2 5))
					(else (= temp2 1))
				)
			)
			((> temp0 30)
				(cond 
					((< temp1 0) (= temp2 6))
					((> temp1 0) (= temp2 4))
					(else (= temp2 0))
				)
			)
			((< temp1 0) (= temp2 3))
			((> temp1 0) (= temp2 2))
			(else (return))
		)
		(= theLavaStone self)
		(poTorinJumper setLoop: temp2 cel: 0 setCycle: End self)
	)
	
	(method (onMe theObjOrX)
		(cond 
			((self isKindOf: NearLavaStone)
				(if
					(or
						(not theLavaStone)
						(BAD_SELECTOR contains: (theLavaStone getBitmap?))
					)
					(super onMe: theObjOrX)
				)
				(return)
			)
			((self isKindOf: FarLavaStone)
				(if (== theLavaStone [lavaStoneSel_141 33])
					(super onMe: theObjOrX)
				)
				(return)
			)
			((self isKindOf: AlmostFarLavaStone)
				(if
					(or
						(== theLavaStone [lavaStoneSel_141 34])
						(and
							theLavaStone
							(== (poTorinJumper cel:) 0)
							(BAD_SELECTOR contains: (theLavaStone getBitmap?))
						)
					)
					(super onMe: theObjOrX)
				)
				(return)
			)
			(else
				(if
					(and
						theLavaStone
						(== (poTorinJumper cel:) 0)
						(super onMe: theObjOrX)
					)
					(BAD_SELECTOR contains: (theLavaStone getBitmap?))
				)
				(return)
			)
		)
	)
	
	(method (cue)
		(theGame handsOn:)
		(poTorinJumper
			nCurPosY: [local2 BAD_SELECTOR]
			loop: 0
			cel: 0
			posn: x y
		)
		(UpdateScreenItem poTorinJumper)
		(= theLavaStone self)
		(self setScript: (SoStoneSink new:))
	)
	
	(method (BAD SELECTOR theBAD SELECTOR param2)
		(= BAD_SELECTOR theBAD_SELECTOR)
		(self nCurPosY: [local2 BAD_SELECTOR])
		(localproc_03c4 self theBAD_SELECTOR param2)
	)
	
	(method (add param1 &tmp temp0)
		(= temp0 0)
		(while (< temp0 argc)
			(BAD_SELECTOR add: [param1 temp0])
			(++ temp0)
		)
	)
)

(class NearLavaStone of LavaStone
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
		view -24734
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
		cycleSpeed 30
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		getBitmap -1
		BAD_SELECTOR -1
		BAD_SELECTOR 0
	)
	
	(method (doVerb)
		(if theLavaStone
			(super doVerb: &rest)
		else
			(= theLavaStone self)
			(self setScript: soEgoToJumper self)
		)
	)
	
	(method (cue)
		(theGame handsOn:)
		(poTorinJumper
			nCurPosY: [local2 BAD_SELECTOR]
			loop: 0
			cel: 0
			posn: x y
		)
		(UpdateScreenItem poTorinJumper)
		(= theLavaStone self)
	)
)

(instance soJumperToEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not theLavaStone) (self dispose:) (return))
				(theGame handsOff:)
				(Prints {Anim: jumper to stopwalk})
				(self cue:)
			)
			(1 (= ticks 2))
			(2
				(= theLavaStone 0)
				(ego
					oPanner:
					init:
					posn: (poTorinJumper x?) (poTorinJumper y?)
					setPri: 650
				)
				(poTorinJumper dispose:)
				(self dispose:)
			)
		)
	)
)

(instance soEgoToJumper of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: PolyPath (theLavaStone x?) (theLavaStone y?) self
				)
			)
			(1 (= ticks 2))
			(2 (ego setHeading: 0 self))
			(3
				(Prints {Anim of torin to jumping position})
				(self cue:)
			)
			(4
				(poTorinJumper
					init:
					posn: (theLavaStone x?) (theLavaStone y?)
					nCurPosY: [local2 (theLavaStone BAD SELECTOR?)]
				)
				(ego dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soEgoToJumpBackward of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: soEgoToJumper self)
			)
			(1
				(poTorinJumper
					posn: (theLavaStone x?) (theLavaStone y?)
					nCurPosY: [local2 (theLavaStone BAD SELECTOR?)]
					setLoop: 2
					cel: 0
					setCycle: End self
				)
			)
			(2 (self dispose:))
		)
	)
)

(class AlmostFarLavaStone of LavaStone
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
		view -24734
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
		cycleSpeed 30
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		getBitmap -1
		BAD_SELECTOR -1
		BAD_SELECTOR 0
	)
	
	(method (doVerb)
		(if (== theLavaStone [lavaStoneSel_141 34])
			(self setScript: soEgoToJumpBackward self)
		else
			(super doVerb: &rest)
		)
	)
)

(class FarLavaStone of LavaStone
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
		view -24734
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
		cycleSpeed 30
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		getBitmap -1
		BAD_SELECTOR -1
		BAD_SELECTOR 0
	)
	
	(method (cue)
		(theGame handsOn:)
		(= theLavaStone self)
		(localproc_0316)
		(ego
			oPanner:
			init:
			posn: x y
			nCurPosY: [local2 BAD_SELECTOR]
			setPri: 650
		)
		(poTorinJumper dispose:)
	)
)

(class SoStoneSink of Script
	(properties
		scratch 0
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setTotalWidth: 1
					cycleSpeed: 30
					setCycle: End self
				)
				(++ local1)
				(oStoneSound lThumbLoop: -24735)
			)
			(1
				(poBridge cel: local1)
				(if (== theLavaStone client)
					(oStoneSound lThumbLoop: -24733)
					(theGame handsOff:)
					(poTorinSinker
						init:
						posn: (client x?) (client y?)
						setScale: poTorinJumper
						cel: 0
						setCycle: End self
					)
					(poTorinJumper dispose:)
				)
			)
			(2
				(poTorinSinker dispose:)
				(if ((ScriptID 64019 0) show: 0 42 3)
					(theGame handsOff:)
					(curRoom setScript: soTorinEnter)
				)
				(self dispose:)
			)
		)
	)
)

(instance oStoneSound of TPSound
	(properties)
)

(instance foExit of ExitFeature
	(properties
		approachX 580
		approachY 47
		x 580
		y 47
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 628 109 579 109 574 0 628 0
					yourself:
				)
		)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 3))
	)
	
	(method (doVerb)
		(ego oPlane: self self)
	)
	
	(method (cue)
		(curRoom newRoom: -24636)
	)
)

(instance poTorinSinker of Prop
	(properties
		view -24733
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 650)
	)
)

(instance poTorinJumper of Prop
	(properties
		view -24735
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 650)
	)
)

(instance poBridge of Prop
	(properties
		x 439
		y 35
		view -24734
		loop 3
	)
)

(instance soTorinEnter of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= temp0 0)
				(while (< temp0 37)
					([lavaStoneSel_141 temp0]
						cel: ([lavaStoneSel_141 temp0] lastCel:)
					)
					(++ temp0)
				)
				(= theLavaStone 0)
				(ego
					oPanner:
					init:
					posn: 418 728
					setPri: 650
					setMotion: MoveTo 280 617 self
				)
				(poBridge cel: local1)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poNearPusher of Prop
	(properties
		x 238
		y 611
		view -5434
		loop 1
	)
)

(instance soResetNear of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (<= local1 0) (self dispose:))
				(self setScript: soJumperToEgo self)
			)
			(1
				(ego
					setMotion: PolyPath (poNearPusher x?) (poNearPusher y?) self
				)
			)
			(2 (= ticks 2))
			(3
				(theGame handsOff:)
				(ego setHeading: 270 self)
			)
			(4
				(if (not ((ScriptID 64017 0) test: 104))
					((ScriptID 64017 0) set: 104)
					(messager say: 1 1 1 1 self)
				else
					(self cue:)
				)
				(ego hide:)
				(poNearPusher init: setCycle: End self)
			)
			(5)
			(6 (localproc_0af9 self))
			(7
				(ego show:)
				(poNearPusher dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance voFarResetHandle of View
	(properties
		x 342
		y 98
		view -24732
		loop 1
	)
)

(instance foFarReset of Feature
	(properties
		x 345
		y 79
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 341 90 338 79 346 69 353 83 350 88
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(if (not ((ScriptID 64017 0) test: 105))
			((ScriptID 64017 0) set: 105)
			(messager say: 2 1 2 1)
		)
		(localproc_0af9 (ScriptID 64020 0))
	)
	
	(method (onMe theObjOrX)
		(if
		(and theLavaStone (== 34 (theLavaStone getBitmap?)))
			(super onMe: theObjOrX)
		)
	)
)

(instance voNearResetHandle of View
	(properties
		x 180
		y 561
		view -24732
	)
)

(instance foNearReset of Feature
	(properties
		x 184
		y 529
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 193 513 176 514 176 541 178 545 186 545
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(curRoom setScript: soResetNear)
	)
	
	(method (onMe theObjOrX)
		(if
			(and
				(> local1 0)
				(or
					(not theLavaStone)
					(and
						theLavaStone
						(or
							(== 35 (theLavaStone getBitmap?))
							(== 36 (theLavaStone getBitmap?))
						)
					)
				)
			)
			(super onMe: theObjOrX)
		)
	)
)

(instance oNearShorePoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super
			init:
				452
				598
				414
				608
				404
				573
				389
				565
				377
				572
				369
				609
				261
				607
				254
				577
				240
				565
				227
				573
				222
				614
				174
				629
				145
				620
				132
				661
				468
				669
		)
	)
)

(instance oFarShorePoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super
			init: 302 46 316 70 255 102 318 105 333 86 364 83 338 70 341 49
		)
	)
	
	(method (dispose)
		(SetDebug)
	)
)

(instance oFarShoreWinnerPoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super
			init:
				586
				44
				297
				45
				316
				70
				255
				102
				318
				105
				333
				86
				365
				82
				338
				70
				341
				49
				587
				49
		)
	)
)

(instance oNullVoidPlane of TorScrollPlane
	(properties
		priority 20
		oBoogleFeatures 0
		oExtraPlanes 0
		oMainPlane 0
		addBoogleFeature 0
	)
	
	(method (nSeq)
		(AddPicAt self -24736 0 316)
		(AddPicAt self -24735 0 0)
	)
)

(instance roSteppingStones of TPRoom
	(properties)
	
	(method (init &tmp temp0)
		(super init: &rest)
		(= plane (oNullVoidPlane init: 632 632 yourself:))
		(= [local2 0] 100)
		(= [local2 1] 93)
		(= [local2 2] 86)
		(= [local2 3] 80)
		(= [local2 4] 74)
		(= [local2 5] 68)
		(= [local2 6] 63)
		(= [local2 7] 58)
		(= [local2 8] 54)
		(= [local2 9] 50)
		(= [local2 10] 46)
		(= [local2 11] 43)
		(= [local2 12] 40)
		(= [local2 13] 37)
		(= [local2 14] 34)
		(= [local2 15] 31)
		(= [local2 16] 29)
		(= [local2 17] 27)
		(= [local2 18] 25)
		(= [local2 19] 23)
		(= [local42 0] 0)
		(= temp0 1)
		(while (< temp0 20)
			(= [local42 temp0]
				(+
					[local42 temp0]
					[local42 (- (= [local42 temp0] (MulDiv 51 [local2 temp0] 100)) 1)]
				)
			)
			(++ temp0)
		)
		(oNearShorePoly init:)
		(curRoom addObstacle: oNearShorePoly)
		(localproc_0432)
		(foFarReset init:)
		(voFarResetHandle init:)
		(foNearReset init:)
		(voNearResetHandle init:)
		(poBridge init:)
		(curRoom setScript: soTorinEnter)
	)
	
	(method (dispose &tmp temp0)
		(= temp0 0)
		(while (< temp0 37)
			([lavaStoneSel_141 temp0] dispose:)
			(++ temp0)
		)
		(super dispose:)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
		(= local1 34)
		([lavaStoneSel_141 34] cue:)
	)
)
