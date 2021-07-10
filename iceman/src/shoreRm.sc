;;; Sierra Script 1.0 - (do not remove this comment)
(script# 54)
(include sci.sh)
(use Main)
(use scubaRg)
(use Motion)
(use Game)

(public
	shoreRm 0
)

(instance shoreRm of Rm
	(properties
		picture 54
		north 999
		east 61
		south 51
		west 53
	)
	
	(method (init)
		(super init:)
		(self setRegions: 305)
		(ego init:)
		(switch prevRoomNum
			(61
				(ego x: 310 loop: 1 setMotion: MoveTo -5 (ego y?))
			)
			(53
				(ego x: 10 loop: 0 setMotion: MoveTo 325 (ego y?))
			)
			(else 
				(ego posn: 199 185 loop: 3 setMotion: MoveTo 199 -5)
			)
		)
		(AddUnderwaterObj 3 0 147 97 6)
		(AddUnderwaterObj 1 0 49 146 10)
		(AddUnderwaterObj 1 0 83 84)
		(AddUnderwaterObj 3 1 25 132 9)
		(AddUnderwaterObj 2 2 162 158 12)
		(AddUnderwaterObj 2 0 265 184 14)
		(AddUnderwaterObj 4 1 118 156 11)
		(AddWavingPlant 5 176 154 11)
		(AddWavingPlant 6 79 165 12)
		(AddWavingPlant 6 22 185 14)
		(AddWavingPlant 6 284 179 14)
		(AddWavingPlant 8 283 106 7)
		(AddWavingPlant 8 319 104 6)
		(AddWavingPlant 8 79 158 11)
		(AddWavingPlant 8 130 93 5)
		(AddWavingPlant 8 285 81 4)
		(AddWavingPlant 6 259 79 4)
		(AddWavingPlant 6 177 93 5)
		(AddWavingPlant 5 55 77 4)
		(addToPics doit:)
	)
)
