;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9690)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use Actor)
(use System)

(public
	rm9v69 0
)

(local
	local0
	local1
)
(instance rm9v69 of ShiversRoom
	(properties
		picture 9690
	)
	
	(method (init)
		(efExitLeft init: 7)
		(efExitRight init: 6)
		(super init: &rest)
		(if (== global186 0)
			(if (proc951_5 28)
				(hsDoor init:)
				(if (== prevRoomNum 9691)
					(spPuzzle cel: (spPuzzle lastCel:) init:)
					(curRoom setScript: sDoorOpens)
				else
					(spPuzzle init:)
				)
			else
				(spPuzzle init:)
				(if (== prevRoomNum 9691)
					(sounds play: 10926 0 82 0)
					(spPuzzle cel: (spPuzzle lastCel:) setCycle: Beg)
				)
			)
		else
			(hsDemoDoor init:)
		)
	)
	
	(method (newRoom n)
		(if (>= n 10000) (sounds fade: 20903 0 15 8 1 0))
		(super newRoom: n &rest)
		(if (and local0 (< 1000 n) (< n 10000))
			(sounds play: 10904 0 40 0)
		)
		(if
		(and local1 (!= n 9691) (< 1000 n) (< n 10000))
			(sounds play: 10926 0 82 0)
		)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9680
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9680
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 16000
	)
	
	(method (init)
		(self createPoly: 41 142 30 8 234 8 221 141)
		(super init: &rest)
	)
)

(instance efPuzzle of ExitFeature
	(properties
		nextRoom 9691
	)
	
	(method (init)
		(self createPoly: 69 25 69 109 200 109 200 25 70 25)
		(super init: &rest)
		(mouseDownHandler delete: self)
		(mouseDownHandler addToFront: self)
	)
)

(instance spPuzzle of ShiversProp
	(properties
		priority 100
		fixPriority 1
		view 9692
		cycleSpeed 12
	)
	
	(method (init)
		(mouseDownHandler delete: self)
		(mouseDownHandler addToFront: self)
		(super init: &rest)
	)
	
	(method (doVerb)
		(sounds play: 10925 0 82 0)
		(= local1 1)
		(self createPoly: -1 -1 -1 -1 -1 -1 setCycle: End)
		(efPuzzle init: 3)
	)
)

(instance pDoor of Prop
	(properties
		priority 25
		fixPriority 1
		view 9690
		cycleSpeed 27
	)
	
	(method (init)
		(if (proc951_5 13) (self view: 9691))
		(super init: &rest)
	)
)

(instance hsDoor of HotSpot
	(properties)
	
	(method (init)
		(self createPoly: 41 142 30 8 234 8 221 141)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if (== global186 0)
			(curRoom setScript: sDoorOpens)
		else
			(curRoom setScript: sPurchaseMe)
		)
	)
)

(instance sDoorOpens of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(efPuzzle dispose:)
				(pDoor init:)
				(if (proc951_5 13) (pDoor view: 9691))
				(proc951_7 10903)
				(if (== prevRoomNum 9691)
					(sounds play: 10926 0 82 0)
					(spPuzzle setCycle: Beg self)
				else
					(= cycles 1)
				)
			)
			(1
				(spPuzzle hide:)
				(pDoor setCycle: End)
				(sounds play: 10903 0 82 self)
			)
			(2
				(theGame handsOn:)
				(efExitForward init: 3)
				(= local0 1)
				(hsDoor dispose:)
				(self dispose:)
			)
		)
	)
)

(instance hsDemoDoor of HotSpot
	(properties)
	
	(method (init)
		(self createPoly: 16 0 28 142 233 142 244 0 17 0)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom setScript: sPurchaseMe)
	)
)

(instance hsDialogArea of HotSpot
	(properties
		nsRight 266
		nsBottom 143
	)
	
	(method (init)
		(super init: &rest)
		(mouseDownHandler delete: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (doVerb)
		(vDialog dispose:)
		(self dispose:)
	)
)

(instance sPurchaseMe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(vDialog init:)
				(sounds play: -30516 0 106 self)
			)
			(1
				(hsDialogArea init:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance vDialog of View
	(properties
		x 61
		y 115
		priority 250
		fixPriority 1
		view 921
		cel 3
	)
)
