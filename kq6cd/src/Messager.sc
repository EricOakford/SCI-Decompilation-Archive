;;; Sierra Script 1.0 - (do not remove this comment)
(script# 924)
(include game.sh)
(use Main)
(use Print)
(use Game)
(use System)


(class Messager of Object
	(properties
		caller 0
		disposeWhenDone TRUE
		oneOnly 0
		killed 0
		oldIconBarState 0
		curSequence 0
		lastSequence 0
		talker 0
	)
	
	(method (dispose)
		(talkerSet dispose:)
		(if theIconBar
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
		(= talker 0)
		(super dispose:)
	)
	
	(method (cue param1)
		(if (and argc param1) (= killed 1))
		(if (or oneOnly killed)
			(if fastCast
				(fastCast release: dispose:)
				(= fastCast 0)
			)
			(self dispose:)
		else
			(self sayNext:)
		)
	)
	
	(method (say theCaller &tmp theTheCaller theTheCaller_2 theTheCaller_3 temp3 [temp4 20] temp24)
		(= theTheCaller
			(= theTheCaller_2 (= theTheCaller_3 (= curSequence 0)))
		)
		(= caller (= oneOnly (= killed 0)))
		(if (and theIconBar (not oldIconBarState))
			(= oldIconBarState (theIconBar state?))
		)
		(if (== (= theTheCaller [theCaller 0]) -1)
			(if (and (> argc 1) (IsObject [theCaller 1]))
				(= caller [theCaller 1])
			)
			(self sayNext:)
		else
			(if (and (> argc 1) [theCaller 1])
				(= theTheCaller_2 [theCaller 1])
			)
			(if (and (> argc 2) [theCaller 2])
				(= theTheCaller_3 [theCaller 2])
			)
			(if (and (> argc 3) [theCaller 3])
				(= oneOnly 1)
				(= curSequence [theCaller 3])
			else
				(= curSequence 1)
			)
			(if
				(and
					(> argc (= temp24 4))
					[theCaller temp24]
					(not (IsObject [theCaller temp24]))
				)
				(= lastSequence [theCaller temp24])
				(++ temp24)
				(= oneOnly 0)
			else
				(= lastSequence 0)
			)
			(if (and (> argc temp24) [theCaller temp24])
				(= caller [theCaller temp24])
			else
				(= caller 0)
			)
			(= temp3
				(if (> argc (++ temp24))
					[theCaller temp24]
				else
					curRoomNum
				)
			)
			(if
				(and
					msgType
					(Message
						msgGET
						temp3
						theTheCaller
						theTheCaller_2
						theTheCaller_3
						curSequence
					)
				)
				(self
					sayNext: temp3 theTheCaller theTheCaller_2 theTheCaller_3 curSequence
				)
			else
				(Print
					addTextF:
						{<Messager>\n\tmsgType set to 0 or\n\t%d: %d, %d, %d, %d not found}
						temp3
						theTheCaller
						theTheCaller_2
						theTheCaller_3
						curSequence
					init:
				)
				(self dispose:)
			)
		)
	)
	
	(method (sayFormat param1 param2 theCaller &tmp temp0 temp1 temp2)
		(if (and theIconBar (not oldIconBarState))
			(= oldIconBarState (theIconBar state?))
		)
		(= temp2 (self findTalker: param1))
		(= temp0 (FindFormatLen param2 theCaller &rest))
		(if (IsObject [theCaller (- argc 2)])
			(= caller [theCaller (- argc 2)])
		)
		(= oneOnly 1)
		(= temp1 (Memory memALLOC_CRIT temp0))
		(Format temp1 param2 theCaller &rest)
		(temp2 say: temp1 self)
		(Memory memFREE temp1)
	)
	
	(method (sayNext param1 param2 param3 param4 param5 &tmp theTalker [temp1 200] temp201)
		(if argc
			(= theTalker
				(Message msgGET param1 param2 param3 param4 param5 @temp1)
			)
		else
			(= theTalker (Message msgNEXT @temp1))
		)
		(if (& msgType $0002)
			(= temp201 (Memory memALLOC_CRIT 12))
			(Message msgLAST_MESSAGE temp201)
		)
		(if
			(and
				theTalker
				(or
					(not lastSequence)
					(and lastSequence (<= curSequence lastSequence))
				)
			)
			(= theTalker (self findTalker: theTalker))
			(if
				(and
					talker
					(!= theTalker talker)
					(== (talker disposeWhenDone?) 2)
				)
				(talker caller: 0 dispose: 1)
			)
			(if (!= (= talker theTalker) -1)
				(talkerSet add: theTalker)
				(if (& msgType $0002)
					(theTalker modNum: param1 say: temp201 self)
				else
					(theTalker
						modNum: param1
						say: @temp1 self param1 param2 param3 param4 param5
					)
				)
				(++ curSequence)
			else
				(if fastCast
					(fastCast release: dispose:)
					(= fastCast 0)
				)
				(self dispose:)
			)
		else
			(if fastCast
				(fastCast release: dispose:)
				(= fastCast 0)
			)
			(self dispose:)
		)
		(if (& msgType $0002) (Memory memFREE temp201))
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
