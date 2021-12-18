;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11340)
(include sci.sh)
(use Main)
(use n925)
(use Procs)
(use Actor)
(use System)

(public
	rm11v340 0
)

(instance rm11v340 of ShiversRoom
	(properties
		picture 11340
	)
	
	(method (init)
		(spUnicorn init:)
		(super init: &rest)
		(efExitBack init: 8)
		(if (proc951_11 9 11000) (self setScript: sAttack))
	)
)

(instance efExitBack of ExitFeature
	(properties)
	
	(method (init)
		(self
			nextRoom: 11060
			createPoly:
				141
				0
				0
				1
				0
				140
				96
				142
				115
				115
				129
				59
				131
				22
				140
				10
				156
				23
				160
				59
				189
				141
				261
				142
				265
				0
		)
		(super init: &rest)
	)
)

(instance spUnicorn of ShiversProp
	(properties
		view 11341
	)
	
	(method (init)
		(self setPri: 2 1)
		((Prop new:) view: 11341 loop: 1 setPri: 1 1 init:)
		(super init: &rest)
	)
	
	(method (doVerb)
	)
)

(instance sAttack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc925_1 -6426 self spUnicorn)
			)
			(1
				(sounds stop: 21902)
				(efExitBack dispose:)
				(proc925_0 self)
			)
			(2
				(proc951_7 21902)
				(proc951_9 21902)
				(sounds play: 21902 -1 8 0)
				(efExitBack init: 8)
				(if (proc951_11 9 11000)
					(proc951_7 21103)
					(proc951_9 21103)
					(sounds play: 21103 -1 50 0)
					(proc951_7 21111)
					(proc951_9 21111)
					(sounds play: 21111 -1 32 0)
					(= state 0)
					(proc925_1 -6426 self spUnicorn 1)
				else
					(proc951_7 21101)
					(proc951_9 21101)
					(sounds play: 21101 -1 48 0)
					(sounds adjChainVol: 32)
					(sounds playChain: -1 -5 21102 -1)
					(self dispose:)
				)
			)
		)
	)
)
