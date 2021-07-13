;;; Sierra Script 1.0 - (do not remove this comment)
(script# 58)
(include game.sh)
(use Main)
(use scubaRg)
(use Motion)
(use Game)

(public
	eastDeadEndRm 0
)

(instance eastDeadEndRm of Room
	(properties
		picture 58
		north 999
		south 51
		west 61
	)
	
	(method (init)
		(super init:)
		(self setRegions: 305)
		(ego init:)
		(switch prevRoomNum
			(61
				(ego x: 10 loop: 0 setMotion: MoveTo 325 (ego y?))
			)
			(else 
				(ego posn: 61 180 loop: 3 setMotion: MoveTo 61 -5)
			)
		)
		(AddWavingPlant 5 61 102 6)
		(AddWavingPlant 6 218 128 9)
		(AddWavingPlant 6 232 97 6)
		(AddWavingPlant 5 189 103 6)
		(AddWavingPlant 7 251 139 10)
		(AddWavingPlant 6 18 85 5)
		(AddWavingPlant 6 76 87 5)
		(AddWavingPlant 6 113 103 6)
		(AddUnderwaterObj 0 0 222 102 5)
		(AddUnderwaterObj 1 3 215 96 5)
		(AddUnderwaterObj 1 3 277 105 6)
		(AddUnderwaterObj 1 2 73 103 6)
		(AddUnderwaterObj 3 1 296 115 7)
		(AddUnderwaterObj 3 0 236 135 9)
		(AddUnderwaterObj 3 2 149 141 10)
		(AddUnderwaterObj 1 3 184 101 6)
		(AddUnderwaterObj 1 4 250 109 0)
		(AddUnderwaterObj 1 4 167 121 0)
		(AddUnderwaterObj 1 4 206 138 0)
		(addToPics doit:)
	)
)
