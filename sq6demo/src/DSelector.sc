;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	DSELECTR.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Brian K. Hughes
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	The DSelector class.
;;;;
;;;;	Classes:
;;;;		DSelector
;;;;		SelectorDText


(script# DSELECTOR)
(include game.sh) (include "64990.shm")
(use Main)
(use DButton)
(use DText)
(use DItem)
(use Plane)
(use String)
(use Array)
(use System)


(define MAX_NUMFILES		250)


(class DSelector kindof DItem
	(properties
		font			SYSFONT		; font of text in list
		length		0				; max number of text items to show
		width			100			; max width of text items
		textHeight	0				; height of text items
		first			0				; index of first item showing
		current		0				; current (hilited) item
		listPlane	0				; plane in which items are put
		textList		0				; list in which items are kept
		bitmap		0				; dynamic skip color bitmap
		fore			0				; foreground color of text
		back			255			; background color of text
		slider		0				; slider for scrolling
		knob			0				; knob of slider
		upButton		0				; button/feature to scroll up
		downButton	0				; button/feature to scroll down
	)
;;;	(methods
;;;		setText
;;;		getText
;;;		scrollDown
;;;		scrollUp
;;;	)

	(method (init theList &tmp d newCast str)
		; Create the bitmap (skip) so DSelector can animate
		(= d
			((DText new:)
				fore:		0,
				back:		0,
				skip:		0,
				font:		font,
				text:		{ },
				setSize:	,
				yourself:
			)
		)
		(= bitmap
			(CreateTextBitmap
				TBMWithoutBitmap
				(+ (- (d nsRight?) (d nsLeft?)) 1)
				(+ (- (d nsBottom?) (d nsTop?)) 1)
				d
			)
		)
		(DisposeClone d)

		; Create the list plane
		(= listPlane
			((Plane new:)
				name:			{DSPlane},
				priority:	(+ (GetHighPlanePri) 1),
				yourself:
			)
		)

		(= first 0)

		; Init us, move the listPlane to the correct position & init the texts
		(super init: theList &rest)

		(listPlane
			init: 		0 0 (+ (- nsRight nsLeft) 1) (+ (- nsBottom nsTop) 1),
			back:			back,
;			setBitmap:	SAVE 5 0 TRUE,
			posn:			nsLeft nsTop plane,
			addCast:		textList
		)
		(textList eachElementDo: #init: textList)

		; If scrolling are buttons/features supplied, init them
;		;	Otherwise create a slider
		(if upButton
			(upButton init: theList)
		)
		(if downButton
			(downButton init: theList)
		)

		; DEBUG!
		; Create some default up and down buttons
		(= upButton
			(DButton new:)
		)
		(= str (String new:))
		(Message MsgGet SAVE N_BUTTON_UP 0 0 1 (str data?))
		(upButton
				font:			SYSFONT,
				text:			(String StrDup (str data?)),
				view:			SAVE,
				loop:			4,
				cel:			0,
				object:		self,
				selector:	#scrollUp,
				state:		dActive,
  				setSize: 	,
				init:			theList,
				yourself:
		)
		(Message MsgGet SAVE N_BUTTON_DOWN 0 0 1 (str data?))
		(= downButton
			((DButton new:)
				font:			SYSFONT,
				text:			(String StrDup (str data?)),
				view:			SAVE,
				loop:			4,
				cel:			0,
  				setSize: 	,
				object:		self,
				selector:	#scrollDown,
				state:		dActive,
				init:			theList,
				yourself:
	  		)
		)


;		(if (not (or upButton downButton))
;			(listPlane
;				right: 	(+ (listPlane right?) (= w (CelWide SAVE 6 0))),
;				addCast:	(= newCast (Cast new:)),
;			)
;			(UpdatePlane listPlane)
;			(= slider
;				((View new:)
;					view:			SAVE,
;					loop:			6,
;					cel:			0,
;					x:				(- (- (listPlane right?) (listPlane left?)) w),
;					y:				0,
;					init:			newCast,
;					setScale:	-1 (- (listPlane bottom?) (listPlane top?)),
;					yourself:
;				)
;			)
;		)

		; Make it all happen
		(self	draw:)

		(str dispose:)
	)

	(method (dispose)
		(textList dispose:)
		(if bitmap
			(Bitmap MapDispose bitmap)
			(= bitmap 0)
		)
		(listPlane dispose:)
		(upButton dispose:)
		(downButton dispose:)
		(= textList (= listPlane (= upButton (= downButton (= bitmap 0)))))
		(super dispose:)
	)

	(method (scrollDown howMany &tmp num)
		;
		; Move the hilite bar down, scrolling if necessary

		(= num (if argc howMany else 1))
		(if (>= (+= current howMany) (textList size?))
			(= current (- (textList size?) 1))
		)

		(if (>= current (+ first length))
			(= first (- current (- length 1)))
		)

		(self draw:)
	)

	(method (scrollUp howMany &tmp num)
		;
		; Move the hilite bar up, scrolling if necessary

		(= num (if argc howMany else 1))
		(if (< (-= current howMany) 0)
			(= current 0)
		)

		(if (< current first)
			(= first current)
		)

		(self draw:)
	)

	(method (draw &tmp node nextNode obj cobj i)
		(if (textList size?)
			(= cobj (textList at: current))
			(for	((= node (List LFirstNode (textList elements?))) (= i 0))
					node
					((= node nextNode) (++ i))
				(= nextNode (List LNextNode node))
				(= obj (List LNodeValue node))
				(obj
					moveTo:	2 (+ 1 (* (- first i) (* -1 textHeight))),
					fore:		(if (== cobj obj) back else fore),
					back:		(if (== cobj obj) fore else back),
					draw:
				)
				(UpdateScreenItem obj)
			)
		)
		;;; This saves the text off in a global variable so can look at it
		;;; later.
		(if saveFileSelText 
			(saveFileSelText dispose:) 
			(= saveFileSelText 0)
		)
		(if (>= (- (textList size:) 1) current 0)
			(= saveFileSelText 
				(String with:
					((textList at: current) text?)
				)
			)
		)
	)

	(method (setText theText &tmp i widest l str)
		;
		; If theText is 0, dispose of the list.
		;	Otherwise, create a list (if necessary) and add the args.

		(if (not theText)
			(if textList
				(textList dispose:)
				(= textList 0)
			)
		)

		(if (not textList)
			(= textList
				((Cast new:)
					name:		{DSList},
					add:		,
					yourself:
				)
			)
		)

		(if theText
			; Now add all the items as SelectorDTexts to the textList
			(= str (String new:))
			(for	((= i 0))
					(and (< i argc) (< (textList size:) MAX_NUMFILES))
					((++ i))
				(str copy: [theText i])
				(textList add:
					((SelectorDText new:)
						font:			font,
						text:			(String StrDup (str data?)),
						setSize:		(- width MARGIN),
						yourself:
					)
				)
			)
			(str dispose:)

			; If the length hasn't been set (it is 0) then set it
			(if (not length)
				(= length argc)
			)

			; If the listPlane has already been created, init the text items
			(if listPlane
				(listPlane addCast: textList)
				(textList eachElementDo: #init: textList)
			)
		)
	)

	(method (getText)
		;
		; Return a copy of the text for the current item

		(return (String StrDup ((textList at: current) text?)))
	)

	(method (setSize &tmp widest node obj r)
		; Find the width of the widest text item if not specified
		(= r (IntArray new:))
		(= widest 0)
		(if (not width)
			(for	((= node (List LFirstNode (textList elements?))))
					node
					((= node (textList nextNode?)))
				(textList nextNode: (List LNextNode node))
				(= obj (List LNodeValue node))
				(Text TextSize (r data?) (obj text?) font 0)
				(= widest (Max (r at: 2) widest))
			)
		)

		; Find the height of the font
		(Text TextSize (r data?) {M} font 0)
		(= textHeight (+ (r at: 3) 2))
		(r dispose:)

		; Set the nsRect
		(= nsLeft 0)
		(= nsTop 0)
		(= nsRight (Max width widest))
		(= nsBottom (- (* textHeight length) 4))
	)

	(method (updatePlane &tmp c l h)
		;
		; Position our listPlane on the dialog's plane

		(listPlane posn: (listPlane left?) (listPlane top?) plane)
		(UpdatePlane listPlane)

		; Move the up and down buttons to the right of the selector,
		;	equally spaced above and below the centerline
		(= c
			(-
				(+ (/ (- (listPlane bottom?) (listPlane top?)) 2) (listPlane top?))
				(plane top?)
			)
		)
		(= l (+ (- (listPlane right?) (plane left?)) MARGIN))
		(= h (CelHigh (upButton view?) (upButton loop?) (upButton cel?)))
		(upButton moveTo: l (- c (+ h MARGIN)))
		(UpdateScreenItem upButton)
		(downButton moveTo: l (+ c MARGIN))
		(UpdateScreenItem downButton)
	)

	(method (handleEvent event &tmp ret)
		(if (== (event type?) mouseDown)
			(event globalize:)
			(if (listPlane onMe: event)
				(event localize: listPlane)
				(if (= ret (textList firstTrue: #onMe: event))
					(= current (textList indexOf: ret))

					(self draw:)
				)
			)
			(event localize: plane)
		)
		(if (event claimed?)
			(return self)
		)
	)
)


(class SelectorDText kindof DText
	(method (setSize w)
		;
		; Overridden to fix the bitmap size to exactly fit the list plane

		(super setSize: w)

		; Now set the nsRight and textRight to the size of the plane
		(= nsRight w)
		(= textRight (- w (- textLeft nsLeft)))
	)
)
