;;; Sierra Script 1.0 - (do not remove this comment)
(script# 924)
(include game.sh)
(use Main)
(use Print)
(use System)


(class Messager of Object
	(properties
		caller 0
		talkerList 0
		disposeWhenDone 1
		oneOnly 0
		killed 0
		oldIconBarState 0
	)
	
	(method (dispose &tmp theCaller)
		(if talkerList
			(talkerList
				eachElementDo: #caller 0
				eachElementDo: #dispose 1
				dispose:
			)
			(= talkerList 0)
		)
		(theIconBar state: oldIconBarState)
		(= oldIconBarState 0)
		(= theCaller caller)
		(super dispose:)
		(if theCaller (theCaller cue: killed))
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
	
	(method (say param1 theCaller param3 param4 theCaller_2 theTheCurRoomNum &tmp theTheCaller temp1 temp2 theCurRoomNum [temp4 20])
		(= theTheCaller (= temp1 (= temp2 0)))
		(= caller (= oneOnly (= killed 0)))
		(if (not oldIconBarState)
			(= oldIconBarState (theIconBar state?))
		)
		(if (> argc 5)
			(= theCurRoomNum theTheCurRoomNum)
		else
			(= theCurRoomNum curRoomNum)
		)
		(if (not talkerList) ((= talkerList (Set new:)) add:))
		(if (== param1 -1)
			(if (and (> argc 1) (IsObject theCaller))
				(= caller theCaller)
			)
			(self sayNext:)
		else
			(if (and (> argc 4) theCaller_2)
				(= caller theCaller_2)
			)
			(if (and (> argc 1) theCaller)
				(= theTheCaller theCaller)
			)
			(if (and (> argc 2) param3) (= temp1 param3))
			(if (and (> argc 3) param4)
				(= oneOnly 1)
				(= temp2 param4)
			else
				(= temp2 1)
			)
			(= caller
				(if (and (> argc 4) theCaller_2) theCaller_2 else 0)
			)
			(if
				(or
					(and
						(& msgType $0001)
						(Message
							MsgGet
							theCurRoomNum
							param1
							theTheCaller
							temp1
							temp2
						)
					)
					0
				)
				(self
					sayNext: theCurRoomNum param1 theTheCaller temp1 temp2
				)
			else
				(Print
					addTextF:
						@temp4
						{<Messager> %d: %d, %d, %d, %d not found}
						theCurRoomNum
						param1
						theTheCaller
						temp1
						temp2
					init:
				)
				(self dispose:)
			)
		)
	)
	
	(method (sayNext param1 param2 param3 param4 param5 &tmp temp0)
		(if (= temp0 (Message MsgNext 0))
			(= temp0 (self findTalker: temp0))
			(talkerList add: temp0)
			(if argc
				(temp0 say: param2 param3 param4 param5 self param1)
			else
				(temp0 say: 0 0 0 0 self)
			)
		else
			(if fastCast
				(fastCast release: dispose:)
				(= fastCast 0)
			)
			(self dispose:)
		)
	)
	
	(method (findTalker)
		(Prints {<Messager findTalker:> Can't find talker})
		(= quit 1)
	)
)
