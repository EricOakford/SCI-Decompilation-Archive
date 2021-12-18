;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9630)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use Actor)
(use System)

(public
	rm9v63 0
)

(local
	local0
)
(instance rm9v63 of ShiversRoom
	(properties
		picture 9630
	)
	
	(method (init)
		(if global186
			(proc951_7 -30516)
			(efFalseForward init: 9)
		)
		(efExitLeft init: 2)
		(efExitRight init: 6)
		(if (proc951_5 41) (hsDoor init:))
		(super init: &rest)
	)
	
	(method (newRoom n)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9870
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9620
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 15240
	)
	
	(method (init)
		(self createPoly: 46 141 34 56 179 52 172 141)
		(super init: &rest)
	)
)

(instance efFalseForward of ExitFeature
	(properties
		nextRoom 9630
	)
	
	(method (init)
		(self createPoly: 46 141 34 56 179 52 172 141)
		(super init: &rest)
	)
	
	(method (handleEvent event)
		(event localize: thePlane)
		(if
			(and
				(& (event type?) evMOUSEBUTTON)
				(self onMe: event)
				(user canControl:)
			)
			(event claimed: 1)
			(hsDialogArea init:)
			(vDialog init:)
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

(instance vDialog of View
	(properties
		x 60
		y 110
		priority 250
		fixPriority 1
		view 921
		cel 6
	)
)

(instance pDoor of Prop
	(properties
		priority 25
		fixPriority 1
		view 9630
		cycleSpeed 10
	)
)

(instance hsDoor of HotSpot
	(properties)
	
	(method (init)
		(self createPoly: 46 141 34 56 179 52 172 141)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if (== global186 0) (curRoom setScript: sDoorOpens))
	)
)

(instance sDoorOpens of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(pDoor init:)
				(= cycles 1)
			)
			(1
				(sounds play: 11501 0 82 self)
				(pDoor setCycle: End)
			)
			(2
				(theGame handsOn:)
				(efExitForward init: 3)
				(= local0 1)
				(proc951_3 43)
				(hsDoor dispose:)
				(self dispose:)
			)
		)
	)
)
