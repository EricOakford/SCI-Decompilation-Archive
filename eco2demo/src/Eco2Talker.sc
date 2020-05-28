;;; Sierra Script 1.0 - (do not remove this comment)
(script# ECO2TALKER)
(include game.sh)
(use Print)
(use Talker)
(use System)

(public
	Eco2Talker 0
)

(class Eco2Talker of Talker
	(properties
		dftBack 8
		textSpacer 40
	)
	
	(method (display)
		(Print mode: teJustCenter)
		(super display: &rest)
	)
	
	(method (setupTalker param1 param2 theTextSpacer &tmp theBackColor theWidth theHeight)
		(= theBackColor (if (< argc 2) dftBack else param2))
		(if (> argc 2) (= textSpacer theTextSpacer))
		(= theWidth (CelWide view 1 0))
		(= theHeight (CelHigh view 1 0))
		(self
			x: (if (OneOf param1 2 4) (- 319 (+ theWidth 5)) else 5)
			y: (if (OneOf param1 6 4) (- 189 (+ theHeight 5)) else 5)
			textX:
				(if (OneOf param1 2 4)
					(- (+ talkWidth textSpacer))
				else
					(+ theWidth textSpacer)
				)
			textY: 10
			back: theBackColor
		)
	)
)
