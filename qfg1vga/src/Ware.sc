;;; Sierra Script 1.0 - (do not remove this comment)
(script# WARE)
(include game.sh)
(use Main)
(use Intrface)
(use Dialog)

(public
	buy 0
)

(local
	oldCur
)
(class Ware
	(properties
		price 0
	)
	
	(method (dispose)
		(DisposeClone self)
	)
)

(instance buy of Dialog
	(method (init &tmp temp0 temp1 temp2 temp3 newDText gNewListFirst temp6)
		(= oldCur theCursor)
		(theGame setCursor: ARROW_CURSOR)
		(= temp0 (= temp1 4))
		(= temp3 0)
		(if (!= curRoomNum 65)
			((= theBuyDialog (DText new:))
				text: {You may buy:}
				setSize:
				moveTo: 4 temp1
			)
		else
			((= theBuyDialog (DText new:))
				text: {You may give:}
				setSize:
				moveTo: 4 temp1
			)
		)
		(self add: theBuyDialog setSize:)
		(= temp1
			(+
				temp1
				(- (theBuyDialog nsBottom?) (theBuyDialog nsTop?))
				1
			)
		)
		(= gNewListFirst (wareList first:))
		(while gNewListFirst
			(= temp6 (NodeValue gNewListFirst))
			(++ temp3)
			(self
				add:
					((= newDText (DText new:))
						value: temp3
						text: (temp6 name?)
						nsLeft: temp0
						nsTop: temp1
						state: 3
						setSize:
						yourself:
					)
			)
			(if (!= curRoomNum 65)
				(self
					add:
						((DText new:)
							text: (temp6 price?)
							nsLeft: (+ temp0 120)
							nsTop: temp1
							setSize:
							yourself:
						)
				)
				(self
					add:
						((DText new:)
							text: (if (StrCmp (temp6 price?) {1}) {Silvers} else {Silver})
							nsLeft: (+ temp0 140)
							nsTop: temp1
							setSize:
							yourself:
						)
				)
			)
			(= temp1
				(+ temp1 (- (newDText nsBottom?) (newDText nsTop?)) 1)
			)
			(= gNewListFirst (wareList next: gNewListFirst))
		)
		(= window systemWindow)
		(self setSize:)
		(= theBuyDialog (DButton new:))
		(if (!= curRoomNum 65)
			(theBuyDialog
				text: {Buy nothing}
				setSize:
				moveTo: (- nsRight (+ 4 (theBuyDialog nsRight?))) nsBottom
			)
		else
			(theBuyDialog
				text: {Give nothing}
				setSize:
				moveTo: (- nsRight (+ 4 (theBuyDialog nsRight?))) nsBottom
			)
		)
		(theBuyDialog
			move: (- (theBuyDialog nsLeft?) (theBuyDialog nsRight?)) 0
		)
		(self add: theBuyDialog setSize: center:)
		(return temp3)
	)
	
	(method (doit &tmp theGNewDText temp1 temp2 temp3 temp4 temp5)
		(self init:)
		(self open: 4 15)
		(= temp2 ((inventory at: iSilver) amount?))
		(= temp4 (/ ((inventory at: iSilver) amount?) 10))
		(= temp1 (* ((inventory at: iGold) amount?) 10))
		(= theGNewDText theBuyDialog)
		(= theGNewDText (super doit: theGNewDText))
		(if
			(or
				(not (IsObject theGNewDText))
				(== theGNewDText theBuyDialog)
			)
			(self dispose: 0)
		else
			(= temp5
				(not
					(mod
						(= temp3
							(ReadNumber
								((wareList at: (- (theGNewDText value?) 1)) price?)
							)
						)
						10
					)
				)
			)
			(cond 
				((< (+ temp1 temp2) temp3) (self dispose: -1))
				((== (+ temp1 temp2) temp3)
					((inventory at: iSilver) amount: 0)
					((inventory at: iGold) amount: 0)
					(ego use: 0 0)
					(self dispose: (theGNewDText value?))
				)
				((> temp2 temp3)
					((inventory at: iSilver)
						amount: (- ((inventory at: iSilver) amount?) temp3)
					)
					(self dispose: (theGNewDText value?))
				)
				(temp4
					((inventory at: iSilver)
						amount: (- ((inventory at: iSilver) amount?) (* temp4 10))
					)
					(= temp3 (- temp3 (* temp4 10)))
					((inventory at: iGold)
						amount:
							(-
								((inventory at: iGold) amount?)
								(+ (/ temp3 10) (if temp5 0 else 1))
							)
					)
					(if (not temp5)
						((inventory at: iSilver)
							amount: (+ ((inventory at: iSilver) amount?) (- 10 (mod temp3 10)))
						)
					)
					(cond 
						(
							(and
								(== ((inventory at: iSilver) amount?) 0)
								(== ((inventory at: iGold) amount?) 0)
							)
							(ego use: 0 0)
						)
						(
							(and
								(== ((inventory at: iSilver) amount?) 0)
								(> ((inventory at: iGold) amount?) 0)
							)
							((inventory at: iSilver) amount: 10)
							((inventory at: iGold)
								amount: (- ((inventory at: iGold) amount?) 1)
							)
						)
					)
					(self dispose: (theGNewDText value?))
				)
				(else
					((inventory at: iGold)
						amount:
							(-
								((inventory at: iGold) amount?)
								(+ (/ temp3 10) (if temp5 0 else 1))
							)
					)
					(if (not temp5)
						((inventory at: iSilver)
							amount: (+ ((inventory at: iSilver) amount?) (- 10 (mod temp3 10)))
						)
					)
					(cond 
						(
							(and
								(== ((inventory at: iSilver) amount?) 0)
								(== ((inventory at: iGold) amount?) 0)
							)
							(ego use: 0 0)
						)
						(
							(and
								(== ((inventory at: iSilver) amount?) 0)
								(> ((inventory at: iGold) amount?) 0)
							)
							((inventory at: iSilver) amount: 10)
							((inventory at: iGold)
								amount: (- ((inventory at: iGold) amount?) 1)
							)
						)
					)
					(self dispose: (theGNewDText value?))
				)
			)
		)
	)
	
	(method (dispose param1)
		(self eachElementDo: #dispose 1)
		(super dispose:)
		(wareList dispose:)
		(theGame setCursor: oldCur)
		(return param1)
	)
	
	(method (handleEvent event &tmp eMsg eType)
		(= eMsg (event message?))
		(switch (= eType (event type?))
			(keyDown
				(switch eMsg
					(UPARROW
						(= eMsg SHIFTTAB)
					)
					(DOWNARROW
						(= eMsg TAB)
					)
				)
			)
			(direction
				(switch eMsg
					(dirN
						(= eMsg SHIFTTAB)
						(= eType keyDown)
					)
					(dirS
						(= eMsg TAB)
						(= eType keyDown)
					)
				)
			)
		)
		(event type: eType message: eMsg)
		(super handleEvent: event)
	)
)
