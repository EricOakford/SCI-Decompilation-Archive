;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include game.sh)
(use Main)
(use Print)
(use Window)
(use System)


(class MapIcon of Object	;EO: was called "IconI"; changed to avoid name conflicts
	(properties
		view -1
		loop -1
		cel -1
		nsLeft 0
		nsTop -1
		nsRight 0
		nsBottom 0
		state $0000
		modifiers $0000
		signal RELVERIFY
		noun 0
		modNum -1
		helpVerb 0
		drawIt 1
	)
	
	(method (show)
		(if drawIt
			(= nsBottom (+ nsTop (CelHigh view loop cel)))
			(= nsRight (+ nsLeft (CelWide view loop cel)))
			(DrawCel view loop cel nsLeft nsTop -1)
			(if (and pMouse (pMouse respondsTo: #stop))
				(pMouse stop:)
			)
		)
	)
	
	(method (select relVer &tmp event whichCel tut)
		(= whichCel 0)
		(return
			(if (and argc relVer (& signal RELVERIFY))
				(= whichCel 1)
				(if drawIt (DrawCel view loop whichCel nsLeft nsTop -1))
				(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
				(while (!= ((= event (Event new:)) type?) mouseUp)
					(event localize:)
					(if drawIt
						(cond 
							((self onMe: event)
								(if (not whichCel)
									(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
									(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
								)
							)
							(whichCel
								(DrawCel view loop (= whichCel 0) nsLeft nsTop -1)
								(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
							)
						)
					)
					(event dispose:)
				)
				(event dispose:)
				(if (and drawIt (== whichCel 1))
					(DrawCel view loop 0 nsLeft nsTop -1)
					(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
				)
				whichCel
			else
				FALSE
			)
		)
	)
	
	(method (highlight tOrF &tmp theMod)
		(if (== modNum -1)
			(= theMod curRoomNum)
		)
		(cond 
			(tOrF
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(Print
					addText: noun NULL NULL 0 0 0 theMod
					x: 0
					y: 150
					window: mapWindow
					modeless: TRUE
					init:
				)
				(if drawIt
					(DrawCel view loop 1 nsLeft nsTop -1)
				)
			)
			(drawIt
				(DrawCel view loop 0 nsLeft nsTop -1)
			)
		)
	)
	
	(method (onMe theObjOrX theY &tmp theObjOrXX theObjOrXY)
		(if (IsObject theObjOrX)
			(= theObjOrXX (theObjOrX x?))
			(= theObjOrXY (theObjOrX y?))
		else
			(= theObjOrXX theObjOrX)
			(= theObjOrXY theY)
		)
		(return
			(if
				(and
					(>= theObjOrXX nsLeft)
					(>= theObjOrXY nsTop)
					(<= theObjOrXX nsRight)
				)
				(<= theObjOrXY nsBottom)
			else
				0
			)
		)
	)
)

(class IconMap of Set
	(properties
		curIcon 0
		highlightedIcon 0
		prevIcon 0
		prevWaitCurs 0
	)
	
	(method (doit &tmp thisIcon theY)
		(= theY (- mouseY 10))
		(cond 
			((not (= thisIcon (self firstTrue: #onMe mouseX theY)))
				(if highlightedIcon
					(highlightedIcon highlight: 0)
					(= highlightedIcon 0)
				)
				((ScriptID curRoomNum 2) doit: mouseX theY)
			)
			((and thisIcon (!= thisIcon highlightedIcon))
				(self highlight: thisIcon)
			)
		)
	)
	
	(method (dispose)
		(= waitCursor prevWaitCurs)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (show &tmp theIcon pnv i theX theY node nextNode obj)
		(theGame handsOff:)
		(keyDownHandler add: self)
		(mouseDownHandler add: self)
		(= prevWaitCurs waitCursor)
		(= waitCursor ARROW_CURSOR)
		(sounds pause:)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(PicNotValid TRUE)
		(= node (FirstNode elements))
		(while node
			(= nextNode (NextNode node))
			(if (not (IsObject (= obj (NodeValue node))))
				(return)
			)
			(obj show:)
			(= node nextNode)
		)
		(PicNotValid 0)
		(Graph GShowBits MINTOP MINLEFT MAXRIGHT MAXRIGHT VMAP)
	)
	
	(method (advance &tmp theIcon i)
	)
	
	(method (retreat &tmp theIcon i)
	)
	
	(method (goLeft &tmp theIcon i)
	)
	
	(method (goRight &tmp theIcon i)
	)
	
	(method (select theIcon relVer)
		(return
			(if (theIcon select: (if (>= argc 2) relVer))
				(if (not (& (theIcon signal?) IMMEDIATE))
					(= curIcon theIcon)
				)
				TRUE
			else
				FALSE
			)
		)
	)
	
	(method (highlight theIcon &tmp sColor)
		(if (IsObject highlightedIcon)
			(highlightedIcon highlight: FALSE)
		)
		((= highlightedIcon theIcon) highlight: TRUE)
	)
	
	(method (handleEvent event)
		(event claimed: TRUE)
		(self dispatchEvent: event)
	)
	
	(method (dispatchEvent event &tmp evtY evtX evtType evtMsg thisIcon evtClaimed)
		(= evtX (event x?))
		(= evtY (event y?))
		(= evtType (event type?))
		(= evtMsg (event message?))
		(= evtClaimed (event claimed?))
		(= thisIcon (self firstTrue: #onMe event))
		(if (& evtType direction)
			(switch evtMsg
				(dirE
					(self advance:)
				)
				(dirW
					(self retreat:)
				)
			)
		else
			(switch evtType
				(mouseDown
					(cond 
						((not thisIcon)
							(self noDest:)
						)
						((self select: thisIcon TRUE)
							(= evtClaimed TRUE)
							(thisIcon doit:)
						)
					)
				)
				(keyDown
					(switch evtMsg
						(ESC
							(= evtClaimed TRUE)
						)
						(DELETE
							(= evtClaimed TRUE)
						)
						(ENTER
							(cond 
								((not thisIcon)
									(self noDest:)
								)
								((self select: thisIcon TRUE)
									(= evtClaimed TRUE)
									(thisIcon doit:)
								)
							)
						)
						(SHIFTTAB
							(self retreat:)
						)
						(TAB
							(self advance:)
						)
						(`#5
							(if (not (& ((theIconBar at: ICON_CONTROLS) signal?) DISABLED))
								(if fastCast (return fastCast))
								(theGame save:)
								(event claimed: TRUE)
							)
						)
						(`#7
							(if (not (& ((theIconBar at: ICON_CONTROLS) signal?) DISABLED))
								(if fastCast (return fastCast))
								(theGame restore:)
								(event claimed: TRUE)
							)
						)
					)
				)
			)
		)
		(return evtClaimed)
	)
)

(instance mapWindow of SysWindow
	(properties
		color 47
		back 0
	)
)
