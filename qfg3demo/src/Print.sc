;;; Sierra Script 1.0 - (do not remove this comment)
(script# PRINT)
(include game.sh)
(use Main)
(use Intrface)
(use Dialog)
(use System)

(public
	Prints 0
	Printf 1
	GetInput 2
)

(procedure (Prints)
	(Print addText: &rest init:)
)

(procedure (Printf)
	(Print addTextF: &rest init:)
)

(procedure (GetInput str maxLen prompt)
	(if
		(Print
			addText: (if (>= argc 3) prompt else {})
			addEdit: str maxLen 0 12 str
			init:
		)
		(StrLen str)
	)
)

(class Print of Object
	(properties
		dialog 0
		window 0
		title 0
		mode 0
		font 0
		width 0
		x -1
		y -1
		ticks 0
		caller 0
		retValue 0
		modeless 0
		first 0
		saveCursor 0
	)
	
	(method (init whoCares)
		(= caller 0)
		(if argc (= caller whoCares))
		(if (> argc 1) (self addText: &rest))
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
	
	(method (dispose)
		(if (and prints (prints contains: self))
			(prints delete: self)
			(if (prints isEmpty:) (prints dispose:) (= prints 0))
		)
		(= title (= first (= saveCursor (= window 0))))
		(= font userFont)
		(= width (= mode 0))
		(= x (= y -1))
		(= modeless 0)
		(sounds pause: 0)
		(super dispose:)
	)
	
	(method (showSelf &tmp default curPort i theX theY)
		(if saveCursor (theGame setCursor: ARROW_CURSOR))
		(if (not dialog) (= dialog (Dialog new:)))
		(dialog
			window: (if window else systemWindow)
			name: {PODialog}
			caller: self
		)
		(dialog
			text: title
			time: ticks
			setSize:
		)
		(dialog center:)
		(= theX (if (== x -1) (dialog nsLeft?) else x))
		(= theY (if (== y -1) (dialog nsTop?) else y))
		(dialog moveTo: theX theY)
		(= curPort (GetPort))
		(dialog open: (if title 4 else 0) 15)
		(return
			(if modeless
				(= modelessPort (GetPort))
				(SetPort curPort)
				(= modelessDialog dialog)
			else
				(sounds pause: TRUE)
				(cond 
					((not (= default first))
						(if
							(and
								(= default (dialog firstTrue: #checkState dActive))
								(not (dialog firstTrue: #checkState dExit))
							)
							(default state: (| (default state?) dExit))
						)
					)
					((not (IsObject default)) (= default (dialog at: default)))
				)
				(= retValue (dialog doit: default))
				(SetPort curPort)
				(cond 
					((== retValue -1) (= retValue FALSE))
					(
					(and (IsObject retValue) (retValue isKindOf: DButton)) (= retValue (retValue value?)))
					((not (dialog theItem?)) (= retValue TRUE))
				)
				(if saveCursor
					(theGame setCursor: ((theIconBar curIcon?) cursor?))
				)
				(dialog dispose:)
				(return retValue)
			)
		)
	)
	
	(method (addButton val args &tmp noun verb case seq theX theY module theText s)
		(if (not dialog) (= dialog (Dialog new:)))
		(if (> argc 4)
			(= noun [args 0])
			(= verb [args 1])
			(= case [args 2])
			(= seq
				(if [args 3] [args 3] else 1)
			)
			(= theX 0)
			(= theY 0)
			(= module curRoomNum)
			(if (> argc 5)
				(= theX [args 4])
				(if (> argc 6)
					(= theY [args 5])
					(if (> argc 7) (= module [args 6]))
				)
			)
			(if
				(= s
					(Message MsgSize module noun verb case seq)
				)
				(= theText (Memory MNeedPtr s))
				(if
					(not
						(Message MsgGet module noun verb case seq theText)
					)
					(= theText 0)
				)
			)
		else
			(= theX 0)
			(= theY 0)
			(if (> argc 2)
				(= theX [args 1])
				(if (> argc 3)
					(= theY [args 2])
				)
			)
			(= theText
				(Memory MNeedPtr (+ (StrLen [args 0]) 1))
			)
			(StrCpy theText [args 0])
		)
		(if theText
			(dialog
				add:
					((DButton new:)
						value: val
						font: font
						text: theText
						setSize:
						moveTo: (+ 4 theX) (+ 4 theY)
						yourself:
					)
				setSize:
			)
		)
	)
	
	(method (addEdit buf maxSize theX theY default &tmp eX eY)
		(if (not dialog) (= dialog (Dialog new:)))
		(StrCpy buf (if (> argc 4) default else {}))
		(if (> argc 2)
			(= eX theX)
			(if (> argc 3) (= eY theY))
		)
		(dialog
			add:
				((DEdit new:)
					text: buf
					max: maxSize
					setSize:
					moveTo: (+ eX MARGIN) (+ eY MARGIN)
					yourself:
				)
			setSize:
		)
	)
	
	(method (addIcon theViewOrObj theLoop theCel theX theY &tmp iX iY)
		(if (not dialog) (= dialog (Dialog new:)))
		(if (> argc 3)
			(= iX theX)
			(= iY theY)
		else
			(= iX (= iY 0))
		)
		(if (IsObject theViewOrObj)
			(dialog
				add: (theViewOrObj
					setSize:
					moveTo: (+ iX 4) (+ iY 4)
					yourself:
				)
				setSize:
			)
		else
			(dialog
				add:
					((DIcon new:)
						view: theViewOrObj
						loop: theLoop
						cel: theCel
						setSize:
						moveTo: (+ iX 4) (+ iY 4)
						yourself:
					)
				setSize:
			)
		)
	)
	
	(method (addText args &tmp noun verb case seq theX theY module theText s)
		(if (not dialog) (= dialog (Dialog new:)))
		(if (> argc 3)
			(= noun [args 0])
			(= verb [args 1])
			(= case [args 2])
			(= seq
				(if [args 3] [args 3] else 1)
			)
			(= theX 0)
			(= theY 0)
			(= module curRoomNum)
			(if (>= argc 5)
				(= theX [args 4])
				(if (>= argc 6)
					(= theY [args 5])
					(if (>= argc 7) (= module [args 6]))
				)
			)
			(if
				(= s
					(Message MsgSize module noun verb case seq)
				)
				(= theText (Memory MNeedPtr s))
				(if
					(Message MsgGet module noun verb case seq theText)
					(dialog
						add:
							((DText new:)
								text: theText
								font: font
								mode: mode
								setSize: width
								moveTo: (+ 4 theX) (+ 4 theY)
								yourself:
							)
						setSize:
					)
				)
			)
		else
			(= theX 0)
			(= theY 0)
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
						text: theText
						font: font
						mode: mode
						setSize: width
						moveTo: (+ 4 theX) (+ 4 theY)
						yourself:
					)
				setSize:
			)
		)
	)
	
	(method (addTextF buffer)
		(Format buffer &rest)
		(self addText: buffer)
	)
	
	(method (posn theX theY)
		(= x theX)
		(= y theY)
	)
	
	(method (handleEvent event)
		(if (dialog handleEvent: event) (dialog dispose:))
	)
	
	(method (cue &tmp theCaller)
		(= theCaller caller)
		(= dialog 0)
		(if window (window dispose:))
		(self dispose:)
		(if theCaller (theCaller cue:))
	)
)
