;;; Sierra Script 1.0 - (do not remove this comment)
(script# 59)
(include sci.sh)
(use Main)
(use Print)
(use Talker)
(use Actor)
(use System)


(class GloryTalker of Talker
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
		font 300
		cueVal 0
		initialized 0
		showTitle 0
		fore 0
		back 255
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
		(if
			(and
				argc
				(or
					(not (Btst 147))
					(and gTeller (!= (gTeller talker?) self))
				)
			)
			(= mouth theMouth)
			(if (> argc 1)
				(= bust theBust)
				(if (> argc 2)
					(= eyes theEyes)
					(if (> argc 3) (= frame theFrame))
				)
			)
		)
		(Palette palSET_FLAG 0 255 100)
		(PalVary pvUNINIT)
		(Bclr 6)
		(if (& msgType $0002)
			(= curVolume (theGame masterVolume:))
			(if (>= curVolume 6)
				(theGame masterVolume: (- curVolume 6))
			else
				(theGame masterVolume: 1)
			)
		)
		(= initialized 1)
		(if
			(or
				(not (Btst 147))
				(and gTeller (!= (gTeller talker?) self))
			)
			(talkers addToFront: self)
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
			(mouth init: setPri: priority show:)
			(if bust (bust setPri: init:))
			(if eyes (eyes setPri: priority init: show:))
			(if frame (frame setPri: priority init: show:))
		)
	)
	
	(method (doit)
		(cast doit:)
		(if
			(and
				(!= ticks -1)
				(> (- gameTime ticks) 0)
				(& msgType $0002)
			)
			(==
				(DoAudio 6 audModNum audNoun audVerb audCase audSequence)
				-1
			)
			(if
			(and 1 (or (!= modeless 0) (& msgType $0002)))
				(self dispose: 0)
				(return 0)
			)
		)
		(return 1)
	)
	
	(method (dispose param1)
		(if
			(or
				(not (Btst 147))
				(and gTeller (!= (gTeller talker?) self))
			)
			(if mouth (mouth setCycle: 0 setCel: 0))
			(if (or (not argc) param1)
				(if eyes (eyes setCycle: 0 setCel: 0))
				(if mouth (mouth dispose:) (= mouth 0))
				(if bust (bust dispose:) (= bust 0))
				(if eyes (eyes dispose:) (= eyes 0))
				(if frame (frame dispose:) (= frame 0))
			)
		)
		(if
		(and (!= audModNum (= ticks -1)) (& msgType $0002))
			(DoAudio 3 audModNum audNoun audVerb audCase audSequence)
			(= audModNum -1)
		)
		(if (or (not argc) param1)
			(= initialized 0)
			(= modNum -1)
			(talkers delete: self)
		)
		(if dialog (dialog dispose:) (= dialog 0))
		(if (& msgType $0002) (theGame masterVolume: curVolume))
		(if caller (caller cue: cueVal))
		(= cueVal 0)
		(if
			(and
				(or (not argc) param1)
				(not (Btst 147))
				(Btst 148)
			)
			(Bclr 148)
			(if (not (== curRoomNum 470))
				((curRoom plane?) drawPic: (curRoom picture?))
			)
			(cast eachElementDo: #perform (ScriptID 90 0) 0)
			((ScriptID 0 21) doit:)
			(Bset 6)
			(CyclePalette)
			(CyclePalette_13)
		)
		(if (or (not argc) param1)
			(if
			(and (Btst 147) gTeller (!= (gTeller talker?) self))
				((gTeller talker?) showAgain:)
			)
			(DisposeClone self)
		)
	)
	
	(method (show)
		(if (not initialized) (self init: &rest))
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
								(self dispose: 0)
							)
						else
							(event claimed: 1)
							(self dispose: 0)
						)
					)
				)
			)
		)
	)
	
	(method (say param1 param2 param3)
		(Bset 50)
		(if (and (not (Btst 147)) (not (Btst 148)))
			(if (not (== curRoomNum 470))
				((ScriptID 0 21) doit: 100)
			)
			(Bset 148)
			(cast eachElementDo: #perform (ScriptID 90 0) 1)
			(if (not (== curRoomNum 470))
				(UpdatePlane
					((curRoom plane?) back: 0 picture: -1 yourself:)
				)
				(FrameOut)
			)
		)
		(if (and nextTalker (!= currentTalker nextTalker))
			(nextTalker hide:)
			(self showAgain:)
		)
		(if (not (talkers contains: self))
			(talkers addToFront: self)
		)
		(if (and gTeller (!= (gTeller talker?) self))
			((gTeller talker?) hide:)
		)
		(if (not initialized) (self init:))
		(= caller (if (and (> argc 1) param2) param2 else 0))
		(if (& msgType $0001) (self startText: param1))
		(if (& msgType $0002) (self startAudio: param3))
		(= ticks (+ ticks 60 gameTime))
		(return 1)
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
				largeAlp: 1
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
	
	(method (isModeless)
		(return 0)
	)
	
	(method (hide)
		(if mouth (mouth setCycle: 0 hide:))
		(if eyes (eyes hide:))
		(if frame (frame hide:))
		(if bust (bust hide:))
	)
	
	(method (showAgain)
		(if mouth (mouth show:))
		(if eyes (eyes show:))
		(if frame (frame show:))
		(if bust (bust show:))
	)
)
