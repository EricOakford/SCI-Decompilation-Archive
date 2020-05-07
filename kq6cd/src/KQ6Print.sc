;;; Sierra Script 1.0 - (do not remove this comment)
(script# 104)
(include sci.sh)
(use Main)
(use Intrface)
(use Kq6Talker)
(use Print)
(use DIcon)
(use System)


(local
	theCurRoomNum_2
	theTheTheCurRoomNum_2_2
	theTheTheCurRoomNum_2_3
	theTheTheCurRoomNum_2_4
	local4
)
(instance myDialog of Dialog
	(properties)
	
	(method (dispose)
		(if (not (== (DoAudio 6) -1)) (DoAudio 3))
		(super dispose: &rest)
	)
	
	(method (handleEvent)
		(return
			(if (== (DoAudio 6) -1)
				(return -2)
			else
				(return (super handleEvent: &rest))
			)
		)
	)
	
	(method (check)
		(return
			(if (or (super check:) (== (DoAudio 6) -1))
				(return 1)
			else
				0
			)
		)
	)
)

(class KQ6Print of Print
	(properties
		dialog 0
		window 0
		title 0
		mode 0
		font -1
		width 0
		x -1
		y -1
		ticks 0
		caller 0
		retValue 0
		modeless 0
		first 0
		saveCursor 0
		repressText 0
	)
	
	(method (init theCaller)
		(= caller 0)
		(if argc (= caller theCaller))
		(if (> argc 1) (self addText: &rest))
		(if (not modeless)
			(if (not (IsObject prints))
				(= prints ((EventHandler new:) name: {prints}))
			)
			(prints add: self)
		)
		(if (& msgType $0002)
			(DoAudio
				2
				theCurRoomNum_2
				theTheTheCurRoomNum_2_2
				theTheTheCurRoomNum_2_3
				theTheTheCurRoomNum_2_4
				local4
			)
		)
		(if modeless (theIconBar disable: 6))
		(self showSelf:)
	)
	
	(method (dispose)
		(if modeless (theIconBar enable: 6))
		(= repressText 0)
		(if (& msgType $0002) (DoAudio 3))
		(super dispose: &rest)
	)
	
	(method (showSelf &tmp theFirst temp1 temp2 temp3 temp4 theTheCursor temp6)
		(if
			(and
				(= temp6 (if repressText (& msgType $0002)))
				(theGame isHandsOn?)
			)
			(= theTheCursor theCursor)
			(theGame setCursor: waitCursor)
		else
			(= theTheCursor 0)
		)
		(if saveCursor (theGame setCursor: 999))
		(if (not dialog)
			(if temp6
				(= dialog myDialog)
			else
				(= dialog (Dialog new:))
			)
		)
		(dialog
			window: (if window else systemWindow)
			name: {PODialog}
			caller: self
		)
		(dialog text: title time: ticks setSize:)
		(dialog center:)
		(= temp3 (if (== x -1) (dialog nsLeft?) else x))
		(= temp4 (if (== y -1) (dialog nsTop?) else y))
		(dialog moveTo: temp3 temp4)
		(= temp1 (GetPort))
		(if (not repressText)
			(dialog open: (if title 4 else 0) 15)
		)
		(return
			(if modeless
				(= modelessPort (GetPort))
				(SetPort temp1)
				(= modelessDialog dialog)
				(if theTheCursor (theGame handsOn:))
			else
				(sounds pause: 1)
				(cond 
					((not (= theFirst first))
						(if
							(and
								(= theFirst (dialog firstTrue: #checkState 1))
								(not (dialog firstTrue: #checkState 2))
							)
							(theFirst state: (| (theFirst state?) $0002))
						)
					)
					((not (IsObject theFirst)) (= theFirst (dialog at: theFirst)))
				)
				(= retValue (dialog doit: theFirst))
				(SetPort temp1)
				(cond 
					((== retValue -1) (= retValue 0))
					(
					(and (IsObject retValue) (retValue isKindOf: DButton)) (= retValue (retValue value?)))
					((not (dialog theItem?)) (= retValue 1))
				)
				(if saveCursor
					(theGame setCursor: ((theIconBar curIcon?) cursor?))
				)
				(dialog dispose:)
				(if theTheCursor (theGame setCursor: theTheCursor))
				(return retValue)
			)
		)
	)
	
	(method (addText theTheCurRoomNum &tmp theTheTheCurRoomNum theTheTheCurRoomNum_2 theTheTheCurRoomNum_3 temp3 theTheTheCurRoomNum_4 theTheTheCurRoomNum_5 theCurRoomNum temp7 temp8 temp9 temp10 temp11)
		(= repressText 0)
		(if (not dialog) (= dialog (Dialog new:)))
		(if (== font -1) (= font userFont))
		(if (> argc 3)
			(= theTheTheCurRoomNum [theTheCurRoomNum 0])
			(= theTheTheCurRoomNum_2 [theTheCurRoomNum 1])
			(= theTheTheCurRoomNum_3 [theTheCurRoomNum 2])
			(= temp3
				(if [theTheCurRoomNum 3] [theTheCurRoomNum 3] else 1)
			)
			(= theTheTheCurRoomNum_4 0)
			(= theTheTheCurRoomNum_5 0)
			(= theCurRoomNum curRoomNum)
			(if (>= argc 5)
				(= theTheTheCurRoomNum_4 [theTheCurRoomNum 4])
				(if (>= argc 6)
					(= theTheTheCurRoomNum_5 [theTheCurRoomNum 5])
					(if (>= argc 7) (= theCurRoomNum [theTheCurRoomNum 6]))
				)
			)
			(if
				(= temp8
					(Message
						msgSIZE
						theCurRoomNum
						theTheTheCurRoomNum
						theTheTheCurRoomNum_2
						theTheTheCurRoomNum_3
						temp3
					)
				)
				(= temp7 (Memory memALLOC_CRIT temp8))
				(if
					(Message
						msgGET
						theCurRoomNum
						theTheTheCurRoomNum
						theTheTheCurRoomNum_2
						theTheTheCurRoomNum_3
						temp3
						temp7
					)
					(if
					(and (>= 90 (= temp9 (StrAt temp7 0))) (>= temp9 65))
						(StrAt temp7 0 9)
						(= temp10 (+ 0 (/ (- temp9 65) 13)))
						(= temp11 (mod (- temp9 65) 13))
						(dialog
							add:
								((DText new:)
									text: temp7
									font: font
									mode: mode
									setSize: width
									moveTo: (+ 4 theTheTheCurRoomNum_4) (+ 4 theTheTheCurRoomNum_5 7)
									yourself:
								)
							add:
								((DIcon new:)
									view: (Kq6Narrator strView?)
									loop: temp10
									cel: temp11
									setSize:
									moveTo: (+ theTheTheCurRoomNum_4 4) (+ theTheTheCurRoomNum_5 4)
									yourself:
								)
							setSize:
						)
					else
						(dialog
							add:
								((DText new:)
									text: temp7
									font: font
									mode: mode
									setSize: width
									moveTo: (+ 4 theTheTheCurRoomNum_4) (+ 4 theTheTheCurRoomNum_5)
									yourself:
								)
							setSize:
						)
					)
				)
			)
		else
			(= theTheTheCurRoomNum_4 0)
			(= theTheTheCurRoomNum_5 0)
			(if (>= argc 2)
				(= theTheTheCurRoomNum_4 [theTheCurRoomNum 1])
				(if (>= argc 3)
					(= theTheTheCurRoomNum_5 [theTheCurRoomNum 2])
				)
			)
			(= temp7
				(Memory
					memALLOC_CRIT
					(+ (StrLen [theTheCurRoomNum 0]) 1)
				)
			)
			(StrCpy temp7 [theTheCurRoomNum 0])
			(dialog
				add:
					((DText new:)
						text: temp7
						font: font
						mode: mode
						setSize: width
						moveTo: (+ 4 theTheTheCurRoomNum_4) (+ 4 theTheTheCurRoomNum_5)
						yourself:
					)
				setSize:
			)
		)
	)
	
	(method (say param1 theTheTheTheCurRoomNum_2_2 &tmp theTheTheTheTheCurRoomNum_2_2 theTheTheTheTheCurRoomNum_2_2_2)
		(if (& msgType $0002)
			(= theTheTheCurRoomNum_2_2 [theTheTheTheCurRoomNum_2_2 0])
			(= theTheTheCurRoomNum_2_3 [theTheTheTheCurRoomNum_2_2 1])
			(= theTheTheCurRoomNum_2_4 [theTheTheTheCurRoomNum_2_2 2])
			(= local4
				(if [theTheTheTheCurRoomNum_2_2 3]
					[theTheTheTheCurRoomNum_2_2 3]
				else
					1
				)
			)
			(= theTheTheTheTheCurRoomNum_2_2 0)
			(= theTheTheTheTheCurRoomNum_2_2_2 0)
			(= theCurRoomNum_2 curRoomNum)
			(if (>= argc 5)
				(= theTheTheTheTheCurRoomNum_2_2
					[theTheTheTheCurRoomNum_2_2 4]
				)
				(if (>= argc 6)
					(= theTheTheTheTheCurRoomNum_2_2_2
						[theTheTheTheCurRoomNum_2_2 5]
					)
					(if (>= argc 7)
						(= theCurRoomNum_2 [theTheTheTheCurRoomNum_2_2 6])
					)
				)
			)
			(if (== param1 1)
				(self addText: theTheTheTheCurRoomNum_2_2 &rest)
			else
				(= repressText 1)
			)
		else
			(self addText: theTheTheTheCurRoomNum_2_2 &rest)
		)
	)
)
