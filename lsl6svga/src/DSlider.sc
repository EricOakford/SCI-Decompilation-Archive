;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	DSLIDER.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Bob Heitman
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	The dialog slider class.
;;;;
;;;;	Classes:
;;;;		DSlider


(script# DSLIDER)
(include game.sh)
(use DItem)
(use DIcon)
(use System)

;¯gtp¯
;¯gtp¯	This entire module has been commented out.
;¯gtp¯	It is incompatible with sci32
;¯gtp¯

;#if 0

(class DSlider kindof DItem
	;;; Sliders are items that maintain a value within a range.

	(properties
		type			dSlider
		knob			NULL			; View used as slider knob
		sLeft			0
		sTop			0
		sRight 		0
		maxY			0	
		minY			0
		sliderView	-1
		sliderLoop	0
		sliderCel	0
		theObj		NULL
		selector		0
		bottomValue	0
		topValue		0
	)
;;;	(methods
;;;		select				; select (& move) the slider
;;;		eval					; evaluate object and selector
;;;		valueToPosn			; position knob by value
;;;		posnToValue			; get value of knob position
;;;		advance
;;;		retreat
;;;	)

	(method (init theList)
		(super init: theList)
		((= knob (DIcon new:))
			view:		sliderView,
			loop:		sliderLoop,
			cel:		sliderCel,
			init:		theList
		)
		(= sLeft x)
		(= sTop (+ y (self valueToPosn: value)))
		(knob posn: sLeft sTop)
		(UpdateScreenItem knob)
	)

	(method (select relVer &tmp event)
		(if (and argc relVer)
			(while (!= ((= event (Event new:)) type?) mouseUp)
				(event localize:)
				(cond
					((< (event y?)	(- sTop	yStep))
						(self move: yStep (not (& signal RELSEND)))
					)
					((> (event y?)	(+ sTop	yStep))
						(self move: (- yStep) (not (& signal RELSEND)))
					)
				)
				(event dispose:)
			)
			(if (& signal RELSEND)
				(self eval: (self posnToValue: sTop))
			)
			(event dispose:)
		else
			(return TRUE)
		)
	)

	(method (move amount doSend &tmp newTop cHigh pnv newValue i dir retVal doSendTmp)
		(= doSendTmp (or (not argc) doSend))
		(= dir (sign amount))

		(for 	((= i amount))
				(<= yStep (Abs i))
				((-= i (* yStep dir)))
			(= newTop (- sTop (* dir yStep)))
			(= cHigh (CelHigh sliderView sliderLoop sliderCel))
			(= sTop (+ y
				(cond
					((< newTop minY)
						minY
					)
					((> newTop maxY)
						maxY
					)
					(else
						newTop
					)
				)
			))
;			(= pnv (PicNotValid))
;			(PicNotValid TRUE)
;			(DrawCel view loop cel nsLeft nsTop -1)
;			(DrawCel sliderView sliderLoop sliderCel sLeft sTop -1)
;			(Graph GShowBits (- nsTop 1) (- nsLeft 1) (+ 2 nsBottom) (+ 2 nsRight) VMAP)
;			(PicNotValid pnv)
			(= newValue (self posnToValue: sTop))
			(= retVal
				(if doSendTmp
					(self eval: newValue)
				else
					(self eval:)
				)
			)
		)
		(knob posn: sLeft sTop)
		(return retVal)
	)

	(method (eval)
		(return
			(if theObj
 				(Eval theObj selector &rest)
			)
		)
	)

	(method (posnToValue yPosn)
		(return
			(+
				bottomValue
				(/ 
					(* 
						(- maxY yPosn)	
						(- topValue bottomValue)
					) 
					(- maxY minY) 
				)
			)
		)
	)

	(method (valueToPosn val &tmp selVal)
		(= selVal (if argc val else (self eval:)))
		(return
			;; this mess allows reverse control
			;; i.e. topValue lower than bottomValue
			(cond
				;; out of range checks
				((and (<	selVal topValue)
						(<	selVal bottomValue)
					)
					(if (< bottomValue topValue)
						maxY
					else
						minY
					)
				)
				((and (>	selVal topValue)
						(>	selVal bottomValue)
					)
					(if (< bottomValue topValue)
						minY
					else
						maxY
					)
				)
				;; now the real thing
				(else
					(+
						minY	 												
						(/
							(*
								(Abs (- topValue selVal))				
								(- maxY minY)								
							)
							(Abs (- topValue bottomValue))			
						)
					)
				)
			)
		)
	)

	(method (advance)
		(self 
			move:
			(Max 
				yStep
				(- 
					sTop 
					(self 
						valueToPosn: 
							(+ 
								(self eval:) 
								(sign (- topValue bottomValue))
							)
					)
				)
			)
			(not (& signal RELSEND))
		)	 
		(if (& signal RELSEND)
			(self eval: (self posnToValue: sTop))
		)
	)

	(method (retreat)
		(self 
			move: 
			(Min
				(- yStep)
				(- 
					sTop 
					(self 
						valueToPosn: 
							(- 
								(self eval:) 
								(sign (- topValue bottomValue))
							)
					)
				)
			)
			(not (& signal RELSEND))
		)
		(if (& signal RELSEND)
			(self eval: (self posnToValue: sTop))
		)
	)
	
	(method (setSize &tmp w h)
		(= nsLeft x)
		(= nsTop y)
		(= nsRight (- (+ nsLeft (CelWide view loop cel)) 1))
		(= nsBottom (- (+ nsTop (CelHigh view loop cel)) 1))
	)
)

;#endif
