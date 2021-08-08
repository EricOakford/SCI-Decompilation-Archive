;;; Sierra Script 1.0 - (do not remove this comment)
(script# 754)
(include game.sh)
(use Main)
(use Intrface)
(use System)

(public
	proc754_0 0
)

(local
	[local0 52]
	[theNewDButton 26]
	[local78 4]
)
(procedure (proc754_0 &tmp temp0 temp1 temp2)
	(CPCheckD window: systemWindow)
	(CPCheckD init: &rest)
	(CPCheckD open: 0 0)
	(= temp1 (theGame setCursor: normalCursor))
	(= temp2 0)
	(= temp0 0)
	(while (< temp0 3)
		(if (CPCheckD doit:) (= temp2 1) (break))
		(if (<= temp0 1) (Print 754 0))
		(localproc_0482)
		(++ temp0)
	)
	(CPCheckD dispose:)
	(theGame setCursor: temp1)
	(DisposeScript 754)
	(return temp2)
)

(procedure (localproc_042c param1 &tmp temp0 temp1 temp2 temp3)
	(= temp3 0)
	(= temp0 0)
	(while (< temp0 4)
		(= temp2 (& (= temp1 [local78 temp0]) $0100))
		(= temp1 (& temp1 $feff))
		(if (and (not temp2) (== temp1 (param1 value?)))
			(= [local78 temp0] (| [local78 temp0] $0100))
			(= temp3 1)
			(break)
		)
		(++ temp0)
	)
	(return temp3)
)

(procedure (localproc_0482 &tmp temp0)
	(= temp0 0)
	(while (< temp0 4)
		(= [local78 temp0] (& [local78 temp0] $feff))
		(++ temp0)
	)
)

(instance CPCheckD of Dialog
	
	(method (init param1 &tmp temp0 temp1 temp2 newDButton temp4 temp5 temp6 temp7 temp8 temp9 temp10 newDButtonNsRight)
		(= temp0 (= temp2 0))
		(while (< temp0 26)
			(StrAt @local0 temp2 (+ 65 temp0))
			(StrAt @local0 (+ temp2 1) 0)
			(++ temp0)
			(= temp2 (+ temp2 2))
		)
		(= temp6 0)
		(= temp7 0)
		(= temp0 0)
		(= temp4 @local0)
		(while (< temp0 26)
			((= newDButton (DButton new:))
				value: temp0
				state: 3
				text: temp4
				key: (+ 65 temp0)
				setSize:
				moveTo: temp6 temp7
			)
			(self add: newDButton)
			(= [theNewDButton temp0] newDButton)
			(if (== temp0 12)
				(= temp6 0)
				(= temp7 (+ (newDButton nsBottom?) 4))
			else
				(= temp6 (+ (newDButton nsRight?) 4))
			)
			(++ temp0)
			(= temp4 (+ temp4 2))
		)
		(= newDButtonNsRight (newDButton nsRight?))
		(cond 
			((u< [param1 0] 1000) (= temp5 (GetFarText [param1 0] [param1 1] 0)))
			([param1 0]
				(= temp5 (Memory 1 (+ (StrLen [param1 0]) 1)))
				(StrCpy temp5 [param1 0])
			)
			(else (= temp5 (Memory 1 2)) (StrCpy temp5 {_}))
		)
		((= newDButton (DText new:))
			disposeText: 1
			text: temp5
			font: userFont
			setSize: newDButtonNsRight
		)
		(newDButton
			moveTo:
				(/
					(-
						newDButtonNsRight
						(- (newDButton nsRight?) (newDButton nsLeft?))
					)
					2
				)
				4
		)
		(self add: newDButton)
		(= temp6 0)
		(= temp7 (+ (newDButton nsBottom?) 0))
		(= temp9 0)
		(= temp0 0)
		(while (< temp0 4)
			(repeat
				(= [local78 temp0] (= temp8 (Random 0 25)))
				(= temp1 0)
				(while (< temp1 temp0)
					(breakif (== [local78 temp1] temp8))
					(++ temp1)
				)
				(breakif (== temp1 temp0))
			)
			((= newDButton (DIcon new:))
				view: 940
				loop: (/ temp8 16)
				cel: (mod temp8 16)
				setSize:
				moveTo: temp6 temp7
			)
			(self add: newDButton)
			(if
				(>
					(= temp1
						(- (newDButton nsBottom?) (newDButton nsTop?))
					)
					temp9
				)
				(= temp9 temp1)
			)
			(= temp6 (+ (newDButton nsRight?) 12))
			(++ temp0)
		)
		(= temp6
			(/ (- newDButtonNsRight (newDButton nsRight?)) 2)
		)
		(= temp0 0)
		(= temp10 (self contains: newDButton))
		(while (and (< temp0 4) temp10)
			((NodeValue temp10) move: temp6 0)
			(++ temp0)
			(= temp10 (self prev: temp10))
		)
		(= temp7 (+ temp7 temp9 8))
		(= temp0 0)
		(= temp10 (self contains: [theNewDButton 0]))
		(while (and (< temp0 26) temp10)
			((NodeValue temp10) move: 0 temp7)
			(++ temp0)
			(= temp10 (self next: temp10))
		)
		(self setSize: center:)
	)
	
	(method (doit param1 &tmp temp0 temp1 temp2 theEatMice temp4 temp5 temp6)
		(= temp0 0)
		(= temp6 0)
		(= busy 1)
		(self eachElementDo: #init)
		(if theItem (theItem select: 0))
		(= theItem
			(if (and argc param1)
				param1
			else
				(self firstTrue: #checkState 1)
			)
		)
		(if theItem (theItem select: 1))
		(if (not theItem)
			(= theEatMice eatMice)
			(= temp4 (GetTime))
		else
			(= theEatMice 0)
		)
		(= temp5 0)
		(while (< temp5 4)
			(= temp2 0)
			(while (not temp2)
				(self eachElementDo: #cycle)
				(= temp1 ((Event new:) localize:))
				(if theEatMice
					(-- theEatMice)
					(if (== (temp1 type?) 1) (temp1 type: 0))
					(while (== temp4 (GetTime))
					)
					(= temp4 (GetTime))
				)
				(if (== (= temp2 (self handleEvent: temp1)) -1)
					(= temp2 0)
				)
				(temp1 dispose:)
				(self check:)
				(if (or (== temp2 -1) (not busy))
					(= temp2 0)
					(EditControl theItem 0)
					(break)
				)
				(Wait 1)
			)
			(= temp6 (+ temp6 (localproc_042c temp2)))
			(++ temp5)
		)
		(= busy 0)
		(return (== temp6 4))
	)
)
