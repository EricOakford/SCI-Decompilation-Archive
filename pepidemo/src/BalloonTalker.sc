;;; Sierra Script 1.0 - (do not remove this comment)
(script# 877)
(include game.sh)
(use Main)
(use SpeakWindow)
(use Print)
(use Talker)


(class BalloonTalker of Talker
	(properties
		x 111
		y 111
		view 2000
		winX 0
		winY 0
		tailPosn 0
		dontUpd 1
		cSpeed 6
		talkLoop -1
		forceWidth 0
		hidden 0
		offX 0
		offY 0
	)
	
	(method (dispose)
		(= msgType TEXT_MSG)
		(super dispose: &rest)
	)
	
	(method (display theText &tmp theWin temp1)
		((= theWin (SpeakWindow new:)) speakObj: self)
		(if (+ winX winY) (theWin x: winX y: winY))
		(theWin tailPosn: tailPosn)
		(if (and (not (HaveMouse)) (!= theCursor INVIS_CURSOR))
			(= saveCursor theCursor)
			(theGame setCursor: INVIS_CURSOR)
		else
			(= saveCursor 0)
		)
		((Print new:)
			window: theWin
			font: (if (not font) USERFONT else font)
			width: talkWidth
			title: 0
			addText: theText
			modeless: TRUE
			init:
		)
	)
)
