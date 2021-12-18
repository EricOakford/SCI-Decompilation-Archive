;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9710)
(include sci.sh)
(use Main)
(use Procs)
(use Motion)
(use Actor)

(public
	rm9v710 0
)

(instance rm9v710 of ShiversRoom
	(properties
		picture 9710
	)
	
	(method (init)
		(proc951_1 2)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(efExitForward init: 3)
		(efFountain init: 3)
		(efPlaque init: 3)
		(pFountain init:)
		(if (proc951_5 42)
			(if
			(and (< 9000 prevRoomNum) (< prevRoomNum 9200))
				(proc951_7 10908)
				(proc951_9 10908)
				(sounds play: 10908 -1 8 0)
			)
			(if (proc951_11 0 9000)
				(sounds fade: 20903 0 10 16 1 0)
				(sounds stop: 20908)
				(proc951_9 20908)
				(sounds play: 20908 -1 50 0)
			)
			((Prop new:)
				view: 9710
				setPri: 5 1
				loop: 1
				cycleSpeed: 12
				setCycle: Fwd
				init:
			)
		else
			((Prop new:) view: 9710 setPri: 5 1 loop: 0 init:)
		)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(if
		(and (!= n 9390) (proc951_5 42) (proc951_11 0 9000))
			(sounds fade: 20908 0 10 16 1 0)
			(sounds stop: 20903)
			(proc951_9 20903)
			(sounds play: 20903 -1 0 0)
			(sounds fade: 20903 42 10 16 0 0)
		)
		(super newRoom: n &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 9720
	)
	
	(method (init)
		(self
			createPoly: 0 0 20 0 20 72 13 72 14 112 20 112 20 144 0 143
		)
		(super init: &rest)
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9740
	)
	
	(method (init)
		(self
			createPoly: 228 123 218 118 220 17 231 1 248 1 263 0 263 140 227 122
		)
		(super init: &rest)
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 9370
	)
	
	(method (init)
		(self createPoly: 119 118 129 17 216 16 224 22 218 117)
		(super init: &rest)
	)
)

(instance efFountain of ExitFeature
	(properties
		nextRoom 9390
	)
	
	(method (init)
		(self createPoly: 13 72 31 72 34 83 47 85 52 106 14 113)
		(super init: &rest)
	)
)

(instance efPlaque of ExitFeature
	(properties
		nextRoom 9800
	)
	
	(method (init)
		(self createPoly: 208 142 231 125 263 140 263 143)
		(super init: &rest)
	)
)

(instance pFountain of ShiversProp
	(properties
		priority 3
		fixPriority 1
		view 9710
	)
)
