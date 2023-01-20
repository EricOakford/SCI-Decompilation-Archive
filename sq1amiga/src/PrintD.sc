;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	PRINTD.SC
;;;;	(c) Sierra On-Line, Inc, 1990
;;;;
;;;;	Author: Mark Wilden
;;;;
;;;;	Simplifies use of dialogs
;;;;

; Syntax:
;
;		PrintD [[[dialogItem] [dialogItemModifier]...]...] [dialogModifier]...
;
;  where dialogItem is one or more of:
;
;		[#text:]	nearTextPointer
;		#button:	nearTextPointer returnValue
;		#edit:	nearTextPointer maxLength
;		#icon:	view loop cel
;
;	dialogItemModifier is one or more of:
;		#x:	xOffset		which offset the position of the item in the box
;		#y:	yOffset
;
;	and dialogModifier is one or more of:
;
;		#title:
;		#at:		xCoord yCoord
;		#new:  
;		#first:	itemNum
;
;	Items are placed side by side.  A #new: parameter puts the following
;	items below the current item.  xOffset and yOffset are optional adjustments
;  to the default position of the item and may be positive or negative.
;	#text: in front of a text item is optional.  All text must be near strings.
;	#first: makes the itemNum'th dialogItem the default, counting from 0.
;
;	A icon will probably necessitate some #x: and/or #y: adjustments for the 
;	next line.
;
;	dialogModifiers apply to the entire dialog box.
;
;	Returns 0 if Esc pressed, 1 if Enter pressed, or the value of the button if
;	a button was selected.
;
; Sample usage:
;
;	(PrintD
;			#text:	{Where to, Hero?}	; #text: is optional
;			#edit:	@room 3	#x: 10	; max input length 3, move 10 to the right
;			#new:								; put the buttons on the next line
;			#button:	{Demo} 2				; will return 2 if selected
;			#button:	{Alley} 3			; will return 3 if selected
;			#button:	{Inn} 4				; will return 4 if selected
;			#new:
;			{When to, Hero?}
;			#new:
;			{Day} #y: -5					; move the text up 5 pixels
;			#edit:	@day 3
;			{Time}
;			#edit:	@theTime 3
;	)
(script# PRINTD)
(include game.sh)
(use Main)
(use Intrface)

;;;(procedure	PrintD)
(public		PrintD	0)

(procedure (PrintD	args
							&tmp arg theDialog theItem right bottom top left
							  	  ret type theObj xDialog yDialog
							  	  theTitle firstItem firstItemNum)

	(= xDialog (= yDialog -1))
	(= right (= bottom (= top (= left 0))))
	(= theTitle 0)
	(= firstItemNum 0)

	((= theDialog (Dialog new:))
		window: systemWindow
	)

	(for ((= arg 0)) (< arg argc) ((++ arg))
		(= type [args arg])
		(switch type
			; non dialog item arguments
			(#new:
				(= top (theItem nsBottom?))
				(= right 0)
			)
			(#at:
				(= xDialog [args (++ arg)])
				(= yDialog [args (++ arg)])
			)
			(#title:
				(= theTitle [args (++ arg)])
			)
			(#first:
				(= firstItemNum [args (++ arg)])
			)

			; item types
			(else
				(++ arg)
				(switch type
					(#text:
						((= theItem (DText new:))
							text: 	[args arg]
						)
					)
					(#button:
						((= theItem (DButton new:))
							text: 	[args arg],
							value:	[args (++ arg)]
						)
					)
					(#icon:
						((= theItem (DIcon new:))
							view:		[args arg],
							loop:		[args (++ arg)],
							cel:		[args (++ arg)]
						)
					)
					(#edit:
						((= theItem (DEdit new:))
							text:		[args arg],
							max:		[args (++ arg)]
						)
					)
					(else
						((= theItem (DText new:))
							text: 	[args (-- arg)]
						)
					)
				)

				(if (and (< (+ arg 1) argc) (== [args (+ arg 1)] #x:))
					(++ arg)
					(+= right [args (++ arg)])
				)
				(if (and (< (+ arg 1) argc) (== [args (+ arg 1)] #y:))
					(++ arg)
					(+= top [args (++ arg)])
				)
			
				(theItem
					setSize:,
					moveTo:	(+ right MARGIN) (+ top MARGIN)
				)
				
				(theDialog add: theItem)
				(= right (theItem nsRight?))
			)
		)
	)
	
	(theDialog
		setSize:,
		center:
	)
	(theDialog
		moveTo:
		 (if (== -1 xDialog) (theDialog nsLeft?) else xDialog)
		 (if (== -1 yDialog) (theDialog nsTop?) else yDialog)
	)
	
	(if theTitle
		(theDialog text: theTitle)
	)
	
	(= firstItem (theDialog at: firstItemNum))
	(if (not (& dActive (firstItem state?)))
		(= firstItem NULL)
	)

	(= ret
		(theDialog
			open: (if theTitle wTitled else 0) -1,
			doit:	firstItem
		)
	)

	(if (IsObject ret)
		(if (ret isKindOf: DButton)
			(= ret (ret value?))
		else
			(= ret 1)
		)
	)
	(theDialog dispose:)
	(return ret)
)
