;;; Sierra Script 1.0 - (do not remove this comment)
(script# 3420)
(include sci.sh)
(use Main)
(use Actor)
(use System)

(public
	rm3v420 0
)

(instance rm3v420 of ShiversRoom
	(properties
		picture 3420
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 3)
		(super init: &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 3450
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 3430
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 3310
	)
	
	(method (init)
		(self createPoly: 90 89 72 5 239 8 206 91)
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
			(if (== global186 1) (curRoom setScript: sPurchaseMe))
		)
		(super handleEvent: event)
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
		cel 2
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
