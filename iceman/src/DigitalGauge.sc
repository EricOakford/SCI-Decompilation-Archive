;;; Sierra Script 1.0 - (do not remove this comment)
(script# 816)
(include game.sh)
(use Main)
(use Eval)
(use System)


(class DigitalGauge of Code
	(properties
		theObj 0
		selector 0
		lastSelectorValue 0
		x 0
		y 0
		underBits 0
		port 0
	)
	
	(method (init theTheObj theSelector &tmp [str 5] sColor)
		(if (>= argc 1)
			(= theObj theTheObj)
			(if (>= argc 2)
				(= selector theSelector)
			)
		)
		(= sColor (if (< numColors 10) vWHITE else vLRED))
		(= port (GetPort))
		(= underBits
			(Display
				(Format @str 816 0 (Eval theObj selector))
				p_at x y
				p_color sColor
				p_mode teJustRight
				p_width 25
				p_font 100
				p_save
			)
		)
	)
	
	(method (doit &tmp [str 5] val thePort sColor)
		(if
			(!=
				lastSelectorValue
				(= val (Eval theObj selector))
			)
			(= thePort (GetPort))
			(SetPort port)
			(Display 816 1 p_restore underBits)
			(= sColor (if (< numColors 10) 15 else 12))
			(= underBits
				(Display
					(Format @str 816 0 val)
					p_at x y
					p_color sColor
					p_at teJustRight
					p_width 25
					p_font 100
					p_save
				)
			)
			(SetPort thePort)
			(= lastSelectorValue val)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Display 816 1 p_restore underBits)
		(super dispose:)
	)
)
