;;; Sierra Script 1.0 - (do not remove this comment)
(script# GKNARRATOR)
(include game.sh)
(use Main)
(use Talker)
(use BordWind)
(use Window)
(use Motion)
(use Actor)

(public
	GKNarrator 0
)

(local
	newProp
)
(class GKNarrator of Narrator
	
	(method (init)
		(= systemWindow SysWindow)
		(self
			color: myHighlightColor
			back: myLowlightColor
			font: userFont
			talkWidth: 314
			x: 0
			y: 147
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow BorderWindow)
		(if (ego talking?)
			(newProp setCycle: 0 dispose:)
			(ego
				view: (ego oldView?)
				loop: (ego oldLoop?)
				cel: (ego oldCel?)
				signal: (ego oldSig?)
				talking: FALSE
			)
		)
		(super dispose:)
	)
	
	(method (display theBuf &tmp theView)
		(= color
			(switch currentTalker
				(GRACE 21)
				(GABRIEL 54)
				(NARRATOR 7)
				(SARGE 60)
				(MOSELY 30)
				(FRANKY 47)
				(BRUNO 26)
				(MALIA 19)
				(29 global171)
				(30 global158)
				(28 global154)
				(31 global160)
				(TAPPER global161)
				(17 global169)
				(21 global166)
				(COP 36)
				(18 38)
				(ARTIST global153)
				(16 global168)
				(LUCKY_DOG_VENDOR global162)
				(15 global155)
				(13 global159)
				(MUSEUM_GIRL 35)
				(else  myHighlightColor)
			)
		)
		(if
			(and
				(== currentTalker GABRIEL)
				(cast contains: ego)
				(or (== (ego view?) 901) (== (ego view?) 900))
				(== (ego loop?) 8)
				(not (ego scaleSignal?))
			)
			(if (or (== (ego cel?) 4) (== (ego cel?) 5))
				(= theView (+ (ego view?) 1000))
			else
				(if (== (ego view?) 900)
					(= theView (+ (ego view?) 1010 (ego cel?)))
				else
					(= theView (+ (ego view?) 1039 (ego cel?)))
				)
				(switch (Random 0 2)
					(0 1)
					(1
						(+= theView 10)
					)
					(2
						(= theView (+ (ego view?) 1000))
					)
				)
			)
			(ego
				talking: TRUE
				oldView: (ego view?)
				oldLoop: (ego loop?)
				oldCel: (ego cel?)
				oldSig: (ego signal?)
				view: theView
			)
			((= newProp (Prop new:))
				view: theView
				loop: (ego cel?)
				cel: 0
				posn: (ego x?) (ego y?)
				ignoreActors:
				cycleSpeed: 14
				init:
				setCycle: Forward
			)
		)
		(super display: theBuf)
	)
)
