;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	PRINT.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author:	Brian K. Hughes
;;;;	Updated:
;;;;		Brian K. Hughes
;;;;		August 10, 1992
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
(use Intrface)
(use Dialog)
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
				font:		(if (> argc 3) theFont else userFont),
				addText:	(if (and (> argc 2) prompt) prompt else {}),
				addEdit: str maxLen 0 12 str,
				init:
			)
			(StrLen str)
		)
	)
)

(procedure (FindFormatLen ctrlString args &tmp finalLen len parmCount i)
	(= finalLen (= len (StrLen ctrlString)))
	(= parmCount 0)
	(for	((= i 0))
			(< i len)
			((++ i))
		(if (== (StrAt ctrlString i) `%)
			(switch (StrAt ctrlString (++ i))
				(`d	;integer
					(+= finalLen 5)
				)
				(`x	;hex
					(+= finalLen 4)
				)
				(`s	;string
					(+= finalLen (StrLen [args parmCount]))
				)
			)
			(++ parmCount)
		)
	)
	(return (++ finalLen))
)




(class Print kindof Object
	(properties
		dialog	 	0					; pointer to dialog of which we are a member
		window	 	0					; pointer to window clone
		title			0					; title of window
		mode		 	teJustLeft		; justification mode
		font		 	-1					; print font
		width			0					; print width
		x			 	-1					;-position of top left of print box
		y			 	-1					;/
		ticks			0					; time until dispose
		caller		0					; who to cue when we're done
		retValue		NULL				; return value - calced by dialog
		modeless		FALSE				; TRUE if modeless dialog
		first			0					; default item to highlight first
		saveCursor	FALSE				; TRUE will set cursor back to curIcon's
	)
;;;	(methods
;;;		addButton		;Add a button
;;;		addEdit			;Add an edit field
;;;		addIcon			;Add an icon
;;;		addText			;Add text
;;;		addTextF			;Format & add text
;;;		addTitle			;Add a title to the window
;;;		posn				;Set x & y of dialog
;;;		handleEvent
;;;		cue
;;;	)

	(method (addButton val args &tmp noun verb case seq theX theY module theText s)
		;
		; Add a button with the specified text and at the specified offset
		;	Params are val noun [verb [case [sequence [x [y [module]]]]]]

		(if (not dialog)
			(= dialog (Dialog new:))
		)

		(if (== font -1)
			(= font userFont)
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

			(if (= s (Message MsgSize module noun verb case seq))
				(= theText (Memory MNeedPtr s))
				(if (not (Message MsgGet module noun verb case seq theText))
					(= theText 0)
				)
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

			(= theText (Memory MNeedPtr (+ (StrLen [args 0]) 1)))
			(StrCpy theText [args 0])
		)

		(if theText
			(dialog
				add:
	  				((DButton new:)
						value:		val,
						font:			font,
	  					text:			theText,
  						setSize: 	,
						moveTo:		(+ MARGIN theX) (+ MARGIN theY),
						yourself:
	  				),
				setSize:
			)
		)
	)

	(method (addEdit buf maxSize theX theY default &tmp eX eY)
		;
		; Add an edit field with a maximum size, and offset by theX theY

		(if (not dialog)
			(= dialog (Dialog new:))
		)

		(StrCpy buf (if (> argc 4) default else {}))

		(if (> argc 2)
			(= eX theX)
			(if (> argc 3)
				(= eY theY)
			)
		)

		(dialog
			add:
				((DEdit new:)
					text:			buf,
					max:			maxSize,
					setSize:		,
					moveTo:		(+ eX MARGIN) (+ eY MARGIN),
					yourself:
				),
			setSize:
		)
	)

	(method (addIcon theViewOrObj theLoop theCel theX theY &tmp iX iY)
		;
		; Add an icon of view, loop, cel, and offset by theX theY, or an object
		;	(like cycling icon) at theX theY

		(if (not dialog)
			(= dialog (Dialog new:))
		)
		(if (> argc 3)
			(= iX theX)
			(= iY theY)
		else
			(= iX (= iY 0))
		)
		(if (IsObject theViewOrObj)
			(dialog
				add:
					(theViewOrObj
						setSize:		,
						moveTo: 		(+ iX MARGIN) (+ iY MARGIN),
						yourself:
					),
				setSize:
			)
		else
			(dialog
				add:
					((DIcon new:)
						view:			theViewOrObj,
						loop:			theLoop,
						cel:			theCel,
						setSize:		,
						moveTo:		(+ iX MARGIN) (+ iY MARGIN),
						yourself:
					),
				setSize:
			)
		)
	)

	(method (addText args &tmp noun verb case seq theX theY module theText s)
		;
		; Add some text from either the message parameters or the buffer, at
		;	the offset specified by theX theY

		(if (not dialog)
			(= dialog (Dialog new:))
		)

		(if (== font -1)
			(= font userFont)
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

			(if (= s (Message MsgSize module noun verb case seq))
				(= theText (Memory MNeedPtr s))
				(if (Message MsgGet module noun verb case seq theText)
					(dialog
						add:
	  						((DText new:)
	  							text:			theText,
	  							font: 		font,
	  							mode: 		mode,
	  							setSize: 	width,
								moveTo:		(+ MARGIN theX) (+ MARGIN theY),
								yourself:
	  						),
						setSize:
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

			(= theText (Memory MNeedPtr (+ (StrLen [args 0]) 1)))
			(StrCpy theText [args 0])
			(dialog
				add:
	  				((DText new:)
	  					text:			theText,
						font:			font,
						mode:			mode,
  						setSize: 	width,
						moveTo:		(+ MARGIN theX) (+ MARGIN theY),
						yourself:
	  				),
				setSize:
			)
		)
	)

	(method (addTextF &tmp len buffer)
		;
		; Format a string and its parameters into the passed buffer, then
		;	add the text to the dialog

		(= len (FindFormatLen &rest))
		(= buffer (Memory MNeedPtr len))
		(Format buffer &rest)
		(self addText: buffer)
		(Memory MDisposePtr buffer)
	)

	(method (addTitle args &tmp noun verb case seq module s)
		;
		; Use the buffer/string passed as a title on the window
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

			(if (= s (Message MsgSize module noun verb case seq))
				(= title (Memory MNeedPtr s))
				(Message MsgGet module noun verb case seq title)
			)
		else
			; Case b), just a buffer
			(= title (Memory MNeedPtr (+ (StrLen [args 0]) 1)))
			(StrCpy title [args 0])
		)
	)

	(method (dispose)
		(if (and	prints
					(prints contains: self)
				)
			(prints delete: self)
			(if (prints isEmpty?)
				(prints dispose:)
				(= prints 0)
			)
		)

		(if title
			(Memory MDisposePtr title)
		)
		(= title (= first (= saveCursor (= window 0))))
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
		(if window
			(window dispose:)
		)
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

		; Add ourselves to the prints list if we're modal
		(if (not modeless)
			(if (not (IsObject prints))
				(= prints ((EventHandler new:) name: {prints}))
			)
			(prints add: self)
		)

		(self showSelf:)
	)

	(method (doit)
		(dialog eachElementDo: #doit)
	)

	(method (handleEvent event)
		;
		; If the dialog gets an event, kill it - this will cue us

		(if (dialog handleEvent: event)
			(dialog dispose:)
		)
	)

	(method (showSelf &tmp default curPort i theX theY)
		;
		; Create the dialog & set it up

		(if saveCursor
			(theGame setCursor: ARROW_CURSOR)
		)

		(if (not dialog)
			(= dialog (Dialog new:))
		)
	  	(dialog
	  		window:		(if window window else systemWindow),
	  		name:			{PODialog},
			caller:		self
	  	)

		; Set up the dialog time, size, and position
	  	(dialog
	  		text:		title,
	  		time:		ticks,
	  		setSize:
		)
		(dialog center:)
		(= theX (if (== x -1) (dialog nsLeft?) else x))
		(= theY (if (== y -1) (dialog nsTop?) else y))
		(dialog moveTo: theX theY)

		; Save the current port & open the dialog
		(= curPort (GetPort))
		(dialog open: (if title wTitled else 0) 15)

		; If Print is modeless, treat the dialog as if it's modeless
		(if modeless
			(= modelessPort (GetPort))
			(SetPort curPort)
			(= modelessDialog dialog)
		else
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
				(if (not (IsObject default))
					(= default (dialog at: default))
				)
			)

			(= retValue (dialog doit: default))

			(SetPort curPort)

			(cond

				; If ESC has been pressed
				((== retValue -1)
					(= retValue FALSE)
				)

				; If anyof the buttons was pressed
				((and	(IsObject retValue)
						(retValue isKindOf: DButton)
					)
					(= retValue (retValue value?))
				)

				; If there were no active items
				((not (dialog theItem?))
					(= retValue TRUE)
				)
			)

			; dispose of the dialog and all its elements - will cue us
			(if saveCursor
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
			)
			(dialog dispose:)
			(return retValue)
		)
	)
)
