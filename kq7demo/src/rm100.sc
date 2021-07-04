;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include game.sh)
(use Main)
(use Procs)
(use KQRoom)
(use Print)
(use Actor)
(use System)

(public
	rm100 0
)

(instance rm100 of KQRoom
	(properties
		picture 100
	)
	
	(method (init)
		(super init:)
		(Bset 21)
		(= curChapter 1)
		(= global104 -4)
		(rosella init:)
		(valenice init:)
		(self setScript: showOpening)
	)
)

(instance showOpening of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 1)
			)
			(1
				(messager sayRange: 0 0 1 1 14 self)
			)
			(2
				(Prints
					{Rosella jumps into the pool and the alternate universe.}
				)
				(rosella dispose:)
				(self cue:)
			)
			(3
				(messager sayRange: 0 0 1 15 17 self)
			)
			(4
				(Prints {Valenice goes over and picks up Rosella's comb.})
				(Prints {Then jumps into the pool.})
				(self cue:)
			)
			(5 (curRoom newRoom: 150))
		)
	)
)

(instance valenice of Actor
	(properties
		x 250
		y 80
		view 0
	)
)

(instance rosella of Actor
	(properties
		x 50
		y 80
		view 1
	)
)
