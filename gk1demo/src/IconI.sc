;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include sci.sh)
(use GK)
(use Print)
(use SysWindow)
(use Obj)


(class IconI of Obj
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
		signal $0001
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
			(if
			(and gPseudoMouse (gPseudoMouse respondsTo: #stop))
				(gPseudoMouse stop:)
			)
		)
	)
	
	(method (select param1 &tmp newEvent temp1 temp2)
		(= temp1 0)
		(return
			(if (and argc param1 (& signal notUpd))
				(= temp1 1)
				(if drawIt (DrawCel view loop temp1 nsLeft nsTop -1))
				(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
				(while (!= ((= newEvent (Event new:)) type?) 2)
					(newEvent localize:)
					(if drawIt
						(cond 
							((self onMe: newEvent)
								(if (not temp1)
									(DrawCel view loop (= temp1 1) nsLeft nsTop -1)
									(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
								)
							)
							(temp1
								(DrawCel view loop (= temp1 0) nsLeft nsTop -1)
								(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
							)
						)
					)
					(newEvent dispose:)
				)
				(newEvent dispose:)
				(if (and drawIt (== temp1 1))
					(DrawCel view loop 0 nsLeft nsTop -1)
					(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
				)
				temp1
			else
				0
			)
		)
	)
	
	(method (highlight param1 &tmp theGModNum)
		(if (== modNum -1) (= theGModNum gModNum))
		(cond 
			(param1
				(if gDialog (gDialog dispose:))
				(Print
					addText: noun 0 0 0 0 0 theGModNum
					x: 0
					y: 150
					window: mapWindow
					modeless: 1
					init:
				)
				(if drawIt (DrawCel view loop 1 nsLeft nsTop -1))
			)
			(drawIt (DrawCel view loop 0 nsLeft nsTop -1))
		)
	)
	
	(method (onMe param1 param2 &tmp temp0 temp1)
		(if (IsObject param1)
			(= temp0 (param1 x?))
			(= temp1 (param1 y?))
		else
			(= temp0 param1)
			(= temp1 param2)
		)
		(return
			(if
				(and
					(>= temp0 nsLeft)
					(>= temp1 nsTop)
					(<= temp0 nsRight)
				)
				(<= temp1 nsBottom)
			else
				0
			)
		)
	)
)

(class IconMap of Set
	(properties
		elements 0
		size 0
		curIcon 0
		highlightedIcon 0
		prevIcon 0
		prevWaitCurs 0
	)
	
	(method (doit &tmp temp0 temp1)
		(= temp1 (- gPEventY 10))
		(cond 
			(
			(not (= temp0 (self firstTrue: #onMe gPEventX temp1)))
				(if highlightedIcon
					(highlightedIcon highlight: 0)
					(= highlightedIcon 0)
				)
				((ScriptID gModNum 2) doit: gPEventX temp1)
			)
			((and temp0 (!= temp0 highlightedIcon)) (self highlight: temp0))
		)
	)
	
	(method (dispose)
		(= gPrevWaitCurs prevWaitCurs)
		(gGkKDHandler delete: self)
		(gGkMDHandler delete: self)
		(super dispose:)
	)
	
	(method (show &tmp [temp0 5] temp5 temp6 temp7)
		(gGK handsOff:)
		(gGkKDHandler add: self)
		(gGkMDHandler add: self)
		(= prevWaitCurs gPrevWaitCurs)
		(= gPrevWaitCurs 999)
		(gSounds pause:)
		(gGK setCursor: 999 1)
		(PicNotValid 1)
		(= temp5 (FirstNode elements))
		(while temp5
			(= temp6 (NextNode temp5))
			(if (not (IsObject (= temp7 (NodeValue temp5))))
				(return)
			)
			(temp7 show:)
			(= temp5 temp6)
		)
		(PicNotValid 0)
		(Graph grUPDATE_BOX 0 0 320 320 1)
	)
	
	(method (advance &tmp [temp0 2])
	)
	
	(method (retreat &tmp [temp0 2])
	)
	
	(method (goLeft &tmp [temp0 2])
	)
	
	(method (goRight &tmp [temp0 2])
	)
	
	(method (select theCurIcon param2)
		(return
			(if (theCurIcon select: (if (>= argc 2) param2))
				(if (not (& (theCurIcon signal?) $0002))
					(= curIcon theCurIcon)
				)
				1
			else
				0
			)
		)
	)
	
	(method (highlight theHighlightedIcon &tmp temp0)
		(if (IsObject highlightedIcon)
			(highlightedIcon highlight: 0)
		)
		((= highlightedIcon theHighlightedIcon) highlight: 1)
	)
	
	(method (handleEvent pEvent)
		(pEvent claimed: 1)
		(self dispatchEvent: pEvent)
	)
	
	(method (dispatchEvent pEvent &tmp pEventY pEventX pEventType pEventMessage temp4 pEventClaimed)
		(= pEventX (pEvent x?))
		(= pEventY (pEvent y?))
		(= pEventType (pEvent type?))
		(= pEventMessage (pEvent message?))
		(= pEventClaimed (pEvent claimed?))
		(= temp4 (self firstTrue: #onMe pEvent))
		(if (& pEventType evJOYSTICK)
			(switch pEventMessage
				(JOY_RIGHT (self advance:))
				(JOY_LEFT (self retreat:))
			)
		else
			(switch pEventType
				(evMOUSEBUTTON
					(cond 
						((not temp4) (self noDest:))
						((self select: temp4 1) (= pEventClaimed 1) (temp4 doit:))
					)
				)
				(evKEYBOARD
					(switch pEventMessage
						(KEY_ESCAPE (= pEventClaimed 1))
						(KEY_DELETE (= pEventClaimed 1))
						(KEY_RETURN
							(cond 
								((not temp4) (self noDest:))
								((self select: temp4 1) (= pEventClaimed 1) (temp4 doit:))
							)
						)
						(KEY_SHIFTTAB (self retreat:))
						(KEY_TAB (self advance:))
						(KEY_F5
							(if (not (& ((global69 at: 11) signal?) $0004))
								(if gNewEventHandler (return gNewEventHandler))
								(gGK save:)
								(pEvent claimed: 1)
							)
						)
						(KEY_F7
							(if (not (& ((global69 at: 11) signal?) $0004))
								(if gNewEventHandler (return gNewEventHandler))
								(gGK restore:)
								(pEvent claimed: 1)
							)
						)
					)
				)
			)
		)
		(return pEventClaimed)
	)
)

(instance mapWindow of SysWindow
	(properties
		color 47
		back 0
	)
)
