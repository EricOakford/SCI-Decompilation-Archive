;;; Sierra Script 1.0 - (do not remove this comment)
(script# KQ6ICON)
(include game.sh)
(use Main)
(use IconBar)


(class Kq6IconBar of IconBar	
	(method (show &tmp theIcon pnv i theX theY node nextNode obj)
		(sounds pause:)
		(|= state IB_ACTIVE)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(= height
			(CelHigh
				((= theIcon (self at: 0)) view?)
				(theIcon loop?)
				(theIcon cel?)
			)
		)
		(= port (GetPort))
		(SetPort -1)
		(= underBits (Graph GSaveBits y 0 (+ y height) SCRNWIDE VMAP))
		(= pnv (PicNotValid))
		(PicNotValid TRUE)
		(= theX 0)
		(= theY y)
		(= node (FirstNode elements))
		(while node
			(= nextNode (NextNode node))
			(if (not (IsObject (= obj (NodeValue node))))
				(return)
			)
			(if (<= (obj nsRight?) 0)
				(obj show: theX theY)
				(= theX (obj nsRight?))
			else
				(obj show:)
			)
			(= node nextNode)
		)
		(self updateInvIcon:)
		(PicNotValid pnv)
		(Graph GShowBits y 0 (+ y height) SCRNWIDE VMAP)
		(self highlight: curIcon)
	)
	
	(method (hide &tmp node nextNode obj)
		(if (& state IB_ACTIVE)
			(sounds pause: FALSE)
			(&= state (~ IB_ACTIVE))
			(= node (FirstNode elements))
			(while node
				(= nextNode (NextNode node))
				(if (not (IsObject (= obj (NodeValue node))))
					(return)
				)
				((= obj (NodeValue node))
					signal: (& (obj signal?) (~ IB_ACTIVE))
				)
				(= node nextNode)
			)
			(if
				(and
					(not (& state NOCLICKHELP))
					helpIconItem
					(& (helpIconItem signal?) TRANSLATOR)
				)
				(helpIconItem signal: (& (helpIconItem signal?) (~ TRANSLATOR)))
			)
			(Graph GRestoreBits underBits)
			(Graph GShowBits y 0 (+ y height) SCRNWIDE VMAP)
			(Graph GReAnimate y 0 (+ y height) SCRNWIDE)
			(SetPort port)
			(= height activateHeight)
		)
	)
	
	(method (advance &tmp theIcon i)
		(for ((= i 1)) (<= i size) ((++ i))
			(= theIcon
				(self
					at: (mod (+ i (self indexOf: highlightedIcon)) size)
				)
			)
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self first:)))
			)
			(breakif (not (& (theIcon signal?) DISABLED)))
		)
		(self highlight: theIcon (& state IB_ACTIVE))
	)
	
	(method (retreat &tmp theIcon i)
		(for ((= i 1)) (<= i size) ((++ i))
			(= theIcon
				(self
					at: (mod (- (self indexOf: highlightedIcon) i) size)
				)
			)
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self last:)))
			)
			(breakif (not (& (theIcon signal?) DISABLED)))
		)
		(self highlight: theIcon (& state IB_ACTIVE))
	)
	
	(method (updateInvIcon &tmp temp0 temp1)
		(if (and curInvIcon (not (& (useIconItem state?) DISABLED)))
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
	(method (highlight tOrF &tmp t l b r sColor [temp5 2])
		(if (or (not (& signal IB_ACTIVE)) (== highlightColor -1))
			(return)
		)
		(if (and argc tOrF)
			(= sColor highlightColor)
			(= t (+ nsTop 2))
			(= l (+ nsLeft 2))
			(= b (- nsBottom 3))
			(= r (- nsRight 4))
			(Graph GDrawLine t l t r sColor -1 -1)
			(Graph GDrawLine t r b r sColor -1 -1)
			(Graph GDrawLine b r b l sColor -1 -1)
			(Graph GDrawLine b l t l sColor -1 -1)
		else
			(self show:)
			(if (Kq6IconBar curInvIcon?)
				(Kq6IconBar updateInvIcon:)
			)
		)
		(Graph GShowBits (- nsTop 2) (- nsLeft 2) nsBottom (+ nsRight 3) VMAP)
	)
)
