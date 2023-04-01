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
(class Ware ;of RootObj
	(properties
		price 0
	)
	
	(method (dispose)
		(DisposeClone self)
	)
)

(instance buy of Dialog
	(method (init &tmp h v temp2 val wareText node obj)
		(= oldCur theCursor)
		(theGame setCursor: ARROW_CURSOR)
		(= h (= v MARGIN))
		(= val 0)
		(if (!= curRoomNum rTownOutside)
			((= theBuyDialog (DText new:))
				text: {You may buy:}
				setSize:
				moveTo: MARGIN v
			)
		else
			((= theBuyDialog (DText new:))
				text: {You may give:}
				setSize:
				moveTo: MARGIN v
			)
		)
		(self add: theBuyDialog setSize:)
		(= v
			(+ v (- (theBuyDialog nsBottom?) (theBuyDialog nsTop?)) 1)
		)
		(= node (wareList first:))
		(while node
			(= obj (NodeValue node))
			(++ val)
			(self
				add:
					((= wareText (DText new:))
						value: val
						text: (obj name?)
						nsLeft: h
						nsTop: v
						state: (| dActive dExit)
						setSize:
						yourself:
					)
			)
			(if (!= curRoomNum rTownOutside)
				(self
					add:
						((DText new:)
							text: (obj price?)
							nsLeft: (+ h 120)
							nsTop: v
							setSize:
							yourself:
						)
				)
				(self
					add:
						((DText new:)
							text: (if (StrCmp (obj price?) {1}) {Silvers} else {Silver})
							nsLeft: (+ h 140)
							nsTop: v
							setSize:
							yourself:
						)
				)
			)
			(= v
				(+ v (- (wareText nsBottom?) (wareText nsTop?)) 1)
			)
			(= node (wareList next: node))
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
		(return val)
	)
	
	(method (doit &tmp obj haveG haveS costS temp4 temp5)
		(self init:)
		(self open: wTitled 15)
		(= haveS ((inventory at: iSilver) amount?))
		(= temp4 (/ ((inventory at: iSilver) amount?) 10))
		(= haveG (* ((inventory at: iGold) amount?) 10))
		(= obj theBuyDialog)
		(= obj (super doit: obj))
		(if
			(or
				(not (IsObject obj))
				(== obj theBuyDialog)
			)
			(self dispose: 0)
		else
			(= temp5
				(not
					(mod
						(= costS
							(ReadNumber
								((wareList at: (- (obj value?) 1)) price?)
							)
						)
						10
					)
				)
			)
			(cond 
				((< (+ haveG haveS) costS)
					;not enough money
					(self dispose: -1)
				)
				((== (+ haveG haveS) costS)
					;has exactly enough
					((inventory at: iSilver) amount: 0)
					((inventory at: iGold) amount: 0)
					(ego use: iSilver 0)
					(self dispose: (obj value?))
				)
				((> haveS costS)
					((inventory at: iSilver)
						amount: (- ((inventory at: iSilver) amount?) costS)
					)
					(self dispose: (obj value?))
				)
				(temp4
					((inventory at: iSilver)
						amount: (- ((inventory at: iSilver) amount?) (* temp4 10))
					)
					(-= costS (* temp4 10))
					((inventory at: iGold)
						amount:
							(-
								((inventory at: iGold) amount?)
								(+ (/ costS 10) (if temp5 0 else 1))
							)
					)
					(if (not temp5)
						((inventory at: iSilver)
							amount: (+ ((inventory at: iSilver) amount?) (- 10 (mod costS 10)))
						)
					)
					(cond 
						(
							(and
								(== ((inventory at: iSilver) amount?) 0)
								(== ((inventory at: iGold) amount?) 0)
							)
							(ego use: iSilver 0)
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
					(self dispose: (obj value?))
				)
				(else
					((inventory at: iGold)
						amount:
							(-
								((inventory at: iGold) amount?)
								(+ (/ costS 10) (if temp5 0 else 1))
							)
					)
					(if (not temp5)
						((inventory at: iSilver)
							amount: (+ ((inventory at: iSilver) amount?) (- 10 (mod costS 10)))
						)
					)
					(cond 
						(
							(and
								(== ((inventory at: iSilver) amount?) 0)
								(== ((inventory at: iGold) amount?) 0)
							)
							(ego use: iSilver 0)
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
					(self dispose: (obj value?))
				)
			)
		)
	)
	
	(method (dispose ret)
		(self eachElementDo: #dispose 1)
		(super dispose:)
		(wareList dispose:)
		(theGame setCursor: oldCur)
		(return ret)
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
