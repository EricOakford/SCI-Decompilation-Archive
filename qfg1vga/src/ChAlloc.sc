;;; Sierra Script 1.0 - (do not remove this comment)
(script# CHALLOC)
(include game.sh) (include "203.shm")
(use Main)
(use Procs)
(use Print)
(use IconBar)
(use GControl)
(use Game)
(use Actor)
(use System)

(public
	chAlloc 0
)

(local
	[local0 14] = [999 0 1 2 3 4 12 5 6 7 8 9 10 11]
	[local14 14] = [28 40 52 64 76 88 100 40 52 64 76 88 100 112]
	[local28 14] = [100 100 100 100 100 100 100 204 204 204 204 204 204 204]
	[local42 14] = [0 1 2 3 4 5 6 7 9 10 1 9 11 12]
	[theTheEgoStats 49] = [0 25 10 15 15 10 0 20 15 10 0 0 10 0 0 10 25 15 15 10 25 10 0 15 0 0 0 0 0 10 15 25 10 10 0 10 0 5 10 10 5 5 67 78 68 20 23 27 1]
	local105
	pointsAvailable =  50
	[theEgoStats 14]
	[theTheTheEgoStats 14]
	nextRoom
)
(procedure (ShowValue y)
	(Display
		&rest
		100 220 y ;100 = p_at
		p_color 215
		p_width 30
		p_mode teJustCenter
		p_font 123
	)
	(Display
		&rest
		100 219 y ;100 = p_at
		p_color 91
		p_width 30
		p_mode teJustCenter
		p_font 123
	)
)

(procedure (UpdatePoints &tmp [str 4] i)
	(= i 1)
	(while (< i 14)
		(= [egoStats [local0 i]] [theEgoStats i])
		(++ i)
	)
	(DrawCel 802 8 1 215 142 15)
	(Format @str {%d} pointsAvailable)
	(if pointsAvailable
		(Display @str
			p_at 220 141
			p_color 215
			p_width 30
			p_mode teJustCenter
			p_font 123
		)
		(Display @str
			p_at 219 141
			p_color 50
			p_width 30
			p_mode teJustCenter
			p_font 123
		)
	else
		(ShowValue 141 @str)
	)
	(DrawCel 802 8 1 215 154 15)
	(ShowValue
		153
		(Format
			@str
			{%d}
			(= [egoStats HEALTH] (/ (+ (MaxHealth) 1) 2))
		)
	)
	(DrawCel 802 8 1 215 166 15)
	(ShowValue
		165
		(Format
			@str
			{%d}
			(= [egoStats STAMINA] (/ (+ (MaxStamina) 3) 4))
		)
	)
	(DrawCel 802 8 1 215 178 15)
	(ShowValue
		177
		(Format @str {%d} (= [egoStats MANA] (MaxMana)))
	)
)

(instance chAlloc of Room
	(properties
		picture 904
	)
	
	(method (init)
		(super init: &rest)
		(pointsAvail init:)
		(= userName 0)
		(self setScript: selectChar)
	)
	
	(method (dispose &tmp statNum)
		(startControls eachElementDo: #dispose dispose: release:)
		(= [egoStats HEALTH] (MaxHealth))
		(= [egoStats STAMINA] (MaxStamina))
		(= [egoStats MANA] (MaxMana))
		(= statNum 0)
		(while (< statNum NUMSTATS)
			(= [oldStats statNum] [egoStats statNum])
			(++ statNum)
		)
		(= oldScore score)
		(super dispose:)
	)
)

(instance selectChar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable:)
				(= cycles 2)
			)
			(1
				(theChar cel: heroType init:)
				(curRoom drawPic: (curRoom picture?) 9)
				(= ticks 60)
			)
			(2
				(startControls init: show:)
				(theIconBar enable:)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(event claimed: TRUE)
	)
)

(instance startControls of GameControls
	(properties)
	
	(method (init &tmp statNum)
		(self add: namePlate)
		(= statNum 1)
		(while (< statNum 14)
			(self
				add:
					((selectionIcon new:)
						nsLeft: [local28 statNum]
						nsTop: [local14 statNum]
						cel: [local42 statNum]
						maskCel: [local42 statNum]
						state: statNum
						yourself:
					)
			)
			(= [theTheTheEgoStats statNum]
				[theTheEgoStats (+ statNum (* heroType 14))]
			)
			(= [theEgoStats statNum]
				[theTheEgoStats (+ statNum (* heroType 14))]
			)
			(++ statNum)
		)
		(self
			add:
				(startIcon theObj: startCode selector: 57 yourself:)
				(cancelIcon theObj: cancelCode selector: 57 yourself:)
		)
		(super init: &rest)
	)
	
	(method (show)
		(= state (| state IB_ACTIVE))
		(self eachElementDo: #show)
		((= curIcon (= highlightedIcon (self at: 0)))
			highlight: TRUE
		)
		(DrawCel 802 5 0 1 148 15)
		(UpdatePoints)
		(Bclr fHideCursor)
		(theGame
			setCursor: ARROW_CURSOR TRUE (+ (curIcon nsLeft?) 5) (- (curIcon nsBottom?) 2)
		)
		(self doit: hide:)
	)
	
	(method (dispatchEvent event &tmp evtX evtY evtType evtMsg temp4 evtMod obj highlightedIconState)
		(if nextRoom
			(curRoom newRoom: nextRoom)
			(event dispose:)
			(return 1)
		)
		(= evtX (event x?))
		(= evtY (event y?))
		(= evtType (event type?))
		(= evtMsg (event message?))
		(= temp4 0)
		(= evtMod (event modifiers?))
		(= obj (self firstTrue: #onMe event))
		(event dispose:)
		(cond 
			((& evtType direction)
				(switch evtMsg
					(dirE
						(if (and highlightedIcon (highlightedIcon state?))
							(self select: obj 1)
							(spareSound number: 63 loop: 1 play:)
						)
					)
					(dirW
						(if (and highlightedIcon (highlightedIcon state?))
							(self select: obj 0)
							(spareSound number: 63 loop: 1 play:)
						)
					)
					(dirN
						(if (not highlightedIcon)
							(= highlightedIcon (self at: (- size 1)))
						)
						(self retreat:)
					)
					(dirS
						(if (not highlightedIcon)
							(= highlightedIcon (self at: 0))
						)
						(self advance:)
					)
					(dirNE
						(if (obj state?)
							(self select: obj 3)
							(spareSound number: 63 loop: 1 play:)
						)
					)
					(dirSE
						(if (obj state?)
							(self select: obj 2)
							(spareSound number: 63 loop: 1 play:)
						)
					)
				)
			)
			((== evtType nullEvt)
				(cond 
					((not (IsObject obj))
						(if (IsObject highlightedIcon)
							(highlightedIcon highlight: 0)
							(= highlightedIcon 0)
						)
					)
					((and obj (!= obj highlightedIcon)) (= oldMouseY 0) (self highlight: obj))
				)
			)
			((not (IsObject highlightedIcon)) 0)
			((== evtType mouseDown)
				(cond 
					((== highlightedIcon namePlate) 0)
					((not (obj state?))
						(cond 
							((== obj startIcon) (startCode doit:))
							((== obj cancelIcon) (cancelCode doit:))
							(else (self select: obj 1))
						)
					)
					((== evtMod shiftDown)
						(self select: obj 1)
						(spareSound number: 63 loop: 1 play:)
					)
					(else
						(self select: obj 0)
						(spareSound number: 63 loop: 1 play:)
					)
				)
			)
			((== evtType keyDown)
				(switch evtMsg
					(ESC
						(cancelCode doit:)
					)
					(ENTER
						(cond 
							((== highlightedIcon startIcon)
								(startCode doit:)
							)
							((== highlightedIcon cancelIcon)
								(cancelCode doit:)
							)
						)
					)
					(TAB
						(cond 
							((< (= highlightedIconState (highlightedIcon state?)) 1) 0)
							((< (= highlightedIconState (highlightedIcon state?)) 7)
								(highlightedIcon highlight: 0)
								(= highlightedIcon
									(self at: (+ highlightedIconState 5))
								)
								(self advance:)
							)
							((< highlightedIconState 13)
								(highlightedIcon highlight: 0)
								(= highlightedIcon
									(self at: (- highlightedIconState 5))
								)
								(self retreat:)
							)
						)
					)
					(else 
						(cond 
							((!= highlightedIcon namePlate) 0)
							(
								(or
									;EO: As of 6/4/2020, backtic sequences are now case-sensitive. Thanks, Kawa!
									(and (<= `a evtMsg) (<= evtMsg `z))
									(and (<= `A evtMsg) (<= evtMsg `Z))
									(and (<= `0 evtMsg) (<= evtMsg `9))
								)
								(self select: namePlate evtMsg)
							)
							((== evtMsg SPACEBAR)
								(self select: namePlate evtMsg)
							)
							((and (== evtMsg keyUp) local105)
								(self select: namePlate evtMsg)
							)
						)
					)
				)
			)
		)
		(return temp4)
	)
)

(instance selectionIcon of IconItem
	(properties
		view 802
		loop 1
		maskView 802
		maskLoop 2
		highlightColor 9
		lowlightColor 91
	)
	
	(method (show)
		(self highlight: 0)
		(= nsRight (+ nsLeft (if (< state 7) 102 else 111)))
		(= nsBottom (+ nsTop 12))
	)
	
	(method (select param1)
		(return
			(if (super select: &rest)
				(switch param1
					(1
						(cond 
							((not pointsAvailable) 0)
							((< pointsAvailable 5)
								(= [theEgoStats state] (+ [theEgoStats state] pointsAvailable))
								(= pointsAvailable 0)
							)
							((not [theEgoStats state])
								(if (>= pointsAvailable 15)
									(= [theEgoStats state] (+ [theEgoStats state] 5))
									(= pointsAvailable (- pointsAvailable 15))
								)
							)
							(else
								(= [theEgoStats state] (+ [theEgoStats state] 5))
								(= pointsAvailable (- pointsAvailable 5))
							)
						)
					)
					(0
						(cond 
							(
							(== [theEgoStats state] [theTheTheEgoStats state]) 0)
							(
								(and
									(== [theEgoStats state] 5)
									(not [theTheTheEgoStats state])
								)
								(= [theEgoStats state] 0)
								(= pointsAvailable (+ pointsAvailable 15))
							)
							(else
								(= [theEgoStats state] (- [theEgoStats state] 5))
								(= pointsAvailable (+ pointsAvailable 5))
							)
						)
					)
					(3
						(cond 
							((not pointsAvailable) 0)
							((not [theEgoStats state]) 0)
							(else
								(= [theEgoStats state] (+ [theEgoStats state] 1))
								(= pointsAvailable (- pointsAvailable 1))
							)
						)
					)
					(2
						(cond 
							(
							(== [theEgoStats state] [theTheTheEgoStats state]) 0)
							(
								(and
									(== [theEgoStats state] 5)
									(not [theTheTheEgoStats state])
								)
								(= [theEgoStats state] 0)
								(= pointsAvailable (+ pointsAvailable 15))
							)
							(else
								(= [theEgoStats state] (- [theEgoStats state] 1))
								(= pointsAvailable (+ pointsAvailable 1))
							)
						)
					)
				)
				(self highlight: 1)
				(UpdatePoints)
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (highlight param1 &tmp x [str 4] bgColor fgColor)
		(= x
			(if (< state 7) (+ nsLeft 71) else (+ nsLeft 80))
		)
		(DrawCel
			view
			8
			(if (< state 7) 0 else 1)
			x
			(+ nsTop 1)
			15
		)
		(if param1
			(DrawCel view loop cel nsLeft nsTop 15)
			(= bgColor 50)
			(= fgColor 215)
		else
			(DrawCel maskView maskLoop maskCel nsLeft nsTop 15)
			(= bgColor 91)
			(= fgColor 215)
		)
		(Display
			(Format @str {%d} [theEgoStats state])
			p_at (+ x 1) nsTop
			p_color fgColor
			p_width 25
			p_mode teJustRight
			p_font 123
		)
		(Display
			(Format @str {%d} [theEgoStats state])
			p_at x nsTop
			p_color bgColor
			p_width 25
			p_mode teJustRight
			p_font 123
		)
	)
)

(instance startIcon of ControlIcon
	(properties
		view 802
		loop 3
		cel 0
		nsLeft 9
		nsTop 157
	)
	
	(method (highlight param1)
		(if param1
			(DrawCel view loop 2 nsLeft nsTop 15)
		else
			(DrawCel view loop 0 nsLeft nsTop 15)
		)
	)
)

(instance cancelIcon of ControlIcon
	(properties
		view 802
		loop 4
		cel 0
		nsLeft 9
		nsTop 170
	)
	
	(method (highlight param1)
		(if param1
			(DrawCel view loop 2 nsLeft nsTop 15)
		else
			(DrawCel view loop 0 nsLeft nsTop 15)
		)
	)
)

(instance namePlate of IconItem
	(properties
		view 802
		loop 1
		cel 0
		nsLeft 100
		nsTop 22
		nsRight 300
		nsBottom 34
		maskView 802
		maskLoop 9
	)
	
	(method (show)
	)
	
	(method (select param1 &tmp [temp0 4])
		(return
			(if (super select: &rest)
				(TextSize @[temp0 0] @userName 123 0)
				(cond 
					((and (== param1 8) local105)
						(StrAt @userName (-- local105) 0)
						(DrawCel
							maskView
							maskLoop
							maskCel
							(+ nsLeft 46)
							nsTop
							15
						)
						(self highlight: 1)
					)
					((<= [temp0 3] 150)
						(StrAt @userName local105 param1)
						(StrAt @userName (++ local105) 0)
						(self highlight: 1)
					)
					(else (return 1))
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (highlight param1 &tmp bgColor fgColor theLoop)
		(if param1
			(= theLoop loop)
			(= bgColor 50)
			(= fgColor 215)
		else
			(= theLoop 2)
			(= bgColor 91)
			(= fgColor 215)
		)
		(DrawCel view theLoop cel nsLeft nsTop 15)
		(Display @userName
			p_at (+ nsLeft 47) nsTop
			p_color fgColor
			p_width 172
			p_mode teJustLeft
			p_font 123
		)
		(Display @userName
			p_at (+ nsLeft 46) nsTop
			p_color bgColor
			p_width 172
			p_mode teJustLeft
			p_font 123
		)
	)
)

(instance theChar of View
	(properties
		x 48
		y 145
		view 802
		priority 14
		signal fixPriOn
	)
)

(instance pointsAvail of View
	(properties
		x 102
		y 140
		view 802
		loop 7
		priority 15
		signal fixPriOn
	)
)

(instance cancelCode of Code
	(properties)
	
	(method (doit &tmp answer statNum startControlsCurIcon)
		(if
			(= answer
				(Print
					font: userFont
					mode: teJustCenter
					addText: N_ROOM NULL C_CANCEL 1 0 0 CHALLOC
					addButton: 0 N_ROOM NULL C_CANCEL 2 37 30 CHALLOC
					addButton: 1 N_ROOM NULL C_CANCEL 3 87 30 CHALLOC
					init:
				)
			)
			(= nextRoom CHARSEL)
		else
			(= startControlsCurIcon (startControls curIcon?))
			(= statNum 1)
			(while (< statNum 14)
				(= [theTheTheEgoStats statNum]
					[theTheEgoStats (+ statNum (* heroType 14))]
				)
				(= [theEgoStats statNum]
					[theTheEgoStats (+ statNum (* heroType 14))]
				)
				(++ statNum)
			)
			(= local105 (= userName 0))
			(DrawCel 802 9 0 146 22 15)
			(= pointsAvailable 50)
			(UpdatePoints)
			(startControls eachElementDo: #highlight 0)
			(theGame
				setCursor: ARROW_CURSOR TRUE
					(+ (startControlsCurIcon nsLeft?) 5)
					(- (startControlsCurIcon nsBottom?) 2)
			)
			(startControlsCurIcon highlight: 1)
		)
	)
)

(instance startCode of Code
	(properties)
	
	(method (doit &tmp answer)
		(= answer 1)
		(if pointsAvailable
			(= answer
				(Print
					font: userFont
					mode: teJustCenter
					addText: 1 0 2 1 0 0 203
					addButton: 0 1 0 2 2 37 20 203
					addButton: 1 1 0 2 3 45 40 203
					init:
				)
			)
		)
		(if answer
			(= nextRoom 300)
			(switch heroType
				(FIGHTER
					(ego get: iSword get: iShield)
				)
				(MAGIC_USER
					(ego get: iDagger learn: ZAP 10)
				)
				(THIEF
					(ego get: iDagger get: iLockPick)
					(= lockPickBonus 10)
				)
			)
		)
	)
)
