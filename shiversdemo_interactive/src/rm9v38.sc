;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9380)
(include sci.sh)
(use Main)
(use Procs)

(public
	rm9v38 0
)

(instance rm9v38 of ShiversRoom
	(properties
		picture 9380
	)
	
	(method (init)
		(if (proc951_5 43)
			(sounds play: 10920 0 42 0)
			(proc951_4 43)
		)
		(efExitLeft init: 7)
		(efExitRight init: 1)
		(efExitForward init: 9)
		(efLeftForward init: 3)
		(efRightForward init: 3)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(if (proc951_5 42)
			(cond 
				((and (< 9700 n) (< n 9740)) (MonoOut {fade to 8}) (sounds fade: 10908 8 15 8 0 0))
				((== n 9340) (MonoOut {fade to 26}) (sounds fade: 10908 26 15 8 0 0))
			)
		)
		(if
			(and
				(!= n 9390)
				(!= n 9850)
				(proc951_5 42)
				(proc951_11 0 9000)
			)
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
		nextRoom 9390
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 9850
	)
)

(instance efExitForward of ExitFeature
	(properties
		nextRoom 9740
	)
	
	(method (init)
		(self
			createPoly: 158 120 141 120 136 112 138 80 158 81 166 114
		)
		(super init: &rest)
	)
)

(instance efLeftForward of ExitFeature
	(properties
		nextRoom 9340
	)
	
	(method (init)
		(self createPoly: 50 75 52 135 21 135 21 58 29 58)
		(super init: &rest)
	)
)

(instance efRightForward of ExitFeature
	(properties
		nextRoom 9730
	)
	
	(method (init)
		(self createPoly: 159 80 211 58 243 57 243 120 170 118)
		(super init: &rest)
	)
)
