;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64928)
(include sci.sh)
(use Main)
(use Print)
(use RandCyc)
(use Motion)
(use Actpr)
(use System)


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

(class Narrator of Obj
	(properties
		scratch 0
		x -1
		y -1
		caller 0
		modNum -1
		disposeWhenDone 1
		ticks 0
		talkWidth 0
		modeless 1
		font 0
		cueVal 0
		initialized 0
		showTitle 0
		fore 0
		back 7
		dialog 0
		curVolume 0
		saveCursor 0
		audModNum 0
		audNoun 0
		audVerb 0
		audCase 0
		audSequence 0
	)
	
	(method (init)
		(if (& msgType $0002)
			(= curVolume (theGame masterVolume:))
			(if (>= curVolume 6)
				(theGame masterVolume: (- curVolume 6))
			else
				(theGame masterVolume: 1)
			)
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
		(if (& msgType $0002) (theGame masterVolume: curVolume))
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
							((& eventType evMENUHIT) (= cueVal 0))
							((& eventType evMOUSEBUTTON) (= cueVal (& (event modifiers?) emSHIFT)))
							((& eventType evVERB)
								(= cueVal (& (event modifiers?) emSHIFT))
								(event type: 1 message: 0 modifiers: 0)
							)
							((& eventType evKEYBOARD) (= cueVal (== (event message?) KEY_ESCAPE)))
						)
					)
					(if
						(or
							(& eventType $4021)
							(and
								(& eventType evKEYBOARD)
								(OneOf (event message?) 13 27)
							)
						)
						(if (& msgType $0001)
							(if (dialog handleEvent: event)
								(event claimed: 1)
								(self dispose: disposeWhenDone)
							)
						else
							(event claimed: 1)
							(self dispose: disposeWhenDone)
						)
					)
				)
			)
		)
	)
	
	(method (say param1 param2 param3)
		(if (not initialized) (self init:))
		(= caller (if (and (> argc 1) param2) param2 else 0))
		(if (& msgType $0001) (self startText: param1))
		(if (& msgType $0002) (self startAudio: param3))
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
	
	(method (display theText &tmp theTalkWidth newPrint)
		(if (> (+ x talkWidth) 318)
			(= theTalkWidth (- 318 x))
		else
			(= theTalkWidth talkWidth)
		)
		(= newPrint (Print new:))
		(if showTitle (newPrint addTitle: name))
		(newPrint
			fore: fore
			back: back
			posn: x y
			font: font
			width: theTalkWidth
			addText: theText
			modeless: 2
			init:
		)
		(prints delete: newPrint)
		(= dialog (newPrint dialog?))
	)
	
	(method (startAudio param1 &tmp temp0)
		(= audModNum (param1 at: 0))
		(= audNoun (param1 at: 1))
		(= audVerb (param1 at: 2))
		(= audCase (param1 at: 3))
		(= audSequence (param1 at: 4))
		(= ticks
			(DoAudio 2 audModNum audNoun audVerb audCase audSequence)
		)
	)
	
	(method (isModeless param1)
		(return (if argc (== modeless param1) else modeless))
	)
)

(class Talker of Narrator
	(properties
		scratch 0
		x -1
		y -1
		caller 0
		modNum -1
		disposeWhenDone 1
		ticks 0
		talkWidth 318
		modeless 1
		font 0
		cueVal 0
		initialized 0
		showTitle 0
		fore 0
		back 7
		dialog 0
		curVolume 0
		saveCursor 0
		audModNum 0
		audNoun 0
		audVerb 0
		audCase 0
		audSequence 0
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
		(mouth init: setPri: priority)
		(if bust (bust setPri: priority init:))
		(if eyes (eyes setPri: priority init:))
		(if frame (frame setPri: priority init:))
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
	
	(method (display theText &tmp temp0 theTalkWidth temp2 temp3 newPrint)
		(= temp3 (cond 
				(frame)
				(bust)
				(else mouth)
			))
		(= newPrint (Print new:))
		(if viewInPrint
			(= temp0 (temp3 loop?))
			(if showTitle (newPrint addTitle: name))
			(newPrint
				fore: fore
				back: back
				posn: x y
				modeless: 2
				font: font
				addText: theText
				addIcon: view temp0 cel 0 0
				init:
			)
		else
			(if (not (+ textX textY))
				(= textX
					(+
						(CelWide (temp3 view?) (temp3 loop?) (temp3 cel?))
						5
					)
				)
			)
			(if
				(>
					(+ (= temp2 (+ (temp3 nsLeft?) textX)) talkWidth)
					318
				)
				(= theTalkWidth (- 318 temp2))
			else
				(= theTalkWidth talkWidth)
			)
			(if showTitle (newPrint addTitle: name))
			(newPrint
				fore: fore
				back: back
				posn: (+ (temp3 x?) textX) (+ (temp3 y?) textY)
				modeless: 2
				font: font
				width: theTalkWidth
				addText: theText
				init:
			)
		)
		(prints delete: newPrint)
		(= dialog (newPrint dialog?))
	)
	
	(method (startAudio param1 &tmp [temp0 5])
		(super startAudio: param1)
		(if mouth (mouth setCycle: RandCycle ticks 0 1))
		(if (and eyes (not (eyes cycler?)))
			(eyes setCycle: Blink blinkSpeed)
		)
	)
)
