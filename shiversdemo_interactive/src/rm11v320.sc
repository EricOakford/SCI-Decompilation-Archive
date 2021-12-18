;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11320)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use Actor)
(use System)

(public
	rm11v320 0
)

(local
	local0
)
(instance rm11v320 of ShiversRoom
	(properties
		picture 11320
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(hsDoor init:)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(cond 
			((== n 19040)
				(sounds stop: 21101)
				(sounds fade: 21103 0 5 16 1 0)
				(sounds interruptChain:)
			)
			(local0 (proc951_3 43))
		)
		(super newRoom: n &rest)
	)
)

(instance hsDoor of HotSpot
	(properties)
	
	(method (init)
		(self
			createPoly: 67 143 68 37 91 12 131 5 179 16 199 42 200 150
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom setScript: sOpenDoor)
	)
)

(instance pDoor of Prop
	(properties
		view 11320
	)
)

(instance sOpenDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(hsDoor dispose:)
				(pDoor init: setPri: 25 1 cycleSpeed: 15 setCycle: End)
				(sounds play: 11901 0 82 self)
			)
			(1
				(efExitForward init: 3)
				(= local0 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 11500
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 11250
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 19040
	)
	
	(method (init)
		(self
			createPoly: 67 143 68 37 91 12 131 5 179 16 199 42 200 150
		)
		(super init: &rest)
	)
)
