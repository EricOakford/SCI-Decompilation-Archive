;;; Sierra Script 1.0 - (do not remove this comment)
(script# 819)
(include game.sh)
(use Main)
(use Submarine_806)
(use RegionPath)
(use Smooper)
(use System)

(public
	preloadCode 0
)

(instance preloadCode of Code
	
	(method (doit roomNum)
		(cond 
			(
				(OneOf roomNum
					1 2 3 4 5 6 7 8 9 10 11 12
					13 14 16 15 199 24 8 16 17
					18 19 20 21 22 23
				)
				(cond 
					((== gamePhase 0)
						((ScriptID 371) init:)
					)
					((== gamePhase 2)
						((ScriptID 372) dispose:)
						((ScriptID 371) init:)
					)
					((== gamePhase 3)
						((ScriptID 374) dispose:)
						((ScriptID 371) init:)
					)
				)
				(= gamePhase 1)
			)
			(
				(OneOf roomNum
					25 26 27 28 29 30 31 32 33 34
					35 36 37 38 39 40 41 42 51 50
					53 54 55 56 57 58 61 45 46 47
					59 60 63 64 65 66 67 68 62 43
				)
				(cond 
					((== gamePhase 0)
						((ScriptID 372) init:)
					)
					((== gamePhase 1)
						((ScriptID 371) dispose:)
						((ScriptID 372) init:)
					)
					((== gamePhase 3)
						((ScriptID 374) dispose:)
						((ScriptID 372) init:)
					)
				)
				(= gamePhase 2)
			)
			(
				(OneOf roomNum
					70 71 72 73 74 75 76 77 78 79
					80 81 82 83 84 85 86
				)
				(cond 
					((== gamePhase 0)
						((ScriptID 374) init:)
					)
					((== gamePhase 1)
						((ScriptID 371) dispose:)
						((ScriptID 374) init:)
					)
					((== gamePhase 2)
						((ScriptID 372) dispose:)
						((ScriptID 374) init:)
					)
				)
				(= gamePhase 3)
			)
		)
		(if
			(OneOf roomNum
				1 2 3 4 5 6 7 8 9 10 11
				12 13 14 16 15 199 24 8
				16
			)
			(ScriptID rgTahiti)
		)
		(if (OneOf roomNum 1 2 3 4 5 7 12 13 9 24 8 16)
			(ScriptID rgWater)
			RegionPath
		)
		(if (OneOf roomNum 53 54 55 56 57 58 61 45 46 47)
			SmoothLooper
			(ScriptID rgScuba)
			RegionPath
		)
		(if (== roomNum 51) SmoothLooper)
		(if (OneOf roomNum 59 60 63 64 65 66 67 68 62 43)
			SmoothLooper
			(ScriptID rgCaves)
		)
		(if (OneOf roomNum 8 15 16)
			(ScriptID rgOcean)
		)
		(if
			(OneOf roomNum
				70 71 72 73 74 75 76 77 78
				79 80 81 82 83 84 85 86
			)
			(ScriptID rgTunisia)
		)
		(if (OneOf roomNum 70 71 72)
			(ScriptID 306)
		)
		(if (OneOf roomNum 73 74 75 76)
			(ScriptID 311)
		)
		(if (OneOf roomNum 77 78 79 80 82 83)
			(ScriptID 312)
		)
		(if (OneOf roomNum 17 18 19 20 21)
			(ScriptID rgWashington)
		)
		(if
			(OneOf roomNum
				25 26 27 28 29 30 31 32 33 34 35
				36 37 38 39 40 41 42 51 50
			)
			(ScriptID rgSubmarine)
			(if (not (OneOf roomNum 50 51)) Submarine)
			(ScriptID 827)
		)
		;EO: debug code added from beta
		(if (not (OneOf roomNum 11 99 27 3 39 33 25))
			((ScriptID rgDebug) init:)
		)
		(if (= useSortedFeatures (not (OneOf roomNum 39 27)))
			(ScriptID SORTCOPY)
			(ScriptID SIGHT)
			(= egoBlindSpot 90)
		)
		(Load SCRIPT GOTOSAID)
		(DisposeScript 819)
	)
)
