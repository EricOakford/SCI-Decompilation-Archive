;;; Sierra Script 1.0 - (do not remove this comment)
(script# 934)
(include sci.sh)
(use Main)
(use Intrface)
(use IconBar)
(use System)

(public
	GameControls 0
)

(class Slider of IconI
	(properties
		view -1
		loop -1
		cel -1
		nsLeft 0
		nsTop -1
		nsRight 0
		nsBottom 0
		state $0000
		cursor -1
		type $4000
		message -1
		modifiers $0000
		signal $0001
		helpStr 0
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
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
		theObj 0
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
		(Graph
			grUPDATE_BOX
			(- nsTop 1)
			(- nsLeft 1)
			(+ 2 nsBottom)
			(+ 2 nsRight)
			1
		)
	)
	
	(method (select param1 &tmp newEvent)
		(return
			(if (and argc param1)
				(while (!= ((= newEvent (Event new:)) type?) 2)
					(newEvent localize:)
					(cond 
						((< (newEvent y?) (- sTop yStep)) (self move: yStep (not (& signal $0200))))
						((> (newEvent y?) (+ sTop yStep)) (self move: (- yStep) (not (& signal $0200))))
					)
					(newEvent dispose:)
				)
				(if (& signal $0200)
					(self doit: (self posnToValue: sTop))
				)
				(newEvent dispose:)
			else
				(return 1)
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
				(not (& signal $0200))
		)
		(if (& signal $0200)
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
				(not (& signal $0200))
		)
		(if (& signal $0200)
			(self doit: (self posnToValue: sTop))
		)
	)
	
	(method (move param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7)
		(= temp7 (if (not argc) else param2))
		(= temp5 (sign param1))
		(= temp4 param1)
		(while (<= yStep (Abs temp4))
			(= temp0 (- sTop (* temp5 yStep)))
			(= temp1 (CelHigh sliderView sliderLoop sliderCel))
			(= sTop
				(cond 
					((< temp0 minY) minY)
					((> temp0 maxY) maxY)
					(else temp0)
				)
			)
			(= temp2 (PicNotValid))
			(PicNotValid 1)
			(DrawCel view loop cel nsLeft nsTop -1)
			(DrawCel sliderView sliderLoop sliderCel sLeft sTop -1)
			(Graph
				grUPDATE_BOX
				(- nsTop 1)
				(- nsLeft 1)
				(+ 2 nsBottom)
				(+ 2 nsRight)
				1
			)
			(PicNotValid temp2)
			(= temp3 (self posnToValue: sTop))
			(= temp6
				(if temp7 (self doit: temp3) else (self doit:))
			)
			(= temp4 (- temp4 (* yStep temp5)))
		)
		(return temp6)
	)
	
	(method (valueToPosn param1 &tmp temp0)
		(return
			(cond 
				(
					(and
						(<
							(= temp0 (if argc param1 else (self doit:)))
							topValue
						)
						(< temp0 bottomValue)
					)
					(if (< bottomValue topValue) maxY else minY)
				)
				((and (> temp0 topValue) (> temp0 bottomValue)) (if (< bottomValue topValue) minY else maxY))
				(else
					(+
						minY
						(/
							(* (Abs (- topValue temp0)) (- maxY minY))
							(Abs (- topValue bottomValue))
						)
					)
				)
			)
		)
	)
	
	(method (posnToValue param1)
		(return
			(+
				bottomValue
				(/
					(* (- maxY param1) (- topValue bottomValue))
					(- maxY minY)
				)
			)
		)
	)
)

(class GameControls of IconBar
	(properties
		elements 0
		size 0
		height 200
		underBits 0
		oldMouseX 0
		oldMouseY 0
		curIcon 0
		highlightedIcon 0
		prevIcon 0
		curInvIcon 0
		useIconItem 0
		helpIconItem 0
		port 0
		window 0
		state $0000
		activateHeight 0
		y 0
		okButton 0
	)
	
	(method (show &tmp temp0 temp1 temp2 temp3 temp4 temp5)
		(= temp5 (PicNotValid))
		(PicNotValid 0)
		(sounds pause:)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
		(= state (| state $0020))
		(if (IsObject window)
			(window open:)
		else
			(= window
				((systemWindow new:)
					top: 46
					left: 24
					bottom: 155
					right: 296
					priority: 15
					open:
					yourself:
				)
			)
		)
		(= temp0 30)
		(= temp1 30)
		(= temp2 (FirstNode elements))
		(while temp2
			(= temp3 (NextNode temp2))
			(if (not (IsObject (= temp4 (NodeValue temp2))))
				(return)
			)
			(if
				(and
					(not (& (temp4 signal?) $0080))
					(<= (temp4 nsRight?) 0)
				)
				(temp4 show: temp0 temp1)
				(= temp0 (+ 20 (temp4 nsRight?)))
			else
				(temp4 show:)
			)
			(= temp2 temp3)
		)
		(PicNotValid temp5)
		(if (not okButton)
			(= okButton (NodeValue (self first:)))
		)
		(if curIcon
			(if global400
				(theGame
					setCursor:
						theCursor
						1
						(+
							(curIcon nsLeft?)
							(/ (- (curIcon nsRight?) (curIcon nsLeft?)) 2)
						)
						(- (curIcon nsBottom?) 3)
				)
			else
				(theGame setCursor: theCursor 1)
				(Intersections
					(+
						(curIcon nsLeft?)
						(/ (- (curIcon nsRight?) (curIcon nsLeft?)) 2)
					)
					(- (curIcon nsBottom?) 3)
				)
			)
		)
		(self doit: hide:)
	)
	
	(method (hide)
		(if window (window dispose:))
		(if (& state $0020)
			(sounds pause: 0)
			(= state (& state $ffdf))
		)
	)
	
	(method (select param1 param2)
		(param1 select: (if (>= argc 2) param2 else 0))
	)
	
	(method (swapCurIcon)
	)
	
	(method (advanceCurIcon &tmp temp0)
	)
	
	(method (dispatchEvent event &tmp systemWindowEraseOnly temp1 temp2)
		(return
			(cond 
				(
					(and
						(& (event type?) $4000)
						(== (event message?) JOY_DOWNLEFT)
					)
					(= temp1 (self firstTrue: #onMe event))
					(event dispose:)
					(if (and temp1 (temp1 helpStr?))
						(= temp2 (GetPort))
						(if (systemWindow respondsTo: #eraseOnly)
							(= systemWindowEraseOnly (systemWindow eraseOnly?))
							(systemWindow eraseOnly: 1)
							(Printf 934 0 (temp1 helpStr?))
							(systemWindow eraseOnly: systemWindowEraseOnly)
						else
							(Printf 934 0 (temp1 helpStr?))
						)
						(SetPort temp2)
					)
					(if helpIconItem
						(helpIconItem signal: (& (helpIconItem signal?) $ffef))
					)
					(theGame setCursor: 999)
					(return 0)
				)
				((& (event type?) evJOYSTICK)
					(switch (event message?)
						(JOY_DOWN
							(event dispose:)
							(cond 
								(
									(and
										(IsObject highlightedIcon)
										(highlightedIcon respondsTo: #retreat)
									)
									(highlightedIcon retreat:)
									(return 0)
								)
								(
									(or
										(not (IsObject highlightedIcon))
										(& (highlightedIcon signal?) $0100)
									)
									(self advance:)
									(return 0)
								)
							)
						)
						(JOY_UP
							(event dispose:)
							(cond 
								(
									(and
										(IsObject highlightedIcon)
										(highlightedIcon respondsTo: #advance)
									)
									(highlightedIcon advance:)
									(return 0)
								)
								(
									(or
										(not (IsObject highlightedIcon))
										(& (highlightedIcon signal?) $0100)
									)
									(self retreat:)
									(return 0)
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

(class ControlIcon of IconI
	(properties
		view -1
		loop -1
		cel -1
		nsLeft 0
		nsTop -1
		nsRight 0
		nsBottom 0
		state $0000
		cursor -1
		type $4000
		message -1
		modifiers $0000
		signal $0001
		helpStr 0
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
		theObj 0
		selector 0
	)
	
	(method (select)
		(if theObj
			(if (super select: &rest)
				(if (& signal $0040)
					((if gameControls else GameControls) hide:)
				)
				(Eval theObj selector)
			)
		else
			(super select: &rest)
		)
	)
)
