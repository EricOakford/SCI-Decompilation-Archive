;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	SLIDICON.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	J. Mark Hood
;;;;	Updated: Brian K. Hughes
;;;;				April 3, 1992
;;;;
;;;;	Slider and other control icon classes.
;;;;
;;;;	Classes:
;;;;		Slider


(script# SLIDEICON)
(include game.sh) (include menu.sh)
(use IconBar)
(use System)


(class Slider kindof IconItem
	(properties
		sliderView		0
		sliderLoop		0
		sliderCel		0
		sTop				0
		sLeft				0
		sRight 			0
		maxY				0	
		minY				0
		underBits		0
		yStep				1
		theObj			NULL
		selector			0
		bottomValue		0
		topValue			0
	)

;;;	(methods
;;;		advance
;;;		retreat
;;;		move
;;;		valueToPosn
;;;		posnToValue
;;;	)

	(method (show)
		(super show:&rest)
		;; if not already set
		(if (not sRight)
			(= sLeft nsLeft)
			(= sRight nsRight)
			(= maxY (- nsBottom (CelHigh sliderView sliderLoop sliderCel)))
			(= minY nsTop)
		)
		(= sTop (self valueToPosn?))
	 	(DrawCel sliderView sliderLoop sliderCel sLeft sTop -1)
		(Graph GShowBits (- nsTop 1) (- nsLeft 1) (+ 2 nsBottom) (+ 2 nsRight) VMAP)
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
				(self doit: (self posnToValue: sTop))
			)
			(event dispose:)
		else
			(return TRUE)
		)
	)

	(method (highlight)
	)

	;; move a specified number of steps
	(method (move amount doSend &tmp newTop cHigh pnv newValue i dir retVal doSendTmp)
		(= doSendTmp (or (not argc) doSend))
		(= dir (sign amount))

		(for 	((= i amount))
				(<= yStep (Abs i))
				((-= i (* yStep dir)))
			(= newTop (- sTop (* dir yStep)))
			(= cHigh (CelHigh sliderView sliderLoop sliderCel))
			(= sTop
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
			)
			(= pnv (PicNotValid))
			(PicNotValid TRUE)
			(DrawCel view loop cel nsLeft nsTop -1)
			(DrawCel sliderView sliderLoop sliderCel sLeft sTop -1)
			(Graph GShowBits (- nsTop 1) (- nsLeft 1) (+ 2 nsBottom) (+ 2 nsRight) VMAP)
			(PicNotValid pnv)
			(= newValue (self posnToValue: sTop))
			(= retVal
				(if doSendTmp
					(self doit: newValue)
				else
					(self doit:)
				)
			)
		)
		(return retVal)
	)

	(method (doit)
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
		(= selVal (if argc val else (self doit:)))
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
								(self doit:) 
								(sign (- topValue bottomValue))
							)
					)
				)
			)
			(not (& signal RELSEND))
		)	 
		(if (& signal RELSEND)
			(self doit: (self posnToValue: sTop))
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
								(self doit:) 
								(sign (- topValue bottomValue))
							)
					)
				)
			)
			(not (& signal RELSEND))
		)
		(if (& signal RELSEND)
			(self doit: (self posnToValue: sTop))
		)
	)
	
)
