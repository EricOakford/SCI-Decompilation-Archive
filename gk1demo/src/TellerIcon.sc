;;; Sierra Script 1.0 - (do not remove this comment)
(script# TELLER)
(include game.sh)
(use Main)
(use IconBar)
(use GControl)
(use Window)
(use System)

(public
	Teller 0
)

(class TellerIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 0
		signal (| VICON RELVERIFY)
		myHandle 0
		value 0
		myTeller 0
		titleColor 255
		menuFont 30
		lineSpace 8
		blankSpace 2
		buttonSize 80
	)
	
	(method (show &tmp [str 35])
		(if (myTeller title?)
			(Message MsgGet 920 0 0 (myTeller title?) 1 @str)
			(Display @str
				p_at 5 blankSpace
				p_color titleColor
				p_font menuFont
			)
		)
		(= nsRight buttonSize)
		(= nsBottom (+ nsTop lineSpace))
		(Display myHandle
			p_at 10 (+ nsTop blankSpace)
			p_color lowlightColor
			p_font menuFont
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(myTeller iconValue: value)
		((myTeller theControls?)
			state: (& ((myTeller theControls?) state?) $ffdf)
		)
	)
	
	(method (highlight tOrF &tmp sColor)
		(Display myHandle
			p_at 10 (+ nsTop blankSpace)
			p_color (if tOrF highlightColor else lowlightColor)
			p_font menuFont
		)
	)
)

(class Teller of Object
	(properties
		curNoun 0
		sayNoun 0
		verb 0
		modNum 0
		stack 0
		curList 0
		client 0
		iconValue 0
		theControls 0
		title 0
		window 0
	)
	
	(method (init whoCares theMod theNoun theVerb)
		((= client whoCares) actions: self)
		(if (> argc 1)
			(= modNum theMod)
			(if (> argc 2)
				(= sayNoun theNoun)
				(if (> argc 3)
					(= verb theVerb)
				)
			)
		else
			(= modNum curRoomNum)
		)
		((= stack (List new:))
			addToFront: (= curNoun (client noun?))
		)
	)
	
	(method (dispose)
		(if (IsObject stack)
			(stack dispose:)
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_ASK)
			(repeat
				(if (self respond:)
					(break)
				)
			)
		else
			(client doVerb: theVerb)
		)
	)
	
	(method (respond &tmp theNoun)
		(= iconValue 0)
		(self buildCaseList: showCases:)
		(curList dispose:)
		(= curList 0)
		(if (or (not iconValue) (== iconValue -999))
			(return TRUE)
		)
		(if (== iconValue 999)
			(stack delete: curNoun)
			(= curNoun (stack at: 0))
			(return FALSE)
		)
		(self sayMessage:)
		(if
			(and
				(Message MsgSize modNum curNoun verb iconValue 2)
				(>
					(= theNoun
						(Message MsgGetRefNoun modNum curNoun verb iconValue 2)
					)
					-1
				)
			)
			(= curNoun theNoun)
			(stack addToFront: curNoun)
		)
		(return TRUE)
	)
	
	(method (sayMessage)
		(messager say: sayNoun verb iconValue 0)
	)
	
	(method (buildCaseList &tmp theCase)
		(if curList
			(curList dispose:)
		)
		(= curList (List new:))
		(= theCase 0)
		(while (< theCase 150)
			(if
				(Message MsgSize modNum curNoun verb theCase (self getSeqNum:))
				(curList add: theCase)
			)
			(++ theCase)
		)
	)
	
	(method (showCases param1 &tmp
				temp0 temp1 temp2 newTellerIcon temp4 temp5 temp6
				temp7 newList temp9 temp10 temp11 temp12 temp13
				temp14 [buffer 30])
		(= temp14 150)
		(= temp2 0)
		(= temp4 5)
		(= temp7 0)
		(= newTellerIcon 0)
		(= newList (List new:))
		((= theControls (GameControls new:))
			window:
				((if (IsObject window)
					(window new:)
				else
					(SysWindow new:)
				)
					priority: 15
					yourself:
				)
		)
		(if title (= temp4 (+ temp4 15)))
		(= temp1 0)
		(while (< temp1 (curList size?))
			(= temp5 1)
			(= temp2 0)
			(while (and temp5 (< temp2 argc))
				(if
					(and
						(== (curList at: temp1) [param1 temp2])
						(not [param1 (+ temp2 1)])
					)
					(= temp5 0)
				)
				(= temp2 (+ temp2 2))
			)
			(if temp5
				(= temp6 (curList at: temp1))
				(= temp13 (self getSeqNum: temp6))
				(if
					(not
						(= temp12
							(Message MsgSize modNum curNoun verb temp6 temp13)
						)
					)
					(break)
				)
				(= temp0 (Memory MNewPtr temp12))
				(if (> (= temp12 (+ (* temp12 7) 20)) temp14)
					(= temp14 temp12)
				)
				(Message MsgGet modNum curNoun verb temp6 temp13 temp0)
				(newList add: temp0)
				((= newTellerIcon (TellerIcon new:))
					myHandle: temp0
					value: temp6
					nsTop: temp4
					myTeller: self
				)
				(theControls add: newTellerIcon)
				(++ temp7)
				(= temp4 (+ temp4 15))
			)
			(++ temp1)
		)
		(if (not (IsObject newTellerIcon))
			(client doVerb: V_ASK)
			((theControls window?) dispose:)
			(theControls dispose:)
			(newList dispose:)
			(return -999)
		)
		((= newTellerIcon (TellerIcon new:))
			nsTop: temp4
			myTeller: self
		)
		(if (not (== curNoun (client noun?)))
			(Message MsgGet TELLER NULL NULL 2 1 @buffer)
			(newTellerIcon myHandle: @buffer value: 999)
		else
			(Message MsgGet TELLER NULL NULL 3 1 @buffer)
			(newTellerIcon myHandle: @buffer value: -999)
		)
		(theControls add: newTellerIcon)
		((theControls window?)
			top: (- 60 (* 7 temp7))
			bottom: (- (+ temp4 80) (* 7 temp7))
			left: (- 160 (/ temp14 2))
			right: (+ 160 (/ temp14 2))
		)
		(theControls init: show: dispose:)
		(= temp9 (FirstNode (newList elements?)))
		(while temp9
			(= temp10 (NextNode temp9))
			(if (not (= temp11 (NodeValue temp9))) (break))
			(Memory MDisposePtr temp11)
			(= temp9 temp10)
		)
		(return (newList dispose:))
	)
	
	(method (getSeqNum)
		(return TRUE)
	)
	
	(method (cue)
	)
)
