;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64924)
(include game.sh)
(use Main)
(use Set)
(use Cue)
(use String)
(use Array)
(use Print)
(use System)


(class Messager of Object
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
		cCheck 0
		nDisableCount 0
		isHilited 0
	)
	
	(method (init)
		(super init: &rest)
		(= cCheck talkerSet)
	)
	
	(method (dispose)
		(= isHilited 0)
		(if (not nDisableCount) (theGame handsOn:))
		(if (cCheck size:) (cCheck dispose:))
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
	
	(method (say theCurSequence &tmp theTheCurSequence theTheCurSequence_2 theTheCurSequence_3 theCurRoomNum)
		(if isHilited
			(MonoOut {Tried to say two messages. messager.sc})
			(self isVisible:)
		)
		(= isHilited 1)
		(= nDisableCount (not (user canControl:)))
		(theGame handsOff: 0)
		(= theTheCurSequence
			(= theTheCurSequence_2
				(= theTheCurSequence_3 (= curSequence (= caller 0)))
			)
		)
		(= oneOnly (= killed 0))
		(if
		(and theIconBar disableIconBar (not oldIconBarState))
			(= oldIconBarState (theIconBar state?))
			(theIconBar disable:)
		)
		(= theTheCurSequence [theCurSequence 0])
		(if (> argc 1)
			(= theTheCurSequence_2 [theCurSequence 1])
		)
		(if (> argc 2)
			(= theTheCurSequence_3 [theCurSequence 2])
		)
		(if (and (> argc 3) [theCurSequence 3])
			(= oneOnly 1)
			(= curSequence [theCurSequence 3])
		else
			(= curSequence 1)
		)
		(if (> argc 4) (= caller [theCurSequence 4]))
		(if (> argc 5)
			(= theCurRoomNum [theCurSequence 5])
		else
			(= theCurRoomNum curRoomNum)
		)
		(if
			(not
				(if msgType
					(Message
						MsgGet
						theCurRoomNum
						theTheCurSequence
						theTheCurSequence_2
						theTheCurSequence_3
						curSequence
					)
				)
			)
			(Print
				addTextF:
					{<Messager>\n\tmsgType set to 0 or\n\t%d: %d, %d, %d, %d not found}
					theCurRoomNum
					theTheCurSequence
					theTheCurSequence_2
					theTheCurSequence_3
					curSequence
				init:
			)
			(= theCurRoomNum -542)
			(= theTheCurSequence 12)
			(= theTheCurSequence_2 (= theTheCurSequence_3 0))
			(= curSequence 1)
		)
		(self
			nextMsg:
				theCurRoomNum
				theTheCurSequence
				theTheCurSequence_2
				theTheCurSequence_3
				curSequence
		)
	)
	
	(method (sayRange theCurSequence &tmp theTheCurSequence theTheCurSequence_2 theTheCurSequence_3 theCurRoomNum)
		(if isHilited
			(MonoOut {Tried to say two messages. ;messager.sc})
			(self isVisible:)
		)
		(= isHilited 1)
		(= nDisableCount (not (user canControl:)))
		(theGame handsOff: 0)
		(= theTheCurSequence
			(= theTheCurSequence_2
				(= theTheCurSequence_3 (= curSequence (= caller 0)))
			)
		)
		(= oneOnly (= killed 0))
		(if
		(and theIconBar disableIconBar (not oldIconBarState))
			(= oldIconBarState (theIconBar state?))
			(theIconBar disable:)
		)
		(= theTheCurSequence [theCurSequence 0])
		(if (> argc 1)
			(= theTheCurSequence_2 [theCurSequence 1])
		)
		(if (> argc 2)
			(= theTheCurSequence_3 [theCurSequence 2])
		)
		(= oneOnly 1)
		(if (and (> argc 3) [theCurSequence 3])
			(= curSequence [theCurSequence 3])
		else
			(= curSequence 1)
		)
		(if (and (> argc 4) [theCurSequence 4])
			(= oneOnly 0)
			(= lastSequence [theCurSequence 4])
		else
			(= lastSequence curSequence)
		)
		(if (> argc 5) (= caller [theCurSequence 5]))
		(if (> argc 6)
			(= theCurRoomNum [theCurSequence 6])
		else
			(= theCurRoomNum curRoomNum)
		)
		(if
			(not
				(if msgType
					(Message
						MsgGet
						theCurRoomNum
						theTheCurSequence
						theTheCurSequence_2
						theTheCurSequence_3
						curSequence
					)
				)
			)
			(Print
				addTextF:
					{<Messager>\n\tmsgType set to 0 or\n\t%d: %d, %d, %d, %d not found}
					theCurRoomNum
					theTheCurSequence
					theTheCurSequence_2
					theTheCurSequence_3
					curSequence
				init:
			)
			(= theCurRoomNum -542)
			(= theTheCurSequence 12)
			(= theTheCurSequence_2 (= theTheCurSequence_3 0))
			(= curSequence 1)
		)
		(self
			nextMsg:
				theCurRoomNum
				theTheCurSequence
				theTheCurSequence_2
				theTheCurSequence_3
				curSequence
		)
	)
	
	(method (sayNext theCaller)
		(= caller (= oneOnly (= killed 0)))
		(if
		(and theIconBar disableIconBar (not oldIconBarState))
			(= oldIconBarState (theIconBar state?))
			(theIconBar disable:)
		)
		(if argc (= caller theCaller))
		(self nextMsg:)
	)
	
	(method (sayFormat theCaller param2 param3 param4 &tmp temp0 temp1 temp2)
		(if
		(and theIconBar disableIconBar (not oldIconBarState))
			(= oldIconBarState (theIconBar state?))
			(theIconBar disable:)
		)
		(= temp2 (self findTalker: param2))
		(= temp0 (FindFormatLen param3 param4 &rest))
		(= caller theCaller)
		(= oneOnly 1)
		(= temp1 (String newWith: temp0 {}))
		(temp1 format: param3 param4 &rest)
		(temp2 say: temp1 self)
		(temp1 dispose:)
	)
	
	(method (nextMsg param1 param2 param3 param4 param5 &tmp temp0 temp1 temp2)
		(= temp1 (String newWith: 4000 {}))
		(if argc
			(= temp0
				(Message
					MsgGet
					param1
					param2
					param3
					param4
					param5
					(temp1 data?)
				)
			)
		else
			(= temp0 (Message MsgNext (temp1 data?)))
		)
		(if
			(and
				temp0
				(or
					(not lastSequence)
					(and lastSequence (<= curSequence lastSequence))
				)
			)
			(if (!= (= temp0 (self findTalker: temp0)) -1)
				(if (& msgType $0002)
					(= temp2 (IntArray with: 0 0 0 0 0))
					(Message 9 (temp2 data?))
				)
				(cCheck add: temp0)
				(temp0 modNum: param1 say: temp1 temp2 self)
				(++ curSequence)
				(if (& msgType $0002) (temp2 dispose:))
			else
				(self dispose:)
			)
		else
			(self dispose:)
		)
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
	
	(method (isVisible)
		(= caller 0)
		(self dispose:)
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
