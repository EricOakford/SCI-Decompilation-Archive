;;; Sierra Script 1.0 - (do not remove this comment)
(script# 5130)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use System)

(public
	rm5v13 0
)

(instance rm5v13 of ShiversRoom
	(properties
		picture 5010
	)
	
	(method (init)
		(if (>= prevRoomNum 6000)
			(proc951_7 10403)
			(proc951_9 10403)
		)
		(proc951_7 10501)
		(proc951_7 10504)
		(pDnButton init:)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(pWindow init:)
		(super init: &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 5150
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 5120
	)
	
	(method (init)
		(self
			createPoly: 244 4 244 103 249 103 249 125 242 125 242 142 264 142 264 2
		)
		(super init: &rest)
	)
)

(instance pDnButton of ShiversProp
	(properties
		view 5010
		loop 1
		cel 1
	)
	
	(method (init)
		(self
			setPri: 25
			cel: 1
			createPoly: 233 114 233 124 248 124 248 114
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(self setScript: sGoDn)
		(proc951_4 6)
	)
)

(instance pWindow of ShiversProp
	(properties
		priority 25
		fixPriority 1
		view 5010
		loop 4
	)
	
	(method (init)
		(pWindow setCel: (pWindow lastCel:))
		(super init: &rest)
	)
	
	(method (doVerb)
	)
)

(instance sGoDn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(pDnButton cel: 0)
				(sounds play: 10501 0 30 self)
			)
			(1
				(pDnButton cel: 1)
				(sounds play: 10505 0 90 self)
			)
			(2
				(sounds play: 10403 -1 0 0)
				(sounds fade: 10403 20 15 20 0 0)
				(sounds play: 10504 0 90 self)
				(self cue: self)
			)
			(3 (= ticks 50))
			(4
				(pWindow cycleSpeed: 22 setCycle: Beg self)
			)
			(5
				(pWindow setLoop: 3)
				(pWindow
					setCel: (pWindow lastCel:)
					cycleSpeed: 16
					setCycle: Beg self
				)
			)
			(6
				(pWindow
					cel: (pWindow lastCel:)
					loop: 2
					cycleSpeed: 22
					setCycle: Beg self
				)
			)
			(7 (pDnButton cel: 0))
			(8
				(theGame handsOn:)
				(curRoom newRoom: 5030)
			)
		)
	)
)
