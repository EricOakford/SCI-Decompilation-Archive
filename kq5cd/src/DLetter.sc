;;; Sierra Script 1.0 - (do not remove this comment)
(script# 759)
(include game.sh)
(use Main)
(use Intrface)

(public
	KQPrint 0
)

(procedure (KQPrint theString moreStuff &tmp temp0 temp1 temp2 temp3 temp4 [buffer 400] [temp405 400])
	(if (> argc 2)
		(Print theString moreStuff &rest)
	else
		(if (u< theString 1000)
			(GetFarText theString moreStuff @buffer)
		else
			(StrCpy @buffer theString)
		)
		(= temp1 (StrAt @buffer 0))
		(= temp2 (StrAt @buffer 1))
		(if (and (<= 65 temp1) (<= temp1 90))
			(StrAt @buffer 0 32)
			(if (== temp2 32)
				(Format @temp405 {___})
			else
				(Format @temp405 {__})
			)
			(StrCat @temp405 @buffer)
			(= temp3 (+ 0 (/ (- temp1 65) 13)))
			(= temp4 (mod (- temp1 65) 13))
			(localproc_0147 @temp405 &rest 82 945 temp3 temp4 1)
		else
			(Print @buffer &rest)
		)
	)
)

(procedure (localproc_0147 param1 &tmp newDialog newDText newDLetter temp3 temp4 temp5 temp6 temp7 temp8 theModelessDialog temp10 temp11 [temp12 6] temp18 temp19 temp20 [temp21 500])
	(= temp11 (GetPort))
	(= temp6 (= temp7 -1))
	(= theModelessDialog
		(= temp8
			(= temp18 (= newDLetter (= temp3 (= temp19 0))))
		)
	)
	((= newDialog (Dialog new:))
		window: systemWindow
		name: {GothicD}
	)
	(= newDText (DText new:))
	(cond 
		((u< [param1 0] 1000) (GetFarText [param1 0] [param1 1] @temp21) (= temp5 2))
		([param1 0] (StrCpy @temp21 [param1 0]) (= temp5 1))
		(else (= temp21 0) (= temp5 0))
	)
	(newDText text: @temp21 font: userFont setSize:)
	(newDialog add: newDText)
	((= newDLetter (DLetter new:))
		view: [param1 (++ temp5)]
		loop: [param1 (++ temp5)]
		cel: [param1 (++ temp5)]
		letter: [param1 (++ temp5)]
		setSize:
	)
	(if temp19
		(theGame setCursor: (= gGTheGameCursor 999) (HaveMouse))
	)
	(newDLetter moveTo: 4 4)
	(newDText
		moveTo: (+ 4 (newDLetter nsLeft?)) (- (newDLetter nsBottom?) 7)
	)
	(newDialog add: newDLetter)
	(newDialog setSize:)
	(= temp20
		(if (> temp18 (newDialog nsRight?))
			4
		else
			(- (newDialog nsRight?) temp18)
		)
	)
	(= temp5 0)
	(while (< temp5 temp19)
		([temp12 temp5]
			moveTo: temp20 (+ 4 (newDialog nsBottom?))
		)
		(newDialog add: [temp12 temp5])
		(= temp20 (+ 4 ([temp12 temp5] nsRight?)))
		(++ temp5)
	)
	(newDialog setSize:)
	(newDialog
		nsRight: (+ 4 (newDialog nsRight?))
		nsBottom: (+ (newDialog nsBottom?) 4)
		center:
	)
	(newDialog
		moveTo:
			(if (== -1 temp6) (newDialog nsLeft?) else temp6)
			(if (== -1 temp7) (newDialog nsTop?) else temp7)
	)
	(newDialog
		open: (if (newDialog text?) 4 else 0) (if theModelessDialog 15 else -1)
	)
	(if theModelessDialog
		(= modelessPort (GetPort))
		(SetPort temp11)
		(return (= modelessDialog theModelessDialog))
	)
	(if
		(and
			(= temp10 (newDialog firstTrue: #checkState 1))
			(not (newDialog firstTrue: #checkState 2))
		)
		(temp10 state: (| (temp10 state?) $0002))
	)
	(if (== (= temp4 (newDialog doit: temp10)) -1)
		(= temp4 0)
	)
	(= temp5 0)
	(while (< temp5 temp19)
		(if (== temp4 [temp12 temp5])
			(= temp4 (temp4 value?))
			(break)
		)
		(++ temp5)
	)
	(if (not (newDialog theItem?)) (= temp4 1))
	(newDialog dispose:)
	(return temp4)
)

(class DLetter of DIcon
	(properties
		type dIcon
		letter 0
		priority -1
	)
)
