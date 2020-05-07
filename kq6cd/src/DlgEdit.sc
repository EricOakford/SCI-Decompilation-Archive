;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	DLGEDIT.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Brian K. Hughes
;;;;
;;;;	This class is a dialog editor.  It allows the user to create dialogs
;;;;	with dialog items, then writes the SCI code out.
;;;;
;;;;	Classes:
;;;;		DlgWindow
;;;;		_DItem
;;;;		_DText
;;;;		_DIcon
;;;;		_DButton
;;;;		_DEdit
;;;;		_DSelector
;;;;		DialogEditor
;;;;
;;;;	Note!  Selector items are not yet supported by Print.


(script# DLGEDIT)
(include game.sh)
(use Main)
(use Intrface)
(use Print)
(use PolyEdit)
(use Window)
(use File)
(use System)


;;;(procedure
;;;	TerminateString
;;;	GetMsgParms
;;;	GetTextType
;;;)

(local
	saveWin
	windowUp
	[fileName 40]

	[winTitle 20]

	mainItems = [
		{DIALOG}		0		0
		{About}		0		0
		{Item}		0		0
		{Edit}		0		0
		{Look}		0		0
		{Del}			0		0
		{Win}			0		0
		{Help}		0		0
		{eXit}		`x		0
		0
	]

	editButtonItems = [
		{EDITING}	0		0
		{Text}		0		0
		{Font}		0		0
		{Value}		0		0
		{Position}	0		0
		{Help}		0		0
		{Menu}		0		0
		0
	]

	editTextItems = [
		{EDITING}	0		0
		{Text}		0		0
		{Font}		0		0
		{Just}		0		0
		{Width}		0		0
		{Position}	0		0
		{Help}		0		0
		{Menu}		0		0
		0
	]
	
	editEditItems = [
		{EDITING}	0		0
		{Font}		0		0
		{Length}		0		0
		{Position}	0		0
		{Text}		0		0
		{Help}		0		0
		{Menu}		0		0
		0
	]

	editIconItems = [
		{EDITING}	0		0
		{View}		0		0
		{Loop}		0		0
		{Cel}			0		0
		{Position}	0		0
		{Help}		0		0
		{Menu}		0		0
		0
	]

	editSelectorItems = [
		{EDITING}	0		0
		{Width}		0		0
		{Length}		0		0
		{Position}	0		0
		{Help}		0		0
		{Menu}		0		0
		0
	]

	editWindowItems = [
		{WINDOW}		0		0
		{Create}		0		0
		{Position}	0		0
		{Delete}		0		0
		{Title}		0		0
		{Help}		0 		0
		{Menu}		0		0
		0
	]
)

; State values
(enum
	Main
	EditButton
	EditText
	EditEdit
	EditIcon
	EditSelector
	EditWindow
)

(define	BMOD 16)

;ษอออออออออออออป
;บ             บ
;บ Click Menus บ
;บ             บ
;ศอออออออออออออผ

(instance mainMenu of ClickMenu
	(method (init)
		(super init: @mainItems)
	)
)		

(instance editBMenu of ClickMenu
	(method (init)
		(super init: @editButtonItems)
	)
)

(instance editTMenu of ClickMenu
	(method (init)
		(super init: @editTextItems)
	)
)

(instance editEMenu of ClickMenu
	(method (init)
		(super init: @editEditItems)
	)
)

(instance editIMenu of ClickMenu
	(method (init)
		(super init: @editIconItems)
	)
)

(instance editSMenu of ClickMenu
	(method (init)
		(super init: @editSelectorItems)
	)
)

(instance editWMenu of ClickMenu
	(method (init)
		(super init: @editWindowItems)
	)
)



;ษออออออออป
;บ        บ
;บ Window บ
;บ        บ
;ศออออออออผ

(class DlgWindow of SysWindow
	(properties
		noun		0
		verb		0
		case		0
		seq		0
		modNum	0
	)
;;;	(methods
;;;		create
;;;		moveTo
;;;		editMsg
;;;		editPosn
;;;		editTitle
;;;	)
	(method (open &tmp savePort)
		(= type (if title wTitled else stdWindow))
		(= savePort (GetPort))
		(super open: &rest)
		(SetPort savePort)
		(= windowUp TRUE)
	)
	(method (dispose)
		(super dispose:)
		(= windowUp FALSE)
	)
	(method (create &tmp t l b r num theItem)
		(= t 190)
		(= l 320)
		(= b 0)
		(= r 0)

		(for	((= num 0))
				(< num (DialogEditor size?))
				((++ num))
			(= theItem (DialogEditor at: num))
			(= t (Min (theItem nsTop?) t))
			(= l (Min (theItem nsLeft?) l))
			(= b (Max (theItem nsBottom?) b))
			(= r (Max (theItem nsRight?) r))
		)
		(DialogEditor eachElementDo: #hide)
		(self dispose:)
		(= top 		(- t MARGIN))
		(= bottom	(+ b MARGIN))
		(=	left		(- l MARGIN))
		(=	right		(+ r MARGIN))
		(self open:)
		(DialogEditor eachElementDo: #draw)
	)
	(method (moveTo mX mY &tmp num theItem dX dY)
		(for 	((= num 0))
				(< num (DialogEditor size?))
				((++ num))
			(= theItem (DialogEditor at: num))
			(= dX (- (theItem nsLeft?) left))
			(= dY (- (theItem nsTop?) top))
			(theItem
				hide:		,
				moveTo: 	(+ mX dX) (+ mY dY)
			)
		)
		(= left mX)
		(= top mY)
		(self create:)
	)
	(method (editMsg)
		;
		; Set/Change message parameters
		;
		(if (GetMsgParms self)
			(if title
				(Memory MDisposePtr title)
			)
			(= title (Memory MNeedPtr (Message MsgSize modNum noun verb case seq)))
			(Message MsgGet modNum noun verb case seq title)
		)
	 	(self create:)
	)
	(method (editPosn &tmp event eX eY h w)
		(if windowUp
			(Prints {Click to where the top left of the
						window should be}
			)
			(while (!= ((= event (Event new:)) type?) mouseDown)
				(event dispose:)
			)
			(= eX (event x?))
			(= eY (- (event y?) 10))
			(event dispose:)

			; Don't let the window go off screen
			(= h (- bottom top))
			(= w (- right left))
			(= eX (Max 0 (Min eX (- 320 w))))
			(= eY (Max 0 (Min eY (- 190 h))))

			(self moveTo: eX eY)
		else
			(Prints {No window to position!})
		)
	)
	(method (editTitle)
		;
		; Enter either:
		;		1) literal text (50 bytes allocated off heap)
		;		2) message parameters (will allocate as needed)
		;
		(switch (GetTextType self)
			(0
				(return)
			)
			(2
				(self editMsg:)
				(return)
			)
		)

		; If previous title was message, dump its buffer
		(if seq
			(Memory MDisposePtr title)
			(= noun (= verb (= case (= seq (= modNum (= title 0))))))
		)

		; If no title (or dumped message), allocate new buffer
		(if (not title)
			(= title (Memory MNeedPtr 50))
			(StrCpy title {title})
		)

		(Print
			addTitle:	@winTitle,
			addText:		{Enter new title:},
			addEdit:		title 50 0 12 title,
			init:
		)
		(self create:)
	)
)



;ษออออออออออออออป
;บ              บ
;บ Dialog Items บ
;บ              บ
;ศออออออออออออออผ

(class _DItem kindof DItem
	(properties
		underBits		0
	)
;;;	(methods
;;;		hide
;;;		editPosn
;;;		showHelp
;;;	)
	(method (select hilite)
		(self hide:)
		(if hilite
			(|= state dSelected)
		else
			(&= state (~ dSelected))
		)
		(self draw:)
	)
	(method (hide &tmp t l b r)
		(if underBits
			(Graph GRestoreBits underBits)
			(= t (- nsTop 1))
			(= l (- nsLeft 1))
			(= b (+ nsBottom 1))
			(= r (+ nsRight 1))
			(= underBits 0)
			(Graph GReAnimate t l b r)
		)
	)
	(method (draw &tmp t l b r)
	 	(= t (- nsTop 1))
	 	(= l (- nsLeft 1))
	 	(= b (+ nsBottom 1))
	 	(= r (+ nsRight 1))
		(if underBits
			(UnLoad MEMORY underBits)	;Graph GRestoreBits underBits)
			(= underBits 0)
		)
		(= underBits (Graph GSaveBits t l b r VMAP))
		(DrawControl self)
	)
	(method (editPosn &tmp [str 25] [str2 5] num theX theY)
		(Format @str {%d} nsLeft)
		(Format @str2 {%d} nsTop)
		(= num
			(Print
				addTitle:	@winTitle,
				addText:		{Enter new position:},
				addText:		{x = } 0 12,
				addText:		{y = } 65 12,
				addEdit:		@str 5 25 12 @str,
				addEdit:		@str2 6 90 12 @str2,
				font:			0,
				addButton:	0 {_Cancel_}	35 26,
				init:
			)
		)
		(if num
			(= theX (Max MARGIN (ReadNumber @str)))
			(= theY (Max MARGIN (ReadNumber @str2)))
			(self
				hide:		,
				moveTo:	theX theY,
				draw:
			)
		)
	)
	(method (handleEvent event &tmp ret)
		(= ret 0)
		(event claimed: TRUE)
		(= ret (self track: event))
		(return ret)
	)
 	(method (track event &tmp eX eY [str 40])
		(if (== (event type?) mouseDown)
			(self hide:)
			(repeat
				(= event (Event new: leaveIt))
				(event localize:)
				(= eX (event x?))
				(= eY (event y?))
				(DrawStatus (Format @str {DRAGGING:  %d, %d} eX eY))
				(self moveTo: eX eY)
				(event dispose:)
				(breakif (not (StillDown)))
			)
			(DrawStatus { } 0 0)
			(DrawStatus 0)
			(if (DialogEditor curMenu?)
				((DialogEditor curMenu?) init:)
			)
			(DrawPic (curRoom picture?) PLAIN)
			(if windowUp
				(DlgWindow create:)
			else
				(DialogEditor eachElementDo: #draw)
			)
		)
	)
	(method (dispose)
		(self hide:)
		(super dispose: &rest)
	)
)

(class _DText kindof _DItem
	(properties
		type		dText
		text		0
		font		SYSFONT
		mode		teJustLeft
		width		0
		noun		0
		verb		0
		case		0
		seq		0
		modNum	0
	)
;;;	(methods
;;;		editFont
;;;		editJust
;;;		editMsg
;;;		editText
;;;		editWidth
;;;	)
	(method (setSize w &tmp [r 4])
		(TextSize @[r 0] text font (if argc w else width))
		(-- nsLeft)
		(-- nsTop)
		(= nsBottom (+ nsTop [r 2] 1))
		(= nsRight (+ nsLeft [r 3] 1))
	)
	(method (editFont &tmp [str 25] num)
		(= num
			(Print
				addTitle:	@winTitle,
				font:			0,
				width:		90,
				addText:		{Enter new font number:},
				addEdit:		@str 6 0 24,
				addButton:	SYSFONT		{_System_}	100 0,
				addButton:	userFont		{__User__}	100 14,
				addButton:	smallFont	{__Small__}	100 28,
				addButton:	bigFont		{___Big___}	100 42,
				addButton:	-1				{_Cancel_}	100 56,
				init:
			)
		)
		(if str
			(= num (ReadNumber @str))
		)
		(if (!= num -1)
			(self
				hide:		,
				font:		num,
				setSize:	,
				draw:
			)
		)
	)
	(method (editJust &tmp [str 25] num)
		(= str 0)
		(= num
			(Print
				addTitle:	@winTitle,
				font:			0,
				width:		100,
				addText:		{Enter justification mode:},
				addButton:	1	{___Left___} 	100 0,
				addButton:	2	{_Center_}		100 14,
				addButton:	3	{__Right__}		100 28,
				addButton:	0	{_Cancel_}		100 42,
				init:
			)
		)
		(if str
			(= num (ReadNumber @str))
		)
		(if num
			(self
				hide:		,
				mode:
					(switch num
						(1		teJustLeft)
						(2		teJustCenter)
						(3		teJustRight)
					),
				setSize:	,
				draw:
			)
		)
	)
	(method (editMsg)
		;
		; Set/Change message parameters
		;
		(if (GetMsgParms self)
			(Memory MDisposePtr text)
			(= text (Memory MNeedPtr (Message MsgSize modNum noun verb case seq)))
			(Message MsgGet modNum noun verb case seq text)
		)
	 	(self
	 		hide:		,
	 		setSize:	,
	 		draw:
	 	)
	)
	(method (editText)
		;
		; Enter either:
		;		1) literal text (100 bytes allocated off heap)
		;		2) message parameters (will allocate as needed)
		;
		(switch (GetTextType self)
			(0
				(return)
			)
			(2
				(self editMsg:)
				(return)
			)
		)

		; If we have a sequence number, then the last text used was from a
		;	message file - dump the pointer & allocate a new one (we don't
		;	need to reallocate heap if we last used a literal string)
		(if seq
			(Memory MDisposePtr text)
			(= text (Memory MNeedPtr 100))
			(StrCpy text {text})
			(= noun (= verb (= case (= seq (= modNum 0)))))
		)

		(Print
			addTitle:	@winTitle,
			addText:		{Enter new text:},
			addEdit:		text 50 0 12 text,
			init:
		)
		(self
			hide:		,
			setSize:	,
			draw:
		)
	)
	(method (editWidth &tmp [str 25] num)
		(Format @str {%d} width)
		(= num
			(Print
				addTitle:	@winTitle,
				addText:		{Enter new width:},
				addEdit:		@str 6 0 12 @str,
				font:			0,
				addButton:	0 {_Cancel_} 0 26,
				init:
			)
		)
		(if str
			(= num (ReadNumber @str))
		)
		(if num
			(self
				hide:		,
				width:	num,
				setSize:	,
				draw:
			)
		)
	)
	(method (showHelp)
		(Print
			font:		userFont,
			width:	250,
			addText:
				{Text Menu:\n\n
				__Text     - Change the text\n
				__Font     - Change the font of the text\n
				__Just     - Change justification mode\n
				__Position - Change the position of the text\n
				__Menu     - Return to the Main Menu\n},
			init:
		)
	)
)

(class _DIcon kindof _DItem
	(properties
		type		dIcon
		view		0
		loop		0
		cel		0
	)
;;;	(methods
;;;		editView
;;;		editLoop
;;;		editCel
;;;	)
	(method (setSize)
		(= nsRight (+ nsLeft (CelWide view loop cel)))
		(= nsBottom (+ nsTop (CelHigh view loop cel)))
	)
	(method (editView &tmp [str 25] num)
		(Format @str {%d} view)
		(= num
			(Print
				addTitle:	@winTitle,
				addText:		{Enter new view number:},
				addEdit:		@str 5 0 12 @str,
				font:			0,
				addButton:	0 {_Cancel_} 0 26,
				init:
			)
		)
		(if str
			(= num (ReadNumber @str))
		)
		(if num
			(= view num)
		)
		(self
			hide:		,
			setSize:	,
			draw:
		)
	)
	(method (editLoop &tmp [str 25] num)
		(Format @str {%d} loop)
		(= num
			(Print
				addTitle:	@winTitle,
				addText:		{Enter new loop number:},
				addEdit:		@str 5 0 12 @str,
				font:			0,
				addButton:	0 {_Cancel_} 0 26,
				init:
			)
		)
		(if str
			(= num (ReadNumber @str))
		)
		(if num
			(= loop num)
		)
		(self
			hide:		,
			setSize:	,
			draw:
		)
	)
	(method (editCel &tmp [str 25] num)
		(Format @str {%d} cel)
		(= num
			(Print
				addTitle:	@winTitle,
				addText:		{Enter new cel number:},
				addEdit:		@str 5 0 12 @str,
				font:			0,
				addButton:	0 {_Cancel_} 0 26,
				init:
			)
		)
		(if str
			(= num (ReadNumber @str))
		)
		(if num
			(= cel num)
		)
		(self
			hide:		,
			setSize:	,
			draw:
		)
	)
	(method (showHelp)
		(Print
			font:		userFont,
			width:	250,
			addText:
				{Icon Menu:\n\n
				__View     - Change the view of the icon\n
				__Loop     - Change the loop of the icon\n
				__Cel      - Change the cel of the icon\n
				__Position - Change the position of the icon\n
				__Menu     - Return to the Main Menu\n},
			init:
		)
	)
)

(class _DButton kindof _DItem
	(properties
		type		dButton
		state 	dActive
		text		0
		font		SYSFONT
		noun		0
		verb		0
		case		0
		seq		0
		modNum	0
	)
;;;	(methods
;;;		editFont
;;;		editMsg
;;;		editText
;;;		editValue
;;;	)
	(method (setSize &tmp [r 4])
;;;		(define	BMOD 16)

		(TextSize @[r 0] text font 0 NULL)

		(+= [r 2] 2)
		(+= [r 3] 2)
		(= nsBottom (+ nsTop [r 2]))
		(= [r 3] (* (/ (+ [r 3] (- BMOD 1)) BMOD) BMOD))
		(= nsRight (+ [r 3] nsLeft))
	)
	(method (editFont &tmp [str 25] num)
		(= num
			(Print
				addTitle:	@winTitle,
				font:			0,
				width:		90,
				addText:		{Enter new font number:},
				addEdit:		@str 6 0 24,
				addButton:	SYSFONT		{_System_}	100 0,
				addButton:	userFont		{__User__}	100 14,
				addButton:	smallFont	{__Small__}	100 28,
				addButton:	bigFont		{___Big___}	100 42,
				addButton:	-1				{_Cancel_}	100 56,
				init:
			)
		)
		(if str
			(= num (ReadNumber @str))
		)
		(if (!= num -1)
			(self
				hide:		,
				font:		num,
				setSize:	,
				draw:
			)
		)
	)
	(method (editMsg)
		;
		; Set/Change message parameters
		;
		(if (GetMsgParms self)
			(Memory MDisposePtr text)
			(= text (Memory MNeedPtr (Message MsgSize modNum noun verb case seq)))
			(Message MsgGet modNum noun verb case seq text)
		)
	 	(self
	 		hide:		,
	 		setSize:	,
	 		draw:
	 	)
	)
	(method (editText)
		;
		; Enter either:
		;		1) literal text (50 bytes allocated off heap)
		;		2) message parameters (will allocate as needed)
		;
		(switch (GetTextType self)
			(0
				(return)
			)
			(2
				(self editMsg:)
				(return)
			)
		)

		; If we have a sequence number, then the last text used was from a
		;	message file - dump the pointer & allocate a new one (we don't
		;	need to reallocate heap if we last used a literal string)
		(if seq
			(Memory MDisposePtr text)
			(= text (Memory MNeedPtr 50))
			(StrCpy text {button})
			(= noun (= verb (= case (= seq (= modNum 0)))))
		)

		(Print
			addTitle:	@winTitle,
			addText:		{Enter new text:},
			addEdit:		text 50 0 12 text,
			init:
		)
		(self
			hide:		,
			setSize:	,
			draw:
		)
	)
	(method (editValue &tmp [str 25] num)
		(Format @str {%d} value)
		(= num
			(Print
				addTitle:	@winTitle,
				addText:		{Enter new value:},
				addEdit:		@str 6 0 12 @str,
				font:			0,
				addButton:	0 {_Cancel_} 0 26,
				init:
			)
		)
		(if str
			(= num (ReadNumber @str))
		)
		(if num
			(= value num)
		)
	)
	(method (showHelp)
		(Print
			font:		userFont,
			width:	250,
			addText:
				{Button Menu:\n\n
				__Text     - Change the button text\n
				__Font     - Change the font of the button text\n
				__Value    - Change the return value of the button\n
				__Position - Change the position of the button\n
				__Menu     - Return to the Main Menu\n},
			init:
		)
	)
)

(class _DEdit kindof _DItem
	(properties
		type		dEdit
		state 	dActive
		text		0
		font		SYSFONT
		max		0
		cursor	0
	)
;;;	(methods
;;;		editFont
;;;		editLength
;;;		editText
;;;	)
	(method (setSize &tmp [r 4])
		; box is as sized by max * width of an "M"
		(TextSize @[r 0] {M} font 0 NULL)
		(= nsBottom (+ nsTop [r 2]))
		(= nsRight (+ nsLeft (/ (* [r 3] max 3) 4)))
		(= cursor (StrLen text))
	)
	(method (editFont &tmp [str 25] num)
		(= num
			(Print
				addTitle:	@winTitle,
				font:			0,
				width:		90,
				addText:		{Enter new font number:},
				addEdit:		@str 6 0 24,
				addButton:	SYSFONT		{_System_}	100 0,
				addButton:	userFont		{__User__}	100 14,
				addButton:	smallFont	{__Small__}	100 28,
				addButton:	bigFont		{___Big___}	100 42,
				addButton:	-1				{_Cancel_}	100 56,
				init:
			)
		)
		(if str
			(= num (ReadNumber @str))
		)
		(if (!= num -1)
			(self
				hide:		,
				font:		num,
				setSize:	,
				draw:
			)
		)
	)
	(method (editLength &tmp [str 25] num)
		(Format @str {%d} max)
		(= num
			(Print
				addTitle:	@winTitle,
				addText:		{Enter new maximum length:},
				addEdit:		@str 5 0 12 @str,
				font:			0,
				addButton:	0 {_Cancel_} 0 26,
				init:
			)
		)
		(if str
			(= num (ReadNumber @str))
		)
		(if num
			(self
				max: 		num,
				hide:		,
				setSize:	,
				draw:
			)
		)
	)
	(method (editText)
		(Print
			addTitle:	@winTitle,
			addText:		{Enter new default text:},
			addEdit:		text 25 0 12 text,
			init:
		)
		(self
			hide:		,
			setSize:	,
			draw:
		)
	)
	(method (showHelp)
		(Print
			font:		userFont,
			width:	250,
			addText:
				{Edit Menu:\n\n
				__Font     - Change the font of the edit text\n
				__Length   - Change the maximum length of input\n
				__Position - Change the position of the edit\n
				__Text     - Change the default edit text\n
				__Menu     - Return to the Main Menu\n},
			init:
		)
	)
)

(class _DSelector kindof _DItem
	(properties
		type 			dSelector
		state 		0
		font 			0
		x 				20
		y 				6
	)
;;;	(methods
;;;		editLength
;;;		editWidth
;;;	)
	(method (setSize &tmp [r 4])
		(TextSize @[r 0] {M} font 0 NULL)
		(= nsBottom (+ nsTop 20 (* [r 2] y)))
		(= nsRight (+ nsLeft (/ (* [r 3] x 3) 4)))
	)
	(method (editLength &tmp [str 25] num)
		(Format @str {%d} y)
		(= num
			(Print
				addTitle:	@winTitle,
				addText:		{Enter new length:},
				addEdit:		@str 5 0 12 @str,
				font:			0,
				addButton:	0 {_Cancel_} 0 26,
				init:
			)
		)
		(if str
			(= num (ReadNumber @str))
		)
		(if num
			(self
				y: 		num,
				hide:		,
				setSize:	,
				draw:
			)
		)
	)
	(method (editWidth &tmp [str 25] num)
		(Format @str {%d} x)
		(= num
			(Print
				addTitle:	@winTitle,
				addText:		{Enter new width:},
				addEdit:		@str 5 0 12 @str,
				font:			0,
				addButton:	0 {_Cancel_} 0 26,
				init:
			)
		)
		(if str
			(= num (ReadNumber @str))
		)
		(if num
			(self
				x: 		num,
				hide:		,
				setSize:	,
				draw:
			)
		)
	)
	(method (showHelp)
		(Print
			font:		userFont,
			width:	250,
			addText:
				{Selector Menu:\n\n
				__Width    - Change the selector width (in chars)\n
				__Length   - Change number of selector lines\n
				__Position - Change the position of the selector\n
				__Menu     - Return to the Main Menu\n},
			init:
		)
	)
)



;ษออออออออออออออออออป
;บ                  บ
;บ The DialogEditor บ
;บ                  บ
;ศออออออออออออออออออผ

(class DialogEditor kindof List
	(properties
		name		{DialogEditor}
		state		0
		curItem	0
		curMenu	0
	)
;;;	(methods
;;;		handleEvent
;;;		delItem
;;;		exit
;;;		writeFile
;;;		changeState
;;;	)

	(method (init)
		(= saveWin systemWindow)
		(StrCpy @winTitle {DialogEditor__v1.1})
		((= systemWindow SysWindow)
			color:	0,
			back:		255
		)
		(theGame setCursor: ARROW_CURSOR)
		(self changeState: Main)
	)

	(method (dispose)
		(mainMenu dispose:)
		(DlgWindow dispose:)
		(= systemWindow saveWin)
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
		(DrawStatus { } 0 0)
		(DrawStatus 0)
		(super dispose:)
		(DrawPic (curRoom picture?) PLAIN)
		(DisposeScript 111)
	)

	(method (changeState newState)
		(if curMenu
			(curMenu dispose:)
		)
		(= curMenu
			(switch (= state newState)
				(Main				mainMenu)
				(EditButton		editBMenu)
				(EditText		editTMenu)
				(EditEdit		editEMenu)
				(EditIcon		editIMenu)
				(EditSelector	editSMenu)
				(EditWindow		editWMenu)
				(else				0)
			)
		)
		(if curMenu
			(curMenu init:)
		)
	)
	
	(method (delItem &tmp theItem [buffer 40] t l b r)
		(if size
			(if
				(Print
					addText:		{Delete current item?},
					font:			0,
					addButton:	1 {Yes} 		0 12,
					addButton:	0 {No}		40 12,
					init:
				)
				(self delete: curItem)
				(curItem dispose:)
				(= curItem 0)
				(if size
					(= curItem (self at: 0))
				)
			)
		else
			(Prints {Nothing to delete!})
		)
	)

	(method (exit &tmp [str 100] rc file fileMode)
		; If there's no window, create one
		(if (not windowUp)
			(DlgWindow create:)
		)

		; Figure out what to do with our info
		(if (not fileName)
			(Format @fileName {%d.dlg} curRoomNum)
		)
		(= rc
			(Print
				addTitle:	@winTitle,
				addText:		{Save to: },
				addEdit: 	@fileName 25 60 0 @fileName,
				font:			0,
				addButton: 	1 {__Save__}	10	12,
				addButton: 	2 {_Abandon_}	80 12,
				addButton: 	0 {_Cancel_} 	151 12,
				init:
			)
		)
		
		(if (not rc)
			(return 0)
		)
		(if (== rc 2)
			(return 1)
		)

		; See if we're to overwrite existing file
		(if (FileIO fileExists @fileName)
			(Format @str {The file '%s' already exists.__Now what?} @fileName)
			(= rc
				(Print
					addTitle:	@winTitle,
					addText:		@str,
					font:			0,
					addButton: 	1 {_Replace_}	0 24,
					addButton: 	2 {_Append_}	70 24,
					addButton: 	0 {_Cancel_}	125 24,
					init:
				)
			)
			(if (not rc)
				(return 0)
			)
		)

		; Open the file
		(= fileMode (if (== rc 1) fTrunc else fAppend))
		(if (not
		  	((= file (File new:))
		  		name:	@fileName,
		  		open:	fileMode
		  	))
			(Printf {Error opening '%s'} (file name?))
			(file dispose:)
			(return 0)
		)

		; Write header information & start Print
		(file
			writeString: {\t\t; DialogEditor v1.0\r},
			writeString: {\t\t; by Brian K. Hughes\r},
			writeString: {\t\t(Print\r}
		)
		(if windowUp
			(Format @str {\t\t\tposn:\t\t\t%d %d,\r}
				(DlgWindow left?)
				(DlgWindow top?)
			)
			(file writeString: @str)
			(if (DlgWindow title?)
				(if (DlgWindow seq?)
					(Format @str {\t\t\taddTitle:\t%d %d %d %d %d,\r}
						(DlgWindow noun?)
						(DlgWindow verb?)
						(DlgWindow case?)
						(DlgWindow seq?)
						(DlgWindow modNum?)
					)
				else
					(Format @str {\t\t\taddTitle:\t\{%s\},\r}
						(DlgWindow title?)
					)
				)
				(file writeString: @str)
			)
		)

		; Write body of Print
		(self writeFile: file)

		; Write end of Print & close file
		(if
			(Print
				addTitle:	@winTitle,
				addText:		{This dialog should be...},
				font:			0,
				addButton: 	0 {___Modal___} 	0 24,
				addButton: 	1 {_Modeless_}		0 38,
				init:
			)
			(file writeString: {\t\t\tmodeless:\tTRUE,\r})
		)
		(file
			writeString: {\t\t\tinit:\r},
			writeString: {\t\t)\r}
		)
		(file dispose:)
		(return TRUE)
	)

	(method (doit &tmp event [str 100])
		(self init:)
		(repeat
			(= event (Event new:))
			(if (not (curMenu handleEvent: event))
				(GlobalToLocal event)
				(breakif (self handleEvent: event))
			)
			(event dispose:)
		)
		(event dispose:)
		(self dispose:)
	)

	(method (handleEvent event &tmp ptr theItem [str 500] [str2 5]
												num 
												[prop1 30] [prop2 30] [prop3 30]
												[prop4 30] [prop5 30] [prop6 20]
												[prop7 30] [prop8 30] [prop9 20]
												[prop10 30] [propTitle 30])
		(switch (event type?)
			(nullEvt
			)

			(mouseDown
				(= theItem (self firstTrue: #check event))
				(if theItem
					(event claimed: TRUE)
					(if (== (event modifiers?) shiftDown)
						(theItem handleEvent: event)
					else
						(if curItem
							(curItem select: FALSE)
						)
						((= curItem theItem) select: TRUE)
						(self changeState: Main)
					)
				)
			)

			(mouseUp
			)

			(keyDown
				; map some synonyms
				(switch (event message?)
					(`?
						(event message: `h)
					)
					(`^s
						(event message: `x)
					)
				)

				(switch (event message?)
					(TAB
						(curItem select: FALSE)
						(= ptr (DialogEditor indexOf: curItem))
						(if (== (++ ptr) (DialogEditor size?))
							(= curItem (DialogEditor at: 0))
						else
							(= curItem (DialogEditor at: ptr))
						)
						(curItem select: TRUE)
						(DialogEditor changeState: Main)
					)
					(SHIFTTAB
						(curItem select: FALSE)
						(= ptr (DialogEditor indexOf: curItem))
						(if (== ptr 0)
							(= curItem (DialogEditor at: (- (DialogEditor size?) 1)))
						else
							(= curItem (DialogEditor at: (-- ptr)))
						)
					)
					(SPACEBAR
					)
					(BACKSPACE
					)
					(`a
						;
						; About (main)
						;
						(Print
							addTitle:	@winTitle,
							mode:			teJustCenter,
							width:		140,
							addText:		{by Brian K. Hughes\n
											17 July, 1992},
							init:
						)
					)
					(`c
						;
						; Cel (icon)
						; Create (window)
						;
						(switch state
							(EditIcon
								(curItem editCel:)
							)
							(EditWindow
								(if size
									(DlgWindow create:)
								else
									(Prints {Can't create window: no items!})
								)
							)
						)
					)
					(`d
						;
						; Delete (main)
						; Delete (window)
						;
						(switch state
							(Main
								(self delItem:)
							)
							(EditWindow
								(if windowUp
									(DlgWindow dispose:)
									(self eachElementDo: #draw)
								else
									(Prints {No window to delete!})
								)
							)
						)
					)
					(`e
						;
						; Edit menu (main)
						;
						(if size
							(self changeState:
								(switch (curItem -super-?)
									(_DButton		EditButton)
									(_DText			EditText)
									(_DEdit			EditEdit)
									(_DIcon			EditIcon)
									(_DSelector		EditSelector)
								)
							)
						else
							(Prints {No items to edit!})
						)
					)
					(`f
						;
						; Font (button, text, edit)
						;
						(if (OneOf state EditText EditButton EditEdit)
							(curItem editFont:)
						)
					)
					(`h
						;
						; Help
						;
						(switch state
							(Main
								(Print
									font:		userFont,
									width:	250,
									addText:
										{Main Menu:\n\n
									 	__About - About the DialogEditor\n
									 	__Item  - Create a new item\n
									 	__Edit	- Edit properties of the current item\n
									 	__Look  - View properties of the current item\n
									 	__Del   - Delete the current item\n
									 	__Win   - Create a window background\n
									 	__Help  - You're here!\n
									 	__eXit  - Exit the DialogEditor (& maybe save)\n},
									init:
								)
							)
							(EditWindow
								(Print
									font:		userFont,
									width:	250,
									addText:
										{Window Menu:\n\n
										__Create   - Draw the window to the correct size\n
										__Position - Move the window and all items\n
										__Delete   - Remove the window\n
										__Menu     - Return to the Main Menu},
									init:
								)
							)
							(else
								(curItem showHelp:)
							)
						)
					)
					(`i
						;
						; Item
						;
						(= theItem 0)
						(switch
							(Print
								addTitle:	@winTitle,
								width:		65,
								addText:		{Select the kind of item you want
													to add to the dialog:},
								font:			0,
								addButton:	1	{__Button__} 		80 0,
								addButton:	2	{___Text___} 		80 14,
								addButton:	3	{___Edit___} 		80 28,
								addButton:	4	{___Icon___} 		80 42,
								addButton:	6	{_Selector_} 		80 56,
								addButton:	0	{__Cancel__} 		80 70,
								init:
							)
							(dButton
								(= ptr (Memory MNeedPtr 50))
								(StrCpy ptr {button})
							  	(= theItem
							  		((_DButton new:)
							  			text:			ptr,
										yourself:
							  		)
							  	)
							)
							(dText
								(= ptr (Memory MNeedPtr 100))
								(StrCpy ptr {text})
								(= theItem
									((_DText new:)
										text:			ptr,
										yourself:
									)
								)
							)
							(dEdit
								(= ptr (Memory MNeedPtr 50))
								(StrCpy ptr {})
								(= theItem
									((_DEdit new:)
										text:			ptr,
										max:			5,
										yourself:
									)
								)
							)
							(dIcon
								(= theItem
									((_DIcon new:)
										view:			0,
										loop:			0,
										cel:			0,
										yourself:
									)
								)
							)
							(dSelector
								(= theItem
									((_DSelector new:)
										x:				10,
										y:				1,
										yourself:
									)
								)
							)
						)
						(if theItem
							(if curItem
								(curItem select: FALSE)
							)
							(= curItem theItem)
							(if windowUp
								(DlgWindow dispose:)
							)
							(self
								addToEnd:
									(curItem
										setSize:		,
										moveTo:		MARGIN MARGIN,
										yourself:
									),
								eachElementDo: #draw
							)
							(curItem select: TRUE)
						)
					)
					(`j
						;
						; Just (text)
						;
						(if (== state EditText)
							(curItem editJust:)
						)
					)
					(`l
						;
						; Length (edit, selector)
						; Look (main)
						;
						(switch state
							(Main
								(if curItem
									(Format @prop1 {__x:_______%d} (curItem nsLeft?))
									(Format @prop2 {__y:_______%d} (curItem nsTop?))
									(switch (curItem -super-?)
										(_DText
											(StrCpy @propTitle {Text Item})
											(StrCpy @str (curItem text?))
											(Format @prop3 {__text:___%s} (TerminateString @str 15))
											(Format @prop4 {__font:___%d} (curItem font?))
											(Format @prop5 {__width:__%d} (curItem width?))
											(Format @prop6 {__noun:___%d} (curItem noun?))
											(Format @prop7 {__verb:___%d} (curItem verb?))
											(Format @prop8 {__case:___%d} (curItem case?))
											(Format @prop9 {__seq:____%d} (curItem seq?))
											(Format @prop10 {__modNum:_%d} (curItem modNum?))
										)
										(_DButton
											(StrCpy @propTitle {Button Item})
											(StrCpy @str (curItem text?))
											(Format @prop3 {__text:___%s} (TerminateString @str 15))
											(Format @prop4 {__font:___%d} (curItem font?))
											(Format @prop5 {__value:__%d} (curItem value?))
											(Format @prop6 {__noun:___%d} (curItem noun?))
											(Format @prop7 {__verb:___%d} (curItem verb?))
											(Format @prop8 {__case:___%d} (curItem case?))
											(Format @prop9 {__seq:____%d} (curItem seq?))
											(Format @prop10 {__modNum:_%d} (curItem modNum?))
										)
										(_DIcon
											(StrCpy @propTitle {Icon Item})
											(Format @prop3 {__view:___%d} (curItem view?))
											(Format @prop4 {__loop:___%d} (curItem loop?))
											(Format @prop5 {__cel:____%d} (curItem cel?))
											(= prop6 (= prop7 (= prop8 (= prop9 (= prop10 0)))))
										)
										(_DEdit
											(StrCpy @propTitle {Edit Item})
											(StrCpy @str (curItem text?))
											(Format @prop3 {__text:___%s} (TerminateString @str 15))
											(Format @prop4 {__font:___%d} (curItem font?))
											(Format @prop5 {__max:____%d} (curItem max?))
											(= prop6 (= prop7 (= prop8 (= prop9 (= prop10 0)))))
										)
										(_DSelector
											(StrCpy @propTitle {Selector Item})
											(Format @prop3 {__width:__%d} (curItem x?))
											(Format @prop4 {__length:_%d} (curItem y?))
											(= prop5 (= prop6 (= prop7 (= prop8 (= prop9 (= prop10 0))))))
										)
									)
									(Print
										addTitle:	@propTitle,
										addText:		@prop1,
										addText:		@prop2 0 12,
										addText:		@prop3 0 24,
										addText:		@prop4 0 36,
										addText:		@prop5 0 48,
										addText:		@prop6 0 60,
										addText:		@prop7 0 72,
										addText:		@prop8 0 84,
										addText:		@prop9 0 96,
										addText:		@prop10 0 108,
										init:
									)
									(if windowUp
										(DlgWindow create:)
									)
								else
									(Prints {No item to look at!})
								)
							)
							(EditIcon
								(curItem editLoop:)
							)
							(EditEdit
								(curItem editLength:)
							)
							(EditSelector
								(curItem editLength:)
							)
						)
					)
					(`m
						;
						; Main menu (all subs)
						;
						(self changeState: Main)
					)
					(`p
						;
						; Position (all)
						;
						(switch state
							(EditWindow
								(DlgWindow editPosn:)
							)
							(else
								(curItem editPosn:)
							)
						)
					)
					(`t
						;
						; Text (button, text, edit)
						; Title (window)
						;
						(switch state
							(EditWindow
								(DlgWindow editTitle:)
							)
							(else
								(curItem editText:)
							)
						)
					)
					(`v
						;
						; View (icon)
						; Value (button)
						;

						(switch state
							(EditIcon
								(curItem editView:)
							)
							(EditButton
								(curItem editValue:)
							)
						)
					)
					(`w
						;
						; Window (main)
						; Width (text, selector)
						;

						(switch state
							(Main
								(self changeState: EditWindow)
							)
							(EditSelector
								(curItem editWidth:)
							)
							(EditText
								(curItem editWidth:)
							)
						)
					)
					(`x
						;
						; Exit (main)
						;
						(return (self exit:))
					)
				)
			)
		)

		(return 0)
	)

	(method (writeFile file &tmp i theItem [str 100] lastFont lastMode lastWidth)
		(= lastFont -1)
		(= lastMode teJustLeft)
		(= lastWidth 0)
		(for	((= i 0))
				(< i size)
				((++ i))
			(= theItem (self at: i))

			; Figure out which type of item this is and write the
			;	SCI code line to our file, including all correct
			;	parameters

			(switch (theItem -super-?)

				(_DButton
					; Write font if different from last used font
					(if (!= (theItem font?) lastFont)
						(= lastFont (theItem font?))
						(Format @str {\t\t\tfont:\t\t\t%d,\r}
							lastFont
						)
						(file writeString: @str)
					)

					(if (theItem seq?)
						; Write line with message parameters
						(Format @str {\t\t\taddButton:\t%d %d %d %d %d %d %d %d, \r}
							(theItem value?)
							(theItem noun?)
							(theItem verb?)
							(theItem case?)
							(theItem seq?)
							(- (- (theItem nsLeft?) (DlgWindow left?)) MARGIN)
							(- (- (theItem nsTop?) (DlgWindow top?)) MARGIN)
							(theItem modNum?)
						)
					else
						; Write line with string pointer
						(Format @str {\t\t\taddButton:\t%d {%s\} %d %d,\r}
							(theItem value?)
							(theItem text?)
							(- (- (theItem nsLeft?) (DlgWindow left?)) MARGIN)
							(- (- (theItem nsTop?) (DlgWindow top?)) MARGIN)
						)
					)
					(file writeString: @str)
				)

				(_DText
					; Write font if different from last used font
					(if (!= (theItem font?) lastFont)
						(= lastFont (theItem font?))
						(Format @str {\t\t\tfont:\t\t\t%d,\r}
							lastFont
						)
						(file writeString: @str)
					)

					; Write justification mode if not teJustLeft (default), unless
					;	last mode was not teJustLeft
					(if (!= (theItem mode?) lastMode)
						(= lastMode (theItem mode?))
						(Format @str {\t\t\tmode:\t\t\t%s,\r}
							(switch (theItem mode?)
								(teJustLeft		{teJustLeft})
								(teJustRight	{teJustRight})
								(teJustCenter	{teJustCenter})
							)
						)
						(file writeString: @str)
					)

					; Write the width, if not 0 (default), unless last width was
					;	not 0
					(if (!= (theItem width?) lastWidth)
						(= lastWidth (theItem width?))
						(Format @str {\t\t\twidth:\t\t%d, \r}
							lastWidth
						)
						(file writeString: @str)
					)

					(if (theItem seq?)
						; Write line with message parameters
						(Format @str {\t\t\taddText:\t\t%d %d %d %d %d %d %d, \r}
							(theItem noun?)
							(theItem verb?)
							(theItem case?)
							(theItem seq?)
							(- (- (theItem nsLeft?) (DlgWindow left?)) MARGIN)
							(- (- (theItem nsTop?) (DlgWindow top?)) MARGIN)
							(theItem modNum?)
						)
					else
						; Write line with string pointer
						(Format @str {\t\t\taddText:\t\t\{%s\} %d %d,\r}
							(theItem text?)
							(- (- (theItem nsLeft?) (DlgWindow left?)) MARGIN)
							(- (- (theItem nsTop?) (DlgWindow top?)) MARGIN)
						)
					)
					(file writeString: @str)
				)

				(_DEdit
					; Write font if different from last used font
					(if (!= (theItem font?) lastFont)
						(= lastFont (theItem font?))
						(Format @str {\t\t\tfont:\t\t\t%d,\r}
							lastFont
						)
						(file writeString: @str)
					)
					; Write message line
					(Format @str {\t\t\taddEdit:\t\t@str %d %d %d \{%s\},\r}
						(theItem max?)
						(- (- (theItem nsLeft?) (DlgWindow left?)) MARGIN)
						(- (- (theItem nsTop?) (DlgWindow top?)) MARGIN)
						(theItem text?)
					)
					(file writeString: @str)
				)

				(_DIcon
					; Write message line
					(Format @str {\t\t\taddIcon:\t\t%d %d %d %d %d,\r}
						(theItem view?)
						(theItem loop?)
						(theItem cel?)
						(- (- (theItem nsLeft?) (DlgWindow left?)) MARGIN)
						(- (- (theItem nsTop?) (DlgWindow top?)) MARGIN)
					)
					(file writeString: @str)
				)
				(_DSelector
				)
			)
		)
	)
)



;ษออออออออออออป
;บ            บ
;บ Procedures บ
;บ            บ
;ศออออออออออออผ

(procedure (TerminateString ptr len)
	(if (> (StrLen ptr) len)
		(StrAt ptr len NULL)
		(StrAt ptr (-- len) `.)
		(StrAt ptr (-- len) `.)
		(StrAt ptr (-- len) `.)
	)
	(return ptr)
)

(procedure (GetMsgParms theItem &tmp n v c s m
												[nounStr 10] [verbStr 10] [caseStr 10] [seqStr 10] [modStr 10]
												num msgLen)
	(= n (theItem noun?))
	(= v (theItem verb?))
	(= c (theItem case?))
	(= s (theItem seq?))
	(= m (theItem modNum?))
	(Format @nounStr {%d} n)
	(Format @verbStr {%d} v)
	(Format @caseStr {%d} c)
	(Format @seqStr {%d} s)
	(Format @modStr {%d} m)
	(if
		(Print
			addTitle:	@winTitle,
			font:			SYSFONT,
			addText:		{Enter new message parameters:},
			addText:		{Noun} 	5 25,
			addText:		{Verb} 	85 25,
			addText:		{Case} 	5 39,
			addText:		{Seq} 	85 39,
			addText:		{Module} 47 53,
			addEdit:		@nounStr 4 45 25 	@nounStr,
			addEdit:		@verbStr 4 125 25	@verbStr,
			addEdit:		@caseStr 4 45 39 	@caseStr,
			addEdit:		@seqStr 	4 125 39	@seqStr,
			addEdit:		@modStr 	5 101 53	@modStr,
			addButton:	1 {___OK___} 	18 67,
			addButton:	0 {Cancel} 		91 67,
			init:
		)
		(= n (ReadNumber @nounStr))
		(= v (ReadNumber @verbStr))
		(= c (ReadNumber @caseStr))
		(= s (ReadNumber @seqStr))
		(= m (ReadNumber @modStr))

		(cond
			((not (Message MsgGet m n v c s))
				(Prints {Can't find message!})
				(return FALSE)
			)
			((not (Message MsgSize m n v c s))
				(Prints {Message contains no text!})
				(return FALSE)
			)
			(else
				(theItem
					noun:		n,
					verb:		v,
					case:		c,
					seq:		s,
					modNum:	m
				)
				(return TRUE)
			)
		)
	else
		(return FALSE)
	)
)

(procedure (GetTextType theItem)
	(return
		(Print
			addTitle:	@winTitle,
			font:			SYSFONT,
			width:		50,
			addText:		{What kind of text?},
			addButton:	1 {_Literal_} 	60 0,
			addButton:	2 {MSG file} 	60 14,
			addButton:	0 {__Cancel__}	60 28,
			first:		(if (theItem seq?) 2 else 1),
			init:
		)
	)
)
