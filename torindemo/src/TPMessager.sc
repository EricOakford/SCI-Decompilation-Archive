;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64029)
(include sci.sh)
(use Main)
(use oMessager)
(use String)
(use Array)
(use Messager)


(class TPMessager of Messager
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
		nPointLeft 0
		nGoingTo 1
		nComingFrom 0
		exitPriority 0
		enterPriority 0
		arrowDir 0
	)
	
	(method (nextMsg theArrowDir theNComingFrom theExitPriority theEnterPriority param5 &tmp temp0 temp1 temp2 temp3)
		(= temp1 (Str newWith: 1000 LOOKUP_ERROR))
		(= temp2 0)
		(if argc
			(= temp0
				(Message
					0
					theArrowDir
					theNComingFrom
					theExitPriority
					theEnterPriority
					param5
					(temp1 data?)
				)
			)
			(= nComingFrom theNComingFrom)
			(= exitPriority theExitPriority)
			(= enterPriority theEnterPriority)
			(= arrowDir theArrowDir)
		else
			(= temp0 (Message 1 (temp1 data?)))
		)
		(if
			(and
				temp0
				(or
					(not lastSequence)
					(and lastSequence (<= curSequence lastSequence))
				)
			)
			(= temp2
				(Str format: LOOKUP_ERROR (proc64032_0 temp0) temp1)
			)
			(if (!= (= temp0 (self findTalker: temp0)) -1)
				(if (& msgType $0002)
					(= temp3 (IntArray with: 0 0 0 0 0))
					(Message 9 (temp3 data?))
				)
				(cCheck add: temp0)
				(temp0 modNum: theArrowDir say: temp2 temp3 self)
				(if (and nGoingTo nPointLeft)
					(nPointLeft
						unselect: arrowDir nComingFrom exitPriority enterPriority curSequence
					)
				)
				(++ curSequence)
				(if (& msgType $0002) (temp3 dispose:))
			else
				(self dispose:)
			)
		else
			(self dispose:)
		)
		(temp1 dispose:)
		(if temp2 (temp2 dispose:))
	)
	
	(method (sing)
		(if nGoingTo
			(= nGoingTo 0)
			(self say: &rest)
			(= nGoingTo 1)
		else
			(self say: &rest)
		)
	)
)
