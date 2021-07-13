;;; Sierra Script 1.0 - (do not remove this comment)
(script# 53)
(include game.sh)
(use Main)
(use Intrface)
(use scubaRg)
(use EgoDead)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	antiSubNetRm 0
)

(local
	local0
	local1
)
(instance antiSubNetRm of Room
	(properties
		picture 53
		north 45
		east 54
		south 51
		west 55
	)
	
	(method (init)
		(super init:)
		(self setRegions: 305)
		(ego init:)
		(AddWavingPlant 5 160 116 8)
		(AddWavingPlant 5 209 119 8)
		(AddWavingPlant 5 39 130 9)
		(AddWavingPlant 5 24 61 2)
		(AddWavingPlant 6 97 167 12)
		(AddWavingPlant 6 125 131 9)
		(AddWavingPlant 7 291 167 12)
		(AddWavingPlant 6 49 178 13)
		(AddWavingPlant 6 309 66 3)
		(AddUnderwaterObj 4 1 288 68)
		(addToPics doit:)
		(switch prevRoomNum
			(54
				(if (< (ego y?) 55)
					(ego x: 310 y: 58 loop: 1 setMotion: MoveTo -5 58)
				else
					(ego x: 310 loop: 1 setMotion: MoveTo -5 (ego y?))
				)
			)
			(55
				(if (< 50 (ego y?))
					(ego x: 10 loop: 0 setMotion: MoveTo 325 (ego y?))
				else
					(ego x: 10 y: 70 loop: 0 setMotion: MoveTo 325 70)
				)
			)
			(68
				(HandsOff)
				(ego illegalBits: 0 posn: 1 33 setScript: exitCave)
			)
			(45
				(ego posn: 160 4 loop: 2 setMotion: MoveTo 160 200)
			)
			(else 
				(ego posn: 160 174 loop: 3 setMotion: MoveTo 160 -5)
			)
		)
	)
	
	(method (doit)
		(cond 
			((and local0 (< (ego y?) 40))
				(= local0 0)
				(theGame changeScore: 1)
			)
			((and local1 (< 60 (ego y?)))
				(= local1 0)
				(theGame changeScore: -11)
			)
			(
				(and
					(< 45 (ego y?))
					(< (ego y?) 55)
					(not (ego script?))
					(not local1)
					(not local0)
				)
				(ego setScript: hitNet)
			)
		)
		(if
			(and
				(< (ego y?) 43)
				(< (ego x?) 48)
				(not (ego script?))
			)
			(ego setScript: enterCave)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '(turn<on),use,activate/device')
				(cond 
					((not (ego has: iDevice))
						(Print 53 0)
					)
					((or local0 local1)
						(Print 53 1)
					)
					(else
						(Print 53 2)
						(if (< 54 (ego y?))
							(= local0 1)
						else
							(= local1 1)
						)
					)
				)
			)
			((Said '(turn<off),cease/device')
				(cond 
					((or local0 local1) (== local0 0) (== local1 0))
					((ego has: iDevice)
						(Print 53 3)
					)
					(else
						(Print 53 4)
					)
				)
			)
		)
	)
)

(instance leftNet of Feature
	(properties
		y 100
		x 30
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look<through/net')
				(Print 53 5)
				(Print 53 6)
			)
		)
	)
)

(instance rightNet of Feature
	(properties
		y 100
		x 230
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look<through/net')
				(Print 53 7)
			)
		)
	)
)

(instance exitCave of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 100 33 self)
			)
			(1
				(ego illegalBits: cWHITE setMotion: MoveTo 280 -5 self)
			)
			(2
				(HandsOn)
				(antiSubNetRm newRoom: 55)
			)
		)
	)
)

(instance enterCave of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (antiSubNetRm newRoom: 68))
		)
	)
)

(instance hitNet of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 53 8)
				(EgoDead 157 0 0 53 9)
			)
		)
	)
)
