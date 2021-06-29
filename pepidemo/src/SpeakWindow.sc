;;; Sierra Script 1.0 - (do not remove this comment)
(script# 891)
(include game.sh)
(use Main)
(use Window)
(use System)


(class SpeakWindow of SysWindow
	(properties
		hMargin 0
		vMargin 0
		speakObj 0
		tailTop 0
		tailLeft 0
		tailBottom 0
		tailRight 0
		tailBits 0
		tailPosn 0
		underBits1 0
		underBits2 0
		atTop 0
		x 0
		y 0
	)
	
	(method (open &tmp theX theY savePort temp3 [temp4 4])
		(= color 0)
		(= back 6)
		(= priority 15)
		(if (+ x y)
			(= theX x)
			(= theY y)
			(= y (= x 0))
		else
			(= theX (speakObj x?))
			(= theY (speakObj y?))
		)
		(= tailPosn (speakObj tailPosn?))
		(if (OneOf tailPosn 0 1)
			(self move: (- theX left) (- theY (+ bottom 10)))
			(self repos: -10 -10)
			(= brTop top)
			(= brLeft left)
			(= brBottom bottom)
			(= brRight right)
		else
			(self moveTo: theX theY)
			(= brTop (- top 10))
			(= brLeft (- left 10))
			(= brBottom (+ bottom 10))
			(= brRight (+ right 10))
		)
		(= temp3 1)
		(if (!= priority -1)
			(|= temp3 $0002)
		)
		(switch tailPosn
			(1
				(= tailTop (- brBottom 8))
				(= tailLeft (- brRight 35))
			)
			(4
				(= tailTop (- brTop 3))
				(= tailLeft (- brRight 35))
			)
			(3
				(= tailTop (- brTop 3))
				(= tailLeft (+ brLeft 20))
			)
			(else 
				(= tailPosn 0)
				(= tailTop (- brBottom 8))
				(= tailLeft (+ brLeft 20))
			)
		)
		(= tailBottom (+ tailTop 11))
		(= type 128)
		(= lsTop (Min brTop tailTop))
		(= lsBottom (Max brBottom tailBottom))
		(= lsRight brRight)
		(= lsLeft brLeft)
		(if (OneOf tailPosn 0 1)
			(self repos: 10 10)
		)
		(super open: &rest)
		(= savePort (GetPort))
		(SetPort 0)
		(Graph GFillRect
			(+ top 3)
			(- left 3)
			(- bottom 2)
			right
			temp3
			back
			priority
		)
		(Graph GFillRect
			(- top 3)
			(+ left 3)
			(+ bottom 4)
			(- right 6)
			temp3
			back
			priority
		)
		(DrawCel 905 2 0 (+ brLeft 7) (+ brTop 7) priority)
		(DrawCel 905 2 1 (- brRight 20) (+ brTop 7) priority)
		(DrawCel 905 2 3
			(- brRight 20)
			(- brBottom 16)
			priority
		)
		(DrawCel 905 2 2 (+ brLeft 7) (- brBottom 16) priority)
		(Graph GDrawLine
			(+ brTop 7)
			(+ brLeft 17)
			(+ brTop 7)
			(- brRight 21)
			7
			-1
			-1
		)
		(Graph GDrawLine
			(+ brTop 8)
			(+ brLeft 17)
			(+ brTop 8)
			(- brRight 21)
			6
			-1
			-1
		)
		(Graph GDrawLine
			(+ brTop 17)
			(+ brLeft 7)
			(- brBottom 17)
			(+ brLeft 7)
			7
			-1
			-1
		)
		(Graph GDrawLine
			(+ brTop 17)
			(+ brLeft 8)
			(- brBottom 17)
			(+ brLeft 8)
			6
			-1
			-1
		)
		(Graph GDrawLine
			(+ brTop 17)
			(- brRight 11)
			(- brBottom 17)
			(- brRight 11)
			5
			-1
			-1
		)
		(Graph GDrawLine
			(+ brTop 17)
			(- brRight 12)
			(- brBottom 17)
			(- brRight 12)
			6
			-1
			-1
		)
		(Graph GDrawLine
			(- brBottom 9)
			(+ brLeft 17)
			(- brBottom 9)
			(- brRight 21)
			6
			-1
			-1
		)
		(Graph GDrawLine
			(- brBottom 8)
			(+ brLeft 17)
			(- brBottom 8)
			(- brRight 21)
			myLowlightColor
			-1
			-1
		)
		(Graph GDrawLine
			(- brBottom 7)
			(+ brLeft 17)
			(- brBottom 7)
			(- brRight 21)
			myBotBordColor
			-1
			-1
		)
		(DrawCel 905 3 tailPosn tailLeft tailTop priority)
		(Graph GDrawLine
			(- top 4)
			(- left 4)
			(+ bottom 5)
			right
			1
		)
		(SetPort savePort)
	)
	
	(method (move toX toY)
		(+= top toY)
		(+= left toX)
		(+= bottom toY)
		(+= right toX)
	)
	
	(method (moveTo toX toY)
		(self move: (- toX left) (- toY top))
	)
	
	(method (repos toX toY)
		(+= top toY)
		(+= left toX)
		(+= bottom toY)
		(+= right toX)
	)
)
