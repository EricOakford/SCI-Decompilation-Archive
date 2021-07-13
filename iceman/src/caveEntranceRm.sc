;;; Sierra Script 1.0 - (do not remove this comment)
(script# 56)
(include game.sh)
(use Main)
(use Intrface)
(use scubaRg)
(use NormalEgo)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	caveEntranceRm 0
)

(local
	local0 =  1
)
(instance caveEntranceRm of Room
	(properties
		picture 56
		north 999
		east 55
		south 51
		west 57
	)
	
	(method (init)
		(super init:)
		(self setRegions: 305 setFeatures: caveEntranceFeat)
		(ego init:)
		(switch prevRoomNum
			(59
				(ego posn: 80 80 loop: 0 setMotion: MoveTo 100 70 self)
				(= local0 0)
			)
			(55
				(ego x: 310 loop: 1 setMotion: MoveTo -5 (ego y?))
			)
			(57
				(ego x: 10 loop: 0 setMotion: MoveTo 325 (ego y?))
			)
			(else 
				(ego posn: 133 176 loop: 3 setMotion: MoveTo 133 -5)
			)
		)
		(AddWavingPlant 5 88 118 8)
		(AddWavingPlant 6 26 119 8)
		(AddWavingPlant 6 74 91 5)
		(AddWavingPlant 7 271 126 8)
		(AddWavingPlant 5 309 124 8)
		(AddWavingPlant 5 285 85 5)
		(AddWavingPlant 6 210 83 4)
		(AddUnderwaterObj 0 0 31 120 8)
		(AddUnderwaterObj 0 0 60 92)
		(AddUnderwaterObj 0 1 24 97)
		(AddUnderwaterObj 0 2 87 117)
		(AddUnderwaterObj 0 1 304 123)
		(AddUnderwaterObj 4 1 270 124)
		(addToPics doit:)
		(if
			(and
				(not (ego has: iRumBottle))
				(not ((inventory at: iRumBottle) ownedBy: 55))
			)
			(bottle init: cel: (if (< numColors 7) 2 else 1))
		)
	)
	
	(method (doit)
		(if (and local0 (== (ego onControl: origin) cRED))
			(ego setScript: goInCaveScript)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,for,around][/room,cave]')
				(Print 56 0)
			)
		)
	)
	
	(method (cue)
		(= local0 1)
	)
)

(instance caveEntranceFeat of Feature
	(properties
		y 74
		x 48
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]/cave')
				(Print 56 1)
			)
		)
	)
)

(instance goInCaveScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreControl: cWHITE setMotion: MoveTo 50 83 self)
			)
			(1
				(HandsOn)
				(NormalEgo)
				(curRoom newRoom: 59)
			)
		)
	)
)

(instance bottle of View
	(properties
		y 95
		x 262
		view 54
		loop 5
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]>')
				(if
					(or
						(Said '/bottle,object,thing')
						(InRect 200 85 300 125 ego)
					)
					(event claimed: TRUE)
					(Print 56 2)
				)
			)
			((Said 'get/bottle')
				(if (InRect 160 85 300 125 ego)
					(ego setScript: getBottle)
				else
					(Print 56 3)
				)
			)
		)
	)
)

(instance getBottle of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0)
				(cond 
					((InRect 160 85 263 102 ego)
						(ego setMotion: MoveTo 200 94 self)
					)
					((InRect 263 85 300 102 ego)
						(ego setMotion: MoveTo 275 94 self)
					)
					((InRect 160 102 263 125 ego)
						(ego setMotion: MoveTo 200 110 self)
					)
					(else
						(ego setMotion: MoveTo 275 110 self)
					)
				)
			)
			(1
				(if (< 102 (ego y?))
					(ego setMotion: MoveTo 263 110 self)
				else
					(ego setMotion: MoveTo 263 94 self)
				)
			)
			(2
				(ego get: iRumBottle)
				(if (< 102 (ego y?))
					(ego setMotion: MoveTo 263 94 self)
				else
					(ego setMotion: MoveTo 263 110 self)
				)
			)
			(3
				(bottle dispose:)
				(ego
					illegalBits: cWHITE
					setMotion: MoveTo 155 (ego y?) self
				)
			)
			(4
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
