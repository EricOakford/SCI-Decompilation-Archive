;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64924)
(include sci.sh)
(use Main)
(use String)
(use Array)
(use Print)
(use System)

(public
	Messager 0
	talkerSet 1
)

(class Messager of Obj
	(properties
		scratch 0
		caller 0
		disposeWhenDone 1
		oneOnly 0
		killed 0
		disableIconBar 1
		oldIconBarState 0
		curSequence 0
		lastSequence 0
	)
	
	(method (dispose)
		(talkerSet dispose:)
		(= lastSequence 0)
		(if (and theIconBar disableIconBar)
			(theIconBar state: oldIconBarState)
			(= oldIconBarState 0)
		)
		(if caller
			(if (not cuees) (= cuees (Set new:)))
			(cuees
				add: ((Cue new:)
					cuee: caller
					cuer: self
					register: killed
					yourself:
				)
			)
		)
		(super dispose:)
	)
	
	(method (cue param1)
		(if (and argc param1) (= killed 1))
		(if (or oneOnly killed)
			(self dispose:)
		else
			(self nextMsg:)
		)
	)
	
	(method (say param1 param2 param3 theCurSequence theCaller theTheCurRoomNum &tmp theCurRoomNum)
		(= caller (= oneOnly (= killed 0)))
		(if
		(and theIconBar disableIconBar (not oldIconBarState))
			(= oldIconBarState (theIconBar state?))
		)
		(if (and (> argc 3) theCurSequence)
			(= oneOnly 1)
			(= curSequence theCurSequence)
		else
			(= curSequence 1)
		)
		(if (> argc 4) (= caller theCaller))
		(if (> argc 5)
			(= theCurRoomNum theTheCurRoomNum)
		else
			(= theCurRoomNum curRoomNum)
		)
		(if
			(and
				msgType
				(Message
					msgGET
					theCurRoomNum
					param1
					param2
					param3
					curSequence
				)
			)
			(self
				nextMsg: theCurRoomNum param1 param2 param3 curSequence
			)
		else
			(Print
				addTextF:
					{<Messager>\n\tmsgType set to 0 or\n\t%d: %d, %d, %d, %d not found}
					theCurRoomNum
					param1
					param2
					param3
					curSequence
				init:
			)
			(self dispose:)
		)
	)
	
	(method (sayRange param1 param2 param3 theCurSequence theLastSequence theCaller theTheCurRoomNum &tmp theCurRoomNum)
		(= caller (= oneOnly (= killed 0)))
		(if
		(and theIconBar disableIconBar (not oldIconBarState))
			(= oldIconBarState (theIconBar state?))
		)
		(= oneOnly 1)
		(if (and (> argc 3) theCurSequence)
			(= curSequence theCurSequence)
		else
			(= curSequence 1)
		)
		(if (and (> argc 4) theLastSequence)
			(= oneOnly 0)
			(= lastSequence theLastSequence)
		else
			(= lastSequence curSequence)
		)
		(if (> argc 5) (= caller theCaller))
		(if (> argc 6)
			(= theCurRoomNum theTheCurRoomNum)
		else
			(= theCurRoomNum curRoomNum)
		)
		(if
			(and
				msgType
				(Message
					msgGET
					theCurRoomNum
					param1
					param2
					param3
					curSequence
				)
			)
			(self
				nextMsg: theCurRoomNum param1 param2 param3 curSequence
			)
		else
			(Print
				addTextF:
					{<Messager>\n\tmsgType set to 0 or\n\t%d: %d, %d, %d, %d not found}
					theCurRoomNum
					param1
					param2
					param3
					curSequence
				init:
			)
			(self dispose:)
		)
	)
	
	(method (sayNext theCaller)
		(= caller (= oneOnly (= killed 0)))
		(if
		(and theIconBar disableIconBar (not oldIconBarState))
			(= oldIconBarState (theIconBar state?))
		)
		(if argc (= caller theCaller))
		(self nextMsg:)
	)
	
	(method (sayFormat theCaller param2 param3 param4 &tmp temp0 temp1 temp2)
		(if
		(and theIconBar disableIconBar (not oldIconBarState))
			(= oldIconBarState (theIconBar state?))
		)
		(= temp2 (self findTalker: param2))
		(= temp0 (FindFormatLen param3 param4 &rest))
		(= caller theCaller)
		(= oneOnly 1)
		(= temp1 (Str newWith: temp0 {}))
		(temp1 format: param3 param4 &rest)
		(temp2 say: temp1 self)
		(temp1 dispose:)
	)
	
	(method (nextMsg param1 param2 param3 param4 param5 &tmp temp0 temp1 temp2)
		(= temp1 (Str newWith: 400 {}))
		(if argc
			(= temp0
				(Message
					msgGET
					param1
					param2
					param3
					param4
					param5
					(temp1 data?)
				)
			)
		else
			(= temp0 (Message msgNEXT (temp1 data?)))
		)
		(= temp2 0)
		(if
			(and
				temp0
				(or
					(not lastSequence)
					(and lastSequence (<= curSequence lastSequence))
				)
			)
			(if (!= (= temp0 (self findTalker: temp0)) -1)
				(talkerSet add: temp0)
				(if (& msgType $0002)
					(= temp2 (IntArray with: 0 0 0 0 0))
					(Message 9 (temp2 data?))
				)
				(temp0 modNum: param1 say: temp1 self temp2)
				(++ curSequence)
			else
				(++ curSequence)
				(if (not cuees) (= cuees (Set new:)))
				(cuees
					add: ((Cue new:) cuee: self cuer: self yourself:)
				)
				(self dispose:)
			)
		else
			(self dispose:)
		)
		(if (and temp2 (& msgType $0002)) (temp2 dispose:))
		(temp1 dispose:)
	)
	
	(method (findTalker)
		(Print
			width: 200
			addText:
				{<Messager findTalker:>\n\tCan't find talker or\n\tfindTalker method not over-ridden}
			init:
		)
		(return narrator)
	)
)

(instance talkerSet of Set
	(properties)
	
	(method (dispose)
		(self
			eachElementDo: #caller 0
			eachElementDo: #dispose (messager disposeWhenDone?)
			release:
		)
		(super dispose:)
	)
)
