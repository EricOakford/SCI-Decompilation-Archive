;;; Sierra Script 1.0 - (do not remove this comment)
(script# ZOID) ;819
(include game.sh)
(use Main)
(use Sq4Feature)
(use System)

(public
	zoid 0
)

(instance zoid of Sq4View
	(properties
		x 106
		y 25
		view 1950
		priority 15
		signal (| ignrAct ignrHrz fixPriOn stopUpdOn) ;$6011
	)
	
	(method (init &tmp evt)
		(= view 1950)
		(= cel (= loop 0))
		(= x 106)
		(= y 25)
		(= priority 15)
		(self show:)
		(while
			(not
				(OneOf
					((= evt ((user curEvent?) new:)) type?)
					keyDown mouseDown joyDown
				)
			)
			(evt dispose:)
		)
		(evt dispose:)
		(self dispose:)
	)
	
	(method (dispose)
		(self hide:)
		(super dispose: &rest)
	)
	
	(method (hide)
		(Graph GRestoreBits underBits)
		(Graph GShowBits 25 106 nsBottom nsRight 1)
		(Graph GReAnimate 25 106 nsBottom nsRight)
		(= underBits 0)
	)
	
	(method (show &tmp temp0 i temp2 temp3 [str 30])
		(= nsRight (+ x (CelWide view loop cel)))
		(= nsBottom (+ y (CelHigh view loop cel)))
		(= underBits (Graph GSaveBits y x nsBottom nsRight 3))
		(DrawCel view loop cel x y priority)
		(Format @str {%d} buckazoids)
		(= temp3 (StrLen @str))
		(self loop: 1 cel: 0 x: (- 194 (* 8 temp3)) y: 66)
		(= i 0)
		(while (< i temp3)
			(= temp0 (- (StrAt @str i) 48))
			(DrawCel view loop temp0 x 66 priority)
			(= x (+ x 8))
			(++ i)
		)
		(Graph GShowBits 66 x nsBottom nsRight 1)
	)
)
