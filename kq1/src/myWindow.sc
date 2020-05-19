;;; Sierra Script 1.0 - (do not remove this comment)
(script# KQ_WINDOW)
(include game.sh)
(use Save)


(class myWindow of SysWindow
	(properties
		back vLCYAN
		type $0081
		brBottom 190
		brRight 320
		blunderBits 0
		underBits 0
	)
	
	(method (dispose)
		(SetPort 0)
		(Graph GRestoreBits underBits)
		(Graph GRestoreBits blunderBits)
		(Graph GReAnimate (- top 20) (- left 20) (+ bottom 20) (+ right 20))
		(DisposeWindow window)
		(DisposeClone self)
	)
	
	(method (open &tmp wMap t l b r topRgt botRgt topLft botLft ornament)
		(= topLft (CelHigh 657 0 0))
		(= topRgt (CelHigh 657 0 1))
		(= botRgt (CelHigh 657 0 2))
		(= botLft (CelWide 657 0 3))
		(= ornament (CelWide 657 1 0))
		(SetPort 0)
		(= t (- top 5))
		(= l (* (/ (= l (- left 8)) 8) 8))
		(= b (+ bottom 8))
		(if (mod (= r (+ right 8)) 8)
			(= r (+ (* (/ r 8) 8) 8))
		)
		(= wMap VMAP)
		(if (!= priority -1)
			(|= wMap PMAP)
		)
		(= underBits
			(Graph GSaveBits t l b r wMap)
		)
		(= t (- top 15))
		(= blunderBits
			(Graph GSaveBits t
				(- (/ (+ left right) 2) (/ ornament 2))
				(+ t 10)
				(+ (/ (+ left right) 2) (/ ornament 2) 1)
				wMap
			)
		)
		(Graph GFillRect (+ t 10) l b r wMap 0 priority)
		(Graph GFillRect (+ t 16) (+ l 6) (- b 6) (- r 6) wMap back priority)
		(DrawCel 657 1 0 (- (/ (+ left right) 2) (/ ornament 2)) t -1)
		(DrawCel 657 0 0 l (+ t 10) -1)
		(DrawCel 657 0 0 l (- b topRgt) -1)
		(DrawCel 657 0 0 (- r botRgt) (+ t 10) -1)
		(DrawCel 657 0 0 (- r botRgt) (- b topRgt) -1)
		(Graph GDrawLine (+ t 10) (- (+ l botLft) 1) (+ t 10) (- r botLft) 53 -1 -1)
		(Graph GDrawLine (+ t 13) (- (+ l botLft) 1) (+ t 13) (- r botLft) 53 -1 -1)
		(Graph GDrawLine (- b 1) (- (+ l botLft) 1) (- b 1) (- r botLft) 53 -1 -1)
		(Graph GDrawLine (- b 4) (- (+ l botLft) 1) (- b 4) (- r botLft) 53 -1 -1)
		(Graph GDrawLine (+ t topLft 9) l (- b topLft) l 53 -1 -1)
		(Graph GDrawLine (+ t topLft 9) (+ l 3) (- b topLft) (+ l 3) 53 -1 -1)
		(Graph GDrawLine (+ t topLft 9) (- r 1) (- b topLft) (- r 1) 53 -1 -1)
		(Graph GDrawLine (+ t topLft 9) (- r 4) (- b topLft) (- r 4) 53 -1 -1)
		(Graph GShowBits (+ t 10) l b r VMAP)
		(= type $81)
		(super open:)
	)
)
