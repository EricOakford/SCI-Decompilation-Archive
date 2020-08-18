;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	PRINT.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author:	Brian K. Hughes
;;;;	Updated: Brian K. Hughes
;;;;
;;;;	This class is a front end for dialogs.  It replaces the Print, Printf,
;;;;	and PrintD procedures.  The Print and Printf procedure still survives
;;;;	(in a primitive form) as Prints and Printf.
;;;;
;;;;	Classes:
;;;;		Print
;;;;
;;;;	Procedures:
;;;;		Prints
;;;;		Printf
;;;;		GetInput
;;;;		FindFormatLen


(script# PRINT)
(include game.sh)
(use Main)
(use DButton)
(use DIcon)
(use DSelector)
(use DEdit)
(use DText)
(use String)
(use Dialog)
(use FileSel)
(use System)

;;;(procedure
;;;	Prints
;;;	Printf
;;;	GetInput
;;;	FindFormatLen
;;;)

(public
	Prints			0
	Printf			1
	GetInput			2
	FindFormatLen	3
)

;NA	(define A_RECT 	1)
;NA	(define A_BM		5)
;NA	(define A_STOP		6)
;NA	(define A_FORE		8)
;NA	(define A_FONT		9)
;NA	(define A_RECTNUM	10)


(procedure (Prints &tmp p)
	(= p (Print new:))
	(p
		addText:	&rest,
		init:
	)
)

(procedure (Printf &tmp p)
	(= p (Print new:))
	(p
		addTextF:	&rest,
		init:
	)
)

(procedure (GetInput str maxLen prompt theFont)
	(return
		(and
			((Print new:)
				font:		(= inputFont (if (> argc 3) theFont else userFont)),
				addText:	(if (and (> argc 2) prompt) prompt else {}),
				addEdit: str maxLen 0 12 str,
				init:
			)
			(str size:)
		)
	)
)

(procedure (FindFormatLen ctrlString args &tmp finalLen len parmCount i str argStr)
	(= str (String newWith: (String StrLen (String StrGetData ctrlString)) {}))
	(str copy: ctrlString)
	(= finalLen (= len (str size:)))
	(= parmCount 0)
	(for	((= i 0))
			(< i len)
			((++ i))
		(if (== (str at: i) `%)
			(switch (str at: (++ i))
				(`d	;integer
					(+= finalLen 5)
				)
				(`x	;hex
					(+= finalLen 4)
				)
				(`s	;string
					(= argStr (String with: [args parmCount]))
					(+= finalLen (argStr size:))
					(argStr dispose:)
				)
			)
			(++ parmCount)
		)
	)
	(str dispose:)
	(return (++ finalLen))
)




(class Print kindof Object
	(properties
		dialog	 	0					; pointer to dialog of which we are a member
		plane		 	0					; pointer to plane clone
		title			0					; title of plane
		mode		 	teJustLeft		; justification mode
		font		 	-1					; print font
		width			0					; print width
		fore			255				; default foreground color for all items
		back			-1					; default background color for all items
		skip			-1					; default skip color for all items
											;	(-1 indicates use item class's skip)

		x			 	-1					;-position of top left of print box
		y			 	-1					;/
		margin		MARGIN			; pixel margin around items in plane
		ticks			0					; time until dispose
		caller		0					; who to cue when we're done
		retValue		NULL				; return value - calced by dialog
		modeless		DLG_MODAL		; DLG_MODAL, DLG_SEMI_MODAL, or DLG_MODELESS
		first			0					; default item to highlight first
		saveCursor	FALSE				; TRUE will set cursor back to curIcon's
		bitmap		FALSE				; ID of DIcon used as bitmap background
		onScreen		TRUE				; if should keep on screen

		classButton	0					; button	class to use (default is DButton)
		classEdit	0					; edit class to use (default is DEdit)
		classIcon	0					; icon class to use (default is DIcon)
		classText	0					; text class to use (default is DText)
	)
;;;	(methods
;;;		addBitmap		;Add a bitmap as the background
;;;		addButton		;Add a button
;;;		addButtonBM		;Add a button with a bitmap
;;;		addEdit			;Add an edit field
;;;		addFSelector	;Add a file selector
;;;		addIcon			;Add an icon
;;;		addSelector		;Add a selector
;;;		addSlider		;Add a slider
;;;		addText			;Add text
;;;		addTextBM		;Add text with a bitmap
;;;		addTextF			;Format & add text
;;;		addTitle			;Add a title to the plane
;;;		posn				;Set x & y of dialog
;;;		handleEvent
;;;		cue
;;;		showSelf
;;;		isModeless		;Return TRUE of modeless state matches arg
;;;	)


	; All Print methods that take a string as a parameter (except addEdit) will
	;	accept either a string object or a string's data.  The dialog items
	;	accept only string data.


	(method (addBitmap v l c)
		;
		; Add a bitmap to the plane as a background
		;
		; Returns the ID of the new bitmap

		(if (not dialog)
			(= dialog (Dialog new:))
		)

		(= bitmap (DIcon new:))
		(dialog add:
			(bitmap
				view:			v,
				loop:			l,
				cel:			c,
				setSize:		,
				moveTo:		0 0,
				yourself:
			)
		)
		(= width (- (CelWide v l c) (* margin 2)))

		(return bitmap)
	)

	(method (addButton val args
							&tmp noun verb case seq theX theY module theText ret buttonClass)
		;
		; Add a button with the specified text and at the specified offset
		;	Params are val noun [verb [case [sequence [x [y [module]]]]]]
		;
		; Returns the ID of the new DButton

		(if (not dialog)
			(= dialog (Dialog new:))
		)

		(if (== font -1)
			(= font userFont)
		)

		; Figure out which button we're going to use
		(= buttonClass
			(if classButton
				classButton
			else
				DButton
			)
		)

		; If we have at least five args (val, noun, verb, case, sequence) then we
		;	might also have an x, y, and module

		(if (> argc 4)
			(= noun 		[args 0])
			(= verb 		[args 1])
			(= case 		[args 2])
			(= seq		(if [args 3] [args 3] else 1))
			(= theX 		0)
			(= theY 		0)
			(= module 	curRoomNum)

			(if (> argc 5)
				(= theX [args 4])
				(if (> argc 6)
					(= theY [args 5])
					(if (> argc 7)
						(= module [args 6])
					)
				)
			)

			(= theText (String new:))

			; If no message or message is 0 bytes, dump out
			(if (or	(not (Message MsgSize module noun verb case seq))
						(not (Message MsgGet module noun verb case seq (theText data?)))
				)
				(theText dispose:)
				(return 0)
			)

			(dialog
				add:
					(= ret
	  					((buttonClass new:)
							value:		val,
							font:			font,
							fore:			fore,
							back:			(if (== back -1) (buttonClass back?) else back),
							skip:			(if (== skip -1) (buttonClass skip?) else skip),
	  						text:			(theText data?),
  							setSize: 	,
							moveTo:		(+ margin theX) (+ margin theY),
							yourself:
	  					)
					)
			)

			; We have transferred the text to our DButton item, so now get
			;	rid of the string (without getting rid of the text)
			(theText
				data:		0,
				dispose:
			)
			

		; Else we have at least two arguments (val, buffer) and maybe an x and y

		else
			(= theX		0)
			(= theY		0)

			(if (> argc 2)
				(= theX [args 1])
				(if (> argc 3)
					(= theY [args 2])
				)
			)

			; Clone the text passed so we can do whatever we want
			(= theText (String StrDup (String StrGetData [args 0])))

			(dialog
				add:
					(= ret
	  					((buttonClass new:)
							value:		val,
							font:			font,
							fore:			fore,
							back:			(if (== back -1) (buttonClass back?) else back),
							skip:			(if (== skip -1) (buttonClass skip?) else skip),
	  						text:			theText,
  							setSize: 	,
							moveTo:		(+ margin theX) (+ margin theY),
							yourself:
	  					)
					)
			)
		)

		(return ret)
	)

	(method (addButtonBM v l c &tmp theButton)
		;
		; Add a button with a bitmap.  Calls addButton:.

		(= theButton (self addButton: &rest))
		(if (!= v -1)
			(theButton
				view:		v,
				loop:		l,
				cel: 		c,
				setSize:
			)
		else
			(theButton
				view:		SAVE,
				loop:		0,
				cel:		0,
				setSize:
			)
		)

		(return theButton)
	)

	(method (addEdit buf maxSize theX theY default &tmp eX eY ret editClass)
		;
		; Add an edit field with a maximum size, and offset by theX theY
		; Note! 'buf' will always be a string object.
		;
		; Returns the ID of the new DEdit

		(if (not dialog)
			(= dialog (Dialog new:))
		)

		(if (== font -1)
			(= font inputFont)
		)

		; Figure out which button we're going to use
		(= editClass
			(if classEdit
				classEdit
			else
				DEdit
			)
		)

		(if (> argc 4)
			(buf copy: default)
		)

		(if (> argc 2)
			(= eX theX)
			(if (> argc 3)
				(= eY theY)
			)
		)

		(dialog
			add:
				(= ret
					((editClass new:)
						text:			(buf data?),
						width:		maxSize,
						fore:			fore,
						font:			font,
						back:			(if (== back -1) (editClass back?) else back),
						skip:			(if (== skip -1) (editClass skip?) else skip),
						setSize:		,
						moveTo:		(+ eX margin) (+ eY margin),
						yourself:
					)
				)
		)

		(return ret)
	)

	(method (addFSelector theX theY maxLen theMask &tmp ret d)
		;
		; Add a file selector object to the dialog.
		;
		; Returns the ID of the new FileSelector.

		(if (not dialog)
			(= dialog (Dialog new:))
		)

		(if (== font -1)
			(= font userFont)
		)

		((= d (FileSelector new:))
			font:	font
		)
		(dialog add:
			(= ret
				(d
					setText:		,
					mask:			(if (> argc 3)
										(String StrDup (String StrGetData theMask))
									else
										(String StrDup {*.*})
									),
					sort:			FALSE,
					length:		(if (!= maxLen -1) maxLen else 0),
					setSize:		,
					moveTo:		(+ theX margin) (+ theY margin),
					yourself:
				)
			)
		)

		(return ret)
	)

	(method (addIcon theViewOrObj theLoop theCel theX theY &tmp iX iY ret iconClass)
		;
		; Add an icon of view, loop, cel, and offset by theX theY, or an object
		;	(like cycling icon) at theX theY.
		; Note that if an object is passed, theLoop and theCel must be -1
		;
		; Returns the ID of the new DIcon

		(if (not dialog)
			(= dialog (Dialog new:))
		)
		(if (> argc 3)
			(= iX theX)
			(= iY theY)
		else
			(= iX (= iY 0))
		)

		; Figure out which icon we're going to use
		(= iconClass
			(if classIcon
				classIcon
			else
				DIcon
			)
		)

		(if (== theLoop -1)
			(dialog
				add:
					(= ret
						(theViewOrObj
							setSize:		,
							moveTo: 		(+ iX margin) (+ iY margin),
							yourself:
						)
					)
			)
		else
			(dialog
				add:
					(= ret
						((iconClass new:)
							view:			theViewOrObj,
							loop:			theLoop,
							cel:			theCel,
							setSize:		,
							moveTo:		(+ iX margin) (+ iY margin),
							yourself:
						)
				 	)
			)
		)

		(return ret)
	)

	(method (addSelector theX theY maxLen theText &tmp ret d)
		;
		; Add a selector object to the dialog.
		;
		; Returns the ID of the new DSelector.

		(if (not dialog)
			(= dialog (Dialog new:))
		)

		(if (== font -1)
			(= font userFont)
		)

		((= d (DSelector new:))
			font:	font
		)
		(dialog add:
			(= ret
				(d
					setText:		theText &rest,
					length:		(if (!= maxLen -1) maxLen else 0),
					setSize:		,
					moveTo:		(+ theX margin) (+ theY margin),
					yourself:
				)
			)
		)

		(return ret)
	)

	(method (addSlider v l c sv sl sc val tVal bVal theX theY ret)
		;
		; Add a slider object to the dialog.
		;
		; Returns the ID of the new DSlider

;¯gtp¯
;¯gtp¯	DSlider has not been converted to sci32
;¯gtp¯	This method will not work, so:
;¯gtp¯
			(return FALSE)
;¯gtp¯
;¯gtp¯		(if (not dialog)
;¯gtp¯			(= dialog (Dialog new:))
;¯gtp¯		)
;¯gtp¯
;¯gtp¯		(dialog add:
;¯gtp¯			(= ret
;¯gtp¯				((DSlider new:)
;¯gtp¯					view:				v,
;¯gtp¯					loop:				l,
;¯gtp¯					cel:				c,
;¯gtp¯					sliderView:		sv,
;¯gtp¯					sliderLoop:		sl,
;¯gtp¯					sliderCel:		sc,
;¯gtp¯					value:			val,
;¯gtp¯					topValue:		tVal,
;¯gtp¯					bottomValue:	bVal,
;¯gtp¯					posn:				theX theY,
;¯gtp¯					yourself:
;¯gtp¯				)
;¯gtp¯		  	)
;¯gtp¯		)
;¯gtp¯
;¯gtp¯		(return ret)
	)

	(method (addText args
						&tmp noun verb case seq theX theY module
								theText theArray ret t textClass)
		;
		; Add some text from either the message parameters or the buffer, at
		;	the offset specified by theX theY
		;
		; Returns the ID of the new DText

		(if (not dialog)
			(= dialog (Dialog new:))
		)

		(if (== font -1)
			(= font userFont)
		)

		; Figure out which text class we're going to use
		(= textClass
			(if classText
				classText
			else
				DText
			)
		)

		; If we have at least four args (noun, verb, case, sequence) then we
		;	might also have an x, y, and module

		(if (> argc 3)
			(= noun 		[args 0])
			(= verb 		[args 1])
			(= case 		[args 2])
			(= seq		(if [args 3] [args 3] else 1))
			(= theX 		0)
			(= theY 		0)
			(= module 	curRoomNum)

			(if (>= argc 5)
				(= theX [args 4])
				(if (>= argc 6)
					(= theY [args 5])
					(if (>= argc 7)
						(= module [args 6])
					)
				)
			)

			(= theText (String new:))

			; If no message or message is 0 bytes, dump out
			(if (or 	(not (Message MsgSize module noun verb case seq))
						(not (Message MsgGet module noun verb case seq (theText data?)))
				)
				(return 0)
			)

			(dialog
				add:
					(= ret
 						((textClass new:)
							text:			(theText data?),
 							font: 		font,
 							mode: 		mode,
							fore:			fore,
							back:			(if (== back -1) (textClass back?) else back),
							skip:			(if (== skip -1) (textClass skip?) else skip),
 							setSize: 	width,
							moveTo:		(+ margin theX) (+ margin theY),
							yourself:
 						)
					)
			)

		; Else we have at least one argument (the buffer) and maybe an x and y

		else
			(= theX		0)
			(= theY		0)

			(if (>= argc 2)
				(= theX [args 1])
				(if (>= argc 3)
					(= theY [args 2])
				)
			)

			; Clone the text passed so we can do whatever we want
			(= theText (String new:))
			(theText copy: (String StrGetData [args 0]))

			(dialog
				add:
					(= ret
  						((textClass new:)
  							text:			(theText data?),
							font:			font,
							mode:			mode,
							fore:			fore,
							back:			(if (== back -1) (textClass back?) else back),
							skip:			(if (== skip -1) (textClass skip?) else skip),
							setSize: 	width,
							moveTo:		(+ margin theX) (+ margin theY),
							yourself:
  						)
					)
			)
		)

		; We have transferred the text to our DText item, so now get
		;	rid of the string (without getting rid of the text)
		(theText
			data:		0,
			dispose:
		)

;NA					(= theArray (IntArray new:))
;NA					(theArray data:
;NA						(ParseText (theText data?), width, font, fore, back)
;NA					)
;NA					(for	((= idx 0))
;NA							(theArray at: idx)
;NA							((+= idx 11)
;NA						(dialog
;NA							add:
;NA								((DText new:)
;NA									text:			(theArray at: idx),
;NA									font:			(theArray at: (+ idx A_FONT)),
;NA									fore:			(theArray at: (+ idx A_FORE)),
;NA									back:			(theArray at: (+ idx A_BACK)),
;NA									image:		(theArray at: (+ idx A_BM)),
;NA									mode:			mode,
;NA									setSize:		width,
;NA									moveTo:		(+ margin theX) (+ margin theY),
;NA									yourself:
;NA								)
;NA						)
;NA					)
;NA					(theArray dispose:)

		(return ret)
	)

	(method (addTextBM v l c &tmp theText)
		;
		; Add text with a bitmap.  Calls addText:.

		(= theText (self addText: &rest))
		(if (!= v -1)
			(theText
				view:		v,
				loop:		l,
				cel: 		c,
				setSize:
			)
		else
			(theText
				view:		1234,	;DEBUG!
				loop:		2,
				cel:		0,
				setSize:
			)
		)

	)

	(method (addTextF &tmp len buffer)
		;
		; Format a string and its parameters into the passed buffer, then
		;	add the text to the dialog

		(= len (FindFormatLen &rest))
		(= buffer (String newWith: len {}))
		(buffer format: &rest)
		(self addText: (buffer data?))
		(buffer dispose:)
	)

	(method (addTitle args &tmp noun verb case seq module theText)
		;
		; Use the buffer/string passed as a title on the plane
		; Parameters are:
		;		a) noun verb case sequence module
		;		b) buffer
		
		(if (> argc 1)
			; Case a), full complement of message params
			(= noun		[args 0])
			(= verb		[args 1])
			(= case		[args 2])
			(= seq		[args 3])
			(= module	[args 4])

			(= theText (String new:))
			; If no message or message is 0 bytes, dump out
			(if (or	(not (Message MsgSize module noun verb case seq))
						(not (Message MsgGet module noun verb case seq (theText data?)))
				)
				(theText dispose:)
				(return 0)
			)
			(= title (String StrDup (theText data?)))
			(theText
				data:		0,
				dispose:
			)
		else
			; Case b), just a buffer

			(= title (String StrDup (String StrGetData [args 0])))
		)
	)

	(method (dispose)
		(if (prints contains: self)
			(prints delete: self)
		)

		; Get rid of the plane
		(plane dispose:)

		(= ticks (= title (= first (= saveCursor (= plane (= bitmap 0))))))
		(= mode teJustLeft)
		(= width 0)
		(= x (= y -1))
		(= modeless FALSE)
		(sounds pause: FALSE)
		(super dispose:)
	)

	(method (cue &tmp theCaller)
		;
		; We've been cued by dialog disposing - now cue our caller & dispose

		(= theCaller caller)
		(= dialog 0)
		(self dispose:)
		(if theCaller
			(theCaller cue:)
		)
	)

	(method (posn theX theY)
		(= x theX)
		(= y theY)
	)

	(method (init whoCares)
		;
		; Set ourselves up to display the message.  If we have any args beyond
		;	the first (optional caller), then we call addText: with them.

		(= caller 0)

		(if argc
			(= caller whoCares)
		)
		
		(if (> argc 1)
			(self addText: &rest)
		)

		(self showSelf:)
	)

	(method (doit)
		(dialog doit:)
	)

	(method (handleEvent event)
		;
		; If the dialog gets an event, kill it - this will cue us

		(if (dialog handleEvent: event)
			(dialog dispose:)
			(return TRUE)
		)
	)

	(method (showSelf &tmp default i theX theY p ret)
		;
		; Create the dialog & set it up

		(if saveCursor
			(theGame setCursor: normalCursor)
		)

		(if (not dialog)
			(= dialog (Dialog new:))
		)

		; Create the plane and set its background
		((= plane
			(if plane
				(plane new:)
			else
				(systemPlane new:)
			))
			back:	(if (== back -1) 0 else back)
		)

		; Set up the dialog
	  	(dialog
			plane:		plane,
	  		name:			{PODialog},
			caller:		self,
	  		text:			title,
	  		ticks:		ticks,
			margin:		margin,
			modeless:	modeless,
			onScreen: 	onScreen,
			init:
		)

		; If a bitmap background is being used, resize the plane to it
		;	Otherwise, size the plane to the minimum required by the items.
		(if bitmap
			(dialog
				nsLeft:		(bitmap nsLeft?),
				nsTop:		(bitmap nsTop?),
				nsRight:		(bitmap nsRight?),
				nsBottom:	(bitmap nsBottom?)
			)
		else
			(dialog setSize:)
		)

		; Do the title, if any
		(if title
			((dialog plane?) setTitle: title)
			(dialog
				nsLeft:		((dialog plane?) left?),
				nsTop:		((dialog plane?) top?),
				nsRight:		((dialog plane?) right?),
				nsBottom:	((dialog plane?) bottom?)
			)
		)

		; Center the dialog and then figure out if we want to move
		;	it somewhere else
		(dialog center:)
		(= theX (if (== x -1) (dialog nsLeft?) else x))
		(= theY (if (== y -1) (dialog nsTop?) else y))
		(dialog
			moveTo: 			theX theY,
			eachElementDo: #updatePlane:
		)

		;; To show the dialog now! (for opaque planes & modeless dialogs)
		(FrameOut)

		; If we're completely modal, lock the Dialog into its loop.  Otherwise,
		;	just add it to the prints list
		(if (== modeless DLG_MODAL)
			(sounds pause: TRUE)

			; If there's no first property, set the default to the first item in
			;	the dialog, & if it isn't an exit item make it so
			(if (not (= default first))
				(if (= default (dialog firstTrue: #checkState: dActive))
					(if (not (dialog firstTrue: #checkState: dExit))
						(default state: (| (default state?) dExit))
					)
				)
			else
			 	(= default (dialog at: default))
			)

			(= retValue (dialog doit: default))

			(switch retValue
				(RET_ESC
					(= retValue FALSE)
				)
				(RET_NO_ACTIVE
					(= retValue TRUE)
				)
				(RET_NO_EVENT
					(= retValue TRUE)
				)
				(else
					(if (retValue object?)
						(theGame
							panelObj:		(retValue object?),
							panelSelector:	(retValue selector?)
						)
					)
					(cond
						((retValue isKindOf: DEdit)
							(= retValue (retValue text?))
						)
						((retValue isKindOf: DSelector)
							(= retValue (retValue getText?))
						)
						(else
							(= retValue (retValue value?))
						)
					)
				)
			)

			; dispose of the dialog and all its elements - will cue us
			(if (and saveCursor theIconBar) ;-gtp in case no iconbar
				(theGame setCursor: (theIconBar getCursor:))
			)
			(= ret retValue)
			(dialog dispose:)
			(return ret)
		else
			;;; Then let prints handle it.
			(prints addToFront: self)
		)
	)

	(method (isModeless checkFor)
		;
		; If a parameter is passed, return whether that parameter matches
		;	our modeless state.  Else, return the modeless state, which will be:
		;		0 - Truly modal, no game execution until print disposed
		;		1 - Semi-modal, all prints in prints list execute
		;		2 - Truly modeless, game execution continues (if possible)

		(return
			(if argc
				(== modeless checkFor)
			else
				modeless
			)
		)
	)
)
