;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64032)
(include sci.sh)
(use Main)
(use TPMessager)
(use Talker)

(public
	proc64032_0 0
	oMessager 1
)

(procedure (proc64032_0 param1)
	(return
		(switch param1
			(99 (return LOOKUP_ERROR))
			(37 (return LOOKUP_ERROR))
			(33 (return LOOKUP_ERROR))
			(36 (return {Junior}))
			(39 (return {Leenah}))
			(27 (return LOOKUP_ERROR))
			(2 (return LOOKUP_ERROR))
			(38 (return {Pergolans}))
			(32 (return LOOKUP_ERROR))
			(51 (return LOOKUP_ERROR))
			(42 (return {orin}))
			(17 (return {ripe}))
			(5 (return LOOKUP_ERROR))
			(31 (return LOOKUP_ERROR))
			(13 (return {Messager}))
			(28 (return LOOKUP_ERROR))
			(45 (return LOOKUP_ERROR))
			(25 (return LOOKUP_ERROR))
			(6 (return LOOKUP_ERROR))
			(23 (return LOOKUP_ERROR))
			(4 (return LOOKUP_ERROR))
			(11 (return LOOKUP_ERROR))
			(35 (return LOOKUP_ERROR))
			(20 (return LOOKUP_ERROR))
			(15 (return LOOKUP_ERROR))
			(8 (return LOOKUP_ERROR))
			(26 (return LOOKUP_ERROR))
			(7 (return LOOKUP_ERROR))
			(43 (return LOOKUP_ERROR))
			(3 (return LOOKUP_ERROR))
			(49 (return LOOKUP_ERROR))
			(46 (return LOOKUP_ERROR))
			(24 (return LOOKUP_ERROR))
			(34 (return LOOKUP_ERROR))
			(16 (return LOOKUP_ERROR))
			(19 (return LOOKUP_ERROR))
			(9 (return LOOKUP_ERROR))
			(10 (return LOOKUP_ERROR))
			(48 (return LOOKUP_ERROR))
			(41 (return LOOKUP_ERROR))
			(1 (return LOOKUP_ERROR))
			(14 (return LOOKUP_ERROR))
			(50 (return LOOKUP_ERROR))
			(21 (return LOOKUP_ERROR))
			(18 (return LOOKUP_ERROR))
			(22 (return LOOKUP_ERROR))
			(44 (return LOOKUP_ERROR))
			(12 (return LOOKUP_ERROR))
			(else  (return LOOKUP_ERROR))
		)
	)
)

(instance oNarrator of Narrator
	(properties)
)

(instance moTorin of Mouth
	(properties
		view -5427
	)
	
	(method (init)
		(= client ego)
		(super init: &rest)
	)
)

(instance toTorin of Talker
	(properties)
	
	(method (init)
		(= mouth LOOKUP_ERROR)
		(super init: &rest)
	)
)

(instance oMessager of TPMessager
	(properties)
	
	(method (init)
		(super init: &rest)
		(= msgType 2)
	)
	
	(method (findTalker param1)
		(cond 
			((not nGoingTo) (return LOOKUP_ERROR))
			((== param1 99) (return LOOKUP_ERROR))
			((== param1 1)
				(if gToTorinPullsOutMeat
					(return gToTorinPullsOutMeat)
				else
					(return LOOKUP_ERROR)
				)
			)
			((== param1 19) (if gToSam (return gToSam) else (return LOOKUP_ERROR)))
			((== param1 20) (if gToMax (return gToMax) else (return LOOKUP_ERROR)))
			((== param1 13)
				(switch global226
					(0 (return LOOKUP_ERROR))
					(1 (return (ScriptID 15000 1)))
					(2 (return (ScriptID 15000 2)))
					(3 (return (ScriptID 15100 1)))
					(4 (return (ScriptID 15100 2)))
				)
			)
			((== param1 9)
				(if (== curRoomNum 14000)
					(return (ScriptID 14000 1))
				else
					(return LOOKUP_ERROR)
				)
			)
			((== param1 10)
				(if (== curRoomNum 14000)
					(return (ScriptID 14000 2))
				else
					(return LOOKUP_ERROR)
				)
			)
			((== param1 12) (return (ScriptID 16100 1)))
			((== param1 25) (return (ScriptID 50500 1)))
			((== param1 26) (return (ScriptID 50400 1)))
			((== param1 34)
				(switch global233
					(0 (return LOOKUP_ERROR))
					(1 (return (ScriptID 50900 1)))
					(2 (return (ScriptID 50900 2)))
				)
			)
			((== param1 32)
				(switch global237
					(0 (return LOOKUP_ERROR))
					(1 (return (ScriptID 50900 3)))
				)
			)
			((== param1 35) (return (ScriptID 50900 4)))
			((== param1 36)
				(if gToArchivistCU
					(return gToArchivistCU)
				else
					(return LOOKUP_ERROR)
				)
			)
			((== param1 48)
				(if gToSmetana
					(return gToSmetana)
				else
					(return LOOKUP_ERROR)
				)
			)
			((== param1 23)
				(if gToLeenahReactsToLocket
					(return gToLeenahReactsToLocket)
				else
					(return LOOKUP_ERROR)
				)
			)
			((== param1 18)
				(if gToVederPillow
					(return gToVederPillow)
				else
					(return LOOKUP_ERROR)
				)
			)
			((== param1 21)
				(if gToTripeMeat
					(return gToTripeMeat)
				else
					(return LOOKUP_ERROR)
				)
			)
			((== param1 22)
				(if gToVisceraMeat
					(return gToVisceraMeat)
				else
					(return LOOKUP_ERROR)
				)
			)
			(else (return LOOKUP_ERROR))
		)
		(return LOOKUP_ERROR)
	)
)
