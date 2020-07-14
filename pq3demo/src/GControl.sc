;;; Sierra Script 1.0 - (do not remove this comment)
(script# GCONTROL)
(include game.sh)
(use Main)
(use Intrface)
(use IconBar)
(use System)

(public
	GameControls 0
)

(define	CTRL_BOX_TOP		46)
(define	CTRL_BOX_LEFT		24)
(define	CTRL_BOX_BOTTOM	155)
(define	CTRL_BOX_RIGHT		296)


(class Slider of IconItem
	(properties
		sliderView 0
		sliderLoop 0
		sliderCel 0
		sTop 0
		sLeft 0
		sRight 0
		maxY 0
		minY 0
		underBits 0
		yStep 1
		theObj NULL
		selector 0
		bottomValue 0
		topValue 0
	)
	
	(method (doit)
		(if theObj (Eval theObj selector &rest))
	)
	
	(method (show)
		(super show: &rest)
		(if (not sRight)
			(= sLeft nsLeft)
			(= sRight nsRight)
			(= maxY
				(- nsBottom (CelHigh sliderView sliderLoop sliderCel))
			)
			(= minY nsTop)
		)
		(= sTop (self valueToPosn:))
		(DrawCel sliderView sliderLoop sliderCel sLeft sTop -1)
		(Graph GShowBits (- nsTop 1) (- nsLeft 1) (+ 2 nsBottom) (+ 2 nsRight) VMAP)
	)
	
	(method (select relVar &tmp event)
		(return
			(if (and argc relVar)
				(while (!= ((= event (Event new:)) type?) mouseUp)
					(event localize:)
					(cond 
						((< (event y?) (- sTop yStep)) (self move: yStep (not (& signal RELSEND))))
						((> (event y?) (+ sTop yStep)) (self move: (- yStep) (not (& signal RELSEND))))
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
	)
	
	(method (highlight)
	)
	
	(method (advance)
		(self
			move:
				(Max
					yStep
					(-
						sTop
						(self
							valueToPosn: (+ (self doit:) (sign (- topValue bottomValue)))
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
							valueToPosn: (- (self doit:) (sign (- topValue bottomValue)))
						)
					)
				)
				(not (& signal RELSEND))
		)
		(if (& signal RELSEND)
			(self doit: (self posnToValue: sTop))
		)
	)
	
	(method (move amount doSend &tmp newTop cHigh pnv newValue i dir retVal doSendTmp)
		(= doSendTmp (if (not argc) else doSend))
		(= dir (sign amount))
		(= i amount)
		(while (<= yStep (Abs i))
			(= newTop (- sTop (* dir yStep)))
			(= cHigh (CelHigh sliderView sliderLoop sliderCel))
			(= sTop
				(cond 
					((< newTop minY) minY)
					((> newTop maxY) maxY)
					(else newTop)
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
				(if doSendTmp (self doit: newValue) else (self doit:))
			)
			(= i (- i (* yStep dir)))
		)
		(return retVal)
	)
	
	(method (valueToPosn val &tmp selVal)
		(return
			(cond 
				(
					(and
						(<
							(= selVal (if argc val else (self doit:)))
							topValue
						)
						(< selVal bottomValue)
					)
					(if (< bottomValue topValue) maxY else minY)
				)
				((and (> selVal topValue) (> selVal bottomValue)) (if (< bottomValue topValue) minY else maxY))
				(else
					(+
						minY
						(/
							(* (Abs (- topValue selVal)) (- maxY minY))
							(Abs (- topValue bottomValue))
						)
					)
				)
			)
		)
	)
	
	(method (posnToValue yPosn)
		(return
			(+
				bottomValue
				(/
					(* (- maxY yPosn) (- topValue bottomValue))
					(- maxY minY)
				)
			)
		)
	)
)

(class GameControls of IconBar
	(properties
		window NULL
		height 200
		okButton 0
		state 0
	)
	
	(method (show &tmp theX theY node nextNode obj)
		(sounds pause:)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
		(= state (| state IB_ACTIVE))
		(if (IsObject window)
			(window open:)
		else
			(= window
				((systemWindow new:)
					top: CTRL_BOX_TOP
					left: CTRL_BOX_LEFT
					bottom: CTRL_BOX_BOTTOM
					right: CTRL_BOX_RIGHT
					priority: 15
					open:
					yourself:
				)
			)
		)
		(= theX 30)
		(= theY 30)
		(= node (FirstNode elements))
		(while node
			(= nextNode (NextNode node))
			(if (not (IsObject (= obj (NodeValue node))))
				(return)
			)
			(if
				(and
					(not (& (obj signal?) FIXED_POSN))
					(<= (obj nsRight?) 0)
				)
				(obj show: theX theY)
				(= theX (+ 20 (obj nsRight?)))
			else
				(obj show:)
			)
			(= node nextNode)
		)
		(if (not okButton)
			(= okButton (NodeValue (self first:)))
		)
		(if curIcon
			(theGame
				setCursor:
					theCursor
					TRUE
					(+
						(curIcon nsLeft?)
						(/ (- (curIcon nsRight?) (curIcon nsLeft?)) 2)
					)
					(- (curIcon nsBottom?) 3)
			)
		)
		(self doit: hide:)
	)
	
	(method (hide)
		(if window (window dispose:))
		(if (& state IB_ACTIVE)
			(sounds pause: FALSE)
			(= state (& state (~ IB_ACTIVE)))
		)
	)
	
	(method (select theIcon relVer)
		(theIcon select: (if (>= argc 2) relVer else 0))
	)
	
	(method (swapCurIcon)
	)
	
	(method (advanceCurIcon &tmp theIcon)
	)
	
	(method (dispatchEvent event &tmp eO thisIcon thePort)
		(return
			(cond 
				(
					(and
						(& (event type?) userEvent)
						(== (event message?) verbHelp)
					)
					(= thisIcon (self firstTrue: #onMe event))
					(event dispose:)
					(if (and thisIcon (thisIcon helpStr?))
						(= thePort (GetPort))
						(if (systemWindow respondsTo: #eraseOnly)
							(= eO (systemWindow eraseOnly?))
							(systemWindow eraseOnly: TRUE)
							(Printf GCONTROL 0 (thisIcon helpStr?))
							(systemWindow eraseOnly: eO)
						else
							(Printf GCONTROL 0 (thisIcon helpStr?))
						)
						(SetPort thePort)
					)
					(if helpIconItem
						(helpIconItem signal: (& (helpIconItem signal?) (~ TRANSLATOR)))
					)
					(theGame setCursor: ARROW_CURSOR)
					(return FALSE)
				)
				((& (event type?) direction)
					(switch (event message?)
						(dirS
							(event dispose:)
							(cond 
								(
									(and
										(IsObject highlightedIcon)
										(highlightedIcon respondsTo: #retreat)
									)
									(highlightedIcon retreat:)
									(return FALSE)
								)
								(
									(or
										(not (IsObject highlightedIcon))
										(& (highlightedIcon signal?) VICON)
									)
									(self advance:)
									(return FALSE)
								)
							)
						)
						(dirN
							(event dispose:)
							(cond 
								(
									(and
										(IsObject highlightedIcon)
										(highlightedIcon respondsTo: #advance)
									)
									(highlightedIcon advance:)
									(return FALSE)
								)
								(
									(or
										(not (IsObject highlightedIcon))
										(& (highlightedIcon signal?) VICON)
									)
									(self retreat:)
									(return FALSE)
								)
							)
						)
						(else 
							(super dispatchEvent: event)
						)
					)
				)
				(else (super dispatchEvent: event))
			)
		)
	)
)

(class ControlIcon of IconItem
	(properties
		theObj 0
		selector 0
	)
	
	(method (select)
		(if theObj
			(if (super select: &rest)
				(if (& signal HIDEBAR)
					((if gameControls else GameControls) hide:)
				)
				(Eval theObj selector)
			)
		else
			(super select: &rest)
		)
	)
)
