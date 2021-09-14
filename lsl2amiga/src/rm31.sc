;;; Sierra Script 1.0 - (do not remove this comment)
(script# 31)
(include game.sh)
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
	aCloud
	noEntry
)
(instance rm31 of Room
	(properties
		picture 31
		horizon 1
	)
	
	(method (init &tmp i temp1)
		(Load VIEW 300)
		(Load VIEW 130)
		(Load VIEW 301)
		(Load VIEW 620)
		(super init:)
		(addToPics
			add: aView1 aView2 aView3 aView4 aView5 aView6
			doit:
		)
		(aHorizonBow
			setCycle: Forward
			cycleSpeed: 2
			isExtra: TRUE
			init:
		)
		(aHorizonStern
			setCycle: Forward
			cycleSpeed: 2
			isExtra: TRUE
			init:
		)
		(aBar
			setCycle: Forward
			cycleSpeed: 30
			isExtra: TRUE
			init:
		)
		(aFlag
			setCycle: Forward
			cycleSpeed: 3
			isExtra: TRUE
			init:
		)
		(aWakeRear
			setCycle: Forward
			cycleSpeed: 3
			isExtra: TRUE
			init:
		)
		(aWakeFront
			setCycle: Forward
			cycleSpeed: 4
			isExtra: TRUE
			init:
		)
		(cond 
			((> machineSpeed 60)
				(= aCloud 3)
			)
			((> machineSpeed 40)
				(= aCloud 2)
			)
			((> machineSpeed 20)
				(= aCloud 1)
			)
		)
		(for ((= i 0)) (< i aCloud) ((++ i))
			((Actor new:)
				view: 620
				ignoreHorizon:
				ignoreActors:
				illegalBits: 0
				setScript: (cloudScript new:)
			)
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
			illegalBits: cWHITE
		)
		(cond 
			((== prevRoomNum 38)
				(ego posn: 179 74)
			)
			((== prevRoomNum 37)
				(ego posn: 97 85)
			)
			((== prevRoomNum 36)
				(ego posn: 112 62)
			)
			((== prevRoomNum 35)
				(ego posn: 215 50)
			)
			((== prevRoomNum 34)
				(ego posn: 299 85)
			)
			(else
				(ego posn: 280 113)
			)
		)
		(ego init:)
		(User canControl: TRUE canInput: TRUE)
		(= currentStatus egoNORMAL)
		(self setRegions: SHIP setScript: rm31Script)
	)
)

(instance rm31Script of Script
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(== currentEgoView 132)
					(& (ego onControl:) (| cMAGENTA cBROWN cLGREY))
				)
				(if (== noEntry FALSE)
					(Print 31 0)
					(= noEntry TRUE)
				)
			)
			((& (ego onControl:) cGREY)
				(curRoom newRoom: 38)
			)
			((& (ego onControl:) cLGREY)
				(curRoom newRoom: 37)
			)
			((& (ego onControl:) cBROWN)
				(curRoom newRoom: 36)
			)
			((& (ego onControl:) cMAGENTA)
				(curRoom newRoom: 35)
			)
			((& (ego onControl:) cRED)
				(curRoom newRoom: 34)
			)
			((& (ego onControl:) cBLUE)
				(curRoom newRoom: 32)
			)
			(else
				(= noEntry FALSE)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'climb/stair')
			(Print 31 1)
		)
		(if
			(or
				(Said 'open/door')
				(Said 'board,(board<to),explore/cabin,airport')
			)
			(Print 31 2)
		)
		(if (Said 'look>')
			(if (Said '/corridor')
				(Print 31 3)
			)
			(if (Said '/stair')
				(Print 31 4)
			)
			(if (Said '/flag')
				(Print 31 5)
			)
			(if (Said '[/craft,boat,cloud,airport]')
				(Print 31 6)
			)
		)
	)
)

(instance cloudScript of Script
	(method (changeState newState &tmp theY theCel)
		(switch (= state newState)
			(0
				(client posn: (Random 0 270) (Random 10 65) init:)
				(self changeState: 2)
			)
			(1
				(= theY (Random 1 80))
				(= theCel (Random 0 10))
				(client
					setCel: theCel
					posn: (- 1 (CelWide 620 0 theCel)) theY
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

(instance aView1 of PicView
	(properties
		y 75
		x 159
		view 301
		priority 14
		signal ignrAct
	)
)

(instance aView2 of PicView
	(properties
		y 75
		x 199
		view 301
		priority 14
		signal ignrAct
	)
)

(instance aView3 of PicView
	(properties
		y 75
		x 217
		view 301
		priority 14
		signal ignrAct
	)
)

(instance aView4 of PicView
	(properties
		y 75
		x 234
		view 301
		priority 14
		signal ignrAct
	)
)

(instance aView5 of PicView
	(properties
		y 75
		x 251
		view 301
		priority 14
		signal ignrAct
	)
)

(instance aView6 of PicView
	(properties
		y 75
		x 268
		view 301
		priority 14
		signal ignrAct
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
