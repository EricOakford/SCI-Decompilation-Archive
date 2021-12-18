;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6260)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use Actor)
(use System)

(public
	rm6v260 0
)

(local
	local0
)
(instance rm6v260 of ShiversRoom
	(properties
		picture 6260
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(hsDoor init:)
		(= local0 0)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(if (>= n 7000)
			(sounds fade: 20601 0 15 8 1 0)
			(sounds fade: 20603 0 15 8 1 0)
			(if (proc951_11 2 6000)
				(sounds playChain: -1 -4 20602 -1)
				(sounds adjChainVol: 34)
			)
		)
		(super newRoom: n &rest)
		(if (and local0 (<= n 7000))
			(sounds play: 10712 0 40 0)
		)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 6040
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 6500
	)
)

(instance efExitDoor of ExitFeature
	(properties
		nextRoom 7010
	)
	
	(method (init)
		(self createPoly: 103 143 103 38 173 38 173 144)
		(super init: &rest)
	)
)

(instance pExtra of Prop
	(properties
		priority 26
		fixPriority 1
		view 6260
		loop 1
		cycleSpeed 27
	)
)

(instance pDoor of Prop
	(properties
		priority 25
		fixPriority 1
		view 6260
		cycleSpeed 27
	)
)

(instance hsDoor of HotSpot
	(properties)
	
	(method (init)
		(self createPoly: 103 143 103 38 173 38 173 144)
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
				(if (proc951_5 8) (pExtra init:))
				(proc951_7 10711)
				(= cycles 1)
			)
			(1
				(pDoor setCycle: End)
				(if (proc951_5 8) (pExtra setCycle: End))
				(sounds play: 10711 0 40 self)
			)
			(2
				(theGame handsOn:)
				(efExitDoor init: 3)
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
		x 55
		y 118
		priority 250
		fixPriority 1
		view 921
		cel 5
	)
)
