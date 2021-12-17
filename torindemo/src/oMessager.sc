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
			(99 (return {The Game}))
			(37 (return {Acrobat}))
			(33 (return {Archer}))
			(36 (return {R. Kyvest}))
			(39 (return {Asst. Director}))
			(27 (return {Audience}))
			(2 (return {Boogle}))
			(38 (return {Carpenter}))
			(32 (return {Centipede}))
			(51 (return {The crowd}))
			(42 (return {Mr. Death}))
			(17 (return {Queen Di}))
			(5 (return {Dreep}))
			(31 (return {Grass}))
			(13 (return {Herman}))
			(28 (return {Inchie}))
			(45 (return {Junior}))
			(25 (return {Da Judge}))
			(6 (return {Kurtzwell}))
			(23 (return {Leenah}))
			(4 (return {Lycentia}))
			(11 (return {Lycentia}))
			(35 (return {Magician}))
			(20 (return {Max}))
			(15 (return {Mrs. Bitternut}))
			(8 (return {Ma}))
			(26 (return {Mrs. Plant}))
			(7 (return {Pa}))
			(43 (return {Odalisques}))
			(3 (return {Pecand}))
			(49 (return {Pergolans}))
			(46 (return {Phace}))
			(24 (return {The Cop}))
			(34 (return {Rabbit}))
			(16 (return {Rupert}))
			(19 (return {Sam}))
			(9 (return {Slim}))
			(10 (return {Slime}))
			(48 (return {Smetana}))
			(41 (return {Torin thinks...}))
			(1 (return {Torin}))
			(14 (return {Game tip}))
			(50 (return {Sappy Tree}))
			(21 (return {Tripe}))
			(18 (return {Veder}))
			(22 (return {Viscera}))
			(44 (return {Announcer}))
			(12 (return {Zax}))
			(else  (return {Somebody}))
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
		(= mouth moTorin)
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
			((not nGoingTo) (return oNarrator))
			((== param1 99) (return oNarrator))
			((== param1 1)
				(if gToTorinPullsOutMeat
					(return gToTorinPullsOutMeat)
				else
					(return toTorin)
				)
			)
			((== param1 19) (if gToSam (return gToSam) else (return oNarrator)))
			((== param1 20) (if gToMax (return gToMax) else (return oNarrator)))
			((== param1 13)
				(switch global226
					(0 (return oNarrator))
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
					(return oNarrator)
				)
			)
			((== param1 10)
				(if (== curRoomNum 14000)
					(return (ScriptID 14000 2))
				else
					(return oNarrator)
				)
			)
			((== param1 12) (return (ScriptID 16100 1)))
			((== param1 25) (return (ScriptID 50500 1)))
			((== param1 26) (return (ScriptID 50400 1)))
			((== param1 34)
				(switch global233
					(0 (return oNarrator))
					(1 (return (ScriptID 50900 1)))
					(2 (return (ScriptID 50900 2)))
				)
			)
			((== param1 32)
				(switch global237
					(0 (return oNarrator))
					(1 (return (ScriptID 50900 3)))
				)
			)
			((== param1 35) (return (ScriptID 50900 4)))
			((== param1 36)
				(if gToArchivistCU
					(return gToArchivistCU)
				else
					(return oNarrator)
				)
			)
			((== param1 48)
				(if gToSmetana
					(return gToSmetana)
				else
					(return oNarrator)
				)
			)
			((== param1 23)
				(if gToLeenahReactsToLocket
					(return gToLeenahReactsToLocket)
				else
					(return oNarrator)
				)
			)
			((== param1 18)
				(if gToVederPillow
					(return gToVederPillow)
				else
					(return oNarrator)
				)
			)
			((== param1 21)
				(if gToTripeMeat
					(return gToTripeMeat)
				else
					(return oNarrator)
				)
			)
			((== param1 22)
				(if gToVisceraMeat
					(return gToVisceraMeat)
				else
					(return oNarrator)
				)
			)
			(else (return oNarrator))
		)
		(return oNarrator)
	)
)
