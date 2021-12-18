;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9570)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use Actor)
(use System)

(public
	rm9v57 0
)

(local
	local0
)
(instance rm9v57 of ShiversRoom
	(properties
		picture 9570
	)
	
	(method (init)
		(efExitLeft init: 7)
		(efExitRight init: 6)
		(hsDoor init:)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(if (>= n 10000) (sounds fade: 20903 0 15 8 1 0))
		(if (and local0 (< n 10000))
			(sounds play: 12020 0 82 0)
		)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9560
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9560
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 20060
	)
	
	(method (init)
		(self createPoly: 64 144 62 14 200 14 199 143)
		(super init: &rest)
	)
)

(instance pDoor of Prop
	(properties
		priority 25
		fixPriority 1
		view 9570
		cycleSpeed 27
	)
)

(instance hsDoor of HotSpot
	(properties)
	
	(method (init)
		(self createPoly: 64 144 62 14 200 14 199 143)
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
				(proc951_7 12019)
				(= cycles 1)
			)
			(1
				(pDoor setCycle: End)
				(sounds play: 12019 0 82 self)
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
		x 60
		y 120
		priority 250
		fixPriority 1
		view 921
		cel 4
	)
)
