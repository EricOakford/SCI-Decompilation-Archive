;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	GCONTROL.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	J. Mark Hood
;;;;	Updated:
;;;;		Brian K. Hughes
;;;;		July 24, 1992
;;;;
;;;;	Classes:
;;;;		GameControls
;;;;		ControlIcon


(script# GCONTROL)
(include game.sh)
(use Main)
(use Print)
(use IconBar)


(define	CTRL_BOX_TOP		46)
(define	CTRL_BOX_LEFT		24)
(define	CTRL_BOX_BOTTOM	155)
(define	CTRL_BOX_RIGHT		296)

(class GameControls kindof IconBar
	(properties
		window 		NULL
		height		200
		okButton		0
		state			0
	)

	(method (show &tmp theX theY node nextNode obj)
		(sounds pause:)
		(if (and pMouse
					(pMouse respondsTo: #stop)
				)
			(pMouse stop:)
		)
		(|= state IB_ACTIVE)

		(if (IsObject window)
			(window open:)
		else
			(= window
				((systemWindow new:)
					top:			CTRL_BOX_TOP,	
					left:			CTRL_BOX_LEFT,
					bottom:		CTRL_BOX_BOTTOM,
					right:		CTRL_BOX_RIGHT,
					priority:	15,
					open:			,
					yourself:
				)
			)
		)

		(for	((= theX 30) (= theY 30) (= node (FirstNode elements)))
				node
				((= node nextNode))
			(= nextNode (NextNode node))			
			(if (not (IsObject (= obj (NodeValue node))))
				(return)
			)
			; if nsRect not set yet
			(if (and	(not (& (obj signal?) FIXED_POSN))
						(<= (obj nsRight?) 0)
					)
				(obj show: theX theY)
				(= theX (+ 20 (obj nsRight?)))
			else
				(obj show:)
			)
		)

		(if (not okButton)
			(= okButton (NodeValue (self first?)))
		)

		(if curIcon 
			(theGame 
				setCursor: 
					theCursor	
					TRUE
					(+ (curIcon nsLeft?) (/ (- (curIcon nsRight?) (curIcon nsLeft?)) 2)) 
					(- (curIcon nsBottom?) 3)
			)
		)

		(self doit:, hide:)
	)

	(method (dispatchEvent event &tmp eO thisIcon thePort [buffer 50] eType eMsg)
		(= eType (event type?))
		(= eMsg	(event message?))
		(cond
			((== eType helpEvent)
				(= thisIcon (self firstTrue: #onMe event))
				(if (and thisIcon
							(thisIcon helpVerb?)
						)
				 	(= thePort (GetPort))
					(if (systemWindow respondsTo: #eraseOnly)
						(= eO (systemWindow eraseOnly?))
						(systemWindow eraseOnly: TRUE)
						(Print
							font:			userFont,
							width:		250,
							addText:		(thisIcon noun?) (thisIcon helpVerb?) NULL 1 0 0 (thisIcon modNum?),
							init:
						)
						(systemWindow eraseOnly: eO)
					else
						(Print
							font:			userFont,
							width:		250,
							addText:		(thisIcon noun?) (thisIcon helpVerb?) NULL 1 0 0 (thisIcon modNum?),
							init:
						)
					)
				 	(SetPort thePort)
				)
				(if helpIconItem
					(helpIconItem signal: (& (helpIconItem signal?) (~ TRANSLATOR)))
				)
				(theGame setCursor: ARROW_CURSOR)
				(return FALSE)
			)
			((& eType direction)
				(switch eMsg 
					(dirS
						(cond
							((and	(IsObject highlightedIcon)
									(highlightedIcon respondsTo: #retreat:)
								)
								; move slider down
								(highlightedIcon retreat:)
								(return FALSE)
							)
							((or 	(not (IsObject highlightedIcon))
									(& (highlightedIcon signal?) VICON)
								)
								(self advance:)
								(return FALSE)
							)
						)
					)
					(dirN
						(cond
							((and	(IsObject highlightedIcon)
									(highlightedIcon respondsTo: #advance:)
								)
								; move slider up
								(highlightedIcon advance:)
								(return FALSE)
							)
							((or 	(not (IsObject highlightedIcon))
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
			(else
				(super dispatchEvent: event)
			)
		)
	)

	(method (select theIcon relVer)
		(return
			(theIcon select: (if (>= argc 2) relVer))
		)
	)

	(method (advanceCurIcon &tmp theIcon)
	)

	(method (swapCurIcon)
	)

	(method (hide)
		(if window
			(window dispose:)
			(= window 0)
		)
		(if (& state IB_ACTIVE)
			(sounds pause:FALSE)
			(&= state (~ IB_ACTIVE))
		)
	)
)


(class ControlIcon kindof IconItem
	(properties
		theObj		0
		selector		0
	)

	(method (doit)
		(if theObj
		  	(if (& signal HIDEBAR)
		  		(
		  			(if gameControls
		  				gameControls
		  			else
		  				GameControls
		  			)
					hide:		
		  		)
		  	)
			(theGame
				panelObj:		theObj,
				panelSelector:	selector
			)
		)
	)
)
