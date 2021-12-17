;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64924)
(include game.sh)
(use Main)
(use String)
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
		talkerList 0
		bGameWasHandsOff 0
		bActive 0
	)
	
	(method (init)
		(super init: &rest)
		(= talkerList talkerSet)
	)
	
	(method (dispose)
		(= bActive 0)
		(if (not bGameWasHandsOff) (theGame handsOn:))
		(if (talkerList size:) (talkerList dispose:))
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
	
	(method (cue)
	)
	
	(method (say theCurSequence &tmp theTheCurSequence theTheCurSequence_2 theTheCurSequence_3 theCurRoomNum)
		(if bActive
			(MonoOut {Tried to say two messages. messager.sc})
			(self kill:)
		)
		(= bActive 1)
		(= bGameWasHandsOff (not (user canControl:)))
		(theGame handsOff:)
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
						0
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
		(if bActive
			(MonoOut {Tried to say two messages. ;messager.sc})
			(self kill:)
		)
		(= bActive 1)
		(= bGameWasHandsOff (not (user canControl:)))
		(theGame handsOff:)
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
						0
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
	
	(method (nextMsg &tmp [temp0 3])
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
	
	(method (kill)
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
