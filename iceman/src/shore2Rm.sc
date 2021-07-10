;;; Sierra Script 1.0 - (do not remove this comment)
(script# 55)
(include sci.sh)
(use Main)
(use scubaRg)
(use Motion)
(use Game)

(public
	shore2Rm 0
)

(instance shore2Rm of Rm
	(properties
		picture 55
		north 999
		east 53
		south 51
		west 56
	)
	
	(method (init)
		(super init:)
		(self setRegions: 305)
		(ego init: observeControl: 1024)
		(switch prevRoomNum
			(56
				(ego x: 10 loop: 0 setMotion: MoveTo 325 (ego y?))
			)
			(53
				(ego x: 310 loop: 1 setMotion: MoveTo -5 (ego y?))
			)
			(else 
				(ego posn: 122 185 loop: 3 setMotion: MoveTo 122 -5)
			)
		)
		(AddWavingPlant 6 121 131 9)
		(AddWavingPlant 5 148 129 9)
		(AddWavingPlant 5 194 127 9)
		(AddWavingPlant 7 271 125 8)
		(AddWavingPlant 7 48 179 14)
		(AddWavingPlant 6 31 105 6)
		(AddWavingPlant 5 74 106 7)
		(AddWavingPlant 6 231 71 3)
		(AddWavingPlant 5 183 72 3)
		(AddWavingPlant 5 44 78 4)
		(AddUnderwaterObj 0 0 230 125 8)
		(AddUnderwaterObj 0 1 260 129 9)
		(AddUnderwaterObj 0 2 205 137 10)
		(AddUnderwaterObj 2 2 296 130 9)
		(AddUnderwaterObj 1 0 290 85 5)
		(AddUnderwaterObj 3 0 35 179 14)
		(addToPics doit:)
	)
)
