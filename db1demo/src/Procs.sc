;;; Sierra Script 1.0 - (do not remove this comment)
(script# 5)
(include sci.sh)
(use Main)
(use Intrface)
(use Invent)
(use User)
(use System)

(public
	SolvePuzzle 0
	HandsOff 1
	HandsOn 2
	HaveMem 3
	IsObjectOnControl 4
	Btst 5
	Bset 6
	Bclr 7
	proc5_8 8
	HighPrint 9
	TimePrint 10
	proc5_11 11
	DoDisplay 12
	proc5_13 13
	proc5_14 14
	proc5_15 15
	proc5_16 16
	proc5_17 17
)

(local
	local0
	[local1 4]
)
(procedure (SolvePuzzle param1 param2 &tmp temp0 temp1)
	(if (not (= temp0 (Btst param1)))
		(= temp1 (if (> argc 1) param2 else arcadeLevel))
		(Bset param1)
		(++ numCoins)
		(++ [global119 temp1])
		(if (not (ego has: 0)) (ego get: 0))
	)
	(return temp0)
)

(procedure (HandsOff)
	(if (not global122)
		(User canControl: 0 canInput: 0)
		(theGame setCursor: 50 1)
	)
	(return (++ global122))
)

(procedure (HandsOn param1 &tmp temp0 theIconBarCurIcon)
	(if (or (and argc param1) (<= (-- global122) 0))
		(= global122 0)
		(User canControl: 1 canInput: 1)
		(= temp0
			(if (= theIconBarCurIcon (theIconBar curIcon?))
				(theIconBarCurIcon cursor?)
			else
				999
			)
		)
		(theGame setCursor: temp0 1)
	)
)

(procedure (HaveMem param1)
	(return (u> (MemoryInfo 1) param1))
)

(procedure (IsObjectOnControl param1 param2)
	(return (if (& (param1 onControl: 1) param2) (return 1) else 0))
)

(procedure (Btst param1)
	(return
		(&
			[gameFlags (/ param1 16)]
			(>> $8000 (mod param1 16))
		)
	)
)

(procedure (Bset param1 &tmp temp0)
	(= temp0 (Btst param1))
	(= [gameFlags (/ param1 16)]
		(|
			[gameFlags (/ param1 16)]
			(>> $8000 (mod param1 16))
		)
	)
	(return temp0)
)

(procedure (Bclr param1 &tmp temp0)
	(= temp0 (Btst param1))
	(= [gameFlags (/ param1 16)]
		(&
			[gameFlags (/ param1 16)]
			(~ (>> $8000 (mod param1 16)))
		)
	)
	(return temp0)
)

(procedure (proc5_8 param1 param2 param3 param4 param5 theTheUserFont &tmp temp0 temp1 temp2 temp3 theUserFont)
	(if (>= argc 6)
		(= theUserFont theTheUserFont)
	else
		(= theUserFont userFont)
	)
	(if (and (>= argc 5) (!= param5 -1))
		(localproc_01b3 0 theUserFont param1)
		(= temp0 (+ param3 [local1 1]))
		(= temp1 (+ param4 [local1 0]))
		(= temp2 (+ param3 [local1 3] 1))
		(= temp3 (+ param4 [local1 2]))
		(Graph grFILL_BOX temp1 temp0 temp3 temp2 1 param5 -1 -1)
	)
	(Display
		param1
		dsCOORD
		(+ param3 1)
		param4
		dsCOLOR
		(+ param2 2)
		dsALIGN
		0
		dsFONT
		theUserFont
		&rest
	)
	(Display
		param1
		dsCOORD
		param3
		param4
		dsCOLOR
		param2
		dsALIGN
		0
		dsFONT
		theUserFont
		&rest
	)
)

(procedure (HighPrint)
	(if modelessDialog (modelessDialog dispose:))
	(Print &rest #at -1 12)
)

(procedure (TimePrint param1)
	(if modelessDialog (modelessDialog dispose:))
	(Print &rest #at -1 12 #time param1 #dispose)
)

(procedure (proc5_11 param1 &tmp temp0)
	(localproc_01b3 0 userFont param1 &rest)
	(= temp0 [local1 2])
	(Print param1 &rest 67 -1 (- 180 temp0))
)

(procedure (DoDisplay param1 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8)
	(return
		(if (== argc 1)
			(Display 5 0 108 [param1 0])
		else
			(= temp4 (= temp5 -1))
			(= temp0 1)
			(= temp1 70)
			(= temp2 73)
			(= temp3 160)
			(= temp6 28)
			(= temp7 0)
			(= temp8 1)
			(while (< temp8 argc)
				(switch [param1 temp8]
					(30
						(= temp0 [param1 (++ temp8)])
					)
					(33
						(= temp2 (+ (= temp1 [param1 (++ temp8)]) 1))
					)
					(70
						(= temp3 [param1 (++ temp8)])
					)
					(67
						(= temp4 [param1 (++ temp8)])
						(= temp5 [param1 (++ temp8)])
					)
					(28
						(= temp6 [param1 (++ temp8)])
					)
					(29
						(= temp7 [param1 (++ temp8)])
					)
				)
				(++ temp8)
			)
			(= temp8
				(Display
					[param1 0]
					dsCOORD
					temp4
					temp5
					dsCOLOR
					temp7
					dsWIDTH
					temp3
					dsALIGN
					temp0
					dsFONT
					temp2
					dsSAVEPIXELS
				)
			)
			(Display
				[param1 0]
				dsCOORD
				temp4
				temp5
				dsCOLOR
				temp6
				dsWIDTH
				temp3
				dsALIGN
				temp0
				dsFONT
				temp1
			)
			(return temp8)
		)
	)
)

(procedure (proc5_13 param1 &tmp temp0 theIconBarUseIconItem)
	(= theIconBarUseIconItem (theIconBar useIconItem?))
	(if (not (theIconBar curInvIcon?))
		(theIconBar enable: theIconBarUseIconItem)
	)
	(= temp0 (Inventory at: param1))
	(theIconBar
		curInvIcon: temp0
		select: theIconBarUseIconItem
		curIcon: theIconBarUseIconItem
	)
	(theGame setCursor: (temp0 cursor?) 1)
)

(procedure (proc5_14 &tmp [temp0 500])
	(Format @temp0 &rest)
	(Print @temp0 #at -1 12)
)

(procedure (proc5_15 param1 param2)
	(if (== argc 2)
		(param1 init: dummyClient 0 param2)
	else
		(param1 init: dummyClient 0 0)
	)
	(theIconBar disable:)
	(while (param1 client?)
		(param1 doit:)
		(theGame doit:)
	)
	(theIconBar enable:)
	(param1 dispose:)
)

(procedure (proc5_16 param1 param2 param3 param4 param5 &tmp temp0 temp1 temp2 temp3 temp4)
	(= temp1 (Min param1 param3))
	(= temp0 (Min param2 param4))
	(= temp3 (Max param1 param3))
	(= temp2 (Max param2 param4))
	(if (<= param1 param3)
		(Graph
			grDRAW_LINE
			param2
			param1
			param4
			param3
			param5
			-1
			-1
		)
	else
		(Graph
			grDRAW_LINE
			param4
			param3
			param2
			param1
			param5
			-1
			-1
		)
	)
	(Graph
		grUPDATE_BOX
		(- temp0 1)
		(- temp1 1)
		(+ temp2 1)
		(+ temp3 1)
		1
	)
)

(procedure (proc5_17 param1 param2 param3 param4 &tmp temp0 temp1 temp2 temp3)
	(if (<= param1 param3)
		(Graph grDRAW_LINE param2 param1 param4 param3 0 -1 -1)
	else
		(Graph grDRAW_LINE param4 param3 param2 param1 0 -1 -1)
	)
	(= temp1 (Min param1 param3))
	(= temp0 (Min param2 param4))
	(= temp3 (Max param1 param3))
	(= temp2 (Max param2 param4))
	(Graph
		grUPDATE_BOX
		(- temp0 1)
		(- temp1 1)
		(+ temp2 1)
		(+ temp3 1)
		1
	)
)

(procedure (localproc_01b3 param1 param2 param3 &tmp temp0 [temp1 100])
	(if (u< (= temp0 param3) 1000)
		(= temp0 @temp1)
		(Format @temp1 param3 &rest)
	)
	(if param1
		(= local0 (StrLen temp0))
	else
		(TextSize @[local1 0] temp0 param2 0)
	)
)

(class dummyClient of Object
	(properties
		script 0
	)
)
