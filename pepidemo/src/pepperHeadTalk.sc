;;; Sierra Script 1.0 - (do not remove this comment)
(script# tlkPepper)
(include game.sh)
(use Main)
(use BalloonTalker)
(use TalkerWindow)
(use BalloonWindow)
(use Print)
(use Talker)
(use Actor)
(use System)

(public
	pepperHeadTalk 0
	setUpPepper 1
	pepperBalloon 2
	PepperTalker 3
)

(local
	saveWindow
	local1
)
(instance pepperHeadTalk of Talker
	(properties
		talkWidth 120
		color 15
		back 41
		view tlkPepper
		textX 130
	)
	
	(method (init)
		(= saveWindow systemWindow)
		(= systemWindow TalkerWindow)
		(super init: 0 0 pepperEyes pepperFrame &rest)
	)
	
	(method (dispose)
		(= systemWindow saveWindow)
		(super dispose: &rest)
		(Animate (cast elements?) FALSE)
	)
)

(instance pepperFrame of View
	(properties
		x 9
		y 14
		loop 3
		priority 15
		signal (| ignrAct ignrHrz fixPriOn)
	)
)

(instance pepperEyes of Prop
	(properties
		loop 2
		priority 15
		signal (| ignrAct ignrHrz fixPriOn)
	)
)

(instance PepperTalker of BalloonTalker
	(properties
		talkWidth 0
	)
	
	(method (init &tmp theView)
		((ScriptID 895 0) setMotion: 0)
		(cond 
			((== global194 48)
				(pepperMouth view: 2000)
			)
			((!= talkLoop -1)
				(if (OneOf talkLoop 0 1 2)
					(= theView 2001)
				else
					(= theView 2009)
				)
				(pepperMouth
					view: theView
					x: ((ScriptID 895 0) x?)
					y: ((ScriptID 895 0) y?)
					loop: talkLoop
					cycleSpeed: cSpeed
					scaleSignal: ((ScriptID 895 0) scaleSignal?)
					scaleX: ((ScriptID 895 0) scaleX?)
					scaleY: ((ScriptID 895 0) scaleY?)
				)
				(self priority: ((ScriptID 895 0) priority?))
			)
			(
				(and
					(== ((ScriptID 895 0) loop?) 8)
					(OneOf ((ScriptID 895 0) cel?) 0 1 2 4 5)
					(== ((ScriptID 895 0) view?) 800)
				)
				(if (OneOf ((ScriptID 895 0) cel?) 0 1 2)
					(= theView 2001)
				else
					(= theView 2009)
				)
				(pepperMouth
					view: theView
					x: ((ScriptID 895 0) x?)
					y: ((ScriptID 895 0) y?)
					loop: ((ScriptID 895 0) cel?)
					cycleSpeed: cSpeed
					scaleSignal: ((ScriptID 895 0) scaleSignal?)
					scaleX: ((ScriptID 895 0) scaleX?)
					scaleY: ((ScriptID 895 0) scaleY?)
				)
				(self priority: ((ScriptID 895 0) priority?))
			)
			(
				(and
					(== ((ScriptID 895 0) loop?) 8)
					(OneOf ((ScriptID 895 0) cel?) 0 1 2 4 5)
					(== ((ScriptID 895 0) view?) 790)
				)
				(if (OneOf ((ScriptID 895 0) cel?) 0 1 2)
					(= theView 2002)
				else
					(= theView 2010)
				)
				(pepperMouth
					view: theView
					x: ((ScriptID 895 0) x?)
					y: ((ScriptID 895 0) y?)
					loop: ((ScriptID 895 0) cel?)
					cycleSpeed: cSpeed
					scaleSignal: ((ScriptID 895 0) scaleSignal?)
					scaleX: ((ScriptID 895 0) scaleX?)
					scaleY: ((ScriptID 895 0) scaleY?)
				)
				(self priority: ((ScriptID 895 0) priority?))
			)
			(else
				(pepperMouth view: 2000)
			)
		)
		(self posnBalloon: (ScriptID 895 0))
		(if (!= (pepperMouth view?) 2000)
			((ScriptID 895 0) hide:)
			(= hidden TRUE)
		)
		(super init: pepperMouth 0 0 0 &rest)
	)
	
	(method (dispose)
		(pepperMouth view: 2000)
		(if hidden
			((ScriptID 895 0) show:)
			(= hidden FALSE)
		)
		(Animate (cast elements?) FALSE)
		(super dispose: &rest)
		(if ((ScriptID 895 0) cycler?)
			((ScriptID 895 0) startUpd:)
		)
	)
	
	(method (say)
		(if (and dontUpd (not hidden))
			((ScriptID 895 0) stopUpd:)
		)
		(super say: &rest)
	)
	
	(method (posnBalloon obj &tmp t l b r theWidth)
		(= theWidth 150)
		(= x (obj x?))
		(= y (obj y?))
		(SetNowSeen obj)
		(= t (obj nsTop?))
		(= l (obj nsLeft?))
		(= b (obj nsBottom?))
		(= r (obj nsRight?))
		(cond 
			((+ winX winY))
			((<= x 130)
				(if (<= y 92)
					(= x (- r 10))
					(= y (+ t 30))
					(= tailPosn 3)
				else
					(= x (- r 10))
					(= y (+ t 5))
					(= tailPosn 0)
				)
				(= x (+ x offX))
				(= y (+ y offY))
				(= theWidth 150)
			)
			((<= x 173)
				(if (<= y 92)
					(= x (- r 10))
					(= y (+ t 30))
					(= tailPosn 3)
				else
					(= x (- r 10))
					(= y (+ t 5))
					(= tailPosn 0)
				)
				(= x (+ x offX))
				(= y (+ y offY))
				(= theWidth 130)
			)
			(else
				(if (<= y 92)
					(= x (- l 145))
					(= y (+ t 30))
					(= tailPosn 4)
				else
					(= x (- l 145))
					(= y (+ t 5))
					(= tailPosn 1)
				)
				(= x (+ x offX))
				(= y (+ y offY))
				(= theWidth 150)
			)
		)
		(if (or (not talkWidth) (not forceWidth))
			(= talkWidth theWidth)
		)
	)
)

(instance pepperMouth of TalkerMouth
	(properties
		view 0
		signal (| ignrAct ignrHrz)
	)
)

(instance setUpPepper of Code
	
	(method (doit param1)
		(if (== curRoomNum 530)
			(= local1 1)
		)
		(switch param1
			(1
				(pepperHeadTalk view: 3001 x: 46 y: 86)
				(pepperFrame view: 3001)
				(pepperEyes view: 3001 x: 63 y: 73)
			)
			(12
				(pepperHeadTalk view: 3002 y: 84)
				(if local1
					(pepperHeadTalk
						textX: -155
						textY: 10
						x: 210
						talkWidth: 135
					)
				else
					(pepperHeadTalk x: 45)
				)
				(pepperFrame view: 3002 x: (if local1 174 else 9))
				(pepperEyes view: 3002 x: (if local1 228 else 63) y: 71)
			)
			(23
				(pepperHeadTalk
					view: 3003
					x: (if local1 229 else 64)
					y: 73
				)
				(if local1
					(pepperHeadTalk textX: -155 textY: 10 talkWidth: 135)
				)
				(pepperFrame view: 3003 x: (if local1 173 else 9) y: 14)
				(pepperEyes view: 3003 x: (if local1 208 else 43) y: 52)
			)
		)
	)
)

(instance pepperBalloon of Talker
	(properties
		x 60
		y 10
		talkWidth 140
		view 2000
	)
	
	(method (init)
		(Load RES_VIEW 914)
		(Load RES_VIEW 915)
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose: &rest)
	)
	
	(method (display theText &tmp theWin [len 4] theCel theOffset theScale)
		(cond 
			((< (ego x?) 105) (= theCel 2) (= theOffset 5))
			((< (ego x?) 195) (= theCel 3) (= theOffset 0))
			(else (= theCel 1) (= theOffset 0))
		)
		(cond 
			((<= (ego y?) 60) (= theScale 5))
			((<= (ego y?) 100) (= theScale 60))
			((<= (ego y?) 115) (= theScale 80))
			((<= (ego y?) 150) (= theScale 100))
			(else (= theScale 128))
		)
		(TextSize @len theText 0 talkWidth)
		((= theWin (BalloonWindow new:))
			x: x
			y: y
			height: (- [len 2] [len 0])
			width: (- [len 3] [len 1])
			tailCel: theCel
			tailOffset: theOffset
			tailScale: theScale
		)
		(if (and (not (HaveMouse)) (!= theCursor INVIS_CURSOR))
			(= saveCursor theCursor)
			(theGame setCursor: INVIS_CURSOR)
		else
			(= saveCursor 0)
		)
		((Print new:)
			window: theWin
			font: SYSFONT
			width: talkWidth
			title: FALSE
			modeless: TRUE
			addText: theText
			init:
		)
	)
)
