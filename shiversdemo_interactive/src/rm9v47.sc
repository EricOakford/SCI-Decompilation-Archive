;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9470)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use Actor)
(use System)

(public
	rm9v47 0
)

(instance rm9v47 of ShiversRoom
	(properties
		picture 9470
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(hsDoor init:)
		(if (proc951_5 42)
			(cond 
				(
					(or
						(== prevRoomNum 9850)
						(and (< 9300 prevRoomNum) (< prevRoomNum 9440))
					)
					(MonoOut {fade to 8})
					(sounds fade: 10908 8 15 8 0 0)
				)
				(
				(and (< 9000 prevRoomNum) (< prevRoomNum 9300))
					(proc951_7 10908)
					(proc951_9 10908)
					(sounds play: 10908 -1 8 0)
				)
			)
		)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(proc951_1 2)
		(if (and (< 1000 n) (< n 9000))
			(sounds fade: 20903 0 15 8 1 0)
			(MonoOut {fade to 0})
			(sounds fade: 10908 0 15 8 1 0)
		)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9460
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9440
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 8000
	)
	
	(method (init)
		(self createPoly: 77 143 77 0 184 0 184 142)
		(super init: &rest)
	)
)

(instance pDoor of Prop
	(properties
		priority 25
		fixPriority 1
		view 9470
		cycleSpeed 27
	)
)

(instance hsDoor of HotSpot
	(properties)
	
	(method (init)
		(self createPoly: 77 143 77 0 184 0 184 142)
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
				(pDoor init:)
				(proc951_7 10903)
				(= cycles 1)
			)
			(1
				(pDoor setCycle: End)
				(sounds play: 10903 0 40 self)
			)
			(2
				(theGame handsOn:)
				(efExitForward init: 3)
				(proc951_3 43)
				(hsDoor dispose:)
				(self dispose:)
			)
		)
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
		cel 1
	)
)
