;;; Sierra Script 1.0 - (do not remove this comment)
(script# 909)
(include sci.sh)
(use Main)
(use Kq6Window)
(use Print)
(use DIcon)
(use Talker)

(public
	Kq6Talker 0
)

(class Kq6Talker of Talker
	(properties
		x -1
		y -1
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
		caller 0
		disposeWhenDone 2
		ticks 0
		talkWidth 270
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
		bust 0
		eyes 0
		mouth 0
		viewInPrint 0
		textX 82
		textY 0
		useFrame 0
		blinkSpeed 100
		raving 0
		raveName 0
		saveX 0
		saveY 0
		winPosn 1
	)
	
	(method (init param1)
		(= font userFont)
		(= keepWindow 1)
		(= color (Kq6Window color?))
		(= back (Kq6Window back?))
		(if
		(and (== msgType 2) argc (IsObject param1) raveName)
			(= saveX x)
			(= saveY y)
			(if (== winPosn 0)
				(= x 59)
				(= y 15)
			else
				(= x 177)
				(= y 15)
			)
		)
		(if (not (== curRoomNum 450))
			(= raving
				(if
					(and
						argc
						param1
						global169
						(== msgType 2)
						(not (Platform 5))
					)
					(Platform 6)
				else
					0
				)
			)
		else
			(= raving
				(if
					(and
						(or bust param1)
						global169
						(== msgType 2)
						(not (Platform 5))
					)
					(Platform 6)
				else
					0
				)
			)
		)
		(if argc (super init: param1 &rest) else (super init:))
	)
)

(class Kq6Narrator of Narrator
	(properties
		x -1
		y 10
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
		caller 0
		disposeWhenDone 1
		ticks 0
		talkWidth 0
		keepWindow 1
		modeless 0
		font 0
		cueVal 0
		initialized 0
		showTitle 0
		color 0
		back 7
		curVolume 0
		saveCursor 0
		strView 945
	)
	
	(method (init)
		(self
			font: userFont
			color: (Kq6Window color?)
			back: (Kq6Window back?)
		)
		(super init: &rest)
	)
	
	(method (display theText &tmp theTalkWidth newSystemWindow temp2 newDIcon)
		(if (> (+ x talkWidth) 318)
			(= theTalkWidth (- 318 x))
		else
			(= theTalkWidth talkWidth)
		)
		((= newSystemWindow (systemWindow new:))
			color: color
			back: back
		)
		(if
			(and
				(>= 90 (= temp2 (StrAt theText 0)))
				(>= temp2 65)
			)
			(StrAt theText 0 9)
			((= newDIcon (DIcon new:))
				view: strView
				loop: (+ 0 (/ (- temp2 65) 13))
				cel: (mod (- temp2 65) 13)
			)
			(Print
				window: newSystemWindow
				posn: x y
				font: font
				width: theTalkWidth
				addTitle: (if showTitle name else 0)
				addText: theText 0 7
				addIcon: newDIcon 0 0 0 0
				modeless: 1
				init:
			)
		else
			(Print
				window: newSystemWindow
				posn: x y
				font: font
				width: theTalkWidth
				title: (if showTitle name else 0)
				addText: theText
				modeless: 1
				init:
			)
		)
	)
)
