;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6290)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use System)

(public
	rm6v290 0
)

(local
	local0
)
(instance rm6v290 of ShiversRoom
	(properties
		picture 6290
	)
	
	(method (init)
		(= local0 0)
		(efExitLeft init: 2)
		(efExitRight init: 6)
		(if (proc951_5 6)
			(spDoor init:)
		else
			(spOutDoor init:)
		)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(cond 
			((== n 5130) (sounds fade: 20601 0 15 8 1 0) (sounds fadeChain:))
			((== (spDoor loop?) 1) (sounds play: 10407 0 20 0))
		)
		(if
		(and (or (== n 6300) (== n 6400)) (== local0 1))
			(sounds play: 11009 0 50 0)
		)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 6300
	)
	
	(method (init)
		(self
			createPoly: 0 0 20 0 24 58 34 57 34 79 24 82 20 142 0 143
		)
		(super init: &rest)
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 6400
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 5130
	)
	
	(method (init)
		(self createPoly: 71 144 67 10 205 11 202 144)
		(super init: &rest)
	)
)

(instance spOutDoor of ShiversProp
	(properties
		priority 25
		fixPriority 1
		view 6290
	)
	
	(method (init)
		(self loop: 0 cel: 0 createPoly: -1 0 0 0 0 -1 -1 -1)
		(super init: &rest)
	)
	
	(method (doVerb)
	)
)

(instance spDoor of ShiversProp
	(properties
		priority 25
		fixPriority 1
		view 6291
	)
	
	(method (init)
		(self
			loop: 0
			cel: 0
			cycleSpeed: 10
			createPoly: 71 144 67 10 205 11 202 144
		)
		(super init: &rest)
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
				(sounds play: 11008 0 90 self)
			)
			(1
				(= local0 1)
				(theGame handsOn:)
				(efExitForward init: 3)
			)
		)
	)
)
