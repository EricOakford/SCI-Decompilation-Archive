;;; Sierra Script 1.0 - (do not remove this comment)
(script# 98)
(include sci.sh)
(use Main)
(use IconBar)
(use Tutorial)
(use System)


(local
	local0
)
(class Kq6IconBar of IconBar
	(properties
		elements 0
		size 0
		height 0
		underBits 0
		oldMouseX 0
		oldMouseY 0
		curIcon 0
		highlightedIcon 0
		prevIcon 0
		curInvIcon 0
		useIconItem 0
		helpIconItem 0
		walkIconItem 0
		port 0
		window 0
		state $0400
		activateHeight 10
		y 0
		hiRes 0
	)
	
	(method (handleEvent event &tmp temp0 eventType temp2 temp3 theTheCursor theCurIcon theCurInvIcon)
		(event localize:)
		(= eventType (event type?))
		(cond 
			((& state $0004))
			(
				(or
					(and
						(not eventType)
						(& state $0400)
						(<= -10 (event y?))
						(<= (event y?) height)
						(<= 0 (event x?))
						(<= (event x?) 320)
						(not (= temp0 0))
					)
					(and
						(== eventType evKEYBOARD)
						(or
							(== (event message?) KEY_ESCAPE)
							(== (event message?) KEY_DELETE)
						)
						(= temp0 1)
					)
				)
				(event globalize:)
				(= oldMouseX (event x?))
				(= oldMouseY (event y?))
				(= theTheCursor theCursor)
				(= theCurIcon curIcon)
				(= theCurInvIcon curInvIcon)
				(self show:)
				(theGame setCursor: 999)
				(if temp0
					(theGame
						setCursor:
							theCursor
							1
							(+
								(curIcon nsLeft?)
								(/
									(- (curIcon nsRight?) (curIcon nsLeft?))
									(if hiRes 4 else 2)
								)
							)
							(if (not hiRes)
								(- (curIcon nsBottom?) 3)
							else
								(-
									(+
										(curIcon nsTop?)
										(CelHigh
											(curIcon view?)
											(curIcon loop?)
											(curIcon cel?)
										)
									)
									3
								)
							)
					)
				)
				(self doit:)
				(= temp3
					(if (or (user canControl:) (user canInput:))
						(curIcon cursor?)
					else
						waitCursor
					)
				)
				(if temp0
					(theGame setCursor: temp3 1 oldMouseX oldMouseY)
				else
					(theGame setCursor: temp3 1)
				)
				(self hide:)
			)
			((& eventType evKEYBOARD)
				(switch (event message?)
					(KEY_RETURN
						(cond 
							((not (IsObject curIcon)))
							((or (!= curIcon useIconItem) curInvIcon)
								(event
									type: (curIcon type?)
									message:
										(if (== curIcon useIconItem)
											(curInvIcon message?)
										else
											(curIcon message?)
										)
								)
							)
							(else (event type: 0))
						)
					)
					(KEY_NUMPAD0
						(if (user canControl:) (self swapCurIcon:))
						(event claimed: 1)
					)
					(JOY_NULL
						(if (& (event type?) evJOYSTICK)
							(self advanceCurIcon:)
							(event claimed: 1)
						)
					)
				)
			)
			((& eventType evMOUSEBUTTON)
				(cond 
					((& (event modifiers?) emSHIFT) (self advanceCurIcon:) (event claimed: 1))
					((& (event modifiers?) emCTRL)
						(if (user canControl:) (self swapCurIcon:))
						(event claimed: 1)
					)
					((IsObject curIcon)
						(event
							type: (curIcon type?)
							message:
								(if (== curIcon useIconItem)
									(curInvIcon message?)
								else
									(curIcon message?)
								)
						)
					)
				)
			)
		)
	)
	
	(method (show &tmp temp0 temp1 temp2 temp3 theY temp5 temp6 temp7)
		(sounds pause:)
		(= state (| state $0020))
		(theGame setCursor: 999 1)
		(= local0 ((= temp0 (self at: 0)) view?))
		(= hiRes
			(if (and global169 (not (Platform 5)))
				(Platform 6)
			else
				0
			)
		)
		(if hiRes
			(temp0 view: (temp0 hrView?))
		else
			(temp0 view: (temp0 view?))
		)
		(= height
			(CelHigh (temp0 view?) (temp0 loop?) (temp0 cel?))
		)
		(= port (GetPort))
		(SetPort -1)
		(if hiRes
			(= height (/ height 2))
			(= underBits (Graph 15 y 0 (+ y height) 319))
		else
			(= underBits (Graph grSAVE_BOX y 0 (+ y height) 320 1))
		)
		(= temp1 (PicNotValid))
		(PicNotValid 1)
		(= temp3 0)
		(= theY y)
		(= temp5 (FirstNode elements))
		(while temp5
			(= temp6 (NextNode temp5))
			(if (not (IsObject (= temp7 (NodeValue temp5))))
				(return)
			)
			(if hiRes
				(temp7
					maskView: (temp0 hrView?)
					view: (temp0 hrView?)
					nsRight: 0
				)
			else
				(temp7
					maskView: (temp0 maskView?)
					view: (temp0 view?)
					nsRight: 0
				)
			)
			(if (<= (temp7 nsRight?) 0)
				(temp7 show: temp3 theY)
				(= temp3 (temp7 nsRight?))
			else
				(temp7 show:)
			)
			(= temp5 temp6)
		)
		(self updateInvIcon:)
		(PicNotValid temp1)
		(Graph
			grUPDATE_BOX
			y
			0
			(* height (if hiRes 2 else 1))
			639
			1
			(if hiRes underBits else 0)
		)
		(self highlight: curIcon)
	)
	
	(method (hide &tmp temp0 temp1 temp2)
		(if (& state $0020)
			(sounds pause: 0)
			(= state (& state $ffdf))
			(= temp0 (FirstNode elements))
			(while temp0
				(= temp1 (NextNode temp0))
				(if (not (IsObject (= temp2 (NodeValue temp0))))
					(return)
				)
				((= temp2 (NodeValue temp0))
					signal: (& (temp2 signal?) $ffdf)
					view: local0
					maskView: local0
					hiRes: 0
				)
				(= temp0 temp1)
			)
			(if
				(and
					(not (& state $0800))
					helpIconItem
					(& (helpIconItem signal?) $0010)
				)
				(helpIconItem signal: (& (helpIconItem signal?) $ffef))
			)
			(Graph grRESTORE_BOX underBits)
			(Graph grUPDATE_BOX y 0 (+ y height) 320 1)
			(Graph grREDRAW_BOX y 0 (+ y height) 320)
			(SetPort port)
			(= hiRes 0)
			(= height activateHeight)
		)
	)
	
	(method (advance &tmp temp0 temp1)
		(= temp1 1)
		(while (<= temp1 size)
			(= temp0
				(self
					at: (mod (+ temp1 (self indexOf: highlightedIcon)) size)
				)
			)
			(if (not (IsObject temp0))
				(= temp0 (NodeValue (self first:)))
			)
			(breakif (not (& (temp0 signal?) $0004)))
			(++ temp1)
		)
		(self highlight: temp0 (& state $0020))
	)
	
	(method (retreat &tmp temp0 temp1)
		(= temp1 1)
		(while (<= temp1 size)
			(= temp0
				(self
					at: (mod (- (self indexOf: highlightedIcon) temp1) size)
				)
			)
			(if (not (IsObject temp0))
				(= temp0 (NodeValue (self last:)))
			)
			(breakif (not (& (temp0 signal?) $0004)))
			(++ temp1)
		)
		(self highlight: temp0 (& state $0020))
	)
	
	(method (updateInvIcon &tmp temp0 temp1)
		(if
		(and curInvIcon (not (& (useIconItem state?) $0004)))
			(curInvIcon view: (if hiRes 973 else 970))
			(if (ego has: (inventory indexOf: curInvIcon))
				(= temp0
					(+
						(/
							(-
								(- (useIconItem nsRight?) (useIconItem nsLeft?))
								(CelWide
									(curInvIcon view?)
									(curInvIcon loop?)
									(curInvIcon cel?)
								)
							)
							2
						)
						(useIconItem nsLeft?)
					)
				)
				(= temp1
					(+
						y
						(/
							(-
								(- (useIconItem nsBottom?) (useIconItem nsTop?))
								(CelHigh
									(curInvIcon view?)
									(curInvIcon loop?)
									(curInvIcon cel?)
								)
							)
							2
						)
						(useIconItem nsTop?)
					)
				)
				(DrawCel
					(curInvIcon view?)
					(curInvIcon loop?)
					(curInvIcon cel?)
					temp0
					temp1
					-1
					0
					(if hiRes underBits else 0)
				)
				(if (& (useIconItem signal?) $0004)
					(useIconItem mask:)
				)
			else
				(= curInvIcon 0)
			)
		)
	)
)

(class Kq6IconItem of IconI
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
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
		noun 0
		modNum 0
		helpVerb 0
		hiRes 0
		hrView 981
	)
	
	(method (show theNsLeft theNsTop &tmp [temp0 7])
		(= hiRes
			(if (and global169 (not (Platform 5)))
				(Platform 6)
			else
				0
			)
		)
		(= signal (| signal $0020))
		(if argc
			(= nsRight
				(+ (= nsLeft theNsLeft) (CelWide view loop cel))
			)
			(= nsBottom
				(+ (= nsTop theNsTop) (CelHigh view loop cel))
			)
		else
			(= nsRight (+ nsLeft (CelWide view loop cel)))
			(= nsBottom (+ nsTop (CelHigh view loop cel)))
		)
		(DrawCel
			view
			loop
			cel
			nsLeft
			nsTop
			-1
			0
			(if hiRes (theIconBar underBits?) else 0)
		)
		(if (& signal $0004) (self mask:))
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select param1 &tmp newEvent temp1 theGameScript theIconBarUnderBits)
		(if hiRes
			(= theIconBarUnderBits (theIconBar underBits?))
		else
			(= theIconBarUnderBits 0)
		)
		(return
			(cond 
				((& signal $0004) 0)
				((and argc param1 (& signal $0001))
					(DrawCel
						view
						loop
						(= temp1 1)
						nsLeft
						nsTop
						-1
						0
						theIconBarUnderBits
					)
					(Graph
						grUPDATE_BOX
						nsTop
						nsLeft
						nsBottom
						nsRight
						1
						theIconBarUnderBits
					)
					(while (!= ((= newEvent (Event new:)) type?) 2)
						(newEvent localize:)
						(cond 
							((self onMe: newEvent)
								(if (not temp1)
									(DrawCel
										view
										loop
										(= temp1 1)
										nsLeft
										nsTop
										-1
										0
										theIconBarUnderBits
									)
									(Graph
										grUPDATE_BOX
										nsTop
										nsLeft
										nsBottom
										nsRight
										1
										theIconBarUnderBits
									)
								)
							)
							(temp1
								(DrawCel
									view
									loop
									(= temp1 0)
									nsLeft
									nsTop
									-1
									0
									theIconBarUnderBits
								)
								(Graph
									grUPDATE_BOX
									nsTop
									nsLeft
									nsBottom
									nsRight
									1
									theIconBarUnderBits
								)
							)
						)
						(newEvent dispose:)
					)
					(newEvent dispose:)
					(if (== temp1 1)
						(DrawCel
							view
							loop
							0
							nsLeft
							nsTop
							-1
							0
							theIconBarUnderBits
						)
						(Graph
							grUPDATE_BOX
							nsTop
							nsLeft
							nsBottom
							nsRight
							1
							theIconBarUnderBits
						)
					)
					(if
						(and
							(= theGameScript (theGame script?))
							(theGameScript isKindOf: Tutorial)
						)
						(cond 
							(
								(and
									(== (theGameScript nextItem?) self)
									(!=
										(theGameScript nextAction?)
										((theIconBar helpIconItem?) message?)
									)
								)
								(theGameScript cue:)
							)
							((not temp1) (return 0))
							(else (theGameScript report:) (return 0))
						)
					)
					temp1
				)
				(else
					(if
						(and
							(= theGameScript (theGame script?))
							(theGameScript isKindOf: Tutorial)
						)
						(if
							(and
								(== (theGameScript nextItem?) self)
								(!=
									(theGameScript nextAction?)
									((theIconBar helpIconItem?) message?)
								)
							)
							(theGameScript cue:)
						else
							(theGameScript report:)
							(return 0)
						)
					)
					1
				)
			)
		)
	)
	
	(method (highlight param1 &tmp temp0 temp1 temp2 temp3 theHighlightColor [temp5 2])
		(if
			(or
				(not (& signal $0020))
				(== highlightColor -1)
				hiRes
			)
			(return)
		)
		(if (and argc param1)
			(= theHighlightColor highlightColor)
			(= temp0 (+ nsTop 1))
			(= temp1 (+ nsLeft 1))
			(= temp2 (- nsBottom 3))
			(= temp3 (- nsRight 3))
			(Graph
				grDRAW_LINE
				temp0
				temp1
				temp0
				temp3
				theHighlightColor
				-1
				-1
			)
			(Graph
				grDRAW_LINE
				temp0
				temp3
				temp2
				temp3
				theHighlightColor
				-1
				-1
			)
			(Graph
				grDRAW_LINE
				temp2
				temp3
				temp2
				temp1
				theHighlightColor
				-1
				-1
			)
			(Graph
				grDRAW_LINE
				temp2
				temp1
				temp0
				temp1
				theHighlightColor
				-1
				-1
			)
		else
			(self show:)
			(if (Kq6IconBar curInvIcon?)
				(Kq6IconBar updateInvIcon:)
			)
		)
		(Graph
			grUPDATE_BOX
			(- nsTop 2)
			(- nsLeft 2)
			nsBottom
			(+ nsRight 3)
			1
		)
	)
	
	(method (onMe event)
		(return
			(if
			(>= (event x?) (if hiRes (/ nsLeft 2) else nsLeft))
				(if
				(>= (event y?) (if hiRes (/ nsTop 2) else nsTop))
					(if
					(<= (event x?) (if hiRes (/ nsRight 2) else nsRight))
						(<= (event y?) (if hiRes (/ nsBottom 2) else nsBottom))
					)
				)
			else
				0
			)
		)
	)
	
	(method (mask)
		(DrawCel
			maskView
			maskLoop
			maskCel
			(+
				nsLeft
				(/
					(-
						(CelWide view loop cel)
						(CelWide maskView maskLoop maskCel)
					)
					2
				)
			)
			(+
				nsTop
				(/
					(-
						(CelHigh view loop cel)
						(CelHigh maskView maskLoop maskCel)
					)
					2
				)
			)
			-1
			0
			(if hiRes (theIconBar underBits?) else 0)
		)
	)
)
