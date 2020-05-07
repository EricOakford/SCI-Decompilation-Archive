;;; Sierra Script 1.0 - (do not remove this comment)
(script# HAREM) ;6
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoHarem 0
)

(instance demoHarem of Room
	(properties
		picture 430
		style IRISIN
	)
	
	(method (init)
		(super init:)
		(ego view: 438 loop: 0 cel: 2 posn: 183 136 init:)
		(servant init:)
		(girl init:)
		(veil init:)
		(vase init: addToPic:)
		(addToPics doit:)
		(globalSound number: 430 loop: -1 playBed:)
		(Print HAREM 0 #at -1 12 #dispose)
		(self setScript: rmScript)
	)
)

(instance girl of Prop
	(properties
		x 78
		y 119
		view 435
	)
)

(instance servant of Prop
	(properties
		x 110
		y 116
		view 432
	)
)

(instance veil of PicView
	(properties
		x 83
		y 125
		view 430
		priority 12
	)
)

(instance vase of View
	(properties
		x 149
		y 114
		view 430
		loop 1
		cel 1
	)
)

(instance rmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(girl loop: 0 cel: 0 cycleSpeed: 2 setCycle: EndLoop self)
				(servant loop: 0 cel: 0 cycleSpeed: 2)
			)
			(1
				(if censored
					(self cue:)
				else
					(girl loop: 1 cel: 0 setCycle: EndLoop self)
				)
			)
			(2
				(servant setCycle: EndLoop)
				(= cycles 7)
			)
			(3
				(servant setCycle: BegLoop)
				(= cycles 10)
			)
			(4
				(servant loop: 1 cel: 0 setCycle: Forward)
				(= seconds 3)
			)
			(5
				(ego cycleSpeed: 2 setCycle: BegLoop)
				(= seconds 3)
			)
			(6 (cls) (curRoom newRoom: DESERT))
		)
	)
)
