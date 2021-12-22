;;; Sierra Script 1.0 - (do not remove this comment)
(script# 92)
(include sci.sh)
(use Main)
(use fileScr)
(use Plane)
(use String)
(use Print)
(use Talker)
(use Actor)
(use System)

(public
	LarryTalker 0
	curTalkerbust 1
	proc92_2 2
	larryTBust 3
)

(procedure ( param1 param2 param3 thePROPERTY_ACCESS_IN_NON_METHOD &tmp selfX selfY)
	(= gGButtonBarGetCursor theCursor)
	(theGame setCursor: waitCursor 1)
	(= gGUserInput (user input?))
	(= gGUserControls (user controls?))
	(if (not PROPERTY-ACCESS-IN-NON-METHOD)
		(= PROPERTY-ACCESS-IN-NON-METHOD userFont)
	)
	(cond 
		(PROPERTY-ACCESS-IN-NON-METHOD)
		(PROPERTY-ACCESS-IN-NON-METHOD
			(if (curRoom inset?)
				(((curRoom inset?) plane?)
					addCast:
						(= PROPERTY-ACCESS-IN-NON-METHOD
							((Cast new:) name: {faceCast} init: yourself:)
						)
				)
			else
				(thePlane
					addCast:
						(= PROPERTY-ACCESS-IN-NON-METHOD
							((Cast new:) name: {faceCast} init: yourself:)
						)
				)
			)
		)
		(else
			(talkerPlane
				addCast:
					(= PROPERTY-ACCESS-IN-NON-METHOD
						((Cast new:) name: {faceCast} init: yourself:)
					)
			)
		)
	)
	(switch PROPERTY-ACCESS-IN-NON-METHOD
		(1
			(= PROPERTY-ACCESS-IN-NON-METHOD 160)
			(= PROPERTY-ACCESS-IN-NON-METHOD 10)
			(= PROPERTY-ACCESS-IN-NON-METHOD 130)
			(= PROPERTY-ACCESS-IN-NON-METHOD 38)
			(= PROPERTY-ACCESS-IN-NON-METHOD 0)
		)
		(2
			(= PROPERTY-ACCESS-IN-NON-METHOD -220)
			(= PROPERTY-ACCESS-IN-NON-METHOD 10)
			(= PROPERTY-ACCESS-IN-NON-METHOD 280)
			(= PROPERTY-ACCESS-IN-NON-METHOD 2)
			(= PROPERTY-ACCESS-IN-NON-METHOD 145)
		)
	)
	(= selfX (self x?))
	(= selfY (self y?))
	(if argc
		(if param1
			((= PROPERTY-ACCESS-IN-NON-METHOD (param1 new:))
				x: (+ (param1 x?) selfX)
				y: (+ (param1 y?) selfY)
			)
		)
		(if (> argc 1)
			(if param2
				((= PROPERTY-ACCESS-IN-NON-METHOD (param2 new:))
					x: (+ (param2 x?) selfX)
					y: (+ (param2 y?) selfY)
				)
			)
			(if (> argc 2)
				(if param3
					((= PROPERTY-ACCESS-IN-NON-METHOD (param3 new:))
						x: (+ (param3 x?) selfX)
						y: (+ (param3 y?) selfY)
					)
				)
				(if (> argc 3)
					(= PROPERTY-ACCESS-IN-NON-METHOD
						thePROPERTY_ACCESS_IN_NON_METHOD
					)
				)
			)
		)
	)
	(= PROPERTY-ACCESS-IN-NON-METHOD 1)
	(talkers addToFront: self)
	(if
		(and
			(not PROPERTY-ACCESS-IN-NON-METHOD)
			PROPERTY-ACCESS-IN-NON-METHOD
		)
		((= PROPERTY-ACCESS-IN-NON-METHOD (View new:))
			name: {frame}
			view: (self view?)
			loop: (self loop?)
			cel: (self cel?)
			x: selfX
			y: selfY
			setPri: PROPERTY-ACCESS-IN-NON-METHOD
			init: PROPERTY-ACCESS-IN-NON-METHOD
		)
	)
	(if (not PROPERTY-ACCESS-IN-NON-METHOD)
		(= PROPERTY-ACCESS-IN-NON-METHOD
			((Prop new:)
				name: {mouth}
				view: 98
				loop: 0
				cel: 0
				x: PROPERTY-ACCESS-IN-NON-METHOD
				y: PROPERTY-ACCESS-IN-NON-METHOD
				yourself:
			)
		)
	)
	(PROPERTY-ACCESS-IN-NON-METHOD
		init: PROPERTY-ACCESS-IN-NON-METHOD
		setPri: PROPERTY-ACCESS-IN-NON-METHOD
	)
	(if PROPERTY-ACCESS-IN-NON-METHOD
		(PROPERTY-ACCESS-IN-NON-METHOD
			setPri: PROPERTY-ACCESS-IN-NON-METHOD
			init: PROPERTY-ACCESS-IN-NON-METHOD
		)
	)
	(if PROPERTY-ACCESS-IN-NON-METHOD
		(PROPERTY-ACCESS-IN-NON-METHOD
			setPri: PROPERTY-ACCESS-IN-NON-METHOD
			init: PROPERTY-ACCESS-IN-NON-METHOD
		)
	)
	(if PROPERTY-ACCESS-IN-NON-METHOD
		(PROPERTY-ACCESS-IN-NON-METHOD
			setPri: PROPERTY-ACCESS-IN-NON-METHOD
			init: PROPERTY-ACCESS-IN-NON-METHOD
		)
	)
)

(class LarryTalker of Talker
	(properties
		scratch 0
		x -1
		y -1
		caller 0
		modNum -1
		disposeWhenDone 1
		ticks 0
		talkWidth 175
		modeless 2
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
		priority 200
		viewInPrint 0
		textX 0
		textY 0
		blinkSpeed 100
		winPosn 0
		showFrame 1
		faceCast 0
		fullFace 0
		curText 0
		strHandle 0
	)
	
	(method (init param1 param2 param3 theFrame &tmp larryTalkerX larryTalkerY)
		(= gGButtonBarGetCursor theCursor)
		(theGame setCursor: waitCursor 1)
		(= gGUserInput (user input?))
		(= gGUserControls (user controls?))
		(if (not font) (= font userFont))
		(cond 
			(faceCast)
			(fullFace
				(if (curRoom inset?)
					(((curRoom inset?) plane?)
						addCast: (= faceCast
							((Cast new:) name: {faceCast} init: yourself:)
						)
					)
				else
					(thePlane
						addCast: (= faceCast
							((Cast new:) name: {faceCast} init: yourself:)
						)
					)
				)
			)
			(else
				(talkerPlane
					addCast: (= faceCast
						((Cast new:) name: {faceCast} init: yourself:)
					)
				)
			)
		)
		(switch winPosn
			(1
				(= textX 160)
				(= textY 10)
				(= talkWidth 130)
				(= x 38)
				(= y 0)
			)
			(2
				(= textX -220)
				(= textY 10)
				(= x 280)
				(= y 2)
				(= talkWidth 145)
			)
		)
		(= larryTalkerX (self x?))
		(= larryTalkerY (self y?))
		(if argc
			(if param1
				((= mouth (param1 new:))
					x: (+ (param1 x?) larryTalkerX)
					y: (+ (param1 y?) larryTalkerY)
				)
			)
			(if (> argc 1)
				(if param2
					((= bust (param2 new:))
						x: (+ (param2 x?) larryTalkerX)
						y: (+ (param2 y?) larryTalkerY)
					)
				)
				(if (> argc 2)
					(if param3
						((= eyes (param3 new:))
							x: (+ (param3 x?) larryTalkerX)
							y: (+ (param3 y?) larryTalkerY)
						)
					)
					(if (> argc 3) (= frame theFrame))
				)
			)
		)
		(= initialized 1)
		(talkers addToFront: self)
		(if (and (not frame) showFrame)
			((= frame (View new:))
				name: {frame}
				view: (self view?)
				loop: (self loop?)
				cel: (self cel?)
				x: larryTalkerX
				y: larryTalkerY
				setPri: priority
				init: faceCast
			)
		)
		(if (not mouth)
			(= mouth
				((Prop new:)
					name: {mouth}
					view: 98
					loop: 0
					cel: 0
					x: x
					y: y
					yourself:
				)
			)
		)
		(mouth init: faceCast setPri: priority)
		(if bust (bust setPri: priority init: faceCast))
		(if eyes (eyes setPri: priority init: faceCast))
		(if frame (frame setPri: priority init: faceCast))
	)
	
	(method (doit)
		(if faceCast (faceCast doit:))
		(super doit: &rest)
	)
	
	(method (dispose)
		(user canInput: gGUserInput canControl: gGUserControls)
		(theGame setCursor: gGButtonBarGetCursor)
		(if faceCast
			(faceCast dispose:)
			(= mouth (= eyes (= bust (= frame (= faceCast 0)))))
		)
		(if curText
			(global208
				modifyString: curText (strHandle data?) font fore 0 0
			)
			(strHandle dispose:)
			(= curText 0)
		)
		(super dispose: &rest)
	)
	
	(method (startText param1 &tmp temp0)
		(= temp0 (param1 size:))
		(= ticks (Max 120 (* (/ (* 24 temp0) 10) textSpeed)))
		(super startText: param1 &rest)
	)
	
	(method (display theText &tmp temp0 theTalkWidth temp2 temp3 newPrint)
		(= temp3 (cond 
				(frame)
				(bust)
				(else mouth)
			))
		(= newPrint (Print new:))
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
		(if global208 (newPrint plane: invisiblePlane))
		(if showTitle (newPrint addTitle: name))
		(newPrint
			back: back
			posn: (+ (temp3 x?) textX) (+ (temp3 y?) textY)
			modeless: 2
			font: font
			width: theTalkWidth
			addText: theText
			init:
		)
		(prints delete: newPrint)
		(= dialog (newPrint dialog?))
		(if global208
			(= strHandle (Str format: {%s\n} (self name?)))
			(global208 addString: (strHandle data?) 2408 fore 0)
			(strHandle dispose:)
			(= strHandle (Str format: {%s\n\n} theText))
			(= curText
				(global208 addString: (strHandle data?) font 57 0 0)
			)
		)
	)
)

(class LarryNarrator of Narrator
	(properties
		scratch 0
		x -1
		y -1
		caller 0
		modNum -1
		disposeWhenDone 1
		ticks 0
		talkWidth 0
		modeless 2
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
		curText 0
		strHandle 0
	)
	
	(method (init)
		(= font userFont)
		(super init: &rest)
	)
	
	(method (dispose)
		(if curText
			(global208
				modifyString: curText (strHandle data?) font fore 0 0
			)
			(strHandle dispose:)
			(= curText 0)
		)
		(= msgType 3)
		(super dispose: &rest)
	)
	
	(method (startText param1 &tmp temp0)
		(= temp0 (param1 size:))
		(= ticks (Max 120 (* (/ (* 24 temp0) 10) textSpeed)))
		(super startText: param1 &rest)
	)
	
	(method (display theText &tmp theTalkWidth newPrint)
		(if (> (+ x talkWidth) 318)
			(= theTalkWidth (- 318 x))
		else
			(= theTalkWidth talkWidth)
		)
		(= newPrint (Print new:))
		(if global208 (newPrint plane: invisiblePlane))
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
		(if global208
			(= strHandle (Str format: {%s\n\n} theText))
			(= curText
				(global208 addString: (strHandle data?) font 57 0)
			)
		)
	)
	
	(method (startAudio param1)
		(= audModNum (param1 at: 0))
		(= audNoun (param1 at: 1))
		(= audVerb (param1 at: 2))
		(= audCase (param1 at: 3))
		(= audSequence (param1 at: 4))
		(if
			(ResCheck
				146
				audModNum
				audNoun
				audVerb
				audCase
				audSequence
			)
			(= ticks
				(DoAudio 2 audModNum audNoun audVerb audCase audSequence)
			)
		else
			(= msgType 1)
		)
	)
)

(instance invisiblePlane of Plane
	(properties)
	
	(method (init)
		(= priority -1)
		(super init: &rest)
	)
)

(instance larryTBust of View
	(properties
		x 2
		y 2
		view 1900
		loop 1
	)
	
	(method (init)
		(if (Btst 74) (= view 1901))
		(super init: &rest)
	)
)

(instance curTalkerbust of View
	(properties
		x 282
		y 2
		loop 1
	)
)
