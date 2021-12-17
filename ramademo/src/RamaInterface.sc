;;; Sierra Script 1.0 - (do not remove this comment)
(script# 90)
(include game.sh)
(use Main)
(use Procs)
(use DText)
(use Plane)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	RamaInterface 0
)

(local
	theGGame
	local1
)
(class RamaInterface of Cast
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
		plane 0
		priority 200
	)
	
	(method (init)
		(interfacePlane priority: priority init: 0 0 639 479)
		(= plane interfacePlane)
		((= gPlane_2 plane) addCast: self drawPic: -2)
		(optionsText setSize: 180)
		(self
			add:
				optionBar
				hiliteOptText
				optionsText
				thighComputerAnim
				interfaceBrdr
				leftEyeBall
				middleEyeBall
				rightEyeBall
			eachElementDo: #setPri priority
			eachElementDo: #plane plane
			eachElementDo: #init self
		)
		(cast add: self)
		(= gRamaInterface_2 self)
		((interfacePlane theFtrs?)
			add: optionsButton thighComputerButton ramanEyes
			eachElementDo: #plane interfacePlane
			eachElementDo: #init
		)
		(ramanEyes setHotspot: 11)
		(Lock 135 0 1)
	)
	
	(method (dispose)
	)
	
	(method (handleEvent event)
		(ramanEyes handleEvent: event)
		(return
			(cond 
				((not (user canInput:)) (return (event claimed: 1)))
				((super handleEvent: event) (return (event claimed: 1)))
				((plane handleEvent: event) (return (event claimed: 1)))
				(else (return 0))
			)
		)
	)
	
	(method (displayOptionsText param1)
		(if argc
			(optionsText text: param1)
		else
			(optionsText text: {_})
		)
		(optionsText draw:)
	)
	
	(method (displayUpArrow param1)
		(if (and argc param1)
			(upArrow setHotspot: 31 11 9 2)
		else
			(upArrow deleteHotspot:)
		)
	)
	
	(method (displayDownArrow param1)
		(if (and argc param1)
			(downArrow setHotspot: 31 11 9 2)
		else
			(downArrow deleteHotspot:)
		)
	)
	
	(method (displayComputer)
		(thighComputerAnim setCycle: EndLoop)
		((thighComputerAnim cycler?) caller: thighComputerAnim)
	)
)

(instance optionBar of Actor
	(properties
		x -230
		y 108
		view 200
		cel 2
		xStep 10
		moveSpeed 0
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 7 125 172 125 202 155 202 241 200 248 193 254 185 257 7 257
					yourself:
				)
		)
		(self setPri: 240 ignoreActors: 1)
		(hiliteOptText hide:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (show)
		(self setMotion: MoveTo 7 108)
		(RamaInterface displayOptionsText:)
		(self x: -229)
		(self cue:)
	)
	
	(method (handleEvent event)
		(cond 
			(mover (event claimed: 1))
			((< x 0))
			((event claimed?))
			((== (event type?) nullEvt)
				(event claimed: 1)
				(if (self onMe: event)
					(cond 
						((< mouseY 124) (hiliteOptText hide:))
						((< mouseY 146)
							(hiliteOptText cel: 0 y: 126 show:)
							(UpdateScreenItem hiliteOptText)
						)
						((< mouseY 167)
							(hiliteOptText cel: 1 y: 148 show:)
							(UpdateScreenItem hiliteOptText)
						)
						((< mouseY 188)
							(hiliteOptText cel: 2 y: 169 show:)
							(UpdateScreenItem hiliteOptText)
						)
						((< mouseY 210)
							(hiliteOptText cel: 3 y: 190 show:)
							(UpdateScreenItem hiliteOptText)
						)
						((< mouseY 231)
							(hiliteOptText cel: 4 y: 213 show:)
							(UpdateScreenItem hiliteOptText)
						)
						(else (hiliteOptText hide:))
					)
				else
					(hiliteOptText hide:)
				)
			)
			((& (event type?) mouseDown)
				(if (> x 0)
					(event claimed: 1)
					(if (self onMe: event)
						(cond 
							((and (< 123 mouseY) (< mouseY 146)))
							((and (< 145 mouseY) (< mouseY 167)))
							((and (< 209 mouseY) (< mouseY 231)) (= theGGame theGame) (= local1 103))
						)
					)
					(self hide:)
				)
			)
			(else (hiliteOptText hide:))
		)
	)
	
	(method (hide)
		(hiliteOptText hide:)
		(self setMotion: MoveTo -230 108 self)
	)
	
	(method (cue &tmp temp0)
		(asm
			_line_   184
			_file_   {filename}
			_line_   185
			lal      theGGame
			bnt      code_0658
			_line_   187
			pushi    2
			push    
			lsl      local1
			calle    Eval,  4
			_line_   188
			ldi      0
			sal      local1
			sal      theGGame
code_0658:
			_line_   190
code_065b:
			pTos     x
			ldi      65306
			gt?     
			bnt      code_06c8
			_line_   191
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp0
			_line_   192
			pushi    #y
			pushi    0
			send     4
			sag      mouseY
			_line_   193
			pushi    #handleEvent
			pushi    1
			lst      temp0
			self     6
			_line_   194
			pushi    0
			callk    FrameOut,  0
			_line_   195
			pushi    #dispose
			pushi    0
			lat      temp0
			send     4
			_line_   196
			pToa     mover
			bnt      code_065b
			_line_   197
			pushi    #doit
			pushi    0
			send     4
			_line_   198
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			_line_   199
			pushi    0
			callk    FrameOut,  0
			_line_   200
			pushi    #motionCue
			pushi    0
			self     4
			jmp      code_065b
code_06c8:
			_line_   204
			ret     
		)
	)
)

(instance hiliteOptText of View
	(properties
		x 17
		y 126
		view 1000
		cel 1
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 254 ignoreActors: 1)
		(self hide:)
	)
	
	(method (show)
		(if (> (optionBar x?) 0) (super show:))
	)
)

(instance interfaceBrdr of View
	(properties
		view 200
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 249 ignoreActors: 1)
	)
)

(instance optText of View
	(properties
		x 110
		y 35
		view 400
	)
)

(instance interfacePlane of Plane
	(properties)
)

(class InterfaceFeature of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum 90
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
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 31 11 9 2)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(1
				(theCursor setTempCursor: arrowCursor 31)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ramanEyes of InterfaceFeature
	(properties
		nsLeft 40
		nsTop 344
		nsRight 210
		nsBottom 454
	)
	
	(method (init)
		(super init:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?))
			((== (event type?) nullEvt)
				(if (self onMe: event)
					(leftEyeBall move: (event x?) (event y?))
					(middleEyeBall move: (event x?) (event y?))
					(rightEyeBall move: (event x?) (event y?))
				else
					(leftEyeBall center:)
					(middleEyeBall center:)
					(rightEyeBall center:)
				)
			)
		)
		(super handleEvent: event)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (theCursor hilite:))
			(else 
				(cond 
					((proc1111_20 72) ((ScriptID 75 0) resetInvObj: (theCursor invItem?)))
					(
						(or
							(and (not curRoom) (not (proc1111_20 1)))
							(and curRoom (not (curRoom inset?)))
						)
						((ScriptID 75 0)
							init: 0 curRoom 0 0 (theCursor invItem?)
						)
					)
				)
			)
		)
	)
)

(instance thighComputerButton of InterfaceFeature
	(properties
		nsLeft 220
		nsTop 350
		nsRight 289
		nsBottom 437
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(31
				(cond 
					((proc1111_20 1) (__proc44_3) (proc1111_19 1))
					(
						(or
							(and (not curRoom) (not (proc1111_20 72)))
							(and curRoom (not (curRoom inset?)))
						)
						(__proc44_2)
						(proc1111_18 1)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(class InterfaceView of View
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum 90
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
		signal $5021
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
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(1
				(theCursor setTempCursor: arrowCursor 31)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance upArrow of InterfaceFeature
	(properties
		nsLeft 575
		nsTop 359
		nsRight 616
		nsBottom 392
	)
	
	(method (init)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(31)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance downArrow of InterfaceFeature
	(properties
		nsLeft 576
		nsTop 395
		nsRight 612
		nsBottom 430
	)
	
	(method (init)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(31)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance optionsButton of InterfaceFeature
	(properties
		nsLeft 34
		nsTop 10
		nsRight 69
		nsBottom 32
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(31 (optionBar show:))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance optionsText of DText
	(properties
		x 110
		y 15
		priority 255
		fixPriority 1
		text {_}
		fore 218
		back 222
		font 0
	)
	
	(method (init)
		(self setPri: 254 ignoreActors: 1)
		(super init: &rest)
	)
)

(instance arrowCursor of View
	(properties
		view 600
		cel 9
	)
)

(class RamanEyeBall of Prop
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
		view 201
		loop 2
		cel 2
		bitmap 0
		yStep 2
		signal $5021
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
		code 0
	)
	
	(method (init)
		(self setPri: 254 ignoreActors: 1)
		(super init: &rest)
	)
	
	(method (move param1 param2 &tmp temp0)
		(if script (script dispose:))
		(cond 
			((< (= temp0 (/ (- param2 y) 7)) 0)
			(switch temp0
				(-1 (= loop 1))
				(else  (= loop 0))
			))
			((== temp0 0) (= loop 2))
			(else
			(switch temp0
				(1 (= loop 3))
				(else  (= loop 4))
			))
		)
		(cond 
			((< (= temp0 (/ (- param1 x) 7)) 0)
			(switch temp0
				(-1 (= cel 3))
				(else  (= cel 4))
			))
			((== temp0 0) (= cel 2))
			(else
			(switch temp0
				(1 (= cel 1))
				(else  (= cel 0))
			))
		)
	)
	
	(method (center)
		(if
		(and (or (!= cel 2) (!= loop 2)) (not script))
			(self setScript: (returnToCenter new:))
		)
	)
)

(instance leftEyeBall of RamanEyeBall
	(properties
		x 84
		y 390
	)
)

(instance middleEyeBall of RamanEyeBall
	(properties
		x 122
		y 383
		view 202
	)
)

(instance rightEyeBall of RamanEyeBall
	(properties
		x 162
		y 390
		view 203
	)
)

(instance returnToCenter of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(= temp1 0)
		(= temp0 0)
		(switch (= state newState)
			(0 (= ticks 6))
			(1
				(cond 
					((< (client cel?) 2) (client cel: (+ (client cel?) 1)))
					((> (client cel?) 2) (client cel: (- (client cel?) 1)))
					(else (= temp0 1))
				)
				(cond 
					((< (client loop?) 2) (client loop: (+ (client loop?) 1)))
					((> (client loop?) 2) (client loop: (- (client loop?) 1)))
					(else (= temp1 1))
				)
				(if (and temp1 temp0)
					(self dispose:)
				else
					(self changeState: 0)
				)
			)
		)
	)
)

(instance thighComputerAnim of Prop
	(properties
		x 214
		y 342
		view 204
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 250 ignoreActors: 1)
	)
	
	(method (cue)
		(super cue: &rest)
		(self dispose:)
	)
)
