;;; Sierra Script 1.0 - (do not remove this comment)
(script# 850)
(include game.sh)
(use Window)


(class TapestryWindow of SysWindow
	(properties
		brBottom 190
		brRight 320
		underBits 0
		pUnderBits 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
	)
	
	(method (dispose)
		(SetPort 0)
		(Graph GRestoreBits underBits)
		(Graph GRestoreBits pUnderBits)
		(Graph GReAnimate lsTop lsLeft lsBottom lsRight)
		(super dispose:)
	)
	
	(method (open &tmp temp0 savePort temp2 t l b r temp7 temp8 theTheLeft)
		(SetPort 0)
		(= temp2 (| (= temp2 1) $0002))
		(= t top)
		(= l left)
		(= b bottom)
		(= r right)
		(= lsTop (- top 11))
		(= lsLeft (- left 32))
		(= lsRight (+ right 32))
		(= lsBottom (+ bottom 13))
		(= underBits
			(Graph GSaveBits lsTop lsLeft lsBottom lsRight 1)
		)
		(= pUnderBits
			(Graph GSaveBits lsTop lsLeft lsBottom lsRight 2)
		)
		(super open:)
		(= savePort (GetPort))
		(SetPort 0)
		(= temp8 (- l 1))
		(= temp7 (- r 13))
		(= theTheLeft l)
		(DrawCel 785 7 0 (- l 18) (- t 6) 15)
		(DrawCel 785 7 0 r (- t 6) 15)
		(DrawCel 785 6 0 r b 15)
		(DrawCel 785 6 0 (- l 1) b 15)
		(DrawCel 785 5 0 (- r 13) (+ b 1) 15)
		(if (< theTheLeft (- r 14))
			(DrawCel 785 5 0 theTheLeft (+ b 1) 15)
			(+= theTheLeft 13)
		)
		(while (> (- temp7 temp8) 14)
			(DrawCel 785 4 0 temp8 (+ b 1) 15)
			(DrawCel 785 4 0 temp7 (+ bottom 1) 15)
			(+= temp8 26)
			(-= temp7 26)
		)
		(SetPort savePort)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		(= type 129)
	)
)