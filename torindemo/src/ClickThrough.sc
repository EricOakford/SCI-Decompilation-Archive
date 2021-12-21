;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64021)
(include sci.sh)
(use Main)
(use Array)
(use System)

(public
	Help 0
	oClickThrough1 1
	oClickThroughBog 2
	oClickThroughSlugs 3
	oClickThrough2 4
)

(procedure (Help &tmp theLOOKUP_ERROR theLOOKUP_ERRORAddLine theLOOKUP_ERRORAddPoint temp3 temp4 theLOOKUP_ERRORFindPoint temp6 temp7 temp8 temp9)
	(cond 
		(
			(and
				(== global202 1)
				(not ((ScriptID 64017 0) test: 13))
				(LOOKUP_ERROR dragPoint: 7)
			)
			(= theLOOKUP_ERROR LOOKUP_ERROR)
		)
		(
			(and
				(== global202 1)
				(or
					((ScriptID 64017 0) test: 21)
					(and
						(not ((ScriptID 64017 0) test: 22))
						(== curRoomNum 13000)
						(LOOKUP_ERROR dragPoint: 1)
					)
				)
			)
			(= theLOOKUP_ERROR LOOKUP_ERROR)
		)
		(else
			(= theLOOKUP_ERROR
				(switch global202
					(0 0)
					(1 'LOOKUP_ERROR')
					(2 'LOOKUP_ERROR')
					(3 0)
					(4 0)
					(5 0)
					(6 0)
				)
			)
		)
	)
	(if theLOOKUP_ERROR
		(if (not (theLOOKUP_ERROR oCast?))
			(theLOOKUP_ERROR init:)
		)
		(= theLOOKUP_ERRORAddLine (theLOOKUP_ERROR addLine?))
		(= theLOOKUP_ERRORAddPoint (theLOOKUP_ERROR addPoint?))
		(= theLOOKUP_ERRORFindPoint (theLOOKUP_ERROR findPoint?))
	else
		(MonoOut LOOKUP_ERROR)
		(return)
	)
	(= temp3 0)
	(while
		(and
			(< temp3 (theLOOKUP_ERRORAddLine size:))
			((ScriptID 64017 0)
				test: (theLOOKUP_ERRORAddLine at: temp3)
			)
		)
		(++ temp3)
	)
	(if (>= temp3 (theLOOKUP_ERRORAddLine size:))
		(MonoOut LOOKUP_ERROR)
		(return)
	)
	(= temp4 (theLOOKUP_ERRORAddPoint at: temp3))
	(if
	(and (== global202 global259) (== temp4 global260))
		(++ global261)
	else
		(= global261 1)
	)
	(= global259 global202)
	(= global260 temp4)
	(if
	(Message 0 theLOOKUP_ERRORFindPoint 0 0 temp4 global261)
		(messager
			say: 0 0 temp4 global261 0 theLOOKUP_ERRORFindPoint
		)
		(= temp8 (theLOOKUP_ERRORAddLine at: temp3))
		(= temp6 ((ScriptID 64017 0) writeToFile: temp8))
		(= temp9 ((ScriptID 64017 0) lineColor: temp8))
		(if
			(Message
				0
				theLOOKUP_ERRORFindPoint
				0
				0
				temp4
				(+ global261 1)
			)
			(= temp7 (Min temp9 (+ temp6 global261)))
		else
			(= temp7 temp9)
		)
		(theGame changeScore: (- temp6 temp7))
		((ScriptID 64017 0) polygons: temp8 temp7)
	else
		(if (== (-- global261) 0)
			(MonoOut LOOKUP_ERROR temp4 theLOOKUP_ERRORFindPoint)
			(return)
		)
		(messager
			say: 0 0 temp4 global261 0 theLOOKUP_ERRORFindPoint
		)
	)
	(theLOOKUP_ERROR dispose:)
)

(class ClickThrough of Obj
	(properties
		scratch 0
		oCast 0
		findPoint 0
		addLine 0
		addPoint 0
	)
	
	(method (init)
		(= oCast 1)
		(= addLine (IntArray new: 0))
		(= addPoint (IntArray new: 0))
	)
	
	(method (dispose)
		(= oCast 0)
		(addLine dispose:)
		(addPoint dispose:)
		(super dispose:)
	)
	
	(method (add param1 &tmp temp0 temp1)
		(= temp0 0)
		(while (< temp0 argc)
			(= temp1 (/ temp0 2))
			(addLine at: temp1 [param1 temp0])
			(addPoint at: temp1 [param1 (+ temp0 1)])
			(= temp0 (+ temp0 2))
		)
	)
	
	(method (dragPoint param1 &tmp temp0 temp1 temp2)
		(if oCast (= temp1 1) else (= temp1 0) (self init:))
		(if (== (= temp2 (addLine indexOf: param1)) -1)
			(MonoOut LOOKUP_ERROR)
			(return 1)
		)
		(= temp0 0)
		(while (<= temp0 temp2)
			(if
			(not ((ScriptID 64017 0) test: (addLine at: temp0)))
				(if (not temp1) (self dispose:))
				(return 0)
			)
			(++ temp0)
		)
		(if (not temp1) (self dispose:))
		(return 1)
	)
	
	(method (setPoint param1 &tmp temp0 temp1 temp2)
		(if oCast (= temp2 1) else (= temp2 0) (self init:))
		(if (== (= temp1 (addLine indexOf: param1)) -1)
			(MonoOut LOOKUP_ERROR)
			(return)
		)
		(= temp0 0)
		(while (<= temp0 temp1)
			((ScriptID 64017 0) set: (addLine at: temp0))
			(++ temp0)
		)
		(if (not temp2) (self dispose:))
	)
)

(instance oClickThroughBog of ClickThrough
	(properties
		findPoint 101
	)
	
	(method (init)
		(super init:)
		(self add: 19 10 20 11 21 12 22 13)
	)
)

(instance oClickThroughSlugs of ClickThrough
	(properties
		findPoint 101
	)
	
	(method (init)
		(super init:)
		(self add: 14 19 16 20 17 21 13 22)
	)
)

(instance oClickThrough1 of ClickThrough
	(properties
		findPoint 101
	)
	
	(method (init)
		(super init:)
		(self
			add:
				25
				1
				26
				32
				0
				2
				8
				3
				28
				4
				32
				5
				4
				6
				30
				7
				1
				8
				10
				9
				24
				14
				3
				16
				12
				15
				11
				33
				6
				17
				7
				18
				29
				23
				5
				24
				31
				25
				33
				26
				35
				27
				40
				28
				2
				29
				9
				30
				41
				31
		)
	)
)

(instance oClickThrough2 of ClickThrough
	(properties
		findPoint 102
	)
	
	(method (init)
		(super init:)
		(self
			add:
				43
				20100
				1
				LOOKUP_ERROR
				30
				62
				20200
				1
				LOOKUP_ERROR
				31
				51
				20100
				1
				LOOKUP_ERROR
				32
				65
				20300
				1
				LOOKUP_ERROR
				33
				52
				20300
				12
				LOOKUP_ERROR
				34
				53
				20300
				1
				LOOKUP_ERROR
				35
				63
				20200
				34
				LOOKUP_ERROR
				36
				44
				20200
				1
				LOOKUP_ERROR
				37
				45
				20400
				1
				LOOKUP_ERROR
				38
				70
				20400
				43
				LOOKUP_ERROR
				39
				71
				20400
				45
				LOOKUP_ERROR
				40
				69
				20400
				44
				LOOKUP_ERROR
				41
				54
				20400
				43
				LOOKUP_ERROR
				42
				75
				20100
				1
				LOOKUP_ERROR
				43
				73
				20100
				35
				LOOKUP_ERROR
				44
				46
				20600
				1
				LOOKUP_ERROR
				45
				56
				20600
				1
				LOOKUP_ERROR
				46
				58
				20600
				1
				LOOKUP_ERROR
				47
				55
				20600
				1
				LOOKUP_ERROR
				48
				47
				20600
				1
				LOOKUP_ERROR
				49
				86
				20500
				33
				LOOKUP_ERROR
				50
				83
				20500
				38
				LOOKUP_ERROR
				51
				80
				20900
				1
				LOOKUP_ERROR
				52
				49
				20900
				37
				LOOKUP_ERROR
				53
				90
				20600
				39
				LOOKUP_ERROR
				54
				91
				20600
				40
				LOOKUP_ERROR
				55
				92
				20600
				46
				LOOKUP_ERROR
				56
				89
				20600
				1
				LOOKUP_ERROR
				57
				72
				20100
				36
				LOOKUP_ERROR
				59
				93
				20700
				1
				LOOKUP_ERROR
				60
				50
				20300
				1
				LOOKUP_ERROR
				61
				74
				20100
				29
				LOOKUP_ERROR
				62
				60
				20100
				1
				LOOKUP_ERROR
				63
				94
				20700
				1
				LOOKUP_ERROR
				64
				95
				20800
				13
				LOOKUP_ERROR
				25
		)
	)
)
