;;; Sierra Script 1.0 - (do not remove this comment)
(script# 31)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm31 0
)

(local
	numClouds
	noEntry
)
(instance rm31 of Rm
	(properties
		picture 31
		horizon 1
	)
	
	(method (init &tmp temp0 temp1)
		(Load rsVIEW 300)
		(Load rsVIEW 130)
		(Load rsVIEW 301)
		(Load rsVIEW 620)
		(super init:)
		(addToPics
			add: aView1 aView2 aView3 aView4 aView5 aView6
			doit:
		)
		(aHorizonBow setCycle: Fwd cycleSpeed: 2 isExtra: 1 init:)
		(aHorizonStern
			setCycle: Fwd
			cycleSpeed: 2
			isExtra: 1
			init:
		)
		(aBar setCycle: Fwd cycleSpeed: 30 isExtra: 1 init:)
		(aFlag setCycle: Fwd cycleSpeed: 3 isExtra: 1 init:)
		(aWakeRear setCycle: Fwd cycleSpeed: 3 isExtra: 1 init:)
		(aWakeFront setCycle: Fwd cycleSpeed: 4 isExtra: 1 init:)
		(cond 
			((> machineSpeed 60) (= numClouds 3))
			((> machineSpeed 40) (= numClouds 2))
			((> machineSpeed 20) (= numClouds 1))
		)
		(= temp0 0)
		(while (< temp0 numClouds)
			((Act new:)
				view: 620
				ignoreHorizon:
				ignoreActors:
				illegalBits: 0
				setScript: (cloudScript new:)
			)
			(++ temp0)
		)
		(if (== (= currentEgoView (ego view?)) 132)
			(ego view: 129)
		else
			(ego view: 130)
		)
		(ego
			setLoop: 0
			setPri: 14
			setCycle: Walk
			setStep: 1 1
			illegalBits: -32768
		)
		(cond 
			((== prevRoomNum 38) (ego posn: 179 74))
			((== prevRoomNum 37) (ego posn: 97 85))
			((== prevRoomNum 36) (ego posn: 112 62))
			((== prevRoomNum 35) (ego posn: 215 50))
			((== prevRoomNum 34) (ego posn: 299 85))
			(else (ego posn: 280 113))
		)
		(ego init:)
		(User canControl: 1 canInput: 1)
		(= currentStatus 0)
		(self setRegions: 300 setScript: rm31Script)
	)
)

(instance rm31Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(== currentEgoView 132)
					(& (ego onControl:) $00e0)
				)
				(if (== noEntry 0) (Print 31 0) (= noEntry 1))
			)
			((& (ego onControl:) $0100) (curRoom newRoom: 38))
			((& (ego onControl:) $0080) (curRoom newRoom: 37))
			((& (ego onControl:) $0040) (curRoom newRoom: 36))
			((& (ego onControl:) $0020) (curRoom newRoom: 35))
			((& (ego onControl:) $0010) (curRoom newRoom: 34))
			((& (ego onControl:) $0002) (curRoom newRoom: 32))
			(else (= noEntry 0))
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'climb/stair') (Print 31 1))
		(if
			(or
				(Said 'open/door')
				(Said 'board,(board<to),explore/cabin,airport')
			)
			(Print 31 2)
		)
		(if (Said 'look>')
			(if (Said '/corridor') (Print 31 3))
			(if (Said '/stair') (Print 31 4))
			(if (Said '/flag') (Print 31 5))
			(if (Said '[/craft,boat,cloud,airport]') (Print 31 6))
		)
	)
)

(instance cloudScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(client posn: (Random 0 270) (Random 10 65) init:)
				(self changeState: 2)
			)
			(1
				(= temp0 (Random 1 80))
				(= temp1 (Random 0 10))
				(client
					setCel: temp1
					posn: (- 1 (CelWide 620 0 temp1)) temp0
				)
				(self changeState: 2)
			)
			(2
				(client
					setStep: (Random 1 3) -1
					setMotion: MoveTo 321 (client y?) self
				)
				(= state 0)
			)
		)
	)
)

(instance aView1 of PV
	(properties
		y 75
		x 159
		view 301
		priority 14
		signal $4000
	)
)

(instance aView2 of PV
	(properties
		y 75
		x 199
		view 301
		priority 14
		signal $4000
	)
)

(instance aView3 of PV
	(properties
		y 75
		x 217
		view 301
		priority 14
		signal $4000
	)
)

(instance aView4 of PV
	(properties
		y 75
		x 234
		view 301
		priority 14
		signal $4000
	)
)

(instance aView5 of PV
	(properties
		y 75
		x 251
		view 301
		priority 14
		signal $4000
	)
)

(instance aView6 of PV
	(properties
		y 75
		x 268
		view 301
		priority 14
		signal $4000
	)
)

(instance aHorizonBow of Prop
	(properties
		y 102
		x 8
		view 300
		loop 5
		priority 3
	)
)

(instance aHorizonStern of Prop
	(properties
		y 102
		x 317
		view 300
		loop 6
		priority 3
	)
)

(instance aBar of Prop
	(properties
		y 44
		x 216
		view 300
		priority 15
	)
)

(instance aFlag of Prop
	(properties
		y 22
		x 99
		view 300
		loop 1
		priority 1
	)
)

(instance aWakeRear of Prop
	(properties
		y 118
		x 267
		view 300
		loop 2
		priority 10
	)
)

(instance aWakeFront of Prop
	(properties
		y 119
		x 108
		view 300
		loop 3
		priority 10
	)
)
