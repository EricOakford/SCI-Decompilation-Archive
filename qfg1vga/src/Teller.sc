;;; Sierra Script 1.0 - (do not remove this comment)
(script# TELLER)
(include game.sh)
(use Main)
(use Intrface)
(use Print)
(use Dialog)
(use System)

(public
	Teller 0
)

(procedure (localproc_042e param1 param2 param3)
	(Memory MWriteWord (+ param1 (* 2 param2)) param3)
)

(class Teller of Object
	(properties
		query 0
		curArray 0
		arrays 0
		keys 0
		client 0
	)
	
	(method (init who mainBranch dialogTree theKeys)
		(= client who)
		(= curArray mainBranch)
		(= arrays dialogTree)
		(if (> argc 3)
			(= keys theKeys)
		)
		(client actions: self)
		(super init:)
	)
	
	(method (respond)
		(= query (self showDialog:))
		(cond 
			((== query -999)
				(return TRUE)
			)
			((== query 999)
				(self doParent:)
				(return FALSE)
			)
			((and (< query 0) (not (self doChild: query)))
				(return TRUE)
			)
		)
		(if (< query 0)
			(= query (- query)) ;(-= query) doesn't work
		)
		(if (not (client noun?))
			(Prints
				{You forgot to assign a noun property to your actor.}
			)
		)
		(messager say: (client noun?) V_TELL query 0)
		(return TRUE)
	)
	
	(method (showDialog)
		(self
			doDialog: (if (== (WordAt arrays 0) curArray) 1 else 0) &rest
		)
	)
	
	(method (doDialog param1 &tmp
			atTopTree temp1 temp2 temp3 theDialog theButton newDTextNsBottom temp7 theCase theIndex
			[buf1 10] [buf2 10] [buf3 10] [buf4 10] [buf5 10] [buf6 10] [buf7 10] [buf8 10])
		(= atTopTree [param1 (= temp2 0)])
		(= newDTextNsBottom 0)
		(= theIndex 1)
		((= theDialog (Dialog new:)) window: systemWindow)
		((= theButton (DText new:))
			text: {Ask about:}
			font: userFont
			setSize:
			moveTo: MARGIN (+ newDTextNsBottom 4)
		)
		(theDialog add: theButton)
		(= newDTextNsBottom (theButton nsBottom?))
		(= temp1 1)
		(while (!= (WordAt curArray temp1) ENDTELL)
			(= temp7 1)
			(= temp2 1)
			(while (and temp7 (< temp2 argc))
				(if
					(and
						(== (WordAt curArray temp1) [param1 temp2])
						(not [param1 (+ temp2 1)])
					)
					(= temp7 0)
				)
				(+= temp2 2)
			)
			(if temp7
				(if (IsObject theButton)
					(= newDTextNsBottom (theButton nsBottom?))
				)
				(= theCase (WordAt curArray temp1))
				(Message MsgGet curRoomNum (client noun?)
					V_TALK (if (< theCase 0) (- theCase) else theCase) 1
					(switch theIndex
						(0 @buf1)
						(1 @buf2)
						(2 @buf3)
						(3 @buf4)
						(4 @buf5)
						(5 @buf6)
						(6 @buf7)
						(7 @buf8)
					)
				)
				((= theButton (DButton new:))
					text:
						(switch theIndex
							(0 @buf1)
							(1 @buf2)
							(2 @buf3)
							(3 @buf4)
							(4 @buf5)
							(5 @buf6)
							(6 @buf7)
							(7 @buf8)
						)
					value: theCase
					font: userFont
					setSize:
					moveTo: MARGIN (+ newDTextNsBottom MARGIN)
				)
				(theDialog add: theButton)
				(++ theIndex)
			)
			(++ temp1)
		)
		(= newDTextNsBottom (theButton nsBottom?))
		(= theButton (DButton new:))
		(if (not atTopTree)
			(theButton text: {something else} value: 999)
		else
			(theButton text: {enough already} value: -999)
		)
		(theButton setSize: moveTo: MARGIN (+ newDTextNsBottom MARGIN))
		(theDialog add: theButton)
		(= temp3 (theDialog setSize: center: open: 0 -1 doit: 0))
		(cond 
			((IsObject temp3)
				(if (temp3 isKindOf: DButton)
					(= temp3 (temp3 value?))
				)
			)
			(atTopTree
				(theDialog dispose:)
				(return -999)
			)
			(else
				(= temp3 999)
			)
		)
		(theDialog eachElementDo: #dispose 1 dispose:)
		(return temp3)
	)
	
	(method (doChild param1 &tmp i)
		(= i 0)
		(while (++ i)
			(if (== (WordAt keys i) param1)
				(self stuffArray: (WordAt arrays i) 1)
				(return 1)
			)
			(< (WordAt keys i) 999)
		)
		(return 1)
	)
	
	(method (doParent)
		(self stuffArray: (WordAt curArray 0) 0)
	)
	
	(method (stuffArray theCurArray param2)
		(if param2 (localproc_042e theCurArray 0 curArray))
		(= curArray theCurArray)
		(return param2)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_TALK)
			(repeat
				(if (self respond:) (break))
			)
		else
			(client doVerb: theVerb)
		)
	)
)
