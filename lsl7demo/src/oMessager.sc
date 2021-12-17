;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64032)
(include sci.sh)
(use Main)
(use TPMessager)
(use PArray)
(use String)
(use Talker)

(public
	proc64032_0 0
	oMessager 1
	proc64032_2 2
)

(local
	newPArray
	local1
)
(procedure (proc64032_0 param1 &tmp temp0)
	(if
		(or
			(not local1)
			(not newPArray)
			(< (newPArray size:) param1)
		)
		(return {Captain Nobody})
	)
	(= temp0 (newPArray at: param1))
	(return
		(if
		(not (Message 0 17 temp0 0 0 1 (local1 data?)))
			(return {Captain Nobody})
		else
			(return (local1 data?))
		)
	)
)

(procedure (proc64032_2)
	(= local1 (Str newWith: 400 {_}))
	((= newPArray (PArray new:))
		add:
			99
			1
			23
			2
			2
			9
			39
			11
			9
			10
			17
			12
			29
			13
			6
			14
			28
			15
			1
			16
			5
			17
			13
			18
			11
			19
			30
			20
			12
			21
			4
			24
			26
			25
			3
			26
			8
			27
			19
			28
			25
			29
			7
			30
			10
			31
			15
			32
	)
)

(instance oNarrator of Narrator
	(properties)
)

(instance oMessager of TPMessager
	(properties)
	
	(method (findTalker param1)
		(cond 
			((== param1 2) (if gToDewmi (return gToDewmi) else (return oNarrator)))
			((== param1 14) (if gToDewmi (return global343) else (return oNarrator)))
			((== param1 9) (if gToDewmi (return global336) else (return oNarrator)))
			((== param1 1) (if gToLarry (return gToLarry) else (return oNarrator)))
			((== param1 3) (if gToThygh (return gToThygh) else (return oNarrator)))
			((== param1 4) (if global331 (return global331) else (return oNarrator)))
			((== param1 8) (if global337 (return global337) else (return oNarrator)))
			((== param1 7) (if global342 (return global342) else (return oNarrator)))
			((== param1 35) (if global344 (return global344) else (return oNarrator)))
			((== param1 99) (return oNarrator))
			(else (return oNarrator))
		)
		(return oNarrator)
	)
)
