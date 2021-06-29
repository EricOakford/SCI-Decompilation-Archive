;;; Sierra Script 1.0 - (do not remove this comment)
(script# TALKER)
(include game.sh)
(use Main)
(use Print)
(use Sync)
(use RandCyc)
(use Motion)
(use Game)
(use Actor)
(use System)


(class Blink of Cycle
	(properties
		waitCount 0
		lastCount 0
		waitMin 0
		waitMax 0
	)
	
	(method (init param1 param2)
		(if argc
			(= waitMin (/ param2 2))
			(= waitMax (+ param2 waitMin))
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
					(self init:)
				)
			)
			(
				(or
					(> (= blinkNextCel (self nextCel:)) (client lastCel:))
					(< blinkNextCel 0)
				)
				(= cycleDir (- cycleDir))
				(self cycleDone:)
			)
			(else (client cel: blinkNextCel))
		)
	)
	
	(method (cycleDone)
		(if (== cycleDir -1)
			(self init:)
		else
			(= waitCount (+ (Random waitMin waitMax) gameTime))
		)
	)
)

(class Narrator of Object
	(properties
		x -1
		y -1
		caller 0
		modNum -1
		disposeWhenDone 1
		ticks 0
		talkWidth 0
		keepWindow 0
		modeless 0
		font 0
		cueVal 0
		initialized 0
		showTitle 0
		color 0
		back 7
		freezeCast 0
		curVolume 0
		saveCursor 0
	)
	
	(method (init)
		(if cDAudio
			(self curVolume: (theGame masterVolume:))
			(if (>= (theGame masterVolume:) 4)
				(theGame masterVolume: (- curVolume 4))
			)
			(if (not modeless)
				(= saveCursor (theGame setCursor: waitCursor 1))
			)
		)
		(= initialized 1)
		(super init:)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(theDoits addToFront: self)
		(if freezeCast (= freezeCast cast) (= cast castOfOne))
	)
	
	(method (doit)
		(if (and (!= ticks -1) (> (- gameTime ticks) 0))
			(if
				(and
					(if cDAudio (== (DoAudio 6) -1) else 1)
					(not keepWindow)
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
			(if cDAudio (DoAudio 3))
			(= modNum -1)
			(= initialized 0)
			(mouseDownHandler delete: self)
			(keyDownHandler delete: self)
			(theDoits delete: self)
			(if (IsObject freezeCast)
				(cast dispose:)
				(= cast freezeCast)
				(= freezeCast 1)
			)
		)
		(if modelessDialog (modelessDialog dispose:))
		(if cDAudio (theGame masterVolume: curVolume))
		(if caller
			(if (not cuees) (= cuees (Set new:)))
			(cuees
				add: ((Cue new:)
					cuee: caller
					cuer: self
					register: cueVal
					yourself:
				)
			)
		)
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
						(and
							(or
								(& eventType $4101)
								(and
									(& eventType keyDown)
									(OneOf (event message?) 13 27)
								)
							)
							(modelessDialog handleEvent: event)
						)
						(event claimed: 1)
						(self dispose: disposeWhenDone)
					)
				)
			)
		)
	)
	
	(method (say param1 param2 param3 param4 param5 param6 param7)
		(if (not initialized) (self init:))
		(= caller (if (and (> argc 1) param2) param2 else 0))
		(if (& msgType $0001) (self startText: param1))
		(if (& msgType $0002)
			(self startAudio: param3 param4 param5 param6 param7)
		)
		(= ticks (+ ticks 60 gameTime))
		(return 1)
	)
	
	(method (startText param1 &tmp temp0)
		(if (not (& msgType $0002))
			(= ticks
				(Max 120 (* textSpeed 2 (= temp0 (StrLen param1))))
			)
		)
		(if modelessDialog (modelessDialog dispose:))
		(self display: param1)
		(return temp0)
	)
	
	(method (display theText &tmp theTalkWidth newSystemWindow newPrint)
		(if (> (+ x talkWidth) 318)
			(= theTalkWidth (- 318 x))
		else
			(= theTalkWidth talkWidth)
		)
		((= newSystemWindow (systemWindow new:))
			color: color
			back: back
		)
		(= newPrint (Print new:))
		(if showTitle (newPrint addTitle: name))
		(newPrint
			window: newSystemWindow
			posn: x y
			font: font
			width: theTalkWidth
			addText: theText
			modeless: 1
			init:
		)
	)
	
	(method (startAudio param1 param2 param3 param4 param5)
		(= ticks (DoAudio 2 param1 param2 param3 param4 param5))
	)
)

(class Talker of Narrator
	(properties
		talkWidth 318
		frame 0
		bust 0
		eyes 0
		mouth 0
		view 0
		loop 0
		cel 0
		priority 15
		viewInPrint 0
		textX 0
		textY 0
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
				((TalkerMouth new:)
					view: view
					loop: loop
					cel: cel
					x: x
					y: y
					yourself:
				)
			)
		)
		(mouth init: setPri: priority)
		(if bust (bust setPri: priority init:))
		(if eyes (eyes setPri: priority init:))
		(if frame (frame setPri: priority init:))
		(Animate (cast elements?) 0)
	)
	
	(method (dispose param1)
		(if mouth (mouth setCycle: 0 setCel: 0))
		(if (or (not argc) param1)
			(if eyes (eyes setCycle: 0 setCel: 0))
			(if mouth (mouth dispose: delete:) (= mouth 0))
			(if bust (bust dispose:) (= bust 0))
			(if eyes (eyes dispose:) (= eyes 0))
			(if frame (frame dispose:) (= frame 0))
		)
		(super dispose: param1)
	)
	
	(method (startText &tmp temp0)
		(= temp0 (super startText: &rest))
		(if mouth (mouth setCycle: RandCycle (* 3 temp0) 0 1))
		(if (and eyes (not (eyes cycler?)))
			(eyes setCycle: Blink blinkSpeed)
		)
	)
	
	(method (display theText &tmp temp0 theTalkWidth temp2 newSystemWindow temp4 newPrint)
		((= newSystemWindow (systemWindow new:))
			color: color
			back: back
		)
		(= temp4 (cond 
				(frame)
				(bust)
				(else mouth)
			))
		(= newPrint (Print new:))
		(if viewInPrint
			(= temp0 (temp4 loop?))
			(if showTitle (newPrint addTitle: name))
			(newPrint
				window: newSystemWindow
				posn: x y
				modeless: 1
				font: font
				addText: theText
				addIcon: view temp0 cel 0 0
				init:
			)
		else
			(if (not (+ textX textY))
				(= textX
					(+
						(CelWide (temp4 view?) (temp4 loop?) (temp4 cel?))
						5
					)
				)
			)
			(if
				(>
					(+ (= temp2 (+ (temp4 nsLeft?) textX)) talkWidth)
					318
				)
				(= theTalkWidth (- 318 temp2))
			else
				(= theTalkWidth talkWidth)
			)
			(if showTitle (newPrint addTitle: name))
			(newPrint
				window: newSystemWindow
				posn: (+ (temp4 x?) textX) (+ (temp4 y?) textY)
				modeless: 1
				font: font
				width: theTalkWidth
				addText: theText
				init:
			)
		)
	)
	
	(method (startAudio param1 param2 param3 param4 param5)
		(super startAudio: param1 param2 param3 param4 param5)
		(if mouth
			(mouth
				setCycle: MouthSync param1 param2 param3 param4 param5
			)
		)
		(if (and eyes (not (eyes cycler?)))
			(eyes setCycle: Blink blinkSpeed)
		)
	)
)

(class TalkerMouth of Prop
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
	)
	
	(method (init)
		(= signal (& signal $feff))
		(super init:)
	)
)

(instance castOfOne of EventHandler)
