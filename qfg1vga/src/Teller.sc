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
	
	(method (init theClient theCurArray theArrays theKeys)
		(= client theClient)
		(= curArray theCurArray)
		(= arrays theArrays)
		(if (> argc 3) (= keys theKeys))
		(client actions: self)
		(super init:)
	)
	
	(method (respond)
		(= query (self showDialog:))
		(cond 
			((== query -999) (return 1))
			((== query 999) (self doParent:) (return 0))
			((and (< query 0) (not (self doChild: query))) (return 1))
		)
		(if (< query 0) (= query (- query)))
		(if (not (client noun?))
			(Prints
				{You forgot to assign a noun property to your actor.}
			)
		)
		(messager say: (client noun?) 5 query 0)
		(return 1)
	)
	
	(method (showDialog)
		(self
			doDialog: (if (== (WordAt arrays 0) curArray) 1 else 0) &rest
		)
	)
	
	(method (doDialog param1 &tmp temp0 temp1 temp2 temp3 newDialog newDText newDTextNsBottom temp7 temp8 temp9 [temp10 10] [temp20 10] [temp30 10] [temp40 10] [temp50 10] [temp60 10] [temp70 10] [temp80 10])
		(= temp0 [param1 (= temp2 0)])
		(= newDTextNsBottom 0)
		(= temp9 1)
		((= newDialog (Dialog new:)) window: systemWindow)
		((= newDText (DText new:))
			text: {Ask about:}
			font: userFont
			setSize:
			moveTo: 4 (+ newDTextNsBottom 4)
		)
		(newDialog add: newDText)
		(= newDTextNsBottom (newDText nsBottom?))
		(= temp1 1)
		(while (!= (WordAt curArray temp1) 999)
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
				(= temp2 (+ temp2 2))
			)
			(if temp7
				(if (IsObject newDText)
					(= newDTextNsBottom (newDText nsBottom?))
				)
				(= temp8 (WordAt curArray temp1))
				(Message MsgGet
					curRoomNum
					(client noun?)
					2
					(if (< temp8 0) (- temp8) else temp8)
					1
					(switch temp9
						(0 @temp10)
						(1 @temp20)
						(2 @temp30)
						(3 @temp40)
						(4 @temp50)
						(5 @temp60)
						(6 @temp70)
						(7 @temp80)
					)
				)
				((= newDText (DButton new:))
					text:
						(switch temp9
							(0 @temp10)
							(1 @temp20)
							(2 @temp30)
							(3 @temp40)
							(4 @temp50)
							(5 @temp60)
							(6 @temp70)
							(7 @temp80)
						)
					value: temp8
					font: userFont
					setSize:
					moveTo: 4 (+ newDTextNsBottom 4)
				)
				(newDialog add: newDText)
				(++ temp9)
			)
			(++ temp1)
		)
		(= newDTextNsBottom (newDText nsBottom?))
		(= newDText (DButton new:))
		(if (not temp0)
			(newDText text: {something else} value: 999)
		else
			(newDText text: {enough already} value: -999)
		)
		(newDText setSize: moveTo: 4 (+ newDTextNsBottom 4))
		(newDialog add: newDText)
		(= temp3 (newDialog setSize: center: open: 0 -1 doit: 0))
		(cond 
			((IsObject temp3) (if (temp3 isKindOf: DButton) (= temp3 (temp3 value?))))
			(temp0 (newDialog dispose:) (return -999))
			(else (= temp3 999))
		)
		(newDialog eachElementDo: #dispose 1 dispose:)
		(return temp3)
	)
	
	(method (doChild param1 &tmp temp0)
		(= temp0 0)
		(while (++ temp0)
			(if (== (WordAt keys temp0) param1)
				(self stuffArray: (WordAt arrays temp0) 1)
				(return 1)
			)
			(< (WordAt keys temp0) 999)
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
