;;; Sierra Script 1.0 - (do not remove this comment)
(script# 98)
(include game.sh)
(use Main)
(use IconBar)


(class Kq6IconBar of IconBar
	
	(method (show &tmp temp0 temp1 temp2 temp3 theY temp5 temp6 temp7)
		(sounds pause:)
		(= state (| state IB_ACTIVE))
		(theGame setCursor: 999 1)
		(= height
			(CelHigh
				((= temp0 (self at: 0)) view?)
				(temp0 loop?)
				(temp0 cel?)
			)
		)
		(= port (GetPort))
		(SetPort -1)
		(= underBits (Graph grSAVE_BOX y 0 (+ y height) 320 1))
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
		(Graph GShowBits y 0 (+ y height) 320 1)
		(self highlight: curIcon)
	)
	
	(method (hide &tmp temp0 temp1 temp2)
		(if (& state IB_ACTIVE)
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
			(Graph GRestoreBits underBits)
			(Graph GShowBits y 0 (+ y height) 320 1)
			(Graph GReAnimate y 0 (+ y height) 320)
			(SetPort port)
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
			(breakif (not (& (temp0 signal?) DISABLED)))
			(++ temp1)
		)
		(self highlight: temp0 (& state IB_ACTIVE))
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
			(breakif (not (& (temp0 signal?) DISABLED)))
			(++ temp1)
		)
		(self highlight: temp0 (& state IB_ACTIVE))
	)
	
	(method (updateInvIcon &tmp temp0 temp1)
		(if
		(and curInvIcon (not (& (useIconItem state?) DISABLED)))
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
				)
				(if (& (useIconItem signal?) DISABLED)
					(useIconItem mask:)
				)
			else
				(= curInvIcon 0)
			)
		)
	)
)

(class Kq6IconItem of IconItem
	
	(method (highlight param1 &tmp temp0 temp1 temp2 temp3 theHighlightColor [temp5 2])
		(if
		(or (not (& signal IB_ACTIVE)) (== highlightColor -1))
			(return)
		)
		(if (and argc param1)
			(= theHighlightColor highlightColor)
			(= temp0 (+ nsTop 2))
			(= temp1 (+ nsLeft 2))
			(= temp2 (- nsBottom 3))
			(= temp3 (- nsRight 4))
			(Graph
				GDrawLine
				temp0
				temp1
				temp0
				temp3
				theHighlightColor
				-1
				-1
			)
			(Graph
				GDrawLine
				temp0
				temp3
				temp2
				temp3
				theHighlightColor
				-1
				-1
			)
			(Graph
				GDrawLine
				temp2
				temp3
				temp2
				temp1
				theHighlightColor
				-1
				-1
			)
			(Graph
				GDrawLine
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
			GShowBits
			(- nsTop 2)
			(- nsLeft 2)
			nsBottom
			(+ nsRight 3)
			1
		)
	)
)
