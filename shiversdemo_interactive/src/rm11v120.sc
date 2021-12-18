;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11120)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use Actor)
(use System)

(public
	rm11v120 0
)

(local
	local0
)
(instance rm11v120 of ShiversRoom
	(properties
		picture 11120
	)
	
	(method (init)
		(= local0 0)
		(proc951_4 43)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(hsDoor init:)
		(efSkullDial init: 3)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(proc951_1 8)
		(if (>= n 12000)
			(proc951_4 43)
			(sounds stop: 21902)
			(sounds fade: 21103 0 5 16 1 0)
			(sounds stop: 21101)
			(sounds stopChain:)
		)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 11110
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 11130
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 12010
	)
	
	(method (init)
		(self createPoly: 87 123 87 18 189 18 189 123)
		(super init: &rest)
	)
)

(instance efSkullDial of ExitFeature
	(properties
		nextRoom 11330
	)
	
	(method (init)
		(self createPoly: 20 34 71 34 71 77 20 77)
		(super init: &rest)
	)
)

(instance hsDoor of HotSpot
	(properties)
	
	(method (init)
		(self createPoly: 87 123 87 18 189 18 189 123)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if (== global186 0)
			(curRoom setScript: sOpenDoor)
		else
			(curRoom setScript: sPurchaseMe)
		)
	)
)

(instance pDoor of Prop
	(properties
		view 11120
	)
	
	(method (init)
		(if (proc951_5 10) (self view: 11121))
		(super init: &rest)
	)
)

(instance pColossus of Prop
	(properties
		view 11120
		loop 1
	)
	
	(method (init)
		(if (proc951_5 10) (self view: 11121 loop: 1))
		(super init: &rest)
	)
)

(instance sOpenDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(hsDoor dispose:)
				(pDoor init: setPri: 25 1 setCycle: End)
				(if (proc951_5 9)
					(pColossus init: setPri: 27 1 setCycle: End)
				)
				(sounds play: 11106 0 32 self)
			)
			(1
				(efExitForward init: 3)
				(= local0 1)
				(proc951_3 43)
				(theGame handsOn:)
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
		x 65
		y 110
		priority 25
		view 921
	)
	
	(method (init)
		(self setPri: 25)
		(super init: &rest)
	)
)
