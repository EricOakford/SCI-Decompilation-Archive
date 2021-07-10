;;; Sierra Script 1.0 - (do not remove this comment)
(script# 57)
(include sci.sh)
(use Main)
(use scubaRg)
(use Motion)
(use Game)
(use System)

(public
	westDeadEndRm 0
)

(instance westDeadEndRm of Rm
	(properties
		picture 57
		north 999
		east 56
		south 51
	)
	
	(method (init)
		(super init:)
		(self setRegions: 305)
		(ego init:)
		(switch prevRoomNum
			(56
				(ego x: 310 loop: 1 setMotion: MoveTo -5 (ego y?))
			)
			(else 
				(ego setScript: fromTheDeep)
			)
		)
		(AddWavingPlant 8 205 103 6)
		(AddWavingPlant 8 179 128 9)
		(AddWavingPlant 6 41 103 6)
		(AddWavingPlant 6 276 85 5)
		(AddWavingPlant 8 317 147 10)
		(AddWavingPlant 7 105 92 5)
		(AddWavingPlant 5 237 96 6)
		(AddWavingPlant 8 119 100 6)
		(AddWavingPlant 8 86 116 8)
		(AddUnderwaterObj 2 1 303 142)
		(AddUnderwaterObj 4 1 289 140)
		(AddUnderwaterObj 1 4 218 145 0)
		(AddUnderwaterObj 1 4 192 143 0)
		(AddUnderwaterObj 1 4 22 118 0)
		(AddUnderwaterObj 1 4 57 132 0)
		(AddUnderwaterObj 1 4 95 104 0)
		(AddUnderwaterObj 1 4 184 117 0)
		(addToPics doit:)
	)
)

(instance fromTheDeep of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 175 148 loop: 3 setMotion: MoveTo 175 136 self)
			)
			(1
				(ego setMotion: MoveTo 325 136)
			)
		)
	)
)
