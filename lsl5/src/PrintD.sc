;;; Sierra Script 1.0 - (do not remove this comment)
(script# PRINTD)
(include game.sh)
(use Main)
(use Intrface)

(public
	PrintD 0
)

(procedure (PrintD args &tmp
		arg theDialog theItem right bottom top left ret type theObj
		xDialog yDialog theTitle theValue theFirst)
	(= xDialog (= yDialog -1))
	(= right (= bottom (= top (= left 0))))
	(= theTitle 0)
	(= theFirst 0)
	((= theDialog (Dialog new:)) window: systemWindow)
	(= arg 0)
	(while (< arg argc)
		(switch (= type [args arg])
			(#new
				(= top (theItem nsBottom?))
				(= right 0)
			)
			(#at
				(= xDialog [args (++ arg)])
				(= yDialog [args (++ arg)])
			)
			(#title
				(= theTitle [args (++ arg)])
			)
			(#first
				(= theFirst [args (++ arg)])
			)
			(else 
				(++ arg)
				(switch type
					(#text
						((= theItem (DText new:)) text: [args arg])
					)
					(#button
						((= theItem (DButton new:))
							text: [args arg]
							value: [args (++ arg)]
						)
					)
					(#icon
						((= theItem (DIcon new:))
							view: [args arg]
							loop: [args (++ arg)]
							cel: [args (++ arg)]
						)
					)
					
					(#edit
						((= theItem (DEdit new:))
							text: [args arg]
							max: [args (++ arg)]
						)
					)
					(else 
						((= theItem (DText new:)) text: [args (-- arg)])
					)
				)
				(if
				(and (< (+ arg 1) argc) (== [args (+ arg 1)] 4))
					(++ arg)
					(= right
						(+ right [args (++ arg)])
					)
				)
				(if
				(and (< (+ arg 1) argc) (== [args (+ arg 1)] 3))
					(++ arg)
					(= top
						(+ top [args (++ arg)])
					)
				)
				(theItem
					setSize:
					moveTo: (+ right MARGIN) (+ top MARGIN)
				)
				(theDialog add: theItem)
				(= right (theItem nsRight?))
			)
		)
		(++ arg)
	)
	(theDialog setSize: center:)
	(theDialog
		moveTo:
			(if (== -1 xDialog) (theDialog nsLeft?) else xDialog)
			(if (== -1 yDialog) (theDialog nsTop?) else yDialog)
	)
	(if theTitle (theDialog text: theTitle))
	(= theValue (theDialog at: theFirst))
	(if (not (& dButton (theValue state?))) (= theValue 0))
	(= ret
		(theDialog open: (if theTitle wTitled else 0) -1 doit: theValue)
	)
	(if (IsObject ret)
		(if (ret isKindOf: DButton)
			(= ret (ret value?))
		else
			(= ret TRUE)
		)
	)
	(theDialog dispose:)
	(return ret)
)
