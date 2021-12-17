;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64928)
(include game.sh)
(use Main)
(use TPSound)
(use TranslucentPlane)
(use GenDialog)
(use DText)
(use String)
(use Print)
(use Dialog)
(use Sync)
(use Scaler)
(use RandCyc)
(use Motion)
(use Actor)
(use System)

(public
	proc64928_0 0
	proc64928_1 1
	proc64928_2 2
)

(local
	local0 =  320
	local1 =  470
	local2 =  450
	local3
)
(procedure (proc64928_0 param1 &tmp newPrint temp1)
	(if (oTranslucentDialog bInitialized?)
		(MonoOut
			{Tried to create two subtitles at same time. talker.sc}
		)
		(oTranslucentDialog dispose:)
	)
	(= temp1 (GetTextWidth param1 local3 local2))
	(= newPrint (Print new:))
	(newPrint
		fore: 87
		back: 0
		skip: 0
		posn: -1 -1
		font: local3
		width: temp1
		dialog: oTranslucentDialog
		addText: param1
		modeless: 2
		plane: (ScriptID 0 1)
		init:
	)
	(prints delete: newPrint)
	(newPrint dialog?)
)

(procedure (proc64928_1 param1 param2 param3 param4 param5 &tmp temp0 temp1)
	(if (< argc 5)
		(MonoOut
			{Passed less than the minimum no. of args to MakeMessageSubTitle}
		)
		(return 0)
	)
	(if
	(not (Message 0 param1 param2 param3 param4 param5))
		(MonoOut
			{MakeMessageSubtitle: No message found m:%hu n:%d v:%d c:%d s:%d}
			param1
			param2
			param3
			param4
			param5
		)
		(return 0)
	)
	(= temp0 (String newWith: 4000 {}))
	(Message
		0
		param1
		param2
		param3
		param4
		param5
		(temp0 data?)
	)
	(= temp1 (proc64928_0 temp0))
	(temp0 dispose:)
	(return temp1)
)

(procedure (proc64928_2 param1 param2 param3 param4)
	(if (> argc 0) (= local0 param1) else (= local0 320))
	(if (> argc 1) (= local1 param2) else (= local1 470))
	(if (> argc 2) (= local2 param3) else (= local2 450))
	(if (> argc 0) (= local3 param4) else (= local3 0))
)

(instance oTextTranslucentPlane of TranslucentPlane
	(properties)
)

(class oTranslucentDialog of Dialog
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
		text 0
		font 0
		theItem 0
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		ticks 0
		caller 0
		plane 0
		eatTheMice 0
		lastTicks 0
		mouseHiliting 0
		margin 4
		modeless 0
		onScreen 0
		bInitialized 0
	)
	
	(method (init)
		(= bInitialized 1)
		(super init: &rest)
	)
	
	(method (dispose)
		(if bInitialized
			(= bInitialized 0)
			(oTextTranslucentPlane dispose:)
			(super dispose: &rest)
		)
	)
	
	(method (moveTo)
		(super moveTo: &rest)
		(if (oTextTranslucentPlane oMyFeatures?)
			(oTextTranslucentPlane dispose:)
		)
		(oTextTranslucentPlane
			priority: (+ 1 (GetHighPlanePri))
			init: (plane left:) (plane top?) (plane right:) (plane bottom?)
		)
		(plane setPri: (+ 1 (oTextTranslucentPlane priority?)))
	)
	
	(method (center &tmp temp0 temp1)
		(super center: &rest)
		(= temp0 (+ (- nsRight nsLeft) 1))
		(= temp1 (+ nsLeft (/ temp0 2)))
		(self move: (- local0 temp1) (- local1 nsBottom))
	)
)

(class TPText of DText
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
		signal $0000
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
		type $0002
		key 0
		value 0
		object 0
		selector 0
		textLeft 0
		textTop 0
		textRight 0
		textBottom 0
		text 0
		mode 0
		fore 0
		back 0
		skip 254
		font 1
		borderColor 0
		dimmed 0
		rects 0
	)
)

(class Blink of Cycle
	(properties
		scratch 0
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		clientLastCel 0
		waitCount 0
		lastCount 0
		waitMin 0
		waitMax 0
	)
	
	(method (init param1 param2)
		(if argc
			(= waitMin (/ param2 2))
			(= waitMax (+ param2 waitMin))
			(= waitCount (+ (Random 0 waitMax) gameTime))
			(super init: param1)
		else
			(super init:)
		)
	)
	
	(method (doit &tmp blinkNextCel)
		(cond 
			(waitCount
				(if (> (- gameTime waitCount) 0)
					(= waitCount 0)
					(= cycleCnt gameTime)
				)
			)
			(
			(!= (= blinkNextCel (self nextCel:)) (client cel?))
				(if
				(or (> blinkNextCel clientLastCel) (< blinkNextCel 0))
					(= cycleDir (- cycleDir))
					(self cycleDone:)
				else
					(client cel: blinkNextCel)
				)
			)
		)
	)
	
	(method (cycleDone)
		(if (== cycleDir -1)
			(= cycleCnt gameTime)
		else
			(= waitCount (+ (Random waitMin waitMax) gameTime))
		)
	)
)

(class Narrator of Object
	(properties
		scratch 0
		x -1
		y -1
		textX -1
		textY -1
		caller 0
		modNum -1
		disposeWhenDone 1
		ticks 0
		talkWidth 400
		modeless 2
		font 0
		cueVal 0
		initialized 0
		showTitle 0
		fore 232
		back 227
		dialog 0
		curVolume 0
		saveCursor 0
		audModNum 0
		audNoun 0
		audVerb 0
		audCase 0
		audSequence 0
		curSFXVolume 0
	)
	
	(method (init)
		(if (& msgType $0002)
			(proc64031_0
				(MulDiv 55 (= curVolume gOFileReadWord_3) 100)
			)
			(= curSFXVolume gOFileReadWord_4)
		)
		(= initialized 1)
		(super init:)
		(talkers addToFront: self)
	)
	
	(method (doit)
		(if (and (!= ticks -1) (> (- gameTime ticks) 0))
			(if
				(and
					(if (& msgType $0002)
						(==
							(DoAudio 6 audModNum audNoun audVerb audCase audSequence)
							-1
						)
					else
						1
					)
					(or (!= modeless 0) (& msgType $0002))
				)
				(self dispose: disposeWhenDone)
				(return 0)
			)
		)
		(return 1)
	)
	
	(method (dispose param1)
		(= ticks -1)
		(if (or (not argc) param1)
			(if (& msgType $0002)
				(DoAudio 3 audModNum audNoun audVerb audCase audSequence)
			)
			(= modNum -1)
			(= initialized 0)
			(talkers delete: self)
		)
		(if dialog (dialog dispose:) (= dialog 0))
		(if (& msgType $0002)
			(proc64031_0 curVolume)
			(proc64031_1 curSFXVolume)
		)
		(if caller (caller cue: cueVal))
		(= cueVal 0)
		(if (or (not argc) param1) (super dispose:))
	)
	
	(method (handleEvent event &tmp eventType)
		(return
			(cond 
				((event claimed?))
				((== ticks -1) (return 0))
				(else
					(= eventType (event type?))
					(if (not cueVal)
						(cond 
							((& eventType joyDown) (= cueVal 0))
							((& eventType mouseDown) (= cueVal (& (event modifiers?) shiftDown)))
							((& eventType userEvent)
								(= cueVal (& (event modifiers?) shiftDown))
								(event type: 1 message: 0 modifiers: 0)
							)
							((& eventType keyDown) (= cueVal (== (event message?) ESC)))
						)
					)
					(if
						(or
							(& eventType $4021)
							(and
								(& eventType keyDown)
								(OneOf (event message?) 13 27)
							)
						)
						(event claimed: 1)
						(self dispose:)
					)
				)
			)
		)
	)
	
	(method (say param1 param2 param3)
		(if (not initialized)
			(self init:)
		else
			(MonoOut
				{Tried to say two messages simultaneously with one talker. mle}
			)
			(if dialog (dialog dispose:) (= dialog 0))
		)
		(= caller (if (and (> argc 1) param3) param3 else 0))
		(if (& msgType $0001) (self startText: param1 param2))
		(if (& msgType $0002)
			(self startAudio: param2 param1)
			0
		)
		(= ticks (+ ticks 60 gameTime))
		(return 1)
	)
	
	(method (startText param1 &tmp temp0)
		(if (not (& msgType $0002))
			(= temp0 (param1 size:))
			(= ticks (Max 120 (* (/ (* 24 temp0) 10) textSpeed)))
		)
		(self display: param1)
		(return ticks)
	)
	
	(method (display theText)
		(= dialog (proc64928_0 theText))
	)
	
	(method (startAudio param1 param2 &tmp temp0)
		(= audModNum (param1 at: 0))
		(= audNoun (param1 at: 1))
		(= audVerb (param1 at: 2))
		(= audCase (param1 at: 3))
		(= audSequence (param1 at: 4))
		(= ticks
			(DoAudio
				2
				audModNum
				audNoun
				audVerb
				audCase
				audSequence
				1
				gOFileReadWord_5
			)
		)
		(if (> ticks 0) (= ticks (Max 1 (- ticks 30))))
		(if (<= ticks 0)
			(MonoOut
				{Missing audio for %d %d %d %d %hu}
				audNoun
				audVerb
				audCase
				audSequence
				audModNum
			)
		)
		(if (<= ticks 0)
			(= temp0 (param2 size:))
			(= ticks (Max 120 (* (/ (* 24 temp0) 10) textSpeed)))
			(if (not dialog) (self display: param2))
		)
	)
	
	(method (isModeless param1)
		(return (if argc (== modeless param1) else modeless))
	)
	
	(method (kill)
		(= caller 0)
		(self dispose:)
	)
)

(class Mouth of Prop
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
		cycleSpeed 7
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		code 0
		client 0
	)
	
	(method (init &tmp clientScaler)
		(if
			(or
				(not client)
				(not (& (client -info-?) $0010))
				(not (client plane?))
				(not ((client plane?) isEnabled:))
			)
			(return)
		)
		(if (client respondsTo: #stopwalk) (client stopwalk:))
		(= loop (client cel?))
		(= x (client x?))
		(= y (client y?))
		(self setPri: (+ 1 (client priority?)))
		(self
			oldScaleX: 128
			scaleX: 128
			scaleY: 128
			setScale: 0
			setScaler: 0
		)
		(cond 
			((= clientScaler (client scaler?))
				(clientScaler doit:)
				(self
					setScaler:
						Scaler
						(clientScaler frontSize?)
						(clientScaler backSize?)
						(clientScaler frontY?)
						(clientScaler backY?)
				)
			)
			((& (client scaleSignal?) $0001)
				(self
					setScale:
					scaleX: (client scaleX?)
					scaleY: (client scaleY?)
				)
			)
		)
		(UpdateScreenItem client)
		(super init: &rest)
	)
)

(class Talker of Narrator
	(properties
		scratch 0
		x -1
		y -1
		textX -1
		textY -1
		caller 0
		modNum -1
		disposeWhenDone 1
		ticks 0
		talkWidth 400
		modeless 2
		font 0
		cueVal 0
		initialized 0
		showTitle 0
		fore 232
		back 227
		dialog 0
		curVolume 0
		saveCursor 0
		audModNum 0
		audNoun 0
		audVerb 0
		audCase 0
		audSequence 0
		curSFXVolume 0
		frame 0
		bust 0
		eyes 0
		mouth 0
		view 0
		loop 0
		cel 0
		priority 15
		viewInPrint 0
		blinkSpeed 100
	)
	
	(method (init theMouth theBust theEyes theFrame)
		(if argc
			(= mouth theMouth)
			(if (> argc 1)
				(= bust theBust)
				(if (> argc 2)
					(= eyes theEyes)
					(if (> argc 3) (= frame theFrame))
				)
			)
		)
		(super init:)
		(if (not mouth)
			(= mouth
				((Prop new:)
					view: view
					loop: loop
					cel: cel
					x: x
					y: y
					yourself:
				)
			)
		)
		(if bust (bust setPri: priority init:))
		(if eyes (eyes setPri: priority init:))
		(if frame (frame setPri: priority init:))
		(mouth setPri: priority init:)
	)
	
	(method (dispose param1)
		(if mouth (mouth setCycle: 0 setCel: 0))
		(if (or (not argc) param1)
			(if eyes (eyes setCycle: 0 setCel: 0))
			(if mouth (mouth dispose:) (= mouth 0))
			(if bust (bust dispose:) (= bust 0))
			(if eyes (eyes dispose:) (= eyes 0))
			(if frame (frame dispose:) (= frame 0))
		)
		(super dispose: param1)
	)
	
	(method (startText &tmp temp0)
		(= temp0 (super startText: &rest))
		(if mouth
			(mouth setCycle: RandCycle (* (/ temp0 3) 2) 0 1)
		)
		(if (and eyes (not (eyes cycler?)))
			(eyes setCycle: Blink blinkSpeed)
		)
	)
	
	(method (startAudio param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6)
		(if mouth
			(= temp0 (param1 at: 0))
			(= temp1 (param1 at: 1))
			(= temp2 (param1 at: 2))
			(= temp3 (param1 at: 3))
			(= temp4 (param1 at: 4))
			(= temp5 0)
			(= temp6 0)
			(mouth setCycle: MouthSync temp0 temp1 temp2 temp3 temp4)
		)
		(super startAudio: param1 param2)
		(if (and eyes (not (eyes cycler?)))
			(eyes setCycle: Blink blinkSpeed)
		)
	)
)
