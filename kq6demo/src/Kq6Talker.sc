;;; Sierra Script 1.0 - (do not remove this comment)
(script# 909)
(include game.sh)
(use Main)
(use Kq6Window)
(use Print)
(use Dialog)
(use Talker)

(public
	Kq6Talker 0
)

(class Kq6Talker of Talker
	(properties
		talkWidth 270
		textX 82
	)
	
	(method (init)
		(= font userFont)
		(= keepWindow TRUE)
		(= color (Kq6Window color?))
		(= back (Kq6Window back?))
		(super init: &rest)
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
				title: (if showTitle name else 0)
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
