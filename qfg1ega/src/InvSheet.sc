;;; Sierra Script 1.0 - (do not remove this comment)
(script# INVSHEET) ;206
(include game.sh)
(use Main)
(use Invent)
(use System)

(public
	theInvSheet 0
)

(procedure (DisplayInventoryItem item x y &tmp invQty [str 40])
	(Display
		(Format @str 206 0 (= invQty [invNum item]))
		p_at (+ x 5) y
		p_mode teJustRight
		p_font smallFont
		p_color vBLUE
		p_width 20
	)
	;display quantity
	(Display
		(Format @str 3 (+ item item) (if (> invQty 1) KEY_s else 0))
		p_at (+ x 30) y
		p_mode teJustLeft
		p_font smallFont
		p_color vBLUE
	)
	;display name of item

)

(procedure (DisplayMagicSpell spell x y &tmp spellIndex [str 8])
	(= spellIndex (- spell OPEN))
	(Display
		(Format @str 206 0 [spellCost spellIndex])
		p_at (+ x 7) y
		p_mode teJustLeft
		p_font smallFont
		p_color vBLUE
		p_width 20
	)
	;display MP cost of spell
	(Display
		4 spellIndex
		p_at (+ x 33) y
		p_mode teJustLeft
		p_font smallFont
		p_color vBLUE
	)
	;display name of spell
	(Display
		(Format @str 206 0 [egoStats spell])
		p_at (+ x 105) y
		p_mode teJustLeft
		p_font smallFont
		p_color vBLUE
	)
	;display skill of spell
)

(class InvSheet of Object
	(properties
		nsTop 0
		nsLeft 0
		nsBottom southEdge
		nsRight MAXRIGHT
		theWindow 0
	)
	
	(method (init &tmp i temp1 y fgColour totalWeight [str 40])
		(super init:)
		(= theWindow
			(NewWindow nsTop nsLeft nsBottom nsRight {} nwNORMAL nwON_TOP vBLUE vWHITE)
		)
		(Display 206 1 p_at 10 8 p_mode teJustLeft p_font 300 p_color vBLUE)
		;You Are Carrying:
		(= totalWeight (WtCarried))
		(= i 1)
		(= y 24)
		(while (<= i 41)
			(if (and (> [invNum i] 0) (> i 2))
				(if (<= y 160) 
					(DisplayInventoryItem i 10 y)
				)
				(= y (+ y 9))
			)
			(++ i)
		)
		(cond 
			((== y 24)
				(Display 206 2 p_at 40 (+ y 5) p_mode teJustLeft p_font 300 p_color vBLUE)
				;Nothing
			)
			((>= y 174) 
				(Display 206 3 p_at 40 172 p_mode teJustLeft p_font 300 p_color vBLUE)
				;. . . and more
			)
		)
		(= fgColour (if (< numColors 16) vRED else vLRED))
		(Display
			(Format @str 206 0 [invNum iGold])
			p_at 180 10
			p_mode teJustRight
			p_font 300
			p_color fgColour
			p_width 25
		)
		(Display 206 4 p_at 220 10 p_mode teJustLeft p_font 300 p_color fgColour)
		;Gold Coins
		(= fgColour (if (< numColors 16) vGREY else vLGREY))
		(Display
			(Format @str 206 0 [invNum iSilver])
			p_at 180 23
			p_mode teJustRight
			p_font 300
			p_color fgColour
			p_width 25
		)
		(Display 206 5 p_at 220 23 p_mode teJustLeft p_font 300 p_color fgColour)
		;Silver Coins
		(= fgColour
			(cond 
				((< totalWeight (/ (* (WtCarried) 90) 100))
					vBLUE
				)
				((< numColors 16) 
					vRED
				)
				(else 
					vLRED
				)
			)
		)
		(Display
			(Format @str 206 6 totalWeight)
			p_at 180 36
			p_mode teJustRight
			p_font 300
			p_color fgColour
			p_width 25
		)
		(Display 206 7 p_at 220 36 p_mode teJustLeft p_font 300 p_color fgColour)
		(Display
			(Format @str 206 6 (MaxLoad))
			p_at 180 49
			p_mode teJustRight
			p_font 300
			p_color fgColour
			p_width 25
		)
		(Display 206 8 p_at 220 49 p_mode teJustLeft p_font 300 p_color fgColour)
		(if [egoStats MAGIC]
			(Display 206 9 p_at 185 75 p_mode teJustLeft p_font 300 p_color vBLUE)
			(Display 206 10 p_at 218 75 p_mode teJustLeft p_font 300 p_color vBLUE)
			(Display 206 11 p_at 280 75 p_mode teJustLeft p_font 300 p_color vBLUE)
			(= y 91)
			(= i 17)
			(= y 91)
			(while (< i 25)
				(if (> [egoStats i] 0)
					(DisplayMagicSpell i 185 y)
					(= y (+ y 9))
				)
				(++ i)
			)
		)
	)
	
	(method (doit &tmp newEvent newEventType)
		(= newEventType 0)
		(while (not newEventType)
			(GlobalToLocal (= newEvent (Event new:)))
			(= newEventType (newEvent type?))
			(newEvent dispose:)
		)
		(self dispose:)
	)
	
	(method (dispose)
		(DisposeWindow theWindow)
		(super dispose:)
		(DisposeScript 206)
	)
)

(instance theInvSheet of InvSheet
	(properties)
)
