;;; Sierra Script 1.0 - (do not remove this comment)
(script# 61)
(include sci.sh)
(use Main)
(use scubaRg)
(use Motion)
(use Game)

(public
	tunisBeachRm 0
)

(instance tunisBeachRm of Rm
	(properties
		picture 61
		east 58
		south 51
		west 54
	)
	
	(method (init)
		(super init:)
		(self setRegions: 305)
		(ego init: observeControl: 1024)
		(switch prevRoomNum
			(54
				(ego x: 10 loop: 0 setMotion: MoveTo 325 (ego y?))
			)
			(58
				(ego x: 310 loop: 1 setMotion: MoveTo -5 (ego y?))
			)
			(else 
				(ego posn: 172 180 loop: 3 setMotion: MoveTo 172 -5)
			)
		)
		(AddWavingPlant 6 192 84 4)
		(AddWavingPlant 6 293 93 5)
		(AddWavingPlant 7 272 128 9)
		(AddWavingPlant 7 97 105 6)
		(AddWavingPlant 6 53 107 7)
		(AddWavingPlant 7 26 149 11)
		(AddWavingPlant 5 80 142 11)
		(AddWavingPlant 6 7 182 14)
		(AddWavingPlant 8 315 134 9)
		(AddUnderwaterObj 2 0 139 94)
		(AddUnderwaterObj 2 0 44 149)
		(AddUnderwaterObj 0 0 10 133)
		(AddUnderwaterObj 0 0 23 178)
		(AddUnderwaterObj 1 0 263 94)
		(AddUnderwaterObj 3 0 278 126)
		(AddUnderwaterObj 2 2 214 86)
		(AddUnderwaterObj 0 2 313 135)
		(AddUnderwaterObj 1 4 308 133 0)
		(addToPics doit:)
	)
)
