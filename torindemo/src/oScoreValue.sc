;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64017)
(include sci.sh)
(use Main)
(use Set)
(use Array)
(use Flags)

(public
	oFlags 0
)

(class oScoreValue of Set
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
		stopSinging 0
		setDisplay 0
	)
	
	(method (init)
		(super init: &rest)
		(= stopSinging (IntArray new: 0))
		(= setDisplay (IntArray new: 0))
	)
	
	(method (dispose)
		(stopSinging dispose:)
		(= stopSinging 0)
		(super dispose:)
	)
	
	(method (add param1 &tmp temp0 temp1)
		(= temp0 0)
		(while (< temp0 argc)
			(super addToEnd: [param1 temp0])
			(= temp1 (self indexOf: [param1 temp0]))
			(stopSinging at: temp1 [param1 (= temp0 (+ temp0 1))])
			(= temp0 (+ temp0 1))
		)
	)
	
	(method (lineColor param1 &tmp temp0)
		(return
			(if (< (= temp0 (self indexOf: param1)) 0)
				(return 0)
			else
				(return (stopSinging at: temp0))
			)
		)
	)
	
	(method (writeToFile param1 &tmp temp0)
		(return
			(if (< (= temp0 (self indexOf: param1)) 0)
				(return 0)
			else
				(return (setDisplay at: temp0))
			)
		)
	)
)

(class oFlagsClass of Flags
	(properties
		scratch 0
		size 0
		array 0
	)
	
	(method (getMyYOffset param1)
		(MonoOut
			{SetNoScore should be replaced by normal set. djm. tpflags.sc}
		)
		(super set: param1 &rest)
	)
	
	(method (unlockAudio param1 &tmp temp0 temp1 temp2)
		(super clear: param1 &rest)
		(= temp0 0)
		(while (< temp0 argc)
			(= temp1 (oScoreValue lineColor: [param1 temp0]))
			(= temp2 (oScoreFlags test: [param1 temp0]))
			(if (and temp1 temp2)
				(theGame changeScore: (- 0 temp1))
				(oScoreFlags clear: [param1 temp0])
			)
			(= temp0 (+ temp0 1))
		)
	)
)

(instance oScoreFlags of Flags
	(properties
		size 145
	)
)

(instance oFlags of oFlagsClass
	(properties
		size 145
	)
	
	(method (init)
		(super init: &rest)
		(oScoreFlags init:)
		(oScoreValue
			init:
			add:
				0
				2
				1
				2
				2
				2
				3
				2
				4
				2
				5
				2
				6
				2
				7
				2
				8
				2
				9
				2
				10
				10
				11
				10
				33
				2
				13
				2
				14
				2
				29
				5
				30
				5
				31
				5
				40
				10
				41
				5
				42
				2
				43
				2
				44
				2
				45
				2
				46
				2
				47
				2
				48
				2
				49
				2
				50
				2
				51
				2
				52
				2
				53
				2
				54
				2
				55
				2
				56
				2
				57
				2
				58
				2
				59
				2
				63
				5
				72
				5
				73
				5
				83
				5
				97
				2
				98
				2
				99
				5
				100
				5
				101
				5
				106
				5
				109
				2
				110
				2
				111
				2
				112
				2
				113
				2
				114
				2
				115
				2
				116
				2
				117
				2
				118
				2
				119
				2
				120
				2
				121
				2
				122
				2
				123
				2
				124
				2
				125
				2
				126
				2
				127
				2
				138
				2
				140
				5
				141
				10
		)
	)
	
	(method (set param1 &tmp temp0 temp1 temp2 temp3)
		(super set: param1 &rest)
		(= temp0 0)
		(while (< temp0 argc)
			(= temp2 (oScoreValue lineColor: [param1 temp0]))
			(= temp3 (oScoreFlags test: [param1 temp0]))
			(if (and temp2 (not temp3))
				(theGame changeScore: temp2)
				(oScoreFlags set: [param1 temp0])
			)
			(= temp0 (+ temp0 1))
		)
	)
	
	(method (lineColor param1)
		(oScoreValue lineColor: param1)
	)
	
	(method (writeToFile param1)
		(oScoreValue writeToFile: param1)
	)
	
	(method (polygons param1 param2)
		((oScoreValue setDisplay?) at: param1 param2)
	)
)
