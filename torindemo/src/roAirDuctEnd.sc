;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50300)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use TPSound)
(use Motion)
(use Actor)

(public
	roAirDuctEnd 0
)

(instance oTorinCycle of Walk
	(properties)
)

(instance poTorinFalls of Prop
	(properties
		view -15235
	)
)

(instance aoTorinCrawls of Actor
	(properties
		view -15335
		xStep 10
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: oTorinCycle)
	)
	
	(method (cantBeHere)
		(return 0)
	)
	
	(method (setHeading h)
		(= heading h)
	)
)

(instance oWhoosh of TPSound
	(properties)
)

(instance soTorinFallsThroughSpace of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(aoTorinCrawls
					loop: 0
					cel: 4
					posn: 660 80
					init:
					setMotion: MoveTo 390 80 self
				)
			)
			(1
				(aoTorinCrawls dispose:)
				(poTorinFalls loop: 0 cel: 0 posn: 390 80 init:)
				(messager say: 0 0 1 1 self)
			)
			(2
				(poTorinFalls setCycle: CT 15 1 self)
			)
			(3
				(sound1 lThumbLoop: -15235)
				(poTorinFalls setCycle: End self)
			)
			(4
				(poTorinFalls dispose:)
				(curRoom picture: -15235)
				(curRoom drawPic: -15235)
				(poTorinFalls
					loop: 1
					cel: 0
					posn: 316 120
					init:
					setCycle: Fwd
				)
				(messager sayRange: 0 0 1 2 3 self)
			)
			(5 (curRoom newRoom: -15136))
		)
	)
)

(instance roAirDuctEnd of TPRoom
	(properties
		picture -15236
	)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: -15236)
		(curRoom setScript: soTorinFallsThroughSpace)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
