;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51)
(include game.sh)
(use Main)
(use Procs)
(use TellerIcon)
(use Print)
(use GControl)
(use Window)
(use System)

(public
	GKTeller 0
	RoomTeller 1
)

(procedure (localproc_098c param1 param2 param3 &tmp temp0 temp1)
	(= temp0 (param1 at: param2))
	(= temp1 (FirstNode (param1 elements?)))
	(while (and param2 temp1)
		(-- param2)
		(= temp1 (Memory MReadWord temp1))
	)
	(if temp1
		(Memory MWriteWord (+ temp1 4) param3)
		(Memory MWriteWord (+ temp1 6) param3)
	)
	(return temp0)
)

(class RoomTeller of Teller
	(properties
		curNoun 0
		sayNoun 7
		verb 0
		modNum 50
		stack 0
		curList 0
		client 0
		iconValue 0
		theControls 0
		title 1
		window 0
		topNoun 0
		index 0
		transcript 0
		personals 0
		curTopic -1
		view 0
		loop 0
		cel 0
		left 222
		top 85
		color 54
		hiColor 7
	)
	
	(method (init theIndex theVerb theCurNoun theModNum &tmp temp0)
		(= index theIndex)
		(if (> argc 1)
			(= verb theVerb)
			(if (> argc 2)
				(= curNoun theCurNoun)
				(if (> argc 3) (= modNum theModNum))
			)
		)
		((= stack (List new:)) addToFront: (= topNoun curNoun))
		(if view
			(DrawCel view loop cel left top 13)
			((RestoreCel new:) init: view loop cel left top 13)
		)
		(self setPersonals:)
		(return self)
	)
	
	(method (dispose)
		(if (IsObject curList) (curList dispose:))
		(if (IsObject transcript) (transcript dispose:))
		(personals dispose:)
		(super dispose:)
	)
	
	(method (doVerb)
		(repeat
			(if (self respond:) (break))
		)
		(if (or (not iconValue) (== iconValue -999))
			(curRoom cue:)
		)
	)
	
	(method (respond &tmp theCurNoun temp1 temp2 temp3 personalsSize)
		(= temp1 (== topNoun curNoun))
		(= iconValue 0)
		(self buildCaseList: showCases:)
		(curList dispose:)
		(= curList 0)
		(if (or (not iconValue) (== iconValue -999))
			(return 1)
		)
		(if (== iconValue 999)
			(stack delete: curNoun)
			(= curNoun (stack at: 0))
			(return 0)
		)
		(if temp1
			(= curTopic
				(if (and (<= 1 iconValue) (<= iconValue 16))
					iconValue
				else
					(= personalsSize (personals size?))
					(= temp3 0)
					(= temp2 0)
					(while (< temp2 personalsSize)
						(if (== iconValue (personals at: temp2))
							(= temp3 (+ 17 temp2))
							(break)
						)
						(++ temp2)
					)
					temp3
				)
			)
		)
		(self sayMessage:)
		(if
			(and
				(Message MsgSize modNum curNoun verb iconValue 2)
				(>
					(= theCurNoun
						(Message MsgGetRefNoun modNum curNoun verb iconValue 2)
					)
					-1
				)
			)
			(= curNoun theCurNoun)
			(stack addToFront: curNoun)
		)
		(return 1)
	)
	
	(method (sayMessage &tmp temp0)
		(if (IsObject transcript)
			(localproc_098c
				(transcript at: 0)
				(= temp0 (- curTopic 1))
				(+ 1 ((transcript at: 0) at: temp0))
			)
			(if (not (transcript at: curTopic))
				(localproc_098c transcript curTopic (List new:))
			)
			((transcript at: curTopic) addToEnd: iconValue)
		)
		(messager say: sayNoun verb iconValue 0 self)
	)
	
	(method (showCases param1 &tmp temp0 temp1 temp2 newTellerIcon temp4 temp5 temp6 temp7 newList temp9 temp10 temp11 temp12 temp13 temp14 [temp15 30] theHiColor theColor temp47)
		(= temp14 50)
		(= temp2 0)
		(= temp4 0)
		(= temp7 0)
		(= newTellerIcon 0)
		(= temp47 (== topNoun curNoun))
		(= newList (List new:))
		((= theControls (GameControls new:))
			window:
				((if (IsObject window) (window new:) else (Window new:))
					priority: 15
					back: 0
					yourself:
				)
		)
		(if title (= temp4 (+ temp4 8)))
		(if temp47
			(= temp1 1)
			(while (< temp1 16)
				(if (Btst (+ temp1 36))
					(if
					(not (= temp12 (Message MsgSize modNum 3 0 temp1 1)))
						(break)
					)
					(= temp0 (Memory MNewPtr temp12))
					(if (> (= temp12 (+ (* temp12 5) 5)) temp14)
						(= temp14 temp12)
					)
					(Message MsgGet modNum 3 0 temp1 1 temp0)
					(newList add: temp0)
					((= newTellerIcon (TellerIcon new:))
						myHandle: temp0
						value: temp1
						nsTop: temp4
						myTeller: self
						highlightColor: 7
						lowlightColor: 54
						titleColor: 7
						buttonSize: temp12
					)
					(theControls add: newTellerIcon)
					(++ temp7)
					(= temp4 (+ temp4 8))
				)
				(++ temp1)
			)
		)
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
				(if (> (= temp12 (+ (* temp12 5) 5)) temp14)
					(= temp14 temp12)
				)
				(Message MsgGet modNum curNoun verb temp6 temp13 temp0)
				(newList add: temp0)
				(if temp47
					(= theHiColor hiColor)
					(= theColor color)
				else
					(= theHiColor 7)
					(= theColor 54)
				)
				((= newTellerIcon (TellerIcon new:))
					myHandle: temp0
					value: temp6
					nsTop: temp4
					myTeller: self
					highlightColor: theHiColor
					lowlightColor: theColor
					titleColor: 7
					buttonSize: temp12
				)
				(theControls add: newTellerIcon)
				(++ temp7)
				(= temp4 (+ temp4 8))
			)
			(++ temp1)
		)
		(if (not (IsObject newTellerIcon))
			((theControls window?) dispose:)
			(theControls dispose:)
			(newList dispose:)
			(return -999)
		)
		(if (== curNoun topNoun)
			((= newTellerIcon (TellerIcon new:))
				nsTop: temp4
				myTeller: self
				highlightColor: 7
				lowlightColor: 54
				titleColor: 7
				buttonSize: temp14
			)
			(Message MsgGet 920 0 0 3 1 @temp15)
			(newTellerIcon myHandle: @temp15 value: -999)
		)
		(theControls add: newTellerIcon)
		(= temp1 (* 8 (+ temp7 2)))
		(if temp47
			((theControls window?)
				top: (/ (- 190 temp1) 2)
				bottom: (/ (+ 190 temp1) 2)
				left: (- 160 (/ temp14 2))
				right: (+ 160 (/ temp14 2))
			)
		else
			((theControls window?)
				top: 10
				bottom: (+ 10 temp1)
				left: 100
				right: 310
			)
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
	
	(method (cue)
		(self doVerb:)
	)
	
	(method (goTop)
		(if (IsObject stack) (stack dispose:))
		((= stack (List new:)) addToFront: (= curNoun topNoun))
	)
	
	(method (setPersonals)
		(Prints {Hey, some dummy forgot to redefine setPersonals})
	)
)

(class GKTeller of Teller
	(properties
		curNoun -1
		sayNoun -1
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
	
	(method (init theClient theCurNoun theVerb theSayNoun theModNum)
		(= modNum curRoomNum)
		(if (and argc (IsObject theClient))
			((= client theClient) actions: self)
		)
		(if (and (== curNoun -1) (IsObject client))
			(= curNoun (client noun?))
		)
		(if (> argc 1)
			(= curNoun theCurNoun)
			(if (> argc 2)
				(= verb theVerb)
				(if (> argc 3)
					(= sayNoun theSayNoun)
					(if (> argc 4) (= modNum theModNum))
				)
			)
		)
		((= stack (List new:)) addToFront: curNoun)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(SetCursor -2)
		(= temp0 (theGame setCursor: 999))
		(if (== theVerb 11)
			(repeat
				(if (self respond:) (break))
			)
		else
			(client doVerb: theVerb)
		)
		(theGame setCursor: temp0)
		(SetCursor 0 0 155 319)
		(return 1)
	)
	
	(method (sayMessage)
		(messager say: sayNoun verb iconValue 0 self)
	)
	
	(method (showCases param1 &tmp temp0 temp1 temp2 newTellerIcon temp4 temp5 temp6 temp7 newList temp9 temp10 temp11 temp12 temp13 temp14 [temp15 30])
		(= temp14 150)
		(= temp2 0)
		(= temp4 0)
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
		(if title (= temp4 (+ temp4 8)))
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
				(if (> (= temp12 (+ (* temp12 5) 5)) temp14)
					(= temp14 temp12)
				)
				(Message MsgGet modNum curNoun verb temp6 temp13 temp0)
				(newList add: temp0)
				((= newTellerIcon (TellerIcon new:))
					myHandle: temp0
					value: temp6
					nsTop: temp4
					myTeller: self
					buttonSize: temp12
				)
				(theControls add: newTellerIcon)
				(++ temp7)
				(= temp4 (+ temp4 8))
			)
			(++ temp1)
		)
		(if (not (IsObject newTellerIcon))
			(if (IsObject client) (client doVerb: 10))
			((theControls window?) dispose:)
			(theControls dispose:)
			(newList dispose:)
			(return -999)
		)
		((theControls window?)
			top: (- 190 (* 10 temp7))
			bottom: 199
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
	
	(method (cue)
	)
	
	(method (newNoun theCurNoun)
		(if (IsObject stack) (stack dispose:))
		((= stack (List new:))
			addToFront: (= curNoun theCurNoun)
		)
	)
)
