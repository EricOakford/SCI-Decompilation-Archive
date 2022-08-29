;;; Sierra Script 1.0 - (do not remove this comment)
(script# GKICONBAR)
(include game.sh)
(use Main)
(use IconBar)
(use System)

(public
	GKIconbar 0
)

(class GKIconItem of IconItem
	(properties
		topIcon 0
		leftIcon 0
		bottomIcon 0
		rightIcon 0
	)
	
	(method (highlight tOrF &tmp t l b r theCel)
		(if
			(or
				(& signal DISABLED)
				(not (& signal IB_ACTIVE))
				(== highlightColor -1)
			)
			(return)
		)
		(= theCel (if (and argc tOrF) 1 else 0))
		(= t (+ nsTop 1))
		(= l (+ nsLeft 1))
		(= b (- nsBottom 2))
		(= r (- nsRight 2))
		(DrawCel view loop theCel nsLeft nsTop -1)
	)
)

(class GKIconbar of IconBar
	
	(method (show &tmp theIcon pnv i theX theY node nextNode obj)
		(sounds pause:)
		(|= state IB_ACTIVE)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(= height 32)
		(= port (GetPort))
		(SetPort -1)
		(= underBits (Graph GSaveBits y 0 (+ y height) MAXRIGHT VMAP))
		(= pnv (PicNotValid))
		(PicNotValid 1)
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
		(self drawCels:)
		(if curInvIcon (self showInvItem:))
		(PicNotValid pnv)
		(Graph GShowBits y MINLEFT (+ y height) MAXRIGHT VMAP)
		(self highlight: curIcon 0 1)
	)
	
	(method (hide &tmp temp0)
		(if (& state IB_ACTIVE)
			(super hide:)
			(if (theGame keepBar?) (self draw:))
		)
	)
	
	(method (advance &tmp theIcon)
		(= theIcon (highlightedIcon rightIcon?))
		(while (& (theIcon signal?) DISABLED)
			(= theIcon
				(theIcon rightIcon?)
			)
		)
		(self checkNewIcon: theIcon)
	)
	
	(method (retreat &tmp theIcon)
		(= theIcon (highlightedIcon leftIcon?))
		(while (& (theIcon signal?) DISABLED)
			(= theIcon
				(theIcon leftIcon?)
			)
		)
		(self checkNewIcon: theIcon)
	)
	
	(method (highlight theIcon posnCursor tOrF &tmp temp0)
		(if (not (& (theIcon signal?) DISABLED))
			(if (IsObject highlightedIcon)
				(highlightedIcon highlight: FALSE)
			)
			(= highlightedIcon theIcon)
			(if (not (if (>= argc 3) tOrF))
				(theIcon highlight: TRUE)
			)
		)
		(if (and (>= argc 2) posnCursor)
			(theGame
				setCursor: theCursor TRUE
					(+
						(theIcon nsLeft?)
						(/
							(-
								(theIcon nsRight?)
								(theIcon nsLeft?)
							)
							2
						)
					)
					(- (theIcon nsBottom?) 3)
			)
		)
	)
	
	(method (dispatchEvent event)
		(if (& (event type?) direction)
			(switch (event message?)
				(dirN
					(self goUp:)
				)
				(dirS
					(self goDown:)
				)
			)
		)
		(super dispatchEvent: event)
	)
	
	(method (disable)
		(super disable: &rest)
		(if (and argc (theGame keepBar?))
			(self draw:)
		)
	)
	
	(method (enable)
		(super enable: &rest)
		(if (and (not (OneOf curRoomNum 50)) (theGame keepBar?))
			(self draw:)
		)
	)
	
	(method (draw &tmp oldPort pnv node nextNode obj theView)
		(= height
			(CelHigh
				((= theView (self at: 0)) view?)
				(theView loop?)
				(theView cel?)
			)
		)
		(= pnv (PicNotValid))
		(= oldPort (GetPort))
		(SetPort -1)
		(PicNotValid 1)
		(= node (FirstNode elements))
		(while node
			(= nextNode (NextNode node))
			(if (not (IsObject (= obj (NodeValue node))))
				(return)
			)
			(obj show:)
			(= node nextNode)
		)
		(self drawCels:)
		(if curInvIcon (self showInvItem:))
		(PicNotValid pnv)
		(Graph GShowBits MINTOP MINLEFT 32 MAXRIGHT VMAP)
		(SetPort oldPort)
	)
	
	(method (erase &tmp pnv oldPort)
		(= pnv (PicNotValid))
		(= oldPort (GetPort))
		(SetPort -1)
		(PicNotValid 1)
		(Graph GFillRect MINTOP MINLEFT 32 MAXRIGHT VMAP myLowlightColor)
		(PicNotValid pnv)
		(Graph GShowBits MINTOP MINLEFT 32 MAXRIGHT VMAP)
		(SetPort oldPort)
	)
	
	(method (showInvItem &tmp theX theY pnv)
		(if (= pnv (PicNotValid)) (PicNotValid FALSE))
		(Graph GFillRect
			(+ (useIconItem nsTop?) 3)
			(+ (useIconItem nsLeft?) 3)
			(- (useIconItem nsBottom?) 3)
			(- (useIconItem nsRight?) 3)
			1
			myLowlightColor
		)
		(if (ego has: (inventory indexOf: curInvIcon))
			(= theX
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
			(= theY
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
				theX
				theY
				-1
			)
			(if (& (useIconItem signal?) DISABLED)
				(useIconItem mask:)
			)
			(Graph GShowBits
				(useIconItem nsTop?)
				(useIconItem nsLeft?)
				(useIconItem nsBottom?)
				(useIconItem nsRight?)
				VMAP
			)
			(PicNotValid pnv)
		else
			(= curInvIcon 0)
		)
	)
	
	(method (drawCels)
		(DrawCel 959 1 1 0 0 -1)
		(DrawCel 959 1 0 43 0 -1)
		(DrawCel 959 5 0 49 0 -1)
		(DrawCel 959 1 0 166 0 -1)
		(DrawCel 959 1 0 217 0 -1)
		(DrawCel 959 1 0 247 0 -1)
		(DrawCel 959 1 2 276 0 -1)
		(self drawScore:)
	)
	
	(method (goUp &tmp theIcon)
		(= theIcon (highlightedIcon topIcon:))
		(while (& (theIcon signal?) DISABLED)
			(= theIcon
				(theIcon topIcon:)
			)
		)
		(self checkNewIcon: theIcon)
	)
	
	(method (goDown &tmp theIcon)
		(= theIcon
			(highlightedIcon bottomIcon?)
		)
		(while (& (theIcon signal?) DISABLED)
			(= theIcon
				(theIcon bottomIcon?)
			)
		)
		(self checkNewIcon: theIcon)
	)
	
	(method (drawScore)
		(DrawCel 959 3 0 223 0 -1)
		(DrawCel 959 4 2 229 20 -1)
		(DrawCel 959 4 5 237 20 -1)
		(if (< score 10)
			(DrawCel 959 4 score 233 3 -1)
		else
			(DrawCel 959 4 (/ score 10) 229 3 -1)
			(DrawCel 959 4 (mod score 10) 237 3 -1)
		)
	)
	
	(method (updateScore &tmp oldPort)
		(= oldPort (GetPort))
		(SetPort -1)
		(self drawScore:)
		(SetPort oldPort)
	)
	
	(method (checkNewIcon theIcon)
		(if
			(and
				(== theIcon (self at: ICON_INVENTORY))
				(not (self curInvIcon?))
			)
			(= theIcon (self at: ICON_CONTROLS))
		)
		(if (theGame barUp?)
			(= highlightedIcon theIcon)
		else
			(self highlight: theIcon (& state IB_ACTIVE))
		)
	)
)
