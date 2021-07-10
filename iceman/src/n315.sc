;;; Sierra Script 1.0 - (do not remove this comment)
(script# 315)
(include game.sh)
(use Main)
(use Intrface)

(public
	MachinePrint 0
)

(procedure (MachinePrint args
							&tmp theDialog textI iconI editI
							temp4 temp5 temp6 ret i atX atY fixWidth keepIt default curPort
							[buttons 8] buttonWide itemWide buttonsUsed butAtX
							[buffer 800])
	(= atX (= atY -1))
	(= keepIt
		(= fixWidth
			(= buttonWide
				(= itemWide (= iconI (= editI (= buttonsUsed 0))))
			)
		)
	)
	((= theDialog (Dialog new:))
		window: systemWindow
		name: {PrintD}
	)
	(= textI (DText new:))
	(cond 
		((u< [args 0] 1000) (GetFarText [args 0] [args 1] @buffer)
			(= i 2)
		)
		([args 0] (StrCpy @buffer [args 0])
			(= i 1)
		)
		(else
			(= buffer 0)
			(= i 0)
		)
	)
	(textI
		text: @buffer
		moveTo: 4 4
		font: userFont
		setSize:
	)
	(theDialog add: textI)
	(= i i)
	(while (< i argc)
		(switch [args i]
			(#mode
				(++ i)
				(textI mode: [args i])
			)
			(#font
				(++ i)
				(textI font: [args i] setSize: fixWidth)
			)
			(#width
				(= fixWidth [args (++ i)])
				(textI setSize: fixWidth)
			)
			(#time
				(++ i)
				(theDialog time: [args i])
			)
			(#title
				(++ i)
				(theDialog text: [args i])
			)
			(#at
				(= atX [args (++ i)])
				(= atY [args (++ i)])
			)
			(#draw
				(Animate (cast elements?) FALSE)
			)
			(#edit
				(++ i)
				((= editI (DEdit new:)) text: [args i])
				(++ i)
				(editI max: [args i] setSize:)
			)
			(#button
				((= [buttons buttonsUsed] (DButton new:))
					text: [args (++ i)]
					value: [args (++ i)]
					setSize:
				)
				(= buttonWide (+ buttonWide ([buttons buttonsUsed] nsRight?) 4))
				(++ buttonsUsed)
			)
			(#theItem
				((= [buttons buttonsUsed] (DButton new:))
					text: [args (++ i)]
					value: [args (++ i)]
					setSize:
				)
				(= itemWide (+ itemWide ([buttons buttonsUsed] nsBottom?) 4))
				(++ buttonsUsed)
			)
			(#icon
				(if (not iconI) (= iconI (DIcon new:)))
				(iconI
					view: [args (+ i 1)]
					loop: [args (+ i 2)]
					cel: [args (+ i 3)]
					setSize:
				)
				(= i (+ i 3))
			)
			(#dispose
				(if modelessDialog (modelessDialog dispose:))
				(= keepIt theDialog)
			)
			(#window
				(++ i)
				(theDialog window: [args i])
			)
		)
		(++ i)
	)
	(if iconI
		(iconI moveTo: 4 4)
		(textI
			moveTo: (+ 4 (iconI nsRight?)) (textI nsTop?)
		)
		(theDialog add: iconI)
	)
	(theDialog setSize:)
	(if editI
		(editI
			moveTo: (textI nsLeft?) (+ 4 (textI nsBottom?))
		)
		(theDialog add: editI setSize:)
	)
	(cond 
		(buttonWide
			(= butAtX
				(if (> buttonWide (theDialog nsRight?))
					4
				else
					(- (theDialog nsRight?) buttonWide)
				)
			)
			(= i 0)
			(while (< i buttonsUsed)
				([buttons i] moveTo: butAtX (theDialog nsBottom?))
				(theDialog add: [buttons i])
				(= butAtX (+ 4 ([buttons i] nsRight?)))
				(++ i)
			)
		)
		(itemWide
			(= butAtX (+ (theDialog nsTop?) 4))
			(= i 0)
			(while (< i buttonsUsed)
				([buttons i] moveTo: (theDialog nsRight?) butAtX)
				(theDialog add: [buttons i])
				(= butAtX (+ 4 ([buttons i] nsBottom?)))
				(++ i)
			)
		)
	)
	(theDialog setSize: center:)
	(if (and iconI (not (StrLen @buffer)))
		(iconI
			moveTo:
				(/
					(-
						(- (theDialog nsRight?) (theDialog nsLeft?))
						(- (iconI nsRight?) (iconI nsLeft?))
					)
					2
				)
				4
		)
	)
	(= temp5 (if (== atY -1) 0 else atY))
	(if
		(>
			(+
				temp5
				(= temp6 (- (theDialog nsBottom?) (theDialog nsTop?)))
			)
			180
		)
		(= temp5 (- temp5 (- (+ temp5 temp6) 180)))
	)
	(if (< (+ temp5 (theDialog nsTop?)) 10)
		(= temp5 (+ temp5 (- (theDialog nsTop?) 10)))
	)
	(theDialog
		moveTo:
			(if (== -1 atX) (theDialog nsLeft?) else atX)
			(if temp5 else (theDialog nsTop?))
	)
	(= curPort (GetPort))
	(theDialog
		open: (if (theDialog text?) 4 else 0) (if keepIt 15 else -1)
	)
	(if keepIt
		(= modelessPort (GetPort))
		(SetPort curPort)
		(return (= modelessDialog keepIt))
		(DisposeScript 315)
	)
	(if
		(and
			(= default (theDialog firstTrue: #checkState 1))
			(not (theDialog firstTrue: #checkState 2))
		)
		(default state: (| (default state?) $0002))
	)
	(if (== (= ret (theDialog doit: default)) -1)
		(= ret 0)
	)
	(= i 0)
	(while (< i buttonsUsed)
		(if (== ret [buttons i])
			(= ret (ret value?))
			(break)
		)
		(++ i)
	)
	(if (not (theDialog theItem?)) (= ret 1))
	(theDialog dispose:)
	(return ret)
)
