;;; Sierra Script 1.0 - (do not remove this comment)
(script# 928)
(include game.sh)
(use Main)
(use Print)
(use Sync)
(use RandCyc)
(use Motion)
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

(class Narrator of Prop
	(properties
		caller NULL
		modNum -1
		disposeWhenDone TRUE
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
		curVolume 0
		saveCursor 0
	)
	
	(method (init &tmp theCurVolume_2 temp1 theCurVolume)
		(if (& msgType $0002)
			(= curVolume (theGame masterVolume:))
			(if (>= curVolume 4)
				(theGame masterVolume: curVolume)
				(= theCurVolume curVolume)
				(= theCurVolume_2 curVolume)
				(while (>= theCurVolume_2 (Max 3 (/ curVolume 2)))
					(theGame masterVolume: theCurVolume_2)
					(= temp1 0)
					(while (<= temp1 400)
						(++ temp1)
					)
					(-- theCurVolume_2)
				)
			)
		)
		(if
			(or
				(and (& msgType $0002) (not modeless))
				(not (HaveMouse))
			)
			(= saveCursor (theGame setCursor: waitCursor 1))
		)
		(= gameTime (+ tickOffset (GetTime)))
		(= initialized 1)
	)
	
	(method (doit)
		(if (and (!= ticks -1) (> (- gameTime ticks) 0))
			(if
				(and
					(if (& msgType $0002) (== (DoAudio 6) -1) else 1)
					(or (not keepWindow) (& msgType $0002))
				)
				(self dispose: disposeWhenDone)
				(return 0)
			)
		)
		(return 1)
	)
	
	(method (dispose param1 &tmp theGameMasterVolume temp1 temp2)
		(= ticks -1)
		(if (or (not argc) (== param1 1))
			(cond 
				(modeless
					(keyDownHandler delete: self)
					(mouseDownHandler delete: self)
					(theDoits delete: self)
				)
				((and fastCast (fastCast contains: self))
					(fastCast delete: self)
					(if (fastCast isEmpty:)
						(fastCast dispose:)
						(= fastCast 0)
					)
				)
			)
			(if (& msgType $0002) (DoAudio 3))
			(= modNum -1)
			(= initialized 0)
		)
		(if modelessDialog (modelessDialog dispose:))
		(if (& msgType $0002)
			(= theGameMasterVolume (theGame masterVolume:))
			(while (<= theGameMasterVolume curVolume)
				(theGame masterVolume: theGameMasterVolume)
				(= temp1 0)
				(while (<= temp1 400)
					(++ temp1)
				)
				(++ theGameMasterVolume)
			)
		)
		(if
			(or
				(and (& msgType $0002) (not modeless))
				(not (HaveMouse))
			)
			(theGame setCursor: saveCursor)
		)
		(if caller (caller cue: cueVal))
		(= cueVal 0)
		(DisposeClone self)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?))
				((== ticks -1) (return 0))
				(else
					(if (not cueVal)
						(switch (event type?)
							(joyDown (= cueVal 0))
							(mouseDown
								(= cueVal (& (event modifiers?) shiftDown))
							)
							(keyDown
								(= cueVal (== (event message?) ESC))
							)
						)
					)
					(if
						(or
							(& (event type?) $4101)
							(and
								(& (event type?) evKEYBOARD)
								(OneOf (event message?) 13 27)
							)
						)
						(event claimed: 1)
						(self dispose: disposeWhenDone)
					)
				)
			)
		)
	)
	
	(method (say param1 param2)
		(if theIconBar (theIconBar disable:))
		(if (not initialized) (self init:))
		(= caller (if (and (> argc 1) param2) param2 else 0))
		(if (& msgType $0001) (self startText: param1))
		(if (& msgType $0002) (self startAudio: param1))
		(cond 
			(modeless
				(mouseDownHandler addToFront: self)
				(keyDownHandler addToFront: self)
				(theDoits add: self)
			)
			((IsObject fastCast) (fastCast add: self))
			(else
				((= fastCast (EventHandler new:))
					name: {fastCast}
					add: self
				)
			)
		)
		(= ticks (+ ticks 60 gameTime))
		(return 1)
	)
	
	(method (startText param1 &tmp temp0)
		(if (not (& msgType $0002))
			(= ticks
				(Max 240 (* textSpeed 2 (= temp0 (StrLen param1))))
			)
		)
		(if modelessDialog (modelessDialog dispose:))
		(self display: param1)
		(return temp0)
	)
	
	(method (display theText &tmp theTalkWidth newSystemWindow)
		(if (> (+ x talkWidth) 318)
			(= theTalkWidth (- 318 x))
		else
			(= theTalkWidth talkWidth)
		)
		((= newSystemWindow (systemWindow new:))
			color: color
			back: back
		)
		(if (and (not (HaveMouse)) (!= theCursor 996))
			(= saveCursor theCursor)
			(theGame setCursor: 996)
		else
			(= saveCursor 0)
		)
		(if showTitle (Print addTitle: name))
		(Print
			window: newSystemWindow
			posn: x y
			font: font
			width: theTalkWidth
			addText: theText
			modeless: 1
			init:
		)
	)
	
	(method (startAudio param1 &tmp temp0 temp1 temp2 temp3 temp4)
		(= temp0 (WordAt param1 0))
		(= temp1 (WordAt param1 1))
		(= temp2 (WordAt param1 2))
		(= temp3 (WordAt param1 3))
		(= temp4 (WordAt param1 4))
		(= ticks (DoAudio 2 temp0 temp1 temp2 temp3 temp4))
	)
)

(class Talker of Narrator
	(properties
		bust 0
		eyes 0
		mouth 0
		viewInPrint 0
		textX 0
		textY 0
		useFrame 0
		blinkSpeed 100
		raving 0
		raveName 0
		saveX 0
		saveY 0
	)
	
	(method (init theBust theEyes theMouth)
		(if raving
			(= mouth (= eyes (= bust 0)))
		else
			(if argc
				(= bust theBust)
				(if (> argc 1)
					(= eyes theEyes)
					(if (> argc 2) (= mouth theMouth))
				)
			)
			(self setSize:)
		)
		(super init:)
	)
	
	(method (doit)
		(if (and (super doit:) mouth) (self cycle: mouth))
		(if eyes (self cycle: eyes))
	)
	
	(method (dispose param1)
		(if (and mouth underBits)
			(mouth cel: 0)
			(DrawCel
				(mouth view?)
				(mouth loop?)
				0
				(+ (mouth nsLeft?) nsLeft)
				(+ (mouth nsTop?) nsTop)
				-1
			)
		)
		(if (and mouth (mouth cycler?))
			(if ((mouth cycler?) respondsTo: #cue)
				((mouth cycler?) cue:)
			)
			(mouth setCycle: 0)
		)
		(if (or (not argc) (== param1 1))
			(if raving
				(Graph GRestoreBits underBits)
				(Graph
					GReAnimate
					(- y 10)
					(- x 10)
					(+ 10 (- y 10) (/ (* (CelHigh 5 0 0) 5) 11))
					(+ 10 (- x 10) (/ (CelWide 5 0 0) 2))
				)
				(= raving (= underBits 0))
			)
			(if (and eyes underBits)
				(eyes setCycle: 0 cel: 0)
				(DrawCel
					(eyes view?)
					(eyes loop?)
					0
					(+ (eyes nsLeft?) nsLeft)
					(+ (eyes nsTop?) nsTop)
					-1
				)
			)
			(if saveX (= x saveX) (= y saveY))
			(= saveY (= saveX 0))
			(self hide:)
		)
		(super dispose: param1)
	)
	
	(method (hide)
		(Graph GRestoreBits underBits)
		(= underBits 0)
		(Graph GReAnimate nsTop nsLeft nsBottom nsRight)
		(if theIconBar (theIconBar enable:))
	)
	
	(method (show &tmp temp0)
		(if (not underBits)
			(= underBits
				(Graph GSaveBits nsTop nsLeft nsBottom nsRight 3)
			)
		)
		(= temp0 (PicNotValid))
		(PicNotValid 1)
		(if bust
			(DrawCel
				(bust view?)
				(bust loop?)
				(bust cel?)
				(+ (bust nsLeft?) nsLeft)
				(+ (bust nsTop?) nsTop)
				-1
			)
		)
		(if eyes
			(DrawCel
				(eyes view?)
				(eyes loop?)
				(eyes cel?)
				(+ (eyes nsLeft?) nsLeft)
				(+ (eyes nsTop?) nsTop)
				-1
			)
		)
		(if mouth
			(DrawCel
				(mouth view?)
				(mouth loop?)
				(mouth cel?)
				(+ (mouth nsLeft?) nsLeft)
				(+ (mouth nsTop?) nsTop)
				-1
			)
		)
		(DrawCel view loop cel nsLeft nsTop -1)
		(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
		(PicNotValid temp0)
	)
	
	(method (say)
		(if (and (> view 0) (not underBits)) (self init:))
		(super say: &rest)
	)
	
	(method (startText &tmp temp0)
		(if (not viewInPrint) (self show:))
		(= temp0 (super startText: &rest))
		(if mouth (mouth setCycle: RandCycle (* 4 temp0) 0 1))
		(if (and eyes (not (eyes cycler?)))
			(eyes setCycle: Blink blinkSpeed)
		)
	)
	
	(method (display theText &tmp temp0 theTalkWidth temp2 newSystemWindow)
		((= newSystemWindow (systemWindow new:))
			color: color
			back: back
		)
		(if (and (not (HaveMouse)) (!= theCursor 996))
			(= saveCursor theCursor)
			(theGame setCursor: 996)
		else
			(= saveCursor 0)
		)
		(if viewInPrint
			(= temp0 (if useFrame loop else (bust loop?)))
			(if showTitle (Print addTitle: name))
			(Print
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
				(= textX (+ (- nsRight nsLeft) 5))
			)
			(if
			(> (+ (= temp2 (+ nsLeft textX)) talkWidth) 318)
				(= theTalkWidth (- 318 temp2))
			else
				(= theTalkWidth talkWidth)
			)
			(if showTitle (Print addTitle: name))
			(Print
				window: newSystemWindow
				posn: (+ x textX) (+ y textY)
				modeless: 1
				font: font
				width: theTalkWidth
				addText: theText
				init:
			)
		)
	)
	
	(method (startAudio param1 &tmp temp0 temp1 temp2 temp3 temp4 [temp5 13] temp18)
		(if (or raving (and (not raving) mouth))
			(= temp0 (WordAt param1 0))
			(= temp1 (WordAt param1 1))
			(= temp2 (WordAt param1 2))
			(= temp3 (WordAt param1 3))
			(= temp4 (WordAt param1 4))
		)
		(if (and raving raveName)
			(self showRave: temp0 temp1 temp2 temp3 temp4)
		else
			(self show:)
			(if mouth
				(if (ResCheck 147 temp0 temp1 temp2 temp3 temp4)
					(mouth setCycle: MouthSync temp0 temp1 temp2 temp3 temp4)
					(super startAudio: param1)
				else
					(= temp18 (super startAudio: param1))
					(mouth setCycle: RandCycle (* temp18 4) 0 1)
				)
			else
				(super startAudio: param1)
			)
			(if (and eyes (not (eyes cycler?)))
				(eyes setCycle: Blink blinkSpeed)
			)
		)
	)
	
	(method (cycle param1 &tmp temp0 [temp1 100])
		(if (and param1 (param1 cycler?))
			(= temp0 (param1 cel?))
			((param1 cycler?) doit:)
			(if (!= temp0 (param1 cel?))
				(DrawCel
					(param1 view?)
					(param1 loop?)
					(param1 cel?)
					(+ (param1 nsLeft?) nsLeft)
					(+ (param1 nsTop?) nsTop)
					-1
				)
				(param1
					nsRight:
						(+
							(param1 nsLeft?)
							(CelWide (param1 view?) (param1 loop?) (param1 cel?))
						)
				)
				(param1
					nsBottom:
						(+
							(param1 nsTop?)
							(CelHigh (param1 view?) (param1 loop?) (param1 cel?))
						)
				)
				(Graph
					GShowBits
					(+ (param1 nsTop?) nsTop)
					(+ (param1 nsLeft?) nsLeft)
					(+ (param1 nsBottom?) nsTop)
					(+ (param1 nsRight?) nsLeft)
					1
				)
			)
		)
	)
	
	(method (setSize)
		(= nsLeft x)
		(= nsTop y)
		(= nsRight
			(+
				nsLeft
				(Max
					(if view (CelWide view loop cel) else 0)
					(if (IsObject bust)
						(+
							(bust nsLeft?)
							(CelWide (bust view?) (bust loop?) (bust cel?))
						)
					else
						0
					)
					(if (IsObject eyes)
						(+
							(eyes nsLeft?)
							(CelWide (eyes view?) (eyes loop?) (eyes cel?))
						)
					else
						0
					)
					(if (IsObject mouth)
						(+
							(mouth nsLeft?)
							(CelWide (mouth view?) (mouth loop?) (mouth cel?))
						)
					else
						0
					)
				)
			)
		)
		(= nsBottom
			(+
				nsTop
				(Max
					(if view (CelHigh view loop cel) else 0)
					(if (IsObject bust)
						(+
							(bust nsTop?)
							(CelHigh (bust view?) (bust loop?) (bust cel?))
						)
					else
						0
					)
					(if (IsObject eyes)
						(+
							(eyes nsTop?)
							(CelHigh (eyes view?) (eyes loop?) (eyes cel?))
						)
					else
						0
					)
					(if (IsObject mouth)
						(+
							(mouth nsTop?)
							(CelHigh (mouth view?) (mouth loop?) (mouth cel?))
						)
					else
						0
					)
				)
			)
		)
	)
	
	(method (showRave param1 param2 param3 param4 param5 &tmp temp0)
		(if (not underBits)
			(= underBits
				(Graph 15 ;EO: this is neither in sci.sh nor KERNEL.SH
					y
					x
					(+ y (/ (* (CelHigh 5 0 0) 5) 11))
					(+ x (/ (CelWide 5 0 0) 2))
				)
			)
			(DrawCel 5 0 0 0 0 -1 0 underBits)
			(= temp0 0)
		else
			(= temp0 1)
		)
		(if
			(==
				(SetSynonyms
					1
					raveName
					(+ x 4)
					(- y 7)
					param1
					param2
					param3
					param4
					param5
					temp0
				)
				2
			)
			(= cueVal 27)
		else
			(= cueVal 0)
		)
	)
)
