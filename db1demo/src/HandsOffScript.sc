;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include game.sh)
(use Main)
(use Procs)
(use IconBar)
(use System)


(class HandsOffScript of Script
	(properties
		oldIllBits 0
		saveIgnrAct 0
	)
	
	(method (init)
		(HandsOff)
		(= oldIllBits (ego illegalBits?))
		(= saveIgnrAct (& (ego signal?) ignrAct))
		(ego illegalBits: 0 ignoreActors: TRUE)
		(super init: &rest)
	)
	
	(method (dispose)
		(HandsOn)
		(ego illegalBits: oldIllBits ignoreActors: saveIgnrAct)
		(super dispose:)
	)
)

(class CodeIcon of IconItem
	(properties
		signal $0081
		highlightColor 255
		value 0
	)
	
	(method (highlight param1 &tmp theNsTop theNsLeft temp2 temp3 temp4 temp5)
		(= temp5 (if argc param1 else 0))
		(if (& signal $2000)
			(super highlight: temp5 &rest)
		else
			(if (not (& signal $0020)) (return))
			(= temp4 (if temp5 highlightColor else lowlightColor))
			(= theNsTop nsTop)
			(= theNsLeft nsLeft)
			(= temp2 (- nsBottom 1))
			(= temp3 (- nsRight 1))
			(Graph
				GDrawLine
				theNsTop
				theNsLeft
				theNsTop
				temp3
				temp4
				-1
				-1
			)
			(Graph GDrawLine theNsTop temp3 temp2 temp3 temp4 -1 -1)
			(Graph
				GDrawLine
				temp2
				temp3
				temp2
				theNsLeft
				temp4
				-1
				-1
			)
			(Graph
				GDrawLine
				temp2
				theNsLeft
				theNsTop
				theNsLeft
				temp4
				-1
				-1
			)
			(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
		)
	)
)

(class TextIcon of CodeIcon
	(properties
		highlightColor 7
		lowlightColor 3
		text 0
		textColor 50
	)
	
	(method (show)
		(super show: &rest)
		(self showText: textColor)
	)
	
	(method (select param1 &tmp newEvent temp1)
		(return
			(cond 
				((& signal $0004) 0)
				((and argc param1 (& signal $0001))
					(DrawCel view loop (= temp1 1) nsLeft nsTop -1)
					(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
					(self showText: (- textColor 2))
					(while (!= ((= newEvent (Event new:)) type?) 2)
						(newEvent localize:)
						(cond 
							((self onMe: newEvent)
								(if (not temp1)
									(DrawCel view loop (= temp1 1) nsLeft nsTop -1)
									(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
									(self showText: (- textColor 2))
								)
							)
							(temp1
								(DrawCel view loop (= temp1 0) nsLeft nsTop -1)
								(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
								(self showText: textColor)
							)
						)
						(newEvent dispose:)
					)
					(newEvent dispose:)
					(if (== temp1 1)
						(DrawCel view loop 0 nsLeft nsTop -1)
						(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
						(self showText: textColor)
					)
					temp1
				)
				(else 1)
			)
		)
	)
	
	(method (showText param1)
		(proc5_8 text param1 (+ nsLeft 1) (+ nsTop 2))
	)
)

(class LineBox of Object
	(properties
		underLine1 0
		underLine2 0
		underLine3 0
		underLine4 0
		top 0
		left 0
		bottom 0
		right 0
	)
	
	(method (dispose)
		(self eraseBox:)
		(super dispose:)
	)
	
	(method (drawBox theTop theLeft theBottom theRight param5 param6 &tmp temp0 temp1)
		(self eraseBox:)
		(= top theTop)
		(= left theLeft)
		(= bottom theBottom)
		(= right theRight)
		(= temp0 0)
		(= temp1 255)
		(if (> argc 4)
			(= temp0 param5)
			(if (> argc 5) (= temp1 param6))
		)
		(= underLine1
			(Graph
				GSaveBits
				(- top 2)
				(- left 2)
				(+ top 2)
				(+ right 2)
				1
			)
		)
		(= underLine2
			(Graph
				GSaveBits
				(- top 2)
				(- right 2)
				(+ bottom 2)
				(+ right 2)
				1
			)
		)
		(= underLine3
			(Graph
				GSaveBits
				(- bottom 2)
				(- left 2)
				(+ bottom 2)
				(+ right 2)
				1
			)
		)
		(= underLine4
			(Graph
				GSaveBits
				(- top 2)
				(- left 2)
				(+ bottom 2)
				(+ left 2)
				1
			)
		)
		(Graph GDrawLine top left top right temp0 -1 -1)
		(Graph GDrawLine top right bottom right temp0 -1 -1)
		(Graph GDrawLine bottom left bottom right temp0 -1 -1)
		(Graph GDrawLine top left bottom left temp0 -1 -1)
		(if (!= param6 -1)
			(Graph
				GDrawLine
				(- top 1)
				(- left 1)
				(- top 1)
				(+ right 1)
				temp1
				-1
				-1
			)
			(Graph
				GDrawLine
				(- top 1)
				(+ right 1)
				(+ bottom 1)
				(+ right 1)
				temp1
				-1
				-1
			)
			(Graph
				GDrawLine
				(+ bottom 1)
				(- left 1)
				(+ bottom 1)
				(+ right 1)
				temp1
				-1
				-1
			)
			(Graph
				GDrawLine
				(- top 1)
				(- left 1)
				(+ bottom 1)
				(- left 1)
				temp1
				-1
				-1
			)
		)
		(Graph
			GShowBits
			(- top 2)
			(- left 2)
			(+ bottom 2)
			(+ right 2)
			1
		)
	)
	
	(method (eraseBox)
		(if underLine1
			(Graph GRestoreBits underLine4)
			(Graph GRestoreBits underLine3)
			(Graph GRestoreBits underLine2)
			(Graph GRestoreBits underLine1)
			(Graph
				GShowBits
				(- top 1)
				(- left 1)
				(+ bottom 2)
				(+ right 2)
				1
			)
			(= underLine1 0)
		)
	)
)
