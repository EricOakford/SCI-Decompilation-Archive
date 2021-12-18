;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4620)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use System)

(public
	rm4v620 0
)

(instance rm4v620 of ShiversRoom
	(properties
		picture 4190
	)
	
	(method (init)
		(if (== prevRoomNum 4590)
			(proc951_7 10403)
			(proc951_9 10403)
			(sounds play: 10403 -1 0 0)
			(sounds fade: 10403 20 15 15 0 0)
		)
		(efExitLeft init: 7)
		(efExitRight init: 1)
		(if (proc951_5 6) (spDoor init:))
		(spCCTV init:)
		(super init: &rest)
	)
	
	(method (dispose)
		(sMoveCCTV dispose:)
		(super dispose: &rest)
	)
	
	(method (newRoom n)
		(if (and (>= (spDoor cel?) 1) (< n 5000))
			(sounds play: 10407 0 90 0)
		)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 4600
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 4630
	)
	
	(method (init)
		(self createPoly: 216 2 206 132 217 143 265 143 265 1)
		(super init: &rest)
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 5010
	)
	
	(method (init)
		(self createPoly: 81 121 79 20 181 20 178 120)
		(super init: &rest)
	)
)

(instance spCCTV of ShiversProp
	(properties
		priority 255
		fixPriority 1
		view 4190
	)
	
	(method (init)
		(self posn: 0 0)
		(self cycleSpeed: 8)
		(self setCycle: End)
		(sounds play: 15004 0 66 0)
		(self setScript: sMoveCCTV)
		(super init: &rest)
	)
	
	(method (doVerb)
	)
)

(instance spDoor of ShiversProp
	(properties
		priority 25
		fixPriority 1
		view 4192
	)
	
	(method (init)
		(self createPoly: 81 121 79 20 181 20 178 120)
		(super init: &rest)
		(self cycleSpeed: 10)
	)
	
	(method (doVerb)
		(self
			setCycle: End
			createPoly: 0 0 0 -5 -5 -5 -5 0
			setScript: sDoorOpen
		)
	)
)

(instance sDoorOpen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sounds play: 10406 0 90 self)
			)
			(1
				(theGame handsOn:)
				(efExitForward init: 3)
				(self dispose:)
			)
		)
	)
)

(instance sMoveCCTV of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 20)))
			(1
				(sounds play: 15003 0 66 0)
				(spCCTV setCycle: Beg self)
			)
			(2 (= seconds (Random 5 20)))
			(3
				(sounds play: 15004 0 66 0)
				(spCCTV setCycle: End self)
				(= state -1)
			)
		)
	)
)
