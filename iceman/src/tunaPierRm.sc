;;; Sierra Script 1.0 - (do not remove this comment)
(script# 47)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	tunaPierRm 0
)

(instance tunaPierRm of Rm
	(properties
		picture 61
		north 72
		east 51
		south 46
		west 46
	)
	
	(method (init)
		(super init:)
		(self setRegions: 305)
		(ego init:)
		(addToPics add: shadow fLeg bLeg doit:)
		(switch prevRoomNum
			(46
				(ego
					illegalBits: -32768
					observeControl: 4096
					loop: 0
					posn: 10 145
					setMotion: MoveTo 325 145
				)
			)
			(else 
				(ego
					illegalBits: -32768
					observeControl: 4096
					loop: 0
					posn: 315 100
					setMotion: MoveTo -5 100
				)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
			(Said 'bind,conceal,adjust,drop,park/vehicle,diver')
				(if (ego has: 6)
					(ego setScript: hideVehicle)
				else
					(Print 47 0)
				)
			)
			((Said 'get/vehicle,diver') (if (ego has: 6) (Print 47 1) else (Print 47 2)))
			((Said 'look') (Print 47 3))
		)
	)
)

(instance shadow of PV
	(properties
		y 158
		x 108
		view 61
		priority 0
		signal $4000
	)
)

(instance fLeg of PV
	(properties
		y 133
		x 87
		view 61
		loop 1
		priority 8
		signal $4000
	)
)

(instance bLeg of PV
	(properties
		y 94
		x 211
		view 61
		loop 1
		cel 1
		priority 3
		signal $4000
	)
)

(instance hideVehicle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 148 (ego y?) self)
			)
			(1
				(ego setMotion: MoveTo 148 98 self)
			)
			(2
				(ego illegalBits: -28672 setMotion: MoveTo 10 98 self)
			)
			(3
				(ego
					view: 154
					put: 6 curRoomNum
					setMotion: MoveTo 148 98 self
				)
				(theGame changeScore: 3)
			)
			(4
				(HandsOn)
				(ego setMotion: MoveTo 148 133 self)
			)
			(5
				(ego setMotion: MoveTo -5 133 self)
			)
		)
	)
)
